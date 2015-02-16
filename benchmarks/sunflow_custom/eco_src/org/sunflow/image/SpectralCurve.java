package org.sunflow.image;
abstract public class SpectralCurve {
    abstract public float sample(float lambda);
    final private static int WAVELENGTH_MIN = 360;
    final private static int WAVELENGTH_MAX = 830;
    final private static double[] CIE_xbar = { 1.299E-4, 2.321E-4, 4.149E-4,
    7.416E-4,
    0.001368,
    0.002236,
    0.004243,
    0.00765,
    0.01431,
    0.02319,
    0.04351,
    0.07763,
    0.13438,
    0.21477,
    0.2839,
    0.3285,
    0.34828,
    0.34806,
    0.3362,
    0.3187,
    0.2908,
    0.2511,
    0.19536,
    0.1421,
    0.09564,
    0.05795001,
    0.03201,
    0.0147,
    0.0049,
    0.0024,
    0.0093,
    0.0291,
    0.06327,
    0.1096,
    0.1655,
    0.2257499,
    0.2904,
    0.3597,
    0.4334499,
    0.5120501,
    0.5945,
    0.6784,
    0.7621,
    0.8425,
    0.9163,
    0.9786,
    1.0263,
    1.0567,
    1.0622,
    1.0456,
    1.0026,
    0.9384,
    0.8544499,
    0.7514,
    0.6424,
    0.5419,
    0.4479,
    0.3608,
    0.2835,
    0.2187,
    0.1649,
    0.1212,
    0.0874,
    0.0636,
    0.04677,
    0.0329,
    0.0227,
    0.01584,
    0.01135916,
    0.008110916,
    0.005790346,
    0.004106457,
    0.002899327,
    0.00204919,
    0.001439971,
    9.999493E-4,
    6.900786E-4,
    4.760213E-4,
    3.323011E-4,
    2.348261E-4,
    1.661505E-4,
    1.17413E-4,
    8.307527E-5,
    5.870652E-5,
    4.150994E-5,
    2.935326E-5,
    2.067383E-5,
    1.455977E-5,
    1.025398E-5,
    7.221456E-6,
    5.085868E-6,
    3.581652E-6,
    2.522525E-6,
    1.776509E-6,
    1.251141E-6 };
    final private static double[] CIE_ybar = { 3.917E-6, 6.965E-6, 1.239E-5,
    2.202E-5,
    3.9E-5,
    6.4E-5,
    1.2E-4,
    2.17E-4,
    3.96E-4,
    6.4E-4,
    0.00121,
    0.00218,
    0.004,
    0.0073,
    0.0116,
    0.01684,
    0.023,
    0.0298,
    0.038,
    0.048,
    0.06,
    0.0739,
    0.09098,
    0.1126,
    0.13902,
    0.1693,
    0.20802,
    0.2586,
    0.323,
    0.4073,
    0.503,
    0.6082,
    0.71,
    0.7932,
    0.862,
    0.9148501,
    0.954,
    0.9803,
    0.9949501,
    1.0,
    0.995,
    0.9786,
    0.952,
    0.9154,
    0.87,
    0.8163,
    0.757,
    0.6949,
    0.631,
    0.5668,
    0.503,
    0.4412,
    0.381,
    0.321,
    0.265,
    0.217,
    0.175,
    0.1382,
    0.107,
    0.0816,
    0.061,
    0.04458,
    0.032,
    0.0232,
    0.017,
    0.01192,
    0.00821,
    0.005723,
    0.004102,
    0.002929,
    0.002091,
    0.001484,
    0.001047,
    7.4E-4,
    5.2E-4,
    3.611E-4,
    2.492E-4,
    1.719E-4,
    1.2E-4,
    8.48E-5,
    6.0E-5,
    4.24E-5,
    3.0E-5,
    2.12E-5,
    1.499E-5,
    1.06E-5,
    7.4657E-6,
    5.2578E-6,
    3.7029E-6,
    2.6078E-6,
    1.8366E-6,
    1.2934E-6,
    9.1093E-7,
    6.4153E-7,
    4.5181E-7 };
    final private static double[] CIE_zbar = { 6.061E-4, 0.001086, 0.001946,
    0.003486,
    0.006450001,
    0.01054999,
    0.02005001,
    0.03621,
    0.06785001,
    0.1102,
    0.2074,
    0.3713,
    0.6456,
    1.0390501,
    1.3856,
    1.62296,
    1.74706,
    1.7826,
    1.77211,
    1.7441,
    1.6692,
    1.5281,
    1.28764,
    1.0419,
    0.8129501,
    0.6162,
    0.46518,
    0.3533,
    0.272,
    0.2123,
    0.1582,
    0.1117,
    0.07824999,
    0.05725001,
    0.04216,
    0.02984,
    0.0203,
    0.0134,
    0.008749999,
    0.005749999,
    0.0039,
    0.002749999,
    0.0021,
    0.0018,
    0.001650001,
    0.0014,
    0.0011,
    0.001,
    8.0E-4,
    6.0E-4,
    3.4E-4,
    2.4E-4,
    1.9E-4,
    1.0E-4,
    4.999999E-5,
    3.0E-5,
    2.0E-5,
    1.0E-5,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0 };
    final private static int WAVELENGTH_STEP = (WAVELENGTH_MAX - WAVELENGTH_MIN) /
      (CIE_xbar.
         length -
         1);
    static { if (WAVELENGTH_STEP * (CIE_xbar.length - 1) != WAVELENGTH_MAX -
                   WAVELENGTH_MIN) { String err = String.format(("Internal error - spectrum static data is inconsistent!\n  * " +
                                                                 "min = %d\n  * max = %d\n  * step = %d\n  * num = %d"),
                                                                WAVELENGTH_MIN,
                                                                WAVELENGTH_MAX,
                                                                WAVELENGTH_STEP,
                                                                CIE_xbar.
                                                                  length);
                                     throw new RuntimeException(
                                       err); } }
    final public XYZColor toXYZ() { float X = 0;
                                    float Y = 0;
                                    float Z = 0;
                                    for (int i = 0,
                                           w =
                                             WAVELENGTH_MIN;
                                         i <
                                           CIE_xbar.
                                             length;
                                         i++,
                                           w +=
                                             WAVELENGTH_STEP) {
                                        float s =
                                          this.
                                          sample(
                                            w);
                                        X +=
                                          s *
                                            CIE_xbar[i];
                                        Y +=
                                          s *
                                            CIE_ybar[i];
                                        Z +=
                                          s *
                                            CIE_zbar[i];
                                    }
                                    return new XYZColor(
                                      X,
                                      Y,
                                      Z).
                                      mul(
                                        WAVELENGTH_STEP);
    }
    public SpectralCurve() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1168792010000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALUZaWwU1/l517cNPrgJYGxMCNdubCABHBVcH7Cw2K5tDJjD" +
       "eTv7dj0wOzOZebus\nHURDkIAGpQ1torZSA7Si4igkSGlLKxEKzdE0tBJBai" +
       "JRhSZBaiu1qRpVSqjaH/3eezO7s7O7BqPG\n0r55847vPsfnP0VFpoFmSaaP" +
       "jujE9LX19WDDJOE2BZtmPywNSW8VlfWc3qhqHlQQRB45TFFVUDL9\nYUyxXw" +
       "77A+0tSQMt1jVlJKpo1EeS1LdbWWHB2xBckQVwy/FLtQdOFdZ5UFEQVWFV1S" +
       "imsqZ2KCRm\nUlQd3I0T2B+nsuIPyiZtCaIJRI3H2jTVpFil5lNoP/IGUbEu" +
       "MZgU1Qdt5H5A7texgWN+jt7fw9EC\nhEkGoVhWSbg1hQ5uLsm8CWRb93qzTw" +
       "OQUrY5AOxwCoDruSmuBbdZrOreMwOP7Tt51ouqBlGVrPYx\nYBJwQgHfIKqM" +
       "kViIGGZrOEzCg6hGJSTcRwwZK/IoxzqIak05qmIaN4jZS0xNSbCDtWZcJwbH" +
       "aS8G\nUaXEeDLiEtWMlIwiMlHC9ltRRMFRYHtqmm3BbidbBwbLZSDMiGCJ2F" +
       "cK98gqaLzOfSPFY+NGOABX\nS2KEDmspVIUqhgVUK3SpYDXq76OGrEbhaJEW" +
       "BywUzcwLlMlax9IeHCVDFE13n+sRW3CqjAuCXaFo\nivsYhwRamunSkkM/i6" +
       "d+fuTMD66s5bZdGCaSwugvh0tzXJd6SYQYRJWIuHg37nsxsC0+y4MQHJ7i\n" +
       "OizOtM6/tDn411/ViTMP5TjTHdpNJDokdR2r6316nYa8jIxSXTNlpvwMzrk7" +
       "9Fg7LUkdvHZqCiLb\n9NmbV3vf3vbMOfI3DyoPoGJJU+IxsKMaSYvpskKMdU" +
       "QlBqYkHEBlRA238f0AKoF5EExerHZHIiah\nAVSo8KVijb+DiCIAgomoDOay" +
       "GtHsuY7pMJ8ndYRQNfzQFPgVI/HHnxQ1+/xmXI0o2l6/aUh+zYim\n3uUYaN" +
       "Tfp4M8DKy0xY0E8THb0Snq8g9rMeLHElZlVfNHZfBWSVsaJgn2HDfEJKOzdm" +
       "9BAQt8bgdW\nwPbXa0qYGEPS6Tvv7uvY+I0jwjiYQVscgj8AIp+FyMcR+TIQ" +
       "oYICDn8yQyjUA8LdA24KAa1yYd/O\nDU8eafCCXeh7C0Ey7GgD8GJR0SFpbW" +
       "lfDvCwJ4FBTf/R9sO+u6fXCIPy5w+5OW9X3Lhw/eS/Khd5\nkCd3PGTcQUQu" +
       "Z2B6WBBNxblGtwflgv+P5za99v71Dx9J+xJFjVkunn2TuWiDWw+GJpEwBL00" +
       "+FMz\nqrxb0MAxDyoEv4dYx+mHMDLHjSPDVVvssMd4KQmiiohmxLDCtuxYVU" +
       "6HDW1veoUbSDUbJgtbYYp0\nEcgj5t2DxY9+cLniLc6xHVyrHOmrj1DhqjVp" +
       "O+g3CIH1D7/X852XPj28nRuBZQUUclo8pMhSEq48\nnL4CPqqAcTEdNW5WY1" +
       "pYjsg4pBBmTP+tmt/0s79/s1pIXYEVW2lL7g0gvT7jq+iZ67u+mMPBFEgs\n" +
       "R6TZSB8T3ExKQ241DDzC6EgeuDn7+7/BL0MIg7BhyqOERwLEOUNcjj4u3oV8" +
       "XOrae5QNDQB7SR6r\nzpGRh6R956IN8ad++0tOdQV2pnanGjZhvUUoleOeBE" +
       "gbkTVkRCi2O0Vn41Smgmlu712PzWEAtvxq\n145q5ep/AO0goJUgXZrdBsSM" +
       "ZIamrdNFJbeuvTH1yfe8yNOJyhUNhzsxt39UBoZHzGEIN0l9zVpO\nRvXeUj" +
       "t+JhGndqYlpaTjrRiIW5jf/TtZ0k97zlBoyZngu90vcynldfwcOc8FZ/TK5u" +
       "N3f09vczhp\nD2S365PZsRQKpfTdle8naoovnoh5UMkgqpasUm4AK3HmDINQ" +
       "eZh2fQflXsZ+ZhUhUmZLKsLMcnu/\nA63b99MxHObsNJtXuixjBpN2s60E++" +
       "m0DB7ZhZ4ZSb4AFE1RYtR+cvLUFwcOr/QwDyhKMNJBKtXp\nc11xVuwdOv/S" +
       "7IoXPzrKgjh4SAFPm2s4+nl8fFhEAy8F4mUVA48luiEnIGVDfDB5AZmkaOKW" +
       "1oGO\nYEfXuv71Q5sCXUDRdGcRb8gxKAYSPATeOdTw+jubTxwWaWMMu8m4NS" +
       "R9/U8f7fF+61pI3HMbh+vw\nsTmn/vzand7JIg6JqnFeVuHmvCMqRy79Kp25" +
       "W/1YGPjpNxfXn9/fe9uiqDaz/umAHuEvI2+QBU88\n/3GOlO2F2lZEczY2sW" +
       "Gt8KkVeX1vdbZV1FhWUZPDKtikFTT3FTbZnEOlbN7JhnVsWO9WY+tWttzl\n" +
       "onJgnFTOgd8ii8raLCoRn+y8L+JK2wIdQ8kQNDWQAB3WxYM+U8rZF9on9a7a" +
       "fpCXFGXQtWCzK+1i\n0CuyWQGoan5+o0sBG5IW7Lz0z2tXyAIeV0tlE1y51Y" +
       "jmqOAddz7D58imDyLHeVlQGMKmcGp365Pd\n2WQ0LNwKJ+pC0husZzf4W1iD" +
       "hExSKavAKte42HVbmruzpelJSfMREGSxQtSoKJC5domFgYH0iPP8\nfRq1ci" +
       "uPF22KphJso4c9UU3Kmi/VI8JmMos4A83OqCU3cUbTsfi5sz+5RN9bvFp40a" +
       "L8qnFfXLT6\n5Gjd6lePPkAFWefSoBt0TeKhr3mH5Xc8vHkUoT2r6cy81JIZ" +
       "0MuBnrih9meE9bk6f5BsBRUI3YxR\nkuwbY28/G0CxRRLTkVApyL0ud8nVEd" +
       "MpL5JGfzHtp0+cPn6bx/4kNEQV3GeampofX94E92vBydh3\nFZ8ctpJv+83O" +
       "0LmIei7MBVPObbqV3bG4LuMrDqfzarrJGkfHFxoLUmO3brJae4IDSaB938UN" +
       "laU/\nPHqIw7c8tszRhFrvJQlsdDnDqaC8+bFVK5tXUbT1/9uirW5qWrakuW" +
       "kpA91yj1bLZwepBeZcio0o\noXNTS4zaZx1xbD80zdkiZoJB6YAJIpqYdkKW" +
       "VZybwHphb0druytI73qAIN1mgV2cJ0gfu/8gPRKy\nvkqFhMFHdF3PNK9mvn" +
       "3Qqbhly74MxS0fl+JGshU3Yinuuw7e2PvzLpF/+wFEvsMSeXsekZ+4f5GP\n" +
       "3lPky3KI/PEvQ+QrxiXy0WyRj1oi/rGDt1wiPzlOkcvw22WJfFcekV+4L5FX" +
       "Oeqkvv6OnlyF0itj\nUJfMkaKBWRwyQTgSdSQDu99iaTTfh0NejB7e+lnlIf" +
       "zmTo+VE3ooNHSavlQhCaI4QInUTlOSmczA\n++A30ZLMRLdk0nxl1xQ/z5m3" +
       "nHWJo3yB7kfDlMO7NkY2e5sNl1l3gWO6+L7367RYX7+X0lMSy+ST\nV8j18L" +
       "PjZ2w8fHbeMz/fGGPvJhuugwCotnXboF0/zcx2D9iFTG19MOfs/u5+2QWrnJ" +
       "DhXawCmJ71\nHwHxFVsK3np6x+fBP/xb1Kn2l+YKyLSRuKI4G1PHvFg3SETm" +
       "HFWINlXEmj9SVJPFC3DLn5zIW+Lg\nbQg+joPQSloz56GPoTmCQ2z6iW7Lyt" +
       "G3ipY70zsYr/MyKkf+bxe7uouLf7wMSVsvbJ+bPNr/Ai8Z\noVrCo6MMTDlU" +
       "FOJDXKpCrM8LzYZ1A118deDyK6tsh8v4RJdliE1iN78y2cYB/X/V+4BAAhsA" +
       "AA==");
}
