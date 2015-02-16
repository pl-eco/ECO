package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.LightSample;
import org.sunflow.core.LightSource;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public class CornellBox implements PrimitiveList, Shader, LightSource {
    private float minX;
    private float minY;
    private float minZ;
    private float maxX;
    private float maxY;
    private float maxZ;
    private Color left;
    private Color right;
    private Color top;
    private Color bottom;
    private Color back;
    private Color radiance;
    private int samples;
    private float lxmin;
    private float lymin;
    private float lxmax;
    private float lymax;
    private float area;
    private BoundingBox lightBounds;
    public CornellBox() { super();
                          updateGeometry(new Point3(-1, -1, -1), new Point3(
                                           1,
                                           1,
                                           1));
                          left = new Color(0.8F, 0.25F, 0.25F);
                          right = new Color(0.25F, 0.25F, 0.8F);
                          Color gray = new Color(0.7F, 0.7F, 0.7F);
                          top = (bottom = (back = gray));
                          radiance = Color.WHITE;
                          samples = 16; }
    private void updateGeometry(Point3 c0, Point3 c1) { lightBounds = new BoundingBox(
                                                                        c0);
                                                        lightBounds.
                                                          include(
                                                            c1);
                                                        minX = lightBounds.
                                                                 getMinimum(
                                                                   ).
                                                                 x;
                                                        minY = lightBounds.
                                                                 getMinimum(
                                                                   ).
                                                                 y;
                                                        minZ = lightBounds.
                                                                 getMinimum(
                                                                   ).
                                                                 z;
                                                        maxX = lightBounds.
                                                                 getMaximum(
                                                                   ).
                                                                 x;
                                                        maxY = lightBounds.
                                                                 getMaximum(
                                                                   ).
                                                                 y;
                                                        maxZ = lightBounds.
                                                                 getMaximum(
                                                                   ).
                                                                 z;
                                                        lightBounds.
                                                          enlargeUlps(
                                                            );
                                                        lxmin = maxX /
                                                                  3 +
                                                                  2 *
                                                                  minX /
                                                                  3;
                                                        lxmax = minX /
                                                                  3 +
                                                                  2 *
                                                                  maxX /
                                                                  3;
                                                        lymin = maxY /
                                                                  3 +
                                                                  2 *
                                                                  minY /
                                                                  3;
                                                        lymax = minY /
                                                                  3 +
                                                                  2 *
                                                                  maxY /
                                                                  3;
                                                        area = (lxmax -
                                                                  lxmin) *
                                                                 (lymax -
                                                                    lymin);
    }
    public boolean update(ParameterList pl, SunflowAPI api) { Point3 corner0 =
                                                                pl.
                                                                getPoint(
                                                                  "corner0",
                                                                  null);
                                                              Point3 corner1 =
                                                                pl.
                                                                getPoint(
                                                                  "corner1",
                                                                  null);
                                                              if (corner0 !=
                                                                    null &&
                                                                    corner1 !=
                                                                    null) {
                                                                  updateGeometry(
                                                                    corner0,
                                                                    corner1);
                                                              }
                                                              left =
                                                                pl.
                                                                  getColor(
                                                                    "leftColor",
                                                                    left);
                                                              right =
                                                                pl.
                                                                  getColor(
                                                                    "rightColor",
                                                                    right);
                                                              top =
                                                                pl.
                                                                  getColor(
                                                                    "topColor",
                                                                    top);
                                                              bottom =
                                                                pl.
                                                                  getColor(
                                                                    "bottomColor",
                                                                    bottom);
                                                              back =
                                                                pl.
                                                                  getColor(
                                                                    "backColor",
                                                                    back);
                                                              radiance =
                                                                pl.
                                                                  getColor(
                                                                    "radiance",
                                                                    radiance);
                                                              samples =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    samples);
                                                              return true;
    }
    public void init(String name, SunflowAPI api) {
        api.
          geometry(
            name,
            this);
        api.
          shader(
            name +
            ".shader",
            this);
        api.
          parameter(
            "shaders",
            name +
            ".shader");
        api.
          instance(
            name +
            ".instance",
            name);
        api.
          light(
            name +
            ".light",
            this);
    }
    public BoundingBox getBounds() { return lightBounds;
    }
    public float getBound(int i) { switch (i) {
                                       case 0:
                                           return minX;
                                       case 1:
                                           return maxX;
                                       case 2:
                                           return minY;
                                       case 3:
                                           return maxY;
                                       case 4:
                                           return minZ;
                                       case 5:
                                           return maxZ;
                                       default:
                                           return 0;
                                   } }
    public boolean intersects(BoundingBox box) {
        BoundingBox b =
          new BoundingBox(
          );
        b.
          include(
            new Point3(
              minX,
              minY,
              minZ));
        b.
          include(
            new Point3(
              maxX,
              maxY,
              maxZ));
        if (b.
              intersects(
                box)) {
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMinimum(
                          ).
                        x,
                      box.
                        getMinimum(
                          ).
                        y,
                      box.
                        getMinimum(
                          ).
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMinimum(
                          ).
                        x,
                      box.
                        getMinimum(
                          ).
                        y,
                      box.
                        getMaximum(
                          ).
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMinimum(
                          ).
                        x,
                      box.
                        getMaximum(
                          ).
                        y,
                      box.
                        getMinimum(
                          ).
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMinimum(
                          ).
                        x,
                      box.
                        getMaximum(
                          ).
                        y,
                      box.
                        getMaximum(
                          ).
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMaximum(
                          ).
                        x,
                      box.
                        getMinimum(
                          ).
                        y,
                      box.
                        getMinimum(
                          ).
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMaximum(
                          ).
                        x,
                      box.
                        getMinimum(
                          ).
                        y,
                      box.
                        getMaximum(
                          ).
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMaximum(
                          ).
                        x,
                      box.
                        getMaximum(
                          ).
                        y,
                      box.
                        getMinimum(
                          ).
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMaximum(
                          ).
                        x,
                      box.
                        getMaximum(
                          ).
                        y,
                      box.
                        getMaximum(
                          ).
                        z)))
                return true;
        }
        return false;
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
        int n =
          state.
          getPrimitiveID(
            );
        switch (n) {
            case 0:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      1,
                      0,
                      0));
                break;
            case 1:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      -1,
                      0,
                      0));
                break;
            case 2:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      0,
                      1,
                      0));
                break;
            case 3:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      0,
                      -1,
                      0));
                break;
            case 4:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      0,
                      0,
                      1));
                break;
            case 5:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      0,
                      0,
                      -1));
                break;
            default:
                state.
                  getNormal(
                    ).
                  set(
                    new Vector3(
                      0,
                      0,
                      0));
                break;
        }
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
          setShader(
            this);
    }
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
        float intervalMin =
          Float.
            NEGATIVE_INFINITY;
        float intervalMax =
          Float.
            POSITIVE_INFINITY;
        float orgX =
          r.
            ox;
        float invDirX =
          1 /
          r.
            dx;
        float t1;
        float t2;
        t1 =
          (minX -
             orgX) *
            invDirX;
        t2 =
          (maxX -
             orgX) *
            invDirX;
        int sideIn =
          -1;
        int sideOut =
          -1;
        if (invDirX >
              0) {
            if (t1 >
                  intervalMin) {
                intervalMin =
                  t1;
                sideIn =
                  0;
            }
            if (t2 <
                  intervalMax) {
                intervalMax =
                  t2;
                sideOut =
                  1;
            }
        }
        else {
            if (t2 >
                  intervalMin) {
                intervalMin =
                  t2;
                sideIn =
                  1;
            }
            if (t1 <
                  intervalMax) {
                intervalMax =
                  t1;
                sideOut =
                  0;
            }
        }
        if (intervalMin >
              intervalMax)
            return;
        float orgY =
          r.
            oy;
        float invDirY =
          1 /
          r.
            dy;
        t1 =
          (minY -
             orgY) *
            invDirY;
        t2 =
          (maxY -
             orgY) *
            invDirY;
        if (invDirY >
              0) {
            if (t1 >
                  intervalMin) {
                intervalMin =
                  t1;
                sideIn =
                  2;
            }
            if (t2 <
                  intervalMax) {
                intervalMax =
                  t2;
                sideOut =
                  3;
            }
        }
        else {
            if (t2 >
                  intervalMin) {
                intervalMin =
                  t2;
                sideIn =
                  3;
            }
            if (t1 <
                  intervalMax) {
                intervalMax =
                  t1;
                sideOut =
                  2;
            }
        }
        if (intervalMin >
              intervalMax)
            return;
        float orgZ =
          r.
            oz;
        float invDirZ =
          1 /
          r.
            dz;
        t1 =
          (minZ -
             orgZ) *
            invDirZ;
        t2 =
          (maxZ -
             orgZ) *
            invDirZ;
        if (invDirZ >
              0) {
            if (t1 >
                  intervalMin) {
                intervalMin =
                  t1;
                sideIn =
                  4;
            }
            if (t2 <
                  intervalMax) {
                intervalMax =
                  t2;
                sideOut =
                  5;
            }
        }
        else {
            if (t2 >
                  intervalMin) {
                intervalMin =
                  t2;
                sideIn =
                  5;
            }
            if (t1 <
                  intervalMax) {
                intervalMax =
                  t1;
                sideOut =
                  4;
            }
        }
        if (intervalMin >
              intervalMax)
            return;
        assert sideIn !=
          -1;
        assert sideOut !=
          -1;
        if (sideIn !=
              2 &&
              r.
              isInside(
                intervalMin)) {
            r.
              setMax(
                intervalMin);
            state.
              setIntersection(
                sideIn,
                0,
                0);
        }
        else
            if (sideOut !=
                  2 &&
                  r.
                  isInside(
                    intervalMax)) {
                r.
                  setMax(
                    intervalMax);
                state.
                  setIntersection(
                    sideOut,
                    0,
                    0);
            }
    }
    public Color getRadiance(ShadingState state) {
        int side =
          state.
          getPrimitiveID(
            );
        Color kd =
          null;
        switch (side) {
            case 0:
                kd =
                  left;
                break;
            case 1:
                kd =
                  right;
                break;
            case 3:
                kd =
                  back;
                break;
            case 4:
                kd =
                  bottom;
                break;
            case 5:
                float lx =
                  state.
                    getPoint(
                      ).
                    x;
                float ly =
                  state.
                    getPoint(
                      ).
                    y;
                if (lx >=
                      lxmin &&
                      lx <
                      lxmax &&
                      ly >=
                      lymin &&
                      ly <
                      lymax &&
                      state.
                        getRay(
                          ).
                        dz >
                      0)
                    return state.
                      includeLights(
                        )
                      ? radiance
                      : Color.
                          BLACK;
                kd =
                  top;
                break;
            default:
                assert false;
        }
        state.
          faceforward(
            );
        state.
          initLightSamples(
            );
        state.
          initCausticSamples(
            );
        return state.
          diffuse(
            kd);
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        int side =
          state.
          getPrimitiveID(
            );
        Color kd =
          null;
        switch (side) {
            case 0:
                kd =
                  left;
                break;
            case 1:
                kd =
                  right;
                break;
            case 3:
                kd =
                  back;
                break;
            case 4:
                kd =
                  bottom;
                break;
            case 5:
                float lx =
                  state.
                    getPoint(
                      ).
                    x;
                float ly =
                  state.
                    getPoint(
                      ).
                    y;
                if (lx >=
                      lxmin &&
                      lx <
                      lxmax &&
                      ly >=
                      lymin &&
                      ly <
                      lymax &&
                      state.
                        getRay(
                          ).
                        dz >
                      0)
                    return;
                kd =
                  top;
                break;
            default:
                assert false;
        }
        if (Vector3.
              dot(
                state.
                  getNormal(
                    ),
                state.
                  getRay(
                    ).
                  getDirection(
                    )) >
              0) {
            state.
              getNormal(
                ).
              negate(
                );
            state.
              getGeoNormal(
                ).
              negate(
                );
        }
        state.
          storePhoton(
            state.
              getRay(
                ).
              getDirection(
                ),
            power,
            kd);
        double avg =
          kd.
          getAverage(
            );
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              avg) {
            power.
              mul(
                kd).
              mul(
                1 /
                  (float)
                    avg);
            OrthoNormalBasis onb =
              OrthoNormalBasis.
              makeFromW(
                state.
                  getNormal(
                    ));
            double u =
              2 *
              Math.
                PI *
              rnd /
              avg;
            double v =
              state.
              getRandom(
                0,
                1,
                1);
            float s =
              (float)
                Math.
                sqrt(
                  v);
            float s1 =
              (float)
                Math.
                sqrt(
                  1.0 -
                    v);
            Vector3 w =
              new Vector3(
              (float)
                Math.
                cos(
                  u) *
                s,
              (float)
                Math.
                sin(
                  u) *
                s,
              s1);
            w =
              onb.
                transform(
                  w,
                  new Vector3(
                    ));
            state.
              traceDiffusePhoton(
                new Ray(
                  state.
                    getPoint(
                      ),
                  w),
                power);
        }
    }
    public int getNumSamples() { return samples;
    }
    public void getSamples(ShadingState state) {
        if (lightBounds.
              contains(
                state.
                  getPoint(
                    )) &&
              state.
                getPoint(
                  ).
                z <
              maxZ) {
            int n =
              state.
              getDiffuseDepth(
                ) >
              0
              ? 1
              : samples;
            float a =
              area /
              n;
            for (int i =
                   0;
                 i <
                   n;
                 i++) {
                double randX =
                  state.
                  getRandom(
                    i,
                    0);
                double randY =
                  state.
                  getRandom(
                    i,
                    1);
                Point3 p =
                  new Point3(
                  );
                p.
                  x =
                  (float)
                    (lxmin *
                       (1 -
                          randX) +
                       lxmax *
                       randX);
                p.
                  y =
                  (float)
                    (lymin *
                       (1 -
                          randY) +
                       lymax *
                       randY);
                p.
                  z =
                  maxZ -
                    0.001F;
                LightSample dest =
                  new LightSample(
                  );
                dest.
                  setShadowRay(
                    new Ray(
                      state.
                        getPoint(
                          ),
                      p));
                float cosNx =
                  dest.
                  dot(
                    state.
                      getNormal(
                        ));
                if (cosNx <=
                      0)
                    return;
                float cosNy =
                  dest.
                    getShadowRay(
                      ).
                    dz;
                if (cosNy >
                      0) {
                    float r =
                      dest.
                      getShadowRay(
                        ).
                      getMax(
                        );
                    float g =
                      cosNy /
                      (r *
                         r);
                    float scale =
                      g *
                      a;
                    dest.
                      setRadiance(
                        radiance,
                        radiance);
                    dest.
                      getDiffuseRadiance(
                        ).
                      mul(
                        scale);
                    dest.
                      getSpecularRadiance(
                        ).
                      mul(
                        scale);
                    dest.
                      traceShadow(
                        state);
                    state.
                      addSample(
                        dest);
                }
            }
        }
    }
    public void getPhoton(double randX1, double randY1,
                          double randX2,
                          double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power) { p.
                                           x =
                                           (float)
                                             (lxmin *
                                                (1 -
                                                   randX2) +
                                                lxmax *
                                                randX2);
                                         p.
                                           y =
                                           (float)
                                             (lymin *
                                                (1 -
                                                   randY2) +
                                                lymax *
                                                randY2);
                                         p.
                                           z =
                                           maxZ -
                                             0.001F;
                                         double u =
                                           2 *
                                           Math.
                                             PI *
                                           randX1;
                                         double s =
                                           Math.
                                           sqrt(
                                             randY1);
                                         dir.
                                           set(
                                             (float)
                                               (Math.
                                                  cos(
                                                    u) *
                                                  s),
                                             (float)
                                               (Math.
                                                  sin(
                                                    u) *
                                                  s),
                                             (float)
                                               -Math.
                                               sqrt(
                                                 1.0F -
                                                   randY1));
                                         Color.
                                           mul(
                                             (float)
                                               Math.
                                                 PI *
                                               area,
                                             radiance,
                                             power);
    }
    public float getPower() { return radiance.
                                copy(
                                  ).
                                mul(
                                  (float)
                                    Math.
                                      PI *
                                    area).
                                getLuminance(
                                  ); }
    public int getNumPrimitives() { return 1;
    }
    public float getPrimitiveBound(int primID,
                                   int i) {
        switch (i) {
            case 0:
                return minX;
            case 1:
                return maxX;
            case 2:
                return minY;
            case 3:
                return maxY;
            case 4:
                return minZ;
            case 5:
                return maxZ;
            default:
                return 0;
        }
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) {
        BoundingBox bounds =
          new BoundingBox(
          minX,
          minY,
          minZ);
        bounds.
          include(
            maxX,
            maxY,
            maxZ);
        if (o2w ==
              null)
            return bounds;
        return o2w.
          transform(
            bounds);
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169182092000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "7/Hbq/8BxaWsk6giAAA=");
}
