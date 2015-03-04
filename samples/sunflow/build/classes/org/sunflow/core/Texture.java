package org.sunflow.core;
import java.io.IOException;
import org.sunflow.image.Bitmap;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class Texture {
    private String filename;
    private boolean isLinear;
    private Bitmap bitmap;
    private int loaded;
    Texture(String filename, boolean isLinear) { super();
                                                 this.filename = filename;
                                                 this.isLinear = isLinear;
                                                 loaded = 0; }
    private synchronized void load() { if (loaded != 0) return;
                                       try { UI.printInfo(Module.TEX, "Reading texture bitmap from: \"%s\" ...",
                                                          filename);
                                             bitmap = new Bitmap(
                                                        filename,
                                                        isLinear);
                                             if (bitmap.getWidth(
                                                          ) == 0 ||
                                                   bitmap.
                                                   getHeight(
                                                     ) ==
                                                   0) bitmap = null;
                                       }
                                       catch (IOException e) {
                                           UI.
                                             printError(
                                               Module.
                                                 TEX,
                                               "%s",
                                               e.
                                                 getMessage(
                                                   ));
                                       }
                                       loaded = 1; }
    public Bitmap getBitmap() { if (loaded == 0) load();
                                return bitmap; }
    public Color getPixel(float x, float y) { Bitmap bitmap = getBitmap(
                                                                );
                                              if (bitmap == null)
                                                  return Color.
                                                           BLACK;
                                              x = x - (int) x;
                                              y = y - (int) y;
                                              if (x < 0) x++;
                                              if (y < 0) y++;
                                              float dx = (float) x *
                                                (bitmap.
                                                   getWidth(
                                                     ) -
                                                   1);
                                              float dy = (float) y *
                                                (bitmap.
                                                   getHeight(
                                                     ) -
                                                   1);
                                              int ix0 = (int) dx;
                                              int iy0 = (int) dy;
                                              int ix1 = (ix0 + 1) %
                                                bitmap.
                                                getWidth(
                                                  );
                                              int iy1 = (iy0 + 1) %
                                                bitmap.
                                                getHeight(
                                                  );
                                              float u = dx - ix0;
                                              float v = dy - iy0;
                                              u = u * u * (3.0F -
                                                             2.0F *
                                                             u);
                                              v = v * v * (3.0F -
                                                             2.0F *
                                                             v);
                                              float k00 = (1.0F -
                                                             u) *
                                                (1.0F -
                                                   v);
                                              Color c00 = bitmap.
                                                getPixel(
                                                  ix0,
                                                  iy0);
                                              float k01 = (1.0F -
                                                             u) *
                                                v;
                                              Color c01 = bitmap.
                                                getPixel(
                                                  ix0,
                                                  iy1);
                                              float k10 = u * (1.0F -
                                                                 v);
                                              Color c10 = bitmap.
                                                getPixel(
                                                  ix1,
                                                  iy0);
                                              float k11 = u * v;
                                              Color c11 = bitmap.
                                                getPixel(
                                                  ix1,
                                                  iy1);
                                              Color c = Color.mul(
                                                                k00,
                                                                c00);
                                              c.madd(k01, c01);
                                              c.madd(k10, c10);
                                              c.madd(k11, c11);
                                              return c; }
    public Vector3 getNormal(float x, float y, OrthoNormalBasis basis) {
        float[] rgb =
          getPixel(
            x,
            y).
          getRGB(
            );
        return basis.
          transform(
            new Vector3(
              2 *
                rgb[0] -
                1,
              2 *
                rgb[1] -
                1,
              2 *
                rgb[2] -
                1)).
          normalize(
            );
    }
    public Vector3 getBump(float x, float y, OrthoNormalBasis basis,
                           float scale) { Bitmap bitmap = getBitmap(
                                                            );
                                          if (bitmap == null) return basis.
                                                                transform(
                                                                  new Vector3(
                                                                    0,
                                                                    0,
                                                                    1));
                                          float dx = 1.0F / (bitmap.
                                                               getWidth(
                                                                 ) -
                                                               1);
                                          float dy = 1.0F / (bitmap.
                                                               getHeight(
                                                                 ) -
                                                               1);
                                          float b0 = getPixel(x, y).
                                            getLuminance(
                                              );
                                          float bx = getPixel(x +
                                                                dx,
                                                              y).
                                            getLuminance(
                                              );
                                          float by = getPixel(x, y +
                                                                dy).
                                            getLuminance(
                                              );
                                          return basis.transform(
                                                         new Vector3(
                                                           scale *
                                                             (bx -
                                                                b0) /
                                                             dx,
                                                           scale *
                                                             (by -
                                                                b0) /
                                                             dy,
                                                           1)).normalize(
                                                                 );
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYXWwcVxW+Hq/XP3Gz9rr5qZs4seNUOGl3CBCgOGpIFrtx" +
                                                    "2MRW7Ebgim7uzt5dTzx/nblrb1xc2kjIUSpFFXVDilKrKqlKS9pUiKggVCkv" +
                                                    "0FblpQiBeKBFvFBR8pAHSkWBcs6dmZ3Z2Z+EB1aau3fvvefvnnO+c2YvXydt" +
                                                    "jk12W6Z2qqiZPMXKPHVS25vipyzmpA5n9k5R22H5tEYdZwbWssrQa4mPPnly" +
                                                    "rkci8VnSRw3D5JSrpuEcY46pLbB8hiSC1TGN6Q4nPZmTdIHKJa5qckZ1+GiG" +
                                                    "rAuRcjKc8VWQQQUZVJCFCvKB4BQQ3caMkp5GCmpw52HyKGnJkLiloHqcDFYz" +
                                                    "sahNdY/NlLAAOHTg7+NglCAu22R7xXbX5hqDn94tr37/oZ6ftJLELEmoxjSq" +
                                                    "o4ASHITMkm6d6TlmOwfyeZafJb0GY/lpZqtUU5eE3rMk6ahFg/KSzSqXhIsl" +
                                                    "i9lCZnBz3QraZpcUbtoV8woq0/L+r7aCRotg68bAVtfCcVwHA7tUUMwuUIX5" +
                                                    "JLF51chzsi1KUbFx+OtwAEjbdcbnzIqomEFhgSRd32nUKMrT3FaNIhxtM0sg" +
                                                    "hZP+hkzxri2qzNMiy3KyOXpuyt2CU53iIpCEkw3RY4ITeKk/4qWQf64f3Xfu" +
                                                    "EeOQIQmd80zRUP8OIBqIEB1jBWYzQ2EuYfeuzHm68Y0zEiFweEPksHvm9W/f" +
                                                    "+OrdA9fecs/cWefMZO4kU3hWuZRb/+6W9Mi9rahGh2U6Kjq/ynIR/lPezmjZ" +
                                                    "gszbWOGImyl/89qxX33zsZfZhxLpmiBxxdRKOsRRr2Lqlqox+35mMJtylp8g" +
                                                    "nczIp8X+BGmHeUY1mLs6WSg4jE+QmCaW4qb4DVdUABZ4Re0wV42C6c8tyufE" +
                                                    "vGwRQtrhId3wJIj7Ed+cZOU5U2cyVaihGqYMscuorczJTDGzNrNMeSw9Kefg" +
                                                    "lud0as87slMyCpq5mFVKDjd12bEV2bSL/rKsmDaTZyCTIDtSGGjW/19EGa3s" +
                                                    "WWxpAQdsiaa/BplzyNTyzM4qq6WDYzdezb4jVdLBux9wK0hIeRJSKCHlSSAt" +
                                                    "LYLx7SjJ9Sr4ZB6yG3Cve2T6W4dPnBlqhXCyFmNwoXh0CAzzxI8pZjqAgAkB" +
                                                    "dArE4ebnH1xJffzifjcO5cZ4XZeaXLuw+Pjx73xWIlI18KI5sNSF5FMIlxVY" +
                                                    "HI4mXD2+iZUPPrpyftkMUq8KyT1EqKXEjB6KXrxtKiwPFxiw37WdXs2+sTws" +
                                                    "kRjABEAjpxDKgDoDURlVmT3qoyTa0gYGF0xbpxpu+dDWxedsczFYERGxXsx7" +
                                                    "wSnoGLIOnju92BffuNtn4Xi7G0Ho5YgVAoXHf37tmas/2H2vFAbsRKgETjPu" +
                                                    "pn9vECQzNmOw/scLU089fX3lQREhcGJHPQHDOKYBDMBlcK3ffevhP7z/3qXf" +
                                                    "SpWoImUgvStgDgihAUqhy4cfMHQzrxZUmtMYxuS/Ejv3XP3buR7XiRqs+DFw" +
                                                    "980ZBOt3HCSPvfPQPwYEmxYFK1RgcHDMtbsv4HzAtukp1KP8+G+2PvMmfRYA" +
                                                    "FEDLUZeYwCFJGCQJ32yATkJQYjFKucUIuI00aV9sVQdEXfAgX15Ovj9/8YNX" +
                                                    "3DSK1ofIYXZm9eynqXOrUqiI7qipY2Eat5CK4LjNDaZP4dMCz3/wQcfggguk" +
                                                    "ybSH5tsrcG5Z6LfBZmoJEeN/ubL8ix8tr7hmJKtryBi0SK/87t+/Tl3409t1" +
                                                    "gKs9Z5oao4bQMiW0HBHjPaiWFz34ex8O262avbJY2Sx+xZrf/jh2LiEQ++ek" +
                                                    "ljv954+FVjUwVMchEfpZ+fLF/vR9Hwr6AA+Qelu5FsqhywtoP/ey/ndpKP5L" +
                                                    "ibTPkh7FayGPU62EWTcLbZPj95XQZlbtV7dAbr0freDdlmhEhMRGkSjwBMzx" +
                                                    "NM67IuCDNZdsgafHA5+eKPi0EDH5miAZEuNOHD7jVhTwsWWrCxT7U9KB1d4X" +
                                                    "KwvYcl26v1rgVnh6PYG9DQQexmEceKoOthlU3PwXGvPshyfp8Uw24HnE4xnP" +
                                                    "qVynlp/mVeVV1aFTTB0UBxpL2wRPnyetr4G0Y740zaTQrgsGe3DY60b1lzhp" +
                                                    "hf7ZFVJueL9xq5TTVCWcCQJxtzbqUkXWXjq9upaffGGP5CXYlznp5KZ1j8YW" +
                                                    "mBZihW9kW6tagiOiLw+C+exLP36dv7v7K27+72qcgFHCN0//tX/mvrkT/0Mj" +
                                                    "sC1iU5TlS0cuv33/Xcr3JNJayYmaV41qotHqTOiyGTROxkxVPgxUnHsH3u4w" +
                                                    "PIOecwfrF+O6zpJweoiTbucUtIu2aUBZyZeboN7JJnvixQKKQAyjp17sxBZM" +
                                                    "NV+LmGLhRMWgpN9RyJ5Bcl2DcPhGE21KTfYWcYBGqbPIeJA3UzdVTWQ+Xvh+" +
                                                    "T7X9t6xauE5HrgUw0KQiqR4V9MtNND+NwxJgDGg+pZa9xABE2FSLCNBVmPZN" +
                                                    "berz8TTn2ZS7ZZtaBcdWobkYfGUGw8ro8N6UmrQhwo+KRvMgdVRHMD3bxNBz" +
                                                    "OKy4LnLp6mKfYH6cYff8+VszFVG36JlavGVTY249D0zF4YnAZ+ebmHIBh6eg" +
                                                    "7GC0lXQh6sk6jQOc8F6SsAfcXPPHi/tngfLqWqJj09oDvxdtf+WFvhPeqgsl" +
                                                    "TQuX0NA8btmsoAp1Ot2CaomvZ6FljL6qQaLil1DwonvsOU7WhY6Bpt4sfOiH" +
                                                    "UB3gEE4vVSpVqCF1W4MyqSoLVrRI7KiCa/EnlQ+tJfdvqqxyZe3w0UdufPEF" +
                                                    "gdNtikaXlpBLR4a0F4JYQXgebMjN5xU/NPLJ+tc6d/plZz0OyRBohnTbVr/j" +
                                                    "H9MtLnr0pZ9t+um+F9feE28a/wXW+84aPRQAAA==");
}
