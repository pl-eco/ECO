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
import org.sunflow.math.Point3;
import org.sunflow.math.Solvers;
import org.sunflow.math.Vector3;
public class ParticleSurface implements PrimitiveList {
    private float[] particles;
    private float r;
    private float r2;
    private int n;
    public ParticleSurface() { super();
                               particles = null;
                               r = (r2 = 1);
                               n = 0; }
    public int getNumPrimitives() { return n; }
    public float getPrimitiveBound(int primID, int i) { float c = particles[primID *
                                                                              3 +
                                                                              (i >>>
                                                                                 1)];
                                                        return (i &
                                                                  1) ==
                                                          0
                                                          ? c -
                                                          r
                                                          : c +
                                                          r;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) {
        BoundingBox bounds =
          new BoundingBox(
          );
        for (int i =
               0,
               i3 =
                 0;
             i <
               n;
             i++,
               i3 +=
                 3)
            bounds.
              include(
                particles[i3],
                particles[i3 +
                            1],
                particles[i3 +
                            2]);
        bounds.
          include(
            bounds.
              getMinimum(
                ).
              x -
              r,
            bounds.
              getMinimum(
                ).
              y -
              r,
            bounds.
              getMinimum(
                ).
              z -
              r);
        bounds.
          include(
            bounds.
              getMaximum(
                ).
              x +
              r,
            bounds.
              getMaximum(
                ).
              y +
              r,
            bounds.
              getMaximum(
                ).
              z +
              r);
        return o2w ==
          null
          ? bounds
          : o2w.
          transform(
            bounds);
    }
    public void intersectPrimitive(Ray r, int primID,
                                   IntersectionState state) {
        int i3 =
          primID *
          3;
        float ocx =
          r.
            ox -
          particles[i3 +
                      0];
        float ocy =
          r.
            oy -
          particles[i3 +
                      1];
        float ocz =
          r.
            oz -
          particles[i3 +
                      2];
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
             ocx +
             r.
               dy *
             ocy +
             r.
               dz *
             ocz);
        float qc =
          ocx *
          ocx +
          ocy *
          ocy +
          ocz *
          ocz -
          r2;
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
                primID,
                0,
                0);
        }
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
        Point3 localPoint =
          state.
          getInstance(
            ).
          transformWorldToObject(
            state.
              getPoint(
                ));
        localPoint.
          x -=
          particles[3 *
                      state.
                      getPrimitiveID(
                        ) +
                      0];
        localPoint.
          y -=
          particles[3 *
                      state.
                      getPrimitiveID(
                        ) +
                      1];
        localPoint.
          z -=
          particles[3 *
                      state.
                      getPrimitiveID(
                        ) +
                      2];
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
        Vector3 worldNormal =
          state.
          getInstance(
            ).
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
    public boolean update(ParameterList pl, SunflowAPI api) {
        FloatParameter p =
          pl.
          getPointArray(
            "particles");
        if (p !=
              null)
            particles =
              p.
                data;
        r =
          pl.
            getFloat(
              "radius",
              r);
        r2 =
          r *
            r;
        n =
          pl.
            getInt(
              "num",
              n);
        return particles !=
          null &&
          n <=
          particles.
            length /
          3;
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/fR/AMC2BNX/FwAA");
}
