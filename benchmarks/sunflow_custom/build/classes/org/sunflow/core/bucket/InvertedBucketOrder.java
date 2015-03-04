package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class InvertedBucketOrder implements BucketOrder {
    private BucketOrder order;
    public InvertedBucketOrder(BucketOrder order) { super();
                                                    this.order = order; }
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = order.
                                                         getBucketSequence(
                                                           nbw,
                                                           nbh);
                                                       for (int i = 0; i <
                                                                         coords.
                                                                           length /
                                                                         2;
                                                            i +=
                                                              2) {
                                                           int src =
                                                             i;
                                                           int dst =
                                                             coords.
                                                               length -
                                                             2 -
                                                             i;
                                                           int tmp =
                                                             coords[src +
                                                                      0];
                                                           coords[src +
                                                                    0] =
                                                             coords[dst +
                                                                      0];
                                                           coords[dst +
                                                                    0] =
                                                             tmp;
                                                           tmp =
                                                             coords[src +
                                                                      1];
                                                           coords[src +
                                                                    1] =
                                                             coords[dst +
                                                                      1];
                                                           coords[dst +
                                                                    1] =
                                                             tmp;
                                                       }
                                                       return coords;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVYb2wcxRWfW9vnP3HiPyF/miZO4jgIx3ALraCiRrTJyUkc" +
                                                    "jtiyQyQOhWNub+688d7uZnbWvjg1gUhVIj5EqDU0VOAPKJQCIUGoEa0qpHyh" +
                                                    "gKgqgapW/VDS8qWoaaTmQykqbel7M7e3e3vnVP1US7s3O/PevPfmvfd7b3zh" +
                                                    "OmnzOBlxHet4yXJEilVE6qh1d0ocd5mXOpC5e5JyjxXSFvW8QzCXMwbf6Pns" +
                                                    "i6dnejWSzJK11LYdQYXp2N4U8xxrjhUypCecHbNY2ROkN3OUzlHdF6alZ0xP" +
                                                    "jGbIqgirIEOZQAUdVNBBBV2qoO8OqYBpNbP9cho5qC28Y+RxksiQpGugeoJs" +
                                                    "r9/EpZyWq9tMSgtghw78PgxGSeYKJ9tqtiubGwx+ZkRf+sGjvW+2kJ4s6THt" +
                                                    "aVTHACUECMmS7jIr5xn3dhcKrJAlfTZjhWnGTWqZC1LvLOn3zJJNhc9Z7ZBw" +
                                                    "0ncZlzLDk+s20DbuG8LhNfOKJrMKwVdb0aIlsHV9aKuycC/Og4FdJijGi9Rg" +
                                                    "AUvrrGkXBNka56jZOPQAEABre5mJGacmqtWmMEH6le8sapf0acFNuwSkbY4P" +
                                                    "UgTZtOKmeNYuNWZpieUE2Rinm1RLQNUpDwJZBFkXJ5M7gZc2xbwU8c/1g/ed" +
                                                    "PWHvtzWpc4EZFurfAUwDMaYpVmSc2QZTjN27Ms/S9W+f0QgB4nUxYkXz1ndu" +
                                                    "fPv2gSvvKZqvNqGZyB9lhsgZ5/NrPtycHr63BdXocB3PROfXWS7Df7K6Mlpx" +
                                                    "IfPW13bExVSweGXqFw8/8Sq7ppGucZI0HMsvQxz1GU7ZNS3G9zGbcSpYYZx0" +
                                                    "MruQluvjpB3GGdNmanaiWPSYGCetlpxKOvIbjqgIW+ARtcPYtItOMHapmJHj" +
                                                    "iksIaYeHfAOeNqL+5K8gXJ9xykynBrVN29EhdhnlxozODCfHmevoY+kJPQ+n" +
                                                    "PFOmfNbTPd8uWs58zvA94ZR1jxu6w0vBtG44nOl535hlQh+35yCmWGGP/Jzg" +
                                                    "BcZTGHvu/0VqBc+idz6RADdtjoOEBfm137GANmcs+XvGblzMfaDVkqZ6ioKM" +
                                                    "gNBUVWgKhaaU0FQToSSRkLJuQeEqHMCZswALAJjdw9NHDjx2ZrAF4tCdbwVP" +
                                                    "IOkgmF/VaMxw0iF2jEuENCCAN774yOnU5y9/SwWwvjLQN+UmV87NP3n45J0a" +
                                                    "0eoRGy2EqS5kn0ScreHpUDxTm+3bc/rTzy49u+iEOVtXAqpQ0siJUDAY9wV3" +
                                                    "DFYAcA2337WNXs69vTikkVbAF8BUQSEHAK4G4jLqIGE0gFe0pQ0MLjq8TC1c" +
                                                    "CjCxS8xwZz6ckUGyRo77wCmrMEcG4OmoJo38xdW1Lr5vUUGFXo5ZIeF778+u" +
                                                    "PHf5hyP3alGk74nUzmkmFG70hUFyiDMG878/N/n9Z66ffkRGCFDsaCZgCN9p" +
                                                    "QBFwGRzrd9879rurH5//tRZGlYBy6uct06jAHreGUgBjLMA59P3QQ3bZKZhF" +
                                                    "k+YthsH5z56dd13+y9le5U0LZoJguP2/bxDOf2UPeeKDR/8+ILdJGFjjQstD" +
                                                    "MnUAa8Odd3NOj6MelSc/2vLcu/QFgGCAPc9cYBLJEtV8QaXWCbK5IScjSSj9" +
                                                    "o0vSXfKdQgfKDYhc+zq+trkNaxU5s7GWmsMrZ9perOeRDP3HhJU/9cnn0uyG" +
                                                    "HGtSxmL8Wf3C85vS91+T/GGwI/fWSiN0Qe8T8n7t1fLftMHkOxppz5Jeo9pY" +
                                                    "HaaWjyGVhWbCC7otaL7q1usbA1UFR2vJvDmeaBGx8TQLIRPGSI3jrlhmdQeZ" +
                                                    "laxmVjKeWQkiB6OSZVC+d+LrtiCw211uzlHs2qB9QWfj6p0yIV0pbSjqQ7mI" +
                                                    "SbBlpdZDtk3nTy0tFyZeukvha399OR+DbvX13/zrl6lzf3i/SXXoFI57h8Xm" +
                                                    "mBULni11uP6g7MpCpz31ymtviQ9HvqlE7lo50OKM757686ZD98889j+g+daY" +
                                                    "8fEtX3nwwvv7bjW+p5GWmu8bGs16ptF6j3dxBp2xfajO7wM1v+NDBoMACH4b" +
                                                    "EDV0X5i2mjxP7ebJOMnNMrRcc9WeUF/svzr7/Kevq7ONZ16MmJ1ZeurL1Nkl" +
                                                    "LdJl72hodKM8qtOWKq9WJn4Jfwl4/o0PmoYTqtPqT1fbvW21fs91MSK330wt" +
                                                    "KWLvny4t/vzHi6e1KmqlBWmBewEOp+TEwZtA3MP4ekCQvhITChqn2TEfiyTI" +
                                                    "3rnyUUocViez/KMdvzq5vOOPcDJZ0mF6kP27ealJHx/h+euFq9c+Wr3loiza" +
                                                    "rXnqqXiIX4Aa7zd11xZpQbfK56maedEScI8bQEWuOVRoOBwGkCiaNrUALZIW" +
                                                    "s0uqN57C1xG30hBk1eKiyhJCIuCEYzOscMGaauxMJ1W7HsJipamO+5QBR5qF" +
                                                    "ddRV1k3WbHwdBTMMVETpDQ7c2rwkj5VdIYvowk83/OS+l5c/lj1BhTSpdGBl" +
                                                    "k/YV997YcJdW9z/j4nJPx4blh36rfBvc0TrholT0LSuK/5Fx0uWsaEpbOlU1" +
                                                    "UKfiC7Jhhb4afKUGUmuh6EHh3ji9IK34EyU7IciqCBnUi+ooSvQ4JBIQ4fCk" +
                                                    "Gzi2N3S6qoMVEgF0bMeiX3W9GSaR/D9FgK+++k9Fzri0fODgiRv3vCTBGnxI" +
                                                    "FxbkvRbiXbWlNYzevuJuwV7J/cNfrHmjc2cAB2vw1V/tRaO64fjYfwAH0Slm" +
                                                    "FRIAAA==");
}
