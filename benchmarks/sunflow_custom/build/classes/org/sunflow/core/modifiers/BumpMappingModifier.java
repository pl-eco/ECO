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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "3Y4G10X1Umi4a73+UGp3+dzqmjn13AEtTPAHOOrk1PuCTRaJnUrqXXWl9Jjs" +
       "iONkeuKFn7zK393/ZWXn6PpuTjO+ee4vO2fvqzz6PxTQ3Smb0ke+cOzK2/ff" +
       "YXxXQy21nGxo8uuZxuozsYsRCH53ti4fB2vx3yfCYgc8W8P439q0iMUui+FU" +
       "C+8zdN5gg/OkqQS+IQReR2Tbk2Qz6j0+PSnFfP0WgF0Sw0NQsQLPhKiVNF8T" +
       "wxEF2UcBGEqU2gS7jaguF07UV+5ReAZCowf+a6Mz9RG7q8HomQo2odETX5BE" +
       "HrNwC6NkX14Bo2SJPN3MqNZFaplN6hRHfU36SlEt+xu+dtUXmvHyWnbDbWsn" +
       "fic7pdpXVCd8ypQD206id2Le7jFStqS2nQrLVQIvwYfh+j0vJF9tLnVfVFzL" +
       "HPWkucBG8UqSrQC0J8jAteEsSXSGoxYgEtOzXuSQHtkqiFqWU7WsihLJL7ql" +
       "5K+61knkt/x/QpSLgfqPQtG4unb0wcdvfuk5mdhtho2Xl+X3J3xOq66xls97" +
       "1j0tOqv9yL5PNr/SuTfCqc1i6A1bxZRuu5t3VBOOx2UPtPzz23527/Nr78mW" +
       "7j+J86b86BEAAA==");
}
