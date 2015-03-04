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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "+ohQqBvem/8HXlq58nkXAAA=");
}
