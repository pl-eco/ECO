package org.sunflow.system;
import org.sunflow.system.UI.Module;
public class BenchmarkFramework {
    private Timer[] timers;
    private int timeLimit;
    public BenchmarkFramework(int iterations, int timeLimit) { super();
                                                               this.timeLimit =
                                                                 timeLimit;
                                                               timers = (new Timer[iterations]);
    }
    public void execute(BenchmarkTest test) { for (int i = 0; i <
                                                                timers.
                                                                  length;
                                                   i++) timers[i] =
                                                          null;
                                              long startTime = System.
                                                nanoTime();
                                              for (int i = 0; i <
                                                                timers.
                                                                  length &&
                                                                (System.
                                                                   nanoTime() -
                                                                   startTime) /
                                                                1000000000 <
                                                                timeLimit;
                                                   i++) { UI.printInfo(
                                                               Module.
                                                                 BENCH,
                                                               "Running iteration %d",
                                                               i +
                                                                 1);
                                                          timers[i] =
                                                            new Timer(
                                                              );
                                                          test.
                                                            kernelBegin();
                                                          timers[i].
                                                            start();
                                                          test.
                                                            kernelMain();
                                                          timers[i].
                                                            end();
                                                          test.
                                                            kernelEnd();
                                              }
                                              double avg =
                                                0;
                                              double min =
                                                Double.
                                                  POSITIVE_INFINITY;
                                              double max =
                                                Double.
                                                  NEGATIVE_INFINITY;
                                              int n = 0;
                                              for (Timer t
                                                    :
                                                    timers) {
                                                  if (t ==
                                                        null)
                                                      break;
                                                  double s =
                                                    t.
                                                    seconds();
                                                  min =
                                                    Math.
                                                      min(
                                                        min,
                                                        s);
                                                  max =
                                                    Math.
                                                      max(
                                                        max,
                                                        s);
                                                  avg +=
                                                    s;
                                                  n++;
                                              }
                                              if (n == 0)
                                                  return;
                                              avg /= n;
                                              double stdDev =
                                                0;
                                              for (Timer t
                                                    :
                                                    timers) {
                                                  if (t ==
                                                        null)
                                                      break;
                                                  double s =
                                                    t.
                                                    seconds();
                                                  stdDev +=
                                                    (s -
                                                       avg) *
                                                      (s -
                                                         avg);
                                              }
                                              stdDev = Math.
                                                         sqrt(
                                                           stdDev /
                                                             n);
                                              UI.printInfo(
                                                   Module.
                                                     BENCH,
                                                   "Benchmark results:");
                                              UI.printInfo(
                                                   Module.
                                                     BENCH,
                                                   "  * Iterations: %d",
                                                   n);
                                              UI.printInfo(
                                                   Module.
                                                     BENCH,
                                                   "  * Average:    %s",
                                                   Timer.
                                                     toString(
                                                       avg));
                                              UI.printInfo(
                                                   Module.
                                                     BENCH,
                                                   "  * Fastest:    %s",
                                                   Timer.
                                                     toString(
                                                       min));
                                              UI.printInfo(
                                                   Module.
                                                     BENCH,
                                                   "  * Longest:    %s",
                                                   Timer.
                                                     toString(
                                                       max));
                                              UI.printInfo(
                                                   Module.
                                                     BENCH,
                                                   "  * Deviation:  %s",
                                                   Timer.
                                                     toString(
                                                       stdDev));
                                              for (int i =
                                                     0; i <
                                                          timers.
                                                            length &&
                                                          timers[i] !=
                                                          null;
                                                   i++) UI.
                                                          printDetailed(
                                                            Module.
                                                              BENCH,
                                                            "  * Iteration %d: %s",
                                                            i +
                                                              1,
                                                            timers[i]);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169093936000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVYe2wcxRkf3/kRnx38yJO8nDgOaV53Qc0LGxWMYxMnl8TY" +
       "jgGH1Ix3584b7+0u\nu3P22bgpCCkORFCiQl9qQlpFSuJCiUSrtBJNEwEtJa" +
       "oESAUJibRVpLZSS1VUiaZq/+j3zeze7u2d\njYRUS56b3fke871+882+/Amp" +
       "cGyyQnHifMJiTryjr4faDlM7dOo4/fBqSHmrorrn/D7DjJCyJIlo\nKid1Sc" +
       "VJqJTThKYmune35WyyyTL1ibRu8jjL8fhRfbsrb29ye5HAB89cbnzyXHlThF" +
       "QkSR01DJNT\nrplGp84yDif1yaN0jCayXNMTSc3hbUkynxnZTIdpOJwa3HmM" +
       "HCPRJKm0FJTJyZqkpzwByhMWtWkm\nIdQneoRakLDAZpxqBlPb8+qAc3MhJ2" +
       "zb5estpgYh83BxAMwROwCrV+etltYWmWpFLwzsmDp7MUrq\nBkmdZvShMAUs" +
       "4aBvkNRmWGaY2U67qjJ1kDQYjKl9zNaork0KrYOk0dHSBuVZmzm9zDH1MSRs" +
       "dLIW\ns4VO72WS1Cpok51VuGnnfZTSmK56TxUpnabB7MW+2dLcLnwPBsY02J" +
       "idogrzWMpHNQMi3hTmyNvY\nsg8IgLUqw/iImVdVblB4QRplLHVqpBN93NaM" +
       "NJBWmFnQwsmyWYWiry2qjNI0G+JkaZiuRy4BVbVw\nBLJwsihMJiRBlJaFoh" +
       "SIz6bFn5248P0r94rcLleZouP+Y8C0KsTUy1LMZobCJOOtbPyF7oezKyKE\n" +
       "APGiELGkaV93+VDyL79skjTLS9AcHD7KFD6kHDjV1Pv4/SaJ4jbmWaajYfAL" +
       "LBfl0OOutOUsqNrF\neYm4GPcWr/b+6uEnZthfIyTWTSoVU89mII8aFDNjaT" +
       "qz72cGsylnajepZobaIda7SRXMk5Dy8u3B\nVMphvJuU6+JVpSmewUUpEIEu" +
       "qoa5ZqRMb25RPiLmOYsQUgX/pBb+q4n8E7+c7IonnKyR0s3xhGMr\nCdNO+8" +
       "8TDmeZxH3g4pEMtUe7oIjZuGmPxjGDLE76EyNmhiWoQg3NMBNpDWpWMbeobA" +
       "x/v6DcHO65\ncbysDEEwXMw61MEeU1eZPaScv/nOVOe+p0/IRMHkdq3lZB2o" +
       "i7vq4lJdvFgdKSsTWhaiWhkwcPco\nFC5AXO2GviN7Hz3RHIVMscbLwVdI2g" +
       "x2uXvpVMwOv7q7BRAqkGJLf3h4On7r/D0yxRKzg3BJ7pp3\nX7l+9p+1GyMk" +
       "Uhoh0UbA6BiK6UFYzSNfS7imSsn/+zP7X/vg+sdf8quLk5aioi/mxKJtDkfD" +
       "NhWm\nAgz64s/dXhd9kAycipByQAJAP7F/AJZVYR0FxdvmASHaUpUkNSnTzl" +
       "Adlzz0ivER2xz334g0qRfz\nBRCcGszmlfA/301v8YuriywcF8u0wmiHrBBA" +
       "e+upyq0fvl7zlnCLh8l1gVOvj3FZ4Q1+svTbjMH7\nj7/T880XP5k+LDLFTR" +
       "UOR2F2WNeUHLDc4bNAaesALxjIlkNGxlS1lEaHdYYZ99+6dXf+9G/P1cvQ\n" +
       "6PDGi+zmzxfgv7/9PvLE9a/+a5UQU6bg0eKb4ZNJaxb4ktttm07gPnJPvr/y" +
       "u7+mpwH5AG0cbZIJ\nAIkIyyLAtDTYmthaBiBuTITx5vHmX7x96KVpmfob5u" +
       "g/glxDytd//4fR6DeuDUu+MMyHiE+tOven\n1272LpRukmfh2qLjKMgjz0OR" +
       "AHUWBmTNXBoE9Zub1rx8rPeGu6PGQlTvhM7nzxNvsPV3P/vHEuAT\nhRMbH7" +
       "YJjQmRohvFGMecFG4kYq0Vh2bY0OZZXFWi5xlSpmbSzdnHfvNzobqGBpunYM" +
       "bup5a0uR6H\ntWj3kjDQ7aHOCNBtu3rgkXr96n9A4iBIVKDXcA7aALK5gnx3" +
       "qSuqPrr2xuJH34uSSBeJ6SZVu6iA\nClINNcqcEcDnnHXPvaIM68fn4ShMJs" +
       "IJy1wH5AJPmFVzpEsXdkw+yAwNb76QfOfgaeGAWTGyRCaF\n5ExeOXTm1m/5" +
       "DSHHByvkXpMrPnygy/R5d30w1lB56aVMhFQNknrF7YMHqJ5FSBiEts3xmmPo" +
       "lQvW\nC1sw2W+05cF4RTiVA2rDMOnnHcyRGue1IWTEM58sh/+Yi4yxMDKWET" +
       "HZI1haxLg+j2NVlq2NUeyN\nSSXXMtAZI4YGEEDgBhbOxed3L+i96/BT4uiq" +
       "hn6ZOgf8/cEtBWdl4Nh1s0c6L2xIWX/k8j+uXWHr\nRVLO0xzwQ7udLtE7Bn" +
       "g+pTNs/4epM+L4KR+mjvRIuOku7qkLWmXhwNsKHdgwlwMF6RLoDUu0Hf3C\n" +
       "Z171l7mdBD534NDreX+g2PsRDuHWDCoa4A0YAJ0ZadnUbcPhASuXl+zBs7sV" +
       "CeyYYdBbmwbDM8Jb\nk/2OZsbz9xpYzBXt0SYrC7qd/cJFfgk8c/FHl/l7m1" +
       "olRm6cPahhxo2tZyebWl89+QV6nKZQ7MOi\nG8aWPxAd0d6OiAuPrKiii1Ih" +
       "U1thHcVgP1nb6C+optUyVMLnONwxB6in51jTcAAAqFAwJjKE4Oem\n0ud7Z8" +
       "bi4kSe/NmSn9x9/swN9LSVg6a9RlTXnVt37Ny1DfgboRzx7h/XVBfjdr/fNT" +
       "yTMmZU4YiY\nyP525HGtrBZvAuUZNS0HLzeBrwiupJaDloPd3/yAku7dU5f2" +
       "1s77wcnjQr5b29WBi5L7XDVG7QPB\nw9Hd+c6tu768g5Mj/48LROuO7Zt3bt" +
       "uyayto69/T3Rfn+RLM5EHsGNzVir2GtrpFThpFWt/m1xEe\n+8FFsKa8t7N9" +
       "t2wscWzDoVtW0ldmPQA7CpFlmde9er8loHkKh70cDljYfBKblTwGBDR/bQ7N" +
       "OT9t\nvQMYC3y2a7hogqYf+rT2OH3zSMTN3h24AdPaorMxpgdESTijhb34Ju" +
       "klz1slevFShRTER0Cq1XPd\n4/oZ9KpI+PQcFfcsDsfhGGM5pmS5xMCtLm5u" +
       "hwiOmZrqu3D684KX717gFC/OPyzmpUUfoORHEyX5\n0eOPfJb83b/l4eR92K" +
       "iBoklldT14lAfmlZbNUpowpUYe7Jb4+TboL3YOpLeciL1+S5J+D+ogQAq+\n" +
       "cGdBotPQtwIRTs9Ynvvr/eSXbUquwAdo7doC2Bff+TxozsovfUPKQ68cXp07" +
       "2f+8wHuAPjo5iWJi\nAA/ynpeH9zWzSvNkvUsuvTrw+o/v8nJSNLcLc34l5L" +
       "M7H9OFc8QUF3Trf+wHEONzFQAA");
}
