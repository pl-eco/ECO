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
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXW2wUVfTu9F0LffAUoUApakF3fAQTrK9Siy2utqFI4hIt" +
                                                    "t7N3t0Pvzgwzd9ulWh8kBsIHMVoUjfbDgPhAMEaixpjw4ysaE43R+CGoPxqR" +
                                                    "RD5UIr7OuXd3Z3Z2W+OHm8zd+zjnnvfjHj1LqjyXrHVsvivFbRFlWRHdwddF" +
                                                    "xS6HedFNsXUD1PVYoptTz9sCe0NG26uNv154dKRJI9VxMo9ali2oMG3L28w8" +
                                                    "m4+xRIw0+rs9nKU9QZpiO+gY1TPC5HrM9ERnjFwUQBWkPZZnQQcWdGBBlyzo" +
                                                    "XT4UIM1hVibdjRjUEt5O8gCJxEi1YyB7gqwsvsShLk3nrhmQEsANtbjeCkJJ" +
                                                    "5KxLVhRkVzKXCHxgrT715L1Nr1WQxjhpNK1BZMcAJgQQiZOGNEsPM9frSiRY" +
                                                    "Ik6aLcYSg8w1KTcnJN9x0uKZKYuKjMsKSsLNjMNcSdPXXIOBsrkZQ9huQbyk" +
                                                    "yXgiv6pKcpoCWRf6sioJN+I+CFhvAmNukhosj1I5aloJQZaHMQoytt8OAIBa" +
                                                    "k2ZixC6QqrQobJAWZTtOrZQ+KFzTSgFolZ0BKoIsmfFS1LVDjVGaYkOCLA7D" +
                                                    "DagjgKqTikAUQRaEweRNYKUlISsF7HP2zhv232f1WprkOcEMjvzXAlJrCGkz" +
                                                    "SzKXWQZTiA1rYk/Qhe/s1QgB4AUhYAXzxv3nbrmi9eQHCuaSMjD9wzuYIYaM" +
                                                    "Q8NzP13a3bG+AtmodWzPROMXSS7dfyB30pl1IPIWFm7Ew2j+8OTm9+5+6CV2" +
                                                    "RiP1faTasHkmDX7UbNhpx+TMvY1ZzKWCJfpIHbMS3fK8j9TAPGZaTO32J5Me" +
                                                    "E32kksutaluuQUVJuAJVVANz00ra+blDxYicZx1CSA18ZB18VUT95L8g9+gj" +
                                                    "dprp1KCWadk6+C6jrjGiM8PWPZp2OFjNy1hJbo/rnmvotpsqrA3bZfpwxhhl" +
                                                    "QldMb5CLfjfB3Ci6mfN/E8iihE3jkQgof2k49DlETa/NAXbImMps6Dl3bOgj" +
                                                    "rRAKOd0I0gEkozmSUSQZVSSjJSRJJCIpzUfSysRgoFEIdUiCDR2D92zavret" +
                                                    "AnzLGa8E7SJoG8iZ46fHsLv9fNAns54BTrn4uW17oueP3KycUp85eZfFJicP" +
                                                    "jj+89cGrNKIVZ2GUD7bqEX0Ac2chR7aHo6/cvY17fvj1+BOTth+HRWk9lx5K" +
                                                    "MTG828KWcG2DJSBh+tevWUFPDL0z2a6RSsgZkCcFBb+GFNQaplEU5p35lImy" +
                                                    "VIHASdtNU45H+TxXL0Zce9zfkS4yF4cW5S1owBCDMttufOvkUyeeXrteCybm" +
                                                    "xkCpG2RChXmzb/8tLmOw//XBgccPnN2zTRofIFaVI9COYzcEPVgDNPbIBzu/" +
                                                    "On3q0Oea7zACql9mmJtGFu641KcCnsghLaFZ2++y0nbCTJp0mDP0uz8aV199" +
                                                    "4qf9TcpQHHbydr7i3y/w9y/eQB766N7fWuU1EQNLki+5D6YUMM+/uct16S7k" +
                                                    "I/vwZ8ueep8+CxkTspRnTjCZeIiUjEjVR6VFOuR4ZejsKhxWOCVnWbmzOLeS" +
                                                    "i5VybMfhMqU3nF4ehIzI+QJBlpYEdyCeUcvLZipFsowe2j01neg/fLWKzZbi" +
                                                    "9N4D3csrX/z5cfTgNx+WySt1wnau5GyM8SLGgGRRTrhDVmk/Mva9+PIb4tO1" +
                                                    "1yuSa2ZOB2HE93f/uGTLTSPb/0MmWB4SPnzli3cc/fC2S43HNFJRSAIljUcx" +
                                                    "UmdQDUDUZdApWahQ3KmXtm6VDDSDOvAjbfBV58qS/MfTeQ6O81XI4nBtyHk0" +
                                                    "qU8N9NkxS7/rmmkowWO5HkGfbDk9+swPryjdhhuKEDDbO7Xv7+j+KS3Qda0q" +
                                                    "aXyCOKrzkizPUSL+Db8IfH/hh6Lhhqq8Ld258r+iUP8dBz1y5WxsSRIbvz8+" +
                                                    "+fYLk3u0XOysF6QC+kSc9sqNW2cJtH4cugRpTjGhYmGQ7cxgggXaq2dWpQx0" +
                                                    "pZnp51d98uD0qm9BM3FSa3rQgHe5qTJ9XQDn56Onz3w2Z9kxmfArh6mn/CHc" +
                                                    "EJf2u0VtrJSgwZF/vQXxIoXQmsUVNiKZQNX9vZ8P7/7uvLRuSbSU8Y4Qflw/" +
                                                    "+syS7pvOSHy/gCH28mxpMwIq8nGveSn9i9ZW/a5GauKkycg9gLZSnsFaEgcl" +
                                                    "ePlXETySis6LG3jVrXYWYnNp2D0DZMOlMxillaIoPuc62QiR8Rcvn3A1mXAF" +
                                                    "XGhalGehbHFmpVS/2YvDoJMtCdRcRla1A9mHXGtbDMtQ/kw1VqYdLTy54DBb" +
                                                    "Ymdc36icYLBcagi6e2qWMxMHUGmVgYwovsGJlpevmz1pR8hKN/HmotdvODJ9" +
                                                    "ShbuLClTsyC6SppHvHlxyetUvaiMY9ONtYum7/pSRUf+1VMHT49khvOgpQLz" +
                                                    "asdlSVNKUpe3G/5B7Vk0Q08LllITybOt4PFFH4YXpBL/gmBjglwUABOkJjcL" +
                                                    "Au2CVARAOJ1w8mZt8k2uPDZLiqq6U1zjg+0ThrF8+ecrVEa9/YeM49Ob7rzv" +
                                                    "3HWHZbkDC9KJCflShIyhmsJClVs54235u6p7Oy7MfbVudT6hFrWLQd5wzv8B" +
                                                    "6SWfCmcRAAA=");
}
