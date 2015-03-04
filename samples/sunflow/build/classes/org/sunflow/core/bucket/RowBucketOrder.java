package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class RowBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = new int[2 *
                                                                                nbw *
                                                                                nbh];
                                                       for (int i =
                                                              0;
                                                            i <
                                                              nbw *
                                                              nbh;
                                                            i++) {
                                                           int by =
                                                             i /
                                                             nbw;
                                                           int bx =
                                                             i %
                                                             nbw;
                                                           if ((by &
                                                                  1) ==
                                                                 1)
                                                               bx =
                                                                 nbw -
                                                                   1 -
                                                                   bx;
                                                           coords[2 *
                                                                    i +
                                                                    0] =
                                                             bx;
                                                           coords[2 *
                                                                    i +
                                                                    1] =
                                                             by;
                                                       }
                                                       return coords;
    }
    public RowBucketOrder() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXaWwUVfjt9K6lF3KIUKAUtKA7HtEE61VqscXVNhRJXKP1" +
                                                    "dfbtdujbmWHmbbtU60FiIP4gRqui0f4weCMYI1FjTPjjFY2Jxmj8oah/NCKJ" +
                                                    "/PCI9/e9t7tz7LbGH24ys2/e++77HT5FajyXbHJsvifDbRFneRHfxS+Jiz0O" +
                                                    "8+LbEpcMU9djqT5OPW8H7I0anS+1/Pz7/eOtGqlNksXUsmxBhWlb3nbm2XyS" +
                                                    "pRKkxd/t5yzrCdKa2EUnqZ4TJtcTpid6EuSMAKogXYmiCDqIoIMIuhRB7/Wh" +
                                                    "AGkRs3LZPsSglvB2kztJLEFqHQPFE2RtmIhDXZotkBmWGgCFevzeCUpJ5LxL" +
                                                    "1pR0VzqXKfzQJn32kVtbX64iLUnSYlojKI4BQghgkiRNWZYdY67Xm0qxVJK0" +
                                                    "WYylRphrUm5OS7mTpN0zMxYVOZeVjISbOYe5kqdvuSYDdXNzhrDdknppk/FU" +
                                                    "8asmzWkGdF3q66o03Ir7oGCjCYK5aWqwIkr1hGmlBFkdxSjp2HUdAABqXZaJ" +
                                                    "cbvEqtqisEHale84tTL6iHBNKwOgNXYOuAiyYl6iaGuHGhM0w0YFWR6FG1ZH" +
                                                    "ANUgDYEogiyJgklK4KUVES8F/HPqhssP3G4NWJqUOcUMjvLXA1JHBGk7SzOX" +
                                                    "WQZTiE0bEw/TpW/u1wgB4CURYAXz6h2nrz6v4/i7CubsCjBDY7uYIUaNQ2PN" +
                                                    "H63s695chWLUO7ZnovNDmsvwHy6c9OQdyLylJYp4GC8eHt/+9k13P89OaqRx" +
                                                    "kNQaNs9lIY7aDDvrmJy51zKLuVSw1CBpYFaqT54PkjpYJ0yLqd2hdNpjYpBU" +
                                                    "c7lVa8tvMFEaSKCJ6mBtWmm7uHaoGJfrvEMIqYOHXARPDVE/+S9IUh+3s0yn" +
                                                    "BrVMy9Yhdhl1jXGdGbbu0azDwWtezkpze0r3XEO33Uzp27Bdpo/ljAkm9O32" +
                                                    "1Ba5GnJTzI1jjDn/K/U86tY6FYuB2VdGk55DvgzYHGBHjdnclv7TR0bf10pJ" +
                                                    "ULCKIBuAX7zAL4784opfPMyPxGKSzZnIV3kW/DIBGQ61r6l75JZtt+3vrIKQ" +
                                                    "cqaqwagI2gkaFoTpN+w+vwwMymJnQCwuf/LmffFfn7lKxaI+f82uiE2OH5y6" +
                                                    "Z+ddF2hECxdfVA62GhF9GEtmqTR2RZOuEt2Wfd/9fPThGdtPv1A1L1SFckzM" +
                                                    "6s6oG1zbYCmokz75jWvosdE3Z7o0Ug2lAsqjoBDOUHk6ojxC2d1TrJSoSw0o" +
                                                    "nLbdLOV4VCxvjWLctaf8HRkfzfhqV6GCDowIKIvs1tePP3rssU2btWA9bgl0" +
                                                    "uBEmVHa3+f7f4TIG+18cHH7woVP7bpbOB4h1lRh04bsPch28ARa7993dn5/4" +
                                                    "8tAnmh8wAppeboybRh5obPC5QCXgUI3QrV03Wlk7ZaZNOsYZxt0fLesvPPbD" +
                                                    "gVblKA47RT+f9+8E/P2ztpC737/1lw5JJmZgJ/I198GUARb7lHtdl+5BOfL3" +
                                                    "fLzq0XfoE1AooTh55jST9YZIzYg0fVx6pFu+z4+cXYCvNU7ZWV7uLC98yY+1" +
                                                    "8t2Fr3OU3XB5bhAyJtdLBFlZltmBfEYrr5qvA8nueWjv7Fxq6KkLVW62h6t6" +
                                                    "PwwtL3765wfxg1+9V6GoNAjbOZ+zScZDggHLUE24XjZnPzPue+6FV8VHmy5T" +
                                                    "LDfOXw6iiO/s/X7FjivHb/sPlWB1RPkoyeeuP/zetRuMBzRSVSoCZfNGGKkn" +
                                                    "aAZg6jIYkCw0KO40Sl93SAHawBz4kE54agvdSP7j6WIH32eqlMXXxZHg0aQ9" +
                                                    "NbBn9wJjrmtmofNOFkYDfab9xMTj372obBudIyLAbP/sfX/HD8xqgWFrXdm8" +
                                                    "E8RRA5cUeZFS8W/4xeD5Cx9UDTdUw23vK3T9NaW27zgYkWsXEkuy2Prt0Zk3" +
                                                    "np3ZpxVyZ7MgVTAe4nJAblyzQKIN4atXkLYMEyoXRtjuHBZY4L1+flPKRFeW" +
                                                    "mXt63Yd3za37GiyTJPWmB3N3r5upMM4FcH48fOLkx4tWHZEFv3qMeioeonNw" +
                                                    "+Zgbml6lBk2O/BsoqRcrpdYCobAV2QS67m9DfGzvN79K75ZlS4XoiOAn9cOP" +
                                                    "r+i78qTE9xsYYq/Ol08iYCIf96Lnsz9pnbVvaaQuSVqNwr1nJ+U57CVJMIJX" +
                                                    "vAzB3Sh0Hp7b1ZDaU8rNldHwDLCNts5gllaLUH42O/kYkfmXrFxwNVlwBRA0" +
                                                    "Lcrz0LY4szJqzBzA14iTL0vUQkVWvQPFh1prWwzbUPFMDVamHS/dtOAwX+Zn" +
                                                    "/L5CBcFIpdIQDPfMAmcmvsCkNQYKouSGIFpduW/2Zx0hO930a8teufyZuS9l" +
                                                    "486TCj1LkObw5Ihkl5fdSNUtyjgy11K/bO7Gz1RqFG86DXDdSOc4D7opsK51" +
                                                    "XJY2pRoNRafhHzSeZfNMs+AmtZAC2woeb/FReEGq8S8INinIGQEwQeoKqyDQ" +
                                                    "HqhDAITLaafo01bf3ypc8yTU0p1wgw/OTpjD8rZfbE85dd8fNY7Obbvh9tOX" +
                                                    "PiV7HbiPTk/L2yGUCzURllrc2nmpFWnVDnT/3vxSw/piNQ3NikHZcM3/AeVW" +
                                                    "aLtbEQAA");
}
