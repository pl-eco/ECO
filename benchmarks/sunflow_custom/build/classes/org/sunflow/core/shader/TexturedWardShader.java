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
      1414698348000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
