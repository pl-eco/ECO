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
    final private static int[] BORDERS = { Color.RED.toRGB(), Color.GREEN.
      toRGB(),
    Color.
      BLUE.
      toRGB(),
    Color.
      YELLOW.
      toRGB(),
    Color.
      CYAN.
      toRGB(),
    Color.
      MAGENTA.
      toRGB() };
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
                                                                    getModifiersEx() &
                                                                    InputEvent.
                                                                      CTRL_DOWN_MASK) ==
                                                                   InputEvent.
                                                                     CTRL_DOWN_MASK)
                                                                 ImagePanel.this.
                                                                   fit();
                                                             else
                                                                 ImagePanel.this.
                                                                   reset();
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
                                                 ImagePanel.this.
                                                   repaint();
        }
        public void mouseDragged(MouseEvent e) {
            int mx2 =
              e.
              getX();
            int my2 =
              e.
              getY();
            if (dragging)
                ImagePanel.this.
                  drag(
                    mx2 -
                      mx,
                    my2 -
                      my);
            if (zooming)
                ImagePanel.this.
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
            this.
              mouseDragged(
                e);
        }
        public void mouseWheelMoved(MouseWheelEvent e) {
            ImagePanel.this.
              zoom(
                -20 *
                  e.
                  getWheelRotation(),
                0);
        }
        public ScrollZoomListener() { super();
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1414698720000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAALVYe2wcRxmfu/PbrvxI4jwaP+OW5tHbRCIVxK0S4zj1Jev6" +
           "8Cutk+COd+fOm+zu\nbHfnzmc3Kokq1aGllKggtYImEYrk9JlKBQWkUlK1hd" +
           "IIqUWiSJUaQJEACYqEkEoQ/ME3s7u3d3t3\nLmmEpR3P7nzP+b7vN9/cS5+g" +
           "asdGGxUnzhYs4sQHx5PYdog6qGPHmYBPM8o71fXJ5QMmjaKIjKKa\nylCzrD" +
           "iSihmWNFVK7O3P2WirRfWFtE5ZnORY/Ki+05O3X95ZIvDgmUttJ89XdUVRtY" +
           "yasWlShplG\nzSGdGA5DLfJRnMVShmm6JGsO65fRLcTMGIPUdBg2mfMQegTF" +
           "ZFRjKVwmQz2yr1wC5ZKFbWxIQr2U\nFGpBwiqbMKyZRB3IqwPObcWcYLbHN1" +
           "ZKDULq+OIUuCMsAK+781673pa4asUuTN11/NzzMdQ8jZo1\nc5wLU8ATBvqm" +
           "UZNBjFliOwOqStRp1GoSoo4TW8O6tii0TqM2R0ubmGVs4owRh+pZTtjmZCxi" +
           "C53+\nRxk1KdwnO6Mwauf3KKURXfXfqlM6ToPb7YHbrrv7+HdwsEEDw+wUVo" +
           "jPUnVMMyHiXWGOvI99B4AA\nWGsNwuZoXlWVieEDanNjqWMzLY0zWzPTQFpN" +
           "M6CFoQ0VhfK9trByDKfJDEPrwnRJdwmo6sVGcBaG\n1oTJhCSI0oZQlAris7" +
           "X901MXvv/GHpHbVSpRdG5/AzB1hpjGSIrYxFSIy3g9E/9O4oHMxihCQLwm\n" +
           "ROzSDNx2aVL+88+6XJpby9CMzh4lCptR7jvdNfbwvRTFuBl1FnU0Hvwiz0U5" +
           "JL2V/pwFVduel8gX\n4/7i5bGfP3DiBfKXKGpIoBqF6hkD8qhVoYal6cS+l5" +
           "jExoyoCVRPTHVQrCdQLcxlSHn362gq5RCW\nQFW6+FRDxTtsUQpE8C2qh7lm" +
           "pqg/tzCbE/OchRBqggcdhmcjcv/Ef4a2xyUnY6Z0Oi85tiJROx28\nLziMGF" +
           "LCgMAmsUn0OM8ciyFZmqMGkbCCTc2kUlqDWlXonSrJ8v83KC/HbWybj0Q46I" +
           "WLV4e8H6a6\nSuwZZfnae8eHDnzjlJsYPJk978AJUBP31MRdNfFATd+4YlNd" +
           "n6bU4ODFNxtFIkLham6BGyvY6WNQ\ns0DQtHn8yP4HT/XGIEms+SrYJk7aC6" +
           "55Zg0pdDAo7ITAQAWya90PDi3Fry/vdrNLqoy/Zbkb33/5\nyrl/NG2Jomh5" +
           "cOTuAjw3cDFJjqh50OsLl1M5+X97fOS1D698fEdQWAz1ldR7KSev195wYGyq" +
           "EBUQ\nMBB/fn1z7CCaOh1FVQACAHzCfsCUzrCOorrt9zGQ+1Iro8YUtQ2s8y" +
           "UfuBrYnE3ngy8iY1r4sNpN\nHh7IkIECPq8/WrP9t683viM89pG2ueAsGyfM" +
           "rdvWIA8mbELg+8fPJJ/+7idLh0QSeFnAUK1la1mo\n1Bzw3B7wQMXqgBo8SH" +
           "2TpkFVLaXhWZ3wbPpP8207fvTXb7W4267DFz9q2z5bQPB9/VfQiStf+2en\n" +
           "EBNR+IkR+BGQue6sCiQP2DZe4HbkTv6649lf4OcA0ABEHG2RCFxAwjUkNjIu" +
           "9nezGO8MrW3nQy/I\n3lYhrcuczzPK8RfSvZmHfvkTYXUjLjzoC+Mwgq1+N6" +
           "pC9ypQegR5QxFe8dU1Fh/beQjWhst3GDtz\nIOyLl+873KJf/jeonQa1Chye" +
           "zqgNKJIrCrVHXV370ZtvtT/4QQxF96EGnWJ1HxYFgOoh84gzBwCU\ns3bvEW" +
           "a0zNfxUewLEtZu8HYpV/BWBcZtrlz/+3gLEJTOzOy2C/J7o8+JXapY+WVOwJ" +
           "CcxTcmz1z/\nFbsq5AQlyLl7cqXoCm1TwPulD7OtNa+eNaKodhq1KF5jN4X1" +
           "DK+GaehDHL/bg+avaL24p3AP0P48\nxGwMl3+B2nDxB6gOc07N502hzOD7j5" +
           "rh6fAyoyOcGREkJrsFyyYx3m55UWIoavDUWVfYX9uaAed0\nVgDStcd6f/ru" +
           "5NklF8RXCGIR14zy9d/9/ljsqTdnXb5wpELEpzvP//G1a2OrXVBwG7pNJT1V" +
           "IY/b\n1ImtaLa4Az0raRDUb2/teemRsaueRW3FrckQtO9/WniLfOHuJ/9Q5k" +
           "SNQdvpYisfd/Bhj5vgOysW\nwq7SEHV6IeqsEKJRPgzwmCzw2XBIZfIGVa6H" +
           "p8tT2VVB5YSnsk61cToN7a8QsddyVSUA42cp1Qk2\nQ7ZM3qAt6+Dp9mzprm" +
           "DLYc+W2kXoTcAU/nowpPfICnpzpRke4fM7CrEoIuZr4VImkA/PQy5nicni\n" +
           "IzTjkINzhOh+W8TzqqNSay5yaun+vzc9ht8+EvWOhf0M1Xg3pmL06yhql0bE" +
           "TSRAm8eff/ES+2Dr\nLjc1t1QusjDjll3nFrt2XXziczRJXSHHwqJbs7d+NT" +
           "anvRsVlyUXvEouWcVM/cWQ1QD2ZGxzogi4\nuvNpwQsCxeHp9dKiN5wWQeiL" +
           "YwqbbGVmdU3JhU7oSHGE15eL8BCfCtFshfNdDHC/aTI4TxKOPbg2\nh0ujKk" +
           "s1NUhN+lkl4R+N4uVo6Ubc423EPWU3gg/WTTn86AoOL/HhhO/wXg4GROXfFg" +
           "MHT96MgxI8\nw56Dw/8nB59awcGn+fBNhm4RDo4RwDSnxMMnb8bDu+AZ8Twc" +
           "+bwedlZEpcDN763g5lk+PAP9pJFn\nHKHZEkef/V8dhQ6hrfTC6FvbseJNk/" +
           "cVJb8/ub+ZKPJHDx/+VP7Nv8T9KP+7RqOM6lIZXS9sfArm\nNZZNUprws9Ft" +
           "gyzx7wIYWWoJwIQ7EdYuu6QvMtRYQAqHjTcrJHoFTnwg4tOLlu/rJnE9jzvz" +
           "cDIV\nBidhWhk2oGKL8ROjcO/43WNTEZiLX/58wM24v/3NKPe/fKg798TEtw" +
           "WKVys6XlzkYhpkVOte//Kg\n3VNRmi/rffTqxanXX/myfyIVXQxLMniHu7pC" +
           "LsBBUf5eNmRYTNykFn+89od3L5+5GhVXw/8Cei/Z\norAVAAA=");
    }
    public ImagePanel() { super();
                          this.setPreferredSize(
                                 new Dimension(
                                   640,
                                   480));
                          image = null;
                          xo = (yo = 0);
                          w = (h = 0);
                          ScrollZoomListener listener =
                            new ScrollZoomListener(
                            );
                          this.addMouseListener(
                                 listener);
                          this.addMouseMotionListener(
                                 listener);
                          this.addMouseWheelListener(
                                 listener);
    }
    public void save(String filename) { Bitmap.
                                          save(
                                            image,
                                            filename);
    }
    private synchronized void drag(int dx,
                                   int dy) {
        xo +=
          dx;
        yo +=
          dy;
        this.
          repaint();
    }
    private synchronized void zoom(int dx,
                                   int dy) {
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
          this.
          getWidth() *
          0.5F;
        float cy =
          this.
          getHeight() *
          0.5F;
        float x =
          xo +
          (this.
             getWidth() -
             w) *
          0.5F;
        float y =
          yo +
          (this.
             getHeight() -
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
            (this.
               getWidth() -
               w) *
            0.5F;
        yo =
          y2 -
            (this.
               getHeight() -
               h) *
            0.5F;
        this.
          repaint();
    }
    public synchronized void reset() { xo =
                                         (yo =
                                            0);
                                       if (image !=
                                             null) {
                                           w =
                                             image.
                                               getWidth();
                                           h =
                                             image.
                                               getHeight();
                                       }
                                       this.
                                         repaint();
    }
    public synchronized void fit() { xo =
                                       (yo =
                                          0);
                                     if (image !=
                                           null) {
                                         float wx =
                                           Math.
                                           max(
                                             this.
                                               getWidth() -
                                               10,
                                             100);
                                         float hx =
                                           wx *
                                           image.
                                           getHeight() /
                                           image.
                                           getWidth();
                                         float hy =
                                           Math.
                                           max(
                                             this.
                                               getHeight() -
                                               10,
                                             100);
                                         float wy =
                                           hy *
                                           image.
                                           getWidth() /
                                           image.
                                           getHeight();
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
                                         this.
                                           repaint();
                                     } }
    public synchronized void imageBegin(int w,
                                        int h,
                                        int bucketSize) {
        if (image !=
              null &&
              w ==
              image.
              getWidth() &&
              h ==
              image.
              getHeight()) {
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
            nanoTime();
        this.
          repaint();
    }
    public synchronized void imagePrepare(int x,
                                          int y,
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
        this.
          repaint();
    }
    public synchronized void imageUpdate(int x,
                                         int y,
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
                      copy().
                      toNonLinear().
                      toRGB());
        this.
          repaint();
    }
    public synchronized void imageFill(int x,
                                       int y,
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
          copy().
          toNonLinear().
          toRGB();
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
        this.
          fastRepaint();
    }
    public void imageEnd() { this.repaint();
    }
    private void fastRepaint() { long t =
                                   System.
                                   nanoTime();
                                 if (repaintCounter +
                                       125000000 <
                                       t) {
                                     repaintCounter =
                                       t;
                                     this.
                                       repaint();
                                 } }
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
                (this.
                   getWidth() -
                   w) *
                0.5F);
        int y =
          (int)
            Math.
            round(
              yo +
                (this.
                   getHeight() -
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1414698720000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVaC2wcxRmeu/PbBtuXh52QxIlxSPO6s0NMYpsqOLYT7Fxi" +
       "YycBHFKz3ps7b7K3\nu+zO2WcT0SKqhJLSEhVQXwSokBLCU4UqbYE0KVBepQ" +
       "LahgoJSAWilQgVUAlSFVX9/9nd2729ByQm\nlnZud2fm///vf80/s374I1Js" +
       "6GSeaITYpEaNUNfQgKAbNNolC4axFV6NiM8Xlw8c2qSofuKLEL8U\nZaQ6Ih" +
       "rhqMCEsBQN93Z3pHSyXFPlybisshBNsdAuudWi1xdpzSJ49cGjwZsfKGrwk+" +
       "IIqRYURWUC\nk1SlR6YJg5GayC5hXAgnmSSHI5LBOiLkAqokE12qYjBBYcYN" +
       "5CYSiJASTUSajCyK2MzDwDysCbqQ\nCHP24QHOFijM0CkTJIVGO9PsYOaKzJ" +
       "kgtjVvMHs0ECnDzu0Ah0sAqBemUZtos6BqgcPbL9tz34MB\nUj1MqiVlCImJ" +
       "gIQBv2FSlaCJUaobndEojQ6TWoXS6BDVJUGWpjjXYRI0pLgisKROjUFqqPI4" +
       "Dgwa\nSY3qnKf9MkKqRMSkJ0Wm6mkdxSQqR+2n4pgsxAH2bAe2CXcDvgeAFR" +
       "IIpscEkdpTinZLCli8wTsj\njbFpEwyAqaUJysbUNKsiRYAXJGjaUhaUeHiI" +
       "6ZISh6HFahK4MDI3L1HUtSaIu4U4HWGk3jtuwOyC\nUeVcETiFkVneYZwSWG" +
       "mux0ou+yyf/dmth39+7Aru20VRKsoofwVMWuCZNEhjVKeKSM2JZ5KhO3uv\n" +
       "Tc7zEwKDZ3kGm2M6Fx/dFvnn7xrMMRflGNM/uouKbETccqBh8MaNKgmgGGWa" +
       "akho/AzkPBwGrJ6O\nlAZROztNETtDdufxwT9c+50j9EM/qeglJaIqJxPgR7" +
       "WimtAkmeobqUJ1gdFoLymnSrSL9/eSUriP\ngMubb/tjMYOyXlIk81clKn8G" +
       "FcWABKqoHO4lJaba95rAxvh9SiOElMJF2uCaQcw//stIcyhsJJWY\nrE6EDV" +
       "0Mq3rceZ40GE2EexNg2AFBoXIIPUdjJBIeUxM0LIiCIilqOC5BrIrqyigdx9" +
       "+zpJdCGYMT\nPh8mPW/wyuD3V6pylOoj4qH3Xt7Ts+l7t5qOgc5soWNkPrAJ" +
       "WWxCJpuQw4b4fJz6TGRnGgbUuhsC\nFFJZ1dKhnX3X39oYAI/QJopAJzi0EX" +
       "BYMvSIapcTxb084YngSvW/2LEvdObQOtOVwvmTbc7Zla89\n8sp9/65a5if+" +
       "3JkQsUEurkAyA5g+0xmuyRs7uej/67bNT5x85e1vOFHESFNWcGfPxOBs9FpB" +
       "V0Ua\nhXTnkH9gTnXgarL9gJ8UQcRDluPyQwJZ4OWREaQddsJDLKURUhlT9Y" +
       "QgY5edpSrYmK5OOG+4e9Tw\ne/TYSvTaWXANWm7Mf7F3lobtbNOd0NoeFDyh" +
       "nrmlpPnNpyuf52qxc2+1a3UbosyM5FrHWbbqlML7\nt3888KO7Ptq3g3uK5S" +
       "oMlrzkqCyJKZhyiTMFQliGNIKGbNqmJNSoFJOEUZmix31RvbjlV6d/UGOa\n" +
       "RoY3tmVXfDkB5/2c9eQ7r3zr8wWcjE/EJcSB4Qwz0cxwKHfqujCJcqRufmP+" +
       "T14Q7oEMB1nFkKYo\nTxSEIyNcj2Gu92W8DXn6WrBpBNor8rh+jgV7RNxzJN" +
       "6YvOGl33CpKwX3yu82w2ZB6zAtj83FqN06\nb/ReKRhjMG718S3X1cjH/wsU" +
       "h4GiCAul0a9DxkhlGNEaXVz61olnZ1//eoD4N5AKWRWiGwTu/6Qc\nHI8aY5" +
       "BsUtq6K7hv1UyUYcshE66EuZYCUq6nUhBuaf7w34DLvRM5I6MrDkde7r+HKy" +
       "Bv4OdY7Tx0\npo5tO3jmVfYOp+NEIM5elMrOpFAiOXPXnhyvLXn83oSflA6T" +
       "GtEq4rYLchL9fBhqDsOu7KDQy+jP\nrB/MxbIjnWHmeaPfxdYb+04Gh3scjf" +
       "dVnnCfg9peC9dsK9xnesPdR/jNOj6libdLzOAMMGAoKQLI\nVarp0jgssBCu" +
       "Bi/3UvBuff9gd8/gEKYKV63NwwPTz4N3dM8YbNtxC8/Q5VD+CcYWR2IouvHO" +
       "B6pe\nnN/2aWIj4pKdRz8+cYwu4W5aJhmgmU49nqMUcs35RDhCN78ZO8izbN" +
       "GoYJg68taQ2SViRuXHVXqh\nhjFU795U6FICipNxnpjf29v4zIvb7t1nLmYF" +
       "vDlj1oj47XdP7Q788MSoOc/rsp7BBxY88MET7w3O\nNBOfWcVenFVIuueYlS" +
       "wHUM0BLCrEgY9+bvmih28afMeSKJhZj/XAnuUfk8/SJZff/vccZURAsrYy\n" +
       "mOt8VjGAz+2a7Wfbsv3Mj/dd2CwFtyqRqRI3C69+bPq0VJqk3xzPn+uYlZQx" +
       "kKD+VRWK+d3uM2sV\nSQ2l9x7QmcoSTifzMyqVzdzuTqTf9uBDR9nry9tNbS" +
       "zLb1fvxGXt9001tD+2/xzqkwaPjbyka8cv\nuiowJr3o55sSM3FkbWYyJ3Vk" +
       "posKkCepK1szksZCjf/0YXNJgXWLFuiLYwNLabGI9jDNBzpuyL0u\n9yQ0xl" +
       "fSqV/XPXn5oYPvoJa1FBTVZTxdtDS3weQgxBxuzENS1Mrh3W9sGD0SU45EuQ" +
       "YqeCx34gQL\nXjl/40o2AVUzcOfh2uJblJr6NQNLtgtcTHq79zzeV1V2//69" +
       "nL6VqcpduxjruXRc0Le4/b/SFHvt\n6kub1zCy/eus8ttb2ppXXLp65eoWRt" +
       "YUrNZDVl5eYixkgh6nbKH9BuXc7STum2C7la1b1Ii1VpAg\n9/oLnTDD+Hd3" +
       "AuaiwZ7ObrNmxHYVNleYgdaatwxoTy9QVfh2IVx1Ftm6PAvUVHbi8OF9N+SM" +
       "YgnR\n26E/j8srTLAQfx1an4xhCR3lKvJIeuNZSorrab0laX0eSW/BZg8j/p" +
       "TKJ2+yMtgArqdQMTGPDN89\nDzLst2WY5NvZvR6W3z9LlnV2KWH/5mB5h8XS" +
       "N5GL44HzwPEum+NYLo53n4MTzrU4zs3D8acWxwt1\nqkFCZ11qEksHr5mLIP" +
       "vFPeL8rIA4qTy+vdRdJvts/653h7+o6jTULRmaLEziAj8/38EQX9z3XfNJ\n" +
       "1V7huZ1+K19fxaBsV7WVMh2nsotXFb8fydw1LoZrp6WgnV4FcbS5lg5fpvA1" +
       "TjIxD8/4zEcLrCm/\nxOYhUKohjNMsTY+rUtRR8sNfZnN7/+HBxz1sFVyChU" +
       "/IiS9P7dLNQGGTiggbbwV2gdGUB43fGcpL\nmn5O7XgBzL/H5rcAL6oL3JGe" +
       "dDA+NV2McQtj/KwwYnPsqyH7YwFkf8LmBUA2paoJD7IXzxEZT4JN\ncN1uIb" +
       "v9bJAtzYnMLfPfCvS9hc2fIbPD3pcyD6C/TAdQI1x3W4Du/noBvV+g7wNs3o" +
       "VaPiZ54Zya\nDpweuA5bcA5PH07A3KI6nue438cF0H2KzYcMtnxYDKyncUnx" +
       "gDw9HZCb4XrGAvnM9EEW8wHFHpAe\nuF8UgPs/bD6H9MThDuCqpVMP4DPTAY" +
       "ynh69agF89r4D5mI1p7uhOpK8Qd3vFqXMvl2ZFCJsQVdc0\nDXYaFWbJ3rqq" +
       "rdWz14iooiD3dt9/ovKNA8nL+uwlE/3Fd8HXXd2vam5dcVnbyrUtmPNBAGTl" +
       "K8tv\nWd8MbAKw5eCItmkwyWNYX9F0DLsJrpOWak+ef8MWsBOHO7+AKhZhUw" +
       "+VDJ+0QZJljyLmnKMieNWD\npeApSxGncioCmwIbZt/SAn3LsbkENrxc9h4l" +
       "6hF9yTmKzivaBXC9b4n+fl7R9xQQ79ICfa3YNIMH\nxgSDDZr1sEf6lul44B" +
       "q4TlvSn56+B3rq0Nr0JnGjLmhjkmhwUOsKAMYjKl87FP9W6Z/QVIVmYe74\n" +
       "qphh31rhpAE8JqnP+vRufi4WI2/deN1nkb/+xzzHtD/pVkZIWSwpy+5zYNd9" +
       "iabTmMQ1VWmeCvMD\nHl8fI8HsAwQ82eU3KKOv1xy6GazrGspIqXXnHgRVeA" +
       "AG4e1Vmq3eIP8yGTImoMIP9XGEqQz0iPfi\njOM0/j8O9pFX0vwvhxHxmkd2" +
       "LEzt33oHP0crFmVhagrJVERIqfnti1PFY7NFeanZtF4jjz+2/elH\n2+xUzr" +
       "+NzHT5UoY7rjJ7M6xZlbZm2pHmujY0SQ10bhhXCzqsCXEj31SoFsfxWwCoYE" +
       "2eE8XOjP8g\nodbHA/ujwoh453Uvzfy0dsZTXC1l/AsDjEB+3yzwNSBoJ7Sg" +
       "FVdBb1yhG/ADUxte9n4N/Bj7dmn/\nB8mUJL9pIwAA");
}
