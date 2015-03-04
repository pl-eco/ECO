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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "0SPP/2rjm/e+uPSB6Pn/C4dvMre3EwAA");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
