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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "TlvSoLwyGf5k2sfLT47d4oXW34fl9lJSEQAA");
}
