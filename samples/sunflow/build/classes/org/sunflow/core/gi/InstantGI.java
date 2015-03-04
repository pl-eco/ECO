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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
