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
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1bDXAU1R1/t/kOhHwAAhHCV3Dk667I+EFQbBKPEDlCDBBK" +
                                                    "FONm712ysLe77u6FIxZQWoQyI201KnaUmbaoVVGxI9VWndKprVpaHa3VtlOk" +
                                                    "dZzRio5l2lJbi/b/f/vua+9uc5deuJn9796+j//v997//f/v7b498jEpMQ2y" +
                                                    "UNeU7f2KZnlp1PJuUS71Wtt1anqvDVzaKRomDbYqommuh3u90pyj1Wc/+9ZA" +
                                                    "jUBKe8hEUVU1S7RkTTW7qKkpgzQYINWJu36Fhk2L1AS2iIOiL2LJii8gm9by" +
                                                    "ABmXVNQijYEYBB9A8AEEH4Pga07kgkJVVI2EW7GEqFrmzWQn8QRIqS4hPIvM" +
                                                    "Tq1EFw0xzKvpZAyghnL83w2kWOGoQWbFuduc0wjfvdA3fO+NNT8sItU9pFpW" +
                                                    "1yEcCUBYoKSHjA/TcB81zOZgkAZ7SK1KaXAdNWRRkYcY7h5SZ8r9qmhFDBpv" +
                                                    "JLwZ0anBdCZabryE3IyIZGlGnF5Ipkow9q8kpIj9wPWCBFeb4Uq8DwQrZQBm" +
                                                    "hESJxooUb5XVoEVmOkvEOTauhgxQtCxMrQEtrqpYFeEGqbP7ThHVft86y5DV" +
                                                    "fshaokVAi0Xqs1aKba2L0laxn/ZaZKozX6edBLkqWENgEYtMdmZjNUEv1Tt6" +
                                                    "Kal/Pu648sAt6ipVYJiDVFIQfzkUanAU6qIhalBVonbB8QsC94gXvLBPIAQy" +
                                                    "T3ZktvM889UzX17UcPxlO8+FGfKs7dtCJatXOtw34fXprfOXFSGMcl0zZez8" +
                                                    "FObM/Dt5yvKoDiPvgniNmOiNJR7v+uWmWx+lpwVS2U5KJU2JhMGOaiUtrMsK" +
                                                    "NdqoSg3RosF2UkHVYCtLbydlcB2QVWrfXRsKmdRqJ8UKu1Wqsf/QRCGoApuo" +
                                                    "DK5lNaTFrnXRGmDXUZ0QUgUHqYOjhNg/drbIjb4BLUx9oiSqsqr5wHapaEgD" +
                                                    "PippvQbVNZ+/da2vD1p5ICwaW02fGVFDiratV4qYlhb2mYbk04z+2G2fHAYr" +
                                                    "8AEDzfCineljriGKHGu2eTzQ/NOdg1+BcbNKU4LU6JWGIy3+M0/0nhDig4G3" +
                                                    "jkWmgAIvV+BlCrxMAfF4WL2TUJHdpdAhW2Fog9MbP3/d5mtv2jenCGxJ31aM" +
                                                    "TQpZ5wAtrt0vaa2J8d/OvJwERjj1e9fv9X768NW2EfqyO+uMpcnxg9tu6971" +
                                                    "JYEIqV4X2cCtSizeib4y7hMbnaMtU73Vez84++Q9O7TEuEtx49wdpJfE4TzH" +
                                                    "2e6GJtEgOMhE9Qtmicd6X9jRKJBi8BHgFy0R7BhcToNTR8qwXh5zkcilBAiH" +
                                                    "NCMsKpgU82uV1oChbUvcYQYxgV3XQqeMQzuvgWMBN3x2xtSJOspJtgFhLztY" +
                                                    "MBe88sfH7zv2nYXLhGRvXZ0U/9ZRyx77tQkjWW9QCvdPHuy86+6P917PLARy" +
                                                    "zM2koBFlK3gC6DJo1j0v3/yHU+8cflOIW5XHgpAY6VNkKQp1XJTQAiaqgK/C" +
                                                    "vm/coIa1oBySxT6FonH+t3rekmMfHaixe1OBOzFjWDRyBYn701rIrSdu/FcD" +
                                                    "q8YjYZxKME9ksxtgYqLmZsMQtyOO6G1vzLjvJfEBcKPgukx5iDJvRBgzwpre" +
                                                    "x7pqAZNeR9oSFLP0tDR2oz69jxfzPl6csY9RNDq0eew2BvjzXWZNhhwGRz7I" +
                                                    "I41vR92prfd/8Lg9gJ1hyZGZ7hve/4X3wLCQFLvnpoXP5DJ2/GaQq2yKX8DP" +
                                                    "A8fneCA1vGH777pWHkRmxaOIrqOhzHaDxVSsfP/JHc/9YMdem0Zdaujyw8zs" +
                                                    "8bfO/dp78M+vZPCYMBI00WIYL3PpvxYUS3PvvyW8/5bk3H9FrMYi/NuUECx/" +
                                                    "mwuwdhTX5A7Mz4H58zUsls5ydbjA6USxOnc4qzmc1XnbOf6/HMVyu/KrLFIE" +
                                                    "U0xWcIMLwh4UXekI7Vqmsn9V7qNoJU58k8Lgf9Yqfbvf/ZRZV1ogyzCwHOV7" +
                                                    "fEfur29dcZqVT0QULD0zmj4XgEVCouwlj4b/Kcwp/YVAynpIjcRXIN2iEkG/" +
                                                    "3QOzbjO2LIFVSkp66gzani4uj0fM6c6RnaTWGcsSIwquMTdeVzrC13hs5Xo4" +
                                                    "SnmXlzq73EPYRR8rMofJeSgujkWPMt2QB0Vc3hCPER8jNXZfbs5fE5tVghfz" +
                                                    "9Begsq2xyvqyVzYVyy6Do4xXVpalMjVzGzDvMB/CqMnWeui9ZFVUoD1KWgLN" +
                                                    "ravjgya75nKuuTyL5oibZhSs4U1UuXFV+3r/CCovh6OCq6zIonIoZ5VFXf5r" +
                                                    "cuBYyRVWZlG4K3eObV1+f8cIKq+IObTYOYPKr+essrglsGGkVm2KGWbsnEHj" +
                                                    "N3LWWLrJHwis3ZgDyyqusyqLzgO5s2zd1DxSuy6HYwLXOCGLxrty1li2prnN" +
                                                    "37G+eQSlzXBUc6XVWZQezJ1mW1fzpuwap8VoTuMap2XR+ICLxlCKxnL/VzrX" +
                                                    "dgBRCGHzsocwNq+153WHHpr76q5Dc/8C4aeHlMsmOPpmoz/Ds42kMn87cur0" +
                                                    "G1UznmCLoOI+0bRdvvOhUPozn5RHOSxCjNfZqSljgN+sx1rgocwtIMTbnFmy" +
                                                    "QtV++2FBN4rDejRerWDnZ/8nW3yOj6HP26poKsXlQizNXiXLmjf+vAwSo2kA" +
                                                    "DTIjZY28hlFLxOb9jzz2jPX6wiZ7Wroge2c4C760+8P69SsGbspjZTzT0VfO" +
                                                    "Kh9Zc+SVtoukOwVSFA/xaQ/eUgstTw3slQa1Ioa6PiW8N9iddxiFc6KWPOn6" +
                                                    "kUvasyieBl8rYT/Y3QZtOzPz8s4f1i22IBt6dsrTVz586B22vowSe3xFR7aS" +
                                                    "pGkeweXFjGxP8NjS4vDu4UPBtQ8uETjYFRapsDR9sUIHqZJUlZddH031JTPg" +
                                                    "qOUju9Y5shngkeAaUZeGe9kl7Vcofg6N2qeI0tYk55M2Kc+AeTLHPLnwmN9w" +
                                                    "SXsTxW8A87YB2aK5YWbNOp3YEw0SO6dhHsk+/+iS9icUb1tknKV1aCo+xxSN" +
                                                    "PMChS1/Bwa0YFbh3XdLeQ3EK3L6l5Y0Mu/c6juy6USH70CXtIxTvQwiUNH17" +
                                                    "bqim4E2ca2/gqDaMygBNpwG6LK3/4cLgLIpPYNZpUitPAjdwAjcUhkDaEvzz" +
                                                    "7LA9TN+/84aNA1/isKWCw+5m0MpdYFeiKMK1DbW62lryQN4AxxaOfMsYIa9z" +
                                                    "QT4JRRXMMG3k/tygs6neLDgMDt0oDPRkZNNd0hpQTAHUstmCMYJlcjxQKevT" +
                                                    "NIWKam79MBeOQU5msPBk5rukLUTRaJHx/dQKRMKwKIYJDBvluRv/EIc+VHjo" +
                                                    "S13SLkXhBbsH6GtkNU/QOznonYUHfZVL2tUoruCg7TdXOYKeDcduDnp34UG7" +
                                                    "PCr14KNST4tFKgF08yA1xP4cTYQN1Zlw3M6B31544Ne5pK1DEbBbm7vGwyOC" +
                                                    "ZpM3jEX7Oej9hQd9vUvaZhTdMJuzNI65OzfMF8IxzDEPFx4zdUnD54Cem6Ch" +
                                                    "GWZ/bqDjvu8pDvqpwoPWXNJuRrHFiu3REGU15/DJDBvD5/Mc+vOFh77dJe0W" +
                                                    "FBbYiGx2iOj6PNlekWXAfJxjPl54zF9zSduDYifD3K6GcsMcnxe+yDG/WBjM" +
                                                    "znmh5w4X4N9EsQ/mhWIwmJt5YCszh32Cwz6RB+y0p2EOaEKCXE2CwL0uBO5D" +
                                                    "ceeoCLzGCbxWQAJJq4oaB4vvurD4Por782HBrAe94lucxVuFsZ6kHmhKYH/M" +
                                                    "BfvjKB6CNV04f/AnOfiTBQef1PDHXMA/g+Jo/uBx3L7Hwb83RuP2py64f4bi" +
                                                    "J7iei/TlafanOezTYz5uX3EhcALFi6Mi8Akn8Mn5Gbe/dWHxOxSv5sMibj1n" +
                                                    "OYuzY2Q9J11gn0Lxe4Adjih5Nv45DvvcmFvP+y4E/ori3dEQ8Hhs/PZ57K3n" +
                                                    "jAuLv6P4KB8WMevx8De2nrQ3tv+39bBHYJ7PXGCfQ3F2VI3PX6J50l6iFcZ6" +
                                                    "EhFLKMlOQChDQUZFYCInMHFsrKcp1XqEahcWtSgqR2U99ZxF/dj4HmGaC+wL" +
                                                    "UUwC2EF5MM/Gn81hzx5r3yPMdSGAlQsNoyIwjxOYd158j7DYhYUPxcX5sIhb" +
                                                    "zyLOYlFhrCcZ1eUuactQXAKIaVTPA/FMQLqUI15aeMTNLmmtKK60SLmmsy32" +
                                                    "Ob6+YrCnA9wmDrupMLCdjpJ5emG1C4E1KFayt7BiOMdGZ8Y+F1A3c/TNY23s" +
                                                    "Ng+X7X0CbmMROtmLT6rms0xEHnxzpCfz5sjR8ShmuYodPBIjt9eFjIiiZ5Rk" +
                                                    "ApxM4Px4INmFB76AFoKj5NHJeXSOdackkTFdyERQqPmRmYU3FwKJjZzMxvMz" +
                                                    "Una48NiFImqRcQMi7hG1DJHtrc/hcRZ+fcPCg8jZiBnZoHB5eyzscUnbi2I3" +
                                                    "e69tf9vFck22SE1i346dkI4W9+2xr15wG8nUtM/o7E+/pCcOVZdPObThbXsL" +
                                                    "U+zzrIoAKQ9FFCV5R2vSdalu0JDM6FUwOYFtgBEOWKQ27dMbfEaIZwQo3GFn" +
                                                    "/Da0d1JGfGloXyVnGobIB5nw8m49A3F7r26UsKTYVhY95V/KRxq44Yh9dBjb" +
                                                    "HBSxPzvslZ48dG3HLWcue5DtNELXPzSEtZQHSJn9fQqrFDcYzc5aW6yu0lXz" +
                                                    "P5twtGJebKvMBBR1SYY9NdG/5Ln/AUf8C8niOQAA");
}
