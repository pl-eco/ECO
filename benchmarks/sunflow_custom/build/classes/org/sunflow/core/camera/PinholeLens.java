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
                           update(); }
    public boolean update(ParameterList pl, SunflowAPI api) { fov = pl.getFloat(
                                                                         "fov",
                                                                         fov);
                                                              aspect = pl.
                                                                         getFloat(
                                                                           "aspect",
                                                                           aspect);
                                                              update(
                                                                );
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
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1163484248000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfW9t3tmNyFydxEhPsxHGiJoHbpgKk1AhITjZx" +
                                                    "OOJTnESKI3Dmdufsjfd2Nrtz9sWpKURCCQiFCgwNFfUHFESTJgRVjdKqipQv" +
                                                    "bUD0C1VVxIdC1S9FQD7kA38EBfpmZvd2b+/OKVJVSzs3O/Pem/fmvfd7b33h" +
                                                    "BmpxHbTVpuaxCZOyNCmz9BHznjQ7ZhM3vTt7Tw47LtEzJnbdfbA2rvW9mfz8" +
                                                    "659NphQUH0PLsWVRhplBLXcvcak5TfQsSgargyYpugylskfwNFZLzDDVrOGy" +
                                                    "gSxaEmJlqD/rq6CCCiqooAoV1B0BFTDdRqxSMcM5sMXco+hxFMuiuK1x9Rha" +
                                                    "Xy3Exg4uemJywgKQ0MrfD4BRgrnsoHUV26XNNQa/uFWd//ljqd80oeQYShrW" +
                                                    "KFdHAyUYHDKGOoqkmCeOu0PXiT6GllmE6KPEMbBpzAq9x1Cna0xYmJUcUrkk" +
                                                    "vliyiSPODG6uQ+O2OSWNUadiXsEgpu6/tRRMPAG2dgW2SguH+DoY2G6AYk4B" +
                                                    "a8RnaZ4yLJ2h3ihHxcb+h4EAWBNFwiZp5ahmC8MC6pS+M7E1oY4yx7AmgLSF" +
                                                    "luAUhrobCuV3bWNtCk+QcYZWR+lycguo2sRFcBaGVkbJhCTwUnfESyH/3Nhz" +
                                                    "3+nj1i5LETrrRDO5/q3A1BNh2ksKxCGWRiRjx5bsS7jr6ikFISBeGSGWNFd+" +
                                                    "cvPBO3uuvSVpbq9DM5I/QjQ2rp3NL313bWbz9iauRqtNXYM7v8pyEf45b2eg" +
                                                    "bEPmdVUk8s20v3lt758OPnGefKKg9mEU16hZKkIcLdNo0TZM4jxELOJgRvRh" +
                                                    "1EYsPSP2h1EC5lnDInJ1pFBwCRtGzaZYilPxDldUABH8ihIwN6wC9ec2ZpNi" +
                                                    "XrYRQgl4UBqeViT/xC9DeXW/C+GuYg1bhkVVCF6CHW1SJRodz8PtThaxM+Wq" +
                                                    "WslltKi6Jatg0hnVdTSVOhOVd406RNUgwBys5gxrkpokSyw3zWPN/r+cUua2" +
                                                    "pmZiMXDD2igImJA/u6ipE2dcmy/tHLz5xvg7SiUpvFtiaAMclvYOS/PD0vKw" +
                                                    "dOgwFIuJM1bwQ6WbwUlTkO4AhB2bRx/dffhUXxPElz3TDDfMSfvASk+TQY1m" +
                                                    "AkwYFsinQWCufvXQyfSXrz8gA1NtDOB1udG1MzNPHvjpDxWkVCMxtwyW2jl7" +
                                                    "juNnBSf7oxlYT27y5EefX3ppjga5WAXtHkTUcvIU74v6wKEa0QE0A/Fb1uHL" +
                                                    "41fn+hXUDLgBWMkwxDbAUE/0jKpUH/Bhk9vSAgYXqFPEJt/ysa6dTTp0JlgR" +
                                                    "wbFUzJeBU5bw2O+Cp8NLBvHLd5fbfFwhg4l7OWKFgOWh3197+fIvtm5Xwgie" +
                                                    "DNXEUcIkHiwLgmSfQwis//1M7oUXb5w8JCIEKDbUO6CfjxlAB3AZXOtTbx19" +
                                                    "/8MPzv5VCaKKQZks5U1DK4OMTcEpgB0m4Bf3ff9+q0h1o2DgPEQuBOe/kxu3" +
                                                    "Xf70dEp604QVPxjuvLWAYH3NTvTEO4990SPExDReuwLLAzJ5AcsDyTscBx/j" +
                                                    "epSf/MsdL1/HvwRoBThzjVkiEAoJy5C4elW4aosY05G9bXxYZ9fslcXKavHW" +
                                                    "DEdvbpxEQ7wEh5LvqxEzf+KfXwqLatKnTuWJ8I+pF17pztz/ieAP4phz95Zr" +
                                                    "0QjalYD3R+eLnyl98T8qKDGGUprXCx3AZolHyxjUf9dvkKBfqtqvruWycA1U" +
                                                    "8nRtNIdCx0YzKEBBmHNqPm+PJI3IkTXwtHlJ0xZNmhgSk+2CpU+MG/nwAz9m" +
                                                    "E7ZjTGPeaCEFlxb3Uc4xilA8p73qrs51fjj1ykcXJUBGHRIhJqfmn/kufXpe" +
                                                    "CfVLG2paljCP7JmExbdJi7+Dvxg83/KHW8oXZM3szHiFe12lcts2T8T1i6kl" +
                                                    "jhj616W5P/xq7qQ0o7O6XRiEbvji3775c/rMP96uU53AZRSLnE3J6L/7+/tm" +
                                                    "mA8D/Pqn+WxnY2k98LR70tobSMt60uLYtSH4/gcSRzyJTQUaVlA4pj+U4DEx" +
                                                    "X8nQ7TUlOyNKNi/V3CV3NOo0hTvOnphf0Ede26Z42DLEUBuj9l0mmSZm6Lgm" +
                                                    "Lqmqij8ieusgj5859+sr7N2tP5aO3dI4rqOM10983L3v/snD36N290Zsioo8" +
                                                    "98iFtx/apD2voKYKHNR8LlQzDVSDQLtD4PvG2lcFBT0Vdy73w63Tc2dn3foZ" +
                                                    "OC1AckXcp+K7r6fGfcJUAl8jvFT4ZF1hslH5uyM3LI45vEitKPDhUQjQkq1D" +
                                                    "mgqaB/iQkdViEDApT6Gzw1ZtQRELB2tzrNszuruh0QOLqEQX2TvKB3NRdZun" +
                                                    "qaHfUtckX1wJT6+na+9/7aCEkJioJN/Oelo0wVcpnx6vtxnXKbQmInAeF4M4" +
                                                    "bHYRu0/wYQY4Jwjbi4/5Xl9RExywWafwM7Qk1KDztmN1zX8B5Jer9sZCsnXV" +
                                                    "wv73RMtZ+bpsg0+8Qsk0w2UwNI/bDikYQtM2WRQlID3N0KoGXwxgi5wIbU9J" +
                                                    "+mcZSkXpwaP8J0z2HNgTIoMQ9WZhoufBCUDEpy/Y/oWlRLfF24G0bAfKKARi" +
                                                    "vOEMv1V1nxynxH9YfEwpyf+xjGuXFnbvOX7z3tcEQLVoJp6d5VJasyghG+8K" +
                                                    "Lq1vKM2XFd+1+eulb7Zt9PF2KR86vW47oltv/aZ0sGgz0UbO/m7Vb+97feED" +
                                                    "0RX/B89vj0H6EgAA");
}
