package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class LightSample {
    private Ray shadowRay;
    private Color ldiff;
    private Color lspec;
    LightSample next;
    public LightSample() { super();
                           ldiff = (lspec = null);
                           shadowRay = null;
                           next = null; }
    boolean isValid() { return ldiff != null && lspec != null && shadowRay !=
                          null; }
    public void setShadowRay(Ray shadowRay) { this.shadowRay = shadowRay;
    }
    final public void traceShadow(ShadingState state) { Color opacity = state.
                                                          traceShadow(
                                                            shadowRay);
                                                        Color.blend(ldiff,
                                                                    Color.
                                                                      BLACK,
                                                                    opacity,
                                                                    ldiff);
                                                        Color.blend(lspec,
                                                                    Color.
                                                                      BLACK,
                                                                    opacity,
                                                                    lspec);
    }
    public Ray getShadowRay() { return shadowRay; }
    public Color getDiffuseRadiance() { return ldiff; }
    public Color getSpecularRadiance() { return lspec; }
    public void setRadiance(Color d, Color s) { ldiff = d.copy();
                                                lspec = s.copy(); }
    public float dot(Vector3 v) { return shadowRay.dot(v); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1170611622000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVYfWwUxxUf3/nbJmcbY4xjMDYGCobbJi1VwZH4cEwwPsCx" +
                                                   "wRgT6ox35+4W9naX\n3bnz2aEpKFJMErUEtZEaqQHaIkFIKJHSlkZNU1JCms" +
                                                   "atBK2aSEihIKoQqU2lqlJK1f7RN7N7t3t7\nHxjb0s6tZ968937va97s65+j" +
                                                   "EtNAzaIZpOM6MYNdA33YMInUpWDT3AlTI+KVkoq+M72q5kNFIeST\nJYoCId" +
                                                   "EUJEyxIEtCz6OdSQN16JoyHlE0GiRJGtyvrLH5bQ2tyWK4+8TFuiOni1t8qC" +
                                                   "SEAlhVNYqp\nrKndComZFNWE9uMEFuJUVoSQbNLOEJpD1HisS1NNilVqHkRP" +
                                                   "I38Ileoi40lRayglXADhgo4NHBO4\neKGPiwUOcw1CsawSaWNaHOxclbkT1L" +
                                                   "b39WdTA5NytjgIcLgGgHpxGrWFNguq7j87+LVDp171o8Aw\nCsjqAGMmAhIK" +
                                                   "8oZRdYzERolhbpQkIg2jWpUQaYAYMlbkCS51GNWZckTFNG4Qs5+YmpJghHVm" +
                                                   "XCcG\nl5maDKFqkWEy4iLVjLSNwjJRpNR/JWEFRwB2gwPbgruZzQPAShkUM8" +
                                                   "JYJKktxQdkFTze4t2Rxtje\nCwSwtSxGaFRLiypWMUygOsuXClYjwgA1ZDUC" +
                                                   "pCVaHKRQ1JSXKbO1jsUDOEJGKGr00vVZS0BVwQ3B\ntlA0z0vGOYGXmjxecv" +
                                                   "mno+GLo2d/8M4GHtvFEhEVpn8lbFrk2dRPwsQgqkisjXfjwe/17Ik3+xAC\n" +
                                                   "4nkeYotm49KLu0Kf/brFonkwB82O0f1EpCPi9uMt/U89piE/U6Nc10yZOT8D" +
                                                   "OU+HPnulM6lD1jak\nObLFYGrxUv/7ew6fI3/zocoeVCpqSjwGcVQrajFdVo" +
                                                   "jxGFGJgSmRelAFUaUuvt6DyuA9BCFvze4I\nh01Ce1CxwqdKNf4/mCgMLJiJ" +
                                                   "KuBdVsNa6l3HNMrfkzpCqAweVA1PJbL++C9FQlAw42pY0cYE0xAF\nzYik/x" +
                                                   "c1g0C6R6J0AMd0hQRZ4OgU9QpRLUYELGJVVjUhIkOqitpqiSTY7/2xSzIN68" +
                                                   "aKiljJ86au\nAlG/RVMkYoyIZ25/eKi797mjVliwULaxUdQMUoK2lCCTEnRJ" +
                                                   "QUVFnHk9k2Z5BWx6ALIT6lj1ioF9\nW5882uaHcNDHisEgjLQNUNgqdItal5" +
                                                   "PCPbzaiRBHjT/aOxm8e2a9FUdC/kqbc3fV1fNTp/5VvdKH\nfLnLIIMGhbiS" +
                                                   "seljtTNd3tq9iZOL/z+e3/bmR1OffMlJIYraszI7eyfLzDavEwxNJBLUOof9" +
                                                   "6QUB\n/240eNyHiiHdocRx/aF6LPLKyMjQzlS1Y1jKQqgqrBkxrLClVImqpF" +
                                                   "FDG3NmeHTU8Pe54JwqFrIN\n8NTbMcx/2eo8nY0NVjQxb3tQ8Gp695nSL3/8" +
                                                   "dtUVbpZU4Q24jrYBQq00rnWCZadBCMx/8v2+7770\n+eReHil2qFA47+Kjii" +
                                                   "wmYcsyZwvkrwI1hDmyfZca0yQ5LONRhbCI+19g6UM/+/t3aizXKDCT8uyq\n" +
                                                   "ezNw5hdsQoenvvHvRZxNkcjODweGQ2ahmetw3mgYeJzpkTzyx4Uv/xa/AuUN" +
                                                   "SoopTxBeJRBHhrgd\nBW73lXwMetYeYkMb8F6VJ/RznNYj4qFzkbb4wd+9xb" +
                                                   "Wuwu5j3+2GbVjvtDzPhiXMuvO92bsFm1Gg\n++ql7U/UKJf+CxyHgaMIp6S5" +
                                                   "w4CCkcxwok1dUnb93csNT17zI99mVKloWNqMefyjCgg8Ykah1iT1\n9Rt4bN" +
                                                   "WMlbORQ0bcCE22AZKu/4pBuRX5038zO+udzBkZXXU29OGOV7gB8iZ+jqPOw2" +
                                                   "finV0n7v6B\n3uB8nAxku1uT2YUU+iNn79c/StSWvnEy5kNlw6hGtDu4QazE" +
                                                   "WZwPQ8Nhpto66PIy1jObB+uk7ExX\nmGZv9rvEenPfKeDwzqjZe7Un3dlphZ" +
                                                   "pSeZ/6dad7EeIv6/mWdj4uTydnmW7ICcy6OuhJoljSxvrx\nOKeZT1F91pkB" +
                                                   "i1b1YOPDbNhgOXpN3oBYl6lqIzxzbFXn5FG1hw0bKSpRIKnDKW3mu7WRY9BI" +
                                                   "sRqg\nGR6Fts5AoQdshR7Io1BfWiFTJ+J9KvT49BXiqTMPnoCtUCCPQruznY" +
                                                   "nAhcUqONL8JnMtWuC6G23X\nVN4+ySI7NpKpUsEiNGiQMCuE7AxLjt9afn3x" +
                                                   "72u6pqySGaVoqSuWbUqhR01oIk/FLViVoDWzKmhz\nToG7DaxDq3v15qf7jn" +
                                                   "XceZ/1ArrHQEMFDMSjfVlGZQFRC/N1xryrnxz6Z/Wz+L19Vt9Rl9ltdsON\n" +
                                                   "7M74ZbL8kW/fytEmVVBNX62QBFFcMsuZyIx+Zxu/NDj14vlXX7tIr3Wss0Su" +
                                                   "zF/rvBtXrjs10bLu\nwgsz6HJaPEbwsq5NPPi4Pyp/4OP3Gqv8ZN2HMjd1Zh" +
                                                   "adStAnbqg7M0rP4uxobbKjtSlnp8GGPQWO\nyYMF1vgkXCjKZBOKqyyBIxrd" +
                                                   "d35DjsHdIcFbp9vPtv3qg10nJy0fFDhvMnaNiN/6y80D/mPvjlr7\nvIeKh/" +
                                                   "j4otOfvnm7v94KHeuSuSTrnufeY100OZqAzoK3tZAETv1eR+vrT/ff4BqxfW" +
                                                   "EwwKimKQSr\nTtZo9yor6Xxh/+zP7A874Gm3vdae12vLPJ4psrv/QscD3/1M" +
                                                   "AZdOsuEwRdVwMRvIOHHiuqXrBBSy\nhCZLDtYjM8RayybXwBO0sQZzYs0upj" +
                                                   "6o9WFZxfxavSJZ2A4Ls+zAcMlqhH01IVzESwUM8jIbjlFU\nRQ24ylkmYVPP" +
                                                   "OfhfnI2vF8Kz1sa/dtq+dmv4wwJrP2bDCXBnxOvOfBHiwDo5G1hL4dlkw9o0" +
                                                   "I1jn\nC6xdYMM56OUA1qPQhMRN0g8+ZUXyXse/g++12eBbBk+vja93RvjeKr" +
                                                   "D2Szb8nKK5zG3Q08QVbNw/\nwIuzAbganiEb4NC0Afqs/JyWjgXWuYArBQw0" +
                                                   "xYbfQFZCmUoZxpOVl2eDvgUeyUYvTRu9p/I0utHF\nMI0GBwm7q32Fc/hTAX" +
                                                   "gfs+EqRX5Jo97iC5cQDVMH57Xp4oQOtMr1VYk1hY1ZX5qtr6Ni6PpTT3wR\n" +
                                                   "+vN/+PeR9BfMqhAqD8cVxX3zcb2X6tCDyhxAlXUP0vnPLYpqvKUGDhH2wxW8" +
                                                   "aZH9FfRzkcGpar+5\nie6AUYCIvX6mpwxd4/TL1o0umQGcIV2S0XDwj/mpxi" +
                                                   "xufc4fEYfO712cfGHni7zbKxEVPDHB2FSG\nUJn1nSfd3LXm5ZbidRW9cWHw" +
                                                   "7Z+sTTUJ/DtAvetAy4i+h63VAo6EhjL3x5XumE7555CJX8z/6SNn\nTtzgPb" +
                                                   "z+f2r99/+DGQAA");
}
