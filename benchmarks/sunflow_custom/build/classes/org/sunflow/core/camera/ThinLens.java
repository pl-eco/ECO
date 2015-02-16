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
      1169271630000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "jBZsJsr/lZ/u+Mkdz62/LW4z/wHc/S7GqRQAAA==");
}
