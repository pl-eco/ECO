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
                                                                             getSize() *
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
                                                                                 this.
                                                                                   aaDepthToString(
                                                                                     minAADepth),
                                                                                 this.
                                                                                   aaDepthToString(
                                                                                     maxAADepth));
                                                                         else
                                                                             UI.
                                                                               printInfo(
                                                                                 Module.
                                                                                   BCKT,
                                                                                 "  * Anti-aliasing:      %s (fixed)",
                                                                                 this.
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
                                                                               getSize());
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
          start();
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
              new BucketThread(
                i);
            renderThreads[i].
              setPriority(
                scene.
                  getThreadPriority());
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
                      BCKT,
                    "Bucket processing thread %d of %d was interrupted",
                    i +
                      1,
                    renderThreads.
                      length);
            }
        }
        UI.
          taskStop();
        timer.
          end();
        UI.
          printInfo(
            Module.
              BCKT,
            "Render time: %s",
            timer.
              toString());
        display.
          imageEnd();
    }
    private class BucketThread extends Thread {
        private int threadID;
        BucketThread(int threadID) { super();
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
                                BucketRenderer.this.
                                  renderBucket(
                                    display,
                                    bx,
                                    by,
                                    threadID,
                                    istate);
                                if (UI.
                                      taskCanceled())
                                    return;
                            } }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1170392946000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAJ1Xe2wcxRkf3/kRP9CdHcd5kMSxYwp5cFtUgiAGQWKc5MiF" +
           "uLbjJA7gjHfnzhvv\n7S6zs+eLiSARUpKC2hIBUitBiFCkBAgPJa0CEoREvI" +
           "mQAAmQkAitIhUkSqWqEk3V/tFvZnZvb/fO\nBnHS7c7OfI/5Xr/55tT3qM6h" +
           "aLHqpNhemzipvqEBTB2i9RnYcYZhakx9p65x4MRm04qhmgyK6RpD\niYzqKB" +
           "pmWNE1JX1nb5GiVbZl7M0ZFkuRIkvtMdZ48u7KrKkQuP3o2bYDx2s7Y6gugx" +
           "LYNC2GmW6Z\n/QbJOwwlM3twASsu0w0lozusN4OuIqab77NMh2GTOfejB1E8" +
           "g+ptlctkqCvjK1dAuWJjivOKUK8M\nCLUgYS4lDOsm0daV1AHn6jAnbNvjG6" +
           "ykBiFz+OIImCN2AFYvK1ktra0w1Y6fHLlp37Hn4igxihK6\nOcSFqWAJA32j" +
           "qCVP8uOEOus0jWijqNUkRBsiVMeGPi20jqI2R8+ZmLmUOIPEsYwCJ2xzXJtQ" +
           "odOf\nzKAWldtEXZVZtOSjrE4Mzf+qyxo4B2Z3BGZLczfweTCwSYeN0SxWic" +
           "9SO6mbEPHOKEfJxp7NQACs\nDXnCJqySqloTwwRqk7E0sJlThhjVzRyQ1lku" +
           "aGFo0YxCua9trE7iHBljaEGUbkAuAVWjcARnYWhe\nlExIgigtikSpLD6rOn" +
           "44fPKpc3eI3K7ViGrw/TcB09II0yDJEkpMlUjGK27qifROd3EMISCeFyGW\n" +
           "NOuuObst8+2bnZLm6io0W8f3EJWNqXcf6Rx8YKOF4nwbc2zL0XnwQ5aLchjw" +
           "VnqLNlRtR0kiX0z5\ni+cH3925/3nyXQw1pVG9ahluHvKoVbXytm4QupGYhG" +
           "JGtDRqJKbWJ9bTqAHGGUh5Obs1m3UIS6Na\nQ0zVW+IbXJQFEdxFjTDWzazl" +
           "j23MJsS4aCOEWuCPWuH/ApI/8WaoN6U4rpk1rCnFoapi0VzpW7Uo\nUcDBGn" +
           "iZKutddZKwQe8zxZPIZmi7MmHliYJVbOqmpeR0KFvVul4jBf7++aKLfOdtUz" +
           "U1HAqjJW1A\nNWyyDKAeU09c/nBf/+bfHJbpwlPcs5mhNaAx5WlMcY0pX2Mq" +
           "rLFHfg5PUII1VFMjtLbzbcgwQhAm\noZwB+FpWDN171+7D3XHIH3uqFjzISb" +
           "vBVG9v/arVF9R8WsCjCom34Nldh1JXTtwuE0+ZGZqrcjd/\n/OLFY/9qWRlD" +
           "seq4yW0G5G7iYgY42JbwsCdaadXk/+ORLWc+v/jVdUHNMdRTAQWVnLyUu6PR" +
           "oZZK\nNADHQPzxhYn4djRyJIZqAR8AE8X+AW6WRnWESrrXh0duS0MGNWctms" +
           "cGX/IxrYlNUGsqmBFpkxTj\nuRCcOTzH2+H/kpf04s1X59n82SHTjEc7YoWA" +
           "3ysP1//yi9eb3xFu8ZE6UXYWDhEm6741SJZhSgjM\nf/WHgcef/P7QLpEpMl" +
           "VQESh/EVBCnRuANTx+PdvMvKXpWR2PG4Qn2v8S19zw57//LikjYsCMH9DV\n" +
           "Py4gmF+4Hu2/eN+/lwoxNSo/Z4LdB2TSiLmB5HWU4r18H8UDny7543v4aYBB" +
           "gB5HnyYCTWpKub+g\nvE+heh7wriCid/lg9xvvb3vmkMz4FbM0I+VcY+pDX/" +
           "9lMv77C+OSL4r5EeIjS4//7czlwXbpJnkw\nLq84m8p55OEo4p6weUC6ZtMg" +
           "qN9e1XXqwcFL3o7awhDfD23QN3vfItfe+tu/VsGgOBzfQllKJOUK\n8byeJ4" +
           "OXEvz7Fv7ohr2snsFLVXqfMXXf87lu9/4PXhNam3F5E1Weo1uwLc1N8sdybv" +
           "L8KLRtws4E\n0N14/u57ksb5/4LEUZCoQs/hbKUAkcVQhnvUdQ1fXnirY/cn" +
           "cRTbgJoMC2sbsAAH1AhVSZwJQOii\nffsdovCSU7wWk8JkJJywyHNAseyLJ9" +
           "QsmbKBd04BrIyNrz6Z+XDr08IBM6JilSSKyJk+t+3olY/Y\nJSEngCfO3VWs" +
           "PH6g2wx4b/680Fr/yjP5GGoYRUnV64dHsOFyEBiF9s3xm2TomUPr4VZM9h29" +
           "Jfhd\nHM3iMrVRYAxSDsacmo9bIlgofL8E/qc8LDwVxcIaJAYbBUuPeF7rHX" +
           "IMNdhUL2DeI0PPLQ7K9J2c\n4lcSQflzLX9skvG8rVrcxY7SoehDwJfM1AuK" +
           "4ju0458tB/Hb98a8WlnDUL3XoofzZknoEN4iWt8g\nTo8898JZ9smqtbKGV8" +
           "6cY1HGlWuPTXeuffnRn3H0dkYMi4puLVz96/iE/n5MdOcy7BVdfZipNxzs\n" +
           "JtiPS83hUMiXlUKe4C5eCP/TXshPVz/+qsW73nbHDV0tzgJb982ytps/dgD+" +
           "UVe6SLGlqBsZqi1Y\nuhZkzM5ZMiaMFAy1lHdqYnE+Q9f91D6PH1cV10N5pV" +
           "EzXz5wzw+Zz/4jepTStaMZev+saxjlBVY2\nrrcpyerC4GZZbrZ4QSAXzrgp" +
           "KCB/KCyYlDwWXLOjPOAr/ionA/bmMjIoTG9UTuSC44GIDwu276ak\nQHAONi" +
           "npv2LIu7wDWB6qC3Fr93PXlff2MXXHi7uWFR8dfkwURB3c96enxQUN7puyPy" +
           "vlf9eM0nxZ\nH6NXXh55/aVb/OIWR1R7WVaWYKKULe2zZQvUXPXuqD9vM9HP" +
           "TL86/0+3njh6KSbasv8D3kIGZWwR\nAAA=");
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
                this.
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
                          copy().
                          toNonLinear());
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
                                sampled()
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
                      black();
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
              sampled())
            this.
              computeSubPixel(
                s00,
                istate);
        if (!s01.
              sampled())
            this.
              computeSubPixel(
                s01,
                istate);
        if (!s10.
              sampled())
            this.
              computeSubPixel(
                s10,
                istate);
        if (!s11.
              sampled())
            this.
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
                this.
                  refineSamples(
                    samples,
                    sbw,
                    x,
                    y,
                    stepSize,
                    thresh,
                    istate);
                this.
                  refineSamples(
                    samples,
                    sbw,
                    x +
                      stepSize,
                    y,
                    stepSize,
                    thresh,
                    istate);
                this.
                  refineSamples(
                    samples,
                    sbw,
                    x,
                    y +
                      stepSize,
                    stepSize,
                    thresh,
                    istate);
                this.
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
                      processed())
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
    final private static class ImageSample {
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
            super();
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
                    getResult();
                this.
                  checkNanInf();
                shader =
                  state.
                    getShader();
                instance =
                  state.
                    getInstance();
                if (state.
                      getNormal() !=
                      null) {
                    nx =
                      state.
                        getNormal().
                        x;
                    ny =
                      state.
                        getNormal().
                        y;
                    nz =
                      state.
                        getNormal().
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
                    black();
            if (state !=
                  null) {
                c.
                  add(
                    state.
                      getResult());
                this.
                  checkNanInf();
            }
            n++;
        }
        final void checkNanInf() { if (c.
                                         isNan())
                                       UI.
                                         printError(
                                           Module.
                                             BCKT,
                                           "NaN shading sample!");
                                   else
                                       if (c.
                                             isInf())
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
        final static ImageSample bilerp(ImageSample result,
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
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1170392946000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAALUZa2wUx3nuzvb5lfhswDwCGBsTwiN3rQJViokCcUwwHODa" +
           "PIIJcca7c3eL93Y3\nu3vm7BCaqBFQ0jahIVKjEkIjSzhpSFBDRVrRFBpCae" +
           "gDWjVtaUOJkJJULZWqSilR+6PfN7t7e7f3\nCMYCibnZme/9mm/Gr10l5YZO" +
           "pgtG2BzSmBFu7+miusHEdpkaxnpY6hPeLa/qOrxaUf3EFyV+STRJ\nXVQwIi" +
           "I1aUQSI533t6V1skBT5aG4rJphljbD2+TFNr1V0cV5BDcdPN7w5EhZk5+UR0" +
           "kdVRTVpKak\nKh0ySxomCUW30UEaSZmSHIlKhtkWJbcwJZVsVxXDpIppPEp2" +
           "kkCUVGgC0jRJc9RhHgHmEY3qNBnh\n7CNdnC1QmKAzk0oKE5dn2AHmwlxMEN" +
           "vG686HBiKVuLkR1OESgNazMlpb2uapqgVGN35px6FXAqSu\nl9RJSg8SE0AT" +
           "E/j1ktokS/Yz3VguikzsJfUKY2IP0yUqS8Ocay9pMKS4Qs2UzoxuZqjyIAI2" +
           "GCmN\n6ZynsxgltQLqpKcEU9UzNopJTBadr/KYTOOgdqOrtqXuClwHBaslEE" +
           "yPUYE5KGUDkgIeb/JiZHRs\nXQ0AgBpMMjOhZliVKRQWSIPlS5kq8UiPqUtK" +
           "HEDL1RRwMcm0okTR1hoVBmic9Zlkiheuy9oCqCpu\nCEQxySQvGKcEXprm8V" +
           "KWfxY0frpn9MDby3hsl4lMkFH+akCa6UHqZjGmM0VgFuK1VHh/5+bUdD8h\n" +
           "ADzJA2zBLJ9zfEP0k582WTC3FYBZ17+NCWafsHZfU/djD6gkgGJUaqohofNz" +
           "NOfp0GXvtKU1yNrG\nDEXcDDubJ7vPbH7iVfZ3P6nuJBWCKqeSEEf1gprUJJ" +
           "npDzCF6dRkYiepYorYzvc7SRDmUQh5a3Vd\nLGYws5OUyXypQuXfYKIYkEAT" +
           "VcFcUmKqM9eomeDztEYImQr/yUxCfDHC/1m/JmkLR4yUEpPV7RFD\nFyKqHs" +
           "98C6rOImBgEaysR+5LCQPM7LY/wxhEmkk2RRJqkkWoQBVJUSNxCdJWUO8U2S" +
           "D+3jjpNEre\nsN3nw1LoTWkZsmGlKgN0n3D4yns7OlZ/fY8VLhjits4mWQQc" +
           "wzbHMHIMOxzDuRxbO5MQuz00qcmM\n+Hyc6USUwvIi+GAAshnqXu28nq2rHt" +
           "nTEoDw0baXoRkBtAU0tUXrENR2N+U7eXUUIO6mvLxld/ja\n4XutuIsUr8wF" +
           "sWvOHzl36N+18/3EX7hsospQuKuRTBfW2kw5bPUmWiH6/9y75s33z31wh5ty" +
           "JmnN\nqwT5mJjJLV7n6KrARKiNLvmRqXWBTWTjPj8pg/IAJZHLD9VmppdHTk" +
           "a3OdURdQlGSU1M1ZNUxi2n\npFWbCV3d7q7wqAnx+QRwTiWGOEx8ih3z/Bd3" +
           "J2k4NlpRht72aMGr77WvVXzhDydq3uVmcQp1XdZR\n2MNMK+3r3WBZrzMG6x" +
           "98p+u556/u3sIjxQoVkgbI211ISHMZSg36r3WDklRFKSbRfplhoP2vbs4X\n" +
           "f/iPb4Usj8iw4jh04ecTcNen3keeOPfwf2ZyMj4BjxlXehfMUmKCS3m5rtMh" +
           "lCP95G9nvPBz+iJU\nQag8hjTMeDEJcIUCgDQlu03RpSSUu0HuvSu7Wn5yds" +
           "NLu62In1eiF8nG6hO++tfLA4FnTvVbeN6S\n7wHeN3PkozevdE+0zGSdi7Pz" +
           "jqZsHOts5H6v09AhzaU4cOjTC5pf29l9yZaoIbfCd0AX9PHQO2zu\n0m9+WK" +
           "AEQdSolPvuLh6VEeCJv4tMEoCDncsR5ivz+HgnxokdLfi9FIcWEHNhEQMW6I" +
           "r6hB2vxltS\nj/7iR1ygGprdXmWH7xqqWZYI4TAbrTHZW/VWUiMBcItOrn0o" +
           "JJ/8L1DsBYoCdCPGOh2KZzon+G3o\n8uDFU+80PnIhQPwrSDVYQFxBed0gVZ" +
           "CwzEhA7U5r9y7jSRnajmka4ioTboRptgHSWV/VRskgWoE9\nlVtx+voXjkbf" +
           "W/ciN0DRglkgvjx0ht/ecPDar8xLnI5buRC7OZ1/MEEf6uLe/f5gfcXRl5J+" +
           "Euwl\nIcHulDdSOYX1oRcaO8Npn6GbztnPbdKsjqQtU5mnewM8i623ZrrRCH" +
           "OExnltoTI5Ccpj3C6TcW+Z\n9BE+WYXD7Sbx65zCXVb1xPEeHFZbDltW1LEd" +
           "Y2e5LsNyqBDLrjGyrAdWCZtlogjL9TZLn4STuz0c\nN9wEjpsdjkohjr1j5A" +
           "hbPsnmKBXh+LDDUeC4k00yObtZkrAfwvNF1T3C9I1RmGkgxDZbmG1FhGG2\n" +
           "MJWSHcWOTFPzGjgnzj1SxcYoVSNIM2BLNVBEKtmWqsJIUCh0Be3EZerh+x6J" +
           "kmOUCNpzn2xLJBeR\nyHByQSmYfuZNYJnOsCyYfkM3geXjGZbDhVjuLMHS2m" +
           "rl41yr6QrgGSwpFOpmUNOlQbhloUv5nT/7\nfOHN2Yxit1LeB+x+8F+1u+jp" +
           "rX77bF4MhOzHApdOJZLJuQ+s4Zdw91zY+8r3j5sXFiyx2on5xc80\nL+L8JY" +
           "eGm5a88fQN3AKaPIp5SdcP3vaVQEI66+fvBNYxk/e+kIvUlnu4VIM8KV1Zn3" +
           "PEzMp4/1Y0\n8Xzw+pDt/SGv91035/jPh/On0p4WyWffveycnFEwJyUljm87" +
           "jJN+vkST9QIOz0JHBvdpb5dWNqhK\noht9+z4v4DPRhB/fyNd/r63/3oL64/" +
           "Dtces6UkLXURy+B7pSUcTpd13dXh6PbrNAxP22bvuvW7ds\nyY6W2PsBDkdM" +
           "UiMkmDCwliqdSswj/evjkb4ZpD5gS39grJ7hVYpD/biECidwOA7VyBCo9V6T" +
           "Jfxb\n4xX+kC38oRsy/ekSe2dwOGWSKg0v9obBRG+KBPtVVWZUcdX52XjUaQ" +
           "I1Rmx1Rm5InQsl9n6Hw69B\naIO/9vAcOOtK/pvxSN4OEo/ako9et+R+TtFv" +
           "PI5oZGrWfXqtqvCXQUnAspp2bmd4KQjrLIaXd3xu\nSQ99OPfirF+G2s9Z1/" +
           "yESeZkXR9syEinMqgK/KBYSRVRZrp1659ekOEmnWoa089f/mjrMws+PoNn\n" +
           "lebG+Z9LGPgKDn+EVJWM+6UYf84xPUb+0w0aeRIuPgdWOmYb+VhBI+cfIn6c" +
           "P4XDLu9JEuQAQf79\nFz5eHuuc28U1ztUSxvkMh0+gb+jHZ18tjyI3z9+u1z" +
           "xpsHLWy6VzQtxxvc+e+HyT99cS64VfiF58\n7KFPo7//jL/ZZV7ha6KkMpaS" +
           "5exbZda8QoNgk7imNdYdU8MfX1mhVt4RCvp+Z4oa+AIWTtAkIS8O\nHMf4kw" +
           "1WDTbIAsPEtmbZQLfAeQdAOL1Vc8wUcpPJumHntoOYG7NzmjP+RyyngUpZf8" +
           "bqEx48smVW\n+un1z/KurFyQ6TBvW6ujJGi9V2aasOai1Bxa58nRNzaeeP3L" +
           "TofJ32UmZkV1TmLcY+2WCBZo/Aq/\nFnYkNZO/7w2/NfnY0sMHL/EE1/4POs" +
           "VCYnscAAA=");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170392946000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVae3AU9R3/3eX9wDzQEB4hJEQhIdyRhISQ2NGQEEg8ICaB" +
       "aFDjZu93ycLe7rK7\nF47IUKktIExFWvuwragdOwjFamtb2qm1MEjrY+wone" +
       "qMHR8d+646dZwqnfaPfn+Pvdvbuz25Y8zM\n/rK7v8fn+/5+f7/b0++jPENH" +
       "i0TDZ+7RsOHrHRkSdAMHe2XBMEbh1YR4Ia9o6MRNiupFngDySkET\nlQVEwx" +
       "8UTMEvBf0Dfd1RHa3QVHnPlKyaPhw1fTvkdr7eYKA9acGx42cq9z+WW+tFeQ" +
       "FUJiiKagqm\npCrrZRw2TFQe2CHMCP6IKcn+gGSY3QE0ByuRcK+qGKagmMYu" +
       "tA/lBFC+JpI1TVQXsMD9AO7XBF0I\n+ym8f4jCwgpzdWwKkoKDPTE4mNmcOB" +
       "PI5vOGk0fDIoWkcxuwQykArpfEuGbcJrGq5Ty+rWPvIydz\nUNk4KpOUEbKY" +
       "CJyYgDeOSsM4PIl1oycYxMFxVKFgHBzBuiTI0ixFHUeVhjSlCGZEx8YwNlR5" +
       "hgys\nNCIa1imm9TKASkXCkx4RTVWPySgkYTloPeWFZGEK2K6Ks83Y7Sfvgc" +
       "FiCQjTQ4KIrSm5OyUFNF7r\nnBHjseEmGABTC8LYnFZjULmKAC9QJdOlLChT" +
       "/hFTl5QpGJqnRgDFRAtcFyWy1gRxpzCFJ0xU7Rw3\nxLpgVBEVBJliomucw+" +
       "hKoKUFDi3Z9LOi6uNDj3/n2RupbecGsSgT+oth0mLHpGEcwjpWRMwmXor4\n" +
       "Hhi4NbLIixAMvsYxmI3pufbM1sDfflXLxixMMWbL5A4smhPi5mO1w3dtUFEO" +
       "IaNQUw2JKD+Bc+oO\nQ7ynO6qB11bFViSdPqvz7PCvb737FP6nFxUPoHxRlS" +
       "NhsKMKUQ1rkoz1DVjBumDi4AAqwkqwl/YP\noAK4D4DJs7dbQiEDmwMoV6av" +
       "8lX6DCIKwRJEREVwLykh1brXBHOa3kc1hFABXKgNrmrE/uh/E3X7\n/EZECc" +
       "nqbr+hi35Vn4o9i6qO/SDgIEhZ96+LiDuxOcwffcSINBON+afVMPYLoqBIiu" +
       "qfksBtRXVl\nEM+Q/9kvHSWUV+72eEgodLq0DN6wUZVh9IR44t0X966/6d5D" +
       "zFyIiXOeTbQcEH0c0UcQfRaiLxER\neTwU6GqCzDQHct8JHgyxrrRx5PbBOw" +
       "/V54DJaLtzQWhkaD1wx8lZL6q9cTcfoBFRBFur/u72g75L\nJ25gtuZ3j8Yp" +
       "Z5e88sRLj3xU2uRF3tShkrAJwbqYLDNE4mssBDY4nSvV+h8c3vT0ay+9uTzu" +
       "ZiZq\nSPL+5JnEe+udCtFVEQchHsaXf2x+Wc4Y2nbMi3IhJEAYpPRDhFnsxE" +
       "jw4m4rIhJeCgKoJKTqYUEm\nXVYYKzandXV3/A21lHJ6PxeUU0LMej5cn+N2" +
       "Tv+T3ms00lYxyyLadnBBI+6le/JXvf5MyQUqFis4\nl9nS3wg2matXxI1lVM" +
       "cY3r/5zaGvfu39g9uppXBTMSEnRiZlSYzClOviU8DHZYgzRJENW5WwGpRC\n" +
       "kjApY2Jx/yu7tuUn791XzlQjwxtLs82fvkD8/fx16O6X7vhkMV3GI5IcE2cj" +
       "PoxxMze+co+uC3sI\nHdH9F2se/I3wEIRACDuGNItpJEGUM0Tl6Kdyb6Ktz9" +
       "HXQpp6WLvZxfRTZPQJce+pqfrIrhd+Tqku\nEeylgV0NmwStm2meNEuJdOc5" +
       "vXejYEzDuNVnN99WLp/9L6w4DiuKkEmNLTo4fjRBiXx0XsEb585X\n3flqDv" +
       "L2o2JZFYL9ArV/VASGh41piDtR7YYbqW2V7y4kLWUZUSEs4AKI2p4WAnGN7u" +
       "7fT+qBuOdM\nTDY/Hnhxy0NUAK6OnyIdOtaZfXbr8Usvm2/RdeIeSGbXRZOD" +
       "KtRQ8bmdr81U5D/1cNiLCsZRucir\nvG2CHCF2Pg5FiWGVflAJJvQnFhgsm3" +
       "bHIswip/fbYJ2+Hw/mcE9Gk/tSh7uXWqlsPnf3+U539yB6\ncwOd0kDbZTHn" +
       "LNB0aUYglR8U3SKkYdo/D3wlKXeMkG4WO0jbSpobmZrbXc2hK5HQRXAt4IQu" +
       "cCF0\ngDQ9QFpQMjRZ2GNRVJ1EUR8b4KBpMEOaFnK6LPpS0TTEaSqWwlDkjc" +
       "GGYxpsutq+RdGlMJQ6MzSK\nv3ug/pfPb334IMt8aUw/YdaE+Pm339mZc/Tc" +
       "JJvntG/H4GOLH/vL0+8OX82iJKuJlyaVpfY5rC6m\nBlSmkYhRlw6Bjn5uRd" +
       "3pfcNvcYoqE6u79bAD+uue83jZ9V/+Y4ryIwcqd4dubs7CXmq4bmpcdHMH\n" +
       "100J1c1GLE1NU9gxB/ZEhthL4arl2LUu2CLHLpuk9RSNqptBBpbNltPwSiKB" +
       "j201HDQFM6SpAa4l\nnKYlLjTtsORho8miZ1GSD62LD3LQtjMLP6rjtNW50L" +
       "bL8iNG2wik1FSq0jOEXgxXPYeud4Ge4dBz\nGHSvGiH7ylTouzNEr+XGYhlN" +
       "KvS7OHqpha7qQYPUYLYQQusO4nkn7++bO7x2+z209C2CjbdgbI6n\nAq8UJH" +
       "ce8Mdr3SNLbLEJcdntZ/517lm8jOb/QsmAlNOjT6XYhNrmfCicwpteDx2n5W" +
       "vupGCw5OPc\nvSdvzhP23DTUXKXRf2OxGsnDNxFUlJoloXuS85MXklJIUgS6" +
       "AW6EFJUvY2WKbeqo0vZr0diqXjbF\nsvS5cc/rlVUFk9LQ6mPbHEn1xc41oD" +
       "OaRJ+OahI2OZsoZ/Ei4fDJ758xX13RxWJjk7sunBObuh6Z\nre168kgWW5ta" +
       "h8qcS1fMLLw5Z1p63ksPPFjNkXRQkjipO7HSKAZ6IroymlBvLGE63E+a69KU" +
       "vA+k\n6fs6ab4CKhWJPpj6QMa1qUv69WHNpEX47M/m/fj6E8ffIlLWorBhL6" +
       "YO0bJ6bccamF4JDkSO/XxS\nkBeAfRf7J0+FlFNBKoNiaq89ZApnsIi+sTlU" +
       "jqoZ5FzDdoDIV2rYohlkvzfHBjLQt/epwdLCR48c\noOtzbyyynZHw54IZQd" +
       "9sz4cljPBVne2rOk0kfEYHB10tnWuaW9tXtq0xUcXoxoERnz3kEGK+5QhD\n" +
       "+3RUlSxFwjsPaqiSWvhVcZcimd/eCdzlDq/v6XNE0r0ZRtIlPMVZqS5VJD1p" +
       "pbdgJKwx/g26yggP\nBrdA6TipqjIWFAc5p7LIaMs4OctcyPmhldHCktLT04" +
       "c1W3iyQf8oC+jlHHq5C/RPY9BCNA30mSyS\naSOHbnSB/oWVTOlZ54gQ1mRe" +
       "3DjRn8miwmni6E0u6Gc5egXZ1OmCYY5aG1OnIcB+ShWcRei5LHSx\ngpO0wo" +
       "Wk5zlJ+Tskk5cVpx2wL2Shh2YO2+wC+zKHLeJ7pZ6eVMi/zUIHfo7sd0G+yJ" +
       "HLJWVmxG4E\nThXkB9XIpOzcOP4uQ5JI8b+Kk7TKhaQ3OEmlRmRySIpi2a28" +
       "/EMWu5AWDt7iAv62FZbC5BcVrLlh\nv5MFdivHbnXB/lMMW4imw/5zhtjkWK" +
       "GNY7e5YP+dY+cZ0lSY5dh9l1fwfZCi4CP3B0jTSJc4GOPi\nveRKjzzeS5oj" +
       "yaUbeb6PkfEeadLVLP9O0/cJaT4izTcYFaR9MLEK6aQzv23L7x2trZ9hfm9t" +
       "a2/u\n6Fi5pgWIoPmdSp7QcMlSA3n4nkP5/8hQ+eQcaTVX/urUyvcgK+qxk0" +
       "HydD4R1uPJIuq1c9h2F9g8\nK+pFDDzoFm89+Vkgd3LkThfkYo4MVaVsZrTV" +
       "95RkSA45HlvLyVnrQk6FJX9GjkXKvKRdfj/td1BU\nmSFF5KaLU9TlQlE1p8" +
       "gbMlJEIM/8DCGr4OrmkN0ukDUcMic0baSywMVpMKPx4LDAFjpAhjVJMhwg\n" +
       "x0s0y2GdHJ7VuP2ESw/ODt7yYekB4bnbvTyS3ArmaqraShnPYNkGmM9iVYxt" +
       "+vNNP1wbONsbnGxT\n9lIFtVy6YK7FQfJ56RaN7q2sAWmOeKnexgiUp9k9PH" +
       "qofBvpKTLWBJ1OPR2XfNOnadv6scAhA6r6\nlXAd5jI47CqDHgd1PAHEye9K" +
       "Q/71pIFtUpkg0Op5VGWe6+rScdY6s2SNqpfsJY5y1o5etno9iQbq\nehxOWe" +
       "tPw/YgaXoharA04yzYcmdUKRjntO9KlBiC6wLn9MJlKzGPrpj3qZzGLZU21v" +
       "j6ZNclp1QG\nO1ogn7tgKoetaWS0nTTDUEsyGbE8TN4F4pIZuRLJbAZV8ure" +
       "k1Tdu0rGcci1+nJ/4G+wBa9MxSSm\nEZNEmjvBg8iXHBETj/DK2yEp4Uok9Q" +
       "WQ0DouqXWXLakCumIBK0hjC9Jz/KZ0C16RaDVNi5WGra0d\nHasdB1QBVRTk" +
       "gb5Hz5VcPBbpGLRyw0NERjOfYcnY1tra3Naysh3K0gIjbgRjKZrzmZrHrjTm" +
       "sY80\nionm6DgkKTb7sxuHernGETXRVYnckfPD6qTv3dg3WmLgjbtu+zjw+/" +
       "+wI2zrO6qSACoMRWTZ/tuq\n7T5fI6RSrZSwX1rpLsLzJRPNd7UIExVat5S5" +
       "L7I5hyCDOOdAeCX/7MOOwM7BNoyoiN3ZB90H9Q0M\nIrdHtRT5if3OHE2QF5" +
       "HO0oRTafoZonVyHGEfIk6ItzyxfUn0yOj99Dg6T5SF2VmyTHEAFbCvT+iq\n" +
       "5PS5znU1a61X0FNPbnvmB2st06ZfJ1zNPzlJ8vRW1uuuf9LxoPZ/tsE4UxIq" +
       "AAA=");
}
