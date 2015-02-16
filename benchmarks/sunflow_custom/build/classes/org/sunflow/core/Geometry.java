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
      1170609110000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "5l+69We7n12+xp9J/gcRCJik7RgAAA==");
}
