package org.sunflow.core;
import org.sunflow.core.accel.BoundingIntervalHierarchy;
import org.sunflow.core.accel.KDTree;
import org.sunflow.core.accel.NullAccelerator;
import org.sunflow.core.accel.UniformGrid;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
class AccelerationStructureFactory {
    static final AccelerationStructure create(String name, int n, boolean primitives) {
        if (name ==
              null ||
              name.
              equals(
                "auto")) {
            if (primitives) {
                if (n >
                      20000000)
                    return new UniformGrid(
                      );
                else
                    if (n >
                          2000000)
                        return new BoundingIntervalHierarchy(
                          );
                    else
                        if (n >
                              2)
                            return new KDTree(
                              );
                        else
                            return new NullAccelerator(
                              );
            }
            else {
                if (n >
                      2)
                    return new BoundingIntervalHierarchy(
                      );
                else
                    return new NullAccelerator(
                      );
            }
        }
        else
            if (name.
                  equals(
                    "uniformgrid"))
                return new UniformGrid(
                  );
            else
                if (name.
                      equals(
                        "null"))
                    return new NullAccelerator(
                      );
                else
                    if (name.
                          equals(
                            "kdtree"))
                        return new KDTree(
                          );
                    else
                        if (name.
                              equals(
                                "bih"))
                            return new BoundingIntervalHierarchy(
                              );
                        else {
                            UI.
                              printWarning(
                                Module.
                                  ACCEL,
                                "Unrecognized intersection accelerator \"%s\" - using auto",
                                name);
                            return create(
                                     null,
                                     n,
                                     primitives);
                        }
    }
    public AccelerationStructureFactory() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVXW2wUVRg+3d5LYdtyqwilN8BSuiOJmGCJWjYttC60oUBC" +
                                                    "CSyns2e7Q2fmDGfOtttiFUhMCQ/EaEEw2gcDQZRbjASNIemTQPAFYzQ+CL5J" +
                                                    "VB54QRNU/M+Z2dt0W/XJTfbM7H/+//z37/x74QEqtBlqtqg+MqBTHiAJHtiv" +
                                                    "rwvwEYvYga7Quh7MbBIJ6ti2twMtrNZf8T96/GaswoeK+tB8bJqUY65R095G" +
                                                    "bKoPkUgI+dPUdp0YNkcVof14CCtxrulKSLN5awjNyRDlqDGUNEEBExQwQZEm" +
                                                    "KG1pLhCaS8y4ERQS2OT2AfQayguhIksV5nFUl32IhRk23GN6pAdwQon4vROc" +
                                                    "ksIJhmpTvjs+T3P4RLMy8c7eik/ykb8P+TWzV5ijghEclPShcoMY/YTZbZEI" +
                                                    "ifShSpOQSC9hGta1UWl3H6qytQET8zgjqSAJYtwiTOpMR65cFb6xuMopS7kX" +
                                                    "1YgeSf4qjOp4AHxdlPbV8bBD0MHBMg0MY1GskqRIwaBmRjha7pVI+dj4CjCA" +
                                                    "aLFBeIymVBWYGAioysmdjs0BpZczzRwA1kIaBy0cLZnxUBFrC6uDeICEOar2" +
                                                    "8vU4W8BVKgMhRDha6GWTJ0GWlniylJGfB1s3HD9objZ90uYIUXVhfwkI1XiE" +
                                                    "tpEoYcRUiSNYvjp0Ei+6ftSHEDAv9DA7PNdeffjympqpmw7P0zl4uvv3E5WH" +
                                                    "1TP98+4sDTatzxdmlFjU1kTyszyX5d/j7rQmLOi8RakTxWYguTm17ctdhz4i" +
                                                    "v/hQWScqUqkeN6COKlVqWJpO2CZiEoY5iXSiUmJGgnK/ExXDe0gziUPtjkZt" +
                                                    "wjtRgS5JRVT+hhBF4QgRomJ418woTb5bmMfke8JC7qcQvmXuu3xyxJUYNYiC" +
                                                    "VWxqJlWgdglmakwhKg0zYlGlPdit9EOUYwZmg7Zix82oTofDatzm1FBspiqU" +
                                                    "DSTJikoZUdpUlejCI3C9V5Y/9EoHFl0wEhDVZ/1PehMiHhXDeXmQqqVeoNCh" +
                                                    "xzZTPUJYWJ2Ib2x/eCl825dqHDeSHLWA2oCrNiDUBmZTi/LypLYFQr1TFJDS" +
                                                    "QQAHgM3ypt49XfuO1udDNVrDBZAPwVoPIXBtaldpMI0gnRInVSjj6g92jwd+" +
                                                    "P/eSU8bKzHCfUxpNnRo+vPP1Z33Il43bwkcglQnxHoG2KVRt9PZrrnP94/cf" +
                                                    "XT45RtOdm3URuIAyXVIAQr03G4yqJAJxTB+/uhZfDV8fa/ShAkAZQFaOoRMA" +
                                                    "tGq8OrKAoTUJssKXQnA4SpmBdbGVRMYyHmN0OE2RZTJPLFVOxYgEegyU+Nzx" +
                                                    "+dTpq+82r/dlQrk/43LsJdwBhsp0/rczQoD+w6met088GN8tkw8cDbkUNIo1" +
                                                    "CDAB2YCIvXHzwPf37p75xpcqGJQA0ZXpwwE7dMAvkc3GHaZBI1pUw/06EeX2" +
                                                    "h3/F2qu/Hq9w8qMDJZneNf98QJr+1EZ06Pbe32rkMXmquLvSDqfZHL/np09u" +
                                                    "YwyPCDsSh79edvoGfh+gFeDM1kaJg1CuQ8KoFpmIZ+S6xrOniKXWmraXkJTq" +
                                                    "DM6GLApYs2yma0demWeOTExGus+udbqqKhvK22FSufjtn18FTv14KwcqlHJq" +
                                                    "tehkiOgZOkU3L8vq5i3yRk7X9LHzH1/jd5pfcFSunrmRvYI3jvy8ZPuLsX3/" +
                                                    "oYeXe5z3Hnl+y4Vbm1aqb/lQfqp9pw0Z2UKtmWEApYwA9JkioIJSJpNQIw2o" +
                                                    "hHCsEmloge8c9wqST7E73xLrArfZxFon10axrJKx9HFUZMtRjUOnaSbWE54a" +
                                                    "yZd8+fL3QphRZeWJMSfgjDmQjKZZBmOmGXBXD7nDhDJWdW/wvfsXncR4Jw8P" +
                                                    "Mzk6cexJ4PiEL2M8a5g2IWXKOCOa9HeuE58n8MmD71/iK+IiCM4VXRV054Ta" +
                                                    "1KBgWaKc62YzS6ro+Ony2Bcfjo373I54jqN8zR21O8TS5UQxxFFxP6U6wabk" +
                                                    "C87Sf71i2QDpUBkBW5LxXvnv7sUcfcvR0tmuUAEj1dOmemcSVS9N+ksWT+74" +
                                                    "Tl4KqWmxFEa2aFzXM2ozs06LLEaimnSm1MF6Sz76oGq8XnBUIB7S6l0O2x6O" +
                                                    "5mSwQejct0ymfRBpYBKv2MpRk86cmUBZEGV5Aashq2DlP6Bk98ad/0Bh9fJk" +
                                                    "19aDD58/K6GgEP47jY7KiRn+ADhXXQoB6mY8LXlW0eamx/OulK5I1kvWJeix" +
                                                    "bXnuS6PdsLiE+dHPFn+64dzkXXlZ/Q0Ttch9mg4AAA==");
}
