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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
