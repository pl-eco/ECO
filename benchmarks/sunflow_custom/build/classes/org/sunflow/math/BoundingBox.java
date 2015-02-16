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
      1159048306000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVZfWwcxRWfO387Jv4ISYwxtuM4bp2EW9KUj2Ca1rHsxOaS" +
                                                    "WLbjCrvFrPfmzhvv7S67c/bZqSmkQomQmlTUgEHBlaqkNDQkoWpEv1DzRwpE" +
                                                    "VFWJKqqqAlftH42g/iNVC1Gh0Pdm9+729s7LGV1O2nez8/Hm9+Z9zJvZM0uk" +
                                                    "yDTIFl1TpiOKxgI0zgIHlTsDbFqnZqA3eGefaJg01KmIpjkIdaNS8/nKDz76" +
                                                    "3niVnxQPkzWiqmpMZLKmmv3U1JRJGgqSylRtl0KjJiNVwYPipCjEmKwIQdlk" +
                                                    "7UGyyjGUkZZgAoIAEASAIHAIQkeqFwy6iaqxaCeOEFVmPkweIb4gKdYlhMfI" +
                                                    "hnQmumiIUZtNH5cAOJTi+xAIxQfHDdKUlN2SOUPgp7YIc888WPXTAlI5TCpl" +
                                                    "dQDhSACCwSTDpCJKo2PUMDtCIRoaJtUqpaEBasiiIs9w3MOkxpQjqshiBk0u" +
                                                    "ElbGdGrwOVMrVyGhbEZMYpqRFC8sUyWUeCsKK2IEZF2XktWSsBvrQcByGYAZ" +
                                                    "YVGiiSGFE7IaYqTRPSIpY8v90AGGlkQpG9eSUxWqIlSQGkt3iqhGhAFmyGoE" +
                                                    "uhZpMZiFkbplmeJa66I0IUboKCO17n59VhP0KuMLgUMYWevuxjmBlupcWnLo" +
                                                    "Z2nffccOqXtUP8ccopKC+EthUINrUD8NU4OqErUGVmwOPi2ue/WonxDovNbV" +
                                                    "2erzyreufW1rw8U3rD63Zumzf+wgldiodHJs9Vv1nW07ChBGqa6ZMio/TXJu" +
                                                    "/n12S3tcB89bl+SIjYFE48X+1x549EX6vp+U95BiSVNiUbCjakmL6rJCjd1U" +
                                                    "pYbIaKiHlFE11Mnbe0gJlIOySq3a/eGwSVkPKVR4VbHG32GJwsACl6gEyrIa" +
                                                    "1hJlXWTjvBzXCSEl8JCKRCHxz8iIcMAEcxdESVRlVRPAeKloSOMClbTRMVjd" +
                                                    "8ahoTJiCFDOZFhXMmBpWtCnBNCRBMyLJ9yhMJuzSYmoIDGqXFg+gkek3ln0c" +
                                                    "paua8vlg4evdbq+Ax+zRlBA1RqW52K6ua2dH3/Qn3cBeF0bqYZaAPUsAZwk4" +
                                                    "ZiE+H2d+M85maRT0MQGeDTGvom3gm70PHW0uAFPSpwphMTH0NoNcNoQuSetM" +
                                                    "uX8PD3IS2GDtD0eOBK6/8FXLBoXlY3XW0eTi/NRjQ9++w0/86UEXRYKqchze" +
                                                    "h6EyGRJb3M6WjW/lkasfnHt6Vku5XVoUt6NB5kj05mb34huaREMQH1PsNzeJ" +
                                                    "F0ZfnW3xk0IIERAWmQhmDBGnwT1Hmle3JyIkylIEAoc1Iyoq2JQIa+Vs3NCm" +
                                                    "UjXcKlbzcjUoZRWa+Tp4qmy75//YukZHerNlRahllxQ8Anf/4uKzF57bssPv" +
                                                    "DNaVju1vgDLL9atTRjJoUAr178z3ff+ppSMj3EKgx8ZsE7Qg7YRAACqDZX38" +
                                                    "jYf/vPjuyT/6k1blY7AjxsYUWYoDj9bULBAmFAhVqPuWA2pUC8lhWRxTKBrn" +
                                                    "x5Wbtl3457EqS5sK1CSMYetnM0jV37KLPPrmgx82cDY+CbeplOSpbtYCrElx" +
                                                    "7jAMcRpxxB+7ctuzr4vPQxSFyGXKM5QHI8IlI3zpBa6qzZwGXG3bkDTpGW28" +
                                                    "oi5Tx3W2juuy6hhJi2s2n7XGvJ33ussDzj1ItucOp8WG07JSOPx9LSPrM6JT" +
                                                    "nwb5wHbO4CseSDuQ3Js7UsFGKuSMtIBzLAC9t3lkm4YchQ1w0t6hhdmaxYkT" +
                                                    "V1+yIp97O3d1pkfnnvg0cGzO78h5NmakHc4xVt7DId9kifgp/HzwfIIPioYV" +
                                                    "1r5X02lvvk3J3VfX0cM2eMHiU3T/49zsr348e8QSoyZ9y++CjPalt//3u8D8" +
                                                    "Xy9n2W8ghGgid8ZeTjjabg9N9iPpzF2TO2xN7vg8LmDB+boHnAeQDGbCsfDU" +
                                                    "8je/t1F0Y/7r2A7/u18ZO/y363yxMja0LHbiGj8snDlR17nzfT4+tbPg6MZ4" +
                                                    "ZmIAZ4XU2C+9GP2Pv7n4t35SMkyqJPsgMiQqMYzfw5B8m4nTCRxW0trTE2kr" +
                                                    "a2xP7pz1bkN1TOve01IGAmXsjeVy1zaGmRu5FZ5SW7+lbv36CC+IfEgzp5uQ" +
                                                    "fDGxi5Tohjwp4ikHTgayKkdjUWzfyQ3D0ug3Mucrs+crW2a+CBIJWYpxF8t4" +
                                                    "ytpqE1ZrkNuWS/25b508PLcQ2n9qm9+2t32MlDFNv12hk1RxsKpGTmm51l5+" +
                                                    "2Enp9onTP3mFvbXlXstLNy9vj+6Brx9+r25w5/hDK8iwGl0yuVme3nvm8u5W" +
                                                    "6Uk/KUiaSMb5LX1Qe7phlBsUDpzqYJp5NCTVVYur2wxPt62u7qzun902/Fhs" +
                                                    "w9gkq6IS9/D+aY+2Q0ggOSqPULbXYV7LhC4tE3u/jb1/xdiRTHkB/45H2+NI" +
                                                    "HrGBO4w4N+Ab4BmxgY/kH/h3PdqOIzkKDgLAOyleE6wQd8TGHckPbmfyshvJ" +
                                                    "/VaPvYwUQNbCuT7jIc8JJE/a8miGmqs867GykeCubv1YXuXB13nO4qQH+B8h" +
                                                    "+QEjpQCeHyHxvfczsd+ClRvhOW5jP55/Gzrr0XYeyWnL+LviDKzITCSftRnJ" +
                                                    "5xDFk99yCbBLIQ3wzNtCzedfqF96tP0ayQXYk0CoDoOKuamCo26C55SN+lT+" +
                                                    "UV/yaHsNyW8s8x/CKyCaG+7qhAm9bON+Of+4f+/R9gcklxlZRVVFNCL0gKKb" +
                                                    "2WJA4aQmh3JzCNTCJVuaS/mX5i8ebe8geRtsRza7ojqbziZJyZimKVRUc/fu" +
                                                    "K7YwV/IjTMap9aqHRO8h+Tuzb5JN8GHebTE39LhPLNroF/OOfidn8S8P9P9G" +
                                                    "sgRx1c7sV4p9yca+lB/s9rGXe2aKcGYfe0jxCZIPVywFd+0vwHPdluL6jdGA" +
                                                    "r3h57L5SJHh+kFVJiYV4VLqSG/R7YJJqC7n1fyMV4KvyEKIGyarPJcRWAN9q" +
                                                    "C9F6Y/zXV+cBvR7J2pVC50leE0xytw397vxAdyJz3yQ421qRNILFM836ypRI" +
                                                    "K6r4NSGemgNWQ5bLBNhMHNfweLlYm/FZz/oUJZ1dqCxdv3DgT/xiOfm5qCxI" +
                                                    "SsMxRXEerR3lYt2gYZmvQJl10OaHVd/tAM+d9cDGhX8I0rfV6nYH4HN0A9XY" +
                                                    "JWen7ZD1QicsflnPIrx1ZRAnvClxPtbT3tLujPHcyj+BJs6YMesj6Kh0bqF3" +
                                                    "36Frd53iB9YiSRFnZpBLaZCUWNflnCmeUzcsyy3Bq3hP20erz5dtSpy/VyOp" +
                                                    "cdiKA1tj9qtkvm3i5e/Mz9f/7L4XFt7ld9n/B3BGhr+bHgAA");
}
