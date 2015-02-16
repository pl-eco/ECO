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
    long jlc$SourceLastModified$jl7 = 1170612556000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUZf3AU1fndXX4TuBAgSfkRSAhSQO6kFVoIIkkIEjwg5QKO" +
                                "0Xpsdt/lFvZ2l913yRGF8cc4MM6ATsUKFjJjB9rSKjCdMrbTsUNrR0GkMzja" +
                                "FlvR/lORlg78obaDSr/v7d7d3uZyXGLMzH7Zfe977/vxvp/vXrpKik2DLNA1" +
                                "ZUevorEATbLAVmVxgO3QqRlYG1rcKRgmldoUwTS7YCwiNp70f3rjmViVl5R0" +
                                "k0mCqmpMYLKmmhupqSl9VAoRf2a0XaFxk5Gq0FahTwgmmKwEQ7LJmkNknGMp" +
                                "I02hFAtBYCEILAQ5C8GWDBYsGk/VRLwNVwgqM7eTXcQTIiW6iOwx0pC9iS4Y" +
                                "QtzeppNLADuU4fdmEIovThpkVlp2S+YhAj+3ILj/+Yeqfukj/m7il9UwsiMC" +
                                "EwyIdJPKOI33UMNskSQqdZOJKqVSmBqyoMgDnO9uUm3KvarAEgZNKwkHEzo1" +
                                "OM2M5ipFlM1IiEwz0uJFZapIqa/iqCL0gqw1GVktCVfjOAhYIQNjRlQQaWpJ" +
                                "0TZZlRiZ6V6RlrHpXkCApaVxymJamlSRKsAAqbbOThHU3mCYGbLaC6jFWgKo" +
                                "MDJ12E1R17ogbhN6aYSROjdepzUFWOVcEbiEkSluNL4TnNJU1yk5zufq+uX7" +
                                "HlbXqF7Os0RFBfkvg0X1rkUbaZQaVBWptbByfuiHQs2re7yEAPIUF7KF88oj" +
                                "11feXn/6jIUzLQfOhp6tVGQR8UjPhAvT2+Yt9SEbZbpmynj4WZJz8++0Z5qT" +
                                "OnheTXpHnAykJk9vfP3+R39O/+UlFR2kRNSURBzsaKKoxXVZocY9VKWGwKjU" +
                                "QcqpKrXx+Q5SCu8hWaXW6IZo1KSsgxQpfKhE49+goihsgSoqhXdZjWqpd11g" +
                                "Mf6e1AkhpfAQDzwriPVXgYCRB4KbTDD3oCAKqqxqQTBeKhhiLEhFLdID2o3F" +
                                "BWObGRQTJtPiQTOhRhWtP2gaYlAzetPfombQYGdMYxo4FLwH0Mj0r3f7JEpX" +
                                "1e/xgOKnu91eAY9ZoykSNSLi/kRr+/XjkXPetBvYemFkOlAJ2FQCSCXgoEI8" +
                                "Hr75ZKRmnSicxzbwbIh5lfPC31+7ZU+jD0xJ7y9CbSa5q9WlPmChiyvu1Kt/" +
                                "c/rgqRcWLPU6/d/viKhhyixrmpih22VQCuPvH+h89rmrux/gRAFjdi4CTQjb" +
                                "wLYgYIIYT57ZfvGDS0fe8aYZ9TEIsokeRRYZKRN6IEIJImOkPB1qhggyYzh/" +
                                "5bHmyOP7B6UNRxdZXlWd7QPtEOJf/vMXbwUOfHg2xwGUM01fqNA+qjhoViFJ" +
                                "sBCbWruoreOhrINnChEc+aljv3iFXViwzCI5f/iE5174xuNXpnatiG3xEm92" +
                                "0kLqMFSBKzsx1aRTykyX8O4tj6176ew9t4k/8BKfHbFyROfsRc1ONQBRg0I6" +
                                "UVGhOFIBRBvdBm1oIpUg52Tozp8lnIq8urPJS4og7EKqYQKEBoji9W7iWZGy" +
                                "OWV1SKoYlBDVjLig4FQqVVSwmKH1Z0a4p03g7xPheMrQLPx4TnYs4f9xdpKO" +
                                "cLLlmRx/Gof1CBr42XrxtRHBbLSs2zI2DnFPgdiLh9G0SY1rkhyVhR6Ford9" +
                                "7p+z6NS/91VZFqTASOp0br/1Bpnxb7SSR8899Fk938YjYt7N+F0GzXK/SZmd" +
                                "WwxD2IF8JB97e8bBN4TDkBYgFJvyAOXRldiugkwt4xIv5nCpa245gkWMlIJT" +
                                "tMdlBlTm5SnWDBlw5D47wQV3Vn+w7dDlly2bd2dDFzLds/+pm4F9+72OkmH2" +
                                "kKztXGOVDZzN8dZR34Q/Dzxf4oMy4ICVNqrb7Nw1K528dB3PsyEfW5zE6o9O" +
                                "7Pztz3bu9to6mcuIDyKPPkRhfGBB2ur44Dx4am2rqx2t1WWfj4cjePj3FFdG" +
                                "iEMODbRqCVWCOqlVS3IS6/Ic8PcQrIED1g0K8dfynxUIWi26qxgp6tNkqTBx" +
                                "74WnwRa3YWzELeIIRSlxZwxJgOGYgNJiXUxTWHVDlLKZYlH77RRCrRNBjkMp" +
                                "iN6oGThtJeoH82hNQnAfg+YF0y5+hAvTDwahubZ+5o6Nfpx8KXnmVAS9DGsu" +
                                "mRXGciUOzoQnYLMcGHuW+/LMcbCdQZunwDGtkqPRhEnRuiGz5DLU0h5NU6ig" +
                                "FiZYIzxLbMGWjL1gT+SZexLBLojmXDDIdnYct2XD2YHChbjLFuKusRdib565" +
                                "pxHscQiBVdmIhODhOXe8rsk0fVaHE+BNNsTsPEL5MkJBxQgtL4TxfNIdzDP3" +
                                "I4tRBM8mU1GjimdY5CpgcZWPDwgPUVkVeDv4PN/zAIIXEByCQraXMl6Wmnnr" +
                                "wXAC6l1Hu7lXHjz/5if+x6ykmp2M+Y2DvdS97uJffd8ax5qe5nVkUY9g8rBV" +
                                "BrWViZiMzBr+9oLvZWXacbc8uSmZk+Pk0weXUqI/o0SOgMPHsjJxbiVExI54" +
                                "JHzq4u4lvEbw98nQrFKpy75Qya6NMn1Pc9YlS041RcTLJ/aeabiyeRLvnlMa" +
                                "cfY46wR9SI+zRjBjMF5c+t7vX6vZcsFHvKtJhaIJ0moBcw20wVCWUjMGXV1S" +
                                "v3sl91FPPxajXnyDzeYMI7ItEy8GI+Ijh748//HOS2d9pARKXazXIVFDac5I" +
                                "YLhrK+cGTV3wtgpWQSE9wVoN6TKtejCB6vRoumpnZOFwe/NCz1Xc48UPBAFq" +
                                "8NoDt73D1TEkdN05y42pcvTGtAsK6QKUl5bdDpGkmrvNhIwJYrvnnIQWb1Jb" +
                                "qCUcjnTd39ke2dyysaOlNdTOjVSHSU8Xvh5O5jm9+2RFahMMyXK8ozfLZ6+s" +
                                "PbuMO95QJY1SMXVcMUI/C2Jhq6lUzURI3eJwSFPcNAy/jjvOiPjMO9fGb772" +
                                "u+vcx9z95nGrsbKDokFq3RcNtkvceXr9g1XK6RuwSTdsIorUNDcYEjX4+pO2" +
                                "LxDuC7wXS5KcQfjHCH6C4DUErztjcmF5wAq+eeL8uTxz5930Dt8qmVpx/k0E" +
                                "byH4E8T5GOikTZO4J7TwpX9wZJU/5lQGNPG528T2uM54Yzfw69pfLf/p4CV+" +
                                "S4IbXeD7I3gbwbsF6urWjYZlTQjfy6OrvxeoK75dI8e4iOBvCN6HdE23JwSr" +
                                "fh0oTElcSgQf8kUI/jFGkjsF+yjP3McjF/qfCC4juAK2wTTrSjtHgeGYKFwV" +
                                "VxH8B8G1kahiJA7zaZ65/xaojwy9FzNe8wmCzxD8D6s3jcnRHfgVHqEOPueL" +
                                "EHzxNenA48szV/wVdOBBm/QUIShhpNzSQYuijEINnvKUGjwVY6kG5+2D+55A" +
                                "0dReLkZVnkVg5fUZK+/AK1wjoUMh154UqY5Rjm8x5auo0Y9gMoI6YKtfsBve" +
                                "EWpwWlqD00eiwTxxxZtG8EzEsRbObeOwcddTwxGaRpyHXuTrGhDgMXrmZOQZ" +
                                "tRK+OUZKcDpL4Fai3zFK0RciCCJYNAai38nHkoyMc/zagt1A3ZBfb61fHMXj" +
                                "g/6y2sFNf+F33elfBctDpCyaUBRHYewskkt0g0ZlLma5VWnxdtfzXUgM7msv" +
                                "sGv8h2J4vmOhNQN/DjRGSu03J9IKRnyAhK93Q61IsopE3V0yzh62cVqXsH7T" +
                                "jognBteuf/j6kqO8wC2GInSA52LoK0qtm3q+KV59Nwy7W2qvkjXzbkw4WT4n" +
                                "ddfKS81qh6HVObLNu/8HGW4AIj8gAAA=");
}
