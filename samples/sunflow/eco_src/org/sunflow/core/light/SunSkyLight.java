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
    final private double[] perezY = new double[5];
    final private double[] perezx = new double[5];
    final private double[] perezy = new double[5];
    private float jacobian;
    private float[] colHistogram;
    private float[][] imageHistogram;
    final private static float[] solAmplitudes = { 165.5F, 162.3F, 211.2F,
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
    final private static RegularSpectralCurve solCurve = new RegularSpectralCurve(
      solAmplitudes,
      380,
      750);
    final private static float[] k_oWavelengths = { 300, 305, 310,
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
    final private static float[] k_oAmplitudes = { 10.0F, 4.8F, 2.7F,
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
    final private static float[] k_gWavelengths = { 759, 760, 770,
    771 };
    final private static float[] k_gAmplitudes = { 0, 3.0F, 0.21F,
    0 };
    final private static float[] k_waWavelengths = { 689, 690, 700,
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
    final private static float[] k_waAmplitudes = { 0.0F, 0.016F,
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
    final private static IrregularSpectralCurve k_oCurve = new IrregularSpectralCurve(
      k_oWavelengths,
      k_oAmplitudes);
    final private static IrregularSpectralCurve k_gCurve =
      new IrregularSpectralCurve(
      k_gWavelengths,
      k_gAmplitudes);
    final private static IrregularSpectralCurve k_waCurve =
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
                           this.initSunSky(); }
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
                                                      this.
                                                        computeAttenuatedSunlight(
                                                          sunTheta,
                                                          turbidity);
                                                    sunColor =
                                                      RGBSpace.
                                                        SRGB.
                                                        convertXYZtoRGB(
                                                          sunSpectralRadiance.
                                                            toXYZ().
                                                            mul(
                                                              1.0E-4F)).
                                                        constrainRGB();
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
                                float T2 = turbidity *
                                  turbidity;
                                double chi = (4.0 /
                                                9.0 -
                                                T /
                                                120.0) *
                                  (Math.
                                     PI -
                                     2.0 *
                                     sunTheta);
                                zenithY = (4.0453 *
                                             T -
                                             4.971) *
                                            Math.
                                            tan(
                                              chi) -
                                            0.2155 *
                                            T +
                                            2.4192;
                                zenithY *= 1000;
                                zenithx = (0.00165 *
                                             theta3 -
                                             0.00374 *
                                             theta2 +
                                             0.00208 *
                                             sunTheta +
                                             0) *
                                            T2 +
                                            (-0.02902 *
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
                                zenithy = (0.00275 *
                                             theta3 -
                                             0.0061 *
                                             theta2 +
                                             0.00316 *
                                             sunTheta +
                                             0) *
                                            T2 +
                                            (-0.04212 *
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
                                perezY[0] = 0.17872 *
                                              T -
                                              1.46303;
                                perezY[1] = -0.3554 *
                                              T +
                                              0.42749;
                                perezY[2] = -0.02266 *
                                              T +
                                              5.32505;
                                perezY[3] = 0.12064 *
                                              T -
                                              2.57705;
                                perezY[4] = -0.06696 *
                                              T +
                                              0.37027;
                                perezx[0] = -0.01925 *
                                              T -
                                              0.25922;
                                perezx[1] = -0.06651 *
                                              T +
                                              8.1E-4;
                                perezx[2] = -4.1E-4 *
                                              T +
                                              0.21247;
                                perezx[3] = -0.06409 *
                                              T -
                                              0.89887;
                                perezx[4] = -0.00325 *
                                              T +
                                              0.04517;
                                perezy[0] = -0.01669 *
                                              T -
                                              0.26078;
                                perezy[1] = -0.09495 *
                                              T +
                                              0.00921;
                                perezy[2] = -0.00792 *
                                              T +
                                              0.21023;
                                perezy[3] = -0.04405 *
                                              T -
                                              1.65369;
                                perezy[4] = -0.01092 *
                                              T +
                                              0.05291;
                                final int w = 32;
                                final int h = 32;
                                imageHistogram = (new float[w][h]);
                                colHistogram = (new float[w]);
                                float du = 1.0F /
                                  w;
                                float dv = 1.0F /
                                  h;
                                for (int x = 0; x <
                                                  w;
                                     x++) { for (int y =
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
                                                  this.
                                                  getSkyRGB(
                                                    this.
                                                      getDirection(
                                                        u,
                                                        v));
                                                imageHistogram[x][y] =
                                                  c.
                                                    getLuminance() *
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
                                for (int x = 0; x <
                                                  w;
                                     x++) colHistogram[x] /=
                                            colHistogram[w -
                                                           1];
                                jacobian = (float)
                                             (2 *
                                                Math.
                                                  PI *
                                                Math.
                                                  PI) /
                                             (w *
                                                h);
    }
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
        this.
          initSunSky();
        return true;
    }
    public void init(String name, SunflowAPI api) {
        api.
          geometry(
            name,
            this);
        api.
          shader(
            name +
            ".shader",
            this);
        api.
          parameter(
            "shaders",
            name +
            ".shader");
        api.
          instance(
            name +
            ".instance",
            name);
        api.
          light(
            name +
            ".light",
            this);
    }
    private Color getSkyRGB(Vector3 dir) { if (dir.
                                                 z <
                                                 0)
                                               return Color.
                                                        BLACK;
                                           if (dir.
                                                 z <
                                                 0.001F)
                                               dir.
                                                 z =
                                                 0.001F;
                                           dir.normalize();
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
                                           double x =
                                             this.
                                             perezFunction(
                                               perezx,
                                               theta,
                                               gamma,
                                               zenithx);
                                           double y =
                                             this.
                                             perezFunction(
                                               perezy,
                                               theta,
                                               gamma,
                                               zenithy);
                                           double Y =
                                             this.
                                             perezFunction(
                                               perezY,
                                               theta,
                                               gamma,
                                               zenithY) *
                                             1.0E-4;
                                           XYZColor c =
                                             ChromaticitySpectrum.
                                             get(
                                               (float)
                                                 x,
                                               (float)
                                                 y);
                                           float X =
                                             (float)
                                               (c.
                                                  getX() *
                                                  Y /
                                                  c.
                                                  getY());
                                           float Z =
                                             (float)
                                               (c.
                                                  getZ() *
                                                  Y /
                                                  c.
                                                  getY());
                                           return RGBSpace.
                                                    SRGB.
                                             convertXYZtoRGB(
                                               X,
                                               (float)
                                                 Y,
                                               Z);
    }
    public int getNumSamples() { return 1 + numSkySamples;
    }
    public void getPhoton(double randX1, double randY1,
                          double randX2,
                          double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power) {  }
    public float getPower() { return 0; }
    public void getSamples(ShadingState state) { if (Vector3.
                                                       dot(
                                                         sunDirWorld,
                                                         state.
                                                           getGeoNormal()) >
                                                       0 &&
                                                       Vector3.
                                                       dot(
                                                         sunDirWorld,
                                                         state.
                                                           getNormal()) >
                                                       0) {
                                                     LightSample dest =
                                                       new LightSample(
                                                       );
                                                     dest.
                                                       setShadowRay(
                                                         new Ray(
                                                           state.
                                                             getPoint(),
                                                           sunDirWorld));
                                                     dest.
                                                       getShadowRay().
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
                                                 int n =
                                                   state.
                                                   getDiffuseDepth() >
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
                                                       this.
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
                                                               getGeoNormal()) >
                                                           0 &&
                                                           Vector3.
                                                           dot(
                                                             dir,
                                                             state.
                                                               getNormal()) >
                                                           0) {
                                                         LightSample dest =
                                                           new LightSample(
                                                           );
                                                         dest.
                                                           setShadowRay(
                                                             new Ray(
                                                               state.
                                                                 getPoint(),
                                                               dir));
                                                         dest.
                                                           getShadowRay().
                                                           setMax(
                                                             Float.
                                                               MAX_VALUE);
                                                         Color radiance =
                                                           this.
                                                           getSkyRGB(
                                                             localDir);
                                                         dest.
                                                           setRadiance(
                                                             radiance,
                                                             radiance);
                                                         dest.
                                                           getDiffuseRadiance().
                                                           mul(
                                                             invP);
                                                         dest.
                                                           getSpecularRadiance().
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
              getMax() ==
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
              includeLights())
            state.
              setShader(
                this);
    }
    public Color getRadiance(ShadingState state) {
        return this.
          getSkyRGB(
            basis.
              untransform(
                state.
                  getRay().
                  getDirection())).
          constrainRGB();
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169179348000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVbCWwc13l+u7wPiRQp67AlUZJ1i9zlfYhCLYqkJMoriSJ1" +
       "WZJDD2eHyzFnZyaz\ns+SSVh25AmzHRtyodgIXiI8EBmQrTuw0ad00jg8kbl" +
       "y7bmwHdYCkcZoaSeo2TmMkcBW0KPr/b97s\nzrmcJUUC8zi7847/+7///9//" +
       "3rx99iNSktLIOj4V0WdVIRXpHx3mtJQQ75e4VOoEfDXGv1ZSMXzl\nVlkJk1" +
       "CMhMW4TmpifCoa53QuKsajQwO9GY3sVhVpNiEpekTI6JE7pQ7W3+FYh6vD04" +
       "+/UHfPU8UN\nYVISIzWcLCs6p4uKPCgJyZROamN3ctNcNK2LUjQmpvTeGFkm" +
       "yOlkvyKndE7WU58md5OiGClVeexT\nJ5ti5uBRGDyqchqXjNLho8N0WOihXh" +
       "N0TpSFeF92OGjZaG8JYrN2I+7a0Ek5PjwFcKgEgHpjFrWB\n1gVVLXr6VOeF" +
       "J58pIjVnSY0oj2JnPCDRYbyzpDopJMcFLdUXjwvxs2SFLAjxUUETOUmco6Oe" +
       "JXUp\nMSFzeloTUiNCSpGmsWJdKq0KGh3T/DJGqnnEpKV5XdGyOpoQBSlufi" +
       "qZkLgEwF6Vg23APYDfA8BK\nEQTTJjheMJsUT4kyMN7gbJHFuOVWqABNy5KC" +
       "PqlkhyqWOfiC1BlcSpyciI7qmignoGqJkoZRdHKj\nb6eoa5Xjp7iEMKaTNc" +
       "56w8YjqFVBFYFNdHKDsxrtCVi60cGShZ/dqz65/+kvvbSP2nZxXOAllL8S\n" +
       "Gm1wNBoRJgRNkHnBaHgtHXlk6Lb0ujAhUPkGR2WjTt/WF07G/v3lBqPOTR51" +
       "jo3fKfD6GH/0csPI\nXQcVUoRilKtKSkTybcipOwyzJ70ZFbx2VbZHfBgxH7" +
       "4y8ve3Xbwq/GeYVA6RUl6R0kmwoxW8klRF\nSdAOCrKgcboQHyIVghzvp8+H" +
       "SBncx8DkjW+PTUykBH2IFEv0q1KFfgYVTUAXqKIKuBflCcW8Vzl9\nkt5nVE" +
       "JIGVzkAFw3EeOP/tdJRySaSssTkjITTWl8VNES2c+8oglRSUxM6tHRtDw6NR" +
       "vD+wiaj6qT\n49FJJSlEOZ6TRVmJJkRwWF5pigvT+H8hnWZQ2rqZUAjDn9ON" +
       "JfCAQ4oUF7Qx/soHb1wYvPWz9xsm\ngmbNcOpkM4wVYWNFcKwIHStiGYuEQn" +
       "SIlTimwRNoeQr8FSJb9c7R2w/fcf/mIjAQdaYYVIRVNwMi\nJsggr/TnnHqI" +
       "xj8eLGvNV87dF7l25RbDsqL+sdezddXbX3vzyd9X7wqTsHdgRIAQmiuxm2GM" +
       "ptmA\nt8XpSl79//aBI998782f7cg5lU62uHzd3RJ9dbOTCk3hhThEv1z3T6" +
       "2tKTpNTl0Ok2IIABD0qPwQ\nTzY4x7D5bK8Z/xBLWYxUTShakpPwkRm0KvVJ" +
       "TZnJfUNtpJbe1wM5VWjEq+AaZlZN/+PTG1QsVxk2\nhWw7UND4eu1SafOPX6" +
       "x6jarFDMU1lsluVNANx16RM5YTmiDA9z97dPjhL3x03zlqKcxUdJgB0+OS\n" +
       "yGegybZcE/BoCaIKErnlpJxU4uKEyI1LAlrc/9Zsbfnr3zxUa1AjwTcms43z" +
       "d5D7fu1+cvHNT/33\nBtpNiMcZJQcjV81AU5/ruU/TuFmUI3PPu+v/8gfcYx" +
       "DwIMikxDmBxg1CkRGqxyjV+y5aRhzPWrDY\nDH03+pi+x/w9xl+4mtic/vQ/" +
       "fJtKXcVZEwErDUc4tddgHoubUburnd57iEtNQr32V46er5Ve+R/o\n8Sz0yM" +
       "O8mTqmQdjI2EhktUvKfvLq91bd8U4RCR8glZLCxQ9w1P5JBRiekJqEiJNRb9" +
       "lHbat2phxL\nCplQJdzIFJCxfFoHwu30d/8DOPvnPGdsvPHp2BvHHqMK8HV8" +
       "j8nP0c/cSycfv/aW/j7tJ+eB2HpT\nxh1OIWPKte1+b3pF6fNPJMOk7Cyp5V" +
       "lOd4qT0mjnZyEFSZmJHuR9tuf2dMKYO3uzEWad0/stwzp9\nPxfG4R5r4321" +
       "w92rUdsb4FrP3H29091DhN7cQptsoeX2rHOWqZo4zWGeR5ZB9goTwiiXVCWw" +
       "EI2s\nsebbmpiEeXuaBqkP7t383ddPPnGfEdjzMGtrNcZ/5uf/OlX056+OG+" +
       "2c9DkqX97w1K+++cHISiMI\nGAneza4cy9rGSPKofmpUdIhN+Uagtb+/e9Oz" +
       "d4+8zySqs6cqg6CQX89+T9i+93O/8JhXiyANNUIp\nlq1Y7DOsvsPXO/bYeb" +
       "uZcWdy6MXbMBZ9OikZ51JiirZfDasJ66SehLQmckyDzPYonS32Y02HaMcL\n" +
       "FG0ju8x7L9FOM9GqQJABUTutaCyLBwHXuAQ8JWAYaXPIdaZAudbBtYnJtclH" +
       "rk8xuSpgQTIuxkV9\nlvYxqBpjHdbRzxTOyd5YgaLcxBg0mfQSZYKJUmqoqF" +
       "DtJAoUCaegLUykLT4iJZlI9TD+qArDapw0\nwsVFDEGmfA1W+cQkLGMiZtX+" +
       "tDYtOMSUF6C5rUzMrT5i6kzMchADJmslq7vVbtnoc4dM6QXItI3J\ntM1Hpr" +
       "ssMp2YhJkJP3OOgS8UOHATXNvZwNt9Br7IBi6bE2RRn7zNac+lcQWSLCct9y" +
       "yBJPfbJaHR\n9pJj3M8uwbifs4876zXuQ8HHrcNvW+Hawcbd4TPuX7inzTDG" +
       "D1Hm6Cq8P4MZLiTwc7dhSm2ZMmka\niTPNM58fqB/pOXeJrmQqOEnkUkdzM3" +
       "tYjONdCOafrf4zabazMX777S/87tWXhO00nSsXU5BB9GkJ\njx0ES5uPuavC" +
       "kR9PPE5XI8Uwkxi5hHPrxb2zYtswoWQvVw3OVVWFtXIlhdPa2tPVBvDrAD7u" +
       "uEXE\nOMvGBt49MH51Qr4aD9P+6Wh92IShr6DfWNRRpKgp3FKw7N2xnrYcU1" +
       "O4+FpmGWRo4MLzh6vLv/zg\nvbR/pssKy/YE+1w2zWlHrbN3FRW8paujs7tZ" +
       "J+eu+8p9T2trS2NbW1NbD4x14tDQaMQwEhTgS1mT\nuVsjq9w6Q6TMLNFQAf" +
       "NymqZjRkmzEutDwFI8Mtg34PCFhxfgCztZtzt9fOGrHr6A91+0O4Ix8qM+\n" +
       "dtJOnz5mZaCrfWkYaOnxYIBK9Y2srPjpikN3zy5Ad7uY7nb56O7bwXQ3m1d3" +
       "HR6661oi62320B2N\nui9nZfXS3d8tYNrdzXS320d3PzCn3Ts5XhmHRMVr2n" +
       "29wIExeWxkAzf6DPyPbOBqXpEOiSldSZjb\nTIwhLrvwD7GdMTqSanbwTj7W" +
       "dyLrkiAnjE3JISx+yOZ07DJs1DfznvpcDOiXFFnAzQ7zmbFxJyqR\n7L48PM" +
       "y4hNPIetu23REa3HPL3gee+eoL+ju79xjLoV3+05Gz4a49T8417HnuwQVs1j" +
       "U4Zi1n1yum\nbzpeNCm+TgM8W0W7NvrtjXrta+dKkCetySdsK+iNBoE/xGJb" +
       "nk2cX+R59gEWP4dsgEc+DPpAxw3e\nm1SDSVWn20pzf7v6W3uvPP4+alnN2B" +
       "y80+XgXV3dS+PgXXR6au/QyQrq4FYTRyF+7TB7L2d/q0Cf\nw1y/iflck4/P" +
       "/RfzueU0tXd5nc37nPGxy1AfFn9YGq11QljsaOpohWUI1ZpdyMi5zO06KYIS" +
       "RbhG\nVeaUsdtNcdsSUWwI2w4xvN5DWBTjjy49e9H8u+A0rzVpbmc0R71pDo" +
       "XckbEoNx/igpm+hMR9qZQi\n9SVVSdTTcSGVl/8el26bOzt1cvb667ajrbG7" +
       "takHvOfQ/K9XIjYE21MbdU5LCPpG+/eolXInXA86\nQuEC6ZiCq4PR0eFDR2" +
       "1eOrBGSQbXvYqx/jfnnW3utfiIkEhLnJZnuyC0okAALXD1MgCdPgDWBgKw\n" +
       "fGpMOc1NC8asm9eYupvdxtTdsySO2tXZ2N3Z1NIMC5HDAazJjsFiTvYHiLnB" +
       "BdnLoG4skA9MevsZ\nH3t9+NgeiI9lIFww3+5ucdPR06KT80tCR0tza1NLS0" +
       "DvtmGw02H37iYnYC8ydhRIxhBcA4yMAR8y\nOoI6RyKoc7R6sNG5NM7RVaBz" +
       "JPycI+Fwjl4XZC8+Ogvk4xBcg4yPQR8++oM6RyKgc7R50NG9NM7R\nVahzJH" +
       "ycI2F3jiEnYC8yBgokA6e7g4yMAz5kHA9ERs3U2AwX1DvcexwtzW1L4x3djd" +
       "1d4B2QkMYC\nsWEDYePD9gRhn3aj9uJkpEBOeuA6zDg55MPJWNCANcMF9BD3" +
       "1klLc8fSeEg3eEg7eEh30IhlBeGg\nxO4jgguzFyF3FEhIGq4YIyTmQ0gyWH" +
       "4Is5stP9zhzg+HNG3+DLGAF0pZCEcYhCM+EKaDQkhcFwgz\nBULIwHWUQTjq" +
       "A+EzgSBUoIlcFwwX82CgbmbsnBinPIrM0da5jJ4a+qiS1nJvGDe4KmXfzuMe" +
       "iVlt\ntava6CSHR1dwL8vvxCR9tX/fmY+r7+W+f3uYbdbciu+DFbVJEiCaWc" +
       "Sup/c/tW9UHIfrDCPjjJMM\nqiQs+hx7Q+HcBp/xZhCrhr7ov4MUehSLh3Wy" +
       "Fs89pnWhT9cFOY3nHiFC0FBhKmK+N7I50h6Zz/DM\nIzpewM/DpTHgWmDgxb" +
       "THYmsAdu5dd3c53g/FFJ6Thga+/GrVu5fTnYdNmp5ACE8tyVzZ0tLe2NbT\n" +
       "1AGrhiLJ2Om4lCsoH1/Jw9VVLJ6ALIVufh9Iy7z5fulSTvlPLkb561kYMMNB" +
       "MOVbZfyrPM++hcVz\nOr7kE3VDObSe5TVy8bQixnNgnl8gGHoEERfs32Fgvu" +
       "MLxrn16tjz9ogSuHks6IJmjRKrrNVGjf99\nw0MU9st5VPIaFi/qpDStglkK" +
       "TnWUjSuKJHByTiPfXYxGcBvjDaaRNxaqkdrcWwDjqHoQHbydRwfv\nYfGWjg" +
       "elRTxzEfqbHNx/Wow14xv1HzG4Pwpszdb3KPkOylDZ38+D69+w+CkEfcilwN" +
       "RHDu73nFMs\nB0hyuP9lMTTjQa5fMty/DEyzVfTf5Hn2Wyw+hCgEsI6mk+y0" +
       "HtYcysn/H4uRfxauD5n8HwaWv4z2\nWOYIqrno6NQ75XJYEWW9bV6u5yOOKu" +
       "aTPEr7Pyx+b9jC8KSi07BtNfQ/LEZh+ArzI6awjxZCeLg0\nz7NyLIogMUXZ" +
       "lRnjACuXFT1cvBjRcbCPmegfBxbd4aPrPXM0CE34OyKBgqjNA3AlFstgXkJH" +
       "zZqz\nhZ3w8kVCDG02EBr/C2ZnXZ5nG7BYq5N6EH4/NwWgs2ls9qxmkFSX4p" +
       "x3HzYfzk2Aj51fC7nOrwXC\nuSPPs11YbIXpxwg7OZD2yBPethgIHSA6O0cR" +
       "cp2j8IVgybrpK3Q65YVb84BpxyKqkxXoUiaS/Upa\njjt8q3kxaFoBRYShiS" +
       "zUt9wx8QgH036mnQLZmwfkPix6dLIcQNKTuRRg1ijXuTqmz8GA9yuZnAb2\n" +
       "LEYDsIwNtTINtAbWQJF9NbnS5T0j3GzO6lZ7/cBpCI+xpYwX77kgdDiPtvCU" +
       "dfigTupEs2nWMBzB\n6NBiNNIFmuhiGulaqE0EiLdn8kA9j8VJCFmqJqicJl" +
       "gbO7CeWgzWZhB5L8Pqekt0HbHG82DFgzVh\nTidV4ATOM87zpoHh8cXA/xNA" +
       "sY/B31doMAsGP0heFNbyqIcWCr5z5jkd7N4rNwqri1kEYBBkb6dC\nrrdTWS" +
       "0E2kgJX8yD5M+w+FOdVAPRA6LGztz4BlFrYklB3h0UZAaMybKzgOd91rh+X2" +
       "38JpiP/eSu\n85/E/vmPxqlb83e7VTFSPpGWJOuveyz3peCWEyLVTZXxWx+6" +
       "oRJ+0LHEy+0m66Qku1UUfsCo/RBM\n1c7asMbDf9ZqlwGNpRosedmdtdIjOi" +
       "mCSnj7BdXUqWUdavzGKWNTE+rlZtv5MfqDd/OMV9r4yfsY\nf+Zr5zZmHjzx" +
       "eXpwrISXuLk57KYyRsqMXz7SXvGc2Cbf3sy+3ibPP3fqxa/3mFtJ9JdxK9nP" +
       "HV2G\n2mo89acdH/xK/X/vZ4P5fEAAAA==");
}
