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
                                 Point3 v0p = TriangleMeshLight.this.
                                   getPoint(
                                     a);
                                 Point3 v1p = TriangleMeshLight.this.
                                   getPoint(
                                     b);
                                 Point3 v2p = TriangleMeshLight.this.
                                   getPoint(
                                     c);
                                 ng = Point3.normal(v0p, v1p,
                                                    v2p);
                                 area = 0.5F * ng.length();
                                 ng.normalize(); }
        public boolean update(ParameterList pl, SunflowAPI api) {
            return true;
        }
        public int getNumSamples() { return numSamples; }
        final private boolean intersectTriangleKensler(Ray r) {
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
                                                       getNormal();
                                                     Point3 p =
                                                       state.
                                                       getPoint();
                                                     Vector3 p0 =
                                                       Point3.
                                                       sub(
                                                         TriangleMeshLight.this.
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
                                                         TriangleMeshLight.this.
                                                           getPoint(
                                                             triangles[tri3 +
                                                                         1]),
                                                         p,
                                                         new Vector3(
                                                           ));
                                                     Vector3 p2 =
                                                       Point3.
                                                       sub(
                                                         TriangleMeshLight.this.
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
                                                     p0.normalize();
                                                     p1.normalize();
                                                     p2.normalize();
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
                                                       length();
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
                                                       length();
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
                                                       length();
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
                                                       length();
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
                                                       getDiffuseDepth() >
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
                                                           normalize();
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
                                                                   getGeoNormal()) >
                                                               0 &&
                                                               Vector3.
                                                               dot(
                                                                 result,
                                                                 ng) <
                                                               0) {
                                                             Ray shadowRay =
                                                               new Ray(
                                                               state.
                                                                 getPoint(),
                                                               result);
                                                             if (!this.
                                                                   intersectTriangleKensler(
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
        public void getPhoton(double randX1,
                              double randY1,
                              double randX2,
                              double randY2,
                              Point3 p,
                              Vector3 dir,
                              Color power) {
            double s =
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
            p.
              x =
              w *
                points[index0 +
                         0] +
                u *
                points[index1 +
                         0] +
                v *
                points[index2 +
                         0];
            p.
              y =
              w *
                points[index0 +
                         1] +
                u *
                points[index1 +
                         1] +
                v *
                points[index2 +
                         1];
            p.
              z =
              w *
                points[index0 +
                         2] +
                u *
                points[index1 +
                         2] +
                v *
                points[index2 +
                         2];
            p.
              x +=
              0.001F *
                ng.
                  x;
            p.
              y +=
              0.001F *
                ng.
                  y;
            p.
              z +=
              0.001F *
                ng.
                  z;
            OrthoNormalBasis onb =
              OrthoNormalBasis.
              makeFromW(
                ng);
            u =
              (float)
                (2 *
                   Math.
                     PI *
                   randX1);
            s =
              Math.
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
                                    copy().
                                    mul(
                                      (float)
                                        Math.
                                          PI *
                                        area).
                                    getLuminance();
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1170312822000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAKVYe2wUxxkf3/mBH8iHAfPG2DhNAHPblIAaOwkB14YLB77a" +
           "YIhJ6ox3586L93Y2\nu3P22SGUtFKgidIW9RmpPFTRAmnSRKIViZqkRAlpEl" +
           "qVVGoqRQptRdRWalOpqpSC2j/6zczu7d3e\n+bDMSTu3u/PN9/5+880+/wmq" +
           "cmy0XHWibNIiTrR7IIFth2jdBnac3fBqWL1UVZs4s8OkIVQRRyFd\nY6gxrj" +
           "qKhhlWdE2JfaEra6N1FjUmUwZlUZJl0QPGRpffA/GNRQz3nrjQ9MTpypYQqo" +
           "qjRmyalGGm\nU7PHIGmHoUj8AB7HSobphhLXHdYVR3OJmUl3U9Nh2GTOo+gQ" +
           "CsdRtaVyngy1xj3hCghXLGzjtCLE\nKwkhFjjMtwnDukm0LTlxsLKjcCWo7a" +
           "7rL6YGJnP45CCYIzQAq1flrJbWFplqhc8Objp46lwYNQ6h\nRt0c4MxUsISB" +
           "vCHUkCbpEWI7WzSNaENonkmINkBsHRv6lJA6hJocPWVilrGJ008caoxzwiYn" +
           "YxFb\nyPRexlGDym2yMyqjds5HSZ0YmvdUlTRwCsxu9s2W5vby92BgnQ6K2U" +
           "msEm9J5ZhuQsRbgityNrbv\nAAJYWpMmbJTmRFWaGF6gJhlLA5spZYDZupkC" +
           "0iqaASkMLZ2WKfe1hdUxnCLDDC0O0iXkFFDVCkfw\nJQwtDJIJThClpYEo5c" +
           "VnXfOnR8/+4PX7RW5XakQ1uP51sGhlYFE/SRKbmCqRC69not+OPZhZHkII\n" +
           "iBcGiCXNltsu7In/7ZctkmZZCZq+kQNEZcPqrmMt/Y9toyjM1ZhjUUfnwS+w" +
           "XJRDwp3pylpQtc05\njnwy6k1e7H/7wcPPkb+HUF0MVavUyKQhj+apNG3pBr" +
           "G3EZPYmBEthmqJqXWL+Riqgfs4pLx825dM\nOoTFUKUhXlVT8QwuSgIL7qJa" +
           "uNfNJPXuLcxGxX3WQgg1wIU2wdWB5E/8M9QVVZyMmTTohOLYqkLt\nVO5ZpT" +
           "ZRDD01ypTdUABmyiA7iTMa52+iPIkshvYqozRNFKxiUzepktKhbFW6XiPj/H" +
           "/2rLNc86aJ\nigoOhcGSNqAatlNDI/aweubaewd7dnztqEwXnuKuzQxtAolR" +
           "V2KUS4wKidEiie3eG/GEKiqE2AVc\nDxlHiMIY1DMgX8OagYcfeORoWxgSyJ" +
           "qoBBdy0jaw1VWuR6XdftHHBD6qkHmLf7j/SPT6mc0y85Tp\nsbnk6vorL1w+" +
           "9e+GtSEUKg2c3GiA7jrOJsHRNgeI7cFSK8X/n0/tPP/B5Y/u8IuOofYiLChe" +
           "yWu5\nLRgem6pEA3T02Z9e0hjeiwaPhVAlAASAotAf8GZlUEZBTXd5+MhtqY" +
           "mj+iS109jgUx6o1bFRm074\nb0TeRMT9fAjOHJ7kC+G608168c9nF1p8bJZ5" +
           "xqMdsELg7/WvVn/2D6/WXxJu8aC6MW8zHCBMFv48\nP1l224TA+4++n/jWdz" +
           "45sl9kikwVlAXKz/iUUOgGgA2PX/seM001PanjEZ6GDvtf4213/vwfX4/I\n" +
           "iBjwxgtox80Z+O+XbEWHL3/pPysFmwqVbzS+9j6ZNGK+z3mLbeNJrkf2id+t" +
           "ePZX+DjgIGCPo08R\nAScVudxfnN+o2HoaAG9cRO/ak22vvbPn5BGZ8WvKdC" +
           "P5q4bVL//xT2Phb7wxItcFQT9AfGzl6b+c\nv9a/QLpJ7oyrizan/DVydxRx" +
           "b7R4QFrLSRDUb61rff5Q/1VXo6ZCjO+BPuivk2+S2+955s8lQCgM\n+7cQFh" +
           "VJuUaM63kyuCnBn+/mQxvo0jGNl0o0P8PqwedSbZlH331FSK3H+V1Ufo7uxJ" +
           "Y0N8KH1dzk\nRUFo246dUaC76+KuhyLGxf8CxyHgqELT4fTZgLPZggx3qatq" +
           "PnzjzeZH3g+jUC+qMyjWerEAB1QL\nVQngChCdtTbfLwovMsFrMSJMRsIJS1" +
           "0HZPOewk7ZTOnlrZMPK8MjHWfj7/UdFw6YFhVLJFGAz9Tr\ne05c/w27Kvj4" +
           "8MRXt2aL9x9oN/21n/9gfF71SyfTIVQzhCKq2xAPYiPDQWAI+jfH65KhaS6Y" +
           "L+zF\nZOPRlYPf5cEszhMbBEY/5eCeU/P7hgAWCt8vgWu9i4Xrg1hYgcTNNr" +
           "GkXYy3u5scQzWWrY9j3iSD\nCFvfwGc3SPTkYycftstY3jttzLcWarMMrqir" +
           "TXQabfr4EAOh2CZYLFcsKeYuxr1AMQtokZiFFoqr\nhTKNFntdLUKmdPoi6A" +
           "Tz24s0NFvRQcLTP+iUfWXUESGK5RVAhcd9eVHzIpqTAZqxVcJreMV0LbSA\n" +
           "rCP7/tXwJH7r4ZCLMBsZqnZPNr6was6moHXZKU4MfnY/de4nF9j76zol8q2d" +
           "vjKDC9d2nppq6Xzx\n6Vk0LC0Bw4Ks540v+2J4VH8nJA41sliKDkOFi7oKS6" +
           "QO9MnY5u6CQlmVS4pGHpvNcN3nJsV9pZuG\nUlVSbWVGDF3NBsA+JOZDXnBX" +
           "FgVXuIDAGYzvuR5Zcz7ZgPzfkogJ8bTMdpLhgw7KZCwNKjZYNDUj\nlBoEm3" +
           "6KHrhZxXhYLR5Ioafa4NrqemprSU/xYayMvofLzH2FD48zNDdF2K5MegCnLU" +
           "MqsMHX/9As\n9W/mL++Fq9fVv3eGkQ5x6NFNLM6mO4LRrigs5QVF0e7Hk4Lz" +
           "M2UMP8aHIwAz4gOAA9DinVJ2ENMx\n5PY04fvg6K3E8HNwma4PzBnHMGDnii" +
           "I7B0axppsp/omFCDbPljH4OB++y1AdRNoNczBzK8eprvkm\nf+9WTH4cro9d" +
           "kz+esck1gmNNULFqjULdCzQ55w+eYxYV7RQJClHdcNOdpCQDPY1ThLf+1BZ6" +
           "ni3j\n0/N8+BFDteDTxChl1OQvTvou/PGtuLAFrhuuC2/MqvJ/UWbuNT68zN" +
           "AcrjudkAnf76v+ykxVh45l\nbsEJ33PrHTP9QMCPOUXfFeW3MDX+4WMPfRr/" +
           "/Q1xts19r6qPoznJjGHkN2Z599WWTZK6MLNetmmW\n+Hs7APq+UgA4Rk73S5" +
           "L6XYYiQWooEv6XT/ZrhurzyGADcO/yiX4LRxUg4rdXLM9BEdHz8/Y0KtvT\n" +
           "bIFj+ZlxdUFPID70evt2Rn7qHVb3vbB/Vfbp3d8UzUCVauCpKc6mLo5q5Ik+" +
           "t/e3TsvN43UFvfTi\n4Ks/vdtrbMShZkEeThckbKecLZMo0G+UPk/3pC0mTs" +
           "BTLy/62T1nTlwNiYP8/wHQOz2lnxcAAA==");
    }
    public Color getRadiance(ShadingState state) {
        if (!state.
              includeLights())
            return Color.
                     BLACK;
        state.
          faceforward();
        return state.
          isBehind()
          ? Color.
              BLACK
          : radiance;
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170312822000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKUYC2wcxXXuYzs+G/mXOMFJ/IshkIRbgpqqzaEmruvAJRty" +
       "2I4DDmDGu3N3G+/t\nLrtz54uJUlBbnBL1k1KkVi0hRUEJED4SoLQSpUFAS4" +
       "kqQaVSCYm0VaS2UkulqhJN1Qr1zcze7e3e\nJ1E4aWfnZt5n3v/NnvkYNTk2" +
       "WqM4cXrQIk58bDKFbYeoYzp2nClYmlXebmpNndplmGEUklFYUynq\nkBVHUj" +
       "HFkqZKya8kijbaaJn6wYxu0jgp0vgBfYtLb6e8pYrgvuNnux8+GR0IoyYZdW" +
       "DDMCmmmmmM\n6yTnUNQpH8AFLOWppkuy5tCEjK4hRj43ZhoOxQZ1HkCHUURG" +
       "zZbCaFI0JJeYS8BcsrCNcxJnL6U4\nW6DQYxOKNYOoo2V2gLnJjwnHdvEmqq" +
       "GByDK2OQ3i8BOA1INlqYW0VaJakdPTnz904pkI6phBHZox\nyYgpIAkFfjOo" +
       "PUdyc8R2RlWVqDOoyyBEnSS2hnVtkXOdQd2OljEwzdvEmSCOqRcYYLeTt4jN" +
       "eZYW\nZdSuMJnsvEJNu6yjtEZ0tfSvKa3jDIjd64ktxN3B1kHAmAYHs9NYIS" +
       "WU6LxmgMUHghhlGUd2AQCg\ntuQIzZplVlEDwwLqFrbUsZGRJqmtGRkAbTLz" +
       "wIWivrpEma4trMzjDJmlaFUQLiW2AKqVK4KhULQi\nCMYpgZX6AlaqsM/G3k" +
       "+OnP7x69u5b0dVoujs/DFA6g8gTZA0sYmhEIF4KR//fvLu/JowQgC8IgAs\n" +
       "YEavO7tX/usvBgTM6howe+YOEIXOKnccG5h48DYTRdgxllmmozHj+yTn4ZBy" +
       "dxJFC6K2t0yRbcZL\nm+cmfnn3Q8+Sv4VRLImaFVPP58CPuhQzZ2k6sW8jBr" +
       "ExJWoStRJDHeP7SdQCcxlcXqzuSacdQpMo\nqvOlZpP/BxWlgQRTUSvMNSNt" +
       "luYWplk+L1oIoRZ40Dg8XUj8+JuiRFxy8kZaNxckx1Yk086U/yum\nTSRdy2" +
       "SpNAUBYGR0sps4WZmtxJkTWRTtk7JmjkhYwYZmmFJGg7BVzJtUUmDvqyddZC" +
       "fvXgiFWCoM\nhrQO0XC7qavEnlVOXXz30Piubx4R7sJc3JWZohuAY9zlGGcc" +
       "45xjvIojCoU4o+WMs7Ac6H0eIhhy\nXfuNk/fuvP/IcARcxlqIgtIY6DBI5x" +
       "5nXDHHvDBP8oyogK+temr/UvzSqW3C16T62bgmdtt7z58/\n8a/2DWEUrp0q" +
       "mZiQrGOMTIrl13IKHAkGVy36/3h098sfnP/oBi/MKBqpiv5qTBa9w0GD2KZC" +
       "VMiH\nHvmT13ZE9qHpY2EUhZQAaZCfHzJMf5CHL4oTpYzIZGmRUVvatHNYZ1" +
       "ulNBajWdtc8Fa4p3TyeQ8Y\np4259Rp4Vrh+zt9sd4XFxl7hWczaASl4xr30" +
       "teabf/9a29tcLaXk3FFR/iYJFaHe5TnLlE0IrH/0\ng9Rjj3+8tJ97iusqFG" +
       "pifk7XlCKgXO+hQIzrkGeYIUf2GjlT1dIantMJ87j/dVy3+dW/f7tTmEaH\n" +
       "lZJlN12egLd+7ZfRQ+fv+3c/JxNSWI3xxPDAhDQ9HuVR28YH2TmKD/927Q9/" +
       "hZ+AFAhpx9EWCc8k\niEuGuB4lrvcNfIwH9jazYRhob6rj+jUq+qxy6NnMcP" +
       "6BX/+Mn7oNV7YGlWbYja2EsDwb1jHtrgxG\n7+3YyQLc587dcU+nfu6/QHEG" +
       "KCpQSZ09NiSPos+ILnRTy4dvvNl7//sRFN6BYrqJ1R2Y+z9qBceD\njAF5p2" +
       "ht2859q3NhGRu5yIgroc9VQLHiXxgOd2P98N/B+gEvcmbnNp2W393zBFdA3c" +
       "CvUQ4DdBZf\n33v80m/oBU7Hi0CGPVSsTqrQQ3m4X/ig0NX80pO5MGqZQZ2K" +
       "2+VNYz3P/HwGmhKn1PpBJ+jb9zcY\nopomyhlmTTD6K9gGY99L5jBn0GzeHg" +
       "j3dqbt1fB0u+HeHQz3EOKTbRxlhI/ry8HZYtlaAbPODy2z\nsaqxc3CQlRSt" +
       "rCwfWg66GxZ0pi3yBxtvYcN2YeotdV1ia/Vhe9zD9tQ5bJINoxTFoMeexDlL" +
       "B5e1\n0arKK4Gt5aC1KPCsefGR4Z+/s/fJJVFpGriaD2tW+eof/jgf+c4bcw" +
       "Iv6E8B4GP9J//88sWJ5SIr\niR50XVUbWIkj+lBusA6LRehQIw4c+q2NQ2cO" +
       "T1xwT9Tt76bGQRt/OfgmWX/rt/5Uo9xHoFMO2GZn\nA9vwc11fEaahmobnfc" +
       "NkFrN8ASKsrde48uMv3fXP9kfwW/eG3RQ4QSFrmNZNOikQvYJVlFHydRC7\n" +
       "eavuReCjzzx3lr6/catQxIb6Jg0ibth6YnFg64tHr6JvGAjIFiTdVVh9ZySr" +
       "vRPmtwkR0FW3ED9S\nwh/GMThP3jamfME86K/dCXj63Pjoq1m7PcN5hSfsZl" +
       "rXhP1VJuSiErjksMpWAuutBJsU79FUkrMh\nDUrbPBvmoLbnLbhzC0jZEs52" +
       "J2SVOdPUCTY8R1QulyRKdYP/uc+vkc3wDLoaGbxajXTySseSclzc\n+q5EB4" +
       "UGOjjEBqjKcOfQaFAD0YKpqZ749LOIfzM8n7rif3rF4of8Mb22ZkyDGtj1n3" +
       "AySw2EPcqG\nr1PUliF04grrhCf+Nz6L+F8CKZqF9OJ9NdZvLP7lJOFsHm+g" +
       "nh+x4XsUXeMomEKMpbImNQ22eNjT\nwmNXqgUoxl1VdzTWpK6q+rYjvkco8o" +
       "cP3vOJ/Lv/8NtG+ZtBG1zc03ldr+wjKubNlk3SGj9/m+gq\nLP56KhAT3s2R" +
       "oib+5qf9iYB+GkIrCA3+z16VYKfBdyrAIEe4s0qg56CIARCbnrFKRllfdRar" +
       "VDZ9\nN9miT4dMXet8VYN/gytl9rz4Cjer3PX8/sHi0anv8nLRpOh4cZGRic" +
       "moRVy9ytVhqC61Eq330Esv\nTr/2whdL1Y+35suLXk32ufktYreBT0BFqn3f" +
       "Gc9ZlN9QFn+68pVbTx2/EOY3rv8Dddn4oDoVAAA=");
}
