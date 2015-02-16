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
      1166294492000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
