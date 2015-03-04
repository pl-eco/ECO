package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class LightSample {
    private Ray shadowRay;
    private Color ldiff;
    private Color lspec;
    LightSample next;
    public LightSample() { super();
                           ldiff = (lspec = null);
                           shadowRay = null;
                           next = null; }
    boolean isValid() { return ldiff != null && lspec != null && shadowRay !=
                          null; }
    public void setShadowRay(Ray shadowRay) { this.shadowRay = shadowRay;
    }
    public final void traceShadow(ShadingState state) { Color opacity = state.
                                                          traceShadow(
                                                            shadowRay);
                                                        Color.blend(ldiff,
                                                                    Color.
                                                                      BLACK,
                                                                    opacity,
                                                                    ldiff);
                                                        Color.blend(lspec,
                                                                    Color.
                                                                      BLACK,
                                                                    opacity,
                                                                    lspec);
    }
    public Ray getShadowRay() { return shadowRay; }
    public Color getDiffuseRadiance() { return ldiff; }
    public Color getSpecularRadiance() { return lspec; }
    public void setRadiance(Color d, Color s) { ldiff = d.copy();
                                                lspec = s.copy(); }
    public float dot(Vector3 v) { return shadowRay.dot(v); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YXWwcRx0fn78dx+fY+TBu4iTOpcJxe0ugQTKuWhLHbpxc" +
                                                    "a8t2o+CovY5353wb71d35+yLi0sTAQkVilpwQ4KCJapU/UqbCAgBoUp5gbYq" +
                                                    "L0UIxAMt4oWKkoc8UCoKtP//7O7t3t5H3D5w0szNzfxn/t+/+c9dukHqHZv0" +
                                                    "W6Z2fFYzeZLlefKYtifJj1vMSR5M7RmntsOUIY06zhTMpeXeK/EPPnoq2x4j" +
                                                    "DdOkkxqGySlXTcOZYI6pzTMlReLB7LDGdIeT9tQxOk+lHFc1KaU6fDBF1oS2" +
                                                    "cpJI+SJIIIIEIkhCBGlvQAWb1jIjpw/hDmpw51HyOKlJkQZLRvE42V58iEVt" +
                                                    "qnvHjAsN4IQm/H0YlBKb8zbZVtDd1blE4Wf6peUfPNz+k1oSnyZx1ZhEcWQQ" +
                                                    "ggOTadKqM32G2c5eRWHKNFlnMKZMMlulmroo5J4mHY46a1Ces1nBSDiZs5gt" +
                                                    "eAaWa5VRNzsnc9MuqJdRmab4v+ozGp0FXTcGuroajuA8KNiigmB2hsrM31I3" +
                                                    "pxoKJ1ujOwo6Jg4BAWxt1BnPmgVWdQaFCdLh+k6jxqw0yW3VmAXSejMHXDjp" +
                                                    "rngo2tqi8hydZWlOuqJ04+4SUDULQ+AWTjZEycRJ4KXuiJdC/rnxwN1nHjMO" +
                                                    "GDEhs8JkDeVvgk09kU0TLMNsZsjM3di6K3WWbnztdIwQIN4QIXZprn395lfv" +
                                                    "6Ln+hktzWxmasZljTOZp+eJM29ubh/oGalGMJst0VHR+keYi/Me9lcG8BZm3" +
                                                    "sXAiLib9xesTv/naEy+x92OkZZQ0yKaW0yGO1smmbqkas+9jBrMpZ8ooaWaG" +
                                                    "MiTWR0kjjFOqwdzZsUzGYXyU1GliqsEUv8FEGTgCTdQIY9XImP7YojwrxnmL" +
                                                    "ENIIjbRCayHuR3xzIktZU2cSlamhGqYEscuoLWclJptpm1mmNDw0Js2AlbM6" +
                                                    "teccyckZGc1cSMs5h5u65NiyZNqz/rQkmzYDSJjN8kmqWxpLYrBZ/x82edS2" +
                                                    "faGmBhyxOQoDGmTQAVNTmJ2Wl3P7hm++mn4rVkgLz06cbAYuSY9LErkkQ1xI" +
                                                    "TY04fD1ycz0M/pmDTAcMbO2bfOjgI6d7ayG0rIU6MC6S9oKCngjDsjkUwMGo" +
                                                    "AD0ZYrLr2aOnkh8+f68bk1Jl7C67m1w/t3Di8De+ECOxYhBGlWCqBbePI3QW" +
                                                    "IDIRTb5y58ZPvffB5bNLZpCGRajuoUPpTszu3qjxbVNmCuBlcPyubfRq+rWl" +
                                                    "RIzUAWQATHIKYQ0I1BPlUZTlgz5ioi71oHDGtHWq4ZIPcy08a5sLwYyIijYx" +
                                                    "XgdOWYNhvxHaei8PxDeudlrYr3ejCL0c0UIg8sgvr5+/+sP+gVgYvOOh63CS" +
                                                    "cRcK1gVBMmUzBvN/Pjf+/WdunDoqIgQodpRjkMB+CIABXAZm/dYbj/7p3Xcu" +
                                                    "/j4WRBWHGzI3o6lyHs64PeACsKEBdKHvEw8auqmoGZXOaAyD8z/xnbuv/uNM" +
                                                    "u+tNDWb8YLjj1gcE85/bR5546+F/9YhjamS8tgLNAzLXAJ3ByXttmx5HOfIn" +
                                                    "frfl/Ov0R4CqgGSOusgEOBGhGRGml4Srdok+GVnbjd02q2QtL2a6xK86YN1X" +
                                                    "OYlG8PYNJd+/x7SZk3/9UGhUkj5lLp3I/mnp0oXuoXveF/uDOMbdW/OlMASV" +
                                                    "SrD3iy/p/4z1Nvw6RhqnSbvslUGHqZbDaJmGq9/xayMolYrWi69x984aLOTp" +
                                                    "5mgOhdhGMyiAPxgjNY5bIkmD9wbp9rPH/w4nTQ0RgwGxpVf0O7H7vB+zjZat" +
                                                    "zlOssaBCyFLFXJigxwXdBk7Wl6AuLIo8dH19V7EkXdDWepKsrSDJXuwGOanX" +
                                                    "IIwzPqNNYUaqDhULRr1pV+fV5vFqq8BrpMDLsZiMP/aXPVAE6wZoce/AeIUD" +
                                                    "R8ubkYDx6gwwoTjdZSE8lQjFP0FY2FKpvhK14cWTyyvK2HO73Runo7hmGYaS" +
                                                    "/JU//Pe3yXN/ebPMBdnMTetOjc0zLcSzCVkW3XT3i9IziPUnX3z5Gn+7/ysu" +
                                                    "y12V8zO68fWTf++euif7yKe437ZGlI8e+eL9l96873b5ezFSW0iZkmq6eNNg" +
                                                    "caK02AzKf2OqKF16igOn00sZP3VK7xjsDlVBu6NV1h7C7gikleoAIKhKddAb" +
                                                    "t1UdCtF5r1KWljrenbvw3iuuM6IIFyFmp5ef/Dh5ZjkWenvsKCn/w3vc94cQ" +
                                                    "c61rk4/hUwPtf9hQB5xw68+OIa8I3laogi0LQ3h7NbEEi5G/XV761QtLp2Ke" +
                                                    "TcbBHDOmqTFqlF4RYmKquAzoh5bwXJSo6KJExA01LqThz3sFlVbFTwZ2Kiet" +
                                                    "UKtPFsFeGjvZlQvwvG7eVJVbio2N7IGW9MROlq9eysJHDId9AFMZ1aBavrxa" +
                                                    "PlJuKYFkFB9ei/heZoLNYhW9H8eOc7KG21B4u5rjlHVLFQUm3gZtwFNxYNWe" +
                                                    "CQvwzSpr38buBDhlNuQU4c/VOWAHtH2edPs+k3TfrbJ2BrvvQGaAdPvh7so5" +
                                                    "bAIsjzAkrpbVyYjxfMiT8dBnknG5ytpZ7J7mpBMtCHdeTqP2pxNSJOCd0I54" +
                                                    "Qh5ZtZCxIJrFPbtfkF6oIu4KduchGiELw2LeOhqFLfGJoHhiKqsWM5JQXeGE" +
                                                    "0uFtnjzM8OH0JXHCxSrSv4DdjzmpVUxeDjqgnjMpL1MVg8Kh1yvW5F0l/465" +
                                                    "/+jIr67EmzatPPhH8R4r/OvSnCJNmZymhWvE0LjBsllGFTI2uxWjW45c4aQ9" +
                                                    "Ch+AcPglhLzskv0U5AuRAXp7ozDRz0FvIMLhNcu3Zbt4WmDtm3Rr3zwpqoCs" +
                                                    "aD20o+huFP8k+sVBzv0vMS1fXjn4wGM3v/ycqDTqZY0uLuIpTSnS6L4yCwXG" +
                                                    "9oqn+Wc1HOj7qO1K807/amrDriMEziHZtpZ/gQ3rFhdvpsVfbPrZ3c+vvCOe" +
                                                    "gJ8AgBJyZuIVAAA=");
}
