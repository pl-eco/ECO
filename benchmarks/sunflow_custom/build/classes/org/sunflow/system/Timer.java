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
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYXWwcxXnubN/ZjrEvDnGCGxzHcaI6obdNVSqlRtDkZBOH" +
                                                    "A7uxE7VHy2W8O+fbeP/YnbMvTl1IKpQIqWlFHQgQjARBFBoIqhrRqkLKSwuI" +
                                                    "vlBVoD7wo74UleYhD6WotKXfN7N3e7d3PpNItTTj2Znvd77fuQtXSIvnkp2O" +
                                                    "bRydMWyeZEWePGLcmuRHHeYl96dvnaCux7SUQT1vCvay6sArXZ989tN8Ikpi" +
                                                    "GbKOWpbNKddtyzvAPNuYY1qadAW7IwYzPU4S6SN0jioFrhtKWvf4cJqsqUDl" +
                                                    "ZDBdEkEBERQQQREiKHsCKEC6gVkFM4UY1OLe/eSHJJImMUdF8TjZUk3EoS41" +
                                                    "fTITQgOg0Irfh0ApgVx0SX9Zd6lzjcJndipLj92X+GUT6cqQLt2aRHFUEIID" +
                                                    "kwzpMJk5zVxvj6YxLUPWWoxpk8zVqaEvCLkzpNvTZyzKCy4rXxJuFhzmCp7B" +
                                                    "zXWoqJtbULntltXL6czQSl8tOYPOgK49ga5Sw1HcBwXbdRDMzVGVlVCaZ3VL" +
                                                    "42RzGKOs4+BdAACocZPxvF1m1WxR2CDd0nYGtWaUSe7q1gyAttgF4MJJ74pE" +
                                                    "8a4dqs7SGZblZGMYbkIeAVSbuAhE4WR9GExQAiv1hqxUYZ8r99x2+pi1z4oK" +
                                                    "mTWmGih/KyD1hZAOsBxzmaUyidixI/0o7XntVJQQAF4fApYwr/7g6rdu6bv8" +
                                                    "hoT5Uh2Y8ekjTOVZ9fx059ubUkO7m1CMVsf2dDR+lebC/Sf8k+GiA5HXU6aI" +
                                                    "h8nS4eUDv//ugy+yj6OkfYzEVNsomOBHa1XbdHSDuXcyi7mUM22MtDFLS4nz" +
                                                    "MRKHdVq3mNwdz+U8xsdIsyG2Yrb4hivKAQm8ojisdStnl9YO5XmxLjqEkDgM" +
                                                    "0gGjicg/8Z+TrJK3TaZQlVq6ZSvgu4y6al5hqp11mWMrI6lxZRpuOW9Sd9ZT" +
                                                    "vIKVM+z5rFrwuG0qnqsqtjtT2la8ox5npjKlm8xNoqM5/38WRdQyMR+JgAE2" +
                                                    "hcPfgMjZZxsac7PqUmHvyNWXs29Fy+Hg3w+YFTgkfQ5JySEpOJBIRBC+ETlJ" +
                                                    "q4JNZiG6Ie91DE1+f//hUwNwl0VnvhkuFEEHQDGf/Yhqp4IUMCYSnQp+uPGZ" +
                                                    "e08mP33+DumHysr5ui42uXx2/vihB74aJdHqxIvqwFY7ok9guiynxcFwwNWj" +
                                                    "23Xyo08uPrpoB6FXlcn9jFCLiRE9EL5411aZBjkyIL+jn17KvrY4GCXNkCYg" +
                                                    "NXIKrgxZpy/Moyqyh0tZEnVpAYVztmtSA49Kqa2d5117PtgRHtEp1mvBKGvQ" +
                                                    "1RMwYr7vi/94us7B+UbpQWjlkBYiC4/+5vLjl57YuTtambC7KkrgJOMy/NcG" +
                                                    "TjLlMgb7752d+NmZKyfvFR4CEFvrMRjEOQXJAEwG1/rQG/f/+YP3z/8pGngV" +
                                                    "h6pYmDZ0tQg0tgdcIFUYkK7Q9oMHLdPW9JxOpw2Gzvnvrm27Lv39dEJa04Cd" +
                                                    "kjPcsjqBYP+mveTBt+77Z58gE1GxVAWaB2DyAtYFlPe4Lj2KchSP//Hmx1+n" +
                                                    "T0Emhezl6QtMJCQiNCPi6hVhqh1iTobOduHU79ScFcXORvEVBdZDKwfRKFbc" +
                                                    "iuD717gxfeIvnwqNasKnTqEJ4WeUC+d6U7d/LPADP0bszcXaFATdSYD7tRfN" +
                                                    "f0QHYr+LkniGJFS/9TlEjQJ6SwbKvVfqh6A9qjqvLt2yTg2X43RTOIYq2IYj" +
                                                    "KEh9sEZoXLeHggZrBRmE0ewHTXM4aCJELHYLlAExb8PpyyWfjTuuPkexr4Ku" +
                                                    "gFOXY0ZtbKoJVzehZM75NV1Z7P5g9txHL8k8GbZLCJidWnr48+TppWhFl7S1" +
                                                    "plGpxJGdklD8Bqn45/AXgfFfHKgwbshK2Z3yy3V/uV47DsbjlkZiCRajf724" +
                                                    "+NufL56UanRXNwkj0AO/9M5//pA8++GbdSpTs2FbMyI/yRj4+rVbaAynYS4a" +
                                                    "CjQBfu6VJIXugxWhRFCjm1dqz4Q2508sLWvjz+2K+hE6CubltvMVg80xo4JU" +
                                                    "HClV1cK7RUMaRMPDL/ziVf72zm/Ke9mxsluEEV8/8bfeqdvzh6+hAm4O6RQm" +
                                                    "+cLdF968c7v6SJQ0lYOqpseuRhquDqV2l8GjwJqqCqi+6iq0AUa7b672ulUo" +
                                                    "MEj9fPidBmcZnA5yeARitAmQO3BKyWQ5At40Z+tabTIVG9+uFnU9jE5f1M7r" +
                                                    "ElVtcCau6DAnTeCTuPzeqkIlSkJ1+0J1X5dQsw3OTJzycH8WtWwBsXdVsYQU" +
                                                    "N8Ho8cXquS6xvAZnBZwgFcQ9iCZL8+oZNqbZ0CCwVcVFSckWGL2+uL11xa2f" +
                                                    "06O4HAJmnng0F0MyR2TiF/cmyBxvoNSPcDrG4QVvy2eogFrPSUL0EFjkkvLg" +
                                                    "i+vU7+vUf8064fRAA33mBYkfN9DnJzidqtAHvx/6Yu6DNtjuy779utznTIOz" +
                                                    "x3B6ZHXJoEy3iDcPdnIba35HkW9/9eXlrtYNywffFV18+X3eBo/kXMEwKjuL" +
                                                    "inXMcVlOF9K0yT5DVp5zUFZrX17oYGIhRHxSgj7NyZoKUIwGuaoEegYSCgDh" +
                                                    "8lmnjkPJrqlIqgqeEy5/W6sKkfjdqVQ0CvKXp6x6cXn/PceufuM5UYFaVIMu" +
                                                    "LCCV1jSJy/dJufBsWZFaiVZs39Bnna+0bSsV1E6cuiuctkK2zfV79xHT4aLb" +
                                                    "Xvj1hl/d9vzy++Lx8D/8Z1wPEBQAAA==");
}
