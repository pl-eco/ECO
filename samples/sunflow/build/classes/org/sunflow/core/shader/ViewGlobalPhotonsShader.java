package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class ViewGlobalPhotonsShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { state.faceforward();
                                                   return state.getGlobalRadiance(
                                                                  );
    }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public ViewGlobalPhotonsShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XW2xURRie3d4LdttyRyhQKlouezQGEgQvtRYoLrJpgcQS" +
                                                    "qLPnzO4eOHvmcM5suxSrYGIgPhCjBcFoHwwEURRjJGoMSZ9Eoy8Yo/FB9E2i" +
                                                    "8sALmqDi/8/s9ey2yIubnNlz/vln/vs3/5y7Rmo8l6xwuLU/YXERZhkR3mOt" +
                                                    "Dov9DvPCmyOro9T1mNFtUc/bBrRBvf2D0I2bLyebg6R2gMygts0FFSa3vT7m" +
                                                    "cWuIGRESKlB7LJbyBGmO7KFDVEsL09IipifWRci0oqWCdERyKmigggYqaFIF" +
                                                    "ravABYvuYnY61Y0rqC28feQ5EoiQWkdH9QRZUrqJQ12aym4TlRbADvX4vQOM" +
                                                    "koszLlmct13ZXGbwsRXa2Gu7mz+sIqEBEjLtflRHByUECBkg01MsFWOu12UY" +
                                                    "zBggLTZjRj9zTWqZI1LvAdLqmQmbirTL8k5CYtphrpRZ8Nx0HW1z07rgbt68" +
                                                    "uMksI/dVE7doAmydXbBVWbgB6WBgowmKuXGqs9yS6r2mbQiyyL8ib2PHk8AA" +
                                                    "S+tSTCR5XlS1TYFAWlXsLGontH7hmnYCWGt4GqQIMn/STdHXDtX30gQbFGSu" +
                                                    "ny+qpoCrQToClwgyy88md4IozfdFqSg+155af/SAvckOSp0Npluofz0savMt" +
                                                    "6mNx5jJbZ2rh9OWR43T2xSNBQoB5lo9Z8Xz87PXHVrZNfKF47q7AszW2h+li" +
                                                    "UD8Va7q8oLtzbRWqUe9wz8Tgl1gu0z+anVmXcaDyZud3xMlwbnKi7/OnD77D" +
                                                    "fguSxl5Sq3MrnYI8atF5yjEt5m5kNnOpYEYvaWC20S3ne0kdvEdMmynq1njc" +
                                                    "Y6KXVFuSVMvlN7goDlugi+rg3bTjPPfuUJGU7xmHEFIHD1kDTwNRP/kviK4l" +
                                                    "eYppVKe2aXMNcpdRV09qTOeaR1OOBVHz0nbc4sOa5+oadxP5b527TPOS1GCu" +
                                                    "tsNkwxstHqNWNMkFpH2/pIcx2Zz/R0wGrW0eDgQgEAv8MGBBBW3iFvAO6mPp" +
                                                    "x3uuvz/4VTBfFlk/CYKCw1nBYRQcVoLDkwgmgYCUNxMVUEGHkO2F4gdYnN7Z" +
                                                    "v2vzM0faqyDbnOFq8DeytoPNWa16dN5dQIheiYM6pOnct3YeDv955lGVptrk" +
                                                    "cF5xNZk4MXxox/P3B0mwFJfRSiA14vIoomkeNTv89Vhp39DhqzfOHx/lhcos" +
                                                    "AfosYJSvxIJv98fD5TozAEIL2y9fTC8MXhztCJJqQBFATkEh0wGU2vwySgp/" +
                                                    "XQ5E0ZYaMDjO3RS1cCqHfI0i6fLhAkUmShMOrSpnMIA+BSX+bvh04uSF11es" +
                                                    "DRZDdajo8OtnQhV+SyH+21zGgP7jieirx64d3imDDxxLKwnowLEbYACiAR57" +
                                                    "8Yt9P/x05dS3wULCCDgP0zHL1DOwx7KCFAAJC4AKw9qx3U5xw4ybNGYxzLu/" +
                                                    "Qvc8cOH3o80qUBZQcnFeefsNCvR5j5ODX+3+o01uE9DxkCpYXmBTDphR2LnL" +
                                                    "del+1CNz6JuFJy/RNwFDAbc8c4RJKCLSMiJdH5YR6ZTjKt/c/TgsdsrmMpIy" +
                                                    "N/slP5bIsQOHe5Xf8PW+Ys6AfJ8lyJyyEleljA5eONm5JM/UUy+MjRtbTz+g" +
                                                    "yrK1FOt7oJV577u/vw6f+PnLCsDSILizymJDzCrSqQpFlsDBFnlkF4ripbPv" +
                                                    "fiwur3hIiVw+ORL4F1564df52x5JPnMHILDIZ7x/y7Nbzn25cZn+SpBU5eu/" +
                                                    "rAspXbSu2A0g1GXQNtnoUKQ0yjC3SQVawB0zMKDz4GnMnlHyH2dnODjOVNWK" +
                                                    "w4O+vAlKfwZzMW4ri7E0lUGTg4mZY5tdzNav/ruivVLME1Nk5mYcuqA0044B" +
                                                    "5zZEsXOKlts1U9AFDGXbFG209ae9b1x9T0XU39P4mNmRsZduhY+OBYsav6Vl" +
                                                    "vVfxGtX8SS3vUo69Bb8APP/ggyYgQR3+rd3ZDmRxvgVxHKyDJVOpJUVs+OX8" +
                                                    "6Gdvjx4OZl2yVpC6GOcWo3Z5xUrCw/k440MWwNOUjXPTf45zoLSWF1asZehp" +
                                                    "satncpunp4jjLhx2CDItwUQfrMOUrQgTZgqaW4RM7t7WumlIfCSXzrn/O8li" +
                                                    "/BzAYVCysiksSOAQExBrnQrI7mxzgtQIDlGlXZ8g1UPcNCqAKVg6SW+DsD63" +
                                                    "7DqlrgD6++Oh+jnj27+Xp3W+TW+AXjmetqyimi+u/1rHZXFTKt6gDmFH/vFK" +
                                                    "qKwaLygy9SI1txU/UJv9/GAh/hWzpSGyRWyQodm3YiZwQBUw4et+Jxf8Znma" +
                                                    "4R0prC4EGVJy6DilR1Dx6Y71L6+qORRNq8vqoH5+fPNTB66vOS0huQYuuSMj" +
                                                    "8moDNzXVs+SReMmku+X2qt3UebPpg4Z7cuVX0s34dFtU+dDvSTlCHtMjn8z5" +
                                                    "aP2Z8Suy6/gXXIsGYEMQAAA=");
}
