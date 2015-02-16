package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class DiagonalBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = new int[2 *
                                                                                nbw *
                                                                                nbh];
                                                       int x =
                                                         0;
                                                       int y =
                                                         0;
                                                       int nx =
                                                         1;
                                                       int ny =
                                                         0;
                                                       for (int i =
                                                              0;
                                                            i <
                                                              nbw *
                                                              nbh;
                                                            i++) {
                                                           coords[2 *
                                                                    i +
                                                                    0] =
                                                             x;
                                                           coords[2 *
                                                                    i +
                                                                    1] =
                                                             y;
                                                           do  {
                                                               if (y ==
                                                                     ny) {
                                                                   y =
                                                                     0;
                                                                   x =
                                                                     nx;
                                                                   ny++;
                                                                   nx++;
                                                               }
                                                               else {
                                                                   x--;
                                                                   y++;
                                                               }
                                                           }while((y >=
                                                                     nbh ||
                                                                     x >=
                                                                     nbw) &&
                                                                    i !=
                                                                    nbw *
                                                                    nbh -
                                                                    1); 
                                                       }
                                                       return coords;
    }
    public DiagonalBucketOrder() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1159026716000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YW2wUVRg+O73XQi9cRShQClqKO16iBuut1EKLq20okLhG" +
                                                    "6tnZs9uhszPDzNl2qdYLiYHwQIxWRYN9MCCCCMZI0BgTXlQIxkRiND4I6otE" +
                                                    "JJEH0Xj//3N2d2ZntzW+2GTOnjnn/8/5r9//T49cIhWuQ9pty9ieNCweZhke" +
                                                    "3mrcEubbbeaG10du6aeOy+JdBnXdjbA2qLW8VX/l92eGGhRSGSWzqGlanHLd" +
                                                    "Mt0NzLWMERaPkHpvtdtgKZeThshWOkLVNNcNNaK7vCNCrvKxctIayYmggggq" +
                                                    "iKAKEdROjwqYZjAznepCDmpydxt5nIQipNLWUDxOlhYeYlOHprLH9AsN4IRq" +
                                                    "fN8MSgnmjEOW5HWXOhcp/Hy7OvHiloa3y0h9lNTr5gCKo4EQHC6JkroUS8WY" +
                                                    "43bG4yweJY0mY/EB5ujU0MeE3FHS5OpJk/K0w/JGwsW0zRxxp2e5Og11c9Ia" +
                                                    "t5y8egmdGfHcW0XCoEnQda6nq9RwLa6DgrU6COYkqMZyLOXDuhnnZHGQI69j" +
                                                    "631AAKxVKcaHrPxV5SaFBdIkfWdQM6kOcEc3k0BaYaXhFk4WTHko2tqm2jBN" +
                                                    "skFO5gfp+uUWUNUIQyALJ3OCZOIk8NKCgJd8/rn0wB17HjV7TEXIHGeagfJX" +
                                                    "A1NzgGkDSzCHmRqTjHUrIy/QuR/sUggB4jkBYklz4rHL96xqPnlK0lxTgqYv" +
                                                    "tpVpfFDbH5v52cKuttVlKEa1bbk6Or9AcxH+/dmdjowNmTc3fyJuhnObJzd8" +
                                                    "9OCTh9lFhdT2kkrNMtIpiKNGzUrZusGcdcxkDuUs3ktqmBnvEvu9pArmEd1k" +
                                                    "crUvkXAZ7yXlhliqtMQ7mCgBR6CJqmCumwkrN7cpHxLzjE0IqYKH3AZPBZF/" +
                                                    "4peTreomF8JdpRo1ddNSIXgZdbQhlWnWYAysO5SizrCrammXWynVTZsJwxpV" +
                                                    "XUdTLSeZf9csh6mxtDbMuHqvTpOWSY014rXPiTMnjDFn/6+3ZVD3htFQCNyy" +
                                                    "MAgKBuRTj2UA7aA2kV7Tffno4BklnyRZq3HSDpeGs5eG8dKwvDRc4lISCom7" +
                                                    "ZuPl0v3gvGGAAQDIuraBh9c/squlDOLOHi0HyyNpC2idlahbs7o8rOgViKhB" +
                                                    "wM5/9aGd4V8P3i0DVp0a2Etyk5N7R5/a/MQNClEKERo1hKVaZO9HXM3jZ2sw" +
                                                    "M0udW7/zwpVjL4xbXo4WQH4WOoo5MfVbgr5wLI3FAUy941cuoccHPxhvVUg5" +
                                                    "4AlgKKcQ8wBPzcE7CiCgIwenqEsFKJywnBQ1cCuHgbV8yLFGvRURJDNxaJLx" +
                                                    "gg4MCCiQeO17J186/nL7asUP2vW+MjjAuISARs//Gx3GYP3rvf3PPX9p50PC" +
                                                    "+UCxrNQFrTh2ASCAN8BiT5/a9tX5c/s/V7yA4VAZ0zFD1zJwxgrvFoALAyAL" +
                                                    "3dq6yUxZcT2h05jBMO7+qF9+4/Ef9zRIRxmwkvPzqn8/wFu/eg158syWX5rF" +
                                                    "MSENy5WnuUcmDTDLO7nTceh2lCPz1NlFL31MXwE0BQRz9TEmQIkIzYgwfVh4" +
                                                    "pE2M1wf2bsBhiV20lxEr87Nv4mWpGFtxuFbaDafX+SlDYj6Hk4VF6e3LZ7Ty" +
                                                    "oqnKlCix+3dMTMb7Dtwoc7OpEPq7obN584s/Pwnv/eZ0CWSp4ZZ9vcFGmFEg" +
                                                    "GFxZgAn3iwruZcbuQ2+c4J+13y6vXDk1HAQZP97xw4KNdw098h+QYHFA+eCR" +
                                                    "h+4/cnrdCu1ZhZTlQaCoKSlk6vCbAS51GHRRJhoUV2qFr5uFAI1gDnxICzyV" +
                                                    "2ZIlfnF3lo3jbJmyONwcCB5F2FMBe7ZN0ws7egrK80i2f1DHm84P77vwprRt" +
                                                    "sNkIELNdE7v/Du+ZUHwd2bKipsjPI7syIfIMqeLf8BeC5y98UDVckFW5qSvb" +
                                                    "GizJ9wa2jRG5dDqxxBVrvz82/v7r4zuVbO6s5qQMekic9oiFe6dJtD4cOjlp" +
                                                    "TDIuc2GAbUsjwMLdy6c2pUh0aZnJ15Z9+sTksm/BMlFSrbvQnHc6yRI9n4/n" +
                                                    "pyPnL56dseioAPzyGHVlPASb5eJeuKDFFRrU2eKnJ69eKJ9a04TCWrzGV3V/" +
                                                    "6zNiO777VXi3KFtKREeAP6oe2beg666Lgt8rYMi9OFPcjoCJPN6bDqd+Vloq" +
                                                    "P1RIVZQ0aNmPo83USGMtiYIR3NwXE3xAFewXNveyk+3I5+bCYHj6rg2WTn+W" +
                                                    "lvOC/JxpZ0JE5F+0NOAqAnA5HKhDh5SBsmUwMyl70R4cBuxMUaJmEVnWDhQf" +
                                                    "sNYyGZah3J5srHQrnP8cg81MkZ/x/U4ZBAOloMEf7slp9nQcwKQVGgoi5YYg" +
                                                    "Wly6bnanbC4q3di789654+DkOVG4M6REzQItS7SPePb8om9X+b2lHZ2sr543" +
                                                    "uelLmR+5b6Ia+DBJpA3D7yvfvNJ2WEIXutTkPIc/UH3mTdHXgq/kREhtSXr8" +
                                                    "3g/Sc1KOP36yEU6u8pFxUpWd+Ym2AxgBEU7H7JxjGzyny5jNkIK6bhdWeX8D" +
                                                    "hYks/i+Qq1Fp+Z+BQe3Y5PoHHr186wFR8MCHdGxMfEcCZsi2MF/nlk55Wu6s" +
                                                    "yp6232e+VbM8B6kFDaNfNpwb/wDeXFQ6hREAAA==");
}
