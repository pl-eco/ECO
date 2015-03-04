package org.sunflow.core;
import org.sunflow.SunflowAPI;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
final class InstanceList implements PrimitiveList {
    private Instance[] instances;
    InstanceList() { super();
                     instances = (new Instance[0]); }
    InstanceList(Instance[] instances) { super();
                                         this.instances = instances; }
    public final float getPrimitiveBound(int primID, int i) { return instances[primID].
                                                                getBounds(
                                                                  ).
                                                                getBound(
                                                                  i);
    }
    public final BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                             new BoundingBox(
                                                             );
                                                           for (Instance i
                                                                 :
                                                                 instances)
                                                               bounds.
                                                                 include(
                                                                   i.
                                                                     getBounds(
                                                                       ));
                                                           return bounds;
    }
    public final void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        instances[primID].
          intersect(
            r,
            state);
    }
    public final int getNumPrimitives() { return instances.
                                                   length;
    }
    public final int getNumPrimitives(int primID) { return instances[primID].
                                                      getNumPrimitives(
                                                        );
    }
    public final void prepareShadingState(ShadingState state) {
        state.
          getInstance(
            ).
          prepareShadingState(
            state);
    }
    public boolean update(ParameterList pl, SunflowAPI api) {
        return true;
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVZe2wcRxmfO79dx3acxnFC7CSOXdVxctdEBDW4tNgmDxsn" +
                                                    "ceM0iKuIO96bO2+yt7vdnbMvDqZpJJSoEhGPNKRQLAEpoSVNCiK0qKqUP4C0" +
                                                    "FCFaoaIK0RSQaEWIRP6gVBQo3zezr9tbnwsJlnZ2bma+b+b3vWd97hqpsi3S" +
                                                    "axraoaxm8AQr8MQBbXOCHzKZnRge2TxKLZulBzVq23thbFzpfKbpnfe+ONkc" +
                                                    "J9UpsoTqusEpVw3d3sNsQ5ti6RHS5I9u1VjO5qR55ACdosk8V7XkiGrzvhFy" +
                                                    "S4CUk64R9whJOEISjpAUR0j2+6uAaBHT87lBpKA6tx8knyOxEVJtKng8TtYU" +
                                                    "MzGpRXMOm1GBADjU4u99AEoQFyyy2sMuMZcAfrQ3efKr+5t/UEGaUqRJ1cfw" +
                                                    "OAocgsMmKdKQY7kJZtn96TRLp8hinbH0GLNUqqkz4twp0mKrWZ3yvMU8IeFg" +
                                                    "3mSW2NOXXIOC2Ky8wg3Lg5dRmZZ2f1VlNJoFrK0+VolwG44DwHoVDmZlqMJc" +
                                                    "ksqDqp7mZFWYwsPY9UlYAKQ1OcYnDW+rSp3CAGmRutOonk2OcUvVs7C0ysjD" +
                                                    "LpysmJcpytqkykGaZeOctIXXjcopWFUnBIEknCwNLxOcQEsrQloK6OfarrtO" +
                                                    "HNZ36HFx5jRTNDx/LRB1hIj2sAyzmK4wSdiwbuQUbX3heJwQWLw0tFiuefaz" +
                                                    "1z++vuPSi3LNhyLW7J44wBQ+rpyZaHxl5WDPlgo8Rq1p2Coqvwi5MP9RZ6av" +
                                                    "YILntXoccTLhTl7a87NPH3mKXY2T+iFSrRhaPgd2tFgxcqaqMWs705lFOUsP" +
                                                    "kTqmpwfF/BCpgf6IqjM5ujuTsRkfIpWaGKo2xG8QUQZYoIhqoK/qGcPtm5RP" +
                                                    "in7BJIRUw0M2wlND5J94c3JvctLIsSRVqK7qRhJsl1FLmUwyxUjaNGdqoDU7" +
                                                    "r2c0YzppW0rSsLLeb8WwWHJIeK/CMA4k0LTM/wfTAiJpno7FQMgrwy6ugXfs" +
                                                    "MLQ0s8aVk/mBrdfPj78c90zekQEn7bBNwtkmgdskgtuQWExwvxW3k+oD4R8E" +
                                                    "N4bJhp6xzww/cLyzAuzGnK4EycVhaSfgcc6wVTEGfV93+Y4rbd+6/1ji3bP3" +
                                                    "SINLzh+YI6nJpdPTD+976I44iRdHWMQEQ/VIPopx0Yt/XWHPiuLbdOztdy6c" +
                                                    "mjV8HysK2Y7rl1Ki63aGpW8ZCktDMPTZr1tNL46/MNsVJ5UQDyAGcgo2C+Gl" +
                                                    "I7xHkQv3ueEQsVQB4Ixh5aiGU24Mq+eTljHtjwizaBT9xaAUVAxphqfeMXLx" +
                                                    "xtklJra3SjNCLYdQiHC77ceXHrv4td4t8WBkbgrkujHGpZ8v9o1kr8UYjP/u" +
                                                    "9OhXHr127H5hIbBibdQGXdgOgteDykCsn3/xwdevvHHm13HPqkgBSG/zmUMo" +
                                                    "0CAcocq77tNzRlrNqHRCEwb7z6bujRf/cqJZKlGDEdcG1i/MwB9fPkCOvLz/" +
                                                    "7x2CTUzBVOQD9pdJ3Et8zv2WRQ/hOQoPv9r+2GX6DYiUEJ1sdYaJgEMcQHio" +
                                                    "hNBQj2g3hObuwGa1WTInBlaUqrbRUW1jpGqx6QrtFhMc0be75/c/AUcmibnv" +
                                                    "rP3lQ3Nrfw8SSZFa1Ybyot/KRmStAM1fz125+uqi9vPC5CsnqC3CTn043Zdm" +
                                                    "86IkLTA0eJjXIMQeeHIO5pyM2cM3Gl7HFMg4brC+idyk0pZysnzeUBuhlZ75" +
                                                    "tbINhReIpv/YrU0c/cO7wlZL4mFEiRCiTyXPPb5i8O6rgt4PTEi9qlCaWEDx" +
                                                    "Pu2mp3J/i3dW/zROalKkWXGK1n1Uy6P7p0C1tlvJQmFbNF9cdMkKo88LvCvD" +
                                                    "QTGwbTgk+gkN+rhaWJmMgthsKcSIcIdPCIpO0XZjc7vMWhzK6/yEpoKfV2VU" +
                                                    "nWoFGNGYnuWT5TUxaqk5qGWmnGIrOdty5eDjbz8t81pY7KHF7PjJR95PnDgZ" +
                                                    "D5Sva0sqyCCNLGEFrkXSHd6Hvxg8/8YH3QAHpDu0DDp11GqvkDJNDKRryh1L" +
                                                    "bLHtrQuzz3939piE0VJcvW2Fy8nTr/3rF4nTb74UUU5UgC/jjzvNgmfUcac2" +
                                                    "cNxAhkvUOlS4hs4w8rpzss5QjYR3u4DJQoR7tBdVGTtFuPDN8pEnv/csf6X3" +
                                                    "oxLBuvkVGCa8fPTPK/bePfnAf1FbrArJM8zyyZ3nXtp+m/LlOKnwrLvkmlJM" +
                                                    "1Fds0/UWg3uVvrfIsjukZQtZR5t1DLvbC2WyzESZuTQ2+8EhFNSRVCnIfVV0" +
                                                    "Ft2aM7nIezPPLfvhXWfn3hDZuyBZbS6zjYrNptJEJ4/d5ugb+/d4GaCBOGmg" +
                                                    "1skAteGs5/i7No9gOKkxLXWK4uWW1KnBIHynSJiO8UaLdEfp2cByO0qCu+dY" +
                                                    "oh5Am53vfie87szRk3Pp3U9sjDuSuReOxg1zg8ammBbYsVb0U540liH42+Fp" +
                                                    "caTREl3eRYc+YSMBVD1hr8WfO0Uj+Bwpo8uj2BzmZHGWcQ/8gJHXZSIfxmaX" +
                                                    "3GYUI61mUD5PiePDa8fBXnhaHXitNwderFh9bUH15eCSmNhJuaUWPizYf6EM" +
                                                    "6i9hc5yTRkD9KcPS0hKxy3hlCWMxr+rZAaOwIHp8yF5XDO77xtFXiAUVXtgt" +
                                                    "Md499JBQuruiM6J2gaLNlgEAPxcxcZSvl5HUN7E5BalJdUk9K4kykMopQ00v" +
                                                    "KKGlONjt1oXu+8YlFDz4uTJz57E5y0kzqH9XPleMaOcHP363c/zum2revuP+" +
                                                    "qAyG57D5/v+MQRjpx4isyon7vukuWvqlYmySoiP51nepDMifYPM8lB+mxeCq" +
                                                    "yYLEOPXtBXEuwcHl8GxwcG6IxInNeHQwLZMssKZg4BaYLNxlrcFlY/LdPzok" +
                                                    "tvl5Gai/wuYy1LB5Mw3oopyrZsIwNEb1BUEjXrIenk0O6E0fGHTwSK+Xmfst" +
                                                    "Nq+BZsD8BihUwtliC8xHlAecNAS/U2Fp0lbykVt+mFXOzzXVLpu77zfyGup+" +
                                                    "PK0bIbWZvKYFLw+BfjWYSUYV56uTVwlTvN4ENwlrD2IVvsQpr8hlf+TklsAy" +
                                                    "ELjTCy76E9TLsAi7b5mu0pv98lheigokkP2JkzPcX0UfVbC8Ff8QcEvRvPyX" +
                                                    "wLhyYW541+HrH3lC1LVQ0tGZGeRSC1dt+T3JK2fXzMvN5VW9o+e9xmfqut1C" +
                                                    "RdyxWgI+3hbQbfY/RQRwRX4ZAAA=");
}
