package org.sunflow.math;
public class BoundingBox {
    private Point3 minimum;
    private Point3 maximum;
    public BoundingBox() { super();
                           minimum = new Point3(Float.POSITIVE_INFINITY, Float.
                                                                           POSITIVE_INFINITY,
                                                Float.
                                                  POSITIVE_INFINITY);
                           maximum = new Point3(Float.NEGATIVE_INFINITY,
                                                Float.
                                                  NEGATIVE_INFINITY,
                                                Float.
                                                  NEGATIVE_INFINITY);
    }
    public BoundingBox(BoundingBox b) { super();
                                        minimum = new Point3(b.minimum);
                                        maximum = new Point3(b.maximum);
    }
    public BoundingBox(Point3 p) { this(p.x, p.y, p.z); }
    public BoundingBox(float x, float y, float z) { super();
                                                    minimum = new Point3(
                                                                x,
                                                                y,
                                                                z);
                                                    maximum = new Point3(
                                                                x,
                                                                y,
                                                                z);
    }
    public BoundingBox(float size) { super();
                                     minimum = new Point3(-size, -size,
                                                          -size);
                                     maximum = new Point3(size, size,
                                                          size); }
    final public Point3 getMinimum() { return minimum; }
    final public Point3 getMaximum() { return maximum; }
    final public Point3 getCenter() { return Point3.mid(minimum, maximum,
                                                        new Point3(
                                                          )); }
    final public Point3 getCorner(int i) { float x = (i & 1) == 0
                                             ? minimum.
                                                 x
                                             : maximum.
                                                 x;
                                           float y = (i & 2) == 0
                                             ? minimum.
                                                 y
                                             : maximum.
                                                 y;
                                           float z = (i & 4) == 0
                                             ? minimum.
                                                 z
                                             : maximum.
                                                 z;
                                           return new Point3(x, y,
                                                             z); }
    final public float getBound(int i) { switch (i) { case 0: return minimum.
                                                                       x;
                                                      case 1:
                                                          return maximum.
                                                                   x;
                                                      case 2:
                                                          return minimum.
                                                                   y;
                                                      case 3:
                                                          return maximum.
                                                                   y;
                                                      case 4:
                                                          return minimum.
                                                                   z;
                                                      case 5:
                                                          return maximum.
                                                                   z;
                                                      default:
                                                          return 0;
                                         } }
    final public Vector3 getExtents() { return Point3.sub(maximum,
                                                          minimum,
                                                          new Vector3(
                                                            )); }
    final public float getArea() { Vector3 w = this.getExtents();
                                   float ax = Math.max(w.x, 0);
                                   float ay = Math.max(w.y, 0);
                                   float az = Math.max(w.z, 0);
                                   return 2 * (ax * ay + ay * az +
                                                 az *
                                                 ax); }
    final public float getVolume() { Vector3 w = this.getExtents();
                                     float ax = Math.max(w.x, 0);
                                     float ay = Math.max(w.y, 0);
                                     float az = Math.max(w.z, 0);
                                     return ax * ay * az; }
    final public void enlargeUlps() { final float eps = 1.0E-4F;
                                      minimum.x -= Math.max(eps, Math.
                                                              ulp(
                                                                minimum.
                                                                  x));
                                      minimum.y -= Math.max(eps, Math.
                                                              ulp(
                                                                minimum.
                                                                  y));
                                      minimum.z -= Math.max(eps, Math.
                                                              ulp(
                                                                minimum.
                                                                  z));
                                      maximum.x += Math.max(eps, Math.
                                                              ulp(
                                                                maximum.
                                                                  x));
                                      maximum.y += Math.max(eps, Math.
                                                              ulp(
                                                                maximum.
                                                                  y));
                                      maximum.z += Math.max(eps, Math.
                                                              ulp(
                                                                maximum.
                                                                  z));
    }
    final public boolean isEmpty() { return maximum.x < minimum.x ||
                                       maximum.
                                         y <
                                       minimum.
                                         y ||
                                       maximum.
                                         z <
                                       minimum.
                                         z; }
    final public boolean intersects(BoundingBox b) { return b != null &&
                                                       minimum.
                                                         x <=
                                                       b.
                                                         maximum.
                                                         x &&
                                                       maximum.
                                                         x >=
                                                       b.
                                                         minimum.
                                                         x &&
                                                       minimum.
                                                         y <=
                                                       b.
                                                         maximum.
                                                         y &&
                                                       maximum.
                                                         y >=
                                                       b.
                                                         minimum.
                                                         y &&
                                                       minimum.
                                                         z <=
                                                       b.
                                                         maximum.
                                                         z &&
                                                       maximum.
                                                         z >=
                                                       b.
                                                         minimum.
                                                         z; }
    final public boolean contains(Point3 p) { return p != null &&
                                                p.
                                                  x >=
                                                minimum.
                                                  x &&
                                                p.
                                                  x <=
                                                maximum.
                                                  x &&
                                                p.
                                                  y >=
                                                minimum.
                                                  y &&
                                                p.
                                                  y <=
                                                maximum.
                                                  y &&
                                                p.
                                                  z >=
                                                minimum.
                                                  z &&
                                                p.
                                                  z <=
                                                maximum.
                                                  z; }
    final public boolean contains(float x, float y, float z) { return x >=
                                                                 minimum.
                                                                   x &&
                                                                 x <=
                                                                 maximum.
                                                                   x &&
                                                                 y >=
                                                                 minimum.
                                                                   y &&
                                                                 y <=
                                                                 maximum.
                                                                   y &&
                                                                 z >=
                                                                 minimum.
                                                                   z &&
                                                                 z <=
                                                                 maximum.
                                                                   z;
    }
    final public void include(Point3 p) { if (p != null) { if (p.
                                                                 x <
                                                                 minimum.
                                                                   x)
                                                               minimum.
                                                                 x =
                                                                 p.
                                                                   x;
                                                           if (p.
                                                                 x >
                                                                 maximum.
                                                                   x)
                                                               maximum.
                                                                 x =
                                                                 p.
                                                                   x;
                                                           if (p.
                                                                 y <
                                                                 minimum.
                                                                   y)
                                                               minimum.
                                                                 y =
                                                                 p.
                                                                   y;
                                                           if (p.
                                                                 y >
                                                                 maximum.
                                                                   y)
                                                               maximum.
                                                                 y =
                                                                 p.
                                                                   y;
                                                           if (p.
                                                                 z <
                                                                 minimum.
                                                                   z)
                                                               minimum.
                                                                 z =
                                                                 p.
                                                                   z;
                                                           if (p.
                                                                 z >
                                                                 maximum.
                                                                   z)
                                                               maximum.
                                                                 z =
                                                                 p.
                                                                   z;
                                          } }
    final public void include(float x, float y, float z) { if (x <
                                                                 minimum.
                                                                   x)
                                                               minimum.
                                                                 x =
                                                                 x;
                                                           if (x >
                                                                 maximum.
                                                                   x)
                                                               maximum.
                                                                 x =
                                                                 x;
                                                           if (y <
                                                                 minimum.
                                                                   y)
                                                               minimum.
                                                                 y =
                                                                 y;
                                                           if (y >
                                                                 maximum.
                                                                   y)
                                                               maximum.
                                                                 y =
                                                                 y;
                                                           if (z <
                                                                 minimum.
                                                                   z)
                                                               minimum.
                                                                 z =
                                                                 z;
                                                           if (z >
                                                                 maximum.
                                                                   z)
                                                               maximum.
                                                                 z =
                                                                 z;
    }
    final public void include(BoundingBox b) { if (b != null) { if (b.
                                                                      minimum.
                                                                      x <
                                                                      minimum.
                                                                        x)
                                                                    minimum.
                                                                      x =
                                                                      b.
                                                                        minimum.
                                                                        x;
                                                                if (b.
                                                                      maximum.
                                                                      x >
                                                                      maximum.
                                                                        x)
                                                                    maximum.
                                                                      x =
                                                                      b.
                                                                        maximum.
                                                                        x;
                                                                if (b.
                                                                      minimum.
                                                                      y <
                                                                      minimum.
                                                                        y)
                                                                    minimum.
                                                                      y =
                                                                      b.
                                                                        minimum.
                                                                        y;
                                                                if (b.
                                                                      maximum.
                                                                      y >
                                                                      maximum.
                                                                        y)
                                                                    maximum.
                                                                      y =
                                                                      b.
                                                                        maximum.
                                                                        y;
                                                                if (b.
                                                                      minimum.
                                                                      z <
                                                                      minimum.
                                                                        z)
                                                                    minimum.
                                                                      z =
                                                                      b.
                                                                        minimum.
                                                                        z;
                                                                if (b.
                                                                      maximum.
                                                                      z >
                                                                      maximum.
                                                                        z)
                                                                    maximum.
                                                                      z =
                                                                      b.
                                                                        maximum.
                                                                        z;
                                               } }
    final public String toString() { return String.format("(%.2f, %.2f, %.2f) to (%.2f, %.2f, %.2f)",
                                                          minimum.
                                                            x,
                                                          minimum.
                                                            y,
                                                          minimum.
                                                            z,
                                                          maximum.
                                                            x,
                                                          maximum.
                                                            y,
                                                          maximum.
                                                            z);
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159048306000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAL1aDWwUxxWeu7Pxb+Uf/o2DwZh/uGtoCQETJcbhx/EBjo1N" +
                                                   "YkOd9d7ceWFvd9md\nM2eHEFClQIkaSptWVCoERUSQhDSkaUVTpYQIkqbQqo" +
                                                   "BUIlEFEtEmrVKqRlUTV02lvje7d7u3Zy+G\nc2tp3+3uzLyZ773vvflZn7hJ" +
                                                   "8g2dVItGkPVr1Ag2trUIukEjjbJgGBvgVbf4Tn5Ry7FmRfUTX5j4\npQgjZW" +
                                                   "HRCEUEJoSkSKjpwfqkTuZrqtwfk1UWpEkW3CIvtvQ9FF6cpXDj4VOVu4/m1f" +
                                                   "hJfpiUCYqi\nMoFJqrJSpnGDkfLwFqFPCCWYJIfCksHqw+QrVEnEG1XFYILC" +
                                                   "jG1kJwmEyRhNRJ2MTA+nOg9B5yFN\n0IV4iHcfauHdgoaxOmWCpNBIQ7o7aL" +
                                                   "kgsyUM22rXml0blBRiYQfA4SMA1NPSqE20WVC1wPGOe3Yc\neTFAyjpJmaS0" +
                                                   "oTIRkDDor5OUxmm8h+pGQyRCI52kQqE00kZ1SZClAd5rJ6k0pJgisIROjVZq" +
                                                   "qHIf\nVqw0EhrVeZ+pl2FSKiImPSEyVU/bKCpROZJ6yo/KQgxgT7Bhm3BX4X" +
                                                   "sAWCzBwPSoINJUk7ytkgIe\nr3G3SGOsa4YK0LQgTlmvmu4qTxHgBak0fSkL" +
                                                   "SizUxnRJiUHVfDUBvTBSNaxStLUmiFuFGO1mZJK7\nXotZBLWKuCGwCSPj3d" +
                                                   "W4JvBSlctLDv/Mn/D53uM/Ov0A53ZehIoyjr8YGk11NWqlUapTRaRmw8FE\n" +
                                                   "8NmmRxPVfkKg8nhXZbNOw8xT7eE/v1Vj1pkyRJ31PVuoyLrFdQdqWh9frZIA" +
                                                   "DqNQUw0JnZ+BnIdD\ni1VSn9QgaiekNWJhMFV4pvXdR3e9RD/1k+ImMkZU5U" +
                                                   "QceFQhqnFNkqm+mipUFxiNNJEiqkQaeXkT\nKYD7MFDefLs+GjUoayJ5Mn81" +
                                                   "RuXPYKIoqEATFcG9pETV1L0msF5+n9QIIQVwkdLUTeqXkVAwZCSU\nqKxuDx" +
                                                   "m6GFL1WPo5DgpCK9SEEgGSrFCTQSSOxkhzqFeN05AgCoqkqKGYBKEqqgsjtA" +
                                                   "9/b09dEkdY\nud3nw5TnDl0ZWL9GlSNU7xaP3Ti/Y2Xzt/aatEAqW9gYqYZe" +
                                                   "glYvQewl6OiF+Hxc+TjszfQK2HQr\nRCfksdK5bZsfemxvbQDooG3PA4Ng6q" +
                                                   "0FFNYQVopqox3CTTzbicCjSc937QkOHrvf5FFo+Ew7ZOuS\ni69cOPKP0nl+" +
                                                   "4h86DSI0SMTFqKYFc2c6vdW5A2co/X/bt/b1Kxc+mGOHECN1WZGd3RIjs9bt" +
                                                   "BF0V\naQRyna3+6OSywEbSccBP8iDcIcXx8UP2mOruIyNC61PZDrEUhElJVN" +
                                                   "XjgoxFqRRVzHp1dbv9hrOj\nnN+PBeeUIGUnwFVucZj/Yul4DeUEk03obRcK" +
                                                   "nk0Hvznmq++/WfION0sq8ZY5prY2yswwrrDJskGn\nFN5/cLDle9+/uaeLM8" +
                                                   "Wkio/BfJfokSUxCU1m2U0gfmXIIejIunYlrkakqCT0yBQZ92XZzLt/9tdn\n" +
                                                   "yk3XyPAm5dkFt1Zgv5+8guy68I0vpnI1PhHnDxuGXc1EM9bW3KDrQj+OI7n7" +
                                                   "8l0//JVwCNIbpBRD\nGqA8SxCOjHA7hrjd53EZdJXdjaIWdC8YhvpDzNbd4o" +
                                                   "6XYrWJbb9+g4+6RHBO+043rBW0etPzKGag\ndSe6o3eNYPRCva+fWbepXD7z" +
                                                   "b9DYCRpFmCWN9TokjGSGE63a+QVX3z474bFLAeJfRYplVYisEjj/\nSREQjx" +
                                                   "q9kGuS2v0PmNzaXphiWJJwI1RZBuAP1dmsrLJYWTUkK1HMcpnUZxLJeAIlme" +
                                                   "xY8q1TFT4r\nSCJGQzJlAZy1gzqNon8xNJP9H82+Ou035Y0XTCb0MjLTMb9b" +
                                                   "NUNNSp8qclOvEZQIzDgmMaqH7HCj\nLmgwg1/88OPN++d/8i6mOI1DWO5BiA" +
                                                   "dRLOVFi1AsM620+I6NWWcZs+62jYnPExmZmDUrtKiwlvoa\nV9DsAWU9ijU2" +
                                                   "lKZcoYQsKKERQwlwjQFw0STnNkCX4rCc6OPZ9MZTtb98r/25PeYMNNdjre9s" +
                                                   "1S0+\nef3DrYH9b/eY7dxLKlflA1OPfvz6jdZxZrYy150zspZ+zjbm2pMjK9" +
                                                   "Mwcqd79cBrn5s//cTO1mvW\niCozV1ArYZfxSf9ZOnv5tz8aYuqHLK4KPIV2" +
                                                   "ccE77vBwroCizXbuhlydu9Ry7tLb5ak93l6P8W5B\nQe3xRkc63qTjyW94Mm" +
                                                   "QVbkjs6b27Z8Hx8Pn1h7i5h12dDEEel56B0+2HB3/LrnE99jIBW09PZq/2\n" +
                                                   "YBNnt733Sl/FmJPPxf2koJOUi9Y2s0OQEzgZd8KuyEjtPWErmlGeucMxl/P1" +
                                                   "6WVQtZu9jm7dCxSb\nanCPtfG+1LUmwSU1mQJXoUWEQjcRfITfmJvkOi5np1" +
                                                   "cQBZou9Qm49YTtmqRI8UT8VgnMXOOkGZG4\nFSPU7LEWWWMtGmasO1H04ZCE" +
                                                   "5B0M6UmPISXtkEiHlk7uGm4/yDPEnkc+K31KOLfZbwXFZgYTtqot\nlGkflR" +
                                                   "2qKlBTxuJ9Ld8B27za9+LLp9il+cvMXDNv+JhwN5y37MhAzbJXn76DJXuNC5" +
                                                   "tbdUXflIcD\nvdJ7fr5JN2matbnPbFSfSc5iGE9CVzZkUHRa2u0VqXl0leX2" +
                                                   "VUPmqmx++jHBSorAd+Bzkx5p6lmP\nsh+geIaR4hhla0dGcZtK+28rP+/Lxt" +
                                                   "xqYW4dKWa8/+4tAT/vUXYUxSEL8MgCyAZ8OBfAM+DqsgB3\njS7gkx5lP0Hx" +
                                                   "MgQlAG6keF41crwncsE7B66YhTeWO17nCnKjZpZuYiQAI+baTnvY4CyKNywb" +
                                                   "qLpy\nOzb4RS42mEXwGMf8Y6NmA3x8izf/nQfoSyjOM1IIoPmZCz532cAu5A" +
                                                   "JsJlz7LWD7R5fMf/Ao+wDF\nFTN6VyYZ0NlIeXJSlic7KO4fHa58PxfE0+A6" +
                                                   "aCE+OLqI/+JR9imKP8JsD4gbdCq4nPinXCDVwvWC\nBemF0YX0T4+yL1D83Y" +
                                                   "zGDjxOpS5Qn+WaZl+zQL02qqB8Po+yAL78kpESqsiCHqPtsma4c1VenypF\n" +
                                                   "bJj/ydV35yyY50YXZrlHGW6afCVAR8lYGddYvxtiQY+qylRQ0ih9pbmgXATX" +
                                                   "ZQvl5dxROueQ+7hs\n4KhqPBDPQFHFrG89BuQUrOYbZyOckgvC+XBdtxBeH1" +
                                                   "2Etzhn8S30QI1niL45MHlYW0M35rm5YF4O\n100L883cMVsHMjyJ2ILDqPeA" +
                                                   "eB+Ke4aHuCQXiLPhGrQgDv5/3braA3MzihUYvoooJyKYeH35NuTG\nXCDfCw" +
                                                   "OsMBGbv/8zr7Z7INyIomVYhA/ngnABIJtlIZw1uk51ZiPBAx7Hs2lYeJtznF" +
                                                   "J8Syx4S3KH\n5xy26lG2DcUWiEOmmp+8Uywvt0/VHQUc59YRn7TBjOz44IgH" +
                                                   "65Oy/gnB/HAuhq8+vunz8O//xT+d\npT9ul4RJYTQhy87zJsf9GE2nUYmbp8" +
                                                   "Q8feLn8b4BGL87SmEFgD880fSb1Z6A8TmqgWOtO2elXbDN\ngUp4u1sbwjrm" +
                                                   "OVoyAzginZFxhML/zyN1zJEw/9OjW3zkla5pyac3fIefneSLsjAwgGqKw6TA" +
                                                   "/ATI\nteJRyfRhtaV0XSQnX+1488dLU0dB/BPROAd1Mti3yCz1cKROaob+7s" +
                                                   "aXHvilbODnE3+6/Njha/w7\niPZf2LzLAp4jAAA=");
}
