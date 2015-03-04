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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "3eI2nwWfBvy8iXT0H8gqOgNKFwAA");
}
