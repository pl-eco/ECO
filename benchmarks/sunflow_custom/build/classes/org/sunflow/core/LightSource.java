package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public interface LightSource extends RenderObject {
    public int getNumSamples();
    public void getSamples(ShadingState state);
    public void getPhoton(double randX1, double randY1, double randX2, double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power);
    public float getPower();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1170611812000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXW2xURXR2+y6FPniVVyltISmFvSEEEUsIUFsoLrTpUhJK" +
                                "ZJneO7t76b13LnNn+8ISIDEQPojRomC0XxBFecVI0BgSvgSCPxij8UPwT6Py" +
                                "wY9+oOKZufu4e7stxuAmMzv3zDlz3ufMXHqEihyGWmxqjMQNykNkmIcOGutC" +
                                "fMQmTmhHeF03Zg7R2gzsOLsBFlUbrlX+/uSNRFUQFfeh2diyKMdcp5bTQxxq" +
                                "DBItjCqz0HaDmA5HVeGDeBArSa4bSlh3eGsYzfCQctQUTouggAgKiKBIEZQt" +
                                "WSwgmkmspNkmKLDFnUPoCAqEUbGtCvE4WpZ7iI0ZNlPHdEsN4IRS8b0HlJLE" +
                                "wwzVZ3R3dZ6k8JkWZfyd/VWfFKDKPlSpWxEhjgpCcGDShypMYvYT5mzRNKL1" +
                                "oWqLEC1CmI4NfVTK3YdqHD1uYZ5kJGMkAUzahEmeWctVqEI3llQ5ZRn1Yjox" +
                                "tPRXUczAcdB1XlZXV8MOAQcFy3UQjMWwStIkhQO6pXG01E+R0bHpFUAA0hKT" +
                                "8ATNsCq0MABQjes7A1txJcKZbsUBtYgmgQtHC6c8VNjaxuoAjpMoR7V+vG53" +
                                "C7DKpCEECUdz/WjyJPDSQp+XPP55tGvj6cPWdisoZdaIagj5S4GozkfUQ2KE" +
                                "EUslLmHFyvDbeN7Nk0GEAHmuD9nFufHa482r6m7dcXEW5cHp6j9IVB5Vz/fP" +
                                "ur+4rXlDgRCj1KaOLpyfo7kM/+7UTuuwDZk3L3Oi2AylN2/1fLn36Efk1yAq" +
                                "70TFKjWSJsRRtUpNWzcI20YswjAnWicqI5bWJvc7UQmsw7pFXGhXLOYQ3okK" +
                                "DQkqpvIbTBSDI4SJSmCtWzGaXtuYJ+R62EYIlcBAARgvIvc3Q0wc7VN6HQh3" +
                                "BavY0i2qQPASzNSEQlQa7QfrJkzMBhxFTTqcmoqTtGIGHVIcpiqUxTPfKmUE" +
                                "SkE8wSM0yVQSEkFm/7/HDwvtqoYCATD8Yn/aG5Ax26mhERZVx5Nb2x9fid4L" +
                                "ZtIgZReOFgOXUIpLSHAJebigQEAePkdwcz0K/hiAzIaaV9EceXXHgZMNBRBK" +
                                "9lChsOawTLXa9AcQ+qSSSd3x+a1z199t2RD05n+lp6JGCHejqTrLdzcjBOA/" +
                                "nO1+68yjE/skU8BozMegScxtEFtQMKHwvH7n0PcPH5z/JpgRtIBDkU32G7rK" +
                                "USnuhwqFVc5RWabUeBUJyPVcjpZMMlUPhCVhbsYIdZdMldWyIp0/Pj6hdV1Y" +
                                "4+ZeTW6mtEMjuPztX1+Fzv54N4+byji1VxtkkBgeyQoFS4ijFLd2le6UBa9T" +
                                "9hMV0v3UxY9v8PstL7ksV07dFv2Et4//snD3psSBIArmtjbBHUDlgrJbNKRM" +
                                "41nqU95/5MWdl+5uW6G+GUQFqbqWp4bnErV6zQBMGYGmYwmDCkg5MG3whz2j" +
                                "KtGgM2X5rqzH16M3x5qCqBCKMzQkjqGAQK2v8zPPqaet6dgUrIrACDHKTGyI" +
                                "rXRDKecJRoeyEJmPs+S6GtxTKbJgMYyaVMWR/2J3ti3mOW7+SvxFcq4T0zLp" +
                                "26BYNoipUUTWimwmQHU0IN6EM5p6LZNqekzH/QYROfln5fI11387XeVGkAGQ" +
                                "tHdWPfuALHzBVnT03v4/6uQxAVV052x2ZtHcJJ2dPXkLY3hEyDF87Osl527j" +
                                "96F5QMF29FEiazBKVQYh1Eap8Xo5t/r2NolpLUcz44TvSpoRbNoGcYBX8zQX" +
                                "O6ab0GsGU81QGat5OPDez5fdyPd3Th8yOTl+6mno9HjQc71onNThvTTuFUMK" +
                                "O9N1+FP4BWD8LYbQRADcFlPTlupz9ZlGZ9vCq8umE0uy6Pjp6tgXH46dCKYs" +
                                "08xRAVQpe5LZJGB1JvZkcwvBqE/FXv1/jb1cLwWeVRIjCazBlUpcKonk0TWN" +
                                "nyNi2sFROfg57WQB2iyml13eHRwVDlJd+3cqj8BQUiorz0flEolQkk+wYo1C" +
                                "F5HFKJqd0saZ7zWOCZcRuA6B79am92sn7e8h4qK8Nu8BugnXS5G7lElF9k9j" +
                                "V11Me6FrgF27E5RTSwB6n2lCMdACGOtTJlz/fEzole3QNHsSCG2hVMhNhwjL" +
                                "Z3SoyhTnyYBhjmZ47i6iNNVOegu593f1ykRl6fyJ3u9kT8jcscvgohtLGoan" +
                                "5XjbT7HNSEyXgpa5pd6Wf4fhPejPAwha8SeFHHXRjoB8HjQOIeWuvEjHIL8B" +
                                "SSyPQ41AORcq23+9aswph/J9mG6+SfeFGFWvTuzYdfjxCxdkJy+Cl+XoqHxP" +
                                "wPPI7WiZBr5sytPSZxVvb34y61rZ8nQ1miWmGk9QeGRbmr/htJs2ly1i9LP5" +
                                "n278YOKBvJX9AzaBrka4DwAA");
}
