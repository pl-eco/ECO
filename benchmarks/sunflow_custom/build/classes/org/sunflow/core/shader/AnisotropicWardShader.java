package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.LightSample;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class AnisotropicWardShader implements Shader {
    private Color rhoD;
    private Color rhoS;
    private float alphaX;
    private float alphaY;
    private int numRays;
    public AnisotropicWardShader() { super();
                                     rhoD = Color.GRAY;
                                     rhoS = Color.GRAY;
                                     alphaX = 1;
                                     alphaY = 1;
                                     numRays = 4; }
    public boolean update(ParameterList pl, SunflowAPI api) { rhoD = pl.getColor(
                                                                          "diffuse",
                                                                          rhoD);
                                                              rhoS =
                                                                pl.
                                                                  getColor(
                                                                    "specular",
                                                                    rhoS);
                                                              alphaX =
                                                                pl.
                                                                  getFloat(
                                                                    "roughnessX",
                                                                    alphaX);
                                                              alphaY =
                                                                pl.
                                                                  getFloat(
                                                                    "roughnessY",
                                                                    alphaY);
                                                              numRays =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    numRays);
                                                              return true;
    }
    protected Color getDiffuse(ShadingState state) {
        return rhoD;
    }
    private float brdf(Vector3 i, Vector3 o,
                       OrthoNormalBasis basis) {
        float fr =
          4 *
          (float)
            Math.
              PI *
          alphaX *
          alphaY;
        fr *=
          (float)
            Math.
            sqrt(
              basis.
                untransformZ(
                  i) *
                basis.
                untransformZ(
                  o));
        Vector3 h =
          Vector3.
          add(
            i,
            o,
            new Vector3(
              ));
        basis.
          untransform(
            h);
        float hx =
          h.
            x /
          alphaX;
        hx *=
          hx;
        float hy =
          h.
            y /
          alphaY;
        hy *=
          hy;
        float hn =
          h.
            z *
          h.
            z;
        fr =
          (float)
            Math.
            exp(
              -(hx +
                  hy) /
                hn) /
            fr;
        return fr;
    }
    public Color getRadiance(ShadingState state) {
        state.
          faceforward(
            );
        OrthoNormalBasis onb =
          state.
          getBasis(
            );
        state.
          initLightSamples(
            );
        state.
          initCausticSamples(
            );
        Color lr =
          Color.
          black(
            );
        if (state.
              includeSpecular(
                )) {
            Vector3 in =
              state.
              getRay(
                ).
              getDirection(
                ).
              negate(
                new Vector3(
                  ));
            for (LightSample sample
                  :
                  state) {
                float cosNL =
                  sample.
                  dot(
                    state.
                      getNormal(
                        ));
                float fr =
                  brdf(
                    in,
                    sample.
                      getShadowRay(
                        ).
                      getDirection(
                        ),
                    onb);
                lr.
                  madd(
                    cosNL *
                      fr,
                    sample.
                      getSpecularRadiance(
                        ));
            }
            if (numRays >
                  0) {
                int n =
                  state.
                  getDepth(
                    ) ==
                  0
                  ? numRays
                  : 1;
                for (int i =
                       0;
                     i <
                       n;
                     i++) {
                    double r1 =
                      state.
                      getRandom(
                        i,
                        0,
                        n);
                    double r2 =
                      state.
                      getRandom(
                        i,
                        1,
                        n);
                    float alphaRatio =
                      alphaY /
                      alphaX;
                    float phi =
                      0;
                    if (r1 <
                          0.25) {
                        double val =
                          4 *
                          r1;
                        phi =
                          (float)
                            Math.
                            atan(
                              alphaRatio *
                                Math.
                                tan(
                                  Math.
                                    PI /
                                    2 *
                                    val));
                    }
                    else
                        if (r1 <
                              0.5) {
                            double val =
                              1 -
                              4 *
                              (0.5 -
                                 r1);
                            phi =
                              (float)
                                Math.
                                atan(
                                  alphaRatio *
                                    Math.
                                    tan(
                                      Math.
                                        PI /
                                        2 *
                                        val));
                            phi =
                              (float)
                                Math.
                                  PI -
                                phi;
                        }
                        else
                            if (r1 <
                                  0.75) {
                                double val =
                                  4 *
                                  (r1 -
                                     0.5);
                                phi =
                                  (float)
                                    Math.
                                    atan(
                                      alphaRatio *
                                        Math.
                                        tan(
                                          Math.
                                            PI /
                                            2 *
                                            val));
                                phi +=
                                  Math.
                                    PI;
                            }
                            else {
                                double val =
                                  1 -
                                  4 *
                                  (1 -
                                     r1);
                                phi =
                                  (float)
                                    Math.
                                    atan(
                                      alphaRatio *
                                        Math.
                                        tan(
                                          Math.
                                            PI /
                                            2 *
                                            val));
                                phi =
                                  2 *
                                    (float)
                                      Math.
                                        PI -
                                    phi;
                            }
                    float cosPhi =
                      (float)
                        Math.
                        cos(
                          phi);
                    float sinPhi =
                      (float)
                        Math.
                        sin(
                          phi);
                    float denom =
                      cosPhi *
                      cosPhi /
                      (alphaX *
                         alphaX) +
                      sinPhi *
                      sinPhi /
                      (alphaY *
                         alphaY);
                    float theta =
                      (float)
                        Math.
                        atan(
                          Math.
                            sqrt(
                              -Math.
                                log(
                                  1 -
                                    r2) /
                                denom));
                    float sinTheta =
                      (float)
                        Math.
                        sin(
                          theta);
                    float cosTheta =
                      (float)
                        Math.
                        cos(
                          theta);
                    Vector3 h =
                      new Vector3(
                      );
                    h.
                      x =
                      sinTheta *
                        cosPhi;
                    h.
                      y =
                      sinTheta *
                        sinPhi;
                    h.
                      z =
                      cosTheta;
                    onb.
                      transform(
                        h);
                    Vector3 o =
                      new Vector3(
                      );
                    float ih =
                      Vector3.
                      dot(
                        h,
                        in);
                    o.
                      x =
                      2 *
                        ih *
                        h.
                          x -
                        in.
                          x;
                    o.
                      y =
                      2 *
                        ih *
                        h.
                          y -
                        in.
                          y;
                    o.
                      z =
                      2 *
                        ih *
                        h.
                          z -
                        in.
                          z;
                    float no =
                      onb.
                      untransformZ(
                        o);
                    float ni =
                      onb.
                      untransformZ(
                        in);
                    float w =
                      ih *
                      cosTheta *
                      cosTheta *
                      cosTheta *
                      (float)
                        Math.
                        sqrt(
                          Math.
                            abs(
                              no /
                                ni));
                    Ray r =
                      new Ray(
                      state.
                        getPoint(
                          ),
                      o);
                    lr.
                      madd(
                        w /
                          n,
                        state.
                          traceGlossy(
                            r,
                            i));
                }
            }
            lr.
              mul(
                rhoS);
        }
        lr.
          add(
            state.
              diffuse(
                getDiffuse(
                  state)));
        return lr;
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        state.
          faceforward(
            );
        Color d =
          getDiffuse(
            state);
        state.
          storePhoton(
            state.
              getRay(
                ).
              getDirection(
                ),
            power,
            d);
        float avgD =
          d.
          getAverage(
            );
        float avgS =
          rhoS.
          getAverage(
            );
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              avgD) {
            power.
              mul(
                d).
              mul(
                1.0F /
                  avgD);
            OrthoNormalBasis onb =
              state.
              getBasis(
                );
            double u =
              2 *
              Math.
                PI *
              rnd /
              avgD;
            double v =
              state.
              getRandom(
                0,
                1,
                1);
            float s =
              (float)
                Math.
                sqrt(
                  v);
            float s1 =
              (float)
                Math.
                sqrt(
                  1.0F -
                    v);
            Vector3 w =
              new Vector3(
              (float)
                Math.
                cos(
                  u) *
                s,
              (float)
                Math.
                sin(
                  u) *
                s,
              s1);
            w =
              onb.
                transform(
                  w,
                  new Vector3(
                    ));
            state.
              traceDiffusePhoton(
                new Ray(
                  state.
                    getPoint(
                      ),
                  w),
                power);
        }
        else
            if (rnd <
                  avgD +
                  avgS) {
                power.
                  mul(
                    rhoS).
                  mul(
                    1 /
                      avgS);
                OrthoNormalBasis basis =
                  state.
                  getBasis(
                    );
                Vector3 in =
                  state.
                  getRay(
                    ).
                  getDirection(
                    ).
                  negate(
                    new Vector3(
                      ));
                double r1 =
                  rnd /
                  avgS;
                double r2 =
                  state.
                  getRandom(
                    0,
                    1,
                    1);
                float alphaRatio =
                  alphaY /
                  alphaX;
                float phi =
                  0;
                if (r1 <
                      0.25) {
                    double val =
                      4 *
                      r1;
                    phi =
                      (float)
                        Math.
                        atan(
                          alphaRatio *
                            Math.
                            tan(
                              Math.
                                PI /
                                2 *
                                val));
                }
                else
                    if (r1 <
                          0.5) {
                        double val =
                          1 -
                          4 *
                          (0.5 -
                             r1);
                        phi =
                          (float)
                            Math.
                            atan(
                              alphaRatio *
                                Math.
                                tan(
                                  Math.
                                    PI /
                                    2 *
                                    val));
                        phi =
                          (float)
                            Math.
                              PI -
                            phi;
                    }
                    else
                        if (r1 <
                              0.75) {
                            double val =
                              4 *
                              (r1 -
                                 0.5);
                            phi =
                              (float)
                                Math.
                                atan(
                                  alphaRatio *
                                    Math.
                                    tan(
                                      Math.
                                        PI /
                                        2 *
                                        val));
                            phi +=
                              Math.
                                PI;
                        }
                        else {
                            double val =
                              1 -
                              4 *
                              (1 -
                                 r1);
                            phi =
                              (float)
                                Math.
                                atan(
                                  alphaRatio *
                                    Math.
                                    tan(
                                      Math.
                                        PI /
                                        2 *
                                        val));
                            phi =
                              2 *
                                (float)
                                  Math.
                                    PI -
                                phi;
                        }
                float cosPhi =
                  (float)
                    Math.
                    cos(
                      phi);
                float sinPhi =
                  (float)
                    Math.
                    sin(
                      phi);
                float denom =
                  cosPhi *
                  cosPhi /
                  (alphaX *
                     alphaX) +
                  sinPhi *
                  sinPhi /
                  (alphaY *
                     alphaY);
                float theta =
                  (float)
                    Math.
                    atan(
                      Math.
                        sqrt(
                          -Math.
                            log(
                              1 -
                                r2) /
                            denom));
                float sinTheta =
                  (float)
                    Math.
                    sin(
                      theta);
                float cosTheta =
                  (float)
                    Math.
                    cos(
                      theta);
                Vector3 h =
                  new Vector3(
                  );
                h.
                  x =
                  sinTheta *
                    cosPhi;
                h.
                  y =
                  sinTheta *
                    sinPhi;
                h.
                  z =
                  cosTheta;
                basis.
                  transform(
                    h);
                Vector3 o =
                  new Vector3(
                  );
                float ih =
                  Vector3.
                  dot(
                    h,
                    in);
                o.
                  x =
                  2 *
                    ih *
                    h.
                      x -
                    in.
                      x;
                o.
                  y =
                  2 *
                    ih *
                    h.
                      y -
                    in.
                      y;
                o.
                  z =
                  2 *
                    ih *
                    h.
                      z -
                    in.
                      z;
                Ray r =
                  new Ray(
                  state.
                    getPoint(
                      ),
                  o);
                state.
                  traceReflectionPhoton(
                    r,
                    power);
            }
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "l1eKxZ9v/tk9L65+IG9I/wNU0Jw6NBUAAA==");
}
