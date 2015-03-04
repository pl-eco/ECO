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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
