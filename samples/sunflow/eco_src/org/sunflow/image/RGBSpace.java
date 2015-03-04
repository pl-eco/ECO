package org.sunflow.image;
import org.sunflow.math.MathUtils;
final public class RGBSpace {
    final public static RGBSpace ADOBE = new RGBSpace(0.64F, 0.33F, 0.21F,
                                                      0.71F,
                                                      0.15F,
                                                      0.06F,
                                                      0.31271F,
                                                      0.32902F,
                                                      2.2F,
                                                      0);
    final public static RGBSpace APPLE = new RGBSpace(0.625F, 0.34F, 0.28F,
                                                      0.595F,
                                                      0.155F,
                                                      0.07F,
                                                      0.31271F,
                                                      0.32902F,
                                                      1.8F,
                                                      0);
    final public static RGBSpace NTSC = new RGBSpace(0.67F, 0.33F, 0.21F,
                                                     0.71F,
                                                     0.14F,
                                                     0.08F,
                                                     0.3101F,
                                                     0.3162F,
                                                     20.0F /
                                                       9.0F,
                                                     0.018F);
    final public static RGBSpace HDTV = new RGBSpace(0.64F, 0.33F, 0.3F, 0.6F,
                                                     0.15F,
                                                     0.06F,
                                                     0.31271F,
                                                     0.32902F,
                                                     20.0F /
                                                       9.0F,
                                                     0.018F);
    final public static RGBSpace SRGB = new RGBSpace(0.64F, 0.33F, 0.3F, 0.6F,
                                                     0.15F,
                                                     0.06F,
                                                     0.31271F,
                                                     0.32902F,
                                                     2.4F,
                                                     0.00304F);
    final public static RGBSpace CIE = new RGBSpace(0.735F, 0.265F, 0.274F,
                                                    0.717F,
                                                    0.167F,
                                                    0.009F,
                                                    1 /
                                                      3.0F,
                                                    1 /
                                                      3.0F,
                                                    2.2F,
                                                    0);
    final public static RGBSpace EBU = new RGBSpace(0.64F, 0.33F, 0.29F, 0.6F,
                                                    0.15F,
                                                    0.06F,
                                                    0.31271F,
                                                    0.32902F,
                                                    20.0F /
                                                      9.0F,
                                                    0.018F);
    final public static RGBSpace SMPTE_C = new RGBSpace(0.63F, 0.34F, 0.31F,
                                                        0.595F,
                                                        0.155F,
                                                        0.07F,
                                                        0.31271F,
                                                        0.32902F,
                                                        20.0F /
                                                          9.0F,
                                                        0.018F);
    final public static RGBSpace SMPTE_240M = new RGBSpace(0.63F, 0.34F, 0.31F,
                                                           0.595F,
                                                           0.155F,
                                                           0.07F,
                                                           0.31271F,
                                                           0.32902F,
                                                           20.0F /
                                                             9.0F,
                                                           0.018F);
    final public static RGBSpace WIDE_GAMUT = new RGBSpace(0.7347F, 0.2653F,
                                                           0.1152F,
                                                           0.8264F,
                                                           0.1566F,
                                                           0.0177F,
                                                           0.3457F,
                                                           0.3585F,
                                                           2.2F,
                                                           0);
    final private float gamma;
    final private float breakPoint;
    final private float slope;
    final private float slopeMatch;
    final private float segmentOffset;
    final private float xr;
    final private float yr;
    final private float zr;
    final private float xg;
    final private float yg;
    final private float zg;
    final private float xb;
    final private float yb;
    final private float zb;
    final private float xw;
    final private float yw;
    final private float zw;
    final private float rx;
    final private float ry;
    final private float rz;
    final private float gx;
    final private float gy;
    final private float gz;
    final private float bx;
    final private float by;
    final private float bz;
    final private float rw;
    final private float gw;
    final private float bw;
    final private int[] GAMMA_CURVE;
    final private int[] INV_GAMMA_CURVE;
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
                                                    (this.
                                                       gammaCorrect(
                                                         c) *
                                                       255 +
                                                       0.5F),
                                                  0,
                                                  255);
                                            INV_GAMMA_CURVE[i] =
                                              MathUtils.
                                                clamp(
                                                  (int)
                                                    (this.
                                                       ungammaCorrect(
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
    final public Color convertXYZtoRGB(XYZColor c) {
        return this.
          convertXYZtoRGB(
            c.
              getX(),
            c.
              getY(),
            c.
              getZ());
    }
    final public Color convertXYZtoRGB(float X,
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
    final public XYZColor convertRGBtoXYZ(Color c) {
        float[] rgb =
          c.
          getRGB();
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
    final public boolean insideGamut(float r,
                                     float g,
                                     float b) {
        return r >=
          0 &&
          g >=
          0 &&
          b >=
          0;
    }
    final public float gammaCorrect(float v) {
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
    final public float ungammaCorrect(float vp) {
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
    final public int rgbToNonLinear(int rgb) {
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
    final public int rgbToLinear(int rgb) {
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
    final public String toString() { String info =
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
              toString());
        System.
          out.
          println(
            HDTV.
              toString());
        System.
          out.
          println(
            WIDE_GAMUT.
              toString());
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170388980000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVbCZAU1Rl+M3sf7AXsInIuiwgsMyyyXGuiy+4CA7NH9gIX" +
       "ydrT82a2oae77e5Z\nZpeNYmlEJYpU1GglIqas4ohGUsagVUgkXkRigiTRlF" +
       "HjUSVWeZRWSiWJqeR/r7une3p6ZndZZqve\n657u9973/cf7+137+GcoR5HR" +
       "DFbxqEMSVjxNXR2MrOBgE88oSjc86mdfyinoOLRJEN3I5UduLqii\nUj+reI" +
       "OMyni5oNfX3BCT0WJJ5IfCvKh6cEz1bOfr9fY2+uuTGtx84HjFLY9lz3ajHD" +
       "8qZQRBVBmV\nE4UWHkcUFZX5tzODjDeqcrzXzylqgx9NwkI00iQKisoIqnIj" +
       "ugll+VGuxJI2VTTXb4B7AdwrMTIT\n8VJ4bweFhRYmy1hlOAEHG+NwULM2sS" +
       "bQ1ut1JpeGRvLJy14QhzIAqefEpdakTRJVyjrcu2Lk4JEs\nVNqHSjmhizTG" +
       "giQq4PWh4giOBLCsNAaDONiHygWMg11Y5hieG6aofahC4cICo0ZlrHRiReQH" +
       "ScEK\nJSphmWIaD/2omCUyyVFWFeW4jkIc5oPGr5wQz4RB7EpTbE3cdeQ5CF" +
       "jIATE5xLDYqJK9gxPA4rPt\nNeIy1myCAlA1L4LVATEOlS0w8ABVaLbkGSHs" +
       "7VJlTghD0RwxCigqmp6yUaJriWF3MGHcr6Jp9nId\n2isoVUAVQaqoaKq9GG" +
       "0JrDTdZiWLfRZXfn3H4Z+dvJb6dnYQszzhXwiVZtkqdeIQlrHAYq3ihajn\n" +
       "Pt910RluhKDwVFthrUzj/OM9/o9/O1src7lDmfbAdsyq/Wzb/tmdu9aLKIvQ" +
       "yJdEhSPGT5CcdocO\n/U1DTIJeWxlvkbz0GC+f73z5ut1H8SduVOhDuazIRy" +
       "PgR+WsGJE4HsvrsYBlRsVBHyrAQrCJvveh\nPLj3g8trT9tDIQWrPpTN00e5" +
       "Iv0NKgpBE0RFBXDPCSHRuJcYdYDexySE0CRIqAJSDtL+6FVFSzxe\nJSqEeH" +
       "GnV5FZryiH47+5CFjU27l+bRcYHnuI20gq8nkHxAj2MiwjcILoDXPQUVlxSR" +
       "APkut4GosR\ndhU7XS4S7uzdlodSG0Q+iOV+9tCHr460bLrzDs0liBvrcoHD" +
       "AoZHx/BQDI+BgVwu2vQUgqXZA7S5\nA/olRLDihV3bNt5wR3UWOIK0MxtUQY" +
       "pWgwQ6gRZWbDI7r4/GORY8aNrPt+7xXDh0jeZB3tQx1rF2\n0dknzhz8Z/Ei" +
       "N3I7B0AiGITgQtJMB4ma8cBWY+8yTu1/flfrU2+ceedKs/OoqCapTyfXJH2y" +
       "2m4C\nWWRxEKKc2fxjl5VmbUa9+90oGzo6BDfKH+LGLDtGQt9sMOIckSXPj4" +
       "pCohxhePLKCE6F6oAs7jSf\nUN8oo/eTwThFxFnJzXTde+mVvJ0qkbxS8yVi" +
       "bZsUNI5euDV36Zsnil6iajFCbqnlo9aFVa0Dl5vO\n0i1jDM/febDjx/d/tm" +
       "cr9RTdVVT40kUDPMfGoMoVZhXouTxED2LImh4hIga5EMcEeEw87tvS+XVP\n" +
       "f3pPmWYaHp4Ylq0dvQHz+WVr0e4z3/9mFm3GxZIvhymGWUyTZrLZcqMsM0OE" +
       "R+yWczMfeoV5GAIb\nBBOFG8Y0PhRSyQqh0jTryEPmIhDBBqkZP7y9+rnTPY" +
       "/s0Vx/YZrhhbVWP3vzP97bkbXvVECrZ4/i\ntsL7Zz320VMfdk7R1KR96uYl" +
       "fW2sdbTPHXWAUokYZG46BFr6xcVzH7+p812dUUVi0G6Bgc35oRfw\ngqvvft" +
       "8h4oD7iAy13fKxZpSblzrzIpp7iPdShSP6bg3JqoF6bQqlOgx++tmRo+Hq6I" +
       "2/f5aSLGKs\noyirb7cykqadMpLNIxqqsofEDYwyAOWWP992fRn//H+gxT5o" +
       "kYVBh9IuQwyOJfQMvXRO3lunXqi8\n4fUs5F6HCkErwXUMDSqoAHozVgYgfM" +
       "eka66lHbZsZz7JqciIKmG6roCY5ddCJa1jrSNDJzMc9Qdq\nD/tfbX+YKiBl" +
       "NHXwOVs7wyd7Dlx4TX2XtmOGNVJ7biz52wTDTbPuqjcGy3OPPRJxo7w+VMbq" +
       "A+Je\nho+S4NEH4zfFGCXDoDnhfeJYTBt4NMTD9gy701tg7QHV9FC4J6XJfb" +
       "Ethk4j2r4TUq4eQ3PtMdSF\n6M0GWqWG5gu0iJdF/J4TGDoWWwjhT6Gj5hg8" +
       "bmxuX9ui/ICERXSZJXS0iQId13As6XYxw+mIrB4Z\nh0icIp+Y2ND7C96a84" +
       "eypjNaRBtQ0XyLVvSSXp8wKLLUqBsYIQhjJi3AzXAE3CwzEoxBz7730bZ9\n" +
       "i8+/THq5pH0fSN5AMp/mdd9N6Z1NyXrL0/WWl0Jvmx30Ru79VGkka6UK6+jw" +
       "t9BC7TTvslHbMk5q\n90PK16nlp6B2w5ioZbd1dzWlYcZcBLMCnVlBCmbc2J" +
       "htaO7uTcNs+ziZ7SPfOp1ZYQpm8tiYdcF4\nMw0zZZzM9hijHePqwGzXmJhl" +
       "NfnSudnIOIndB6lYJ1acgthtYyPWsrYnDbEfjpPYg0if3RhXB2J3\nj4lYXl" +
       "drR3dLf7oucM84yf0UUolOriQFuQfGRK5QI7ds+dLWNPx+Mk5+P4JUqvMrTc" +
       "HvkbHx2+xr\nbulf39ja052G38Gx8yOTVbQIUrnOrzwFv8PJ/NyUH1hUkrlB" +
       "hqzsoJwwE4kw5PlyG6cjGeB0jGRH\nQSsBGTM7OkROUJ2gfzVO6NXGjXF1gH" +
       "46lTpI9jjRhMKL2hDBTuc3GaBzgmTHQRMUtZVR2QEn6Ocy\nAH1Kh56k4HAE" +
       "C6q2hOKE/rtxojcgbTqKjKsD+suj2cEdk524vJIBLq+R7DRADjlC/jEDkOcM" +
       "yGFH\nyD9nAPJNAzIWdoL8WwYg344r1hHynQxAvh9XrCPkBxmA/Ciu2IAT5P" +
       "kMQH4SV6wj5KcZgPwirlhH\nyC/HCTkP0hQdckoKyK9GjxE7nbh8nQEu35Ls" +
       "G6JxR8j/XnpIl8uAHHaCdLkvwshTdcipKSBzR9W4\nHHPikpcBLpNIlk8gh5" +
       "wgSzIAWRGHHHaCnJwByCoDMuyo2GkZgJwRh3RU7MwMQM6NQzoqtjoDkFcY\n" +
       "kAFHxS7IAOTiOKSjYmszALk0Dumo2LqLCEuVOmRlCsj60WOEY7xakQEuV5Ns" +
       "JfErR8jvZACy0YAM\nOEKuHSdkDaQqHbIqBeS60TReBBPL1sb+pp7O3hayy2" +
       "NZ96M7G2SN8ci9zZM7V2+9lW6uFTA8xyht\n5rqomwuSO1dMRvNTrzDHG+tn" +
       "F2w7/sWpk3gBXQzP55ReRm6Uww6b15Y6XzJHceuboQN0gyw7wCja\nSqx91z" +
       "95Uz9hr5525xJJ0/NS/VqvoiyoJkkSAnVQ6eqW1tXXrQB1VIA6yOEPDxfU17" +
       "abz60LHA0J\nR4NuikfRG0kdXRsF9IlFPVmipJDdbcsxEr2lmnZJIfuDkywg" +
       "vuaRYxuL8x/dezttX9dtgWWnXP+d\nN8jIbdbtE435spVXrVwKLtZ1yTaT19" +
       "TVL6+tW7lk2SoVlXVv8HV5LB5DXKwz0YluklFlstaIrLqr\nEucFqUvMVWuy" +
       "MWR9CdJkd7Y0Ntv6x/px9o8rkb4WY1wd+gczWv8o9bX19lv7CKm1SSKe5GpP" +
       "8pmV\n9HWX1Rp1qy+tNVZcRaxx1TIVTaHWsPEj+OFk2uTxNps+A2n0GUunl4" +
       "XWnSVE9r1mpjp2QncF92z5\nsvh25sVtbn1XboWKClRRWsLjQcxbmiKbpDMT" +
       "Tgq00s5rbgrddeQXx9XXF6/R9hcXpQ439oqL1hwc\nnr3myb0XcT5gtk02e9" +
       "Plg5d/L2uAO007rL7HlHSGKLFSQ+LOUiHwicpCd8L+0py4M9OlsGWQON2Z\n" +
       "Obszm4ZNbzFzk9SlH80gv6scT3tsua6vSeRFOmh33Wyrbtljdd1KsmFwOVYU" +
       "BrGsQkVVNNbuoe2q\n5LZpw6Yj7hqtY8c9jZQeTFRMIyQjekQmrpgscxnWtt" +
       "Hs2pdGCftJdtdElLB3Ikog3qHqSlAvuXek\nIk8lfziNVh4l2YOmVkAhqgiq" +
       "GYPbmYp5aCKKuQbSiK6YkYx6xxNp9PAkyQ7DV4ETFC6I1zORqGof\ng+QFRJ" +
       "HHjGAKPuryeTrBayHt1gXffck8whT3RBpxT5LsuIqK6c5AkyjLmNVWh03hnp" +
       "mIcF5Ie3Xh\n9mZAuNNphHuVZC+oqCQqpBHvxYna7gFdvAcupXiudirCX9KI" +
       "9wbJ/gTiyeFAt9gmCuSgJCPTyqZ4\nZyci3kJIB3TxDmRAvPfSiPcByf4OPZ" +
       "GK5yjb2xORrRrSIV22QxOXzUr90zTvPifZeRXlq6J29NiI\nsGXmKNvygsr5" +
       "8UXKGd+qe1aX89nxyKlva6b+4Lg2xZFmGBpNiZRSzMSRef3yFfbZnF9kGd7X" +
       "/Oip\nonP7oys26kNTVw/J/ndpR+yr6muXrVqyvA5mNYwcVijOv1Mb051DCn" +
       "wFpSMwSLV/JrIHRS5oGnHU\n9er4YTBwDoMXOeAzLek4v3YEnfW/tev6r/1/" +
       "/Zc20zaOiRfBDDQU5XnreSjLfa4k4xBHDV+knY6i\nMyR3qYrKk77xKsqhV8" +
       "LPXaIVrABjWQrC51C/sxaaCjN1KERuKyUH02snvRInJ0TWeQnzBPo/E8ZY\n" +
       "Pqr910Q/u+WJrXNie7vvpROEHJZnhumqWCHMtLVDtfH5wNyUrRltnUXHnuw9" +
       "8cvVxnyHng+cYukW\n1p6lbQJMSWdHmIM4n2RtiUgqPXs6/EzVr68+dOBdei" +
       "JL+j8Wq0Cg6jIAAA==");
}
