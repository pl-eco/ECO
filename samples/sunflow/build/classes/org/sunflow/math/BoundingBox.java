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
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVZfWwcxRWfO387Jv4ISYwxtuM4bp2EW9KUj2Ca1jnZic0l" +
                                                    "sWzHFU6LWe/NnTfZ21125+yzU1NIhRIhNamoAYOCK1VJaWhIQtWIfqHmjxSI" +
                                                    "qKoSVVRVBa7aPxpB/UeqFqJCoe/N7t3t7Z2XM7qctO9m5+PN7837mDezZxZJ" +
                                                    "iWmQTbqmTEUVjQVoggUOKHcG2JROzUBf6M5+0TBpOKiIpjkEdaNS6/nqDz76" +
                                                    "3niNn5SOkFWiqmpMZLKmmgPU1JQJGg6R6nRtt0JjJiM1oQPihCjEmawIIdlk" +
                                                    "nSGywjGUkbZQEoIAEASAIHAIQle6Fwy6iarxWBBHiCozHyaPEF+IlOoSwmNk" +
                                                    "XSYTXTTEmM2mn0sAHMrxfRiE4oMTBmlJyW7JnCXwU5uE2WcerPlpEakeIdWy" +
                                                    "OohwJADBYJIRUhWjsTFqmF3hMA2PkFqV0vAgNWRRkac57hFSZ8pRVWRxg6YW" +
                                                    "CSvjOjX4nOmVq5JQNiMuMc1IiReRqRJOvpVEFDEKsq5Jy2pJ2IP1IGClDMCM" +
                                                    "iCjR5JDig7IaZqTZPSIlY9v90AGGlsUoG9dSUxWrIlSQOkt3iqhGhUFmyGoU" +
                                                    "upZocZiFkYYlmeJa66J0UIzSUUbq3f36rSboVcEXAocwstrdjXMCLTW4tOTQ" +
                                                    "z+Ke+44dUnepfo45TCUF8ZfDoCbXoAEaoQZVJWoNrNoYelpc8+pRPyHQebWr" +
                                                    "s9XnlW9d+9rmpotvWH1uzdFn79gBKrFR6eTYyrcagx3bihBGua6ZMio/Q3Ju" +
                                                    "/v12S2dCB89bk+KIjYFk48WB1x549EX6vp9U9pJSSVPiMbCjWkmL6bJCjZ1U" +
                                                    "pYbIaLiXVFA1HOTtvaQMyiFZpVbt3kjEpKyXFCu8qlTj77BEEWCBS1QGZVmN" +
                                                    "aMmyLrJxXk7ohJAyeEhVspD8Z6RfGNdiVBAlUZVVTQDbpaIhjQtU0gRTjOkK" +
                                                    "aM2MqxFFmxRMQxI0I5p6j8EMwg4trobBinZoiQBaln4DeCZQjppJnw+WuNHt" +
                                                    "4Ar4xi5NCVNjVJqN7+i+dnb0TX/K4O0VYKQRZgnYswRwloBjFuLzceY342yW" +
                                                    "7mDlD4IPQ3Sr6hj8Zt9DR1uLwGj0yWJYNgyyrSCNDaFb0oJpR+/l4UwCa6v/" +
                                                    "4f4jgesvfNWyNmHpqJxzNLk4N/nY8Lfv8BN/ZnhFkaCqEof3Y1BMBb82t1vl" +
                                                    "4lt95OoH556e0dIOlhGvbb/PHol+2+pefEOTaBgiYZr9xhbxwuirM21+UgzB" +
                                                    "AAIgE8FgIbY0uefI8N/OZCxEWUpA4IhmxEQFm5IBrJKNG9pkuoZbxUpergWl" +
                                                    "rECDXgNPjW3h/B9bV+lIb7asCLXskoLH2p5fXHz2wnObtvmdYbnasdENUmY5" +
                                                    "eW3aSIYMSqH+nbn+7z+1eGQ/txDosT7XBG1Ig+DyoDJY1sffePjPC++e/KM/" +
                                                    "ZVU+BntffEyRpQTwaE/PAgFBgaCEum/bp8a0sByRxTGFonF+XL1hy4V/Hqux" +
                                                    "tKlATdIYNn82g3T9LTvIo28++GETZ+OTcENKS57uZi3AqjTnLsMQpxBH4rEr" +
                                                    "tz37uvg8xEuIUaY8TXnYIVwywpde4KrayGnA1bYFSYue1cYrGrJ13GDruCGn" +
                                                    "jpG0uWbzWWvM23mvuzzg3INka/5w2mw4bcuFw99XM7I2Kzr1a7Dzb+UMvuKB" +
                                                    "tAvJvfkjFWykQt5IizjHItB7h0deacgx2Oom7L1YmKlbOHji6ktW5HNv3K7O" +
                                                    "9OjsE58Gjs36HdnN+qwEwznGynA45JssET+Fnw+eT/BB0bDC2uHqgvY225La" +
                                                    "Z3UdPWydFyw+Rc8/zs386sczRywx6jI3927IXV96+3+/C8z99XKO/QZCiCZy" +
                                                    "Z+zjhKPt8dDkAJJg/prcZmty2+dxAQvO1z3gPIBkKBuOhaeev/m9jaIHM13H" +
                                                    "dvjfvcrY4b9d54uVtaHlsBPX+BHhzImG4Pb3+fj0zoKjmxPZiQGcCtJjv/Ri" +
                                                    "7D/+1tLf+knZCKmR7CPHsKjEMX6PQJptJs8hcCzJaM9Mma38sDO1cza6DdUx" +
                                                    "rXtPSxsIlLE3litd2xjmaORWeMpt/Za79esjvCDyIa2cbkDyxeQuUqYb8oSI" +
                                                    "5xk4A8iqHIvHsH07NwxLo9/Inq/Cnq9iifmiSCRkKSZcLBNpa6tPWq1Bblsq" +
                                                    "yee+dfLw7Hx476ktftve9jBSwTT9doVOUMXBqhY5ZeRau/mxJq3bJ07/5BX2" +
                                                    "1qZ7LS/duLQ9uge+fvi9hqHt4w8tI8NqdsnkZnl695nLO9ulJ/2kKGUiWSe1" +
                                                    "zEGdmYZRaVA4WqpDGebRlFJXPa5uKzw9trp6crp/btvwY7EDY5OsikrCw/un" +
                                                    "PNoOIYHkqDJK2W6HeS0RurRs7AM29oFlY0cy6QX8Ox5tjyN5xAbuMOL8gK+D" +
                                                    "Z78NfH/hgX/Xo+04kqPgIAA8SPFCYJm4ozbuaGFwO5OXnUjut3rsZqQIshbO" +
                                                    "9RkPeU4gedKWRzPUfOVZi5XNBHd168cKKg++znEWJz3A/wjJDxgpB/D8CInv" +
                                                    "fZ+J/RasXA/PcRv78cLb0FmPtvNITlvG351gYEVmMvmsz0o+hyme/JZKgF0K" +
                                                    "aYJnzhZqrvBC/dKj7ddILsCeBEJ1GVTMTxUcdQs8p2zUpwqP+pJH22tIfmOZ" +
                                                    "/zBe9tD8cNcmTehlG/fLhcf9e4+2PyC5zMgKqiqiEaX7FN3MFQOKJzQ5nJ9D" +
                                                    "oBYu2dJcKrw0f/FoewfJ22A7stkd09lULknKxjRNoaKav3dfsYW5Uhhhsk6t" +
                                                    "Vz0keg/J35l9Z2yCD/NuC/mhx31iwUa/UHD02zmLf3mg/zeSRYirdma/XOyL" +
                                                    "NvbFwmC3j73cM9OEM/vYQ4pPkHy4bCm4a38Bnuu2FNdvjAZ8pUtj95UjwfOD" +
                                                    "rEpKPMyj0pX8oN8Dk9RayK3/G6kAX42HEHVIVnwuITYD+HZbiPYb47++Bg/o" +
                                                    "jUhWLxc6T/JaYJK7beh3Fwa6E5n7JsHZ1o6kGSyeadb3pGRaUcOvCfHUHLAa" +
                                                    "clwmwGbiuIbHy8X6rA941kcn6ex8dfna+X1/4hfLqQ9DFSFSHokrivNo7SiX" +
                                                    "6gaNyHwFKqyDNj+s+m4HeO6sBzYu/EOQvs1WtzsAn6MbqMYuOTtthawXOmHx" +
                                                    "y3oO4a0rgwThTcnzsZ7xlnFnjOdW/rEzecaMW587R6Vz8317Dl276xQ/sJZI" +
                                                    "ijg9jVzKQ6TMui7nTPGcum5Jbklepbs6Plp5vmJD8vy9Ekmdw1Yc2JpzXyXz" +
                                                    "bRMvf6d/vvZn970w/y6/y/4/GmoNOIUeAAA=");
}
