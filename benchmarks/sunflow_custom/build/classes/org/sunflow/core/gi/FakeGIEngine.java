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
    public static final long jlc$SourceLastModified$jl7 = 1166304750000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYXWxcxRWevf53nHhtE+enseMYB5oY9pZWIFFT2rCyE6cL" +
                                                    "tuIkVTdqltl7Z9c3nvvD3Fl7Y2pKolaOeIhQa2iowA8oiEIDQRURIISUlxYQ" +
                                                    "faGqWvWhUPWlqDQPeShFpS09M/d37+66zUMt3btzZ86Zc86cc75zxpeuoTaX" +
                                                    "oXHHpqfL1OYZUuWZU/TODD/tEDdzOHfnLGYu0bMUu+5RmCtoo6/0fvLZ4/Np" +
                                                    "BbXn0QC2LJtjbtiWe4S4Nl0keg71RrOTlJguR+ncKbyI1Qo3qJozXD6RQ5ti" +
                                                    "rByN5QIVVFBBBRVUqYJ6IKICps3EqphZwYEt7j6EHkGpHGp3NKEeR3tqN3Ew" +
                                                    "w6a/zay0AHboFN/HwSjJXGVoJLTds7nO4CfG1bUfn0z/vAX15lGvYc0JdTRQ" +
                                                    "goOQPOoxiVkkzD2g60TPoz6LEH2OMANTY1nqnUf9rlG2MK8wEh6SmKw4hEmZ" +
                                                    "0cn1aMI2VtG4zULzSgahevDVVqK4DLYORrZ6Fk6JeTCw2wDFWAlrJGBpXTAs" +
                                                    "naPdSY7QxrFvAgGwdpiEz9uhqFYLwwTq93xHsVVW5zgzrDKQttkVkMLRzqab" +
                                                    "irN2sLaAy6TA0fYk3ay3BFRd8iAEC0dbk2RyJ/DSzoSXYv659sA95x+2DlmK" +
                                                    "1FknGhX6dwLTcILpCCkRRiyNeIw9+3NP4sG3zikIAfHWBLFH89p3r3/jtuGr" +
                                                    "73g0X2hAM1M8RTRe0C4Wt7y/K7vv7hahRqdju4Zwfo3lMvxn/ZWJqgOZNxju" +
                                                    "KBYzweLVI7/89qMvko8V1D2N2jWbVkyIoz7NNh2DEnaQWIRhTvRp1EUsPSvX" +
                                                    "p1EHjHOGRbzZmVLJJXwatVI51W7LbziiEmwhjqgDxoZVsoOxg/m8HFcdhFAH" +
                                                    "POh2eNLI+5O/HBXUYy6Eu4o1bBmWrULwEsy0eZVodqEIpztvYrbgqlrF5bap" +
                                                    "uhWrRO0l1WWaarNy+K3ZjKhlQ53CC+Tg9KRVBiUzItCc/7+IqrAyvZRKgQN2" +
                                                    "JdOfQuYcsqlOWEFbq9w3ef3lwntKmA7++XA0ApIyvqSMkJQpG5m4JJRKSQE3" +
                                                    "CYmed8E3C5DlgH89++a+c/jBc6MtEFbOUiscrCAdBft8NSY1OxtBwbQEPA3i" +
                                                    "cfuzJ1Yznz7/dS8e1ea43ZAbXb2wdOb4976kIKUWgIVZMNUt2GcFbIbwOJZM" +
                                                    "vEb79q5+9MnlJ1fsKAVrEN1HhnpOkdmjSQcwWyM6YGW0/f4RfKXw1sqYgloB" +
                                                    "LgAiOYYDBvQZTsqoyfCJAC2FLW1gcMlmJqZiKYC4bj7P7KVoRkbGFjnuA6ds" +
                                                    "EiG/DZ6tfg7IX7E64Ij3TV4kCS8nrJBoPPXG1aeu/GT8biUO3L2xUjhHuAcD" +
                                                    "fVGQHGWEwPwfLsz+6IlrqydkhADFzY0EjIl3FkABXAbH+oN3Hvr9hx9c/I0S" +
                                                    "RRWH6lgpUkOrwh63RFIAMijAlvD92DHLtHWjZOAiJSI4/9m7944rfz2f9rxJ" +
                                                    "YSYIhtv++wbR/I770KPvnfz7sNwmpYmSFVkekXkHMBDtfIAxfFroUT3z66Gn" +
                                                    "3sbPAKICirnGMpHAlPLzRSi1FfC1LhFnHKmW9I0qyfbLd0Y4TzIjufYV8Rpx" +
                                                    "6taqcma7/GoB3fY1z7IpUZpj2fmPGVo8+6dPpcl1+dWgIiX48+qlp3dm7/1Y" +
                                                    "8keBLrh3V+uxCtqYiPfLL5p/U0bbf6GgjjxKa36PdBzTiginPPQFbtA4QR9V" +
                                                    "s15b472CNhEm8q5kksXEJlMswkgYC2ox7k5kVU+QVX1+VvUlsyqF5GBCsozK" +
                                                    "917x+mIQ1B0OMxaxaMCQUnEahoIJ1SxznAi4kY5Oe2R31aoxCE+/r0Z/EzWy" +
                                                    "4vU1DrGwcDoQtS0uyjChkxEZYbPmknbAM+BLGmgi6ZAvqb3M7Iqli68pb0d5" +
                                                    "hGOxwAzjf0dd/AcVSKT8ULO+SfZ8F8+ureszz93hVZP+2l5kElrtl377r19l" +
                                                    "Lvzx3QYFsIvbzu2ULBKaSJehmip2v2wpozB97IWfvcbfH/+qJ3J/89RKMr59" +
                                                    "9i87j947/+AN1K7dCeOTW75w/6V3D96i/VBBLWG013XJtUwTtTHezQi09dbR" +
                                                    "mkgfDh0vA3sYniHf8UMN60fk2wioFHmeSuDloTovz81jHXpxcRuRsqfkXvkN" +
                                                    "8O6keH2Lo81laAsBZHVDWCR560FQTszVWjIKz62+Jbf+z5b4eC0+T0gqfQMd" +
                                                    "S+KFOeoDHQ9Su4jpkRvScyDAlnFfz/Eb1TM48cH6E9eg7Zb8dAMLpBiDi9ba" +
                                                    "4BtXj1lmmNDuL/r3EXWl/8OFpz96yUuNZKlIEJNza499njm/psRueDfXXbLi" +
                                                    "PN4tT+q42Tuvz+EvBc+/xSMMEBNel9+f9a8aI+Fdw3EEoOzZSC0pYurPl1fe" +
                                                    "/OnKquIfyAzAddG2KcFWg1rLUU+8axa9wPa6G7l3i9ReXu/t3LZ+7HeyDwxv" +
                                                    "el1w3SpVKI2Xnti43WGkZEhNurxC5IHpIxwNNOjhoaCUDanmikd3hqN0kg68" +
                                                    "K37iZN/naFOMDGz2R3GiVSgiQCSG58KqlZatjyi9Ga/0VlEMUUX3F/+qaQVF" +
                                                    "RMn/cgQAV/H+z1HQLq8ffuDh63c9J9GyTaN4eVns0gmXfK8LDkFyT9Pdgr3a" +
                                                    "D+37bMsrXXsDh24Rr36/9U3otrtxhzhpOlz2dMuvb3v1nufXP5At6n8AdSkB" +
                                                    "f34SAAA=");
}
