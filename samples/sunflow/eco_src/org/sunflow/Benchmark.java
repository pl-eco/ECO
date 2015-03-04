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
                                                           kernelMain();
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
                                                           kernelBegin();
                                                         benchmark.
                                                           kernelMain();
                                                         benchmark.
                                                           kernelEnd();
                                                     }
    }
    public Benchmark() { this(384, false,
                              true,
                              false); }
    public Benchmark(int resolution, boolean showOutput,
                     boolean showBenchmarkOutput,
                     boolean saveOutput) {
        this(resolution,
             showOutput,
             showBenchmarkOutput,
             saveOutput,
             0);
    }
    public Benchmark(int resolution, boolean showOutput,
                     boolean showBenchmarkOutput,
                     boolean saveOutput,
                     int threads) { super();
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
                                              getWidth() !=
                                              resolution ||
                                              bi.
                                              getHeight() !=
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
                                                  getWidth(),
                                                bi.
                                                  getHeight());
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
                                  this.build();
                                  this.render(
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
        public void build() { this.parameter(
                                     "threads",
                                     threads);
                              this.parameter(
                                     "threads.lowPriority",
                                     false);
                              this.parameter(
                                     "resolutionX",
                                     resolution);
                              this.parameter(
                                     "resolutionY",
                                     resolution);
                              this.parameter(
                                     "aa.min",
                                     -1);
                              this.parameter(
                                     "aa.max",
                                     1);
                              this.parameter(
                                     "filter",
                                     "triangle");
                              this.parameter(
                                     "depths.diffuse",
                                     2);
                              this.parameter(
                                     "depths.reflection",
                                     2);
                              this.parameter(
                                     "depths.refraction",
                                     2);
                              this.parameter(
                                     "bucket.order",
                                     "hilbert");
                              this.parameter(
                                     "bucket.size",
                                     32);
                              this.parameter(
                                     "gi.engine",
                                     "igi");
                              this.parameter(
                                     "gi.igi.samples",
                                     90);
                              this.parameter(
                                     "gi.igi.c",
                                     8.0E-6F);
                              this.options(
                                     SunflowAPI.
                                       DEFAULT_OPTIONS);
                              this.buildCornellBox();
        }
        private void buildCornellBox() { this.
                                           parameter(
                                             "eye",
                                             new Point3(
                                               0,
                                               0,
                                               -600));
                                         this.
                                           parameter(
                                             "target",
                                             new Point3(
                                               0,
                                               0,
                                               0));
                                         this.
                                           parameter(
                                             "up",
                                             new Vector3(
                                               0,
                                               1,
                                               0));
                                         this.
                                           parameter(
                                             "fov",
                                             45.0F);
                                         this.
                                           camera(
                                             "main_camera",
                                             new PinholeLens(
                                               ));
                                         this.
                                           parameter(
                                             "camera",
                                             "main_camera");
                                         this.
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
                                         this.
                                           parameter(
                                             "diffuse",
                                             gray);
                                         this.
                                           shader(
                                             "gray_shader",
                                             new DiffuseShader(
                                               ));
                                         this.
                                           parameter(
                                             "diffuse",
                                             red);
                                         this.
                                           shader(
                                             "red_shader",
                                             new DiffuseShader(
                                               ));
                                         this.
                                           parameter(
                                             "diffuse",
                                             blue);
                                         this.
                                           shader(
                                             "blue_shader",
                                             new DiffuseShader(
                                               ));
                                         this.
                                           parameter(
                                             "triangles",
                                             indices);
                                         this.
                                           parameter(
                                             "points",
                                             "point",
                                             "vertex",
                                             verts);
                                         this.
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
                                         this.
                                           geometry(
                                             "walls",
                                             new TriangleMesh(
                                               ));
                                         this.
                                           parameter(
                                             "shaders",
                                             new String[] { "gray_shader",
                                             "red_shader",
                                             "blue_shader" });
                                         this.
                                           instance(
                                             "walls.instance",
                                             "walls");
                                         this.
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
                                         this.
                                           parameter(
                                             "triangles",
                                             new int[] { 0,
                                             1,
                                             2,
                                             2,
                                             3,
                                             0 });
                                         this.
                                           parameter(
                                             "radiance",
                                             emit);
                                         this.
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
                                         this.
                                           parameter(
                                             "eta",
                                             1.6F);
                                         this.
                                           shader(
                                             "Glass",
                                             new GlassShader(
                                               ));
                                         this.
                                           sphere(
                                             "glass_sphere",
                                             "Glass",
                                             -120,
                                             minY +
                                               55,
                                             -150,
                                             50);
                                         this.
                                           parameter(
                                             "color",
                                             new Color(
                                               0.7F,
                                               0.7F,
                                               0.7F));
                                         this.
                                           shader(
                                             "Mirror",
                                             new MirrorShader(
                                               ));
                                         this.
                                           sphere(
                                             "mirror_sphere",
                                             "Mirror",
                                             100,
                                             minY +
                                               60,
                                             -50,
                                             50);
                                         this.
                                           geometry(
                                             "teapot",
                                             (Tesselatable)
                                               new Teapot(
                                               ));
                                         this.
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
                                         this.
                                           parameter(
                                             "shaders",
                                             "gray_shader");
                                         this.
                                           instance(
                                             "teapot.instance1",
                                             "teapot");
                                         this.
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
                                         this.
                                           parameter(
                                             "shaders",
                                             "gray_shader");
                                         this.
                                           instance(
                                             "teapot.instance2",
                                             "teapot");
        }
        private void sphere(String name, String shaderName,
                            float x,
                            float y,
                            float z,
                            float radius) {
            this.
              geometry(
                name,
                new Sphere(
                  ));
            this.
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
            this.
              parameter(
                "shaders",
                shaderName);
            this.
              instance(
                name +
                ".instance",
                name);
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1169964658000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAKVXe2wURRif3rVX+tBrCy0IQmmpPFq8VSMmtiZYSoHCAbUH" +
           "BcqjTHfn7pbu7a6z\nc+21EB8xEcT4IGqiiQ9iSKqIYoIGTVAh+ED5ByRqQg" +
           "JqSNTER2JMFKN/+M3MPfeuJ9FLdm535nvM\n9/rNN0d+RmUORTeqToCN2cQJ" +
           "dIV6MXWI1mVgx9kAU4PqR2UVvRNrTMuDSoLIo2sM+YOqo2iYYUXX\nlJ7lHQ" +
           "mK2mzLGIsYFguQBAvsMpYk5a0OLskTuOnF43UPHipt9KCyIPJj07QYZrpldh" +
           "sk5jBUE9yF\nR7ASZ7qhBHWHdQTRdcSMx7os02HYZM696D7kDSKfrXKZDDUF" +
           "U8oVUK7YmOKYItQrvUItSJhKCcO6\nSbTOtDrgXJzLCdtO8vXlU4OQKXyxH8" +
           "wROwCr56atltbmmWp7X+m/Y8/BV73IP4D8uhniwlSwhIG+\nAVQdI7EhQp1O" +
           "TSPaAKo1CdFChOrY0MeF1gFU5+gRE7M4JU4fcSxjhBPWOXGbUKEzNRlE1Sq3" +
           "icZV\nZtG0j8I6MbTUV1nYwBEwuyFjtjR3BZ8HAyt12BgNY5WkWEqHdRMi3u" +
           "jmSNvYsgYIgLU8RljUSqsq\nNTFMoDoZSwObESXEqG5GgLTMioMWhmZOKpT7" +
           "2sbqMI6QQYZmuOl65RJQVQhHcBaG6t1kQhJEaaYr\nSlnxaWv4fd8rz79/t8" +
           "jtUo2oBt9/JTDNcTH1kTChxFSJZLwaDzzdsyV+owchIK53EUuazpuObwz+\n" +
           "8EGjpJlVgGb90C6iskF13YHGvt0rLeTl25hiW47Og59juSiH3uRKR8KGqm1I" +
           "S+SLgdTiyb6Ptzxw\nmPzoQZU9yKdaRjwGeVSrWjFbNwhdSUxCMSNaD6ogpt" +
           "Yl1ntQObwHIeXl7Ppw2CGsB5UaYspniW9w\nURhEcBdVwLtuhq3Uu41ZVLwn" +
           "bIRQNTyoFp4Ykj/xz9DCgOLEzbBhjSoOVRWLRtLfy8C30RimwwGe\nMTZDy5" +
           "WoFSMKVrGpm5YS0aFGVetmjYzw/2uUk+B7qhstKeEg5y5WA/J8lWVohA6qE1" +
           "c+29O95pF9\nMhF48iatYagFxAeS4gNp8S3pt5AKHkUlJULJNK5VxgO8OQx1" +
           "CQhWvSi0ffXOfc1eSAR7tBRcwUmb\nwYzkVrpVqytTvD0C51TIoBkvb90buD" +
           "qxVGaQMjnGFuSuOvf62YO/Vbd6kKcwAHITAYIruZhejppp\nYGtxl0wh+b/s" +
           "X3vsy7OXFmaKB5yVV9P5nLwmm93BoJZKNEC5jPhDN/i9m1D/AQ8qhUIHcBP7" +
           "B9yY\n49aRU5sdKZzjtpQHUVXYojFs8KUUOFWyKLVGMzMiS2rE+1QIjp8n62" +
           "x4zGT2in++Wm/zsUFmFY+2\nywqBo1cf8t3y1Ymqj4RbUpDrzzrUQoTJAq7N" +
           "JMsGSgjMX3q296lnft67VWRKMlUYnHTxIUNXE8Ay\nP8MClWsAevBAtmw0Y5" +
           "amh3U8ZBCecX/7b7r17Z8er5GhMWAmFdnF/y4gM3/DMvTA2R1/zBFiSlR+\n" +
           "cmTMyJBJa6ZmJHdSisf4PhIPfj77uU/wCwBsACaOPk4EPiBhGRJ+VITfW8UY" +
           "cK3dyodmkL14ktQv\ncE4PqnsOR5rj9376rth1Fc4+8LPDsBbbHTLyfJjHvT" +
           "vdXb2rsBMFuttPrttWY5z8CyQOgEQVzkdn\nPQXgSOQEMUldVn7x1OmGnee9" +
           "yLMCVRoW1lZgkf+oAhKPOFHAnIS99G6RWzWjU/goTEbCCTOTDkjk\nfImPFj" +
           "EuSCdGuU31EcByFylFsyc78sRxvXfzr9UP4w+3S1ipyz1GuqHV+n7sNFlw12" +
           "PfFkBDX7Jl\nySj0cn05WLZWtAKZSt7/6mvH2fm2dqmvdXIYczO2th8cb2w/" +
           "+uh/QLBGlwfcomtHZt3jjepnPKJb\nkeCV1+XkMnVk+wKUwn7i1ORe5TPVIp" +
           "fm5qLILHicJIo4BVGED/OLlMDqImtBPqxkqGworkOXR9GM\n7D6e6jHoB0YE" +
           "KF55uPm9Mxtf2isjsKhIs57NNaje//U3w94nTg1JPndP5CI+MOfQd8eu9E2T" +
           "WSMb\nx3l5vVs2j2wehS1+m+dtUzENgvrDtqYj9/VdFjvifEsZKh2xdNl43s" +
           "aHVbIalvxrgYmPrnTABMl8\nePYnA7Z/0oC1FwnKtiJrO/iwBWBIBKzLoiYx" +
           "jGWWWOvLWDDwfyzQ4LmQtODCNVvgExJ94ns63MEE\nqPG2PSDb9uIL62y5lR" +
           "DjB56FxWkznBmE3nARx4g+Es4Xn2NH4Sh3+UO7Vn8kGLo+ty9L7bu+YBPH\n" +
           "SybvCievHWrw4u5tvwe/+FO0H+mrQRX05+G4YWThQDYm+GxKwrowqUqeLbb4" +
           "g41VZe0AkDv5JvY3\nKol2M+QFIv66x05tvCF74yH539nbk4v4/Pidl1PU4h" +
           "Kcgr64vAYPqptf3zo38eiGJwWelsH1eXxc\n3Hfg+ia7pDR8Nk0qLSXrHHrz" +
           "aP+JN+5MFaI4RaclW6O8JL1NrhaJI0B24dakO2Yz0UyMvzP9rbsm\nXrzsEc" +
           "3RP0NV0367EAAA");
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
                    copy().
                    toNonLinear().
                    toRGB();
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169964658000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAK0aDXAU1fndXZLLnyQk/MlfSAggEO7kJxCIVkMSMHAhMQmo" +
       "QYybvXeXJXu76+5e\nOCKjMlpBsa20Ouq0orbOgKlWO9bSzliUUVur01adqj" +
       "M6/g217bTaqXWqdGrHft97e7d7ez8hCTez\n73b3vff9/767xz8lhYZO5otG" +
       "wNyvUSPQ2tst6AYNt8qCYfTBqwHxpcKS7uPbFdVLPCHilcImqQiJ\nRjAsmE" +
       "JQCgc72poTOlmpqfL+qKyaAZowA3vlRgvetlBjBsCrjp2sOvhoQY2XFIZIha" +
       "AoqimYkqq0\nyzRmmKQytFcYEYJxU5KDIckwm0PkAqrEY62qYpiCYho3kJuI" +
       "L0SKNBFhmqQ2lEQeBORBTdCFWJCh\nD3YztAChWqemICk03JJCBzsb0ncC2d" +
       "a+nszVAKQYJ3cBO4wC4HpRimvObQarmu/ErvUHHn7MRyr6\nSYWk9CIwETgx" +
       "AV8/KY/R2CDVjZZwmIb7yXSF0nAv1SVBlkYZ1n5SZUhRRTDjOjV6qKHKI7iw" +
       "yohr\nVGc4ky9DpFxEnvS4aKp6SkYRicrh5FNhRBaiwPYsm23O7hZ8DwyWSk" +
       "CYHhFEmtxSMCwpoPEa944U\nj/XbYQFs9ceoOaSmUBUoArwgVVyXsqBEg72m" +
       "LilRWFqoxgGLSebmBIqy1gRxWIjSAZPMca/r5lOw\nqoQJAreYZKZ7GYMEWp" +
       "rr0pJDPytnfXH4xA9OXc5suyBMRRnpL4VNC12bemiE6lQRKd94Nh64p+Oa\n" +
       "+HwvIbB4pmsxX9Oy5OTO0F+fq+Fr5mVZ0zW4l4rmgLjjaE3PjVtV4kMyijXV" +
       "kFD5aZwzd+i2ZpoT\nGnjtrBREnAwkJ5/v+fU1t4zRv3tJaQcpElU5HgM7mi" +
       "6qMU2Sqb6VKlQXTBruICVUCbey+Q7ih/sQ\nmDx/2xWJGNTsIAUye1WksmcQ" +
       "UQRAoIhK4F5SImryXhPMIXaf0AghfrjIVrhqCP+wb5NcFAgacSUi\nq/uChi" +
       "4GVT2aet4Msh2KCfpwAC1GM0lbcEiN0aAgCoqkqMGoBD4qqqvCdAS/zxFOAm" +
       "mq2ufxYJBz\nO6sMdn6FKoepPiAeP/PKgfbtdxzmhoDGa3EDdgXgAxb4QAo8" +
       "8XgY1BmIhisAxDcMjgghq3x5755t\n1x+u84HmtX0FwLsPltYB3RbudlFttb" +
       "21gwU2EUxmzg93HwqcPX4ZN5lg7qCadXfZa0+8+vDn5Su8\nxJs94iFPEHNL" +
       "EUw3hslUJKt3+0g2+P+4s/Ppt1597yLbW0xSn+HEmTvRCevc0tdVkYYhrNng" +
       "H72w\nwncV2XXUSwrAsyGaMfohUCx040hzxuZkYENe/CFSFlH1mCDjVDIalZ" +
       "pDurrPfsPMopLdV4NyytA6\nZ8DVapkr+8bZmRqOs7gZobZdXLDAefbWoovf" +
       "frbsJSaWZIytcGSxXmpyj51uG0ufTim8f+/+7u/d\n++mh3cxSuKl4TEht8U" +
       "FZEhOwZam9BVxVhnCBiqzfqcTUsBSRhEGZosV9VbFk9TOffLuSq0aGN0nN\n" +
       "NowPwH5/4WZyy6vXfbmQgfGImCpsNuxlnJtqG3KLrgv7kY7EwTcWPPAb4UGI" +
       "ZBA9DGmUsoBAGGeE\nyTHI5L6CjQHX3Goc6gB2Qw7Tz5KYB8QDY9G6+A2//S" +
       "WjukxwZninGjoFrZlrHofFKN3Zbu+9QjCG\nYN2653dcWyk//1+A2A8QRUiI" +
       "RpcOkSKRpkRrdaH/ndMvzLr+dR/xbiGlsiqEtwjM/kkJGB41hiDI\nJLTLLm" +
       "e2VbmvGEfGMmFCmGsJgD3Mz7TKrZZVbs1qlTgsdYm0gEEsAAbnOCs7XYpBhh" +
       "hhXnPm9rpf\nvbzzoUM80izPU745dw2IN3/w4bDvO6cH+T53lnQtPrrw0T8/" +
       "faZnBrdKXkoszsjmzj28nGCcVWio\nodp8GNjqF1fWPn5Tz/sWRVXpSbEdCs" +
       "e/7H+BLrvkWx9lie0+yaonL9W4+DebxD+oqjIVFHzcwgZG\nziV5zHYbDhvZ" +
       "1BocNnFgjZNW+XZL5dvPWeWFDGIhPrbYlKeGFrbzyjw89OKww+ah61x5SDie" +
       "io28\ntrQFq1E74A8MNpwIvdL1IFNMznyVxcxccEZP7Tx29nfm+wyOnThwd2" +
       "0iM/FDBW/vbXprZHrRUw/F\nvMTfTypFq8fYJchxDM/9UBIbycYD+pC0+fTy" +
       "ltdyzanEON9t5w607pRlGyXc42q8L3dlqXKU9jy4\nFlnGschtHB7CbvawLf" +
       "VsXJbKKX5Nl0YE7DtIqY5NQxxFnLKXKlvz142n+avTacLirtaiqTYHTYwj\n" +
       "qOVLIRju64qbWtxM2aYDdWSCqC+Cq85CXZcD9V4LdTWiTtVvuWkYngT7iy0a" +
       "FuegQU2xL4zQ3Ki1\nCaKeA1e9hbo+B2rTQu3HVCTwBs2t8fgE8aKWl1h4l+" +
       "TAu9/CO01PFmsdMWjasIhy5CRWOGAof+zu\ntuqejbtvZbVrCTTAgrHDdgqv" +
       "FMY7D3jzktzhJQVsQFy25+Q/T5+iy1gCL5YMcL4WPZqlGXTs+UwY\no51vR4" +
       "6x+rNgUDC4G7q76MwmOa33ZV47TWNfLalI6+FuyIWpJWV0MNNTvSaEBkkRWC" +
       "O6HJy1SKZK\nlDdXTG03W0aCUL18C3uebVoFGUYj6HFVhWJtl5zjfYqkBlLn" +
       "CzCZyKBPJwvSupROxpkdLu987Mcn\nzddXbuLJdkVuXbg3rtj08GjNpiePTK" +
       "I3qXGpzA16+si8K31D0stedvDAo2/GgUX6pub0mAsB0Yzr\nSl9a5F3EdXhz" +
       "tmzrTJzfzTN3Dw53g0pF1AdXH8i4JntN3h7TTFZFj/5i9s8uOX7sfZSyloDG" +
       "2c8c\nYu0G2FsF3oNnbwEpbOXBtje2DI5FlLEwE0ApM9YWXG9xV8LeOLzJp2" +
       "oGHi44TvEsSPVdmoHd2gUO\nJB1tB57aVl78yJHbGXzLFUscBxXWs39E0Hc4" +
       "q6syRvXqtWuaNqw3yY7z0dBvWrN2Q8OGtauaNoC5\n913R0RtIDy+I+/6MoH" +
       "OTTmZlig2ZtYIYqWL2PM12ICwcnZPATkFPe0ubK3KOTjByYpJaaoFdmiNy\n" +
       "HrciZ8UIyC/MvIOHTnx/4Nwiy+NZIgve38bDCo7fTIWUscyQgo+HcLgjM0bg" +
       "812cjLHxnOPpPHPP\n4PBTHO7lVOB4n8Pcm9i2BxyGtLHx4vNmSE0bGlavX7" +
       "V2NcRGZkguaSPqn2cqAV//yGUEJyZRMSyz\njGBZDiM4lUyfVNdVvS/ZRmbL" +
       "3s/lQZ+wFTTXOoiyEsIi57GWsd8wacw+3eqjhplv4U6DQjHrTIOw\ncI5zoa" +
       "jqNNAmGZos7Mc+bkGuQ1jWwx26+rPy24UX93gt02g1oXVWtVUyHaGyg/pp3P" +
       "hS4qxCLlfA\ntdwS53K3OG1ZZTrDcsiuBjuRT7jM1GnqB1LY5ic9OCe2pCgq" +
       "7UDCj72zuysObyU1/s5EXPbNSbgs\nx8Z2j+e2H+WZO4PDBym3fdN221Lupo" +
       "1r1q1xJaqQKgpyR9sjp8veOBpfvy2p5u/j8CeTdJ4Xj163\numFN06p14NAF" +
       "gh41GIY/5mHkbzj8HlbHoBZxnwIUjKhS2PaxP0yoo7cNlHX0F8IVtUwmmtVA" +
       "x9PH\nv/PMfYnDvyBo0gQV4yaLUJ/YlH8+FcoXwPWuRfm7k6L869xzHg++/A" +
       "rC+zDVFSpvplFJcVH/v6lQ\nj/76oUX9h5Oh3lOSZ64MhyKwek59p+Am3uOf" +
       "CvHY6X9sEf/xpIivzjM3E4dKiLKc+HYl7KJ9+lRo\nb4brrEX72XOm3ZeeoO" +
       "Znyzsd9Z1qOG63NVmTU0d9N8RcM5RKHtkiMhPDgjwiWoLDXKjdNQTmEs+8\n" +
       "qYinEa6vLfF8PVnxZDB0QzI3sLM+z6o8rOExu2cF5ljBGO41Bd3N3sqpsDcX" +
       "Mo6Pc8e/J2y5G/PM\nNeMA7UQxp13VXKRvmArpUI15/Bbp/nMm3cqytuxb89" +
       "DfjsPlEDaQ/p0aZEhXxPa0TIWDJiCkzOKg\nbKK2ZZuQzUtnHl66cNgGvEhY" +
       "GmeJ357tUzWkCouXikkZ0lV55q7BoQ8MiRGfGQF3ToX0bwDJ1Rbp\n1edMuv" +
       "sQ3zEk3X62M+AxyvEIQdUZS0IedtHKPNeBz7NNWyRZdvE7MBV+YY9ntsXv7P" +
       "PBr22AsTxM\n4bmqZ69JyhlT3TrVBN3tTuOe5+bjqx34mWfxNe+86dFuKZoQ" +
       "5qX5kIyneE3TnKV348Vsw8fI+YHz\n1yuvbWhcv2p9E9TFWNIz2e/Lo5eDOJ" +
       "hQ2TFCs0a5cc+cU78vgc2m6MHTszkZ/7ri/xQSQ+/ceO0X\noTf/ww9wk//m" +
       "KQuR4khclp2/sTjuizSdRiSmyzL+iws72vAcBuodIof62rpD4jyH+KIjJvHB" +
       "Iry9\nS8uSmfmPQok0fpCFxWkHp+wfa8nDzTj/z9qAePUTuxcljvTdzU5MC0" +
       "VZGB1FMKUh4uf/cGBQ8YC0\nNie0JKzXyFNP7nr2JxuTjRf7BXyGozlO84E1" +
       "fDa3fnDiPu3/uKJwsD0oAAA=");
}
