package org.sunflow.core.tesselatable;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Instance;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Tesselatable;
import org.sunflow.core.ParameterList.FloatParameter;
import org.sunflow.core.ParameterList.InterpolationType;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.core.primitive.QuadMesh;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class BezierMesh implements PrimitiveList, Tesselatable {
    private int subdivs;
    private boolean smooth;
    private boolean quads;
    private float[][] patches;
    public BezierMesh() { this(null); }
    public BezierMesh(float[][] patches) { super();
                                           subdivs = 8;
                                           smooth = true;
                                           quads = false;
                                           this.patches = patches; }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       );
                                                     if (o2w == null) { for (int i =
                                                                               0;
                                                                             i <
                                                                               patches.
                                                                                 length;
                                                                             i++) {
                                                                            float[] patch =
                                                                              patches[i];
                                                                            for (int j =
                                                                                   0;
                                                                                 j <
                                                                                   patch.
                                                                                     length;
                                                                                 j +=
                                                                                   3)
                                                                                bounds.
                                                                                  include(
                                                                                    patch[j],
                                                                                    patch[j +
                                                                                            1],
                                                                                    patch[j +
                                                                                            2]);
                                                                        }
                                                     }
                                                     else {
                                                         for (int i =
                                                                0;
                                                              i <
                                                                patches.
                                                                  length;
                                                              i++) {
                                                             float[] patch =
                                                               patches[i];
                                                             for (int j =
                                                                    0;
                                                                  j <
                                                                    patch.
                                                                      length;
                                                                  j +=
                                                                    3) {
                                                                 float x =
                                                                   patch[j];
                                                                 float y =
                                                                   patch[j +
                                                                           1];
                                                                 float z =
                                                                   patch[j +
                                                                           2];
                                                                 float wx =
                                                                   o2w.
                                                                   transformPX(
                                                                     x,
                                                                     y,
                                                                     z);
                                                                 float wy =
                                                                   o2w.
                                                                   transformPY(
                                                                     x,
                                                                     y,
                                                                     z);
                                                                 float wz =
                                                                   o2w.
                                                                   transformPZ(
                                                                     x,
                                                                     y,
                                                                     z);
                                                                 bounds.
                                                                   include(
                                                                     wx,
                                                                     wy,
                                                                     wz);
                                                             }
                                                         }
                                                     }
                                                     return bounds;
    }
    private float[] bernstein(float u) { float[] b =
                                           new float[4];
                                         float i =
                                           1 -
                                           u;
                                         b[0] =
                                           i *
                                             i *
                                             i;
                                         b[1] =
                                           3 *
                                             u *
                                             i *
                                             i;
                                         b[2] =
                                           3 *
                                             u *
                                             u *
                                             i;
                                         b[3] =
                                           u *
                                             u *
                                             u;
                                         return b;
    }
    private float[] bernsteinDeriv(float u) {
        if (!smooth)
            return null;
        float[] b =
          new float[4];
        float i =
          1 -
          u;
        b[0] =
          3 *
            (0 -
               i *
               i);
        b[1] =
          3 *
            (i *
               i -
               2 *
               u *
               i);
        b[2] =
          3 *
            (2 *
               u *
               i -
               u *
               u);
        b[3] =
          3 *
            (u *
               u -
               0);
        return b;
    }
    private void getPatchPoint(float u, float v,
                               float[] ctrl,
                               float[] bu,
                               float[] bv,
                               float[] bdu,
                               float[] bdv,
                               Point3 p,
                               Vector3 n) {
        float px =
          0;
        float py =
          0;
        float pz =
          0;
        for (int i =
               0,
               index =
                 0;
             i <
               4;
             i++) {
            for (int j =
                   0;
                 j <
                   4;
                 j++,
                   index +=
                     3) {
                float scale =
                  bu[j] *
                  bv[i];
                px +=
                  ctrl[index +
                         0] *
                    scale;
                py +=
                  ctrl[index +
                         1] *
                    scale;
                pz +=
                  ctrl[index +
                         2] *
                    scale;
            }
        }
        p.
          x =
          px;
        p.
          y =
          py;
        p.
          z =
          pz;
        if (n !=
              null) {
            float dpdux =
              0;
            float dpduy =
              0;
            float dpduz =
              0;
            float dpdvx =
              0;
            float dpdvy =
              0;
            float dpdvz =
              0;
            for (int i =
                   0,
                   index =
                     0;
                 i <
                   4;
                 i++) {
                for (int j =
                       0;
                     j <
                       4;
                     j++,
                       index +=
                         3) {
                    float scaleu =
                      bdu[j] *
                      bv[i];
                    dpdux +=
                      ctrl[index +
                             0] *
                        scaleu;
                    dpduy +=
                      ctrl[index +
                             1] *
                        scaleu;
                    dpduz +=
                      ctrl[index +
                             2] *
                        scaleu;
                    float scalev =
                      bu[j] *
                      bdv[i];
                    dpdvx +=
                      ctrl[index +
                             0] *
                        scalev;
                    dpdvy +=
                      ctrl[index +
                             1] *
                        scalev;
                    dpdvz +=
                      ctrl[index +
                             2] *
                        scalev;
                }
            }
            n.
              x =
              dpduy *
                dpdvz -
                dpduz *
                dpdvy;
            n.
              y =
              dpduz *
                dpdvx -
                dpdux *
                dpdvz;
            n.
              z =
              dpdux *
                dpdvy -
                dpduy *
                dpdvx;
        }
    }
    public PrimitiveList tesselate() { float[] vertices =
                                         new float[patches.
                                                     length *
                                                     (subdivs +
                                                        1) *
                                                     (subdivs +
                                                        1) *
                                                     3];
                                       float[] normals =
                                         smooth
                                         ? (new float[patches.
                                                        length *
                                                        (subdivs +
                                                           1) *
                                                        (subdivs +
                                                           1) *
                                                        3])
                                         : null;
                                       float[] uvs =
                                         new float[patches.
                                                     length *
                                                     (subdivs +
                                                        1) *
                                                     (subdivs +
                                                        1) *
                                                     2];
                                       int[] indices =
                                         new int[patches.
                                                   length *
                                                   subdivs *
                                                   subdivs *
                                                   (quads
                                                      ? 4
                                                      : 2 *
                                                      3)];
                                       int vidx =
                                         0;
                                       int pidx =
                                         0;
                                       float step =
                                         1.0F /
                                         subdivs;
                                       int vstride =
                                         subdivs +
                                         1;
                                       Point3 p =
                                         new Point3(
                                         );
                                       Vector3 n =
                                         smooth
                                         ? new Vector3(
                                         )
                                         : null;
                                       for (float[] patch
                                             :
                                             patches) {
                                           for (int i =
                                                  0,
                                                  voff =
                                                    0;
                                                i <=
                                                  subdivs;
                                                i++) {
                                               float u =
                                                 i *
                                                 step;
                                               float[] bu =
                                                 bernstein(
                                                   u);
                                               float[] bdu =
                                                 bernsteinDeriv(
                                                   u);
                                               for (int j =
                                                      0;
                                                    j <=
                                                      subdivs;
                                                    j++,
                                                      voff +=
                                                        3) {
                                                   float v =
                                                     j *
                                                     step;
                                                   float[] bv =
                                                     bernstein(
                                                       v);
                                                   float[] bdv =
                                                     bernsteinDeriv(
                                                       v);
                                                   getPatchPoint(
                                                     u,
                                                     v,
                                                     patch,
                                                     bu,
                                                     bv,
                                                     bdu,
                                                     bdv,
                                                     p,
                                                     n);
                                                   vertices[vidx +
                                                              voff +
                                                              0] =
                                                     p.
                                                       x;
                                                   vertices[vidx +
                                                              voff +
                                                              1] =
                                                     p.
                                                       y;
                                                   vertices[vidx +
                                                              voff +
                                                              2] =
                                                     p.
                                                       z;
                                                   if (smooth) {
                                                       normals[vidx +
                                                                 voff +
                                                                 0] =
                                                         n.
                                                           x;
                                                       normals[vidx +
                                                                 voff +
                                                                 1] =
                                                         n.
                                                           y;
                                                       normals[vidx +
                                                                 voff +
                                                                 2] =
                                                         n.
                                                           z;
                                                   }
                                                   uvs[(vidx +
                                                          voff) /
                                                         3 *
                                                         2 +
                                                         0] =
                                                     u;
                                                   uvs[(vidx +
                                                          voff) /
                                                         3 *
                                                         2 +
                                                         1] =
                                                     v;
                                               }
                                           }
                                           for (int i =
                                                  0,
                                                  vbase =
                                                    vidx /
                                                    3;
                                                i <
                                                  subdivs;
                                                i++) {
                                               for (int j =
                                                      0;
                                                    j <
                                                      subdivs;
                                                    j++) {
                                                   int v00 =
                                                     (i +
                                                        0) *
                                                     vstride +
                                                     (j +
                                                        0);
                                                   int v10 =
                                                     (i +
                                                        1) *
                                                     vstride +
                                                     (j +
                                                        0);
                                                   int v01 =
                                                     (i +
                                                        0) *
                                                     vstride +
                                                     (j +
                                                        1);
                                                   int v11 =
                                                     (i +
                                                        1) *
                                                     vstride +
                                                     (j +
                                                        1);
                                                   if (quads) {
                                                       indices[pidx +
                                                                 0] =
                                                         vbase +
                                                           v01;
                                                       indices[pidx +
                                                                 1] =
                                                         vbase +
                                                           v00;
                                                       indices[pidx +
                                                                 2] =
                                                         vbase +
                                                           v10;
                                                       indices[pidx +
                                                                 3] =
                                                         vbase +
                                                           v11;
                                                       pidx +=
                                                         4;
                                                   }
                                                   else {
                                                       indices[pidx +
                                                                 0] =
                                                         vbase +
                                                           v00;
                                                       indices[pidx +
                                                                 1] =
                                                         vbase +
                                                           v10;
                                                       indices[pidx +
                                                                 2] =
                                                         vbase +
                                                           v01;
                                                       indices[pidx +
                                                                 3] =
                                                         vbase +
                                                           v10;
                                                       indices[pidx +
                                                                 4] =
                                                         vbase +
                                                           v11;
                                                       indices[pidx +
                                                                 5] =
                                                         vbase +
                                                           v01;
                                                       pidx +=
                                                         6;
                                                   }
                                               }
                                           }
                                           vidx +=
                                             vstride *
                                               vstride *
                                               3;
                                       }
                                       ParameterList pl =
                                         new ParameterList(
                                         );
                                       pl.
                                         addPoints(
                                           "points",
                                           InterpolationType.
                                             VERTEX,
                                           vertices);
                                       if (quads)
                                           pl.
                                             addIntegerArray(
                                               "quads",
                                               indices);
                                       else
                                           pl.
                                             addIntegerArray(
                                               "triangles",
                                               indices);
                                       pl.
                                         addTexCoords(
                                           "uvs",
                                           InterpolationType.
                                             VERTEX,
                                           uvs);
                                       if (smooth)
                                           pl.
                                             addVectors(
                                               "normals",
                                               InterpolationType.
                                                 VERTEX,
                                               normals);
                                       PrimitiveList m =
                                         quads
                                         ? new QuadMesh(
                                         )
                                         : new TriangleMesh(
                                         );
                                       m.
                                         update(
                                           pl,
                                           null);
                                       pl.
                                         clear(
                                           true);
                                       return m;
    }
    public boolean update(ParameterList pl,
                          SunflowAPI api) {
        subdivs =
          pl.
            getInt(
              "subdivs",
              subdivs);
        smooth =
          pl.
            getBoolean(
              "smooth",
              smooth);
        quads =
          pl.
            getBoolean(
              "quads",
              quads);
        int nu =
          pl.
          getInt(
            "nu",
            0);
        int nv =
          pl.
          getInt(
            "nv",
            0);
        pl.
          setVertexCount(
            nu *
              nv);
        boolean uwrap =
          pl.
          getBoolean(
            "uwrap",
            false);
        boolean vwrap =
          pl.
          getBoolean(
            "vwrap",
            false);
        FloatParameter points =
          pl.
          getPointArray(
            "points");
        if (points !=
              null &&
              points.
                interp ==
              InterpolationType.
                VERTEX) {
            int numUPatches =
              uwrap
              ? nu /
              3
              : (nu -
                   4) /
              3 +
              1;
            int numVPatches =
              vwrap
              ? nv /
              3
              : (nv -
                   4) /
              3 +
              1;
            if (numUPatches <
                  1 ||
                  numVPatches <
                  1) {
                UI.
                  printError(
                    Module.
                      GEOM,
                    "Invalid number of patches for bezier mesh - ignoring");
                return false;
            }
            patches =
              (new float[numUPatches *
                           numVPatches][]);
            for (int v =
                   0,
                   p =
                     0;
                 v <
                   numVPatches;
                 v++) {
                for (int u =
                       0;
                     u <
                       numUPatches;
                     u++,
                       p++) {
                    float[] patch =
                      patches[p] =
                      (new float[16 *
                                   3]);
                    int up =
                      u *
                      3;
                    int vp =
                      v *
                      3;
                    for (int pv =
                           0;
                         pv <
                           4;
                         pv++) {
                        for (int pu =
                               0;
                             pu <
                               4;
                             pu++) {
                            int meshU =
                              (up +
                                 pu) %
                              nu;
                            int meshV =
                              (vp +
                                 pv) %
                              nv;
                            patch[3 *
                                    (pv *
                                       4 +
                                       pu) +
                                    0] =
                              points.
                                data[3 *
                                       (meshU +
                                          nu *
                                          meshV) +
                                       0];
                            patch[3 *
                                    (pv *
                                       4 +
                                       pu) +
                                    1] =
                              points.
                                data[3 *
                                       (meshU +
                                          nu *
                                          meshV) +
                                       1];
                            patch[3 *
                                    (pv *
                                       4 +
                                       pu) +
                                    2] =
                              points.
                                data[3 *
                                       (meshU +
                                          nu *
                                          meshV) +
                                       2];
                        }
                    }
                }
            }
        }
        if (subdivs <
              1) {
            UI.
              printError(
                Module.
                  GEOM,
                "Invalid subdivisions for bezier mesh - ignoring");
            return false;
        }
        if (patches ==
              null) {
            UI.
              printError(
                Module.
                  GEOM,
                "No patch data present in bezier mesh - ignoring");
            return false;
        }
        return true;
    }
    public int getNumPrimitives() { return patches.
                                             length;
    }
    public float getPrimitiveBound(int primID,
                                   int i) {
        float[] patch =
          patches[primID];
        int axis =
          i >>>
          1;
        if ((i &
               1) ==
              0) {
            float min =
              patch[axis];
            for (int j =
                   axis +
                   3;
                 j <
                   patch.
                     length;
                 j +=
                   3)
                if (min >
                      patch[j])
                    min =
                      patch[j];
            return min;
        }
        else {
            float max =
              patch[axis];
            for (int j =
                   axis +
                   3;
                 j <
                   patch.
                     length;
                 j +=
                   3)
                if (max <
                      patch[j])
                    max =
                      patch[j];
            return max;
        }
    }
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
        final float[] stack =
          state.
          getRobustStack(
            );
        final int STACKSIZE =
          64;
        {
            float[] patch =
              patches[primID];
            for (int i =
                   0;
                 i <
                   4 *
                   4 *
                   3;
                 i++)
                stack[i] =
                  patch[i];
            stack[48] =
              Float.
                POSITIVE_INFINITY;
            stack[49] =
              0;
            stack[50] =
              0;
            stack[51] =
              1;
            stack[52] =
              1;
        }
        int stackpos =
          0;
        float orgX =
          r.
            ox;
        float invDirX =
          1 /
          r.
            dx;
        float orgY =
          r.
            oy;
        float invDirY =
          1 /
          r.
            dy;
        float orgZ =
          r.
            oz;
        float invDirZ =
          1 /
          r.
            dz;
        float t1;
        float t2;
        while (stackpos >=
                 0) {
            float intervalMin =
              r.
              getMin(
                );
            float intervalMax =
              r.
              getMax(
                );
            float minx =
              stack[stackpos +
                      0];
            float maxx =
              stack[stackpos +
                      0];
            for (int j =
                   1,
                   idx =
                     stackpos +
                     3;
                 j <
                   4 *
                   4;
                 j++,
                   idx +=
                     3) {
                if (minx >
                      stack[idx])
                    minx =
                      stack[idx];
                if (maxx <
                      stack[idx])
                    maxx =
                      stack[idx];
            }
            t1 =
              (minx -
                 orgX) *
                invDirX;
            t2 =
              (maxx -
                 orgX) *
                invDirX;
            if (invDirX >
                  0) {
                if (t1 >
                      intervalMin)
                    intervalMin =
                      t1;
                if (t2 <
                      intervalMax)
                    intervalMax =
                      t2;
            }
            else {
                if (t2 >
                      intervalMin)
                    intervalMin =
                      t2;
                if (t1 <
                      intervalMax)
                    intervalMax =
                      t1;
            }
            if (intervalMin >
                  intervalMax) {
                stackpos -=
                  STACKSIZE;
                continue;
            }
            float miny =
              stack[stackpos +
                      1];
            float maxy =
              stack[stackpos +
                      1];
            for (int j =
                   1,
                   idx =
                     stackpos +
                     4;
                 j <
                   4 *
                   4;
                 j++,
                   idx +=
                     3) {
                if (miny >
                      stack[idx])
                    miny =
                      stack[idx];
                if (maxy <
                      stack[idx])
                    maxy =
                      stack[idx];
            }
            t1 =
              (miny -
                 orgY) *
                invDirY;
            t2 =
              (maxy -
                 orgY) *
                invDirY;
            if (invDirY >
                  0) {
                if (t1 >
                      intervalMin)
                    intervalMin =
                      t1;
                if (t2 <
                      intervalMax)
                    intervalMax =
                      t2;
            }
            else {
                if (t2 >
                      intervalMin)
                    intervalMin =
                      t2;
                if (t1 <
                      intervalMax)
                    intervalMax =
                      t1;
            }
            if (intervalMin >
                  intervalMax) {
                stackpos -=
                  STACKSIZE;
                continue;
            }
            float minz =
              stack[stackpos +
                      2];
            float maxz =
              stack[stackpos +
                      2];
            for (int j =
                   1,
                   idx =
                     stackpos +
                     5;
                 j <
                   4 *
                   4;
                 j++,
                   idx +=
                     3) {
                if (minz >
                      stack[idx])
                    minz =
                      stack[idx];
                if (maxz <
                      stack[idx])
                    maxz =
                      stack[idx];
            }
            t1 =
              (minz -
                 orgZ) *
                invDirZ;
            t2 =
              (maxz -
                 orgZ) *
                invDirZ;
            if (invDirZ >
                  0) {
                if (t1 >
                      intervalMin)
                    intervalMin =
                      t1;
                if (t2 <
                      intervalMax)
                    intervalMax =
                      t2;
            }
            else {
                if (t2 >
                      intervalMin)
                    intervalMin =
                      t2;
                if (t1 <
                      intervalMax)
                    intervalMax =
                      t1;
            }
            if (intervalMin >
                  intervalMax) {
                stackpos -=
                  STACKSIZE;
                continue;
            }
            float size =
              maxx -
              minx +
              (maxy -
                 miny) +
              (maxz -
                 minz);
            if (Float.
                  floatToRawIntBits(
                    stack[stackpos +
                            48]) ==
                  Float.
                  floatToRawIntBits(
                    size)) {
                r.
                  setMax(
                    intervalMin);
                state.
                  setIntersection(
                    primID,
                    stack[stackpos +
                            49],
                    stack[stackpos +
                            50]);
                stackpos -=
                  STACKSIZE;
                continue;
            }
            float sizeu =
              0;
            float sizev =
              0;
            for (int i =
                   0;
                 i <
                   3;
                 i++) {
                sizeu +=
                  Math.
                    abs(
                      stack[stackpos +
                              (0 *
                                 4 +
                                 3) *
                              3 +
                              i] -
                        stack[stackpos +
                                i]);
                sizev +=
                  Math.
                    abs(
                      stack[stackpos +
                              (3 *
                                 4 +
                                 0) *
                              3 +
                              i] -
                        stack[stackpos +
                                i]);
            }
            if (sizeu >
                  sizev) {
                for (int i =
                       0;
                     i <
                       4;
                     i++) {
                    for (int axis =
                           0;
                         axis <
                           3;
                         axis++) {
                        float p0 =
                          stack[stackpos +
                                  (i *
                                     4 +
                                     0) *
                                  3 +
                                  axis];
                        float p1 =
                          stack[stackpos +
                                  (i *
                                     4 +
                                     1) *
                                  3 +
                                  axis];
                        float p2 =
                          stack[stackpos +
                                  (i *
                                     4 +
                                     2) *
                                  3 +
                                  axis];
                        float p3 =
                          stack[stackpos +
                                  (i *
                                     4 +
                                     3) *
                                  3 +
                                  axis];
                        float q0 =
                          p0;
                        float q1 =
                          (p0 +
                             p1) *
                          0.5F;
                        float q2 =
                          q1 *
                          0.5F +
                          (p1 +
                             p2) *
                          0.25F;
                        float r3 =
                          p3;
                        float r2 =
                          (p2 +
                             p3) *
                          0.5F;
                        float r1 =
                          r2 *
                          0.5F +
                          (p1 +
                             p2) *
                          0.25F;
                        float q3 =
                          (q2 +
                             r1) *
                          0.5F;
                        float r0 =
                          q3;
                        stack[stackpos +
                                (i *
                                   4 +
                                   0) *
                                3 +
                                axis] =
                          q0;
                        stack[stackpos +
                                (i *
                                   4 +
                                   1) *
                                3 +
                                axis] =
                          q1;
                        stack[stackpos +
                                (i *
                                   4 +
                                   2) *
                                3 +
                                axis] =
                          q2;
                        stack[stackpos +
                                (i *
                                   4 +
                                   3) *
                                3 +
                                axis] =
                          q3;
                        stack[stackpos +
                                STACKSIZE +
                                (i *
                                   4 +
                                   0) *
                                3 +
                                axis] =
                          r0;
                        stack[stackpos +
                                STACKSIZE +
                                (i *
                                   4 +
                                   1) *
                                3 +
                                axis] =
                          r1;
                        stack[stackpos +
                                STACKSIZE +
                                (i *
                                   4 +
                                   2) *
                                3 +
                                axis] =
                          r2;
                        stack[stackpos +
                                STACKSIZE +
                                (i *
                                   4 +
                                   3) *
                                3 +
                                axis] =
                          r3;
                    }
                }
                stack[stackpos +
                        48] =
                  (stack[stackpos +
                           STACKSIZE +
                           48] =
                     size);
                float umin =
                  stack[stackpos +
                          49];
                float umax =
                  stack[stackpos +
                          51];
                stack[stackpos +
                        49] =
                  umin;
                stack[stackpos +
                        STACKSIZE +
                        50] =
                  stack[stackpos +
                          50];
                stack[stackpos +
                        51] =
                  (stack[stackpos +
                           STACKSIZE +
                           49] =
                     (umin +
                        umax) *
                       0.5F);
                stack[stackpos +
                        STACKSIZE +
                        51] =
                  umax;
                stack[stackpos +
                        STACKSIZE +
                        52] =
                  stack[stackpos +
                          52];
            }
            else {
                for (int i =
                       0;
                     i <
                       4;
                     i++) {
                    for (int axis =
                           0;
                         axis <
                           3;
                         axis++) {
                        float p0 =
                          stack[stackpos +
                                  (0 *
                                     4 +
                                     i) *
                                  3 +
                                  axis];
                        float p1 =
                          stack[stackpos +
                                  (1 *
                                     4 +
                                     i) *
                                  3 +
                                  axis];
                        float p2 =
                          stack[stackpos +
                                  (2 *
                                     4 +
                                     i) *
                                  3 +
                                  axis];
                        float p3 =
                          stack[stackpos +
                                  (3 *
                                     4 +
                                     i) *
                                  3 +
                                  axis];
                        float q0 =
                          p0;
                        float q1 =
                          (p0 +
                             p1) *
                          0.5F;
                        float q2 =
                          q1 *
                          0.5F +
                          (p1 +
                             p2) *
                          0.25F;
                        float r3 =
                          p3;
                        float r2 =
                          (p2 +
                             p3) *
                          0.5F;
                        float r1 =
                          r2 *
                          0.5F +
                          (p1 +
                             p2) *
                          0.25F;
                        float q3 =
                          (q2 +
                             r1) *
                          0.5F;
                        float r0 =
                          q3;
                        stack[stackpos +
                                (0 *
                                   4 +
                                   i) *
                                3 +
                                axis] =
                          q0;
                        stack[stackpos +
                                (1 *
                                   4 +
                                   i) *
                                3 +
                                axis] =
                          q1;
                        stack[stackpos +
                                (2 *
                                   4 +
                                   i) *
                                3 +
                                axis] =
                          q2;
                        stack[stackpos +
                                (3 *
                                   4 +
                                   i) *
                                3 +
                                axis] =
                          q3;
                        stack[stackpos +
                                STACKSIZE +
                                (0 *
                                   4 +
                                   i) *
                                3 +
                                axis] =
                          r0;
                        stack[stackpos +
                                STACKSIZE +
                                (1 *
                                   4 +
                                   i) *
                                3 +
                                axis] =
                          r1;
                        stack[stackpos +
                                STACKSIZE +
                                (2 *
                                   4 +
                                   i) *
                                3 +
                                axis] =
                          r2;
                        stack[stackpos +
                                STACKSIZE +
                                (3 *
                                   4 +
                                   i) *
                                3 +
                                axis] =
                          r3;
                    }
                }
                stack[stackpos +
                        48] =
                  (stack[stackpos +
                           STACKSIZE +
                           48] =
                     size);
                float vmin =
                  stack[stackpos +
                          50];
                float vmax =
                  stack[stackpos +
                          52];
                stack[stackpos +
                        STACKSIZE +
                        49] =
                  stack[stackpos +
                          49];
                stack[stackpos +
                        50] =
                  vmin;
                stack[stackpos +
                        52] =
                  (stack[stackpos +
                           STACKSIZE +
                           50] =
                     (vmin +
                        vmax) *
                       0.5F);
                stack[stackpos +
                        STACKSIZE +
                        51] =
                  stack[stackpos +
                          51];
                stack[stackpos +
                        STACKSIZE +
                        52] =
                  vmax;
            }
            stackpos +=
              STACKSIZE;
        }
    }
    public void prepareShadingState(ShadingState state) {
        state.
          init(
            );
        state.
          getRay(
            ).
          getPoint(
            state.
              getPoint(
                ));
        Instance parent =
          state.
          getInstance(
            );
        float u =
          state.
          getU(
            );
        float v =
          state.
          getV(
            );
        float[] bu =
          bernstein(
            u);
        float[] bdu =
          bernsteinDeriv(
            u);
        float[] bv =
          bernstein(
            v);
        float[] bdv =
          bernsteinDeriv(
            v);
        getPatchPoint(
          u,
          v,
          patches[state.
                    getPrimitiveID(
                      )],
          bu,
          bv,
          bdu,
          bdv,
          new Point3(
            ),
          state.
            getNormal(
              ));
        state.
          getNormal(
            ).
          set(
            parent.
              transformNormalObjectToWorld(
                state.
                  getNormal(
                    )));
        state.
          getNormal(
            ).
          normalize(
            );
        state.
          getGeoNormal(
            ).
          set(
            state.
              getNormal(
                ));
        state.
          getUV(
            ).
          set(
            u,
            v);
        state.
          setShader(
            parent.
              getShader(
                0));
        state.
          setModifier(
            parent.
              getModifier(
                0));
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                state.
                  getNormal(
                    )));
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1170256240000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL0ZW2wU1/Xu2viFwQ9exsEGjIlioLulNInAEantQjBZg4UN" +
       "FY4SZ3b27u7g2Zlh5q5ZnLpJqCJQPmiVEkra1Kpa8iYQVaHpQ6ioUppEVEik" +
       "VaqoKpD+JApFKlJJ0qYhPefeee3sw4aPWpqzd+4959zzuuecOz5xlcyyTLLa" +
       "0NX9KVVnEZpjkT3qnRG236BWZGvszkHJtGiiT5UsaxjmRuWO1xo++fz76cYw" +
       "qRoh8yRN05nEFF2zdlBLV8dpIkYavNlNKs1YjDTG9kjjUjTLFDUaUyzWHSOz" +
       "faSMdMYcEaIgQhREiHIRoj0eFhDNoVo204cUksasveQ7JBQjVYaM4jGyPJ+J" +
       "IZlSxmYzyDUADjX4vguU4sQ5kyxzdRc6Fyj89OrokR8+1PiLCtIwQhoUbQjF" +
       "kUEIBpuMkPoMzcSpafUkEjQxQpo0ShND1FQkVZngco+QZktJaRLLmtQ1Ek5m" +
       "DWryPT3L1cuom5mVmW666iUVqiact1lJVUqBrgs9XYWGm3EeFKxTQDAzKcnU" +
       "IakcU7QEI0uDFK6OnfcDApBWZyhL6+5WlZoEE6RZ+E6VtFR0iJmKlgLUWXoW" +
       "dmGktSRTtLUhyWNSio4y0hLEGxRLgFXLDYEkjCwIonFO4KXWgJd8/rm67Z7D" +
       "j2hbtDCXOUFlFeWvAaL2ANEOmqQm1WQqCOtXxY5KC88cChMCyAsCyALnjW9f" +
       "+8aa9rNvC5zbiuBsj++hMhuVj8fnXljS17W+AsWoMXRLQefnac7Df9Be6c4Z" +
       "cPIWuhxxMeIsnt3xh92PvUyvhEldP6mSdTWbgThqkvWMoajUvI9q1JQYTfST" +
       "Wqol+vh6P6mGcUzRqJjdnkxalPWTSpVPVen8HUyUBBZoomoYK1pSd8aGxNJ8" +
       "nDMIIdXwkI3wLCLij/8ykorutCDco5IsaYqmRyF4qWTK6SiV9dE4WDedkcwx" +
       "KypnLaZnolZWS6r6vqhlylHdTLnvsm7SKKOWRVWJSXGVRnvphELNAWqlIxhw" +
       "xv9vqxxq3bgvFAKHLAmmAxVO0hZdTVBzVD6S7d107eToubB7PGx7MXIH7Bix" +
       "d4zgjhH/jhFvRxIK8Y3m487C6+CzMTj9kBfru4Ye3PrwoY4KCDdjXyUYPAyo" +
       "HaCvLc4mWe/zUkQ/T4QyxGnLzx44GPnshXtFnEZL5/Oi1OTssX2P73r0q2ES" +
       "zk/MqB5M1SH5IKZTN212Bg9kMb4NBz/65NTRSd07mnmZ3s4YhZR44juCjjB1" +
       "mSYgh3rsVy2TTo+emewMk0pII5A6mQShDlmpPbhH3snvdrIo6jILFE7qZkZS" +
       "cclJfXUsber7vBkeIXP5uAmcMhuPwgJ42uyzwX9xdZ6BcL6IKPRyQAuepTf/" +
       "+uwzp3+0en3Yn9AbfCVyiDKRHpq8IBk2KYX5vx0b/MHTVw8+wCMEMFYU26AT" +
       "YR8kC3AZmPWJt/e+f+ni8T+H3agKMaia2biqyDngcbu3C6QSFdIZ+r5zp5bR" +
       "E0pSwQDG4Pxvw8q1p/9xuFF4U4UZJxjWTM/Am1/cSx4799Cn7ZxNSMZS5mnu" +
       "oQkDzPM495imtB/lyD3+btszb0k/gUwL2c1SJihPWIRrRrjpo9xVqziMBNbW" +
       "IlhmFKzxidZCHy+3fby8qI8RdAZ2Cwkbg/grSx9Ero4oMlPPrzj/6NSKD8Ai" +
       "I6RGsaA96TFTRaqej+afJy5deXdO20ke+5VxyeKpqC7YLhR2A3lFnutQbwjd" +
       "7zYwGLrK9IKmkoHyNG7Xz+hk86WxZz96VeScYLENINNDR578MnL4SNjXkawo" +
       "aAr8NKIr4RLOEV75Ev5C8NzAB72BE6IqNffZpXGZWxsNrs7ycmLxLTZ/eGry" +
       "ty9OHhRqNOcX5E3Qb7763hd/jBy7/E6RrA+HV5dYEceXseJm9I8vc/9nuxo/" +
       "8PfPOPeC3FvEsAH6keiJZ1v7Nl7h9F4SROqlucJ6BrHl0X7t5cz1cEfVm2FS" +
       "PUIaZbuv3iWpWUw1IxA9ltNsQ++dt57fF4omqNtN8kuCnvVtG0y/nkVhjNg8" +
       "kEXGNXIhwk/b/Ry7g8OVCO4Q1RGHXegHRZPUHGQ1lWopluZ43QjuFbHdw0gF" +
       "HAUcbrDDHR0Wtmssvi9gdrZBjaDB1DWKictZE/Va0SNucw+LuSKub8ur1gP8" +
       "tHkmf/KlV95gF1ZvENG2qnSYBAnfOvBx6/DG9MM3UaOXBnwfZPnSwIl37rtd" +
       "fipMKlzPFdwS8om68/1VZ1K41mjDeV5rN/jPhmKp0Z+IR8usSQgeBMfK6Afh" +
       "NrDt0uKFZlPGYLw0TPxq0ev3vDB1kVe6HCnwDr5vdoMqWS6oEGzjsmxHMIhg" +
       "fWHo4Oswgl2FsYDvu4Ux1k9njEyZNd6hjyGICykQJnJi8a4yhHsRrCssdULU" +
       "Fv5WKYzi1r16xGixH2ecV/ds67Hi1oPWotowlXEJr8ek2srGE8q45Zqw0Si2" +
       "323wLLb3W1xivwkE43DErYyuFz/i1XFdV6mkld6o1X6ccbGNHrM3mrU3K4k6" +
       "OVmaYbstvaNFMYbftRlWww1LTouMsl6wzHlx0ZKfi9oLLhRu6cJId9DaCtCG" +
       "ffcOrIBtpa7OvPodP3BkKrH9ubVhO2R6GalluvEVlY5T1SfXbBHNrvL8NrgS" +
       "ni5b+a6bbo9sDVr8GmTgDhoZkJip5L7OOTxVJryPIvgeI3NTlH1LN9VEr57V" +
       "EpbDeEkBY76uaKlePVei//MUnI+T2Nf32Qr2lVRwvLiC+LqRY02VUeKnCH4M" +
       "Vo9TE5IsVTQ7281Evg54Bmz5Bm5dvufLyPcigp+DkV35vgkFcHxmQvIj8gQ8" +
       "u20hd89YyFrOsZYL6YINxYDj70UF/h7UoeavKx1ouyjeOtdxIU6WscHrCF5h" +
       "ZA4E2iCeYc64WAaqHNeVxLRmWez47oBtlgMzPjx+sc6UWfsdgt/gYbbTAS+i" +
       "B6cVbR6x5Ttvi3Z+xqKFp01g2J1QuI/4E9hCP9qQ+O0Z7OfbvFlGwXMIfg/l" +
       "IGskbO0mp9WuASeXwnPD1u7GLRn+Qpm1PyE4z0gjxMq2bMZN2qIETithkx0a" +
       "obAQUPzejP35Phxw1PfLyPpXBO8x0oRx7QjKkyQ/cdMKy+/I20DI+baw82cs" +
       "bAXnWOF21wXBskPaz5VwMDoKMPrxamuJHhA/ylO+2wdl9P0QwUW4uigOqas1" +
       "rvxyZgrfDYpethW+PGOFA1WvsG4PpSWsTZ4mV8tocg3Bx3BjMUxqSCb1E89M" +
       "FZ6D1oBE121Vrt/SUfi0zNq/EfwLhITw6pXgzp/KPw1FshG0jHXet1Js+VsK" +
       "/j8j/qcgn5xqqFk0tfMv4guI892/NkZqkllV9V8qfeMqsFdS4dLVOldM/LlR" +
       "zCP+L7iM1PtfueBfcNoQdLyNQVqoBPjjR6tkZLYPjffGfORHqoZ7KiDhsMZw" +
       "YqXRu5aKi7Z9s2mxjWbkveV9FMRrJf8/mHMFzIr/hI3Kp6a2bnvk2l3P8fsk" +
       "XLOkCd5g18RItfgeypniNXJ5SW4Or6otXZ/Pfa12pdNEzkXQbH8E9cuG48T/" +
       "ANsSUUJ1HAAA");
}
