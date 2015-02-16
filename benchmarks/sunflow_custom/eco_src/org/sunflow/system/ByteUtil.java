package org.sunflow.system;
public class ByteUtil {
    final public static byte[] get2Bytes(int i) { byte[] b = new byte[2];
                                                  b[0] = (byte) (i & 255);
                                                  b[1] = (byte) (i >> 8 &
                                                                   255);
                                                  return b; }
    final public static byte[] get4Bytes(int i) { byte[] b = new byte[4];
                                                  b[0] = (byte) (i & 255);
                                                  b[1] = (byte) (i >> 8 &
                                                                   255);
                                                  b[2] = (byte) (i >> 16 &
                                                                   255);
                                                  b[3] = (byte) (i >> 24 &
                                                                   255);
                                                  return b; }
    final public static byte[] get4BytesInv(int i) { byte[] b = new byte[4];
                                                     b[3] = (byte) (i & 255);
                                                     b[2] = (byte) (i >> 8 &
                                                                      255);
                                                     b[1] = (byte) (i >> 16 &
                                                                      255);
                                                     b[0] = (byte) (i >> 24 &
                                                                      255);
                                                     return b; }
    final public static byte[] get8Bytes(long i) { byte[] b = new byte[8];
                                                   b[0] = (byte) (i & 255);
                                                   b[1] = (byte) ((long) ((long)
                                                                            i >>
                                                                            (long)
                                                                              8) &
                                                                    (long)
                                                                      255);
                                                   b[2] =
                                                     (byte)
                                                       ((long)
                                                          ((long)
                                                             i >>
                                                             (long)
                                                               16) &
                                                          (long)
                                                            255);
                                                   b[3] =
                                                     (byte)
                                                       ((long)
                                                          ((long)
                                                             i >>
                                                             (long)
                                                               24) &
                                                          (long)
                                                            255);
                                                   b[4] =
                                                     (byte)
                                                       ((long)
                                                          ((long)
                                                             i >>
                                                             (long)
                                                               32) &
                                                          (long)
                                                            255);
                                                   b[5] =
                                                     (byte)
                                                       ((long)
                                                          ((long)
                                                             i >>
                                                             (long)
                                                               40) &
                                                          (long)
                                                            255);
                                                   b[6] =
                                                     (byte)
                                                       ((long)
                                                          ((long)
                                                             i >>
                                                             (long)
                                                               48) &
                                                          (long)
                                                            255);
                                                   b[7] =
                                                     (byte)
                                                       ((long)
                                                          ((long)
                                                             i >>
                                                             (long)
                                                               56) &
                                                          (long)
                                                            255);
                                                   return b;
    }
    final public static long toLong(byte[] in) { return (long)
                                                          ((long)
                                                             ByteUtil.
                                                             toInt(
                                                               in[0],
                                                               in[1],
                                                               in[2],
                                                               in[3]) |
                                                             (long)
                                                               ByteUtil.
                                                               toInt(
                                                                 in[4],
                                                                 in[5],
                                                                 in[6],
                                                                 in[7]) <<
                                                             (long)
                                                               32);
    }
    final public static int toInt(byte in0, byte in1, byte in2,
                                  byte in3) { return in0 &
                                                255 |
                                                (in1 &
                                                   255) <<
                                                8 |
                                                (in2 &
                                                   255) <<
                                                16 |
                                                (in3 &
                                                   255) <<
                                                24; }
    final public static int toInt(byte[] in) { return ByteUtil.
                                                 toInt(
                                                   in[0],
                                                   in[1],
                                                   in[2],
                                                   in[3]);
    }
    final public static int toInt(byte[] in, int ofs) { return ByteUtil.
                                                          toInt(
                                                            in[ofs +
                                                                 0],
                                                            in[ofs +
                                                                 1],
                                                            in[ofs +
                                                                 2],
                                                            in[ofs +
                                                                 3]);
    }
    final public static int floatToHalf(float f) { int i =
                                                     Float.
                                                     floatToRawIntBits(
                                                       f);
                                                   int s =
                                                     i >>
                                                     16 &
                                                     32768;
                                                   int e =
                                                     (i >>
                                                        23 &
                                                        255) -
                                                     (127 -
                                                        15);
                                                   int m =
                                                     i &
                                                     8388607;
                                                   if (e <=
                                                         0) {
                                                       if (e <
                                                             -10) {
                                                           return 0;
                                                       }
                                                       m =
                                                         (m |
                                                            8388608) >>
                                                           1 -
                                                           e;
                                                       if ((m &
                                                              4096) ==
                                                             4096)
                                                           m +=
                                                             8192;
                                                       return s |
                                                         m >>
                                                         13;
                                                   }
                                                   else
                                                       if (e ==
                                                             255 -
                                                             (127 -
                                                                15)) {
                                                           if (m ==
                                                                 0) {
                                                               return s |
                                                                 31744;
                                                           }
                                                           else {
                                                               m >>=
                                                                 13;
                                                               return s |
                                                                 31744 |
                                                                 m |
                                                                 (m ==
                                                                    0
                                                                    ? 0
                                                                    : 1);
                                                           }
                                                       }
                                                       else {
                                                           if ((m &
                                                                  4096) ==
                                                                 4096) {
                                                               m +=
                                                                 8192;
                                                               if ((m &
                                                                      8388608) ==
                                                                     8388608) {
                                                                   m =
                                                                     0;
                                                                   e +=
                                                                     1;
                                                               }
                                                           }
                                                           if (e >
                                                                 30) {
                                                               return s |
                                                                 31744;
                                                           }
                                                           return s |
                                                             e <<
                                                             10 |
                                                             m >>
                                                             13;
                                                       }
    }
    public ByteUtil() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1159026718000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAL1Ze2wUxxkf39n4GWwMmFeCwZjwsm/9ODs2RgLHNnBwgGsD" +
       "ISbEGe/NnRf2dpfd\nOXM2KCWNCjQ0behLjdRSqKgIlDRp05ZUSig0pE2K1C" +
       "ap0khIeVRUfahNmygSoWr/6Dcze7d3e74L\nje1a2rnZnW++b77f95hvxhfe" +
       "RwWWie6ULR8dNYjl6+rvxaZFQl0qtqzt8GlQfrmguPfsZk33oLwg\n8ighis" +
       "qDsiWFMMWSEpIC3R1xE60ydHU0ourUR+LUt1dtsfltCrZkMLzv5MXKR87kV3" +
       "tQQRCVY03T\nKaaKrvWoJGpRVBHci0ewFKOKKgUVi3YE0R1Ei0W7dM2iWKPW" +
       "fvQw8gbRNENmPClaHEwIl0C4ZGAT\nRyUuXurlYoHDTJNQrGgk1JkUBzPr0m" +
       "fCsu15fZnUwKSIDe4EdfgKQOtFSa2FthmqGt6ndrYeOnXO\ni8oHULmi9TNm" +
       "MmhCQd4AKouS6BAxrc5QiIQG0AyNkFA/MRWsKmNc6gCqtJSIhmnMJFYfsXR1" +
       "hBFW\nWjGDmFxm4mMQlclMJzMmU91MYhRWiBpKvBWEVRwBtasctYW669l3UL" +
       "BEgYWZYSyTxJT8fYoGFq92\nz0jqWLsZCGBqYZTQYT0pKl/D8AFVCluqWItI" +
       "/dRUtAiQFugxkELR/KxMGdYGlvfhCBmkaK6brlcM\nAVUxB4JNoWi2m4xzAi" +
       "vNd1kpxT6rqm4ee+pbl9Zx384PEVll6y+BSQtdk/pImJhEk4mYeCvm+1rg\n" +
       "/tidHoSAeLaLWNB0Lr24I/iXn1cLmgXj0Gwb2ktkOihvPVHdd3CDjrxsGUWG" +
       "binM+Gma83DotUc6\n4gZEbVWSIxv0JQYv9/3y/sPnyd88qCSApsm6GouCH8" +
       "2Q9aihqMTcQDRiYkpCAVRMtFAXHw+gQugH\nweXF123hsEVoAOWr/NM0nb8D" +
       "RGFgwSAqhr6ihfVE38B0mPfjBkKoEB5UBo8XiT/+S5HPJ1kxLazq\nByTLlC" +
       "XdjDjvoxYlUeneUUp2MG2Y3xgUbZKG9SiRsIw1RdOliAKRKuv1ITLCfv8nbn" +
       "G2vsoDeXks\n4bkDVwWf36irIWIOymdv/PpQz+YvHBNOwRzZ1oyiBSDEZwvx" +
       "CSG+hBCUl8d5z2LChEkA0H0QmpDE\nylb079n00LEaACJuHMgHNBhpDehgr6" +
       "BH1ruc+A3wVCeDE8397u6jvltn1wonkrKn2XFnl7729LVT\nH5Wt9CDP+DmQ" +
       "aQZZuISx6WWJM5nbat1RMx7/fzy25bnfX3t7uRM/FNVmhHXmTBaWNW4bmLpM" +
       "QpDo\nHPZn5pV770M7T3hQPsQ65De+fkgdC90y0sKzI5HqmC6FQVQa1s0oVt" +
       "lQIj+V0GFTP+B84c5RwZpZ\nwk+YIV0L5Fny1qPTGt56ofRlrnEioZanbFn9" +
       "hIrwnOH4wXaTEPj+9jd7v/r194/u5k5gewGFfSw2\npCpyHKbc7UyBuFQhNz" +
       "Ab1e7QonpICSt4SCXMmf5TvrTxJ3//UoVAXYUvCaPVfTID5/u8e9Hhaw9+\n" +
       "vJCzyZPZvuCo4ZAJbWY6nDtNE4+ydcQfeeOuJ3+Fvw1pC1KFpYwRHv2Ia4Y4" +
       "jj4O7wre1rvGGlhT\nA7zrsnj1OLvwoHzofKQmtv/Vn/FVl+LU7TzVDFuw0S" +
       "GMymXPBKFVyG7SshIbnW2wtoqZYI47ejdi\naxiY+S9vfaBCvfxvEDsAYmXY" +
       "Iq1tJuSLeJqlbeqCwutXXqp66HUv8qxHJaqOQ+sx939UDI5HrGFI\nNXFj7T" +
       "q+jIoDRazluCBYQvX4RuyJGpTDPvb8nB+vOXvyHZYSjDhXb74NK39Zwtu7bQ" +
       "dj/eUuKhPd\nlW1P5Pv50V0flh3BV/eIpFOZvs/0QC3259GXyLI1j/9hnBRZ" +
       "THWjXiUjRE2RWcxEpiW7LbxccEL9\nsXPfv0hfX7VaiFyZPc+5J65cfWqsev" +
       "Uzxz9Fiqt2geBmPWNkwWe8w8orHl7RiOyWUQmlT+pIhQOE\nwnpipsaAZV/K" +
       "uEcuSnrkXGYOCZ4C2yML3B4pclGGVb0Uco+iYV6uLIccYvHCMu6KtrzkTjM3" +
       "tcI3\nlShUCiM8V944UvPiKzu+c1TgviJHGZ86a1D+7Lvv7fN++cqQmOeull" +
       "zEJxae+dNzN/pmCXcRJeWS\njKoudY4oK7n65QZz2MW5JHDqq6sWX3i47x2+" +
       "IjZvLUVeqGd5f2uONLSLNRvAcSOENrHN3GK5PwUv\nnu+YmHNPdM/sa9/9KH" +
       "e1YijSsbXVsTUcjVgvD+BYmh3GJLNBedmeix9cuUSW8ZRSpFhwsug0I+MU\n" +
       "rClzPsTnyZa3wif5jpg/hC3hV+5KP7OQT6vPud7T7dyxzf7dToEhqG8YBpRq" +
       "Qr325sYmQKMS0GDn\nPZ8S8gV1GauB7tNXSt84EWvdJOx/RwpBoPvQs5vKik" +
       "4fP8LjxoalOKWwtd8LR7C51Ukd7AdThD9d\nxSfrJpFCimWoeFTaZhCtJ252" +
       "i1de/a1uamura26ob29cSdHSHGWcz3EDtqJG1mwUCLWMm7Az8m9X\nZnxPt+" +
       "N7+u3GN+tv5sHNmmCWwGav/ZyHnsPB97Nmr3Bwv6PZgMFHH3TZ288Hh1hDp8" +
       "4azQ3tdU3N\n9S1Nt2ENv8sa+yZijSZ45tnWmDdV1jicwxqfY81BisqSmgW0" +
       "kVwGaXUM8vmpM0jjPe11je31/mYw\nyPLbMkhi2dwmhyZikwZ4am2b1E6uTT" +
       "IynKprEc7zRA4bfYM1XxQR0/aJEdPmGOjJKcxf/pa6pnvq\nW1tvI2LaXBHz" +
       "+ESs44PHb1vHPwXWGR/VpgYH1bMU9U/ePcDqlqa65tZ6fwuFvUnjsr+Xwxcu" +
       "sOYU\nlFhUD4LvsLevOMiengiyAXjabGTbJgnZfE6VzxF1NZzlT3Oo+jxrfg" +
       "ilJdUDGj9T9jua/mgimtbB\ns8bWdM3/z4caHR+6Ork+1NpQ1+yHbOn40C9y" +
       "APsqa17MAuyliQDbAs86G9h1kwSsh1N5cgHb5AD7\n5iQD608BliPFF/C7HO" +
       "heZ81vsqD724luTN02ut1T4LYpG1MBQILFYeWPOXT9K2vepaiUk2/XN2I1\n" +
       "7NL4vdvVOE5RUQJ8dsUzN+O/GeIGXg5eP/jAzeCb/xKHjsQteSlU9OGYqqYc" +
       "d1OPvtMMk4QVvuhS\ncREjXOkDiiozty92hOUdvsJ/CtKPQM8UUooK7V4q0U" +
       "045gER635s8IE5FFXwKxT2bwefuGNPvwBh\n2i5JO6PxfxolrgFi4t9Gg/Ku" +
       "p3cvih/f/gQ/8BXIKh4bY2xK4OwirhSTVwmLs3JL8HoNPfvMzhd+\n0J44nq" +
       "ZdNmY4YqMYzW5JNtBu/BfBGiinwBsAAA==");
}
