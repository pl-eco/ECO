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
public class QuadMesh implements PrimitiveList {
    protected float[] points;
    protected int[] quads;
    private FloatParameter normals;
    private FloatParameter uvs;
    private byte[] faceShaders;
    public QuadMesh() { super();
                        quads = null;
                        points = null;
                        normals = (uvs = new FloatParameter());
                        faceShaders = null; }
    public void writeObj(String filename) { try { FileWriter file = new FileWriter(
                                                    filename);
                                                  file.write(String.
                                                               format(
                                                                 "o object\n"));
                                                  for (int i = 0;
                                                       i <
                                                         points.
                                                           length;
                                                       i +=
                                                         3) file.
                                                              write(
                                                                String.
                                                                  format(
                                                                    "v %g %g %g\n",
                                                                    points[i],
                                                                    points[i +
                                                                             1],
                                                                    points[i +
                                                                             2]));
                                                  file.write(
                                                         "s off\n");
                                                  for (int i =
                                                         0;
                                                       i <
                                                         quads.
                                                           length;
                                                       i +=
                                                         4)
                                                      file.
                                                        write(
                                                          String.
                                                            format(
                                                              "f %d %d %d %d\n",
                                                              quads[i] +
                                                                1,
                                                              quads[i +
                                                                      1] +
                                                                1,
                                                              quads[i +
                                                                      2] +
                                                                1,
                                                              quads[i +
                                                                      3] +
                                                                1));
                                                  file.close(
                                                         );
                                            }
                                            catch (IOException e) {
                                                e.
                                                  printStackTrace(
                                                    );
                                            } }
    public boolean update(ParameterList pl, SunflowAPI api) {
        {
            int[] quads =
              pl.
              getIntArray(
                "quads");
            if (quads !=
                  null) {
                this.
                  quads =
                  quads;
            }
        }
        if (quads ==
              null) {
            UI.
              printError(
                Module.
                  GEOM,
                "Unable to update mesh - quad indices are missing");
            return false;
        }
        if (quads.
              length %
              4 !=
              0)
            UI.
              printWarning(
                Module.
                  GEOM,
                ("Quad index data is not a multiple of 4 - some quads may be m" +
                 "issing"));
        pl.
          setFaceCount(
            quads.
              length /
              4);
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
                }
        }
        if (points ==
              null) {
            UI.
              printError(
                Module.
                  GEOM,
                "Unabled to update mesh - vertices are missing");
            return false;
        }
        pl.
          setVertexCount(
            points.
              length /
              3);
        pl.
          setFaceVertexCount(
            4 *
              (quads.
                 length /
                 4));
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
              quads.
                length /
              4) {
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
                        "Shader index too large on quad %d",
                        i);
                this.
                  faceShaders[i] =
                  (byte)
                    (v &
                       255);
            }
        }
        return true;
    }
    public float getPrimitiveBound(int primID,
                                   int i) {
        int quad =
          4 *
          primID;
        int a =
          3 *
          quads[quad +
                  0];
        int b =
          3 *
          quads[quad +
                  1];
        int c =
          3 *
          quads[quad +
                  2];
        int d =
          3 *
          quads[quad +
                  3];
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
                         axis],
                points[d +
                         axis]);
        else
            return MathUtils.
              max(
                points[a +
                         axis],
                points[b +
                         axis],
                points[c +
                         axis],
                points[d +
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
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
        int quad =
          4 *
          primID;
        int p0 =
          3 *
          quads[quad +
                  0];
        int p1 =
          3 *
          quads[quad +
                  1];
        int p2 =
          3 *
          quads[quad +
                  2];
        int p3 =
          3 *
          quads[quad +
                  3];
        final float[] A =
          { points[p2 +
                     0] -
          points[p3 +
                   0] -
          points[p1 +
                   0] +
          points[p0 +
                   0],
        points[p2 +
                 1] -
          points[p3 +
                   1] -
          points[p1 +
                   1] +
          points[p0 +
                   1],
        points[p2 +
                 2] -
          points[p3 +
                   2] -
          points[p1 +
                   2] +
          points[p0 +
                   2] };
        final float[] B =
          { points[p1 +
                     0] -
          points[p0 +
                   0],
        points[p1 +
                 1] -
          points[p0 +
                   1],
        points[p1 +
                 2] -
          points[p0 +
                   2] };
        final float[] C =
          { points[p3 +
                     0] -
          points[p0 +
                   0],
        points[p3 +
                 1] -
          points[p0 +
                   1],
        points[p3 +
                 2] -
          points[p0 +
                   2] };
        final float[] R =
          { r.
              ox -
          points[p0 +
                   0],
        r.
          oy -
          points[p0 +
                   1],
        r.
          oz -
          points[p0 +
                   2] };
        final float[] Q =
          { r.
              dx,
        r.
          dy,
        r.
          dz };
        float absqx =
          Math.
          abs(
            r.
              dx);
        float absqy =
          Math.
          abs(
            r.
              dy);
        float absqz =
          Math.
          abs(
            r.
              dz);
        int X =
          0;
        int Y =
          1;
        int Z =
          2;
        if (absqx >
              absqy &&
              absqx >
              absqz) {
            
        }
        else
            if (absqy >
                  absqz) {
                X =
                  1;
                Y =
                  0;
            }
            else {
                X =
                  2;
                Z =
                  0;
            }
        float Cxz =
          C[X] *
          Q[Z] -
          C[Z] *
          Q[X];
        float Cyx =
          C[Y] *
          Q[X] -
          C[X] *
          Q[Y];
        float Czy =
          C[Z] *
          Q[Y] -
          C[Y] *
          Q[Z];
        float Rxz =
          R[X] *
          Q[Z] -
          R[Z] *
          Q[X];
        float Ryx =
          R[Y] *
          Q[X] -
          R[X] *
          Q[Y];
        float Rzy =
          R[Z] *
          Q[Y] -
          R[Y] *
          Q[Z];
        float Bxy =
          B[X] *
          Q[Y] -
          B[Y] *
          Q[X];
        float Byz =
          B[Y] *
          Q[Z] -
          B[Z] *
          Q[Y];
        float Bzx =
          B[Z] *
          Q[X] -
          B[X] *
          Q[Z];
        float a =
          A[X] *
          Byz +
          A[Y] *
          Bzx +
          A[Z] *
          Bxy;
        if (a ==
              0) {
            float b =
              B[X] *
              Czy +
              B[Y] *
              Cxz +
              B[Z] *
              Cyx;
            float c =
              C[X] *
              Rzy +
              C[Y] *
              Rxz +
              C[Z] *
              Ryx;
            float u =
              -c /
              b;
            if (u >=
                  0 &&
                  u <=
                  1) {
                float v =
                  (u *
                     Bxy +
                     Ryx) /
                  Cyx;
                if (v >=
                      0 &&
                      v <=
                      1) {
                    float t =
                      (B[X] *
                         u +
                         C[X] *
                         v -
                         R[X]) /
                      Q[X];
                    if (r.
                          isInside(
                            t)) {
                        r.
                          setMax(
                            t);
                        state.
                          setIntersection(
                            primID,
                            u,
                            v);
                    }
                }
            }
        }
        else {
            float b =
              A[X] *
              Rzy +
              A[Y] *
              Rxz +
              A[Z] *
              Ryx +
              B[X] *
              Czy +
              B[Y] *
              Cxz +
              B[Z] *
              Cyx;
            float c =
              C[X] *
              Rzy +
              C[Y] *
              Rxz +
              C[Z] *
              Ryx;
            float discrim =
              b *
              b -
              4 *
              a *
              c;
            if (c *
                  (a +
                     b +
                     c) >
                  0 &&
                  (discrim <
                     0 ||
                     a *
                     c <
                     0 ||
                     b /
                     a >
                     0 ||
                     b /
                     a <
                     -2))
                return;
            float q =
              b >
              0
              ? -0.5F *
              (b +
                 (float)
                   Math.
                   sqrt(
                     discrim))
              : -0.5F *
              (b -
                 (float)
                   Math.
                   sqrt(
                     discrim));
            float Axy =
              A[X] *
              Q[Y] -
              A[Y] *
              Q[X];
            float u =
              q /
              a;
            if (u >=
                  0 &&
                  u <=
                  1) {
                float d =
                  u *
                  Axy -
                  Cyx;
                float v =
                  -(u *
                      Bxy +
                      Ryx) /
                  d;
                if (v >=
                      0 &&
                      v <=
                      1) {
                    float t =
                      (A[X] *
                         u *
                         v +
                         B[X] *
                         u +
                         C[X] *
                         v -
                         R[X]) /
                      Q[X];
                    if (r.
                          isInside(
                            t)) {
                        r.
                          setMax(
                            t);
                        state.
                          setIntersection(
                            primID,
                            u,
                            v);
                    }
                }
            }
            u =
              c /
                q;
            if (u >=
                  0 &&
                  u <=
                  1) {
                float d =
                  u *
                  Axy -
                  Cyx;
                float v =
                  -(u *
                      Bxy +
                      Ryx) /
                  d;
                if (v >=
                      0 &&
                      v <=
                      1) {
                    float t =
                      (A[X] *
                         u *
                         v +
                         B[X] *
                         u +
                         C[X] *
                         v -
                         R[X]) /
                      Q[X];
                    if (r.
                          isInside(
                            t)) {
                        r.
                          setMax(
                            t);
                        state.
                          setIntersection(
                            primID,
                            u,
                            v);
                    }
                }
            }
        }
    }
    public int getNumPrimitives() { return quads.
                                             length /
                                      4; }
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
        state.
          getRay(
            ).
          getPoint(
            state.
              getPoint(
                ));
        int quad =
          4 *
          primID;
        int index0 =
          quads[quad +
                  0];
        int index1 =
          quads[quad +
                  1];
        int index2 =
          quads[quad +
                  2];
        int index3 =
          quads[quad +
                  3];
        Point3 v0p =
          getPoint(
            index0);
        Point3 v1p =
          getPoint(
            index1);
        Point3 v2p =
          getPoint(
            index2);
        Point3 v3p =
          getPoint(
            index2);
        float tanux =
          (1 -
             v) *
          (v1p.
             x -
             v0p.
               x) +
          v *
          (v2p.
             x -
             v3p.
               x);
        float tanuy =
          (1 -
             v) *
          (v1p.
             y -
             v0p.
               y) +
          v *
          (v2p.
             y -
             v3p.
               y);
        float tanuz =
          (1 -
             v) *
          (v1p.
             z -
             v0p.
               z) +
          v *
          (v2p.
             z -
             v3p.
               z);
        float tanvx =
          (1 -
             u) *
          (v3p.
             x -
             v0p.
               x) +
          u *
          (v2p.
             x -
             v1p.
               x);
        float tanvy =
          (1 -
             u) *
          (v3p.
             y -
             v0p.
               y) +
          u *
          (v2p.
             y -
             v1p.
               y);
        float tanvz =
          (1 -
             u) *
          (v3p.
             z -
             v0p.
               z) +
          u *
          (v2p.
             z -
             v1p.
               z);
        float nx =
          tanuy *
          tanvz -
          tanuz *
          tanvy;
        float ny =
          tanuz *
          tanvx -
          tanux *
          tanvz;
        float nz =
          tanux *
          tanvy -
          tanuy *
          tanvx;
        Vector3 ng =
          new Vector3(
          nx,
          ny,
          nz);
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
        float k00 =
          (1 -
             u) *
          (1 -
             v);
        float k10 =
          u *
          (1 -
             v);
        float k01 =
          (1 -
             u) *
          v;
        float k11 =
          u *
          v;
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
                    int i33 =
                      3 *
                      index3;
                    float[] normals =
                      this.
                        normals.
                        data;
                    state.
                      getNormal(
                        ).
                      x =
                      k00 *
                        normals[i30 +
                                  0] +
                        k10 *
                        normals[i31 +
                                  0] +
                        k11 *
                        normals[i32 +
                                  0] +
                        k01 *
                        normals[i33 +
                                  0];
                    state.
                      getNormal(
                        ).
                      y =
                      k00 *
                        normals[i30 +
                                  1] +
                        k10 *
                        normals[i31 +
                                  1] +
                        k11 *
                        normals[i32 +
                                  1] +
                        k01 *
                        normals[i33 +
                                  1];
                    state.
                      getNormal(
                        ).
                      z =
                      k00 *
                        normals[i30 +
                                  2] +
                        k10 *
                        normals[i31 +
                                  2] +
                        k11 *
                        normals[i32 +
                                  2] +
                        k01 *
                        normals[i33 +
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
                      quad;
                    float[] normals =
                      this.
                        normals.
                        data;
                    state.
                      getNormal(
                        ).
                      x =
                      k00 *
                        normals[idx +
                                  0] +
                        k10 *
                        normals[idx +
                                  3] +
                        k11 *
                        normals[idx +
                                  6] +
                        k01 *
                        normals[idx +
                                  9];
                    state.
                      getNormal(
                        ).
                      y =
                      k00 *
                        normals[idx +
                                  1] +
                        k10 *
                        normals[idx +
                                  4] +
                        k11 *
                        normals[idx +
                                  7] +
                        k01 *
                        normals[idx +
                                  10];
                    state.
                      getNormal(
                        ).
                      z =
                      k00 *
                        normals[idx +
                                  2] +
                        k10 *
                        normals[idx +
                                  5] +
                        k11 *
                        normals[idx +
                                  8] +
                        k01 *
                        normals[idx +
                                  11];
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
        float uv30 =
          0;
        float uv31 =
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
                    int i23 =
                      2 *
                      index3;
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
                    uv20 =
                      uvs[i23 +
                            0];
                    uv21 =
                      uvs[i23 +
                            1];
                    break;
                }
            case FACEVARYING:
                {
                    int idx =
                      quad <<
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
                    uv30 =
                      uvs[idx +
                            6];
                    uv31 =
                      uvs[idx +
                            7];
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
              k00 *
                uv00 +
                k10 *
                uv10 +
                k11 *
                uv20 +
                k01 *
                uv30;
            state.
              getUV(
                ).
              y =
              k00 *
                uv01 +
                k10 *
                uv11 +
                k11 *
                uv21 +
                k01 *
                uv31;
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
    protected Point3 getPoint(int i) { i *=
                                         3;
                                       return new Point3(
                                         points[i],
                                         points[i +
                                                  1],
                                         points[i +
                                                  2]);
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169362880000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "gxLRGwAA");
}
