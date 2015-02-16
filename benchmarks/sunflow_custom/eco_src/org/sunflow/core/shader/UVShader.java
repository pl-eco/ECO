package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class UVShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { if (state.getUV() == null)
                                                       return Color.
                                                                BLACK;
                                                   return new Color(state.
                                                                      getUV().
                                                                      x, state.
                                                                           getUV().
                                                                           y,
                                                                    0);
    }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public UVShader() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1165807990000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVXeWwUVRh/3e1BD2gppZSrpQWVcweimEiNUGuRlUXWLlQs" +
                                                   "anmdebs7MDtvmHnT\nLoVwaATUeBA10cSDGBK8NVGDJh4Yb/lHTdSExCskau" +
                                                   "KRGBPF6B9+772ZPWa3BXWTfTPz3ne87/q9\n7z3zM6pybDRHdSJsl0WcSG8i" +
                                                   "jm2HaL0GdpxNMDWkvltVGz++3qQhVBFDIV1jqDGmOoqGGVZ0TYle\n1Z210R" +
                                                   "KLGrtSBmURkmWR7cZKT941sZUlAq9/9ETzgWOVHSFUFUON2DQpw0ynZp9BMg" +
                                                   "5DTbHteAQr\nLtMNJaY7rDuGJhPTzfRS02HYZM5OtBeFY6jaUrlMhjpjvnIF" +
                                                   "lCsWtnFGEeqVuFALEqbZhGHdJFpP\nTh1wLi3mhG17fP2l1CBkEl8cAHPEDs" +
                                                   "DqeTmrpbUlplrhJwYu3XP0yTBqHESNupngwlSwhIG+QdSQ\nIZlhYjs9mka0" +
                                                   "QTTVJERLEFvHhj4mtA6iZkdPmZi5NnH6iUONEU7Y7LgWsYVOfzKGGlRuk+2q" +
                                                   "jNo5\nHyV1Ymj+V1XSwCkwuzVvtjR3LZ8HA+t02JidxCrxWSp36CZEvCPIkb" +
                                                   "NxwXogANaaDGFpmlNVaWKY\nQM0ylgY2U0qC2bqZAtIq6oIWhmaNK5T72sLq" +
                                                   "DpwiQwy1BenicgmoaoUjOAtD04NkQhJEaVYgSgXx\nWdL6++EnHn5jjcjtSo" +
                                                   "2oBt9/HTC1B5j6SZLYxFSJZDzrRu6P3uDOCSEExNMDxJKm54ITm2M/vNkh\n" +
                                                   "aWaXodk4vJ2obEi99khH/+6rKQrzbUyyqKPz4BdZLsoh7q10Zy2o2tacRL4Y" +
                                                   "8RdP9r93w/6nyI8h\nVBdF1So13Azk0VSVZizdIPbVxCQ2ZkSLolpiar1iPY" +
                                                   "pq4D0GKS9nNyaTDmFRVGmIqWoqvsFFSRDB\nXVQL77qZpP67hVlavGcthFAN" +
                                                   "/NFF8K9F8ieeDF0cURzXTBp0VHFsVaF2KvetUpsoThprxFY2DyTE\nS4Qnj8" +
                                                   "XQRiVNM0TBKjZ1kyopHcpVpcs0MsKf/15klu+0ebSigkNfsIQNyP511ADaIf" +
                                                   "X4mY/29K2/\n/bBMD57Sno0MzQNNEU9ThGuKSE0RXxOqqBAKWrhGGSHw7w6o" +
                                                   "VMC0hkWJm67ZdrgrDKlhjVaCczhp\nF1jjbaNPpb35co4K5FMhp9oe33oocv" +
                                                   "b4aplTyvioW5a7/uNnTx39rWFxCIXKQyI3D0C5jouJcxzN\nQd2CYBGVk//L" +
                                                   "HRte/PzUlwvz5cTQgpIqL+XkVdoVDIRNVaIB7uXFH5vZGL4eDRwJoUoofYA7" +
                                                   "sX9A\nkvagjqJq7faRj9tSE0P1SWpnsMGXfLiqY2mbjuZnRIY08aFFJgsPZG" +
                                                   "CDAjTP3lq9/IvX6t8VFvv4\n2lhwgiUIk9U6NZ8Hm2xCYP7LB+P3PfDzoa0i" +
                                                   "CbwsYHCsucOGrmaB5cI8C5SpAVDBY7Rgs5mhmp7U\n8bBBeDL93XjBipd/ur" +
                                                   "tJet2AGT9oS88tID8/80q0/9TNf7QLMRUqPybyZuTJpDXT8pJ7bBvv4vvI\n" +
                                                   "Hvh07kPv40cAxQA5HH2MCDBAwjIk/BgR7l0kxmWBteV86ALZS8fJ6jKH8pC6" +
                                                   "56lUl7vzw1fFrutx\n4eleGIYN2OqWQRW6p4HShcgbikCKr063+NjKQzAjWL" +
                                                   "3rsJMGYZecvPbGJuPkX6B2ENSqcGI6G20o\n+2xRpD3qqprTb73duu2TMAqt" +
                                                   "RXUGxdpaLPIf1ULiEScNeJO1Vq8R22gancRH4RckdjvL81K26Et8\nzBfjhV" +
                                                   "728PeFhVQV4n0GQzNK4EqiFLdx7njnozjbD235teEgfucmiTjNxWdOH/Rl3+" +
                                                   "96m1x0+V3f\nlgHJWkatZQYZIUbBnsJcZRHSbRCtQ77O73jy6RPskyWrpMrF" +
                                                   "44NckHHxqqNjHauev/M/4FtHwAlB\n0VNHZl8XTusfhER3I6GtpCsqZuoudA" +
                                                   "cohf24tskdy2caRDrOy6VjPQ9qN/zrvHSsC6ajACI+rApU\nUUj4NeTHur0k" +
                                                   "1sJUAk0XL1OfrLWQLCGfPfGoULN+gjq9jg/rAKhcC+4ABKLZVnh9sPUMtCEj" +
                                                   "AnnP\nHOx6/YPNjx2SgVw0wR2hkGtI3ff1NzvC97w1LPmCrViA+Ej7se9ePN" +
                                                   "PfIvNP9qvzS1rGQh7Zswpj\nGi1eAZ0TaRDU7yzpfGZv/1diR5xvDUM1w5Qa" +
                                                   "BMukWsGHqCy9lecsZPFxVXHol8N/ihf6Kecd+ori\nMp9btsyh7eYXDyLE4A" +
                                                   "lCKxLzZobqU4T1Ax/P4rIIomeg/+ZninfVEOYP/R/zr4B/i2d+y3/N/InN\n" +
                                                   "P5clQg2dwD0uH6DuJzsqZlBN8TRlHqgkLGnVFoYqR6iu5b2SOV+vZBma5DeP" +
                                                   "/IhtK7lcyguRGju9\n+8bfY5/9Kdqg3KWlHm4OSdcwChCnEH2qLZskdWFGvT" +
                                                   "wILfHYV+5skK0slLh8EdvcK+lvgQt6kB5s\n5o9CstsgiQrIoFq8t0KiwwyF" +
                                                   "gYi/3m750WkSpye/MUbk9aj41OOemV8EJOK+76O2K2/8Q+qWZ7fO\ny9656V" +
                                                   "5xFFSpBh4bE1c7uKnK9i+H/J3jSvNlfYxeeH7gtecu84u/qDEsSewVcnWCqM" +
                                                   "NpU74x68tY\nTLRSY6/MeOny449+FRKt4T8MtXl2phEAAA==");
}
