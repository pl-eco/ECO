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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "n+/f5KRJ92f9uBdXMc04GjvSOf85aRRO/uUzoW1dOjSwVgI/p55/alPmzqsC" +
       "P2xCiD1QrR8gIFBC3K+/YP5dGWz9vULacqRX8z9djlCjgv0gB6HgBd8z8HkT" +
       "u4+P3nLOHK0l3+akuyJsk+0vmobNPJaAa5xqiogEu2+1oikzChLMYFZZjokH" +
       "cJlwqjXXKH5Zw/f13C/+KDtUUtti2EeCOzkZ6Xa69qUEl9U6J+P7HkfIOdFY" +
       "upSQLpnq0YZRXuVOxwV6WIuGAkp9ILIGGjfEMdPhooUt/OamX+95bvkD0ZGr" +
       "ktT4KmwsXO7C6gTdzi4FRugNDSS/q+q7mrRAvEbugqfdr5HtX7pG4vKdVesj" +
       "vu/H5aCEmOIwLthWWdBezdKLuMAs1F2Y58w7bEul8NBp0K/BHHJaRotvrPsI" +
       "lx+O2oXlnvablu99VxbU4OOuA76wShXDiIZ1ZN/quKykC5k6giDHn5OQUfVD" +
       "O/pFbISMJyToo5zcEAHlpM3fRYFOQ20CINz+0GngVZmyVRIbNJzk2DEUq2Pi" +
       "nxZBD67If1vktYvL+w8+dP0bz4qGDtFKFxaQSjs0DjnZ1vr4thWpBbRa9418" +
       "vualju1BhY3NvFHZcG/8F7gBmcQiEgAA");
}
