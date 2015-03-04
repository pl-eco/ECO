package org.sunflow.math;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
final public class QMC {
    final private static int NUM = 128;
    final private static int[][] SIGMA = new int[NUM][];
    final private static int[] PRIMES = new int[NUM];
    static { UI.printInfo(Module.QMC, "Initializing Faure scrambling tables ...");
             PRIMES[0] = 2;
             for (int i = 1; i < PRIMES.length; i++) PRIMES[i] = QMC.nextPrime(
                                                                       PRIMES[i -
                                                                                1]);
             int[][] table = new int[PRIMES[PRIMES.length - 1] + 1][];
             table[2] = (new int[2]);
             table[2][0] = 0;
             table[2][1] = 1;
             for (int i = 3; i <= PRIMES[PRIMES.length - 1]; i++) {
                 table[i] =
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
             for (int i = 0; i < PRIMES.length; i++) { int p = PRIMES[i];
                                                       SIGMA[i] =
                                                         (new int[p]);
                                                       System.arraycopy(
                                                                table[p],
                                                                0,
                                                                SIGMA[i],
                                                                0,
                                                                p);
             } }
    final private static int nextPrime(int p) { p = p + (p & 1) +
                                                      1;
                                                while (true) { int div =
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
                                                } }
    private QMC() { super(); }
    public static double riVDC(int bits, int r) { bits = bits << 16 |
                                                           bits >>>
                                                           16;
                                                  bits = (bits & 16711935) <<
                                                           8 |
                                                           (bits &
                                                              -16711936) >>>
                                                           8;
                                                  bits = (bits & 252645135) <<
                                                           4 |
                                                           (bits &
                                                              -252645136) >>>
                                                           4;
                                                  bits = (bits & 858993459) <<
                                                           2 |
                                                           (bits &
                                                              -858993460) >>>
                                                           2;
                                                  bits = (bits & 1431655765) <<
                                                           1 |
                                                           (bits &
                                                              -1431655766) >>>
                                                           1;
                                                  bits ^= r;
                                                  return (double)
                                                           (bits &
                                                              4294967295L) /
                                                    (double)
                                                      4294967296L;
    }
    public static double riS(int i, int r) { for (int v = 1 << 31;
                                                  i !=
                                                    0;
                                                  i >>>=
                                                    1,
                                                    v ^=
                                                      v >>>
                                                        1) if ((i &
                                                                  1) !=
                                                                 0)
                                                               r ^=
                                                                 v;
                                             return (double) r / (double)
                                                                   4294967296L;
    }
    public static double riLP(int i, int r) { for (int v = 1 << 31;
                                                   i !=
                                                     0;
                                                   i >>>=
                                                     1,
                                                     v |=
                                                       v >>>
                                                         1) if ((i &
                                                                   1) !=
                                                                  0)
                                                                r ^=
                                                                  v;
                                              return (double) r /
                                                (double)
                                                  4294967296L; }
    final public static double halton(int d, int i) { switch (d) {
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
    final public static double mod1(double x) {
        return x -
          (int)
            x;
    }
    final public static int[] generateSigmaTable(int n) {
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1163562858000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVZC2wUxxke3/ltExvzCEkA80pdjLmLTTAPpwLXNuTInTE2" +
       "EDAhznpv7rywt7vZ\nnbPPDqJBiYCGNg1tIrVSQ0iEyqPkoSYNrUQoiKTNQ1" +
       "GTqEnUVKGkUZtKDVWjSISqldr/n9m73du7\nMyQQSzs3O49/5vv///vnn/Xx" +
       "C6TEMsl02QqwUYNagY6+Hsm0aLRDlSxrPTQNyK+UVPQcvkPTfaQo\nTHxKlJ" +
       "GasGwFoxKTgko0GOpsS5lkgaGro3FVZwGaYoFt6mJb3prw4hyBdx44UbfrUH" +
       "G9j5SESY2k\naTqTmKJrXSpNWIzUhrdJw1IwyRQ1GFYs1hYmE6iWTHTomsUk" +
       "jVn3kp3EHyalhowyGZkdTi8ehMWD\nhmRKiSBfPtjDlwUJk0zKJEWj0fbMcj" +
       "CzKXsmbNue15s7GoSUY+dGgMN3AKhnZVALtDlQDf+Rja07\nDh71k5p+UqNo" +
       "fShMBiQM1usn1QmaGKSm1R6N0mg/mahRGu2jpiKpyhhftZ/UWUpck1jSpFYv" +
       "tXR1\nGAfWWUmDmnzNdGOYVMuIyUzKTDczOoopVI2m30piqhQH2FMd2ALuKm" +
       "wHgJUKbMyMSTJNTynermhg\n8XrvjAzGeXfAAJhalqBsSM8sVaxJ0EDqhC1V" +
       "SYsH+5ipaHEYWqInYRVGbiwoFHVtSPJ2KU4HGJnm\nHdcjumBUBVcETmFkin" +
       "cYlwRWutFjJZd9Fky9uPfIT0+t5L5dHKWyivuvhEkzPZN6aYyaVJOpmHgp\n" +
       "GXg0tDk53UcIDJ7iGSzGtN98YkP477+pF2NuyjNm7eA2KrMBuXt/fe99q3Xi" +
       "x22UG7qloPGzkHM6\n9Ng9bSkDWDs1IxE7A+nO072/3Xz/MfoPH6kMkVJZV5" +
       "MJ8KOJsp4wFJWaq6lGTYnRaIhUUC3awftD\npAzqYXB50bo2FrMoC5FilTeV" +
       "6vwdVBQDEaiiCqgrWkxP1w2JDfF6yiCETICH1MFTSsQf/2WkIRC0\nklpM1U" +
       "eClikHdTOeeU+AgOC6SEcAHcZgpCM4pCdoUJIlTdH0YFwBisr6wigdxt8rE5" +
       "PCHdWNFBVh\niPNSVQUvv11Xo9QckA9//PqOrju+u1e4AbqujYWRySA9YEsP" +
       "oPQASCdFRVzoZFxFaB90tx1YCPGq\nen7f1jX37J3jB7MbI8UAHIfOgV3bS3" +
       "fJeodD1RCPajL4y7SntuwJXDq8QvhLsHBEzTu76q2n3zj4\neXWjj/jyhzuE" +
       "BAG3EsX0YIzMhLF5XoLkk//PhyLPv/fGh990qMLIvBwG585EBs7xKt/UZRqF" +
       "mOaI\nP3RDjf9OsnG/jxQDrSGU8f1DlJjpXSOLiW3pqIZYysKkKqabCUnFrn" +
       "QoqmRDpj7itHCvqOX1SWCc\nanTNGni+Zfsq/8XeKQaWU4UXobU9KHjUvPRA" +
       "6S3vn6x6haslHWBrXEdYH2WCrhMdZ1lvUgrtH/64\n50ePXdizhXuK7SqMlB" +
       "mmMgwETcGcbzhzgKgqBAu05LwNWkKPKjFFGlQputx/a25u/uWnD9cK26jQ\n" +
       "kjZt0+UFOO03fJvc/8bdX8zkYopkPCgcHM4wAWeSI7ndNKVR3Edq1zszfvI7" +
       "6XGIYxA7LGWM8nBA\nODTCFRnkim/kZcDT14zFHJDdVMD38xzLA/KOY/E5yX" +
       "tf+zXfdZXkPt/ddohIRpswPRZzUbvXe+l7\nu2QNwbhbT3ffVaue/g9I7AeJ" +
       "MhyH1loTIkUqy4r26JKyD86cnXrP237iW0UqVV2KrpI4AUgFeB61\nhiDIpI" +
       "wVK7lz1Y6UY8khE66EG20FpFxvftjc/ML8X4WHukOdgcGmI+HX1z7OFVCQ+X" +
       "nONI+csVMb\nDlx6k53jchwK4uzZqdwIComQM3fpe8MTS597IuEjZf2kVrZT" +
       "tY2SmkRH74fMwkrnb5DOZfVnZwni\nSGzLhJjpXvq7lvWS34ncUMfRWK/28P" +
       "0G1HYDPGU238u8fOehXdgZtxQIQVIUp2bdXw4e+mLXnqU+\ndO6SYdw6aKXW" +
       "GdedxGRu9/HHZlQ9en4fRnF0/vtR6Aq+/DxeNgim+xlsXtEknnHMZ6TU4rlh" +
       "ihF/\n94YI7GCaOyk3lQQc7sM85n28e85Lr254Yo84J8bxk6xZA/J3/nx+u/" +
       "8HZwbFPK8zeAbvn3nob89/\n3DtZhBSRBc7NScTcc0QmyLVdYyC9Zo+3Ah/9" +
       "8oLZx3f2nrN3VJedz3RBzv/J6FnacNv3P8pzMPsh\nVxWRGcsWLFYKDi0uyL" +
       "Xl2V6wFJ5y2wvKc7yA8Mr6PJbD+ipuNixWg8lK+kKrI+14RriMxsMiYj36\n" +
       "SOek3mVbHuBHcwUk95LV7XgqXKmwVgQauLmwLTPCBuSGrSf+deYUbeDhqVyx" +
       "gBHtZjxPouua85l0\njEbejx3gx2vxoGQJbnhvCLkXgKy8nhv3OkOoebPBXy" +
       "OGYUBqV8lhtbS0LlkEaqgDNeAFMaBE7SjT\n+c6qwWMx7VjUx9fhq7bjFFsL" +
       "FbzFpRa/bliYAbuumrakeWsNC7OKCa5FQp07nltTXf7kvt1cvq3T\nClc2bb" +
       "+XDUtmt+NH+HM3I5FrkG0ub25ubmppXtiyjJFAvqwxwJ2kwZrFJDNO2SzxHt" +
       "iS2greDCXu\nhe6EvD5XeQjZdlTMqwH8dU7cQaK4O4Ecxb1d7Z1ew9zKdQ0X" +
       "qire1Ny6aNnSZdcYeysjjVeOHfez\nLc0efBnyMHrDl2T0YngqbFVUFGC0eU" +
       "WMLu3pDUW6+rI8PZO0FNlpPd+BkRY8mivYxwWDNCM5qPLg\nXqpSLS7uShEs" +
       "Rmw2oVifmMPfr2d2hsVN3KHqGsVkLd0nbh6KHsh8LoDOVM4GTTIj694R4WR2" +
       "ju2H\njv78BHt7wXIRgBsLhx/vxMblB8fqlz+77yvcNuo9UcoreuLwTev8Q8" +
       "qrnMh2FpDz/SF7Ulv22V8J\n+0ma2vqsDGCWMOJIrpGKsL4jNU5i+r1x+h7G" +
       "Yi84sYw2EiYFvdfnT7y7EgbjqfLYr65/4bbDB87x\nNCGVxdPFeXi65Jrx9J" +
       "am5mULWxYz0pSXp8LtXUQVDbijH2ZYkY+q1jhUTRVihkvz6TwYfbbQBxue\n" +
       "NOzZ9Fn1bunlrT7bAGsZJNq6sVClw1R1iSrj9QezA8RCeAJ2gAjkv+hdPjp4" +
       "vKHI8aIIl/Gzcdzl\nCBZPwpY14BhmRNQJLVyPT10u5GUUlQ0PP7eQIDwrbX" +
       "grrxAet8SOvMh8zoCIA+8X48B7AYungQ2m\nsrGzgw8J2RGuG9wnqifTQYyD" +
       "feZqwDbCs8YGu+brAntmHLBnsTgJh7epcEq86AB76WqALYBnnQ1s\n3dcF7M" +
       "1xgP0ei1chjTCVcI8H2WtfEdk0bFwEzyYb2aYvTb8vA++P48D7ExZ/AHcckl" +
       "QmMkMXwHev\nBiDGl1Eb4Og1AuiKLy9yGX8dB9snWJwH0yX0aLMH2UdXg2wJ" +
       "PDttZDuvPTJhtc/GQfY5Fp8yUhe3\nv2D3KfGEtD6TFRW4jyxt9txHwrosqa" +
       "HOJ89UvbM/2bomfYxIWFxkJPbVjllZN2nQpFqUmtSEi7Ee\nN6llwWW3124T" +
       "5++iJU3NrQtvbW1kpCHv+VsAHLffhSu1H35EAGGYh0zL+ReV+LeKHP7gvrsu" +
       "ht/9\nt7gRpv/1UQU3plhSVd1fUlz1UsOkMYXrq0p8V+FKLwKF13rhoA/CD9" +
       "/Y/8QwP+Q0rmEMzmhRcw8q\nhc3DIKyWGel81/WZRXwhyk4aEOncrOyV/xcw" +
       "nWEmxf8BB+RNT2+Zldq3/hGetkLGJo2NoZhKuBeK\nD8eZLHV2QWlpWW+R55" +
       "7dePKZZWkH4t8VJ7uokMWmFtFb2IDYsd/4P+6zRqWRHQAA");
}
