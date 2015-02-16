package org.sunflow.core;
import org.sunflow.image.Color;
public interface Shader extends RenderObject {
    public Color getRadiance(ShadingState state);
    public void scatterPhoton(ShadingState state, Color power);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1170614044000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXXWwURRyf236X0mvLp0AL/YCkFG5DDBosEqAp0HpA0wIJ" +
                                "NXBMZ+fulu4Xs3PtUaxBEgPxgRgtCkb7BFGUrxgJGkPCk0DwBWM0Pgi+aVQe" +
                                "eNEHVPzP7O3e3baFxMRLbm529v/98fvPXXiAylyGOhzbOJwybB6jWR47aKyN" +
                                "8cMOdWO98bV9mLlU6zKw6+6CswRpuRL949Gb6ToFlQ+iOdiybI65bltuP3Vt" +
                                "Y4RqcRTNn3Yb1HQ5qosfxCNYzXDdUOO6yzvjaFYBK0dtcd8EFUxQwQRVmqBu" +
                                "ylMB02xqZcwuwYEt7h5Cr6JIHJU7RJjHUXOxEAczbObE9EkPQEKleN4DTknm" +
                                "LEPLAt89n6c4fKpDnXh3f92nJSg6iKK6NSDMIWAEByWDqMak5hBl7iZNo9og" +
                                "qrco1QYo07Ghj0m7B1GDq6cszDOMBkEShxmHMqkzH7kaInxjGcJtFriX1Kmh" +
                                "+U9lSQOnwNf5eV89D7eIc3CwWgfDWBIT6rOUDuuWxtHSMEfgY9tLQACsFSbl" +
                                "aTtQVWphOEANXu4MbKXUAc50KwWkZXYGtHC0aEahItYOJsM4RRMcLQzT9Xmv" +
                                "gKpKBkKwcDQvTCYlQZYWhbJUkJ8HO9afPGJtsxRps0aJIeyvBKamEFM/TVJG" +
                                "LUI9xpqV8Xfw/OsnFISAeF6I2KO59srDjauabtzyaBZPQ7Nz6CAlPEHODtXe" +
                                "XdLVvq5EmFHp2K4ukl/kuSz/vtybzqwDnTc/kChexvyXN/q/2nv0Y/qbgqp7" +
                                "UDmxjYwJdVRPbNPRDcq2UosyzKnWg6qopXXJ9z2oAvZx3aLe6c5k0qW8B5Ua" +
                                "8qjcls8QoiSIECGqgL1uJW1/72CelvusgxCqgC+KwLcReZ9KsXC0R93tQrmr" +
                                "mGBLt2wVipdiRtIqJXZiCKKbNjEbdlWScbltqm7GShr2qOoyotosFTwTm1F1" +
                                "II01ymKivpz/TXJW+FQ3GolAuJeEm92APtlmG0CbIBOZzd0PLyXuKEHx56LB" +
                                "0QJQEMspiAkFMU8BikSk3LlCkZdCSMAwtDKAXE37wL7eAydaSqB2nNFSEb6s" +
                                "7K2F/gMwhgySXbzlixtnrr7XsU4pbPhoAYQOUO6VT31e7y5GKZz/eLrv7VMP" +
                                "jr8slQJF63QK2sTaBcUECAlI8/qtQz/cv3f2WyUwtIQDqmaGDJ1wVImHAJIw" +
                                "4RxVBdhS6EhE7udx1DglSv1Qh5R5LSLcbZypjSUEnT02MantPLfGa7aG4tbo" +
                                "BuS/+N3fX8dO/3R7mgxVcdtZbdARahRYpgiVUD05bd3E3i4RrkcOEAL9/cb5" +
                                "T67xux0veCpXzjwHw4w3j/26aNeG9AEFKcWzTGiHo2rB2ScmUDBploacD4s8" +
                                "v/3C7a0ryFsKKskB2TSgXczUWRgGUMooTBlLBFScVIPSlnDFM5tQDUZRXu/K" +
                                "Zfhq4vp4m4JKAY1hAnEMiAHg3hRWXgSgnX5tClVlEISkzUxsiFf+BKnmaWaP" +
                                "5k9kK9bKfT2kR3zREn/j/4q3cxyxzvVaV9IvlmuTWJq93Ipti1haRWWtyHcC" +
                                "wKEB9SaS0bbbMm1NT+p4yKCiJ/+KLl9z9feTdV4FGXDiZ2fV0wXkz5/ZjI7e" +
                                "2f9nkxQTIWIc57szT+Y16Zy85E2M4cPCjuxr3zSeuYk/gGkBCO3qY1SCbuRp" +
                                "DSVgByawuINQGZ31kvZ5uXaK8OWwRTxvFMuzHM1KUd4PfCLdvugiRNNNGMXC" +
                                "bZs5UyTJg9VB0maJww25xPkJ/E9JKzZcyRO8KJbNUk7vE9yLi6Wbo9kuwRxw" +
                                "qS9tc0gZQ+1PuM4y3YQJO5K7AqjjDfeH3//lotf+4ftCiJiemHjjcezkhFJw" +
                                "qWqdcq8p5PEuVtLY2V4AH8MnAt9/xFd4Ig68wdrQlZvuy4Lx7jiitJufZJZU" +
                                "seXny+NffjR+XMlFpp2j0hFb16YmMwvA7o0uUZkLp9x9vfsauTQZrVwwuft7" +
                                "CQnBnaoKLjbJjGEUIE4h+pQ7jCZ1aUCV1+mO/BmE+3+4kMFA8SPt2+uR7YNC" +
                                "LSDjqCK3KyQ6wFEJEIkthuigonnqhKdra1EhyP8DPvZmvH8ECXJ5snfHkYfP" +
                                "nZNAXgb/JMbG5P0RrsMeoAX43TyjNF9W+bb2R7VXqpb7eagVS0NBQxTYtnR6" +
                                "vOk2HS4RYuzzBZ+t/3DynhzK/wJyzVCNqA0AAA==");
}
