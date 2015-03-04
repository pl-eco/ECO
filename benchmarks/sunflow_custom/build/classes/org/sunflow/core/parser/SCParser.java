package org.sunflow.core.parser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import org.codehaus.janino.ClassBodyEvaluator;
import org.codehaus.janino.CompileException;
import org.codehaus.janino.Scanner;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.Scanner.ScanException;
import org.sunflow.SunflowAPI;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.SceneParser;
import org.sunflow.core.Shader;
import org.sunflow.core.Tesselatable;
import org.sunflow.core.camera.FisheyeLens;
import org.sunflow.core.camera.PinholeLens;
import org.sunflow.core.camera.SphericalLens;
import org.sunflow.core.camera.ThinLens;
import org.sunflow.core.light.DirectionalSpotlight;
import org.sunflow.core.light.ImageBasedLight;
import org.sunflow.core.light.PointLight;
import org.sunflow.core.light.SphereLight;
import org.sunflow.core.light.SunSkyLight;
import org.sunflow.core.light.TriangleMeshLight;
import org.sunflow.core.modifiers.BumpMappingModifier;
import org.sunflow.core.modifiers.NormalMapModifier;
import org.sunflow.core.primitive.Background;
import org.sunflow.core.primitive.BanchoffSurface;
import org.sunflow.core.primitive.CornellBox;
import org.sunflow.core.primitive.Hair;
import org.sunflow.core.primitive.JuliaFractal;
import org.sunflow.core.primitive.ParticleSurface;
import org.sunflow.core.primitive.Plane;
import org.sunflow.core.primitive.Sphere;
import org.sunflow.core.primitive.Torus;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.core.shader.AmbientOcclusionShader;
import org.sunflow.core.shader.AnisotropicWardShader;
import org.sunflow.core.shader.ConstantShader;
import org.sunflow.core.shader.DiffuseShader;
import org.sunflow.core.shader.GlassShader;
import org.sunflow.core.shader.IDShader;
import org.sunflow.core.shader.MirrorShader;
import org.sunflow.core.shader.PhongShader;
import org.sunflow.core.shader.ShinyDiffuseShader;
import org.sunflow.core.shader.TexturedAmbientOcclusionShader;
import org.sunflow.core.shader.TexturedDiffuseShader;
import org.sunflow.core.shader.TexturedPhongShader;
import org.sunflow.core.shader.TexturedShinyDiffuseShader;
import org.sunflow.core.shader.TexturedWardShader;
import org.sunflow.core.shader.UberShader;
import org.sunflow.core.shader.ViewCausticsShader;
import org.sunflow.core.shader.ViewGlobalPhotonsShader;
import org.sunflow.core.shader.ViewIrradianceShader;
import org.sunflow.core.tesselatable.BezierMesh;
import org.sunflow.core.tesselatable.FileMesh;
import org.sunflow.core.tesselatable.Gumbo;
import org.sunflow.core.tesselatable.Teapot;
import org.sunflow.image.Color;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.Parser;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.Parser.ParserException;
import org.sunflow.system.UI.Module;
public class SCParser implements SceneParser {
    private Parser p;
    private int numLightSamples;
    public SCParser() { super(); }
    public boolean parse(String filename, SunflowAPI api) { String localDir =
                                                              new File(
                                                              filename).
                                                              getAbsoluteFile(
                                                                ).
                                                              getParentFile(
                                                                ).
                                                              getAbsolutePath(
                                                                );
                                                            numLightSamples =
                                                              1;
                                                            Timer timer =
                                                              new Timer(
                                                              );
                                                            timer.start();
                                                            UI.printInfo(
                                                                 Module.
                                                                   API,
                                                                 "Parsing \"%s\" ...",
                                                                 filename);
                                                            try {
                                                                p =
                                                                  new Parser(
                                                                    filename);
                                                                while (true) {
                                                                    String token =
                                                                      p.
                                                                      getNextToken(
                                                                        );
                                                                    if (token ==
                                                                          null)
                                                                        break;
                                                                    if (token.
                                                                          equals(
                                                                            "image")) {
                                                                        UI.
                                                                          printInfo(
                                                                            Module.
                                                                              API,
                                                                            "Reading image settings ...");
                                                                        parseImageBlock(
                                                                          api);
                                                                    }
                                                                    else
                                                                        if (token.
                                                                              equals(
                                                                                "background")) {
                                                                            UI.
                                                                              printInfo(
                                                                                Module.
                                                                                  API,
                                                                                "Reading background ...");
                                                                            parseBackgroundBlock(
                                                                              api);
                                                                        }
                                                                        else
                                                                            if (token.
                                                                                  equals(
                                                                                    "accel")) {
                                                                                UI.
                                                                                  printInfo(
                                                                                    Module.
                                                                                      API,
                                                                                    "Reading accelerator type ...");
                                                                                p.
                                                                                  getNextToken(
                                                                                    );
                                                                                UI.
                                                                                  printWarning(
                                                                                    Module.
                                                                                      API,
                                                                                    "Setting accelerator type is not recommended - ignoring");
                                                                            }
                                                                            else
                                                                                if (token.
                                                                                      equals(
                                                                                        "filter")) {
                                                                                    UI.
                                                                                      printInfo(
                                                                                        Module.
                                                                                          API,
                                                                                        "Reading image filter type ...");
                                                                                    parseFilter(
                                                                                      api);
                                                                                }
                                                                                else
                                                                                    if (token.
                                                                                          equals(
                                                                                            "bucket")) {
                                                                                        UI.
                                                                                          printInfo(
                                                                                            Module.
                                                                                              API,
                                                                                            "Reading bucket settings ...");
                                                                                        api.
                                                                                          parameter(
                                                                                            "bucket.size",
                                                                                            p.
                                                                                              getNextInt(
                                                                                                ));
                                                                                        api.
                                                                                          parameter(
                                                                                            "bucket.order",
                                                                                            p.
                                                                                              getNextToken(
                                                                                                ));
                                                                                        api.
                                                                                          options(
                                                                                            SunflowAPI.
                                                                                              DEFAULT_OPTIONS);
                                                                                    }
                                                                                    else
                                                                                        if (token.
                                                                                              equals(
                                                                                                "photons")) {
                                                                                            UI.
                                                                                              printInfo(
                                                                                                Module.
                                                                                                  API,
                                                                                                "Reading photon settings ...");
                                                                                            parsePhotonBlock(
                                                                                              api);
                                                                                        }
                                                                                        else
                                                                                            if (token.
                                                                                                  equals(
                                                                                                    "gi")) {
                                                                                                UI.
                                                                                                  printInfo(
                                                                                                    Module.
                                                                                                      API,
                                                                                                    "Reading global illumination settings ...");
                                                                                                parseGIBlock(
                                                                                                  api);
                                                                                            }
                                                                                            else
                                                                                                if (token.
                                                                                                      equals(
                                                                                                        "lightserver")) {
                                                                                                    UI.
                                                                                                      printInfo(
                                                                                                        Module.
                                                                                                          API,
                                                                                                        "Reading light server settings ...");
                                                                                                    parseLightserverBlock(
                                                                                                      api);
                                                                                                }
                                                                                                else
                                                                                                    if (token.
                                                                                                          equals(
                                                                                                            "trace-depths")) {
                                                                                                        UI.
                                                                                                          printInfo(
                                                                                                            Module.
                                                                                                              API,
                                                                                                            "Reading trace depths ...");
                                                                                                        parseTraceBlock(
                                                                                                          api);
                                                                                                    }
                                                                                                    else
                                                                                                        if (token.
                                                                                                              equals(
                                                                                                                "camera")) {
                                                                                                            parseCamera(
                                                                                                              api);
                                                                                                        }
                                                                                                        else
                                                                                                            if (token.
                                                                                                                  equals(
                                                                                                                    "shader")) {
                                                                                                                if (!parseShader(
                                                                                                                       api))
                                                                                                                    return false;
                                                                                                            }
                                                                                                            else
                                                                                                                if (token.
                                                                                                                      equals(
                                                                                                                        "modifier")) {
                                                                                                                    if (!parseModifier(
                                                                                                                           api))
                                                                                                                        return false;
                                                                                                                }
                                                                                                                else
                                                                                                                    if (token.
                                                                                                                          equals(
                                                                                                                            "override")) {
                                                                                                                        api.
                                                                                                                          shaderOverride(
                                                                                                                            p.
                                                                                                                              getNextToken(
                                                                                                                                ),
                                                                                                                            p.
                                                                                                                              getNextBoolean(
                                                                                                                                ));
                                                                                                                    }
                                                                                                                    else
                                                                                                                        if (token.
                                                                                                                              equals(
                                                                                                                                "object")) {
                                                                                                                            parseObjectBlock(
                                                                                                                              api);
                                                                                                                        }
                                                                                                                        else
                                                                                                                            if (token.
                                                                                                                                  equals(
                                                                                                                                    "instance")) {
                                                                                                                                parseInstanceBlock(
                                                                                                                                  api);
                                                                                                                            }
                                                                                                                            else
                                                                                                                                if (token.
                                                                                                                                      equals(
                                                                                                                                        "light")) {
                                                                                                                                    parseLightBlock(
                                                                                                                                      api);
                                                                                                                                }
                                                                                                                                else
                                                                                                                                    if (token.
                                                                                                                                          equals(
                                                                                                                                            "texturepath")) {
                                                                                                                                        String path =
                                                                                                                                          p.
                                                                                                                                          getNextToken(
                                                                                                                                            );
                                                                                                                                        if (!new File(
                                                                                                                                              path).
                                                                                                                                              isAbsolute(
                                                                                                                                                ))
                                                                                                                                            path =
                                                                                                                                              localDir +
                                                                                                                                              File.
                                                                                                                                                separator +
                                                                                                                                              path;
                                                                                                                                        api.
                                                                                                                                          addTextureSearchPath(
                                                                                                                                            path);
                                                                                                                                    }
                                                                                                                                    else
                                                                                                                                        if (token.
                                                                                                                                              equals(
                                                                                                                                                "includepath")) {
                                                                                                                                            String path =
                                                                                                                                              p.
                                                                                                                                              getNextToken(
                                                                                                                                                );
                                                                                                                                            if (!new File(
                                                                                                                                                  path).
                                                                                                                                                  isAbsolute(
                                                                                                                                                    ))
                                                                                                                                                path =
                                                                                                                                                  localDir +
                                                                                                                                                  File.
                                                                                                                                                    separator +
                                                                                                                                                  path;
                                                                                                                                            api.
                                                                                                                                              addIncludeSearchPath(
                                                                                                                                                path);
                                                                                                                                        }
                                                                                                                                        else
                                                                                                                                            if (token.
                                                                                                                                                  equals(
                                                                                                                                                    "include")) {
                                                                                                                                                String file =
                                                                                                                                                  p.
                                                                                                                                                  getNextToken(
                                                                                                                                                    );
                                                                                                                                                UI.
                                                                                                                                                  printInfo(
                                                                                                                                                    Module.
                                                                                                                                                      API,
                                                                                                                                                    "Including: \"%s\" ...",
                                                                                                                                                    file);
                                                                                                                                                api.
                                                                                                                                                  parse(
                                                                                                                                                    file);
                                                                                                                                            }
                                                                                                                                            else
                                                                                                                                                UI.
                                                                                                                                                  printWarning(
                                                                                                                                                    Module.
                                                                                                                                                      API,
                                                                                                                                                    "Unrecognized token %s",
                                                                                                                                                    token);
                                                                }
                                                                p.
                                                                  close(
                                                                    );
                                                            }
                                                            catch (ParserException e) {
                                                                UI.
                                                                  printError(
                                                                    Module.
                                                                      API,
                                                                    "%s",
                                                                    e.
                                                                      getMessage(
                                                                        ));
                                                                e.
                                                                  printStackTrace(
                                                                    );
                                                                return false;
                                                            }
                                                            catch (FileNotFoundException e) {
                                                                UI.
                                                                  printError(
                                                                    Module.
                                                                      API,
                                                                    "%s",
                                                                    e.
                                                                      getMessage(
                                                                        ));
                                                                return false;
                                                            }
                                                            catch (IOException e) {
                                                                UI.
                                                                  printError(
                                                                    Module.
                                                                      API,
                                                                    "%s",
                                                                    e.
                                                                      getMessage(
                                                                        ));
                                                                return false;
                                                            }
                                                            timer.
                                                              end(
                                                                );
                                                            UI.
                                                              printInfo(
                                                                Module.
                                                                  API,
                                                                "Done parsing.");
                                                            UI.
                                                              printInfo(
                                                                Module.
                                                                  API,
                                                                "Parsing time: %s",
                                                                timer.
                                                                  toString(
                                                                    ));
                                                            return true;
    }
    private void parseImageBlock(SunflowAPI api)
          throws IOException,
        ParserException { p.checkNextToken(
                              "{");
                          if (p.peekNextToken(
                                  "resolution")) {
                              api.
                                parameter(
                                  "resolutionX",
                                  p.
                                    getNextInt(
                                      ));
                              api.
                                parameter(
                                  "resolutionY",
                                  p.
                                    getNextInt(
                                      ));
                          }
                          if (p.peekNextToken(
                                  "aa")) {
                              api.
                                parameter(
                                  "aa.min",
                                  p.
                                    getNextInt(
                                      ));
                              api.
                                parameter(
                                  "aa.max",
                                  p.
                                    getNextInt(
                                      ));
                          }
                          if (p.peekNextToken(
                                  "samples"))
                              api.
                                parameter(
                                  "aa.samples",
                                  p.
                                    getNextInt(
                                      ));
                          if (p.peekNextToken(
                                  "contrast"))
                              api.
                                parameter(
                                  "aa.contrast",
                                  p.
                                    getNextFloat(
                                      ));
                          if (p.peekNextToken(
                                  "filter"))
                              api.
                                parameter(
                                  "filter",
                                  p.
                                    getNextToken(
                                      ));
                          if (p.peekNextToken(
                                  "jitter"))
                              api.
                                parameter(
                                  "aa.jitter",
                                  p.
                                    getNextBoolean(
                                      ));
                          if (p.peekNextToken(
                                  "show-aa")) {
                              UI.
                                printWarning(
                                  Module.
                                    API,
                                  "Deprecated: show-aa ignored");
                              p.
                                getNextBoolean(
                                  );
                          }
                          if (p.peekNextToken(
                                  "output")) {
                              UI.
                                printWarning(
                                  Module.
                                    API,
                                  "Deprecated: output statement ignored");
                              p.
                                getNextToken(
                                  );
                          }
                          api.options(SunflowAPI.
                                        DEFAULT_OPTIONS);
                          p.checkNextToken(
                              "}"); }
    private void parseBackgroundBlock(SunflowAPI api)
          throws IOException,
        ParserException { p.checkNextToken(
                              "{");
                          p.checkNextToken(
                              "color");
                          api.parameter("color",
                                        parseColor(
                                          ));
                          api.shader("background.shader",
                                     new ConstantShader(
                                       ));
                          api.geometry("background",
                                       new Background(
                                         ));
                          api.parameter("shaders",
                                        "background.shader");
                          api.instance("background.instance",
                                       "background");
                          p.checkNextToken(
                              "}"); }
    private void parseFilter(SunflowAPI api)
          throws IOException,
        ParserException { UI.printWarning(
                               Module.
                                 API,
                               ("Deprecated keyword \"filter\" - set this option in the image" +
                                " block"));
                          String name = p.
                            getNextToken(
                              );
                          api.parameter("filter",
                                        name);
                          api.options(SunflowAPI.
                                        DEFAULT_OPTIONS);
                          boolean hasSizeParams =
                            name.
                            equals(
                              "box") ||
                            name.
                            equals(
                              "gaussian") ||
                            name.
                            equals(
                              "blackman-harris") ||
                            name.
                            equals(
                              "sinc") ||
                            name.
                            equals(
                              "triangle");
                          if (hasSizeParams) {
                              p.
                                getNextFloat(
                                  );
                              p.
                                getNextFloat(
                                  );
                          } }
    private void parsePhotonBlock(SunflowAPI api)
          throws ParserException,
        IOException { int numEmit = 0;
                      boolean globalEmit =
                        false;
                      p.checkNextToken("{");
                      if (p.peekNextToken(
                              "emit")) { UI.
                                           printWarning(
                                             Module.
                                               API,
                                             ("Shared photon emit values are deprectated - specify number o" +
                                              "f photons to emit per map"));
                                         numEmit =
                                           p.
                                             getNextInt(
                                               );
                                         globalEmit =
                                           true;
                      }
                      if (p.peekNextToken(
                              "global")) {
                          UI.
                            printWarning(
                              Module.
                                API,
                              ("Global photon map setting belonds inside the gi block - igno" +
                               "ring"));
                          if (!globalEmit)
                              p.
                                getNextInt(
                                  );
                          p.
                            getNextToken(
                              );
                          p.
                            getNextInt(
                              );
                          p.
                            getNextFloat(
                              );
                      }
                      p.checkNextToken("caustics");
                      if (!globalEmit) numEmit =
                                         p.
                                           getNextInt(
                                             );
                      api.parameter("caustics.emit",
                                    numEmit);
                      api.parameter("caustics",
                                    p.
                                      getNextToken(
                                        ));
                      api.parameter("caustics.gather",
                                    p.
                                      getNextInt(
                                        ));
                      api.parameter("caustics.radius",
                                    p.
                                      getNextFloat(
                                        ));
                      api.options(SunflowAPI.
                                    DEFAULT_OPTIONS);
                      p.checkNextToken("}");
    }
    private void parseGIBlock(SunflowAPI api)
          throws ParserException,
        IOException { p.checkNextToken("{");
                      p.checkNextToken("type");
                      if (p.peekNextToken(
                              "irr-cache")) {
                          api.
                            parameter(
                              "gi.engine",
                              "irr-cache");
                          p.
                            checkNextToken(
                              "samples");
                          api.
                            parameter(
                              "gi.irr-cache.samples",
                              p.
                                getNextInt(
                                  ));
                          p.
                            checkNextToken(
                              "tolerance");
                          api.
                            parameter(
                              "gi.irr-cache.tolerance",
                              p.
                                getNextFloat(
                                  ));
                          p.
                            checkNextToken(
                              "spacing");
                          api.
                            parameter(
                              "gi.irr-cache.min_spacing",
                              p.
                                getNextFloat(
                                  ));
                          api.
                            parameter(
                              "gi.irr-cache.max_spacing",
                              p.
                                getNextFloat(
                                  ));
                          if (p.
                                peekNextToken(
                                  "global")) {
                              api.
                                parameter(
                                  "gi.irr-cache.gmap.emit",
                                  p.
                                    getNextInt(
                                      ));
                              api.
                                parameter(
                                  "gi.irr-cache.gmap",
                                  p.
                                    getNextToken(
                                      ));
                              api.
                                parameter(
                                  "gi.irr-cache.gmap.gather",
                                  p.
                                    getNextInt(
                                      ));
                              api.
                                parameter(
                                  "gi.irr-cache.gmap.radius",
                                  p.
                                    getNextFloat(
                                      ));
                          }
                      }
                      else
                          if (p.
                                peekNextToken(
                                  "path")) {
                              api.
                                parameter(
                                  "gi.engine",
                                  "path");
                              p.
                                checkNextToken(
                                  "samples");
                              api.
                                parameter(
                                  "gi.path.samples",
                                  p.
                                    getNextInt(
                                      ));
                              if (p.
                                    peekNextToken(
                                      "bounces")) {
                                  UI.
                                    printWarning(
                                      Module.
                                        API,
                                      ("Deprecated setting: bounces - use diffuse trace depth instea" +
                                       "d"));
                                  p.
                                    getNextInt(
                                      );
                              }
                          }
                          else
                              if (p.
                                    peekNextToken(
                                      "fake")) {
                                  api.
                                    parameter(
                                      "gi.engine",
                                      "fake");
                                  p.
                                    checkNextToken(
                                      "up");
                                  api.
                                    parameter(
                                      "gi.fake.up",
                                      parseVector(
                                        ));
                                  p.
                                    checkNextToken(
                                      "sky");
                                  api.
                                    parameter(
                                      "gi.fake.sky",
                                      parseColor(
                                        ));
                                  p.
                                    checkNextToken(
                                      "ground");
                                  api.
                                    parameter(
                                      "gi.fake.ground",
                                      parseColor(
                                        ));
                              }
                              else
                                  if (p.
                                        peekNextToken(
                                          "igi")) {
                                      api.
                                        parameter(
                                          "gi.engine",
                                          "igi");
                                      p.
                                        checkNextToken(
                                          "samples");
                                      api.
                                        parameter(
                                          "gi.igi.samples",
                                          p.
                                            getNextInt(
                                              ));
                                      p.
                                        checkNextToken(
                                          "sets");
                                      api.
                                        parameter(
                                          "gi.igi.sets",
                                          p.
                                            getNextInt(
                                              ));
                                      if (!p.
                                            peekNextToken(
                                              "b"))
                                          p.
                                            checkNextToken(
                                              "c");
                                      api.
                                        parameter(
                                          "gi.igi.c",
                                          p.
                                            getNextFloat(
                                              ));
                                      p.
                                        checkNextToken(
                                          "bias-samples");
                                      api.
                                        parameter(
                                          "gi.igi.bias_samples",
                                          p.
                                            getNextInt(
                                              ));
                                  }
                                  else
                                      if (p.
                                            peekNextToken(
                                              "ambocc")) {
                                          api.
                                            parameter(
                                              "gi.engine",
                                              "ambocc");
                                          p.
                                            checkNextToken(
                                              "bright");
                                          api.
                                            parameter(
                                              "gi.ambocc.bright",
                                              parseColor(
                                                ));
                                          p.
                                            checkNextToken(
                                              "dark");
                                          api.
                                            parameter(
                                              "gi.ambocc.dark",
                                              parseColor(
                                                ));
                                          p.
                                            checkNextToken(
                                              "samples");
                                          api.
                                            parameter(
                                              "gi.ambocc.samples",
                                              p.
                                                getNextInt(
                                                  ));
                                          if (p.
                                                peekNextToken(
                                                  "maxdist"))
                                              api.
                                                parameter(
                                                  "gi.ambocc.maxdist",
                                                  p.
                                                    getNextFloat(
                                                      ));
                                      }
                                      else
                                          if (p.
                                                peekNextToken(
                                                  "none") ||
                                                p.
                                                peekNextToken(
                                                  "null")) {
                                              api.
                                                parameter(
                                                  "gi.engine",
                                                  "none");
                                          }
                                          else
                                              UI.
                                                printWarning(
                                                  Module.
                                                    API,
                                                  "Unrecognized gi engine type \"%s\" - ignoring",
                                                  p.
                                                    getNextToken(
                                                      ));
                      api.options(SunflowAPI.
                                    DEFAULT_OPTIONS);
                      p.checkNextToken("}");
    }
    private void parseLightserverBlock(SunflowAPI api)
          throws ParserException,
        IOException { p.checkNextToken("{");
                      if (p.peekNextToken(
                              "shadows")) {
                          UI.
                            printWarning(
                              Module.
                                API,
                              "Deprecated: shadows setting ignored");
                          p.
                            getNextBoolean(
                              );
                      }
                      if (p.peekNextToken(
                              "direct-samples")) {
                          UI.
                            printWarning(
                              Module.
                                API,
                              "Deprecated: use samples keyword in area light definitions");
                          numLightSamples =
                            p.
                              getNextInt(
                                );
                      }
                      if (p.peekNextToken(
                              "glossy-samples")) {
                          UI.
                            printWarning(
                              Module.
                                API,
                              "Deprecated: use samples keyword in glossy shader definitions");
                          p.
                            getNextInt(
                              );
                      }
                      if (p.peekNextToken(
                              "max-depth")) {
                          UI.
                            printWarning(
                              Module.
                                API,
                              ("Deprecated: max-depth setting - use trace-depths block inste" +
                               "ad"));
                          int d =
                            p.
                            getNextInt(
                              );
                          api.
                            parameter(
                              "depths.diffuse",
                              1);
                          api.
                            parameter(
                              "depths.reflection",
                              d -
                                1);
                          api.
                            parameter(
                              "depths.refraction",
                              0);
                          api.
                            options(
                              SunflowAPI.
                                DEFAULT_OPTIONS);
                      }
                      if (p.peekNextToken(
                              "global")) {
                          UI.
                            printWarning(
                              Module.
                                API,
                              ("Deprecated: global settings ignored - use photons block inst" +
                               "ead"));
                          p.
                            getNextBoolean(
                              );
                          p.
                            getNextInt(
                              );
                          p.
                            getNextInt(
                              );
                          p.
                            getNextInt(
                              );
                          p.
                            getNextFloat(
                              );
                      }
                      if (p.peekNextToken(
                              "caustics")) {
                          UI.
                            printWarning(
                              Module.
                                API,
                              ("Deprecated: caustics settings ignored - use photons block in" +
                               "stead"));
                          p.
                            getNextBoolean(
                              );
                          p.
                            getNextInt(
                              );
                          p.
                            getNextFloat(
                              );
                          p.
                            getNextInt(
                              );
                          p.
                            getNextFloat(
                              );
                      }
                      if (p.peekNextToken(
                              "irr-cache")) {
                          UI.
                            printWarning(
                              Module.
                                API,
                              ("Deprecated: irradiance cache settings ignored - use gi block" +
                               " instead"));
                          p.
                            getNextInt(
                              );
                          p.
                            getNextFloat(
                              );
                          p.
                            getNextFloat(
                              );
                          p.
                            getNextFloat(
                              );
                      }
                      p.checkNextToken("}");
    }
    private void parseTraceBlock(SunflowAPI api)
          throws ParserException,
        IOException { p.checkNextToken("{");
                      if (p.peekNextToken(
                              "diff")) api.
                                         parameter(
                                           "depths.diffuse",
                                           p.
                                             getNextInt(
                                               ));
                      if (p.peekNextToken(
                              "refl")) api.
                                         parameter(
                                           "depths.reflection",
                                           p.
                                             getNextInt(
                                               ));
                      if (p.peekNextToken(
                              "refr")) api.
                                         parameter(
                                           "depths.refraction",
                                           p.
                                             getNextInt(
                                               ));
                      p.checkNextToken("}");
                      api.options(SunflowAPI.
                                    DEFAULT_OPTIONS);
    }
    private void parseCamera(SunflowAPI api)
          throws ParserException,
        IOException { p.checkNextToken("{");
                      p.checkNextToken("type");
                      String type = p.getNextToken(
                                        );
                      UI.printInfo(Module.
                                     API,
                                   "Reading %s camera ...",
                                   type);
                      parseCameraTransform(
                        api);
                      String name = api.getUniqueName(
                                          "camera");
                      if (type.equals("pinhole")) {
                          p.
                            checkNextToken(
                              "fov");
                          api.
                            parameter(
                              "fov",
                              p.
                                getNextFloat(
                                  ));
                          p.
                            checkNextToken(
                              "aspect");
                          api.
                            parameter(
                              "aspect",
                              p.
                                getNextFloat(
                                  ));
                          api.
                            camera(
                              name,
                              new PinholeLens(
                                ));
                      }
                      else
                          if (type.
                                equals(
                                  "thinlens")) {
                              p.
                                checkNextToken(
                                  "fov");
                              api.
                                parameter(
                                  "fov",
                                  p.
                                    getNextFloat(
                                      ));
                              p.
                                checkNextToken(
                                  "aspect");
                              api.
                                parameter(
                                  "aspect",
                                  p.
                                    getNextFloat(
                                      ));
                              p.
                                checkNextToken(
                                  "fdist");
                              api.
                                parameter(
                                  "focus.distance",
                                  p.
                                    getNextFloat(
                                      ));
                              p.
                                checkNextToken(
                                  "lensr");
                              api.
                                parameter(
                                  "lens.radius",
                                  p.
                                    getNextFloat(
                                      ));
                              if (p.
                                    peekNextToken(
                                      "sides"))
                                  api.
                                    parameter(
                                      "lens.sides",
                                      p.
                                        getNextInt(
                                          ));
                              if (p.
                                    peekNextToken(
                                      "rotation"))
                                  api.
                                    parameter(
                                      "lens.rotation",
                                      p.
                                        getNextFloat(
                                          ));
                              api.
                                camera(
                                  name,
                                  new ThinLens(
                                    ));
                          }
                          else
                              if (type.
                                    equals(
                                      "spherical")) {
                                  api.
                                    camera(
                                      name,
                                      new SphericalLens(
                                        ));
                              }
                              else
                                  if (type.
                                        equals(
                                          "fisheye")) {
                                      api.
                                        camera(
                                          name,
                                          new FisheyeLens(
                                            ));
                                  }
                                  else {
                                      UI.
                                        printWarning(
                                          Module.
                                            API,
                                          "Unrecognized camera type: %s",
                                          p.
                                            getNextToken(
                                              ));
                                      p.
                                        checkNextToken(
                                          "}");
                                      return;
                                  }
                      p.checkNextToken("}");
                      if (name != null) {
                          api.
                            parameter(
                              "camera",
                              name);
                          api.
                            options(
                              SunflowAPI.
                                DEFAULT_OPTIONS);
                      } }
    private void parseCameraTransform(SunflowAPI api)
          throws ParserException,
        IOException { if (p.peekNextToken(
                              "steps")) {
                          int n =
                            p.
                            getNextInt(
                              );
                          api.
                            parameter(
                              "transform.steps",
                              n);
                          for (int i =
                                 0;
                               i <
                                 n;
                               i++)
                              parseCameraMatrix(
                                i,
                                api);
                      }
                      else
                          parseCameraMatrix(
                            -1,
                            api); }
    private void parseCameraMatrix(int index,
                                   SunflowAPI api)
          throws IOException,
        ParserException { String offset =
                            index <
                            0
                            ? ""
                            : String.
                            format(
                              "[%d]",
                              index);
                          if (p.peekNextToken(
                                  "transform")) {
                              api.
                                parameter(
                                  String.
                                    format(
                                      "transform%s",
                                      offset),
                                  parseMatrix(
                                    ));
                          }
                          else {
                              if (index >=
                                    0)
                                  p.
                                    checkNextToken(
                                      "{");
                              p.
                                checkNextToken(
                                  "eye");
                              api.
                                parameter(
                                  String.
                                    format(
                                      "eye%s",
                                      offset),
                                  parsePoint(
                                    ));
                              p.
                                checkNextToken(
                                  "target");
                              api.
                                parameter(
                                  String.
                                    format(
                                      "target%s",
                                      offset),
                                  parsePoint(
                                    ));
                              p.
                                checkNextToken(
                                  "up");
                              api.
                                parameter(
                                  String.
                                    format(
                                      "up%s",
                                      offset),
                                  parseVector(
                                    ));
                              if (index >=
                                    0)
                                  p.
                                    checkNextToken(
                                      "}");
                          } }
    private boolean parseShader(SunflowAPI api)
          throws ParserException,
        IOException { p.checkNextToken("{");
                      p.checkNextToken("name");
                      String name = p.getNextToken(
                                        );
                      UI.printInfo(Module.
                                     API,
                                   "Reading shader: %s ...",
                                   name);
                      p.checkNextToken("type");
                      if (p.peekNextToken(
                              "diffuse")) {
                          if (p.
                                peekNextToken(
                                  "diff")) {
                              api.
                                parameter(
                                  "diffuse",
                                  parseColor(
                                    ));
                              api.
                                shader(
                                  name,
                                  new DiffuseShader(
                                    ));
                          }
                          else
                              if (p.
                                    peekNextToken(
                                      "texture")) {
                                  api.
                                    parameter(
                                      "texture",
                                      p.
                                        getNextToken(
                                          ));
                                  api.
                                    shader(
                                      name,
                                      new TexturedDiffuseShader(
                                        ));
                              }
                              else
                                  UI.
                                    printWarning(
                                      Module.
                                        API,
                                      "Unrecognized option in diffuse shader block: %s",
                                      p.
                                        getNextToken(
                                          ));
                      }
                      else
                          if (p.
                                peekNextToken(
                                  "phong")) {
                              String tex =
                                null;
                              if (p.
                                    peekNextToken(
                                      "texture"))
                                  api.
                                    parameter(
                                      "texture",
                                      tex =
                                        p.
                                          getNextToken(
                                            ));
                              else {
                                  p.
                                    checkNextToken(
                                      "diff");
                                  api.
                                    parameter(
                                      "diffuse",
                                      parseColor(
                                        ));
                              }
                              p.
                                checkNextToken(
                                  "spec");
                              api.
                                parameter(
                                  "specular",
                                  parseColor(
                                    ));
                              api.
                                parameter(
                                  "power",
                                  p.
                                    getNextFloat(
                                      ));
                              if (p.
                                    peekNextToken(
                                      "samples"))
                                  api.
                                    parameter(
                                      "samples",
                                      p.
                                        getNextInt(
                                          ));
                              if (tex !=
                                    null)
                                  api.
                                    shader(
                                      name,
                                      new TexturedPhongShader(
                                        ));
                              else
                                  api.
                                    shader(
                                      name,
                                      new PhongShader(
                                        ));
                          }
                          else
                              if (p.
                                    peekNextToken(
                                      "amb-occ") ||
                                    p.
                                    peekNextToken(
                                      "amb-occ2")) {
                                  String tex =
                                    null;
                                  if (p.
                                        peekNextToken(
                                          "diff") ||
                                        p.
                                        peekNextToken(
                                          "bright"))
                                      api.
                                        parameter(
                                          "bright",
                                          parseColor(
                                            ));
                                  else
                                      if (p.
                                            peekNextToken(
                                              "texture"))
                                          api.
                                            parameter(
                                              "texture",
                                              tex =
                                                p.
                                                  getNextToken(
                                                    ));
                                  if (p.
                                        peekNextToken(
                                          "dark")) {
                                      api.
                                        parameter(
                                          "dark",
                                          parseColor(
                                            ));
                                      p.
                                        checkNextToken(
                                          "samples");
                                      api.
                                        parameter(
                                          "samples",
                                          p.
                                            getNextInt(
                                              ));
                                      p.
                                        checkNextToken(
                                          "dist");
                                      api.
                                        parameter(
                                          "maxdist",
                                          p.
                                            getNextFloat(
                                              ));
                                  }
                                  if (tex ==
                                        null)
                                      api.
                                        shader(
                                          name,
                                          new AmbientOcclusionShader(
                                            ));
                                  else
                                      api.
                                        shader(
                                          name,
                                          new TexturedAmbientOcclusionShader(
                                            ));
                              }
                              else
                                  if (p.
                                        peekNextToken(
                                          "mirror")) {
                                      p.
                                        checkNextToken(
                                          "refl");
                                      api.
                                        parameter(
                                          "color",
                                          parseColor(
                                            ));
                                      api.
                                        shader(
                                          name,
                                          new MirrorShader(
                                            ));
                                  }
                                  else
                                      if (p.
                                            peekNextToken(
                                              "glass")) {
                                          p.
                                            checkNextToken(
                                              "eta");
                                          api.
                                            parameter(
                                              "eta",
                                              p.
                                                getNextFloat(
                                                  ));
                                          p.
                                            checkNextToken(
                                              "color");
                                          api.
                                            parameter(
                                              "color",
                                              parseColor(
                                                ));
                                          if (p.
                                                peekNextToken(
                                                  "absorbtion.distance"))
                                              api.
                                                parameter(
                                                  "absorbtion.distance",
                                                  p.
                                                    getNextFloat(
                                                      ));
                                          if (p.
                                                peekNextToken(
                                                  "absorbtion.color"))
                                              api.
                                                parameter(
                                                  "absorbtion.color",
                                                  parseColor(
                                                    ));
                                          api.
                                            shader(
                                              name,
                                              new GlassShader(
                                                ));
                                      }
                                      else
                                          if (p.
                                                peekNextToken(
                                                  "shiny")) {
                                              String tex =
                                                null;
                                              if (p.
                                                    peekNextToken(
                                                      "texture"))
                                                  api.
                                                    parameter(
                                                      "texture",
                                                      tex =
                                                        p.
                                                          getNextToken(
                                                            ));
                                              else {
                                                  p.
                                                    checkNextToken(
                                                      "diff");
                                                  api.
                                                    parameter(
                                                      "diffuse",
                                                      parseColor(
                                                        ));
                                              }
                                              p.
                                                checkNextToken(
                                                  "refl");
                                              api.
                                                parameter(
                                                  "shiny",
                                                  p.
                                                    getNextFloat(
                                                      ));
                                              if (tex ==
                                                    null)
                                                  api.
                                                    shader(
                                                      name,
                                                      new ShinyDiffuseShader(
                                                        ));
                                              else
                                                  api.
                                                    shader(
                                                      name,
                                                      new TexturedShinyDiffuseShader(
                                                        ));
                                          }
                                          else
                                              if (p.
                                                    peekNextToken(
                                                      "ward")) {
                                                  String tex =
                                                    null;
                                                  if (p.
                                                        peekNextToken(
                                                          "texture"))
                                                      api.
                                                        parameter(
                                                          "texture",
                                                          tex =
                                                            p.
                                                              getNextToken(
                                                                ));
                                                  else {
                                                      p.
                                                        checkNextToken(
                                                          "diff");
                                                      api.
                                                        parameter(
                                                          "diffuse",
                                                          parseColor(
                                                            ));
                                                  }
                                                  p.
                                                    checkNextToken(
                                                      "spec");
                                                  api.
                                                    parameter(
                                                      "specular",
                                                      parseColor(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "rough");
                                                  api.
                                                    parameter(
                                                      "roughnessX",
                                                      p.
                                                        getNextFloat(
                                                          ));
                                                  api.
                                                    parameter(
                                                      "roughnessY",
                                                      p.
                                                        getNextFloat(
                                                          ));
                                                  if (p.
                                                        peekNextToken(
                                                          "samples"))
                                                      api.
                                                        parameter(
                                                          "samples",
                                                          p.
                                                            getNextInt(
                                                              ));
                                                  if (tex !=
                                                        null)
                                                      api.
                                                        shader(
                                                          name,
                                                          new TexturedWardShader(
                                                            ));
                                                  else
                                                      api.
                                                        shader(
                                                          name,
                                                          new AnisotropicWardShader(
                                                            ));
                                              }
                                              else
                                                  if (p.
                                                        peekNextToken(
                                                          "view-caustics")) {
                                                      api.
                                                        shader(
                                                          name,
                                                          new ViewCausticsShader(
                                                            ));
                                                  }
                                                  else
                                                      if (p.
                                                            peekNextToken(
                                                              "view-irradiance")) {
                                                          api.
                                                            shader(
                                                              name,
                                                              new ViewIrradianceShader(
                                                                ));
                                                      }
                                                      else
                                                          if (p.
                                                                peekNextToken(
                                                                  "view-global")) {
                                                              api.
                                                                shader(
                                                                  name,
                                                                  new ViewGlobalPhotonsShader(
                                                                    ));
                                                          }
                                                          else
                                                              if (p.
                                                                    peekNextToken(
                                                                      "constant")) {
                                                                  p.
                                                                    peekNextToken(
                                                                      "color");
                                                                  api.
                                                                    parameter(
                                                                      "color",
                                                                      parseColor(
                                                                        ));
                                                                  api.
                                                                    shader(
                                                                      name,
                                                                      new ConstantShader(
                                                                        ));
                                                              }
                                                              else
                                                                  if (p.
                                                                        peekNextToken(
                                                                          "janino")) {
                                                                      String code =
                                                                        p.
                                                                        getNextCodeBlock(
                                                                          );
                                                                      try {
                                                                          Shader shader =
                                                                            (Shader)
                                                                              ClassBodyEvaluator.
                                                                              createFastClassBodyEvaluator(
                                                                                new Scanner(
                                                                                  null,
                                                                                  new StringReader(
                                                                                    code)),
                                                                                Shader.class,
                                                                                ClassLoader.
                                                                                  getSystemClassLoader(
                                                                                    ));
                                                                          api.
                                                                            shader(
                                                                              name,
                                                                              shader);
                                                                      }
                                                                      catch (CompileException e) {
                                                                          UI.
                                                                            printDetailed(
                                                                              Module.
                                                                                API,
                                                                              "Compiling: %s",
                                                                              code);
                                                                          UI.
                                                                            printError(
                                                                              Module.
                                                                                API,
                                                                              "%s",
                                                                              e.
                                                                                getMessage(
                                                                                  ));
                                                                          e.
                                                                            printStackTrace(
                                                                              );
                                                                          return false;
                                                                      }
                                                                      catch (ParseException e) {
                                                                          UI.
                                                                            printDetailed(
                                                                              Module.
                                                                                API,
                                                                              "Compiling: %s",
                                                                              code);
                                                                          UI.
                                                                            printError(
                                                                              Module.
                                                                                API,
                                                                              "%s",
                                                                              e.
                                                                                getMessage(
                                                                                  ));
                                                                          e.
                                                                            printStackTrace(
                                                                              );
                                                                          return false;
                                                                      }
                                                                      catch (ScanException e) {
                                                                          UI.
                                                                            printDetailed(
                                                                              Module.
                                                                                API,
                                                                              "Compiling: %s",
                                                                              code);
                                                                          UI.
                                                                            printError(
                                                                              Module.
                                                                                API,
                                                                              "%s",
                                                                              e.
                                                                                getMessage(
                                                                                  ));
                                                                          e.
                                                                            printStackTrace(
                                                                              );
                                                                          return false;
                                                                      }
                                                                      catch (IOException e) {
                                                                          UI.
                                                                            printDetailed(
                                                                              Module.
                                                                                API,
                                                                              "Compiling: %s",
                                                                              code);
                                                                          UI.
                                                                            printError(
                                                                              Module.
                                                                                API,
                                                                              "%s",
                                                                              e.
                                                                                getMessage(
                                                                                  ));
                                                                          e.
                                                                            printStackTrace(
                                                                              );
                                                                          return false;
                                                                      }
                                                                  }
                                                                  else
                                                                      if (p.
                                                                            peekNextToken(
                                                                              "id")) {
                                                                          api.
                                                                            shader(
                                                                              name,
                                                                              new IDShader(
                                                                                ));
                                                                      }
                                                                      else
                                                                          if (p.
                                                                                peekNextToken(
                                                                                  "uber")) {
                                                                              if (p.
                                                                                    peekNextToken(
                                                                                      "diff"))
                                                                                  api.
                                                                                    parameter(
                                                                                      "diffuse",
                                                                                      parseColor(
                                                                                        ));
                                                                              if (p.
                                                                                    peekNextToken(
                                                                                      "diff.texture"))
                                                                                  api.
                                                                                    parameter(
                                                                                      "diffuse.texture",
                                                                                      p.
                                                                                        getNextToken(
                                                                                          ));
                                                                              if (p.
                                                                                    peekNextToken(
                                                                                      "diff.blend"))
                                                                                  api.
                                                                                    parameter(
                                                                                      "diffuse.blend",
                                                                                      p.
                                                                                        getNextFloat(
                                                                                          ));
                                                                              if (p.
                                                                                    peekNextToken(
                                                                                      "refl") ||
                                                                                    p.
                                                                                    peekNextToken(
                                                                                      "spec"))
                                                                                  api.
                                                                                    parameter(
                                                                                      "specular",
                                                                                      parseColor(
                                                                                        ));
                                                                              if (p.
                                                                                    peekNextToken(
                                                                                      "texture")) {
                                                                                  UI.
                                                                                    printWarning(
                                                                                      Module.
                                                                                        API,
                                                                                      ("Deprecated uber shader parameter \"texture\" - please use \"" +
                                                                                       "diffuse.texture\" and \"diffuse.blend\" instead"));
                                                                                  api.
                                                                                    parameter(
                                                                                      "diffuse.texture",
                                                                                      p.
                                                                                        getNextToken(
                                                                                          ));
                                                                                  api.
                                                                                    parameter(
                                                                                      "diffuse.blend",
                                                                                      p.
                                                                                        getNextFloat(
                                                                                          ));
                                                                              }
                                                                              if (p.
                                                                                    peekNextToken(
                                                                                      "spec.texture"))
                                                                                  api.
                                                                                    parameter(
                                                                                      "specular.texture",
                                                                                      p.
                                                                                        getNextToken(
                                                                                          ));
                                                                              if (p.
                                                                                    peekNextToken(
                                                                                      "spec.blend"))
                                                                                  api.
                                                                                    parameter(
                                                                                      "specular.blend",
                                                                                      p.
                                                                                        getNextFloat(
                                                                                          ));
                                                                              if (p.
                                                                                    peekNextToken(
                                                                                      "glossy"))
                                                                                  api.
                                                                                    parameter(
                                                                                      "glossyness",
                                                                                      p.
                                                                                        getNextFloat(
                                                                                          ));
                                                                              if (p.
                                                                                    peekNextToken(
                                                                                      "samples"))
                                                                                  api.
                                                                                    parameter(
                                                                                      "samples",
                                                                                      p.
                                                                                        getNextInt(
                                                                                          ));
                                                                              api.
                                                                                shader(
                                                                                  name,
                                                                                  new UberShader(
                                                                                    ));
                                                                          }
                                                                          else
                                                                              UI.
                                                                                printWarning(
                                                                                  Module.
                                                                                    API,
                                                                                  "Unrecognized shader type: %s",
                                                                                  p.
                                                                                    getNextToken(
                                                                                      ));
                      p.checkNextToken("}");
                      return true; }
    private boolean parseModifier(SunflowAPI api)
          throws ParserException,
        IOException { p.checkNextToken("{");
                      p.checkNextToken("name");
                      String name = p.getNextToken(
                                        );
                      UI.printInfo(Module.
                                     API,
                                   "Reading shader: %s ...",
                                   name);
                      p.checkNextToken("type");
                      if (p.peekNextToken(
                              "bump")) { p.
                                           checkNextToken(
                                             "texture");
                                         api.
                                           parameter(
                                             "texture",
                                             p.
                                               getNextToken(
                                                 ));
                                         p.
                                           checkNextToken(
                                             "scale");
                                         api.
                                           parameter(
                                             "scale",
                                             p.
                                               getNextFloat(
                                                 ));
                                         api.
                                           modifier(
                                             name,
                                             new BumpMappingModifier(
                                               ));
                      }
                      else
                          if (p.
                                peekNextToken(
                                  "normalmap")) {
                              p.
                                checkNextToken(
                                  "texture");
                              api.
                                parameter(
                                  "texture",
                                  p.
                                    getNextToken(
                                      ));
                              api.
                                modifier(
                                  name,
                                  new NormalMapModifier(
                                    ));
                          }
                          else {
                              UI.
                                printWarning(
                                  Module.
                                    API,
                                  "Unrecognized modifier type: %s",
                                  p.
                                    getNextToken(
                                      ));
                          }
                      p.checkNextToken("}");
                      return true; }
    private void parseObjectBlock(SunflowAPI api)
          throws ParserException,
        IOException { p.checkNextToken("{");
                      boolean noInstance =
                        false;
                      Matrix4 transform =
                        null;
                      String name = null;
                      String[] shaders = null;
                      String[] modifiers =
                        null;
                      if (p.peekNextToken(
                              "noinstance")) {
                          noInstance =
                            true;
                      }
                      else {
                          if (p.
                                peekNextToken(
                                  "shaders")) {
                              int n =
                                p.
                                getNextInt(
                                  );
                              shaders =
                                (new String[n]);
                              for (int i =
                                     0;
                                   i <
                                     n;
                                   i++)
                                  shaders[i] =
                                    p.
                                      getNextToken(
                                        );
                          }
                          else {
                              p.
                                checkNextToken(
                                  "shader");
                              shaders =
                                (new String[] { p.
                                   getNextToken(
                                     ) });
                          }
                          if (p.
                                peekNextToken(
                                  "modifiers")) {
                              int n =
                                p.
                                getNextInt(
                                  );
                              modifiers =
                                (new String[n]);
                              for (int i =
                                     0;
                                   i <
                                     n;
                                   i++)
                                  modifiers[i] =
                                    p.
                                      getNextToken(
                                        );
                          }
                          else
                              if (p.
                                    peekNextToken(
                                      "modifier"))
                                  modifiers =
                                    (new String[] { p.
                                       getNextToken(
                                         ) });
                          if (p.
                                peekNextToken(
                                  "transform"))
                              transform =
                                parseMatrix(
                                  );
                      }
                      if (p.peekNextToken(
                              "accel")) api.
                                          parameter(
                                            "accel",
                                            p.
                                              getNextToken(
                                                ));
                      p.checkNextToken("type");
                      String type = p.getNextToken(
                                        );
                      if (p.peekNextToken(
                              "name")) name =
                                         p.
                                           getNextToken(
                                             );
                      else
                          name =
                            api.
                              getUniqueName(
                                type);
                      if (type.equals("mesh")) {
                          UI.
                            printWarning(
                              Module.
                                API,
                              "Deprecated object type: mesh");
                          UI.
                            printInfo(
                              Module.
                                API,
                              "Reading mesh: %s ...",
                              name);
                          int numVertices =
                            p.
                            getNextInt(
                              );
                          int numTriangles =
                            p.
                            getNextInt(
                              );
                          float[] points =
                            new float[numVertices *
                                        3];
                          float[] normals =
                            new float[numVertices *
                                        3];
                          float[] uvs =
                            new float[numVertices *
                                        2];
                          for (int i =
                                 0;
                               i <
                                 numVertices;
                               i++) {
                              p.
                                checkNextToken(
                                  "v");
                              points[3 *
                                       i +
                                       0] =
                                p.
                                  getNextFloat(
                                    );
                              points[3 *
                                       i +
                                       1] =
                                p.
                                  getNextFloat(
                                    );
                              points[3 *
                                       i +
                                       2] =
                                p.
                                  getNextFloat(
                                    );
                              normals[3 *
                                        i +
                                        0] =
                                p.
                                  getNextFloat(
                                    );
                              normals[3 *
                                        i +
                                        1] =
                                p.
                                  getNextFloat(
                                    );
                              normals[3 *
                                        i +
                                        2] =
                                p.
                                  getNextFloat(
                                    );
                              uvs[2 *
                                    i +
                                    0] =
                                p.
                                  getNextFloat(
                                    );
                              uvs[2 *
                                    i +
                                    1] =
                                p.
                                  getNextFloat(
                                    );
                          }
                          int[] triangles =
                            new int[numTriangles *
                                      3];
                          for (int i =
                                 0;
                               i <
                                 numTriangles;
                               i++) {
                              p.
                                checkNextToken(
                                  "t");
                              triangles[i *
                                          3 +
                                          0] =
                                p.
                                  getNextInt(
                                    );
                              triangles[i *
                                          3 +
                                          1] =
                                p.
                                  getNextInt(
                                    );
                              triangles[i *
                                          3 +
                                          2] =
                                p.
                                  getNextInt(
                                    );
                          }
                          api.
                            parameter(
                              "triangles",
                              triangles);
                          api.
                            parameter(
                              "points",
                              "point",
                              "vertex",
                              points);
                          api.
                            parameter(
                              "normals",
                              "vector",
                              "vertex",
                              normals);
                          api.
                            parameter(
                              "uvs",
                              "texcoord",
                              "vertex",
                              uvs);
                          api.
                            geometry(
                              name,
                              new TriangleMesh(
                                ));
                      }
                      else
                          if (type.
                                equals(
                                  "flat-mesh")) {
                              UI.
                                printWarning(
                                  Module.
                                    API,
                                  "Deprecated object type: flat-mesh");
                              UI.
                                printInfo(
                                  Module.
                                    API,
                                  "Reading flat mesh: %s ...",
                                  name);
                              int numVertices =
                                p.
                                getNextInt(
                                  );
                              int numTriangles =
                                p.
                                getNextInt(
                                  );
                              float[] points =
                                new float[numVertices *
                                            3];
                              float[] uvs =
                                new float[numVertices *
                                            2];
                              for (int i =
                                     0;
                                   i <
                                     numVertices;
                                   i++) {
                                  p.
                                    checkNextToken(
                                      "v");
                                  points[3 *
                                           i +
                                           0] =
                                    p.
                                      getNextFloat(
                                        );
                                  points[3 *
                                           i +
                                           1] =
                                    p.
                                      getNextFloat(
                                        );
                                  points[3 *
                                           i +
                                           2] =
                                    p.
                                      getNextFloat(
                                        );
                                  p.
                                    getNextFloat(
                                      );
                                  p.
                                    getNextFloat(
                                      );
                                  p.
                                    getNextFloat(
                                      );
                                  uvs[2 *
                                        i +
                                        0] =
                                    p.
                                      getNextFloat(
                                        );
                                  uvs[2 *
                                        i +
                                        1] =
                                    p.
                                      getNextFloat(
                                        );
                              }
                              int[] triangles =
                                new int[numTriangles *
                                          3];
                              for (int i =
                                     0;
                                   i <
                                     numTriangles;
                                   i++) {
                                  p.
                                    checkNextToken(
                                      "t");
                                  triangles[i *
                                              3 +
                                              0] =
                                    p.
                                      getNextInt(
                                        );
                                  triangles[i *
                                              3 +
                                              1] =
                                    p.
                                      getNextInt(
                                        );
                                  triangles[i *
                                              3 +
                                              2] =
                                    p.
                                      getNextInt(
                                        );
                              }
                              api.
                                parameter(
                                  "triangles",
                                  triangles);
                              api.
                                parameter(
                                  "points",
                                  "point",
                                  "vertex",
                                  points);
                              api.
                                parameter(
                                  "uvs",
                                  "texcoord",
                                  "vertex",
                                  uvs);
                              api.
                                geometry(
                                  name,
                                  new TriangleMesh(
                                    ));
                          }
                          else
                              if (type.
                                    equals(
                                      "sphere")) {
                                  UI.
                                    printInfo(
                                      Module.
                                        API,
                                      "Reading sphere ...");
                                  api.
                                    geometry(
                                      name,
                                      new Sphere(
                                        ));
                                  if (transform ==
                                        null &&
                                        !noInstance) {
                                      p.
                                        checkNextToken(
                                          "c");
                                      float x =
                                        p.
                                        getNextFloat(
                                          );
                                      float y =
                                        p.
                                        getNextFloat(
                                          );
                                      float z =
                                        p.
                                        getNextFloat(
                                          );
                                      p.
                                        checkNextToken(
                                          "r");
                                      float radius =
                                        p.
                                        getNextFloat(
                                          );
                                      api.
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
                                      api.
                                        parameter(
                                          "shaders",
                                          shaders);
                                      if (modifiers !=
                                            null)
                                          api.
                                            parameter(
                                              "modifiers",
                                              modifiers);
                                      api.
                                        instance(
                                          name +
                                          ".instance",
                                          name);
                                      noInstance =
                                        true;
                                  }
                              }
                              else
                                  if (type.
                                        equals(
                                          "banchoff")) {
                                      UI.
                                        printInfo(
                                          Module.
                                            API,
                                          "Reading banchoff ...");
                                      api.
                                        geometry(
                                          name,
                                          new BanchoffSurface(
                                            ));
                                  }
                                  else
                                      if (type.
                                            equals(
                                              "torus")) {
                                          UI.
                                            printInfo(
                                              Module.
                                                API,
                                              "Reading torus ...");
                                          p.
                                            checkNextToken(
                                              "r");
                                          api.
                                            parameter(
                                              "radiusInner",
                                              p.
                                                getNextFloat(
                                                  ));
                                          api.
                                            parameter(
                                              "radiusOuter",
                                              p.
                                                getNextFloat(
                                                  ));
                                          api.
                                            geometry(
                                              name,
                                              new Torus(
                                                ));
                                      }
                                      else
                                          if (type.
                                                equals(
                                                  "plane")) {
                                              UI.
                                                printInfo(
                                                  Module.
                                                    API,
                                                  "Reading plane ...");
                                              p.
                                                checkNextToken(
                                                  "p");
                                              api.
                                                parameter(
                                                  "center",
                                                  parsePoint(
                                                    ));
                                              if (p.
                                                    peekNextToken(
                                                      "n")) {
                                                  api.
                                                    parameter(
                                                      "normal",
                                                      parseVector(
                                                        ));
                                              }
                                              else {
                                                  p.
                                                    checkNextToken(
                                                      "p");
                                                  api.
                                                    parameter(
                                                      "point1",
                                                      parsePoint(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "p");
                                                  api.
                                                    parameter(
                                                      "point2",
                                                      parsePoint(
                                                        ));
                                              }
                                              api.
                                                geometry(
                                                  name,
                                                  new Plane(
                                                    ));
                                          }
                                          else
                                              if (type.
                                                    equals(
                                                      "cornellbox")) {
                                                  UI.
                                                    printInfo(
                                                      Module.
                                                        API,
                                                      "Reading cornell box ...");
                                                  if (transform !=
                                                        null)
                                                      UI.
                                                        printWarning(
                                                          Module.
                                                            API,
                                                          ("Instancing is not supported on cornell box -- ignoring trans" +
                                                           "form"));
                                                  p.
                                                    checkNextToken(
                                                      "corner0");
                                                  api.
                                                    parameter(
                                                      "corner0",
                                                      parsePoint(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "corner1");
                                                  api.
                                                    parameter(
                                                      "corner1",
                                                      parsePoint(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "left");
                                                  api.
                                                    parameter(
                                                      "leftColor",
                                                      parseColor(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "right");
                                                  api.
                                                    parameter(
                                                      "rightColor",
                                                      parseColor(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "top");
                                                  api.
                                                    parameter(
                                                      "topColor",
                                                      parseColor(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "bottom");
                                                  api.
                                                    parameter(
                                                      "bottomColor",
                                                      parseColor(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "back");
                                                  api.
                                                    parameter(
                                                      "backColor",
                                                      parseColor(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "emit");
                                                  api.
                                                    parameter(
                                                      "radiance",
                                                      parseColor(
                                                        ));
                                                  if (p.
                                                        peekNextToken(
                                                          "samples"))
                                                      api.
                                                        parameter(
                                                          "samples",
                                                          p.
                                                            getNextInt(
                                                              ));
                                                  new CornellBox(
                                                    ).
                                                    init(
                                                      name,
                                                      api);
                                                  noInstance =
                                                    true;
                                              }
                                              else
                                                  if (type.
                                                        equals(
                                                          "generic-mesh")) {
                                                      UI.
                                                        printInfo(
                                                          Module.
                                                            API,
                                                          "Reading generic mesh: %s ... ",
                                                          name);
                                                      p.
                                                        checkNextToken(
                                                          "points");
                                                      int np =
                                                        p.
                                                        getNextInt(
                                                          );
                                                      api.
                                                        parameter(
                                                          "points",
                                                          "point",
                                                          "vertex",
                                                          parseFloatArray(
                                                            np *
                                                              3));
                                                      p.
                                                        checkNextToken(
                                                          "triangles");
                                                      int nt =
                                                        p.
                                                        getNextInt(
                                                          );
                                                      api.
                                                        parameter(
                                                          "triangles",
                                                          parseIntArray(
                                                            nt *
                                                              3));
                                                      p.
                                                        checkNextToken(
                                                          "normals");
                                                      if (p.
                                                            peekNextToken(
                                                              "vertex"))
                                                          api.
                                                            parameter(
                                                              "normals",
                                                              "vector",
                                                              "vertex",
                                                              parseFloatArray(
                                                                np *
                                                                  3));
                                                      else
                                                          if (p.
                                                                peekNextToken(
                                                                  "facevarying"))
                                                              api.
                                                                parameter(
                                                                  "normals",
                                                                  "vector",
                                                                  "facevarying",
                                                                  parseFloatArray(
                                                                    nt *
                                                                      9));
                                                          else
                                                              p.
                                                                checkNextToken(
                                                                  "none");
                                                      p.
                                                        checkNextToken(
                                                          "uvs");
                                                      if (p.
                                                            peekNextToken(
                                                              "vertex"))
                                                          api.
                                                            parameter(
                                                              "uvs",
                                                              "texcoord",
                                                              "vertex",
                                                              parseFloatArray(
                                                                np *
                                                                  2));
                                                      else
                                                          if (p.
                                                                peekNextToken(
                                                                  "facevarying"))
                                                              api.
                                                                parameter(
                                                                  "uvs",
                                                                  "texcoord",
                                                                  "facevarying",
                                                                  parseFloatArray(
                                                                    nt *
                                                                      6));
                                                          else
                                                              p.
                                                                checkNextToken(
                                                                  "none");
                                                      if (p.
                                                            peekNextToken(
                                                              "face_shaders"))
                                                          api.
                                                            parameter(
                                                              "faceshaders",
                                                              parseIntArray(
                                                                nt));
                                                      api.
                                                        geometry(
                                                          name,
                                                          new TriangleMesh(
                                                            ));
                                                  }
                                                  else
                                                      if (type.
                                                            equals(
                                                              "hair")) {
                                                          UI.
                                                            printInfo(
                                                              Module.
                                                                API,
                                                              "Reading hair curves: %s ... ",
                                                              name);
                                                          p.
                                                            checkNextToken(
                                                              "segments");
                                                          api.
                                                            parameter(
                                                              "segments",
                                                              p.
                                                                getNextInt(
                                                                  ));
                                                          p.
                                                            checkNextToken(
                                                              "width");
                                                          api.
                                                            parameter(
                                                              "widths",
                                                              p.
                                                                getNextFloat(
                                                                  ));
                                                          p.
                                                            checkNextToken(
                                                              "points");
                                                          api.
                                                            parameter(
                                                              "points",
                                                              "point",
                                                              "vertex",
                                                              parseFloatArray(
                                                                p.
                                                                  getNextInt(
                                                                    )));
                                                          api.
                                                            geometry(
                                                              name,
                                                              new Hair(
                                                                ));
                                                      }
                                                      else
                                                          if (type.
                                                                equals(
                                                                  "janino-tesselatable")) {
                                                              UI.
                                                                printInfo(
                                                                  Module.
                                                                    API,
                                                                  "Reading procedural primitive: %s ... ",
                                                                  name);
                                                              String code =
                                                                p.
                                                                getNextCodeBlock(
                                                                  );
                                                              try {
                                                                  Tesselatable tess =
                                                                    (Tesselatable)
                                                                      ClassBodyEvaluator.
                                                                      createFastClassBodyEvaluator(
                                                                        new Scanner(
                                                                          null,
                                                                          new StringReader(
                                                                            code)),
                                                                        Tesselatable.class,
                                                                        ClassLoader.
                                                                          getSystemClassLoader(
                                                                            ));
                                                                  api.
                                                                    geometry(
                                                                      name,
                                                                      tess);
                                                              }
                                                              catch (CompileException e) {
                                                                  UI.
                                                                    printDetailed(
                                                                      Module.
                                                                        API,
                                                                      "Compiling: %s",
                                                                      code);
                                                                  UI.
                                                                    printError(
                                                                      Module.
                                                                        API,
                                                                      "%s",
                                                                      e.
                                                                        getMessage(
                                                                          ));
                                                                  e.
                                                                    printStackTrace(
                                                                      );
                                                                  noInstance =
                                                                    true;
                                                              }
                                                              catch (ParseException e) {
                                                                  UI.
                                                                    printDetailed(
                                                                      Module.
                                                                        API,
                                                                      "Compiling: %s",
                                                                      code);
                                                                  UI.
                                                                    printError(
                                                                      Module.
                                                                        API,
                                                                      "%s",
                                                                      e.
                                                                        getMessage(
                                                                          ));
                                                                  e.
                                                                    printStackTrace(
                                                                      );
                                                                  noInstance =
                                                                    true;
                                                              }
                                                              catch (ScanException e) {
                                                                  UI.
                                                                    printDetailed(
                                                                      Module.
                                                                        API,
                                                                      "Compiling: %s",
                                                                      code);
                                                                  UI.
                                                                    printError(
                                                                      Module.
                                                                        API,
                                                                      "%s",
                                                                      e.
                                                                        getMessage(
                                                                          ));
                                                                  e.
                                                                    printStackTrace(
                                                                      );
                                                                  noInstance =
                                                                    true;
                                                              }
                                                              catch (IOException e) {
                                                                  UI.
                                                                    printDetailed(
                                                                      Module.
                                                                        API,
                                                                      "Compiling: %s",
                                                                      code);
                                                                  UI.
                                                                    printError(
                                                                      Module.
                                                                        API,
                                                                      "%s",
                                                                      e.
                                                                        getMessage(
                                                                          ));
                                                                  e.
                                                                    printStackTrace(
                                                                      );
                                                                  noInstance =
                                                                    true;
                                                              }
                                                          }
                                                          else
                                                              if (type.
                                                                    equals(
                                                                      "teapot")) {
                                                                  UI.
                                                                    printInfo(
                                                                      Module.
                                                                        API,
                                                                      "Reading teapot: %s ... ",
                                                                      name);
                                                                  boolean hasTesselationArguments =
                                                                    false;
                                                                  if (p.
                                                                        peekNextToken(
                                                                          "subdivs")) {
                                                                      api.
                                                                        parameter(
                                                                          "subdivs",
                                                                          p.
                                                                            getNextInt(
                                                                              ));
                                                                      hasTesselationArguments =
                                                                        true;
                                                                  }
                                                                  if (p.
                                                                        peekNextToken(
                                                                          "smooth")) {
                                                                      api.
                                                                        parameter(
                                                                          "smooth",
                                                                          p.
                                                                            getNextBoolean(
                                                                              ));
                                                                      hasTesselationArguments =
                                                                        true;
                                                                  }
                                                                  if (hasTesselationArguments)
                                                                      api.
                                                                        geometry(
                                                                          name,
                                                                          (Tesselatable)
                                                                            new Teapot(
                                                                            ));
                                                                  else
                                                                      api.
                                                                        geometry(
                                                                          name,
                                                                          (PrimitiveList)
                                                                            new Teapot(
                                                                            ));
                                                              }
                                                              else
                                                                  if (type.
                                                                        equals(
                                                                          "gumbo")) {
                                                                      UI.
                                                                        printInfo(
                                                                          Module.
                                                                            API,
                                                                          "Reading gumbo: %s ... ",
                                                                          name);
                                                                      boolean hasTesselationArguments =
                                                                        false;
                                                                      if (p.
                                                                            peekNextToken(
                                                                              "subdivs")) {
                                                                          api.
                                                                            parameter(
                                                                              "subdivs",
                                                                              p.
                                                                                getNextInt(
                                                                                  ));
                                                                          hasTesselationArguments =
                                                                            true;
                                                                      }
                                                                      if (p.
                                                                            peekNextToken(
                                                                              "smooth")) {
                                                                          api.
                                                                            parameter(
                                                                              "smooth",
                                                                              p.
                                                                                getNextBoolean(
                                                                                  ));
                                                                          hasTesselationArguments =
                                                                            true;
                                                                      }
                                                                      if (hasTesselationArguments)
                                                                          api.
                                                                            geometry(
                                                                              name,
                                                                              (Tesselatable)
                                                                                new Gumbo(
                                                                                ));
                                                                      else
                                                                          api.
                                                                            geometry(
                                                                              name,
                                                                              (PrimitiveList)
                                                                                new Gumbo(
                                                                                ));
                                                                  }
                                                                  else
                                                                      if (type.
                                                                            equals(
                                                                              "julia")) {
                                                                          UI.
                                                                            printInfo(
                                                                              Module.
                                                                                API,
                                                                              "Reading julia fractal: %s ... ",
                                                                              name);
                                                                          if (p.
                                                                                peekNextToken(
                                                                                  "q")) {
                                                                              api.
                                                                                parameter(
                                                                                  "cw",
                                                                                  p.
                                                                                    getNextFloat(
                                                                                      ));
                                                                              api.
                                                                                parameter(
                                                                                  "cx",
                                                                                  p.
                                                                                    getNextFloat(
                                                                                      ));
                                                                              api.
                                                                                parameter(
                                                                                  "cy",
                                                                                  p.
                                                                                    getNextFloat(
                                                                                      ));
                                                                              api.
                                                                                parameter(
                                                                                  "cz",
                                                                                  p.
                                                                                    getNextFloat(
                                                                                      ));
                                                                          }
                                                                          if (p.
                                                                                peekNextToken(
                                                                                  "iterations"))
                                                                              api.
                                                                                parameter(
                                                                                  "iterations",
                                                                                  p.
                                                                                    getNextInt(
                                                                                      ));
                                                                          if (p.
                                                                                peekNextToken(
                                                                                  "epsilon"))
                                                                              api.
                                                                                parameter(
                                                                                  "epsilon",
                                                                                  p.
                                                                                    getNextFloat(
                                                                                      ));
                                                                          api.
                                                                            geometry(
                                                                              name,
                                                                              new JuliaFractal(
                                                                                ));
                                                                      }
                                                                      else
                                                                          if (type.
                                                                                equals(
                                                                                  "particles") ||
                                                                                type.
                                                                                equals(
                                                                                  "dlasurface")) {
                                                                              if (type.
                                                                                    equals(
                                                                                      "dlasurface"))
                                                                                  UI.
                                                                                    printWarning(
                                                                                      Module.
                                                                                        API,
                                                                                      ("Deprecated object type: \"dlasurface\" - please use \"partic" +
                                                                                       "les\" instead"));
                                                                              p.
                                                                                checkNextToken(
                                                                                  "filename");
                                                                              String filename =
                                                                                p.
                                                                                getNextToken(
                                                                                  );
                                                                              boolean littleEndian =
                                                                                false;
                                                                              if (p.
                                                                                    peekNextToken(
                                                                                      "little_endian"))
                                                                                  littleEndian =
                                                                                    true;
                                                                              UI.
                                                                                printInfo(
                                                                                  Module.
                                                                                    USER,
                                                                                  "Loading particle file: %s",
                                                                                  filename);
                                                                              File file =
                                                                                new File(
                                                                                filename);
                                                                              FileInputStream stream =
                                                                                new FileInputStream(
                                                                                filename);
                                                                              MappedByteBuffer map =
                                                                                stream.
                                                                                getChannel(
                                                                                  ).
                                                                                map(
                                                                                  FileChannel.MapMode.
                                                                                    READ_ONLY,
                                                                                  0,
                                                                                  file.
                                                                                    length(
                                                                                      ));
                                                                              if (littleEndian)
                                                                                  map.
                                                                                    order(
                                                                                      ByteOrder.
                                                                                        LITTLE_ENDIAN);
                                                                              FloatBuffer buffer =
                                                                                map.
                                                                                asFloatBuffer(
                                                                                  );
                                                                              float[] data =
                                                                                new float[buffer.
                                                                                            capacity(
                                                                                              )];
                                                                              for (int i =
                                                                                     0;
                                                                                   i <
                                                                                     data.
                                                                                       length;
                                                                                   i++)
                                                                                  data[i] =
                                                                                    buffer.
                                                                                      get(
                                                                                        i);
                                                                              stream.
                                                                                close(
                                                                                  );
                                                                              api.
                                                                                parameter(
                                                                                  "particles",
                                                                                  "point",
                                                                                  "vertex",
                                                                                  data);
                                                                              if (p.
                                                                                    peekNextToken(
                                                                                      "num"))
                                                                                  api.
                                                                                    parameter(
                                                                                      "num",
                                                                                      p.
                                                                                        getNextInt(
                                                                                          ));
                                                                              else
                                                                                  api.
                                                                                    parameter(
                                                                                      "num",
                                                                                      data.
                                                                                        length /
                                                                                        3);
                                                                              p.
                                                                                checkNextToken(
                                                                                  "radius");
                                                                              api.
                                                                                parameter(
                                                                                  "radius",
                                                                                  p.
                                                                                    getNextFloat(
                                                                                      ));
                                                                              api.
                                                                                geometry(
                                                                                  name,
                                                                                  new ParticleSurface(
                                                                                    ));
                                                                          }
                                                                          else
                                                                              if (type.
                                                                                    equals(
                                                                                      "file-mesh")) {
                                                                                  UI.
                                                                                    printInfo(
                                                                                      Module.
                                                                                        API,
                                                                                      "Reading file mesh: %s ... ",
                                                                                      name);
                                                                                  p.
                                                                                    checkNextToken(
                                                                                      "filename");
                                                                                  api.
                                                                                    parameter(
                                                                                      "filename",
                                                                                      p.
                                                                                        getNextToken(
                                                                                          ));
                                                                                  if (p.
                                                                                        peekNextToken(
                                                                                          "smooth_normals"))
                                                                                      api.
                                                                                        parameter(
                                                                                          "smooth_normals",
                                                                                          p.
                                                                                            getNextBoolean(
                                                                                              ));
                                                                                  api.
                                                                                    geometry(
                                                                                      name,
                                                                                      new FileMesh(
                                                                                        ));
                                                                              }
                                                                              else
                                                                                  if (type.
                                                                                        equals(
                                                                                          "bezier-mesh")) {
                                                                                      UI.
                                                                                        printInfo(
                                                                                          Module.
                                                                                            API,
                                                                                          "Reading bezier mesh: %s ... ",
                                                                                          name);
                                                                                      p.
                                                                                        checkNextToken(
                                                                                          "n");
                                                                                      int nu;
                                                                                      int nv;
                                                                                      api.
                                                                                        parameter(
                                                                                          "nu",
                                                                                          nu =
                                                                                            p.
                                                                                              getNextInt(
                                                                                                ));
                                                                                      api.
                                                                                        parameter(
                                                                                          "nv",
                                                                                          nv =
                                                                                            p.
                                                                                              getNextInt(
                                                                                                ));
                                                                                      if (p.
                                                                                            peekNextToken(
                                                                                              "wrap")) {
                                                                                          api.
                                                                                            parameter(
                                                                                              "uwrap",
                                                                                              p.
                                                                                                getNextBoolean(
                                                                                                  ));
                                                                                          api.
                                                                                            parameter(
                                                                                              "vwrap",
                                                                                              p.
                                                                                                getNextBoolean(
                                                                                                  ));
                                                                                      }
                                                                                      p.
                                                                                        checkNextToken(
                                                                                          "points");
                                                                                      float[] points =
                                                                                        new float[3 *
                                                                                                    nu *
                                                                                                    nv];
                                                                                      for (int i =
                                                                                             0;
                                                                                           i <
                                                                                             points.
                                                                                               length;
                                                                                           i++)
                                                                                          points[i] =
                                                                                            p.
                                                                                              getNextFloat(
                                                                                                );
                                                                                      api.
                                                                                        parameter(
                                                                                          "points",
                                                                                          "point",
                                                                                          "vertex",
                                                                                          points);
                                                                                      if (p.
                                                                                            peekNextToken(
                                                                                              "subdivs"))
                                                                                          api.
                                                                                            parameter(
                                                                                              "subdivs",
                                                                                              p.
                                                                                                getNextInt(
                                                                                                  ));
                                                                                      if (p.
                                                                                            peekNextToken(
                                                                                              "smooth"))
                                                                                          api.
                                                                                            parameter(
                                                                                              "smooth",
                                                                                              p.
                                                                                                getNextBoolean(
                                                                                                  ));
                                                                                      api.
                                                                                        geometry(
                                                                                          name,
                                                                                          (Tesselatable)
                                                                                            new BezierMesh(
                                                                                            ));
                                                                                  }
                                                                                  else {
                                                                                      UI.
                                                                                        printWarning(
                                                                                          Module.
                                                                                            API,
                                                                                          "Unrecognized object type: %s",
                                                                                          p.
                                                                                            getNextToken(
                                                                                              ));
                                                                                      noInstance =
                                                                                        true;
                                                                                  }
                      if (!noInstance) { api.
                                           parameter(
                                             "shaders",
                                             shaders);
                                         if (modifiers !=
                                               null)
                                             api.
                                               parameter(
                                                 "modifiers",
                                                 modifiers);
                                         if (transform !=
                                               null)
                                             api.
                                               parameter(
                                                 "transform",
                                                 transform);
                                         api.
                                           instance(
                                             name +
                                             ".instance",
                                             name);
                      }
                      p.checkNextToken("}");
    }
    private void parseInstanceBlock(SunflowAPI api)
          throws ParserException,
        IOException { p.checkNextToken("{");
                      p.checkNextToken("name");
                      String name = p.getNextToken(
                                        );
                      UI.printInfo(Module.
                                     API,
                                   "Reading instance: %s ...",
                                   name);
                      p.checkNextToken("geometry");
                      String geoname = p.
                        getNextToken(
                          );
                      p.checkNextToken("transform");
                      api.parameter("transform",
                                    parseMatrix(
                                      ));
                      String[] shaders;
                      if (p.peekNextToken(
                              "shaders")) {
                          int n =
                            p.
                            getNextInt(
                              );
                          shaders =
                            (new String[n]);
                          for (int i =
                                 0;
                               i <
                                 n;
                               i++)
                              shaders[i] =
                                p.
                                  getNextToken(
                                    );
                      }
                      else {
                          p.
                            checkNextToken(
                              "shader");
                          shaders =
                            (new String[] { p.
                               getNextToken(
                                 ) });
                      }
                      api.parameter("shaders",
                                    shaders);
                      String[] modifiers =
                        null;
                      if (p.peekNextToken(
                              "modifiers")) {
                          int n =
                            p.
                            getNextInt(
                              );
                          modifiers =
                            (new String[n]);
                          for (int i =
                                 0;
                               i <
                                 n;
                               i++)
                              modifiers[i] =
                                p.
                                  getNextToken(
                                    );
                      }
                      else
                          if (p.
                                peekNextToken(
                                  "modifier"))
                              modifiers =
                                (new String[] { p.
                                   getNextToken(
                                     ) });
                      if (modifiers != null)
                          api.
                            parameter(
                              "modifiers",
                              modifiers);
                      api.instance(name, geoname);
                      p.checkNextToken("}");
    }
    private void parseLightBlock(SunflowAPI api)
          throws ParserException,
        IOException { p.checkNextToken("{");
                      p.checkNextToken("type");
                      if (p.peekNextToken(
                              "mesh")) { UI.
                                           printWarning(
                                             Module.
                                               API,
                                             "Deprecated light type: mesh");
                                         p.
                                           checkNextToken(
                                             "name");
                                         String name =
                                           p.
                                           getNextToken(
                                             );
                                         UI.
                                           printInfo(
                                             Module.
                                               API,
                                             "Reading light mesh: %s ...",
                                             name);
                                         p.
                                           checkNextToken(
                                             "emit");
                                         api.
                                           parameter(
                                             "radiance",
                                             parseColor(
                                               ));
                                         int samples =
                                           numLightSamples;
                                         if (p.
                                               peekNextToken(
                                                 "samples"))
                                             samples =
                                               p.
                                                 getNextInt(
                                                   );
                                         else
                                             UI.
                                               printWarning(
                                                 Module.
                                                   API,
                                                 "Samples keyword not found - defaulting to %d",
                                                 samples);
                                         api.
                                           parameter(
                                             "samples",
                                             samples);
                                         int numVertices =
                                           p.
                                           getNextInt(
                                             );
                                         int numTriangles =
                                           p.
                                           getNextInt(
                                             );
                                         float[] points =
                                           new float[3 *
                                                       numVertices];
                                         int[] triangles =
                                           new int[3 *
                                                     numTriangles];
                                         for (int i =
                                                0;
                                              i <
                                                numVertices;
                                              i++) {
                                             p.
                                               checkNextToken(
                                                 "v");
                                             points[3 *
                                                      i +
                                                      0] =
                                               p.
                                                 getNextFloat(
                                                   );
                                             points[3 *
                                                      i +
                                                      1] =
                                               p.
                                                 getNextFloat(
                                                   );
                                             points[3 *
                                                      i +
                                                      2] =
                                               p.
                                                 getNextFloat(
                                                   );
                                             p.
                                               getNextFloat(
                                                 );
                                             p.
                                               getNextFloat(
                                                 );
                                             p.
                                               getNextFloat(
                                                 );
                                             p.
                                               getNextFloat(
                                                 );
                                             p.
                                               getNextFloat(
                                                 );
                                         }
                                         for (int i =
                                                0;
                                              i <
                                                numTriangles;
                                              i++) {
                                             p.
                                               checkNextToken(
                                                 "t");
                                             triangles[3 *
                                                         i +
                                                         0] =
                                               p.
                                                 getNextInt(
                                                   );
                                             triangles[3 *
                                                         i +
                                                         1] =
                                               p.
                                                 getNextInt(
                                                   );
                                             triangles[3 *
                                                         i +
                                                         2] =
                                               p.
                                                 getNextInt(
                                                   );
                                         }
                                         api.
                                           parameter(
                                             "points",
                                             "point",
                                             "vertex",
                                             points);
                                         api.
                                           parameter(
                                             "triangles",
                                             triangles);
                                         TriangleMeshLight mesh =
                                           new TriangleMeshLight(
                                           );
                                         mesh.
                                           init(
                                             name,
                                             api);
                      }
                      else
                          if (p.
                                peekNextToken(
                                  "point")) {
                              UI.
                                printInfo(
                                  Module.
                                    API,
                                  "Reading point light ...");
                              Color pow;
                              if (p.
                                    peekNextToken(
                                      "color")) {
                                  pow =
                                    parseColor(
                                      );
                                  p.
                                    checkNextToken(
                                      "power");
                                  float po =
                                    p.
                                    getNextFloat(
                                      );
                                  pow.
                                    mul(
                                      po);
                              }
                              else {
                                  UI.
                                    printWarning(
                                      Module.
                                        API,
                                      ("Deprecated color specification - please use color and power " +
                                       "instead"));
                                  p.
                                    checkNextToken(
                                      "power");
                                  pow =
                                    parseColor(
                                      );
                              }
                              p.
                                checkNextToken(
                                  "p");
                              api.
                                parameter(
                                  "center",
                                  parsePoint(
                                    ));
                              api.
                                parameter(
                                  "power",
                                  pow);
                              api.
                                light(
                                  api.
                                    getUniqueName(
                                      "pointlight"),
                                  new PointLight(
                                    ));
                          }
                          else
                              if (p.
                                    peekNextToken(
                                      "spherical")) {
                                  UI.
                                    printInfo(
                                      Module.
                                        API,
                                      "Reading spherical light ...");
                                  p.
                                    checkNextToken(
                                      "color");
                                  Color pow =
                                    parseColor(
                                      );
                                  p.
                                    checkNextToken(
                                      "radiance");
                                  pow.
                                    mul(
                                      p.
                                        getNextFloat(
                                          ));
                                  api.
                                    parameter(
                                      "radiance",
                                      pow);
                                  p.
                                    checkNextToken(
                                      "center");
                                  api.
                                    parameter(
                                      "center",
                                      parsePoint(
                                        ));
                                  p.
                                    checkNextToken(
                                      "radius");
                                  api.
                                    parameter(
                                      "radius",
                                      p.
                                        getNextFloat(
                                          ));
                                  p.
                                    checkNextToken(
                                      "samples");
                                  api.
                                    parameter(
                                      "samples",
                                      p.
                                        getNextInt(
                                          ));
                                  SphereLight light =
                                    new SphereLight(
                                    );
                                  light.
                                    init(
                                      api.
                                        getUniqueName(
                                          "spherelight"),
                                      api);
                              }
                              else
                                  if (p.
                                        peekNextToken(
                                          "directional")) {
                                      UI.
                                        printInfo(
                                          Module.
                                            API,
                                          "Reading directional light ...");
                                      p.
                                        checkNextToken(
                                          "source");
                                      Point3 s =
                                        parsePoint(
                                          );
                                      api.
                                        parameter(
                                          "source",
                                          s);
                                      p.
                                        checkNextToken(
                                          "target");
                                      Point3 t =
                                        parsePoint(
                                          );
                                      api.
                                        parameter(
                                          "dir",
                                          Point3.
                                            sub(
                                              t,
                                              s,
                                              new Vector3(
                                                )));
                                      p.
                                        checkNextToken(
                                          "radius");
                                      api.
                                        parameter(
                                          "radius",
                                          p.
                                            getNextFloat(
                                              ));
                                      p.
                                        checkNextToken(
                                          "emit");
                                      Color e =
                                        parseColor(
                                          );
                                      if (p.
                                            peekNextToken(
                                              "intensity")) {
                                          float i =
                                            p.
                                            getNextFloat(
                                              );
                                          e.
                                            mul(
                                              i);
                                      }
                                      else
                                          UI.
                                            printWarning(
                                              Module.
                                                API,
                                              ("Deprecated color specification - please use emit and intensi" +
                                               "ty instead"));
                                      api.
                                        parameter(
                                          "radiance",
                                          e);
                                      api.
                                        light(
                                          api.
                                            getUniqueName(
                                              "dirlight"),
                                          new DirectionalSpotlight(
                                            ));
                                  }
                                  else
                                      if (p.
                                            peekNextToken(
                                              "ibl")) {
                                          UI.
                                            printInfo(
                                              Module.
                                                API,
                                              "Reading image based light ...");
                                          p.
                                            checkNextToken(
                                              "image");
                                          api.
                                            parameter(
                                              "texture",
                                              p.
                                                getNextToken(
                                                  ));
                                          p.
                                            checkNextToken(
                                              "center");
                                          api.
                                            parameter(
                                              "center",
                                              parseVector(
                                                ));
                                          p.
                                            checkNextToken(
                                              "up");
                                          api.
                                            parameter(
                                              "up",
                                              parseVector(
                                                ));
                                          p.
                                            checkNextToken(
                                              "lock");
                                          api.
                                            parameter(
                                              "fixed",
                                              p.
                                                getNextBoolean(
                                                  ));
                                          int samples =
                                            numLightSamples;
                                          if (p.
                                                peekNextToken(
                                                  "samples"))
                                              samples =
                                                p.
                                                  getNextInt(
                                                    );
                                          else
                                              UI.
                                                printWarning(
                                                  Module.
                                                    API,
                                                  "Samples keyword not found - defaulting to %d",
                                                  samples);
                                          api.
                                            parameter(
                                              "samples",
                                              samples);
                                          ImageBasedLight ibl =
                                            new ImageBasedLight(
                                            );
                                          ibl.
                                            init(
                                              api.
                                                getUniqueName(
                                                  "ibl"),
                                              api);
                                      }
                                      else
                                          if (p.
                                                peekNextToken(
                                                  "meshlight")) {
                                              p.
                                                checkNextToken(
                                                  "name");
                                              String name =
                                                p.
                                                getNextToken(
                                                  );
                                              UI.
                                                printInfo(
                                                  Module.
                                                    API,
                                                  "Reading meshlight: %s ...",
                                                  name);
                                              p.
                                                checkNextToken(
                                                  "emit");
                                              Color e =
                                                parseColor(
                                                  );
                                              if (p.
                                                    peekNextToken(
                                                      "radiance")) {
                                                  float r =
                                                    p.
                                                    getNextFloat(
                                                      );
                                                  e.
                                                    mul(
                                                      r);
                                              }
                                              else
                                                  UI.
                                                    printWarning(
                                                      Module.
                                                        API,
                                                      ("Deprecated color specification - please use emit and radianc" +
                                                       "e instead"));
                                              api.
                                                parameter(
                                                  "radiance",
                                                  e);
                                              int samples =
                                                numLightSamples;
                                              if (p.
                                                    peekNextToken(
                                                      "samples"))
                                                  samples =
                                                    p.
                                                      getNextInt(
                                                        );
                                              else
                                                  UI.
                                                    printWarning(
                                                      Module.
                                                        API,
                                                      "Samples keyword not found - defaulting to %d",
                                                      samples);
                                              api.
                                                parameter(
                                                  "samples",
                                                  samples);
                                              p.
                                                checkNextToken(
                                                  "points");
                                              int np =
                                                p.
                                                getNextInt(
                                                  );
                                              api.
                                                parameter(
                                                  "points",
                                                  "point",
                                                  "vertex",
                                                  parseFloatArray(
                                                    np *
                                                      3));
                                              p.
                                                checkNextToken(
                                                  "triangles");
                                              int nt =
                                                p.
                                                getNextInt(
                                                  );
                                              api.
                                                parameter(
                                                  "triangles",
                                                  parseIntArray(
                                                    nt *
                                                      3));
                                              TriangleMeshLight mesh =
                                                new TriangleMeshLight(
                                                );
                                              mesh.
                                                init(
                                                  name,
                                                  api);
                                          }
                                          else
                                              if (p.
                                                    peekNextToken(
                                                      "sunsky")) {
                                                  p.
                                                    checkNextToken(
                                                      "up");
                                                  api.
                                                    parameter(
                                                      "up",
                                                      parseVector(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "east");
                                                  api.
                                                    parameter(
                                                      "east",
                                                      parseVector(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "sundir");
                                                  api.
                                                    parameter(
                                                      "sundir",
                                                      parseVector(
                                                        ));
                                                  p.
                                                    checkNextToken(
                                                      "turbidity");
                                                  api.
                                                    parameter(
                                                      "turbidity",
                                                      p.
                                                        getNextFloat(
                                                          ));
                                                  if (p.
                                                        peekNextToken(
                                                          "samples"))
                                                      api.
                                                        parameter(
                                                          "samples",
                                                          p.
                                                            getNextInt(
                                                              ));
                                                  SunSkyLight sunsky =
                                                    new SunSkyLight(
                                                    );
                                                  sunsky.
                                                    init(
                                                      api.
                                                        getUniqueName(
                                                          "sunsky"),
                                                      api);
                                              }
                                              else
                                                  UI.
                                                    printWarning(
                                                      Module.
                                                        API,
                                                      "Unrecognized object type: %s",
                                                      p.
                                                        getNextToken(
                                                          ));
                      p.checkNextToken("}");
    }
    private Color parseColor() throws IOException,
        ParserException { if (p.peekNextToken(
                                  "{")) {
                              String space =
                                p.
                                getNextToken(
                                  );
                              Color c =
                                null;
                              if (space.
                                    equals(
                                      "sRGB nonlinear")) {
                                  float r =
                                    p.
                                    getNextFloat(
                                      );
                                  float g =
                                    p.
                                    getNextFloat(
                                      );
                                  float b =
                                    p.
                                    getNextFloat(
                                      );
                                  c =
                                    new Color(
                                      r,
                                      g,
                                      b);
                                  c.
                                    toLinear(
                                      );
                              }
                              else
                                  if (space.
                                        equals(
                                          "sRGB linear")) {
                                      float r =
                                        p.
                                        getNextFloat(
                                          );
                                      float g =
                                        p.
                                        getNextFloat(
                                          );
                                      float b =
                                        p.
                                        getNextFloat(
                                          );
                                      c =
                                        new Color(
                                          r,
                                          g,
                                          b);
                                  }
                                  else
                                      UI.
                                        printWarning(
                                          Module.
                                            API,
                                          "Unrecognized color space: %s",
                                          space);
                              p.
                                checkNextToken(
                                  "}");
                              return c;
                          }
                          else {
                              float r =
                                p.
                                getNextFloat(
                                  );
                              float g =
                                p.
                                getNextFloat(
                                  );
                              float b =
                                p.
                                getNextFloat(
                                  );
                              return new Color(
                                r,
                                g,
                                b);
                          } }
    private Point3 parsePoint() throws IOException {
        float x =
          p.
          getNextFloat(
            );
        float y =
          p.
          getNextFloat(
            );
        float z =
          p.
          getNextFloat(
            );
        return new Point3(
          x,
          y,
          z);
    }
    private Vector3 parseVector() throws IOException {
        float x =
          p.
          getNextFloat(
            );
        float y =
          p.
          getNextFloat(
            );
        float z =
          p.
          getNextFloat(
            );
        return new Vector3(
          x,
          y,
          z);
    }
    private int[] parseIntArray(int size)
          throws IOException { int[] data =
                                 new int[size];
                               for (int i =
                                      0; i <
                                           size;
                                    i++) data[i] =
                                           p.
                                             getNextInt(
                                               );
                               return data;
    }
    private float[] parseFloatArray(int size)
          throws IOException { float[] data =
                                 new float[size];
                               for (int i =
                                      0; i <
                                           size;
                                    i++) data[i] =
                                           p.
                                             getNextFloat(
                                               );
                               return data;
    }
    private Matrix4 parseMatrix() throws IOException,
        ParserException { if (p.peekNextToken(
                                  "row")) {
                              return new Matrix4(
                                parseFloatArray(
                                  16),
                                true);
                          }
                          else
                              if (p.
                                    peekNextToken(
                                      "col")) {
                                  return new Matrix4(
                                    parseFloatArray(
                                      16),
                                    false);
                              }
                              else {
                                  Matrix4 m =
                                    Matrix4.
                                      IDENTITY;
                                  p.
                                    checkNextToken(
                                      "{");
                                  while (!p.
                                           peekNextToken(
                                             "}")) {
                                      Matrix4 t =
                                        null;
                                      if (p.
                                            peekNextToken(
                                              "translate")) {
                                          float x =
                                            p.
                                            getNextFloat(
                                              );
                                          float y =
                                            p.
                                            getNextFloat(
                                              );
                                          float z =
                                            p.
                                            getNextFloat(
                                              );
                                          t =
                                            Matrix4.
                                              translation(
                                                x,
                                                y,
                                                z);
                                      }
                                      else
                                          if (p.
                                                peekNextToken(
                                                  "scaleu")) {
                                              float s =
                                                p.
                                                getNextFloat(
                                                  );
                                              t =
                                                Matrix4.
                                                  scale(
                                                    s);
                                          }
                                          else
                                              if (p.
                                                    peekNextToken(
                                                      "scale")) {
                                                  float x =
                                                    p.
                                                    getNextFloat(
                                                      );
                                                  float y =
                                                    p.
                                                    getNextFloat(
                                                      );
                                                  float z =
                                                    p.
                                                    getNextFloat(
                                                      );
                                                  t =
                                                    Matrix4.
                                                      scale(
                                                        x,
                                                        y,
                                                        z);
                                              }
                                              else
                                                  if (p.
                                                        peekNextToken(
                                                          "rotatex")) {
                                                      float angle =
                                                        p.
                                                        getNextFloat(
                                                          );
                                                      t =
                                                        Matrix4.
                                                          rotateX(
                                                            (float)
                                                              Math.
                                                              toRadians(
                                                                angle));
                                                  }
                                                  else
                                                      if (p.
                                                            peekNextToken(
                                                              "rotatey")) {
                                                          float angle =
                                                            p.
                                                            getNextFloat(
                                                              );
                                                          t =
                                                            Matrix4.
                                                              rotateY(
                                                                (float)
                                                                  Math.
                                                                  toRadians(
                                                                    angle));
                                                      }
                                                      else
                                                          if (p.
                                                                peekNextToken(
                                                                  "rotatez")) {
                                                              float angle =
                                                                p.
                                                                getNextFloat(
                                                                  );
                                                              t =
                                                                Matrix4.
                                                                  rotateZ(
                                                                    (float)
                                                                      Math.
                                                                      toRadians(
                                                                        angle));
                                                          }
                                                          else
                                                              if (p.
                                                                    peekNextToken(
                                                                      "rotate")) {
                                                                  float x =
                                                                    p.
                                                                    getNextFloat(
                                                                      );
                                                                  float y =
                                                                    p.
                                                                    getNextFloat(
                                                                      );
                                                                  float z =
                                                                    p.
                                                                    getNextFloat(
                                                                      );
                                                                  float angle =
                                                                    p.
                                                                    getNextFloat(
                                                                      );
                                                                  t =
                                                                    Matrix4.
                                                                      rotate(
                                                                        x,
                                                                        y,
                                                                        z,
                                                                        (float)
                                                                          Math.
                                                                          toRadians(
                                                                            angle));
                                                              }
                                                              else
                                                                  UI.
                                                                    printWarning(
                                                                      Module.
                                                                        API,
                                                                      "Unrecognized transformation type: %s",
                                                                      p.
                                                                        getNextToken(
                                                                          ));
                                      if (t !=
                                            null)
                                          m =
                                            t.
                                              multiply(
                                                m);
                                  }
                                  return m;
                              } }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1af3AU1R1/d/kdAgkJhIhAQgi2IXgHFezQWGpIkxAMJhJg" +
       "NLbGzd673JK93XX3XXKgVEU7MNihHRsVHZrOOLFUisA4MrSlDkynBa1aRmTq" +
       "tLbQOu3ogMyUmZY6pdV+v+/t3u3tXZLL/dHM7Hf33s/P932/38/7lcNXSYFl" +
       "kiZDV7cPqjoL0DgLbFNXB9h2g1qBDV2reyTToqFWVbKszZDWL9cfK79+43uR" +
       "Cj8p7CNVkqbpTGKKrlmbqKWrwzTURcqTqW0qjVqMVHRtk4alYIwparBLsVhz" +
       "F5nhqspIQ5cDIQgQggAhyCEEW5KloNJMqsWirVhD0pj1EPkW8XWRQkNGeIws" +
       "Tm3EkEwpajfTwzWAForx91ZQileOm6QuobvQOU3hZ5qCo889UPFqHinvI+WK" +
       "1otwZADBoJM+Uhal0QFqWi2hEA31kdkapaFeaiqSquzguPtIpaUMahKLmTQx" +
       "SJgYM6jJ+0yOXJmMupkxmelmQr2wQtWQ86sgrEqDoGt1UlehYTumg4KlCgAz" +
       "w5JMnSr5Q4oWYqTWWyOhY8NdUACqFkUpi+iJrvI1CRJIpbCdKmmDwV5mKtog" +
       "FC3QY9ALI/MnbBTH2pDkIWmQ9jNS4y3XI7KgVAkfCKzCyFxvMd4SWGm+x0ou" +
       "+1y9+459D2vrNT/HHKKyiviLodIiT6VNNExNqslUVCxb1vWsVP36Hj8hUHiu" +
       "p7Aoc+KRa3cuX3T6DVHm5gxluge2UZn1y+MDs95d0Nq4Jg9hFBu6paDxUzTn" +
       "7t9j5zTHDYi86kSLmBlwMk9vOnPfY4foFT8p7SSFsq7GouBHs2U9aigqNTuo" +
       "Rk2J0VAnKaFaqJXnd5Ii+O5SNCpSu8Nhi7JOkq/ypEKd/4YhCkMTOERF8K1o" +
       "Yd35NiQW4d9xgxBSBA9ZDs89RPzxNyODwYgepUFJljRF04Pgu1Qy5UiQynq/" +
       "SQ092NbaHRyAUY5EJXPICloxLazqI/1yzGJ6NGiZclA3B53koKybFCPVomaw" +
       "t5UHnxlAhzP+f13FUeuKEZ8PDLLASwcqRNJ6XQ1Rs18eja1ru3ak/y1/Ijzs" +
       "8WKkDnoK2D0FsKeA6Cng9ER8Pt7BHOxRWBtsNQRRD3xY1tj7zQ0P7qnPAzcz" +
       "RvJhoLFoPShqw2iT9dYkNXRyApTBP2tevH934NODXxP+GZyYxzPWJqf3jzy+" +
       "9dEVfuJPJWRUC5JKsXoP0miCLhu8gZip3fLdH18/+uxOPRmSKQxvM0V6TYz0" +
       "eq8BTF2mIeDOZPPL6qTj/a/vbPCTfKAPoEwmgYsDGy3y9pES8c0Oe6IuBaBw" +
       "WDejkopZDuWVsoipjyRTuGfM4t+zwSgzMASq4NlixwR/Y26VgXKO8CS0skcL" +
       "zs7tPzv9/PEXmtb43URe7poaeykTtDA76SSbTUoh/U/7e77/zNXd93MPgRJL" +
       "MnXQgLIVSAJMBsP67Tce+v2li+MX/EmvYjBbxgZURY5DG7ckewEKUYHG0PYN" +
       "W7SoHlLCijSgUnTO/5QvXXn8k30VwpoqpDjOsHzqBpLpN60jj731wL8W8WZ8" +
       "Mk5hSc2TxcQAVCVbbjFNaTviiD9+fuHzZ6UfAMMCq1nKDsqJinDNCB/6IDfV" +
       "Mi4DnryVKOqMtLw4T6nhv/zQdePEQdSOM7Er+P7drQ7s+vBTrlFa+GSYgDz1" +
       "+4KHD8xvXXuF10/6MdaujadTEaxaknW/dCj6T3994a/9pKiPVMj2kmirpMbQ" +
       "W/pgGWA56yRYNqXkp07pYv5qTsTpAm8Mubr1RlCSAuEbS+N3qSdoynCU58Kz" +
       "yQ6aTd6g8RH+sYZXqedyKYovOj5bZJjKsITrLeITBpzLyE1uxrW2W4xGxeKN" +
       "D2CFKLcqFUYdPL02jN4JYLSgaIbAhKVmlzIYYb1S1FCpNblr9JhKFKbuYXtt" +
       "EdxZeWnowMevCF72+oGnMN0zuvfzwL5Rv2u1tiRtweSuI1ZsfKBnCg0/hz8f" +
       "PJ/hg5phgpixK1vtZUNdYt1gGBj/iyeDxbto/+jozpM/3rlbqFGZulhpgwF6" +
       "5Xf/fTuw/89vZpgR82AhKizBcTa4wszn2HBB2qzZKwNGYUaEuHCidR+HN75r" +
       "dCzU/dJKvx3idzFSwnTjVpUOU9XVXzW2lDKZbuQr3WQ47X35JyfYu01fEYou" +
       "m9jO3opnd12ev3lt5MFpTKG1Hp28Tb688fCbHbfIT/tJXiIq0xbvqZWaU2Ox" +
       "1KSw29A2p0TkokQo4AxGauC51w6FezNOY0mrJQnVb9Okbb8KTtPIIwGxNXAy" +
       "qt2G7RXvlp5O3vA3JiHpART3MVLAV0+8yNdRrBcsvQG4YEDXVSpp6UTOE7ak" +
       "BvwKeI7bWh6fUMtmDyKf8FL82c9LDU0+DGK2UvRAZ3dbXKZGwgEgr3FCmmoQ" +
       "r0QN3lMURQTYhw9AZxT2SOtUXR7KNBT5w7oSym4cVsPznj0O7+U+DjsyjwP+" +
       "1FCIUH8ExQiDRRDqsA52eoOmHtNCXBHMs7IDjbuPizboi7mD3pUV6CdRPMrI" +
       "DA66XVGZmEiyxLoSnss21su5Y907MVbelnCS76DYDeHHsfZEdKZr0x3cW6Hf" +
       "QoFXvHMD/HRWgEdRfJeRMg64o3O6YG+HPjtssB25g30hK7AHUDzHyFwOli8B" +
       "IEyHqTld1CsQgo16JHfUL2aFehzFDx3i2GzCtjWHePM9YeN9Ine8h7LCexjF" +
       "QSfeWmHyMqVpYAVC8x2zsR7LHeurWWF9DcVRh9AEVhhhzcId5DRAfxX6PmmD" +
       "Ppk1aBemjiTyn2fFar9AcYKR2S7kGyWYquPZwZ6DibUA95wN+1zuY/3LrMb6" +
       "VyhOOX7RG5FCgoennug41sXQIhNYxTs3rL/JCuvbKM4yMpNj3cg3vdmidWYN" +
       "/1M22qdyR3s+K7QXUJxzZg2x75suRdxGSN7fBGDxzg3wH7IC/EcU78MGRqyF" +
       "7LVuDiycXyQgi3dukD/MCvJfUVxyWJjPHdPAW4mJCwDnuI13PGu87kX05ayo" +
       "4RMUHzFSKqhBV+0rB1izznOvWRVcgwZ4/pQK8J3FQgB+ylbgVE4K/GNiY4hh" +
       "vo7imoO9R1fs2yAv9qjEIgGefVvWVJd/xsZ+Jifsn02B3cebvOEQ3FaauOsB" +
       "8DVp4EX+1OgrHfTv2OjfycXVOzjC4qlUKEVR4PBep8b4GR1sspdOvHHmRcR5" +
       "x9iPlvz20bElf/ETXx8pVqytktliDma45XHV+fvhS1fOz1x4hJ/55g9IltjZ" +
       "eq/H0m+/Ui61+CCUCf/v8OrHf68y7HMgXwX/nXYcxcOoEXaoYUWT1DgjhSrV" +
       "BsXNCU7PvllG3Bt5jnmrktvlVlXXKB6QOnlznD1k4vIQMuMZMW7hCvhm4bd3" +
       "g+5yRd/CSfJqUdwMasgIROAGA9ZmPtFtixqMn8Hu+Om81+44OHaRHynHSXZB" +
       "tQTc8YLtlhdyd8svTOWWjSgaHPJtV3XJdkxML7PN4tlDF4Sx2BSusGIyV8AC" +
       "Vbz/OQkfaEr3AUyuRlEzqVGbpjLqlyfJW4NiNYo6gQJlfbZmQvb4wDbTB7lw" +
       "n29tNhOP704UzQ4BitXoxAQo8ldlOLdnpNi5XEPXrUm7yBeXz/KRsfLieWNb" +
       "3hfU4VwQl3SR4nBMVd1H2K7vQsOkYbFKLhEH2gJ8h2eGcV314d0K/+D+1i7K" +
       "g4tVeMszko8vd7GNMByuYowU2V/uQj2M5EEh/LwncQzuOoITS7o44Vk19jAZ" +
       "Kb9Sbo6Qo/k/STgHkTHxbxL98tGxDXc/fO32l/ipJlCEtGMHtlIMdCouzWw6" +
       "dZ8he1tz2ipc33hj1rGSpc4hLaetSvumzI2NO+v/AA6m2K2SIgAA");
}
