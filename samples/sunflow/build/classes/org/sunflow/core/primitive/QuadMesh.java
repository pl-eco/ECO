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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/bThOP4/c35XeOEbAAA=");
}
