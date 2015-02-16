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
      1170024466000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "LBvujR8Apkz3ThISAAA=");
}
