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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "tMccJjB/9PN5n609PXlLXGH/ANxrlqhmDgAA");
}
