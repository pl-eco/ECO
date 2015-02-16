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
      1169093936000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "CLfg0B20wtWy4bzwbxnoYGtyEgAA");
}
