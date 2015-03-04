package org.sunflow.image;
final public class ChromaticitySpectrum extends SpectralCurve {
    final private static float[] S0Amplitudes = { 0.04F, 6.0F, 29.6F, 55.3F,
    57.3F,
    61.8F,
    61.5F,
    68.8F,
    63.4F,
    65.8F,
    94.8F,
    104.8F,
    105.9F,
    96.8F,
    113.9F,
    125.6F,
    125.5F,
    121.3F,
    121.3F,
    113.5F,
    113.1F,
    110.8F,
    106.5F,
    108.8F,
    105.3F,
    104.4F,
    100.0F,
    96.0F,
    95.1F,
    89.1F,
    90.5F,
    90.3F,
    88.4F,
    84.0F,
    85.1F,
    81.9F,
    82.6F,
    84.9F,
    81.3F,
    71.9F,
    74.3F,
    76.4F,
    63.3F,
    71.7F,
    77.0F,
    65.2F,
    47.7F,
    68.6F,
    65.0F,
    66.0F,
    61.0F,
    53.3F,
    58.9F,
    61.9F };
    final private static float[] S1Amplitudes = { 0.02F, 4.5F, 22.4F, 42.0F,
    40.6F,
    41.6F,
    38.0F,
    42.4F,
    38.5F,
    35.0F,
    43.4F,
    46.3F,
    43.9F,
    37.1F,
    36.7F,
    35.9F,
    32.6F,
    27.9F,
    24.3F,
    20.1F,
    16.2F,
    13.2F,
    8.6F,
    6.1F,
    4.2F,
    1.9F,
    0.0F,
    -1.6F,
    -3.5F,
    -3.5F,
    -5.8F,
    -7.2F,
    -8.6F,
    -9.5F,
    -10.9F,
    -10.7F,
    -12.0F,
    -14.0F,
    -13.6F,
    -12.0F,
    -13.3F,
    -12.9F,
    -10.6F,
    -11.6F,
    -12.2F,
    -10.2F,
    -7.8F,
    -11.2F,
    -10.4F,
    -10.6F,
    -9.7F,
    -8.3F,
    -9.3F,
    -9.8F };
    final private static float[] S2Amplitudes = { 0.0F, 2.0F, 4.0F, 8.5F,
    7.8F,
    6.7F,
    5.3F,
    6.1F,
    3.0F,
    1.2F,
    -1.1F,
    -0.5F,
    -0.7F,
    -1.2F,
    -2.6F,
    -2.9F,
    -2.8F,
    -2.6F,
    -2.6F,
    -1.8F,
    -1.5F,
    -1.3F,
    -1.2F,
    -1.0F,
    -0.5F,
    -0.3F,
    0.0F,
    0.2F,
    0.5F,
    2.1F,
    3.2F,
    4.1F,
    4.7F,
    5.1F,
    6.7F,
    7.3F,
    8.6F,
    9.8F,
    10.2F,
    8.3F,
    9.6F,
    8.5F,
    7.0F,
    7.6F,
    8.0F,
    6.7F,
    5.2F,
    7.4F,
    6.8F,
    7.0F,
    6.4F,
    5.5F,
    6.1F,
    6.5F };
    final private static RegularSpectralCurve kS0Spectrum = new RegularSpectralCurve(
      S0Amplitudes,
      300,
      830);
    final private static RegularSpectralCurve kS1Spectrum = new RegularSpectralCurve(
      S1Amplitudes,
      300,
      830);
    final private static RegularSpectralCurve kS2Spectrum =
      new RegularSpectralCurve(
      S2Amplitudes,
      300,
      830);
    final private static XYZColor S0xyz = kS0Spectrum.toXYZ();
    final private static XYZColor S1xyz = kS1Spectrum.toXYZ();
    final private static XYZColor S2xyz = kS2Spectrum.toXYZ();
    final private float M1;
    final private float M2;
    public ChromaticitySpectrum(float x, float y) { super();
                                                    M1 = (-1.3515F -
                                                            1.7703F *
                                                            x +
                                                            5.9114F *
                                                            y) /
                                                           (0.0241F +
                                                              0.2562F *
                                                              x -
                                                              0.7341F *
                                                              y);
                                                    M2 = (0.03F -
                                                            31.4424F *
                                                            x +
                                                            30.0717F *
                                                            y) /
                                                           (0.0241F +
                                                              0.2562F *
                                                              x -
                                                              0.7341F *
                                                              y);
    }
    public float sample(float lambda) { return kS0Spectrum.
                                          sample(
                                            lambda) +
                                          M1 *
                                          kS1Spectrum.
                                          sample(
                                            lambda) +
                                          M2 *
                                          kS2Spectrum.
                                          sample(
                                            lambda); }
    final public static XYZColor get(float x, float y) { float M1 =
                                                           (-1.3515F -
                                                              1.7703F *
                                                              x +
                                                              5.9114F *
                                                              y) /
                                                           (0.0241F +
                                                              0.2562F *
                                                              x -
                                                              0.7341F *
                                                              y);
                                                         float M2 =
                                                           (0.03F -
                                                              31.4424F *
                                                              x +
                                                              30.0717F *
                                                              y) /
                                                           (0.0241F +
                                                              0.2562F *
                                                              x -
                                                              0.7341F *
                                                              y);
                                                         float X =
                                                           S0xyz.
                                                           getX() +
                                                           M1 *
                                                           S1xyz.
                                                           getX() +
                                                           M2 *
                                                           S2xyz.
                                                           getX();
                                                         float Y =
                                                           S0xyz.
                                                           getY() +
                                                           M1 *
                                                           S1xyz.
                                                           getY() +
                                                           M2 *
                                                           S2xyz.
                                                           getY();
                                                         float Z =
                                                           S0xyz.
                                                           getZ() +
                                                           M1 *
                                                           S1xyz.
                                                           getZ() +
                                                           M2 *
                                                           S2xyz.
                                                           getZ();
                                                         return new XYZColor(
                                                           X,
                                                           Y,
                                                           Z);
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1167346356000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVZeWwU1xl/3vVtgw/AHAF8YHC5dvFiTqdJHGOCYQ2ujQmY" +
                                                   "EOd55u16YHZmmHlr\nrx1EiCIBCWobeki9QmiLxFFokNKUViIUlKSloVFDpI" +
                                                   "KUCtoKNW3VUDWqlFK1f/R7b2Z3jt11Fius\nNG9n3/F93++73vfenr2LCgwd" +
                                                   "zRaMAB3ViBFo7+3GukHEdhkbxlboGhDeKSjpPrlJUX0oL4x8kkhR\nRVgwgi" +
                                                   "KmOCiJwc51rQkdLdZUeTQqqzRAEjSwW15h0dsYXpFG8MljF6qfP5Ff60MFYV" +
                                                   "SBFUWlmEqq\n0iGTmEFRZXg3HsbBOJXkYFgyaGsYTSJKPNauKgbFCjX2ov3I" +
                                                   "H0aFmsBoUlQfTjIPAvOghnUcC3L2\nwW7OFihM0QnFkkLEthQ7WLnEvRLEtt" +
                                                   "b1pM8GIsVscBvA4RIA6roUahNtGlTNf2rbyn3HT/tRRT+q\nkJReRkwAJBT4" +
                                                   "9aPyGIkNEt1oE0Ui9qMqhRCxl+gSlqUxzrUfVRtSVME0rhOjhxiqPMwmVhtx" +
                                                   "jeic\nZ7IzjMoFhkmPC1TVUzqKSEQWk78KIjKOAuwaG7YJdz3rB4ClEgimR7" +
                                                   "BAkkvy90gKWLzWuyKFsXET\nTIClRTFCh9QUq3wFQweqNm0pYyUa7KW6pERh" +
                                                   "aoEaBy4UzcpKlOlaw8IeHCUDFM3wzus2h2BWCVcE\nW0LRNO80TgmsNMtjJY" +
                                                   "d9Ftd8evjU9y49xn07XySCzOQvhUVzPYt6SIToRBGIufBePPCNzh3x2T6E\n" +
                                                   "YPI0z2RzTtv8C33hv/6i1pzzUIY5WwZ3E4EOCJuP1vY8+4SK/EyMYk01JGZ8" +
                                                   "F3IeDt3WSGtCg6it\nSVFkg4Hk4OWeX+44cIb83YdKO1GhoMrxGPhRlaDGNE" +
                                                   "km+hNEITqmROxEJUQR2/l4JyqC9zC4vNm7\nJRIxCO1E+TLvKlT5b1BRBEgw" +
                                                   "FZXAu6RE1OS7hukQf09oCKFJ8KBqeIqQ+eHfFK0JBI24EpHVkaCh\nC0FVj6" +
                                                   "Z+SzGwaLB9SFdjLEQkOtqrgW70eCzAXEijqC84pMZIEAtYkRQ1GJUgaAV1qU" +
                                                   "iG2fdECSeY\n1NUjeXksDXrDWYZI2KDKItEHhJN33t3XsenFw6arMPe28FK0" +
                                                   "APgFLH4Bzi+QiR/Ky+NspjK+ps1A\n43sgdiHLlS/s3bXxmcMNfnAWbSQf1M" +
                                                   "WmNgAyS5gOQW23A7yT50IBvGzGD3YeCtw7+ajpZcHseTjj\n6rL3z107/q/y" +
                                                   "RT7ky5wkGUhI06WMTDfLrKnk1+gNq0z0//FS1+s3rt36gh1gFDWmxX36Sha3" +
                                                   "DV5z\n6KpARMiENvkTMyv8T6JtR30oH5IBJEAuP+SWuV4ervhtTeZChqUojM" +
                                                   "oiqh7DMhtKJrBSChYcsXu4\nn1Ty9ylgnDLm0LXwLLQ8nH+z0Wkaa2tMv2LW" +
                                                   "9qDgufbeC4XLbl4se4erJZmWKxwbXy+hZpBX2c6y\nVScE+m99q/vr37x7aC" +
                                                   "f3FMtVKOyG8UFZEhKwZIG9BKJbBudjhmzsU2KqKEUkPCgT5nH/q5jf/MbH\n" +
                                                   "X6k0TSNDT9KySz6bgN0/83F04NrT/57LyeQJbHexYdjTTDRTbMptuo5HmRyJ" +
                                                   "5z+Y8+1f4Vcg+UHC\nMaQxwnOIjyPzwaIZzupEl2KQ5Ya5Ge8cbHjzat+rh0" +
                                                   "zXXzhOCeJcNSA894c/7vF/9cqguc6b6T2T\nj8498dHrd3qmmmoyt8N5aTuS" +
                                                   "c425JXIHqNCYQerH48Bnv724/uz+ntuWRNXuxN4Bxc9fRt8iTQ9/\n+U8Zsg" +
                                                   "+4j4q57Vo4zyB30kW8DTCv5IpEfGwtaxpApCVZlJWh8BkQ9p2JNsT3/vrnnH" +
                                                   "kZdlZQTp/t\nwpqJupI18xjy6d5UtwEbQzCv5fLmpyrly/8Fiv1AUYCCw9ii" +
                                                   "Q55NuDzeml1Q9OGVt2qeue5HvvWo\nFNCK6zFPFqgEopQYQ5CiE9qjj/FArB" +
                                                   "wpZi2HjLgSZlkKSDh+lRnjOsx6VjbZaWZgcMmp8LtbXuEK\nyJolM/iSh87Y" +
                                                   "pb5j996jtzkdO12x1fWJ9P0HSk177eobw1WF51+N+VBRP6oUrGJ4G5bjLCn0" +
                                                   "Q+1m\nJCtkKJhd4+46zCw6WlPpeLbXmR1svYnS9jx4Z7PZe7knN85k2n4Ens" +
                                                   "lWbiz25sY8xF828CWNvG0y\nM5mf+bOkYJCrSNOlYShUILcZvGxOUFTeu6wt" +
                                                   "pskSjYvgMZBcHamBJxQWUadfXjelZ83OF/ieVgK1\nNDY222LDCYa95YG+52" +
                                                   "d3gBSxAaFp14V/XrlEmrivFksGqKdNj2aoKx1rPsFnSNfNyDG+L+UPYsNU\n" +
                                                   "lLcgT6+3XWU01+tkjX+1aJoGFVQZh9PcHFq1PAT4qwE/O4gFJNHytXUfrB88" +
                                                   "E1HOiD7OgLNrY2ss\n+CW8x6EPv6oZrNJ0HOksSo1bNIPtw5McTDrX7Tu/sb" +
                                                   "z4+0cOcvqWMkscVav1u2gY65udacqUPLQq\ntHrFCoqefiDF3NrlzUtWr1i6" +
                                                   "ZhVFG3OryQJOh2oy6ijWo4TWuboZhK0e39sP5Xe67pnGLJ9n5S/o\nbjJPZi" +
                                                   "zueBp3DoJO8ns62taZhQJrW1nTaaapR7Kms3Z3oK2Bp8YiW5kl0IQMgcbew6" +
                                                   "zpYs1mHlzN\njuBivd3ZfG85H+5zWnXlsgdn1dB9W7U5s1Wb3VaVPKBZ34DH" +
                                                   "HuJ92uOL8Myx7DEjiz325miPUK72\naMlgjwcYZcvv2x6hzPYIuXU/4gGdyR" +
                                                   "76fdojZhXqyYI9kz2ey8keZXt6lyUR8anTMx79ekg0LmPd\nnInl9rg+TDwY" +
                                                   "DkwAQ52FoS4LhsO5Ymj+fDC8OAEM9RaG+iwYjuaKIfT5YPjafWJgRc08C8O8" +
                                                   "LBi+\nkxOGgt5lidGxpPSz0qXfvqMfDk+q7pH4uxOQuNGSuDGLxD/MUeLmCU" +
                                                   "l8YgISz7cknp9F4rM5Shya\nkMTncpe4OplVmiyJm7JI/Ea6xD5bYhDW19XM" +
                                                   "U7tHlp8+AFkusuYCYxnKxPLNcVgmxoOx0HnAQuz4\nNyfbzSs/9B7a/kn5Qf" +
                                                   "z2Lp91OF1J4TynaktlMkxkByl2BzDHdRHWxYtk+2z00ukfXaDXF681j8+L\n" +
                                                   "spf13oWL1h4fq1372pEJXH/VerB5SVcNP/Ql/5B0ldfJ1lEr7RrdvajVfcAq" +
                                                   "BXniurLVdcyqc19B\nsQgJWPYOeO3NDcuaBZ4rgTzz1ohbn8/67Th3BtdZ8x" +
                                                   "t2CsOwMXNRWmxvee+zHDTlDuzH1ZT0vDRa\nCU/Ikj6UUfrxAn2hGege0X22" +
                                                   "V7bYAH8/DsBbrLlBkR9qkhyyhY39Zq7YIb6nZqqL2JXYjLT/kMz/\nPYTwh8" +
                                                   "8+9Wn4d/8xz5DJ/ybK4KgVicuy8yDueC/UdBKROKwy81hulowfUVSVBglSJP" +
                                                   "/msv7ZnPg3\n2F8dE+EYbr05J30MyoJJ7PWullRZbbrKXHuvOzsw6PNcgcr/" +
                                                   "t0sGU9z8525A2H5uZ13iyNaXeYQW\nCDIeG2NkSuGEaV7apgKyPiu1JK330f" +
                                                   "nXtl388ZpkwuH3VFMdjuby1VZzdBwTQxLIfFPaEdMov9sc\n+9n0nzx88tht" +
                                                   "H7+r/T9C0rrtbh0AAA==");
}
