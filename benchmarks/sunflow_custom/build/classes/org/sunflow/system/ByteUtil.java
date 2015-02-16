package org.sunflow.system;
public class ByteUtil {
    public static final byte[] get2Bytes(int i) { byte[] b = new byte[2];
                                                  b[0] = (byte) (i & 255);
                                                  b[1] = (byte) (i >> 8 &
                                                                   255);
                                                  return b; }
    public static final byte[] get4Bytes(int i) { byte[] b = new byte[4];
                                                  b[0] = (byte) (i & 255);
                                                  b[1] = (byte) (i >> 8 &
                                                                   255);
                                                  b[2] = (byte) (i >> 16 &
                                                                   255);
                                                  b[3] = (byte) (i >> 24 &
                                                                   255);
                                                  return b; }
    public static final byte[] get4BytesInv(int i) { byte[] b = new byte[4];
                                                     b[3] = (byte) (i & 255);
                                                     b[2] = (byte) (i >> 8 &
                                                                      255);
                                                     b[1] = (byte) (i >> 16 &
                                                                      255);
                                                     b[0] = (byte) (i >> 24 &
                                                                      255);
                                                     return b; }
    public static final byte[] get8Bytes(long i) { byte[] b = new byte[8];
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
    public static final long toLong(byte[] in) { return (long)
                                                          ((long)
                                                             toInt(
                                                               in[0],
                                                               in[1],
                                                               in[2],
                                                               in[3]) |
                                                             (long)
                                                               toInt(
                                                                 in[4],
                                                                 in[5],
                                                                 in[6],
                                                                 in[7]) <<
                                                             (long)
                                                               32);
    }
    public static final int toInt(byte in0, byte in1, byte in2,
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
    public static final int toInt(byte[] in) { return toInt(
                                                        in[0],
                                                        in[1],
                                                        in[2],
                                                        in[3]);
    }
    public static final int toInt(byte[] in, int ofs) { return toInt(
                                                                 in[ofs +
                                                                      0],
                                                                 in[ofs +
                                                                      1],
                                                                 in[ofs +
                                                                      2],
                                                                 in[ofs +
                                                                      3]);
    }
    public static final int floatToHalf(float f) { int i =
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
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1159026718000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "j9xpFwAA");
}
