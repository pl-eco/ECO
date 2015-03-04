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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
