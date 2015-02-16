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
      1160895492000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YXWwdxRWeu/6LHce+cUhi0sRJjBM1Md0llaiUGoUGyyEO" +
                                                    "N+QqTqLWtDFzd+f6brx3ZzM7174xuECqKhEPEWpNmlTgBxTETwNBFVFACCkv" +
                                                    "LSD6QlWBeOBHvBQBechDKSrl58zM/t2916a89Eo7Oztzzpxz5pzzzZl78Rpq" +
                                                    "8Rka9KhzYtKhXCdVrh9zbtX5CY/4+r7crXnMfGINO9j3D8HYhNn/QvfnXz5S" +
                                                    "ymqodRytwq5LOeY2df2DxKfONLFyqDseHXFI2ecomzuGp7FR4bZj5GyfD+XQ" +
                                                    "8gQrRwO5UAUDVDBABUOqYOyOqYBpBXEr5WHBgV3uH0e/RpkcavVMoR5Hm2sX" +
                                                    "8TDD5WCZvLQAVlgmvo+AUZK5ytCmyHZlc53Bjw4a8384mv1zE+oeR922OybU" +
                                                    "MUEJDkLGUWeZlAuE+bsti1jjaKVLiDVGmI0de1bqPY56fHvSxbzCSLRJYrDi" +
                                                    "ESZlxjvXaQrbWMXklEXmFW3iWOFXS9HBk2DrmthWZeEeMQ4GdtigGCtik4Qs" +
                                                    "zVO2a3G0Mc0R2ThwFxAAa1uZ8BKNRDW7GAZQj/Kdg91JY4wz250E0hZaASkc" +
                                                    "rVt0UbHXHjan8CSZ4Kg3TZdXU0DVLjdCsHC0Ok0mVwIvrUt5KeGfa3ffduY+" +
                                                    "d6+rSZ0tYjpC/2XA1JdiOkiKhBHXJIqxc3vuLF7z6mkNISBenSJWNFfuv/6z" +
                                                    "m/uuvq5oftCA5kDhGDH5hHmh0PXW+uFtO5uEGss86tvC+TWWy/DPBzNDVQ8y" +
                                                    "b020opjUw8mrB//6iwefJZ9qqGMUtZrUqZQhjlaatOzZDmF3EpcwzIk1itqJ" +
                                                    "aw3L+VHUBv2c7RI1eqBY9AkfRc2OHGql8hu2qAhLiC1qg77tFmnY9zAvyX7V" +
                                                    "Qwi1wYMG4VmO1E++OTKNwz6Eu4FN7NouNSB4CWZmySAmnSjA7pbKmE35hlnx" +
                                                    "OS0bfsUtOnTG8JlpUDYZfZuUEcMvYYswY7/NGGVj8kMXweb9f8RUhbXZmUwG" +
                                                    "HLE+DQMOZNBe6gDthDlfuWPk+vMTb2pRWgT7BMAF0vRAmi6k6UqanpSGMhkp" +
                                                    "5AYhVXka/DQFGQ9Y2Llt7Ff77j3d3wQh5s00wyYL0n6wM1BlxKTDMSyMSvAz" +
                                                    "ITZ7n7jnlP7FU7er2DQWx/CG3OjquZmHjjxwi4a0WjAWpsFQh2DPCwiNoHIg" +
                                                    "nYSN1u0+9fHnl87O0Tgda9A9QIl6TpHl/WknMGoSC3AzXn77Jnx54tW5AQ01" +
                                                    "A3QAXHIM4Q1I1JeWUZPtQyFyCltawOAiZWXsiKkQ7jp4idGZeERGR5fsrwzD" +
                                                    "fy08XUE+yLeYXeWJ9gYVTcLLKSskMu95+er5y38c3KklQbw7cSyOEa4gYWUc" +
                                                    "JIcYITD+3rn87x+9duoeGSFAcVMjAQOiHQaAAJfBtv729ePvfvD+hX9ocVRx" +
                                                    "OCkrBcc2q7DG1lgKwIcDECZ8P3DYLVPLLtq44BARnP/t3rLj8mdnssqbDoyE" +
                                                    "wXDzdy8Qj994B3rwzaP/7pPLZExxfMWWx2RqA1bFK+9mDJ8QelQf+vuG86/h" +
                                                    "xwFdAdF8e5ZIkELSMiS33pCu2i5bPTW3QzSbvLq5qhzpjbJu2+JJtEecwonk" +
                                                    "+88Bp3Dyoy+kRXXp0+DwSfGPGxcfWze861PJH8ex4N5YrYcjqFhi3h8/W/6X" +
                                                    "1t/6Fw21jaOsGZRDR7BTEdEyDiWAH9ZIUDLVzNce5+rsGorydH06hxJi0xkU" +
                                                    "wyD0BbXod6SSplPscm/YCd/JpMkg2dkpWfplu0U0Pwxjts1j9jQWtRZqgYMw" +
                                                    "qItWc7Q2ibx2GYoJEYhUbmHWk4oM1Li3EZ9EbAXSIis2LFZmyBLpwsn5BevA" +
                                                    "kzsU4PbUHt0jUJk+9/ZXf9PPffhGg3OinVPvRw6ZJk5CpyYhsgbo98sKLHb1" +
                                                    "w8/86Qp/a/CnSuT2xcMzzfjayU/WHdpVuvd7wPvGlPHpJZ/Zf/GNO7eav9NQ" +
                                                    "UxQxdUVlLdNQbZx0MAJVsHuoJlr6omhZJYLjRnh6gmjpaQixsWfjZNfkfmqh" +
                                                    "j/vqfCxNJVCzCjQJydYkycbUe3d+VIrJLwEnR0SzH/C04lkQmksDR57ZZSjq" +
                                                    "poOq05jr+WDqsY+fUx5No0SKmJyef/gb/cy8lqjjb6orpZM8qpaXWq5QG/sN" +
                                                    "/DLwfC0eYYIYULVcz3BQUG6KKkrPE3mweSm1pIg9/7w098rTc6e0YEtGIFML" +
                                                    "lDoEu/UwKwdGIz+LB60PjtPwWP3f/JypzeUNDXMZrijikkbkMmQJP9qiKXC0" +
                                                    "fJLwg8AnQlYM3f6dJshqYBc8emCC/n1DVXwWpTBJSpdQ87hoINlW+CbmEML5" +
                                                    "EuVBJv9cNL9U2h3lqHma2laDY46jzmQ9Kk7Z3rp7r7qrmc8vdC9bu3D4HVlh" +
                                                    "RfepdrjUFCuOk0T9RL/VY6RoS23b1RmgEPhEI7xVFTKkj+pIdauK/n6Osml6" +
                                                    "MEu8kmQPgM8SZBB7QS9JdJKjJiAS3d94YcRkZXEhTj9dnX5VlABkUV8lv2qK" +
                                                    "LZHZ8j+FEB8r6l+FCfPSwr6777v+kycl2LaYDp6dlXdQuFKrOjPC2M2Lrhau" +
                                                    "1bp325ddL7RvCROrSzQ9QXGZ0m1j4xpspOxxWTXNvrT2xdueWnhfFoHfAn5I" +
                                                    "sAvsEQAA");
}
