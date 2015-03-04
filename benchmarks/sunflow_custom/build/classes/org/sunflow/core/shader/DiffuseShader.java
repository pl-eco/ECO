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
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcRxWfW/93nNzFaRI3TZzEtSvyh1uCVKTgKiU9nMbp" +
                                                    "pT7FaQQu5Dq3N+fbeG9nsztnX1wMSQRyFKQIgVtS1PoDSlVa0qZCRAWhSvkC" +
                                                    "bVW+FCEQH2gRX6go+ZAPlIoC5b2Z3du9vbNJvmBp52Zn3pv33rz3fu+tr94k" +
                                                    "HZ5L9jjcOjNtcZFmNZE+Zd2fFmcc5qWPZO/PUddjxYxFPe84rOWNoVeTH378" +
                                                    "nXJKI51TZAO1bS6oMLntHWMet2ZZMUuS4eqYxSqeIKnsKTpL9aowLT1remI0" +
                                                    "S9ZEWAUZzgYq6KCCDiroUgX9YEgFTGuZXa1kkIPawjtNvk4SWdLpGKieIDsb" +
                                                    "D3GoSyv+MTlpAZzQje8nwCjJXHPJjrrtyuYmg5/aoy99/2TqJ20kOUWSpj2J" +
                                                    "6highAAhU6SvwioF5noHi0VWnCLrbcaKk8w1qWXOS72nSL9nTttUVF1WvyRc" +
                                                    "rDrMlTLDm+sz0Da3agju1s0rmcwqBm8dJYtOg62bQluVhYdwHQzsNUExt0QN" +
                                                    "FrC0z5h2UZDtcY66jcOPAAGwdlWYKPO6qHabwgLpV76zqD2tTwrXtKeBtINX" +
                                                    "QYogW1Y8FO/aocYMnWZ5QQbidDm1BVQ98iKQRZCNcTJ5EnhpS8xLEf/cfPSB" +
                                                    "S0/ah21N6lxkhoX6dwPTYIzpGCsxl9kGU4x9u7NP002vX9AIAeKNMWJF89rX" +
                                                    "bn1h7+CNNxXNPS1oJgqnmCHyxpXCune2Znbtb0M1uh3umej8Bstl+Of8ndGa" +
                                                    "A5m3qX4ibqaDzRvHfvXlsy+xDzTSO046DW5VKxBH6w1ecUyLuQ8zm7lUsOI4" +
                                                    "6WF2MSP3x0kXzLOmzdTqRKnkMTFO2i251MnlO1xRCY7AK+qCuWmXeDB3qCjL" +
                                                    "ec0hhHTBQ/bC00fUn/wVxNLLvMJ0alDbtLkOscuoa5R1ZvC8yxyuj2Um9ALc" +
                                                    "crlC3RlP96p2yeJzeaPqCV7RPdfQuTsdLOsGd5nulWmRufoXzVKp6rFJ+ZbG" +
                                                    "qHP+z/JqaH9qLpEA12yNA4MFOXWYW0CbN5aqD43deiX/tlZPFP/mBBkBcWlf" +
                                                    "XBrFpZW4dIM4kkhIKXehWOV8cN0MgADAY9+uya8eeeLCUBtEnTPXDveOpENg" +
                                                    "sq/LmMEzIVKMSzw0IFwHfvj4YvqjFx5U4aqvDOstucmNy3PnTnzjMxrRGvEZ" +
                                                    "bYOlXmTPIarW0XM4npetzk0uvv/htacXeJihDYDvA0czJyb+UNwLLjdYEaA0" +
                                                    "PH73Dno9//rCsEbaAU0AQQWFiAdwGozLaACA0QBM0ZYOMLjE3Qq1cCtAwF5R" +
                                                    "dvlcuCLDY52crwenrMGMGIAn6aeI/MXdDQ6Od6lwQi/HrJBgfejnN565/oM9" +
                                                    "+7UoricjlXKSCYUS68MgOe4yBut/vJz73lM3Fx+XEQIU97YSMIxjBjADXAbX" +
                                                    "+q03T//hvXev/FYLo0pA8awWLNOowRn3hVIAUSxANfT98GN2hRfNkkkLFsPg" +
                                                    "/FdyZN/1v11KKW9asBIEw97/fUC4fvdD5OzbJ/8xKI9JGFjRQstDMnUBG8KT" +
                                                    "D7ouPYN61M79Ztszb9DnAHAB5DxznkncItIyIq9el67aLcd0bG8fDjucpr2a" +
                                                    "XBmoZ92ulZPoEBbmSPL9c8IqnP/zR9KipvRpUY9i/FP61We3ZA58IPnDOEbu" +
                                                    "7bVmPIImJuT97EuVv2tDnb/USNcUSRl+h3SCWlWMlinoCrygbYIuqmG/scKr" +
                                                    "cjZaz9Ot8RyKiI1nUIiDMEdqnPfGkkaWkc3wrPWTZm08aRJETvZLliE5juDw" +
                                                    "qSBmuxzXnKXYfoGWAKqSZKMgm6PIa1agvcA45PIGU47UY7jBu634JGIrjMak" +
                                                    "2LZS4yGbpivnl5aLE8/vU3jb31jMx6BXffl3//51+vKf3mpRJ3oEdz5tsVlm" +
                                                    "RXRqR5ENOH9U9mShpy+++OPXxDt7Pq9E7l45OuOMb5z/65bjB8pP3AG6b48Z" +
                                                    "Hz/yxaNX33r4PuO7GmmrB0xTm9nINNoYJr0ug77YPt4QLIP1YNmAsXF3MAl+" +
                                                    "mxA29GyY65q8Ty3w8WCTj6WpDLpYBJOAbFOUbFL9HsyNSzG5VdDkBA5HAU6r" +
                                                    "ThEic3XcyLlmBdq8Wb8P1Rf635t59v2XlUfjIBEjZheWLn6SvrSkRTr7e5ua" +
                                                    "6yiP6u6llmvVxX4Cfwl4/oMPmoALqrvrz/gt5o56j+k4mAc7V1NLijj0l2sL" +
                                                    "v/jRwqLmX8kYJGqBc4tRuxll5cJ43c/4kHv8ahpU1dvzc6Ixl7e1zGX4aMHP" +
                                                    "NiaPYav40cShIEjvNBN+v4YrD96eBVv9J5jfkQX4WpJUfBUFT+MAabYGFDwG" +
                                                    "hmFO3Z6Gsls5AM+Ir+HIneaS1FAKk6Rzq6h5BgchIOIMKiDHcmUufKj5Eg5f" +
                                                    "UdqdBASf5WaxRRkG3oaGGduAgaZvdfV9abyynOzevPzY72ULWP8G7IEPsVLV" +
                                                    "sqJlKTLvdFxWMqW6PapIqRpxrlVFUD08JLiaSH3PKvpvCpKK04Nd+BMlWwSn" +
                                                    "RcggO/xZlOiiIG1AhNNvO0FMp2T3g+U5rcpzjURKBjaA0beGbhCxR/4fJEDw" +
                                                    "qvpPSN64tnzk0Sdvfe55WQ46DIvOz+Mp3VnSpRrhehXYueJpwVmdh3d9vO7V" +
                                                    "npEg9dfh0O93vzHdtrduEscqjpBt3fzPNv/0gReW35Vd6n8BCryI+KASAAA=");
}
