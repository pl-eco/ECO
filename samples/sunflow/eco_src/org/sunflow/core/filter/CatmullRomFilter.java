package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class CatmullRomFilter implements Filter {
    public float getSize() { return 4.0F; }
    public float get(float x, float y) { return this.catrom1d(x) * this.catrom1d(
                                                                          y);
    }
    private float catrom1d(float x) { x = Math.abs(x);
                                      float x2 = x * x;
                                      float x3 = x * x2;
                                      if (x >= 2) return 0;
                                      if (x < 1) return 3 * x3 - 5 *
                                                   x2 +
                                                   2;
                                      return -x3 + 5 * x2 - 8 * x +
                                        4; }
    public CatmullRomFilter() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026718000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Xe2wURRif3vVBH9pSoOVdKAV53tJETKQkgKVA6QG15VnA" +
                                                   "Mt2duy7s7Syzc+21\nIEJMACE+iJpookgMCQ9BTNCgCSoEUJR/wERNSEANiZ" +
                                                   "ooJsYEMfqH38zcc+9aCJfs3O7M95zv+37z\nzck7qMBlaKzuBnifQ9xAY3sr" +
                                                   "Zi4xGi3suqtgqlO/XFDcerTFpj6UF0Q+0+CoPKi7moE51kxDa17U\nEGNohk" +
                                                   "OtvrBFeYDEeGCLNScub1lwTpbAtYfOVu4+kl/jQwVBVI5tm3LMTWo3WSTicl" +
                                                   "QR3IJ7sBbl\npqUFTZc3BNEjxI5GGqntcmxzdxvaifxBVOjoQiZHE4MJ5Roo" +
                                                   "1xzMcEST6rVWqRYkDGOEY9MmxsKk\nOuCcmckJZsf52rKpQcgQsbgG3JEWgN" +
                                                   "cTkl4rb7NcdfzH1jyx4/BxPyrvQOWm3S6E6eAJB30dqCxC\nIl2EuQsNgxgd" +
                                                   "aKhNiNFOmIkts19q7UCVrhm2MY8y4rYRl1o9grDSjTqESZ2JySAq04VPLKpz" +
                                                   "ypJ7\nFDKJZSS+CkIWDoPbVSm3lbuLxTw4WGKCYSyEdZJgyd9q2hDxGi9H0s" +
                                                   "e6FiAA1qII4d00qSrfxjCB\nKlUsLWyHtXbOTDsMpAU0Clo4Gj2gULHXDta3" +
                                                   "4jDp5Gikl65VLQFVsdwIwcLRCC+ZlARRGu2JUlp8\nZlTd3Xfsrc8WyNzON4" +
                                                   "huCftLgGm8h6mNhAgjtk4U471o4LXm9dGxPoSAeISHWNEsnHx2dfDXz2sU\n" +
                                                   "zZgcNCu7thCdd+orDta0bV9CkV+YMcShrimCn+G5LIfW+EpDzIGqrUpKFIuB" +
                                                   "xOL5ti/W7zpBfvOh\nkmZUqFMrGoE8GqrTiGNahC0hNmGYE6MZFRPbaJTrza" +
                                                   "gI3oOQ8mp2ZSjkEt6M8i05VUjlN2xRCESI\nLSqGd9MO0cS7g3m3fI85CKEi" +
                                                   "eJAGTwFSP/nPUUNAc6N2yKK9mst0jbJw8lunjGggHTJDa8Q8ErWs\nNhpZLC" +
                                                   "cCIokcjtZq3TRCNKxj27SpFjahbHU6yyA94v/hRceE5ZW9eXkCCr0lbUE1LK" +
                                                   "WWQVinfvT2\n1zuaWl7Yp9JFpHjcZ46mgsZAXGNAaAwojQGvRpSXJxUNF5pV" +
                                                   "5GDft0IFA9aVTWvftGzzvlo/pIzT\nmw+bJkhrwbu4OU06bUyVebNERB1ybe" +
                                                   "S7G/YG7h2dr3JNGxiNc3KXXjt19fBfZdN9yJcbKoWbANYl\nQkyrwNckBNZ5" +
                                                   "iyuX/D/2Lz/z3dWbU1NlxlFdVvVnc4rqrfUGhFGdGICHKfFHRpX716I1B30o" +
                                                   "HyAB\nYFDaDwgz3qsjo4obEogofCkKotIQZRFsiaUEjJXwbkZ7UzMyUyrEMF" +
                                                   "wljQikx0AJpveeL5z9/bnS\ny9LjBO6Wp51s7YSrKh6ayoNVjBCYv/lG66uv" +
                                                   "39m7QSZBPAs4HHfRLsvUY8AyJcUC5WsBhIgY1a22\nI9QwQybusohIpv/KJ9" +
                                                   "d/9PtLFWrXLZhJBG3m/QWk5kc9hXZdfebv8VJMni6Oj5QbKTLlzbCU5IWM\n" +
                                                   "4T5hR2z3N+Pe/BK/DegGiOKa/USCBJKeIbmPAbm90+Q4y7M2Wwy1IHvmAFmd" +
                                                   "47Du1HecCNdGt331\nibS6FKef+ulhWI6dBhVUqXsYKJ2N4kMGeInVEY4Yq0" +
                                                   "QIqr3VuxS73SDs8fMrNlZY5/8FtR2gVoeT\n1F3JADxiGZGOUxcU3bhwsWrz" +
                                                   "dT/yLUYlFsXGYizzHxVD4hG3G3An5sxfIM2o6B0iRrkvSFo7Or5L\nsYwv+T" +
                                                   "FJjlPi2SPep6ZT5cn3ao6qs2BLoZTwcdxA56Y88/eu+7NsD760SSFOZeZZ1A" +
                                                   "T92i99F8lj\n8178KQdYFnPqzLJID7HSbPILlRlIt1y2FKk633/8vbP8+oy5" +
                                                   "SuX0gUHOyzh97uH+mrmnDzwEvtV4\nNsEremjPmKf93eYVn+x6FLRldUuZTA" +
                                                   "3p2wFKwZ4os8XGipkymY4TkulYKoI6Gp7CeDoWetNRApEY\n5g5SRS2DrC0X" +
                                                   "w1KOisKEt0N5QiBGpt8ImBmBzqJHgubtPbWfXln9zl4Vg2mDtP3pXJ36cz/8" +
                                                   "uNX/\n8oUuxeftrjzEB8cf+fnM7bbhKnVUCzopqwtM51FtqPSm3BHJO3EwDZ" +
                                                   "L60oyJJ3e23ZIWCb4FXAA1\nxeqCUS+GZlUzc+5bgfJjUWbMpsBTEo9ZyQPH" +
                                                   "zCcl+sRnuxwk6aZBotcphg6O/BA9yZGyf8ND2l8m\nJifB82jc/kdz2p8DaS" +
                                                   "CJHGb2QH8Z85icl4Ii5dOWQXySbTgEfYiOOaOResPjGHlQx2Jwn/R2YOKc\n" +
                                                   "Gpl1c1O3DT14Y/vGu8Fv/5G9RPJGUApteQhkpJVtegkXOoyETGl5qTpNHPnn" +
                                                   "5gJY1RdycTcTL9Jc\npuh7wFovPUf54i+drI+j0jQy2PP4WzrRDsgIIBKvzz" +
                                                   "oJuK+QR5C4jgXU3SPz6BA7MymjpOVlOgF9\nUXWd7tTXndowIXZg1SsSTwvg" +
                                                   "Gt7fL+9NcA1UPVQSPicOKC0h6xr64PSac+8/mSjDjO4qK0fr1eog\n0QfIzt" +
                                                   "3dNEUcLvuR/o+rP5x39NAtn+yv/gfzbPa4AxEAAA==");
}
