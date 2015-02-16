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
      1166294492000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
