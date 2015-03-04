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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "AAA=");
}
