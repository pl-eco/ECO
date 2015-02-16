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
      1414698752000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "Jr7WHMgaAAA=");
}
