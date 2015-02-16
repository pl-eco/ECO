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
          1169964658000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "fw8AAA==");
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
      1169964658000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
