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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("H4sIAAAAAAAAALVYb2wcRxWfO/93nZzjNKkT4j9x3IKTcpuCWtG6KXWvTuxw" +
           "bo44DeJa6ox3586bzP7p7px9djCkqZCjSkTQOiVFxR9QqtKSNhUiKghVyhdo" +
           "q/KlCIH4QIv4QkXJh3ygVBQo783s3d7t3RmChKWdm51978178977vTe+eJW0" +
           "+B7Z4zp8Mc8dkWRFkTzOb0+KRZf5yYPp2zPU85mR4tT3j8DajD70SuKDj741" +
           "1x0nrVmymdq2I6gwHds/zHyHzzMjTRLh6jhnli9Id/o4nadaQZhcS5u+GE2T" +
           "GypYBRlOl1TQQAUNVNCkCtpYSAVMG5hdsFLIQW3hP0q+RmJp0urqqJ4gO6uF" +
           "uNSjViAmIy0ACe34fhSMksxFjwyWbVc21xh8bo+2+p1Hun/URBJZkjDtaVRH" +
           "ByUEbJIlXRazZpnnjxkGM7Jkk82YMc08k3JzSeqdJT2+mbepKHisfEi4WHCZ" +
           "J/cMT65LR9u8gi4cr2xezmTcKL215DjNg61bQ1uVhftxHQzsNEExL0d1VmJp" +
           "PmHahiADUY6yjcNfAAJgbbOYmHPKWzXbFBZIj/Idp3ZemxaeaeeBtMUpwC6C" +
           "bG8oFM/apfoJmmczgvRG6TLqE1B1yINAFkG2RMmkJPDS9oiXKvxz9YG7z560" +
           "J+y41NlgOkf924GpP8J0mOWYx2ydKcau3emn6dbXzsQJAeItEWJF8+pXr917" +
           "a/+VNxTNJ+rQHJo9znQxo1+Y3fj2jtTInU2oRrvr+CY6v8pyGf6Z4Mto0YXM" +
           "21qWiB+TpY9XDv/iy6deZO/HSeckadUdXrAgjjbpjuWanHkHmM08KpgxSTqY" +
           "baTk90nSBvO0aTO1eiiX85mYJM1cLrU68h2OKAci8IjaYG7aOac0d6mYk/Oi" +
           "Swjpgoc8DM8Oov7kryBf1OYci2lUp7ZpOxrELqOePqcx3dF8arkcvOYX7Bx3" +
           "FjTf0zXHy4fvi75gljZpgecz1GY8iaHl/j+EFtGS7oVYDA55RzTFOWTHhMMN" +
           "5s3oq4X7xq+9PPNWvBzywRkIshe2SQbbJNU2yXCb4WndczjPOo6FkIYuIbGY" +
           "3PBG1EB5FPxxAjIbCLpGpr9y8NiZoSYIJXehGQ4TSYfAxECtcd1Jhek/KUFO" +
           "hxjs/f5DK8kPn/+8ikGtMVbX5SZXzi88dvTre+MkXg26aCYsdSJ7BqGyDInD" +
           "0WSrJzex8t4Hl55edsK0q0LxAA1qOTGbh6IO8RydGYCPofjdg/TyzGvLw3HS" +
           "DBABsCgohDEgTn90j6qsHi0hJNrSAgbnHM+iHD+VYK1TzHnOQrgiI2UjDj0q" +
           "aNCBEQUluO7/6ZVnLn93z53xShxOVFS2aSZUVm8K/X/EYwzWf38+89S5qysP" +
           "SecDxa56GwzjmIIcB2/AiX3jjUd/9+47F34dDwNGkDbXM+ch9Ysg5JZwG4AA" +
           "DjCEfh1+0LYcw8yZdJYzDLx/JG6+7fJfznYrT3FYKTn61v8sIFzfdh859dYj" +
           "f+uXYmI6lqDQ9JBMncDmUPKY59FF1KP42K/6nnmdfg8QElDJN5eYBBoiTSPy" +
           "7JPSJSNy/HTk214cBt2ab0W50ivfmmHrkcYJsh8raUVi/f0Qnz39xw+lRTWp" +
           "UaeARPiz2sVnt6fueV/yhzGK3APFWtiBriPk/cyL1l/jQ60/j5O2LOnWg5bm" +
           "KOUFDJcslHG/1OdA21P1vbokq/ozWs7BHdH8qNg2mh0h3MEcqXHeqRJC0myC" +
           "M23HU07A0xcUAvmLXze7ON5YjBE5+Zxk2SnHYRw+GXhIkLhVXN8zGc+0oPLN" +
           "B6VZW+5598Sz772kIC/qhggxO7P6xMfJs6vximZnV02/UcmjGh5p5wZl58fw" +
           "F4PnX/ig0rigCl5PKqi6g+Wy67pozs711JJb7P/TpeWf/WB5RZnRU13rx6GV" +
           "fek3//xl8vwf3qxTfJqgj5NwpCL+s7X+6A/80d/AHwdwuAsPfxFn9zaWtg2e" +
           "gUDaQANpBwNp7YZH83noAqWIfTiMqRRMATrNOg5n1G68VS88g8FWgw22ygRb" +
           "tS1BbYWd8HVKiSzWj7EYTj9ViQQxOd8ClwKJRHQBgm6e2SI55RR89qU5xnip" +
           "aqMv+xr1l9KPF06vrhmHnrstHkDR/YK0Bm1/Nfb0VVXzKdlOhzn/xAs/fFW8" +
           "vecuFQ67G2dDlPH103/efuSeuWPXUcMHIgZFRb4wdfHNA7foT8ZJUxk6am4I" +
           "1Uyj1YDR6TG40thHqmCjv+xtjFCShGco8PZQ1Nuq2Nb3JxywW5jlpl6MVIRY" +
           "tXe31fPuOE6l+Nw69eQ4DscEXOCQJwOXM7j31Qvq5nnHNGrrjlx4uNbefYG9" +
           "++rai8NsfZvwNS+p/HXULuDgltS+H5ORGbjG/zsVNXgmAhUn/ncVT66j4jIO" +
           "gPobpIqHGSCCf1063gHPVKDj1PXqWAqN/oaJH8bH4+tYsYLDKejwrDLjlDPf" +
           "0A6wt6f2RlBSpm/dqwQgR2/NvyHU1Vl/eS3RftPag7+VjXD5etsBd8xcgfPK" +
           "Al4xb3U9ljOlGR2qnLvy5ywoWasJJJuaSG2/qUi/LcgNFaSAxsGskmgV6hQQ" +
           "4fScW7J1l7x/Jf0FgO7Ks5+03YIYM+C+B5hLKpATW+LKt6r+GMFR/ienBGQF" +
           "9b+cGf3S2sEHTl674zmJii06p0tLKKU9TdpU118Gw50NpZVktU6MfLTxlY6b" +
           "SwhfdR+I6DZQv2set1wh+9yln9z047ufX3tH9u3/BnPoxX5iEwAA");
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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
