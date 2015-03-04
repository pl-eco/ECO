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
    private PriorityBlockingQueue<SmallBucket> smallBucketQueue;
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
          new PriorityBlockingQueue<SmallBucket>(
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
          start(
            );
        counter =
          0;
        counterMax =
          imageWidth *
            imageHeight;
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
              new SmallBucketThread(
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
                      IPR,
                    "Thread %d of %d was interrupted",
                    i +
                      1,
                    renderThreads.
                      length);
            }
        }
        UI.
          taskStop(
            );
        t.
          end(
            );
        UI.
          printInfo(
            Module.
              IPR,
            "Rendering time: %s",
            t.
              toString(
                ));
        display.
          imageEnd(
            );
    }
    private class SmallBucketThread extends Thread {
        public void run() { IntersectionState istate =
                              new IntersectionState(
                              );
                            while (true) {
                                int n =
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
                                      taskCanceled(
                                        ))
                                    return;
                            } }
        public SmallBucketThread() { super(
                                       );
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    private int progressiveRenderNext(IntersectionState istate) {
        final int TASK_SIZE =
          16;
        SmallBucket first =
          smallBucketQueue.
          poll(
            );
        if (first ==
              null)
            return 0;
        int ds =
          first.
            size /
          TASK_SIZE;
        boolean useMask =
          !smallBucketQueue.
          isEmpty(
            );
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
                  getResult(
                    )
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
    private static class SmallBucket implements Comparable<SmallBucket> {
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
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "p5iQsBoAAA==");
}
