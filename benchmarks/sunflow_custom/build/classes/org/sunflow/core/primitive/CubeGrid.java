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
      1169362796000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "g9kftfzwwecW3hIvIf8B/67Y0u4YAAA=");
}
