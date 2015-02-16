package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class HilbertBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int hi = 0;
                                                       int hn = 0;
                                                       while ((1 << hn < nbw ||
                                                                 1 <<
                                                                 hn <
                                                                 nbh) && hn <
                                                                16) hn++;
                                                       int hN = 1 << 2 * hn;
                                                       int n = nbw * nbh;
                                                       int[] coords = new int[2 *
                                                                                n];
                                                       for (int i =
                                                              0; i <
                                                                   n;
                                                            i++) {
                                                           int hx;
                                                           int hy;
                                                           do  {
                                                               int s =
                                                                 hi;
                                                               int comp;
                                                               int swap;
                                                               int cs;
                                                               int t;
                                                               int sr;
                                                               s =
                                                                 s |
                                                                   1431655765 <<
                                                                   2 *
                                                                   hn;
                                                               sr =
                                                                 s >>>
                                                                   1 &
                                                                   1431655765;
                                                               cs =
                                                                 (s &
                                                                    1431655765) +
                                                                   sr ^
                                                                   1431655765;
                                                               cs =
                                                                 cs ^
                                                                   cs >>>
                                                                   2;
                                                               cs =
                                                                 cs ^
                                                                   cs >>>
                                                                   4;
                                                               cs =
                                                                 cs ^
                                                                   cs >>>
                                                                   8;
                                                               cs =
                                                                 cs ^
                                                                   cs >>>
                                                                   16;
                                                               swap =
                                                                 cs &
                                                                   1431655765;
                                                               comp =
                                                                 cs >>>
                                                                   1 &
                                                                   1431655765;
                                                               t =
                                                                 s &
                                                                   swap ^
                                                                   comp;
                                                               s =
                                                                 s ^
                                                                   sr ^
                                                                   t ^
                                                                   t <<
                                                                   1;
                                                               s =
                                                                 s &
                                                                   (1 <<
                                                                      2 *
                                                                      hn) -
                                                                   1;
                                                               t =
                                                                 (s ^
                                                                    s >>>
                                                                    1) &
                                                                   572662306;
                                                               s =
                                                                 s ^
                                                                   t ^
                                                                   t <<
                                                                   1;
                                                               t =
                                                                 (s ^
                                                                    s >>>
                                                                    2) &
                                                                   202116108;
                                                               s =
                                                                 s ^
                                                                   t ^
                                                                   t <<
                                                                   2;
                                                               t =
                                                                 (s ^
                                                                    s >>>
                                                                    4) &
                                                                   15728880;
                                                               s =
                                                                 s ^
                                                                   t ^
                                                                   t <<
                                                                   4;
                                                               t =
                                                                 (s ^
                                                                    s >>>
                                                                    8) &
                                                                   65280;
                                                               s =
                                                                 s ^
                                                                   t ^
                                                                   t <<
                                                                   8;
                                                               hx =
                                                                 s >>>
                                                                   16;
                                                               hy =
                                                                 s &
                                                                   65535;
                                                               hi++;
                                                           }while((hx >=
                                                                     nbw ||
                                                                     hy >=
                                                                     nbh ||
                                                                     hx <
                                                                     0 ||
                                                                     hy <
                                                                     0) &&
                                                                    hi <
                                                                    hN); 
                                                           coords[2 *
                                                                    i +
                                                                    0] =
                                                             hx;
                                                           coords[2 *
                                                                    i +
                                                                    1] =
                                                             hy;
                                                       }
                                                       return coords;
    }
    public HilbertBucketOrder() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1159026718000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0Ya2wURXhu+66FPpCHCAVKQQty6yOaYH2VWmzxtA0FEs9o" +
                                                    "ndubuy6d211259qjWh8kBuIPYrQqGu0PA+IDwRgJGmPCH1/RmGiMxh+K+kcj" +
                                                    "ksgP0fj+vpm72729a41/vGRnZ2e+b773Y+7IGVLjuWS9Y/PdaW6LKMuJ6E5+" +
                                                    "ZVTsdpgX3RK7coi6Hkv2cup522BtxOh4pfnc7w+PtmikNk4WUMuyBRWmbXlb" +
                                                    "mWfzcZaMkWZ/tY+zjCdIS2wnHad6Vphcj5me6I6R8wKognTGCizowIIOLOiS" +
                                                    "Bb3HhwKkeczKZnoRg1rC20XuJZEYqXUMZE+QVaWHONSlmfwxQ1ICOKEev3eA" +
                                                    "UBI555KVRdmVzGUCP7Zen37izpZXq0hznDSb1jCyYwATAojESVOGZRLM9XqS" +
                                                    "SZaMk1aLseQwc03KzUnJd5y0eWbaoiLrsqKScDHrMFfS9DXXZKBsbtYQtlsU" +
                                                    "L2Uynix81aQ4TYOsi3xZlYSbcR0EbDSBMTdFDVZAqR4zraQgK8IYRRk7bwYA" +
                                                    "QK3LMDFqF0lVWxQWSJuyHadWWh8WrmmlAbTGzgIVQZbOeijq2qHGGE2zEUGW" +
                                                    "hOGG1BZANUhFIIogC8Ng8iSw0tKQlQL2OXPrNfvvtvotTfKcZAZH/usBqT2E" +
                                                    "tJWlmMssgynEpnWxx+mit/ZphADwwhCwgjlxz9kbLmk/+Z6CubACzGBiJzPE" +
                                                    "iHEwMf/jZb1dG6uQjXrH9kw0fonk0v2H8jvdOQcib1HxRNyMFjZPbn3ntvtf" +
                                                    "ZKc10jhAag2bZzPgR62GnXFMztybmMVcKlhygDQwK9kr9wdIHcxjpsXU6mAq" +
                                                    "5TExQKq5XKq15TeoKAVHoIrqYG5aKbswd6gYlfOcQwipg4dcBU8NUT/5FsTU" +
                                                    "t3vg7jo1qGVatg7Oy6hrjOrMsEcSoN3RDHXHPN3IesLO6F7WSnF7QvdcQ7fd" +
                                                    "dPHbsF2mJ7LGGBN6v8khgsQm+TXoJpkbRZdz/k9iOZS8ZSISAaMsC6cEDtHU" +
                                                    "b3OAHTGms5v6zh4d+UArhkheZ4KsA5rRPM0o0owqmtFymiQSkaTOR9rK9mC5" +
                                                    "McgBkB2buobv2HLXvo4qcDpnohrUjqAdIHOeoT7D7vUTxYBMhwZ465Jnb98b" +
                                                    "/fXw9cpb9dmzekVscvLAxAM77rtUI1ppekYBYakR0YcwqRaTZ2c4LCud27z3" +
                                                    "+3PHHp+y/QAtyff5vFGOiXHfETaFaxssCZnUP37dSnp85K2pTo1UQzKBBCoo" +
                                                    "ODzkpvYwjZL47y7kUpSlBgRO2W6GctwqJMBGMeraE/6K9JH5OLQpd0EDhhiU" +
                                                    "aXjzGyefPP7U+o1aMGM3B2rgMBMq/lt9+29zGYP1Lw8MPfrYmb23S+MDxOpK" +
                                                    "BDpx7IVsANYAjT343q4vTn118FPNdxgBZTGb4KaRgzPW+lQgV3DIV2jWzu1W" +
                                                    "xk6aKZMmOEO/+6N5zWXHf9zfogzFYaVg50v+/QB//YJN5P4P7vylXR4TMbBW" +
                                                    "+ZL7YEoBC/yTe1yX7kY+cg98svzJd+kzkEohfXnmJJMZiUjJiFR9VFqkS44b" +
                                                    "QnuX4rDSKdvLyZUl+S/5sUqOnThcpPSG04uDkBE5XyjIsrLoDsQzann5bDVK" +
                                                    "1teDe6ZnkoOHLlOx2Vaa9/ugrXn5sz8/jB74+v0KiaVB2M4GzsYZL2EMSJbk" +
                                                    "hFtk+fYj46EXXjohPl5/tSK5bvZ0EEZ8d88PS7ddN3rXf8gEK0LCh4984ZYj" +
                                                    "79+01nhEI1XFJFDWkZQidQfVAERdBi2UhQrFlUZp63bJQCuoAx/SAU9tvl7J" +
                                                    "N+4ucHA8X4UsDleEnEeT+tRAn11zNMKumYHaPJ5vHvSptlNjT3//stJtuNMI" +
                                                    "AbN90w/9Hd0/rQXasdVlHVEQR7VkkuV5SsS/4ReB5y98UDRcUCW5rTffF6ws" +
                                                    "NgaOgx65ai62JInN3x2bevP5qb1aPnY2ClIFDSRO++XCjXME2iAOPYK0plm+" +
                                                    "tg2zXVlMsEB7zeyqlIGuNDPz3OqP7ptZ/Q1oJk7qTQ868x43XaHhC+D8dOTU" +
                                                    "6U/mLT8qE351gnrKH8KdcnkjXNLfSgmaHPnqL4oXKYbWHK6wGckEqu5vgzyx" +
                                                    "59tfpXXLoqWCd4Tw4/qRp5f2Xnda4vsFDLFX5Mq7EVCRj3v5i5mftY7atzVS" +
                                                    "FyctRv5mtIPyLNaSOCjBK1yX4PZUsl/a2as2trsYm8vC7hkgGy6dwSitFiXx" +
                                                    "Od/JRYiMv3jlhKvJhCvgQNOiPAdlizMrrRrRfhyGnVxZoOYzsqodyD7kWtti" +
                                                    "WIYKe6qxMu1o8S4Gm7kyO+P3tcoJhiulhqC7p+fYM3EAldYYyIjiG5xoReW6" +
                                                    "2ZdxhKx0k68vfu2awzNfycKdIxVqFlipvHvEo5eU3VvVXcs4OtNcv3hm++cq" +
                                                    "PAr3oQa4lKSynAdNFZjXOi5LmVKUhoLh8AXFZ/EsXS2YSk0k07aCx7t+GF6Q" +
                                                    "anwFwcYFOS8AJkhdfhYE2g25CIBwOukU7Nri21y5bI6UlHWntMgH+yeMY/mf" +
                                                    "QKFEZdW/AiPGsZktt9599qpDst6BCenkpLxDQspQXWGxzK2a9bTCWbX9Xb/P" +
                                                    "f6VhTSGjlvSLQd5wzv8B/PYQRoERAAA=");
}
