package org.sunflow.system.ui;
import org.sunflow.system.UI;
import org.sunflow.system.UserInterface;
import org.sunflow.system.UI.Module;
import org.sunflow.system.UI.PrintLevel;
public class ConsoleInterface implements UserInterface {
    private int min;
    private int max;
    private float invP;
    private String task;
    private int lastP;
    public ConsoleInterface() { super(); }
    public void print(Module m, PrintLevel level, String s) { System.err.
                                                                println(
                                                                  UI.
                                                                    formatOutput(
                                                                      m,
                                                                      level,
                                                                      s));
    }
    public void taskStart(String s, int min, int max) { task = s;
                                                        this.min =
                                                          min;
                                                        this.max =
                                                          max;
                                                        lastP = -1;
                                                        invP = 100.0F /
                                                                 (max -
                                                                    min);
    }
    public void taskUpdate(int current) { int p = min == max ? 0 : (int)
                                                                     ((current -
                                                                         min) *
                                                                        invP);
                                          if (p != lastP) System.
                                                            err.print(
                                                                  task +
                                                                  " [" +
                                                                  (lastP =
                                                                     p) +
                                                                  "%]\r");
    }
    public void taskStop() { System.err.print(("                                                            " +
                                               "          \r"));
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1166152330000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKUYa2wcxXnu4bfD2U7sOA/i2DFJScJtQQ0qMVLiug5csiGH" +
                                                   "HRtwCGa8O3feeG93\n2Z09n00ERJVwCCptxENUakKEIiWhoUFNq7QSpUFAS4" +
                                                   "kqQaVSCYm0VaS2UkulqhJN1f7oNzO7t3d7\ndyYJJ+3s3Mz3fs03e/YzVOfY" +
                                                   "aLXiJOmcRZzk0Gga2w5Rh3TsOHthaVJ5r64pfWqXYUZRREZRTaUo\nISuOpG" +
                                                   "KKJU2VUt8cKNhok2Xqc1ndpElSoMkD+haP3k55SwXBB45f6Dh0Mt4TRXUySm" +
                                                   "DDMCmmmmkM\n6yTnUNQmH8B5LLlU0yVZc+iAjJYQw80NmYZDsUGdx9ATKCaj" +
                                                   "ekthNCnqlX3mEjCXLGzjnMTZS2nO\nFigstQnFmkHUwSI7wNxcjglie3gjld" +
                                                   "BApJFtjoM6XALQem1Ra6FthapW7PT4nQdPnImhxARKaMYo\nI6aAJhT4TaDW" +
                                                   "HMlNEdsZVFWiTqB2gxB1lNga1rV5znUCdTha1sDUtYkzQhxTzzPADse1iM15" +
                                                   "+osy\nalWYTrarUNMu2iijEV31/9VldJwFtbsCtYW6O9g6KNisgWB2BivER4" +
                                                   "nPaAZ4vCeMUdSxfxcAAGpD\njtBps8gqbmBYQB3Clzo2stIotTUjC6B1pgtc" +
                                                   "KFpZkyiztYWVGZwlkxR1h+HSYgugmrghGApFnWEw\nTgm8tDLkpRL/bOr6/P" +
                                                   "Dp77+1ncd2XCWKzuRvBqQ1IaQRkiE2MRQiEK+6yRdSD7mrowgBcGcIWMAM\n" +
                                                   "3nJhTP7rL3oEzKoqMHumDhCFTir3He0ZefweE8WYGI2W6WjM+WWa83RIezsD" +
                                                   "BQuytqtIkW0m/c2L\nI7986KnXyN+iqDmF6hVTd3MQR+2KmbM0ndj3EIPYmB" +
                                                   "I1hZqIoQ7x/RRqgLkMIS9W92QyDqEpFNf5\nUr3J/4OJMkCCmagJ5pqRMf25" +
                                                   "hek0nxcshFADPOhOeFqQ+PE3RXclJcc1Mro5Kzm2Ipl2Nvg/51CS\nk1xNYn" +
                                                   "lu6iTlh2KShZBF0Zg0beaIhBVsaIYpZTVIWsW8TSV59r5RwgUmdcdsJMLKYD" +
                                                   "iddYC619RV\nYk8qp658cHB41zOHRaiw8Pb0pWg98Et6/JKCX9LVkmF+KBLh" +
                                                   "bJYxvsJnYPEZyF2ocq23ju7f+ejh\nvhgEizUbB3Mx0D7QzBNmWDGHggRP8V" +
                                                   "qoQJR1v7pvIXn11DYRZVLtOlwVu+XD1y+d+FfrxiiKVi+S\nTEko082MTJpV" +
                                                   "1mLx6w+nVTX6/ziy+/zHlz79SpBgFPVX5H0lJsvbvrA7bFMhKlTCgPzJFYnY" +
                                                   "A2j8\naBTFoRhAAeTyQ21ZE+ZRlr8Dfi1kujTIqCVj2jmssy2/gDXTaducDV" +
                                                   "Z4nLTx+VI/oFfB0+FFOH+z\n3U6LjV0irpi3Q1rwWnv1W/Vf/f2bLe9xs/hl" +
                                                   "OVFy8I0SKpK8PQiWvTYhsP7py+nnX/xsYR+PFC9U\nKJyG7pSuKQVAWR+gQH" +
                                                   "brUGGYI/vHjJypahkNT+mERdz/Erfc/pO/P9cmXKPDiu/ZzV9MIFhf8Q30\n" +
                                                   "1KVH/r2Gk4ko7HQJ1AjAhDZLA8qDto3nmByFQ7+9+Xu/wseg+EHBcbR5wmsI" +
                                                   "4pohbkeJ230jH5Oh\nvdvZ0Ae0N9cI/Spn+aRy8LVsn/vYr3/GpW7BpU1BqR" +
                                                   "t2Y2tAeJ4N65h1l4ez917sTAPc1y7e93Cb\nfvG/QHECKCpwhjp7bCgdhTIn" +
                                                   "etB1DZ+8/U7Xox/FUHQHatZNrO7APP5REwQecaah6hSsbdt5bLXN\nNrKRq4" +
                                                   "y4EVZ6BiiU/GON3K21038H6wSCzJmc2nxa/mDPMW6Amolf5SAM0Zl/a+z41d" +
                                                   "/Qy5xOkIEM\nu7dQWVKhewpwv/5xvr3+jVdyUdQwgdoUr78bx7rL4nwC2hHH" +
                                                   "b/qgByzbL28txDk6UKwwq8PZX8I2\nnPtBKYc5g2bz1lC6tzJrL/Mn/rs03S" +
                                                   "OIT7ZxlH4+bigmZ4Nla3nMej4Uy2kGOKq7tOO2tRyc3Hle\nmq483ffz98de" +
                                                   "WRDlfBF/lmFNKk/+4Y8zse+8PSXwwk4LAR9dc/LP56+MLBOpL1q8dRVdVimO" +
                                                   "aPO4\nVRIWS4PexThw6Hc39Z59YuSyJ1FHebMyDA39X+beIRvu/vafqpyoMW" +
                                                   "hERQFl4x1s2C5ifUvNnNha\n6a0lnreW1PBWmg2DzCmiD0iFeN5/nTyXw3OT" +
                                                   "x/OmGjzHPJ7QPuXTHH3YEmx2UhaZJg5rPn6dUnTD\nk/CkSNSQYr8vBcXODE" +
                                                   "dfDjcvXqZYRiVFsx4S5JHrFKTL3/bfVQRRPUHqoCmn6WpOIItw5V5bX1IE\n" +
                                                   "I74ua6v0ZGMOsYvdGAvim2vdDHgALzz4z9an8bv7o95Js4tCcTat23SSJ3oJ" +
                                                   "zzijVNao7eZ3oaDQ\nHTnzgwv0o01bRSpsrJ3UYcSNW0/M92w99+wNtGc9Id" +
                                                   "3CpNvzq+6PTWvvR/l1TdTNimteOdJAebVs\nBnlc29hbVjPXlrdIA/B0eiHQ" +
                                                   "WbVFCjwYnO8xbteY78vV1XyZ6t9tqq5OFnV4qh/qEpweRY9Vi3Iu\nx+wiLc" +
                                                   "aTbKAQoxYjFs7ZeN7U1CBc3S9KEv/s5n+scnNtgWeFZ64VN2quqmnMsyrFaR" +
                                                   "xZRNXn2LDA\nAh3qwijFNi9GhwL1Dn8Z9TbA0+up13vN6kVEZgcKvLSIAi+z" +
                                                   "4XkKGQEKjFkqnLohDV74Mhqs9LTw\ntbk2DUoFPLHI3qtsOEZRo7C+aYVEP3" +
                                                   "6tokOj0Ra+frL+u7vig5X4yKLInzz+8Ofy7/7DL1LFDyEt\nMmrMuLpe2iKV" +
                                                   "zOstm2Q0LnmLaJgs/jpDUWfVKzFFUVfjgp4WkGehj6uEhNuMmJSCnqOopQQU" +
                                                   "mipv\nVgr0IzjLAYhNz1tV8kE0ioUyWzG7rCsryfwLol82XfENcVJ58PV9aw" +
                                                   "vP7v0ur8V1io7n5xmZZhk1\niOtjsfT21qTm0/oQvXFu/M0f3uUfLfx6sawQ" +
                                                   "nHxlMXiH2F3E91Duq9/ZhnMW5bes+Z8u//Hdp45f\njvJb4/8BQ/tk+fgVAA" +
                                                   "A=");
}
