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
      1163966490000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfW/83Tnx2yB/SxElsh8YJ3EKrVEpNaR3XwQ4H" +
                                                    "tuIkUg+Bmdubu9t4b3czO2dfDC4kEk2KqhS1BgKi/lCFUmhIUNUIUIWUL/wT" +
                                                    "CAmEWvVDSdsvRU0jNR9KUWlL35u5293buzPql1raudmZ9+b9mfd+763PXSUt" +
                                                    "Hie7XMc6lrMckWAlkThi7U6IYy7zEvuTu6co91hm1KKedxDWZoz+l7o/+eyx" +
                                                    "fFwjrSmyhtq2I6gwHds7wDzHmmOZJOkOVscsVvAEiSeP0DmqF4Vp6UnTE8NJ" +
                                                    "cl2IVZDBZEUFHVTQQQVdqqCPBFTAtIrZxcIoclBbeEfJd0ksSVpdA9UTZFv1" +
                                                    "IS7ltFA+ZkpaACe04/thMEoylzjZ6tuubK4x+PFd+tKT98V/2US6U6TbtKdR" +
                                                    "HQOUECAkRboKrJBm3BvJZFgmRXpsxjLTjJvUMhek3inS65k5m4oiZ76TcLHo" +
                                                    "Mi5lBp7rMtA2XjSEw33zsiazMpW3lqxFc2DrusBWZeE+XAcDO01QjGepwSos" +
                                                    "zbOmnRFkS5TDt3HwTiAA1rYCE3nHF9VsU1ggveruLGrn9GnBTTsHpC1OEaQI" +
                                                    "srHhoehrlxqzNMdmBNkQpZtSW0DVIR2BLIKsjZLJk+CWNkZuKXQ/V+++7fQD" +
                                                    "9ritSZ0zzLBQ/3Zg6oswHWBZxpltMMXYtTP5BF332imNECBeGyFWNC8/eO1b" +
                                                    "N/VdekvRfKkOzWT6CDPEjHE2vfr9TaNDe5pQjXbX8Uy8/CrLZfhPlXeGSy5k" +
                                                    "3jr/RNxMVDYvHXjjOw+/wK5opHOCtBqOVSxAHPUYTsE1LcbvYDbjVLDMBOlg" +
                                                    "dmZU7k+QNpgnTZup1cls1mNigjRbcqnVke/goiwcgS5qg7lpZ53K3KUiL+cl" +
                                                    "lxDSBg/ZDU8LUX/yV5C8fsiDcNepQW3TdnQIXka5kdeZ4cykwbv5AuWznm4U" +
                                                    "PeEUdK9oZy1nXve4oTs8578bDmd6umjMMqEfoHbGKeyVL5M8w3gCI879P8oq" +
                                                    "od3x+VgMrmRTFBAsyKVxxwLaGWOpuHfs2vmZdzQ/QcoeE2QIRCbKIhMoMqFE" +
                                                    "JmpEklhMSroeRauLh2ubBQAAaOwamr53//2n+psg4tz5ZvA5kvaDxWV9xgxn" +
                                                    "NECJCYmFBoTqhp/eczLx6XPfVKGqN4b0utzk0pn544cfukUjWjU2o32w1Ins" +
                                                    "U4ioPnIORnOy3rndJz/+5MITi06QnVVgXwaNWk5M+v7oTXDHYBmA0eD4nVvp" +
                                                    "xZnXFgc10gxIAugpKEQ7AFNfVEZV8g9XgBRtaQGDsw4vUAu3KujXKfLcmQ9W" +
                                                    "ZIisxqFXRQteYERBicH7Xr301MWnd+3RwnDdHSqA00yo5O8J7v8gZwzWf39m" +
                                                    "6sePXz15j7x8oBioJ2AQx1GAArgN8Ngjbx393eWPzn6oBQEjoCYW05ZplOCM" +
                                                    "GwMpABQWgBVe6+Ahu+BkzKxJ0xbDuPtX9/ZbL/71dFxdlAUrlXu+6YsPCNZv" +
                                                    "2Esefue+f/TJY2IGFqrA8oBMOWBNcPII5/QY6lE6/sHmp96kPwEcBezyzAUm" +
                                                    "4YhIy4h0fULeyJAcb47s3YLDVrdmryRXNpTf5Ms2OQ7i8GXlN5zuCFPG5Hyt" +
                                                    "IJtqkjuUz+jlzY0KlCyuZ08sLWcmn71V5WZvNeiPQU/z4m/+/W7izB/eroMr" +
                                                    "HcJxb7bYHLNCijWhyCpMuEvW7iAzHn3+Fy+L93d9XYnc2RgOooxvnvjLxoO3" +
                                                    "5+//H5BgS8T46JHP33Xu7TtuNH6kkSYfBGrakWqm4bAbQChn0D/Z6FBc6ZR3" +
                                                    "3ScV6AF34EP64WktFyv5i7trXByvVymLw1cjwaNJf2rgz6EVumBuFqAwz5U7" +
                                                    "B32x9/LsMx+/qHwbbTMixOzU0qOfJ04vaaFebKCmHQrzqH5MqrxKmfg5/MXg" +
                                                    "+Q8+aBouqHrcO1puCrb6XYHrYkRuW0ktKWLfny8s/vrniye1cu7sEaQJukec" +
                                                    "jsuFb6+QaJM4jAjSk2NC5cI0O1pEgAXZ2xu7Uia68szyzwbee2h54I/gmRRp" +
                                                    "Nz1oy0d4rk63F+L527nLVz5Ytfm8BPzmNPVUPETb5NouuKq5lRZ0ufJn3Dcv" +
                                                    "5pfbFUJhH4oJVd1/TlrpE3/6VN5uTbbUiY4If0o/98zG0duvSP6ggCH3llJt" +
                                                    "MwIuCni/8kLh71p/6+saaUuRuFH+LDpMrSLWkhQ4wat8K8GnU9V+dVuvethh" +
                                                    "Pzc3RcMzJDZaOsNZ2iyq8nO1W4oRmX+p+oCrScAVcKBpU6sEZctidk51oeM4" +
                                                    "TLulmkQtI7KqHag+YK1jMyxDlT3VWJlOwv8Qg81SzT3j+zdUEEzXg4ZwuOdW" +
                                                    "2DNxAJe2GKiI0huCaEv9ujlWcIWsdAuvrP/Vbc8tfyQLd4nU1iyloA9ycVxc" +
                                                    "A89AGeQG6oJcg9omSJvLzTnAh1J9BPSdrnJfrGDwHA7wbdMEHYDk+ELdJUDf" +
                                                    "AM+Osu47GgK0GxEcvq0xHCbU4XcKaFMcOyc5H1xB2+M4HBPwSQgf1XkzKwFu" +
                                                    "sU6LAGBW06vjRW6o+ReB+qw1zi93t69fPvRbBUaVT88O+P7LFi0rnBiheavL" +
                                                    "WdaUmnVU0gR/vifI+gafEJAYaiJ1fkTRf1+QeJQeXII/YbIfCHJdiAzCoDwL" +
                                                    "E/0Q7hKIcPqYW8mieJBhCiBKpKqJcqtbqnC3iqgp//1SaQiK6h8wM8aF5f13" +
                                                    "P3Dta8/K7gIShi4s4CntANCqB/ebim0NT6uc1To+9Nnqlzq2V+pXVXce1g3n" +
                                                    "1n8B0RlxZuwSAAA=");
}
