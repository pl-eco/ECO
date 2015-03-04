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
        public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXXWwURXhu215/KPSPn4qltOUQC3gLGoxQosLZQutJLxRI" +
                                                        "PCJ1ujd3t3Rvd5mda6/FKpCYEh+IiQXBYB8MBEP4i5GoMSR9UTH4ojEaHwTj" +
                                                        "iyTIAw8iERW/md27vdtea3zwkp2bnfn+//fcbVRmUbTKNLSRhGawIMmw4F5t" +
                                                        "XZCNmMQK9oTXRTC1SCykYcvaAWf9Stulmrv330zWSsgfRQ1Y1w2GmWro1nZi" +
                                                        "GdoQiYVRjXvaqZGUxVBteC8ewnKaqZocVi3WEUZz8lAZCoSzIsggggwiyEIE" +
                                                        "eZMLBUhziZ5OhTgG1pm1D72KfGHkNxUuHkOthURMTHHKIRMRGgCFCv6+C5QS" +
                                                        "yBmKWnK62zpPU/joKnni7T21H5SgmiiqUfU+Lo4CQjBgEkXVKZIaINTaFIuR" +
                                                        "WBTV6YTE+ghVsaaOCrmjqN5SEzpmaUpyRuKHaZNQwdO1XLXCdaNphRk0p15c" +
                                                        "JVos+1YW13ACdF3o6mpr2MXPQcEqFQSjcayQLErpoKrHGFrqxcjpGHgeAAC1" +
                                                        "PEVY0sixKtUxHKB623ca1hNyH6OqngDQMiMNXBhaPCNRbmsTK4M4QfoZavTC" +
                                                        "RewrgKoUhuAoDC3wgglK4KXFHi/l+ef2to1H9utbdUnIHCOKxuWvAKRmD9J2" +
                                                        "EieU6AqxEatXho/hhVcOSwgB8AIPsA3z0St3nl3dPHXVhnm4CEzvwF6isH7l" +
                                                        "1MC8r5tC7etLuBgVpmGp3PkFmovwjzg3HRkTMm9hjiK/DGYvp7Z//uKBs+SW" +
                                                        "hKq6kV8xtHQK4qhOMVKmqhG6heiEYkZi3aiS6LGQuO9G5bAPqzqxT3vjcYuw" +
                                                        "blSqiSO/Id7BRHEgwU1UDntVjxvZvYlZUuwzJkKoAR7UCM96ZP/EP0MROWmk" +
                                                        "iIwVrKu6IUPsEkyVpEwUQ7ZwytTAa1Zaj2vGsGxRRTZoIveuGJRA/ieSDDJk" +
                                                        "iNAgjyzzf6CZ4XrUDvt8YOImb4JrkBtbDS1GaL8ykd7ceedC/zUpF/COBRha" +
                                                        "AVyCDpcg5xLM4xIIYSVJOnVGR5DPJ/jM54xtN4ITBiGdodBVt/e91PPy4bYS" +
                                                        "iB9zuBQsyEHbQDFHmk7FCLk53y0qmwKB1/je7vHgvTPP2IEnz1ygi2KjqePD" +
                                                        "B3e9tkZCUmGl5drBURVHj/D6mKuDAW+GFaNbM37z7sVjY4abawWl2ykB0zF5" +
                                                        "Crd5/UANhcSgKLrkV7bgy/1XxgISKoW6ALWQYYhdKDPNXh4FqdyRLYtclzJQ" +
                                                        "OG7QFNb4VbaWVbEkNYbdExEg8/hSb8cKd6BHQFFRuz6ZOnH5nVXrpfziW5PX" +
                                                        "zvoIs1O5zvX/DkoInP94PPLW0dvju4XzAWJZMQYBvoYgscEbYLHXr+774cb1" +
                                                        "U99KbsAwVG5SdQjyPQNEHnHZQN5rUHu4XwM79ZQRU+MqHtAID7w/a5avvfzr" +
                                                        "kVrbUxqcZB29+t8JuOcPbUYHru35vVmQ8Sm877iqu2C2BRpcypsoxSNcjszB" +
                                                        "b5ac+AK/C2URSpGljhJRXZBQDQnbB4VL2sX6mOduDV9azGl3GXHSKN5KgHX7" +
                                                        "zAnSxdtnXmL90asNHPr5ntBoWmoU6Roe/Kh87uTi0NO3BL4boxx7aWZ6tYFR" +
                                                        "w8V9/GzqN6nN/5mEyqOoVnHmmF1YS/NwiULvtrLDDcw6BfeFfdhuOh25HGzy" +
                                                        "5kceW292uFUO9hya76vshBAwdWDTCuS0gA1O9Rf//LbB5Ov8jA+JzVMCpVWs" +
                                                        "Ab6scDzEkKRkZvdMhKopaHdDTj+Wx+pvDJ68ed4ueV43eIDJ4Yk3HgSPTEh5" +
                                                        "E86yaUNGPo495Qg959p6PoCfD56/+cOF5gd2l6sPOa22JddrTZOr0zqbWIJF" +
                                                        "1y8Xxz59f2zcVqO+sMF3wvx6/ru/vgoe/+nLIj2nBIY3UY7siH/iv/tjC182" +
                                                        "cOOP8N2zM1NbAE+HQ61jBmo9DrWyuEote+BeAHaetTP2iYZt880Ujw6Jbx9l" +
                                                        "yG+JWTo/mRE38pKZpj1h4FOHJiZjvafXSk6NeA4IOUN4Ph2GqtwmnRW9aTbR" +
                                                        "gXPjtK8Be4JVLkzWVCya3Pm9aE25KbMSRr14WtPyUypv7zcpiatCyEo7wUzx" +
                                                        "txu+iLyCMFTK/4SgURtsD0Nz8sCgDzi7fCAMUQNAfDtgZtWsFZWYl4qgXSoy" +
                                                        "qMA2ptfiywqyVHw5ORZ4IW1/O/UrFyd7tu2/8+RpMVSUwTfX6KiYtOHDwW64" +
                                                        "uVmidUZqWVr+re33512qXJ71YUEr9si2tHjD6kyZTLSY0Y8XfbjxzOR10TL/" +
                                                        "AUcg6mbSDgAA");
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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "B5e4MyZNObm4NatZYvwXXd1K3+YsAAA=");
}
