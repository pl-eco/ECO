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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
