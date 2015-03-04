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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "vmyf/wswzfLMQhMAAA==");
}
