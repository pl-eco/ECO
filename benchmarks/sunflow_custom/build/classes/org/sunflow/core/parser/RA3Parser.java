package org.sunflow.core.parser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import org.sunflow.SunflowAPI;
import org.sunflow.core.SceneParser;
import org.sunflow.core.Shader;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.core.shader.SimpleShader;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class RA3Parser implements SceneParser {
    public boolean parse(String filename, SunflowAPI api) { try { UI.printInfo(
                                                                       Module.
                                                                         USER,
                                                                       "RA3 - Reading geometry: \"%s\" ...",
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
                                                                  IntBuffer ints =
                                                                    map.
                                                                    asIntBuffer(
                                                                      );
                                                                  FloatBuffer buffer =
                                                                    map.
                                                                    asFloatBuffer(
                                                                      );
                                                                  int numVerts =
                                                                    ints.
                                                                    get(
                                                                      0);
                                                                  int numTris =
                                                                    ints.
                                                                    get(
                                                                      1);
                                                                  UI.
                                                                    printInfo(
                                                                      Module.
                                                                        USER,
                                                                      "RA3 -   * Reading %d vertices ...",
                                                                      numVerts);
                                                                  float[] verts =
                                                                    new float[3 *
                                                                                numVerts];
                                                                  for (int i =
                                                                         0;
                                                                       i <
                                                                         verts.
                                                                           length;
                                                                       i++)
                                                                      verts[i] =
                                                                        buffer.
                                                                          get(
                                                                            2 +
                                                                              i);
                                                                  UI.
                                                                    printInfo(
                                                                      Module.
                                                                        USER,
                                                                      "RA3 -   * Reading %d triangles ...",
                                                                      numTris);
                                                                  int[] tris =
                                                                    new int[3 *
                                                                              numTris];
                                                                  for (int i =
                                                                         0;
                                                                       i <
                                                                         tris.
                                                                           length;
                                                                       i++)
                                                                      tris[i] =
                                                                        ints.
                                                                          get(
                                                                            2 +
                                                                              verts.
                                                                                length +
                                                                              i);
                                                                  stream.
                                                                    close(
                                                                      );
                                                                  UI.
                                                                    printInfo(
                                                                      Module.
                                                                        USER,
                                                                      "RA3 -   * Creating mesh ...");
                                                                  api.
                                                                    parameter(
                                                                      "triangles",
                                                                      tris);
                                                                  api.
                                                                    parameter(
                                                                      "points",
                                                                      "point",
                                                                      "vertex",
                                                                      verts);
                                                                  api.
                                                                    geometry(
                                                                      filename,
                                                                      new TriangleMesh(
                                                                        ));
                                                                  Shader s =
                                                                    api.
                                                                    lookupShader(
                                                                      "ra3shader");
                                                                  if (s ==
                                                                        null) {
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
                                                                  }
                                                                  else {
                                                                      api.
                                                                        parameter(
                                                                          "shaders",
                                                                          "ra3shader");
                                                                  }
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
                                                            return true;
    }
    public RA3Parser() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1166294492000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
