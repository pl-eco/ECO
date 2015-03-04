package org.sunflow.core;
import org.sunflow.image.Color;
public interface Display {
    void imageBegin(int w, int h, int bucketSize);
    void imagePrepare(int x, int y, int w, int h, int id);
    void imageUpdate(int x, int y, int w, int h, Color[] data);
    void imageFill(int x, int y, int w, int h, Color c);
    void imageEnd();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0ZfXAU1f3dXT4hJCHhS74kJOgAeleYYgdwkCQmEDwgQwId" +
                                "09Zjs/vusrC3u+y+Sw4EC7QKgyM6FKt0bP6CtlY+nFZKW0eG1lZBS2eg1Ban" +
                                "hdqPKWppoTPaP1Dp7/fe3u3e5nIkiGZmf9l77/3e7+P9Pt8evEyKbYvMNg1t" +
                                "Y0IzWJimWXidNi/MNprUDi+LzmuXLJsqzZpk250wFpOnv1j14bWneqqDpKSL" +
                                "1Eq6bjCJqYZur6K2ofVSJUqq3NEWjSZtRqqj66ReKZJiqhaJqjZbGCUjPaiM" +
                                "NEQzLESAhQiwEOEsRBrdVYA0iuqpZDNiSDqzN5BHSCBKSkwZ2WOkLncTU7Kk" +
                                "pLNNO5cAdijD32tAKI6ctsi0rOxC5gECPz07sveZh6p/FCJVXaRK1TuQHRmY" +
                                "YECki1QkabKbWnajolCli4zWKVU6qKVKmrqJ891Famw1oUssZdGsknAwZVKL" +
                                "03Q1VyGjbFZKZoaVFS+uUk3J/CqOa1ICZB3nyiokbMVxEHCECoxZcUmmGZSi" +
                                "9aquMHK7HyMrY8MDsABQS5OU9RhZUkW6BAOkRpydJumJSAezVD0BS4uNFFBh" +
                                "ZOKgm6KuTUleLyVojJEJ/nXtYgpWlXNFIAojY/3L+E5wShN9p+Q5n8sr7t39" +
                                "sL5UD3KeFSpryH8ZIE31Ia2icWpRXaYCsWJW9NvSuFd2BgmBxWN9i8WaY5uv" +
                                "Lr5r6omTYs2kPGtWdq+jMovJ+7srz0xunjk/hGyUmYat4uHnSM7Nv92ZWZg2" +
                                "wfPGZXfEyXBm8sSq1x7c+kP6fpCMaCMlsqGlkmBHo2UjaaoatZZQnVoSo0ob" +
                                "Kae60szn20gpvEdVnYrRlfG4TVkbKdL4UInBf4OK4rAFqqgU3lU9bmTeTYn1" +
                                "8Pe0SQgphYcE4FlGxF8ZAkZikR4jSSOSLOmqbkTAdqlkyT0RKhsxi5pGpKV5" +
                                "ZaQbtNyTlKz1dsRO6XHN6IvJKZsZyYhtyRHDSmSGI7Jh0cj9qm1q0sYwGpr5" +
                                "2ZNIo5TVfYEAHMBkv/tr4DlLDU2hVkzem2pquXo49mYw6w6OfuBYgULYoRBG" +
                                "CmGHAgkE+MZjkJI4VTiT9eDdEPcqZnZ8bdnandNDYE5mXxFqNM3dbULmByD6" +
                                "OOKO3fqzE/uOfmf2/KA3BlR5omoHZcKiRrt0Oy1KYfzPz7Z/6+nLO77CicKK" +
                                "+nwEGhA2g31B0ITg8+jJDecvXth/LphlNMQg0Ka6NVVmpEzqhiglyYyR8my4" +
                                "GSDIlMF8lseb/dv39isrD8wRnlWT6wctEOYPvfXxb8LP/uVUHuWXM8O8W6O9" +
                                "VPPQrESSYCIOtRbZWM7DWRvPFjI4867nXzjGzsxeIEjOGjzp+RFf3/7exM5F" +
                                "PWuDJJibuJA6DI1AzHZMN9m0crtPeP+Wzy8/eGrJHfKeIAk5UStPhM5FWuhV" +
                                "AxC1KKQUHRWKIyOA6HS/MVuGTBXIOy7dWdOko7FXtjQESRGEXkg3TILwAJF8" +
                                "qp94TrRcmLE6JFUMSogbVlLScCqTLkawHsvoc0e4l1Xy99FwPNws5sBT68QT" +
                                "/h9na02EY4RX8vWTOJyKoI6fbRBfpyOoR8u6w7VxiH0axF88jIbVetJQ1Lgq" +
                                "dWsUve2jqhlzjv5rd7WwIA1GMqdz1403cMdvayJb33zof1P5NgEZc6/rd+4y" +
                                "4X617s6NliVtRD7S285O2fe69F1IDRCObXUT5RE2JPwKkGYWqL8sNQkpodfJ" +
                                "WZEtNRfXP3fpkDBhf4LzLaY79+66Ht69N+ipAuoHJGIvjqgE+EmMEid3Hf4C" +
                                "8HyCD54YDohMUNPspKNp2Xxkmng8dYXY4iRa/3lky8s/2LIDxUAydzISgkCC" +
                                "r4s44KMLOAvzOJyPNJ3Ygr9bEMxhUO4koYxooglVeONCBPeJWNTISFGvoSrm" +
                                "gB34wOxc28T5Osc2627WNnMZLuYLil2xfIBv215AylUIHmCkgkvZDslPsri3" +
                                "LxmaTIvgmevINPczlwmOfsbglszdQRhm//fqf/v1/vp3wDC7SJlqQz3eaCXy" +
                                "lHkenCsHL75/dtSUwzxwFXVLtoh6/vp4YPmbU9VyoSuySmohToFz2lHSaWHa" +
                                "yq2tQOyNNqPJSBs/REmnWqbS+VzoiBMcy8h4b83CLQqjn2GZpinMrauAKSoI" +
                                "VjMykiOuNhXw92FY4pfgaXKU3PS5eJco87QCIukIEljDoEitqqYNQyBMX22O" +
                                "QG23RiAvb70F5jjYAGUY57tFV4bANg/b+eP4OLe/E81MmPfTEMsLiBFyxYDC" +
                                "ELpbCO+F5NleYO6bglEEm7PGWs0TKXIVFlwV4oNBdaLqEj+/rXzPbQi+geBR" +
                                "UFSCMl592gXLvo4UlLWezvIJtf/0Gx9UbRPJNjdJ88sFB9WPd/6PobkjWcOT" +
                                "vFzMhqoyKKFsXMnItMEvKvheIgOPvOHJjXVPjpPPHlxGiVWuEvkCHN6Tk6Hz" +
                                "KyEmtyVjHUfP77iH1w5VvSr0pVTpdO5Ocksgt71ZmHOfkldNMfnSkSdO1r23" +
                                "ppY3yhmNeFuZ5ZI5oJVZKtk9MF5c+vYvXh239kyIBFvJCM2QlFYJ70qg44Xq" +
                                "k9o90LilzfsWc6+s6MOWtRqeQIHM5MjEa76YvPm5T06/u+XCqRApgVyCZTkk" +
                                "XKjAGQkPdkPl3aChE97uByyolysFtqonsqoHE6jJjmaLc0buHmxvXgD6ani8" +
                                "44EATq0mIyVc/wu+xiBlmt5Zb867GWN6BOrlISgvK7sTFEkNd5tK1wSxq/NO" +
                                "QgKsbY42dnTEOh9sb4mtaVzV1tgU5ZXdHhMmA534+li6wOl9WdWUZslShOMd" +
                                "uF5ev3j8qQXc8QYq6SYVM4ErRupjESx4DZ3qboQ0BYcDet+GQfj1XGfG5KfO" +
                                "XRm15srxq9zH/G3lM6J/coKiRcb77xMcl/jiiRVfrdZOXOO11EhJhsLHXmkp" +
                                "1OL4+xxfqM76AkmTvEH4cQRPIjiI4Ig3Jg8tD4jgWyDOv1Rg7id+eo/dKH2K" +
                                "OP9jBEcRHIM43wM6aTYU7gmLOOoLnqxyKK8yoFfP3w22JE3G+7dNPx3/0r3f" +
                                "77/AL0Nwo59ni4yXERwfoq4KpP4AXxAQ1oTw1QK6+vUQdcW3m85X/BLBrxC8" +
                                "BumabkhJmp2vXyrtNgyNSvrQdMeFR3AKwRsITt8ihXjl/V2Bud8PXxdnEZxD" +
                                "8BaYDDPEpXaeusMzMXRVnEfwNoI/DUcVw/GjvxaY+/sQ9eHS2+U60zsI/obg" +
                                "H1jUGUyNb8RfS4apg0scCcG7n5UO/lNg7uqn0cG/EVxB8F/oDIQOGp3OYJhq" +
                                "+CCrhg9vpRqcWJHPfYs0Q0/wvT8qgARWPtW18jZsmK2UCfVdS1qmJgY/3CIQ" +
                                "+DRqvIY7oCoCRcBWn6Sym9BgoDSjwUDZcDRYIK4E3QUfI+B3MIHKQcNxIMgX" +
                                "VA87Pe3ieKMQVCEY7cpz00oYc4uU4HGWwMQbiT75JkW/DcEkBFNugejT+Fga" +
                                "MpTznQUbhAkDvt2K743y4f6qsvH9q/8gLosy3wTLo6QsntI0T63srZtLTIvG" +
                                "VS5iuSi+eAccaICk4P/aAzaN/1CEQL1YdicjIz3LgFPnzbtoFiMhWISvs6F8" +
                                "JDl1o+mvIusH7aWWp8QX7Zh8pH/Zioev3nOA17zFUJdu2oS7QKtRKu7o+aZ4" +
                                "6V036G6ZvUqWzrxW+WL5jMy1LK8+azxGNsETZY//H3AVS0A9IAAA");
}
