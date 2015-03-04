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
      ("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");
}
