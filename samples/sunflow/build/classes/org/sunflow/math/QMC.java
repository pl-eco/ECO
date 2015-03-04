package org.sunflow.math;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public final class QMC {
    private static final int NUM = 128;
    private static final int[][] SIGMA = new int[NUM][];
    private static final int[] PRIMES = new int[NUM];
    static { UI.printInfo(Module.QMC, "Initializing Faure scrambling tables ...");
             PRIMES[0] = 2;
             for (int i = 1; i < PRIMES.length; i++) PRIMES[i] = nextPrime(
                                                                   PRIMES[i -
                                                                            1]);
             int[][] table = new int[PRIMES[PRIMES.length - 1] + 1][];
             table[2] = (new int[2]);
             table[2][0] = 0;
             table[2][1] = 1;
             for (int i = 3; i <= PRIMES[PRIMES.length - 1]; i++) { table[i] =
                                                                      (new int[i]);
                                                                    if ((i &
                                                                           1) ==
                                                                          0) {
                                                                        int[] prev =
                                                                          table[i >>
                                                                                  1];
                                                                        for (int j =
                                                                               0;
                                                                             j <
                                                                               prev.
                                                                                 length;
                                                                             j++)
                                                                            table[i][j] =
                                                                              2 *
                                                                                prev[j];
                                                                        for (int j =
                                                                               0;
                                                                             j <
                                                                               prev.
                                                                                 length;
                                                                             j++)
                                                                            table[i][prev.
                                                                                       length +
                                                                                       j] =
                                                                              2 *
                                                                                prev[j] +
                                                                                1;
                                                                    }
                                                                    else {
                                                                        int[] prev =
                                                                          table[i -
                                                                                  1];
                                                                        int med =
                                                                          i -
                                                                          1 >>
                                                                          1;
                                                                        for (int j =
                                                                               0;
                                                                             j <
                                                                               med;
                                                                             j++)
                                                                            table[i][j] =
                                                                              prev[j] +
                                                                                (prev[j] >=
                                                                                   med
                                                                                   ? 1
                                                                                   : 0);
                                                                        table[i][med] =
                                                                          med;
                                                                        for (int j =
                                                                               0;
                                                                             j <
                                                                               med;
                                                                             j++)
                                                                            table[i][med +
                                                                                       j +
                                                                                       1] =
                                                                              prev[j +
                                                                                     med] +
                                                                                (prev[j +
                                                                                        med] >=
                                                                                   med
                                                                                   ? 1
                                                                                   : 0);
                                                                    }
             }
             for (int i = 0; i < PRIMES.length;
                  i++) { int p = PRIMES[i];
                         SIGMA[i] = (new int[p]);
                         System.arraycopy(
                                  table[p],
                                  0,
                                  SIGMA[i],
                                  0,
                                  p); } }
    private static final int nextPrime(int p) {
        p =
          p +
            (p &
               1) +
            1;
        while (true) {
            int div =
              3;
            boolean isPrime =
              true;
            while (isPrime &&
                     div *
                     div <=
                     p) {
                isPrime =
                  p %
                    div !=
                    0;
                div +=
                  2;
            }
            if (isPrime)
                return p;
            p +=
              2;
        }
    }
    private QMC() { super(); }
    public static double riVDC(int bits, int r) {
        bits =
          bits <<
            16 |
            bits >>>
            16;
        bits =
          (bits &
             16711935) <<
            8 |
            (bits &
               -16711936) >>>
            8;
        bits =
          (bits &
             252645135) <<
            4 |
            (bits &
               -252645136) >>>
            4;
        bits =
          (bits &
             858993459) <<
            2 |
            (bits &
               -858993460) >>>
            2;
        bits =
          (bits &
             1431655765) <<
            1 |
            (bits &
               -1431655766) >>>
            1;
        bits ^=
          r;
        return (double)
                 (bits &
                    4294967295L) /
          (double)
            4294967296L;
    }
    public static double riS(int i, int r) {
        for (int v =
               1 <<
               31;
             i !=
               0;
             i >>>=
               1,
               v ^=
                 v >>>
                   1)
            if ((i &
                   1) !=
                  0)
                r ^=
                  v;
        return (double)
                 r /
          (double)
            4294967296L;
    }
    public static double riLP(int i, int r) {
        for (int v =
               1 <<
               31;
             i !=
               0;
             i >>>=
               1,
               v |=
                 v >>>
                   1)
            if ((i &
                   1) !=
                  0)
                r ^=
                  v;
        return (double)
                 r /
          (double)
            4294967296L;
    }
    public static final double halton(int d,
                                      int i) {
        switch (d) {
            case 0:
                {
                    i =
                      i <<
                        16 |
                        i >>>
                        16;
                    i =
                      (i &
                         16711935) <<
                        8 |
                        (i &
                           -16711936) >>>
                        8;
                    i =
                      (i &
                         252645135) <<
                        4 |
                        (i &
                           -252645136) >>>
                        4;
                    i =
                      (i &
                         858993459) <<
                        2 |
                        (i &
                           -858993460) >>>
                        2;
                    i =
                      (i &
                         1431655765) <<
                        1 |
                        (i &
                           -1431655766) >>>
                        1;
                    return (double)
                             (i &
                                4294967295L) /
                      (double)
                        4294967296L;
                }
            case 1:
                {
                    double v =
                      0;
                    double inv =
                      1.0 /
                      3;
                    double p;
                    int n;
                    for (p =
                           inv,
                           n =
                             i;
                         n !=
                           0;
                         p *=
                           inv,
                           n /=
                             3)
                        v +=
                          n %
                            3 *
                            p;
                    return v;
                }
            default:
        }
        int base =
          PRIMES[d];
        int[] perm =
          SIGMA[d];
        double v =
          0;
        double inv =
          1.0 /
          base;
        double p;
        int n;
        for (p =
               inv,
               n =
                 i;
             n !=
               0;
             p *=
               inv,
               n /=
                 base)
            v +=
              perm[n %
                     base] *
                p;
        return v;
    }
    public static final double mod1(double x) {
        return x -
          (int)
            x;
    }
    public static final int[] generateSigmaTable(int n) {
        assert (n &
                  n -
                  1) ==
          0;
        int[] sigma =
          new int[n];
        for (int i =
               0;
             i <
               n;
             i++) {
            int digit =
              n;
            sigma[i] =
              0;
            for (int bits =
                   i;
                 bits !=
                   0;
                 bits >>=
                   1) {
                digit >>=
                  1;
                if ((bits &
                       1) !=
                      0)
                    sigma[i] +=
                      digit;
            }
        }
        return sigma;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "6Phw2YtV7emytwxBvctHGlwW1f8PmxwJT0kYAAA=");
}
