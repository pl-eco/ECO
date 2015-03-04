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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1159026718000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVXe2wUxxkf352f59QPwBBCMBgImMcuprGl2EjBMSYcHHC1" +
       "gRA7xIxn586L93aX\n2Vn7cFCaKBKQoDZBaaVWSiipkAg0aSKlFY2UByjv8E" +
       "8SqY0UKY8Kqa3UJkpUKaVq/+g3M/fY27OJ\n1KqWPDc78z3me/3mm+e+RNUe" +
       "Q0uIp/GjLvW0geEUZh41BizseXthaYy8VV2fOr/TdiKoKokipsFR\nU5J4uo" +
       "E51k1DT2ztyzG0znWsoxnL4RrNce2w1Z2XtyPZXSHwnjOXWh8+F2uPoOokas" +
       "K27XDMTcce\ntGjW46g5eRhPYd3npqUnTY/3JdFN1PazA47tcWxz7wh6EEWT" +
       "qMYlQiZHy5MF5Too113McFaX6vWU\nVAsS5jHKsWlTo7+oDjjXl3PCsfN8Q5" +
       "XUIKRObO4Hc+QJwOplRauVtRWmutFn9/ccO3shippGUJNp\nDwthBCzhoG8E" +
       "NWZpdpwyr98wqDGCWmxKjWHKTGyZM1LrCGr1zIyNuc+oN0Q9x5oShK2e71Im" +
       "dRYW\nk6iRCJuYT7jDij5Km9QyCl/VaQtnwOy2ktnK3G1iHQxsMOFgLI0JLb" +
       "DEJk0bIt4e5ijauHInEABr\nbZbyCaeoKmZjWECtKpYWtjP6MGemnQHSascH" +
       "LRwtnlOo8LWLySTO0DGOFoXpUmoLqOqlIwQLRwvC\nZFISRGlxKEqB+Kxr+/" +
       "bks0+9tkXmdsygxBLnbwCmpSGmIZqmjNqEKsbrvvaTxL3+kghCQLwgRKxo\n" +
       "+ldd2pf8y+vtiuaWWWj2jB+mhI+R3afbhx6420FRcYw61/FMEfwyy2U5pPI7" +
       "fTkXqratKFFsaoXN\ny0Nv3/vQRfrXCGpIoBriWH4W8qiFOFnXtCi7m9qUYU" +
       "6NBKqntjEg9xOoFuZJSHm1uied9ihPoJgl\nl2oc+Q0uSoMI4aJ6mJt22inM" +
       "Xcwn5DznIoRq4R91w381Un/yl6PNmu75dtpypnWPEd1hmeI3cRjV\nx30ySb" +
       "k+7JoMW3fJjz3MoEwTWeRydECfcLJUxwTbpu3oGRPqljgbDDolfv8H2Tlx9t" +
       "bpqioBhuGi\ntqAetjsW0I6R89fePza489GTKmFEkuet5qgTVGp5lZpQqSmV" +
       "WoVKVFUlNc0XqlXwwPWTUMQAd42d\nwwd3HDrZEYWscadj4DdB2gH25c8zSJ" +
       "yBUqUnJCgSSLdFvxw9oV0/f6dKN31uQJ6VO/7B81fP/r1x\nbQRFZkdLYSfg" +
       "dYMQkxIQW0TBleH6mk3+V4/teukPVz9dU6o0jlZWAEAlpyjgjnBEmEOoAZBY" +
       "En/u\n5qboPWj/6QiKASoAEsrzA8gsDesoK+S+AigKW2qTKJ52WBZbYquAZA" +
       "18gjnTpRWZKs1imK+yRgQy\ndECJp9cfqdn48Svxt6TFBehtClxuw5SrQm4p" +
       "5cFeRimsf/qz1JM//fLEqEyCfBZwuPH8ccskOWC5\nrcQCFWwBiogYrdxnZx" +
       "3DTJt43KIimf7dtKrrt3/7cbPyugUrhaCt/24BpfWb70IPXb3/H0ulmCoi\n" +
       "bpCSGSUyZc28kuR+xvBRcY7cwx/d+vN38NMAcAAqnjlDJU4gaRmSftSkezvl" +
       "uCG0t1EMHSB7/RxZ\nPct9PUaOXcx0+Efee1meOo6DF38wDLuw26eCKnXPA6" +
       "U9KD+U4ZfYXeCKsU2EYGG4erdjbwKE3X55\n933N1uV/gdoRUEvgMvVk2efK" +
       "Ip2nrq795MobbYc+jKLINtRgOdjYhmX+o3pIPOpNAPDk3Du3yGM0\nT9eJUf" +
       "oFydMuznspV/YlP1bI8bZ89oj5miBVlZwv5GhJBW4FoEoYeutc96e8+08c+K" +
       "bxOH7zoIKd\n1vI7aRD6tj8ffYOu3vyjP84CmfXccTdYdIpaZQcDlWVwt0u2" +
       "FqVif+zCry7xD9f1KpVr50a6MOPa\n3rMz7b0vnPovQK495ISw6JapW34QnT" +
       "DfjcjuR+FbRddUztQXdAcohfP4zBaOFSuNMieXFXMyLiJ7\nO/zX5HOyJpyT" +
       "Eo3E0BsqpYj0awT8uijY6DMzCw3DlATCa8c7Xn133y9OKJd23qCbD3KNkR9+" +
       "/sVk\n9PEr44ov3DSFiE8vPfenl64NzVeZoDrLFRXNXZBHdZfSsiZX5OLyG2" +
       "mQ1G+uW/7cg0OfyRMJvi0c\nRaGtFdOUXNh5A6DZJ4btHLVkKFdVMEyP+OK2" +
       "ECgfcJ5ENqHzwhNb5w3dMfqITKl6aNyxt7sUU3gu\niVkV+GbV3D4tChsjqw" +
       "9e+vrKa3S1BI8604PXRj/LzNLEBni+wRfpro/TZ+TdFxvHnsqfcPdf2dyX\n" +
       "9ezS/u+58iflui60a3FpTtfGTT3f3wT2t4L94tWnmYaWdAi2ElufuRL/6LTf" +
       "s0OF/6YAQWLrsRd3\nNNY9c+q4rIi8I+oD7W3+u3YKs90lUBA/oxwd+n91er" +
       "0967u6NnRvgsyriPEaTdM6V3vLSoUoXdMl\nhoTCzu7vRmLIngrV4l5cVPFY" +
       "VA8ckvzkgfu+Tf7+nyp+hUdIHFyV9i0rgBBBtKhxGU2bMlpxdXup\n0KU5Wj" +
       "hHIwoNhJrI81JFb8KDO0zPUUz8BMkAzeIBMo5q87MgkQOlBkRi6rqF66VZXn" +
       "niBaip5075\nVSU8s6KsNOT7vYCyvnrBj5EDz48uy53a+4Sss2p4+c/MyKca" +
       "JJDq2YpIvXxOaQVZH6AXX9j/yq/v\nKEBEWTdXcY92qd0bhB9uh9m7qcGsy2" +
       "X/M/O7hb/ZfP7MZxHZz/0H6d/khHYRAAA=");
}
