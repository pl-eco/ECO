package org.sunflow.system;
import org.sunflow.system.ui.ConsoleInterface;
import org.sunflow.system.ui.SilentInterface;
public final class UI {
    private static UserInterface ui = new ConsoleInterface();
    private static boolean canceled = false;
    private static int verbosity = 0;
    public enum Module {
        API, GEOM, HAIR, ACCEL, BCKT, IPR, LIGHT, GUI, SCENE, BENCH, TEX,
         IMG,
         DISP,
         QMC,
         SYS,
         USER,
         CAM;
         
        private Module() {
            
        }
        public static final String
          jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long
          jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String
          jlc$ClassType$jl7 =
          ("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" +
           "fPP5efBfs9nHoJQcAAA=");
    }
    public enum PrintLevel {
        ERROR, WARN, INFO, DETAIL; 
        private PrintLevel() {  }
        public static final String jlc$CompilerVersion$jl7 = "2.6.1";
        public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYa2wcR3l8ts/P+Bw7TtM0sWPnUuSkvW2LWim4JLHdc3Lh" +
                                                        "Elt+obi0l/HenG+Tvd3t7px9dpPmIVUuSA2IpiVFxT9QStUSkgoRFX5UCkK0" +
                                                        "CSVIqRAQJFLgV2mIRBAtFS2Fb769u33cXR5InLRzszPzveZ776lrpNYyySZD" +
                                                        "V+dnVJ1HWI5H9qkPRvi8wazIzviDI9S0WHJQpZY1DmsJuef10EeffCPdGiDB" +
                                                        "KdJONU3nlCu6Zo0yS1dnWTJOQs5qVGUZi5PW+D46S6UsV1Qprli8L06aXKCc" +
                                                        "hOMFFiRgQQIWJGRB6ndOAdAypmUzgwKCatx6gjxFquIkaMiCPU66vUgMatJM" +
                                                        "Hs0ISgAY6sX7JAiFwDmTrCvKbstcIvDzm6Tj33q89YfVJDRFQoo2JtiRgQkO" +
                                                        "RKZIc4Zlpplp9SeTLDlFlmuMJceYqVBVWUC+p0ibpcxolGdNVrwksZg1mIk0" +
                                                        "nZtrloVsZlbmulkUL6UwNVl4q02pdAZkXenIaks4JNZBwEYFGDNTVGYFkJr9" +
                                                        "ipbkpMsPUZQx/CU4AKB1GcbTepFUjUZhgbTZulOpNiONcVPRZuBorZ4FKpys" +
                                                        "rohU3LVB5f10hiU4WeU/N2JvwakGvAgBwkmH/xhiAi2t9mnJpZ9rux8+9qS2" +
                                                        "Qwsgz0kmq4L/egDq9AGNshQzmSYzG7B5Y/wFuvLNZwKEwOEO32H7zBsHrm+7" +
                                                        "p/PcefvMXWXODE/vYzJPyCenWy6tGezdXC3YqDd0SxHK90iO5j+S3+nLGeB5" +
                                                        "K4sYxWaksHlu9K09h19jVwOkMUaCsq5mM2BHy2U9YygqM7czjZmUs2SMNDAt" +
                                                        "OYj7MVIH87iiMXt1OJWyGI+RGhWXgjq+wxWlAIW4ojqYK1pKL8wNytM4zxmE" +
                                                        "kCZ4SC08HcT+tYuBk69IaT3DJCpTTdF0CWyXUVNOS0zWEyYzdCk6OCxNwy2n" +
                                                        "M9Tcb0lWVkup+lxCzlpcz0iWKUu6OVNYlqx5i7OMNBGLCCsz/s/4c0K+1rmq" +
                                                        "Krj6NX7HV8FnduhqkpkJ+Xh2IHr9dOKdQNER8jfDyTpAH8mjj9joIxOx8Ah4" +
                                                        "Bo+zWaaSqioksEJQtPUKWtkP/g2Rr7l37LGde5/pqQaDMuZq4ErF0R6QLs9G" +
                                                        "VNYHnSAQw1AngyWu+u6ji5GPX9lqW6JUOWKXhSbnTswdmTx0X4AEvKFXiAVL" +
                                                        "jQJ8RATMYmAM+12uHN7Q4vsfnXnhoO44nyeW52NCKaTw6R6/AkxdZkmIkg76" +
                                                        "jevo2cSbB8MBUgOBAoIjp2DMEHc6/TQ8vt1XiJNClloQOKWbGaqKrUJwa+Rp" +
                                                        "U59zVtAyWsTQZhuJUKCPQQyxQz859+LZb2/aHHBH45Arv40xbvv2ckf/4yZj" +
                                                        "sP6HEyPPPX9t8VFUPpxYX45AWIyD4OmgDbixp88/cfm9Kyd/HXAMhpM6w1Rm" +
                                                        "IQDkAMndDhkIBCoEI6HX8ISW0ZNKSqHTKhOG92low/1n/3qs1daUCisFRd9z" +
                                                        "cwTO+p0D5PA7j/+zE9FUySIROaI7x+wbaHcw95smnRd85I68u/bFt+l3IE5C" +
                                                        "bLKUBYbhhqBoBO8+girpxfFe3959YlhnlOzlcGUVvtUA6Q0VHCQKBYRjXy0T" +
                                                        "f7+499O3/gHS7CR1uplUNKrClfRWdq8hkY1dbvmvYXX66J8/xvsocawyScgH" +
                                                        "PyWdemn14JarCO9YuIDuypUGKahcHNgHXst8GOgJ/jxA6qZIq5wviyapmhXG" +
                                                        "NgWlgFWolaB08ux707qdw/qKHrzG710usn7fcoIjzMVpMW+03QnPLAeN1Asd" +
                                                        "LYNnRT6Z4L/YbTfEuCJHCE42I0g3jmExfM7WKIcqLzutKmBwQQsLMA5sCG1x" +
                                                        "UiOKwhy8R0dHh0fReXEw8rQIYvt8kZlQIZvdjJmBCsyI6RfFsEUMW8WwDejX" +
                                                        "fLl/dHcp+Sofecynd94C+e23ST62e2i4lHzAR/4usdh9C+Tjt0c++Eh0vD8W" +
                                                        "L2WgOlceUbUX0Ta3BxMR2NZWqvmwXj159PhScvjl++182Oato4SX/+A3//5l" +
                                                        "5MQfL5RJ4cF8ze4QDAh6niS8C2thx9m+9ur33+CXNn3BprexcoDwA7599IPV" +
                                                        "41vSe28j9Xb5JPejfHXXqQvb75a/GSDVRZ8tKe+9QH1eT200GfQj2rjHXzvd" +
                                                        "6e9WdLY154vUVfmKRrx3QNuH8V+EmIjdOSBueoPwjtw8BgluVkSp4ZRtSaCc" +
                                                        "rvJJKpoxOKaVhR/f8aOHX1m6gmkSUe+5qTR+UwbDgDZNmWV+sdwcajfYQ79R" +
                                                        "AA1yb90gDYGdYEa0u4yl763/1aGl9X8CU50i9YoF4bbfnCnT9rhg/nbqvavv" +
                                                        "Llt7Gkujmmlq2Yr094ul7aCny0O2m103ZRiGLUYafLrRKWoLKu0oW/2CpKtK" +
                                                        "+mi795NPL4Xq71ia+K3NaKE/a4AmKZVVVXf2cM2DhslSCrLUYOcSA//mIXGV" +
                                                        "ciCSAk6Qy5x99AAnTa6jYFP5mfvQIU6q4ZCYHhZmdgO/HstOW9zVdz6rLF38" +
                                                        "xYehI3ZA8FYM+OkhD+qHu/y76geaePjrGA+KiquHtGqJk9BgVP6Mgbj6kPMm" +
                                                        "O6L/B34Ens/EIwwRF7BTW+EkeBEPI/iVxDByBVW2ON4p9sXqIlxB902uICHH" +
                                                        "Momxs5cXH8LIGppVoGdlyfH8dxVvReg0Pn2eby1lLykhv3/m2fPdH0y2YxNd" +
                                                        "uA93cb2LGiXF9Q5qpWG9tu73P/3Zyr2XqklgiDSqOk0OUWw5SAPU+sxKQ2uX" +
                                                        "M7Zuw6TUPCdKktZ8+1XJS/MyYQmckA+89NnFvxy8cqGaBMGvhCFTE1pw6PEj" +
                                                        "lb5euRGEx2H2CEBBKG6xoSEiomHkDaCtuFpshTi5txJu8XHO3zGJ7z9g4cwc" +
                                                        "0LNaUqDt8sX9rGG4d23//59N6SnoHm7h7oqiFyqyNvQZnwG6NyFJtw/G+8fG" +
                                                        "EuN7RqKJyf7RWP9APIo2asBmVRR5PiKGp1GIRdt+xfhVHI+VqlosP4dDzltt" +
                                                        "2LHPv0g8r4a7wxDTzWIYwEoN66Wcp6PzW/iurP0NMiGfWdq5+8nrD72MAaAW" +
                                                        "rnJhIW8AdXafWqwDuitiK+AK7uj9pOX1hg2BfAbydLBu7jGk/xckhwsq7xUA" +
                                                        "AA==");
    }
    private UI() { super(); }
    public static final void set(UserInterface ui) {
        if (ui ==
              null)
            ui =
              new SilentInterface(
                );
        UI.
          ui =
          ui;
    }
    public static final void verbosity(int verbosity) {
        UI.
          verbosity =
          verbosity;
    }
    public static final String formatOutput(Module m,
                                            PrintLevel level,
                                            String s) {
        return String.
          format(
            "%-5s  %-6s: %s",
            m.
              name(
                ),
            level.
              name(
                ).
              toLowerCase(
                ),
            s);
    }
    public static final synchronized void printDetailed(Module m,
                                                        String s,
                                                        java.lang.Object ... args) {
        if (verbosity >
              3)
            ui.
              print(
                m,
                PrintLevel.
                  DETAIL,
                String.
                  format(
                    s,
                    args));
    }
    public static final synchronized void printInfo(Module m,
                                                    String s,
                                                    java.lang.Object ... args) {
        if (verbosity >
              2)
            ui.
              print(
                m,
                PrintLevel.
                  INFO,
                String.
                  format(
                    s,
                    args));
    }
    public static final synchronized void printWarning(Module m,
                                                       String s,
                                                       java.lang.Object ... args) {
        if (verbosity >
              1)
            ui.
              print(
                m,
                PrintLevel.
                  WARN,
                String.
                  format(
                    s,
                    args));
    }
    public static final synchronized void printError(Module m,
                                                     String s,
                                                     java.lang.Object ... args) {
        if (verbosity >
              0)
            ui.
              print(
                m,
                PrintLevel.
                  ERROR,
                String.
                  format(
                    s,
                    args));
    }
    public static final synchronized void taskStart(String s,
                                                    int min,
                                                    int max) {
        ui.
          taskStart(
            s,
            min,
            max);
    }
    public static final synchronized void taskUpdate(int current) {
        ui.
          taskUpdate(
            current);
    }
    public static final synchronized void taskStop() {
        ui.
          taskStop(
            );
        canceled =
          false;
    }
    public static final synchronized void taskCancel() {
        printInfo(
          Module.
            GUI,
          "Abort requested by the user ...");
        canceled =
          true;
    }
    public static final synchronized boolean taskCanceled() {
        if (canceled)
            printInfo(
              Module.
                GUI,
              "Abort request noticed by the current task");
        return canceled;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "5/kaAAA=");
}
