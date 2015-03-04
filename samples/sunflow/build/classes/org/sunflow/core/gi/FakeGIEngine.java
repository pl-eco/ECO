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
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYXWwcxXlu/e848dkmzk9jxzEONDHcllYgUVPacLITpwe2" +
                                                    "YpOqFzVmvDt33nj2h905+2JqSqJWjniIUGtoqKgfqiAKBIJQI4oQUl5aQPSF" +
                                                    "qmrVh0LVl6LSPOShFJW29Ptmd2/39u7c5qGWZm525vud73d86Rpp8Vwy6tj8" +
                                                    "dJHbIsPKInOK35kRpx3mZY7m7pymrsf0LKeeNwt7c9rwK90ff/rEQlohrXnS" +
                                                    "Ry3LFlQYtuUdY57Nl5ieI93R7jhnpidIOneKLlG1JAyu5gxPjOXIlhiqICO5" +
                                                    "UAQVRFBBBFWKoB6KoABpK7NKZhYxqCW8h8mjJJUjrY6G4gmyr5qIQ11qBmSm" +
                                                    "pQZAoR2/j4NSErnskqGK7r7ONQo/Oaqu//Bk+tUm0p0n3YY1g+JoIIQAJnnS" +
                                                    "ZTJznrneIV1nep70WIzpM8w1KDdWpNx50usZRYuKkssql4SbJYe5kmd0c10a" +
                                                    "6uaWNGG7FfUKBuN6+NVS4LQIuvZHuvoaTuA+KNhpgGBugWosRGleNCxdkL1J" +
                                                    "jIqOI18HAEBtM5lYsCusmi0KG6TXtx2nVlGdEa5hFQG0xS4BF0F2NySKd+1Q" +
                                                    "bZEW2ZwgO5Nw0/4RQHXIi0AUQbYnwSQlsNLuhJVi9rn2wD3nH7GOWIqUWWca" +
                                                    "R/nbAWkwgXSMFZjLLI35iF0Hc0/R/jfPKYQA8PYEsA/z2revf+22watv+zCf" +
                                                    "qwMzNX+KaWJOuzi/7b092QN3N6EY7Y7tGWj8Ks2l+08HJ2NlByKvv0IRDzPh" +
                                                    "4dVjv/zmYy+wjxTSOUlaNZuXTPCjHs02HYMz9zCzmEsF0ydJB7P0rDyfJG2w" +
                                                    "zhkW83enCgWPiUnSzOVWqy2/4YoKQAKvqA3WhlWww7VDxYJclx1CSBsMcjuM" +
                                                    "NPH/5K8gs+qCbTKVatQyLFsF32XU1RZUptmqR02Hg9W8klXg9rLquZpqu8XK" +
                                                    "t2a7TC0a6gRdZIcnx60iSJZB73L+T3TLqE96OZWCq96TDHQOMXLE5jpz57T1" +
                                                    "0n3j11+ee1epOH5wE4IMAadMwCmDnDJFIxPnRFIpyeAm5OjbEaywCPEMma7r" +
                                                    "wMy3jj50brgJHMhZboYrRNBh0CoQY1yzs1HQT8rUpoHn7fzJibXMJ8991fc8" +
                                                    "tXGGrotNrl5YPnP8O19QiFKdalEt2OpE9GlMkJVEOJIMsXp0u9c+/PjyU6t2" +
                                                    "FGxVuTvIAbWYGMPDSQO4tsZ0yIoR+YND9Mrcm6sjCmmGxADJUFC4YMgzg0ke" +
                                                    "VbE8FuZF1KUFFC7Yrkk5HoXJrFMsuPZytCM9Y5tc94BRtqBz74CxPfB2+Yun" +
                                                    "fQ7ON/mehFZOaCHz7sTrV5++8qPRu5V4iu6OFb0ZJvyA74mcZNZlDPb/cGH6" +
                                                    "B09eWzshPQQgbq7HYATnLIQ/mAyu9XtvP/z7D96/+Bsl8ioBdbA0zw2tDDRu" +
                                                    "ibhAcuCQoND2Iw9apq0bBYPOc4bO+c/u/Xdc+ev5tG9NDjuhM9z23wlE+7vu" +
                                                    "I4+9e/Lvg5JMSsPiFGkegfkX0BdRPuS69DTKUT7z64Gn36I/htwJ+cozVphM" +
                                                    "QakgXlCo7ZBJawJxypFiSduoEuygnDNoPIlM5NmXcBpyas7Kcmen/GoC2Q40" +
                                                    "jrIJLMKx6PzHFJ8/+6dPpMo18VWn9iTw8+qlZ3Zn7/1I4keOjth7y7W5ChqW" +
                                                    "CPeLL5h/U4Zbf6GQtjxJa0E3dJzyErpTHjoAL2yRoGOqOq+u5n7pGqsE8p5k" +
                                                    "kMXYJkMsypGwRmhcdyaiqiuMqp4gqnqSUZUicjEmUYblvB+nz4dO3ea4xhLF" +
                                                    "VosoJaeuK5hQtzLHGaYbaei0D3ZXtRj9MHoDMXobiJHF6SsCfGHxdMhqR5yV" +
                                                    "YULPghFhu4057YLRF3Dqa8DpSMCptejaJUvHrwmforzCkZhjVvx/V43/hxUI" +
                                                    "Q36gUYcku7uLZ9c39Kln7/CrSW911zEOTfVLv/3XrzIX/vhOnQLYIWznds6W" +
                                                    "GE+Ey0BVFbtfNo+Rmz7+/IuvifdGv+yzPNg4tJKIb539y+7ZexceuoHatTeh" +
                                                    "fJLk8/dfeufwLdr3FdJU8faafrgaaazaxztdBg28NVvl6YMVw0vHHoQxEBh+" +
                                                    "oG79iGwbJSpF3qcSWnmgxsozC1SHrhvfHZL3hKSV3yTfncTpG4JsLUIDCElW" +
                                                    "N1AjiVubBOXGTLUmwzBuDTS59X/WJMjX+HlCQumbyFjAiQrSAzIe5vY85cdu" +
                                                    "SM6+MLeMBnKO3qic4Y331964Bg22xOebaCDZGAKbaENsXj2mXcOExn4peHmo" +
                                                    "q70fLD7z4Ut+aCRLRQKYnVt//LPM+XUl9pa7ueY5Fcfx33NSxq3+fX0GfykY" +
                                                    "/8aBCuCG38/3ZoNHxVDlVeE4mFD2bSaWZDHx58urb/x0dU0JLmQK0vW8bXNG" +
                                                    "rTq1VpCueNeMvcDOmre3/17UXt7obt+x8eDvZB9YedN1wMOqUOI8Xnpi61bH" +
                                                    "ZQVDStLhFyI/mT4qSF+dHh4KStGQYq76cGcESSfhwLr4Ewf7riBbYmCgc7CK" +
                                                    "A61BEQEgXJ6rVK20bH2w9Gb80lsmsYyK3V/8q6oVRI+S/88IE1zJ/4/GnHZ5" +
                                                    "4+gDj1y/61mZLVs0TldWkEo7POf9LriSJPc1pBbSaj1y4NNtr3TsDw26Dafe" +
                                                    "oPVNyLa3foc4bjpC9nQrP9/xs3ue23hftqj/AQ+v1EloEgAA");
}
