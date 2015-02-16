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
    public static final long jlc$SourceLastModified$jl7 = 1159026716000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfW9vnP3Fix4kTkwY7MQ5q/nDbNKIVNUqbnGzi" +
                                                    "9CBWnKTqUTBzu3Pnjfd2N7Nz9sWpW4hUJeJDVBUDoaL+UAVRaCCoagSoQsqX" +
                                                    "FhD9QlWB+MAf9UsRNB/yoRSVtvS9mb3dvb2zC19qaeZmZ96b9968937zxpev" +
                                                    "kzafk92ea58u2a7IsKrInLRvz4jTHvMzh3O3T1LuMzNrU98/BnPTxvALPZ98" +
                                                    "9tOZXo2k82QDdRxXUGG5jn+U+a49x8wc6Ylmx2xW9gXpzZ2kc1SvCMvWc5Yv" +
                                                    "RnNkTYxVkJFcTQUdVNBBBV2qoB+IqIBpLXMq5SxyUEf4p8iPSCpH0p6B6gmy" +
                                                    "vX4Tj3JaDraZlBbADh34fQKMksxVTraFtiubGwx+dLe+9Pj9vb9pIT150mM5" +
                                                    "U6iOAUoIEJIn3WVWLjDuHzBNZubJeocxc4pxi9rWgtQ7T/p8q+RQUeEsPCSc" +
                                                    "rHiMS5nRyXUbaBuvGMLloXlFi9lm7autaNMS2LopslVZOI7zYGCXBYrxIjVY" +
                                                    "jaV11nJMQYaSHKGNI98FAmBtLzMx44aiWh0KE6RP+c6mTkmfEtxySkDa5lZA" +
                                                    "iiBbVtwUz9qjxiwtsWlBBpJ0k2oJqDrlQSCLIP1JMrkTeGlLwksx/1y/584L" +
                                                    "Z5xDjiZ1Nplho/4dwDSYYDrKiowzx2CKsXtX7jG66ZXzGiFA3J8gVjQv/vDG" +
                                                    "d/YMXntN0XylCc2RwklmiGnjUmHdm1uzO+9oQTU6PNe30Pl1lsvwnwxWRqse" +
                                                    "ZN6mcEdczNQWrx39w/cffJZ9rJGuCZI2XLtShjhab7hlz7IZv4s5jFPBzAnS" +
                                                    "yRwzK9cnSDuMc5bD1OyRYtFnYoK02nIq7cpvOKIibIFH1A5jyym6tbFHxYwc" +
                                                    "Vz1CSDs0sg9aG1F/8leQWf24D+GuU4M6luPqELyMcmNGZ4Y7XYDTnSlTPuvr" +
                                                    "RsUXbln3K07Rdud1nxu6y0vht+FypoMmEEX6QRuCoUydQ5Rzyx+XkxkMOu//" +
                                                    "K66K1vfOp1LgmK1JWAAmdsi1TcanjaXKwbEbz0+/oYVpEpybIHtAaiaQmkGp" +
                                                    "GSU100wqSaWksI0oXUUA+G8WkAAwsnvn1H2HHzg/3AKh5823wuEj6TDYHag0" +
                                                    "ZrjZCC4mJCgaELMDv7z3XObTp7+tYlZfGdubcpNrF+cfOvHjr2lEqwdpNBGm" +
                                                    "upB9EqE1hNCRZHI227fn3IefXHls0Y3StA71A/Ro5MTsH046g7sGMwFPo+13" +
                                                    "baNXp19ZHNFIK0AKwKigEPaAUINJGXUoMFpDVLSlDQwuurxMbVyqwWCXmOHu" +
                                                    "fDQjo2SdHK8Hp6zBtBiC1hHkifzF1Q0e9htVVKGXE1ZIxB5/+doTV3+++w4t" +
                                                    "Du49setyigkFFeujIDnGGYP5dy9OPvLo9XP3yggBiluaCRjBPgvAAS6DY/3J" +
                                                    "a6feef+9S3/WoqgScINWCrZlVGGPWyMpACs2QBv6fuS4U3ZNq2jRgs0wOP/V" +
                                                    "s2Pv1b9d6FXetGGmFgx7/vcG0fxNB8mDb9z/j0G5TcrAay2yPCJTB7Ah2vkA" +
                                                    "5/Q06lF96E83P/Eq/QWgLiCdby0wCV6pMF92rlLacKsMaDsXXAf6Yt/7s09+" +
                                                    "+JxKm+TdkSBm55ce/jxzYUmLXbC3NNxxcR51ycpgWKuC53P4S0H7DzYMGpxQ" +
                                                    "INuXDZB+Wwj1nofu2b6aWlLE+F+vLP7uV4vnlBl99ffLGJRPz7317z9mLn7w" +
                                                    "ehPwgvhzqZA66lLHXbLPoFLyRIlcG8Vum9ewVpUzA/JLW/3sx7GmiUHWP4/Y" +
                                                    "hbN/+VTq1AA6TdyR4M/rl5/ckt3/seSPsh+5h6qNYA71X8T79WfLf9eG07/X" +
                                                    "SHue9BpBcXmC2hXMsTwUVH6t4oQCtG69vjhSlcBoiG5bk/EQE5vEncgPMEZq" +
                                                    "HHcloKYbT/kmaOkAatJJqEkROchKlmHZ78Duq7VMb/e4NUexcoXswJV9Ep2U" +
                                                    "L/d/eUkT2I0J0mI5c7HtpOIjsXBQwvoF2dxwP6qrEIP75pWKPBnYl84uLZtH" +
                                                    "ntqrBVH4TUE6hevdZrM5ZsdEteBOdbfk3bKsjTz+8DO/flG8uftbKkV2rRyl" +
                                                    "ScZXz3605dj+mQe+xN04lLApueUzd19+/a5bjZ9ppCUMnIZKvZ5ptD5cujiD" +
                                                    "p4VzrC5oBkNXYiMD0NYGrlzb9H6KHNY85/OrrP0Au+9BcJWYmAIIloHQCA9y" +
                                                    "YqpesY21Qe33CymmKYgJA26fJC2soqKJHbxDWkDFL6ZeH05ugtYfqNe/onpj" +
                                                    "CcHB7RNpdnIVzeRrpSRIa2Fmr7mCapCtG5vVj3grDjS8X9Wby3h+uadj8/Lx" +
                                                    "t2VFFL6LOuFxUqzYdhxvYuO0x1nRkop1KvRRuXyqWeaqylbgCxUHUm1P0Qt4" +
                                                    "8yfpwUb8iZPNC7ImRgYhFIziRAvgNiDC4RmvhiO9shhA3M0o3K2SGAZgPRT/" +
                                                    "qiuOMM3l/wZqKVlR/x2YNq4sH77nzI1vPCXzu82w6cKCfEvC01jVhWFab19x" +
                                                    "t9pe6UM7P1v3QueOGlytw64vKAYTug01r5nGyp6QVc7CS5t/e+fTy+/Jou2/" +
                                                    "GBDPi7QRAAA=");
}
