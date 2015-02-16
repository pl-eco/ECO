package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class FisheyeLens implements CameraLens {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time) { float cx = 2.0F * x / imageWidth -
                                       1.0F;
                                     float cy = 2.0F * y / imageHeight -
                                       1.0F;
                                     float r2 = cx * cx + cy * cy;
                                     if (r2 > 1) return null;
                                     return new Ray(0, 0, 0, cx, cy,
                                                    (float)
                                                      -Math.
                                                      sqrt(
                                                        1 -
                                                          r2)); }
    public FisheyeLens() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1163483360000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XW2wUVRg+3d5Ly/ZCS7mVthS0FGYgBhMsXkptoXWBpgUS" +
                                                    "SmA5nTm7nXZ2Zpg5226LVSAxEB6I0YJgtA8Ggii3GAkaQ9IngeALxmh8EHyT" +
                                                    "qDzwgiao+J9z9jI7u6364iZz9sw5//nv/3f+ufAA5Ts2arFMfSysm1QiMSoN" +
                                                    "6eskOmYRR+oOrOvBtkPUdh07znZYCyqNV/yPHr85WO5DBf2oChuGSTHVTMPp" +
                                                    "JY6pjxA1gPyp1Q6dRByKygNDeATLUarpckBzaGsAzXEdpagpkFBBBhVkUEHm" +
                                                    "KshtKSo4VEaMaKSdncAGdfaj11BOABVYClOPooZ0Jha2cSTOpodbAByK2PtO" +
                                                    "MIofjtmoPmm7sDnD4BMt8uQ7e8s/yUX+fuTXjD6mjgJKUBDSj0ojJDJAbKdN" +
                                                    "VYnajyoMQtQ+YmtY18a53v2o0tHCBqZRmySdxBajFrG5zJTnShVmmx1VqGkn" +
                                                    "zQtpRFcTb/khHYfB1pqUrcLCTrYOBpZooJgdwgpJHMkb1gyVoqXeE0kbm14B" +
                                                    "AjhaGCF00EyKyjMwLKBKETsdG2G5j9qaEQbSfDMKUihaOCNT5msLK8M4TIIU" +
                                                    "1XrpesQWUBVzR7AjFFV7yTgniNJCT5Rc8XmwdcPxA8Zmw8d1VomiM/2L4FCd" +
                                                    "51AvCRGbGAoRB0tXBk7imutHfQgBcbWHWNBce/XhS6vqpm8KmkVZaLYNDBGF" +
                                                    "BpUzA3PvLG5vXp/L1CiyTEdjwU+znKd/T3ynNWZB5dUkObJNKbE53fvlroMf" +
                                                    "kV98qKQLFSimHo1AHlUoZsTSdGJvIgaxMSVqFyomhtrO97tQIcwDmkHE6rZQ" +
                                                    "yCG0C+XpfKnA5O/gohCwYC4qhLlmhMzE3MJ0kM9jFkKoEB4kwVOExI//UzQg" +
                                                    "73Ag3WWsYEMzTBmSl2BbGZSJYgYHwLuDEWwPO7ISdagZkZ2oEdLNUdmxFdm0" +
                                                    "w8l3xbSJrECC2Vju1JxBMkYCxHAklmvW/yIlxmwtH83JgTAs9oKADvWz2dRV" +
                                                    "YgeVyejGjoeXgrd9yaKIe4miZSBMiguTmDBJCJNcwlBODpcxjwkVYYYgDUO5" +
                                                    "AxCWNvft6d53tDEX8ssazQMPM9JGsDKuSYditqcwoYsjnwKJWfvB7iPS7+de" +
                                                    "FIkpzwzgWU+j6VOjh3a+vsaHfOlIzCyDpRJ2vIfhZxInm7wVmI2v/8j9R5dP" +
                                                    "TpipWkyD9jhEZJ5kJd7ojYFtKkQF0EyxX1mPrwavTzT5UB7gBmAlxZDbAEN1" +
                                                    "Xhlppd6agE1mSz4YHDLtCNbZVgLrSuigbY6mVnhyzGVDpcgTFkCPghxxOz+f" +
                                                    "Pn313Zb1Pjc4+13XXR+hotQrUvHfbhMC6z+c6nn7xIMju3nwgWJZNgFNbGyH" +
                                                    "wodogMfeuLn/+3t3z3zjSyUMhRswOqBrSgx4rEhJAVjQAZpYWJt2GBFT1UIa" +
                                                    "HtAJy7s//MvXXv31eLkIlA4riTiv+mcGqfUFG9HB23t/q+NschR2LaUsT5EJ" +
                                                    "B1SlOLfZNh5jesQOfb3k9A38PqAmIJWjjRMOPohbhrjrJR6RZj6u9uytYUO9" +
                                                    "lbEX4yu18Tf+0sDHJjY8JfzGpk+7KXP4vJqiRRll3c7LmpUzc/KSmW4jfpOe" +
                                                    "OTw5pW47u1aUZmU6wndAA3Px2z+/kk79eCsLoBRT01qtkxGiu/TyMZFpkLCF" +
                                                    "X9Spwjh2/uNr9E7Lc0LkypnRwHvwxuGfF25/YXDffwCCpR7jvSzPb7lwa9MK" +
                                                    "5S0fyk1iQEbvkX6o1e0GEGoTaJYM5lC2UsJDXccVqAB3VLGgLoCnOH4z8X+2" +
                                                    "W2WxcZ6oWDY848kdX9yf8TjXZcSZm0qgtWHJmSCrcZP1if+2ni4u5uVZsrOb" +
                                                    "DW1QnlFLhdsaotg8S6NtaxG4+0fizYk8UXlv+L37F0VEvZ2Mh5gcnTz2RDo+" +
                                                    "6XO1e8syOi73GdHycS3LhGOfwC8Hnr/Yw0xgC+LKr2yP9x31ycbDslgdNMym" +
                                                    "FhfR+dPliS8+nDjii7tkPUWFA6apE2xkVi1feD4ZZz9brIanLB7nsn8d50LO" +
                                                    "sZC/B9jQI5j3UobRJuZo159tOxfaZzbdk22zQDUBaHlS7uMD12DXLCmgsmEn" +
                                                    "nAwT2ovHEhk1LyPxYDMLjFE0x9VJMBCtzfhcES22cmnKXzR/asd3/G5MtsHF" +
                                                    "0IuGorruqi53pRVYNglpXNNiceVZ/G+YovkztDZgi5hwbYcEPfS55V56ivLY" +
                                                    "n5tsP9jjIoNciM/cRBSCAERsGrUSDivndwf7BpFEwx1DaRBvpQO++y5llcY/" +
                                                    "BRN4FRUfg0Hl8lT31gMPnz3LwS8fPiLHx/mnA3wJiQ4hiXkNM3JL8CrY3Px4" +
                                                    "7pXi5YlET+sdPLotzX7FdkQsyi/F8c/mf7rh3NRdfsf/DSBeafWjDwAA");
}
