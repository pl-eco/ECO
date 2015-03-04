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
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVYb2wcxRWfO/93HPvi4CSExE6MgxoHbpuqVEoNoeZwiMOB" +
                                                    "T3ESCVM45vbmfBvv7Wx2Z+2LqQtEah1FakRbQ0MF/oCCKDQQVDWiCCHlSwuI" +
                                                    "fgFVRXzgj/gCguZDPpSi0pa+N7N3u7d3dpNPtTRzs7PvzXtv3nu/99bnLpEW" +
                                                    "1yG7bG4enza5SLKySB41b06K4zZzkwfSN2eo47J8yqSuewj2svrgyz1ffv1Y" +
                                                    "MREnrVNkPbUsLqgwuOUeZC43Z1k+TXqC3TGTlVxBEumjdJZqnjBMLW24YiRN" +
                                                    "1oRYBRlKV1TQQAUNVNCkCtpoQAVMa5nllVLIQS3hHiM/JrE0abV1VE+Q7bWH" +
                                                    "2NShJf+YjLQATmjH5yNglGQuO2Rb1XZlc53Bj+/Sln71QOJ3TaRnivQY1iSq" +
                                                    "o4MSAoRMka4SK+WY447m8yw/RdZZjOUnmWNQ05iXek+RXteYtqjwHFa9JNz0" +
                                                    "bOZImcHNdelom+PpgjtV8woGM/OVp5aCSafB1g2BrcrCfbgPBnYaoJhToDqr" +
                                                    "sDTPGFZekIEoR9XGobuAAFjbSkwUeVVUs0Vhg/Qq35nUmtYmhWNY00Dawj2Q" +
                                                    "IsjmFQ/Fu7apPkOnWVaQTVG6jHoFVB3yIpBFkL4omTwJvLQ54qWQfy7dc8vp" +
                                                    "h6z9VlzqnGe6ifq3A1N/hOkgKzCHWTpTjF3D6SfohtdPxgkB4r4IsaJ55UeX" +
                                                    "f3Bj/8U3Fc11DWgmckeZLrL62Vz3O1tSO/c0oRrtNncNdH6N5TL8M/6bkbIN" +
                                                    "mbeheiK+TFZeXjz4p3sfeYF9ESed46RV56ZXgjhap/OSbZjMuZNZzKGC5cdJ" +
                                                    "B7PyKfl+nLTBOm1YTO1OFAouE+Ok2ZRbrVw+wxUV4Ai8ojZYG1aBV9Y2FUW5" +
                                                    "LtuEkDYYZDeMLqL+5K8gx7QiLzGN6tQyLK5B7DLq6EWN6TzrMJtrY6kJLQe3" +
                                                    "XCxRZ8bVXM8qmHwuq3uu4CXNdXSNO9OVbU3nDtPcIs0zR5ssGtbxO4xCwXPZ" +
                                                    "pNxKYujZ/w+hZbyJxFwsBk7aEoUIE7JrPzeBNqsvebePXX4p+3a8mjL+HQoy" +
                                                    "DDKTvswkykwqmcl6mSQWk6KuQdkqFsCTM4AJgJZdOyfvP/DgycEmCEJ7rhnc" +
                                                    "gKSDYLyv0JjOUwFwjEt41CF6Nz1z32Lyq+duU9GrrYzyDbnJxTNzjx55+Ntx" +
                                                    "Eq+FazQQtjqRPYMgWwXToWiaNjq3Z/GzL88/scCDhK3Bfx9H6jkRBwajrnC4" +
                                                    "zvKArMHxw9vohezrC0Nx0gzgAoAqKCQAYFV/VEYNHoxUsBVtaQGDC9wpURNf" +
                                                    "VQCxUxQdPhfsyBjplut14JQ1mCBbYST8jJG/+Ha9jfM1KqbQyxErJHbve/Xi" +
                                                    "kxd+vWtPPAzzPaHCOcmEAo11QZAcchiD/Q/OZH75+KXF+2SEAMX1jQQM4ZwC" +
                                                    "CAGXwbX+5M1j73/04dm/xIOoElBLvZxp6GU444ZACgCMCSCHvh86bJV43igY" +
                                                    "NGcyDM5/9ezYfeFvpxPKmybsVILhxv99QLB/7e3kkbcf+Ee/PCamY4ELLA/I" +
                                                    "1AWsD04edRx6HPUoP/ru1iffoE8D/gLmucY8kzBGpGVEXr0mXTUs52Tk3W6c" +
                                                    "ttl178pyZ5N8ioPonSsn0T6s06Hk++eEmTvxyVfSorr0aVCeIvxT2rmnNqf2" +
                                                    "fiH5gzhG7oFyPShBTxPwfueF0t/jg61/jJO2KZLQ/YbpCDU9jJYpaBLcShcF" +
                                                    "TVXN+9qCr6rbSDVPt0RzKCQ2mkEBGMIaqXHdGUkaWVU2wljrJ83aaNLEiFzs" +
                                                    "kSyDct6B07cqMdtmO8YsxW4MtARQlSR9gmwMw69Rgm4D45DLG0woT3+3Xo9u" +
                                                    "X4/uFfQYxWkERDmsYK4eEBnHKEE5n/X7DW2h96OZpz57UaFx1PsRYnZy6dQ3" +
                                                    "ydNL8VAHd31dExXmUV2cvN61yqxv4C8G4z840BzcUFW8N+W3EtuqvYRtY9Zv" +
                                                    "X00tKWLfp+cXXvvNwqIyo7e2gRmD/vzFv/77z8kzH7/VoCJCfHAqlAOkpkOh" +
                                                    "9Io1dJysm6pIon5bV2oEpW5nTywt5yee3R33s/ouQToEt28y2SwzQ6Ka8aSa" +
                                                    "+nm3bH2DDDr1/G9fEe/s+r6ycnhlJ0cZ3zjx+eZDe4sPXkXVHIjYFD3y+bvP" +
                                                    "vXXnDfov4qSpmoh13Xwt00ht+nU6DD4/rEM1SdhfDf71GBTXwujzg7+vYeUK" +
                                                    "HBZgaNxHRt91/XWuk6Yy+FhAkK6QbQiTTarf0cy4FPPDVVA6h9O9UKY8Ow8x" +
                                                    "K2nuwGm/wukDgAY5zk1GrXoolxuHq0bjINf5o7K+MqNjtfG6tWG8wocSfioy" +
                                                    "eczMKkbJBrwoSOc0E35TiDu3XZkFW2AM+BYMXK0F+GhKKm8VBedwggZqDSh4" +
                                                    "EAzDALsyDWVLtBfGrb6Gt15tYEkNpTBJurCKmg/jNC8A/XQqIOAyRS78vIvE" +
                                                    "SPMsN/INaj3gYn1rjg3Hprp/EqgPW/2l5Z72jcuH35PNZvXjswO+AAueaYYL" +
                                                    "YGjdakPtMKTOHaocKjBcbAR96pMBQl4tpNI/VfSnBElE6cE4/AmT/Qw8FyKD" +
                                                    "FPFXYaLHBGkCIlz+3K4EdkL2WdgIJFUjUCYhEMVWM/xU03ciTsp/wFQwzVP/" +
                                                    "gsnq55cP3PPQ5e89KwGyRTfp/Dye0p4mbarlruLi9hVPq5zVun/n190vd+yo" +
                                                    "4H03Tr1+nx3RbaBxOzpWsoVsIOf/sPH3tzy3/KHsh/8LYhjpeBkTAAA=");
}
