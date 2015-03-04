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
