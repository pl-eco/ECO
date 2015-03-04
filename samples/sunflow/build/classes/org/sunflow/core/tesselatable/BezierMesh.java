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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
