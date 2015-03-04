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
                                                                                getNextInt()];
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
                                                                          getNextFloat();
                                                                      verts[v +
                                                                              1] =
                                                                        p.
                                                                          getNextFloat();
                                                                      verts[v +
                                                                              2] =
                                                                        p.
                                                                          getNextFloat();
                                                                      p.
                                                                        getNextToken();
                                                                      p.
                                                                        getNextToken();
                                                                  }
                                                                  int[] triangles =
                                                                    new int[p.
                                                                              getNextInt() *
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
                                                                          getNextInt();
                                                                      triangles[t +
                                                                                  1] =
                                                                        p.
                                                                          getNextInt();
                                                                      triangles[t +
                                                                                  2] =
                                                                        p.
                                                                          getNextInt();
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
                                                                    close();
                                                                  RandomAccessFile stream =
                                                                    new RandomAccessFile(
                                                                    filename.
                                                                      replace(
                                                                        ".tri",
                                                                        ".ra3"),
                                                                    "rw");
                                                                  MappedByteBuffer map =
                                                                    stream.
                                                                    getChannel().
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
                                                                    asIntBuffer();
                                                                  FloatBuffer floats =
                                                                    map.
                                                                    asFloatBuffer();
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
                                                                    close();
                                                            }
                                                            catch (FileNotFoundException e) {
                                                                e.
                                                                  printStackTrace();
                                                                return false;
                                                            }
                                                            catch (IOException e) {
                                                                e.
                                                                  printStackTrace();
                                                                return false;
                                                            }
                                                            return true;
    }
    public TriParser() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1166294492000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAJ1Xe2wURRif3vVBH6YP2lJeLS0Ped5CAiZQEqi1wNEDjrYU" +
       "KGCZ7s5dt93bXWbn\n2qMQhJhQhPggaqKJIjEkPAQxQYMmqBBAUf4BEzUhAT" +
       "UkaqKYGBPE6B9+M3PPvRYSLrnZ2ZnvMd98\n3/f7vj19D+U5FE1SHR/baRPH" +
       "19wexNQhWrOBHacDlrrVq3mFweOtpuVBOQHk0TWGSgOqo2iYYUXX\nFP8zjT" +
       "GK5tiWsTNsWMxHYszXZyyKy1sdWJQlcOOR8xX7juXWeVBeAJVi07QYZrplth" +
       "gk4jBUFujD\nA1iJMt1QArrDGgPoCWJGI82W6TBsMmcH2oO8AZRvq1wmQ/WB" +
       "hHIFlCs2pjiiCPVKUKgFCWMpYVg3\nidaUVAecczM54dhxvrZsahAyhm92gj" +
       "niBGD1lKTV0tosU23vic6ndh896UWlXahUN9u5MBUsYaCv\nC5VESKSHUKdJ" +
       "04jWhcpNQrR2QnVs6ENCaxeqcPSwiVmUEqeNOJYxwAkrnKhNqNCZWAygEpXb" +
       "RKMq\ns2jyjkI6MbTEW17IwGEwuzpltjR3BV8HA4t0OBgNYZUkWHL7dRM8Xu" +
       "fmSNo4rRUIgLUgQlivlVSV\na2JYQBXSlwY2w0o7o7oZBtI8KwpaGJowqlB+" +
       "1zZW+3GYdDNU46YLyi2gKhQXwVkYqnKTCUngpQku\nL6X5Z071/QMn3vpsuY" +
       "jtXI2oBj9/ETDVupjaSIhQYqpEMj6I+l7zb45O8iAExFUuYknTNP38hsCv\n" +
       "n9dJmokj0Kzr6SMq61bXHq5r27XSQl5+jDG25ejc+RmWi3QIxncaYzZkbXVS" +
       "It/0JTYvtn2xee8p\n8psHFflRvmoZ0QjEUblqRWzdIHQlMQnFjGh+VEhMrV" +
       "ns+1EBzAMQ8nJ1XSjkEOZHuYZYyrfEO1xR\nCETwKyqEuW6GrMTcxqxXzGM2" +
       "QqgA/mge/CuR/IknQwt9ihM1Q4Y1qDhUVSwaTr6rFiU8dR1ClQ6q\ni0yiPh" +
       "49NkNBpdeKEAWr2NRNSwnrkK+qNU8jA/z5GDJj/KwVgzk5HPzcSWxA/K+yDI" +
       "3QbvX43a93\nt7S+cEAGCA/quJUAO6DKF1fl46p8UpUvqQrl5AgNlVyldBJc" +
       "cT8kK8Bayaz2bau3H2jwQnTYg7lw\nP5y0AeyJn6NFtZpTGe0X4KdCWNW8u2" +
       "XY9+D4MhlWyujAOyJ38Y0z14/+VTLbgzwjoyK3D3C5iIsJ\ncihNot00dx6N" +
       "JP+Pg2vOfXf99sxURjE0LSvRszl5oja4PUEtlWgAfSnxx8aXejeizsMelAvZ" +
       "D4gn\nzg9gUuvWkZGwjQnw47YUBFBxyKIRbPCtBGIVsV5qDaZWRIiU8aFSRg" +
       "t3pOuAAjcfPJ8///sLxVeF\nxQmILU0rYu2EyYQtT8VBByUE1m+/EXz19XvD" +
       "W0QQxKOAQWWL9hi6GgOWGSkWyFQD0IL7aNoGM2Jp\nekjHPQbhwfRf6fQFH/" +
       "3+Upm8dQNWEk6b+2gBqfXxT6O915/9u1aIyVF5pUiZkSKT1oxNSW6iFO/k\n" +
       "54jt+2bym1/itwHIADwcfYgIPEDCMiTu0Seud5YY57n25vOhAWTPHSWqR6jL" +
       "3eruU+GG6I6vPhGn\nLsbpBT7dDWuw3SidKnSPBaU+FB8ycIrvVtl8rOYuGO" +
       "fO3lXY6QVhCy+u3VpmXPwX1HaBWhWKprOO\nAmrEMjwdp84ruHXpcvX2m17k" +
       "WYGKDAtrK7CIf1QIgUecXgCcmL1suThG2eAYPop7QeK0E+K3FMt4\nEy9TxT" +
       "gjHj18PjOdKkfMxzE0KQuv2lWoBRKquKGTR6uTosYPb/qzZD++sk3CTkVm7W" +
       "mB/uyXnZfJ\nk0tf/GkEqCxklj3PIAPEyDgYqMyAuzWihUgl+8GT751nN+cs" +
       "kSpnj450bsbZS44O1S05e+gxQK7O\ndQlu0eUDE9d7e/VrHtHlSHzL6o4ymR" +
       "rTrwOUwnmi1OQXy1dKRExOScZkMffsYvhXxWOyyh2TAo34\nsMSVSh5xr56E" +
       "w8tEEPLeyyd7r8RGdXoktMtnU9AvBLc+JD3X82EVQ3mi0IH7atK/G6gegf5j" +
       "QODt\n3f0Nn17b8M6w9Nysh3wcpHN1q8/98GO/9+VLPZLP3YO5iA/XHvv53N" +
       "22ShlwslGdmtUrpvPIZlXY\nUmrzkK9/mAZBfWVO/ek9bXfEiTjfcoYKeizL" +
       "IFhG0QI++GXCLXp0+kIuJNsDDqI1WV8QsutVA7d2\nbb0f+PYfUeiSnWkxtI" +
       "ehqGGkhVN6aOXblIR0cc5iCXW2eGxnaNwo3QqvNmIiztkt6QH5y9z0DOXy\n" +
       "RzpZiKHiNDK4mfgsnUhnyAtEfNpnjxCasgfOxDV+M1MzgkZ81CVSMio/67rV" +
       "TWe2TIkd6nhF5Hke\nfA4ODYn+HT5HZIFPpnX9qNISsm6gD852Xnh/ccLRGa" +
       "U/C3QXyN2HuB2gZOTS2xKxmSiWQx+P+3Dp\n8SN3PKL4/w+5OWr5iw8AAA==");
}
