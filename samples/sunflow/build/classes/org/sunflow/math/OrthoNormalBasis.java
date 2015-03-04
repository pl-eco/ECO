package org.sunflow.math;
public final class OrthoNormalBasis {
    private Vector3 u;
    private Vector3 v;
    private Vector3 w;
    private OrthoNormalBasis() { super();
                                 u = new Vector3();
                                 v = new Vector3();
                                 w = new Vector3(); }
    public void flipU() { u.negate(); }
    public void flipV() { v.negate(); }
    public void flipW() { w.negate(); }
    public void swapUV() { Vector3 t = u;
                           u = v;
                           v = t; }
    public void swapVW() { Vector3 t = v;
                           v = w;
                           w = t; }
    public void swapWU() { Vector3 t = w;
                           w = u;
                           u = t; }
    public Vector3 transform(Vector3 a, Vector3 dest) { dest.x = a.x * u.
                                                                         x +
                                                                   a.
                                                                     y *
                                                                   v.
                                                                     x +
                                                                   a.
                                                                     z *
                                                                   w.
                                                                     x;
                                                        dest.y = a.x * u.
                                                                         y +
                                                                   a.
                                                                     y *
                                                                   v.
                                                                     y +
                                                                   a.
                                                                     z *
                                                                   w.
                                                                     y;
                                                        dest.z = a.x * u.
                                                                         z +
                                                                   a.
                                                                     y *
                                                                   v.
                                                                     z +
                                                                   a.
                                                                     z *
                                                                   w.
                                                                     z;
                                                        return dest; }
    public Vector3 transform(Vector3 a) { float x = a.x * u.x + a.y * v.x +
                                            a.
                                              z *
                                            w.
                                              x;
                                          float y = a.x * u.y + a.y * v.y +
                                            a.
                                              z *
                                            w.
                                              y;
                                          float z = a.x * u.z + a.y * v.z +
                                            a.
                                              z *
                                            w.
                                              z;
                                          return a.set(x, y, z); }
    public Vector3 untransform(Vector3 a, Vector3 dest) { dest.x = Vector3.
                                                                     dot(
                                                                       a,
                                                                       u);
                                                          dest.y = Vector3.
                                                                     dot(
                                                                       a,
                                                                       v);
                                                          dest.z = Vector3.
                                                                     dot(
                                                                       a,
                                                                       w);
                                                          return dest; }
    public Vector3 untransform(Vector3 a) { float x = Vector3.dot(a, u);
                                            float y = Vector3.dot(a, v);
                                            float z = Vector3.dot(a, w);
                                            return a.set(x, y, z); }
    public float untransformX(Vector3 a) { return Vector3.dot(a, u); }
    public float untransformY(Vector3 a) { return Vector3.dot(a, v); }
    public float untransformZ(Vector3 a) { return Vector3.dot(a, w); }
    public static final OrthoNormalBasis makeFromW(Vector3 w) { OrthoNormalBasis onb =
                                                                  new OrthoNormalBasis(
                                                                  );
                                                                w.
                                                                  normalize(
                                                                    onb.
                                                                      w);
                                                                if (Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          x) <
                                                                      Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          y) &&
                                                                      Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          x) <
                                                                      Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          z)) {
                                                                    onb.
                                                                      v.
                                                                      x =
                                                                      0;
                                                                    onb.
                                                                      v.
                                                                      y =
                                                                      onb.
                                                                        w.
                                                                        z;
                                                                    onb.
                                                                      v.
                                                                      z =
                                                                      -onb.
                                                                         w.
                                                                         y;
                                                                }
                                                                else
                                                                    if (Math.
                                                                          abs(
                                                                            onb.
                                                                              w.
                                                                              y) <
                                                                          Math.
                                                                          abs(
                                                                            onb.
                                                                              w.
                                                                              z)) {
                                                                        onb.
                                                                          v.
                                                                          x =
                                                                          onb.
                                                                            w.
                                                                            z;
                                                                        onb.
                                                                          v.
                                                                          y =
                                                                          0;
                                                                        onb.
                                                                          v.
                                                                          z =
                                                                          -onb.
                                                                             w.
                                                                             x;
                                                                    }
                                                                    else {
                                                                        onb.
                                                                          v.
                                                                          x =
                                                                          onb.
                                                                            w.
                                                                            y;
                                                                        onb.
                                                                          v.
                                                                          y =
                                                                          -onb.
                                                                             w.
                                                                             x;
                                                                        onb.
                                                                          v.
                                                                          z =
                                                                          0;
                                                                    }
                                                                Vector3.
                                                                  cross(
                                                                    onb.
                                                                      v.
                                                                      normalize(
                                                                        ),
                                                                    onb.
                                                                      w,
                                                                    onb.
                                                                      u);
                                                                return onb;
    }
    public static final OrthoNormalBasis makeFromWV(Vector3 w,
                                                    Vector3 v) {
        OrthoNormalBasis onb =
          new OrthoNormalBasis(
          );
        w.
          normalize(
            onb.
              w);
        Vector3.
          cross(
            v,
            onb.
              w,
            onb.
              u).
          normalize(
            );
        Vector3.
          cross(
            onb.
              w,
            onb.
              u,
            onb.
              v);
        return onb;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "ZJKkGQAA");
}
