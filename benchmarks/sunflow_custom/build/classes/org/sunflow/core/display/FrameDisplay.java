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
          1414698334000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "tf8BWyJnzKwNAAA=");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1414698334000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
