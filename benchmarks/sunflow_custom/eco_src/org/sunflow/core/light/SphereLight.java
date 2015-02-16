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
                                                     getPoint().
                                                     distanceToSquared(
                                                       center) >
                                                     r2; }
    public void getSamples(ShadingState state) { if (this.
                                                       getNumSamples() <=
                                                       0)
                                                     return;
                                                 Vector3 wc =
                                                   Point3.
                                                   sub(
                                                     center,
                                                     state.
                                                       getPoint(),
                                                     new Vector3(
                                                       ));
                                                 float l2 =
                                                   wc.
                                                   lengthSquared();
                                                 if (l2 <=
                                                       r2)
                                                     return;
                                                 float topX =
                                                   wc.
                                                     x +
                                                   state.
                                                     getNormal().
                                                     x *
                                                   radius;
                                                 float topY =
                                                   wc.
                                                     y +
                                                   state.
                                                     getNormal().
                                                     y *
                                                   radius;
                                                 float topZ =
                                                   wc.
                                                     z +
                                                   state.
                                                     getNormal().
                                                     z *
                                                   radius;
                                                 if (state.
                                                       getNormal().
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
                                                   getDiffuseDepth() >
                                                   0
                                                   ? 1
                                                   : this.
                                                   getNumSamples();
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
                                                           getNormal());
                                                     if (cosNx <=
                                                           0)
                                                         continue;
                                                     float ocx =
                                                       state.
                                                         getPoint().
                                                         x -
                                                       center.
                                                         x;
                                                     float ocy =
                                                       state.
                                                         getPoint().
                                                         y -
                                                       center.
                                                         y;
                                                     float ocz =
                                                       state.
                                                         getPoint().
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
                                                             getPoint(),
                                                           dir));
                                                     dest.
                                                       getShadowRay().
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
                                getLuminance(); }
    public Color getRadiance(ShadingState state) { if (!state.
                                                         includeLights())
                                                       return Color.
                                                                BLACK;
                                                   state.
                                                     faceforward();
                                                   return state.
                                                     isBehind()
                                                     ? Color.
                                                         BLACK
                                                     : radiance;
    }
    public void scatterPhoton(ShadingState state, Color power) {
        
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1169179358000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALUZa2wUx3l8fhycHdkYsHnaxpCkPHIXSKGtHQVcFxLDAcYH" +
                                                   "hpgkznh37m5hb3ez\nO2efDUqDIgVCWhJoKrW0PFRRAWkSUqURfSQpKG9Qpa" +
                                                   "RqUxU1tBVVWrVNpbRVSh8/+n2ze7d7ew8M\nVizt7NzO937NN+NnPyLVlknm" +
                                                   "SFaYjxrMCnfHeqlpMblbpZa1GT4NSm9UT+49uU7TA6QiSgKKzEl9\nVLIiMu" +
                                                   "U0osiRni91Zkyy2NDV0YSq8zDL8PAOdblDb210eQHBrUfPNu45UdUaINVRUk" +
                                                   "81TeeUK7q2\nWmUpi5OG6A46TCNprqiRqGLxzii5iWnpVLeuWZxq3HqIPEwq" +
                                                   "o6TGkJAmJ/OiWeYRYB4xqElTEcE+\n0ivYAoWpJuNU0ZjclWMHmEvyMUFsB6" +
                                                   "+vEBqITMLFflBHSABat+W0trUtUNWoPNW/Yvfx05WkfoDU\nK1oMiUmgCQd+" +
                                                   "A6QuxVJDzLS6ZJnJA2SKxpgcY6ZCVWVMcB0gjZaS0ChPm8zqY5auDiNgo5U2" +
                                                   "mCl4\nZj9GSZ2EOplpietmzkZxhaly9ld1XKUJULvJVdtWdw1+BwVDCghmxq" +
                                                   "nEsihVOxUNPN7qx8jpuGAd\nAABqMMV4Us+xqtIofCCNti9VqiUiMW4qWgJA" +
                                                   "q/U0cOFkVkmiaGuDSjtpgg1yMsMP12svAdRkYQhE\n4WS6H0xQAi/N8nnJ45" +
                                                   "/FTZ/sO/XtV1eJ2K6SmaSi/CFAavEh9bE4M5kmMRvxajr8dM+96TkBQgB4\n" +
                                                   "ug/Yhum6+eyW6J9+2mrDzC4Cs3FoB5P4oLThYGvfrrt1UoliTDJ0S0Hn52ku" +
                                                   "0qHXWenMGJC1TTmK\nuBjOLp7re/PeR55hfwmQUA+pkXQ1nYI4miLpKUNRmX" +
                                                   "k305hJOZN7yGSmyd1ivYcEYR6FkLe/bozH\nLcZ7SJUqPtXo4jeYKA4k0EST" +
                                                   "Ya5ocT07NyhPinnGIIQE4SGfg6eR2H/izcnycMRKa3FVH4lYphTR\nzUTut6" +
                                                   "SbLKIqiSSPxIwkmDqK8zCGj8HJpkhST7EIlaimaHokoUDCSvptMhvG940Qza" +
                                                   "C0jSMVFVj+\n/GmsQgbco6syMwelk1cu7F697vF9dohgWDt6ctIOvMIOrzDy" +
                                                   "CgteYQ8vUlEhWExDnrafwMo7IV+h\nstUtjN2/9sF97ZUQIMZIFZgIQdtBI0" +
                                                   "eQ1ZLe7SZ1j6h/EkTWjO9s3xu+enKlHVmR0rW3KHbtu89d\nPP6PukUBEihe" +
                                                   "GFFBKM0hJNOL1TRX8Bb4U6kY/b/tX//i+xc/+IybVJwsKMj1QkzM1Xa/K0xd" +
                                                   "YjJU\nP5f8iZn1lVtJ/8EAqYICAEVPyA/1pMXPIy9nO7P1D3UJRkltXDdTVM" +
                                                   "WlbNEK8aSpj7hfRIw0iPlU\ncE4tBnETPDOdqBZvXJ1u4NhkxxR626eFqK9X" +
                                                   "H625/Vcv174hzJItxfWezS7GuJ3YU9xg2WwyBt8/\n+Ebv177+0d7tIlKcUO" +
                                                   "GwA6aHVEXKAMotLgpktApVBR25YIuW0mUlrtAhlWHE/a/+5qUv/fVAg+0a\n" +
                                                   "Fb5kPbvk2gTc7zO/SB65+MC/WgSZCgl3FFcNF8zWZqpLucs06SjKkdnz87nf" +
                                                   "fIsegYIHRcZSxpio\nG0RoRoQdI8Lui8QY9q0txaEdaC8pEfpF9u9Bafczif" +
                                                   "b0Q+/8SEhdS72NgNcN66nRaXseh/lo3WZ/\n9t5DrSTAffbchvsa1HP/BYoD" +
                                                   "QFGCfdPaaELZyOQ50YGuDl46/1rTg+9VksAaElJ1Kq+hIv7JZAg8\nZiWh4m" +
                                                   "SMlatEbDWMTMJRqEyEEWY5Bsh4fmHztrB0+q/B3d/NnMGhJaeiFzYeEQYomf" +
                                                   "hFNj8fnbFX\ntxy9+jN+WdBxMxCx52UKyyl0TC7u598fnlLzwrFUgAQHSIPk" +
                                                   "9HT9VE1jnA9AC2JlGz3o+/LW89sJ\ne+/szFWYOf7s97D1575bxmGO0Div86" +
                                                   "V7HVp7NjxTnXSf6k/3CiImKwXKAjHemkvOoGEqwxT7PDLJ\npLKCcgiQZk6a" +
                                                   "vRuHkoJeBpNON+36geMyHFbZrl5eMiQ6CoWd5gg7rYSwPTh0cRKCjjpGU4YK" +
                                                   "IWuS\nGd4DgKmkoJEYFlXzymPtr7y95dhee6cpE2p5WIPSl3/7u52VT54fsv" +
                                                   "H88eQDPthy4sMXr/RNs6uS\n3XHOL2j6vDh21ykcVm9ghs4rx0FAv7543rMP" +
                                                   "9112JGrM751WgzX+OPoau/XOr/6+yEZfCX2xzzdr\nr9M3s+CZ7vhmegnfbH" +
                                                   "V8UyMx7MOLRksKGi1o9UCgO3wSbbtOiWY6u1l2Vysm0QNZiTCC05YgEDVs\n" +
                                                   "Rps4JpVO/ZYZvE450BrNjhzNJeSIO3IETMGC+lgmyrAUQXKLp2YGsnadU9C+" +
                                                   "iZYtpqfNEqkqgGJJ\nihUegm5uqYOFCLi92z6ue4y+fn/A2bT6ONR53bhNZc" +
                                                   "NM9cgTQkp5Pd96cZRya+b+0987y99b3GGH\n7qLSSehHXNRxfKy148wTN9Dp" +
                                                   "tfp085OeMjx7U2VSeTsgTnt2CS44JeYjdeYX3hDIkza1zXnlty2/\n2+qEp8" +
                                                   "2Jjbai3ZbrXbdVCOT7uaXAhUJVBimGvUgWrMkLFrPfXb09gs2uMs3IHhygyt" +
                                                   "ekDRnqvT9H\ngkO6rjKqudE6eq0Eye704gfPt8hSeBY6Fll4oxZpEL0JbqNh" +
                                                   "+1Q+Hht8pYwNDuHwOMczocL9Fqga\n1hXZVX//RNRvgWeZo/6ycavvFfVImb" +
                                                   "VjOBzm5KYE4xty+yN+7HXl/9ZE5V/hyL/ihuQ/WWbtNA4n\nbPmj+khx+b87" +
                                                   "Eflvh6fDkb9j3PJXOIdbJ8rmFq2pEIZ4PcYEme+X0fIlHJ6HaqpY/YqlDNk3" +
                                                   "Eo+6\nGp6ZiIbI7S5Hw7s+RQ1fKaPheRx+DE0a+NHjxKddFX8yERVHkZuj4s" +
                                                   "i4VQwKikF/dtfIetpxwQV3\nuFbvkl2fUbDez/BMdEdRAp5WWcj5Thkb/gKH" +
                                                   "NyFKwIa9SZ3rms+Eb03EhNhqH3BMeOCG8vg3ZdYu\n4/BrODig7PqIfbSiru" +
                                                   "iXJprChxzRD32KAf5hGQX/jMMVTmpBwb5xHo5c9f8wEfUxpw876h8et/q+\n" +
                                                   "DbS8+uOK3X+WMc9/cPgY6rglUQ5tStH4/ft4rQC9Sa3nShLvZGYU/OPCvmyX" +
                                                   "opd23fdJ9Jf/Fpdr\nuQvx2iiZFE+rqvfY7JnXGCaLK0LyWvsQbeCrosLXUL" +
                                                   "hXpHB0EG+UE1p8AV0FfYkfGpoHfHnBgqCN\nBwwaLGfmBQrBmQ2AcFprFOl6" +
                                                   "7MuDTJ6Z0C7z83pr8Z+kbP+btv+XNChte257W+aJzU+JprpaUunY\nGJIJRU" +
                                                   "nQvlLM9dDzSlLL0nqXvHCm/+Xnv5A9I4grp2nOPWJBJC+zV8u4Hfr24vd4q1" +
                                                   "MGFzdvYz9s\n/sGdJ49eDoibxP8D8szXbAAcAAA=");
}
