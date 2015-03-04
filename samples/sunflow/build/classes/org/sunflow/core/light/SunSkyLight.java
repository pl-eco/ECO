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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "+s74KgAA");
}
