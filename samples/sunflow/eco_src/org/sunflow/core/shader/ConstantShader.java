package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class ConstantShader implements Shader {
    private Color c;
    public ConstantShader() { super();
                              this.c = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { c = pl.getColor(
                                                                       "color",
                                                                       c);
                                                              return true;
    }
    public Color getRadiance(ShadingState state) { return c; }
    public void scatterPhoton(ShadingState state, Color power) {  }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1160854688000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKUYa2wcR3l850d8dvEjsfNoYseOm9I8btuKIBpXJMY4rZtL" +
                                                   "c7VjN3XaOuPdubuN\n93a2u3P22Y1KC1ITGqAEKAKJphGKlLS0tFJBAamUVG" +
                                                   "2hNEJqkShSpQZQJECCIiGkEgQ/+Oab29vb\nvbNThZN2dnbme8z3/uae/5A0" +
                                                   "eC5Zr3tJseAwLzk8nqaux4xhi3reAVia1t9saE6f3WvzGKlLkZhp\nCNKW0j" +
                                                   "3NoIJqpqGNfn6w6JKtDrcWshYXSVYUySPWjhK9u1I7qgjee+p852Nn6ntjpC" +
                                                   "FF2qhtc0GF\nye0Ri+U9QdpTR+gc1QrCtLSU6YnBFLmO2YX8MLc9QW3hPUQe" +
                                                   "IfEUaXR0SVOQvpTPXAPmmkNdmteQ\nvZZGtkBhpcsENW1mDJXZAea2MCYcu4" +
                                                   "Q3Vg0NRFbIzUkQB08AUm8sS62krRLViZ+b/PTR08/GSdsU\naTPtcUlMB0kE" +
                                                   "8JsirXmWn2GuN2QYzJgiHTZjxjhzTWqZi8h1inR6ZtamouAyb4x53JqTgJ1e" +
                                                   "wWEu\n8vQXU6RVlzK5BV1wt6yjjMksw/9qyFg0C2J3B2IrcffIdRAwYcLB3A" +
                                                   "zVmY9SP2vaYPHeKEZZxoG9\nAACoTXkmcrzMqt6msEA6lS0tame1ceGadhZA" +
                                                   "G3gBuAiybkmiUtcO1Wdplk0LsiYKl1ZbANWMipAo\ngnRFwZASWGldxEoV9t" +
                                                   "na/dHxc997dTf6dr3BdEuePwFIPRGkMZZhLrN1phCvFJLfGr2vsD5GCAB3\n" +
                                                   "RYAVzNAN5ydSf/l5r4K5vgbM/pkjTBfT+t0ne8cevoOTuDzGCod7pjR+SHIM" +
                                                   "h3RpZ7DoQNR2lynK\nzaS/eWHsF/c9+hz7a4wkRkmjzq1CHvyoQ+d5x7SYew" +
                                                   "ezmUsFM0ZJM7ONYdwfJU0wT4HLq9X9mYzH\nxCipt3CpkeM3qCgDJKSKmmFu" +
                                                   "2hnuzx0qcjgvOoSQJnjIdniaifrhW5DbkppXsDMWn9c8V9e4my1/\n69xlmp" +
                                                   "ejBnM1P9LH8TMpXcgRZELL8TzTqE5t0+Za1oSg1fl2g83J97USLspTd87X1c" +
                                                   "k0GA1nCyLh\nTm4B7LR+9vLbR0f2fvm4chXp3iV5BdkM/JIlfknJL6n4JcP8" +
                                                   "SF0dslkl+SqbgcZnIXYhy7XeNP7A\nXYeP98fBWZz5elCXBO0HyUqHGdH5cB" +
                                                   "Dgo0hYBy9b8/1Dx5JXzu5SXqYtnYdrYre888LF0/9s3RIj\nsdpJUgoJaToh" +
                                                   "yaRlZi0nv4FoWNWi//cn9r383sUPPhkEmCADVXFfjSnjtj9qDpfrzIBMGJA/" +
                                                   "s7Yt\nfi+ZPBkj9ZAMIAHi+SG39ER5hOJ30M+FUpamFGnJcDdPLbnlJ7CEyL" +
                                                   "l8PlhBP2nH+UowTot06LXw\ntJY8HN9yt8uRY7fyK2ntiBSYa698qfHm373S" +
                                                   "8iaqxU/LbRWFb5wJFeQdgbMccBmD9Q++k/7mUx8e\nO4SeUnIVAdWwMGOZeh" +
                                                   "FQNgcoEN0WZBhpyIEJO88NM2PSGYtJj/tv2w23/PhvX2tXprFgxbfstqsT\n" +
                                                   "CNbXfo48evHBf/UgmTpdVpdAjABMSbMyoDzkunRBnqP42G82fPeX9GlIfpBw" +
                                                   "PHORYQ4hKBlBPWqo\n9y04JiN7t8ihH2hvW8L1a9Tyaf3oc9n+wkO/+imeuo" +
                                                   "VWNgWVZthHnUFleTlsktpdHY3eO6mXA7hP\nXbj7/nbrwn+A4hRQ1KGGevtd" +
                                                   "CPtiyIgl6Iam9197vfvwu3ES20MSFqfGHor+T5rB8ZiXg6xTdHbt\nRt9qn1" +
                                                   "8hRxSZoBLWlRRQrPiS+eKmpcN/j+wEgsiZntl2LvX2/qdRAUsGfo1CGKGz+O" +
                                                   "rEqSu/FpeQ\nThCBEruvWJ1SoXsKcD/z3lxH40vP5GOkaYq066VkOUmtgvTz" +
                                                   "KWhHPD+DQg8Y2g+3FqqODpYzzPpo\n9FewjcZ+kMphLqHlvDUS7hjdq+BJlM" +
                                                   "I9EQ33OoKTXYgygOON5eBsclxzjsqeDyIE91YLsrqyaph5\naGhktHFXJQ45" +
                                                   "3iqH3crGO2r5Ap5yc8gHahHHkqRqkPTgDUv1Q9jLHTv4j9bH6RsPqHrSGe4x" +
                                                   "RqAP\n//PC6+zG27/6xxqFsFlwZ7vF5phVcaa4ZBmqY/uwVQz84Ilnf3BevL" +
                                                   "t1p2K5ZWkfjiJu2Xl6sXfn\niyeuoXr1RpQQJd0xd/098Zz5Vgy7WeVWVV1w" +
                                                   "GGkw7EwJOE/BtQ+EXGpjuIIM+rb031UVJLBwkP5i\nqNeYb+ueKlujqAyabJ" +
                                                   "lffbDuSrBx9R5KjyKbg8sk2AflMAEVpuDAnY+BNddUXhddMw9t5xzW1cuP\n" +
                                                   "9//srYlnjilDLpOMQljT+hd+/4fZ+JOvzSi8aMaJAJ/sOfOnly+PrVL+p+4n" +
                                                   "m6quCJU46o6CwrQ5\nMgL6luOA0G9s7Xv+kbFLeCKJtw+CeIZzi1E7CM3JZU" +
                                                   "IzlKbx456w6W+Gp6tk+q6Pbfq6cJhvqBnm\ncM2SF02GZOxlTIu5ZlaQliwT" +
                                                   "Y4Anvfhq6SkQ3/p/xP9sqX/y+6hr8vzlxb+aJMjm6DLq+aIcFgS5\nztOpgG" +
                                                   "hK57goJZXDjpIKClH9HDeNQCuLH1crUAw+Eb4gyA5pTdVfCuoarKfef/j+j1" +
                                                   "K//Te2uuWr\nagvcFzMFy6osYhXzRsdlGROFaVElzcHXV2pVCHVpgUBXEzzs" +
                                                   "CQX/pCDtUXiQXL4qwb4BrlQBBjFT\nmlUCPSVIHIDk9NuOb6N27JBkMU+qYl" +
                                                   "4M6UpqZlMoneC/PH7uLqj/eab1gy8c2lg8ceDrWBAadIsu\nLuKFPkWaVItf" +
                                                   "zv99S1Lzab1DXnpx8pUf3uanAGwBVxWD8hxy71vV7jK2h5pTu68eyTsCO+HF" +
                                                   "n6z+\n0e1nT12KYWf/PxElcTucEwAA");
}
