package org.sunflow.core.gi;
import java.util.ArrayList;
import org.sunflow.core.GIEngine;
import org.sunflow.core.Options;
import org.sunflow.core.PhotonStore;
import org.sunflow.core.Ray;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class InstantGI implements GIEngine {
    private int numPhotons;
    private int numSets;
    private float c;
    private int numBias;
    private PointLight[][] virtualLights;
    public InstantGI(Options options) { super();
                                        numPhotons = options.getInt("gi.igi.samples",
                                                                    64);
                                        numSets = options.getInt(
                                                            "gi.igi.sets",
                                                            1);
                                        c = options.getFloat("gi.igi.c",
                                                             3.0E-5F);
                                        numBias = options.getInt(
                                                            "gi.igi.bias_samples",
                                                            0);
                                        virtualLights = null;
    }
    public Color getGlobalRadiance(ShadingState state) { Point3 p =
                                                           state.
                                                           getPoint(
                                                             );
                                                         Vector3 n =
                                                           state.
                                                           getNormal(
                                                             );
                                                         int set =
                                                           (int)
                                                             (state.
                                                                getRandom(
                                                                  0,
                                                                  1,
                                                                  1) *
                                                                numSets);
                                                         float maxAvgPow =
                                                           0;
                                                         float minDist =
                                                           1;
                                                         Color pow =
                                                           null;
                                                         for (PointLight vpl
                                                               :
                                                               virtualLights[set]) {
                                                             maxAvgPow =
                                                               Math.
                                                                 max(
                                                                   maxAvgPow,
                                                                   vpl.
                                                                     power.
                                                                     getAverage(
                                                                       ));
                                                             if (Vector3.
                                                                   dot(
                                                                     n,
                                                                     vpl.
                                                                       n) >
                                                                   0.9F) {
                                                                 float d =
                                                                   vpl.
                                                                     p.
                                                                   distanceToSquared(
                                                                     p);
                                                                 if (d <
                                                                       minDist) {
                                                                     pow =
                                                                       vpl.
                                                                         power;
                                                                     minDist =
                                                                       d;
                                                                 }
                                                             }
                                                         }
                                                         return pow ==
                                                           null
                                                           ? Color.
                                                               BLACK
                                                           : pow.
                                                           copy(
                                                             ).
                                                           mul(
                                                             1.0F /
                                                               maxAvgPow);
    }
    public boolean init(Scene scene) { if (numSets <
                                             1)
                                           numSets =
                                             1;
                                       UI.
                                         printInfo(
                                           Module.
                                             LIGHT,
                                           "Instant Global Illumination settings:");
                                       UI.
                                         printInfo(
                                           Module.
                                             LIGHT,
                                           "  * Samples:     %d",
                                           numPhotons);
                                       UI.
                                         printInfo(
                                           Module.
                                             LIGHT,
                                           "  * Sets:        %d",
                                           numSets);
                                       UI.
                                         printInfo(
                                           Module.
                                             LIGHT,
                                           "  * Bias bound:  %f",
                                           c);
                                       UI.
                                         printInfo(
                                           Module.
                                             LIGHT,
                                           "  * Bias rays:   %d",
                                           numBias);
                                       virtualLights =
                                         (new PointLight[numSets][]);
                                       if (numPhotons >
                                             0) {
                                           for (int i =
                                                  0,
                                                  seed =
                                                    0;
                                                i <
                                                  virtualLights.
                                                    length;
                                                i++,
                                                  seed +=
                                                    numPhotons) {
                                               PointLightStore map =
                                                 new PointLightStore(
                                                 );
                                               if (!scene.
                                                     calculatePhotons(
                                                       map,
                                                       "virtual",
                                                       seed))
                                                   return false;
                                               virtualLights[i] =
                                                 map.
                                                   virtualLights.
                                                   toArray(
                                                     new PointLight[map.
                                                                      virtualLights.
                                                                      size(
                                                                        )]);
                                               UI.
                                                 printInfo(
                                                   Module.
                                                     LIGHT,
                                                   "Stored %d virtual point lights for set %d of %d",
                                                   virtualLights[i].
                                                     length,
                                                   i +
                                                     1,
                                                   numSets);
                                           }
                                       }
                                       else {
                                           for (int i =
                                                  0;
                                                i <
                                                  virtualLights.
                                                    length;
                                                i++)
                                               virtualLights[i] =
                                                 (new PointLight[0]);
                                       }
                                       return true;
    }
    public Color getIrradiance(ShadingState state,
                               Color diffuseReflectance) {
        float b =
          (float)
            Math.
              PI *
          c /
          diffuseReflectance.
          getMax(
            );
        Color irr =
          Color.
          black(
            );
        Point3 p =
          state.
          getPoint(
            );
        Vector3 n =
          state.
          getNormal(
            );
        int set =
          (int)
            (state.
               getRandom(
                 0,
                 1,
                 1) *
               numSets);
        for (PointLight vpl
              :
              virtualLights[set]) {
            Ray r =
              new Ray(
              p,
              vpl.
                p);
            float dotNlD =
              -(r.
                  dx *
                  vpl.
                    n.
                    x +
                  r.
                    dy *
                  vpl.
                    n.
                    y +
                  r.
                    dz *
                  vpl.
                    n.
                    z);
            float dotND =
              r.
                dx *
              n.
                x +
              r.
                dy *
              n.
                y +
              r.
                dz *
              n.
                z;
            if (dotNlD >
                  0 &&
                  dotND >
                  0) {
                float r2 =
                  r.
                  getMax(
                    ) *
                  r.
                  getMax(
                    );
                Color opacity =
                  state.
                  traceShadow(
                    r);
                Color power =
                  Color.
                  blend(
                    vpl.
                      power,
                    Color.
                      BLACK,
                    opacity);
                float g =
                  dotND *
                  dotNlD /
                  r2;
                irr.
                  madd(
                    0.25F *
                      Math.
                      min(
                        g,
                        b),
                    power);
            }
        }
        int nb =
          state.
          getDiffuseDepth(
            ) ==
          0 ||
          numBias <=
          0
          ? numBias
          : 1;
        if (nb <=
              0)
            return irr;
        OrthoNormalBasis onb =
          state.
          getBasis(
            );
        Vector3 w =
          new Vector3(
          );
        float scale =
          (float)
            Math.
              PI /
          nb;
        for (int i =
               0;
             i <
               nb;
             i++) {
            float xi =
              (float)
                state.
                getRandom(
                  i,
                  0,
                  nb);
            float xj =
              (float)
                state.
                getRandom(
                  i,
                  1,
                  nb);
            float phi =
              (float)
                (xi *
                   2 *
                   Math.
                     PI);
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
              state.
                getPoint(
                  ),
              w);
            r.
              setMax(
                (float)
                  Math.
                  sqrt(
                    cosTheta /
                      b));
            ShadingState temp =
              state.
              traceFinalGather(
                r,
                i);
            if (temp !=
                  null) {
                temp.
                  getInstance(
                    ).
                  prepareShadingState(
                    temp);
                if (temp.
                      getShader(
                        ) !=
                      null) {
                    float dist =
                      temp.
                      getRay(
                        ).
                      getMax(
                        );
                    float r2 =
                      dist *
                      dist;
                    float cosThetaY =
                      -Vector3.
                      dot(
                        w,
                        temp.
                          getNormal(
                            ));
                    if (cosThetaY >
                          0) {
                        float g =
                          cosTheta *
                          cosThetaY /
                          r2;
                        if (g >
                              b)
                            irr.
                              madd(
                                scale *
                                  (g -
                                     b) /
                                  g,
                                temp.
                                  getShader(
                                    ).
                                  getRadiance(
                                    temp));
                    }
                }
            }
        }
        return irr;
    }
    private static class PointLight {
        Point3 p;
        Vector3 n;
        Color power;
        public PointLight() { super(); }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "Yzdl+/kXdMD9tkcOAAA=");
    }
    private class PointLightStore implements PhotonStore {
        ArrayList<PointLight> virtualLights =
          new ArrayList<PointLight>(
          );
        public int numEmit() { return numPhotons;
        }
        public void prepare(BoundingBox sceneBounds) {
            
        }
        public void store(ShadingState state,
                          Vector3 dir,
                          Color power,
                          Color diffuse) {
            state.
              faceforward(
                );
            PointLight vpl =
              new PointLight(
              );
            vpl.
              p =
              state.
                getPoint(
                  );
            vpl.
              n =
              state.
                getNormal(
                  );
            vpl.
              power =
              power;
            synchronized (this)  {
                virtualLights.
                  add(
                    vpl);
            }
        }
        public void init() {  }
        public boolean allowDiffuseBounced() {
            return true;
        }
        public boolean allowReflectionBounced() {
            return true;
        }
        public boolean allowRefractionBounced() {
            return true;
        }
        public PointLightStore() { super(
                                     ); }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "E7MX+ZeO/wDQbFfutxgAAA==");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "t5A3ka7+AyA3pplUFgAA");
}
