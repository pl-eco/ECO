package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class BlackmanHarrisFilter implements Filter {
    private float s;
    private float inv;
    public BlackmanHarrisFilter(float size) { super();
                                              s = size;
                                              inv = 1.0F / (s * 0.5F); }
    public float getSize() { return s; }
    public float get(float x, float y) { return bh1d(x * inv) * bh1d(y * inv);
    }
    private float bh1d(float x) { if (x < -1.0F || x > 1.0F) return 0.0F;
                                  x = (x + 1) * 0.5F;
                                  final double A0 = 0.35875;
                                  final double A1 = -0.48829;
                                  final double A2 = 0.14128;
                                  final double A3 = -0.01168;
                                  return (float) (A0 + A1 * Math.cos(2 * Math.
                                                                           PI *
                                                                       x) +
                                                    A2 *
                                                    Math.
                                                    cos(
                                                      4 *
                                                        Math.
                                                          PI *
                                                        x) +
                                                    A3 *
                                                    Math.
                                                    cos(
                                                      6 *
                                                        Math.
                                                          PI *
                                                        x)); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVYXWwcRRLuHdvrnzix48SJCcFOjIPIDzuECBAYhUtWNnFY" +
                                                    "sBWHoFsOlt6Z3t2J5y89vfbGOR8Q6ZQoDxHiDBcQ+AEF8RcIOl3EnRBSXjhA" +
                                                    "3AunE4gHfsQLCMhDHvgR/9XdMzuzs2sDT1jq3p7uqq6qrqqvq33mAmrxKNrq" +
                                                    "OubhoumwFKmw1EHz2hQ77BIvtTdz7QSmHtHTJva8/TCX0wZf6vrquwdL3QpK" +
                                                    "ZtEqbNsOw8xwbG8f8RxzmugZ1BXOjpjE8hjqzhzE01gtM8NUM4bHhjNoWYSV" +
                                                    "oaFMoIIKKqiggipUUHeFVMC0nNhlK805sM28Q+gvKJFBSVfj6jG0sXYTF1Ns" +
                                                    "+dtMCAtghzb+fQCMEswVijZUbZc21xn88FZ1/u/3dP+jCXVlUZdhT3J1NFCC" +
                                                    "gZAs6rSIlSfU26XrRM+ilTYh+iShBjaNWaF3FvV4RtHGrExJ9ZD4ZNklVMgM" +
                                                    "T65T47bRssYcWjWvYBBTD75aCiYugq1rQlulhaN8HgzsMEAxWsAaCViapwxb" +
                                                    "Z2ggzlG1cehWIADWVouwklMV1WxjmEA90ncmtovqJKOGXQTSFqcMUhhat+im" +
                                                    "/KxdrE3hIskx1Benm5BLQNUuDoKzMNQbJxM7gZfWxbwU8c+F2286ecTeYytC" +
                                                    "Z51oJte/DZj6Y0z7SIFQYmtEMnZuyTyC17x6XEEIiHtjxJLm5T9f/MO2/vNv" +
                                                    "SJpLG9CM5w8SjeW00/kVb69Pb76hiavR5jqewZ1fY7kI/wl/ZbjiQuatqe7I" +
                                                    "F1PB4vl9//nj/c+RzxXUMYaSmmOWLYijlZpjuYZJ6C3EJhQzoo+hdmLrabE+" +
                                                    "hlphnDFsImfHCwWPsDHUbIqppCO+4YgKsAU/olYYG3bBCcYuZiUxrrgIoVZo" +
                                                    "aAe0FiT/xC9DnlpyLKJiDduG7agQuwRTraQSzclR4jrqSHpczcMplyxMpzzV" +
                                                    "K9sF05nJaWWPOZbqUU11aDGYVjWHEhU0gmhSd5sQFBa292BKDW9UTKZ48Lm/" +
                                                    "j9gKP43umUQCHLU+DhPARPY4pk5oTpsv7x65+GLuLaWaNv45MrQNpKZ8qSku" +
                                                    "NSWlphpJRYmEELaaS5cRAf6cAmQAzOzcPHn33nuPDzZBKLozzeAMTjoIB+Cr" +
                                                    "NKI56RA+xgRIahDDfU/edSz1zdM3yxhWF8f6htzo/KmZBw7cd7WClFrQ5ibC" +
                                                    "VAdnn+BQW4XUoXiyNtq369inX519ZM4J07bmFvDRpJ6To8Fg3BnU0YgO+Bpu" +
                                                    "v2UDPpd7dW5IQc0AMQCrDEMaAGL1x2XUoMJwgLDclhYwuOBQC5t8KYDFDlai" +
                                                    "zkw4I6JkhRivBKcs42kyAK3Nzxvxy1dXubxfLaOKezlmhUDw0X+ff/TcY1tv" +
                                                    "UKJg3xW5PicJk9CxMgyS/ZQQmH//1MTfHr5w7C4RIUBxeSMBQ7xPA5CAy+BY" +
                                                    "//rGofc+/OD0/5UwqhjcqOW8aWgV2OOKUArAjAlQx30/dIdtObpRMHDeJDw4" +
                                                    "v+/atP3cFye7pTdNmAmCYdsvbxDOX7Ib3f/WPV/3i20SGr/mQstDMnkAq8Kd" +
                                                    "d1GKD3M9Kg/877JHX8dPAAoD8nnGLBFglqjmy+YlSh1qWIC+0/71oM71fDj1" +
                                                    "+KcvyLSJ3yUxYnJ8/sRPqZPzSuTCvbzuzovyyEtXBMNyGTw/wV8C2o+88aDh" +
                                                    "ExJ0e9I+8m+oQr/rcvdsXEotIWL0k7Nzrzwzd0ya0VN734xAOfXCOz/8N3Xq" +
                                                    "ozcbgBfEn4OZ0FEVOm4RfYorJU4UibVh3m1w69YqYqZPfClLn/0or3EikPXt" +
                                                    "uJk/+vE3Qqc60Gngjhh/Vj3z+Lr0zs8Ff5j9nHugUg/mUA+GvNc8Z32pDCZf" +
                                                    "U1BrFnVrfrF5AJtlnmNZKLC8oAKFgrRmvbZYkpXBcBXd1sfjISI2jjuhH2DM" +
                                                    "qfm4IwY1nfyUL4GW9KEmGYeaBBKDtGAZFP0m3l0ZZHqrS41pzCtZyA6+skOg" +
                                                    "k/Tlzt8uaYx3Iww1GfZ0ZDuh+FAkHKSwXobW1t2P8irkwX3ZYkWfCOzTR+cX" +
                                                    "9PGntit+FF7PUDtz3KtMMk3MiKgmvlPNLXmbKHNDj5949vmX2dtbb5QpsmXx" +
                                                    "KI0zvn70s3X7d5bu/Q1340DMpviWz9525s1brtAeUlBTNXDqKvdapuHacOmg" +
                                                    "BJ4a9v6aoOmvupI31Adtue/K5Q3vp9BhjXM+u8Tan3h3JwRXkbBJgGARCPXw" +
                                                    "ICYmaxVbHQyC31+lmCIhphpwOwRpfgkVdd7Bu6QJVPx16vXwyTXQen31ehdV" +
                                                    "byQm2L99Qs0OLqGZeL0UGWrOl7bri6gG2bq6Uf3Ib8W+uvesfINpLy50ta1d" +
                                                    "uONdURFV30nt8FgplE0zijeRcdKlpGAIxdol+shcPtQoc2Vly/iLlQ+E2q6k" +
                                                    "Zwx1x+nBRv4TJZthaFmEDELIH0WJZsFtQMSHR9wAR7pFMcBxNyVxt4IiGMDr" +
                                                    "oehXTXHE01z8ryBIybL8b0FOO7uw9/YjF697SuR3i2bi2VnxtoSnsqwLq2m9" +
                                                    "cdHdgr2SezZ/t+Kl9k0BXK3gXY9fDMZ0G2hcM41YLhNVzuy/1v7zpqcXPhBF" +
                                                    "289QMV7pxBEAAA==");
}
