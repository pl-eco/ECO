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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
