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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "b/OX7H8ArV7BYokWAAA=");
}
