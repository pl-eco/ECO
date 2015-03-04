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
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYX2wcxRmfW/+LHce+OCQxaeIkxkFNDLtNJSqlRqHBcojT" +
                                                    "CznFIaKmjRnvzvk22dvZ7M7ZF4MLpKoS8RBVraGhAj+gIAoNBFWNaFUh5aUF" +
                                                    "RF+oqlZ9KCBeQEAe8sAflbb0+2b23+2dTXmopZ2bnfn+zvd9v/nWl66RtsAn" +
                                                    "wx53Ts84XOisJvQTzm26OO2xQD9YuK1I/YBZow4NgqOwNmUOvtT7yec/Kec1" +
                                                    "0j5J1lHX5YIKm7vBERZwZ5ZZBdKbrI45rBIIki+coLPUqArbMQp2IEYKZHWK" +
                                                    "VZChQmSCASYYYIIhTTD2JVTAtIa51cooclBXBKfID0muQNo9E80TZHu9EI/6" +
                                                    "tBKKKUoPQMIqfD8GTknmmk+2xb4rnxscfmzYWPz58fyvW0jvJOm13Qk0xwQj" +
                                                    "BCiZJN0VVplmfrDPspg1Sda6jFkTzLepY89LuydJX2DPuFRUfRYfEi5WPeZL" +
                                                    "ncnJdZvom181Bfdj90o2c6zora3k0BnwdUPiq/JwP66Dg102GOaXqMkiltaT" +
                                                    "tmsJsjXLEfs49F0gANaOChNlHqtqdSkskD4VO4e6M8aE8G13BkjbeBW0CLJp" +
                                                    "WaF41h41T9IZNiVIf5auqLaAqlMeBLIIsj5LJiVBlDZlopSKz7W7bz//gHvA" +
                                                    "1aTNFjMdtH8VMA1kmI6wEvOZazLF2L2r8Djd8Mo5jRAgXp8hVjQvP3j9O7cM" +
                                                    "XH1N0XytCc3h6RPMFFPmxemeNzeP7tzTgmas8nhgY/DrPJfpXwx3RmoeVN6G" +
                                                    "WCJu6tHm1SN//N7Dz7MPNdI1TtpN7lQrkEdrTV7xbIf5dzGX+VQwa5x0Mtca" +
                                                    "lfvjpAPmBdtlavVwqRQwMU5aHbnUzuU7HFEJROARdcDcdks8mntUlOW85hFC" +
                                                    "OuAhw/CsJupP/gpyr1HmFWZQk7q2yw3IXUZ9s2wwkxsBrXgORC2ouiWHzxmB" +
                                                    "bxrcn4nfTe4zIyhTi/nGIdv3uT8hX3TMMO//KLuGfuXncjk48s3ZgnegVg5w" +
                                                    "B2inzMXqnWPXX5x6Q4sLIDwRgCjQpofadNSmK216WhvJ5aSSG1CriilE5CTU" +
                                                    "NqBe986JHxy8/9xgCySTN9cKx4mkg+BdaMqYyUcTABiXMGdCFvY/fd9Z/bNn" +
                                                    "71BZaCyP1k25ydULc48ce+gbGtHqYRddg6UuZC8iWMagOJQtt2Zye8++/8nl" +
                                                    "xxd4Unh1OB7iQSMn1vNgNgg+N5kFCJmI37WNXpl6ZWFII60AEgCMgkIiA+YM" +
                                                    "ZHXU1fVIhJHoSxs4XOJ+hTq4FQFblyj7fC5ZkdnRI+dro0TfCE9PmPnyF3fX" +
                                                    "eTjeoLIJo5zxQmLw/t9dfeLKL4b3aGm47k1dgBNMqOJfmyTJUZ8xWP/HheLP" +
                                                    "Hrt29j6ZIUBxUzMFQziOAhRAyOBYf/zaqb+//dbFv2hJVgm4E6vTjm3WQMbN" +
                                                    "iRYACgfACmM/dI9b4ZZdsum0wzA5/9W7Y/eVj87nVTQdWImS4ZYvF5Cs33gn" +
                                                    "efiN458OSDE5Ey+qxPOETB3AukTyPt+np9GO2iN/3vLEq/QpwFHArsCeZxKO" +
                                                    "iPSMyKM3ZKh2yVHP7O3GYZvXsFeTK/1x1e1cvoj2432bKr5/Hnamz7z7mfSo" +
                                                    "oXyaXDMZ/knj0pObRvd+KPmTPEburbVGOILeJOH95vOVj7XB9j9opGOS5M2w" +
                                                    "8TlGnSpmyyRc9kHUDUFzVLdff3GrW2okrtPN2RpKqc1WUAKDMEdqnHdliqYb" +
                                                    "T7k/mkS/6aLJETnZI1kG5bgDh69HOdvh+fYsxa6KtMGVF3ZA6wXZmEZeuwJt" +
                                                    "AyYil0eY96QhQ3XhbcYnEVuBNFbFluUaCtkMXTyzuGQdfma3Aty++kt6DHrQ" +
                                                    "F/767z/pF955vck90Sm4d6vDZpmTsqkFVdYB/SHZayWhfvS5X70s3hz+tlK5" +
                                                    "a/n0zDK+euaDTUf3lu//CvC+NeN8VuRzhy69ftfN5k810hJnTEP7WM80Up8n" +
                                                    "XT6Dftc9WpctA3G2rMPkuBGevjBb+ppCbBLZpNg1eZ5aFOOBhhhLVxl0p4gm" +
                                                    "EdmGNNmE+t1XHJdqiivAyTEcDgGeVj0LUnNl4Cj6dgXat9mwvzQW+t4++eT7" +
                                                    "L6iIZlEiQ8zOLT76hX5+UUt17Dc1NM1pHtW1SyvXqIP9Av5y8PwHH3QBF1TX" +
                                                    "1jcato7b4t7R87AOtq9kllSx/73LC7//5cJZLTySMajUac4dRt1GmJUL43Gc" +
                                                    "8SGbw+s0ulb/tzjn6mt5S9Naho8R/BxjUgxbIY42DtOCrJ5h4gjwYcri0h1f" +
                                                    "6oLsBvbCo4cu6F81VfG1JJVJUr6CmadwgGJbE5hUQAoXy1yElXwvDt9X1h0X" +
                                                    "pHWW21aTa06Q7nQ/irdsf8MXrvoqM19c6l21cemev8kOK/5y6oTPl1LVcdKo" +
                                                    "n5q3ez4r2dLaTnUHKAQ+3QxvVYcM5aMm0tyaon9QkHyWHtzCnzTZQxCzFBnk" +
                                                    "XjhLE50RpAWIcPojL8qYvGwu8PbT1e1XIylAxv4q/VbXbGFly/8eRPhYVf8/" +
                                                    "mDIvLx28+4Hr33pGgm2b6dD5efm1CR/Pqs+MMXb7stIiWe0Hdn7e81Lnjqiw" +
                                                    "enDoC5vLjG1bm/dgYxVPyK5p/rcbf3P7s0tvySbwvy7Ved7WEQAA");
}
