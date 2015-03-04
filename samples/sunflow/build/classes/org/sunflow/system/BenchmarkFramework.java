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
                                                nanoTime(
                                                  );
                                              for (int i = 0; i <
                                                                timers.
                                                                  length &&
                                                                (System.
                                                                   nanoTime(
                                                                     ) -
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
                                                            kernelBegin(
                                                              );
                                                          timers[i].
                                                            start(
                                                              );
                                                          test.
                                                            kernelMain(
                                                              );
                                                          timers[i].
                                                            end(
                                                              );
                                                          test.
                                                            kernelEnd(
                                                              );
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
                                                    seconds(
                                                      );
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
                                                    seconds(
                                                      );
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
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "VrhSNpzn/w0gXo7OXBIAAA==");
}
