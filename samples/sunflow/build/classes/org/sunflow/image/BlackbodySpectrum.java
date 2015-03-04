package org.sunflow.image;
public class BlackbodySpectrum extends SpectralCurve {
    private float temp;
    public BlackbodySpectrum(float temp) { super();
                                           this.temp = temp; }
    public float sample(float lambda) { double wavelength = lambda * 1.0E-9;
                                        return (float) (3.74183E-16 * Math.
                                                          pow(
                                                            wavelength,
                                                            -5.0) /
                                                          (Math.
                                                             exp(
                                                               0.014388 /
                                                                 (wavelength *
                                                                    temp)) -
                                                             1.0)); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXXWwUVRS+u/1fSrctf7WWtrSF2BZ3RIMGS1DYtFBcaNMW" +
                                                    "iItS7s7ebYfOHzN3222xiiSmhAditCAY7IOBIMhfjASNIemLAsEXjNH4IBhf" +
                                                    "JCIPPIhEVDz3zuzO7uwW9cEmM7177zn3nHPPd7575tRtVGAaqFXX5NEBWaMB" +
                                                    "kqCBHfLyAB3ViRlYH1rejQ2TRIMyNs0+mOsXG875795/c7DciwrDaA5WVY1i" +
                                                    "Kmmq2UNMTR4m0RDyO7PtMlFMispDO/AwFuJUkoWQZNK2EJqVpkpRUyjpggAu" +
                                                    "COCCwF0QVjtSoDSbqHElyDSwSs2d6FXkCaFCXWTuUbQocxMdG1ixt+nmEcAO" +
                                                    "xez3ZgiKKycMVJ+K3Yo5K+ADrcLkO9vKP8pD/jDyS2ovc0cEJygYCaNShSgR" +
                                                    "Ypiro1ESDaMKlZBoLzEkLEtj3O8wqjSlARXTuEFSh8Qm4zoxuE3n5EpFFpsR" +
                                                    "F6lmpMKLSUSOJn8VxGQ8ALHOd2K1Iuxg8xCgTwLHjBgWSVIlf0hSoxTVuTVS" +
                                                    "MTa9AAKgWqQQOqilTOWrGCZQpZU7GasDQi81JHUARAu0OFihqHrGTdlZ61gc" +
                                                    "wgOkn6Iqt1y3tQRSJfwgmApF89xifCfIUrUrS2n5ub1x5f5d6jrVy32OElFm" +
                                                    "/heDUq1LqYfEiEFUkViKpS2hg3j+xb1ehEB4nkvYkrnwyp3nl9ZOX7ZkHs0h" +
                                                    "0xXZQUTaLx6NlF2rCTavyGNuFOuaKbHkZ0TO4d9tr7QldKi8+akd2WIguTjd" +
                                                    "88WLu0+SW17k60SFoibHFcBRhagpuiQTYy1RiYEpiXaiEqJGg3y9ExXBOCSp" +
                                                    "xJrtisVMQjtRvsynCjX+G44oBluwIyqCsaTGtORYx3SQjxM6QqgIHlQKTx6y" +
                                                    "/vh/irYIg5pCBCxiVVI1AbBLsCEOCkTUBBMrugxZM+NqTNZGBNMQBc0YSP2W" +
                                                    "FEi5sEaG3Ee06GivDidnxJUAA5j+/22dYFGVj3g8cOA17nIHDbJOk6PE6Bcn" +
                                                    "42va75zpv+pNwd8+D4oawVjANhbgxgJZxpDHw23MZUathEI6hqCwgfJKm3tf" +
                                                    "Xr99bwMcY0IfyYezZKINEJvtSbuoBZ3q7+QcJwIEq97fOhG4d/w5C4LCzFSd" +
                                                    "UxtNHxp5ffNrT3iRN5NzWWQw5WPq3YwpU4zY5K61XPv6J27ePXtwXHOqLoPE" +
                                                    "bTLI1mTF3ODOgaGJJAr06GzfUo/P918cb/KifGAIYEWKAcVAOLVuGxlF3ZYk" +
                                                    "SBZLAQQc0wwFy2wpyWo+OmhoI84MB0cZH1dAUmYxlNfAU2jDnv9nq3N09p5r" +
                                                    "gYll2RUFJ+COT6cPn3+3dYU3nav9abdfL6FW5Vc4IOkzCIH57w91v33g9sRW" +
                                                    "jhCQaMxloIm9g8ADkDI41jcu7/zuxvWjX3sdVFG4EOMRWRITsMcSxwqwhAw4" +
                                                    "Zblv2qQqWlSKSTgiEwbOP/yLl53/ZX+5lU0ZZpJgWPrPGzjzj6xBu69u+62W" +
                                                    "b+MR2S3lRO6IWQcwx9l5tWHgUeZH4vWvFh6+hN8DEgXiMqUxwrnIk6qX5od0" +
                                                    "KoakAHkO2+wujFfeGDpy87RVNu6rwCVM9k7uexDYP+lNuy8bs66sdB3rzuRg" +
                                                    "mG2B5wH8eeD5iz0MNGzC4szKoE3c9Snm1nWWnkUPc4ub6Pjp7PhnH4xPWGFU" +
                                                    "Zl4X7dANnf7mzy8Dh364koOzAH8aptxHgfvYwt8B5hQ/UcTX2tirXs9aS/CZ" +
                                                    "qn9z9h2sRUmjrN+75MieH+9xn7JIJ0c6XPph4dSR6uCqW1zfqX6mXZfI5nBo" +
                                                    "5xzdJ08qv3obCj/3oqIwKhftXnEzluOsxsLQH5nJBhL6yYz1zF7HutjbUuxW" +
                                                    "48ZDmlk37zh5gDGTZmOfi2rY5YoWwJNvU02+m2o8iA+CXKWBvxez12PJSi/S" +
                                                    "DWkYs0YUzBCFSz/FCUrnxprSUogY4BbO1EdxsB3dMzkV7Tq2zGsj4xmKSqim" +
                                                    "Py6TYSK70LAw4+bawDtHJwv7Tnx4gV5rfdaCbcvMyHErXtrzc3XfqsHt/+G+" +
                                                    "qnPF5N7yxIZTV9YuEd/yorxUMrOa4UyltswU+gwC3bval5HI2lQiK5KJ9NmJ" +
                                                    "9OW8M5yEOHVoMxvPG5fa9JBC3cJePcDvVjPElXKULUUVWa0JI9yqrC8dqzsX" +
                                                    "z0z5ixdMbfqWX7apDroE2thYXJbToZw2LtQNEpO4XyUWsC3IvQTms3ol4CL+" +
                                                    "nzu71RKEr4JZaYIAZnuULhShKA+E2FC0Qp0Hycvuxaw4sRyMG8MkgTJgr7uL" +
                                                    "oDEDjvwzMQmduPWh2C+enVq/cdedp49xHBbAB+bYGP+sgK8kq6dIwW/RjLsl" +
                                                    "9ypc13y/7FzJ4mRZlbFXpd1IuHyry33ftis65Tfk2CcLPl55fOo6v/D/BvrH" +
                                                    "Qz2/DwAA");
}
