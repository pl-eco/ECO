package org.sunflow.core;
import java.util.Iterator;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.image.Color;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point2;
import org.sunflow.math.Point3;
import org.sunflow.math.QMC;
import org.sunflow.math.Vector3;
public final class ShadingState implements Iterable<LightSample> {
    private IntersectionState istate;
    private LightServer server;
    private float rx;
    private float ry;
    private Color result;
    private Point3 p;
    private Vector3 n;
    private Point2 tex;
    private Vector3 ng;
    private OrthoNormalBasis basis;
    private float cosND;
    private boolean behind;
    private float hitU;
    private float hitV;
    private Instance instance;
    private int primitiveID;
    private Ray r;
    private int d;
    private int i;
    private double qmcD0I;
    private double qmcD1I;
    private Shader shader;
    private Modifier modifier;
    private int diffuseDepth;
    private int reflectionDepth;
    private int refractionDepth;
    private boolean includeLights;
    private boolean includeSpecular;
    private LightSample lightSample;
    private PhotonStore map;
    static ShadingState createPhotonState(Ray r, IntersectionState istate,
                                          int i,
                                          PhotonStore map,
                                          LightServer server) { ShadingState s =
                                                                  new ShadingState(
                                                                  null,
                                                                  istate,
                                                                  r,
                                                                  i,
                                                                  4);
                                                                s.
                                                                  server =
                                                                  server;
                                                                s.
                                                                  map =
                                                                  map;
                                                                return s;
    }
    static ShadingState createState(IntersectionState istate, float rx,
                                    float ry,
                                    Ray r,
                                    int i,
                                    LightServer server) { ShadingState s =
                                                            new ShadingState(
                                                            null,
                                                            istate,
                                                            r,
                                                            i,
                                                            4);
                                                          s.server =
                                                            server;
                                                          s.rx = rx;
                                                          s.ry = ry;
                                                          return s;
    }
    static ShadingState createDiffuseBounceState(ShadingState previous,
                                                 Ray r,
                                                 int i) { ShadingState s =
                                                            new ShadingState(
                                                            previous,
                                                            previous.
                                                              istate,
                                                            r,
                                                            i,
                                                            2);
                                                          s.diffuseDepth++;
                                                          return s;
    }
    static ShadingState createGlossyBounceState(ShadingState previous,
                                                Ray r,
                                                int i) { ShadingState s =
                                                           new ShadingState(
                                                           previous,
                                                           previous.
                                                             istate,
                                                           r,
                                                           i,
                                                           2);
                                                         s.includeLights =
                                                           false;
                                                         s.includeSpecular =
                                                           false;
                                                         s.reflectionDepth++;
                                                         return s;
    }
    static ShadingState createReflectionBounceState(ShadingState previous,
                                                    Ray r,
                                                    int i) { ShadingState s =
                                                               new ShadingState(
                                                               previous,
                                                               previous.
                                                                 istate,
                                                               r,
                                                               i,
                                                               2);
                                                             s.reflectionDepth++;
                                                             return s;
    }
    static ShadingState createRefractionBounceState(ShadingState previous,
                                                    Ray r,
                                                    int i) { ShadingState s =
                                                               new ShadingState(
                                                               previous,
                                                               previous.
                                                                 istate,
                                                               r,
                                                               i,
                                                               2);
                                                             s.refractionDepth++;
                                                             return s;
    }
    static ShadingState createFinalGatherState(ShadingState state,
                                               Ray r,
                                               int i) { ShadingState finalGatherState =
                                                          new ShadingState(
                                                          state,
                                                          state.
                                                            istate,
                                                          r,
                                                          i,
                                                          2);
                                                        finalGatherState.
                                                          diffuseDepth++;
                                                        finalGatherState.
                                                          includeLights =
                                                          false;
                                                        finalGatherState.
                                                          includeSpecular =
                                                          false;
                                                        return finalGatherState;
    }
    private ShadingState(ShadingState previous, IntersectionState istate,
                         Ray r,
                         int i,
                         int d) { super();
                                  this.r = r;
                                  this.istate = istate;
                                  this.i = i;
                                  this.d = d;
                                  this.instance = istate.
                                                    instance;
                                  this.primitiveID = istate.
                                                       id;
                                  this.hitU = istate.u;
                                  this.hitV = istate.v;
                                  if (previous == null) {
                                      diffuseDepth =
                                        0;
                                      reflectionDepth =
                                        0;
                                      refractionDepth =
                                        0;
                                  }
                                  else {
                                      diffuseDepth =
                                        previous.
                                          diffuseDepth;
                                      reflectionDepth =
                                        previous.
                                          reflectionDepth;
                                      refractionDepth =
                                        previous.
                                          refractionDepth;
                                      this.
                                        server =
                                        previous.
                                          server;
                                      this.
                                        map =
                                        previous.
                                          map;
                                      this.
                                        rx =
                                        previous.
                                          rx;
                                      this.
                                        ry =
                                        previous.
                                          ry;
                                      this.
                                        i +=
                                        previous.
                                          i;
                                      this.
                                        d +=
                                        previous.
                                          d;
                                  }
                                  behind = false;
                                  cosND = Float.NaN;
                                  includeLights = (includeSpecular =
                                                     true);
                                  qmcD0I = QMC.halton(this.
                                                        d,
                                                      this.
                                                        i);
                                  qmcD1I = QMC.halton(this.
                                                        d +
                                                        1,
                                                      this.
                                                        i);
                                  result = null; }
    final void setRay(Ray r) { this.r = r; }
    public final void init() { p = new Point3();
                               n = new Vector3();
                               tex = new Point2();
                               ng = new Vector3();
                               basis = null; }
    public final Color shade() { return server.shadeHit(this);
    }
    final void correctShadingNormal() { if (Vector3.dot(n,
                                                        ng) <
                                              0) { n.negate(
                                                       );
                                                   basis.
                                                     flipW(
                                                       );
                                        } }
    public final void faceforward() { if (r.dot(ng) < 0) {
                                          
                                      }
                                      else {
                                          ng.
                                            negate(
                                              );
                                          n.
                                            negate(
                                              );
                                          basis.
                                            flipW(
                                              );
                                          behind =
                                            true;
                                      }
                                      cosND = Math.max(-r.
                                                         dot(
                                                           n),
                                                       0);
                                      p.x += 0.001F * ng.
                                                        x;
                                      p.y += 0.001F * ng.
                                                        y;
                                      p.z += 0.001F * ng.
                                                        z;
    }
    public final float getRasterX() { return rx; }
    public final float getRasterY() { return ry; }
    public final float getCosND() { return cosND; }
    public final boolean isBehind() { return behind; }
    final IntersectionState getIntersectionState() { return istate;
    }
    public final float getU() { return hitU; }
    public final float getV() { return hitV; }
    public final Instance getInstance() { return instance;
    }
    public final int getPrimitiveID() { return primitiveID;
    }
    final void setResult(Color c) { result = c; }
    public final Color getResult() { return result; }
    final LightServer getLightServer() { return server; }
    public final void addSample(LightSample sample) { sample.
                                                        next =
                                                        lightSample;
                                                      lightSample =
                                                        sample;
    }
    public final double getRandom(int j, int dim) { switch (dim) {
                                                        case 0:
                                                            return QMC.
                                                              mod1(
                                                                qmcD0I +
                                                                  QMC.
                                                                  halton(
                                                                    0,
                                                                    j));
                                                        case 1:
                                                            return QMC.
                                                              mod1(
                                                                qmcD1I +
                                                                  QMC.
                                                                  halton(
                                                                    1,
                                                                    j));
                                                        default:
                                                            return QMC.
                                                              mod1(
                                                                QMC.
                                                                  halton(
                                                                    d +
                                                                      dim,
                                                                    i) +
                                                                  QMC.
                                                                  halton(
                                                                    dim,
                                                                    j));
                                                    } }
    public final double getRandom(int j, int dim, int n) {
        switch (dim) {
            case 0:
                return QMC.
                  mod1(
                    qmcD0I +
                      (double)
                        j /
                      (double)
                        n);
            case 1:
                return QMC.
                  mod1(
                    qmcD1I +
                      QMC.
                      halton(
                        0,
                        j));
            default:
                return QMC.
                  mod1(
                    QMC.
                      halton(
                        d +
                          dim,
                        i) +
                      QMC.
                      halton(
                        dim -
                          1,
                        j));
        }
    }
    public final boolean includeLights() { return includeLights;
    }
    public final boolean includeSpecular() { return includeSpecular;
    }
    public final Shader getShader() { return shader; }
    public final void setShader(Shader shader) { this.shader =
                                                   shader;
    }
    final Modifier getModifier() { return modifier; }
    public final void setModifier(Modifier modifier) { this.
                                                         modifier =
                                                         modifier;
    }
    public final int getDepth() { return diffuseDepth + reflectionDepth +
                                    refractionDepth; }
    public final int getDiffuseDepth() { return diffuseDepth;
    }
    public final int getReflectionDepth() { return reflectionDepth;
    }
    public final int getRefractionDepth() { return refractionDepth;
    }
    public final Point3 getPoint() { return p; }
    public final Vector3 getNormal() { return n; }
    public final Point2 getUV() { return tex; }
    public final Vector3 getGeoNormal() { return ng; }
    public final OrthoNormalBasis getBasis() { return basis;
    }
    public final void setBasis(OrthoNormalBasis basis) { this.
                                                           basis =
                                                           basis;
    }
    public final Ray getRay() { return r; }
    public final Matrix4 getCameraToWorld() { Camera c = server.
                                                getScene(
                                                  ).
                                                getCamera(
                                                  );
                                              return c !=
                                                null
                                                ? c.
                                                getCameraToWorld(
                                                  )
                                                : Matrix4.
                                                    IDENTITY;
    }
    public final Matrix4 getWorldToCamera() { Camera c = server.
                                                getScene(
                                                  ).
                                                getCamera(
                                                  );
                                              return c !=
                                                null
                                                ? c.
                                                getWorldToCamera(
                                                  )
                                                : Matrix4.
                                                    IDENTITY;
    }
    public final boolean getTrianglePoints(Point3[] p) { PrimitiveList prims =
                                                           instance.
                                                           getGeometry(
                                                             ).
                                                           getPrimitiveList(
                                                             );
                                                         if (prims instanceof TriangleMesh) {
                                                             TriangleMesh m =
                                                               (TriangleMesh)
                                                                 prims;
                                                             m.
                                                               getPoint(
                                                                 primitiveID,
                                                                 0,
                                                                 p[0] =
                                                                   new Point3(
                                                                     ));
                                                             m.
                                                               getPoint(
                                                                 primitiveID,
                                                                 1,
                                                                 p[1] =
                                                                   new Point3(
                                                                     ));
                                                             m.
                                                               getPoint(
                                                                 primitiveID,
                                                                 2,
                                                                 p[2] =
                                                                   new Point3(
                                                                     ));
                                                             return true;
                                                         }
                                                         return false;
    }
    public final void initLightSamples() { server.
                                             initLightSamples(
                                               this);
    }
    public final void initCausticSamples() { server.
                                               initCausticSamples(
                                                 this);
    }
    public final Color traceGlossy(Ray r, int i) {
        return server.
          traceGlossy(
            this,
            r,
            i);
    }
    public final Color traceReflection(Ray r, int i) {
        return server.
          traceReflection(
            this,
            r,
            i);
    }
    public final Color traceRefraction(Ray r, int i) {
        r.
          ox -=
          0.002F *
            ng.
              x;
        r.
          oy -=
          0.002F *
            ng.
              y;
        r.
          oz -=
          0.002F *
            ng.
              z;
        return server.
          traceRefraction(
            this,
            r,
            i);
    }
    public final Color traceTransparency() { return traceRefraction(
                                                      new Ray(
                                                        p.
                                                          x,
                                                        p.
                                                          y,
                                                        p.
                                                          z,
                                                        r.
                                                          dx,
                                                        r.
                                                          dy,
                                                        r.
                                                          dz),
                                                      0);
    }
    public final Color traceShadow(Ray r) { return server.
                                              getScene(
                                                ).
                                              traceShadow(
                                                r,
                                                istate);
    }
    public final void storePhoton(Vector3 dir, Color power,
                                  Color diffuse) {
        map.
          store(
            this,
            dir,
            power,
            diffuse);
    }
    public final void traceReflectionPhoton(Ray r,
                                            Color power) {
        if (map.
              allowReflectionBounced(
                ))
            server.
              traceReflectionPhoton(
                this,
                r,
                power);
    }
    public final void traceRefractionPhoton(Ray r,
                                            Color power) {
        if (map.
              allowRefractionBounced(
                )) {
            r.
              ox -=
              0.002F *
                ng.
                  x;
            r.
              oy -=
              0.002F *
                ng.
                  y;
            r.
              oz -=
              0.002F *
                ng.
                  z;
            server.
              traceRefractionPhoton(
                this,
                r,
                power);
        }
    }
    public final void traceDiffusePhoton(Ray r, Color power) {
        if (map.
              allowDiffuseBounced(
                ))
            server.
              traceDiffusePhoton(
                this,
                r,
                power);
    }
    public final Color getGlobalRadiance() { return server.
                                               getGlobalRadiance(
                                                 this);
    }
    public final Color getIrradiance(Color diffuseReflectance) {
        return server.
          getIrradiance(
            this,
            diffuseReflectance);
    }
    public final ShadingState traceFinalGather(Ray r,
                                               int i) {
        return server.
          traceFinalGather(
            this,
            r,
            i);
    }
    public final Color occlusion(int samples, float maxDist) {
        return occlusion(
                 samples,
                 maxDist,
                 Color.
                   WHITE,
                 Color.
                   BLACK);
    }
    public final Color occlusion(int samples, float maxDist,
                                 Color bright,
                                 Color dark) { if (n ==
                                                     null) {
                                                   return bright;
                                               }
                                               faceforward(
                                                 );
                                               OrthoNormalBasis onb =
                                                 getBasis(
                                                   );
                                               Vector3 w =
                                                 new Vector3(
                                                 );
                                               Color result =
                                                 Color.
                                                 black(
                                                   );
                                               for (int i =
                                                      0;
                                                    i <
                                                      samples;
                                                    i++) {
                                                   float xi =
                                                     (float)
                                                       getRandom(
                                                         i,
                                                         0,
                                                         samples);
                                                   float xj =
                                                     (float)
                                                       getRandom(
                                                         i,
                                                         1,
                                                         samples);
                                                   float phi =
                                                     (float)
                                                       (2 *
                                                          Math.
                                                            PI *
                                                          xi);
                                                   float cosPhi =
                                                     (float)
                                                       Math.
                                                       cos(
                                                         phi);
                                                   float sinPhi =
                                                     (float)
                                                       Math.
                                                       sin(
                                                         phi);
                                                   float sinTheta =
                                                     (float)
                                                       Math.
                                                       sqrt(
                                                         xj);
                                                   float cosTheta =
                                                     (float)
                                                       Math.
                                                       sqrt(
                                                         1.0F -
                                                           xj);
                                                   w.
                                                     x =
                                                     cosPhi *
                                                       sinTheta;
                                                   w.
                                                     y =
                                                     sinPhi *
                                                       sinTheta;
                                                   w.
                                                     z =
                                                     cosTheta;
                                                   onb.
                                                     transform(
                                                       w);
                                                   Ray r =
                                                     new Ray(
                                                     p,
                                                     w);
                                                   r.
                                                     setMax(
                                                       maxDist);
                                                   result.
                                                     add(
                                                       Color.
                                                         blend(
                                                           bright,
                                                           dark,
                                                           traceShadow(
                                                             r)));
                                               }
                                               return result.
                                                 mul(
                                                   1.0F /
                                                     samples);
    }
    public final Color diffuse(Color diff) { Color lr =
                                               Color.
                                               black(
                                                 );
                                             if (diff.
                                                   isBlack(
                                                     ))
                                                 return lr;
                                             for (LightSample sample
                                                   :
                                                   this)
                                                 lr.
                                                   madd(
                                                     sample.
                                                       dot(
                                                         n),
                                                     sample.
                                                       getDiffuseRadiance(
                                                         ));
                                             lr.add(
                                                  getIrradiance(
                                                    diff));
                                             return lr.
                                               mul(
                                                 diff).
                                               mul(
                                                 1.0F /
                                                   (float)
                                                     Math.
                                                       PI);
    }
    public final Color specularPhong(Color spec, float power,
                                     int numRays) {
        Color lr =
          Color.
          black(
            );
        if (!includeSpecular ||
              spec.
              isBlack(
                ))
            return lr;
        float dn =
          2 *
          cosND;
        Vector3 refDir =
          new Vector3(
          );
        refDir.
          x =
          dn *
            n.
              x +
            r.
              dx;
        refDir.
          y =
          dn *
            n.
              y +
            r.
              dy;
        refDir.
          z =
          dn *
            n.
              z +
            r.
              dz;
        for (LightSample sample
              :
              this) {
            float cosNL =
              sample.
              dot(
                n);
            float cosLR =
              sample.
              dot(
                refDir);
            if (cosLR >
                  0)
                lr.
                  madd(
                    cosNL *
                      (float)
                        Math.
                        pow(
                          cosLR,
                          power),
                    sample.
                      getSpecularRadiance(
                        ));
        }
        if (numRays >
              0) {
            int numSamples =
              getDepth(
                ) ==
              0
              ? numRays
              : 1;
            OrthoNormalBasis onb =
              OrthoNormalBasis.
              makeFromW(
                refDir);
            float mul =
              2.0F *
              (float)
                Math.
                  PI /
              (power +
                 1) /
              numSamples;
            for (int i =
                   0;
                 i <
                   numSamples;
                 i++) {
                double r1 =
                  getRandom(
                    i,
                    0,
                    numSamples);
                double r2 =
                  getRandom(
                    i,
                    1,
                    numSamples);
                double u =
                  2 *
                  Math.
                    PI *
                  r1;
                double s =
                  (float)
                    Math.
                    pow(
                      r2,
                      1 /
                        (power +
                           1));
                double s1 =
                  (float)
                    Math.
                    sqrt(
                      1 -
                        s *
                        s);
                Vector3 w =
                  new Vector3(
                  (float)
                    (Math.
                       cos(
                         u) *
                       s1),
                  (float)
                    (Math.
                       sin(
                         u) *
                       s1),
                  (float)
                    s);
                w =
                  onb.
                    transform(
                      w,
                      new Vector3(
                        ));
                float wn =
                  Vector3.
                  dot(
                    w,
                    n);
                if (wn >
                      0)
                    lr.
                      madd(
                        wn *
                          mul,
                        traceGlossy(
                          new Ray(
                            p,
                            w),
                          i));
            }
        }
        lr.
          mul(
            spec).
          mul(
            (power +
               2) /
              (2.0F *
                 (float)
                   Math.
                     PI));
        return lr;
    }
    public Iterator<LightSample> iterator() { return new LightSampleIterator(
                                                lightSample);
    }
    private static class LightSampleIterator implements Iterator<LightSample> {
        private LightSample current;
        LightSampleIterator(LightSample head) { super(
                                                  );
                                                current =
                                                  head;
        }
        public boolean hasNext() { return current !=
                                     null; }
        public LightSample next() { LightSample c =
                                      current;
                                    current = current.
                                                next;
                                    return c; }
        public void remove() { throw new UnsupportedOperationException(
                                 ); }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAALVcDXAcxZXunZFkSZYtWf4T/pVl2eAftLYBAzEXW5YtI0f+" +
       "iST7ghyQR7MjaWB2Zzwza8kGB2MqtouUCZczsSGOK5czPyEYfJf4CEWR80Fx" +
       "4HBcCoe6nI8AxyWX40ycg6QIPyHh3uvu2Z39mdbIHqtqW7PT3e99r/v169ev" +
       "u/fx86TUsckCyzR29Bum26QNuU23Gtc0uTsszWla237NRsV2tESLoThOF7zr" +
       "URtOVP/hj/cN1EikrJuMV1Ip01Vc3Uw5HZpjGtu1RDupzr5dbWhJxyU17bcq" +
       "25V42tWNeLvuuMvayWhfVZc0tnsQ4gAhDhDiFEK8OVsKKo3RUulkC9ZQUq6z" +
       "jXyFxNpJmaUiPJfMyiViKbaS5GQ2UgmAQjl+3wxC0cpDNqnPyM5kLhD4/gXx" +
       "g4duqfl7mVR3k2o91YlwVADhApNuUpXUkr2a7TQnElqim4xLaVqiU7N1xdB3" +
       "UtzdpNbR+1OKm7a1TCPhy7Sl2ZRntuWqVJTNTquuaWfE69M1I+F9K+0zlH6Q" +
       "dVJWViZhK74HASt1AGb3KarmVSm5TU8lXDIzv0ZGxsYvQAGoOiqpuQNmhlVJ" +
       "SoEXpJb1naGk+uOdrq2n+qFoqZkGLi6ZEkgU29pS1NuUfq3HJXX55TayLChV" +
       "QRsCq7hkYn4xSgl6aUpeL/n65/z6G+69PXVjSqKYE5pqIP5yqDQjr1KH1qfZ" +
       "WkrVWMWq+e3fVCY9u18iBApPzCvMyjx1x/srFs449RIrM7VImQ29t2qq26Me" +
       "6x376rSWedfLCKPcMh0dOz9Hcqr+G3nOsiELRt6kDEXMbPIyT3X88027H9Pe" +
       "lUhlGylTTSOdBD0ap5pJSzc0e42W0mzF1RJtpEJLJVpofhsZBc/tekpjbzf0" +
       "9Tma20ZKDPqqzKTfoYn6gAQ20Sh41lN9pvdsKe4AfR6yCCFj4EOWw6eWsD/6" +
       "3yVfjA+YSS2uqEpKT5lx0F1NsdWBuKaacUdJWgb0mpNO9RnmYNyx1bhp92e+" +
       "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" +
       "pOXSX/TZ+aPJP7zhkaNv0l8o+n+mEg+QXlAAAA==");
}
