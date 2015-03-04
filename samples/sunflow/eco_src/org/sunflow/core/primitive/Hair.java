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
                                                                  this.
                                                                  getWidth(
                                                                    vn),
                                                                points[v1 +
                                                                         axis] -
                                                                  0.5F *
                                                                  this.
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
                                                                  this.
                                                                  getWidth(
                                                                    vn),
                                                                points[v1 +
                                                                         axis] +
                                                                  0.5F *
                                                                  this.
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
                                                           this.
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
          normalize();
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
              normalize();
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
              normalize();
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
                  this.
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
                      this.
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
                      this.
                      getWidth(
                        vn) +
                      q *
                      this.
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
          init();
        Instance i =
          state.
          getInstance();
        state.
          getRay().
          getPoint(
            state.
              getPoint());
        Ray r =
          state.
          getRay();
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
          getPrimitiveID();
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
          this.
          getTangent(
            line,
            v0,
            state.
              getV());
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
          getBasis().
          swapVW();
        state.
          getNormal().
          set(
            0,
            0,
            1);
        state.
          getBasis().
          transform(
            state.
              getNormal());
        state.
          getGeoNormal().
          set(
            state.
              getNormal());
        state.
          getUV().
          set(
            0,
            (line +
               state.
               getV()) /
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
                      name().
                      toLowerCase());
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
                      name().
                      toLowerCase());
        }
        return true;
    }
    public Color getRadiance(ShadingState state) {
        state.
          initLightSamples();
        state.
          initCausticSamples();
        Vector3 v =
          state.
          getRay().
          getDirection();
        v.
          negate();
        Vector3 h =
          new Vector3(
          );
        Vector3 t =
          state.
          getBasis().
          transform(
            new Vector3(
              0,
              1,
              0));
        Color diff =
          Color.
          black();
        Color spec =
          Color.
          black();
        for (LightSample ls
              :
              state) {
            Vector3 l =
              ls.
              getShadowRay().
              getDirection();
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
                  getDiffuseRadiance());
            Vector3.
              add(
                v,
                l,
                h);
            h.
              normalize();
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
                  getSpecularRadiance());
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
              traceTransparency(),
            state.
              getV(),
            new Color(
              ));
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1165455086000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVZDXAU1R1/d5fvRBIChoBITAxS+biTCFaIUwmQyMERYgJB" +
       "gza+7L27W9jbXXff\nhSMyVHQGqE5baW2nnSrSji1ItTqjLXZGKX61Ku2MMq" +
       "3O2IHqOKPttLZ1OqP0w07//7e7t3t7HwTS\nZmbfbva9//+93//j9/5v7/GP" +
       "SKVpkLmSGea7dGaG1wwNUMNk8TUKNc3N8GpUeqWyduDIBlULkkCM\nBOU4J4" +
       "0xyYzEKacROR6Jru3OGmSRrim7korGwyzLw9uV5ba+9bHlBQq3HjrevPfRir" +
       "YgqYyRRqqq\nGqdc1tRehaVNTppi2+k4jWS4rERissm7Y+QSpmbSazTV5FTl" +
       "5p1kDwnFSJUuoU5O2mPO5BGYPKJT\ng6YjYvrIgJgWNMwwGKeyyuI9uelAcn" +
       "G+JCzblhssHA1KarBzGOCIFQDqK3KoLbQFUPXQ0eHrdh9+\nLEQaR0ijrA6h" +
       "MgmQcJhvhDSkWXqMGWZPPM7iI2S6ylh8iBkyVeQJMesIaTblpEp5xmDmIDM1" +
       "ZRwH\nNpsZnRliTudljDRIiMnISFwzcjZKyEyJO/9VJhSaBNgtLmwLbh++B4" +
       "B1MizMSFCJOSIVO2QVPN7m\nl8hh7NwAA0C0Os14SstNVaFSeEGaLV8qVE1G" +
       "hrghq0kYWqllYBZO5pRUirbWqbSDJtkoJ63+cQNW\nF4yqFYZAEU4u9Q8Tms" +
       "BLc3xe8vhnUcsnB44+dGKViO2KOJMUXH8dCM3zCQ2yBDOYKjFL8Fwm/GD0\n" +
       "1szcICEw+FLfYGtMz/zjW2J/+HmbNeayImM2jW1nEh+V+g+2Dd51k0ZCuIwa" +
       "XTNldH4ecpEOA3ZP\nd1aHrG3JacTOsNN5cvAXt959jP0pSOqipErSlEwa4m" +
       "i6pKV1WWHGTUxlBuUsHiW1TI2vEf1RUg3P\nMQh56+2mRMJkPEoqFPGqShP/" +
       "g4kSoAJNVAvPsprQnGed8pR4zuqEkGq4SBdcLcT6E3dOusIRM6Mm\nFG1nxD" +
       "SkiGYkc/9LmsEiuiGnAcM4i6yjshHG2NE56Y+ktDSLUImqsqpFkjJkq6Qtib" +
       "NxvF+wxiyu\ns3lnIIDE509gBWJ/nabEmTEqHXn/9d29G758wAoODGgbIeQD" +
       "TBS2JwrjROHcRGGciAQCQv9MnNBy\nDxh3B6QpEFrD1UO3r7/jQEcI4kLfWQ" +
       "GWwaEdgMVeRa+krXFzOSpoT4KAav3+tv3hc0dutAIqUppy\ni0rXv/HEqcN/" +
       "b1gYJMHifIjogJHrUM0AkmiO5zr9GVRM/1/u2/j0W6fOfM7NJU46C1K8UBJT" +
       "tMPv\nB0OTWBxIz1X/6OzG0FYyfDBIKiDvgevE+oFG5vnnyEvVbof2EEt1jN" +
       "QnNCNNFexyuKqOpwxtp/tG\nBEiTeJ4BzqnH2G2E6zI7mMUdey/VsW2xAgq9" +
       "7UMhaPXcvVXXvP1c/SvCLA4DN3r2uCHGrXye7gbL\nZoMxeH/m2wPf+OZH+7" +
       "eJSLFDhcPGlxlTZCkLIle5IpDICpAJOrJzi5rW4nJCpmMKw4j7d+P8pT/5\n" +
       "81ebLNco8Mbx7OLzK3Dfz15N7j71xU/nCTUBCTcSF4Y7zEIzw9XcYxh0F64j" +
       "u/f05d/5JX0YeA64\nxZQnmKALIpARYceIsPtC0YZ9fUux6QDdi0uEfpFte1" +
       "TafSzZkbnztZ+JVddT7/7vdcNGqndbnsfm\nSrTuLH/2rqNmCsYtO9l/W5Ny" +
       "8l+gcQQ0SrBdmpsM4IxsnhPt0ZXV77zwUssdb4ZIsI/UKRqN91ER\n/6QWAo" +
       "+ZKaCbrH7jKhFbTTtrsBWQiTDCHNsAWc9/IVjc1aXTvw83fTdzRscWH429vu" +
       "lhYYCSiV9k\nz/PpmTix5dC5X/OzQo+bgSjdni3kUiiUXNnr3xqfXvXUI+kg" +
       "qR4hTZJdyg1TJYNxPgKVh+nUd1Du\n5fXnVxHWltmdY5i5/uz3TOvPfZfD4R" +
       "lH43ODL90b0Npz4Zplp/ssf7oHiHi4UYh0inZBLjmrYSMY\np1jekXooWodY" +
       "Ms2gZgWHtXqLbGe3QNZ5f1/H869ueWS/Retl/JonNSp96ffv7gh97YUxS87v" +
       "PN/g\ng/Me/eDp9wdnWhRgVXVXFhRWXhmrshPWadQxHdrLzSBGv7yo/fE9g2" +
       "ftFTXn1ye9YI4Pd73EFtzw\nlfeKbKkhqD0tIsW2C5tVVswvL5kbK/O9hsTc" +
       "anuttYTXBrDpQRLVZMsvMz1+EUyFcB57YO2MwRXb\n7hWbZS3U49Tsd4MHTk" +
       "H4FACQ80u7K6dsVFpw+/G/vXCCLRCMUSObEKQ9RrJIbeqR+ZgeYxvfThwS\n" +
       "G17FGDWtcPUX9YU1e14pLvw3Tbcs2Wvf13NMDI3yHM0G7DpEWFV3jLWtMMSD" +
       "KCqrVBTKV0OUVylM\nTVrFXxSbYXsO1Bq0RMT/s7i9JWAaQw2uqQx3F6fPqp" +
       "RkLZw7/0BntmB9Brk8r07aKKC6PHPfYz86\nzt9ctNKKwIWlneMXXLjy8ETb" +
       "yifvv4jqqM3nQ7/q6eOX3RxKya8GxcHIoq2CA1W+UHc+WdXBejKG\nujmPsq" +
       "7QxW0Ym6vK7JpKmT4Vm+3gUgn9YbkPbNxWvCroTetc7OMTz8565oYjh86ilf" +
       "UsFPZ1IkO6\nulasuAbEmyGj8PNAWI7be8ja031jxxLqsbiwQZ0I4B4UsQHW" +
       "ijeeDAtpuonnH8+HBltT5ybdxJLx\nEs8k0bW7n1rfUPO9+/cJ/XZ61nrOUv" +
       "b/1ePU6PeyTr1Y+NLrly9btpSTW/63J42VXUuvXdx13ZJr\nu2CizeuiQ2GL" +
       "dHB2I0dBe+AkV2gwhGlTGWkWwTzNzR6kUm8nAKkY7O1Z6+PPmy+QPzvgmm2r" +
       "nV2C\nP/c6/LlTjvOU6aTvkoIDkcgRBtSEAdPZh2yTe+Vb5z1l1pl1w3tOPp" +
       "vMK5zScQFO6QybVTBsKEWx\nVkMuKfVlQOxm+2/5uGEfffn2oJ0sGzhUbJq+" +
       "RGHjTPGsqF48J/IPDO325TwXHhjOl7cPlun7FjYH\nOWlKMt6fSeeAi8FR17" +
       "JfP18EONVlMQjL4ZpvQ5g/aQg25+f2g6gYeqgMmMPYfJeT6QAmh2S1llFF\n" +
       "Fm910Tw0FTT4OWKZjWbZpNF490UIplZvMKUpT4U3Um7I2WVCw9EyIB/H5gec" +
       "TAOQWzVDiQuAufyZ\nW6BY9MtqcrWWdS3ww4u0QIMThqtsC6wqaYGe4hZwXf" +
       "nTMiifxeZpTmoQJRKEz4PPTGX9K+DaYK9/\nw6TXH7JOTG48iiWJ8SfLIHkR" +
       "m+dhZwMkm4FzmcpLB8EwwwPdtS7OE1OJ1H64UjbO1KQj1caZq6cK\naG+Q7n" +
       "LZAUZ0FIyIYiVpWrs9fqlmYrZflbHSaWxegxOa7IjmU5Gn4KwY1+S4a6HXp2" +
       "Khz8N11LbQ\n0YvN5cuLbgyQcS70M2Wgv4fNO1DU6gbTqcG8wtj1Gxfr76aC" +
       "tRuuF22sL04aa/C8e6V3e3aGtXiH\nDVn3ngEr7f9YxhZ/xeYDKAoyOpQxBa" +
       "6vHtM0hVHVtciHU7HINXC9a1vk3f+j9/9ZBvF/sPkUyjqg\nh0GQw6K9aMUh" +
       "p2mSYRVt/ygj4J+bCvwvwPWZDf+ziw2I8vDPhwSnCdSUNk9gGjaVnFxiSpRD" +
       "kA2k\nNG4V4W5aBKqmYgWYMWAbwbpPygreNbaU6WvFZgYkN7h3Nd0BdnF57U" +
       "LqT4Fz5mRxwmm6Ag8PeARr\nLfhp0fo5TIq9c9dtn8R++w/rs4Dzk1U9nHUS" +
       "GUXxfuHyPFcBSSVkYZd663uXODwG2jmZU/qnDKh3\ndS+dB6wjZ6ATCk+/FC" +
       "wdb95hCyA9PMOAB+wn76CFHM56RhIfF+mOaZvco471vS+bZye0z5V5R3vx\n" +
       "m69z/M5Yv/qOSrc8se2K7P2bHxBnejji0okJVFMH50DrVwChFY/w7SW1Obre" +
       "IE89Ofzcj1c4hwHx\nlXim/em/IFi7rN7SfseOO/X/At88K7J/HwAA");
}
