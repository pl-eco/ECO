package org.sunflow.image;
public class ConstantSpectralCurve extends SpectralCurve {
    private final float amp;
    public ConstantSpectralCurve(float amp) { super();
                                              this.amp = amp; }
    public float sample(float lambda) { return amp; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1166416160000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWwUVRS+u/1fSndb/motbSkFbYs7IkGDJShsWigutKGl" +
                                                    "0UVYbmfvtkPnj5m77VKsIomB8ECMFgSDfTAQBPmLkaAxJH1RIPiCMRofBOOL" +
                                                    "ROSBB5GIiufemd3Znd2ivthkpnfvPefec+75znfOnLqNikwDteqavGNA1miQ" +
                                                    "JGlwm7w0SHfoxAyuDS/txoZJYiEZm2YvzEXFxnP+u/ffHAx4UXEEzcCqqlFM" +
                                                    "JU01NxBTk4dJLIz8zmy7TBSTokB4Gx7GQoJKshCWTNoWRtMyVClqCqdMEMAE" +
                                                    "AUwQuAnCSkcKlKYTNaGEmAZWqbkdvYo8YVSsi8w8iuZlb6JjAyv2Nt3cA9ih" +
                                                    "lP3uA6e4ctJADWnfLZ9zHD7QKoy/syXwUQHyR5BfUnuYOSIYQeGQCCpXiNJP" +
                                                    "DHNlLEZiEVSpEhLrIYaEZWmU2x1BVaY0oGKaMEj6kthkQicGP9O5uXKR+WYk" +
                                                    "RKoZaffiEpFjqV9FcRkPgK+zHV8tDzvYPDjok8AwI45FklIpHJLUGEX1bo20" +
                                                    "j00vgAColiiEDmrpowpVDBOoyoqdjNUBoYcakjoAokVaAk6hqGbKTdld61gc" +
                                                    "wgMkSlG1W67bWgKpMn4RTIWiWW4xvhNEqcYVpYz43F6/fP9OdY3q5TbHiCgz" +
                                                    "+0tBqc6ltIHEiUFUkViK5S3hg3j2xb1ehEB4lkvYkrnwyp3nF9VNXrZkHs0j" +
                                                    "09W/jYg0Kh7tr7hWG2peVsDMKNU1U2LBz/Kcw7/bXmlL6pB5s9M7ssVganFy" +
                                                    "wxcv7TpJbnmRrxMVi5qcUABHlaKm6JJMjNVEJQamJNaJyogaC/H1TlQC47Ck" +
                                                    "Emu2Kx43Ce1EhTKfKtb4b7iiOGzBrqgExpIa11JjHdNBPk7qCKESeFA5PGXI" +
                                                    "+uP/KYoLG02Au4BFrEqqJgB4CTbEQYGIWrQfbndQwcaQKYgJk2qKYCbUuKyN" +
                                                    "CKYhCpoxkP4tKRB9IZXKPTrcoYHlUMIYJkGGN/1/OynJfA6MeDwQjlo3GciQ" +
                                                    "R2s0OUaMqDieWNV+50z0qjedHPZtUfQYHBi0DwzyA4N5D0QeDz9nJjvYCjkE" +
                                                    "bAhSH0ixvLln89qtexsLAGv6SCHcNhNtBG9ta9pFLeTwQyffXwSQVr+/aU/w" +
                                                    "3vHnLJAKU5N5Xm00eWjk9b7XnvQibzYrM+9gysfUuxmXpjmzyZ2N+fb177l5" +
                                                    "9+zBMc3Jyyyat+kiV5Ole6M7DoYmkhgQqLN9SwM+H7041uRFhcAhwJsUA86B" +
                                                    "kurcZ2SlfVuKQpkvReBwXDMULLOlFO/56KChjTgzHCAVfFwJQZnG8qDBTo5U" +
                                                    "kiC2OkNn75kWoFiUXV5wiu74dPLw+Xdbl3kz2dyfUR97CLW4odIBSa9BCMx/" +
                                                    "f6j77QO392ziCAGJ+fkOaGLvEDAFhAyu9Y3L27+7cf3o114HVRRKZqJflsQk" +
                                                    "7LHQOQV4RAawstg3bVQVLSbFJdwvEwbOP/wLFp//ZX/AiqYMMykwLPrnDZz5" +
                                                    "R1ahXVe3/FbHt/GIrI45njti1gXMcHZeaRh4B7Mj+fpXcw9fwu8BzQK1mdIo" +
                                                    "4WzlSedL80N6GUNSgF6Hbf4XxqpuDB25edpKG3excAmTveP7HgT3j3szKur8" +
                                                    "nKKWqWNVVQ6G6RZ4HsCfB56/2MNAwyYsVq0K2dTekOZ2XWfhmfcws/gRHT+d" +
                                                    "Hfvsg7E9lhtV2QWlHfql09/8+WXw0A9X8vAW4E/DlNsocBtb+DvIjOI3ivha" +
                                                    "G3s16DlrST5T/W/uvoM1MRmU9XuX3L/7x3vcphzSyRMOl35EOHWkJrTiFtd3" +
                                                    "sp9p1ydzeRwaPkf3qZPKr97G4s+9qCSCAqLN031YTrAci0AHZabIGzrOrPXs" +
                                                    "bsgq/W1pdqt14yHjWDfvOHGAMZNmY5+LaqrYLdfC47OpxuemGg/igxBXaeTv" +
                                                    "Bez1OI+Jl6IS3ZCGAU4s1pKK5SRFBVjhSks4T+n8zKaMSCKGu7lTNVwcc0d3" +
                                                    "j0/Euo4t9toAeYaiMqrpT8hkmMguUMzNKmDreIvpBGPfiQ8v0Gutz1robZka" +
                                                    "QG7FS7t/ruldMbj1P5StepdP7i1PrDt1ZfVC8S0vKkjHNKdrzlZqy46kzyDQ" +
                                                    "5qu9WfGsS8eTPWgOPAE7noG8pcMJiJOONsHxuHGpvofk64vs1QM0b0KorWZv" +
                                                    "SZ7shX47b5fCuLc657PIauXFMxP+0jkTG7/ldTfdbpdBzxtPyHImqjPGxbpB" +
                                                    "4hK3rczCuAW7zRRV5rROAFX+nxv8siW4laJpGYKAa3uUKQTlpACE2DBmuTsL" +
                                                    "ApjbmmU5m0RZ0NfdiTA/C5L8mzIFn4T1VRkVz06sXb/zztPHOBaL4Gt0dJR/" +
                                                    "g8AnldVepCE4b8rdUnsVr2m+X3GubEEqtSrYq8ruKVy21ecvve2KTnmxHP1k" +
                                                    "zsfLj09c57X/b9bUm0DsDwAA");
}
