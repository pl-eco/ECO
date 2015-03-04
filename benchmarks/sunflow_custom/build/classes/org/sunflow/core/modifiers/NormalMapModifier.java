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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "oMXloOKRLQiTDTrfzak+ISZ1HVTZzV/9AKsFS4UIvT5+pKiWhx5wzG1S5cm2" +
       "K6MvfPua49EoWESIyZGpx/+KH52SQm3/yrLOO8zjtP5CyznOwf4Fvxg8f/KH" +
       "m8AnnNavLeH2n8v9BtSyeB6suJ5aQsSWb85Ovv3y5GHJPZJeSNiMaeoEG+Vo" +
       "Kyb6SitqNzztrp/b/7WfY6XZvLTMz4M5rMKNht/piNiGXMePGn9lwI+idB0Q" +
       "NPfw1/2OynsYqh4zNbVC/WCotazb4zWsvez26dyYlDPTzbMWTu/6XPQv/q2m" +
       "Hq4W2YKuhzE1NK61KMlqQtd6B2EdaNsPF7WZ+1BAG38sNLccLgb39igXWMg/" +
       "YbJxhmaHyMCx7ihMNMFQFRDx4UEflVtEAecVJu5UmCIKoR3vYcL/Shoanjbi" +
       "fu+BT8G54aeVs9Pbdxy8dusJgWQ1io4nJsR9EK63Ti/nA9iKGXfz9qrdtua3" +
       "pnP1q7yobeKvNreBi+i2rHKf05u3mOhMJt5c+MbtJ6cvi0brb/uiEVZ4EQAA");
}
