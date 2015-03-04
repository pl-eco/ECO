package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class MitchellFilter implements Filter {
    public float getSize() { return 4.0F; }
    public float get(float x, float y) { return mitchell(x) * mitchell(y);
    }
    private float mitchell(float x) { final float B = 1 / 3.0F;
                                      final float C = 1 / 3.0F;
                                      final float SIXTH = 1 / 6.0F;
                                      x = Math.abs(x);
                                      float x2 = x * x;
                                      if (x > 1.0F) return ((-B - 6 * C) *
                                                              x *
                                                              x2 +
                                                              (6 *
                                                                 B +
                                                                 30 *
                                                                 C) *
                                                              x2 +
                                                              (-12 *
                                                                 B -
                                                                 48 *
                                                                 C) *
                                                              x +
                                                              (8 *
                                                                 B +
                                                                 24 *
                                                                 C)) * SIXTH;
                                      return ((12 - 9 * B - 6 * C) * x * x2 +
                                                (-18 +
                                                   12 *
                                                   B +
                                                   6 *
                                                   C) *
                                                x2 +
                                                (6 -
                                                   2 *
                                                   B)) * SIXTH; }
    public MitchellFilter() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXW2xURRie3d5L6bblViuUthS0FM6BGEyweClrC4UFmhYw" +
                                                    "lkiZnp3dPe25cc5suxSrQGJKeCBGC4LRPhgIotxiJGgMSZ8Egi8Yo/FB8E2i" +
                                                    "8sALmqDiPzO7e3bPbqs+uMmZPWfmv//zf/PPuXuoyLFRq2Vq+6KaSSWSoNKg" +
                                                    "tkai+yziSJtCa7qx7ZBwUMOOsx3m+pWmS4EHD9+MVflRcR+agw3DpJiqpuH0" +
                                                    "EMfUhkk4hALubIdGdIeiqtAgHsZynKqaHFId2hZCszJYKWoOpUyQwQQZTJC5" +
                                                    "CXK7SwVMs4kR14OMAxvU2YteQ74QKrYUZh5FjdlCLGxjPSmmm3sAEkrZ905w" +
                                                    "ijMnbNSQ9l34nOPwsVZ54p3dVZ8UoEAfCqhGLzNHASMoKOlDFTrRB4jttIfD" +
                                                    "JNyHqg1Cwr3EVrGmjnK7+1CNo0YNTOM2SQeJTcYtYnOdbuQqFOabHVeoaafd" +
                                                    "i6hEC6e+iiIajoKv811fhYedbB4cLFfBMDuCFZJiKRxSjTBFi70caR+bNwMB" +
                                                    "sJbohMbMtKpCA8MEqhG507ARlXuprRpRIC0y46CForpphbJYW1gZwlHST1Gt" +
                                                    "l65bLAFVGQ8EY6FonpeMS4Is1XmylJGfe1vXHd1vbDT83OYwUTRmfykw1XuY" +
                                                    "ekiE2MRQiGCsWB46judfPexHCIjneYgFzZVX77+won7quqB5PA/NtoFBotB+" +
                                                    "5dRA5a2FwZa1BcyMUst0VJb8LM/59u9OrrQlLKi8+WmJbFFKLU71fPnygY/I" +
                                                    "L35U3oWKFVOL67CPqhVTt1SN2BuIQWxMSbgLlREjHOTrXagE3kOqQcTstkjE" +
                                                    "IbQLFWp8qtjk3xCiCIhgISqBd9WImKl3C9MYf09YCKESeNBKeIqQ+PF/ivrk" +
                                                    "mKkTGSvYUA1Thr1LsK3EZKKYsoN1S4OsOXEjopkjsmMrsmlH09+KaRMZ1MPW" +
                                                    "kbeoVIkRTevknxLbY9b/Kj3BfKsa8fkg7Au9Ra9BvWw0tTCx+5WJ+PqO+xf6" +
                                                    "b/rTRZCMCkXLQJ+U1CcxfZLQJ2XrQz4fVzOX6RWZhbwMQYUD9lW09L6yac/h" +
                                                    "pgLYUtZIIQSVkTaBh0ljOhQz6MJAFwc7BfZi7Qe7xqXfzzwv9qI8PWbn5UZT" +
                                                    "J0YO7nx9lR/5s8GXOQdT5Yy9m0FmGhqbvUWXT25g/O6Di8fHTLf8stA8iQq5" +
                                                    "nKyqm7xpsE2FhAEnXfHLG/Dl/qtjzX5UCFAB8EgxbGdAnnqvjqzqbkshJfOl" +
                                                    "CByOmLaONbaUgrdyGrPNEXeG749KNtSIrcIS6DGQg2zn51MnL7/butaficeB" +
                                                    "jBOul1BR3dVu/rfbhMD8Dye63z52b3wXTz5QLMmnoJmNQah1yAZE7I3re7+/" +
                                                    "c/vUN353w1A49OIDmqokQMYyVwsggQZoxNLavMPQzbAaUfGARti++yOwdPXl" +
                                                    "X49WiURpMJPK84p/FuDOP7YeHbi5+7d6LsansJPI9dwlEwGY40put228j9mR" +
                                                    "OPj1opPX8PsAlABOjjpKON4g7hnioZd4Rlr4uNKztooNDVbOWoLP1Ca/+Ecj" +
                                                    "H5vZ8ISIG3t9MpPSx9/nUbQgp7JFKbMAL5ru8OEH56lDE5PhbadXi7KsyQb0" +
                                                    "DuhXzn/751fSiR9v5MGTMmpaKzUyTLQMmwqYyiw42MLPZbcojpz9+Aq91fqM" +
                                                    "ULl8eiTwMl479HPd9udie/4DCCz2OO8VeXbLuRsblilv+VFBuv5zWo1sprbM" +
                                                    "MIBSm0BvZLCAsplynuZ6bkA1hIM9qBae4uRBxP/Z6hyLjXNFtbLhqRn2zYsz" +
                                                    "rHWyoZ2ikiihvbAhIQEtM7TEtqrDKT2cbCPksZo7Q+/dPS+S4e05PMTk8MSR" +
                                                    "R9LRCX9GY7YkpzfK5BHNGTdztojJI/j54PmLPcwHNiEO55pgskNoSLcIlsW2" +
                                                    "cONMZnEVnT9dHPviw7FxfzImaynDNhPT3FLjE89mJ2guPOXJBJX/6wT5uUQ/" +
                                                    "+9zMB066Y4ZUvcSGHooKIFWc4x/Nq2GTdfBUJs2rzGveNIgBm8Ky1WGIZMJj" +
                                                    "lc+FFGH2nhnMHmBDH0WlerJRmMb2BEWV2b0Eg9HanDuK6KuVC5OB0gWTO77j" +
                                                    "p2O69y2DBjQS17SMGsust2LLJhGVm1UmDj2L/0XzoaDobyi7hbAXbnBE0A/C" +
                                                    "vc5LT1Eh+8sk0ymalUEGAU2+ZRIBFhYAEXvda6UwuYqfHuziIYkuO4GyQN7K" +
                                                    "hvzM05QVLb//pVArLm6A/crFyU1b999/+jSHwCK4OY6O8vsCXH9Ej5BGvsZp" +
                                                    "paVkFW9seVh5qWxpqmayugePbYvzH7IdukX5sTj62YJP152ZvM1P+b8BK8+k" +
                                                    "aJgPAAA=");
}
