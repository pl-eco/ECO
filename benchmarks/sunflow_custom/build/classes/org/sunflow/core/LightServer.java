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
        public static final String jlc$CompilerVersion$jl7 = "2.6.1";
        public static final long jlc$SourceLastModified$jl7 = 1421803809000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXXWwURXju2l5/KNy1pVCxlLYcYgFvQYMRSlQ4W2g9aUOB" +
                                                        "xGvknO7N9Zbu7S6zc+21WAUSU+IDMbEgGOyDgWAIfzESNYakLyoGXzRG44Ng" +
                                                        "fJEEeeBBJKLiN7N7t3fba40PXrJzszPf//+eu43KTIpWG7o6OqjqLEQyLLRX" +
                                                        "XR9iowYxQ92R9b2YmiQeVrFp7oSzmNx6yX/3/pvJgBf5oqgOa5rOMFN0zdxB" +
                                                        "TF0dJvEI8junHSpJmQwFInvxMJbSTFGliGKy9gial4fKUDCSFUECESQQQRIi" +
                                                        "SJsdKECaT7R0KswxsMbMfehV5IkgnyFz8RhqKSRiYIpTNpleoQFQqODvu0Ep" +
                                                        "gZyhqDmnu6XzDIWPrpYm394T+KAE+aPIr2h9XBwZhGDAJIqqUyQ1QKi5OR4n" +
                                                        "8Siq0QiJ9xGqYFUZE3JHUa2pDGqYpSnJGYkfpg1CBU/HctUy142mZabTnHoJ" +
                                                        "hajx7FtZQsWDoOsiR1dLw05+DgpWKSAYTWCZZFFKhxQtztAyN0ZOx+DzAACo" +
                                                        "5SnCknqOVamG4QDVWr5TsTYo9TGqaIMAWqangQtDS2Ylym1tYHkID5IYQw1u" +
                                                        "uF7rCqAqhSE4CkP1bjBBCby0xOWlPP/c3r7pyH5tm+YVMseJrHL5KwCpyYW0" +
                                                        "gyQIJZpMLMTqVZFjeNGVw16EALjeBWzBfPTKnWfXNE1ftWAeLgLTM7CXyCwm" +
                                                        "nxpY8HVjuG1DCRejwtBNhTu/QHMR/r32TXvGgMxblKPIL0PZy+kdn7944Cy5" +
                                                        "5UVVXcgn62o6BXFUI+spQ1EJ3Uo0QjEj8S5USbR4WNx3oXLYRxSNWKc9iYRJ" +
                                                        "WBcqVcWRTxfvYKIEkOAmKoe9oiX07N7ALCn2GQMhVAcPaoBnA7J+4p+hfmmX" +
                                                        "CeEuYRlriqZLELwEUzkpEVmPDYB1kylMh0xJTptMT0lmWkuo+ohkUlnS6WDu" +
                                                        "XdYpgVIwmGSQLMOEhniQGf8v+QzXLjDi8YDhG91pr0LGbNPVOKExeTK9pePO" +
                                                        "hdg1by4NbLswtBK4hGwuIc4llMclGMZyknRojI4ij0fwWcgZW84F1wxBkkP5" +
                                                        "q27re6n75cOtJRBVxkgp2JWDtoKKtjQdsh52KkGXqHcyhGPDe/0ToXtnnrHC" +
                                                        "UZq9bBfFRtPHRw7ufm2tF3kL6y/XDo6qOHovr5q56hh0510xuv6Jm3cvHhvX" +
                                                        "nQwsKOh2YZiJyRO71e0HqsskDqXSIb+qGV+OXRkPelEpVAuokAxDREPxaXLz" +
                                                        "KEjw9myx5LqUgcIJnaawyq+yFa6KJak+4pyIAFnAl1orVrgDXQKKOtv5yfSJ" +
                                                        "y++s3uDNL8n+vCbXR5iV4DWO/3dSQuD8x+O9bx29PdEvnA8Qy4sxCPI1DOkO" +
                                                        "3gCLvX513w83rp/61usEDEPlBlWGoQpkgMgjDhuoBipUJO7X4C4tpceVhIIH" +
                                                        "VMID70//inWXfz0SsDylwknW0Wv+nYBz/tAWdODant+bBBmPzLuRo7oDZlmg" +
                                                        "zqG8mVI8yuXIHPxm6Ykv8LtQLKFAmcoYETUHCdWQsH1IuKRNrI+57tbypdmY" +
                                                        "cZcRJw3irQRYt82eIJ28qeYl1h896sChn+8JjWakRpFe4sKPSudOLgk/fUvg" +
                                                        "OzHKsZdlZlYbGEAc3MfPpn7ztvo+86LyKArI9nSzG6tpHi5R6OhmduSBCajg" +
                                                        "vrA7W62oPZeDje78yGPrzg6nysGeQ/N9lZUQAqYGbFqB7Maw0e4J4p/f1hl8" +
                                                        "XZjxILF5SqC0iDXIl5W2hxjyypm5PdNLlRQ0wWG7S0vjtTeGTt48b5U8txtc" +
                                                        "wOTw5BsPQkcmvXlzz/IZo0c+jjX7CD3nW3o+gJ8Hnr/5w4XmB1bvqw3bDbg5" +
                                                        "14ENg6vTMpdYgkXnLxfHP31/fMJSo7aw7XfAVHv+u7++Ch3/6csiPacERjpR" +
                                                        "jqyIf+K/+2MrXzZy44/y3bOzU6uHp92m1j4LtW6bWllCoaY1hteDnefsjH04" +
                                                        "ZVhjR8DIFI8OL98+ypDPFBN2fjIjbuSls82AwsCnDk1OxXtOr/PaNeI5IGSP" +
                                                        "5vl0GKpymnRW9Ma5RAfODTO+Eay5Vr4w5a9YPLXre9GacrNnJQyAibSq5qdU" +
                                                        "3t5nUJJQhJCVVoIZ4q8fvpPcgjBUyv+EoFELbA9D8/LAoA/Yu3wgDFEDQHw7" +
                                                        "YGTVDIhKzEtFyCoVGVRgG8Nt8eUFWSq+p2wLvJC2vqhi8sWp7u377zx5WgwV" +
                                                        "ZfAlNjYm5m/4nLAabm6WaJmVWpaWb1vb/QWXKldkfVjQil2yLSvesDpSBhMt" +
                                                        "ZuzjxR9uOjN1XbTMfwC2k80y6A4AAA==");
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
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1421803809000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "AAA=");
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
                               shadingCache(
                                 0); }
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
                                     t.start(
                                         );
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
                                             getNumSamples(
                                               );
                                     if (giEngine !=
                                           null) {
                                         if (!giEngine.
                                               init(
                                                 scene))
                                             return false;
                                     }
                                     if (!calculatePhotons(
                                            causticPhotonMap,
                                            "caustic",
                                            0))
                                         return false;
                                     t.end(
                                         );
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
                                            toString(
                                              ));
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
            getPower(
              );
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
                getPower(
                  );
        UI.
          printInfo(
            Module.
              LIGHT,
            "Tracing %s photons ...",
            type);
        int numEmittedPhotons =
          map.
          numEmit(
            );
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
              getBounds(
                ));
        UI.
          taskStart(
            "Tracing " +
            type +
            " photons",
            0,
            numEmittedPhotons);
        Thread[] photonThreads =
          new Thread[scene.
                       getThreads(
                         )];
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
          start(
            );
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
                                      taskCanceled(
                                        ))
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
                                  hit(
                                    ))
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
                  getThreadPriority(
                    ));
            photonThreads[i].
              start(
                );
        }
        for (int i =
               0;
             i <
               photonThreads.
                 length;
             i++) {
            try {
                photonThreads[i].
                  join(
                    );
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
              taskCanceled(
                )) {
            UI.
              taskStop(
                );
            return false;
        }
        photonTimer.
          end(
            );
        UI.
          taskStop(
            );
        UI.
          printInfo(
            Module.
              LIGHT,
            "Tracing time for %s photons: %s",
            type,
            photonTimer.
              toString(
                ));
        map.
          init(
            );
        return true;
    }
    void shadePhoton(ShadingState state, Color power) {
        state.
          getInstance(
            ).
          prepareShadingState(
            state);
        Shader shader =
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
              getDiffuseDepth(
                ) >=
              (maxDiffuseDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]))
            return;
        IntersectionState istate =
          previous.
          getIntersectionState(
            );
        scene.
          trace(
            r,
            istate);
        if (previous.
              getIntersectionState(
                ).
              hit(
                )) {
            ShadingState state =
              ShadingState.
              createDiffuseBounceState(
                previous,
                r,
                0);
            shadePhoton(
              state,
              power);
        }
    }
    void traceReflectionPhoton(ShadingState previous,
                               Ray r,
                               Color power) {
        if (previous.
              getReflectionDepth(
                ) >=
              (maxReflectionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]))
            return;
        IntersectionState istate =
          previous.
          getIntersectionState(
            );
        scene.
          trace(
            r,
            istate);
        if (previous.
              getIntersectionState(
                ).
              hit(
                )) {
            ShadingState state =
              ShadingState.
              createReflectionBounceState(
                previous,
                r,
                0);
            shadePhoton(
              state,
              power);
        }
    }
    void traceRefractionPhoton(ShadingState previous,
                               Ray r,
                               Color power) {
        if (previous.
              getRefractionDepth(
                ) >=
              (maxRefractionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]))
            return;
        IntersectionState istate =
          previous.
          getIntersectionState(
            );
        scene.
          trace(
            r,
            istate);
        if (previous.
              getIntersectionState(
                ).
              hit(
                )) {
            ShadingState state =
              ShadingState.
              createRefractionBounceState(
                previous,
                r,
                0);
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
          getShader(
            );
    }
    private Shader getPhotonShader(ShadingState state) {
        return shaderOverride !=
          null &&
          shaderOverridePhotons
          ? shaderOverride
          : state.
          getShader(
            );
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
              hit(
                )) {
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
              getInstance(
                ).
              prepareShadingState(
                state);
            Shader shader =
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
                addShadingCache(
                  state,
                  shader,
                  state.
                    getResult(
                      ));
            return state;
        }
        else
            return null;
    }
    void shadeBakeResult(ShadingState state) {
        Shader shader =
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
                                           getInstance(
                                             ).
                                           prepareShadingState(
                                             state);
                                         Shader shader =
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
    private static final int hash(int x, int y) {
        return x ^
          y;
    }
    private synchronized Color lookupShadingCache(ShadingState state,
                                                  Shader shader) {
        if (state.
              getNormal(
                ) ==
              null)
            return null;
        cacheLookups++;
        int cx =
          (int)
            (state.
               getRasterX(
                 ) *
               shadingCacheResolution);
        int cy =
          (int)
            (state.
               getRasterY(
                 ) *
               shadingCacheResolution);
        int hash =
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
                      getInstance(
                        ))
                    continue;
                if (s.
                      s !=
                      shader)
                    continue;
                if (state.
                      getNormal(
                        ).
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
              getNormal(
                ) ==
              null)
            return;
        cacheEntryAdditions++;
        int cx =
          (int)
            (state.
               getRasterX(
                 ) *
               shadingCacheResolution);
        int cy =
          (int)
            (state.
               getRasterY(
                 ) *
               shadingCacheResolution);
        int h =
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
            getInstance(
              );
        s.
          s =
          shader;
        s.
          c =
          c;
        s.
          nx =
          state.
            getNormal(
              ).
            x;
        s.
          ny =
          state.
            getNormal(
              ).
            y;
        s.
          nz =
          state.
            getNormal(
              ).
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
                                     getReflectionDepth(
                                       ) >=
                                     (maxReflectionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]) ||
                                     previous.
                                     getDiffuseDepth(
                                       ) >
                                     0) return Color.
                                                 BLACK;
                               IntersectionState istate =
                                 previous.
                                 getIntersectionState(
                                   );
                               scene.trace(
                                       r,
                                       istate);
                               return istate.
                                 hit(
                                   )
                                 ? shadeHit(
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
                                         getReflectionDepth(
                                           ) >=
                                         (maxReflectionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]) ||
                                         previous.
                                         getDiffuseDepth(
                                           ) >
                                         0)
                                       return Color.
                                                BLACK;
                                   IntersectionState istate =
                                     previous.
                                     getIntersectionState(
                                       );
                                   scene.
                                     trace(
                                       r,
                                       istate);
                                   return istate.
                                     hit(
                                       )
                                     ? shadeHit(
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
                                         getRefractionDepth(
                                           ) >=
                                         (maxRefractionDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]) ||
                                         previous.
                                         getDiffuseDepth(
                                           ) >
                                         0)
                                       return Color.
                                                BLACK;
                                   IntersectionState istate =
                                     previous.
                                     getIntersectionState(
                                       );
                                   scene.
                                     trace(
                                       r,
                                       istate);
                                   return istate.
                                     hit(
                                       )
                                     ? shadeHit(
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
              getDiffuseDepth(
                ) >=
              (maxDiffuseDepth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]))
            return null;
        IntersectionState istate =
          previous.
          getIntersectionState(
            );
        scene.
          trace(
            r,
            istate);
        return istate.
          hit(
            )
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
              getDiffuseDepth(
                ) >=
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
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1421803809000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAALVaf4wU1R1/s/f7PLgfcMfJb44DPH7sCgqtQqhw3sHRQ64c" +
       "R8sROOdm3+4ONzszzsweCxZFEgvR1BoLilaxMfizpxhTqq2R8E9FY1uj0Zo2" +
       "VqppotHalKS1ttra7/e9N/tjdmfYu+Al827mzXvv+/n+/r63M/YpqbAtssQ0" +
       "tH1xzXDCNO2E92grw84+k9rhTb0r+2TLptFOTbbtbdA3pLQ9W//ZF3cnGkKk" +
       "cpBMkXXdcGRHNXR7K7UNbZRGe0l9trdLo0nbIQ29e+RROZJyVC3Sq9rO6l5y" +
       "Wc5Uh7T3uhAiACECECIMQmRddhRMmkT1VLITZ8i6Y99EbiFSL6k0FYTnkHn5" +
       "i5iyJSfFMn2MA1ihGp+3A1NsctoiczO8c54LGD62JHL0vt0Nz5WR+kFSr+r9" +
       "CEcBEA4QGSR1SZocppa9Lhql0UHSqFMa7aeWKmvqfoZ7kDTZalyXnZRFM0LC" +
       "zpRJLUYzK7k6BXmzUopjWBn2YirVou5TRUyT48BrS5ZXzmE39gODtSoAs2Ky" +
       "Qt0p5SOqHnXIHO+MDI/t34YBMLUqSZ2EkSFVrsvQQZq47jRZj0f6HUvV4zC0" +
       "wkgBFYdM910UZW3Kyogcp0MOafWO6+OvYFQNEwROcUizdxhbCbQ03aOlHP18" +
       "esOau27WN+ohhjlKFQ3xV8Ok2Z5JW2mMWlRXKJ9Yt7j3XrnlpSMhQmBws2cw" +
       "H/P89y9ct3T22Vf4mBlFxmwZ3kMVZ0g5OTz5jZmdHdeUIYxq07BVVH4e58z8" +
       "+8Sb1WkTPK8lsyK+DLsvz259ecfBp+gnIVLbQyoVQ0slwY4aFSNpqhq1NlCd" +
       "WrJDoz2khurRTva+h1TBfa+qU967JRazqdNDyjXWVWmwZxBRDJZAEVXBvarH" +
       "DPfelJ0Eu0+bRPxVwDVJ3LP/DtkZGbDB3COyIuuqbkTAeKlsKYkIVYyhYZBu" +
       "IilbI3ZESdmOkYzYKT2mGXsjtqVEDCueeVYMi0IoiCcccJZRaoXRyMyvd/k0" +
       "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");
}
