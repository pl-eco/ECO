package org.sunflow.image;
public class RegularSpectralCurve extends SpectralCurve {
    private final float[] spectrum;
    private final float lambdaMin;
    private final float lambdaMax;
    private final float delta;
    private final float invDelta;
    public RegularSpectralCurve(float[] spectrum, float lambdaMin, float lambdaMax) {
        super(
          );
        this.
          lambdaMin =
          lambdaMin;
        this.
          lambdaMax =
          lambdaMax;
        this.
          spectrum =
          spectrum;
        delta =
          (lambdaMax -
             lambdaMin) /
            (spectrum.
               length -
               1);
        invDelta =
          1 /
            delta;
    }
    public float sample(float lambda) { if (lambda < lambdaMin ||
                                              lambda >
                                              lambdaMax) return 0;
                                        float x = (lambda - lambdaMin) *
                                          invDelta;
                                        int b0 = (int) x;
                                        int b1 = Math.min(b0 + 1,
                                                          spectrum.
                                                            length -
                                                            1);
                                        float dx = x - b0;
                                        return (1 - dx) * spectrum[b0] +
                                          dx *
                                          spectrum[b1]; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYb2wcxRWfW9vnP3His0MSNyR2YjsRTuC2pIIqGIWGq02c" +
                                                    "XogbJ6k4Ssx4d+68yeyf7M7ZG4MbEokmRSVCYCAg6g8oQKEhQRURVBVSvrSA" +
                                                    "qCqBUKt+KGn5AmoaqflQikpb+mZmb/du7879I7C0c7Mz7715f39v1mevoCbP" +
                                                    "RZsdmx4pUJulic/SB+lNaXbEIV56Z/amMex6RM9Q7Hl7YW1C63ul45PPHplK" +
                                                    "KSiZQ8uxZdkMM8O2vD3Es+k00bOoI1odpsT0GEplD+JprBaZQdWs4bGhLFpS" +
                                                    "xsrQQLakggoqqKCCKlRQt0dUwLSUWEUzwzmwxbzD6HsokUVJR+PqMbS+UoiD" +
                                                    "XWwGYsaEBSChhb/vB6MEs++idaHt0uYqgx/frM4/eSD10wbUkUMdhjXO1dFA" +
                                                    "CQaH5FC7ScxJ4nrbdZ3oOdRpEaKPE9fA1JgVeudQl2cULMyKLgmdxBeLDnHF" +
                                                    "mZHn2jVum1vUmO2G5uUNQvXSW1Oe4gLYujKyVVo4wtfBwDYDFHPzWCMllsZD" +
                                                    "hqUz1BvnCG0c+BYQAGuzSdiUHR7VaGFYQF0ydhRbBXWcuYZVANImuwinMLS6" +
                                                    "rlDuawdrh3CBTDDUHacbk1tA1SocwVkYWhEnE5IgSqtjUSqLz5U7bz11n7XD" +
                                                    "UoTOOtEo178FmHpiTHtInrjE0ohkbN+UfQKvfOOkghAQr4gRS5rX7r/6jet7" +
                                                    "Lr4laa6tQbN78iDR2IR2ZnLZu2syg1sbuBotju0ZPPgVlov0Hwt2hnwHKm9l" +
                                                    "KJFvpkubF/f88q4HXiKXFdQ2ipKaTYsm5FGnZpuOQYl7B7GIixnRR1ErsfSM" +
                                                    "2B9FzTDPGhaRq7vzeY+wUdRIxVLSFu/gojyI4C5qhrlh5e3S3MFsSsx9ByHU" +
                                                    "DA9qh6cByT/xy1BOnbJNomINW4Zlq5C7BLvalEo0W/Ww6VCImle08tSeUT1X" +
                                                    "U223EL4bJoQcWApFit1xB/zmYpoputMkzXPM+VKl+9y21EwiAW5fEy96CvWy" +
                                                    "w6Y6cSe0+eLtw1fPTbyjhEUQeIWhjXBeOjgvLc5L1zoPJRLimGv4uTKyEJdD" +
                                                    "UOGAfe2D4/fsvPdkH/jTd2YawamctA8sDJQZ1uxMBAOjAuw0yMXuZ+8+kf70" +
                                                    "hdtkLqr1MbsmN7p4eubY/qNfVZBSCb7cOFhq4+xjHDJDaByIF10tuR0nPv7k" +
                                                    "/BNzdlR+FWgeoEI1J6/qvngYXFsjOuBkJH7TOnxh4o25AQU1AlQAPDIM6QzI" +
                                                    "0xM/o6K6h0pIyW1pAoPztmtiyrdK8NbGplx7JloR+bFMzDshKEt4uvfC0xLk" +
                                                    "v/jlu8sdPl4j84lHOWaFQOKRn1186sLTm7cq5aDdUdYGxwmTENAZJclelxBY" +
                                                    "//3psccev3LibpEhQNFf64ABPmYAECBk4NYH3zr8u0sfnHlfibKKQWcsTlJD" +
                                                    "80HGxugUgAsKycpjP7DPMm3dyBt4khKenP/o2HDjhT+fSsloUlgpJcP1/1lA" +
                                                    "tP6V29ED7xz4W48Qk9B4u4osj8ikA5ZHkre7Lj7C9fCPvbf2qTfxjwBNAcE8" +
                                                    "Y5YIUGoQljUA04b66S+ESPheeL7/10cX+v8IeuRQi+FB49/uFmr0kzKev5y9" +
                                                    "dPm9pWvPiYxrnMSeKP+2eCOu7rMV7VNkR7vDXT+4yO3KNUwA/OmgI6lzXZcO" +
                                                    "PfPxy7LC4+0rRkxOzj/0efrUvFLW4/ur2mw5j+zzQrOlMs8/h78EPP/iD89v" +
                                                    "viBxvisTNJt1YbdxhDnrF1NLHDHy0fm5n/947oQ0o6uyxQ3DDe7l3/zzV+nT" +
                                                    "f3i7BsJCqdhYXu3SPIlDgFzEiyM8LmU4+ffddPL4h58K6VVIV8OxMf6cevaZ" +
                                                    "1ZltlwV/BDmcu9ev7h2QUxHvlpfMvyp9yV8oqDmHUlpwU92PaZEXdg6yxitd" +
                                                    "X+E2W7FfedOS14qhEFLXxCNbdmwc7CKPwpxTiwSW+Ob4CSTwKyOo+8S4gQ/X" +
                                                    "CVcrfDrI42BYmPqAIZRYBTYl6L7Gh6/7Yr6VoQYoAT7d4vhhwBQpRbyvYEFt" +
                                                    "c4vgymZbhMNEaU92R8NOh9dl2PRrhH5tRW/cJaoscvlDL/7kNfbu5ltktm2q" +
                                                    "nyZxxjeP/2n13m1T9/4PHbE3Fvu4yBd3nX37jo3aowpqCCNXde+uZBqqjFeb" +
                                                    "S+BDwdpbEbUeR/xs4cOAUGZT6CYk3IQEwXcW2buLD/sgsBqPgwwb+La3NqwP" +
                                                    "mw4TQDz7+qpXb31h4QPRV3zR/24WgxCqLnLgAT6sc6r2ZIS7xVuTmG8Lm24X" +
                                                    "p+iHpzFouo3xphtkL66TvQw1O64xDWjFF0cgf1s8cTMrmqEHU06tU2/g6gSn" +
                                                    "NtU5tbBIzYQHtlJsTup4l2GFzvr/TxTfMkYkVN5iFxF6HTzJQGiyjlD7vzGj" +
                                                    "SSeU4S/gNAERhyEQhjX9zZhMP8ro7iA9eL3X+9gT3eXM8fkFffdzNypBnt0C" +
                                                    "3mG2cwMl04SWiUqI+bdDhfmDVsGTChRO1bzQ1aqxAIqivD+6SN4f48P9AJzy" +
                                                    "e0Uw1agCwL9anw68Jrur/iUhP6O1cwsdLasW9v1WXk1Kn7qt8L2ZL1Jajvpl" +
                                                    "86TjkrwhVGst9QD+832GOqs+ZyDu4lfo+6Ak/AFDS8oIocSCWTnRD6EdABGf" +
                                                    "PuyUEL63+nOpwlgfVYTeiSdCfwWYi//nlIC3KP+jM6GdX9h5531Xb35OoDiA" +
                                                    "G56d5VJa4D4m7/wheK+vK60kK7lj8LNlr7RuKKXWMj50BRf9ct34/Lv/BqRU" +
                                                    "GUo9EwAA");
}
