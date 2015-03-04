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
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Ya2wcVxW+Hr8dx680iWvit53ipOz4QYuCi8FdOYnTdW1l" +
                                                    "04huoc717N3diefVmbv22sV9pEKx8iMgcEOCin/Q9J0mUdU0PBSUP9BWRUit" +
                                                    "EIgfNMAfKkIk8oNSUaCce2dmZ3b20VhIWJq7d+49555z7jnnO8dz7gaqtEy0" +
                                                    "29CVpaSi0xDJ0NBR5a4QXTKIFToQuWsGmxaJhxVsWYdgbVbqudj44cffTjUJ" +
                                                    "qCqGtmBN0ymmsq5ZB4mlKwskHkGN3uqEQlSLoqbIUbyAxTSVFTEiW3Q0gjb5" +
                                                    "WCnqi7gqiKCCCCqIXAVx3KMCps1ES6thxoE1aj2CHkNlEVRlSEw9irpzDzGw" +
                                                    "iVXnmBluAZxQw94Pg1GcOWOirqztts15Bj+9W1z73sNNr5WjxhhqlLUoU0cC" +
                                                    "JSgIiaF6lahzxLTG43ESj6FmjZB4lJgyVuRlrncMtVhyUsM0bZLsJbHFtEFM" +
                                                    "LtO7uXqJ2WamJaqbWfMSMlHi7ltlQsFJsHWbZ6tt4V62DgbWyaCYmcAScVkq" +
                                                    "5mUtTlFnkCNrY999QACs1SqhKT0rqkLDsIBabN8pWEuKUWrKWhJIK/U0SKGo" +
                                                    "reih7K4NLM3jJJmlqDVIN2NvAVUtvwjGQtHWIBk/CbzUFvCSzz837r/n5KPa" +
                                                    "fk3gOseJpDD9a4CpI8B0kCSISTSJ2Iz1uyKn8LYrqwJCQLw1QGzTXP7Gza/c" +
                                                    "2XH1LZvmMwVopueOEonOSmfnGt7dER7YU87UqDF0S2bOz7Gch/+MszOaMSDz" +
                                                    "tmVPZJshd/PqwV88+MTL5LqA6iZRlaQraRXiqFnSVUNWiLmPaMTElMQnUS3R" +
                                                    "4mG+P4mqYR6RNWKvTicSFqGTqELhS1U6f4crSsAR7IqqYS5rCd2dG5im+Dxj" +
                                                    "IIQ2w4Na4KlG9h//pUgVU7pKRCxhTdZ0EWKXYFNKiUTSZ01i6OJEeFqcg1tO" +
                                                    "qdict0QrrSUUfXFWSltUV0XLlETdTLrLoqxCFIjhlKmrLK1kuhQ14D7NtBpi" +
                                                    "YWf8vwVm2A00LZaVgXN2BKFBgazarytxYs5Ka+l7J26en31HyKaKc3cU7QR5" +
                                                    "IUdeiMsLFZKHysq4mNuYXNv/4L15wAFAyPqB6NcPHFntKYfAMxYr4OoZaQ8Y" +
                                                    "7SgzIelhDywmOSRKELGtP3zoeOijF75sR6xYHNkLcqOrpxefPPz4oICEXIhm" +
                                                    "xsFSHWOfYcCaBdC+YGoWOrfx+AcfXji1ontJmoP5Dnbkc7Lc7wm6wdQlEgc0" +
                                                    "9Y7f1YUvzV5Z6RNQBQAKgCjFEPSATx1BGTkYMOriKbOlEgxO6KaKFbblgmAd" +
                                                    "Bc8teis8Phr4vBmcsoklRSc8A06W8F+2u8Vg4212PDEvB6zgeL33x1fPXPr+" +
                                                    "7j2CH9obfcUySqgNFM1ekBwyCYH135+e+e7TN44/xCMEKHoLCehjYxhgA1wG" +
                                                    "1/rNtx753bX3z/5a8KKKQv1MzymylIEzdnpSAFQUiFPm+74HNFWPywkZzymE" +
                                                    "Bee/GvuHLv31ZJPtTQVW3GC489MP8NZvvxc98c7D/+jgx5RJrKh5lntk9gVs" +
                                                    "8U4eN028xPTIPPle+5k38Q8AcwHnLHmZcOgSuGUCMA2UaGxMWQWsXXCKgbjS" +
                                                    "cm3+mQ9etdMmWDkCxGR17cQnoZNrgq+89uZVOD+PXWJ5MGy2g+cT+CuD5z/s" +
                                                    "YUHDFmyIbQk7ON+VBXrDYO7pLqUWF7H3zxdWfvriynHbjJbc6jIBzdOrv/n3" +
                                                    "L0On//B2AdiC+NMx9+QIV1Tkiu7iY4hpxq8V8b1RNnQZeXsZvtLK3zaVdsBe" +
                                                    "1tb4cOuf08rcsT99xBXLQ54CPgnwx8Rzz7SFx65zfg8CGHdnJh/LoQX0eIdf" +
                                                    "Vv8u9FT9XEDVMdQkOf3lYaykWaLFoKey3KYTetCc/dz+yG4GRrMQtyMYFD6x" +
                                                    "QfDxnAFzRs3mdQG8uZ3d8hg8DQ7e1ATxpgzxSZiz9PCxnw2f5T4pp6jaMOUF" +
                                                    "iClIfIu3sszzsoaVDEX10cFx1VBkmo4TC9zXX9x9PAntdFh/vvdXj6/3/hGu" +
                                                    "PoZqZAuMHDeTBbo2H8/fzl27/t7m9vMcsSvmsGWbG2x387vZnCaV3069wX9G" +
                                                    "ssFY5tRL9j5muDcyXfhGBDYdYMMkXECVQrQkTXGiQTZ83o7ouykqB1XYdMrI" +
                                                    "ZCW5SMPet1IHo1gkQIOqa4TBnbtnV3lZD2X/OYDNTJ7OJmrPqfFT3FovVE+8" +
                                                    "9Mpl+u7uL9oJvqu4f4KMbx77S9uhsdSRDVT2zoD7gke+NHXu7X07pe8IqDwb" +
                                                    "8Xn/ZeQyjebGeZ1J4N8i7VBOtHfY/pxiQ18JDJorsRdnwxGIbIn5wXYb3G1n" +
                                                    "4fI0oRqUF5TlH21//Z4X1t/n9THD86rJBrmx3BTcA882JwWbiqRgqkgKsuk+" +
                                                    "Nux3o64+OuSlXdb2wpK/BE+7I7m1iGR1A5KHb1my6rQ6bstTSLJ5y5I3zUcH" +
                                                    "3U7YzZECjfNBkkwr2LQpsRJOmwuktIpdjopdRVRc2oiKQ66KbG2xtOBuR3B3" +
                                                    "EcGPbUTw8C0KZrWg1xHcW0TwU7csuDI6mFladt3Rlu+Orz4Yg8TRzdIK9TkK" +
                                                    "9RVR6MQGFBoChdjbammR/Y7I/iIiv7UBkcMlRba48X+HI/KOIiLXShScfa40" +
                                                    "YWqITUf+N1Fn2HCKnTbsOy3z6RXP164h1mG2F/vOwrvLs8fW1uPTzw0JDsp+" +
                                                    "gaJaqhufU8gCUXxH2QXxa1k72IO2wxNy7AgF7eAKsyEI+E5d5FZxqudLoP6L" +
                                                    "bHiWNTYYQI3jxEh+jxpQrtuN1GFHueGCypWInwFf/AS0E7wbH/FsuFjChtfY" +
                                                    "8Aq0G0nC243VAk02dBOFPiiwCtea9znT/gQnnV9vrNm+/sBv7YbL/UxWG0E1" +
                                                    "ibSi+HtP37zKMElC5nrV2p2oXZsvU9ScBw6QOvyX6/uGTfgTADMfIXSfzsxP" +
                                                    "dAWsBSI2/Znhgk9nPvjkFIEMyoldIxjJvTmtEf8W7LYxaftr8Kx0Yf3A/Y/e" +
                                                    "vPs53hNBq4CXed7XQJdpfwnItkLdRU9zz6raP/Bxw8Xafjc3GtjQ4oudVp+T" +
                                                    "k/8FTlXSnXkXAAA=");
}
