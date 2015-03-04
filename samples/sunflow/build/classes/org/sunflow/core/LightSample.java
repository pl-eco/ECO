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
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYa2wcR3nu/HYcn2PnYdzESZxLheP2lkCDZFy1JI7dOLk2" +
                                                    "J9uNgqPWHe/OnTfZ293uztkXF5cmAhIqFLXghgQFS1Sp+kqbCAgBoUr5A21V" +
                                                    "/hQhED9oEX+oKPmRH5SKAu33ze7ePu4RtxKWZjw7871f881dukEabIsMmIZ2" +
                                                    "PKcZPMWKPHVU25Xix01mp/and2WoZTNlWKO2PQl703LflcQHHz012xEnjVOk" +
                                                    "i+q6wSlXDd0eZ7ahzTElTRL+7ojG8jYnHemjdI5KBa5qUlq1+VCarAqgcpJM" +
                                                    "eyJIIIIEIkhCBGm3DwVIq5leyA8jBtW5/Sh5nMTSpNGUUTxOtoaJmNSieZdM" +
                                                    "RmgAFJrx+xAoJZCLFtlS0t3RuUzhZwakpR883PGTOpKYIglVn0BxZBCCA5Mp" +
                                                    "0pZn+Rlm2bsVhSlTZI3OmDLBLJVq6oKQe4p02mpOp7xgsZKRcLNgMkvw9C3X" +
                                                    "JqNuVkHmhlVSL6syTfG+GrIazYGu631dHQ1HcR8UbFVBMCtLZeah1B9TdYWT" +
                                                    "zVGMko7JAwAAqE15xmeNEqt6ncIG6XR8p1E9J01wS9VzANpgFIALJz1ViaKt" +
                                                    "TSofozk2zUl3FC7jHAFUizAEonCyLgomKIGXeiJeCvjnxgN3n3lM36fHhcwK" +
                                                    "kzWUvxmQeiNI4yzLLKbLzEFs25E+S9e/djpOCACviwA7MNe+fvOrd/Ref8OB" +
                                                    "ua0CzMGZo0zm0/LFmfa3Nw73D9ahGM2mYavo/JDmIvwz7slQ0YTMW1+iiIcp" +
                                                    "7/D6+G++9sRL7P04aR0jjbKhFfIQR2tkI2+qGrPuYzqzKGfKGGlhujIszsdI" +
                                                    "E6zTqs6c3YPZrM34GKnXxFajIb7BRFkggSZqgrWqZw1vbVI+K9ZFkxDSBIO0" +
                                                    "wWglzp/4z0lGmjXyTKIy1VXdkCB2GbXkWYnJhmTTvKmB1+yCntWMecm2ZMmw" +
                                                    "cqVv2bAY5H9ulk8IyBRGlvl/oFlEPTrmYzEw8cZogmuQG/sMTWHWtLxU2DNy" +
                                                    "89Xpt+KlgHctwMlG4JJyuaSQSyrAhcRigvha5Ob4Dix/DHIYqltb/8RD+x85" +
                                                    "3VcHQWPO14PZELQPtHFFGJGNYT/Rx0Q5kyHaup89cir14fP3OtEmVa/KFbHJ" +
                                                    "9XPzJw594wtxEg+XV1QJtloRPYNFsVT8ktG0qkQ3ceq9Dy6fXTT8BAvVazfv" +
                                                    "yzExb/uixrcMmSlQCX3yO7bQq9OvLSbjpB6KARRATiFgobb0RnmE8nfIq4Wo" +
                                                    "SwMonDWsPNXwyCtgrXzWMub9HREV7WK9BpyyCgN6PYy1boSL/3jaZeK81oki" +
                                                    "9HJEC1FrR395/fzVHw4MxoNlORG46CYYd5J8jR8kkxZjsP/nc5nvP3Pj1BER" +
                                                    "IQCxrRKDJM7DkPLgMjDrt9549E/vvnPx93E/qjjcfYUZTZWLQON2nwsUBA2K" +
                                                    "Evo++aCeNxQ1q9IZjWFw/iexfefVf5zpcLypwY4XDHfcmoC//7k95Im3Hv5X" +
                                                    "ryATk/FC8jX3wRwDdPmUd1sWPY5yFE/8btP51+mPoF5CjbLVBSbKDhGaEWF6" +
                                                    "Sbhqh5hTkbOdOG0xy86KYqdbfNUD6/7qSTSK92og+f59UJs5+dcPhUZl6VPh" +
                                                    "OongT0mXLvQM3/O+wPfjGLE3F8vLEPQgPu4XX8r/M97X+Os4aZoiHbLb4Byi" +
                                                    "WgGjZQouddvreqAJCp2HL2jnNhoq5enGaA4F2EYzyC9/sEZoXLdGkgZvBNLj" +
                                                    "ZY/3P5g0MSIWgwKlT8zbcfq8F7NNpqXOUeye4O6fpYoxP06PC7h1nKwtq7pw" +
                                                    "KPLQ8fVdYUm6Yax2JVldRZLdOA1x0qBBGGc9RhuCjNQ89CIY9YZVm1e7y6u9" +
                                                    "Cq/REi/bZDJ+7K1IUATrOhgJl2CiCsGxymYkYLx6HUwoqDsshKeSgfgnWBY2" +
                                                    "VeucRNd38eTSsnLwuZ3OjdMZ7kZGoNl+5Q///W3q3F/erHBBtnDDvFNjc0wL" +
                                                    "8GxGlqGb7n7RVPqx/uSLL1/jbw98xWG5o3p+RhFfP/n3nsl7Zh/5FPfb5ojy" +
                                                    "UZIv3n/pzftul78XJ3WllCnrk8NIQ+FEabUYNPb6ZChdesOB0+WmjJc65XcM" +
                                                    "TgdqVLsjNc4ewukwpJVqQ0FQldpFL2OpeWgx59weWFrsfPfYhfdecZwRrXAR" +
                                                    "YHZ66cmPU2eW4oFXxbayxj6I47wshJirHZt8DH8xGP/DgTrghtNZdg677e2W" +
                                                    "Un9rmhjCW2uJJViM/u3y4q9eWDwVd22SAXPMGIbGqF5+RYiNyXAbMAAj6boo" +
                                                    "WdVFyYgbYk5Jw897BZRWw086TionbdCFT4TK3jROsiMX1PP6OUNVbik2DrIL" +
                                                    "RsoVO1W5e6lYPuK47IcylVV1qhUrq+VVyk1lJRnFh3cgvoSZYLNQQ+/HceKc" +
                                                    "rOIWNN6O5rhl3lJFURNvgzHoqji4Ys8EBfhmjbNv43QCnJILOEX4c2UO2AZj" +
                                                    "jyvdns8k3XdrnJ3B6TuQGSDdXri7CjYbB8tjGRJXy8pkxHg+4Mp44DPJuFTj" +
                                                    "7CxOT3PShRaEO6+gUevTCSkS8E4Yh10hD69YyLgfzeKe3StAL9QQdxmn8xCN" +
                                                    "kIVBMW8djcKW+ERQXDGVFYsZSajuYELl4dWdOsTw4fQlQeFiDelfwOnHnNQp" +
                                                    "Bq9UOqCfMyiv0BWDwoHXK/bk3WW/ezm/1civLieaNyw/+EfxHiv9ntKSJs3Z" +
                                                    "gqYFe8TAutG0WFYVMrY4HaPTjlzhpCNaPqDC4T8h5GUH7KcgXwAMqre7CgL9" +
                                                    "HPQGIFxeMz1bdoinBfa+Kaf3LZJQB2RG+6FtobtR/EboNQcF51fCafny8v4H" +
                                                    "Hrv55edEp9Ega3RhAak0p0mT88osNRhbq1LzaDXu6/+o/UrLdu9qasepM1Cc" +
                                                    "A7JtrvwCG8mbXLyZFn6x4Wd3P7/8jngCfgI0SvvavBUAAA==");
}
