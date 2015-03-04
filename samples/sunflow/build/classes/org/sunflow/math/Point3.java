package org.sunflow.math;
public final class Point3 {
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
    public final float distanceTo(Point3 p) { float dx = x - p.x;
                                              float dy = y - p.y;
                                              float dz = z - p.z;
                                              return (float) Math.sqrt(dx *
                                                                         dx +
                                                                         dy *
                                                                         dy +
                                                                         dz *
                                                                         dz);
    }
    public final float distanceTo(float px, float py, float pz) { float dx =
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
    public final float distanceToSquared(Point3 p) {
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
    public final float distanceToSquared(float px,
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
    public final Point3 set(float x, float y,
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
    public final Point3 set(Point3 p) { x =
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
    public static final Point3 add(Point3 p,
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
    public static final Vector3 sub(Point3 p1,
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
    public static final Point3 mid(Point3 p1,
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
    public static final Vector3 normal(Point3 p0,
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
    public static final Vector3 normal(Point3 p0,
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
    public final String toString() { return String.
                                       format(
                                         "(%.2f, %.2f, %.2f)",
                                         x,
                                         y,
                                         z);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "5ZnZ1/k72P8ADoGuRtgaAAA=");
}
