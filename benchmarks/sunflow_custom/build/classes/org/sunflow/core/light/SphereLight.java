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
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0Ya2wUx3l8fpxtDDY2fuAYYxwT1ZDeFtpQgSNS4howuWCX" +
                                                    "I5biqHHWe3O+xXs7m905+zB1E5AiEFKsiJoUEHWkiIiEkJBWQTRqo1KpLUFp" +
                                                    "K1FFTfujSdU/jZrygx9No6Zt+n2zu7d7e48c/OhJOzc78z3ne85evEmqLZNs" +
                                                    "NJh2cEpjPEIzPHJAuy/CDxrUiuyJ3jcqmxaND2qyZe2HtQml943GTz57LtkU" +
                                                    "IjXjpEXWdcZlrjLd2kctps3QeJQ0eqtDGk1ZnDRFD8gzspTmqiZFVYsPRMky" +
                                                    "HyonfVFXBAlEkEAESYgg7fCgAGk51dOpQcSQdW49Sb5LKqKkxlBQPE7W5RIx" +
                                                    "ZFNOOWRGhQZAoRbfx0ApgZwxSU9Wd1vnPIVPbpQWv/94048qSeM4aVT1GIqj" +
                                                    "gBAcmIyThhRNTVLT2hGP0/g4WalTGo9RU5U1dU7IPU6aLXVKl3napNlDwsW0" +
                                                    "QU3B0zu5BgV1M9MKZ2ZWvYRKtbj7Vp3Q5CnQtc3T1dZwJ66DgvUqCGYmZIW6" +
                                                    "KFXTqh7nZG0QI6tj30MAAKjhFOVJlmVVpcuwQJpt22myPiXFuKnqUwBazdLA" +
                                                    "hZPOokTxrA1ZmZan6AQnHUG4UXsLoOrEQSAKJ61BMEEJrNQZsJLPPjf33r9w" +
                                                    "SN+th4TMcapoKH8tIHUHkPbRBDWprlAbsWFD9Hm57e1jIUIAuDUAbMNc+c6t" +
                                                    "b9zbffUdG+auAjAjkweowieUc5MrbnQN9m+tRDFqDWapaPwczYX7jzo7AxkD" +
                                                    "Iq8tSxE3I+7m1X2/evTpC/TjEKkfJjUK09Ip8KOVCksZqkbNXVSnpsxpfJjU" +
                                                    "UT0+KPaHSRjmUVWn9upIImFRPkyqNLFUw8Q7HFECSOARhWGu6gnmzg2ZJ8U8" +
                                                    "YxBCwvCQr8PTTOyf+OdElZIsRSVZkXVVZxL4LpVNJSlRhU2Y1GDS0OCINAmn" +
                                                    "nEzJ5rQlWWk9obHZCSVtcZaSLFORmDnlLksKM6mkqVNJLsWMJJgnivMIupzx" +
                                                    "/2SWQc2bZisqwChdwZSgQTTtZlqcmhPKYvrBoVuvT7wbyoaIc2ac9AKviMMr" +
                                                    "grwiglfEx4tUVAgWq5CnbXOw2DTEPmTFhv7Yt/c8cay3EpzNmK2C40bQXlDW" +
                                                    "EWRIYYNeghgWaVABL+148bGjkU/PP2B7qVQ8mxfEJldPzR4ee+orIRLKTcuo" +
                                                    "GCzVI/ooJtNs0uwLhmMhuo1HP/rk0vPzzAvMnDzv5It8TIz33qAJTKbQOGRQ" +
                                                    "j/yGHvnyxNvzfSFSBUkEEieXwdEhJ3UHeeTE/YCbQ1GXalA4wcyUrOGWm/jq" +
                                                    "edJks96K8I0VYr4SjLIMA6ENntVOZIh/3G0xcFxl+xJaOaCFyNE737p6+vKZ" +
                                                    "jVtD/nTe6CuQMcrt5LDSc5L9JqWw/qdTo987efPoY8JDAOLuQgz6cByEVAEm" +
                                                    "g2N95p0n//jhB+feC3lexaFmpic1VckAjXs8LpBINEhmaPu+R/QUi6sJVZ7U" +
                                                    "KDrnvxvXb7r894Um25oarLjOcO8XE/DWVz9Inn738X92CzIVChYyT3MPzD6A" +
                                                    "Fo/yDtOUD6IcmcO/W3P6mvwDyLOQ2yx1jop0RYRmRBy9JEy1QYyRwN4mHHqM" +
                                                    "vL2MWOkQb9gS9RcPop1Yj33B968RbfLIXz4VGuWFT4EyFMAfly6e7Rzc/rHA" +
                                                    "9/wYsddm8pMR9C4e7uYLqX+Eemt+GSLhcdKkOI3RmKyl0VvGoRmw3G4Jmqec" +
                                                    "/dzCblexgWycdgVjyMc2GEFeEoQ5QuO8PhA0DXjKd8HT4gRNSzBoKoiYbBUo" +
                                                    "vWJcj8OXXJ8NG6Y6I2PXRWpNOa6iLAKslZN2f+pVU9BZoC8ycYpNtrW/li/L" +
                                                    "KkeWVUVk2YHDACf10HXG5JShUau0a4yaagoK+IzTYUjzzR9On/3oNTsvB/0g" +
                                                    "AEyPLR7/PLKwGPL1bHfntU1+HLtvEwe93Fbuc/hVwPNffFApXLDrdvOg0zz0" +
                                                    "ZLsHw8D4X1dKLMFi518vzf/k5fmjthrNuS3LEJzNa7//z68jp/58vUBNrIR2" +
                                                    "tLgROuFpdYzQWsQIex0j1CgUO9uCFk9B6wLNE/D6anFmq5207abvQsxiLjN0" +
                                                    "sLQlCHwTh912gtjD0feZXEIn1KPdYdNehM2jDpuQuRlnYzY1Yck+XyIKucp2" +
                                                    "5XUWopuIsbRZJAYEUCwpQ8+CRl5TrH8WBj53ZHEpPvLSppCTJB/ipI4z48sa" +
                                                    "naGaT556pJTTjjwsbgxeQjr+yqtX+I2N22xX2VA8UoKI1478rXP/9uQTt9GE" +
                                                    "rA3oFCT5ysMXr++6RzkRIpXZvJZ3CcpFGsjNZvUmhVubvj8np3Vnzd3ielWP" +
                                                    "Y+6ego2AZ1WvJIVy7dudZzqhKgV/x5rngrX5wWL2/47RYcFGK1H0RBpUwavT" +
                                                    "RhwCv5BXhycZ06is51dGsUBzu59N8PQ7SvffqdJNorpj+YnY90pcZwL9UAll" +
                                                    "nsIBagDcXVReSJWqGabGv1CPRlzsgmezo8fmsvXwS3OsxN5xHJ7hZPkU5Xuz" +
                                                    "FQQXd5Uv3hZHvC13JN5zJfZO4PCsLV6Uzd6WeC2ueNsc8baVLV6Fc7NxvGBN" +
                                                    "wawF3oDfWaggc6qEEmdxOAn5SrXGVEudtK+2vDw3xv3tjgLbb1cBfD0toF4s" +
                                                    "Id85HF6AFgIO2XfCh8sT8CBuOwLOli1gWFAMF4qNmjhLO2f0au7wLdcgHXmF" +
                                                    "dYzi9UxU1gcE1wslFP4hDufBIKDwaJJxppenLz4imS44+i7ckcNfKbH3Fg5v" +
                                                    "QveIorFZu8ceK08y9PUTjmQn7txVflZCvJ/j8FNOloF4+5z+Vpx5eb6CDnzG" +
                                                    "kfBM2RI6OVlI6Bn4Wgkxr+PwC0gbliJzKFAljQx5epnv8wde6jryPrjaHwmV" +
                                                    "15caa9uXHnlfXOizH/LqoqQ2kdY0/yXDN68xTJpQhWB19pXDbqN+G6iW3ucY" +
                                                    "6ODEv5D1Nzb0DahGQWgoJPjnB3sPtPGBQdV0Zn6g96HpBSCc/sEoUOvsq1aG" +
                                                    "+PoqvMz733Ju9tg6iU/ZbpuTtj9mTyiXlvbsPXRry0uiZ6pWNHluDqnURknY" +
                                                    "/qiRbZXWFaXm0qrZ3f/Zijfq1rst4Aocmp0vGQHZ1ha+8A+lDC6u6HM/bn/z" +
                                                    "/vNLH4gvDv8DXxCMOGMYAAA=");
}
