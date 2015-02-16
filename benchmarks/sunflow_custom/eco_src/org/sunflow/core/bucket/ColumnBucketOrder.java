package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class ColumnBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = new int[2 *
                                                                                nbw *
                                                                                nbh];
                                                       for (int i =
                                                              0;
                                                            i <
                                                              nbw *
                                                              nbh;
                                                            i++) {
                                                           int bx =
                                                             i /
                                                             nbh;
                                                           int by =
                                                             i %
                                                             nbh;
                                                           if ((bx &
                                                                  1) ==
                                                                 1)
                                                               by =
                                                                 nbh -
                                                                   1 -
                                                                   by;
                                                           coords[2 *
                                                                    i +
                                                                    0] =
                                                             bx;
                                                           coords[2 *
                                                                    i +
                                                                    1] =
                                                             by;
                                                       }
                                                       return coords;
    }
    public ColumnBucketOrder() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026718000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVXe2wUxxkf352f59QPwBBCMBgImMcuRrHV2EjBMSYcHHC1" +
                                                   "gRA7xIx3586L93aX\n3Vn7cFCaKBKQoDZBaaVWSiipkAg0aSKlFY2UByjv8E" +
                                                   "8SqY0UKY8Kqa3UJkpUKaVq/+g339xjb88m\nUqta8tzszPeY7/Wbb577klR7" +
                                                   "LlmieQo/6jBPGRhOUddj+oBJPW8vLI1pb1XXp87vtOwIqUqSiKFz\n0pTUPF" +
                                                   "WnnKqGria29uVcss6xzaMZ0+YKy3HlsNmdl7cj2V0h8J4zl1ofPhdrj5DqJG" +
                                                   "milmVzyg3b\nGjRZ1uOkOXmYTlHV54apJg2P9yXJTczyswO25XFqce8IeZBE" +
                                                   "k6TG0YRMTpYnC8pVUK461KVZFdWr\nKVQLEua5jFPDYnp/UR1wri/nhGPn+Y" +
                                                   "YqqUFIndjcD+bgCcDqZUWrpbUVpjrRZ/f3HDt7IUqaRkiT\nYQ0LYRpYwkHf" +
                                                   "CGnMsuw4c71+XWf6CGmxGNOHmWtQ05hBrSOk1TMyFuW+y7wh5tnmlCBs9XyH" +
                                                   "uaiz\nsJgkjZqwyfU1brtFH6UNZuqFr+q0STNgdlvJbGnuNrEOBjYYcDA3TT" +
                                                   "VWYIlNGhZEvD3MUbRx5U4g\nANbaLOMTdlFVzKKwQFplLE1qZdRh7hpWBkir" +
                                                   "bR+0cLJ4TqHC1w7VJmmGjXGyKEyXkltAVY+OECyc\nLAiToSSI0uJQlALxWd" +
                                                   "f27clnn3ptC+Z2TGeaKc7fAExLQ0xDLM1cZmlMMl73lZ8k7vWXRAgB4gUh\n" +
                                                   "YknTv+rSvuRfXm+XNLfMQrNn/DDT+Ji2+3T70AN32yQqjlHn2J4hgl9mOZZD" +
                                                   "Kr/Tl3OgatuKEsWm\nUti8PPT2vQ9dZH+NkIYEqdFs089CHrVodtYxTObezS" +
                                                   "zmUs70BKlnlj6A+wlSC/MkpLxc3ZNOe4wn\nSMzEpRobv8FFaRAhXFQPc8NK" +
                                                   "24W5Q/kEznMOIaQW/kk3/FcT+Ye/nGxWVM+30qY9rXquptpupvit\n2S5Tx3" +
                                                   "1tknFVnuku/Njj6sxVRBY5nBxQJ+wsU6lGLcOy1YwBdavZG3Q2JX7/B9k5cf" +
                                                   "bW6aoqAYbh\nojahHrbbJtCOaeevvX9scOejJ2XCiCTPW81JJ6hU8ioVoVKR" +
                                                   "KpUKlaSqCjXNF6pl8MD1k1DEAHeN\nncMHdxw62RGFrHGmY+A3QdoB9uXPM6" +
                                                   "jZA6VKTyAoapBui345ekK5fv5OmW7q3IA8K3f8g+evnv17\n49oIicyOlsJO" +
                                                   "wOsGISYlILaIgivD9TWb/K8e2/XSH65+uqZUaZysrACASk5RwB3hiLi2xnSA" +
                                                   "xJL4\nczc3Re8h+09HSAxQAZAQzw8gszSso6yQ+wqgKGypTZJ42naz1BRbBS" +
                                                   "Rr4BOuPV1awVRpFsN8mTUi\nkKEDIp5ef6Rm48evxN9CiwvQ2xS43IYZl4Xc" +
                                                   "UsqDvS5jsP7pz1JP/vTLE6OYBPks4HDj+eOmoeWA\n5bYSC6SXCSgiYrRyn5" +
                                                   "W1dSNt0HGTiWT6d9Oqrt/+7cfN0usmrBSCtv67BZTWb76LPHT1/n8sRTFV\n" +
                                                   "mrhBSmaUyKQ180qS+12XHhXnyD380a0/f4c+DQAHoOIZMwxxgqBlBP2ooHs7" +
                                                   "cdwQ2tsohg6QvX6O\nrJ7lvh7Tjl3MdPhH3nsZTx2nwYs/GIZd1OmTQUXd80" +
                                                   "BpD8kPZfgldhc4YmwTIVgYrt7t1JsAYbdf\n3n1fs3n5X6B2BNRqcJl6WPa5" +
                                                   "skjnqatrP7nyRtuhD6Mkso00mDbVt1HMf1IPice8CQCenHPnFjxG\n83SdGN" +
                                                   "EvBE+7OO+lXNkXfqzA8bZ89oj5miBVFc4XcrKkArcCUCUMvXWu+xPv/hMHvm" +
                                                   "k8Tt88KGGn\ntfxOGoS+7c9H32CrN//oj7NAZj23nQ0mm2Jm2cFAZRnc7cLW" +
                                                   "olTsj1341SX+4bpeqXLt3EgXZlzb\ne3amvfeFU/8FyLWHnBAW3TJ1yw+iE8" +
                                                   "a7Eex+JL5VdE3lTH1Bd4BSOI/vWsKxYqURc3JZMSfjIrK3\nw39NPidrwjmJ" +
                                                   "aCSG3lApRdCvEfDromCj7xpZaBimEAivHe949d19vzghXdp5g24+yDWm/fDz" +
                                                   "Lyaj\nj18Zl3zhpilEfHrpuT+9dG1ovswE2VmuqGjugjyyu0TLmhyRi8tvpA" +
                                                   "Gp31y3/LkHhz7DEwm+LZxE\noa0V0xQu7LwB0OwTw3ZOWjKMyyoYZkd8cVsI" +
                                                   "lA84D5FN6LzwxNZ5Q3eMPoIpVQ+NO/V2l2IKzyUx\nqwLfrJrbp0VhY9rqg5" +
                                                   "e+vvIaW43gUWd48NrodzOzNLEBnm/oRbbr4/QZvPti49ST+RPu/iub+7Ke\n" +
                                                   "He3/noM/KcdxoF2LozldGzf1bPo+2N8K9otXn2LoStLWqJnY+syV+Een/Z4d" +
                                                   "Mvw3BQgSW4+9uKOx\n7plTx7Ei8o6oD7S3+e/aKeruLoGC+Bnl5ND/q9Pr7V" +
                                                   "nf1bWhexNkXkWM1yiK0rnaW1YqRHRNlxgS\nEju7vxuJIXsqVIt7cVHFY1E+" +
                                                   "cLTkJw/c923y9/+U8Ss8QuLgqrRvmgGECKJFjeOytIHRisvbS4Yu\nzcnCOR" +
                                                   "pRaCDkBM/LJL0BD+4wPScx8RMkAzSLB8g4qc3PgkQ2lBoQianjFK6XZrzyxA" +
                                                   "tQkc+d8qtK\neGZFWWng+72Asr58wY9pB54fXZY7tfcJrLNqePnPzOBTDRJI" +
                                                   "9mxFpF4+p7SCrA/Iiy/sf+XXdxQg\noqybq7hHu+TuDcIPt8Ps3dRg1uHY/8" +
                                                   "z8buFvNp8/81kE+7n/AEtSudV2EQAA");
}
