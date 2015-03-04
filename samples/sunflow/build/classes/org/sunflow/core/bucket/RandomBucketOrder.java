package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class RandomBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = new int[2 *
                                                                                nbw *
                                                                                nbh];
                                                       for (int i =
                                                              0;
                                                            i <
                                                              nbw *
                                                              nbh;
                                                            i++) {
                                                           int by =
                                                             i /
                                                             nbw;
                                                           int bx =
                                                             i %
                                                             nbw;
                                                           if ((by &
                                                                  1) ==
                                                                 1)
                                                               bx =
                                                                 nbw -
                                                                   1 -
                                                                   bx;
                                                           coords[2 *
                                                                    i +
                                                                    0] =
                                                             bx;
                                                           coords[2 *
                                                                    i +
                                                                    1] =
                                                             by;
                                                       }
                                                       long seed =
                                                         2463534242L;
                                                       for (int i =
                                                              0;
                                                            i <
                                                              coords.
                                                                length;
                                                            i++) {
                                                           seed =
                                                             xorshift(
                                                               seed);
                                                           int src =
                                                             mod(
                                                               (int)
                                                                 seed,
                                                               nbw *
                                                                 nbh);
                                                           seed =
                                                             xorshift(
                                                               seed);
                                                           int dst =
                                                             mod(
                                                               (int)
                                                                 seed,
                                                               nbw *
                                                                 nbh);
                                                           int tmp =
                                                             coords[2 *
                                                                      src +
                                                                      0];
                                                           coords[2 *
                                                                    src +
                                                                    0] =
                                                             coords[2 *
                                                                      dst +
                                                                      0];
                                                           coords[2 *
                                                                    dst +
                                                                    0] =
                                                             tmp;
                                                           tmp =
                                                             coords[2 *
                                                                      src +
                                                                      1];
                                                           coords[2 *
                                                                    src +
                                                                    1] =
                                                             coords[2 *
                                                                      dst +
                                                                      1];
                                                           coords[2 *
                                                                    dst +
                                                                    1] =
                                                             tmp;
                                                       }
                                                       return coords;
    }
    private int mod(int a, int b) { int m = a % b;
                                    return m < 0 ? m + b : m;
    }
    private long xorshift(long y) { y = y ^ y << 13;
                                    y = y ^ y >>> 17;
                                    y = y ^ y << 5;
                                    return y; }
    public RandomBucketOrder() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYb2wcxRWfW/83Tnx2yB/SxElsh8YJ3PJHQQqmtI7rxA4H" +
                                                    "tuIQqYfAjPfmzhvP7W525+yLwYVEogmoSlExkCDwhyqUQkOCqkYUIaR8aQFR" +
                                                    "IYFQKz5AWr6AmkZqPhQQtMB7M3e7e3t3RnzA0s7Nzrw379+833vr05dIg+eS" +
                                                    "bY7ND2W5LRKsIBIH+PaEOOQwL7EnuX2Muh5LD3LqeftgbcLofqn90y8fnYpr" +
                                                    "pDFFVlDLsgUVpm15e5ln8xmWTpL2YHWIs5wnSDx5gM5QPS9MridNT/QnyRUh" +
                                                    "VkF6kyUVdFBBBxV0qYI+EFAB0zJm5XODyEEt4R0kPyexJGl0DFRPkE3lhzjU" +
                                                    "pbniMWPSAjihGd/3g1GSueCSjb7tyuYKgx/fpi88eU/8D3WkPUXaTWsc1TFA" +
                                                    "CQFCUqQtx3KTzPUG0mmWTpEOi7H0OHNNys05qXeKdHpm1qIi7zLfSbiYd5gr" +
                                                    "ZQaeazPQNjdvCNv1zcuYjKdLbw0ZTrNg66rAVmXhLlwHA1tNUMzNUIOVWOqn" +
                                                    "TSstyIYoh29j721AAKxNOSambF9UvUVhgXSq2HFqZfVx4ZpWFkgb7DxIEWRt" +
                                                    "zUPR1w41pmmWTQiyJko3praAqkU6AlkEWRklkydBlNZGohSKz6U7bjl+nzVs" +
                                                    "aVLnNDM46t8MTF0Rpr0sw1xmGUwxtm1NPkFXvXZMIwSIV0aIFc3L91/+yTVd" +
                                                    "599QND+oQjM6eYAZYsI4Nbn8nXWDfTvqUI1mx/ZMDH6Z5fL6jxV3+gsOZN4q" +
                                                    "/0TcTJQ2z+/9y88efIFd1EjrCGk0bJ7PwT3qMOycY3Lm7mYWc6lg6RHSwqz0" +
                                                    "oNwfIU0wT5oWU6ujmYzHxAip53Kp0Zbv4KIMHIEuaoK5aWXs0tyhYkrOCw4h" +
                                                    "pAkesh2eBqL+5K8gd+tTdo7p1KCWadk63F1GXWNKZ4atezTncIial7cy3J7V" +
                                                    "PdfQbTfrvxu2y/TJvDHNhL6XWmk7t1O+jLpp5ibwmjnft4ACWhifjcXA+eui" +
                                                    "qc8ha4ZtDrQTxkJ+59DlMxNvaX4qFH0jSB+ITBRFJlBkQolMVIgksZiUdCWK" +
                                                    "ViGGAE1DqgMItvWN373n3mPddXC3nNl68C6SdoOdRX2GDHswwIMRiXoGXMo1" +
                                                    "v7nraOLz536sLqVeG7yrcpPzJ2YP73/gOo1o5SiM9sFSK7KPIXb6GNkbzb5q" +
                                                    "57Yf/eTTs0/M20EelsF6ER4qOTG9u6ORcG2DpQEwg+O3bqTnJl6b79VIPWAG" +
                                                    "4KSgcK8BgrqiMsrSvL8EmWhLAxicsd0c5bhVwrlWMeXas8GKvCLLcehUtwUD" +
                                                    "GFFQou2uV86fPPfUth1aGJjbQ6VunAmV5h1B/Pe5jMH6ByfGHnv80tG7ZPCB" +
                                                    "oqeagF4cByHpIRrgsYfeOPj+hQ9PvacFF0ZA9ctPctMowBlXB1IAEjjAEoa1" +
                                                    "904rZ6fNjEknOcN797/2zdef+/fxuAoUh5VSnK/59gOC9at2kgffuuezLnlM" +
                                                    "zMCSFFgekCkHrAhOHnBdegj1KBx+d/3J1+kzgJiAUp45xyTwEGkZka5PyIj0" +
                                                    "yfHayN51OGx0KvYKcmVN8U2+bJJjLw4/VH7D6ZYwZUzOVwqyriK5Q/mMXl5f" +
                                                    "qxTJMnrqyMJievTZ61VudpbD+xB0Ly/+7f9/TZz4x5tVcKVF2M61nM0wHlKs" +
                                                    "DkWWYcLtskoHmfHI879/Wbyz7WYlcmttOIgyvn7kX2v33Tp173dAgg0R46NH" +
                                                    "Pn/76Td3X238WiN1PghUNB7lTP1hN4BQl0GnZKFDcaVVxrpLKtAB7sCHdMPT" +
                                                    "WCxL8hd3Vzg4XqlSFocbI5dHk/7UwJ99S/S7rpmDEjxT7BH0+c4L009/8qLy" +
                                                    "bbShiBCzYwuPfJ04vqCFuq6eisYnzKM6L6nyMmXi1/AXg+crfNA0XFCVt3Ow" +
                                                    "WP43+vXfcfBGblpKLSli18dn51/93fxRrZg7OwSpgz4Rp8Ny4adLJNooDgOC" +
                                                    "dGSZULkwzg7mEWBB9ubarpSJrjyz+Nuetx9Y7PkneCZFmk0PGvABN1ulrwvx" +
                                                    "/Of0hYvvLlt/RgJ+/ST11H2INsSV/W5ZGystaHPkz7BvXswvt0tchV0oJlR1" +
                                                    "vxjlk0c++lxGtyJbqtyOCH9KP/302sFbL0r+oIAh94ZCZTMCLgp4b3gh91+t" +
                                                    "u/HPGmlKkbhR/ADaT3kea0kKnOCVvorgI6lsv7yBV91qv5+b66LXMyQ2WjrD" +
                                                    "WVovyvJzuVOIEZl/qeqAq0nAFXCgaVFegLLFmZVV/eYwDuNOoSJRi4isageq" +
                                                    "D1hrWwzLUGlPNVamnfA/uWCzUBFnfP+RugTj1aAhfN2zS+yZOIBLGwxUROkN" +
                                                    "l2hD9bo5lHOErHRzf1r9x1ueW/xQFu4CqaxZSkEf5OK4uAKeniLI9VQFuRq1" +
                                                    "TZAmxzVnAB8K1RHQd7rKfbGEwTM4wFdMHXQAkuNbdZcAfRU8W4q6b6kJ0E5E" +
                                                    "cDhaQziMqMNvE9Cm2FZWct6/hLaHcTgk4OMPPp+nzIwEuPkqLQKAWUWvjoFc" +
                                                    "U/HPAPUBa5xZbG9evXjn3xUYlT4yW+BLL5PnPJwYoXmj47KMKTVrKaUJ/vxC" +
                                                    "kNU1PiEgMdRE6vyQon9YkHiUHlyCP2GyXwpyRYgMrkFxFib6FcQSiHD6qFPK" +
                                                    "oniQYQogCqSsiXLKW6pwt4qoKf/RUmoI8upfLRPG2cU9d9x3+aZnZXcBCUPn" +
                                                    "5vCUZgBo1YP7TcWmmqeVzmoc7vty+Ustm0v1q6w7D+uGc/4NQ3HgvtYSAAA=");
}
