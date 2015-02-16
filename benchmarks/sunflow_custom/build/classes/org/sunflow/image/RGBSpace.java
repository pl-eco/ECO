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
      1170388980000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
