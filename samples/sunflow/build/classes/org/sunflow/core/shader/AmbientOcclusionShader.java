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
                                                       getBrightColor(
                                                         state),
                                                       dark);
    }
    public void scatterPhoton(ShadingState state, Color power) {
        
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcRxWfO/8523F8jtM4rnHsxHUi4pRdAkpRcEjruE7i" +
                                                    "cI0tO4mEI+rO7c3dbbz/ujtnX5yatpEqR5EaELhpWrUWoFSlJW0qRFQQqpQv" +
                                                    "0FblSxEC8YEW8YWKkg/5QKkoUN6b2b292zu7CR+wtHOzs2/en3nv/d4bX7lB" +
                                                    "GjyX7HJs43TOsLnCilw5ZexR+GmHecqR1J4J6nosM2JQzzsGazNa/2vJjz75" +
                                                    "Tr49ThqnyUZqWTanXLctb5J5tjHHMimSDFdHDWZ6nLSnTtE5qha4bqgp3eND" +
                                                    "KbKubCsnA6lABRVUUEEFVaigDodUsGk9swrmCO6gFvceJt8isRRpdDRUj5Nt" +
                                                    "lUwc6lLTZzMhLAAOTfh+AowSm4su2VqyXdpcZfBTu9Tlpx9s/0kdSU6TpG5N" +
                                                    "oToaKMFByDRpNZmZZq43nMmwzDTZYDGWmWKuTg19Qeg9TTo8PWdRXnBZ6ZBw" +
                                                    "seAwV8gMT65VQ9vcgsZtt2ReVmdGJnhryBo0B7Z2hrZKCw/iOhjYooNibpZq" +
                                                    "LNhSP6tbGU76ojtKNg58HQhga8JkPG+XRNVbFBZIh/SdQa2cOsVd3coBaYNd" +
                                                    "ACmcdK/KFM/aodoszbEZTrqidBPyE1A1i4PALZxsipIJTuCl7oiXyvxz4+i+" +
                                                    "C2esw1Zc6JxhmoH6N8Gm3simSZZlLrM0Jje2DqYu0s43zsUJAeJNEWJJ8/oj" +
                                                    "N++7u/f6W5LmczVoxtOnmMZntMvptnd7RnburUM1mhzb09H5FZaL8J/wvwwV" +
                                                    "Hci8zhJH/KgEH69P/uobj73MPoyTljHSqNlGwYQ42qDZpqMbzD3ELOZSzjJj" +
                                                    "pJlZmRHxfYwkYJ7SLSZXx7NZj/ExUm+IpUZbvMMRZYEFHlEC5rqVtYO5Q3le" +
                                                    "zIsOISQBD9kDTzORf+KXk7Sat02mUo1aumWrELuMulpeZZqtetR0DPCaV7Cy" +
                                                    "hj2veq6m2m6u9K7ZLlO9PM0wVx020zqz+LimGQUPTJ4SywrGmvN/kVJEW9vn" +
                                                    "YzFwQ08UBAzIn8O2AbQz2nLhwOjNV2feiZeSwj8lThSQq/hyFZSrSLlKbbkk" +
                                                    "FhPi7kD50uPgr1nIfMDE1p1T3zzy0Ln+Ogg1Z74eDjsOpP1gsa/UqGaPhPAw" +
                                                    "JkBQgxjt+uHJJeXjF++VMaqujuU1d5Prl+YfP/HoF+MkXgnKaCQsteD2CYTS" +
                                                    "EmQORJOxFt/k0gcfXb24aIdpWYHyPlpU78Rs74+6w7U1lgH8DNkPbqXXZt5Y" +
                                                    "HIiTeoAQgE1OIcwBkXqjMiqyfihAULSlAQzO2q5JDfwUwF4Lz7v2fLgi4qRN" +
                                                    "zDeAU9ZhGmyDJ+nnhfjFrxsdHO+QcYVejlghEPrgz68/c+3ZXXvj5WCeLCuP" +
                                                    "U4xLaNgQBskxlzFY/+Olie89dWPppIgQoLirloABHEcAKMBlcKxPvPXwH95/" +
                                                    "7/Jv46WoinGomIW0oWtF4LEjlAIwYgCUoe8HjlumndGzOk0bDIPzX8ntu6/9" +
                                                    "7UK79KYBK0Ew3P3ZDML1Ow+Qx9558B+9gk1MwzIWWh6SyQPYGHIedl16GvUo" +
                                                    "Pv6bLc+8SZ8HlAVk8/QFJsCKCMuIOHpVuGpQjErk224ctjpV38RCd7WPO30f" +
                                                    "d9b0MQ4DEWlxP3PxfRMnm8sBQjeh4uEpQXF3yc41mi5XN6EOzPmFSl3seH/2" +
                                                    "uQ9ekQkerWoRYnZu+fynyoXleFnpv6uq+pbvkeVfmLReHsGn8BeD5z/4oOm4" +
                                                    "IOG/Y8SvQVtLRchxMJC2raWWEHHwL1cXf/GjxSVpRkdl5RuFxu6V3/3718ql" +
                                                    "P71dA2ghU2zKhY73rOHf+3H4crV/pYO7xFv92md/ELutMnD957iRPvvnj4VO" +
                                                    "VfBYwx2R/dPqlee6R/Z/KPaHOIW7+4rVhQc603Dvl142/x7vb/xlnCSmSbvm" +
                                                    "t70nqFFANJiGVs8LemFojSu+V7ZtskcZKuFwTzQeysRGETL0A8yRGuctEVBs" +
                                                    "xVO+E54WP2FaogkTI2KSElv6xbgdh88HmJRwXH2OYk9NGtOunssLhPmKSDTp" +
                                                    "0EOV4jYHiRr81hA3icNRDnZTd/Yz+HUFk+C3Br/jPr+E34MIDntx+JoMsXs5" +
                                                    "qYMGfHUp3fCs96WsX0XKyUCKSYv3+1C7T7IshpgjozlWE2lEKyJ7DszNLat1" +
                                                    "zyIvL59dXsmMv7A77ifRfZw0c9v5gsHmmBFJnC0V7cgD4r4QBuz5l378On93" +
                                                    "11dlhg+unmTRjW+e/Wv3sf35h26jCemL2BRl+dIDV94+tEP7bpzUleK+6gpU" +
                                                    "uWmoMtpbXAZ3NutYRcz3lty5MYj5Ht+dPf9rkeitcp0wlcENC2teQNZZTjYl" +
                                                    "f4cnxoQYYw1QFGCjQ1oVnAwkWK2YTaRt22DUWqUyspLR+JA+eHb4Ru+4ZaNj" +
                                                    "lfG6pWa8wu0S79dMsDmzhlGP4gBY0ZZj/ICAC1FXRY7fmhXorkHfisHbtQJf" +
                                                    "HxFUT6yh5BIOZzlZB0pOgnEYZLemoQC0/fAovobK7QaX0FAIE6RPrqHmt3E4" +
                                                    "z6H6a5RD0E3kbe7nXiRO6udsPVOjvEJw1r7xYB/XVfUfFvlfAe3VlWTT5pXj" +
                                                    "vxc9fOnm3gzX52zBMMrrTtm80XFZVhd6N8sqJEHx6VoQKG9jEPpyIhS/KOmf" +
                                                    "5aQ9Sg8G4k852fPgvTIyhH45Kyf6PqA+EOH0B04Q4O2ifcX6q8j6WyRlYIod" +
                                                    "fPlbRTuPeCn+exVgW0H+/2pGu7py5OiZm/e8IICyQTPowgJyaUqRhLzJlPBx" +
                                                    "26rcAl6Nh3d+0vZa8/YA99tw6PCvLxHd+mp3+aOmw0VfvvCzzT/d9+LKe+Ka" +
                                                    "8V/rTP2mVhQAAA==");
}
