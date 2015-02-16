package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class NormalShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { Vector3 n = state.getNormal(
                                                                       );
                                                   if (n == null) return Color.
                                                                           BLACK;
                                                   float r = (n.x + 1) * 0.5F;
                                                   float g = (n.y + 1) * 0.5F;
                                                   float b = (n.z + 1) * 0.5F;
                                                   return new Color(r, g,
                                                                    b); }
    public void scatterPhoton(ShadingState state, Color power) {  }
    public NormalShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1169281078000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWwURRyfu37RUrjS8lEQyldBS+EWYjDBIlpqgeIBlxZI" +
                                                    "KIFjujt3t3RvZ9mda6/FKpCYEh6I0YJgtA8GgihfMRI0hoQngeALxmh8EHyT" +
                                                    "qDzwgiao+J+Z+9jbuxZ58ZKdm535/+f//Zv/nruPyhwbNVvUGIgZlAVJigX3" +
                                                    "GiuDbMAiTnBjaGUY2w7R2gzsOFthLaIuuBR4+OjteI0flXejOmyalGGmU9Pp" +
                                                    "JA41+ogWQoHcartBEg5DNaG9uA8rSaYbSkh3WEsITXSxMtQYyqiggAoKqKAI" +
                                                    "FZTWHBUwTSJmMtHGObDJnH3oDeQLoXJL5eoxND//EAvbOJE+JiwsgBMm8Pft" +
                                                    "YJRgTtloXtZ2aXOBwcealZH3dtd8VoIC3Sigm11cHRWUYCCkG1UnSKKH2E6r" +
                                                    "phGtG00xCdG6iK1jQx8UenejWkePmZglbZJ1El9MWsQWMnOeq1a5bXZSZdTO" +
                                                    "mhfViaFl3sqiBo6BrdNztkoL1/F1MLBKB8XsKFZJhqW0Vzc1huZ6ObI2Nr4G" +
                                                    "BMBakSAsTrOiSk0MC6hWxs7AZkzpYrZuxoC0jCZBCkOzxjyU+9rCai+OkQhD" +
                                                    "9V66sNwCqkrhCM7C0DQvmTgJojTLEyVXfO5vXn10v7nB9AudNaIaXP8JwNTg" +
                                                    "YeokUWITUyWSsXpJ6DiefvWwHyEgnuYhljRXXn/wytKGazckzTNFaLb07CUq" +
                                                    "i6ineibfnt3WtKqEqzHBoo7Og59nuUj/cHqnJWVB5U3Pnsg3g5nNa51f7zjw" +
                                                    "CfnNj6o6ULlKjWQC8miKShOWbhB7PTGJjRnROlAlMbU2sd+BKmAe0k0iV7dE" +
                                                    "ow5hHajUEEvlVLyDi6JwBHdRBcx1M0ozcwuzuJinLIRQBTyoGZ4qJH/inyFV" +
                                                    "2eZAuitYxaZuUgWSl2BbjStEpZEe8G48ge1eR1GTDqMJxUmaUYP2K46tKtSO" +
                                                    "Zd9VahPFiWON2Mpmaiew0SVegjzZrP9HTIpbW9Pv80EgZnthwIAK2kANoI2o" +
                                                    "I8m17Q8uRG75s2WR9hMAF0gLpqUFubSglBZ0S0M+nxAylUuVkYY49ULFAxZW" +
                                                    "N3Xt2rjn8IISSDGrvxSczEkXgJ1pVdpV2paDhQ4BfirkZv1HO4eDf555Weam" +
                                                    "MjaGF+VG1070H9z+5nI/8ueDMTcNlqo4e5hDaBYqG71FWOzcwPC9hxePD9Fc" +
                                                    "OeahexolCjl5lS/wBsGmKtEAN3PHL5mHL0euDjX6USlAB8Alw5DegEQNXhl5" +
                                                    "1d6SQU5uSxkYHBXh4VsZuKticZv251ZEdkzmQ61MFB5Aj4ICdNd9ee3k5feb" +
                                                    "V/nd+Bxw3XhdhMlqn5KL/1abEFj/6UT43WP3h3eK4APFwmICGvnYBrUP0QCP" +
                                                    "vXVj349375z6zp9LGAaXYLLH0NUUnLE4JwWQwQB04mFt3GYmqKZHddxjEJ53" +
                                                    "fwUWrbj8+9EaGSgDVjJxXvrkA3LrM9eiA7d2/9EgjvGp/GbKWZ4jkw6oy53c" +
                                                    "att4gOuROvjtnJPX8YcAnABWjj5IBP4gYRkSrg+KiDSJcZlnbzkf5lkFeymx" +
                                                    "Up9+Ey/zxdjIh2el3/j0OTelT8ynMTSjoK5lKXMHzxnrMhIX6alDI6PaltMr" +
                                                    "ZFnW5gN8O/Qv57//+5vgiZ9vFkGTSkatZQbpI4ZLpxIuMg8ONol7OlcUR85+" +
                                                    "eoXdbn5RilwyNhJ4Ga8f+nXW1jXxPU8BAnM9xnuPPLvp3M31i9V3/KgkW/8F" +
                                                    "rUc+U4vbDSDUJtArmdyhfKVKhLlBKDAF3FHHAzoTnonpi0n88906i49TZbXy" +
                                                    "4XlP3viFP/2ZGDcUxFiYSqCz4YmZIZvuJuuS/63hDiHm1XEycyMfWqE0k5YG" +
                                                    "lzVEsWmcPtvWE3D196V7E2Wo9m7vB/fOy4h6GxkPMTk8cuRx8OiI39XtLSxo" +
                                                    "uNw8suMTWk6Sjn0MPx88//CHm8AX5I1f25ZuO+Zl+w7L4nUwfzy1hIh1v1wc" +
                                                    "+urjoWF/2iWrGKroodQg2CysWLHwUjbO/EGz4Qmk4xz4z3H25dfynKK1DI0s" +
                                                    "b+WJOGbHOHHcxYftDE2MEdYJfDxli8KEnoCOlkMmtZ9onUjaNelMzmT0U2Ux" +
                                                    "f+3mQ0SQknEsiPGhh0GsVcwgu8NxytJFHuJDWGrXyVBpH9W1ImDKULW7oeFY" +
                                                    "Xl/w4SSbffXCaGDCjNFtP4grOtuQV0JXHE0ahqvQ3UVfbtkkqgttK+XNa4k/" +
                                                    "WgyKZYsFlSUnQl1T0sNqjZcezOJ/brIkhNNFBmmZnrmJwOoSIOLTASsT8Rpx" +
                                                    "hfGvoaBs/VMo76ax8u8d95XOi158lGagMyk/SyPqxdGNm/c/eOG0wOEy+Jwd" +
                                                    "HBQfMfBNJhuVLPzOH/O0zFnlG5oeTb5UuShTc3ktjEe3ucVv+vaExcTdPPjF" +
                                                    "jM9Xnxm9I1qNfwHjrpunLRAAAA==");
}
