package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class SimpleShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { return new Color(Math.
                                                                      abs(
                                                                        state.
                                                                          getRay(
                                                                            ).
                                                                          dot(
                                                                            state.
                                                                              getNormal(
                                                                                ))));
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        
    }
    public SimpleShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAALUXW2wUVfTu9kVLbUsLBREK1Poo1R2I0QTxVWuR1gU2XSBS" +
       "A+vtzN3dgdm548zddqnWV2IgfBCjVdFoPwwEH7xiJGgMSb9Egz8ao/FD9E+j" +
       "8sGPmvg85959zM5uq364ydy9c+95v+fEZVLnuaTP4db+lMVFhOVEZK91a0Ts" +
       "d5gXGY7eGqOux4wBi3redjhL6N1nWn/+7dl0W5jUj5IOattcUGFy2xthHrfG" +
       "mRElraXTQYtlPEHaonvpONWywrS0qOmJjVGy0IcqSE+0IIIGImgggiZF0PpL" +
       "UIB0FbOzmQHEoLbwHiGPk1CU1Ds6iifImnIiDnVpJk8mJjUACgvwfScoJZFz" +
       "Llld1F3pXKHwC33a9Et72t6pIa2jpNW04yiODkIIYDJKmjMsM8Zcr98wmDFK" +
       "FtmMGXHmmtQyJ6Xco6TdM1M2FVmXFY2Eh1mHuZJnyXLNOurmZnXB3aJ6SZNZ" +
       "RuGtLmnRFOjaWdJVabgJz0HBJhMEc5NUZwWU2n2mbQiyKohR1LHnAQAA1IYM" +
       "E2leZFVrUzgg7cp3FrVTWly4pp0C0DqeBS6CLJ+TKNraofo+mmIJQZYF4WLq" +
       "CqAapSEQRZAlQTBJCby0POAln38ub73j8KP2ZjssZTaYbqH8CwCpK4A0wpLM" +
       "ZbbOFGLz2uiLtPP8wTAhALwkAKxgzj125Z6bumY/UjDXVIHZNraX6SKhHx1r" +
       "+XTFQO+GGhRjgcM9E51fprkM/1j+ZmPOgczrLFLEy0jhcnbkw11PvsV+DJOm" +
       "IVKvcyubgThapPOMY1rMvZ/ZzKWCGUOkkdnGgLwfIg2wj5o2U6fbkkmPiSFS" +
       "a8mjei7fwURJIIEmaoC9aSd5Ye9QkZb7nEMIaYCH9MHTSNRP/gvyoJbmGaZR" +
       "ndqmzTWIXUZdPa0xnWsezTgWeM3L2kmLT2ieq2vcTRXfde4yzUtTg7la3ETY" +
       "uHyJYIQ5/yPtHOrVNhEKgclXBBPeglzZzC2ATejT2XsHr5xKXAwXEyBvEShR" +
       "wC2S5xZBbhHFLeLnRkIhyWQxclU+BY/sg9yGqtfcG989/PDB7hoIJmeiFsyJ" +
       "oN2gXV6UQZ0PlArAkCxzOkThstcfOhD59fjdKgq1uat1VWwye2TiqZ1PrAuT" +
       "cHnZRdXgqAnRY1gsi0WxJ5hu1ei2Hvj+59MvTvFS4pXV8Xw9qMTEfO4OOsHl" +
       "OjOgQpbIr11NzybOT/WESS0UCSiMgkIgQ83pCvIoy+uNhRqJutSBwknuZqiF" +
       "V4XC1iTSLp8oncjoaMGlXQUKOjAgoCyvm96fffnsK30bwv5K3OrrbXEmVF4v" +
       "Kvl/u8sYnH99JPb8C5cPPCSdDxDXVmPQg+sAZDl4Ayz2zEePfPXNpaOfh0sB" +
       "I6DdZccsU88BjetLXKAGWFCH0K09O+wMN8ykSccshnH3e+t168/+dLhNOcqC" +
       "k4Kfb/pnAqXzq+8lT17c80uXJBPSsQeVNC+BKQN0lCj3uy7dj3Lknvps5csX" +
       "6GtQIqEseeYkk5WGSM2INH1EeqRXrjcH7tbhstqpuMvJk2X5N/myRq49uNyg" +
       "7IbbG/2QIblfIsjSirxWqYwGXjlX25Et8+jT0zPGtmPrVVq2l5fyQZhUTn7x" +
       "xyeRI99+XKWaNAru3GyxcWb5ZKpBlmXlYIvsyKWkOPTm2+fEp323K5Zr564E" +
       "QcQLT/+wfPtd6Yf/QxFYFVA+SPLNLSc+vv96/bkwqSnmf8WQUY600W8GYOoy" +
       "mIpsNCieNEk3d0kBFoE5OtChV8PTlG9B8h9vOxxcF6tsxeWWQNyEpT3DBR93" +
       "VfhYqspghsHALIB1+sHi6r8/NiTZ3DdPZA7j0g+pmXUMaMvgxd55JmrXzECT" +
       "H89PIdpU+zf7Xv3+pPJocGQJALOD04f+ihyeDvvmumsrRis/jprtpJRXKcP+" +
       "Bb8QPH/igyrggert7QP5AWN1ccJwHMyDNfOJJVls+u701AdvTB0I502yQZCG" +
       "Mc4tRu3KjJUHdxb9jA9ZAU9L3s8t/9rPofJcXlk1l2FkxaGdSTK75vHjblx2" +
       "CrIwxcQI4GHIVi0TZgZmVyyZ3P1H7Rbi4V3wtOe1a/+vUYyvo7gkJCibR4MU" +
       "LmMCfK1TAdEdS3ORT/IoLjEl3YggtePcNKoUU0Ga/QMN1vJlFZ9IaqzXT820" +
       "Llg6s+NL2aKLo3cjzL/JrGX5Et2f9PWOy5KmlLZRdV5H/vFqpViNWJBZaiPF" +
       "tRU8nLYF4UEt/PODZcGdPjAIy/zODwRa1wAQbvc7BY+3yRaG3z0RNeTnSFmn" +
       "ccr7jr+lY9LLz89C6cyqD9CEfnpmeOujV247JutwHXy4Tk7KzxX4+lKDSrH8" +
       "rpmTWoFW/ebe31rONF5XyLmyESYg26rqnX4w4wjZmyffW/ruHcdnLslR42+P" +
       "oyg0FxAAAA==");
}
