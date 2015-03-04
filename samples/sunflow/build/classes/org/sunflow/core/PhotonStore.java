package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Vector3;
public interface PhotonStore {
    int numEmit();
    void prepare(BoundingBox sceneBounds);
    void store(ShadingState state, Vector3 dir, Color power, Color diffuse);
    void init();
    boolean allowDiffuseBounced();
    boolean allowReflectionBounced();
    boolean allowRefractionBounced();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUZf3AU1fndXX4TuBAgSfkRSAhSQO6kFVoIIkkIEjwg5SKO" +
                                "qfXY7L7LLeztLrvvkiMKo3YcGGdAp8YKFjJjB9rSKjCdMrbTsUNrR0GkMzja" +
                                "FlvR/lORlg78obaDSr/v7d7d3uZyXGLMzH7Zfe977/vxvp/vXrxKik2DLNI1" +
                                "ZWevorEATbLANmVpgO3UqRlYH1raKRgmldoUwTS7YCwiNp70f3Lj6ViVl5R0" +
                                "kymCqmpMYLKmmpupqSl9VAoRf2a0XaFxk5Gq0DahTwgmmKwEQ7LJmkNkgmMp" +
                                "I02hFAtBYCEILAQ5C8GWDBYsmkjVRLwNVwgqM3eQ3cQTIiW6iOwx0pC9iS4Y" +
                                "QtzeppNLADuU4fcWEIovThpkTlp2S+ZhAj+7KDj43ENVv/QRfzfxy2oY2RGB" +
                                "CQZEukllnMZ7qGG2SBKVuslklVIpTA1ZUOQBznc3qTblXlVgCYOmlYSDCZ0a" +
                                "nGZGc5UiymYkRKYZafGiMlWk1FdxVBF6QdaajKyWhGtxHASskIExIyqINLWk" +
                                "aLusSozMdq9Iy9h0LyDA0tI4ZTEtTapIFWCAVFtnpwhqbzDMDFntBdRiLQFU" +
                                "GJk+4qaoa10Qtwu9NMJInRuv05oCrHKuCFzCyDQ3Gt8JTmm665Qc53N148r9" +
                                "D6vrVC/nWaKigvyXwaJ616LNNEoNqorUWli5MPRDoeaVvV5CAHmaC9nCefmR" +
                                "66tvrz99xsKZkQNnU882KrKIeKRn0oWZbQuW+5CNMl0zZTz8LMm5+XfaM81J" +
                                "HTyvJr0jTgZSk6c3v/bAoz+n//KSig5SImpKIg52NFnU4rqsUOMeqlJDYFTq" +
                                "IOVUldr4fAcphfeQrFJrdFM0alLWQYoUPlSi8W9QURS2QBWVwrusRrXUuy6w" +
                                "GH9P6oSQUniIB55VxPqrQMBIZzCmxWlQEAVVVrUg2C4VDDEWpKIWNIW4rsCp" +
                                "mQk1qmj9QdMQg5rRm/4WNYMGO2Ma08CL4D2AlqV/BXsmUY6qfo8HVDzT7eAK" +
                                "+MY6TZGoEREHE63t149HznnTBm9rgJGZQCVgUwkglYCDCvF4+OZTkZp1dqD5" +
                                "7eDDEN0qF4S/t37r3kYfGI3eX4R6S3Knqkt9wEIXV9x91/7m9MFTzy9a7nV6" +
                                "ut8RO8OUWXYzOUO3y6AUxt870PnMs1f3fJcTBYy5uQg0IWwDK4LQCGI8cWbH" +
                                "xfcvHXnbm2bUxyCcJnoUWWSkTOiBWCSIjJHydFAZJsiskTyTR5Ujjw8OSZuO" +
                                "LrH8pzrb2tshmL/058/fDBz44GyOAyhnmr5YoX1UcdCsQpJgFza1dlHbwINW" +
                                "B88JIrjsk8d+8TK7sGiFRXLhyKnNvfD1x69M71oV2+ol3uz0hNRhqAJXdmJS" +
                                "SSeP2S7h3Vse2/Di2XtuE3/gJT47NuWIw9mLmp1qAKIGhcShokJxpAKINroN" +
                                "2tBEKkF2ydBdOEc4FXllV5OXFEGAhaTCBAgCEK/r3cSzYmJzyuqQVDEoIaoZ" +
                                "cUHBqVRSqGAxQ+vPjHBPm8TfJ8PxlKFZ+PGc7KjB/+PsFB3hVMszOf4MDusR" +
                                "NPCz9eJrI4K5aFm3ZWwcIpwCURYPo+k+Na5JclQWehSK3vaZf96SU//eX2VZ" +
                                "kAIjqdO5/dYbZMa/1koePffQp/V8G4+IGTbjdxk0y/2mZHZuMQxhJ/KRfOyt" +
                                "WQdfFw5DAoCga8oDlMdRYrsKMrWCS7yUw+WuuZUIljBSCk7RHpcZUFmQpywz" +
                                "ZMCR++xUFtxV/f72Q5dfsmzenfdcyHTv4JM3A/sHvY7iYO6w/OxcYxUInM2J" +
                                "1lHfhD8PPF/ggzLggJUgqtvsLDUnnaZ0Hc+zIR9bnMTaD0/s+u3Pdu3x2jqZ" +
                                "z4gPIo8+TGF8YFHa6vjgAnhqbaurHavVZZ+PhyN4+Pc0V0aIQ7YMtGoJVYKK" +
                                "qFVLchIb8hzwdxCsgwPWDQrx1/KfVQhaLbprGCnq02SpMHHvhafBFrdhfMQt" +
                                "4ghFKXFnDUuA4ZiA0mIFTFNYdcOUsoVi+frNFEKtE0GOQ9GH3qgZOG0l6gfz" +
                                "aE1CcD+DNgXTLn6EC9MPBqH5tn7mj49+nHwpeeZUBL0MqyuZFcZyJQ7Ohidg" +
                                "sxwYf5b78sxxsINBQ6fAMa2Ro9GESdG6IbPkMtTSHk1TqKAWJlgjPMtswZaN" +
                                "v2DfzzP3BILdEM25YJDt7Dhuy4azA4ULcZctxF3jL8S+PHNPIdjrEAKrslEJ" +
                                "wcNz7nhdk2nvrF4mwNtpiNl5hPJlhIKKEZpbCOP5pDuYZ+5HFqMInkmmokYV" +
                                "z7DIVcDiKh8fEB6isirwxu85vucBBM8jOASFbC9lvCw189aD4QTUu47Gcp88" +
                                "dP6Nj/2PWUk1OxnzuwV7qXvdxb/6vjGBNT3F68iiHsHkYasMaisTMRmZM/I9" +
                                "Bd/LyrQTbnly0zInx8mnDy6lRH9GiRwBh49lZeLcSoiIHfFI+NTFPct4jeDv" +
                                "k6EtpVKXfXWSXRtl+p7mrOuUnGqKiJdP7DvTcGXLFN4npzTi7HE2CPqwHmed" +
                                "YMZgvLj03d+/WrP1go9415IKRROktQLmGmh4oSylZgy6uqR+92ruo5X9WIxi" +
                                "/YmN2rwRRLZl4sVgRHzk0BfnP9p16ayPlECpi/U6JGoozRkJjHRB5dygqQve" +
                                "1sAqKKQnWashXaZVDyZQnR5NV+2MLB5pb17ouYp7vOKBIEANXnvgtne4OoaE" +
                                "rjtnuTFVjt2YdkMhXYDy0rLbIZJUc7eZlDFBbPeck9DiTWkLtYTDka4HOtsj" +
                                "W1o2d7S0htq5keow6enC18PJPKd3v6xIbYIhWY539Gb53NW1Z1dwxxuupDEq" +
                                "po4rRuhnQSxsNZWqmQipWxwOa4qbRuDXcZsZEZ9++9rELdd+d537mLvfPG41" +
                                "VnZQNEit+6LBdok7T298sEo5fQM26YZNRJGa5iZDogZff9L2haq0L5AkyRmE" +
                                "f4zgJwheRfCaMyYXlges4Jsnzp/LM3feTe/wrZKpFeffQPAmgj9BnI+BTto0" +
                                "iXtCC1/6B0dW+WNOZUATn7tNbI/rjDd2A7+u/dXKnw5d4rckuNEFvj+CtxC8" +
                                "U6Cubt1oWNaE8N08uvp7gbri2zVyjIsI/obgPUjXdEdCsOrXgcKUxKVE8AFf" +
                                "hOAf4yS5U7AP88x9NHqh/4ngMoIrYBtMsy6vcxQYjonCVXEVwX8QXBuNKkbj" +
                                "MJ/kmftvgfrI0Hsh4zUfI/gUwf+wetOYHN2JX+FR6uAzvgjB51+RDjy+PHPF" +
                                "X0IHHrRJTxGCEkbKLR20KMoY1OApT6nBUzGeanDePrjvCRRN7eViVOVZBFZe" +
                                "n7HyDrzCNRI6FHLtSZHqGOX4FtO+jBr9CKYiqAO2+gW74R2lBmekNThzNBrM" +
                                "E1e8aQTPZBxr4dw2jhh3PTUcoWnUeegFvq4BAR6jZ15GnjEr4evjpASnswRu" +
                                "JfodYxR9MYIggiXjIPqdfCzJyATHry3YDdQN+53W+m1RPD7kL6sduu8v/K47" +
                                "/ftfeYiURROK4iiMnUVyiW7QqMzFLLcqLd7uer4NicF97QV2jf9QDM+3LLRm" +
                                "4M+Bxkip/eZEWsWID5Dw9W6oFUlWkai7S8a5IzZOGxLWr9cR8cTQ+o0PX192" +
                                "lBe4xVCEDvBcDH1FqXVTzzfFq++GEXdL7VWybsGNSSfL56XuWnmpWe0wtDpH" +
                                "tnnn/z844T8pIAAA");
}
