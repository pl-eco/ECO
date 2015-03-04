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
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026718000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVXe2wcxRkf352fZ+pHEieEECdOQt67MSRG2FTEOA655JJc" +
                                                   "7SQQm2DGu3Pnjfd2\nN7uz9sVEFISUBKIWIkACqU1DFSkkhYJEUahEaSKgQP" +
                                                   "MPIBUkJB5VpIIEVEVIkKr8wTff3GNvzw4S\nqJY8NzvzPeZ7/eabZ74k1Z5L" +
                                                   "Fmiewg86zFP6BlPU9ZjeZ1LP2wVLI9rr1fWp09ssO0KqkiRi6Jw0\nJTVP1S" +
                                                   "mnqqGriU09OZesdmzzYMa0ucJyXNlvbsjL25rcUCHw9hPnWu8/FWuPkOokaa" +
                                                   "KWZXPKDdvq\nN1nW46Q5uZ9OUNXnhqkmDY/3JMlVzPKzfbblcWpx7wC5l0ST" +
                                                   "pMbRhExOFicLylVQrjrUpVkV1asp\nVAsSZrmMU8Niem9RHXCuKeeEY+f5Bi" +
                                                   "qpQUid2NwD5uAJwOpFRaultRWmOtGn93QdOnkmSpqGSJNh\nDQphGljCQd8Q" +
                                                   "acyy7ChzvV5dZ/oQabEY0weZa1DTmEKtQ6TVMzIW5b7LvAHm2eaEIGz1fIe5" +
                                                   "qLOw\nmCSNmrDJ9TVuu0UfpQ1m6oWv6rRJM2B2W8lsae5msQ4GNhhwMDdNNV" +
                                                   "ZgiY0bFkS8PcxRtHHpNiAA\n1tos42N2UVXMorBAWmUsTWpl1EHuGlYGSKtt" +
                                                   "H7RwMn9GocLXDtXGaYaNcDIvTJeSW0BVj44QLJzM\nCZOhJIjS/FCUAvFZ3f" +
                                                   "bN0ad/88pGzO2YzjRTnL8BmBaGmAZYmrnM0phkvOwrjyX2+gsihADxnBCx\n" +
                                                   "pOlddm538rO/tkuaa6ah2Tm6n2l8RNtxvH3gnttsEhXHqHNszxDBL7McyyGV" +
                                                   "3+nJOVC1bUWJYlMp\nbJ4f+Nve+86yzyOkIUFqNNv0s5BHLZqddQyTubcxi7" +
                                                   "mUMz1B6pml9+F+gtTCPAkpL1d3ptMe4wkS\nM3GpxsZvcFEaRAgX1cPcsNJ2" +
                                                   "Ye5QPobznEMIqYV/0gX/1UT+4S8nP1dUz7fSpj2peq6m2m6m+K3Z\nLlNHfW" +
                                                   "2ccXWLYUJV8Fvxa6erM1cRaeRwslcds7NMpRq1DMtWMwYUrmav1dmE+P0pwn" +
                                                   "Pi9K2TVVUC\nDsNlbUJFbLFNoB3RTl/6+6H+bQ8elSkj0jxvNyerQKeS16kI" +
                                                   "nYrUqVTqJFVVqGq20C3jB94fhzoG\nxGtcObhv691HO6KQOM5kDFwnSDvAwv" +
                                                   "yB+jW7r1TsCcRFDTJu3u+HjyiXT98iM06dGZOn5Y6//ezF\nk183roqQyPSA" +
                                                   "KQwFyG4QYlICZYtAuDRcYtPJ//dD21947+KHK0rFxsnSCgyo5BQ13BEOiWtr" +
                                                   "TAdU\nLIk/dXVT9Hay53iExAAYAAzx/IAzC8M6ymq5p4CLwpbaJImnbTdLTb" +
                                                   "FVALMGPubak6UVzJVmMcyW\naSMCGTogQurlB2rWvf9y/HW0uIC+TYH7bZBx" +
                                                   "WcstpTzY5TIG6x8+kXr08S+PDGMS5LOAw6Xnj5qG\nlgOW60osUMQmAImI0d" +
                                                   "LdVtbWjbRBR00mkum7pmWdL37x62bpdRNWCkFb88MCSutX30ruu3jXtwtR\n" +
                                                   "TJUmLpGSGSUyac2skuRe16UHxTly97977ZNv0N8CxgGueMYUQ6ggaBlBPyro" +
                                                   "3pU4rg3trRNDB8he\nM0NWT3Nlj2iHzmY6/ANv/RlPHafBuz8Yhu3U6ZFBRd" +
                                                   "2zQOmNJD+UQZjYneOIsU2EYG64erdQbwyE\nrT+/485m8/z/QO0QqNXgPvWw" +
                                                   "7HNlkc5TV9d+cOHVtrvfiZLIZtJg2lTfTDH/ST0kHvPGAHlyzi0b\n8RjNk3" +
                                                   "ViRL8QPO38vJdyZV/4sQTH6/LZI+YrglRVOJ/LyYIK4ApAlTD02pmuULz+j9" +
                                                   "zxVeNh+to+\nCTut5ddSP7Runx58lS2/+Vf/nAYz67ntrDXZBDPLDgYqy+Bu" +
                                                   "O3YXpWJ/6MwfzvF3VndLlatmRrow\n46ruk1Pt3c8d+xEg1x5yQlh0y8Q1v4" +
                                                   "iOGW9GsAGS+FbROJUz9QTdAUrhPL5rCceKlUbMyUXFnIyL\nyK6H/5p8TtaE" +
                                                   "cxLRSAzdoVKKoF8j4Nd5wV7fNbLQM0wgEF463PGXN3f/7oh06corNPRBrhHt" +
                                                   "lx9/\nMh59+MKo5Av3TSHi4wtP/euFSwOzZSbI5nJJRX8X5JENJlrW5IhcXH" +
                                                   "wlDUj92urFz9w78BGeSPBt\n5CQKna2YpnBh2xWAZrcYtnDSkmH5C3uQHfDF" +
                                                   "bSFQPuA8RDah88wjm2YN3DT8AKZUPfTu1NtRiim8\nmMSsCnyzbGafFoWNaM" +
                                                   "v3nfvPhVfYcgSPOsODB0evm5mmjw3wfEXPsu3vp0/g3RcbpZ7Mn/ADoLK/\n" +
                                                   "L2vb0f6fOfiTchwHOrY4mtO57vquG9aD/a1gv3j4KYauJG2NmolNT12Iv3vc" +
                                                   "79oqw39VgCCx6dDz\nWxvrnjp2GCsi74j6QIeb/66doO6OEiiIn2FO6P+t1+" +
                                                   "vuWtPZuXbD9ZB6FUFeoSjKyuXeolIlom86\nxZCQ4Lnhh6EYXj6VusXNOK/i" +
                                                   "xShfOVryg3vu/Cb5j//KCBZeInFwVto3zQBGBPGixnFZ2sB4xeX9\nJYOX5m" +
                                                   "TuDL0otBByggdmkt6AV3eYnpOY+AmSAZ7FA2Sc1OZnQSIbig2IxNRxChdMM1" +
                                                   "564hmoyDdP\n+WUlPLOkrDjwEV/AWV8+40e0O54dXpQ7tusRrLRqeP5PTeF7" +
                                                   "DVJIdm1FrF48o7SCrLfJ88/tefmP\nNxVAoqyfq7hJO+XuFeIP98P0/VR/1u" +
                                                   "HYAU29NPdPN58+8VEEO7rvAUcDNJF7EQAA");
}
