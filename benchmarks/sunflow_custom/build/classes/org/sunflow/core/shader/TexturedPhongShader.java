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
      1414698376000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "rmbnFdBijL12AFwWTBky78q4kLIUDRq5SbfTFGfav5w4/u0rTkSjIBAhJofn" +
       "HvsrfmROCPXuPWXtc5jH6d+5lUudjf0LfjF4/mQPc4FNOP1be8JtItf6XaRp" +
       "sjpYdyWzuIot35ydefOFmVnB3ZIEFGLGMFSC9XIU5RNb/TjzsF7jFr4HAP8u" +
       "zjEXid0Ari6LM+un4FrCLmaEi5GuEMcce+2jqDFH6F1KNluwiSd5RViyosH1" +
       "hJ1ihlXhiKCoo0JLxw6qzrL7pHMHks7Mt9SvmN/9KW9S/HtKA1wWsgVVDQNn" +
       "aFxrWiSrcMMbHBh1UEyPWBvqNSFFnQG3WnPoD8AdPEpPUTX7hMkoRUtCZBBf" +
       "dxQmgukqIGLDountXc9inW9oe4qoBHvNKBL3lBQUv757sFRwLvDj0tn57Tvv" +
       "v3zzSY5xNXDxn57m1z24vTrdmw9t6xaV5smq3db3W/O5hvVePjezV7vbskVs" +
       "W1O5sxnSTMp7kenXV7x266n5L3hr9Tdj6mHhVxEAAA==");
}
