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
      1163562858000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
