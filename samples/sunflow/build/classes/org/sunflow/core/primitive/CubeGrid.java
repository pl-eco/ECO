package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Instance;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public abstract class CubeGrid implements PrimitiveList {
    private int nx;
    private int ny;
    private int nz;
    private float voxelwx;
    private float voxelwy;
    private float voxelwz;
    private float invVoxelwx;
    private float invVoxelwy;
    private float invVoxelwz;
    private BoundingBox bounds;
    public CubeGrid() { super();
                        nx = (ny = (nz = 1));
                        bounds = new BoundingBox(1); }
    public boolean update(ParameterList pl, SunflowAPI api) { nx = pl.getInt(
                                                                        "resolutionX",
                                                                        nx);
                                                              ny =
                                                                pl.
                                                                  getInt(
                                                                    "resolutionY",
                                                                    ny);
                                                              nz =
                                                                pl.
                                                                  getInt(
                                                                    "resolutionZ",
                                                                    nz);
                                                              voxelwx =
                                                                2.0F /
                                                                  nx;
                                                              voxelwy =
                                                                2.0F /
                                                                  ny;
                                                              voxelwz =
                                                                2.0F /
                                                                  nz;
                                                              invVoxelwx =
                                                                1 /
                                                                  voxelwx;
                                                              invVoxelwy =
                                                                1 /
                                                                  voxelwy;
                                                              invVoxelwz =
                                                                1 /
                                                                  voxelwz;
                                                              return true;
    }
    protected abstract boolean inside(int x, int y,
                                      int z);
    public BoundingBox getBounds() { return bounds;
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
        Vector3 normal;
        switch (state.
                  getPrimitiveID(
                    )) {
            case 0:
                normal =
                  new Vector3(
                    -1,
                    0,
                    0);
                break;
            case 1:
                normal =
                  new Vector3(
                    1,
                    0,
                    0);
                break;
            case 2:
                normal =
                  new Vector3(
                    0,
                    -1,
                    0);
                break;
            case 3:
                normal =
                  new Vector3(
                    0,
                    1,
                    0);
                break;
            case 4:
                normal =
                  new Vector3(
                    0,
                    0,
                    -1);
                break;
            case 5:
                normal =
                  new Vector3(
                    0,
                    0,
                    1);
                break;
            default:
                normal =
                  new Vector3(
                    0,
                    0,
                    0);
                break;
        }
        state.
          getNormal(
            ).
          set(
            parent.
              transformNormalObjectToWorld(
                normal));
        state.
          getGeoNormal(
            ).
          set(
            state.
              getNormal(
                ));
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                state.
                  getNormal(
                    )));
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
    }
    public void intersectPrimitive(Ray r, int primID,
                                   IntersectionState state) {
        float intervalMin =
          r.
          getMin(
            );
        float intervalMax =
          r.
          getMax(
            );
        float orgX =
          r.
            ox;
        float orgY =
          r.
            oy;
        float orgZ =
          r.
            oz;
        float dirX =
          r.
            dx;
        float invDirX =
          1 /
          dirX;
        float dirY =
          r.
            dy;
        float invDirY =
          1 /
          dirY;
        float dirZ =
          r.
            dz;
        float invDirZ =
          1 /
          dirZ;
        float t1;
        float t2;
        t1 =
          (-1 -
             orgX) *
            invDirX;
        t2 =
          (+1 -
             orgX) *
            invDirX;
        int curr =
          -1;
        if (invDirX >
              0) {
            if (t1 >
                  intervalMin) {
                intervalMin =
                  t1;
                curr =
                  0;
            }
            if (t2 <
                  intervalMax)
                intervalMax =
                  t2;
            if (intervalMin >
                  intervalMax)
                return;
        }
        else {
            if (t2 >
                  intervalMin) {
                intervalMin =
                  t2;
                curr =
                  1;
            }
            if (t1 <
                  intervalMax)
                intervalMax =
                  t1;
            if (intervalMin >
                  intervalMax)
                return;
        }
        t1 =
          (-1 -
             orgY) *
            invDirY;
        t2 =
          (+1 -
             orgY) *
            invDirY;
        if (invDirY >
              0) {
            if (t1 >
                  intervalMin) {
                intervalMin =
                  t1;
                curr =
                  2;
            }
            if (t2 <
                  intervalMax)
                intervalMax =
                  t2;
            if (intervalMin >
                  intervalMax)
                return;
        }
        else {
            if (t2 >
                  intervalMin) {
                intervalMin =
                  t2;
                curr =
                  3;
            }
            if (t1 <
                  intervalMax)
                intervalMax =
                  t1;
            if (intervalMin >
                  intervalMax)
                return;
        }
        t1 =
          (-1 -
             orgZ) *
            invDirZ;
        t2 =
          (+1 -
             orgZ) *
            invDirZ;
        if (invDirZ >
              0) {
            if (t1 >
                  intervalMin) {
                intervalMin =
                  t1;
                curr =
                  4;
            }
            if (t2 <
                  intervalMax)
                intervalMax =
                  t2;
            if (intervalMin >
                  intervalMax)
                return;
        }
        else {
            if (t2 >
                  intervalMin) {
                intervalMin =
                  t2;
                curr =
                  5;
            }
            if (t1 <
                  intervalMax)
                intervalMax =
                  t1;
            if (intervalMin >
                  intervalMax)
                return;
        }
        orgX +=
          intervalMin *
            dirX;
        orgY +=
          intervalMin *
            dirY;
        orgZ +=
          intervalMin *
            dirZ;
        int indxX;
        int indxY;
        int indxZ;
        int stepX;
        int stepY;
        int stepZ;
        int stopX;
        int stopY;
        int stopZ;
        float deltaX;
        float deltaY;
        float deltaZ;
        float tnextX;
        float tnextY;
        float tnextZ;
        indxX =
          (int)
            ((orgX +
                1) *
               invVoxelwx);
        if (indxX <
              0)
            indxX =
              0;
        else
            if (indxX >=
                  nx)
                indxX =
                  nx -
                    1;
        if (Math.
              abs(
                dirX) <
              1.0E-6F) {
            stepX =
              0;
            stopX =
              indxX;
            deltaX =
              0;
            tnextX =
              Float.
                POSITIVE_INFINITY;
        }
        else
            if (dirX >
                  0) {
                stepX =
                  1;
                stopX =
                  nx;
                deltaX =
                  voxelwx *
                    invDirX;
                tnextX =
                  intervalMin +
                    ((indxX +
                        1) *
                       voxelwx -
                       1 -
                       orgX) *
                    invDirX;
            }
            else {
                stepX =
                  -1;
                stopX =
                  -1;
                deltaX =
                  -voxelwx *
                    invDirX;
                tnextX =
                  intervalMin +
                    (indxX *
                       voxelwx -
                       1 -
                       orgX) *
                    invDirX;
            }
        indxY =
          (int)
            ((orgY +
                1) *
               invVoxelwy);
        if (indxY <
              0)
            indxY =
              0;
        else
            if (indxY >=
                  ny)
                indxY =
                  ny -
                    1;
        if (Math.
              abs(
                dirY) <
              1.0E-6F) {
            stepY =
              0;
            stopY =
              indxY;
            deltaY =
              0;
            tnextY =
              Float.
                POSITIVE_INFINITY;
        }
        else
            if (dirY >
                  0) {
                stepY =
                  1;
                stopY =
                  ny;
                deltaY =
                  voxelwy *
                    invDirY;
                tnextY =
                  intervalMin +
                    ((indxY +
                        1) *
                       voxelwy -
                       1 -
                       orgY) *
                    invDirY;
            }
            else {
                stepY =
                  -1;
                stopY =
                  -1;
                deltaY =
                  -voxelwy *
                    invDirY;
                tnextY =
                  intervalMin +
                    (indxY *
                       voxelwy -
                       1 -
                       orgY) *
                    invDirY;
            }
        indxZ =
          (int)
            ((orgZ +
                1) *
               invVoxelwz);
        if (indxZ <
              0)
            indxZ =
              0;
        else
            if (indxZ >=
                  nz)
                indxZ =
                  nz -
                    1;
        if (Math.
              abs(
                dirZ) <
              1.0E-6F) {
            stepZ =
              0;
            stopZ =
              indxZ;
            deltaZ =
              0;
            tnextZ =
              Float.
                POSITIVE_INFINITY;
        }
        else
            if (dirZ >
                  0) {
                stepZ =
                  1;
                stopZ =
                  nz;
                deltaZ =
                  voxelwz *
                    invDirZ;
                tnextZ =
                  intervalMin +
                    ((indxZ +
                        1) *
                       voxelwz -
                       1 -
                       orgZ) *
                    invDirZ;
            }
            else {
                stepZ =
                  -1;
                stopZ =
                  -1;
                deltaZ =
                  -voxelwz *
                    invDirZ;
                tnextZ =
                  intervalMin +
                    (indxZ *
                       voxelwz -
                       1 -
                       orgZ) *
                    invDirZ;
            }
        boolean isInside =
          inside(
            indxX,
            indxY,
            indxZ) &&
          bounds.
          contains(
            r.
              ox,
            r.
              oy,
            r.
              oz);
        for (;
             ;
             ) {
            if (inside(
                  indxX,
                  indxY,
                  indxZ) !=
                  isInside) {
                r.
                  setMax(
                    intervalMin);
                if (isInside)
                    curr ^=
                      1;
                state.
                  setIntersection(
                    curr,
                    0,
                    0);
                return;
            }
            if (tnextX <
                  tnextY &&
                  tnextX <
                  tnextZ) {
                curr =
                  dirX >
                    0
                    ? 0
                    : 1;
                intervalMin =
                  tnextX;
                if (intervalMin >
                      intervalMax)
                    return;
                indxX +=
                  stepX;
                if (indxX ==
                      stopX)
                    return;
                tnextX +=
                  deltaX;
            }
            else
                if (tnextY <
                      tnextZ) {
                    curr =
                      dirY >
                        0
                        ? 2
                        : 3;
                    intervalMin =
                      tnextY;
                    if (intervalMin >
                          intervalMax)
                        return;
                    indxY +=
                      stepY;
                    if (indxY ==
                          stopY)
                        return;
                    tnextY +=
                      deltaY;
                }
                else {
                    curr =
                      dirZ >
                        0
                        ? 4
                        : 5;
                    intervalMin =
                      tnextZ;
                    if (intervalMin >
                          intervalMax)
                        return;
                    indxZ +=
                      stepZ;
                    if (indxZ ==
                          stopZ)
                        return;
                    tnextZ +=
                      deltaZ;
                }
        }
    }
    public int getNumPrimitives() { return 1; }
    public float getPrimitiveBound(int primID, int i) {
        return (i &
                  1) ==
          0
          ? -1
          : 1;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) {
        if (o2w ==
              null)
            return bounds;
        return o2w.
          transform(
            bounds);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "W+Il5D/G2X6r2BgAAA==");
}
