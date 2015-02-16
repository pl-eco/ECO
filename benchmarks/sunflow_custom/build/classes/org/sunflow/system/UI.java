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
          1415385948000L;
        public static final String
          jlc$ClassType$jl7 =
          ("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" +
           "r/GDhBwAAA==");
    }
    public enum PrintLevel {
        ERROR, WARN, INFO, DETAIL; 
        private PrintLevel() {  }
        public static final String jlc$CompilerVersion$jl7 = "2.6.1";
        public static final long jlc$SourceLastModified$jl7 = 1415385948000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYa2wcR3l8Z5+f8Tl2nKZpYsfOpchJe9sWtVJwSXJ2z8mF" +
                                                        "S2z5heKqvYz35nyb7O1ud+fss5s0D6lyi9SAaFpSVPwDpVQtIakQUeFHpSBE" +
                                                        "m1CClAoBQSIFfpWGSATRUtFS+GZm7/Zxd3kgcdLOzc7M95rvvaeuoTrLRJsM" +
                                                        "XZ2fUXUaJQUa3ac+GKXzBrGiO5MPjmDTIulBFVvWOKyl5N43wh9/+o1sWwCF" +
                                                        "plAH1jSdYqromjVKLF2dJekkCjurcZXkLIrakvvwLJbyVFGlpGLR/iRqdoFS" +
                                                        "FEkWWZCABQlYkDgLUsw5BUDLiJbPDTIIrFHrCfQUqkmikCEz9ijq8SIxsIlz" +
                                                        "NpoRLgFgaGDvkyAUBy6YaF1JdiFzmcAvbJKOf+vxth8GUXgKhRVtjLEjAxMU" +
                                                        "iEyhlhzJTRPTiqXTJD2FlmuEpMeIqWBVWeB8T6F2S5nRMM2bpHRJbDFvEJPT" +
                                                        "dG6uRWaymXmZ6mZJvIxC1HTxrS6j4hmQdaUjq5BwiK2DgE0KMGZmsEyKILX7" +
                                                        "FS1NUbcfoiRj5CtwAEDrc4Rm9RKpWg3DAmoXulOxNiONUVPRZuBonZ4HKhSt" +
                                                        "roqU3bWB5f14hqQoWuU/NyK24FQjvwgGQlGn/xjHBFpa7dOSSz/Xdj987Elt" +
                                                        "hxbgPKeJrDL+GwCoywc0SjLEJJpMBGDLxuSLeOVbzwQQgsOdvsPizJsHrm+7" +
                                                        "p+vceXHmrgpnhqf3EZmm5JPTrZfWDPZtDjI2GgzdUpjyPZJz8x+xd/oLBnje" +
                                                        "yhJGthktbp4bfXvP4dfJ1QBqSqCQrKv5HNjRclnPGYpKzO1EIyamJJ1AjURL" +
                                                        "D/L9BKqHeVLRiFgdzmQsQhOoVuVLIZ2/wxVlAAW7onqYK1pGL84NTLN8XjAQ" +
                                                        "Qs3woDp4OpH4dbCBonFpwgJzl7CMNUXTJTBegk05KxFZT03D7WZz2NxvSXLe" +
                                                        "onpOsvJaRtXnJMuUJd2ccd7nLUpy0kQiyqzL+D/hLTB52uZqauCq1/gdXQUf" +
                                                        "2aGraWKm5OP5gfj106l3AyXDt2+ConWAPmqjjwr00YlEZAQ8gSbJLFFRTQ0n" +
                                                        "sIJRFHoELewHf4ZI19I39tjOvc/0BsGAjLlauEJ2tBeEstmIy/qg4/QJHtpk" +
                                                        "sLxV3310MfrJq1uF5UnVI3RFaHTuxNyRyUP3BVDAG2qZWLDUxMBHWIAsBcKI" +
                                                        "38Uq4Q0vfvDxmRcP6o6zeWK3HQPKIZkP9/oVYOoySUNUdNBvXIfPpt46GAmg" +
                                                        "WggMEAwpBuOFONPlp+Hx5f5iXGSy1IHAGd3MYZVtFYNZE82a+pyzwi2jlQ3t" +
                                                        "wkiYAn0M8pA69JNzL5399qbNAXf0Dbvy2RihwpeXO/ofNwmB9T+cGHn+hWuL" +
                                                        "j3Llw4n1lQhE2DgIng3agBt7+vwTl9+/cvLXAcdgKKo3TGUWHL4ASO52yIDj" +
                                                        "qxB8mF4jE1pOTysZBU+rhBneZ+EN95/967E2oSkVVoqKvufmCJz1OwfQ4Xcf" +
                                                        "/2cXR1Mjs8TjiO4cEzfQ4WCOmSaeZ3wUjry39qV38HcgLkIsspQFwsML4qIh" +
                                                        "fvdRrpI+Pt7r27uPDeuMsr0CX1nF32qB9IYqDhKHgsGxr9aJv1/c+9nb/wBp" +
                                                        "dqJ63UwrGlbhSvqqu9cQy74ut/zXsDp99M+f8Psoc6wKSccHPyWdenn14Jar" +
                                                        "HN6xcAbdXSgPUlCpOLAPvJ77KNAb+nkA1U+hNtkugyaxmmfGNgWp3yrWRlAq" +
                                                        "efa9aVzkrP6SB6/xe5eLrN+3nOAIc3aazZuEO/Ezy0EjDUxHy+BZYScP/s92" +
                                                        "Oww2riggxCebOUgPHyNs+ILQKIWqLj+tKmBwIYsXXBTYYNqiqJYVgQV4j4+O" +
                                                        "Do9y5+WDYdNCHNsXS8yEi9nrZswMVGGGTb/Mhi1s2MqGbUC/9qux0d3l5Gt8" +
                                                        "5Hn+vPMWyG+/TfKJ3UPD5eQDPvJ3scWeWyCfvD3yoUfi47FEspyBYKEyoqAX" +
                                                        "0Ta3ByMW2NZWq/F4fXry6PGl9PAr94t82O6tm5iX/+A3//5l9MQfL1RI4SG7" +
                                                        "RncIBhg9TxLexWtfx9m+9tr336SXNn1J0NtYPUD4Ad85+uHq8S3ZvbeRert9" +
                                                        "kvtRvrbr1IXtd8vfDKBgyWfLynkvUL/XU5tMAv2HNu7x1y53+rsVnW0t+CJ1" +
                                                        "jV3RsPdOaPN4/GchJio6BY4b3yC8c24egwQ3y6LUcEZYEiinu3KSiucMytPK" +
                                                        "wo/v+NHDry5d4WmSo95zU2n8pgyGAW2ZMkv8Yrk51G6wx/1GATSce+sGaQjs" +
                                                        "hGdE0VUsfW/9rw4trf8TmOoUalAsCLcxc6ZCm+OC+dup96++t2ztaV4a1U5j" +
                                                        "SyjS3x+Wt3+ero6z3eK6KcMwhBhZ8Okmp6gtqrSzYvULkq4q65tFryefXgo3" +
                                                        "3LE08VvBaLEfa4SmKJNXVXf2cM1DhkkyCmepUeQSg//NQ+Iq54AlBT7hXBbE" +
                                                        "0QMUNbuOgk3ZM/ehQxQF4RCbHmZmdgO/HstPW9TVZz6nLF38xUfhIyIgeCsG" +
                                                        "/qnBBvXDXf5d8IFmGvk6jwclxTVAWrXYSWgwqn+24Lj6OefNIqL/B34Ins/Z" +
                                                        "wwyRL/DObIWT4Fk8jPKvIoZRKKqy1fFOts9WF+EKem5yBSk5kUuNnb28+BCP" +
                                                        "rOFZBXpUkh63v6N4K0Kn8en3fFupeEkp+YMzz53v+XCygzfNxftwF9e7sFFW" +
                                                        "XO/AVhbW6+p//9Ofrdx7KYgCQ6hJ1XF6CPOWAzVCrU+sLLR2BWPrNpGU51hJ" +
                                                        "ErDbr2peasvES+CUfODlzy/+5eCVC0EUAr9ihoxNaLmhp49W+1rlRhAZh9kj" +
                                                        "AAWhuFVAQ0TkhmEbQHtptdQKUXRvNdzsY5y/Y2Lfe8DCiTmg57U0Q9vti/t5" +
                                                        "w3DvCv//n03pKegebuHuSqIXK7J27jM+A3RvQpLuGEzGxsZS43tG4qnJ2Ggi" +
                                                        "NpCMcxs1YLMmznk+woanuRCLwn7Z+Cwfj5Wrmi0/z4eCt9oQsc+/iDyvhrvD" +
                                                        "YNPNbBjglRqvlwqejs5v4bvy4ptjSj6ztHP3k9cfeoUHgDq4yoUF2wDqRZ9a" +
                                                        "qgN6qmIr4grt6Pu09Y3GDQE7A3k6WDf3PKT/F05R8tbfFQAA");
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
      1415385948000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
