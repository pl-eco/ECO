package org.sunflow.system;
public class Timer {
    private long startTime;
    private long endTime;
    public Timer() { super();
                     startTime = (endTime = 0); }
    public void start() { startTime = (endTime = System.nanoTime()); }
    public void end() { endTime = System.nanoTime(); }
    public long nanos() { return endTime - startTime; }
    public double seconds() { return (endTime - startTime) * 1.0E-9; }
    public static String toString(long nanos) { Timer t = new Timer();
                                                t.endTime = nanos;
                                                return t.toString(); }
    public static String toString(double seconds) { Timer t = new Timer();
                                                    t.endTime = (long) (seconds *
                                                                          1.0E9);
                                                    return t.toString(
                                                               );
    }
    public String toString() { long millis = nanos() / (1000 * 1000);
                               if (millis < 10000) return String.
                                                     format(
                                                       "%dms",
                                                       millis);
                               long hours = millis / (60 * 60 * 1000);
                               millis -= hours * 60 * 60 * 1000;
                               long minutes = millis / (60 * 1000);
                               millis -= minutes * 60 * 1000;
                               long seconds = millis / 1000;
                               millis -= seconds * 1000;
                               return String.format("%d:%02d:%02d.%1d",
                                                    hours,
                                                    minutes,
                                                    seconds,
                                                    millis /
                                                      100); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAK0YbWwcxXVubd/ZjrEvDnGCGxzHcSKc0NumKpVSI2hysonD" +
                                                    "QUzsRPQoOOPdOXvj/WJ3zr44dSGpUCKkphV1INDUlWgQHw0EVURQVUj50wKC" +
                                                    "P1QVqD/4UP8UleZHfpSi0pa+N7N3e7d3PpOolmY8O/Pem/f93tz5y6TJ98h2" +
                                                    "1zGPTJkOT7ECTx02b0nxIy7zU3szt4xSz2d62qS+Pw57E1rfyx2fffHT6aRC" +
                                                    "4lmyhtq2wyk3HNvfz3zHnGV6hnSEu0Mms3xOkpnDdJaqeW6Yasbw+WCGrCpD" +
                                                    "5aQ/U2RBBRZUYEEVLKi7QihAuo7ZeSuNGNTm/oPkhySWIXFXQ/Y42VRJxKUe" +
                                                    "tQIyo0ICoNCM3wdBKIFc8EhvSXYpc5XAp7eri088kPxNA+nIkg7DHkN2NGCC" +
                                                    "wyVZ0mYxa5J5/i5dZ3qWrLYZ08eYZ1DTmBd8Z0mnb0zZlOc9VlISbuZd5ok7" +
                                                    "Q821aSibl9e445XEyxnM1ItfTTmTToGsXaGsUsJh3AcBWw1gzMtRjRVRGmcM" +
                                                    "W+dkYxSjJGP/nQAAqAmL8WmndFWjTWGDdErbmdSeUse4Z9hTANrk5OEWTrqX" +
                                                    "JYq6dqk2Q6fYBCfro3Cj8gigWoQiEIWTtVEwQQms1B2xUpl9Lt9966mj9h5b" +
                                                    "ETzrTDOR/2ZA6okg7Wc55jFbYxKxbVvmcdr1+kmFEABeGwGWMK/+4Mp3b+65" +
                                                    "9KaE+VoNmH2Th5nGJ7Rzk+3vbkgP7GxANppdxzfQ+BWSC/cfDU4GCy5EXleJ" +
                                                    "Ih6mioeX9v/hew+/wD5VSOsIiWuOmbfAj1ZrjuUaJvPuYDbzKGf6CGlhtp4W" +
                                                    "5yMkAeuMYTO5uy+X8xkfIY2m2Io74htUlAMSqKIErA075xTXLuXTYl1wCSEJ" +
                                                    "GKQNRgORf+I/Jxl12rGYSjVqG7ajgu8y6mnTKtMc1aeWa4LV/LydM5051fc0" +
                                                    "1fGmwu8jPmeWOm5YzEuhV7n/Z3oF5D85F4uBajdEA9uEmNjjmDrzJrTF/O6h" +
                                                    "Ky9NvK2UHD2QHAwGN6SCG1LyhpS4gcRigvD1eJO0F2h7BuIWMlrbwNj9ew+d" +
                                                    "7AMtFdy5RlAVgvaBFMH1Q5qTDoN7RKQwDTxs/dP3nUh9/uzt0sPU5TNxTWxy" +
                                                    "6czcsYMPfUMhSmVKRXFgqxXRRzERlhJefzSUatHtOPHJZxceX3DCoKrI0UGs" +
                                                    "V2NirPZFFe85GtMh+4Xkt/XSixOvL/QrpBESACQ9TsFJIZ/0RO+oiNnBYv5D" +
                                                    "WZpA4JzjWdTEo2LSauXTnjMX7giPaBfr1WCUVejESRjxwKvFfzxd4+J8vfQg" +
                                                    "tHJECpFfh3976cmLT23fqZSn4o6y4jbGuAzs1aGTjHuMwf4HZ0Z/dvryifuE" +
                                                    "hwDE5loX9OOchjAHk4FaH3nzwT9/9OG5PymhV3God/lJ09AKQGNreAskARMS" +
                                                    "Edq+/4BtObqRM+ikydA5/92xZcfFv59KSmuasFN0hptXJhDu37CbPPz2A//s" +
                                                    "EWRiGhahUPIQTCpgTUh5l+fRI8hH4dgfb3zyDfoLyJGQl3xjnolUQ4RkRKhe" +
                                                    "FabaJuZU5GwHTr1u1VlB7KwXXwpcPbB8EA1jLS0Lvn/tMyeP/+VzIVFV+NQo" +
                                                    "IRH8rHr+bHf6tk8FfujHiL2xUJ2CoO8Icb/5gvUPpS/+e4UksiSpBU3NQWrm" +
                                                    "0VuyUMj9YqcDjU/FeWVRlhVosBSnG6IxVHZtNILC1AdrhMZ1ayRosAqQfhiN" +
                                                    "QdA0RoMmRsRip0DpE/MWnG4q+mzC9YxZih0T1HtOPY4Ztb6pRj3DgmI4G1Rr" +
                                                    "daHzo5mzn7wo82TULhFgdnLx0S9TpxaVsv5nc1ULUo4jeyAh+HVS8C/hLwbj" +
                                                    "vzhQYNyQNbAzHRTi3lIldl2Mx0312BJXDP/1wsLvnls4IcXorCz/Q9Ddvvje" +
                                                    "f95Jnfn4rRqVqdF07CmRn2QMfOvqLTSC0yAXrQKaAD93S5JC9v6yUCIo0Y3L" +
                                                    "NV5CmnPHF5f0fc/sUIIIHQbzcsf9uslmmVlGKoGUKmrhXaLVDKPh0ed//Sp/" +
                                                    "d/t3pF62Le8WUcQ3jv+te/y26UNXUQE3RmSKknz+rvNv3bFVe0whDaWgquqe" +
                                                    "K5EGK0Op1WPQ7tvjFQHVU1mF1sFoDczVWrMKhQapnQ/vrXOWxekAh+cdRpsA" +
                                                    "uR2ntEyWQ+BNs46hVydTsXFPJatrYbQHrLZfE6tanTOhokOcNIBP4vL7KzKV" +
                                                    "LDLVGTDVeU1MzdQ5s3CaBv3Z1HYExO4V2RJc3ACjK2Cr65rY8uuc5XGCVJDw" +
                                                    "IZps3a9l2LjuQIPAVmQXOSWbYHQH7HbXZLd2TldwOQCX+eI5XIjwHJOJX+hN" +
                                                    "kDlWR6gf4XSUw9vckQ9MAbWWk6ToIbDIpeTBV5epN5Cp96plwumhOvLMCRI/" +
                                                    "riPPT3A6WSYPfj/y1dwHbbA14H3rNbnP6TpnT+D02MqcQZluEm8e7OTWV/1C" +
                                                    "Il/12ktLHc3rlg68L7r40su7BZ6/ubxplncWZeu467GcIbhpkX2GrDxnoaxW" +
                                                    "v7zQwcRCsPhzCfpLTlaVgWI0yFU50NOQUAAIl79yaziU7JoKpKLgudHyt7mi" +
                                                    "EIlflIpFIy9/U5rQLiztvfvolW8/IypQk2bS+Xmk0pwhCfk+KRWeTctSK9KK" +
                                                    "7xn4ov3lli3FgtqOU2eZ05bxtrF27z5kuVx02/OvrXvl1meXPhSPh/8B6lJz" +
                                                    "xuoTAAA=");
}
