package org.sunflow;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.sunflow.core.Display;
import org.sunflow.core.Tesselatable;
import org.sunflow.core.camera.PinholeLens;
import org.sunflow.core.display.FileDisplay;
import org.sunflow.core.light.TriangleMeshLight;
import org.sunflow.core.primitive.Sphere;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.core.shader.DiffuseShader;
import org.sunflow.core.shader.GlassShader;
import org.sunflow.core.shader.MirrorShader;
import org.sunflow.core.tesselatable.Teapot;
import org.sunflow.image.Color;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.BenchmarkFramework;
import org.sunflow.system.BenchmarkTest;
import org.sunflow.system.UI;
import org.sunflow.system.UserInterface;
import org.sunflow.system.UI.Module;
import org.sunflow.system.UI.PrintLevel;
public class Benchmark implements BenchmarkTest, UserInterface, Display {
    private int resolution;
    private boolean showOutput;
    private boolean showBenchmarkOutput;
    private boolean saveOutput;
    private int threads;
    private int[] referenceImage;
    private int[] validationImage;
    private int errorThreshold;
    public static void main(String[] args) { if (args.length == 0) { System.
                                                                       out.
                                                                       println(
                                                                         "Benchmark options:");
                                                                     System.
                                                                       out.
                                                                       println(
                                                                         ("  -regen                        Regenerate reference images " +
                                                                          "for a variety of sizes"));
                                                                     System.
                                                                       out.
                                                                       println(
                                                                         ("  -bench [threads] [resolution] Run a single iteration of th" +
                                                                          "e benchmark using the specified thread count and image resol" +
                                                                          "ution"));
                                                                     System.
                                                                       out.
                                                                       println(
                                                                         ("                                Default: threads=0 (auto-det" +
                                                                          "ect cpus), resolution=256"));
                                             }
                                             else
                                                 if (args[0].
                                                       equals(
                                                         "-regen")) {
                                                     int[] sizes =
                                                       { 32,
                                                     64,
                                                     96,
                                                     128,
                                                     256,
                                                     384,
                                                     512 };
                                                     for (int s
                                                           :
                                                           sizes) {
                                                         Benchmark b =
                                                           new Benchmark(
                                                           s,
                                                           true,
                                                           false,
                                                           true);
                                                         b.
                                                           kernelMain(
                                                             );
                                                     }
                                                 }
                                                 else
                                                     if (args[0].
                                                           equals(
                                                             "-bench")) {
                                                         int threads =
                                                           0;
                                                         int resolution =
                                                           256;
                                                         if (args.
                                                               length >
                                                               1)
                                                             threads =
                                                               Integer.
                                                                 parseInt(
                                                                   args[1]);
                                                         if (args.
                                                               length >
                                                               2)
                                                             resolution =
                                                               Integer.
                                                                 parseInt(
                                                                   args[2]);
                                                         Benchmark benchmark =
                                                           new Benchmark(
                                                           resolution,
                                                           false,
                                                           true,
                                                           false,
                                                           threads);
                                                         benchmark.
                                                           kernelBegin(
                                                             );
                                                         benchmark.
                                                           kernelMain(
                                                             );
                                                         benchmark.
                                                           kernelEnd(
                                                             );
                                                     }
    }
    public Benchmark() { this(384, false,
                              true,
                              false); }
    public Benchmark(int resolution, boolean showOutput,
                     boolean showBenchmarkOutput,
                     boolean saveOutput) {
        this(
          resolution,
          showOutput,
          showBenchmarkOutput,
          saveOutput,
          0);
    }
    public Benchmark(int resolution, boolean showOutput,
                     boolean showBenchmarkOutput,
                     boolean saveOutput,
                     int threads) { super(
                                      );
                                    UI.set(
                                         this);
                                    this.
                                      resolution =
                                      resolution;
                                    this.
                                      showOutput =
                                      showOutput;
                                    this.
                                      showBenchmarkOutput =
                                      showBenchmarkOutput;
                                    this.
                                      saveOutput =
                                      saveOutput;
                                    this.
                                      threads =
                                      threads;
                                    errorThreshold =
                                      6;
                                    if (saveOutput)
                                        return;
                                    URL imageURL =
                                      Benchmark.class.
                                      getResource(
                                        String.
                                          format(
                                            "/resources/golden_%04X.png",
                                            resolution));
                                    if (imageURL ==
                                          null)
                                        UI.
                                          printError(
                                            Module.
                                              BENCH,
                                            "Unable to find reference frame!");
                                    UI.printInfo(
                                         Module.
                                           BENCH,
                                         "Loading reference image from: %s",
                                         imageURL);
                                    try {
                                        BufferedImage bi =
                                          ImageIO.
                                          read(
                                            imageURL);
                                        if (bi.
                                              getWidth(
                                                ) !=
                                              resolution ||
                                              bi.
                                              getHeight(
                                                ) !=
                                              resolution)
                                            UI.
                                              printError(
                                                Module.
                                                  BENCH,
                                                ("Reference image has invalid resolution! Expected %dx%d found" +
                                                 " %dx%d"),
                                                resolution,
                                                resolution,
                                                bi.
                                                  getWidth(
                                                    ),
                                                bi.
                                                  getHeight(
                                                    ));
                                        referenceImage =
                                          (new int[resolution *
                                                     resolution]);
                                        for (int y =
                                               0,
                                               i =
                                                 0;
                                             y <
                                               resolution;
                                             y++)
                                            for (int x =
                                                   0;
                                                 x <
                                                   resolution;
                                                 x++,
                                                   i++)
                                                referenceImage[i] =
                                                  bi.
                                                    getRGB(
                                                      x,
                                                      resolution -
                                                        1 -
                                                        y);
                                    }
                                    catch (IOException e) {
                                        UI.
                                          printError(
                                            Module.
                                              BENCH,
                                            "Unable to load reference frame!");
                                    } }
    public void execute() { BenchmarkFramework framework =
                              new BenchmarkFramework(
                              10,
                              600);
                            framework.execute(
                                        this);
    }
    private class BenchmarkScene extends SunflowAPI {
        public BenchmarkScene() { super();
                                  build();
                                  render(
                                    SunflowAPI.
                                      DEFAULT_OPTIONS,
                                    saveOutput
                                      ? new FileDisplay(
                                      String.
                                        format(
                                          "resources/golden_%04X.png",
                                          resolution))
                                      : Benchmark.this);
        }
        public void build() { parameter("threads",
                                        threads);
                              parameter("threads.lowPriority",
                                        false);
                              parameter("resolutionX",
                                        resolution);
                              parameter("resolutionY",
                                        resolution);
                              parameter("aa.min",
                                        -1);
                              parameter("aa.max",
                                        1);
                              parameter("filter",
                                        "triangle");
                              parameter("depths.diffuse",
                                        2);
                              parameter("depths.reflection",
                                        2);
                              parameter("depths.refraction",
                                        2);
                              parameter("bucket.order",
                                        "hilbert");
                              parameter("bucket.size",
                                        32);
                              parameter("gi.engine",
                                        "igi");
                              parameter("gi.igi.samples",
                                        90);
                              parameter("gi.igi.c",
                                        8.0E-6F);
                              options(SunflowAPI.
                                        DEFAULT_OPTIONS);
                              buildCornellBox(
                                ); }
        private void buildCornellBox() { parameter(
                                           "eye",
                                           new Point3(
                                             0,
                                             0,
                                             -600));
                                         parameter(
                                           "target",
                                           new Point3(
                                             0,
                                             0,
                                             0));
                                         parameter(
                                           "up",
                                           new Vector3(
                                             0,
                                             1,
                                             0));
                                         parameter(
                                           "fov",
                                           45.0F);
                                         camera(
                                           "main_camera",
                                           new PinholeLens(
                                             ));
                                         parameter(
                                           "camera",
                                           "main_camera");
                                         options(
                                           SunflowAPI.
                                             DEFAULT_OPTIONS);
                                         Color gray =
                                           new Color(
                                           0.7F,
                                           0.7F,
                                           0.7F);
                                         Color blue =
                                           new Color(
                                           0.25F,
                                           0.25F,
                                           0.8F);
                                         Color red =
                                           new Color(
                                           0.8F,
                                           0.25F,
                                           0.25F);
                                         Color emit =
                                           new Color(
                                           15,
                                           15,
                                           15);
                                         float minX =
                                           -200;
                                         float maxX =
                                           200;
                                         float minY =
                                           -160;
                                         float maxY =
                                           minY +
                                           400;
                                         float minZ =
                                           -250;
                                         float maxZ =
                                           200;
                                         float[] verts =
                                           new float[] { minX,
                                         minY,
                                         minZ,
                                         maxX,
                                         minY,
                                         minZ,
                                         maxX,
                                         minY,
                                         maxZ,
                                         minX,
                                         minY,
                                         maxZ,
                                         minX,
                                         maxY,
                                         minZ,
                                         maxX,
                                         maxY,
                                         minZ,
                                         maxX,
                                         maxY,
                                         maxZ,
                                         minX,
                                         maxY,
                                         maxZ };
                                         int[] indices =
                                           new int[] { 0,
                                         1,
                                         2,
                                         2,
                                         3,
                                         0,
                                         4,
                                         5,
                                         6,
                                         6,
                                         7,
                                         4,
                                         1,
                                         2,
                                         5,
                                         5,
                                         6,
                                         2,
                                         2,
                                         3,
                                         6,
                                         6,
                                         7,
                                         3,
                                         0,
                                         3,
                                         4,
                                         4,
                                         7,
                                         3 };
                                         parameter(
                                           "diffuse",
                                           gray);
                                         shader(
                                           "gray_shader",
                                           new DiffuseShader(
                                             ));
                                         parameter(
                                           "diffuse",
                                           red);
                                         shader(
                                           "red_shader",
                                           new DiffuseShader(
                                             ));
                                         parameter(
                                           "diffuse",
                                           blue);
                                         shader(
                                           "blue_shader",
                                           new DiffuseShader(
                                             ));
                                         parameter(
                                           "triangles",
                                           indices);
                                         parameter(
                                           "points",
                                           "point",
                                           "vertex",
                                           verts);
                                         parameter(
                                           "faceshaders",
                                           new int[] { 0,
                                           0,
                                           0,
                                           0,
                                           1,
                                           1,
                                           0,
                                           0,
                                           2,
                                           2 });
                                         geometry(
                                           "walls",
                                           new TriangleMesh(
                                             ));
                                         parameter(
                                           "shaders",
                                           new String[] { "gray_shader",
                                           "red_shader",
                                           "blue_shader" });
                                         instance(
                                           "walls.instance",
                                           "walls");
                                         parameter(
                                           "points",
                                           "point",
                                           "vertex",
                                           new float[] { -50,
                                           maxY -
                                             1,
                                           -50,
                                           50,
                                           maxY -
                                             1,
                                           -50,
                                           50,
                                           maxY -
                                             1,
                                           50,
                                           -50,
                                           maxY -
                                             1,
                                           50 });
                                         parameter(
                                           "triangles",
                                           new int[] { 0,
                                           1,
                                           2,
                                           2,
                                           3,
                                           0 });
                                         parameter(
                                           "radiance",
                                           emit);
                                         parameter(
                                           "samples",
                                           8);
                                         TriangleMeshLight light =
                                           new TriangleMeshLight(
                                           );
                                         light.
                                           init(
                                             "light",
                                             this);
                                         parameter(
                                           "eta",
                                           1.6F);
                                         shader(
                                           "Glass",
                                           new GlassShader(
                                             ));
                                         sphere(
                                           "glass_sphere",
                                           "Glass",
                                           -120,
                                           minY +
                                             55,
                                           -150,
                                           50);
                                         parameter(
                                           "color",
                                           new Color(
                                             0.7F,
                                             0.7F,
                                             0.7F));
                                         shader(
                                           "Mirror",
                                           new MirrorShader(
                                             ));
                                         sphere(
                                           "mirror_sphere",
                                           "Mirror",
                                           100,
                                           minY +
                                             60,
                                           -50,
                                           50);
                                         geometry(
                                           "teapot",
                                           (Tesselatable)
                                             new Teapot(
                                             ));
                                         parameter(
                                           "transform",
                                           Matrix4.
                                             translation(
                                               80,
                                               -50,
                                               100).
                                             multiply(
                                               Matrix4.
                                                 rotateX(
                                                   (float)
                                                     -Math.
                                                        PI /
                                                     6)).
                                             multiply(
                                               Matrix4.
                                                 rotateY(
                                                   (float)
                                                     Math.
                                                       PI /
                                                     4)).
                                             multiply(
                                               Matrix4.
                                                 rotateX(
                                                   (float)
                                                     -Math.
                                                        PI /
                                                     2).
                                                 multiply(
                                                   Matrix4.
                                                     scale(
                                                       1.2F))));
                                         parameter(
                                           "shaders",
                                           "gray_shader");
                                         instance(
                                           "teapot.instance1",
                                           "teapot");
                                         parameter(
                                           "transform",
                                           Matrix4.
                                             translation(
                                               -80,
                                               -160,
                                               50).
                                             multiply(
                                               Matrix4.
                                                 rotateY(
                                                   (float)
                                                     Math.
                                                       PI /
                                                     4)).
                                             multiply(
                                               Matrix4.
                                                 rotateX(
                                                   (float)
                                                     -Math.
                                                        PI /
                                                     2).
                                                 multiply(
                                                   Matrix4.
                                                     scale(
                                                       1.2F))));
                                         parameter(
                                           "shaders",
                                           "gray_shader");
                                         instance(
                                           "teapot.instance2",
                                           "teapot");
        }
        private void sphere(String name, String shaderName,
                            float x,
                            float y,
                            float z,
                            float radius) {
            geometry(
              name,
              new Sphere(
                ));
            parameter(
              "transform",
              Matrix4.
                translation(
                  x,
                  y,
                  z).
                multiply(
                  Matrix4.
                    scale(
                      radius)));
            parameter(
              "shaders",
              shaderName);
            instance(
              name +
              ".instance",
              name);
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public void kernelBegin() { validationImage =
                                  (new int[resolution *
                                             resolution]);
    }
    public void kernelMain() { new BenchmarkScene(
                                 ); }
    public void kernelEnd() { int diff = 0;
                              if (referenceImage !=
                                    null &&
                                    validationImage.
                                      length ==
                                    referenceImage.
                                      length) {
                                  for (int i =
                                         0;
                                       i <
                                         validationImage.
                                           length;
                                       i++) {
                                      diff +=
                                        Math.
                                          abs(
                                            (validationImage[i] &
                                               255) -
                                              (referenceImage[i] &
                                                 255));
                                      diff +=
                                        Math.
                                          abs(
                                            (validationImage[i] >>
                                               8 &
                                               255) -
                                              (referenceImage[i] >>
                                                 8 &
                                                 255));
                                      diff +=
                                        Math.
                                          abs(
                                            (validationImage[i] >>
                                               16 &
                                               255) -
                                              (referenceImage[i] >>
                                                 16 &
                                                 255));
                                  }
                                  if (diff >
                                        errorThreshold)
                                      UI.
                                        printError(
                                          Module.
                                            BENCH,
                                          "Image check failed! - #errors: %d",
                                          diff);
                                  else
                                      UI.
                                        printInfo(
                                          Module.
                                            BENCH,
                                          "Image check passed!");
                              }
                              else
                                  UI.
                                    printError(
                                      Module.
                                        BENCH,
                                      "Image check failed! - reference is not comparable");
    }
    public void print(Module m, PrintLevel level,
                      String s) { if (showOutput ||
                                        showBenchmarkOutput &&
                                        m ==
                                        Module.
                                          BENCH)
                                      System.
                                        out.
                                        println(
                                          UI.
                                            formatOutput(
                                              m,
                                              level,
                                              s));
                                  if (level ==
                                        PrintLevel.
                                          ERROR)
                                      throw new RuntimeException(
                                        s);
    }
    public void taskStart(String s, int min,
                          int max) {  }
    public void taskStop() {  }
    public void taskUpdate(int current) {
        
    }
    public void imageBegin(int w, int h, int bucketSize) {
        
    }
    public void imageEnd() {  }
    public void imageFill(int x, int y, int w,
                          int h,
                          Color c) {  }
    public void imagePrepare(int x, int y,
                             int w,
                             int h,
                             int id) {  }
    public void imageUpdate(int x, int y,
                            int w,
                            int h,
                            Color[] data) {
        for (int j =
               0,
               index =
                 0;
             j <
               h;
             j++,
               y++)
            for (int i =
                   0,
                   offset =
                     x +
                     resolution *
                     (resolution -
                        1 -
                        y);
                 i <
                   w;
                 i++,
                   index++,
                   offset++)
                validationImage[offset] =
                  data[index].
                    copy(
                      ).
                    toNonLinear(
                      ).
                    toRGB(
                      );
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
