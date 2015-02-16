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
    public static final long jlc$SourceLastModified$jl7 = 1170615980000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYXWxcRxUeX6/XP3Gz9rr5qZs4seNUOGn3EiBAcdSQGLtx" +
                                                    "2MRW7ARwRZ3Zu7PrG9+/3jtrb1xc2kjIUZCiirohRamFIFVpSZsKERWEKuUF" +
                                                    "2qq8FCEQD7SIFypKHvJAqShQzpn7u3d/Eh5Y6c7Ozsz5m3POd87dKzdIi2OT" +
                                                    "3ZapnS5qJs+wMs+c0vZm+GmLOZnD2b2T1HZYfkSjjjMNa7PKwCupDz56cq5L" +
                                                    "IskZ0kMNw+SUq6bhHGOOqS2wfJakwtVRjekOJ13ZU3SByiWuanJWdfhwlqyL" +
                                                    "kHIymPVVkEEFGVSQhQrygfAUEN3BjJI+ghTU4M4j5DHSlCVJS0H1OOmvZGJR" +
                                                    "m+oem0lhAXBow98nwChBXLbJ9sB21+Yqg5/eLa9+9+GunzST1AxJqcYUqqOA" +
                                                    "EhyEzJBOnek5ZjsH8nmWnyHdBmP5KWarVFOXhN4zJO2oRYPyks2CS8LFksVs" +
                                                    "ITO8uU4FbbNLCjftwLyCyrS8/6uloNEi2LoxtNW1cAzXwcAOFRSzC1RhPkli" +
                                                    "XjXynGyLUwQ2Dn4ZDgBpq874nBmIShgUFkja9Z1GjaI8xW3VKMLRFrMEUjjp" +
                                                    "rcsU79qiyjwtsllONsfPTbpbcKpdXASScLIhfkxwAi/1xrwU8c+No/vOP2oc" +
                                                    "MiShc54pGurfBkR9MaJjrMBsZijMJezclb1AN752ViIEDm+IHXbPvPqNm1+8" +
                                                    "t+/6G+6Zu2ucmcidYgqfVS7n1r+9ZWTo/mZUo80yHRWdX2G5CP9Jb2e4bEHm" +
                                                    "bQw44mbG37x+7Fdfe/xF9r5EOsZJUjG1kg5x1K2YuqVqzH6QGcymnOXHSTsz" +
                                                    "8iNif5y0wjyrGsxdnSgUHMbHSUITS0lT/IYrKgALvKJWmKtGwfTnFuVzYl62" +
                                                    "CCGt8JBOeFLE/YhvTr4iH3cg3GWqUEM1TBmCl1FbmZOZYs7m4HbndGrPO7JS" +
                                                    "cripy07JKGjmouzYimzaxeC3YtpMnoYMgqzIYIBZ/z/WZbSqa7GpCS58Szzd" +
                                                    "NciUQ6aWZ/asslo6OHrz5dm3pCD8vfsAN4KEjCchgxIyngTS1CQY34mSXC+C" +
                                                    "D+YhmwHnOoemvn745NmBZggfazEBF4hHB8AeT/yoYo6EKT8ugE2BuNv8g4dW" +
                                                    "Mh8+v9+NO7k+PtekJtcvLj5x4puflIhUCbRoDix1IPkkwmMAg4PxBKvFN7Xy" +
                                                    "3gdXLyybYapVILeHANWUmMED8Yu3TYXl4QJD9ru202uzry0PSiQBsABQyCmE" +
                                                    "LqBMX1xGRSYP+6iItrSAwQXT1qmGWz6UdfA521wMV0RErBfzbnAKOoasg+du" +
                                                    "L9bFN+72WDje6UYQejlmhUDdsZ9ff+ba93bfL0UBOhUpeVOMu+neHQbJtM0Y" +
                                                    "rP/x4uRTT99YeUhECJzYUUvAII4jkPzgMrjWb73xyB/efefyb6UgqkgZSO8J" +
                                                    "mQMiaIBK6PLB44Zu5tWCSnMaw5j8V2rnnmt/O9/lOlGDFT8G7r01g3D9roPk" +
                                                    "8bce/kefYNOkYEUKDQ6PuXb3hJwP2DY9jXqUn/jN1mdep88CYAJIOeoSE7gj" +
                                                    "CYMk4ZsN0DkISiw+Gbf4ALehBu2KreqAoAsexMvL6XfnL733kptG8XoQO8zO" +
                                                    "rp77OHN+VYoUzR1VdStK4xZOERx3uMH0MXya4PkPPugYXHCBMz3ioff2AL4t" +
                                                    "C/3W30gtIWLsL1eXf/Gj5RXXjHRlzRiFluil3/3715mLf3qzBnC15kxTY9QQ" +
                                                    "WmaElkNivA/V8qIHf+/DYbtVtVcWK5vFr0Tj2x/DTiUCYv+c0HJn/vyh0KoK" +
                                                    "hmo4JEY/I1+51DvywPuCPsQDpN5WroZy6OpC2k+9qP9dGkj+UiKtM6RL8VrG" +
                                                    "E1QrYdbNQJvk+H0ktJUV+5Utj1vfhwO82xKPiIjYOBKFnoA5nsZ5Rwx8sMaS" +
                                                    "LfB0eeDTFQefJiImXxIkA2LcicMn3IoCPrZsdYFiP0rasLr7YmUBW65L91cK" +
                                                    "3ApPtyewu47AwziMAU/VwbaCipv/TH2evfCkPZ7pOjyPeDyTOZXr1PLTvKK8" +
                                                    "qjp0hpmD4kB9aZvg6fGk9dSRdsyXppkU2nPBYA8Oe92o/hwnzdAvu0LKde83" +
                                                    "aZVymqpEM0Eg7tZ6XanI2stnVtfyE8/tkbwE+zwn7dy07tPYAtMirPANbGtF" +
                                                    "S3BE9OFhMJ974cev8rd3f8HN/131EzBO+PqZv/ZOPzB38n9oBLbFbIqzfOHI" +
                                                    "lTcfvEf5jkSag5yoerWoJBquzIQOm0HjZExX5ENf4Ny78HYH4en3nNtfuxjX" +
                                                    "dJaE00OcdDqnoV20TQPKSr7cAPVONdgTLxJQBBIYPbViJ7FgqvlqxBQLJwOD" +
                                                    "0n5HIXsGyTUNwuGrDbQpNdhbxAEapfYi42HeTN5SNZH5eOH7PdX237Zq0Tod" +
                                                    "uxbAQJOKpHpM0C830PwMDkuAMaD5pFr2EgMQYVM1IkBXYdq3tKnHx9OcZ1Pu" +
                                                    "tm1qFhybheZi8JXpjyqjw3tSZsKGCD8qGs2D1FEdwfRcA0PP47Diusilq4l9" +
                                                    "gvkJht3zp2/PVETdomdq8bZNTbj1PDQVh2+HPrvQwJSLODwFZQejraQLUU/W" +
                                                    "aBzghPeShD3g5qo/Wtw/B5SX11Jtm9aO/160/cELfDu8RRdKmhYtoZF50rJZ" +
                                                    "QRXqtLsF1RJfz0LLGH9Vg0TFL6HgJffY9zlZFzkGmnqz6KEfQnWAQzi9HFSq" +
                                                    "SEPqtgZlUlEWrHiR2FEB1+JPKR9aS+7fUrPK1bXDRx+9+dnnBE63KBpdWkIu" +
                                                    "bVnSWghjBeG5vy43n1fy0NBH619p3+mXnfU4pCOgGdFtW+2Of1S3uOjRl362" +
                                                    "6af7nl97R7xp/Bc8caLmLRQAAA==");
}
