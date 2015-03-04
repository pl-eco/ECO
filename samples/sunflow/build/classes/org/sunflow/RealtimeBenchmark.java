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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "8djnSz7beH7qnugt/wDcwbyVPg4AAA==");
}
