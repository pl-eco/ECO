package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class GlassShader implements Shader {
    private float eta;
    private float f0;
    private Color color;
    private float absorbtionDistance;
    private Color absorbtionColor;
    public GlassShader() { super();
                           eta = 1.3F;
                           color = Color.WHITE;
                           absorbtionDistance = 0;
                           absorbtionColor = Color.GRAY; }
    public boolean update(ParameterList pl, SunflowAPI api) { color = pl.
                                                                        getColor(
                                                                          "color",
                                                                          color);
                                                              eta =
                                                                pl.
                                                                  getFloat(
                                                                    "eta",
                                                                    eta);
                                                              f0 =
                                                                (1 -
                                                                   eta) /
                                                                  (1 +
                                                                     eta);
                                                              f0 =
                                                                f0 *
                                                                  f0;
                                                              absorbtionDistance =
                                                                pl.
                                                                  getFloat(
                                                                    "absorbtion.distance",
                                                                    absorbtionDistance);
                                                              absorbtionColor =
                                                                pl.
                                                                  getColor(
                                                                    "absorbtion.color",
                                                                    absorbtionColor);
                                                              return true;
    }
    public Color getRadiance(ShadingState state) {
        if (!state.
              includeSpecular())
            return Color.
                     BLACK;
        Vector3 reflDir =
          new Vector3(
          );
        Vector3 refrDir =
          new Vector3(
          );
        state.
          faceforward();
        float cos =
          state.
          getCosND();
        boolean inside =
          state.
          isBehind();
        float neta =
          inside
          ? eta
          : 1.0F /
          eta;
        float dn =
          2 *
          cos;
        reflDir.
          x =
          dn *
            state.
              getNormal().
              x +
            state.
              getRay().
              getDirection().
              x;
        reflDir.
          y =
          dn *
            state.
              getNormal().
              y +
            state.
              getRay().
              getDirection().
              y;
        reflDir.
          z =
          dn *
            state.
              getNormal().
              z +
            state.
              getRay().
              getDirection().
              z;
        float arg =
          1 -
          neta *
          neta *
          (1 -
             cos *
             cos);
        boolean tir =
          arg <
          0;
        if (tir)
            refrDir.
              x =
              (refrDir.
                 y =
                 (refrDir.
                    z =
                    0));
        else {
            float nK =
              neta *
              cos -
              (float)
                Math.
                sqrt(
                  arg);
            refrDir.
              x =
              neta *
                state.
                  getRay().
                  dx +
                nK *
                state.
                  getNormal().
                  x;
            refrDir.
              y =
              neta *
                state.
                  getRay().
                  dy +
                nK *
                state.
                  getNormal().
                  y;
            refrDir.
              z =
              neta *
                state.
                  getRay().
                  dz +
                nK *
                state.
                  getNormal().
                  z;
        }
        float cosTheta1 =
          Vector3.
          dot(
            state.
              getNormal(),
            reflDir);
        float cosTheta2 =
          -Vector3.
          dot(
            state.
              getNormal(),
            refrDir);
        float pPara =
          (cosTheta1 -
             eta *
             cosTheta2) /
          (cosTheta1 +
             eta *
             cosTheta2);
        float pPerp =
          (eta *
             cosTheta1 -
             cosTheta2) /
          (eta *
             cosTheta1 +
             cosTheta2);
        float kr =
          0.5F *
          (pPara *
             pPara +
             pPerp *
             pPerp);
        float kt =
          1 -
          kr;
        Color absorbtion =
          null;
        if (inside &&
              absorbtionDistance >
              0) {
            absorbtion =
              Color.
                mul(
                  -state.
                    getRay().
                    getMax() /
                    absorbtionDistance,
                  absorbtionColor.
                    copy().
                    opposite()).
                exp();
            if (absorbtion.
                  isBlack())
                return Color.
                         BLACK;
        }
        Color ret =
          Color.
          black();
        if (!tir) {
            ret.
              madd(
                kt,
                state.
                  traceRefraction(
                    new Ray(
                      state.
                        getPoint(),
                      refrDir),
                    0)).
              mul(
                color);
        }
        if (!inside ||
              tir)
            ret.
              add(
                Color.
                  mul(
                    kr,
                    state.
                      traceReflection(
                        new Ray(
                          state.
                            getPoint(),
                          reflDir),
                        0)).
                  mul(
                    color));
        return absorbtion !=
          null
          ? ret.
          mul(
            absorbtion)
          : ret;
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        Color refr =
          Color.
          mul(
            1 -
              f0,
            color);
        Color refl =
          Color.
          mul(
            f0,
            color);
        float avgR =
          refl.
          getAverage();
        float avgT =
          refr.
          getAverage();
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              avgR) {
            state.
              faceforward();
            if (state.
                  isBehind())
                return;
            float cos =
              state.
              getCosND();
            power.
              mul(
                refl).
              mul(
                1.0F /
                  avgR);
            float dn =
              2 *
              cos;
            Vector3 dir =
              new Vector3(
              );
            dir.
              x =
              dn *
                state.
                  getNormal().
                  x +
                state.
                  getRay().
                  getDirection().
                  x;
            dir.
              y =
              dn *
                state.
                  getNormal().
                  y +
                state.
                  getRay().
                  getDirection().
                  y;
            dir.
              z =
              dn *
                state.
                  getNormal().
                  z +
                state.
                  getRay().
                  getDirection().
                  z;
            state.
              traceReflectionPhoton(
                new Ray(
                  state.
                    getPoint(),
                  dir),
                power);
        }
        else
            if (rnd <
                  avgR +
                  avgT) {
                state.
                  faceforward();
                float cos =
                  state.
                  getCosND();
                float neta =
                  state.
                  isBehind()
                  ? eta
                  : 1.0F /
                  eta;
                power.
                  mul(
                    refr).
                  mul(
                    1.0F /
                      avgT);
                float wK =
                  -neta;
                float arg =
                  1 -
                  neta *
                  neta *
                  (1 -
                     cos *
                     cos);
                Vector3 dir =
                  new Vector3(
                  );
                if (state.
                      isBehind() &&
                      absorbtionDistance >
                      0) {
                    power.
                      mul(
                        Color.
                          mul(
                            -state.
                              getRay().
                              getMax() /
                              absorbtionDistance,
                            absorbtionColor.
                              copy().
                              opposite()).
                          exp());
                }
                if (arg <
                      0) {
                    float dn =
                      2 *
                      cos;
                    dir.
                      x =
                      dn *
                        state.
                          getNormal().
                          x +
                        state.
                          getRay().
                          getDirection().
                          x;
                    dir.
                      y =
                      dn *
                        state.
                          getNormal().
                          y +
                        state.
                          getRay().
                          getDirection().
                          y;
                    dir.
                      z =
                      dn *
                        state.
                          getNormal().
                          z +
                        state.
                          getRay().
                          getDirection().
                          z;
                    state.
                      traceReflectionPhoton(
                        new Ray(
                          state.
                            getPoint(),
                          dir),
                        power);
                }
                else {
                    float nK =
                      neta *
                      cos -
                      (float)
                        Math.
                        sqrt(
                          arg);
                    dir.
                      x =
                      -wK *
                        state.
                          getRay().
                          dx +
                        nK *
                        state.
                          getNormal().
                          x;
                    dir.
                      y =
                      -wK *
                        state.
                          getRay().
                          dy +
                        nK *
                        state.
                          getNormal().
                          y;
                    dir.
                      z =
                      -wK *
                        state.
                          getRay().
                          dz +
                        nK *
                        state.
                          getNormal().
                          z;
                    state.
                      traceRefractionPhoton(
                        new Ray(
                          state.
                            getPoint(),
                          dir),
                        power);
                }
            }
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169410512000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKUYfYwUV/3t7n1wH3TvDjgOCnfcB6096A5tpFGuEc7rQQ+W" +
       "sr3jruUAr29n3u4N\nzM6bzrzdW66ktlF7WKIt1iYaLSUGA/2kSTVoUitNW6" +
       "0lJq2JNWlS1JCoidbEmFSM/uHvvTezMzu7\nexTcZN68fe/3/fV+b178GNU7" +
       "NlqjOgl2xCJOYmQihW2HaCMGdpy9sDSjvl3flDqzy6RRFEmiqK4x\nFE+qjq" +
       "JhhhVdU8buGiraaINFjSNZg7IEKbLEIWOzS29ncnMFwftOnu949HRdTxTVJ1" +
       "EcmyZlmOnU\nHDVIzmGoLXkIF7CSZ7qhJHWHDSXRUmLmcyPUdBg2mfMgehjF" +
       "kqjBUjlNhnqTHnMFmCsWtnFOEeyV\nlGALFJbZhGHdJNpwiR1gbizHBLFdvP" +
       "FKaCCyhG9OgTpCAtB6XUlrqW2Fqlbs7NQdR089F0PxaRTX\nzQlOTAVNGPCb" +
       "Rq05kksT2xnWNKJNo3aTEG2C2Do29HnBdRp1OHrWxCxvE2ecONQocMAOJ28R" +
       "W/D0\nFpOoVeU62XmVUbtko4xODM37V58xcBbU7vTVlupu5+ugYLMOgtkZrB" +
       "IPpe6wboLHe8IYJR0HdgEA\noDbmCJulJVZ1JoYF1CF9aWAzq0wwWzezAFpP" +
       "88CFodU1iXJbW1g9jLNkhqGuMFxKbgFUkzAER2Fo\nRRhMUAIvrQ55KeCfDZ" +
       "2fHDv7/de3idiu04hqcPmbAak7hDROMsQmpkok4pV84ttj+/JroggB8IoQ\n" +
       "sIQZXn9+MvmXn/dImBurwOxJHyIqm1HvOdEz/tAOimJcjCUWdXTu/DLNRTqk" +
       "3J2hogVZ21miyDcT\n3uaF8V/se+R58tcoah5DDSo18jmIo3aV5izdIPYOYh" +
       "IbM6KNoSZiaiNifww1wjwJIS9X92QyDmFj\nqM4QSw1U/AcTZYAEN1ETzHUz" +
       "Q725hdmsmBcthFAjPGgQnhYkf+LN0B0JxcmbGYPOKY6tKtTOlv6r\n1CaKM4" +
       "s1Yis7uIMmxDzB48diaFyZpTmiYBWbukmVrA4Zq9JbNVLg7+uiWuTydsxFIr" +
       "wAhhPZgBy4\nmxoAO6Oeufzu0dFdXz8mg4QHtqspQ/3ALOEyS3BmCcksEWCG" +
       "IhHBYzlnKl0Fhj4MKQvFrfWWiYM7\nHzjWF4MYsebqwEoctA90ciUZVemIn9" +
       "djogSqEFxdP9i/kLhyZqsMLqV2+a2K3fLeSxdP/bN1MIqi\n1Wsj1xCqczMn" +
       "k+IFtVTzBsLZVI3+3x/f/eoHFz/6jJ9XDA1UpHslJk/XvrAvbKoSDQqgT/70" +
       "qnjs\nPjR1IorqoAZA3RPyQ0npDvMoS9shrwRyXRqTqCVD7Rw2+JZXt5rZrE" +
       "3n/BURJG1ivsyL4054OtzA\nFm++u8LiY6cMKu7tkBaixF75SsOm373W8rYw" +
       "i1eN44HzboIwmdvtfrDstQmB9Y++k3rq6Y8X9otI\ncUOFwSGYTxu6WgSUm3" +
       "wUSGoDCgt35MCkmaOantFx2iA84v4bX3/bj//2zTbpGgNWPM9uvDoBf33V\n" +
       "F9EjF7/0r25BJqLyQ8VXwweT2izzKQ/bNj7C5Sg++pu13/0lfgZqHtQZR58n" +
       "onQgoRkSdlSE3QfF\nmAjt3caHPqC9sUboVznCZ9Sjz2f78g/+6qdC6hYc7A" +
       "WCbtiNrSHpeT70c+uuDGfv3diZBbjPXrjn\nQJtx4T9AcRooqnB0OntsSPti" +
       "mRNd6PrGD994s/OB92Mouh01GxRr27GIf9QEgUecWSg5RWvrNhFb\nbXNL+C" +
       "hURsIIq10DFAP/eP92S+30384bAD9zZtIbzybf3fOMMEDNxK9y/oXozL8+ef" +
       "LKr9klQcfP\nQI7dW6ysp9A0+bif+6DQ3vDKs7koapxGbarb1k1hI8/jfBq6" +
       "EMfr9aD1K9sv7yjk8TlUqjBrwtkf\nYBvOfb+Ow5xD83lrKN1bvXRvddO9NZ" +
       "zuESQmWwXKgBhvLiVno2XrBcxbPRQDS4OjuoKNtq3n4MAu\niNJ0+bG+n70z" +
       "+eyCLOeL+LMMa0b98u//cDj2xBtpiRd2Wgj4RPfpP716eXy5TH3Z2fVXNFdB" +
       "HNnd\nCavELZ4GvYtxENBvbeh98eHxS65EHeU9yij08X8+8ia5+c5v/LHKcQ" +
       "oeopjJEsrH2/mwTUb75ppZ\nsaXcXyvgWer6a2kNf6X4MMxQNLOJz8ZCLO+9" +
       "RpZd8NzgsryhBstJl2U9dGRug76SoZXBBkLPQVfL\nay+1QwJNXaNA6+GJuw" +
       "LFawh0wBWoA6cdaqd59t+ly2SpZpOD1yhCn7ftvauIgF0R4r4II9dhnfQi\n" +
       "oonYvSlQLyNViYveTfZrPMzX1royiBBfuP8frY/htw5G3bNoF4PyTa1bDVIg" +
       "RoBVjFMqa+V2i0uS\nXwoff+6F8+z9DVtksgzWTvsw4uCWU/M9W84dv44Gri" +
       "ekW5h0e+HGe2Oz+jtRcY+TlbXi/leONFRe\nT5tBnrxt7i2rquvKm6gheFa5" +
       "4bGqahPlO87vAKLCrlHPhd0VLhSqErhe8hbDA+sMgk3I93BqTLAp\nLNJjHO" +
       "UDtAYNeUuDMi5gRi0ZbDuhvKcpNQg2/UBkV8sR7wAXf2i5RTbB0+9apP9TWy" +
       "RSHtRrqwY1\n3Lv5lwciyCwsovFxPnyVoZYsYeOAx517tWT01f/a/6P+F+DZ" +
       "56q/73oDYnH1r6aJYPP0Iub5Hh++\nxdBSR8UMgiw1S5mba4G4qCtQXfOt8t" +
       "SntQq0CS2BeyNvnLsqPjDJjyJq8sOHDnyS/O2/xQ2o9OGi\nJYmWZPKGEext" +
       "AvMGyyYZXWjSIjsdS7x+WK0YyossBL+cCElPS/izDLWF4UFt/gqCvQD6BMAg" +
       "YdxZ\nEOhl6I0AiE/PWZ6D2kTjzHu8hOzximWG4pbpL6uV4pufV8/y8qvfjH" +
       "r/S/vXFY/vfVIUyXrVwPPz\nnExzEjXKm1+pJvbWpObReg+9cm7qtZc/79V8" +
       "cTNYXvRPorLYvl3uLuJ4qMPVr1ujOYuJC9L8T1b+\n6M4zJy9FxYXvf1i3dF" +
       "+qFQAA");
}
