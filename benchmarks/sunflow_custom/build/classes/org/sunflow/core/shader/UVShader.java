package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class UVShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { if (state.getUV() == null)
                                                       return Color.
                                                                BLACK;
                                                   return new Color(state.
                                                                      getUV(
                                                                        ).
                                                                      x, state.
                                                                           getUV(
                                                                             ).
                                                                           y,
                                                                    0);
    }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public UVShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1165807990000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXW2wUVfTu9l2g2xdPobRQwPLYgRhMEERrLVBcYNPSJpTA" +
                                                    "cjtzd3fo7Nxh5m67FKtAYkr4IEYLgtF+GAiivGIkaAxJvwSCPxij8UPwT6Ly" +
                                                    "wQ+aoOK59+5zdlvkw03m7p1zz7nn/Zjz91GJY6NlFjX2RwzK/CTB/HuN1X62" +
                                                    "3yKOf3NgdRDbDtHaDOw42wEWUhdc9j189Ha02otKe1EdNk3KMNOp6XQShxoD" +
                                                    "RAsgXwbabpCYw1B1YC8ewEqc6YYS0B22NoCmZJEy1BxIiaCACAqIoAgRlNYM" +
                                                    "FhBNI2Y81sYpsMmcfegN5AmgUkvl4jHUlHuJhW0cS14TFBrADeX8vQeUEsQJ" +
                                                    "GzWmdZc65yl8fJky+t7u6s+KkK8X+XSzi4ujghAMmPSiqTES6yO206ppROtF" +
                                                    "NSYhWhexdWzoQ0LuXlTr6BETs7hN0kbiwLhFbMEzY7mpKtfNjquM2mn1wjox" +
                                                    "tNRbSdjAEdB1RkZXqeEGDgcFK3UQzA5jlaRIivt1U2NovpsirWPza4AApGUx" +
                                                    "wqI0zarYxABAtdJ3BjYjShezdTMCqCU0DlwYmjPhpdzWFlb7cYSEGJrlxgvK" +
                                                    "I8CqEIbgJAxNd6OJm8BLc1xeyvLP/a3rjh0wN5leIbNGVIPLXw5EDS6iThIm" +
                                                    "NjFVIgmnLg2cwDOuHfEiBMjTXcgS5+rrD15e3jB+Q+I8UwBnW99eorKQerqv" +
                                                    "6vbctpY1RVyMcos6Ond+juYi/IPJk7UJCzJvRvpGfuhPHY53fr3j4CfkNy+q" +
                                                    "7EClKjXiMYijGpXGLN0g9kZiEhszonWgCmJqbeK8A5XBPqCbREK3hcMOYR2o" +
                                                    "2BCgUirewURhuIKbqAz2uhmmqb2FWVTsExZCqAwetASeCiR/4p+hkNLtQLgr" +
                                                    "WMWmblIFgpdgW40qRKWhPrBuNIbtfkdR4w6jMcWJm2GDDiqOrSrUjqTfVWoT" +
                                                    "xYlijdhKd0+X2Ph5oFn/P4sE17J60OMBB8x1p78BmbOJGoAbUkfjr7Q/uBi6" +
                                                    "5U2nQ9I+DDUCJ3+Sk59z8ktO/hQn5PEIBvWco/Qu+KYfshzq39SWrl2b9xxZ" +
                                                    "UARhZQ0Wg2E56gLQLylGu0rbMqWgQxQ8FeJx1kc7R/x/nn1JxqMycd0uSI3G" +
                                                    "Tw4e6nlzpRd5cwswVwtAlZw8yMtmujw2uxOv0L2+kXsPL50YppkUzKnoycqQ" +
                                                    "T8kze4HbATZViQa1MnP90kZ8JXRtuNmLiqFcQIlkGEIaqk+Dm0dOhq9NVUuu" +
                                                    "SwkoHKZ2DBv8KFXiKlnUpoMZiIiMKr7UyiDhDnQJKArthi/HT115f9kab3ZN" +
                                                    "9mV1uS7CZIbXZPy/3SYE4D+dDL57/P7ITuF8wFhYiEEzX9sg38EbYLG3buz7" +
                                                    "8e6d0995MwHDoPHF+wxdTcAdizNcoBoYUJG4W5u7zRjV9LCO+wzC4+4v36JV" +
                                                    "V34/Vi0dZQAk5eflT74gA5/9Cjp4a/cfDeIaj8q7UUbzDJo0QF3m5lbbxvu5" +
                                                    "HIlD3847dR1/CMUSCpSjDxFRc5DQDAnT+4VHWsS6wnW2ki+NVt5ZQkBmJd/E" +
                                                    "S5NYm/myRNqNb5/NxvSI/XSGZubltExlbuB5EzUg0TxPHx4d07adWSXTsja3" +
                                                    "qLfDzHLh+7+/8Z/8+WaBSlLBqLXCIAPEyJKpiLPMKQdbRG/OJMXRc59eZbeX" +
                                                    "vSBZLp24ErgJrx/+dc729dE9T1EE5ruUd195bsv5mxsXq+94UVE6//PGjVyi" +
                                                    "tdlmAKY2gfnI5AblkErh5gYhQA2Yo447dDY8lclmJP75aZ3F13qZrXx5zhU3" +
                                                    "XmFPb8rHDXk+FqoSmGZ4YKbQZmSjdcn/1mCHYPPqJJG5mS+tkJpxS4MGDV5s" +
                                                    "mWS2tvUYtPuB5DyiDNfe7f/g3gXpUffw4kImR0aPPvYfG/VmTXgL84asbBo5" +
                                                    "5Qkpp0nDPoafB55/+MNV4ADZ5WvbkqNGY3rWsCyeB02TiSVYbPjl0vBXHw+P" +
                                                    "eJMmWcNQWR+lBsFmfsYKwItpP/MHzYWnKunnqv/sZ09uLs8rmMswvPLxnYhr" +
                                                    "dkzix1186WFoSoSwTqDjIVuwTOgxmGJ5yaT2E7WbwoHr4alPalf/tFHMX3v5" +
                                                    "EhKoZBINInzpY+BrFTOI7mCUsmSSB/gSlNJ1MlQ8QHWtQDFlqDw1zPA6Pivv" +
                                                    "Q0kO9+rFMV/5zLHuH0R7Tg/gFTAFh+OGkZXk2QlfatkkrAtJK2TXtcQfLVSG" +
                                                    "5WgFWSU3QlRT4gO02o0PKvG/bLQ4uDILDUIyuctGAo2LAIlv91spb1eL9sW/" +
                                                    "fvxy1E+gnC5j5fac7HbOE158hKbKZlx+hobUS2Obtx548PwZUYNL4PN1aEh8" +
                                                    "tMA3mBxS0qW3acLbUneVbmp5VHW5YlEq33LGF5ds8wt3+faYxURfHvpi5ufr" +
                                                    "zo7dEWPGv4l2JYMdEAAA");
}
