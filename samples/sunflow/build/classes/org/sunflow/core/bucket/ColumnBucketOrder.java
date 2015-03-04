package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class ColumnBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = new int[2 *
                                                                                nbw *
                                                                                nbh];
                                                       for (int i =
                                                              0;
                                                            i <
                                                              nbw *
                                                              nbh;
                                                            i++) {
                                                           int bx =
                                                             i /
                                                             nbh;
                                                           int by =
                                                             i %
                                                             nbh;
                                                           if ((bx &
                                                                  1) ==
                                                                 1)
                                                               by =
                                                                 nbh -
                                                                   1 -
                                                                   by;
                                                           coords[2 *
                                                                    i +
                                                                    0] =
                                                             bx;
                                                           coords[2 *
                                                                    i +
                                                                    1] =
                                                             by;
                                                       }
                                                       return coords;
    }
    public ColumnBucketOrder() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVYW2wcRRatab+NEz9CHoTESRyHXScwzUNBCmYBZ9bBzg7Y" +
                                                    "ikOkHbQMNT014457ujvVNfbErHlEQon4iBAYNiDwxyosC4QEoY1YhJDyswuI" +
                                                    "1UogBOIDAvyACJHIx7KI971V09M9PWMjvrDUNdVV91bd57m3feI8afI42eY6" +
                                                    "1sG85Yg4K4n4fmt7XBx0mRffndw+TrnHsgmLet5eWEsbfS90fvXtg5NdGmlO" +
                                                    "kRXUth1BhenY3h7mOdY0yyZJZ7A6bLGCJ0hXcj+dpnpRmJaeND0xmCQXhVgF" +
                                                    "6U/6Iugggg4i6FIEfSigAqZlzC4WEshBbeEdIHeTWJI0uwaKJ8im6kNcymmh" +
                                                    "fMy41ABOaMX3faCUZC5xsrGiu9K5RuFHtunzf7mj68UG0pkinaY9geIYIISA" +
                                                    "S1Kko8AKGca9oWyWZVOk22YsO8G4SS1zVsqdIj2embepKHJWMRIuFl3G5Z2B" +
                                                    "5ToM1I0XDeHwino5k1lZ/60pZ9E86Loq0FVpuAvXQcF2EwTjOWown6VxyrSz" +
                                                    "gmyIclR07P8DEABrS4GJSadyVaNNYYH0KN9Z1M7rE4Kbdh5Im5wi3CLI2kUP" +
                                                    "RVu71JiieZYWZE2UblxtAVWbNASyCLIySiZPAi+tjXgp5J/zt15/9C57xNak" +
                                                    "zFlmWCh/KzD1Rpj2sBzjzDaYYuzYmnyUrnr1iEYIEK+MECual/584abLe8+8" +
                                                    "rmgurUMzltnPDJE2jmeWv7UuMbCjAcVodR3PROdXaS7Df7y8M1hyIfNWVU7E" +
                                                    "zbi/eWbPv/9477PsnEbaR0mz4VjFAsRRt+EUXNNi/GZmM04Fy46SNmZnE3J/" +
                                                    "lLTAPGnaTK2O5XIeE6Ok0ZJLzY58BxPl4Ag0UQvMTTvn+HOXikk5L7mEkBZ4" +
                                                    "yHZ4moj6k7+CuPqkU2A6Naht2o4OscsoNyZ1ZjhpzlxHH06M6Rmw8mSB8ilP" +
                                                    "94p2znJm0kbRE05B97ihOzzvL+uGw5meKRpTTOhKj53yZYxnGY9j5Lm/wp0l" +
                                                    "tEPXTCwGLloXBQgLcmvEsYA2bcwXdw5fOJl+U6skTNmCggzAlfHylXG8Mq6u" +
                                                    "jNdcSWIxedPFeLUKBHDjFAACQGXHwMSfdt95pK8BItCdaQQfIGkfqF6WZ9hw" +
                                                    "EgFqjEpsNCB01/z19sPxr5++UYWuvjjE1+UmZ47N3Lfvnis1olVjNeoHS+3I" +
                                                    "Po4IW0HS/miO1ju38/BnX516dM4JsrUK/MsgUsuJINAX9QR3DJYFWA2O37qR" +
                                                    "nk6/OtevkUZAFkBTQSH6Aah6o3dUgcGgD6yoSxMonHN4gVq45aNhu5jkzkyw" +
                                                    "IkNkOQ49KlrQgREBJSbvevnMY6cf37ZDC8N3Z6ggTjChwKA78P9ezhisf3Bs" +
                                                    "/OFHzh++XTofKDbXu6AfxwRAA3gDLHb/6wfeP/vh8Xe0IGAE1MhixjKNEpxx" +
                                                    "WXALRKIF4IVu7b/NLjhZM2fSjMUw7r7r3HLV6S+OdilHWbDi+/nynz8gWL9k" +
                                                    "J7n3zTv+3yuPiRlYuALNAzJlgBXByUOc04MoR+m+t9c/9hp9EnAVsMwzZ5mE" +
                                                    "JyI1I9L0cemRATleEdm7EoeNbs1eSa6sKb/Jl01y7MfhN8puOP1tmDIm5ysF" +
                                                    "WVeT3KF8RiuvX6xgyWJ7/ND8QnbsqatUbvZUF4Fh6HGef/f7/8SPffRGHVxp" +
                                                    "E457hcWmmVUlGFxZhQm3yFoeZMYDzzz3knhr23Xqyq2Lw0GU8bVDn6/de8Pk" +
                                                    "nb8ACTZElI8e+cwtJ964+TLjIY00VECgpj2pZhoMmwEu5Qz6KRsNiivt0te9" +
                                                    "UoBuMAc+pA+e5nLxkr+4u8LF8WKVsjhcEwkeTdpTA3sOLNEVc7MAhXq63Eno" +
                                                    "cz1np5747Hll22jbESFmR+Yf+DF+dF4L9Waba9qjMI/qz6TIy5SKP8JfDJ4f" +
                                                    "8EHVcEHV555EuUnYWOkSXBcjctNSYskrdn16au6Vv88d1sq5s0OQBugmcToi" +
                                                    "F36/RKKN4TAkSHeeCZULE+xAEQEW7t6yuClloivLLPxt83/vWdj8MVgmRVpN" +
                                                    "D9r0IZ6v0/2FeL48cfbc28vWn5SA35ihnoqHaNtc2xVXNbtSgw5X/oxU1ItV" +
                                                    "UmuJUNiF14Sq7jdjVubQJ19L79ZkS53oiPCn9BNPrE3ccE7yBwUMuTeUapsR" +
                                                    "MFHAe/Wzhf9pfc3/0khLinQZ5c+kfdQqYi1JgRE8/9sJPqWq9qvbfNXTDlZy" +
                                                    "c100PEPXRktnOEsbRVV+LndLMSLzL1UfcDUJuAIONG1qlaBsWczOq650BIcJ" +
                                                    "t1STqGVEVrUDxQesdWyGZcjfU42V6cQrH2awWarxM77/TgXBRD1oCId7fok9" +
                                                    "EwcwaZOBgii5IYg21K+bwwVXyEo3+8/V/7j+6YUPZeEukTo1C7KrpnnEk9fU" +
                                                    "fMOq7y7j5EJn6+qF295T2eF/G7XBB0quaFlhT4XmzS5nOVNq0ub7DX+g9qxe" +
                                                    "pKcFT6mJlNlR9PjdH6UXpBF/wmTTglwUIhOkpTwLEx0EKAIinM66vlu7Aper" +
                                                    "iC2RqqruVtf4cPuEaSz/P+BXqKL6D0HaOLWw+9a7Llz7lCx34EE6Oyu/JwEx" +
                                                    "VFNYqXKbFj3NP6t5ZODb5S+0bfEBtapdDMuGc+snApIZYI0RAAA=");
}
