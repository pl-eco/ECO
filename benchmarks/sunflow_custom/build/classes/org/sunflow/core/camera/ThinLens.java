package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class ThinLens implements CameraLens {
    private float au;
    private float av;
    private float aspect;
    private float fov;
    private float focusDistance;
    private float lensRadius;
    private int lensSides;
    private float lensRotation;
    private float lensRotationRadians;
    public ThinLens() { super();
                        focusDistance = 1;
                        lensRadius = 0;
                        fov = 90;
                        aspect = 1;
                        lensSides = 0;
                        lensRotation = (lensRotationRadians = 0); }
    public boolean update(ParameterList pl, SunflowAPI api) { fov = pl.getFloat(
                                                                         "fov",
                                                                         fov);
                                                              aspect = pl.
                                                                         getFloat(
                                                                           "aspect",
                                                                           aspect);
                                                              focusDistance =
                                                                pl.
                                                                  getFloat(
                                                                    "focus.distance",
                                                                    focusDistance);
                                                              lensRadius =
                                                                pl.
                                                                  getFloat(
                                                                    "lens.radius",
                                                                    lensRadius);
                                                              lensSides =
                                                                pl.
                                                                  getInt(
                                                                    "lens.sides",
                                                                    lensSides);
                                                              lensRotation =
                                                                pl.
                                                                  getFloat(
                                                                    "lens.rotation",
                                                                    lensRotation);
                                                              update(
                                                                );
                                                              return true;
    }
    private void update() { au = (float) Math.
                                   tan(
                                     Math.
                                       toRadians(
                                         fov *
                                           0.5F)) *
                                   focusDistance;
                            av = au / aspect;
                            lensRotationRadians =
                              (float)
                                Math.
                                toRadians(
                                  lensRotation);
    }
    public Ray getRay(float x, float y, int imageWidth,
                      int imageHeight,
                      double lensX,
                      double lensY,
                      double time) { float du =
                                       -au +
                                       2.0F *
                                       au *
                                       x /
                                       (imageWidth -
                                          1.0F);
                                     float dv =
                                       -av +
                                       2.0F *
                                       av *
                                       y /
                                       (imageHeight -
                                          1.0F);
                                     float eyeX;
                                     float eyeY;
                                     if (lensSides <
                                           3) {
                                         double angle;
                                         double r;
                                         double r1 =
                                           2 *
                                           lensX -
                                           1;
                                         double r2 =
                                           2 *
                                           lensY -
                                           1;
                                         if (r1 >
                                               -r2) {
                                             if (r1 >
                                                   r2) {
                                                 r =
                                                   r1;
                                                 angle =
                                                   0.25 *
                                                     Math.
                                                       PI *
                                                     r2 /
                                                     r1;
                                             }
                                             else {
                                                 r =
                                                   r2;
                                                 angle =
                                                   0.25 *
                                                     Math.
                                                       PI *
                                                     (2 -
                                                        r1 /
                                                        r2);
                                             }
                                         }
                                         else {
                                             if (r1 <
                                                   r2) {
                                                 r =
                                                   -r1;
                                                 angle =
                                                   0.25 *
                                                     Math.
                                                       PI *
                                                     (4 +
                                                        r2 /
                                                        r1);
                                             }
                                             else {
                                                 r =
                                                   -r2;
                                                 if (r2 !=
                                                       0)
                                                     angle =
                                                       0.25 *
                                                         Math.
                                                           PI *
                                                         (6 -
                                                            r1 /
                                                            r2);
                                                 else
                                                     angle =
                                                       0;
                                             }
                                         }
                                         r *=
                                           lensRadius;
                                         eyeX =
                                           (float)
                                             (Math.
                                                cos(
                                                  angle) *
                                                r);
                                         eyeY =
                                           (float)
                                             (Math.
                                                sin(
                                                  angle) *
                                                r);
                                     }
                                     else {
                                         lensY *=
                                           lensSides;
                                         float side =
                                           (int)
                                             lensY;
                                         float offs =
                                           (float)
                                             lensY -
                                           side;
                                         float dist =
                                           (float)
                                             Math.
                                             sqrt(
                                               lensX);
                                         float a0 =
                                           (float)
                                             (side *
                                                Math.
                                                  PI *
                                                2.0F /
                                                lensSides +
                                                lensRotationRadians);
                                         float a1 =
                                           (float)
                                             ((side +
                                                 1.0F) *
                                                Math.
                                                  PI *
                                                2.0F /
                                                lensSides +
                                                lensRotationRadians);
                                         eyeX =
                                           (float)
                                             ((Math.
                                                 cos(
                                                   a0) *
                                                 (1.0F -
                                                    offs) +
                                                 Math.
                                                 cos(
                                                   a1) *
                                                 offs) *
                                                dist);
                                         eyeY =
                                           (float)
                                             ((Math.
                                                 sin(
                                                   a0) *
                                                 (1.0F -
                                                    offs) +
                                                 Math.
                                                 sin(
                                                   a1) *
                                                 offs) *
                                                dist);
                                         eyeX *=
                                           lensRadius;
                                         eyeY *=
                                           lensRadius;
                                     }
                                     float eyeZ =
                                       0;
                                     float dirX =
                                       du;
                                     float dirY =
                                       dv;
                                     float dirZ =
                                       -focusDistance;
                                     return new Ray(
                                       eyeX,
                                       eyeY,
                                       eyeZ,
                                       dirX -
                                         eyeX,
                                       dirY -
                                         eyeY,
                                       dirZ -
                                         eyeZ);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
