package org.sunflow.core.tesselatable;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Tesselatable;
import org.sunflow.core.ParameterList.InterpolationType;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.Memory;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
import org.sunflow.util.FloatArray;
import org.sunflow.util.IntArray;
public class FileMesh implements Tesselatable {
    private String filename = null;
    private boolean smoothNormals = false;
    public BoundingBox getWorldBounds(Matrix4 o2w) { return null; }
    public PrimitiveList tesselate() { if (filename.endsWith(".ra3")) { try {
                                                                            UI.
                                                                              printInfo(
                                                                                Module.
                                                                                  GEOM,
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
                                                                                  GEOM,
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
                                                                                  GEOM,
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
                                                                                  GEOM,
                                                                                "RA3 -   * Creating mesh ...");
                                                                            return generate(
                                                                                     tris,
                                                                                     verts,
                                                                                     smoothNormals);
                                                                        }
                                                                        catch (FileNotFoundException e) {
                                                                            e.
                                                                              printStackTrace(
                                                                                );
                                                                            UI.
                                                                              printError(
                                                                                Module.
                                                                                  GEOM,
                                                                                "Unable to read mesh file \"%s\" - file not found",
                                                                                filename);
                                                                        }
                                                                        catch (IOException e) {
                                                                            e.
                                                                              printStackTrace(
                                                                                );
                                                                            UI.
                                                                              printError(
                                                                                Module.
                                                                                  GEOM,
                                                                                "Unable to read mesh file \"%s\" - I/O error occured",
                                                                                filename);
                                                                        }
                                       }
                                       else
                                           if (filename.
                                                 endsWith(
                                                   ".obj")) {
                                               int lineNumber =
                                                 1;
                                               try {
                                                   UI.
                                                     printInfo(
                                                       Module.
                                                         GEOM,
                                                       "OBJ - Reading geometry: \"%s\" ...",
                                                       filename);
                                                   FloatArray verts =
                                                     new FloatArray(
                                                     );
                                                   IntArray tris =
                                                     new IntArray(
                                                     );
                                                   FileReader file =
                                                     new FileReader(
                                                     filename);
                                                   BufferedReader bf =
                                                     new BufferedReader(
                                                     file);
                                                   String line;
                                                   while ((line =
                                                             bf.
                                                               readLine(
                                                                 )) !=
                                                            null) {
                                                       if (line.
                                                             startsWith(
                                                               "v")) {
                                                           String[] v =
                                                             line.
                                                             split(
                                                               "\\s+");
                                                           verts.
                                                             add(
                                                               Float.
                                                                 parseFloat(
                                                                   v[1]));
                                                           verts.
                                                             add(
                                                               Float.
                                                                 parseFloat(
                                                                   v[2]));
                                                           verts.
                                                             add(
                                                               Float.
                                                                 parseFloat(
                                                                   v[3]));
                                                       }
                                                       else
                                                           if (line.
                                                                 startsWith(
                                                                   "f")) {
                                                               String[] f =
                                                                 line.
                                                                 split(
                                                                   "\\s+");
                                                               if (f.
                                                                     length ==
                                                                     5) {
                                                                   tris.
                                                                     add(
                                                                       Integer.
                                                                         parseInt(
                                                                           f[1]) -
                                                                         1);
                                                                   tris.
                                                                     add(
                                                                       Integer.
                                                                         parseInt(
                                                                           f[2]) -
                                                                         1);
                                                                   tris.
                                                                     add(
                                                                       Integer.
                                                                         parseInt(
                                                                           f[3]) -
                                                                         1);
                                                                   tris.
                                                                     add(
                                                                       Integer.
                                                                         parseInt(
                                                                           f[1]) -
                                                                         1);
                                                                   tris.
                                                                     add(
                                                                       Integer.
                                                                         parseInt(
                                                                           f[3]) -
                                                                         1);
                                                                   tris.
                                                                     add(
                                                                       Integer.
                                                                         parseInt(
                                                                           f[4]) -
                                                                         1);
                                                               }
                                                               else
                                                                   if (f.
                                                                         length ==
                                                                         4) {
                                                                       tris.
                                                                         add(
                                                                           Integer.
                                                                             parseInt(
                                                                               f[1]) -
                                                                             1);
                                                                       tris.
                                                                         add(
                                                                           Integer.
                                                                             parseInt(
                                                                               f[2]) -
                                                                             1);
                                                                       tris.
                                                                         add(
                                                                           Integer.
                                                                             parseInt(
                                                                               f[3]) -
                                                                             1);
                                                                   }
                                                           }
                                                       if (lineNumber %
                                                             100000 ==
                                                             0)
                                                           UI.
                                                             printInfo(
                                                               Module.
                                                                 GEOM,
                                                               "OBJ -   * Parsed %7d lines ...",
                                                               lineNumber);
                                                       lineNumber++;
                                                   }
                                                   file.
                                                     close(
                                                       );
                                                   UI.
                                                     printInfo(
                                                       Module.
                                                         GEOM,
                                                       "OBJ -   * Creating mesh ...");
                                                   return generate(
                                                            tris.
                                                              trim(
                                                                ),
                                                            verts.
                                                              trim(
                                                                ),
                                                            smoothNormals);
                                               }
                                               catch (FileNotFoundException e) {
                                                   e.
                                                     printStackTrace(
                                                       );
                                                   UI.
                                                     printError(
                                                       Module.
                                                         GEOM,
                                                       "Unable to read mesh file \"%s\" - file not found",
                                                       filename);
                                               }
                                               catch (NumberFormatException e) {
                                                   e.
                                                     printStackTrace(
                                                       );
                                                   UI.
                                                     printError(
                                                       Module.
                                                         GEOM,
                                                       "Unable to read mesh file \"%s\" - syntax error at line %d",
                                                       lineNumber);
                                               }
                                               catch (IOException e) {
                                                   e.
                                                     printStackTrace(
                                                       );
                                                   UI.
                                                     printError(
                                                       Module.
                                                         GEOM,
                                                       "Unable to read mesh file \"%s\" - I/O error occured",
                                                       filename);
                                               }
                                           }
                                           else
                                               if (filename.
                                                     endsWith(
                                                       ".stl")) {
                                                   try {
                                                       UI.
                                                         printInfo(
                                                           Module.
                                                             GEOM,
                                                           "STL - Reading geometry: \"%s\" ...",
                                                           filename);
                                                       FileInputStream file =
                                                         new FileInputStream(
                                                         filename);
                                                       DataInputStream stream =
                                                         new DataInputStream(
                                                         new BufferedInputStream(
                                                           file));
                                                       file.
                                                         skip(
                                                           80);
                                                       int numTris =
                                                         getLittleEndianInt(
                                                           stream.
                                                             readInt(
                                                               ));
                                                       UI.
                                                         printInfo(
                                                           Module.
                                                             GEOM,
                                                           "STL -   * Reading %d triangles ...",
                                                           numTris);
                                                       long filesize =
                                                         new File(
                                                         filename).
                                                         length(
                                                           );
                                                       if (filesize !=
                                                             84 +
                                                             50 *
                                                             numTris) {
                                                           UI.
                                                             printWarning(
                                                               Module.
                                                                 GEOM,
                                                               "STL - Size of file mismatch (expecting %s, found %s)",
                                                               Memory.
                                                                 bytesToString(
                                                                   84 +
                                                                     14 *
                                                                     numTris),
                                                               Memory.
                                                                 bytesToString(
                                                                   filesize));
                                                           return null;
                                                       }
                                                       int[] tris =
                                                         new int[3 *
                                                                   numTris];
                                                       float[] verts =
                                                         new float[9 *
                                                                     numTris];
                                                       for (int i =
                                                              0,
                                                              i3 =
                                                                0,
                                                              index =
                                                                0;
                                                            i <
                                                              numTris;
                                                            i++,
                                                              i3 +=
                                                                3) {
                                                           stream.
                                                             readInt(
                                                               );
                                                           stream.
                                                             readInt(
                                                               );
                                                           stream.
                                                             readInt(
                                                               );
                                                           for (int j =
                                                                  0;
                                                                j <
                                                                  3;
                                                                j++,
                                                                  index +=
                                                                    3) {
                                                               tris[i3 +
                                                                      j] =
                                                                 i3 +
                                                                   j;
                                                               verts[index +
                                                                       0] =
                                                                 getLittleEndianFloat(
                                                                   stream.
                                                                     readInt(
                                                                       ));
                                                               verts[index +
                                                                       1] =
                                                                 getLittleEndianFloat(
                                                                   stream.
                                                                     readInt(
                                                                       ));
                                                               verts[index +
                                                                       2] =
                                                                 getLittleEndianFloat(
                                                                   stream.
                                                                     readInt(
                                                                       ));
                                                           }
                                                           stream.
                                                             readShort(
                                                               );
                                                           if ((i +
                                                                  1) %
                                                                 100000 ==
                                                                 0)
                                                               UI.
                                                                 printInfo(
                                                                   Module.
                                                                     GEOM,
                                                                   "STL -   * Parsed %7d triangles ...",
                                                                   i +
                                                                     1);
                                                       }
                                                       file.
                                                         close(
                                                           );
                                                       UI.
                                                         printInfo(
                                                           Module.
                                                             GEOM,
                                                           "STL -   * Creating mesh ...");
                                                       if (smoothNormals)
                                                           UI.
                                                             printWarning(
                                                               Module.
                                                                 GEOM,
                                                               ("STL - format does not support shared vertices - normal smoot" +
                                                                "hing disabled"));
                                                       return generate(
                                                                tris,
                                                                verts,
                                                                false);
                                                   }
                                                   catch (FileNotFoundException e) {
                                                       e.
                                                         printStackTrace(
                                                           );
                                                       UI.
                                                         printError(
                                                           Module.
                                                             GEOM,
                                                           "Unable to read mesh file \"%s\" - file not found",
                                                           filename);
                                                   }
                                                   catch (IOException e) {
                                                       e.
                                                         printStackTrace(
                                                           );
                                                       UI.
                                                         printError(
                                                           Module.
                                                             GEOM,
                                                           "Unable to read mesh file \"%s\" - I/O error occured",
                                                           filename);
                                                   }
                                               }
                                               else
                                                   UI.
                                                     printWarning(
                                                       Module.
                                                         GEOM,
                                                       "Unable to read mesh file \"%s\" - unrecognized format",
                                                       filename);
                                       return null;
    }
    private TriangleMesh generate(int[] tris,
                                  float[] verts,
                                  boolean smoothNormals) {
        ParameterList pl =
          new ParameterList(
          );
        pl.
          addIntegerArray(
            "triangles",
            tris);
        pl.
          addPoints(
            "points",
            InterpolationType.
              VERTEX,
            verts);
        if (smoothNormals) {
            float[] normals =
              new float[verts.
                          length];
            Point3 p0 =
              new Point3(
              );
            Point3 p1 =
              new Point3(
              );
            Point3 p2 =
              new Point3(
              );
            Vector3 n =
              new Vector3(
              );
            for (int i3 =
                   0;
                 i3 <
                   tris.
                     length;
                 i3 +=
                   3) {
                int v0 =
                  tris[i3 +
                         0];
                int v1 =
                  tris[i3 +
                         1];
                int v2 =
                  tris[i3 +
                         2];
                p0.
                  set(
                    verts[3 *
                            v0 +
                            0],
                    verts[3 *
                            v0 +
                            1],
                    verts[3 *
                            v0 +
                            2]);
                p1.
                  set(
                    verts[3 *
                            v1 +
                            0],
                    verts[3 *
                            v1 +
                            1],
                    verts[3 *
                            v1 +
                            2]);
                p2.
                  set(
                    verts[3 *
                            v2 +
                            0],
                    verts[3 *
                            v2 +
                            1],
                    verts[3 *
                            v2 +
                            2]);
                Point3.
                  normal(
                    p0,
                    p1,
                    p2,
                    n);
                normals[3 *
                          v0 +
                          0] +=
                  n.
                    x;
                normals[3 *
                          v0 +
                          1] +=
                  n.
                    y;
                normals[3 *
                          v0 +
                          2] +=
                  n.
                    z;
                normals[3 *
                          v1 +
                          0] +=
                  n.
                    x;
                normals[3 *
                          v1 +
                          1] +=
                  n.
                    y;
                normals[3 *
                          v1 +
                          2] +=
                  n.
                    z;
                normals[3 *
                          v2 +
                          0] +=
                  n.
                    x;
                normals[3 *
                          v2 +
                          1] +=
                  n.
                    y;
                normals[3 *
                          v2 +
                          2] +=
                  n.
                    z;
            }
            for (int i3 =
                   0;
                 i3 <
                   normals.
                     length;
                 i3 +=
                   3) {
                n.
                  set(
                    normals[i3 +
                              0],
                    normals[i3 +
                              1],
                    normals[i3 +
                              2]);
                n.
                  normalize(
                    );
                normals[i3 +
                          0] =
                  n.
                    x;
                normals[i3 +
                          1] =
                  n.
                    y;
                normals[i3 +
                          2] =
                  n.
                    z;
            }
            pl.
              addVectors(
                "normals",
                InterpolationType.
                  VERTEX,
                normals);
        }
        TriangleMesh m =
          new TriangleMesh(
          );
        if (m.
              update(
                pl,
                null))
            return m;
        return null;
    }
    public boolean update(ParameterList pl,
                          SunflowAPI api) {
        String file =
          pl.
          getString(
            "filename",
            null);
        if (file !=
              null)
            filename =
              api.
                resolveIncludeFilename(
                  file);
        smoothNormals =
          pl.
            getBoolean(
              "smooth_normals",
              smoothNormals);
        return filename !=
          null;
    }
    private int getLittleEndianInt(int i) {
        return i >>>
          24 |
          i >>>
          8 &
          65280 |
          i <<
          8 &
          16711680 |
          i <<
          24;
    }
    private float getLittleEndianFloat(int i) {
        return Float.
          intBitsToFloat(
            getLittleEndianInt(
              i));
    }
    public FileMesh() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1170211998000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL0YbWwUx3XubPwVg40J4FAw+ANS43CbNE3V4CiNsWwwPYOF" +
       "gaoXJc54b+68eG93sztnH6YOgaoFUdWtGichEfGPliQlJRBFQfQrkiu1JSlV" +
       "pURVq0ZqaPlTVIpUfjSNmrbpezO7t3t75zPtj560c7Mz77153+/Nnr1Bljg2" +
       "6bJM/WBaN3mM5XjsgH5fjB+0mBPbGb9viNoOS/bq1HH2wtqI2vZawwcffXOs" +
       "MUqqEmQFNQyTU66ZhrOHOaY+wZJx0uCv9uks43DSGD9AJ6iS5ZquxDWHd8fJ" +
       "bQFUTjriHgsKsKAAC4pgQenxoQBpKTOymV7EoAZ3HidPkEicVFkqssdJayER" +
       "i9o045IZEhIAhRp83w9CCeScTTbkZZcyFwn8dJcy++yjja9XkIYEadCMYWRH" +
       "BSY4HJIg9RmWGWW205NMsmSCLDcYSw4zW6O6NiX4TpAmR0sblGdtllcSLmYt" +
       "Zoszfc3VqyibnVW5aefFS2lMT3pvS1I6TYOsq3xZpYT9uA4C1mnAmJ2iKvNQ" +
       "Ksc1I8nJ+jBGXsaOzwMAoFZnGB8z80dVGhQWSJO0nU6NtDLMbc1IA+gSMwun" +
       "cLJmQaKoa4uq4zTNRjhpDsMNyS2AqhWKQBROVobBBCWw0pqQlQL2ubHrgZlD" +
       "xg4jKnhOMlVH/msAqSWEtIelmM0MlUnE+s3xZ+iqN49HCQHglSFgCXPxSzcf" +
       "uqtl/i0J84kSMLtHDzCVj6inR5e9s7a38/4KZKPGMh0NjV8guXD/IXenO2dB" +
       "5K3KU8TNmLc5v+fnX3zyFXY9SuoGSJVq6tkM+NFy1cxYms7s7cxgNuUsOUBq" +
       "mZHsFfsDpBrmcc1gcnV3KuUwPkAqdbFUZYp3UFEKSKCKqmGuGSnTm1uUj4l5" +
       "ziKEVMNDtsDTSuRP/HPClH0OuLtCVWpohqmA8zJqq2MKU82RUdDuWIba446i" +
       "Zh1uZhQna6R0c1JxbFUx7XT+XTVtpnDmOEynnI7qTOkHtgaZMxZDd7P+Xwfl" +
       "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");
}
