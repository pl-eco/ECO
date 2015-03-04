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
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXW2wUVfTu9F0KfSAPEQqUopbHjmg0wfoqtdjiahuKJK6R" +
                                                    "ejt7dzv07swwc7ddqvVBYiB+EKNV0Wg/DIgPBGMkaowJPwpGY6IxGj8U9Uci" +
                                                    "ksiHaHyfc+/uzuzstsYPN5m793HOPe/HPXKOVHkuWevYfHeK2yLKsiK6k18d" +
                                                    "Fbsd5kW3xK4eoK7HEt2cet422Bsy2l5rvPD7oyNNGqmOk/nUsmxBhWlb3lbm" +
                                                    "2XyMJWKk0d/t4SztCdIU20nHqJ4RJtdjpic6Y2ROAFWQ9lieBR1Y0IEFXbKg" +
                                                    "d/lQgDSXWZl0N2JQS3i7yP0kEiPVjoHsCbKy+BKHujSdu2ZASgA31OJ6Owgl" +
                                                    "kbMuWVGQXclcIvATa/Wpp3Y0vV5BGuOk0bQGkR0DmBBAJE4a0iw9zFyvK5Fg" +
                                                    "iThpthhLDDLXpNyckHzHSYtnpiwqMi4rKAk3Mw5zJU1fcw0GyuZmDGG7BfGS" +
                                                    "JuOJ/KoqyWkKZF3oy6ok3Iz7IGC9CYy5SWqwPErlqGklBFkexijI2H4rAABq" +
                                                    "TZqJEbtAqtKisEFalO04tVL6oHBNKwWgVXYGqAiyZMZLUdcONUZpig0JsjgM" +
                                                    "N6COAKpOKgJRBFkQBpM3gZWWhKwUsM+526/bf6/Va2mS5wQzOPJfC0itIaSt" +
                                                    "LMlcZhlMITasiT1JF767TyMEgBeEgBXMm/edv2ld64lTCuaSMjD9wzuZIYaM" +
                                                    "g8PzPlna3bGxAtmodWzPROMXSS7dfyB30pl1IPIWFm7Ew2j+8MTW9+988GV2" +
                                                    "ViP1faTasHkmDX7UbNhpx+TMvYVZzKWCJfpIHbMS3fK8j9TAPGZaTO32J5Me" +
                                                    "E32kksutaluuQUVJuAJVVANz00ra+blDxYicZx1CSA185Br4qoj6yX9Bdugj" +
                                                    "dprp1KCWadk6+C6jrjGiM8PWPZp2OFjNy1hJbo/rnmvotpsqrA3bZfpwxhhl" +
                                                    "Qu81OYSN2CRX/W6CuVH0M+d/p5BFGZvGIxFQ/9Jw8HOIm16bA+yQMZXZ1HP+" +
                                                    "6NCHWiEYctoRZA3QjOZoRpFmVNGMltIkkYgkdRHSVlYGG41CtEMebOgYvHvL" +
                                                    "PfvaKsC9nPFKUDCCtoGkOYZ6DLvbTwl9MvEZ4JeLn79rb/TXwzcqv9Rnzt9l" +
                                                    "scmJA+MPbX/gCo1oxYkYBYStekQfwPRZSJPt4QAsd2/j3jMXjj05afuhWJTZ" +
                                                    "cxmiFBMjvC1sCtc2WAJypn/9mhX0+NC7k+0aqYS0AalSUHBtyEKtYRpFkd6Z" +
                                                    "z5ooSxUInLTdNOV4lE919WLEtcf9Hekj83BoUe6CBgwxKBPu5rdPPH38mbUb" +
                                                    "tWBubgxUu0EmVKQ3+/bf5jIG+18dGHj8iXN775LGB4hV5Qi049gNcQ/WAI09" +
                                                    "fGrXl6e/PviZ5juMgAKYGeamkYU7LvWpQFbgkJnQrO13WGk7YSZNOswZ+t0f" +
                                                    "jas3HP9xf5MyFIedvJ3X/fsF/v7Fm8iDH+74pVVeEzGwKvmS+2BKAfP9m7tc" +
                                                    "l+5GPrIPfbrs6ZP0OUiakKg8c4LJ3EOkZESqPiot0iHH9aGzK3BY4ZScZeXO" +
                                                    "4txKLlbKsR2Hy5TecHp5EDIi5wsEWVoS3YF4Ri0vm6kayUp6cM/UdKL/0AYV" +
                                                    "my3FGb4HGphXP//zo+iBbz4ok1jqhO2s52yM8SLGgGRRTrhNFmo/Mh556ZU3" +
                                                    "xSdrr1Uk18ycDsKIJ/f8sGTbDSP3/IdMsDwkfPjKl2478sEtlxqPaaSikARK" +
                                                    "eo9ipM6gGoCoy6BZslChuFMvbd0qGWgGdeBH2uCrzlUm+Y+n8x0cL1Ihi8NV" +
                                                    "IefRpD410GfHLC2va6ahCo/l2gR9suX06LNnXlW6DfcUIWC2b+qRv6P7p7RA" +
                                                    "47WqpPcJ4qjmS7I8V4n4N/wi8P2FH4qGG6r4tnTnOoAVhRbAcdAjV87GliSx" +
                                                    "+ftjk++8OLlXy8XORkEqoFXEaa/cuHmWQOvHoUuQ5hTL1bZBtiuDCRZor55Z" +
                                                    "lTLQlWamX1j18QPTq74FzcRJrelBD97lpsq0dgGcn46cPvvp3GVHZcKvHKae" +
                                                    "8odwT1za8hZ1slKCBkf+9RbEixRCaxZX2IxkAlX3t34+vOe7X6V1S6KljHeE" +
                                                    "8OP6kWeXdN9wVuL7BQyxl2dLuxFQkY975cvpn7W26vc0UhMnTUbuDbSd8gzW" +
                                                    "kjgowcs/jOCdVHRe3MOrhrWzEJtLw+4ZIBsuncEorRRF8TnPyUaIjL94+YSr" +
                                                    "yYQr4ELTojwLZYszK6Vazl4cBp1sSaDmMrKqHcg+5FrbYliG8meqsTLtaOHV" +
                                                    "BYfZEjvj+nrlBIPlUkPQ3VOznJk4gEqrDGRE8Q1OtLx83exJO0JWuom3Fr1x" +
                                                    "3eHpr2XhzpIyNQusVNo94tWLS16o6lVlHJ1urF00fccXKjzyL586eH4kM5wH" +
                                                    "TRWYVzsuS5pSlLq84fAPis+iGbpaMJWaSKZtBY+v+jC8IJX4FwQbE2ROAEyQ" +
                                                    "mtwsCLQbchEA4XTCydu1ybe5ctksKSrrTnGRD/ZPGMfy9Z8vURn1/h8yjk1v" +
                                                    "uf3e89cckvUOTEgnJuRrEVKG6goLZW7ljLfl76ru7fh93mt1q/MZtahfDPKG" +
                                                    "c/4PENRtQGsRAAA=");
}
