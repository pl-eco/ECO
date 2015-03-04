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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "Zq9xTtrzsWoQ3+u4ecPnh/8LLcPPGR4rAAA=");
}
