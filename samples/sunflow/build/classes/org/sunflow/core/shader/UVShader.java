package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class UVShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { if (state.getUV() == null)
                                                       return Color.
                                                                BLACK;
                                                   return new Color(state.
                                                                      getUV(
                                                                        ).
                                                                      x, state.
                                                                           getUV(
                                                                             ).
                                                                           y,
                                                                    0);
    }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public UVShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXXWwURXju+l9KW0opBaG0UMBSuIUYTLCI1lqg9YBLC00o" +
                                                    "gTLdnbtburez7M6112IVSEwJD8RoQTDaBwNBlL8YCRpD0ieB4AvGaHwQfJOo" +
                                                    "PPCCJqj4zcz97l2LPHjJzs3OfP//e/4+KnBs1GxRYzhkUOYjMebbZ6z1sWGL" +
                                                    "OL5O/9oAth2itRnYcbbDWZ+6+HLFw0dvhyu9qLAXzcamSRlmOjWdLuJQY5Bo" +
                                                    "flSROm03SMRhqNK/Dw9iJcp0Q/HrDmvxoxlpqAw1+hMiKCCCAiIoQgSlNQUF" +
                                                    "SDOJGY20cQxsMmc/egN5/KjQUrl4DDVkErGwjSNxMgGhAVAo5u89oJRAjtmo" +
                                                    "Pqm71DlL4ePNyvh7eyo/y0MVvahCN7u5OCoIwYBJLyqLkEg/sZ1WTSNaL5pl" +
                                                    "EqJ1E1vHhj4i5O5FVY4eMjGL2iRpJH4YtYgteKYsV6Zy3eyoyqidVC+oE0NL" +
                                                    "vBUEDRwCXWtSukoNN/JzULBUB8HsIFZJAiV/QDc1hha5MZI6Nr4GAIBaFCEs" +
                                                    "TJOs8k0MB6hK+s7AZkjpZrZuhgC0gEaBC0PzpyTKbW1hdQCHSB9DtW64gLwC" +
                                                    "qBJhCI7C0Bw3mKAEXprv8lKaf+5vXX/sgLnZ9AqZNaIaXP5iQKpzIXWRILGJ" +
                                                    "qRKJWLbCfwLXXDviRQiA57iAJczV1x+8vLJu8oaEeSYHzLb+fURlferp/vLb" +
                                                    "C9qa1uVxMYot6ujc+Rmai/APxG9aYhZkXk2SIr/0JS4nu77eefAT8psXlXag" +
                                                    "QpUa0QjE0SyVRizdIPYmYhIbM6J1oBJiam3ivgMVwd6vm0SebgsGHcI6UL4h" +
                                                    "jgqpeAcTBYEEN1ER7HUzSBN7C7Ow2McshFARPGg5PCVI/sQ/Q9uVMI0QBavY" +
                                                    "1E2qQOwSbKthhahUcXDEMsBrTtQMGnRIcWxVoXYo+a5SmyhOGGvEVnb0dIuN" +
                                                    "j0eX9T/RjXF9Koc8HjD1AneiG5Ajm6kBsH3qePSV9gcX+255k4EftwRD9cDJ" +
                                                    "F+fk45x8kpMvwQl5PIJBNeco/QheGIB8hkpX1tS9u3PvkcV5EEDWUD6YkIMu" +
                                                    "Bq3iYrSrtC2V9B2itKkQebUf7Rrz/Xn2JRl5ytQVOic2mjw5dKjnzdVe5M0s" +
                                                    "tVwtOCrl6AFeIJOFsNGdYrnoVozde3jpxChNJVtG7Y7XgGxMnsOL3Q6wqUo0" +
                                                    "qIop8ivq8ZW+a6ONXpQPhQGKIcMQvFBn6tw8MnK5JVEXuS4FoHCQ2hFs8KtE" +
                                                    "MStlYZsOpU5EZJTzpUoGCXegS0BRUjd+OXnqyvvN67zp1bcirZ91EyZzeVbK" +
                                                    "/9ttQuD8p5OBd4/fH9slnA8QS3IxaORrG2Q2eAMs9taN/T/evXP6O28qYBi0" +
                                                    "uGi/oasxoLEsxQXy3oDaw93auMOMUE0P6rjfIDzu/qpYuubK78cqpaMMOEn4" +
                                                    "eeWTCaTO572CDt7a80edIONRed9JaZ4CkwaYnaLcatt4mMsRO/TtwlPX8YdQ" +
                                                    "FqEUOfoIEdUFCc2QML1PeKRJrKtcd6v5Um9l3cXESW38Tbw0iLWRL8ul3fj2" +
                                                    "2XRIj9jPYWhuVk7LVOYGXjhVqxFt8vTh8Qlt25k1Mi2rMst3O0wnF77/+xvf" +
                                                    "yZ9v5qgkJYxaqwwySIw0mfI4y4xysEV04VRSHD336VV2u/kFyXLF1JXAjXj9" +
                                                    "8K/zt28I732KIrDIpbyb5Lkt529uWqa+40V5yfzPGiwykVrSzQBMbQKTkMkN" +
                                                    "yk9KhZvrhACzwByzuUPnwVMabzvin9/OtvhaLbOVL8+54sYr7OlN+Lguy8dC" +
                                                    "VQJzCw/MBFhNOli3/G8NdAg2r04TmZ18aYXUjFoatGLwYtM0U7StR6CxD8Yn" +
                                                    "D2W06u7AB/cuSI+6xxQXMDkyfvSx79i4N22WW5I1TqXjyHlOSDlTGvYx/Dzw" +
                                                    "/MMfrgI/kP28qi0+VNQnpwrL4nnQMJ1YgsXGXy6NfvXx6Jg3bpJ1DBX1U2oQ" +
                                                    "bGZnrDh4Meln/qAF8JTH/Vz+n/3syczlhTlzGcZUPqgTQWbnNH7czZcehmaE" +
                                                    "COsCPB6yOcuEHoF5lZdMaj9Ruxn8cAM81XHtqp82ivlrL1/6BCiZRoMQX/oZ" +
                                                    "+FrFDKI7EKYsnuR+vgSkdF0M5Q9SXctRTBkqTgwzvI7XZn0SyTFevThRUTx3" +
                                                    "YscPoj0nR+0SmHeDUcNIS/L0hC+0bBLUhaQlsuta4o/mKsNytIKskhshqinh" +
                                                    "4bTSDQ8q8b90sCi4Mg0MQjK+SwcCjfMAiG+HrYS3K0X74t85PjnUx1BGl7Ey" +
                                                    "e056O+cJLz43E2UzKj84+9RLE51bDzx4/oyowQXwoToyIj5P4GtLDinJ0tsw" +
                                                    "JbUErcLNTY/KL5csTeRbxvjikm1R7i7fHrGY6MsjX8z9fP3ZiTtizPgXvoC3" +
                                                    "rwcQAAA=");
}
