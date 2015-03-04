package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class ShinyDiffuseShader implements Shader {
    private Color diff;
    private float refl;
    public ShinyDiffuseShader() { super();
                                  diff = Color.GRAY;
                                  refl = 0.5F; }
    public boolean update(ParameterList pl, SunflowAPI api) { diff = pl.getColor(
                                                                          "diffuse",
                                                                          diff);
                                                              refl =
                                                                pl.
                                                                  getFloat(
                                                                    "shiny",
                                                                    refl);
                                                              return true;
    }
    public Color getDiffuse(ShadingState state) { return diff;
    }
    public Color getRadiance(ShadingState state) { state.
                                                     faceforward(
                                                       );
                                                   state.
                                                     initLightSamples(
                                                       );
                                                   state.
                                                     initCausticSamples(
                                                       );
                                                   Color d =
                                                     getDiffuse(
                                                       state);
                                                   Color lr =
                                                     state.
                                                     diffuse(
                                                       d);
                                                   if (!state.
                                                         includeSpecular(
                                                           ))
                                                       return lr;
                                                   float cos =
                                                     state.
                                                     getCosND(
                                                       );
                                                   float dn =
                                                     2 *
                                                     cos;
                                                   Vector3 refDir =
                                                     new Vector3(
                                                     );
                                                   refDir.
                                                     x = dn *
                                                           state.
                                                             getNormal(
                                                               ).
                                                             x +
                                                           state.
                                                             getRay(
                                                               ).
                                                             getDirection(
                                                               ).
                                                             x;
                                                   refDir.
                                                     y = dn *
                                                           state.
                                                             getNormal(
                                                               ).
                                                             y +
                                                           state.
                                                             getRay(
                                                               ).
                                                             getDirection(
                                                               ).
                                                             y;
                                                   refDir.
                                                     z = dn *
                                                           state.
                                                             getNormal(
                                                               ).
                                                             z +
                                                           state.
                                                             getRay(
                                                               ).
                                                             getDirection(
                                                               ).
                                                             z;
                                                   Ray refRay =
                                                     new Ray(
                                                     state.
                                                       getPoint(
                                                         ),
                                                     refDir);
                                                   cos = 1 -
                                                           cos;
                                                   float cos2 =
                                                     cos *
                                                     cos;
                                                   float cos5 =
                                                     cos2 *
                                                     cos2 *
                                                     cos;
                                                   Color ret =
                                                     Color.
                                                     white(
                                                       );
                                                   Color r =
                                                     d.
                                                     copy(
                                                       ).
                                                     mul(
                                                       refl);
                                                   ret.sub(
                                                         r);
                                                   ret.mul(
                                                         cos5);
                                                   ret.add(
                                                         r);
                                                   return lr.
                                                     add(
                                                       ret.
                                                         mul(
                                                           state.
                                                             traceReflection(
                                                               refRay,
                                                               0)));
    }
    public void scatterPhoton(ShadingState state, Color power) {
        Color diffuse;
        state.
          faceforward(
            );
        diffuse =
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
            diffuse);
        float d =
          diffuse.
          getAverage(
            );
        float r =
          d *
          refl;
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              d) {
            power.
              mul(
                diffuse).
              mul(
                1.0F /
                  d);
            OrthoNormalBasis onb =
              state.
              getBasis(
                );
            double u =
              2 *
              Math.
                PI *
              rnd /
              d;
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
                  1.0 -
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
                  d +
                  r) {
                float cos =
                  -Vector3.
                  dot(
                    state.
                      getNormal(
                        ),
                    state.
                      getRay(
                        ).
                      getDirection(
                        ));
                power.
                  mul(
                    diffuse).
                  mul(
                    1.0F /
                      d);
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
                      getNormal(
                        ).
                      x +
                    state.
                      getRay(
                        ).
                      getDirection(
                        ).
                      x;
                dir.
                  y =
                  dn *
                    state.
                      getNormal(
                        ).
                      y +
                    state.
                      getRay(
                        ).
                      getDirection(
                        ).
                      y;
                dir.
                  z =
                  dn *
                    state.
                      getNormal(
                        ).
                      z +
                    state.
                      getRay(
                        ).
                      getDirection(
                        ).
                      z;
                state.
                  traceReflectionPhoton(
                    new Ray(
                      state.
                        getPoint(
                          ),
                      dir),
                    power);
            }
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYXWwc1RW+O/53HHvj4CSExE6MgxoHdhpUKgVDwBiHOCxk" +
                                                    "FSeRMG3M9cxd78R3Z4aZu/bGYH4itY4iNaKtgYDADyiIv0BQ1YhWFVJeWkD0" +
                                                    "haoq4oEf8QIC8pAHKOL/nHtnd2Zn127yUEv37p0759xzzj3nfOeMT58nDb5H" +
                                                    "trkOPzLJHZFiRZE6zK9LiSMu81N70tdlqOczc4hT398Pe+NG76sdX337SC6p" +
                                                    "kcYxspratiOosBzb38d8h08zM006wt1hzvK+IMn0YTpN9YKwuJ62fDGQJisi" +
                                                    "rIL0pUsq6KCCDiroUgV9MKQCppXMLuSHkIPawr+XPEASadLoGqieIJsrD3Gp" +
                                                    "R/PBMRlpAZzQjM8HwSjJXPTIprLtyuYqgx/dpi88fij5pzrSMUY6LHsU1TFA" +
                                                    "CQFCxkhbnuUnmOcPmiYzx8gqmzFzlHkW5das1HuMdPrWpE1FwWPlS8LNgss8" +
                                                    "KTO8uTYDbfMKhnC8snlZi3Gz9NSQ5XQSbF0T2qos3IX7YGCrBYp5WWqwEkv9" +
                                                    "lGWbgvTEOco29t0OBMDalGci55RF1dsUNkin8h2n9qQ+KjzLngTSBqcAUgRZ" +
                                                    "v+SheNcuNaboJBsXZF2cLqNeAVWLvAhkEaQrTiZPAi+tj3kp4p/zd95w4j57" +
                                                    "t61JnU1mcNS/GZi6Y0z7WJZ5zDaYYmzrTz9G17x+TCMEiLtixIrmtfsv3Hx1" +
                                                    "97k3Fc0VNWj2Thxmhhg3Tk20v7NhaOuOOlSj2XV8C51fYbkM/0zwZqDoQuat" +
                                                    "KZ+IL1Oll+f2/eOuh15kn2ukdYQ0Gg4v5CGOVhlO3rU4825jNvOoYOYIaWG2" +
                                                    "OSTfj5AmWKctm6ndvdmsz8QIqedyq9GRz3BFWTgCr6gJ1paddUprl4qcXBdd" +
                                                    "QkgTDLIdRhtRf/JXkEN6zskznRrUtmxHh9hl1DNyOjMc3ad5l4PX/IKd5c6M" +
                                                    "7nuG7niT5WfD8Zju56jJPH00Z9lHbrWy2YLPRuVWCuPM/b9LKKKNyZlEAq5/" +
                                                    "Qzz5OeTNbocD7bixULhl+MIr429r5WQIbkeQfpCZCmSmUGZKyUxVyySJhBR1" +
                                                    "GcpWXgYfTUG2Aw62bR399Z57jvXWQXi5M/VwwUjaC5YGCg0bzlAICSMS+AyI" +
                                                    "y3XP3D2f+vq5m1Rc6kvjd01ucu7kzMMHH/y5RrRKIEYDYasV2TMIn2WY7Isn" +
                                                    "YK1zO+Y//erMY3NOmIoVyB4gRDUnZnhv3BWeYzATMDM8vn8TPTv++lyfRuoB" +
                                                    "NgAqBYXQBhTqjsuoyPSBEmqiLQ1gcNbx8pTjqxLUtYqc58yEOzJG2uV6FThl" +
                                                    "BYb+RhjJIBfkL75d7eJ8mYop9HLMConKu/567omzT27boUUBvCNSEkeZUHCw" +
                                                    "KgyS/R5jsP/+ycwfHz0/f7eMEKC4spaAPpyHABzAZXCtv3nz3vc+/ODUv7Uw" +
                                                    "qgRUycIEt4winHFVKAWggwN8oe/7Dth5x7SyFp3gDIPzu44t289+cSKpvMlh" +
                                                    "pxQMV//vA8L9y28hD7196L/d8piEgaUrtDwkUxewOjx50PPoEdSj+PC/Nj7x" +
                                                    "Bn0akBXQzLdmmQQoIi0j8up16ap+Oadi77bjtMmteleUO+vkkwaity6dRLuw" +
                                                    "AkeS75u9fOLox19Li6rSp0bhifGP6aefWj+083PJH8YxcvcUq0EJupWQ99oX" +
                                                    "819qvY1/10jTGEkaQSt0kPICRssYlH+/1B9Bu1TxvrKUq7o1UM7TDfEcioiN" +
                                                    "Z1AIhrBGaly3xpJG1ou1MFYGSbMynjQJIhc7JEuvnLfg9LNSzDa5njVNsc8C" +
                                                    "LQFUJUmXIGuj8GvloY/AOHTkDSaVp39RrUd7oEf7EnoM4jQAojyW5csHRMaz" +
                                                    "8lCop4NOQp/r/HDqqU9fVmgc936MmB1bOP5j6sSCFunNrqxqj6I8qj+T17tS" +
                                                    "mfUj/CVg/IADzcENVZ87h4ImYVO5S3BdzPrNy6klRez65Mzc356fm1dmdFa2" +
                                                    "JsPQeb/8n+//mTr50Vs1KiLEh0OFcoDUtC+SXomajpN1UxVJ1G/jUi2e1O3U" +
                                                    "0YVFc++z27Ugq28XpEU47jWcTTMeEVWPJ1XUzztkUxtm0PEXXnpNvLPtemVl" +
                                                    "/9JOjjO+cfSz9ft35u65hKrZE7MpfuQLd5x+67arjD9opK6ciFV9eiXTQGX6" +
                                                    "tXoMPizs/RVJ2F0O/tUYFJfD6AqCv6tm5QodFmKoFiBj4LruKtdJUxl8BiBI" +
                                                    "l8jWRMlG1e9gZkSK+dUyKD2B011QpgquCTEraW7FabfC6T2ABhOOwxm1q6Fc" +
                                                    "bhwoG42DXBGM0vrijE5UxuvGmvEKn0D4EcjkMVPLGCVb65wgrZNMBE0h7tx0" +
                                                    "cRZsgNETWNBzqRbgI5dUhWUUnMEJGqgVoOA+MAwD7OI0lC3RThg3BhreeKmB" +
                                                    "JTWUwiTp3DJqPojTrAD0M6iAgMvkHBHkXSxG6qcdy6xR6wEXq1tzbDjWVX3+" +
                                                    "q09W45XFjua1iwfelc1m+bOyBb7tsgXOowUwsm50oXZYUucWVQ4VGM7Xgj71" +
                                                    "yQAhrxZS6d8q+uOCJOP0YBz+RMl+B56LkEGKBKso0SOC1AERLn/vlgI7Kfss" +
                                                    "bARSqhEokgiIYqsZfaroOxEn5b9WSphWUP9cGTfOLO65874Lv3xWAmSDwens" +
                                                    "LJ7SnCZNquUu4+LmJU8rndW4e+u37a+2bCnhfTtOnUGfHdOtp3Y7Opx3hWwg" +
                                                    "Z/+y9s83PLf4geyHfwKBZAK28xIAAA==");
}
