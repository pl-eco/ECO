package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class NormalShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { Vector3 n = state.getNormal(
                                                                       );
                                                   if (n == null) return Color.
                                                                           BLACK;
                                                   float r = (n.x + 1) * 0.5F;
                                                   float g = (n.y + 1) * 0.5F;
                                                   float b = (n.z + 1) * 0.5F;
                                                   return new Color(r, g,
                                                                    b); }
    public void scatterPhoton(ShadingState state, Color power) {  }
    public NormalShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWwURRyfu37RUrjS8i2UFgpaCrcQgwkW0VILFA+4tEBC" +
                                                    "CRzT3bm7pXs7y+5cey1WgcRAeCBGC4LRPhgIonzFSNAYEp4Egi8Yo/FB8E2i" +
                                                    "8sALmqDif2buY2/vWuTFS3Zudub/n//3b/577j4qc2zUYlFjMGZQFiQpFtxj" +
                                                    "rAiyQYs4wQ2hFWFsO0RrN7DjbIG1iDr/UuDho7fjNX5U3oPqsGlShplOTaeL" +
                                                    "ONToJ1oIBXKrHQZJOAzVhPbgfqwkmW4oId1hrSE00cXKUFMoo4ICKiiggiJU" +
                                                    "UNpyVMA0iZjJRDvnwCZz9qI3kC+Eyi2Vq8dQY/4hFrZxIn1MWFgAJ0zg79vA" +
                                                    "KMGcslFD1nZpc4HBx1qUkfd21XxWggI9KKCb3VwdFZRgIKQHVSdIopfYTpum" +
                                                    "Ea0HTTEJ0bqJrWNDHxJ696BaR4+ZmCVtknUSX0xaxBYyc56rVrltdlJl1M6a" +
                                                    "F9WJoWXeyqIGjoGt03O2SgvX8nUwsEoHxewoVkmGpbRPNzWG5nk5sjY2vQYE" +
                                                    "wFqRICxOs6JKTQwLqFbGzsBmTOlmtm7GgLSMJkEKQ7PHPJT72sJqH46RCEMz" +
                                                    "vXRhuQVUlcIRnIWhaV4ycRJEabYnSq743N+06ug+c73pFzprRDW4/hOAqd7D" +
                                                    "1EWixCamSiRj9eLQcTz96mE/QkA8zUMsaa68/uCVJfXXbkiaZ4rQbO7dQ1QW" +
                                                    "UU/1Tr49p715ZQlXY4JFHZ0HP89ykf7h9E5ryoLKm549kW8GM5vXur7evv8T" +
                                                    "8psfVXWicpUayQTk0RSVJizdIPY6YhIbM6J1okpiau1ivxNVwDykm0Subo5G" +
                                                    "HcI6UakhlsqpeAcXReEI7qIKmOtmlGbmFmZxMU9ZCKEKeFALPFVI/sQ/Q31K" +
                                                    "nCaIglVs6iZVIHcJttW4QlQasYlFlY72zUoveDmewHafozhJM2rQgYiadBhN" +
                                                    "KI6tKtSOZZYVldpEceJYI7ayidoJbHSLlyBPOuv/FZfi1tcM+HwQmDleWDCg" +
                                                    "otZTA2gj6khyTceDC5Fb/myZpP0GQAbSgmlpQS4tKKUF3dKQzyeETOVSZeQh" +
                                                    "bn2AAICN1c3dOzfsPjy/BFLOGigFp3PS+WBwWpUOlbbnYKJTgKEKuTrzox2H" +
                                                    "gn+eeVnmqjI2phflRtdODBzY9uYyP/LngzM3DZaqOHuYQ2oWOpu8RVns3MCh" +
                                                    "ew8vHh+mufLMQ/s0ahRy8qqf7w2CTVWiAY7mjl/cgC9Hrg43+VEpQAnAJ8OQ" +
                                                    "7oBM9V4ZedXfmkFSbksZGBwV4eFbGfirYnGbDuRWRHZM5kOtTBQeQI+CAoTX" +
                                                    "fnnt5OX3W1b63XgdcN2A3YTJ6p+Si/8WmxBY/+lE+N1j9w/tEMEHigXFBDTx" +
                                                    "sR2wAKIBHnvrxt4f79459Z0/lzAMLsVkr6GrKThjUU4KIIUBaMXD2rTVTFBN" +
                                                    "j+q41yA87/4KLFx++fejNTJQBqxk4rzkyQfk1metQftv7fqjXhzjU/lNlbM8" +
                                                    "RyYdUJc7uc228SDXI3Xg27knr+MPAUgBvBx9iAg8QsIyJFwfFBFpFuNSz94y" +
                                                    "PjRYBXspsTIz/SZeGsXYxIdnpd/49Dk3pU/MpzE0o6CuZSlzB88d63ISF+up" +
                                                    "gyOj2ubTy2VZ1uYDfgf0M+e///ub4ImfbxZBk0pGraUG6SeGS6cSLjIPDjaK" +
                                                    "eztXFEfOfnqF3W55UYpcPDYSeBmvH/x19pbV8d1PAQLzPMZ7jzy78dzNdYvU" +
                                                    "d/yoJFv/Ba1IPlOr2w0g1CbQO5ncoXylSoS5XigwBdxRxwM6C56J6YtK/PPd" +
                                                    "OouPU2W18uF5T974hT/9mRjXF8RYmEqg0+GJmSGb7ibrlv9t4U4h5tVxMnMD" +
                                                    "H9qgNJOWBpc3RLF5nL7b1hPQCvSnexVluPZu3wf3zsuIehsbDzE5PHLkcfDo" +
                                                    "iN/V/S0oaMDcPLIDFFpOko59DD8fPP/wh5vAF2QHUNuebkMasn2IZfE6aBxP" +
                                                    "LSFi7S8Xh7/6ePiQP+2SlQxV9FJqEGwWVqxYeCkbZ/6gOfAE0nEO/Oc4+/Jr" +
                                                    "eW7RWobGlrf2RByzfZw47uTDNoYmxgjrAj6eskVhQk9Ah8shk9pPtE4k7ep0" +
                                                    "Jmcy+qmymL/28CEiSMk4FsT40Msg1ipmkN3hOGXpIg/xISy162KotJ/qWhEw" +
                                                    "Zaja3dBwLJ9Z8CElm3/1wmhgwozRrT+IKzrboFdClxxNGoar0N1FX27ZJKoL" +
                                                    "bSvlzWuJP1oMimWLBZUlJ0JdU9LDao2XHszif26yJITTRQZpmZ65icDqEiDi" +
                                                    "00ErE/EacYXxr6Og/BRIobybxsq/d9xXOi968ZGagc6k/EyNqBdHN2za9+CF" +
                                                    "0wKHy+DzdmhIfNTAN5psVLLw2zjmaZmzytc3P5p8qXJhpubyWhiPbvOK3/Qd" +
                                                    "CYuJu3noixmfrzozeke0Gv8CjAGLKD0QAAA=");
}
