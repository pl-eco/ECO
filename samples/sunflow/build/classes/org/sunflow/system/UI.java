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
          1425485134000L;
        public static final String
          jlc$ClassType$jl7 =
          ("H4sIAAAAAAAAAK1ZfWxT1xW/sRPngxDng68yCF+GKUBtykQnFgY4rklMHRJw" +
           "EpWg1nl5vo4fPL/3+t514tBCS6sKNIlu2mhHqy5/VLQVjEE3DXWdhEQ3jYZ2" +
           "bKKqtjFpdNtfXRnSmNauWrtu59xn+9nPNtjTIvnk+tx7z++ce885997js7dI" +
           "naGTdZoqT0/IKvPSNPPulzd52bRGDe/O8KZBQTdoLCALhjEEvKi48nX3J599" +
           "K9HqIK5R0iEoisoEJqmKsYcaqjxJY2HitrhBmSYNRlrD+4VJwZdikuwLSwbr" +
           "DpM5eVMZ8YSzKvhABR+o4OMq+PzWKJg0lyqpZABnCAozHiWHSU2YuDQR1WNk" +
           "RaEQTdCFZEbMILcAJDTg9xEwik9O62R5znbT5iKDn1vnO/HdR1p/5CTuUeKW" +
           "lAiqI4ISDEBGSXOSJsepbvhjMRobJW0KpbEI1SVBlg5yvUdJuyFNKAJL6TS3" +
           "SMhMaVTnmNbKNYtom54SmarnzItLVI5lv9XFZWECbF1g2WpauAP5YGCTBIrp" +
           "cUGk2Sm1ByQlxsgy+4ycjZ4HYQBMrU9SllBzULWKAAzSbu6dLCgTvgjTJWUC" +
           "htapKUBhZHFZobjWmiAeECZolJFF9nGDZheMauQLgVMYmW8fxiXBLi227VLe" +
           "/tzateXZx5Q+xcF1jlFRRv0bYFKnbdIeGqc6VURqTmxeG35eWHDxmIMQGDzf" +
           "Ntgc88bjt7ev77w0a475UokxA+P7qcii4qnxlmtLAl2bnahGg6YaEm5+geXc" +
           "/QczPd1pDSJvQU4idnqznZf2XN775Bl600GaQsQlqnIqCX7UJqpJTZKp3ksV" +
           "qguMxkKkkSqxAO8PkXpohyWFmtyBeNygLERqZc5yqfw7LFEcROAS1UNbUuJq" +
           "tq0JLMHbaY0QMgc+pA4+bcT8cyNhJORLqEnqE0RBkRTVB75LBV1M+Kio+gwh" +
           "qcmwa0ZKicvqlM/QRZ+qT1jfpw1Gk77hkBddSvt/Ckuj5q1TNTWwqEvsIS1D" +
           "NPSpcozqUfFEqid4+1z0XUfOxTM2M7IExHsz4r2meO9wyNOvxlIyJTU1XPg8" +
           "RDN3C9b6AEQt5LPmrsjDO8eOrXSCm2hTtbBQOHQlmJFRISiqASu0QzyBieBf" +
           "i17ed9T76WvbTP/ylc/DJWeTSyenjow8scFBHIUJFU0CVhNOH8Q0mEt3Hnsg" +
           "lZLrPvrhJ+efP6RaIVWQoTORXjwTI3WlffF1VaQxyH2W+LXLhQvRi4c8DlIL" +
           "4Q8pjwngopBNOu0YBRHbnc1+aEsdGBxX9aQgY1c2ZTWxhK5OWRzuFS1I2k0H" +
           "wQ20KcgT5443L71w4cV1mx35Odadd2pFKDMjts3a/yGdUuD/4eTgd567dXQf" +
           "33wYsaoUgAdpAOIXdgNW7JnZR69/cOPU+w7LYRip13RpEsI6DULWWDAQ3jKk" +
           "GNxXz7CSVGNSXBLGZYqO97l79X0X/vpsq7lTMnCyG73+7gIs/j095Ml3H/ln" +
           "JxdTI+LxYpluDTNXoMOS7Nd1YRr1SB95b+kLbwvfg+wHGceQDlKeRAg3jfC1" +
           "9/It6eL0XlvfBiTLtaK+NOcs4t/aAHp1mQAJwrXA8q+W4b9fHfv88j/Amp2k" +
           "XtVjkiLIsCRd5cNrB56xeWH5rwF5/Kk/f8rXoyiwShwttvmjvrMvLQ5svcnn" +
           "Wx6Os5elixMU3EesuRvPJD92rHT9wkHqR0mrmLnsjAhyCp1tFA54I3sDggtR" +
           "QX/hYW2eTN25CF5ij648WHtsWYkR2jga201mOPExeCo0kMw50Zo5Ivh/7O3Q" +
           "kM5LE8Ibm/mUFZx6kHyZ72gtg7tbalyWwOFcBr9WMVADd4uRWrzqpRlx+gdD" +
           "PHQ50TJIhMv6Sk6VuSRzUt1NlZ4yqmDz60i2ItmGZDug1/YGB/qL4Wts8B3I" +
           "XFgBfG+V8H3+0J5ieIcN/h5kdlYAH64Ovs4fCATDxfhOG/5yZHoqwN9dpfk9" +
           "gQeHiuFrbfBrkNlVAfxIdfDO0GCJxa+zoa9D5oYK0PdVufjhUG9fCetdNvyN" +
           "yNxUAf5Yldb3DpcIvHob+leRuaUCdFql9ZFAcFewGL/Bhr8VmYEK8PdXid8T" +
           "3BXoK8ZvtOEHkdlXAb5a5eoPBR8qRm+yoe9EZn8F6Kxaz+/vLUafY0MfQGak" +
           "AvTpKsP+gVBksBi+2QY/jMyHKoA/XKXxu/sDxehzbeijyHy4AvSnq0SP7I0U" +
           "o7fY0KPIFCtAP1bl0g9HgiVyntsGT5GZqAD+eJXGB/wlTtvWdGkpzkIp2/Nv" +
           "iwQv0UvLVQ14xePUUydmYgOv3Ge+vdoLX+J4o/zBb/79S+/JP14p8VR0Zao+" +
           "FqAD8QoefP28mmJd7L5x+vtvsGvrvmbirS1/GbVPfPupjxYPbU2MVfHMW2az" +
           "3C7ydP/ZK71rxG87iDN3PywqEBVO6i68FTbplKV0ZajgbtiJJPvUqmTPtqVt" +
           "r4KazOsZv89npJW/NfA66zVrT1z2y3d4SryKZAYeU5N4Ix6Im54Em7Os9IMo" +
           "mNQYf8Ic/MnCH295beYGf5Jx0S/e1Rq7H4NjKLA1k9RuVr6G5+/Q90MkZ0AM" +
           "1964w5MH/IS/vsw61cyrq371xMyqP4GrjpIGyYCrvV+fKFE4y5vzt7Mf3Hxv" +
           "7tJz/BleOy4Y5kbaK47FBcWCOiFXuzlvpTRNM2P/NAS0yyyeZLdzfskKC1i5" +
           "qKgKa1YOxXMz7oaFM8O/NZXMVvcaw6QhnpLl/FdKXtul6TQucXUazTeLxv/9" +
           "FB5IxRrg44M3uJZvmkMvMjInbyj4U6aVP+gSZCwYhM230MXuENOR1LjB8qqW" +
           "x6WZq+987D5iJoPClykvXGem2udd/51z4xzm+SbPBblNa4Dnm4EjGVlevgjO" +
           "ZXVzzc2jvO0/8Efg8wV+0Ak5g2Cdb571kMRc6OU1dk1LZ7eyxYpM7EfuLCzB" +
           "irssQVQMJaORC9eP3s+zqntSMiRGY0OZqnxh5cEqsHUXVOpLLlJU/PD88dkV" +
           "H4108BJsdj3yizj9glZUxOkTjATw6+p//9bPF4xdcxLHDtIkq0Jsh8BLW6SR" +
           "JXRqJFQ5lta2bTdvIlMNmTOv5g4RmrGJl1qi4uMvfXH1L4duXHESF8QUOrKg" +
           "U0iujHjL/faRL8AzBK0HYBak4RZzNmRD7hgZB2jPcXMlN0buLScbf9qxV+bw" +
           "1wPwcKr3qCklhmKX2XJ+StPye83Y/59d6bBO1lewdjnTM9cB0s5jxuaA+Z1w" +
           "QHcEwv5IJDq0dzAYHfHvCfl7wvw1MatBZ02Q6/wzJJe5EbOm/yJ9h9NfF281" +
           "st/nJF140zDznp1JCr5q+ZUsbG5G0oOE37T5K3s3khEk+5CMIeFrvx8JL9Tz" +
           "+t40ksNInkZyjN+00gWlR3uI9KfMn8Ci4vmZnbseu33/KzyD1MFeHDyY8aB6" +
           "s6Cau0SsKCstK8vV1/VZy+uNqx2Z46ug1JpvPj8P/gte0aOMbhwAAA==");
    }
    public enum PrintLevel {
        ERROR, WARN, INFO, DETAIL; 
        private PrintLevel() {  }
        public static final String jlc$CompilerVersion$jl7 = "2.6.1";
        public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAK0Ya2xb1fnETpxn4zRpSilt0qQuU1qwgQmkLqxtEpzWndtE" +
                                                        "cZKpQeCeXB/Ht72+93LvceKElj4kFJhEN43CysTyYypDsK5F0yq2H0idpkE7" +
                                                        "1klF07ZOWtn2i9FVWqfB0GBs3/mu7fuw3cc0S/f43HPO9zrf+566RupMg2zS" +
                                                        "NWV+RtF4mOV5eJ/yYJjP68wM74w/OEoNk6WGFGqa47CWlHrfCH786TcybT4S" +
                                                        "mCIdVFU1TrmsqeYYMzVllqXiJGivRhWWNTlpi++jszSS47ISicsm74+TZgco" +
                                                        "J6F4kYUIsBABFiLIQmTAPgVAy5iayw4JCKpy8wnyFKmJk4AuCfY46XEj0alB" +
                                                        "swU0oygBYGgQ75MgFALnDbKuJLslc5nAL2yKHP/W420/9JPgFAnKakKwIwET" +
                                                        "HIhMkZYsy04zwxxIpVhqiixXGUslmCFTRV5AvqdIuynPqJTnDFa6JLGY05mB" +
                                                        "NO2ba5GEbEZO4ppREi8tMyVVfKtLK3QGZF1py2pJOCzWQcAmGRgz0lRiRZDa" +
                                                        "/bKa4qTbC1GSMfQVOACg9VnGM1qJVK1KYYG0W7pTqDoTSXBDVmfgaJ2WAyqc" +
                                                        "rK6KVNy1TqX9dIYlOVnlPTdqbcGpRrwIAcJJp/cYYgItrfZoyaGfa7sfPvak" +
                                                        "ukP1Ic8pJimC/wYA6vIAjbE0M5gqMQuwZWP8RbryrWd8hMDhTs9h68ybB65v" +
                                                        "u6fr3HnrzF0VzoxM72MST0onp1svrRnq2+wXbDTomikL5bskR/MfLez053Xw" +
                                                        "vJUljGIzXNw8N/b2nsOvs6s+0hQjAUlTclmwo+WSltVlhRnbmcoMylkqRhqZ" +
                                                        "mhrC/Riph3lcVpm1OpJOm4zHSK2CSwEN3+GK0oBCXFE9zGU1rRXnOuUZnOd1" +
                                                        "QkgzPKQOnk5i/TrEwEksktGyLEIlqsqqFgHbZdSQMhEmaRGTZnUFtGbm1LSi" +
                                                        "zUVMQ4poxoz9Pm9ylo1MxMLCpPT/J7K84LxtrqYGLnWN16UV8IYdmpJiRlI6" +
                                                        "nhuMXj+dfNdXMvGCzJysA/ThAvqwhT48EQuNgs3zOJtlCqmpQQIrBEVLY3Df" +
                                                        "+8FzIaa19CUe27n3mV4/mIo+VwuXJY72gigFNqKSNmS7dwyDmAQ2tuq7jy6G" +
                                                        "P3l1q2VjkeqxuCI0OXdi7sjkoft8xOcOqkIsWGoS4KMiFJZCXsjrTJXwBhc/" +
                                                        "+PjMiwc1261cUbrg7eWQwlt7vQowNImlIP7Z6Deuo2eTbx0M+UgthAAIe5yC" +
                                                        "mUJE6fLScHltfzECClnqQOC0ZmSpIraKYauJZwxtzl5By2gVQ7tlJEKBHgYx" +
                                                        "eA7/5NxLZ7+9abPPGWeDjsyVYNzy2uW2/scNxmD9DydGn3/h2uKjqHw4sb4S" +
                                                        "gZAYh8CHQRtwY0+ff+Ly+1dO/tpnGwwn9bohz4Jr5wHJ3TYZcHEFwozQa2hC" +
                                                        "zWopOS3TaYUJw/ssuOH+s3891mZpSoGVoqLvuTkCe/3OQXL43cf/2YVoaiSR" +
                                                        "YmzR7WPWDXTYmAcMg84LPvJH3lv70jv0OxABIeqY8gLDQEJQNIJ3H0aV9OF4" +
                                                        "r2fvPjGs08v28riyCt9qgfSGKg4ShdLAtq/Wib9f3PvZ2/8AaXaSes1IySpV" +
                                                        "4Er6qrvXsMizDrf814gyffTPn+B9lDlWhfTigZ+KnHp59dCWqwhvW7iA7s6X" +
                                                        "BymoSWzYB17PfuTrDfzcR+qnSJtUKHgmqZITxjYFSd4sVkFQFLn23Qnbyk79" +
                                                        "JQ9e4/UuB1mvb9nBEebitJg3We6EZ5aDRhqEjpbBs6KQJvBf7HboYlyRJwQn" +
                                                        "mxGkB8eQGL5gaZRD/ZabVmQwuICJpRUHNoS2OKkV5V4e3qNjYyNj6Lw46AVa" +
                                                        "BLF9scRMsJinbsbMYBVmxPTLYtgihq1i2Ab0a786MLa7nHyNhzxmyjtvgfz2" +
                                                        "2yQf2z08Uk7e5yF/l1jsuQXy8dsjH3gkOj4Qi5cz4M9XRuR3I9rm9GAiAtva" +
                                                        "atUcVqInjx5fSo28cr+VD9vdFZLw8h/85t+/DJ/444UKKTxQqMZtgj5Bz5WE" +
                                                        "d2GVazvb1177/pv80qYvWfQ2Vg8QXsB3jn64enxLZu9tpN5uj+RelK/tOnVh" +
                                                        "+93SN33EX/LZssLdDdTv9tQmg0GnoY67/LXLmf5uRWdb855IXVOoaMR7JzR0" +
                                                        "GP9FiAlbPQHipjcI78jNY5DgZkWUGklblgTK6a6cpKJZnWNaWfjxHT96+NWl" +
                                                        "K5gmEfWem0rjNWUwDGjA5FnmFcvJoXqDPfQbGdAg9+YN0hDYCWZEq39Y+t76" +
                                                        "Xx1aWv8nMNUp0iCbEG4HjJkKDY0D5m+n3r/63rK1p7E0qp2mpqVIbydY3ui5" +
                                                        "+jdku8VxU7quW2JkwKeb7KK2qNLOitUvSLqqrEO2ujrp9FKw4Y6lid9ajBY7" +
                                                        "r0Zof9I5RXFmD8c8oBssLSNLjVYu0fFvHhJXOQciKeAEucxbRw9w0uw4CjZV" +
                                                        "mDkPHeLED4fE9LAwsxv4dSI3bXJHR/mcvHTxFx8Fj1gBwV0x4EeFAqgX7vLv" +
                                                        "/A8089DXMR6UFNcAadUUJ6HBqP6BAnH1I+fNVkT/D/wIPJ+LRxgiLmAPtsJO" +
                                                        "8CIehvH7h67ni6pstb1T7IvVRbiCnptcQVKKZZOJs5cXH8LIGpyVoRtlqfHC" +
                                                        "FxN3RWg3Pv2urygVLykpfXDmufM9H052YHtcvA9ncb2L6mXF9Q5qZmC9rv73" +
                                                        "P/3Zyr2X/MQ3TJoUjaaGKbYcpBFqfWZmoLXL61u3YVJqmRMlSVuh/armpQWZ" +
                                                        "sAROSgde/vziXw5eueAnAfArYcjUgOYauvdwte9STgShcZg9AlAQilstaIiI" +
                                                        "aBgFA2gvrZZaIU7urYZbfHbzdkziyw5YODMGtZyaEmi7PXE/p+vOXcv//2dT" +
                                                        "egq6h1u4u5LoxYqsHX3GY4DOTUjSHUPxgUQiOb5nNJqcHBiLDQzGo2ijOmzW" +
                                                        "RJHnI2J4GoVYtOxXjM/ieKxc1WL5eRzy7mrDin3eReJ61Z0dhphuFsMgVmpY" +
                                                        "L+VdHZ3XwnflrK+LSenM0s7dT15/6BUMAHVwlQsLBQOot/rUUh3QUxVbEVdg" +
                                                        "R9+nrW80bvAVMpCrg3VyjyH9v72fwzLJFQAA");
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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
