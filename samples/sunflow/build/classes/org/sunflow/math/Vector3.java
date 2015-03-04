package org.sunflow.math;
public final class Vector3 {
    private static final float[] COS_THETA = new float[256];
    private static final float[] SIN_THETA = new float[256];
    private static final float[] COS_PHI = new float[256];
    private static final float[] SIN_PHI = new float[256];
    public float x;
    public float y;
    public float z;
    static { for (int i = 0; i < 256; i++) { double angle = i * Math.PI /
                                               256.0;
                                             COS_THETA[i] = (float) Math.
                                                              cos(
                                                                angle);
                                             SIN_THETA[i] = (float) Math.
                                                              sin(
                                                                angle);
                                             COS_PHI[i] = (float) Math.cos(
                                                                         2 *
                                                                           angle);
                                             SIN_PHI[i] = (float) Math.sin(
                                                                         2 *
                                                                           angle);
             } }
    public Vector3() { super(); }
    public Vector3(float x, float y, float z) { super();
                                                this.x = x;
                                                this.y = y;
                                                this.z = z; }
    public Vector3(Vector3 v) { super();
                                x = v.x;
                                y = v.y;
                                z = v.z; }
    public static final Vector3 decode(short n, Vector3 dest) { int t =
                                                                  (n &
                                                                     65280) >>>
                                                                  8;
                                                                int p =
                                                                  n &
                                                                  255;
                                                                dest.
                                                                  x =
                                                                  SIN_THETA[t] *
                                                                    COS_PHI[p];
                                                                dest.
                                                                  y =
                                                                  SIN_THETA[t] *
                                                                    SIN_PHI[p];
                                                                dest.
                                                                  z =
                                                                  COS_THETA[t];
                                                                return dest;
    }
    public static final Vector3 decode(short n) { return decode(n,
                                                                new Vector3(
                                                                  ));
    }
    public final short encode() { int theta = (int) (Math.acos(z) *
                                                       (256.0 /
                                                          Math.
                                                            PI));
                                  if (theta > 255) theta = 255;
                                  int phi = (int) (Math.atan2(y, x) *
                                                     (128.0 /
                                                        Math.
                                                          PI));
                                  if (phi < 0) phi += 256; else if (phi >
                                                                      255)
                                                                    phi =
                                                                      255;
                                  return (short) ((theta & 255) <<
                                                    8 |
                                                    phi &
                                                    255); }
    public float get(int i) { switch (i) { case 0: return x; case 1:
                                               return y;
                                           default:
                                               return z; } }
    public final float length() { return (float) Math.sqrt(x * x +
                                                             y *
                                                             y +
                                                             z *
                                                             z); }
    public final float lengthSquared() { return x * x + y * y + z *
                                           z; }
    public final Vector3 negate() { x = -x;
                                    y = -y;
                                    z = -z;
                                    return this; }
    public final Vector3 negate(Vector3 dest) { dest.x = -x;
                                                dest.y = -y;
                                                dest.z = -z;
                                                return dest; }
    public final Vector3 mul(float s) { x *= s;
                                        y *= s;
                                        z *= s;
                                        return this; }
    public final Vector3 mul(float s, Vector3 dest) { dest.x = x *
                                                                 s;
                                                      dest.y = y *
                                                                 s;
                                                      dest.z = z *
                                                                 s;
                                                      return dest;
    }
    public final Vector3 div(float d) { x /= d;
                                        y /= d;
                                        z /= d;
                                        return this; }
    public final Vector3 div(float d, Vector3 dest) { dest.x = x /
                                                                 d;
                                                      dest.y = y /
                                                                 d;
                                                      dest.z = z /
                                                                 d;
                                                      return dest;
    }
    public final float normalizeLength() { float n = (float) Math.
                                                       sqrt(
                                                         x *
                                                           x +
                                                           y *
                                                           y +
                                                           z *
                                                           z);
                                           float in = 1.0F / n;
                                           x *= in;
                                           y *= in;
                                           z *= in;
                                           return n; }
    public final Vector3 normalize() { float in = 1.0F / (float) Math.
                                                           sqrt(
                                                             x *
                                                               x +
                                                               y *
                                                               y +
                                                               z *
                                                               z);
                                       x *= in;
                                       y *= in;
                                       z *= in;
                                       return this; }
    public final Vector3 normalize(Vector3 dest) { float in = 1.0F /
                                                     (float)
                                                       Math.
                                                       sqrt(
                                                         x *
                                                           x +
                                                           y *
                                                           y +
                                                           z *
                                                           z);
                                                   dest.x = x * in;
                                                   dest.y = y * in;
                                                   dest.z = z * in;
                                                   return dest; }
    public final Vector3 set(float x, float y, float z) { this.x =
                                                            x;
                                                          this.y =
                                                            y;
                                                          this.z =
                                                            z;
                                                          return this;
    }
    public final Vector3 set(Vector3 v) { x = v.x;
                                          y = v.y;
                                          z = v.z;
                                          return this; }
    public final float dot(float vx, float vy, float vz) { return vx *
                                                             x +
                                                             vy *
                                                             y +
                                                             vz *
                                                             z; }
    public static final float dot(Vector3 v1, Vector3 v2) { return v1.
                                                                     x *
                                                              v2.
                                                                x +
                                                              v1.
                                                                y *
                                                              v2.
                                                                y +
                                                              v1.
                                                                z *
                                                              v2.
                                                                z;
    }
    public static final Vector3 cross(Vector3 v1, Vector3 v2, Vector3 dest) {
        dest.
          x =
          v1.
            y *
            v2.
              z -
            v1.
              z *
            v2.
              y;
        dest.
          y =
          v1.
            z *
            v2.
              x -
            v1.
              x *
            v2.
              z;
        dest.
          z =
          v1.
            x *
            v2.
              y -
            v1.
              y *
            v2.
              x;
        return dest;
    }
    public static final Vector3 add(Vector3 v1, Vector3 v2, Vector3 dest) {
        dest.
          x =
          v1.
            x +
            v2.
              x;
        dest.
          y =
          v1.
            y +
            v2.
              y;
        dest.
          z =
          v1.
            z +
            v2.
              z;
        return dest;
    }
    public static final Vector3 sub(Vector3 v1, Vector3 v2, Vector3 dest) {
        dest.
          x =
          v1.
            x -
            v2.
              x;
        dest.
          y =
          v1.
            y -
            v2.
              y;
        dest.
          z =
          v1.
            z -
            v2.
              z;
        return dest;
    }
    public final String toString() { return String.format("(%.2f, %.2f, %.2f)",
                                                          x,
                                                          y,
                                                          z); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1ae2wUxxmfO7/Nw8a8jIsfGJvWQO8KKFSxCQ24BkwPbLCh" +
                                                    "iVFw1ntz9sLe7rK7Zx+mFEKTgtIIVa1JSUhdCREolEBoS0MUpUGq0hClDwVV" +
                                                    "jSK1oWmlNipFCn/koaQt/b6Zvdu9vbvFR49Yms+z8/p+33yP+Xb2zt4gRYZO" +
                                                    "FmmqvHtQVs0AjZuBHfI9AXO3Ro3A+tA93YJu0HC7LBhGL7T1i43PV3z46XeH" +
                                                    "Kv2kuI9MFxRFNQVTUhVjMzVUeZiGQ6TCbu2QadQwSWVohzAsBGOmJAdDkmG2" +
                                                    "hcgkx1STNIUSEIIAIQgQggxCcJU9CiZNoUos2o4zBMU0dpFvEl+IFGsiwjPJ" +
                                                    "vNRFNEEXotYy3UwCWKEUn7eCUGxyXCcNSdm5zGkCH1kUHPvB9sqfFpCKPlIh" +
                                                    "KT0IRwQQJjDpI5OjNDpAdWNVOEzDfWSaQmm4h+qSIEujDHcfqTKkQUUwYzpN" +
                                                    "bhI2xjSqM572zk0WUTY9JpqqnhQvIlE5nHgqisjCIMg6y5aVS7gG20HAcgmA" +
                                                    "6RFBpIkphTslJWySeveMpIxNX4MBMLUkSs0hNcmqUBGggVRx3cmCMhjsMXVJ" +
                                                    "GYShRWoMuJikJuuiuNeaIO4UBmm/Sard47p5F4wqYxuBU0wy0z2MrQRaqnFp" +
                                                    "yaGfGxtXHN6jrFP8DHOYijLiL4VJda5Jm2mE6lQRKZ84eWHoSWHWy4f8hMDg" +
                                                    "ma7BfMwL37h5/+K6y1f4mM9lGNM1sIOKZr94YmDqm3PbW+4tQBilmmpIqPwU" +
                                                    "yZn5d1s9bXENPG9WckXsDCQ6L2/+9YP7z9DrflLeSYpFVY5FwY6miWpUk2Sq" +
                                                    "r6UK1QWThjtJGVXC7ay/k5RAPSQplLd2RSIGNTtJocyailX2DFsUgSVwi0qg" +
                                                    "LikRNVHXBHOI1eMaIWQKFFIFpYDwP/bfJKHgkBqlQUEUFElRg2C7VNDFoSAV" +
                                                    "1aAhRDUZtGbElIisjgQNXQyq+mDyOQocglspWveyAFqVluf14oi/csTng62d" +
                                                    "63ZsGXxinSqHqd4vjsVWd9w81/+GP2noluSgMOAQsDgEkEPA4kB8PrbwDOTE" +
                                                    "9QW7vRP8FiLa5Jaeh9Y/fKgRdimujRTifsHQRpDCYt8hqu22c3eyECaChVUf" +
                                                    "33Yw8PGpr3ALC2aPxBlnk8tHRx7Zuu9LfuJPDakoDjSV4/RuDITJgNfkdqVM" +
                                                    "61YcfO/D80/uVW2nSonRlq+nz0RfbXRvvK6KNAzRz15+YYNwsf/lvU1+UggB" +
                                                    "AIKeKYCRQjypc/NI8dm2RPxDWYpA4IiqRwUZuxJBq9wc0tURu4VZxFRWnwZK" +
                                                    "mZSw6lmWVbP/2DtdQzqDWxBq2SUFi69rXrz81MWnF93rd4biCsfh1kNN7tjT" +
                                                    "bCPp1SmF9j8f7f7+kRsHtzELgRHzMzFoQtoObg4qg2197Mqut6+9c+IP/qRV" +
                                                    "+Uw472IDsiTGYY0FNhcIAjLYKeq+aYsSVcNSRBIGZIrG+e+K5iUX/3W4kmtT" +
                                                    "hpaEMSy+/QJ2+5zVZP8b2z+qY8v4RDyEbMntYXwDptsrr9J1YTfiiD9ytfap" +
                                                    "14QfQoyEuGRIo5SFGsIkI2zrg0xVCxkNuPqWIGnQ0vpYQ026judYOp6TUcdI" +
                                                    "mlzcCpKe2+KREulSFKL0sHWMBPdWXdv5zHvPcQd2nzmuwfTQ2OO3AofH/I6D" +
                                                    "eX7a2eicww9nBnkKF/EW/Pmg/BcLioYNPDhXtVsnREPyiNA0NJR5XrAYizX/" +
                                                    "OL/3pR/vPcjFqEo9lzog7Xruj//5TeDoX17PEDLBE1SB2VQrIwztcg9Nrkay" +
                                                    "bOKarLc0WT9hTfq4t7B+NmqtB5xOJF9Nh8PxVLOnEm+jWINJmiOqf9IlDxz4" +
                                                    "68dss9LicgY7cc3vC559pqZ95XU23w6QOLs+nn62QUJrz116JvqBv7H4VT8p" +
                                                    "6SOVopUtbxXkGIahPsgQjUQKDRl1Sn9qtsdTm7bkATDXbagOtu7QbBsI1HE0" +
                                                    "1std0Zg55goohZZ+C9369RFW2cSmNDLajOQL3FFNUqLp0jDYOYRFgyXmaI2S" +
                                                    "Ishxk5S1d/X0967r6F0FumvOrjsWn7h/jp+c/7t94/PfhX3vI6WSARKu0gcz" +
                                                    "JKCOOe+fvXb96pTac+wwKxwQDC6rO3NPT8xT8m22NZM19q/VbcbsOaQltuOh" +
                                                    "zNvhx2oLkq+D9MUyVQbNITboy0jauDnfZ5ICgILVbVo8ycnPl2DPM00rfKMZ" +
                                                    "QK6tKhRPgkQfT4AkNZB8z4HOeBpmndSmpD8bmLS2nT5++icvmG8uauURZ2F2" +
                                                    "/bgnvnbgnzW9K4ceziHpqXepz73k6Q1nX1+7QPyenxQkzT3thSl1UluqkZfr" +
                                                    "FN7wlN4UU6/j+tyWKUI5A5Dm0cecHl4kikTUA1cb7G195pO7I6qZ7KwdvTT7" +
                                                    "5ytOjb/DUoc4c6pKziaU7n9Flv8VZfG/4Sz+h9UtSLYmrK6sp3Mj97mk4JnZ" +
                                                    "tkIpttgWZ2G7Z8JsS9DVu9d1ToBpicW0JAvT/RNnirJ6Mp2USD/KLKZlWZg+" +
                                                    "xizEtMJA6/+32KHEYrvzsNh3EouNOhaL3z4AOY5OghlIbbY3eJZ9nDgwNh7u" +
                                                    "enaJ3zL6lWBJpqp9UabDVHYsNZvVdyTFaMDlF0BptsRozpgheKi0xaFSlxc6" +
                                                    "Q6IrghYZQ6rOYijPLp72cOAfITkCARnCihqmDnWkpT4ZBAtYggXyKJgjOTrG" +
                                                    "1jnlAf80kuM5wp+NjbVQllrwl+YAP82MMgO74NH3MyRnATS8Olqgj90WNBYy" +
                                                    "A8pKC/TKjKCZQ2TfUX4J8aIHuJeQ/AKO4UHKk+aJb2enhawz/9v5K4++V5H8" +
                                                    "MplT5AC6CcoGC/SG/IP+rUff75FcMckUDrpnV0zQaXhi2NlpgW8bmyzsm/KP" +
                                                    "/S2PvreRXIUNV+ggZLcTc7ok6Acs0A/kB3Tau9S7Hsj/huRPd4J8LpR+C3l/" +
                                                    "3pHzl9LrHshvIPk7uGU0JucIO2LBjuQHtt8e0Grv+gce2D9C8v4dYY9a2KN3" +
                                                    "actvZYftY8M+AdhhaThH2KYF27yLW+4r88A+CUlhLthZQMQzfY+FfU9+sDtR" +
                                                    "Tffom4lkqkkqFHZtKo3SUA7hnO18I5RHLfSP5h99rUdfPZJqyAuT6HOwGMT9" +
                                                    "hIX7ibwbOjeWz3uAxyV8jXcIHs19zAI/lh/wBXae2GoTBnWJhxjLkCwGmzd4" +
                                                    "5pKDAMcsAY7dpd1v84B9H5LlucBmrloD5bgF+/hd3/cODwHWIrkfY406wYyx" +
                                                    "DhvnQTlpCXAyBwEm+GLEVrA10OUhwCYk63MRoAEb8W3ujCXAmTwK4FBDpUuK" +
                                                    "Bz2kwJd8Xy9exeiqYUzMkpgc86FcsOS48NnIIXrIgcHHtx20IYTDOUpxyZLi" +
                                                    "0mcjRdRDCvxw7RtCr44NTEyKamKJ8oolxSv58WonqhGPPryP8ekmKTVV/ksK" +
                                                    "NmqmSSrt21bekeFbhElKrA/ReP1XnfazFf5TC/HceEXp7PEtb/Hb6MTPIcpC" +
                                                    "pDQSk2XnrbyjXqzpNCIx6cv4HT27uPTtB2juz+EmKcR/CNC3jw/7lkkmOYYB" +
                                                    "UqvmHPRt0BQMwupBLYPg/GtDnLCuxMWRlvKU8tUUr4nZT3wSV7ox/iOffvH8" +
                                                    "+PqNe24uf5bdDxeJsjDK7q5KQ6SEfzBmi+K18LysqyXWKl7X8unU58uaExdT" +
                                                    "U5FUOeyk2tYvif0PGOGG9VAlAAA=");
}
