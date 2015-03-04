package org.sunflow.core.accel;
import org.sunflow.core.AccelerationStructure;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
public class NullAccelerator implements AccelerationStructure {
    private PrimitiveList primitives;
    private int n;
    public NullAccelerator() { super();
                               primitives = null;
                               n = 0; }
    public void build(PrimitiveList primitives) { this.primitives = primitives;
                                                  n = primitives.getNumPrimitives(
                                                                   );
    }
    public void intersect(Ray r, IntersectionState state) { for (int i =
                                                                   0;
                                                                 i <
                                                                   n;
                                                                 i++)
                                                                primitives.
                                                                  intersectPrimitive(
                                                                    r,
                                                                    i,
                                                                    state);
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXb2wURRSf2/69o9A/QMUKBWoh8sdbMWqCVbTUAsUTGgok" +
                                                    "HkqZ7s21C3u7y+5cexSrQmIgfiBGK4LBfjAYBRGIkaAxJHxRIfhFYzR+UIxf" +
                                                    "NCof+CAS/783s3t7t3dF/eAlMzc7897Me/Pe+703J66QKtchS2zL2DVoWDzO" +
                                                    "cjy+3bg7znfZzI2vTdzdSx2XpboM6robYa5faztdf+2354YaFFKdJNOpaVqc" +
                                                    "ct0y3Q3MtYxhlkqQ+mC222AZl5OGxHY6TNUs1w01obu8I0GmFLBy0p7wRVBB" +
                                                    "BBVEUIUIamdABUxTmZnNdCEHNbm7kzxJIglSbWsoHifzizexqUMz3ja9QgPY" +
                                                    "oRa/N4NSgjnnkHl53aXOJQq/uEQdf2lrw9sVpD5J6nWzD8XRQAgOhyRJXYZl" +
                                                    "BpjjdqZSLJUkjSZjqT7m6NTQR4XcSdLk6oMm5VmH5S8JJ7M2c8SZwc3Vaaib" +
                                                    "k9W45eTVS+vMSPlfVWmDDoKuzYGuUsNVOA8KxnQQzElTjfkslTt0M8XJ3DBH" +
                                                    "Xsf2h4EAWGsyjA9Z+aMqTQoTpEnazqDmoNrHHd0cBNIqKwuncNIy6aZ41zbV" +
                                                    "dtBB1s/JrDBdr1wCqqi4CGThZGaYTOwEVmoJWanAPlfW3Xdgt7nGVITMKaYZ" +
                                                    "KH8tMLWGmDawNHOYqTHJWLc4cZA2n9uvEALEM0PEkubsE1cfXNp6/oKkuaUM" +
                                                    "zfqB7Uzj/drRgWmfzO5atLwCxai1LVdH4xdpLty/11vpyNkQec35HXEx7i+e" +
                                                    "3/Dho08fZz8qJNZDqjXLyGbAjxo1K2PrBnNWM5M5lLNUD4kyM9Ul1ntIDYwT" +
                                                    "usnk7Pp02mW8h1QaYqraEt9wRWnYAq+oBsa6mbb8sU35kBjnbEJIDTRyP7Ra" +
                                                    "In/in5OkOmRlmEo1auqmpYLvMupoQyrTLNWlGdsAq7lZM21YI6rraKrlDOa/" +
                                                    "NctBTo0Z6rqsYXTiCBWxnDj6mP2/7p5D3RpGIhG49tnhoDcgXtZYRoo5/dp4" +
                                                    "dmX31ZP9l5R8EHi3wslCOC/unRfH8+LivHjoPBKJiGNm4LnSsmCXHRDhgH11" +
                                                    "i/oeX7ttf1sFuJQ9UgmXiqRtoKEnTLdmdQUw0CPATgNfnPXqln3x668/IH1R" +
                                                    "nRyzy3KT84dG9mx+6g6FKMXgi8rBVAzZexEy89DYHg66cvvW7/v+2qmDY1YQ" +
                                                    "fkVo7qFCKSdGdVvYDI6lsRTgZLD94nn0TP+5sXaFVAJUADxyCu4MyNMaPqMo" +
                                                    "ujt8pERdqkDhtOVkqIFLPrzF+JBjjQQzwj+miXEjGGUKunsLtDrP/8U/rk63" +
                                                    "sZ8h/QmtHNJCIPGq984fPvPykuVKIWjXF6TBPsYlBDQGTrLRYQzmvzrU+8KL" +
                                                    "V/ZtER4CFLeWO6Ad+y4ABCpc7pkLO7+8/PXRz5TAqzhkxuyAoWs52GNhcArA" +
                                                    "hQGQhbZv32RmrJSe1umAwdA5f69fsOzMTwcapDUNmPGdYek/bxDM37ySPH1p" +
                                                    "6y+tYpuIhukq0DwgkxcwPdi503HoLpQjt+fTOYc/oq8AmgKCufooE6BEhGZE" +
                                                    "XL0qTLVY9PHQ2jLs5tklazkxM0t8KXD0osmDaBVm3YLg+3W9MbD32+tCo5Lw" +
                                                    "KZNsQvxJ9cSRlq4VPwr+wI+Re26uFJCgQgl47zye+Vlpq/5AITVJ0qB55c9m" +
                                                    "amTRW5KQ8l2/JoISqWi9OH3LXNWRj9PZ4RgqODYcQQEQwhipcRwLBY2IkQXQ" +
                                                    "ol7QRMNBEyFisFywtIl+AXa3+T5bYzv6MMXaisRgmIF8OMxcQTgTor4EgHt9" +
                                                    "moTnrA3S7HcVC9UELeYJFZtEqE7sOjiJmDd2jfyRskZQx5ou7zjy/VsSl8N+" +
                                                    "ECJm+8ef/St+YFwpqMxuLSmOCnlkdSYueqrU6S/4RaD9iQ11wQmZnZu6vBJh" +
                                                    "Xr5GsG2M//k3Ekscseq7U2PvvzG2T6rRVFyYdEPd/dbnf3wcP/TNxTJ5sQKK" +
                                                    "Tnn3Qs72gjCL+KYrzZ35jAlx1CfSA0A/CjtnsmpPCHp07/hEav1ryxQv2B/m" +
                                                    "JMot+3aDDTMjFOBzitLqI6K+DQLr2WNvnuWfLLlXqrx4couHGT/a+0PLxhVD" +
                                                    "2/5DMp0b0im85bFHTlxcvVB7XiEV+fgsKdmLmTqKozLmMLhAc2NRbLYWJzQV" +
                                                    "WqMXBo1lE1pgvwBaI9KS+PmAoHrsBti7FbtHOakayOreu+Uh7NZI8F3LSeWw" +
                                                    "padKwVlMbCqWdzm0Zk/e5n8tr+LZ3/O8GSWet4Hu8hfbShZ78AHlygyFjz0m" +
                                                    "Dhq8gcoZ7CCQo7rPihPbyuQfqAFCtSJmwFklb1D5btJOTtTX3jSx6QtR/eTf" +
                                                    "NlF4YKRhl0JELhhX2w5L60KwqMRnGZVQNDWXr1/BWOJfyLtTUmfh1R6mBtPh" +
                                                    "XyEZaDSlgAzw2xsVEu0GhAAiHD5h+xffINI+5qW4zEs5UhC8WPkUfhWVQRif" +
                                                    "4nXvx1JWvu/7tVMTa9ftvnrPayIwqzSDjo6K1yA8bmUFmI/H+ZPu5u9VvWbR" +
                                                    "b9NORxf4ODMNuyav7AvJNrd8ddSdsbmoZ0bfvemd+16f+FqUZ38D6fTkZXYR" +
                                                    "AAA=");
}
