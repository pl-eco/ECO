package org.sunflow.core.light;
import org.sunflow.SunflowAPI;
import org.sunflow.core.LightSample;
import org.sunflow.core.LightSource;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.image.Color;
import org.sunflow.math.MathUtils;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public class TriangleMeshLight extends TriangleMesh implements Shader {
    private Color radiance;
    private int numSamples;
    public TriangleMeshLight() { super();
                                 radiance = Color.WHITE;
                                 numSamples = 4; }
    public boolean update(ParameterList pl, SunflowAPI api) { radiance = pl.
                                                                           getColor(
                                                                             "radiance",
                                                                             radiance);
                                                              numSamples =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    numSamples);
                                                              return super.
                                                                update(
                                                                  pl,
                                                                  api);
    }
    public void init(String name, SunflowAPI api) { api.geometry(
                                                          name,
                                                          this);
                                                    api.shader(
                                                          name +
                                                          ".shader",
                                                          this);
                                                    api.parameter(
                                                          "shaders",
                                                          name +
                                                          ".shader");
                                                    api.instance(
                                                          name +
                                                          ".instance",
                                                          name);
                                                    for (int i =
                                                           0,
                                                           j =
                                                             0;
                                                         i <
                                                           triangles.
                                                             length;
                                                         i +=
                                                           3,
                                                           j++) {
                                                        TriangleLight t =
                                                          new TriangleLight(
                                                          j);
                                                        String lname =
                                                          String.
                                                          format(
                                                            "%s.light[%d]",
                                                            name,
                                                            j);
                                                        api.
                                                          light(
                                                            lname,
                                                            t);
                                                    } }
    private class TriangleLight implements LightSource {
        private int tri3;
        private float area;
        private Vector3 ng;
        TriangleLight(int tri) { super();
                                 tri3 = 3 * tri;
                                 int a = triangles[tri3 +
                                                     0];
                                 int b = triangles[tri3 +
                                                     1];
                                 int c = triangles[tri3 +
                                                     2];
                                 Point3 v0p = getPoint(a);
                                 Point3 v1p = getPoint(b);
                                 Point3 v2p = getPoint(c);
                                 ng = Point3.normal(v0p, v1p,
                                                    v2p);
                                 area = 0.5F * ng.length(
                                                    );
                                 ng.normalize(); }
        public boolean update(ParameterList pl, SunflowAPI api) {
            return true;
        }
        public int getNumSamples() { return numSamples; }
        private final boolean intersectTriangleKensler(Ray r) {
            int a =
              3 *
              triangles[tri3 +
                          0];
            int b =
              3 *
              triangles[tri3 +
                          1];
            int c =
              3 *
              triangles[tri3 +
                          2];
            float edge0x =
              points[b +
                       0] -
              points[a +
                       0];
            float edge0y =
              points[b +
                       1] -
              points[a +
                       1];
            float edge0z =
              points[b +
                       2] -
              points[a +
                       2];
            float edge1x =
              points[a +
                       0] -
              points[c +
                       0];
            float edge1y =
              points[a +
                       1] -
              points[c +
                       1];
            float edge1z =
              points[a +
                       2] -
              points[c +
                       2];
            float nx =
              edge0y *
              edge1z -
              edge0z *
              edge1y;
            float ny =
              edge0z *
              edge1x -
              edge0x *
              edge1z;
            float nz =
              edge0x *
              edge1y -
              edge0y *
              edge1x;
            float v =
              r.
              dot(
                nx,
                ny,
                nz);
            float iv =
              1 /
              v;
            float edge2x =
              points[a +
                       0] -
              r.
                ox;
            float edge2y =
              points[a +
                       1] -
              r.
                oy;
            float edge2z =
              points[a +
                       2] -
              r.
                oz;
            float va =
              nx *
              edge2x +
              ny *
              edge2y +
              nz *
              edge2z;
            float t =
              iv *
              va;
            if (t <=
                  0)
                return false;
            float ix =
              edge2y *
              r.
                dz -
              edge2z *
              r.
                dy;
            float iy =
              edge2z *
              r.
                dx -
              edge2x *
              r.
                dz;
            float iz =
              edge2x *
              r.
                dy -
              edge2y *
              r.
                dx;
            float v1 =
              ix *
              edge1x +
              iy *
              edge1y +
              iz *
              edge1z;
            float beta =
              iv *
              v1;
            if (beta <
                  0)
                return false;
            float v2 =
              ix *
              edge0x +
              iy *
              edge0y +
              iz *
              edge0z;
            if ((v1 +
                   v2) *
                  v >
                  v *
                  v)
                return false;
            float gamma =
              iv *
              v2;
            if (gamma <
                  0)
                return false;
            r.
              setMax(
                t -
                  0.001F);
            return true;
        }
        public void getSamples(ShadingState state) { if (numSamples ==
                                                           0)
                                                         return;
                                                     Vector3 n =
                                                       state.
                                                       getNormal(
                                                         );
                                                     Point3 p =
                                                       state.
                                                       getPoint(
                                                         );
                                                     Vector3 p0 =
                                                       Point3.
                                                       sub(
                                                         getPoint(
                                                           triangles[tri3 +
                                                                       0]),
                                                         p,
                                                         new Vector3(
                                                           ));
                                                     if (Vector3.
                                                           dot(
                                                             p0,
                                                             ng) >=
                                                           0)
                                                         return;
                                                     Vector3 p1 =
                                                       Point3.
                                                       sub(
                                                         getPoint(
                                                           triangles[tri3 +
                                                                       1]),
                                                         p,
                                                         new Vector3(
                                                           ));
                                                     Vector3 p2 =
                                                       Point3.
                                                       sub(
                                                         getPoint(
                                                           triangles[tri3 +
                                                                       2]),
                                                         p,
                                                         new Vector3(
                                                           ));
                                                     if (Vector3.
                                                           dot(
                                                             p0,
                                                             n) <=
                                                           0 &&
                                                           Vector3.
                                                           dot(
                                                             p1,
                                                             n) <=
                                                           0 &&
                                                           Vector3.
                                                           dot(
                                                             p2,
                                                             n) <=
                                                           0)
                                                         return;
                                                     p0.normalize(
                                                          );
                                                     p1.normalize(
                                                          );
                                                     p2.normalize(
                                                          );
                                                     float dot =
                                                       Vector3.
                                                       dot(
                                                         p2,
                                                         p0);
                                                     Vector3 h =
                                                       new Vector3(
                                                       );
                                                     h.x =
                                                       p2.
                                                         x -
                                                         dot *
                                                         p0.
                                                           x;
                                                     h.y =
                                                       p2.
                                                         y -
                                                         dot *
                                                         p0.
                                                           y;
                                                     h.z =
                                                       p2.
                                                         z -
                                                         dot *
                                                         p0.
                                                           z;
                                                     float hlen =
                                                       h.
                                                       length(
                                                         );
                                                     if (hlen >
                                                           1.0E-6F)
                                                         h.
                                                           div(
                                                             hlen);
                                                     else
                                                         return;
                                                     Vector3 n0 =
                                                       Vector3.
                                                       cross(
                                                         p0,
                                                         p1,
                                                         new Vector3(
                                                           ));
                                                     float len0 =
                                                       n0.
                                                       length(
                                                         );
                                                     if (len0 >
                                                           1.0E-6F)
                                                         n0.
                                                           div(
                                                             len0);
                                                     else
                                                         return;
                                                     Vector3 n1 =
                                                       Vector3.
                                                       cross(
                                                         p1,
                                                         p2,
                                                         new Vector3(
                                                           ));
                                                     float len1 =
                                                       n1.
                                                       length(
                                                         );
                                                     if (len1 >
                                                           1.0E-6F)
                                                         n1.
                                                           div(
                                                             len1);
                                                     else
                                                         return;
                                                     Vector3 n2 =
                                                       Vector3.
                                                       cross(
                                                         p2,
                                                         p0,
                                                         new Vector3(
                                                           ));
                                                     float len2 =
                                                       n2.
                                                       length(
                                                         );
                                                     if (len2 >
                                                           1.0E-6F)
                                                         n2.
                                                           div(
                                                             len2);
                                                     else
                                                         return;
                                                     float cosAlpha =
                                                       MathUtils.
                                                       clamp(
                                                         -Vector3.
                                                           dot(
                                                             n2,
                                                             n0),
                                                         -1.0F,
                                                         1.0F);
                                                     float cosBeta =
                                                       MathUtils.
                                                       clamp(
                                                         -Vector3.
                                                           dot(
                                                             n0,
                                                             n1),
                                                         -1.0F,
                                                         1.0F);
                                                     float cosGamma =
                                                       MathUtils.
                                                       clamp(
                                                         -Vector3.
                                                           dot(
                                                             n1,
                                                             n2),
                                                         -1.0F,
                                                         1.0F);
                                                     float alpha =
                                                       (float)
                                                         Math.
                                                         acos(
                                                           cosAlpha);
                                                     float beta =
                                                       (float)
                                                         Math.
                                                         acos(
                                                           cosBeta);
                                                     float gamma =
                                                       (float)
                                                         Math.
                                                         acos(
                                                           cosGamma);
                                                     float area =
                                                       alpha +
                                                       beta +
                                                       gamma -
                                                       (float)
                                                         Math.
                                                           PI;
                                                     float cosC =
                                                       MathUtils.
                                                       clamp(
                                                         Vector3.
                                                           dot(
                                                             p0,
                                                             p1),
                                                         -1.0F,
                                                         1.0F);
                                                     float salpha =
                                                       (float)
                                                         Math.
                                                         sin(
                                                           alpha);
                                                     float product =
                                                       salpha *
                                                       cosC;
                                                     int samples =
                                                       state.
                                                       getDiffuseDepth(
                                                         ) >
                                                       0
                                                       ? 1
                                                       : numSamples;
                                                     Color c =
                                                       Color.
                                                       mul(
                                                         area /
                                                           samples,
                                                         radiance);
                                                     for (int i =
                                                            0;
                                                          i <
                                                            samples;
                                                          i++) {
                                                         double randX =
                                                           state.
                                                           getRandom(
                                                             i,
                                                             0,
                                                             samples);
                                                         double randY =
                                                           state.
                                                           getRandom(
                                                             i,
                                                             1,
                                                             samples);
                                                         float phi =
                                                           (float)
                                                             randX *
                                                           area -
                                                           alpha +
                                                           (float)
                                                             Math.
                                                               PI;
                                                         float sinPhi =
                                                           (float)
                                                             Math.
                                                             sin(
                                                               phi);
                                                         float cosPhi =
                                                           (float)
                                                             Math.
                                                             cos(
                                                               phi);
                                                         float u =
                                                           cosPhi +
                                                           cosAlpha;
                                                         float v =
                                                           sinPhi -
                                                           product;
                                                         float q =
                                                           (-v +
                                                              cosAlpha *
                                                              (cosPhi *
                                                                 -v +
                                                                 sinPhi *
                                                                 u)) /
                                                           (salpha *
                                                              (sinPhi *
                                                                 -v -
                                                                 cosPhi *
                                                                 u));
                                                         float q1 =
                                                           1.0F -
                                                           q *
                                                           q;
                                                         if (q1 <
                                                               0.0F)
                                                             q1 =
                                                               0.0F;
                                                         float sqrtq1 =
                                                           (float)
                                                             Math.
                                                             sqrt(
                                                               q1);
                                                         float ncx =
                                                           q *
                                                           p0.
                                                             x +
                                                           sqrtq1 *
                                                           h.
                                                             x;
                                                         float ncy =
                                                           q *
                                                           p0.
                                                             y +
                                                           sqrtq1 *
                                                           h.
                                                             y;
                                                         float ncz =
                                                           q *
                                                           p0.
                                                             z +
                                                           sqrtq1 *
                                                           h.
                                                             z;
                                                         dot =
                                                           p1.
                                                             dot(
                                                               ncx,
                                                               ncy,
                                                               ncz);
                                                         float z =
                                                           1.0F -
                                                           (float)
                                                             randY *
                                                           (1.0F -
                                                              dot);
                                                         float z1 =
                                                           1.0F -
                                                           z *
                                                           z;
                                                         if (z1 <
                                                               0.0F)
                                                             z1 =
                                                               0.0F;
                                                         Vector3 nd =
                                                           new Vector3(
                                                           );
                                                         nd.
                                                           x =
                                                           ncx -
                                                             dot *
                                                             p1.
                                                               x;
                                                         nd.
                                                           y =
                                                           ncy -
                                                             dot *
                                                             p1.
                                                               y;
                                                         nd.
                                                           z =
                                                           ncz -
                                                             dot *
                                                             p1.
                                                               z;
                                                         nd.
                                                           normalize(
                                                             );
                                                         float sqrtz1 =
                                                           (float)
                                                             Math.
                                                             sqrt(
                                                               z1);
                                                         Vector3 result =
                                                           new Vector3(
                                                           );
                                                         result.
                                                           x =
                                                           z *
                                                             p1.
                                                               x +
                                                             sqrtz1 *
                                                             nd.
                                                               x;
                                                         result.
                                                           y =
                                                           z *
                                                             p1.
                                                               y +
                                                             sqrtz1 *
                                                             nd.
                                                               y;
                                                         result.
                                                           z =
                                                           z *
                                                             p1.
                                                               z +
                                                             sqrtz1 *
                                                             nd.
                                                               z;
                                                         if (Vector3.
                                                               dot(
                                                                 result,
                                                                 n) >
                                                               0 &&
                                                               Vector3.
                                                               dot(
                                                                 result,
                                                                 state.
                                                                   getGeoNormal(
                                                                     )) >
                                                               0 &&
                                                               Vector3.
                                                               dot(
                                                                 result,
                                                                 ng) <
                                                               0) {
                                                             Ray shadowRay =
                                                               new Ray(
                                                               state.
                                                                 getPoint(
                                                                   ),
                                                               result);
                                                             if (!intersectTriangleKensler(
                                                                    shadowRay))
                                                                 continue;
                                                             LightSample dest =
                                                               new LightSample(
                                                               );
                                                             dest.
                                                               setShadowRay(
                                                                 shadowRay);
                                                             dest.
                                                               setRadiance(
                                                                 c,
                                                                 c);
                                                             dest.
                                                               traceShadow(
                                                                 state);
                                                             state.
                                                               addSample(
                                                                 dest);
                                                         }
                                                     }
        }
        public void getPhoton(double randX1, double randY1,
                              double randX2,
                              double randY2,
                              Point3 p,
                              Vector3 dir,
                              Color power) { double s =
                                               Math.
                                               sqrt(
                                                 1 -
                                                   randX2);
                                             float u =
                                               (float)
                                                 (randY2 *
                                                    s);
                                             float v =
                                               (float)
                                                 (1 -
                                                    s);
                                             float w =
                                               1 -
                                               u -
                                               v;
                                             int index0 =
                                               3 *
                                               triangles[tri3 +
                                                           0];
                                             int index1 =
                                               3 *
                                               triangles[tri3 +
                                                           1];
                                             int index2 =
                                               3 *
                                               triangles[tri3 +
                                                           2];
                                             p.x =
                                               w *
                                                 points[index0 +
                                                          0] +
                                                 u *
                                                 points[index1 +
                                                          0] +
                                                 v *
                                                 points[index2 +
                                                          0];
                                             p.y =
                                               w *
                                                 points[index0 +
                                                          1] +
                                                 u *
                                                 points[index1 +
                                                          1] +
                                                 v *
                                                 points[index2 +
                                                          1];
                                             p.z =
                                               w *
                                                 points[index0 +
                                                          2] +
                                                 u *
                                                 points[index1 +
                                                          2] +
                                                 v *
                                                 points[index2 +
                                                          2];
                                             p.x +=
                                               0.001F *
                                                 ng.
                                                   x;
                                             p.y +=
                                               0.001F *
                                                 ng.
                                                   y;
                                             p.z +=
                                               0.001F *
                                                 ng.
                                                   z;
                                             OrthoNormalBasis onb =
                                               OrthoNormalBasis.
                                               makeFromW(
                                                 ng);
                                             u = (float)
                                                   (2 *
                                                      Math.
                                                        PI *
                                                      randX1);
                                             s = Math.
                                                   sqrt(
                                                     randY1);
                                             onb.
                                               transform(
                                                 new Vector3(
                                                   (float)
                                                     (Math.
                                                        cos(
                                                          u) *
                                                        s),
                                                   (float)
                                                     (Math.
                                                        sin(
                                                          u) *
                                                        s),
                                                   (float)
                                                     Math.
                                                     sqrt(
                                                       1 -
                                                         randY1)),
                                                 dir);
                                             Color.
                                               mul(
                                                 (float)
                                                   Math.
                                                     PI *
                                                   area,
                                                 radiance,
                                                 power);
        }
        public float getPower() { return radiance.
                                    copy(
                                      ).
                                    mul(
                                      (float)
                                        Math.
                                          PI *
                                        area).
                                    getLuminance(
                                      ); }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "D7y88K64k/0XzA1Kt38VAAA=");
    }
    public Color getRadiance(ShadingState state) {
        if (!state.
              includeLights(
                ))
            return Color.
                     BLACK;
        state.
          faceforward(
            );
        return state.
          isBehind(
            )
          ? Color.
              BLACK
          : radiance;
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {  }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
