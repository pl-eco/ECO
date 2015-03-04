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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "cqmdj9YgaUiaEk0J85IP/wfldhJ+rCgAAA==");
}
