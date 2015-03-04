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
                                                             this.
                                                               xorshift(
                                                                 seed);
                                                           int src =
                                                             this.
                                                             mod(
                                                               (int)
                                                                 seed,
                                                               nbw *
                                                                 nbh);
                                                           seed =
                                                             this.
                                                               xorshift(
                                                                 seed);
                                                           int dst =
                                                             this.
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
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1163966490000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVYe2wUxxkf3/ltp34AhhCCsQ3B5rGL09hSbKTg2CYcHODY" +
                                                   "QIgdasa7c+fFe7vL\n7qx9dlBChAQE1DaordRKLSUVEoEmTaS0opXSFJSkTc" +
                                                   "s/SaQ2UqSkrZDaSm2qRpVSqvaPfvPNPfb2\nzk7VqJZudnb2e8z3+s03fvFj" +
                                                   "UuG5ZI3mKXzeYZ4yODZCXY/pgyb1vAOwNKm9VVEzcmWPZUdIWZxE\nDJ2Thr" +
                                                   "jmqTrlVDV0NTbUn3bJZsc255OmzRWW5soxsycjb3e8p0jgYxevNz9zubw1Qi" +
                                                   "ripIFals0p\nN2xr2GQpj5PG+DE6S1WfG6YaNzzeHyd3MctPDdqWx6nFvePk" +
                                                   "KRKNk0pHEzI5aYtnlaugXHWoS1Mq\nqldHUC1IWOYyTg2L6QM5dcC5pZATtp" +
                                                   "3hGy2mBiHV4uMhMAd3AFavy1ktrS0y1Ym+cKj3xKWrUdIw\nThoMa0wI08AS" +
                                                   "DvrGSX2KpaaY6w3oOtPHSZPFmD7GXIOaxgJqHSfNnpG0KPdd5o0yzzZnBWGz" +
                                                   "5zvM\nRZ3ZxTip14RNrq9x2835KGEwU8++VSRMmgSzW/JmS3N3inUwsNaAjb" +
                                                   "kJqrEsS/mMYUHEW8McORvX\n7wECYK1KMT5t51SVWxQWSLOMpUmtpDrGXcNK" +
                                                   "AmmF7YMWTlYvKlT42qHaDE2ySU5WhelG5CegqkFH\nCBZOVoTJUBJEaXUoSo" +
                                                   "H4bG759OwL3359B+Z2uc40U+y/FpjWhphGWYK5zNKYZLzjK1+PPe6viRAC\n" +
                                                   "xCtCxJJmYMP1g/E//axV0txTgmb/1DGm8Ult34XW0ScfsUlUbKPasT1DBL/A" +
                                                   "ciyHkcyX/rQDVduS\nkyg+KtmPN0Z//vjJa+zPEVIbI5WabfopyKMmzU45hs" +
                                                   "ncR5jFXMqZHiM1zNIH8XuMVME8DikvV/cn\nEh7jMVJu4lKlje/gogSIEC6q" +
                                                   "gblhJezs3KF8GudphxBSBT/SA78KIv/wycl2RfV8K2Hac6rnaqrt\nJnPvmu" +
                                                   "0ydcrXZhhXR6ml26mH8WW/qzNXEVnkcHJYnbZTTKUatQzLVpMG1K1mb9XZrH" +
                                                   "h+Dtlpsffm\nubIyAYbhojahHnbZJtBOaldu/+rE8J5nz8qEEUmesZqTLlCp" +
                                                   "ZFQqQqUiVSpFKklZGWpaLlTL4IHr\nZ6CIAe7qu8aO7D56tj0KWePMlYPfBG" +
                                                   "k72JfZz7BmD+YrPYagqEG6rfrexBnlzpWHZLqpiwNySe66\nd166denv9Zsi" +
                                                   "JFIaLYWdgNe1QsyIgNgcCq4P11cp+X89t/fV39z6sDNfaZysLwKAYk5RwO3h" +
                                                   "iLi2\nxnSAxLz4y3c3RB8jhy5ESDmgAiAh7h9AZm1YR0Eh92dBUdhSFSd1Cd" +
                                                   "tNUVN8yiJZLZ927bn8CqZK\noxiWy6wRgQxtEPH0zqnKbe+/VvcWWpyF3obA" +
                                                   "4TbGuCzkpnweHHAZg/UPvznytW98fGYCkyCTBRxO\nPH/KNLQ0sNyXZ4EKNg" +
                                                   "FFRIzWH7RStm4kDDplMpFM/27Y0P2jv3ylUXrdhJVs0LZ8toD8+t0Pk5O3\n" +
                                                   "vvSPtSimTBMnSN6MPJm0Zlle8oDr0nmxj/Qz7937rV/Q7wDAAah4xgJDnCBo" +
                                                   "GUE/KujeLhy3hr5t\nE0M7yN6ySFaXOK8ntRPXku3+8V/+BHddR4MHfzAMe6" +
                                                   "nTL4OKupeB0l6SGQrwS3xd4YixRYRgZbh6\nd1FvGoQ9cGPfE43mjX+B2nFQ" +
                                                   "q8Fh6mHZpwsinaGuqPrg5hstR9+NkshOUmvaVN9JMf9JDSQe86YB\neNLOQz" +
                                                   "twG41z1WJEvxDc7eqMl9IFb/jSgeN9mewR884gVRnOV3Kypgi3AlAlDL13sf" +
                                                   "MTz/4zhz+p\nP03fPCJhp7nwTBqGvu2P82+wjdu//PsSkFnDbWeryWaZGdhY" +
                                                   "VKgsgLu92Frki/3c1e9f5+9u7pMq\nNy2OdGHGTX2XFlr7Xj7/P4Bca8gJYd" +
                                                   "FNs/c8Gp023o5g9yPxrahrKmTqD7oDlMJ+fNcSjhUr9ZiT\n63I5WSci+wD8" +
                                                   "KjM5WRnOSUQjMfSFSimCfo2AX1cFG33XSEHDMItAePt0+0/fPvjdM9KlXUt0" +
                                                   "80Gu\nSe3p3/5uJvrVm1OSL9w0hYgvrL38h1dvjy6XmSA7y46i5i7II7tLtK" +
                                                   "zBEbnYtpQGpH5zc9uLT41+\nhDsSfDs4iUJbK6YjuLBnCaA5KIZdnDQlGZdV" +
                                                   "MMaO++K0ECgfcB4im9B59bmhZaMPTpzClKqBxp16\n+/IxheuSmJWBbzYs7t" +
                                                   "OcsElt45Hrf7v5OtuI4FFteHDbGHCTJZrYAM8n9Brb+37iIp595VPUk/kT\n" +
                                                   "7v6Lm/uCnh3t/4KDjxHHcaBdq0Nzurfd3/vFXrC/GewXtz7F0JW4rVEzNvT8" +
                                                   "zbr3Lvi9u2X47woQ\nxIZOvLK7vvr586exIjKOqAm0t5n3qlnq7suDgnhMcH" +
                                                   "L0/9Xp9fVu6e7e2nM/ZF5RjDsVRena6K3L\nFyK6plsMMYmdPZ+JxPgylCvb" +
                                                   "erHYBr+OTNl2lCzbEojNSZXjGrPQqqdL1zNGKp/Xx5bIa7zWQLVF\n4XRHjr" +
                                                   "xh7PMa1pkxrHNRPEqEdlYWOIDijhT8KIf+xLaSyMWXMGVeDMc5qU7DNXfaSG" +
                                                   "Bl+3l73P/W\nnjSUeVGOiAZmVdGtXt5EtfgHTz7xafzX/5SFlr0t1kFOJ3zT" +
                                                   "DEB5ENYrHZclDNx6nWwzZI2d5GTl\nIjcG6PTkBPf7tKQ/xUljmB58Jh5Bst" +
                                                   "NQtgEySKLMLEj0LGQCEInpOSfbBzRibyKu6oq8lxb2FMIz\nHQUYhv9oyR6H" +
                                                   "vvxXy6R2+KWJdenzB55DQKzQTLqwgHdqqHTZXOeO1LZFpWVlvUNeefnQaz94" +
                                                   "MIvl\nBW13UW52y69LhB+O8dJt73DK4dioLvx45Q+3X7n4UQQb7/8ASIeuSh" +
                                                   "8TAAA=");
}
