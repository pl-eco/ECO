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
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVaD4wUVxl/u/f/OLi9498VDjiOAwvUXShSW8BWuB5wuMDJ" +
                                                    "XQlc0x5zs2/vBmZnpjOzxwJCC2kDIVqJpRUMnraC/QP9k6YNGqnBGFtqLVpi" +
                                                    "azRatDFarbXWpLUp2vp9b97O7M7ujLP2dpP5dub9+b3v977vfe/PzJm3SZWh" +
                                                    "k4WaKu8aklUzSjNmdLu8NGru0qgRXRdf2iPoBk10yoJh9EHagNj+VOP7V44M" +
                                                    "R8Kkup9MFBRFNQVTUhVjEzVUeYQm4qTRSe2SacowSSS+XRgRYmlTkmNxyTCX" +
                                                    "x8m4nKom6YhnVYiBCjFQIcZUiK10SkGl8VRJpzqxhqCYxh1kHwnFSbUmonom" +
                                                    "mZ0Pogm6kOIwPYwBINTi82YgxSpndNJmc7c4FxC+f2Hs6NdvjzxdQRr7SaOk" +
                                                    "9KI6IihhQiP9pCFFU4NUN1YmEjTRT5oUShO9VJcEWdrN9O4nzYY0pAhmWqd2" +
                                                    "J2FiWqM6a9PpuQYRuelp0VR1m15SonIi+1SVlIUh4DrF4WoxXI3pQLBeAsX0" +
                                                    "pCDSbJXKHZKSMMksdw2bY8cXoABUrUlRc1i1m6pUBEggzZbtZEEZivWauqQM" +
                                                    "QdEqNQ2tmGSaJyj2tSaIO4QhOmCSFne5HisLStWxjsAqJpnsLsaQwErTXFbK" +
                                                    "sc/bG1bcu0dZq4SZzgkqyqh/LVSa6aq0iSapThWRWhUbFsQfEKY8dyhMCBSe" +
                                                    "7CpslTn7pXc/f83M8xesMtOLlNk4uJ2K5oB4cnDCK62d82+oQDVqNdWQ0Ph5" +
                                                    "zJn79/Cc5RkNRt4UGxEzo9nM85ue33rXY/StMKnvJtWiKqdT4EdNoprSJJnq" +
                                                    "a6hCdcGkiW5SR5VEJ8vvJjVwH5cUaqVuTCYNanaTSpklVavsGbooCRDYRTVw" +
                                                    "LylJNXuvCeYwu89ohJDxcJFmuGqI9WP/JonHhtUUjQmioEiKGgPfpYIuDseo" +
                                                    "qMYMIaXJYDUjrSRldWfM0MWYqg/ZzyloIbZeACfKfCaKXqWNMV4G9Y/sDIWg" +
                                                    "a1vdA1uGMbFWlRNUHxCPpld1vfvEwEth29E5czAYtBDlLUSxhShvgYRCDHgS" +
                                                    "tmTZC3p7B4xbiGgN83tvW7ftUHsFOIq2sxK6qgKKtgML3nyXqHY6g7ubhTAR" +
                                                    "PKzloVsPRj94+CbLw2LekbhobXL+2M79m+9cFCbh/JCKdCCpHqv3YCC0A16H" +
                                                    "eygVw208+Ob7Tz6wV3UGVV6M5mO9sCaO1XZ3x+uqSBMQ/Rz4BW3CswPP7e0I" +
                                                    "k0oIABD0TAGcFOLJTHcbeWN2eTb+IZcqIJxU9ZQgY1Y2aNWbw7q600lhHjGB" +
                                                    "3TeBUcZlvXo292r2j7kTNZSTLA9CK7tYsPi6+vvnjz/7jYU3hHNDcWPO5NZL" +
                                                    "TWtgNzlO0qdTCum/O9Zz3/1vH7yVeQiUmFOsgQ6UnTDMwWTQrfdcuOPXl18/" +
                                                    "+cuw7VUhE+a79KAsiRnAmOe0AkFAhkCEtu+4RUmpCSkpCYMyRef8d+Pcxc/+" +
                                                    "7d6IZU0ZUrLOcM3/BnDSr1pF7nrp9n/NZDAhESchh7lTzOqAiQ7ySl0XdqEe" +
                                                    "mf2XZhx/QfgmxEiIS4a0m7JQQxgzwro+xky1gMmoK28xijatII8lTCu08fXc" +
                                                    "xtcXtTGKDldrEYaI5p/vsyTSpRRE6RE+jcT2Nl/eceLNx60B7J5zXIXpoaOH" +
                                                    "P47eezScMzHPKZgbc+tYkzNTebxF8WP4heD6CC+khglWcG7u5DNEmz1FaBo6" +
                                                    "ymw/tVgTq//85N4fPLL3oEWjOX9e6oJl1+Ov/edn0WO/f7FIyISRoArMp5aN" +
                                                    "jWB0r/NxhVUolgR3hV7uCr2BXSHMEMPQd3O9XYF5tmXZ0e/OuXjn6Jw/QPf0" +
                                                    "k1rJgDXmSn2oyNIlp84/zlx+69L4GU+wMFg5KBisS+vda77CJV3eSo1xaNDY" +
                                                    "3zJb/5AVLvxdeTU2kzMXfbhRHjzwxgfMxAWzSRHvdtXvj505Ma3zxrdYfSes" +
                                                    "Y+1ZmcIZGbrIqXvtY6n3wu3VPwmTmn4SEfkaf7MgpzF49kMnGNmFP+wD8vLz" +
                                                    "16jWgmy5PW21uodXTrPuCcVxa7jH0swe1hyiZUKEOc0XWel2JueiuNpyFbyd" +
                                                    "j4NBUgQ5A3FapsqQOczKfRbFcss9P2eSCrAo3q7TMgUOh8+TTR4/kREsdlWF" +
                                                    "YijO5lkrEEmN2hsNyMwUMf2MvPXHeuY0TpcffvT0WfOVhcusIb/A203cFV84" +
                                                    "8NdpfTcObyth1THLZXs35KPrz7y4Zp74tTCpsC1XsGPJr7Q83171OoUtltKX" +
                                                    "Z7WZ1rBYV2yE58YT6pM3hAJmuyoR7WCZDfp2VvGpsyulmWyy2/29qc+seHj0" +
                                                    "dTZ3Z0gxN6gZVFWZCgprZo2PCmyNfnNhuLOQWthTM7uP27GvAUtMgauOx746" +
                                                    "d+zj/qwX92dYadRoujQi4D6XVKQWLbLjc0Tzaquet1Xv0RZjYzK4xQHgxnG4" +
                                                    "cR5wexy4awPANXC4Bg+4fQ7ckgBw4znceA+4/Tbc4iB9N4HDTfCAu9uBC9J3" +
                                                    "jRyu0QPuoAMXpO8iHC7iAXfYgQvSd00crskD7is23LVB+q6ZwzV7wB1x4IL0" +
                                                    "3UQON9ED7j4HLkjfTeJwkzzgHnDggvTdZA432QPuuA23JEjfTeFwUzzgTjhw" +
                                                    "QfpuKoeb6gH3LQcuSN+1cLgWD7iHHDifvmO1l8I1ncNN94A7VTwYVuAtTO7V" +
                                                    "Bjv9w6c+iIqV/V2bNrIGvVtdAVcrb7XVo9XTfq2ieDTbYm33zV0b+rr7tua0" +
                                                    "mvFbkFgVc+YJgvuBGV7naWwvcPLA0dHExlOLw3z6udEkdaaqfVqmI1TOgZrO" +
                                                    "7gdsvlch/By4dnG+u9x8mcIB1S0+FZ7zyfshirMmqReMTerO9cJ2la0913ns" +
                                                    "E4oovo8rvm/sFX/eJ+8Cih8xxWEtUYLibJC1w3WIK35o7BX/hU/eJRQvmWRc" +
                                                    "gsIWJQUrX2thuyxYl7fB9SDX/MGx1/w3Pnm/RfEqrHEkZYTq1s4rEkxrPDO6" +
                                                    "yLW+ODZa8yU7q8wg/uij+p9QXIZYkErLpqTJu4LpPgMT58H1Dtf9nTHVPbs5" +
                                                    "aS04P12lppWEpAytUjOsib/7cPsnir9gvNEFxcDzPUx4J/AIDoUtbtb/mJMr" +
                                                    "PBzeTPH8cwmD/9CH2Eco3oMhbhPbjClXgjG7BnTgS6ZQwZLpE7vcFYQIVXtr" +
                                                    "H6pFATuCibb2fXijqQYNSIPNhRCpQm2cRltZDDS1wEA9Kmy2mX1CER+Gk1CM" +
                                                    "y7VPD6Y0BQ7BoUWc2KKxIVbhLAFcJ2OhVh8eM1FMhYjs+NkWVjM4kRWcyIqy" +
                                                    "E5nnQ+RqFLPziGwtkchaTmRt2Yks8iGCq9zQwjwi/SUQWQgE+jiRvrITWeZD" +
                                                    "ZAWKpSaZVCQIlOJjyGgbZ7St7Ixu9mG0GsVNxRmV4mzISOaM5LIz2uDDiAWt" +
                                                    "7uKMSvE6HD4ZzihTdkZbfBih1qHe3OHTU2pAu5sTubvsRAZ9iCRQ3JZHpNSA" +
                                                    "doQTOVJ2IrIPEQXFUB6RgK7Vlh0sJziREyUQKdgNB2cz4sOGiTuybGR2kM20" +
                                                    "CMbmU8DiNGdzegzZ5CzPLA53+XA4gGIPbGZ0PImnW0rU/xzX/1w59T/so/+X" +
                                                    "Udxj67+1RP1f5vq/XE79j/rojwd4oa/a+veXoD9syEJvcP3fGEP9K1mpStdo" +
                                                    "cOiM+tD5NorjJqm26JTAZi7Em0qLjfVfLms84qP+Yyi+Y5IqQxTkUrXnR7/h" +
                                                    "gqPfT6C9T2R62ofHMyge/z94zAf9+QcuYY8PXD6JFdgz7LVmF+y1NurmsLqB" +
                                                    "fZSzSjAkg9HwObELnUdxFrb7SV1NsSolmmsJp7mkPOaK2MIy1wUfLj9F8WMw" +
                                                    "16BMlYQHjwwECf49Gb5EbCn4+tT6YlJ8YrSxduroLb+yPg3IftVYFye1ybQs" +
                                                    "576mzrmv1nSalBj1uuxLa1Tq5yaJuG1lkkr8QwVDF61il2ASzCkGmvK73EKv" +
                                                    "mqQCCuHta1rWEyLOy2rr9Tt/35k9cdbynvI+fsKXzexL3eyL4bT1re6A+OTo" +
                                                    "ug173r3uFHvLXCXKwu7diFIbJzXWd18MFF8uz/ZEy2JVr51/ZcJTdXOzJ9oT" +
                                                    "UDTnOEmLY0iy/b+5C7AAFy0AAA==");
}
