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
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfO/93HN/FiRPHBMc2l9Ak9JZUBCk1giYnm1y4" +
                                                    "4FMcp6pROca7c76N9x+7s/bZ4EKiVg5IRNAaCIj6QxXEv0AQIoKqQsqXFhD9" +
                                                    "QlW16odC1S9FpfmQDwUEtPTNzO7t3t6dSb70pJ2bm31v3nvz3vu9N3f+Mmpx" +
                                                    "bLTHMrWFGc2kaVKm6RPavjRdsIiTPpzbl8e2Q5SMhh3nGKwV5OHXE5999Xgp" +
                                                    "GUetU2gjNgyTYqqahnOUOKY2R5QcSgSroxrRHYqSuRN4DksuVTUppzp0JIfW" +
                                                    "hVgpSuV8FSRQQQIVJK6CdCCgAqb1xHD1DOPABnXuRz9BsRxqtWSmHkVD1ZtY" +
                                                    "2Ma6t02eWwA7tLPfx8Eozly20WDFdmFzjcFP7pFWnr43+UYTSkyhhGpMMHVk" +
                                                    "UIKCkCnUpRN9mtjOAUUhyhTaYBCiTBBbxZq6yPWeQj2OOmNg6tqkckhs0bWI" +
                                                    "zWUGJ9clM9tsV6amXTGvqBJN8X+1FDU8A7ZuDmwVFo6xdTCwUwXF7CKWic/S" +
                                                    "PKsaCkXboxwVG1N3AQGwtumElsyKqGYDwwLqEb7TsDEjTVBbNWaAtMV0QQpF" +
                                                    "/Q03ZWdtYXkWz5ACRX1Rurx4BVQd/CAYC0W9UTK+E3ipP+KlkH8u333bmQeM" +
                                                    "Q0ac66wQWWP6twPTQITpKCkSmxgyEYxdu3NP4c3vnI4jBMS9EWJB89aDV35w" +
                                                    "08Cl9wTNdXVoxqdPEJkW5HPT3R9uy+za38TUaLdMR2XOr7Kch3/eezNStiDz" +
                                                    "Nld2ZC/T/stLR3/3o4dfJp/GUWcWtcqm5uoQRxtkU7dUjdh3EoPYmBIlizqI" +
                                                    "oWT4+yxqg3lONYhYHS8WHUKzqFnjS60m/w1HVIQt2BG1wVw1iqY/tzAt8XnZ" +
                                                    "Qgi1wYNuhWcdEh/+TZEulUydSFjGhmqYEsQuwbZckohsFmximdJoZlyahlMu" +
                                                    "6diedSTHNYqaOV+QXYeauuTYsmTaM/6y5Cw4lOiSq0osrU2NZP3wTbOws/7f" +
                                                    "AsvsBJLzsRg4Z1sUGjSgOmRqCrEL8op7cPTKa4UP4pVU8c6Oop0gL+3JSwt5" +
                                                    "aVdNR+WhWIyL2cTkCv+D92YBBwAhu3ZN/PjwfaeHmyDwrPlmOHpGOgxGe8qM" +
                                                    "ymYmAIssh0QZIrbvV/csp7944Q4RsVJjZK/LjS6dnT95/KGb4yheDdHMOFjq" +
                                                    "ZOx5BqwVAE1FU7PevonlTz678NSSGSRpFeZ72FHLyXJ/OOoG25SJAmgabL97" +
                                                    "EF8svLOUiqNmABQAUYoh6AGfBqIyqjBgxMdTZksLGFw0bR1r7JUPgp20ZJvz" +
                                                    "wQqPj24+3+AnxXXw9HhZwr/Z240WGzeJeGJejljB8Xrs15eeufjsnv3xMLQn" +
                                                    "QsVyglABFBuCIDlmEwLrfz2b/8WTl5fv4RECFDfUE5BiYwZgA1wGx/qz9+7/" +
                                                    "y8cfnftjPIgqCvXTndZUuQx77AykAKhoAGzM96lJQzcVtajiaY2w4Pw6sWPv" +
                                                    "xX+dSQpvarDiB8NN375BsL71IHr4g3s/H+DbxGRW1ALLAzJxABuDnQ/YNl5g" +
                                                    "epRP/uH6Z97FvwTMBZxz1EXCoQtxyxA/eom7ajcf05F3e9kwaNW8K/OVPv6L" +
                                                    "tUe7GifRGKvNoeT7clybPvX3L7hFNelTpyRF+Kek88/1Z27/lPMHccy4t5dr" +
                                                    "AQn6mID3ey/r/44Pt/42jtqmUFL2mqTjWHNZtExBY+D4nRM0UlXvq4u8qGgj" +
                                                    "lTzdFs2hkNhoBgVACHNGzeadkaTpYqe8yZ/43+GkiSE+2c9Zhvm4gw3f8WO2" +
                                                    "zbLVOcw6MNSkq8baTsrbqg5ldc6r+9JSz8ezz33yqkDIqEcixOT0yqPfpM+s" +
                                                    "xEOd1A01zUyYR3RT3OT1wuRv4BOD57/sYaayBVFNezJeSR+s1HTLYpk4tJZa" +
                                                    "XMTYPy4s/ebFpWVhRk91IzEKffKrf/rP79Nn//Z+nQrVBE0iByYR/LfUuma9" +
                                                    "55r1DVyTZcMIO31RMg823m4LPN3edt0Ntst520FDMpfn7HewISNScZSyKDPx" +
                                                    "Gjr3wZPwhCQaCDnqC6HYmeXsvXA14bjCAj8tutvGMjbDk/RkJBvI+KEnowUa" +
                                                    "VJoPnQw/pVQIWWK+BoN12oVJh9iVRoHFw/WNGmAeC+dOrawq48/vjXvINkZR" +
                                                    "BzWt72pkjmghmc1sp6oe4ghv+QMUefSlV96iH+75voiq3Y2TKsr47ql/9h+7" +
                                                    "vXTfNXQO2yM2Rbd86cj59+/cKf88jpoqYFRzi6lmGqmGoE6bwLXLOFYFRAPV" +
                                                    "1XsEnl7Pq711q3fguaCONPHzbPJ9uK2eD7OpI6biamRNR2dTkNoAycxTjG6S" +
                                                    "yyytUbYMNgBmt1iMsV6yNM+ZqlJb1/gCrrZ+HzxbPeu3Xqv1XN9KjB/k9PNr" +
                                                    "aL7ABspiExIQ7tE2T2jr6jS9EZ4hT9Ohq9Y0JnIt0O+hNfQ7yYYHKcQq6Ddp" +
                                                    "KQDH16Bgv6ekr+zVKRiWv7zGu0fY8FOK2sXZmVYDzaAiJqM3DdY/9dX8zyHu" +
                                                    "5vJrq4n2LauTf+a9c+X+3AGX2KKraeF6Hpq3WjYpqlyxDlHdBcCdgTt83dsP" +
                                                    "RXFX5co+JiifgOJXSwndqJiESVcoWhcihervzcJET0MtAiI2PWvVAXfR1ZRR" +
                                                    "CA1Z3xz+VdVEM8Dj/yD54OSK/5AK8oXVw3c/cOXW5znStcgaXlxku7TnUJu4" +
                                                    "P1QAbqjhbv5erYd2fdX9escOH7i72dDjXRoium2v31uP6hbl3fDi21vevO2F" +
                                                    "1Y94c/8/bDf6mdoTAAA=");
}
