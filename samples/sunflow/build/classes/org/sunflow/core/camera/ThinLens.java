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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "T+54bv1tcZv5Dy4LD+CTFAAA");
}
