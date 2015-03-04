package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class BlackmanHarrisFilter implements Filter {
    private float s;
    private float inv;
    public BlackmanHarrisFilter(float size) { super();
                                              s = size;
                                              inv = 1.0F / (s * 0.5F); }
    public float getSize() { return s; }
    public float get(float x, float y) { return bh1d(x * inv) * bh1d(y * inv);
    }
    private float bh1d(float x) { if (x < -1.0F || x > 1.0F) return 0.0F;
                                  x = (x + 1) * 0.5F;
                                  final double A0 = 0.35875;
                                  final double A1 = -0.48829;
                                  final double A2 = 0.14128;
                                  final double A3 = -0.01168;
                                  return (float) (A0 + A1 * Math.cos(2 * Math.
                                                                           PI *
                                                                       x) +
                                                    A2 *
                                                    Math.
                                                    cos(
                                                      4 *
                                                        Math.
                                                          PI *
                                                        x) +
                                                    A3 *
                                                    Math.
                                                    cos(
                                                      6 *
                                                        Math.
                                                          PI *
                                                        x)); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YWWwcRRDtHdvrI07sOHFiQrAT4yBysEOIAIFRIFnZxGEh" +
                                                    "VhyCWA6nPdO7nmR2ZjLTa28czBEJJeIjQmAgIPAHCuIKBCEiQAgpP1yCHxAC" +
                                                    "8cEhfkBAPvLBIe6q7tmZ2dm1gR8sdW9Pd1VXVVfV62qfOEMaPJesd2zzQN60" +
                                                    "eYqVeGqveWmKH3CYl9qeuXSYuh7T0yb1vF0wN6r1vtT202/3j7crJJklS6hl" +
                                                    "2Zxyw7a8ncyzzQmmZ0hbODtgsoLHSXtmL52gapEbppoxPN6fIQsirJz0Zcoq" +
                                                    "qKCCCiqoQgV1S0gFTAuZVSykkYNa3NtP7iSJDEk6GqrHyerKTRzq0oK/zbCw" +
                                                    "AHZowu/dYJRgLrlkVWC7tLnK4IfWqzOP3N7+ch1py5I2wxpBdTRQgoOQLGkt" +
                                                    "sMIYc70tus70LFlsMaaPMNegpjEl9M6SDs/IW5QXXRYcEk4WHeYKmeHJtWpo" +
                                                    "m1vUuO0G5uUMZurlr4acSfNg67LQVmnhIM6DgS0GKObmqMbKLPX7DEvnpCfO" +
                                                    "EdjYdx0QAGtjgfFxOxBVb1GYIB3Sdya18uoIdw0rD6QNdhGkcLJizk3xrB2q" +
                                                    "7aN5NspJV5xuWC4BVbM4CGThpDNOJnYCL62IeSninzM3XHX0oLXNUoTOOtNM" +
                                                    "1L8JmLpjTDtZjrnM0phkbF2XeZgue/OIQggQd8aIJc2rd5y9ZkP36Xclzbk1" +
                                                    "aHaM7WUaH9WOjy36cGV67RV1qEaTY3sGOr/CchH+w/5Kf8mBzFsW7IiLqfLi" +
                                                    "6Z1v33z3c+x7hbQMkaRmm8UCxNFizS44hsnca5nFXMqZPkSamaWnxfoQaYRx" +
                                                    "xrCYnN2Ry3mMD5F6U0wlbfENR5SDLfCIGmFsWDm7PHYoHxfjkkMIaYRGNkFr" +
                                                    "IPJP/HKyRx23C0ylGrUMy1Yhdhl1tXGVabbq0YJjgte8opUz7UnVczXVdvPB" +
                                                    "t2a7TAXxEDrqVhMioECtbdR1DW9QTKYw0pz/QUYJ7WyfTCTABSvjAABMbJtt" +
                                                    "6swd1WaKWwfOvjj6vhIkhH9CnGwAqSlfagqlpqTUVC2pJJEQwpaidOlr8NQ+" +
                                                    "yHlAw9a1I7dt33Oktw6CzJmsh2NG0l6w1ldpQLPTITAMCfjTIDq7nrzlcOqX" +
                                                    "p6+W0anOjeI1ucnpY5P37L7rYoUolXCMJsJUC7IPI4gGYNkXT8Na+7Yd/van" +
                                                    "kw9P22FCVuC7jxPVnJjnvXFnuLbGdEDOcPt1q+ip0Ten+xRSD+ABgMkpBDhg" +
                                                    "UXdcRkW+95exE21pAINztlugJi6VAa+Fj7v2ZDgjomSRGC8GpyzABOiB1uRn" +
                                                    "hPjF1SUO9ktlVKGXY1YIbB58/fSjpx5bf4UShfG2yMU4wrgEhcVhkOxyGYP5" +
                                                    "z48NP/jQmcO3iAgBivNrCejDPg0QAS6DY7333f2fffnF8Y+VMKo43JXFMdPQ" +
                                                    "SrDHBaEUABATQAx933ejVbB1I2fQMZNhcP7etmbjqR+OtktvmjBTDoYN/7xB" +
                                                    "OH/OVnL3+7f/3C22SWh4gYWWh2TyAJaEO29xXXoA9Sjd89F5j75DnwB8BUzz" +
                                                    "jCkmYCoR5MvaeYoY1ygArk74wK9Od3y57/FvX5BpE78lYsTsyMx9f6WOziiR" +
                                                    "q/T8qtssyiOvUxEMC2Xw/AV/CWh/YsOgwQkJpx1pH9NXBaDuOOie1fOpJUQM" +
                                                    "fnNy+o1npg9LMzoqb5IBKJRe+OSPD1LHvnqvBnhB/NmUCx1VoeM60adQKXGi" +
                                                    "RKz1Y7fKqVoriZku8aXMf/aDWL1EIOvXHebYoa9/ETpVgU4Nd8T4s+qJx1ek" +
                                                    "N38v+MPsR+6eUjWYQ6UX8l7yXOFHpTf5lkIas6Rd88vI3dQsYo5loXTyyrUl" +
                                                    "lJoV65VlkLzz+wN0WxmPh4jYOO6EfoAxUuO4JQY1rXjK50BL+lCTjENNgohB" +
                                                    "WrD0in4NdheWM73RcY0JijUqZAeubBLoJH25+b9LGsJugJM6w5qIbCcU74uE" +
                                                    "gxTWycnyqvtRXoUY3OfNVc6JwD5+aGZW3/HURsWPwss5aea2c5HJJpgZEVWH" +
                                                    "O1XckteLAjb0+H3PPv8q/3D9lTJF1s0dpXHGdw59t2LX5vE9/+Fu7InZFN/y" +
                                                    "2etPvHftBdoDCqkLAqeqJq9k6q8MlxaXwSPC2lURNN2BK7GRLmgLfVcurHk/" +
                                                    "hQ6rnfPZedZuxe4mCK484yMAwSIQquFBTIxUKra0PCj//ivFFAkxQcBtEqRj" +
                                                    "86ioYwcvjjpQ8d+p14GTy6B1+up1zqneQEywf/uEmu2dRzPxLslzUj82vlGf" +
                                                    "QzXI1qW16ke8FbuqXqrydaW9ONvWtHz2xk9FRRS8gJrhGZIrmmYUbyLjpOOy" +
                                                    "nCEUa5boI3N5f63MlZUtx7coDoTajqTn8LqP04ON+BMlm+RkQYQMQsgfRYmm" +
                                                    "wG1AhMODThlH2kUxgLibkrhbIhEMwHoo+lVRHGGai/8ClFOyKP8PMKqdnN1+" +
                                                    "w8Gzlz0l8rtBM+nUlHg1wiNY1oVBWq+ec7fyXslta39b9FLzmjJcLcKuwy8G" +
                                                    "Y7r11K6ZBgoOF1XO1GvLX7nq6dkvRNH2N0JpVVueEQAA");
}
