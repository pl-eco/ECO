package org.sunflow.system;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import org.sunflow.core.Display;
import org.sunflow.image.Bitmap;
import org.sunflow.image.Color;
@SuppressWarnings("serial") 
public class ImagePanel extends JPanel implements Display {
    private static final int[] BORDERS = { Color.RED.toRGB(), Color.GREEN.
      toRGB(
        ),
    Color.
      BLUE.
      toRGB(
        ),
    Color.
      YELLOW.
      toRGB(
        ),
    Color.
      CYAN.
      toRGB(
        ),
    Color.
      MAGENTA.
      toRGB(
        ) };
    private BufferedImage image;
    private float xo;
    private float yo;
    private float w;
    private float h;
    private long repaintCounter;
    private class ScrollZoomListener extends MouseInputAdapter implements MouseWheelListener {
        int mx;
        int my;
        boolean dragging;
        boolean zooming;
        public void mousePressed(MouseEvent e) { mx = e.getX();
                                                 my = e.getY();
                                                 switch (e.getButton()) {
                                                     case MouseEvent.
                                                            BUTTON1:
                                                         dragging =
                                                           true;
                                                         zooming =
                                                           false;
                                                         break;
                                                     case MouseEvent.
                                                            BUTTON2:
                                                         {
                                                             dragging =
                                                               (zooming =
                                                                  false);
                                                             if ((e.
                                                                    getModifiersEx(
                                                                      ) &
                                                                    InputEvent.
                                                                      CTRL_DOWN_MASK) ==
                                                                   InputEvent.
                                                                     CTRL_DOWN_MASK)
                                                                 fit(
                                                                   );
                                                             else
                                                                 reset(
                                                                   );
                                                             break;
                                                         }
                                                     case MouseEvent.
                                                            BUTTON3:
                                                         zooming =
                                                           true;
                                                         dragging =
                                                           false;
                                                         break;
                                                     default:
                                                         return;
                                                 }
                                                 repaint(
                                                   );
        }
        public void mouseDragged(MouseEvent e) { int mx2 =
                                                   e.
                                                   getX(
                                                     );
                                                 int my2 =
                                                   e.
                                                   getY(
                                                     );
                                                 if (dragging)
                                                     drag(
                                                       mx2 -
                                                         mx,
                                                       my2 -
                                                         my);
                                                 if (zooming)
                                                     zoom(
                                                       mx2 -
                                                         mx,
                                                       my2 -
                                                         my);
                                                 mx =
                                                   mx2;
                                                 my =
                                                   my2;
        }
        public void mouseReleased(MouseEvent e) {
            mouseDragged(
              e);
        }
        public void mouseWheelMoved(MouseWheelEvent e) {
            zoom(
              -20 *
                e.
                getWheelRotation(
                  ),
              0);
        }
        public ScrollZoomListener() { super(); }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1414698720000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "eBMAAA==");
    }
    public ImagePanel() { super();
                          setPreferredSize(new Dimension(
                                             640,
                                             480));
                          image = null;
                          xo = (yo = 0);
                          w = (h = 0);
                          ScrollZoomListener listener =
                            new ScrollZoomListener(
                            );
                          addMouseListener(listener);
                          addMouseMotionListener(
                            listener);
                          addMouseWheelListener(listener);
    }
    public void save(String filename) { Bitmap.save(
                                                 image,
                                                 filename);
    }
    private synchronized void drag(int dx, int dy) {
        xo +=
          dx;
        yo +=
          dy;
        repaint(
          );
    }
    private synchronized void zoom(int dx, int dy) {
        int a =
          Math.
          max(
            dx,
            dy);
        int b =
          Math.
          min(
            dx,
            dy);
        if (Math.
              abs(
                b) >
              Math.
              abs(
                a))
            a =
              b;
        if (a ==
              0)
            return;
        float cx =
          getWidth(
            ) *
          0.5F;
        float cy =
          getHeight(
            ) *
          0.5F;
        float x =
          xo +
          (getWidth(
             ) -
             w) *
          0.5F;
        float y =
          yo +
          (getHeight(
             ) -
             h) *
          0.5F;
        float sx =
          cx -
          x;
        float sy =
          cy -
          y;
        if (w +
              a >
              100) {
            h =
              (w +
                 a) *
                h /
                w;
            sx =
              (w +
                 a) *
                sx /
                w;
            sy =
              (w +
                 a) *
                sy /
                w;
            w =
              w +
                a;
        }
        float x2 =
          cx -
          sx;
        float y2 =
          cy -
          sy;
        xo =
          x2 -
            (getWidth(
               ) -
               w) *
            0.5F;
        yo =
          y2 -
            (getHeight(
               ) -
               h) *
            0.5F;
        repaint(
          );
    }
    public synchronized void reset() { xo = (yo =
                                               0);
                                       if (image !=
                                             null) {
                                           w =
                                             image.
                                               getWidth(
                                                 );
                                           h =
                                             image.
                                               getHeight(
                                                 );
                                       }
                                       repaint();
    }
    public synchronized void fit() { xo = (yo = 0);
                                     if (image !=
                                           null) {
                                         float wx =
                                           Math.
                                           max(
                                             getWidth(
                                               ) -
                                               10,
                                             100);
                                         float hx =
                                           wx *
                                           image.
                                           getHeight(
                                             ) /
                                           image.
                                           getWidth(
                                             );
                                         float hy =
                                           Math.
                                           max(
                                             getHeight(
                                               ) -
                                               10,
                                             100);
                                         float wy =
                                           hy *
                                           image.
                                           getWidth(
                                             ) /
                                           image.
                                           getHeight(
                                             );
                                         if (hx >
                                               hy) {
                                             w =
                                               wy;
                                             h =
                                               hy;
                                         }
                                         else {
                                             w =
                                               wx;
                                             h =
                                               hx;
                                         }
                                         repaint(
                                           );
                                     } }
    public synchronized void imageBegin(int w, int h,
                                        int bucketSize) {
        if (image !=
              null &&
              w ==
              image.
              getWidth(
                ) &&
              h ==
              image.
              getHeight(
                )) {
            for (int y =
                   0;
                 y <
                   h;
                 y++) {
                for (int x =
                       0;
                     x <
                       w;
                     x++) {
                    int rgb =
                      image.
                      getRGB(
                        x,
                        y);
                    image.
                      setRGB(
                        x,
                        y,
                        ((rgb &
                            16711422) >>>
                           1) +
                          ((rgb &
                              16579836) >>>
                             2));
                }
            }
        }
        else {
            image =
              new BufferedImage(
                w,
                h,
                BufferedImage.
                  TYPE_INT_RGB);
            this.
              w =
              w;
            this.
              h =
              h;
            xo =
              (yo =
                 0);
        }
        repaintCounter =
          System.
            nanoTime(
              );
        repaint(
          );
    }
    public synchronized void imagePrepare(int x, int y,
                                          int w,
                                          int h,
                                          int id) {
        int border =
          BORDERS[id %
                    BORDERS.
                      length];
        for (int by =
               0;
             by <
               h;
             by++) {
            for (int bx =
                   0;
                 bx <
                   w;
                 bx++) {
                if (bx ==
                      0 ||
                      bx ==
                      w -
                      1) {
                    if (5 *
                          by <
                          h ||
                          5 *
                          (h -
                             by -
                             1) <
                          h)
                        image.
                          setRGB(
                            x +
                              bx,
                            y +
                              by,
                            border);
                }
                else
                    if (by ==
                          0 ||
                          by ==
                          h -
                          1) {
                        if (5 *
                              bx <
                              w ||
                              5 *
                              (w -
                                 bx -
                                 1) <
                              w)
                            image.
                              setRGB(
                                x +
                                  bx,
                                y +
                                  by,
                                border);
                    }
            }
        }
        repaint(
          );
    }
    public synchronized void imageUpdate(int x, int y,
                                         int w,
                                         int h,
                                         Color[] data) {
        if (image ==
              null ||
              data ==
              null)
            return;
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
                image.
                  setRGB(
                    x +
                      i,
                    y +
                      j,
                    data[index].
                      copy(
                        ).
                      toNonLinear(
                        ).
                      toRGB(
                        ));
        repaint(
          );
    }
    public synchronized void imageFill(int x, int y,
                                       int w,
                                       int h,
                                       Color c) {
        if (image ==
              null ||
              c ==
              null)
            return;
        int rgb =
          c.
          copy(
            ).
          toNonLinear(
            ).
          toRGB(
            );
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
                image.
                  setRGB(
                    x +
                      i,
                    y +
                      j,
                    rgb);
        fastRepaint(
          );
    }
    public void imageEnd() { repaint(); }
    private void fastRepaint() { long t = System.
                                   nanoTime(
                                     );
                                 if (repaintCounter +
                                       125000000 <
                                       t) { repaintCounter =
                                              t;
                                            repaint(
                                              ); }
    }
    public synchronized void paintComponent(Graphics g) {
        super.
          paintComponent(
            g);
        if (image ==
              null)
            return;
        int x =
          (int)
            Math.
            round(
              xo +
                (getWidth(
                   ) -
                   w) *
                0.5F);
        int y =
          (int)
            Math.
            round(
              yo +
                (getHeight(
                   ) -
                   h) *
                0.5F);
        int iw =
          (int)
            Math.
            round(
              w);
        int ih =
          (int)
            Math.
            round(
              h);
        int x0 =
          x -
          1;
        int y0 =
          y -
          1;
        int x1 =
          x +
          iw +
          1;
        int y1 =
          y +
          ih +
          1;
        g.
          setColor(
            java.awt.Color.
              WHITE);
        g.
          drawLine(
            x0,
            y0,
            x1,
            y0);
        g.
          drawLine(
            x1,
            y0,
            x1,
            y1);
        g.
          drawLine(
            x1,
            y1,
            x0,
            y1);
        g.
          drawLine(
            x0,
            y1,
            x0,
            y0);
        g.
          drawImage(
            image,
            x,
            y,
            iw,
            ih,
            this);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1414698720000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
