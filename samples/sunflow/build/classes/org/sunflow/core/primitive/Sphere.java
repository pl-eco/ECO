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
import org.sunflow.math.Point3;
import org.sunflow.math.Solvers;
import org.sunflow.math.Vector3;
public class Sphere implements PrimitiveList {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       1);
                                                     if (o2w != null) bounds =
                                                                        o2w.
                                                                          transform(
                                                                            bounds);
                                                     return bounds;
    }
    public float getPrimitiveBound(int primID, int i) { return (i &
                                                                  1) ==
                                                          0
                                                          ? -1
                                                          : 1; }
    public int getNumPrimitives() { return 1; }
    public void prepareShadingState(ShadingState state) { state.init(
                                                                  );
                                                          state.getRay(
                                                                  ).
                                                            getPoint(
                                                              state.
                                                                getPoint(
                                                                  ));
                                                          Instance parent =
                                                            state.
                                                            getInstance(
                                                              );
                                                          Point3 localPoint =
                                                            parent.
                                                            transformWorldToObject(
                                                              state.
                                                                getPoint(
                                                                  ));
                                                          state.
                                                            getNormal(
                                                              ).
                                                            set(
                                                              localPoint.
                                                                x,
                                                              localPoint.
                                                                y,
                                                              localPoint.
                                                                z);
                                                          state.
                                                            getNormal(
                                                              ).
                                                            normalize(
                                                              );
                                                          float phi =
                                                            (float)
                                                              Math.
                                                              atan2(
                                                                state.
                                                                  getNormal(
                                                                    ).
                                                                  y,
                                                                state.
                                                                  getNormal(
                                                                    ).
                                                                  x);
                                                          if (phi <
                                                                0)
                                                              phi +=
                                                                2 *
                                                                  Math.
                                                                    PI;
                                                          float theta =
                                                            (float)
                                                              Math.
                                                              acos(
                                                                state.
                                                                  getNormal(
                                                                    ).
                                                                  z);
                                                          state.
                                                            getUV(
                                                              ).
                                                            y =
                                                            theta /
                                                              (float)
                                                                Math.
                                                                  PI;
                                                          state.
                                                            getUV(
                                                              ).
                                                            x =
                                                            phi /
                                                              (float)
                                                                (2 *
                                                                   Math.
                                                                     PI);
                                                          Vector3 v =
                                                            new Vector3(
                                                            );
                                                          v.
                                                            x =
                                                            -2 *
                                                              (float)
                                                                Math.
                                                                  PI *
                                                              state.
                                                                getNormal(
                                                                  ).
                                                                y;
                                                          v.
                                                            y =
                                                            2 *
                                                              (float)
                                                                Math.
                                                                  PI *
                                                              state.
                                                                getNormal(
                                                                  ).
                                                                x;
                                                          v.
                                                            z =
                                                            0;
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
                                                          Vector3 worldNormal =
                                                            parent.
                                                            transformNormalObjectToWorld(
                                                              state.
                                                                getNormal(
                                                                  ));
                                                          v =
                                                            parent.
                                                              transformVectorObjectToWorld(
                                                                v);
                                                          state.
                                                            getNormal(
                                                              ).
                                                            set(
                                                              worldNormal);
                                                          state.
                                                            getNormal(
                                                              ).
                                                            normalize(
                                                              );
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
                                                                makeFromWV(
                                                                  state.
                                                                    getNormal(
                                                                      ),
                                                                  v));
    }
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
        float qa =
          r.
            dx *
          r.
            dx +
          r.
            dy *
          r.
            dy +
          r.
            dz *
          r.
            dz;
        float qb =
          2 *
          (r.
             dx *
             r.
               ox +
             r.
               dy *
             r.
               oy +
             r.
               dz *
             r.
               oz);
        float qc =
          r.
            ox *
          r.
            ox +
          r.
            oy *
          r.
            oy +
          r.
            oz *
          r.
            oz -
          1;
        double[] t =
          Solvers.
          solveQuadric(
            qa,
            qb,
            qc);
        if (t !=
              null) {
            if (t[0] >=
                  r.
                  getMax(
                    ) ||
                  t[1] <=
                  r.
                  getMin(
                    ))
                return;
            if (t[0] >
                  r.
                  getMin(
                    ))
                r.
                  setMax(
                    (float)
                      t[0]);
            else
                r.
                  setMax(
                    (float)
                      t[1]);
            state.
              setIntersection(
                0,
                0,
                0);
        }
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    public Sphere() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "YW0TAAA=");
}
