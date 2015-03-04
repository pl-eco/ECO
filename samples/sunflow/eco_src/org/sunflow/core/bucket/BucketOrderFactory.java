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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1163562944000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVXe2wURRif3l3fNX0ALe+WUhAK3IIREykGSm2hsMDZQqEF" +
       "LNPZuevSvd1ld7a9\nFmIgJoIQjURNNFFCDEkBRUzUoAlRDOKLf8BETUgwMf" +
       "6hiWJiTBCjf/jNzD33Wkj0j9udnfke871+\n33dv3UaFroNmEzfMRm3qhtu6" +
       "I9hxqdZmYNfdBlv95GphaWR8k2kFUIGKArrGUKVKXEXDDCu6pnQ+\n3pJw0B" +
       "LbMkZjhsXCNMHC+4yVSXkb1ZV5AnecvFhz+HSoPoAKVVSJTdNimOmW2W7QuM" +
       "tQlboPD2PF\nY7qhqLrLWlT0ADW9eJtlugybzN2PnkJBFRXZhMtkaJ6aUq6A" +
       "csXGDo4rQr0SEWpBwhSHMqybVGtN\nqwPOpbmccO0kX1c+NQgp4Yc9YI64AV" +
       "jdkLZaWptnqh080/PIwVNng6iyD1XqZjcXRsASBvr6UEWc\nxgeo47ZqGtX6" +
       "ULVJqdZNHR0b+pjQ2odqXD1mYuY51O2irmUMc8Ia17OpI3SmNlVUQbhNjkeY" +
       "5aR9\nFNWpoaW+CqMGjoHZtRmzpbkdfB8MLNPhYk4UE5piCQ3pJkS83s+Rtr" +
       "FpExAAa3GcskErrSpkYthA\nNTKWBjZjSjdzdDMGpIWWB1oYmjmpUO5rG5Mh" +
       "HKP9DE3300XkEVCVCkdwFoam+cmEJIjSTF+UsuKz\npPbO0TOvfbRW5HZIo8" +
       "Tg9y8Dprk+pi4apQ41CZWMd73wS5293uwAQkA8zUcsaVoXXNyu/vxxvaSZ\n" +
       "NQHN1oF9lLB+suVEfdeB9RYK8muU2Jar8+DnWC7KIZI8aUnYULW1aYn8MJw6" +
       "vNz1We+hc/SXACrr\nREXEMrw45FE1seK2blBnPTWpgxnVOlEpNbU2cd6Jim" +
       "GtQsrL3a3RqEtZJwoZYqvIEt/goiiI4C4q\nhbVuRq3U2sZsUKwTNkKoGH6o" +
       "IrVIvRl6LKy4nhk1rBHFdYhiObH0N7Ecqgx4ZIgyZZ14bXU06nRg\nns6jYZ" +
       "5GNkO9yqAVpwom2NRNS4npULjEWqbRYf7+P8IT/PY1IwUFHA79ZW1ARWywDG" +
       "DpJ+M/fnWw\nfdOzR2XK8DRP2s1QM+gMJ3WGuc6w1BnO14kKCoSqqVy3jB94" +
       "fwjqGBCvYnH3no17jzYGIXHskRC4\njpM2goXJC7UTqy1T7J0CFwlk3PQ3dh" +
       "0J3x1fIzNOmRyTJ+Quv37+2qk/KpoDKDAxYHJDAbLLuJgI\nR9k0EDb5S2wi" +
       "+b8d2/zut9duLcoUG0NNeRiQz8lruNEfEsciVANUzIg/PaMyuAP1nAigEAAD" +
       "gKG4\nP+DMXL+OnFpuSeEit6VYReVRy4ljgx+lwKyMDTrWSGZH5EoVf0yVac" +
       "MD6buggNS7Txct/+5S+VVh\ncQp9K7P6WzdlsparM3mwzaEU9m+9Ennx5dtH" +
       "dokkSGYBg6bnDRg6SQDLwgwLFLEBQMJj1LTdjFua\nHtXxgEF5Mv1TuWDF+7" +
       "8+XyW9bsBOKmhL7y8gsz9jHTp07ck/5woxBYQ3kYwZGTJpzZSM5FbHwaP8\n" +
       "HonDX8959XP8OmAc4Iqrj1EBFUhYhoQfw8K9i8Vzme9sOX80guylk2T1BC27" +
       "nxw8F2v09n/5obh1\nOc7u/dlh2IztFhlUoXsKKG1AyUcOhPHTaTZ/1vIQ1P" +
       "mrdwN2B0HYw5e37K4yLv8NavtALYF+6orq\nT+REOkldWHzzkyu1e28EUaAD" +
       "lRkW1iRIABRD4lF3EJAnYa9ZK65RNVLCn8IvSNx2ZtJLiZwv8TFf\nPBcms4" +
       "evF/moHDRnsu4oOvuRnb9XPIM/3SMRpSa347TDVPbT6BX64OrnfpgADkuZZS" +
       "8z6DA1snRy\nJJuTg2SbxeCQqeNjZ9+8yG4sWSVVNk8OYn7G5lWnxupXXTj+" +
       "H/Cr3ucEv+jq4VlPBAf1LwJitpHQ\nlTcT5TK1ZLsDlMJ9PMfkjuU7FSLdGt" +
       "LpVsPD8RD8SpLpVuJPNwk0eVENiKgCMLhitEz4Sqgg6XT+\nXQeztUg+Po6F" +
       "5TgmxKr3qLsIf3SAfOJQmBpSgmbntbqsHieIVvDHenmflfdPXpgV87skx5Lp" +
       "eTO2\nnAuJevPA7jvqN38JvE/PbuUwQEU9w8hyfXYYimyHRnVhWbmseFu8eh" +
       "mqm6R7g+1yIS68U9LvBl/6\n6RkK8Vc2GUyv5VlkDBUnV9lEAwwFgYgviT1B" +
       "pOSUmFu43DPzc0pD/O1Jpa8n//j0k53ndzUkjm97\nQdREIfxhGhsTEy4M7L" +
       "LPpUtg3qTSUrKuo3cu9Fx6+9FAMjVyOmAe9qyQp/eIP5TdxB2oPW4z0TPG\n" +
       "Pqh7b/X4ye8Dogf+C0pcU12tDgAA");
}
