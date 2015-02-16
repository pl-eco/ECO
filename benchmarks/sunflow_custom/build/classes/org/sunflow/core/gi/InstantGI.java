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
          1166309762000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "2VaqNw4AAA==");
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
          1166309762000L;
        public static final String jlc$ClassType$jl7 =
          ("H4sIAAAAAAAAALVYfWwcxRWfO387js+xyQdu4tjhQnECt0ALghoBtrETpxdi" +
           "5RxXmJbL3N7c3cZ7u8vunH0xOCWRkCMQAQWTxlWwRGUaIAHTiihFFWoqVEig" +
           "/JGqaptKDVX/aQrNH/kDikoLfTO7t1/3gZuqlna8N/PezPv8vTd78jKqMXS0" +
           "WVPlvWlZpRGSp5E98m0RulcjRmRb9LZhrBsk2S9jwxiBubi44fXQp58/k2kJ" +
           "otox1IYVRaWYSqpi7CSGKk+QZBSFnNkBmWQNilqie/AEFnJUkoWoZNCeKFrm" +
           "YqUoHC2IIIAIAoggcBGEXocKmJYTJZftZxxYocbDaB8KRFGtJjLxKOrybqJh" +
           "HWetbYa5BrBDPfs9Ckpx5ryOOm3dTZ2LFH5uszD7g4daflqFQmMoJCkxJo4I" +
           "QlA4ZAw1ZUk2QXSjN5kkyTG0QiEkGSO6hGVpiss9hloNKa1gmtOJbSQ2mdOI" +
           "zs90LNckMt30nEhV3VYvJRE5WfhVk5JxGnRd5ehqajjI5kHBRgkE01NYJAWW" +
           "6nFJSVK03s9h6xj+NhAAa12W0IxqH1WtYJhArabvZKykhRjVJSUNpDVqDk6h" +
           "qL3spszWGhbHcZrEKVrjpxs2l4CqgRuCsVC00k/GdwIvtfu85PLP5fvvOvSI" +
           "slUJcpmTRJSZ/PXA1OFj2klSRCeKSEzGpk3RI3jVWweDCAHxSh+xSXP60Sv3" +
           "3thx5qxJ87USNDsSe4hI4+JCovn82v7uO6uYGPWaakjM+R7NefgPWys9eQ0y" +
           "b5W9I1uMFBbP7HzngcdeIR8HUeMQqhVVOZeFOFohqllNkom+hShEx5Qkh1AD" +
           "UZL9fH0I1cF7VFKIObsjlTIIHULVMp+qVflvMFEKtmAmqoN3SUmphXcN0wx/" +
           "z2sIoSZ40B3wPI3MP/6fou8KuwwIdwGLWJEUVYDgJVgXMwIR1XgCrJvJYn3c" +
           "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" +
           "B3MbxqcYAAA=");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1166309762000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "M6oSRBYAAA==");
}
