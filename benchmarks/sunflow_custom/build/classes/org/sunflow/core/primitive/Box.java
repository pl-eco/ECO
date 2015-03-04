package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.core.ParameterList.FloatParameter;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class Box implements PrimitiveList {
    private float minX;
    private float minY;
    private float minZ;
    private float maxX;
    private float maxY;
    private float maxZ;
    public Box() { super();
                   minX = (minY = (minZ = -1));
                   maxX = (maxY = (maxZ = +1)); }
    public boolean update(ParameterList pl, SunflowAPI api) { FloatParameter pts =
                                                                pl.
                                                                getPointArray(
                                                                  "points");
                                                              if (pts !=
                                                                    null) {
                                                                  BoundingBox bounds =
                                                                    new BoundingBox(
                                                                    );
                                                                  for (int i =
                                                                         0;
                                                                       i <
                                                                         pts.
                                                                           data.
                                                                           length;
                                                                       i +=
                                                                         3)
                                                                      bounds.
                                                                        include(
                                                                          pts.
                                                                            data[i],
                                                                          pts.
                                                                            data[i +
                                                                                   1],
                                                                          pts.
                                                                            data[i +
                                                                                   2]);
                                                                  minX =
                                                                    bounds.
                                                                      getMinimum(
                                                                        ).
                                                                      x;
                                                                  minY =
                                                                    bounds.
                                                                      getMinimum(
                                                                        ).
                                                                      y;
                                                                  minZ =
                                                                    bounds.
                                                                      getMinimum(
                                                                        ).
                                                                      z;
                                                                  maxX =
                                                                    bounds.
                                                                      getMaximum(
                                                                        ).
                                                                      x;
                                                                  maxY =
                                                                    bounds.
                                                                      getMaximum(
                                                                        ).
                                                                      y;
                                                                  maxZ =
                                                                    bounds.
                                                                      getMaximum(
                                                                        ).
                                                                      z;
                                                              }
                                                              return true;
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
        int n =
          state.
          getPrimitiveID(
            );
        switch (n) {
            case 0:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      1,
                      0,
                      0));
                break;
            case 1:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      -1,
                      0,
                      0));
                break;
            case 2:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      0,
                      1,
                      0));
                break;
            case 3:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      0,
                      -1,
                      0));
                break;
            case 4:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      0,
                      0,
                      1));
                break;
            case 5:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      0,
                      0,
                      -1));
                break;
            default:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      0,
                      0,
                      0));
                break;
        }
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
            state.
              getInstance(
                ).
              getShader(
                0));
        state.
          setModifier(
            state.
              getInstance(
                ).
              getModifier(
                0));
    }
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
        float intervalMin =
          Float.
            NEGATIVE_INFINITY;
        float intervalMax =
          Float.
            POSITIVE_INFINITY;
        float orgX =
          r.
            ox;
        float invDirX =
          1 /
          r.
            dx;
        float t1;
        float t2;
        t1 =
          (minX -
             orgX) *
            invDirX;
        t2 =
          (maxX -
             orgX) *
            invDirX;
        int sideIn =
          -1;
        int sideOut =
          -1;
        if (invDirX >
              0) {
            if (t1 >
                  intervalMin) {
                intervalMin =
                  t1;
                sideIn =
                  0;
            }
            if (t2 <
                  intervalMax) {
                intervalMax =
                  t2;
                sideOut =
                  1;
            }
        }
        else {
            if (t2 >
                  intervalMin) {
                intervalMin =
                  t2;
                sideIn =
                  1;
            }
            if (t1 <
                  intervalMax) {
                intervalMax =
                  t1;
                sideOut =
                  0;
            }
        }
        if (intervalMin >
              intervalMax)
            return;
        float orgY =
          r.
            oy;
        float invDirY =
          1 /
          r.
            dy;
        t1 =
          (minY -
             orgY) *
            invDirY;
        t2 =
          (maxY -
             orgY) *
            invDirY;
        if (invDirY >
              0) {
            if (t1 >
                  intervalMin) {
                intervalMin =
                  t1;
                sideIn =
                  2;
            }
            if (t2 <
                  intervalMax) {
                intervalMax =
                  t2;
                sideOut =
                  3;
            }
        }
        else {
            if (t2 >
                  intervalMin) {
                intervalMin =
                  t2;
                sideIn =
                  3;
            }
            if (t1 <
                  intervalMax) {
                intervalMax =
                  t1;
                sideOut =
                  2;
            }
        }
        if (intervalMin >
              intervalMax)
            return;
        float orgZ =
          r.
            oz;
        float invDirZ =
          1 /
          r.
            dz;
        t1 =
          (minZ -
             orgZ) *
            invDirZ;
        t2 =
          (maxZ -
             orgZ) *
            invDirZ;
        if (invDirZ >
              0) {
            if (t1 >
                  intervalMin) {
                intervalMin =
                  t1;
                sideIn =
                  4;
            }
            if (t2 <
                  intervalMax) {
                intervalMax =
                  t2;
                sideOut =
                  5;
            }
        }
        else {
            if (t2 >
                  intervalMin) {
                intervalMin =
                  t2;
                sideIn =
                  5;
            }
            if (t1 <
                  intervalMax) {
                intervalMax =
                  t1;
                sideOut =
                  4;
            }
        }
        if (intervalMin >
              intervalMax)
            return;
        if (r.
              isInside(
                intervalMin)) {
            r.
              setMax(
                intervalMin);
            state.
              setIntersection(
                sideIn,
                0,
                0);
        }
        else
            if (r.
                  isInside(
                    intervalMax)) {
                r.
                  setMax(
                    intervalMax);
                state.
                  setIntersection(
                    sideOut,
                    0,
                    0);
            }
    }
    public int getNumPrimitives() { return 1;
    }
    public float getPrimitiveBound(int primID,
                                   int i) {
        switch (i) {
            case 0:
                return minX;
            case 1:
                return maxX;
            case 2:
                return minY;
            case 3:
                return maxY;
            case 4:
                return minZ;
            case 5:
                return maxZ;
            default:
                return 0;
        }
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) {
        BoundingBox bounds =
          new BoundingBox(
          minX,
          minY,
          minZ);
        bounds.
          include(
            maxX,
            maxY,
            maxZ);
        if (o2w ==
              null)
            return bounds;
        return o2w.
          transform(
            bounds);
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
