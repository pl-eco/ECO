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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "bn7TP173owdenH9XXGT/A9o2JpvJFgAA");
}
