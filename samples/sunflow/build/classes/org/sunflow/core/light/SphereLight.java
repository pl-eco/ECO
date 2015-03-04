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
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYa2wUx3l8fpxtDDY2fuAYYxwT1ZDeFtpQgSNSxzVgcgGX" +
                                                    "I5biqHHWe3O+hb2dze6cfZi6CUgRCClWRE0KiDpSRERCSEirIBo1UajUhKC0" +
                                                    "laiipv3RpOqfRk35wY+mUdM2/b7Z3du9vUcOpJ60c7Mz33O+5+yFG6TaMsl6" +
                                                    "g2kHpjTGIzTDI/u0eyL8gEGtyM7oPaOyadH4kCZb1l5Ym1B6X2v87Itnkk0h" +
                                                    "UjNOWmRdZ1zmKtOtPdRi2jSNR0mjtzqs0ZTFSVN0nzwtS2mualJUtfhAlCzx" +
                                                    "oXLSF3VFkEAECUSQhAjSoAcFSEupnk4NIYasc+tx8kNSESU1hoLicbIml4gh" +
                                                    "m3LKITMqNAAKtfg+BkoJ5IxJerK62zrnKXxivbTw40ebflZJGsdJo6rHUBwF" +
                                                    "hODAZJw0pGhqkprWYDxO4+NkuU5pPEZNVdbUWSH3OGm21Cld5mmTZg8JF9MG" +
                                                    "NQVP7+QaFNTNTCucmVn1EirV4u5bdUKTp0DXNk9XW8NtuA4K1qsgmJmQFeqi" +
                                                    "VO1X9Tgnq4MYWR37HgAAQA2nKE+yLKsqXYYF0mzbTpP1KSnGTVWfAtBqlgYu" +
                                                    "nHQWJYpnbcjKfnmKTnDSEYQbtbcAqk4cBKJw0hoEE5TASp0BK/nsc2PXvfMH" +
                                                    "9R16SMgcp4qG8tcCUncAaQ9NUJPqCrURG9ZFn5Xb3joaIgSAWwPANszlH9z8" +
                                                    "zt3dV96zYe4oALN7ch9V+IRydnLZ9a6h/s2VKEatwSwVjZ+juXD/UWdnIGNA" +
                                                    "5LVlKeJmxN28sufdh588Tz8NkfoRUqMwLZ0CP1qusJShatTcTnVqypzGR0gd" +
                                                    "1eNDYn+EhGEeVXVqr+5OJCzKR0iVJpZqmHiHI0oACTyiMMxVPcHcuSHzpJhn" +
                                                    "DEJIGB7ybXiaif0T/5yMSUmWopKsyLqqMwl8l8qmkpSowiRLThkaWM1K6wmN" +
                                                    "zUiWqUjMnMq+K8ykkqZOJbkUM5JgiyjOI+hfxv+NcgZ1apqpqIDj7goGuwZx" +
                                                    "soNpcWpOKAvp+4dvvjrxfijr/M5pcNILvCIOrwjyigheER8vUlEhWKxAnrY1" +
                                                    "wRb7Iaoh3zX0x76/87GjvZXgRsZMFRwkgvaCZo4gwwob8kJ/RCQ4Bfyv4/lH" +
                                                    "jkQ+P3ef7X9S8TxdEJtcOTlzaOyJb4RIKDfhomKwVI/oo5gms+mwLxhoheg2" +
                                                    "Hvnks4vPzjEv5HIyuJMJ8jExknuDJjCZQuOQGz3y63rkSxNvzfWFSBWkB0iJ" +
                                                    "XAYXhmzTHeSRE9EDbnZEXapB4QQzU7KGW25Kq+dJk814K8I3lon5cjDKEnTx" +
                                                    "NnhWOj4v/nG3xcBxhe1LaOWAFiL7bnvjyqlLp9dvDvkTdaOv9MUot8N+ueck" +
                                                    "e01KYf1PJ0d/dOLGkUeEhwDEnYUY9OE4BEkATAbH+tR7j//x44/OfhDyvIpD" +
                                                    "NUxPaqqSARp3eVwgRWiQptD2fQ/pKRZXE6o8qVF0zn83rt1w6e/zTbY1NVhx" +
                                                    "neHurybgra+8nzz5/qP/7BZkKhQsUZ7mHph9AC0e5UHTlA+gHJlDv1t16qr8" +
                                                    "E8igkLUsdZaKRESEZkQcvSRMtU6MkcDeBhx6jLy9jFjpEG/Y7PQXD6JtWGl9" +
                                                    "wfev3drk4b98LjTKC58CBSaAPy5dONM5tPVTge/5MWKvzuQnI+hKPNyN51P/" +
                                                    "CPXWvBMi4XHSpDgtz5ispdFbxqHMW24fBG1Rzn5uybbr00A2TruCMeRjG4wg" +
                                                    "LwnCHKFxXh8ImgY85TvgaXGCpiUYNBVETDYLlF4xrsXha67Phg1TnZaxnyK1" +
                                                    "phxXURYB1spJuz/1qinoGdAXmTjFJtva38qXZYUjy4oisgziMMBJPfSTMbu2" +
                                                    "lHaNUVNNQWmednoHaa754/1nPnnFzstBPwgA06MLx76MzC+EfN3YnXkNkR/H" +
                                                    "7sjEQS+1lfsSfhXw/BcfVAoX7IrcPOS0BT3ZvsAwMP7XlBJLsNj214tzv3hx" +
                                                    "7oitRnNuMzIMZ/PK7//z68jJP18rUBMrodEsboROeFodI7QWMcIuxwg1CsWe" +
                                                    "taDFU9CUQFsEvL5ZnNlKJ2276bsQs5jLDB0sbQkC38Vhh50gdnL0fSaX0An1" +
                                                    "aHfYtBdh87DDJmRuxNmYTU1Yss+XiEKusl15nYXoJmIsbRaJAQEUS8rQs6CR" +
                                                    "VxXrjIWBzx5eWIzvfmFDyEmSD3BSx5nxdY1OU80nTz1SymlHHhR3AS8hHXvp" +
                                                    "5cv8+vottqusKx4pQcSrh//WuXdr8rFbaEJWB3QKknzpwQvXtt+lHA+Rymxe" +
                                                    "y7ve5CIN5GazepPCfUzfm5PTurPmbnG9qscxd0/BRsCzqleSQrn27c4znVCV" +
                                                    "gr9jzXPB2vxgMft/cHREsNFKFD2RBlXw6rQRh8Av5NXhScY0Kuv5lVEs0Nzu" +
                                                    "ZwM8/Y7S/berdJOo7lh+IvaNEdeZQD9YQpkncIAaALcSlRdSpWqaqfGv1KMR" +
                                                    "F7vg2ejosbFsPfzSHC2xdwyHpzhZOkX5rmwFwcXt5Yu3yRFv022J90yJveM4" +
                                                    "PG2LF2UztyReiyveFke8LWWLV+HcbBwvWFUwa4E34BcUKsicLKHEGRxOQL5S" +
                                                    "rTHVUiftSysvz41xf6ujwNZbVQBfTwmo50vIdxaH56CFgEP2nfCh8gQ8gNuO" +
                                                    "gDNlCxgWFMOFYqMmztLOGb2cO3zPNUhHXmEdo3g9E5X1PsH1fAmFf4rDOTAI" +
                                                    "KDyaZJzp5emLj0im846+87fl8JdL7L2Bw+vQPaJobMbuscfKkwx9/bgj2fHb" +
                                                    "d5W3S4j3Sxze5GQJiLfH6W/FmZfnK+jApx0JT5ctoZOThYSega+WEPMaDr+C" +
                                                    "tGEpMocCVdLIkKeX+D5/4KWuI+9Tqv35T3l1sbG2ffGhD8WFPvuJri5KahNp" +
                                                    "TfNfMnzzGsOkCVUIVmdfOew26reBaul9joEOTvwLWX9jQ1+HahSEhkKCf36w" +
                                                    "D0AbHxhUTWfmB/oQml4AwukfjAK1zr5qZYivr8LLvP8t52aPrZP4SO22OWn7" +
                                                    "M/WEcnFx566DNze9IHqmakWTZ2eRSm2UhO2PGtlWaU1Rai6tmh39Xyx7rW6t" +
                                                    "2wIuw6HZ+ZIRkG114Qv/cMrg4oo++/P21+89t/iR+OLwP04yWd89GAAA");
}
