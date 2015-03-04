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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "3hdPlP8A3Z0XhIEUAAA=");
}
