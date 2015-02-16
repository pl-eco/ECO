package org.sunflow.image;
public final class XYZColor {
    private float X;
    private float Y;
    private float Z;
    public XYZColor() { super(); }
    public XYZColor(float X, float Y, float Z) { super();
                                                 this.X = X;
                                                 this.Y = Y;
                                                 this.Z = Z; }
    public final float getX() { return X; }
    public final float getY() { return Y; }
    public final float getZ() { return Z; }
    public final XYZColor mul(float s) { X *= s;
                                         Y *= s;
                                         Z *= s;
                                         return this; }
    public final void normalize() { float XYZ = X + Y + Z;
                                    if (XYZ < 1.0E-6F) return;
                                    float s = 1 / XYZ;
                                    X *= s;
                                    Y *= s;
                                    Z *= s; }
    public final String toString() { return String.format("(%.3f, %.3f, %.3f)",
                                                          X,
                                                          Y,
                                                          Z); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1167328438000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYW2wU1/Xu2F4/MKwfAbsOGGNMVAPdKa2SKnFKC5YJpptg" +
                                                    "YUB4o+Jcz95dD56dGWbu2oupW4IUGfFBX05CKuKPCJQXCVEVlFRVJH6ah5Kf" +
                                                    "VFWrfjSp+tOoKR98NI2atuk5987szM6uN0FyV7pn7t57zrnn3POcuXqTNLgO" +
                                                    "2WFbxqmcYfEkK/LkCePuJD9lMzd5IHX3GHVclhk2qOsehrVJrf+VxCef/WS6" +
                                                    "TSHxNOmkpmlxynXLdA8x1zJmWSZFEsHqiMHyLidtqRN0lqoFrhtqSnf5UIqs" +
                                                    "CZFyMpDyRVBBBBVEUIUI6p4AC4jWMrOQH0YKanL3JPkhiaVI3NZQPE62lDOx" +
                                                    "qUPzHpsxoQFwaML/R0EpQVx0SF9Jd6lzhcKP71CXnjze9ss6kkiThG6Oozga" +
                                                    "CMHhkDRpzbP8FHPcPZkMy6RJu8lYZpw5OjX0eSF3mnS4es6kvOCw0iXhYsFm" +
                                                    "jjgzuLlWDXVzChq3nJJ6WZ0ZGf9fQ9agOdB1Q6Cr1HAfroOCLToI5mSpxnyS" +
                                                    "+hndzHCyOUpR0nHge4AApI15xqet0lH1JoUF0iFtZ1Azp45zRzdzgNpgFeAU" +
                                                    "TnpWZIp3bVNthubYJCfdUbwxuQVYzeIikIST9VE0wQms1BOxUsg+Nx+6/8Jp" +
                                                    "c7+pCJkzTDNQ/iYg6o0QHWJZ5jBTY5KwdXvqCbrhjXMKIYC8PoIscV77wa3v" +
                                                    "7uy98bbEubMKzsGpE0zjk9rlqXXvbxwevLcOxWiyLVdH45dpLtx/zNsZKtoQ" +
                                                    "eRtKHHEz6W/eOPTmxJkX2McKaRklcc0yCnnwo3bNytu6wZwHmMkcyllmlDQz" +
                                                    "MzMs9kdJI8xTusnk6sFs1mV8lNQbYiluif9wRVlggVfUCHPdzFr+3KZ8WsyL" +
                                                    "NiFkLQzSAaOOyJ94cjKhHnHB3VWqUVM3LRWcl1FHm1aZZk1Owe1O56kz46pa" +
                                                    "weVWXnULZtaw5lTX0VTLyZX+63mwvnpsIg3CW04SXcz+fzIvomZtc7EYXPrG" +
                                                    "aMgbEC37LSPDnEltqbB35NbLk+8qpRDw7gScHc5IemckxRlJ/wwSiwnWd+BZ" +
                                                    "0pZgiRmIach2rYPj3z/wyLl+uMGiPVcP16gAaj/o5AkwolnDQeCPivSmgfd1" +
                                                    "P/PwYvLTZ78jvU9dOUtXpSY3Ls49evRHX1eIUp5uUSFYakHyMUySpWQ4EA2z" +
                                                    "anwTix99cu2JBSsIuLL87eWBSkqM4/7o1TuWxjKQGQP22/vo9ck3FgYUUg/J" +
                                                    "ARIip+DAkGt6o2eUxfOQnxtRlwZQOGs5eWrglp/QWvi0Y80FK8In1ol5Oxhl" +
                                                    "DTp4J4y45/HiibudNsI7pA+hlSNaiNy771c3nrr+ix33KuE0nQgVvnHGZdC3" +
                                                    "B05y2GEM1v90ceznj99cfFh4CGBsrXbAAMJhSAFgMrjWx94++ccPP7j8O6Xk" +
                                                    "VTEOtbAwZehaEXjcFZwCLmpAkkLbDxwx81ZGz+p0ymDonP9ObNt1/e8X2qQ1" +
                                                    "DVjxnWHnFzMI1r+yl5x59/g/ewWbmIYFKtA8QJMX0Blw3uM49BTKUXz0t5ue" +
                                                    "eos+DfkTcparzzORhojQjIirV4WptguYjOztQtBnV+yJhZ5KGzd7Nm6uamME" +
                                                    "A5HT6gTHOhB/sEa75Oh5yOCzXolRFzo+nLn00UsygKP1KILMzi2d/zx5YUkJ" +
                                                    "Fe2tFXUzTCMLtxB5rVTxc/jFYPwXB6qGCzJxdwx71aOvVD5sGx1lSy2xxBH7" +
                                                    "/npt4dfPLSxKNTrKa9YItGQv/f4/7yUv/vmdKkkTIsGiwqfuE0BIe08NS+5F" +
                                                    "8M1KS0pTdn8ZK+zDjimURv910Jg6+5dPhXQVibCKYSL0afXqpZ7h3R8L+iAj" +
                                                    "IfXmYmU5ge4yoP3GC/l/KP3x3yikMU3aNK91PUqNAsZ9Gto11+9nob0t2y9v" +
                                                    "vWSfMVTKuBujnhE6NpoLA4vAHLFx3hJJf614yz0w6r3QqI+GRoyIyQFB0i/g" +
                                                    "NgRf9bNPo+3osxT7YhI7VjJ5m7TlyO2fNIYgBcwmVoHZuM8sHWJWrK6LgtNB" +
                                                    "dF7dpEbY8whGzKaVulERLZfPLi1nDl7ZpXgOvZuTZm7ZXzPYLDNCrOLIqawJ" +
                                                    "eFD034HznH/+xdf4+zvuk3G3fWWHjxK+dfZvPYd3Tz9yG6V/c0SnKMvnH7z6" +
                                                    "zgN3aT9TSF3JByteKcqJhso9r8Vh8A5kHi7zv96SIbvwdu+EkfAMmahefmsY" +
                                                    "DMGxYo3ckquxpyOY4qQ+x7h03hWqyfFKkTs9kTtXX+STNfbEoiFFnrhNkbs8" +
                                                    "kbtWX+TTNfYWEMxKkdNfTmQMbrLJE9sXfxVEjsm0FVSlx2rIvYjgDCd1+YIR" +
                                                    "ykM1xMZBtsDo98TuX/2b/nGNvZ8iOA+5xxSNMDRUAu1bCIYk02+DHWYtPfOF" +
                                                    "unTjYh+MnZ4uO1dfl0s19pYRPMlJE7fkJwiBtZ6TNtFJYoFMyo0qfQOQ+a9p" +
                                                    "2Hx2V3zwkR8ptJeXE01dy0f+IF48Sh8SmuFtPlswjHAJDc3jtsOyuhCyWRZU" +
                                                    "WzyucNJe8boIBUU8hYyXJeJznKwJIUIR9WZhpBfB8QAJp1ftKrrL5qBIygqV" +
                                                    "HS1bW8sKiPg85if7gvxANqldWz7w0Olb91wRlaNBM+j8PHJpSpFG+UJVKhhb" +
                                                    "VuTm84rvH/xs3SvN2/xCuA5BR8hVQrJtrv6yMZK3uXg9mH+969X7n13+QLzt" +
                                                    "/A+FmqjOtxQAAA==");
}
