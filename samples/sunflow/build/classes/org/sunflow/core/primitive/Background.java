package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
public class Background implements PrimitiveList {
    public Background() { super(); }
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public void prepareShadingState(ShadingState state) { if (state.getDepth(
                                                                      ) ==
                                                                0) state.
                                                                     setShader(
                                                                       state.
                                                                         getInstance(
                                                                           ).
                                                                         getShader(
                                                                           0));
    }
    public int getNumPrimitives() { return 1; }
    public float getPrimitiveBound(int primID, int i) { return 0;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { return null;
    }
    public void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        if (r.
              getMax(
                ) ==
              Float.
                POSITIVE_INFINITY)
            state.
              setIntersection(
                0,
                0,
                0);
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYW2wc1fXu+u04XttxEpPGTmwcpDjpDpGatjRRS2w5xGGT" +
                                                    "mDihYNqY65m760lm5g4zd+2NU5eHWjlCIkLFoQkqVtUG8SYIEVGEkPLFQ/SH" +
                                                    "ClH1o9C/orb5yA+tRFt6zp3nzq7XAakr3Tsz557nPY977r50jTS4Dtlhc+N0" +
                                                    "weAiy0oie9LYnRWnbeZmD+Z2j1PHZdqIQV33GMCm1IFXM59/8fhMR5o0TpJ1" +
                                                    "1LK4oELnlnuUudyYZVqOZCLoqMFMV5CO3Ek6S5Wi0A0lp7tiT46siZEKMpgL" +
                                                    "VFBABQVUUKQKyr4IC4jWMqtojiAFtYT7APkpSeVIo62ieoL0lzOxqUNNn824" +
                                                    "tAA4NOP33WCUJC45ZGtou2dzhcHndyhLvzzR8VodyUySjG5NoDoqKCFAyCRp" +
                                                    "M5k5zRx3n6YxbZJ0WoxpE8zRqaHPS70nSZerFywqig4LNwmBRZs5Uma0c20q" +
                                                    "2uYUVcGd0Ly8zgwt+GrIG7QAtm6IbPUs3I9wMLBVB8WcPFVZQFJ/Src0QbYk" +
                                                    "KUIbB+8EBCBtMpmY4aGoeosCgHR5vjOoVVAmhKNbBUBt4EWQIsimFZniXttU" +
                                                    "PUULbEqQniTeuLcEWC1yI5BEkPVJNMkJvLQp4aWYf64d3nvujHXASkudNaYa" +
                                                    "qH8zEPUliI6yPHOYpTKPsG0o9yTd8PbZNCGAvD6B7OG88ZPrt+/su/qeh/ON" +
                                                    "KjhHpk8yVUypl6bbP9w8sv22OlSj2eaujs4vs1yG/7i/sqdkQ+ZtCDniYjZY" +
                                                    "vHr0nXsfeoH9PU1ax0ijyo2iCXHUqXLT1g3m3MEs5lDBtDHSwixtRK6PkSZ4" +
                                                    "z+kW86BH8nmXiTFSb0hQI5ffsEV5YIFb1ATvupXnwbtNxYx8L9mEkCYY5FYY" +
                                                    "bcT7yacg9yoz3GQKVamlW1yB2GXUUWcUpnLFpaZtgNfcopU3+JziOqrCnUL4" +
                                                    "rXKHKbajm2DkLFOGIQYKDi9aWhZDzP5/Mi+hZR1zqRRs+uZkyhuQLQe4oTFn" +
                                                    "Sl0qDo9ef2Xqg3SYAv6eCLINxGV9cVkUlw3FZSNxJJWSUrpRrOdWcMopSG8o" +
                                                    "fG3bJ3588P6zA3UQT/ZcPewoog6Afb4uoyofiWrAmKx0KgRiz2/uW8z+69kf" +
                                                    "eIGorFywq1KTqxfmHr77wVvTJF1eedE2ALUi+TjWy7AuDiYzrhrfzOJnn19+" +
                                                    "coFHuVdWyv2SUEmJKT2Q9ILDVaZBkYzYD22lV6beXhhMk3qoE1AbBYVYhrLT" +
                                                    "l5RRltp7gjKJtjSAwXnumNTApaC2tYoZh89FEBke7fK9E5yyBmN9PYy1fvDL" +
                                                    "J66us3Hu9sIJvZywQpbh/W9evXjlqR23peMVOxM7AyeY8PK/MwqSYw5jAP/z" +
                                                    "hfEnzl9bvE9GCGDcXE3AIM4jUA3AZbCtP3/vgT99+smlj9JRVAk4FovThq6W" +
                                                    "gMctkRSoFQbUK/T94HHL5Jqe1+m0wTA4/53ZtuvKP851eN40ABIEw87VGUTw" +
                                                    "m4bJQx+c+GefZJNS8ayKLI/QvA1YF3He5zj0NOpRevgPvRffpU9DKYXy5erz" +
                                                    "TFYkIi0jcusV6aohOWcTa7tw2mpXrJUkpCeGORiDpOT7eoitiiwfD7JcKgdK" +
                                                    "9650GsmT9NIjS8vakWd2eanaVV7hR6GBefnj//w+e+Ev71cpMS2C29802Cwz" +
                                                    "Yqo1ociyEnFIHtRRojz6/ItviA93fM8TObRydUgSvvvI3zYd+/7M/V+hMGxJ" +
                                                    "GJ9k+fyhl96/4xb1F2lSF9aEit6jnGhPfBtAqMOgWbJwQxHSKn3VFybnOvTf" +
                                                    "TTA6/OTsqJqckYOjMEnL/UzXcDWayqC1QVcHaBviaBPec9/4mBQzUiMQx3C6" +
                                                    "HTKxaGtwWoMXt9dotIMg8xoPZaHr01O/+uxlz6PJTiaBzM4uPfpl9txSOtbu" +
                                                    "3VzRccVpvJZParnW29gv4ZeC8V8caAICvCO/a8TvO7aGjYdtYx7011JLitj/" +
                                                    "18sLbz23sJj2t+S7gjRNc24walUmqATsLS/C34HR7fu5+4b9nCpP6d4KP0/M" +
                                                    "UA06WezlmWRzTw0//gin4wLEOgwqLosTS4I7cTri6X+XIPWzXNdWNS+DwC1+" +
                                                    "KAchfWPmxZVjNdYKOE3D9avAxOGiGbrHraZ1HVwcVlUaBxmA0esr3ftVcw8/" +
                                                    "dTlJVLuG+g5OpiCdoH6o+zD2WNX0h3OW09Ut2IjAbTD6fQv6v25U9cSjyoTm" +
                                                    "OXuIwv2o9C3J4UwNwx7EqSRIOxj2Q+4YmmdUwHhzBWO5DiE3zEs3ljaHfSMD" +
                                                    "Y2/MwDrJsS7Qo7sibY7S09J1AcZABcYY3j5d74SPsmuxxmY8htPPoMroAWno" +
                                                    "a1w5sarBMm92whjyDR76Won0RI218zg9DhUAHDZMocAWolzCpd1Vug1BWqNL" +
                                                    "AbY6PRX/NHi3Y/WV5UzzxuXjf5RtbniDbYFrZL5oGLGDMX5INkIxyns51OJ1" +
                                                    "r7Z8PAWX8pXvKdBh2GWaX/SonoYikaSCQoaPONqvBVkTQ4Ni7r/FkX4LtQSQ" +
                                                    "8PWSHURKh+zz8E+ErHdjLpGydswu+yrre/GolP/lBA1H0fs3Z0q9vHzw8Jnr" +
                                                    "335Gdi8NqkHn55FLc440eS1/2LT0r8gt4NV4YPsX7a+2bAtOqnacuvw+P6Hb" +
                                                    "lurt8KhpC9nAzv9u4+t7n13+RPbj/wOoRdCVZBMAAA==");
}
