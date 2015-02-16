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
                                                 this.
                                                 bernstein(
                                                   u);
                                               float[] bdu =
                                                 this.
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
                                                     this.
                                                     bernstein(
                                                       v);
                                                   float[] bdv =
                                                     this.
                                                     bernsteinDeriv(
                                                       v);
                                                   this.
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
          getRobustStack();
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
              getMin();
            float intervalMax =
              r.
              getMax();
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
          init();
        state.
          getRay().
          getPoint(
            state.
              getPoint());
        Instance parent =
          state.
          getInstance();
        float u =
          state.
          getU();
        float v =
          state.
          getV();
        float[] bu =
          this.
          bernstein(
            u);
        float[] bdu =
          this.
          bernsteinDeriv(
            u);
        float[] bv =
          this.
          bernstein(
            v);
        float[] bdv =
          this.
          bernsteinDeriv(
            v);
        this.
          getPatchPoint(
            u,
            v,
            patches[state.
                      getPrimitiveID()],
            bu,
            bv,
            bdu,
            bdv,
            new Point3(
              ),
            state.
              getNormal());
        state.
          getNormal().
          set(
            parent.
              transformNormalObjectToWorld(
                state.
                  getNormal()));
        state.
          getNormal().
          normalize();
        state.
          getGeoNormal().
          set(
            state.
              getNormal());
        state.
          getUV().
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
                  getNormal()));
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170256240000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVaCWwc1Rl+uz42PsBH7pDY2DGEJPZu7I1jO6YFx87hsHaM" +
       "nYM4BGd29q09eHZm\nMvN2szER5VATCmpL1KIWCQJUSIGUq6IorcQVAS0lLQ" +
       "KqgkTFUVH1UAsqQtBUrVD//83MzuzseuPE\nsqV5c7z3/vf/33++533iU1Ji" +
       "6GS5aATZYY0awd6RIUE3aKxXFgxjJ3waE18rKRs6eZ2i+okvQvxS\njJGqiG" +
       "iEYgITQlIs1N/XndbJWk2VD4/LKgvSNAveLLdb9LZH2nMI7jlxuvb2R4vr/a" +
       "QkQqoERVGZ\nwCRV2SzThMFIdeRmISWEkkySQxHJYN0RcglVkoleVTGYoDDj" +
       "ILmVFEVIqSYiTUYaIvbiIVg8pAm6\nkAjx5UNDfFmgMF+nTJAUGuvJLAczm7" +
       "NnAtvWvOHc0UBkHnbuBnE4ByD15RmpTWlzRNWKHtu94cjD\njxeRqlFSJSkj" +
       "SEwESRisN0oqEzQRpbrRE4vR2CipUSiNjVBdEmRpiq86SmoNaVwRWFKnxjA1" +
       "VDmF\nA2uNpEZ1vqb9MUIqRZRJT4pM1TMYxSUqx+y3krgsjIPYixyxTXG34H" +
       "cQsFwCxvS4IFJ7SvGkpIDG\n670zMjI2XQcDYGogQdmEmlmqWBHgA6k1dSkL" +
       "ynhohOmSMg5DS9QkrMLIsmmJItaaIE4K43SMkSXe\ncUNmF4wq40DgFEYWeo" +
       "dxSqClZR4tufSzdtFXdz32wIvXctsujlFRRv7LYVKdZ9IwjVOdKiI1J55L\n" +
       "Bn/Yvze53E8IDF7oGWyO6bni9K7I316qN8dclmfMjujNVGRj4uDx+uFbtqqk" +
       "CNmYp6mGhMrPkpy7\nw5DV053WwGsXZShiZ9DufHn4V3tvO0X/4Sfl/aRUVO" +
       "VkAuyoRlQTmiRTfStVqC4wGusnZVSJ9fL+\nfhKA5wiYvPl1RzxuUNZPimX+" +
       "qVTl7wBRHEggRGXwLClx1X7WBDbBn9MaISQAF/kmXIuJ+cfvjHQH\nQ0ZSic" +
       "vqoZChiyFVH8+8i6pOQ4waBpUhsERlGtpEpySqD1BjIohGpDGyJzShJmhIEA" +
       "VFUtTQuARu\nK6otMZrC+8WTTiPntYd8PgyFXpeWwRu2qXKM6mPiyU/eOLL5" +
       "uu/cZZoLmrglMyNXwYpBa8Ugrhh0\nrxh0ViQ+H19oAa5sag5wnwQPhlhXuX" +
       "pk//YDdzUWgcloh4oBND8MbQTpLHY2i2qv4+b9PCKKYGtL\nfrLvWPDcyWtM" +
       "WwtNH43zzq5468mzD39RucZP/PlDJYoJwbocyQxhfM2EwCavc+Wj/9ndA8++" +
       "e/aD\nqxw3Y6Qpx/tzZ6L3NnoVoqsijUE8dMg/urSqaA/ZfdxPiiEkQBjk/E" +
       "OEqfOukeXF3XZERFkCEVIR\nV/WEIGOXHcbK2YSuHnK+cEup5s/zQTkVaNYL" +
       "4Vph2Tm/Y+9CDdtFpmWhtj1S8Ih77s7Sde89X/Ea\nh8UOzlWu9DdCmenqNY" +
       "6x7NQphe8f/HjoB/d9emwftxTTVHwMcmIyKktiGqZc6UwBH5chzqAim3Yp\n" +
       "CTUmxSW0SrS4/1Vd0frcP79XbapGhi+2ZpvPT8D5vnQTue3sTf+u42R8IuYY" +
       "RwxnmCnNfIdyj64L\nh5GP9O3vrLj/18KDEAIh7BjSFOWRhHDJCMcxxHFfw9" +
       "ugp68Vm0ag3TyN6efJ6GPikVPjjcmDv/kl\n57pCcJcGbjUMCFq3qXlsViK6" +
       "i73eu00wJmDc+pcHb6yWX/4vUBwFiiJkUmOHDsEjnaVEa3RJ4P0z\nryw68H" +
       "YR8W8h5bIqxLYI3P5JGRgeBAuIO2ntmmu5bVUfmoctF5lwEJZZAPCX5blW2W" +
       "BZZUNeq8Tm\nSg+kPtOQ0GBdJSFXEjrB4/f2zR/u2ncnjxNlUKUIxqAdBHlt" +
       "iE8+EPWK6QNQhtiYuGr/6X+deZGu\n4mDNkwworXr08TwZ2zXnc+EUHXgvfo" +
       "L7enFUMPjqld5SJ7eSySpQOAKXaiZy39BQo0vcNbAuJSCX\npniY+ORo4wuv" +
       "73romBlaVxcodN2zxsRvffTxZNH3z0TNed56wjP4eN2jf3n2k+EFphuaRdfK" +
       "nLrH\nPccsvLggVVyAhkIr8NGvrm144tbhDy2OarPLh81QYv/18Ct01dXf/V" +
       "Oe/AbhSRWYpmmQxsu55tva\nuro6NO+XTuCkFqDE7UFQigUjqijI/X2PnKl4" +
       "53hyw3Zz6UtcA/r7jjyzvXLeI/cc9aOaLCMqc9VB\n1nsgJeiDDkt4izByYI" +
       "5qgo3hcHNbW0u4i5EAFDfiBDU41lcXCEIj2HTxrjZsNpr21X5eB0673oqN\n" +
       "gka2Ba3cSX1j0ebHIm/seJBrbNrMncf+PHSmXtx14tzv2IecjpNCcXZDOrcq" +
       "Ak915na+m6opfeah\nhJ8ERkm1aG3TdgtyEhPVKPiiYe/dYCuX1Z+9QzDL4e" +
       "5MibDc6wCuZb3J27FWeMbRPCx48nUlor3E\nuuznrMjoI/xhP5/SxNtVmewa" +
       "0HQpJeDWjQSMZDQmpQw+YpMVSLYyUgRxx8z5GSu46XxWcEM2f5fB\ntdTib+" +
       "k0/PFaG7ZGpUZCVdmEl4tAVFVlKigeTqQL5GSZddnP+TjRLE5KDiYFM7hOel" +
       "Y9eIGr1lkY\n2FjkWzVprWq7ZiaOW3e8bfGGpnZPaDJdoO+dLdFTceVUjIef" +
       "cp5FenCKZUxl/IsrzRWpmoFbM9cZ\niEWpaYdmmBF5EJspRoS5ik7ru9Y1t3" +
       "W2tEN4qt65rX8kaAER3JfeD1YILXJw262wS8yVGPm0ACa1\nPCBfyksTdEKe" +
       "B9ydEPuLhzf39OXUCFx1mq2Ro7ke48esISkC31OvBqcplakybu4TRWzSlsKQ" +
       "qt/a\n7eD7YmaViJyhXllVKIJg95k7J0kNZo5KoDOdw59OVmTtmwZ4/nfC1t" +
       "2P//Q0e3vtRjMlrZk+5Hon\nrtn48FT9xqfvuYjdUr0njnpJ16Quu75oQnqd" +
       "26IVBXPOXrIndWfHvnLgJ6krO7Mi4OWmR6TzlXzu\nBPajAn33Y3MfqFREfZ" +
       "jqA4zr8+8SNic0xuv6qV8s/vnVJ098iChr6Sx/3MDXuYVBnYyfWrvCHZhu\n" +
       "59pnwu2MVLp9Brl40Ikl+HqnJ4alCsQwF6rLso24LudMIFOTITT2sBU5w3a6" +
       "xMDSbsV0J1i8rDt2\nw+eVR4VX9/stTW1jsH1QtRaZpqjs4quCPx/P3ie0wb" +
       "XacvfV3mh73n2CJcEStwQJgU0EBwSmS+n1\nnMLPCljVc9g8xcil45TtUXU5" +
       "tklNKjHDJrw8hzDvl5TxTWraUc/TMy20PAjwfHMlXL0WAr3TIjCW\nHwGeaP" +
       "iolwpIeQab50EtUaqD21JJmT5Rhdeta+W9A9i8ykh0zvyhs7O5rbUlHF4D+L" +
       "MJyQhms8eh\nfWE20K6Fa8CCduDioX2zALRvYXMWBMjw3gdZIVUQ37CD7+/n" +
       "FN8ucwsB+M7PxtfhkYP829mA/G24\n9log750xyGWcYhnHhzcFEWt3EPtoDv" +
       "dcANj6rpYNrVByiEyXC7K0wWHpz3PL0oZwS0eYEX80WZCh\nDoehv88tQx3t" +
       "LZ3tyFBhM+90GPpsbhnq7Gjp6oSyMxorDFGXw9EXc+h4wFHrunUtra2tnKeU" +
       "nU0W\n52STIRX2a+Hp09huisdxYS7NBwWi0P+weZ+RSyCNDWEZwQl7t2XFKV" +
       "WKOV7/x4v0ep63m+C6w/L6\nO2act108+wIF+jAu+IqxjrBwzhTfM6lpUDpf" +
       "yWyk64brTUu6N2csnf+85RcW45RR3V1+LXIPGzHv\nPUP9HIf5BTBaik017G" +
       "qSGuyveEU86YhfMxvx8cD2a0v8ry9KuQ0F+lZiUwcbRzDWwWQioz8+WHRE\n" +
       "qJ+NCO2Qwf2mBOb9QjTIGeENZ3htAWFasLmKkRr0PFsSXiQ6WY1Ls3o20gyC" +
       "FAssaRbMWJoiTrEo\ns2/Nscdh4bCDOYxozBnRj8fYhrmrwh8PUC50ewFAur" +
       "FpY6RWsqdmYMHhXzuIhGeDSAcg8bGFyMcz\nRsSzb8jd+YxMCFjdO6L2FhB1" +
       "KzbXQnGl6VQTdOqe7JG1Zzaywrq+Ly1Zv7wod9xRoO96bCIgBVjw\nJmESBH" +
       "A88oKj7sCMj5whLzt5E3fyS3J+zGL+AEOMvH/LjV9F/vAf818u9o8kKiJkXj" +
       "wpy+5zV9dz\nKegkLnF0KsxTWF4T+Ebzad2dzWFz7n5Fpn17zbn7IWZ552Kp" +
       "CDf3sAOMVLiG8cNa/uQeJEJpAIPw\nMabZMFc7p07mWXQ6CzNEaWXWORH/rZ" +
       "F9lpM0f200Jt7w5L7L0/fsvJcfEJWIsjA1hWTKIyRg/ouZ\nU8XzoIZpqdm0" +
       "3iLPPL37+ae67M09/xfkAuv/yjmG22b2Tm8D2PGA9n/AVmJm9yUAAA==");
}
