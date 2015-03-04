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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
