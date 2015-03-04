package org.sunflow.math;
public class PerlinVector {
    private static final float P1x = 0.34F;
    private static final float P1y = 0.66F;
    private static final float P1z = 0.237F;
    private static final float P2x = 0.011F;
    private static final float P2y = 0.845F;
    private static final float P2z = 0.037F;
    private static final float P3x = 0.34F;
    private static final float P3y = 0.12F;
    private static final float P3z = 0.9F;
    public static final Vector3 snoise(float x) { return new Vector3(PerlinScalar.
                                                                       snoise(
                                                                         x +
                                                                           P1x),
                                                                     PerlinScalar.
                                                                       snoise(
                                                                         x +
                                                                           P2x),
                                                                     PerlinScalar.
                                                                       snoise(
                                                                         x +
                                                                           P3x));
    }
    public static final Vector3 snoise(float x,
                                       float y) {
        return new Vector3(
          PerlinScalar.
            snoise(
              x +
                P1x,
              y +
                P1y),
          PerlinScalar.
            snoise(
              x +
                P2x,
              y +
                P2y),
          PerlinScalar.
            snoise(
              x +
                P3x,
              y +
                P3y));
    }
    public static final Vector3 snoise(float x,
                                       float y,
                                       float z) {
        return new Vector3(
          PerlinScalar.
            snoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z),
          PerlinScalar.
            snoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z),
          PerlinScalar.
            snoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z));
    }
    public static final Vector3 snoise(float x,
                                       float y,
                                       float z,
                                       float t) {
        return new Vector3(
          PerlinScalar.
            snoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              t),
          PerlinScalar.
            snoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              t),
          PerlinScalar.
            snoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              t));
    }
    public static final Vector3 snoise(Point2 p) {
        return snoise(
                 p.
                   x,
                 p.
                   y);
    }
    public static final Vector3 snoise(Point3 p) {
        return snoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z);
    }
    public static final Vector3 snoise(Point3 p,
                                       float t) {
        return snoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 t);
    }
    public static final Vector3 noise(float x) {
        return new Vector3(
          PerlinScalar.
            noise(
              x +
                P1x),
          PerlinScalar.
            noise(
              x +
                P2x),
          PerlinScalar.
            noise(
              x +
                P3x));
    }
    public static final Vector3 noise(float x,
                                      float y) {
        return new Vector3(
          PerlinScalar.
            noise(
              x +
                P1x,
              y +
                P1y),
          PerlinScalar.
            noise(
              x +
                P2x,
              y +
                P2y),
          PerlinScalar.
            noise(
              x +
                P3x,
              y +
                P3y));
    }
    public static final Vector3 noise(float x,
                                      float y,
                                      float z) {
        return new Vector3(
          PerlinScalar.
            noise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z),
          PerlinScalar.
            noise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z),
          PerlinScalar.
            noise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z));
    }
    public static final Vector3 noise(float x,
                                      float y,
                                      float z,
                                      float t) {
        return new Vector3(
          PerlinScalar.
            noise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              t),
          PerlinScalar.
            noise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              t),
          PerlinScalar.
            noise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              t));
    }
    public static final Vector3 noise(Point2 p) {
        return noise(
                 p.
                   x,
                 p.
                   y);
    }
    public static final Vector3 noise(Point3 p) {
        return noise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z);
    }
    public static final Vector3 noise(Point3 p,
                                      float t) {
        return noise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 t);
    }
    public static final Vector3 pnoise(float x,
                                       float period) {
        return new Vector3(
          PerlinScalar.
            pnoise(
              x +
                P1x,
              period),
          PerlinScalar.
            pnoise(
              x +
                P2x,
              period),
          PerlinScalar.
            pnoise(
              x +
                P3x,
              period));
    }
    public static final Vector3 pnoise(float x,
                                       float y,
                                       float w,
                                       float h) {
        return new Vector3(
          PerlinScalar.
            pnoise(
              x +
                P1x,
              y +
                P1y,
              w,
              h),
          PerlinScalar.
            pnoise(
              x +
                P2x,
              y +
                P2y,
              w,
              h),
          PerlinScalar.
            pnoise(
              x +
                P3x,
              y +
                P3y,
              w,
              h));
    }
    public static final Vector3 pnoise(float x,
                                       float y,
                                       float z,
                                       float w,
                                       float h,
                                       float d) {
        return new Vector3(
          PerlinScalar.
            pnoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              w,
              h,
              d),
          PerlinScalar.
            pnoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              w,
              h,
              d),
          PerlinScalar.
            pnoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              w,
              h,
              d));
    }
    public static final Vector3 pnoise(float x,
                                       float y,
                                       float z,
                                       float t,
                                       float w,
                                       float h,
                                       float d,
                                       float p) {
        return new Vector3(
          PerlinScalar.
            pnoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              t,
              w,
              h,
              d,
              p),
          PerlinScalar.
            pnoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              t,
              w,
              h,
              d,
              p),
          PerlinScalar.
            pnoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              t,
              w,
              h,
              d,
              p));
    }
    public static final Vector3 pnoise(Point2 p,
                                       float periodx,
                                       float periody) {
        return pnoise(
                 p.
                   x,
                 p.
                   y,
                 periodx,
                 periody);
    }
    public static final Vector3 pnoise(Point3 p,
                                       Vector3 period) {
        return pnoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 period.
                   x,
                 period.
                   y,
                 period.
                   z);
    }
    public static final Vector3 pnoise(Point3 p,
                                       float t,
                                       Vector3 pperiod,
                                       float tperiod) {
        return pnoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 t,
                 pperiod.
                   x,
                 pperiod.
                   y,
                 pperiod.
                   z,
                 tperiod);
    }
    public static final Vector3 spnoise(float x,
                                        float period) {
        return new Vector3(
          PerlinScalar.
            spnoise(
              x +
                P1x,
              period),
          PerlinScalar.
            spnoise(
              x +
                P2x,
              period),
          PerlinScalar.
            spnoise(
              x +
                P3x,
              period));
    }
    public static final Vector3 spnoise(float x,
                                        float y,
                                        float w,
                                        float h) {
        return new Vector3(
          PerlinScalar.
            spnoise(
              x +
                P1x,
              y +
                P1y,
              w,
              h),
          PerlinScalar.
            spnoise(
              x +
                P2x,
              y +
                P2y,
              w,
              h),
          PerlinScalar.
            spnoise(
              x +
                P3x,
              y +
                P3y,
              w,
              h));
    }
    public static final Vector3 spnoise(float x,
                                        float y,
                                        float z,
                                        float w,
                                        float h,
                                        float d) {
        return new Vector3(
          PerlinScalar.
            spnoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              w,
              h,
              d),
          PerlinScalar.
            spnoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              w,
              h,
              d),
          PerlinScalar.
            spnoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              w,
              h,
              d));
    }
    public static final Vector3 spnoise(float x,
                                        float y,
                                        float z,
                                        float t,
                                        float w,
                                        float h,
                                        float d,
                                        float p) {
        return new Vector3(
          PerlinScalar.
            spnoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              t,
              w,
              h,
              d,
              p),
          PerlinScalar.
            spnoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              t,
              w,
              h,
              d,
              p),
          PerlinScalar.
            spnoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              t,
              w,
              h,
              d,
              p));
    }
    public static final Vector3 spnoise(Point2 p,
                                        float periodx,
                                        float periody) {
        return spnoise(
                 p.
                   x,
                 p.
                   y,
                 periodx,
                 periody);
    }
    public static final Vector3 spnoise(Point3 p,
                                        Vector3 period) {
        return spnoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 period.
                   x,
                 period.
                   y,
                 period.
                   z);
    }
    public static final Vector3 spnoise(Point3 p,
                                        float t,
                                        Vector3 pperiod,
                                        float tperiod) {
        return spnoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 t,
                 pperiod.
                   x,
                 pperiod.
                   y,
                 pperiod.
                   z,
                 tperiod);
    }
    public PerlinVector() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
