package org.sunflow.core;
import org.sunflow.core.display.FrameDisplay;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.MathUtils;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
class $UTILMODES{
    public static final int $MAX = 2;
    public static final int high = 2;
    public static final int low = 1;
    public static final int mid = 0;
}
public class Scene {
    private LightServer lightServer;
    private InstanceList instanceList;
    private InstanceList infiniteInstanceList;
    private Camera camera;
    private AccelerationStructure intAccel;
    private String acceltype;
    private boolean bakingViewDependent;
    private Instance bakingInstance;
    private PrimitiveList bakingPrimitives;
    private AccelerationStructure bakingAccel;
    private boolean rebuildAccel;
    private int[] imageWidth = new int[] { 480, 800, 1024, };
    private int[] imageHeight = new int[] { 320, 600, 728, };
    private int threads;
    private boolean lowPriority;
    public Scene() { super();
                     lightServer = new LightServer(this);
                     instanceList = new InstanceList();
                     infiniteInstanceList = new InstanceList();
                     acceltype = "auto";
                     bakingViewDependent = false;
                     bakingInstance = null;
                     bakingPrimitives = null;
                     bakingAccel = null;
                     camera = null;
                     threads = 0;
                     lowPriority = true;
                     rebuildAccel = true; }
    public int getThreads() { return threads <= 0 ? Runtime.getRuntime().
                                availableProcessors(
                                  )
                                : threads; }
    public int getThreadPriority() { return lowPriority ? Thread.MIN_PRIORITY
                                       : Thread.
                                           NORM_PRIORITY; }
    public void setCamera(Camera camera) { this.camera = camera; }
    Camera getCamera() { return camera; }
    public void setInstanceLists(Instance[] instances, Instance[] infinite) {
        infiniteInstanceList =
          new InstanceList(
            infinite);
        instanceList =
          new InstanceList(
            instances);
        rebuildAccel =
          true;
    }
    public void setLightList(LightSource[] lights) { lightServer.setLights(
                                                                   lights);
    }
    public void setShaderOverride(Shader shader, boolean photonOverride) {
        lightServer.
          setShaderOverride(
            shader,
            photonOverride);
    }
    public void setBakingInstance(Instance instance) { bakingInstance = instance;
    }
    public ShadingState getRadiance(IntersectionState istate, float rx,
                                    float ry,
                                    double lensU,
                                    double lensV,
                                    double time,
                                    int instance) { if (bakingPrimitives ==
                                                          null) {
                                                        Ray r =
                                                          camera.
                                                          getRay(
                                                            rx,
                                                            ry,
                                                            imageWidth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)],
                                                            imageHeight[ecor.CalibratorStack.getMode($UTILMODES.$MAX)],
                                                            lensU,
                                                            lensV,
                                                            time);
                                                        return r !=
                                                          null
                                                          ? lightServer.
                                                          getRadiance(
                                                            rx,
                                                            ry,
                                                            instance,
                                                            r,
                                                            istate)
                                                          : null;
                                                    }
                                                    else {
                                                        Ray r =
                                                          new Ray(
                                                          rx /
                                                            (imageWidth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]),
                                                          ry /
                                                            (imageHeight[ecor.CalibratorStack.getMode($UTILMODES.$MAX)]),
                                                          -1,
                                                          0,
                                                          0,
                                                          1);
                                                        traceBake(
                                                          r,
                                                          istate);
                                                        if (!istate.
                                                              hit(
                                                                ))
                                                            return null;
                                                        ShadingState state =
                                                          ShadingState.
                                                          createState(
                                                            istate,
                                                            rx,
                                                            ry,
                                                            r,
                                                            instance,
                                                            lightServer);
                                                        bakingPrimitives.
                                                          prepareShadingState(
                                                            state);
                                                        if (bakingViewDependent)
                                                            state.
                                                              setRay(
                                                                camera.
                                                                  getRay(
                                                                    state.
                                                                      getPoint(
                                                                        )));
                                                        else {
                                                            Point3 p =
                                                              state.
                                                              getPoint(
                                                                );
                                                            Vector3 n =
                                                              state.
                                                              getNormal(
                                                                );
                                                            Ray incoming =
                                                              new Ray(
                                                              p.
                                                                x +
                                                                n.
                                                                  x,
                                                              p.
                                                                y +
                                                                n.
                                                                  y,
                                                              p.
                                                                z +
                                                                n.
                                                                  z,
                                                              -n.
                                                                 x,
                                                              -n.
                                                                 y,
                                                              -n.
                                                                 z);
                                                            incoming.
                                                              setMax(
                                                                1);
                                                            state.
                                                              setRay(
                                                                incoming);
                                                        }
                                                        lightServer.
                                                          shadeBakeResult(
                                                            state);
                                                        return state;
                                                    }
    }
    public BoundingBox getBounds() { return instanceList.
                                       getWorldBounds(
                                         null);
    }
    void trace(Ray r, IntersectionState state) {
        state.
          instance =
          null;
        state.
          current =
          null;
        for (int i =
               0;
             i <
               infiniteInstanceList.
               getNumPrimitives(
                 );
             i++)
            infiniteInstanceList.
              intersectPrimitive(
                r,
                i,
                state);
        state.
          current =
          null;
        intAccel.
          intersect(
            r,
            state);
    }
    Color traceShadow(Ray r, IntersectionState state) {
        trace(
          r,
          state);
        return state.
          hit(
            )
          ? Color.
              WHITE
          : Color.
              BLACK;
    }
    void traceBake(Ray r, IntersectionState state) {
        state.
          current =
          bakingInstance;
        state.
          instance =
          null;
        bakingAccel.
          intersect(
            r,
            state);
    }
    public void render(Options options, ImageSampler sampler,
                       Display display) {
        if (display ==
              null)
            display =
              new FrameDisplay(
                );
        if (bakingInstance !=
              null) {
            UI.
              printDetailed(
                Module.
                  SCENE,
                "Creating primitives for lightmapping ...");
            bakingPrimitives =
              bakingInstance.
                getBakingPrimitives(
                  );
            if (bakingPrimitives ==
                  null) {
                UI.
                  printError(
                    Module.
                      SCENE,
                    "Lightmap baking is not supported for the given instance.");
                return;
            }
            int n =
              bakingPrimitives.
              getNumPrimitives(
                );
            UI.
              printInfo(
                Module.
                  SCENE,
                ("Building acceleration structure for lightmapping (%d num pri" +
                 "mitives) ..."),
                n);
            bakingAccel =
              AccelerationStructureFactory.
                create(
                  "auto",
                  n,
                  true);
            bakingAccel.
              build(
                bakingPrimitives);
        }
        else {
            bakingPrimitives =
              null;
            bakingAccel =
              null;
        }
        bakingViewDependent =
          options.
            getBoolean(
              "baking.viewdep",
              bakingViewDependent);
        if (bakingInstance !=
              null &&
              bakingViewDependent &&
              camera ==
              null ||
              bakingInstance ==
              null &&
              camera ==
              null) {
            UI.
              printError(
                Module.
                  SCENE,
                "No camera found");
            return;
        }
        threads =
          options.
            getInt(
              "threads",
              0);
        lowPriority =
          options.
            getBoolean(
              "threads.lowPriority",
              true);
        int imageWidthTemp =
          MathUtils.
          clamp(
            imageWidth[ecor.CalibratorStack.getMode($UTILMODES.$MAX)],
            1,
            1 <<
              14);
        int imageHeightTemp =
          MathUtils.
          clamp(
            imageHeight[ecor.CalibratorStack.getMode($UTILMODES.$MAX)],
            1,
            1 <<
              14);
        long numPrimitives =
          0;
        for (int i =
               0;
             i <
               instanceList.
               getNumPrimitives(
                 );
             i++)
            numPrimitives +=
              instanceList.
                getNumPrimitives(
                  i);
        UI.
          printInfo(
            Module.
              SCENE,
            "Scene stats:");
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Infinite instances:  %d",
            infiniteInstanceList.
              getNumPrimitives(
                ));
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Instances:           %d",
            instanceList.
              getNumPrimitives(
                ));
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Primitives:          %d",
            numPrimitives);
        String accelName =
          options.
          getString(
            "accel",
            null);
        if (accelName !=
              null) {
            rebuildAccel =
              rebuildAccel ||
                !acceltype.
                equals(
                  accelName);
            acceltype =
              accelName;
        }
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Instance accel:      %s",
            acceltype);
        if (rebuildAccel) {
            intAccel =
              AccelerationStructureFactory.
                create(
                  acceltype,
                  instanceList.
                    getNumPrimitives(
                      ),
                  false);
            intAccel.
              build(
                instanceList);
            rebuildAccel =
              false;
        }
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Scene bounds:        %s",
            getBounds(
              ));
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Scene center:        %s",
            getBounds(
              ).
              getCenter(
                ));
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Scene diameter:      %.2f",
            getBounds(
              ).
              getExtents(
                ).
              length(
                ));
        UI.
          printInfo(
            Module.
              SCENE,
            "  * Lightmap bake:       %s",
            bakingInstance !=
              null
              ? (bakingViewDependent
                   ? "view"
                   : "ortho")
              : "off");
        if (sampler ==
              null)
            return;
        if (!lightServer.
              build(
                options))
            return;
        UI.
          printInfo(
            Module.
              SCENE,
            "Rendering ...");
        sampler.
          prepare(
            options,
            this,
            imageWidthTemp,
            imageHeightTemp);
        sampler.
          render(
            display);
        lightServer.
          showStats(
            );
        bakingPrimitives =
          null;
        bakingAccel =
          null;
        UI.
          printInfo(
            Module.
              SCENE,
            "Done.");
    }
    public boolean calculatePhotons(PhotonStore map,
                                    String type,
                                    int seed) {
        return lightServer.
          calculatePhotons(
            map,
            type,
            seed);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1421803822000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "AAA=");
}
