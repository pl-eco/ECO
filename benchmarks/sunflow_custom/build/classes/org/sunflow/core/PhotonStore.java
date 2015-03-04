package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Vector3;
public interface PhotonStore {
    int numEmit();
    void prepare(BoundingBox sceneBounds);
    void store(ShadingState state, Vector3 dir, Color power, Color diffuse);
    void init();
    boolean allowDiffuseBounced();
    boolean allowReflectionBounced();
    boolean allowRefractionBounced();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0Zf3AU1fndXX4TuBAgSfkRSAhSQO6kFVoIIkkIEjwg5SKO" +
                                "qfXY7L7LLeztLrvvkiMKo3YcGGdAp8YKFjJjB9rSKjCdMrbTsUNrR0GkMzja" +
                                "FlvR/lORlg78obaDSr/v7d7d3uZyXGLazOyX3fe+974f7/v57sWrpNg0yCJd" +
                                "U3b2KhoL0CQLbFOWBthOnZqB9aGlnYJhUqlNEUyzC8YiYuNJ/yc3no5VeUlJ" +
                                "N5kiqKrGBCZrqrmZmprSR6UQ8WdG2xUaNxmpCm0T+oRggslKMCSbrDlEJjiW" +
                                "MtIUSrEQBBaCwEKQsxBsyWDBoolUTcTbcIWgMnMH2U08IVKii8geIw3Zm+iC" +
                                "IcTtbTq5BLBDGX5vAaH44qRB5qRlt2QeJvCzi4KDzz1U9XMf8XcTv6yGkR0R" +
                                "mGBApJtUxmm8hxpmiyRRqZtMVimVwtSQBUUe4Hx3k2pT7lUFljBoWkk4mNCp" +
                                "wWlmNFcpomxGQmSakRYvKlNFSn0VRxWhF2StychqSbgWx0HAChkYM6KCSFNL" +
                                "irbLqsTIbPeKtIxN9wICLC2NUxbT0qSKVAEGSLV1doqg9gbDzJDVXkAt1hJA" +
                                "hZHpI26KutYFcbvQSyOM1LnxOq0pwCrnisAljExzo/Gd4JSmu07JcT5XN67c" +
                                "/7C6TvVyniUqKsh/GSyqdy3aTKPUoKpIrYWVC0PfF2pe2eslBJCnuZAtnJcf" +
                                "ub769vrTZyycGTlwNvVsoyKLiEd6Jl2Y2bZguQ/ZKNM1U8bDz5Kcm3+nPdOc" +
                                "1MHzatI74mQgNXl682sPPPpT+g8vqeggJaKmJOJgR5NFLa7LCjXuoSo1BEal" +
                                "DlJOVamNz3eQUngPySq1RjdFoyZlHaRI4UMlGv8GFUVhC1RRKbzLalRLvesC" +
                                "i/H3pE4IKYWHeOBZRay/CgSMiMGYFqdBQRRUWdWCYLtUMMRYkIpaxKC6Fmxv" +
                                "2xTsAS3H4oKx3QyaCTWqaP0RMWEyLR40DTGoGb2p4aCoGTTYGdOYBo4F7wE0" +
                                "Nv3/QyaJ0lb1ezxwEDPdYUABD1qnKRI1IuJgorX9+vHIOW/aLWw9MTITqARs" +
                                "KgGkEnBQIR4P33wqUrNOGM5nO3g6xMDKBeHvrN+6t9EHpqX3F6F2k9z16lIf" +
                                "sNDFFXfytb86ffDU84uWe53xwO+IsGHKLOuanKHbZVAK4+8d6Hzm2at7vs2J" +
                                "AsbcXASaELaBrUEABTGeOLPj4vuXjrztTTPqYxB0Ez2KLDJSJvRAxBJExkh5" +
                                "OvQME2TWSP7LY8+RxweHpE1Hl1heVp3tE+0Q8l/64+dvBg58cDbHAZQzTV+s" +
                                "0D6qOGhWIUkwFZtau6ht4KGtg2cOERz7yWM/e5ldWLTCIrlw5AToXvj641em" +
                                "d62KbfUSb3YSQ+owVIErOzH1pFPMbJfw7i2PbXjx7D23id/zEp8dwXJE6+xF" +
                                "zU41AFGDQnpRUaE4UgFEG90GbWgilSAHZegunCOciryyq8lLiiAMQ+phAoQK" +
                                "iOr1buJZkbM5ZXVIqhiUENWMuKDgVCp1VLCYofVnRrinTeLvk+F4ytAs/HhO" +
                                "dmzh/3F2io5wquWZHH8Gh/UIGvjZevG1EcFctKzbMjYOcVCBWIyH0XSfGtck" +
                                "OSoLPQpFb/vMP2/JqX/ur7IsSIGR1OncfusNMuNfaSWPnnvo03q+jUfEPJzx" +
                                "uwya5X5TMju3GIawE/lIPvbWrIOvC4chTUBoNuUByqMtsV0FmVrBJV7K4XLX" +
                                "3EoESxgpBadoj8sMqCzIU7wZMuDIfXbCC+6qfn/7ocsvWTbvzo4uZLp38Mmb" +
                                "gf2DXkcJMXdYFneuscoIzuZE66hvwp8Hni/wQRlwwEoj1W12LpuTTma6jufZ" +
                                "kI8tTmLthyd2/fonu/Z4bZ3MZ8QHkUcfpjA+sChtdXxwATy1ttXVjtXqss/H" +
                                "wxE8/HuaKyPEIacGWrWEKkHd1KolOYkNeQ74WwjWwQHrkOUEw/KfVQhaLbpr" +
                                "GCnq02SpMHHvhafBFrdhfMQt4ghFKXFnDUuA4ZiA0mKdTFNYdcOUsoVikfv1" +
                                "FEKtE0GOQ2mI3qgZOG0l6gfzaE1CcD+DZgbTLn6EC9MPBqH5tn7mj49+nHwp" +
                                "eeZUBL0MazCZFcZyJQ7OhidgsxwYf5b78sxxsINB26fAMa2Ro9GESdG6IbPk" +
                                "MtTSHk1TqKAWJlgjPMtswZaNv2DfzTP3BILdEM25YJDt7Dhuy4azA4ULcZct" +
                                "xF3jL8S+PHNPIdjrEAKrslEJwcNz7nhdk2kCrY4nwJtuiNl5hPJlhIKKEVpg" +
                                "COP5pDuYZ+4HFqMInkmmokYVz7DIVcDiKh8fEB6isirw9vA5vucBBM8jOASF" +
                                "bC9lvCw189aD4QTUu472c588dP6Nj/2PWUk1OxnzGwh7qXvdxT/7vjaBNT3F" +
                                "68iiHsHkYasMaisTMRmZM/JtBt/LyrQTbnly0zInx8mnDy6lRH9GiRwBh49l" +
                                "ZeLcSoiIHfFI+NTFPct4jeDvk6F5pVKXfcGSXRtl+p7mrEuXnGqKiJdP7DvT" +
                                "cGXLFN5NpzTi7HE2CPqwHmedYMZgvLj03d++WrP1go9415IKRROktQLmGmiL" +
                                "oSylZgy6uqR+92ruo5X9WIxi/YmN2rwRRLZl4sVgRHzk0BfnP9p16ayPlECp" +
                                "i/U6JGoozRkJjHSN5dygqQve1sAqKKQnWashXaZVDyZQnR5NV+2MLB5pb17o" +
                                "uYp7vAiCIEANXnvgtne4OoaErjtnuTFVjt2YdkMhXYDy0rLbIZJUc7eZlDFB" +
                                "bPeck9DiTWkLtYTDka4HOtsjW1o2d7S0htq5keow6enC18PJPKd3v6xIbYIh" +
                                "WY539Gb53NW1Z1dwxxuupDEqpo4rRuhnQSxsNZWqmQipWxwOa4qbRuDXcecZ" +
                                "EZ9++9rELdd+c537mLvfPG41VnZQNEit+6LBdok7T298sEo5fQM26YZNRJGa" +
                                "5iZDogZff9L2haq0L5AkyRmEf4jgRwheRfCaMyYXlges4Jsnzp/LM3feTe/w" +
                                "rZKpFeffQPAmgj9AnI+BTto0iXtCC1/6O0dW+X1OZUATn7tNbI/rjDd2A7+s" +
                                "/cXKHw9d4rckuNEFvj+CtxC8U6Cubt1oWNaE8N08uvprgbri2zVyjIsI/oLg" +
                                "PUjXdEdCsOrXgcKUxKVE8AFfhOBv4yS5U7AP88x9NHqh/47gMoIrYBtMs664" +
                                "cxQYjonCVXEVwb8QXBuNKkbjMJ/kmft3gfrI0Hsh4zUfI/gUwX+wetOYHN2J" +
                                "X+FR6uAzvgjB5/8jHXh8eeaKv4QOPGiTniIEJYyUWzpoUZQxqMFTnlKDp2I8" +
                                "1eC8fXDfEyia2svFqMqzCKy8PmPlHXiFayR0KOTakyLVMcrxLaZ9GTX6EUxF" +
                                "UAds9Qt2wztKDc5Ia3DmaDSYJ6540wieyTjWwrltHDHuemo4QtOo89ALfF0D" +
                                "AjxGz7yMPGNWwlfHSQlOZwncSvQ7xij6YgRBBEvGQfQ7+ViSkQmOX1uwG6gb" +
                                "9muu9QukeHzIX1Y7dN+f+F13+lfC8hApiyYUxVEYO4vkEt2gUZmLWW5VWrzd" +
                                "9XwTEoP72gvsGv+hGJ5vWGjNwJ8DjZFS+82JtIoRHyDh691QK5KsIlF3l4xz" +
                                "R2ycNiSs37gj4omh9Rsfvr7sKC9wi6EIHeC5GPqKUuumnm+KV98NI+6W2qtk" +
                                "3YIbk06Wz0vdtfJSs9phaHWObPPOfwEwmgxYTyAAAA==");
}
