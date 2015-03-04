package org.sunflow.core;
import org.sunflow.SunflowAPI;
import org.sunflow.core.accel.NullAccelerator;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class Geometry implements RenderObject {
    private Tesselatable tesselatable;
    private PrimitiveList primitives;
    private AccelerationStructure accel;
    private int builtAccel;
    private int builtTess;
    private String acceltype;
    public Geometry(Tesselatable tesselatable) { super();
                                                 this.tesselatable = tesselatable;
                                                 primitives = null;
                                                 accel = null;
                                                 builtAccel = 0;
                                                 builtTess = 0;
                                                 acceltype = null; }
    public Geometry(PrimitiveList primitives) { super();
                                                tesselatable = null;
                                                this.primitives = primitives;
                                                accel = null;
                                                builtAccel = 0;
                                                builtTess = 1; }
    public boolean update(ParameterList pl, SunflowAPI api) { acceltype =
                                                                pl.
                                                                  getString(
                                                                    "accel",
                                                                    acceltype);
                                                              if (tesselatable !=
                                                                    null) {
                                                                  primitives =
                                                                    null;
                                                                  builtTess =
                                                                    0;
                                                              }
                                                              accel =
                                                                null;
                                                              builtAccel =
                                                                0;
                                                              if (tesselatable !=
                                                                    null)
                                                                  return tesselatable.
                                                                    update(
                                                                      pl,
                                                                      api);
                                                              return primitives.
                                                                update(
                                                                  pl,
                                                                  api);
    }
    int getNumPrimitives() { return primitives ==
                               null
                               ? 0
                               : primitives.
                               getNumPrimitives(
                                 ); }
    BoundingBox getWorldBounds(Matrix4 o2w) { if (primitives ==
                                                    null) {
                                                  BoundingBox b =
                                                    tesselatable.
                                                    getWorldBounds(
                                                      o2w);
                                                  if (b !=
                                                        null)
                                                      return b;
                                                  if (builtTess ==
                                                        0)
                                                      tesselate(
                                                        );
                                                  if (primitives ==
                                                        null)
                                                      return null;
                                              }
                                              return primitives.
                                                getWorldBounds(
                                                  o2w);
    }
    void intersect(Ray r, IntersectionState state) {
        if (builtTess ==
              0)
            tesselate(
              );
        if (builtAccel ==
              0)
            build(
              );
        accel.
          intersect(
            r,
            state);
    }
    private synchronized void tesselate() { if (builtTess !=
                                                  0)
                                                return;
                                            if (tesselatable !=
                                                  null &&
                                                  primitives ==
                                                  null) {
                                                UI.
                                                  printInfo(
                                                    Module.
                                                      GEOM,
                                                    "Tesselating geometry ...");
                                                primitives =
                                                  tesselatable.
                                                    tesselate(
                                                      );
                                                if (primitives ==
                                                      null)
                                                    UI.
                                                      printError(
                                                        Module.
                                                          GEOM,
                                                        "Tesselation failed - geometry will be discarded");
                                                else
                                                    UI.
                                                      printDetailed(
                                                        Module.
                                                          GEOM,
                                                        "Tesselation produced %d primitives",
                                                        primitives.
                                                          getNumPrimitives(
                                                            ));
                                            }
                                            builtTess =
                                              1;
    }
    private synchronized void build() { if (builtAccel !=
                                              0)
                                            return;
                                        if (primitives !=
                                              null) {
                                            int n =
                                              primitives.
                                              getNumPrimitives(
                                                );
                                            if (n >=
                                                  1000)
                                                UI.
                                                  printInfo(
                                                    Module.
                                                      GEOM,
                                                    "Building acceleration structure for %d primitives ...",
                                                    n);
                                            accel =
                                              AccelerationStructureFactory.
                                                create(
                                                  acceltype,
                                                  n,
                                                  true);
                                            accel.
                                              build(
                                                primitives);
                                        }
                                        else {
                                            accel =
                                              new NullAccelerator(
                                                );
                                        }
                                        builtAccel =
                                          1;
    }
    void prepareShadingState(ShadingState state) {
        primitives.
          prepareShadingState(
            state);
    }
    PrimitiveList getBakingPrimitives() {
        if (builtTess ==
              0)
            tesselate(
              );
        if (primitives ==
              null)
            return null;
        return primitives.
          getBakingPrimitives(
            );
    }
    PrimitiveList getPrimitiveList() { return primitives;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "+uMW4+8K8y/d+rPdzy5f488k/wMQEu8n/RgAAA==");
}
