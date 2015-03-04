package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class DiagonalBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = new int[2 *
                                                                                nbw *
                                                                                nbh];
                                                       int x =
                                                         0;
                                                       int y =
                                                         0;
                                                       int nx =
                                                         1;
                                                       int ny =
                                                         0;
                                                       for (int i =
                                                              0;
                                                            i <
                                                              nbw *
                                                              nbh;
                                                            i++) {
                                                           coords[2 *
                                                                    i +
                                                                    0] =
                                                             x;
                                                           coords[2 *
                                                                    i +
                                                                    1] =
                                                             y;
                                                           do  {
                                                               if (y ==
                                                                     ny) {
                                                                   y =
                                                                     0;
                                                                   x =
                                                                     nx;
                                                                   ny++;
                                                                   nx++;
                                                               }
                                                               else {
                                                                   x--;
                                                                   y++;
                                                               }
                                                           }while((y >=
                                                                     nbh ||
                                                                     x >=
                                                                     nbw) &&
                                                                    i !=
                                                                    nbw *
                                                                    nbh -
                                                                    1); 
                                                       }
                                                       return coords;
    }
    public DiagonalBucketOrder() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYa2wURXhu+y6FPniKUKAUtVRvfQQN1lepxRZP21Ak8YjW" +
                                                    "6d7cdenc7rI71x7F+iAxEH8Qo1XRaH8YfCMYI1FjTPjjKxgTjNH4Q1H/aEQS" +
                                                    "+eEjvvD7Zu5u9/auNf7wkp2dnfm++d6PucNnSJXnkk7H5rtT3BZRlhXRnXxD" +
                                                    "VOx2mBfdEtswSF2PJXo49bxtsDZstL3a+MsfD402aaQ6ThZSy7IFFaZteVuZ" +
                                                    "Z/NxloiRRn+1l7O0J0hTbCcdp3pGmFyPmZ7oipF5AVRB2mN5FnRgQQcWdMmC" +
                                                    "3u1DAdJ8ZmXSPYhBLeHtIveQSIxUOwayJ8ia4kMc6tJ07phBKQGcUIvf20Eo" +
                                                    "iZx1yeqC7ErmEoEf7dSnH7+z6bUK0hgnjaY1hOwYwIQAInHSkGbpEeZ63YkE" +
                                                    "S8RJs8VYYoi5JuXmpOQ7Tlo8M2VRkXFZQUm4mHGYK2n6mmswUDY3YwjbLYiX" +
                                                    "NBlP5L+qkpymQNYlvqxKws24DgLWm8CYm6QGy6NUjplWQpBVYYyCjO03AwCg" +
                                                    "1qSZGLULpCotCgukRdmOUyulDwnXtFIAWmVngIogy2c9FHXtUGOMptiwIMvC" +
                                                    "cINqC6DqpCIQRZDFYTB5ElhpechKAfucufWaA3usPkuTPCeYwZH/WkBqDSFt" +
                                                    "ZUnmMstgCrFhfewxuuSd/RohALw4BKxg3rj77A0Xtx7/QMGcXwZmYGQnM8Sw" +
                                                    "cWhkwckVPR0bK5CNWsf2TDR+keTS/QdzO11ZByJvSeFE3IzmN49vfe/2+15i" +
                                                    "pzVS30+qDZtn0uBHzYaddkzO3JuYxVwqWKKf1DEr0SP3+0kNzGOmxdTqQDLp" +
                                                    "MdFPKrlcqrblN6goCUegimpgblpJOz93qBiV86xDCKmBh1wFTxVRP/kWZFgf" +
                                                    "tdNMpwa1TMvWwXcZdY1RnRm27tG0w8FqXsZKcntC91xDt91U4duwXaaPZIwx" +
                                                    "JvQbTZqyLco3yc8BN8HcKDqa8/+TyKKUTRORCBhgRTj8OUROn80BdtiYzmzq" +
                                                    "PXtk+IRWCIecfgTpBKLRHNEoEo0qotEyREkkImktQuLK0GCmMQh4SIUNHUN3" +
                                                    "bLlrf1sFeJgzUQk6RtA2kDXHUa9h9/hZoV/mPgNcc9kzO/ZFf3v+euWa+uwp" +
                                                    "vCw2OX5w4v7t916qEa04F6OEsFSP6IOYQQuZsj0cg+XObdz3/S9HH5uy/Wgs" +
                                                    "Su65JFGKiUHeFraFaxssAWnTP379anps+J2pdo1UQuaAbCkoeDckotYwjaJg" +
                                                    "78onTpSlCgRO2m6actzKZ7t6MeraE/6KdJIFOLQof0EDhhiUOXfzW8efOPZk" +
                                                    "50YtmJ4bAwVviAkV7M2+/be5jMH6lwcHH3n0zL4d0vgAsbYcgXYceyD0wRqg" +
                                                    "sQc+2PXFqa8Ofar5DiOgBmZGuGlk4YwLfCqQGDgkJzRr+21W2k6YSZOOcIZ+" +
                                                    "92fjusuO/XigSRmKw0rezhf/+wH++nmbyH0n7vy1VR4TMbAw+ZL7YEoBC/2T" +
                                                    "u12X7kY+svd/svKJ9+nTkDchV3nmJJPph0jJiFR9VFqkQ46XhPYuxWG1U7KX" +
                                                    "lSvLcl/yY40c23G4UOkNpxcFISNyvliQFSXhHYhn1PLK2QqSLKaH9k7PJAae" +
                                                    "vUzFZktxku+FHuaVz/76KHrw6w/LZJY6YTuXcDbOeBFjQLIoJ9wia7UfGQ++" +
                                                    "+PIb4mTn1Yrk+tnTQRjx/b0/LN923ehd/yETrAoJHz7yxVsOf3jTBcbDGqko" +
                                                    "JIGS9qMYqSuoBiDqMuiXLFQortRLW7dKBppBHfiQNniqc8VJvnF3oYPjIhWy" +
                                                    "OFwRch5N6lMDfXbM0fW6ZhoK8XiuU9CnWk6NPfX9K0q34bYiBMz2Tz94Lnpg" +
                                                    "Wgv0XmtL2p8gjuq/JMvzlYjn4BeB5298UDRcUPW3pSfXBKwudAGOgx65Zi62" +
                                                    "JInN3x2devuFqX1aLnY2ClIB3SJO++TCjXME2gAO3YI0p5hQsTDEdmUwwQLt" +
                                                    "dbOrUga60szMc2s/vndm7TegmTipNT1ow7vdVJnuLoDz0+FTpz+Zv/KITPiV" +
                                                    "I9RT/hBui0u73qJmVkrQ4MhXX0G8SCG05nCFzUgmUHV/H+Aje7/9TVq3JFrK" +
                                                    "eEcIP64ffmp5z3WnJb5fwBB7Vba0HQEV+biXv5T+WWurflcjNXHSZOSuQdsp" +
                                                    "z2AtiYMSvPzdCK5KRfvFbbzqWbsKsbki7J4BsuHSGYzSSlEUnwucbITI+IuX" +
                                                    "T7iaTLgCDjShQ8pC2eLMSqmusw+HISdbEqi5jKxqB7IPuda2GJah/J5qrEw7" +
                                                    "Wrh4wWa2xM74fa1ygqFyqSHo7qk59kwcQKVVBjKi+AYnWlW+bvamHSEr3eSb" +
                                                    "S1+/5vmZr2ThzpIyNQukLNM+4tnLSm6p6mZlHJlprF06c9vnKj7yt586uIIk" +
                                                    "M5wHbRWYVzsuS5pSlrq85fAF1WfpLH0t2EpNJNe2gsebfRhekEp8BcHGBZkX" +
                                                    "ABOkJjcLAu2GZARAOJ108oZt8o2ufDZLiuq6U1zlgw0UBrL8ByBfozLqP4Bh" +
                                                    "4+jMllv3nL3yWVnwwIZ0clLeGCFnqLawUOfWzHpa/qzqvo4/Frxaty6fUosa" +
                                                    "xiBvOOf/AOSICZ5vEQAA");
}
