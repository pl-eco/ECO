package org.sunflow.image;
public class RegularSpectralCurve extends SpectralCurve {
    private final float[] spectrum;
    private final float lambdaMin;
    private final float lambdaMax;
    private final float delta;
    private final float invDelta;
    public RegularSpectralCurve(float[] spectrum, float lambdaMin, float lambdaMax) {
        super(
          );
        this.
          lambdaMin =
          lambdaMin;
        this.
          lambdaMax =
          lambdaMax;
        this.
          spectrum =
          spectrum;
        delta =
          (lambdaMax -
             lambdaMin) /
            (spectrum.
               length -
               1);
        invDelta =
          1 /
            delta;
    }
    public float sample(float lambda) { if (lambda < lambdaMin ||
                                              lambda >
                                              lambdaMax) return 0;
                                        float x = (lambda - lambdaMin) *
                                          invDelta;
                                        int b0 = (int) x;
                                        int b1 = Math.min(b0 + 1,
                                                          spectrum.
                                                            length -
                                                            1);
                                        float dx = x - b0;
                                        return (1 - dx) * spectrum[b0] +
                                          dx *
                                          spectrum[b1]; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1166422616000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcRxWfW9vnP3His9MkJiR2YjtRnbS3NKhBqatAerUb" +
                                                    "h0tj4iSoV4g7tzd33nj2T3Zn7Y1bkyZSSahKhMAtKSr+gFJKS5pUqFGLUKV8" +
                                                    "gbYqQmqFQHygAb5QESKRD5SKAuXNzN7u3d6d+SOEpZ2bnXnvzfv7e7O+eAO1" +
                                                    "uA7ablv0RIlaLE18lj5G70yzEzZx0/uyd05gxyWFDMWuewjWprSBl7re//Br" +
                                                    "0ykFJXNoNTZNi2GmW6Z7kLgWnSWFLOqKVkcpMVyGUtljeBarHtOpmtVdNpJF" +
                                                    "KypYGRrKllVQQQUVVFCFCuqeiAqYVhLTMzKcA5vMPY6+hBJZlLQ1rh5Dm6uF" +
                                                    "2NjBRiBmQlgAEtr4+xEwSjD7DtoU2i5trjH4ye3q4jePpn7QhLpyqEs3J7k6" +
                                                    "GijB4JAc6jSIkSeOu6dQIIUc6jYJKUwSR8dUnxd651CPq5dMzDyHhE7ii55N" +
                                                    "HHFm5LlOjdvmeBqznNC8ok5oofzWUqS4BLaujWyVFo7xdTCwQwfFnCLWSJml" +
                                                    "eUY3Cwz1xzlCG4c+CwTA2moQNm2FRzWbGBZQj4wdxWZJnWSObpaAtMXy4BSG" +
                                                    "1jcUyn1tY20Gl8gUQ71xugm5BVTtwhGchaE1cTIhCaK0PhalivjcuP/ucw+b" +
                                                    "e01F6FwgGuX6twFTX4zpICkSh5gakYyd27JP4bWvnVUQAuI1MWJJ88ojNz9z" +
                                                    "W9/VNyTNx+vQHMgfIxqb0i7kV729ITO8q4mr0WZbrs6DX2W5SP+JYGfEt6Hy" +
                                                    "1oYS+Wa6vHn14E8eePQFcl1BHeMoqVnUMyCPujXLsHVKnPuISRzMSGEctROz" +
                                                    "kBH746gV5lndJHL1QLHoEjaOmqlYSlriHVxUBBHcRa0w182iVZ7bmE2LuW8j" +
                                                    "hFrhQZ3wNCH5J34ZIuphF9JdxRo2ddNSIXkJdrRplWjWVB68O21gZ8ZVNc9l" +
                                                    "lqG6nlmk1pzqOppqOaXwXTcg+sBc8ih2Jm1woYNpxnNmSZqnm/3/OsjnFqfm" +
                                                    "EgkIxoY4FFCoor0WLRBnSlv07hm9eWnqLSUsjcBXDG2F89LBeWlxXrreeSiR" +
                                                    "EMfcws+V8YZozUDdAyJ2Dk9+cd9DZwfAy7491wyu5qQDYGugzKhmZSJwGBcQ" +
                                                    "qEGG9n7nwTPpD577tMxQtTGS1+VGV8/PnTpy8hMKUqohmRsHSx2cfYIDaQiY" +
                                                    "Q/FSrCe368x7719+asGKirIK4wOsqOXktT4QD4NjaaQA6BmJ37YJX5l6bWFI" +
                                                    "Qc0AIACaDEOSAx71xc+oqvmRMn5yW1rA4KLlGJjyrTLodbBpx5qLVkR+rBLz" +
                                                    "bgjKCl4E/fC0BVUhfvnuapuPt8h84lGOWSHweeyHV5++8q3tu5RKKO+qaI6T" +
                                                    "hElg6I6S5JBDCKz/+vzEN568ceZBkSFAMVjvgCE+ZgAmIGTg1sfeOP6ra+9e" +
                                                    "+LkSZRWDfunlqa75IGNrdAqACIVk5bEfOmwaVkEv6jhPCU/Ov3VtuePKH8+l" +
                                                    "ZDQprJST4bZ/LSBa/9g96NG3jv6lT4hJaLyJRZZHZNIBqyPJexwHn+B6+Kfe" +
                                                    "2fj06/jbgLGAa64+TwRUNQnLmoBpS+P0F0IkqC99d/BnJ5cGfwt65FCb7sJ1" +
                                                    "YI9TqtNlKnj+dPHa9XdWbrwkMq45j11R/h3x9lzbfauaqsiOTpu7fniZO5ej" +
                                                    "G9AGZoM+pS70XJt55r0XZYXHm1qMmJxdfPyj9LlFpaLzD9Y030oe2f2FZitl" +
                                                    "nn8Efwl4/sEfnt98QaJ/TyZoQZvCHmQLczYvp5Y4Yuz3lxd+9L2FM9KMnurG" +
                                                    "Nwr3uhd/8fefps//5s06CAulYmF54UvzJA4BchkvjvG4VODkXw/Q/OnffSCk" +
                                                    "1yBdHcfG+HPqxWfWZ3ZfF/wR5HDufr+2d0BORbw7XjD+rAwkf6yg1hxKacH9" +
                                                    "9QimHi/sHGSNW77Uwh23ar/6/iUvGyMhpG6IR7bi2DjYRR6FOacWCSzxzfYT" +
                                                    "SOBXRlAPiHELH24Vrlb4dJjHQTcx9QFDKDFLbFrQfZIPn/LFfBdDTVACfLrD" +
                                                    "9sOAKVKKeF/DgtrmFsFFzjIJh4nynuyOupUOL9Gw6dcJ/caq3rhfVFnk8sef" +
                                                    "//4r7O3td8ls29Y4TeKMr5/+w/pDu6cf+g86Yn8s9nGRz++/+OZ9W7WvK6gp" +
                                                    "jFzNbbyaaaQ6Xh0Ogc8H81BV1Pps8bODD0NCmW2hm5BwExIEn19m7wE+HIbA" +
                                                    "ajwOMmzg2/76sD5q2EwA8fyr616++7mld0Vf8UX/2ykGIVRd5sCjfNhk1+zJ" +
                                                    "CPeKtxYx3x023R5OMQhPc9B0m+NNN8he3CB7GWq1HX0W0IovjkH+trniZuYZ" +
                                                    "oQdTdr1Tb+fqBKe2NDi1tEzNhAe2U2zkC3i/bobO+u9PFF84eiRU3mKXEXor" +
                                                    "PMlAaLKBUOvfMaOlQCjD/4PTBEQch0Do5uy9MZl+lNG9QXrwem/0CSi6y4XT" +
                                                    "i0uFA8/eoQR5dhd4h1n27ZTMElohKiHmnwsV5g9aB08qUDhV90JXr8YCKIry" +
                                                    "/uQyeX+KD48AcLrYsOX31846VQD4V+/Tgddkb80/KuTHtXZpqatt3dLhX8qr" +
                                                    "SfkDuB2+QosepZWoXzFP2g4p6kK19nIP4D9fZqi75nMG4i5+hb6PScKvMLSi" +
                                                    "ghBKLJhVEj0B7QCI+PSrdhnh+2s/l6qM9VFV6O14IgxWgbn4L08ZeD35f54p" +
                                                    "7fLSvvsfvrnzWYHiAG54fp5LaYP7mLzzh+C9uaG0sqzk3uEPV73UvqWcWqv4" +
                                                    "0BNc9Ct14/Mv/BN5p3MDUxMAAA==");
}
