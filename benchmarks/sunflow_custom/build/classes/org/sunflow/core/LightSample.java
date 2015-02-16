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
    public static final long jlc$SourceLastModified$jl7 = 1170611622000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYa2wcR3nu/HYcn2PnYdzESZxLheP2lgBBMq5aEsdunFwb" +
                                                    "y3ai4Ki9jnfnzhvvq7tz9sXFpYmAhApFPNyQoGAJlKoP0iYCQkCoUv5AW5U/" +
                                                    "RQjED1rEHypKfuQHpaJA+b7Z3du9vUfcSlia8ezM937NN3flFmlwbDJgmdrJ" +
                                                    "nGbyFCvw1AltT4qftJiTOpjeM05thynDGnWcKdjLyH3XEu998M3ZjjhpnCZd" +
                                                    "1DBMTrlqGs4Ec0xtnilpkgh2RzSmO5x0pE/QeSrluapJadXhQ2myJoTKSTLt" +
                                                    "iyCBCBKIIAkRpL0BFCCtZUZeH0YManDncfIkiaVJoyWjeJxsLyViUZvqHplx" +
                                                    "oQFQaMbvo6CUQC7YZFtRd1fnMoWfGZCWv/tox4/rSGKaJFRjEsWRQQgOTKZJ" +
                                                    "m870GWY7exWFKdNkncGYMslslWrqopB7mnQ6as6gPG+zopFwM28xW/AMLNcm" +
                                                    "o252XuamXVQvqzJN8b8ashrNga4bA11dDUdxHxRsVUEwO0tl5qPUz6mGwsnW" +
                                                    "KEZRx+QhAADUJp3xWbPIqt6gsEE6Xd9p1MhJk9xWjRyANph54MJJT1WiaGuL" +
                                                    "ynM0xzKcdEfhxt0jgGoRhkAUTjZEwQQl8FJPxEsh/9x6+L5zTxgHjLiQWWGy" +
                                                    "hvI3A1JvBGmCZZnNDJm5iG270ufpxlfOxgkB4A0RYBfmxpduf+Ge3puvuTB3" +
                                                    "VYA5PHOCyTwjX55pf3PzcP9gHYrRbJmOis4v0VyE/7h3MlSwIPM2FiniYco/" +
                                                    "vDnx6y8+9SJ7N05ax0ijbGp5HeJonWzqlqox+0FmMJtypoyRFmYow+J8jDTB" +
                                                    "Oq0azN09nM06jI+Rek1sNZriG0yUBRJooiZYq0bW9NcW5bNiXbAIIU0wSBuM" +
                                                    "VuL+if+cHJeOOBDuEpWpoRqmBMHLqC3PSkw2MzNg3Vmd2nOOJOcdbuqSkzey" +
                                                    "mrkgObYsmXau+C2bNoNSkJvlk1S3NJbCILP+v+QLqF3HQiwGht8cTXsNMuaA" +
                                                    "qSnMzsjL+X0jt1/OvBEvpoFnF042A5eUxyWFXFIhLiQWE8TXIzfXo+CPOchs" +
                                                    "qHlt/ZOPHHzsbF8dhJK1UA/GRNA+0MsTYUQ2h4P0HxNFToYY7P7h8TOp9597" +
                                                    "wI1BqXqtrohNbl5YOHX0y5+Kk3hp0UWVYKsV0cexVBZLYjKabJXoJs68897V" +
                                                    "80tmkHYlVdyrBuWYmM19UePbpswUqI8B+V3b6PXMK0vJOKmHEgFlkVMIY6g4" +
                                                    "vVEeJVk95FdI1KUBFM6atk41PPLLWiuftc2FYEdERbtYrwOnrMEw3whjvRf3" +
                                                    "4j+edlk4r3ejCL0c0UJU4NFf3Lx4/XsDg/FwsU6Err9Jxt3UXxcEyZTNGOz/" +
                                                    "6cL4d565dea4iBCA2FGJQRLnYSgE4DIw61dfe/yPb791+XfxIKo43Ij5GU2V" +
                                                    "C0Dj7oALlAkNShX6PnnE0E1Fzap0RmMYnP9O7Nx9/e/nOlxvarDjB8M9dyYQ" +
                                                    "7H9iH3nqjUf/2SvIxGS8pgLNAzDXAF0B5b22TU+iHIVTv91y8VX6faiiULkc" +
                                                    "dZGJYkSEZkSYXhKu2iXmVORsN07brLKzgtjpFl/1wLq/ehKN4m0bSr5/HdZm" +
                                                    "Tv/lfaFRWfpUuGQi+NPSlUs9w/e/K/CDOEbsrYXyMgSdSYD76Rf1f8T7Gn8V" +
                                                    "J03TpEP22p6jVMtjtEzDVe/4vRC0RiXnpde2e0cNFfN0czSHQmyjGRSUP1gj" +
                                                    "NK5bI0mD9wTp8bPH/x9OmhgRi0GB0ifmnTh90o/ZJstW5yn2VNARzFLFXJig" +
                                                    "JwXcBk7Wl1VdOBR56Pr6s6WSdMNY60mytooke3Ea4qRBgzDO+ow2hRmpOnQo" +
                                                    "GPWmXZtXu8ervQqv0SIvx2IyfuyvSFAE6wYYCY9gogrBscpmJGC8egNMKKi7" +
                                                    "LISnkqH4J1gWtlTrp0QvePn08opy+Nnd7o3TWdqjjEAL/tLv//Ob1IU/v17h" +
                                                    "gmzhpnWvxuaZFuLZjCxLbrqHRKsZxPrTL/zoBn9z4PMuy13V8zOK+Orpv/VM" +
                                                    "3T/72Ee437ZGlI+SfOGhK68/eLf87TipK6ZMWfdcijRUmiitNoN235gqSZfe" +
                                                    "0sDp8lLGT53yOwanQzWq3fEaZ4/gdAzSSnWgIKhK7aI3bqs6NJ7zXmcsLXW+" +
                                                    "PXfpnZdcZ0QrXASYnV1++sPUueV46K2xo6zdD+O47w0h5lrXJh/CXwzGf3Gg" +
                                                    "Drjh9pudw17Tu63Y9VoWhvD2WmIJFqN/vbr0y+eXzsQ9m4yDOWZMU2PUKL8i" +
                                                    "xMZUaRswACPpuShZ1UXJiBtibknDzwcElFbDTwZOKidt0JtPlpS9DE6yKxfU" +
                                                    "8/p5U1XuKDYOsgdGyhM7Vbl7qVg+4rjshzKVVQ2qFSqr5VfKLWUlGcWH1yG+" +
                                                    "j5lgs1hD7ydx4pys4TY03q7muGXdUUVRE++CMeipOLhqz4QF+EqNs6/hdAqc" +
                                                    "kgs5RfhzdQ7YAWOfJ92+jyXdN2qcncPp65AZIN1+uLvyDpsAy2MZElfL6mTE" +
                                                    "eD7kyXjoY8m4XOPsPE7f4qQLLQh3Xl6j9kcTUiTgvTCOeUIeW7WQ8SCaxT27" +
                                                    "X4BeqiHuCk4XIRohC8Ni3jkahS3xiaB4YiqrFjOSUN3hhNLhLZ46yvDh9BlB" +
                                                    "4XIN6Z/H6Qec1Ckmr1Q6oJ8zKa/QFYPCodcr9uTdZb+Gub/gyC+vJJo3rRz5" +
                                                    "g3iPFX9laUmT5mxe08I9YmjdaNksqwoZW9yO0W1HrnHSES0fUOHwnxDyqgv2" +
                                                    "E5AvBAbV21uFgX4GegMQLm9Yvi07xNMCe9+U2/sWSEkHZEX7oR0ld6P45dBv" +
                                                    "DvLub4cZ+erKwYefuP25Z0Wn0SBrdHERqTSnSZP7yiw2GNurUvNpNR7o/6D9" +
                                                    "WstO/2pqx6kzVJxDsm2t/AIb0S0u3kyLP9/00/ueW3lLPAH/B6MobgzSFQAA");
}
