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
      1167346356000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Ya2wcVxW+Hr8dx680iWvit53ipOz4QYuCi8FdOYnDuray" +
                                                    "SUS3UOfu7N31xPPqzF177eI+UqFY+REQuGlSFf+A9J0mUUUIDwXlD7RVEVIr" +
                                                    "BOIHDfCHihCJ/KBUFCjn3pnZmZ19NBYSlubunXvPueece875zvGcv4kqLRPt" +
                                                    "NnRlKaXoNEQyNHRMuSdElwxihQ5E7pnBpkUSYQVb1iFYm5V6LjV+8NG35poE" +
                                                    "VBVDW7Cm6RRTWdesg8TSlQWSiKBGb3VCIapFUVPkGF7AYprKihiRLToaQZt8" +
                                                    "rBT1RVwVRFBBBBVEroI47lEB02aipdUw48AatR5Bj6GyCKoyJKYeRd25hxjY" +
                                                    "xKpzzAy3AE6oYe9HwCjOnDFRV9Z22+Y8g5/eLa4983DT6+WoMYYaZS3K1JFA" +
                                                    "CQpCYqheJWqcmNZ4IkESMdSsEZKIElPGirzM9Y6hFktOaZimTZK9JLaYNojJ" +
                                                    "ZXo3Vy8x28y0RHUza15SJkrCfatMKjgFtm7zbLUt3MvWwcA6GRQzk1giLkvF" +
                                                    "vKwlKOoMcmRt7PsyEABrtUronJ4VVaFhWEAttu8UrKXEKDVlLQWklXoapFDU" +
                                                    "VvRQdtcGluZxisxS1Bqkm7G3gKqWXwRjoWhrkIyfBF5qC3jJ55+bD9x36lFt" +
                                                    "vyZwnRNEUpj+NcDUEWA6SJLEJJpEbMb6XZHTeNvVVQEhIN4aILZprnz91pfu" +
                                                    "7rj2pk3zqQI00/FjRKKz0rl4wzs7wgN7ypkaNYZuycz5OZbz8J9xdkYzBmTe" +
                                                    "tuyJbDPkbl47+IsHn3iF3BBQ3SSqknQlrUIcNUu6asgKMfcRjZiYksQkqiVa" +
                                                    "Isz3J1E1zCOyRuzV6WTSInQSVSh8qUrn73BFSTiCXVE1zGUtqbtzA9M5Ps8Y" +
                                                    "CKHN8KAWeKqR/cd/KSLiYQvCXcQS1mRNFyF4CTalOZFI+mwcbndOxea8JUpp" +
                                                    "i+qqaKW1pKIvipYpibqZyr7LKnhfDM+ZusrSSaZLUQPu0UyrIRZuxv9LUIZZ" +
                                                    "3LRYVgbO2BGEAgWyaL+uJIg5K62l75+4dWH2bSGbGs5dUbQT5IUceSEuL1RI" +
                                                    "Hior42LuYHJtf4O35iHvARHrB6JfO3B0taccAs1YrICrZqQ9YKujzISkhz1w" +
                                                    "mOQQKEGEtn7voROhD1/8oh2hYnEkL8iNrp1ZfPLI44MCEnIhmRkHS3WMfYYB" +
                                                    "aRYw+4KpWOjcxhPvf3Dx9IruJWUOxjtYkc/Jcr0n6AZTl0gC0NM7flcXvjx7" +
                                                    "daVPQBUAIACaFEOQAx51BGXk5Pyoi5/MlkowOKmbKlbYlgt6dRQ8t+it8Pho" +
                                                    "4PNmcMomlgSd8Aw4WcF/2e4Wg4132PHEvBywguPz3h9fO3v52d17BD+UN/qK" +
                                                    "Y5RQGxiavSA5ZBIC678/M/Odp2+eeIhHCFD0FhLQx8YwwAS4DK71G28+8rvr" +
                                                    "7537teBFFYV6mY4rspSBM3Z6UgBEFIhT5vu+w5qqJ+SkjOMKYcH5r8b+oct/" +
                                                    "PdVke1OBFTcY7v7kA7z1O+9HT7z98D86+DFlEitinuUemX0BW7yTx00TLzE9" +
                                                    "Mk++2372DfxdwFjANUteJhyqBG6ZAEwDJRoZU1YBWxcc8BdXWq7PP/f+a3ba" +
                                                    "BCtFgJisrp38OHRqTfCV0968iubnsUsqD4bNdvB8DH9l8PyHPSxo2IINqS1h" +
                                                    "B9e7ssBuGMw93aXU4iL2/vniyk9fWjlhm9GSW00moFl67Tf//mXozB/eKgBb" +
                                                    "EH865p4c4YqKXNFdfAwxzfi1Ir43yoYuI28vw1da+dum0g7Yy9oYH279c1qJ" +
                                                    "H//Th1yxPOQp4JMAf0w8/1xbeOwG5/cggHF3ZvKxHFo+j3f4FfXvQk/VzwVU" +
                                                    "HUNNktNPHsFKmiVaDHooy20yoefM2c/th+ziP5qFuB3BoPCJDYKP5wyYM2o2" +
                                                    "rwvgzZ3slsfgaXDwpiaIN2WIT8KcpYeP/Wz4NPdJOUXVhikvQExB4lu8dWWe" +
                                                    "lzWsZCiqjw6Oq4Yi03SCWOC+/uLu40lop8P6C72/eny9949w9TFUI1tg5LiZ" +
                                                    "KtCl+Xj+dv76jXc3t1/giF0Rx5ZtbrC9ze9ec5pSfjv1Bv8ZyQZjmVMv2fuY" +
                                                    "4d7IdOEbEdh0gA2TcAFVCtFSdI4TDbLhs3ZE30tROajCplNGJivJRRr2vpU6" +
                                                    "GMUiARpSXSMM7tw9u8rLeij7zwBsZvJ0NlF7To2f4tZ6oXry5Vev0Hd2f95O" +
                                                    "8F3F/RNkfOP4X9oOjc0d3UBl7wy4L3jky1Pn39q3U/q2gMqzEZ/3X0Uu02hu" +
                                                    "nNeZBP4N0g7lRHuH7c8pNvSVwKB4ib0EG45CZEvMD7bb4G47C5enCdWgvKAs" +
                                                    "/2j7D+57cf09Xh8zPK+abJAby03BPfBsc1KwqUgKzhVJQTbdx4b9btTVR4e8" +
                                                    "tMvaXljyF+BpdyS3FpGsbkDy8G1LVp1Wx215Ckk2b1vypvnooNsJuzlSoHE+" +
                                                    "SFJpBZs2JVbCaXOBlFaxy1Gxq4iKSxtRcchVka0tlhbc7QjuLiL4sY0IHr5N" +
                                                    "wawW9DqCe4sIfuq2BVdGBzNLy6472vLd8ZUHY5A4ullaoT5Hob4iCp3cgEJD" +
                                                    "oBB7Wy0tst8R2V9E5Dc3IHK4pMgWN/7vckTeVUTkWomCs8+VJkwNsenI/ybq" +
                                                    "LBtOs9OGfadlPrni+do1xDrM9mLfVXh3ee742npi+vkhwUHZz1FUS3XjMwpZ" +
                                                    "IIrvKLsgfjVrB3vQdnhCjh2hoB1cYTYEAd+pi9wqTvVCCdR/iQ3fZ40NBlDj" +
                                                    "ODGS36MGlOt2I3XYUW64oHIl4mfAFz8B7QTvxkc8Gy6VsOF1NrwK7UaK8HZj" +
                                                    "tUCTDd1EoQ8KrMK15n2+tD+5SRfWG2u2rx/+rd1wuZ/FaiOoJplWFH/v6ZtX" +
                                                    "GSZJylyvWrsTtWvzFYqa88ABUof/cn1/aBP+BMDMRwjdpzPzE10Fa4GITX9m" +
                                                    "uODTmQ8+OUUgg3Ji1whGcm9Oa8S//bptTNr++jsrXVw/8MCjt+59nvdE0Crg" +
                                                    "ZZ73NdBl2l8Csq1Qd9HT3LOq9g981HCptt/NjQY2tPhip9Xn5NR/AbrhFOxp" +
                                                    "FwAA");
}
