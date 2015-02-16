package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class SphericalLens implements CameraLens {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time) { double theta = 2 * Math.PI *
                                       x /
                                       imageWidth +
                                       Math.
                                         PI /
                                       2;
                                     double phi = Math.PI * (imageHeight -
                                                               1 -
                                                               y) /
                                       imageHeight;
                                     return new Ray(0, 0, 0, (float)
                                                               (Math.
                                                                  cos(
                                                                    theta) *
                                                                  Math.
                                                                  sin(
                                                                    phi)),
                                                    (float)
                                                      Math.
                                                      cos(
                                                        phi),
                                                    (float)
                                                      (Math.
                                                         sin(
                                                           theta) *
                                                         Math.
                                                         sin(
                                                           phi)));
    }
    public SphericalLens() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1163484256000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XW2wUVRg+u72Xlu2FlnIrbSloKexADCZYvJS1QOsCmxZI" +
                                                    "KIHldObs7rSzM8PM2XZbrAKJKeGBGC0IRvtgIIhyi5GgMSR9Egi+YIzGB8E3" +
                                                    "icoDL2iCiv85Z6+z26ovbjJnz5zzn//+f+efCw9QkW2hdtPQRsOaQb0kTr2D" +
                                                    "2jovHTWJ7e3xrwtgyyaKT8O2vQPWgnLLFc+jx29GqtyouB/VYl03KKaqodu9" +
                                                    "xDa0YaL4kSe92qWRqE1RlX8QD2MpRlVN8qs27fCjORlHKWr1J1WQQAUJVJC4" +
                                                    "ClJnmgoOVRI9FvWxE1in9gH0GnL5UbEpM/Uoas5mYmILRxNsAtwC4FDK3neB" +
                                                    "Ufxw3EJNKduFzTkGn2iXJt/ZV/VJAfL0I4+q9zF1ZFCCgpB+VBEl0QFi2Z2K" +
                                                    "QpR+VK0TovQRS8WaOsb17kc1thrWMY1ZJOUkthgzicVlpj1XITPbrJhMDStl" +
                                                    "XkglmpJ8KwppOAy21qdtFRZuYutgYLkKilkhLJPkkcIhVVcoWuo8kbKx9RUg" +
                                                    "gKMlUUIjRkpUoY5hAdWI2GlYD0t91FL1MJAWGTGQQtHCGZkyX5tYHsJhEqSo" +
                                                    "wUkXEFtAVcYdwY5QVOck45wgSgsdUcqIz4NtG44f1Lfobq6zQmSN6V8Khxod" +
                                                    "h3pJiFhEl4k4WLHSfxLXXz/qRgiI6xzEgubaqw9fWtU4fVPQLMpDs31gkMg0" +
                                                    "KJ8ZmHtnsa9tfQFTo9Q0bJUFP8tynv6BxE5H3ITKq09xZJve5OZ075e7D31E" +
                                                    "fnGj8m5ULBtaLAp5VC0bUVPViLWZ6MTClCjdqIzoio/vd6MSmPtVnYjV7aGQ" +
                                                    "TWg3KtT4UrHB38FFIWDBXFQCc1UPGcm5iWmEz+MmQqgEHrQGnlIkfvyfIkXa" +
                                                    "aUO6S1jGuqobEiQvwZYckYhsBAfAu5EotoZsSY7Z1IhKdkwPacaIZFuyZFjh" +
                                                    "1LtsWESSIcEsLPWZESgYGWt+ottelm3m/yQnzuytGnG5IBSLnUCgQQ1tMTSF" +
                                                    "WEF5Mrax6+Gl4G13qjASnqJoOYjzJsR5mTivEOfNEodcLi5lHhMrgg2hGoKi" +
                                                    "BzisaOvb27P/aEsBZJk5Ugh+ZqQtYGlCly7Z8KWRoZvjnwzp2fDBngnv7+de" +
                                                    "FOkpzQzjeU+j6VMjh3e9vsaN3Nl4zGyDpXJ2PMBQNIWWrc46zMfXM3H/0eWT" +
                                                    "40a6IrMAPgEUuSdZobc4o2AZMlEAOtPsVzbhq8Hr461uVAjoAYhJMWQ4gFGj" +
                                                    "U0ZWwXckwZPZUgQGhwwrijW2lUS8chqxjJH0Ck+PuWyoEZnCAuhQkOPups+n" +
                                                    "T199t329OxOiPRmXXh+houCr0/HfYREC6z+cCrx94sHEHh58oFiWT0ArG31Q" +
                                                    "/hAN8NgbNw98f+/umW/c6YShcA/GBjRVjgOPFWkpAA4aABQLa+tOPWooakjF" +
                                                    "AxphefeHZ/naq78erxKB0mAlGedV/8wgvb5gIzp0e99vjZyNS2aXU9ryNJlw" +
                                                    "QG2ac6dl4VGmR/zw10tO38DvA3YCXtnqGOEQhLhliLveyyPSxsfVjr01bGgy" +
                                                    "c/bifKUh8cZfmvnYyoanhN/Y9OlMShef11G0KKewfbywWTkzJy+Z6U7i9+mZ" +
                                                    "I5NTyvaza0Vp1mTjfBe0MRe//fMr76kfb+WBlDJqmKs1Mky0DL3cTGQWJGzl" +
                                                    "13W6MI6d//gavdP+nBC5cmY0cB68ceTnhTteiOz/D0Cw1GG8k+X5rRdubV4h" +
                                                    "v+VGBSkMyOlAsg91ZLoBhFoEWiadOZStlPNQN3IFqsEdtSyoC+ApS9xP/J/t" +
                                                    "1ppsnCcqlg3POHLHnfBnIs6NOXHmphJocFhyJsnqM8n6xH9noJuLeXmW7Oxh" +
                                                    "QyeUZ8xU4M6GKLbN0m5bahQ6gOFEiyKN19wbeu/+RRFRZz/jICZHJ4898R6f" +
                                                    "dGc0fcty+q7MM6Lx41pWCsc+gZ8Lnr/Yw0xgC+Lir/Eluo+mVPthmqwOmmdT" +
                                                    "i4vY9NPl8S8+HJ9wJ1yynqKSAcPQCNZzq5YvPJ+Ks4ct1sFTmYhz5b+Ocwnn" +
                                                    "WMLf/WwICOa9lGG0gTna9efbLoAmmk335tssVgwAWp6U+/nANdg9SwoobNgF" +
                                                    "J8OE9uLRZEbNy0k82MwDYxRVZvUSDEYbcj5bRKstX5rylM6f2vkdvx1T7XAZ" +
                                                    "9KShmKZl1FdmrRWbFgmpXNcycemZ/G+IovkztDdgjZhwfQcFPfS7VU56igrZ" +
                                                    "XybZAYrmZJBBNiRmmUQUwgBEbBozky6r4rcH+xbxisY7jrJA3syG/MzblNUa" +
                                                    "/yRMIlZMfBQG5ctTPdsOPnz2LIe/IviYHBvjnxDwRSR6hBTqNc/ILcmreEvb" +
                                                    "47lXypYnUz2re3DotjT/JdsVNSm/Fsc+m//phnNTd/kt/zch5Ldqqw8AAA==");
}
