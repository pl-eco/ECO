package org.sunflow.math;
final public class MathUtils {
    private MathUtils() { super(); }
    final public static int clamp(int x, int min, int max) { if (x > max)
                                                                 return max;
                                                             if (x > min)
                                                                 return x;
                                                             return min; }
    final public static float clamp(float x, float min, float max) { if (x >
                                                                           max)
                                                                         return max;
                                                                     if (x >
                                                                           min)
                                                                         return x;
                                                                     return min;
    }
    final public static double clamp(double x, double min, double max) { if (x >
                                                                               max)
                                                                             return max;
                                                                         if (x >
                                                                               min)
                                                                             return x;
                                                                         return min;
    }
    final public static int min(int a, int b, int c) {
        if (a >
              b)
            a =
              b;
        if (a >
              c)
            a =
              c;
        return a;
    }
    final public static float min(float a, float b,
                                  float c) { if (a >
                                                   b)
                                                 a =
                                                   b;
                                             if (a >
                                                   c)
                                                 a =
                                                   c;
                                             return a;
    }
    final public static double min(double a, double b,
                                   double c) { if (a >
                                                     b)
                                                   a =
                                                     b;
                                               if (a >
                                                     c)
                                                   a =
                                                     c;
                                               return a;
    }
    final public static float min(float a, float b,
                                  float c,
                                  float d) { if (a >
                                                   b)
                                                 a =
                                                   b;
                                             if (a >
                                                   c)
                                                 a =
                                                   c;
                                             if (a >
                                                   d)
                                                 a =
                                                   d;
                                             return a;
    }
    final public static int max(int a, int b, int c) {
        if (a <
              b)
            a =
              b;
        if (a <
              c)
            a =
              c;
        return a;
    }
    final public static float max(float a, float b,
                                  float c) { if (a <
                                                   b)
                                                 a =
                                                   b;
                                             if (a <
                                                   c)
                                                 a =
                                                   c;
                                             return a;
    }
    final public static double max(double a, double b,
                                   double c) { if (a <
                                                     b)
                                                   a =
                                                     b;
                                               if (a <
                                                     c)
                                                   a =
                                                     c;
                                               return a;
    }
    final public static float max(float a, float b,
                                  float c,
                                  float d) { if (a <
                                                   b)
                                                 a =
                                                   b;
                                             if (a <
                                                   c)
                                                 a =
                                                   c;
                                             if (a <
                                                   d)
                                                 a =
                                                   d;
                                             return a;
    }
    final public static float smoothStep(float a,
                                         float b,
                                         float x) {
        if (x <=
              a)
            return 0;
        if (x >=
              b)
            return 1;
        float t =
          MathUtils.
          clamp(
            (x -
               a) /
              (b -
                 a),
            0.0F,
            1.0F);
        return t *
          t *
          (3 -
             2 *
             t);
    }
    final public static float fastPow(float a, float b) {
        float x =
          Float.
          floatToRawIntBits(
            a);
        x *=
          1.0F /
            (1 <<
               23);
        x =
          x -
            127;
        float y =
          x -
          (int)
            Math.
            floor(
              x);
        b *=
          x +
            (y -
               y *
               y) *
            0.346607F;
        y =
          b -
            (int)
              Math.
              floor(
                b);
        y =
          (y -
             y *
             y) *
            0.33971F;
        return Float.
          intBitsToFloat(
            (int)
              ((b +
                  127 -
                  y) *
                 (1 <<
                    23)));
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169105480000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAL1ZfWwcxRUf353t+CM6x0lsEyBOHNOUfNwWRIoapyLGOOTI" +
       "JnF8SUgcglnvzp03\n2dvd7M7ZFyeioZVwgJY2gkq0aiGqooaPUFBpFSqlNC" +
       "ihTckfDVVLJVoobaSCVECq2tJU8Effm9m7\n3Vufj9Kza2ne7c6892Z+72tn" +
       "xqfeJ7WuQ65R3QQ7aFM30ZcaUByXan2G4rrboWtYfaW2YeDkJtOK\nkBqZRH" +
       "SNkbisupKmMEXSNSl5W0/eISttyziYMSyWoHmW2Ges8fTdIa+ZovDOx0+33n" +
       "ci1hkhtTKJ\nK6ZpMYXpltlv0KzLSIu8TxlTpBzTDUnWXdYjk7nUzGX7LNNl" +
       "isncA+ReEpVJna2iTkaWyoXJJZhc\nshVHyUp8emmATwsa5juUKbpJtd7idC" +
       "C5qlQSlu3JDU7lBiVzcHAnwOErANRLiqgF2ilQ7eiTOz9/\n+PhTURIfInHd" +
       "TKEyFZAwmG+INGdpdoQ6bq+mUW2IzDMp1VLU0RVDn+CzDpFWV8+YCss51B2k" +
       "rmWM\nIWOrm7Opw+csdMqkWUVMTk5lllO0UVqnhlZ4q00bSgZgt/mwBdwN2A" +
       "8AG3VYmJNWVFoQie3XTfB4\nZ1iiiLF7EzCAaH2WslGrOFXMVKCDtApfGoqZ" +
       "kVLM0c0MsNZaOZiFkUXTKkVb24q6X8nQYUY6wnwD\nYgi4GrghUISRhWE2rg" +
       "m8tCjkpYB/VrZ9ePTJ77y0nsd2TKOqgetvBKHFIaFBmqYONVUqBK/kEo8m\n" +
       "d+euiRACzAtDzIKn97rTO+R3f9YpeK4uw7N1ZB9V2bC65Vjn4KHbLRLFZcyx" +
       "LVdH55cg5+kw4I30\n5G3I2raiRhxMFAbPDv5895Gn6V8jpDFJ6lTLyGUhju" +
       "apVtbWDercTk3qKIxqSdJATa2PjydJPTzL\nEPKid2s67VKWJDGDd9VZ/B1M" +
       "lAYVaKIGeNbNtFV4thU2yp/zNiFkLjTSCi1KxB//ZWR1QnJzZtqw\nxiXXUS" +
       "XLyRTfs6BA2gxkB2BxExg2NiNJadTKUklRFVM3LSmjQ6Kq1mqNjuHvp1GWx9" +
       "W1jtfUYLkL\np60BEb/RMjTqDKsnL796uH/TA0dFSGAYe7ggYGGOhDdHAudI" +
       "FOcgNTVc9QKcS/gDrLkf8hIqWPP1\nqb133HO0C6yQt8djYApk7QIE3gL6Va" +
       "vPT94kr3MqRFDH9/ZMJq6cvEVEkDR9jS0r3XTp2YvH/968\nIkIi5QsgAoMS" +
       "3IhqBrBqFgtbdzhlyun/4MHNL7x+8c3P+snDSPeUnJ4qiTnZFXaBY6lUgyrn" +
       "qz9x\nVTx6J9l5LEJikOhQ3Pj6oW4sDs9Rkps9hTqHWOpl0pS2nKxi4FChOD" +
       "WyUcca93t4bLTw5/ngnGYM\n1oXQYl708l8cXWgjbROxhN4OoeB19MpX6j73" +
       "uzNNr3CzFEpuPPBRS1EmEnieHyzbHUqh/83HBh75\n5vuTe3ikeKHCSL3t6G" +
       "OQsnmQ+YwvA6lrQPlAT3bvMLOWpqd1ZcSgGHIfx6+74cfvPdwifGNAT8G1\n" +
       "qz5Zgd9/1a3kyMW7/7WYq6lR8dPh4/DZBJz5vuZex1EO4jry9/362m/9Qvku" +
       "VDaoJq4+QXmBIBwa\n4YaUuOFXcJoIjd2ApAt0r5om9st8qIfVw09nunIHfv" +
       "kTvuomJfjFD/phs2L3CNcjWYbWbQ+n70bF\nHQW+m85uuavFOPsRaBwCjSp8" +
       "IN2tDtSLfIkXPe7a+jdePtd2z2tREtlAGg1L0TYoPAFIA0QedUeh\n1OTtW9" +
       "bz4GoZn4OUQybcCIs8A+RL3vhLN6fLRWREGISXbiqQdnV2bsTQ1ZCEQ66d7t" +
       "PHP9uTu/7W\nfL9yfq8oL62ln5N+2HK9c/AcXb7ua38qUwsbmGWvNugYNQJz" +
       "zsUpS8raZr4r8JP6waeeOc1eW7lW\nTLli+ooWFlyx9vhE59rnHvofillnyA" +
       "hh1fPGrt4WHdUvRPjGRdSxKRueUqGeoDlgUlhPzjHRsNjT\nzKNqSbGgdKA7" +
       "1kKr9wpKffmCMsXDUXxeh+SL4GOXbx7zoXSJCk6wfEdwF+/oWdgNjPGSePn+" +
       "rp9e\n2PHEpDD69RW26kGpYfVLf3x7f/TrL48IufCOKMR8bPGJv7xweXCBiB" +
       "WxbVw2ZecWlBFbR449bmO0\nLq00A+c+v3LpqXsH3+IrQrn1jERhz4qPKU54" +
       "75YKFWUXko2QOXB4yHLrpzjfjUiSwrhrPjE1+ctt\npQ6+FVrcc3D80zsYiT" +
       "ydc/F9qy1Gt2PaQ1HhqFVOuPqRCqgzSPYGUas+6rurQb0BWruHun12Uddp\n" +
       "FlQ5nmEOJ1z/gQqwx5AYQdiODztbDeyboHV6sDtnFrYfzH5EH6kA8stIDkEi" +
       "ZHUzFM+Hq4G4Dtpy\nD+LymYeohsL3qxUgPoxk0ocYCN6j1UBEGcmDKM08RC" +
       "cUqo9VgPhtJI/4EAOB+mg1EPuh3exBvHmG\nIMY4VyzkRd+VJyrg/D6SJ8q6" +
       "8ni1Cdnr4eyd/YR8vgLEHyJ5BiGKU2cgIU9Vm5BJD2Jy9hPyTAWI\nLyE57U" +
       "MMePHFahNymwdx2+wn5IUKEF9Fcs6HGEjI89Um5G4P4u7/U0L+pgLO15H8qq" +
       "wrL1WDsw+a\n6uFUZz9a364A8c9Ifs9Io5u1LDaaYjS8BfpDNUixrDIPKZsh" +
       "pBFxwguBfK8CyA+QvMNIfVpx2YA1\nHkL47n+LMA9nu+K1Fh7rO6ZccouLWV" +
       "V+49BdH8q//Te/oClenjbJZE46ZxiB41HwqFRnOzSt8xU3\nicO3zX/+yUhL" +
       "+HqNkRj+8OX9Q7BdYaQpwAZwvacg00cQzcCEjx/bfKAdtPNjOt5EJ8S1a+lh" +
       "GZEu\nKzkZ8f8jFI6MOfGfhGF117N7luQf2v4Nfg7FfeXEBKpplMHw/KKJa8" +
       "Vj59JptRV0XSLPP7fzzA++\nUDjN8HuIBYGQKYm6G8VoBTfCUbf85U5/1mb8" +
       "OmbixfYfrTv5+FsRfr/0H5+ie2j+GQAA");
}
