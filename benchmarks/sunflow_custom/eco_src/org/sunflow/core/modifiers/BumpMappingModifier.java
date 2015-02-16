package org.sunflow.core.modifiers;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Modifier;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.math.OrthoNormalBasis;
public class BumpMappingModifier implements Modifier {
    private Texture bumpTexture;
    private float scale;
    public BumpMappingModifier() { super();
                                   bumpTexture = null;
                                   scale = 1; }
    public boolean update(ParameterList pl, SunflowAPI api) { String filename =
                                                                pl.
                                                                getString(
                                                                  "texture",
                                                                  null);
                                                              if (filename !=
                                                                    null)
                                                                  bumpTexture =
                                                                    TextureCache.
                                                                      getTexture(
                                                                        api.
                                                                          resolveTextureFilename(
                                                                            filename),
                                                                        true);
                                                              scale =
                                                                pl.
                                                                  getFloat(
                                                                    "scale",
                                                                    scale);
                                                              return bumpTexture !=
                                                                null;
    }
    public void modify(ShadingState state) {
        state.
          getNormal().
          set(
            bumpTexture.
              getBump(
                state.
                  getUV().
                  x,
                state.
                  getUV().
                  y,
                state.
                  getBasis(),
                scale));
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                state.
                  getNormal()));
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169407162000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKUYa2wcxXnuzo/4EZ3tOI4THDt2DBQn3AIiCGKkxBgHLtkQ" +
       "YzsGHIKZ2507b7y3\nu+zOns8moiAqHIKARgWplUqIqkgJFAoSrdJKlAYBLS" +
       "WqBJVKJSTSVpFKpQISQqKp4AffzOze3u7d\nGQQn7ezszPeY7/3NvfAJqnds" +
       "1KM4KbpoESc1OjmObYeoozp2nClYmlXeqm8aP7XHMOMoJqO4plKU\nlBVHUj" +
       "HFkqZK6ZuHizbaYpn6Yk43aYoUaeqQvs2jt1veVkHwjuNnOh46WdcXR/UySm" +
       "LDMCmmmmmM\n6STvUNQmH8IFLLlU0yVZc+iwjFYTw82PmoZDsUGd+9ADKCGj" +
       "BkthNCnql33mEjCXLGzjvMTZS+Oc\nLVBYYxOKNYOoIyV2gLk1jAnH9vAmKq" +
       "GByCq2OQ3i8BOA1JtKUgtpK0S1Eqenrzt84rkESs6gpGZM\nMmIKSEKB3wxq" +
       "zZN8htjOiKoSdQa1G4Sok8TWsK4tca4zqMPRcgamrk2cCeKYeoEBdjiuRWzO" +
       "01+U\nUavCZLJdhZp2SUdZjeiq/1Wf1XEOxO4KxBbi7mLrIGCzBgezs1ghPk" +
       "rdvGaAxfuiGCUZB/cAAKA2\n5gmdM0us6gwMC6hD2FLHRk6apLZm5AC03nSB" +
       "C0UbahJlurawMo9zZJai7ijcuNgCqCauCIZC0doo\nGKcEVtoQsVKZfbZ0fX" +
       "Hk9M9f28l9u04lis7O3wxIvRGkCZIlNjEUIhAvuqmn0ne5PXGEAHhtBFjA\n" +
       "jFx6Zr/8nz/0CZhLqsDsyxwiCp1VbjvWN3H/LSZKsGOsskxHY8YPSc7DYdzb" +
       "GS5aELVdJYpsM+Vv\nnp34410PPk/+G0fNadSgmLqbBz9qV8y8penEvoUYxM" +
       "aUqGnURAx1lO+nUSPMZXB5sbovm3UITaM6\nnS81mPwbVJQFEkxFTTDXjKzp" +
       "zy1M5/i8aCGEGuFB18LTgsSPvykaSUmOa2R1c0FybEUy7VzpWzFt\nIuVNVQ" +
       "OPtR3pJjdv7cWWBR6z11tMMVeyKDoozZl5ImEFG5phSjkNglcxr1RJgb2/L4" +
       "Mik6JjIRZj\naTEa3jpExq2mrhJ7Vjl14Z3DY3sePSJch7m7Jz9FKeCb8vim" +
       "GN9UiW+qCl8Ui3F2nYy/sCVYYh5i\nGrJf6xWTB3ffe2QgAU5kLdSBGhnoAE" +
       "jqHWpMMUeDwE/zHKmA93X/4sBy6uKpHcL7pNr5uSp2y7sv\nnjvxeetQHMWr" +
       "J08mLKTvZkZmnGXcUlIcjIZbNfqfHt37yvvnPvxBEHgUDVbkg0pMFs8DUbPY" +
       "pkJU\nyJAB+ZPrk4k70PSxOKqDJAGJkZ8fck5vlEcorof9HMlkaZRRS9a081" +
       "hnW35ia6ZztrkQrHB/aePz\nNb6j98KT9Dyfv9nuWouNXcK/mLUjUvAcfPHh" +
       "hqv+/mrLW1wtfrpOlhXESUJF8LcHzjJlEwLrH/50\n/CdPf7J8gHuK5yoUqq" +
       "Sb0TWlCCiXBSgQ9TpkHmbIwf2G8E6c0QnzuK+Sl179m4+faBOm0WHFt+zW\n" +
       "byYQrK+/CT147p7/9XIyMYVVnUCMAExIsyagPGLbeJGdo/jQXzf+7E/4GUiK" +
       "kIgcbYnw3IK4ZIjr\nUeJ6H+JjKrJ3NRsGgPbWGq5fpcbPKoefzw249/35d/" +
       "zULbi8WSg3A0TwsLA8GzYz7a6LRu+t2JkD\nuGvP3nZ3m372S6A4AxQVqK3O" +
       "PhtSSDFkRA+6vvGD19/ouve9BIrvQs26idVdmPs/agLHI84cZJ+i\ntWMn96" +
       "22hVVs5CIjroQNngKKZV9xONwVtcN/F+sQgsiZzWw9Lb+z7xmugJqBX6VARu" +
       "gsvbb/+MW/\n0POcThCBDLu/WJlaoasKcK9/v9De8PKz+ThqnEFtitf3TWPd" +
       "ZX4+A22K4zeD0BuG9sMth6ivw6UM\n0xON/jK20dgPUjrMGTSbt0bCvZVpe5" +
       "M/8d/l4R5DfLKDowzy8fJScDZatlbArBdELRkoDFMwY50e\ng1oH5b+ijngA" +
       "Ioew8Ro27BTm3lbTLbaHD9wNz2rvwKtrHDjNhhEK1wMF6wRcqLv8jmBreeg1" +
       "Cjxp\nXnhk4Pdv7392WRSaFTwthDWr/PAf/5xPPPl6RuBF3SkCfKz35L9fuT" +
       "DRKZKSaEo3V/SF5TiiMeX2\nSlosQPtX4sCh39zS/8IDE+e9E3WE26sxuIJ8" +
       "tPgGufzGx/9VpeaD75iYRgyzewXD8JNdVhanMd/s\n6yvM7ncKTIyNtbpZLs" +
       "LynZ+1PoLfPBj3suAEhcRhWlfqpED0SFLYGGoi9vL+PQjCo8/98gx9b8t2\n" +
       "oYyh2maNIg5tP7HUt/2lx75D69AXkS1Kur1wye2JOe3tOL9iiJiuuJqEkYbD" +
       "kdwM53FtYyoUz5vC\n5XsYnk4vPDqrlu/AdEHtiXt69YzYW2FELiqBmw8rbj" +
       "5YVznYpHiPjKc5G7JCdZtnQwbKu2vBRVxA\nypZwt9shsWRMUyfYCFxR+aYc" +
       "4ZcO/nFPWCND8PR4Gun51hqJhd16Y4VGJuewCn0wuxQTTqawgsRL\nbIAy3M" +
       "CbjcWoxHUFU1MDcem3FRdS75oqPTlrSrorbvfiRqrIH9x/9xfy3/7Pu8vSrb" +
       "EFrm5ZV9fL\n60bZvMGySVbjorSIKmLx14/gIlz7vgDhW5rzcz8ssJYpaoti" +
       "gRLYqxzsKBSWMjBwDG9WDvQ4RQkA\nYtMnLN9abbw1YVU0JapoMaQ2pp/NoZ" +
       "zA/3bx49YVf7zMKne+eGBT8bGpH/NkUK/oeInbsVlGjaK3\nLsV+f01qPq13" +
       "0csvTb/6qxv83MZ7r85ikHNDLnyN2F3BDSDfVG9ox/IW5S3o0m/X/frGU8fP" +
       "x3lL\n/TWdeijTLRMAAA==");
}
