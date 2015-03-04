package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class AmbientOcclusionShader implements Shader {
    private Color bright;
    private Color dark;
    private int samples;
    private float maxDist;
    public AmbientOcclusionShader() { super();
                                      bright = Color.WHITE;
                                      dark = Color.BLACK;
                                      samples = 32;
                                      maxDist = Float.POSITIVE_INFINITY; }
    public AmbientOcclusionShader(Color c, float d) { this();
                                                      bright = c;
                                                      maxDist = d; }
    public boolean update(ParameterList pl, SunflowAPI api) { bright = pl.
                                                                         getColor(
                                                                           "bright",
                                                                           bright);
                                                              dark =
                                                                pl.
                                                                  getColor(
                                                                    "dark",
                                                                    dark);
                                                              samples =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    samples);
                                                              maxDist =
                                                                pl.
                                                                  getFloat(
                                                                    "maxdist",
                                                                    maxDist);
                                                              if (maxDist <=
                                                                    0)
                                                                  maxDist =
                                                                    Float.
                                                                      POSITIVE_INFINITY;
                                                              return true;
    }
    public Color getBrightColor(ShadingState state) { return bright;
    }
    public Color getRadiance(ShadingState state) { return state.
                                                     occlusion(
                                                       samples,
                                                       maxDist,
                                                       this.
                                                         getBrightColor(
                                                           state),
                                                       dark);
    }
    public void scatterPhoton(ShadingState state, Color power) {
        
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1169096380000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALUYa2wcxXnuzvbFZ6d+JLGd4NixY0Jxwi3Qpipx1GAcB45c" +
                                                   "YuMX4BDMeHfubuN9\nsTtnX0zKQ6gkBUGJSqVWhRChSHkQCipFoWoKoRBKiS" +
                                                   "pBpVIJibRVpLZSS6WqEk3V/ug3M7u3d3vn\ncx7qSTs7O/PN937NnfwcVTs2" +
                                                   "apedON1rESc+MDqMbYcoAxp2nDFYmpLPVtcOH91umGEUSqKwqlDU\nkJQdSc" +
                                                   "EUS6oiJbb25Wy03jK1vWnNpHGSo/E92kYX353JjSUI7z50qvmxI1WdYVSdRA" +
                                                   "3YMEyKqWoa\ngxrRHYoak3vwLJayVNWkpOrQviRaSoysPmAaDsUGdR5ED6NI" +
                                                   "EtVYMsNJUVfSIy4BccnCNtYlTl4a\n5mQBwzKbUKwaROnPk4OTG4pPAtvuuZ" +
                                                   "FSaECyhG1OgDicA5B6TV5qIW2JqFbk2MTX9h0+HkENk6hB\nNUYZMhkkoUBv" +
                                                   "EtXrRJ8mttOvKESZRE0GIcoosVWsqfOc6iRqdtS0gWnWJs4IcUxtlgE2O1mL" +
                                                   "2Jym\nt5hE9TKTyc7K1LTzOkqpRFO8r+qUhtMgdosvthB3G1sHAWMqMGansE" +
                                                   "y8I1UzqgEW7wyeyMvYsx0A\n4GhUJzRj5klVGRgWULOwpYaNtDRKbdVIA2i1" +
                                                   "mQUqFK1aECnTtYXlGZwmUxS1BeGGxRZA1XJFsCMU\nrQiCcUxgpVUBKxXYZ3" +
                                                   "3LFweOPf/Wrdy3qxQia4z/GBzqCBwaISliE0Mm4uDFbPy5xL3Z9jBCALwi\n" +
                                                   "ACxg+q89NZ78y9udAuaaMjBD03uITKfknQc7Rx663UQRxsYSy3RUZvwiyXk4" +
                                                   "DLs7fTkLorYlj5Ft\nxr3NMyPv3/voCfLXMIolUI1salkd/KhJNnVL1Yh9Oz" +
                                                   "GIjSlREqiWGMoA30+gKMyT4PJidSiVcghN\noCqNL9WY/BtUlAIUTEW1MFeN" +
                                                   "lOnNLUwzfJ6zEEJReNBGeGqR+PE3Rf1xyckaKc2ckxxblkw7nf+W\nTZtITg" +
                                                   "YrxJb69WmVGHRIlrWsAxKN8uU4cyWLot1SxtSJhGVsqIYppVUIXtm8QSGz7H" +
                                                   "21BHJMiua5\nUIilxWB4axAZd5gawE7JRy98uG9w+7cPCNdh7u7KT1Ec6MZd" +
                                                   "unFGNy7oxsvTRaEQJ7ec0Re2BEvM\nQExD9qu/fnT3nQ8c6I6AE1lzVaDGMI" +
                                                   "B2g6QuU4OyOeAHfoLnSBm8r+2lXfvjF49uEd4nLZyfy56u\n++iVc4f/Wd8b" +
                                                   "RuHyyZMJC+k7xtAMs4ybT4o9wXArh//vT+54/ZNzn33ZDzyKekryQelJFs/d" +
                                                   "QbPY\npkwUyJA++iMrGyJ3o4mDYVQFSQISI+cfck5HkEZRXPd5OZLJEk2iup" +
                                                   "Rp61hjW15ii9GMbc75K9xf\nGvl8GRinjjl6FzwNrufzN9tdYbGxRfgXs3ZA" +
                                                   "Cp6DLz5ec+PvTted5Wrx0nVDQUEcJVQEf5PvLGM2\nIbD+2feHv/u9z/fv4p" +
                                                   "4iXCVEoUpmpzVVzsGRdf4RiHoNMg8zZM+4oZuKmlLxtEaYx/234dqb3vjb\n" +
                                                   "M43CNBqseJbdsDgCf33lbejRc/f/q4OjCcms6vhi+GBCmmU+5n7bxnsZH7nH" +
                                                   "frP6B7/EL0BShETk\nqPOE5xbEJUNcjxLXey8f44G9m9jQDbg3LOD6ZWr8lL" +
                                                   "zvRLo7++Cvfsq5rsOFzUKhGXZgq09Yng1r\nmXZbg9F7B3YyAPfVMzvva9TO" +
                                                   "/AcwTgJGGWqrM2RD2OeKjOhCV0c/fefdlgc+jqDwNhTTTKxsw9z/\nUS04Hn" +
                                                   "EykH1y1pZbuW81zi1hIxcZcSWschXAP9pLvbLF9cqWsl7JhnUBlYbdnMO+Wy" +
                                                   "lqLUxtqg5V\nmLkCNBw2aitsIm1Vh2I0y6PqwhPdP/9g/MX9IhNdX6FTLDw1" +
                                                   "JT/y+z/MRL7zzrQ4FyzIAeCDHUf+\n9PqFkeXCa0XXsrakcSg8IzoXLnmDxS" +
                                                   "zYVYkCh35vfdfJh0fOuxw1F9ffQehR/7z3XXLd5qf/WKYo\nQDSbmHJymyu4" +
                                                   "bYINt/Ctm9mwSdhy46ImzxV8VTkV9byNNYV+spya3nAs+eHQC5zpBXN9GRME" +
                                                   "8My/\nNX7o4q/peY7HT7rsdFeutJpCI+2f/fons001r72oh1F0EjXKbqs/gb" +
                                                   "UsS22T0Jk6Xv8P14Gi/eIu\nU7RUffmi0h70gQKywXTvGwzmDJrN6wMZvp5p" +
                                                   "eyU8MTeWYsFYCiE+GeVHevh4XT4fRy1bncWs/Uc1\n07aaztDFQksUjLxDjC" +
                                                   "3mEDuLWW314t97l2F1NxvGKegM2zOXyc/9l8lPmzfx3mX4UVx+og7WLQ0S\n" +
                                                   "JvveYglKWymKwD0lwAa5TDZWwbPUZWPpAmxoHhs6zm116+BAgK5egW7OT6gi" +
                                                   "LkNldcs7RNEKsjS0\neqHrCk9B++/5R/0T+L3dYTdfDFKoDKZ1g0ZmiRZIAa" +
                                                   "uLusQd/ILmh9yTx18+RT9ev0kks96F00Xw\nYO+mw/Odm1596gp6w86AbEHU" +
                                                   "TbPX3BXJqB+E+R1SRHDJ3bP4UF9x3MaAn6xtjBVF75riStgHT7tr\n+/YrrY" +
                                                   "QdJSbkohK42rLuxQNrKQQbFe/+4QQn80iFOvAtNuyDJJG1FEgXwQiITpumRr" +
                                                   "DhO+I3L7VQ\n8I9csUa+As86VyPrLlkjoWKnXl3WqeHOz/71IBzNsxUkfo4N" +
                                                   "T1P0pTSht/HMKDqLxTqPvAaeuRoN\n3AhPr6uB3v+jBg5V0MBLbPghRXWggR" +
                                                   "E4x9z70sV//mrE/wY8cVf8+JWGRGXxF5OEkzlZQT0/ZsNx\nipY6MqYQZsMZ" +
                                                   "k7rZpiAyqmZNVfG1cuKS+yeI1fL3c3ZBaSv5p0/8OyUnP33ovi+Sv/03v2nm" +
                                                   "/0Gq\nS6IlqaymFTYUBfMayyYplQtVJ9oLi79Ol6sM4r8DyARiwpn+mYB/m6" +
                                                   "LGIDxogL0KwX4BLlUAxuqq\nmBUCnYWyCkBs+r7l2aqRX1BYYxUXjVWuSGdM" +
                                                   "M2uLCgf/89VL7lnx9+uUfM8ru9bknhp7lleMalnD\n8/MMTSyJouKGnS8QXQ" +
                                                   "ti83B9hF57deL0j27xCiC/gS3P+WW5yM1vFrsVfACKUvlr7aBuUX4RnX+z\n" +
                                                   "9Sebjx46H+YX6/8BWen57jMXAAA=");
}
