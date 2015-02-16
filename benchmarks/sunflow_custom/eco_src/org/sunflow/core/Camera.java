package org.sunflow.core;
import org.sunflow.SunflowAPI;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class Camera implements RenderObject {
    final private CameraLens lens;
    private Matrix4[] c2w;
    private Matrix4[] w2c;
    public Camera(CameraLens lens) { super();
                                     this.lens = lens;
                                     c2w = (w2c = (new Matrix4[1])); }
    public boolean update(ParameterList pl, SunflowAPI api) { int n = pl.
                                                                getInt(
                                                                  "transform.steps",
                                                                  0);
                                                              if (n <=
                                                                    0) {
                                                                  this.
                                                                    updateCameraMatrix(
                                                                      -1,
                                                                      pl);
                                                              }
                                                              else {
                                                                  c2w =
                                                                    (new Matrix4[n]);
                                                                  for (int i =
                                                                         0;
                                                                       i <
                                                                         n;
                                                                       i++) {
                                                                      if (!this.
                                                                            updateCameraMatrix(
                                                                              i,
                                                                              pl)) {
                                                                          UI.
                                                                            printError(
                                                                              Module.
                                                                                CAM,
                                                                              "Camera matrix for step %d was not specified!",
                                                                              i +
                                                                                1);
                                                                          return false;
                                                                      }
                                                                  }
                                                              }
                                                              w2c =
                                                                (new Matrix4[c2w.
                                                                               length]);
                                                              for (int i =
                                                                     0;
                                                                   i <
                                                                     c2w.
                                                                       length;
                                                                   i++) {
                                                                  if (c2w[i] !=
                                                                        null) {
                                                                      w2c[i] =
                                                                        c2w[i].
                                                                          inverse();
                                                                      if (w2c[i] ==
                                                                            null) {
                                                                          UI.
                                                                            printError(
                                                                              Module.
                                                                                CAM,
                                                                              "Camera matrix is not invertible");
                                                                          return false;
                                                                      }
                                                                  }
                                                                  else
                                                                      w2c[i] =
                                                                        null;
                                                              }
                                                              return lens.
                                                                update(
                                                                  pl,
                                                                  api);
    }
    private boolean updateCameraMatrix(int index,
                                       ParameterList pl) {
        String offset =
          index <
          0
          ? ""
          : String.
          format(
            "[%d]",
            index);
        if (index <
              0)
            index =
              0;
        Matrix4 transform =
          pl.
          getMatrix(
            String.
              format(
                "transform%s",
                offset),
            null);
        if (transform ==
              null) {
            Point3 eye =
              pl.
              getPoint(
                String.
                  format(
                    "eye%s",
                    offset),
                null);
            Point3 target =
              pl.
              getPoint(
                String.
                  format(
                    "target%s",
                    offset),
                null);
            Vector3 up =
              pl.
              getVector(
                String.
                  format(
                    "up%s",
                    offset),
                null);
            if (eye !=
                  null &&
                  target !=
                  null &&
                  up !=
                  null) {
                c2w[index] =
                  Matrix4.
                    fromBasis(
                      OrthoNormalBasis.
                        makeFromWV(
                          Point3.
                            sub(
                              eye,
                              target,
                              new Vector3(
                                )),
                          up));
                c2w[index] =
                  Matrix4.
                    translation(
                      eye.
                        x,
                      eye.
                        y,
                      eye.
                        z).
                    multiply(
                      c2w[index]);
            }
            else {
                return offset.
                  length() ==
                  0;
            }
        }
        else
            c2w[index] =
              transform;
        return true;
    }
    public Ray getRay(float x, float y, int imageWidth,
                      int imageHeight,
                      double lensX,
                      double lensY,
                      double time) { Ray r =
                                       lens.
                                       getRay(
                                         x,
                                         y,
                                         imageWidth,
                                         imageHeight,
                                         lensX,
                                         lensY,
                                         time);
                                     if (r !=
                                           null) {
                                         if (c2w.
                                               length ==
                                               1) {
                                             r =
                                               r.
                                                 transform(
                                                   c2w[0]);
                                         }
                                         else {
                                             double nt =
                                               time *
                                               (c2w.
                                                  length -
                                                  1);
                                             int idx0 =
                                               (int)
                                                 nt;
                                             int idx1 =
                                               Math.
                                               min(
                                                 idx0 +
                                                   1,
                                                 c2w.
                                                   length -
                                                   1);
                                             r =
                                               r.
                                                 transform(
                                                   Matrix4.
                                                     blend(
                                                       c2w[idx0],
                                                       c2w[idx1],
                                                       (float)
                                                         (nt -
                                                            idx0)));
                                         }
                                         r.
                                           normalize();
                                     }
                                     return r;
    }
    Ray getRay(Point3 p) { return new Ray(
                             c2w ==
                               null
                               ? new Point3(
                               0,
                               0,
                               0)
                               : c2w[0].
                               transformP(
                                 new Point3(
                                   0,
                                   0,
                                   0)),
                             p); }
    Matrix4 getCameraToWorld() { return c2w ==
                                   null
                                   ? Matrix4.
                                       IDENTITY
                                   : c2w[0];
    }
    Matrix4 getWorldToCamera() { return w2c ==
                                   null
                                   ? Matrix4.
                                       IDENTITY
                                   : w2c[0];
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170608332000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAK1ZC2wUxxke3/ltJ37xCg8bDITyugtgasBIiWPsYDiDYxsI" +
       "JtQZ783ZC3u7m909\n++wgmgQJSFDToLZSKxVCK1QeTQJVGtFHQkEJbRqaKk" +
       "RKIkWFNEJqU6VUjapSoqZS/39m93Zv7wEh\nsXSzszP//8/8r2/+WT9/jRSZ" +
       "BpkumSFrTGdmqK23mxomi7Yp1DT7YGhAulBU1n1svaoFSEGEBOSo\nRaoikh" +
       "mOUouG5Wi4c01L0iALdU0ZG1I0K8SSVmiHstyWty6yPEPglsNnap84WtgQIE" +
       "URUkVVVbOo\nJWtqu8LipkWqIzvoCA0nLFkJR2TTaomQO5iaiLdpqmlR1TIf" +
       "JbtJMEKKdQllWmRWxFk8DIuHdWrQ\neJgvH+7my4KEOoNZVFZZtDW1HHAuSu" +
       "eEbdt8PZnUIKQUJzeDOnwHoPXMlNZC2wxV9eDxzV/fdeRE\nkFT1kypZ7UVh" +
       "EmhiwXr9pDLO4oPMMFujURbtJzUqY9FeZshUkcf5qv2k1pSHVGolDGb2MFNT" +
       "RpCw\n1kzozOBrOoMRUimhTkZCsjQjZaOYzJSo81YUU+gQqD3JVVuo24HjoG" +
       "C5DBszYlRiDkvhTlkFjzf4\nOVI6zlkPBMBaEmfWsJZaqlClMEBqhS8Vqg6F" +
       "ey1DVoeAtEhLwCoWmZpTKNpap9JOOsQGLDLFT9ct\npoCqjBsCWSwy0U/GJY" +
       "GXpvq85PHPwknX9x//4dn7eGwXRpmk4P7Lganex9TDYsxgqsQE441E6Lud\n" +
       "WxPTA4QA8UQfsaBpnXtmU+Tj3zQImmlZaDYO7mCSNSBtONjQ89gDGgniNkp1" +
       "zZTR+Wma83Totmda\nkjpk7aSURJwMOZPnen679fGT7JMAKe8kxZKmJOIQRz" +
       "WSFtdlhRkPMJUZ1GLRTlLG1Ggbn+8kJdCP\nQMiL0Y2xmMmsTlKo8KFijb+D" +
       "iWIgAk1UBn1ZjWlOX6fWMO8ndUJICfzIQvhVE/HHnxZZEAqbCTWm\naKNh05" +
       "DCmjGUepc0g4XbIGgMGsKY0S3SER7W4ixMJarKqhYekiFLJW1xlI3g85YlJX" +
       "FftaMFBQh0\n/oRVINbXakqUGQPSsatv7mpf/9R+EQwYwLZGFpkMC4TsBUK4" +
       "QEgsQAoKuNwJuJBwAxhxJ6QjAFfl\n/N7t6x7Z3xgE/+ujhWABJG2Evdurt0" +
       "tam5uznRzeJAicKT/eti9049i9InDCuaE1K3fF2y9cPPKv\nygUBEsiOe6gV" +
       "IG85iulGsEzh2Rx/pmST/4+nu1567+Llr7k5Y5E5GamcyYmp2Oi3v6FJLArg" +
       "5oo/\neldVcAvZfDBACiG/AdP4/gEu6v1rpKVkiwNvqEtJhFTENCNOFZxyMK" +
       "ncGja0UXeEB0Y179eBcyow\nRmvgN9EOWv7E2Yk6tpNEIKG3fVpw+Lyxp/ie" +
       "91+puMDN4iBtlecs62WWyNsaN1j6DMZg/PL3u7/z\nvWv7tvFIsUPFggMuMa" +
       "jIUhJY7nZZIGEVAA105JxNalyLyjGZDioMI+7zqrlLXv77M9XCNQqMOJ5d\n" +
       "dHMB7vhd95PHL37jP/VcTIGEB4arhksmtKlzJbcaBh3DfSSfeGfGD35HDwGe" +
       "AYaY8jjjsFBgJwFu\narJFpuVIqghTTW7rMKdcwNsQOoPzEz63DJtGWH9Rjv" +
       "TIcoQPSLtODjUmHv39L7lmFdRbC3hd1UX1\nFhEd2MxGD0z2Z/haag4DXdO5" +
       "DQ9XK+f+CxL7QaIER6e50QA8SaY52qYuKvng/GuTHrkUJIEOUq5o\nNNpBeY" +
       "6QMghOZg4DFCX1e+8ToDla6kBnknAjTLUNkPS8BWFz83NDRAcWAG52DQwuOh" +
       "55c+MhboCc\n4JDl/PPJGT+76fCNt6wrXI6bpcg9K5mJs1A0ubwr3hupKT79" +
       "XDxASvpJtWSXdZupksBc6IcqxHRq\nPSj90ubTKwpxfLakUGi6HyE8y/rxwc" +
       "V36CM19it9kFCL1m60YcGBhzRIKCC808pZ5vB2nkjggAUL\nyiqFfZXohjxC" +
       "seSDpITgvpUEEFiDbRM29wuXN+cMjdWpTVfi6FRn984zy6YjmZsuwH4H7DMo" +
       "LR1F\npPNcDHh2I3qeeHZNXc/KbXv4AVMGtSo1N7jGhBsC9gogCubmDsuUsA" +
       "Fp3vYz/zx/ls3jGVQqm+C0\nVmMoS93m4fmUnmRd78cO80OicJCawn3+gjez" +
       "nk0rU7m370w3XF0+wzmOm+J1XBxKn1AXheo22ZSC\nKi/UrcZmk2P1rVlCBf" +
       "vt2MwH0xdDiAxZw2D9Kd5rmSHHobwb4Yfd1b2Nr76x6bl9okDIk/1pXAPS\n" +
       "Nz/8887gt88PCj5/ivuID9Yf/ctLV3smiMNE3ANmZ5TiXh5xF+BWrdIRNGfl" +
       "W4FTv75w1vO7e67Y\nO6pNr2jb4db317HX2LzV3/ooS1EWBO/iS5+eTNk9IA" +
       "zqOEocTggWUPVrKsNzzpkTNZushVI3LphM\nZnjQIDPSKrYuHkAumj194qdn" +
       "rEsLVwkNFuT2hZ9xwaoj4w2rTh24jTqtwWdXv+iakWkPBoflNwL8\nKibAMe" +
       "MKl87Ukg6J5bCfhKH2pQHjTBHI3ObY3J3nfDbyzHGvgQOLJPSJcCHYuSF7jd" +
       "Ie1y1eVYz/\nYvLPVx87fAUtrSfhOlHOsWfpsuYVS4G9FrIFP0qE5Kh9Wq15" +
       "p2PwZEw9GeV2KOfQ0IostpJlfMSD\nXUFNN/HW5fm8YUuas1E3sYC9w7NI55" +
       "pdp9dVlv7owF4u3wa+Ms8Nzn4vGaHGBm/kVvCNL2lesXLp\nEot0fzX3nFVL" +
       "li1Z1LRi8XIQWdq3trM3BBiOS9pwvhtujJkmQsWIi3eg4p1uzmACeidh64U9" +
       "7a1r\nfIdT120cTnW22Loch9M+bDbAzkeXSpz7wUyczinjpjit63pa/AjgHk" +
       "vzTPNX6Jmme9I9A0rhgs8I\n/bC7x2fT/XlsmnSTb2p6ST0jo6Logds8M0Sl" +
       "hJA8I9fHEA7H+x76tHIvfX17wM5UqP3LLE1frLAR\npngWLOZ9Jf3u1GK71n" +
       "Fx5t0pG2j4ELs+QwMOfQzOccQAh2ySl6xXPFu7O/kyR/JAz0+wOQSHbEKH\n" +
       "RBBnwXb7/BiESm1Q0xRGVdcPh28W205N7rMID9I2+DXbFmnOaZEN2S2Cr/QW" +
       "zcKF/SyP3i9j8yKU\nz0JvEZwiGXDmuKvwqdtUuELoR8iArfDALYdACZdY4n" +
       "cG1Osa5WfFWW4L1yAeouKoBrdkDq3necOX\neTWPKS5g8yvgHGJWDx1LlQOZ" +
       "qWNPcrP8+jbNwgenwG/MNstYVrNkFoW2JFcH3+15cga2dWtQEC3j\n8i7l0f" +
       "9dbP7wRfV/68voXw+/J239n8wZFn/Ms+kP88x9hM2fLFINConA7tO2aIZ9Db" +
       "hpuc7Vu/xl\n1Ttgq3fgttT7JM/cNWw+Fupxtfo0oeUXUO9vt6oe3j+EdKzI" +
       "pmT8f0N8k5ciHzz28PXIu5+J+5fz\n3bwCSp9YQlG8V2tPv1g3WEzgTYW4aO" +
       "v8cR108wcfFBr44Hv7tyD7DE5nDxmAtd3zEn0OJyoQYfd/\numOgareicQ5C" +
       "r86o6ey0up3/C8mprRPin0gD0kMvbJuZPND3LC/YoXal4+MophwKPPGxMVWf" +
       "z8op\nzZH1Njl9avMrL650Dlr+oWmCBwvS4q1JzOb2IU6M6v8Hztn8/s4bAA" +
       "A=");
}
