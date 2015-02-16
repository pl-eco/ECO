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
      1169362820000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "0tJ7/O3lv8ohuaxsGQAA");
}
