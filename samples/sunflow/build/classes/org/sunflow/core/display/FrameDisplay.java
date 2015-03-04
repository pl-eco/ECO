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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "4skz8+OOH217ef5D8YL7DzaJ5j+WFQAA");
}
