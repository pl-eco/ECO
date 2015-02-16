package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class PrimIDShader implements Shader {
    private static final Color[] BORDERS = { Color.RED, Color.GREEN, Color.
                                                                       BLUE,
    Color.
      YELLOW,
    Color.
      CYAN,
    Color.
      MAGENTA };
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { Vector3 n = state.getNormal(
                                                                       );
                                                   float f = n == null ? 1.0F
                                                     : Math.
                                                     abs(
                                                       state.
                                                         getRay(
                                                           ).
                                                         dot(
                                                           n));
                                                   return BORDERS[state.getPrimitiveID(
                                                                          ) %
                                                                    BORDERS.
                                                                      length].
                                                     copy(
                                                       ).
                                                     mul(
                                                       f); }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public PrimIDShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1163966490000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcRxWfOzv+F8d2nCZxQ+wkjlNw3N62oCKKq4B9tZsL" +
                                                    "l/qwnUi4tO7c3tzdxnu72905++Jg2kRUiVopqsBtUyiWqFJKS5pUiKggVClf" +
                                                    "oK1KkVohEB9ogC9UhEjkA6WiQHlvZv/d3tmlEnDSzs3OvDfz3rz3fu/NnrtK" +
                                                    "1jk2GbJM/WhBN3mCVXjiiH5rgh+1mJM4kL41Q22H5ZI6dZxpGJtV+1/sfPf9" +
                                                    "R4tdcdI0QzZRwzA55ZppOJPMMfV5lkuTzmB0TGclh5Ou9BE6T5Uy13QlrTl8" +
                                                    "OE3Wh1g5GUh7IiggggIiKEIEZSSgAqYNzCiXkshBDe7cT75KYmnSZKkoHie7" +
                                                    "qhexqE1L7jIZoQGs0ILvh0EpwVyxyU5fd6lzjcKPDSnLT9zb9YMG0jlDOjVj" +
                                                    "CsVRQQgOm8yQ9hIrZZntjORyLDdDNhqM5aaYrVFdWxRyz5BuRysYlJdt5h8S" +
                                                    "DpYtZos9g5NrV1E3u6xy0/bVy2tMz3lv6/I6LYCuWwJdpYbjOA4KtmkgmJ2n" +
                                                    "KvNYGuc0I8fJjiiHr+PAF4AAWJtLjBdNf6tGg8IA6Za206lRUKa4rRkFIF1n" +
                                                    "lmEXTratuiietUXVOVpgs5z0ROkycgqoWsVBIAsnm6NkYiWw0raIlUL2uXrX" +
                                                    "7aePGfuNuJA5x1Qd5W8Bpr4I0yTLM5sZKpOM7XvTj9MtL5+KEwLEmyPEkual" +
                                                    "r1z7/I19l16VNB+rQzORPcJUPquezXa8uT05eFsDitFimY6Gxq/SXLh/xp0Z" +
                                                    "rlgQeVv8FXEy4U1emvzZlx58nl2Jk7YUaVJNvVwCP9qomiVL05l9JzOYTTnL" +
                                                    "pUgrM3JJMZ8izdBPawaToxP5vMN4ijTqYqjJFO9wRHlYAo+oGfqakTe9vkV5" +
                                                    "UfQrFiGkGR4yBE8bkT/xz4mqHHLA3RWqUkMzTAWcl1FbLSpMNWezcLrFErXn" +
                                                    "HEUtO9wsKU7ZyOvmguLYqmLaBf9dNW2mOEWaY7aSsbVS6o4p8ZJAZ7P+P9tU" +
                                                    "UNuuhVgMDLE9CgM6RNB+UwfaWXW5PDp27fzs63E/LNxzAuCC3RLubgncLSF3" +
                                                    "S4R3I7GY2OQ63FVaGuw0BxEPWNg+OHXPgftO9TeAi1kLjXDISNoPerqijKlm" +
                                                    "MoCFlAA/FXyz5+m7Tybee/Zz0jeV1TG8Lje5dGbh+OEHbo6TeDUYo2ow1Ibs" +
                                                    "GYRQHyoHokFYb93Ok++8e+HxJTMIxyp0d1GilhOjvD9qBNtUWQ5wM1h+7056" +
                                                    "cfblpYE4aQToALjkFNwbkKgvukdVtA97yIm6rAOF86ZdojpOeXDXxou2uRCM" +
                                                    "CO/owKZbOgoaMCKgAN3xH1968uI3h26Lh/G5M5TxphiX0b4xsP+0zRiM//ZM" +
                                                    "5huPXT15tzA+UOyut8EAtkmIfbAGnNhDr97/m8tvn/1lPHAYDkmwnNU1tQJr" +
                                                    "3BDsAsigAzqhWQcOGSUzp+U1mtUZ+t0/OvfccvHPp7ukoXQY8ex844cvEIxf" +
                                                    "P0oefP3ev/WJZWIqZqZA84BMHsCmYOUR26ZHUY7K8bd6n3yFfhuAE8DK0RaZ" +
                                                    "wB8iNCPi6BPCIoOivSkydzM2O62auYoY6fEDanD1+BjHBBuKq79P6NkTf3hP" +
                                                    "aFQTGXXySoR/Rjn31LbkviuCP3BR5N5RqUUaKEYC3k8+X/prvL/pp3HSPEO6" +
                                                    "VLfSOUz1MnrLDGR3xyt/oBqqmq/O1DItDfshuD0aHqFto8ERIBz0kRr7bTIe" +
                                                    "BM1GONPr8ZRH4Wl388N6bHB2k4XtdZUYEZ3PCJZdoh3A5uPCJg2cNFu2Ng9p" +
                                                    "DLzXEUUVBzk0g+oVmBudmLxjbHIKLLdndcsJJ5KZeuW7u3/xwMru38Opz5AW" +
                                                    "zQH9RuxCndIhxPOXc5evvLWh97wAk8YsdaSm0ZqrtqSqqpTEwbT7BzOG53AA" +
                                                    "njfcg3lDJs4v/3cymnPU4aykpEpQPWWowXQvY/5P15fRtJmTreF8pyERYoRb" +
                                                    "rWL8xdyIw/dPYZP0HCFV3xHi2P0ENvvA7k06Mwq8uHbAYm6FOmneLeSUpe7L" +
                                                    "c0+984JMhNHojBCzU8sPf5A4vRwPlca7a6rTMI8sj4WVN0grfwC/GDz/wget" +
                                                    "iwPSyt1Jt0bb6RdploWovGstscQW43+8sPST7y2dlGp0V1eGY3DxeeFX//x5" +
                                                    "4szvXqtThjSAi+LLqFXxLRGXx+tZTmIvggNUz6bBEMa9OVmYaGbCv7nAZKXG" +
                                                    "pjbprSpLDoooCNDr4ee+/xJ/c+izUoO9qxswyvjKiT9tm95XvO8jFCM7IucZ" +
                                                    "XfK5g+deu/MG9etx0uCDYM0VqJppuBr62mwGdzZjugoA+6RLi7Ou788x4c+V" +
                                                    "NVIWW2OugA0FIFTRRtKkcO476qfksZLFRRJd/NHWH97+7MrboiaoCBjussSC" +
                                                    "2ao8WC+KRdUqC1V01N7VrlrCSc+eWF7JTTxzS9yVd5qTVm5aN+lsnumhrRpE" +
                                                    "/x4fFTcRN2d0uKjYEU0XQuZA4sHV/LivRnbhGAwgGk/CI9sSJpuS/yOZlNim" +
                                                    "vIYBjmED9WRT2cpB9Aqag9h8UZp0CnJT1jR1Ro3aoiOiND5kOzzdrtLd/7HS" +
                                                    "sWqD9dY1GNzF8WsEE8t8bQ2lTmFznJP1BcYngQ+9HYfGP1QFkdX3wdPjqtDz" +
                                                    "Ue2Grw+JzQTp6TXEfBSbRzjArEo52DNTNLkLAhETNM6bWq5O0cdJe/jihaHT" +
                                                    "U/OBR36UUM+vdLZsXTn0a5n9vQ8HrXB7z5d1PVwDhfpNls3ympC2VVZEMszO" +
                                                    "1AsqeRXE8kZ0hLhPSPpvcdIVpQe18C9MtgI2C5GB77m9MNF3AP+BCLtPW57H" +
                                                    "dAVwL2vBCgmFJ942wm9VVw+Ea/HxzIPWsvx8NqteWDlw17Frn35G4DRAFF1c" +
                                                    "xFVaoCKSFyofnnetupq3VtP+wfc7Xmzd4yFJ1VUrLBv2j/wbo7KZiaoUAAA=");
}
