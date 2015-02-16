package org.sunflow.core.modifiers;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Modifier;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.math.OrthoNormalBasis;
public class BumpMappingModifier implements Modifier {
    private Texture bumpTexture;
    private float scale;
    public BumpMappingModifier() { super();
                                   bumpTexture = null;
                                   scale = 1; }
    public boolean update(ParameterList pl, SunflowAPI api) { String filename =
                                                                pl.
                                                                getString(
                                                                  "texture",
                                                                  null);
                                                              if (filename !=
                                                                    null)
                                                                  bumpTexture =
                                                                    TextureCache.
                                                                      getTexture(
                                                                        api.
                                                                          resolveTextureFilename(
                                                                            filename),
                                                                        true);
                                                              scale =
                                                                pl.
                                                                  getFloat(
                                                                    "scale",
                                                                    scale);
                                                              return bumpTexture !=
                                                                null;
    }
    public void modify(ShadingState state) {
        state.
          getNormal(
            ).
          set(
            bumpTexture.
              getBump(
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
                    ),
                scale));
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
      1169407162000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "1G0vbZC8BL+Lolpq2dfqZJLosaReVVRKB3kHHCbT48deeZ1+vP5mYWfX/G6O" +
       "M76377uVI7fmHvgPBbQtZlP8yGODJz648yrlSQlVBDlZ0tQXM3UXZ2KdQyD4" +
       "zZGifGwN4r+JhcUKeJZ68b+0bBELXRbCqeTdp+e81hLncVMJfDMwvPbJlkfJ" +
       "hsVvz1A/F3PvZQA7w4a7oWLlbRWiltPcwYYtArIHABgylqUTbJaiOl/YUVy5" +
       "u+Bp8Yxu+ddGJ4ojdlWJ0cM5rEKjx74YCT9m4jJG8T48B0bxErm7nFGVk5am" +
       "lqlTFDWV6StZtWwu+boVX2TKqbnkgivmdnzOO6Xgq6kWPl2yeV2PondkXm07" +
       "JKtxbWsFlosEnoIPwfl7Xki+YM51nxRc0xQ1xLnARvYTJZsBaI+QgWu9WZRo" +
       "D0UVQMSme23fIQ28VWC1LCVqWQFFkp91S9G3otaJ5Tf//4Gfi3nxH4Qx5fTc" +
       "wNaHLt30Ek/sKkXH09P8exM+n0XXGOTz6nlP88+q3rLut8Vnatf6OLWYDY1e" +
       "qxjTra18R9Vn2JT3QNNvXPHaLUfnzvOW7m+3q/eA2BEAAA==");
}
