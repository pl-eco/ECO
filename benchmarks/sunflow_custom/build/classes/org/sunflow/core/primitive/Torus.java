package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Instance;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.MathUtils;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Solvers;
import org.sunflow.math.Vector3;
public class Torus implements PrimitiveList {
    private float ri2;
    private float ro2;
    private float ri;
    private float ro;
    public Torus() { super();
                     ri = 0.25F;
                     ro = 1;
                     ri2 = ri * ri;
                     ro2 = ro * ro; }
    public boolean update(ParameterList pl, SunflowAPI api) { ri = pl.getFloat(
                                                                        "radiusInner",
                                                                        ri);
                                                              ro =
                                                                pl.
                                                                  getFloat(
                                                                    "radiusOuter",
                                                                    ro);
                                                              ri2 =
                                                                ri *
                                                                  ri;
                                                              ro2 =
                                                                ro *
                                                                  ro;
                                                              return true;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       -ro -
                                                         ri,
                                                       -ro -
                                                         ri,
                                                       -ri);
                                                     bounds.
                                                       include(
                                                         ro +
                                                           ri,
                                                         ro +
                                                           ri,
                                                         ri);
                                                     if (o2w !=
                                                           null)
                                                         bounds =
                                                           o2w.
                                                             transform(
                                                               bounds);
                                                     return bounds;
    }
    public float getPrimitiveBound(int primID, int i) { switch (i) {
                                                            case 0:
                                                            case 2:
                                                                return -ro -
                                                                  ri;
                                                            case 1:
                                                            case 3:
                                                                return ro +
                                                                  ri;
                                                            case 4:
                                                                return -ri;
                                                            case 5:
                                                                return ri;
                                                            default:
                                                                return 0;
                                                        }
    }
    public int getNumPrimitives() { return 1; }
    public void prepareShadingState(ShadingState state) {
        state.
          init(
            );
        state.
          getRay(
            ).
          getPoint(
            state.
              getPoint(
                ));
        Instance parent =
          state.
          getInstance(
            );
        Point3 p =
          parent.
          transformWorldToObject(
            state.
              getPoint(
                ));
        float deriv =
          p.
            x *
          p.
            x +
          p.
            y *
          p.
            y +
          p.
            z *
          p.
            z -
          ri2 -
          ro2;
        state.
          getNormal(
            ).
          set(
            p.
              x *
              deriv,
            p.
              y *
              deriv,
            p.
              z *
              deriv +
              2 *
              ro2 *
              p.
                z);
        state.
          getNormal(
            ).
          normalize(
            );
        double phi =
          Math.
          asin(
            MathUtils.
              clamp(
                p.
                  z /
                  ri,
                -1,
                1));
        double theta =
          Math.
          atan2(
            p.
              y,
            p.
              x);
        if (theta <
              0)
            theta +=
              2 *
                Math.
                  PI;
        state.
          getUV(
            ).
          x =
          (float)
            (theta /
               (2 *
                  Math.
                    PI));
        state.
          getUV(
            ).
          y =
          (float)
            ((phi +
                Math.
                  PI /
                2) /
               Math.
                 PI);
        state.
          setShader(
            parent.
              getShader(
                0));
        state.
          setModifier(
            parent.
              getModifier(
                0));
        Vector3 worldNormal =
          parent.
          transformNormalObjectToWorld(
            state.
              getNormal(
                ));
        state.
          getNormal(
            ).
          set(
            worldNormal);
        state.
          getNormal(
            ).
          normalize(
            );
        state.
          getGeoNormal(
            ).
          set(
            state.
              getNormal(
                ));
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                state.
                  getNormal(
                    )));
    }
    public void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        float rd2x =
          r.
            dx *
          r.
            dx;
        float rd2y =
          r.
            dy *
          r.
            dy;
        float rd2z =
          r.
            dz *
          r.
            dz;
        float ro2x =
          r.
            ox *
          r.
            ox;
        float ro2y =
          r.
            oy *
          r.
            oy;
        float ro2z =
          r.
            oz *
          r.
            oz;
        double alpha =
          rd2x +
          rd2y +
          rd2z;
        double beta =
          2 *
          (r.
             ox *
             r.
               dx +
             r.
               oy *
             r.
               dy +
             r.
               oz *
             r.
               dz);
        double gamma =
          ro2x +
          ro2y +
          ro2z -
          ri2 -
          ro2;
        double A =
          alpha *
          alpha;
        double B =
          2 *
          alpha *
          beta;
        double C =
          beta *
          beta +
          2 *
          alpha *
          gamma +
          4 *
          ro2 *
          rd2z;
        double D =
          2 *
          beta *
          gamma +
          8 *
          ro2 *
          r.
            oz *
          r.
            dz;
        double E =
          gamma *
          gamma +
          4 *
          ro2 *
          ro2z -
          4 *
          ro2 *
          ri2;
        double[] t =
          Solvers.
          solveQuartic(
            A,
            B,
            C,
            D,
            E);
        if (t !=
              null) {
            if (t[0] >=
                  r.
                  getMax(
                    ) ||
                  t[t.
                      length -
                      1] <=
                  r.
                  getMin(
                    ))
                return;
            for (int i =
                   0;
                 i <
                   t.
                     length;
                 i++) {
                if (t[i] >
                      r.
                      getMin(
                        )) {
                    r.
                      setMax(
                        (float)
                          t[i]);
                    state.
                      setIntersection(
                        0,
                        0,
                        0);
                    return;
                }
            }
        }
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169362904000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYXWxcRxWeXf87rr3+ieO6ie24TkTisJdELSi4aohXduN0" +
                                                    "E1uxE1RX7Xp8d9a+8d17b++dtTdODGkiSNRKESpuSWmxBEoVUvJTUKMUoUp5" +
                                                    "gbYqL0UIxAMt4oWKkoc8UCoKlHPm/u7d9TqNwNKMZ2fOOXO+OX8z9/ItUmWZ" +
                                                    "pN/Q1WMzqs7jLM/jR9UH4/yYwaz4/uSDY9S0WDqhUsuagLmU3Pta08effmc2" +
                                                    "FiXVk6SVaprOKVd0zTrELF2dZ+kkafJnh1SWtTiJJY/SeSrluKJKScXiA0my" +
                                                    "LsDKSV/SVUECFSRQQRIqSHt9KmC6h2m5bAI5qMatp8g3SCRJqg0Z1eNkc6EQ" +
                                                    "g5o064gZEwhAQi3+PgKgBHPeJD0edhtzEeDn+6Xl7z0Z+1kFaZokTYo2jurI" +
                                                    "oASHTSZJQ5Zlp5lp7U2nWXqSNGuMpceZqVBVWRR6T5IWS5nRKM+ZzDsknMwZ" +
                                                    "zBR7+ifXICM2Mydz3fTgZRSmpt1fVRmVzgDWdh+rjXAY5wFgvQKKmRkqM5el" +
                                                    "ck7R0px0hzk8jH2PAgGw1mQZn9W9rSo1ChOkxbadSrUZaZybijYDpFV6Dnbh" +
                                                    "pHNVoXjWBpXn6AxLcdIRphuzl4CqThwEsnCyPkwmJIGVOkNWCtjn1sGHzh3X" +
                                                    "9mlRoXOaySrqXwtMXSGmQyzDTKbJzGZs2J58gba/eTZKCBCvDxHbNDdO3P7a" +
                                                    "jq6bb9s095WgGZ0+ymSeki9MN763MbFtdwWqUWvoloLGL0Au3H/MWRnIGxB5" +
                                                    "7Z5EXIy7izcP/eqxk6+yj6KkfoRUy7qay4IfNct61lBUZj7CNGZSztIjpI5p" +
                                                    "6YRYHyE1ME4qGrNnRzMZi/ERUqmKqWpd/IYjyoAIPKIaGCtaRnfHBuWzYpw3" +
                                                    "CCE10Eg/tBZi/4n/nKSkwxa4u0RlqimaLoHzMmrKsxKT9dQ0nO5slppzliTn" +
                                                    "LK5nJSunZVR9QbJMWdLNGe+3rJtMMkwlC3jnmTShmzkrjo5m/P+3yCPK2EIk" +
                                                    "AgbYGA5/FSJnn66mmZmSl3ODQ7evpt6NeuHgnA8nPbBT3NkpjjvFvZ3iYicS" +
                                                    "iYgN2nBH27pgmzmIcsh/DdvGn9g/dba3AtzKWKiEg0XSXsDnqDEk6wk/FYyI" +
                                                    "hCeDP3b86PEz8U8u7rH9UVo9b5fkJjfPLzx95JtfipJoYQJGWDBVj+xjmDa9" +
                                                    "9NgXDrxScpvOfPjxtReWdD8ECzK6kxmKOTGye8MGMHWZpSFX+uK399DrqTeX" +
                                                    "+qKkEtIFpEhOwaUh+3SF9yiI8AE3WyKWKgCc0c0sVXHJTXH1fNbUF/wZ4RmN" +
                                                    "YtwMRlmHLh+D1u7EgPiPq60G9m22J6GVQyhENh7++c0Xr3+/f3c0mLibAqVw" +
                                                    "nHE7DTT7TjJhMgbzfzw/9t3nb515XHgIUNxfaoM+7BOQFMBkcKzfevupP3zw" +
                                                    "/oXfRn2v4lAdc9OqIudBxlZ/F0gZKqQttH3fYS2rp5WMQqdVhs75r6YtO6//" +
                                                    "7VzMtqYKM64z7FhbgD9/7yA5+e6T/+gSYiIyliwfuU9mH0CrL3mvadJjqEf+" +
                                                    "6d9sevEt+gPIqJDFLGWRicREBDIijl4Sptou+nhobSd2PUbRWl7MdIhflbD1" +
                                                    "ttWDaBgrbyD4/jmqTp/68ycCUVH4lCg4If5J6fLLnYmHPxL8vh8jd3e+OBXB" +
                                                    "LcXn3fVq9u/R3upfRknNJInJzhXoCFVz6C2TUPYt914E16SC9cISbterAS9O" +
                                                    "N4ZjKLBtOIL8FAhjpMZxfShoGvCU74PW6gRNazhoIkQMdguWXtFvwe4Lrs/W" +
                                                    "QC6dp3i/IhWmsqu8kcbcvGtXbGmp5YO5lz+8YmfIsEVCxOzs8jOfxc8tRwP3" +
                                                    "pPuLripBHvuuJCDfY0P+DP4i0P6DDaHihF0rWxJOwe7xKrZhYCRuLqeW2GL4" +
                                                    "L9eWfvHjpTM2jJbCa8IQ3IKv/O7fv46f/9M7JWoT2EynImhjtvs/8PmNM4Ld" +
                                                    "AJ6/vguHg6uLuxdamyOubRVxSUdc1FT+B9JGPWl6QJowSl8guiNivB7qRFGx" +
                                                    "9g5cJBqwyKbVLpjCGhdOLa+kR1/ZGXVyyzAndVw3vqiyeaYGdqxBSQVV/IC4" +
                                                    "Uvtx/Myln9zg7/V/1bbr9tXdOsz41qm/dk48PDv1OWp3dwhTWOSlA5ffeWSr" +
                                                    "/FyUVHjpoOiVUMg0UJgE6k0GzxptoiAVdHkGbXUN2uUYtKtk/fTt5mfyqDjP" +
                                                    "aBkLIlQGjxC0oEvWHiQbt//vHRsR20yVqRUZ7J6AYpkz0hClgmYPdgm7WgxB" +
                                                    "TprWdZVRrbigiInHPNAbcHILtK0O6K13DDpS6LYdQTRZuJ3HD1B4gOUfEBL0" +
                                                    "MnjEJFizcYbxr+ummh7Uc1racgVvLBIs1uFpN6jn1wSIjfRCizsA43dl1dD5" +
                                                    "VsC7FYcnBPfxMuBOYgeloRnAeXEsAIhksKb+TTjZDW2Po/+eO9Y/qMa3y6yd" +
                                                    "xe40JzFQ8WAu62kpiE+sqaG4d34FWsLRMHG3LrSpKG7GZykaGr9iMCHmXBkc" +
                                                    "z2H3LIdtTQaXTBZkLmXEynldSd8ZvIPQphx4U3cMr0JIrHDhtRXBO0SPiTN2" +
                                                    "KXqLKEbw+4hlXz79U3ipzCn8ELvzUM4Vl9UzKK4srwkYUyDZAe20A/j0XXnc" +
                                                    "xTJrl7C7AJYCjxukcJOZKXS68RIXYbgpiKcqXsA7ij6D2Z9u5KsrTbUbVg7/" +
                                                    "Xjy+vM8rdUlSm8mpavBCGBhXg79kFKFYnX09tKvzVU46V384Q1E1CpS+YnP9" +
                                                    "FAIpzAW+hv+CZK9zsi5ABinbGQWJbkCmASIcvmG4ThITrw+8Hsft63GeBIo6" +
                                                    "cVKi+6vgNYZ1W3xodGtszv7UmJKvrew/ePz2l18RBbtKVuniIkqpTZIa+yHq" +
                                                    "1enNq0pzZVXv2/Zp42t1W9z7RyN2Lc7rM6Rbd+lH2lDW4OJZtfjGhtcfurjy" +
                                                    "vngl/hdZXrfPARYAAA==");
}
