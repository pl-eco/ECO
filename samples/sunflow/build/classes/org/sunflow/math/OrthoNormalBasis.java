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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
