package org.sunflow.core.renderer;
import org.sunflow.core.BucketOrder;
import org.sunflow.core.Display;
import org.sunflow.core.Filter;
import org.sunflow.core.ImageSampler;
import org.sunflow.core.Instance;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.Options;
import org.sunflow.core.Scene;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.core.bucket.BucketOrderFactory;
import org.sunflow.core.filter.BoxFilter;
import org.sunflow.core.filter.FilterFactory;
import org.sunflow.image.Bitmap;
import org.sunflow.image.Color;
import org.sunflow.math.MathUtils;
import org.sunflow.math.QMC;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class BucketRenderer implements ImageSampler {
    private Scene scene;
    private Display display;
    private int imageWidth;
    private int imageHeight;
    private String bucketOrderName;
    private BucketOrder bucketOrder;
    private int bucketSize;
    private int bucketCounter;
    private int[] bucketCoords;
    private boolean dumpBuckets;
    private int minAADepth;
    private int maxAADepth;
    private int superSampling;
    private float contrastThreshold;
    private boolean jitter;
    private boolean displayAA;
    private double invSuperSampling;
    private int subPixelSize;
    private int minStepSize;
    private int maxStepSize;
    private int[] sigma;
    private float thresh;
    private boolean useJitter;
    private String filterName;
    private Filter filter;
    private int fs;
    private float fhs;
    public BucketRenderer() { super();
                              bucketSize = 32;
                              bucketOrderName = "hilbert";
                              displayAA = false;
                              contrastThreshold = 0.1F;
                              filterName = "box";
                              jitter = false;
                              dumpBuckets = false; }
    public boolean prepare(Options options, Scene scene, int w, int h) { this.
                                                                           scene =
                                                                           scene;
                                                                         imageWidth =
                                                                           w;
                                                                         imageHeight =
                                                                           h;
                                                                         bucketSize =
                                                                           options.
                                                                             getInt(
                                                                               "bucket.size",
                                                                               bucketSize);
                                                                         bucketOrderName =
                                                                           options.
                                                                             getString(
                                                                               "bucket.order",
                                                                               bucketOrderName);
                                                                         minAADepth =
                                                                           options.
                                                                             getInt(
                                                                               "aa.min",
                                                                               minAADepth);
                                                                         maxAADepth =
                                                                           options.
                                                                             getInt(
                                                                               "aa.max",
                                                                               maxAADepth);
                                                                         superSampling =
                                                                           options.
                                                                             getInt(
                                                                               "aa.samples",
                                                                               superSampling);
                                                                         displayAA =
                                                                           options.
                                                                             getBoolean(
                                                                               "aa.display",
                                                                               displayAA);
                                                                         jitter =
                                                                           options.
                                                                             getBoolean(
                                                                               "aa.jitter",
                                                                               jitter);
                                                                         contrastThreshold =
                                                                           options.
                                                                             getFloat(
                                                                               "aa.contrast",
                                                                               contrastThreshold);
                                                                         bucketSize =
                                                                           MathUtils.
                                                                             clamp(
                                                                               bucketSize,
                                                                               16,
                                                                               512);
                                                                         int numBucketsX =
                                                                           (imageWidth +
                                                                              bucketSize -
                                                                              1) /
                                                                           bucketSize;
                                                                         int numBucketsY =
                                                                           (imageHeight +
                                                                              bucketSize -
                                                                              1) /
                                                                           bucketSize;
                                                                         bucketOrder =
                                                                           BucketOrderFactory.
                                                                             create(
                                                                               bucketOrderName);
                                                                         bucketCoords =
                                                                           bucketOrder.
                                                                             getBucketSequence(
                                                                               numBucketsX,
                                                                               numBucketsY);
                                                                         minAADepth =
                                                                           MathUtils.
                                                                             clamp(
                                                                               minAADepth,
                                                                               -4,
                                                                               5);
                                                                         maxAADepth =
                                                                           MathUtils.
                                                                             clamp(
                                                                               maxAADepth,
                                                                               minAADepth,
                                                                               5);
                                                                         superSampling =
                                                                           MathUtils.
                                                                             clamp(
                                                                               superSampling,
                                                                               1,
                                                                               256);
                                                                         invSuperSampling =
                                                                           1.0 /
                                                                             superSampling;
                                                                         subPixelSize =
                                                                           maxAADepth >
                                                                             0
                                                                             ? 1 <<
                                                                             maxAADepth
                                                                             : 1;
                                                                         minStepSize =
                                                                           maxAADepth >=
                                                                             0
                                                                             ? 1
                                                                             : 1 <<
                                                                             -maxAADepth;
                                                                         if (minAADepth ==
                                                                               maxAADepth)
                                                                             maxStepSize =
                                                                               minStepSize;
                                                                         else
                                                                             maxStepSize =
                                                                               minAADepth >
                                                                                 0
                                                                                 ? 1 <<
                                                                                 minAADepth
                                                                                 : subPixelSize <<
                                                                                 -minAADepth;
                                                                         useJitter =
                                                                           jitter &&
                                                                             maxAADepth >
                                                                             0;
                                                                         contrastThreshold =
                                                                           MathUtils.
                                                                             clamp(
                                                                               contrastThreshold,
                                                                               0,
                                                                               1);
                                                                         thresh =
                                                                           contrastThreshold *
                                                                             (float)
                                                                               Math.
                                                                               pow(
                                                                                 2.0F,
                                                                                 minAADepth);
                                                                         filterName =
                                                                           options.
                                                                             getString(
                                                                               "filter",
                                                                               filterName);
                                                                         filter =
                                                                           FilterFactory.
                                                                             get(
                                                                               filterName);
                                                                         if (filter ==
                                                                               null) {
                                                                             UI.
                                                                               printWarning(
                                                                                 Module.
                                                                                   BCKT,
                                                                                 "Unrecognized filter type: \"%s\" - defaulting to box",
                                                                                 filterName);
                                                                             filter =
                                                                               new BoxFilter(
                                                                                 1);
                                                                             filterName =
                                                                               "box";
                                                                         }
                                                                         fhs =
                                                                           filter.
                                                                             getSize(
                                                                               ) *
                                                                             0.5F;
                                                                         fs =
                                                                           (int)
                                                                             Math.
                                                                             ceil(
                                                                               subPixelSize *
                                                                                 (fhs -
                                                                                    0.5F));
                                                                         sigma =
                                                                           QMC.
                                                                             generateSigmaTable(
                                                                               subPixelSize <<
                                                                                 7);
                                                                         UI.
                                                                           printInfo(
                                                                             Module.
                                                                               BCKT,
                                                                             "Bucket renderer settings:");
                                                                         UI.
                                                                           printInfo(
                                                                             Module.
                                                                               BCKT,
                                                                             "  * Resolution:         %dx%d",
                                                                             imageWidth,
                                                                             imageHeight);
                                                                         UI.
                                                                           printInfo(
                                                                             Module.
                                                                               BCKT,
                                                                             "  * Bucket size:        %d",
                                                                             bucketSize);
                                                                         UI.
                                                                           printInfo(
                                                                             Module.
                                                                               BCKT,
                                                                             "  * Number of buckets:  %dx%d",
                                                                             numBucketsX,
                                                                             numBucketsY);
                                                                         if (minAADepth !=
                                                                               maxAADepth)
                                                                             UI.
                                                                               printInfo(
                                                                                 Module.
                                                                                   BCKT,
                                                                                 "  * Anti-aliasing:      %s -> %s (adaptive)",
                                                                                 aaDepthToString(
                                                                                   minAADepth),
                                                                                 aaDepthToString(
                                                                                   maxAADepth));
                                                                         else
                                                                             UI.
                                                                               printInfo(
                                                                                 Module.
                                                                                   BCKT,
                                                                                 "  * Anti-aliasing:      %s (fixed)",
                                                                                 aaDepthToString(
                                                                                   minAADepth));
                                                                         UI.
                                                                           printInfo(
                                                                             Module.
                                                                               BCKT,
                                                                             "  * Rays per sample:    %d",
                                                                             superSampling);
                                                                         UI.
                                                                           printInfo(
                                                                             Module.
                                                                               BCKT,
                                                                             "  * Subpixel jitter:    %s",
                                                                             useJitter
                                                                               ? "on"
                                                                               : (jitter
                                                                                    ? "auto-off"
                                                                                    : "off"));
                                                                         UI.
                                                                           printInfo(
                                                                             Module.
                                                                               BCKT,
                                                                             "  * Contrast threshold: %.2f",
                                                                             contrastThreshold);
                                                                         UI.
                                                                           printInfo(
                                                                             Module.
                                                                               BCKT,
                                                                             "  * Filter type:        %s",
                                                                             filterName);
                                                                         UI.
                                                                           printInfo(
                                                                             Module.
                                                                               BCKT,
                                                                             "  * Filter size:        %.2f pixels",
                                                                             filter.
                                                                               getSize(
                                                                                 ));
                                                                         return true;
    }
    private String aaDepthToString(int depth) {
        int pixelAA =
          depth <
          0
          ? -(1 <<
                -depth)
          : 1 <<
          depth;
        return String.
          format(
            "%s%d sample%s",
            depth <
              0
              ? "1/"
              : "",
            pixelAA *
              pixelAA,
            depth ==
              0
              ? ""
              : "s");
    }
    public void render(Display display) {
        this.
          display =
          display;
        display.
          imageBegin(
            imageWidth,
            imageHeight,
            bucketSize);
        bucketCounter =
          0;
        UI.
          taskStart(
            "Rendering",
            0,
            bucketCoords.
              length);
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
                i);
            renderThreads[i].
              setPriority(
                scene.
                  getThreadPriority(
                    ));
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
        UI.
          taskStop(
            );
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
        private int threadID;
        BucketThread(int threadID) { super(
                                       );
                                     this.
                                       threadID =
                                       threadID;
        }
        public void run() { IntersectionState istate =
                              new IntersectionState(
                              );
                            while (true) {
                                int bx;
                                int by;
                                synchronized (BucketRenderer.this)  {
                                    if (bucketCounter >=
                                          bucketCoords.
                                            length)
                                        return;
                                    UI.
                                      taskUpdate(
                                        bucketCounter);
                                    bx =
                                      bucketCoords[bucketCounter +
                                                     0];
                                    by =
                                      bucketCoords[bucketCounter +
                                                     1];
                                    bucketCounter +=
                                      2;
                                }
                                renderBucket(
                                  display,
                                  bx,
                                  by,
                                  threadID,
                                  istate);
                                if (UI.
                                      taskCanceled(
                                        ))
                                    return;
                            } }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "/gF2mbHvbRAAAA==");
    }
    private void renderBucket(Display display,
                              int bx,
                              int by,
                              int threadID,
                              IntersectionState istate) {
        int x0 =
          bx *
          bucketSize;
        int y0 =
          by *
          bucketSize;
        int bw =
          Math.
          min(
            bucketSize,
            imageWidth -
              x0);
        int bh =
          Math.
          min(
            bucketSize,
            imageHeight -
              y0);
        display.
          imagePrepare(
            x0,
            y0,
            bw,
            bh,
            threadID);
        Color[] bucketRGB =
          new Color[bw *
                      bh];
        int sx0 =
          x0 *
          subPixelSize -
          fs;
        int sy0 =
          y0 *
          subPixelSize -
          fs;
        int sbw =
          bw *
          subPixelSize +
          fs *
          2;
        int sbh =
          bh *
          subPixelSize +
          fs *
          2;
        sbw =
          sbw +
            (maxStepSize -
               1) &
            ~(maxStepSize -
                1);
        sbh =
          sbh +
            (maxStepSize -
               1) &
            ~(maxStepSize -
                1);
        if (maxStepSize >
              1) {
            sbw++;
            sbh++;
        }
        ImageSample[] samples =
          new ImageSample[sbw *
                            sbh];
        float invSubPixelSize =
          1.0F /
          subPixelSize;
        for (int y =
               0,
               index =
                 0;
             y <
               sbh;
             y++) {
            for (int x =
                   0;
                 x <
                   sbw;
                 x++,
                   index++) {
                int sx =
                  sx0 +
                  x;
                int sy =
                  sy0 +
                  y;
                int j =
                  sx &
                  sigma.
                    length -
                  1;
                int k =
                  sy &
                  sigma.
                    length -
                  1;
                int i =
                  j *
                  sigma.
                    length +
                  sigma[k];
                float dx =
                  useJitter
                  ? (float)
                      sigma[k] /
                  (float)
                    sigma.
                      length
                  : 0.5F;
                float dy =
                  useJitter
                  ? (float)
                      sigma[j] /
                  (float)
                    sigma.
                      length
                  : 0.5F;
                float rx =
                  (sx +
                     dx) *
                  invSubPixelSize;
                float ry =
                  (sy +
                     dy) *
                  invSubPixelSize;
                ry =
                  imageHeight -
                    ry -
                    1;
                samples[index] =
                  new ImageSample(
                    rx,
                    ry,
                    i);
            }
        }
        for (int x =
               0;
             x <
               sbw -
               1;
             x +=
               maxStepSize)
            for (int y =
                   0;
                 y <
                   sbh -
                   1;
                 y +=
                   maxStepSize)
                refineSamples(
                  samples,
                  sbw,
                  x,
                  y,
                  maxStepSize,
                  thresh,
                  istate);
        if (dumpBuckets) {
            UI.
              printInfo(
                Module.
                  BCKT,
                "Dumping bucket [%d, %d] to file ...",
                bx,
                by);
            Bitmap bitmap =
              new Bitmap(
              sbw,
              sbh,
              true);
            for (int y =
                   sbh -
                   1,
                   index =
                     0;
                 y >=
                   0;
                 y--)
                for (int x =
                       0;
                     x <
                       sbw;
                     x++,
                       index++)
                    bitmap.
                      setPixel(
                        x,
                        y,
                        samples[index].
                          c.
                          copy(
                            ).
                          toNonLinear(
                            ));
            bitmap.
              save(
                String.
                  format(
                    "bucket_%04d_%04d.png",
                    bx,
                    by));
        }
        if (displayAA) {
            float invArea =
              invSubPixelSize *
              invSubPixelSize;
            for (int y =
                   0,
                   index =
                     0;
                 y <
                   bh;
                 y++) {
                for (int x =
                       0;
                     x <
                       bw;
                     x++,
                       index++) {
                    int sampled =
                      0;
                    for (int i =
                           0;
                         i <
                           subPixelSize;
                         i++) {
                        for (int j =
                               0;
                             j <
                               subPixelSize;
                             j++) {
                            int sx =
                              x *
                              subPixelSize +
                              fs +
                              i;
                            int sy =
                              y *
                              subPixelSize +
                              fs +
                              j;
                            int s =
                              sx +
                              sy *
                              sbw;
                            sampled +=
                              samples[s].
                                sampled(
                                  )
                                ? 1
                                : 0;
                        }
                    }
                    bucketRGB[index] =
                      new Color(
                        sampled *
                          invArea);
                }
            }
        }
        else {
            float cy =
              imageHeight -
              1 -
              (y0 +
                 0.5F);
            for (int y =
                   0,
                   index =
                     0;
                 y <
                   bh;
                 y++,
                   cy--) {
                float cx =
                  x0 +
                  0.5F;
                for (int x =
                       0;
                     x <
                       bw;
                     x++,
                       index++,
                       cx++) {
                    Color c =
                      Color.
                      black(
                        );
                    float weight =
                      0.0F;
                    for (int j =
                           -fs,
                           sy =
                             y *
                             subPixelSize;
                         j <=
                           fs;
                         j++,
                           sy++) {
                        for (int i =
                               -fs,
                               sx =
                                 x *
                                 subPixelSize,
                               s =
                                 sx +
                                 sy *
                                 sbw;
                             i <=
                               fs;
                             i++,
                               sx++,
                               s++) {
                            float dx =
                              samples[s].
                                rx -
                              cx;
                            if (Math.
                                  abs(
                                    dx) >
                                  fhs)
                                continue;
                            float dy =
                              samples[s].
                                ry -
                              cy;
                            if (Math.
                                  abs(
                                    dy) >
                                  fhs)
                                continue;
                            float f =
                              filter.
                              get(
                                dx,
                                dy);
                            c.
                              madd(
                                f,
                                samples[s].
                                  c);
                            weight +=
                              f;
                        }
                    }
                    c.
                      mul(
                        1.0F /
                          weight);
                    bucketRGB[index] =
                      c;
                }
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
    private void computeSubPixel(ImageSample sample,
                                 IntersectionState istate) {
        float x =
          sample.
            rx;
        float y =
          sample.
            ry;
        double q0 =
          QMC.
          halton(
            1,
            sample.
              i);
        double q1 =
          QMC.
          halton(
            2,
            sample.
              i);
        double q2 =
          QMC.
          halton(
            3,
            sample.
              i);
        if (superSampling >
              1) {
            sample.
              add(
                scene.
                  getRadiance(
                    istate,
                    x,
                    y,
                    q1,
                    q2,
                    q0,
                    sample.
                      i));
            for (int i =
                   1;
                 i <
                   superSampling;
                 i++) {
                double time =
                  QMC.
                  mod1(
                    q0 +
                      i *
                      invSuperSampling);
                double lensU =
                  QMC.
                  mod1(
                    q1 +
                      QMC.
                      halton(
                        0,
                        i));
                double lensV =
                  QMC.
                  mod1(
                    q2 +
                      QMC.
                      halton(
                        1,
                        i));
                sample.
                  add(
                    scene.
                      getRadiance(
                        istate,
                        x,
                        y,
                        lensU,
                        lensV,
                        time,
                        sample.
                          i +
                          i));
            }
            sample.
              scale(
                (float)
                  invSuperSampling);
        }
        else {
            sample.
              set(
                scene.
                  getRadiance(
                    istate,
                    x,
                    y,
                    q1,
                    q2,
                    q0,
                    sample.
                      i));
        }
    }
    private void refineSamples(ImageSample[] samples,
                               int sbw,
                               int x,
                               int y,
                               int stepSize,
                               float thresh,
                               IntersectionState istate) {
        int dx =
          stepSize;
        int dy =
          stepSize *
          sbw;
        int i00 =
          x +
          y *
          sbw;
        ImageSample s00 =
          samples[i00];
        ImageSample s01 =
          samples[i00 +
                    dy];
        ImageSample s10 =
          samples[i00 +
                    dx];
        ImageSample s11 =
          samples[i00 +
                    dx +
                    dy];
        if (!s00.
              sampled(
                ))
            computeSubPixel(
              s00,
              istate);
        if (!s01.
              sampled(
                ))
            computeSubPixel(
              s01,
              istate);
        if (!s10.
              sampled(
                ))
            computeSubPixel(
              s10,
              istate);
        if (!s11.
              sampled(
                ))
            computeSubPixel(
              s11,
              istate);
        if (stepSize >
              minStepSize) {
            if (s00.
                  isDifferent(
                    s01,
                    thresh) ||
                  s00.
                  isDifferent(
                    s10,
                    thresh) ||
                  s00.
                  isDifferent(
                    s11,
                    thresh) ||
                  s01.
                  isDifferent(
                    s11,
                    thresh) ||
                  s10.
                  isDifferent(
                    s11,
                    thresh) ||
                  s01.
                  isDifferent(
                    s10,
                    thresh)) {
                stepSize >>=
                  1;
                thresh *=
                  2;
                refineSamples(
                  samples,
                  sbw,
                  x,
                  y,
                  stepSize,
                  thresh,
                  istate);
                refineSamples(
                  samples,
                  sbw,
                  x +
                    stepSize,
                  y,
                  stepSize,
                  thresh,
                  istate);
                refineSamples(
                  samples,
                  sbw,
                  x,
                  y +
                    stepSize,
                  stepSize,
                  thresh,
                  istate);
                refineSamples(
                  samples,
                  sbw,
                  x +
                    stepSize,
                  y +
                    stepSize,
                  stepSize,
                  thresh,
                  istate);
                return;
            }
        }
        float ds =
          1.0F /
          stepSize;
        for (int i =
               0;
             i <=
               stepSize;
             i++)
            for (int j =
                   0;
                 j <=
                   stepSize;
                 j++)
                if (!samples[x +
                               i +
                               (y +
                                  j) *
                               sbw].
                      processed(
                        ))
                    ImageSample.
                      bilerp(
                        samples[x +
                                  i +
                                  (y +
                                     j) *
                                  sbw],
                        s00,
                        s01,
                        s10,
                        s11,
                        i *
                          ds,
                        j *
                          ds);
    }
    private static final class ImageSample {
        float rx;
        float ry;
        int i;
        int n;
        Color c;
        Instance instance;
        Shader shader;
        float nx;
        float ny;
        float nz;
        ImageSample(float rx, float ry, int i) {
            super(
              );
            this.
              rx =
              rx;
            this.
              ry =
              ry;
            this.
              i =
              i;
            n =
              0;
            c =
              null;
            instance =
              null;
            shader =
              null;
            nx =
              (ny =
                 (nz =
                    1));
        }
        final void set(ShadingState state) {
            if (state ==
                  null)
                c =
                  Color.
                    BLACK;
            else {
                c =
                  state.
                    getResult(
                      );
                checkNanInf(
                  );
                shader =
                  state.
                    getShader(
                      );
                instance =
                  state.
                    getInstance(
                      );
                if (state.
                      getNormal(
                        ) !=
                      null) {
                    nx =
                      state.
                        getNormal(
                          ).
                        x;
                    ny =
                      state.
                        getNormal(
                          ).
                        y;
                    nz =
                      state.
                        getNormal(
                          ).
                        z;
                }
            }
            n =
              1;
        }
        final void add(ShadingState state) {
            if (n ==
                  0)
                c =
                  Color.
                    black(
                      );
            if (state !=
                  null) {
                c.
                  add(
                    state.
                      getResult(
                        ));
                checkNanInf(
                  );
            }
            n++;
        }
        final void checkNanInf() { if (c.
                                         isNan(
                                           ))
                                       UI.
                                         printError(
                                           Module.
                                             BCKT,
                                           "NaN shading sample!");
                                   else
                                       if (c.
                                             isInf(
                                               ))
                                           UI.
                                             printError(
                                               Module.
                                                 BCKT,
                                               "Inf shading sample!");
        }
        final void scale(float s) { c.mul(
                                        s);
        }
        final boolean processed() { return c !=
                                      null;
        }
        final boolean sampled() { return n >
                                    0; }
        final boolean isDifferent(ImageSample sample,
                                  float thresh) {
            if (instance !=
                  sample.
                    instance)
                return true;
            if (shader !=
                  sample.
                    shader)
                return true;
            if (Color.
                  hasContrast(
                    c,
                    sample.
                      c,
                    thresh))
                return true;
            float dot =
              nx *
              sample.
                nx +
              ny *
              sample.
                ny +
              nz *
              sample.
                nz;
            return dot <
              0.9F;
        }
        static final ImageSample bilerp(ImageSample result,
                                        ImageSample i00,
                                        ImageSample i01,
                                        ImageSample i10,
                                        ImageSample i11,
                                        float dx,
                                        float dy) {
            float k00 =
              (1.0F -
                 dx) *
              (1.0F -
                 dy);
            float k01 =
              (1.0F -
                 dx) *
              dy;
            float k10 =
              dx *
              (1.0F -
                 dy);
            float k11 =
              dx *
              dy;
            Color c00 =
              i00.
                c;
            Color c01 =
              i01.
                c;
            Color c10 =
              i10.
                c;
            Color c11 =
              i11.
                c;
            Color c =
              Color.
              mul(
                k00,
                c00);
            c.
              madd(
                k01,
                c01);
            c.
              madd(
                k10,
                c10);
            c.
              madd(
                k11,
                c11);
            result.
              c =
              c;
            return result;
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "5idrL9/3wvzb4uPCfwHC0jRwbBgAAA==");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "HwAA");
}
