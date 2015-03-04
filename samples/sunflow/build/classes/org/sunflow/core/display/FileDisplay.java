package org.sunflow.core.display;
import org.sunflow.core.Display;
import org.sunflow.image.Bitmap;
import org.sunflow.image.Color;
public class FileDisplay implements Display {
    private Bitmap bitmap;
    private String filename;
    public FileDisplay(boolean saveImage) { super();
                                            bitmap = null;
                                            filename = saveImage ? "output.png"
                                                         : null; }
    public FileDisplay(String filename) { super();
                                          bitmap = null;
                                          this.filename = filename == null
                                                            ? "output.png"
                                                            : filename; }
    public void imageBegin(int w, int h, int bucketSize) { if (bitmap == null ||
                                                                 bitmap.
                                                                 getWidth(
                                                                   ) !=
                                                                 w ||
                                                                 bitmap.
                                                                 getHeight(
                                                                   ) !=
                                                                 h) bitmap =
                                                                      new Bitmap(
                                                                        w,
                                                                        h,
                                                                        filename ==
                                                                          null ||
                                                                          filename.
                                                                          endsWith(
                                                                            ".hdr"));
    }
    public void imagePrepare(int x, int y,
                             int w,
                             int h,
                             int id) {  }
    public void imageUpdate(int x, int y,
                            int w,
                            int h,
                            Color[] data) {
        for (int j =
               0,
               index =
                 0;
             j <
               h;
             j++)
            for (int i =
                   0;
                 i <
                   w;
                 i++,
                   index++)
                bitmap.
                  setPixel(
                    x +
                      i,
                    bitmap.
                      getHeight(
                        ) -
                      1 -
                      (y +
                         j),
                    data[index]);
    }
    public void imageFill(int x, int y, int w,
                          int h,
                          Color c) { Color cg =
                                       c;
                                     for (int j =
                                            0;
                                          j <
                                            h;
                                          j++)
                                         for (int i =
                                                0;
                                              i <
                                                w;
                                              i++)
                                             bitmap.
                                               setPixel(
                                                 x +
                                                   i,
                                                 bitmap.
                                                   getHeight(
                                                     ) -
                                                   1 -
                                                   (y +
                                                      j),
                                                 cg);
    }
    public void imageEnd() { if (filename !=
                                   null) bitmap.
                                           save(
                                             filename);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "hxrO2VzceuZ/3P6ju19Yek9c4v4DT7HbBJYVAAA=");
}
