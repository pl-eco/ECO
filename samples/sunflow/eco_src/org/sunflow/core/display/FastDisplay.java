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
                                                                              getWidth() &&
                                                                              h ==
                                                                              image.
                                                                              getHeight()) {
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
                                                                                this.
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
                                                                                                  getKeyCode() ==
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
                                                                                  pack();
                                                                                frame.
                                                                                  setLocationRelativeTo(
                                                                                    null);
                                                                                frame.
                                                                                  setVisible(
                                                                                    true);
                                                                            }
                                                                        }
                                                                        t.
                                                                          start();
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
          getWidth();
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
                    toRGB();
    }
    public void imageFill(int x, int y, int w,
                          int h,
                          Color c) { int iw =
                                       image.
                                       getWidth();
                                     int off =
                                       x +
                                       iw *
                                       y;
                                     iw -=
                                       w;
                                     int rgb =
                                       -16777216 |
                                       c.
                                       toRGB();
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
              getWidth(),
            image.
              getHeight(),
            pixels,
            0,
            image.
              getWidth());
        this.
          repaint();
        t.
          end();
        seconds +=
          t.
            seconds();
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1415304895000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVZe2wUxxkf3/ltJ35iGwIYjAkFzB1QsDFOCsYPOHOAYxuT" +
       "GKgZ786dF/Z2N7t7\n57ODSKJIQEIfoNIqrRoCLRXghAY1rUjVhIJC2gBqRZ" +
       "BCJKTQVvQRKU3atFJK1f7Rb2Z3b/f2HimQ\nWNrx3M58r/ke85vZlz9CeZqK" +
       "pnOaTx9XiObr6O/Fqkb4DhFr2gC8GubeyivqPbFekj0oJ4g8Aq+j\nsiCn+X" +
       "msY7/A+wOdbXEVLVRkcTwsyrqPxHXfTnG5ya8nuDyF4ZYjZyufPp5b70F5QV" +
       "SGJUnWsS7I\nUpdIIpqOyoM7cQz7o7og+oOCprcF0X1EikY6ZEnTsaRrj6M9" +
       "yBtE+QpHeepodtAS7gfhfgWrOOJn\n4v29TCxwqFKJjgWJ8O0JcUDZlEwJap" +
       "t0famzgUkhHRwEc5gGYPWshNWGtSmmKt6Tg827j57yorIh\nVCZI/ZQZB5bo" +
       "IG8IlUZIZISoWjvPE34IVUiE8P1EFbAoTDCpQ6hSE8IS1qMq0fqIJosxOrFS" +
       "iypE\nZTKtl0FUylGb1Ciny2pijUICEXnrV15IxGEwu8Y22zC3m74HA4sFUE" +
       "wNYY5YJLm7BAk8Xu+mSNjY\nuB4mAGlBhOijckJUroThBao0fCliKezv11VB" +
       "CsPUPDkKUnQ0LSNTutYK5nbhMBnWUZ17Xq8xBLOK\n2EJQEh1NcU9jnMBL01" +
       "xecvhnYc2n+09+/9xqFtu5POFEqn8xEM10EfWREFGJxBGD8HbUdzjwWHS6\n" +
       "ByGYPMU12ZjTPvfs5uAHv6w35jyQZs6mkZ2E04e5jYfq+55YKyMvVaNQkTWB" +
       "Oj/JcpYOveZIW1yB\nrK1JcKSDPmvwfN+vHntqknzoQcUBlM/JYjQCcVTByR" +
       "FFEIm6lkhExTrhA6iISHwHGw+gAugHIeSN\nt5tCIY3oAZQrslf5MvsNSxQC" +
       "FnSJiqAvSCHZ6itYH2X9uIIQKoAHrYSnEhl/7L+OWnx+LSqFRHnM\nr6mcX1" +
       "bDid+crBI/L2iKiMf93VjTO42+jwaQoqN+/6gcIX7MYUmQZH9YgJTl5EU8id" +
       "H/d8c2TjWu\nHMvJoSXQncoiZME6WeSJOsyduHV5d9f6Z/cbYUJD27RVR40g" +
       "zWdK81FpPlOazyEN5eQwIdVUquEt\nWOtdkLVQ30rn92/v2bG/wQthoozlwk" +
       "LRqQ1glalKFyd32KkdYFWQg/iq+8HWfb7bJ1YZ8eXPXIHT\nUpdcPX3l6D9L" +
       "F3iQJ315pCZCgS6mbHppTU2UvUZ3QqXj//FzG169fuX9L9mpBYuVkvGplDRj" +
       "G9zO\nUGWO8FADbfbHp5Z5t6DBQx6UC2UASh/TH6rKTLeMpMxts6ogtaUgiE" +
       "pCshrBIh2ySlexPqrKY/Yb\nFiXlrF8FzimhoVwDzzQzttl/OjpFoW2NEVXU" +
       "2y4rWJW9/Uz+4vdeL3mLLYtVkMscW14/0Y30rrCD\nZUAlBN6//3zvt7790b" +
       "6tLFLMUNFhH4yOiAIXB5IHbRLIaxFqC3Vk42YpIvNCSMAjIqER99+yuUt+\n" +
       "9tdvlBuuEeGN5dmmz2Zgv5+6Bj115av/msnY5HB0X7HNsKcZ1lTZnNtVFY9T" +
       "PeJPX5vx3V/jF6Ds\nQanRhAnCqgdiliG2jn627gtY63ONLaFNA/BuyhD6aX" +
       "bxYW73ZLgh+vilnzOtS7ATDjjdsAErbYbn\naTOHrm6tO3vXYW0U5i07v3Fb" +
       "uXj+P8BxCDhysHtqm1QoHPEkJ5qz8wpuXHizZsc7XuTpRsWijPlu\nzOIfFU" +
       "HgEW0Uak5cWbWaxVb5WCFtmcmILcI0cwHijl/5oNz8zOnfTTGAnTnDI00ng5" +
       "c3vcAWIGPi\np9kCXXwmzm0+cvs3+k3Gx85ASj07nlpQATfZtCuuxyryz7wY" +
       "8aCCIVTOmchuEItRGudDAEQ0C+4B\n+ksaTwYVxg7alqgw093Z7xDrzn27kE" +
       "Ofzqb9Ule6l9LVngpPlZnuVe50z0Gss4qRNLJ2XiI5CxRV\niGGK9kA21FCj" +
       "ptSCEWwD8mljgIl8Pd2qqUolG19Km9WGi5dnDIWVyUrOgqfaVLI6g5IB2rSD" +
       "LkIE\nAJSly3QWpHhM97HXvjXREC2ZfID+cmnVc4da0aWbYmo1JYNWvaZW+Y" +
       "oQJ6JGS6fjQMLKBS3Hpw52\nVvW1bn2G7VhFgJGxttH2IJxMaC8HQm9u5lxI" +
       "MBvm5m0/+/cL58g8lraFggaR0q6G0+BFB80neJJs\neC90hO06uSNYM2LGDb" +
       "RTcXQSPGYhdr9Ca0qd8+SlChFAcDG2Ud3a2/DG25tf3Gds7lmyO4lqmHvy\n" +
       "d7/f5f3mhRGDzp3CrsmHZh7/86u3+qqNjcCA+nNS0LaTxoD7zIAyZsDsbBLY" +
       "7IsLZ7+8p++mqVFl\nMmjtgoPdX8bfJPMe+vof0qArLyykoigAHQ1/ty5tbQ" +
       "GhlbBq9PzpE3izKnVe6x6ZDEmTvIetP/NG\nO6Uwo6OIvXGEi1dWNAqwHSdZ" +
       "k1PjJkWjIOQ+h5BA5+4zPaWFxw7sZfzNWCtygHXzd0EMqxudFpQw\nvZcuXr" +
       "K8pVVH274AFLtyxYqm5mWLWhaDsIF1gX6fkUVUgx2JnNoDx4XURaOmIhuig9" +
       "H3s1JASytz\njXMQjMnt62rvdBWER+6wIFSb8MmCUekKgmwWhBzdKlF1Tpit" +
       "jWs6ifgGhIix3Ti0Ue5QGwreak1t\najNoEzO1KdDARxKvMQ5bTEnbaGGHXV" +
       "x3KTJ2h4pQ4XWmInUZFNlj1Ul7J9nqEvtkFrHxNDsU7c93\nYomctCvODjZm" +
       "0NGsn5HpSM0yft+jn5TuxRe3e0ygth3SV5eVRSKJmYFp45YZSeecDaxK2jjh" +
       "uVMv\nndXfWbjSqB0LMldBN+GClUcn6le+cuAuTjf1LtvcrCtiDzziHRXeZp" +
       "XAhB0p9yPJRG3JYKMY9Imq\n0kAS5JiViAfm/i54Gsx4aHDHg+30JG96mDd1" +
       "VKqNSxwcYiRA1HzchaC9bKo3ETxGw1h+JwvYfp42\nB3XY2igoWEPCguTOg9" +
       "yYLPB2KB76rAywoCz78bXkA9Zq87H6qebT5kGXwnmMY57LNpeVP8xi5Y9o\n" +
       "cxRWkFnZqxIFq8xH37PtOnYvdlG3dph2dXwedrE5/QkhKyjPh7MJsfK71pnf" +
       "Bu6DM5usGtutsW0t\n+XJL67JW14YblDksBjqPXSi5dija3GMlOUebn3xBW1" +
       "xzS9Py5kXNKyDIqBpM4OksfnyNNpNgBjNs\nswJEbje+dC9u/Ao8QXOFg5+b" +
       "G7N4hTE9n8Xii7R5AwotI+oWRNFl77m7tJdVo3nwDJr2Dt5RNaLN\nYXcNci" +
       "r+2yxjV2lzSQd0To3qkniXTZfvxSYqLGTaFLp3m3KSt8+KxJlqrYqVUYHTGM" +
       "8bWay9SZt3\nAU8osFnpLlOv/7+mwkmzxJE/9O6lLuUzhXG1zgVvPLHt0+C7" +
       "/zaOM9b1dwnA2lBUFJ3HY0c/X1FJ\nSGAalxiHZYX9+1M6zGBmNIAns8d0/a" +
       "NB8YGOyt0UkOH0n3Pah2CRYxoFYkbPOeljOCzAJNr9m5L+\nkN2LJSLGkxaL" +
       "rs6cJFTBvh5ZO3/U+H40zD16euus+IGBgwxO5HEinpigbIoB8BsXiAn0MDsj" +
       "N4vX\nVXTmlcHXf9xqFU52wVTtiLikoF1qjCY5vzTh/ES4TbNxe39UAQ9p2h" +
       "asQgUOa5lIIdBi9EIFlqAl\nA7BqT/o2R8wbGOtmZpg7vO1S9T8qqn7BlqWQ" +
       "XdPADCrv4SxXKuxjAAXeFWb2VbizDyCuxj6DWeaV\nO8xjn5HiNOwBq6W/r+" +
       "yKKDq7YZx4rfanD504ctPDbkz/B5ICoGXuHAAA");
}
