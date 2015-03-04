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
        public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0XXWwURXju2l5/KPTa8lOxlLYcYgFvQYMRSlQ4W2g9aUOB" +
                                                        "xCNyTvfmekv3j9m59lqsAokp8YGYWBAM9sFAMIS/GIkaQ9IXFYMvGqPxQTC+" +
                                                        "SII88CASUfGb2b3bu+21xhcv2dm5me//f8/dRmUWRatMQx0ZUA0WJhkW3quu" +
                                                        "C7MRk1jh7ui6Xkwtkoio2LJ2wFlcbr1Uc/f+m6mgHwViqB7rusEwUwzd2k4s" +
                                                        "Qx0iiSiqcU87VKJZDAWje/EQltJMUaWoYrH2KJqTh8pQKJoVQQIRJBBBEiJI" +
                                                        "m1woQJpL9LQW4RhYZ9Y+9CryRVHAlLl4DLUUEjExxZpDpldoABQq+P9doJRA" +
                                                        "zlDUnNPd1nmawkdXSRNv7wl+UIJqYqhG0fu4ODIIwYBJDFVrROsn1NqUSJBE" +
                                                        "DNXqhCT6CFWwqowKuWOozlIGdMzSlOSMxA/TJqGCp2u5apnrRtMyM2hOvaRC" +
                                                        "1ET2X1lSxQOg60JXV1vDTn4OClYpIBhNYplkUUoHFT3B0FIvRk7H0PMAAKjl" +
                                                        "GmEpI8eqVMdwgOps36lYH5D6GFX0AQAtM9LAhaHFMxLltjaxPIgHSJyhBi9c" +
                                                        "r30FUJXCEByFoQVeMEEJvLTY46U8/9zetvHIfn2r7hcyJ4iscvkrAKnJg7Sd" +
                                                        "JAklukxsxOqV0WN44ZXDfoQAeIEH2Ib56JU7z65umrpqwzxcBKanfy+RWVw+" +
                                                        "1T/v68ZI2/oSLkaFaVgKd36B5iL8e52b9owJmbcwR5FfhrOXU9s/f/HAWXLL" +
                                                        "j6q6UEA21LQGcVQrG5qpqIRuITqhmJFEF6okeiIi7rtQOeyjik7s055k0iKs" +
                                                        "C5Wq4ihgiP9goiSQ4CYqh72iJ43s3sQsJfYZEyFUDw9qgGc9sn/izZAspQyN" +
                                                        "SFjGuqIbEsQuwVROSUQ24pSYhtQR6ZH6wcopDdNBS7LSelI1huNy2mKGJllU" +
                                                        "lgw6kD2WZIMSKAkDKQZJM0RomAeb+f+wyXBtg8M+Hzii0VsGVMigrYaaIDQu" +
                                                        "T6Q3d9y5EL/mz6WFYyeGVgCXsMMlzLmE87iEIlhOkQ6d0RHk8wk+8zlj29ng" +
                                                        "qkFIeiiH1W19L3W/fLi1BKLMHC4FO3PQVtDVkaZDNiJuZegS9U+G8Gx4b/d4" +
                                                        "+N6ZZ+zwlGYu40Wx0dTx4YO7XlvjR/7Cesy1g6Mqjt7Lq2iuWoa8eViMbs34" +
                                                        "zbsXj40ZbkYWFHinUEzH5Ine6vUDNWSSgNLpkl/ZjC/Hr4yF/KgUqgdUTIYh" +
                                                        "wqEYNXl5FCR8e7Z4cl3KQOGkQTWs8qtsxatiKWoMuyciQObxpc6OFe5Aj4Ci" +
                                                        "7nZ+MnXi8jur1vvzS3RNXtPrI8xO+FrX/zsoIXD+4/Het47eHt8tnA8Qy4ox" +
                                                        "CPE1AukP3gCLvX513w83rp/61u8GDEPlJlWGoCpkgMgjLhuoDipUKO7X0E5d" +
                                                        "MxJKUsH9KuGB92fN8rWXfz0StD2lwknW0av/nYB7/tBmdODant+bBBmfzLuT" +
                                                        "q7oLZlug3qW8iVI8wuXIHPxmyYkv8LtQPKFgWcooETUICdWQsH1YuKRNrI95" +
                                                        "7tbwpdmcdpcRJw3iXwmwbps5QTp5k81LrD961P5DP98TGk1LjSK9xYMfk86d" +
                                                        "XBx5+pbAd2OUYy/NTK82MJC4uI+f1X7ztwY+86PyGArKzrSzC6tpHi4x6PBW" +
                                                        "dgSCiajgvrBb262pPZeDjd78yGPrzQ63ysGeQ/N9lZ0QAqYWbFqBnEaxwekR" +
                                                        "4s1v602+zs/4kNg8JVBaxBriywrHQwz55czsnumligZNccjp2tJY3Y3BkzfP" +
                                                        "2yXP6wYPMDk88caD8JEJf94ctGzaKJKPY89CQs+5tp4P4OeD52/+cKH5gd0L" +
                                                        "6yJOQ27OdWTT5Oq0zCaWYNH5y8WxT98fG7fVqCscAzpgyj3/3V9fhY//9GWR" +
                                                        "nlMCI54oR3bEP/Hf/bGFLxu48Uf47tmZqS2Ap92h1j4DtW6HWllSoZY9li8A" +
                                                        "O8/aGfuwZtpjSNDMFI8OP98+ylDAEhN3fjIjbuQlM82EwsCnDk1MJnpOr/U7" +
                                                        "NeI5IOSM6vl0GKpym3RW9MbZRAfODdO+Gew5V74wWVOxaHLn96I15WbRShgI" +
                                                        "k2lVzU+pvH3ApCSpCCEr7QQzxWs3fDd5BWGolL+EoDEbbA9Dc/LAoA84u3wg" +
                                                        "DFEDQHzbb2bVDIpKzEtF2C4VGVRgG9Nr8WUFWSq+rxwLvJC2v7Di8sXJ7m37" +
                                                        "7zx5WgwVZfBlNjoq5nH4vLAbbm6WaJmRWpZWYGvb/XmXKpdnfVjQij2yLS3e" +
                                                        "sDo0k4kWM/rxog83npm8LlrmP2WhaG/4DgAA");
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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "Dcl/Meb0mFIQAAA=");
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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
