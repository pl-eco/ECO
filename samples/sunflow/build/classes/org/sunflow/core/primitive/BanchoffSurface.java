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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
