package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class CatmullRomFilter implements Filter {
    public float getSize() { return 4.0F; }
    public float get(float x, float y) { return catrom1d(x) * catrom1d(y);
    }
    private float catrom1d(float x) { x = Math.abs(x);
                                      float x2 = x * x;
                                      float x3 = x * x2;
                                      if (x >= 2) return 0;
                                      if (x < 1) return 3 * x3 - 5 * x2 +
                                                   2;
                                      return -x3 + 5 * x2 - 8 * x + 4; }
    public CatmullRomFilter() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXW2xURRie3d5L6bblViuUthSkFPZADCZYvJS1hcICTQsY" +
                                                    "i1Km58xuT3tunDPbLsUqkJgSHgjRgmC0DwaCKLcYCRpD0ieB4AvGaHwQfJOo" +
                                                    "PPCCJqj4z8zunt2z26IPbnJmz5n57//83/xz7h4qcGzUbJna3qhm0iCJ0+CA" +
                                                    "tjpI91rECW4Mr+7EtkOUkIYdZxvM9coNlwIPHh7tr/Cjwh40CxuGSTFVTcPp" +
                                                    "Io6pDREljALubJtGdIeiivAAHsJSjKqaFFYd2hJGM9JYKWoMJ02QwAQJTJC4" +
                                                    "CVKrSwVMM4kR00OMAxvU2YPeQL4wKrRkZh5F9ZlCLGxjPSGmk3sAEorZ9w5w" +
                                                    "ijPHbVSX8l34nOXwsWZp/N1dFZ/moUAPCqhGNzNHBiMoKOlBZTrR+4jttCoK" +
                                                    "UXpQpUGI0k1sFWvqCLe7B1U5atTANGaTVJDYZMwiNtfpRq5MZr7ZMZmadsq9" +
                                                    "iEo0JflVENFwFHyd6/oqPGxn8+BgqQqG2REskyRL/qBqKBQt9HKkfGzcBATA" +
                                                    "WqQT2m+mVOUbGCZQlcidho2o1E1t1YgCaYEZAy0U1UwplMXawvIgjpJeiqq9" +
                                                    "dJ1iCahKeCAYC0VzvGRcEmSpxpOltPzc27L2yD5jg+HnNitE1pj9xcBU62Hq" +
                                                    "IhFiE0MmgrFsWfg4nnv1kB8hIJ7jIRY0V16//+Ly2snrgubJHDRb+waITHvl" +
                                                    "U33lt+aHmtbkMTOKLdNRWfIzPOfbvzOx0hK3oPLmpiSyxWBycbLrq1f2f0x+" +
                                                    "9aPSDlQom1pMh31UKZu6pWrEXk8MYmNKlA5UQgwlxNc7UBG8h1WDiNmtkYhD" +
                                                    "aAfK1/hUocm/IUQREMFCVATvqhExk+8Wpv38PW4hhIrgQRI8BUj8+D9Fr0r9" +
                                                    "pk4kLGNDNUwJ9i7BttwvEdmUHKxbGmTNiRkRzRyWHFuWTDua+pZNm0igHraO" +
                                                    "FMJUj2lal6m384kg22XW/yw/zvyrGPb5IPTzvYWvQc1sMDWF2L3yeGxd2/0L" +
                                                    "vTf9qUJIRIaipaAxmNAYZBqDQmPQqxH5fFzRbKZZ5BeyMwh1DghY1tT92sbd" +
                                                    "hxryYGNZw/kQWkbaAF4mzGmTzZALBh0c8mTYkdUf7hwL/nHmBbEjpamROyc3" +
                                                    "mjwxfGDHmyv9yJ8Jwcw9mCpl7J0MOFMA2egtvVxyA2N3H1w8Pmq6RZiB6Qls" +
                                                    "yOZktd3gTYRtykQBtHTFL6vDl3uvjjb6UT4ABoAkxbCpAX9qvToyarwliZfM" +
                                                    "lwJwOGLaOtbYUhLkSmm/bQ67M3yHlLOhSmwWlkCPgRxq27+YPHn5veY1/nRU" +
                                                    "DqSdc92EihqvdPO/zSYE5n880fnOsXtjO3nygWJRLgWNbAxBxUM2IGJvXd/z" +
                                                    "w53bp771uxuGwtEX69NUOQ4ylrhaAA80wCSW1sbthm4qakTFfRph++7PwOJV" +
                                                    "l387UiESpcFMMs/LHy/AnX9iHdp/c9fvtVyMT2bnkeu5SyYCMMuV3GrbeC+z" +
                                                    "I37gmwUnr+EPAC4Bohx1hHDUQdwzxEMf5Blp4uMKz9pKNtRZWWtxPlOd+OIf" +
                                                    "9XxsZMNTIm7sdWk6pY+/z6FoXlZti1JmAV4w1RHEj89TB8cnlK2nV4myrMqE" +
                                                    "9TboWs5/99fXwRM/3ciBKCXUtFZoZIhoaTblMZUZcLCZn85uURw++8kVeqv5" +
                                                    "WaFy2dRI4GW8dvCXmm3P9+/+DyCw0OO8V+TZzedurF8iv+1Hean6z2o4Mpla" +
                                                    "0sMASm0CHZLBAspmSnmaa7kBlRAO9qBqeAoTxxH/Z6uzLDbOFtXKhqen2Tcv" +
                                                    "TbPWzoZWioqihHbDhoQENE3TGNuqDmf1UKKZkEar7gy+f/e8SIa38/AQk0Pj" +
                                                    "hx8Fj4z709qzRVkdUjqPaNG4mTNFTB7BzwfP3+xhPrAJcURXhRJ9Ql2qUbAs" +
                                                    "toXrpzOLq2j/+eLolx+NjvkTMVlDGbaZmGaXGp94LjNBs+EpTSSo9F8nyM8l" +
                                                    "+tnnJj5w0u3TpOplNnRRlAep4hyPNa+KTdbAU54wrzyneVMgBmwKy1aHIJJx" +
                                                    "j1U+F1KE2bunMbuPDT0UFcuY2qa+SpnC9jjcmLzdBAPS6qy7iuiv5QsTgeJ5" +
                                                    "E9u/5+djqgcugUY0AjLSqiy94gotm0RUbliJOPYs/hfNhYOix6HsNsJeuMkR" +
                                                    "QT8A1nrpKcpnf+lkOkUz0sggpIm3dCJAwzwgYq97rCQqV/Dzg11AgqLbjqMM" +
                                                    "mLcyQT/9PGVly++BSdyKiZtgr3xxYuOWffefOc1BsABukCMj/N4A1yDRJaSw" +
                                                    "r35KaUlZhRuaHpZfKlmcrJqM/sFj28Lcx2ybblF+MI58Pu+ztWcmbvNz/h93" +
                                                    "fysxoA8AAA==");
}
