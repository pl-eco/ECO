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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "yr793zaSjKaIEwAA");
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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "ET29tpodAAA=");
}
