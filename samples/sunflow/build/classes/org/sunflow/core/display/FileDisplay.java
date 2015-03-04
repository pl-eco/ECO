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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
