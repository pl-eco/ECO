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
                                                         includeSpecular())
                                                       return Color.
                                                                BLACK;
                                                   state.
                                                     faceforward();
                                                   float cos =
                                                     state.
                                                     getCosND();
                                                   float dn =
                                                     2 *
                                                     cos;
                                                   Vector3 refDir =
                                                     new Vector3(
                                                     );
                                                   refDir.
                                                     x = dn *
                                                           state.
                                                             getNormal().
                                                             x +
                                                           state.
                                                             getRay().
                                                             getDirection().
                                                             x;
                                                   refDir.
                                                     y = dn *
                                                           state.
                                                             getNormal().
                                                             y +
                                                           state.
                                                             getRay().
                                                             getDirection().
                                                             y;
                                                   refDir.
                                                     z = dn *
                                                           state.
                                                             getNormal().
                                                             z +
                                                           state.
                                                             getRay().
                                                             getDirection().
                                                             z;
                                                   Ray refRay =
                                                     new Ray(
                                                     state.
                                                       getPoint(),
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
                                                     white();
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
          getAverage();
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
          faceforward();
        float cos =
          state.
          getCosND();
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
              getNormal().
              x +
            state.
              getRay().
              getDirection().
              x;
        dir.
          y =
          dn *
            state.
              getNormal().
              y +
            state.
              getRay().
              getDirection().
              y;
        dir.
          z =
          dn *
            state.
              getNormal().
              z +
            state.
              getRay().
              getDirection().
              z;
        state.
          traceReflectionPhoton(
            new Ray(
              state.
                getPoint(),
              dir),
            power);
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1160895492000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKUYa2wcxXl850f8oH4ksU1I7NgxUOJwC6jpI0ZNXNcBkzM5" +
                                                   "fLEJDuCMd+fuNt7b\nWWbnzmcTUWglkpICTVuqViohqiIlUChItEorURoEtJ" +
                                                   "SoElQqlZBIW0VqK7VUqirRVO2PfjNze/u4\ns4PSk3Z2duZ7zPf+5p7/EDW4" +
                                                   "DG3U3QRfcoibGEunMHOJMWZh190HS3P6mw3NqdN7bBpDdUkUMw2O\n2pO6qx" +
                                                   "mYY800tIkvjpQYGnaotZS1KE+QEk8csraX6d2R3F5F8O4TZ7seOVXfH0MNSd" +
                                                   "SObZtyzE1q\nj1sk73LUkTyEi1grcNPSkqbLR5LoKmIX8mPUdjm2ufsAegjF" +
                                                   "k6jR0QVNjgaSHnMNmGsOZjivSfZa\nSrIFCmsZ4di0iTFaYQeY28KYcOwy3l" +
                                                   "Q1NBBZIzZnQBx5ApB6c0VqJW2VqE78zMynD598No7aZ1G7\naacFMR0k4cBv" +
                                                   "FrXlSX6eMHfUMIgxizptQow0YSa2zGXJdRZ1uWbWxrzAiDtFXGoVBWCXW3AI" +
                                                   "kzy9\nxSRq04VMrKBzyio6ypjEMryvhoyFsyB2ty+2Ene3WAcBW0w4GMtgnX" +
                                                   "go9QumDRbvj2JUZBzaAwCA\n2pQnPEcrrOptDAuoS9nSwnZWS3Nm2lkAbaAF" +
                                                   "4MLRhhWJCl07WF/AWTLHUW8ULqW2AKpZKkKgcLQ+\nCiYpgZU2RKwUsM9w90" +
                                                   "dHz3zv1V3St+sNolvi/C2A1BdBmiIZwoitE4V4qZD41sQ9hY0xhAB4fQRY\n" +
                                                   "wYxee3Y6+Zef9yuYa2rA7J0/RHQ+p995vH/qwdsoiotjrHGoawrjhySX4ZAq" +
                                                   "74yUHIja7gpFsZnw\nNs9N/eKeh58jf42hlgnUqFOrkAc/6tRp3jEtwm4jNm" +
                                                   "GYE2MCNRPbGJP7E6gJ5klwebW6N5NxCZ9A\n9ZZcaqTyG1SUARJCRc0wN+0M" +
                                                   "9eYO5jk5LzkIoSZ40DA8rUj95JujzyQ0t2BnLLqouUzXKMtWvnXK\niObmsE" +
                                                   "GYNmkyRllafiSEAzkcpbUczRMN69g2baplTQhZnd5okKJ4XxnZkjhx12JdnU" +
                                                   "iB0VC2IApu\npxbAzumnL759eHzPV48qNxGuXZaVoyHglihzSwhuCcUtEeSG" +
                                                   "6uokk3WCq7IW6HoBohbyW9sN6fvu\nOHh0MA5u4izWg6IE6CBIVT7KuE7H/N" +
                                                   "CekFlQB//q/f6BI4lLp3cq/9JWzsA1sVvfeeH8yX+2bY2h\nWO30KESEBN0i" +
                                                   "yKRETq2kvaFoQNWi//fHJl9+7/wHn/RDC5RVFfHVmCJiB6PGYFQnBuRAn/yp" +
                                                   "q9vj\nd6OZ4zFUD2kAUp88P2SVviiPUOSOeFlQyNKURK0ZyvLYElte6mrhOU" +
                                                   "YX/RXpJR1yvtZz5R54PlH2\nbfkWu+sdMXYrrxLWjkghs+ylrzTe9LtXWt+U" +
                                                   "avEScnug5KUJV+Hd6TvLPkYIrH/wndQ3n/rwyAHp\nKWVX4VAHC/OWqZcA5T" +
                                                   "ofBeLagtwiDDk0beepYWZMPG8R4XH/bb/25h//7YkOZRoLVjzLbrs8AX/9\n" +
                                                   "6i+gh8/f/68+SaZOF3XFF8MHU9Ks9SmPMoaXxDlKj/xm03d/iZ+GtAepxjWX" +
                                                   "icweSEqGpB41qfet\nckxE9m4WwyDQ3raC69eo4nP64eeyg4UHfvVTeepWHG" +
                                                   "wHgmaYxM6IsrwYtgjt9kSj93bs5gDuU+fu\nvLfDOvcfoDgLFHWonu5eBmFf" +
                                                   "ChmxDN3Q9P5rr3cffDeOYrtRi0WxsRtL/0fN4HjEzUHOKTk7d0nf\n6lhcI0" +
                                                   "YpMpJK2FBWQCnwJfLFDSuH/27RA/iRMze/7Uzy7b1PSwWsGPg1SmCEzvKr0y" +
                                                   "cu/ZpfkHT8\nCBTYA6XqhAp9k4/72feKnY0vPZOPoaZZ1KGXO7sZbBWEn89C" +
                                                   "I+J67R50f6H9cFOhKuhIJcNsjEZ/\ngG009v1EDnMBLeZtkXBvE9ru9SbeOx" +
                                                   "judUhOdkqUITleXwnOJoeZRSy6PdQApbjcmfVw1BOsG2Ye\n2hkRcZSp5CHG" +
                                                   "W8SwS9l5ey1/kCe9LuQHtYjLoqTqkPDiTSt1Q7KTO7L/H22P4jfuUzWlK9xh" +
                                                   "jEMX\n/uel18n1tz7+xxqlsJlT50aLFIkVOFNcsAzVsknZKPq+8NizPzjL3x" +
                                                   "3eoVhuXdmPo4hbd5xc7t/x\n4rErqGD9ESVESXcWr7krnjPfisleVrlWVQ8c" +
                                                   "RhoJO1QLnKfA7H0ht9ocriIj8HSV3aqrZhXxLeyn\nwJjUa8yzdV+VraWoBF" +
                                                   "pskWM9sO4gWFq9R1MTks3+VZLs/WKYhipTcODGR8CavcHLIjPz0HQWZW29\n" +
                                                   "+Ojgz96afuaIMuQqCSmENad/6fd/WIg/+dq8wotmnQjw8b5Tf3r54tQ65X/q" +
                                                   "drKl6oIQxFE3FClM\nuyMiYGA1DhL6jeGB5x+auiBPJPAmIZDnKbUItv3QnF" +
                                                   "klNEOpWn7cFTb9TeUmwmsmPp7p68Jhvqlm\nmMMlS1wziSRjr2JamWsWOGrN" +
                                                   "Ej4FeMKLL5eefPGt/0f8z8OTKIufuFLPX138y0ki2RxeRT1fFsMS\nR1e5Ou" +
                                                   "YQTakc5eWkctBRUkExqi9S0/C1svxxtQIFoS14RRA9Um/V3wnqCqwn33/w3o" +
                                                   "+Sv/23bHYr\n19RWuCtmCpYVLGOBeaPDSMaUorSqoubI19dq1Qd1aYEwVxN5" +
                                                   "1GMK/kmOOqLwILd4BcG+AY4UAIOI\nKc+CQE9xFAcgMf2241moQ/ZIopwnVD" +
                                                   "kvhTQlNLMllEzkPzxe5i6o/3jm9P0vHNhcOrbv67IcNOgW\nXl6Wl/kkalJN" +
                                                   "fiX7D6xIzaP1DnrpxZlXfvg5LwHIJnBdyS/OIee+Re2uYnmoOLU76/G8w2Uv" +
                                                   "vPyT\nnh/devrEhZjs7f8HoJvjeZgTAAA=");
}
