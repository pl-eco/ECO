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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
