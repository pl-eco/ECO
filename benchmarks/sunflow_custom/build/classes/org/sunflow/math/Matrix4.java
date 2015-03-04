package org.sunflow.math;
public final class Matrix4 {
    private float m00;
    private float m01;
    private float m02;
    private float m03;
    private float m10;
    private float m11;
    private float m12;
    private float m13;
    private float m20;
    private float m21;
    private float m22;
    private float m23;
    private float m30;
    private float m31;
    private float m32;
    private float m33;
    public static final Matrix4 ZERO = new Matrix4();
    public static final Matrix4 IDENTITY = Matrix4.scale(1);
    public Matrix4() { super(); }
    public Matrix4(float m00, float m01, float m02, float m03, float m10,
                   float m11,
                   float m12,
                   float m13,
                   float m20,
                   float m21,
                   float m22,
                   float m23,
                   float m30,
                   float m31,
                   float m32,
                   float m33) { super();
                                this.m00 = m00;
                                this.m01 = m01;
                                this.m02 = m02;
                                this.m03 = m03;
                                this.m10 = m10;
                                this.m11 = m11;
                                this.m12 = m12;
                                this.m13 = m13;
                                this.m20 = m20;
                                this.m21 = m21;
                                this.m22 = m22;
                                this.m23 = m23;
                                this.m30 = m30;
                                this.m31 = m31;
                                this.m32 = m32;
                                this.m33 = m33; }
    public Matrix4(float[] m, boolean rowMajor) { super();
                                                  if (rowMajor) { m00 = m[0];
                                                                  m01 = m[1];
                                                                  m02 = m[2];
                                                                  m03 = m[3];
                                                                  m10 = m[4];
                                                                  m11 = m[5];
                                                                  m12 = m[6];
                                                                  m13 = m[7];
                                                                  m20 = m[8];
                                                                  m21 = m[9];
                                                                  m22 = m[10];
                                                                  m23 = m[11];
                                                                  m30 = m[12];
                                                                  m31 = m[13];
                                                                  m32 = m[14];
                                                                  m33 = m[15];
                                                  }
                                                  else {
                                                      m00 =
                                                        m[0];
                                                      m01 =
                                                        m[4];
                                                      m02 =
                                                        m[8];
                                                      m03 =
                                                        m[12];
                                                      m10 =
                                                        m[1];
                                                      m11 =
                                                        m[5];
                                                      m12 =
                                                        m[9];
                                                      m13 =
                                                        m[13];
                                                      m20 =
                                                        m[2];
                                                      m21 =
                                                        m[6];
                                                      m22 =
                                                        m[10];
                                                      m23 =
                                                        m[14];
                                                      m30 =
                                                        m[3];
                                                      m31 =
                                                        m[7];
                                                      m32 =
                                                        m[11];
                                                      m33 =
                                                        m[15];
                                                  } }
    public final float[] asRowMajor() { return new float[] { m00, m01, m02,
                                        m03,
                                        m10,
                                        m11,
                                        m12,
                                        m13,
                                        m20,
                                        m21,
                                        m22,
                                        m23,
                                        m30,
                                        m31,
                                        m32,
                                        m33 }; }
    public final float[] asColMajor() { return new float[] { m00, m10, m20,
                                        m30,
                                        m01,
                                        m11,
                                        m21,
                                        m31,
                                        m02,
                                        m12,
                                        m22,
                                        m32,
                                        m03,
                                        m13,
                                        m23,
                                        m33 }; }
    public final float determinant() { float A0 = m00 * m11 - m01 * m10;
                                       float A1 = m00 * m12 - m02 * m10;
                                       float A2 = m00 * m13 - m03 * m10;
                                       float A3 = m01 * m12 - m02 * m11;
                                       float A4 = m01 * m13 - m03 * m11;
                                       float A5 = m02 * m13 - m03 * m12;
                                       float B0 = m20 * m31 - m21 * m30;
                                       float B1 = m20 * m32 - m22 * m30;
                                       float B2 = m20 * m33 - m23 * m30;
                                       float B3 = m21 * m32 - m22 * m31;
                                       float B4 = m21 * m33 - m23 * m31;
                                       float B5 = m22 * m33 - m23 * m32;
                                       return A0 * B5 - A1 * B4 + A2 * B3 +
                                         A3 *
                                         B2 -
                                         A4 *
                                         B1 +
                                         A5 *
                                         B0; }
    public final Matrix4 inverse() { float A0 = m00 * m11 - m01 * m10;
                                     float A1 = m00 * m12 - m02 * m10;
                                     float A2 = m00 * m13 - m03 * m10;
                                     float A3 = m01 * m12 - m02 * m11;
                                     float A4 = m01 * m13 - m03 * m11;
                                     float A5 = m02 * m13 - m03 * m12;
                                     float B0 = m20 * m31 - m21 * m30;
                                     float B1 = m20 * m32 - m22 * m30;
                                     float B2 = m20 * m33 - m23 * m30;
                                     float B3 = m21 * m32 - m22 * m31;
                                     float B4 = m21 * m33 - m23 * m31;
                                     float B5 = m22 * m33 - m23 * m32;
                                     float det = A0 * B5 - A1 * B4 + A2 *
                                       B3 +
                                       A3 *
                                       B2 -
                                       A4 *
                                       B1 +
                                       A5 *
                                       B0;
                                     if (Math.abs(det) < 1.0E-12F) return null;
                                     float invDet = 1 / det;
                                     Matrix4 inv = new Matrix4();
                                     inv.m00 = (+m11 * B5 - m12 * B4 + m13 *
                                                  B3) * invDet;
                                     inv.m10 = (-m10 * B5 + m12 * B2 - m13 *
                                                  B1) * invDet;
                                     inv.m20 = (+m10 * B4 - m11 * B2 + m13 *
                                                  B0) * invDet;
                                     inv.m30 = (-m10 * B3 + m11 * B1 - m12 *
                                                  B0) * invDet;
                                     inv.m01 = (-m01 * B5 + m02 * B4 - m03 *
                                                  B3) * invDet;
                                     inv.m11 = (+m00 * B5 - m02 * B2 + m03 *
                                                  B1) * invDet;
                                     inv.m21 = (-m00 * B4 + m01 * B2 - m03 *
                                                  B0) * invDet;
                                     inv.m31 = (+m00 * B3 - m01 * B1 + m02 *
                                                  B0) * invDet;
                                     inv.m02 = (+m31 * A5 - m32 * A4 + m33 *
                                                  A3) * invDet;
                                     inv.m12 = (-m30 * A5 + m32 * A2 - m33 *
                                                  A1) * invDet;
                                     inv.m22 = (+m30 * A4 - m31 * A2 + m33 *
                                                  A0) * invDet;
                                     inv.m32 = (-m30 * A3 + m31 * A1 - m32 *
                                                  A0) * invDet;
                                     inv.m03 = (-m21 * A5 + m22 * A4 - m23 *
                                                  A3) * invDet;
                                     inv.m13 = (+m20 * A5 - m22 * A2 + m23 *
                                                  A1) * invDet;
                                     inv.m23 = (-m20 * A4 + m21 * A2 - m23 *
                                                  A0) * invDet;
                                     inv.m33 = (+m20 * A3 - m21 * A1 + m22 *
                                                  A0) * invDet;
                                     return inv; }
    public final Matrix4 multiply(Matrix4 m) { float rm00 = m00 * m.m00 +
                                                 m01 *
                                                 m.
                                                   m10 +
                                                 m02 *
                                                 m.
                                                   m20 +
                                                 m03 *
                                                 m.
                                                   m30;
                                               float rm01 = m00 * m.m01 +
                                                 m01 *
                                                 m.
                                                   m11 +
                                                 m02 *
                                                 m.
                                                   m21 +
                                                 m03 *
                                                 m.
                                                   m31;
                                               float rm02 = m00 * m.m02 +
                                                 m01 *
                                                 m.
                                                   m12 +
                                                 m02 *
                                                 m.
                                                   m22 +
                                                 m03 *
                                                 m.
                                                   m32;
                                               float rm03 = m00 * m.m03 +
                                                 m01 *
                                                 m.
                                                   m13 +
                                                 m02 *
                                                 m.
                                                   m23 +
                                                 m03 *
                                                 m.
                                                   m33;
                                               float rm10 = m10 * m.m00 +
                                                 m11 *
                                                 m.
                                                   m10 +
                                                 m12 *
                                                 m.
                                                   m20 +
                                                 m13 *
                                                 m.
                                                   m30;
                                               float rm11 = m10 * m.m01 +
                                                 m11 *
                                                 m.
                                                   m11 +
                                                 m12 *
                                                 m.
                                                   m21 +
                                                 m13 *
                                                 m.
                                                   m31;
                                               float rm12 = m10 * m.m02 +
                                                 m11 *
                                                 m.
                                                   m12 +
                                                 m12 *
                                                 m.
                                                   m22 +
                                                 m13 *
                                                 m.
                                                   m32;
                                               float rm13 = m10 * m.m03 +
                                                 m11 *
                                                 m.
                                                   m13 +
                                                 m12 *
                                                 m.
                                                   m23 +
                                                 m13 *
                                                 m.
                                                   m33;
                                               float rm20 = m20 * m.m00 +
                                                 m21 *
                                                 m.
                                                   m10 +
                                                 m22 *
                                                 m.
                                                   m20 +
                                                 m23 *
                                                 m.
                                                   m30;
                                               float rm21 = m20 * m.m01 +
                                                 m21 *
                                                 m.
                                                   m11 +
                                                 m22 *
                                                 m.
                                                   m21 +
                                                 m23 *
                                                 m.
                                                   m31;
                                               float rm22 = m20 * m.m02 +
                                                 m21 *
                                                 m.
                                                   m12 +
                                                 m22 *
                                                 m.
                                                   m22 +
                                                 m23 *
                                                 m.
                                                   m32;
                                               float rm23 = m20 * m.m03 +
                                                 m21 *
                                                 m.
                                                   m13 +
                                                 m22 *
                                                 m.
                                                   m23 +
                                                 m23 *
                                                 m.
                                                   m33;
                                               float rm30 = m30 * m.m00 +
                                                 m31 *
                                                 m.
                                                   m10 +
                                                 m32 *
                                                 m.
                                                   m20 +
                                                 m33 *
                                                 m.
                                                   m30;
                                               float rm31 = m30 * m.m01 +
                                                 m31 *
                                                 m.
                                                   m11 +
                                                 m32 *
                                                 m.
                                                   m21 +
                                                 m33 *
                                                 m.
                                                   m31;
                                               float rm32 = m30 * m.m02 +
                                                 m31 *
                                                 m.
                                                   m12 +
                                                 m32 *
                                                 m.
                                                   m22 +
                                                 m33 *
                                                 m.
                                                   m32;
                                               float rm33 = m30 * m.m03 +
                                                 m31 *
                                                 m.
                                                   m13 +
                                                 m32 *
                                                 m.
                                                   m23 +
                                                 m33 *
                                                 m.
                                                   m33;
                                               return new Matrix4(rm00, rm01,
                                                                  rm02,
                                                                  rm03,
                                                                  rm10,
                                                                  rm11,
                                                                  rm12,
                                                                  rm13,
                                                                  rm20,
                                                                  rm21,
                                                                  rm22,
                                                                  rm23,
                                                                  rm30,
                                                                  rm31,
                                                                  rm32,
                                                                  rm33); }
    public final BoundingBox transform(BoundingBox b) { if (b.isEmpty()) return new BoundingBox(
                                                                           );
                                                        BoundingBox rb =
                                                          new BoundingBox(
                                                          transformP(
                                                            b.
                                                              getMinimum(
                                                                )));
                                                        rb.include(
                                                             transformP(
                                                               b.
                                                                 getMaximum(
                                                                   )));
                                                        for (int i =
                                                               1;
                                                             i <
                                                               7;
                                                             i++)
                                                            rb.
                                                              include(
                                                                transformP(
                                                                  b.
                                                                    getCorner(
                                                                      i)));
                                                        return rb;
    }
    public final Vector3 transformV(Vector3 v) { Vector3 rv = new Vector3(
                                                   );
                                                 rv.x = m00 * v.x +
                                                          m01 *
                                                          v.
                                                            y +
                                                          m02 *
                                                          v.
                                                            z;
                                                 rv.y = m10 * v.x +
                                                          m11 *
                                                          v.
                                                            y +
                                                          m12 *
                                                          v.
                                                            z;
                                                 rv.z = m20 * v.x +
                                                          m21 *
                                                          v.
                                                            y +
                                                          m22 *
                                                          v.
                                                            z;
                                                 return rv; }
    public final Vector3 transformTransposeV(Vector3 v) { Vector3 rv =
                                                            new Vector3(
                                                            );
                                                          rv.x = m00 *
                                                                   v.
                                                                     x +
                                                                   m10 *
                                                                   v.
                                                                     y +
                                                                   m20 *
                                                                   v.
                                                                     z;
                                                          rv.y = m01 *
                                                                   v.
                                                                     x +
                                                                   m11 *
                                                                   v.
                                                                     y +
                                                                   m21 *
                                                                   v.
                                                                     z;
                                                          rv.z = m02 *
                                                                   v.
                                                                     x +
                                                                   m12 *
                                                                   v.
                                                                     y +
                                                                   m22 *
                                                                   v.
                                                                     z;
                                                          return rv;
    }
    public final Point3 transformP(Point3 p) { Point3 rp = new Point3(
                                                 );
                                               rp.x = m00 * p.x +
                                                        m01 *
                                                        p.
                                                          y +
                                                        m02 *
                                                        p.
                                                          z +
                                                        m03;
                                               rp.y = m10 * p.x +
                                                        m11 *
                                                        p.
                                                          y +
                                                        m12 *
                                                        p.
                                                          z +
                                                        m13;
                                               rp.z = m20 * p.x +
                                                        m21 *
                                                        p.
                                                          y +
                                                        m22 *
                                                        p.
                                                          z +
                                                        m23;
                                               return rp; }
    public final float transformVX(float x, float y, float z) { return m00 *
                                                                  x +
                                                                  m01 *
                                                                  y +
                                                                  m02 *
                                                                  z;
    }
    public final float transformVY(float x, float y, float z) { return m10 *
                                                                  x +
                                                                  m11 *
                                                                  y +
                                                                  m12 *
                                                                  z;
    }
    public final float transformVZ(float x, float y, float z) { return m20 *
                                                                  x +
                                                                  m21 *
                                                                  y +
                                                                  m22 *
                                                                  z;
    }
    public final float transformTransposeVX(float x, float y, float z) {
        return m00 *
          x +
          m10 *
          y +
          m20 *
          z;
    }
    public final float transformTransposeVY(float x, float y, float z) {
        return m01 *
          x +
          m11 *
          y +
          m21 *
          z;
    }
    public final float transformTransposeVZ(float x, float y, float z) {
        return m02 *
          x +
          m12 *
          y +
          m22 *
          z;
    }
    public final float transformPX(float x, float y, float z) { return m00 *
                                                                  x +
                                                                  m01 *
                                                                  y +
                                                                  m02 *
                                                                  z +
                                                                  m03;
    }
    public final float transformPY(float x, float y, float z) { return m10 *
                                                                  x +
                                                                  m11 *
                                                                  y +
                                                                  m12 *
                                                                  z +
                                                                  m13;
    }
    public final float transformPZ(float x, float y, float z) { return m20 *
                                                                  x +
                                                                  m21 *
                                                                  y +
                                                                  m22 *
                                                                  z +
                                                                  m23;
    }
    public static final Matrix4 translation(float x, float y, float z) {
        Matrix4 m =
          new Matrix4(
          );
        m.
          m00 =
          (m.
             m11 =
             (m.
                m22 =
                (m.
                   m33 =
                   1)));
        m.
          m03 =
          x;
        m.
          m13 =
          y;
        m.
          m23 =
          z;
        return m;
    }
    public static final Matrix4 rotateX(float theta) { Matrix4 m =
                                                         new Matrix4(
                                                         );
                                                       float s = (float)
                                                                   Math.
                                                                   sin(
                                                                     theta);
                                                       float c = (float)
                                                                   Math.
                                                                   cos(
                                                                     theta);
                                                       m.m00 = (m.
                                                                  m33 =
                                                                  1);
                                                       m.m11 = (m.
                                                                  m22 =
                                                                  c);
                                                       m.m12 = -s;
                                                       m.m21 = +s;
                                                       return m; }
    public static final Matrix4 rotateY(float theta) { Matrix4 m =
                                                         new Matrix4(
                                                         );
                                                       float s = (float)
                                                                   Math.
                                                                   sin(
                                                                     theta);
                                                       float c = (float)
                                                                   Math.
                                                                   cos(
                                                                     theta);
                                                       m.m11 = (m.
                                                                  m33 =
                                                                  1);
                                                       m.m00 = (m.
                                                                  m22 =
                                                                  c);
                                                       m.m02 = +s;
                                                       m.m20 = -s;
                                                       return m; }
    public static final Matrix4 rotateZ(float theta) { Matrix4 m =
                                                         new Matrix4(
                                                         );
                                                       float s = (float)
                                                                   Math.
                                                                   sin(
                                                                     theta);
                                                       float c = (float)
                                                                   Math.
                                                                   cos(
                                                                     theta);
                                                       m.m22 = (m.
                                                                  m33 =
                                                                  1);
                                                       m.m00 = (m.
                                                                  m11 =
                                                                  c);
                                                       m.m01 = -s;
                                                       m.m10 = +s;
                                                       return m; }
    public static final Matrix4 rotate(float x, float y, float z,
                                       float theta) { Matrix4 m =
                                                        new Matrix4(
                                                        );
                                                      float invLen =
                                                        1 /
                                                        (float)
                                                          Math.
                                                          sqrt(
                                                            x *
                                                              x +
                                                              y *
                                                              y +
                                                              z *
                                                              z);
                                                      x *= invLen;
                                                      y *= invLen;
                                                      z *= invLen;
                                                      float s = (float)
                                                                  Math.
                                                                  sin(
                                                                    theta);
                                                      float c = (float)
                                                                  Math.
                                                                  cos(
                                                                    theta);
                                                      float t = 1 -
                                                        c;
                                                      m.m00 = t *
                                                                x *
                                                                x +
                                                                c;
                                                      m.m11 = t *
                                                                y *
                                                                y +
                                                                c;
                                                      m.m22 = t *
                                                                z *
                                                                z +
                                                                c;
                                                      float txy =
                                                        t *
                                                        x *
                                                        y;
                                                      float sz = s *
                                                        z;
                                                      m.m01 = txy -
                                                                sz;
                                                      m.m10 = txy +
                                                                sz;
                                                      float txz =
                                                        t *
                                                        x *
                                                        z;
                                                      float sy = s *
                                                        y;
                                                      m.m02 = txz +
                                                                sy;
                                                      m.m20 = txz -
                                                                sy;
                                                      float tyz =
                                                        t *
                                                        y *
                                                        z;
                                                      float sx = s *
                                                        x;
                                                      m.m12 = tyz -
                                                                sx;
                                                      m.m21 = tyz +
                                                                sx;
                                                      m.m33 = 1;
                                                      return m; }
    public static final Matrix4 scale(float s) { Matrix4 m = new Matrix4(
                                                   );
                                                 m.m00 = (m.m11 =
                                                            (m.
                                                               m22 =
                                                               s));
                                                 m.m33 = 1;
                                                 return m; }
    public static final Matrix4 scale(float sx, float sy, float sz) {
        Matrix4 m =
          new Matrix4(
          );
        m.
          m00 =
          sx;
        m.
          m11 =
          sy;
        m.
          m22 =
          sz;
        m.
          m33 =
          1;
        return m;
    }
    public static final Matrix4 fromBasis(OrthoNormalBasis basis) {
        Matrix4 m =
          new Matrix4(
          );
        Vector3 u =
          basis.
          transform(
            new Vector3(
              1,
              0,
              0));
        Vector3 v =
          basis.
          transform(
            new Vector3(
              0,
              1,
              0));
        Vector3 w =
          basis.
          transform(
            new Vector3(
              0,
              0,
              1));
        m.
          m00 =
          u.
            x;
        m.
          m01 =
          v.
            x;
        m.
          m02 =
          w.
            x;
        m.
          m10 =
          u.
            y;
        m.
          m11 =
          v.
            y;
        m.
          m12 =
          w.
            y;
        m.
          m20 =
          u.
            z;
        m.
          m21 =
          v.
            z;
        m.
          m22 =
          w.
            z;
        m.
          m33 =
          1;
        return m;
    }
    public static final Matrix4 blend(Matrix4 m0, Matrix4 m1, float t) {
        Matrix4 m =
          new Matrix4(
          );
        m.
          m00 =
          (1 -
             t) *
            m0.
              m00 +
            t *
            m1.
              m00;
        m.
          m01 =
          (1 -
             t) *
            m0.
              m01 +
            t *
            m1.
              m01;
        m.
          m02 =
          (1 -
             t) *
            m0.
              m02 +
            t *
            m1.
              m02;
        m.
          m03 =
          (1 -
             t) *
            m0.
              m03 +
            t *
            m1.
              m03;
        m.
          m10 =
          (1 -
             t) *
            m0.
              m10 +
            t *
            m1.
              m10;
        m.
          m11 =
          (1 -
             t) *
            m0.
              m11 +
            t *
            m1.
              m11;
        m.
          m12 =
          (1 -
             t) *
            m0.
              m12 +
            t *
            m1.
              m12;
        m.
          m13 =
          (1 -
             t) *
            m0.
              m13 +
            t *
            m1.
              m13;
        m.
          m20 =
          (1 -
             t) *
            m0.
              m20 +
            t *
            m1.
              m20;
        m.
          m21 =
          (1 -
             t) *
            m0.
              m21 +
            t *
            m1.
              m21;
        m.
          m22 =
          (1 -
             t) *
            m0.
              m22 +
            t *
            m1.
              m22;
        m.
          m23 =
          (1 -
             t) *
            m0.
              m23 +
            t *
            m1.
              m23;
        m.
          m30 =
          (1 -
             t) *
            m0.
              m30 +
            t *
            m1.
              m30;
        m.
          m31 =
          (1 -
             t) *
            m0.
              m31 +
            t *
            m1.
              m31;
        m.
          m32 =
          (1 -
             t) *
            m0.
              m32 +
            t *
            m1.
              m32;
        m.
          m33 =
          (1 -
             t) *
            m0.
              m33 +
            t *
            m1.
              m33;
        return m;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVaD3AUVxl/d/kfArmEfykECCFggXoHRWoL2AohQDCQSFKm" +
                                                    "pNOGzd67ZGFvd7u7Fw4QWhg6MIwiYymCg9HW1v7hTzuddtCxdXAcW2otWsbW" +
                                                    "0dGiHUertdY609op2vp9b9/t3u3drns2l5n9svf+/N73+77vfe+93T3zNqkw" +
                                                    "dLJQU+WdQ7JqRmnajG6Tl0bNnRo1ouu7lvYIukHj7bJgGH1QNiC2Pln//tWj" +
                                                    "w5EwqewnEwVFUU3BlFTF2EQNVR6h8S5S75R2yDRpmCTStU0YEWIpU5JjXZJh" +
                                                    "Lu8i47K6mqStK6NCDFSIgQoxpkJspdMKOo2nSirZjj0ExTTuIntJqItUaiKq" +
                                                    "Z5LZuSCaoAtJDtPDGABCNf7eDKRY57ROWmzuFuc8wvcvjB37+p2Rp8pIfT+p" +
                                                    "l5ReVEcEJUwYpJ/UJWlykOrGynicxvtJg0JpvJfqkiBLu5je/aTRkIYUwUzp" +
                                                    "1DYSFqY0qrMxHcvVichNT4mmqtv0EhKV45lfFQlZGAKuUxyuFsM1WA4EayVQ" +
                                                    "TE8IIs10Kd8uKXGTzHL3sDm2fQEaQNeqJDWHVXuockWAAtJo+U4WlKFYr6lL" +
                                                    "yhA0rVBTMIpJpnmCoq01QdwuDNEBkzS52/VYVdCqhhkCu5hksrsZQwIvTXN5" +
                                                    "Kcs/b29ccWS3sk4JM53jVJRR/2roNNPVaRNNUJ0qIrU61i3oOi5Mee5QmBBo" +
                                                    "PNnV2Gpz/kvvfv66mRcuWm2mF2jTPbiNiuaA+NDghFea2+ffVIZqVGuqIaHz" +
                                                    "c5iz8O/hNcvTGsy8KTYiVkYzlRc2Pb/lnsfpW2FS20kqRVVOJSGOGkQ1qUky" +
                                                    "1ddSheqCSeOdpIYq8XZW30mq4L5LUqhV2p1IGNTsJOUyK6pU2W8wUQIg0ERV" +
                                                    "cC8pCTVzrwnmMLtPa4SQ8XCRRriqiPXH/ptkIDasJmlMEAVFUtQYxC4VdHE4" +
                                                    "RkV1QKeaGuto744NgpWHk4K+3YgZKSUhqzsGxJRhqsmYoYsxVR/KFMeSMGhs" +
                                                    "gwBxlf5MFANNK/0QaWQZ2REKgQOa3dNfhpmzTpXjVB8Qj6VWdbx7buClsD0d" +
                                                    "uH3ArTBClI8QxRGifAQSCjHgSTiS5VXwyXaY3ZD36ub33rF+66HWMggnbUc5" +
                                                    "GLQMmrYCMT58h6i2OymgkyU6EeKw6cHbD0Y/eOQWKw5j3vm6YG9y4cSOfZvv" +
                                                    "XhQm4dzEi3SgqBa792C6tNNim3vCFcKtP/jm+08c36M6Uy8nk/OMkN8TZ3Sr" +
                                                    "2/C6KtI45EgHfkGL8MzAc3vawqQc0gSkRlOAUIasM9M9Rs7MXp7JksilAggn" +
                                                    "VD0pyFiVSW215rCu7nBKWERMYPcN4JRxmdifzWOf/cfaiRrKSVYEoZddLFgW" +
                                                    "XvP9Cyef+cbCm8LZCbs+awnspaY1/RucIOnTKYXy353oue/+tw/eziIEWswp" +
                                                    "NEAbynZIBuAyMOu9F+/69ZXXH/pl2I6qkAmrYmpQlsQ0YMxzRoFUIUO6Qt+3" +
                                                    "3aok1biUkIRBmWJw/rt+7uJn/nYkYnlThpJMMFz3vwGc8mtWkXteuvNfMxlM" +
                                                    "SMSlymHuNLMMMNFBXqnrwk7UI73v8oyTLwjfhEwK2cuQdlGWkAhjRpjpY8xV" +
                                                    "C5iMuuoWo2jR8upYwbR8H9/IfXxjQR+jaHONFmGI6P75PhsnXUpCLh/hi01s" +
                                                    "T+OV7afePGtNYPfK5GpMDx07/HH0yLFw1vI9J28Fze5jLeFM5fEWxY/hLwTX" +
                                                    "R3ghNSywUnhjO19HWuyFRNMwUGb7qcWGWPPnJ/b84NE9By0ajbmrVwdszs6+" +
                                                    "9p+fRU/8/sUCKRNmgiqwmFo2NoLRvcEnFFahWBI8FHp5KPQGDoUwQwyD7eZ6" +
                                                    "hwKLbMuzo9+dc+nu0Tl/APP0k2rJgJ3oSn2owAYnq88/zlx56/L4GedYGiwf" +
                                                    "FAxm0lr3zjB/45ezn2Mc6jT2b5mtf8hKF/6hvAaHyVqLPuyWB/e/8QFzcd5q" +
                                                    "UiC6Xf37Y2dOTWu/+S3W30nr2HtWOn9FBhM5fa9/PPleuLXyJ2FS1U8iIj8J" +
                                                    "bBbkFCbPfjCCkTkewGkhpz53J2tt25bby1aze3plDeteUJywhntszfxhrSFa" +
                                                    "OkRY0HyRtW5lci6Ka61Qwdv5OBkkRZDTkKdlqgyZw6zdZ1Est8LzcyYpA4/i" +
                                                    "7XotnRdw+HuyyfMnMoItsapQTMWZOmsHIqlR+zgClekCrp+Rs//YwILGMfnh" +
                                                    "x06fN19ZuMya8gu8w8Td8YX9f53Wd/Pw1iJ2HbNcvndDPrbhzItr54lfC5My" +
                                                    "23N555rcTstz/VWrUziIKX05XptpTYv1hWZ4dj6hPnVDKGC1qxDRD5bbwLaz" +
                                                    "Ci+dHUnNZIvdru9NfXrFI6Ovs7U7TQqFQdWgqspUUNgwa31UYDv51fnpzkJq" +
                                                    "Yr8a2X2XnfvqsMUUuGp47qtx5z4ez3rheIadRpWmSyMCnoZJWXLRIjs/RzSv" +
                                                    "sWr5WLUeYzE2JoNbHABuHIcb5wG324G7PgBcHYer84Db68AtCQA3nsON94Db" +
                                                    "Z8MtDmK7CRxuggfcAQcuiO3qOVy9B9xBBy6I7SIcLuIBd9iBC2K7Bg7X4AH3" +
                                                    "FRvu+iC2a+RwjR5wRx24ILabyOEmesDd58AFsd0kDjfJA+64AxfEdpM53GQP" +
                                                    "uJM23JIgtpvC4aZ4wJ1y4ILYbiqHm+oB9y0HLojtmjhckwfcgw6cj+1Y76Vw" +
                                                    "Tedw0z3gHi6cDMvwFhb3SoM9I8RffZAVy/s7NnWzAb1HXQFXMx+12WPU036j" +
                                                    "ongsM2J15+qOjX2dfVuyRk37bUisjlnrBMHzwAyvp27sLPDQ/mOj8e6HF4f5" +
                                                    "8nOzSWpMVfu0TEeonAU1nd0P2HyvQfg5cO3kfHe6+TKFA6pbeCl81qfuhyjO" +
                                                    "m6RWMDapOzYI21S291zvcU4ooPhervjesVf8eZ+6iyh+xBSHvUQRirNJ1grX" +
                                                    "Ia74obFX/Bc+dZdRvGSScXEKR5Qk7Hytje2yYCZvgesBrvkDY6/5b3zqfovi" +
                                                    "VdjjSMoI1a2TVySY1vjM6BLX+tLYaM237Kwzg/ijj+p/QnEFckEyJZuSJu8M" +
                                                    "pvsMLJwH1ztc93fGVPfM4aQ57/npKjWlxCVlaJWaZkP83YfbP1H8BfONLigG" +
                                                    "Pt/DgncCz+BQ2OJm/R9zcvkPhzdTfP65hMF/6EPsIxTvwRS3iW3GkqvBmF0H" +
                                                    "OvAtUyhvy/SJQ+4qQoQqvbUPVaOAE8FEW/s+vNFUgwakwdZCyFShFk6jpSQO" +
                                                    "mprnoB4VDtvMP6GID8NJKMZl+6cHSxoCp+DQIk5s0dgQK3O2AK4nY6FmHx4z" +
                                                    "UUyFjOzE2W2sZ3AiKziRFSUnMs+HyLUoZucQ2VIkkXWcyLqSE1nkQwR3uaGF" +
                                                    "OUT6iyCyEAj0cSJ9JSeyzIfIChRLTTKpQBIoJsaQ0VbOaGvJGa32YbQGxS2F" +
                                                    "GRUTbMhI5ozkkjPa6MOIJa3OwoyKiTqcPmnOKF1yRrf5MEKtQ73Z06en2IR2" +
                                                    "gBM5UHIigz5E4ijuyCFSbEI7yokcLTkR2YeIgmIoh0jA0GrJTJZTnMipIojk" +
                                                    "nYaDsxnxYcPEXRk2MnuQzbQIxuZTwOI0Z3N6DNlkbc8sDvf4cNiPYjccZnR8" +
                                                    "Ek9vK1L/Z7n+z5ZS/8M++n8Zxb22/luK1P9lrv/LpdT/mI/++AAv9FVb//4i" +
                                                    "9IcDWegNrv8bY6h/OWtV7poNDp1RHzrfRnHSJJUWnSLYzIV8U26xsf6XyhuP" +
                                                    "+qj/OIrvmKTCEAW5WO35o99w3qPfT6C9T2Z6yofH0yjO/h885oP+/AOXsMcH" +
                                                    "Lp/EC+w3nLVm5521unVzWN3IPspZJRiSwWj4PLELXUBxHo77CV1Nsi5FumsJ" +
                                                    "p7mkNO6K2MJy10UfLj9F8WNw16BMlbgHjzQkCf49Gb5EbMr7RtX6rlI8N1pf" +
                                                    "PXX01l9ZnwZkvn2s6SLViZQsZ7+mzrqv1HSakBj1msxLa1Tq5yaJuH1lknL8" +
                                                    "hwqGLlnNLsMimNUMNOV32Y1eNUkZNMLb17RMJEScl9XW63f+vjPzxFnL+ZXz" +
                                                    "8RO+bGbf82ZeDKesL3oHxCdG12/c/e4ND7O3zBWiLOzahSjVXaTK+u6LgeLL" +
                                                    "5dmeaBmsynXzr054smZu5on2BBSNWUHS5DiSbPsvvrUs9j0tAAA=");
}
