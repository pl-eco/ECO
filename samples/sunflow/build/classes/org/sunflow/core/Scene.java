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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "1N+AIwAA");
}
