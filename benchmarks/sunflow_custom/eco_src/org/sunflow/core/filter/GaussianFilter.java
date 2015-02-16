package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class GaussianFilter implements Filter {
    private float s;
    private float es2;
    public GaussianFilter(float size) { super();
                                        s = size;
                                        es2 = (float) -Math.exp(-s * s); }
    public float getSize() { return s; }
    public float get(float x, float y) { float gx = (float) Math.exp(-x *
                                                                       x) +
                                           es2;
                                         float gy = (float) Math.exp(-y *
                                                                       y) +
                                           es2;
                                         return gx * gy; }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026718000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Ye2gcRRif3OXRPGwuaZs+bJsmjVX7uFWpoo2oNSZt2quN" +
                                                   "SRM1raaT2bnLNnu7\n6+7s5RKLVoS2Kj6KCgpaixRa34JKFXxUfFsEFVQQrE" +
                                                   "pBBR8gglb0D7+Z2b293btEMbC7czvfc+b3\n/ebbPP0zqnJstJg4STZpUSfZ" +
                                                   "NdCHbYeqXTp2nG3waoS8U1Xbd2SzYcZQRQrFNJWhxhRxFBUzrGiq\n0ntVZ9" +
                                                   "5GqyxTn8zoJkvSPEvu0i/07G1KXVhi8NqDx5pvO1zZGkNVKdSIDcNkmGmm0a" +
                                                   "3TrMNQIrUL\n57DiMk1XUprDOlPoDGq42S7TcBg2mHMTugXFU6jaItwmQ20p" +
                                                   "37kCzhUL2zirCPdKn3ALFubYlGHN\noOr6gjvQXB3WhLA9vf5SaTAyi08OQT" +
                                                   "oiAsh6WSFrmW1Jqlb86NBFuw89EUeNw6hRMwa4MQKZMPA3\njBqyNDtKbWe9" +
                                                   "qlJ1GDUZlKoD1Nawrk0Jr8Oo2dEyBmauTZ1+6ph6jgs2O65FbeHTf5lCDYTn" +
                                                   "ZLuE\nmXZhjdIa1VX/V1VaxxlIuyVIW6bbw99DgnUaBGanMaG+SuW4ZsCOt0" +
                                                   "Y1Cjl2bAYBUK3JUjZmFlxV\nGhheoGa5lzo2MsoAszUjA6JVpgteGFo0rVG+" +
                                                   "1hYm4zhDRxhaEJXrk1MgVSsWgqswNC8qJizBLi2K\n7FLR/qxq+X3/0Udev0" +
                                                   "Jgu1KlROfx14HS0ohSP01TmxqESsXTbvKB3uvdxTGEQHheRFjKrD/r2GDq\n" +
                                                   "hzdapcyZZWS2ju6ihI2Qqw+09t+8wURxHsYsy3Q0vvmhzEU59HkznXkLqral" +
                                                   "YJFPJv3J4/3vXr/n\nSfpjDNX1ompi6m4WcNREzKyl6dTeQA1qY0bVXlRLDb" +
                                                   "VLzPeiGhinAPLy7dZ02qGsF1Xq4lW1KX7D\nEqXBBF+iWhhrRtr0xxZmY2Kc" +
                                                   "txBCNXChNXBVIfknngxdklQc10jr5oTi2EQx7UzhNzFtqoB1QIay\nAbuOo2" +
                                                   "GjR/xMcghZDA0qY2aWKphgQzNMJaNB0RJzjUpz/Pl/Ded51M0TFRWcBqPlrE" +
                                                   "MlbDR1ldoj\n5MipD3d3b75jv4QKh7eXL0MrwF/S85fk/pLSXzLsD1VUCDdz" +
                                                   "uV+5Z7Di41C7wHIN5w7csGnn/vY4\ngMWaqITl4qLtkJkXTDcxu4IC7xVcSA" +
                                                   "BlCx7fvi95+sjlEmXK9DxcVrv+42dOHPqtYWUMxcqTJE8S\naLqOm+njzFog" +
                                                   "v45oWZWz/8udW174/MRX5wQFxlBHSd2XavK6bY9uh20SqgITBuYPL2yMX4uG" +
                                                   "DsRQ\nJZABEKCIH7hladRHqH47fS7kudSkUH3atLNY51M+gdWxMducCN4InC" +
                                                   "TEeA5sTj0H9EK4aj2Eiyef\nnWfxe4vEFd/tSBaCa0/fXn3eF6/WvyOWxafl" +
                                                   "xqKDb4AyWeRNAVi22ZTC+68e6rv/wZ/3bRdI8aDC\n4DR0R3WN5EFlRaAC1a" +
                                                   "0Dw/CN7Bg0sqaqpTU8qlOOuL8bzzr/pZ/uScit0eGNv7Or/91A8H7hlWjP\n" +
                                                   "iRv/WCrMVBB+ugRpBGIymzmB5fW2jSd5HPnbPl3y8Hv4USA/IBxHm6KCQyoK" +
                                                   "RbCguDuxtSywXE5s\n46m97a+9P/jYPgn9c2doQYq1RsitX38zHr/3zVGpF2" +
                                                   "X6iPCBpYe/e+FU/1y5TPI4XF5yIhXryCNR\nAKDR4hvSNpMHIf32qranb+k/" +
                                                   "6UXUHCb2bmh+vp98i5596d3flmEfgI+JmXCnCHyuFPckB6RYQyTm\n1vFbO0" +
                                                   "Szepp1KtPzjJDdT2ba3Zs+eEX4rcfFzVMxXLdgSyac4LflPOn5UZbbiJ0xkF" +
                                                   "t7/OodCf34\nX2BxGCwS6DWcrTZQbD4Edk+6qubLN99q2flJHMV6UB0kqvZg" +
                                                   "wROoFgqUOmPAznnr8itEDSYmZvG7\nSBmJRVjkLUC+6FfMmRErPbxjChhmZH" +
                                                   "T10dSHWx8VCzAtQZaBUcTO1OuDB09/xE4KOwFTce22fOnR\nA11moHvx57mm" +
                                                   "6ucfy8ZQzTBKEK8PHsK6y/lgGNo2x2+OoVcOzYdbMNlvdBaYeHEUx0VuoxwZ" +
                                                   "gA7G\nXJqPGyK02MBXey5c1R4tVkdpsQKJwUah0iHuZxdIrMaytRzmvTEUPp" +
                                                   "9aK1mU3zv5rVdu5GXTbnhX\nOJQWvxnxn2VC6eO3TQzFqXNBOZ/XzOBTpL+i" +
                                                   "CFwy7PkMzS/pCWQTwEtjyXQNqeCCfdf92rAXv31D\nzCvcixhg3bTW6DRH9Q" +
                                                   "iOl4T6gy2iBQ9wc+cTTx1jn6xaJ1ll5fSYjyquXHdoqnXdc3f9j66gNZJb\n" +
                                                   "1HRT7sxr4mPa+zHxlSBhWPJ1EVbqDIOvDuJxbWNbCILLwifzIrhme/s+u+zJ" +
                                                   "HGxcebokM8wJvzsB\nrhnKBuC8EqgJAIP/DaQ+K4kfO8KRr4Cr2Yu8+T9HHp" +
                                                   "OIKMB3rRDNzpCD6Nx3Aeohh0j84/81fijT\n2eEWl5/xC0o+iuWHHEl9efOO" +
                                                   "31Of/SmatcLHVj188aRdXS+ml6JxtWXTtCZCrpdkY4lHrlyJybab\n8c9ePh" +
                                                   "DBulJ+kqFEVJ6hSv4oFtvNUH2RGOyxNyoWuhWWDYT4cI/lF3xCnF2cZpOSZv" +
                                                   "OhteIrszxU\ngeL/FH6VuPI/FSPkume2L8vfte0+UXpVRMdTU+KTFL6wZZNa" +
                                                   "qLS2aa35tj5Gzz839Oqzl/hMIg7n\nufmA30JA7JSzM+w9VHf5zrA7azHRy0" +
                                                   "29PP/FS48cPBkTvek/aud3DF4SAAA=");
}
