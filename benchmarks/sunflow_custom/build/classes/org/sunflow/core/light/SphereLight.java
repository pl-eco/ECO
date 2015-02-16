package org.sunflow.core.light;
import org.sunflow.SunflowAPI;
import org.sunflow.core.LightSample;
import org.sunflow.core.LightSource;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.core.primitive.Sphere;
import org.sunflow.image.Color;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Solvers;
import org.sunflow.math.Vector3;
public class SphereLight implements LightSource, Shader {
    private Color radiance;
    private int numSamples;
    private Point3 center;
    private float radius;
    private float r2;
    public SphereLight() { super();
                           radiance = Color.WHITE;
                           numSamples = 4;
                           center = new Point3();
                           radius = (r2 = 1); }
    public boolean update(ParameterList pl, SunflowAPI api) { radiance = pl.
                                                                           getColor(
                                                                             "radiance",
                                                                             radiance);
                                                              numSamples =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    numSamples);
                                                              radius =
                                                                pl.
                                                                  getFloat(
                                                                    "radius",
                                                                    radius);
                                                              r2 =
                                                                radius *
                                                                  radius;
                                                              center =
                                                                pl.
                                                                  getPoint(
                                                                    "center",
                                                                    center);
                                                              return true;
    }
    public void init(String name, SunflowAPI api) { api.light(
                                                          name,
                                                          this);
                                                    api.geometry(
                                                          name +
                                                          ".geo",
                                                          new Sphere(
                                                            ));
                                                    api.shader(
                                                          name +
                                                          ".shader",
                                                          this);
                                                    api.parameter(
                                                          "shaders",
                                                          name +
                                                          ".shader");
                                                    api.parameter(
                                                          "transform",
                                                          Matrix4.
                                                            translation(
                                                              center.
                                                                x,
                                                              center.
                                                                y,
                                                              center.
                                                                z).
                                                            multiply(
                                                              Matrix4.
                                                                scale(
                                                                  radius)));
                                                    api.instance(
                                                          name +
                                                          ".instance",
                                                          name +
                                                          ".geo");
    }
    public int getNumSamples() { return numSamples; }
    public int getLowSamples() { return 1; }
    public boolean isVisible(ShadingState state) { return state.
                                                     getPoint(
                                                       ).
                                                     distanceToSquared(
                                                       center) >
                                                     r2; }
    public void getSamples(ShadingState state) { if (getNumSamples(
                                                       ) <=
                                                       0)
                                                     return;
                                                 Vector3 wc =
                                                   Point3.
                                                   sub(
                                                     center,
                                                     state.
                                                       getPoint(
                                                         ),
                                                     new Vector3(
                                                       ));
                                                 float l2 =
                                                   wc.
                                                   lengthSquared(
                                                     );
                                                 if (l2 <=
                                                       r2)
                                                     return;
                                                 float topX =
                                                   wc.
                                                     x +
                                                   state.
                                                     getNormal(
                                                       ).
                                                     x *
                                                   radius;
                                                 float topY =
                                                   wc.
                                                     y +
                                                   state.
                                                     getNormal(
                                                       ).
                                                     y *
                                                   radius;
                                                 float topZ =
                                                   wc.
                                                     z +
                                                   state.
                                                     getNormal(
                                                       ).
                                                     z *
                                                   radius;
                                                 if (state.
                                                       getNormal(
                                                         ).
                                                       dot(
                                                         topX,
                                                         topY,
                                                         topZ) <=
                                                       0)
                                                     return;
                                                 float cosThetaMax =
                                                   (float)
                                                     Math.
                                                     sqrt(
                                                       Math.
                                                         max(
                                                           0,
                                                           1 -
                                                             r2 /
                                                             Vector3.
                                                             dot(
                                                               wc,
                                                               wc)));
                                                 OrthoNormalBasis basis =
                                                   OrthoNormalBasis.
                                                   makeFromW(
                                                     wc);
                                                 int samples =
                                                   state.
                                                   getDiffuseDepth(
                                                     ) >
                                                   0
                                                   ? 1
                                                   : getNumSamples(
                                                       );
                                                 float scale =
                                                   (float)
                                                     (2 *
                                                        Math.
                                                          PI *
                                                        (1 -
                                                           cosThetaMax));
                                                 Color c =
                                                   Color.
                                                   mul(
                                                     scale /
                                                       samples,
                                                     radiance);
                                                 for (int i =
                                                        0;
                                                      i <
                                                        samples;
                                                      i++) {
                                                     double randX =
                                                       state.
                                                       getRandom(
                                                         i,
                                                         0,
                                                         samples);
                                                     double randY =
                                                       state.
                                                       getRandom(
                                                         i,
                                                         1,
                                                         samples);
                                                     double cosTheta =
                                                       (1 -
                                                          randX) *
                                                       cosThetaMax +
                                                       randX;
                                                     double sinTheta =
                                                       Math.
                                                       sqrt(
                                                         1 -
                                                           cosTheta *
                                                           cosTheta);
                                                     double phi =
                                                       randY *
                                                       2 *
                                                       Math.
                                                         PI;
                                                     Vector3 dir =
                                                       new Vector3(
                                                       (float)
                                                         (Math.
                                                            cos(
                                                              phi) *
                                                            sinTheta),
                                                       (float)
                                                         (Math.
                                                            sin(
                                                              phi) *
                                                            sinTheta),
                                                       (float)
                                                         cosTheta);
                                                     basis.
                                                       transform(
                                                         dir);
                                                     float cosNx =
                                                       Vector3.
                                                       dot(
                                                         dir,
                                                         state.
                                                           getNormal(
                                                             ));
                                                     if (cosNx <=
                                                           0)
                                                         continue;
                                                     float ocx =
                                                       state.
                                                         getPoint(
                                                           ).
                                                         x -
                                                       center.
                                                         x;
                                                     float ocy =
                                                       state.
                                                         getPoint(
                                                           ).
                                                         y -
                                                       center.
                                                         y;
                                                     float ocz =
                                                       state.
                                                         getPoint(
                                                           ).
                                                         z -
                                                       center.
                                                         z;
                                                     float qa =
                                                       Vector3.
                                                       dot(
                                                         dir,
                                                         dir);
                                                     float qb =
                                                       2 *
                                                       (dir.
                                                          x *
                                                          ocx +
                                                          dir.
                                                            y *
                                                          ocy +
                                                          dir.
                                                            z *
                                                          ocz);
                                                     float qc =
                                                       ocx *
                                                       ocx +
                                                       ocy *
                                                       ocy +
                                                       ocz *
                                                       ocz -
                                                       r2;
                                                     double[] t =
                                                       Solvers.
                                                       solveQuadric(
                                                         qa,
                                                         qb,
                                                         qc);
                                                     if (t ==
                                                           null)
                                                         continue;
                                                     LightSample dest =
                                                       new LightSample(
                                                       );
                                                     dest.
                                                       setShadowRay(
                                                         new Ray(
                                                           state.
                                                             getPoint(
                                                               ),
                                                           dir));
                                                     dest.
                                                       getShadowRay(
                                                         ).
                                                       setMax(
                                                         (float)
                                                           t[0] -
                                                           0.001F);
                                                     dest.
                                                       setRadiance(
                                                         c,
                                                         c);
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
                          Color power) { float z = (float)
                                                     (1 -
                                                        2 *
                                                        randX2);
                                         float r = (float)
                                                     Math.
                                                     sqrt(
                                                       Math.
                                                         max(
                                                           0,
                                                           1 -
                                                             z *
                                                             z));
                                         float phi = (float)
                                                       (2 *
                                                          Math.
                                                            PI *
                                                          randY2);
                                         float x = r * (float)
                                                         Math.
                                                         cos(
                                                           phi);
                                         float y = r * (float)
                                                         Math.
                                                         sin(
                                                           phi);
                                         p.x = center.x +
                                                 x *
                                                 radius;
                                         p.y = center.y +
                                                 y *
                                                 radius;
                                         p.z = center.z +
                                                 z *
                                                 radius;
                                         OrthoNormalBasis basis =
                                           OrthoNormalBasis.
                                           makeFromW(
                                             new Vector3(
                                               x,
                                               y,
                                               z));
                                         phi = (float) (2 *
                                                          Math.
                                                            PI *
                                                          randX1);
                                         float cosPhi = (float)
                                                          Math.
                                                          cos(
                                                            phi);
                                         float sinPhi = (float)
                                                          Math.
                                                          sin(
                                                            phi);
                                         float sinTheta =
                                           (float)
                                             Math.
                                             sqrt(
                                               randY1);
                                         float cosTheta =
                                           (float)
                                             Math.
                                             sqrt(
                                               1 -
                                                 randY1);
                                         dir.x = cosPhi *
                                                   sinTheta;
                                         dir.y = sinPhi *
                                                   sinTheta;
                                         dir.z = cosTheta;
                                         basis.transform(
                                                 dir);
                                         power.set(radiance);
                                         power.mul((float)
                                                     (Math.
                                                        PI *
                                                        Math.
                                                          PI *
                                                        4 *
                                                        r2));
    }
    public float getPower() { return radiance.copy().mul(
                                                       (float)
                                                         (Math.
                                                            PI *
                                                            Math.
                                                              PI *
                                                            4 *
                                                            r2)).
                                getLuminance(
                                  ); }
    public Color getRadiance(ShadingState state) { if (!state.
                                                         includeLights(
                                                           ))
                                                       return Color.
                                                                BLACK;
                                                   state.
                                                     faceforward(
                                                       );
                                                   return state.
                                                     isBehind(
                                                       )
                                                     ? Color.
                                                         BLACK
                                                     : radiance;
    }
    public void scatterPhoton(ShadingState state, Color power) {
        
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169179358000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0Ya2wUx3l8fpxtDDY2fuAYYxwT1ZDeFtpQgSNSxzVgcgGX" +
                                                    "A0tx1DjjvTnf4r3dze6cfZi6CUgRCClWRE0KiLpSRERDSEirIBq1UanUlqC0" +
                                                    "laiipv3RpOqfRk35wY+mUdM2/b7Z3du9vUcOfvSknZud+Z7zPWcv3SLVlkk2" +
                                                    "Grp6aErVeYRleOSg+kCEHzKYFdkdfWCUmhaLD6nUsvbD2oTc+3rjx58+n2wK" +
                                                    "kZpx0kI1TeeUK7pm7WOWrs6weJQ0eqvDKktZnDRFD9IZKqW5okpRxeIDUbLM" +
                                                    "h8pJX9QVQQIRJBBBEiJIgx4UIC1nWjo1hBhU49ZT5NukIkpqDBnF42RdLhGD" +
                                                    "mjTlkBkVGgCFWnwfA6UEcsYkPVndbZ3zFD61UVr87hNNP6okjeOkUdFiKI4M" +
                                                    "QnBgMk4aUiw1yUxrMB5n8XGyUmMsHmOmQlVlTsg9TpotZUqjPG2y7CHhYtpg" +
                                                    "puDpnVyDjLqZaZnrZla9hMLUuPtWnVDpFOja5ulqa7gD10HBegUEMxNUZi5K" +
                                                    "1bSixTlZG8TI6tj3CAAAajjFeFLPsqrSKCyQZtt2KtWmpBg3FW0KQKv1NHDh" +
                                                    "pLMoUTxrg8rTdIpNcNIRhBu1twCqThwEonDSGgQTlMBKnQEr+exza8+DC4e1" +
                                                    "XVpIyBxnsory1wJSdwBpH0swk2kysxEbNkRfoG1vHQ8RAsCtAWAb5uq3bn/t" +
                                                    "/u5rb9sw9xSA2Tt5kMl8Qj4/ueJm11D/1koUo9bQLQWNn6O5cP9RZ2cgY0Dk" +
                                                    "tWUp4mbE3by271ePPXORfRQi9SOkRtbVdAr8aKWspwxFZeZOpjGTchYfIXVM" +
                                                    "iw+J/REShnlU0Zi9ujeRsBgfIVWqWKrRxTscUQJI4BGFYa5oCd2dG5QnxTxj" +
                                                    "EELC8JCvwtNM7J/454RKByxwd4nKVFM0XQLnZdSUkxKT9YlJON1kiprTliSn" +
                                                    "La6nJCutJVR9VrJMWdLNqey7rJtMUpWpJJdiRhLMEsV5BF3N+H8wyaCmTbMV" +
                                                    "FWCErmAKUCF6dulqnJkT8mL64eHbr028E8qGhHNGnPQCr4jDK4K8IoJXxMeL" +
                                                    "VFQIFquQp21jsNA0xDpkwYb+2Dd3P3m8txKcy5itguNF0F7Q0RFkWNaHvIQw" +
                                                    "ItKeDF7Z8eLjxyKfXHjI9kqpePYuiE2unZ49Mvb0l0IklJuGUTFYqkf0UUye" +
                                                    "2STZFwy/QnQbj3348eUX5nUvEHPyupMf8jExvnuDJjB1mcUhY3rkN/TQKxNv" +
                                                    "zfeFSBUkDUiUnIJjQw7qDvLIifMBN2eiLtWgcEI3U1TFLTfR1fOkqc96K8I3" +
                                                    "Voj5SjDKMnT8NnhWO5Eg/nG3xcBxle1LaOWAFiIn73jz2pkrZzduDfnTd6Ov" +
                                                    "IMYYt5PBSs9J9puMwfqfTo9+59StY48LDwGIewsx6MNxCFIDmAyO9dm3n/rj" +
                                                    "B++ffzfkeRWHGpmeVBU5AzTu87hA4lAheaHt+w5oKT2uJBQ6qTJ0zn83rt90" +
                                                    "5e8LTbY1VVhxneH+zyfgra9+mDzzzhP/7BZkKmQsXJ7mHph9AC0e5UHTpIdQ" +
                                                    "jsyR3605c51+D/Iq5DJLmWMiPRGhGRFHLwlTbRBjJLC3CYceI28vI1Y6xBu2" +
                                                    "QP3Fg2gH1l9f8P1rrzp59C+fCI3ywqdA2Qngj0uXznUObf9I4Ht+jNhrM/nJ" +
                                                    "CHoVD3fzxdQ/Qr01vwyR8Dhpkp1GaIyqafSWcSj+ltsdQbOUs59byO2qNZCN" +
                                                    "065gDPnYBiPIS4IwR2ic1weCpgFP+R54WpygaQkGTQURk60CpVeM63H4guuz" +
                                                    "YcNUZih2WaTWpHEFZRFgrZy0+1OvkoJOAn1RF6fYZFv7K/myrHJkWVVElkEc" +
                                                    "Bjiphy4zRlOGyqzSrjFqKiko2DNORyHNN38wfe7DV+28HPSDADA7vnjis8jC" +
                                                    "YsjXo92b1yb5cew+TRz0clu5z+BXAc9/8UGlcMGu081DTrPQk+0WDAPjf10p" +
                                                    "sQSLHX+9PP+TH8wfs9Vozm1RhuFsXv39f34dOf3nGwVqYiW0n8WN0AlPq2OE" +
                                                    "1iJG2OMYoUZm2MkWtHgKWhVoloDXl4szW+2kbTd9F2IWc5mhg6UtQeDrOOyy" +
                                                    "E8Rujr6v0xI6oR7tDpv2Imwec9iEzM04G7OpCUv2+RJRyFW2K6+zEN1ETE+b" +
                                                    "RWJAAMWSFHoWNPKaYv2yMPD5o4tL8b0vbQo5SfIRTuq4bnxRZTNM9clTj5Ry" +
                                                    "2pFHxQ3BS0gnXn7lKr+5cZvtKhuKR0oQ8frRv3Xu35588g6akLUBnYIkX370" +
                                                    "0o2d98knQ6Qym9fyLj25SAO52azeZHBL0/bn5LTurLlbXK/qcczdU7AR8Kzq" +
                                                    "laRQrn2780wnVGXg71jzXLA2P1jM/h8cHRFs1BJFT6RBBbw6bcQh8At5dXhS" +
                                                    "11VGtfzKKBZYbvezCZ5+R+n+u1W6SVR3LD8R+x6J67pAP1xCmadxgBoAdxWF" +
                                                    "F1KlakZX4p+rRyMudsGz2dFjc9l6+KU5XmLvBA7PcrJ8ivE92QqCizvLF2+L" +
                                                    "I96WuxLv+RJ7J3F4zhYvqs/ekXgtrnjbHPG2lS1ehXOzcbxgTcGsBd6A31WY" +
                                                    "IHO6hBLncDgF+UqxxhRLmbSvsrw8N8b97Y4C2+9UAXw9I6BeLCHfeRy+Dy0E" +
                                                    "HLLvhI+UJ+Ah3HYEnC1bwLCgGC4UGzVxPe2c0Su5wzdcg3TkFdYxhtczUVkf" +
                                                    "ElwvllD4hzhcAIOAwqNJnetaefriI5LpgqPvwl05/NUSe2/i8AZ0jyiaPmv3" +
                                                    "2GPlSYa+ftKR7OTdu8rPSoj3cxx+yskyEG+f09+KMy/PV9CBzzoSni1bQicn" +
                                                    "Cwk9A18vIeYNHH4BacOSKYcCVdLIkKeX+T5/4KWuI+8Dq/1RUH5tqbG2fenA" +
                                                    "e+JCn/1wVxcltYm0qvovGb55jWGyhCIEq7OvHHYb9dtAtfQ+x0AHJ/6FrL+x" +
                                                    "oW9CNQpCQyHBPz/Yu6CNDwyqpjPzA70HTS8A4fQPRoFaZ1+1MsTXV+Fl3v+W" +
                                                    "c7PH1kl8unbbnLT98XpCvry0e8/h21teEj1TtazSuTmkUhslYfujRrZVWleU" +
                                                    "mkurZlf/pyter1vvtoArcGh2vmQEZFtb+MI/nDK4uKLP/bj9jQcvLL0vvjj8" +
                                                    "DzQsomhTGAAA");
}
