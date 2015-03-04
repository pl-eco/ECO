package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class InvertedBucketOrder implements BucketOrder {
    private BucketOrder order;
    public InvertedBucketOrder(BucketOrder order) { super();
                                                    this.order = order; }
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = order.
                                                         getBucketSequence(
                                                           nbw,
                                                           nbh);
                                                       for (int i = 0; i <
                                                                         coords.
                                                                           length /
                                                                         2;
                                                            i +=
                                                              2) {
                                                           int src =
                                                             i;
                                                           int dst =
                                                             coords.
                                                               length -
                                                             2 -
                                                             i;
                                                           int tmp =
                                                             coords[src +
                                                                      0];
                                                           coords[src +
                                                                    0] =
                                                             coords[dst +
                                                                      0];
                                                           coords[dst +
                                                                    0] =
                                                             tmp;
                                                           tmp =
                                                             coords[src +
                                                                      1];
                                                           coords[src +
                                                                    1] =
                                                             coords[dst +
                                                                      1];
                                                           coords[dst +
                                                                    1] =
                                                             tmp;
                                                       }
                                                       return coords;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYXWwc1RW+O7bXP3Hin5CfhsRJHAfVMd2BVlCBEW2ychKH" +
                                                    "JbbsEIlFYbmevbue+O7MZOauvXFqApGqRDxECAwNFfVDFUqhIUFVI1pVSHkp" +
                                                    "PwIhgRCIB0jbl6KGSOShFAEtPefenZ+dXafqQy3N3Tv3nnPPOfec850zPneV" +
                                                    "tHguGXJsfrTIbZFiFZE6zG9LiaMO81L7MreNU9dj+TSnnncA1nJG/8tdX3z9" +
                                                    "+HS3RpJZsppali2oMG3Lm2CezWdZPkO6wtURzkqeIN2Zw3SW6mVhcj1jemI4" +
                                                    "Q1ZEWAUZyPgq6KCCDiroUgV9Z0gFTCuZVS6lkYNawjtCHiaJDEk6BqonyNba" +
                                                    "Qxzq0lL1mHFpAZzQhu8HwSjJXHHJlsB2ZXOdwU8N6Ys/e7D7t02kK0u6TGsS" +
                                                    "1TFACQFCsqSzxEpTzPV25vMsnyU9FmP5SeaalJvzUu8s6fXMokVF2WXBJeFi" +
                                                    "2WGulBneXKeBtrllQ9huYF7BZDzvv7UUOC2CrWtDW5WFu3EdDOwwQTG3QA3m" +
                                                    "szTPmFZekM1xjsDGgXuAAFhbS0xM24GoZovCAulVvuPUKuqTwjWtIpC22GWQ" +
                                                    "IsiGZQ/Fu3aoMUOLLCfI+jjduNoCqnZ5EcgiyJo4mTwJvLQh5qWIf67uv+v0" +
                                                    "MWuvpUmd88zgqH8bMPXFmCZYgbnMMphi7NyReZquffWURggQr4kRK5pXfnLt" +
                                                    "xzf3XXpD0dzYgGZs6jAzRM44O7Xq3Y3pwTuaUI02x/ZMdH6N5TL8x6s7wxUH" +
                                                    "Mm9tcCJupvzNSxOv3f/Ii+yKRjpGSdKwebkEcdRj2CXH5MzdwyzmUsHyo6Sd" +
                                                    "Wfm03B8lrTDPmBZTq2OFgsfEKGnmcilpy3e4ogIcgVfUCnPTKtj+3KFiWs4r" +
                                                    "DiGkFR7yQ3haiPqTv4Lk9Gm7xHRqUMu0bB1il1HXmNaZYeseLTkcvOaVrQK3" +
                                                    "53TPNXTbLQbvhu0yfapszDChj1qzEEAsv0u+jrl55qYw0Jz/v4gKWtk9l0iA" +
                                                    "AzbG059D5uy1OdDmjMXyrpFr53NvaUE6VO9HkCEQmqoKTaHQlBKaaiCUJBJS" +
                                                    "1g0oXDka3DQDCQ9Q2Dk4eWjfQ6f6myDCnLlmuGMk7QdbqxqNGHY6RIVRiX0G" +
                                                    "hOb6Xz5wMvXl8z9SoakvD+ENucmlM3OPHjx+i0a0WixGC2GpA9nHEUEDpByI" +
                                                    "52Cjc7tOfvrFhacX7DAba8C9ChL1nJjk/XFfuLbB8gCb4fE7ttCLuVcXBjTS" +
                                                    "DMgBaCkoRDcAUV9cRk2yD/vAiba0gMEF2y1Rjls+2nWIadeeC1dkkKyS8x5w" +
                                                    "ygqM/j542qrpIH9xd7WD4w0qqNDLMSskMO/+w6VnLv586A4tiuFdkao4yYRC" +
                                                    "hJ4wSA64jMH6x2fGn3zq6skHZIQAxbZGAgZwTAM+gMvgWn/6xpGPLn9y9n0t" +
                                                    "jCoBhbI8xU2jAmfcFEoB9OCAYOj7gfuskp03Cyad4gyD85uu7bde/Ox0t/Im" +
                                                    "hxU/GG7+7weE69/ZRR5568F/9sljEgZWr9DykExdwOrw5J2uS4+iHpVH39v0" +
                                                    "zOv0FwCuAGieOc8kRiWq+YJKrRFkY11ORpJQ+keXpDvkmEIHygOI3PsBDluc" +
                                                    "ur2KXFkfpObg8pm2Gyt1JEO/GuNTJ/76pTS7LscaFKgYf1Y/9+yG9N1XJH8Y" +
                                                    "7Mi9uVIPXdDVhLzff7H0D60/+SeNtGZJt1FtmQ5SXsaQykKb4Pl9FLRVNfu1" +
                                                    "JV/Vt+EgmTfGEy0iNp5mIWTCHKlx3hHLrE4/s5LVzErGMytB5GRYsvTLcTsO" +
                                                    "3/UDu9VxzVmK/Rg0Juhs3L1FJqQjpQ1EfSg3MQk2LddUyIbo7InFpfzYc7cq" +
                                                    "fO2tLdQj0Ie+9MG/3k6d+fObDapDu7Cd73E2y3gseDbV4Pq9st8KnfbYC795" +
                                                    "Rbw7dKcSuWP5QIszvn7i7xsO3D390P+A5ptjxsePfOHec2/uucl4QiNNge/r" +
                                                    "WshapuFaj3e4DHpe60CN3/sCv+ND+v0A8H/rEDV0X5i2mrxP7frJOO6aJWim" +
                                                    "Zqvdnr7Qe3nm2U9fUncbz7wYMTu1+Ni3qdOLWqR/3lbXwkZ5VA8tVV6pTPwW" +
                                                    "/hLw/BsfNA0XVA/Vm642cluCTs5xMCK3Xk8tKWL33y4s/PHXCye1KmqlBWmC" +
                                                    "jh+nE3Jh/3Ug7n4c7hGkp8iEgsZJdqSMRRJkb1/+KiUOq5tZ+tW2d44vbfsL" +
                                                    "3EyWtJkeZP9Ot9igQ4/wfH7u8pX3Vm46L4t28xT1VDzEP23qv1xqPkikBZ0q" +
                                                    "nycC86Il4HbHh4pcY6jQcDoIIFEwLcoBLZKcWUXV9U7gcMip1AVZtbiosoSQ" +
                                                    "CDhhWwwrnL+nGjvTTgUffrBZaajjHmXAoUZhHXUVv86ehcNhMMNARZTe4MDN" +
                                                    "jUvySMkRsojO/37d7+56fukT2RNUSINKB1Y2aF/x7PV1X8nqy844v9TVtm7p" +
                                                    "vg+Vb/2vr3b4BCqUOY/if2SedFxWMKUt7aoaqFspC7Jumb4afKUmUmuh6EHh" +
                                                    "7ji9IM34EyU7JsiKCBnUi+osSvQwJBIQ4fS44zu2O3S6qoMVEgF0bMeibzW9" +
                                                    "GSaR/A+Ej69l9T+InHFhad/+Y9duf06CNfiQzs/LL1aId9WWBhi9ddnT/LOS" +
                                                    "ewe/XvVy+3YfDlbh0FvtRaO64fzIfwCsGmjP7xEAAA==");
}
