package org.sunflow.core.gi;
import org.sunflow.core.GIEngine;
import org.sunflow.core.Options;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class FakeGIEngine implements GIEngine {
    private Vector3 up;
    private Color sky;
    private Color ground;
    public FakeGIEngine(Options options) { super();
                                           up = options.getVector("gi.fake.up",
                                                                  new Vector3(
                                                                    0,
                                                                    1,
                                                                    0)).normalize(
                                                                          );
                                           sky = options.getColor(
                                                           "gi.fake.sky",
                                                           Color.
                                                             WHITE).
                                                   copy(
                                                     );
                                           ground = options.getColor(
                                                              "gi.fake.ground",
                                                              Color.
                                                                BLACK).
                                                      copy(
                                                        );
                                           sky.mul((float) Math.PI);
                                           ground.mul((float) Math.
                                                                PI);
    }
    public Color getIrradiance(ShadingState state, Color diffuseReflectance) {
        float cosTheta =
          Vector3.
          dot(
            up,
            state.
              getNormal(
                ));
        float sin2 =
          1 -
          cosTheta *
          cosTheta;
        float sine =
          sin2 >
          0
          ? (float)
              Math.
              sqrt(
                sin2) *
          0.5F
          : 0;
        if (cosTheta >
              0)
            return Color.
              blend(
                sky,
                ground,
                sine);
        else
            return Color.
              blend(
                ground,
                sky,
                sine);
    }
    public Color getGlobalRadiance(ShadingState state) { return Color.
                                                                  BLACK;
    }
    public boolean init(Scene scene) { return true; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfW/93nPhsE+dPY8cxDjQx3BYQSNRACSc7cXpg" +
                                                    "Kw6pelFzzO3O3W28u7PMztkXUwOJihzlQ4RaAwGBP6AgCg0EVY1oVSHlSwuI" +
                                                    "fqGqWvVDoeqXotJ8yIdSBLT0zezu7d7enSFfamn3Zmfem/fevPd+740vXEFt" +
                                                    "LkPjDjVPFE3KU6TCU8fN21P8hEPc1MHM7bOYuURPm9h1D8NcTht9o/eTz58s" +
                                                    "JRXUnkUD2LYpx9ygtnuIuNRcIHoG9YazkyaxXI6SmeN4AatlbphqxnD5RAZt" +
                                                    "iLByNJYJVFBBBRVUUKUK6r6QCpg2ErtspQUHtrn7MHoUJTKo3dGEehztqt3E" +
                                                    "wQxb/jaz0gLYoVN8HwGjJHOFoZGq7Z7NdQY/Na6uPnMs+fMW1JtFvYY9J9TR" +
                                                    "QAkOQrKoxyJWnjB3n64TPYv6bEL0OcIMbBpLUu8s6neNoo15mZHqIYnJskOY" +
                                                    "lBmeXI8mbGNljVNWNa9gEFMPvtoKJi6CrYOhrZ6FU2IeDOw2QDFWwBoJWFrn" +
                                                    "DVvnaGeco2rj2HeBAFg7LMJLtCqq1cYwgfo935nYLqpznBl2EUjbaBmkcLS9" +
                                                    "6abirB2szeMiyXG0NU436y0BVZc8CMHC0eY4mdwJvLQ95qWIf648cNfZR+wD" +
                                                    "tiJ11olmCv07gWk4xnSIFAgjtkY8xp69mafx4FunFYSAeHOM2KN584dX771p" +
                                                    "+PI7Hs03GtDM5I8Tjee08/lN7+9I77mzRajR6VDXEM6vsVyG/6y/MlFxIPMG" +
                                                    "qzuKxVSwePnQb7//+KvkYwV1T6N2jZplC+KoT6OWY5iE7Sc2YZgTfRp1EVtP" +
                                                    "y/Vp1AHjjGETb3amUHAJn0atppxqp/IbjqgAW4gj6oCxYRdoMHYwL8lxxUEI" +
                                                    "dcCDboYnibw/+ctRUS1Ri6hYw7ZhUxVil2CmlVSi0RwjDlUn0zNqHk65ZGE2" +
                                                    "76pu2S6YdDGnlV1OLdVlmkpZMZhWNcqIWjTUKTxP9k9P2kVQNiUCzvn/iaoI" +
                                                    "q5OLiQQ4ZEccDkzIpAPU1AnLaavl+yavvp57T6mmh39eHI2ApJQvKSUkpYpG" +
                                                    "KioJJRJSwHVCoudt8NU8ZD3gYc+euR8cfOj0aAuEmbPYCgctSEfBUF+NSY2m" +
                                                    "Q2iYlgCoQXxuffHoSurTl7/jxafaHMcbcqPL5xZPHnnsWwpSagFZmAVT3YJ9" +
                                                    "VsBoFS7H4onYaN/elY8+ufj0Mg1TsgbhfaSo5xSZPhp3AKMa0QE7w+33juBL" +
                                                    "ubeWxxTUCvABkMkxHDCg0XBcRk3GTwToKWxpA4MLlFnYFEsB5HXzEqOL4YyM" +
                                                    "jE1y3AdO2SBSYAs8m/2ckL9idcAR7+u8SBJejlkh0XnqV5efvfTc+J1KFMh7" +
                                                    "I6VxjnAPFvrCIDnMCIH5v5yb/clTV1aOyggBiusbCRgT7zSABLgMjvWJdx7+" +
                                                    "84cfnP+DEkYVh2pZzpuGVoE9bgilAISYAGPC92MP2hbVjYKB8yYRwflF7+5b" +
                                                    "Lv3zbNLzpgkzQTDc9NUbhPPb7kOPv3fs38Nym4QmSlhoeUjmHcBAuPM+xvAJ" +
                                                    "oUfl5O+Hnn0bvwAIC6jmGktEAlXCzxeh1GbA27pEnHGkWtI3qiTbK98p4TzJ" +
                                                    "jOTabeI14tStVeTMVvnVArrtaZ5lU6JUR7Lzsxkzf+pvn0qT6/KrQYWK8WfV" +
                                                    "C89vT9/zseQPA11w76zUYxW0NSHvra9a/1JG23+joI4sSmp+z3QEm2URTlno" +
                                                    "E9ygkYK+qma9tuZ7BW6imsg74kkWERtPsRAjYSyoxbg7llU9QVb1+VnVF8+q" +
                                                    "BJKDCckyKt+7xeubQVB3OMxYwKIhQ0rZaRgKFlS31BEi4EY6OumR3VGrxiA8" +
                                                    "/b4a/U3USIvX3RxiYf5EIGpLVJRhQWcjMoKy5pK2wTPgSxpoIumAL6m9yGjZ" +
                                                    "1sXXlLejPMKxSGBW439bXfwHFUik/FCzPkr2gOdPra7pMy/d4lWT/treZBJa" +
                                                    "79f++J/fpc799d0GBbCLU+dmkywQM5YuQzVV7H7ZYoZheuaVn73J3x//tidy" +
                                                    "b/PUijO+feof2w/fU3roGmrXzpjx8S1fuf/Cu/tv0H6soJZqtNd1zbVME7Ux" +
                                                    "3s0ItPn24ZpIH646Xgb2MDxDvuOHGtaP0LchUCnyPJXAy0N1Xp4rYR16c3E7" +
                                                    "kbKn5F7ZdfDumHh9j6ONRWgTAWR1Q1gkeetBUE7M1VoyCs+NviU3fm1LfLwW" +
                                                    "n0cllb6OjgXxwhz1gY77TZrH5qFr0nMgwJZxX8/xa9UzOPHB+hPXoA2X/OY6" +
                                                    "FkgxBhettsHXrx6zzLCg/V/w7yfqcv+H889/9JqXGvFSESMmp1fPfJk6u6pE" +
                                                    "bnzX1126ojzerU/quNE7ry/hLwHPf8UjDBATXtffn/avHiPVu4fjCEDZtZ5a" +
                                                    "UsTU3y8u//qnyyuKfyAzANd5Sk2C7Qa1lqOeaNcseoGtdTd071apvb7W27ll" +
                                                    "7cE/yT6wevPrgutXoWya0dITGbc7jBQMqUmXV4g8MH2Uo4EGPTwUlKIh1Vz2" +
                                                    "6E5ylIzTgXfFT5TsRxxtiJCBzf4oSrQCRQSIxPB0tWolZesjSm/KK70VFEFU" +
                                                    "0f1Fv2paQRFR8r8eAcCVvf975LSLawcfeOTqHS9JtGzTTLy0JHbphEu/1wVX" +
                                                    "QXJX092CvdoP7Pl80xtduwOHbhKvfr/1jem2s3GHOGk5XPZ0S7/c8ou7Xl77" +
                                                    "QLao/wO8bscXjhIAAA==");
}
