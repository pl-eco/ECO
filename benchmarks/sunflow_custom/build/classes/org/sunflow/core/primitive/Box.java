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
      1169362762000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/eZ/suG1e88vvctfsv8BZ0f1lp8WAAA=");
}
