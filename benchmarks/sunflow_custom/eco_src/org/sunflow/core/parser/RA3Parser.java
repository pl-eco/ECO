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
                                                                  IntBuffer ints =
                                                                    map.
                                                                    asIntBuffer();
                                                                  FloatBuffer buffer =
                                                                    map.
                                                                    asFloatBuffer();
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
                                                                    close();
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
    public RA3Parser() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1166294492000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAJ1Xe2wURRif3vVBH6YP6INXS0tBoPQWIpjQkkCtBY4ecLRQ" +
       "oIDHdHfuuu3e7rI7\n114LQYiJRYgPoiaaKBJDUkAQEzRoggoBFOUfMFETEl" +
       "BDoiaKiTFBjP7hNzP33GtLwiU3OzvzPeab\n7/t+37en76Ec20IzZNtDh0xi" +
       "e1o7/diyidKqYdveBEsB+WpOvn+0XTdcKMuHXKpCUbFPtiUFUyyp\niuR9uj" +
       "lqoQbT0IZCmkE9JEo9fdrSmLy1vqUZArccPV924Hh2jQvl+FAx1nWDYqoaep" +
       "tGwjZFJb4+\nPIClCFU1yafatNmHHiN6JNxq6DbFOrV3o33I7UO5psxkUlTr" +
       "iyuXQLlkYguHJa5e8nO1IGGyRShW\ndaK0JNQB58J0Tjh2jK8jkxqETGKbXW" +
       "AOPwFYPSthtbA2w1TTfaLryb3HTrpRcTcqVvVOJkwGSyjo\n60ZFYRLuIZbd" +
       "oihE6UalOiFKJ7FUrKnDXGs3KrPVkI5pxCJ2B7ENbYARltkRk1hcZ3zRh4pk" +
       "ZpMV\nkalhJe4oqBJNib/lBDUcArMrkmYLc1exdTCwQIWDWUEskzhLdr+qg8" +
       "drnBwJG+vbgQBY88KE9hoJ\nVdk6hgVUJnypYT0kdVJL1UNAmmNEQAtF08YV" +
       "yu7axHI/DpEARVVOOr/YAqp8fhGMhaJyJxmXBF6a\n5vBSin8aKu4fPPHWZy" +
       "t5bGcrRNbY+QuAqdrB1EGCxCK6TATjg4jnNe+2yAwXQkBc7iAWNC1zzm/2\n" +
       "/fp5jaCZPgbNhp4+ItOAvP5ITcee1QZys2NMMg1bZc5Ps5yngz+20xw1IWsr" +
       "EhLZpie+ebHji237\nT5HfXKjAi3JlQ4uEIY5KZSNsqhqxVhOdWJgSxYvyia" +
       "608n0vyoO5D0JerG4IBm1CvShb40u5Bn+H\nKwqCCHZF+TBX9aARn5uY9vJ5" +
       "1EQI5cEfNcK/HIkff1K0xCPZET2oGYOSbcmSYYUS77JhEZa6NrGk\njpYneC" +
       "ZZHhY9JkV+qdcIEwnLWFd1QwqpkK+y0aiQAfZ8BJlRdtaywawsBn7OJNYg/t" +
       "cYmkKsgDx6\n9+u9be0vHBQBwoI6ZiXADqjyxFR5mCqPUOVJqEJZWVzDFKZS" +
       "OAmuuB+SFWCtaH7nzrW7Dta5ITrM\nwWy4H0ZaB/bEztEmG63JjPZy8JMhrK" +
       "re3T7ieTC6QoSVND7wjsldeOPM9WN/FS1wIdfYqMjsA1wu\nYGL8DEoTaFfv" +
       "zKOx5P9xaN25767fnpfMKIrqMxI9k5Mlap3TE5YhEwWgLyn++NRi9xbUdcSF" +
       "siH7\nAfH4+QFMqp060hK2OQ5+zJY8HyoMGlYYa2wrjlgFtNcyBpMrPERK2D" +
       "BFRAtzpOOAHDcfPJe76PsL\nhVe5xXGILU4pYp2EioQtTcbBJosQWL/9hv/V" +
       "1++NbOdBEIsCCpUt0qOpchRY5iZZIFM1QAvmo/rN\nethQ1KCKezTCgum/4j" +
       "mLP/r9pRJx6xqsxJ228OECkutTn0L7rz/zdzUXkyWzSpE0I0kmrJmclNxi\n" +
       "WXiInSN64JuZb36J3wYgA/Cw1WHC8QBxyxC/Rw+/3vl8bHTsLWJDHcheOE5U" +
       "j1GXA/LeU6G6yO6v\nPuGnLsSpBT7VDeuw2SycynVPBqUeFBvScIrtlptsrG" +
       "AuqHRm7xps94KwJRfX7yjRLv4LartBrQxF\n095gAWpE0zwdo87Ju3XpcsWu" +
       "m27kWoUKNAMrqzCPf5QPgUfsXgCcqLliJT9GyeAkNvJ7Qfy002K3\nFE174y" +
       "+z+Tg3Fj1sPi+VKovPKymakYFXnTLUAgFVzNCZ49VJXuNHtv5Z9Dy+slPATl" +
       "l67WmD/uyX\nocvk8eUv/jQGVOZTw2zUyADR0g4GKtPgbh1vIZLJfujke+fp" +
       "zYYmoXLB+EjnZFzQdGy4puns4UcA\nuRrHJThFlw5M3+juVa+5eJcj8C2jO0" +
       "pnak69DlAK54lYOrtYtlLEY3JWIiYLmWeXwb8iFpMVzpjk\naMSGJkcqufi9" +
       "uuIOL+FByHovj+i94hsVqZHQKZ4tfi8X3D5Bem5kwxqKcnihA/dVpX43WGoY" +
       "+o8B\njrd3n6/79Nrmd0aE5+ZP8HGQyhWQn/3hx373y5d6BJ+zB3MQH6k+/v" +
       "O5ux1TRMCJRnV2Rq+YyiOa\nVW5LsclCvnYiDZz6SkPt6X0dd/iJGN9KivJ6" +
       "DEMjWETRYjZ4RcItfXj6Qi4k2gMGolUZXxCi65V9\nt/bsuO/79h9e6BKdaS" +
       "G0h8GIpqWEU2po5ZoWCar8nIUC6kz+2EVR5TjdCqs2fMLPGRD0gPwlTnqK\n" +
       "stkjlSxIUWEKGdxMbJZKpFLkBiI27TPHCE3RA6fjGruZ2WlBwz/q4ikZEZ91" +
       "AXnrme2zooc3vcLz\nPAc+B4eHef8OnyOiwCfSunZcaXFZN9AHZ7suvL8s7u" +
       "i00p8BuovF7gRuBygZu/S2hU3Ki+Xwx5Uf\nLh89esfFi///7GKRMYsPAAA=");
}
