package org.sunflow.util;
final public class FloatArray {
    private float[] array;
    private int size;
    public FloatArray() { super();
                          array = (new float[10]);
                          size = 0; }
    public FloatArray(int capacity) { super();
                                      array = (new float[capacity]);
                                      size = 0; }
    final public void add(float f) { if (size == array.length) { float[] oldArray =
                                                                   array;
                                                                 array =
                                                                   (new float[size *
                                                                                3 /
                                                                                2 +
                                                                                1]);
                                                                 System.
                                                                   arraycopy(
                                                                     oldArray,
                                                                     0,
                                                                     array,
                                                                     0,
                                                                     size);
                                     }
                                     array[size] =
                                       f;
                                     size++;
    }
    final public void set(int index, float value) {
        array[index] =
          value;
    }
    final public float get(int index) { return array[index];
    }
    final public int getSize() { return size;
    }
    final public float[] trim() { if (size <
                                        array.
                                          length) {
                                      float[] oldArray =
                                        array;
                                      array =
                                        (new float[size]);
                                      System.
                                        arraycopy(
                                          oldArray,
                                          0,
                                          array,
                                          0,
                                          size);
                                  }
                                  return array;
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170479976000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAK1Ze2wUxxkf351t/CB+AIYAwWBMXMDc2RSTYlMljh9gOMCx" +
       "wSR2qBnvzp0X9nY3\nu3PH4SCaKArQoLahL6VVQ6Ci4lFoqGhFIiUEFGiToF" +
       "YJUkOVKqQVUlupTdWoUkrV/tFvZnZv9/bO\nRxqwtHOzM/N9M7/vPevTH6Ni" +
       "y0RzJStMdxvECncN9mPTInKXii1rMwyNSleKy/qPr9f0ACqKooAi\nU1QVla" +
       "yIjCmOKHKkr7sjbaKlhq7ujqs6DZM0De9Q22x+66JtOQy3Hj5f+/SxUH0AFU" +
       "dRFdY0nWKq\n6FqPShIWRdXRHTiFI0mqqJGoYtGOKJpKtGSiS9csijVqPYH2" +
       "omAUlRgS40nRgqizeQQ2jxjYxIkI\n3z7Sz7cFDtNMQrGiEbkzsx1QNmdTwr" +
       "FtuoHc1cBkCpscAjj8BIB6fga1QJsD1QieGFq558jJIKoa\nRlWKNsiYSYCE" +
       "wn7DqDJBEmPEtDplmcjDqEYjRB4kpoJVZYLvOoxqLSWuYZo0iTVALF1NsYW1" +
       "VtIg\nJt/TGYyiSolhMpMS1c2MjGIKUWXnrTim4jjArnNhC7i9bBwAlitwMD" +
       "OGJeKQhHYqGmi83k+Rwdi4\nHhYAaWmC0HE9s1VIwzCAaoUuVazFI4PUVLQ4" +
       "LC3Wk7ALRbMnZcpkbWBpJ46TUYpm+df1iylYVcYF\nwUgomuFfxjmBlmb7tO" +
       "TRz9K6Tw+c+OGFh7hth2Qiqez85UA0z0c0QGLEJJpEBOGtZPg7fY8l5wYQ\n" +
       "gsUzfIvFms5F57dE//JGvVgzJ8+aTWM7iERHpY2H6geeXKOjIDvGFEO3FKb8" +
       "LOTcHfrtmY60AV5b\nl+HIJsPO5MWBXz721Cny1wAq70Mlkq4mE2BHNZKeMB" +
       "SVmGuIRkxMidyHyogmd/H5PlQK/SiYvBjd\nFItZhPahkMqHSnT+DiKKAQsm" +
       "ojLoK1pMd/oGpuO8nzYQQlPhQbXwBJH4478UhcMRK6nFVH1XxDKl\niG7GM+" +
       "8cX6+qY9ppmnh3mNmNQdG6yLieIBEsYU3R9EhcAU+V9GUySbHf/4tbmp2vdl" +
       "dREQt4fsdV\nwebX6qpMzFHp+M139vSs/9oBYRTMkG1kFM2BTcL2JkLq7iao" +
       "qIjzns42E5Mg0J3gmhDEKhcPblu3\n/UADCCJt7AqBNAKwtAEw2CfokfQu13" +
       "/7eKiTwIhm/Whkf/jW8QeFEUUmD7N5qSvePXP1yD8rlwRQ\nIH8MZMggCpcz" +
       "Nv0scGZiW6Pfa/Lx//tzG869f/XDL7j+Q1FjjlvnUjK3bPDrwNQlIkOgc9kf" +
       "u7cq\nuBUNHQqgEPg6xDd+fggd8/x7ZLlnhxPqGJbSKKqI6WYCq2zKiU/ldN" +
       "zUd7kj3DiqeX8aKKeC2esM\neEptA+a/bHaGwdo6YUxM2z4UPJTeeqak5fpr" +
       "FVe4WJyoW+XJa4OECh+ucY1ls0kIjH/4Qv+3v/vx\n/hFuKcJUiigku+SYqk" +
       "hpILnfJQHnVSGAMEU2btESuqzEFDymEmZx/61a1PqLv32jWqhGhRFHs823\n" +
       "Z+CO3/sweurqV/41j7MpkljycGG4ywSaaS5n7hLsHOmnr933/V/hFyG2QTyx" +
       "lAnCQwTiyBCXY4TL\nfQlvw765VtY0AO/mSUw/T6oelfacijckn3j7VX7qCu" +
       "zN+V41bMBGh9A8axYy6c70e+9abI3DuhUX\nNz5erV78D3AcBo4SpEhrkwnx" +
       "Ip2lRHt1cekHl96s2/5eEAV6UTmECLkXc/tHZWB4xBqHUJM2HnyI\n21b1ri" +
       "ms5ZARF8JsWwD8ZW6uVVbaVlmZ1ypZc79PpEXCkADgLG+tZyoJyBkp7jU39z" +
       "W8/taWl/aL\nSLO4QEHnpRqVvvrRH3YGv3lpTND586Zv8aF5x/507ubAdGGV" +
       "orhYmJPfvTSiwODIqgymoQWFduCr\nLy9dcHrvwA37RLXZabIHSsk/736TNK" +
       "3++h/zRPgglEB8s9UFjHINa1bxqeWsaReaarutQtOeN5YA\nCki5l1Vubigc" +
       "HWs+EX1n04v8yJNG8jwK8PGZuLDl8K1f0xucjxtSGfWCdG5ihGrXpf3S+6ma" +
       "krMv\nJQKodBhVS3Y9PoTVJAtcw1A+Wk6RDjV71nx2KSjqno5MypjrtwDPtv" +
       "5g7qoL+mw161f64jd3jNnw\nhGxPCfk9pQjxziOcpJG3TZloW2qYSgqzGh0V" +
       "YxbIWKD3+A0PbszcTj7fPW1g1cgzPL+WQdmOrY3u\n8eCyxHpFINdFkys6w2" +
       "xUatp2/h+XLpAmHmSmKBaIodOM5ylhPTSf4FNkw/XYYZ4jQ2PYEgLx1/65\n" +
       "pX1Wxc7ld48h7PPL9u/DlIke6pucMMLeo4YjxNFcIQYYqaJhXk8vBjmWqESL" +
       "ixqxkzXD9h6Ma8D2\nB/Y+k9pZhBkKlOq6RlhCcuZEcaXo4cw1CSbTecLcfV" +
       "ml1QYO1bXk507+5Dx9b2m7iBBLJleOn3BJ\n+5GJ+vaXD36Ogqrep0M/65rU" +
       "nEeC48pbAX5/Eo6Rc+/KJurIdodyOE/S1DZnOcV8g/8M58sK3phm\nFJjj4Q" +
       "HuI8US04dQH8i4Pn8h0ZMwKE/9E6/M/Pnq44dvMCkbaaj/y7mHLP9iS9sqIK" +
       "8Fj2JfEcKK\nbEep7mu9Y6di2imZy6CcG3AnI7EBlvERj4cFdcNi1yTP9wib" +
       "U+Mmw2JV5lTPJn3de86uq5xy9OA+\nzt92zzLPlct+L01hc6M3K1Twg7cub2" +
       "lZ0UbR4N27lLQ/sKK5tW3Z8hYQzua1fYNhHm7Yvikn9uyF\nm16upBg+O7ax" +
       "mxYgvcd1G5bjvJOAIDTQ09ktitZM2hq4XdqKZgdUVnoU22yLJwmoz7JmkIpK" +
       "L+Pt\nnk33Fdg0nSeQsP6YiCLZtRH4+GQXe14F7H/0k8p9+PK2gG3EXRSKL9" +
       "1YppIUUT2sinl/RwZqDWPf\nAE+dDbUuf+1/+6PmlF/sdYSTf6+At73AmkNQ" +
       "imBZ9sfjUEpXZFeY3/qshUc+jK3wNNkYm+4cY8Bd\n0OkC/XEBoMdZcwSAWo" +
       "RfTn7gAjt6J8AW2eAckHdPeZ2c/GcFMJ1jzWnAFBeYRlxMZ+4E0zx42m1M\n" +
       "7XeOyXvk1wvMvcGaV6AiAjiDjke7kF69E0j18HTbkLrvLqS3C8xdZc1lcCUK" +
       "Fwe+YqtIkSOGYXiT\n1MpWX5KK6hJW+7qPXqq4dii5cp0TWHi4/g1F2z9fWp" +
       "B0k0QoXCeJCvtAOQMpTCUb4JIoMkRry6rm\nFW3L2h5YAmdLEZNCHeqcnCvh" +
       "yme+fQADN/+wDD4r5wO2+OgqRT948vFPo7/9t6gqnQ+jFZAqY0lV\n9Zbgnn" +
       "6JYZKYwmVSIQpyIdjfUVTt/3AG8mc//HzXxbLfQ571LAOjs3veRTfAtWAR63" +
       "5kOFVhtZv6\nxNUiO1UwpAuzajz+PwKnDkuK/xKMSo+eGZmfPrj5eV7cQa2D" +
       "JyYYm3IoCMQXpEwtt2BSbg6vd9HZ\nl4de++kqx0j4F4bpHrPO8ozlYnZyPb" +
       "KJpPE/SpSO468ZAAA=");
}
