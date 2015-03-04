package org.sunflow.system;
public final class Memory {
    public static final String sizeof(int[] array) { return bytesToString(
                                                              array ==
                                                                null
                                                                ? 0
                                                                : 4 *
                                                                array.
                                                                  length);
    }
    public static final String bytesToString(long bytes) { if (bytes < 1024)
                                                               return String.
                                                                 format(
                                                                   "%db",
                                                                   bytes);
                                                           if (bytes < 1024 *
                                                                 1024) return String.
                                                                         format(
                                                                           "%dKb",
                                                                           bytes +
                                                                             512 >>>
                                                                             10);
                                                           return String.
                                                             format(
                                                               "%dMb",
                                                               bytes +
                                                                 512 *
                                                                 1024 >>>
                                                                 20);
    }
    public Memory() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/BEAAA==");
}
