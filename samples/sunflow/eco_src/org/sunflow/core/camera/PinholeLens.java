package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class PinholeLens implements CameraLens {
    private float au;
    private float av;
    private float aspect;
    private float fov;
    public PinholeLens() { super();
                           fov = 90;
                           aspect = 1;
                           this.update(); }
    public boolean update(ParameterList pl, SunflowAPI api) { fov = pl.getFloat(
                                                                         "fov",
                                                                         fov);
                                                              aspect = pl.
                                                                         getFloat(
                                                                           "aspect",
                                                                           aspect);
                                                              this.
                                                                update();
                                                              return true;
    }
    private void update() { au = (float) Math.tan(Math.toRadians(
                                                         fov *
                                                           0.5F));
                            av = au / aspect; }
    public Ray getRay(float x, float y, int imageWidth, int imageHeight,
                      double lensX,
                      double lensY,
                      double time) { float du = -au + 2.0F *
                                       au *
                                       x /
                                       (imageWidth -
                                          1.0F);
                                     float dv = -av + 2.0F *
                                       av *
                                       y /
                                       (imageHeight -
                                          1.0F);
                                     return new Ray(0, 0,
                                                    0,
                                                    du,
                                                    dv,
                                                    -1); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1163484248000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVYf2zc1B1/ubvkmkumXNI2bdOSNGmA0ZbzQCvaGqQ2y1K4" +
                                                   "1qXHpQmQwsKL/e7i\nxmcb+/lySauuaBItdBur2KRNg7ZC3VoYDCQ2dZMYKw" +
                                                   "I2RjUJJo1JSHSbKm2TNiZNk1in7Y9933v2\n2ee7C1BpJ9l+9vv+eN9fn/d9" +
                                                   "99wHqNWx0QbFydBFiziZsYkcth2ijunYcfbDpxnljdb23Lk9hhlD\nLTKKaS" +
                                                   "pFXbLiSCqmWNJUKfvFkYqNtlimvljUTZohFZo5qG/z5O2Wt9UJvOfUhZ6Hzy" +
                                                   "YGYqhVRl3Y\nMEyKqWYa4zopORSl5YO4jCWXarokaw4dkdGniOGWxkzDodig" +
                                                   "zkPoCIrLqM1SmEyKBmVfuQTKJQvb\nuCRx9VKOqwUJK21CsWYQdbSqDji31n" +
                                                   "LCsj2+fD01CFnBJqfAHL4CsHpj1WphbZ2pVvz81G2HzzwT\nR13TqEszJpgw" +
                                                   "BSyhoG8adZZIaZbYzqiqEnUadRuEqBPE1rCuLXGt06jH0YoGpq5NnDxxTL3M" +
                                                   "CHsc\n1yI21+l/lFGnwmyyXYWadtVHBY3oqv/WWtBxEczuDcwW5u5i38HAlA" +
                                                   "YLswtYIT5LYl4zIOIDUY6q\njcN7gABYkyVC58yqqoSB4QPqEbHUsVGUJqit" +
                                                   "GUUgbTVd0EJRX1OhzNcWVuZxkcxQtDZKlxNTQNXO\nHcFYKFodJeOSIEp9kS" +
                                                   "iF4rOl98Pj5598ZSfP7YRKFJ2tPwVM/RGmPCkQmxgKEYxX3cw3s/e5G2II\n" +
                                                   "AfHqCLGgGb3+wqT8l58PCJr1DWj2zR4kCp1R7jo5kD90h4nibBkrLNPRWPBr" +
                                                   "LOflkPNmRioWVG1v\nVSKbzPiTF/O/uO/os+SvMZTKojbF1N0S5FG3YpYsTS" +
                                                   "f2HcQgNqZEzaJ2YqhjfD6LkjCWIeXF132F\ngkNoFiV0/qnN5O/gogKIYC5q" +
                                                   "h7FmFEx/bGE6x8cVCyGUhAtl4FqBxI8/KbotIzmuUdDNBcmxFcm0\ni9V3xb" +
                                                   "SJpEDS2FjKacacqROZGE6G5Y9FUV6aM0tEwgo2NMOUihpUrGLerJIye16T1A" +
                                                   "pbb89CSwsD\nwGgh61ADd5q6SuwZ5dyVtw6P73n0uEgSltiepRRtAmUZT1mG" +
                                                   "KcsIZZmQMtTSwnWsYkpFqMDR81Cy\nAG6dN008sPvB40NxyBFrIQFeYqRDYJ" +
                                                   "O3knHFHAvqOsshUIHkWvv0gWOZq+d2iOSSmsNvQ+6Ot5+/\ndOafnZtjKNYY" +
                                                   "G5mFgM4pJibHALWKecPRamok/++P7X3p3UvvfzqoK4qG68q9npOV61A0Frap" +
                                                   "EBUA\nMBB/dl1X/B40dTKGEoABgHt8/QAp/VEdNWU74kMgsyUpo46CaZewzq" +
                                                   "Z83ErROdtcCL7wJEnz8UoI\nTgfL4164Or3E5k82u9pi916RVCzaESs4xF79" +
                                                   "SttnfvdyxxvcLT4ad4X2uwlCRW13B8my3yYEvr//\n7dwT3/rg2AGeKV6qUN" +
                                                   "gE3VldUyrAckPAAkWtA7CwQA5PGiVT1QoanoV0hIz7b9f1t/z4b19Pi9Do\n" +
                                                   "8MWP7NaPFhB8X/cFdPTSl/7Vz8W0KGxTCcwIyIQ1KwPJo7aNF9k6Kg//5rrv" +
                                                   "/BI/BZgHOONoS4RD\nB+KWIe5Hift9M79nInO3sNsQyN7aJPUbbOEzyuFni0" +
                                                   "PuQ7/6KV91Bw73AuEw7MXWiIg8u21i3l0T\nrd47sTMHdJ+9eNf9af3if0Di" +
                                                   "NEhUYOt09tmAG5WaIHrUrcn3Xn2t98F34ii2C6V0E6u7MM9/1A6J\nRxzADL" +
                                                   "Vi7djJcyu9wGAzzU1G3Al9ngMqobcELO6m5uW/izUAQeXMzG49L7+17ynugK" +
                                                   "aF32D/i8hZ\nemXy1NVf08tcTlCBjHuwUo+n0DQFvJ97t9zd9uLpUgwlp1Fa" +
                                                   "8dq6Kay7LM+noQtx/F4PWr+a+dqO\nQmyfI1WE2RCt/pDaaO0HOA5jRs3GnZ" +
                                                   "Fy59W9Dq52r9zbo+XegvhgB2cZ5vcbq8WZtGytjFmrh2LY\nhTitDffZtlaC" +
                                                   "/brMkenKI0M/e3Py9DGB5suEs4ZrRvny7/8wH3/81VnBF41ZhPhk/9k/vXQl" +
                                                   "v0pU\nvmjsNtX1VmEe0dxxp3RZrAoGl9PAqV/fMvjckfxlb0U9tS3KOLTxf1" +
                                                   "58jdx4+9f+2GA3hQCZmAoE\nZfdb2W2nSPZtTYti+ycPV47dRllUymyUjai8" +
                                                   "+xOq7Icr5alMNVE56alsw44FSdtI7dT/Qe20pzZe\nMBuaemAZnTzuN4Sgpo" +
                                                   "WP11C0vq7tGeNtD2t3WJpc16zj5ily7N5/dD6CX38g5kH5HgroZ1o366RM\n" +
                                                   "9JC6OJNU0wnt5WeMAEkee+YHF+g7W7aLZNvcvGyijJu3n1ka2P7CiWvofwYi" +
                                                   "tkVFd5fX3x2f096M\n8WOQAKa641Mt00gtHKVgPa5t7K8BpY21PcgIXD1e7H" +
                                                   "sa9iBB8IINNMb9GvPD2F8XRm4qgdMZ26F9\nst4w2YR4juayXE1pmS2afzwI" +
                                                   "Ke9aKqAgfx23RMLtBnScNaFDxkaQjPMfVQD+/sdfCvV13+d5pK+p\nR0aXWe" +
                                                   "+RZeaOstuhprYkyqamBoYcvkZDOsS6ERrwDBn42KFNconJao1no0uMw9meDb" +
                                                   "8anWhTTegh\neao9zm9cyYllnPEEux0HziKhebzo58mqunTyJ7lTHv24ToHt" +
                                                   "siN0fmIN5Nq6P1rEnwOK/N6h+z+U\nf/tvfhKoHuA74BRdcHU9vMeHxm2WTQ" +
                                                   "oaN6VD7PgWfzxJ0ZomBzowVgz4Sr8r6E9TlI7SQy6wR5js\nabAnRAaZ743C" +
                                                   "RN+DCAERG37f8j2a5g0k63Uyotep1DiKeWZTDejx/758YHLFv18zyr3PH9hY" +
                                                   "ObH/\nGxztWhUdLy0xMSkZJcUJqApug02l+bLeRi++MPXyDz/vgzfvkFdVgm" +
                                                   "2lJrVvFbPLBB4AtfGxY7xk\nUX5QWPrJmh/dfu7U5Rg/+PwPJtoWDbIUAAA=");
}
