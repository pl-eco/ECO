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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "EQAA");
}
