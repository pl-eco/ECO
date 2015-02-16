package org.sunflow.image;
public abstract class SpectralCurve {
    public abstract float sample(float lambda);
    private static final int WAVELENGTH_MIN = 360;
    private static final int WAVELENGTH_MAX = 830;
    private static final double[] CIE_xbar = { 1.299E-4, 2.321E-4, 4.149E-4,
    7.416E-4,
    0.001368,
    0.002236,
    0.004243,
    0.00765,
    0.01431,
    0.02319,
    0.04351,
    0.07763,
    0.13438,
    0.21477,
    0.2839,
    0.3285,
    0.34828,
    0.34806,
    0.3362,
    0.3187,
    0.2908,
    0.2511,
    0.19536,
    0.1421,
    0.09564,
    0.05795001,
    0.03201,
    0.0147,
    0.0049,
    0.0024,
    0.0093,
    0.0291,
    0.06327,
    0.1096,
    0.1655,
    0.2257499,
    0.2904,
    0.3597,
    0.4334499,
    0.5120501,
    0.5945,
    0.6784,
    0.7621,
    0.8425,
    0.9163,
    0.9786,
    1.0263,
    1.0567,
    1.0622,
    1.0456,
    1.0026,
    0.9384,
    0.8544499,
    0.7514,
    0.6424,
    0.5419,
    0.4479,
    0.3608,
    0.2835,
    0.2187,
    0.1649,
    0.1212,
    0.0874,
    0.0636,
    0.04677,
    0.0329,
    0.0227,
    0.01584,
    0.01135916,
    0.008110916,
    0.005790346,
    0.004106457,
    0.002899327,
    0.00204919,
    0.001439971,
    9.999493E-4,
    6.900786E-4,
    4.760213E-4,
    3.323011E-4,
    2.348261E-4,
    1.661505E-4,
    1.17413E-4,
    8.307527E-5,
    5.870652E-5,
    4.150994E-5,
    2.935326E-5,
    2.067383E-5,
    1.455977E-5,
    1.025398E-5,
    7.221456E-6,
    5.085868E-6,
    3.581652E-6,
    2.522525E-6,
    1.776509E-6,
    1.251141E-6 };
    private static final double[] CIE_ybar = { 3.917E-6, 6.965E-6, 1.239E-5,
    2.202E-5,
    3.9E-5,
    6.4E-5,
    1.2E-4,
    2.17E-4,
    3.96E-4,
    6.4E-4,
    0.00121,
    0.00218,
    0.004,
    0.0073,
    0.0116,
    0.01684,
    0.023,
    0.0298,
    0.038,
    0.048,
    0.06,
    0.0739,
    0.09098,
    0.1126,
    0.13902,
    0.1693,
    0.20802,
    0.2586,
    0.323,
    0.4073,
    0.503,
    0.6082,
    0.71,
    0.7932,
    0.862,
    0.9148501,
    0.954,
    0.9803,
    0.9949501,
    1.0,
    0.995,
    0.9786,
    0.952,
    0.9154,
    0.87,
    0.8163,
    0.757,
    0.6949,
    0.631,
    0.5668,
    0.503,
    0.4412,
    0.381,
    0.321,
    0.265,
    0.217,
    0.175,
    0.1382,
    0.107,
    0.0816,
    0.061,
    0.04458,
    0.032,
    0.0232,
    0.017,
    0.01192,
    0.00821,
    0.005723,
    0.004102,
    0.002929,
    0.002091,
    0.001484,
    0.001047,
    7.4E-4,
    5.2E-4,
    3.611E-4,
    2.492E-4,
    1.719E-4,
    1.2E-4,
    8.48E-5,
    6.0E-5,
    4.24E-5,
    3.0E-5,
    2.12E-5,
    1.499E-5,
    1.06E-5,
    7.4657E-6,
    5.2578E-6,
    3.7029E-6,
    2.6078E-6,
    1.8366E-6,
    1.2934E-6,
    9.1093E-7,
    6.4153E-7,
    4.5181E-7 };
    private static final double[] CIE_zbar = { 6.061E-4, 0.001086, 0.001946,
    0.003486,
    0.006450001,
    0.01054999,
    0.02005001,
    0.03621,
    0.06785001,
    0.1102,
    0.2074,
    0.3713,
    0.6456,
    1.0390501,
    1.3856,
    1.62296,
    1.74706,
    1.7826,
    1.77211,
    1.7441,
    1.6692,
    1.5281,
    1.28764,
    1.0419,
    0.8129501,
    0.6162,
    0.46518,
    0.3533,
    0.272,
    0.2123,
    0.1582,
    0.1117,
    0.07824999,
    0.05725001,
    0.04216,
    0.02984,
    0.0203,
    0.0134,
    0.008749999,
    0.005749999,
    0.0039,
    0.002749999,
    0.0021,
    0.0018,
    0.001650001,
    0.0014,
    0.0011,
    0.001,
    8.0E-4,
    6.0E-4,
    3.4E-4,
    2.4E-4,
    1.9E-4,
    1.0E-4,
    4.999999E-5,
    3.0E-5,
    2.0E-5,
    1.0E-5,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0,
    0.0 };
    private static final int WAVELENGTH_STEP = (WAVELENGTH_MAX - WAVELENGTH_MIN) /
      (CIE_xbar.
         length -
         1);
    static { if (WAVELENGTH_STEP * (CIE_xbar.length - 1) != WAVELENGTH_MAX -
                   WAVELENGTH_MIN) { String err = String.format(("Internal error - spectrum static data is inconsistent!\n  * " +
                                                                 "min = %d\n  * max = %d\n  * step = %d\n  * num = %d"),
                                                                WAVELENGTH_MIN,
                                                                WAVELENGTH_MAX,
                                                                WAVELENGTH_STEP,
                                                                CIE_xbar.
                                                                  length);
                                     throw new RuntimeException(
                                       err); } }
    public final XYZColor toXYZ() { float X = 0;
                                    float Y = 0;
                                    float Z = 0;
                                    for (int i = 0,
                                           w =
                                             WAVELENGTH_MIN;
                                         i <
                                           CIE_xbar.
                                             length;
                                         i++,
                                           w +=
                                             WAVELENGTH_STEP) {
                                        float s =
                                          sample(
                                            w);
                                        X +=
                                          s *
                                            CIE_xbar[i];
                                        Y +=
                                          s *
                                            CIE_ybar[i];
                                        Z +=
                                          s *
                                            CIE_zbar[i];
                                    }
                                    return new XYZColor(
                                      X,
                                      Y,
                                      Z).
                                      mul(
                                        WAVELENGTH_STEP);
    }
    public SpectralCurve() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1168792010000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAALVYe2wcxRkfn+3zI47PdkgIbvyI7dA6hlugogKMAuZqJ04v" +
       "jhU7AY7Wx9ze3HnjfWV3zr4YXEgk6ohKUVUcGlpqIRQeAZNEVSNaIaT80wKi" +
       "qgSqWvWPEso/RU0jNX8UUGlLv5nZvd3be4RU6kk7NzfzzXzv3/ftrV1G9baF" +
       "hkxDPZxVDRoleRo9qN4epYdNYkd3x2+fxJZN0jEV2/Y0rCXlvnORTz7/wWxb" +
       "CIUTaAPWdYNiqhi6vY/YhjpP0nEU8VZHVaLZFLXFD+J5LOWookpxxabDcbTO" +
       "d5SigbgrggQiSCCCxEWQRjwqOLSe6Dktxk5gndqH0HdRTRyFTZmJR9HW4ktM" +
       "bGHNuWaSawA3NLLfB0Apfjhvod6C7kLnEoVPDEkrP5pp+1ktiiRQRNGnmDgy" +
       "CEGBSQK1aERLEcseSadJOoHadULSU8RSsKoscrkTqMNWsjqmOYsUjMQWcyax" +
       "OE/Pci0y083KydSwCuplFKKm3V/1GRVnQddNnq5CwzG2Dgo2KyCYlcEycY/U" +
       "zSl6mqKe4ImCjgPfAgI42qAROmsUWNXpGBZQh/CdivWsNEUtRc8Cab2RAy4U" +
       "dVa8lNnaxPIczpIkRZuDdJNiC6iauCHYEYo2Bsn4TeClzoCXfP65PHH38Uf0" +
       "XXqIy5wmssrkb4RD3YFD+0iGWESXiTjYsj3+NN705rEQQkC8MUAsaF5/9Mq9" +
       "N3VfeFvQfKUMzd7UQSLTpHwq1freltjgnbVMjEbTsBXm/CLNefhPOjvDeRMy" +
       "b1PhRrYZdTcv7Pv1g4+/Qi6FUPM4CsuGmtMgjtplQzMVlVg7iU4sTEl6HDUR" +
       "PR3j++OoAeZxRSdidW8mYxM6jupUvhQ2+G8wUQauYCZqgLmiZwx3bmI6y+d5" +
       "EyHUBg/aCE8YiQ//pmhG2m9DuEtYxrqiGxIEL8GWPCsR2UimwLqzGrbmbEnO" +
       "2dTQJDunZ1RjQbItWTKsbOG3ooH3pSkTbGdhNZaz5kmUxZn5f+eQZzq2LdTU" +
       "gPm3BJNfhbzZZahpYiXlldx9o1fOJN8NFZLBsQ7kEjCKOoyinFG0iBGqqeH3" +
       "X8cYCteCY+YgxQH8WganvrP74WN9tRBT5kIdWJWR9oF2jhSjshHzcGCco50M" +
       "wbj5+YeWo5+9dI8IRqkyaJc9jS6cXDhy4LFbQihUjL5MK1hqZscnGWYWsHEg" +
       "mHXl7o0sf/zJ2aeXDC//iuDcgYXSkyyt+4L2twyZpAEoveu39+LzyTeXBkKo" +
       "DrAC8JFiiGeAnu4gj6L0HnahkulSDwpnDEvDKtty8a2ZzlrGgrfCA6OVDR0i" +
       "RpgDAwJylB375YVnzv946M6QH5AjvhI3RahI73bP/9MWIbD+p5OTT524vPwQ" +
       "dz5Q9JdjMMDGGCQ7eAMs9sTbh/548YNTvwt5AUOh6uVSqiLn4Y4bPS4ABSrE" +
       "IXPrwH5dM9JKRsEplbC4+1dk263n/3a8TThKhRXXzzdd/QJv/Yb70OPvznza" +
       "za+pkVkp8jT3yIQBNng3j1gWPszkyB95v+uZt/BPASkBnWxlkXDAQVwzxE0f" +
       "5R4Z5OPNgb1b2NBrluzl+cpm/isMrAcr58cYq6i+vPrnXjV19KPPuEYlmVGm" +
       "kATOJ6S1ZztjOy7x816IstM9+VKQge7DO3vbK9o/Qn3hX4VQQwK1yU5rcwCr" +
       "ORYtCSjnttvvQPtTtF9cmkUdGi6k4JZgevjYBpPDAzeYM2o2bxb5wGnawaY3" +
       "MCvf5lYG95vtbjDZeF2eQ54IeSZSdBw6kSyxOj567tSnR5bvCDF/188z0cEq" +
       "bR7dRI51UN9bO9G1buXD7/MohxDntegOzn4rHwfY8FXu31qKGkxLmYcaCJlg" +
       "846Mgk6KjtU8Ra33jxwYjY9O7JzeldwzPlE9GCYtRYOiO+90BdJSx8W5Zz9+" +
       "TYBs0PMBYnJs5ckvosdXQr4+q7+k1fGfEb0WN+16Ydov4FMDz3/Yw0zKFkSt" +
       "7Yg5Bb+3UPFNk2X81mpicRZjfzm79MbLS8tCjY7iNmMUuujXfv/v30RPfvhO" +
       "mepWCy0kB0CRZF8vDYF2JwTay4QAm9wFLtrBJnsq+I9N72HDvWwYCfps5AG2" +
       "PFZZiG54tjtCdJQIgfhk6kvzboyNjybzKej8LbStcqRwBBOOX32x/7ePrfb/" +
       "GayXQI2KDck1YmXLNKq+M39fu3jp/fVdZ3glq0thW6RZsMMvbeCL+nIeOi2m" +
       "QLsYG3aK+ThkQtqAokAK0FjjdBfcgKZrl2R5u4TY9GuuScIq0bOiH+RuSDgc" +
       "2bUhQc9/b6QOxvNMjqmGTrArAuyJBkgxooVXItjMlwhooa6i9mcPV9hDySdP" +
       "v/o6fW/oLhHN2yu7KHjwraN/7ZzeMfvwNTQ9PQEPBq88vWftnZ03yj8ModoC" +
       "2Ja8WxUfGi6G2GaLwMugPl0EtN0m/0qUd04Nd06+Slk8VGWPL2oAkDLzj3An" +
       "2LynfNkf1UzKC/XiL67/+d0vrX7AETmPqmdjzMnGoQrZuHBt2XgYspELWp3r" +
       "tx2u36zA9dFr47p4da4KPDMO15kKXI98aa4RH+pNTY9O+mAvXy1NQV6cgo4a" +
       "y9Tf9yBWHLoqvSvzwnDq6Mpqeu8Lt4ac2NhNURM1zJtVMk9U31UivZWC8p3s" +
       "+l54Wh3lW4PKc7Gvhi3HgjHsx6gAnEGPYmDK732qSnSfZMNx1gdgzRSvtiul" +
       "7WE5dXrg0Rx1tP9JnZFqKflclb3n2fAT0JEaDzyYcOGys/S9EnYhOQ2rTMNL" +
       "0fqit06W05tL/s4Sf8HIZ1Yjjdev7v+DqD7u3yRNcdSYyamqvwH0zcOmRTIK" +
       "F7hJtIMCpU5T1F4iKijDv7mgLwvCNYrW+QihbXNmfqKz0HIAEZueM11T+PpD" +
       "0drmUVGgm8Gw7y+qCvzPPxfBc+Lvv6R8dnX3xCNXvvECLweAhnhxkd3SCDVW" +
       "vB8WqsDWire5d4V3DX7eeq5pm5tIRW+OftnYfP6/umJRRWoVAAA=");
}
