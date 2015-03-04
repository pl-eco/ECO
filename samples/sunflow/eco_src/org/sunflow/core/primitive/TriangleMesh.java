package org.sunflow.core.primitive;
import java.io.FileWriter;
import java.io.IOException;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Instance;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.core.ParameterList.FloatParameter;
import org.sunflow.core.ParameterList.InterpolationType;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.MathUtils;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class TriangleMesh implements PrimitiveList {
    private static boolean smallTriangles = false;
    protected float[] points;
    protected int[] triangles;
    private WaldTriangle[] triaccel;
    private FloatParameter normals;
    private FloatParameter uvs;
    private byte[] faceShaders;
    public static void setSmallTriangles(boolean smallTriangles) { if (smallTriangles)
                                                                       UI.
                                                                         printInfo(
                                                                           Module.
                                                                             GEOM,
                                                                           "Small trimesh mode: enabled");
                                                                   else
                                                                       UI.
                                                                         printInfo(
                                                                           Module.
                                                                             GEOM,
                                                                           "Small trimesh mode: disabled");
                                                                   TriangleMesh.
                                                                     smallTriangles =
                                                                     smallTriangles;
    }
    public TriangleMesh() { super();
                            triangles = null;
                            points = null;
                            normals = (uvs =
                                         new FloatParameter(
                                           ));
                            faceShaders =
                              null; }
    public void writeObj(String filename) {
        try {
            FileWriter file =
              new FileWriter(
              filename);
            file.
              write(
                String.
                  format(
                    "o object\n"));
            for (int i =
                   0;
                 i <
                   points.
                     length;
                 i +=
                   3)
                file.
                  write(
                    String.
                      format(
                        "v %g %g %g\n",
                        points[i],
                        points[i +
                                 1],
                        points[i +
                                 2]));
            file.
              write(
                "s off\n");
            for (int i =
                   0;
                 i <
                   triangles.
                     length;
                 i +=
                   3)
                file.
                  write(
                    String.
                      format(
                        "f %d %d %d\n",
                        triangles[i] +
                          1,
                        triangles[i +
                                    1] +
                          1,
                        triangles[i +
                                    2] +
                          1));
            file.
              close();
        }
        catch (IOException e) {
            e.
              printStackTrace();
        }
    }
    public boolean update(ParameterList pl,
                          SunflowAPI api) {
        boolean updatedTopology =
          false;
        {
            int[] triangles =
              pl.
              getIntArray(
                "triangles");
            if (triangles !=
                  null) {
                this.
                  triangles =
                  triangles;
                updatedTopology =
                  true;
            }
        }
        if (triangles ==
              null) {
            UI.
              printError(
                Module.
                  GEOM,
                "Unable to update mesh - triangle indices are missing");
            return false;
        }
        if (triangles.
              length %
              3 !=
              0)
            UI.
              printWarning(
                Module.
                  GEOM,
                ("Triangle index data is not a multiple of 3 - triangles may b" +
                 "e missing"));
        pl.
          setFaceCount(
            triangles.
              length /
              3);
        {
            FloatParameter pointsP =
              pl.
              getPointArray(
                "points");
            if (pointsP !=
                  null)
                if (pointsP.
                      interp !=
                      InterpolationType.
                        VERTEX)
                    UI.
                      printError(
                        Module.
                          GEOM,
                        ("Point interpolation type must be set to \"vertex\" - was \"%" +
                         "s\""),
                        pointsP.
                          interp.
                          name().
                          toLowerCase());
                else {
                    points =
                      pointsP.
                        data;
                    updatedTopology =
                      true;
                }
        }
        if (points ==
              null) {
            UI.
              printError(
                Module.
                  GEOM,
                "Unable to update mesh - vertices are missing");
            return false;
        }
        pl.
          setVertexCount(
            points.
              length /
              3);
        pl.
          setFaceVertexCount(
            3 *
              (triangles.
                 length /
                 3));
        FloatParameter normals =
          pl.
          getVectorArray(
            "normals");
        if (normals !=
              null)
            this.
              normals =
              normals;
        FloatParameter uvs =
          pl.
          getTexCoordArray(
            "uvs");
        if (uvs !=
              null)
            this.
              uvs =
              uvs;
        int[] faceShaders =
          pl.
          getIntArray(
            "faceshaders");
        if (faceShaders !=
              null &&
              faceShaders.
                length ==
              triangles.
                length /
              3) {
            this.
              faceShaders =
              (new byte[faceShaders.
                          length]);
            for (int i =
                   0;
                 i <
                   faceShaders.
                     length;
                 i++) {
                int v =
                  faceShaders[i];
                if (v >
                      255)
                    UI.
                      printWarning(
                        Module.
                          GEOM,
                        "Shader index too large on triangle %d",
                        i);
                this.
                  faceShaders[i] =
                  (byte)
                    (v &
                       255);
            }
        }
        if (updatedTopology) {
            this.
              init();
        }
        return true;
    }
    public float getPrimitiveBound(int primID,
                                   int i) {
        int tri =
          3 *
          primID;
        int a =
          3 *
          triangles[tri +
                      0];
        int b =
          3 *
          triangles[tri +
                      1];
        int c =
          3 *
          triangles[tri +
                      2];
        int axis =
          i >>>
          1;
        if ((i &
               1) ==
              0)
            return MathUtils.
              min(
                points[a +
                         axis],
                points[b +
                         axis],
                points[c +
                         axis]);
        else
            return MathUtils.
              max(
                points[a +
                         axis],
                points[b +
                         axis],
                points[c +
                         axis]);
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) {
        BoundingBox bounds =
          new BoundingBox(
          );
        if (o2w ==
              null) {
            for (int i =
                   0;
                 i <
                   points.
                     length;
                 i +=
                   3)
                bounds.
                  include(
                    points[i],
                    points[i +
                             1],
                    points[i +
                             2]);
        }
        else {
            for (int i =
                   0;
                 i <
                   points.
                     length;
                 i +=
                   3) {
                float x =
                  points[i];
                float y =
                  points[i +
                           1];
                float z =
                  points[i +
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
        return bounds;
    }
    public void intersectPrimitiveRobust(Ray r,
                                         int primID,
                                         IntersectionState state) {
        int tri =
          3 *
          primID;
        int a =
          3 *
          triangles[tri +
                      0];
        int b =
          3 *
          triangles[tri +
                      1];
        int c =
          3 *
          triangles[tri +
                      2];
        final float[] stack =
          state.
          getRobustStack();
        for (int i =
               0,
               i3 =
                 0;
             i <
               3;
             i++,
               i3 +=
                 3) {
            stack[i3 +
                    0] =
              points[a +
                       i];
            stack[i3 +
                    1] =
              points[b +
                       i];
            stack[i3 +
                    2] =
              points[c +
                       i];
        }
        stack[9] =
          Float.
            POSITIVE_INFINITY;
        int stackpos =
          0;
        float orgX =
          r.
            ox;
        float dirX =
          r.
            dx;
        float invDirX =
          1 /
          dirX;
        float orgY =
          r.
            oy;
        float dirY =
          r.
            dy;
        float invDirY =
          1 /
          dirY;
        float orgZ =
          r.
            oz;
        float dirZ =
          r.
            dz;
        float invDirZ =
          1 /
          dirZ;
        float t1;
        float t2;
        float minx;
        float maxx;
        float miny;
        float maxy;
        float minz;
        float maxz;
        float mint =
          r.
          getMin();
        float maxt =
          r.
          getMax();
        while (stackpos >=
                 0) {
            float intervalMin =
              mint;
            float intervalMax =
              maxt;
            float p0x =
              stack[stackpos +
                      0];
            float p1x =
              stack[stackpos +
                      1];
            float p2x =
              stack[stackpos +
                      2];
            t1 =
              ((minx =
                  MathUtils.
                    min(
                      p0x,
                      p1x,
                      p2x)) -
                 orgX) *
                invDirX;
            t2 =
              ((maxx =
                  MathUtils.
                    max(
                      p0x,
                      p1x,
                      p2x)) -
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
                  10;
                continue;
            }
            float p0y =
              stack[stackpos +
                      3];
            float p1y =
              stack[stackpos +
                      4];
            float p2y =
              stack[stackpos +
                      5];
            t1 =
              ((miny =
                  MathUtils.
                    min(
                      p0y,
                      p1y,
                      p2y)) -
                 orgY) *
                invDirY;
            t2 =
              ((maxy =
                  MathUtils.
                    max(
                      p0y,
                      p1y,
                      p2y)) -
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
                  10;
                continue;
            }
            float p0z =
              stack[stackpos +
                      6];
            float p1z =
              stack[stackpos +
                      7];
            float p2z =
              stack[stackpos +
                      8];
            t1 =
              ((minz =
                  MathUtils.
                    min(
                      p0z,
                      p1z,
                      p2z)) -
                 orgZ) *
                invDirZ;
            t2 =
              ((maxz =
                  MathUtils.
                    max(
                      p0z,
                      p1z,
                      p2z)) -
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
                  10;
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
                            9]) ==
                  Float.
                  floatToRawIntBits(
                    size)) {
                r.
                  setMax(
                    intervalMin);
                triaccel[primID].
                  intersectBox(
                    r,
                    p0x,
                    p0y,
                    p0z,
                    primID,
                    state);
                return;
            }
            float p01x =
              (p0x +
                 p1x) *
              0.5F;
            float p01y =
              (p0y +
                 p1y) *
              0.5F;
            float p01z =
              (p0z +
                 p1z) *
              0.5F;
            float p12x =
              (p1x +
                 p2x) *
              0.5F;
            float p12y =
              (p1y +
                 p2y) *
              0.5F;
            float p12z =
              (p1z +
                 p2z) *
              0.5F;
            float p20x =
              (p2x +
                 p0x) *
              0.5F;
            float p20y =
              (p2y +
                 p0y) *
              0.5F;
            float p20z =
              (p2z +
                 p0z) *
              0.5F;
            stack[stackpos +
                    0] =
              p0x;
            stack[stackpos +
                    1] =
              p01x;
            stack[stackpos +
                    2] =
              p20x;
            stack[stackpos +
                    3] =
              p0y;
            stack[stackpos +
                    4] =
              p01y;
            stack[stackpos +
                    5] =
              p20y;
            stack[stackpos +
                    6] =
              p0z;
            stack[stackpos +
                    7] =
              p01z;
            stack[stackpos +
                    8] =
              p20z;
            stack[stackpos +
                    9] =
              size;
            stackpos +=
              10;
            stack[stackpos +
                    0] =
              p1x;
            stack[stackpos +
                    1] =
              p12x;
            stack[stackpos +
                    2] =
              p01x;
            stack[stackpos +
                    3] =
              p1y;
            stack[stackpos +
                    4] =
              p12y;
            stack[stackpos +
                    5] =
              p01y;
            stack[stackpos +
                    6] =
              p1z;
            stack[stackpos +
                    7] =
              p12z;
            stack[stackpos +
                    8] =
              p01z;
            stack[stackpos +
                    9] =
              size;
            stackpos +=
              10;
            stack[stackpos +
                    0] =
              p2x;
            stack[stackpos +
                    1] =
              p20x;
            stack[stackpos +
                    2] =
              p12x;
            stack[stackpos +
                    3] =
              p2y;
            stack[stackpos +
                    4] =
              p20y;
            stack[stackpos +
                    5] =
              p12y;
            stack[stackpos +
                    6] =
              p2z;
            stack[stackpos +
                    7] =
              p20z;
            stack[stackpos +
                    8] =
              p12z;
            stack[stackpos +
                    9] =
              size;
            stackpos +=
              10;
            stack[stackpos +
                    0] =
              p20x;
            stack[stackpos +
                    1] =
              p12x;
            stack[stackpos +
                    2] =
              p01x;
            stack[stackpos +
                    3] =
              p20y;
            stack[stackpos +
                    4] =
              p12y;
            stack[stackpos +
                    5] =
              p01y;
            stack[stackpos +
                    6] =
              p20z;
            stack[stackpos +
                    7] =
              p12z;
            stack[stackpos +
                    8] =
              p01z;
            stack[stackpos +
                    9] =
              size;
        }
    }
    final private void intersectTriangleKensler(Ray r,
                                                int primID,
                                                IntersectionState state) {
        int tri =
          3 *
          primID;
        int a =
          3 *
          triangles[tri +
                      0];
        int b =
          3 *
          triangles[tri +
                      1];
        int c =
          3 *
          triangles[tri +
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
        if (!r.
              isInside(
                t))
            return;
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
            return;
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
            return;
        float gamma =
          iv *
          v2;
        if (gamma <
              0)
            return;
        r.
          setMax(
            t);
        state.
          setIntersection(
            primID,
            beta,
            gamma);
    }
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
        if (triaccel !=
              null) {
            triaccel[primID].
              intersect(
                r,
                primID,
                state);
            return;
        }
        this.
          intersectTriangleKensler(
            r,
            primID,
            state);
    }
    public int getNumPrimitives() { return triangles.
                                             length /
                                      3; }
    public void prepareShadingState(ShadingState state) {
        state.
          init();
        Instance parent =
          state.
          getInstance();
        int primID =
          state.
          getPrimitiveID();
        float u =
          state.
          getU();
        float v =
          state.
          getV();
        float w =
          1 -
          u -
          v;
        state.
          getRay().
          getPoint(
            state.
              getPoint());
        int tri =
          3 *
          primID;
        int index0 =
          triangles[tri +
                      0];
        int index1 =
          triangles[tri +
                      1];
        int index2 =
          triangles[tri +
                      2];
        Point3 v0p =
          this.
          getPoint(
            index0);
        Point3 v1p =
          this.
          getPoint(
            index1);
        Point3 v2p =
          this.
          getPoint(
            index2);
        Vector3 ng =
          Point3.
          normal(
            v0p,
            v1p,
            v2p);
        ng =
          parent.
            transformNormalObjectToWorld(
              ng);
        ng.
          normalize();
        state.
          getGeoNormal().
          set(
            ng);
        switch (normals.
                  interp) {
            case NONE:
            case FACE:
                {
                    state.
                      getNormal().
                      set(
                        ng);
                    break;
                }
            case VERTEX:
                {
                    int i30 =
                      3 *
                      index0;
                    int i31 =
                      3 *
                      index1;
                    int i32 =
                      3 *
                      index2;
                    float[] normals =
                      this.
                        normals.
                        data;
                    state.
                      getNormal().
                      x =
                      w *
                        normals[i30 +
                                  0] +
                        u *
                        normals[i31 +
                                  0] +
                        v *
                        normals[i32 +
                                  0];
                    state.
                      getNormal().
                      y =
                      w *
                        normals[i30 +
                                  1] +
                        u *
                        normals[i31 +
                                  1] +
                        v *
                        normals[i32 +
                                  1];
                    state.
                      getNormal().
                      z =
                      w *
                        normals[i30 +
                                  2] +
                        u *
                        normals[i31 +
                                  2] +
                        v *
                        normals[i32 +
                                  2];
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
                    break;
                }
            case FACEVARYING:
                {
                    int idx =
                      3 *
                      tri;
                    float[] normals =
                      this.
                        normals.
                        data;
                    state.
                      getNormal().
                      x =
                      w *
                        normals[idx +
                                  0] +
                        u *
                        normals[idx +
                                  3] +
                        v *
                        normals[idx +
                                  6];
                    state.
                      getNormal().
                      y =
                      w *
                        normals[idx +
                                  1] +
                        u *
                        normals[idx +
                                  4] +
                        v *
                        normals[idx +
                                  7];
                    state.
                      getNormal().
                      z =
                      w *
                        normals[idx +
                                  2] +
                        u *
                        normals[idx +
                                  5] +
                        v *
                        normals[idx +
                                  8];
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
                    break;
                }
        }
        float uv00 =
          0;
        float uv01 =
          0;
        float uv10 =
          0;
        float uv11 =
          0;
        float uv20 =
          0;
        float uv21 =
          0;
        switch (uvs.
                  interp) {
            case NONE:
            case FACE:
                {
                    state.
                      getUV().
                      x =
                      0;
                    state.
                      getUV().
                      y =
                      0;
                    break;
                }
            case VERTEX:
                {
                    int i20 =
                      2 *
                      index0;
                    int i21 =
                      2 *
                      index1;
                    int i22 =
                      2 *
                      index2;
                    float[] uvs =
                      this.
                        uvs.
                        data;
                    uv00 =
                      uvs[i20 +
                            0];
                    uv01 =
                      uvs[i20 +
                            1];
                    uv10 =
                      uvs[i21 +
                            0];
                    uv11 =
                      uvs[i21 +
                            1];
                    uv20 =
                      uvs[i22 +
                            0];
                    uv21 =
                      uvs[i22 +
                            1];
                    break;
                }
            case FACEVARYING:
                {
                    int idx =
                      tri <<
                      1;
                    float[] uvs =
                      this.
                        uvs.
                        data;
                    uv00 =
                      uvs[idx +
                            0];
                    uv01 =
                      uvs[idx +
                            1];
                    uv10 =
                      uvs[idx +
                            2];
                    uv11 =
                      uvs[idx +
                            3];
                    uv20 =
                      uvs[idx +
                            4];
                    uv21 =
                      uvs[idx +
                            5];
                    break;
                }
        }
        if (uvs.
              interp !=
              InterpolationType.
                NONE) {
            state.
              getUV().
              x =
              w *
                uv00 +
                u *
                uv10 +
                v *
                uv20;
            state.
              getUV().
              y =
              w *
                uv01 +
                u *
                uv11 +
                v *
                uv21;
            float du1 =
              uv00 -
              uv20;
            float du2 =
              uv10 -
              uv20;
            float dv1 =
              uv01 -
              uv21;
            float dv2 =
              uv11 -
              uv21;
            Vector3 dp1 =
              Point3.
              sub(
                v0p,
                v2p,
                new Vector3(
                  ));
            Vector3 dp2 =
              Point3.
              sub(
                v1p,
                v2p,
                new Vector3(
                  ));
            float determinant =
              du1 *
              dv2 -
              dv1 *
              du2;
            if (determinant ==
                  0.0F) {
                state.
                  setBasis(
                    OrthoNormalBasis.
                      makeFromW(
                        state.
                          getNormal()));
            }
            else {
                float invdet =
                  1.0F /
                  determinant;
                Vector3 dpdv =
                  new Vector3(
                  );
                dpdv.
                  x =
                  (-du2 *
                     dp1.
                       x +
                     du1 *
                     dp2.
                       x) *
                    invdet;
                dpdv.
                  y =
                  (-du2 *
                     dp1.
                       y +
                     du1 *
                     dp2.
                       y) *
                    invdet;
                dpdv.
                  z =
                  (-du2 *
                     dp1.
                       z +
                     du1 *
                     dp2.
                       z) *
                    invdet;
                dpdv =
                  parent.
                    transformVectorObjectToWorld(
                      dpdv);
                state.
                  setBasis(
                    OrthoNormalBasis.
                      makeFromWV(
                        state.
                          getNormal(),
                        dpdv));
            }
        }
        else
            state.
              setBasis(
                OrthoNormalBasis.
                  makeFromW(
                    state.
                      getNormal()));
        int shaderIndex =
          faceShaders ==
          null
          ? 0
          : faceShaders[primID] &
          255;
        state.
          setShader(
            parent.
              getShader(
                shaderIndex));
        state.
          setModifier(
            parent.
              getModifier(
                shaderIndex));
    }
    public void init() { triaccel = null;
                         int nt = this.getNumPrimitives();
                         if (!smallTriangles) {
                             if (nt >
                                   2000000) {
                                 UI.
                                   printWarning(
                                     Module.
                                       GEOM,
                                     "TRI - Too many triangles -- triaccel generation skipped");
                                 return;
                             }
                             triaccel =
                               (new WaldTriangle[nt]);
                             for (int i =
                                    0;
                                  i <
                                    nt;
                                  i++)
                                 triaccel[i] =
                                   new WaldTriangle(
                                     this,
                                     i);
                         } }
    protected Point3 getPoint(int i) { i *=
                                         3;
                                       return new Point3(
                                         points[i],
                                         points[i +
                                                  1],
                                         points[i +
                                                  2]);
    }
    public void getPoint(int tri, int i, Point3 p) {
        int index =
          3 *
          triangles[3 *
                      tri +
                      i];
        p.
          set(
            points[index],
            points[index +
                     1],
            points[index +
                     2]);
    }
    final private static class WaldTriangle {
        private int k;
        private float nu;
        private float nv;
        private float nd;
        private float bnu;
        private float bnv;
        private float bnd;
        private float cnu;
        private float cnv;
        private float cnd;
        private WaldTriangle(TriangleMesh mesh,
                             int tri) { super();
                                        k =
                                          0;
                                        tri *=
                                          3;
                                        int index0 =
                                          mesh.
                                            triangles[tri +
                                                        0];
                                        int index1 =
                                          mesh.
                                            triangles[tri +
                                                        1];
                                        int index2 =
                                          mesh.
                                            triangles[tri +
                                                        2];
                                        Point3 v0p =
                                          mesh.
                                          getPoint(
                                            index0);
                                        Point3 v1p =
                                          mesh.
                                          getPoint(
                                            index1);
                                        Point3 v2p =
                                          mesh.
                                          getPoint(
                                            index2);
                                        Vector3 ng =
                                          Point3.
                                          normal(
                                            v0p,
                                            v1p,
                                            v2p);
                                        if (Math.
                                              abs(
                                                ng.
                                                  x) >
                                              Math.
                                              abs(
                                                ng.
                                                  y) &&
                                              Math.
                                              abs(
                                                ng.
                                                  x) >
                                              Math.
                                              abs(
                                                ng.
                                                  z))
                                            k =
                                              0;
                                        else
                                            if (Math.
                                                  abs(
                                                    ng.
                                                      y) >
                                                  Math.
                                                  abs(
                                                    ng.
                                                      z))
                                                k =
                                                  1;
                                            else
                                                k =
                                                  2;
                                        float ax;
                                        float ay;
                                        float bx;
                                        float by;
                                        float cx;
                                        float cy;
                                        switch (k) {
                                            case 0:
                                                {
                                                    nu =
                                                      ng.
                                                        y /
                                                        ng.
                                                          x;
                                                    nv =
                                                      ng.
                                                        z /
                                                        ng.
                                                          x;
                                                    nd =
                                                      v0p.
                                                        x +
                                                        nu *
                                                        v0p.
                                                          y +
                                                        nv *
                                                        v0p.
                                                          z;
                                                    ax =
                                                      v0p.
                                                        y;
                                                    ay =
                                                      v0p.
                                                        z;
                                                    bx =
                                                      v2p.
                                                        y -
                                                        ax;
                                                    by =
                                                      v2p.
                                                        z -
                                                        ay;
                                                    cx =
                                                      v1p.
                                                        y -
                                                        ax;
                                                    cy =
                                                      v1p.
                                                        z -
                                                        ay;
                                                    break;
                                                }
                                            case 1:
                                                {
                                                    nu =
                                                      ng.
                                                        z /
                                                        ng.
                                                          y;
                                                    nv =
                                                      ng.
                                                        x /
                                                        ng.
                                                          y;
                                                    nd =
                                                      nv *
                                                        v0p.
                                                          x +
                                                        v0p.
                                                          y +
                                                        nu *
                                                        v0p.
                                                          z;
                                                    ax =
                                                      v0p.
                                                        z;
                                                    ay =
                                                      v0p.
                                                        x;
                                                    bx =
                                                      v2p.
                                                        z -
                                                        ax;
                                                    by =
                                                      v2p.
                                                        x -
                                                        ay;
                                                    cx =
                                                      v1p.
                                                        z -
                                                        ax;
                                                    cy =
                                                      v1p.
                                                        x -
                                                        ay;
                                                    break;
                                                }
                                            case 2:
                                            default:
                                                {
                                                    nu =
                                                      ng.
                                                        x /
                                                        ng.
                                                          z;
                                                    nv =
                                                      ng.
                                                        y /
                                                        ng.
                                                          z;
                                                    nd =
                                                      nu *
                                                        v0p.
                                                          x +
                                                        nv *
                                                        v0p.
                                                          y +
                                                        v0p.
                                                          z;
                                                    ax =
                                                      v0p.
                                                        x;
                                                    ay =
                                                      v0p.
                                                        y;
                                                    bx =
                                                      v2p.
                                                        x -
                                                        ax;
                                                    by =
                                                      v2p.
                                                        y -
                                                        ay;
                                                    cx =
                                                      v1p.
                                                        x -
                                                        ax;
                                                    cy =
                                                      v1p.
                                                        y -
                                                        ay;
                                                }
                                        }
                                        float det =
                                          bx *
                                          cy -
                                          by *
                                          cx;
                                        bnu =
                                          -by /
                                            det;
                                        bnv =
                                          bx /
                                            det;
                                        bnd =
                                          (by *
                                             ax -
                                             bx *
                                             ay) /
                                            det;
                                        cnu =
                                          cy /
                                            det;
                                        cnv =
                                          -cx /
                                            det;
                                        cnd =
                                          (cx *
                                             ay -
                                             cy *
                                             ax) /
                                            det;
        }
        void intersectBox(Ray r, float hx,
                          float hy,
                          float hz,
                          int primID,
                          IntersectionState state) {
            switch (k) {
                case 0:
                    {
                        float hu =
                          hy;
                        float hv =
                          hz;
                        float u =
                          hu *
                          bnu +
                          hv *
                          bnv +
                          bnd;
                        if (u <
                              0.0F)
                            u =
                              0;
                        float v =
                          hu *
                          cnu +
                          hv *
                          cnv +
                          cnd;
                        if (v <
                              0.0F)
                            v =
                              0;
                        state.
                          setIntersection(
                            primID,
                            u,
                            v);
                        return;
                    }
                case 1:
                    {
                        float hu =
                          hz;
                        float hv =
                          hx;
                        float u =
                          hu *
                          bnu +
                          hv *
                          bnv +
                          bnd;
                        if (u <
                              0.0F)
                            u =
                              0;
                        float v =
                          hu *
                          cnu +
                          hv *
                          cnv +
                          cnd;
                        if (v <
                              0.0F)
                            v =
                              0;
                        state.
                          setIntersection(
                            primID,
                            u,
                            v);
                        return;
                    }
                case 2:
                    {
                        float hu =
                          hx;
                        float hv =
                          hy;
                        float u =
                          hu *
                          bnu +
                          hv *
                          bnv +
                          bnd;
                        if (u <
                              0.0F)
                            u =
                              0;
                        float v =
                          hu *
                          cnu +
                          hv *
                          cnv +
                          cnd;
                        if (v <
                              0.0F)
                            v =
                              0;
                        state.
                          setIntersection(
                            primID,
                            u,
                            v);
                        return;
                    }
            }
        }
        void intersect(Ray r, int primID,
                       IntersectionState state) {
            switch (k) {
                case 0:
                    {
                        float det =
                          1.0F /
                          (r.
                             dx +
                             nu *
                             r.
                               dy +
                             nv *
                             r.
                               dz);
                        float t =
                          (nd -
                             r.
                               ox -
                             nu *
                             r.
                               oy -
                             nv *
                             r.
                               oz) *
                          det;
                        if (!r.
                              isInside(
                                t))
                            return;
                        float hu =
                          r.
                            oy +
                          t *
                          r.
                            dy;
                        float hv =
                          r.
                            oz +
                          t *
                          r.
                            dz;
                        float u =
                          hu *
                          bnu +
                          hv *
                          bnv +
                          bnd;
                        if (u <
                              0.0F)
                            return;
                        float v =
                          hu *
                          cnu +
                          hv *
                          cnv +
                          cnd;
                        if (v <
                              0.0F)
                            return;
                        if (u +
                              v >
                              1.0F)
                            return;
                        r.
                          setMax(
                            t);
                        state.
                          setIntersection(
                            primID,
                            u,
                            v);
                        return;
                    }
                case 1:
                    {
                        float det =
                          1.0F /
                          (r.
                             dy +
                             nu *
                             r.
                               dz +
                             nv *
                             r.
                               dx);
                        float t =
                          (nd -
                             r.
                               oy -
                             nu *
                             r.
                               oz -
                             nv *
                             r.
                               ox) *
                          det;
                        if (!r.
                              isInside(
                                t))
                            return;
                        float hu =
                          r.
                            oz +
                          t *
                          r.
                            dz;
                        float hv =
                          r.
                            ox +
                          t *
                          r.
                            dx;
                        float u =
                          hu *
                          bnu +
                          hv *
                          bnv +
                          bnd;
                        if (u <
                              0.0F)
                            return;
                        float v =
                          hu *
                          cnu +
                          hv *
                          cnv +
                          cnd;
                        if (v <
                              0.0F)
                            return;
                        if (u +
                              v >
                              1.0F)
                            return;
                        r.
                          setMax(
                            t);
                        state.
                          setIntersection(
                            primID,
                            u,
                            v);
                        return;
                    }
                case 2:
                    {
                        float det =
                          1.0F /
                          (r.
                             dz +
                             nu *
                             r.
                               dx +
                             nv *
                             r.
                               dy);
                        float t =
                          (nd -
                             r.
                               oz -
                             nu *
                             r.
                               ox -
                             nv *
                             r.
                               oy) *
                          det;
                        if (!r.
                              isInside(
                                t))
                            return;
                        float hu =
                          r.
                            ox +
                          t *
                          r.
                            dx;
                        float hv =
                          r.
                            oy +
                          t *
                          r.
                            dy;
                        float u =
                          hu *
                          bnu +
                          hv *
                          bnv +
                          bnd;
                        if (u <
                              0.0F)
                            return;
                        float v =
                          hu *
                          cnu +
                          hv *
                          cnv +
                          cnd;
                        if (v <
                              0.0F)
                            return;
                        if (u +
                              v >
                              1.0F)
                            return;
                        r.
                          setMax(
                            t);
                        state.
                          setIntersection(
                            primID,
                            u,
                            v);
                        return;
                    }
            }
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1169362888000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAALVYDWwcRxWeu7PP8Q/y2UmcxE3t2HEb8sMtoDaUOFXjGKd1" +
           "sqnN2XFSJ8Ed786d\n197b3ezOnc9OVFohNaGlhQgQVKJJVEVKWloa1KLwU0" +
           "qqtlAaIbVIFKlSAygSIEGREFIJkLa8mdm9\n3du7c9pItuTduZn33jfvd97s" +
           "0++iWsdGqxUnSecs4iT7R4ax7RC1X8eOMwpTE8qrtfXDZ3YZZhRF\nZBTVVI" +
           "qaZcWRVEyxpKnS4Bd6CzbaaJn6XEY3aZIUaHJav9WVt1O+tUzg3hPnWx84Xd" +
           "MZRbUyasaG\nYVJMNdMY0EnWoSghT+M8lnJU0yVZc2ivjD5BjFy23zQcig3q" +
           "HEL3oZiM4pbCZFLUJXvgEoBLFrZx\nVuLw0jCHBQlLbUKxZhC1rwgHnJtKOW" +
           "HbLl+qnBqELGGLY6AO3wFovaaotdC2TFUrdnZs85FTT8ZQ\n8zhq1owRJkwB" +
           "TSjgjaOmLMlOEtvpU1WijqMWgxB1hNga1rV5jjqOWh0tY2Cas4mTIo6p5xlh" +
           "q5Oz\niM0xvUkZNSlMJzunUNMu2iitEV31ftWmdZwBtdt8tYW6O9g8KNigwc" +
           "bsNFaIx1Izoxng8c4wR1HH\nnl1AAKx1WUKnzCJUjYFhArUKX+rYyEgj1NaM" +
           "DJDWmjlAoai9qlBmawsrMzhDJihaGaYbFktAVc8N\nwVgoWh4m45LAS+0hLw" +
           "X8s7HtvWNnv/fiNh7bNSpRdLb/BmDqCDGlSJrYxFCIYLySS35r8J7c6ihC\n" +
           "QLw8RCxo+m46v0f+6y86Bc0NFWiGJqeJQieUu493pg7faaIY28YSy3Q05vwS" +
           "zXk6DLsrvQULsrat\nKJEtJr3FC6lf3nP/U+RvUdQwiOKKqeeyEEctipm1NJ" +
           "3YdxKD2JgSdRDVE0Pt5+uDqA7GMoS8mB1K\npx1CB1GNzqfiJv8NJkqDCGai" +
           "ehhrRtr0xhamU3xcsBBCq+AfdSAU+S/if+JN0Zak5OSMtG7OSo6t\nSKadKf" +
           "5WTJtIlq1lQYc8kUYhCYyMTnYTZyrJYsiiaEyaMrNEwgo2NMOUMhpkrWJ+Si" +
           "V59r5uyQW2\n79bZSIQVwnBC65ALd5m6SuwJ5czl148M7PrqMREsLMBdjSm6" +
           "BQCTLmCSASaLgMkgYM9erKveBIpE\nOOgytgvhQ/DADOQyVL2m9SMHd957rD" +
           "sGwWPN1jAjAmk3KOpubUAx+/2EH+S1UYGoW/nE/qPJK2fu\nEFEnVa/LFbkb" +
           "33jm4ql/NW2IomjloslUhrLdwMQMs0pbLIY94TSrJP8fD+1+7q2L73zSTziK" +
           "esrq\nQDkny+PusHNsUyEqVEZf/OlVzbG9aOx4FNVAcYCCyPcPtaYjjFGSz7" +
           "1ebWS61MmoMW3aWayzJa+g\nNdAp25z1Z3jUJPh4KTgnwQJ8NULRiIh48War" +
           "yy32bBNRxrwd0oLX3itfiX/69y80vsrN4pXp5sBB\nOEKoSPoWP1hGbUJg/p" +
           "3vDn/z2+8e3c8jxQ0ViuogAPOQ5gXgudnngXTXoeQwT/bsMbKmqqU1PKkT\n" +
           "FnJXm2/6zI/+/mhC+EaHGc+1m64twJ9ftR3df/FL/+7gYiIKO258PXwyoc5S" +
           "X3KfbeM5to/CA7+9\n8bFf4cehGkIFcrR5wotKlKsW5SZfQdG6j5hyALIy2N" +
           "54RMyVlx/s/vlre04eFbmyfoEeJsg1oXz5\nD3+ciX39pUnBFz4qQsTHO07/" +
           "+bnLqWXCrOI8XVt2pAV5xJnKI6bZYg7sWgiBU7+ysevp+1KX3B21\nlp4MA9" +
           "A9/WXuZbJu6yN/qlC8YnDqczCJ23YDfyZZ/HKLI762lT26YS+bqlipQss0oR" +
           "x5KtOdO/Tr\nn3DURhzsvYLRvRtbQt0Ee6xlKq8IF8W7sDMFdLdcuPtAQr/w" +
           "P5A4DhIVaFWcIRvqc6EkN1zq2rq3\nX3q57d43Yyi6AzXoJlZ3YF5WUD3kM0" +
           "QHlPaCdcc2nrOJ2SVeJhcQN0K7a4BC4FeDs2Ck7GANl1+Q\nJiY3nZVfH3qc" +
           "G6BqPa0QRCE58y/uOXHlN/QSl+MXNsbdVSg/t6BJ9XlveyvfEj93MhtFdeMo" +
           "obht\n9BjWc6x8jEPX53i9NbTaJeulHZxoV3qLhXt1OIoDsOGS6occjBk1Gz" +
           "dVqqJtUL+uun3D1XAVjSA+\n2MkeN1MUmWGDzaK2suft7LFL+GtbVb8OlCJ2" +
           "AdL7LuL7VRCHXMSokePMn7UEyOco09PENLSH4UXY\nw97iHvJslApB7lsEyA" +
           "NFSLUS5MGPCdkDUB+4kB9UgcQuZGzSyFXCnFwEzLSPWdG0mUXAnPExK9pW\n" +
           "vw7MD13MD6tgWh6mUtm2hxYBM+djVrRtfhEw533MirY9vACmWOrhz3Win4qx" +
           "JNcMzG+H6ymKO/we\nHzwWEDu0bqx20+Rn9NF9/2x6EL9yMOoeqbeBIPcDgC" +
           "8nysSUdPm7+cXaL+cPPfn98/TNjVvEUb+h\n+lEUZtyw5dR855ZnH76O3r4z" +
           "pFhYdEv+hi/GprTXovzuL06Hsm8GpUy9pWdCA+wnZxujJSfDmqLn\nmX+QAu" +
           "bpE54X76DnfQ+XuM51p9/UxPl03Gsjl5W1kSk8VwwZ/7HZY+guYxhkH00c0d" +
           "WyDzyE7+X4\nAs3Ud9jjaxQ1aR7rdrMQPlZq8qam+iH7yLXSpD2o77FSy20H" +
           "i027lpuuaDn2eDS05ZgI/mua6mPb\n5okFbHOWPU5SVF+0DZt4zDfEqY9qiA" +
           "IYOHjfvp6bQ9kHPvFRSpHfPnzgPfl3/+EXzeKHo0YZLUnn\ndD3Y6wTGccsm" +
           "aY1r2Sg6H4u/fkhRe/U9gSmKY67COcH1PEWJMBdEDXsFyc5T1Bggg5uhOwoS" +
           "/RRK\nJRCx4c8sz04J3k6zzi8pOr/Seseub2tLqg//8upViJz49jqh7Htm/5" +
           "rCw6Pf4GWnVtHxPK/ODTKq\nE9fsYpXpqirNk/UGOvfs2As/+LxXQvl9YVkg" +
           "90uS4HaxukC4QGWrfLUdyFqUX0bnf7zi+a1nTlyK\n8tv1/wHJgbMnMBcAAA" +
           "==");
    }
    public PrimitiveList getBakingPrimitives() {
        switch (uvs.
                  interp) {
            case NONE:
            case FACE:
                UI.
                  printError(
                    Module.
                      GEOM,
                    ("Cannot generate baking surface without texture coordinate da" +
                     "ta"));
                return null;
            default:
                return new BakingSurface(
                  );
        }
    }
    private class BakingSurface implements PrimitiveList {
        public PrimitiveList getBakingPrimitives() {
            return null;
        }
        public int getNumPrimitives() { return TriangleMesh.this.
                                          getNumPrimitives();
        }
        public float getPrimitiveBound(int primID,
                                       int i) {
            if (i >
                  3)
                return 0;
            switch (uvs.
                      interp) {
                case NONE:
                case FACE:
                default:
                    {
                        return 0;
                    }
                case VERTEX:
                    {
                        int tri =
                          3 *
                          primID;
                        int index0 =
                          triangles[tri +
                                      0];
                        int index1 =
                          triangles[tri +
                                      1];
                        int index2 =
                          triangles[tri +
                                      2];
                        int i20 =
                          2 *
                          index0;
                        int i21 =
                          2 *
                          index1;
                        int i22 =
                          2 *
                          index2;
                        float[] uvs =
                          TriangleMesh.this.
                            uvs.
                            data;
                        switch (i) {
                            case 0:
                                return MathUtils.
                                  min(
                                    uvs[i20 +
                                          0],
                                    uvs[i21 +
                                          0],
                                    uvs[i22 +
                                          0]);
                            case 1:
                                return MathUtils.
                                  max(
                                    uvs[i20 +
                                          0],
                                    uvs[i21 +
                                          0],
                                    uvs[i22 +
                                          0]);
                            case 2:
                                return MathUtils.
                                  min(
                                    uvs[i20 +
                                          1],
                                    uvs[i21 +
                                          1],
                                    uvs[i22 +
                                          1]);
                            case 3:
                                return MathUtils.
                                  max(
                                    uvs[i20 +
                                          1],
                                    uvs[i21 +
                                          1],
                                    uvs[i22 +
                                          1]);
                            default:
                                return 0;
                        }
                    }
                case FACEVARYING:
                    {
                        int idx =
                          6 *
                          primID;
                        float[] uvs =
                          TriangleMesh.this.
                            uvs.
                            data;
                        switch (i) {
                            case 0:
                                return MathUtils.
                                  min(
                                    uvs[idx +
                                          0],
                                    uvs[idx +
                                          2],
                                    uvs[idx +
                                          4]);
                            case 1:
                                return MathUtils.
                                  max(
                                    uvs[idx +
                                          0],
                                    uvs[idx +
                                          2],
                                    uvs[idx +
                                          4]);
                            case 2:
                                return MathUtils.
                                  min(
                                    uvs[idx +
                                          1],
                                    uvs[idx +
                                          3],
                                    uvs[idx +
                                          5]);
                            case 3:
                                return MathUtils.
                                  max(
                                    uvs[idx +
                                          1],
                                    uvs[idx +
                                          3],
                                    uvs[idx +
                                          5]);
                            default:
                                return 0;
                        }
                    }
            }
        }
        public BoundingBox getWorldBounds(Matrix4 o2w) {
            BoundingBox bounds =
              new BoundingBox(
              );
            if (o2w ==
                  null) {
                for (int i =
                       0;
                     i <
                       uvs.
                         data.
                         length;
                     i +=
                       2)
                    bounds.
                      include(
                        uvs.
                          data[i],
                        uvs.
                          data[i +
                                 1],
                        0);
            }
            else {
                for (int i =
                       0;
                     i <
                       uvs.
                         data.
                         length;
                     i +=
                       2) {
                    float x =
                      uvs.
                        data[i];
                    float y =
                      uvs.
                        data[i +
                               1];
                    float wx =
                      o2w.
                      transformPX(
                        x,
                        y,
                        0);
                    float wy =
                      o2w.
                      transformPY(
                        x,
                        y,
                        0);
                    float wz =
                      o2w.
                      transformPZ(
                        x,
                        y,
                        0);
                    bounds.
                      include(
                        wx,
                        wy,
                        wz);
                }
            }
            return bounds;
        }
        public void intersectPrimitive(Ray r,
                                       int primID,
                                       IntersectionState state) {
            float uv00 =
              0;
            float uv01 =
              0;
            float uv10 =
              0;
            float uv11 =
              0;
            float uv20 =
              0;
            float uv21 =
              0;
            switch (uvs.
                      interp) {
                case NONE:
                case FACE:
                default:
                    return;
                case VERTEX:
                    {
                        int tri =
                          3 *
                          primID;
                        int index0 =
                          triangles[tri +
                                      0];
                        int index1 =
                          triangles[tri +
                                      1];
                        int index2 =
                          triangles[tri +
                                      2];
                        int i20 =
                          2 *
                          index0;
                        int i21 =
                          2 *
                          index1;
                        int i22 =
                          2 *
                          index2;
                        float[] uvs =
                          TriangleMesh.this.
                            uvs.
                            data;
                        uv00 =
                          uvs[i20 +
                                0];
                        uv01 =
                          uvs[i20 +
                                1];
                        uv10 =
                          uvs[i21 +
                                0];
                        uv11 =
                          uvs[i21 +
                                1];
                        uv20 =
                          uvs[i22 +
                                0];
                        uv21 =
                          uvs[i22 +
                                1];
                        break;
                    }
                case FACEVARYING:
                    {
                        int idx =
                          3 *
                          primID <<
                          1;
                        float[] uvs =
                          TriangleMesh.this.
                            uvs.
                            data;
                        uv00 =
                          uvs[idx +
                                0];
                        uv01 =
                          uvs[idx +
                                1];
                        uv10 =
                          uvs[idx +
                                2];
                        uv11 =
                          uvs[idx +
                                3];
                        uv20 =
                          uvs[idx +
                                4];
                        uv21 =
                          uvs[idx +
                                5];
                        break;
                    }
            }
            double edge1x =
              uv10 -
              uv00;
            double edge1y =
              uv11 -
              uv01;
            double edge2x =
              uv20 -
              uv00;
            double edge2y =
              uv21 -
              uv01;
            double pvecx =
              r.
                dy *
              0 -
              r.
                dz *
              edge2y;
            double pvecy =
              r.
                dz *
              edge2x -
              r.
                dx *
              0;
            double pvecz =
              r.
                dx *
              edge2y -
              r.
                dy *
              edge2x;
            double qvecx;
            double qvecy;
            double qvecz;
            double u;
            double v;
            double det =
              edge1x *
              pvecx +
              edge1y *
              pvecy +
              0 *
              pvecz;
            if (det >
                  0) {
                double tvecx =
                  r.
                    ox -
                  uv00;
                double tvecy =
                  r.
                    oy -
                  uv01;
                double tvecz =
                  r.
                    oz;
                u =
                  tvecx *
                    pvecx +
                    tvecy *
                    pvecy +
                    tvecz *
                    pvecz;
                if (u <
                      0.0 ||
                      u >
                      det)
                    return;
                qvecx =
                  tvecy *
                    0 -
                    tvecz *
                    edge1y;
                qvecy =
                  tvecz *
                    edge1x -
                    tvecx *
                    0;
                qvecz =
                  tvecx *
                    edge1y -
                    tvecy *
                    edge1x;
                v =
                  r.
                    dx *
                    qvecx +
                    r.
                      dy *
                    qvecy +
                    r.
                      dz *
                    qvecz;
                if (v <
                      0.0 ||
                      u +
                      v >
                      det)
                    return;
            }
            else
                if (det <
                      0) {
                    double tvecx =
                      r.
                        ox -
                      uv00;
                    double tvecy =
                      r.
                        oy -
                      uv01;
                    double tvecz =
                      r.
                        oz;
                    u =
                      tvecx *
                        pvecx +
                        tvecy *
                        pvecy +
                        tvecz *
                        pvecz;
                    if (u >
                          0.0 ||
                          u <
                          det)
                        return;
                    qvecx =
                      tvecy *
                        0 -
                        tvecz *
                        edge1y;
                    qvecy =
                      tvecz *
                        edge1x -
                        tvecx *
                        0;
                    qvecz =
                      tvecx *
                        edge1y -
                        tvecy *
                        edge1x;
                    v =
                      r.
                        dx *
                        qvecx +
                        r.
                          dy *
                        qvecy +
                        r.
                          dz *
                        qvecz;
                    if (v >
                          0.0 ||
                          u +
                          v <
                          det)
                        return;
                }
                else
                    return;
            double inv_det =
              1.0 /
              det;
            float t =
              (float)
                ((edge2x *
                    qvecx +
                    edge2y *
                    qvecy +
                    0 *
                    qvecz) *
                   inv_det);
            if (r.
                  isInside(
                    t)) {
                r.
                  setMax(
                    t);
                state.
                  setIntersection(
                    primID,
                    (float)
                      (u *
                         inv_det),
                    (float)
                      (v *
                         inv_det));
            }
        }
        public void prepareShadingState(ShadingState state) {
            state.
              init();
            Instance parent =
              state.
              getInstance();
            int primID =
              state.
              getPrimitiveID();
            float u =
              state.
              getU();
            float v =
              state.
              getV();
            float w =
              1 -
              u -
              v;
            int tri =
              3 *
              primID;
            int index0 =
              triangles[tri +
                          0];
            int index1 =
              triangles[tri +
                          1];
            int index2 =
              triangles[tri +
                          2];
            Point3 v0p =
              TriangleMesh.this.
              getPoint(
                index0);
            Point3 v1p =
              TriangleMesh.this.
              getPoint(
                index1);
            Point3 v2p =
              TriangleMesh.this.
              getPoint(
                index2);
            state.
              getPoint().
              x =
              w *
                v0p.
                  x +
                u *
                v1p.
                  x +
                v *
                v2p.
                  x;
            state.
              getPoint().
              y =
              w *
                v0p.
                  y +
                u *
                v1p.
                  y +
                v *
                v2p.
                  y;
            state.
              getPoint().
              z =
              w *
                v0p.
                  z +
                u *
                v1p.
                  z +
                v *
                v2p.
                  z;
            state.
              getPoint().
              set(
                parent.
                  transformObjectToWorld(
                    state.
                      getPoint()));
            Vector3 ng =
              Point3.
              normal(
                v0p,
                v1p,
                v2p);
            if (parent !=
                  null)
                ng =
                  parent.
                    transformNormalObjectToWorld(
                      ng);
            ng.
              normalize();
            state.
              getGeoNormal().
              set(
                ng);
            switch (normals.
                      interp) {
                case NONE:
                case FACE:
                    {
                        state.
                          getNormal().
                          set(
                            ng);
                        break;
                    }
                case VERTEX:
                    {
                        int i30 =
                          3 *
                          index0;
                        int i31 =
                          3 *
                          index1;
                        int i32 =
                          3 *
                          index2;
                        float[] normals =
                          TriangleMesh.this.
                            normals.
                            data;
                        state.
                          getNormal().
                          x =
                          w *
                            normals[i30 +
                                      0] +
                            u *
                            normals[i31 +
                                      0] +
                            v *
                            normals[i32 +
                                      0];
                        state.
                          getNormal().
                          y =
                          w *
                            normals[i30 +
                                      1] +
                            u *
                            normals[i31 +
                                      1] +
                            v *
                            normals[i32 +
                                      1];
                        state.
                          getNormal().
                          z =
                          w *
                            normals[i30 +
                                      2] +
                            u *
                            normals[i31 +
                                      2] +
                            v *
                            normals[i32 +
                                      2];
                        if (parent !=
                              null)
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
                        break;
                    }
                case FACEVARYING:
                    {
                        int idx =
                          3 *
                          tri;
                        float[] normals =
                          TriangleMesh.this.
                            normals.
                            data;
                        state.
                          getNormal().
                          x =
                          w *
                            normals[idx +
                                      0] +
                            u *
                            normals[idx +
                                      3] +
                            v *
                            normals[idx +
                                      6];
                        state.
                          getNormal().
                          y =
                          w *
                            normals[idx +
                                      1] +
                            u *
                            normals[idx +
                                      4] +
                            v *
                            normals[idx +
                                      7];
                        state.
                          getNormal().
                          z =
                          w *
                            normals[idx +
                                      2] +
                            u *
                            normals[idx +
                                      5] +
                            v *
                            normals[idx +
                                      8];
                        if (parent !=
                              null)
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
                        break;
                    }
            }
            float uv00 =
              0;
            float uv01 =
              0;
            float uv10 =
              0;
            float uv11 =
              0;
            float uv20 =
              0;
            float uv21 =
              0;
            switch (uvs.
                      interp) {
                case NONE:
                case FACE:
                    {
                        state.
                          getUV().
                          x =
                          0;
                        state.
                          getUV().
                          y =
                          0;
                        break;
                    }
                case VERTEX:
                    {
                        int i20 =
                          2 *
                          index0;
                        int i21 =
                          2 *
                          index1;
                        int i22 =
                          2 *
                          index2;
                        float[] uvs =
                          TriangleMesh.this.
                            uvs.
                            data;
                        uv00 =
                          uvs[i20 +
                                0];
                        uv01 =
                          uvs[i20 +
                                1];
                        uv10 =
                          uvs[i21 +
                                0];
                        uv11 =
                          uvs[i21 +
                                1];
                        uv20 =
                          uvs[i22 +
                                0];
                        uv21 =
                          uvs[i22 +
                                1];
                        break;
                    }
                case FACEVARYING:
                    {
                        int idx =
                          tri <<
                          1;
                        float[] uvs =
                          TriangleMesh.this.
                            uvs.
                            data;
                        uv00 =
                          uvs[idx +
                                0];
                        uv01 =
                          uvs[idx +
                                1];
                        uv10 =
                          uvs[idx +
                                2];
                        uv11 =
                          uvs[idx +
                                3];
                        uv20 =
                          uvs[idx +
                                4];
                        uv21 =
                          uvs[idx +
                                5];
                        break;
                    }
            }
            if (uvs.
                  interp !=
                  InterpolationType.
                    NONE) {
                state.
                  getUV().
                  x =
                  w *
                    uv00 +
                    u *
                    uv10 +
                    v *
                    uv20;
                state.
                  getUV().
                  y =
                  w *
                    uv01 +
                    u *
                    uv11 +
                    v *
                    uv21;
                float du1 =
                  uv00 -
                  uv20;
                float du2 =
                  uv10 -
                  uv20;
                float dv1 =
                  uv01 -
                  uv21;
                float dv2 =
                  uv11 -
                  uv21;
                Vector3 dp1 =
                  Point3.
                  sub(
                    v0p,
                    v2p,
                    new Vector3(
                      ));
                Vector3 dp2 =
                  Point3.
                  sub(
                    v1p,
                    v2p,
                    new Vector3(
                      ));
                float determinant =
                  du1 *
                  dv2 -
                  dv1 *
                  du2;
                if (determinant ==
                      0.0F) {
                    state.
                      setBasis(
                        OrthoNormalBasis.
                          makeFromW(
                            state.
                              getNormal()));
                }
                else {
                    float invdet =
                      1.0F /
                      determinant;
                    Vector3 dpdv =
                      new Vector3(
                      );
                    dpdv.
                      x =
                      (-du2 *
                         dp1.
                           x +
                         du1 *
                         dp2.
                           x) *
                        invdet;
                    dpdv.
                      y =
                      (-du2 *
                         dp1.
                           y +
                         du1 *
                         dp2.
                           y) *
                        invdet;
                    dpdv.
                      z =
                      (-du2 *
                         dp1.
                           z +
                         du1 *
                         dp2.
                           z) *
                        invdet;
                    if (parent !=
                          null)
                        dpdv =
                          parent.
                            transformVectorObjectToWorld(
                              dpdv);
                    state.
                      setBasis(
                        OrthoNormalBasis.
                          makeFromWV(
                            state.
                              getNormal(),
                            dpdv));
                }
            }
            else
                state.
                  setBasis(
                    OrthoNormalBasis.
                      makeFromW(
                        state.
                          getNormal()));
            int shaderIndex =
              faceShaders ==
              null
              ? 0
              : faceShaders[primID] &
              255;
            state.
              setShader(
                parent.
                  getShader(
                    shaderIndex));
        }
        public boolean update(ParameterList pl,
                              SunflowAPI api) {
            return true;
        }
        public BakingSurface() { super();
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1169362888000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAKVYe2wcxRkf3/kRP+jZTuKEkMSJ4wB5cEsqgkqMlDjGIZec" +
           "k+s5cYITMOPdufMm\nezvL7qx9NikFtcUpiNKogKhEaYoiBVIolSBNIyUlCG" +
           "hp8w9UtJWQoK0itZVaiqpKNFX7R7+Z2b3d\n27MPN7bkud2Z7/X7XjOzL3+C" +
           "6hwbLVedJJu0iJPsG8xg2yFan4EdZx9Mjajv1DVmTu82aQzVpFFM\n1xhKpF" +
           "VH0TDDiq4pqbt6ijbaYFFjMm9QliRFljxibPbk7UpvrhB44Plz7Y+cqu2Mob" +
           "o0SmDTpAwz\nnZr9Bik4DLWmj+BxrLhMN5S07rCeNLqOmG6hj5oOwyZzHkAP" +
           "oXga1Vsql8nQ6rSvXAHlioVtXFCE\neiUj1IKEhTZhWDeJ1ltSB5wbyznBbI" +
           "8vW0kNQhbwxSGAIywA1KtKqCXaCqhW/MWh24+dfCmOEsMo\noZuDXJgKSBjo" +
           "G0YtBVIYJbbTq2lEG0ZtJiHaILF1bOhTQuswanf0vImZaxMnSxxqjHPCdse1" +
           "iC10\n+pNp1KJyTLarMmqXfJTTiaH5b3U5A+cBdkcAW8LdwecBYJMOhtk5rB" +
           "KfpfaobkLEO6McJYzdu4EA\nWBsKhI3RkqpaE8MEapexNLCZVwaZrZt5IK2j" +
           "LmhhaNmsQrmvLawexXkywtDSKF1GLgFVo3AEZ2Fo\ncZRMSIIoLYtEKRSfDR" +
           "2fHX/xuZ9tE7ldqxHV4PY3AdPKCFOW5IhNTJVIxqtu8qnUPe7yGEJAvDhC\n" +
           "LGl6157bn/7LG52S5oYZaPaOHiEqG1H3nOjMPng3RXFuxgKLOjoPfhlyUQ4Z" +
           "b6WnaEHVdpQk8sWk\nv3gp+/N7Hj5D/hpDTSlUr1LDLUAetam0YOkGse8mJr" +
           "ExI1oKNRJT6xPrKdQAz2lIeTm7N5dzCEuh\nWkNM1VPxDi7KgQjuokZ41s0c" +
           "9Z8tzMbEc9FCCLXAP/oSQrHXkfiTvwxtSSqOa+YMOqE4tqpQO196\nV6lNFM" +
           "vWC4BhnCj7oAjMvEEGiDOW5DlkMTSkjNECUbCKTd2kSl6HqlXpLRoZ57/XLL" +
           "nI7W6fqKnh\njTBa0AbUwk5qaMQeUU9f+dWx/t3fPC6ThSe4h5ihzaAw6SlM" +
           "coXJksJkWGH3dgwFlR90RZGhmhqh\ndRE3QwYRQnAUihnaXsu6wXt33X+8Kw" +
           "7ZY03Ugv84aRcg9WzrV2lfUPEp0RxVSLulLxyaTl49vVWm\nnTJ7Y56Ru/m9" +
           "Vy6f/GfL+hiKzdw1OWbo201cTIa32lI37I7W2Uzy//7YwGu/vfzRzUHFMdRd" +
           "0Qgq\nOXkhd0WjY1OVaNAaA/Gnrk/ED6ChEzFUC90BOqKwH5rNyqiOsoLu8Z" +
           "sjx9KQRs05ahewwZf8jtbE\nxmw6EcyItGnlwyKZQTyQEQNFX736tfpbf3eh" +
           "+R2B2G/BidAmN0iYLOi2IA/22YTA/EfPZr7z9CfT\nh0QSeFnAUAMk1ziUcB" +
           "F4bgx4oJQNaCc8SN37zQLV9JyORw3Cs+m/ibWbzv7tW63S7QbM+FHb+PkC\n" +
           "gvnrt6OHL9/3r5VCTI3Kt5IAR0Am4SwMJPfaNp7kdhQf+fWK7/4Cfw86HXQX" +
           "R58iomEgAQ0JRyaF\nf9eJ8ZbI2q186ALZG2dJ6xk27hH12Jl8l/vAL88Lq5" +
           "tx+AQQjsMAtnpkVIXuhaD0DiSH8kbGVxdb\nfOzgIVgSLd+d2BkDYbdd2nO4" +
           "1bj0H1A7DGpV2FWdvTa0kmJZqD3quoYP33yr4/734yi2AzUZFGs7\nsCgA1A" +
           "iZB70DulDR2rpN2NE6sYCPwi9IWLvM81Kx7E28rBHjjV768Oebw1Q14nkJlE" +
           "hFE8v4TUyE\nDqCumG0rFceA6YP/aHkUv32v7Dzt5dtTPxzh/jz5Frnpzif+" +
           "OEMHrfeOQoFdDVxfWbsbEEeMoNgf\ne+mH59j7G7ZIfetn73RRxvVbTk51bn" +
           "n18Wtocp0RD0RFt43f8OX4mP5uTJyCZH+rOD2VM/WEfQFK\nwR7XNrlX+UyL" +
           "SMlVpZRM8MB+EVLxrJeSZ6MpKbtRZeTByZY7auhqsUqF7amyluHDToYW5gmT" +
           "G1kp\nQZw5JpEg28SHlLRi8+dmtHi5qxz/WsB93sN/fkb8fNhVBctwlbXDfD" +
           "gANxHAuccthEDaaGn4quQv\n8FhdebTr4rv7vz8tk3FdlftQmGtE/erv/3A0" +
           "/uSbo5IveuyMEJ9YeepPr13JLpIFJM/mayqOx2Ee\neT4XsBIWL+HV1TQI6r" +
           "c3rH75oezHwiLOt42huG6GQndwPqHj3fSiF7qLcw5dTEiM8ddRMQhSo0oQ\n" +
           "TT7oDLVBEEsYt1PXlHeU+yxpHexhsCdTHIJ3ZD7wbgcrP/XgfTpneDXlnXhp" +
           "uIgKcLRODmC4PRVv\nExImq6D+Ch/GGfoCoD5AbUOTiH3ByysEi3Wo5O20GH" +
           "hgYj4eyCIUj0kPyN85eSAuJMZ9QxdVtJEs\nnhSB9ym6KihS/PLqyBMIv2gT" +
           "oW26iree4MPX4Zqq+6xBrUeSpHac6lrgoW/Mx0M9AHTI89DQtebI\nigr8g2" +
           "OYRzKA/mwV6M/x4Wno5ZZNLGyTMDNfejLA+sx8sG4FjB94WD/4f8u9yqbCN2" +
           "UCQSttKkDW\nESYblL+9mZRQc6qKL87w4QewPbqWBvCjoW8YpdQg2Aw88sJc" +
           "PVJk6LqyK59v6k1zvDDyHafiI5P8\nMKKmP3zw8Gfp3/xb3HVKHy+a02hBzj" +
           "WM0IkifLqoh3jndAG7WR52LfHzE4aWzW4TQ41WWWWclVzn\nYZOMckGt8J8w" +
           "2QWGmkNk4FLvKUz0BmwzQMQfL1m+n1rFOZl/P0rKjyXl51t+zVhTttWKr3/+" +
           "2cyV\n3/9G1IOvHFpVfHzft8WBr0418NQUF9OURg3yplc6362eVZov6z3041" +
           "eHLvzoDn97LLsDVpTDJrla\nJV/gTDnzFay/YDFxaZr66ZLX7zz9/McxcQv8" +
           "H2RtXKu0FQAA");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169362888000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAMVaC3AV1Rk+996EPCUhQAgBEoEoAuFeCElIiDMYEqKBC8SE" +
       "hwY1bvaemyzs3V13\n94abyFBfAyhjK6217UxF7DjDo1rp2A52xqKO2lqdzq" +
       "hTdWpHW4eO7bS1xXa0dPqY/v85+7p7H0BS\nppm5Zx/nnP/8z+//z9k8/Skp" +
       "NHQyXzTC5rhGjXDXQJ+gGzTWJQuGsQ1eDYmvFZb0Hd+kqEESiJKg\nFDNJRV" +
       "Q0IjHBFCJSLNLb3ZHSyXJNlcdHZNUM05QZ3i23WPQ2RlsyCO48eqbq3qcK6o" +
       "OkMEoqBEVR\nTcGUVGWDTBOGSSqju4UxIZI0JTkSlQyzI0quokoy0aUqhiko" +
       "pnEX2U9CUTJNE5GmSRZG7cUjsHhE\nE3QhEWHLR/rYskBhpk5NQVJorNNZDm" +
       "Y2ps8Etq15/ZmjgUgxdu4AcRgHIPXVjtRc2gxRtdCJHa37\njp0MkYpBUiEp" +
       "A0hMBElMWG+QlCdoYpjqRmcsRmODZIZCaWyA6pIgSxNs1UFSZUgjimAmdWr0" +
       "U0OV\nx3BglZHUqM7WtF9GSbmIMulJ0VR1R0dxicox+6kwLgsjIHa1KzYXtw" +
       "ffg4ClEjCmxwWR2lMK9kgK\nWLzeP8ORsWETDICpRQlqjqrOUgWKAC9IFbel" +
       "LCgjkQFTl5QRGFqoJmEVk9TmJIq61gRxjzBCh0xS\n4x/Xx7tgVAlTBE4xyW" +
       "z/MEYJrFTrs5LHPsurvzh04ttnb2C+XRCjooz8l8KkOt+kfhqnOlVEyide\n" +
       "SIYf7b01OT9ICAye7RvMx3Rec2Z79Pcv1vMx87KM2Tq8m4rmkLjlSH3/3Teq" +
       "JIRsFGuqIaHx0yRn\n4dBn9XSkNIjaaocidobtzpf6f3LrPafoH4OktJdME1" +
       "U5mQA/miGqCU2SqX4jVagumDTWS0qoEuti\n/b2kCO6j4PL87dZ43KBmLymQ" +
       "2atpKnsGFcWBBKqoBO4lJa7a95pgjrL7lEYIKYIfaYJfDeF/7GqS\nteGIkV" +
       "Tisro3YuhiRNVHnGdR1WlE06UEyDBGI9sgCJQRmW6mxmgYfUgzyY7IqJqgEU" +
       "EUFElRIyMS\nRK2orojRMbxOmnIK+a7aGwggEPoDWoZYuEmVY1QfEo+fe2Pf" +
       "hk0PHuLOgg5uSWySJbBg2FowjAuG\nnQXD3gVJIMDWmYULc7OB0vdA+ALQlS" +
       "8duH3jnYcWhcBftL0FoDEcughks7jZIKpdboz3MjgUwdFq\nvrPrYPjC8XXc" +
       "0SK5oTjr7LK3nnnz2N/KlwVJMDtOopSA1KVIpg/B1cG/Bn9kZaP/54c2P/fe" +
       "mx9e\n58aYSRoyQj9zJobuIr89dFWkMQBDl/xTcytCO8mOI0FSAHgAGMj4B3" +
       "ip86+RFsIdNhyiLEVRUhZX\n9YQgY5eNYaXmqK7udd8wR6lk9zPBOGXo03Pg" +
       "t9RycnbF3tkattXcsdDaPikY3F64f9rK918oe42p\nxUbmCk/uG6Amj/MZrr" +
       "Ns0ymF9x9+s+9rX//04C7mKZarmJAQk8OyJKZgyrXuFAhwGUAGDdmwXUmo\n" +
       "MSkuCcMyRY/7V8U1q374py9XctPI8Ma2bOPFCbjv564n97x5x9/rGJmAiAnG" +
       "FcMdxqWZ6VLu1HVh\nHPlI3fvOgm/9VHgc8A8wx5AmKIMRwiQjTI8RpvdlrA" +
       "37+lZhswhoN+Zw/SzpfEjcd2pkUfKun/2I\ncV0meOsCrxk2C1oHtzw2i1G7" +
       "c/zRe5NgjMK45pe23FYpv/RPoDgIFEVIo8ZWHbAjlWZEa3Rh0Qcv\nv1J959" +
       "shEuwhpbIqxHoE5v+kBBwPwAJgJ6Wtu4H5VuXeYmyZyIQpodZSQMrzVATMLc" +
       "0d/j1YDLiR\nMzTceCL6xtbHmQJyBn6WXOijM3F2+9ELPzc/YnTcCMTZC1OZ" +
       "mAoFlDu37b2xGdNOP5EIkqJBUila\nJd4OQU6inw9CRWLYdR+UgWn96dUFT6" +
       "UdDsLM90e/Z1l/7LtYDvc4Gu/LfeE+085pc61wn+sP9wBh\nN+vYlAbWLuHB" +
       "GTRJESSEMUi7EKYGKwJTJpluAOLIdn4wwHg13kLcziCIQOcOLPrx69ufOMgh" +
       "Po+N\n02YNiV/69W/2hL7y8jCf5zekb/CRuqc+ee5c/ywOB7zyW5xRfHnn8O" +
       "qPaapCw9BYmG8FNvrV5Quf\n3t//kcVRVXoNswHq/N+Nv0KXXP/wx1nSbNGw" +
       "qspUUDiwYtuEzQ08Blpyxspax4rT8e0C+NVaVqzN\nYcWbM60IEFui6aoJfk" +
       "ZjKcRbFeplNNssj9kYqKG0Jx/pntnfvut+lldLoKQXjC2un8FGCu8CoINr\n" +
       "clvTITYkLrn9zPmXz9IlDFyKJQP8uVMfyVLeeuZ8Jpyim9+PH2W5sWBYMLhn" +
       "+/cFmWV/WjXPzDtd\n40rusa6bTIwhVTAdRA5YJQtTuGbrcShrNBTGJUVgtf" +
       "ZSVKRMlRFz1E8/BGzh7aD1ChcJcgrseY5p\nJRMEAKjqVYViXrL7eI0lqWFn" +
       "RwWdqQx2dbIgrcLazCR3Eeqhk989Y769fC3312W5beWfuGztsYn6\ntc8enk" +
       "RdVe8zqZ/0jLF5N4dGpdeDbKvFAS9ji5Y+qSMd5kqBn6SubEsDu6s1dhnE5t" +
       "o8+dbI05fE\n5i6wsIj24OYDHddnryc2JDSTVQATz8/5wfXHj36EWtZSsFUo" +
       "YgHT1gpzqyC68LQhLMWs1NP9Ts/w\nqbhyKsYUUMqcuRPHW9KVsDeeaAupmo" +
       "HbKc+5hUWpYatmYKV5lWeR3u59pzeWFz95+ACjb4VqiWdr\nZj0XjQn6Fi9A" +
       "lTGuV7WtamttM8mdV2bDsrZl5erG5rYVLc2w4LabegfCHIiQi3EHlvbDBjFT" +
       "cSiu\nhXykinn0dDeEEH29nSBQQf+Gzm4f3PZfJtzWwW+eRXZeDrg9hM0AAK" +
       "zpZEN8s5N7ZOwiIPNwFpDB\n+2GOMNiKjA42hzPhBB/Z9nV3Jj7gc4Kzcfhi" +
       "gfFonr7HsPkqNmOcC2xTHldfw6ZNuE7U1Nq+6so5\nUVtrS2NTy4rV4ETTmR" +
       "M5mkc2vuE1Bb54wOcED166E5Tj2wb4zbecYH4OJ3giS87F+y7IEcXIDVTU\n" +
       "MveLdOI1+Yjb+aD5EvfoDTsFOWa/0DQNLMSzd/vK9ia/jVa1NbdcORs1tzc1" +
       "rmpd0QQ2usqxESoB\nuTjhUUo2Cx27TAsttioju0LKZqHT2DwJLquw3bJh63" +
       "ZFhm5ZQqNQViC6N/RgpeC88jH6/ctktN7C\nFBtbsjF6xmI0lBz73zD5/CSY" +
       "rLeYrM/B5FmLyTIsvQZGBdgpOrDnL4YKhsdNyxstvGjLwIv2tivo\ni+1rWh" +
       "tb1qzAvFbJfNHDNDLyWroc2TzyxTw6TLnYWutBXjBbXabZbDbZ2QFWcLlOeN" +
       "mO4+Atn5Uf\nEF69PWghcRSxTdVWyHTMghO+4nSO9Y4dq5AzZHWhZceFfju6" +
       "8mXmnqXYdKd8GSHgotpGNv2XeVLG\nr7D5hUlmGNQcSN8q+r1jTJVirqLfvZ" +
       "iz2ocHPonZkRYeY620JF6ZVeJsaTCQbrRKt6rg5/5s5id5\nhP0DNucA0/bq" +
       "kokn4/j8oSvTb6ciUwf81lkyrbtkmXybjSyO6MUPe1i1d9gAv3b29bJl/ppH" +
       "ARew\n+QvUb0kNijZWU250xT8/FfFb4DdmiT92ueLjIyucYjg0QHKLEGBj/w" +
       "0OO0JNJ0jXq0mFzd/lSvOf\nqUiDhzD3WdLcN1kHrfFaKSGYo+HNAjhqqpkJ" +
       "UppHyOnYFEHVBELuVHU5xgR0ssz8DMKsH0JgvZpy\nNBAonooGtsHvMUsDj1" +
       "2yBkKMYsjZH2e4c78wzuxsj1iUMaIXDwoMvnvDb5mUaaQmj7bqsJkN+pbs\n" +
       "qY5j9KvDSX7i7IZ5oHqSemFgvQsM3MrVwq+XCtZso9DlB+srprDr8igsjM1i" +
       "r8Js1N9EFUPmhYlH\nYQ1TcaQtoKg7LIXd8f93pNY8erkem9Umqcp0JJ9Gmq" +
       "eiEcjxgd2WRnZfska8jHbn6evBphPSI2DH\nlmTCkcBw1cVEWD8VEdYA64ol" +
       "gjJZfFyQYTIs7ADFXGttySNoPzZQmMzUdKoJOvVO9pkrOhVZq4Hl\njy1ZP5" +
       "6UuQbz9N2GzU4TP3VLfqC6ZZJssyMR2A0Hzltsn8/J9kDu4pHn4lge3uPYCF" +
       "BPYS52zofA\nsnMyEhTrXu2KNjzF7Bz43BLt88uFFCZaGnTk4pbJqOWRH+0V" +
       "SHjk99lPmYqQsFjwJBeSXy/b7fbl\n6duPzTgED7C+XtgDceMCRZ5i1Lsrcu" +
       "WcuFQ5UyYp92778Ni2JuMfnPg/5YjRD+6+7Yvou//gXxbs\nf5wpi5LieFKW" +
       "vd/TPPfTAAziEtNPGf+6xs7VAgdNUpv7cIZ9dfHgZOAAn/UQwKh/FoQqXrzD" +
       "HoZ9\nqWcYbJ+tO++gR0wSgkF4e0SzVezZw/Cvi6k0faF+Fqd9DmD/eWYf2S" +
       "f5/54Nibc8s+vq1OFtj7Dv\nAIWiLExMIJnSKCni/3PAqOKx/8Kc1Gxab5HT" +
       "z+544Xvt9laWfZOe5Slt0py2iffmtj92pLT/Aiun\ny5EFKAAA");
}
