package org.sunflow.system;
final public class Memory {
    final public static String sizeof(int[] array) { return Memory.bytesToString(
                                                                     array ==
                                                                       null
                                                                       ? 0
                                                                       : 4 *
                                                                       array.
                                                                         length);
    }
    final public static String bytesToString(long bytes) {
        if (bytes <
              1024)
            return String.
              format(
                "%db",
                bytes);
        if (bytes <
              1024 *
              1024)
            return String.
              format(
                "%dKb",
                bytes +
                  512 >>>
                  10);
        return String.
          format(
            "%dMb",
            bytes +
              512 *
              1024 >>>
              20);
    }
    public Memory() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1170024466000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAK0YfYwU1f3tx30fvQ/gOBA57gAROHaFggGOBM/jgIU9OPbg" +
                                                   "0EO6vJt5uzcwOzPO\nvL2bO4jVknBU2ipRE5sqYkPCR7WS2IY2sRSitlbSRE" +
                                                   "2qiYm0DUnbpLWpaWJp2j/6e+/N7nzs7lmb\nbrJvZ977fX+/feVTVGWZaKFk" +
                                                   "xeikQaxY39AgNi0i96nYsvbBVlp6u6pu8MIuTQ+jUBKFFZmipqRk\nxWVMcV" +
                                                   "yR44mtPbaJVhm6OplVdRojNo0dUdc79HYm15cQPHD2ausT56MdYVSVRE1Y03" +
                                                   "SKqaJr/SrJ\nWRQ1J4/gcRzPU0WNJxWL9iTRLKLlc326ZlGsUetR9BiKJFG1" +
                                                   "ITGaFHUmC8zjwDxuYBPn4px9fJCz\nBQqzTUKxohG5t8gOMLv9mCC2g5cqhQ" +
                                                   "YitexwGNThEoDWi4taC21LVDUiF4fvP37uUgQ1jaAmRRti\nxCTQhAK/EdSY" +
                                                   "I7lRYlq9skzkEdSiESIPEVPBqjLFuY6gVkvJapjmTWKliKWr4wyw1cobxOQ8" +
                                                   "C5tJ\n1Cgxncy8RHWzaKOMQlS58FaVUXEW1G5z1RbqbmP7oGC9AoKZGSyRAk" +
                                                   "r0qKKBxzuCGEUdl+4CAECt\nyRE6phdZRTUMG6hV+FLFWjY+RE1FywJolZ4H" +
                                                   "LhQtqEiU2drA0lGcJWmK2oNwg+IIoOq4IRgKRXOD\nYJwSeGlBwEse/6xq+/" +
                                                   "zUxReuPcBjOyoTSWXy1wPSogBSimSISTSJCMQ7+diziYfzC8MIAfDcALCA\n" +
                                                   "6V12dX/yTz/vEDB3lYHZM3qESDQt7T7TkTq2XUcRJkatoVsKc75Pc54Og85J" +
                                                   "j21A1rYVKbLDWOHw\neuoXDz9+mfw5jOoTqFrS1XwO4qhF0nOGohJzO9GIiS" +
                                                   "mRE6iOaHIfP0+gGnhOQsiL3T2ZjEVoAkVV\nvlWt83cwUQZIMBPVwbOiZfTC" +
                                                   "s4HpGH+2DYTQLPiiVvhGkPjwX8i3WNzKaxlVn4hbphTXzaz7PmlR\nkosPkJ" +
                                                   "xuTsZY1BgU7YiP6TkSxxLWFE2PZxXIU0lfLZNx9vslaNlMttaJUIgVu2DSqh" +
                                                   "DvO3RVJmZa\nunD73eP9u755SgQEC2JHK4rmA4uYwyImWMQECxQKccpzGCvh" +
                                                   "DDDlUUhKKF+NK4YO7Tx8qgtMYBsT\nUbADA+0C+R3+/ZLe52Zughc5CcKn/f" +
                                                   "sHp2N3LmwR4ROvXGDLYje89+rNc39vXBlG4fLVj+kF9bee\nkRlkJbNY1ZYG" +
                                                   "86Uc/b8+OfD6hzc/udfNHIqWliR0KSZLyK6gB0xdIjKUOJf8+flNkQNo+EwY" +
                                                   "RSHL\nobJx+aFoLAry8CVmT6HIMV1qkqgho5s5rLKjQmWqp2OmPuHu8NBoZs" +
                                                   "scESXMkQEBeX28c6L6vo/e\naHiba1wopU2eZjVEqEjMFjcO9pmEwP4nzw8+" +
                                                   "89yn0wd5EDhRQKGD5UdVRbIB5R4XBTJSharAfLR0\nv5bTZSWj4FGVsGD6d9" +
                                                   "OyNT/+y3eahdVV2Ck4rfuLCbj78x9Ej9/82j8WcTIhiXUEVw0XTGgz26Xc\n" +
                                                   "a5p4kslhP/HB3d/9JX4RChYUCUuZIjzvEdcMcTvGuHlX8HV14Ow+tnQB7e4K" +
                                                   "UV2m/6al45ezXflH\nf/VTLnUD9jZyrxsGsNEjnMp5zwamC5Cz+OoRO51rsL" +
                                                   "WNuWBeMHt3YGsMiK27vvuRZvX6v4DtCLCV\noDlae0yoFrbP0w50Vc3HN95s" +
                                                   "O/x+BIW3oXpVx/I2zOMf1UHgEWsMCo1tbHmAi9E8UctWbhfEpV3g\nWMn2vf" +
                                                   "GXJXy9R0RPmEIIKhrm/ereALSJ7q7U8ni7nn7os8aT+K1DorK0+ttIP4xaf5" +
                                                   "x8kyzf/O3f\nl6mCdVQ3VqtknKgenmHG0lfRBvg04Obzk5d+cJW+v2qTYLmy" +
                                                   "cjELIq7cdG6qY9Nrp/+HOtYRMEKQ\ndMv4XXsjY8o7YT6wiBJWMuj4kXq85g" +
                                                   "CmIE/e1Jhh2U4jD7vFxbBrZ+5YC9+oE3bRYNiJglPi3Qh7\n3sxdC1XC4kOj" +
                                                   "HcinULGXzPFM7zxDmTyXnt46O7Xx4AlutzoYKLG12xUcxnj2FAIrLavsiyKx" +
                                                   "tLT8\n0NW/3bhGlvMkqFUsmIJ7zWyZ4cqD8xm+TAY+ypzlNTw6ii1hpOBUWj" +
                                                   "p0+mZJbqOvGCyq273XFFPJ\nwbgzzsv+7ZNdP3tn/0vTIrpWzHAX8WKlpa//" +
                                                   "9ndHI0/dGBV4wZEvAHxm0fk/vH47NUckhZiLl5SM\npl4cMRtzBZq4Ap0zce" +
                                                   "DQb63qfOWx1C0uEcPbQlFEce48fq/PoOU2Zl43ztOj3ReT7+55kQteMYXK\n" +
                                                   "GCBAZ+ra/rN3fk1vcTpuT2bYnXbpXAUh4uJu+HC8pfrKS7kwqhlBzZJzlRvG" +
                                                   "ap61xxEIAqtwv4Pr\nnu/cf4sQI3NPMWEXBj3gYRucBrypG6W+pG027BDiSX" +
                                                   "m4TLF109GGfFSJlhXT7jBb9hp20T1hpxyy\n93nUaZ1MbqjAukZYFy6ciWFR" +
                                                   "0WPFCx8c2iWOZu99Bpdxb6lkIU/5L99qtRnOuL5HoJNITDihC0RW\nR/lRoj" +
                                                   "9nUN78p34y70ebL5y9xWLUsGGsF+Vl49q16wG7FZKU/TcQU+RYUpewmtj68o" +
                                                   "2GD87k798p\n0myWByCx9fiVnY21L58+yYuwU5bqPJcg571mHJu73T7EfihF" +
                                                   "g/+v+8Gmdd1f3bB63UawBWYFjJtn\nYAbTHWPLdlacYfbRMwWnNrsOF9ddfr" +
                                                   "CGLTuEl9Z/YesXLvc3kQ3wrXWaSO2XbyJs2VWpgbD3lBPD\nByhMlbqW5TSn" +
                                                   "Z9D/W2z5BkWzRicpsfbpHm1nNsOJ/9YMLNOEh1hMtpf80yL+HZCSHx975PPk" +
                                                   "b/4p\nmkzhBt8AEZTJq6o34T3P1YZJMgpXpKGQ/uznOag1pbc85mX+wOV7Vo" +
                                                   "A+T1GDB5SiGufJC/Q9qN4A\nxB5fMMoYRxQz//TGtF3iq+38D63CDJMXf2ml" +
                                                   "pYdePbjYPr3vad7gIYPx1BQjUw+5Ii49xTmosyK1\nAq330JXXht/44cZC1/" +
                                                   "Fdh0pic404rexHdmAZ/wFjyXG3XBQAAA==");
}
