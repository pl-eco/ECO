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
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVYX2wcRxmfW/93nfhPmj+ExEkcp+C4vW1BBQWXgnN1aodr" +
                                                    "bcVpJK6i17m9ufPGezub2Tn74mLaRkKJ+hBV4JYUtX6oUkpLmlSIqCBUKS/Q" +
                                                    "VkVIrRCIBxrghYoQiTxQKgqU75u5vd3bOxvxhKWdm535vpnv7+/71heukzZf" +
                                                    "kFGPOyeLDpdJVpHJ486dSXnSY37ycPrOGSp8lk851PePwlrWGnq194OPnpzr" +
                                                    "M0h7hmyirssllTZ3/SPM584Cy6dJb7g64bCSL0lf+jhdoGZZ2o6Ztn05liY3" +
                                                    "RVglGU4HIpggggkimEoEczykAqYNzC2XUshBXemfIN8giTRp9ywUT5I99Yd4" +
                                                    "VNBS9ZgZpQGc0Invx0ApxVwRZHdNd61zg8JPjZor33mo74ctpDdDem13FsWx" +
                                                    "QAgJl2RIT4mVckz44/k8y2dIv8tYfpYJmzr2kpI7QwZ8u+hSWRasZiRcLHtM" +
                                                    "qDtDy/VYqJsoW5KLmnoFmzn54K2t4NAi6Lol1FVreAjXQcFuGwQTBWqxgKV1" +
                                                    "3nbzkuyKc9R0HP4KEABrR4nJOV67qtWlsEAGtO8c6hbNWSlstwikbbwMt0iy" +
                                                    "fc1D0dYeteZpkWUl2Ranm9FbQNWlDIEskmyOk6mTwEvbY16K+Of6/XedfcSd" +
                                                    "dA0lc55ZDsrfCUyDMaYjrMAEcy2mGXv2p5+mW14/YxACxJtjxJrmta/f+PKt" +
                                                    "g1fe1DSfbEIznTvOLJm1zuc2vrMjNXKgBcXo9Lhvo/PrNFfhP1PdGat4kHlb" +
                                                    "aifiZjLYvHLk51997GV2zSDdU6Td4k65BHHUb/GSZztM3MtcJqhk+SnSxdx8" +
                                                    "Su1PkQ6Yp22X6dXpQsFncoq0Omqpnat3MFEBjkATdcDcdgs8mHtUzql5xSOE" +
                                                    "dMBDPg9PG9F/6lcSYc7xEjOpRV3b5SbELqPCmjOZxbOCedycSE2bObDyXImK" +
                                                    "ed/0y27B4YtZq+xLXjJ9YZlcFINl0+KCmbmyNc+keY9Ni9ylzkH1Oi3yTCQx" +
                                                    "9rz/y60VtEXfYiIBbtoRBwkH8muSO0CbtVbKByduXMy+bdSSpmpFSUbh0mT1" +
                                                    "0iRemtSXJptcShIJddfNeLkOB3DmPMACAGbPyOzXDj98ZqgF4tBbbAVPIOkQ" +
                                                    "qF+VaMLiqRA7phRCWhDA255/8HTywxe/pAPYXBvom3KTK+cWHz/26O0GMeoR" +
                                                    "GzWEpW5kn0GcreHpcDxTm53be/r9Dy49vczDnK0rAVUoaeREKBiK+0Jwi+UB" +
                                                    "XMPj9++ml7OvLw8bpBXwBTBVUsgBgKvB+B11kDAWwCvq0gYKF7goUQe3Akzs" +
                                                    "lnOCL4YrKkg24jCg4wUdGBNQIfOhn1x55vJ3Rw8YURDvjZTFWSY1JPSH/j8q" +
                                                    "GIP1352b+fZT108/qJwPFHubXTCMYwoAArwBFvvmmyd+e/W9878ywoCRUCnL" +
                                                    "Oce2KnDGLeEtAB8OQBi6dfgBt8TzdsGmOYdh3P2zd98dl/9ytk87yoGVwM+3" +
                                                    "/vcDwvVPHCSPvf3Q3wfVMQkLy1eoeUimDbApPHlcCHoS5ag8/u7OZ96gzwG6" +
                                                    "AqL59hJTIEWUZkSZPqk8MqLG22J7t+Ow22vYq6iVbdU39bJHjcM4fErbDaef" +
                                                    "jlIm1HyzJDsa0juSz2jlnWuVLVVyz59aWc1Pv3CHzs2B+lIwAZ3OK7/+1y+S" +
                                                    "537/VhNk6ZLcu81hC8ypEwyurMOE+1RFDzPjiZd+8Jp8Z/QL+sr9a8NBnPGN" +
                                                    "U3/efvTuuYf/ByTYFVM+fuRL9114695brG8ZpKUGAg1NSj3TWNQMcKlg0FW5" +
                                                    "aFBc6Va+HlQC9IM58CFD8LRXS5j6xd1NHo4365TF4bOx4DGUPQ2w58g6vbGw" +
                                                    "S1CuF6r9hLk8cHX+2fdf0baNNx8xYnZm5YmPk2dXjEiHtrehSYry6C5NibxB" +
                                                    "q/gx/CXg+Tc+qBou6Co9kKq2CrtrvYLnYUTuWU8sdcWhP11a/un3l08b1dw5" +
                                                    "IEkL9JQ4nVQL96yTaNM4jEvSX2RS58IsO1FGgIW7961tSpXo2jKr39v7y0dX" +
                                                    "9/4BLJMhnbYPzfq4KDbpASM8f71w9dq7G3ZeVIDfmqO+jod489zYG9e1vEqD" +
                                                    "Hk/9TNbUS9RSa51QOITXRKruP6ad3Kk/fqi825AtTaIjxp8xLzy7PXX3NcUf" +
                                                    "FjDk3lVpbEfARCHvZ14u/c0Yav+ZQToypM+qfiwdo04Za0kGjOAHX1DwQVW3" +
                                                    "X9/s6852rJabO+LhGbk2XjqjWdoq6/Jzo1dJEJV/meaAayjAlXCgDR1SBcqW" +
                                                    "w9yi7k0ncZj1Kg2JWkVkXTtQfMBa7jIsQ8Gebqxsnqx9nsFmpcHP+P5FHQSz" +
                                                    "zaAhGu7FdfZsHMCkbRYKouWGINrVvG5OlDypKt3Sj7f+6K4XV99ThbtCmtQs" +
                                                    "0LJJ+4hnb2v4ltXfX9bF1d7OrasP/EbnR/CN1AUfKoWy40R9FZm3e4IVbKVL" +
                                                    "V+A5/IHqs3WNvhZ8pSdKaq7p8fs/Ti9JK/5EyRYkuSlCJklHdRYlOglgBEQ4" +
                                                    "XfICx/aFTtcxWyF1dd2rr/LRBgoTWf2fIKhRZf2fgqx1afXw/Y/c+NwLquCB" +
                                                    "D+nSkvquBMzQbWGtzu1Z87TgrPbJkY82vtq1L4DUuoYxKhvOnf8AYNNdI5UR" +
                                                    "AAA=");
}
