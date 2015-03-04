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
import org.sunflow.math.Vector3;
public class Plane implements PrimitiveList {
    private Point3 center;
    private Vector3 normal;
    int k;
    private float bnu;
    private float bnv;
    private float bnd;
    private float cnu;
    private float cnv;
    private float cnd;
    public Plane() { super();
                     center = new Point3(0, 0, 0);
                     normal = new Vector3(0, 1, 0);
                     k = 3;
                     bnu = (bnv = (bnd = 0));
                     cnu = (cnv = (cnd = 0)); }
    public boolean update(ParameterList pl, SunflowAPI api) { center = pl.
                                                                         getPoint(
                                                                           "center",
                                                                           center);
                                                              Point3 b =
                                                                pl.
                                                                getPoint(
                                                                  "point1",
                                                                  null);
                                                              Point3 c =
                                                                pl.
                                                                getPoint(
                                                                  "point2",
                                                                  null);
                                                              if (b !=
                                                                    null &&
                                                                    c !=
                                                                    null) {
                                                                  Point3 v0 =
                                                                    center;
                                                                  Point3 v1 =
                                                                    b;
                                                                  Point3 v2 =
                                                                    c;
                                                                  Vector3 ng =
                                                                    normal =
                                                                    Vector3.
                                                                      cross(
                                                                        Point3.
                                                                          sub(
                                                                            v1,
                                                                            v0,
                                                                            new Vector3(
                                                                              )),
                                                                        Point3.
                                                                          sub(
                                                                            v2,
                                                                            v0,
                                                                            new Vector3(
                                                                              )),
                                                                        new Vector3(
                                                                          )).
                                                                      normalize(
                                                                        );
                                                                  if (Math.
                                                                        abs(
                                                                          ng.
                                                                            x) >
                                                                        Math.
                                                                        abs(
                                                                          ng.
                                                                            y) &&
                                                                        Math.
                                                                        abs(
                                                                          ng.
                                                                            x) >
                                                                        Math.
                                                                        abs(
                                                                          ng.
                                                                            z))
                                                                      k =
                                                                        0;
                                                                  else
                                                                      if (Math.
                                                                            abs(
                                                                              ng.
                                                                                y) >
                                                                            Math.
                                                                            abs(
                                                                              ng.
                                                                                z))
                                                                          k =
                                                                            1;
                                                                      else
                                                                          k =
                                                                            2;
                                                                  float ax;
                                                                  float ay;
                                                                  float bx;
                                                                  float by;
                                                                  float cx;
                                                                  float cy;
                                                                  switch (k) {
                                                                      case 0:
                                                                          {
                                                                              ax =
                                                                                v0.
                                                                                  y;
                                                                              ay =
                                                                                v0.
                                                                                  z;
                                                                              bx =
                                                                                v2.
                                                                                  y -
                                                                                  ax;
                                                                              by =
                                                                                v2.
                                                                                  z -
                                                                                  ay;
                                                                              cx =
                                                                                v1.
                                                                                  y -
                                                                                  ax;
                                                                              cy =
                                                                                v1.
                                                                                  z -
                                                                                  ay;
                                                                              break;
                                                                          }
                                                                      case 1:
                                                                          {
                                                                              ax =
                                                                                v0.
                                                                                  z;
                                                                              ay =
                                                                                v0.
                                                                                  x;
                                                                              bx =
                                                                                v2.
                                                                                  z -
                                                                                  ax;
                                                                              by =
                                                                                v2.
                                                                                  x -
                                                                                  ay;
                                                                              cx =
                                                                                v1.
                                                                                  z -
                                                                                  ax;
                                                                              cy =
                                                                                v1.
                                                                                  x -
                                                                                  ay;
                                                                              break;
                                                                          }
                                                                      case 2:
                                                                      default:
                                                                          {
                                                                              ax =
                                                                                v0.
                                                                                  x;
                                                                              ay =
                                                                                v0.
                                                                                  y;
                                                                              bx =
                                                                                v2.
                                                                                  x -
                                                                                  ax;
                                                                              by =
                                                                                v2.
                                                                                  y -
                                                                                  ay;
                                                                              cx =
                                                                                v1.
                                                                                  x -
                                                                                  ax;
                                                                              cy =
                                                                                v1.
                                                                                  y -
                                                                                  ay;
                                                                          }
                                                                  }
                                                                  float det =
                                                                    bx *
                                                                    cy -
                                                                    by *
                                                                    cx;
                                                                  bnu =
                                                                    -by /
                                                                      det;
                                                                  bnv =
                                                                    bx /
                                                                      det;
                                                                  bnd =
                                                                    (by *
                                                                       ax -
                                                                       bx *
                                                                       ay) /
                                                                      det;
                                                                  cnu =
                                                                    cy /
                                                                      det;
                                                                  cnv =
                                                                    -cx /
                                                                      det;
                                                                  cnd =
                                                                    (cx *
                                                                       ay -
                                                                       cy *
                                                                       ax) /
                                                                      det;
                                                              }
                                                              else {
                                                                  normal =
                                                                    pl.
                                                                      getVector(
                                                                        "normal",
                                                                        normal);
                                                                  k =
                                                                    3;
                                                                  bnu =
                                                                    (bnv =
                                                                       (bnd =
                                                                          0));
                                                                  cnu =
                                                                    (cnv =
                                                                       (cnd =
                                                                          0));
                                                              }
                                                              return true;
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
        Instance parent =
          state.
          getInstance(
            );
        Vector3 worldNormal =
          parent.
          transformNormalObjectToWorld(
            normal);
        state.
          getNormal(
            ).
          set(
            worldNormal);
        state.
          getGeoNormal(
            ).
          set(
            worldNormal);
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
        Point3 p =
          parent.
          transformWorldToObject(
            state.
              getPoint(
                ));
        float hu;
        float hv;
        switch (k) {
            case 0:
                {
                    hu =
                      p.
                        y;
                    hv =
                      p.
                        z;
                    break;
                }
            case 1:
                {
                    hu =
                      p.
                        z;
                    hv =
                      p.
                        x;
                    break;
                }
            case 2:
                {
                    hu =
                      p.
                        x;
                    hv =
                      p.
                        y;
                    break;
                }
            default:
                hu =
                  (hv =
                     0);
        }
        state.
          getUV(
            ).
          x =
          hu *
            bnu +
            hv *
            bnv +
            bnd;
        state.
          getUV(
            ).
          y =
          hu *
            cnu +
            hv *
            cnv +
            cnd;
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                normal));
    }
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
        float dn =
          normal.
            x *
          r.
            dx +
          normal.
            y *
          r.
            dy +
          normal.
            z *
          r.
            dz;
        if (dn ==
              0.0)
            return;
        float t =
          ((center.
              x -
              r.
                ox) *
             normal.
               x +
             (center.
                y -
                r.
                  oy) *
             normal.
               y +
             (center.
                z -
                r.
                  oz) *
             normal.
               z) /
          dn;
        if (r.
              isInside(
                t)) {
            r.
              setMax(
                t);
            state.
              setIntersection(
                0,
                0,
                0);
        }
    }
    public int getNumPrimitives() { return 1;
    }
    public float getPrimitiveBound(int primID,
                                   int i) {
        return 0;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) {
        return null;
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "sumFu59ZfYt/APgveIUF6+wXAAA=");
}
