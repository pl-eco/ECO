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
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1bC5AUxRnunTvuBcc9AIETjtdhyWs3SPngUMzducDJcpzH" +
                                                    "K5ziOTfbezcwOzPOzh7LGVBJFEKVJNFTMaVUJUGNioopiSZqhVRM1JBoaYwm" +
                                                    "qSCJZZVGtAyVhJgYNP/f0/ua3e3bveyxVfPvzPTj/77uv/+/e6bn8MdkTMQi" +
                                                    "C0xD29GnGbaXxmzvVu1ir73DpBHv1YGLO2UrQoNtmhyJrId7PcrsIzVnPvtW" +
                                                    "f61EyrrJBFnXDVu2VUOPdNGIoQ3QYIDUJO/6NRqO2KQ2sFUekH1RW9V8ATVi" +
                                                    "LwuQsSlFbdIUiEPwAQQfQPAxCL6WZC4oVE31aLgNS8i6HbmR7CKeACkzFYRn" +
                                                    "k1nplZiyJYd5NZ2MAdRQgdcbgRQrHLPIzAR3h3MG4bsX+Ibuvb72hyWkppvU" +
                                                    "qPo6hKMACBuUdJNxYRrupVakJRikwW5Sp1MaXEctVdbUQYa7m9RH1D5dtqMW" +
                                                    "TTQS3oya1GI6ky03TkFuVlSxDStBL6RSLRi/GhPS5D7gel6Sq8NwBd4HglUq" +
                                                    "ALNCskLjRUq3qXrQJjPcJRIcm1ZDBihaHqZ2v5FQVarLcIPUO32nyXqfb51t" +
                                                    "qXofZB1jREGLTRpyVoptbcrKNrmP9thkijtfp5MEuSpZQ2ARm0xyZ2M1QS81" +
                                                    "uHoppX8+7rh8/036Kl1imINU0RB/BRRqdBXqoiFqUV2hTsFx8wP3yOe9sFci" +
                                                    "BDJPcmV28jzz1dNfXth47GUnz/lZ8qzt3UoVu0c51Dv+9Wlt85aWIIwK04io" +
                                                    "2PlpzJn5d/KUZTETRt55iRox0RtPPNb1y823PEpPSaSqnZQphhYNgx3VKUbY" +
                                                    "VDVqraQ6tWSbBttJJdWDbSy9nZTDeUDVqXN3bSgUoXY7KdXYrTKDXUMThaAK" +
                                                    "bKJyOFf1kBE/N2W7n53HTEJINRykHo4xxPmxf5us9vUbYeqTFVlXdcMHtktl" +
                                                    "S+n3UcXwReSwqUGvRaJ6SDO2+yKW4jOsvsS1GoYu9wFcw/KiUZnFrS6G6Gu3" +
                                                    "ezzQsNPcw1qDEbHK0ILU6lGGoq3+00/0HJcSZs5522QyKPByBV6mwMsUEI+H" +
                                                    "1TsRFTmdBU29DQYtuLNx89ZtufqGvbNLwErM7aXYWJB1NnDg2v2K0ZYc2e3M" +
                                                    "fylgXlO+d+0e76cPX+mYly+3G85amhw7sP3WjTd/SSJSuj9FNnCrCot3ohdM" +
                                                    "eLsm9zjKVm/Nng/OPHnPTiM5otIcNB/omSVxoM52t7tlKDQIri9Z/fyZ8tGe" +
                                                    "F3Y2SaQURj94PFsGCwVn0ujWkTZgl8WdH3IZA4RDhhWWNUyKe6wqu98ytifv" +
                                                    "MIMYz87roFPGogXXwjGfmzT7x9QJJsqJjgFhL7tYMOe64sfH7jv6nQVLpVQ/" +
                                                    "XJMS2dZR2xnVdUkjWW9RCvdPHOi86+6P91zLLARyzMmmoAllG4xx6DJo1tte" +
                                                    "vvEPJ9859KaUsCqPDcEu2qupSgzquCCpBUxUAy+Efd+0QQ8bQTWkyr0aReP8" +
                                                    "b83cxUc/2l/r9KYGd+LGsHD4CpL3p7aSW45f/69GVo1HwQiUZJ7M5jTAhGTN" +
                                                    "LZYl70AcsVvfmH7fS/ID4CDBKUXUQcr8DGHMCGt6H+uq+Ux6XWmLUcw0M9LY" +
                                                    "jYbMPl7E+3hR1j5G0eTS5nHaGODPE8yHLDUMLnqAxxDfzvqT2+7/4HFnALsD" +
                                                    "jisz3Tu07wvv/iEpJSrPyQiMqWWcyMwgVzsUv4CfB47P8UBqeMPxzPVtPDzM" +
                                                    "TMQH00RDmSWCxVSseP/Jnc/9YOceh0Z9elDyw5zr8bfO/tp74M+vZPGYMBIM" +
                                                    "2WYYLxH0XyuKJfn332Lef4vz7r8SVmMJXjYnBcu/UgCsHcVV+QPzc2D+Qg2L" +
                                                    "pbNcHQI4nShW5w9nNYezumA7x+tLUSxzKr/CJiUweWQFNwgQdqPoykTo1DKF" +
                                                    "XVWLR9EKnNKmhMH/rNV6d7/7KbOujECWZWC5ynf7Dt/f0Lb8FCufjChYekYs" +
                                                    "cy4A0/9k2YseDf9Tml32C4mUd5Naha8tNspaFP12N8ynI/EFB6w/0tLT58bO" +
                                                    "RHBZImJOc4/sFLXuWJYcUXCOufG8yhW+xmErN8BRxru8zN3lHsJOelmR2UzO" +
                                                    "RXFhPHqUm5Y6IOPChXisxBipdfpyS+Ga2HwRvJinrwiVbYtX1pu7silYdikc" +
                                                    "5byy8hyV6dnbgHmHeRBGI2wVh95L1WUN2mNMa6ClbXVi0OTWXME1V+TQHBVp" +
                                                    "RsEaPoIqN61qX+8fRuWlcFRylZU5VA7mrbKky39VHhyruMKqHApvzp/jyi6/" +
                                                    "v2MYlZfFHVr8P4vKr+etsrQ1sGG4Vm2OG2b8P4vGb+StsWyzPxBYuykPltVc" +
                                                    "Z3UOnfvzZ9m2uWW4dl0Gx3iucXwOjXflrbF8TctKf8f6lmGUtsBRw5XW5FB6" +
                                                    "IH+aK7taNufWODVOcyrXODWHxgcEGkNpGiv8X+lc2wFEIYTNzR3C2LzWmdcd" +
                                                    "fGjOqzcfnPMXCD/dpEKNgKNvsfqyPLVIKfO3wydPvVE9/Qm2CCrtlSOOy3c/" +
                                                    "7sl8mpP2kIZFiHEm+2vOGuC3mPEWeCh7C0iJNmeWrFG9z3kMsBHFITOWqFZy" +
                                                    "8rPrSTaf42Po87Zphk5xuRBPc1bJquFNPAmDxFgGQItMT1sjr2HUkrF53yOP" +
                                                    "PWO/vqDZmZbOz90Z7oIv7f6wYf3y/hsKWBnPcPWVu8pH1hx+ZeUFyp0SKUmE" +
                                                    "+IxHaumFlqUH9iqL2lFLX58W3hudzjuEwj1RS510/UiQ9iyKp8HXKtgPTrdB" +
                                                    "287Ivrzzh02bLcgGn5389OUPH3yHrS9jxBlfseGtJGWaR3B5MT3Xszm2tDi0" +
                                                    "e+hgcO2DiyUOdrlNKm3DXKTRAaqlVOVl50fSfcl0OOr4yK5zj2wGeDi4VkzQ" +
                                                    "cC8L0n6F4ufQqL2arGxLcT4Zk/IsmCdxzJOKj/kNQdqbKH4DmLf3qzbNDzNr" +
                                                    "1mnEmWiQ+H8G5uHs84+CtD+heNsmY22jw9DxCaVsFQAOXfpyDm75iMC9K0h7" +
                                                    "D8VJcPu2UTAy7N5rOLJrRoTsQ0HaRyjehxCoGOaO/FBNxps4197AUW0YkQFG" +
                                                    "3AYoWFr/Q8DgDIpPYNYZoXaBBK7jBK4rDoGMJfjnuWF7mL5/FwwbB77CYStF" +
                                                    "h72RQasQwK5CUYJrG2p3rWwtAHkjHFs58q2jhLxegHwiimqYYTrI/flBZ1O9" +
                                                    "mXBYHLpVHOipyKYJ0hpRTAbUaqQVYwTL5HqgUt5rGBqV9fz6YQ4cA5zMQPHJ" +
                                                    "zBOkLUDRZJNxfdQORMOwKIYJDBvl+Rv/IIc+WHzoSwRpF6Pwgt0D9DWqXiDo" +
                                                    "XRz0ruKDvkKQdiWKyzho581VnqBnwbGbg95dfNCCR6UefFTqabVJFYBuGaCW" +
                                                    "3JenibChOgOO2znw24sP/BpB2joUAae1uWs8NCxoNnnDWLSPg95XfNDXCtK2" +
                                                    "oNgIsznb4Jg35of5fDiGOOah4mOmgjR8Dui5ARqaYfbnBzrh+57ioJ8qPmhD" +
                                                    "kHYjiq12fPeFrOp5h09m2Bg+n+fQny8+9B2CtJtQ2GAjaqRDRtfnyfWKLAvm" +
                                                    "YxzzseJj/pog7TYUuxjmdj2UH+bEvPBFjvnF4mB2zws9dwiAfxPFXpgXysFg" +
                                                    "fuaBrcwc9nEO+3gBsDOehrmgSUlytUkC9woI3IfizhEReI0TeK2IBFJWFbUu" +
                                                    "Ft8VsPg+ivsLYcGsB73iW5zFW8WxnpQeaE5if0yA/XEUD8GaLlw4+BMc/Imi" +
                                                    "g09p+KMC8M+gOFI4eBy373Hw743SuP2pAPfPUPwE13PR3gLN/hSHfWrUx+0r" +
                                                    "AgLHUbw4IgKfcAKfnJtx+1sBi9+heLUQFgnrOcNZnBkl6zkhgH0Sxe8Bdjiq" +
                                                    "Fdj4Zznss6NuPe8LCPwVxbsjIeDxOPid/9G3ntMCFn9H8VEhLOLW4+FvbD0Z" +
                                                    "b2z/b+thj8A8nwlgn0VxZkSNz1+ieTJeohXHepIRSxqTm4BUjoKMiMAETmDC" +
                                                    "6FhPc7r1SDUCFnUoqkZkPQ2cRcPo+B5pqgD2+SgmAuygOlBg48/isGeNtu+R" +
                                                    "5ggIYOVS44gIzOUE5p4T3yMtErDwobiwEBYJ61nIWSwsjvWkorpUkLYUxUWA" +
                                                    "mMbMAhDPAKRLOOIlxUfcIkhrQ3G5TSoMk22ez/P1FYM9DeA2c9jNxYHtdpTM" +
                                                    "00urBQTWoFjB3sLK4TwbnRn7HEDdwtG3jLaxOzwE2/sk3MYidbIXn1QvZJmI" +
                                                    "PPjmSE/2zZEj41HKcpW6eCRHbo+AjIyie4RkApxM4Nx4IFXAA19AS8ER8ujk" +
                                                    "PDpHu1NSyEQEZKIo9MLIzMSbC4DEJk5m07kZKTsFPG5GEbPJ2H4Z94jalsz2" +
                                                    "1ufxOAu/q2HhQeZs5KxsUAjeHku3CdL2oNjN3ms7X22xXJNsUpvct+MkZKLF" +
                                                    "fXvsqxfcRjIl4wM556Mu5YmDNRWTD25429nCFP/wqjJAKkJRTUvd0ZpyXmZa" +
                                                    "NKQyepVMjmcbYKT9NqnL+PQGnxHiPwKU7nAyfhvaOyUjvjR0zlIzDUHkg0x4" +
                                                    "ereZhbizVzdGWFJ8K4uZdpX2kQZuOGKfE8Y3B0WdDwp7lCcPXt1x0+lLHmQ7" +
                                                    "jdD1Dw5iLRUBUu58n8IqxQ1Gs3LWFq+rbNW8z8YfqZwb3yozHkV9imFPSfYv" +
                                                    "ee5/XPVcKbw5AAA=");
}
