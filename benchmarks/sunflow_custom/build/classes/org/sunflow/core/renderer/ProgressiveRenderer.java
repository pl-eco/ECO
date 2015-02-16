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
          1163562052000L;
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
          1163562052000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1163562052000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
