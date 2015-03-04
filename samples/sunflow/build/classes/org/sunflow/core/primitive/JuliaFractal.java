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
public class JuliaFractal implements PrimitiveList {
    private static float BOUNDING_RADIUS = (float) Math.sqrt(3);
    private static float BOUNDING_RADIUS2 = 3;
    private static float ESCAPE_THRESHOLD = 10.0F;
    private static float DELTA = 1.0E-4F;
    private float cx;
    private float cy;
    private float cz;
    private float cw;
    private int maxIterations;
    private float epsilon;
    public JuliaFractal() { super();
                            cw = -0.4F;
                            cx = 0.2F;
                            cy = 0.3F;
                            cz = -0.2F;
                            maxIterations = 15;
                            epsilon = 1.0E-5F; }
    public int getNumPrimitives() { return 1; }
    public float getPrimitiveBound(int primID, int i) { return (i & 1) ==
                                                          0
                                                          ? -BOUNDING_RADIUS
                                                          : BOUNDING_RADIUS;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       BOUNDING_RADIUS);
                                                     if (o2w != null) bounds =
                                                                        o2w.
                                                                          transform(
                                                                            bounds);
                                                     return bounds;
    }
    public void intersectPrimitive(Ray r, int primID, IntersectionState state) {
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
          BOUNDING_RADIUS2;
        float qt =
          r.
          getMin(
            );
        if (qc >
              0) {
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
            double[] t =
              Solvers.
              solveQuadric(
                qa,
                qb,
                qc);
            if (t ==
                  null ||
                  t[0] >=
                  r.
                  getMax(
                    ) ||
                  t[1] <=
                  r.
                  getMin(
                    ))
                return;
            qt =
              (float)
                t[0];
        }
        float dist =
          Float.
            POSITIVE_INFINITY;
        float rox =
          r.
            ox +
          qt *
          r.
            dx;
        float roy =
          r.
            oy +
          qt *
          r.
            dy;
        float roz =
          r.
            oz +
          qt *
          r.
            dz;
        float invRayLength =
          (float)
            (1 /
               Math.
               sqrt(
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
                     dz));
        while (true) {
            float zw =
              rox;
            float zx =
              roy;
            float zy =
              roz;
            float zz =
              0;
            float zpw =
              1;
            float zpx =
              0;
            float zpy =
              0;
            float zpz =
              0;
            float dotz =
              0;
            for (int i =
                   0;
                 i <
                   maxIterations;
                 i++) {
                {
                    float nw =
                      zw *
                      zpw -
                      zx *
                      zpx -
                      zy *
                      zpy -
                      zz *
                      zpz;
                    float nx =
                      zw *
                      zpx +
                      zx *
                      zpw +
                      zy *
                      zpz -
                      zz *
                      zpy;
                    float ny =
                      zw *
                      zpy +
                      zy *
                      zpw +
                      zz *
                      zpx -
                      zx *
                      zpz;
                    zpz =
                      2 *
                        (zw *
                           zpz +
                           zz *
                           zpw +
                           zx *
                           zpy -
                           zy *
                           zpx);
                    zpw =
                      2 *
                        nw;
                    zpx =
                      2 *
                        nx;
                    zpy =
                      2 *
                        ny;
                }
                {
                    float nw =
                      zw *
                      zw -
                      zx *
                      zx -
                      zy *
                      zy -
                      zz *
                      zz +
                      cw;
                    zx =
                      2 *
                        zw *
                        zx +
                        cx;
                    zy =
                      2 *
                        zw *
                        zy +
                        cy;
                    zz =
                      2 *
                        zw *
                        zz +
                        cz;
                    zw =
                      nw;
                }
                dotz =
                  zw *
                    zw +
                    zx *
                    zx +
                    zy *
                    zy +
                    zz *
                    zz;
                if (dotz >
                      ESCAPE_THRESHOLD)
                    break;
            }
            float normZ =
              (float)
                Math.
                sqrt(
                  dotz);
            dist =
              0.5F *
                normZ *
                (float)
                  Math.
                  log(
                    normZ) /
                length(
                  zpw,
                  zpx,
                  zpy,
                  zpz);
            rox +=
              dist *
                r.
                  dx;
            roy +=
              dist *
                r.
                  dy;
            roz +=
              dist *
                r.
                  dz;
            qt +=
              dist;
            if (dist *
                  invRayLength <
                  epsilon)
                break;
            if (rox *
                  rox +
                  roy *
                  roy +
                  roz *
                  roz >
                  BOUNDING_RADIUS2)
                return;
        }
        if (!r.
              isInside(
                qt))
            return;
        if (dist *
              invRayLength <
              epsilon) {
            r.
              setMax(
                qt);
            state.
              setIntersection(
                0,
                0,
                0);
        }
    }
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
                                                          Point3 p =
                                                            parent.
                                                            transformWorldToObject(
                                                              state.
                                                                getPoint(
                                                                  ));
                                                          float gx1w =
                                                            p.
                                                              x -
                                                            DELTA;
                                                          float gx1x =
                                                            p.
                                                              y;
                                                          float gx1y =
                                                            p.
                                                              z;
                                                          float gx1z =
                                                            0;
                                                          float gx2w =
                                                            p.
                                                              x +
                                                            DELTA;
                                                          float gx2x =
                                                            p.
                                                              y;
                                                          float gx2y =
                                                            p.
                                                              z;
                                                          float gx2z =
                                                            0;
                                                          float gy1w =
                                                            p.
                                                              x;
                                                          float gy1x =
                                                            p.
                                                              y -
                                                            DELTA;
                                                          float gy1y =
                                                            p.
                                                              z;
                                                          float gy1z =
                                                            0;
                                                          float gy2w =
                                                            p.
                                                              x;
                                                          float gy2x =
                                                            p.
                                                              y +
                                                            DELTA;
                                                          float gy2y =
                                                            p.
                                                              z;
                                                          float gy2z =
                                                            0;
                                                          float gz1w =
                                                            p.
                                                              x;
                                                          float gz1x =
                                                            p.
                                                              y;
                                                          float gz1y =
                                                            p.
                                                              z -
                                                            DELTA;
                                                          float gz1z =
                                                            0;
                                                          float gz2w =
                                                            p.
                                                              x;
                                                          float gz2x =
                                                            p.
                                                              y;
                                                          float gz2y =
                                                            p.
                                                              z +
                                                            DELTA;
                                                          float gz2z =
                                                            0;
                                                          for (int i =
                                                                 0;
                                                               i <
                                                                 maxIterations;
                                                               i++) {
                                                              {
                                                                  float nw =
                                                                    gx1w *
                                                                    gx1w -
                                                                    gx1x *
                                                                    gx1x -
                                                                    gx1y *
                                                                    gx1y -
                                                                    gx1z *
                                                                    gx1z +
                                                                    cw;
                                                                  gx1x =
                                                                    2 *
                                                                      gx1w *
                                                                      gx1x +
                                                                      cx;
                                                                  gx1y =
                                                                    2 *
                                                                      gx1w *
                                                                      gx1y +
                                                                      cy;
                                                                  gx1z =
                                                                    2 *
                                                                      gx1w *
                                                                      gx1z +
                                                                      cz;
                                                                  gx1w =
                                                                    nw;
                                                              }
                                                              {
                                                                  float nw =
                                                                    gx2w *
                                                                    gx2w -
                                                                    gx2x *
                                                                    gx2x -
                                                                    gx2y *
                                                                    gx2y -
                                                                    gx2z *
                                                                    gx2z +
                                                                    cw;
                                                                  gx2x =
                                                                    2 *
                                                                      gx2w *
                                                                      gx2x +
                                                                      cx;
                                                                  gx2y =
                                                                    2 *
                                                                      gx2w *
                                                                      gx2y +
                                                                      cy;
                                                                  gx2z =
                                                                    2 *
                                                                      gx2w *
                                                                      gx2z +
                                                                      cz;
                                                                  gx2w =
                                                                    nw;
                                                              }
                                                              {
                                                                  float nw =
                                                                    gy1w *
                                                                    gy1w -
                                                                    gy1x *
                                                                    gy1x -
                                                                    gy1y *
                                                                    gy1y -
                                                                    gy1z *
                                                                    gy1z +
                                                                    cw;
                                                                  gy1x =
                                                                    2 *
                                                                      gy1w *
                                                                      gy1x +
                                                                      cx;
                                                                  gy1y =
                                                                    2 *
                                                                      gy1w *
                                                                      gy1y +
                                                                      cy;
                                                                  gy1z =
                                                                    2 *
                                                                      gy1w *
                                                                      gy1z +
                                                                      cz;
                                                                  gy1w =
                                                                    nw;
                                                              }
                                                              {
                                                                  float nw =
                                                                    gy2w *
                                                                    gy2w -
                                                                    gy2x *
                                                                    gy2x -
                                                                    gy2y *
                                                                    gy2y -
                                                                    gy2z *
                                                                    gy2z +
                                                                    cw;
                                                                  gy2x =
                                                                    2 *
                                                                      gy2w *
                                                                      gy2x +
                                                                      cx;
                                                                  gy2y =
                                                                    2 *
                                                                      gy2w *
                                                                      gy2y +
                                                                      cy;
                                                                  gy2z =
                                                                    2 *
                                                                      gy2w *
                                                                      gy2z +
                                                                      cz;
                                                                  gy2w =
                                                                    nw;
                                                              }
                                                              {
                                                                  float nw =
                                                                    gz1w *
                                                                    gz1w -
                                                                    gz1x *
                                                                    gz1x -
                                                                    gz1y *
                                                                    gz1y -
                                                                    gz1z *
                                                                    gz1z +
                                                                    cw;
                                                                  gz1x =
                                                                    2 *
                                                                      gz1w *
                                                                      gz1x +
                                                                      cx;
                                                                  gz1y =
                                                                    2 *
                                                                      gz1w *
                                                                      gz1y +
                                                                      cy;
                                                                  gz1z =
                                                                    2 *
                                                                      gz1w *
                                                                      gz1z +
                                                                      cz;
                                                                  gz1w =
                                                                    nw;
                                                              }
                                                              {
                                                                  float nw =
                                                                    gz2w *
                                                                    gz2w -
                                                                    gz2x *
                                                                    gz2x -
                                                                    gz2y *
                                                                    gz2y -
                                                                    gz2z *
                                                                    gz2z +
                                                                    cw;
                                                                  gz2x =
                                                                    2 *
                                                                      gz2w *
                                                                      gz2x +
                                                                      cx;
                                                                  gz2y =
                                                                    2 *
                                                                      gz2w *
                                                                      gz2y +
                                                                      cy;
                                                                  gz2z =
                                                                    2 *
                                                                      gz2w *
                                                                      gz2z +
                                                                      cz;
                                                                  gz2w =
                                                                    nw;
                                                              }
                                                          }
                                                          float gradX =
                                                            length(
                                                              gx2w,
                                                              gx2x,
                                                              gx2y,
                                                              gx2z) -
                                                            length(
                                                              gx1w,
                                                              gx1x,
                                                              gx1y,
                                                              gx1z);
                                                          float gradY =
                                                            length(
                                                              gy2w,
                                                              gy2x,
                                                              gy2y,
                                                              gy2z) -
                                                            length(
                                                              gy1w,
                                                              gy1x,
                                                              gy1y,
                                                              gy1z);
                                                          float gradZ =
                                                            length(
                                                              gz2w,
                                                              gz2x,
                                                              gz2y,
                                                              gz2z) -
                                                            length(
                                                              gz1w,
                                                              gz1x,
                                                              gz1y,
                                                              gz1z);
                                                          Vector3 n =
                                                            new Vector3(
                                                            (float)
                                                              gradX,
                                                            (float)
                                                              gradY,
                                                            (float)
                                                              gradZ);
                                                          state.
                                                            getNormal(
                                                              ).
                                                            set(
                                                              parent.
                                                                transformNormalObjectToWorld(
                                                                  n));
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
                                                          state.
                                                            getPoint(
                                                              ).
                                                            x +=
                                                            state.
                                                              getNormal(
                                                                ).
                                                              x *
                                                              epsilon *
                                                              20;
                                                          state.
                                                            getPoint(
                                                              ).
                                                            y +=
                                                            state.
                                                              getNormal(
                                                                ).
                                                              y *
                                                              epsilon *
                                                              20;
                                                          state.
                                                            getPoint(
                                                              ).
                                                            z +=
                                                            state.
                                                              getNormal(
                                                                ).
                                                              z *
                                                              epsilon *
                                                              20;
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
    }
    private static float length(float w, float x,
                                float y,
                                float z) { return (float)
                                                    Math.
                                                    sqrt(
                                                      w *
                                                        w +
                                                        x *
                                                        x +
                                                        y *
                                                        y +
                                                        z *
                                                        z);
    }
    public boolean update(ParameterList pl, SunflowAPI api) {
        maxIterations =
          pl.
            getInt(
              "iterations",
              maxIterations);
        epsilon =
          pl.
            getFloat(
              "epsilon",
              epsilon);
        cw =
          pl.
            getFloat(
              "cw",
              cw);
        cx =
          pl.
            getFloat(
              "cx",
              cx);
        cy =
          pl.
            getFloat(
              "cy",
              cy);
        cz =
          pl.
            getFloat(
              "cz",
              cz);
        return true;
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "FQu/2PDzB19aeY+/vfwXF0PuBXwZAAA=");
}
