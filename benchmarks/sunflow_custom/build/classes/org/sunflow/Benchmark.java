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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "W/sf/j4xr48PAAA=");
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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1ZfWwUxxWfO38bg40N2DFfxtgEQ3pXWpE2OKUB1xiDiS0w" +
       "SHWamPXenL14b3fZnbMPEhIgrUD5A1UtpKQlVlVBKQkfaRWaRm0qKrVJUNJI" +
       "RFGrNkqgkVBRKFKolBQlTel7M3u7e3t3iw/RWprn3fl483uf8+b25DVSYplk" +
       "qaGrO4ZVnUVoikW2qcsjbIdBrci6nuV9kmnRWIcqWVY/9A3KzS9Uf/LZd0dq" +
       "wqR0gNRJmqYziSm6Zm2klq6O0VgPqXZ7O1WasBip6dkmjUnRJFPUaI9isfYe" +
       "MsWzlJGWnjSEKECIAoQohxBd5c6CRVOplkx04ApJY9Z28jgJ9ZBSQ0Z4jCzI" +
       "ZGJIppSw2fRxCYBDOb5vAaH44pRJmhzZhcxZAh9aGj34g0dqflFEqgdItaJt" +
       "QjgygGCwyQCpStDEEDWtVbEYjQ2Q6RqlsU3UVCRV2clxD5BaSxnWJJY0qaMk" +
       "7Ewa1OR7upqrklE2Mykz3XTEiytUjaXfSuKqNAyyznJlFRKuwX4QsFIBYGZc" +
       "kml6SfGoosUYme9f4cjYsh4mwNKyBGUjurNVsSZBB6kVtlMlbTi6iZmKNgxT" +
       "S/Qk7MJIY16mqGtDkkelYTrISIN/Xp8YglkVXBG4hJGZ/mmcE1ip0Wclj32u" +
       "PXj/gUe1tVqYY45RWUX85bBonm/RRhqnJtVkKhZWLel5Wpr1yv4wITB5pm+y" +
       "mPPSY9cfuGfeudfFnNk55vQObaMyG5SPDk27MKej7b4ihFFu6JaCxs+QnLt/" +
       "nz3SnjIg8mY5HHEwkh48t/HVb+5+jl4Nk8puUirrajIBfjRd1hOGolKzi2rU" +
       "lBiNdZMKqsU6+Hg3KYPnHkWjorc3Hrco6ybFKu8q1fk7qCgOLFBFZfCsaHE9" +
       "/WxIbIQ/pwxCSBk00gVtPhF//D8j34qO6AkalWRJUzQ9Cr5LJVMeiVJZHzSp" +
       "oUc7O3qjQ6DlkYRkjlpRK6nFVX18UE5aTE9ELVOO6uZwuju6Oj0zgl5m/I/5" +
       "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" +
       "BSoVS5tZIAAA");
}
