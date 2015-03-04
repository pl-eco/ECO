package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Color;
public class TexturedAmbientOcclusionShader extends AmbientOcclusionShader {
    private Texture tex;
    public TexturedAmbientOcclusionShader() { super();
                                              tex = null; }
    public boolean update(ParameterList pl, SunflowAPI api) { String filename =
                                                                pl.
                                                                getString(
                                                                  "texture",
                                                                  null);
                                                              if (filename !=
                                                                    null)
                                                                  tex =
                                                                    TextureCache.
                                                                      getTexture(
                                                                        api.
                                                                          resolveTextureFilename(
                                                                            filename),
                                                                        false);
                                                              return tex !=
                                                                null &&
                                                                super.
                                                                update(
                                                                  pl,
                                                                  api);
    }
    public Color getBrightColor(ShadingState state) {
        return tex.
          getPixel(
            state.
              getUV().
              x,
            state.
              getUV().
              y);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1414698367000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAK1Ye2wcxRkf3/kRP6gfSRwTEjt2DDQPbgttUBsjJcZ1wOSC" +
       "XZ9jwAk149m5u03m\ndpfdWftsIh5FIgHUlohWaqUSoipSAoWCRKu0EqVBQE" +
       "uJKkGlUgmJtFWktlJLpaoSTdX+0W9mdm8f\nd+eiqpZudnbne8z3+s03fuEj" +
       "1OQ6aBNxM3zZpm5mLDeFHZfqYwy77gx8midvNbVOnd1vWinUkEUp\nQ+eoM0" +
       "tcTccca4auTXxxpOygHbbFlgvM4hla5pkjbJcv747sriqBd5063/PImcaBFG" +
       "rKok5smhbH\n3LDMcUZLLkdd2SN4EWseN5iWNVw+kkVXUdMrjVmmy7HJ3fvR" +
       "gyidRc02ETI5GswGyjVQrtnYwSVN\nqtempFqQsNahHBsm1Ucr6oBzZ5wTtu" +
       "3zTVdTg5A1YnEWzJE7AKu3VKxW1laZaqfPzd587PRzadQ5\nhzoNMyeEEbCE" +
       "g7451FGipQXquKO6TvU51G1SqueoY2BmrEitc6jHNQom5p5D3WnqWmxREPa4" +
       "nk0d\nqTP4mEUdRNjkeIRbTsVHeYMyPXhryjNcALN7Q7OVufvEdzCwzYCNOX" +
       "lMaMDSeNQwIeIDSY6KjcP7\ngQBYW0qUF62KqkYTwwfUo2LJsFnQctwxzAKQ" +
       "NlkeaOFoY12hwtc2Jkdxgc5z1Jekm1JLQNUqHSFY\nOFqfJJOSIEobE1GKxG" +
       "dH78cnzn3ntb0ytxt1SpjYfxsw9SeYpmmeOtQkVDFe8TLfmLjH25RCCIjX\n" +
       "J4gVzei15w9m//TTAUVzTQ2ayYUjlPB5cufJgekHbrNQWmxjjW25hgh+zHJZ" +
       "DlP+ykjZhqrtrUgU\ni5lg8cL0z+55+Hn65xRqm0DNxGJeCfKom1gl22DUuY" +
       "2a1MGc6hOolZr6mFyfQC0wz0LKq6+T+bxL\n+QRqZPJTsyXfwUV5ECFc1Apz" +
       "w8xbwdzGvCjnZRsh1AI/1AG/NqT+5JOjiYzmemaeWUua6xDNcgqV\nd2I5VH" +
       "OLWKeONgPFAQmvj5YWDGrySUKY54JlObmcESllc0S1olWiGibYNExLKxhQxM" +
       "S6QaeL4vn/\nUlQWVvUsNTQImEyWO4NKud1iQDtPzl5+59j4/sdPqFQS6e/7" +
       "g6ObQX/G158R+jNKf2Z1/aihQapd\nJ/ahYgwROgq1DqjYsS137x33nRhKQ3" +
       "LZS43gXkE6BJb7mxsn1lgICBMSOwlkZd93Dx3PXDm7R2Wl\nVh+3a3K3v/vi" +
       "xdN/79ieQqnaoCqMBlhvE2KmBBJXwHI4WYa15P/1iQOvvH/xw0+HBcnRcBVO" +
       "VHOK\nOh9KhsexCNXBv6H4M1d3pu9CsydTqBHAAwBT7h+wqD+pI1bvIwF2Cl" +
       "tasqg9bzklzMRSAHhtvOhY\nS+EXmTddcr4WgtMuCmAb/K7yK0I+xep6W4y9" +
       "Ks9EtBNWSGy+8mjzZ37zavtb0i0BjHdGDsoc5QoU\nusNkmXEohe8ffmvq6W" +
       "9+dPyQzBQ/VTicnt4CM0gZWK4LWQANGCCSCOTwQbNk6UbewAuMioz7d+e1\n" +
       "N/7wL1/rUqFh8CWI7M7/LiD8fvWt6OGLX/5HvxTTQMRpFJoRkilr1oaSRx0H" +
       "L4t9lB/51eZv/xw/\nA2AJAOUaK1RiDpKWIelHTfp9uxwzibUbxTAEsnfWSf" +
       "0aZ/88OfZ8Yci7/xc/lrtux9EmIhqGA9ge\nUZEXw1bh3Q3J6r0du0Wg+9yF" +
       "Ow93sQv/AolzIJHAmetOOlD25VgQfeqmlg9ef6P3vvfSKLUPtTEL\n6/uwzH" +
       "/UColH3SKgUNnes1fmVtfSGjFKk5F0wkbfAeXIm8CLbfXLf5/oHMLKmV/YeS" +
       "77zuQz0gF1\nC7/GwZmQs/LawVNXfskvSTlhBQruwXI1xEK3FfJ+/v3F7uaX" +
       "ny2lUMsc6iJ+PziLmSfyfA7aFzdo\nEqFnjK3HWxF17o5UEGZTsvojapO1H0" +
       "I7zAW1mHckyl2cfagvqPvgGS33BiQneyTLsByvrxRni+0Y\ni1j0iCgNo1zd" +
       "AO1A1TniHyAKO8R4kxj2Ko5dtdJBbvS6WFJAHmyu1yLJ9u743X/reAy/ea86" +
       "Mnri\nbcc4tOZ/XH6DXn/LV39f4+xr5ZZ9A6OLlEV0poTK2FF1QHaPYaifeO" +
       "575/l7O3Yrldvrp2mScfvu\n0ysDu1968n84oAYSTkiK7l685kvpovF2Sja4" +
       "KnOqGuM400g8X9pgP55jzsSyZkv8kBiBX7efNd01\nD4kwgiHCpXy/+pnSX5" +
       "Up0lQKfbeA0ICsN0qWU8/RqQmpZnYVDD0shhwcIp4N10AK0eyL3iAdowSd\n" +
       "6KI8Oi8/NvSTtw8+e1wFchW8iXHNk4d++7uj6a+/vqD4kqCSID7Zf+YPr1ye" +
       "XqfyT11ZtlbdGqI8\n6toijem0RQUMrqZBUr+5Y/CFB6cvyR0Jvv1QpwuWxS" +
       "g2w9KbWaX04kUnXibjof+sDxoBeHyy0Df4\naO7HdHNV6EU3CTcvcfekUgxb" +
       "JbRSm8HRpwqU3+oYhSKHM9m/UIL0DVHpRgluYZlwXXrgyCf1AIBb\n/+oNsO" +
       "gA+qqu2OpaSLIfPHD44+yv/ylbucrVrR3uT3mPsShIR+bNtkPzhjS0XUG2LR" +
       "/LCcsiTTpk\nuZrIzZcV/TGOupL0HDWKR5TsIY7aI2SQMP4sSvQVAHkgEtNH" +
       "7cDPmXpXhtqeKsc8K/y2NVZp8n8i\nAax56r8i8+TuFw9tKT8585TEyibC8M" +
       "qKvP7CbV41uBVoHKwrLZD1Lnr5pdlXv/+FoDpkA7SuHJ5M\nscS/Sa2ukikA" +
       "x7W7yvGSzWUfuPKjDT+45eypSynZ1/4HMZOVWcoSAAA=");
}
