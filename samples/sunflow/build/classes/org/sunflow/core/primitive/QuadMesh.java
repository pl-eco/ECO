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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
