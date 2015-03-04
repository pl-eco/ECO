package org.sunflow.image;
public final class ChromaticitySpectrum extends SpectralCurve {
    private static final float[] S0Amplitudes = { 0.04F, 6.0F, 29.6F, 55.3F,
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
    private static final float[] S1Amplitudes = { 0.02F, 4.5F, 22.4F, 42.0F,
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
    private static final float[] S2Amplitudes = { 0.0F, 2.0F, 4.0F, 8.5F,
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
    private static final RegularSpectralCurve kS0Spectrum = new RegularSpectralCurve(
      S0Amplitudes,
      300,
      830);
    private static final RegularSpectralCurve kS1Spectrum = new RegularSpectralCurve(
      S1Amplitudes,
      300,
      830);
    private static final RegularSpectralCurve kS2Spectrum =
      new RegularSpectralCurve(
      S2Amplitudes,
      300,
      830);
    private static final XYZColor S0xyz = kS0Spectrum.toXYZ(
                                                        );
    private static final XYZColor S1xyz = kS1Spectrum.toXYZ(
                                                        );
    private static final XYZColor S2xyz = kS2Spectrum.toXYZ(
                                                        );
    private final float M1;
    private final float M2;
    public ChromaticitySpectrum(float x, float y) { super(
                                                      );
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
    public static final XYZColor get(float x, float y) { float M1 =
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
                                                           getX(
                                                             ) +
                                                           M1 *
                                                           S1xyz.
                                                           getX(
                                                             ) +
                                                           M2 *
                                                           S2xyz.
                                                           getX(
                                                             );
                                                         float Y =
                                                           S0xyz.
                                                           getY(
                                                             ) +
                                                           M1 *
                                                           S1xyz.
                                                           getY(
                                                             ) +
                                                           M2 *
                                                           S2xyz.
                                                           getY(
                                                             );
                                                         float Z =
                                                           S0xyz.
                                                           getZ(
                                                             ) +
                                                           M1 *
                                                           S1xyz.
                                                           getZ(
                                                             ) +
                                                           M2 *
                                                           S2xyz.
                                                           getZ(
                                                             );
                                                         return new XYZColor(
                                                           X,
                                                           Y,
                                                           Z);
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYa2wcVxW+Hr8dx680iWvit53ipOzYDi0KLgZ35SQO69rK" +
                                                    "phHdQp3x7N31xPPqzF177eI+UqFY+REQuCFBxT8gfadJVBHCQ0H5A21VhNQK" +
                                                    "gfhBA/yhIkQiPygVBco5d2Z2dmcfjYWwNHfv3HvOPefcc853juf8TVJpW2S3" +
                                                    "aahLSdVgIZpmoWPqPSG2ZFI7dDByz7Rk2TQeViXbPgxrM3LPpcb3P/zGXJNA" +
                                                    "qmJki6TrBpOYYuj2IWob6gKNR0ijvzquUs1mpClyTFqQxBRTVDGi2GwkQjZl" +
                                                    "sTLSF/FUEEEFEVQQuQrimE8FTJupntLCyCHpzH6UPE7KIqTKlFE9RrpzDzEl" +
                                                    "S9LcY6a5BXBCDb4fAaM4c9oiXRnbHZvzDH5mt7j27UeaXisnjTHSqOhRVEcG" +
                                                    "JRgIiZF6jWqz1LLH4nEaj5FmndJ4lFqKpCrLXO8YabGVpC6xlEUzl4SLKZNa" +
                                                    "XKZ/c/Uy2malZGZYGfMSClXj3ltlQpWSYOs231bHwn24DgbWKaCYlZBk6rFU" +
                                                    "zCt6nJHOIEfGxr4vAgGwVmuUzRkZURW6BAukxfGdKulJMcosRU8CaaWRAimM" +
                                                    "tBU9FO/alOR5KUlnGGkN0k07W0BVyy8CWRjZGiTjJ4GX2gJeyvLPzQfuO/WY" +
                                                    "fkAXuM5xKquofw0wdQSYDtEEtaguU4exflfktLTt6qpACBBvDRA7NFe+eusL" +
                                                    "d3dce8Oh+UQBmqnZY1RmM/K52Ya3d4QH9pajGjWmYSvo/BzLefhPuzsjaRMy" +
                                                    "b1vmRNwMeZvXDv3ioSdfpjcEUjdBqmRDTWkQR82yoZmKSq39VKeWxGh8gtRS" +
                                                    "PR7m+xOkGuYRRafO6lQiYVM2QSpUvlRl8He4ogQcgVdUDXNFTxje3JTYHJ+n" +
                                                    "TULIZnhICzzVxPnjv4zExDlDo6IkS7qiGyLELpUseU6ksiHakmaq4DU7pSdU" +
                                                    "Y1G0LVk0rGTmXdHA5WJ4zjI0zCGFLUVNuDwrpYUwxsz/6+lptK1psawMrn1H" +
                                                    "MOlVyJcDhhqn1oy8lrp//NaFmbeETBK4t8LITpAXcuWFuLxQIXmkrIyLuQPl" +
                                                    "Op4Fv8xDhgP21Q9Ev3Lw6GpPOYSUuVgBl4qkPWChq8y4bIR9GJjgYCdDLLZ+" +
                                                    "7+EToQ9e+LwTi2JxzC7ITa6dWXzqyBODAhFywReNg6U6ZJ9GyMxAY18w6Qqd" +
                                                    "23jivfcvnl4x/PTLQXMXFfI5Mat7gm6wDJnGASf943d1SZdnrq70CaQCoALg" +
                                                    "kUkQzoA8HUEZOdk94iEl2lIJBicMS5NU3PLgrY6B5xb9FR4fDXzeDE7ZhOHe" +
                                                    "Cc+AG//8F3e3mDje4cQTejlgBUfifT++dvbyd3bvFbJBuzGrDEYpcyCg2Q+S" +
                                                    "wxalsP77M9PfeubmiYd5hABFbyEBfTiGARDAZXCtX3vj0d9df/fcrwU/qhhU" +
                                                    "xtSsqshpOGOnLwXgQoU4Rd/3PahrRlxJKNKsSjE4/9XYP3T5r6eaHG+qsOIF" +
                                                    "w90ff4C/fuf95Mm3HvlHBz+mTMZy5VvukzkXsMU/ecyypCXUI/3UO+1nX5e+" +
                                                    "C2gKCGYry5SDksAtE4BpoETLYikaoOiCC/PiSsv1+Wffe9VJm2BNCBDT1bWT" +
                                                    "H4VOrQlZhbM3r3Zl8zjFkwfDZid4PoK/Mnj+gw8GDS444NkSdhG8KwPhponu" +
                                                    "6S6lFhex788XV3764soJx4yW3LoxDm3Rq7/59y9DZ/7wZgHYgvgzJO7JPVxR" +
                                                    "kSu6i48h1IxfK+F7Izh0mXl7ab7Syt82lXbAPmxYsnDrn1Pq7PE/fcAVy0Oe" +
                                                    "Aj4J8MfE88+2hUdvcH4fApC7M52P5dDc+bzDL2t/F3qqfi6Q6hhpkt3O8Yik" +
                                                    "pjDRYtAt2V47Cd1lzn5u5+OU+ZEMxO0IBkWW2CD4+M6AOVLjvC6AN3fiLY/C" +
                                                    "0+DiTU0Qb8oIn4Q5Sw8f+3H4JPdJOSPVpqUsQExB4tu8SUXPK7qkphmpjw6O" +
                                                    "QflUWCpObXBff3H38SR00mH9+d5fPbHe+0e4+hipUWwwcsxKFujHsnj+dv76" +
                                                    "jXc2t1/giF0xK9mOucFGNr9PzWk/+e3Um/xnTyYYy9x6ie+jpncjU4VvRMDp" +
                                                    "AA4TcAFVKtWTbI4TDeLwaSei72WkHFTB6aSZzkjykAbftzIXozASoPU0dIpw" +
                                                    "5+05VV4xQpm2HzbTeTpbpD2nxk9ya/1QPfnSK1fY27s/6yT4ruL+CTK+fvwv" +
                                                    "bYdH545uoLJ3BtwXPPKlyfNv7t8pf1Mg5ZmIz/v/IZdpJDfO6ywK//Doh3Oi" +
                                                    "vcPx5yQOfSUwaLbEXhyHoxDZMvrBcRvcbWfh8jSumYwXlOUfbf/BfS+sv8vr" +
                                                    "Y5rnVZMDcqO5KbgXnm1uCjYVScG5IimI0/04HPCirj465KddxvbCkj8HT7sr" +
                                                    "ubWIZG0DkodvW7Lmtjpey1NIsnXbkjfNRwe9TtjLkQKN8yGaTKmS5VBKajhl" +
                                                    "LdDSKna5KnYVUXFpIyoOeSri2mJpwd2u4O4igh/fiODh2xSMtaDXFdxbRPDT" +
                                                    "ty24MjqYXlr23NGW744vPRSDxDGs0gr1uQr1FVHo5AYUGgKF8G21tMh+V2R/" +
                                                    "EZFf34DI4ZIiW7z4v8sVeVcRkWslCs5+T5owOYTTPf+bqLM4nMbThrNOS398" +
                                                    "xctq1wh2mO3FvqDw7vLc8bX1+NRzQ4KLsp9hpJYZ5qdUukDVrKOcgvjljB34" +
                                                    "kO3whFw7QkE7uMI4BAHfrYvcKk71fAnUfxGH72Njwz8CcKb8HjWgXLcXqcOu" +
                                                    "csMFlSsRPwNZ8RPQTvBvfI9vw6USNryGwyvQbiQpbzdWCzTZ0E0U+qCAFa41" +
                                                    "70Ol83FNvrDeWLN9/cHfOg2X9wGsNkJqEilVze49s+ZVpkUTCter1ulEndp8" +
                                                    "hZHmPHCA1OG/XN8fOoQ/ATDLIoTu051lE10Fa4EIpz8zPfDpzAefnCKQJjmx" +
                                                    "awYjuTenNeJfeb02JuV8552RL64ffOCxW/c+x3siaBWkZZ73NdBlOl8CMq1Q" +
                                                    "d9HTvLOqDgx82HCptt/LjQYcWrJipzXLycn/AjlPWZVTFwAA");
}
