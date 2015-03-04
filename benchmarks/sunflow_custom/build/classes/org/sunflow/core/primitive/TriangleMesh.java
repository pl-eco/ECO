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
              close(
                );
        }
        catch (IOException e) {
            e.
              printStackTrace(
                );
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
                          name(
                            ).
                          toLowerCase(
                            ));
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
            init(
              );
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
          getRobustStack(
            );
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
          getMin(
            );
        float maxt =
          r.
          getMax(
            );
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
    private final void intersectTriangleKensler(Ray r,
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
          init(
            );
        Instance parent =
          state.
          getInstance(
            );
        int primID =
          state.
          getPrimitiveID(
            );
        float u =
          state.
          getU(
            );
        float v =
          state.
          getV(
            );
        float w =
          1 -
          u -
          v;
        state.
          getRay(
            ).
          getPoint(
            state.
              getPoint(
                ));
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
          getPoint(
            index0);
        Point3 v1p =
          getPoint(
            index1);
        Point3 v2p =
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
          normalize(
            );
        state.
          getGeoNormal(
            ).
          set(
            ng);
        switch (normals.
                  interp) {
            case NONE:
            case FACE:
                {
                    state.
                      getNormal(
                        ).
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
                      getNormal(
                        ).
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
                      getNormal(
                        ).
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
                      getNormal(
                        ).
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
                      getNormal(
                        ).
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
                      getNormal(
                        ).
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
                      getNormal(
                        ).
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
                      getUV(
                        ).
                      x =
                      0;
                    state.
                      getUV(
                        ).
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
              getUV(
                ).
              x =
              w *
                uv00 +
                u *
                uv10 +
                v *
                uv20;
            state.
              getUV(
                ).
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
                          getNormal(
                            )));
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
                          getNormal(
                            ),
                        dpdv));
            }
        }
        else
            state.
              setBasis(
                OrthoNormalBasis.
                  makeFromW(
                    state.
                      getNormal(
                        )));
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
                         int nt = getNumPrimitives(
                                    );
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
    private static final class WaldTriangle {
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
                             int tri) { super(
                                          );
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
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "SfVnXhQAAA==");
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
                                          getNumPrimitives(
                                            );
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
              init(
                );
            Instance parent =
              state.
              getInstance(
                );
            int primID =
              state.
              getPrimitiveID(
                );
            float u =
              state.
              getU(
                );
            float v =
              state.
              getV(
                );
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
              getPoint(
                index0);
            Point3 v1p =
              getPoint(
                index1);
            Point3 v2p =
              getPoint(
                index2);
            state.
              getPoint(
                ).
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
              getPoint(
                ).
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
              getPoint(
                ).
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
              getPoint(
                ).
              set(
                parent.
                  transformObjectToWorld(
                    state.
                      getPoint(
                        )));
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
              normalize(
                );
            state.
              getGeoNormal(
                ).
              set(
                ng);
            switch (normals.
                      interp) {
                case NONE:
                case FACE:
                    {
                        state.
                          getNormal(
                            ).
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
                          getNormal(
                            ).
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
                          getNormal(
                            ).
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
                          getNormal(
                            ).
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
                          getNormal(
                            ).
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
                          getNormal(
                            ).
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
                          getNormal(
                            ).
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
                          getUV(
                            ).
                          x =
                          0;
                        state.
                          getUV(
                            ).
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
                  getUV(
                    ).
                  x =
                  w *
                    uv00 +
                    u *
                    uv10 +
                    v *
                    uv20;
                state.
                  getUV(
                    ).
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
                              getNormal(
                                )));
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
                              getNormal(
                                ),
                            dpdv));
                }
            }
            else
                state.
                  setBasis(
                    OrthoNormalBasis.
                      makeFromW(
                        state.
                          getNormal(
                            )));
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
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "PHtlrWdvMP4HlRg6IsQgAAA=");
}
