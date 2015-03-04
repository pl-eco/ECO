package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Color;
public class TexturedPhongShader extends PhongShader {
    private Texture tex;
    public TexturedPhongShader() { super();
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
      1414698376000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVYfWwcxRUf3/kjPhv5I4ljQmLHjoESh9uCGtTGiMQYB0wu" +
       "+OpzDDihZrw7d7fJ\n3O6yO2ufTcSnlARQPyKo1EolRFWkBAoFiVZpJUqDgJ" +
       "YSVYJKpRISKShSW6kFCSFBKviDNzO7tx93\n56Ji6XZnZ97HvHnv/d4bP/sh" +
       "anJstEF10mzJIk56LJfFtkO0MYodZxqm5tTXm1qzp3YbZgI1ZFBC\n1xjqyK" +
       "iOomGGFV1TJm4aKdto2DLpUoGaLE3KLH2AbvPk3ZrZViXw9uNnuh882difQE" +
       "0Z1IENw2SY\n6aYxTknJYagzcwAvYMVlOlUyusNGMugSYrilMdNwGDaYcw+6" +
       "DyUzqNlSuUyGBjK+cgWUKxa2cUkR\n6pWsUAsSVtuEYd0g2mhFHXBujXLCtj" +
       "2+qWpqELKKL86AOWIHYPWmitXS2ipTreTpmesOnXg6iTpm\nUYdu5LgwFSxh" +
       "oG8WtZdIaZ7YzqimEW0WdRmEaDli65jqy0LrLOp29IKBmWsTZ4o4Jl3ghN2O" +
       "axFb\n6PQnM6hd5TbZrspMu3JGeZ1Qzf9qylNcALN7ArOlubv4PBiY0mFjdh" +
       "6rxGdpPKgb4PH+OEfFxqHd\nQACsLSXCimZFVaOBYQJ1S19SbBSUHLN1owCk" +
       "TaYLWhhaX1coP2sLqwdxgcwx1Buny8oloGoVB8FZ\nGFobJxOSwEvrY14K+W" +
       "e459Ojp3/28k4R240aUSnffwqY+mJMUyRPbGKoRDJedNNPTNzpbkggBMRr\n" +
       "Y8SSZvTyM3sz//p9v6S5rAbN5PwBorI59bZj/VP33myiJN/GKst0dO78iOUi" +
       "HbLeykjZgqztqUjk\ni2l/8ezUH+584Bny7wRKTaBm1aRuCeKoSzVLlk6JfT" +
       "MxiI0Z0SZQKzG0MbE+gVpgnIGQl7OT+bxD\n2ARqpGKq2RTfcER5EMGPqBXG" +
       "upE3/bGFWVGMyxZCqAV+qB1+KST/xJuhG9KK4xp5ai4qjq0qpl2o\nfKumTR" +
       "SniDViK9OQHBDwWrZoGoWcmEvzOLIYmlWKZokoWMWGbphKQYfMVc2rNbLA31" +
       "9Lepnvv3ux\noYEDYjyxKeTELSYF2jn11IU3D43vfuSoDBoe6J7lDA2D0rSn" +
       "NM2VpqXSdA2lqKFB6FrDlUsXggMO\nQioD6LVflbvr1ruPDiYhdqzFRjg9Tj" +
       "oINno7GlfNsSDfJwQ0qhB0vT/fdyR98dQOGXRKfViuyd32\n1nPnTnzSviWB" +
       "ErUxk1sKqJ3iYrIcaCtYOBTPslryP3p0z4vvnHvvG0G+MTRUBQPVnDyNB+M+" +
       "sU2V\naHCogfiTl3Ykb0czxxKoEbAB8FDsH6CmL64jks4jPjRyW1oyqC1v2i" +
       "VM+ZKPZylWtM3FYEYES6cY\nrwbntPH47oPfJV7AizdfXWvxZ48MLu7tmBUC" +
       "ei8+3PzNv73U9ro4Fh+lO0J1MEeYzPmuIFimbUJg\n/r2fZB//8YdH9olI8U" +
       "KFQXF056muloHlioAFkp0C4HBHDu01Sqam53U8TwmPuC86Lr/m1//5Qad0\n" +
       "DYUZ37Nb/7eAYP7SG9ED5773WZ8Q06DyYhOYEZBJa1YHkkdtGy/xfZQf/MvG" +
       "n/4RPwlYCPjj6MtE\nQAoSliFxjoo49y3imY6tXcMfgyB7a53Qr1Ha59RDzx" +
       "QG3Xv+9Fux6zYc7hHCbtiDrRHpef7YzE93\nXTx7b8FOEei+dfa2/Z307Ocg" +
       "cRYkqlBSnUkb0r4ccaJH3dTy7iuv9tz9dhIldqEUNbG2C4v4R60Q\neMQpAv" +
       "SUrR07RWx1Lq7iT2EyEoew3juAcuiL48VV9dN/F28MgsyZm996OvPm5JPiAO" +
       "omfo26GJOz\n/PLe4xf/zM4LOUEGcu6BcjWuQjMV8H77nYWu5heeKiVQyyzq" +
       "VL12bwZTl8f5LHQnjt8DQksYWY92\nGrKsjlQQZkM8+0Nq47kf4DmMOTUft8" +
       "fSnZc21Ovnvf8Op3sDEoMdgmVIPK+sJGeLZesLmLeAKAlP\nsboOqn1V8fCq" +
       "hsQO/ryWP3ZKjm21wkFs9IpIUEAcbKzXAYnu7cgdH7cfxq/dJUtGd7SrGIfO" +
       "+59L\nr5Irr//+BzUKXiszraspWSA0pDPBVUZK1R7RHAaufvTpX5xhbw9vly" +
       "q31A/TOOOW7SeW+7c//9j/\nUaD6Y4cQF921cNl3k0X9jYToX2XkVPW9UaaR" +
       "aLykYD+ubUxHomZTtEiMwK/Li5qumkUi8GCAcAnv\nXL1I6auKFGEqgbaaQ6" +
       "hP1hMmy8n3aHZCqJlZAUP380cOiohrwS2PgDd7wxdEWy9Bo7kgSueFw4O/\n" +
       "e2PvU0ekI1fAmwjXnHr/398/mPzhK/OSLw4qMeJjfSf/8eKFqTUy/uSNZHPV" +
       "pSDMI28lwpgOi2fA\nwEoaBPVrwwPP3jd1XuyI8+2GPJ03TUqwEaTe9AqpF0" +
       "06/jEZdb3igYYPHl/N9Q0emns+3Vjlet5N\nwsWKXy2JEENXcK3QpjOUKhB2" +
       "k57Puw7xJa8LS9ZLcMHi5d67SwrrD3xV6wHYVtfoeHnJ7626Mstr\nnpp599" +
       "79n2b++l/Ru1WuYm1wH8q7lIZROTRutmyS14VlbRKjLfFaipkTasUhrOVA7L" +
       "gs6Q8x1Bmn\nZ6iRv8Jk9zPUFiKDCPFGYaKHANWBiA8ftvzD3VzvYhA6nnLk" +
       "DPlhbY7kk/jHhg9ervzXxpx6x3P7\nNpUfm/6RQMQmleLlZXGHhSu5bGMrAD" +
       "hQV5ov6y30wvMzL/3yO34OiDZnTTmoP5HwvlaurhATALq1\ne8fxksVEt7f8" +
       "m3W/uv7U8fMJ0b1+CRXwt3SPEgAA");
}
