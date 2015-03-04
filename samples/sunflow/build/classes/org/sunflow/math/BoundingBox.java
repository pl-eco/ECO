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
    public final Point3 getMinimum() { return minimum; }
    public final Point3 getMaximum() { return maximum; }
    public final Point3 getCenter() { return Point3.mid(minimum, maximum,
                                                        new Point3(
                                                          )); }
    public final Point3 getCorner(int i) { float x = (i & 1) == 0
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
    public final float getBound(int i) { switch (i) { case 0: return minimum.
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
    public final Vector3 getExtents() { return Point3.sub(maximum,
                                                          minimum,
                                                          new Vector3(
                                                            )); }
    public final float getArea() { Vector3 w = getExtents();
                                   float ax = Math.max(w.x, 0);
                                   float ay = Math.max(w.y, 0);
                                   float az = Math.max(w.z, 0);
                                   return 2 * (ax * ay + ay * az +
                                                 az *
                                                 ax); }
    public final float getVolume() { Vector3 w = getExtents();
                                     float ax = Math.max(w.x, 0);
                                     float ay = Math.max(w.y, 0);
                                     float az = Math.max(w.z, 0);
                                     return ax * ay * az; }
    public final void enlargeUlps() { final float eps = 1.0E-4F;
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
    public final boolean isEmpty() { return maximum.x < minimum.x ||
                                       maximum.
                                         y <
                                       minimum.
                                         y ||
                                       maximum.
                                         z <
                                       minimum.
                                         z; }
    public final boolean intersects(BoundingBox b) { return b != null &&
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
    public final boolean contains(Point3 p) { return p != null &&
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
    public final boolean contains(float x, float y, float z) { return x >=
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
    public final void include(Point3 p) { if (p != null) { if (p.
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
    public final void include(float x, float y, float z) { if (x <
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
    public final void include(BoundingBox b) { if (b != null) { if (b.
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
    public final String toString() { return String.format("(%.2f, %.2f, %.2f) to (%.2f, %.2f, %.2f)",
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
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1ZfWwUxxWfO3+dDcEfBHAcYhtjaA3kNpTmgzilNScb7Bxg" +
                                                    "GeMqpo2z3ps7L97b3ezO2QfUaUIUgSIVqtRJnIq6UgVNSQmQqij9isofNAlK" +
                                                    "VTWoSlVVCVX7R1FS/0HVJqhJk743u3u3t3fenKOjJ+272fl483vz3rx5b/b0" +
                                                    "HKkwDbJe15T9CUVjYZpm4X3KnWG2X6dmuC96Z79omDQWUUTTHIS6EantXO37" +
                                                    "H357rC5IKofJUlFVNSYyWVPNAWpqygSNRUlttrZboUmTkbroPnFCFFJMVoSo" +
                                                    "bLLOKFnkGspIe9SBIAAEASAIHILQle0Fg26iaioZwRGiysyHySMkECWVuoTw" +
                                                    "GFmVy0QXDTFps+nnEgCHEL4PgVB8cNogrRnZLZnzBH56vTD97IN1PykjtcOk" +
                                                    "VlZ3IxwJQDCYZJgsTtLkKDXMrliMxoZJvUppbDc1ZFGRD3Dcw6TBlBOqyFIG" +
                                                    "zSwSVqZ0avA5syu3WELZjJTENCMjXlymSsx5q4grYgJkXZ6V1ZKwB+tBwBoZ" +
                                                    "gBlxUaLOkPJxWY0x0uIdkZGx/X7oAEOrkpSNaZmpylURKkiDpTtFVBPCbmbI" +
                                                    "agK6VmgpmIWRpnmZ4lrrojQuJugII43efv1WE/Sq5guBQxhZ5u3GOYGWmjxa" +
                                                    "culnbud9Rw+q29UgxxyjkoL4QzCo2TNogMapQVWJWgMXr4s+Iy5/5UiQEOi8" +
                                                    "zNPZ6vPyN659ZUPzhdetPrcW6LNrdB+V2Ih0YnTJmysjHZvLEEZI10wZlZ8j" +
                                                    "OTf/frulM63Dzlue4YiNYafxwsCrDzz6An0vSGp6SaWkKakk2FG9pCV1WaHG" +
                                                    "NqpSQ2Q01kuqqRqL8PZeUgXlqKxSq3ZXPG5S1kvKFV5VqfF3WKI4sMAlqoKy" +
                                                    "rMY1p6yLbIyX0zohpAoestgpOP+MSMKYlqSCKImqrGoC2C4VDWlMoJI2YlBd" +
                                                    "E7oju4RRWOWxpGiMm4KZUuOKNjkipUymJQXTkATNSDjVQhImFbZqKTUGhrVV" +
                                                    "S4fR2PT/zzRplLZuMhAARaz0ugEFdtB2TYlRY0SaTm3tvnZm5I1gZlvY68TI" +
                                                    "SpglbM8SxlnCrllIIMCZ34yzWRoG/YzDTgcfuLhj99f7HjrSVgampU+Ww+Ki" +
                                                    "K24DAW0I3ZIWybqDXu70JLDJxh/sPRy+/vyXLZsU5vfdBUeTCzOTjw19844g" +
                                                    "CeY6YRQJqmpweD+6zoyLbPduvkJ8aw9fff/sM1NadhvmeHXbO+SPxN3d5l18" +
                                                    "Q5NoDPxllv26VvH8yCtT7UFSDi4D3CQTwazBAzV758jZ5Z2Ox0RZKkDguGYk" +
                                                    "RQWbHDdXw8YMbTJbw61iCS/Xg1IWodkvh6fO3gf8H1uX6khvtqwIteyRgnvk" +
                                                    "np9feO78d9dvDrqdd63rONxNmeUK6rNGMmhQCvVvz/R/5+m5w3u5hUCP1YUm" +
                                                    "aEcaAccAKoNlfeL1h/905Z0TfwhmrCrA4IRMjSqylAYea7OzgNtQwHWh7tv3" +
                                                    "qEktJsdlcVShaJwf1a7ZeP4fR+ssbSpQ4xjDhk9nkK2/ZSt59I0HP2jmbAIS" +
                                                    "HltZybPdrAVYmuXcZRjifsSRfuzybc+9Jn4PvCp4MlM+QLlzIlwywpde4Kpa" +
                                                    "x2nY07YRSaue18YrmvJ13GTruKmgjpG0e2YLWGvM23mvu3zg3INkU/Fw2m04" +
                                                    "7QuFw9+XMbIizzv1axAfbOIMvuSDtAvJvcUjFWykQtFIyzjHMtB7h0/0achJ" +
                                                    "OBAn7BNbmGq4Mn786ouW5/Me757O9Mj0k5+Ej04HXTHQ6rwwxD3GioM45Jss" +
                                                    "ET+BXwCej/FB0bDCOgcbIvZh3Jo5jXUdd9gqP1h8ip6/n5365Y+mDltiNOSG" +
                                                    "AN0Q4b741n9/G575y6UC5w24EE3km7GPE462x0eTA0gixWtys63JzZ9lC1hw" +
                                                    "vuoD5wEkg/lwLDyN/C3obxQ9GA+7jsP/7FJGD/31Ol+svAOtgJ14xg8Lp483" +
                                                    "Rba8x8dnTxYc3ZLODwwgd8iO/cILyX8H2yp/EyRVw6ROshOTIVFJof8ehmDc" +
                                                    "dLIVSF5y2nMDayuK7MycnCu9huqa1numZQ0EytgbyzWeYwwjOXIrPCFbvyGv" +
                                                    "fgOEF0Q+pI3TNUg+75wiVbohT4iY9UCmIKtyMpXE9i3cMCyNfi1/vmp7vup5" +
                                                    "5ksgkZClmPawTGetrdGxWoPcNl8qwPfWiUPTs7FdJzcGbXvbyUg10/TbFTpB" +
                                                    "FRereuSUE2vt4MlPVrdPnvrxy+zN9fdau3Td/PboHfjaoXebBreMPbSACKvF" +
                                                    "I5OX5akdpy9tWys9FSRlGRPJy+dyB3XmGkaNQSEBVQdzzKM5o65GXN02eHps" +
                                                    "dfUU3P6FbSOIxQ70TbIqKmmf3b/fp+0gEgiOahKU7XCZ1zyuS8vHPmBjH1gw" +
                                                    "diSTfsAf92l7AskjNnCXERcHfBU8e23ge0sP/Fs+bceQHIENAsAjFK8NFog7" +
                                                    "YeNOlAa3O3jZhuR+q8cORsogauFcn/WR5ziSp2x5NEMtVp4VWNlC8FS3fqyk" +
                                                    "8uDrDGdxwgf8D5F8n5EQgOcpJL73fSr2W7ByNTzHbOzHSm9DZ3zaziE5ZRl/" +
                                                    "d5qBFZlO8NmYF3wOUcz85guAPQpphmfGFmqm9EL9wqftV0jOw5kEQnUZVCxO" +
                                                    "FRx1KzwnbdQnS4/6ok/bq0h+bZn/EF4J0eJw1zsm9JKN+6XS4/6dT9vvkVxi" +
                                                    "ZBFVFdFI0D2KbhbyAeUTmhwrbkOgFi7a0lwsvTR/9ml7G8lbYDuy2Z3U2f5C" +
                                                    "klSNappCRbX43X3ZFuZyaYTJy1qv+kj0LpK/Mftm2YQ9zLtdKQ49nhNXbPRX" +
                                                    "So5+C2fxTx/0/0IyB37VjuwXin3Oxj5XGux22st3ZpZwZh/5SPExkg8WLAXf" +
                                                    "2p+D57otxfUbo4FA5fzYAyEkmD/IqqSkYtwrXS4O+j0wSb2F3Pq/kQoI1PkI" +
                                                    "0YBk0WcSYgOAX2sLsfbG7N9Akw/0lUiWLRQ6D/JaYZK7beh3lwa6G5n3JsHd" +
                                                    "thZJC1g806yvTk5YUcevCTFrDlsNBS4T4DBxXcPj5WJj3mc+69OUdGa2NrRi" +
                                                    "ds8f+cVy5vNRdZSE4ilFcafWrnKlbtC4zFeg2kq0ebIauB3geaMeOLjwD0EG" +
                                                    "Nljd7gB8rm6gGrvk7rQJol7ohMUv6gWEt64M0oQ3OfmxnvOWc2eMeSv/JOrk" +
                                                    "mCnro+iIdHa2b+fBa3ed5AlrhaSIBw4gl1CUVFnX5Zwp5qmr5uXm8Krc3vHh" +
                                                    "knPVa5z8ewmSBpetuLC1FL5K5scmXv4e+NmKn973/Ow7/C77f3DSO7yrHgAA");
}
