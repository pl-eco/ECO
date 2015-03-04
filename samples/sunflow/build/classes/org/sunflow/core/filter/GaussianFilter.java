package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class GaussianFilter implements Filter {
    private float s;
    private float es2;
    public GaussianFilter(float size) { super();
                                        s = size;
                                        es2 = (float) -Math.exp(-s * s); }
    public float getSize() { return s; }
    public float get(float x, float y) { float gx = (float) Math.exp(-x *
                                                                       x) +
                                           es2;
                                         float gy = (float) Math.exp(-y *
                                                                       y) +
                                           es2;
                                         return gx * gy; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWxURRSevW23tNR2Wf4KQoFaiOVnr0LUYA2KmxaKq21o" +
                                                    "xbj+rNO7s91L75/3zrZLtf6QmBIeiNGqaLQPBqMgAjESNYakLwpEXzRG44No" +
                                                    "fJGoPPDgT/w/M7N3789uq764yczOnTlnzjlzzvnmzPFLqM6x0QbL1PYNayZN" +
                                                    "kCJN7NWuS9B9FnESu1LX9WPbIdmkhh1nEOYySvuplp9+ezIfk1A0jRZiwzAp" +
                                                    "pqppOLuJY2qjJJtCLd5st0Z0h6JYai8exXKBqpqcUh3alULzfawUdaRcFWRQ" +
                                                    "QQYVZK6CvN2jAqYriFHQk4wDG9R5ED2CIikUtRSmHkVrgptY2MZ6aZt+bgHs" +
                                                    "MI997wGjOHPRRqvLtgubKwx+ZoM89dz9sTdrUEsatajGAFNHASUoCEmjJp3o" +
                                                    "Q8R2tmezJJtGCwxCsgPEVrGmjnO90yjuqMMGpgWblA+JTRYsYnOZ3sk1Kcw2" +
                                                    "u6BQ0y6bl1OJlnW/6nIaHgZbl3i2Cgt72DwY2KiCYnYOK8RlqR1RjSxFq8Ic" +
                                                    "ZRs7bgMCYK3XCc2bZVG1BoYJFBe+07AxLA9QWzWGgbTOLIAUipbPuik7awsr" +
                                                    "I3iYZChqDdP1iyWgauAHwVgoWhwm4zuBl5aHvOTzz6U7bjr0kLHTkLjOWaJo" +
                                                    "TP95wNQWYtpNcsQmhkIEY9P61LN4yZkDEkJAvDhELGjefvjyLRvbZs4Jmiur" +
                                                    "0PQN7SUKzShHhpo/XpHs3FrD1JhnmY7KnB+wnId/f2mlq2hB5i0p78gWE+7i" +
                                                    "zO4P7n7sGPleQo29KKqYWkGHOFqgmLqlasTeQQxiY0qyvaiBGNkkX+9F9TBO" +
                                                    "qQYRs325nENoL6rV+FTU5N9wRDnYgh1RPYxVI2e6YwvTPB8XLYRQPTS0CVod" +
                                                    "Ej/+T5Eu502dyFjBhmqYMsQuwbaSl4liZmximXJ3sk8eglPO69gecWSnYOQ0" +
                                                    "cyyjFBxq6rJjK7JpD7vTsmLaRAaNIJrkHbjgOCo2evhngoWd9X8LLLITiI1F" +
                                                    "IuCcFWFo0CCrdppaltgZZapwa/flE5kPpXKqlM6OonUgL1GSl2DyEkJeIigP" +
                                                    "RSJczCImV/gfvDcCOAAI2dQ5cN+uBw6010DgWWO1cPSMtB2MLinTrZhJDyx6" +
                                                    "OSQqELGtL98zmfjl1ZtFxMqzI3tVbjRzeOzxPY9eIyEpCNHMOJhqZOz9DFjL" +
                                                    "ANoRTs1q+7ZMXvzp5LMTppekAcwvYUclJ8v99rAbbFMhWUBTb/v1q/HpzJmJ" +
                                                    "DgnVAqAAiFIMQQ/41BaWEcCALhdPmS11YHDOtHWssSUXBBtp3jbHvBkeH818" +
                                                    "vACcMp8lxTJoDaUs4f9sdaHF+kUinpiXQ1ZwvO55d+b50y9s2Cr5ob3Fd1kO" +
                                                    "ECqAYoEXJIM2ITD/5eH+p5+5NHkPjxCguKqagA7WJwE2wGVwrE+ce/CLry4c" +
                                                    "+VTyoorC/VkY0lSlCHus86QAqGgAbMz3HXcauplVcyoe0ggLzt9b1l57+odD" +
                                                    "MeFNDWbcYNj4zxt488tuRY99eP/PbXybiMIuNc9yj0wcwEJv5+22jfcxPYqP" +
                                                    "f7Ly+bP4JcBcwDlHHSccuiLlfOmco7CxVR2wdrR0GcgT8a9GXrz4hkib8M0R" +
                                                    "IiYHpg7+lTg0Jfmu16sqbjg/j7hieTBcIYLnL/hFoP3JGgsaNiEgNp4s4fzq" +
                                                    "MtBbFnPPmrnU4iJ6vj058d5rE5PCjHjwdumG4umNz/74KHH46/NVYAviz8SU" +
                                                    "6yhzHdfzPsGU4ieK+FoX61ZbFWtFPtPKv6S5z76HVTQ+yPq1Txva/80vXKcK" +
                                                    "0KnijhB/Wj7+4vLktu85v5f9jHtVsRLGofrzeDcf03+U2qPvS6g+jWJKqbTc" +
                                                    "g7UCy7E0lFOOW29C+RlYD5ZGog7oKqPbinA8+MSGccfzA4wZNRs3hqCmiZ3y" +
                                                    "ImjREtREw1ATQXyQ5CztvF/LuqvdTK+3bHUUs7oVsoOtbOHoJHy5LShpiVsD" +
                                                    "uP9VJPWyrpuiGuJs9m3HFe/whYMQtpiipRU3o7gKWXCvnK3E44F9ZP/UdLbv" +
                                                    "lWulUhTeQFEDNa1NGhklWijyVgZuydt5Uet5/ODR19+mH2+4UaTI+tmjNMx4" +
                                                    "dv93ywe35R/4D3fjqpBN4S2P3n78/I51ylMSqikHTkWdHmTqCoZLo03gYWEM" +
                                                    "BoKmrexK1lArtOaSK5ur3k+ew6rnfHqOtXtZdxcE1zChAwDBPBAq4YFPDAQV" +
                                                    "Y9EcLykW/9eKScLR5YDbwkmH5lAxyzp4hdSAirOoBynRHCzP2KXTWvE4FA8a" +
                                                    "5cR0y7yl03d+zguO8qOjASr/XEHT/OnsG0ctm+RUrlGDSG6RKmq1xBAlI2XP" +
                                                    "PzbgCucFPQRJLExPUS3785OZFM33kYGHSiM/EUioASI2dCw3TWP8rmWwlhCw" +
                                                    "VkS+FGPlhv8rUHuwLOIPbzfiC+LpnVFOTu+646HL17/C06cOnuzj4/yhBu9O" +
                                                    "UXaVs2bNrLu5e0V3dv7WfKphrYsGzayLl2qtkG6rqpck3bpFeREx/s7St256" +
                                                    "dfoCr4n+Bq1HeT0REQAA");
}
