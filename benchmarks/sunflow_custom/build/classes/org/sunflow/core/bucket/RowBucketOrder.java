package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class RowBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = new int[2 *
                                                                                nbw *
                                                                                nbh];
                                                       for (int i =
                                                              0;
                                                            i <
                                                              nbw *
                                                              nbh;
                                                            i++) {
                                                           int by =
                                                             i /
                                                             nbw;
                                                           int bx =
                                                             i %
                                                             nbw;
                                                           if ((by &
                                                                  1) ==
                                                                 1)
                                                               bx =
                                                                 nbw -
                                                                   1 -
                                                                   bx;
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
    public RowBucketOrder() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YW2xcxRmePb4bx7eQS9PESRwnrROyB6hASs3N2TrY6YKt" +
                                                    "OETqIlhmz86uT3zOmZM5s/bGYC6RUCIeIkRNmlTgBxRKoSFBiIhWFVJeuAmE" +
                                                    "BEKgPpS0fSlqGql5aIrK9Z+ZPXsuuzbqSy2d2Tkz/z/zX7//Pz5zGTV5DO1w" +
                                                    "qXW4aFGeJGWePGjdlOSHXeIl96ZvmsDMI/mUhT1vP6xljf5Xuq5++eRUt4aa" +
                                                    "M2gldhzKMTep4+0jHrVmSD6NuoLVEYvYHkfd6YN4Buslblp62vT4UBpdE2Ll" +
                                                    "aCDti6CDCDqIoEsR9OGACphWEKdkpwQHdrh3CD2MEmnU7BpCPI42Rw9xMcN2" +
                                                    "5ZgJqQGc0CreD4BSkrnM0Kaq7krnGoWf3qEv/Or+7lcbUFcGdZnOpBDHACE4" +
                                                    "XJJBHTaxc4R5w/k8yWdQj0NIfpIwE1vmnJQ7g3o9s+hgXmKkaiSxWHIJk3cG" +
                                                    "luswhG6sZHDKquoVTGLl/bemgoWLoOvqQFel4R6xDgq2myAYK2CD+CyN06aT" +
                                                    "52hjnKOq48DPgQBYW2zCp2j1qkYHwwLqVb6zsFPUJzkznSKQNtES3MLRuiUP" +
                                                    "FbZ2sTGNiyTL0do43YTaAqo2aQjBwtGqOJk8Cby0LualkH8u333L8QedUUeT" +
                                                    "MueJYQn5W4GpL8a0jxQII45BFGPH9vQJvPqNYxpCQLwqRqxoXn/oyh3X9V14" +
                                                    "R9H8sA7NeO4gMXjWOJ3r/HB9anBXgxCj1aWeKZwf0VyG/0RlZ6jsQuatrp4o" +
                                                    "NpP+5oV9b/3i0ZfIJQ21j6Fmg1olG+Kox6C2a1qE3UkcwjAn+THURpx8Su6P" +
                                                    "oRaYp02HqNXxQsEjfAw1WnKpmcp3MFEBjhAmaoG56RSoP3cxn5LzsosQaoEH" +
                                                    "3QhPE1J/8pcjW5+iNtGxgR3ToTrELsHMmNKJQbOMuFQfSY3rObDylI3ZtKd7" +
                                                    "Jadg0dmsUfI4tXWPGTplRX9ZNygjeq5kTBOu76Ozu+VsnOUJS4qwc//fF5aF" +
                                                    "BbpnEwlwzvo4NFiQVaPUAtqssVDaPXLlbPY9rZoqFdtxtA3uS1buS4r7kuq+" +
                                                    "ZPQ+lEjIa64V9yr/g/emAQcAITsGJ+/b+8Cx/gYIPHe2EUwvSPtB6YowIwZN" +
                                                    "BWAxJiHRgIhd+9y9R5NfvHC7ilh9aWSvy40unJx97MAj12tIi0K0UA6W2gX7" +
                                                    "hADWKoAOxFOz3rldRz+/eu7EPA2SNIL5Feyo5RS53x93A6MGyQOaBsdv34TP" +
                                                    "Z9+YH9BQIwAKgCjHEPSAT33xOyIYMOTjqdClCRQuUGZjS2z5INjOpxidDVZk" +
                                                    "fHSKoVeFinBgTEAJxXv+cOHU+V/v2KWFUbsrVAcnCVcY0BP4fz8jBNb/fHLi" +
                                                    "l09fPnqvdD5QbKl3wYAYU4AI4A2w2OPvHPrTxc9Of6wFAcOhNJZylmmU4Yxt" +
                                                    "wS2AFxZglnDrwD2OTfNmwcQ5i4i4+6pr6w3n/3m8WznKghXfz9d9/wHB+g92" +
                                                    "o0ffu/8/ffKYhCHqVaB5QKYMsDI4eZgxfFjIUX7sow2n3sbPApwChHnmHJGo" +
                                                    "hKRmSJo+KT0yKMedsb3rxbDJrdkry5W1lTf5slmOA2L4kbKbmP44TJmQ81Uc" +
                                                    "ra/J7FA+CytvWKpOyRp7+sjCYn78+RtUbvZGsX8EWpuXP/n6/eTJv7xbB1Ta" +
                                                    "OHV3WmSGWBHB4MoIJtwlS3iQGU+8+LvX+Yc7fqqu3L40HMQZ3z7yj3X7b5t6" +
                                                    "4H9Ago0x5eNHvnjXmXfv3GY8paGGKgjUdCVRpqGwGeBSRqCNcoRBxUq79HWf" +
                                                    "FKAHzCEe1A9Pc6VmyV+xu9IV47UqZcXwk1jwaNKeGthzcJlmmJk21OeZSgOh" +
                                                    "z/denH7m85eVbePdRoyYHFt44tvk8QUt1JJtqemKwjyqLZMir1Aqfgt/CXi+" +
                                                    "EY9QTSyostybqvQGm6rNgeuKiNy8nFjyij1/Pzf/x9/OH9UqubOLowZoIsV0" +
                                                    "VC78bJlEGxfDMEc9RcJVLkySQyUBsHD31qVNKRNdWWbxN1s+eGRxy1/BMhnU" +
                                                    "anrQnQ+zYp2mL8TzrzMXL320YsNZCfiNOeypeIh3y7XNcKTHlRp0uPJntKpe" +
                                                    "oppay4TCHnFNqOr+d9zKHfnbF9K7NdlSJzpi/Bn9zDPrUrddkvxBARPcG8u1" +
                                                    "nQiYKOC98SX731p/85saasmgbqPydXQAWyVRSzJgBM//ZIIvqMh+tLtXrexQ" +
                                                    "NTfXx8MzdG28dIaztJFH8rPTLSeQzL9MfcDVJOByONB0sFWGsmURp6ia0VEx" +
                                                    "TLrlmkStILKqHUJ8wFrqEFGG/D3VWJk0Wf0eg81yjZ/F+60qCCbrQUM43IvL" +
                                                    "7JliAJM2GUIQJTcE0cb6dXPEdrmsdHO/X/PaLS8sfiYLdxnVqVkcdUY7R3Hs" +
                                                    "2prvVvWtZZxd7Gpds3jPpyo1/O+hNvgoKZQsK+ym0LzZZaRgSjXafKeJHyg8" +
                                                    "a5boZsFNaiIFpopefOvH6TlqFD9hshmOrgmRcdRSmYWJDgMOAZGYzrm+T7sD" +
                                                    "f6twLaNISXejBT7cO4kclv8T8MtTSf1XIGucW9x794NXbn5e1jpwH56bk9+Q" +
                                                    "ABeqI6yWuM1Lnuaf1Tw6+GXnK21bfTSN9Iph2cTc+g5BKnbbgREAAA==");
}
