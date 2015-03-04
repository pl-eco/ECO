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
                                                           getPoint();
                                                         Vector3 n =
                                                           state.
                                                           getNormal();
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
                                                                     getAverage());
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
                                                           copy().
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
                                                                      size()]);
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
          getMax();
        Color irr =
          Color.
          black();
        Point3 p =
          state.
          getPoint();
        Vector3 n =
          state.
          getNormal();
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
                  getMax() *
                  r.
                  getMax();
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
          getDiffuseDepth() ==
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
          getBasis();
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
                getPoint(),
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
                  getInstance().
                  prepareShadingState(
                    temp);
                if (temp.
                      getShader() !=
                      null) {
                    float dist =
                      temp.
                      getRay().
                      getMax();
                    float r2 =
                      dist *
                      dist;
                    float cosThetaY =
                      -Vector3.
                      dot(
                        w,
                        temp.
                          getNormal());
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
                                  getShader().
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
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1166309762000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAJUXfYgUVfx3u/fhfcR9qHenqafnaZrnjlJGdYJe152ujt5y" +
           "p6ee2vlu5u3e6OzM\nOPP2bj0llCA/i6SCgjQJQZMsocICMcMsUgILMhAUoj" +
           "8KyiACM+qPfu/N7NfsruHCvHn73u/7e967\nA2WODdMUJ8R2WdQJdfVHiO1Q" +
           "tUsnjrMOj4aUK2WVkVOrDTMAJTIENJVBraw4kkoYkTRVCj/bkbRh\ngWXqu2" +
           "K6yUI0yULb9SUevVXykjyCG46fb9h3srQlAGUy1BLDMBlhmml06zTuMKiTt5" +
           "NRIiWYpkuy\n5rAOGR6iRiLeZRoOIwZzdsLzEJSh3FI4TQaz5BRzCZlLFrFJ" +
           "XBLspYhgixQm2pQRzaBqZ5odYrbn\nYqLYHl5fPjQSmcAvB1AdIQFqPTOtta" +
           "ttnqpW8PTAE3tOvBuE2kGo1Yx+TkxBTRjyG4SaOI0PU9vp\nVFWqDkK9Qana" +
           "T22N6Nq44DoIDY4WMwhL2NTpo46pj3LABidhUVvwTB3KUKNwneyEwkw7baOo" +
           "RnU1\n9a8sqpMYqt2YUdtVt4efo4JVGgpmR4lCUyilOzQDPd7ix0jr2LYaAR" +
           "C1Ik7ZiJlmVWoQPIAG15c6\nMWJSP7M1I4agZWYCuTCYWpQot7VFlB0kRocY" +
           "NPvhIu4VQlUKQ3AUBpP9YIISemmqz0tZ/lnQePfA\n6bcuLhexXapSRefyVy" +
           "HSDB9SH41SmxoKdRHvJUKvhTclpgUAEHiyD9iF6Zxzfr38y2ctLszDBWB6\n" +
           "h7dThQ0pa4+29O1eYUKQizHBMh2NOz9Hc5EOEe+mI2lh1jamKfLLUOryUt+X" +
           "m/aeob8GoCoM5Yqp\nJ+IYR/WKGbc0ndorqEFtwqgahkpqqF3iPgwVuJcx5N" +
           "3T3mjUoSwMpbo4KjfFfzRRFElwE1XiXjOi\nZmpvETYi9kkLACbiA834HAT3" +
           "J94MFoUkJ2FEdXNMcmxFMu1Y+r9i2lSKaVLYTfIV4RCPHIuBLI2Y\ncSoRhR" +
           "iaYSII5qpiLlTpKH8/IL0kl7FhrKSEFz1/8uoY9ytNXaX2kHLqp6t7ulcfPO" +
           "AGBg9mTzsG\n85BNyGMT4mxCMS2UZtMWMTGJZC02wqCkRDCaxDm7PkIL78Bc" +
           "xapWM79/66ptB1qDGBzWWCmah4O2\nokqeON2K2ZVJaJe+glHV/M7m/aF7p5" +
           "a5USUVr7sFsauvn7124s+aRwMQKFwUuZpYlqs4mQivpOli\n1+ZPo0L0fz+0" +
           "5sMb127NyyQUg7a8PM/H5Hna6neIbSpUxcqXIX9ySm1wAwwcDUApJj8WPCE/" +
           "1pIZ\nfh45+dqRqn1clwoZqqOmHSc6v0oVrCo2YptjmRMRKXV8meQGDXekT0" +
           "BRNu+9UL7ohwvVV4TGqQpb\nm9XD+ilz87U+EwfrbErx/NYbkVdfv7N/swgC" +
           "LwoYVFi2NooZmkScuRkczFQdqwV3Utt6I26qWlQj\nwzrl0fRv7ZzFH//2cp" +
           "1rdh1PUl5r/38CmfMpz8Dea8/9NUOQKVF4p8jokQFz1ZmYodxp22QXlyO5\n" +
           "77vpb35FjmEhw+LhaONU1AMQqoEwZEjYd75YF/ruFvGlFWm3FwnrAn15SNlz" +
           "Jtaa2Pn1p0LqapLd\n4LP9sIZYHa5XBW9eo9rAW3LqFL+dbPG1kbugyZ++K4" +
           "kzgsQev7R2S51+6R9kO4hsFWyaTq+N1SOZ\n42oPuqzi5ueXG7d9G4RAD1Tp" +
           "JlF7iEgAqMTIo84IFp6ktWy5EKNubAJfhV1ASDvVs1Iy618QhZtf\nPP97eO" +
           "vPpM7QcPtp+WrvMWGloplfoPP56IxfXH/83jfstqCTSUGOPSuZX1VxXMrgPn" +
           "ljtL783Nvx\nAFQMQp3iDXQDRE/wbBjE+cNJTXk49OXc584SbuPsSJeYaf70" +
           "z2LrT/5MNcc9h+b7Gl9kcPtDPT6H\nvMg45I+MEhCbZQJltljnWp6XsPRb4q" +
           "SJQVN2u4hjmwyJDvGYW1T4upgvy13PLikaAU/nytaAz2FP\ntsNFZFvJl04U" +
           "xkgJ05wnzADlUeiXJvyA0kzC54gnzZEi0vR60pRZ5hi1C5pHi+NYx+uUafsE" +
           "itxH\noGS+CwJ8P49BuSMG7mRODmHaTC82Eopxdv/GP2peJF9sdVtsQ+6Y1Y" +
           "2fIj/vukwfWfrSjwWmg3Jv\npM9myKAqMxWkFJ9+3zECZWzO+7hwB2JFvrl7" +
           "y135+79FE0wPrdU4OUYTup4d3Vn7csumUU1EebUb\n65Z4YZGfWEASBoGYJk" +
           "QdduGi+GHmh2NQyl/ZYIhYnQWGzczbZQNhwgYRiG/j6TypEyWTZ3fIze5c\n" +
           "p/GOMzun2InvPM82axLul96QsvHs5pnJw+teEQNOGX4hjo+LkR6/UNymn55n" +
           "ZhWllqJ1Hc59MHDh\n/acCXnvKGQdyIiodp5Pulzg2tBTuxt1xi4n+Of5J00" +
           "dLTx2/HRADwX9Z3aumng8AAA==");
    }
    private class PointLightStore implements PhotonStore {
        java.util.ArrayList<PointLight> virtualLights =
          new java.util.ArrayList<PointLight>(
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
              faceforward();
            PointLight vpl =
              new PointLight(
              );
            vpl.
              p =
              state.
                getPoint();
            vpl.
              n =
              state.
                getNormal();
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
        public PointLightStore() { super();
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1166309762000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAK0ZC2wUx3V8Ptv4AzbmY/7GxiR8zB12wAEcCQw2YDjAtY1J" +
           "DPQy3ps7L+ztbnbn\nzoeDCGkjoKFJIElRiQKhKZUJJAU1iWiqlIAIbRrSiE" +
           "QKrZBC2lKlaRMqVZVSqlZp38zu3X7uE4pr\naed2Z9578/7z3vjlm6hA19AU" +
           "QffRHSrRfSu6OrCmk9AKCet6N0wFhUsFxR1Da2XFg/ICyCOGKCoP\nCLo/hC" +
           "n2iyF/e2tzQkNzVUXaEZEU6iMJ6tsmLTTprQksTCO46ejZykePe6s9qCCAyr" +
           "EsKxRTUZHb\nJBLVKaoIbMNx7I9RUfIHRJ02B9BIIseiKxRZp1im+kNoF8oP" +
           "oEJVYDQpqgkkN/fD5n4Vazjq59v7\nO/i2QGGMRigWZRJqSW0HmPVOTGDbxO" +
           "tMhwYiI9hiD4jDOQCpp6ekNqRNE1XNP9HTtPPYS/movBeV\ni3IXIyaAJBT2" +
           "60VlURLtI5reEgqRUC8aLRMS6iKaiCVxkO/aiyp1MSJjGtOI3kl0RYozwEo9" +
           "phKN\n75mcDKAygcmkxQSqaCkdhUUihZJfBWEJR0Ds8ZbYhrgr2TwIWCICY1" +
           "oYCySJ4t0uymDxajdGSsa6\ntQAAqEVRQvuV1FZeGcMEqjRsKWE54u+imihH" +
           "ALRAicEuFE3KSpTpWsXCdhwhQYomuOE6jCWAKuaK\nYCgUjXODcUpgpUkuK9" +
           "nsM3f8l/tOPH9uGfdtb4gIEuO/BJCmuZA6SZhoRBaIgXgr5nu2/YHYFA9C\n" +
           "ADzOBWzAtMw8uzHw2VvVBszkDDAb+rYRgQaF9QerOx9epaB8xsYIVdFFZnyH" +
           "5DwcOsyV5oQKUTs+\nRZEt+pKL5zt/8cDuk+RzDyppR4WCIsWi4EejBSWqih" +
           "LRVhGZaJiSUDsqJnJoBV9vR0XwHgCXN2Y3\nhMM6oe3IK/GpQoV/g4rCQIKp" +
           "qBjeRTmsJN9VTPv5e0JFCJXBgxbB8xQy/vgvRfN9fj0mhyVlwK9r\ngl/RIq" +
           "lvQdGIPyL6240gX9XuY56jUhTw9ytR4scClkVZARCIVUGZFyJx9vs/0kswHi" +
           "sH8vJY0nMH\nrwR+v1qRQkQLCkM33t3ZtvY7+wzHYM5sSkfRPNjGZ27jY9v4" +
           "IqIvtU1dhwJBFBAj/bQLApGgvDy+\n21i2vWEoUPN2CFhIbWWzu7aueXBfbT" +
           "54iDrgBR0x0FqQy+SpTVBWWFFtbCKAa014cfNe362hpYZr\n+bMn34zYpVde" +
           "uXzs72VzPMiTOTMyWSE3lzAyHSydpjJenTuWMtH/6+PrXr16+eNZVlRRVJcW" +
           "7OmY\nLFhr3VbRFIGEIP1Z5I9PLM/fhHoOepAXMgBkPc4/JJRp7j0cQducTI" +
           "BMlqIAKg0rWhRLbCmZtUpo\nv6YMWDPcXSrYMNbwHGZIF4M8d976duH837xZ" +
           "eolLnEyz5baDrItQI2hHW37QrREC8x9/v+OZ793c\nu5k7gekFFBWpmhiHME" +
           "0Azl0WDoSrBCmDGaluoxxVQmJYxH0SYd707/KZDa9/8WSFoXYJZpJWq/96\n" +
           "Atb8xOVo9+Vv/mMaJ5MnsOPCksMCM8QZY1Fu0TS8g/GRePTDqYd/iY9ANoMM" +
           "oouDhCcFxEVDXJE+\nrt/ZfJznWpvPhlqgXZ/FrTMczkFh58lIbeyhX73BuS" +
           "7F9lPebod1WG02rMr3HgObLkbm4EhWbHWc\nysbxzARV7vBdjfV+ILbg/Pot" +
           "FdL5f8G2vbCtACenvkGDFJJwmNqELii6duHi+Ac/yEeelahEUnBo\nJeYBgI" +
           "rB84jeD9knoS5dxtmoGBjBRq4XxLmdZGopYftiCWN29vhfyc5/K3SCffUnAu" +
           "9uOMK1lDXy\nMxx/LjqD5zYevfVrep3TsUKQYdck0lMr1EwW7qKr8dGFZ16I" +
           "elBRL6oQzKquB0sxFg29UIToyVIP\nKj/HurOgME7P5lSKmeIOf9u27uC3Uj" +
           "q8M2j2XubyDKZ/tAaeA6ZnHHB7Rh7iL0s5ygw+3qWaVqJo\nZFzUaAxL/DTQ" +
           "wVCTbXV2V6xPp7ZSZOGye+t/snrLRZ6Ui6ECxPp6i0eou9lbHih3TnZru2lq" +
           "F7sv\nkRvjDhnHhNNNeJlsorrxDs3+PD53/reOcF68fVjnbJSADnUGSdH07C" +
           "U3p2XE2EhDk/+BPwTPV+xh\nquETvCCYaEVmKn34eFmvqoaXV9GMOYYtrQWN" +
           "Vro1GhSqtqgbe9B3txhC13yNtoLCpPd+dnXTtR6V\n+3J5XIQqioS6zUrfmf" +
           "2sk7vZUf1n1GdQ+GPt1uvPeT85zMs6Q3WM70bInOy3yQz0slSgs2Cusklk\n" +
           "csET9F/ePzz2MW2fwm1SwP0j3TdmZpHWTigoPPnZ8q92B7bf7WGRUMKCB2tQ" +
           "D0LB6cvWENkJ1HXD\nWytgQVCNMrChsOfuY7pJZWo2dXxD3ZSNNusR3ae8O1" +
           "4hWQ4QbbkSk7msNc4ILompqn2Ve9+o4Xmf\nyv4oymtLumEFd0OWeXxG5qGo" +
           "hJuhsXHRvQ1qEmzW7ZaHjjTO0kWpQa1p8T2LF5quzZpsnxgy02/r\nhyv7To" +
           "blkyEPL1Z4b9dic4RiPmPLGvmKqrMuwtaum5TqNqg68+GRtk3aW3eeWVM24g" +
           "f793D6plsV\n2zoS87sojrX19pLY5HxRY1PjAop6/p8V+5KGpvn1DU3zGhdD" +
           "8u9e3d7lcyRVxgJ2Z9pd0BulK4+J\nbOZxVMlzwyjLom1yLGpfBKG8nW0trU" +
           "bVx8YGNiwzbLYw0xGdSD8E8tj7LOdxbbrJlDQ36ehXqCLz\npoFVHFOzda28" +
           "4957/9/K9uC3txpJrtLZCTJh/rTjIrn7vid+n6GBKTRvHSyuith+jq5jHe/m" +
           "rUP7\n8ZdOnaUfzF1i7JfjCHIjzllybLB6yen9d9BrVLs04CY9Oj75G/n94j" +
           "vcW80aIO2iwonU7MobwE9M\nk7sd5//01Plfzgw7BZ6nTc942n3+W+7htDso" +
           "WY31SaKQyFHoPpZjbQ8bdkEbAKZsi4oUDDTBfkmn\niTApxnljc2NP7c/f2f" +
           "jC3kynvPMmzo4VFB755Hfb85+60GfguSs+F/DBacc/ffVG51jDn4xboRlp\n" +
           "FzN2HONmiEtTrjKPrsm1A4d+e27Ny7s6r3OOGF6conzRvPHj8fdIjvhzZFP+" +
           "Mei05AJ4DpmWPJTR\nkmzY7bJKXo64jWLa7+PHDhx1y5UEp3Ioh1mfY8MzvL" +
           "sjUDAYbeY+s9R5AnJOXBFDlrjPDkfcHngO\nm+Ievm1xvZyiNynu1LQ01dWP" +
           "mbTsJpMkoSakKaWHsL7mniRAlR1AjOIIYY2oeU+ZY53z+aMcCj3N\nhhcpVF" +
           "csb7KP5y31/XA46psEz3FTfcdvW3123s7mWHuDDa9RdokmUhfbrw+H7Xp4hk" +
           "y2h+6I7Qs5\n1i6y4RyU5FgCS7WK4XBMJywAoHpz+3JRn6JIBMuWYG8NRzA/" +
           "PKdMwU7dkWDv51i7wobLUO1zwTpJ\n2LzlMGVjq5csQd4briCnTUFO35Egv8" +
           "2xdo0NH9kE0XAOQa7eriBQnpa7bjazpwh7wcsOrrT/khg3\n+0Lg2sNbvgx8" +
           "9E9+kZe6fS+FgjMckyR7h257L4TUGRa5rKVGv67ynxvglRk4ocgTETmrfzDg" +
           "PoVK\n3g0HcSgkJTLB/gxVrQ0M3Nl8swN9AecTALHXm6kGIK1PcNb57NZsRt" +
           "bOcV3M+JdVULj/lc3TE/u7\nDxj9HnQlg4OMDHRXRcbFZapOqslKLUnrCjpz" +
           "uufNHy9OnquOK800f20wVnN4BtRmmW8U26Iq5W3U\n4E+rXrtv6Oh1D7/U/C" +
           "83WM1OZxwAAA==");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1166309762000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALUZa2wUx3l8NjZ+ED8Am7fBmBBedzS8MVJijDEHB3ZtXjGh" +
       "Zrw3d17Y293szpnD\nIJKICmhQ06CmUqMWQiskHg0JUlLRqpRCk7QpKFKSqo" +
       "mUNqQtalqpTdWoUkrV/uj3zeze7e09eLS1\ndLOzM99jvvc365c+JaNsi0xR" +
       "7CDfZzI72NHXQy2bRTs0atubYWlAeXNUZc+ZDboRICURElCjnNRG\nFDsUpZ" +
       "yG1GgovKYtZZF5pqHti2sGD7IUD+7Wljj01keW5BDcdvJSw9Ony5oDZFSE1F" +
       "JdNzjlqqF3\naixhc1IX2U2HaSjJVS0UUW3eFiFjmJ5MdBi6zanO7SfIQVIa" +
       "IeWmgjQ5mRFxmYeAecikFk2EBPtQ\nj2ALFMZajFNVZ9H2NDvAnJ+NCcd28H" +
       "pzoYHIaNzcCuKIE4DU09NSS2lzRDVLz25deuDUuVJS209q\nVb0PiSkgCQd+" +
       "/aQmwRKDzLLbo1EW7Sf1OmPRPmapVFNHBNd+0mCrcZ3ypMXsXmYb2jACNthJ" +
       "k1mC\np7sYITUKymQlFW5YaR3FVKZF3bdRMY3GQezGjNhS3LW4DgJWqXAwK0" +
       "YV5qKU7VF1sHizHyMtY+sG\nAADUigTjQ0aaVZlOYYE0SFtqVI+H+ril6nEA" +
       "HWUkgQsnkwoSRV2bVNlD42yAkwl+uB65BVCVQhGI\nwsl4P5igBFaa5LOSxz" +
       "7zGj8/evbbVx4Vvl0WZYqG568CpGk+pF4WYxbTFSYRbyeDz4cfS04JEALA\n" +
       "433AEqZ91qUtkT/9pFnCTM4D0z24myl8QNl0vLl3f5dBSvEYo03DVtH4WZKL" +
       "cOhxdtpSJkRtY5oi\nbgbdzau9P3vsqfPszwFSFSbliqElE+BH9YqRMFWNWV" +
       "1MZxblLBomlUyPdoj9MKmAeQRcXq52x2I2\n42FSpomlckO8g4piQAJVVAlz" +
       "VY8Z7tykfEjMUyYhpAJ+ZC78xhL5J56cLAyG7KQe04y9IdtSQoYV\nT78rhs" +
       "VCcTUUlkHeFQ6i55icREJDRoKFqEJ1VTcABGJVMRZE2TA+75FeCs/YsLekBJ" +
       "OeP3g18Pt1\nhhZl1oBy5tb1A50bvnJUOgY6syMdJ1OBTdBhE0Q2wbgaTLMh" +
       "JSWC+jhkJw0Dat0DAQqprGZO3871\nu462lIJHmHvLQCcI2gJyOGfoVIyOTB" +
       "RLogq40oTv7jgSvH3mEelKocLJNi929TsXbpz6e83cAAnk\nz4QoG+TiKiTT" +
       "g+kzneFa/bGTj/5fn9n46vs3PnooE0WctOYEdy4mBmeL3wqWobAopLsM+dMT" +
       "a0u3\nka3HA6QMIh6ynDg/JJBpfh5ZQdrmJjyUpSJCqmOGlaAabrlZqooPWc" +
       "bezIpwjzoxR4+tRq8dB79J\njhuLJ+6ON3FslO6E1vZJIRLq7UPlCz+4XP2m" +
       "UIube2s91a2PcRnJ9Rln2WwxBusffbPn69/49MgO\n4SmOq3AoeclBTVVSgP" +
       "JgBgVCWIM0goZs3aInjKgaU+mgxtDj/l076wvf/8uzddI0Gqy4lp1/ZwKZ\n" +
       "9YmryVM3vvSPaYJMiYIlJCNGBkxKMzZDud2y6D48R+rp96a+8HN6AjIcZBVb" +
       "HWEiUZQ4QYCHaoJ8\nlxNa3aY4llB0SIDNFWMQLSGQidhbhEMLMJ9fIDbyVP" +
       "QB5cD5eEvyiV/8UIhVTb2tgddOG6nZJl0D\nh5mo/iZ/eK+j9hDALb666fE6" +
       "7eq/gGI/UFSgktrdFqSUVJaVHehRFR9ee71x17ulJLCWVGkGja6l\nIkBIJX" +
       "gms4cgG6XMRx4Vzle3dzSOQmQilDDJUUDK84bt3JzC+WEt9gOZ0BoYnH82cr" +
       "37hFBAwcyQ\npxz66Ixc2XLy9tv8pqCTCVHEnpHKTbXQQ2Vwl78/XF9+8cVE" +
       "gFT0kzrF6fK2Ui2JgdAPTYnttn7Q\nCWbtZzcYspq2pVPQFH968LD1J4dMio" +
       "c5QuO8xpcPalDbk52c4OaGrHxQQsSkXaC0inF2OnorTEsd\nptj5kSroZ3uG" +
       "DA7CgL0meNtvS01AGR8WKezW4ZYfv7XlxSMy7RcxaxbWgPLkx7/dU/q1a4MS" +
       "z287\nH/Dxaac/efVW7ziZImS/NzOn5fLiyJ5PKKfWxGiYUYyDgH5j3oyXDv" +
       "bedE7UkN25dII2/rjvdTZ7\n1Vd/l6fglkJXKhMtjotxWC1dflnB0FiVbbQJ" +
       "8BvvGG18AaP14tABZoLTgOMJAht8fPvukS86SKPD\nt7EA320O3xJF4HaZkk" +
       "eEo4ca1C/69vsQvck5QlOBIwx4RF+t0ryi77pHvnMc3u4Z8vGNOnzHDKsW\n" +
       "T1ItosaHOEbEOE9EiBqCjnTuuTVje1fsOCTamEq4I1F7UyZq4WaKsxJwr1mF" +
       "AyVNbECZvfPS365d\nYbNFqh6t2pAd2q14nvuCB+czep5t/CB2UrQiZYPUln" +
       "nCf9HKvUdlXY9E5DyQra7GYuoSoHEc9qTc\nWvlQ0Ta0tceAM0h9uvXSW2xX" +
       "SWKOHczcfBVA91N1Ki5EcyBllWtMj8smX7iG5vgpUg5IFPdosgHA\nnAx3LU" +
       "Nn2Eu4e7IvVo1g+p4Lm6mcM1pkalZXvFGoL1M0njn3vUv83XkrZT6ZW9jgfs" +
       "S5K0+NNK98\n5dh99MLNPr/wk64fnvzF0iH1rYC4AMsalHNxzkZqy648VXCe" +
       "pKVvzqo/06WphM5xeLBIG/R0kb1D\nODwJZlXQJtKEoOfm/H1gZ8LkonMb+U" +
       "HTa6vOnLyJmjZTcImrFpH38NIVi5YtBfwGCFX8FhRUo05X\nsOa9tYPnY/r5" +
       "qFBElYiMds1JKyBlpVjxhG6pYdp42fV8VXIotXabNt4SxniYhNccuLi+ZvR3" +
       "jh0W\n9J24r/RcnJ33imFqbcoUEnwc4WTL//I+uXL5kvmLFi5YvBw64s3rwn" +
       "3BrEQW3JHaCeULRuT97EG4\nsucqC0V04p40CG9+IBM+WBm9m1ANy3o729fc" +
       "RUw/nyemcW7LgMaRp4N5d24w4+swDqnc6MT3/Rm3\n3H0nt/xWkb0TOLyAw5" +
       "flSXA87HO0ZQL1aHpt+cOLl634P9lyEXSVubZE/if9lQoXj/vqJCtSJ1MZ\n" +
       "RU3KvvlMzMnmXeFOPQ65CfurqYW+YIne6sj2z2oO0zd2BhyNbuRwfTDMBRob" +
       "ZpqHWam0W/b1din8\nWhwPa/HXHSFaPuP67m25n0T6hmhU1eP4vZMJMheLuM" +
       "BrOFzgpD7OeJdmDFKtF7AxP7oMmrwM1ASN\nM0xWzndOofaX79SeuJelfEpo" +
       "dRThKuS+lNCYqwSF6VL6K0Wk/ykOP+L4PU3l/hawYtAwNEb1jKCX\n/xtBI/" +
       "DrdgTtvmtBfeW9uLXvZDLB5u0i+vglDtch1MAbwtB63asn3LhbBUFTU5lOAV" +
       "gKJ+R8zpef\noJXIh/sf/zzyq3/Kts/9TFwNJSeW1DTv1dEzLzctFlOFSNXy" +
       "ImmKx2+gQcrTu3ESiKvifL+WcB9z\nUueHAy/Bhxfs95AUPWDgMs7MC/QHKE" +
       "MAhNNPTFeVdZk6I6/OqSz1oEZmZjVW4j8rbvOTlP9bGVC2\nX9gxPXVs83Oi" +
       "o4Lmgo6MIJkqKMDyi1u6gZpRkJpL6x1y8ZWtl19e4aYy8cFlXCqTYrOce7Hc" +
       "LWxu\nUUrM/wAFzzpq5RoAAA==");
}
