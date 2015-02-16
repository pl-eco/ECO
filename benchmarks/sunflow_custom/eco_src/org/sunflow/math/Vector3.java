package org.sunflow.math;
final public class Vector3 {
    final private static float[] COS_THETA = new float[256];
    final private static float[] SIN_THETA = new float[256];
    final private static float[] COS_PHI = new float[256];
    final private static float[] SIN_PHI = new float[256];
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
    final public static Vector3 decode(short n, Vector3 dest) { int t =
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
    final public static Vector3 decode(short n) { return Vector3.
                                                    decode(
                                                      n,
                                                      new Vector3(
                                                        )); }
    final public short encode() { int theta = (int) (Math.acos(z) *
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
    final public float length() { return (float) Math.sqrt(x * x +
                                                             y *
                                                             y +
                                                             z *
                                                             z); }
    final public float lengthSquared() { return x * x + y * y + z *
                                           z; }
    final public Vector3 negate() { x = -x;
                                    y = -y;
                                    z = -z;
                                    return this; }
    final public Vector3 negate(Vector3 dest) { dest.x = -x;
                                                dest.y = -y;
                                                dest.z = -z;
                                                return dest; }
    final public Vector3 mul(float s) { x *= s;
                                        y *= s;
                                        z *= s;
                                        return this; }
    final public Vector3 mul(float s, Vector3 dest) { dest.x = x *
                                                                 s;
                                                      dest.y = y *
                                                                 s;
                                                      dest.z = z *
                                                                 s;
                                                      return dest;
    }
    final public Vector3 div(float d) { x /= d;
                                        y /= d;
                                        z /= d;
                                        return this; }
    final public Vector3 div(float d, Vector3 dest) { dest.x = x /
                                                                 d;
                                                      dest.y = y /
                                                                 d;
                                                      dest.z = z /
                                                                 d;
                                                      return dest;
    }
    final public float normalizeLength() { float n = (float) Math.
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
    final public Vector3 normalize() { float in = 1.0F / (float) Math.
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
    final public Vector3 normalize(Vector3 dest) { float in = 1.0F /
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
    final public Vector3 set(float x, float y, float z) { this.x =
                                                            x;
                                                          this.y =
                                                            y;
                                                          this.z =
                                                            z;
                                                          return this;
    }
    final public Vector3 set(Vector3 v) { x = v.x;
                                          y = v.y;
                                          z = v.z;
                                          return this; }
    final public float dot(float vx, float vy, float vz) { return vx *
                                                             x +
                                                             vy *
                                                             y +
                                                             vz *
                                                             z; }
    final public static float dot(Vector3 v1, Vector3 v2) { return v1.
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
    final public static Vector3 cross(Vector3 v1, Vector3 v2, Vector3 dest) {
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
    final public static Vector3 add(Vector3 v1, Vector3 v2, Vector3 dest) {
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
    final public static Vector3 sub(Vector3 v1, Vector3 v2, Vector3 dest) {
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
    final public String toString() { return String.format("(%.2f, %.2f, %.2f)",
                                                          x,
                                                          y,
                                                          z); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1414698744000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAMVaC5AUxRnu3Xs/8I5Decs9PEReu9whKA8f5/E6WY7zDhBB" +
                                                   "cszN9t6NzM6MM7PH\nchDU0oCi8VHRxERFY4jgqRFLU5DECJaPGEkqaBKtWN" +
                                                   "FokahV0UQrKcXESuX/e3p3Zmdn5072KK+q\n+2anu/+/v//V/z8zj39Migyd" +
                                                   "TBaNkLlNo0aotatD0A0abZUFw1gDt7rFl4rKOvavVNQgCURIUIqa\npCoiGu" +
                                                   "GoYAphKRpuW7IoqZOZmipv65VVM0STZugaeR6nd3lkXhbBK/ceqrlhX2FtkB" +
                                                   "RFSJWgKKop\nmJKqLJVp3DBJdeQaoV8IJ0xJDkckw1wUIaOokoi3qophCopp" +
                                                   "XEt2koIIKdZEpGmS+kiKeRiYhzVB\nF+Jhxj7cwdgChTE6NQVJodGWNDtYOS" +
                                                   "tzJWybr+vMng1ESnFwHcBhOwDUdWnUFtosqFrBgXXzdzz0\naAGp2kCqJKUL" +
                                                   "iYmAxAR+G0hlnMZ7qG60RKM0uoGMViiNdlFdEmRpgHHdQGoMqVcRzIROjU5q" +
                                                   "qHI/\nTqwxEhrVGc/UzQipFBGTnhBNVU/LKCZROZr6VRSThV6APdaGbcFdhv" +
                                                   "cBYLkEG9NjgkhTSwq3SApo\nvNa9Io2xcSVMgKUlcWr2qWlWhYoAN0iNpUtZ" +
                                                   "UHrDXaYuKb0wtUhNABeTTMxJFGWtCeIWoZd2m2S8\ne16HNQSzypggcIlJzn" +
                                                   "JPY5RASxNdWnLoZ+bYz24+cP9zlzLbLoxSUcb9l8OiKa5FnTRGdaqI1Fp4\n" +
                                                   "MhG6u+2qxOQgITD5LNdka07L1ENrIx8eqbXmTPKYs7rnGiqa3WL7XbWd25er" +
                                                   "pAC3UaqphoTKz0DO\n3KGDjyxKauC1Y9MUcTCUGjza+fJV1w/SvwdJeRspFl" +
                                                   "U5EQc7Gi2qcU2Sqb6cKlQXTBptI2VUibay\n8TZSAtcRMHnr7upYzKBmGymU" +
                                                   "2a1ilf0GEcWABIqoDK4lJaamrjXB7GPXSY0QMgoaqYFWQKw/9t8k\nM0NhI6" +
                                                   "HEZHVr2NDFsKr3pn/HgUB4HUXjnRtCo9FMsjzcp8ZpWBAFRVLUcK8Ebiqqs6" +
                                                   "O0H/8Pn1QS\nd1azNRDAUOd2WRmsfYUqR6neLe4/8eqOpStvudkyBzRhjglU" +
                                                   "ARxCnEMIOYQ4BxIIMMJnIidLEyDH\nLeCRELsqp3dtunzzzQ2AP6ltLURJwN" +
                                                   "QG2D1nv1RUW223bWMRTgTbGf/wxt2hk/svsWwnnDu6eq6u\nOP7EsYf+VTkj" +
                                                   "SILeoQ9hQfAtRzIdGC/TIa3R7Sxe9P+xZ9XTbxx7+zzbbUzSmOXN2SvRGxvc" +
                                                   "CtBV\nkUYhvtnk902oKriSrLsrSArBxSGssf1DxJji5pHhlYtSEQ6xlERIRU" +
                                                   "zV44KMQ6mwVG726epW+w6z\njGp2PQaUU5Gy27Hcbtl/HD1Lw36sZUmobRcK" +
                                                   "FkFP3lg8581nK15iYkkF2yrHcdZFTct1R9vGskan\nFO6/fW/Hd+75ePdGZi" +
                                                   "mWqQRMOOMSPbIkJmHJufYS8FkZjA8V2bhWiatRKSYJPTJFi/uyamrTTz+6\n" +
                                                   "vdpSjQx3UpqdNTQB+/6Ey8j1x77x+RRGJiDimWHDsKdZaMbYlFt0XdiG+0je" +
                                                   "8PrZ3/+V8ACENAgj\nhjRAWWQgDBlhcgwzuc9gfcg11oRdA9CelcP0PU7obn" +
                                                   "HHYG9D4tpf/4ztukJwHvVONawStEWW5rE7\nB6U7zu29KwSjD+adf7T96mr5" +
                                                   "6H+B4gagKMLJaKzWIVgkM5TIZxeVvPX8C2M3v1ZAgstIuawK0WUC\ns39SBo" +
                                                   "ZHjT6IM0ntkkuZbVVvLcWeQSZMCBO5ANiPydlWOYFb5QRPq8TuXJdIC9IxZ7" +
                                                   "wzxdOlOBwV\n/cxrTuxq+OUrax/cbUWa6T55nHNVt3jdX97dUnDH8z3WOvdx" +
                                                   "6Zp815R97z99ovNMyyqtnOKcrGPd\nucbKKxiyKg01VO/Hgc1+cWb94zs73+" +
                                                   "E7qsk8HZdCBvnBthfotMXffs8jvIO3qgJzlRbWMcaLfQx0\nOXYL2FAzdgst" +
                                                   "rc07ZeXWcuXWDlu5AStKGN/EnkxwKLhdVdgxL4konGTKvDENC+k0hs6LcTe5" +
                                                   "7b1p\nb9X9prr1mOXmfSaZ6kjY+Mxwm9KvisyPVghKFFIIy+snezK8Uhc0SM" +
                                                   "mOv/v+pjtmfvAy6kJjENp9\nhLkOu5W2MCPDFWbS8avE8DXfZZgJ22dMd8+s" +
                                                   "A5FXVz/AkOc8Ij0s20Vn4Lm1e0/+1nyH0bHPKlxd\nn8xON6B6sNde+Eb/6O" +
                                                   "KDD8aDpGQDqRZ5fbNOkBN4ImyAdNxIFT1QA2WMZ6bWVh65KH0WT3a7loOt\n" +
                                                   "+5S0/QCucTZeV7oORhZxFkMr5FZa6LbSAGEXm9mSRtZP4xkPepekCLCvEk2X" +
                                                   "+iH3hIPNYJVQ0iRl\nrau7utesWLqmBY9Vh02xowTt99E7l4zpXLDxRpbNlE" +
                                                   "FtJBjt9p6hIsWrAAh7am7tp4l1i9M2Hfrk\n+efoNBbSSyUDZNOi93rUCY41" +
                                                   "nwqDdNWbsb0sIynsEQxLSu4CK7t+yiiLmFDPsNyhRdM0yIgtOAua\n5l8I6G" +
                                                   "sAPZbVISnKzWzJ68t6BmPKYDTIyDNmLbiCgy9jdxzSKFA1A+sGR4HOKTWu1g" +
                                                   "zMv0Y5mLQt\n2XHw8srSH966i9Hnoixz1CD8d0m/oLc742UF23dTU9P8pnkm" +
                                                   "6RihRH3h+c2zmi6Y3TzfJPNz5duh\ntL1MM+pMQe+lZp19DzcoO41qJ9RJ2W" +
                                                   "JFYXBLxuALYjnDjpB4VDgHAW5h59KWJVbulw5SwlBBamO2\n+xRxskU53Ge7" +
                                                   "h/vgNdMLk34MXaarrZ27DN7q87Yo63CKO3U1Z8FI6qppaF2lN+rQVfoebu8G" +
                                                   "Jxq8\nsdUl5R1fUcoLoRVzKRfnkPItw5JyCdpQx4o2HxlfMMdDxiPqD3NmzW" +
                                                   "2ePRfU1uzrD7BNlzfAHdza\nHTYOL+nuOQXplnDpluSQ7veGJ13U+hDSbcqW" +
                                                   "blPTyFrwkNLl23TZLxfnAzYOL+neO3zpsjQQRVzG\npVuWQ7o/ZpmgCeddOl" +
                                                   "l1cHzkNHAcTHHc5sXxsdPA8ckUxwEvjgd9OCazbS+Ytr3pzmyRYE1xdq4n\n" +
                                                   "g6ye2L3+08pdwoubgjxLbQXTNFVttkz7qewgNQ4pZTzSWcUOfTvR2/PoY4fM" +
                                                   "12YutCqTGbnTFPfC\nGQsfGqhd+OStp/Agp9aFzU16dP+kKwr6pFfYyc/zxq" +
                                                   "zHvJmLFmVmi+Wwn4SurMnIGevS6h6PUr4I\n2lSu7qludduKzREtplvRwlU5" +
                                                   "BC29st8XadboZZBkQnmtW28HVrO+izF40afuOIbdEchGAb4apc61\ntr0d/U" +
                                                   "qV3S8y8YehhTj+0Ajh53Uf/nyJ0XjTB+KfsHt9CIi/P0WIo/EmFqzNHGLzMC" +
                                                   "FmOaX35v/m\nM/YBdu8CMKogMCYNG9F7p4iIxahJ0C7miC72RMQCVI5y3GWW" +
                                                   "BVATsEX/9AHzb+w+gslwwrCYZyP5\nOF/dtHEkbSOrm//lHgswNl+AbmSq9F" +
                                                   "ovBhyI/pMPonOhreKIVo0ookC5z1gldkUmGWUh6ro2Ieg0\nmgksUJwPsHpo" +
                                                   "V3BgV4wssLE+Y+OxGw2qUmgvVOae8SFQkw+w2dDWc2Dr8wfmdDNHnA80+oA8" +
                                                   "D7va\nIUDW5QMSz7ZuDrJ7xEAy+2IIzvdBNx+7MASPeEL2hjYnH2hzocU4tF" +
                                                   "j+0IL2hJYsJV7mA3MZdhf5\nwbw4Xw2myv74adBgpw+0NditAmhRqd8bWnu+" +
                                                   "GjQ5NPM0a3CzD0wkF9joB/PqfGCeB207h7k9f5jO\nnWs+Y/isN7DFJFUKe9" +
                                                   "0oDdCIx6kXkPOB1gjtJg7tppGFtsNnbCd2W6HsSUPzVlsyH2xzoN3Gsd02\n" +
                                                   "Yo6XZZh7fHDejt23hsa5Kx+cF0C7m+O8O3+cBXat0GJ3DM4PfKDej9094IMG" +
                                                   "Nb1BfjcfkNOg3cdB\n3nf6lHnAB+Hj2P3ID+G+fBBeCO1hjvDh06rGwz4gf4" +
                                                   "7d0xhKVVfBEHgmn3p1AbRHOLpHhonuq9Tr\nTl04FepTpQdexe6IJ9a8avOV" +
                                                   "0AY51sERwpp64Z4DaxbuP/jg/jN2vzNJkairhuFtysfzEQBWhE9x\nATz1NQ" +
                                                   "ngQx8BfILdCVC8EI16w/9rvvAPc/iHvyb4X+aGHyzECZ9jIEv0eMM/mU8ga4" +
                                                   "B2hMM/Mkz4\nw8spgtU+Y/jsJFhhklJTtb4IZbPGmaTafgPnGECcwcphfw9g" +
                                                   "khL+5B4/URif9X2u9U2pGHlr+9Wf\nRf74hfU+N/XdZ0WElMYSsux8I+64Lt" +
                                                   "Z0GpOYaCqs9+PstUVwEuzd/f7AJIX4DzcXnGhNm2KSCsc0\n2Cm/ck6qB3XD" +
                                                   "JLxs0DwkY73pz3yQjUjPyXimzD6BTj33TVgfQXeL65/YWJe8dc2d7GFykSgL" +
                                                   "A+z5\nenmElFhfyjGq+Oy4Pie1FK3j5OCT6579yYLUs3H2JdWZDrPJsLxma9" +
                                                   "RHiTqp9f48bWlcM9kHZQOH\nxz2zeP/ed9gXJdr/AeqzFHq5LgAA");
}
