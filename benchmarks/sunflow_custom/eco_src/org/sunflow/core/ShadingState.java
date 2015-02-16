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
final public class ShadingState implements java.lang.Iterable<LightSample> {
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
    final public void init() { p = new Point3();
                               n = new Vector3();
                               tex = new Point2();
                               ng = new Vector3();
                               basis = null; }
    final public Color shade() { return server.shadeHit(this);
    }
    final void correctShadingNormal() { if (Vector3.dot(n,
                                                        ng) <
                                              0) { n.negate();
                                                   basis.
                                                     flipW();
                                        } }
    final public void faceforward() { if (r.dot(ng) < 0) {
                                          
                                      }
                                      else {
                                          ng.
                                            negate();
                                          n.
                                            negate();
                                          basis.
                                            flipW();
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
    final public float getRasterX() { return rx; }
    final public float getRasterY() { return ry; }
    final public float getCosND() { return cosND; }
    final public boolean isBehind() { return behind; }
    final IntersectionState getIntersectionState() { return istate;
    }
    final public float getU() { return hitU; }
    final public float getV() { return hitV; }
    final public Instance getInstance() { return instance;
    }
    final public int getPrimitiveID() { return primitiveID;
    }
    final void setResult(Color c) { result = c; }
    final public Color getResult() { return result; }
    final LightServer getLightServer() { return server; }
    final public void addSample(LightSample sample) { sample.
                                                        next =
                                                        lightSample;
                                                      lightSample =
                                                        sample;
    }
    final public double getRandom(int j, int dim) { switch (dim) {
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
    final public double getRandom(int j, int dim, int n) {
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
    final public boolean includeLights() { return includeLights;
    }
    final public boolean includeSpecular() { return includeSpecular;
    }
    final public Shader getShader() { return shader; }
    final public void setShader(Shader shader) { this.shader =
                                                   shader;
    }
    final Modifier getModifier() { return modifier; }
    final public void setModifier(Modifier modifier) { this.
                                                         modifier =
                                                         modifier;
    }
    final public int getDepth() { return diffuseDepth + reflectionDepth +
                                    refractionDepth; }
    final public int getDiffuseDepth() { return diffuseDepth;
    }
    final public int getReflectionDepth() { return reflectionDepth;
    }
    final public int getRefractionDepth() { return refractionDepth;
    }
    final public Point3 getPoint() { return p; }
    final public Vector3 getNormal() { return n; }
    final public Point2 getUV() { return tex; }
    final public Vector3 getGeoNormal() { return ng; }
    final public OrthoNormalBasis getBasis() { return basis;
    }
    final public void setBasis(OrthoNormalBasis basis) { this.
                                                           basis =
                                                           basis;
    }
    final public Ray getRay() { return r; }
    final public Matrix4 getCameraToWorld() { Camera c = server.
                                                getScene().
                                                getCamera();
                                              return c !=
                                                null
                                                ? c.
                                                getCameraToWorld()
                                                : Matrix4.
                                                    IDENTITY;
    }
    final public Matrix4 getWorldToCamera() { Camera c = server.
                                                getScene().
                                                getCamera();
                                              return c !=
                                                null
                                                ? c.
                                                getWorldToCamera()
                                                : Matrix4.
                                                    IDENTITY;
    }
    final public boolean getTrianglePoints(Point3[] p) { PrimitiveList prims =
                                                           instance.
                                                           getGeometry().
                                                           getPrimitiveList();
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
    final public void initLightSamples() { server.
                                             initLightSamples(
                                               this);
    }
    final public void initCausticSamples() { server.
                                               initCausticSamples(
                                                 this);
    }
    final public Color traceGlossy(Ray r, int i) {
        return server.
          traceGlossy(
            this,
            r,
            i);
    }
    final public Color traceReflection(Ray r, int i) {
        return server.
          traceReflection(
            this,
            r,
            i);
    }
    final public Color traceRefraction(Ray r, int i) {
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
    final public Color traceTransparency() { return this.
                                               traceRefraction(
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
    final public Color traceShadow(Ray r) { return server.
                                              getScene().
                                              traceShadow(
                                                r,
                                                istate);
    }
    final public void storePhoton(Vector3 dir, Color power,
                                  Color diffuse) {
        map.
          store(
            this,
            dir,
            power,
            diffuse);
    }
    final public void traceReflectionPhoton(Ray r,
                                            Color power) {
        if (map.
              allowReflectionBounced())
            server.
              traceReflectionPhoton(
                this,
                r,
                power);
    }
    final public void traceRefractionPhoton(Ray r,
                                            Color power) {
        if (map.
              allowRefractionBounced()) {
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
    final public void traceDiffusePhoton(Ray r, Color power) {
        if (map.
              allowDiffuseBounced())
            server.
              traceDiffusePhoton(
                this,
                r,
                power);
    }
    final public Color getGlobalRadiance() { return server.
                                               getGlobalRadiance(
                                                 this);
    }
    final public Color getIrradiance(Color diffuseReflectance) {
        return server.
          getIrradiance(
            this,
            diffuseReflectance);
    }
    final public ShadingState traceFinalGather(Ray r,
                                               int i) {
        return server.
          traceFinalGather(
            this,
            r,
            i);
    }
    final public Color occlusion(int samples, float maxDist) {
        return this.
          occlusion(
            samples,
            maxDist,
            Color.
              WHITE,
            Color.
              BLACK);
    }
    final public Color occlusion(int samples, float maxDist,
                                 Color bright,
                                 Color dark) { if (n ==
                                                     null) {
                                                   return bright;
                                               }
                                               this.
                                                 faceforward();
                                               OrthoNormalBasis onb =
                                                 this.
                                                 getBasis();
                                               Vector3 w =
                                                 new Vector3(
                                                 );
                                               Color result =
                                                 Color.
                                                 black();
                                               for (int i =
                                                      0;
                                                    i <
                                                      samples;
                                                    i++) {
                                                   float xi =
                                                     (float)
                                                       this.
                                                       getRandom(
                                                         i,
                                                         0,
                                                         samples);
                                                   float xj =
                                                     (float)
                                                       this.
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
                                                           this.
                                                             traceShadow(
                                                               r)));
                                               }
                                               return result.
                                                 mul(
                                                   1.0F /
                                                     samples);
    }
    final public Color diffuse(Color diff) { Color lr =
                                               Color.
                                               black();
                                             if (diff.
                                                   isBlack())
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
                                                       getDiffuseRadiance());
                                             lr.add(
                                                  this.
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
    final public Color specularPhong(Color spec, float power,
                                     int numRays) {
        Color lr =
          Color.
          black();
        if (!includeSpecular ||
              spec.
              isBlack())
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
                      getSpecularRadiance());
        }
        if (numRays >
              0) {
            int numSamples =
              this.
              getDepth() ==
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
                  this.
                  getRandom(
                    i,
                    0,
                    numSamples);
                double r2 =
                  this.
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
                        this.
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
    public java.util.Iterator<LightSample> iterator() {
        return new LightSampleIterator(
          lightSample);
    }
    private static class LightSampleIterator implements java.util.Iterator<LightSample> {
        private LightSample current;
        LightSampleIterator(LightSample head) { super();
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
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1170616230000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAKVYDWwUxxUe3/kH/yQ2Bgzhz9iYUrC5AwLBYKRgGxMcFnD9" +
           "R2Kgl/HunL2wt7vs\nzp4PQxOSRoFCkxS1paUtBFWoJjQkUdOKtkpTIkKbhr" +
           "ZKKpVKSCGtqNqoaSpVlVKqVmnfzOzd7u3d\nOTQ56XZnd968mff3vff2ufdR" +
           "iW2hubIdoftNYkc6+3qwZROlU8O23Q+vYvKVkvKeiS26EUJFEgqp\nCkXVkm" +
           "xHFUxxVFWi3RvbUhZqNg1t/4hm0AhJ0cgebbXL735pdQ7DHacv1j56trg+hE" +
           "okVI113aCY\nqobepZGETVGNtAcncdShqhaVVJu2SegOojuJTkO3KdapvQ89" +
           "jMISKjVlxpOiBim9eRQ2j5rYwoko\n3z7aw7cFDtMsQrGqE6U9sx2sbMleCc" +
           "d21/XmUgOTKWxyEMThJwCpF2SkFtLmiGqGzw3ec/DMs2FU\nPYSqVb2PMZNB" +
           "Egr7DaGqBEkME8tuVxSiDKGpOiFKH7FUrKnjfNchVGurIzqmjkXsXmIbWpIR" +
           "1tqO\nSSy+Z/qlhKpkJpPlyNSwMjqKq0RT0k8lcQ2PgNh1nthC3E3sPQhYoc" +
           "LBrDiWSXpJ8V5VB4vXB1dk\nZGzaAgSwtCxB6KiR2apYx/AC1QpbalgfifZR" +
           "S9VHgLTEcGAXimYXZMp0bWJ5Lx4hMYpmBel6xBRQ\nlXNFsCUUzQiScU5gpd" +
           "kBK/ns01z3wZFz33plA/ftYoXIGjt/BSyaH1jUS+LEIrpMxMJbTuQr3Q86\n" +
           "c0MIAfGMALGgaV90cUB696f1gmZOHprtw3uITGPytuP1vQfuM1CYHWOKadgq" +
           "M36W5DwcetyZtpQJ\nUVuX4cgmI+nJS70/e/DQefJeCFV0o1LZ0JwE+NFU2U" +
           "iYqkas+4hOLEyJ0o3Kia508vluVAZjCVxe\nvN0ej9uEdqNijb8qNfgzqCgO" +
           "LJiKymGs6nEjPTYxHeXjlIkQmgZ/JCEUXoH4T9wpWh6J2o4e14yx\nqG3JUc" +
           "MayTzLhkWifaNYAR9hUUIizHNMiqToqJEgUSxjXdWN6IgKsSobyxSSZPf/k1" +
           "+KnbF2rKiI\ngV4weDXw+82GphArJk/cfONg15YvHBGOwZzZlY6iFbBNxN0m" +
           "wraJ+LdpktSRUdqHE6ZGuinTtGGh\noiK+43R2BGEsUPVeCFqAt6olfbvvf+" +
           "hIYxi8xBwrBj0x0kaQzT1Xl2x0epHdzUFQBvea9e2dhyO3\nJu4V7hUtDMB5" +
           "V1e+eeHqmX9ULQ2hUH50ZPICPlcwNj0MUjOo1xSMp3z8/3Z060vXrr79aS+y" +
           "KGrK\nCfjclSxgG4OWsQyZKACBHvuzd1WHd6DB4yFUDCgAyMfPD6AyP7hHVu" +
           "C2pUGQyVImocq4YSWwxqbS\nyFVBRy1jzHvDXaaGj5lbT2GePAdcepXr2vzO" +
           "ZmeY7FonXIxZOyAFB9lbny9d/ruXK69wtaTxuNqX\n8foIFdE91XOWfosQeP" +
           "/213u+/NX3D+/kniJcBaWA8lMeJUSzBojC7Nc0oCcMRY2reFgjzNH+U71o\n" +
           "xQ/++lSNsIgGb9IGbfloBt77uzrQoauf/ed8zqZIZtnEO71HJoSY5nFutyy8" +
           "n50j9ehv5p38OT4F\nYAcAY6vjhGNGkev77FAzKZqbE2W+wOI6jnDSJfy6jO" +
           "nC1Qh7XskujXCAlgJhkSfBx+SD50canX2/\n+BEXrRL7KwW/ibZis014Bbss" +
           "ZCaYGYzszdgeBbpVl7btqtEu/Rs4DgFHGRKrvd0ChEllGdilLim7\n/urluo" +
           "feCqPQJlShGVjZhHlsoHJwSmKPAjilzHs3cMerGWOuWMNFRlwJs10FpHxPDE" +
           "uWFIaGTaw8\n8KIqNtxyTnpj+ymugIKgkCc7BviMvzJw+tav6A3Ox4tOtroh" +
           "lYu8UFJ5a1uvJaeWvvhMIoTKhlCN\n7BZ9g1hzWAwMQY1ipytBKAyz5rPrDZ" +
           "Fc2zLoMzeIDL5tg7jgIT6MGTUbVwWggOu+CSBgpQsFK4NQ\nUIT4YANf0sSv" +
           "i12Mp6jMtNQkZoUgKpMdCzCK3pbzC3xh17vZpV2Y+558bpHK3TjExhspKrV5" +
           "KRr0\nlTm+TqDPGbapr1havWFNy/c277rMU0Y51KjY3uapCToDNioC+y4t7H" +
           "BBntbl/ivk5owTIolleyov\n5N2lwXUnlryXbF7+2Cl+luJhbPNjVIAZbUZJ" +
           "0YLCTQHnJSL4DmHM/8IPwf9D9meq4y94yTLLi/t0\nOo/wvsM0U2lz1XqRnK" +
           "ZhMwOgz9qgPmPyzF3mwCD64i4hcsNH6Comz/7lj6/tuD5o8mCqTqpQ5RGl\n" +
           "3+1EsuHXqyrasrqTvNqMyX9s3H3jG8XvnORlp1AcO/cqgG52X+O6VFXGpZiH" +
           "zPRJ5J6CZ4i//Prk\n9MetIwa3SAn3jlzPWFRAWj+jmPzUux0fHpL2Lg6xUK" +
           "xg0YstqFehII4Uatj8DJr6YbQRVkFU3ylW\nQ3XGncd1ktrM20xpQdGyQrxZ" +
           "DxusQIKAAWg9RqwOw9G5rA3ZEFLhmKZ/lvvenZ/I90z2o6ioK+2E\nNdwJGf" +
           "JFBPJRN0ZbW9esMm8vraay8ghFFZzB2rWtq1eaLM3NK9RJ8S7w8AN/r3oCv7" +
           "ZbOHZtdnfS\nBR38n/dfJovXP/mHPEV1qdsJe/uH2X5ZVfBW3mF6meLos9+9" +
           "SN9qXif2mwR0gguXrjszXr/uhWMf\no/atD2ggyHpqcs5nwqPq6yHeBIvEk9" +
           "M8Zy9qC/gKnMex9P6spLMgk3SqmWkWgHpa3aTTmr/+zJdx\nSk1nWEtjfv7C" +
           "6cAkc59jFwey1SgAPygZDDTL/+HIUhPQgCZ5oX3zicafvD7wzOF8uJ79dci/" +
           "KiY/\n8s7v94affnVYrAuWGQHi4/PP/umlm73ThT+JLxULcz4W+NeIrxVcmm" +
           "ru0Q2T7cCpX2tueO7h3hv8\nRGxdAhQwbBgawbqXhJOTJOGsmOIP+7Kt2QBW" +
           "XO9ac31ea7LL2CSWeXKSuafZ5SgFH2Mmux0Y8MQ6\n9knEmgvidLhidXwssU" +
           "5OMvdNdjkBPm2RhJEUZ37ETcmPg7RJQ1U8Qb52u4IA6E3L08Gn9TZv0t6f\n" +
           "BUTOF0HxFUuWrh/Y9YH023/xhjXzpalSQlPijqb5y03fuNS0SFzl8laK4tPk" +
           "twmA++BJQGZ24yf9\njiA7T1Gljwwc1x35iS5QFAYiNnw+kyZykkl2ZmCt3c" +
           "KC1cVWR3x2jckPXNi5IHWs/0uiJoDUNT7u\nZuAy0XhncLWhILc0rzfRiy8M" +
           "vvz82nQc8uZrug/tsnzxbjE7idUBy/O3vV0Jk/JGdfyHM7+/fuL0\njRDvt/" +
           "8HGAebCCsXAAA=");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170616230000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAM1dCXgcxZWuOSRbh5EsH/J9yDbyObKxDbLN4vuQGR8ryQIb" +
       "E9HqaUkNo+lxd48s\nGydciU0MBLwh4b7ymcV4cXDCgkkgxA53ICwxCSQQDF" +
       "5YyALmC+FbggkL+1519XRPT3eNRj3xt/q+\nftMzVV3v/a9evap6VV164AQp" +
       "0lQyStQi+rakpEWWNq0XVE2KLY0LmtYMP7WKTxeVrL/v3IQSJIEo\nCcoxnV" +
       "RERa0uJuhCnRyra1i2oEcl05JKfFtHXNEjUo8euTg+l5W3Ojo3q8Dz7jxUdc" +
       "Xe8NggKYqS\nCiGRUHRBl5XE8rjUpemkMnqx0C3UpXQ5XheVNX1BlAyQEqmu" +
       "pUpC04WErm0h3yKhKClOilimTsZH\nTeZ1wLwuKahCVx1lX7eesoUSBqmSLs" +
       "gJKbY4zQ6enJ75JIjNnmvMzg2F9MfEFoBDJQDU49KoDbRZ\nUJOhfS1n7rj7" +
       "/hCp2EQq5EQTFiYCEh34bSLlXVJXm6Rqi2MxKbaJDExIUqxJUmUhLm+nXDeR" +
       "Kk3u\nSAh6SpW0RklT4t2YsUpLJSWV8jR/jJJyETGpKVFX1LSO2mUpHjO/Fb" +
       "XHhQ6APdSCbcBdgb8DwFIZ\nBFPbBVEyHwlfIiegxsc6n0hjnHguZIBH+3VJ" +
       "eqeSZhVOCPADqTLqMi4kOuqadFVOdEDWIiUFXHQy\nwrNQ1HVSEC8ROqRWnQ" +
       "xz5ltvJEGuEqoIfEQnQ5zZaElQSyMctWSrn2lDP7t63+1PLKK2HY5JYhzl\n" +
       "L4WHxjgeapTaJVVKiJLx4OepyI0NG1OjgoRA5iGOzEaexZMObYj++ZdjjTwj" +
       "XfKsa7tYEvVWce2e\nsY2XrlRICMXon1Q0GSs/AzltDutZyoKeJLTaoekSMT" +
       "FiJh5ufGbj5fulD4OktIEUi0o81QV2NFBU\nupJyXFJXSglJFXQp1kBKpERs" +
       "KU1vIP3gPgomb/y6rr1dk/QGEo7Tn4oV+h1U1A5FoIpK4F5OtCvm\nfVLQO+" +
       "l9T5IQMgAushCuKmL80U+dzIzUaalEe1zZWqepYp2idqS/i4oq1TV1CjGwEW" +
       "wlUgQtJ6mT\naF2n0iXVCaKQkBNKXYcMbVVUZsSkbvzMs7welLFqayCATs/Z" +
       "eONg96uUeExSW8X73vn1juXnfvdq\nwzDQmBk6nYwGNhHGJoJsInY2JBCgpQ" +
       "9GdkbFgFovgQYKrqx8StOFqy+6uiYEFpHcGgadYNYawMFk\nWC4qS61W3EAd" +
       "ngimNOxHF+yKfH7fQsOU6rydrevTZS8feOHuT8unBknQ3RMiNvDFpVjMenSf" +
       "aQ83\n0dl23Mr/ePeah1594c3JVivSycSsxp39JDbOGmctqIooxcDdWcXvHV" +
       "4ROo+07AmSMLR48HJUfnAg\nY5w8MhrpAtPhIZZ+UVLWrqhdQhyTTC9Vqneq" +
       "ylbrF2oelfR+EFROOVrtMLjamBnTT0wdkkQ61DAn\nrG0HCupQP7+qeOZrj5" +
       "c9TdVi+t4KW+/WJOlGSx5oGUuzKknw+5s3r//+D07suoBaCjMVnfRLqnI3\n" +
       "GFkPPHO69Qy04Tj4EazJiRsSXUpMbpeFtriEJvdlxaRZD3/0vUqjbuLwi1m1" +
       "03MXYP0+fAm5/IVv\n/G0MLSYgYh9i4bCyGXAGWSUvVlVhG8rRc8XR0bc8K9" +
       "wBLg7ciiZvl6inKKLQirRvIkAy3DYOWask\nqKuSRawfkLealopdSUSV2pEh" +
       "GkvPtuO1r497sXLpC4ZonTqZZOt0WM66hkS3IlJjXyUkYuAGDUlH\nuTI8Tx" +
       "WS0K28/PZ7F14/7f1nsNElqVFU66Qmq/U3YJ+pGfipCzCzDs7K2ihsA6bD7K" +
       "MtVe4Cr91N\nLfadnTW/eG7DXbuMVj6FM6SyP9UqXvbW25eErj/SZjzn7Lkc" +
       "mfeM2fveQ+80DjYMwujeJ2T1sPZn\njC6e2npFEk1vPI8Dzf3UtPEPfKvxGJ" +
       "OoKrOjWg6Dufe3PSnVnn3dcRf/GoJBCH45h3Kso9qcSmkE\nmx81GELTliGp" +
       "AYGme6jKZRjXKu7Y31GT2vL8zyjrMsE+HrQ3zjVC0sBciWQC4q52+vRVgtYJ" +
       "+eYc\nXru5Mn7471DiJihRhOGTtk6FfqQno2mz3EX9Xj/y5NCLfhsiwRWkNK" +
       "4IsRUC9YqkBNyRpHVCF9ST\nXLiIepzKrf2RUsiEKmEEU0CP7dsYjWsuK3AQ" +
       "aPnT1rbp+6K/XncHVYBnd+BiSY5ytj+x4c7Pf6Mf\no+VYfhmfHt+T3b/CwN" +
       "l6tv7V7oHFB+/qCpJ+m0ilyIb2LUI8hd5vE4xENXO8D8P/jPTMUaUxhFqQ\n" +
       "7ndGOU3ZxtbZI1h2B/eYG+/L3TqBSXANYp3AIGcnECD0phHJ6TopBm9n8wK9" +
       "cBhGL4J0OZImo2pX\neZrAmkzhxsI1mAk32EO4jaZwmqR2S6op3Kgs4aJyR6" +
       "feRDM5xNqUp1jD4RrCxBriIdZFTKygahQ+\nP2l8LtSxphRBd8gg/ANk6EzL" +
       "sA3vRAdLuQ8shzKWQz1Ydpm1Ac09FdfN2qi214bcBVMc7JkVZ0Uk\n8pQIgV" +
       "cziao9JNKZRIGkqzBdMLyHCQZ45tkOYVJ5CoMqGcaEGeYhzKWmMAlTmGFZwr" +
       "RI6DOd0uzI\nU5pqVmFmxblJcyWTJoTjLr5yznCIc1UfxBnBxBnhIc53TXNN" +
       "dOSrnd15ijMBrpFMnJEe4tzAxClq\nEzRZMyUanyXROlXvVNbSEfgSzOkQbU" +
       "+eoqHpjGKijfIQ7SZTNFHR1i5za9s358kVlTCacR3twfV2\ns223SZ0wEnK6" +
       "tX5tihKXhIRDkjvylAS5j2GSjPGQZC+TJNwp6xvc4N/7D2B6v41pixvT/Xky" +
       "RUZj\nGdOxHkx/zJj2l1lPb9rhcJfO18jhkOrBPKVCmxvHpBrnIdXDTKqypD" +
       "k+bqBWeI6D9yN58sZgynjG\ne7wH75+bLjTd1bvORhySPNYHSWqYJDUekhwx" +
       "JYm5Yf9VHzhOYBwneHB8xuQou3F8Nk+O6IcnMo4T\nPTi+YLb5LV3ispkNzj" +
       "ZfHFNSbXGnyb3YB0EmMUEmeQjyil2QWQ347SUH29/1ge3pjO3pHmz/YLLV\n" +
       "OoWYNbqsdo2UZQ0s/9gHj1DLJKr1kOgt0yMYwQxLpmyPsIblcEj1dh+c42Qm" +
       "1WQPqd5jUpUDx/aU\nJi2TkkbY1Gmk7+fJfBxcUxjzKR7MP2TMK1hMBGYfnv" +
       "w/6gP/qYz/VA/+f7HxVwU+/0/y5I/OZxrj\nP82D//8w/gPkhBhPxSQ606HF" +
       "3OXg/lme3NEtTGfcp3tw/8JEz7g3JSUxFRdUN/5/7wP/GYz/DA/+\nXzP+ZX" +
       "E6wRO6kvF0L+k1C6SZMmULkD50lBEmW8RdtkAxky3UJSS9ZVrfqeg4YYZ7h0" +
       "z9ODIZSRMp\nrTUCqUGcXMoJIQ4OKwmOWRbtsRSMyI+0ReiaUm2ablsymrvo" +
       "rOk/XbX5SRpMLxHisqCttcIIQZl2\nc4EelUz1Dsg4y1SfbH5aemfID90Cf3" +
       "Q5kz3qfO6HUz7snjbzyjuoLGEYgVMxSqOkSMOcOhnnvTRK\nyzIiXAOMKvsa" +
       "/ghcX+GF2qM/EFy4GWYFXBp0ScXYcISuviaT6TlRlRWfNfNg5QwHfVY59dkq" +
       "Vm9O\nbmgh1242II/PoatWccSLP3/1vNdbkjTYVNEta7IuxZrZemxmONpab1" +
       "mQsUbrqs1W8d2aC4/dGn7r\nFrr4ZigOEa1gMd8GZlXlaatCC6m2IWJS0Ij5" +
       "By/dMvjb6tUKrZEiah3ZljHJA629oFbxe39e8tXl\n0UtqgxiqKsXolqBKsY" +
       "aETiJey9b2AiY2w90yeGpBlJxmPC0nOqjxMCOpSv+aXnTRyQyvsmnY2bE2\n" +
       "4wyolUJjldQlSipBsY7PDLGVpjCqbqVS2zvNl+0l8Q9Ges2mEVZaRmhEBmHG" +
       "RWth1lkc12Jzdxlx\nVdKjk1L6+Lx5Z82eQ4Pfo71Wk2nge9f5n5TvFJ66MM" +
       "hC1It0UqIryRlxqVuK20pegiVlrPutoevn\nVoR09/3/dkj/7bT5RgPhOBPn" +
       "g1Pn37197PwHr+nDat9YBzZn0QO7R/5zqFN+LkiX+I2Aa9bWgMyH\nFjhsAO" +
       "RJqYnmjGDruHSXUYZK74ZrJusyZjq7DMv1Z/j0AA4+6eYKo/6sBQNzhYlVvv" +
       "uiDEvsZaz2\nnF51UrnMjcZYEU/gHIfItjWOwCok9ToZKKoSsDfLNwPMMymd" +
       "Y/WF83L1z6Zl45fAnEzNa3DVM83X\nu2oeH5rvELeYllicpxJpXEDMOTvl6N" +
       "qpxkaOGjchWQfDH0ONHAWu96PADXAtYgpc1GsFhmiJoSx5\nuHqhsGIcyBcj" +
       "EcB/GpCXGVMOdL8iD3+bH/zNcK1g+FecAvw6B/92JFtgFmrgXxlXNG1bTviq" +
       "H/jn\nw7WWwV97CuB/mwN/N5IrdBjLUviN6UlfThVc6VcFzUwFzadABTdyVH" +
       "Arkj12FbB5Z04V/IsfFayH\naxNTwaZToIK9HBXsR3IPjI8NFazAac9KQe+U" +
       "VA76H/URfSn+iBO+Kxn6K13Ru/TeWE6Fs+cOsPkY\nTwsU5CMcBWAgNPATuv" +
       "Cpmz29LTQX7lbkmIX8p31EPtBEvpsh391L5EGKHEmlE74dxbOctOeR/ErH\n" +
       "/XgyzlwCj1t4nvSDB+NsNzE8NxUWzyuctN8jeQnm6DSYaNa+19qohfU//Fgt" +
       "BlRvY1hv82yzD3HE\nfouTdhzJn8CEwWxV8MJsw6CxHOaosjf9VBmGxPcxGP" +
       "sKW2UfctJOIHkPRle4dbhdUbcKaswBK2dE\nMxeswwzW4cLC+hsn7SSSv8IU" +
       "sAN9hwaD2PMxm2ih+tQvqucZqucLiioY4qQV4dNf2VFtdKD62g8q\nXJF6ha" +
       "F6pbCoKjhpA5GU6qQ/oFqaXu9NYwqW+a2pNxmmNwuLaSQnbTSSobisqS2hi8" +
       "mY6S4LU7Uf\nj3cGXMcZpuOumHJ4vOAkTlotkhrweFAdnhske7uXkoKd4KcC" +
       "cXHmAwb2g8JW4GxO2lwkEeiaQQsb\nHAZZ5xfPpwzPp4XFs5CTthjJfANPiw" +
       "PPAj94cBnvS4bny8LiOZeTtgbJCui3qJX2duuAhXmlH8ww\n5AgUGZCNz8Jh" +
       "3shJuwBJs05OA8zrHRsTLGQb/LgW6AICJQxZiSsyN9fiGO57DfgoCJEDsAPJ" +
       "RTop\nwRE/3V+3JWMoEsy5iZBXazUgInurxfgsXK1t4aThj8E4gOowQeXSk4" +
       "W3y09dng442XbGQNZ2Rs+6\ntIv+TU7aZUi2G8ZoC+eZ2LgxPwvgpX4qdDYA" +
       "Y/uLAln7i/KvUIcV89caUPhrOOq5HslOqHUhFkuv\nxtpNeZcf5LNATrZsHc" +
       "hats4feTCdwVjOp8GJ4C0ceLchuZEZtZCIKXRf7EsWvB/4gVcPsBYzeIv9\n" +
       "w2PxGQuehfFeDsb7kNzNwXiPH4yTAVsjw9hYWG/0E07aQ0gecN1KYQE74AfY" +
       "VAC0mQHbXFhgv+Ck\n/RLJox67NCxoP/M5mQhIDJpUWGjPc9Jwj1rwKcMQm3" +
       "q1TcvC+7QfvCBIQGF4Ff94ecMEm/AU82sc\nfbyB5KgxTEhvS7P71lf8dJvj" +
       "UEQGWnMFvSVHt/kOJ+2/kLxtjFvX9HqDmwXtuJ/6nAOQdjBoOwpe\nn97iU+" +
       "SfcLSCG7yCH4FWNEsrjjo94Qc4jA4COxnwnYVtuF97p4UwOB78wohsWBvmLE" +
       "w5d4vxMOHw\n7gaG6YaCYgqVcdIGICkGP4uYnHsh09BCvI1dOaFNAUi3Mmi3" +
       "FhZaNSdtOJIqnVTRkXr2TksL3SC/\n6PYydHsLi24CJ20SkjFpdFn7OC10Y/" +
       "3Osg4wdAcKi47zQmloFpKpRlOjL/S49jK2l6EsuNP8wJ0I\nMA8xuIcKC/ef" +
       "OGkLkdQbQwJjLcLE6/lGkQW4rxtN0q70MAN8uLCAOfGeEMZ7Qit0UoTxuJYc" +
       "lXuG\nhdVXnKcWMLIwf6CwYf4QJ84TwjhPqFkn5YB1paTkXb99jQBRzDCdDB" +
       "xlmI8WFnMHJw1f+Ai1Ge13\nSX7vo1nART/AzwTAbzDgb/gH7hgc5YZB1ZDi" +
       "qAj3DYSSoCKNqWhLxggptMVnWDrwLkP/bmGr/SpO\n2neQfEsnxR3WGj93qx" +
       "9FepkfpNMA4ccM6ceFRbqHk/Z9JNfqpBJXuYQuSRWalfMUlR3x5Nqw1wi6\n" +
       "KvdYuztC1/nFfZLhPllY3Hdx0u5BcquBm+JtVgz4eeC+zWe7DhYbuI3PwrRr" +
       "lQy2bSSnh6Xg1tj7\nb1g2qHHeBVflfMfAayf56ujcdGGtYu2Fh/5y5Amplp" +
       "5P0V/WWgR1sdrhcjKW7ZlPhP3Smtfa76SH\n7qRfLSh3HimWfWJYxkFgVDWn" +
       "pVVJX82ZxVNlrgFXMpkk1p7s+lmz2dsFeBpdRI5FooooxBuW3XOk\n7Oie1J" +
       "mrjc3TA2wZGpbtOLi6vP891+ykm5iZLktsx26x7/26BXWtdS4JWtDDOmkp5G" +
       "lU8+fOqp8+\nZ/aMubPxxXtq6Ac4jeBxJPt0MhAaQbMqC4mOuGSNUq3wVOh+" +
       "nyHFIAv4B90D/n1u4c9w0p5DcgRa\nOO5iima+o2Tvn3K+MJojqhhkLzAHs1" +
       "5g9oftKCftd0h+A5MmxLZUSGm6LLqje8kPupkgLHsJL5j1\nEl7+6Fg8n9ub" +
       "YhqNgYeOcfD/J5I/6qRMh/ki24jr2tDdlq9Cr/sMVgXPZDo581Tr5GOOTv6K" +
       "5L91\nUkF1YgUKeq+XD/zqhS2OBAuwOJKfXr7y1ksYmYRO2vTCQgy918sXfj" +
       "3EOqaXdQX1EOHTOGl4xlS4\nBFw7Rd2sCgktKeBJdr1vJ+FSn+GkINuzHHTf" +
       "s9yn8Q3PHijwURyljENSbboN7D2Vrb1XxzA/6lgN\n6NgaTbAAazT2vd05Z9" +
       "+50OXYGxGezlHpHCS1GCLHN5SMl4m2ZHRB4cl+9LYQ1JBkekueGrfSK51w\n" +
       "3rAK49aX8DydDHF4YlftzPerHbZyEizAyknBtLOWo51mJA027TB/7Kqd1X60" +
       "czZguY5p57r/R9r5\nBkc7OHkIb4SRHdUOW8lwVU3O09Ny9Uo3M9Xc7F81dg" +
       "QJThoyCMvGhANGbW1CvBFmMPZNcrnd8MV+\ncJ8DMNgiR7AAixx57DAL7+Do" +
       "5XIkW3UyALcNwpQ5X530+G0mB5lODp6aZoJpdOQWvpajFTwJLLwL\nZnC0Ld" +
       "jeNKK5nS8Zha/2q4THmBIeK5gSKE4kIgV0OwfsnUhu0kmJIorxlJbPADWc89" +
       "QxHvIWkJOt\nJgQLsJoQphnCFvKCDEH2czR3EMm9fdPcv/p1oieY5k6cWmfy" +
       "OEcfh5Ec0kk/diZQ77XxqM8Rbihg\naMP49KcNxwiXd5KlaLmTFzl6eRnJs+" +
       "BkNbYHC7pV68jD3Np5ro/aoa9wTgMs7ESdUNaJOh7aCbgq\nxg7pT5y0Y0he" +
       "w3c98IwL9i8cAgab3Kdj0HNPGtiTHiez0DOIzTzIje4wChgvoLidcoKp430J" +
       "YB7P\nsdyUxOV4DiO8XV9/1hyfB3TUz6nHlNAjiO2TQodq62fNnj5r1ow5s3" +
       "UywqyiyZFIZEqtNs4608Iy\nvz/01vwAQbmdHR5LPizr/4oY/wtDjL5+6ebP" +
       "or8/aUTlzf9XURYl/dtT8bj9OGPbfXFSldplarZl\nlFbSWHP4C6gPp6Z1Eh" +
       "bZ2RXhk0a2/4Xpoi0beCp2Z8tUBIYQgkx4ax0hm1XbmVWHSCd4ns6zJmX8\n" +
       "85ZW8fwDF4zruab5BuNMHbCt7fhqP55g06/dWtUOZR0jZC/NLOtlcvDBlsd/" +
       "PM88nwUDL2SwrUnb\nvYJxGtdgXk2qZKz7MfrLu5I6Pfh++6PV/372fXceo6" +
       "fIJ/8PtQes1XFnAAA=");
}
