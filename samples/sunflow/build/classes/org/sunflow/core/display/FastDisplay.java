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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1Yb2wcRxWfO/93nPhP7MQNieM4TsFJuSWIIqWuQp3DTuxc" +
       "ayt2gnBF3fHunL3x3u52d84+u5i2kaqEIgKibkih+AOkLSluUhChRVUlfwCa" +
       "qC2oAYH4QAN8oSJEIh8oFQXKezO7d3t7f2ikqCft3OzMvHnvzXvv997syjVS" +
       "5Tpkl20Z81OGxWMsw2NHjdtjfN5mbmwocfsIdVymxQ3qumMwNqF2vdD4zntf" +
       "n26Kkupxsp6apsUp1y3TPcRcy5hlWoI05kb7DZZyOWlKHKWzVElz3VASust7" +
       "E2RNgJST7oQvggIiKCCCIkRQ+nKrgGgtM9OpOFJQk7sPkC+RSIJU2yqKx8m2" +
       "/E1s6tCUt82I0AB2qMX3I6CUIM44pDOru9S5QOEndilL37yv6UcVpHGcNOrm" +
       "KIqjghAcmIyThhRLTTLH7dM0po2TZpMxbZQ5OjX0BSH3OGlx9SmT8rTDsoeE" +
       "g2mbOYJn7uQaVNTNSavccrLqJXVmaP5bVdKgU6DrhpyuUsMBHAcF63UQzElS" +
       "lfkklTO6qXGyNUyR1bH7ICwA0poU49NWllWlSWGAtEjbGdScUka5o5tTsLTK" +
       "SgMXTjaV3BTP2qbqDJ1iE5y0h9eNyClYVScOAkk4aQsvEzuBlTaFrBSwz7V7" +
       "7jz5oHnAjAqZNaYaKH8tEHWEiA6xJHOYqTJJ2LAzcYpueOVElBBY3BZaLNe8" +
       "+MXrd93WsXpRrvlIkTXDk0eZyifUM5Pr3twc79lTgWLU2paro/HzNBfuP+LN" +
       "9GZsiLwN2R1xMuZPrh76xecffo5djZL6QVKtWkY6BX7UrFopWzeYs5+ZzKGc" +
       "aYOkjplaXMwPkhroJ3STydHhZNJlfJBUGmKo2hLvcERJ2AKPqAb6upm0/L5N" +
       "+bToZ2xCSA085A54Woj8iX9OZpRpK8UUqlJTNy0FfJdRR51WmGpNOMy2lP74" +
       "sDIJpzydos6Mq7hpM2lYcxNq2uVWSnEdVbGcKX9YUS2HKZru2gadVwaoyz8r" +
       "+zF0OvvDZZdB7ZvmIhEwzOYwLBgQUQcsQ2POhLqU3td//dzEa9FsmHjnBkAG" +
       "3GIetxhyi3ncYgFuJBIRTFqRq7Q82G0GEACwsaFn9AtD95/oqgCXs+cq4dBx" +
       "aRco7InSr1rxHEwMCjBUwVfbv3vv8di7z35G+qpSGtOLUpPV03OPHHnoE1ES" +
       "zQdnVA2G6pF8BCE1C53d4aAstm/j8bffOX9q0cqFZx7ae6hRSIlR3xU2gmOp" +
       "TAMczW2/s5NemHhlsTtKKgFKAD45BXcHZOoI88iL/l4fSVGXKlA4aTkpauCU" +
       "D3/1fNqx5nIjwjvWiX4zGGUNhsMGeDZ58SH+cXa9jW2r9Ca0ckgLgdQDP119" +
       "8sK3du2JBkG9MZAmRxmXENGcc5IxhzEY/8PpkcefuHb8XuEhsGJ7MQbd2MYB" +
       "MMBkcKyPXnzg91feOvObaM6rOGTO9KShqxnY49YcF4ATAyANbd992ExZmp7U" +
       "6aTB0Dn/3bhj94W/nWyS1jRgxHeG2/7/BrnxW/aRh1+7758dYpuIiuksp3lu" +
       "mTyA9bmd+xyHzqMcmUcub3nyVfodQFtAOFdfYAK0iNCMiKNXhKl2ijYWmtuN" +
       "TaddMJcRI+3irRpY95QOogHMyoHg+9ewMXnsz+8KjQrCp0gyCtGPKytPbYrv" +
       "vSroc36M1FszhXAEFUyO9pPPpf4R7ar+eZTUjJMm1SuPjlAjjd4yDiWB69dM" +
       "UELlzeend5nLerNxujkcQwG24QjKwSD0cTX260NB04CnfAs8672gWR8OmggR" +
       "nT2CpEu0O7D5mO+zNbajz1KsvYA/oJGMzjZQREB4zJ2DCiU2NOB44jRJK38q" +
       "X4ZOeFo9GVpLyNCHTS+w0VNQqfhsNgtvpHM8JoZj+9JJxBVtEN9KM0Sl2zyG" +
       "bSUYDngMq209wwwX/G9Haf8ToSCLlOVntv/yoeXtfwLfGSe1ugtW6nOmilRN" +
       "AZq/r1y5enntlnMCNysnqSvtFS43C6vJvCJRmLfBzpQPlRFHT0FZM+vVXcpi" +
       "y5WZp95+XuapcFyEFrMTS4+9Hzu5FA1UstsLiskgjaxmhWRrpQ3eh18Env/i" +
       "g2ePA7KaaYl7JVVntqayhTrbyoklWAz85fziy99fPC7VaMkv5PrhnvL8b//z" +
       "euz0Hy8VqRIqdO/6guAT8ZK88Brbd4bPFY+AKHZ70Pd1kxoQBNUGM6dk4TaM" +
       "zUE7k905Kkl815VAipEO9bNlMsRkf06WIroVy95dYDJTIKNDtuQVIncLZ8hB" +
       "0WNnf/Aif3PXHfJQdpb2iTDhq8f+umls7/T9N1B+bA2ZKLzl2btXLu2/Vf1G" +
       "lFRkEa3gEpRP1JuPY/UOg1ubOZaHZh22+DuITXeZHDNdZu4oNkmwoop2kGaD" +
       "s91aPIf2p2wust7CSxt/fOezy2+JJJ4hpdGm1atN/BqlGNqYHtpEuO8D7cHa" +
       "1Z13OUvFxvSUzELFGWHRs9FjtLEEI9djVOOC65iaK94T2IxIBzuE7mxYlJfm" +
       "g3u3e3zaS/CZ9+EzlxaG5Y6ZEtlExFIw50eKHoUo473SHbFhS6nLqMCFM8eW" +
       "lrXhp3dHPVuPclLHLfvjBpsFUA/WF9ifzOop1OqHp8vTs6t4QVkWFhrcebgK" +
       "OZYJFZGWCXlghVhakT0Y2Yhtv1LGWb+KzaMccgGmuH1sSjeLmbBy1tK1wpIq" +
       "pKUom+/yHr9fqGWx4KoSO1aFxA8pcqqMIqexeRwOSSgyAtdH6ojg+9oHExuN" +
       "E/fEjt8MscWawSwT3J8MwfOGx+QNmaS0m3sHlpGtiIJlhJrM8O/aHwqfjB9i" +
       "G4MhJmspAD3LsW1bmmu5jCmfwebbnKwRhIdtDTL3DVhyLzwJ75ATN8sBvyco" +
       "V8pIfQ6bs4AHQuoB3TA+mMwCGj4KzxFP5iM3DA3YfDkMCEHZflJm7iVsfsih" +
       "tkS5+03tBsTG+aQndvLmiB3Jx+rmbEW+36H2tK66Yt/VMgr9DJuXIe3YcMvi" +
       "JbSB4mpN4KMNJuj2go/F8gOnem65sXbj8uHfyXLa/whZlyC1ybRhBK9GgX61" +
       "7bCkLgSqkxclWVpcKpZ/vM9IkEa9npD3oqR4nZOmMAUgMv4Fl/0KNAosw5Qs" +
       "e8FFl6E8hUXY/bVd/HIlojlDAskMP0QE3/K+SmDxJz7G+4VaWn6On1DPLw/d" +
       "8+D1Tz8tqj4ohujCAu5SC9cM+UEmW+xtK7mbv1f1gZ731r1Qt8PPu+uwaQm4" +
       "WHvAAVL/A4Zctin6GAAA");
}
