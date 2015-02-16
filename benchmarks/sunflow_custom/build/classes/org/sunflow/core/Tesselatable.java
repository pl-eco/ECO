package org.sunflow.core;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
public interface Tesselatable extends RenderObject {
    public PrimitiveList tesselate();
    public BoundingBox getWorldBounds(Matrix4 o2w);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1170615938000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVWXWxURRSe3f6X2j/+yl+BtpBAYW+IosESIm0qLS6waQuG" +
                                "qiyzc2d3L5175zJ3tl2KGiQxEh+IUVAwsU8QgyISI0EfSHgSCL5gjMYH0WeV" +
                                "B170AU08M3d/7t62mpjYpLNzz5zfOec7Zy4/QDWeQL0uZ8cyjMsYzcvYEbY1" +
                                "Jo+51Ivtjm9NYOFRc4BhzxsDWpJ0XW35/dFb2dYoqh1HC7HjcImlxR1vhHqc" +
                                "TVIzjlrK1EFGbU+i1vgRPImNnLSYEbc82RdHCwKiEvXEiy4Y4IIBLhjaBWNn" +
                                "mQuEHqNOzh5QEtiR3lH0KorEUa1LlHsSra1U4mKB7YKahI4ANNSr7wMQlBbO" +
                                "C7SmFLsf86yAz/YaZ9471PpZFWoZRy2WM6rcIeCEBCPjqMmmdooKb6dpUnMc" +
                                "tTmUmqNUWJhZ09rvcdTuWRkHy5ygpUtSxJxLhbZZvrkmomITOSK5KIWXtigz" +
                                "i181aYYzEOuScqx+hM8qOgTYaIFjIo0JLYpUT1iOKdHqsEQpxp7ngAFE62wq" +
                                "s7xkqtrBQEDtfu4YdjLGqBSWkwHWGp4DKxItn1epumsXkwmcoUmJOsJ8Cf8I" +
                                "uBr0RSgRiRaH2bQmyNLyUJYC+Xmwd/vp486QE9U+m5Qw5X89CHWGhEZomgrq" +
                                "EOoLNm2Mv4uX3DgVRQiYF4eYfZ7rLz98ZlPnzds+z4o5ePaljlAik+RCqvne" +
                                "yoEN26qUG/Uu9yyV/IrIdfknCid9eReQt6SkUR3Gioc3R746eOIj+msUNQ6j" +
                                "WsJZzoY6aiPcdi1GxS7qUIElNYdRA3XMAX0+jOpgH7cc6lP3pdMelcOommlS" +
                                "LdffcEVpUKGuqA72lpPmxb2LZVbv8y5CqA7+UQT+u5H/t0AtEr1o7Peg3A1M" +
                                "sGM53IDipViQrEEJT6bgdrM2FhOeQXKe5Lbh5Zw041OGJ4jBRab0Tbigxhj1" +
                                "PMqwxClGY6rK3P9Zf17F1zoVicDVrwwDnwFmhjgzqUiSM7n+wYdXknejJSAU" +
                                "bkaiVWAmVjATU2ZiQTMoEtHaFylzflIhJRMAbmh7TRtGX9p9+FRXFVSTO1Wt" +
                                "LjSv0dZR/ADBkFsa189+efP8tfd7t0WDLaAl0FRHqfQLqq1sd0xQCvQfzyXe" +
                                "OfvgjRe0UeDonstAj1oHoLygZ0Lvef320R9+un/h22jJ0SoJfTaXYhaRqB6n" +
                                "oElhIiVqKHWbYCARvV88112NQGVS4YNGhbtqPmDrpnTh5JkZc9/FLT782ivB" +
                                "Mgiz4JPv/vo6du7nO3PkqUFydzOjk5QFPIsqk1BJBWuDhO/RPW9YjxQCiH/z" +
                                "0sfX5b3ep32TG+efjGHBWyd/WT62I3s4iqKV001ZB1KjkkyomVSaPatDwYdV" +
                                "Xtpz+c6u9eTtKKoqtLY52nilUF/wGsCooDB3HHWhitIIRrvCdS84oSYMp7Ld" +
                                "jWvwteSNV3qiqBr6M8wkiaGHQLvvDBuvaKl9xdpUpmrgEtJc2Jipo+JMaZRZ" +
                                "wafKFA3IZr1vg/QsUyjogv/2QtPRv+p0oavWRT6ANf8KvXaqZa2fW7XtUku3" +
                                "qqz1ZSRAg2RQbyoZPfsdm5tW2lJgVZj8s2Xdlmu/nW71K4gBpZidTf+uoExf" +
                                "1o9O3D30R6dWEyFqQJfRWWbzQbqwrHmnEPiY8iP/2jerzt/CH8D8gJ7tWdNU" +
                                "t2FU6AzKqe064qf02hc626GWx1XVF5oRLWKwcxYGE8KyYcxM6vjdWeo0YXMp" +
                                "J0sVcV0hL8X8/KecVHofqWwVHUE3bRhFsT0YXhv5J7T6oX8IPa6WAYmaM1Q+" +
                                "zwUz+3nOMb2i4pWzFOtzeMf08/zs6PMSNQUbuspXx6w3ov+uIVdmWuqXzuz/" +
                                "XgOl9PZogAdAOsdYAIdBTNa6gqYt7XqDX/+u/hmFd3I4VxJVqx/t5YjPdkCi" +
                                "BQE2ieoKuyDTQYmqgEltx103jyqmjBueOd0VXU6/m4sdKee/nJPk05nde48/" +
                                "fPKibm818OKentbvLHg2+jAvdbW182or6qod2vCo+WrDumghg81qaQ/UUcC3" +
                                "1XOjcNB2pcbN9BdLP9/+4cx9Par+BonwbePQDAAA");
}
