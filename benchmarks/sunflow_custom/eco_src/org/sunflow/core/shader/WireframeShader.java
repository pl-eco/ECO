package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
public class WireframeShader implements Shader {
    private Color lineColor;
    private Color fillColor;
    private float width;
    private float cosWidth;
    public WireframeShader() { super();
                               lineColor = Color.BLACK;
                               fillColor = Color.WHITE;
                               width = (float) (Math.PI * 0.5 / 4096);
                               cosWidth = (float) Math.cos(width); }
    public boolean update(ParameterList pl, SunflowAPI api) { lineColor =
                                                                pl.
                                                                  getColor(
                                                                    "line",
                                                                    lineColor);
                                                              fillColor =
                                                                pl.
                                                                  getColor(
                                                                    "fill",
                                                                    fillColor);
                                                              width = pl.
                                                                        getFloat(
                                                                          "width",
                                                                          width);
                                                              cosWidth =
                                                                (float)
                                                                  Math.
                                                                  cos(
                                                                    width);
                                                              return true;
    }
    public Color getFillColor(ShadingState state) { return fillColor;
    }
    public Color getLineColor(ShadingState state) { return lineColor;
    }
    public Color getRadiance(ShadingState state) { Point3[] p =
                                                     new Point3[3];
                                                   if (!state.
                                                         getTrianglePoints(
                                                           p))
                                                       return this.
                                                         getFillColor(
                                                           state);
                                                   Point3 center =
                                                     state.
                                                     getPoint();
                                                   Matrix4 w2c =
                                                     state.
                                                     getWorldToCamera();
                                                   center =
                                                     w2c.
                                                       transformP(
                                                         center);
                                                   for (int i =
                                                          0;
                                                        i <
                                                          3;
                                                        i++)
                                                       p[i] =
                                                         w2c.
                                                           transformP(
                                                             state.
                                                               getInstance().
                                                               transformObjectToWorld(
                                                                 p[i]));
                                                   float cn =
                                                     1.0F /
                                                     (float)
                                                       Math.
                                                       sqrt(
                                                         center.
                                                           x *
                                                           center.
                                                             x +
                                                           center.
                                                             y *
                                                           center.
                                                             y +
                                                           center.
                                                             z *
                                                           center.
                                                             z);
                                                   for (int i =
                                                          0,
                                                          i2 =
                                                            2;
                                                        i <
                                                          3;
                                                        i2 =
                                                          i,
                                                          i++) {
                                                       float t =
                                                         (center.
                                                            x -
                                                            p[i].
                                                              x) *
                                                         (p[i2].
                                                            x -
                                                            p[i].
                                                              x);
                                                       t +=
                                                         (center.
                                                            y -
                                                            p[i].
                                                              y) *
                                                           (p[i2].
                                                              y -
                                                              p[i].
                                                                y);
                                                       t +=
                                                         (center.
                                                            z -
                                                            p[i].
                                                              z) *
                                                           (p[i2].
                                                              z -
                                                              p[i].
                                                                z);
                                                       t /=
                                                         p[i].
                                                           distanceToSquared(
                                                             p[i2]);
                                                       float projx =
                                                         (1 -
                                                            t) *
                                                         p[i].
                                                           x +
                                                         t *
                                                         p[i2].
                                                           x;
                                                       float projy =
                                                         (1 -
                                                            t) *
                                                         p[i].
                                                           y +
                                                         t *
                                                         p[i2].
                                                           y;
                                                       float projz =
                                                         (1 -
                                                            t) *
                                                         p[i].
                                                           z +
                                                         t *
                                                         p[i2].
                                                           z;
                                                       float n =
                                                         1.0F /
                                                         (float)
                                                           Math.
                                                           sqrt(
                                                             projx *
                                                               projx +
                                                               projy *
                                                               projy +
                                                               projz *
                                                               projz);
                                                       float dot =
                                                         projx *
                                                         center.
                                                           x +
                                                         projy *
                                                         center.
                                                           y +
                                                         projz *
                                                         center.
                                                           z;
                                                       if (dot *
                                                             n *
                                                             cn >=
                                                             cosWidth)
                                                           return this.
                                                             getLineColor(
                                                               state);
                                                   }
                                                   return this.
                                                     getFillColor(
                                                       state);
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {  }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169098062000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALUYa2wcxXl857cdznYSx+Rhx45JihNuCWqq1kZNXNeGSzbk" +
       "sBM7cQAz3p2722Rv\nd7M7Z19MBKFISQqCEpVKrQohQpHyaHgIWqWIFEIhlB" +
       "JVgkqlEhJpq0htpZZKVSWaqv3Rb2Z2b+/2\nHjGJOGln53a/93v27GeoxrHR" +
       "csWJ0v0WcaJDY3FsO0Qd0rHjbIdHU8rFmob4yS2GGUJVMgppKkUR\nWXEkFV" +
       "MsaaoU+/ZA1kZrLVPfn9RNGiVZGt2jb3DpbZY3FBGcOHau7dET1V0hVCOjCD" +
       "YMk2Kqmcaw\nTtIORS3yHjyDpQzVdEnWHDogowXEyKSHTMOh2KDOPvQwCsuo" +
       "1lIYTYq6ZY+5BMwlC9s4LXH2Upyz\nBQoLbUKxZhB1MMcOMNcVYoLYLt5oMT" +
       "QQqWcvx0EdLgFovTKntdC2SFUrfGr8aweOnw6jyCSKaMYY\nI6aAJhT4TaLm" +
       "NElPE9sZVFWiTqJWgxB1jNga1rU5znUStTla0sA0YxNnlDimPsMA25yMRWzO" +
       "03so\no2aF6WRnFGraORslNKKr3r+ahI6ToHa7r7ZQd4Q9BwUbNRDMTmCFeC" +
       "jVezUDPN4VxMjp2LsFAAC1\nLk1oysyxqjYwPEBtwpc6NpLSGLU1IwmgNWYG" +
       "uFC0tCxRZmsLK3txkkxR1BGEi4tXANXADcFQKFoc\nBOOUwEtLA17K88/a9s" +
       "+PnHr2zU08tqtVouhM/kZA6gwgjZIEsYmhEIF4NRN9JrYrszyEEAAvDgAL\n" +
       "mMFbzu2Q//pWl4BZVgJm2/QeotAp5Z6jXaMP3WWiMBOj3jIdjTm/QHOeDnH3" +
       "zUDWgqxtz1FkL6Pe\nywuj7+06eIb8LYQaY6hWMfVMGuKoVTHTlqYT+y5iEB" +
       "tTosZQAzHUIf4+hupgL0PIi6fbEgmH0Biq\n1vmjWpP/BxMlgAQzUQPsNSNh" +
       "ensL0xTfZy2EUB1cKApXExI/fqeoPyo5GSOhm7OSYyuSaSdz/xXT\nJpKTwi" +
       "qxpQnNJglIYjLG/0dZDFkUjUspM00krGBDM0wpqUHWKuZtKplh9+umnGVyt8" +
       "1WVbFCGExo\nHXLhblMH2Cnl5JUPDgxv+e4RESwswF2NKVoDDKMuwyhjGBUM" +
       "owGGqKqK81nEGAu3gdH3QvpCoWu+\ndez+zQ8e6QlDvFiz1WAxBtoDurnSDC" +
       "vmkJ/jMV4OFQi0jhd2H45ePblRBJpUvhSXxG768MVLx//V\n3BdCodJ1kmkJ" +
       "lbqRkYmz4pqrf73BzCpF/x+Pb33t40uffsXPMYp6i1K/GJOlbk/QH7apEBWK" +
       "oU/+\nxM2R8AQaPxpC1VAPoAZy+aG8dAZ5FKTwgFcOmS51MmpKmHYa6+yVV8" +
       "Maaco2Z/0nPFBa+H6hF9NL\n4Wp1g5zf2dvFFlvbRWAxbwe04OX26mO1t//+" +
       "fNNFbhavMkfyet8YoSLPW/1g2W4TAs8//WH8+z/4\n7PBuHiluqFBoiJlpXV" +
       "OygLLaR4EE16HIMEf27jDSpqolNDytExZx/4vcsv5nf3+qRbhGhyeeZ9dd\n" +
       "m4D//OZvoYOXHvh3JydTpbAG46vhgwltFvqUB20b72dyZB/97Yof/Qo/B/UP" +
       "ao6jzRFeRhDXDHE7\nStzufXyNBt6tZ0sP0F5XJvRLtPMp5cCZZE9m369f51" +
       "I34fy5IN8NW7E1IDzPllXMukuC2Xs3dlIA\n99UL99zXol/4L1CcBIoKtFFn" +
       "mw1pny1wogtdU/fJ2++0P/hRGIVGUKNuYnUE8/hHDRB4xElB2cla\nGzfx2G" +
       "qZrWcrVxlxIyx1DZDN+1cNwt1aPv1H2DDgZ87U9LpT8gfbnuMGKJv4JXphgM" +
       "7cmzuOXf0N\nvczp+BnIsLuzxTUVBigf9+sfz7TWvvJ8OoTqJlGL4o5441jP" +
       "sDifhInE8eY+GAML3hdOF6KVDuQq\nzPJg9uexDea+X8thz6DZvjmQ7s3M2s" +
       "u9jXfPT/cqxDcbOUovX9fkkrPOsrUZzMY+1MCaKqSFO6Yt\noWhJfvvQ0jDb" +
       "RPl7UUDYegdbNglfbygbE/3F0i5wpV1QRtoYWwZBKOjs+vUItfkLCtUB102u" +
       "UDeV\nESruClUzC8eNFAR1R/4BxdbSMOjM8DJ+5VDPL97f8fxh0foqxH4B1p" +
       "TyyB/+uDf8vbenBV4wwAPA\nRztP/Pm1K6OLRJkUE/GqoqE0H0dMxTyCIhYr" +
       "Gd2VOHDod9d2n3149LIrUVvhbDcM55+/7H+HrLnz\nyT+VGD8gmk1MA4659w" +
       "s6ZhlcEdcxkTKOecB1TL1iOhPMN+z/RIDxVAXG3CSr80pWVclw4yOUGJmY\n" +
       "9VaUm+C55Q7v/GfzIfzu/SG3HeyCaKamdZtOZoiex4qddFcUTFNb+ZnFr0aP" +
       "n/7JOfrR2n7hg77y\n0RRE7Os/PtfV//IT1zFDdQV0C5JunVl2bzilvR/ixy" +
       "pR3IqOY4VIA4UlrRHkydjG9oLCtrJwjhlw\nZxlvpimeY3zH+U04xO0a8lzY" +
       "WeRCriqB0x7r8h5Yez7YmLgPxmOczb4KbX6WLXAqqs1YKlRSDjNm\niWDbCR" +
       "V22jR1gg0/EM1rZYDXQ/mfPYUWWQ9Xj2uRnnlbpKowqFeUDGo4BrMPAYST+U" +
       "4FjQ+x5RGK\nmpOEjsy3Pvv6H7xR/Ve7+q/+EvV/uoL+z7DlSaG/PN+m6ev/" +
       "1I3ofztcfa7+fV+i/scq6P8CW35M\nURPoPwp4LLnnr/6zN6L+N+GKuerHrr" +
       "cgVFb/WppwNmcrmOdVtpymaIGjYApFJp4yqVtr8+pC9Yyp\nqb5VzszXKjCp" +
       "RQLHd3Z+6Sj65ie+UynyJw/d97n8u//wg2juW1KTjOoTGV3PHzHz9rUWMNC4" +
       "Nk1i\n4LT47Xyphii+KUABFBsu7RsC/i2KWoLwoDq75YP9EmIpDwyKprvLB7" +
       "pIURiA2PY9y3NSCz+/sFE7\nKkbtbIGxmGVWFfRL/hnW62kZ8SF2Stn54u6V" +
       "2Se2P80bZY2i47k5RqZRRnXiAJ7ri91lqXm0PkSv\nvDx+/qVveH2fH9AWua" +
       "fuovi+Q7yt4HzoxaVPvcNpi/Jz6tzPl/z0zpPHLof4ufv/ukEkzz0XAAA=");
}
