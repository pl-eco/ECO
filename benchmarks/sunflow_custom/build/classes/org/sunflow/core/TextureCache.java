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
    public static final long jlc$SourceLastModified$jl7 = 1170616086000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYb2wcRxWfu7PPf+L4HLtJTHBsx3EKjttbUqmVgktb57Ab" +
                                                    "p5fE8jmW4kAvc7tzvo33dre7s/bFqUsTgZwWNaDWDUkV/AGlKi1pUyGiglCl" +
                                                    "IARNKP0QhIAgkSK+EFryIR9aKgotb2b39t/dpfwRJ+3e3Mx7b+b9+703d+4G" +
                                                    "qjcNNKhryuEZRaNJUqLJQ8rdSXpYJ2ZyV/rucWyYREop2DQnYS4r9r2aeP/D" +
                                                    "bxbaoig+jTqwqmoUU1lTzQliasockdIo4c2OKKRoUtSWPoTnsGBRWRHSskmH" +
                                                    "0miVj5Wi/nT5CAIcQYAjCPwIwrBHBUyriWoVU4wDq9R8BD2GImkU10V2PIo2" +
                                                    "BYXo2MBFR8w41wAkNLLfU6AUZy4ZqNfV3da5QuFnB4Xlbz3c9v0YSkyjhKxm" +
                                                    "2HFEOASFTaZRS5EUc8QwhyWJSNNojUqIlCGGjBV5gZ97GrWb8oyKqWUQ10hs" +
                                                    "0tKJwff0LNciMt0MS6Sa4aqXl4kilX/V5xU8A7qu83S1NRxl86BgswwHM/JY" +
                                                    "JGWWullZlSjqCXO4OvY/BATA2lAktKC5W9WpGCZQu+07BaszQoYasjoDpPWa" +
                                                    "BbtQtKGmUGZrHYuzeIZkKeoM043bS0DVxA3BWChaGybjksBLG0Je8vnnxp57" +
                                                    "TxxRd6pRfmaJiAo7fyMwdYeYJkieGEQVic3YsjV9Eq97/XgUISBeGyK2aV57" +
                                                    "9OYDd3RfvGTTfLoKzd7cISLSrHg213qlKzWwPcaO0ahrpsycH9Cch/+4szJU" +
                                                    "0iHz1rkS2WKyvHhx4uf7H3+JvBtFzWMoLmqKVYQ4WiNqRV1WiPEgUYmBKZHG" +
                                                    "UBNRpRRfH0MNME7LKrFn9+bzJqFjqE7hU3GN/wYT5UEEM1EDjGU1r5XHOqYF" +
                                                    "Pi7pCKHV8KB2eFqQ/eHfFH1J2GdCuAtYxKqsagIEL8GGWBCIqGVzYN1CERuz" +
                                                    "piBaJtWKgmmpeUWbF0xDFDRjxv0tagYRJiGDICtSWCyQJIsy/f8sv8T0a5uP" +
                                                    "RMD0XeHEVyBndmqKRIysuGztGLn5SvbNqJsIjmUo2gjbJJ1tkmybpH8bFIlw" +
                                                    "6bex7WyngktmIbkB9loGMl/edfB4XwyiSZ+vA3sy0j7QzDnDiKilPAQY4zgn" +
                                                    "Qhh2fufAUvKDF+63w1CoDddVudHFU/NHp77yuSiKBnGX6QRTzYx9nKGli4r9" +
                                                    "4XyrJjexdP398ycXNS/zAkDuAEIlJ0vovrD1DU0kEljRE7+1F1/Ivr7YH0V1" +
                                                    "gBKAjBRDJAPodIf3CCT2UBkkmS71oHBeM4pYYUtlZGumBUOb92Z4WLTy8Zpy" +
                                                    "pHfCk3BCn3+z1Q6dvW+zw4h5OaQFB+HRH108feG5we1RP14nfBUwQ6id/Wu8" +
                                                    "IJk0CIH5P5waf+bZG0sHeIQAxeZqG/SzdwqwAFwGZv3apUeuvn3t7K+jXlRR" +
                                                    "1KAb8hxARAmE3O5tA1ChAFwx5/fvU4uaJOdlnFMIi85/JLZsu/DXE222OxWY" +
                                                    "KUfDHZ8swJv/1A70+JsP/62bi4mIrFR5qntktgU6PMnDhoEPs3OUjv5q4+k3" +
                                                    "8LcBSQG9THmBcEBCXDXEbS9wX23l72RobRt79eoVayU+0+mm3UDtLBplFdeX" +
                                                    "fX/fq+SO/ekDrlFF/lQpNCH+aeHcmQ2p+97l/F4gM+6eUiUQQXfi8d71UvG9" +
                                                    "aF/8Z1HUMI3aRKf1mcKKxcJlGsq9We6HoD0KrAdLt12nhtxE7QonkW/bcAp5" +
                                                    "AAhjRs3GzaGs6WBWnipXjvK3P2siiA+2c5Y+/t7CXp/lPomy4QBFcZM3WCUK" +
                                                    "vZoNrCZ4a2ttb2WsnEl9DcFT8spbv3gvcdQGy6CbeU/osIb5rv4udtcq2v8N" +
                                                    "DpN1OWxyNRvBFiajpKi3dn/JZQ1xi6yyLfIxfBA8H7GHWYJP8BK63suHndgs" +
                                                    "7MZ6knewum7H6FrqRwaHhC2MgCE2fYIhsuJYMZu5cHXpHh5uiTkZWgoiTTpt" +
                                                    "bzAdvdI0FGiFq5oqK14//9SlTe9MdfAep2wVP7LBOSuQzTl/fcPvf/LTdQev" +
                                                    "xFB0FDUrGpZGMS8KqAnQmJgFqLwl/f4HeOjE5hvhzaMChG2pobKjE8efrPjo" +
                                                    "mY/e+svitcsxFAeEZ0GODeiQoAVL1rpc+AX0T8Loi8AFwd9qc0Ory8PDCYN2" +
                                                    "d9YtVhTdWUs2uzuFaxprz6FrIMYOzVIlnv/B5Gq2dN2/ygOq5X8JqMcAvf8N" +
                                                    "87naO7nLOj+IiFbuRoYfyRG4efkXoRPqSKWHM5ns5P7xkezU8MTY8I70CA9T" +
                                                    "HRYjU+VYbvOE2CDkRnlnrVaKE+xhrwlugilP7ENsuL9URbh9OQngvF0R2py8" +
                                                    "qoQcQBvdyikyVKn6vKxiJcgMsbex1sWDX5rOHltekfY+v82GmvZgM88s9vJv" +
                                                    "/vnL5Kk/Xq7SRzZRTb9TIXNE8e3Jwn1joB/cze9kXkF48sXvvUavDH7e3vIW" +
                                                    "sBhmfOPYOxsm7ysc/A+6wJ6Q8mGRL+4+d/nB28Wnoyjm1pWKa2aQaSgU8JBD" +
                                                    "lqFOBmpKt1tTPsPcsA2eLifyuqp3YlW9G2NDnkdfoJBEh+HqYGgqtBNSKdQ+" +
                                                    "RL0ClL11ZzBuyEW4oc05V0hhsf3t2TPXX7adEW4DQsTk+PKTHydPLEd9l/LN" +
                                                    "FfdiP499Mec6rvZAIFIdBNpTzu2w170esoLirxhVjsW3GP3z+cUff3dxKeok" +
                                                    "TAH6x5ymKQSr/PfcLdqto+wF4dw8Q6iTvWzmQGUDxicU17c9bHLQecrj/8K3" +
                                                    "7EXDHvWf8IlbrH2dvb7Ksl+xzAInmWevI7bARYrq5jRZqtJNQkj5732s6HVW" +
                                                    "/JVk//0hvrKSaFy/su+3/Cbj/kXRlEaNeUtR/M2VbxzXDZKX+Smb7FZL51/P" +
                                                    "AOiFcROOyb74KZ+2yU5StMpHBh51Rn6i0xTFgIgNn9NtYEUBBNTDeLi5Zo+w" +
                                                    "27L/ZMuK51d27Tly857nOdLUQyVaWHBKaIN9F3MBZlNNaWVZ8Z0DH7a+2rSl" +
                                                    "HJqt7NXuCw3f2XqqX1NGijrlF4uFH67/wb0vrFzjF6V/AXJDBOj7FAAA");
}
