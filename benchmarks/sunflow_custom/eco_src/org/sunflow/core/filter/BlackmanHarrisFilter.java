package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class BlackmanHarrisFilter implements Filter {
    private float s;
    private float inv;
    public BlackmanHarrisFilter(float size) { super();
                                              s = size;
                                              inv = 1.0F / (s * 0.5F); }
    public float getSize() { return s; }
    public float get(float x, float y) { return this.bh1d(x * inv) * this.
                                           bh1d(
                                             y *
                                               inv); }
    private float bh1d(float x) { if (x < -1.0F || x > 1.0F) return 0.0F;
                                  x = (x + 1) * 0.5F;
                                  final double A0 = 0.35875;
                                  final double A1 = -0.48829;
                                  final double A2 = 0.14128;
                                  final double A3 = -0.01168;
                                  return (float) (A0 + A1 * Math.cos(2 * Math.
                                                                           PI *
                                                                       x) +
                                                    A2 *
                                                    Math.
                                                    cos(
                                                      4 *
                                                        Math.
                                                          PI *
                                                        x) +
                                                    A3 *
                                                    Math.
                                                    cos(
                                                      6 *
                                                        Math.
                                                          PI *
                                                        x)); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026716000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVYe2wcxRkf3/kRPyKfndh5EOLYMaEk4baoAbUxagiuTZxs" +
                                                   "iLETA07AGc/OnTfe\n211mZ89nE0EQUhKCCo3aSq1UQlRFSngj0SqtRGkQ0F" +
                                                   "IiJKhUkJAIoEhtJaBSVYmmav/oNzO7t3d7\nd4ZSS7s7t/M95/vNb771s5+j" +
                                                   "Bo+hNcRL83mXeunB8VHMPGoMWtjz9sKrKfJGQ/Po2V22k0B1OkqY\nBkftOv" +
                                                   "E0A3OsmYY28r2BAkObXMeaz1oOT9MCTx+ybgzs7dRvrDB456nznQ+dqe9JoA" +
                                                   "YdtWPbdjjm\npmMPWTTncZTSD+E81nxuWppuenxAR0up7ecGHdvj2ObefegB" +
                                                   "lNRRo0uETY569dC5Bs41FzOc06R7\nbVS6BQvLGOXYtKmxvegONDeXa0LYgd" +
                                                   "5YpTQYWSImJyAdGQFkva6Ytcq2IlU3eW7ipsOnn0qi9knU\nbtrjwhiBTDj4" +
                                                   "m0RtOZqbpszbbhjUmEQdNqXGOGUmtswF6XUSdXpm1sbcZ9Qbo55j5YVgp+e7" +
                                                   "lEmf\n4UsdtRGRE/MJd1hxjTImtYzwV0PGwllIuztKW6U7LN5Dgi0mBMYymN" +
                                                   "BQpX7WtKHiPXGNYo79u0AA\nVJtylM84RVf1NoYXqFPV0sJ2VhvnzLSzINrg" +
                                                   "+OCFo9U1jYq1djGZxVk6xdHKuNyomgKpZrkQQoWj\nrriYtARVWh2rUkl9Nn" +
                                                   "V/cfzcz165RWK73qDEEvG3gNLamNIYzVBGbUKV4hU//aORu/01CYRAuCsm\n" +
                                                   "rGS2X3N+n/7X3/YomauqyOyZPkQJnyK3n+wZu/82ByVFGEtcxzNF8csyl9th" +
                                                   "NJgZKLiwa7uLFsVk\nOpy8MPa7u488TT9NoJYR1Egcy88BjjqIk3NNi7LbqE" +
                                                   "0Z5tQYQc3UNgbl/AhqgrEOkFdv92QyHuUj\nqN6Srxod+RuWKAMmxBI1w9i0" +
                                                   "M044djGfkeOCixBqggt9C64GpP7kk6Ntac3z7YzlzGkeI5rDssXf\nxGFUA+" +
                                                   "uADO1WCwqcw/YOzJjpDcuXaQEkl6P92oyToxom2DZtR8uasHWJc71B8+L5/5" +
                                                   "kviAw65+rq\nBCXGtzYo0R2OZVA2Rc5efuvw0K5HjivYCKgHuQOlgNd04DUt" +
                                                   "vKaV13Q1r6iuTjpbLryrKkINZmE3\nA++1XTd+z86Dx/uSAB93rh4WUIj2QZ" +
                                                   "ZBSEPEGYy2/IhkRwK4W/nz/cfSV85uU7jTajNzVe3Wd567\nePofbRsTKFGd" +
                                                   "NkWqQNwtwsyo4NoiHfbHN1o1+387sful9y5++I1oy3HUX8EElZpiJ/fFi8Ic" +
                                                   "Qg3g\nxsj8mVXtyTvRxMkEqgd6AEqU8QPbrI37KNvRAyE7ilyadNSacVgOW2" +
                                                   "IqpLQWPsOcueiNREtKjpdB\ncVoFxHvgWhJgXj7FbJcr7t0KXaLasSwk+155" +
                                                   "uPGb77/c+oZclpCo20uOwnHK1bbviMCyl1EK7z/8\nyegPf/z5sf0SKQFUOJ" +
                                                   "yP/rRlkgKobIhUYL9bwDmikP377JxjmBkTT1tUIO4/7dfc8MvPHkup0ljw\n" +
                                                   "Jqzs5i83EL1fdSs6cvHef66VZuqIOG+iNCIxlc2yyPJ2xvC8iKPw0B+v/unv" +
                                                   "8RNAh0BBnrlAJavU\nFTfBytJ+hZk54L28LOPlo32/eXPfk8cU9K9bpCkp1Z" +
                                                   "oiD3708Wzy8VenlV6c+2PCJ9ee+fNLl8eW\nq2VSB+T6ijOqVEcdkhIA7a4o" +
                                                   "SO9iHqT065t6n31g7FIQUWc51Q9BO/SX+dfotTd//5MqHATwcTCX\n7jSJz4" +
                                                   "3ynhaAlGuI5NxWceuDaDbXWKcqXdAUOfx0ts+/7w+/ln5bcWk7VQrX3dhVCa" +
                                                   "fEbb1IekWc\n5XZgbwbktly4/UDKuvBvsDgJFgl0H94eBkRbKAN7IN3Q9MGr" +
                                                   "r3UffDeJEsOoBRI1hrHkCdQMG5R6\nM8DRBXfbLXIPpubELkzJlJFchNXBAh" +
                                                   "RKfiW8RbEyLHqoiGGmpjef09/a84RcgJoEWQVGMTsLr+w7\ndeVtfknaiZhK" +
                                                   "aPcWKg8g6Dsj3W+/l+9ofPHJXAI1TaIUCTrjCWz5gg8moZHzwnYZuuey+fKm" +
                                                   "THUg\nA0UmXhPHcYnbOEdGoIOxkBbjthgttonVXgVXY0CLjXFarENysEOq9M" +
                                                   "v7tUUSa3KZmceiW4aNL6a2\nKBYV9wFxG1GF/G7Ngg/+76GMittOjpKmna/m" +
                                                   "845FfMr0N5SAS4W9gqMVFZ2BagLE1ri6VosqueDY\nXX9vO4pfvycRbNybOG" +
                                                   "Ddca+3aJ5aJa6SwlJZf7BbNuURbk489cx5/u6mrYpVNtbGfFxx49bTCz1b\n" +
                                                   "X3j0a3QFPbHc4qY78lfdkZwx30zI7wYFw4rvjXKlgXLwtUA8PrP3lkFwXfnJ" +
                                                   "vBqupUHdl1Y9maPC\nVadLssic9HsQ4JqlfBzOK4maCDD4y0AaspL8caA88g" +
                                                   "1wdQSRd3zlyBOK2Yrw3SJFc4vkIHv5Q4B6\nyCEW/+zXjL8t7Im6gvi7asa/" +
                                                   "MxZZcNhHoc8tEvq8uHGO6qdnbjBisftfNXagmOXVmnTRpays+NBX\nH6dE/+" +
                                                   "D+A1/of/qXbDeLH5Ct8BWX8S2rlCBLxo0uoxlTRt6q6NKVjyPVSEJ9PnDxKS" +
                                                   "8GMuQHlfzD\nHKXi8rAI4lEqdpSj1hIxQGkwKhV6BAoPQmJ4wg0pKyVPX3FQ" +
                                                   "pNVBUShbMbEy68s4RP7vJdznvvrv\nyxS567n96wqP7v2BJI8GYuGFBWGmRU" +
                                                   "dNqs0uckVvTWuhrXfQiy9MvPz8d0IulO3F8kLE0GVQHFCz\niyAA+Kl6bzuU" +
                                                   "c7nsRhd+teIXN589dSkhu+v/Ar44iEoyEwAA");
}
