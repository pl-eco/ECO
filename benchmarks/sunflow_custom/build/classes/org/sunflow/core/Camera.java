package org.sunflow.core;
import org.sunflow.SunflowAPI;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class Camera implements RenderObject {
    private final CameraLens lens;
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
                                                                      if (!updateCameraMatrix(
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
                                                                          inverse(
                                                                            );
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
                  length(
                    ) ==
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
                                           normalize(
                                             );
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
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1170608332000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
