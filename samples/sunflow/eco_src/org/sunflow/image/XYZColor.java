package org.sunflow.image;
final public class XYZColor {
    private float X;
    private float Y;
    private float Z;
    public XYZColor() { super(); }
    public XYZColor(float X, float Y, float Z) { super();
                                                 this.X = X;
                                                 this.Y = Y;
                                                 this.Z = Z; }
    final public float getX() { return X; }
    final public float getY() { return Y; }
    final public float getZ() { return Z; }
    final public XYZColor mul(float s) { X *= s;
                                         Y *= s;
                                         Z *= s;
                                         return this; }
    final public void normalize() { float XYZ = X + Y + Z;
                                    if (XYZ < 1.0E-6F) return;
                                    float s = 1 / XYZ;
                                    X *= s;
                                    Y *= s;
                                    Z *= s; }
    final public String toString() { return String.format("(%.3f, %.3f, %.3f)",
                                                          X,
                                                          Y,
                                                          Z); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1167328438000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVYe2wUxxmfe/jt6PwAm4fB2Bgo2Nw2UakaTJU4rgGHAxwb" +
                                                   "DD5CnPHu3Hnx3u5m\nd+58NjQlrRSTR5OiJFJSNQRFpBBKmkhJRKOmKRHkUd" +
                                                   "xKpFJTCSk0EVJSqU2lqlJK1f7Rb2b2bu/2\nzkdS05N2bnbne8zve823e+Zz" +
                                                   "VGZbqEW2w3TKJHa4d2gAWzZRejVs27vg0aj8TlnVwMltuuFHvgjy\nqwpFoY" +
                                                   "hsSwqmWFIVqf873WkLdZqGNhXXDBomaRo+oG1w5N0Z2VAgcM+xsw0PnAi2+l" +
                                                   "FZBIWwrhsU\nU9XQ+zSSsCmqixzAKSwlqapJEdWm3RF0E9GTiV5DtynWqX0f" +
                                                   "uh8FIqjclJlMitoiGeUSKJdMbOGE\nxNVLA1wtSGi0CMWqTpSerDrg7MrnhG" +
                                                   "07fIOF1CCkki0OAxy+A0C9IotaoC2AagZODX/z0PEXAygU\nRSFVH2LCZEBC" +
                                                   "QV8U1SZIYoxYdo+iECWK6nVClCFiqVhTp7nWKGqw1biOadIi9iCxDS3FCBvs" +
                                                   "pEks\nrjPzMIJqZYbJSsrUsLI2iqlEUzJ3ZTENxwF2kwtbwN3MngPAahU2Zs" +
                                                   "WwTDIswQlVB4+3ejmyGDu2\nAQGwViQIHTeyqoI6hgeoQfhSw3pcGqKWqseB" +
                                                   "tMxIghaKlswplNnaxPIEjpNRihZ56QbEElBVcUMw\nFooWesm4JPDSEo+Xcv" +
                                                   "zT2fTFkVM/eet2HttBhcga2381MC33MA2SGLGILhPBeC0ZfrJ/JNniRwiI\n" +
                                                   "F3qIBU3PqrO7I3/+daugWVqEZufYASLTUXnH0dbBg1sMFGDbqDQNW2XOz0PO" +
                                                   "02HAWelOm5C1TVmJ\nbDGcWTw3+O7I4dPkL35U3Y/KZUNLJiCO6mUjYaoasb" +
                                                   "YQnViYEqUfVRFd6eXr/agC5hEIefF0Zyxm\nE9qPghp/VG7wezBRDEQwE1XB" +
                                                   "XNVjRmZuYjrO52kTIXQTXKgBrgASP/5P0fqwZCf1mGZMSrYlS4YV\nz96rCf" +
                                                   "CotHckChsyrDALG5OifmncSBAJy1hXdUOKq5CosrFeISn2/1WEpdnuGiZ9Pl" +
                                                   "buvGmrQcRv\nNTSFWKPyyasXD/Vte+iICAkWxg4uCFjQEXZ0hLmOcEYH8vm4" +
                                                   "6AVMl/AHWHMC8hIqWO3aof133nuk\nHayQNieDYAo/kLYDAmcDfbLR6yZvP6" +
                                                   "9zMkTQouf3zYSvnbxNRJA0d40tyl1z6aXZ4/+oXedH/uIF\nkAGDElzNxAyw" +
                                                   "qpktbB3elCkm/28Pb3/1w9mPvuYmD0UdBTldyMlyst3rAsuQiQJVzhV/YnEo" +
                                                   "sAcN\nH/WjICQ6FDe+f6gby7068nKzO1PnGJaKCKqJGVYCa2wpU5yq6bhlTL" +
                                                   "pPeGzU8XkjOKeGBSublDvR\ny//Z6kKTjU0ilpi3PSh4Hb32g/Kv//HNmne4" +
                                                   "WTIlN5RzqA0RKhK43g2WXRYh8PyjpweeeOrzmX08\nUkSo+CicdMkxTZXTwL" +
                                                   "LaZYG406B6MEd27NYThqLGVDymERZx/wmtuvn1vz5WJ1yjwZOMZ7uuL8B9\n" +
                                                   "vvgOdHj2nn8u52J8Mjs5XBgumUDT6ErusSw8xfaRfuD3y555Dz8LhQ2Kia1O" +
                                                   "E14fEEeGuB0lbvd1\nfAx71m5mQzvI7poj9Iuc06PyodPx9uR9v3mD77oG5x" +
                                                   "74uW7Yjs1u4Xk2rGTWbfZm71ZsjwPdN87t\nuLtOO/dvkBgFiTKcj/ZOC8pF" +
                                                   "Os+JDnVZxeW3zzfd+0EA+Tejas3AymbM4x9VQeARexwqTdq87XYe\nW3WTlW" +
                                                   "zkkBE3whLHAPympTAqq5yorCoalWxY7TFpgEsMAMBFuY2epSbgwEjxrLn6YP" +
                                                   "uv3t/93Iyo\nNGtLdHO5XKPy9/708UTg8bfHBJ/30PQQH11+4tNXrw4uEFEp" +
                                                   "OouVBYd7Lo/oLjiykMk81FZKA6e+\n0Nl25v7BK86OGvLPyD7oIz+bOk/WbP" +
                                                   "rhJ0UKPGSrgXmq9PCBK95UIkC3sOFWvnQLGzYKr224rnPT\nOXfMMSUsvpm1" +
                                                   "cG5ZHB3rOhW5uPNZvv05q3oRZ3jkTL+1+9i139ErXI5bXhl3W7rwjIS21+X9" +
                                                   "1oep\n+vJXnkv4UUUU1clOYz6MtSQrYlHoI+1Mtw7Ne956fk8oGqDu7PHR4o" +
                                                   "2GHLXewu66DuaMms1rPbW8\nlll7CVxBJ2uC3qzxIT65i7N08HFNtvJWmJaa" +
                                                   "wqxZR7692bhocB0+eD2HR776VkbYMAQaR4ppjP4f\nNN6T0RgtpnG0hMZ0od" +
                                                   "n8LI9UHfNWem06v6ZZaNlc3TjP3pm9f699EF/Y73cSrJdC0TTM9RpJES1H\n" +
                                                   "VDmTlNdAbefvH26MPvziz87SDzo3ijqwbu788jKu23h8unXjy4/8D21Tqweb" +
                                                   "V3R9auldgXH1fT9/\nRRIhX/Bqlc/UnR/o1bCfpKXvygv3FVmP1zMrL4Mr5H" +
                                                   "g8VLx1KXQam5McjxWvd7TEWooNBkXBOKEi\nV9wQMr9sXeQ3E4V4Gh08jTcW" +
                                                   "z+ESa99nw0GBZ8SD59B88TQ7eJpvLJ5HS6w9xoYZgSfqwXNkPnhW\nw7XUwb" +
                                                   "N0/nh8ovjyHXL2p0uA+jEbnqAokEhq9ncZH1qc0+PsMHT+vqvKLGPSmQ6PnT" +
                                                   "1hi8RY/8pe\nPdJTn6y5vOK3db2zotMdp2hVzinlUEr9esqQeQ3YinUF3qVF" +
                                                   "49tSVOEeC5smsS59/On+xzs/e5eV\nIdM1+JPzMXgbXO2OwdtvbACdLrF2hg" +
                                                   "0vQEHW+RsVNPOc7NtOct8BoZUyVMUF+dP5gGTAuhyQXTcW\n5Bsl1n7Jhtco" +
                                                   "qqSG+GDFqZopqnMjJ2eB43z9S3d9IDfzxYBFzqKC74fim5ccuXzw7i8if/gX" +
                                                   "f/fN\nfpeqiaDKWFLTchufnHm5CYGqchQ1og0y+d8FiuoLvlzAIc3/+f7OC8" +
                                                   "L3KKrJIYT2x5nlEl2EZAMi\nNp01ixhHtHT5xz7DujLvBOYfaTOnZFJ8ph2V" +
                                                   "9760b0X6kV0/4kdvmazh6WkmpjqCKsRbfPakbZtT\nWkbWJfTKy8Nv/vzWTC" +
                                                   "fB3/IW5EROXvDdIlZL+BFO9+Kvzn0Jk/KX3elfNL+26eSxKzzVzf8Cq67X\n" +
                                                   "KFsXAAA=");
}
