package org.sunflow.core;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public final class Ray {
    public float ox;
    public float oy;
    public float oz;
    public float dx;
    public float dy;
    public float dz;
    private float tMin;
    private float tMax;
    private static final float EPSILON = 0;
    private Ray() { super(); }
    public Ray(float ox, float oy, float oz, float dx, float dy, float dz) {
        super(
          );
        this.
          ox =
          ox;
        this.
          oy =
          oy;
        this.
          oz =
          oz;
        this.
          dx =
          dx;
        this.
          dy =
          dy;
        this.
          dz =
          dz;
        float in =
          1.0F /
          (float)
            Math.
            sqrt(
              dx *
                dx +
                dy *
                dy +
                dz *
                dz);
        this.
          dx *=
          in;
        this.
          dy *=
          in;
        this.
          dz *=
          in;
        tMin =
          EPSILON;
        tMax =
          Float.
            POSITIVE_INFINITY;
    }
    public Ray(Point3 o, Vector3 d) { super();
                                      ox = o.x;
                                      oy = o.y;
                                      oz = o.z;
                                      dx = d.x;
                                      dy = d.y;
                                      dz = d.z;
                                      float in = 1.0F / (float) Math.sqrt(
                                                                       dx *
                                                                         dx +
                                                                         dy *
                                                                         dy +
                                                                         dz *
                                                                         dz);
                                      dx *= in;
                                      dy *= in;
                                      dz *= in;
                                      tMin = EPSILON;
                                      tMax = Float.POSITIVE_INFINITY; }
    public Ray(Point3 a, Point3 b) { super();
                                     ox = a.x;
                                     oy = a.y;
                                     oz = a.z;
                                     dx = b.x - ox;
                                     dy = b.y - oy;
                                     dz = b.z - oz;
                                     tMin = EPSILON;
                                     float n = (float) Math.sqrt(dx * dx +
                                                                   dy *
                                                                   dy +
                                                                   dz *
                                                                   dz);
                                     float in = 1.0F / n;
                                     dx *= in;
                                     dy *= in;
                                     dz *= in;
                                     tMax = n - EPSILON; }
    public Ray transform(Matrix4 m) { if (m == null) return this;
                                      Ray r = new Ray();
                                      r.ox = m.transformPX(ox, oy, oz);
                                      r.oy = m.transformPY(ox, oy, oz);
                                      r.oz = m.transformPZ(ox, oy, oz);
                                      r.dx = m.transformVX(dx, dy, dz);
                                      r.dy = m.transformVY(dx, dy, dz);
                                      r.dz = m.transformVZ(dx, dy, dz);
                                      r.tMin = tMin;
                                      r.tMax = tMax;
                                      return r; }
    public void normalize() { float in = 1.0F / (float) Math.sqrt(dx * dx +
                                                                    dy *
                                                                    dy +
                                                                    dz *
                                                                    dz);
                              dx *= in;
                              dy *= in;
                              dz *= in; }
    public final float getMin() { return tMin; }
    public final float getMax() { return tMax; }
    public final Vector3 getDirection() { return new Vector3(dx, dy, dz);
    }
    public final boolean isInside(float t) { return tMin < t && t < tMax;
    }
    public final Point3 getPoint(Point3 dest) { dest.x = ox + tMax * dx;
                                                dest.y = oy + tMax * dy;
                                                dest.z = oz + tMax * dz;
                                                return dest; }
    public final float dot(Vector3 v) { return dx * v.x + dy * v.y + dz *
                                          v.
                                            z; }
    public final float dot(float vx, float vy, float vz) { return dx * vx +
                                                             dy *
                                                             vy +
                                                             dz *
                                                             vz; }
    public final void setMax(float t) { tMax = t; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVZa2wc1RW+u347jr12Xsbk6TiojulO0zRtk9CU4DqJwzo2" +
                                                    "dpKqRsWMZ++uJ56dmczctdehLiRSSESrFIHzasFqK1NeAUeoEX1B86PlIfIn" +
                                                    "bZWqqoAIqQJBoyo/CLRA6Tl3nju7Xq8l19I9O3Mf537nnucdn7tGykyDtOma" +
                                                    "MpZUNBalGRY9oGyKsjGdmtHdsU09omHSeLsimuZe6BuQms/X3fjk4aFImJT3" +
                                                    "k0WiqmpMZLKmmr3U1JQRGo+ROq+3Q6Epk5FI7IA4IgppJitCTDbZ1hhZ4FvK" +
                                                    "SEvMgSAABAEgCByCsN2bBYsWUjWdascVosrMg+T7JBQj5bqE8BhZk81EFw0x" +
                                                    "ZbPp4RIAh0p83w9C8cUZg6x2ZbdkzhH4ZJswcfqeyAslpK6f1MlqH8KRAASD" +
                                                    "TfpJTYqmBqlhbo/Habyf1KuUxvuoIYuKfIjj7icNppxURZY2qHtI2JnWqcH3" +
                                                    "9E6uRkLZjLTENMMVLyFTJe68lSUUMQmyLvVktSTcgf0gYLUMwIyEKFFnSemw" +
                                                    "rMYZWRVc4crYcidMgKUVKcqGNHerUlWEDtJg6U4R1aTQxwxZTcLUMi0NuzDS" +
                                                    "NCNTPGtdlIbFJB1gpDE4r8cagllV/CBwCSNLgtM4J9BSU0BLPv1c23PbifvU" +
                                                    "XWqYY45TSUH8lbBoZWBRL01Qg6oStRbWrI+dEpe+dDxMCExeEphszXnxe9dv" +
                                                    "v3XlxdesOTfnmdM9eIBKbECaGqy9vLy9dXMJwqjUNVNG5WdJzs2/xx7ZmtHB" +
                                                    "85a6HHEw6gxe7H3lOw88Qz8Ik+pOUi5pSjoFdlQvaSldVqixk6rUEBmNd5Iq" +
                                                    "qsbb+XgnqYDnmKxSq7c7kTAp6ySlCu8q1/g7HFECWOARVcCzrCY051kX2RB/" +
                                                    "zuiEkIXQSAO0GmL98V9GdglDWooKoiSqsqoJYLtUNKQhgUqaYIopXQGtmWk1" +
                                                    "oWijgmlIgmYk3XdJM6jQK45F0aL0eeSVQdyR0VAIjnR50KEV8IVdmhKnxoA0" +
                                                    "kb6j4/rzA2+EXQO3JWZkMXCP2tyjyD0K3EkoxJkuxl0sHcEJD4OvQhSrae37" +
                                                    "7u57jzeXgHHoo6VwPKUwtRnQ21t3SFq759CdPGxJYFWNP7/7WPTjJ79pWZUw" +
                                                    "c/TNu5pcPDN6eP/9XwqTcHYYRVGgqxqX92Dwc4NcS9B98vGtO/bejelT45rn" +
                                                    "SFlx2fbv3JXon83BQzc0icYh4nns168WLwy8NN4SJqXg9BDomAiGCTFkZXCP" +
                                                    "LD/d6sQ8lKUMBE5oRkpUcMgJVNVsyNBGvR5uDbX8ud4x3Dpoi2xL5r84ukhH" +
                                                    "utiyHtRyQAoeU3f8+uLZCz9u2xz2h986X0Lro8xy5nrPSPYalEL/m2d6Hj15" +
                                                    "7djd3EJgxtp8G7QgbQfXBpXBsR597eDf3n5r6i9h16pCjFTohjwCHp8BJrd4" +
                                                    "24DnKxB9UPkt+9SUFpcTsjioULTOT+vWbbjwzxMRS50K9DjWcOvsDLz+m+4g" +
                                                    "D7xxz0crOZuQhJnHE92bZp3AIo/zdsMQxxBH5vCfVpx9VXwcAiMEI1M+RHl8" +
                                                    "IVw0ws9e4Lpaz2k0MLYByWo9Z4x3NLlKXoCdtdDW2kpem1/JSJs5XYfkC84B" +
                                                    "l+vpQUWWMgEk5Xy8HERrLVAjGXIKwvaInVeE8Ya3hx977znLu4NJKDCZHp94" +
                                                    "6PPoiYmwL1OvzUmW/jVWtubiLLTE/xz+QtD+iw3Fxg4rWje02yljtZszdB2N" +
                                                    "aE0hWHyLHe9Oj//2qfFjlhgN2YmqA+qw5658dil65urreWIpuIkmcnv7Rj7C" +
                                                    "wW8uoPQOJBuLV/oWW+lb8iodyabAbmHOMczflzCyzB/8U5AIIRVDIbXRGW/M" +
                                                    "Gd9PMQZu5DvsLiBKD5KdxYtyly3KXXMVBV/v5IRP3VcA07eR9OZiskA18req" +
                                                    "wja/A4tSX0b7T7cyeOSdj7kt5OSkPG4QWN8vnHusqX3bB3y9lxxw9apMbk6H" +
                                                    "At5b++VnUh+Gm8v/GCYV/SQi2beD/aKSxhDcDxWx6VwZ4AaRNZ5d3Vql3FY3" +
                                                    "+S0P+qFv22Ba8uwfnnE2PlcHMhFX8nKnuHJ+/UoOEf4wwPXMSFjLuF4TsfTV" +
                                                    "P3dugy63sXngRl1uh4rgVmtzq52B25DDLV6MpLNxG3a5FSPpbNxUl1sBSXl1" +
                                                    "sYxYFQZxfvNwO4ikhYF1dMlqEfwiNr/IDPyYx08scHY34fI2aPU2v/ocfiG3" +
                                                    "dkE3iHbCBTJJjYZ3fjr10eFjXw9j3i4bQXcBT4x48/ak8eL74LmTKxZMXP0B" +
                                                    "L1cc1qP582wJPrZCsjX5HRrzhKyKSgbqm46evs5Y9x6fHJn8PHic+xqS+/3h" +
                                                    "imBSWzHTLZEntKkjE5Px7ic2hO0ouJ2RKqbpX1ToCFV8rKqRU1YR38XvxV7E" +
                                                    "eejpZ19kl9u2WKlx/cxRMrjw1SPvN+3dNnTvHEr3VQGZgiyf7jr3+s5bpEfC" +
                                                    "pMQNXDlX/exFW7PDVbVBWdpQ92YFrZWuAXF7boSWtA0oWXRmClnV1cxJtEtk" +
                                                    "hpz5CufwowIJ61EkP0R9GaJq4g3AZ+s5WfV4tqvfDG3Mxj5WNHb/9mcLjP0E" +
                                                    "ySmApvJ7CZS3fNoWJNssPLeDk45ocnxWuOj2ZAW0ozbco3nhFukY+fH+osDY" +
                                                    "U0h+Bv6ZpG6QKh70CRv0ifkHfb7A2AtInrVB25FwVtA8Kq6DdtoGfXr+Qf+m" +
                                                    "wNjvkFxgpAZAf0s2rDsU9nUVB30NtCkb+tT8QLedlZ8fZ/GHAvhfQfJ7Ripl" +
                                                    "E6KKHM9r9BWDmqZQUZ1VJIwuZDW0aVuk6XkXySqKLxcQ6c9ILoFIoBJe/PNl" +
                                                    "xZl/E7SXbewvzzv2Ls7i7wWwv4nkCiMlcY3NwWsR9iUb9qX5gV3iJfrghe8f" +
                                                    "BQR4F8nVuQhQ73jwFVuAK/8nN/hXAdjXkbyPBY0bex7Pc60CuXrFMfxA0pjz" +
                                                    "3wbrC7n0/GRd5bLJfX/lX8fcr9hVMVKZSCuK/3Lhey7XDZqQ+bZV1lVD5z83" +
                                                    "GIkEv2ZCDsIfDu5Da9q/GVngmwYOaz/5J30K4GESPn6mO5ncVwVal6YMyarF" +
                                                    "9GBltjarRuL/mXHqmbT1v5kBaXpy9577rn/1CV4clUmKeIiX3ZUxUmF983Nr" +
                                                    "ojUzcnN4le9q/aT2fNU6p9arRdLgMwkftlX5P4d1pHTGP2Ad+tWyX9725ORb" +
                                                    "vML9H/xef7AyGwAA");
}
