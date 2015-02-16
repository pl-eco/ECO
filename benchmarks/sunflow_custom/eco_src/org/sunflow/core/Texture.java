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
                                             if (bitmap.getWidth() ==
                                                   0 ||
                                                   bitmap.
                                                   getHeight() ==
                                                   0) bitmap = null;
                                       }
                                       catch (IOException e) {
                                           UI.
                                             printError(
                                               Module.
                                                 TEX,
                                               "%s",
                                               e.
                                                 getMessage());
                                       }
                                       loaded = 1; }
    public Bitmap getBitmap() { if (loaded == 0) this.load();
                                return bitmap; }
    public Color getPixel(float x, float y) { Bitmap bitmap = this.
                                                getBitmap();
                                              if (bitmap == null)
                                                  return Color.
                                                           BLACK;
                                              x = x - (int) x;
                                              y = y - (int) y;
                                              if (x < 0) x++;
                                              if (y < 0) y++;
                                              float dx = (float) x *
                                                (bitmap.
                                                   getWidth() -
                                                   1);
                                              float dy = (float) y *
                                                (bitmap.
                                                   getHeight() -
                                                   1);
                                              int ix0 = (int) dx;
                                              int iy0 = (int) dy;
                                              int ix1 = (ix0 + 1) %
                                                bitmap.
                                                getWidth();
                                              int iy1 = (iy0 + 1) %
                                                bitmap.
                                                getHeight();
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
          this.
          getPixel(
            x,
            y).
          getRGB();
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
          normalize();
    }
    public Vector3 getBump(float x, float y, OrthoNormalBasis basis,
                           float scale) { Bitmap bitmap = this.getBitmap();
                                          if (bitmap == null) return basis.
                                                                transform(
                                                                  new Vector3(
                                                                    0,
                                                                    0,
                                                                    1));
                                          float dx = 1.0F / (bitmap.
                                                               getWidth() -
                                                               1);
                                          float dy = 1.0F / (bitmap.
                                                               getHeight() -
                                                               1);
                                          float b0 = this.getPixel(
                                                            x,
                                                            y).getLuminance();
                                          float bx = this.getPixel(
                                                            x +
                                                              dx,
                                                            y).getLuminance();
                                          float by = this.getPixel(
                                                            x,
                                                            y +
                                                              dy).
                                            getLuminance();
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
                                                           1)).normalize();
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1170615980000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKUYfWzcVv3l7pJrckH5apN+p0kzRpv2zAQd0FTK2izp0l6b" +
                                                   "LEmzNt24vrPfXdz6\nbM9+l1zSamwaartNsJUxDSTWFlTUbrR0UkEFUbaObj" +
                                                   "BWIW1IDGnSCqgSIMGQENIogj/4vffss8/3\nsWw9yc8+v9/39/P5D1CtbaGV" +
                                                   "sh2ncyax4wPjo9iyiTKgYduegFdJ+Y3a+tGzO3UjhGoSKKQqFDUl\nZFtSMM" +
                                                   "WSqkjD9/blLdRrGtpcRjNonORp/KC2yaG3I7GphOADJy+3PnYm0hlCtQnUhH" +
                                                   "XdoJiqhj6o\nkaxNUXPiIJ7BUo6qmpRQbdqXQJ8iei47YOg2xTq1H0aPoHAC" +
                                                   "1Zkyo0lRV8JlLgFzycQWzkqcvTTK\n2QKFNotQrOpE2VpgB5gbijFBbAdvrB" +
                                                   "QaiCxim5OgDpcAtF5T0FpoW6KqGT43efeR0y+GUdMUalL1\ncUZMBk0o8JtC" +
                                                   "jVmSTRHL3qooRJlCLTohyjixVKyp85zrFGq11YyOac4i9hixDW2GAbbaOZNY" +
                                                   "nKf7\nMoEaZaaTlZOpYRVslFaJprj/atMazoDa7Z7aQt0h9h4UbFBBMCuNZe" +
                                                   "KiRA6pOni8M4hR0LFnJwAA\najRL6LRRYBXRMbxArcKXGtYz0ji1VD0DoLVG" +
                                                   "DrhQtLwiUWZrE8uHcIYkKVoahBsVWwBVzw3BUCha\nEgTjlMBLywNe8vmnt/" +
                                                   "3D4+e+88o9PLYjCpE1Jn8DIK0OII2RNLGILhOBeCsX/+bwvtzKEEIAvCQA\n" +
                                                   "LGC23nF5T+Kvr3YKmBVlYEZSB4lMk/LuE51jh7cbKMzEWGQatsqcX6Q5T4dR" +
                                                   "Z6cvb0LWthcoss24\nu3l17Jf7Hn2J/C2EGoZRnWxouSzEUYtsZE1VI9Z2oh" +
                                                   "MLU6IMo3qiKwN8fxhF4TkBIS/ejqTTNqHD\nKKLxV3UG/w8mSgMJZqJ6eFb1" +
                                                   "tOE+m5hO8+e8iRCKwoUa4WpC4sfvFPXGJTunpzVjVrItWTKsTOG/\nbFhEmo" +
                                                   "CsgEiPs6AxKdouTRtZImEZ66puSBkV0lQ2Nipkht0XTirPJGudralhpS6Ysh" +
                                                   "pE+32GphAr\nKZ+9+daRwZ1PHBfhwELY0QlcARziDoc44xB3OKCaGk54MeMk" +
                                                   "PAF2PAQZCbWrcd34QzsOHO8OQwiY\nsxEwAgPtBukd9oOyMeCl7TCvcDLEzt" +
                                                   "Lv7T8Wv3W2X8SOVLm6lsWOvX3h+ul/Na4PoVD50sfUguLb\nwMiMsnpZKGk9" +
                                                   "wWQpR/8fT+669O719z/jpQ1FPSXZXIrJsrE76ADLkIkChvTIn1nWFH4ATZ4I" +
                                                   "oQik\nOJQ1Lj9UjNVBHkVZ2edWOKZLNIFiacPKYo1tuWWpgU5bxqz3hkdGM3" +
                                                   "9uA+cwB6EYXCucuOV3trvE\nZGu7iCTm7YAWvILeerzus7+/EnuDm8Uttk2+" +
                                                   "djZOqEjdFi9YJixC4P373xp99rkPju3nkSJCBeUB\n8tMeJKSqBuWC+a9nj5" +
                                                   "41FDWt4pRGWKD9r+mOu3789683C49o8MZ16IaPJuC9X7YNPXr9y/9ezcnU\n" +
                                                   "yKxVeNJ7YEKJNo/yVsvCc0yO/GO/XfXtX+EXoJJB9bDVecILQogrFOKG7oAW" +
                                                   "zzFZV4iLrgDUlvpn\nEEvNQi2b4W69ebT752/uOXVMpMK6KoOGHyspf+UPfz" +
                                                   "wUfvq1lMAL1vMA8InVZ/586ebYYmE/0fTW\nlvQdP45ofDwgmkzmqa5qHDj0" +
                                                   "671d5x8Zu+FI1FpcvgdhxPnL3DVy55av/alM/YmmDEMjWOcM49yQ\n6/i6kU" +
                                                   "WKEy/sfx9bukGeDRUsVWa2ScpHXsp05x7+9U855xj2D0n+AN6FTaFyM1vWMr" +
                                                   "U7gnXvPmxP\nA9znr+5+sFm7+l+gOAUUZZgp7BELymy+KPwd6Nroe69daz/w" +
                                                   "ThiFhlCDZmBlCPPKgeohZYk9DRU6\nb/bfw7OyeXYRW7nKiBthuWOAvO9fxK" +
                                                   "4aLUNsMvJqTjK14VzirZEXuAEqlswygRSgM//KnpO3fkNv\ncDpe7WLYXfnS" +
                                                   "9gPTpIf7xXdnWupePpUNoegUapadeXcSazlWIaZgPLPdIRhm4qL94lFLzBV9" +
                                                   "hdq8\nMhjJPrbBqumFHTwzaPbcGCiUrLejla4T3Lu/UNYg/jDMUXr4eqfTAS" +
                                                   "GYTUudwWwGRovYRMFYVqwN\nouSydQtbdggf91eMhXuLpVwFV4sjZUsFKcfZ" +
                                                   "shOEUW02A2Hurk0BxhMfk/FyuFodxq0VGO91GNel\nVJrFpmuDomFDzcKsG9" +
                                                   "/GAQIi7fuYInXA1eaI1FZBpKQrEktCIgb6u0zB6G6KwnBECEhxoIoU+bL+\n" +
                                                   "rzNzKU2V80W5C+m6qtKkzsvnsb3/bDyKX38o5FS6L1AoDoa5USMzRPORYifa" +
                                                   "VUUj1i5+NvES7ckX\nf3CZvtO7WRTi9ZWLRBBx/ebT852bLz71CQarzoBuQd" +
                                                   "ItMyvuD0+rb4b48UnkbcmxqxiprzhbG0Ce\nnKVPFOXsmkIELGNW7oGry4mA" +
                                                   "rvLDTYnDQux5F0WN9pwuw/ykQ1dX8lVa0FyVvcNsga4SYeEVDK7I\njKEqXm" +
                                                   "DRjwrvQuiwP0ZB05ib95KjqVRWU7Zkqoh6tMrecbY8DgGYIXTbwnLX0+urt6" +
                                                   "NXL1z9jl79\nC9bLP4D5DA6V38A8n5/luN+oovLzbHkaiiSoPKrmnZQDjTtK" +
                                                   "NYZR0fkIwRV+5nYUHoIr5SicWrDC\nYU4xzFXjiyttl1/aLBxb4yMWJNVufl" +
                                                   "bYhm3V5kS/W8US32fLSeF8gVfW+Zz4JGFjzOc8W5y6HVvc\nD1fGsUVmwbaI" +
                                                   "iHnok9jCC42LVQxyiS3noa+zbMhly+dCeXNcWKg5YFaIOkdudvhYWvLpTXwu" +
                                                   "khPv\nHX7ww8Tv/sMPj4VPOrEETBo5TfMPN77nOtMiaZXrEhOjjslvV2AeCR" +
                                                   "78oUyxGxfuZwLsVYpiPjCQ\n1HnyA/0CmicAscdrBQv5ph0xtBX3RKbp2qL2" +
                                                   "xL9uui0kJ75vJuW9F/avyT818QzvS7WyhufnGZmG\nBIqmvQBlbairIjWX1t" +
                                                   "vo5YuTV374JbfN8lF/sa85FAXoFrFbxYnQ+sofQQezJuWHxvmfdPxoy9mT\n" +
                                                   "N0L87Pt/ZoFbRpQWAAA=");
}
