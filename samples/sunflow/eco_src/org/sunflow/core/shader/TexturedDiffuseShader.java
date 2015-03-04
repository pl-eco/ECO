package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Color;
public class TexturedDiffuseShader extends DiffuseShader {
    private Texture tex;
    public TexturedDiffuseShader() { super();
                                     tex = null; }
    public boolean update(ParameterList pl, SunflowAPI api) { String filename =
                                                                pl.
                                                                getString(
                                                                  "texture",
                                                                  null);
                                                              if (filename !=
                                                                    null)
                                                                  tex =
                                                                    TextureCache.
                                                                      getTexture(
                                                                        api.
                                                                          resolveTextureFilename(
                                                                            filename),
                                                                        false);
                                                              return tex !=
                                                                null &&
                                                                super.
                                                                update(
                                                                  pl,
                                                                  api);
    }
    public Color getDiffuse(ShadingState state) {
        return tex.
          getPixel(
            state.
              getUV().
              x,
            state.
              getUV().
              y);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1414698358000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVYe2wcxRkf3/kRn438SOKYkNix40DJ47agpioxUuIaB0wu" +
       "2PU5BpxQZ7w7d95k\nbnfZnbXPJuJRJJKC2hIBEkjNQ1WkBJoAElQBidIgoA" +
       "UiJFIJkJAIVJFaJB5SVYmmav/oNzO7t4+7\nMwgs3e7szPeYb77v+33f+PSX" +
       "qM6x0SrVSbN5izjpwewoth2iDVLsOOMwNaW+Wdc4enKHYSZQTQYl\ndI2hlo" +
       "zqKBpmWNE1Zfim/qKNNlgmnc9Tk6VJkaX30c2evFszm8sE3n70bPsDJ2q7E6" +
       "gug1qwYZgM\nM900higpOAy1ZvbhWay4TKdKRndYfwZdQQy3MGgaDsMGc+5G" +
       "96JkBtVbKpfJUE/GV66AcsXCNi4o\nQr0yKtSChKU2YVg3iDZQUgecG6OcsG" +
       "2Pb6ycGoQs4YsTYI7YAVi9pmS1tLbMVCt5auLHB44/nUQt\nk6hFN7JcmAqW" +
       "MNA3iZoLpDBNbGdA04g2idoMQrQssXVM9QWhdRK1O3rewMy1iTNGHJPOcsJ2" +
       "x7WI\nLXT6kxnUrHKbbFdlpl06o5xOqOZ/1eUozoPZHYHZ0tztfB4MTOmwMT" +
       "uHVeKz1O7XDfB4d5yjZGPf\nDiAA1oYCYTNmSVWtgWECtUtfUmzklSyzdSMP" +
       "pHWmC1oYWllVKD9rC6v7cZ5MMdQZpxuVS0DVKA6C\nszC0PE4mJIGXVsa8FP" +
       "LPho6vD5367avbRGzXakSlfP8pYOqKMY2RHLGJoRLJeNlNPz58p7sqgRAQ\n" +
       "L48RS5qBdWd3ZT77U7ekuaoCzcj0PqKyKfW2w91j99xsoiTfxhLLdHTu/Ijl" +
       "Ih1GvZX+ogVZ21GS\nyBfT/uK5sT/fef8z5PMESg2jetWkbgHiqE01C5ZOiX" +
       "0zMYiNGdGGUSMxtEGxPowaYJyBkJezI7mc\nQ9gwqqViqt4U33BEORDBj6gR" +
       "xrqRM/2xhdmMGBcthFAD/FAz/FJI/ok3Q9vSiuMaOWrOKY6tKqad\nL32rpk" +
       "0UZwZrxFbGITkg4LWb9FzOdUhWzKZ5JFkM7VFmzAJRsIoN3TCVvA65q5qbND" +
       "LL399TfpHb\n0D5XU8NBMZ7cFPLiFpMC7ZR68tI7B4Z2/PKQDBwe7J71DG0C" +
       "tWlPbZqrTUu16YpqUU2N0LaMq5eO\nBDfsh4QG6Gu+NnvXrXsP9SYhgqy5Wj" +
       "hDTtoLdnp7GlLNwSDrhwVAqhB6nb/bfTB9+eRWGXpKdXCu\nyN303pnzx//V" +
       "vD6BEpWRk9sK2J3iYkY53JYQsS+ea5Xkf/Xwzhc+OP/xD4KsY6ivDAzKOXky" +
       "98a9\nYpsq0eBYA/EnrmxJ3o4mDidQLSAEoKLYPwBOV1xHJKn7fYDktjRkUF" +
       "POtAuY8iUf1VJsxjbnghkR\nLq1ivBSc08SjfA38rvDCXrz56nKLPztkeHFv" +
       "x6wQAHz5wfoffvhK05viWHysbglVwyxhMvPbgmAZ\ntwmB+Y+fHH3siS8P7h" +
       "aR4oUKgxLpTlNdLQLL1QELpDwF2OGO7NtlFExNz+l4mhIecf9rWXfdH774\n" +
       "dat0DYUZ37Mbv1lAMH/lT9H953/+7y4hpkblJScwIyCT1iwNJA/YNp7n+yg+" +
       "8NfVT/0FHwFEBBRy\n9AUigAUJy5A4R0Wc+3rxTMfWruOPXpC9sUroVyjwU+" +
       "qBZ/K97t1vvyx23YTDnULYDTux1S89zx9r\n+emuiGfvLdiZAbofnbttTys9" +
       "91+QOAkSVSiszogNaV+MONGjrmv46LXXO/ZeSKLEdpSiJta2YxH/\nqBECjz" +
       "gzAD5Fa+s2EVutc0v4U5iMxCGs9A6gGPrieHFt9fTfztuDIHOmpjeeyrwzck" +
       "QcQNXEr1Ad\nY3IWXt119PK77KKQE2Qg5+4pliMrtFQB708+mG2rf/5YIYEa" +
       "JlGr6jV9E5i6PM4noUdx/E4QGsPI\nerTfkMW1v4Qwq+LZH1Ibz/0A0WHMqf" +
       "m4OZbuvMChTj/v/Xc43WuQGGwVLH3ieU0pORssW5/FvBFE\nSXiK1RVQ88vK" +
       "h1c3JHbw5/X8sU1ybK4UDmKjV0eCAuJgdbU+SPRwB+/4Z/ND+I27ZMloj/YW" +
       "Q9B/\n/2P+dXLNjb/6W4WS18hMaxMls4SGdCa4ykip2ilaxMDVDz/9+7Pswo" +
       "YtUuX66mEaZ1y/5fhC95bn\nHvkOBao7dghx0W2zV/0sOaO/lRBdrIycsu43" +
       "ytQfjZcU7Me1jfFI1KyJFol++LV5UdNWsUgEHgwQ\nLuGdqxcpXWWRIkwl0F" +
       "xzCPXJOsJkWfkeGB0WaiYWwdA9/JGFIuJacNcj4M3O8DXR1gvQbs6K0nnp\n" +
       "od4/vrXr2EHpyEXwJsI1pd73yaf7k795bVryxUElRny468TfX7g0tkzGn7yX" +
       "rC27GoR55N1EGNNi\n8QzoWUyDoH5jQ8/pe8cuih1xvh2Qp9OmSQk2gtQbXy" +
       "T1oknHP0airlc80PDB49u5vsZDc8+nq8tc\nz7tJuF7xCyYRYugirhXadIZS" +
       "ecK8btSXvCIsWS/ANYuXe+9GKazf922tB2BbXrHn5UW/s+zqLK97\nauaje/" +
       "Z8nXn/P6J7K13JmuBelHMpDeNyaFxv2SSnC9uaJEpb4jUfMyjUjkNgy4HYc1" +
       "HSH2CoNU7P\nUC1/hcnuY6gpRAYx4o3CRL8AXAciPnzQ8o93XbXLQeSAipFz" +
       "5Me1NpJT4l8cPoC58p8cU+odZ3av\nKT4y/qhAxTqV4oUFcZuFy7lsZUsg2F" +
       "NVmi/rPfT8cxOvPHuDnwei1VlWDGpQJMSvl6uLxAUAb+X+\ncahgMdHxLby0" +
       "4sUbTx69mBAd7P8BMa6mF5kSAAA=");
}
