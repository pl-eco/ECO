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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
