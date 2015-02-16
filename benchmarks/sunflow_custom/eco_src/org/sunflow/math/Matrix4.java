package org.sunflow.math;
final public class Matrix4 {
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
    final public static Matrix4 ZERO = new Matrix4();
    final public static Matrix4 IDENTITY = Matrix4.scale(1);
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
    final public float[] asRowMajor() { return new float[] { m00, m01, m02,
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
    final public float[] asColMajor() { return new float[] { m00, m10, m20,
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
    final public float determinant() { float A0 = m00 * m11 - m01 * m10;
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
    final public Matrix4 inverse() { float A0 = m00 * m11 - m01 * m10;
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
    final public Matrix4 multiply(Matrix4 m) { float rm00 = m00 * m.m00 +
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
    final public BoundingBox transform(BoundingBox b) { if (b.isEmpty()) return new BoundingBox(
                                                                           );
                                                        BoundingBox rb =
                                                          new BoundingBox(
                                                          this.
                                                            transformP(
                                                              b.
                                                                getMinimum()));
                                                        rb.include(
                                                             this.
                                                               transformP(
                                                                 b.
                                                                   getMaximum()));
                                                        for (int i =
                                                               1;
                                                             i <
                                                               7;
                                                             i++)
                                                            rb.
                                                              include(
                                                                this.
                                                                  transformP(
                                                                    b.
                                                                      getCorner(
                                                                        i)));
                                                        return rb;
    }
    final public Vector3 transformV(Vector3 v) { Vector3 rv = new Vector3(
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
    final public Vector3 transformTransposeV(Vector3 v) { Vector3 rv =
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
    final public Point3 transformP(Point3 p) { Point3 rp = new Point3(
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
    final public float transformVX(float x, float y, float z) { return m00 *
                                                                  x +
                                                                  m01 *
                                                                  y +
                                                                  m02 *
                                                                  z;
    }
    final public float transformVY(float x, float y, float z) { return m10 *
                                                                  x +
                                                                  m11 *
                                                                  y +
                                                                  m12 *
                                                                  z;
    }
    final public float transformVZ(float x, float y, float z) { return m20 *
                                                                  x +
                                                                  m21 *
                                                                  y +
                                                                  m22 *
                                                                  z;
    }
    final public float transformTransposeVX(float x, float y, float z) {
        return m00 *
          x +
          m10 *
          y +
          m20 *
          z;
    }
    final public float transformTransposeVY(float x, float y, float z) {
        return m01 *
          x +
          m11 *
          y +
          m21 *
          z;
    }
    final public float transformTransposeVZ(float x, float y, float z) {
        return m02 *
          x +
          m12 *
          y +
          m22 *
          z;
    }
    final public float transformPX(float x, float y, float z) { return m00 *
                                                                  x +
                                                                  m01 *
                                                                  y +
                                                                  m02 *
                                                                  z +
                                                                  m03;
    }
    final public float transformPY(float x, float y, float z) { return m10 *
                                                                  x +
                                                                  m11 *
                                                                  y +
                                                                  m12 *
                                                                  z +
                                                                  m13;
    }
    final public float transformPZ(float x, float y, float z) { return m20 *
                                                                  x +
                                                                  m21 *
                                                                  y +
                                                                  m22 *
                                                                  z +
                                                                  m23;
    }
    final public static Matrix4 translation(float x, float y, float z) {
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
    final public static Matrix4 rotateX(float theta) { Matrix4 m =
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
    final public static Matrix4 rotateY(float theta) { Matrix4 m =
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
    final public static Matrix4 rotateZ(float theta) { Matrix4 m =
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
    final public static Matrix4 rotate(float x, float y, float z,
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
    final public static Matrix4 scale(float s) { Matrix4 m = new Matrix4(
                                                   );
                                                 m.m00 = (m.m11 =
                                                            (m.
                                                               m22 =
                                                               s));
                                                 m.m33 = 1;
                                                 return m; }
    final public static Matrix4 scale(float sx, float sy, float sz) {
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
    final public static Matrix4 fromBasis(OrthoNormalBasis basis) {
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
    final public static Matrix4 blend(Matrix4 m0, Matrix4 m1, float t) {
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
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1170479814000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVbD3AU1Rl/t/lH/kBCCOFfhBBBBMIdAfkbrIbwL3CEmIQI" +
                                                   "QQybvXfJwt7uursX\nLpGiVAdQRwW1U+0ggmNFASvWKrZFlPqnKtVBZ9QpU6" +
                                                   "mKo05btdappdPO2O+9fXd7t7e3hLskM/du\nb/e97/d+3/e9977vvc3Rr1CO" +
                                                   "rqEKQfcavSrWvfUtTbym40C9xOt6K9zqEF7LyW86tEpWOOTxI04M\nGKjYL+" +
                                                   "i+AG/wPjHga1hSG9HQdFWRerskxfDiiOHdLM1h8lb65yQJvH7/8dIdj2VP4F" +
                                                   "COHxXzsqwY\nvCEq8lIJh3QDlfg38z28L2yIks8v6katHw3FcjhUr8i6wcuG" +
                                                   "fhPajrL8KFcViEwDTfRHwX0A7lN5\njQ/5KLyvicKChBEaNnhRxoG6GBy0rE" +
                                                   "5sCd1m7ZqTa4OQIeRhG9ChPQDWlTHWJtskqmrWE21ztx14\nMgsVt6NiUW4h" +
                                                   "wgRgYgBeOyoK4VAn1vS6QAAH2tFwGeNAC9ZEXhL7KGo7KtXFLpk3whrWm7Gu" +
                                                   "SD2k\nYqkeVrFGMaM3/ahIIJy0sGAoWkxHQRFLgeivnKDEdwHtcou2SXcZuQ" +
                                                   "8EC0TomBbkBRxtkr1FlMHi\nE+wtYhwnrYIK0DQvhI1uJQaVLfNwA5WatpR4" +
                                                   "ucvXYmii3AVVc5QwoBhobEqhRNcqL2zhu3CHgUbb\n6zWZj6BWPlUEaWKgkf" +
                                                   "ZqVBJYaazNSnH2mV7+/e4n9p28lvp2dgALEul/ATQab2vUjINYw7KAzYYX\n" +
                                                   "wt4HGtaHKziEoPJIW2WzTt3k42v9X740wawzzqHOms7NWDA6hMa9E5pvXq6g" +
                                                   "LNKNIaqii8T4Cczp\ncGhiT2ojKoza8phE8tAbffhy8+vrbz2M/8ahggaUKy" +
                                                   "hSOAR+NFxQQqooYW05lrHGGzjQgPKxHKin\nzxtQHlz7weXNu2uCQR0bDShb" +
                                                   "ordyFfobVBQEEURF+XAtykEleq3yRje9jqgIoaHwQaXwyUPmH/02\n0HSvTw" +
                                                   "/LQUnZ6tM1wadoXbHfIRDgW82Dj0Su8hKnUQ203NethLCPF3hZlBVflwjDVF" +
                                                   "BmBHAP+e6/\nqAjpWelWj4dMdfYhK4G3r1CkANY6hEPn39q2dNUdu013IC7M" +
                                                   "OIEpAMHLELwEwcsQkMdDBZcRJNMS\noMctMCJh7iqa2rJx5abdVVngAurWbF" +
                                                   "BCFlStgt4z+KWCUm8N2wY6wwngO6Mf3bDLe+HQNabv+FLP\nro6tC888dfrA" +
                                                   "d0XTOMQ5T32EFky+BURME5kvY1PaJPtgcZL/9Z2rn/3g9EdXWsPGQJOSRnNy" +
                                                   "SzIa\nq+wG0BQBB2B+s8Q/NqY463rUtpdD2TDEYVqj/YcZY7wdI2FU1kZnOM" +
                                                   "Ilz48Kg4oW4iXyKDotFRjd\nmrLVukM9o4RejwDjFEb9diLzW/pNno5USVlu" +
                                                   "ehKxto0FnUEv3JY788MTha9RtUQn2+K45awFG+bQ\nHW45S6uGMdz/6MGm+3" +
                                                   "/61a4N1FNMV/EYsMaFOyVRiECTK6wmMGYlmDeIISetlUNKQAyKfKeEicf9\n" +
                                                   "r3hyzXN/v6fENI0Ed6KWrb64AOv+mMXo1tM3/ns8FeMRyJph0bCqmWxGWJLr" +
                                                   "NI3vJf2I7Hjvsof+\nwD8MUxpMI7rYh+nMgCgzRPXoo3qfRkuv7VkNKapAdn" +
                                                   "UK13dYoTuEbYe7qsI3vfkb2utCPn6pjzfD\nal6tNS1PisuJdkfZR+8KXu+G" +
                                                   "ele93HhDifTyf0FiO0gUYGXU12gwWUQSjMhq5+SdPfVK+aZ3sxC3\nDBVICh" +
                                                   "9YxlP/R/ngeFjvhnkmol5zLfWtkq1DSEkpI6qEsUwB9EdFslfOZ14539ErSX" +
                                                   "GFTaUlVGIJ\nEBwdH+JpYgiWih46as7vrHrxjbWP7DJnmqkucVx8qw7hlr98" +
                                                   "vCXr3lOdZjv7cmmrvHf8Y58/e765\nzPRKM6a4PGlZj29jxhWUWbFKLDTRDY" +
                                                   "HWfnX6xKPbm8+xHpUmro5LIYL8ovcVPGXR3Z84TO8wWhWe\nDpW6gSlozxe5" +
                                                   "ePhyUiygj2aRYqFp9jlpe0cL846WfnsHRyVyZDqL8w46hIlSn9yzZETzgg23" +
                                                   "0VUk\nH2JSXm+M6oxmAuTKA6qenNppYsI6hCkbj//j1Ek8hQ6lIaIOgXSd1u" +
                                                   "UQn8W1+ZY/jFd/GNxPV4Ls\nTl6n6EX2wDY5bk0IR6kGhqn0q05VVYhETDoL" +
                                                   "aubOAfalwJ6kM14x4PUrAi81LDl4qvC9veG5K01f\nGhpXoWHJtmMri4YcvG" +
                                                   "snR4CYGvLj4jb2O6+H1xotHyNfLQZqGqCYZuH82dU1C2bMmm8gj7l6X62a\n" +
                                                   "rrHYQHmdiiJhXqaMG12ccCMpVllO6O+vE0bifpH10GXeWEYsZS3uHZ3VT/jf" +
                                                   "WvMwHYQpYxOHKcUm\np+/k2v0X3jbOUTlWkEBaT4wkx3ngbVbb+R/0DM899k" +
                                                   "iIQ3ntqERgiWUbL4XJUtwO/qRHs01IPhOe\nJ+Y0ZgBfGwuCKuxzWhysPTyx" +
                                                   "JiC4JrWpa9sikiKi7XL45LPRnW8f3R5EL0yxk2g5JRY/5Kma2MOT\nZBNlhW" +
                                                   "bOjE1PpZbJuy9m8s7kzhSwzhSk6AxNC0SKWeOEqaaBWcgwC1NgGhbmLCfMcB" +
                                                   "qYRQyzKAVm\nr4U52wmzLw3MoQxzaArM7THMGkd73pIG5jCGOSwF5m0WpqM9" +
                                                   "b08Ds5hhFqfA3G1hOtrzjjQwSxhm\nSQrMuy1MR3vekwbmcIY5PAXmfTHMWY" +
                                                   "72vD8NzFKGWZoC82cWpqM9H0wDcwTDHJECc5+F6WjPh9PA\nLGOYZSkwD1qY" +
                                                   "jvZ8NA3MkQxzZArMx2OYsx3teSgNzHKGWZ4C84iF6WjPo2lgjmKYo1JgHrMw" +
                                                   "He35\nTBqYoxnm6BSYz1mYjvZ8vv+YFGIOfMYxzHEpMH+bvLBmkXxBlHm6WT" +
                                                   "gVsnSdbuvCIpvdvrR5jf5j\nqONBY+Ji6kZFpvtuokDi2kg03yQxhFfDQZJN" +
                                                   "k42QSO8nU85W/rGk/rSZd3cbaHJctMFq+hrkHohS\nSbC0gpcDEtbMNLzCEf" +
                                                   "B6jVdVrJ35+PON907/4nUS0Ko2tf3uEtW2CD4VTG0VKdT2poPayPVJqjNS\n" +
                                                   "vAT6GtKwZGlja0PrelrvFC1fs/XuLZfeRZJROAslPkhFJIe8LNVOMM0fd637" +
                                                   "tmgn/+pGjgXH9ZAm\nGIo6Q8I9WIoTNY5IStjCW02TDSu+vPPJI8eNd6cvNL" +
                                                   "OHaaljY3vDaQsP9E1Y+PRdaWzcTbBxs4se\n3jPuuqxu8Q2atbBwNWlbP7FR" +
                                                   "bWKQWgD9CWtya0KoWhlzDbrATYZPL3ONXrtrWIZ1t5hzrvKpy7PP\nSPGRgQ" +
                                                   "p4vVnZuprfzI5A1jgnfXPpw1ZSfGGg6wYqJauZVVNdM2/G7HmQCFgdudLr9U" +
                                                   "6dolda2rN8\n+9wlJf1/Stb1dqbr7QOr6+9cnv2LFF9TXdcr0kV1Pc/S9YUB" +
                                                   "1fXcOF1HO+Km628y0fUk+Oxmut49\noLr2ZLs8yyU3fzBQYQAbWAvBmiObm1" +
                                                   "QxWh6UCa0q+BxktA4OLK0Sl2dkN8pTCOmpKPdgTcfxs7/F\nrCgTZtXweYcx" +
                                                   "eydzZh4zo47vKGUy3oVlFSnGwCIXCkuGqEq9zjTHZkKTRDDfMJrfDCzNUTCy" +
                                                   "kg69\nFithOSDKXYuVCOXodeFP2U0li6jGyzo5jOmXXEsz0zLRDPTGw5mKMb" +
                                                   "8HVDPJx4FtmOz0z6bMf+Si\nlcWkWADTZ0wrbRcVaqnk0naJbSqZBwRYOupJ" +
                                                   "SkcHWSUuu5CeZlI0GGhETCWt5EJVdHwpulmZ4Xzh\nqWS6qRxw3YxK6n2TIs" +
                                                   "qGqZobXVTTSYr18d7SdDGZlkLaM1HI1dD9mUwhMzNXSJaVAthOSTyyC3+C\n" +
                                                   "54GMr9AaLetoS4vl5kxZLmIsFw0qyz4XlttIEU5gud7GsidTlisYyxWDynKn" +
                                                   "C0uyo+fZkcCy3cby\nJ5mwXAbsWhnL1kFleZ8LywdIcbeByhwmM7vrXnQ/8W" +
                                                   "J0NzG6mwaV7n4XugdI8ZAzXbsP/zxTuhKj\nKw0q3cMudI+S4hfOdO3O/Him" +
                                                   "QzbC6EYGle7zLnRfIMUz8UO2ye7Dv8qU5e2M5e2DyvL3LixfJcWL\nCSztrn" +
                                                   "syU5Z7GMs9g8ryHReWZ0jxZgJLu8e6bbW5sRwdHaD7GMt9/WSZtCXYf6pnXa" +
                                                   "j+mRTvR6lK\n1tZZUvL1QSaMrwKmRxjjIwPEmAWOFs8vXXj+lRTnIY/WyP4g" +
                                                   "XufM8bNMOZ5gHE8MFsfvXTheIMW3\nMY7rnTn+M1OObzOObw8SR85lm4cj2z" +
                                                   "yeH2Ic2x05cunu9FCOjdCZTxnHTweIYzatlW0bnRblUhfK\nZaQYaqBck7Iz" +
                                                   "42GZMAZELttkbH4PhlUrXSiS/R9unIFydIGXUjCsyIRhPTBjx65c0rFrmgxT" +
                                                   "z7ic\ny14PR446uanuXNPdzqFc64AjeyuXS/FWbrrWpL8hoZ6YlFCv0YxupZ" +
                                                   "G+RbyY10WdUnV5qY+7lhTz\nDJQf1JQQbeKsivkZLrTcbKaK2QNr9qTexh3J" +
                                                   "mU7gd2FP9nG45eAEnRKWA87MV/T7DTOYDtmOPznc\nHJ30rzbmv4cI/rM33/" +
                                                   "C9//3/mK8IRv+Fo9CPhgTDkhT/jlXcda6q4aBIlVVovnFFDy+4DQYqsXuB\n" +
                                                   "gbLJF+kc125WuxFCi7hq0FN2FV+JN1AWVCKXnWrUx0qsc1/z3bHEM0rC9PKE" +
                                                   "40L630zRI72w+f9M\nHcK6pzZURu5q3UPPCXMEie/rI2IK/CjPfOmdSiXHgh" +
                                                   "NTSovKOoOOPd124pcLosee9KXosjhHSvDF\nWeZTFyNqaILzm+ZLQ6pB3w3v" +
                                                   "e2HUrxcd2n+OnkWr/welKmnshDYAAA==");
}
