package org.sunflow.core.parser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import org.sunflow.SunflowAPI;
import org.sunflow.core.SceneParser;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.core.shader.SimpleShader;
import org.sunflow.system.Parser;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class TriParser implements SceneParser {
    public boolean parse(String filename, SunflowAPI api) { try { UI.printInfo(
                                                                       Module.
                                                                         USER,
                                                                       "TRI - Reading geometry: \"%s\" ...",
                                                                       filename);
                                                                  Parser p =
                                                                    new Parser(
                                                                    filename);
                                                                  float[] verts =
                                                                    new float[3 *
                                                                                p.
                                                                                getNextInt(
                                                                                  )];
                                                                  for (int v =
                                                                         0;
                                                                       v <
                                                                         verts.
                                                                           length;
                                                                       v +=
                                                                         3) {
                                                                      verts[v +
                                                                              0] =
                                                                        p.
                                                                          getNextFloat(
                                                                            );
                                                                      verts[v +
                                                                              1] =
                                                                        p.
                                                                          getNextFloat(
                                                                            );
                                                                      verts[v +
                                                                              2] =
                                                                        p.
                                                                          getNextFloat(
                                                                            );
                                                                      p.
                                                                        getNextToken(
                                                                          );
                                                                      p.
                                                                        getNextToken(
                                                                          );
                                                                  }
                                                                  int[] triangles =
                                                                    new int[p.
                                                                              getNextInt(
                                                                                ) *
                                                                              3];
                                                                  for (int t =
                                                                         0;
                                                                       t <
                                                                         triangles.
                                                                           length;
                                                                       t +=
                                                                         3) {
                                                                      triangles[t +
                                                                                  0] =
                                                                        p.
                                                                          getNextInt(
                                                                            );
                                                                      triangles[t +
                                                                                  1] =
                                                                        p.
                                                                          getNextInt(
                                                                            );
                                                                      triangles[t +
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
                                                                      verts);
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
                                                                  p.
                                                                    close(
                                                                      );
                                                                  RandomAccessFile stream =
                                                                    new RandomAccessFile(
                                                                    filename.
                                                                      replace(
                                                                        ".tri",
                                                                        ".ra3"),
                                                                    "rw");
                                                                  MappedByteBuffer map =
                                                                    stream.
                                                                    getChannel(
                                                                      ).
                                                                    map(
                                                                      MapMode.
                                                                        READ_WRITE,
                                                                      0,
                                                                      8 +
                                                                        4 *
                                                                        (verts.
                                                                           length +
                                                                           triangles.
                                                                             length));
                                                                  map.
                                                                    order(
                                                                      ByteOrder.
                                                                        LITTLE_ENDIAN);
                                                                  IntBuffer ints =
                                                                    map.
                                                                    asIntBuffer(
                                                                      );
                                                                  FloatBuffer floats =
                                                                    map.
                                                                    asFloatBuffer(
                                                                      );
                                                                  ints.
                                                                    put(
                                                                      0,
                                                                      verts.
                                                                        length /
                                                                        3);
                                                                  ints.
                                                                    put(
                                                                      1,
                                                                      triangles.
                                                                        length /
                                                                        3);
                                                                  for (int i =
                                                                         0;
                                                                       i <
                                                                         verts.
                                                                           length;
                                                                       i++)
                                                                      floats.
                                                                        put(
                                                                          2 +
                                                                            i,
                                                                          verts[i]);
                                                                  for (int i =
                                                                         0;
                                                                       i <
                                                                         triangles.
                                                                           length;
                                                                       i++)
                                                                      ints.
                                                                        put(
                                                                          2 +
                                                                            verts.
                                                                              length +
                                                                            i,
                                                                          triangles[i]);
                                                                  stream.
                                                                    close(
                                                                      );
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
    public TriParser() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
