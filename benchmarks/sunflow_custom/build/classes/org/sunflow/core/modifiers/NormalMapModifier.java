package org.sunflow.core.modifiers;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Modifier;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.math.OrthoNormalBasis;
public class NormalMapModifier implements Modifier {
    private Texture normalMap;
    public NormalMapModifier() { super();
                                 normalMap = null; }
    public boolean update(ParameterList pl, SunflowAPI api) { String filename =
                                                                pl.
                                                                getString(
                                                                  "texture",
                                                                  null);
                                                              if (filename !=
                                                                    null)
                                                                  normalMap =
                                                                    TextureCache.
                                                                      getTexture(
                                                                        api.
                                                                          resolveTextureFilename(
                                                                            filename),
                                                                        true);
                                                              return normalMap !=
                                                                null;
    }
    public void modify(ShadingState state) {
        state.
          getNormal(
            ).
          set(
            normalMap.
              getNormal(
                state.
                  getUV(
                    ).
                  x,
                state.
                  getUV(
                    ).
                  y,
                state.
                  getBasis(
                    )));
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                state.
                  getNormal(
                    )));
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169407166000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
