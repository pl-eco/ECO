package org.sunflow.core.display;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Display;
import org.sunflow.image.Color;
import org.sunflow.system.ImagePanel;
public class FrameDisplay implements Display {
    private String filename;
    private RenderFrame frame;
    public FrameDisplay() { this(null); }
    public FrameDisplay(String filename) { super();
                                           this.filename = filename;
                                           frame = null; }
    public void imageBegin(int w, int h, int bucketSize) { if (frame == null) {
                                                               frame =
                                                                 new RenderFrame(
                                                                   );
                                                               frame.
                                                                 imagePanel.
                                                                 imageBegin(
                                                                   w,
                                                                   h,
                                                                   bucketSize);
                                                               Dimension screenRes =
                                                                 Toolkit.
                                                                 getDefaultToolkit(
                                                                   ).
                                                                 getScreenSize(
                                                                   );
                                                               boolean needFit =
                                                                 false;
                                                               if (w >=
                                                                     screenRes.
                                                                     getWidth(
                                                                       ) -
                                                                     200 ||
                                                                     h >=
                                                                     screenRes.
                                                                     getHeight(
                                                                       ) -
                                                                     200) {
                                                                   frame.
                                                                     imagePanel.
                                                                     setPreferredSize(
                                                                       new Dimension(
                                                                         (int)
                                                                           screenRes.
                                                                           getWidth(
                                                                             ) -
                                                                           200,
                                                                         (int)
                                                                           screenRes.
                                                                           getHeight(
                                                                             ) -
                                                                           200));
                                                                   needFit =
                                                                     true;
                                                               }
                                                               else
                                                                   frame.
                                                                     imagePanel.
                                                                     setPreferredSize(
                                                                       new Dimension(
                                                                         w,
                                                                         h));
                                                               frame.
                                                                 pack(
                                                                   );
                                                               frame.
                                                                 setLocationRelativeTo(
                                                                   null);
                                                               frame.
                                                                 setVisible(
                                                                   true);
                                                               if (needFit)
                                                                   frame.
                                                                     imagePanel.
                                                                     fit(
                                                                       );
                                                           }
                                                           else
                                                               frame.
                                                                 imagePanel.
                                                                 imageBegin(
                                                                   w,
                                                                   h,
                                                                   bucketSize);
    }
    public void imagePrepare(int x, int y,
                             int w,
                             int h,
                             int id) { frame.
                                         imagePanel.
                                         imagePrepare(
                                           x,
                                           y,
                                           w,
                                           h,
                                           id);
    }
    public void imageUpdate(int x, int y,
                            int w,
                            int h,
                            Color[] data) {
        frame.
          imagePanel.
          imageUpdate(
            x,
            y,
            w,
            h,
            data);
    }
    public void imageFill(int x, int y, int w,
                          int h,
                          Color c) { frame.
                                       imagePanel.
                                       imageFill(
                                         x,
                                         y,
                                         w,
                                         h,
                                         c);
    }
    public void imageEnd() { frame.imagePanel.
                               imageEnd(
                                 );
                             if (filename !=
                                   null) frame.
                                           imagePanel.
                                           save(
                                             filename);
    }
    @SuppressWarnings("serial") 
    private static class RenderFrame extends JFrame {
        ImagePanel imagePanel;
        RenderFrame() { super("Sunflow v" +
                              SunflowAPI.
                                VERSION);
                        setDefaultCloseOperation(
                          EXIT_ON_CLOSE);
                        addKeyListener(new KeyAdapter(
                                         ) {
                                           public void keyPressed(KeyEvent e) {
                                               if (e.
                                                     getKeyCode(
                                                       ) ==
                                                     KeyEvent.
                                                       VK_ESCAPE)
                                                   System.
                                                     exit(
                                                       0);
                                           }
                                       });
                        imagePanel = new ImagePanel(
                                       );
                        setContentPane(imagePanel);
                        pack(); }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "yUebL8zcEqj9D8P0aAG8DQAA");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
