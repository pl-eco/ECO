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
          1170312822000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "o0HD5uI2M/uzVa8/8PLC++JO9l8Wqb5llRUAAA==");
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
      1170312822000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "6DxRPadKFEouard5pdZPaHb2xNKyfvDpXUqQ3Xdw0sEd9yaTzTEzJqoZd6qp" +
       "owdEsxtl0iPP/vol/sbOb0sbd6zs4iTjKyc+GJjeU7r3K1TPLQmbkls+e+Dc" +
       "a7dfrz2qkKZqQtb177VMo7Vp2Okx+OCwp2uScbCaACLer4FrIEiAgYYVLHJY" +
       "hKVKgJCB6wbrXCdMZfB5gGAdkq2Pk03J+1h2Uoj53iponcfhLihXZVeHiBU0" +
       "38Vhn8Tr/YAKeccxGbXrIV0sHK4t27vgGgqMHvpfje4RZQlxMy0/iXA9J9hn" +
       "VzFGtNrQAkLbbfBGpjTPOYb+hXb0hu3HlcCOK1/ajlRt3m1umHdgD37kMrFN" +
       "ZRV77sehzMmaIuOHAjzGpVu/nCv2gCat0gJ5/yquwMfjQpggPbGKmj/E4QEO" +
       "EKlRDnGZLTncsXHxWIM2gJPeuqYdW5GNdX8UyI9b7YXl7vYNy4f/LtrQ6gdo" +
       "B3wFFsqmGS+NsXmr67GCIdTrkIVSwuPDiVSJPiU4aRF3ofEpSf1jCMUkNUQR" +
       "3uJkPwEXxcggZYJZnOhRgGogwuljbhggX6vTxQ2LQ82nTYXEsBY70/hTTZuK" +
       "cCr+mQmhryz/m8lp55f333nf5W89LXC0RTPpwgLu0p4hbbJDr8Ln1hV3C/dq" +
       "3bf9s7UvdmwLy8JaHPqCtjyh25bG3euE5XLRby78bsNvb3lm+S3RPv8XMIFU" +
       "FDITAAA=");
}
