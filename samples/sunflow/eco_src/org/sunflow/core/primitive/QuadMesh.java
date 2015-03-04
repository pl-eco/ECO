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
                                                  file.close();
                                            }
                                            catch (IOException e) {
                                                e.
                                                  printStackTrace();
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
                          name().
                          toLowerCase());
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
        state.
          getRay().
          getPoint(
            state.
              getPoint());
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
        Point3 v3p =
          this.
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
          normalize();
        state.
          getGeoNormal().
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
                    int i33 =
                      3 *
                      index3;
                    float[] normals =
                      this.
                        normals.
                        data;
                    state.
                      getNormal().
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
                      getNormal().
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
                      getNormal().
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
                      quad;
                    float[] normals =
                      this.
                        normals.
                        data;
                    state.
                      getNormal().
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
                      getNormal().
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
                      getNormal().
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
              getUV().
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
              getUV().
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169362880000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVZe2wcxRmfu7PjJ/Ej4Lxj4jhJkzh32I7zsJHASRxicnaM" +
       "nRdOwIz35uxN9naX\n3TnnYlICAiWBqC0IkFqphLRKFQhQkGiVPoASAS2PVg" +
       "KkgkQFtKItlSilNCpNRf/o980+b++R4MBJ\nOze7M9833/M33+w+8QkpNQ0y" +
       "TzKj/IDOzOiGoQFqmCyxQaGmuQ0ejUgvl1YMnNqiamESipOwnOCk\nJi6ZsQ" +
       "TlNCYnYr0buzIGWaFryoExReNRluHRvUqHze/6eEcOw53Hz9TfebKkMUxK46" +
       "SGqqrGKZc1\ntUdhKZOT2vheOkFjaS4rsbhs8q44uYyp6dQGTTU5Vbl5K7md" +
       "ROJkmi4hT04Wxp3FY7B4TKcGTcXE\n8rEBsSxwmGEwTmWVJbrd5YCyJZsSxL" +
       "bpBnNnA5NyHNwB6ggJQOsrXa0tbXNU1SOP7lh98MRjEVIz\nTGpkdQiZSaAJ" +
       "h/WGSXWKpUaZYXYnEiwxTOpUxhJDzJCpIk+KVYdJvSmPqZSnDWYOMlNTJnBi" +
       "vZnW\nmSHWdB7GSbWEOhlpiWuGa6OkzJSEc1eaVOgYqN3gqW2puwmfg4KVMg" +
       "hmJKnEHJKSfbIKHm8MUrg6\nNm+BCUBalmJ8XHOXKlEpPCD1li8Vqo7Fhrgh" +
       "q2MwtVRLwyqczCnIFG2tU2kfHWMjnMwKzhuwhmBW\nhTAEknByRXCa4ARemh" +
       "Pwks8/Kxo+P/ro95+/VsR2SYJJCspfCUQLAkSDLMkMpkrMIjyfjj7Ye2N6\n" +
       "XpgQmHxFYLI1p3vxme3xv/2q0ZozN8+craN7mcRHpP77Gwdvu04jERSjXNdM" +
       "GZ2fpblIhwF7pCuj\nQ9Y2uBxxMOoMvjD46xvvOM0+DpPKXjJN0pR0CuKoTt" +
       "JSuqww4zqmMoNyluglFUxNbBDjvaQM+nEI\neevp1mTSZLyXlCji0TRN3IOJ" +
       "ksACTVQBfVlNak5fp3xc9DM6IaQMLhKFaxaxfuKfk9XRmJlWk4q2\nP2YaUk" +
       "wzxtx7STNYTDfkFOgwwWI3pGmij5njUYwfnZPB2LiWYjEqUVVWtdiYDBkraS" +
       "sTbAL/p8Q1\ng/LW7w+FEACDiaxADmzWlAQzRqRTH752sGfLPUetIMHAtjXl" +
       "ZBEsFrUXi+JiUXexqLMYCYXEGpfj\noparwND7IGUB3KqXDd10/S1HmyIQI/" +
       "r+ErASTm0CnWxJeiRtg5fXvQICJQiuWT/cfSR6/tQ1VnDF\nCsNvXuqqN558" +
       "/cS56uVhEs6PjaghoHMlshlAQHUxrzmYTfn4/+Pevmfefv29b3h5xUlzTrrn" +
       "UmK6\nNgV9YWgSSwAAeuxPzq6J7CQ77g+TEsAAwD0hP0DKguAaWWnb5UAg6l" +
       "IWJ1VJzUhRBYcc3Krk44a2\n33sigqRW9GeAc6owjrHTaAe2+MfRK3RsG6yg" +
       "Qm8HtBAQe/6uaVe982zVy8IsDhrX+Pa7Icat3K7z\ngmWbwRg8f++7Aw889M" +
       "mR3SJS7FDhsAmmRxVZygDJEo8EkloBYEFHNm9XU1pCTsp0VGEYcf+rWdz6\n" +
       "079/u9ZyjQJPHM+2XJiB93z2enLH6zf/Z4FgE5JwU/HU8KZZ2szwOHcbBj2A" +
       "cmTufGv+935DHwbM\nA5wx5UkmoIMIzYiwY0zYfbloo4GxVmyagHdLgdDPs4" +
       "WPSAdPjzWlb33150LqKuqvBfxu6KN6l+V5\nbBahdWcGs3czNcdh3qoX+vfU" +
       "Ki98ARyHgaMEW6e51QDcyGQ50Z5dWvbu2RcbbnkzQsKbSKWi0cQm\nKuKfVE" +
       "DgAVgA5GT0a64VsVW7vxxboTIRRphjGyDju8P6bVnh9N+EBYCXOSOjLY/GX9" +
       "v6sDBAwcTP\ns/8F+Ew+v/34+d/x9wUfLwORemEmF0+haPJo1749UTft6UdS" +
       "YVI2TGolu6zbQZU0xvkwVCGmU+tB\n6Zc1nl1RWNtnl4sw84LZ71s2mPsejk" +
       "MfZ2O/OpDu09Ha8+Gabaf77GC6h4joXCNImkW71E3OCt3Q\nOEjIEhnMVA2q" +
       "KxOhwVdti3RAuHnsvo0zBtftvksgcgUUgNTs9ySEsht7ITDt4sK+dpmNSEtv" +
       "OvPP\ns8+zpSIsy2UTLNFtjOUphnw0n9HTrO+d5HGBqiWj1LRsEqwic4vErN" +
       "pPmHC6jjkzy3+scPZFXOvD\nw03PvbL9kSPW5lUkerOoRqRDH/xxX+Q7Z0ct" +
       "umCIBibfv+DkX5/5cPByC+isOnZRTinpp7FqWaFA\njVBgYbEVxOyXVix84v" +
       "bB922J6rMrsh44tXx04EW29Opv/SlP8QDxqFHuolvI3v7xvlN3ImsoN7LC\n" +
       "SCqrVNSqyzC0FKaO8XExp1e3wKGfkwg4Crub7Ue4SNjiIO5nchuYMZmgKtZU" +
       "hhjvjFn1iqxF3RMJ\nDGZyxDXI/KxqpU/Egpft9z72+Bn+5opOy0LLC/s6SL" +
       "i888RkY+dTx6ZQozQG/BZkXTcx94bIuPxK\nWBxVLPDIOeJkE3VlQ0YlyJM2" +
       "1G1ZwHGlLv42Y7OkyN41VmRMxgZwrlRCf1juAxs35t+be1I6F7vp\n5M9m/u" +
       "TqU8ffRyvrGSi1qwSEtLauWtN+FdDXQyriiT0qJ2wo3/jWptHTSfV0QhihUq" +
       "R4N9LYGlaI\nJz4Mimi6iUcS39nf5tS8VTexcrvMt0jvxoNPX19d/oNjhwV/" +
       "G8AqfMcb+75sghr9/rSwJG9bs7a9\nbR0ne776wr+zfW1HS0fbyo61sNi2zb" +
       "1DUQuaUYKUC9S3wwEr12ioqr0XkHoR0dO9FMJ89w+CMiWD\nPd0brZoQ2zZs" +
       "rrWSqKPgNt+ZvQHNsS+nn28DOohNN4TNraCpKeh7rGjcdQGAuTMPwGB/h4Uu" +
       "2O4U\nfLA5lAsleLsHm5tzsQHvqSXGoQslxdEiY/dicxibvZYU2CqBMG8VpK" +
       "oXQGvWtXZ8PQHU1rGqpa11\nZdtqTipFAAmr4/LHHBfgTSbg+G9evOOr8eki" +
       "uObajp9bwPEP5K08ykDyCYovmUiZKg45pgPrK3OO\nrAI7GezpCCTNm3BTch" +
       "8FNHjwS2qAx6N5tgbzCmjwMDYPwXaVnvhqhDw+BSHn20LOLyDkSVvIKqx7\n" +
       "hsYpFPhulgX33ZLRA5zpup4dnm3B8Fzb3vp14duatS0d7StXr+KkVoSnT2gU" +
       "4vFsPfKF6o+K2DDj\npfIcX6KD2xbkus0RURz5sFgo9DJOlFNHdn1WfZi+dF" +
       "PYTvytUERzTV+psAmm+FassKAl+1y+DK4l\nth+XBP0o9MuHQaFsFWo9OLde" +
       "WArK54qA01lsfsFJ+X5D5vhKLyceJjQ54Zn2lxcKT+eUl0/HLrg6\nbB07Ll" +
       "rHQNWXx03+7HKmNfinDVn/3QO9YpnfFjHIW9i8ChtpWofdkwXNUTaqaQqjqm" +
       "eR1y7FImgF\nZluEfVmLuBvbLjH1vSJafYDNu5zUjTHuRvV6La2KwqbP0+YP" +
       "l6JNG1yGrY0x1Rie5XdcivLxaB+F\nWM6sEhw+KqLkx9j8mZPpoOROzVASQk" +
       "EXluflMBbjkCXrtYxngb9cigX64TpsW+DwRVsgIjhG3LNL\nToQP0gPCz86M" +
       "ppwZvXisNa3KGr/TMLHav4tY6wts/sVJveyQuoGBIy96Fjl3KRZZCNentkU+" +
       "vWiL\n+AQNRYqMlWIDW1UtuLw/nXI1MD1zoQqh0KWosAauc7YK56Ya1vNzXI" +
       "YbGASf663QZUUUrcOmEs68\nusF0ajA/cba7QlVT1FWU680g8iFLVes/r67d" +
       "+XV1sSg0u4gqc7FpgC0Hscg9u4CJZuYkqBhu91Sb\neSluBDlCd9uq3T2lSF" +
       "xcZGwpNk3gINBqPd0HvvGC8SIrDE/PRRerJ5TI5U75hCftWTnfdK3vkFL8\n" +
       "3dv2fB7//X+t12POt8IqONEm04rif53o60+DYEvKwjZV1stFcRwKXcXJnMLf" +
       "jsSrQ18ehmIWVTuk\naZAK6gv880+DY0mVbxpsuXbPPwlqzwhMwm6nnqf6sV" +
       "6uZrJshfZZlPUGR3xsd96ypK3P7SPSrid3\nX5k5tu0+8eqmVFLo5CSyqYTT" +
       "vvXJRXDFNzULC3JzeL1Bnn5qx7M/XueUhOKV/OW+ejUrYNus0cK+\nxwFF/z" +
       "/GKNgE+CAAAA==");
}
