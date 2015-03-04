package org.sunflow.system;
import org.sunflow.system.UI.Module;
import org.sunflow.system.UI.PrintLevel;
public interface UserInterface {
    void print(Module m, PrintLevel level, String s);
    void taskStart(String s, int min, int max);
    void taskUpdate(int current);
    void taskStop();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUZC2wUx3Xuzn9sn7GDIYQ4fEwQv7tShUhAVDCOAdMDLGxo" +
                                "47Q5xrtz9sLe7rI7Z5+dQJNULSgVNGpJm1Sp26qghHwgQqUkrYiIKgVISiVa" +
                                "mpRUJelHCk1LG/pJ1dKGvje7d7u3vjtsx7G0z7Mz77157837zd5zV0ipZZKF" +
                                "hq4O9qo6j7A0j2xXl0b4oMGsyPrY0g5qWkxuValldcFcXJr9QviDa4/21QVJ" +
                                "WTdpoJqmc8oVXbM2M0tX+5kcI2F3tk1lSYuTuth22k+jKa6o0Zhi8RUxMslD" +
                                "yklzLCNCFESIgghRIUK0xcUCohqmpZKtSEE1bu0ku0kgRsoMCcXjZFYuE4Oa" +
                                "NOmw6RAaAIcKfN8KSgnitElmZnW3dR6h8GMLowe+eV/dsRAJd5OwonWiOBII" +
                                "wWGTblKdZMkeZlotsszkbjJZY0zuZKZCVWVIyN1N6i2lV6M8ZbKskXAyZTBT" +
                                "7OlarlpC3cyUxHUzq15CYaqceStNqLQXdG10dbU1XIPzoGCVAoKZCSqxDEnJ" +
                                "DkWTObnNT5HVsfnTgACk5UnG+/TsViUahQlSb5+dSrXeaCc3Fa0XUEv1FOzC" +
                                "yfSCTNHWBpV20F4W52SaH6/DXgKsSmEIJOFkih9NcIJTmu47Jc/5XNl41/77" +
                                "tXVaUMgsM0lF+SuAqMlHtJklmMk0idmE1Qti36CNJ/cGCQHkKT5kG+fEA1dX" +
                                "LWo6dcbGuSUPzqae7UzicelgT+35Ga3zl4VQjApDtxQ8/BzNhft3OCsr0gZE" +
                                "XmOWIy5GMounNr96z4PPsD8FSVU7KZN0NZUEP5os6UlDUZm5lmnMpJzJ7aSS" +
                                "aXKrWG8n5TCOKRqzZzclEhbj7aREFVNlungHEyWABZqoHMaKltAzY4PyPjFO" +
                                "G4SQcnhIAJ7FxP4rQ8BJV7RPT7IolaimaHoUfJdRU+qLMkmPWjRpqHBqVkpL" +
                                "qPpA1DKlqG72uu+DFmfJ6BaLme0ZL42gdxkfE9806lM3EAiAqWf4A10FrHW6" +
                                "KjMzLh1IrW67eiT+ejDr+I4lOJkJO0WcnSL2TpGcnUggIDa4CXe0zxFOYQfE" +
                                "M2S66vmdn1+/be/sEDiQMVCCNkyLAJuWeQFCn2QilNe8dOqJ499auCzojfqw" +
                                "J492Mm770GR33y6TMZj/zeMdX3/syp57xaaAMSffBs0IW8GjIE1CuvnSmZ0X" +
                                "37508EIwK2iIQ2pN9aiKxEkF7YG8RCXOSWU2wYxQ5NZCUSoyzMGHDwzLmw4t" +
                                "sWOpPtfz2yCxP//G/34aefyds3kOoZLrxmKV9TPVs2cNbgn+4ezWJukbRAJr" +
                                "F/VBgvB95PCzJ/j5hcvtLRcULnN+wtMPvze961N924IkmFuqcHeYqkLKDiww" +
                                "2UJym095P8vDG547u/Z26WtBEnLyVJ6cnEu0wmsG2NRkUEQ0NCjOVMGms/1O" +
                                "beoSk6HSuPsumEmPx0/uag6SEki2UGA4hYQAubvJv3lOflyR8TrcqhSMkNDN" +
                                "JFVxKVMgqnifqQ+4MyLaasV4MhyPcIs74Kl3Moj4j6sNBsKb7OgU+LcI2IRg" +
                                "ljjbIA5nI5iDnnW76+OQ7VTIuHgYzVu0pC4rCYX2qAyj7b/huUuO/3l/ne1B" +
                                "KsxkTmfRjRm48zevJg++ft+/mgSbgITV1o07F80OvwaXc4tp0kGUI/3Qz299" +
                                "4jT9NhQDSMCWMsRETg3ZcSV0ncLJjHyZpb15gy6nVJZBypt+2ps7oBDzWDYg" +
                                "ALFOyIFlOmKXaWHe5WJ5qYDL0P5OtOL7agRLOCk1kBmoMr9IH2gqSShN/U7t" +
                                "jO6qf3vHk5eftwPLX2h9yGzvgUeuR/YfCHq6kTkjGgIvjd2RCCFrbH+6Dn8B" +
                                "eD7EBzXACbsi1bc6ZXFmti4aBjrNrGJiiS3WvHt014+f3rUn6FhkHicl/boi" +
                                "GyPsJSYW5vp2BJ4mx7ebxuvbucfjeAi+rhSTdyNYZ6Ot5yQEB4XDLsG/s8jh" +
                                "fhbBRsyc1NoBPasp6NaOTrOZ8MxzNJs3MZoFBELAFX5bEeF7ENzLIcOA8FsM" +
                                "Gc50DNI3wLPIkX7RxEjvFW57kTXRe0IjW2FbXTdGIbbw5Pyu3ei23nafGRFX" +
                                "HXDvImqEXDWggsPFAzy+mD79RdYcQREY6TyZxpaqmByQXhKKRoVhuOCZQjCA" +
                                "YBAM1cu4aBOsovW5MwX9h6fp36cMn3vtn+GH7PyTm7fEvc8h9dNd/FXok5N4" +
                                "81dFXS/poZbwqwqodRZiQrotfIcUvOykNOmGJzfFPTmxffbgMkYMu0YUCDj9" +
                                "5Zykld8Icak9Ge88fnHPnSKdhvsVuDIwucu51ubWKrcPXZFz1c1rprh0+ei+" +
                                "M7Pe29og7jAZi3h7zg3UGNFzrqNWH8yXlr/1yk8at50PkeAaUqXqVF5D8RoL" +
                                "lxFoE5jVB5122li5SkRl9UAFwDpMCMBsbgGVHZ1EcY5LDzz54bk/7rp0NkTK" +
                                "oPXA/omacAOCK1ak0McDL4PmLhjdDVTQ2NTa1FAjs6YHF6jPzma7KE4WF+It" +
                                "aqKv2cLrN5RpZq7WU5qMbD/h6+BShuFdFc5UPX5n2g2NzSiMl9WduJ0Y+EOt" +
                                "64LYfnsXoeVuaI21dHbGu+7paItvbdnc3rI61iac1IDFQBcOh9JFTu8ziiq3" +
                                "UlO2A+/Q9co5q6aeXS4Cb6SRxmmYacIwdIBHsQfQNaa5GdKwJRxxSWkuIK/n" +
                                "S1NcevTC+zVb33/5qogxf///FbvRdZKiSab6L35OSNxxauPn6tRT14BJNzCR" +
                                "JGZZm0y4bwr6fU4s1GVjgaRJ3iT8BQRfRPBdBN/35uTR1QE7+RbJ808XWXvG" +
                                "v9/QjcqnneefQnAYwbOQ5/vAJq26LCKhS5B+x1NVvpfXGHCpyt+2tyUNLhrt" +
                                "oRen/uCup4YviVsrMjoi+CM4iuDYKG01usZlSLA4UcRWL43SVoLdbIHxQwQv" +
                                "IvgRlGu2M0VVK1/rV96j6yqj2uhsJ5RHcBLBywhemSCDePU9XWTt7Nht8SqC" +
                                "Mwhewx5Kty8y+L5yjGqfE0QIfjYWtccSMxeKrL0xSt3d/Xa7gfMLBL9E8CY2" +
                                "cDpXEoP4tnaMNnhLECH49cdlg98WWfv9R7HBOwh+h+APcIexbdCiquMxw7tZ" +
                                "M1yeSDM4eSFfqJaounP9/ksRImgCm9wKLD4nmikDerm2tMQMTHSCxd8/ihmv" +
                                "IPgbgg9ArAGq8PFY8N9ZC/5nLBYskkOCLsJfEYgrYYAUTr3/EAjBMZei3QLt" +
                                "OhIjn0DI1We8RgiUTZARPMESqL6R6rXjUz0wCUENgvAEqF4v5tKc1OR8Ascr" +
                                "wbQRP6TZP/5IR4bDFVOHt7wpPkBmf6CpjJGKREpVPd2xt1MuM0yWUISilXa7" +
                                "JbQONHJSP/KLGKRJe4DKBKbYqDdzMsmDChXUGXmRZnASAiQc3gpNI8npFg1/" +
                                "7zin4A1qQ8r+iTEuHR1ev/H+q3ceEp1uKXSjQ0PIBS4Y5fYnVMEUv0nOKsgt" +
                                "w6ts3fxrtS9Uzs18nxI9Z73H3aZ58u2x/wPNVrz0zh0AAA==");
}
