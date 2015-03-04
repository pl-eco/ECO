package org.sunflow.image;
public class BlackbodySpectrum extends SpectralCurve {
    private float temp;
    public BlackbodySpectrum(float temp) { super();
                                           this.temp = temp; }
    public float sample(float lambda) { double wavelength = lambda * 1.0E-9;
                                        return (float) (3.74183E-16 * Math.
                                                          pow(
                                                            wavelength,
                                                            -5.0) /
                                                          (Math.
                                                             exp(
                                                               0.014388 /
                                                                 (wavelength *
                                                                    temp)) -
                                                             1.0)); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWxURRSe3f6XQn/4q7W0pS3EtrhXJGiwBIVNC8WFNm3B" +
                                                    "WJRl9u5se9v7x9zZdlusIokp4YEYLQgG+2AgCPIXI0FjSPqiQPAFYzQ+CMYX" +
                                                    "icgDDyIRFc/M3d27e3eL+mKTe3c6c86cc+Z855tzT91GeRZFLaahjvarBvOR" +
                                                    "GPMNqit9bNQklm9jYGUXphYJ+1VsWb0wF5Trz5Xevf/mQJkX5fehuVjXDYaZ" +
                                                    "YuhWN7EMdZiEA6jUmW1TiWYxVBYYxMNYijJFlQKKxVoDaFaKKkONgYQLErgg" +
                                                    "gQuScEFa60iB0myiRzU/18A6s3aiV5EngPJNmbvH0OL0TUxMsRbfpktEADsU" +
                                                    "8v+3QlBCOUZRXTJ2O+aMgA+0SJPvbC/7KAeV9qFSRe/h7sjgBAMjfahEI1qI" +
                                                    "UGttOEzCfahcJyTcQ6iCVWVM+N2HKiylX8csSknykPhk1CRU2HROrkTmsdGo" +
                                                    "zAyaDC+iEDWc+C8vouJ+iHWBE6sdYTufhwCLFXCMRrBMEiq5Q4oeZqjWrZGM" +
                                                    "sfF5EADVAo2wASNpKlfHMIEq7NypWO+XehhV9H4QzTOiYIWhqhk35WdtYnkI" +
                                                    "95MgQ5VuuS57CaSKxEFwFYbmu8XETpClKleWUvJze/Pq/bv0DbpX+Bwmssr9" +
                                                    "LwSlGpdSN4kQSnSZ2IolzYGDeMHFvV6EQHi+S9iWufDKneeW1UxftmUezSLT" +
                                                    "GRokMgvKR0NzrlX7m1blcDcKTcNSePLTIhfw74qvtMZMqLwFyR35oi+xON39" +
                                                    "xYu7T5JbXlTcgfJlQ41qgKNy2dBMRSV0PdEJxYyEO1AR0cN+sd6BCmAcUHRi" +
                                                    "z3ZGIhZhHShXFVP5hvgfjigCW/AjKoCxokeMxNjEbECMYyZCqAAeVAJPDrL/" +
                                                    "xC9Dg9KAoREJy1hXdEMC7BJM5QGJyEaQEtOQ2vydUghOeUDDdMiSrKgeUY2R" +
                                                    "oBy1mKFJFpUlg/YnpiVFAxRI61SAQ8gIj/aYcJg0qvk45sz/1VqMx1424vFA" +
                                                    "WqrdpAAaZIOhhgkNypPRdW13zgSvepNFEj81hhrAmC9uzCeM+TKMIY9H2JjH" +
                                                    "jdpph6QNQfkDMZY09by8ccfeejjsmDmSCyfOResh3LgnbbLhdziiQzChDECt" +
                                                    "fH/bhO/e8WdtoEozE3pWbTR9aOT1ra894UXedGbmkcFUMVfv4nya5M1Gd0Vm" +
                                                    "27d04ubdswfHDac206g+ThmZmrzk6905oIZMwkCizvbNdfh88OJ4oxflAo8A" +
                                                    "dzIMWAdaqnHbSCv91gSN8ljyIOCIQTWs8qUE9xWzAWqMODMCHHPEuBySMovX" +
                                                    "QjU8+fHiEL98da7J3/NsMPEsu6IQNN3+6fTh8++2rPKmMnppyh3ZQ5jND+UO" +
                                                    "SHopITD//aGutw/cntgmEAISDdkMNPK3H9gCUgbH+sblnd/duH70a6+DKgbX" +
                                                    "ZjSkKnIM9ljqWAEuUQGnPPeNW3TNCCsRBYdUwsH5R+mS5ed/2V9mZ1OFmQQY" +
                                                    "lv3zBs78I+vQ7qvbf6sR23hkfpc5kTti9gHMdXZeSyke5X7EXv9q0eFL+D2g" +
                                                    "WqA3SxkjgrE8yXppekg/QxUNKHY4fgdI4xU3ho7cPG2XjfvCcAmTvZP7Hvj2" +
                                                    "T3pTbtWGjIstVce+WQUYZtvgeQB/Hnj+4g8HDZ+wmbXCH6f3uiS/myZPz+KH" +
                                                    "uSVMtP90dvyzD8Yn7DAq0i+VNuiZTn/z55e+Qz9cycJZgD8DM+GjJHxsFm8f" +
                                                    "d0qcKBJrrfxVZ2asxcRM5b85+3beyKRQ1u+damjPj/eETxmkkyUdLv0+6dSR" +
                                                    "Kv+aW0LfqX6uXRvL5HBo+hzdJ09qv3rr8z/3ooI+VCbHO8qtWI3yGuuDLspK" +
                                                    "tJnQdaatp3dE9vXfmmS3ajceUsy6ecfJA4y5NB8Xu6iGX8FoITy5carJdVON" +
                                                    "B4mBX6jUi/cS/nosUekFJlWGMW9XwQzRhPQKQVCmMNaYkkLEAbdopm5LgO3o" +
                                                    "nsmpcOex5d44Mp5mqIgZ5uMqGSaqCw2L0m6uTaK/dLKw78SHF9i1lmds2DbP" +
                                                    "jBy34qU9P1f1rhnY8R/uq1pXTO4tT2w6dWX9UvktL8pJJjOjZU5Xak1PYTEl" +
                                                    "0OPrvWmJrEkmsjyRyOJ4Iouz3hlOQpw6jDObyJuQ2vKQQn2Bv7qB3y2smXan" +
                                                    "tyJL2TJUntGacMKtzPgesnt4+cxUaeHCqS3fiss22WcXQbMbiapqKpRTxvkm" +
                                                    "JRFF+FVkA9uG3EtgPqNXAi4Sv8LZbbYgfDvMShEEMMdHqUIhhnJAiA9lO9T5" +
                                                    "kLzMXsyOE6v+KB0mMZQGe9NdBA1pcBQfkwnoRO3PyaB8dmrj5l13njomcJgH" +
                                                    "n6FjY+LjA76l7J4iCb/FM+6W2Ct/Q9P9OeeKliTKag5/VcQbCZdvtdnv2zbN" +
                                                    "ZOKGHPtk4cerj09dFxf+31HusZ/lDwAA");
}
