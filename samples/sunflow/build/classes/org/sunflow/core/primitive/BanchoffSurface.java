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
public class BanchoffSurface implements PrimitiveList {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       1.5F);
                                                     if (o2w != null) bounds =
                                                                        o2w.
                                                                          transform(
                                                                            bounds);
                                                     return bounds;
    }
    public float getPrimitiveBound(int primID, int i) { return (i &
                                                                  1) ==
                                                          0
                                                          ? -1.5F
                                                          : 1.5F;
    }
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
                                                          Point3 n =
                                                            parent.
                                                            transformWorldToObject(
                                                              state.
                                                                getPoint(
                                                                  ));
                                                          state.
                                                            getNormal(
                                                              ).
                                                            set(
                                                              n.
                                                                x *
                                                                (2 *
                                                                   n.
                                                                     x *
                                                                   n.
                                                                     x -
                                                                   1),
                                                              n.
                                                                y *
                                                                (2 *
                                                                   n.
                                                                     y *
                                                                   n.
                                                                     y -
                                                                   1),
                                                              n.
                                                                z *
                                                                (2 *
                                                                   n.
                                                                     z *
                                                                   n.
                                                                     z -
                                                                   1));
                                                          state.
                                                            getNormal(
                                                              ).
                                                            normalize(
                                                              );
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
                                                                makeFromW(
                                                                  state.
                                                                    getNormal(
                                                                      )));
    }
    public void intersectPrimitive(Ray r, int primID,
                                   IntersectionState state) {
        float rd2x =
          r.
            dx *
          r.
            dx;
        float rd2y =
          r.
            dy *
          r.
            dy;
        float rd2z =
          r.
            dz *
          r.
            dz;
        float ro2x =
          r.
            ox *
          r.
            ox;
        float ro2y =
          r.
            oy *
          r.
            oy;
        float ro2z =
          r.
            oz *
          r.
            oz;
        double A =
          rd2y *
          rd2y +
          rd2z *
          rd2z +
          rd2x *
          rd2x;
        double B =
          4 *
          (r.
             oy *
             rd2y *
             r.
               dy +
             r.
               oz *
             r.
               dz *
             rd2z +
             r.
               ox *
             r.
               dx *
             rd2x);
        double C =
          -rd2x -
          rd2y -
          rd2z +
          6 *
          (ro2y *
             rd2y +
             ro2z *
             rd2z +
             ro2x *
             rd2x);
        double D =
          2 *
          (2 *
             ro2z *
             r.
               oz *
             r.
               dz -
             r.
               oz *
             r.
               dz +
             2 *
             ro2x *
             r.
               ox *
             r.
               dx +
             2 *
             ro2y *
             r.
               oy *
             r.
               dy -
             r.
               ox *
             r.
               dx -
             r.
               oy *
             r.
               dy);
        double E =
          3.0F /
          8.0F +
          (-ro2z +
             ro2z *
             ro2z -
             ro2y +
             ro2y *
             ro2y -
             ro2x +
             ro2x *
             ro2x);
        double[] t =
          Solvers.
          solveQuartic(
            A,
            B,
            C,
            D,
            E);
        if (t !=
              null) {
            if (t[0] >=
                  r.
                  getMax(
                    ) ||
                  t[t.
                      length -
                      1] <=
                  r.
                  getMin(
                    ))
                return;
            for (int i =
                   0;
                 i <
                   t.
                     length;
                 i++) {
                if (t[i] >
                      r.
                      getMin(
                        )) {
                    r.
                      setMax(
                        (float)
                          t[i]);
                    state.
                      setIntersection(
                        0,
                        0,
                        0);
                    return;
                }
            }
        }
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public BanchoffSurface() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/xeP2QDdkRMAAA==");
}
