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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
