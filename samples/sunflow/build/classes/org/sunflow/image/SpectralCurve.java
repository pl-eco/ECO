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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/WzLFjeRSr4c/bKx/dz/AX1flQtUFQAA");
}
