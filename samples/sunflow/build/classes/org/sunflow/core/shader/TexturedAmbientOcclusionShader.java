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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "DmCwQqIRAAA=");
}
