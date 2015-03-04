package org.sunflow.image;
public class ConstantSpectralCurve extends SpectralCurve {
    private final float amp;
    public ConstantSpectralCurve(float amp) { super();
                                              this.amp = amp; }
    public float sample(float lambda) { return amp; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWxURRSe3f4vpX/81VraUgraFveKBA2WoLBpobjQhpZG" +
                                                    "F2GZ3p1tL71/zJ1tl2IVSQyEB2K0IBjsg4EgyF+MBI0h6YsCwReM0fggGF8k" +
                                                    "Ig88iERUPDN3d+/u3S3qi03u7ezMOTPnzPnOd849dRsVWBS1moa6Y0A1mJ/E" +
                                                    "mX+butTPdpjE8q8NLu3G1CKRgIotqxfmwnLjufK7998crPCiwhCagXXdYJgp" +
                                                    "hm5tIJahDpNIEJU7s+0q0SyGKoLb8DCWYkxRpaBisbYgmpamylBTMGmCBCZI" +
                                                    "YIIkTJBWOlKgNJ3oMS3ANbDOrO3oVeQJokJT5uYxNC9zExNTrCW26RYewA7F" +
                                                    "/HcfOCWU4xQ1pHy3fc5y+ECrNP7OloqP8lB5CJUreg83RwYjGBwSQqUa0foJ" +
                                                    "tVZGIiQSQpU6IZEeQhWsKqPC7hCqspQBHbMYJalL4pMxk1BxpnNzpTL3jcZk" +
                                                    "ZtCUe1GFqJHkr4KoigfA19mOr7aHHXweHPQpYBiNYpkkVfKHFD3CUL1bI+Vj" +
                                                    "0wsgAKpFGmGDRuqofB3DBKqyY6difUDqYVTRB0C0wIjBKQzVTLkpv2sTy0N4" +
                                                    "gIQZqnbLddtLIFUiLoKrMDTLLSZ2gijVuKKUFp/b65fv36mv0b3C5giRVW5/" +
                                                    "MSjVuZQ2kCihRJeJrVjaEjyIZ1/c60UIhGe5hG2ZC6/ceX5R3eRlW+bRHDJd" +
                                                    "/duIzMLy0f6ya7WB5mV53Ixi07AUHvwMzwX8uxMrbXETMm92ake+6E8uTm74" +
                                                    "4qVdJ8ktL/J1okLZUGMa4KhSNjRTUQldTXRCMSORTlRC9EhArHeiIhgHFZ3Y" +
                                                    "s13RqEVYJ8pXxVShIX7DFUVhC35FRTBW9KiRHJuYDYpx3EQIFcGDSuEpQfaf" +
                                                    "+M+QLg0aGpGwjHVFNyTALsFUHpSIbIQpMQ2pPdAl9cMtD2qYDlmSFdOjqjES" +
                                                    "lmMWMzTJorJk0IHktKRogAIpmdI9JtwlxWogRoeJn+PO/N9PjPM7qBjxeCA8" +
                                                    "tW5yUCGv1hhqhNCwPB5b1X7nTPiqN5Usidtj6DE40J840C8O9Oc8EHk84pyZ" +
                                                    "/GAbAhDAIaACIMnS5p7Na7fubcwD7Jkj+XD7XLQR3E5Y0y4bAYcvOsX+MoC2" +
                                                    "+v1Ne/z3jj9ng1aamtxzaqPJQyOv9732pBd5M1maewdTPq7ezbk1xaFN7uzM" +
                                                    "tW/5npt3zx4cM5w8zaD9BH1ka/L0b3THgRoyiQChOtu3NODz4YtjTV6UD5wC" +
                                                    "PMow4B4oqs59RgYNtCUplftSAA5HDaphlS8ledDHBqkx4swIgJSJcSUEZRrP" +
                                                    "i4ZEsiSTBvHVGSZ/z7QBxaPs8kJQdsenk4fPv9u6zJvO7uVp9bKHMJsrKh2Q" +
                                                    "9FJCYP77Q91vH7i9Z5NACEjMz3VAE38HgDkgZHCtb1ze/t2N60e/9jqoYlBC" +
                                                    "Y/2qIsdhj4XOKcArKoCVx75po64ZESWq4H6VcHD+Ub5g8flf9lfY0VRhJgmG" +
                                                    "Rf+8gTP/yCq06+qW3+rENh6Z1zXHc0fMvoAZzs4rKcU7uB3x17+ae/gSfg9o" +
                                                    "F6jOUkaJYC9PKl+aH9LbUEUDuh1O1ANprOrG0JGbp+20cRcPlzDZO77vgX//" +
                                                    "uDetws7PKnLpOnaVFWCYboPnAfx54PmLPxw0fMJm2apAguobUlxvmjw88x5m" +
                                                    "ljii46ezY599MLbHdqMqs8C0Q/90+ps/v/Qf+uFKDt4C/BmYCRslYWOLePu5" +
                                                    "UeJGkVhr468GM2stLmaq/83dd/CmJo2yfu9S+3f/eE/YlEU6OcLh0g9Jp47U" +
                                                    "BFbcEvpO9nPt+ng2j0MD6Og+dVL71dtY+LkXFYVQhZzg6T6sxniOhaCjspLk" +
                                                    "DR1oxnpmd2S3Am0pdqt14yHtWDfvOHGAMZfmY5+Laqr4LdfC40tQjc9NNR4k" +
                                                    "BgGh0ijeC/jrcRETL0NFJlWGAU481oqO1ThDeVgTSksET5nizKa0SCKOu7lT" +
                                                    "NWACc0d3j09Euo4t9iYA8gxDJcwwn1DJMFFdoJibUcDWiZbTCca+Ex9eYNda" +
                                                    "n7XR2zI1gNyKl3b/XNO7YnDrfyhb9S6f3FueWHfqyuqF8ltelJeKaVYXnanU" +
                                                    "lhlJHyXQ9uu9GfGsS8WTP2gOPBWJeFbkLB1OQJx0TBCciJuQ6ntIvr7IXz1A" +
                                                    "8xaE2m7+luTIXui/c3YpnHursz6T7NZePjNRXjxnYuO3ou6m2u8S6IGjMVVN" +
                                                    "R3XauNCkJKoI20psjNuw28xQZVbrBFAV/4XBL9uCWxmaliYIuE6M0oWgnOSB" +
                                                    "EB9GbHdnQQCzW7MMZ+MoA/qmOxHmZ0BSfGMm4ROzvzLD8tmJtet33nn6mMBi" +
                                                    "AXydjo6KbxL4xLLbixQE5025W3KvwjXN98vOlSxIplYZf1UlegqXbfW5S2+7" +
                                                    "ZjJRLEc/mfPx8uMT10Xt/xv1zSHh/A8AAA==");
}
