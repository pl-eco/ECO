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
      1159026716000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0YW2wUVfTu9F1LX8hDhAKloAXdEQwmWF+lFltcbUOBxDVa" +
                                                    "7s7e3Q6dnRlm7rZLtT5IDIQPYrQqGuyHAZ8IxkjUGBN+VIjGRGM0fgjqj0Yk" +
                                                    "kQ8f8YXn3Lu789htjT9uMnfu3HvOPe/H3aPnSZXrkDW2ZexOGxaPshyP7jTW" +
                                                    "R/lum7nRzbH1g9RxWbLHoK67FdaGtfbXmn7549GRZoVUx8lcapoWp1y3THcL" +
                                                    "cy1jjCVjpMlb7TVYxuWkObaTjlE1y3VDjeku74qRS3yonHTECiyowIIKLKiC" +
                                                    "BbXbgwKkOczMZnoQg5rc3UUeIJEYqbY1ZI+T5cFDbOrQTP6YQSEBnFCL39tB" +
                                                    "KIGcc8iyouxS5hKBn1ijTj11b/PrFaQpTpp0cwjZ0YAJDkTipCHDMgnmuN3J" +
                                                    "JEvGSYvJWHKIOTo19AnBd5y0unrapDzrsKKScDFrM0fQ9DTXoKFsTlbjllMU" +
                                                    "L6UzI1n4qkoZNA2yzvdklRJuwnUQsF4HxpwU1VgBpXJUN5OcLA1jFGXsuB0A" +
                                                    "ALUmw/iIVSRVaVJYIK3SdgY10+oQd3QzDaBVVhaocLJoxkNR1zbVRmmaDXOy" +
                                                    "MAw3KLcAqk4oAlE4mRcGEyeBlRaFrOSzz/k7bzhwn9lnKoLnJNMM5L8WkNpC" +
                                                    "SFtYijnM1JhEbFgde5LOf3efQggAzwsBS5g3779wy1VtJ09JmMvLwAwkdjKN" +
                                                    "D2uHE42fLO7p3FCBbNTalquj8QOSC/cfzO905WyIvPnFE3EzWtg8ueX9ux56" +
                                                    "mZ1TSH0/qdYsI5sBP2rRrIytG8y5jZnMoZwl+0kdM5M9Yr+f1MA8pptMrg6k" +
                                                    "Ui7j/aTSEEvVlvgGFaXgCFRRDcx1M2UV5jblI2KeswkhNfCQdfBUEfkTb06Y" +
                                                    "us0Fd1epRk3dtFRwXkYdbURlmjWcAO2OZKgz6qpa1uVWRnWzZsqwxlXX0VTL" +
                                                    "SRe/NcthaiKrjTKubrHGN4rZgJNkThTdzf6/COVQ4ubxSASMsTicCgyIoj7L" +
                                                    "ANhhbSq7sffCseEPlWJo5HXFySqgF83TiyK9qKQXDdIjkYggcynSlfYGa41C" +
                                                    "3ENGbOgcumfzjn3tFeBo9nglqBpB20HWPDO9mtXjJYd+kQI18NCFz929N/rb" +
                                                    "CzdLD1VnzuRlscnJg+MPb3/wGoUowZSMwsFSPaIPYiItJsyOcCiWO7dp7/e/" +
                                                    "HH9y0vKCMpDj87miFBNjvT1sBsfSWBKyp3f86mX0xPC7kx0KqYQEAkmTU3By" +
                                                    "yEdtYRqBmO8q5E+UpQoETllOhhq4VUh69XzEsca9FeEfjTi0SldBA4YYFKl3" +
                                                    "09snnz7xzJoNij9LN/nq3hDjMuZbPPtvdRiD9a8ODj7+xPm9dwvjA8SKcgQ6" +
                                                    "cOyBDADWAI09cmrXl2fPHP5M8RyGQynMJgxdy8EZqzwqkB8MyFFo1o5tZsZK" +
                                                    "6imdJgyGfvdn08q1J3480CwNZcBKwc5X/fsB3vplG8lDH977a5s4JqJhffIk" +
                                                    "98CkAuZ6J3c7Dt2NfOQe/nTJ0x/QZyF9Qspy9QkmshARkhGh+qiwSKcYrw7t" +
                                                    "XYPDMrtkLydWFua/xMdyMXbgcIXUG06v9ENGxHweJ4tLItsXz6jlJTPVJVFT" +
                                                    "D++Zmk4OHFkrY7M1mOt7oZV59fO/Pooe/Pp0maRSxy37aoONMSPAGJAM5IQ7" +
                                                    "RMn2ImP/S6+8yT9Zc70kuXrmdBBG/GDPD4u23jSy4z9kgqUh4cNHvnTH0dO3" +
                                                    "rdIeU0hFMQmUdCFBpC6/GoCow6BtMlGhuFIvbN0mGGgBdeBD2uGpztco8cbd" +
                                                    "uTaOl8qQxeHakPMoQp8K6LNzlubX0TNQj8fyDYM62Xp29ND3r0rdhruLEDDb" +
                                                    "N7X/YvTAlOJrwVaUdEF+HNmGCZbnSBEvwi8Cz9/4oGi4IMtwa0++F1hWbAZs" +
                                                    "Gz1y+WxsCRKbvjs++c6Lk3uVfOxs4KQCmkac9omFW2cJtAEcujlpSTMuY2GI" +
                                                    "7cpiggXaK2dWpQh0qZnp51d8/OD0im9AM3FSq7vQjXc76TJNng/np6Nnz306" +
                                                    "Z8kxkfArE9SV/hDujkub30BPKyRosMWrryhepBhas7jCJiTjq7q/DxiJPd/+" +
                                                    "JqxbEi1lvCOEH1ePHlrUc9M5ge8VMMRemivtREBFHu66lzM/K+3V7ymkJk6a" +
                                                    "tfxtaDs1slhL4qAEt3BFghtTYD/YzcvWtasYm4vD7ukjGy6d/iit5IH4bLRz" +
                                                    "ESLiL14+4Soi4XI4UDepkYOyZTAzLZvPPhyG7FxJoOYzsqwdyD7kWstkWIYK" +
                                                    "e7Kx0q1o8f4Fm7kSO+P3jdIJhsqlBr+7p2fZ03EAlVZpyIjkG5xoafm62Zux" +
                                                    "uah0E28teOOGF6bPiMKdI2VqFieNwc4Rj11Yck+Vdyvt2HRT7YLpbV/I0Cjc" +
                                                    "f+rgEpLKGobfTL55te2wlC7EqCsYDV9QeBbM0M2CmeREMGxJeLzbh+E5qcSX" +
                                                    "H2yMk0t8YJzU5Gd+oN2QhwAIpxN2wabNnr2lu+ZIoKTbwQLv750whsV/AIXy" +
                                                    "lJX/Agxrx6c333nfheuOiFoH5qMTE+LOCOlCdoTFErd8xtMKZ1X3df7R+Frd" +
                                                    "ykI2DfSKft5wbvwDYSxpInERAAA=");
}
