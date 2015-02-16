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
      1166161668000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "4/teWHiHX1z/C3ZWZXipFgAA");
}
