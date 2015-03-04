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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "B1+af+nuc1Pv8dur/wGg9i8iuCIAAA==");
}
