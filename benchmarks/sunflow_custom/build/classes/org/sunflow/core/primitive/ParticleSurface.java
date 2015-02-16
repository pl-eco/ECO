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
      1169362834000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "FwAA");
}
