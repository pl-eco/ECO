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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
