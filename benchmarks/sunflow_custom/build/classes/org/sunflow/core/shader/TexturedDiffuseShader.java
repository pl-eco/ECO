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
      1414698358000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "wt1Nq2ZS3o8MfjLl42UnR27x9upvxkGV5mERAAA=");
}
