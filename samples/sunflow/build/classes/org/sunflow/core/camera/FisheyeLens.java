package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class FisheyeLens implements CameraLens {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time) { float cx = 2.0F * x / imageWidth -
                                       1.0F;
                                     float cy = 2.0F * y / imageHeight -
                                       1.0F;
                                     float r2 = cx * cx + cy * cy;
                                     if (r2 > 1) return null;
                                     return new Ray(0, 0, 0, cx, cy,
                                                    (float)
                                                      -Math.
                                                      sqrt(
                                                        1 -
                                                          r2)); }
    public FisheyeLens() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXW2wUVRg+u72Xlu2FlnIrbSloKexADCZYvNTaQusCmxYw" +
                                                    "lEA5nTm7O+3szDBztt0Wq0BiIDwQowXBaB8MBFFuMRI0hqRPAsEXjNH4IPgm" +
                                                    "UXngBU1Q8T/n7GV2dlv1wU3m7Jlz/vPf/+/8c/4+KrAt1Goa2lhYM6ifxKl/" +
                                                    "SFvnp2Mmsf09gXVBbNlE6dCwbW+DtQG56bLv4aM3IxVeVNiPqrGuGxRT1dDt" +
                                                    "XmIb2ghRAsiXXu3USNSmqCIwhEewFKOqJgVUm7YF0BzHUYqaA0kVJFBBAhUk" +
                                                    "roLUnqaCQ+VEj0U72AmsU3sfeg15AqjQlJl6FDVmMjGxhaMJNkFuAXAoZu87" +
                                                    "wCh+OG6hhpTtwuYsg4+3SpPv7Kn4JA/5+pFP1fuYOjIoQUFIPyqLkuggsex2" +
                                                    "RSFKP6rUCVH6iKViTR3nevejKlsN65jGLJJyEluMmcTiMtOeK5OZbVZMpoaV" +
                                                    "Mi+kEk1JvhWENBwGW2vTtgoLu9g6GFiqgmJWCMskeSR/WNUVipa6T6RsbH4Z" +
                                                    "COBoUZTQiJESla9jWEBVInYa1sNSH7VUPQykBUYMpFC0cEamzNcmlodxmAxQ" +
                                                    "VOemC4otoCrhjmBHKKpxk3FOEKWFrig54nN/y4Zj+/VNupfrrBBZY/oXw6F6" +
                                                    "16FeEiIW0WUiDpatDJzAtdeOeBEC4hoXsaC5+uqDF1bVT98QNIty0GwdHCIy" +
                                                    "HZBPD869vbijZX0eU6PYNGyVBT/Dcp7+wcROW9yEyqtNcWSb/uTmdO+XOw98" +
                                                    "RH7xotJuVCgbWiwKeVQpG1FT1Yi1kejEwpQo3aiE6EoH3+9GRTAPqDoRq1tD" +
                                                    "IZvQbpSv8aVCg7+Di0LAgrmoCOaqHjKScxPTCJ/HTYRQETzID08xEj/+T9Er" +
                                                    "UsSIEgnLWFd1Q4LcJdiSIxKRDcnGUVODqNkxPaQZo5JtyZJhhVPvsmERSYas" +
                                                    "srDUpdoRMkYCRLf9LMHM/491nFlVMerxgMMXu8tdg0rZZGgKsQbkydiLnQ8u" +
                                                    "DtzyptI/4Q+KloEwf0KYnwnzC2F+hzDk8XAZ85hQEVAIxzAUNkBeWUvf7p69" +
                                                    "R5ryIJPM0XzwJSNtAtsSmnTKRke6+rs5xsmQgnUf7Drs//3s8yIFpZmhOudp" +
                                                    "NH1y9OCO19d4kTcTc5llsFTKjgcZUqYQsdlda7n4+g7fe3jpxISRrroMEE+A" +
                                                    "QfZJVsxN7hhYhkwUgMc0+5UN+MrAtYlmL8oHhABUpBiyGACn3i0jo6jbkgDJ" +
                                                    "bCkAg0OGFcUa20qiWimNWMZoeoUnx1w2VIk8YQF0Kcixtevz6VNX3m1d73XC" +
                                                    "sM9xsfURKoq6Mh3/bRYhsP7DyeDbx+8f3sWDDxTLcgloZmMHlDhEAzz2xo19" +
                                                    "39+9c/obbzphKNx1sUFNlePAY0VaCgCABiDEwtq8XY8aihpS8aBGWN794Vu+" +
                                                    "9sqvxypEoDRYScZ51T8zSK8veBEduLXnt3rOxiOzCyhteZpMOKA6zbndsvAY" +
                                                    "0yN+8Oslp67j9wEfAZNsdZxwmEHcMsRd7+cRaeHjatfeGjY0mFl7cb5Sl3jj" +
                                                    "L418bGbDE8JvbPqkk9LD5zUULcoq6w5e1qycmZOXzHTv8Dvz9KHJKWXrmbWi" +
                                                    "NKsysbwTWpUL3/75lf/kjzdzAEoJNczVGhkhmkMvLxOZAQmb+ZWcLoyj5z6+" +
                                                    "Sm+3PiNErpwZDdwHrx/6eeG25yJ7/wMQLHUZ72Z5bvP5mxtXyG95UV4KA7K6" +
                                                    "jMxDbU43gFCLQFukM4eylVIe6nquQCW4o5oFdQE8JYk7iP+z3WqTjfNExbLh" +
                                                    "KVfueBP+TMS5PivO3FQCTQxLziRZrZOsT/y3B7u5mJdmyc4eNrRDecZMBe5l" +
                                                    "iGLLLC21pUbhlh9JtCHSRNXd4ffuXRARdfcsLmJyZPLoY/+xSa+jsVuW1Vs5" +
                                                    "z4jmjmtZLhz7GH4eeP5iDzOBLYjLvaoj0WE0pFoM02R10DibWlxE10+XJr74" +
                                                    "cOKwN+GS9RQVDRqGRrCeXbV84dlUnH1ssQae8kScy/91nIs4xyL+HmBDUDDv" +
                                                    "pQyjDczRrj/Xdh40ymy6O9dmoWIA0PKk3MsHrsHOWVJAYcMOOBkmtBePJTNq" +
                                                    "XlbiwWYOGKNojqOTYCBal/VhIppp+eKUr3j+1Pbv+N2YanhLoOsMxTTNUV3O" +
                                                    "Sis0LRJSuaYl4soz+d8wRfNnaG3AFjHh2g4JeuhoK9z0FOWzPyfZPrDHQQa5" +
                                                    "kJg5iSgEAYjYNGYmHVbB7w72teEXrXUcZUC8mQn4zruUVRr/6EviVUx89g3I" +
                                                    "l6Z6tux/8PQZDn4F8Lk4Ps4/EuCbR3QIKcxrnJFbklfhppZHcy+XLE8mekbv" +
                                                    "4NJtae4rtjNqUn4pjn82/9MNZ6fu8Dv+b6bfdRuNDwAA");
}
