package org.sunflow.core.display;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Display;
import org.sunflow.image.Color;
import org.sunflow.system.Timer;
@SuppressWarnings("serial") 
public class FastDisplay extends JPanel implements Display {
    private JFrame frame;
    private BufferedImage image;
    private int[] pixels;
    private Timer t;
    private float seconds;
    private int frames;
    public FastDisplay() { super();
                           image = null;
                           frame = null;
                           t = new Timer();
                           frames = 0;
                           seconds = 0; }
    public synchronized void imageBegin(int w, int h, int bucketSize) { if (frame !=
                                                                              null &&
                                                                              image !=
                                                                              null &&
                                                                              w ==
                                                                              image.
                                                                              getWidth(
                                                                                ) &&
                                                                              h ==
                                                                              image.
                                                                              getHeight(
                                                                                )) {
                                                                            frame.
                                                                              setSize(
                                                                                w,
                                                                                h);
                                                                        }
                                                                        else {
                                                                            pixels =
                                                                              (new int[w *
                                                                                         h]);
                                                                            image =
                                                                              new BufferedImage(
                                                                                w,
                                                                                h,
                                                                                BufferedImage.
                                                                                  TYPE_INT_ARGB);
                                                                            if (frame ==
                                                                                  null) {
                                                                                setPreferredSize(
                                                                                  new Dimension(
                                                                                    w,
                                                                                    h));
                                                                                frame =
                                                                                  new JFrame(
                                                                                    "Sunflow v" +
                                                                                    SunflowAPI.
                                                                                      VERSION);
                                                                                frame.
                                                                                  setDefaultCloseOperation(
                                                                                    JFrame.
                                                                                      EXIT_ON_CLOSE);
                                                                                frame.
                                                                                  addKeyListener(
                                                                                    new KeyAdapter(
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
                                                                                frame.
                                                                                  setContentPane(
                                                                                    this);
                                                                                frame.
                                                                                  pack(
                                                                                    );
                                                                                frame.
                                                                                  setLocationRelativeTo(
                                                                                    null);
                                                                                frame.
                                                                                  setVisible(
                                                                                    true);
                                                                            }
                                                                        }
                                                                        t.
                                                                          start(
                                                                            );
    }
    public void imagePrepare(int x, int y,
                             int w,
                             int h,
                             int id) {  }
    public void imageUpdate(int x, int y,
                            int w,
                            int h,
                            Color[] data) {
        int iw =
          image.
          getWidth(
            );
        int off =
          x +
          iw *
          y;
        iw -=
          w;
        for (int j =
               0,
               index =
                 0;
             j <
               h;
             j++,
               off +=
                 iw)
            for (int i =
                   0;
                 i <
                   w;
                 i++,
                   index++,
                   off++)
                pixels[off] =
                  -16777216 |
                    data[index].
                    toRGB(
                      );
    }
    public void imageFill(int x, int y, int w,
                          int h,
                          Color c) { int iw =
                                       image.
                                       getWidth(
                                         );
                                     int off =
                                       x +
                                       iw *
                                       y;
                                     iw -=
                                       w;
                                     int rgb =
                                       -16777216 |
                                       c.
                                       toRGB(
                                         );
                                     for (int j =
                                            0,
                                            index =
                                              0;
                                          j <
                                            h;
                                          j++,
                                            off +=
                                              iw)
                                         for (int i =
                                                0;
                                              i <
                                                w;
                                              i++,
                                                index++,
                                                off++)
                                             pixels[off] =
                                               rgb;
    }
    public synchronized void imageEnd() {
        image.
          setRGB(
            0,
            0,
            image.
              getWidth(
                ),
            image.
              getHeight(
                ),
            pixels,
            0,
            image.
              getWidth(
                ));
        repaint(
          );
        t.
          end(
            );
        seconds +=
          t.
            seconds(
              );
        frames++;
        if (seconds >
              1) {
            frame.
              setTitle(
                String.
                  format(
                    "Sunflow v%s - %.2f fps",
                    SunflowAPI.
                      VERSION,
                    frames /
                      seconds));
            frames =
              0;
            seconds =
              0;
        }
    }
    public synchronized void paint(Graphics g) {
        if (image ==
              null)
            return;
        g.
          drawImage(
            image,
            0,
            0,
            null);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1415304895000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "1NrV2hgAAA==");
}
