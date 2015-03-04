package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class RandomBucketOrder implements BucketOrder {
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
                                                       long seed =
                                                         2463534242L;
                                                       for (int i =
                                                              0;
                                                            i <
                                                              coords.
                                                                length;
                                                            i++) {
                                                           seed =
                                                             xorshift(
                                                               seed);
                                                           int src =
                                                             mod(
                                                               (int)
                                                                 seed,
                                                               nbw *
                                                                 nbh);
                                                           seed =
                                                             xorshift(
                                                               seed);
                                                           int dst =
                                                             mod(
                                                               (int)
                                                                 seed,
                                                               nbw *
                                                                 nbh);
                                                           int tmp =
                                                             coords[2 *
                                                                      src +
                                                                      0];
                                                           coords[2 *
                                                                    src +
                                                                    0] =
                                                             coords[2 *
                                                                      dst +
                                                                      0];
                                                           coords[2 *
                                                                    dst +
                                                                    0] =
                                                             tmp;
                                                           tmp =
                                                             coords[2 *
                                                                      src +
                                                                      1];
                                                           coords[2 *
                                                                    src +
                                                                    1] =
                                                             coords[2 *
                                                                      dst +
                                                                      1];
                                                           coords[2 *
                                                                    dst +
                                                                    1] =
                                                             tmp;
                                                       }
                                                       return coords;
    }
    private int mod(int a, int b) { int m = a % b;
                                    return m < 0 ? m + b : m;
    }
    private long xorshift(long y) { y = y ^ y << 13;
                                    y = y ^ y >>> 17;
                                    y = y ^ y << 5;
                                    return y; }
    public RandomBucketOrder() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVYXWwc1RW+O/43Trx2yA9p4iS2A3ECO9AqlVJTWmfrYIcF" +
                                                    "W3GI1EVluTt7d3fi+cvMXXtjcCGRaFJUpag1NFTUD1X4bUhQRQSoQspLC4iq" +
                                                    "EqhqxQMEeAE1jdQ8lKLSlp5z787O7OyuUZ9qae7eufece8/vd8747BXS5rlk" +
                                                    "l2MbRwuGzROszBOHjd0JftRhXmJ/avc0dT2WSxrU8w7CWkYbfLH3088fLcYV" +
                                                    "0p4ma6hl2Zxy3ba8A8yzjTmWS5HeYHXcYKbHSTx1mM5RtcR1Q03pHh9NkWtC" +
                                                    "rJwMp3wRVBBBBRFUIYI6FlAB0ypmlcwkclCLe0fI90ksRdodDcXjZFvtIQ51" +
                                                    "qVk5ZlpoACd04vshUEowl12ytaq71LlO4cd2qUs/uzf+6xbSmya9ujWD4mgg" +
                                                    "BIdL0qTHZGaWud5YLsdyadJnMZabYa5ODX1ByJ0m/Z5esCgvuaxqJFwsOcwV" +
                                                    "dwaW69FQN7ekcdutqpfXmZHz39ryBi2ArusCXaWG+3AdFOzWQTA3TzXms7TO" +
                                                    "6laOky1RjqqOw3cAAbB2mIwX7epVrRaFBdIvfWdQq6DOcFe3CkDaZpfgFk42" +
                                                    "Nj0Ube1QbZYWWIaTDVG6abkFVF3CEMjCydoomTgJvLQx4qWQf67cdeup+60J" +
                                                    "SxEy55hmoPydwDQQYTrA8sxllsYkY8/O1ON03WsnFUKAeG2EWNK8/MDVb984" +
                                                    "cPENSfOVBjRT2cNM4xntTHb125uSI3taUIxOx/Z0dH6N5iL8pys7o2UHMm9d" +
                                                    "9UTcTPibFw/87rsPPc8uK6R7krRrtlEyIY76NNt0dIO5tzOLuZSz3CTpYlYu" +
                                                    "KfYnSQfMU7rF5OpUPu8xPklaDbHUbot3MFEejkATdcBct/K2P3coL4p52SGE" +
                                                    "dMBDdsPTRuSf+OXEUYu2yVSqUUu3bBVil1FXK6pMszMuc2x1PDmlZsHKRZO6" +
                                                    "s57qlay8Yc9ntJLHbVP1XE213YK/rGq2y9RsSZtlXD1ArZxt7hUvU26OuQmM" +
                                                    "POf/cGcZ7RCfj8XARZuiAGFAbk3YBtBmtKXS3vGr5zJvKdWEqViQkxG4MlG5" +
                                                    "MoFXJuSViborSSwmbroWr5aBAG6cBUAAqOwZmfne/vtODrZABDrzreADJB0E" +
                                                    "1SvyjGt2MkCNSYGNGoTuhl/ecyLx2TPfkqGrNof4htzk4un5Y4cevFkhSi1W" +
                                                    "o36w1I3s04iwVSQdjuZoo3N7T3zy6fnHF+0gW2vAvwIi9ZwIAoNRT7i2xnIA" +
                                                    "q8HxO7fSC5nXFocV0grIAmjKKUQ/ANVA9I4aMBj1gRV1aQOF87ZrUgO3fDTs" +
                                                    "5kXXng9WRIisxqFfRgs6MCKgwOR9r1584sLPd+1RwvDdGyqIM4xLMOgL/H/Q" +
                                                    "ZQzW3zs9/dPHrpy4RzgfKIYaXTCMYxKgAbwBFnv4jSPvXnr/zB+VIGA41MhS" +
                                                    "1tC1MpxxfXALAIcB4IVuHb7bMu2cntdp1mAYd//q3X7Lhb+eiktHGbDi+/nG" +
                                                    "Lz8gWL9uL3norXv/MSCOiWlYuALNAzJpgDXByWOuS4+iHOVj72x+4nX6C8BV" +
                                                    "wDJPX2ACnojQjAjTJ4RHRsR4U2TvZhy2OnV7ZbGyofImXraJcRiHG6TdcLoj" +
                                                    "TBkT87WcbKpL7lA+o5U3NytYotieOb60nJt66haZm/21RWAcepwX/vTv3ydO" +
                                                    "f/BmA1zp4rZzk8HmmBESrAWvrMGEO0UtDzLjked+9TJ/e9c35JU7m8NBlPH1" +
                                                    "43/ZePC24n3/AxJsiSgfPfK5O8++efv12k8U0lIFgbr2pJZpNGwGuNRl0E9Z" +
                                                    "aFBc6Ra+HhAC9IE58CGD8LRXipf4xd01Do7XypTF4WuR4FGEPRWw58gKXbGr" +
                                                    "m1Co5yqdhLrYf2n2yU9ekLaNth0RYnZy6ZEvEqeWlFBvNlTXHoV5ZH8mRF4l" +
                                                    "VfwC/mLw/AcfVA0XZH3uT1aahK3VLsFxMCK3rSSWuGLfx+cXf/Ps4gmlkjt7" +
                                                    "OGmBbhKnE2LhOysk2hQOY5z0FRiXuTDDjpQQYOHu7c1NKRJdWmb56aE/PLg8" +
                                                    "9CFYJk06dQ/a9DG30KD7C/H87eyly++s2nxOAH5rlnoyHqJtc31XXNPsCg16" +
                                                    "HPEzUVUvVi23K4TCPrwmVHX/OWVkj3/0mfBuXbY0iI4If1o9++TG5G2XBX9Q" +
                                                    "wJB7S7m+GQETBbxffd78uzLY/luFdKRJXKt8Jh2iRglrSRqM4PnfTvApVbNf" +
                                                    "2+bLnna0mpubouEZujZaOsNZ2spr8nO1U44RkX/pxoCrCMDlcKBuUaMMZctg" +
                                                    "VkF2pRM4zDjlukStILKsHSg+YK1tMSxD/p5srHQ7Uf0wg81ynZ/x/ZsyCGYa" +
                                                    "QUM43Asr7Ok4gEnbNBREyg1BtKVx3Rw3HS4q3cIr61+69Znl90XhLpP6miUF" +
                                                    "rIJcHBfXwDNUAbmhhiDXpLZx0uG4+hzgQ7kxAlaNLnOfr6DwHA7wrdMCHYDg" +
                                                    "+FLZBUBfB8+Oiuw7mgK0E7k47K1xHCbl4XdwaFNsqyA4H1hB2mM4HOXwiQgf" +
                                                    "2UU9LwBusUGLAGBW16ujIzfU/ctAfuZq55Z7O9cv3/1nCUb+p2gXfA/mS4YR" +
                                                    "TozQvN1xWV4XknX5aYI/P+BkfZNPCEgMOREyPyzpf8hJPEoPJsGfMNmPOLkm" +
                                                    "RAZhUJmFiX4MvgQinD7q+FkUDzJMAkSZ1DRRTm1LFe5WETXFv2P8hqAk/yGT" +
                                                    "0c4v77/r/qtff0p0F5AwdGEBT+kEgJY9eLWp2Nb0NP+s9omRz1e/2LXdr181" +
                                                    "3XlYNpwb/wUW2DZ6/BIAAA==");
}
