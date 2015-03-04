package org.sunflow.core.accel;
import org.sunflow.core.AccelerationStructure;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
public class NullAccelerator implements AccelerationStructure {
    private PrimitiveList primitives;
    private int n;
    public NullAccelerator() { super();
                               primitives = null;
                               n = 0; }
    public void build(PrimitiveList primitives) { this.primitives = primitives;
                                                  n = primitives.getNumPrimitives(
                                                                   );
    }
    public void intersect(Ray r, IntersectionState state) { for (int i =
                                                                   0;
                                                                 i <
                                                                   n;
                                                                 i++)
                                                                primitives.
                                                                  intersectPrimitive(
                                                                    r,
                                                                    i,
                                                                    state);
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Ya2wUVRS+O33uUukDqFihQC1EQHfEqAnWV12LFFbaUCCx" +
                                                    "Ksvt7N126LyYudsuxfogMRB/EKNV0Wh/GIgvBGIkaowJf3xF/2iMxh8+4h+N" +
                                                    "yg9+iMT3OffO7OzOblH/2GTu3rn3nHvOueec75zpsTOkznPJWsc29o4aNk+y" +
                                                    "Ak/uNq5N8r0O85Kb0tcOUtdj2ZRBPW8brGW0rpPN5357ZKxFIfXDZAG1LJtT" +
                                                    "rtuWt5V5tjHBsmnSHK72Gcz0OGlJ76YTVM1z3VDTusd70mReCSsn3elABRVU" +
                                                    "UEEFVaig9oZUwHQRs/JmCjmoxb095D4SS5N6R0P1OFlRfohDXWr6xwwKC+CE" +
                                                    "RnzfAUYJ5oJLlhdtlzZXGPz4WnXmyZ0tr9aQ5mHSrFtDqI4GSnAQMkyaTGaO" +
                                                    "MNfrzWZZdpi0Woxlh5irU0OfEnoPkzZPH7Uoz7useEm4mHeYK2SGN9ekoW1u" +
                                                    "XuO2WzQvpzMjG7zV5Qw6Cra2h7ZKCzfgOhiY0EExN0c1FrDUjutWlpNlUY6i" +
                                                    "jd2bgQBYG0zGx+yiqFqLwgJpk74zqDWqDnFXt0aBtM7OgxROOuY8FO/aodo4" +
                                                    "HWUZThZH6QblFlDFxUUgCyeLomTiJPBSR8RLJf45s+WGQ/usjZYidM4yzUD9" +
                                                    "G4GpM8K0leWYyyyNScamNeknaPvbBxVCgHhRhFjSvH7v2Vuu6Dz9vqS5tArN" +
                                                    "wMhupvGMdmRk/sdLUqvX16AajY7t6ej8MstF+A/6Oz0FBzKvvXgibiaDzdNb" +
                                                    "373zgZfYjwpJ9JN6zTbyJsRRq2abjm4w93ZmMZdylu0ncWZlU2K/nzTAPK1b" +
                                                    "TK4O5HIe4/2k1hBL9bZ4hyvKwRF4RQ0w162cHcwdysfEvOAQQhrgITfC00jk" +
                                                    "n/jlxFTHbJOpVKOWbtkqxC6jrjamMs3OuMyx1b7UgDoCtzxmUnfcU728lTPs" +
                                                    "yYyW97htqp6rqbY7Giyrmu3iYRoz1C15w+jFGdpmu0kMO+f/FljAG2iZjMXA" +
                                                    "OUui0GBAVm20jSxzM9pM/ta+s8czHyrFVPHvjpNVIC/py0uivKSQl4zII7GY" +
                                                    "ELMQ5Ur/g/fGAQcAIZtWD92zadfBrhoIPGeyFq4eSbvAaF+ZPs1OhWDRLyBR" +
                                                    "g4hd/NxdB5Lnn79ZRqw6N7JX5SanD08+uOP+qxSilEM0GgdLCWQfRGAtAmh3" +
                                                    "NDWrndt84PtzJ56YtsMkLcN8HzsqOTH3u6JucG2NZQFNw+PXLKenMm9Pdyuk" +
                                                    "FgAFQJRTCHrAp86ojDIM6AnwFG2pA4NztmtSA7cCEEzwMdeeDFdEfMwX81Zw" +
                                                    "yjxMig54mvwsEb+4u8DBcaGMJ/RyxAqB1xvePP3UqafXrldKob25pFgOMS6B" +
                                                    "ojUMkm0uY7D+5eHBxx4/c+AuESFAcVk1Ad04pgA2qAi5h97f88XXXx35VAmj" +
                                                    "ikP9zI8YulaAM1aFUgBUDAA29H33dsu0s3pOpyMGw+D8vXnlulM/HWqR3jRg" +
                                                    "JQiGK/75gHD9klvJAx/u/KVTHBPTsKiFlodk8gIWhCf3ui7di3oUHvxk6VPv" +
                                                    "0WcBcwHnPH2KCegiwjIirl4VrlojxmRkbx0Oy52KvYJYWSzeFBC9eu4k2oC1" +
                                                    "uST5fh0wRvZ/e15YVJE+VUpShH9YPfZMR+qmHwV/GMfIvaxQCUjQx4S8V79k" +
                                                    "/qx01b+jkIZh0qL5TdIOauQxWoahMfCCzgkaqbL98iIvK1pPMU+XRHOoRGw0" +
                                                    "g0IghDlS4zwRSRqRIyvhiftJE48mTYyIyXrB0iXGlThcHsRsg+PqExQ7MJKA" +
                                                    "qQlVc4J5gnARZH0FAA8GNGk/WFuk268pV6oNnoSvVGIOpXpx6OEkZl04NIoi" +
                                                    "ZSehTrd9Pf7M969IXI7GQYSYHZx5+K/koRmlpH+7rKKFKuWRPZy46IukTX/B" +
                                                    "XwyeP/FBW3BB1vC2lN9ILC92Eo6D+b/iQmoJERu+OzH91gvTB6QZbeXtSx90" +
                                                    "56989sdHycPffFClLtZAayrvXujZXZJmscB1lbWzWDEhj4ZEeQDoR2WXztUT" +
                                                    "CkWP7J+ZzQ4cXaf4yb6Zkzi3nSsNNsGMSIIvLSurd4guOEysh198+XX+8drr" +
                                                    "pclr5vZ4lPG9/T90bLtpbNd/KKbLIjZFj3zxjmMf3L5Ke1QhNcX8rGjsy5l6" +
                                                    "yrMy4TK4QGtbWW52lhc0FZ5WPw1aqxa00H8htMakJ/H1ZkF19wWwdycOd3JS" +
                                                    "N5LX/a+b23DYKMF3Eye1E7aerQRnsbC9XN/18LT7+rb/a30V3/9+5C2siLyt" +
                                                    "dG+w2VWx2Y+fWZ6sUPhJyISg0QuYbOIAiRzXA1Zc2FWl/kAPEOkVsQIurvhS" +
                                                    "lV9X2vHZ5saLZ7d/Lrqf4hdQHD5DcnBKKSKXzOsdl+V0oVhc4rPMSmia2qv3" +
                                                    "r+As8Sv03SOp8/BtH6UG1+FPKRlYNK+EDPDbn5US7QOEACKc3usEF98iyj7W" +
                                                    "paSsSwVSkrzY+ZS+lbVBmJ/ifwBBLuXlfwEy2onZTVv2nb3uqEjMOs2gU1Pi" +
                                                    "mxE+gWUHWMzHFXOeFpxVv3H1b/NPxlcGODMfhza/7Yvotqx6d9RnOlz0M1Nv" +
                                                    "XPzaDc/PfiXas78BlNwLZpwRAAA=");
}
