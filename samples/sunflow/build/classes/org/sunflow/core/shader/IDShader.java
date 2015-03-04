package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class IDShader implements Shader {
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
                                                   return new Color(state.
                                                                      getInstance(
                                                                        ).
                                                                      hashCode(
                                                                        )).
                                                     mul(
                                                       f); }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public IDShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXXWwURXju+l8KVwqFglBaKGD5uYUYTBBESynQesClLSSU" +
                                                    "QJnuzt0t7O0su3PtUawCiYHwQIwWBKN9MBBE+YuRoDEkfRIIvmCMxgfBN4nK" +
                                                    "Ay9ogorfzNzd3u1dizx4yc7Nznz//3vhASpxbLTYosb+qEFZkCRZcI+xIsj2" +
                                                    "W8QJdoRWhLHtEK3VwI7TDWe96twrgUeP345V+1FpD5qCTZMyzHRqOp3EoUY/" +
                                                    "0UIo4J62GSTuMFQd2oP7sZJguqGEdIetCqEJWagMNYXSIiggggIiKEIEpcWF" +
                                                    "AqSJxEzEWzkGNpmzD72BfCFUaqlcPIYac4lY2MbxFJmw0AAolPP3baCUQE7a" +
                                                    "qCGju9Q5T+ETi5Xh93ZVf1aEAj0ooJtdXBwVhGDApAdVxUm8j9hOi6YRrQdN" +
                                                    "NgnRuoitY0MfFHL3oBpHj5qYJWySMRI/TFjEFjxdy1WpXDc7oTJqZ9SL6MTQ" +
                                                    "0m8lEQNHQddprq5Sw/X8HBSs1EEwO4JVkkYp3qubGkNzvBgZHZteAwBALYsT" +
                                                    "FqMZVsUmhgNUI31nYDOqdDFbN6MAWkITwIWhmWMS5ba2sLoXR0kvQ3VeuLC8" +
                                                    "AqgKYQiOwlCtF0xQAi/N9Hgpyz8PNq8+fsDcaPqFzBpRDS5/OSDVe5A6SYTY" +
                                                    "xFSJRKxaFDqJp10/6kcIgGs9wBLm2usPX11SP3pTwjxXAGZL3x6isl71TN+k" +
                                                    "O7Nam1cWcTHKLero3Pk5movwD6duViUtyLxpGYr8Mpi+HO38evvBT8hvflTZ" +
                                                    "jkpVaiTiEEeTVRq3dIPYG4hJbMyI1o4qiKm1ivt2VAb7kG4SebolEnEIa0fF" +
                                                    "hjgqpeIdTBQBEtxEZbDXzQhN7y3MYmKftBBCZfCghfBUIvkT/wx1KzEaJwpW" +
                                                    "sambVIHYJdhWYwpRqeLguGWA15yEGTHogOLYqkLtaOZdpTZRnBjWiK20r+sS" +
                                                    "myCPLut/opvk+lQP+Hxg6lneRDcgRzZSA2B71eHE2raHl3pv+zOBn7IEQw3A" +
                                                    "KZjiFOScgpJTMM0J+XyCwVTOUfoRvLAX8hkqXVVz186O3UfnFkEAWQPFYEIO" +
                                                    "Ohe0SonRptJWN+nbRWlTIfLqPtpxJPjnuVdk5CljV+iC2Gj01MChbW8u8yN/" +
                                                    "bqnlasFRJUcP8wKZKYRN3hQrRDdw5P6jyyeHqJtsObU7VQPyMXkOz/U6wKYq" +
                                                    "0aAquuQXNeCrvdeHmvyoGAoDFEOGIXihztR7eeTk8qp0XeS6lIDCEWrHscGv" +
                                                    "0sWsksVsOuCeiMiYxJcaGSTcgR4BRUld/+Xo6avvL17pz66+gax+1kWYzOXJ" +
                                                    "rv+7bULg/KdT4XdPPDiyQzgfIOYVYtDE11bIbPAGWOytm/t+vHf3zHd+N2AY" +
                                                    "tLhEn6GrSaCxwOUCeW9A7eFubdpqxqmmR3TcZxAed38F5i+/+vvxaukoA07S" +
                                                    "fl7ydALu+Yy16ODtXX/UCzI+lfcdV3MXTBpgiku5xbbxfi5H8tC3s0/fwB9C" +
                                                    "WYRS5OiDRFQXJDRDwvRB4ZFmsS713C3jS4OVd5cUJ3WpN/HSKNYmviyUduPb" +
                                                    "57MhfWJfy9D0vJyWqcwNPHusViPa5JnDwyPalrPLZVrW5JbvNphOLn7/9zfB" +
                                                    "Uz/fKlBJKhi1lhqknxhZMhVxljnlYJPowm5SHDv/6TV2Z/FLkuWisSuBF/HG" +
                                                    "4V9ndq+J7X6GIjDHo7yX5PlNF25tWKC+40dFmfzPGyxykVZlmwGY2gQmIZMb" +
                                                    "lJ9UCjfXCwEmgzmmcIfOgGdCqu2If347xeLrVJmtfHnBEzd+YU9/2sf1eT4W" +
                                                    "qhKYW3hgpsGmZYN1yf+WcLtgs26cyOzgSwukZsLSoBWDF5vHmaJtPQ6NvT81" +
                                                    "eShDNff2fnD/ovSod0zxAJOjw8eeBI8P+7NmuXl541Q2jpznhJQTpWGfwM8H" +
                                                    "zz/84SrwA9nPa1pTQ0VDZqqwLJ4HjeOJJVis/+Xy0FcfDx3xp0yykqGyPkoN" +
                                                    "gs38jBUHL2f8zB80C55Ays+B/+xnX24uzy6YyzCm8kGdCDLbx/HjTr5sY2hC" +
                                                    "lLBOwOMhW7BM6HGYV3nJpPZTtRNBuwae2pR2tc8axfy1hy+9ApSMo0GUL30M" +
                                                    "fK1iBtEdjlGWSvIQX8JSuk6GivuprhUopgyVp4cZXsfr8j6J5BivXhoJlE8f" +
                                                    "2fqDaM+ZUbsC5t1IwjCykjw74Ustm0R0IWmF7LqW+KOFyrAcrSCr5EaIakp4" +
                                                    "OK32woNK/C8bLAGuzAKDkEztsoFA4yIA4tv9Vtrb1aJ98e+coBzqkyiny1i5" +
                                                    "PSe7nfOEF5+b6bKZkB+cverlkY7NBx6+eFbU4BL4UB0cFJ8n8LUlh5RM6W0c" +
                                                    "k1qaVunG5seTrlTMT+dbzvjikW1O4S7fFreY6MuDX0z/fPW5kbtizPgX7eEm" +
                                                    "iwcQAAA=");
}
