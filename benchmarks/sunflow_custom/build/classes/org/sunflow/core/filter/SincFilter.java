package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class SincFilter implements Filter {
    private float s;
    public SincFilter(float size) { super();
                                    s = size; }
    public float getSize() { return s; }
    public float get(float x, float y) { return sinc1d(x) * sinc1d(y); }
    private float sinc1d(float x) { x = Math.abs(x);
                                    if (x < 1.0E-4F) return 1.0F;
                                    x *= Math.PI;
                                    return (float) Math.sin(x) / x; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1159026716000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWwUVRS+O223PxS6lAIVoUAtxALuiEYN1qi4aaW4QkML" +
                                                    "hvqz3M7e3Q6dnRlm7rZLsQokBsIDMVoVjfbBYFREIUaCxpDwokD0RWM0PgjG" +
                                                    "F43KAw/+xP9z7vztzm4rvrjJzN659/zec853zz1+mdTYFlltGtrurGbwOCvw" +
                                                    "+E7tljjfbTI7vjF5Sx+1bJZOaNS2B2AupbSfbPr59yeHYxKJDpJ5VNcNTrlq" +
                                                    "6PYWZhvaKEsnSVMw262xnM1JLLmTjlI5z1VNTqo270qSWUWsnHQkPRNkMEEG" +
                                                    "E2Rhgrw+oAKm2UzP5xLIQXVu7yKPkUiSRE0FzeNkeakQk1o054rpEx6AhDr8" +
                                                    "3gZOCeaCRZb5vjs+lzn8zGp58rlHYm9XkaZB0qTq/WiOAkZwUDJIGnMsN8Qs" +
                                                    "e306zdKDZK7OWLqfWSrV1HFh9yBpttWsTnneYv4m4WTeZJbQGexco4K+WXmF" +
                                                    "G5bvXkZlWtr7qsloNAu+Lgh8dTzswXlwsEEFw6wMVZjHUj2i6mlOloY5fB87" +
                                                    "7gMCYK3NMT5s+KqqdQoTpNmJnUb1rNzPLVXPAmmNkQctnCyaVijutUmVEZpl" +
                                                    "KU5aw3R9zhJQ1YuNQBZO5ofJhCSI0qJQlIric3nTHYf36Bt0SdicZoqG9tcB" +
                                                    "U1uIaQvLMIvpCnMYG1cln6ULzhyUCAHi+SFih+b0o1fuXtN29rxDc20Fms1D" +
                                                    "O5nCU8rRoTmfLE50rqtCM+pMw1Yx+CWei/Tvc1e6CiZU3gJfIi7GvcWzWz7c" +
                                                    "vvcY+0EiDb0kqhhaPgd5NFcxcqaqMetepjOLcpbuJfVMTyfEei+phXFS1Zkz" +
                                                    "uzmTsRnvJdWamIoa4hu2KAMicItqYazqGcMbm5QPi3HBJITUwkM64akhzk/8" +
                                                    "c0LlrTaku0wVqqu6IUPyMmopwzJTjNQQ7O5wjlojtqzkbW7kZDuvZzRjTLYt" +
                                                    "RTasrP+tGBaTwRLIIrlf1ZUeMYxjqpn/h5ICehobi0QgCIvDEKBB9WwwtDSz" +
                                                    "Uspk/p7uK2+lPpL8knD3iJN20BV3dcVRV9zRFQ90kUhEqGhBnU6MIUIjUOuA" +
                                                    "go2d/Q9v3HGwvQqSyxyrhu1F0nbw0TWkWzESASD0CthTICtbX37wQPzXV+9y" +
                                                    "slKeHr0rcpOzR8b2bXv8RolIpTCMjsFUA7L3IXj6INkRLr9KcpsOfPfziWcn" +
                                                    "jKAQS3DdxYdyTqzv9nAILENhaUDMQPyqZfRU6sxEh0SqATQAKDmFxAYMagvr" +
                                                    "KKnzLg8z0ZcacDhjWDmq4ZIHdA182DLGghmRG3PEeC4EZRYm/nx46txKEP+4" +
                                                    "Os/Ed4uTSxjlkBcCk3veO/v8qRdWr5OK4bup6EDsZ9wBg7lBkgxYjMH8V0f6" +
                                                    "nn7m8oEHRYYAxXWVFHTgOwHQACGDbX3i/K4vL108+pkUZBWHMzI/pKlKAWSs" +
                                                    "DLQAcGgAXhj7jq16zkirGZUOaQyT84+mFWtP/Xg45kRTgxkvGdb8u4Bg/pp7" +
                                                    "yN6PHvmlTYiJKHhwBZ4HZM4GzAskr7csuhvtKOz7dMnz5+hLgKuAZbY6zgQ8" +
                                                    "Rfx66ZyhebHUHODpqAv48kTzpZEXv3vTKZvw6RAiZgcnD/0dPzwpFR2h15Wd" +
                                                    "YsU8zjEqkmG2kzx/wy8Cz1/4YNLghAOjzQkXy5f5YG6aGJ7lM5klVPR8e2Li" +
                                                    "/dcmDjhuNJeeIN3QIL35+Z8fx498faECZEH+GZQLG2Vh4yrxjqNRYkeJWOvC" +
                                                    "1zKzbK0gZlqvZu97sGspgqzfNmtD+7/5VdhUBjoVwhHiH5SPv7gocecPgj+o" +
                                                    "fuReWiiHcOjwAt6bjuV+ktqjH0ikdpDEFLd93Ea1PNbYILRMttdTQotZsl7a" +
                                                    "/jhnfZePbovD+VCkNow7QRxgjNQ4bghBTSPucgs8URdqomGoiRAxSAiWdvFe" +
                                                    "ga/rvUqvNS11lGJvChHClZsFOplCU0dJ/HA8n5OFZceYc3ZhNi6Zru8SmXh0" +
                                                    "/+RUevMrayU3bW7jpJ4b5g0aG2VakaoqlFRyrN0vOs0gRIdef+M0/2T17U5O" +
                                                    "r5o+rcKM5/Z/v2jgzuEd/+EwWxryKSzy9fuPX7h3pfKURKr8SJc1z6VMXaXx" +
                                                    "bbAYdPv6QEmU2/wo40NavXB7/2UHShCwykX6wAxr2/E1ANmQZbwfMFMkQnk9" +
                                                    "i4m+UsMw/WKuYbGrNkwSEiU/4W4WpKkZTKT4eoiTKjDx6sxr9vatxTWvZVrz" +
                                                    "ukOK3eMisCw7g2UqvgDuozb0cWvT0xgHBdYQNHp4fLWWXSWd64/y1lRT3cKp" +
                                                    "rV+I1sW/otTDPSGT17RiYCgaR02LZVRhUL0DE04N65Uq1mk8OV4WcSCMzTn0" +
                                                    "u+D6HabnpBr/isk4J7OKyCB13FExEUxXAREOC6aHHzFxaiNAxh2ALJCi2vcI" +
                                                    "va+SLgbLW1zTvVLMOxf1lHJiauOmPVdufUXUdQ1c8MfHxbUObqlOA+eX8/Jp" +
                                                    "pXmyohs6f59zsn6FB1Nz8NXsdm0h25ZWbm66cyYX7cj4uwvfuePVqYuiu/oH" +
                                                    "4kPyUj8RAAA=");
}
