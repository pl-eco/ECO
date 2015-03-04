package org.sunflow.core;
import org.sunflow.core.accel.BoundingIntervalHierarchy;
import org.sunflow.core.accel.KDTree;
import org.sunflow.core.accel.NullAccelerator;
import org.sunflow.core.accel.UniformGrid;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
class AccelerationStructureFactory {
    final static AccelerationStructure create(String name, int n, boolean primitives) {
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
                            return AccelerationStructureFactory.
                              create(
                                null,
                                n,
                                primitives);
                        }
    }
    public AccelerationStructureFactory() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1169992200000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVXe2wURRif3qOlvZo+KKW8WvoAgba3mICJlARqLVC4Qr3S" +
                                                   "AgUs092569K93WV3\nrr0WghATQIgPoiaaKBJDUkAQEzRoggoBFOUfMFETEl" +
                                                   "BDoiaKiTFBjP7hNzP33F6L0Sad25353o/f\nfHvqLvLaFpop2346bBLb39LZ" +
                                                   "gS2bKC0atu31sNUrX/Hmd4yu0Q0Xygkgl6pQVBSQbUnBFEuqIrU9\n0RSzUL" +
                                                   "1paMNhzaB+EqP+7driuLzVgcVjBG44cq507zFPlQt5A6gI67pBMVUNvVUjEZ" +
                                                   "ui4sB2PIil\nKFU1KaDatCmAHiJ6NNJi6DbFOrV3oN3IHUC5psxkUlQdSCiX" +
                                                   "QLlkYgtHJK5e6uBqQcJki1Cs6kRp\nTqoDzoZMTjA7zhccSw1CJrHDbnCHWw" +
                                                   "Bez056Lbwd46rpPt796K6jJ9yoqAcVqXonEyaDJxT09aDC\nCIn0EctuVhSi" +
                                                   "9KASnRClk1gq1tQRrrUHldpqWMc0ahE7SGxDG2SEpXbUJBbXmdgMoEKZ+WRF" +
                                                   "ZWpY\nyRiFVKIpiTdvSMNhcLs85bZwdwXbBwcLVDDMCmGZJFg8A6oOGa9yci" +
                                                   "R9rFsDBMCaFyG030iq8ugY\nNlCpyKWG9bDUSS1VDwOp14iCFoqmjyuUxdrE" +
                                                   "8gAOk16KKpx0HeIIqPJ5IBgLRVOcZFwSZGm6I0tp\n+akvv3fg+OsfL+e17V" +
                                                   "GIrDH7C4Cp0sEUJCFiEV0mgvF+1P9y26boTBdCQDzFQSxomuec6wr89EmV\n" +
                                                   "oJmRhWZd33Yi01557eGq4M6VBnIzMyaZhq2y5Gd4ztuhI37SFDOha8uTEtmh" +
                                                   "P3F4Ifjppj0nyc8u\nVNCGcmVDi0agjkpkI2KqGrFWEp1YmBKlDeUTXWnh52" +
                                                   "0oD54DUPJid10oZBPahjwa38o1+DuEKAQi\nWIjy4VnVQ0bi2cS0nz/HTBT/" +
                                                   "88J/QfyZ/1K03C/ZUT2kGUOSbcmSYYWT77JhEalZlonGrAM3Onkp\nQ92vwK" +
                                                   "yih/2skkyKtkj9RoRIWMa6qhtSWIXelY1GhQyy3/8pP8Z8KB3KyWGg6GxuDf" +
                                                   "pilaEpxOqV\nR+98sat1zbMHROGwYo97T1EjqPXH1fqZWv9EalFODtdWxtSL" +
                                                   "REIaBqChAfoK53duXb3tQI0bKsgc\n8kAMGWkN+Bm3qVU2WlJd38YBUobSq3" +
                                                   "hr837//dFlovSk8cE5K7fv+ulrR38vXOBCruzIyXwF7C5g\nYjoY3CYRsc7Z" +
                                                   "a9nk/3qw/ezX127NS3UdRXVjwGAsJ2vmGmdWLEMmCsQzJf7YtCL3BtR92IU8" +
                                                   "gBCA\nitx+AJxKp46Mpm5KACTzJS+AfCHDimCNHSVQrYD2W8ZQaoeXSzFbyk" +
                                                   "TlsEQ6DOTYev+Z3IXfnPdd\n4R4nYLgo7aLrJFQ0dUmqDtZbhMD+rVc7Xnrl" +
                                                   "7v7NvAhEFaAYUM5NUUITawAkLDV1XXrEUNSQivs0\nwmro76I5j7z/y/PFIt" +
                                                   "ga7CRy1fBgAan9aY+jPdee+qOSi8mR2SWSsj5FJpyYnJLcbFl4mNkR2/vl\n" +
                                                   "rNc+w28AxgGu2OoIEVARd4gZ1cijOo+vDY4ziS01ILthnGLOcmX3yrtOhmui" +
                                                   "Oz7/kFvtw+l3f3r0\n27HZJHLJdU8GpbUovmRAGDudYrK1nKVgqrNpV2G7H4" +
                                                   "QturB2S7F24S9Q2wNqof1te50FwBHLSHCc\n2pt38+Kl8m033Mi1AhVoBlYE" +
                                                   "NgAUQ70Rux8wJ2YuW87NKB6axFYeF8StnR6PUizjjT3PydgBe2eN\ndxPyW3" +
                                                   "z/xt8K9+HLWwVolGbeLq0wgf04fIk8vPS577OAXj41zEaNDBItTScDq1kZYN" +
                                                   "XOh4RUqx48\n8fY5eqN+iVC5YHyccjIuWHJ0pGrJmUP/AaKqHEFwii4ZnPGk" +
                                                   "u1+96uJzjECnMfNPJlNTejhAKdgT\ntXQWWLZTyJMxO1laJSwdXfDvi5eWz1" +
                                                   "laAkvYWsvXuaLtXRSwQ9UxGJRr80ky5ugYN6dz8/epMErz\nWmPTl19MX5CQ" +
                                                   "ivRZ31IjMDMMcvy7s6/mo6tdb+4XuZg/wUCfztUrP/3tdwPuFy72CT7n3OQg" +
                                                   "Plx5\n7Iezd4JlooTEcFk7Zr5L5xEDJg9JkcmKuHoiDZz6cn31qd3B29wixt" +
                                                   "dEkVuNfzi0myJmQYry+gxD\nI1jnNKsnwJ5NbGmBoMsWgckpEd25/+6i5+QL" +
                                                   "2fKEUL3owa1M0cyJhgaGsRVjvj3EvCwHbu7cci/w\n1Z/8+kvOtD4YLENRTU" +
                                                   "sr0/SSzTUtElK5tz6BhCb/6YMicrpJkYf9cIuxIIM9XxoZxDb+lE7UD2kA\n" +
                                                   "IvaomllKVEzDmTDGPK3NKEX+eZdo3aj4wOuVN57ePDt2aP2LHA+88GE4MsIn" +
                                                   "efgwEdd4sv2rx5WW\nkHUdvXum+/w7jyXKJ+OCz0DUZGbLJsosQE72m7Y1Yl" +
                                                   "J+N458MPW9paNHbrv4Ff8PAZH8ZpUPAAA=");
}
