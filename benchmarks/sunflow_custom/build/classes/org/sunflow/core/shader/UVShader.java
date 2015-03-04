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
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0XXWwURXju+l+gLW0pBaEUKGAp3EIMJgiitRYoHnBpgYQS" +
                                                    "OKa7c3dL93aW3bn2KFaBxEB4IEYLgtE+GAii/MVI0BiSPgkEXzBG44Pgm0Tl" +
                                                    "gRc0QcVvZm7vZ+9a5MVLdm72m++b7/9nz99HJY6N2ixq7IsalAVIkgX2GCsC" +
                                                    "bJ9FnMCG4IoQth2idRjYcbYALKzOu1z98NHbsRo/Ku1Fddg0KcNMp6bTTRxq" +
                                                    "DBAtiKoz0E6DxB2GaoJ78ABWEkw3lKDusFVBNCmLlKGWoCuCAiIoIIIiRFDa" +
                                                    "M1hANIWYiXgHp8Amc/aiN5AviEotlYvH0NzcSyxs43jqmpDQAG4o5+/bQClB" +
                                                    "nLRRc1p3qXOewsfblJH3dtV8VoSqe1G1bvZwcVQQggGTXjQ5TuJ9xHbaNY1o" +
                                                    "vWiqSYjWQ2wdG/qQkLsX1Tp61MQsYZO0kTgwYRFb8MxYbrLKdbMTKqN2Wr2I" +
                                                    "TgzNfSuJGDgKujZkdJUaruVwULBSB8HsCFaJS1Lcr5saQ3O8FGkdW14DBCAt" +
                                                    "ixMWo2lWxSYGAKqVvjOwGVV6mK2bUUAtoQngwtDMcS/ltraw2o+jJMxQoxcv" +
                                                    "JI8Aq0IYgpMwNM2LJm4CL830eCnLP/c3rT6231xv+oXMGlENLn85EDV5iLpJ" +
                                                    "hNjEVIkknLw4eAI3XDviRwiQp3mQJc7V1x+8vKRp7IbEeaYAzua+PURlYfV0" +
                                                    "X9XtWR2tK4u4GOUWdXTu/BzNRfiHUierkhZkXkP6Rn4YcA/Hur/efuAT8psf" +
                                                    "VXahUpUaiTjE0VSVxi3dIPY6YhIbM6J1oQpiah3ivAuVwT6om0RCN0ciDmFd" +
                                                    "qNgQoFIq3sFEEbiCm6gM9roZoe7ewiwm9kkLIVQGD1oETwWSP/HPUFSJ0ThR" +
                                                    "sIpN3aQKxC7BthpTiErDNrGo0tmxWekDK8fi2O53FCdhRgw6GFYTDqNxxbFV" +
                                                    "hdpRF6yo1CaKE8MasZWt23rEJsADzvr/WCW51jWDPh84ZJa3HBiQSeupAbhh" +
                                                    "dSTxSueDi+Fb/nR6pOzFUDNwCqQ4BTingOQUcDkhn08wqOccpbfBV/2Q9VAP" +
                                                    "J7f27Nyw+8i8Iggza7AYDM1R54GiKTE6VdqRKQ1dogCqEJ+NH+04HPjz7Esy" +
                                                    "PpXx63hBajR2cvDgtjeX+ZE/tyBztQBUyclDvIymy2WLNxEL3Vt9+N7DSyeG" +
                                                    "aSYlcyp8qlLkU/JMn+d1gE1VokHtzFy/uBlfCV8bbvGjYigfUDIZhhCHatTk" +
                                                    "5ZGT8avc6sl1KQGFI9SOY4MfuSWvksVsOpiBiMio4kutDBLuQI+AovCu/XLs" +
                                                    "1JX321b6s2t0dVbX6yFMZvzUjP+32IQA/KeToXeP3z+8QzgfMOYXYtDC1w7I" +
                                                    "f/AGWOytG3t/vHvn9Hf+TMAwaISJPkNXk3DHwgwXqA4GVCju1patZpxqekTH" +
                                                    "fQbhcfdX9YLlV34/ViMdZQDE9fOSJ1+Qgc94BR24teuPJnGNT+XdKaN5Bk0a" +
                                                    "oC5zc7tt431cjuTBb2efuo4/hOIJBcvRh4ioQUhohoTpA8IjrWJd6jlbxpdm" +
                                                    "K+8sKSCNqTfxMlesLXxZJO3Gt89mY/rEfhpD0/NyWqYyN/Ds8RqSaKanD42M" +
                                                    "apvPLJdpWZtb5Dthhrnw/d/fBE7+fLNAJalg1FpqkAFiZMlUxFnmlIONoldn" +
                                                    "kuLouU+vstttL0iWi8evBF7C64d+nbllTWz3UxSBOR7lvVee23j+5rqF6jt+" +
                                                    "VJTO/7zxI5doVbYZgKlNYF4yuUE5pFK4uUkIMBXMUccdOgOeylRzEv/8tM7i" +
                                                    "a73MVr4854kbv7Cn3/VxU56PhaoEphsemC5aQzZaj/xvD3UJNq9OEJkb+NIO" +
                                                    "qZmwNGjY4MXWCWZtW49D+x9IzSfKcO3d/g/uXZAe9Q4zHmRyZOTo48CxEX/W" +
                                                    "xDc/b+jKppFTn5ByijTsY/j54PmHP1wFDpBdv7YjNXo0p2cPy+J5MHcisQSL" +
                                                    "tb9cGv7q4+HD/pRJVjJU1kepQbCZn7EC8GLaz/xBs+CpSvm56j/72Zeby7ML" +
                                                    "5jIMs3ycJ+Ka7RP4cSdftjE0KUpYN9DxkC1YJvQ4TLW8ZFL7idpN4sA18NSn" +
                                                    "tKt/2ijmr718CQtUMoEGUb70MfC1ihlEdyhGWSrJg3wJSem6GSoeoLpWoJgy" +
                                                    "VO4OM7yON+Z9OMlhX704Wl0+fXTrD6I9pwfyCpiKIwnDyEry7IQvtWwS0YWk" +
                                                    "FbLrWuKPFirDcrSCrJIbIaop8QFa48UHlfhfNloCXJmFBiGZ2mUjgcZFgMS3" +
                                                    "+yzX2zWiffGvoYAc/ZMop8tYuT0nu53zhBcfpW7ZTMjP0rB6aXTDpv0Pnj8j" +
                                                    "anAJfM4ODYmPGPgmk0NKuvTOHfc2967S9a2Pqi5XLHDzLWd88cg2p3CX74xb" +
                                                    "TPTloS+mf7767OgdMWb8C+ggCaotEAAA");
}
