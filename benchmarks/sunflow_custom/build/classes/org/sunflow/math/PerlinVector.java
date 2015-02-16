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
      1159026716000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "IqwR9g3EzksTftp0cugGe939f+K6ZyOAKQAA");
}
