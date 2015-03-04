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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "fg0AAA==");
}
