package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Color;
public class TexturedWardShader extends AnisotropicWardShader {
    private Texture tex;
    public TexturedWardShader() { super();
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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "tzidhs1EUzLy7sx3Hj4xflX0WH8D0Ey5sUcRAAA=");
}
