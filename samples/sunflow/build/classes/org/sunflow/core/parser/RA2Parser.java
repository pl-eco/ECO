package org.sunflow.core.parser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import org.sunflow.SunflowAPI;
import org.sunflow.core.SceneParser;
import org.sunflow.core.camera.PinholeLens;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.core.shader.SimpleShader;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.Parser;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class RA2Parser implements SceneParser {
    public boolean parse(String filename, SunflowAPI api) { try { UI.printInfo(
                                                                       Module.
                                                                         USER,
                                                                       "RA2 - Reading geometry: \"%s\" ...",
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
                                                                      "points",
                                                                      "point",
                                                                      "vertex",
                                                                      data);
                                                                  int[] triangles =
                                                                    new int[3 *
                                                                              (data.
                                                                                 length /
                                                                                 9)];
                                                                  for (int i =
                                                                         0;
                                                                       i <
                                                                         triangles.
                                                                           length;
                                                                       i++)
                                                                      triangles[i] =
                                                                        i;
                                                                  api.
                                                                    parameter(
                                                                      "triangles",
                                                                      triangles);
                                                                  api.
                                                                    geometry(
                                                                      filename,
                                                                      new TriangleMesh(
                                                                        ));
                                                                  api.
                                                                    shader(
                                                                      filename +
                                                                      ".shader",
                                                                      new SimpleShader(
                                                                        ));
                                                                  api.
                                                                    parameter(
                                                                      "shaders",
                                                                      filename +
                                                                      ".shader");
                                                                  api.
                                                                    instance(
                                                                      filename +
                                                                      ".instance",
                                                                      filename);
                                                            }
                                                            catch (FileNotFoundException e) {
                                                                e.
                                                                  printStackTrace(
                                                                    );
                                                                return false;
                                                            }
                                                            catch (IOException e) {
                                                                e.
                                                                  printStackTrace(
                                                                    );
                                                                return false;
                                                            }
                                                            try {
                                                                filename =
                                                                  filename.
                                                                    replace(
                                                                      ".ra2",
                                                                      ".txt");
                                                                UI.
                                                                  printInfo(
                                                                    Module.
                                                                      USER,
                                                                    "RA2 - Reading camera  : \"%s\" ...",
                                                                    filename);
                                                                Parser p =
                                                                  new Parser(
                                                                  filename);
                                                                Point3 eye =
                                                                  new Point3(
                                                                  );
                                                                eye.
                                                                  x =
                                                                  p.
                                                                    getNextFloat(
                                                                      );
                                                                eye.
                                                                  y =
                                                                  p.
                                                                    getNextFloat(
                                                                      );
                                                                eye.
                                                                  z =
                                                                  p.
                                                                    getNextFloat(
                                                                      );
                                                                Point3 to =
                                                                  new Point3(
                                                                  );
                                                                to.
                                                                  x =
                                                                  p.
                                                                    getNextFloat(
                                                                      );
                                                                to.
                                                                  y =
                                                                  p.
                                                                    getNextFloat(
                                                                      );
                                                                to.
                                                                  z =
                                                                  p.
                                                                    getNextFloat(
                                                                      );
                                                                Vector3 up =
                                                                  new Vector3(
                                                                  );
                                                                switch (p.
                                                                          getNextInt(
                                                                            )) {
                                                                    case 0:
                                                                        up.
                                                                          set(
                                                                            1,
                                                                            0,
                                                                            0);
                                                                        break;
                                                                    case 1:
                                                                        up.
                                                                          set(
                                                                            0,
                                                                            1,
                                                                            0);
                                                                        break;
                                                                    case 2:
                                                                        up.
                                                                          set(
                                                                            0,
                                                                            0,
                                                                            1);
                                                                        break;
                                                                    default:
                                                                        UI.
                                                                          printWarning(
                                                                            Module.
                                                                              USER,
                                                                            "RA2 - Invalid up vector specification - using Z axis");
                                                                        up.
                                                                          set(
                                                                            0,
                                                                            0,
                                                                            1);
                                                                        break;
                                                                }
                                                                api.
                                                                  parameter(
                                                                    "eye",
                                                                    eye);
                                                                api.
                                                                  parameter(
                                                                    "target",
                                                                    to);
                                                                api.
                                                                  parameter(
                                                                    "up",
                                                                    up);
                                                                String name =
                                                                  api.
                                                                  getUniqueName(
                                                                    "camera");
                                                                api.
                                                                  parameter(
                                                                    "fov",
                                                                    80.0F);
                                                                api.
                                                                  camera(
                                                                    name,
                                                                    new PinholeLens(
                                                                      ));
                                                                api.
                                                                  parameter(
                                                                    "camera",
                                                                    name);
                                                                api.
                                                                  parameter(
                                                                    "resolutionX",
                                                                    1024);
                                                                api.
                                                                  parameter(
                                                                    "resolutionY",
                                                                    1024);
                                                                api.
                                                                  options(
                                                                    SunflowAPI.
                                                                      DEFAULT_OPTIONS);
                                                                p.
                                                                  close(
                                                                    );
                                                            }
                                                            catch (FileNotFoundException e) {
                                                                UI.
                                                                  printWarning(
                                                                    Module.
                                                                      USER,
                                                                    "RA2 - Camera file not found");
                                                            }
                                                            catch (IOException e) {
                                                                e.
                                                                  printStackTrace(
                                                                    );
                                                                return false;
                                                            }
                                                            return true;
    }
    public RA2Parser() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "uM0E5o9+XvPZ2lOTN8UV9g/64d7ZZg4AAA==");
}
