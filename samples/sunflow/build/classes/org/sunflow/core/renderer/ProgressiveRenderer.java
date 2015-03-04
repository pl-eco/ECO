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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "h76Y+/nac2N3xd31L05x7pSVDgAA");
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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "/8m6Hz/w0soV3kz9F8XlyewgFQAA");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
