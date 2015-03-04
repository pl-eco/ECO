package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Instance;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.LightSample;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.core.ParameterList.FloatParameter;
import org.sunflow.core.ParameterList.InterpolationType;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class Hair implements PrimitiveList, Shader {
    private int numSegments;
    private float[] points;
    private FloatParameter widths;
    public Hair() { super();
                    numSegments = 1;
                    points = null;
                    widths = new FloatParameter(1.0F); }
    public int getNumPrimitives() { return numSegments * (points.length /
                                                            (3 *
                                                               (numSegments +
                                                                  1))); }
    public float getPrimitiveBound(int primID, int i) { int hair = primID /
                                                          numSegments;
                                                        int line = primID %
                                                          numSegments;
                                                        int vn = hair * (numSegments +
                                                                           1) +
                                                          line;
                                                        int vRoot =
                                                          hair *
                                                          3 *
                                                          (numSegments +
                                                             1);
                                                        int v0 = vRoot +
                                                          line *
                                                          3;
                                                        int v1 = v0 +
                                                          3;
                                                        int axis =
                                                          i >>>
                                                          1;
                                                        if ((i & 1) ==
                                                              0) {
                                                            return Math.
                                                              min(
                                                                points[v0 +
                                                                         axis] -
                                                                  0.5F *
                                                                  getWidth(
                                                                    vn),
                                                                points[v1 +
                                                                         axis] -
                                                                  0.5F *
                                                                  getWidth(
                                                                    vn +
                                                                      1));
                                                        }
                                                        else {
                                                            return Math.
                                                              max(
                                                                points[v0 +
                                                                         axis] +
                                                                  0.5F *
                                                                  getWidth(
                                                                    vn),
                                                                points[v1 +
                                                                         axis] +
                                                                  0.5F *
                                                                  getWidth(
                                                                    vn +
                                                                      1));
                                                        } }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       );
                                                     for (int i =
                                                            0, j =
                                                                 0;
                                                          i <
                                                            points.
                                                              length;
                                                          i +=
                                                            3,
                                                            j++) {
                                                         float w =
                                                           0.5F *
                                                           getWidth(
                                                             j);
                                                         bounds.
                                                           include(
                                                             points[i] -
                                                               w,
                                                             points[i +
                                                                      1] -
                                                               w,
                                                             points[i +
                                                                      2] -
                                                               w);
                                                         bounds.
                                                           include(
                                                             points[i] +
                                                               w,
                                                             points[i +
                                                                      1] +
                                                               w,
                                                             points[i +
                                                                      2] +
                                                               w);
                                                     }
                                                     if (o2w != null)
                                                         bounds =
                                                           o2w.
                                                             transform(
                                                               bounds);
                                                     return bounds;
    }
    private float getWidth(int i) { switch (widths.interp) { case NONE:
                                                                 return widths.
                                                                          data[0];
                                                             case VERTEX:
                                                                 return widths.
                                                                          data[i];
                                                             default:
                                                                 return 0;
                                    } }
    private Vector3 getTangent(int line, int v0, float v) {
        Vector3 vcurr =
          new Vector3(
          points[v0 +
                   3] -
            points[v0 +
                     0],
          points[v0 +
                   4] -
            points[v0 +
                     1],
          points[v0 +
                   5] -
            points[v0 +
                     2]);
        vcurr.
          normalize(
            );
        if (line ==
              0 ||
              line ==
              numSegments -
              1)
            return vcurr;
        if (v <=
              0.5F) {
            Vector3 vprev =
              new Vector3(
              points[v0 +
                       0] -
                points[v0 -
                         3],
              points[v0 +
                       1] -
                points[v0 -
                         2],
              points[v0 +
                       2] -
                points[v0 -
                         1]);
            vprev.
              normalize(
                );
            float t =
              v +
              0.5F;
            float s =
              1 -
              t;
            float vx =
              vprev.
                x *
              s +
              vcurr.
                x *
              t;
            float vy =
              vprev.
                y *
              s +
              vcurr.
                y *
              t;
            float vz =
              vprev.
                z *
              s +
              vcurr.
                z *
              t;
            return new Vector3(
              vx,
              vy,
              vz);
        }
        else {
            v0 +=
              3;
            Vector3 vnext =
              new Vector3(
              points[v0 +
                       3] -
                points[v0 +
                         0],
              points[v0 +
                       4] -
                points[v0 +
                         1],
              points[v0 +
                       5] -
                points[v0 +
                         2]);
            vnext.
              normalize(
                );
            float t =
              1.5F -
              v;
            float s =
              1 -
              t;
            float vx =
              vnext.
                x *
              s +
              vcurr.
                x *
              t;
            float vy =
              vnext.
                y *
              s +
              vcurr.
                y *
              t;
            float vz =
              vnext.
                z *
              s +
              vcurr.
                z *
              t;
            return new Vector3(
              vx,
              vy,
              vz);
        }
    }
    public void intersectPrimitive(Ray r, int primID,
                                   IntersectionState state) {
        int hair =
          primID /
          numSegments;
        int line =
          primID %
          numSegments;
        int vRoot =
          hair *
          3 *
          (numSegments +
             1);
        int v0 =
          vRoot +
          line *
          3;
        int v1 =
          v0 +
          3;
        float vx =
          points[v1 +
                   0] -
          points[v0 +
                   0];
        float vy =
          points[v1 +
                   1] -
          points[v0 +
                   1];
        float vz =
          points[v1 +
                   2] -
          points[v0 +
                   2];
        float ux =
          r.
            dy *
          vz -
          r.
            dz *
          vy;
        float uy =
          r.
            dz *
          vx -
          r.
            dx *
          vz;
        float uz =
          r.
            dx *
          vy -
          r.
            dy *
          vx;
        float nx =
          uy *
          vz -
          uz *
          vy;
        float ny =
          uz *
          vx -
          ux *
          vz;
        float nz =
          ux *
          vy -
          uy *
          vx;
        float tden =
          1 /
          (nx *
             r.
               dx +
             ny *
             r.
               dy +
             nz *
             r.
               dz);
        float tnum =
          nx *
          (points[v0 +
                    0] -
             r.
               ox) +
          ny *
          (points[v0 +
                    1] -
             r.
               oy) +
          nz *
          (points[v0 +
                    2] -
             r.
               oz);
        float t =
          tnum *
          tden;
        if (r.
              isInside(
                t)) {
            int vn =
              hair *
              (numSegments +
                 1) +
              line;
            float px =
              r.
                ox +
              t *
              r.
                dx;
            float py =
              r.
                oy +
              t *
              r.
                dy;
            float pz =
              r.
                oz +
              t *
              r.
                dz;
            float qx =
              px -
              points[v0 +
                       0];
            float qy =
              py -
              points[v0 +
                       1];
            float qz =
              pz -
              points[v0 +
                       2];
            float q =
              (vx *
                 qx +
                 vy *
                 qy +
                 vz *
                 qz) /
              (vx *
                 vx +
                 vy *
                 vy +
                 vz *
                 vz);
            if (q <=
                  0) {
                if (line ==
                      0)
                    return;
                float dx =
                  points[v0 +
                           0] -
                  px;
                float dy =
                  points[v0 +
                           1] -
                  py;
                float dz =
                  points[v0 +
                           2] -
                  pz;
                float d2 =
                  dx *
                  dx +
                  dy *
                  dy +
                  dz *
                  dz;
                float width =
                  getWidth(
                    vn);
                if (d2 <
                      width *
                      width *
                      0.25F) {
                    r.
                      setMax(
                        t);
                    state.
                      setIntersection(
                        primID,
                        0,
                        0);
                }
            }
            else
                if (q >=
                      1) {
                    float dx =
                      points[v1 +
                               0] -
                      px;
                    float dy =
                      points[v1 +
                               1] -
                      py;
                    float dz =
                      points[v1 +
                               2] -
                      pz;
                    float d2 =
                      dx *
                      dx +
                      dy *
                      dy +
                      dz *
                      dz;
                    float width =
                      getWidth(
                        vn +
                          1);
                    if (d2 <
                          width *
                          width *
                          0.25F) {
                        r.
                          setMax(
                            t);
                        state.
                          setIntersection(
                            primID,
                            0,
                            1);
                    }
                }
                else {
                    float dx =
                      points[v0 +
                               0] +
                      q *
                      vx -
                      px;
                    float dy =
                      points[v0 +
                               1] +
                      q *
                      vy -
                      py;
                    float dz =
                      points[v0 +
                               2] +
                      q *
                      vz -
                      pz;
                    float d2 =
                      dx *
                      dx +
                      dy *
                      dy +
                      dz *
                      dz;
                    float width =
                      (1 -
                         q) *
                      getWidth(
                        vn) +
                      q *
                      getWidth(
                        vn +
                          1);
                    if (d2 <
                          width *
                          width *
                          0.25F) {
                        r.
                          setMax(
                            t);
                        state.
                          setIntersection(
                            primID,
                            0,
                            q);
                    }
                }
        }
    }
    public void prepareShadingState(ShadingState state) {
        state.
          init(
            );
        Instance i =
          state.
          getInstance(
            );
        state.
          getRay(
            ).
          getPoint(
            state.
              getPoint(
                ));
        Ray r =
          state.
          getRay(
            );
        Shader s =
          i.
          getShader(
            0);
        state.
          setShader(
            s !=
              null
              ? s
              : this);
        int primID =
          state.
          getPrimitiveID(
            );
        int hair =
          primID /
          numSegments;
        int line =
          primID %
          numSegments;
        int vRoot =
          hair *
          3 *
          (numSegments +
             1);
        int v0 =
          vRoot +
          line *
          3;
        Vector3 v =
          getTangent(
            line,
            v0,
            state.
              getV(
                ));
        v =
          i.
            transformVectorObjectToWorld(
              v);
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromWV(
                v,
                new Vector3(
                  -r.
                     dx,
                  -r.
                     dy,
                  -r.
                     dz)));
        state.
          getBasis(
            ).
          swapVW(
            );
        state.
          getNormal(
            ).
          set(
            0,
            0,
            1);
        state.
          getBasis(
            ).
          transform(
            state.
              getNormal(
                ));
        state.
          getGeoNormal(
            ).
          set(
            state.
              getNormal(
                ));
        state.
          getUV(
            ).
          set(
            0,
            (line +
               state.
               getV(
                 )) /
              numSegments);
    }
    public boolean update(ParameterList pl, SunflowAPI api) {
        numSegments =
          pl.
            getInt(
              "segments",
              numSegments);
        if (numSegments <
              1) {
            UI.
              printError(
                Module.
                  HAIR,
                "Invalid number of segments: %d",
                numSegments);
            return false;
        }
        FloatParameter pointsP =
          pl.
          getPointArray(
            "points");
        if (pointsP !=
              null) {
            if (pointsP.
                  interp !=
                  InterpolationType.
                    VERTEX)
                UI.
                  printError(
                    Module.
                      HAIR,
                    ("Point interpolation type must be set to \"vertex\" - was \"%" +
                     "s\""),
                    pointsP.
                      interp.
                      name(
                        ).
                      toLowerCase(
                        ));
            else {
                points =
                  pointsP.
                    data;
            }
        }
        if (points ==
              null) {
            UI.
              printError(
                Module.
                  HAIR,
                "Unabled to update hair - vertices are missing");
            return false;
        }
        pl.
          setVertexCount(
            points.
              length /
              3);
        FloatParameter widthsP =
          pl.
          getFloatArray(
            "widths");
        if (widthsP !=
              null) {
            if (widthsP.
                  interp ==
                  InterpolationType.
                    NONE ||
                  widthsP.
                    interp ==
                  InterpolationType.
                    VERTEX)
                widths =
                  widthsP;
            else
                UI.
                  printWarning(
                    Module.
                      HAIR,
                    "Width interpolation type %s is not supported -- ignoring",
                    widthsP.
                      interp.
                      name(
                        ).
                      toLowerCase(
                        ));
        }
        return true;
    }
    public Color getRadiance(ShadingState state) {
        state.
          initLightSamples(
            );
        state.
          initCausticSamples(
            );
        Vector3 v =
          state.
          getRay(
            ).
          getDirection(
            );
        v.
          negate(
            );
        Vector3 h =
          new Vector3(
          );
        Vector3 t =
          state.
          getBasis(
            ).
          transform(
            new Vector3(
              0,
              1,
              0));
        Color diff =
          Color.
          black(
            );
        Color spec =
          Color.
          black(
            );
        for (LightSample ls
              :
              state) {
            Vector3 l =
              ls.
              getShadowRay(
                ).
              getDirection(
                );
            float dotTL =
              Vector3.
              dot(
                t,
                l);
            float sinTL =
              (float)
                Math.
                sqrt(
                  1 -
                    dotTL *
                    dotTL);
            diff.
              madd(
                sinTL,
                ls.
                  getDiffuseRadiance(
                    ));
            Vector3.
              add(
                v,
                l,
                h);
            h.
              normalize(
                );
            float dotTH =
              Vector3.
              dot(
                t,
                h);
            float sinTH =
              (float)
                Math.
                sqrt(
                  1 -
                    dotTH *
                    dotTH);
            float s =
              (float)
                Math.
                pow(
                  sinTH,
                  10.0F);
            spec.
              madd(
                s,
                ls.
                  getSpecularRadiance(
                    ));
        }
        Color c =
          Color.
          add(
            diff,
            spec,
            new Color(
              ));
        return Color.
          blend(
            c,
            state.
              traceTransparency(
                ),
            state.
              getV(
                ),
            new Color(
              ));
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        
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
       "r/0XOc0GoK4aAAA=");
}
