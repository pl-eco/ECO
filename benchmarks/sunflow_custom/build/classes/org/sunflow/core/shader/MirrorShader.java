package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class MirrorShader implements Shader {
    private Color color;
    public MirrorShader() { super();
                            this.color = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { color = pl.
                                                                        getColor(
                                                                          "color",
                                                                          color);
                                                              return true;
    }
    public Color getRadiance(ShadingState state) { if (!state.
                                                         includeSpecular(
                                                           ))
                                                       return Color.
                                                                BLACK;
                                                   state.
                                                     faceforward(
                                                       );
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
                                                   ret.sub(
                                                         color);
                                                   ret.mul(
                                                         cos5);
                                                   ret.add(
                                                         color);
                                                   return ret.
                                                     mul(
                                                       state.
                                                         traceReflection(
                                                           refRay,
                                                           0));
    }
    public void scatterPhoton(ShadingState state, Color power) {
        float avg =
          color.
          getAverage(
            );
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd >=
              avg)
            return;
        state.
          faceforward(
            );
        float cos =
          state.
          getCosND(
            );
        power.
          mul(
            color).
          mul(
            1.0F /
              avg);
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
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfW/+LHce+OCQxaeIkxkFNTHebSlRKjULDySFO" +
                                                    "L/gUh6g1bY653Tnfxrs7m9k5+2JwgVRVIj5ECAwNFfUHFMSfBoKqRrSqkPKl" +
                                                    "BUS/UFWgfiggvhQB+ZAPpai0pW9mdm/39s6mfOlJOzs789689+a995s3d+ka" +
                                                    "6ggYGvWpc3rGoVwnNa6fdG7V+WmfBPrh/K0FzAJi5RwcBMdgrGgOv9z/6eeP" +
                                                    "VLIa6pxGG7DnUY65Tb3gKAmoM0esPOqPR8cd4gYcZfMn8Rw2qtx2jLwd8LE8" +
                                                    "Wptg5WgkH6lggAoGqGBIFYwDMRUwrSNe1c0JDuzx4BT6McrkUadvCvU42tm4" +
                                                    "iI8ZdsNlCtICWGGN+D4ORknmGkM76rYrm5sMfnzUWPrZieyv2lD/NOq3vSmh" +
                                                    "jglKcBAyjXpd4pYICw5YFrGm0XqPEGuKMBs79oLUexoNBPaMh3mVkfomicGq" +
                                                    "T5iUGe9crylsY1WTU1Y3r2wTx4q+OsoOngFbN8W2KgsPinEwsMcGxVgZmyRi" +
                                                    "aZ+1PYuj7WmOuo0j3wMCYO1yCa/Quqh2D8MAGlC+c7A3Y0xxZnszQNpBqyCF" +
                                                    "oy0rLir22sfmLJ4hRY4G03QFNQVU3XIjBAtHG9NkciXw0paUlxL+uXbXbefv" +
                                                    "8w55mtTZIqYj9F8DTEMppqOkTBjxTKIYe/fkn8CbXj2nIQTEG1PEiuaV+69/" +
                                                    "95ahq68rmq+1oJksnSQmL5oXS31vbc3t3tcm1Fjj08AWzm+wXIZ/IZwZq/mQ" +
                                                    "eZvqK4pJPZq8evQPP3jwBfKxhnomUKdJnaoLcbTepK5vO4TdSTzCMCfWBOom" +
                                                    "npWT8xOoC/p52yNqdLJcDgifQO2OHOqk8hu2qAxLiC3qgr7tlWnU9zGvyH7N" +
                                                    "Rwh1wYNG4VmL1E++OZo1KtQlBjaxZ3vUgNglmJkVg5i0yIhPjfHcpFGCXa64" +
                                                    "mM0GRlD1yg6dL5rVgFPXCJhpUDYTDRsmZcQIKtgizDhiM0bZlPzQRdD5/19x" +
                                                    "NWF9dj6TAcdsTcOCAxl1iDpAWzSXqneMX3+p+KZWT5Nw3wDIQJoeStOFNF1J" +
                                                    "05PSUCYjhdwgpCrPg99mAQEAG3t3T/3o8L3nhtsg5Pz5dth0QToMBoeqjJs0" +
                                                    "F8PEhARDE2J18Ol7zuqfPXu7ilVjZUxvyY2uXph/6PgD39SQ1gjOwjQY6hHs" +
                                                    "BQGpdegcSSdlq3X7z3746eUnFmmcng1oH6JGM6fI+uG0Exg1iQU4Gi+/Zwe+" +
                                                    "Unx1cURD7QAlAJ8cQ7gDMg2lZTRk/1iEpMKWDjC4TJmLHTEVwV8PrzA6H4/I" +
                                                    "6OiT/fVROmyGpy/MD/kWsxt80d6gokl4OWWFROqDv7365JWfj+7TkqDenzgm" +
                                                    "pwhXELE+DpJjjBAY/+uFwmOPXzt7j4wQoLiplYAR0eYAMMBlsK0/ff3UX957" +
                                                    "9+KftTiqOJyc1ZJjmzVY4+ZYCsCJA5AmfD9yt+dSyy7buOQQEZz/6t+198on" +
                                                    "57PKmw6MRMFwy5cvEI/feAd68M0T/xiSy2RMcZzFlsdkagM2xCsfYAyfFnrU" +
                                                    "HvrTtidfw78AtAWEC+wFIkELScuQ3HpDumqPbPXU3F7R7PCb5mpyZLCedbtX" +
                                                    "TqKD4lROJN8/J53SmQ8+kxY1pU+LwyjFP21cempLbv/Hkj+OY8G9vdYMR1DB" +
                                                    "xLzfesH9uzbc+XsNdU2jrBmWR8exUxXRMg0lQRDVTFBCNcw3Hu/qLBur5+nW" +
                                                    "dA4lxKYzKIZB6Atq0e9JJU2v2OXBqBO9k0mTQbKzT7IMy3aXaL4exWyXz+w5" +
                                                    "LGov1AEHY1gnbeRocxJ5bReKCxGIVG5h1peKjDS4txWfRGwF0iIrtq1UdsiS" +
                                                    "6eKZpWVr8pm9CnAHGo/ycahUX3z733/UL7z/RotzoptT/xsOmSNOQqc2IbIB" +
                                                    "6I/Iiix29cPP//IV/tbod5TIPSuHZ5rxtTMfbTm2v3LvV4D37Snj00s+f+TS" +
                                                    "G3febD6qobZ6xDQVmY1MY41x0sMIVMXesYZoGapHywYRHDfCMxBGy0BLiI09" +
                                                    "Gye7JvdTi3w81ORjaSqBGlagSUS2KUk2pd4HChNSTGEVODkumiOAp1XfgtBc" +
                                                    "HTgKzHahyJsLq1BjceC92ac+fFF5NI0SKWJybunhL/TzS1qirr+pqbRO8qja" +
                                                    "Xmq5Tm3sF/DLwPMf8QgTxICq7QZyYYG5o15h+r7Ig52rqSVFHPzb5cXfPbd4" +
                                                    "Vgu3ZBwytUSpQ7DXDLNyYKLuZ/GgreFxGh2r/5ufM425vK1lLsOVRVzaiFyG" +
                                                    "rOJHWzQljtbOEH4U+ETIiqHbv9QEWQ3sh0cPTdC/aqiKz7IUJknpKmqeEg0k" +
                                                    "27rAxBxCuFChPMzk74vmh0q7Exy1z1HbanHMcdSbrEfFKTvYdA9WdzfzpeX+" +
                                                    "NZuX735HVlj1+1U3XHLKVcdJon6i3+kzUraltt3qDFAIfLoV3qoKGdJHdaS6" +
                                                    "NUV/P0fZND2YJV5JsgfAZwkyiL2wlyQ6w1EbEInuT/woYrKyuBCnn65OvxpK" +
                                                    "ALKor5JfDcWWyGz5H0OEj1X1L0PRvLx8+K77rn/7GQm2HaaDFxbknRSu2KrO" +
                                                    "rGPszhVXi9bqPLT7876Xu3dFidUnmoGwuEzptr11DTbu+lxWTQu/2fzr255d" +
                                                    "flcWgf8FyhfIFvwRAAA=");
}
