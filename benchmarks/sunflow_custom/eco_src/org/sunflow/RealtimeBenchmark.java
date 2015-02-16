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
                                                             this.
                                                               parameter(
                                                                 "threads",
                                                                 threads);
                                                             this.
                                                               parameter(
                                                                 "threads.lowPriority",
                                                                 false);
                                                             this.
                                                               parameter(
                                                                 "resolutionX",
                                                                 512);
                                                             this.
                                                               parameter(
                                                                 "resolutionY",
                                                                 512);
                                                             this.
                                                               parameter(
                                                                 "aa.min",
                                                                 -3);
                                                             this.
                                                               parameter(
                                                                 "aa.max",
                                                                 0);
                                                             this.
                                                               parameter(
                                                                 "depths.diffuse",
                                                                 1);
                                                             this.
                                                               parameter(
                                                                 "depths.reflection",
                                                                 1);
                                                             this.
                                                               parameter(
                                                                 "depths.refraction",
                                                                 0);
                                                             this.
                                                               parameter(
                                                                 "bucket.order",
                                                                 "hilbert");
                                                             this.
                                                               parameter(
                                                                 "bucket.size",
                                                                 32);
                                                             this.
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
                                                             this.
                                                               parameter(
                                                                 "eye",
                                                                 eye);
                                                             this.
                                                               parameter(
                                                                 "target",
                                                                 target);
                                                             this.
                                                               parameter(
                                                                 "up",
                                                                 up);
                                                             this.
                                                               parameter(
                                                                 "fov",
                                                                 45.0F);
                                                             String name =
                                                               this.
                                                               getUniqueName(
                                                                 "camera");
                                                             this.
                                                               camera(
                                                                 name,
                                                                 new PinholeLens(
                                                                   ));
                                                             this.
                                                               parameter(
                                                                 "camera",
                                                                 name);
                                                             this.
                                                               options(
                                                                 SunflowAPI.
                                                                   DEFAULT_OPTIONS);
                                                             this.
                                                               createGeometry();
                                                             UI.
                                                               printInfo(
                                                                 Module.
                                                                   BENCH,
                                                                 "Rendering warmup frame ...");
                                                             this.
                                                               render(
                                                                 SunflowAPI.
                                                                   DEFAULT_OPTIONS,
                                                                 display);
                                                             Timer t =
                                                               new Timer(
                                                               );
                                                             t.
                                                               start();
                                                             final double[] phi = {
                                                               0 };
                                                             final double[] totalPhi = {
                                                               Math.
                                                                 PI *
                                                               32 };
                                                             int frames =
                                                               0;
                                                             ecor.CalibratorStack.push(new ecor.Calibrator() {
                                                                 double demandRatio = (double)(totalPhi[0] -
                                                                   phi[0])/(totalPhi[0]);
                                                                 double supplyRatio = 0;
                                                                 public int mode = $UTILMODES.$MAX;
                                                                 public int getMode() {
                                                                         return mode;
                                                                 }
                                                                 public int supply() {
                                                                 return ecor.tsupply.TemperatureSupply.sharedSupply().getCurrentTemperature();}
                                                                     private double supplyLimit = (ecor.tsupply.TemperatureSupply.sharedSupply().getCurrentTemperature()) *
                                                                   1.45;
                                                                 private int initialSupply = this.supply();
                                                                 public Object calibrate(Object input) {
                                                                         double limitFromInitial = supplyLimit - initialSupply;System.out.println("limit:" + limitFromInitial + " current:" + (this.supply() - initialSupply));supplyRatio = (limitFromInitial - (this.supply() - initialSupply))/limitFromInitial;
                                                                         return input;
                                                                         }
                                                                 public void adjust() {
                                                                         demandRatio = (double)(totalPhi[0] -
                                                                               phi[0])/(totalPhi[0]);
                                                                             if (supplyRatio > demandRatio * 1.1 && mode < $UTILMODES.$MAX) ++mode;
                                                                             else if (demandRatio > supplyRatio * 1.1 && mode > 0) --mode;
                                                                             }
                                                             public double getSupplyRatio() { return this.supplyRatio; }
                                                                     public double getDemandRatio() { return this.demandRatio; }
                                                                             public boolean isUniform() { return false; }
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
                                                                     this.
                                                                       parameter(
                                                                         "eye",
                                                                         eye);
                                                                     this.
                                                                       parameter(
                                                                         "target",
                                                                         target);
                                                                     this.
                                                                       parameter(
                                                                         "up",
                                                                         up);
                                                                     this.
                                                                       camera(
                                                                         name,
                                                                         null);
                                                                     this.
                                                                       render(
                                                                         SunflowAPI.
                                                                           DEFAULT_OPTIONS,
                                                                         display);
                                                                 }
                                                             }
                                                             ecor.CalibratorStack.pop();
                                                             ;
                                                             t.
                                                               end();
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
                                                                   seconds());
                                                             UI.
                                                               printInfo(
                                                                 Module.
                                                                   BENCH,
                                                                 "  * Total time:          %s",
                                                                 t);
    }
    private void createGeometry() { this.
                                      parameter(
                                        "source",
                                        new Point3(
                                          -15.5945F,
                                          -30.0581F,
                                          45.967F));
                                    this.
                                      parameter(
                                        "dir",
                                        new Vector3(
                                          15.5945F,
                                          30.0581F,
                                          -45.967F));
                                    this.
                                      parameter(
                                        "radius",
                                        60.0F);
                                    this.
                                      parameter(
                                        "radiance",
                                        Color.
                                          white().
                                          mul(
                                            3));
                                    this.
                                      light(
                                        "light",
                                        new DirectionalSpotlight(
                                          ));
                                    this.
                                      parameter(
                                        "gi.engine",
                                        "fake");
                                    this.
                                      parameter(
                                        "gi.fake.sky",
                                        new Color(
                                          0.25F,
                                          0.25F,
                                          0.25F));
                                    this.
                                      parameter(
                                        "gi.fake.ground",
                                        new Color(
                                          0.01F,
                                          0.01F,
                                          0.5F));
                                    this.
                                      parameter(
                                        "gi.fake.up",
                                        new Vector3(
                                          0,
                                          0,
                                          1));
                                    this.
                                      options(
                                        DEFAULT_OPTIONS);
                                    this.
                                      parameter(
                                        "diffuse",
                                        Color.
                                          white().
                                          mul(
                                            0.5F));
                                    this.
                                      shader(
                                        "default",
                                        new DiffuseShader(
                                          ));
                                    this.
                                      parameter(
                                        "diffuse",
                                        Color.
                                          white().
                                          mul(
                                            0.5F));
                                    this.
                                      parameter(
                                        "shiny",
                                        0.2F);
                                    this.
                                      shader(
                                        "refl",
                                        new ShinyDiffuseShader(
                                          ));
                                    this.
                                      parameter(
                                        "subdivs",
                                        10);
                                    this.
                                      geometry(
                                        "teapot",
                                        (Tesselatable)
                                          new Teapot(
                                          ));
                                    this.
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
                                    this.
                                      parameter(
                                        "transform",
                                        m);
                                    this.
                                      instance(
                                        "teapot.instance",
                                        "teapot");
                                    this.
                                      parameter(
                                        "subdivs",
                                        10);
                                    this.
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
                                    this.
                                      parameter(
                                        "shaders",
                                        "default");
                                    this.
                                      parameter(
                                        "transform",
                                        m);
                                    this.
                                      instance(
                                        "gumbo.instance",
                                        "gumbo");
                                    this.
                                      parameter(
                                        "center",
                                        new Point3(
                                          0,
                                          0,
                                          0));
                                    this.
                                      parameter(
                                        "normal",
                                        new Vector3(
                                          0,
                                          0,
                                          1));
                                    this.
                                      geometry(
                                        "ground",
                                        new Plane(
                                          ));
                                    this.
                                      parameter(
                                        "shaders",
                                        "refl");
                                    this.
                                      instance(
                                        "ground.instance",
                                        "ground");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1417619727000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAJ1Xe2wURRif3vVBH3ptgQLlUVoKCIVbTIREikKpBQoHnC0t" +
       "UMAy3Z27Lt3bXWbn\n2mshCDERhPggaqKJIjEkPAQxQYMmqBBAUf4BEzUhAT" +
       "UkaqKYGBPE6B9+M3PPvWuJXrKzczPfY77X\nb749eQcVOBRNUh0/G7SJ429u" +
       "D2LqEK3ZwI6zDpa61csFxcGjq0zLg/ICyKNrDPkCqqNomGFF15TW\nJxpjFD" +
       "XYljEYNizmJzHm32bMj8tbGZifJXD9obOVe47k13hQQQD5sGlaDDPdMlsMEn" +
       "EYKg9sw/1Y\niTLdUAK6wxoD6AFiRiPNlukwbDJnO9qFvAFUaKtcJkO1gYRy" +
       "BZQrNqY4ogj1SlCoBQmjKWFYN4nW\nlFQHnHMyOeHYcb62bGoQMopvdoI54g" +
       "Rg9dSk1dLaLFNt77HOBTsPH/ciXxfy6WY7F6aCJQz0daGy\nCIn0EOo0aRrR" +
       "ulCFSYjWTqiODX1IaO1ClY4eNjGLUuK0Eccy+jlhpRO1CRU6E4sBVKZym2hU" +
       "ZRZN\n+iikE0NL/CsIGTgMZlelzJbmLuPrYGCJDgejIaySBEt+n25CxGvcHE" +
       "kb61cBAbAWRQjrtZKq8k0M\nC6hSxtLAZlhpZ1Q3w0BaYEVBC0PVwwrlvrax" +
       "2ofDpJuh8W66oNwCqmLhCM7C0Fg3mZAEUap2RSkt\nPg1Vd/cde+OTJSK38z" +
       "WiGvz8JcA0xcXURkKEElMlkvFe1P9K68boJA9CQDzWRSxpmqaf7Qj8/GmN\n" +
       "pJmYg2Ztzzaism51zcGath3LLeTlxxhlW47Og59huSiHYHynMWZD1VYlJfJN" +
       "f2LzfNtnG3efIL94\nUEkrKlQtIxqBPKpQrYitG4QuJyahmBGtFRUTU2sW+6" +
       "2oCOYBSHm5ujYUcghrRfmGWCq0xH9wUQhE\ncBcVw1w3Q1ZibmPWK+YxGyFU" +
       "BA8qg2cCkj/xZmieX3GiZsiwBhSHqopFw8n/bQQbTI+QpeDj3gim\nfX6eOT" +
       "ZDAaXXihAFq9jUTUsJ61CrqjVXI/38/R/lxfgZKwfy8jjouYvXgLxfYRkaod" +
       "3q0dtf7mxZ\n9dw+mRg8mePWMTQZ1PjjavxZalBenpA+hquTgQG39kGBApSV" +
       "zWrfsnLrvjovZIQ9kA8+4aR1YEf8\nDC2q1Zyq4lYBeCqk0vi3N+313zu6WK" +
       "aSMjzY5uQuvXbq6uE/ymZ7kCc3EnLbAItLuJggh88kwtW7\nayeX/N/2rz7z" +
       "zdWbD6WqiKH6rOLO5uTFWeeOArVUogHcpcQfmeDzrkedBz0oHyoeUE6cHwBk" +
       "iltH\nRpE2JgCP21IUQKUhi0awwbcSKFXCeqk1kFoR6VEu5qMhOKU8ayfBUx" +
       "1PY/Hmu2NtPlbJdOLRdlkh\nAPXeM4Xzvj1Xelm4JYG9vrTbrZ0wWckVqWRZ" +
       "RwmB9ZuvBV9+9c7eTSJT4qnC4MqL9hi6GgOWGSkW\nKGEDYIQHsr7DjFiaHt" +
       "Jxj0F4xv3jm/7wB7++UC5DY8BKIrJz7i8gtT5hKdp99ak/pwgxeSq/QlJm\n" +
       "pMikNaNTkpsoxYP8HLE9X01+/XP8JiAcoIqjDxEBFB5hmQeYxqe3IFSPAJT1" +
       "izDefrbu4ysdb+2V\nqT9rhD4jnatbffq77/u8L17okXxuOHcRH5xy5Mczt9" +
       "vGSDfJO29a1rWTziPvPZEAPpsHpHYkDYL6\nUkPtyV1tt+InqsxE7xbocH4a" +
       "vEhmLnr+hxygU9RjWQbBslTngUL+ns+QF25scQhFrMwWo5+nqfAs\nEnuP8a" +
       "EOzjhnGO/laHe61Z0nwnXR7V98JE5TitP7pvQkXo1t6YZyPkzjrhjnxr4V2O" +
       "kFukfOr9lc\nbpz/GyR2gUQV2gxnLQW8jWWUQJy6oOjGhYtVW697kWcZKjEs" +
       "rC3DAj1QMZQtcXoBqmP24iWiMssH\nRvFRmIyEE6rjDohl/OPzGRkrcN7Jwz" +
       "UIImx7N/xe9iy+tMUTZ1/A4ACWPdcg/cRIE8WhfHIGlK8W\nLVEKyPYff+cs" +
       "u96wUCbA7OFT2c04e+HhoZqFpw/8DwCvcdnmFl3RP/FJb69+xSO6NondWd1e" +
       "JlNj\nenKCUjhPlJrcX3ylTDhpahJEeRuAauHBcRDFuUGUj/VinJnEuyKb6v" +
       "3Qq8RGSO6OEfbW82EtQw+q\nlICY5QQ6CUYH3UWU32/psnN9nA9BudF0/9Ri" +
       "qCKrAeAIOD7ru0D2smrgxo7NdwNf/yWusmS/WQpN\nXyhqGGlOTXdwoU1JSB" +
       "cGlcpKs8ULM1Sa1ouAu+IzccitkgiAzAtEfEpssTEO0Du9hWmX76Zga2ap\n" +
       "cEOmZeSo+LJK5FFUflt1qxtObZoaO7DuJZGcBfBNNjQkmmj4JpA3bjIXa4eV" +
       "lpB1Db13uvPcu48m\nak1gypi0DElWbTJaY0aKFuR/7muuJWIzcTENfTju/U" +
       "VHD93yiIv2X+4DLJoQDwAA");
}
