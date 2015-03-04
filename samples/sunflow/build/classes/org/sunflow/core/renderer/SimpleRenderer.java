package org.sunflow.core.renderer;
import org.sunflow.core.Display;
import org.sunflow.core.ImageSampler;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.Options;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class SimpleRenderer implements ImageSampler {
    private Scene scene;
    private Display display;
    private int imageWidth;
    private int imageHeight;
    private int numBucketsX;
    private int numBucketsY;
    private int bucketCounter;
    private int numBuckets;
    public boolean prepare(Options options, Scene scene, int w, int h) { this.
                                                                           scene =
                                                                           scene;
                                                                         imageWidth =
                                                                           w;
                                                                         imageHeight =
                                                                           h;
                                                                         numBucketsX =
                                                                           imageWidth +
                                                                             31 >>>
                                                                             5;
                                                                         numBucketsY =
                                                                           imageHeight +
                                                                             31 >>>
                                                                             5;
                                                                         numBuckets =
                                                                           numBucketsX *
                                                                             numBucketsY;
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
            32);
        bucketCounter =
          0;
        Timer timer =
          new Timer(
          );
        timer.
          start(
            );
        Thread[] renderThreads =
          new Thread[scene.
                       getThreads(
                         )];
        for (int i =
               0;
             i <
               renderThreads.
                 length;
             i++) {
            renderThreads[i] =
              new BucketThread(
                );
            renderThreads[i].
              start(
                );
        }
        for (int i =
               0;
             i <
               renderThreads.
                 length;
             i++) {
            try {
                renderThreads[i].
                  join(
                    );
            }
            catch (InterruptedException e) {
                UI.
                  printError(
                    Module.
                      BCKT,
                    "Bucket processing thread %d of %d was interrupted",
                    i +
                      1,
                    renderThreads.
                      length);
            }
        }
        timer.
          end(
            );
        UI.
          printInfo(
            Module.
              BCKT,
            "Render time: %s",
            timer.
              toString(
                ));
        display.
          imageEnd(
            );
    }
    private class BucketThread extends Thread {
        public void run() { IntersectionState istate =
                              new IntersectionState(
                              );
                            while (true) {
                                int bx;
                                int by;
                                synchronized (SimpleRenderer.this)  {
                                    if (bucketCounter >=
                                          numBuckets)
                                        return;
                                    by =
                                      bucketCounter /
                                        numBucketsX;
                                    bx =
                                      bucketCounter %
                                        numBucketsX;
                                    bucketCounter++;
                                }
                                renderBucket(
                                  bx,
                                  by,
                                  istate);
                            } }
        public BucketThread() { super(); }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "xPob5JFtv3cOAAA=");
    }
    public void renderBucket(int bx, int by,
                             IntersectionState istate) {
        int x0 =
          bx *
          32;
        int y0 =
          by *
          32;
        int bw =
          Math.
          min(
            32,
            imageWidth -
              x0);
        int bh =
          Math.
          min(
            32,
            imageHeight -
              y0);
        Color[] bucketRGB =
          new Color[bw *
                      bh];
        for (int y =
               0,
               i =
                 0;
             y <
               bh;
             y++) {
            for (int x =
                   0;
                 x <
                   bw;
                 x++,
                   i++) {
                ShadingState state =
                  scene.
                  getRadiance(
                    istate,
                    x0 +
                      x,
                    imageHeight -
                      1 -
                      (y0 +
                         y),
                    0.0,
                    0.0,
                    0.0,
                    0);
                bucketRGB[i] =
                  state !=
                    null
                    ? state.
                    getResult(
                      )
                    : Color.
                        BLACK;
            }
        }
        display.
          imageUpdate(
            x0,
            y0,
            bw,
            bh,
            bucketRGB);
    }
    public SimpleRenderer() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
