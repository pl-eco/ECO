package org.sunflow.image;
import org.sunflow.math.MathUtils;
public final class Color {
    private float r;
    private float g;
    private float b;
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(1, 1, 1);
    public static final Color RED = new Color(1, 0, 0);
    public static final Color GREEN = new Color(0, 1, 0);
    public static final Color BLUE = new Color(0, 0, 1);
    public static final Color YELLOW = new Color(1, 1, 0);
    public static final Color CYAN = new Color(0, 1, 1);
    public static final Color MAGENTA = new Color(1, 0, 1);
    public static final Color GRAY = new Color(0.5F, 0.5F, 0.5F);
    public static Color black() { return new Color(); }
    public static Color white() { return new Color(1, 1, 1); }
    private static final float[] EXPONENT = new float[256];
    static { EXPONENT[0] = 0;
             for (int i = 1; i < 256; i++) { float f = 1.0F;
                                             int e = i - (128 + 8);
                                             if (e > 0) for (int j = 0; j <
                                                                          e;
                                                             j++) f *= 2.0F;
                                             else
                                                 for (int j =
                                                        0;
                                                      j <
                                                        -e;
                                                      j++)
                                                     f *=
                                                       0.5F;
                                             EXPONENT[i] = f; } }
    public Color() { super(); }
    public Color(float gray) { super();
                               r = (g = (b = gray)); }
    public Color(float r, float g, float b) { super();
                                              this.r = r;
                                              this.g = g;
                                              this.b = b; }
    public Color toNonLinear() { r = RGBSpace.SRGB.gammaCorrect(r);
                                 g = RGBSpace.SRGB.gammaCorrect(g);
                                 b = RGBSpace.SRGB.gammaCorrect(b);
                                 return this; }
    public Color toLinear() { r = RGBSpace.SRGB.ungammaCorrect(r);
                              g = RGBSpace.SRGB.ungammaCorrect(g);
                              b = RGBSpace.SRGB.ungammaCorrect(b);
                              return this; }
    public Color(Color c) { super();
                            r = c.r;
                            g = c.g;
                            b = c.b; }
    public Color(int rgb) { super();
                            r = (rgb >> 16 & 255) / 255.0F;
                            g = (rgb >> 8 & 255) / 255.0F;
                            b = (rgb & 255) / 255.0F; }
    public Color copy() { return new Color(this); }
    public final Color set(float r, float g, float b) { this.r = r;
                                                        this.g = g;
                                                        this.b = b;
                                                        return this; }
    public final Color set(Color c) { r = c.r;
                                      g = c.g;
                                      b = c.b;
                                      return this; }
    public final Color setRGB(int rgb) { r = (rgb >> 16 & 255) / 255.0F;
                                         g = (rgb >> 8 & 255) / 255.0F;
                                         b = (rgb & 255) / 255.0F;
                                         return this; }
    public final Color setRGBE(int rgbe) { float f = EXPONENT[rgbe & 255];
                                           r = f * ((rgbe >>> 24) + 0.5F);
                                           g = f * ((rgbe >> 16 & 255) + 0.5F);
                                           b = f * ((rgbe >> 8 & 255) + 0.5F);
                                           return this; }
    public final boolean isBlack() { return r <= 0 && g <= 0 && b <= 0; }
    public final float getLuminance() { return 0.2989F * r + 0.5866F * g +
                                          0.1145F *
                                          b; }
    public final float getMin() { return MathUtils.min(r, g, b); }
    public final float getMax() { return MathUtils.max(r, g, b); }
    public final float getAverage() { return (r + g + b) / 3.0F; }
    public final float[] getRGB() { return new float[] { r, g, b }; }
    public final int toRGB() { int ir = (int) (r * 255 + 0.5);
                               int ig = (int) (g * 255 + 0.5);
                               int ib = (int) (b * 255 + 0.5);
                               ir = MathUtils.clamp(ir, 0, 255);
                               ig = MathUtils.clamp(ig, 0, 255);
                               ib = MathUtils.clamp(ib, 0, 255);
                               return ir << 16 | ig << 8 | ib; }
    public final int toRGBE() { float v = MathUtils.max(r, g, b);
                                if (v < 1.0E-32F) return 0;
                                float m = v;
                                int e = 0;
                                if (v > 1.0F) { while (m > 1.0F) { m *= 0.5F;
                                                                   e++; }
                                }
                                else
                                    if (v <=
                                          0.5F) {
                                        while (m <=
                                                 0.5F) {
                                            m *=
                                              2.0F;
                                            e--;
                                        }
                                    }
                                v = m * 255.0F / v;
                                int c = e + 128;
                                c |= (int) (r * v) << 24;
                                c |= (int) (g * v) << 16;
                                c |= (int) (b * v) << 8;
                                return c; }
    public final Color constrainRGB() { float w = -MathUtils.min(0, r, g,
                                                                 b);
                                        if (w > 0) { r += w;
                                                     g += w;
                                                     b += w; }
                                        return this; }
    public final boolean isNan() { return Float.isNaN(r) || Float.isNaN(g) ||
                                     Float.
                                     isNaN(
                                       b); }
    public final boolean isInf() { return Float.isInfinite(r) || Float.isInfinite(
                                                                         g) ||
                                     Float.
                                     isInfinite(
                                       b); }
    public final Color add(Color c) { r += c.r;
                                      g += c.g;
                                      b += c.b;
                                      return this; }
    public static final Color add(Color c1, Color c2) { return Color.
                                                          add(
                                                            c1,
                                                            c2,
                                                            new Color(
                                                              ));
    }
    public static final Color add(Color c1, Color c2, Color dest) {
        dest.
          r =
          c1.
            r +
            c2.
              r;
        dest.
          g =
          c1.
            g +
            c2.
              g;
        dest.
          b =
          c1.
            b +
            c2.
              b;
        return dest;
    }
    public final Color madd(float s, Color c) { r += s * c.r;
                                                g += s * c.g;
                                                b += s * c.b;
                                                return this; }
    public final Color madd(Color s, Color c) { r += s.r * c.r;
                                                g += s.g * c.g;
                                                b += s.b * c.b;
                                                return this; }
    public final Color sub(Color c) { r -= c.r;
                                      g -= c.g;
                                      b -= c.b;
                                      return this; }
    public static final Color sub(Color c1, Color c2) { return Color.
                                                          sub(
                                                            c1,
                                                            c2,
                                                            new Color(
                                                              ));
    }
    public static final Color sub(Color c1, Color c2, Color dest) {
        dest.
          r =
          c1.
            r -
            c2.
              r;
        dest.
          g =
          c1.
            g -
            c2.
              g;
        dest.
          b =
          c1.
            b -
            c2.
              b;
        return dest;
    }
    public final Color mul(Color c) { r *= c.r;
                                      g *= c.g;
                                      b *= c.b;
                                      return this; }
    public static final Color mul(Color c1, Color c2) { return Color.
                                                          mul(
                                                            c1,
                                                            c2,
                                                            new Color(
                                                              ));
    }
    public static final Color mul(Color c1, Color c2, Color dest) {
        dest.
          r =
          c1.
            r *
            c2.
              r;
        dest.
          g =
          c1.
            g *
            c2.
              g;
        dest.
          b =
          c1.
            b *
            c2.
              b;
        return dest;
    }
    public final Color mul(float s) { r *= s;
                                      g *= s;
                                      b *= s;
                                      return this; }
    public static final Color mul(float s, Color c) { return Color.
                                                        mul(
                                                          s,
                                                          c,
                                                          new Color(
                                                            )); }
    public static final Color mul(float s, Color c, Color dest) {
        dest.
          r =
          s *
            c.
              r;
        dest.
          g =
          s *
            c.
              g;
        dest.
          b =
          s *
            c.
              b;
        return dest;
    }
    public final Color div(Color c) { r /= c.r;
                                      g /= c.g;
                                      b /= c.b;
                                      return this; }
    public static final Color div(Color c1, Color c2) { return Color.
                                                          div(
                                                            c1,
                                                            c2,
                                                            new Color(
                                                              ));
    }
    public static final Color div(Color c1, Color c2, Color dest) {
        dest.
          r =
          c1.
            r /
            c2.
              r;
        dest.
          g =
          c1.
            g /
            c2.
              g;
        dest.
          b =
          c1.
            b /
            c2.
              b;
        return dest;
    }
    public final Color exp() { r = (float) Math.exp(r);
                               g = (float) Math.exp(g);
                               b = (float) Math.exp(b);
                               return this; }
    public final Color opposite() { r = 1 - r;
                                    g = 1 - g;
                                    b = 1 - b;
                                    return this; }
    public final Color clamp(float min, float max) { r = MathUtils.
                                                           clamp(
                                                             r,
                                                             min,
                                                             max);
                                                     g = MathUtils.
                                                           clamp(
                                                             r,
                                                             min,
                                                             max);
                                                     b = MathUtils.
                                                           clamp(
                                                             r,
                                                             min,
                                                             max);
                                                     return this;
    }
    public static final Color blend(Color c1, Color c2, float b) {
        return blend(
                 c1,
                 c2,
                 b,
                 new Color(
                   ));
    }
    public static final Color blend(Color c1, Color c2, float b, Color dest) {
        dest.
          r =
          (1.0F -
             b) *
            c1.
              r +
            b *
            c2.
              r;
        dest.
          g =
          (1.0F -
             b) *
            c1.
              g +
            b *
            c2.
              g;
        dest.
          b =
          (1.0F -
             b) *
            c1.
              b +
            b *
            c2.
              b;
        return dest;
    }
    public static final Color blend(Color c1, Color c2, Color b) {
        return blend(
                 c1,
                 c2,
                 b,
                 new Color(
                   ));
    }
    public static final Color blend(Color c1, Color c2, Color b, Color dest) {
        dest.
          r =
          (1.0F -
             b.
               r) *
            c1.
              r +
            b.
              r *
            c2.
              r;
        dest.
          g =
          (1.0F -
             b.
               g) *
            c1.
              g +
            b.
              g *
            c2.
              g;
        dest.
          b =
          (1.0F -
             b.
               b) *
            c1.
              b +
            b.
              b *
            c2.
              b;
        return dest;
    }
    public static final boolean hasContrast(Color c1, Color c2, float thresh) {
        if (Math.
              abs(
                c1.
                  r -
                  c2.
                    r) /
              (c1.
                 r +
                 c2.
                   r) >
              thresh)
            return true;
        if (Math.
              abs(
                c1.
                  g -
                  c2.
                    g) /
              (c1.
                 g +
                 c2.
                   g) >
              thresh)
            return true;
        if (Math.
              abs(
                c1.
                  b -
                  c2.
                    b) /
              (c1.
                 b +
                 c2.
                   b) >
              thresh)
            return true;
        return false;
    }
    public String toString() { return String.format("(%.3f, %.3f, %.3f)",
                                                    r,
                                                    g,
                                                    b); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1170389086000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1bDXAU1R1/twn5gpAPQCBC+AqOfN0VGT8Iik3iAZEjxEBC" +
                                                    "iWLc7L1Lluztrrt74YgFlBahzEhbjYodZaYtalVU7Ei1Vad0aquWVkdrte0U" +
                                                    "aR1ntKJjmbbU1qL9/9+++9q729ylF25m/7e77+P/+733f///e7tvj3xMxpkG" +
                                                    "WaRryvY+RbO8NGp5tyqXeq3tOjW91wYubRcNkwZbFNE0N8K9Hmnu0aqzn32r" +
                                                    "v1ogJd1kkqiqmiVasqaaHdTUlEEaDJCqxF2/QsOmRaoDW8VB0RexZMUXkE1r" +
                                                    "RYCMTypqkYZADIIPIPgAgo9B8DUlckGhSqpGwi1YQlQt82ayk3gCpESXEJ5F" +
                                                    "5qRWoouGGObVtDMGUEMZXncBKVY4apDZce425zTCdy/yDd97Y/UPi0hVN6mS" +
                                                    "1Q0IRwIQFijpJhPCNNxLDbMpGKTBblKjUhrcQA1ZVOQhhrub1JpynypaEYPG" +
                                                    "GwlvRnRqMJ2JlpsgITcjIlmaEacXkqkSjF2NCyliH3C9IMHVZrgK7wPBChmA" +
                                                    "GSFRorEixQOyGrTILGeJOMeGtZABipaGqdWvxVUVqyLcILV23ymi2ufbYBmy" +
                                                    "2gdZx2kR0GKRuqyVYlvrojQg9tEei0xz5mu3kyBXOWsILGKRKc5srCbopTpH" +
                                                    "LyX1z8dtVx64RV2jCgxzkEoK4i+DQvWOQh00RA2qStQuOGFh4B7xghf2CYRA" +
                                                    "5imOzHaeZ7565suL64+/bOe5MEOe9b1bqWT1SId7J74+o2XB8iKEUaZrpoyd" +
                                                    "n8KcmX87T1kR1WHkXRCvERO9scTjHb/cfOuj9LRAKlpJiaQpkTDYUY2khXVZ" +
                                                    "ocZqqlJDtGiwlZRTNdjC0ltJKZwHZJXad9eHQia1Wkmxwm6VaOwamigEVWAT" +
                                                    "lcK5rIa02LkuWv3sPKoTQirhILVwjCP2j/1bpMvXaYK5+0RJVGVV84HxUtGQ" +
                                                    "+n1U0np6oXX7w6IxYPqkiGlpYZ8ZUUOKts1nGpJPM/ri13IYet8HyDXDi/al" +
                                                    "j1nNUeRUvc3jgeae4RzsCoyTNZoSpEaPNBxp9p95oueEEDd+3hoWmQoKvFyB" +
                                                    "lynwMgXE42H1TkZFdhdCBwzAUAYnN2HBhi3X3rRvbhHYjr6tGJsQss4FNly7" +
                                                    "X9JaEuO9lXk1CYxu2veu3+v99OGrbaPzZXfOGUuT4we33da160sCEVK9LLKB" +
                                                    "WxVYvB19Y9wHNjhHV6Z6q/Z+cPbJe3ZoiXGW4rb58E8vicN3rrPdDU2iQXCI" +
                                                    "ieoXzhaP9bywo0EgxeATwA9aItgtuJh6p46UYbwi5hKRyzggHNKMsKhgUsyP" +
                                                    "VVj9hrYtcYcZxER2XgOdMh7tuhqOhdzQ2T+mTtJRTrYNCHvZwYK53FU/Pn7f" +
                                                    "se8sWi4ke+eqpHi3gVr2WK9JGMlGg1K4f/Jg+113f7z3emYhkGNeJgUNKFtg" +
                                                    "5EOXQbPuefnmP5x65/CbQtyqPBaEwEivIktRqOOihBYwUQV8E/Z9Q6ca1oJy" +
                                                    "SBZ7FYrG+d+q+UuPfXSg2u5NBe7EjGHxyBUk7k9vJreeuPFf9awaj4RxKcE8" +
                                                    "kc1ugEmJmpsMQ9yOOKK3vTHzvpfEB8Btgqsy5SHKvA9hzAhreh/rqoVMeh1p" +
                                                    "S1HM1tPS2I269D5ewvt4ScY+RtHg0Oax2xjgL3CZJRlyGBz3II8svh21pwbu" +
                                                    "/+BxewA7w5AjM903vP8L74FhISlWz0sLl8ll7HjNIFfaFL+AnweOz/FAanjD" +
                                                    "9te1LTxozI5HDV1HQ5njBoupWPX+kzue+8GOvTaN2tRQ5YeZ2ONvnfu19+Cf" +
                                                    "X8ngMWEkaKLFMF7m0n/NKJbl3n9Lef8tzbn/iliNRXjZmBAs/2oXYK0orskd" +
                                                    "mJ8D8+drWCyd5WpzgdOOYm3ucNZyOGvztnO8vhzFCrvyqyxSBFNKVrDTBWE3" +
                                                    "io50hHYt09hVpfsoWoUT3aQw+J/1Su/udz9l1pUWyDIMLEf5bt+R++taVp5m" +
                                                    "5RMRBUvPiqbPBWBRkCh7yaPhfwpzS34hkNJuUi3xFUeXqETQb3fDLNuMLUNg" +
                                                    "VZKSnjpjtqeHK+IRc4ZzZCepdcayxIiCc8yN5xWO8DUBW7kOjhLe5SXOLvcQ" +
                                                    "dtLLisxlcj6Ki2PRo1Q35EERlzPEY8THSLXdl1vy18RmkeDFPH0FqGwgVllv" +
                                                    "9sqmYdnlcJTyykqzVKZmbgPmHRZAGDXZ2g69l6yKCrTHuOZAU8va+KDJrrmM" +
                                                    "ay7LojniphkFa3gTVW5a07rRP4LKy+Eo5yrLs6gcylllUYf/mhw4VnCFFVkU" +
                                                    "7sqd4+oOv79tBJVXxBxa7D+Dyq/nrLK4OdA5Uqs2xgwz9p9B4zdy1liy2R8I" +
                                                    "rN+UA8tKrrMyi84DubNs2dw0UruugGMi1zgxi8a7ctZYuq5ptb9tY9MISpvg" +
                                                    "qOJKq7IoPZg7zdUdTZuza5weozmda5yeReMDLhpDKRrL/F9pX98GRCGEzc8e" +
                                                    "wti81p7XHXpo3qu7Ds37C4SfblImm+Dom4y+DM8yksr87cip029UznyCLYKK" +
                                                    "e0XTdvnOh0Dpz3hSHt2wCDFBZ3+NGQP8Fj3WAg9lbgEh3ubMkhWq9tkPB7pQ" +
                                                    "HNaj8WoFOz+7nmLxOT6GPm+LoqkUlwuxNHuVLGve+PMxSIymATTIzJQ18jpG" +
                                                    "LRGb9z/y2DPW64sa7Wnpwuyd4Sz40u4P6zau7L8pj5XxLEdfOat8ZN2RV1Zf" +
                                                    "JN0pkKJ4iE970JZaaEVqYK8wqBUx1I0p4b3e7rzDKJwTteRJ149c0p5F8TT4" +
                                                    "Wgn7we42aNtZmZd3/rBusQXZ0LNTn77y4UPvsPVllNjjKzqylSRN8wguL2Zm" +
                                                    "e2LHlhaHdw8fCq5/cKnAwa60SLml6UsUOkiVpKq87Pxoqi+ZCUcNH9k1zpHN" +
                                                    "AI8E14i6NNzLLmm/QvFzaNReRZQGkpxP2qQ8A+YpHPOUwmN+wyXtTRS/Aczb" +
                                                    "+mWL5oaZNesMYk80SOw/DfNI9vlHl7Q/oXjbIuMtrU1T8bmlaOQBDl36Sg5u" +
                                                    "5ajAveuS9h6KU+D2LS1vZNi913Fk140K2YcuaR+heB9CoKTp23NDNRVv4ly7" +
                                                    "k6PqHJUBmk4DdFla/8OFwVkUn8Cs06RWngRu4ARuKAyBtCX459lhe5i+f+cN" +
                                                    "Gwe+xGFLBYfdxaCVucCuQFGEaxtqdaxuzgN5PRxbOfKtY4S81gX5ZBSVMMO0" +
                                                    "kftzg86merPhMDh0ozDQk5HNcEmrRzEVUMtmM8YIlsnxQKW0V9MUKqq59cM8" +
                                                    "OAY5mcHCk1ngkrYIRYNFJvRRKxAJw6IYJjBslOdu/EMc+lDhoS9zSbsUhRfs" +
                                                    "HqCvk9U8Qe/koHcWHvRVLmlXo7iCg7bfXOUIeg4cuzno3YUH7fKo1IOPSj3N" +
                                                    "FqkA0E2D1BD7cjQRNlRnwXE7B3574YFf55K2AUXAbm3uGg+PCJpN3jAW7eeg" +
                                                    "9xce9PUuaVtQdMFsztI45q7cMF8IxzDHPFx4zNQlDZ8Dem6ChmaY/bmBjvu+" +
                                                    "pzjopwoPWnNJuxnFViu2J0OU1ZzDJzNsDJ/Pc+jPFx76dpe0W1BYYCOy2Sai" +
                                                    "6/Nke0WWAfNxjvl44TF/zSVtD4qdDHOrGsoNc3xe+CLH/GJhMDvnhZ47XIB/" +
                                                    "E8U+mBeKwWBu5oGtzBz2CQ77RB6w056GOaAJCXLVCQL3uhC4D8WdoyLwGifw" +
                                                    "WgEJJK0qqh0svuvC4vso7s+HBbMe9IpvcRZvFcZ6knqgMYH9MRfsj6N4CNZ0" +
                                                    "4fzBn+TgTxYcfFLDH3MB/wyKo/mDx3H7Hgf/3hiN25+64P4Zip/gei7Sm6fZ" +
                                                    "n+awT4/5uH3FhcAJFC+OisAnnMAn52fc/taFxe9QvJoPi7j1nOUszo6R9Zx0" +
                                                    "gX0Kxe8Bdjii5Nn45zjsc2NuPe+7EPgrindHQ8DjsfHb/2NvPWdcWPwdxUf5" +
                                                    "sIhZj4e/sfWkvbH9v62HPQLzfOYC+xyKs6NqfP4SzZP2Eq0w1pOIWMK47ASE" +
                                                    "UhRkVAQmcQKTxsZ6GlOtR6hyYVGDomJU1lPHWdSNje8RprvAvhDFZIAdlAfz" +
                                                    "bPw5HPacsfY9wjwXAli5UD8qAvM5gfnnxfcIS1xY+FBcnA+LuPUs5iwWF8Z6" +
                                                    "klFd7pK2HMUlgJhG9TwQzwKkyzjiZYVH3OSS1oLiSouUaTrbUp/j6ysGewbA" +
                                                    "beSwGwsD2+komacX1roQWIdiFXsLK4ZzbHRm7PMAdRNH3zTWxm7zcNneJ+A2" +
                                                    "FqGdvfikaj7LROTBN0d6Mm+OHB2PYpar2MEjMXJ7XMiIKLpHSSbAyQTOjweS" +
                                                    "XXjgC2ghOEoe7ZxH+1h3ShIZ04VMBIWaH5nZeHMRkNjEyWw6PyNlhwuPXSii" +
                                                    "FhnfL+IeUcsQ2d76HB5n4dc2LDyInI2YkQ0Kl7fHwh6XtL0odrP32va3XCzX" +
                                                    "FItUJ/bt2AnpaHHfHvvqBbeRTEv7bM7+1Et64lBV2dRDnW/bW5hin2OVB0hZ" +
                                                    "KKIoyTtak85LdIOGZEavnMmJbAOMcMAiNWmf3uAzQvxHgMIddsZvQ3snZcSX" +
                                                    "hvZZcqZhiHyQCU/v1jMQt/fqRglLim1l0VOuUj7SwA1H7CPD2OagiP2ZYY/0" +
                                                    "5KFr2245c9mDbKcRuv6hIaylLEBK7e9TWKW4wWhO1tpidZWsWfDZxKPl82Nb" +
                                                    "ZSaiqE0y7GmJ/iXP/Q9JJQst0jkAAA==");
}
