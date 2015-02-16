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
          1170392946000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "XRAAAA==");
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
          1170392946000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "jwv/BSZy7ytcGAAA");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1170392946000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "YGJ1cG3gxDYEEPslJxaOm9MYwny4ujhaVwDarzhaUTxpepRGk1Wb53gdsoVq" +
       "zBGqn9Td1G+xQQ4MjUEvfulh4eyRE1OxgXOrwjwjDoKGLU3/ioInsOIhWErv" +
       "M45EdbYyNnOJNvslopy7fLsJuJgCFgcfBgd0p+K4z9nTKAX8bYFk/jvSvEXP" +
       "uFgXDWpJk7lvKfIJQirnY1yQY4GCdPmI82LM5e69Atz9kTTvwgFGFGl+HNbc" +
       "c8ruabmk78tITn+cc/n4jLfbw+VGOuv9Alz+lTTXwHXYu9d8Yb94QpNj0zJM" +
       "LT4O11XO8NUZb2sJRSyhDLvqp41tM625Vk8KfpNVauT7Eqb4HxYQ9SPS/AMy" +
       "DxOVVV6k728zE247bCpP0qGcJB0onO8osHamL73bPG5N1t6kJG4XkO9T0vwL" +
       "DI5880hbeIgn2C8h4hEQrYeL2DNjEcsoYhl9lh1AerDvmA7wY13XkU/pTnPS" +
       "ETyEggUPFZPOz6FsNDAczviemQFiw8ltTvZOkwNAfc4nTfYZTrowVV2+YGrn" +
       "H9j50/5UVhFF5fG0onhf4nnuS3XCCZWvgr3SowE9VGGhhYH6t1C5fUuYDpWz" +
       "NVVQnPnXgE+SP++0aihRPNMgKPI776RayDMwidzW6bZFel6ksBeaGeRJA+SV" +
       "ufcp6/05OVzST8b2QTDNPhqPShentm5/8NZXz9FTJRy2xIMHCUo5nMvZpwPn" +
       "MLkkEM3GKt3S/tmclyuW2RlrDmlq+fcCL2/k/rH/AeY2JhygHwAA");
}
