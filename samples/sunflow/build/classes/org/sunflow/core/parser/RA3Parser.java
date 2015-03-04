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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "IB1xmwnMH/183mfrTk/eElfYP6sSP39mDgAA");
}
