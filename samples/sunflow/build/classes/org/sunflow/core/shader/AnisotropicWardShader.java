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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1Yb2wcxRWfO9t3tuP4zk7imNRxHGOjOoHbpoWi1Cip4ziJ" +
       "wyU+bJMWo2LGu3O+TfZ2lt05++LUlEQgR5EaIerQgKglUCL+BYKqRrSqkPKl" +
       "BUS/UCEqPgBVvxSV5kM+lKLSlr6Z2b3d27tz4Est7Xh29s37M++933tzl66h" +
       "BsdG2y1qHJ81KEuRIksdNe5IseMWcVIH03dksO0QbdjAjjMJa9Nq72uJz754" +
       "PJeMotgUWodNkzLMdGo648ShxhzR0ijhr44YJO8wlEwfxXNYKTDdUNK6wwbT" +
       "aE1gK0N9aU8FBVRQQAVFqKAM+VSwaS0xC/lhvgObzHkIPYwiaRSzVK4eQ1vL" +
       "mVjYxnmXTUZYABwa+fsRMEpsLtqop2S7tLnC4HPbleWfP5D8ZR1KTKGEbk5w" +
       "dVRQgoGQKdSSJ/kZYjtDmka0KdRmEqJNEFvHhr4g9J5C7Y4+a2JWsEnpkPhi" +
       "wSK2kOmfXIvKbbMLKqN2ybysTgzNe2vIGngWbO3wbZUW7uPrYGCzDorZWawS" +
       "b0v9Md3UGNoS3lGyse9uIICt8TxhOVoSVW9iWEDt0ncGNmeVCWbr5iyQNtAC" +
       "SGFoU02m/KwtrB7Ds2Saoc4wXUZ+AqomcRB8C0MbwmSCE3hpU8hLAf9cO3zX" +
       "2RPmATMqdNaIanD9G2FTd2jTOMkSm5gqkRtbtqWfxB1vnI4iBMQbQsSS5vUf" +
       "X//+rd1X35I036hCMzZzlKhsWr0w0/pu1/DAzjquRqNFHZ07v8xyEf4Z98tg" +
       "0YLM6yhx5B9T3ser47+/75GXyKdR1DyKYio1CnmIozaV5i3dIPZ+YhIbM6KN" +
       "oiZiasPi+yiKwzytm0SujmWzDmGjqN4QSzEq3uGIssCCH1Ec5rqZpd7cwiwn" +
       "5kULIRSHB90Oz1ok/8R/hrCSo3miYBWbukkViF2CbTWnEJUqDs5bBnjNKZhZ" +
       "g84rjq0q1J4tvavUJoqTwxqxIbF1hzKbWrr6A2xrE2I1xUPN+n8IKXJLk/OR" +
       "CDihKwwBBmTPAWoA7bS6XNgzcv3V6XeipZRwz4ih20BsyhWb4mJTUmyqqlgU" +
       "iQhp67l46W5w1jFIewDEloGJHx188HRvHcSZNV8PJ81Je8FeV6cRlQ772DAq" +
       "EFCFAO187v6l1OfP75YBqtQG8qq70dXz8yeP/ORbURQtR2RuIyw18+0ZjqMl" +
       "vOwLZ2I1vomlTz67/OQi9XOyDOJdqKjcyVO9N+wNm6pEA/D02W/rwVem31js" +
       "i6J6wA/ATIYhxgGOusMyylJ+0INPbksDGJyldh4b/JOHec0sZ9N5f0WESauY" +
       "t4FT1vAc6IFnvZsU4j//us7i43oZVtzLISsEPO/7zdWnrjy9fWc0iOSJQG2c" +
       "IEziQpsfJJM2IbD+4fnMz85dW7pfRAhQ3FxNQB8fhwElwGVwrI+99dAHH390" +
       "4b2oH1UMymVhxtDVIvC4xZcCGGIAjnHf991r5qmmZ3U8YxAenP9O9O+48vez" +
       "SelNA1a8YLj1xgz89Zv2oEfeeeCf3YJNROU1zLfcJ5MHsM7nPGTb+DjXo3jy" +
       "j5ufehP/AiAWYM3RF4hAKiQsQ+LoFeGqbWJMhb7t4EOPVfGtKFY6xRvvhgZq" +
       "J9E+XooDyfevMWPm1F8+FxZVpE+VChTaP6VcembT8K5PxX4/jvnuLcVKXIK2" +
       "xd/77Zfy/4j2xn4XRfEplFTdnugINgo8WqagD3C8Rgn6prLv5TVdFrDBUp52" +
       "hXMoIDacQT4ewpxT83lzKGla+ClvhKfVTZrWcNJEkJjsFFt6xdjPh296MRu3" +
       "bH0O84YL1ds5uleQbGBoYxCB9Tw0FDwOqTjBpPT07ZV6JFw9EjX0GOLDoBQ1" +
       "wee7a/O7CZ6kyy9Zg99el18MG1YO/3D1EMvYeh56gDm3SVEW2z8+9swnr0h8" +
       "D8dTiJicXj7zZerscjTQ9t1c0XkF98jWTzhsrTTsS/iLwPNf/nCD+IIs/e3D" +
       "bv/RU2pALIvjyNbV1BIi9v318uJvX1hckma0l3c9I9DUv/L+f/6QOv/nt6uU" +
       "WYg4itnqLmhzXdBWwwX3lLngPv52sDbHTnjaXY7tNThOuhzjoPw4Pu4IDvv5" +
       "cLcElEMM1UEvLqWIE+4LAE2kagiLJkK2C/xcN9fqesWZXji1vKKNXdwRdfHt" +
       "MENNjFq3GWSOGCFM21zWSRwSfb6PJWdefPl19u7270nvbKsdnOGNb57626bJ" +
       "XbkHv0b/sCVkU5jli4cuvb3/FvWJKKorQVLF1aV802A5EDXbBO5a5mQZHHWX" +
       "PLzOi5ku18NdVWu47zC/mkTFeUY913VXuE6YSuBmxMuVR9YRJJuQ/4cyo0JM" +
       "bpV6ZfIBwDpWsDTItWoBFp+h1CDYrCxqYgGXjBZ9Sjc8/a7R/dUbl1oA3GTZ" +
       "lEGZIFoxpHGkPJw3Vw1nuDTyazMRUhZWsflhPjCGmmcJ26tnswVH+HD3DQ0U" +
       "adoBz4Br4EBNrw6G5NcJjnWeBZ1BC/JwJ0odIbxH/Q7//phHtbWCasyGmDws" +
       "Oso92NEdIfDRVWw9w4eTUGZmbC0rEOmGVrZ58Xqna+WdXzl2XT/x1xOC6vFV" +
       "VHuCDz9laA24YRzcx7Psq/lBdMi74FlyNVz6utklNBTCBOn5VdR8mg/nGJQu" +
       "FTPIukyOMhd8QolSP0d1rUrrx9CGqpc13oJ2VvwyJH/NUF9dSTRuXLn3T+L6" +
       "UfrFoQmu/dmCYQRbosA8Ztkkqwu1m2SDJIvCc9VKgLxHQurLidD7WUl/kaFk" +
       "mB7s4/+CZC+A8wJkABXuLEj0MpQoIOLTS5YX2UnRefPWMCVbwyIKFBN++Qi+" +
       "ld1EeL0Qv7p52F6Qv7tNq5dXDh4+cf27F0WhaFANvLDAuTSmUVxewkr1YWtN" +
       "bh6v2IGBL1pfa+r36l4rH9oDABbQbUv1C8pI3mLiSrHw642/uuv5lY/EDel/" +
       "OFlfTQ4VAAA=");
}
