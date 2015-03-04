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
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAK1Ya2wcVxW+Hq/Xj7hZe9086iZO7DgVTtodAgQojhoSYzcO" +
                                                    "m9iK3QhcUefuzN31JPPqzF174+LSRkKOUimqqBtSlFpVSdVX2lRVo4JQpfyB" +
                                                    "tip/ihCIH7SIP1SU/MgPSkWBcs6dmZ3Z2UeCxEpz9+6997zuOec7Z/bSNdLi" +
                                                    "OmSnbeknC7rFM6zEM8f13Rl+0mZu5mB29yR1XKaO6NR1p2FtVhl4LfXJZ4/P" +
                                                    "dUkkOUN6qGlanHLNMt0jzLX0eaZmSSpcHdWZ4XLSlT1O56lc5JouZzWXD2fJ" +
                                                    "mggpJ4PZQAUZVJBBBVmoIO8LTwHRLcwsGiNIQU3uPkgeJk1ZkrQVVI+T/kom" +
                                                    "NnWo4bOZFBYAhzb8fRSMEsQlh2wt2+7ZXGXwkzvllR8/0PV6M0nNkJRmTqE6" +
                                                    "CijBQcgM6TSYkWOOu09VmTpDuk3G1CnmaFTXFoXeMyTtagWT8qLDypeEi0Wb" +
                                                    "OUJmeHOdCtrmFBVuOWXz8hrT1eBXS16nBbB1fWirZ+EYroOBHRoo5uSpwgKS" +
                                                    "xAnNVDnZEqco2zj4bTgApK0G43NWWVTCpLBA0p7vdGoW5CnuaGYBjrZYRZDC" +
                                                    "SW9dpnjXNlVO0AKb5WRj/NyktwWn2sVFIAkn6+LHBCfwUm/MSxH/XDu85+xD" +
                                                    "5gFTEjqrTNFR/zYg6osRHWF55jBTYR5h547sObr+rdMSIXB4Xeywd+bN71//" +
                                                    "5p19V9/xztxe48xE7jhT+KxyMbf2/U0jQ3c3oxpttuVq6PwKy0X4T/o7wyUb" +
                                                    "Mm99mSNuZoLNq0d+9d1HXmIfS6RjnCQVSy8aEEfdimXYms6ce5nJHMqZOk7a" +
                                                    "mamOiP1x0grzrGYyb3Uin3cZHycJXSwlLfEbrigPLPCKWmGumXkrmNuUz4l5" +
                                                    "ySaEtMJDOuFJEe8jvjnJynOWwWSqUFMzLRlil1FHmZOZYskuNWwdvOYWzbxu" +
                                                    "Lciuo8iWUyj/ViyHydOQNpAKGYwq+//Mr4T6dy00NcHVboontg45ccDSVebM" +
                                                    "KivF/aPXX519TyoHum85OAwkZHwJGZSQ8SWQpibB+FaU5PkLbvsE5C0gWufQ" +
                                                    "1PcOHjs90AyBYi8k4Krw6ABY4YsfVayRMLnHBYQpEGEbn71/OfPp83u9CJPr" +
                                                    "I3FNanL1/MKjR3/wRYlIlZCK5sBSB5JPIhCWAW8wnkq1+KaWP/rk8rklK0yq" +
                                                    "Coz2c72aEnN1IH7xjqUwFS4wZL9jK70y+9bSoEQSAAAAepxCkAKe9MVlVOTs" +
                                                    "cIB/aEsLGJy3HIPquBWAVgefc6yFcEVExFox7wanoGPIGnhu96NafONuj43j" +
                                                    "rV4EoZdjVgh8Hfv51aeu/GTn3VIUilOR4jbFuJfY3WGQTDuMwfofz08+8eS1" +
                                                    "5ftFhMCJbbUEDOI4AmkOLoNr/eE7D/7hww8u/lYqRxUpAekdIXPIfR3wB10+" +
                                                    "eJ9pWKqW12hOZxiT/0pt33Xlb2e7PCfqsBLEwJ03ZhCu37afPPLeA//oE2ya" +
                                                    "FKw9ocHhMc/unpDzPsehJ1GP0qO/2fzU2/RpgEaAI1dbZAJhJGGQJHyzDnoE" +
                                                    "QYllJuOVGeA21KAxcTQDsHLeB3N5Kf3hiQsfveKlURz5Y4fZ6ZUzn2fOrkiR" +
                                                    "8ritqkJFabwSKYLjFi+YPodPEzz/wQcdgwseRKZHfJzeWgZq20a/9TdSS4gY" +
                                                    "+8vlpV+8sLTsmZGurA6j0Py88rt//zpz/k/v1gCu1pxl6YyaQsuM0HJIjHeh" +
                                                    "Wn704O89OGy1q/ZKYmWj+JVofPtj2JNEQOyfE3ru1J8/FVpVwVANh8ToZ+RL" +
                                                    "F3pH7vlY0Id4gNRbStVQDv1bSPull4y/SwPJX0qkdYZ0KX5zeJTqRcy6GWiI" +
                                                    "3KBjhAayYr+yufEq+XAZ7zbFIyIiNo5EoSdgjqdx3hEDH6ymZBM8XT74dMXB" +
                                                    "p4mIybcEyYAYt+PwBa+igI9tR5un2HmSNqzjgVhZwJbn0r2VAjfD0+0L7K4j" +
                                                    "8CAOY8BTc7GBoOLmv1KfZy88aZ9nug7PQz7PZE7jBrWDNK8or5oBPWBmvzhQ" +
                                                    "X9oGeHp8aT11pB0JpOkWhUZcMNiFw24vqr/GSTN0xp6QUt37TdrFnK4p0UwQ" +
                                                    "iLu5Xv8psvbiqZVVdeK5XZKfYF/npJ1b9l06m2d6hBW+a22uaAkOiY47DOYz" +
                                                    "L778Jn9/5ze8/N9RPwHjhG+f+mvv9D1zx/6HRmBLzKY4yxcPXXr33juUH0mk" +
                                                    "uZwTVS8RlUTDlZnQ4TBonMzpinzoKzv3NrzdQXj6fef21y7GNZ0l4fQAJ53u" +
                                                    "SVOBmm9CWVFLDVDveIM98coARSCB0VMrdhLzlqZWI6ZYOFY2KB10FLJvkFzT" +
                                                    "IBy+00CbYoO9BRygUWovMB7mzeQNVROZjxe+11dt702rFq3TsWsBDLSoSKqH" +
                                                    "Bf1SA81P4bAIGAOaT2olPzEAETZUIwJ0FZZzQ5t6AjzN+TblbtqmZsGxWWgu" +
                                                    "hkCZ/qgyBrwRZSYciPDDotHcT13NFUzPNDD0LA7Lnos8uprYJ5gfZdg9f/nm" +
                                                    "TEXULfimFm7a1IRXz0NTcXgs9Nm5Bqacx+EJKDsYbUVDiHq8RuMAJ/yXJOwB" +
                                                    "N1b9peL9DaC8uppq27B63+9F219+VW+H9+V8UdejJTQyT9oOy2tCnXavoNri" +
                                                    "62loGeOvapCo+CUUvOAde4aTNZFjoKk/ix76KVQHOITTi+VKFWlIvdagRCrK" +
                                                    "gh0vEtsq4Fr8/RRAa9H7A2pWubx68PBD17/6nMDpFkWni4vIpS1LWvNhrCA8" +
                                                    "99flFvBKHhj6bO1r7duDsrMWh3QENCO6band8Y8aNhc9+uLPNryx5/nVD8Sb" +
                                                    "xn8BHgtwIhcUAAA=");
}
