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
      1169362916000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
