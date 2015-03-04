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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
