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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
