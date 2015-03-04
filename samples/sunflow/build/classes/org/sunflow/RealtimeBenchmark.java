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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "+QfeD+CsGA4AAA==");
}
