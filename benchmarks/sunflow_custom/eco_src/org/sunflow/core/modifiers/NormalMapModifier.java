package org.sunflow.core.modifiers;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Modifier;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.math.OrthoNormalBasis;
public class NormalMapModifier implements Modifier {
    private Texture normalMap;
    public NormalMapModifier() { super();
                                 normalMap = null; }
    public boolean update(ParameterList pl, SunflowAPI api) { String filename =
                                                                pl.
                                                                getString(
                                                                  "texture",
                                                                  null);
                                                              if (filename !=
                                                                    null)
                                                                  normalMap =
                                                                    TextureCache.
                                                                      getTexture(
                                                                        api.
                                                                          resolveTextureFilename(
                                                                            filename),
                                                                        true);
                                                              return normalMap !=
                                                                null;
    }
    public void modify(ShadingState state) {
        state.
          getNormal().
          set(
            normalMap.
              getNormal(
                state.
                  getUV().
                  x,
                state.
                  getUV().
                  y,
                state.
                  getBasis()));
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                state.
                  getNormal()));
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169407166000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVYfWwcRxUf3/kj/gB/JHGcNLFjx22pk97SiCCIKyXGOK2b" +
       "c+L6q6nT1hnvzp03\n3tvd7s6ez07UD1CT0AraCJBAomlURUr6RSuVKkUqJV" +
       "VbKI2QWiSKVKkBFAmQoEgIqQTBH7x5s3t7\nu3dnKrC0u3Mz72PevPd+742f" +
       "/5jUuQ7ZrLopvmwzNzU8OU4dl2nDBnXdKZiaU9+uaxw/v9+0EqQm\nTRK6xk" +
       "lrWnUVjXKq6Joy+tXBgkO225axnDUsnmIFnjpq7PLl3ZHeVSbwrjMXOx4+V9" +
       "uTIHVp0kpN\n0+KU65Y5YrCcy0lb+ijNU8XjuqGkdZcPpslnmOnlhi3T5dTk" +
       "7v3kAZJMk3pbFTI56U0HyhVQrtjU\noTkF1SvjqBYkrHUYp7rJtKGiOuDcEe" +
       "WEbft8E+XUIGSNWJwBc3AHYPXWotXS2jJT7eSFmS8eP/tM\nkrTOklbdnBTC" +
       "VLCEg75Z0pJjuXnmuEOaxrRZ0m4ypk0yR6eGvoJaZ0mHq2dNyj2HuRPMtYy8" +
       "IOxw\nPZs5qDOYTJMWVdjkeCq3nOIZZXRmaMGvuoxBs2B2Z2i2NHefmAcDm3" +
       "TYmJOhKgtYahd1EzzeE+co\n2ti/HwiAtSHH+IJVVFVrUpggHdKXBjWzyiR3" +
       "dDMLpHWWB1o42VRVqDhrm6qLNMvmOOmK043LJaBq\nxIMQLJysj5OhJPDSpp" +
       "iXSvyzvfOTUxd+8PpejO1ajamG2H8TMHXHmCZYhjnMVJlkvOalvjN6t7c5\n" +
       "QQgQr48RS5qh6y9Op//00x5Jc10FmoPzR5nK59QDp3smjt1mkaTYxhrbcnXh" +
       "/IjlmA7j/spgwYas\n7SxKFIupYPHSxM/ufuhZ9ucEaRol9apleDmIo3bVyt" +
       "m6wZzbmMkcypk2ShqZqQ3j+ihpgHEaQl7O\nHsxkXMZHSa2BU/UW/oYjyoAI" +
       "cUSNMNbNjBWMbcoXcFywCSEN8JCd8DQT+YdfTvakFNczM4a1pLiO\nqlhOtv" +
       "hbtRym5CxNh4h1XOWA5eSoMUbtMX8qJQLJ5uSwsmDlmEJVauqmpWR1SF3Vul" +
       "ljefH9/8QX\nhAUdSzU1AhLjqW1AVtxuGRpz5tTzV989PrL/G6dk2IhQ920H" +
       "SAGtKV9rSmhNFbWmyrSSmhpUtk5o\nl14EHyxCNgPutdw0ee8dR071JSF87K" +
       "VaOEBB2gdW+lsaUa3hMOVHER1ViLuupw+fTF07v0fGnVId\nmStyN7/3wuWz" +
       "f28ZSJBEZdgUpgJwNwkx4wJri3DYH0+0SvL/+ujYyx9c/uhzYcpx0l+GBOWc" +
       "IpP7\n4k5xLJVpgI2h+HMbW5N3kZnTCVIL8ACQiPsHtOmO64hk9GCAjsKWhj" +
       "RpzqCvxFIAaU18wbGWwhmM\nljYcrw1CfDM8n/VjHr9idb0t3p0yuoS3Y1Yg" +
       "+l77ev3nf/Na89t4LAFQt5aUwknGZdq3h8Ey5TAG\n8x99b/zb3/345GGMFD" +
       "9UONRHb97Q1QKw3BCyQL4bgDnCkf3TpoxNOm8wEXH/br3+llf+8q026RoD\n" +
       "ZgLP7vjvAsL5jV8hD12+7x/dKKZGFfUmNCMkk9asDSUPOQ5dFvsoPPyrLd//" +
       "OX0S4BAgyNVXGKIK\nQcsInqOC5z6A71Rs7Rbx6gPZO6qEfoXqPqcefzbb59" +
       "3/ix/jrptpaZtQ6gbI3kHpefHaJk53Qzx7\nb6fuAtB94dKBe9qMS/8CibMg" +
       "UYWq6h50AEAKESf61HUNH77xZueR95MksY80GRbV9lGMf9IIgcfc\nBcCegr" +
       "1nL8ZW29Ia8UaTCR7CJv8ACiW/BF7cVD3994neIMycufkdF9LvHnwSD6Bq4l" +
       "cojTE5K69P\nn7n2S34F5YQZKLh7C+XACv1UyPulD/Lt9S89lUuQhlnSpvod" +
       "3ww1PBHns9CguEEbCF1hZD3abMjK\nOlhEmM3x7C9RG8/9ENBhLKjFuCWW7i" +
       "3itLuDQfAtTfcagoM9yNKP7xuLydlgO3qeii6QNJpBWUCa\nDVD2y2rIFBAC" +
       "zEkEEe+d4rVXOntXpaDA7d4QCQZf+sYy6UE5EsG8pVqzhI3eyUN/azlB37pX" +
       "lpaO\naAMyAk36H5ffZDfe+s3fV6iMjdyybzZYnhklu0oIlZGSNoZ9ZBgSjz" +
       "7z3EX+/vbdUuVA9XCOMw7s\nPrvSs/vFx/6HQtYTO4S46Pb8dXcmF/R3Etjq" +
       "yggra5GjTIPRuGqC/XiOORWJrq3RYjIIT7sfXe0V\ni0no4xAJE/65+t7uLv" +
       "M2msqgAxdQG5B1lpJNyu/Q+CiqObQK1t4nXtNQbDwbLoQMvNlVepd09Bz0\n" +
       "pHkssVdP9P3knemnTkpHroJLEa459cHf/m4x+fgb85IvDj4x4tPd5/7w8tWJ" +
       "dTL+5OVlW9n9oZRH\nXmDQmFZbZEDvahqQ+q3tvc8/MHEFdyT4xiCf5y3LYN" +
       "QMk3NmleSMIDb+uDPq+gF4unzXd31q19dE\nE31LmesnF6gGdzBxC2UoxlzF" +
       "tYjYi+BarPHLSHPEltsFtK3NW7oWmmt8WnMB8drLGmHRCXSVXabl\nBVBNf3" +
       "jsnk/Sv/4ntnTFS1oz3JQynmGUgnXJuN52WEZHQ5oldNv4OQ73zuotOqBUcY" +
       "y7Pia5HuSk\nLc4FRyA+pWRf46S5hAzCwh+VEj3CSRKIxPBEEfLbsB8QpSsl" +
       "S1chcmjifLZFMgb/yxHAkyf/zzGn\nHnrh8NbCY1NPIObVqQZdWcELLdzPZU" +
       "NbhLjeqtICWe+Rl16cee2HXw6iHBuedYWwBkUCeKdcXSUI\nAFYrd5EjOZtj" +
       "37fy6oYf3Xr+zJUE9rH/ARmq+X+cEgAA");
}
