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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "P4Qb4vJ+t6SNlRI0OfKvvyhepBhac7jCZiQTqLq/DfKRPd/8Kq1bFi0VvCOE" +
       "n9CPPL2097ozEt8vYIi9IlfejICKfNwrXsz8pHXUvqWRugRpMfIPoB2UZ7GW" +
       "JEAJXuFVBI+kkvPSBl51q93F2FwWds8A2XDpDEZptSiJz/lOLkJk/CUqJ1xN" +
       "JlwBF5oW5TkoW5xZadVv9uMQd3JlgZrPyKp2IPuQa22LYRkqnKnGyrSjxScX" +
       "HObK7Izra5UTxCulhqC7p+c4M3EAldYYyIjiG5xoReW62ZdxhKx0k68tfvWa" +
       "wzNfysKdIxVqFkRXWfOINy8pe52qF5VxbKa5fvHMrZ+p6Ci8ehrg6ZHKch60" +
       "VGBe67gsZUpJGgp2wz+oPYtn6WnBUmoiebYVPL7ow/CCVONfEGxckPMCYILU" +
       "5WdBoN2QigAIp5NOwawtvsmVx+ZISVV3Smt8sH3CMJYv/0KFyqq3/7BxfGbL" +
       "LXefu+qQLHdgQTo5KV+KkDFUU1iscqtmva1wV21/1+/zX25YU0ioJe1ikDec" +
       "838AXTGTSWcRAAA=");
}
