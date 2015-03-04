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
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYfWwcxRWfW9vnjzi+i00SN3Vsx3EojuG2QQIpNQWcq00c" +
                                                    "LonrM5biqFzmdud8m+ztLrtz9sXBlEStHKhIKzBpglL/gYL4aCCoakSrCikI" +
                                                    "UZJS/khVtU2lhqr/NIXmj/wBRaWFvpnd26+7C/1QT9q9uZn33sz7+r03d+Ya" +
                                                    "arBMNGjo6sEZVacJUqKJ/eodCXrQIFZiR+qOcWxaRE6q2LImYS4j9b0a++iT" +
                                                    "7+XjAopOow6saTrFVNE1a4JYujpL5BSKebMjKilYFMVT+/EsFotUUcWUYtGh" +
                                                    "FFrhY6WoP1U+gghHEOEIIj+COOxRAdNKohULScaBNWo9hB5BkRSKGhI7HkUb" +
                                                    "gkIMbOKCI2acawASmtjvKVCKM5dM1OvqbutcofDTg+LS9x+M/6gOxaZRTNHS" +
                                                    "7DgSHILCJtOotUAKWWJaw7JM5Gm0SiNEThNTwaoyz889jdotZUbDtGgS10hs" +
                                                    "smgQk+/pWa5VYrqZRYnqpqteTiGqXP7VkFPxDOi6xtPV1nCUzYOCLQoczMxh" +
                                                    "iZRZ6g8omkxRT5jD1bH/fiAA1sYCoXnd3apewzCB2m3fqVibEdPUVLQZIG3Q" +
                                                    "i7ALRetqCmW2NrB0AM+QDEWdYbpxewmomrkhGAtFq8NkXBJ4aV3ISz7/XNt1" +
                                                    "17FD2nZN4GeWiaSy8zcBU3eIaYLkiEk0idiMrZtTx/Ga148KCAHx6hCxTfPa" +
                                                    "w9fvvbX7/AWb5otVaHZn9xOJZqTT2bZLXcmBrXXsGE2GbinM+QHNefiPOytD" +
                                                    "JQMyb40rkS0myovnJ36+59GXyAcCahlDUUlXiwWIo1WSXjAUlZj3EY2YmBJ5" +
                                                    "DDUTTU7y9THUCOOUohF7dncuZxE6hupVPhXV+W8wUQ5EMBM1wljRcnp5bGCa" +
                                                    "5+OSgRBaCQ9qh6cV2R/+TdHXxbxeICKWsKZougixS7Ap5UUi6aKFC4YKXrOK" +
                                                    "Wk7V50TLlETdnHF/S7pJxElIG0iFJJbyJMFCy/h/CC0xTeJzkQgYuSuc4ipk" +
                                                    "x3ZdlYmZkZaK20auv5J5R3BD3rEBRethm4SzTYJtk/BvgyIRLv0mtp3tPjD+" +
                                                    "AUhjALjWgfQ3duw72lcHcWPM1YPlGGkf6OOcYUTSk16uj3FEkyDgOp/du5j4" +
                                                    "+Pl77IATawNzVW50/sTc4alvfllAQhBhmU4w1cLYxxkuuvjXH86sanJji1c/" +
                                                    "Ont8QfdyLADZTupXcrLU7Qtb39QlIoMVPfGbe/G5zOsL/QKqBzwADKQYYhbg" +
                                                    "pTu8RyCFh8pwyHRpAIVzulnAKlsqY1gLzZv6nDfDw6KNj1eVY7oTnpgT5Pyb" +
                                                    "rXYY7H2THUbMyyEtONyO/vT8yXPPDG4V/Mgc89W6NKF2nq/ygmTSJATm/3Bi" +
                                                    "/Kmnry3u5RECFBurbdDP3knIenAZmPXbFx66/N6V078WvKiiqNEwlVkAgxII" +
                                                    "udnbBkBBBWBizu9/QCvospJTcFYlLDr/Edu05dxfj8Vtd6owU46GWz9fgDf/" +
                                                    "hW3o0Xce/Fs3FxORWFHyVPfIbAt0eJKHTRMfZOcoHf7V+pNv4x8AZgJOWco8" +
                                                    "4dCDuGqI217kvtrM34nQ2hb26jUq1kp8ptNNu4HaWTTKaqsv+/6+W80e+dPH" +
                                                    "XKOK/KlSUkL80+KZU+uSd3/A+b1AZtw9pUoggj7E4739pcKHQl/0LQE1TqO4" +
                                                    "5DQ5U1gtsnCZhsJulTsfaIQC68EibVekITdRu8JJ5Ns2nEIeAMKYUbNxSyhr" +
                                                    "OpiVp8o1ovztz5oI4oOtnKWPvzex1y3cJwIbDlAUtXgrVaLQldnAaoG3Ntf2" +
                                                    "VrqYtaiv9D+hLL/7iw9jh22wDLqZd38Oa5jv8u/qbl9B+7/LYbI+iy2uZhPY" +
                                                    "wmKUFPXW7iS5rCFukRW2RT6DD4LnU/YwS/AJXizXevmwHVv5ndhI8F7VMOwY" +
                                                    "XU39yOCQsIURMMSGzzFERhorZNLnLi/eycMtNqtA80DkSafBDaajV5qGAk1v" +
                                                    "VVNlpKtnn7iw4f2pDt7NlK3iRzY4ZwWyOedvaPz9G2+u2XepDgmjqEXVsTyK" +
                                                    "eVFAzYDGxMpD5S0Z99xrdxVzTfCOs6gAYZtqqOzoxPEnIz186tN3/7Jw5WId" +
                                                    "igLCsyDHJvRC0Gwlal0j/AL6J2H0NeCC4G+zuaGp5eHhhEG7O+sWK4puqyWb" +
                                                    "3ZLCNY014tA1EHObXtRknv/B5GopGoZ/lQdU6/8SUI8Aev8b5nO1d3KX9XgQ" +
                                                    "EW3cjQw/EiNwx/IvQifUkUwNp9OZyT3jI5mp4Ymx4W2pER6mBixGpsqxHPeE" +
                                                    "2CDkRnlnrVaKE+xirwlugilP7P1suKdURbh9DQngvF0R4k5eVUIOoI1RzKoK" +
                                                    "VKmGnKJhNcgMsbe+1hWDX49OH1lalnc/t8WGmvZg284s9vJv/vnLxIk/XqzS" +
                                                    "RzZT3bhNJbNE9e3Jwn19oB/cyW9fXkF4/MUfvkYvDX7F3vIGsBhmfPvI++sm" +
                                                    "787v+w+6wJ6Q8mGRL+48c/G+m6UnBVTn1pWKC2WQaSgU8JBDRVObDNSUbrem" +
                                                    "fIm5YQs8XU7kdVXvxKp6t44NeR59lUISHdQk6Po0aCfkUqh9ELwClLlxZzBu" +
                                                    "KgW4i806l0Vxof29A6euvmw7I9wGhIjJ0aXHP0scWxJ81++NFTdgP499Bec6" +
                                                    "rvRAIFIdBNqTzj2w170IsoLirxhVjsW3GP3z2YWfvbCwKDgJk4f+MavrKsEa" +
                                                    "/z17g3brMHtBOLfMEOpkL5vZW9mA8QnV9W0Pmxx0nvL4v/Ate9GwR/0nfOwG" +
                                                    "a99hr2+x7FeLVp6TzLHXIVvgAkX1s7oiV+kmIaT89z5W9Dor/jSy/+iQXlmO" +
                                                    "Na1dfuC3/Cbj/hnRnEJNuaKq+psr3zhqmCSn8FM2262Wwb+eAtAL4yYck33x" +
                                                    "Uz5pkx2naIWPDDzqjPxEJymqAyI2fMawgRUFENAI4+HGmj3CzqL9d1pGOru8" +
                                                    "Y9eh63c+x5GmASrR/LxTQhvtu5gLMBtqSivLim4f+KTt1eZN5dBsY692X2j4" +
                                                    "ztZT/ZoyUjAov1jM/2Ttj+96fvkKvyj9Cx3uB53lFAAA");
}
