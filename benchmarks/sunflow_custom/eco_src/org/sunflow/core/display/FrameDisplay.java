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
                                                                 getDefaultToolkit().
                                                                 getScreenSize();
                                                               boolean needFit =
                                                                 false;
                                                               if (w >=
                                                                     screenRes.
                                                                     getWidth() -
                                                                     200 ||
                                                                     h >=
                                                                     screenRes.
                                                                     getHeight() -
                                                                     200) {
                                                                   frame.
                                                                     imagePanel.
                                                                     setPreferredSize(
                                                                       new Dimension(
                                                                         (int)
                                                                           screenRes.
                                                                           getWidth() -
                                                                           200,
                                                                         (int)
                                                                           screenRes.
                                                                           getHeight() -
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
                                                                 pack();
                                                               frame.
                                                                 setLocationRelativeTo(
                                                                   null);
                                                               frame.
                                                                 setVisible(
                                                                   true);
                                                               if (needFit)
                                                                   frame.
                                                                     imagePanel.
                                                                     fit();
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
                               imageEnd();
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
                        this.setDefaultCloseOperation(
                               EXIT_ON_CLOSE);
                        this.addKeyListener(
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
                        imagePanel = new ImagePanel(
                                       );
                        this.setContentPane(
                               imagePanel);
                        this.pack(); }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1414698334000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAJ1XfYgVVRQ/+/b7I/ZL183U1XXVzG1GoU9WsnU/cvWpy66u" +
           "umrr3Zn73o7Om5nm\n3rf7dpMogrSiKDIoSJMQNMkSKiwSM8xCJbCggiAh+q" +
           "OgDCqojfqjc+/MvI/Z9yp68O7cuffcc+75\n+p0zr12HUubCPI0pfNKhTOka" +
           "7Ccuo3qXSRjbgksj2sXSyv7jGyw7AkVRiBg6h9qoxlSdcKIautrX\n3ZFyYY" +
           "Vjm5Nx0+YKTXFlr3m7z2999PYZDLcdOdPwyLGSlgiURqGWWJbNCTdsq8ekCc" +
           "ahLrqXjBM1\nyQ1TjRqMd0ThBmolE122xTixOHsAHoLiKJQ5muDJYVE0EK6i" +
           "cNUhLkmoUrzaL8Uih0aXcmJYVO9M\ni8OT7bkn8dr+uYGZ1MikQmwOoTryBq" +
           "j1wrTWnrYzVHWKTwzdsf/oq8VQOwy1hjUomGmoCUd5w1CT\noIlR6rJOXaf6" +
           "MNRblOqD1DWIaUxJqcPQwIy4RXjSpWyAMtscF4QNLOlQV8oMFqNQowmd3KTG" +
           "bTdt\no5hBTT14K42ZJI5qN2XU9tTtFeuoYJWBF3NjRKPBkZJ9hoUebwmfSO" +
           "vYtgEJ8Gh5gvIxOy2qxCK4\nAA2eL01ixdVB7hpWHElL7SRK4TC3IFNha4do" +
           "+0icjnBoDtP1e1tIVSkNIY5wmB0mk5zQS3NDXsry\nz4qm3w6eeOncvTK2S3" +
           "SqmeL+VXhoQejQAI1Rl1oa9Q5OJ5VDfTuS8yIASDw7ROzRdC45szX6/fst\n" +
           "Hs1NeWg2j+6lGh/RNj3bMvDgfTYUi2tUODYzhPNzNJfp0O/vdKQczNqmNEex" +
           "qQSb5wc+2vHwSfpD\nBKr6oEyzzWQC46hesxOOYVL3PmpRl3Cq90EltfQuud" +
           "8H5TiPYsh7q5tjMUZ5H5SYcqnMlu9oohiy\nECaqxLlhxexg7hA+JucpBwAa" +
           "8Q/N+O8C7yefHO5SVJa0YqY9oTJXU203nn7XbJequsEck0yqvZjB\ntNt7UU" +
           "QEORy2qGN2gqpEI5Zh2WrcwJzV7Ft1Oi6e/5NvSty5YaKoSIBgOJlNzIN1tq" +
           "lTd0Q7/u3l\n/T0bHj/oBYoIbl9bDqtQnOKLU4Q4xRenZItrG0CzUlcuQVGR" +
           "lDhLXMFzHpp+HyYxwl3N8sHd6/cc\nbC3GqHEmStBugrQVdfTv1aPZXZlM75" +
           "OgqGG4Nb+y84AyfXyNF25qYUDOe7r66qkrR3+tuSUCkfxo\nKfRFvK4SbPoF" +
           "xKZRsC2cX/n4//TExje/uPL1zZlM49A2AwBmnhQJ3Br2jGtrVEdIzLA/dmNt" +
           "8TYY\nejYCJYgKiITy/ggyC8IychK5IwBFoUt5FKpjtpsgptgKkKyKj7n2RG" +
           "ZFhkydnIswrwA/3nv9UJdP\nsTvbEWOTF2LC2yEtJOhOP1q28suz1RelWQJ8" +
           "rs2qgIOUe9lenwmWLS6luP71C/3PPX/9wE4ZKV6o\nQAopl2YoMbtNRBjhv7" +
           "atVsLWjZhBRk0qAu2v2iWr3v7x6TrPIyauBA5t/3cGmfUb18LDV+7/fYFk\n" +
           "U6SJ6pK5fYbMU6Ixw7nTdcmkuEfqkc/mv/gxOYzgh4DDjCkqMQR8hcSlFGnu" +
           "5XK8NbS3UgytyLu9\nQMTnqeUj2v6T8dbkA5felbeuJtlNQbb1NxKnw3O4GB" +
           "YL684JJ+06wsaQ7rbzm3bVmef/RI7DyFHD\nGso2u5jyqRzf+dSl5V99cKFp" +
           "z6fFEOmFKtMmei+RYQ+VGG+UjSHupJw198qQqpsQUVYnVQZphLm+\nAVJZbw" +
           "ImlhfO+l7RCWQSZmS0/UT08ubD0gAF8z1PIQzxmTq39cj0J/ya5JNJPHF6UW" +
           "omqGL3lDl7\n1xfj9WWnX05EoHwY6jS/vxsiZlKE9zC2Iyxo+rAHzNnPbS28" +
           "OtqRBpZ54aTPEhtO+QyY41xQi3lN\nvixfgP9uP8u7w1leBHJyjxiWcrx6Aj" +
           "uUfmJRUzKZw2F+dp1gk4zThNKXpvJQQoyrxLDG8+xt+SLA\n22qT4zIv7yMc" +
           "yh3XGMe6zqGMyS4zlRMpGBzzC/VBsoc7sP3nmsfIh7u98tGQ21v0YP/93eQF" +
           "umz1\nU9/kKYFlfh+bLZBDdVbJC4yw5D8VS7xs84zW2msHtehXD+76Lfr5Hx" +
           "Lp0y1bNfZNsaRpZjsza17m\nuDRmSKdWe6515GMQe6xCN0KT+jN5+QHvxBB+" +
           "oIRPcCgRj2yyHah+Fhny8mfZRLs4FCORmO52Ags1\nyL5EYRPYLCvrpVVyPS" +
           "lwdHFOnssvHt9OG5PeN8+Itv3UzoWpJ7c8Iyt6KX4rTU3J5hZ7da/KpQv4\n" +
           "ooLcAl5X4fQbQ2dfvzvig66Ew1mpTNCmwywdwbNyI7gmHcFFgaZzJSqKBFYG" +
           "kw56iLFtxMXuLs4K\nHeVQOi7SH01wZwGo68z5nqQ+XgQ4MqId2nVp1i/1je" +
           "9Js1RIUEEKIW/1PwBAUwAAa30AWBsGAJF3\n8tMtUK8uSz356ZMS6O1CS/7q" +
           "2pNwuKyHU+/MeWv18SPXIrKs/w3RzGfqog8AAA==");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1414698334000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVYfWwUxxUf3/nbTv0B2A4BjI0JDZg7QJgCdhscsBObA1x/" +
       "kMSEOuPdufPC3u5m\nd+58dtwkKBImQW1jtZUSKTikQuKjJEFKIlI1JaAkbR" +
       "pUKYnURIoU2gqprdSmalQppWr/6JuZ3du9\nvQ+s0J50s7M78968j99782bO" +
       "f45KLBMtk6wQnTKIFdo5NIBNi8g7VWxZw/BpTHq3pGLg9G5ND6Ci\nCAooMk" +
       "U1EckKy5jisCKH+3Z1pky0ztDVqZiq0xBJ0dAhtcPm1x/pyGJ4//zF+iOnip" +
       "sDqCSCarCm\n6RRTRdd6VBK3KKqNHMJJHE5QRQ1HFIt2RtBtREvEd+qaRbFG" +
       "rUfQYygYQaWGxHhS1BJxFg/D4mED\nmzge5suHB/iywGGRSShWNCJ3p5cDyv" +
       "ZMShDbphvMng1MytngflCHSwBar0xrLbTNUtUIntm/Zebk\n2SCqGUU1ijbE" +
       "mEmgCYX1RlF1nMTHiWl1yzKRR1GdRog8REwFq8o0X3UU1VtKTMM0YRJrkFi6" +
       "mmQT\n662EQUy+pvMxgqolppOZkKhupm0UVYgqO28lURXHQO0GV22hbi/7Dg" +
       "pWKiCYGcUScUiKDysaeLzZ\nT5HWsW03TADSsjihE3p6qWINwwdUL3ypYi0W" +
       "HqKmosVgaomegFUoWpqXKbO1gaXDOEbGKGryzxsQ\nQzCrghuCkVC0xD+Ncw" +
       "IvLfV5yeOfdQ1fHjvz/KUdHNvFMpFUJn8lEK3wEQ2SKDGJJhFBeCMR+lHf\n" +
       "g4llAYRg8hLfZDGne/XFkcif32oWc+7IMWff+CEi0TFp71zz4KP36ijIxCg3" +
       "dEthzs/QnIfDgD3S\nmTIgahvSHNlgyBm8PPjLB584R/4SQJV9qFTS1UQccF" +
       "Qn6XFDUYl5L9GIiSmR+1AF0eSdfLwPlUE/\nApAXX/dFoxahfahY5Z9Kdf4O" +
       "JooCC2aiCugrWlR3+gamE7yfMhBCZfBH7fCvQeLHnxRtDYWthBZV\n9cmwZU" +
       "ph3Yyl3yXdJGFZsQwVT4V7IYLJLvESYggyKBoOT+hxEsYS1hRND8cUiFlJXy" +
       "+TJHt+Rb4p\nJnP9ZFERS4L+YFYhDu7TVZmYY9Lp6+/P9Ox+6pgACgO3rS1F" +
       "q2G5kL1ciC0XspcLeZdDRUV8lcVs\nWeEwMPdhCFxIcdV3DR3sf/hYaxCQYk" +
       "wWg60CMLUV9LJl6ZH0nW509/FEKAHEmn5yYDZ04/TdAmLh\n/Ek4J3XVBy9d" +
       "PfmP6rUBFMidIZmOkKMrGZsBllbTma/NH1O5+P/t6T2vfnz1s6+70UVRW1bQ" +
       "Z1Oy\noG31e8PUJSJDGnTZn7q9Jng/2j8XQMWQCSD7cfkhsazwr5ERvJ1OIm" +
       "S6lEVQVVQ341hlQ072qqQT\npj7pfuEwqeX9ReCcKobmRvgvsuHNn2x0icHa" +
       "BgEr5m2fFjzR3niydMMnb1a9y83i5OQaz643RKiI\n8DoXLMMmIfD9s2cHfv" +
       "jjz2cPcKQIqBRR2AoT46oipYDkTpcEQluF9MIc2TaixXVZiSp4XCUMcf+p\n" +
       "Wb3x9b9+v1a4RoUvjmfbb87A/X77PeiJq9/55wrOpkhiW4urhjtNaLPI5dxt" +
       "mniKyZE68tHy536F\nT0Dmg2xjKdOEJxDENUPcjmFu97W8DfnGNrKmFXi354" +
       "F+jo18TJo5F2tNPPLrn3Gpq7C3IvC6YQ82\nOoXnWbOKWbfRH733YWsC5m2+" +
       "vPehWvXyv4HjKHCUYAO19pmQOVIZTrRnl5R9euXthoc/DKJAL6pU\ndSz3Yo" +
       "5/VAHAI9YEJJ2UcfcOjq3ayXLWcpURN8JS2wD8ZVk2KhttVDbmRCVr7vSZtE" +
       "gAib83QgXG\nJWabdkhs2pyyq4AfdrBmGx/axJrtQriOm+qQ8ryxnHdX/hTW" +
       "y0oZN/rHxtvPRN7fd4I7MW/yyrGT\n+/hMXxqZv/Ebeo3zcbMIo25JZe8KUP" +
       "65tFs/TtaVXnghHkBlo6hWsgvU/VhNsFgdhXrKcqpWKGIz\nxjNrI1EIdKaz" +
       "5DJ/BvMs689f7m4EfTab9at9KauaWXuZ4wTn6QVHEeKdfk7Sxts16QRTZphK" +
       "ErOi\nFZWzEoAtmRctIvmlsbD7ZljoyZRyBfzrbCnr8kg5zJoIBUOYHkk2Lm" +
       "gnbhuEAoeY/JNP1JECoqbc\nwFmaGS5NWcvaK7GMsTxf8ckL59kHvqg+it85" +
       "KPbv+syCrgcOPX+aepus6freH3LUHRVUN9arJElU\nj1DsNLc8o27Yw+tyF7" +
       "NPn/3pRfrhuu1iybX5481PuHb7yenm7a8c/wrVQrPPCH7Wdck7vh2cUN4L\n" +
       "8KODCIGsI0cmUWcm8CtBnoSpDWfAf2Vmbtxqh4ATCgvLjUFu1yDYtcl7SjaV" +
       "OFTbSV5RXD/a+ov3\nRl6YFSYtkMIyqMakx3/3+8PBH1wZF3T+POWbPLfi1B" +
       "9fvT64WCBBHMtWZZ2MvDTiaMY1qzEYFlsK\nrcBnv7Ou5fxjg9e4RIwOTl9B" +
       "OBOyLi/1df71UIGdgM+NUUh9cTii3UNiigDGYUPEFgC3OKkrsht0\nEwvdK/" +
       "iLlOlTRrPB9umGBfu0RMSKq5av4ZSPF9DyCGtmKKrmWg6YxMAmB17S1eu7t6" +
       "JXD/w323pt\n/l/oxYpRD355AcbcfvaZXYsGtx14kkd1BVYVbO11wyqgyKxX" +
       "BPBcnR/WaWZj0pqDF/9+5RJZwwuh\ncsWCfavbjOU4hHtovsDnyJ5PovO8ji" +
       "8ex5YIYf/tRfblRMadA7fK19JWZMGOvlnIik76bvSmb+5P\nVvjqpmEYcGSt" +
       "5CbZ2LGp4xtgwnowIbv4CilyKKJLWO3b9eKVqo/mElv6RRDf5pnQt2vmQn91" +
       "+YvH\nj/K8ZtuywnPCt9/Lktjc66Z29pij6OD/48C7vWNTe8eW9Vu2QhgyOb" +
       "nVniqA9GdZc5SiKm6ZEQOI\n/ECfvRWgf8vxlfO8ZaDfxK2c6ckCGp9izQnY" +
       "XzlRr6KqPn3nb0XfpfDvsvXtWrC+XvnOFxh7mTVn\noUjjsvdosk/0cwuuyy" +
       "G3ecHDzm9NWbed4oZOinz66ENfRn77LxHAzi1aFQA9mlBVb3nq6ZcaJokq\n" +
       "XOoqUawa/PF6roLKxjMUonaPC/uaoHgDalA/BcCbPbzTfg4g9kwDXnbPO+kS" +
       "bHgwiXXfMnJUuKJQ\nT2XYitlmVUZ25FfQTq2TEJfQY9IDLx1YmTo+/AxPtS" +
       "WSiqenGZtKSADiCiJdL7Xk5ebw+gBdeGX/\nmy9vczZqfkRdbN87ZGFwkxgt" +
       "4Huo0XKf+3viBuUn9ek3Gl/rOj1/LcBvHv4L15/3yDkYAAA=");
}
