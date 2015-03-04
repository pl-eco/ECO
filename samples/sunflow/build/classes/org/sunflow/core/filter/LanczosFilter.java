package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class LanczosFilter implements Filter {
    public float getSize() { return 4.0F; }
    public float get(float x, float y) { return sinc1d(x * 0.5F) * sinc1d(
                                                                     y *
                                                                       0.5F);
    }
    private float sinc1d(float x) { x = Math.abs(x);
                                    if (x < 1.0E-5F) return 1;
                                    if (x > 1.0F) return 0;
                                    x *= Math.PI;
                                    float sinc = (float) Math.sin(3 * x) /
                                      (3 *
                                         x);
                                    float lanczos = (float) Math.sin(x) /
                                      x;
                                    return sinc * lanczos; }
    public LanczosFilter() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXXWwURRyf236X0mvLV0UobSloKexCDCZY/Ci1hcIBTQsY" +
                                                    "SqRMd+eu2+7tLrtz7VGsAokp4YEYLQhG+2AgiPIVI0FjSPokEHzBGI0Pgm8S" +
                                                    "lQde0AQV/zNzd3u3dy364CU7tzvzn//3/zf/OXcPFbgOarItY1/EsKhM4lQe" +
                                                    "MFbLdJ9NXHljaHUndlyitRrYdbfBXK9afyn44OFb/RUSKuxBs7BpWhRT3TLd" +
                                                    "LuJaxhDRQijozbYZJOpSVBEawENYiVHdUEK6S5tDaEbaVooaQkkVFFBBARUU" +
                                                    "roLS4lHBppnEjEVb2Q5sUncveh0FQqjQVpl6FNVlMrGxg6MJNp3cAuBQzL53" +
                                                    "gFF8c9xBtSnbhc1ZBh9rUsbf3V3xaR4K9qCgbnYzdVRQgoKQHlQWJdE+4rgt" +
                                                    "mka0HlRpEqJ1E0fHhj7C9e5BVa4eMTGNOSTlJDYZs4nDZXqeK1OZbU5MpZaT" +
                                                    "Mi+sE0NLfhWEDRwBW+d6tgoL29k8GFiqg2JOGKskuSV/UDc1ihb5d6RsbNgE" +
                                                    "BLC1KEpov5USlW9imEBVInYGNiNKN3V0MwKkBVYMpFA0f0qmzNc2VgdxhPRS" +
                                                    "VO2n6xRLQFXCHcG2UDTHT8Y5QZTm+6KUFp97W9Ye3W9uMCWus0ZUg+lfDJtq" +
                                                    "fJu6SJg4xFSJ2Fi2LHQcz716WEIIiOf4iAXNldfuv7S8ZvK6oHkyB83WvgGi" +
                                                    "0l71VF/5rQWtjWvymBrFtuXqLPgZlvP070ysNMdtqLy5KY5sUU4uTnZ9tfPA" +
                                                    "x+RXCZV2oELVMmJRyKNK1YraukGc9cQkDqZE60AlxNRa+XoHKoL3kG4SMbs1" +
                                                    "HHYJ7UD5Bp8qtPg3uCgMLJiLiuBdN8NW8t3GtJ+/x22EUBE8aDk8BUj8+D9F" +
                                                    "O5V+K0oUrGJTNy0FcpdgR+1XiGopLo7aBkTNjZlhwxpWXEdVLCeS+lYthygg" +
                                                    "HlJHCWFTHbHcdv4lsxSz/0/mcWZZxXAgAE5f4C95A6plg2VoxOlVx2Pr2u5f" +
                                                    "6L0ppUog4ROKloA4OSFOZuJkIU7OEIcCAS5lNhMrwgpBGYTyBuAra+x+deOe" +
                                                    "w/V5kE/2cD54lJHWg30JXdpUq9XDgA6OdCokYvWHu8bkP868KBJRmRqwc+5G" +
                                                    "kyeGD+54Y6WEpEzkZbbBVCnb3snwMoWLDf6Ky8U3OHb3wcXjo5ZXexlQnoCE" +
                                                    "7J2spOv9UXAslWgAkh77ZbX4cu/V0QYJ5QNOADZSDLkMsFPjl5FR2s1JmGS2" +
                                                    "FIDBYcuJYoMtJbGtlPY71rA3w9OjnA1VIlNYAH0KcoRt/2Ly5OX3mtZI6WAc" +
                                                    "TDveugkVpV3pxX+bQwjM/3ii851j98Z28eADxeJcAhrY2AqFDtEAj715fe8P" +
                                                    "d26f+lbyEobCiRfrM3Q1DjyWelIABgyAIhbWhu1m1NL0sI77DMLy7s/gklWX" +
                                                    "fztaIQJlwEwyzssfz8Cbf2IdOnBz9+81nE1AZceQZ7lHJhwwy+Pc4jh4H9Mj" +
                                                    "fvCbhSev4Q8AJQGZXH2EcLBB3DLEXS/ziDTycYVvbSUbau2stTifqU588Y86" +
                                                    "Pjaw4SnhN/b6dDplgL/PoWheVmGLUmYOXjjVycNPzVOHxie0radXibKsykTz" +
                                                    "NmhWzn/319fyiZ9u5ICTEmrZKwwyRIw0nfKYyAw42MwPZa8ojpz95Aq91fSc" +
                                                    "ELlsaiTwb7x26Jf5217o3/MfQGCRz3g/y7Obz91Yv1R9W0J5qfrP6jMyNzWn" +
                                                    "uwGEOgQaI5M5lM2U8jDXcAUqwR3sQdXwFCZOIf7PVmfZbJwtqpUNz0yTNy9P" +
                                                    "s9bOhhaKiiKEdkNCQgAap+mHHT0KR/RQoodQRqvuDL5/97wIhr/h8BGTw+NH" +
                                                    "HslHx6W0rmxxVmOUvkd0ZlzNmcInj+AXgOdv9jAb2IQ4mataE+1Bbao/sG2W" +
                                                    "wnXTqcVFtP98cfTLj0bHpIRP1lCGbRam2aXGJ57PDNBseEoTASr91wGSOEeJ" +
                                                    "fW7iAyfdPk2oXmFDF0V5ECq+47HqVSXzpzyhXnlO9aZADEgK29GHwJNxn1YB" +
                                                    "D1KE2numUbuPDT0A265uqqu0KTSPUzQzo5FgGFqddTsRHbV6YSJYPG9i+/f8" +
                                                    "aEx1vSXQeoZjhpFWYOnFVmg7JKxznUrEiWfzv0guCBS9DWX3D/bC9Q0L+gG4" +
                                                    "0fnpKcpnf+lkUYpmpJGBNxNv6UQAhHlAxF732klAruBHB7tyyKK/jqMMhLcz" +
                                                    "8T79KGUVy29+SciKibtfr3pxYuOW/fefPc3xrwDujCMj/KYAFx/RIKRgr25K" +
                                                    "bklehRsaH5ZfKlmSLJiM1sGn26LcJ2xb1Kb8TBz5fN5na89M3OZH/D8+zhDx" +
                                                    "kg8AAA==");
}
