package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Color;
public class TexturedShinyDiffuseShader extends ShinyDiffuseShader {
    private Texture tex;
    public TexturedShinyDiffuseShader() { super();
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
              getUV(
                ).
              x,
            state.
              getUV(
                ).
              y);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAMVXW2wUVRg+O73RUuiFW0EoUAqRojtCggmWoLAWKKy0oYXE" +
       "Gl1OZ87uDp0bZ86222JVSAzEB2K0Khjsg4F44xYjQWNIeFE0+qIxGh9E44tG" +
       "5YEHL/H+nzMzO7Ozu6hPbjIzZ8/5r+f//+/858x1VONQtMa29PGMbrE4ybP4" +
       "fn19nI3bxInvSK7vx9QhakLHjjMIcyml40LTT789mW2WUO0QmoNN02KYaZbp" +
       "7CaOpY8SNYmagtkenRgOQ83J/XgUyzmm6XJSc1h3Es0MsTLUmfRNkMEEGUyQ" +
       "hQny5oAKmGYRM2ckOAc2mXMAPYJiSVRrK9w8hpYXC7ExxYYnpl94ABJm8P97" +
       "wSnBnKdoWcF31+cSh59ZI08991Dz61WoaQg1aeYAN0cBIxgoGUKNBjGGCXU2" +
       "qypRh1CLSYg6QKiGdW1C2D2EWh0tY2KWo6SwSXwyZxMqdAY716hw32hOYRYt" +
       "uJfWiK76/2rSOs6Ar/MDX10Pt/J5cLBBA8NoGivEZ6ke0UyVoaVRjoKPnTuB" +
       "AFjrDMKyVkFVtYlhArW6sdOxmZEHGNXMDJDWWDnQwtCiikL5XttYGcEZkmKo" +
       "LUrX7y4BVb3YCM7C0LwomZAEUVoUiVIoPtd3bTx20NxuSsJmlSg6t38GMLVH" +
       "mHaTNKHEVIjL2NiVfBbPv3xUQgiI50WIXZpLD9+457b2K++5NLeUoekb3k8U" +
       "llJODc/+aHFi9YYqbsYM23I0Hvwiz0X693sr3XkbKm9+QSJfjPuLV3a/e/9j" +
       "r5LvJdTQi2oVS88ZkEctimXYmk7oNmISihlRe1E9MdWEWO9FdTBOaiZxZ/vS" +
       "aYewXlSti6laS/yHLUqDCL5FdTDWzLTlj23MsmKctxFCdfCgRngakPsTX4bG" +
       "5axlEBkr2NRMS4bcJZgqWZkoVooS25J7En3yMOxy1sB0xJGdnJnWrbGUknOY" +
       "ZcgOVWSLZvxpWbEokZ0sVgmVB6GgoEjUgaxmjt+rpdM5hwyIpThPQfv/VJ7n" +
       "O9M8FotB0BZHIUOHattu6UCbUqZyW3punEt9IBVKyNtThtaB7rinO851x13d" +
       "8cq6USwmVM7lNrg5AhEeAawAFG1cPfDgjn1HO6ogOe2xaggPJ+2AzfAM61Gs" +
       "RAAovQI2FcjqthcfOBL/5aW73ayWK6N/WW505fjYob2P3iEhqRjGuaMw1cDZ" +
       "+zn4FkC2M1q+5eQ2Hfn2p/PPTlpBIRedCx6+lHJyfOiIhoRaClFhTwPxXcvw" +
       "xdTlyU4JVQPoANAyDIUBGNYe1VGEE90+5nJfasDhtEUNrPMlHygbWJZaY8GM" +
       "yJXZYtwCQZnJC2clPLO8ShJfvjrH5u+5bm7xKEe8EJi+9a0rJy4+v2aDFIb/" +
       "ptCBOkCYCyYtQZIMUkJg/ovj/U8/c/3IAyJDgGJFOQWd/J0AaIGQwbY+/t6B" +
       "z7+8duoTKcgqBmdsbljXlDzIWBVoAeDRAfx47Dv3mIalamkND+uEJ+fvTSvX" +
       "XvzhWLMbTR1m/GS47Z8FBPMLt6DHPnjo53YhJqbwgy/wPCBzN2BOIHkzpXic" +
       "25E/9PGSE1fxC4DLgIWONkEEvCHhGRJbL4tQdYl3PLK2lr+W2SVreTHTVqi6" +
       "1ZWLaCs/v0PF92ufPnz461+ERyXlU+bYivAPyWdOLkps+l7wB3nMuZfmS8EJ" +
       "ep2Ad92rxo9SR+07EqobQs2K10jtxXqOZ8sQNA+O311Bs1W0XtwIuKded6FO" +
       "F0drKKQ2WkEBKMKYU/NxQ6Ro+MmD2vzq8b/hookhMdggWDrEeyV/3ernbJ1N" +
       "tVHMuzRUBW9BMQ8O5BIU9uBXVKEt7OgMRRfxpF9Sqf8QvdOpw1PTat/ptS6e" +
       "thaf6T3Qsp799I8P48e/er/MoVDPLPt2nYwSPaRT4iqLcPw+0ZoFkXzildcu" +
       "sY/W3OWq7KqcfVHGq4e/WzS4KbvvP6D30ojzUZGv3Hfm/W2rlKckVFVIiJJu" +
       "s5ipuzgNGiiBCJiDRcnQXkiGOTwMC+Fp8ZKhpSyCBpELalny9tMLfntJ8IWr" +
       "BJpZDhY+2fww2YD73dzfK9TsuglaDPLXToDLnK1C5t0cF/qpZkC3N+q1o/Jk" +
       "65cjJ78960Y0CgIRYnJ06om/4sempFCDv6Kkxw7zuE2+sHKWu7F/wS8Gz5/8" +
       "4S7wCbfJa014neayQqtp27wOlt/MLKFi6zfnJ99+efKI5G1JAgpx2LJ0gs1S" +
       "FBUT2wpxFmG9xSt8HwD+XZxjHhJ7AVxSEmfeT8Hdhd/eiBCj3CSOGf7ax1BD" +
       "hjCvH/MlLwhL1gy4w/BTzKJljgi4HlXu7Ph51VZy93TvS8q56aYZC6b3fCZ6" +
       "lcKdph4uFumcrofxMzSutSlJa8L+ehdNXTAzI0aHOk/IVHcgjDdc+gNwX4/S" +
       "M1TNP2EyxtDMEBmE2RuFiWC6Coj4MG/7W9hVqQ8u3aU8KkJiO4rLK4rKS9z4" +
       "fZDKuXf+lHJ+eseugzfuPC0Qr0bR8cSEuCHChdft5QpAt7yiNF9W7fbVv82+" +
       "UL/Sz+7Z/NXqNXAR25aW73N6DJuJzmTizQVvbHxp+ppotP4G9X/C4ooRAAA=");
}
