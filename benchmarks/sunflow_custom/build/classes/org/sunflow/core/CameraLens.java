package org.sunflow.core;
public interface CameraLens extends RenderObject {
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1170608524000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUWXWwURXju2l57pfTaQgEpFPoDSSnehhg0WDSUSwvFA5q2" +
                                "aCyRY2527rp0b3eZnWuvxRokMRAeiNGiYLRPEEX5i5GgMSQ8CQRfMEbjg+Cb" +
                                "RuWBF31AxW9m7nfb4pOX7NzMN9//75y/jypchjod25xImjYP0wwPHzA3hvmE" +
                                "Q93wjujGfsxcqkdM7LpDAIuR1suhPx6+OVLnR4FhtAhbls0xN2zLHaCubY5R" +
                                "PYpCBWiPSVMuR3XRA3gMa2lumFrUcHlXFC0oIuWoPZpTQQMVNFBBkypo3QUs" +
                                "IFpIrXQqIiiwxd2D6DXki6KAQ4R6HLWUMnEww6ksm35pAXCoEucXwShJnGFo" +
                                "dd52ZfMsg092atPv7qv7tAyFhlHIsAaFOgSU4CBkGNWkaCpOmdut61QfRvUW" +
                                "pfogZQY2jUmp9zBqcI2khXma0byTBDDtUCZlFjxXQ4RtLE24zfLmJQxq6rlT" +
                                "RcLESbB1ScFWZWGvgIOB1QYoxhKY0BxJ+ahh6Ryt8lLkbWx/ARCAtDJF+Yid" +
                                "F1VuYQCgBhU7E1tJbZAzw0oCaoWdBikcLZ+XqfC1g8koTtIYR8u8eP3qCrCC" +
                                "0hGChKNGL5rkBFFa7olSUXzu79p84pC13fJLnXVKTKF/FRA1e4gGaIIyahGq" +
                                "CGvWRd/BS64d8yMEyI0eZIVz9dUHW9Y3X7+pcJrmwNkdP0AJj5Ez8do7KyId" +
                                "m8qEGlWO7Roi+CWWy/Tvz950ZRyovCV5juIynLu8PvDVy4c/pr/5UXUfChDb" +
                                "TKcgj+qJnXIMk7Jt1KIMc6r3oSC19Ii870OVsI8aFlXQ3YmES3kfKjclKGDL" +
                                "M7goASyEiyphb1gJO7d3MB+R+4yDEKqED/ngW4HUTwI4Gtb2uJDuGibYMixb" +
                                "g+SlmJERjRI7FgfvjqQwG3U1kna5ndLctJUw7XHNZUSzWTJ/JjajWgQSjOEo" +
                                "tdywyDHnf+WeEbbVjft84PYV3qI3oV6226ZOWYxMp7f2PLgYu+3PF0HWKxw1" +
                                "gZBwVkhYCAkXhCCfT/JeLISpcEIwRqGsoeHVdAy+smP/sdYyyCNnvFy4MiPr" +
                                "bFnuAIQepWRF935x/fSV9zo3+YuLP1TUTgcpV6lUX5A7xCgF+I+n+t8+ef/o" +
                                "XikUMNrmEtAu1ggkFnRL6Dpv3Dz4w727Z7715xUt49Bh03HTIBxV4Ti0J0w4" +
                                "R8F8nyk2xCf3jRytnOWpAchJylS5CHNXzlfSsh2dOTI9o+8+u0EVXkNpmfTA" +
                                "FLjw3d9fh0/9dGuOKAW57Txp0jFqlmgGIiGLstJ6iL1Tdrs+OUwI1Prxc59c" +
                                "5Xc6n1Ui180/E72EN478unzo+ZH9fuQvnWtCOoCqBWW/mEb5qbPKY7yX5bmd" +
                                "529tW0ve8qOybFObo4GXEnUVuwGEMgoTxxIOFZBqENrqzXpmE6rDWCrIXbca" +
                                "X4ldm2r3o3LozDCNOIbuAY2+2Su8pJl25XJTiKoAJyRslsKmuMpNk2o+wuzx" +
                                "AkSWY63c10N4QqIKGuFryrYb+S9uFzliXazKV+I3ybVZLC0ytn6xbRVLm8is" +
                                "tYVKgNZoQr6JYLTvsVK2biQMHDepqMm/Qms2XPn9RJ3KIBMgueis/28GBfgT" +
                                "W9Hh2/v+bJZsfESM5kJ1FtBUkS4qcO5mDE8IPTKvf7Py9A38AUwO6NauMUlV" +
                                "A5amVQJRx2OeZ8xIwcQYy440barh3uj7v1xQKeydfx5kemz6+KPwiWl/0SOh" +
                                "bdacLqZRDwUZiYUqco/g54PvH/GJiAmAGhQNkey0Wp0fV44jwtPyOLWkiN6f" +
                                "L019+dHUUWGGENPBRXrZWAZoi5T8nFi6VeuJcFQG3Uhse+e6DOg29C9ZBn1y" +
                                "kUw3y+tn5NolVM52YnHeKZangDJJ+QCeyDW1xbObGp5wZnHIcFRdmAsi7Mtm" +
                                "PTLVw4hcnAlVLZ3Z872st/zjJQgviETaNIvKubi0Aw6jCUMqGlRl5Mi/IXho" +
                                "ezXkqFz8SR0HFdpLHC0oQuOQY2pXjDQMPgUksd0LYUMlw8rxjq62kgyVD+9c" +
                                "Y0urp3eMXJrZsevQg6fPyi5ZAU/2yUn5UIN3p+oW+ebYMi+3HK/A9o6HtZeD" +
                                "a3IJUiuWhqIWUaTbqrmLuSflcFl+k58v/WzzhzN35cT7F5pkRLsRDQAA");
}
