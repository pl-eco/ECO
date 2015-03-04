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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "GPlsyyttw0EQbsGh22+Fq2XDeeELz/DzjYISAAA=");
}
