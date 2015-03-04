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
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1ae2wUxxmfO7/Nw8YEMC5+YOy0BnpXQKGKTWiIa8D0iA02" +
                                                    "NDEKl/XenL2wt7vszpkDSkNoUlAaRVXrpElIXQnl0VACoS1NoigNUpUmUfpQ" +
                                                    "oqpRpDY0rdRGpUjhjzyUtKXfN7N3u7d3t/jSI5bm8968vt/3nG/n7uRFUmGZ" +
                                                    "ZJmhq/vGVJ2FaIqFdqnXhdg+g1qhTZHrBiXTorFeVbKsYeiLyu1P133wyXfH" +
                                                    "64OkcoTMlTRNZxJTdM3aSi1dnaCxCKlzevtUmrAYqY/skiakcJIpajiiWKwn" +
                                                    "Qma4ljLSEUlDCAOEMEAIcwjhdc4sWDSLaslEL66QNGbtId8kgQipNGSEx8ji" +
                                                    "7E0MyZQS9jaDXALYoRo/bweh+OKUSdoysguZcwS+f1l48gc7639aRupGSJ2i" +
                                                    "DSEcGUAwYDJCZiZoYpSa1rpYjMZGyByN0tgQNRVJVfZz3COkwVLGNIklTZpR" +
                                                    "EnYmDWpyno7mZsoom5mUmW5mxIsrVI2lP1XEVWkMZJ3vyCokXI/9IGCtAsDM" +
                                                    "uCTT9JLy3YoWY6TVuyIjY8fXYAIsrUpQNq5nWJVrEnSQBmE7VdLGwkPMVLQx" +
                                                    "mFqhJ4ELI00FN0VdG5K8WxqjUUYavfMGxRDMquGKwCWMzPNO4zuBlZo8VnLZ" +
                                                    "5+LNa+47oG3UghxzjMoq4q+GRS2eRVtpnJpUk6lYOHNp5AFp/gtHg4TA5Hme" +
                                                    "yWLOM9+4dOPylnOviDmfyzNnYHQXlVlUfnR09uuLeruuL0MY1YZuKWj8LMm5" +
                                                    "+w/aIz0pAyJvfmZHHAylB89t/fWth07QC0FS208qZV1NJsCP5sh6wlBUam6g" +
                                                    "GjUlRmP9pIZqsV4+3k+q4DmiaFT0DsTjFmX9pFzlXZU6/wwqisMWqKIqeFa0" +
                                                    "uJ5+NiQ2zp9TBiFkFjTSAK2MiD/+n5FoeFxP0LAkS5qi6WHwXSqZ8niYynrU" +
                                                    "pIYe7usdCI+ClscTkrnbCltJLa7qe6Ny0mJ6ImyZclg3x9Ld4QQwDW+n6PCr" +
                                                    "QuhoxtVnkUIp6/cGAmCARd7wVyFyNupqjJpReTJ5U9+lU9HXgplwsPUDZgUO" +
                                                    "IZtDCDmEbA4kEOAbX4OchFXBJrshuiHvzewaum3T7UfbQZcpY285ahWmtoNg" +
                                                    "Nvs+We91UkA/T3Qy+GHj8R1HQh898RXhh+HC+TrvanLuwb13br/jS0ESzE68" +
                                                    "KA501eLyQUyXmbTY4Q24fPvWHXn3g9MPHNSd0MvK5HZGyF2JEd3uVbypyzQG" +
                                                    "OdLZfmmbdDb6wsGOICmHNAGpkUngypB1Wrw8siK7J50lUZYKEDiumwlJxaF0" +
                                                    "aqtl46a+1+nhHjGbP88Bo8xI+/582/f5fxydayC9RngQWtkjBc/C658799DZ" +
                                                    "h5ddH3Qn7DrXEThEmQj/OY6TDJuUQv+fHxz8/v0Xj+zgHgIzluRj0IG0F5IB" +
                                                    "mAzUevcre946//ajfwhmvCrA4FRMjqqKnII9rnW4QKpQwU/R9h3btIQeU+KK" +
                                                    "NKpSdM5/13WuOPuv++qFNVXoSTvD8itv4PQvvIkcem3nhy18m4CMR5UjuTNN" +
                                                    "KGCus/M605T2IY7UnW80P/Sy9EPIpJC9LGU/5QmJcMkIV32Ym2oppyHP2Aok" +
                                                    "bUbOGO9oyrXxQtvGC/PaGEmHh1tZJnK7fAonU0lALp+wD5vwwYbzux959ykR" +
                                                    "wN6TyTOZHp2853Lovsmg6/heknOCuteII5xDniVEvAx/AWj/xYaiYYdI4Q29" +
                                                    "9jnSljlIDAMdZbEfLM5i/T9OH3z+xwePCDEask+vPijOnvrjf34TevAvr+ZJ" +
                                                    "mRAJusR9qpsTjna1jyVvQrJq+pZstS3ZOm1LBkS08HE+a4MPnH4kX82FI/A0" +
                                                    "8k9V/k6xHks5V1b/eEAdPfzXj7iycvJyHj/xrB8Jn3ykqXftBb7eSZC4ujWV" +
                                                    "e7ZB2eusXXki8X6wvfKlIKkaIfWyXVNvl9QkpqERqCOtdKENdXfWeHZNKAqg" +
                                                    "nswBsMjrqC623tTsOAg842x8rvVkYx6Ya6CV2/Yt99o3QPjDFr6kndNOJF8Q" +
                                                    "gcpIlWEqE+DnkBYtXr6jNyqapKYYqekdGIoOb+wbXge26yxsO56fRHxOPb7k" +
                                                    "d3dMLXkH9D5CqhULJFxnjuUpU11r3jt5/sIbs5pP8cOsfFSyhKze+j63fM+q" +
                                                    "yrlqZhr8X7fXjfnniJFWx2351RHExy4kXwfpK1WqjbFxPunLSHqEO9/ASBlA" +
                                                    "wccdRirDKSi24J/nMTt9oxtARa5rFE+C9JgogBQ9lHkbgsFUDmaTNGeVP5u5" +
                                                    "tI6f3vPkT55hry/rFhlnaWH7eBe+fPifTcNrx28vouhp9ZjPu+WTm0++uuFa" +
                                                    "+XtBUpZx95zXquxFPdlOXmtSeA/UhrNcvUXYc0e+DOVOQIbPGA96eN2okNEO" +
                                                    "wmyg29b8J3dfwmD8rN3/7IKfr3li6m1eOqR4UNULNpHc+Kuw46+iQPxNFIg/" +
                                                    "fNyGZHva62qG+m8WMZcRPD/bbmiVNtvKAmwPTJttFYb64Mb+aTCtsplWFWB6" +
                                                    "aPpMUVZfpjPS5UeNzbSmANO7uYcwOw10/3+bHU1vtq8Em30nvdl+12apKycg" +
                                                    "19FJsAJpLvSez6uPRw9PTsUGHlsRtJ1+LXgS040vqnSCqq6tFvDnXRkx2nD7" +
                                                    "a6F12mJ05q0QfEza5TKpJwrdKdGTQSuscd3kOVRUFw/7BPCPkNwPCRnSih6j" +
                                                    "LnPklD55BAvZgoVKKJirODrG93nCB/6TSI4XCX8BdjZDW2nDX1kE/Bw3yg/s" +
                                                    "jM/Yz5CcBNDw6miDPnZF0NjINdDW2qDX5gXNA6KwRsUlxHM+4J5H8gs4hseo" +
                                                    "KJqnr85+G1l/6dX5K5+xl5D8MlNTFAG6A9pmG/Tm0oP+rc/Y75G8wsgsAXpo" +
                                                    "T1IyaWx62PlpgW8bW2zsW0qP/U2fsbeQvAEK1+gYVLfTC7oM6Fts0LeUBnTO" +
                                                    "u9Q7Psj/huRPnwb5ImhRG3m05MjFS+kFH+QXkfwdwjKRVIuEHbdhx0sDO+hM" +
                                                    "6Ha0/r4P9g+RvPepsCds7ImrpPLLhWEH+LSPAXZMmSgSNrNhs6uo8kCND/YZ" +
                                                    "SMqLwc4TIp7pB2zsB0qD3Y1qrs/YPCSzGanT+LWpsp9GikjnXPPt0O6y0d9V" +
                                                    "evTNPmOtSBqhLsygL8JjEPe9Nu57S+7owlk+7wMetwi0f0rw6O6TNvjJ0oAv" +
                                                    "c+rEbodwqCt8xFiFZDn4vCUqlyIEOGYLcOwqab/HB/YNSFYXA5uHahO04zbs" +
                                                    "41dd730+AmxAciPmGn2aFWMLdi6G9rgtwONFCDDNFyO+g2OBAR8BtiDZVIwA" +
                                                    "bdiJb3MnbAFOlFAAlxnqPVLc6iMFvuQHhvEqxtQta3qexOVYAu2MLceZz0YO" +
                                                    "2UcOTD6BnWANKRYrUopnbSme/WykSPhIgV9vB8YxqpOj05OikdiivGhL8WJp" +
                                                    "otqNaq/PGN7HBExGqpkufm/BZ81jpN65bRUDeb6LYKTK/iIar/8ac37cIn6Q" +
                                                    "IZ+aqqteMLXtTXEbnf7RRE2EVMeTquq+lXc9VxomjStc+hpxR88vLgOHAJr3" +
                                                    "63BGyvEfAgzcIaZ9i5EZrmmA1H5yT/o2WAom4eMRI4/g4tuGFOFD6YsjI+tT" +
                                                    "1remeE3MfwiUvtJNip8CReXTU5tuPnBp9WP8frhCVqX9/O6qOkKqxBfGfFO8" +
                                                    "Fl5ccLf0XpUbuz6Z/XRNZ/piajaSBpefNDr2Jcn/ASnqezl2JQAA");
}
