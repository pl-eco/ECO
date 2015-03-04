package org.sunflow.image;
public final class XYZColor {
    private float X;
    private float Y;
    private float Z;
    public XYZColor() { super(); }
    public XYZColor(float X, float Y, float Z) { super();
                                                 this.X = X;
                                                 this.Y = Y;
                                                 this.Z = Z; }
    public final float getX() { return X; }
    public final float getY() { return Y; }
    public final float getZ() { return Z; }
    public final XYZColor mul(float s) { X *= s;
                                         Y *= s;
                                         Z *= s;
                                         return this; }
    public final void normalize() { float XYZ = X + Y + Z;
                                    if (XYZ < 1.0E-6F) return;
                                    float s = 1 / XYZ;
                                    X *= s;
                                    Y *= s;
                                    Z *= s; }
    public final String toString() { return String.format("(%.3f, %.3f, %.3f)",
                                                          X,
                                                          Y,
                                                          Z); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAK0YW2xcxXX22l4/4mT9ILFrEsdxHISTdG/TCiowTZtYDnG6" +
                                                    "JFYcomQRMeO7s+ubzH3k3ll749RtiIQc5SNAayCg4I8qEa9AUNWIVggpPy0g" +
                                                    "+AGhVv0oVP0pAvKRj1JUWuDMzH3t3fVCJK80587OnHPmnDnPey9fRw2ug7bY" +
                                                    "Fj1RoBZLkxJLH6V3pNkJm7jpPZk7xrDjktwwxa57ANYmtP5XU59/+dhUm4KS" +
                                                    "WdSJTdNimOmW6e4nrkWnSS6DUuHqCCWGy1Bb5iiexmqR6VTN6C4byqAVEVKG" +
                                                    "BjK+CCqIoIIIqhBB3RFiAdFKYhaNYU6BTeYeR79EiQxK2hoXj6EN5Uxs7GDD" +
                                                    "YzMmNAAOTfz/QVBKEJcc1BfoLnWuUPiJLerCU0fafleHUlmU0s1xLo4GQjA4" +
                                                    "JItaDWJMEsfdkcuRXBa1m4TkxomjY6rPCrmzqMPVCyZmRYcEl8QXizZxxJnh" +
                                                    "zbVqXDenqDHLCdTL64Tm/H8NeYoLoOuaUFep4S6+Dgq26CCYk8ca8Unqj+lm" +
                                                    "jqH1cYpAx4GfAwKQNhqETVnBUfUmhgXUIW1HsVlQx5mjmwVAbbCKcApDPUsy" +
                                                    "5XdtY+0YLpAJhrrjeGNyC7CaxUVwEoZWx9EEJ7BST8xKEftc33vPuZPmblMR" +
                                                    "MueIRrn8TUDUGyPaT/LEIaZGJGHr5syTeM0bZxSEAHl1DFnivPaLGz/b2nvt" +
                                                    "LYlzaxWcfZNHicYmtIuTq95bOzx4Vx0Xo8m2XJ0bv0xz4f5j3s5QyYbIWxNw" +
                                                    "5Jtpf/Pa/j8fPvUi+VRBLaMoqVm0aIAftWuWYeuUOPcSkziYkdwoaiZmbljs" +
                                                    "j6JGmGd0k8jVffm8S9goqqdiKWmJ/3BFeWDBr6gR5rqZt/y5jdmUmJdshNBK" +
                                                    "GKgDRh2SP/FkaK86ZRlExRo2ddNSwXcJdrQplWiW6mLDpmA1t2jmqTWjuo6m" +
                                                    "Wk4h+K8bYHL10OEsSGw5ae5X9rJzLHEd2mYSCbjetfHgphAXuy2aI86EtlDc" +
                                                    "OXLjlYl3lMDZPe3BreGMtHdGWpyR9s9AiYRgfQs/S1oN7vwYRC/ktdbB8Qf3" +
                                                    "PHSmH+6qZM/Uw4UpgNoPmngCjGjWcBjioyKRaeBn3b99YD79xXM/lX6mLp2P" +
                                                    "q1Kja+dnHj74qx8oSClPrFwhWGrh5GM8HQZpbyAeUNX4puY//vzKk3NWGFpl" +
                                                    "mdqL+EpKHrH98at3LI3kIAeG7Df34asTb8wNKKge0gCkPobBVSGr9MbPKIvc" +
                                                    "IT8Lcl0aQOG85RiY8i0/dbWwKceaCVeET6wS83Ywygruyp0wkp5viyff7bQ5" +
                                                    "vEX6ELdyTAuRZXf98drTV5/ZcpcSTcipSIkbJ0yGd3voJAccQmD97+fHfvPE" +
                                                    "9fkHhIcAxsZqBwxwOAzBDiaDa33kreN/++jDix8ogVclGFS94iTVtRLwuC08" +
                                                    "BVyUQjrith+43zSsnJ7X8SQl3Dn/l9q07epn59qkNSms+M6w9dsZhOvf24lO" +
                                                    "vXPkP72CTULjpSjUPESTF9AZct7hOPgEl6P08Pvrnn4TPwuZErKTq88SkXCQ" +
                                                    "0AyJq1eFqTYLmI7tbeOgz67YEws9lTZu9mzcXNXGHAzETqsTHOtA/MEajZGj" +
                                                    "G5Crp71ios51fHTswscvywCOV54YMjmzcPbr9LkFJVKeN1ZUyCiNLNFC5JVS" +
                                                    "xa/hl4DxFR9cNb4gU3THsFcn+oJCYdvcUTbUEkscsetfV+Zef35uXqrRUV6d" +
                                                    "RqD5evkv/383ff4fb1dJmhAJFhY+dbcAQto7a1hyJwc/qrSkNGX3d7HCLt4b" +
                                                    "RdLof/fRydP//EJIV5EIqxgmRp9VL1/oGd7+qaAPMxKnXl+qLCfQR4a0P3zR" +
                                                    "+LfSn/yTghqzqE3zmtSDmBZ53GehMXP9zhUa2bL98iZLdhRDQcZdG/eMyLHx" +
                                                    "XBhaBOYcm89bYumvld9yD4x6LzTq46GRQGKyR5D0C7iJg9v97NNoO/o05h0w" +
                                                    "ShwKTN4mbTly8yeNcZABZoeXgdm4zywbYVaqrovCp4PceXUT06jnIR4x65bq" +
                                                    "O0W0XDy9sJjbd2mb4jn0doaamWV/n5JpQiOskpxTWRNwn+i0Q+c5+8JLr7H3" +
                                                    "ttwt427z0g4fJ3zz9Cc9B7ZPPXQTpX99TKc4yxfuu/z2vbdpv1ZQXeCDFS8P" +
                                                    "5URD5Z7X4hB42zEPlPlfb2DILn67t8JIeYZMVS+/NQzGwaFSjdxSqLGnczDJ" +
                                                    "UH2BMOm8S1STI5Uid3oidy6/yMdr7IlFKkU+fJMid3kidy2/yCdr7M1xMC1F" +
                                                    "zn43kXlwo3We2L74yyByQqatsCo9UkPueQ5OMVRnFGkkD9UQmw+0AUa/J3b/" +
                                                    "8t/0ozX2HufgLOQeUzTC0FAJtB9zMCSZ/gTsMG3puW/VpZsv9sHY6umydfl1" +
                                                    "uVBjb5GDpxhqYpb82CCwVjPUJjpJXiDTcqNK3wBk/msabz67Kz7tyM8R2iuL" +
                                                    "qaauxfv/Kl48gk8GzfDeni9SGi2hkXnSdkheF0I2y4Jqi8clhtorXhehoIin" +
                                                    "kPGiRHyeoRURRCii3iyK9BI4HiDx6WW7iu6yOSihskJlx8vWxrICIj6E+cm+" +
                                                    "KD+FTWhXFvfsPXnjzkuicjRoFM/Oci5NGdQoX6iCgrFhSW4+r+TuwS9Xvdq8" +
                                                    "yS+EqzjoiLhKRLb11V82RgybideD2T90/f6e5xY/FG873wBwC3SnoRQAAA==");
}
