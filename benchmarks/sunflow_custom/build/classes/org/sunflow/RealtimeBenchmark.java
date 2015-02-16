package org.sunflow;
import org.sunflow.core.Display;
import org.sunflow.core.Tesselatable;
import org.sunflow.core.camera.PinholeLens;
import org.sunflow.core.display.FastDisplay;
import org.sunflow.core.display.FileDisplay;
import org.sunflow.core.light.DirectionalSpotlight;
import org.sunflow.core.primitive.Plane;
import org.sunflow.core.shader.DiffuseShader;
import org.sunflow.core.shader.ShinyDiffuseShader;
import org.sunflow.core.tesselatable.Gumbo;
import org.sunflow.core.tesselatable.Teapot;
import org.sunflow.image.Color;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
import org.sunflow.system.ui.ConsoleInterface;
class $UTILMODES{
    public static final int $MAX = 2;
    public static final int high = 2;
    public static final int low = 1;
    public static final int mid = 0;
}
public class RealtimeBenchmark extends SunflowAPI {
    public RealtimeBenchmark(boolean showGUI, int threads) { super();
                                                             Display display =
                                                               showGUI
                                                               ? new FastDisplay(
                                                               )
                                                               : new FileDisplay(
                                                               false);
                                                             UI.
                                                               printInfo(
                                                                 Module.
                                                                   BENCH,
                                                                 "Preparing benchmarking scene ...");
                                                             parameter(
                                                               "threads",
                                                               threads);
                                                             parameter(
                                                               "threads.lowPriority",
                                                               false);
                                                             parameter(
                                                               "resolutionX",
                                                               512);
                                                             parameter(
                                                               "resolutionY",
                                                               512);
                                                             parameter(
                                                               "aa.min",
                                                               -3);
                                                             parameter(
                                                               "aa.max",
                                                               0);
                                                             parameter(
                                                               "depths.diffuse",
                                                               1);
                                                             parameter(
                                                               "depths.reflection",
                                                               1);
                                                             parameter(
                                                               "depths.refraction",
                                                               0);
                                                             parameter(
                                                               "bucket.order",
                                                               "hilbert");
                                                             parameter(
                                                               "bucket.size",
                                                               32);
                                                             options(
                                                               SunflowAPI.
                                                                 DEFAULT_OPTIONS);
                                                             Point3 eye =
                                                               new Point3(
                                                               30,
                                                               0,
                                                               10.967F);
                                                             Point3 target =
                                                               new Point3(
                                                               0,
                                                               0,
                                                               5.4F);
                                                             Vector3 up =
                                                               new Vector3(
                                                               0,
                                                               0,
                                                               1);
                                                             parameter(
                                                               "eye",
                                                               eye);
                                                             parameter(
                                                               "target",
                                                               target);
                                                             parameter(
                                                               "up",
                                                               up);
                                                             parameter(
                                                               "fov",
                                                               45.0F);
                                                             String name =
                                                               getUniqueName(
                                                                 "camera");
                                                             camera(
                                                               name,
                                                               new PinholeLens(
                                                                 ));
                                                             parameter(
                                                               "camera",
                                                               name);
                                                             options(
                                                               SunflowAPI.
                                                                 DEFAULT_OPTIONS);
                                                             createGeometry(
                                                               );
                                                             UI.
                                                               printInfo(
                                                                 Module.
                                                                   BENCH,
                                                                 "Rendering warmup frame ...");
                                                             render(
                                                               SunflowAPI.
                                                                 DEFAULT_OPTIONS,
                                                               display);
                                                             Timer t =
                                                               new Timer(
                                                               );
                                                             t.
                                                               start(
                                                                 );
                                                             final double[] phi = {
                                                               0 };
                                                             final double[] totalPhi = {
                                                               Math.
                                                                 PI *
                                                               32 };
                                                             int frames =
                                                               0;
                                                             ecor.CalibratorStack.push(new ecor.Calibrator() {
                                                             double supplyRatio = 0;
                                                             public int mode = $UTILMODES.$MAX;
                                                             public int getMode(int max) {
                                                             return mode;
                                                             }
                                                             public int supply() {
                                                             return ecor.bsupply.BatterySupply.sharedSupply().getRemainingCapacity();}
                                                             private double supplyLimit = (ecor.bsupply.BatterySupply.sharedSupply().getRemainingCapacity()) *
                                                               0.1;
                                                             private int initialSupply = this.supply();
                                                             public Object calibrate(Object input) {
                                                             supplyRatio = (supplyLimit - (initialSupply - this.supply()))/supplyLimit;
                                                             return input;
                                                             }
                                                             public void adjust() {
                                                             double demandRatio = (double)(totalPhi[0] -
                                                               phi[0])/(totalPhi[0]);
                                                             System.out.println(supplyRatio + " " + demandRatio);
                                                             if (supplyRatio > demandRatio * 1.1 && mode < $UTILMODES.$MAX) ++mode;
                                                             else if (demandRatio > supplyRatio * 1.1 && mode > 0) --mode;
                                                             }
                                                             });
                                                             {
                                                                 while (phi[0] <
                                                                          totalPhi[0]) {
                                                                     eye.
                                                                       x =
                                                                       30 *
                                                                         (float)
                                                                           Math.
                                                                           cos(
                                                                             phi[0]);
                                                                     eye.
                                                                       y =
                                                                       30 *
                                                                         (float)
                                                                           Math.
                                                                           sin(
                                                                             phi[0]);
                                                                     ecor.CalibratorStack.calibrate(phi[0] +=
                                                                       Math.
                                                                         PI /
                                                                         30);
                                                                     frames++;
                                                                     parameter(
                                                                       "eye",
                                                                       eye);
                                                                     parameter(
                                                                       "target",
                                                                       target);
                                                                     parameter(
                                                                       "up",
                                                                       up);
                                                                     camera(
                                                                       name,
                                                                       null);
                                                                     render(
                                                                       SunflowAPI.
                                                                         DEFAULT_OPTIONS,
                                                                       display);
                                                                 }
                                                             }
                                                             ecor.CalibratorStack.pop();
                                                             ;
                                                             t.
                                                               end(
                                                                 );
                                                             UI.
                                                               set(
                                                                 new ConsoleInterface(
                                                                   ));
                                                             UI.
                                                               printInfo(
                                                                 Module.
                                                                   BENCH,
                                                                 "Benchmark results:");
                                                             UI.
                                                               printInfo(
                                                                 Module.
                                                                   BENCH,
                                                                 "  * Average FPS:         %.2f",
                                                                 frames /
                                                                   t.
                                                                   seconds(
                                                                     ));
                                                             UI.
                                                               printInfo(
                                                                 Module.
                                                                   BENCH,
                                                                 "  * Total time:          %s",
                                                                 t);
    }
    private void createGeometry() { parameter(
                                      "source",
                                      new Point3(
                                        -15.5945F,
                                        -30.0581F,
                                        45.967F));
                                    parameter(
                                      "dir",
                                      new Vector3(
                                        15.5945F,
                                        30.0581F,
                                        -45.967F));
                                    parameter(
                                      "radius",
                                      60.0F);
                                    parameter(
                                      "radiance",
                                      Color.
                                        white(
                                          ).
                                        mul(
                                          3));
                                    light(
                                      "light",
                                      new DirectionalSpotlight(
                                        ));
                                    parameter(
                                      "gi.engine",
                                      "fake");
                                    parameter(
                                      "gi.fake.sky",
                                      new Color(
                                        0.25F,
                                        0.25F,
                                        0.25F));
                                    parameter(
                                      "gi.fake.ground",
                                      new Color(
                                        0.01F,
                                        0.01F,
                                        0.5F));
                                    parameter(
                                      "gi.fake.up",
                                      new Vector3(
                                        0,
                                        0,
                                        1));
                                    options(
                                      DEFAULT_OPTIONS);
                                    parameter(
                                      "diffuse",
                                      Color.
                                        white(
                                          ).
                                        mul(
                                          0.5F));
                                    shader(
                                      "default",
                                      new DiffuseShader(
                                        ));
                                    parameter(
                                      "diffuse",
                                      Color.
                                        white(
                                          ).
                                        mul(
                                          0.5F));
                                    parameter(
                                      "shiny",
                                      0.2F);
                                    shader(
                                      "refl",
                                      new ShinyDiffuseShader(
                                        ));
                                    parameter(
                                      "subdivs",
                                      10);
                                    geometry(
                                      "teapot",
                                      (Tesselatable)
                                        new Teapot(
                                        ));
                                    parameter(
                                      "shaders",
                                      "default");
                                    Matrix4 m =
                                      Matrix4.
                                        IDENTITY;
                                    m = Matrix4.
                                          scale(
                                            0.075F).
                                          multiply(
                                            m);
                                    m = Matrix4.
                                          rotateZ(
                                            (float)
                                              Math.
                                              toRadians(
                                                -45.0F)).
                                          multiply(
                                            m);
                                    m = Matrix4.
                                          translation(
                                            -7,
                                            0,
                                            0).
                                          multiply(
                                            m);
                                    parameter(
                                      "transform",
                                      m);
                                    instance(
                                      "teapot.instance",
                                      "teapot");
                                    parameter(
                                      "subdivs",
                                      10);
                                    geometry(
                                      "gumbo",
                                      (Tesselatable)
                                        new Gumbo(
                                        ));
                                    m = Matrix4.
                                          IDENTITY;
                                    m = Matrix4.
                                          scale(
                                            0.5F).
                                          multiply(
                                            m);
                                    m = Matrix4.
                                          rotateZ(
                                            (float)
                                              Math.
                                              toRadians(
                                                25.0F)).
                                          multiply(
                                            m);
                                    m = Matrix4.
                                          translation(
                                            3,
                                            -7,
                                            0).
                                          multiply(
                                            m);
                                    parameter(
                                      "shaders",
                                      "default");
                                    parameter(
                                      "transform",
                                      m);
                                    instance(
                                      "gumbo.instance",
                                      "gumbo");
                                    parameter(
                                      "center",
                                      new Point3(
                                        0,
                                        0,
                                        0));
                                    parameter(
                                      "normal",
                                      new Vector3(
                                        0,
                                        0,
                                        1));
                                    geometry(
                                      "ground",
                                      new Plane(
                                        ));
                                    parameter(
                                      "shaders",
                                      "refl");
                                    instance(
                                      "ground.instance",
                                      "ground");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1421803664000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "s/Xnp+6J3vIPe6JZ8i4OAAA=");
}
