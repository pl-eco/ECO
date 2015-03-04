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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "8XRWGQAA");
}
