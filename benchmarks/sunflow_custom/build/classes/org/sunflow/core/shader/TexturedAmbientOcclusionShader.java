package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Color;
public class TexturedAmbientOcclusionShader extends AmbientOcclusionShader {
    private Texture tex;
    public TexturedAmbientOcclusionShader() { super();
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
    public Color getBrightColor(ShadingState state) {
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
      1414698367000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
