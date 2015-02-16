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
    public static final long jlc$SourceLastModified$jl7 = 1170479814000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVaDZAURxXu3fs/Dm7v+LvAAcdxYIC4y4/EBDAJXI5weMDJ" +
                                                    "XTBcKjnmZnvvBmZnJjOzxwJCEiopKEojZQiChaeJifnhJ6lUKLQkFpZlQsSg" +
                                                    "oUwsLQ2asjQaEWNVYipo4ns9vTO7szvjrLndqnk709P99fvee/26e2aOXyYV" +
                                                    "hk7ma6q8fVBWzShNm9Et8pKouV2jRnRN15JuQTdovF0WDKMXyvrF1ufq3796" +
                                                    "YCgSJpV9ZLygKKopmJKqGBuoocrDNN5F6p3SDpkmDZNEurYIw0IsZUpyrEsy" +
                                                    "zGVdZExWU5O0dWVUiIEKMVAhxlSIrXBqQaOxVEkl27GFoJjGPWQ3CXWRSk1E" +
                                                    "9UwyMxdEE3QhyWG6GQNAqMbrjUCKNU7rpMXmbnHOI/zI/NjBr98deb6M1PeR" +
                                                    "eknpQXVEUMKETvpIXZImB6hurIjHabyPNCiUxnuoLgmytIPp3UcaDWlQEcyU" +
                                                    "Tm0jYWFKozrr07FcnYjc9JRoqrpNLyFROZ65qkjIwiBwneRwtRiuwnIgWCuB" +
                                                    "YnpCEGmmSflWSYmbZIa7hc2x7fNQAZpWJak5pNpdlSsCFJBGy3eyoAzGekxd" +
                                                    "UgahaoWagl5MMsUTFG2tCeJWYZD2m6TJXa/bugW1apghsIlJJrqrMSTw0hSX" +
                                                    "l7L8c3nd8od2KquVMNM5TkUZ9a+GRtNdjTbQBNWpIlKrYd28rkPCpBf3hQmB" +
                                                    "yhNdla06p7/07i3XTT97zqoztUCd9QNbqGj2i48PjHutuX3ujWWoRrWmGhI6" +
                                                    "P4c5C/9ufmdZWoORN8lGxJvRzM2zG17adN8z9J0wqe0klaIqp5IQRw2imtQk" +
                                                    "meq3UYXqgknjnaSGKvF2dr+TVMF5l6RQq3R9ImFQs5OUy6yoUmXXYKIEQKCJ" +
                                                    "quBcUhJq5lwTzCF2ntYIIWPhII1wVBHrx/5N8sXY7QaEe0wQBUVS1BgELxV0" +
                                                    "cShGRbV/AKw7lBT0rUZMTBmmmowZKSUhq9tihi7GVH3Qvk5CZ7G1AsRT+jNR" +
                                                    "DDCtdNBpZBXZFgqBwZvdw12GkbJaleNU7xcPplZ2vHuy/3zYDn9uD3Aj9BDl" +
                                                    "PUSxhyjvgYRCDHgC9mR5EXywFUYz5Lm6uT13rdm8r7UMwkfbVg4GLIOqrcCH" +
                                                    "d98hqu3OkO9kiU2EuGt67M690Q+evNmKu5h3fi7Ympw9vO3+jfcuCJNwbqJF" +
                                                    "OlBUi827MT3aabDNPcAK4dbvffv9Zw/tUp2hlpO5eQbIb4kjuNVteF0VaRxy" +
                                                    "ogM/r0U41f/irrYwKYe0AKnQFCB0IctMd/eRM5KXZbIicqkAwglVTwoy3sqk" +
                                                    "slpzSFe3OSUsIsax8wZwyphMrM/ksc7+8e54DeUEK4LQyy4WLOuu+v7ZI6e+" +
                                                    "Mf/GcHaCrs+a8nqoaQ33BidIenVKofx3h7sffuTy3jtZhECNWYU6aEPZDoMf" +
                                                    "XAZmffDcPb++9ObjvwzbURUyYRZMDciSmAaMOU4vkBpkSE/o+7bblaQalxKS" +
                                                    "MCBTDM5/189eeOpvD0Usb8pQkgmG6/43gFN+zUpy3/m7/zWdwYREnJoc5k41" +
                                                    "ywDjHeQVui5sRz3S91+cduRl4ZuQOSFbGdIOyhIQYcwIM32MuWoek1HXvYUo" +
                                                    "WrS8e6xgSr6Pb+A+vqGgj1G0uXqLMER0/1yfhZIuJSF3D/PJJbar8dLWo2+f" +
                                                    "sAaweyZyVab7Du7/OPrQwXDWdD0rb8bMbmNN2UzlsRbFj+EXguMjPJAaFlgp" +
                                                    "u7Gdzxst9sShaRgoM/3UYl2s+vOzu37w1K69Fo3G3NmqAxZjJ974z8+ih3//" +
                                                    "SoGUCSNBFVhMLR0dwehe7xMKK1EsDh4KPTwUegKHQpghhsF2s71DgUW25dmR" +
                                                    "7866cO/IrD+AefpItWTAynOFPlhgQZPV5h/HL71zcey0kywNlg8IBjNprXsl" +
                                                    "mL/Qy1m/MQ51GvtbausfstKFfyivwm6y5qIP18sDe976gLk4bzYpEN2u9n2x" +
                                                    "40entN/0DmvvpHVsPSOdPyODiZy2i55JvhdurfxJmFT1kYjIV/4bBTmFybMP" +
                                                    "jGBktgOwO8i5n7tytZZpy+xpq9k9vLK6dU8oTljDOdZm/rDmEC0dIixovsBq" +
                                                    "tzI5G8W1Vqjg6VwcDJIiyGnI0zJVBs0hVu+zKJZZ4fk5k5SBR/F0jZbOCzi8" +
                                                    "nmjy/ImMYAmsKhRTceaetQKR1Ki9/YCb6QKun5az/ljLgsYx+f6nj502X5u/" +
                                                    "1Bry87zDxN3w5T1/ndJ709DmIlYdM1y+d0M+vfb4K7fNEb8WJmW25/L2MbmN" +
                                                    "luX6q1ansPFSenO8Nt0aFmsKjfDsfEJ97g2igNmuQkQ/WG4D284oPHV2JDWT" +
                                                    "TXY7vjf5heVPjrzJ5u40KRQGVQOqKlNBYd3c5qMCW7nfmp/uLKQmdtXIzrvs" +
                                                    "3FeHNSbBUcNzX4079/F41gvHM6w0qjRdGhZw90vKkgsW2Pk5onn1Vcv7qvXo" +
                                                    "i7ExGdzCAHBjONwYD7idDtyiAHB1HK7OA263A7c4ANxYDjfWA+5+G25hENuN" +
                                                    "43DjPOAecOCC2K6ew9V7wO114ILYLsLhIh5w+x24ILZr4HANHnBfseEWBbFd" +
                                                    "I4dr9IA74MAFsd14DjfeA+5hBy6I7SZwuAkecIccuCC2m8jhJnrAHbHhFgex" +
                                                    "3SQON8kD7qgDF8R2kzncZA+4bzlwQWzXxOGaPOAec+B8bMdaL4FjKoeb6gH3" +
                                                    "ROFkWIanMLlXGuyZIF71QlYs7+vYsJ516N3rcjiaea/NHr0e8+sVxdOZHqs7" +
                                                    "b+1Y19vZuymr17TfgsRqmDVPENwPTPN6ysb2Ao/vOTgSX//EwjCffm4ySY2p" +
                                                    "ap+W6TCVs6CmsvN+m+81CD8Lju2c73Y3X6ZwQHULT4VnfO79EMVpk9QKxgZ1" +
                                                    "21phi8rWnms89gkFFN/NFd89+oq/5HPvHIofMcVhLVGE4myQtcKxjyu+b/QV" +
                                                    "/4XPvYsozptkTJzCFiUJK19rYbs0mMlb4HiUa/7o6Gv+G597v0XxOqxxJGWY" +
                                                    "6tbOKxJMa3xmdIFrfWF0tOZLdtaYQfzRR/U/obgEuSCZkk1Jk7cH030aFs6B" +
                                                    "4wrX/cqo6p7ZnDTnPT9dqaaUuKQMrlTTrIu/+3D7J4q/YL7RBcXA53tYcCXw" +
                                                    "CA6FLW7W/6iTy384vJHi88/FDP5DH2IfoXgPhrhNbCOWXA3G7DrQgS+ZQnlL" +
                                                    "pk8cclcRIlTprX2oGgXsCMbb2vfiiaYaNCANNhdCpgq1cBotJXHQ5DwHdauw" +
                                                    "2Wb+CUV8GE5AMSbbP91Y0hA4BYcWcGILRodYmbMEcD0ZCzX78JiOYjJkZCfO" +
                                                    "7mAtgxNZzoksLzmROT5ErkUxM4fIpiKJrOZEVpecyAIfIrjKDc3PIdJXBJH5" +
                                                    "QKCXE+ktOZGlPkSWo1hikgkFkkAxMYaMNnNGm0vO6FYfRqtQ3FyYUTHBhoxk" +
                                                    "zkguOaN1PoxY0uoszKiYqMPhk+aM0iVndIcPI9Q61JM9fLqLTWgPcCIPlJzI" +
                                                    "gA+ROIq7cogUm9AOcCIHSk5E9iGioBjMIRIwtFoyg+UoJ3K0CCJ5u+HgbIZ9" +
                                                    "2DBxT4aNzB5kMy2CsfkUsDjG2RwbRTZZyzOLw30+HPag2AmbGR2fxNM7itT/" +
                                                    "DNf/TCn13++j/5dRPGjrv6lI/V/l+r9aSv0P+uiPD/BCX7X17ytCf9iQhd7i" +
                                                    "+r81ivqXs1rlrtHg0BnxofNtFEdMUmnRKYLNbMg35RYb679U3njKR/1nUHzH" +
                                                    "JBWGKMjFas8f/YbzHv1+Au19MtPzPjxeQHHi/+AxF/TnH7iEPT5w+SReYNew" +
                                                    "15qZt9dar5tD6jr2Uc5KwZAMRsPniV3oLIrTsN1P6GqSNSnSXYs5zcWlcVfE" +
                                                    "Fpa7zvlw+SmKH4O7BmSqxD14pCFJ8O/J8CViU943qdZ3lOLJkfrqySO3/8r6" +
                                                    "NCDzrWNNF6lOpGQ5+zV11nmlptOExKjXZF5ao1I/N0nE7SuTlOMfKhi6YFW7" +
                                                    "CJNgVjXQlJ9lV3rdJGVQCU/f0DKREHFeVluv3/n7zswTZy3nKufjJ3zZzL7f" +
                                                    "zbwYTllf8PaLz46sWbfz3eufYG+ZK0RZ2LEDUaq7SJX13RcDxZfLMz3RMliV" +
                                                    "q+deHfdczezME+1xKBqzgqTJcSTZ8l9JeHFsLS0AAA==");
}
