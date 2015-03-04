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
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAMVYWWwbRRgeb+6QNkfpQWnTNk2BtODlUJFKuFKTNimGRHWp" +
       "hBGY8XrsbDPe3c6OEzcQjkqoFQ8VggAFlTygcpcWISpACKkvXAIhgRCIByjw" +
       "AqJUog8c4v5nxutdr50gnoi049mZ/5/5z+//N0dOowaXoQ2OTffkqM2jpMij" +
       "u+jGKN/jEDe6Lb5xFDOXZGIUu+4OWEsZPS+1//z7A2MdGmpMokXYsmyOuWlb" +
       "7nbi2nSCZOKo3V8dpCTvctQR34UnsF7gJtXjpsv74+isACtHvXFPBB1E0EEE" +
       "XYqgD/hUwLSAWIV8THBgi7u70V0oEkeNjiHE42hN5SEOZjhfOmZUagAnNIv3" +
       "naCUZC4ytLqsu9K5SuGHN+gzj97W8XIdak+idtNKCHEMEILDJUnUlif5NGHu" +
       "QCZDMknUaRGSSRBmYmpOSbmTqMs1cxbmBUbKRhKLBYcweadvuTZD6MYKBrdZ" +
       "Wb2sSWjGe2vIUpwDXZf4uioNt4h1ULDVBMFYFhvEY6kfN60MR6vCHGUde68H" +
       "AmBtyhM+ZpevqrcwLKAu5TuKrZye4My0ckDaYBfgFo6Wz3mosLWDjXGcIymO" +
       "loXpRtUWULVIQwgWjhaHyeRJ4KXlIS8F/HP6xisP3GENWZqUOUMMKuRvBqbu" +
       "ENN2kiWMWAZRjG3r44/gJW/u1xAC4sUhYkXz6p1nrr2w+8S7iubcGjQj6V3E" +
       "4CnjcHrhRytifZvqhBjNju2awvkVmsvwHy3t9BcdyLwl5RPFZtTbPLH97Zvv" +
       "eZ6c0lDrMGo0bFrIQxx1GnbeMSlhW4lFGOYkM4xaiJWJyf1h1ATzuGkRtTqS" +
       "zbqED6N6KpcabfkOJsrCEcJETTA3raztzR3Mx+S86CCEmuBBG+FpQOpP/nLk" +
       "6GN2nujYwJZp2TrELsHMGNOJYacYcWx9MDaip8HKY3nMxl3dLVhZak+mjILL" +
       "7bzuMkO3Wc5b1g2bET1dMMYJ1xOOyTDdLF9GWIawqIg853+4syjs0DEZiYCL" +
       "VoQBgkJuDdkUaFPGTGHz4Jmjqfe1csKULMhRH1wZLV0ZFVdG1ZXRqitRJCJv" +
       "OltcrQIB3DgOgABQ2daXuHXb7ft76iACncl68IEg7QHVS/IMGnbMR41hiY0G" +
       "hO6yJ2/ZF/31mWtU6OpzQ3xNbnTi4OS9O+++WENaJVYL/WCpVbCPCoQtI2lv" +
       "OEdrndu+77ufjz0ybfvZWgH+JRCp5hQg0BP2BLMNkgFY9Y9fvxofT7053auh" +
       "ekAWQFOOIfoBqLrDd1SAQb8HrEKXBlA4a7M8pmLLQ8NWPsbsSX9FhshCMXSp" +
       "aBEODAkoMXnL6yceO/74hk1aEL7bAwUxQbgCg07f/zsYIbD+xcHRhx4+ve8W" +
       "6XygWFvrgl4xxgAawBtgsfve3f35yS8Pf6L5AcOhRhbS1DSKcMZ5/i0AHBTA" +
       "S7i19yYrb2fMrInTlIi4+6N93SXHfzjQoRxFYcXz84X/foC/fs5mdM/7t/3S" +
       "LY+JGKJw+Zr7ZMoAi/yTBxjDe4QcxXs/XvnYO/gJwFXAMtecIhKekNQMSdNH" +
       "pUf65HhRaO9iMax2qvaKcmVZ6U2+rJFjrxjOV3YT0wuClBE5X8zRiqrkDuSz" +
       "sPLKuQqWLLaH987MZkaeukTlZldlERiEHufFT//8IHrwq/dq4EoLt52LKJkg" +
       "tEIwuLICE26QtdzPjPufe+FV/tGGK9SV6+eGgzDjO3u/X77j6rHb/wMSrAop" +
       "Hz7yuRuOvLf1PONBDdWVQaCqPalk6g+aAS5lBPopSxhUrLRKX3dLATrBHOJB" +
       "PfA0loqX/BW7ixwxnq1SVgyXhYJHk/bUwJ5983TFzMxDoZ4odRL6dNfJ8UPf" +
       "vahsG247QsRk/8z9f0cPzGiB3mxtVXsU5FH9mRR5gVLxb/iLwPOXeIRqYkHV" +
       "565YqUlYXe4SHEdE5Jr5xJJXbPn22PQbz07v00q5s4mjOugmxXRILlw3T6KN" +
       "iGGAo84c4SoXEmR3QQAs3L1ublPKRFeWmX167Yd3z679GiyTRM2mC236AMvV" +
       "6P4CPD8eOXnq4wUrj0rAr09jV8VDuG2u7oorml2pQZsjf4bK6kXKqTVPKGwR" +
       "1wSq7m8jNL33m1+ld6uypUZ0hPiT+pFDy2NXn5L8fgET3KuK1c0ImMjnvfT5" +
       "/E9aT+NbGmpKog6j9Jm0E9OCqCVJMILrfTvBp1TFfmWbr3ra/nJurgiHZ+Da" +
       "cOkMZmk9r8jPhU4xgmT+JWsDriYBl8OBpoVpEcoWJVZOdaVDYkg4xapELSGy" +
       "qh1CfMBa2yKiDHl7qrEy7Wj5www2i1V+Fu9XqSBI1IKGYLjn5tkzxQAmbTCE" +
       "IEpuCKJVtevmYN7hstJNvbb0lSufmf1SFu4iqlGzILuqmkdx8rKqb1j13WUc" +
       "nW1vXjp702cqO7xvoxb4QMkWKA16KjBvdBjJmlKTFs9v4gdqz9I5elrwlJpI" +
       "mW1FL777w/Qc1YufINkER2cFyDhqKs2CRHsAioBITKccz60dvstVxBZRRVV3" +
       "Kmt8sH0SaSz/P+BVqIL6D0HKODa77cY7zlz+lCx34EE8NSW/JwExVFNYrnJr" +
       "5jzNO6txqO/3hS+1rPMAtaJdDMom5vQfNGb4wI0RAAA=");
}
