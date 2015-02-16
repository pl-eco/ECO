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
      1168806208000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "88/ue2H1fXFD+h/sxOSqJBUAAA==");
}
