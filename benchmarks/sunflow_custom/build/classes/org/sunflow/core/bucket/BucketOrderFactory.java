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
      1163562944000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "DyV+/w131T81lA0AAA==");
}
