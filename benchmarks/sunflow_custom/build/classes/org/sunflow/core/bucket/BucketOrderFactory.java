package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class BucketOrderFactory {
    public static BucketOrder create(String order) { boolean flip = false;
                                                     if (order.startsWith(
                                                                 "inverse") ||
                                                           order.
                                                           startsWith(
                                                             "invert") ||
                                                           order.
                                                           startsWith(
                                                             "reverse")) {
                                                         String[] tokens =
                                                           order.
                                                           split(
                                                             "\\s+");
                                                         if (tokens.
                                                               length ==
                                                               2) {
                                                             order =
                                                               tokens[1];
                                                             flip =
                                                               true;
                                                         }
                                                     }
                                                     BucketOrder o = null;
                                                     if (order.equals("row"))
                                                         o =
                                                           new RowBucketOrder(
                                                             );
                                                     else
                                                         if (order.
                                                               equals(
                                                                 "column"))
                                                             o =
                                                               new ColumnBucketOrder(
                                                                 );
                                                         else
                                                             if (order.
                                                                   equals(
                                                                     "diagonal"))
                                                                 o =
                                                                   new DiagonalBucketOrder(
                                                                     );
                                                             else
                                                                 if (order.
                                                                       equals(
                                                                         "spiral"))
                                                                     o =
                                                                       new SpiralBucketOrder(
                                                                         );
                                                                 else
                                                                     if (order.
                                                                           equals(
                                                                             "hilbert"))
                                                                         o =
                                                                           new HilbertBucketOrder(
                                                                             );
                                                                     else
                                                                         if (order.
                                                                               equals(
                                                                                 "random"))
                                                                             o =
                                                                               new RandomBucketOrder(
                                                                                 );
                                                     if (o ==
                                                           null) {
                                                         UI.
                                                           printWarning(
                                                             Module.
                                                               BCKT,
                                                             "Unrecognized bucket ordering: \"%s\" - using hilbert",
                                                             order);
                                                         return new HilbertBucketOrder(
                                                           );
                                                     }
                                                     else {
                                                         if (flip)
                                                             o =
                                                               new InvertedBucketOrder(
                                                                 o);
                                                         return o;
                                                     }
    }
    public BucketOrderFactory() { super();
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "N/H5vM/WXZ58qPD7b3dQ5hSkDQAA");
}
