package org.sunflow.image;
import org.sunflow.math.MathUtils;
final public class Color {
    private float r;
    private float g;
    private float b;
    final public static Color BLACK = new Color(0, 0, 0);
    final public static Color WHITE = new Color(1, 1, 1);
    final public static Color RED = new Color(1, 0, 0);
    final public static Color GREEN = new Color(0, 1, 0);
    final public static Color BLUE = new Color(0, 0, 1);
    final public static Color YELLOW = new Color(1, 1, 0);
    final public static Color CYAN = new Color(0, 1, 1);
    final public static Color MAGENTA = new Color(1, 0, 1);
    final public static Color GRAY = new Color(0.5F, 0.5F, 0.5F);
    public static Color black() { return new Color(); }
    public static Color white() { return new Color(1, 1, 1); }
    final private static float[] EXPONENT = new float[256];
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
    final public Color set(float r, float g, float b) { this.r = r;
                                                        this.g = g;
                                                        this.b = b;
                                                        return this; }
    final public Color set(Color c) { r = c.r;
                                      g = c.g;
                                      b = c.b;
                                      return this; }
    final public Color setRGB(int rgb) { r = (rgb >> 16 & 255) / 255.0F;
                                         g = (rgb >> 8 & 255) / 255.0F;
                                         b = (rgb & 255) / 255.0F;
                                         return this; }
    final public Color setRGBE(int rgbe) { float f = EXPONENT[rgbe & 255];
                                           r = f * ((rgbe >>> 24) + 0.5F);
                                           g = f * ((rgbe >> 16 & 255) + 0.5F);
                                           b = f * ((rgbe >> 8 & 255) + 0.5F);
                                           return this; }
    final public boolean isBlack() { return r <= 0 && g <= 0 && b <= 0; }
    final public float getLuminance() { return 0.2989F * r + 0.5866F * g +
                                          0.1145F *
                                          b; }
    final public float getMin() { return MathUtils.min(r, g, b); }
    final public float getMax() { return MathUtils.max(r, g, b); }
    final public float getAverage() { return (r + g + b) / 3.0F; }
    final public float[] getRGB() { return new float[] { r, g, b }; }
    final public int toRGB() { int ir = (int) (r * 255 + 0.5);
                               int ig = (int) (g * 255 + 0.5);
                               int ib = (int) (b * 255 + 0.5);
                               ir = MathUtils.clamp(ir, 0, 255);
                               ig = MathUtils.clamp(ig, 0, 255);
                               ib = MathUtils.clamp(ib, 0, 255);
                               return ir << 16 | ig << 8 | ib; }
    final public int toRGBE() { float v = MathUtils.max(r, g, b);
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
    final public Color constrainRGB() { float w = -MathUtils.min(0, r, g,
                                                                 b);
                                        if (w > 0) { r += w;
                                                     g += w;
                                                     b += w; }
                                        return this; }
    final public boolean isNan() { return Float.isNaN(r) || Float.isNaN(g) ||
                                     Float.
                                     isNaN(
                                       b); }
    final public boolean isInf() { return Float.isInfinite(r) || Float.isInfinite(
                                                                         g) ||
                                     Float.
                                     isInfinite(
                                       b); }
    final public Color add(Color c) { r += c.r;
                                      g += c.g;
                                      b += c.b;
                                      return this; }
    final public static Color add(Color c1, Color c2) { return Color.
                                                          add(
                                                            c1,
                                                            c2,
                                                            new Color(
                                                              ));
    }
    final public static Color add(Color c1, Color c2, Color dest) {
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
    final public Color madd(float s, Color c) { r += s * c.r;
                                                g += s * c.g;
                                                b += s * c.b;
                                                return this; }
    final public Color madd(Color s, Color c) { r += s.r * c.r;
                                                g += s.g * c.g;
                                                b += s.b * c.b;
                                                return this; }
    final public Color sub(Color c) { r -= c.r;
                                      g -= c.g;
                                      b -= c.b;
                                      return this; }
    final public static Color sub(Color c1, Color c2) { return Color.
                                                          sub(
                                                            c1,
                                                            c2,
                                                            new Color(
                                                              ));
    }
    final public static Color sub(Color c1, Color c2, Color dest) {
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
    final public Color mul(Color c) { r *= c.r;
                                      g *= c.g;
                                      b *= c.b;
                                      return this; }
    final public static Color mul(Color c1, Color c2) { return Color.
                                                          mul(
                                                            c1,
                                                            c2,
                                                            new Color(
                                                              ));
    }
    final public static Color mul(Color c1, Color c2, Color dest) {
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
    final public Color mul(float s) { r *= s;
                                      g *= s;
                                      b *= s;
                                      return this; }
    final public static Color mul(float s, Color c) { return Color.
                                                        mul(
                                                          s,
                                                          c,
                                                          new Color(
                                                            )); }
    final public static Color mul(float s, Color c, Color dest) {
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
    final public Color div(Color c) { r /= c.r;
                                      g /= c.g;
                                      b /= c.b;
                                      return this; }
    final public static Color div(Color c1, Color c2) { return Color.
                                                          div(
                                                            c1,
                                                            c2,
                                                            new Color(
                                                              ));
    }
    final public static Color div(Color c1, Color c2, Color dest) {
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
    final public Color exp() { r = (float) Math.exp(r);
                               g = (float) Math.exp(g);
                               b = (float) Math.exp(b);
                               return this; }
    final public Color opposite() { r = 1 - r;
                                    g = 1 - g;
                                    b = 1 - b;
                                    return this; }
    final public Color clamp(float min, float max) { r = MathUtils.
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
    final public static Color blend(Color c1, Color c2, float b) {
        return Color.
          blend(
            c1,
            c2,
            b,
            new Color(
              ));
    }
    final public static Color blend(Color c1, Color c2, float b, Color dest) {
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
    final public static Color blend(Color c1, Color c2, Color b) {
        return Color.
          blend(
            c1,
            c2,
            b,
            new Color(
              ));
    }
    final public static Color blend(Color c1, Color c2, Color b, Color dest) {
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
    final public static boolean hasContrast(Color c1, Color c2, float thresh) {
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
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1170389086000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAMWcC5QUxbmAa6b3vYu7LPIUgUUQ5THDLgEFfGRYBlwZlnV5" +
                                                   "rDzM2tvTs9vQ0912\n9yzDhhDUEzCS3EQl517PjWgSclQSo54kkocPPJqESH" +
                                                   "JRz41JzMHoMdHkGOPN8VxjHib5/+qa7d6e\nmWJ2eyR7Ttf0dFVX/d9ff/1V" +
                                                   "f033fv1tUmmZZLpkRew9hmxF2jd2iaYlJ9tV0bI2waVe6QeVtV33\nr9P0MA" +
                                                   "klSFhJ2qQxIVnRpGiLUSUZ7Vi9MmuSBYau7ulXdTsiZ+3ITnUpq+/axNK8Cn" +
                                                   "uOHG+++WjF\nzDCpTJBGUdN0W7QVXYurctqySVNipzgoRjO2okYTimWvTJBx" +
                                                   "spZJt+uaZYuabd1E9hEhQaoMCeu0\nSUsi13gUGo8aoimmo7T5aBdtFmqYYM" +
                                                   "q2qGhyMjbcHNy5cOSdIDa7rzu/NFRSg5lbAIdKANSzhqkd\n2jxUQ3hgy7K9" +
                                                   "9z0okMZtpFHRNmJlEpDY0N420pCW032yacWSSTm5jYzXZDm5UTYVUVWGaKvb" +
                                                   "SLOl\n9GuinTFlq1u2dHUQCzZbGUM2aZu5iwnSICGTmZFs3RzWUUqR1WTuW2" +
                                                   "VKFfsBe5KL7eCuwesAWKeA\nYGZKlOTcLRW7FA16fKb/jmHGOeugANxanZbt" +
                                                   "AX24qQpNhAuk2elLVdT6oxttU9H6oWilnoFWbDKt\naKWoa0OUdon9cq9Npv" +
                                                   "jLdTlZUKqWKgJvsclEfzFaE/TSNF8vefpnwaT3bnvgi09+lNp2RVKWVJS/\n" +
                                                   "Dm6a4bupW07JpqxJsnPj+5nI4Y6tmelhQqDwRF9hp0xs7vHNid89NdMpc0GB" +
                                                   "Mhv6dsqS3St13jGz\n++NrdSKgGDWGbinY+SPI6XDoYjkrswaM2knDNWJmJJ" +
                                                   "d5ovuHW/cfk98Kk7oOUiXpaiYNdjRe0tOG\nosrmWlmTTdGWkx2kVtaS7TS/" +
                                                   "g1TDeQJM3rm6IZWyZLuDVKj0UpVOv4OKUlAFqqgWzhUtpefODdEe\noOdZgx" +
                                                   "AyDg7SDEclcf7op03mR6JWRkup+u6oZUpR3ewf/q6koUejII1uRtBmDJusiQ" +
                                                   "7oaTkqSqKm\naHq0X4FRKumLkvIgfpZcUxblat4dCqGj8w9YFWz9Gl1Nymav" +
                                                   "dP/rz+2Nr/v0bY4xoAEzIptMhgYi\nrIEIbSBCGyChEK33fGzI6QZQ4i4Yju" +
                                                   "C4Gi7deMO1N942W4D+N3ZXoBqg6GyQnbUel/R2d8x2UPcm\ngeFM+fL2g5H3" +
                                                   "77/aMZxocdda8O765x86dd+7DfPDJFzY7yEVeN46rKYLneWwP5vjHymF6v/j" +
                                                   "7eu/\n+dKpM5e4Y8Ymc/KGcv6dOBRn+/Vv6pKcBOfmVn90aqPQQ7bcESYVML" +
                                                   "7Bp1H5wV3M8LcxYkiuzLk3\nZKlOkPqUbqZFFbNyPqnOHjD13e4VahhN9HwC" +
                                                   "dE492mgTHPOZ0dJPzJ1oYDrJMSTsbR8FdZ/v31q1\n+OeP1/+AqiXnaRs9c9" +
                                                   "lG2XbG7XjXWDaZsgzXz/xX111fePvgdmopjqmEbJjgMn2qImXhlovdW8Du\n" +
                                                   "VHAa2JFzNmtpPamkFLFPldHi/t44t/Xbf/iPJqdrVLiS69mFZ6/AvT51Fdl/" +
                                                   "6mN/nkGrCUk4YbgY\nbjGHZoJbc8w0xT0oR/bmFy+8+0fiPeDPwIdYypBM3Q" +
                                                   "KhZITqMUr1Pp+mEV9eKyazoe6FRUy/wPTc\nK+091j87c9OPv0ulrhe987y3" +
                                                   "G9aLxkqn5zG5CLU72T96rxGtASj3kROdO5rUE3+DGrdBjRJMi9YG\nE3xFdk" +
                                                   "QnstKV1S8//cykG18QSHgNqVN1MblGpPZPasHwZGsA3EzWuPqj1Laadtcway" +
                                                   "NZQpUwjSmA\nfpmeb5WLmFUuKmiVmFzsU2nIMSQAnOJd35lKGuaJQTpqXj8w" +
                                                   "+4mTm+896HiaSzmLOO9dvdInf/3q\nLuFzT/c59/nnSl/hO2YcfeObr3ef71" +
                                                   "ils6C4KG9O997jLCooWaOBPdTCa4GWfnZBy9f3db/CJGoe\nOTXGYfn45p5n" +
                                                   "5HlXfPa1At4dRqsu2rS5KzhmuRaT5TSrDZMVTl8tHXOXtrIubS25SwVao4Bf" +
                                                   "Y25C\ny3dyJO/CZJ0reSKo5HEmeXzUxvgJTMlUj0F26hpdkygSdmY2NxxxzR" +
                                                   "gx5RQ6G5wnsntem/fyrJ80\ntZ9y3NKATeZ6VpesZLRDG9QlOu6vEbUkrHcc" +
                                                   "LzW9YIM9pmjA+vH5V9+44XML3vwh2o5BEXo4yuzF\nZJOrzM1BlbmOKXPdqJ" +
                                                   "WJ3680nIpX2USA5Tu9KcURfxcmkit+slTxs55v4yyut1iDUYc7pff2LXwg\n" +
                                                   "8dyGe2jHFV2RFHAkvnqGntx85P2f2q/QetylAd7dks1f3EGk5t57+UuD46se" +
                                                   "uTcdJtXbSJPEYskt\noprBCXgbhD5WLsCEeHNE/sgwxlmzrxxe+kz3ezJPs/" +
                                                   "5Fiet24BxL43mDbx3SgNqeBkcVs4sqv12E\nCD3J0Fvm0HTe8Kqh2jCVQRHj" +
                                                   "SxIyh31Es9vhg2frcGP0onwCk93QYn+hFvd9CC3ekmuxr1CLt5be\n4hS8uh" +
                                                   "yOatZidZEWD+arW8C5Q9FEGjVeCis2i8b3oPrKVYlYu+Nxr6fpDp+Et41Bwh" +
                                                   "omYU0RCe8s\nICGeH6LiYfIZFK3nmo5NcY5od41StMvgqGWi1RYR7b9LEk3o" +
                                                   "jq/mCPbFMeisjglWV0Swo6XpbG13\nPN7JEe2roxTtcsImgNxnAdEeKkm0il" +
                                                   "WJzbze/MYoJVuRG4W5zwKSPVaSZFVb44nEhh6ObMfHoLVx\nTLZxRWR7qjSt" +
                                                   "tW+N8frzxCglWwnHeUyy84pIdrIkyarXx9bGOzfFOML9eJTCxeBoZMI1FhHu" +
                                                   "hdLU\ntrY7tpUj2YulSzY1p7apTLKpRSR7mSfZnpxkNfHruzZ0guIwPvcs9m" +
                                                   "hMigvLBz+/ekL38u230m2R\nWlFVRKvTnY3DShLPQrCMmFt8XTNcWa8074bj" +
                                                   "//f0k/I8GhvWKBbM+jGzv8Buo+eeP4nH5PU/Tx2h\nWxsVfaLlzP/+bdr8Xd" +
                                                   "gRm6t0uXCes06NGYZBAJ7itC6+DOCbAR735iNKkq2fVr+4pu9YSjuWDNPa\n" +
                                                   "aVsxvIGx19IrHmUIumHh5qNnl5/VNGeDYeE+zjhPIx2r9z5ybUPNlw4doPUz" +
                                                   "TdZ6NjLZ9+pB0ez0\nxl31VOy2JcsWL19sk67ybPetADUsbL18Udsymywpsm" +
                                                   "0XyRnLPGuWLZr9sj1r+BJK97rHnvaZZFK+\nSlERzG5xoxNUcp4btWC46c0E" +
                                                   "1IrueGy1b7D8ijNYsvlGH3aHo3cpTjA+vrDYFjeNjQ9e/6eGA+Kz\nN4RZCN" +
                                                   "Buk1pbNxap8qCseqqKYE0jtifXU7tzV9G3P/i14/YLC1Y4Ufb84iPFf+P8Ff" +
                                                   "cNzVzx8KEx\nbErO9LH5qx4/eMF1woByklofW5Tn/V4x8qaVI5fidSBPxtQ2" +
                                                   "jViQzxr2VLiRTWbCMZ716Xi/p3I7\nNr/HmO8sHpGFwpy8Crz4ASxA+lRR2u" +
                                                   "X1vK4d/WNUAehf8rkmMq6J5eVq4uRhWBuqB67dA4otF+QK\nNYyRi66nZhBn" +
                                                   "YUpyn3lcmPgDa6+I0zl5MzCZCh7M1jt1DX83Yb8K5kFMCwJxARxXMYirxgRx" +
                                                   "CSdv\nPiZzwdXZOo/g4iAEk+G4jhFcNyaCj3DylmHSCt5V0o09haVvG6P0dI" +
                                                   "wvhWMzk37zaAaH102XsnsX\nWsWhxHkjdCVER5ZsF4a8KgjkRXDsYJA7gkN6" +
                                                   "96aYoJSii0OIk25oHY9wdDuWPsKL4ZAYoVQ2Qvza\nT8W/kYOG2xOh7bg1IN" +
                                                   "vda1cVptsRhO4SOHYyup0fAp3GocNWQgoELQ5dvDDeziB4s+EwGZ4ZHM8r\n" +
                                                   "/V5O3j5MdgOZYq0anno9e63VfbquyqLmUmaDUM6FY5BRDpaX8hAn77OYHLBJ" +
                                                   "AyyBE5m0ouHqiHol\nl+tgEC5cXgwxrqHycv0nJ+9uTO6EUQdc6xXNR3TWDa" +
                                                   "6zEe1jRPvKS/QVTt5XMTnCiJxnGjxE9wad\nAG5hRLeUl+hhTt6jmByzSR0Q" +
                                                   "xQZlE6IzH9XXglC1wHGAUR0oL9X3OXlPYPKY0085f//rgsH65b5g\nPaFLot" +
                                                   "qx+ktP1794R2bZtblg7Q2s8IRNNgUJj0GSjYYoySxCbr1sYVvboiVt80EaKe" +
                                                   "KRlKr9rLty\nPLVPh+N2pvbby6v2n3LyTmNyEuIJWwcWLNHvAp11v4wHdCEc" +
                                                   "hxnQ4fIC/ZKT9ytM/hfsiALFfUQ/\nCzrXPMqIHi0v0W85eW9i8qqde05RVL" +
                                                   "Si66HXguDNguNxhvd4efHe5eT9PyZ/BAtUrE4RZ5zQfhfo\nnaBAJxjQifIC" +
                                                   "/bN4XhiXgKG/UqAOLeUD+lvQCedZBvRscKAiEUe4ngPXiEkVRBxiMlnQBsPV" +
                                                   "YySk\nO+4YOJ5ihKdKJMzbbveJH3b0kCetF/kCDnILJpN4yJODIOOPDKcZ8u" +
                                                   "kyIbOAuRhyHv4iDv5STC7h\n4V8axKbxsayXGP5LwW067BaI5WHGOJg4U4Sv" +
                                                   "sElFuijnlUE5zzDOM2Xj5PbqdRzcHkwSXNz1QV3V\nbxjub4LjFnNVEgcRH6" +
                                                   "kIfww3RzJ9hQl7g7qqtxjhW2Uat6X0qsVBxl/1whoPWQ/qqt5hyO+UCXm0\n" +
                                                   "rupTHHyMysP7efg3B7Xp9xj+ex+eTd/NIbwHk8NAmM6ohQm/ENSmP2CEH5xD" +
                                                   "m36Qg/wwJkd5yGd9\nhuQsNh0KOcTO57/BpjlxcfgZTB7j4QcKO8GmQ+zxqF" +
                                                   "De41Fjtmn8Svfnw6c5aC9g8hwP7VSQnl0C\ngrCnN0J5T28EM2bKl9eNZzis" +
                                                   "r2HyCx7rL4OwXg2MExjrhPJacR5rHvfbHO53Mfkdj/v3Qc13GuOe\nVjbzzS" +
                                                   "PkhHsCKigM4Z6QVAYLE4415su55FALI2wprxXzOlVo5CBPxKSOgyzUB3XJcx" +
                                                   "ny3PIac6ku\nWWjh4GMLwnQe/oUBdwJDCxn+wuA27ZV8KSfvMkwWA5WcNQpT" +
                                                   "tQbcVg4tYVRLykvVzsnDsE64yiY1\nukGfqir8yIRwdRC0NkBawdBWBEfzzj" +
                                                   "E0oSCcYE7YiAkEc5WSKqaLdN5Yozk6IuGeUIwRxs7NiHTJ\neznk+MuHsI0+" +
                                                   "5iNrheNYYXsQ8gQQs5d2QoVf2hk9eQUtVcElz3NIN3G0gGGfoPK1kA7a/wmm" +
                                                   "hcS5\n6f88BXACPwEDP2E/XwFjDf2GzaCLKaDr3JgBVxmcGFH4CiaH+coIFC" +
                                                   "WuByX0MGX0nGtvwPllVPg2\nJsdsUj8g4ktJtinSF3k9++/CWH8apQ9l4cws" +
                                                   "MnCxIDgmnIeyhCc4eU9h8j36WJnzLx9oqck2aXKf\nkPVkUJzvl4qD72LQR3" +
                                                   "jxpb4pef9+w/mXEVLi5Y/veC/xs784D1rn/q1DfYLUpDKq6n0Jy3NeZZhy\n" +
                                                   "SqH89TRtoj/bCidtMj7vOWL8WQQ/UTjhR07BU9BjnoL4VIxz5i30P7AygUJ4" +
                                                   "etoooBnn9bLsCGhk\nvWjEs7b0f5zknofNOP/lpFe6/qHts7KHNn2ePmSLk+" +
                                                   "jQEFZTlyDVztvwtFZ8pralaG25up4njzy8\n5fFvLM/9DE3flj7fMyxGGFib" +
                                                   "k8vpRJPMLPwKejxt2PSl8aHvTP7WFfcfeYW+hWn8C1L2ZEeaRgAA\n");
}
