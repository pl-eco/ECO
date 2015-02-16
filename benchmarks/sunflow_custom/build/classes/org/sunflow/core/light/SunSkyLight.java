package org.sunflow.core.light;
import org.sunflow.SunflowAPI;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.LightSample;
import org.sunflow.core.LightSource;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.ChromaticitySpectrum;
import org.sunflow.image.Color;
import org.sunflow.image.ConstantSpectralCurve;
import org.sunflow.image.IrregularSpectralCurve;
import org.sunflow.image.RGBSpace;
import org.sunflow.image.RegularSpectralCurve;
import org.sunflow.image.SpectralCurve;
import org.sunflow.image.XYZColor;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.MathUtils;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public class SunSkyLight implements LightSource, PrimitiveList, Shader {
    private int numSkySamples;
    private OrthoNormalBasis basis;
    private Vector3 sunDirWorld;
    private float turbidity;
    private Vector3 sunDir;
    private SpectralCurve sunSpectralRadiance;
    private Color sunColor;
    private float sunTheta;
    private double zenithY;
    private double zenithx;
    private double zenithy;
    private final double[] perezY = new double[5];
    private final double[] perezx = new double[5];
    private final double[] perezy = new double[5];
    private float jacobian;
    private float[] colHistogram;
    private float[][] imageHistogram;
    private static final float[] solAmplitudes = { 165.5F, 162.3F, 211.2F,
    258.8F,
    258.2F,
    242.3F,
    267.6F,
    296.6F,
    305.4F,
    300.6F,
    306.6F,
    288.3F,
    287.1F,
    278.2F,
    271.0F,
    272.3F,
    263.6F,
    255.0F,
    250.6F,
    253.1F,
    253.5F,
    251.3F,
    246.3F,
    241.7F,
    236.8F,
    232.1F,
    228.2F,
    223.4F,
    219.7F,
    215.3F,
    211.0F,
    207.3F,
    202.4F,
    198.7F,
    194.3F,
    190.7F,
    186.3F,
    182.6F };
    private static final RegularSpectralCurve solCurve = new RegularSpectralCurve(
      solAmplitudes,
      380,
      750);
    private static final float[] k_oWavelengths = { 300, 305, 310,
    315,
    320,
    325,
    330,
    335,
    340,
    345,
    350,
    355,
    445,
    450,
    455,
    460,
    465,
    470,
    475,
    480,
    485,
    490,
    495,
    500,
    505,
    510,
    515,
    520,
    525,
    530,
    535,
    540,
    545,
    550,
    555,
    560,
    565,
    570,
    575,
    580,
    585,
    590,
    595,
    600,
    605,
    610,
    620,
    630,
    640,
    650,
    660,
    670,
    680,
    690,
    700,
    710,
    720,
    730,
    740,
    750,
    760,
    770,
    780,
    790 };
    private static final float[] k_oAmplitudes = { 10.0F, 4.8F, 2.7F,
    1.35F,
    0.8F,
    0.38F,
    0.16F,
    0.075F,
    0.04F,
    0.019F,
    0.007F,
    0.0F,
    0.003F,
    0.003F,
    0.004F,
    0.006F,
    0.008F,
    0.009F,
    0.012F,
    0.014F,
    0.017F,
    0.021F,
    0.025F,
    0.03F,
    0.035F,
    0.04F,
    0.045F,
    0.048F,
    0.057F,
    0.063F,
    0.07F,
    0.075F,
    0.08F,
    0.085F,
    0.095F,
    0.103F,
    0.11F,
    0.12F,
    0.122F,
    0.12F,
    0.118F,
    0.115F,
    0.12F,
    0.125F,
    0.13F,
    0.12F,
    0.105F,
    0.09F,
    0.079F,
    0.067F,
    0.057F,
    0.048F,
    0.036F,
    0.028F,
    0.023F,
    0.018F,
    0.014F,
    0.011F,
    0.01F,
    0.009F,
    0.007F,
    0.004F,
    0.0F,
    0.0F };
    private static final float[] k_gWavelengths = { 759, 760, 770,
    771 };
    private static final float[] k_gAmplitudes = { 0, 3.0F, 0.21F,
    0 };
    private static final float[] k_waWavelengths = { 689, 690, 700,
    710,
    720,
    730,
    740,
    750,
    760,
    770,
    780,
    790,
    800 };
    private static final float[] k_waAmplitudes = { 0.0F, 0.016F,
    0.024F,
    0.0125F,
    1.0F,
    0.87F,
    0.061F,
    0.001F,
    1.0E-5F,
    1.0E-5F,
    6.0E-4F,
    0.0175F,
    0.036F };
    private static final IrregularSpectralCurve k_oCurve = new IrregularSpectralCurve(
      k_oWavelengths,
      k_oAmplitudes);
    private static final IrregularSpectralCurve k_gCurve =
      new IrregularSpectralCurve(
      k_gWavelengths,
      k_gAmplitudes);
    private static final IrregularSpectralCurve k_waCurve =
      new IrregularSpectralCurve(
      k_waWavelengths,
      k_waAmplitudes);
    public SunSkyLight() { super();
                           numSkySamples = 64;
                           sunDirWorld = new Vector3(1, 1,
                                                     1);
                           turbidity = 6;
                           basis = OrthoNormalBasis.makeFromWV(
                                                      new Vector3(
                                                        0,
                                                        0,
                                                        1),
                                                      new Vector3(
                                                        0,
                                                        1,
                                                        0));
                           initSunSky(); }
    private SpectralCurve computeAttenuatedSunlight(float theta,
                                                    float turbidity) {
        float[] data =
          new float[91];
        final double alpha =
          1.3;
        final double lozone =
          0.35;
        final double w =
          2.0;
        double beta =
          0.0460836582205 *
          turbidity -
          0.04586025928522;
        double m =
          1.0 /
          (Math.
             cos(
               theta) +
             9.4E-4 *
             Math.
             pow(
               1.6386 -
                 theta,
               -1.253));
        for (int i =
               0,
               lambda =
                 350;
             lambda <=
               800;
             i++,
               lambda +=
                 5) {
            double tauR =
              Math.
              exp(
                -m *
                  0.008735 *
                  Math.
                  pow(
                    (double)
                      lambda /
                      1000.0,
                    -4.08));
            double tauA =
              Math.
              exp(
                -m *
                  beta *
                  Math.
                  pow(
                    (double)
                      lambda /
                      1000.0,
                    -alpha));
            double tauO =
              Math.
              exp(
                -m *
                  k_oCurve.
                  sample(
                    lambda) *
                  lozone);
            double tauG =
              Math.
              exp(
                -1.41 *
                  k_gCurve.
                  sample(
                    lambda) *
                  m /
                  Math.
                  pow(
                    1.0 +
                      118.93 *
                      k_gCurve.
                      sample(
                        lambda) *
                      m,
                    0.45));
            double tauWA =
              Math.
              exp(
                -0.2385 *
                  k_waCurve.
                  sample(
                    lambda) *
                  w *
                  m /
                  Math.
                  pow(
                    1.0 +
                      20.07 *
                      k_waCurve.
                      sample(
                        lambda) *
                      w *
                      m,
                    0.45));
            double amp =
              solCurve.
              sample(
                lambda) *
              tauR *
              tauA *
              tauO *
              tauG *
              tauWA;
            data[i] =
              (float)
                amp;
        }
        return new RegularSpectralCurve(
          data,
          350,
          800);
    }
    private double perezFunction(final double[] lam, double theta,
                                 double gamma,
                                 double lvz) { double den =
                                                 (1.0 +
                                                    lam[0] *
                                                    Math.
                                                    exp(
                                                      lam[1])) *
                                                 (1.0 +
                                                    lam[2] *
                                                    Math.
                                                    exp(
                                                      lam[3] *
                                                        sunTheta) +
                                                    lam[4] *
                                                    Math.
                                                    cos(
                                                      sunTheta) *
                                                    Math.
                                                    cos(
                                                      sunTheta));
                                               double num =
                                                 (1.0 +
                                                    lam[0] *
                                                    Math.
                                                    exp(
                                                      lam[1] /
                                                        Math.
                                                        cos(
                                                          theta))) *
                                                 (1.0 +
                                                    lam[2] *
                                                    Math.
                                                    exp(
                                                      lam[3] *
                                                        gamma) +
                                                    lam[4] *
                                                    Math.
                                                    cos(
                                                      gamma) *
                                                    Math.
                                                    cos(
                                                      gamma));
                                               return lvz *
                                                 num /
                                                 den; }
    private void initSunSky() { sunDirWorld.normalize();
                                sunDir = basis.untransform(
                                                 sunDirWorld,
                                                 new Vector3(
                                                   ));
                                sunDir.normalize();
                                sunTheta = (float) Math.acos(
                                                          MathUtils.
                                                            clamp(
                                                              sunDir.
                                                                z,
                                                              -1,
                                                              1));
                                if (sunDir.z > 0) { sunSpectralRadiance =
                                                      computeAttenuatedSunlight(
                                                        sunTheta,
                                                        turbidity);
                                                    sunColor =
                                                      RGBSpace.
                                                        SRGB.
                                                        convertXYZtoRGB(
                                                          sunSpectralRadiance.
                                                            toXYZ(
                                                              ).
                                                            mul(
                                                              1.0E-4F)).
                                                        constrainRGB(
                                                          );
                                }
                                else {
                                    sunSpectralRadiance =
                                      new ConstantSpectralCurve(
                                        0);
                                }
                                float theta2 = sunTheta *
                                  sunTheta;
                                float theta3 = sunTheta *
                                  theta2;
                                float T = turbidity;
                                float T2 = turbidity * turbidity;
                                double chi = (4.0 / 9.0 -
                                                T /
                                                120.0) * (Math.
                                                            PI -
                                                            2.0 *
                                                            sunTheta);
                                zenithY = (4.0453 * T - 4.971) *
                                            Math.
                                            tan(
                                              chi) -
                                            0.2155 *
                                            T +
                                            2.4192;
                                zenithY *= 1000;
                                zenithx = (0.00165 * theta3 -
                                             0.00374 *
                                             theta2 +
                                             0.00208 *
                                             sunTheta +
                                             0) * T2 + (-0.02902 *
                                                          theta3 +
                                                          0.06377 *
                                                          theta2 -
                                                          0.03202 *
                                                          sunTheta +
                                                          0.00394) *
                                            T +
                                            (0.11693 *
                                               theta3 -
                                               0.21196 *
                                               theta2 +
                                               0.06052 *
                                               sunTheta +
                                               0.25885);
                                zenithy = (0.00275 * theta3 -
                                             0.0061 *
                                             theta2 +
                                             0.00316 *
                                             sunTheta +
                                             0) * T2 + (-0.04212 *
                                                          theta3 +
                                                          0.0897 *
                                                          theta2 -
                                                          0.04153 *
                                                          sunTheta +
                                                          0.00515) *
                                            T +
                                            (0.15346 *
                                               theta3 -
                                               0.26756 *
                                               theta2 +
                                               0.06669 *
                                               sunTheta +
                                               0.26688);
                                perezY[0] = 0.17872 * T -
                                              1.46303;
                                perezY[1] = -0.3554 * T +
                                              0.42749;
                                perezY[2] = -0.02266 * T +
                                              5.32505;
                                perezY[3] = 0.12064 * T -
                                              2.57705;
                                perezY[4] = -0.06696 * T +
                                              0.37027;
                                perezx[0] = -0.01925 * T -
                                              0.25922;
                                perezx[1] = -0.06651 * T +
                                              8.1E-4;
                                perezx[2] = -4.1E-4 * T +
                                              0.21247;
                                perezx[3] = -0.06409 * T -
                                              0.89887;
                                perezx[4] = -0.00325 * T +
                                              0.04517;
                                perezy[0] = -0.01669 * T -
                                              0.26078;
                                perezy[1] = -0.09495 * T +
                                              0.00921;
                                perezy[2] = -0.00792 * T +
                                              0.21023;
                                perezy[3] = -0.04405 * T -
                                              1.65369;
                                perezy[4] = -0.01092 * T +
                                              0.05291;
                                final int w = 32;
                                final int h = 32;
                                imageHistogram = (new float[w][h]);
                                colHistogram = (new float[w]);
                                float du = 1.0F / w;
                                float dv = 1.0F / h;
                                for (int x = 0; x < w; x++) {
                                    for (int y =
                                           0;
                                         y <
                                           h;
                                         y++) {
                                        float u =
                                          (x +
                                             0.5F) *
                                          du;
                                        float v =
                                          (y +
                                             0.5F) *
                                          dv;
                                        Color c =
                                          getSkyRGB(
                                            getDirection(
                                              u,
                                              v));
                                        imageHistogram[x][y] =
                                          c.
                                            getLuminance(
                                              ) *
                                            (float)
                                              Math.
                                              sin(
                                                Math.
                                                  PI *
                                                  v);
                                        if (y >
                                              0)
                                            imageHistogram[x][y] +=
                                              imageHistogram[x][y -
                                                                  1];
                                    }
                                    colHistogram[x] =
                                      imageHistogram[x][h -
                                                          1];
                                    if (x >
                                          0)
                                        colHistogram[x] +=
                                          colHistogram[x -
                                                         1];
                                    for (int y =
                                           0;
                                         y <
                                           h;
                                         y++)
                                        imageHistogram[x][y] /=
                                          imageHistogram[x][h -
                                                              1];
                                }
                                for (int x = 0; x < w; x++)
                                    colHistogram[x] /=
                                      colHistogram[w -
                                                     1];
                                jacobian = (float) (2 * Math.
                                                          PI *
                                                      Math.
                                                        PI) /
                                             (w *
                                                h); }
    public boolean update(ParameterList pl, SunflowAPI api) {
        Vector3 up =
          pl.
          getVector(
            "up",
            null);
        Vector3 east =
          pl.
          getVector(
            "east",
            null);
        if (up !=
              null &&
              east !=
              null)
            basis =
              OrthoNormalBasis.
                makeFromWV(
                  up,
                  east);
        else
            if (up !=
                  null)
                basis =
                  OrthoNormalBasis.
                    makeFromW(
                      up);
        numSkySamples =
          pl.
            getInt(
              "samples",
              numSkySamples);
        sunDirWorld =
          pl.
            getVector(
              "sundir",
              sunDirWorld);
        turbidity =
          pl.
            getFloat(
              "turbidity",
              turbidity);
        initSunSky(
          );
        return true;
    }
    public void init(String name, SunflowAPI api) { api.geometry(
                                                          name,
                                                          this);
                                                    api.shader(
                                                          name +
                                                          ".shader",
                                                          this);
                                                    api.parameter(
                                                          "shaders",
                                                          name +
                                                          ".shader");
                                                    api.instance(
                                                          name +
                                                          ".instance",
                                                          name);
                                                    api.light(
                                                          name +
                                                          ".light",
                                                          this);
    }
    private Color getSkyRGB(Vector3 dir) { if (dir.z < 0)
                                               return Color.
                                                        BLACK;
                                           if (dir.z < 0.001F)
                                               dir.
                                                 z =
                                                 0.001F;
                                           dir.normalize(
                                                 );
                                           double theta =
                                             Math.
                                             acos(
                                               MathUtils.
                                                 clamp(
                                                   dir.
                                                     z,
                                                   -1,
                                                   1));
                                           double gamma =
                                             Math.
                                             acos(
                                               MathUtils.
                                                 clamp(
                                                   Vector3.
                                                     dot(
                                                       dir,
                                                       sunDir),
                                                   -1,
                                                   1));
                                           double x = perezFunction(
                                                        perezx,
                                                        theta,
                                                        gamma,
                                                        zenithx);
                                           double y = perezFunction(
                                                        perezy,
                                                        theta,
                                                        gamma,
                                                        zenithy);
                                           double Y = perezFunction(
                                                        perezY,
                                                        theta,
                                                        gamma,
                                                        zenithY) *
                                             1.0E-4;
                                           XYZColor c = ChromaticitySpectrum.
                                             get(
                                               (float)
                                                 x,
                                               (float)
                                                 y);
                                           float X = (float)
                                                       (c.
                                                          getX(
                                                            ) *
                                                          Y /
                                                          c.
                                                          getY(
                                                            ));
                                           float Z = (float)
                                                       (c.
                                                          getZ(
                                                            ) *
                                                          Y /
                                                          c.
                                                          getY(
                                                            ));
                                           return RGBSpace.
                                                    SRGB.
                                             convertXYZtoRGB(
                                               X,
                                               (float)
                                                 Y,
                                               Z); }
    public int getNumSamples() { return 1 + numSkySamples;
    }
    public void getPhoton(double randX1, double randY1, double randX2,
                          double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power) {  }
    public float getPower() { return 0; }
    public void getSamples(ShadingState state) { if (Vector3.
                                                       dot(
                                                         sunDirWorld,
                                                         state.
                                                           getGeoNormal(
                                                             )) >
                                                       0 &&
                                                       Vector3.
                                                       dot(
                                                         sunDirWorld,
                                                         state.
                                                           getNormal(
                                                             )) >
                                                       0) {
                                                     LightSample dest =
                                                       new LightSample(
                                                       );
                                                     dest.
                                                       setShadowRay(
                                                         new Ray(
                                                           state.
                                                             getPoint(
                                                               ),
                                                           sunDirWorld));
                                                     dest.
                                                       getShadowRay(
                                                         ).
                                                       setMax(
                                                         Float.
                                                           MAX_VALUE);
                                                     dest.
                                                       setRadiance(
                                                         sunColor,
                                                         sunColor);
                                                     dest.
                                                       traceShadow(
                                                         state);
                                                     state.
                                                       addSample(
                                                         dest);
                                                 }
                                                 int n = state.
                                                   getDiffuseDepth(
                                                     ) >
                                                   0
                                                   ? 1
                                                   : numSkySamples;
                                                 for (int i =
                                                        0;
                                                      i <
                                                        n;
                                                      i++) {
                                                     double randX =
                                                       state.
                                                       getRandom(
                                                         i,
                                                         0,
                                                         n);
                                                     double randY =
                                                       state.
                                                       getRandom(
                                                         i,
                                                         1,
                                                         n);
                                                     int x =
                                                       0;
                                                     while (randX >=
                                                              colHistogram[x] &&
                                                              x <
                                                              colHistogram.
                                                                length -
                                                              1)
                                                         x++;
                                                     float[] rowHistogram =
                                                       imageHistogram[x];
                                                     int y =
                                                       0;
                                                     while (randY >=
                                                              rowHistogram[y] &&
                                                              y <
                                                              rowHistogram.
                                                                length -
                                                              1)
                                                         y++;
                                                     float u =
                                                       (float)
                                                         (x ==
                                                            0
                                                            ? randX /
                                                            colHistogram[0]
                                                            : (randX -
                                                                 colHistogram[x -
                                                                                1]) /
                                                            (colHistogram[x] -
                                                               colHistogram[x -
                                                                              1]));
                                                     float v =
                                                       (float)
                                                         (y ==
                                                            0
                                                            ? randY /
                                                            rowHistogram[0]
                                                            : (randY -
                                                                 rowHistogram[y -
                                                                                1]) /
                                                            (rowHistogram[y] -
                                                               rowHistogram[y -
                                                                              1]));
                                                     float px =
                                                       x ==
                                                       0
                                                       ? colHistogram[0]
                                                       : colHistogram[x] -
                                                       colHistogram[x -
                                                                      1];
                                                     float py =
                                                       y ==
                                                       0
                                                       ? rowHistogram[0]
                                                       : rowHistogram[y] -
                                                       rowHistogram[y -
                                                                      1];
                                                     float su =
                                                       (x +
                                                          u) /
                                                       colHistogram.
                                                         length;
                                                     float sv =
                                                       (y +
                                                          v) /
                                                       rowHistogram.
                                                         length;
                                                     float invP =
                                                       (float)
                                                         Math.
                                                         sin(
                                                           sv *
                                                             Math.
                                                               PI) *
                                                       jacobian /
                                                       (n *
                                                          px *
                                                          py);
                                                     Vector3 localDir =
                                                       getDirection(
                                                         su,
                                                         sv);
                                                     Vector3 dir =
                                                       basis.
                                                       transform(
                                                         localDir,
                                                         new Vector3(
                                                           ));
                                                     if (Vector3.
                                                           dot(
                                                             dir,
                                                             state.
                                                               getGeoNormal(
                                                                 )) >
                                                           0 &&
                                                           Vector3.
                                                           dot(
                                                             dir,
                                                             state.
                                                               getNormal(
                                                                 )) >
                                                           0) {
                                                         LightSample dest =
                                                           new LightSample(
                                                           );
                                                         dest.
                                                           setShadowRay(
                                                             new Ray(
                                                               state.
                                                                 getPoint(
                                                                   ),
                                                               dir));
                                                         dest.
                                                           getShadowRay(
                                                             ).
                                                           setMax(
                                                             Float.
                                                               MAX_VALUE);
                                                         Color radiance =
                                                           getSkyRGB(
                                                             localDir);
                                                         dest.
                                                           setRadiance(
                                                             radiance,
                                                             radiance);
                                                         dest.
                                                           getDiffuseRadiance(
                                                             ).
                                                           mul(
                                                             invP);
                                                         dest.
                                                           getSpecularRadiance(
                                                             ).
                                                           mul(
                                                             invP);
                                                         dest.
                                                           traceShadow(
                                                             state);
                                                         state.
                                                           addSample(
                                                             dest);
                                                     }
                                                 }
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    public int getNumPrimitives() { return 1;
    }
    public float getPrimitiveBound(int primID,
                                   int i) {
        return 0;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) {
        return null;
    }
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
        if (r.
              getMax(
                ) ==
              Float.
                POSITIVE_INFINITY)
            state.
              setIntersection(
                0,
                0,
                0);
    }
    public void prepareShadingState(ShadingState state) {
        if (state.
              includeLights(
                ))
            state.
              setShader(
                this);
    }
    public Color getRadiance(ShadingState state) {
        return getSkyRGB(
                 basis.
                   untransform(
                     state.
                       getRay(
                         ).
                       getDirection(
                         ))).
          constrainRGB(
            );
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        
    }
    private Vector3 getDirection(float u,
                                 float v) {
        Vector3 dest =
          new Vector3(
          );
        double phi =
          0;
        double theta =
          0;
        theta =
          u *
            2 *
            Math.
              PI;
        phi =
          v *
            Math.
              PI;
        double sin_phi =
          Math.
          sin(
            phi);
        dest.
          x =
          (float)
            (-sin_phi *
               Math.
               cos(
                 theta));
        dest.
          y =
          (float)
            Math.
            cos(
              phi);
        dest.
          z =
          (float)
            (sin_phi *
               Math.
               sin(
                 theta));
        return dest;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169179348000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "qkF8r+PmDZ8f/i+RTmjfDisAAA==");
}
