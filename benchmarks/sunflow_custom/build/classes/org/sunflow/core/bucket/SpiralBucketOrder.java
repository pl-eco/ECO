package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class SpiralBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = new int[2 *
                                                                                nbw *
                                                                                nbh];
                                                       for (int i =
                                                              0;
                                                            i <
                                                              nbw *
                                                              nbh;
                                                            i++) {
                                                           int bx;
                                                           int by;
                                                           int center =
                                                             (Math.
                                                                min(
                                                                  nbw,
                                                                  nbh) -
                                                                1) /
                                                             2;
                                                           int nx =
                                                             nbw;
                                                           int ny =
                                                             nbh;
                                                           while (i <
                                                                    nx *
                                                                    ny) {
                                                               nx--;
                                                               ny--;
                                                           }
                                                           int nxny =
                                                             nx *
                                                             ny;
                                                           int minnxny =
                                                             Math.
                                                             min(
                                                               nx,
                                                               ny);
                                                           if ((minnxny &
                                                                  1) ==
                                                                 1) {
                                                               if (i <=
                                                                     nxny +
                                                                     ny) {
                                                                   bx =
                                                                     nx -
                                                                       minnxny /
                                                                       2;
                                                                   by =
                                                                     -minnxny /
                                                                       2 +
                                                                       i -
                                                                       nxny;
                                                               }
                                                               else {
                                                                   bx =
                                                                     nx -
                                                                       minnxny /
                                                                       2 -
                                                                       (i -
                                                                          (nxny +
                                                                             ny));
                                                                   by =
                                                                     ny -
                                                                       minnxny /
                                                                       2;
                                                               }
                                                           }
                                                           else {
                                                               if (i <=
                                                                     nxny +
                                                                     ny) {
                                                                   bx =
                                                                     -minnxny /
                                                                       2;
                                                                   by =
                                                                     ny -
                                                                       minnxny /
                                                                       2 -
                                                                       (i -
                                                                          nxny);
                                                               }
                                                               else {
                                                                   bx =
                                                                     -minnxny /
                                                                       2 +
                                                                       (i -
                                                                          (nxny +
                                                                             ny));
                                                                   by =
                                                                     -minnxny /
                                                                       2;
                                                               }
                                                           }
                                                           coords[2 *
                                                                    i +
                                                                    0] =
                                                             bx +
                                                               center;
                                                           coords[2 *
                                                                    i +
                                                                    1] =
                                                             by +
                                                               center;
                                                       }
                                                       return coords;
    }
    public SpiralBucketOrder() { super();
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1159026718000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
