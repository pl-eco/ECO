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
    public static final long jlc$SourceLastModified$jl7 = 1414698744000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1ae2wUxxmfO7/Nw8a8jAsYjKHl0bsCClVsQgOuAdMDDDYk" +
                                                    "MQrOem/OXtjbXXbnzAGlISQpKI1Q1ZqUhNSVEIFCCYS2NERRGqQqDVH6UFDV" +
                                                    "KFIbmlZqo1Kk8EceStrS75vZu93bu1t89Iil+Tw7z9/3nG9n78x1UmaZZL6h" +
                                                    "q7v6VZ2FaJKFtql3hdgug1qhNZG7OiXTotE2VbKsbmjrlZteqPnos+8O1AZJ" +
                                                    "eQ8ZL2maziSm6Jq1kVq6OkijEVLjtLarNG4xUhvZJg1K4QRT1HBEsVhrhIxy" +
                                                    "TWWkOZKCEAYIYYAQ5hDCy51RMGkM1RLxNpwhaczaQb5FAhFSbsgIj5GZmYsY" +
                                                    "kinF7WU6OQewQiU+bwam+OSkSWakeRc8ZzF8eH546Adba39aQmp6SI2idSEc" +
                                                    "GUAw2KSHjI7TeB81reXRKI32kHEapdEuaiqSquzmuHtInaX0axJLmDQtJGxM" +
                                                    "GNTkezqSGy0jb2ZCZrqZZi+mUDWaeiqLqVI/8DrJ4VVwuBLbgcFqBYCZMUmm" +
                                                    "qSml2xUtykijd0aax+ZvwACYWhGnbEBPb1WqSdBA6oTuVEnrD3cxU9H6YWiZ" +
                                                    "noBdGGnIuyjK2pDk7VI/7WWk3juuU3TBqCouCJzCyETvML4SaKnBoyWXfq6v" +
                                                    "W3poj7ZaC3LMUSqriL8SJk33TNpIY9SkmkzFxNHzIk9Jk145GCQEBk/0DBZj" +
                                                    "XvzmjXsXTL90WYz5Qo4x6/u2UZn1ysf7xr41tW3u3SUIo9LQLQWVn8E5N/9O" +
                                                    "u6c1aYDnTUqviJ2hVOeljb9+YN9pei1IqjtIuayriTjY0ThZjxuKSs1VVKOm" +
                                                    "xGi0g1RRLdrG+ztIBdQjikZF6/pYzKKsg5SqvKlc588gohgsgSKqgLqixfRU" +
                                                    "3ZDYAK8nDULIGCikDkoJEX/8PyP3hTdZYO5hSZY0RdPDYLxUMuWBMJX13j6Q" +
                                                    "7kBcMrdbYTlhMT0ethJaTNV3hi1TDutmf/o5DpuFN1M09MUhNDDjzi2dRK5q" +
                                                    "dwYCIPCpXndXwVNW62qUmr3yUGJF+42zvW8G0+ZvywPUCDuE7B1CuEPI3oEE" +
                                                    "AnzhCbiT0CLoYDt4M8S50XO7Hlzz0MEmkF3S2FmKUoShTcCPvX27rLc5Lt/B" +
                                                    "A5sMdld/bMuB0CcnvybsLpw/PuecTS4d2fnI5oe/EiTBzECL7EBTNU7vxPCY" +
                                                    "DoPNXgfLtW7Ngfc/OvfUXt1xtYzIbUeA7JnowU1ewZu6TKMQE53l582QLvS+" +
                                                    "src5SEohLEAoZBKYLkSZ6d49Mjy5NRUVkZcyYDimm3FJxa5UKKtmA6a+02nh" +
                                                    "FjGW18eBUkalbH2Sbev8P/aON5BOEBaEWvZwwaPuypcuPX3hmfl3B90BusZ1" +
                                                    "5HVRJtx9nGMk3Sal0P7nI53fP3z9wBZuITBiVq4NmpG2gfODykCsj1/e8c7V" +
                                                    "d4//IZi2qgCDUzDRpypyEtaY4+wCoUEFO0XdN2/S4npUiSlSn0rROP9dM3vh" +
                                                    "hX8dqhXaVKElZQwLbr2A0z5lBdn35taPp/NlAjIeTQ7nzjAhgPHOystNU9qF" +
                                                    "OJKPXJn29OvSDyFyQrSylN2UByDCOSNc9GGuqnmchjx9C5HMMLL6eENDto6n" +
                                                    "2DqeklPHSJo9u5WkPXeuT6JkKnGI3YP24RLeW3d1+7PvPy8c2HsSeQbTg0NP" +
                                                    "3AwdGgq6jutZWSeme444sjnkMYLFm/AXgPJfLMgaNoiQXddmnxsz0geHYaCh" +
                                                    "zPSDxbdY+Y9ze1/+8d4Dgo26zNOqHZKx5//4n9+EjvzljRwhEzxBl7hNtXDC" +
                                                    "0S7x0eQKJItHrslGW5ONI9ZkQHgL7+ejVvnA6UDy9Ww4Ak89f6rwN4qVmLq5" +
                                                    "ovqn69W+/X/9hAsrKy7nsBPP/J7wmWcb2pZd4/OdAImzG5PZZxukuc7cRafj" +
                                                    "Hwabyl8LkooeUivbOfRmSU1gGOqBvNFKJdaQZ2f0Z+aAIuFpTR8AU72G6trW" +
                                                    "G5odA4E6jsZ6tScac8dcCqXU1m+pV78Bwisb+JQmTmcj+ZJwVEYqDFMZBDuH" +
                                                    "sGjxdB2tUdEkNclIVdv6rt7u1e3dy0F3s/Prjscn4Z/DJ2b97uHhWe+B3HtI" +
                                                    "pWIBh8vN/hxpqWvOB2euXrsyZtpZfpiV9kmW4NWbz2en6xlZOBfNaIP/a/Ga" +
                                                    "MX+OGClxPJhbHEGszkVyH3BfrlKtnw3wQV9F0irM+R5GSgAKVrcYyfROQbEE" +
                                                    "f57I7PCNZgAZuK5RPAlSfSIBUvRQ+u0HOpNZmE0yLSP9Wcu5dez0iVM/eZG9" +
                                                    "Nb9FRJx5+fXjnfj6/n82dC8beKiApKfRoz7vkqfWnnlj1Rz5e0FSkjb3rNeo" +
                                                    "zEmtmUZebVJ479O6M0x9utDnllwRyh2ADJ8+7vTwelEmox6E2kC2jblP7va4" +
                                                    "wfhZu/vi5J8vPTn8Lk8dktypasU2kWz/K7P9ryyP/w3m8T+sbkKyOWV1VV0d" +
                                                    "64TPpRnPvW0LlHJ72/I82+4Z8bYV6OqdqztGsGmFvWlFnk33jXxT5NV301Gp" +
                                                    "9KPK3rQqz6aPcwthdhho+f8WO5habFcRFvtOarHdrsWStw5ArqOTYAYyLd97" +
                                                    "Pc8+ju8fGo6uf25h0Db6ZWBJTDe+rNJBqrqWmszr29JszMDl50CZbbMxO2eG" +
                                                    "4KPSuS6VerzQHRI9EbTMGtBNHkNFdvGMjwP/CMlhCMgQVvQodakjK/XJwVjI" +
                                                    "ZixURMZcydFRvs5JH/inkBwrEP5kbJwGZZENf1EB8LPMKDew8z59P0NyBkDD" +
                                                    "q6MN+ugtQWMhE6Ass0EvywmaO0R+iYpLiJd8wL2M5BdwDPdTkTSPXJwdNrKO" +
                                                    "4ovzVz59ryH5ZTqnKAB0M5S1Nui1xQf9W5++3yO5zMgYAbprR0IyaXRk2Plp" +
                                                    "gW8bG2zsG4qP/W2fvneQXAGBa7QfstuROV0a9P026PuLAzrrXeo9H+R/Q/Kn" +
                                                    "20E+FUqvjby36MjFS+k1H+TXkfwd3DKeUAuEHbNhx4oDO+gMaHGk/qEP9o+R" +
                                                    "fHBb2OM29vgdEvnN/LADfNinADuqDBYIm9mw2R0UeaDKB/soJKWFYOcBEc/0" +
                                                    "PTb2PcXB7kY13qdvIpKxjNRo/NpU2U0jBYRzLvkmKI/Z6B8rPvppPn2NSOoh" +
                                                    "L0yjL8BiEPeTNu4ni27owli+6AMelwg03SZ4NPchG/xQccCXOHlii0M41IU+" +
                                                    "bCxGsgBs3hKZSwEMHLUZOHqHpN/qA/seJEsKgc1dtQHKMRv2sTsu93YfBlYh" +
                                                    "uRdjjT7CjHE6Ns6EcsJm4EQBDIzwxYiv4GhgvQ8DG5CsKYSBGdiIb3OnbQZO" +
                                                    "F5EBlxpqPVw84MMFvuQHuvEqxtQta2SWxPmYBeW8zcf5z4cP2YcPDD6BraAN" +
                                                    "KRotkIuLNhcXPx8u4j5c4OfswAB6daJvZFzUE5uVV20uXi2OV7tR7fTpw/uY" +
                                                    "gMlIJdPF7yv4qImM1Dq3raIjx7cIRirsD9F4/Vef9WMW8QMM+exwTeXk4U1v" +
                                                    "i9vo1I8kqiKkMpZQVfetvKtebpg0pnDuq8QdPb+4DOwDaN7P4YyU4j8EGHhY" +
                                                    "DHuUkVGuYYDUrrkHfRs0BYOwesDIwbj42pAkvCt1cWRkPGV8NcVrYv7Dn9SV" +
                                                    "bkL89KdXPje8Zt2eG0ue4/fDZbIq7eZ3V5URUiE+GPNF8Vp4Zt7VUmuVr577" +
                                                    "2dgXqmanLqbGIqlz2Um9o1+S+B9AA4J0ZiUAAA==");
}
