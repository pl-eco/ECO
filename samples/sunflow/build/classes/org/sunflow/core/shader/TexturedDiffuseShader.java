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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "TPp46cmRW7y9+hvydwy8SxEAAA==");
}
