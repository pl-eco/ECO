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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
