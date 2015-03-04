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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "mkl5ZzLz9qq3bn9t/jJvtP4GB360XGQRAAA=");
}
