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
          1170616230000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "VBUAAA==");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1170616230000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAALVcDZQVxZWu1z0zzAwDMwx/I7/DMKD8OA9QUYMbGAYGhww/" +
       "mRnYOKhDT7+emZZ+r5vufjCgRMQT4JiDMVkMaAgnm+BPjCi7CWs8HrOsHleJ" +
       "6+ZIPJtljbpuslkXQ1aTY/yJiXtvVfV7/X66pgeaOefV9Ouquve7Vbdu3bpV" +
       "9R4/T0odm8yzTGNHv2G6Tdqg23SbcU2Tu8PSnKbV7desV2xHS7QYiuN0wbse" +
       "teFE9R//dN9AjUTKuslYJZUyXcXVzZTToTmmsU1LtJPq7NuVhpZ0XFLTfpuy" +
       "TYmnXd2It+uOu6SdjPRVdUljuwchDhDiACFOIcSbs6Wg0igtlU62YA0l5Tpb" +
       "yZdJrJ2UWSrCc8mMXCKWYitJTmY9lQAolOP3jSAUrTxok/qM7EzmAoHvnxc/" +
       "eOjWmr+XSXU3qdZTnQhHBRAuMOkmVUkt2avZTnMioSW6yZiUpiU6NVtXDH0n" +
       "xd1Nah29P6W4aVvLNBK+TFuaTXlmW65KRdnstOqadka8Pl0zEt630j5D6QdZ" +
       "J2RlZRK24nsQsFIHYHafompelZIteirhkun5NTIyNn4BCkDVEUnNHTAzrEpS" +
       "CrwgtazvDCXVH+90bT3VD0VLzTRwccmkQKLY1paiblH6tR6X1OWXW8+yoFQF" +
       "bQis4pLx+cUoJeilSXm95Ouf82tvuPf21I0piWJOaKqB+Muh0rS8Sh1an2Zr" +
       "KVVjFavmtn9TmfDsfokQKDw+rzAr89Qd7y+bP+3US6zM5CJl1vXepqluj3qs" +
       "d/SrU1rmXC8jjHLLdHTs/BzJqfqv5zlLBi0YeRMyFDGzycs81fHPN+1+THtX" +
       "IpVtpEw1jXQS9GiMaiYt3dDsVVpKsxVXS7SRCi2VaKH5bWQEPLfrKY29XdfX" +
       "52huGykx6Ksyk36HJuoDEthEI+BZT/WZ3rOluAP0edAihIyCD1kKn1rC/uh/" +
       "l9wc3+CAuscVVUnpKTMOyqsptjoQ11SzpxdadyCp2FucuJp2XDMZd9KpPsPc" +
       "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");
}
