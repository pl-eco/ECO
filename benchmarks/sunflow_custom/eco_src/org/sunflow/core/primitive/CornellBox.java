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
                          this.updateGeometry(new Point3(-1, -1, -1), new Point3(
                                                1,
                                                1,
                                                1));
                          left = new Color(0.8F, 0.25F, 0.25F);
                          right = new Color(0.25F, 0.25F, 0.8F);
                          Color gray = new Color(0.7F, 0.7F, 0.7F);
                          top = (bottom = (back = gray));
                          radiance = Color.WHITE;
                          samples = 16; }
    private void updateGeometry(Point3 c0, Point3 c1) { lightBounds =
                                                          new BoundingBox(
                                                            c0);
                                                        lightBounds.
                                                          include(
                                                            c1);
                                                        minX = lightBounds.
                                                                 getMinimum().
                                                                 x;
                                                        minY = lightBounds.
                                                                 getMinimum().
                                                                 y;
                                                        minZ = lightBounds.
                                                                 getMinimum().
                                                                 z;
                                                        maxX = lightBounds.
                                                                 getMaximum().
                                                                 x;
                                                        maxY = lightBounds.
                                                                 getMaximum().
                                                                 y;
                                                        maxZ = lightBounds.
                                                                 getMaximum().
                                                                 z;
                                                        lightBounds.
                                                          enlargeUlps();
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
                                                                  this.
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
                        getMinimum().
                        x,
                      box.
                        getMinimum().
                        y,
                      box.
                        getMinimum().
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMinimum().
                        x,
                      box.
                        getMinimum().
                        y,
                      box.
                        getMaximum().
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMinimum().
                        x,
                      box.
                        getMaximum().
                        y,
                      box.
                        getMinimum().
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMinimum().
                        x,
                      box.
                        getMaximum().
                        y,
                      box.
                        getMaximum().
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMaximum().
                        x,
                      box.
                        getMinimum().
                        y,
                      box.
                        getMinimum().
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMaximum().
                        x,
                      box.
                        getMinimum().
                        y,
                      box.
                        getMaximum().
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMaximum().
                        x,
                      box.
                        getMaximum().
                        y,
                      box.
                        getMinimum().
                        z)))
                return true;
            if (!b.
                  contains(
                    new Point3(
                      box.
                        getMaximum().
                        x,
                      box.
                        getMaximum().
                        y,
                      box.
                        getMaximum().
                        z)))
                return true;
        }
        return false;
    }
    public void prepareShadingState(ShadingState state) {
        state.
          init();
        state.
          getRay().
          getPoint(
            state.
              getPoint());
        int n =
          state.
          getPrimitiveID();
        switch (n) {
            case 0:
                state.
                  getNormal().
                  set(
                    new Vector3(
                      1,
                      0,
                      0));
                break;
            case 1:
                state.
                  getNormal().
                  set(
                    new Vector3(
                      -1,
                      0,
                      0));
                break;
            case 2:
                state.
                  getNormal().
                  set(
                    new Vector3(
                      0,
                      1,
                      0));
                break;
            case 3:
                state.
                  getNormal().
                  set(
                    new Vector3(
                      0,
                      -1,
                      0));
                break;
            case 4:
                state.
                  getNormal().
                  set(
                    new Vector3(
                      0,
                      0,
                      1));
                break;
            case 5:
                state.
                  getNormal().
                  set(
                    new Vector3(
                      0,
                      0,
                      -1));
                break;
            default:
                state.
                  getNormal().
                  set(
                    new Vector3(
                      0,
                      0,
                      0));
                break;
        }
        state.
          getGeoNormal().
          set(
            state.
              getNormal());
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                state.
                  getNormal()));
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
          getPrimitiveID();
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
                    getPoint().
                    x;
                float ly =
                  state.
                    getPoint().
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
                        getRay().
                        dz >
                      0)
                    return state.
                      includeLights()
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
          faceforward();
        state.
          initLightSamples();
        state.
          initCausticSamples();
        return state.
          diffuse(
            kd);
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        int side =
          state.
          getPrimitiveID();
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
                    getPoint().
                    x;
                float ly =
                  state.
                    getPoint().
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
                        getRay().
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
                  getNormal(),
                state.
                  getRay().
                  getDirection()) >
              0) {
            state.
              getNormal().
              negate();
            state.
              getGeoNormal().
              negate();
        }
        state.
          storePhoton(
            state.
              getRay().
              getDirection(),
            power,
            kd);
        double avg =
          kd.
          getAverage();
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
                  getNormal());
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
                    getPoint(),
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
                  getPoint()) &&
              state.
                getPoint().
                z <
              maxZ) {
            int n =
              state.
              getDiffuseDepth() >
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
                        getPoint(),
                      p));
                float cosNx =
                  dest.
                  dot(
                    state.
                      getNormal());
                if (cosNx <=
                      0)
                    return;
                float cosNy =
                  dest.
                    getShadowRay().
                    dz;
                if (cosNy >
                      0) {
                    float r =
                      dest.
                      getShadowRay().
                      getMax();
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
                      getDiffuseRadiance().
                      mul(
                        scale);
                    dest.
                      getSpecularRadiance().
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
                                copy().
                                mul(
                                  (float)
                                    Math.
                                      PI *
                                    area).
                                getLuminance();
    }
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169182092000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVaDXAU1R1/d5fkIInNB5AAAoGAXwTvlIofxFYhBgwcEBMI" +
       "GLRhs/suWdjbt+6+\nSy6RQR1HoVqrVtqpLQqttkHU6ow4Wmv91loZZ7Qd7Y" +
       "yttB07tjPVdjqdWlrbaf/vvd3bvb29I5eb\n3Mx7u7fv4////b/e/73dxz5F" +
       "lZaJFshWjI4Z2Ip19HZLpoWVDk2yrK3waEB+vXJm98RGnYRRKIHC\nqkJRXU" +
       "K24opEpbiqxLuuas+YqM0g2tiQRmgMZ2hst7bKnm9DYlXehNsffKbxlocrWs" +
       "KoMoHqJF0n\nVKIq0Ts1nLIoqk/slkakeJqqWjyhWrQ9gc7AejrVQXSLSjq1" +
       "bkD7UCSBqgyZzUnRkoRDPA7E44Zk\nSqk4Jx/v5mRhhlkmppKqY2VNlhyMXJ" +
       "E7Eti2x/Xk94ZJZrDGPoDDOQDUi7OoBdo8qEbkaN/Fe488\nEkF1/ahO1XvZ" +
       "ZDIgoUCvH9WmcGoQm9YaRcFKP2rQMVZ6salKmjrOqfajRksd0iWaNrHVgy2i" +
       "jbCO\njVbawCan6TxMoFqZYTLTMiVmVkZJFWuK868yqUlDALvJhS3grmPPAW" +
       "C1CoyZSUnGzpCKPaoOGm/x\nj8hiXLYROsDQaArTYZIlVaFL8AA1Cl1qkj4U" +
       "76Wmqg9B10qSBioUzS84KZO1Icl7pCE8QNFcf79u\n0QS9ZnJBsCEUzfF34z" +
       "OBlub7tOTRT1vTZweOHnrhSm7bFQqWNcZ/NQxa5BvUg5PYxLqMxcBT6djB\n" +
       "rmvTC8IIQec5vs6iz5qzntmW+NOLLaLPmQF9tgzuxjIdkDff29Jz43qCIoyN" +
       "GQaxVKb8HOTcHbrt\nlvaMAV7blJ2RNcacxpd63rj25mP4z2FU3YWqZKKlU2" +
       "BHDTJJGaqGzfVYx6ZEsdKFZmJd6eDtXSgK\n9wkwefF0SzJpYdqFKjT+qIrw" +
       "/yCiJEzBRDQT7lU9SZx7Q6LD/D5jIISiUFAnlFlI/PiVoktjcSut\nJzUyGr" +
       "dMOU7Moex/mZg4bphqCjCM4HgHMXWsaWtJJsYsyKBoa3yYpHBckiVd1Ul8SA" +
       "Wflcn5Ch5h\n1ynOm2E8N46GQiwI+p1ZAz+4mmgKNgfkiY/e2tu58asHhKEw" +
       "47bRUnQWkIvZ5GKMXCxLLuaSQ6EQ\npzKbkRUKA3HvAceFEFd7Xu/1G3YdaI" +
       "2ApRijFSAr1rUVcNm8dMqkw/XuLh4IZTCxud/fuT92auIK\nYWLxwkE4cHTN" +
       "O4+fOPL32uVhFA6OkAwjxOhqNk03C6vZyLfM71NB8//ljk1PvX/iw3Nd76Jo" +
       "WZ7T\n549kTtvq14ZJZKxAGHSnf3heXWQ76rs3jCogEkD04/xDYFnkp5HjvO" +
       "1OIGRYoglUkyRmStJYkxO9\nqumwSUbdJ9xM6vk9s+QaZs1zoCy0zZtfWesc" +
       "g9VNwqyYtn0oeKA9dWvVBb96vuZ1LhYnJtd5Vr1e\nTIWHN7jGstXEGJ5/+O" +
       "3u+7756f6d3FJsU6GwFKYHNVXOwJCz3SHg2hqEF6bIZdv0FFHUpCoNaphZ\n" +
       "3H/qzrrw6U++Xi9Uo8ETR7MrTj+B+3zeWnTzia/8cxGfJiSzpcWF4XYTaGa5" +
       "M68xTWmM8ZG55RcL\n7/+Z9ABEPog2ljqOeQBBHBnicoxzuS/ndczXdiGrWm" +
       "HuFQVMP2AhH5D3HhtqTd/w8x9zrmskb0bg\nVcMmyWgXmmfVUibdZr/3Xi1Z" +
       "w9Dvopc2X1evvfQ5zNgPM8qwgFpbTIgcmRwl2r0rox+8/GrTrncj\nKLwOVW" +
       "tEUtZJ3P7RTDA8bA1D0MkYV1zJbat+dAarOWTEhTDfFkDG828WMHdeYfdfx9" +
       "IA13MGBlcc\nTby15QEugIKOH7AK+uYZf2Hbg6fepif5PK4HstFLMvkRFVIn" +
       "d+yl7480VD15OBVG0X5UL9vJXZ+k\npZmd90MuYjkZHySAOe25eYVYRNuzEW" +
       "aB3/s9ZP2+70ZyuGe92X2tz91rmbSXQpltu/tsv7uHEL+5\ngg9Zxutzss4Z" +
       "heVgRGIJH6pIqfoO0NRcb77tLBYs3Hx0e+tP39x2eL+I50UUmjNqQL7pt7/b" +
       "E7n7\n5UExzq81X+d7Fz388VMf9cwWvi8SvKV5OZZ3jEjyuFjqDOYHS4pR4L" +
       "1fa1vy2L6ekzZHjbmpSiek\n838cexWfc/ldvw9YUUFFRKIihrJ6JauuFOa+" +
       "qqBbrC5dYd2sWiP0ci277/IRvWYaiG7zEO0PIto3\nBaJzbKJzChDtzxKVMj" +
       "uCiO6cBqIDHqKB4t01DUQVD9FA8eISiV4Ipckm2lSAqOoQ1XBSbFCbKWr2\n" +
       "JoZqCnYsbEUlpo+f3dPAj2HzU2mqQ8OlMnTDNDA0ajMUocQokZ3MNLCzz2an" +
       "apBQSlIlcnTTNHB0\nm2NBg7C5LZGf20vk50wozTY/zQX4+ZrNzwxTUlS2dJ" +
       "bI010l8jTXLs59EE/32TxFLSllaJBisf+d\nhqC0AYxL1f2rxsES2Tgfyjyb" +
       "jXkF2Piu41xaBkJ4UIg5NA1Uj2SpjhWg+r1poPoDD1axW/ZT/eE0\nUD3mwR" +
       "pM9dESqTIzn29TnV+A6hOOC0omloKIPlki0WW2ryHnGkD0uE20RmORei1J64" +
       "rluNoCr6ul\nJDoc4+2qPrSWZHy8PV2EN973bM9uIeJQWJR3dpHN5NjuLNDn" +
       "ebfeYQl2N4GM8vYEQ9NL0qaMWb64\nsNARHM8V9+/4W+3t0mvXh+1d3UYKGy" +
       "FinK/hEax52GY76oU5hyKb+KGju6m445FHn6Hvtq0WWefy\nwvmzf+Dy1UfG" +
       "W1Y/cecUjkJafNj8UzeMnHlNZFh9M8zPRcUeJe88NXdQe+7OpBr4SZv61pz9" +
       "yeJc\nW1sFpc22tTa/rXFb4Ybm20uHuVzDgXrmBtdNIKZ+8XTtnMDbRfbpv2" +
       "TVWxR9IW0osBVaj0kKU3PM\nH8ArRoiquDZ94nT+5uyE+Z83cs9n2qF02ALp" +
       "KCiQs4sLJMA/mO4xxabXP5q83XrFdU13FydzsohY\n/sCqX0MaIsTiF0d0kB" +
       "ANS7orkd+UIxGWhmy3JbJ9qhKp56cZbOMdEwf6k5HBX4vI4B+s+oSy42SV\n" +
       "r97vuXA/LQduK5RdNtxdk4brZe2/Rdr+x6p/Q6AawqVEbRfb5+VgWwxFsbEp" +
       "k8YWEkcS7O+3WK/Q\nzMIAQzWsqoIE0AHIV8Qs/6FoOfwzYqM2/6Ol8j8pQX" +
       "MMs4rga2ZVPbXfeVlYFt78sYuwoRyEl0A5\naCM8OFWECwPXXQDI3iNiDqOl" +
       "CMSlrFpA0SzDxAYkNd7BuZ4WWlgO1s1QjttYj08aayQ3FZmdh7VH\nGuOm6v" +
       "RozevR5agOVmhXJG1FRMIOjEPnUtSY1Xo24fFJ5LxyJHIBKLFeCERcp0n7lx" +
       "WB+iVWXQyZ\nJThwzyQ3cS78S8qB/2VAsdKGv3LS8H0rTXH4p0PCRbC+iHg2" +
       "seoqis6wZImCLXQPE0p0nxF0liOF\nRYB+0JbC4KSl4OVxW5G27azqAf5BvZ" +
       "vTqV6xJXYdhvPfW2aQDiVt/pPTaMQDRVDKrLoOojSg9ED0\nqOj6ciCOAad2" +
       "lA5NPkpH+YxRf7pWpZD0IH89HtLcKtBQA3LruXntfZi9FApOvv2WvqeIDEdY" +
       "NSxS\nlUArV8sRIWxsQ4dtER6ekpXvK9J2M6tuFFlINxkV75Y8WcjeclhfAi" +
       "w/ZLP+0JRYP1Ck7Q5W3QYZ\ns3DQ7Drj99HTnt4VgwDbvtCEDWFi0hDsSMsZ" +
       "4RVn+BtFwBxk1d0UNTA9OEiC0sJ7ykEDa0XoORvN\nc1ONOPmOtEmCnUrmIg" +
       "7kUBGQ7IgtdD/sUgHkdmJqylQS+9B3yo25r9gSeGVKJnmsSNtjrJqAhJDl\n" +
       "9NIe4Ny1SgflZI6EOM6jk8WZgfDtfgjDvgSYm/fdnPjWS058cON1nyXe+xf/" +
       "pCP7PVZNAs1IpjXN\n+7LWc18F6W1S5dKpEa9uDY72aYrmF/42B+Kh4XXJ0H" +
       "Ex6lnwWP8o2J2yi7fbTyCx8nRjh9Liztvp\nBYoi0Indvph97eHZQYtX15kc" +
       "aTH5LM05uOIfNDqHS2nxSeOAvOPxnYszd269h59YVcqaND7OpqlO\noKj4oI" +
       "XPyg6olhSczZnrHfTkE33P/+gy5wCOf/Aw23PEmGOyK0VrEe2bqCX4K5LOlE" +
       "H5dx/jzzYf\nv3ziwZNh/h3L/wF4ZG38hyoAAA==");
}
