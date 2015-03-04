package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class TriangleFilter implements Filter {
    private float s;
    private float inv;
    public TriangleFilter(float size) { super();
                                        s = size;
                                        inv = 1.0F / (s * 0.5F); }
    public float getSize() { return s; }
    public float get(float x, float y) { return (1.0F - Math.abs(x * inv)) *
                                           (1.0F -
                                              Math.
                                              abs(
                                                y *
                                                  inv)); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026718000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Ye2gcRRif3OXRPGouaZs+bJsmjVX7uFWooo2oNSY27dXG" +
                                                   "vNS0mk5m5y7b7O2u\nu7OXSyxaEWxVfBQVFLQWKbS+BZUq+Kj4tggqqCBYlY" +
                                                   "IKPkAEregffjOze3u7d4mPwO3O7nyP+eb7\nfb/5Nk/9hKocGy0lTpJNWdRJ" +
                                                   "dg30YduhapeOHWcQXo2St6tq+w5vMcwYqkihmKYy1JgijqJihhVN\nVXov78" +
                                                   "zbaI1l6lMZ3WRJmmfJXfp5nr3NqfNKDF594GjzLYcqW2OoKoUasWGYDDPNNL" +
                                                   "p1mnUYSqR2\n4RxWXKbpSkpzWGcKzaWGm+0yDYdhgzk3oJtQPIWqLcJtMtSW" +
                                                   "8p0r4FyxsI2zinCv9Am3YGGeTRnW\nDKpuLLgDzbVhTVi2p9dfKg1G5vDJYQ" +
                                                   "hHrACiXlGIWkZbEqoVPzJ8/u6Dj8dR4whq1IwBboxAJAz8\njaCGLM2OUdvZ" +
                                                   "qKpUHUFNBqXqALU1rGvTwusIana0jIGZa1OnnzqmnuOCzY5rUVv49F+mUAPh" +
                                                   "Mdku\nYaZd2KO0RnXVf6pK6zgDYbcEYctwe/h7CLBOg4XZaUyor1I5oRmQ8d" +
                                                   "aoRiHGji0gAKo1WcrGzYKr\nSgPDC9Qsc6ljI6MMMFszMiBaZbrghaElMxrl" +
                                                   "e21hMoEzdJShRVG5PjkFUrViI7gKQwuiYsISZGlJ\nJEtF+VnT8tu+Iw+/dq" +
                                                   "nAdqVKic7XXwdKyyNK/TRNbWoQKhVPucn7e691l8YQAuEFEWEps/GMo0Op\n" +
                                                   "719vlTKnl5HZNraLEjZKrtzf2n/jFSaK82XMsUxH48kPRS7Koc+b6cxbULUt" +
                                                   "BYt8MulPHut/59o9\nT9AfYqiuF1UTU3ezgKMmYmYtTaf2FdSgNmZU7UW11F" +
                                                   "C7xHwvqoFxCiAv325Lpx3KelGlLl5Vm+IZ\ntigNJvgW1cJYM9KmP7YwGxfj" +
                                                   "vIUQqoEfWge/KiT/xJ2hC5OK4xpp3ZxUHJsopp0pPBPTpgpYB2Qo\ng1ABRk" +
                                                   "anPeIxySFkMTSkjJtZqmCCDc0wlYwGRUvMdSrN8fv/NZznq26erKjgNBgtZx" +
                                                   "0qYZOpq9Qe\nJYdPfrC7e8vt+yRUOLy9eBlaBf6Snr8k95eU/pJhf6iiQriZ" +
                                                   "z/3KnMGOT0DtAss1nD1w3ead+9rj\nABZrshK2i4u2Q2TeYrqJ2RUUeK/gQg" +
                                                   "IoW/TY9r3JU4cvkShTZubhstr1Hz19/OCvDatjKFaeJHmQ\nQNN13EwfZ9YC" +
                                                   "+XVEy6qc/Z/v2Pr8Z8e/PCsoMIY6Suq+VJPXbXs0HbZJqApMGJg/tLgxfjUa" +
                                                   "3h9D\nlUAGQIBi/cAty6M+QvXb6XMhj6UmherTpp3FOp/yCayOjdvmZPBG4C" +
                                                   "QhxvMgOfUc0IvhN8dDuLjz\n2QUWv7ZIXPFsR6IQXHvq1upzPn+l/m2xLT4t" +
                                                   "NxYdfAOUySJvCsAyaFMK7798sO++B37au10gxYMK\ng9PQHdM1kgeVVYEKVL" +
                                                   "cODMMT2TFkZE1VS2t4TKcccX81nnHuiz/enZCp0eGNn9m1/2wgeL/4MrTn\n" +
                                                   "+PW/LxdmKgg/XYIwAjEZzbzA8kbbxlN8HflbPln20Lv4ESA/IBxHm6aCQyoK" +
                                                   "RbCouDuxtSywXE6k\n8eRt7a++N/ToXgn9s2dpQYq1RsnNX309Eb/njTGpF2" +
                                                   "X6iPD+5Ye+ff5k/3y5TfI4XFlyIhXryCNR\nAKDR4glpm82DkH5rTdtTN/Wf" +
                                                   "8FbUHCb2bmh+vpt6k5550V3flGEfgI+JmXCnCHyuFtckB6TYQyTm\nNvBLO6" +
                                                   "xm7Qz7VKbnGSW7n8i0uze8/7LwW4+Lm6diuG7Flgw4wS8redALoyy3CTvjIL" +
                                                   "f+2JU7Evqx\nP8HiCFgk0Gs422yg2HwI7J50Vc0Xb7zZsvPjOIr1oDoIVO3B" +
                                                   "gidQLRQodcaBnfPWJZeKGkxM8ipM\niJCR2IQl3gbki55izqxY6eEdU8Awo2" +
                                                   "Nrj6Q+2PaI2IAZCbIMjCJ2pl8bOnDqQ3ZC2AmYimu35UuP\nHugyA90LPss1" +
                                                   "VT/3aDaGakZQgnh98DDWXc4HI9C2OX5zDL1yaD7cgsl+o7PAxEujOC5yG+XI" +
                                                   "AHQw\n5tJ83BChxQafFqs9WqyO0mIFEoNNQqVDXM8skFiNZWs5zHtjKHw+tV" +
                                                   "6yKL928kuvTOTFMya8678v\npY9fNjMU14xcOZ9XzeJThL+qCFxy2QsZWljS" +
                                                   "E8gmgJfGspkaUsEFe6/5peE2/NZ1Ma9wz2eAddNa\np9Mc1SM4XhbqD7aKFj" +
                                                   "zAzR2PP3mUfbxmg2SV1TNjPqq4esPB6dYNz975P7qC1khsUdNNudOvio9r\n" +
                                                   "78XEV4KEYcnXRVipMwy+OliPaxuDIQiuCJ/MS+A318v73LInc5C48nRJZpkT" +
                                                   "fncCXDOUDcB5JVAT\nAAb/E0h9VhIPO8IrXwW/Jm/lTf965TGJiAJ81wvR7C" +
                                                   "wxiM59F6AeYoisf+Lfrh/K9LRwi8vP+EUl\nH8XyQ46kvrhxx2+pT/8QzVrh" +
                                                   "Y6sevnjSrq4X00vRuNqyaVoTS66XZGOJW65cicm2m/HPXj4Qi3Wl\n/BRDia" +
                                                   "g8Q5X8Viy2m6H6IjHIsTcqFroZtg2E+HCP5Rd8QpxdnGaTkmbzob3iO7MyVI" +
                                                   "Hi/xR+lbjy\nPxWj5Jqnt6/I3zl4ryi9KqLj6WnxSQpf2LJJLVRa24zWfFsf" +
                                                   "oeeeHX7lmQt9JhGH8/x8wG8hIHbK\n2VlyD9VdvjPszlpM9HLTLy184aLDB0" +
                                                   "7ERG/6N9oWjJxeEgAA");
}
