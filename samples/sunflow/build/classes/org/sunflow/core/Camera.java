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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "7LaTRlyaA6nWGnBp9j8ehfHaoBYAAA==");
}
