package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class BoxFilter implements Filter {
    private float s;
    public BoxFilter(float size) { super();
                                   s = size; }
    public float getSize() { return s; }
    public float get(float x, float y) { return 1.0F; }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026718000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Ye2wURRif3vVBH6bXUspTSkt5COVWk0qUEhVrCycH1JYW" +
                                                   "KY8y3Z27btnbXXbn\n2qMQHzERhPggaqKJAjEkBZ8kaNAEEQIoQkzARE1MQA" +
                                                   "2JmigmxkQx+offzNze3u5dK6HJ7c7ufI/5\n5vt9v/m2b19HRbaFZsh2mG43" +
                                                   "iR1u7erAlk2UVg3b9lp41SefKyrtGF2pGwFUEEUBVaGoMirbkoIp\nllRFij" +
                                                   "zUkrLQQtPQtsc1g4ZJioYHtbvT9h6O3p1jcN3+49VPHiqsC6CiKKrEum5QTF" +
                                                   "VDb9NIwqYo\nFB3EQ1hKUlWToqpNW6LoNqInE62GblOsU3sbegwFo6jYlJlN" +
                                                   "iuqjjnMJnEsmtnBC4u6lDu4WLEy0\nCMWqTpRlGXeg2eTVhGWn9TpzpcHIBD" +
                                                   "bZA+HwFUDUszJRi2hzQjWDh3sW7zx4JIgqe1GlqncxYzJE\nQsFfL6pIkEQ/" +
                                                   "sexlikKUXlSlE6J0EUvFmjrCvfaialuN65gmLWJ3EtvQhphgtZ00icV9Oi+j" +
                                                   "qEJm\nMVlJmRpWZo9iKtEU56kopuE4hF3rhi3CbWfvIcAyFRZmxbBMHJXCra" +
                                                   "oOGa/za2RibFwJAqBakiB0\nwMi4KtQxvEDVIpca1uNSF7VUPQ6iRUYSvFA0" +
                                                   "bUyjbK9NLG/FcdJH0RS/XIeYAqlSvhFMhaJJfjFu\nCbI0zZelrPwsrP1z9+" +
                                                   "HXTj7AsV2oEFlj6y8DpZk+pU4SIxbRZSIUbyTDL0XWJ2cEEALhST5hIbNs\n" +
                                                   "zvHu6M+f1AmZ6Xlk1vQPEpn2yav31XXuWG6gIFvGBNOwVZZ8T+S8HDrSMy0p" +
                                                   "E6q2NmORTYadyVOd\nn65/4k3ySwCVRVCxbGjJBOCoSjYSpqoRaznRiYUpUS" +
                                                   "KolOhKK5+PoBIYRwHy4u2aWMwmNIIKNf6q\n2ODPsEUxMMG2qBTGqh4znLGJ" +
                                                   "6QAfp0yEUAn80Hz4FSHxx+8UNYclO6nHNGNYsi1ZMqx45lk2LCKB\ndUCG9K" +
                                                   "CRauejMEOPSVGHNGAkiIRlrKu6IcVVqFfZWKSQIXa/BZspttbq4YICRn7+It" +
                                                   "YA/ysMTSFW\nnzx67cLOtpXP7BYAYaBORwm0A67CaVdh5iosXIUzrlBBAfdQ" +
                                                   "w1yKJMEWb4ViBVqruKNr08NbdjcE\nAR3mcCHsDxNtgHjS62iTjVa3oiOc/G" +
                                                   "SA1ZQ3NuwK3xi9X8BKGpt482qXX3rn4sE/KhYEUCA/K7L4\ngJfLmJkORqUZ" +
                                                   "tmv011E++7/tWXXs64tX5rsVRVFjTqHnarJCbfBnwjJkogD1ueYPTa0MrkM9" +
                                                   "+wKo\nEKofGI+vH8hkpt+Hp2BbHPJjsZREUXnMsBJYY1MOY5XRAcsYdt9wiI" +
                                                   "T4eCIkp5whuAZ+E9KQ5nc2\nO8lk11oBKZZtXxScXG88VXznNyfKz/FtcXi4" +
                                                   "Muuk6yJUVHWVC5a1FiHw/sorHS++fH3XBo6UNFQo\nHH/Jfk2VU6Ay11WBct" +
                                                   "aAUlgiG7v1hKGoMRX3a4Qh7t/KOXd98OtzIZEaDd44mW36fwPu+6kPoicu\n" +
                                                   "bv5rJjdTILPjxA3DFRPRTHQtL7MsvJ2tI/Xkl7e/+hl+HdgOGMZWRwgnjYJM" +
                                                   "EUzJbkcsNQG0NsTT\neO3pho/Pdx/YJaB/xzg9R7ZWn/z4d99vDT5/ul/o+a" +
                                                   "ndJ7xv5qEfj13rrBHbJM6/2TlHULaOOAM5\nACpNlpD68Txw6bML699+rPNq" +
                                                   "ekXVXiZvg27np+1nyLylz/6Qh3gAPgam3J3E8bmAX8MMkHwPEZ9b\nwi4NsJ" +
                                                   "qmMfYpT5PTJ+98M96Q3Pb5R9xvOc7ulrLhugqbIuAQu8xmQU/2s9wKbA+AXP" +
                                                   "Op1RtD2ql/\nwGIvWJShubDXWMCuKQ/Y09JFJd+ePlO75XIQBdpRGQSqtGPO" +
                                                   "E6gUCpTYA0DMKfP+B3gNhoZZFYZ4\nyIhvwrT0BqSynhikxsFKO2uRXIbp62" +
                                                   "86HL2w5nW+AWMSZB4Y+eyMnOzef+MLepXbcZmKadenck8d\naCtd3Xu+Hqoq" +
                                                   "PnogEUAlvSgkpxvfHqwlGR/0Qp9mO90wNMeeeW/PJRqMlgwTz/DjOMutnyNd" +
                                                   "0MGY\nSbNxhY8WKxxaLE7TYrGfFgsQH6zgKo38Oi9DYiWmpQ5h1gxDlthUs2" +
                                                   "BRdm1hl4hI5H35Es6XMteT\naDaeTNHknKNZHMgMpreP1Q3yutz16O8VT+Oz" +
                                                   "mwLpIlpMAXeGuUgjQ0TLchVgljxn9Sre/7o53HPk\nreP08sIlosIXjI0/v+" +
                                                   "KCJQdH6pa8t/cWTug6X2x+01VD0x8JDqjnA7xFF5DIae29Si1eIJTBepKW\n" +
                                                   "vtYDh1neU3KagwvnnnNKuonLT12bx5nbwi4bADpxQrvg7OCocQGzcRzAeBiC" +
                                                   "P6zzrnyuI+vcb2rl\nAYGIDHybuejAODEMsguUahBi8K0/drPrh5IpzXSa7K" +
                                                   "idkvMxKj6g5Oi3Ozb+Gf3qb94zZT5yyuFL\nI5bUtOwqzxoXmxaJqXy15aLm" +
                                                   "TX7blq+6RONL2ecmG/B1mkKewge9X56iQnbLFhumqDxLDNKbHmUL\njcCOgR" +
                                                   "Ab7jCdWg/xI4SxXViwXcqzTWxnZnuKj/9/wCmQpPgPQZ/86DsbZqX2rn2BV1" +
                                                   "2RrOGREf4p\nCF+2olfMFFn9mNYcW5fQ0fd6Trx7r0Mi/IysSbnU5sFgi5gd" +
                                                   "J+1Q2PkbtLaESXlLNfLh5PeXju6/\nGuAt4n9VU0bj1hEAAA==");
}
