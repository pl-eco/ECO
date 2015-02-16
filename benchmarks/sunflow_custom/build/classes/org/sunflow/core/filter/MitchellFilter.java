package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class MitchellFilter implements Filter {
    public float getSize() { return 4.0F; }
    public float get(float x, float y) { return mitchell(x) * mitchell(y);
    }
    private float mitchell(float x) { final float B = 1 / 3.0F;
                                      final float C = 1 / 3.0F;
                                      final float SIXTH = 1 / 6.0F;
                                      x = Math.abs(x);
                                      float x2 = x * x;
                                      if (x > 1.0F) return ((-B - 6 * C) *
                                                              x *
                                                              x2 +
                                                              (6 *
                                                                 B +
                                                                 30 *
                                                                 C) *
                                                              x2 +
                                                              (-12 *
                                                                 B -
                                                                 48 *
                                                                 C) *
                                                              x +
                                                              (8 *
                                                                 B +
                                                                 24 *
                                                                 C)) * SIXTH;
                                      return ((12 - 9 * B - 6 * C) * x * x2 +
                                                (-18 +
                                                   12 *
                                                   B +
                                                   6 *
                                                   C) *
                                                x2 +
                                                (6 -
                                                   2 *
                                                   B)) * SIXTH; }
    public MitchellFilter() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1159026718000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XW2xURRiePb2X0m3LrVYobSloKeyBGEyweCm1hcICTUsx" +
                                                    "lsgyPTu7e9pz45zZdilWgcRAeCBGC4LRPhgIotxiJGgMSZ8Egi8Yo/FB8E2i" +
                                                    "8sALmqDiPzO7e3bPbqu+uMmZPWfm/+e/f/PPuXuoyLFRq2Vqe6OaSQMkQQND" +
                                                    "2poA3WsRJ7ApuKYH2w4Jd2jYcbbDXEhpuuR/8PDNWJWEigfQHGwYJsVUNQ2n" +
                                                    "lzimNkLCQeR3Zzs1ojsUVQWH8AiW41TV5KDq0LYgmpXBSlFzMKWCDCrIoILM" +
                                                    "VZDbXSpgmk2MuN7BOLBBnT3oNeQLomJLYepR1Ji9iYVtrCe36eEWwA6l7HsH" +
                                                    "GMWZEzZqSNsubM4x+FirPPHOrqpPCpB/APlVo4+po4ASFIQMoAqd6IPEdtrD" +
                                                    "YRIeQNUGIeE+YqtYU8e43gOoxlGjBqZxm6SdxCbjFrG5TNdzFQqzzY4r1LTT" +
                                                    "5kVUooVTX0URDUfB1vmurcLCLjYPBparoJgdwQpJsRQOq0aYosVejrSNzZuB" +
                                                    "AFhLdEJjZlpUoYFhAtWI2GnYiMp91FaNKJAWmXGQQlHdtJsyX1tYGcZREqKo" +
                                                    "1kvXI5aAqow7grFQNM9LxneCKNV5opQRn3tb1x3dZ2w0JK5zmCga078UmOo9" +
                                                    "TL0kQmxiKEQwViwPHsfzrx6WEALieR5iQXPl1fsvrKifui5oHs9Ds21wiCg0" +
                                                    "pJwarLy1sKNlbQFTo9QyHZUFP8tynv49yZW2hAWVNz+9I1sMpBaner98ef9H" +
                                                    "5BcJlXejYsXU4jrkUbVi6paqEXsDMYiNKQl3ozJihDv4ejcqgfegahAxuy0S" +
                                                    "cQjtRoUanyo2+Te4KAJbMBeVwLtqRMzUu4VpjL8nLIRQCTxoJTxFSPz4P0VE" +
                                                    "7ncg3WWsYEM1TBmSl2BbiclEMUOD4N2Yju1hR1biDjV12YkbEc0clR1bkU07" +
                                                    "mv5WTJvIoAlkkbxFpUqMaFoX/wywdLP+L0EJZnHVqM8HwVjohQINqmijqYWJ" +
                                                    "HVIm4us7718I3ZTSpZH0FUXLQF4gKS/A5AWEvEC2POTzcTFzmVwRb4jWMNQ9" +
                                                    "IGJFS98rm3YfbiqARLNGC8HVjLQJbE0q06mYHS44dHMIVCBDaz/YeSjw+5nn" +
                                                    "RYbK0yN5Xm40dWL0wI7XV0lIyoZkZhxMlTP2HgakacBs9pZivn39h+4+uHh8" +
                                                    "3HSLMgvjk1iRy8lqvckbBttUSBjQ091+eQO+HLo63iyhQgAQAE2KIckBj+q9" +
                                                    "MrJqvi2Fn8yWIjA4Yto61thSCvTKacw2R90Znh+VbKgRqcIC6FGQQ2/X51Mn" +
                                                    "L7/bulbKRGl/xrnXR6io+Wo3/tttQmD+hxM9bx+7d2gnDz5QLMknoJmNHYAA" +
                                                    "EA3w2BvX93x/5/apbyQ3YSgchfFBTVUSsMcyVwrggwYYxcLa3G/oZliNqHhQ" +
                                                    "Iyzv/vAvXX3516NVIlAazKTivOKfN3DnH1uP9t/c9Vs938ansPPJtdwlEw6Y" +
                                                    "4+7cbtt4L9MjceDrRSev4fcBPgGyHHWMcBRC3DLEXR/gEWnh40rP2io2NFg5" +
                                                    "awk+U5v84h+NfGxmwxPCb+z1yUxKH3+fR9GCnMoWpcwcvGi6I4kfp6cOTkyG" +
                                                    "t51eLcqyJhvmO6GLOf/tn18FTvx4Iw+elFHTWqmREaJl6FTARGbBwRZ+WrtF" +
                                                    "ceTsx1fordZnhMjl0yOBl/HawZ/rtj8X2/0fQGCxx3jvlme3nLuxYZnyloQK" +
                                                    "0vWf04BkM7VlugGE2gQ6JoM5lM2U8zDXcwWqwR3sQbXwFCePJ/7PVudYbJwr" +
                                                    "qpUNT82QNy/OsNbFhnaKSqKE9kFCQgBaZmiUbVWHs3sk2VzI4zV3ht+7e14E" +
                                                    "w9uJeIjJ4YkjjwJHJ6SMdm1JTseUySNaNq7mbOGTR/DzwfMXe5gNbEIc2TUd" +
                                                    "yb6hId04WBZL4caZ1OIiun66OP7Fh+OHpKRP1lKGbSamuaXGJ57NDtBceMqT" +
                                                    "ASr/1wGS+I4S+9zMB07aP0OoXmJDL0UFECrO8Y/q1bDJOngqk+pV5lVvGsSA" +
                                                    "pLBsdQQ8mfBo5XMhRai9ewa1B9kwQFGpnmwUptE9QVFldi/BYLQ25+Yium3l" +
                                                    "wqS/dMFk/3f8dEx3xGXQlkbimpZRY5n1VmzZJKJytcrEoWfxv2g+FBT9DWV3" +
                                                    "E/bCFY4I+iG47XnpKSpkf5lkOkWzMsjAocm3TCLAwgIgYq97rBQmV/HTg11H" +
                                                    "AqL3TqAskLeyIT/zNGVFy2+FKdSKi3thSLk4uWnrvvtPn+YQWAT3ybExfouA" +
                                                    "S5HoEdLI1zjtbqm9ije2PKy8VLY0VTNZ3YNHt8X5D9lO3aL8WBz7bMGn685M" +
                                                    "3uan/N+xUsEkrg8AAA==");
}
