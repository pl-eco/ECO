package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class RowBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = new int[2 *
                                                                                nbw *
                                                                                nbh];
                                                       for (int i =
                                                              0;
                                                            i <
                                                              nbw *
                                                              nbh;
                                                            i++) {
                                                           int by =
                                                             i /
                                                             nbw;
                                                           int bx =
                                                             i %
                                                             nbw;
                                                           if ((by &
                                                                  1) ==
                                                                 1)
                                                               bx =
                                                                 nbw -
                                                                   1 -
                                                                   bx;
                                                           coords[2 *
                                                                    i +
                                                                    0] =
                                                             bx;
                                                           coords[2 *
                                                                    i +
                                                                    1] =
                                                             by;
                                                       }
                                                       return coords;
    }
    public RowBucketOrder() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026716000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVXe2wcxRkf352fZ/AjiRNCiBPn/dqNU2IpNhIxjkMuuSTH" +
                                                   "OQ5gE8x4du688d7O\nZnfWvpiIgpCSlKgtEVRqpTYNVaSQlJcEKCDxSAS0QP" +
                                                   "4BJIqEBG0Vqa3UUhVVoqnaP/rNzD329uwg\nIdWS52Znvsd8r99889xXqNZz" +
                                                   "0RLiafyoQz1tYCiFXY8aAxb2vAOwNEbeq21Mnd9jswiqSaKIaXDU\nkiSebm" +
                                                   "COddPQEzv68i7a4DDraNZiXKN5rh22thbk7U5urRJ475lL7Y+di3VGUG0StW" +
                                                   "DbZhxzk9mD\nFs15HLUmD+MprPvctPSk6fG+JLqJ2n5ugNkexzb3jqBHUDSJ" +
                                                   "6hwiZHK0PFlUroNy3cEuzulSvZ6S\nakHCPJdybNrU6C+pA86NlZxw7AJfup" +
                                                   "oahDSIzYNgjjwBWL2sZLWytspUJ/rswZ5jZy9EUcsIajHt\nISGMgCUc9I2g" +
                                                   "5hzNjVPX6zcMaoygNptSY4i6JrbMGal1BLV7ZtbG3Hepl6Yes6YEYbvnO9SV" +
                                                   "OouL\nSdRMhE2uTzhzSz7KmNQyil+1GQtnweyOstnK3J1iHQxsMuFgbgYTWm" +
                                                   "SJTZo2RLwzzFGyceUeIADW\n+hzlE6ykKmZjWEDtKpYWtrP6EHdNOwuktcwH" +
                                                   "LRwtnlOo8LWDySTO0jGOFoXpUmoLqBqlIwQLRwvC\nZFISRGlxKEqB+Gzo+O" +
                                                   "bksz9/a7vM7ZhBiSXO3wRMS0NMaZqhLrUJVYzXfe3pxP3+kghCQLwgRKxo\n" +
                                                   "+lddGk7+5e1ORXPrLDT7xw9TwsfIvtOd6YfvZigqjtHgMM8Uwa+wXJZDqrDT" +
                                                   "l3egajtKEsWmVty8\nnP7N/Y9epH+NoKYEqiPM8nOQR22E5RzTou7d1KYu5t" +
                                                   "RIoEZqGwNyP4HqYZ6ElFer+zMZj/IEilly\nqY7Jb3BRBkQIFzXC3LQzrDh3" +
                                                   "MJ+Q87yDEKqHf7QF/muR+pO/HG3TdM+3Mxab1j2X6MzNlr4Jc6k+\n7pNJyv" +
                                                   "U0m75Lzva7BnU1kUIOR8P6BMtRHRNsmzbTsyYULWGbDDolfr+r4Lw4dft0TY" +
                                                   "2AwXA5W1AJ\nu5gFtGPk/LUPjw3u+cFJlSoivQv2crQa9GkFfZrQpyl9WqU+" +
                                                   "VFMj1cwXelXMwOOTULuAcs3rhg7t\nfuhkVxSSxZmOgbsEaRdYVjjMIGED5Q" +
                                                   "JPSCwkkGWLfjV6Qrt+/k6VZfrcODwrd/yj56+e/Wfz+giK\nzA6SwkiA6SYh" +
                                                   "JiWQtQR+K8NlNZv8vz+x9+XfXf1ibbnAOFpZVffVnKJuu8LhcBmhBiBhWfy5" +
                                                   "W1qi\n96KDpyMoBmAAACjPD9iyNKyjon77ilgobKlPoniGuTlsia0igDXxCZ" +
                                                   "dNl1dknrSKYb5KGRHI0AEl\njF5/vG7zZ2/E35MWFxG3JXCnDVGu6retnAcH" +
                                                   "XEph/Yufpp76yVcnRmUSFLKAw0Xnj1smyQPL6jIL\nFK4F4CFitHLYzjHDzJ" +
                                                   "h43KIimf7bsqr71b/9qFV53YKVYtA2fruA8votd6FHrz74r6VSTA0RF0fZ\n" +
                                                   "jDKZsmZeWXK/6+Kj4hz5xz657We/xb8AXAMs8cwZKuEBScuQ9KMm3btOjptC" +
                                                   "e5vF0AWyN86R1bNc\n02Pk2MVsl3/kg9flqeM4eN8Hw7AXO30qqFL3PFD6PV" +
                                                   "QYKmBL7C5wxNghQrAwXL27sDcBwm6/vO+B\nVuvyf0DtCKglcId6suzzFZEu" +
                                                   "UNfWf37lnY6HPo6iyE7UZDFs7MQy/1EjJB71JgB18s6d2+UxWqcb\nxCj9gu" +
                                                   "RpFxe8lK/4kh8r5Li6kD1ivjZIVSPnCzlaUgVaAagSht4217Upr/wT933dfB" +
                                                   "y/e0jBTnvl\nVTQI7dqfj75D19zxwz/OgpeNnDmbLDpFrYqDgcoKuNsrO4py" +
                                                   "sT9x4deX+McbepXK9XMjXZhxfe/Z\nmc7eF099B5DrDDkhLLpt6tZ7ohPm+x" +
                                                   "HZ9Ch8q2qWKpn6gu4ApXAe37WFY8VKs8zJZaWcjIvI3g7/\ndYWcrAvnpEQj" +
                                                   "MfSGSiki/RoBvy4K9veumYM+YUoC4bXjXW++P/zLE8ql627QxAe5xsj3f/+H" +
                                                   "yeiP\nr4wrvnCvFCI+vfTcn16+lp6vMkE1lCuqerogj2oqpWUtjsjF5TfSIK" +
                                                   "nf3bD8uUfSX8oTCb7tHEWh\nmxXTlFzYcwOgGRbDLo7aspSrKhiiR3xxWwiU" +
                                                   "DzhPIpvQeeHJHfPS20YflynVCP069vaVYwqvJDGr\nAd+smtunJWFjZM2hS/" +
                                                   "+48hZdI8GjwfTgkdHvZmfpXQM8X+OLdO9nmTPy7ouNY0/lT7jpr+7pK1p1\n" +
                                                   "af/NjvxJOY4DXVpcmtO9eUvPlh6wvx3sF489zTS0JCPYSux45kr8k9N+z24V" +
                                                   "/psCBIkdx17a3dzw\nzKnjsiIKjmgMdLWF7/op7O4rg4L4GeXo0P+lx+vt2d" +
                                                   "jdvWnrFki7qgCv1TRt3RpvWbkKpV+6xZBQ\nwLn122GYo5sr9YobcVHV61C9" +
                                                   "aEjy84cf+Cb56b9V5Iqvjjg4KeNbVgAbgjhR57g0Y8o4xdW9pYKW\n4WjhHP" +
                                                   "0ntA5qIg9LFb0JL+wwPUcx8RMkAxyLB8g4qi/MgkQMigyIxNRxihdLq7zsxJ" +
                                                   "NPU++byktK\neGZFRVHIB3sRX331ZB8j9z0/uix/6sCTssJq4ak/MyPfZpA6" +
                                                   "qlsrYfTyOaUVZX2EXnrx4BsvbCuC\nQ0UfV3WDdqvdG8Qe7oXZ+6jBnMNl5z" +
                                                   "Pz2sJX7jh/5suI7OT+B5+n3JhnEQAA");
}
