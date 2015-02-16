package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class BoxFilter implements Filter {
    private float s;
    public BoxFilter(float size) { super();
                                   s = size; }
    public float getSize() { return s; }
    public float get(float x, float y) { return 1.0F; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1159026718000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XX2wURRif27bXPxR6Lf8qQoFSCFC8FQkaLEHh0kLxoA0t" +
                                                    "GIpyTPfm2qV7u8vuXHsUq0BiIDwQowXBYB8MBEH+xUjQGBJeFAi+YIzGB8H4" +
                                                    "IhF54EEkouI3M7e7d3tXxBcv2b3Zme/vfN/3m29O3UEltoUaTUPb3qMZNEzS" +
                                                    "NLxVWxym201ih1dHF7djyybxiIZtuxPmYkr9uap7D97qDUko2IXGY103KKaq" +
                                                    "odvriG1o/SQeRVXebLNGkjZFoehW3I/lFFU1OaratCmKxmSxUtQQdUyQwQQZ" +
                                                    "TJC5CfJyjwqYxhI9lYwwDqxText6HQWiKGgqzDyKZuYKMbGFkxkx7dwDkFDG" +
                                                    "vjeAU5w5baEZru/C5zyHDzTKw+9uDn1chKq6UJWqdzBzFDCCgpIuVJkkyW5i" +
                                                    "2cvjcRLvQtU6IfEOYqlYUwe53V2oxlZ7dExTFnE3iU2mTGJxnd7OVSrMNyul" +
                                                    "UMNy3UuoRIs7XyUJDfeAr5M8X4WHLWweHKxQwTArgRXisBT3qXqcoul+DtfH" +
                                                    "hpeAAFhLk4T2Gq6qYh3DBKoRsdOw3iN3UEvVe4C0xEiBFoqmjCqU7bWJlT7c" +
                                                    "Q2IU1frp2sUSUJXzjWAsFE30k3FJEKUpvihlxefO2qX7d+irdInbHCeKxuwv" +
                                                    "A6Y6H9M6kiAW0RUiGCvnRw/iSRf3SggB8UQfsaC58NrdFxfUXboiaJ4sQNPW" +
                                                    "vZUoNKYc7R53fWpk3pIiZkaZadgqC36O5zz92zMrTWkTKm+SK5Ethp3FS+u+" +
                                                    "3LjzJLktoYpWFFQMLZWEPKpWjKSpasRaSXRiYUriraic6PEIX29FpTCOqjoR" +
                                                    "s22JhE1oKyrW+FTQ4N+wRQkQwbaoFMaqnjCcsYlpLx+nTYRQKTxoLjwlSPz4" +
                                                    "P0Vb5PU2pLuMFayruiFD8hJsKb0yUYxYN+xubxJbfbaspGxqJGU7pSc0Y0C2" +
                                                    "LUU2rB73WzEsIoMlkEXyCiPdwkdhlmnm/6AjzfwMDQQCEIKpfgDQoHZWGVqc" +
                                                    "WDFlOLWi+e6Z2DXJLYjMDgHagKpwRlWYqQoLVWFXFQoEuIYJTKUIMISnDwod" +
                                                    "ILByXserq7fsrS+CzDIHimFvGWk9eJixo1kxIh4atHLMUyAlaz/YtCd8//gL" +
                                                    "IiXl0aG7IDe6dGhg14Y3npaQlIvBzC+YqmDs7Qw5XYRs8NdeIblVe27dO3tw" +
                                                    "yPCqMAfUM+CQz8mKu94fActQSBzg0hM/fwY+H7s41CChYkAMQEmKIasBgOr8" +
                                                    "OnKKvMkBTOZLCTicMKwk1tiSg3IVtNcyBrwZnhrj+LgagjKGZf0EeMoyZcD/" +
                                                    "2ep4k70niFRiUfZ5wQG55bNLh8+/17hEysbuqqzTsINQgQTVXpJ0WoTA/A+H" +
                                                    "2t85cGfPJp4hQDGrkIIG9o4ALkDIYFvfvLLt+5s3jn4jeVlF4YBMdWuqkgYZ" +
                                                    "czwtgBoaIBeLfcN6PWnE1YSKuzXCkvPPqtkLz/+6PySiqcGMkwwL/l2AN//E" +
                                                    "CrTz2ubf67iYgMJOLc9zj0xswHhP8nLLwtuZHeldX087fBm/D6AKQGarg4Rj" +
                                                    "U8Ctl3mP6FwsNQlg2p9Be3mo5mbfkVunRdn4jwYfMdk7vO9heP+wlHV+zso7" +
                                                    "wrJ5xBnKk2GsSJ6H8AvA8zd7WNKwCYGhNZEMkM9wkdw0WXhmPsosrqLl57ND" +
                                                    "n384tEe4UZN7fDRDd3T627++Ch/68WoBxIL8MzDlNsrcxvn8HWZG8R1FfK2J" +
                                                    "vWaYeWtpPlP7OHvfwlqWLMj6o03r3v3TfW5THugUCIePv0s+dWRKZNltzu9V" +
                                                    "P+Oens5HcGjvPN5nTiZ/k+qDX0iotAuFlEzvuAFrKVZjXdAv2U5DCf1lznpu" +
                                                    "7yMO+iYX3ab68yFLrR93vDjAmFGzcYUPaiodqAlmoCboh5oA4oMIZ6nn79ns" +
                                                    "Ndep9FLTUvsxa0whQmxlEUcnk2tqyIkfG0+kaHLeKSbOLpaN00ZrungmHt09" +
                                                    "PBJvO7ZQyqTNcxSVU8N8SiP9RMtSJTFJOcfaGt5meiHad+KjC/R64/Mip+eP" +
                                                    "nlZ+xsu7f5nSuax3y384zKb7fPKLPLHm1NWVc5S3JVTkRjqvc85lasqNb4VF" +
                                                    "oNXXO3OiXOdGmT2o1gm38593oHgBK1ykLz9ibSN7dUI29BDaAZjJEyG/nvlE" +
                                                    "e65hLP1CGcNCj22YJALtJtwiThp7hImYvV6hqAhMHMU8yOFyt5ViB0Rt3k1N" +
                                                    "3C6UMyNVZZNH1n/HmwP3BlAObXgipWnZpZc1DpoWSajcmHJRiKJKEoVqQnR2" +
                                                    "lN3F2IDbSgS9CrdbPz1FxewvmwzSaEwWGQQnM8omMmBDgIgNTdOp0BA/FxkE" +
                                                    "hQUEpVFWdbHWIPsrp09gBcRvwU6yp8Q9OKacHVm9dsfdZ4/xyimB+/PgIL81" +
                                                    "wSVQtEhuwcwcVZojK7hq3oNx58pnO0Awjr1qMn2Rz7bphduH5qRJ+YE/+Onk" +
                                                    "T5YeH7nB+5d/AMh597aeEAAA");
}
