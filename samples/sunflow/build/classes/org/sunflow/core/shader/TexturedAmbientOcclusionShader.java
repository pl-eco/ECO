package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Color;
public class TexturedAmbientOcclusionShader extends AmbientOcclusionShader {
    private Texture tex;
    public TexturedAmbientOcclusionShader() { super();
                                              tex = null; }
    public boolean update(ParameterList pl, SunflowAPI api) { String filename =
                                                                pl.
                                                                getString(
                                                                  "texture",
                                                                  null);
                                                              if (filename !=
                                                                    null)
                                                                  tex =
                                                                    TextureCache.
                                                                      getTexture(
                                                                        api.
                                                                          resolveTextureFilename(
                                                                            filename),
                                                                        false);
                                                              return tex !=
                                                                null &&
                                                                super.
                                                                update(
                                                                  pl,
                                                                  api);
    }
    public Color getBrightColor(ShadingState state) {
        return tex.
          getPixel(
            state.
              getUV(
                ).
              x,
            state.
              getUV(
                ).
              y);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1XW2xURRiePb3RUnpDoCC0UAqxoHvEBBIsQcumQGGhDa0k" +
       "1miZnjO7e2DOhXNm26VYBRID8YEYLYhG+2AgCKIYI1FjTHhRNPqiMRofvMQX" +
       "jcoDD17i/Z851z27rfriJuec2Zn/Ov//f/PPpeuoyrHRWsukh7LUZElSYMn9" +
       "dH2SHbKIk9yRXj+AbYeoKYodZwjmRpSOlxt/+u2xXJOEqofRfGwYJsNMMw1n" +
       "D3FMOkbUNGoMZ3sp0R2GmtL78RiW80yjclpzWHcazY2wMtSZ9k2QwQQZTJCF" +
       "CXJPSAVM84iR11OcAxvMOYgeQok0qrYUbh5DK4qFWNjGuidmQHgAEubw/3vB" +
       "KcFcsNHywHfX5xKHT62Vp558oOmVCtQ4jBo1Y5Cbo4ARDJQMo3qd6KPEdnpU" +
       "lajDqNkgRB0ktoapNiHsHkYtjpY1MMvbJNgkPpm3iC10hjtXr3Df7LzCTDtw" +
       "L6MRqvr/qjIUZ8HXhaGvrodb+Tw4WKeBYXYGK8RnqTygGSpD7XGOwMfOnUAA" +
       "rDU6YTkzUFVpYJhALW7sKDay8iCzNSMLpFVmHrQwtGRGoXyvLawcwFkywlBr" +
       "nG7AXQKqWrERnIWhBXEyIQmitCQWpUh8ru/edPKwsd2QhM0qUSi3fw4wtcWY" +
       "9pAMsYmhEJexfk36NF741gkJISBeECN2aV578Mbdt7ZdfdelubkMTf/ofqKw" +
       "EeXsaMOHS1NdGyu4GXMs09F48Is8F+k/4K10FyyovIWBRL6Y9Bev7nnn3iMX" +
       "yfcSqutD1YpJ8zrkUbNi6pZGib2NGMTGjKh9qJYYakqs96EaGKc1g7iz/ZmM" +
       "Q1gfqqRiqtoU/2GLMiCCb1ENjDUjY/pjC7OcGBcshFANPKgenjrk/sSXof1y" +
       "ztSJjBVsaIYpQ+4SbCs5mSim7GDdohA1J29kqDkuO7Yim3Y2+K+YNpGdHFaJ" +
       "LQ9B9UBFqD36qEYM1q8oNO+A64NiOclzzvpftRW4703jiQSEZWkcFCjU03aT" +
       "Au2IMpXf0nvjpZH3paBIvF1jaAPoT3r6k1x/0tWfnF0/SiSE2pu4HW4mQBwP" +
       "ACIAVtZ3Dd6/Y9+JjgpIQWu8EoLASTtgBzzjehUzFcJGnwBHBXK39bn7jid/" +
       "OX+Xm7vyzBhflhtdPTN+dO/Dt0tIKgZr7ixM1XH2AQ6xAZR2xou0nNzG49/+" +
       "dPn0pBmWaxH6eyhSyslRoCMeFttUiAr7GopfsxxfGXlrslNClQAtAKcMQ/oD" +
       "UrXFdRShQbePrNyXKnA4Y9o6pnzJh8M6lrPN8XBG5EuDGDdDUOby8uiCZ55X" +
       "L+LLV+db/H2Tm188yjEvBHJvfePqU1eeXrtRioJ8Y+TYHCTMhYzmMEmGbEJg" +
       "/vMzA0+cun78PpEhQLGynIJO/k4BgEDIYFsfeffgZ19+cfZjKcwqBidpfpRq" +
       "SgFkrA61ALxQgDge+857DN1UtYyGRynhyfl746p1V3442eRGk8KMnwy3/rOA" +
       "cH7xFnTk/Qd+bhNiEgo/3kLPQzJ3A+aHkntsGx/idhSOfrTsqWv4WUBfQDxH" +
       "myACxJDwDImtl0Wo1oh3Mra2jr+WWyVrBTHTGlRd18xFtJWf0pHi+7Wfjh77" +
       "+hfhUUn5lDmcYvzD8qVnlqQ2fy/4wzzm3O2FUoCCjibkveOi/qPUUf22hGqG" +
       "UZPitUt7Mc3zbBmGFsHxeyhoqYrWi49792zrDup0abyGImrjFRQCI4w5NR/X" +
       "xYqGny+o1a8e/xstmgQSg42CpUO8V/HXLX7O1li2NoZ5L4Yq4C0oFsCxW4LE" +
       "HgSLKrSEHZ2R6CKe9Mtm6jJEh3T22NS02n9unYunLcUndy80pi9+8scHyTNf" +
       "vVfmYKhlpnUbJWOERnRKXGURju8SDVgYyUcvvPAa+3Dtna7KNTNnX5zx2rHv" +
       "lgxtzu37D+jdHnM+LvLCrkvvbVutPC6hiiAhSnrKYqbu4jSoswlEwBgqSoa2" +
       "IBnm8zAshqfZS4bmsggaRi6sZcnbTy/4bSXBF64SaFk5WPhkC6Nkg+63Z6BP" +
       "qNk9C1oM8ddOgMu8pULmzY4LA7amQ0835jWd8mTLlwee+fZFN6JxEIgRkxNT" +
       "j/6VPDklRdr4lSWddJTHbeWFlfPcjf0Lfgl4/uQPd4FPuK1cS8rrJ5cHDaVl" +
       "8TpYMZtZQsXWby5Pvvn85HHJ25IUFOKoaVKCjVIUFRPbgjiLsLZ7he8DwL+L" +
       "c8JDYi+Ay0rizPspuKHwOxoRYpRZ4pjlr30MNWQJ22Jr2RyDo8a7eIH0RVHp" +
       "mg63laRYL3NMQMrN3uHxc6u15Kbp3o6Ul6Yb5yyavudT0bMEN5hauEZk8pRG" +
       "cTQyrrZsktGEH7UuqrqgZsQMj3ShkLHuQDigu/QH4XYep2eokn+iZIyhuREy" +
       "CLc3ihLBdAUQ8WHB8rcxOVNPXH6nCqgIla04Rq8sKjVxx/cBK+/e8keUy9M7" +
       "dh++seGcQL8qheKJCXEnhCuu29cFoLdiRmm+rOrtXb81vFy7ys/0Bv5q8Zq5" +
       "mG3t5XueXt1iokuZeH3Rq5vOT38hmq6/Abql7Jl8EQAA");
}
