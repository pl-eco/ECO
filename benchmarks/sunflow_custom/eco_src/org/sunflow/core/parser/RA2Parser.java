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
                                                                    getChannel().
                                                                    map(
                                                                      FileChannel.MapMode.
                                                                        READ_ONLY,
                                                                      0,
                                                                      file.
                                                                        length());
                                                                  map.
                                                                    order(
                                                                      ByteOrder.
                                                                        LITTLE_ENDIAN);
                                                                  FloatBuffer buffer =
                                                                    map.
                                                                    asFloatBuffer();
                                                                  float[] data =
                                                                    new float[buffer.
                                                                                capacity()];
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
                                                                    close();
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
                                                                  printStackTrace();
                                                                return false;
                                                            }
                                                            catch (IOException e) {
                                                                e.
                                                                  printStackTrace();
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
                                                                    getNextFloat();
                                                                eye.
                                                                  y =
                                                                  p.
                                                                    getNextFloat();
                                                                eye.
                                                                  z =
                                                                  p.
                                                                    getNextFloat();
                                                                Point3 to =
                                                                  new Point3(
                                                                  );
                                                                to.
                                                                  x =
                                                                  p.
                                                                    getNextFloat();
                                                                to.
                                                                  y =
                                                                  p.
                                                                    getNextFloat();
                                                                to.
                                                                  z =
                                                                  p.
                                                                    getNextFloat();
                                                                Vector3 up =
                                                                  new Vector3(
                                                                  );
                                                                switch (p.
                                                                          getNextInt()) {
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
                                                                  close();
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
                                                                  printStackTrace();
                                                                return false;
                                                            }
                                                            return true;
    }
    public RA2Parser() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1166294492000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAJ1XfWwURRSf3l1b+mGuLdCWr5aWgkDpLRgwoSWBWgscPeBo" +
       "oUABy3R37rrt3u6y\nO9deC0GIiSDED6ImmigSQ1JAEBM0aIIKARTlHzBREx" +
       "JQQ6ImiokxQYz+4ZuZ+9xrS8IlNzs78z7m\nzXvv996evodybQtNl20fHTKJ" +
       "7WvpCGLLJkqLhm17Iyx1y1dzC4IjbbrhQjkB5FIVirwB2ZYUTLGk\nKpL/6a" +
       "aYhepNQxsKawb1kRj19WlL4vLWBJZkCdx89HzZ/uOeahfKDSAv1nWDYqoaeq" +
       "tGIjZFJYE+\nPIClKFU1KaDatCmAHiN6NNJi6DbFOrV3ob3IHUB5psxkUlQT" +
       "SCiXQLlkYgtHJK5eCnK1IGGiRShW\ndaI0J9UB54JMTjh2nK89mxqETGCbnW" +
       "AOPwFYPTNptbA2y1TTfaLzyT3HTrqRtwt5Vb2DCZPBEgr6\nulBxhER6iGU3" +
       "KwpRulCpTojSQSwVa+ow19qFymw1rGMatYjdTmxDG2CEZXbUJBbXmVgMoGKZ" +
       "2WRF\nZWpYyTsKqURTEm+5IQ2HwezylNnC3JVsHQwsVOFgVgjLJMHi6Vd18H" +
       "i1kyNpY10bEABrfoTQXiOp\nyqNjWEBlwpca1sNSB7VUPQykuUYUtFA0dUyh" +
       "7K5NLPfjMOmmqNJJFxRbQFXAL4KxUDTZScYlgZem\nOryU5p/68vsHT7z12Q" +
       "oe2x6FyBo7fyEwVTmY2kmIWESXiWB8EPW95t8ane5CCIgnO4gFTfPs85sC\n" +
       "v35eLWimjUKzvqePyLRbXnekun33KgO52TEmmIatMudnWM7TIRjfaYqZkLXl" +
       "SYls05fYvNj+xdZ9\np8hvLlToR3myoUUjEEelshExVY1Yq4hOLEyJ4kcFRF" +
       "da+L4f5cM8ACEvVteHQjahfuTR+FKewd/h\nikIggl1RAcxVPWQk5iamvXwe" +
       "MxFC+fBHDfCvQOLHnxQt9kl2VA9pxqBkW7JkWOHku2xYhKWuTSyp\nvfkJnk" +
       "mWj0WPSVFQ6jUiRMIy1lXdkMIq5KtsNChkgD0fQWaMnbVsMCeHgZ8ziTWI/9" +
       "WGphCrWx65\n+/We1rYXDooAYUEdtxJgB1T54qp8TJVPqPIlVaGcHK5hElMp" +
       "nARX3A/JCrBWPK9jx5qdB2vdEB3m\noAfuh5HWgj3xc7TKRksqo/0c/GQIq8" +
       "p3tx3wPRhZLsJKGht4R+UuunHm+rG/iue7kGt0VGT2AS4X\nMjFBBqVJtKtz" +
       "5tFo8v84tPbcd9dvz01lFEV1WYmezckStdbpCcuQiQLQlxJ/fIrXvRl1HnEh" +
       "D2Q/\nIB4/P4BJlVNHRsI2JcCP2ZIfQEUhw4pgjW0lEKuQ9lrGYGqFh0gJGy" +
       "aJaGGOdByQ4+aD5/IWfn+h\n6Cq3OAGx3rQi1kGoSNjSVBxstAiB9dtvBF99" +
       "/d6BbTwI4lFAobJFezRVjgHLnBQLZKoGaMF8VLdJ\njxiKGlJxj0ZYMP3nnb" +
       "3oo99fKhG3rsFKwmkLHi4gtT7lKbTv+jN/V3ExOTKrFCkzUmTCmokpyc2W\n" +
       "hYfYOWL7v5nx5pf4bQAyAA9bHSYcDxC3DPF79PHrncfHBsfeQjbUguwFY0T1" +
       "KHW5W95zKlwb3fXV\nJ/zURTi9wKe7YS02m4RTue6JoNSH4kMGTrHdySYby5" +
       "kLKpzZuxrbvSBs8cV120u0i/+C2i5QK0PR\ntNdbgBqxDE/HqXPzb126XL7z" +
       "phu5VqJCzcDKSszjHxVA4BG7FwAnZi5fwY9RMjiBjfxeED/t1Pgt\nxTLe+M" +
       "ssPs6JRw+bz02nyuHzCoqmZ+FVhwy1QEAVM3TGWHWS1/gDW/4sfh5f2SFgpy" +
       "yz9rRCf/bL\n0GXy+LIXfxoFKguoYTZoZIBoGQcDlRlwt5a3EKlkP3TyvfP0" +
       "Zn2jUDl/bKRzMs5vPDZc3Xj28COA\nXLXjEpyiSwembXD3qtdcvMsR+JbVHW" +
       "UyNaVfByiF80QtnV0sWynmMTkzGZNFzLNL4V8Zj8lKZ0xy\nNGJDoyOVXPxe" +
       "XQmHl/AgZL2XT/ReiY3y9EjoEM/moJ8LbhsnPTewYTVFubzQgfsq078bLDUC" +
       "/ccA\nx9u7z9d+em3TOweE5+aN83GQztUtP/vDj/3uly/1CD5nD+YgPlJ1/O" +
       "dzd9sniYATjeqsrF4xnUc0\nq9wWr8lCvmY8DZz6Sn3N6b3td/iJGN8KivJ7" +
       "DEMjWETRIjb4RcIteXj6Qi4k2wMGopVZXxCi65UD\nt3Zvvx/49h9e6JKdaR" +
       "G0h6GopqWFU3po5ZkWCan8nEUC6kz+2ElRxRjdCqs2fMLP2S3oAflLnPQU\n" +
       "edgjnSxEUVEaGdxMfJZOpFLkBiI27TNHCU3RA2fiGruZWRlBwz/qEikZFZ91" +
       "3fKWM9tmxg5vfIXn\neS58Dg4P8/4dPkdEgU+mdc2Y0hKybqAPznZeeH9pwt" +
       "EZpT8LdBeJ3XHcDlAyeultjZiUF8vhjys+\nXDZy9I6LF///ARKfeSaLDwAA");
}
