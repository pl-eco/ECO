package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class CatmullRomFilter implements Filter {
    public float getSize() { return 4.0F; }
    public float get(float x, float y) { return catrom1d(x) * catrom1d(y);
    }
    private float catrom1d(float x) { x = Math.abs(x);
                                      float x2 = x * x;
                                      float x3 = x * x2;
                                      if (x >= 2) return 0;
                                      if (x < 1) return 3 * x3 - 5 * x2 +
                                                   2;
                                      return -x3 + 5 * x2 - 8 * x + 4; }
    public CatmullRomFilter() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XW2xURRie3d5LaUu5VYTSQkHKZQ/EYILgBdYWCgs0LWAs" +
                                                    "kWV6dnZ72nPOHObMtkuxCiQGwgMxWhCM9sFAEOUWI0FjSPokEHzBGI0Pgm8S" +
                                                    "lQde0AQV/5nZ3bN7ekFf3OTMnvPPP/99vvnn3D1U5DK0xKHm3oRJeYikeKjH" +
                                                    "XBniex3ihjZGVrZh5pJY2MSuuw1oUX3epaoHD9/qrg6i4k40Fds25Zgb1Hbb" +
                                                    "iUvNPhKLoCqP2mwSy+WoOtKD+7CW5IapRQyXr46gSTlLOWqMZEzQwAQNTNCk" +
                                                    "CdpajwsWTSZ20gqLFdjm7h70OgpEULGjC/M4asgX4mCGrbSYNukBSCgV3zvA" +
                                                    "Kbk4xVB91nfl8yiHjy3Rht7dVf1pAarqRFWG3SHM0cEIDko6UYVFrC7C3LWx" +
                                                    "GIl1oik2IbEOwgxsGgPS7k5U4xoJG/MkI9kgCWLSIUzq9CJXoQvfWFLnlGXd" +
                                                    "ixvEjGW+iuImToCvMzxflYctgg4OlhtgGItjnWSWFPYadoyjuf4VWR8bNwED" +
                                                    "LC2xCO+mWVWFNgYCqlG5M7Gd0Do4M+wEsBbRJGjhaNa4QkWsHaz34gSJclTr" +
                                                    "52tTU8BVJgMhlnA03c8mJUGWZvmylJOfe1vWHN1nb7CD0uYY0U1hfyksqvMt" +
                                                    "aidxwoitE7WwYnHkOJ5x9XAQIWCe7mNWPFdeu//i0rqR64rnyTF4tnb1EJ1H" +
                                                    "9VNdlbdmh5tWFQgzSh3qGiL5eZ7L8m9Lz6xOObDzZmQlislQZnKk/atX9n9M" +
                                                    "fg2i8lZUrFMzaUEdTdGp5RgmYeuJTRjmJNaKyogdC8v5VlQC7xHDJoq6NR53" +
                                                    "CW9FhaYkFVP5DSGKgwgRohJ4N+w4zbw7mHfL95SDECqBB2nwFCH1k/8cUa2b" +
                                                    "WkTDOrYNm2pQuwQzvVsjOo0y4lCtObxV64Iod1uY9bqam7TjJu2P6kmXU0tz" +
                                                    "ma5RlsiQNZ0yooFFUE1aGHMraZrt1GqRhJAoPOf/V5kSUajuDwQgQbP98GDC" +
                                                    "ztpAzRhhUX0oua75/oXozWB2u6Tjx9Ei0BhKawwJjSGlMeTXiAIBqWia0Kyq" +
                                                    "AHLYC2gAOFnR1PHqxt2H5xVA+Tn9hZAAwToPHE+b06zTsAcZrRIYdajb2g93" +
                                                    "Hgr9ceYFVbfa+Pg+5mo0cqL/wI43lgdRMB+ohXtAKhfL2wS8ZmG00b9Bx5Jb" +
                                                    "dejug4vHB6m3VfOQP40go1cKBJjnTwSjOokBpnriF9fjy9Grg41BVAiwAlDK" +
                                                    "MZQ+oFSdX0ceEqzOoKrwpQgcjlNmYVNMZaCwnHcz2u9RZIVUiqFGFYtIoM9A" +
                                                    "CcgtX4ycvPzeklXBXOyuyjkNOwhXSDDFy/82RgjQfzzR9s6xe4d2yuQDx/yx" +
                                                    "FDSKMQy4ANmAiL15fc8Pd26f+jboFQyHAzLZZRp6CmQs9LQAapiAXCKtjdtt" +
                                                    "i8aMuIG7TCLq7s+qBSsu/3a0WiXKBEomz0sfL8CjP7EO7b+56/c6KSagi1PL" +
                                                    "89xjUwGY6kleyxjeK+xIHfhmzslr+AMAVQAy1xggEpuQ9AzJ0IdkRprkuMw3" +
                                                    "t1wM9c6ouZSk1Ka/5EeDHBvF8JSKm3hdlMsZkO/TOZo5am+rrSwCPGe8g0oe" +
                                                    "sqcODg3Htp5eobZlTT74N0Nvc/67v74OnfjpxhiIUsaps8wkfcTMsalAqMyD" +
                                                    "g83yDPc2xZGzn1zht5Y8q1QuHh8J/AuvHfxl1rbnu3f/BxCY63PeL/Ls5nM3" +
                                                    "1i/U3w6iguz+H9WW5C9anRsGUMoI9FG2CKiglMs010kDpkA4xINq4SlOH1ry" +
                                                    "X8xOdcQ4Te1WMTw9Qd28NMFcixjWclSSILwDChIS0DRB+8wMC070vnTLoQ3W" +
                                                    "3Ol9/+55lQx/f+JjJoeHjjwKHR0K5jRx80f1UblrVCMnzZysYvIIfgF4/haP" +
                                                    "8EEQ1EFeE053E/XZdsJxRAk3TGSWVNHy88XBLz8aPBRMx2QVF9hGMR+91STh" +
                                                    "ufwETYOnPJ2g8n+doKCUGBSfm+QgWbdPkKqXxdDOUQGkSq54rHk1gjgLnsq0" +
                                                    "eZVjmjcOYkBROMzog0imfFYFPEhRZu+ewOwuMXRyVKpjzqi1IjaO7Sm4V/m7" +
                                                    "CQGktaNuNKoL1y8MV5XOHN7+vTwfs51yGbSrcZCRs8tyd1yxw0jckIaVqWPP" +
                                                    "kX+JsXBQ9Thc3FnEizQ5rvh7wFo/P0eF4i+XzeJoUg4bhDT9lssEaFgATOJ1" +
                                                    "j5NB5Wp5fohrSkj15CmUB/NOPujnnqdi28rbYga3kuq+GNUvDm/csu/+M6cl" +
                                                    "CBbBPXNgQN4u4LKkuoQs9jWMKy0jq3hD08PKS2ULMrsmr3/w2TZ37GO22XK4" +
                                                    "PBgHPp/52Zozw7flOf8P17lF+MYPAAA=");
}
