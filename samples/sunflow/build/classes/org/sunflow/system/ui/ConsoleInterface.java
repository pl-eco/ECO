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
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYb2wcxRWfO/93HJ/jxIljgmObS9ok9LapGqTUCJqcbHLh" +
                                                    "Qk5xnKqHihnvztkb7+0uu3P22eBColZOkRpBayAg6g9VEIUGghARrSqkfGkB" +
                                                    "0S9UVat+KFT9UlSaD/lQikpb+t7M7u3e3p2bfKilnZudfW/ee/Pe+703vnSN" +
                                                    "tLgO2WdbxuKMYfEUK/PUaeNAii/azE0dzR7IUcdlWtqgrnsS1qbUkdcSn3z2" +
                                                    "xGxPnLTmyWZqmhanXLdM9wRzLWOeaVmSCFbHDFZ0OenJnqbzVClx3VCyustH" +
                                                    "s2RDiJWTZNZXQQEVFFBBESoohwIqYNrIzFIxjRzU5O5D5NskliWttorqcTJc" +
                                                    "vYlNHVr0tskJC2CHdnw/BUYJ5rJDhiq2S5trDH5qn7L6zAM9rzeRRJ4kdHMC" +
                                                    "1VFBCQ5C8qSryIrTzHEPaRrT8mSTyZg2wRydGvqS0DtPel19xqS85LDKIeFi" +
                                                    "yWaOkBmcXJeKtjkllVtOxbyCzgzNf2spGHQGbN0a2CotHMd1MLBTB8WcAlWZ" +
                                                    "z9I8p5saJzujHBUbk/cCAbC2FRmftSqimk0KC6RX+s6g5owywR3dnAHSFqsE" +
                                                    "UjgZaLgpnrVN1Tk6w6Y46Y/S5eQnoOoQB4EsnPRFycRO4KWBiJdC/rl2353n" +
                                                    "HzaPmHGhs8ZUA/VvB6bBCNMJVmAOM1UmGbv2Zp+mW986FycEiPsixJLmzUeu" +
                                                    "f/32wavvSJpb6tAcnz7NVD6lXpzufn9Hes/BJlSj3bZcHZ1fZbkI/5z3ZbRs" +
                                                    "Q+ZtreyIH1P+x6snfvXNx15mH8dJZ4a0qpZRKkIcbVKtoq0bzLmHmcyhnGkZ" +
                                                    "0sFMLS2+Z0gbzLO6yeTq8ULBZTxDmg2x1GqJdziiAmyBR9QGc90sWP7cpnxW" +
                                                    "zMs2IaQNHnIHPBuI/BO/nOSVWavIFKpSUzctBWKXUUedVZhqKS4t2gZ4zS2Z" +
                                                    "BcNaUFxHVSxnJnhfdDkrKiVdwRy2DJbxYzWFMWb/X3cvo209C7EYHPuOaNIb" +
                                                    "QHXEMjTmTKmrpcNj11+dei9eSQLvVDjZDfJSnryUlJcq6amoPBKLCTFbUK70" +
                                                    "LPhlDjIcsK9rz8S3jj54bqQJQspeaIZDRdIRsNBTZky10gEMZATYqRCL/T++" +
                                                    "fyX16Yt3y1hUGmN2XW5y9cLCmVOPfjlO4tXgi8bBUiey5xAyK9CYjCZdvX0T" +
                                                    "Kx99cvnpZStIvyo091ChlhOzeiTqBsdSmQY4GWy/d4hemXprORknzQAVAI+c" +
                                                    "QjgD8gxGZVRl96iPlGhLCxhcsJwiNfCTD2+dfNaxFoIVER/dYr7JD/db4On1" +
                                                    "4l/84tfNNo5bZDyhlyNWCCQe//nVZ688t+9gPAzaiVAZnGBcQsCmIEhOOozB" +
                                                    "+h8v5H741LWV+0WEAMVt9QQkcUwDIIDL4Fi/+85Df/jwg4u/jQdRxaEylqYN" +
                                                    "XS3DHrsDKQAXBkAW+j45aRYtTS/odNpgGJz/Suzaf+Vv53ukNw1Y8YPh9v+9" +
                                                    "QbC+/TB57L0H/jEotompWK4CywMyeQCbg50POQ5dRD3KZ35z67Nv0x8BmgKC" +
                                                    "ufoSE6BEhGVEHL0iXLVXjKnIt/04DNk138pipV+8YeOzp3ESjWPVDSXfP48b" +
                                                    "02f//KmwqCZ96hSbCH9eufT8QPqujwV/EMfIvbNcC0jQoQS8X3m5+Pf4SOsv" +
                                                    "46QtT3pUr/05RY0SRkseSr7r90TQIlV9ry7fslaNVvJ0RzSHQmKjGRQAIcyR" +
                                                    "GuedkaTpwlPe4k/833DSxIiYHBQsI2LchcMX/Zhtsx19nmJvRZqKurm+k3KO" +
                                                    "XoSCOe9VdGW598O55z96RSJk1CMRYnZu9fHPU+dX46Ee6baaNiXMI/skYfJG" +
                                                    "afLn8BeD5z/4oKm4IOtkb9or1kOVam3bmInD66klRIz/5fLyL36yvCLN6K1u" +
                                                    "EcagA37ld//+derCn96tU6GaoP0TwCSD/6u1rtnouWZjA9dkcBjF05cl83Dj" +
                                                    "7bbB0+1t191gu6y3HbQa8znBfjcOaZmKYxyjzKLr6NwPT8ITkmgg5IQvhFN3" +
                                                    "TrD3waVD4AoGfkr2rY1lbIWnx5PR00DGNzwZLdB68lzoZMQpJUPIEvM1GKrT" +
                                                    "Lky6zKk0ChgPtzZqbUUsXDy7uqYdf2F/3EO2cU46uGV/yWDzzAjJbMadqnqI" +
                                                    "Y6KZD1Dk8Zd++iZ/f9/XZFTtbZxUUca3z/514ORdsw/eROewM2JTdMuXjl16" +
                                                    "957d6g/ipKkCRjX3k2qm0WoI6nQYXKjMk1VANFhdvUfh6fO82le3egeeC+pI" +
                                                    "kzjPJt+HO+r5MJM8Zmklg63r6EwSUhsgGT2FdJNC5uw6ZcvEATC7xUbGesnS" +
                                                    "PG/pWm1dEwu02voD8Gz3rN9+s9YLfSsxfljQL6yj+SIOHGMTEhBuyI5IaPvG" +
                                                    "NP0CPMOepsM3rGlM5lqg36Pr6HcGh0c4xCroN2lrAMc3oeCAp6Sv7I0pGJa/" +
                                                    "ss637+HwHU7a5dlZdgPNoCL2RG8a2D/11/wHQ9661VfXEu3b1iZ/L3rnys24" +
                                                    "A66nhZJhhOt5aN5qO6ygC8U6ZHWXAHcebud1bz+cxEu6UPb7kvJJKH61lNCN" +
                                                    "ykmYdJWTDSFSqP7eLEz0DNQiIMLpBbsOuMuupkxCaIh9c/itqolGwBP/G/LB" +
                                                    "qST/OzSlXl47et/D1+94QSBdi2rQpSXcpT1L2uT9oQJwww138/dqPbLns+7X" +
                                                    "Onb5wN2NQ693aYjotrN+bz1WtLnohpd+tu2NO19c+0A09/8F00zIS7QTAAA=");
}
