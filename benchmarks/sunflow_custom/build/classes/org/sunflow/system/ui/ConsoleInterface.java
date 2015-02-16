package org.sunflow.system.ui;
import org.sunflow.system.UI;
import org.sunflow.system.UserInterface;
import org.sunflow.system.UI.Module;
import org.sunflow.system.UI.PrintLevel;
public class ConsoleInterface implements UserInterface {
    private int min;
    private int max;
    private float invP;
    private String task;
    private int lastP;
    public ConsoleInterface() { super(); }
    public void print(Module m, PrintLevel level, String s) { System.err.
                                                                println(
                                                                  UI.
                                                                    formatOutput(
                                                                      m,
                                                                      level,
                                                                      s));
    }
    public void taskStart(String s, int min, int max) { task = s;
                                                        this.min =
                                                          min;
                                                        this.max =
                                                          max;
                                                        lastP = -1;
                                                        invP = 100.0F /
                                                                 (max -
                                                                    min);
    }
    public void taskUpdate(int current) { int p = min == max ? 0 : (int)
                                                                     ((current -
                                                                         min) *
                                                                        invP);
                                          if (p != lastP) System.
                                                            err.print(
                                                                  task +
                                                                  " [" +
                                                                  (lastP =
                                                                     p) +
                                                                  "%]\r");
    }
    public void taskStop() { System.err.print(("                                                            " +
                                               "          \r"));
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1166152330000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfO/93HN/FiRPHBMc2l7RJ6G1TNUipETQ52eTC" +
                                                    "hZziOFWNihnvztkb7+0uu3P22eBColZOkRpBayAg6g9VEIUGghARrSqkfGkB" +
                                                    "0S9UVat+KFT9UlSaD/lQikpb+t7M7u3e3p2bfKmlnZudfW/ee/Pe+703vnSN" +
                                                    "tLgO2WdbxuKMYfE0K/P0aeNAmi/azE0fzR3IU8dlWsagrnsS1qbU4dcSn3z2" +
                                                    "xGwyTlonyWZqmhanXLdM9wRzLWOeaTmSCFZHDVZ0OUnmTtN5qpS4big53eUj" +
                                                    "ObIhxMpJKueroIAKCqigCBWUQwEVMG1kZqmYQQ5qcvch8m0Sy5FWW0X1OBmq" +
                                                    "3sSmDi162+SFBbBDO76fAqMEc9khgxXbpc01Bj+1T1l95oHk600kMUkSujmO" +
                                                    "6qigBAchk6SryIrTzHEPaRrTJskmkzFtnDk6NfQlofck6XH1GZPyksMqh4SL" +
                                                    "JZs5QmZwcl0q2uaUVG45FfMKOjM0/62lYNAZsHVrYKu0cAzXwcBOHRRzClRl" +
                                                    "PkvznG5qnOyMclRsTN0LBMDaVmR81qqIajYpLJAe6TuDmjPKOHd0cwZIW6wS" +
                                                    "SOGkv+GmeNY2VefoDJvipC9Kl5efgKpDHASycNIbJRM7gZf6I14K+efafXee" +
                                                    "f9g8YsaFzhpTDdS/HZgGIkwnWIE5zFSZZOzam3uabn3rXJwQIO6NEEuaNx+5" +
                                                    "/vXbB66+I2luqUNzfPo0U/mUenG6+/0dmT0Hm1CNdttydXR+leUi/PPel5Gy" +
                                                    "DZm3tbIjfkz7H6+e+NU3H3uZfRwnnVnSqlpGqQhxtEm1irZuMOceZjKHcqZl" +
                                                    "SQcztYz4niVtMM/pJpOrxwsFl/EsaTbEUqsl3uGICrAFHlEbzHWzYPlzm/JZ" +
                                                    "MS/bhJA2eMgd8Gwg8k/8csKUCRfCXaEqNXXTUiB4GXXUWYWp1tQ0nO5skTpz" +
                                                    "rqKWXG4VFbdkFgxrQXEdVbGcmeB90eWsqJR0BdPZMljWD9s0hpv9/xJURouT" +
                                                    "C7EYOGNHFAoMoDpiGRpzptTV0uHR669OvRevpIZ3VpzsBnlpT15aykuX9HRU" +
                                                    "HonFhJgtKFf6G7w1B3kPiNi1Z/xbRx88N9wEgWYvNMNRI+kw2OopM6pamQAc" +
                                                    "sgICVYjQvh/fv5L+9MW7ZYQqjZG8Lje5emHhzKlHvxwn8WpIRuNgqRPZ8wik" +
                                                    "FcBMRVOx3r6JlY8+ufz0shUkZRXGe1hRy4m5Phx1g2OpTAP0DLbfO0ivTL21" +
                                                    "nIqTZgAQAE1OIcgBjwaiMqpyfsTHT7SlBQwuWE6RGvjJB71OPutYC8GKiI9u" +
                                                    "Md/kJ8Et8PR4WSF+8etmG8ctMp7QyxErBD6P/fzqs1ee23cwHobyRKg4jjMu" +
                                                    "gWFTECQnHcZg/Y8X8j986trK/SJCgOK2egJSOGYAJsBlcKzffeehP3z4wcXf" +
                                                    "xoOo4lAvS9OGrpZhj92BFAARA4AMfZ+aMIuWphd0Om0wDM5/JXbtv/K380np" +
                                                    "TQNW/GC4/X9vEKxvP0wee++BfwyIbWIqFrHA8oBMHsDmYOdDjkMXUY/ymd/c" +
                                                    "+uzb9EeAsYBrrr7EBFQRYRkRR68IV+0VYzrybT8Og3bNt7JY6RNv2A7taZxE" +
                                                    "Y1iLQ8n3z+PG9Nk/fyosqkmfOiUowj+pXHq+P3PXx4I/iGPk3lmuBSToWwLe" +
                                                    "r7xc/Ht8uPWXcdI2SZKq1xSdokYJo2USGgHX75Sgcar6Xl3UZQUbqeTpjmgO" +
                                                    "hcRGMygAQpgjNc47I0nThae8xZ/4v+GkiRExOShYhsW4C4cv+jHbZjv6PMWO" +
                                                    "izQVdXN9J+UdvQhldN6r88pyz4dzz3/0ikTIqEcixOzc6uOfp8+vxkOd0201" +
                                                    "zUuYR3ZPwuSN0uTP4S8Gz3/wQVNxQVbPnoxXwgcrNdy2MROH1lNLiBj7y+Xl" +
                                                    "X/xkeUWa0VPdOIxCX/zK7/796/SFP71bp0I1QVMogEkG/1drXbPRc83GBq7J" +
                                                    "4jCCpy9L5uHG222Dp9vbrrvBdjlvO2hA5vOC/W4cMjIVRzlGmUXX0bkPnoQn" +
                                                    "JNFAyAlfCKfunGDvhauIwBUM/LTsZhvL2ApP0pORbCDjG56MFmhIeT50MuKU" +
                                                    "UiFkifkaDNZpF7DVqTQKGA+3Nmp4RSxcPLu6ph1/YX/cQ7YxTjq4ZX/JYPPM" +
                                                    "CMlsxp2qeohjosUPUOTxl376Jn9/39dkVO1tnFRRxrfP/rX/5F2zD95E57Az" +
                                                    "YlN0y5eOXXr3nt3qD+KkqQJGNbeWaqaRagjqdBhcs8yTVUA0UF29R+Dp9bza" +
                                                    "W7d6B54L6kiTOM8m34c76vkwmzpmaSWDrevobApSGyAZPYV0E0Lm7Dply8QB" +
                                                    "MLvFRsZ6ydI8b+labV0TC7Ta+gPwbPes336z1gt9KzF+WNAvrKP5Ig4cYxMS" +
                                                    "EO7Njkho+8Y0/QI8Q56mQzesaUzmWqDfo+vodwaHRzjEKug3YWsAxzehYL+n" +
                                                    "pK/sjSkYlr+yzrfv4fAdTtrl2Vl2A82gIiajNw3sn/pq/q8h7+Lqq2uJ9m1r" +
                                                    "E78XvXPlvtwBl9ZCyTDC9Tw0b7UdVtCFYh2yukuAOw939rq3H07iJV0o+31J" +
                                                    "+SQUv1pK6EblJEy6ysmGEClUf28WJnoGahEQ4fSCXQfcZVdTJiE0xL45/FbV" +
                                                    "RCPgif8Y+eBUkv8zmlIvrx297+Hrd7wgkK5FNejSEu7SniNt8v5QAbihhrv5" +
                                                    "e7Ue2fNZ92sdu3zg7sahx7s0RHTbWb+3Hi3aXHTDSz/b9sadL659IJr7/wIq" +
                                                    "xDbRyhMAAA==");
}
