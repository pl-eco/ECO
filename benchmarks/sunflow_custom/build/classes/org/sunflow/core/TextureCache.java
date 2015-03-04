package org.sunflow.core;
import java.util.HashMap;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public final class TextureCache {
    private static HashMap<String,Texture> textures = new HashMap<String,Texture>(
      );
    private TextureCache() { super(); }
    public static synchronized Texture getTexture(String filename,
                                                  boolean isLinear) {
        if (textures.
              containsKey(
                filename)) {
            UI.
              printInfo(
                Module.
                  TEX,
                "Using cached copy for file \"%s\" ...",
                filename);
            return textures.
              get(
                filename);
        }
        UI.
          printInfo(
            Module.
              TEX,
            "Using file \"%s\" ...",
            filename);
        Texture t =
          new Texture(
          filename,
          isLinear);
        textures.
          put(
            filename,
            t);
        return t;
    }
    public static synchronized void flush() { UI.printInfo(Module.
                                                             TEX,
                                                           "Flushing texture cache");
                                              textures.clear(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcRxWfu7PPf+L4LnaTmODYjuMUHLe3pFIrBZe2znFu" +
                                                    "nF5iy+daiiN6mdud8228t7vdnbUvTl2aCORQ1IBaNyRV8AeUqrSkTYWICkKV" +
                                                    "ghA0ofRDEAKCRIr4QmjJh3xoqSi0vJnd293bu0v5I3HS7s3NvPdm3r/fe3Pn" +
                                                    "bqBG00BDuqYcnlU0miAlmjik3J2gh3ViJvak757AhkmkpIJNcwrmsmL/q7H3" +
                                                    "P/xmIR5G0RnUiVVVo5jKmmpOElNT5omURjFvNqWQoklRPH0Iz2PBorIipGWT" +
                                                    "DqfRGh8rRQPp8hEEOIIARxD4EYQRjwqY1hLVKiYZB1ap+Sh6HIXSKKqL7HgU" +
                                                    "bakUomMDFx0xE1wDkNDMfk+DUpy5ZKA+V3db5yqFnx0SVr71SPz7ERSbQTFZ" +
                                                    "zbDjiHAICpvMoLYiKeaIYY5IEpFm0DqVEClDDBkr8iI/9wzqMOVZFVPLIK6R" +
                                                    "2KSlE4Pv6VmuTWS6GZZINcNVLy8TRSr/aswreBZ03eDpams4yuZBwVYZDmbk" +
                                                    "sUjKLA1zsipR1BvkcHUceAgIgLWpSGhBc7dqUDFMoA7bdwpWZ4UMNWR1Fkgb" +
                                                    "NQt2oWhTXaHM1joW5/AsyVLUFaSbsJeAqoUbgrFQtD5IxiWBlzYFvOTzz419" +
                                                    "9544ou5Ww/zMEhEVdv5mYOoJME2SPDGIKhKbsW17+iTe8PrxMEJAvD5AbNO8" +
                                                    "9tjNB+7ouXjJpvl0DZrx3CEi0qx4Ntd+pTs5uDPCjtGsa6bMnF+hOQ//CWdl" +
                                                    "uKRD5m1wJbLFRHnx4uTP9z/xEnk3jFrHUFTUFKsIcbRO1Iq6rBDjQaISA1Mi" +
                                                    "jaEWokpJvj6GmmCcllViz47n8yahY6hB4VNRjf8GE+VBBDNRE4xlNa+Vxzqm" +
                                                    "BT4u6QihtfCgDnjakP3h3xRJQkErEgGLWJVVTYDYJdgQCwIRtaxBdE1IJceF" +
                                                    "HFi5UMTGnCmYlppXtIWsaJlUKwqmIQqaMVueFkTNIMIUZBJkRxKLBZJg0ab/" +
                                                    "n/YpMX3jC6EQuKI7CAQK5NBuTZGIkRVXrF2pm69k3wy7ieFYiqLNsE3C2SbB" +
                                                    "tkn4t0GhEJd+G9vOdjK4aA6SHWCwbTDzpT0Hj/dHILr0hQawLyPtBxWdM6RE" +
                                                    "LekhwhjHPRHCsus7B5YTH7xwvx2WQn34rsmNLp5aODr95c+FUbgSh5lOMNXK" +
                                                    "2CcYerooORDMv1pyY8vX3z9/cknzMrEC2B2AqOZkCd4ftL6hiUQCK3rit/fh" +
                                                    "C9nXlwbCqAFQA5CSYohsAKGe4B4ViT5cBk2mSyMonNeMIlbYUhnpWmnB0Ba8" +
                                                    "GR4W7Xy8rhz5XfDEnFTg32y1U2fv2+wwYl4OaMFBefRHF09feG5oZ9iP3zFf" +
                                                    "RcwQaqPBOi9IpgxCYP4PpyaeefbG8gEeIUCxtdYGA+ydBGwAl4FZv3rp0atv" +
                                                    "Xzv767AXVRQ16YY8D5BRAiG3e9sAdCgAX8z5Aw+rRU2S8zLOKYRF5z9i23Zc" +
                                                    "+OuJuO1OBWbK0XDHJwvw5j+1Cz3x5iN/6+FiQiIrXZ7qHpltgU5P8ohh4MPs" +
                                                    "HKWjv9p8+g38bUBWQDNTXiQcoBBXDXHbC9xX2/k7EVjbwV59etVaic90uWk3" +
                                                    "WD+LRlkF9mXf38eV3LE/fcA1qsqfGoUnwD8jnDuzKXnfu5zfC2TG3VuqBiLo" +
                                                    "Vjzeu14qvhfuj/4sjJpmUFx0WqFprFgsXGag/Jvl/gjapYr1ylJu161hN1G7" +
                                                    "g0nk2zaYQh4AwphRs3FrIGs6mZWny5Wk/O3PmhDig52cpZ+/t7HXZ7lPwmw4" +
                                                    "SFHU5A1XiULvZgOrCd7aXt9bGStnUl+D8JS8+tYv3osdtcGy0s28R3RYg3xX" +
                                                    "fxe5aw0d+AaHyYYcNrmazWALk1FS1Fe/3+SyhrlF1tgW+Rg+CJ6P2MMswSd4" +
                                                    "Sd3o5cNubBb2Yj3BO1pdt2N0PfUjg0PCFlJgiC2fYIisOFbMZi5cXb6Hh1ts" +
                                                    "XoYWg0hTThtcmY5eaRquaI1rmiorXj//1KUt70x38p6nbBU/ssE5q5DNOX9j" +
                                                    "0+9/8tMNB69EUHgUtSoalkYxLwqoBdCYmAWovCX9/gfs3mOhGd5xFhUgbFsd" +
                                                    "lR2dOP5kxcfOfPTWX5auXY6gKCA8C3JsQMcELVmi3mXDL2BgCkZfBC4I/nab" +
                                                    "G1pfHh5OGHS4s26xoujOerLZXSpY01i7Dl0DMXZplirx/K9MrlZL1/2rPKDa" +
                                                    "/peAehzQ+98wn6u9k7usE4SIaOduZPiRSMFNzL8InVBnMj2SyWSn9k+kstMj" +
                                                    "k2Mju9IpHqY6LIamy7Ec94TYIORGeVe9VooT7GOvSW6CaU/sQ2y4v1RDuH1Z" +
                                                    "qcB5uyLEnbyqhhxAG93KKTJUqca8rGKlkhlib3O9iwi/RJ09trIqjT+/w4aa" +
                                                    "jsrmnlns5d/885eJU3+8XKOPbKGafqdC5oni25OF++aKfnAvv6N5BeHJF7/3" +
                                                    "Gr0y9Hl7y1vAYpDxjWPvbJq6r3DwP+gCewPKB0W+uPfc5QdvF58Oo4hbV6qu" +
                                                    "nZVMw4GAhxyyDHWqoqb0uDXlM8wNO+DpdiKvu3YnVtO7ETbkefQFCkl0GK4O" +
                                                    "hqZCOyGVAu1D2CtA2Vt3BhOGXIQb27xzpRSWOt6eO3P9ZdsZwTYgQEyOrzz5" +
                                                    "ceLESth3Sd9adU/289gXda7jWg8EQrVBoCPp3Bb73OsiKyj+ilHjWHyL0T+f" +
                                                    "X/rxd5eWw07CFKB/zGmaQrDKf8/fot06yl4Qzq2zhDrZy2YOVDdgfEJxfdvL" +
                                                    "Joecpzz+L3zLXjToUf8Jv3aLta+z11dY9iuWWeAkC+x1xBa4RFHDvCZLNbpJ" +
                                                    "CCn/vY8Vva6qv5bsv0PEV1ZjzRtXH/4tv8m4f1m0pFFz3lIUf3PlG0d1g+Rl" +
                                                    "fsoWu9XS+dczAHpB3IRjsi9+yqdtspMUrfGRgUedkZ/oNEURIGLD53QbWFEF" +
                                                    "AupBPNxat0fYa9l/umXF86t79h25ec/zHGkaoRItLjoltMm+i7kAs6WutLKs" +
                                                    "6O7BD9tfbdlWDs129urwhYbvbL21rympok75xWLxhxt/cO8Lq9f4RelfxIAj" +
                                                    "iwsVAAA=");
}
