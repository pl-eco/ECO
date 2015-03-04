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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "MrCGxr/Gobcuiy1un0YwosUoKeqtXdfyvQa5KdfYpvwEPgi+H7MvMyGf4Fd3" +
           "p5dtpasowUtnwzEOJE+7l3YlGrayB0yx5SamyIhjaiZ9/srS3TxSY3MyFDNE" +
           "mnQK7vJ09y68wbIivKqxMuK1c09f3PLuVAevrkp28ePlXmxU4OVubOVhvr7h" +
           "9z/56fqDlyMoPIqaFR1Lo5hfNagJMJ5Yebjji8b9D/Cwa5lvdIKReX9rDZUd" +
           "nTi+ZcTHT3/89l8Wr16KoCjcGyw/sAm1GRR/iVptjX+D+CSMPgdckDetNjdU" +
           "DzxAnEBod2fdK5CiO2vtzbq24E3JGgOAQ2IO6wVN4tBRnpfNBcPwr/KQavmf" +
           "QuoIXA//hv1c9ZHzaef508r9yLAnMQJNn38Riq6OZGoonc5MPjwxkpka2j82" +
           "NJwa4XFqwGJopBTNbd4mNoBxtYQyMGfQbqLNtRoM3hydOba8Io2/sMNO7fby" +
           "op2J98pv/vnLxKk/XqpSH0adBtE7McLOKyvo9vLGy4Ptp1763uv08vb77PNW" +
           "waAg45vH3t04uSt/8D8o43oCmge3fGnv2UsP3i5+I4wiLvpX9JLlTIOB2IJw" +
           "LZjaZBnyd7vIv4E5oRvMco+D/PdUL6VqwX7UKGSVEnJXv9SNVdY4vh2CKyOP" +
           "rX1g4NXv9glTVqFFm3N6SGGx/Z3Z09desR0VvMgDxOT48lOfJE4sh31d+W0V" +
           "jbGfx+7MuZi3eLkYqp6L7UmnPex1+0MG7H7griIWP2L0z+cWf/zdxaWwY5Ms" +
           "mCOr6wrBWmUlxCdyrvs2sckecNtOx307q7qPPZRV3HB0lbUvsccRCkEF/uAZ" +
           "fFOpYmyyC6QZdqQa/q+kenKVta+yx1cg/kyi6nN2m8Clc+BlAQSe02WpSi0J" +
           "AFalLyyh1uZVO0pwaGfFf1b2/yziqyuxxg0rD/2Wt0PufyFNKdSYKyiKvwzz" +
           "jaOGSXIyV6nJLsoM/nMS8DMoCajEfrikyzbZKYrW+MggcpyRn+hbFEWAiA1P" +
           "c/sfKKIyDDbK3sq6mmBJsLdg/5uXEc+t7Nn32I27X+BoVw/3zsKCc2M22A2d" +
           "C3Jbau5W2iu6u/+j1teatpZSoJU92n3Q45Otp3rXM6IalPcpCz/c8IOdL65c" +
           "5d3WvwB+Krb6ZBUAAA==");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1cDXQVx3WetysJSQgkxJ/MrxACmx/rAbaxHdyAED8WET+V" +
       "BI1FbLHat5LW7Hu77O5DAkOM8QlwnIPjpjhgh3DSFP/EMTZtQh0fH6fUPq5N" +
       "XDfHxKcpdWzXTZq6OKR2chz/xIl778zse/t+drSCpTrnjfbtzNz73Zk7d+7c" +
       "mXmPXyCljk3mWaaxo88w3SZt0G263biuyd1haU7TmrbrNii2oyVaDMVxOuFd" +
       "t9pwsvr3f7ivv0YiZV1krJJKma7i6mbKadcc09iuJdpIdfbtSkNLOi6pabtd" +
       "2a7E065uxNt0x13SRkb6qrqksc2DEAcIcYAQpxDizdlSUGmUlkonW7CGknKd" +
       "beTLJNZGyiwV4blkRi4RS7GVJCezgUoAFMrx+yYQilYetEl9RnYmc4HA98+L" +
       "Hzp8W83fyaS6i1TrqQ6EowIIF5h0kaqkluzRbKc5kdASXWRMStMSHZqtK4a+" +
       "k+LuIrWO3pdS3LStZRoJX6YtzaY8sy1XpaJsdlp1TTsjXq+uGQnvW2mvofSB" +
       "rBOysjIJV+F7ELBSB2B2r6JqXpWSrXoq4ZLp+TUyMjZ+AQpA1RFJze03M6xK" +
       "Ugq8ILWs7wwl1RfvcG091QdFS800cHHJpECi2NaWom5V+rRul9Tll9vAsqBU" +
       "BW0IrOKS8fnFKCXopUl5veTrnwvrbrr3jtTNKYliTmiqgfjLodK0vErtWq9m" +
       "aylVYxWr5rZ9Q5nw7AGJECg8Pq8wK/PUrveXzZ92+iVWZnKRMut7btdUt1s9" +
       "3jP61Sktc26UEUa5ZTo6dn6O5FT9N/CcJYMWjLwJGYqY2eRlnm7/p1v2PKa9" +
       "K5HKVlKmmkY6CXo0RjWTlm5o9motpdmKqyVaSYWWSrTQ/FYyAp7b9JTG3q7v" +
       "7XU0t5WUGPRVmUm/QxP1AglsohHwrKd6Te/ZUtx++jxoEUJGwYcshU8tYX/0" +
       "v0sS8X4zqcUVVUnpKTMOuqspttof11Sz29YsM76yZX28B1q5P6nYW524k071" +
       "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" +
       "UAAA");
}
