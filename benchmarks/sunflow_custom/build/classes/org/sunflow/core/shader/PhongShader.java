package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class PhongShader implements Shader {
    private Color diff;
    private Color spec;
    private float power;
    private int numRays;
    public PhongShader() { super();
                           diff = Color.GRAY;
                           spec = Color.GRAY;
                           power = 20;
                           numRays = 4; }
    public boolean update(ParameterList pl, SunflowAPI api) { diff = pl.getColor(
                                                                          "diffuse",
                                                                          diff);
                                                              spec =
                                                                pl.
                                                                  getColor(
                                                                    "specular",
                                                                    spec);
                                                              power =
                                                                pl.
                                                                  getFloat(
                                                                    "power",
                                                                    power);
                                                              numRays =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    numRays);
                                                              return true;
    }
    protected Color getDiffuse(ShadingState state) { return diff;
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
                                                         state)).
                                                     add(
                                                       state.
                                                         specularPhong(
                                                           spec,
                                                           power,
                                                           numRays));
    }
    public void scatterPhoton(ShadingState state, Color power) {
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
          spec.
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
                float dn =
                  2.0F *
                  state.
                  getCosND(
                    );
                Vector3 refDir =
                  new Vector3(
                  );
                refDir.
                  x =
                  dn *
                    state.
                      getNormal(
                        ).
                      x +
                    state.
                      getRay(
                        ).
                      dx;
                refDir.
                  y =
                  dn *
                    state.
                      getNormal(
                        ).
                      y +
                    state.
                      getRay(
                        ).
                      dy;
                refDir.
                  z =
                  dn *
                    state.
                      getNormal(
                        ).
                      z +
                    state.
                      getRay(
                        ).
                      dz;
                power.
                  mul(
                    spec).
                  mul(
                    1.0F /
                      avgS);
                OrthoNormalBasis onb =
                  state.
                  getBasis(
                    );
                double u =
                  2 *
                  Math.
                    PI *
                  (rnd -
                     avgD) /
                  avgS;
                double v =
                  state.
                  getRandom(
                    0,
                    1,
                    1);
                float s =
                  (float)
                    Math.
                    pow(
                      v,
                      1 /
                        (this.
                           power +
                           1));
                float s1 =
                  (float)
                    Math.
                    sqrt(
                      1 -
                        s *
                        s);
                Vector3 w =
                  new Vector3(
                  (float)
                    Math.
                    cos(
                      u) *
                    s1,
                  (float)
                    Math.
                    sin(
                      u) *
                    s1,
                  s);
                w =
                  onb.
                    transform(
                      w,
                      new Vector3(
                        ));
                state.
                  traceReflectionPhoton(
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
      1168806208000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfO/8523F8FydxTJo4sWOjxqG3TVUqpUZJjXES" +
                                                    "h0tytZNINWrM3O7c3cZ7u5vdOfvi1JSkapNSKarApEkV/KEKotBAUNWIVhVS" +
                                                    "vrSA6BcQAvEBqPqlCMiHfChFpS19b2bvdm/vzsCXWpq52Zk3896b997vvfG1" +
                                                    "W6TFdcgO2zJO5QyLJ1mJJ08Ydyf5KZu5yQOpu9PUcZk2ZlDXPQJzM+rA8/GP" +
                                                    "P/15PhElrdNkLTVNi1OuW6Y7yVzLmGNaisT92XGDFVxOEqkTdI4qRa4bSkp3" +
                                                    "+UiKrAps5WQwVRZBAREUEEERIiijPhVsWs3MYmEMd1CTuyfJQySSIq22iuJx" +
                                                    "0l99iE0dWvCOSQsN4IQ2/D4GSonNJYdsreguda5R+PEdytIvjid+20Ti0ySu" +
                                                    "m1MojgpCcGAyTToLrJBhjjuqaUybJmtMxrQp5ujU0BeE3NOk29VzJuVFh1Uu" +
                                                    "CSeLNnMET//mOlXUzSmq3HIq6mV1Zmjlr5asQXOga4+vq9RwL86Dgh06COZk" +
                                                    "qcrKW5pndVPjZEt4R0XHwfuBALbGCoznrQqrZpPCBOmWtjOomVOmuKObOSBt" +
                                                    "sYrAhZONDQ/Fu7apOktzbIaT3jBdWi4BVbu4CNzCyfowmTgJrLQxZKWAfW4d" +
                                                    "uufCaXO/GRUya0w1UP422NQX2jTJssxhpsrkxs7h1EXa8+L5KCFAvD5ELGle" +
                                                    "+MHt79zVd/NlSfOVOjSHMyeYymfUq5mu1zaNbd/VhGK02Zaro/GrNBfun/ZW" +
                                                    "Rko2RF5P5URcTJYXb07++XsPP8M+jJKOCdKqWkaxAH60RrUKtm4wZx8zmUM5" +
                                                    "0yZIOzO1MbE+QWIwTukmk7OHs1mX8QnSbIipVkt8wxVl4Qi8ohiMdTNrlcc2" +
                                                    "5XkxLtmEkBg0Mgytk8g/8ctJRjnqgrsrVKWmbloKOC+jjppXmGrNZOB28wXq" +
                                                    "zLqKWnS5VVDcopk1rHnFdVTFcnKVb9VymOLmqcYcJZ23zNyUGCfR1+z/C5cS" +
                                                    "6pqYj0TADJvCIGBA/Oy3DKCdUZeK947ffm7m1WglKLxb4mQbMEt6zJLILCmZ" +
                                                    "JQPMSCQieKxDptLMYKRZCHcAws7tU98/8OD5gSbwL3u+GW4YSQdAS0+ScdUa" +
                                                    "8zFhQiCfCo7Z+6sHziU/eWqPdEylMYDX3U1uXpo/c+yHX4+SaDUSo2Yw1YHb" +
                                                    "04ifFZwcDEdgvXPj597/+PrFRcuPxSpo9yCidieG+EDYBo6lMg1A0z9+eCu9" +
                                                    "MfPi4mCUNANuAFZyCr4NMNQX5lEV6iNl2ERdWkDhrOUUqIFLZazr4HnHmvdn" +
                                                    "hHN0ifEaMMoq9P0eaN1eMIhfXF1rY79OOhNaOaSFgOW9f7h5+cYvd+yKBhE8" +
                                                    "HsiJU4xLPFjjO8kRhzGYf+dS+rHHb517QHgIUGyrx2AQ+zFABzAZXOuPXz75" +
                                                    "9nvvXn0j6nsVhzRZzBi6WoIz7vS5AHYYgF9o+8GjZsHS9KxOMwZD5/x3fGjn" +
                                                    "jY8uJKQ1DZgpO8Ndn3+AP3/HveThV4//s08cE1Exd/ma+2TyAtb6J486Dj2F" +
                                                    "cpTOvL758kv0CYBWgDNXX2ACoYjQjIirV4SphkWfDK3txG6rXbNWEjO94qsZ" +
                                                    "WG9vHER7MQUHgu9fh43M2b99IjSqCZ86mSe0f1q5dmXj2O4PxX7fj3H3llIt" +
                                                    "GkG54u/9xjOFf0QHWv8UJbFpklC9WugYNYroLdOQ/91ygQT1UtV6dS6XiWuk" +
                                                    "EqebwjEUYBuOIB8FYYzUOO4IBY1IGBugrfaCZnU4aCJEDHaJLQOiH8Luq2Wf" +
                                                    "jdmOPkex0AIpdUhguLqekw1B3NULUEigH1riBhPS0t+slaPLk6OrgRyj2I0A" +
                                                    "K9dmKo73ND6vF1rcOy/e4Lz7vPNabGsektmKHpZ29AKk/jmvNlEWu9+bvfL+" +
                                                    "sxLew+4UImbnlx75LHlhKRqo9rbVFFzBPbLiE/ZaLfX6DP4i0P6LDfXBCZnx" +
                                                    "u8e8smNrpe6wbYSR/pXEEiz2/v364h9/vXhOqtFdXeyMQy3/7Jv/+Uvy0l9f" +
                                                    "qZNbweEsyle2QMKzQKKBBb7rWSAGrCbpKVecsA+7+2X0H+SkCQpmyUXcx2AA" +
                                                    "FSJ1/U3keZnb8RY2NypNxQ1cPbu0rB1+cmfUA6NDnLRzy/6aweaYEQKgzVVp" +
                                                    "/6Aoxv3Af+Tp37zAX9vxbXmXw41dKbzxpbMfbDyyO//gl0j2W0I6hY98+uC1" +
                                                    "V/bdqT4aJU0V/Kh5X1RvGqlGjQ6HwYPIPFKFHX0VC69Fg97htfK4NuH6BvOh" +
                                                    "PyruM1o2XV+N6YSqDJ4vmFvKZD1Bsin5O5qeEGzYCsllFrsMZNeirUFk1HOw" +
                                                    "WMayDEbN2gwkJo5XlF6Hk33Q+j2l++tXGY3Qst12LA6YzrRSSOJItTtvruvO" +
                                                    "8LLDty0TXOZX0Pk0dic56cgxfh+gctEVNtzzuQpiI5ugDXkKDn1hq3oa4GdJ" +
                                                    "UJ1ZQcAfYfcQJ6tAwElQDP3vi0koCr3d0BRPQuXL+p2QUDAT/U9XEPNn2P2E" +
                                                    "AwSrlIM/wqOBe2EZcqHmOUvX6lQwoGLgpYH1U2/NvzPkE1x9bjnetmH56Fui" +
                                                    "dq48k9vhrZotGkYwnwfGrbbDsroQtl1mdwmSj9WDRPn0gVCQAyHto5L+IieJ" +
                                                    "MD1ohT9BssugT4AMQscbBYmuAGQDEQ6fsMsenRBlI9Y1SVnXlEgAXLFyDn5V" +
                                                    "ldGIn+JfRWWsK8p/Fs2o15cPHDp9+1tPCuBsUQ26sICntKVITL4gKnjZ3/C0" +
                                                    "8lmt+7d/2vV8+1A5D3Rh1x0I6IBsW+pX1+MFm4t6eOH3G353z1PL74ry/n9L" +
                                                    "DjDhwxMAAA==");
}
