package org.sunflow.math;
public class PerlinVector {
    private static final float P1x = 0.34F;
    private static final float P1y = 0.66F;
    private static final float P1z = 0.237F;
    private static final float P2x = 0.011F;
    private static final float P2y = 0.845F;
    private static final float P2z = 0.037F;
    private static final float P3x = 0.34F;
    private static final float P3y = 0.12F;
    private static final float P3z = 0.9F;
    public static final Vector3 snoise(float x) { return new Vector3(PerlinScalar.
                                                                       snoise(
                                                                         x +
                                                                           P1x),
                                                                     PerlinScalar.
                                                                       snoise(
                                                                         x +
                                                                           P2x),
                                                                     PerlinScalar.
                                                                       snoise(
                                                                         x +
                                                                           P3x));
    }
    public static final Vector3 snoise(float x,
                                       float y) {
        return new Vector3(
          PerlinScalar.
            snoise(
              x +
                P1x,
              y +
                P1y),
          PerlinScalar.
            snoise(
              x +
                P2x,
              y +
                P2y),
          PerlinScalar.
            snoise(
              x +
                P3x,
              y +
                P3y));
    }
    public static final Vector3 snoise(float x,
                                       float y,
                                       float z) {
        return new Vector3(
          PerlinScalar.
            snoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z),
          PerlinScalar.
            snoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z),
          PerlinScalar.
            snoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z));
    }
    public static final Vector3 snoise(float x,
                                       float y,
                                       float z,
                                       float t) {
        return new Vector3(
          PerlinScalar.
            snoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              t),
          PerlinScalar.
            snoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              t),
          PerlinScalar.
            snoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              t));
    }
    public static final Vector3 snoise(Point2 p) {
        return snoise(
                 p.
                   x,
                 p.
                   y);
    }
    public static final Vector3 snoise(Point3 p) {
        return snoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z);
    }
    public static final Vector3 snoise(Point3 p,
                                       float t) {
        return snoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 t);
    }
    public static final Vector3 noise(float x) {
        return new Vector3(
          PerlinScalar.
            noise(
              x +
                P1x),
          PerlinScalar.
            noise(
              x +
                P2x),
          PerlinScalar.
            noise(
              x +
                P3x));
    }
    public static final Vector3 noise(float x,
                                      float y) {
        return new Vector3(
          PerlinScalar.
            noise(
              x +
                P1x,
              y +
                P1y),
          PerlinScalar.
            noise(
              x +
                P2x,
              y +
                P2y),
          PerlinScalar.
            noise(
              x +
                P3x,
              y +
                P3y));
    }
    public static final Vector3 noise(float x,
                                      float y,
                                      float z) {
        return new Vector3(
          PerlinScalar.
            noise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z),
          PerlinScalar.
            noise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z),
          PerlinScalar.
            noise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z));
    }
    public static final Vector3 noise(float x,
                                      float y,
                                      float z,
                                      float t) {
        return new Vector3(
          PerlinScalar.
            noise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              t),
          PerlinScalar.
            noise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              t),
          PerlinScalar.
            noise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              t));
    }
    public static final Vector3 noise(Point2 p) {
        return noise(
                 p.
                   x,
                 p.
                   y);
    }
    public static final Vector3 noise(Point3 p) {
        return noise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z);
    }
    public static final Vector3 noise(Point3 p,
                                      float t) {
        return noise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 t);
    }
    public static final Vector3 pnoise(float x,
                                       float period) {
        return new Vector3(
          PerlinScalar.
            pnoise(
              x +
                P1x,
              period),
          PerlinScalar.
            pnoise(
              x +
                P2x,
              period),
          PerlinScalar.
            pnoise(
              x +
                P3x,
              period));
    }
    public static final Vector3 pnoise(float x,
                                       float y,
                                       float w,
                                       float h) {
        return new Vector3(
          PerlinScalar.
            pnoise(
              x +
                P1x,
              y +
                P1y,
              w,
              h),
          PerlinScalar.
            pnoise(
              x +
                P2x,
              y +
                P2y,
              w,
              h),
          PerlinScalar.
            pnoise(
              x +
                P3x,
              y +
                P3y,
              w,
              h));
    }
    public static final Vector3 pnoise(float x,
                                       float y,
                                       float z,
                                       float w,
                                       float h,
                                       float d) {
        return new Vector3(
          PerlinScalar.
            pnoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              w,
              h,
              d),
          PerlinScalar.
            pnoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              w,
              h,
              d),
          PerlinScalar.
            pnoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              w,
              h,
              d));
    }
    public static final Vector3 pnoise(float x,
                                       float y,
                                       float z,
                                       float t,
                                       float w,
                                       float h,
                                       float d,
                                       float p) {
        return new Vector3(
          PerlinScalar.
            pnoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              t,
              w,
              h,
              d,
              p),
          PerlinScalar.
            pnoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              t,
              w,
              h,
              d,
              p),
          PerlinScalar.
            pnoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              t,
              w,
              h,
              d,
              p));
    }
    public static final Vector3 pnoise(Point2 p,
                                       float periodx,
                                       float periody) {
        return pnoise(
                 p.
                   x,
                 p.
                   y,
                 periodx,
                 periody);
    }
    public static final Vector3 pnoise(Point3 p,
                                       Vector3 period) {
        return pnoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 period.
                   x,
                 period.
                   y,
                 period.
                   z);
    }
    public static final Vector3 pnoise(Point3 p,
                                       float t,
                                       Vector3 pperiod,
                                       float tperiod) {
        return pnoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 t,
                 pperiod.
                   x,
                 pperiod.
                   y,
                 pperiod.
                   z,
                 tperiod);
    }
    public static final Vector3 spnoise(float x,
                                        float period) {
        return new Vector3(
          PerlinScalar.
            spnoise(
              x +
                P1x,
              period),
          PerlinScalar.
            spnoise(
              x +
                P2x,
              period),
          PerlinScalar.
            spnoise(
              x +
                P3x,
              period));
    }
    public static final Vector3 spnoise(float x,
                                        float y,
                                        float w,
                                        float h) {
        return new Vector3(
          PerlinScalar.
            spnoise(
              x +
                P1x,
              y +
                P1y,
              w,
              h),
          PerlinScalar.
            spnoise(
              x +
                P2x,
              y +
                P2y,
              w,
              h),
          PerlinScalar.
            spnoise(
              x +
                P3x,
              y +
                P3y,
              w,
              h));
    }
    public static final Vector3 spnoise(float x,
                                        float y,
                                        float z,
                                        float w,
                                        float h,
                                        float d) {
        return new Vector3(
          PerlinScalar.
            spnoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              w,
              h,
              d),
          PerlinScalar.
            spnoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              w,
              h,
              d),
          PerlinScalar.
            spnoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              w,
              h,
              d));
    }
    public static final Vector3 spnoise(float x,
                                        float y,
                                        float z,
                                        float t,
                                        float w,
                                        float h,
                                        float d,
                                        float p) {
        return new Vector3(
          PerlinScalar.
            spnoise(
              x +
                P1x,
              y +
                P1y,
              z +
                P1z,
              t,
              w,
              h,
              d,
              p),
          PerlinScalar.
            spnoise(
              x +
                P2x,
              y +
                P2y,
              z +
                P2z,
              t,
              w,
              h,
              d,
              p),
          PerlinScalar.
            spnoise(
              x +
                P3x,
              y +
                P3y,
              z +
                P3z,
              t,
              w,
              h,
              d,
              p));
    }
    public static final Vector3 spnoise(Point2 p,
                                        float periodx,
                                        float periody) {
        return spnoise(
                 p.
                   x,
                 p.
                   y,
                 periodx,
                 periody);
    }
    public static final Vector3 spnoise(Point3 p,
                                        Vector3 period) {
        return spnoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 period.
                   x,
                 period.
                   y,
                 period.
                   z);
    }
    public static final Vector3 spnoise(Point3 p,
                                        float t,
                                        Vector3 pperiod,
                                        float tperiod) {
        return spnoise(
                 p.
                   x,
                 p.
                   y,
                 p.
                   z,
                 t,
                 pperiod.
                   x,
                 pperiod.
                   y,
                 pperiod.
                   z,
                 tperiod);
    }
    public PerlinVector() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL2aC2wUxxmA587vB/jBy6EGDDEEY3IHASoIhJeDwfSAAzuI" +
       "mCRmvTdnL+ztLntz9vFKCW0KShVaUpNASy01AUESXoqKoIpSoYaW0FSqqKJW" +
       "aVQStaqKGlBLJZK0SZvOPzt3u7d3t8aru1ja33O7M/P/3/zz/zP7OH0bFUV1" +
       "1Kyp8o4eWSU+HCe+rfI8H9mh4ahvdWBeUNCjONQiC9FoBz3XJU45X/XJ59/v" +
       "rfai4k40SlAUlQhEUpXoBhxV5T4cCqAq8+wKGUeiBFUHtgp9gj9GJNkfkKJk" +
       "YQBVWJoS1BhImOCnJvipCX5mgn+ZWYs2GoGVWKQFWggKiW5HTyNPABVrIphH" +
       "0OTUTjRBFyK8myAjoD2Uwu+NFIo1juuoIcluMKcBH272D7z0VPUbBaiqE1VJ" +
       "SjuYI1IjCFXSiSojONKN9eiyUAiHOlGNgnGoHeuSIEs7md2dqDYq9SgCiek4" +
       "OUhwMqZhnek0R65SBDY9JhJVT+KFJSyHEr+KwrLQQ1nHmqwGYSucp4DlEjVM" +
       "DwsiTjQp3CYpIYIm2VskGRu/QSvQpiURTHrVpKpCRaAnUK3hO1lQevztRJeU" +
       "Hlq1SI1RLQSNz9opjLUmiNuEHtxFUJ29XtC4RGuVsYGAJgSNsVdjPVEvjbd5" +
       "yeKf22sXHdylrFK8zOYQFmWwv5Q2mmhrtAGHsY4VERsNK2cEXhTGvnXAixCt" +
       "PMZW2ahzcfedpTMnXn7HqPO1DHXWdW/FIukSj3ePvF7f0rSgAMwo1dSoBM5P" +
       "IWfTP8ivLIxrNPLGJnuEi77ExcsbfvX43tfwx15U3oaKRVWOReg8qhHViCbJ" +
       "WF+JFawLBIfaUBlWQi3sehsqoeWApGDj7LpwOIpJGyqU2alilf2mQxSmXcAQ" +
       "ldCypITVRFkTSC8rxzWEUAk9UCU9ypDxx/4TtN7fq0awXxAFRVJUP527WNDF" +
       "Xj8WVX9UiGgy9Vo0poRltd8f1UW/qvckf0eoBn8Q69SijRjmuA+mlpaPTuNA" +
       "Ut3v8dBBrreHuEyjY5Uqh7DeJQ7Elq+4c7brXW9yyvMxIGgCVePjanygxmdV" +
       "gzwe1vtoUGe4jw7+NhrGNMFVNrU/uXrLgSkFdN5o/YV05KDqFMrDbVghqi1m" +
       "rLexjCbSCVf38ub9vs9OLjEmnD97Ys7YGl0+0v/Mxm/O8iJvaoYFJnqqHJoH" +
       "IS8m81+jPbIy9Vu1/+Yn517co5oxlpKyeeint4TQnWIffV0VcYgmQ7P7GQ3C" +
       "ha639jR6USHNBzQHEoHOWZpeJtp1pITwwkQ6BJYiChxW9Yggw6VEDisnvbra" +
       "b55h02IkiFpjhoADbQayTNr6s8tHL/yweYHXmnSrLMtYOyZGCNeY/u/QMabn" +
       "/3Qk+IPDt/dvZs6nNe7PpKARZAsNaOoNOmLPvrP9/Q9vHH/Pa04YQle2WLcs" +
       "iXHaxzRTCw13mc5AcGvjY0pEDUlhSeiWMcy7L6qmzr5w62C14SiZnkn4eebQ" +
       "HZjn71uO9r771KcTWTceEZYbk9ysZgzAKLPnZbou7AA74s/8bsLRq8KPaTak" +
       "GSgq7cQsqSBGhtjQ+5hHmph80HZtFogGLe1anJ2pY7/KqOqm7PHRCqumJa7+" +
       "s07u3vfnzxhRWmRkWCxs7Tv9p4+Nb1n8MWtvTlFoPSmenmLoDsNs+9Brkbve" +
       "KcW/9KKSTlQt8u3LRkGOwWzppEt2NLGnoVuclOupy6+x1ixMhmC9PTwsau3B" +
       "YaY2WobaUC434oHVqaFjeh+McjM9ynnSZ//h6igN5Og4S3lVzONgEp3LqkD+" +
       "ePvayx8s+uctOjitqKgPDKdjUm3WWhuDPdJ3Th+eUDHw0XfZHF98dvQu6HI+" +
       "Uz6ZyUYQDzDvFhBUoulSH13laBxE2Z6LUCJJEeQ4QQXB2XFn/wd1KULX0j6+" +
       "2Pv31H647djNM0ZetTvbVhkfGHjuS9/BAa9l+3R/2g7G2sbYQrHRHGGM5pf0" +
       "z0OP/8EBowgnjCW0toWv4w3JhVzTAGeyk1lMRevfzu1589Se/QZGberuYQXd" +
       "HJ/5/X9/4zvy0bUMyxmdDtRXLOsZkTUn3e8V3O8VGfwOhYeXTL/7NhTWZHEb" +
       "FJeAWApimeGqHVBuza55Jt9qJLYcGTUv1s9PhkL7cDTvvAfNI7jmEdk0L5q7" +
       "YBMUHh+G5ofi96B5JNc8Mutob1p/Cwpdw9F8L6NdxTVXZdP8yLjng1DAw9E8" +
       "1GjDDKvmmquz+pknhm3D0DxnqNEGzTVcc01W5ru/+B4Utg9H81CjPYMetVxz" +
       "bVY/h8NhKPQPR7N1tOOZG3qgON26aiLIMxOy3U2xHHN838BgaN2J2V6+GK8m" +
       "qIyo2oMy7sOypat66CllR7uG3T+aC99zr75+kVxvftjIVjOyJ2t7w6v7/j6+" +
       "Y3HvlmHsYyfZmOxdvrrm9LWV08QXvKgguX6m3RKnNlqYumqW65jewysdKWvn" +
       "xKS3G2B0p9FjNPf2aLu3mascPDzd4mHb5shjurOV9fO8w+6JTeIDsGwqqhQ1" +
       "IngMvRNNu50xbmTmpO+z2Ilvp6PVcbS6HKJ5WS1vcjIbfEcd+H4EYiDJB78O" +
       "3TtDPWeozyFDgVmr1QbyigPICRCDbkEaOEhDDkEKWa1CG4hJc8aB5hyIU25p" +
       "GjlNY+6jJhEA49Lv51VJIcZSfdGB7E0Qb7glm87Jpn/lZMay+LYD2VUQP3dL" +
       "NpOTzcxPOrhizrvfOjBcB/FrNwxT6TGLM8zKvXdM8993MP8DEO/R7bkb6+dy" +
       "6+fmxwOWyP+LA8JfQdxwiTCfI8zPIYJDPr7twPEPEDddciziHItyyDFUOv7U" +
       "AebfIP7lEmYph1man6i4BP3wzW9G6z2ssy9cWv8ot/7R/Fh/hVlY4WD9CBDF" +
       "Lq1fxa1flZ+YNrOqZ4wDwjgQ1S4QYGEIcIRAfhDMCPBMckCAxwWe8fAY1Q1D" +
       "kDMEv7p49jQ50DSDaHRL08FpOnJIU8xqFWeisXHNceCaB8LnlmsT59qUQ65S" +
       "VqvUmctG+IgDIdy5e+a7JXyCEz6RQ0LL+njJBrLSAaQNxHK3IFs4yJb8JAWW" +
       "1w4xO9c7MLSDCLhlCHGGUH6SwpWkMw6ZHtnsQPMkiI1uaB6gRy+n6c2PRyyz" +
       "Cjsw9IDYQlBJ1BWEzCHk/LgkY7SrDjjbQWx1jaNxHC2HOPeeqHc4gMFTWQ9x" +
       "DUY4GMkhmJtM/S0HxGdBPO0aMc4R4zlEdEjVDs8APfAM0HPANcluTrI7P5nB" +
       "kqtfcoA4CuIF1xB7OcTe/GSGjMn6Jw44r4A4NiROnKBK64cm8I69Lu0rNePL" +
       "KvHsYFXpuMHH/sA+nUh+/VQWQKXhmCxb3/laysWajsMSG4Yy4w2wxgw8RVC1" +
       "/UkSQYXwD6z0nDSqvU5QhaUaABkla6WzBBXQSlA8ZzCOIdZXwsa77DhilxLv" +
       "JrSUXymfTsA7A/ZFX+L5fsz4pq9LPDe4eu2uO18/wV4WFImysJO9GSkNoBLj" +
       "gxDWKbwjmJy1t0RfxauaPh95vmxq4t1HyqciNtsmZf6iYkVEI+wbiJ2Xxv10" +
       "0cnBG+x19/8BQgulqmopAAA=");
}
