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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
