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
      1169407182000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
