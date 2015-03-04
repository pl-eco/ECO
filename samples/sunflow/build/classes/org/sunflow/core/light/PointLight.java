package org.sunflow.core.light;
import org.sunflow.SunflowAPI;
import org.sunflow.core.LightSample;
import org.sunflow.core.LightSource;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public class PointLight implements LightSource {
    private Point3 lightPoint;
    private Color power;
    public PointLight() { super();
                          lightPoint = new Point3(0, 0, 0);
                          power = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { lightPoint =
                                                                pl.
                                                                  getPoint(
                                                                    "center",
                                                                    lightPoint);
                                                              power = pl.
                                                                        getColor(
                                                                          "power",
                                                                          power);
                                                              return true;
    }
    public int getNumSamples() { return 1; }
    public void getSamples(ShadingState state) { Vector3 d =
                                                   Point3.
                                                   sub(
                                                     lightPoint,
                                                     state.
                                                       getPoint(
                                                         ),
                                                     new Vector3(
                                                       ));
                                                 if (Vector3.
                                                       dot(
                                                         d,
                                                         state.
                                                           getNormal(
                                                             )) >
                                                       0 &&
                                                       Vector3.
                                                       dot(
                                                         d,
                                                         state.
                                                           getGeoNormal(
                                                             )) >
                                                       0) {
                                                     LightSample dest =
                                                       new LightSample(
                                                       );
                                                     dest.
                                                       setShadowRay(
                                                         new Ray(
                                                           state.
                                                             getPoint(
                                                               ),
                                                           lightPoint));
                                                     float scale =
                                                       1.0F /
                                                       (float)
                                                         (4 *
                                                            Math.
                                                              PI *
                                                            lightPoint.
                                                            distanceToSquared(
                                                              state.
                                                                getPoint(
                                                                  )));
                                                     dest.
                                                       setRadiance(
                                                         power,
                                                         power);
                                                     dest.
                                                       getDiffuseRadiance(
                                                         ).
                                                       mul(
                                                         scale);
                                                     dest.
                                                       getSpecularRadiance(
                                                         ).
                                                       mul(
                                                         scale);
                                                     dest.
                                                       traceShadow(
                                                         state);
                                                     state.
                                                       addSample(
                                                         dest);
                                                 } }
    public void getPhoton(double randX1, double randY1, double randX2,
                          double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power) { p.set(lightPoint);
                                         float phi = (float)
                                                       (2 *
                                                          Math.
                                                            PI *
                                                          randX1);
                                         float s = (float)
                                                     Math.
                                                     sqrt(
                                                       randY1 *
                                                         (1.0F -
                                                            randY1));
                                         dir.x = (float) Math.
                                                   cos(
                                                     phi) *
                                                   s;
                                         dir.y = (float) Math.
                                                   sin(
                                                     phi) *
                                                   s;
                                         dir.z = (float) (1 -
                                                            2 *
                                                            randY1);
                                         power.set(this.power);
    }
    public float getPower() { return power.getLuminance();
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcRxWfO/8523F8ttM4bnAcx3UqkpRbAhTJuGpxjdM4" +
                                                    "XGMrl0TCUeuOd+fuNt7d2e7O2WcH0zYIJVQoQOuWBAUjlVT9lzZV1aggVClf" +
                                                    "oK3KlyIE4gMt4gsVJR/ygVJRoLw3u3u7t3d2Ah84aefmZt+b9968937vzV28" +
                                                    "Sppch+yxubFYMLjIsLLIHDduz4hFm7mZA9nbp6jjMm3MoK57GNZm1MGX0x9+" +
                                                    "/L1iZ5I0T5NN1LK4oELnlnuIudyYZ1qWpMPVcYOZriCd2eN0nioloRtKVnfF" +
                                                    "SJZsiLAKMpQNVFBABQVUUKQKymhIBUwbmVUyx5CDWsJ9kHyDJLKk2VZRPUF2" +
                                                    "VG9iU4ea/jZT0gLYoQV/HwWjJHPZIQMV2z2bawx+Yo+y8oP7O19pIOlpktat" +
                                                    "HKqjghIChEyTdpOZs8xxRzWNadOky2JMyzFHp4a+JPWeJt2uXrCoKDmscki4" +
                                                    "WLKZI2WGJ9euom1OSRXcqZiX15mhBb+a8gYtgK09oa2ehftwHQxs00ExJ09V" +
                                                    "FrA0zumWJsj2OEfFxqGvAgGwpkwmirwiqtGisEC6Pd8Z1CooOeHoVgFIm3gJ" +
                                                    "pAiydc1N8axtqs7RApsRpDdON+W9AqpWeRDIIsjmOJncCby0NealiH+uHrzj" +
                                                    "zAlrv5WUOmtMNVD/FmDqjzEdYnnmMEtlHmP77uyTtOf100lCgHhzjNijee3r" +
                                                    "1758W/+VNz2aT9WhmZw9zlQxo16Y7Xinb2zXcAOq0WJzV0fnV1kuw3/KfzNS" +
                                                    "tiHzeio74stM8PLKoV9+7eHn2QdJ0jZBmlVulEyIoy6Vm7ZuMOceZjGHCqZN" +
                                                    "kFZmaWPy/QRJwTyrW8xbncznXSYmSKMhl5q5/A1HlIct8IhSMNetPA/mNhVF" +
                                                    "OS/bhJAUPCQDz0bifeS3IEWlyE2mUJVausUViF1GHbWoMJXPOMzmyvjYpDIL" +
                                                    "p1w0qTPnKm7Jyht8YUYtuYKbiuuoCncKwbKicocphl4oCjgZCN0sTjMYcfb/" +
                                                    "UVYZ7e5cSCTAJX1xQDAgl/ZzQ2POjLpSunv82kszbycrCeKfGKAPiMr4ojIo" +
                                                    "KiNFZUJRJJGQEm5CkZ7DwV1zkPgAie27cvcdeOD0YANEmr3QCGeNpINgqq/H" +
                                                    "uMrHQnSYkBioQoj2PnXsVOajZ+7yQlRZG8rrcpMrZxceOfrQZ5MkWY3JaBcs" +
                                                    "tSH7FCJpBTGH4rlYb9/0qfc/vPTkMg+zsgrkfbCo5cRkH4x7wOEq0wA+w+13" +
                                                    "D9DLM68vDyVJIyAIoKagEOUASP1xGVVJPxIAKNrSBAbnuWNSA18FqNcmig5f" +
                                                    "CFdkaHTIeRc4ZQNmwWZ4uvy0kN/4dpON401eKKGXY1ZIgN73syvnLv9wz3Ay" +
                                                    "iuXpSHXMMeEhQ1cYJIcdxmD9D2enHn/i6qljMkKA4pZ6AoZwHAOcAJfBsX7r" +
                                                    "zQd//967F36TDKNKQMEszRq6WoY9bg2lAIoYgGTo+6Ejlsk1Pa/TWYNhcP4z" +
                                                    "vXPv5b+e6fS8acBKEAy3XX+DcP3mu8nDb9//9365TULFKhZaHpJ5B7Ap3HnU" +
                                                    "cegi6lF+5Nfbzr1BfwQgC8Dm6ktMYhWRlhF59Ip01W45ZmLv9uIwYNe8K8uV" +
                                                    "XvkrCaJ3rZ1E+7AYR5LvH5PG7Mk/fSQtqkmfOjUoxj+tXDy/dezODyR/GMfI" +
                                                    "vb1ci0XQuIS8n3ve/FtysPkXSZKaJp2q3xUdpUYJo2UaOgE3aJWgc6p6X13V" +
                                                    "vRI2UsnTvngORcTGMyjEQJgjNc7bYknTjqfcD0+HnzQd8aRJEDkZliyDctyJ" +
                                                    "w6eDmE3Zjj5PseUibRJXJaxKws2CbIlirwl1zEPdz8tc9Pz9hWpteuFJ+9qk" +
                                                    "19BmFIcRQZpsvsCcurJ0E5oYjHzueLKk5UOReEoEfH019UEWhRwvOSrDXNy2" +
                                                    "Vo8j+7MLJ1dWtcmn93ow313dN4xDW/zib//1q8zZP75VpzS1Cm5/xmDzzIgo" +
                                                    "ho3/tqrycq9s/8IAe/S5F14T7+z5kidy99pJEWd84+Rfth6+s/jAf1FUtseM" +
                                                    "j2/53L0X37rnVvWxJGmoxGlNR1vNNFIdnW0OgxbcOlwVo/2VqNiEQXAzPD1+" +
                                                    "VPTUBfbQvSHEJH3g8B3dX+NoaSqDhhkxLCDriZLlvO/RqQkp5sg6IHYMh0OA" +
                                                    "4iVbg4RYH66mHN2EjnLeb3mV5e735s6//6Ln0Tg2xYjZ6ZVHP8mcWUlGLhG3" +
                                                    "1PTxUR7vIiG13Ogd7CfwScDzb3zQBFzwGsnuMb+bHai0s7aNebBjPbWkiH1/" +
                                                    "vrT882eXTyX9IzkA+DDLucGoVQvucuFgxc8y2fv8J5jfmJ+jXji+zjt5ASkI" +
                                                    "srHAxMGSmaOmbTBXkt6HA/V0gvrXACB1XY03BJ34gK/xwA1rnKiGoG01kZkr" +
                                                    "Ug1udHinZXKb0jp2LeIAPVYb2LWOUY3zXNduzKpFeBTfKuWGrUrJHVP1ZDdr" +
                                                    "HHobmeDfrB7uCg6ht6ZWHGXYgcpi8RUp9eQ6h/BtHB4CTIVDmCpywS1cOHFd" +
                                                    "e7sCfBn27R3+n+Luu+u8+z4O3xGkBVWrlKzYCUH15rRO0GFhDa8q2ID11vwz" +
                                                    "4t3m1ZdW0y1bVo/8TjbflRt3K1x78yXDiDYEkXmz7bC8LvVs9doDr1aeiyFh" +
                                                    "eHMCXeW3VPWsR31ekM44NUQcfkXJfizIhggZwIM/ixI9BfkHRDj9iR1ER6fs" +
                                                    "OrEtynhtUZlEaiY23tFfVV04gq/8zykoYSXvX6cZ9dLqgYMnrn3xaVkPm1SD" +
                                                    "Li3hLi1ZkvIuIJUyuGPN3YK9mvfv+rjj5dadAfZ14NDt3zpium2v35yPm7aQ" +
                                                    "7fTST7e8esczq+/K28F/AD09UaAMFAAA");
}
