package org.sunflow.core;
import org.sunflow.core.gi.GIEngineFactory;
import org.sunflow.core.photonmap.CausticPhotonMap;
import org.sunflow.image.Color;
import org.sunflow.math.Point3;
import org.sunflow.math.QMC;
import org.sunflow.math.Vector3;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
class LightServer {
    private Scene scene;
    private LightSource[] lights;
    private Shader shaderOverride;
    private boolean shaderOverridePhotons;
    private int[] maxDiffuseDepth = new int[] { 1, 2, 4, };
    private int[] maxReflectionDepth = new int[] { 4, 8, 16, };
    private int[] maxRefractionDepth = new int[] { 4, 8, 16, };
    private CausticPhotonMapInterface causticPhotonMap;
    private GIEngine giEngine;
    private int photonCounter;
    private CacheEntry[] shadingCache;
    private float shadingCacheResolution;
    private long cacheLookups;
    private long cacheEmptyEntryMisses;
    private long cacheWrongEntryMisses;
    private long cacheEntryAdditions;
    private long cacheHits;
    private static class CacheEntry {
        int cx;
        int cy;
        Sample first;
        public CacheEntry() { super(); }
        final public static String jlc$CompilerVersion$jl = "2.5.0";
        final public static long jlc$SourceLastModified$jl = 1415733647000L;
        final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJVXe2wURRj/etcHfZiWAuUhUlvqA4FbSMQIJYFaixQO2rS0" +
                                                       "SkHrdHfuurC3u8zO\ntddCUGMiqPFB1EQTecSQgAbUBA2a+IAovoiJmogJCa" +
                                                       "ghURPFxJggRv/wm5m9116vhktubm7me873\nfb/55thlKPMYzNW9CB9zqRdp" +
                                                       "7+0mzKNGu0U8bxMuDepnyiq7j6y3nRCURCFkGhxqo7qnGYQTzTS0\nzrtbUw" +
                                                       "wWuo41FrccHqEpHtlmLfPlrYsuKxB474GT9Y8cLm0MQVkUaoltO5xw07E7LJ" +
                                                       "rwONRFt5ER\noiW5aWlR0+OtUbiO2slEu2N7nNjc2wG7IRyFclcXMjk0RdPK" +
                                                       "NVSuuYSRhCbVa91SLUqYxignpk2N\ntow65FyUz4lm+3w9hdQoZIrY7Ed3pA" +
                                                       "Xo9Y0Zr5W3Ba664aP9d+w69GoYageg1rR7hTAdPeGobwBq\nEjQxRJnXZhjU" +
                                                       "GICpNqVGL2UmscxxqXUA6j0zbhOeZNTroZ5jjQjCei/pUiZ1phejUKMLn1hS" +
                                                       "5w7L\nnFHMpJaR/lcWs0gc3W7Iuq3cXSPW0cEqEw1jMaLTNEvpdtPGiDcGOT" +
                                                       "I+tqxHAmStSFA+7GRUldoE\nF6BexdIidlzr5cy040ha5iRRC4c5RYWKs3aJ" +
                                                       "vp3E6SCHWUG6brWFVJXyIAQLhxlBMikJozQnEKWc\n+CxsuLL36MsfrJa5XW" +
                                                       "pQ3RL2VyHTvABTD41RRm2dKsarycjznZuTc0MASDwjQKxo2m462Rf95cNG\n" +
                                                       "RXP9BDRdQ9uozgf1jfsae3be40BYmDHFdTxTBD/Pc1kO3f5Oa8rFqm3ISBSb" +
                                                       "kfTmqZ5PNj/8Gv01\nBFWdUK47VjKBeTRVdxKuaVF2D7UpI5wanVBJbaNd7n" +
                                                       "dCBc6jmPJqtSsW8yjvhFJLLpU78j8eUQxF\niCOqxLlpx5z03CV8WM5TLgBM" +
                                                       "wy/Mwu9yUB/5y0GLaF7SjlnOqOYxXXNYPPNfdxjFco8PcyyAEcoi\nInFcDu" +
                                                       "u1YSdBNaIT27QdLW5iqerOYoOOiN9rE5cSFtaPlpQIyAuWroVZv9axDMoG9S" +
                                                       "OXvtjVsf7x\nvSotRCr7vnG4BbVEfC0RoSWSo6WlnejDtMPmbAxKSqSe6UKx" +
                                                       "ChAe73YsVIS0mgW99697cG9zGDPD\nHS3FsxGkzeiQb02H7rRnq7lTAp+OKT" +
                                                       "XrlS17IlePrFIppRUH3Qm5q786fvbQnzW3hSA0MSIKLxGT\nq4SYbgGjGaRr" +
                                                       "CdbQRPJ/f2LDiXNnL9yarSYOLQVFXsgpirQ5GA/m6NRA2MuKPzy7Nnwv9O8L" +
                                                       "QSlW\nPqKdtB+BZF5QR16xtqaBT/hSEYXqmMMSxBJbabSq4sPMGc2uyESpE8" +
                                                       "N0lTMikAEDJWZefbR8yXfv\nVZ+RHqfhtTbnAuulXBXr1GwebGKU4vqFF7uf" +
                                                       "e+Hyni0yCfws4FDhMnMEyzOFPDdnebBMLYQKEaSW\nPjvhGGbMJEMWFdn0b+" +
                                                       "1NS9/+7ek6dewWrqSjtuj/BWTXZ98FD5994K95UkyJLq6JrB9ZMuXOtKzk\n" +
                                                       "NsbImLAj9cg3N7z0KdmPKIbI4ZnjVIIBSNdAHmREnu8COS4O7C0RQzPKXlQk" +
                                                       "rSe4lAf1Xa/Fm5M7\nPn9XWl1Ncm/33DhsIG6riqrULQCqBfwhD6TE7gxXjA" +
                                                       "0iBDOD5buWeMMo7PZTG7fWWaf+QbUDqFbH\nG9PrYggeqbxQ+9RlFedPf9Tw" +
                                                       "4NdhCK2BKsshxhoiCwAqMfOoN4y4k3JXrZZm1I1OEaM8F5DWzvFP\nKZXzL4" +
                                                       "zGLShe/2vEvZ8tncGhRUejX3Ttl6dUtPInuPYCcsY/6Dtw9Ut+UcrJlqDgbk" +
                                                       "oVgir2Slne\nO8+NTC1/82AiBBUDUKf73Vw/sZKiGgaw+fDSLR52fHn7+Y2E" +
                                                       "ujVbMxAzN1j+OWqDxZ8Fc5wLajGv\nCWSGOH85WeFnxopgZpSAnKySLPPleL" +
                                                       "PrR4lDSBepMyu3qWZmAi/nEQlIlx5rfv+zvoN7FIhPEsQ8\nrkH9oe9/2B5+" +
                                                       "5vSQ4gtGKkC8b97hn05c6pmuQEF1cfMLGqlcHtXJyaOodYUDTZNpkNQfL2w6" +
                                                       "trvn\nom9RfX4/0oE9+89jH9FbVj714wQXaRh7TYWtYlwqhtUqwZcVLYQV1x" +
                                                       "6iLjG0iZiMidnagMrua1Q5\nA7+tvsrWIio3+SrLYibz1HNlJh7+pI1DL0m4" +
                                                       "qrPKMa5vEuNShakXEvNbOZR78pWRysMOjOcNxfpg\nGcs99/1R8xj5+P6QD8" +
                                                       "frUJD/PMmVw6Eq2+SkfZs7mW+iFAreSaq316Pnd269Ev32b3mlZ/rvamyC\n" +
                                                       "Y0nLyq3VnHm5y2jMlEZWq8p15U8c345BQziUih9paEyRbeNQnUOGV64/yyVK" +
                                                       "YIIikZjabtrNOgns\nAoMiCoPyj1jci/Pzqlk+RX2fNyTVY3RQv+/4lhtTT2" +
                                                       "56VrZhZfiIHR+Xrw58RKnWJNN1NRWVlpb1\nFbz5Rv97ry9PRy2vacmLfyar" +
                                                       "pk+W8gwaJ+4ZOhIul7f8+Dsz31p55MDFkGxb/gOAFXSgQRAAAA==");
    }
    private static class Sample {
        Instance i;
        Shader s;
        float nx;
        float ny;
        float nz;
        Color c;
        Sample next;
        public Sample() { super(); }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1415733647000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAALUYa2wcR/m7Oz/iB/LZcZw4uHHsOG1D0ttWIghwhe04TnPN" +
           "JbbsxEmdpu54du5u\nk73d7e6cfXZDaQU0gULbCJBAommFIiVULVQqqCCVkq" +
           "oPoAEpIFGkSA2tIrVIUCSEVILgB9/M7N5j\n784oP2rp5uZmvvd7/NyH0Oi5" +
           "0Ee9BF92mJcYn5kirsf0cZN43kE8mqdvNLZMnd9n2VGIpCBq6Bw6\nUtTTdM" +
           "KJZuhacvdwwYXtjm0uZ0ybJ1iBJ46bO316d6d2VhE8fPalrkfONfRHoTEFHc" +
           "SybE64YVsT\nJst5HOKp42SRaHlumFrK8PhwCj7BrHxu3LY8TizuPQAPQSwF" +
           "TQ4VNDkMpALmGjLXHOKSnCbZa1OS\nLVJY6zJODIvpY0V2iLmjEhPF9vGmq6" +
           "GRyBpxOYvqSAlQ681FrZW2Vao6sQuznzn5zA9j0DEHHYY1\nI4hR1IQjvzlo" +
           "z7HcAnO9MV1n+hx0WozpM8w1iGmsSK5z0OUZGYvwvMu8aebZ5qIA7PLyDnMl" +
           "z+Aw\nBe1U6OTmKbfdoo3SBjP14Fdj2iQZVLunpLZSd484RwVbDRTMTRPKAp" +
           "SGE4aFHu8PYxR1HNqHAIja\nnGM8axdZNVgED6BL+dIkVkab4a5hZRC00c4j" +
           "Fw4b6xIVtnYIPUEybJ7DhjDclLpCqBZpCIHCYV0Y\nTFJCL20MeanMP9t7Pj" +
           "p94fuvjMrYbtAZNYX8rYi0KYQ0zdLMZRZlCvF6PvHt5D35vigAAq8LASuY\n" +
           "sa0vHUr95Zf9CuaTNWAmF44zyufpgTP90w/eZUNMiLHGsT1DOL9Cc5kOU/7N" +
           "cMHBrO0pUhSXieDy\n4vSb9zz8LPtrFFqT0ERtM5/DOOqkds4xTObexSzmEs" +
           "70JLQwSx+X90loxn0KQ16dTqbTHuNJaDDl\nUZMtf6OJ0khCmKgF94aVtoO9" +
           "Q3hW7gsOAKzFD2zAzxdA/clvDlpC8/JW2rSXNM+lmu1mir+p7TJM\n90yWYw" +
           "IsMjchAsfhsE/L2jmmEUosw7K1jIGpSu3bdLYovm+MXEFI2LUUiYiSF05dE6" +
           "N+r23qzJ2n\n56+9dXJi39dOq7AQoezrxmELckn4XBKCS6KMy9AMyTkmg0hE" +
           "8ugWTJVz0LQnMEmxnLVvmzl29/2n\nB2MYFc5SA9pFgA6iMr4kE9QeL2VyUh" +
           "Y9iuG04QdHTyWunx9R4aTVL7g1sdsuP3/pmX+2fyoK0drV\nUGiI9bhVkJkS" +
           "JbRY5YbC+VOL/t+/vv/Fty+9c2spkzgMVSV4NaZI0MGwL1ybMh1LXon8ud6O" +
           "2GGY\nPROFBsx6rHRSfiwim8I8KhJ1OCh6QpfmFLSlbTdHTHEVVKpWnnXtpd" +
           "KJDJK4WLpVvAhHhgSU9fL6\nl5tu/9PLbW9IjYPS2lHWvGYYV4naWYqDgy5j" +
           "eP7Od6e+9Z0PTx2VQeBHAYdmxzUWMTULiHNzCQdT\n1MQyIZw0dMjK2bqRNs" +
           "iCyUQ0/bdj6x0//dvjcWV2E08Cr+34/wRK57274OFL9/1rkyQToaJFlPQo\n" +
           "gSl11pYoj7kuWRZyFB75w03f+xV5CisYVg3PWGGyEIBUDaQhE9K+2+R6W+ju" +
           "drEMIu0ddcK6RkOe\npyefzQzmH/jNz6XUbaS8s5f7YT9xhpVXJW9RnDaDv1" +
           "QUKHG7zhFrj3DB+nD67iVeFol9+uKBe+Pm\nxf8g2zlkS7FbepMuFo5Chat9" +
           "6MbmK6++1nP/72MQ3QOtpk30PUQmALRg5DEvizWn4IyMSjHiS2vE\nKu0CUt" +
           "qNvpUKZb+aUbht9fN/j+j5pdSZX9hxIfXW5FPSSnUzv0bLC9FZeeXQ2eu/41" +
           "clnVIKCuyB\nQnVBxTmphPvZtxc7m154OheF5jmIU3+SmyVmXmTDHA4eXjDe" +
           "4bRXcV85RKiOOVwsMX3h9C9jG07+\nUiHHvYAW+/ZQZAj7y82IHxkj4ciIgN" +
           "yMSJQtcr3Z8b3EIWLIk/Uceqs6RSCYKitivUMso8q3O+vG\nwOcrpevEz6gv" +
           "3Wgd6faKZQzF8QJx1leJM5MluvJfmTDJGxSmFz+7fGF21RFm0hcmaom02lD+" +
           "2HCN\nHA4ti7JYX3t08Be/PvT0KdXgVgnwCqx5+qU/v3si9sSrCwovHMUh4D" +
           "Obzr3/4rXpblUw1XS7pWrA\nLMdRE64Mkw5HKDCwGgcJ/fr2gecemr7qS9RV" +
           "OadN4Fvmg+XX2C13fvO9GgMGhqxNeMgrUx+DV44V\nvbIsdrMhlvd9DCwXii" +
           "xXarGkN8hSXI37LMfrsMwEiUBrJoKRw4eEaJB2OBGyNyhMN352+8LsriOM\n" +
           "5QvTYGFMe19EoAj0lqXDAduS47lBRSgVghYkKl/CZWnRhcVwVFh+75Yrm38b" +
           "H7+k+nWWw9ayGulD\naklr0aayxO8llo6jv2rffTUZHnaJg0+py+++f+yJ7R" +
           "+8KSLXCVnEXsUihepqGBX7Wzk0efLRW97A\nQKTRTfWeZTKFTh35R/uj5PVj" +
           "UX9COIKE/NdyOR08VTN34N2+1eZzUX2qnuzqmUlTVx6896PUH/8t\nJ8ziU7" +
           "AN32PpvGmWt46yfZODxjakgG2qkTjy66sc4mFB0O3iSwr6FQV2mkNbGRhOgP" +
           "6uHOgxDjEE\nEttvOIGa8VJgqJZYaV7h5y0VBVT+V8TXeX9e/V9knh55/ujm" +
           "wmMHn5SvgkZqkhWZmK34nleTcvER\nMFCXWkDrMrzw49mXf/S5wGMVM3SF74" +
           "sR1b1ajrnQX3uEncg5XA6dKz9b/5M7z5+9KoPV+R8ZBR4B\nzBIAAA==");
    }
    LightServer(Scene scene) { super();
                               this.scene =
                                 scene;
                               lights = (new LightSource[0]);
                               causticPhotonMap =
                                 null;
                               shaderOverride =
                                 null;
                               shaderOverridePhotons =
                                 false;
                               causticPhotonMap =
                                 null;
                               giEngine =
                                 null;
                               this.shadingCache(
                                      0);
    }
    void setLights(LightSource[] lights) {
        this.
          lights =
          lights;
    }
    void shadingCache(float shadingRate) {
        shadingCache =
          shadingRate >
            0
            ? (new CacheEntry[4096])
            : null;
        shadingCacheResolution =
          (float)
            (1 /
               Math.
               sqrt(
                 shadingRate));
    }
    Scene getScene() { return scene; }
    void setShaderOverride(Shader shader,
                           boolean photonOverride) {
        shaderOverride =
          shader;
        shaderOverridePhotons =
          photonOverride;
    }
    boolean build(Options options) { giEngine =
                                       GIEngineFactory.
                                         create(
                                           options);
                                     String caustics =
                                       options.
                                       getString(
                                         "caustics",
                                         null);
                                     if (caustics ==
                                           null ||
                                           caustics.
                                           equals(
                                             "none"))
                                         causticPhotonMap =
                                           null;
                                     else
                                         if (caustics !=
                                               null &&
                                               caustics.
                                               equals(
                                                 "kd"))
                                             causticPhotonMap =
                                               new CausticPhotonMap(
                                                 options);
                                         else {
                                             UI.
                                               printWarning(
                                                 Module.
                                                   LIGHT,
                                                 "Unrecognized caustics photon map engine \"%s\" - ignoring",
                                                 caustics);
                                             causticPhotonMap =
                                               null;
                                         }
                                     Timer t =
                                       new Timer(
                                       );
                                     t.start();
                                     int numLightSamples =
                                       0;
                                     for (int i =
                                            0;
                                          i <
                                            lights.
                                              length;
                                          i++)
                                         numLightSamples +=
                                           lights[i].
                                             getNumSamples();
                                     if (giEngine !=
                                           null) {
                                         if (!giEngine.
                                               init(
                                                 scene))
                                             return false;
                                     }
                                     if (!this.
                                           calculatePhotons(
                                             causticPhotonMap,
                                             "caustic",
                                             0))
                                         return false;
                                     t.end();
                                     cacheLookups =
                                       0;
                                     cacheHits =
                                       0;
                                     cacheEmptyEntryMisses =
                                       0;
                                     cacheWrongEntryMisses =
                                       0;
                                     cacheEntryAdditions =
                                       0;
                                     if (shadingCache !=
                                           null) {
                                         for (int i =
                                                0;
                                              i <
                                                shadingCache.
                                                  length;
                                              i++)
                                             shadingCache[i] =
                                               null;
                                     }
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "Light Server stats:");
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "  * Light sources found: %d",
                                          lights.
                                            length);
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "  * Light samples:       %d",
                                          numLightSamples);
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "  * Max raytrace depth:");
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "      - Diffuse          %d",
                                          maxDiffuseDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]);
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "      - Reflection       %d",
                                          maxReflectionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]);
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "      - Refraction       %d",
                                          maxRefractionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]);
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "  * GI engine            %s",
                                          options.
                                            getString(
                                              "gi.engine",
                                              "none"));
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "  * Caustics:            %s",
                                          caustics ==
                                            null
                                            ? "none"
                                            : caustics);
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "  * Shader override:     %b",
                                          shaderOverride);
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "  * Photon override:     %b",
                                          shaderOverridePhotons);
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "  * Shading cache:       %s",
                                          shadingCache ==
                                            null
                                            ? "off"
                                            : "on");
                                     UI.printInfo(
                                          Module.
                                            LIGHT,
                                          "  * Build time:          %s",
                                          t.
                                            toString());
                                     return true;
    }
    void showStats() { if (shadingCache ==
                             null) return;
                       int numUsedEntries =
                         0;
                       for (CacheEntry e :
                             shadingCache)
                           numUsedEntries +=
                             e !=
                               null
                               ? 1
                               : 0;
                       UI.printInfo(Module.
                                      LIGHT,
                                    "Shading cache stats:");
                       UI.printInfo(Module.
                                      LIGHT,
                                    "  * Used entries:        %d (%d%%)",
                                    numUsedEntries,
                                    100 *
                                      numUsedEntries /
                                      shadingCache.
                                        length);
                       UI.printInfo(Module.
                                      LIGHT,
                                    "  * Lookups:             %d",
                                    cacheLookups);
                       UI.printInfo(Module.
                                      LIGHT,
                                    "  * Hits:                %d",
                                    cacheHits);
                       UI.printInfo(Module.
                                      LIGHT,
                                    "  * Hit rate:            %d%%",
                                    100 *
                                      cacheHits /
                                      cacheLookups);
                       UI.printInfo(Module.
                                      LIGHT,
                                    "  * Empty entry misses:  %d",
                                    cacheEmptyEntryMisses);
                       UI.printInfo(Module.
                                      LIGHT,
                                    "  * Wrong entry misses:  %d",
                                    cacheWrongEntryMisses);
                       UI.printInfo(Module.
                                      LIGHT,
                                    "  * Entry adds:          %d",
                                    cacheEntryAdditions);
    }
    boolean calculatePhotons(final PhotonStore map,
                             String type,
                             final int seed) {
        if (map ==
              null)
            return true;
        if (lights.
              length ==
              0) {
            UI.
              printError(
                Module.
                  LIGHT,
                "Unable to trace %s photons, no lights in scene",
                type);
            return false;
        }
        final float[] histogram =
          new float[lights.
                      length];
        histogram[0] =
          lights[0].
            getPower();
        for (int i =
               1;
             i <
               lights.
                 length;
             i++)
            histogram[i] =
              histogram[i -
                          1] +
                lights[i].
                getPower();
        UI.
          printInfo(
            Module.
              LIGHT,
            "Tracing %s photons ...",
            type);
        int numEmittedPhotons =
          map.
          numEmit();
        if (numEmittedPhotons <=
              0 ||
              histogram[histogram.
                          length -
                          1] <=
              0) {
            UI.
              printError(
                Module.
                  LIGHT,
                "Photon mapping enabled, but no %s photons to emit",
                type);
            return false;
        }
        map.
          prepare(
            scene.
              getBounds());
        UI.
          taskStart(
            "Tracing " +
            type +
            " photons",
            0,
            numEmittedPhotons);
        Thread[] photonThreads =
          new Thread[scene.
                       getThreads()];
        final float scale =
          1.0F /
          numEmittedPhotons;
        int delta =
          numEmittedPhotons /
          photonThreads.
            length;
        photonCounter =
          0;
        Timer photonTimer =
          new Timer(
          );
        photonTimer.
          start();
        for (int i =
               0;
             i <
               photonThreads.
                 length;
             i++) {
            final int threadID =
              i;
            final int start =
              threadID *
              delta;
            final int end =
              threadID ==
              photonThreads.
                length -
              1
              ? numEmittedPhotons
              : (threadID +
                   1) *
              delta;
            photonThreads[i] =
              new Thread(
                new Runnable(
                  ) {
                    public void run() {
                        IntersectionState istate =
                          new IntersectionState(
                          );
                        for (int i =
                               start;
                             i <
                               end;
                             i++) {
                            synchronized (LightServer.this)  {
                                UI.
                                  taskUpdate(
                                    photonCounter);
                                photonCounter++;
                                if (UI.
                                      taskCanceled())
                                    return;
                            }
                            int qmcI =
                              i +
                              seed;
                            double rand =
                              QMC.
                              halton(
                                0,
                                qmcI) *
                              histogram[histogram.
                                          length -
                                          1];
                            int j =
                              0;
                            while (rand >=
                                     histogram[j] &&
                                     j <
                                     histogram.
                                       length)
                                j++;
                            if (j ==
                                  histogram.
                                    length)
                                continue;
                            double randX1 =
                              j ==
                              0
                              ? rand /
                              histogram[0]
                              : (rand -
                                   histogram[j]) /
                              (histogram[j] -
                                 histogram[j -
                                             1]);
                            double randY1 =
                              QMC.
                              halton(
                                1,
                                qmcI);
                            double randX2 =
                              QMC.
                              halton(
                                2,
                                qmcI);
                            double randY2 =
                              QMC.
                              halton(
                                3,
                                qmcI);
                            Point3 pt =
                              new Point3(
                              );
                            Vector3 dir =
                              new Vector3(
                              );
                            Color power =
                              new Color(
                              );
                            lights[j].
                              getPhoton(
                                randX1,
                                randY1,
                                randX2,
                                randY2,
                                pt,
                                dir,
                                power);
                            power.
                              mul(
                                scale);
                            Ray r =
                              new Ray(
                              pt,
                              dir);
                            scene.
                              trace(
                                r,
                                istate);
                            if (istate.
                                  hit())
                                LightServer.this.
                                  shadePhoton(
                                    ShadingState.
                                      createPhotonState(
                                        r,
                                        istate,
                                        qmcI,
                                        map,
                                        LightServer.this),
                                    power);
                        }
                    }
                });
            photonThreads[i].
              setPriority(
                scene.
                  getThreadPriority());
            photonThreads[i].
              start();
        }
        for (int i =
               0;
             i <
               photonThreads.
                 length;
             i++) {
            try {
                photonThreads[i].
                  join();
            }
            catch (InterruptedException e) {
                UI.
                  printError(
                    Module.
                      LIGHT,
                    "Photon thread %d of %d was interrupted",
                    i +
                      1,
                    photonThreads.
                      length);
                return false;
            }
        }
        if (UI.
              taskCanceled()) {
            UI.
              taskStop();
            return false;
        }
        photonTimer.
          end();
        UI.
          taskStop();
        UI.
          printInfo(
            Module.
              LIGHT,
            "Tracing time for %s photons: %s",
            type,
            photonTimer.
              toString());
        map.
          init();
        return true;
    }
    void shadePhoton(ShadingState state, Color power) {
        state.
          getInstance().
          prepareShadingState(
            state);
        Shader shader =
          this.
          getPhotonShader(
            state);
        if (shader !=
              null)
            shader.
              scatterPhoton(
                state,
                power);
    }
    void traceDiffusePhoton(ShadingState previous,
                            Ray r,
                            Color power) {
        if (previous.
              getDiffuseDepth() >=
              (maxDiffuseDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]))
            return;
        IntersectionState istate =
          previous.
          getIntersectionState();
        scene.
          trace(
            r,
            istate);
        if (previous.
              getIntersectionState().
              hit()) {
            ShadingState state =
              ShadingState.
              createDiffuseBounceState(
                previous,
                r,
                0);
            this.
              shadePhoton(
                state,
                power);
        }
    }
    void traceReflectionPhoton(ShadingState previous,
                               Ray r,
                               Color power) {
        if (previous.
              getReflectionDepth() >=
              (maxReflectionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]))
            return;
        IntersectionState istate =
          previous.
          getIntersectionState();
        scene.
          trace(
            r,
            istate);
        if (previous.
              getIntersectionState().
              hit()) {
            ShadingState state =
              ShadingState.
              createReflectionBounceState(
                previous,
                r,
                0);
            this.
              shadePhoton(
                state,
                power);
        }
    }
    void traceRefractionPhoton(ShadingState previous,
                               Ray r,
                               Color power) {
        if (previous.
              getRefractionDepth() >=
              (maxRefractionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]))
            return;
        IntersectionState istate =
          previous.
          getIntersectionState();
        scene.
          trace(
            r,
            istate);
        if (previous.
              getIntersectionState().
              hit()) {
            ShadingState state =
              ShadingState.
              createRefractionBounceState(
                previous,
                r,
                0);
            this.
              shadePhoton(
                state,
                power);
        }
    }
    private Shader getShader(ShadingState state) {
        return shaderOverride !=
          null
          ? shaderOverride
          : state.
          getShader();
    }
    private Shader getPhotonShader(ShadingState state) {
        return shaderOverride !=
          null &&
          shaderOverridePhotons
          ? shaderOverride
          : state.
          getShader();
    }
    ShadingState getRadiance(float rx, float ry,
                             int i,
                             Ray r,
                             IntersectionState istate) {
        scene.
          trace(
            r,
            istate);
        if (istate.
              hit()) {
            ShadingState state =
              ShadingState.
              createState(
                istate,
                rx,
                ry,
                r,
                i,
                this);
            state.
              getInstance().
              prepareShadingState(
                state);
            Shader shader =
              this.
              getShader(
                state);
            if (shader ==
                  null) {
                state.
                  setResult(
                    Color.
                      BLACK);
                return state;
            }
            if (shadingCache !=
                  null) {
                Color c =
                  this.
                  lookupShadingCache(
                    state,
                    shader);
                if (c !=
                      null) {
                    state.
                      setResult(
                        c);
                    return state;
                }
            }
            state.
              setResult(
                shader.
                  getRadiance(
                    state));
            if (shadingCache !=
                  null)
                this.
                  addShadingCache(
                    state,
                    shader,
                    state.
                      getResult());
            return state;
        }
        else
            return null;
    }
    void shadeBakeResult(ShadingState state) {
        Shader shader =
          this.
          getShader(
            state);
        if (shader !=
              null)
            state.
              setResult(
                shader.
                  getRadiance(
                    state));
        else
            state.
              setResult(
                Color.
                  BLACK);
    }
    Color shadeHit(ShadingState state) { state.
                                           getInstance().
                                           prepareShadingState(
                                             state);
                                         Shader shader =
                                           this.
                                           getShader(
                                             state);
                                         return shader !=
                                           null
                                           ? shader.
                                           getRadiance(
                                             state)
                                           : Color.
                                               BLACK;
    }
    final private static int hash(int x, int y) {
        return x ^
          y;
    }
    private synchronized Color lookupShadingCache(ShadingState state,
                                                  Shader shader) {
        if (state.
              getNormal() ==
              null)
            return null;
        cacheLookups++;
        int cx =
          (int)
            (state.
               getRasterX() *
               shadingCacheResolution);
        int cy =
          (int)
            (state.
               getRasterY() *
               shadingCacheResolution);
        int hash =
          LightServer.
          hash(
            cx,
            cy);
        CacheEntry e =
          shadingCache[hash &
                         shadingCache.
                           length -
                         1];
        if (e ==
              null) {
            cacheEmptyEntryMisses++;
            return null;
        }
        if (e.
              cx ==
              cx &&
              e.
                cy ==
              cy) {
            for (Sample s =
                   e.
                     first;
                 s !=
                   null;
                 s =
                   s.
                     next) {
                if (s.
                      i !=
                      state.
                      getInstance())
                    continue;
                if (s.
                      s !=
                      shader)
                    continue;
                if (state.
                      getNormal().
                      dot(
                        s.
                          nx,
                        s.
                          ny,
                        s.
                          nz) <
                      0.95F)
                    continue;
                cacheHits++;
                return s.
                         c;
            }
        }
        else
            cacheWrongEntryMisses++;
        return null;
    }
    private synchronized void addShadingCache(ShadingState state,
                                              Shader shader,
                                              Color c) {
        if (state.
              getNormal() ==
              null)
            return;
        cacheEntryAdditions++;
        int cx =
          (int)
            (state.
               getRasterX() *
               shadingCacheResolution);
        int cy =
          (int)
            (state.
               getRasterY() *
               shadingCacheResolution);
        int h =
          LightServer.
          hash(
            cx,
            cy) &
          shadingCache.
            length -
          1;
        CacheEntry e =
          shadingCache[h];
        if (e ==
              null)
            e =
              (shadingCache[h] =
                 new CacheEntry(
                   ));
        Sample s =
          new Sample(
          );
        s.
          i =
          state.
            getInstance();
        s.
          s =
          shader;
        s.
          c =
          c;
        s.
          nx =
          state.
            getNormal().
            x;
        s.
          ny =
          state.
            getNormal().
            y;
        s.
          nz =
          state.
            getNormal().
            z;
        if (e.
              cx ==
              cx &&
              e.
                cy ==
              cy) {
            s.
              next =
              e.
                first;
            e.
              first =
              s;
        }
        else {
            e.
              cx =
              cx;
            e.
              cy =
              cy;
            s.
              next =
              null;
            e.
              first =
              s;
        }
    }
    Color traceGlossy(ShadingState previous,
                      Ray r,
                      int i) { if (previous.
                                     getReflectionDepth() >=
                                     (maxReflectionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]) ||
                                     previous.
                                     getDiffuseDepth() >
                                     0) return Color.
                                                 BLACK;
                               IntersectionState istate =
                                 previous.
                                 getIntersectionState();
                               scene.trace(
                                       r,
                                       istate);
                               return istate.
                                 hit()
                                 ? this.
                                 shadeHit(
                                   ShadingState.
                                     createGlossyBounceState(
                                       previous,
                                       r,
                                       i))
                                 : Color.
                                     BLACK;
    }
    Color traceReflection(ShadingState previous,
                          Ray r,
                          int i) { if (previous.
                                         getReflectionDepth() >=
                                         (maxReflectionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]) ||
                                         previous.
                                         getDiffuseDepth() >
                                         0)
                                       return Color.
                                                BLACK;
                                   IntersectionState istate =
                                     previous.
                                     getIntersectionState();
                                   scene.
                                     trace(
                                       r,
                                       istate);
                                   return istate.
                                     hit()
                                     ? this.
                                     shadeHit(
                                       ShadingState.
                                         createReflectionBounceState(
                                           previous,
                                           r,
                                           i))
                                     : Color.
                                         BLACK;
    }
    Color traceRefraction(ShadingState previous,
                          Ray r,
                          int i) { if (previous.
                                         getRefractionDepth() >=
                                         (maxRefractionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]) ||
                                         previous.
                                         getDiffuseDepth() >
                                         0)
                                       return Color.
                                                BLACK;
                                   IntersectionState istate =
                                     previous.
                                     getIntersectionState();
                                   scene.
                                     trace(
                                       r,
                                       istate);
                                   return istate.
                                     hit()
                                     ? this.
                                     shadeHit(
                                       ShadingState.
                                         createRefractionBounceState(
                                           previous,
                                           r,
                                           i))
                                     : Color.
                                         BLACK;
    }
    ShadingState traceFinalGather(ShadingState previous,
                                  Ray r,
                                  int i) {
        if (previous.
              getDiffuseDepth() >=
              (maxDiffuseDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]))
            return null;
        IntersectionState istate =
          previous.
          getIntersectionState();
        scene.
          trace(
            r,
            istate);
        return istate.
          hit()
          ? ShadingState.
          createFinalGatherState(
            previous,
            r,
            i)
          : null;
    }
    Color getGlobalRadiance(ShadingState state) {
        if (giEngine ==
              null)
            return Color.
                     BLACK;
        return giEngine.
          getGlobalRadiance(
            state);
    }
    Color getIrradiance(ShadingState state,
                        Color diffuseReflectance) {
        if (giEngine ==
              null ||
              state.
              getDiffuseDepth() >=
              (maxDiffuseDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]))
            return Color.
                     BLACK;
        return giEngine.
          getIrradiance(
            state,
            diffuseReflectance);
    }
    void initLightSamples(ShadingState state) {
        for (LightSource l
              :
              lights)
            l.
              getSamples(
                state);
    }
    void initCausticSamples(ShadingState state) {
        if (causticPhotonMap !=
              null)
            causticPhotonMap.
              getSamples(
                state);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1415733647000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAM1bCZAU1Rl+M3uxB+4BuyAIC+sqCssMyyWwVuKyu8DCAOsu" +
       "oIC49vb0zDT0dLfd\nPcuwUibGlKAYlYo5zCEkZYIiHhVj1JRBiWc0lkdFTT" +
       "TxKCsxqUQr0SollVQl///69XRPz3Qzs4Ol\nW9Vv+3jH/33/8f73uufYB6RC" +
       "18hZvB4y9qiCHuoZGuA0XYj2SJyub4Jbw/xTFdUDR9bJSpAEIiQo\nRg1SH+" +
       "H1cJQzuLAYDff3dqU1Mk9VpD1xSTFCQtoI7ZSWsP7WRpbkdHjJ7Q81XXNHeW" +
       "uQVERIPSfL\nisEZoiL3SUJSN0hDZCc3yoVThiiFI6JudEXIREFOJXsUWTc4" +
       "2dCvJFeTsgipVHns0yCzI9bgYRg8\nrHIalwzT4cMDdFjoYZImGJwoC9HuzH" +
       "DQsiO7JYjN2g3m1oZOJuDDLQCHSgCoZ2VQm2hzoKpld25Z\nuvfwXWWkfhup" +
       "F+Uh7IwHJAaMt43UJYXkiKDp3dGoEN1GGmVBiA4JmshJ4hgddRtp0sW4zBkp" +
       "TdAH\nBV2RRrFik55SBY2Oad2MkDoeMWkp3lC0DEcxUZCi1lVFTOLiALvFhm" +
       "3CXYX3AWCNCIJpMY4XrCbl\nu0QZNN7qbpHB2L4OKkDTqqRgJJTMUOUyBzdI" +
       "k6lLiZPj4SFDE+U4VK1QUjCKQaZ5dopcqxy/i4sL\nwwaZ6q43YD6CWtWUCG" +
       "xikGZ3NdoTaGmaS0sO/cxr+WT/nT84fhG17fKowEsofw00mulqNCjEBE2Q\n" +
       "ecFseDIVurV/a+qsICFQudlV2azTfc5DmyN/fazVrDM9T52NIzsF3hjmNxxs" +
       "HbxqtULKUIwJqqKL\nqPws5NQdBtiTrrQKXtuS6REfhqyHjw8+vfWrR4W/B0" +
       "lNP6nkFSmVBDtq5JWkKkqCtlqQBY0zhGg/\nqRbkaA993k+q4DwCJm/e3RiL" +
       "6YLRT8oleqtSoddAUQy6QIqq4VyUY4p1rnJGgp6nVcL+KuCYyM7p\nf4OEQ2" +
       "E9JcckZXdY1/iwosUz17yiCeDu8YQBDjAqaCE0HNUg68IJJSmEOZ6TRVkJx0" +
       "VwVV6ZHxVG\n8X9x3aVRwqbdgQCGPLfrSmD1axQpKmjD/JH3ntvbt+76/aZZ" +
       "oCkzbAY5C0YJsVFCOErIMQoJBGjn\nk3E0UyvA6S7wTohjdecP7Vh7xf62Mj" +
       "AHdXc5EIJV2wAFE6GPV3psF+6n0Y4HO5r64+37QiePfNm0\no7B3pM3buval" +
       "e54//HHd3CAJ5g+DCA0CcQ12M4CxMxPe2t2Ok6//D29Y/8Brz//pPNuFDNKe" +
       "49m5\nLdEz29xK0BReiEKss7u/48z6skvIloNBUg7uDiGOyg/RY6Z7jCwP7b" +
       "KiHWKpipDamKIlOQkfWSGq\nxkhoym77DrWOBno+CZSDCiL1cKxmNkz/49Nm" +
       "FcsW05pQ2y4UNJqevLZyweuP1j5FabECb71jahsS\nDNONG21j2aQJAtz/03" +
       "cHvvmtD/Ztp5ZimgpJQ81z7ZrgthKEDtRf+2Y5qUTFmMiNSAIa2n/rz+l8\n" +
       "8B83NZgakeCOpdCOU3dg3z9zJfnq85d/OpN2E+Bx2rClt6uZICbZPXdrGrcH" +
       "5Uhf88qM257hfghR\nDSKJLo4JNDgEmO2jUFOg1xyHGuIhRFF2Q7TS+bScjy" +
       "wwLvB6IRZtMHSHh0PkmcOH+b1H422pK3/z\nCAVVyzmTAady1nNql2kPWJyN" +
       "5E9x+/QaTk9AvcWPb7isQXr8P9DjNuiRh7lT36hBGElnqZbVrqh6\n48QTLV" +
       "e8XEaCq0iNpHDRVRz1ClIN5ijoCYhAafXLF1GLa9g9AUsKmVASpjEC0o6rRh" +
       "DufO+gsAoz\nANufhkc67ow8t/GHlADPcJBnAnT1M3Z88+0nXzDeov3Yfomt" +
       "Z6dzwytkTXbbZa+NNlbefygZJFXb\nSAPP8rotnJRC698GaYhuJXuQ+2U9z0" +
       "4pzPmzKxN3znLHBMew7ohgh3U4x9p4XucKAnXI9lQWCKyA\nkBUEAoSeXESb" +
       "tNNyDovuBqlSNXGUw1wP0my06lMYvRlRsFyERbep5qWe5tCVLehsOJqYoE0e" +
       "gq7F\nYqVBKiWctnQMX47kn/ouhsS7bumdNLh8+7V01qiGfJTTN9h8wSoAzw" +
       "Kg6HO8LS/T2TA/Z8dD/zxx\nXJhDnWSCqINeurV4ntzM0eZf3FFh/eux22nk" +
       "Lx/hdFND7qQ2N2fNSkWpQs/I5mmKH0+WirwmeiWl\nsaHmu4JZFxYXW0Rvzr" +
       "WIIJhBTJQ5sNRKNTUiiXwaFSHIcSMBipjqXIVpYhKyuVE6mb13Xduvnt18\n" +
       "aJ+ZAPj4elarYf4rb7+zq+zmEyNmO7dDuyofnHnHXx54b3CyOWuYaf/ZOZm3" +
       "s42Z+lOC61UMkbP9\nRqC1n5w3+9jVg28xiZqyE9g+WOS9v+cJYc6F33g3T+" +
       "ZVBorGiwE1naE/aPJq6cychTA0QJKvyAJO\naNYzMycTlVBmgQUP0zmK1MiM" +
       "rIxsPbUlO3bdcNfdDxkvz1thIpjrrQt3w7krDo+1rrjvwDjysFYX\nr+6uG0" +
       "enX1yWEJ8N0pWXGQpzVmzZjbqyA2ANyJPS5E1ZYXCWac+U8zzhDc+3pn1maM" +
       "3nGdUkrHAq\neNSTqVbgvjV/gtKXVA2aUow9POXnFx65/S1kX03DiqKGhqbl" +
       "y5d3LobmTeBBuC8REqNsvup9ZdXI\n0Zh8NEq5qaGRoxubMODV9I4jtJUpqo" +
       "4LL8cOB+upfaOqY9I60TFIf+/e+9fWTfjRgeto/ywuVjsW\ncey6apTTNjit" +
       "uZYK3rmgc/GSJQbZfBpXOSsWX3BBx8Ll8xdBv7Wb1vQPhcxQj0OnM4H/alg/" +
       "5rKF\nGIkdGQHtGbZLoX86HwKK8sG+7l7XrLWuyFmrDY5m1m2zx6y1j81aZ+" +
       "gJDlKrjYBVE6MZ356SO53S\nei7J9hcp2Vw4WphkLR6S3cQka86WbCChGGC5" +
       "tL/tLGBdAQnBiKJIAie7BLu5cMHofFUJxwwm2Jke\ngn2bCVaf5NK9YiyW0o" +
       "VeQaUTTbMd4QY4A2ZP6vh3d+94a+GtM58wQ29mvk27IJSPKqaVD7tAfGcc\n" +
       "IM5mIFo9QBxiIJoABKzvWEAwceCj21Qq3vfzyXN4HPKcz+Q5x0Oen2TLo3FF" +
       "yPPTIq3vAjg6mDwd\nHvLczeRp4LmUboi8aXawzrA8Y26OZ/S4avZb6ZNL3G" +
       "NFijsTjvlM3Pke4v6MiTshLvbJcdHOh8/M\nEXN1v1nDJdUD45AqxKQKeUj1" +
       "CJNqoko56VFSSEk+Ff6yyNHPhaOTjd7pMfpxNnodBhBRjvdwfMLk\nZX12Zy" +
       "1+nVlEzvHbnmqnfffJhrbHO4H9tSXY03kSWDy/FIut1M63ZUg6kZuU4SVVXz" +
       "w3y8LrneZo\ntDUWik+68ILPsxexeB6LlCkJlrudycGyZUtoyz2OaXfR0gWn" +
       "e9pdsLSjc+n8hcsM0kinXadCcfiX\nXErGe9e6LOyxIi0MA9ZCZhQLPSzs98" +
       "zCWpyD0/cGqUzi6QjwsEpWOMMl1x/G4XeLmFyLPOR6x7J8\nHgWKKMqulJoz" +
       "Y5ZDfhh3CfNukcLMgWMxE2axhzB/ZcI0U2FowkkdZb2o66Y7vueS4m/jkGIJ" +
       "k2KJ\nhxQfZElxiQbYTyHFh0VKgTPbUibFUg8pPmJSTOIz8aI7GhUzxuKW4e" +
       "MiZZhOzNmNWP/zyPApk6Ga\nyrBGNPKOfNJnZBoczqXPp9l7qDO8XgbR9em+" +
       "S/9Vdx335I4giy0cCGAo6nxJGBUkR1fTzQiWgUW7\nPw+OzQzWZjcsKrgtkR" +
       "3KnCHRjviTLCfy7NCK+L77FKqqOhdJCztdi6SIwnNSf++PTtS+cjC1dK0F\n" +
       "+yooAtUG2XQ64+OyJR2dsCpZnlmC4FCBcu/IHqjHIgAqwHdP5nYViw747/sZ" +
       "MwgET2WAGQPwUNtW\nxvLWYtWGl29SYaf6AJmGRXNu+A802hhaSsEwGY7LGY" +
       "bLC8bgFLHN51k7Fq2YtgnGUAHbmDaoWaWA\n6oZjhIEaKRiUa0vIb2l4kKLz" +
       "eckQwJcMgXkwoYMFDmUt8Fza6ygFKK6A4wxofFyBA4BOzQG6UaUR\nmwJZ4Q" +
       "PyS1hcABP/SEqU6NLuoA1sWalmeYgBOzQus1zl82wNFj0YHhLKbvyyQncppb" +
       "cU2QfguI/J\nfl/BspfRHsu8g7O57hoy4Nyq1GBvsZjfSOD9YQpxwAf+FizW" +
       "07WfxKckzrA2HVwa3FAKC5CoBKpM\nEsz/4/HBGXl9EICi0vJv4ohJLi7gHq" +
       "CiUbCX+xCBJhvYDrk93YIxSXBZwmWlcLAKsLP3P4Gc9z+F\nWkJhHEzOqTXI" +
       "7SmIIMmHIEzmA6JBmgwN1vlsMygvTztL4akf+JnOeJr+ReVprw9PX8cCFonN" +
       "lCd7\nvykvVWOlUjWHUTXni0rVjT5U3YrF9Q6q2FZYXqpuGCdVdLGwAChiK8" +
       "lAzkoyQ9VK/8nRnyqK6Ac+\naA9jcRvMNHErDThVemGD/14p4GGFFmArpUDO" +
       "Suk0gj/qA/5eLI4YpB7As9mrSAruLMVVLgUkFzIK\nLizYVSpojxV4+WamGC" +
       "7IJ9pyHtItUt2MBTZnD/twhpt6gQdhSgLOBoFrfOVWhNtS1n5RCmsdwBZb\n" +
       "OgbGtxYtzHCe9SHht1g8BYZD5+WV3C7cdEpJhis6PF0KznNB3B0M547PEOfv" +
       "fHDi/lrgZVgeUZzW\nVoVPdLWxvzJO7PSlTxgg8Aw7nxc7rZ+1g1uG53QHtx" +
       "fW4Tr9HNn9Dtexf0v3ds1U9M8+DLyPxdsG\nKU9wesL2NArxnVIgbgJoOxnE" +
       "nQVCDDJ0dfoemU9oiiyOCVEPjCVkqI5IRzn4yIefk1j8AxIwiW5y\nDrl3+g" +
       "uylQ9KIXIrEHiAEXmgKCJx6H+66RtXJuI7UZwiGQn67BMF61DG/0Gg4aLRIc" +
       "8dliApJdCs\nAOKOMQKPFRxoTl/GlnHE4GQfJnCjKdgI8w5Ny1ZLiq6fMtez" +
       "CWoqhaAvATEPMoIe/FwJavchaC4W\ns8FUXCl+4SS1lUrScUbS8c+VpMU+JK" +
       "3AotNBEkvuCydpYanr7WcYSc98riT1+pCEH1IGuw3SQEla\nhZ/1reaMhB3O" +
       "CszzgitL4QpzgBcZVy8WzFXR+U9wkw8TmE4ELzZIIyS7EHJGOMmd8p7aYAZL" +
       "IaEP\nsLzKSHi1YBJO+yZVcMSHJEyLgsMGmQgk9Wta0QRdUQpBIEzgj4ygP3" +
       "6GVuLzxV9wFAsF/EWURfNt\nzhCXVM3vM52ztFqqO7zLgL77GQK92gfo17C4" +
       "CpI9BMq+tskPdW+hUNMwozvep+GXklNzfnpo/lyO\nj7xx1WWfRF79t/nZtP" +
       "WTttoImRBLSZLzo3fHeaWqCTGRMlRrfgJP37EFrwd9udmANJ9nu9fB/Wa1\n" +
       "G0E+RzWDVLEzZ6WbDVIGlfD0lsz3SY69b/Nj/nQWcER6dtY3tvTXndZ3sCnz" +
       "953D/KX3bJ+VPrDp\nFvpxbQUvcWNj2E1NhFSZP/yhveK3tLM9e7P6eoncf9" +
       "+WR+9dbr0MpT8BmexIkrMMcJH51FuR+GC3\n+n+X1vpMaTsAAA==");
}
