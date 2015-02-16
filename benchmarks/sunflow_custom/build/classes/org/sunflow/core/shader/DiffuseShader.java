package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class DiffuseShader implements Shader {
    private Color diff;
    public DiffuseShader() { super();
                             diff = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { diff = pl.getColor(
                                                                          "diffuse",
                                                                          diff);
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
                                                   return state.
                                                     diffuse(
                                                       getDiffuse(
                                                         state));
    }
    public void scatterPhoton(ShadingState state, Color power) {
        Color diffuse;
        if (Vector3.
              dot(
                state.
                  getNormal(
                    ),
                state.
                  getRay(
                    ).
                  getDirection(
                    )) >
              0.0) {
            state.
              getNormal(
                ).
              negate(
                );
            state.
              getGeoNormal(
                ).
              negate(
                );
        }
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
        float avg =
          diffuse.
          getAverage(
            );
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              avg) {
            power.
              mul(
                diffuse).
              mul(
                1.0F /
                  avg);
            OrthoNormalBasis onb =
              state.
              getBasis(
                );
            double u =
              2 *
              Math.
                PI *
              rnd /
              avg;
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
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1160859148000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YXWwcVxW+O/53nOzGaRI3TZzEtSvyww5BKlJwlZIap3HY" +
                                                    "NKs4icCFuHdn7nonnp2ZzNy1Ny6GJAI5ClKEwC0pKn6oUvWHtKlQoxahSnmB" +
                                                    "tiovRQjEAy3ihYqShzxQKgqUc+6d/12b5AVLc/fOvefcc84953znjK/eJG2e" +
                                                    "S3Y5tnlmyrR5ntV5/pR5f56fcZiXP1S4v0hdj+kjJvW8Y7A2qQ28kv3ok+9X" +
                                                    "cgppnyDrqGXZnHLDtryjzLPNGaYXSDZaHTVZ1eMkVzhFZ6ha44apFgyPDxfI" +
                                                    "qhgrJ4OFQAUVVFBBBVWooO6PqIBpNbNq1RHkoBb3TpNvkUyBtDsaqsfJ9uQh" +
                                                    "DnVp1T+mKCyAEzrx/QQYJZjrLtkW2i5tbjD4iV3q4o9O5n7WQrITJGtY46iO" +
                                                    "BkpwEDJBeqqsWmKut1/XmT5B1lqM6ePMNahpzAm9J0ivZ0xZlNdcFl4SLtYc" +
                                                    "5gqZ0c31aGibW9O47YbmlQ1m6sFbW9mkU2DrhshWaeEBXAcDuw1QzC1TjQUs" +
                                                    "rdOGpXOyNc0R2jj4FSAA1o4q4xU7FNVqUVggvdJ3JrWm1HHuGtYUkLbZNZDC" +
                                                    "yaZlD8W7dqg2TafYJCd9abqi3AKqLnERyMLJ+jSZOAm8tCnlpZh/bj7ywKXH" +
                                                    "rYOWInTWmWai/p3A1J9iOsrKzGWWxiRjz87Ck3TDGxcUQoB4fYpY0rz2zVtf" +
                                                    "2t1/4y1Jc08TmiOlU0zjk9qV0pp3N4/s2NuCanQ6tmeg8xOWi/Av+jvDdQcy" +
                                                    "b0N4Im7mg80bR3/1tbMvsg8V0j1G2jXbrFUhjtZqdtUxTOY+zCzmUs70MdLF" +
                                                    "LH1E7I+RDpgXDIvJ1SPlssf4GGk1xVK7Ld7hispwBF5RB8wNq2wHc4fyipjX" +
                                                    "HUJIBzxkNzw9RP6JX0509bgH4a5SjVqGZasQvIy6WkVlmj1ZgtutVKk77ala" +
                                                    "zeN2VfVqVtm0Z1XP1VTbnQrfNdtlqlehOnPVLxvlcs1j4+Itj9Hm/J/k1NHe" +
                                                    "3GwmA67YnAYCE3LooG0C7aS2WHto9NbLk+8oYWL4N8XJEIjL++LyKC4vxeUT" +
                                                    "4kgmI6TchWKls8FV05D0AIc9O8a/ceixCwMtEGXObCvcM5IOgKW+LqOaPRIh" +
                                                    "w5jAPw3Cs++ZRxfyHz/3oAxPdXkYb8pNblyePXfi259TiJLEY7QNlrqRvYgo" +
                                                    "GqLlYDoPm52bXfjgo2tPzttRRiYA3geKRk5M9IG0F1xbYzpAZ3T8zm30+uQb" +
                                                    "84MKaQX0AMTkFCIcwKg/LSOR8MMBeKItbWBw2Xar1MStAPG6ecW1Z6MVER5r" +
                                                    "xHwtOGUVZkAfPFk/JcQv7q5zcLxLhhN6OWWFAOcDP7/x1PUf79qrxHE8G6uM" +
                                                    "44xLVFgbBckxlzFY/+Pl4g+fuLnwqIgQoLi3mYBBHEcAI8BlcK3ffev0H95/" +
                                                    "78pvlSiqOBTLWsk0tDqccV8kBRDEBBRD3w8et6q2bpQNWjIZBue/skN7rv/t" +
                                                    "Uk5604SVIBh2/+8DovW7HyJn3zn5j35xTEbDChZZHpHJC1gXnbzfdekZ1KN+" +
                                                    "7jdbnnqT/gQAFkDNM+aYwCkiLCPi6lXhqp1izKf29uCwzWnYq4uVvjDrdiyf" +
                                                    "RAewEMeS759HzNL5P38sLGpInyb1J8U/oV59etPIvg8FfxTHyL213ohH0LRE" +
                                                    "vJ9/sfp3ZaD9lwrpmCA5ze+ITlCzhtEyAV2AF7RJ0DUl9pMVXZav4TBPN6dz" +
                                                    "KCY2nUERDsIcqXHenUoaUTY2wrPaT5rV6aTJEDHZK1gGxDiEw2eCmO1wXGOG" +
                                                    "YrsFWgKoCpL1nGyMI69RhXYC49AWN5hzhB6DCe824xOILTEak2LLco2GaJKu" +
                                                    "nF9c0o88u0fibW+yeI9Cb/rS7/796/zlP73dpE50cdv5rMlmmBnTqRVFJnD+" +
                                                    "sOjBIk9ffOGnr/F3d31Rity5fHSmGd88/9dNx/ZVHrsDdN+aMj595AuHr779" +
                                                    "8H3aDxTSEgZMQ1uZZBpOhkm3y6APto4lgqU/DJZ1GBt3B5PgtwFhI89Gua6I" +
                                                    "+1QCH/c3+FiYyqBrRTAJyDbEycbl7/7imBBTXAFNTuBwGOC05ugQmSvjRtE1" +
                                                    "qtDWzfh9pzrf+/700x+8JD2aBokUMbuwePHT/KVFJdbJ39vQTMd5ZDcvtFwt" +
                                                    "L/ZT+MvA8x980ARckN1c74jfUm4Le0rHwTzYvpJaQsSBv1yb/8Xz8wuKfyWj" +
                                                    "kKgl2zYZtRpRViyMhX7Gh9zjV9Ogqt6enzPJXN7SNJfhIwU/05g4hq3gRwOH" +
                                                    "EifdU4z7/RquPHh7Fmz2n2B+Rxbga1lQ2SsoeBoHSLNVoOBRMAxz6vY0FN3K" +
                                                    "PniGfA2H7jSXhIZCmCCdXUHNMzhwDhGnUQ45VqzY3Iear+LwdandSUDwGdvQ" +
                                                    "m5Rh4E00zNgG9DV8m8vvSe3lpWznxqXjvxctYPjN1wUfXuWaacbLUmze7ris" +
                                                    "bAh1u2SRkjXiXLOKIHt4SHA5EfqelfTf4SSXpge78CdOtgBOi5FBdvizONFF" +
                                                    "TlqACKffc4KYzonuB8tzXpbnOomVDGwA42+JbhCxR/zfI0DwmvzPx6R2benQ" +
                                                    "I4/f+sKzohy0aSadm8NTOuGzXzbCYRXYvuxpwVntB3d8suaVrqEg9dfg0Ot3" +
                                                    "vyndtjZvEkerDhdt3dzrG1994Lml90SX+l8lL01hkBIAAA==");
}
