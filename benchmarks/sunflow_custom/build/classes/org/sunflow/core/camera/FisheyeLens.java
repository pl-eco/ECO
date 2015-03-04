package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class FisheyeLens implements CameraLens {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time) { float cx = 2.0F * x / imageWidth -
                                       1.0F;
                                     float cy = 2.0F * y / imageHeight -
                                       1.0F;
                                     float r2 = cx * cx + cy * cy;
                                     if (r2 > 1) return null;
                                     return new Ray(0, 0, 0, cx, cy,
                                                    (float)
                                                      -Math.
                                                      sqrt(
                                                        1 -
                                                          r2)); }
    public FisheyeLens() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XW2xURRiebu+lZXvhfimlFLRc9kAMJli8lLWF4gJNCySU" +
                                                    "wDI9Z7Z72rNnDufMtttiFUhMCQ/EaEEw2gcDQZRbjASNIeFJIPiCMRofBN8k" +
                                                    "Kg+8oAkq/jOzl7Nnt1Vf3OTMzpn557//3/zn/ANU7NhohUWN4T6DsgBJsEC/" +
                                                    "sTbAhi3iBDaH1nZi2yFa0MCOsx3WwmrjZf+jx29Gq32opAfVYdOkDDOdmk4X" +
                                                    "cagxSLQQ8mdW2wwScxiqDvXjQazEmW4oId1hLSE0zXWUoaZQSgUFVFBABUWo" +
                                                    "oLRmqOBQFTHjsSA/gU3m7EevoYIQKrFUrh5Di7OZWNjGsSSbTmEBcCjj7zvB" +
                                                    "KHE4YaOGtO3S5hyDj69Qxt/ZW/1JIfL3IL9udnN1VFCCgZAeVBkjsV5iO62a" +
                                                    "RrQeVGMSonUTW8eGPiL07kG1jt5nYha3SdpJfDFuEVvIzHiuUuW22XGVUTtt" +
                                                    "XkQnhpZ6K44YuA9snZWxVVrYztfBwAodFLMjWCWpI0UDuqkxtMh7Im1j0ytA" +
                                                    "AEdLY4RFaVpUkYlhAdXK2BnY7FO6ma2bfUBaTOMghaF5kzLlvrawOoD7SJih" +
                                                    "OV66TrkFVOXCEfwIQzO9ZIITRGmeJ0qu+DzYuv7YAXOT6RM6a0Q1uP5lcKje" +
                                                    "c6iLRIhNTJXIg5XLQyfwrGtHfAgB8UwPsaS5+urDl1bWX78paebnodnW209U" +
                                                    "FlZP906/syDYvK6Qq1FmUUfnwc+yXKR/Z3KnJWFB5c1Kc+SbgdTm9a4vdx38" +
                                                    "iPziQxUdqESlRjwGeVSj0pilG8TeSExiY0a0DlROTC0o9jtQKcxDuknk6rZI" +
                                                    "xCGsAxUZYqmEindwUQRYcBeVwlw3IzQ1tzCLinnCQgiVwoMC8JQh+RP/DPUr" +
                                                    "URojClaxqZtUgdwl2FajClFp2CYWVdqC25Re8HI0hu0BR3HiZsSgQ2E17jAa" +
                                                    "UxxbVajdl1pWVGoTRYVEs7HSrjtRMkxCxHQCPOes/1VagttePVRQAGFZ4AUF" +
                                                    "A+ppEzU0YofV8fiGtocXw7d96SJJeo2hJSAskBQW4MICUljAJQwVFAgZM7hQ" +
                                                    "GXYI2gCUPwBjZXP3ns37jjQWQr5ZQ0XgcU7aCOYmNWlTaTCDER0CCVVI1Dkf" +
                                                    "7B4L/H72RZmoyuSAnvc0un5y6NDO11f7kC8bmbllsFTBj3dyPE3jZpO3IvPx" +
                                                    "9Y/df3TpxCjN1GYW1CchI/ckL/lGbwxsqhINQDTDfnkDvhK+NtrkQ0WAI4Cd" +
                                                    "DEOuAyzVe2VklX5LCka5LcVgcITaMWzwrRT2VbCoTYcyKyI5pvOhVuYJD6BH" +
                                                    "QYHA7Z9fP3Xl3RXrfG6w9ruuv27CZOnXZOK/3SYE1n842fn28Qdju0XwgWJJ" +
                                                    "PgFNfAwCEEA0wGNv3Nz//b27p7/xZRKGwY0Y7zV0NQE8lmWkAEwYAFU8rE07" +
                                                    "zBjV9IiOew3C8+4P/9I1V349Vi0DZcBKKs4r/5lBZn3uBnTw9t7f6gWbApVf" +
                                                    "UxnLM2TSAXUZzq22jYe5HolDXy88dQO/DygKyOXoI0SAERKWIeH6gIhIsxhX" +
                                                    "efZW86HBytlLiJU5yTfxsliMTXx4SvqNT592UxaI+UyG5ueUdVCUNS9n7uSF" +
                                                    "k91O4mY9fXh8Qtt2Zo0szdpsxG+DhubCt39+FTj54608gFLOqLXKIIPEcOnl" +
                                                    "4yKzIGGLuLgzhXH03MdX2Z0Vz0mRyydHA+/BG4d/nrf9hei+/wAEizzGe1me" +
                                                    "23L+1sZl6ls+VJjGgJxeJPtQi9sNINQm0DyZ3KF8pUKEul4oUAPuqONBnQtP" +
                                                    "efKmEv98t87i4wxZsXx4xpM7vqQ/k3Guz4mzMJVAq8OTM0U2y03WLf9bOzuE" +
                                                    "mJenyM7NfGiF8oxbGtzeEMXmKRpvW49BLzCYbFaU0dp7A+/dvyAj6u1sPMTk" +
                                                    "yPjRJ4Fj4z5X+7ckpwNzn5EtoNCySjr2CfwK4PmLP9wEviBbgNpgsg9pSDci" +
                                                    "lsXrYPFUagkR7T9dGv3iw9ExX9Il6xgq7aXUINjMrVqx8Hw6zn6+OBOeqmSc" +
                                                    "q/51nEsFx1LxHuJDp2TexThGUyzQriffdiG003y6J99miUYBaEVS7hOD0GDX" +
                                                    "FCmg8WEnnOwjrAsPpzJqRk7iwWYeGGNomquT4CA6J+fzRbbc6sUJf9nsiR3f" +
                                                    "ibsx3RaXQ28aiRuGq7rclVZi2SSiC03L5ZVnib8BhmZP0tqALXIitO2X9ND3" +
                                                    "VnvpGSrif26y/WCPiwxyITlzEzEIAhDxadxKOaxa3B38myQgG/AEyoJ4Kxvw" +
                                                    "3XcprzTxaZjCq7j8OAyrlyY2bz3w8NkzAvyK4aNyZER8SsCXkewQ0pi3eFJu" +
                                                    "KV4lm5ofT79cvjSV6Fm9g0e3Rfmv2LaYxcSlOPLZ7E/Xn524K+74vwG9R024" +
                                                    "sw8AAA==");
}
