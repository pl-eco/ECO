package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class PrimIDShader implements Shader {
    final private static Color[] BORDERS = { Color.RED, Color.GREEN, Color.
                                                                       BLUE,
    Color.
      YELLOW,
    Color.
      CYAN,
    Color.
      MAGENTA };
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { Vector3 n = state.getNormal();
                                                   float f = n == null ? 1.0F
                                                     : Math.
                                                     abs(
                                                       state.
                                                         getRay().
                                                         dot(
                                                           n));
                                                   return BORDERS[state.getPrimitiveID() %
                                                                    BORDERS.
                                                                      length].
                                                     copy().
                                                     mul(
                                                       f); }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public PrimIDShader() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1163966490000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVYfWwUxxWfu/MHPhtsDBhDwAZjQgBzh1vcBBwFjD8Sw4Hd" +
                                                   "OyBgoM54d+68sLe7\n2Z0zh4NookiYBLUJaqsmUUNoRctH84GUVqRqSqFJ2j" +
                                                   "SoUhKpiRQptBVSW6lN1ahSStX+0Tczu7cf\nd+cgqlry3OzOm/fmff3em33x" +
                                                   "E1RpmWiRZMXoYYNYsd7UMDYtIveq2LJ2wKtR6a3KmuGzWzU9jEIJ\nFFZkiu" +
                                                   "oTkhWXMcVxRY4P9nXnTbTa0NXDGVWnMZKnsQNql81vS6KriOGDpy41Pnamoj" +
                                                   "WMKhOoHmua\nTjFVdK1fJVmLoobEATyB4zmqqPGEYtHuBJpJtFy2V9csijVq" +
                                                   "PYyOokgCVRkS40nR0oQjPA7C4wY2\ncTbOxceHuVjgMMckFCsakXsK4mBnh3" +
                                                   "8nHNvelyymBiYz2OIuUIefALReUtBaaFukqhE5t+tLR06f\nj6D6EVSvaCnG" +
                                                   "TAJNKMgbQXVZkh0jptUjy0QeQbM1QuQUMRWsKpNc6ghqtJSMhmnOJFaSWLo6" +
                                                   "wQgb\nrZxBTC7TeZlAdRLTycxJVDcLNkorRJWdp8q0ijOgdpOrtlB3gL0HBa" +
                                                   "MKHMxMY4k4WyoOKhp4vDW4\no6Bj+1YggK3VWULH9YKoCg3DC9QofKliLRNP" +
                                                   "UVPRMkBaqedACkULyzJltjawdBBnyChFzUG6YbEE\nVDXcEGwLRfOCZJwTeG" +
                                                   "lhwEse/6xu+uz4ue9c3sRju0ImksrOH4VNLYFNSZImJtEkIjbezMW+Obgn\n" +
                                                   "tyiMEBDPCxALmp7ll3Ym/vzzVkFzRwmaobEDRKKj0vaTrclH7tdRhB1jhqFb" +
                                                   "CnO+T3OeDsP2Snfe\ngKxtKnBkizFn8Uryl3sevUD+EkbRQVQl6WouC3E0W9" +
                                                   "KzhqIS836iERNTIg+iGqLJvXx9EFXDPAEh\nL94OpdMWoYOoQuWvqnT+DCZK" +
                                                   "AwtmohqYK1pad+YGpuN8njcQQtXwj1bDfxSJP/5L0d2xuJXT0qp+\nKG6ZUl" +
                                                   "w3M4VnSTdJ3BrHMjHjw6aSHexL8YcYCyCDolR8XM+SOJawpmh6PKNAykr6Gp" +
                                                   "lMsN/bY5tn\nJ248FAoxCAymsgpZ8ICuAu2odPbGO0f6tz5xXIQJC21bV4ra" +
                                                   "QVrMlhZj0mJCWswrDYVCXMhcJlV4\nC2x9ELIW8K1uZWr/loeOt0UgTIxDFW" +
                                                   "AoRtoGWtlH6Zf0Xje1BzkKShBfzd/bOxW7eXajiK94eQQu\nubv23Zeunf5H" +
                                                   "3aowCpeGR6YiAHSUsRlmmFqAvfZgQpXi/7cnt736wbWP73JTC4xVlPHFO1nG" +
                                                   "tgWd\nYeoSkQEDXfZnFtRHHkS7ToZRBcAAQB8/P6BKS1CGL3O7HRRkulQnUG" +
                                                   "1aN7NYZUsOdEXpuKkfct/w\nKGlgw1wRMMyRgQNyAL35eNXaD1+vfYtr7GBt" +
                                                   "vaeapQgVmTvbjYMdJiHw/uNnhr/xrU+m9vIgsKOA\nQonLjamKlIctd7pbIG" +
                                                   "VVgA3mo/adWlaXlbSCx1TCguk/9cs7f/zXrzcIq6vwxnFax+czcN8v2Iwe\n" +
                                                   "vfaVf7ZwNiGJlQxXDZdMaDPH5dxjmvgwO0f+sfcXP/sr/DwgGqCIpUwSDgyI" +
                                                   "a4a4HWPcvCv5uCaw\ntpYNbcC7o0xUlyjQo9KRC5m23MO//gk/dS32VnqvG7" +
                                                   "Zho1s4lcueA0I7kD34AIutzjPY2MRcMD+Y\nvQ9gaxyYrbuyfV+DeuXfIHYE" +
                                                   "xEpQPa0hE9I+7/O0TV1Z/dHVN5oeei+CwgMoqupYHsA8/lENBB6x\nxgFz8s" +
                                                   "bGTfwYDYdmsJHbBfHTLrStlPc8MbxYWT79B1gP4GbO6FjHucQ7Q89zK5VN/B" +
                                                   "IlMMBn8vLO\nUzd/Q69zPm4Gst1L88WACn2Tu/eeDyZmV118IRtG1SOoQbI7" +
                                                   "u11YzbFkGIFGxHLaPej+fOv+pkJU\n0O4CwiwKZr9HbDD3XSCHOaNm87pAZC" +
                                                   "xg1t4M/3V2ZNQGIyOE+GQj37KMj3eKDI5QEKhoGM5VbZjK\nBFRdyGmL94B5" +
                                                   "eLd5KNnXn0wxPPE04DyHGPycf7pvTnL93sc5QtdAT4it7e6JoRNnsxCYenl5" +
                                                   "3xeY\njUor9l/6+9XLZAUP0xmKBZbpMTMl+iPPnk/xBbLtw/QpjrIVY9gSNg" +
                                                   "o2lsV9o68d5Cad5Tdpi2PK\nUiblpPMpmu8trkoWej6GXXZ7yzAjZMc/e97A" +
                                                   "hq2OO4aK3RFm81423AXWr1KJlqHjYPxm7+0HqjZ0\nURO8WNw41vazt3e+MC" +
                                                   "UK7DQZ5ts1Kn31d78/GHnq6pjYF0yjAPHJljN/fPVGcq5AbNFuLyvqeL17\n" +
                                                   "RMvNjVpvMGBaOp0ETv3m6qUvHk1et0/U6G8c++Fy9afDb5AV937tDyU6nAg4" +
                                                   "lz1sMfIFu4eFQR0/\niQrAEhKaa10jrJg4a6LnUfRY4WIDi/kiD5posa/j2c" +
                                                   "bjx0WMJ8//8BJ9b/UGocGq8r4Ibly14fRk\n64ZXTtxGn9MasGuQ9eyJO74c" +
                                                   "GVfeDvMbjwCgopuSf1O3H3aicJ6cqe3wgc8SEcjc5sVRHBIBPE3x\nPDDNGr" +
                                                   "/fpAGXJOYn4VawfWvp5qA/a1Bezidfm/+je8+eus6sb+Shk49yOOrs6lz/Rd" +
                                                   "jeCBnEvgfE\nFNmuEn3vD4xdSGsXZG6bKEeLHrbFVryGv/HAWUQ3LHbh8XxZ" +
                                                   "sDm1DxkWawpneoQM9h25uKVuxndP\nHOP8bSys8Vye7OfqCWxu90ZzrTj4uv" +
                                                   "VdXXdTtO//cKvY8IXOjnX3rOnqoui+W7kcxOwysMJaQrGZ\nIXSJ84Yd2nDr" +
                                                   "xFG48hUbmpnHxlHUyJNjlpuNLLW9i8CtItnf0yf6WDZ2smGTCKeuUl0HD5uM" +
                                                   "r9so\nhc5cO6EQg6TF5e7cHI6mdn9adwy/uT9sR+UeCs2PbqxRyQRRPaIifD" +
                                                   "5WqBy8UHTD/yxbp1nBysEV\nc0+8shxitRSdnac+gTLG4t0ha/KSpcRvz/Ag" +
                                                   "F/PENGn2FBuOQZHJGeAugYUpGz93g0fHdF0lWHM9\nMDWNB3x9XymLrBXedb" +
                                                   "x8ixYJ+b25uKQ3FS3DvlkRzua5aTQ+xYZvQ35BACdhHwO7WyrjXP1n/hf1\n" +
                                                   "74P/Zlv95tsNiOnV/zxNuJhz05jnZTZ8n6KZloQpBNnwuE7t2uOJi4oJXZFd" +
                                                   "q/zgVq0C7UydF1IY\npDcXfZsU39OkxEeP7Pss8dt/iZ7O+eZVC9iZzqmqty" +
                                                   "f2zKsMk6QVrkqt6JAN/vNaKRgQIMdaXT7h\nR70k6H9KUUOQHvRmP16yyxBI" +
                                                   "HjLIGHvmJboKvQkQsekvDMdDDS74ibtB3mcpZpllvuaBfy52CnxO\nfDAelX" +
                                                   "a/tHdJ/sSOp3nXAMUST04yNlGoKOKLQaFJWFqWm8PrXXTxlV2vv7zeQTvft4" +
                                                   "Si4O4Uq+U9\nzxZ047+TLM31uhcAAA==");
}
