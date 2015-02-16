package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class InvertedBucketOrder implements BucketOrder {
    private BucketOrder order;
    public InvertedBucketOrder(BucketOrder order) { super();
                                                    this.order = order; }
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = order.
                                                         getBucketSequence(
                                                           nbw,
                                                           nbh);
                                                       for (int i = 0; i <
                                                                         coords.
                                                                           length /
                                                                         2;
                                                            i +=
                                                              2) {
                                                           int src =
                                                             i;
                                                           int dst =
                                                             coords.
                                                               length -
                                                             2 -
                                                             i;
                                                           int tmp =
                                                             coords[src +
                                                                      0];
                                                           coords[src +
                                                                    0] =
                                                             coords[dst +
                                                                      0];
                                                           coords[dst +
                                                                    0] =
                                                             tmp;
                                                           tmp =
                                                             coords[src +
                                                                      1];
                                                           coords[src +
                                                                    1] =
                                                             coords[dst +
                                                                      1];
                                                           coords[dst +
                                                                    1] =
                                                             tmp;
                                                       }
                                                       return coords;
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1160723124000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVYfWwUxxUf3/n7nPpsgyEEbGxMHLDZjQmQBEdNjDHJwYFd" +
                                                   "GwwxoZfx7tx58d7u\nsjt7PgwiiSIBDWob1FZqpYaQComPJg1SWtFKKQUlad" +
                                                   "OgSkmkJlKk0FZIbaU2VaNKKVX7R9/M3N7e\n7d25Uata8u7s7vuY9/V7b+7l" +
                                                   "T1CNY6PliiPRwxZxpOGJMWw7RB3WsePshlcJ5a2ahrHzOwwzhKri\nKKSpFD" +
                                                   "XHFUdWMcWypsqxrYNZG/VZpn44pZtUIlkqHdQ35uRtj28sEbj3zJXWZ85Vd4" +
                                                   "ZQTRw1Y8Mw\nKaaaaYzoJO1QFI0fxBksu1TT5bjm0ME4uoMYbnrYNByKDeoc" +
                                                   "QsdQOI5qLYXJpKgr7imXQblsYRun\nZa5eHuNqQUKbTSjWDKIO5dUBZ38xJ2" +
                                                   "w7xzdeSg1C6tnHSTCH7wCsXpm3WlhbYqoVvjC56ejZi2HU\nPIWaNWOCCVPA" +
                                                   "Egr6plBTmqSnie0MqSpRp1CLQYg6QWwN69o81zqFWh0tZWDq2sQZJ46pZxhh" +
                                                   "q+Na\nxOY6vZdx1KQwm2xXoaad91FSI7rqPdUkdZwCs9t9s4W529h7MLBRg4" +
                                                   "3ZSawQj6V6VjMg4p1BjryN\nPTuAAFjr0oTOmHlV1QaGF6hVxFLHRkqeoLZm" +
                                                   "pIC0xnRBC0XLKgplvrawMotTJEHR0iDdmPgEVA3c\nEYyFosVBMi4JorQsEK" +
                                                   "WC+PS1f3bywnevPsJzu1olis723whMHQGmcZIkNjEUIhhvu9I3Y4+7y0MI\n" +
                                                   "AfHiALGgGVp9ZU/8jz/rFDR3laEZnT5IFJpQdp3uHD/yqInCbBv1luloLPhF" +
                                                   "lvNyGMt9GcxaULXt\neYnso+R9vDb+88efvkT+FEKNMVSrmLqbhjxqUcy0pe" +
                                                   "nEfpQYxMaUqDHUQAx1mH+PoTpYxyHlxdvR\nZNIhNIaqdf6q1uTP4KIkiGAu" +
                                                   "aoC1ZiRNb21hOsPXWQshVAf/6H74r0Hij98p+qIkO66R1M052bEV\n2bRT+W" +
                                                   "fFtIk87SqzhMoxIwP5QdQt/HHUVoktsTyyKJqSZ8w0kbGCDc0w5ZQGlauY61" +
                                                   "SSYff/SXqW\n7b91rqqKAWKwsHWoicdMHWgTyvlb7xwd2fGVkyJpWKLnLKeo" +
                                                   "D5RKOaUSUyoJpVIZpaiqiutaxJSL\nEEIAZqGUAfSa1kwc2P7kye4w5I41Vw" +
                                                   "3eY6TdYGNuRyOKOezXe4xDowJJt/R7+09It88/LJJOrgzL\nZbkj775y4+zf" +
                                                   "mtaGUKg8ZjJLAbUbmZgxBrR5LOwJVlk5+X95budrH9z4+B6/3ijqKYGBUk5W" +
                                                   "xt3B\nmNimQlQARl/8uTubw3vR5OkQqgZsADzk+weo6QjqKCrnQQ8amS11cR" +
                                                   "RJmnYa6+yTh2eNdMY25/w3\nPFmifN0GwYmw/O6A//pcwvM7+7rYYtd2kVws" +
                                                   "2gErOPTefrb23g9fj7zF3eKhdHNBH5wgVNR8i58s\nu21C4P3H3x77xrc+Ob" +
                                                   "GfZ0ouVSg0R3da15QssNzts0Cx6wA4LJA9e4y0qWpJDU/rhGXcv5pXD/zo\n" +
                                                   "z1+LitDo8MaLbP9/FuC/v3MLevrGl//ewcVUKazZ+Gb4ZMKaNl/ykG3jw2wf" +
                                                   "2WfeX/GdX+AXAAsB\nfxxtnnBIqcoVAdvUEoqWlxRaQWVxZ8ucdC2/SiwaXA" +
                                                   "Di3+5jl27YQH+F+ijT/xPK0UupbvfQL3/C\nTYvgwkGiMFY7sTUo0oNdVrEQ" +
                                                   "LAmW+GPYmQG6Ddd2PRHVr/0TJE6BRAX6riMsKIp0jrqm7qPrb7Q/\n+V4Yhb" +
                                                   "ahRt3E6jbMiwQ1QHYSZwbwKWs9/AhPwOgcS8EoNxlxJyzLOSBb8MRAZU1ljN" +
                                                   "jGpge/vBLT\n/Rfi74y+wB1QER3KNM+AnPmre87c/hW9yeX4Zcq4u7Kl4AsT" +
                                                   "l8/7wAeZltrLL6ZDqG4KRZXcTDiJ\ndZcVwxSMMI43KMLcWPS9eBwRvXcwD0" +
                                                   "PLgxBRoDYIED7ow5pRs3VTABOaPEyozWFCbRATqhBfDHGW\nHn7tzVdwnWVr" +
                                                   "GczmRBiYWE58rtQXMMOuG9hliwj2/eWSgm/37qJk+FzFBSmzotJExafBE/s+" +
                                                   "bTqO\n3zwgWlBr8ZQyApP8Hw6/QXof+urvyjTQBmpa63SSIXogS1cUtb6dfN" +
                                                   "j0s+K5i9+/Qt/r2yxUrq2c\n0UHGtZvPzndufvXUf9HwOgNOCIpuydz1pfCM" +
                                                   "9naIz8MiyUrm6GKmweLUaoT9uLaxuyjBVhY3nQ1e\npnn3kqbjh9kHwxD3aw" +
                                                   "j8urTw6GdraRghM7wp3jre/dO397x4Qrh0AZAo4kooT/3mt7Phr1+fFnxB\n" +
                                                   "JAgQn+449/vXbo0vEpkgzhqrSsb9Qh5x3uCWNVssF7sW0sCp3+zrevnY+E2+" +
                                                   "I8Y3SlEYDjpsmeAv\nphZoFSq77KWoJUWoqIIJcshlkwNr5gXO4w2M6bz4/N" +
                                                   "a28Qf3P8tTqgGOctjZ5ccUDtBsVQW+WV3Z\np3lhCaX3wJW/Xr9KenmPqNcc" +
                                                   "gKUhO1XmWFPA8ym+RHZ+mDzD56DqaeyI/AmeB0uPe0WnOG7/Fyx+\nS1iWBQ" +
                                                   "N8hJszcO/6Tfc9APa3gv3sdwBJU6W4qWA9tvWl65H3T7ubtovw31FAENt69P" +
                                                   "L2pvqXTh3n\nFZFzREPBgSf3XJfB9i4fFNjtIEXK/2/y3zywvn9gYN3G9ZB8" +
                                                   "JWG+R5KkNb3OSr8WfXjdtwC8Fvdc\nitrKaGcz0NKS3xDEuVeJf3Tkic/iv/" +
                                                   "6HCKJ3No2Av5Kurhd2oIJ1rWWTpMZDFhH9SMTPpWhJhbMJ\nDItiwXdMBT1s" +
                                                   "OBqkp6ia3QrJjkBKFJBB18qtComOQb0BEVs+ZXk9JsrHG9aJJdGJs0UOY55Z" +
                                                   "VVQf\n/GcdD2pd8cNOQtn3yv6V2VO7n+fFVqPoeH6en+Ahi8QQn4frrorSPF" +
                                                   "nvosuvTr7+gwc9nODz26Ks\n31K9zfkJsGihBIAWUX5yHklblM+68z9e8sOH" +
                                                   "zp+5GeKz+78BqZjYmI0TAAA=");
}
