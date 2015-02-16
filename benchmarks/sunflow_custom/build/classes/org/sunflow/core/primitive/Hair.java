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
      1165455086000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
