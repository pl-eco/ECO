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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "LRRVU9O6TSfjRI8k9bKCUtrPe90gmZ565bV36Ger7hJ2dszu5ijjuf0/LB26" +
       "O7PzPxTQlohN0SNf6T/18aablWckVObnZFH7XsjUWZiJNTaB4DeGCvKx2Y//" +
       "BhYWS+BZ6Mb/wpJFLHBZAKeSe5+u85qLnMdNJfB1wPDaI1scJhsUv10DvVzM" +
       "Q9cB7BQbHoCKlbNUiFpOcx8bNgvI7gNgSJmmTrBRjOp8YXth5e6Ap8k1uulf" +
       "Gx0rjNhlRUYPZrAKjR77NiT8mLHrGMU77gwYxUvknlJGlY+bmlqiTlHUUKKv" +
       "ZNWyseg7Vnx7KW/MxOfcMLP9K94p+d9H1fCRks7pehi9Q/NKyyZpjWtbLbBc" +
       "JPAEfPLN3vNC8vlzrvu44JqkqC7KBTaynzDZFEB7iAxc687CRHspKgMiNt1n" +
       "eQ6p460Cq2UJUcvyKJT8rFsKvxW0Tiy/+X8KvFzMif8VjCinZ/ruf/TqnSd5" +
       "YlcoOp6c5F+W8KEsukY/n5fPepp3VuXmlb/Nf7N6hYdT89lQ77aKEd1aSndU" +
       "PVmL8h5o8t0b3l7/0swF3tL9Df0dn+XCEQAA");
}
