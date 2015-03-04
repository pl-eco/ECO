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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "TPI/CjvC3dcYAAA=");
}
