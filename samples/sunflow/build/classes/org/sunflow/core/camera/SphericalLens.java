package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class SphericalLens implements CameraLens {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time) { double theta = 2 * Math.PI *
                                       x /
                                       imageWidth +
                                       Math.
                                         PI /
                                       2;
                                     double phi = Math.PI * (imageHeight -
                                                               1 -
                                                               y) /
                                       imageHeight;
                                     return new Ray(0, 0, 0, (float)
                                                               (Math.
                                                                  cos(
                                                                    theta) *
                                                                  Math.
                                                                  sin(
                                                                    phi)),
                                                    (float)
                                                      Math.
                                                      cos(
                                                        phi),
                                                    (float)
                                                      (Math.
                                                         sin(
                                                           theta) *
                                                         Math.
                                                         sin(
                                                           phi)));
    }
    public SphericalLens() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWwURRyfXr9Ly5VCy3ehUNBSuIUYTBD8KGeB1gOaFkgo" +
                                                    "gWO6O9fbdm9n2Z1rj2IVSAyEB2K0IBjtg4EgyleMBI0h4Ukg+IIxGh8E3yQq" +
                                                    "D7ygCSr+Z+budm/vivriJTs3O/Of//f/N/89dx+VOjZqtaixt9+gLERSLDRg" +
                                                    "rAyxvRZxQp2RlV3YdogWNrDjbIG1qLrgUvDhozfjtQFU1oumYtOkDDOdmk43" +
                                                    "cagxRLQICrqr7QZJOAzVRgbwEFaSTDeUiO6w1RE0yXOUoeZIRgUFVFBABUWo" +
                                                    "oLS5VHCohpjJRJifwCZz9qDXUFEElVkqV4+hplwmFrZxIs2mS1gAHCr4+zYw" +
                                                    "ShxO2Wh+1nZpc57Bx1qVsXd21X5SjIK9KKibPVwdFZRgIKQXVSdIoo/YTpum" +
                                                    "Ea0XTTEJ0XqIrWNDHxF696I6R+83MUvaJOskvpi0iC1kup6rVrltdlJl1M6a" +
                                                    "F9OJoWXeSmMG7gdbG1xbpYXr+DoYWKWDYnYMqyRzpGRQNzWG5vlPZG1sfgUI" +
                                                    "4Gh5grA4zYoqMTEsoDoZOwOb/UoPs3WzH0hLaRKkMDRrQqbc1xZWB3E/iTI0" +
                                                    "w0/XJbeAqlI4gh9hqN5PJjhBlGb5ouSJz/1Na47uMzeYAaGzRlSD618Bhxp9" +
                                                    "h7pJjNjEVIk8WL0kchw3XD0cQAiI633EkubKqw9eWtp47YakmV2AZnPfAFFZ" +
                                                    "VD3VN/n2nHDLqmKuRoVFHZ0HP8dykf5d6Z3VKQsqryHLkW+GMpvXur/cvv8j" +
                                                    "8ksAVXWgMpUayQTk0RSVJizdIPZ6YhIbM6J1oEpiamGx34HKYR7RTSJXN8di" +
                                                    "DmEdqMQQS2VUvIOLYsCCu6gc5roZo5m5hVlczFMWQqgcHrQcngokf+KfIUOJ" +
                                                    "0wRRsIpN3aQK5C7BthpXiEqjNrGo0h7erPSBl+MJbA86ipM0YwYdjqpJh9GE" +
                                                    "4tiqQu3+zLKiUpsoKiSajZUeKw6Fo2IjQkwnxLPO+p/lpbj9tcNFRRCaOX5g" +
                                                    "MKCmNlBDI3ZUHUuubX9wIXorkC2UtOcYWgTiQmlxIS4uJMWFcsShoiIhZRoX" +
                                                    "K4MPoRsEEAB4rG7p2dm5+/CCYsg6a7gE/M5JF4DJaV3aVRp2kaJD4KEK6Trj" +
                                                    "gx2HQr+feVGmqzIxrBc8ja6dGD6w7fXlARTIxWduGyxV8eNdHFWz6Nnsr8tC" +
                                                    "fIOH7j28eHyUuhWaA/hp4Mg/yQt/gT8KNlWJBlDqsl8yH1+OXh1tDqASQBNA" +
                                                    "UIYh4wGcGv0ycgBgdQZMuS2lYHCM2gls8K0MAlaxuE2H3RWRHpP5UCczhQfQ" +
                                                    "p6DA4XWfXzt5+d3WVQEvZAc9l2APYRIAprjx32ITAus/nOh6+9j9QztE8IFi" +
                                                    "YSEBzXwMAxxANMBjb9zY8/3dO6e+CbgJw+BeTPYZupoCHotdKQAWBgAWD2vz" +
                                                    "VjNBNT2m4z6D8Lz7I7hoxeVfj9bKQBmwkonz0n9m4K7PXIv239r1W6NgU6Ty" +
                                                    "y8q13CWTDpjqcm6zbbyX65E68PXck9fx+4ClgF+OPkIEJCFhGRKuD4mItIhx" +
                                                    "mW9vOR/mW3l7KbEyI/0mXprE2MyHp6Tf+PRpL2WRmNczNDuvsMOisHk5cyfP" +
                                                    "neiOEvfrqYNj49rm0ytkadbl4n47tDXnv/3zq9CJH28WgJRKRq1lBhkihkev" +
                                                    "ABeZAwkbxfXtFsaRsx9fYbdbn5Mil0yMBv6D1w/+PGvLC/Hd/wEI5vmM97M8" +
                                                    "u/HczfWL1bcCqDiLAXkdSe6h1V43gFCbQAtlcofylSoR6kahwBRwx1Qe1Jnw" +
                                                    "VKbvK/HPd6dafJwmK5YPz/hyJ5D2ZzrOjXlxFqYSaHh4cmbIGrxkPfK/ratD" +
                                                    "iHn5CdnZyYc2KM+kpcEdDlFseUL7besJ6AiG0i2LMlp3d/C9e+dlRP39jY+Y" +
                                                    "HB478jh0dCzgaQIX5vVh3jOyERRa1kjHPoZfETx/8YebwBdkI1AXTncj87Pt" +
                                                    "iGXxOmh6klpCxLqfLo5+8eHooUDaJasYKu+j1CDYzK9asfB8Ns5BvlgPT006" +
                                                    "zjX/Os7lgmO5eI/woUsy72YcoykWaNdbaLsYmmo+3Vlos0yjALQiKXeLQWiw" +
                                                    "/QkpoPFhG5zsJ6wb781k1LS8xIPNAjDGUE1OL8FhdEbeZ4xsvdUL48GK6eNb" +
                                                    "vxO3Y7Y9roQeNZY0DE99eWutzLJJTBe6VspLzxJ/gwxNn6C9AWvkROg7IOmh" +
                                                    "/6310zNUwv+8ZHsYmuQhg2xIz7xEDMIARHyatDIuqxW3B/82CclGPIVyQN7K" +
                                                    "hXzvbcprTXwiZhArKT8So+rF8c5N+x48e1rAXyl8XI6MiE8K+EKSPUIW9Zom" +
                                                    "5JbhVbah5dHkS5WLMqme0z34dJtX+JJtT1hMXIsjn03/dM2Z8Tvilv8bzcBH" +
                                                    "srsPAAA=");
}
