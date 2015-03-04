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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "AAA=");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "T6a/WLnYyVjTsakT3wv8vOH9N/4Hjn64OIofAAA=");
}
