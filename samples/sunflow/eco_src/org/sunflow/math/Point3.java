package org.sunflow.math;
final public class Point3 {
    public float x;
    public float y;
    public float z;
    public Point3() { super(); }
    public Point3(float x, float y, float z) { super();
                                               this.x = x;
                                               this.y = y;
                                               this.z = z; }
    public Point3(Point3 p) { super();
                              x = p.x;
                              y = p.y;
                              z = p.z; }
    public float get(int i) { switch (i) { case 0: return x; case 1: return y;
                                           default:
                                               return z; } }
    final public float distanceTo(Point3 p) { float dx = x - p.x;
                                              float dy = y - p.y;
                                              float dz = z - p.z;
                                              return (float) Math.sqrt(dx *
                                                                         dx +
                                                                         dy *
                                                                         dy +
                                                                         dz *
                                                                         dz);
    }
    final public float distanceTo(float px, float py, float pz) { float dx =
                                                                    x -
                                                                    px;
                                                                  float dy =
                                                                    y -
                                                                    py;
                                                                  float dz =
                                                                    z -
                                                                    pz;
                                                                  return (float)
                                                                           Math.
                                                                           sqrt(
                                                                             dx *
                                                                               dx +
                                                                               dy *
                                                                               dy +
                                                                               dz *
                                                                               dz);
    }
    final public float distanceToSquared(Point3 p) {
        float dx =
          x -
          p.
            x;
        float dy =
          y -
          p.
            y;
        float dz =
          z -
          p.
            z;
        return dx *
          dx +
          dy *
          dy +
          dz *
          dz;
    }
    final public float distanceToSquared(float px,
                                         float py,
                                         float pz) {
        float dx =
          x -
          px;
        float dy =
          y -
          py;
        float dz =
          z -
          pz;
        return dx *
          dx +
          dy *
          dy +
          dz *
          dz;
    }
    final public Point3 set(float x, float y,
                            float z) { this.
                                         x =
                                         x;
                                       this.
                                         y =
                                         y;
                                       this.
                                         z =
                                         z;
                                       return this;
    }
    final public Point3 set(Point3 p) { x =
                                          p.
                                            x;
                                        y =
                                          p.
                                            y;
                                        z =
                                          p.
                                            z;
                                        return this;
    }
    final public static Point3 add(Point3 p,
                                   Vector3 v,
                                   Point3 dest) {
        dest.
          x =
          p.
            x +
            v.
              x;
        dest.
          y =
          p.
            y +
            v.
              y;
        dest.
          z =
          p.
            z +
            v.
              z;
        return dest;
    }
    final public static Vector3 sub(Point3 p1,
                                    Point3 p2,
                                    Vector3 dest) {
        dest.
          x =
          p1.
            x -
            p2.
              x;
        dest.
          y =
          p1.
            y -
            p2.
              y;
        dest.
          z =
          p1.
            z -
            p2.
              z;
        return dest;
    }
    final public static Point3 mid(Point3 p1,
                                   Point3 p2,
                                   Point3 dest) {
        dest.
          x =
          0.5F *
            (p1.
               x +
               p2.
                 x);
        dest.
          y =
          0.5F *
            (p1.
               y +
               p2.
                 y);
        dest.
          z =
          0.5F *
            (p1.
               z +
               p2.
                 z);
        return dest;
    }
    final public static Vector3 normal(Point3 p0,
                                       Point3 p1,
                                       Point3 p2) {
        float edge1x =
          p1.
            x -
          p0.
            x;
        float edge1y =
          p1.
            y -
          p0.
            y;
        float edge1z =
          p1.
            z -
          p0.
            z;
        float edge2x =
          p2.
            x -
          p0.
            x;
        float edge2y =
          p2.
            y -
          p0.
            y;
        float edge2z =
          p2.
            z -
          p0.
            z;
        float nx =
          edge1y *
          edge2z -
          edge1z *
          edge2y;
        float ny =
          edge1z *
          edge2x -
          edge1x *
          edge2z;
        float nz =
          edge1x *
          edge2y -
          edge1y *
          edge2x;
        return new Vector3(
          nx,
          ny,
          nz);
    }
    final public static Vector3 normal(Point3 p0,
                                       Point3 p1,
                                       Point3 p2,
                                       Vector3 dest) {
        float edge1x =
          p1.
            x -
          p0.
            x;
        float edge1y =
          p1.
            y -
          p0.
            y;
        float edge1z =
          p1.
            z -
          p0.
            z;
        float edge2x =
          p2.
            x -
          p0.
            x;
        float edge2y =
          p2.
            y -
          p0.
            y;
        float edge2z =
          p2.
            z -
          p0.
            z;
        dest.
          x =
          edge1y *
            edge2z -
            edge1z *
            edge2y;
        dest.
          y =
          edge1z *
            edge2x -
            edge1x *
            edge2z;
        dest.
          z =
          edge1x *
            edge2y -
            edge1y *
            edge2x;
        return dest;
    }
    final public String toString() { return String.
                                       format(
                                         "(%.2f, %.2f, %.2f)",
                                         x,
                                         y,
                                         z);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1414698752000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAMVZe2wUxxmfe/ht5AcvAw42xoSCyV1CStRgKmIcGwwHGNsY" +
       "YkLNeHfOXtjbXXbn\n7LOhNFGkQBIlKepbLQSlSJBXQ5VGtBJJQSRpCq1KWj" +
       "WVqEKJkJJIbfpQq5Sq+aPfzOzd7u2dNwcu\nraX5vLfzzffN73vNY1/8GBVZ" +
       "JqqXrAgdN4gVae/txqZF5HYVW1YfvBqU3iwq6z6xQdODKBBDQUWm\nqComWV" +
       "EZUxxV5GjX/a0pE7UYujo+rOo0QlI0sltdYctbH1uRI3Db0dO1Dx8PNwRRUQ" +
       "xVYU3TKaaK\nrnWoJGFRVB3bjUdxNEkVNRpTLNoaQ9OIlky065pFsUatvegA" +
       "CsVQsSExmRQtiKWVR0F51MAmTkS5\n+mg3VwsSppuEYkUjcltGHYxclj0Spm" +
       "2P68nlBiGlrLMf4PAZAOrGDGqBNgeqETrZf8/+Y8+FUNUA\nqlK0XiZMAiQU" +
       "9A2gygRJDBHTapNlIg+gGo0QuZeYClaVCa51ANVayrCGadIkVg+xdHWUMdZa" +
       "SYOY\nXGf6ZQxVSgyTmZSobmZsFFeIKqd/FcVVPAywZzmwBdxO9h4AliswMT" +
       "OOJZIeEt6jaODxBu+IDMbm\nDcAAQ0sShI7oGVVhDcMLVCt8qWJtONpLTUUb" +
       "BtYiPQlaKJo7qVBmawNLe/AwGaSozsvXLbqAq4wb\ngg2haKaXjUsCL831eM" +
       "nln5ZZnxw6+b3X7+OxHZaJpLL5l8Og+Z5BPSROTKJJRAy8nox8veuBZH0Q\n" +
       "IWCe6WEWPG2LTm+NffTTBsEzLw/P5qHdRKKD0qbDDT371uooxKZRauiWwpyf" +
       "hZynQ7fd05oyIGtn\nZSSyzki682zPWw889Dz5YxCVd6FiSVeTCYijGklPGI" +
       "pKzLVEIyamRO5CZUST23l/FyqB5xiEvHi7\nOR63CO1CYZW/Ktb5bzBRHEQw" +
       "E5XBs6LF9fSzgekIf04ZCKFp0FAttBASf/w/RUsjUSupxVV9LGqZ\nUlQ3hz" +
       "O/EyAA8EEA3h1hMWNQ1Bkd0RMkiiWsKZoeHVYgSyX9DpmMsv8FS0qxedWOBQ" +
       "Ks0HkTVoVY\nX6erMjEHpRPXLuzv2PDYIREMLIBtRBTNBgURW0GEKYgIBSgQ" +
       "4HJnMEXCDWDEPZCOULgql/TuXL/r\nUBOATxljYWYGYG2CudvaOyS93cnZLl" +
       "7eJAicumd3HIxcP7FaBE508tKad3TFpZcuHvt75dIgCuav\newwVVN5yJqab" +
       "FctMPWv2Zko++X9+fOMr715873NOzlDUnJPKuSNZKjZ57W/qEpGhuDnij8+p" +
       "Cm1D\n/YeDKAz5DTWNzx/KxXyvjqyUbE2XN4alJIYq4rqZwCrrStekcjpi6m" +
       "POGx4Y1fx5OjingsVoDbRi\nO2j5f9Y702B0lggk5m0PCl4+rz9SfOfvzlS8" +
       "yc2SrrRVrrWsl1CRtzVOsPSZhMD7977d/bVvfHxw\nB48UESoBCgtcckhVpB" +
       "QMud0ZAgmrQtFgjmzeqiV0WYkreEglLOI+rVp016t/eqpauEaFN2nPLvts\n" +
       "Ac77OWvQQxe/9M/5XExAYguGA8NhE2imO5LbTBOPs3mkHv71bd/5GT4C9Qxq" +
       "iKVMEF4WEEeGuB2j\n3O5LOY14+u5ipAlkL5sk9PMsz4PS/ueHm5J7f/4TPu" +
       "sK7F7n3W7YiI1W4XlGFjLrzvZm7zpsjQDf\n589uerBaPftvkDgAEiVYFq3N" +
       "JtSKVJYTbe6iksvnzs/a9U4IBTtRuapjuRPz+EdlEHjEGoEykzJW\n38djq3" +
       "qslFEOGXEjzLUNwH/U50ZlmR2VZXmjkpHbPSYNZWpOnXt/ZyoJWCdGedZce7" +
       "Tptbe3PnNQ\nVJolPps496hB6St/uLon9PS5ITHOu1Z6mA/PP/7BK9d6Zoio" +
       "FBuKhTlrunuM2FRwZFUG89ACPw2c\n+42WBS8e6Lliz6g2e2nsgO3jh+Pnye" +
       "JVT76fp7pDtuqYp0obJ1zxKp8AXcvIvbxrOSMrhddW3LRz\nq2znVhXs3ICo" +
       "EtaXGUVzXA7epGt8jVckZpxUOrzZHixikjhLXlZ3U+PvL77c+Ivq9osizUco" +
       "WuTa\nrdmc0S5tVJd4Hq3Dmgz7B5H19XkVbjOxAfuxS1c/2Pl0y4dvMV8YHM" +
       "ImH2P2M7LBMWasUGOmXL9Y\nlPuEbyfbBjtrzODQspOxC5uPcOSTLpF5Itsj" +
       "Z+L1rUev/5Je4XKctYqNXpDK3W3A0cEZ+4V3R2uK\nTz2TCKKSAVQt2Yebfq" +
       "wm2YowAHtxK33igQNQVn/2vlpsIlsza3G9N7Vcar2rpJMH8My42XNlvoVx\n" +
       "DrSwHaVhb5QGEH/YxQOVokAqk0u1jl/xZ/l1x41rJGmN4/k0xm+Bxt1pjRP5" +
       "NO7x0Si6mjldLBb5\nIKs9iob5qWNJKrtUmOi2yQ4uvOId3P63ykfxGzuDdh" +
       "61U1hodOMOlYwS1SVqGpOUtencyI9qTig+\n/twLp+k7LStF7Vw6eRp5By5d" +
       "eWyiYeXLT9zEVrPBg80rumZ03pbQiPJ2kJ8mRWTnnEKzB7Vmx3M5\nzCdpan" +
       "1ZUd2Y7fF50GbaHp95w7WX/f6iIdy2hqIQnAj4oAM+1e4RRvYB8zARK44TPP" +
       "tvaBUZyyBh\nCwhqgdZoI2nMiyRP+LHnva7Yyw9xM6e9XMqTPtAOM3KIonJZ" +
       "EQ7p0z0IH5sKwtXQFtsIF08dob07\nymSxa+n/rg/II4x80w/kt6YCcjm0iA" +
       "0ycuvceMIH4QuMPEtRjYOwd28Sm0T2AP3+VIB2QlthA11x\nS735qg/W04yc" +
       "KgDrD6eC9R5oq2ysq24p1nM+WM8zcgYqj0WoOyAckK9NBeQiaO02yPZbF7m/" +
       "8kH4\nG0Yu+CG8eJMI69jL+6GttxGuLxBhyEEIB3qLX/9O4s+cGc+mqC7n3q" +
       "mfsDPl3Tl2uepjl48YuQx2\nwbKc3y6/n4pd1kLbYttly43bhZErhdqkUPtw" +
       "vf/wscmnjPyFxUpyqDBrc0P9dSqG6oC23TbU9v+h\noVxxEiid3CYBdiMSCI" +
       "FNEkr+OAmEpxongzb8wf8T/Hof+M2MQBgUa/zqsPCoCNRNxSxboam2WdT/\n" +
       "klnCnCtciFkKSqXAnT52W8lIy83YbdlUFpwmaOO23cYLtJvPguNG1OnTt46R" +
       "NopKqS6+aKXxVjvX\nLK4OjnNNwVcaYEXxZYFdstTlfF4Un8Sk2OV9D34S++" +
       "2/+B155rNVRQyVxpOq6j7Tu56LDZPEFW6Z\nCnHC53czgS0wda+rKAqzf2xu" +
       "gW7B1kdRhYuNohL7yc20DYoHMLHH7UYew4i7iuyDLkO6MOvMyb/g\nps+FSf" +
       "ENd1Da/tKOxtQTfV/lh80iScUT/AheHkMlcSfw2NlywaTS0rIuoVMv95/5wb" +
       "3pszO/C57h\nipqswFsuen18COfZ/BfsHQmD8ivxiR/P/tGqE0ev8Dsx4z/4" +
       "sQmxeB8AAA==");
}
