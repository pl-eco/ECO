package org.sunflow.image;
import org.sunflow.math.MathUtils;
public final class RGBSpace {
    public static final RGBSpace ADOBE = new RGBSpace(0.64F, 0.33F, 0.21F,
                                                      0.71F,
                                                      0.15F,
                                                      0.06F,
                                                      0.31271F,
                                                      0.32902F,
                                                      2.2F,
                                                      0);
    public static final RGBSpace APPLE = new RGBSpace(0.625F, 0.34F, 0.28F,
                                                      0.595F,
                                                      0.155F,
                                                      0.07F,
                                                      0.31271F,
                                                      0.32902F,
                                                      1.8F,
                                                      0);
    public static final RGBSpace NTSC = new RGBSpace(0.67F, 0.33F, 0.21F,
                                                     0.71F,
                                                     0.14F,
                                                     0.08F,
                                                     0.3101F,
                                                     0.3162F,
                                                     20.0F /
                                                       9.0F,
                                                     0.018F);
    public static final RGBSpace HDTV = new RGBSpace(0.64F, 0.33F, 0.3F, 0.6F,
                                                     0.15F,
                                                     0.06F,
                                                     0.31271F,
                                                     0.32902F,
                                                     20.0F /
                                                       9.0F,
                                                     0.018F);
    public static final RGBSpace SRGB = new RGBSpace(0.64F, 0.33F, 0.3F, 0.6F,
                                                     0.15F,
                                                     0.06F,
                                                     0.31271F,
                                                     0.32902F,
                                                     2.4F,
                                                     0.00304F);
    public static final RGBSpace CIE = new RGBSpace(0.735F, 0.265F, 0.274F,
                                                    0.717F,
                                                    0.167F,
                                                    0.009F,
                                                    1 /
                                                      3.0F,
                                                    1 /
                                                      3.0F,
                                                    2.2F,
                                                    0);
    public static final RGBSpace EBU = new RGBSpace(0.64F, 0.33F, 0.29F, 0.6F,
                                                    0.15F,
                                                    0.06F,
                                                    0.31271F,
                                                    0.32902F,
                                                    20.0F /
                                                      9.0F,
                                                    0.018F);
    public static final RGBSpace SMPTE_C = new RGBSpace(0.63F, 0.34F, 0.31F,
                                                        0.595F,
                                                        0.155F,
                                                        0.07F,
                                                        0.31271F,
                                                        0.32902F,
                                                        20.0F /
                                                          9.0F,
                                                        0.018F);
    public static final RGBSpace SMPTE_240M = new RGBSpace(0.63F, 0.34F, 0.31F,
                                                           0.595F,
                                                           0.155F,
                                                           0.07F,
                                                           0.31271F,
                                                           0.32902F,
                                                           20.0F /
                                                             9.0F,
                                                           0.018F);
    public static final RGBSpace WIDE_GAMUT = new RGBSpace(0.7347F, 0.2653F,
                                                           0.1152F,
                                                           0.8264F,
                                                           0.1566F,
                                                           0.0177F,
                                                           0.3457F,
                                                           0.3585F,
                                                           2.2F,
                                                           0);
    private final float gamma;
    private final float breakPoint;
    private final float slope;
    private final float slopeMatch;
    private final float segmentOffset;
    private final float xr;
    private final float yr;
    private final float zr;
    private final float xg;
    private final float yg;
    private final float zg;
    private final float xb;
    private final float yb;
    private final float zb;
    private final float xw;
    private final float yw;
    private final float zw;
    private final float rx;
    private final float ry;
    private final float rz;
    private final float gx;
    private final float gy;
    private final float gz;
    private final float bx;
    private final float by;
    private final float bz;
    private final float rw;
    private final float gw;
    private final float bw;
    private final int[] GAMMA_CURVE;
    private final int[] INV_GAMMA_CURVE;
    public RGBSpace(float xRed, float yRed, float xGreen, float yGreen, float xBlue,
                    float yBlue,
                    float xWhite,
                    float yWhite,
                    float gamma,
                    float breakPoint) { super();
                                        this.gamma = gamma;
                                        this.breakPoint = breakPoint;
                                        if (breakPoint > 0) { slope =
                                                                1 /
                                                                  (gamma /
                                                                     (float)
                                                                       Math.
                                                                       pow(
                                                                         breakPoint,
                                                                         1 /
                                                                           gamma -
                                                                           1) -
                                                                     gamma *
                                                                     breakPoint +
                                                                     breakPoint);
                                                              slopeMatch =
                                                                gamma *
                                                                  slope /
                                                                  (float)
                                                                    Math.
                                                                    pow(
                                                                      breakPoint,
                                                                      1 /
                                                                        gamma -
                                                                        1);
                                                              segmentOffset =
                                                                slopeMatch *
                                                                  (float)
                                                                    Math.
                                                                    pow(
                                                                      breakPoint,
                                                                      1 /
                                                                        gamma) -
                                                                  slope *
                                                                  breakPoint;
                                        }
                                        else {
                                            slope =
                                              1;
                                            slopeMatch =
                                              1;
                                            segmentOffset =
                                              0;
                                        }
                                        GAMMA_CURVE =
                                          (new int[256]);
                                        INV_GAMMA_CURVE =
                                          (new int[256]);
                                        for (int i =
                                               0;
                                             i <
                                               256;
                                             i++) {
                                            float c =
                                              i /
                                              255.0F;
                                            GAMMA_CURVE[i] =
                                              MathUtils.
                                                clamp(
                                                  (int)
                                                    (gammaCorrect(
                                                       c) *
                                                       255 +
                                                       0.5F),
                                                  0,
                                                  255);
                                            INV_GAMMA_CURVE[i] =
                                              MathUtils.
                                                clamp(
                                                  (int)
                                                    (ungammaCorrect(
                                                       c) *
                                                       255 +
                                                       0.5F),
                                                  0,
                                                  255);
                                        }
                                        float xr =
                                          xRed;
                                        float yr =
                                          yRed;
                                        float zr =
                                          1 -
                                          (xr +
                                             yr);
                                        float xg =
                                          xGreen;
                                        float yg =
                                          yGreen;
                                        float zg =
                                          1 -
                                          (xg +
                                             yg);
                                        float xb =
                                          xBlue;
                                        float yb =
                                          yBlue;
                                        float zb =
                                          1 -
                                          (xb +
                                             yb);
                                        xw =
                                          xWhite;
                                        yw =
                                          yWhite;
                                        zw =
                                          1 -
                                            (xw +
                                               yw);
                                        float rx =
                                          yg *
                                          zb -
                                          yb *
                                          zg;
                                        float ry =
                                          xb *
                                          zg -
                                          xg *
                                          zb;
                                        float rz =
                                          xg *
                                          yb -
                                          xb *
                                          yg;
                                        float gx =
                                          yb *
                                          zr -
                                          yr *
                                          zb;
                                        float gy =
                                          xr *
                                          zb -
                                          xb *
                                          zr;
                                        float gz =
                                          xb *
                                          yr -
                                          xr *
                                          yb;
                                        float bx =
                                          yr *
                                          zg -
                                          yg *
                                          zr;
                                        float by =
                                          xg *
                                          zr -
                                          xr *
                                          zg;
                                        float bz =
                                          xr *
                                          yg -
                                          xg *
                                          yr;
                                        rw =
                                          (rx *
                                             xw +
                                             ry *
                                             yw +
                                             rz *
                                             zw) /
                                            yw;
                                        gw =
                                          (gx *
                                             xw +
                                             gy *
                                             yw +
                                             gz *
                                             zw) /
                                            yw;
                                        bw =
                                          (bx *
                                             xw +
                                             by *
                                             yw +
                                             bz *
                                             zw) /
                                            yw;
                                        this.
                                          rx =
                                          rx /
                                            rw;
                                        this.
                                          ry =
                                          ry /
                                            rw;
                                        this.
                                          rz =
                                          rz /
                                            rw;
                                        this.
                                          gx =
                                          gx /
                                            gw;
                                        this.
                                          gy =
                                          gy /
                                            gw;
                                        this.
                                          gz =
                                          gz /
                                            gw;
                                        this.
                                          bx =
                                          bx /
                                            bw;
                                        this.
                                          by =
                                          by /
                                            bw;
                                        this.
                                          bz =
                                          bz /
                                            bw;
                                        float s =
                                          1 /
                                          (this.
                                             rx *
                                             (this.
                                                gy *
                                                this.
                                                  bz -
                                                this.
                                                  by *
                                                this.
                                                  gz) -
                                             this.
                                               ry *
                                             (this.
                                                gx *
                                                this.
                                                  bz -
                                                this.
                                                  bx *
                                                this.
                                                  gz) +
                                             this.
                                               rz *
                                             (this.
                                                gx *
                                                this.
                                                  by -
                                                this.
                                                  bx *
                                                this.
                                                  gy));
                                        this.
                                          xr =
                                          s *
                                            (this.
                                               gy *
                                               this.
                                                 bz -
                                               this.
                                                 gz *
                                               this.
                                                 by);
                                        this.
                                          xg =
                                          s *
                                            (this.
                                               rz *
                                               this.
                                                 by -
                                               this.
                                                 ry *
                                               this.
                                                 bz);
                                        this.
                                          xb =
                                          s *
                                            (this.
                                               ry *
                                               this.
                                                 gz -
                                               this.
                                                 rz *
                                               this.
                                                 gy);
                                        this.
                                          yr =
                                          s *
                                            (this.
                                               gz *
                                               this.
                                                 bx -
                                               this.
                                                 gx *
                                               this.
                                                 bz);
                                        this.
                                          yg =
                                          s *
                                            (this.
                                               rx *
                                               this.
                                                 bz -
                                               this.
                                                 rz *
                                               this.
                                                 bx);
                                        this.
                                          yb =
                                          s *
                                            (this.
                                               rz *
                                               this.
                                                 gx -
                                               this.
                                                 rx *
                                               this.
                                                 gz);
                                        this.
                                          zr =
                                          s *
                                            (this.
                                               gx *
                                               this.
                                                 by -
                                               this.
                                                 gy *
                                               this.
                                                 bx);
                                        this.
                                          zg =
                                          s *
                                            (this.
                                               ry *
                                               this.
                                                 bx -
                                               this.
                                                 rx *
                                               this.
                                                 by);
                                        this.
                                          zb =
                                          s *
                                            (this.
                                               rx *
                                               this.
                                                 gy -
                                               this.
                                                 ry *
                                               this.
                                                 gx);
    }
    public final Color convertXYZtoRGB(XYZColor c) {
        return convertXYZtoRGB(
                 c.
                   getX(
                     ),
                 c.
                   getY(
                     ),
                 c.
                   getZ(
                     ));
    }
    public final Color convertXYZtoRGB(float X,
                                       float Y,
                                       float Z) {
        float r =
          rx *
          X +
          ry *
          Y +
          rz *
          Z;
        float g =
          gx *
          X +
          gy *
          Y +
          gz *
          Z;
        float b =
          bx *
          X +
          by *
          Y +
          bz *
          Z;
        return new Color(
          r,
          g,
          b);
    }
    public final XYZColor convertRGBtoXYZ(Color c) {
        float[] rgb =
          c.
          getRGB(
            );
        float X =
          xr *
          rgb[0] +
          xg *
          rgb[1] +
          xb *
          rgb[2];
        float Y =
          yr *
          rgb[0] +
          yg *
          rgb[1] +
          yb *
          rgb[2];
        float Z =
          zr *
          rgb[0] +
          zg *
          rgb[1] +
          zb *
          rgb[2];
        return new XYZColor(
          X,
          Y,
          Z);
    }
    public final boolean insideGamut(float r,
                                     float g,
                                     float b) {
        return r >=
          0 &&
          g >=
          0 &&
          b >=
          0;
    }
    public final float gammaCorrect(float v) {
        if (v <=
              0)
            return 0;
        else
            if (v >=
                  1)
                return 1;
            else
                if (v <=
                      breakPoint)
                    return slope *
                      v;
                else
                    return slopeMatch *
                      (float)
                        Math.
                        pow(
                          v,
                          1 /
                            gamma) -
                      segmentOffset;
    }
    public final float ungammaCorrect(float vp) {
        if (vp <=
              0)
            return 0;
        else
            if (vp >=
                  1)
                return 1;
            else
                if (vp <=
                      breakPoint *
                      slope)
                    return vp /
                      slope;
                else
                    return (float)
                             Math.
                             pow(
                               (vp +
                                  segmentOffset) /
                                 slopeMatch,
                               gamma);
    }
    public final int rgbToNonLinear(int rgb) {
        int rp =
          GAMMA_CURVE[rgb >>
                        16 &
                        255];
        int gp =
          GAMMA_CURVE[rgb >>
                        8 &
                        255];
        int bp =
          GAMMA_CURVE[rgb &
                        255];
        return rp <<
          16 |
          gp <<
          8 |
          bp;
    }
    public final int rgbToLinear(int rgb) {
        int rp =
          INV_GAMMA_CURVE[rgb >>
                            16 &
                            255];
        int gp =
          INV_GAMMA_CURVE[rgb >>
                            8 &
                            255];
        int bp =
          INV_GAMMA_CURVE[rgb &
                            255];
        return rp <<
          16 |
          gp <<
          8 |
          bp;
    }
    public final String toString() { String info =
                                       "Gamma function parameters:\n";
                                     info +=
                                       String.
                                         format(
                                           "  * Gamma:          %7.4f\n",
                                           gamma);
                                     info +=
                                       String.
                                         format(
                                           "  * Breakpoint:     %7.4f\n",
                                           breakPoint);
                                     info +=
                                       String.
                                         format(
                                           "  * Slope:          %7.4f\n",
                                           slope);
                                     info +=
                                       String.
                                         format(
                                           "  * Slope Match:    %7.4f\n",
                                           slopeMatch);
                                     info +=
                                       String.
                                         format(
                                           "  * Segment Offset: %7.4f\n",
                                           segmentOffset);
                                     info +=
                                       "XYZ -> RGB Matrix:\n";
                                     info +=
                                       String.
                                         format(
                                           "| %7.4f %7.4f %7.4f|\n",
                                           rx,
                                           ry,
                                           rz);
                                     info +=
                                       String.
                                         format(
                                           "| %7.4f %7.4f %7.4f|\n",
                                           gx,
                                           gy,
                                           gz);
                                     info +=
                                       String.
                                         format(
                                           "| %7.4f %7.4f %7.4f|\n",
                                           bx,
                                           by,
                                           bz);
                                     info +=
                                       "RGB -> XYZ Matrix:\n";
                                     info +=
                                       String.
                                         format(
                                           "| %7.4f %7.4f %7.4f|\n",
                                           xr,
                                           xg,
                                           xb);
                                     info +=
                                       String.
                                         format(
                                           "| %7.4f %7.4f %7.4f|\n",
                                           yr,
                                           yg,
                                           yb);
                                     info +=
                                       String.
                                         format(
                                           "| %7.4f %7.4f %7.4f|\n",
                                           zr,
                                           zg,
                                           zb);
                                     return info;
    }
    public static void main(String[] args) {
        System.
          out.
          println(
            SRGB.
              toString(
                ));
        System.
          out.
          println(
            HDTV.
              toString(
                ));
        System.
          out.
          println(
            WIDE_GAMUT.
              toString(
                ));
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
