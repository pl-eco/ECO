package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class SincFilter implements Filter {
    private float s;
    public SincFilter(float size) { super();
                                    s = size; }
    public float getSize() { return s; }
    public float get(float x, float y) { return sinc1d(x) * sinc1d(y); }
    private float sinc1d(float x) { x = Math.abs(x);
                                    if (x < 1.0E-4F) return 1.0F;
                                    x *= Math.PI;
                                    return (float) Math.sin(x) / x; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXXWwUVRS+O223P1S6lAK1YoFSiC26Ixo1WKPippXiIg2t" +
                                                    "NdSfcjt7dzswOzPM3G2XYv0hMSU+EKNV0WgfDARBBGIkaIwJL4pGXzRG44Ni" +
                                                    "fJGIPPAgEv/PufO3O7ut+OAmM3vn3vN7zznfPffoBVJlW2SNaWi7MprB4yzP" +
                                                    "49u1W+J8l8ns+MbkLX3UslkqoVHbHoC5YaXtRMOl358djUkkOkQWUl03OOWq" +
                                                    "odtbmG1oYyyVJA3BbLfGsjYnseR2OkblHFc1OanavCtJ5hWwctKe9EyQwQQZ" +
                                                    "TJCFCfL6gAqYrmJ6LptADqpzeyd5nESSJGoqaB4nK4qFmNSiWVdMn/AAJNTg" +
                                                    "9yA4JZjzFlnu++74XOLwC2vk6Zcejb1dQRqGSIOq96M5ChjBQckQqc+y7Aiz" +
                                                    "7PWpFEsNkQU6Y6l+ZqlUUyeE3UOk0VYzOuU5i/mbhJM5k1lCZ7Bz9Qr6ZuUU" +
                                                    "bli+e2mVaSnvqyqt0Qz4ujjw1fGwB+fBwToVDLPSVGEeS+UOVU9xsizM4fvY" +
                                                    "fh8QAGt1lvFRw1dVqVOYII1O7DSqZ+R+bql6BkirjBxo4aRlVqG41yZVdtAM" +
                                                    "G+akOUzX5ywBVa3YCGThZFGYTEiCKLWEolQQnwv337Fvt75Bl4TNKaZoaH8N" +
                                                    "MLWGmLawNLOYrjCHsb4z+SJd/MFeiRAgXhQidmhOPXbx7utbT3/s0FxThmbz" +
                                                    "yHam8GHlwMj8z5cmOtZVoBk1pmGrGPwiz0X697krXXkTKm+xLxEX497i6S0f" +
                                                    "bX3yCDsvkbpeElUMLZeFPFqgGFlT1Zh1L9OZRTlL9ZJapqcSYr2XVMM4qerM" +
                                                    "md2cTtuM95JKTUxFDfENW5QGEbhF1TBW9bThjU3KR8U4bxJCquEhHfBUEecn" +
                                                    "/jkZlEeNLJOpQnVVN2TIXUYtZVRmiiHbNGtqEDU7p6c1Y1y2LUU2rIz/rRgW" +
                                                    "k0E9pI7cr+pKjxjGMb/M/01yHn2KjUcisN1Lw8WuQZ1sMLQUs4aV6dw93ReP" +
                                                    "DX8q+cnv7gYnbaAr7uqKo664oyse6CKRiFDRhDqdaEIsdkBVA97Vd/Q/snHb" +
                                                    "3rYKSCNzvBI2EknbwDPXkG7FSASl3ysAToH8a379oan45UN3Ofknz47TZbnJ" +
                                                    "6f3jTw0+caNEpGLARcdgqg7Z+xAmfThsDxdaObkNU+cuHX9x0ghKrgjBXSQo" +
                                                    "5cRKbguHwDIUlgJsDMR3Lqcnhz+YbJdIJcADQCKnkMKANq1hHUUV3eWhI/pS" +
                                                    "BQ6nDStLNVzyIK2Oj1rGeDAjcmO+GC+AoMzDFF8ET42b8+IfVxea+G5ycgmj" +
                                                    "HPJCoG/Pe6dfPvnKmnVSIVA3FBx9/Yw7Zb8gSJIBizGY/3Z/3/MvXJh6SGQI" +
                                                    "UKwsp6Ad3wkAAQgZbOvTH+/85ux3B76UgqzicBrmRjRVyYOM1YEWgAgNYApj" +
                                                    "3/6AnjVSalqlIxrD5PyjYdXakz/viznR1GDGS4br/11AMH/1PeTJTx/9tVWI" +
                                                    "iSh4RAWeB2TOBiwMJK+3LLoL7cg/9cW1L5+hrwGCAmrZ6gQTQBTx66VjjjbF" +
                                                    "UrOAnGMutMuTjWd3vHruLadswudAiJjtnX7m7/i+aangsFxZcl4V8jgHpkiG" +
                                                    "q5zk+Rt+EXj+wgeTBiccwGxMuKi93Idt08TwrJjLLKGi58fjk++/MTnluNFY" +
                                                    "fFZ0Qyv01ld/fhbf//0nZSAL8s+gXNgoCxs7xTuORokdJWKtC1/LzZK1vJhp" +
                                                    "vpK978H+pACyftusjez54bKwqQR0yoQjxD8kH321JXHnecEfVD9yL8uXQjj0" +
                                                    "cgHvTUeyv0ht0Q8lUj1EYorbKA5SLYc1NgTNke11j9BMFq0XNzrOqd7lo9vS" +
                                                    "cD4UqA3jThAHGCM1jutCUFOPu9wET9SFmmgYaiJEDBKCpU28V+HrOq/Sq01L" +
                                                    "HaPYhUKEcOVmgU6m0NReFD8cL+JkSckx5pxdmI3XztZhiUw8sGd6JrX54FrJ" +
                                                    "TZvbOKnlhnmDxsaYVqCqAiUVHWubRE8ZhOiZw2+e4p+vud3J6c7Z0yrMeGbP" +
                                                    "Ty0Dd45u+w+H2bKQT2GRhzcd/eTe1cpzEqnwI13SJhczdRXHt85i0NfrA0VR" +
                                                    "bvWjjA9p9sLt/ZccKEHAyhfpg3OsbcXXAGRDhvF+wEyRCKX1LCb6ig3D9Iu5" +
                                                    "hsWu2DBJSJT8hLtZkA7PYSLF18OcVICJV2Zeo7dvTa55TbOa1x1S7B4XgWWZ" +
                                                    "OSxT8QVwH7Whj1ubmsU4KLC6oNHD46u55NLoXHSUYzMNNUtmHvhatC7+ZaQW" +
                                                    "bgTpnKYVAkPBOGpaLK0Kg2odmHBqWC9XsU7jyfFaiANhbNah3wkX7TA9J5X4" +
                                                    "V0jGOZlXQAap444KiWC6AohwmDc9/IiJUxsBMu4AZJ4U1L5H6H0VdTFY3uJC" +
                                                    "7pVizrmSDyvHZzbev/virQdFXVfBVX5iQlzg4D7qNHB+Oa+YVZonK7qh4/f5" +
                                                    "J2pXeTA1H1+NbtcWsm1Z+eamO2ty0Y5MvLvknTsOzXwnuqt/ABLKeS8pEQAA");
}
