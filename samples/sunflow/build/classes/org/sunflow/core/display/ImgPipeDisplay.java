package org.sunflow.core.display;
import java.io.IOException;
import javax.swing.JPanel;
import org.sunflow.core.Display;
import org.sunflow.image.Color;
@SuppressWarnings("serial") 
public class ImgPipeDisplay extends JPanel implements Display {
    private int ih;
    public ImgPipeDisplay() { super(); }
    public synchronized void imageBegin(int w, int h, int bucketSize) { ih =
                                                                          h;
                                                                        outputPacket(
                                                                          5,
                                                                          w,
                                                                          h,
                                                                          Float.
                                                                            floatToRawIntBits(
                                                                              1.0F),
                                                                          0);
                                                                        System.
                                                                          out.
                                                                          flush(
                                                                            );
    }
    public synchronized void imagePrepare(int x, int y,
                                          int w,
                                          int h,
                                          int id) {
        
    }
    public synchronized void imageUpdate(int x, int y,
                                         int w,
                                         int h,
                                         Color[] data) {
        int xl =
          x;
        int xh =
          x +
          w -
          1;
        int yl =
          ih -
          1 -
          (y +
             h -
             1);
        int yh =
          ih -
          1 -
          y;
        outputPacket(
          2,
          xl,
          xh,
          yl,
          yh);
        byte[] rgba =
          new byte[4 *
                     (yh -
                        yl +
                        1) *
                     (xh -
                        xl +
                        1)];
        for (int j =
               0,
               idx =
                 0;
             j <
               h;
             j++) {
            for (int i =
                   0;
                 i <
                   w;
                 i++,
                   idx +=
                     4) {
                int rgb =
                  data[(h -
                          j -
                          1) *
                         w +
                         i].
                  toNonLinear(
                    ).
                  toRGB(
                    );
                int cr =
                  rgb >>
                  16 &
                  255;
                int cg =
                  rgb >>
                  8 &
                  255;
                int cb =
                  rgb &
                  255;
                rgba[idx +
                       0] =
                  (byte)
                    (cr &
                       255);
                rgba[idx +
                       1] =
                  (byte)
                    (cg &
                       255);
                rgba[idx +
                       2] =
                  (byte)
                    (cb &
                       255);
                rgba[idx +
                       3] =
                  (byte)
                    255;
            }
        }
        try {
            System.
              out.
              write(
                rgba);
        }
        catch (IOException e) {
            e.
              printStackTrace(
                );
        }
    }
    public synchronized void imageFill(int x, int y,
                                       int w,
                                       int h,
                                       Color c) {
        int xl =
          x;
        int xh =
          x +
          w -
          1;
        int yl =
          ih -
          1 -
          (y +
             h -
             1);
        int yh =
          ih -
          1 -
          y;
        outputPacket(
          2,
          xl,
          xh,
          yl,
          yh);
        int rgb =
          c.
          toNonLinear(
            ).
          toRGB(
            );
        int cr =
          rgb >>
          16 &
          255;
        int cg =
          rgb >>
          8 &
          255;
        int cb =
          rgb &
          255;
        byte[] rgba =
          new byte[4 *
                     (yh -
                        yl +
                        1) *
                     (xh -
                        xl +
                        1)];
        for (int j =
               0,
               idx =
                 0;
             j <
               h;
             j++) {
            for (int i =
                   0;
                 i <
                   w;
                 i++,
                   idx +=
                     4) {
                rgba[idx +
                       0] =
                  (byte)
                    (cr &
                       255);
                rgba[idx +
                       1] =
                  (byte)
                    (cg &
                       255);
                rgba[idx +
                       2] =
                  (byte)
                    (cb &
                       255);
                rgba[idx +
                       3] =
                  (byte)
                    255;
            }
        }
        try {
            System.
              out.
              write(
                rgba);
        }
        catch (IOException e) {
            e.
              printStackTrace(
                );
        }
    }
    public synchronized void imageEnd() { outputPacket(
                                            4,
                                            0,
                                            0,
                                            0,
                                            0);
                                          System.
                                            out.flush(
                                                  );
    }
    private void outputPacket(int type, int d0, int d1,
                              int d2,
                              int d3) { outputInt32(
                                          type);
                                        outputInt32(
                                          d0);
                                        outputInt32(
                                          d1);
                                        outputInt32(
                                          d2);
                                        outputInt32(
                                          d3); }
    private void outputInt32(int i) { System.out.
                                        write(
                                          i >>
                                            24 &
                                            255);
                                      System.out.
                                        write(
                                          i >>
                                            16 &
                                            255);
                                      System.out.
                                        write(
                                          i >>
                                            8 &
                                            255);
                                      System.out.
                                        write(
                                          i &
                                            255);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "BrSZJXJ9FgAA");
}
