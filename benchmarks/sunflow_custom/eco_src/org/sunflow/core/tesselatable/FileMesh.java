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
                                                                              close();
                                                                            UI.
                                                                              printInfo(
                                                                                Module.
                                                                                  GEOM,
                                                                                "RA3 -   * Creating mesh ...");
                                                                            return this.
                                                                              generate(
                                                                                tris,
                                                                                verts,
                                                                                smoothNormals);
                                                                        }
                                                                        catch (FileNotFoundException e) {
                                                                            e.
                                                                              printStackTrace();
                                                                            UI.
                                                                              printError(
                                                                                Module.
                                                                                  GEOM,
                                                                                "Unable to read mesh file \"%s\" - file not found",
                                                                                filename);
                                                                        }
                                                                        catch (IOException e) {
                                                                            e.
                                                                              printStackTrace();
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
                                                               readLine()) !=
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
                                                     close();
                                                   UI.
                                                     printInfo(
                                                       Module.
                                                         GEOM,
                                                       "OBJ -   * Creating mesh ...");
                                                   return this.
                                                     generate(
                                                       tris.
                                                         trim(),
                                                       verts.
                                                         trim(),
                                                       smoothNormals);
                                               }
                                               catch (FileNotFoundException e) {
                                                   e.
                                                     printStackTrace();
                                                   UI.
                                                     printError(
                                                       Module.
                                                         GEOM,
                                                       "Unable to read mesh file \"%s\" - file not found",
                                                       filename);
                                               }
                                               catch (NumberFormatException e) {
                                                   e.
                                                     printStackTrace();
                                                   UI.
                                                     printError(
                                                       Module.
                                                         GEOM,
                                                       "Unable to read mesh file \"%s\" - syntax error at line %d",
                                                       lineNumber);
                                               }
                                               catch (IOException e) {
                                                   e.
                                                     printStackTrace();
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
                                                         this.
                                                         getLittleEndianInt(
                                                           stream.
                                                             readInt());
                                                       UI.
                                                         printInfo(
                                                           Module.
                                                             GEOM,
                                                           "STL -   * Reading %d triangles ...",
                                                           numTris);
                                                       long filesize =
                                                         new File(
                                                         filename).
                                                         length();
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
                                                             readInt();
                                                           stream.
                                                             readInt();
                                                           stream.
                                                             readInt();
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
                                                                 this.
                                                                   getLittleEndianFloat(
                                                                     stream.
                                                                       readInt());
                                                               verts[index +
                                                                       1] =
                                                                 this.
                                                                   getLittleEndianFloat(
                                                                     stream.
                                                                       readInt());
                                                               verts[index +
                                                                       2] =
                                                                 this.
                                                                   getLittleEndianFloat(
                                                                     stream.
                                                                       readInt());
                                                           }
                                                           stream.
                                                             readShort();
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
                                                         close();
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
                                                       return this.
                                                         generate(
                                                           tris,
                                                           verts,
                                                           false);
                                                   }
                                                   catch (FileNotFoundException e) {
                                                       e.
                                                         printStackTrace();
                                                       UI.
                                                         printError(
                                                           Module.
                                                             GEOM,
                                                           "Unable to read mesh file \"%s\" - file not found",
                                                           filename);
                                                   }
                                                   catch (IOException e) {
                                                       e.
                                                         printStackTrace();
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
                  normalize();
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
            this.
              getLittleEndianInt(
                i));
    }
    public FileMesh() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170211998000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVZe2wUxxkfn98PYmPAEAIYm1fA+I6HQcWOBMbYieEMjs0j" +
       "MaFmvTd3Xtjb3ezO\n2YeDSNIohoCSQtuoTRUIaZB4NBTatKKVUgJK0qShUU" +
       "OkgkoV2oDaIiWpGlVKqdo/+n0zu7d7e2eD\nQLG0c7s7M99j5vf9vm/Wr39B" +
       "8i2TTJGtINthUCvY0t0pmRaNtKiSZa2HV73yu/nFnUfXaHqA5IRJ\nQIkwUh" +
       "6WrVBEYlJIiYTaVzUlTVJn6OqOmKqzIE2y4DZ1iS1vdXhJhsBNh85UPn0krz" +
       "pA8sOkXNI0\nnUlM0bVWlcYtRirC26QBKZRgihoKKxZrCpMxVEvEW3TNYpLG" +
       "rMfJLpIbJgWGjDIZqQk7ykOgPGRI\nphQPcfWhTq4WJIwzKZMUjUaaU+pg5v" +
       "z0mWC2Pa8rczQIKcLOjeAOtwC8np7yWnib4aqRe2zj0p2H\nj+eS8h5Srmjd" +
       "KEwGTxjo6yFlcRrvo6bVHInQSA8Zq1Ea6aamIqnKENfaQyotJaZJLGFSq4ta" +
       "ujqA\nAyuthEFNrtN5GSZlMvpkJmSmm6k1iipUjThP+VFVioHbVa7bwt02fA" +
       "8OlihgmBmVZOpMyduuaLDj\n1f4ZKR9nroEBMLUwTlm/nlKVp0nwglSKvVQl" +
       "LRbqZqaixWBovp4ALYxMHlEorrUhydulGO1lZJJ/\nXKfoglHFfCFwCiMT/M" +
       "O4JNilyb5d8uxPXdVXe469fHYFx3ZehMoq2l8Ck6b5JnXRKDWpJlMx8WYi\n" +
       "+L32RxNTAoTA4Am+wWJM86wzG8I33qoWY+7LMmZd3zYqs1557YHqrice1Eku" +
       "mlFk6JaCm5/mOQ+H\nTrunKWlA1FalJGJn0Ok81/WbR586QT8LkJJ2UiDrai" +
       "IOOBor63FDUan5INWoKTEaaSfFVIu08P52\nUgj3YYC8eLsuGrUoayd5Kn9V" +
       "oPNnWKIoiMAlKoZ7RYvqzr0hsX5+nzQIIYVwkXq4aoj447+MLAuG\nrIQWVf" +
       "XBkGXKId2MpZ5l3aQhRi2LqkAsfSoNtYGqDmr1BxFCBiMbQv16nIYkWdIUTQ" +
       "/FFAhaWa+P\n0AH8vVPBSbS6cjAnB2nQH84qRMJDuhqhZq989PoHO1vXPLdH" +
       "QAXhbfvLyGzQF7T1BVFf0Ksv6Ogj\nOTlczXjUK/YMVnw7xC6wXNnc7i2rt+" +
       "6pzQWwGIN5sFw4tBY8s41plfUWN8DbORfKgLJJP9q8O3jz\n6HKBstDIPJx1" +
       "dulHJy8c/lfZvAAJZCdJdBJougTFdCKzpshvpj+sssn/x96ONy5d+OR+N8AY" +
       "mZkR\n95kzMW5r/dth6jKNABO64o/cW567iWw8ECB5QAZAgNx+4JZpfh1p8d" +
       "vkcCH6UhgmpVHdjEsqdjkE\nVsL6TX3QfcNxUoHNeAEZ3EifgZxGbz5TsODy" +
       "m6Xvco8dxi335LRuykT8jnVxsN6kFN5/8oPO7774\nxe7NHAQ2ChgkukSfqs" +
       "hJmDLbnQKBqwJ54B7N3KDF9YgSVRBuCKb/lc9a+IvPX6gQq67CG2fT5t9a\n" +
       "gPv+3pXkqQvf/Pc0LiZHxsThuuEOE96McyU3m6a0A+1IPv3x1Jfekw4CrwGX" +
       "WMoQ5fRAuGeEr2OQ\nL+9c3tb7+hZgUwuy54+A6ixpulfeeSJWm3j8t7/iVp" +
       "dK3nzv3YYOyWgSm8p1jwOlQWI3abSFvRMM\nbKtwCyb6o/chyeoHYQ3n1j5W" +
       "oZ77L6jtAbUy5FBrnQnUkUzbaXt0fuGV829Xbb2YSwJtpETVpUib\nxPFPig" +
       "F4QBbAOklj+QpuRsVgEbZ8XQi3drK9SknPUwCMmzty+LdhJeBGTm/f/GPhD9" +
       "Yd5Ks0YuBn\nSYQ+OUNnNxy6+SG7yuW4EYiza5KZlArVkzv3G5cGxhacfiUe" +
       "IIU9pEK267uNkprAYOiBcsRyij6o\nAdP600sLkUebUgwzxR/9HrX+2HepHO" +
       "5xNN6X+ZBRhqs9A65aGxm1fmTkEH6znE+ZwdvZqQguNExl\nQMKajxRhBkWV" +
       "fMhEKHc5NtCNoKiQBLtguxCbFWKPl4yIhcZ0K+fZljoWZ7OyHZtmRsZYcV1n" +
       "/Ws5\n+1mAn0neg4CpxKGgGOCMeX249tfvb3hlt8gyo8AsbVav/OSf/7I999" +
       "vn+8Q8P5Z8gw9MO/K3N653\njRe0JSrPGRnFn3eOqD75ZpUbGJ01o2ngo9+p" +
       "q3l9V9dV26LK9BqqFc4Zf9/xNp3zwPOfZkn0hX26\nrlJJ823R6lG2KJkFEH" +
       "h/vzd0cxwwTM2oJNZ7Kgl0cOpIxSx3bvcjX5YNS+9sCdgM2sWAT3SjXqUD\n" +
       "VPUoLEBJabVFBy/f3djce/zHZ9jFukaxTPNG3nD/xHmNh4eqG0/tu4OKotrn" +
       "m1/02IH7Hs7tV94P\n8BOGCPWMk0n6pKb0AC8BexKmtj4tzKenAqgU92wRXL" +
       "PsAJrlDyC+89hs8uWtnPSNnOTdyDgUx8EO\nCaI72cAlxEZJetuxAeDfE6Ns" +
       "k26qkZV6QotYjuApGYJ5P/DGSj3pQpLeijWcDMIftqavwEy46uwV\nqLvtFf" +
       "A6kRilbxAbE4FpQztFhdMy0J8KX6wmXO+sO/SOE2Q3XMdt746P6F2zz4NcLj" +
       "EXqz4PR/JK\nB9F0fP+qcV3LNj/DUV8M53fJWuvCLqBE8C4HMD5r5EhKCeuV" +
       "52w588/zZ+kcXkwUKRbkr2YzluUs\n65nzpXSCdlyOHuK1cF6fZAmI+z8CZJ" +
       "7x047ufAXuMcTKhe3fhxnJVewvL36wNxpOVtmbSXUBBslW\n0SRVUB7UspD8" +
       "YuKoOIzNs7YGlBqwyxgbDePcxNii6hpFBnT6xBFK0YOpryWcHrPZt9XgLj17" +
       "K8h+\nf5S+l7B5EXyR0RBhNyChOnsx3Ro3GC9/h3458ecPHD10FTnUSML5t4" +
       "QDY9HiBYsXwvRKABJ+RQsq\nkWBYlyW1fdWr50s/PpBYulqw7hjPgPZVO0+v" +
       "Lit6dd8wZz8bUcWezwX2c+GAZK51kxb+HGSk92s5\nPDcuXLps/uKG+oYGBr" +
       "WTqQiW+lYmeqDi0qVb4edYFvzg/fMCPNi+kALOa5nAwcf92HxnVCS8disk\n" +
       "nBql76fYnMTmh8IKbF9O39tFfOYhbH72ta58w9L6JctgcQfgxMt1dnIbnx7F" +
       "/jPY7IIyNGZ/BnIi\nak4G9xoO98LpVIEoFNpdEn7yblJME1xXbBK+ctspxs" +
       "cQWfIF1hMU+C2VL2BYlXdYt/ht7mznas6P\nslbvYXMWOCthQBTygOp03X/r" +
       "bnLQXLiu2e5fu+0clOOWj8N81O9Hsf4iNr+DcxLUEWGFMZW2Qpkg\nae0a/x" +
       "gw7Hry4d14gmpv2J7cuHNP/jiKJ3/C5hKQvs+TNuQU7Dvi+nL5dn3Bo5gTUc" +
       "jlkzI+5YvP\nz3L4yhOPfRX+w39EWnU+EZcC8UYTquo9PHruCwyTRhVufak4" +
       "SgruuZatxPdGOSNl3kdu8qdi7l/h\nrOifC7SLP95hNxgp9QyDQ4t95x30GS" +
       "R0GIS3nxtZTqLiQJ1MWzFcpRlp1Qv/T4tTqyfE/1p65UdO\nbp6e3Ld+Py+F" +
       "IGNKQ0MopgRSk/jMlqr3a0aU5sj6iJw+tfHNnyxzzjNpH+AyoLlQ9I6MAE7W" +
       "xv8B\nDFHF0fUaAAA=");
}
