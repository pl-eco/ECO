package org.sunflow.core.renderer;
import java.util.concurrent.PriorityBlockingQueue;
import org.sunflow.core.Display;
import org.sunflow.core.ImageSampler;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.Options;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.QMC;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class ProgressiveRenderer implements ImageSampler {
    private Scene scene;
    private int imageWidth;
    private int imageHeight;
    private int[] sigma;
    private java.util.concurrent.PriorityBlockingQueue<SmallBucket> smallBucketQueue;
    private Display display;
    private int counter;
    private int counterMax;
    public ProgressiveRenderer() { super();
                                   imageWidth = 640;
                                   imageHeight = 480;
                                   sigma = null;
                                   smallBucketQueue = null; }
    public boolean prepare(Options options, Scene scene, int w, int h) { this.
                                                                           scene =
                                                                           scene;
                                                                         imageWidth =
                                                                           w;
                                                                         imageHeight =
                                                                           h;
                                                                         sigma =
                                                                           QMC.
                                                                             generateSigmaTable(
                                                                               1 <<
                                                                                 7);
                                                                         return true;
    }
    public void render(Display display) {
        this.
          display =
          display;
        display.
          imageBegin(
            imageWidth,
            imageHeight,
            0);
        SmallBucket b =
          new SmallBucket(
          );
        b.
          x =
          (b.
             y =
             0);
        int s =
          Math.
          max(
            imageWidth,
            imageHeight);
        b.
          size =
          1;
        while (b.
                 size <
                 s)
            b.
              size <<=
              1;
        smallBucketQueue =
          new java.util.concurrent.PriorityBlockingQueue<SmallBucket>(
            );
        smallBucketQueue.
          add(
            b);
        UI.
          taskStart(
            "Progressive Render",
            0,
            imageWidth *
              imageHeight);
        Timer t =
          new Timer(
          );
        t.
          start();
        counter =
          0;
        counterMax =
          imageWidth *
            imageHeight;
        Thread[] renderThreads =
          new Thread[scene.
                       getThreads()];
        for (int i =
               0;
             i <
               renderThreads.
                 length;
             i++) {
            renderThreads[i] =
              new SmallBucketThread(
                );
            renderThreads[i].
              start();
        }
        for (int i =
               0;
             i <
               renderThreads.
                 length;
             i++) {
            try {
                renderThreads[i].
                  join();
            }
            catch (InterruptedException e) {
                UI.
                  printError(
                    Module.
                      IPR,
                    "Thread %d of %d was interrupted",
                    i +
                      1,
                    renderThreads.
                      length);
            }
        }
        UI.
          taskStop();
        t.
          end();
        UI.
          printInfo(
            Module.
              IPR,
            "Rendering time: %s",
            t.
              toString());
        display.
          imageEnd();
    }
    private class SmallBucketThread extends Thread {
        public void run() { IntersectionState istate =
                              new IntersectionState(
                              );
                            while (true) {
                                int n =
                                  ProgressiveRenderer.this.
                                  progressiveRenderNext(
                                    istate);
                                synchronized (ProgressiveRenderer.this)  {
                                    if (counter >=
                                          counterMax)
                                        return;
                                    counter +=
                                      n;
                                    UI.
                                      taskUpdate(
                                        counter);
                                }
                                if (UI.
                                      taskCanceled())
                                    return;
                            } }
        public SmallBucketThread() { super();
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1163562052000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAKVXe2wURRif3vXdmmsLlDeFUpDnLUQxkRJ51BYKR6ktz/Io" +
           "0925u6V7u8vsbHsU\nghATQIgPoiaaKBJDUkAQEzRoggoBFOUfMFETElBDoi" +
           "aKiTFBjP7hNzP33GtL1Etudnfme8z3+s03\np+6iAoeicaoTZDts4gQb2lsx" +
           "dYjWYGDHWQ1TneqVgpLW/hWm5UN5IeTTNYYCIdVRNMywomtK85P1\ncYpm2p" +
           "axI2JYLEjiLLjNmJeQtzw0L0fguiPnqvYey6/xoYIQCmDTtBhmumU2GiTmMF" +
           "QR2oZ7sOIy\n3VBCusPqQ+ghYrqxBst0GDaZsx3tRv4QKrRVLpOhSaGkcgWU" +
           "KzamOKYI9UqrUAsShlHCsG4SbXFK\nHXDOyuaEbSf42nKpQUgxX1wL5ogdgN" +
           "UTU1ZLa3NMtf3H1z626+gJPwp0oIButnNhKljCQF8HKo+R\nWBehzmJNI1oH" +
           "qjQJ0doJ1bGh9wmtHajK0SMmZi4lThtxLKOHE1Y5rk2o0JmcDKFyldtEXZVZ" +
           "NOWj\nsE4MLflVEDZwBMyuTpstzW3i82BgqQ4bo2GskiRLfrduQsRrvBwpG+" +
           "tWAAGwFsUIi1opVfkmhglU\nJWNpYDOitDOqmxEgLbBc0MLQmEGFcl/bWO3G" +
           "EdLJ0CgvXatcAqoS4QjOwtAIL5mQBFEa44lSRnxm\nVt87cPz1jxeJ3M7XiG" +
           "rw/ZcC0wQPUxsJE0pMlUjG+27w5eYN7jgfQkA8wkMsaRZPObcm9NMnNZJm\n" +
           "7AA0q7q2EZV1qi2Ha9p2LrWQn2+j2LYcnQc/y3JRDq2Jlfq4DVVbnZLIF4PJ" +
           "xQttn27Yc5L87EOl\nzahQtQw3BnlUqVoxWzcIXUpMQjEjWjMqIabWINabUR" +
           "G8hyDl5eyqcNghrBnlG2Kq0BLf4KIwiOAu\nKoF33QxbyXcbs6h4j9sIoXL4" +
           "o0r4r0TyJ54MLQoqjmuGDatXcaiqWDSS+lYtShRwsAZepkortSKQ\n8Y7eQ9" +
           "oSc0GeSTZDm5SoFSMKVrGpm5YS0aF2VWu2Rnr483/Kj3Mbqnrz8jgoeovbgL" +
           "pYZhlA3an2\n3/liV+OKZw/IxOHJnrCeoYWgNphQG+Rqg0m1wQHU1rXHsGEs" +
           "cdVuwlZHKcEayssT+ofzDcnQQmC6\nocQBDMunt29evvVArR9yyu7NB69y0l" +
           "qwPLHLRtVqSONAs4BMFZJx1Fsb9wfv9y+UyagMDtcDcpdd\nP33t6O/lM3zI" +
           "NzCWcusBzUu5mFYOwCmMrPNW30Dyfz248uzX125NS9chQ3U58JDLycu71hsn" +
           "aqlE\nA8BMiz82OuBfh9Ye9qF8wAzASbF/gKAJXh1ZZV6fhExuS1EIlYUtCt" +
           "HiS0mcK2VRavWmZ0QCVfBh\nuMwlHkjPBgXa3n+mcM4358uuCIuTwBzIOPra" +
           "CZNlXpnOg9WUEJi/9WrrS6/c3b9RJEEiCxgqsqne\nA4UdB56paR4ocANAhg" +
           "epbo0ZszQ9rOMug/Bs+jswZe77vzxfId1uwEwyarMeLCA9P3oJ2nNtyx8T\n" +
           "hJg8lR8waTvSZNKcYWnJiynFO/g+4nu/HP/aZ/gNwD/AHEfvIwJGkDANCUcG" +
           "hX+ni3G2Z20OH2pB\n9qxB0nqA47xT3XUyUutu//xDsesynNkXZMZhJbbrZV" +
           "SF7mGg9BGUGLLgja+OsPlYzUMw0lu+y7AT\nBWGPXmjZVGFc+AvUdoBaFc5a" +
           "ZxUFLIhnhTpBXVB08+Kl6q03/MjXhEoNC2tNWBQAKoHMI04U8Chu\nL1wktl" +
           "HRW8xH4Rckdjsm4aV41pf4mCzGqYn04e/TPFQUjR/s5BSn/v71v5Xvw5c3S0" +
           "ipyj6NGqFj\n+3HHJfLwgue+HwAkCxOdT1ohx7HxWTi2UnQU6So+eOLtc+zG" +
           "zPlS34zBIczLOGP+0b6a+WcO/Qf0\nqvF4wCu6smfsU/6oftUnmh4JXDnNUj" +
           "ZTfaYvQCnsx6Um9yqfKRe5NjGVawEei9Hwb0nkWos31yTM\n5IYUnGy7XYau" +
           "xocondAQay18aGLIT10TgjMq85JA9Rg0Gz0CJu/sq/3o6po398u4TB/iJpDJ" +
           "1ak+\n/e133f4XLnZJPm/D5SE+POHYD2fvtA2XuSS70sk5jWEmj+xMhSUBm2" +
           "fzpKE0COrLMyed2t12W+yI\n8z3BUH6Ppcuudi4flkpnzntwxTFUmXO0C4qR" +
           "DM3+Vy0C933ORUM2x2ro5s5N90Jf/SlOtlQDWwZd\nZNg1jIw0y0y5QpuSsC" +
           "4sLJPQZosH9NqjB90ZQ8XJV2HGFsnTBRc2Lw94jT8yyWCuLIMMTqvEWyZR\n" +
           "FHINiPirbid9VSEwkd8ggtKJ2SjFj5TJWSkn7n/JcnXlDbBTXX9648T4odUv" +
           "CgwogJtjX59o9eHm\nIk/1VMlPGlRaUtZ19O6ZteffeTyZJlnnfQ7QzpWrQ+" +
           "QNwMzAx21jzGbigOz7YOR7C/qP3PaJE/8f\njln2ibYPAAA=");
    }
    private int progressiveRenderNext(IntersectionState istate) {
        final int TASK_SIZE =
          16;
        SmallBucket first =
          smallBucketQueue.
          poll();
        if (first ==
              null)
            return 0;
        int ds =
          first.
            size /
          TASK_SIZE;
        boolean useMask =
          !smallBucketQueue.
          isEmpty();
        int mask =
          2 *
          first.
            size /
          TASK_SIZE -
          1;
        int pixels =
          0;
        for (int i =
               0,
               y =
                 first.
                   y;
             i <
               TASK_SIZE &&
               y <
               imageHeight;
             i++,
               y +=
                 ds) {
            for (int j =
                   0,
                   x =
                     first.
                       x;
                 j <
                   TASK_SIZE &&
                   x <
                   imageWidth;
                 j++,
                   x +=
                     ds) {
                if (useMask &&
                      (x &
                         mask) ==
                      0 &&
                      (y &
                         mask) ==
                      0)
                    continue;
                int instance =
                  (x &
                     sigma.
                       length -
                     1) *
                  sigma.
                    length +
                  sigma[y &
                          sigma.
                            length -
                          1];
                double time =
                  QMC.
                  halton(
                    1,
                    instance);
                double lensU =
                  QMC.
                  halton(
                    2,
                    instance);
                double lensV =
                  QMC.
                  halton(
                    3,
                    instance);
                ShadingState state =
                  scene.
                  getRadiance(
                    istate,
                    x,
                    imageHeight -
                      1 -
                      y,
                    lensU,
                    lensV,
                    time,
                    instance);
                Color c =
                  state !=
                  null
                  ? state.
                  getResult()
                  : Color.
                      BLACK;
                pixels++;
                display.
                  imageFill(
                    x,
                    y,
                    Math.
                      min(
                        ds,
                        imageWidth -
                          x),
                    Math.
                      min(
                        ds,
                        imageHeight -
                          y),
                    c);
            }
        }
        if (first.
              size >=
              2 *
              TASK_SIZE) {
            int size =
              first.
                size >>>
              1;
            for (int i =
                   0;
                 i <
                   2;
                 i++) {
                if (first.
                      y +
                      i *
                      size <
                      imageHeight) {
                    for (int j =
                           0;
                         j <
                           2;
                         j++) {
                        if (first.
                              x +
                              j *
                              size <
                              imageWidth) {
                            SmallBucket b =
                              new SmallBucket(
                              );
                            b.
                              x =
                              first.
                                x +
                                j *
                                size;
                            b.
                              y =
                              first.
                                y +
                                i *
                                size;
                            b.
                              size =
                              size;
                            b.
                              constrast =
                              1.0F /
                                size;
                            smallBucketQueue.
                              put(
                                b);
                        }
                    }
                }
            }
        }
        return pixels;
    }
    private static class SmallBucket implements java.lang.Comparable<SmallBucket> {
        int x;
        int y;
        int size;
        float constrast;
        public int compareTo(SmallBucket o) {
            if (constrast <
                  o.
                    constrast)
                return -1;
            if (constrast ==
                  o.
                    constrast)
                return 0;
            return 1;
        }
        public SmallBucket() { super(); }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1163562052000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAALUYCWwU1/V7feEjsTGHuY2NCQHDLqFAGxyJw0BsWMD1RTCm" +
           "m+/Zv/bg2ZnJzN/1\n2qE5q0CTBoLaRKUqhEZUkIQ0UdOKtkpTIkKahrQilU" +
           "olpJBEVEnUNJWqSilVq7Tv/T+7Mzu7a0Sj\nrjQzf/+847/7vTn9KSm1LTJH" +
           "sYN8zGR2sK27k1o2i7Zp1LZ7YCuinC+t6Dy5VTcCpChMAmqUk5qw\nYoeilN" +
           "OQGg11bGxNWaTFNLSxIc3gQZbiwb3aKofelvCqHII7j52pe/BESUOAlIZJDd" +
           "V1g1OuGvom\njcVtTmrDe2mShhJc1UJh1eatYXIT0xPxNkO3OdW5fQ+5jxSH" +
           "SZmpIE1OGsNp5iFgHjKpReMhwT7U\nKdgChSkW41TVWXR9hh1gLs3GhGM7eF" +
           "250EBkEr7sA3HECUDq+RmppbQ5oprFp/pW7zv+bDGp6Sc1\nqt6NxBSQhAO/" +
           "flIdZ/FBZtnro1EW7SeTdcai3cxSqaaOC679pM5Wh3TKExazu5htaEkErLMT" +
           "JrME\nz/RmmFQrKJOVULhhZXQUU5kWTf8rjWl0CMSe7ootxd2M+yBgpQoHs2" +
           "JUYWmUkhFVB4s3+DEyMjZv\nBQBALY8zPmxkWJXoFDZInbSlRvWhUDe3VH0I" +
           "QEuNBHDhZFZBoqhrkyojdIhFOJnhh+uUrwCqQigC\nUTiZ5gcTlMBKs3xW8t" +
           "inZfpnB059/9V1wrdLokzR8PyVgDTPh9TFYsxiusIk4rVE8DsduxJzAoQA\n" +
           "8DQfsIRZv/BMb/jjXzVImNl5YHYM7mUKjyjbDzd03XunQYrxGJNMw1bR+FmS" +
           "i3DodN60pkyI2ukZ\nivgymH55tuuNXQ88xz4JkMoOUqYYWiIOfjRZMeKmqj" +
           "HrTqYzi3IW7SAVTI+2ifcdpBzWYXB5ubsj\nFrMZ7yAlmtgqM8R/UFEMSKCK" +
           "KmCt6jEjvTYpHxbrlEkImQIX2QzX40T+xJOTdcGQndBjmjEasi0l\nZFhDmf" +
           "+KYbEQKDgKWrZCnZYxBB5vq0nW5ewF0ZNMTgZCw0achahCdVU3QkMqxK5iLI" +
           "uyJD6/IP0U\nylA3WlSESdEf3BrERbuhAXREOXn1rX2btn7zgHQcdHZHek5u" +
           "B7ZBh20Q2QbTbIN52DZ3x6mmbUgo\nI+DCRUWC81Q8ijQqmGQEghvSYPXi7j" +
           "1b7j7QVAzeZI6WgD4RtAlkds63STHa3AzQIZKlAm4445nd\n+4PXTq6Vbhgq" +
           "nKjzYlddfOHC8b9XLwmQQP4sinJDHq9EMp2YejPZsdkfd/no//XRbS9fuvDu" +
           "rW4E\nctKckxhyMTGwm/wWsgyFRSFVuuRPzKwp3kn6DgdICWQLyJDi/JB85v" +
           "l5ZAV4azpZoizlYVIVMyyw\nE75KZ7hKPmwZo+6OcJ1avE2VXoSG9B1Q5Nlr" +
           "D5ct/+MrVeeFxOmUXOMpet2MywCf7PpBj8UY7L/7\n3c5vP/np/t3CCRwv4K" +
           "TctNQkhHQKcG5xcSC0NUgvaKTmXj1uRNWYSgc1ht7075qFt/30Lwdrpdo1\n" +
           "2Elbben1Cbj7MzeQBy587R/zBJkiBUuLK4cLJsWZ4lJeb1l0DM+RevD3c4/8" +
           "mh6FzAfZxlbHmUgg\nRIhGhCKDQr+LxX2Z791yvDUB7aUF3DpPIY8o+54bak" +
           "rc85ufi1NXUW9H4LXDNmq2SqsK3pjU2olz\ny0ps+HaaiffpaIJ6f/i2U3sY" +
           "iK08u32gVjv7L2DbD2wVqLL2DguyQCrL1A50afnl185Nv/udYhLY\nTCo1g0" +
           "Y3UxEApAI8j9nDkIlS5tp14hi1o5PwLvRCxGlnOVpKef6VwOEWF47/zdgruK" +
           "ETGVx6KvzW\njqNCSwUjP0+p9NEZf7X32LXf8iuCjhuCiN2Yyk2z0F+5uF+5" +
           "lJxc9tLT8QAp7ye1itMB9lEtgdHQ\nDw2LnW4LoUvMep/dfMhK25pJMXP84e" +
           "9h6w9+N73DGqFxXe3zDNQ/qYfroOMZB/2eUUTEYq1AWSDu\nt5iOlSD1o+fM" +
           "8PbhlhqHep4U+ejqI02/fLP36f0yh09gwyysiHL/e++PFB96bVDi+Q3lAz48" +
           "78SH\nL1/tmipzgmz8FuT0Xl4c2fwJTdSYKEDjRBwE9Ostjafv67rinKguu4" +
           "XZBG3+R2Pn2KI7Hv8gT2Ut\nhvZUpla834a3ddK/VxWMgzU3bqEdeFsPJhnD" +
           "RbuPY+f/gWOPw1EkwHxMe2+Q6Qy4DjlMDxVgusth\nWiHnBmrLyWqjKXl1cI" +
           "wCg/o13j/BUVK5vh3A9a2clNli9PGmI+xfZns8vjsxaHNPc75q3ZeX/rh9\n" +
           "4JxoPSpgJqL2djcSYRLFVRF40ZLC8eCnaZ3rOc+uTnsqXyCJwdFB9eM9tfiT" +
           "ZMvyh46Ks5QMUlsc\noxIyhY2QnMwvPIQKWjJKbpJm+g/8CFyf44WqExuiRZ" +
           "7l5qw26NiBDBTcoJh0Tccy9dzpEBEq6ELh\nOw10WufXaUSpHzB7+8i3BqTY" +
           "jdfRV0SZ9fYvLu283GeKIKxJqjBZsGiPM/1mV3m3Q23NmojzajSi\n/Klpz5" +
           "Xvlbx3RIw6Unl47hXQIeBzteNW1Rm3Qi+p90jknEI0In/+3ZGp37AOGMIqpc" +
           "JDcr1jYQFp\nvYQiysGPN3z+QHhkUQAzfiUWCWrBjARDWLDQRwIvgeYeWG0E" +
           "LCgeN0tsGHaFAzmOUpfZzbSpnCwr\nRFukcl83669L0BSMMmuDkdCFrI3Zla" +
           "oyYZret8L/bv6C/mfiDzJjT9oRa11HlCUWIl3YYaVpfx2t\nR2Z6bLfd0MX4" +
           "qSqou1S6VRLoFotht4hNfGrsg0WX579d23ZB9pXDnCz01HIHMtShJw1FtCLt" +
           "VI/C\naCvbzDl5Ge60KOrj4vsf7jnU8tEbGAhmVnOERbhSHH3Fl1avWCnK2d" +
           "xCXw5EKdt/19+qH6Gv7wk4\nzegWkN35oJOd5OZmDWnbxIcSt8d59Nnnz/B3" +
           "WtbI2Jwgl/kRl6w5Pt6w5sXH/ofRrMEnmJ/05OTs\nrxYPq28GxLcc2TLlfA" +
           "PKRmr1uR+cJ2HpPVnt0vxMlapBjbfAddipUof9VcotO1n1BMadMjMxqKVL\n" +
           "iTsXFDn6xv/3i/vDgsiTE8wPR/B2SNRA9HXWIz5otLvV7onrFd5Mn81JlWeU" +
           "T8fHshv6GIAtYM4n\nRfkZTAlfvnfgs/Af/ikm2cynqqowmRRLaJq3RfWsy0" +
           "wIFlWIWiUbVlM8nuFkZsGTcTIpvRRi/EDi\n/BCi3Y8DLQs+vGCnQBEeMJhO" +
           "nZUX6Hlo5gAIl6fNgrkkOzwxthcULC/bEvJbb0S564Xd81OP9Twh\niwJkrv" +
           "FxJwWXyyk+EwWNBamlaV0kL73Y98qPbk+HeNZ87z2c6zFTJ/IYiLz84/WmuM" +
           "nFQDz+s/qf\n3HHy2BWRoMz/AsKH3/SgFwAA");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1163562052000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVZDWwUxxWeO5//zgYbG9v8GxsnBDB3mJ+AcSQw2ASTA4xt" +
       "TGIMl/Xe3HnD3u5m\nd+58OIgmTQUU2jSoadRUhdAqEj9NGpS0oolSAiK0KT" +
       "RVkqpEShvSlqqNQqkaVUqoUqV9M7N7u7d3\nZyAVJ+3e3s68N/P+vvfe3PPX" +
       "UKGho2miESA7NWwEVvd2C7qBI6tlwTD64FVYPF9Y2n30PkX1Ik8I\neaUIQR" +
       "Uh0QhGBCIEpUiwq6MtpaN5mirvjMkqCeAUCTwkLzH5rQstyWK45fCpqsee89" +
       "V7UWEIVQiK\nohKBSKrSKeO4QVBl6CEhKQQTRJKDIckgbSE0DiuJ+GpVMYig" +
       "EONhtBsVhFCRJlKeBDWErMWDsHhQ\nE3QhHmTLB7vZssChWsdEkBQcaU8vB5" +
       "TNmZSwbZOuJ3s2MCmhg/0gDtsBSD0zLTWXNktUreBY/927\njhwvQBUDqEJS" +
       "eikzESQhsN4AKo/j+BDWjfZIBEcG0AQF40gv1iVBlkbZqgOoypBiikASOjZ6" +
       "sKHK\nSTqxykhoWGdrWi9DqFykMukJkah6WkdRCcsR61dhVBZiIHatLTYXdw" +
       "19DwL6JdiYHhVEbJH4dkgK\nWLzeTZGWsek+mACkxXFMhtX0Uj5FgBeoittS" +
       "FpRYsJfokhKDqYVqAlYhaEpeplTXmiDuEGI4TNAk\n97xuPgSzSpkiKAlBNe" +
       "5pjBNYaYrLSg77zKv9dN+x759eyXzbF8GiTPfvB6IZLqIeHMU6VkTMCa8n\n" +
       "Ak91PZCY5kUIJte4JvM57Xec2hz66PV6Pmdqjjkbhx7CIgmLGw7W9zxyr4oK" +
       "6DZKNNWQqPEzJGfh\n0G2OtKU0iNraNEc6GLAGz/T84oFHT+CrXuTvQkWiKi" +
       "fi4EcTRDWuSTLW78UK1gWCI12oFCuR1Wy8\nCxXDcwhcnr/dGI0amHQhn8xe" +
       "FansN6goCiyoikrhWVKiqvWsCWSYPac0hFAxXGgZXBMQ/7BvglYG\ngkZCic" +
       "rqSNDQxaCqx9K/RVXHQVBwBLSsB7t1NQYeb0hJ3GO+C1BP0ggaDA6rcRwURE" +
       "GRFDUYkyB2\nRXV+BCfp9//JP0VlqBrxeCgouoNbhrhYq8owOywevXJhV+d9" +
       "X9/HHYc6uyk9QfNh2YC5bIAuG7CW\nDeRYFnk8bLWJdHluSDDDDghogL7yOb" +
       "3b1j24r7EAPEgb8YEO6dRGkNPcU6eorrajvosBpAiuN+mH\nW/cGrh9dwV0v" +
       "mB+cc1KXvf3CxSP/Kp/rRd7cyEllBez2UzbdFG7TiNjkjrVc/P+xf/3Lly5+" +
       "cJcd\ndQQ1ZYFBNiUN5ka3VXRVxBGAR5v9c5MrCrag/oNe5AOEAFRk+wfAme" +
       "FeIyOo2yyApLIUh1BZVNXj\ngkyHLFTzk2FdHbHfMHepZM/VYJwy6uUz4Jps" +
       "uj37pqM1Gr3Xcvei1nZJwQD4+uNFC957rew8U4uF\n1RWObNiLCY/8Cbaz9O" +
       "kYw/sPvtv97e9c27uVeYrpKgRSZGJIlsQUkNxpk0DIywA71JBNm5W4GpGi\n" +
       "kjAkY+px/6m4o+Wnf3+ikptGhjeWZZtvzMB+P3kVevTi9s9mMDYekaYcWwx7" +
       "Gpem2ubcruvCTrqP\n1GPvTn/ml8IhQERAIUMaxQxYEJMMMT0Gmd7nsnvANd" +
       "ZCb43AuzmP6+dI8GFx14lYY+LhX73Cdl0m\nOCsFpxnWC1obtzy9zaLarXNH" +
       "71rBGIZ5i89sGKyUz3wOHAeAowiJ1dioQ+CnMoxozi4sfv/sudoH\n3ylA3j" +
       "XIL6tCZI3A/B+VguNhYxjAJ6WtWMl8q3KkhN6ZyIgpYYqpgJTjVwlsbk7+8F" +
       "9DywM7csJD\nzcdCFzYeYgrIG/g5sqOLz+jpzYevv0UuMz52BFLqhlQ2skJJ" +
       "ZdMuu5ScUHTy2bgXFQ+gStEs+voF\nOUH9fABqFMOqBKEwzBjPrDd4cm1LI8" +
       "w0d/Q7lnXHvo3o8Exn0+dyV7iXU21PgqvKDPcqd7h7EHtY\nwUia2H12OjiL" +
       "NV1KCrQQhBpchKzMxusgVrISSC8d5thB7wvpbSU385K87rA8c6N3wVVtbrQ6" +
       "z0a7\n6K2dgI7jUF9tgVp/GPxnkrM70KU4VBlJhphX9jT+/M3Nz+7lWWYMN8" +
       "ugCotf+fCPOwq+dXaI07l9\nyTX54Izn/vrylZ6JHJF4OTorqyJ00vCSlBmr" +
       "QqPR2TDWCmz2G/Mant/dc9ncUVVmYdUJzcffdp7D\ns+/55p9y5PsCKJpdtl" +
       "l3G2yzxbRNGbPNWizFhtmy3a6177/FtakDTzTXnphn7UFz7UJoRuICzV8O\n" +
       "l2CYTTV5/MmO6p7WrY+zsqEUehjB2GCHEXSO9MkD+r0jv6ekmYXF2dtO/fPs" +
       "aTybYWeJZEC4tuux\nHPW8g+YT4QRe/170MEv9viHB4IHrboSy+5yM9oW5zn" +
       "iNfXWn84vHLMCYDjVLNZHs2PaCoqKSIrBe\nYg6Ed5GMlRivj5m1hrRUmquX" +
       "k1ixz3MhxS9oU1QF07RqjfESUVID6RYRBlNZ+9PR9IwCcT2TzAbY\n/cd/dI" +
       "q8M2859/W5+W3hJpy7/Mho/fIXD3yJsrDeZTI36wnJqZsKhqU3vax35Hid1X" +
       "NmErVlorQf\n9pPQlb4MrJ7JbThEb3eOUS4kxhgboTeoAApFag9uPtBxfe5y" +
       "qDOuEVbAjP6s7if3HD18mWpZS0Hv\n42cB0bK4dekiIK+CAKInKAEpYibPjn" +
       "fXDJ2IKiciTAd+5q/tlMQUsJS9cQRUgaoZtEV0nMWYnJo2\nagatlcc5Funq" +
       "2HVyXXnJDw7sYfzNaCx1tJvm7+KkoG9w4lsZ33jr4kULWwmK3s72a3nLgmXN" +
       "yxbP\nXwYL+fvWdvUGGODQrTxioc9uaHyzlUdFRnYaBuHH25FEAdw5CEL5ej" +
       "rbO1zIue0WkZNOqzHZ1uRB\nzv0mclYa0ErIqxLiDkw2JXACgw9MdYBob2LI" +
       "II6DiSUrlza/tHbw3A2xdIz4dfPUz/Wdx1dqns6V\nrtmhmUnqpnt6ztXkvA" +
       "VfPcT2kgZVP5RMBp1J0Mz8B3CMF8/F47j2/gsfBNcX9KJaYy8QPR5YYNfY\n" +
       "UNeJCR0chtDcruoS2blKVkVI/zGmvgA7+9NMIAVwnGvHo00byElLKZ4yYzBD" +
       "/2GxblDb3I++MchV\n1HAD3YbFKb9+9dKW9/s1VhZUJCWIJBzpM08JM7seu6" +
       "tvyzg5zKn9sPiXxm2Xv+f78Bl2JMQVTfe9\nCDom+n236aTlaSelwF/nkMjc" +
       "BWvMPv7NMxO/pu9TmQULBQeq3ERWdjIKi098tOqLR0M7ICtDmeyn\nSC3oGE" +
       "CZoEC+w1Qng6Y+eOoAKoDv8ZwazMKczXSqqvTbdGtP0Px8vFlx6ToBcBfz0E" +
       "mNYH2VmlCY\nrA2uxJHQNOcoT/63xVc1+iHI02k5baWNUbxbsdLEwoVLW1o0" +
       "a1rrLZ0oNfXaUJPRC6JUmv2iu1uW\nsJFdTnxvXXrb8X1RS/PCZfMXLyaohu" +
       "O7Cxbpnk7kgEv6fo8Lrw/cIl5Pg6vWxOvaPHj9konXxRHJ\n0GRhp2WCSVkm" +
       "6OATXHt6+Rb31ARXnbmnujx7esXakwhOSngb7a76X70N65421/Wb667nx6Pu" +
       "pV8f\nY+mUXXlNcZTPoNDpWQrton1NrxDXZKzTrm16vmN71rHtvf+T8j3CG9" +
       "u8ZpnWQ1ApUbX5Mk5i2bFg\nAXtWM8/o1sA105R+plt6Jl6uitHHGPryu8RG" +
       "jRWBN9HHMx12s6XeGqP2/C29XWBHBRhyBO8EQmbS\n2wTvh1RVxoJiG+Pijf" +
       "zAgoJcapkN1xxTLXNuWi2eTMPmjRTG4fIY0v6Z3n4P7RLHEbewvqQqRWxJ\n" +
       "//AlJWXuvwKu7aak2/NK2j62pI3ZLkzDxOC9AP2rDzNe18aQ+RN6+xjQUHND" +
       "5gZIdMxLbJGv3qzI\nAPTVOTCY9i2Tsv6y5H+ziaH3Hxn8NPS7f/PW2forrA" +
       "wahGhClp3nYY7nInDMqMQkKeOnY7zjuk7Q\n5LxJi6AS65Ht+jNO8znAvpsG" +
       "rE6/nNO+gIzlmAZBYD45Jnk8BNojPUYfvek0mpVtM5Mj1c6svGXZ\n+gT/Lz" +
       "ks3v/C1pmpA31P8mIKcvvoKGUDpUsx/8cg3fU25OVm8XobnXyx/7Uft1ooxk" +
       "6UJ6ZshM1w\n4oV8NL8T0IFR7X/M5+rE1R8AAA==");
}
