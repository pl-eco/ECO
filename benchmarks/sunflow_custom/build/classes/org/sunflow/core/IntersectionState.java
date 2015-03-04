package org.sunflow.core;
public final class IntersectionState {
    private static final int MAX_STACK_SIZE = 64;
    float u;
    float v;
    Instance instance;
    int id;
    private final StackNode[] stack;
    private final float[] rstack;
    Instance current;
    public static final class StackNode {
        public int node;
        public float near;
        public float far;
        public StackNode() { super(); }
        public static final String jlc$CompilerVersion$jl7 = "2.6.1";
        public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0XXWwURXju+l8K1xb6Q4XSlkMt4C1oMGLxp5wttB60oUDi" +
                                                        "IRzT3bl26d7usjtHj2IVSEyJD8TEgmCwDwaCIfzFSNQYkr6oGHzRGI0PgvFF" +
                                                        "EuSBB5GIit/M7N3e7V3hzUt2dm7m+//fs7dQiW2hZaah7R3SDBoiKRrapa0K" +
                                                        "0b0msUO9kVX92LKJEtawbW+Gs5jcdjFw597bw9V+VBpFc7GuGxRT1dDtTcQ2" +
                                                        "tD1EiaCAe9qlkYRNUXVkF96DpSRVNSmi2rQjgmZloVIUjKRFkEAECUSQuAhS" +
                                                        "pwsFSLOJnkyEGQbWqb0bvY58EVRqykw8ilpziZjYwgmHTD/XACiUs/9bQSmO" +
                                                        "nLJQS0Z3oXOewkeWSZPv7qj+qAgFoiig6gNMHBmEoMAkiqoSJDFILLtTUYgS" +
                                                        "RTU6IcoAsVSsqWNc7iiqtdUhHdOkRTJGYodJk1icp2u5KpnpZiVlalgZ9eIq" +
                                                        "0ZT0v5K4hodA13pXV6FhNzsHBStVEMyKY5mkUYpHVF2haJEXI6Nj8GUAANSy" +
                                                        "BKHDRoZVsY7hANUK32lYH5IGqKXqQwBaYiSBC0VNMxJltjaxPIKHSIyiRi9c" +
                                                        "v7gCqApuCIZCUZ0XjFMCLzV5vJTln1sb1xzep6/X/Vxmhcgak78ckJo9SJtI" +
                                                        "nFhEl4lArFoaOYrrLx/yIwTAdR5gAfPJa7dfXN48fUXAPFIApm9wF5FpTD45" +
                                                        "OOfbBeH21UVMjHLTsFXm/BzNefj3OzcdKRMyrz5DkV2G0pfTm758Zf8ZctOP" +
                                                        "KntQqWxoyQTEUY1sJExVI9Y6ohMLU6L0oAqiK2F+34PKYB9RdSJO++Jxm9Ae" +
                                                        "VKzxo1KD/wcTxYEEM1EZ7FU9bqT3JqbDfJ8yEUKN8KCF8NQh8eNvilRp2EgQ" +
                                                        "CctYV3VDgtgl2JKHJSIbMYuYhtQV7pMGwcrDCWyN2JKd1OOaMRqTkzY1EpJt" +
                                                        "yZJhDaWPJdmwiNTDQtYGM4LqLL1IiIWc+X8ySzHNq0d9PnDKAm9J0CCb1hua" +
                                                        "QqyYPJlc23X7fOyqP5Mijs0oWg68Qg6vEOMVyuMVhFUe2WgoBPl8nNk8xl14" +
                                                        "H3w3AlUA6mNV+8D23p2H2oog7MzRYjA8A20DtR2RumQj7JaKHl4QZYjXxg+2" +
                                                        "TYTunn5BxKs0c10viI2mj40e2PrGCj/y5xZopiIcVTL0flZWM+Uz6E3MQnQD" +
                                                        "EzfuXDg6brgpmlPxncqRj8kyv83rDMuQiQK11CW/tAVfil0eD/pRMZQTKKEU" +
                                                        "Q8hDdWr28sipAB3pasp0KQGF44aVwBq7SpfASjpsGaPuCY+SOWypFQHDHOgR" +
                                                        "kBfi7s+mj196b9lqf3bNDmR1wQFCRQWocf2/2SIEzn8+1v/OkVsT27jzAWJx" +
                                                        "IQZBtoahHoA3wGJvXtn90/VrJ7/3uwFDoTEmBzVVTgGNR10uUC00EZF2cIue" +
                                                        "MBQ1ruJBjbC4+zuwZOWl3w9XC0dpcJL28/KHE3DP569F+6/u+LOZk/HJrFu5" +
                                                        "mrtgwgBzXcqdloX3MjlSB75bePwr/D4UUyhgtjpGeE1CXDPETR/iHmnn6xOe" +
                                                        "uxVsaTHz7lL8pJH/KwLW7TPnRzdrull59VefNnjw17tco7zMKNBrPPhR6eyJ" +
                                                        "pvDzNzm+G6IMe1Eqv+LAgOLiPnkm8Ye/rfQLPyqLomrZmX62Yi3JoiUKHd9O" +
                                                        "j0QwIeXc53Zv0ao6Mim4wJseWWy9yeFWOtgzaLavFPnAYWrApgHkNI56p2fw" +
                                                        "N7uda7J1XsqH+OYZjtLK1yBbHhMxy7aPpyhwgwr5YAf1W2oCeuUep5lL47XX" +
                                                        "R07cOCcKn9cbHmByaPKt+6HDk/6s8Whx3oSSjSNGJK7ubKHuffj54PmXPUxN" +
                                                        "diBaZG3Y6dMtmUZtmiwLWx8kFmfR/duF8c8/HJ8QatTmTgddMPye++Gfb0LH" +
                                                        "fvm6QPspgsmPFyUR+E/luqUJngbHLQ0zuGUdW55l5ifOhPwcWzpF3oQpCwkD" +
                                                        "P4DJ/PTgkH4XYLLBYVIUxzz+ewW5VOGQKOIhAbXM5mM3E0HVsZadyYiZduFM" +
                                                        "AyI368mDk1NK36mVfqdAvAQEnbk9mw5FFZn+zC/qKGp7eFMH9o15XxFi8pXP" +
                                                        "TwXKG6a2/Mh7U2Y6rYARMZ7UtOykytqXmhaJq1zSCpFiJn9thy8przjgLfbi" +
                                                        "4r4qwHZSNCsLjKIyZ5cNBKYsAiC2Vcy0stW8FrNiERLFIoVyDGR6zb44J0H5" +
                                                        "F5djgQ1J8c0Vky9M9W7cd/vpU3yqKIFvtbExPqHDB4fouJlhonVGamlapevb" +
                                                        "7825WLEk7cicXuyRbVHhltWVMClvMmOfNny85vTUNd4z/wMkmmGOCg8AAA==");
    }
    public IntersectionState() { super();
                                 stack = (new StackNode[MAX_STACK_SIZE *
                                                          2]);
                                 for (int i =
                                        0;
                                      i <
                                        stack.
                                          length;
                                      i++)
                                     stack[i] =
                                       new StackNode(
                                         );
                                 rstack =
                                   (new float[53 *
                                                256]);
    }
    public final StackNode[] getStack() {
        return stack;
    }
    public final int getStackTop() { return current ==
                                       null
                                       ? 0
                                       : MAX_STACK_SIZE;
    }
    public final float[] getRobustStack() {
        return rstack;
    }
    public final boolean hit() { return instance !=
                                   null; }
    public final void setIntersection(int id,
                                      float u,
                                      float v) {
        instance =
          current;
        this.
          id =
          id;
        this.
          u =
          u;
        this.
          v =
          v;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "OLVk+VFrlsFP/AfwdK6CZxgAAA==");
}
