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
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVYX2wcxRmfW/83Tvwn5E/TxEkch9YJ3EIrkFJTWufqYKcH" +
                                                    "tuIQqYfgmNubO288t7uZnbMvpi4QqUrEQ1S1hoaK+qEKpdCQoKoRrSqkvLSA" +
                                                    "qCqBEKgPJW1figiRyAMUlbb0+2Zub/f2zq76VEs7NzvzfTPf39/3rc9fI22+" +
                                                    "IPs8l58oclcmWUUmj/Hbk/KEx/zkofTt01T4LJ/i1PePwFrWGnqx9+NPvzfb" +
                                                    "Z5D2DNlAHceVVNqu4x9mvsvnWT5NesPVcc5KviR96WN0npplaXMzbftyNE1u" +
                                                    "iLBKMpwORDBBBBNEMJUI5lhIBUzrmFMupZCDOtI/Tr5DEmnS7lkoniS76g/x" +
                                                    "qKCl6jHTSgM4oRPfj4JSirkiyM6a7lrnBoWf2Gcu//DBvl+0kN4M6bWdGRTH" +
                                                    "AiEkXJIhPSVWyjHhj+XzLJ8h/Q5j+RkmbMrtRSV3hgz4dtGhsixYzUi4WPaY" +
                                                    "UHeGluuxUDdRtqQrauoVbMbzwVtbgdMi6Lop1FVreBDXQcFuGwQTBWqxgKV1" +
                                                    "znbykuyIc9R0HP4mEABrR4nJWbd2VatDYYEMaN9x6hTNGSlspwikbW4ZbpFk" +
                                                    "66qHoq09as3RIstKsiVON623gKpLGQJZJNkYJ1MngZe2xrwU8c+1e+8887Az" +
                                                    "4RhK5jyzOMrfCUyDMabDrMAEcyymGXv2pp+km14+bRACxBtjxJrmpW9f//rN" +
                                                    "g5df1TSfb0IzlTvGLJm1zuXWv7EtNbK/BcXo9FzfRufXaa7Cf7q6M1rxIPM2" +
                                                    "1U7EzWSwefnw77716PPsqkG6J0m75fJyCeKo33JLns2ZuJs5TFDJ8pOkizn5" +
                                                    "lNqfJB0wT9sO06tThYLP5CRp5Wqp3VXvYKICHIEm6oC57RTcYO5ROavmFY8Q" +
                                                    "0gEPuQOeNqL/1K8kx81Zt8RMalHHdlwTYpdRYc2azHKzgnmuOZ6aMnNg5dkS" +
                                                    "FXO+6ZedAncXslbZl27J9IVluqIYLJuWK5iZK1tzTJoTNodMkgfU25TIM5HE" +
                                                    "0PP+H5dW0BJ9C4kEOGlbHCI4ZNeEy4E2ay2XD4xfv5B93ailTNWGkuyFO5PV" +
                                                    "O5N4Z1LfmWy8kyQS6qob8W4dC+DJOcAEQMuekZkHDj10eqgFgtBbaAU3IOkQ" +
                                                    "KF8VaNxyUyFwTCp4tCB6t/zk/lPJT579mo5ec3WUb8pNLp9deOzoI7caxKiH" +
                                                    "a1QQlrqRfRpBtgamw/E0bXZu76n3Pr745JIbJmwd/ldxpJETcWAo7grhWiwP" +
                                                    "yBoev3cnvZR9eWnYIK0ALgCokkICAFYNxu+ow4PRAFtRlzZQuOCKEuW4FQBi" +
                                                    "t5wV7kK4omJkPQ4DOlzQgTEBFSwf/PXlpy79aN9+I4rgvZGaOMOkxoP+0P9H" +
                                                    "BGOw/qez0z944tqp+5XzgWJ3swuGcUwBOoA3wGLfffX4H6+8e+4tIwwYCWWy" +
                                                    "nOO2VYEzbgpvAezggF/o1uH7nJKbtws2zXGGcffP3j23XfrgTJ92FIeVwM83" +
                                                    "//cDwvXPHSCPvv7g3wfVMQkLa1eoeUimDbAhPHlMCHoC5ag89ub2p16hPwZo" +
                                                    "BTjz7UWmEIoozYgyfVJ5ZESNt8T2bsVhp9ewV1ErW6pv6mWXGodx+IK2G06/" +
                                                    "GKVMqPlGSbY1ZHckn9HK21erWarenju5vJKfeuY2nZsD9XVgHNqcF97+1++T" +
                                                    "Z//8WhNg6ZKudwtn84zXCQZX1mHCPaqch5nx+HM/f0m+se8r+sq9q8NBnPGV" +
                                                    "k+9vPXLX7EP/AxLsiCkfP/K5e86/dvdN1vcN0lIDgYYOpZ5pNGoGuFQwaKkc" +
                                                    "NCiudCtfDyoB+sEc+JAheNqr9Uv94u4GD8cbdcri8OVY8BjKngbYc2SNxljY" +
                                                    "JajV89VmwlwauDL39HsvaNvGO48YMTu9/PhnyTPLRqQ9293QIUV5dIumRF6n" +
                                                    "VfwM/hLw/BsfVA0XdIkeSFX7hJ21RsHzMCJ3rSWWuuLg3y4u/eZnS6eMau7s" +
                                                    "l6QFGkqcTqiFb6yRaFM4jEnSX2TV2jbDjpcRYOHuPaubUiW6tszKT3f/4ZGV" +
                                                    "3X8By2RIp+1Dpz4mik0awAjPh+evXH1z3fYLCvBbc9TX8RDvnBsb47p+V2nQ" +
                                                    "46mfiZp6iVpqrREKB/GaSNX9xxTPnfzrJ8q7DdnSJDpi/Bnz/NNbU3ddVfxh" +
                                                    "AUPuHZXGbgRMFPJ+6fnSR8ZQ+28N0pEhfVb1S+ko5WWsJRkwgh98PsHXVN1+" +
                                                    "faev29rRWm5ui4dn5Np46Yxmaausy8/1XiVBVP5lmgOuoQBXwoG2Q3kFyhZn" +
                                                    "TlE3phM4zHiVhkStIrKuHSg+YK3rMCxDwZ5urGw3Wfs2g81Kg5/x/as6CGaa" +
                                                    "QUM03Itr7Nk4gEnbLBREyw1BtKN53RwveVJVusVfbf7lnc+uvKsKd4U0qVng" +
                                                    "pcbuEY/e0vAdq7+9rAsrvZ2bV+57R6dH8H3UBR8phTLnUVdF5u2eYAVbqdIV" +
                                                    "OA5/oPhsXqWrBVfpiRLa1fT47R+nl6QVf6Jk85LcECGTpKM6ixKdACwCIpwu" +
                                                    "eoFf+0Kf65CtkLqy7tUX+Wj/hHms/kcQlKiy/i9B1rq4cujeh6/f8Yyqd+BC" +
                                                    "uriovikBMnRXWCtzu1Y9LTirfWLk0/Uvdu0JELWuX4zKhnP+H4aEDd6REQAA");
}
