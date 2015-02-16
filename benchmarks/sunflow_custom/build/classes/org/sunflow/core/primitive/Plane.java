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
      1169362846000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "Al2IQOjcFwAA");
}
