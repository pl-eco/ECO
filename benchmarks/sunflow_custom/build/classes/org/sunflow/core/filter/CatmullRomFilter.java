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
    public static final long jlc$SourceLastModified$jl7 = 1159026718000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XW2xURRie3d5L6Y1brVDaUpBS2AMxmGDxUtYWCgs0LcVY" +
                                                    "Isv0nNntac+Nc2bbpVgFElPCAzFaEIz2wUAQ5RYjQWNI+iQQfMEYjQ+CbxKV" +
                                                    "B17QBBX/mdnds3t2W/TFTc7sOTP//Pf/m3/O3UMFjo1aLFPbF9VMGiBxGhjU" +
                                                    "1gboPos4gc2htV3YdogS1LDj7IC5sNx4qeLBw7cGKv2osA/NwYZhUkxV03C6" +
                                                    "iWNqw0QJoQp3tl0jukNRZWgQD2MpRlVNCqkObQ2hWWlbKWoKJVWQQAUJVJC4" +
                                                    "ClKbSwWbZhMjpgfZDmxQZy96HflCqNCSmXoUNWQysbCN9QSbLm4BcChm3zvB" +
                                                    "KL45bqP6lO3C5iyDj7VIE+/urvw0D1X0oQrV6GHqyKAEBSF9qEwnej+xnTZF" +
                                                    "IUofqjIIUXqIrWJNHeV696FqR40amMZsknISm4xZxOYyXc+Vycw2OyZT006Z" +
                                                    "F1GJpiS/CiIajoKt811bhYUdbB4MLFVBMTuCZZLckj+kGgpFi707UjY2bQEC" +
                                                    "2FqkEzpgpkTlGxgmULWInYaNqNRDbdWIAmmBGQMpFNVOy5T52sLyEI6SMEU1" +
                                                    "XrousQRUJdwRbAtF87xknBNEqdYTpbT43Nu2/uh+Y5Ph5zorRNaY/sWwqc6z" +
                                                    "qZtEiE0MmYiNZStCx/H8q4f9CAHxPA+xoLny2v0XV9ZNXRc0T+ag2d4/SGQa" +
                                                    "lk/1l99aGGxel8fUKLZMR2XBz7Ccp39XYqU1bkHlzU9xZIuB5OJU91evHPiY" +
                                                    "/OpHpZ2oUDa1mA55VCWbuqVqxN5IDGJjSpROVEIMJcjXO1ERvIdUg4jZ7ZGI" +
                                                    "Q2gnytf4VKHJv8FFEWDBXFQE76oRMZPvFqYD/D1uIYSK4EESPAVI/Pg/RVGp" +
                                                    "14F0l7CMDdUwJUhegm15QCKyGe4H7w7o2B5yJDnmUFOXnJgR0cwRybFlybSj" +
                                                    "qW/ZtIkEmkAWSUFM9ZimdZt6B58IsISz/j9RcWZ15YjPBwFZ6IUDDSppk6kp" +
                                                    "xA7LE7EN7fcvhG/6U+WR8BdFy0FiICExwCQGhMSAVyLy+biguUyyiDrEbAiq" +
                                                    "H3CxrLnn1c17DjfmQbpZI/ngcEbaCPYm1GmXzaALEZ0cCGXI05oPd40H/jjz" +
                                                    "gshTaXo8z7kbTZ0YObjzjdV+5M8EZmYeTJWy7V0MTlOw2eQtyFx8K8bvPrh4" +
                                                    "fMx0SzMD6ROIkb2TVXyjNxC2KRMFMNRlv6IeXw5fHWvyo3yAEYBOiiHVAZXq" +
                                                    "vDIyKr81iaLMlgIwOGLaOtbYUhL6SumAbY64MzxDytlQLZKFBdCjIAfgji+m" +
                                                    "Tl5+r2WdPx2rK9JOvx5CReVXufHfYRMC8z+e6Hrn2L3xXTz4QLEkl4AmNgYB" +
                                                    "ByAa4LE3r+/94c7tU9/63YShcCDG+jVVjgOPZa4UQAkNkIqFtanX0E1Fjai4" +
                                                    "XyMs7/6sWLrm8m9HK0WgNJhJxnnl4xm4809sQAdu7v69jrPxyeyUci13yYQD" +
                                                    "5ric22wb72N6xA9+s+jkNfwBgCgAl6OOEo5FiFuGuOsDPCLNfFzlWVvNhnor" +
                                                    "ay3OZ2oSX/yjgY9NbHhK+I29Lk+n9PH3eRQtyKptUcrMwYumO5j4oXrq0MSk" +
                                                    "sv30GlGW1Zlg3w69zPnv/vo6cOKnGzkQpYSa1iqNDBMtTac8JjIDDrbyM9st" +
                                                    "iiNnP7lCb7U8K0SumB4JvBuvHfqldsfzA3v+Awgs9hjvZXl267kbG5fJb/tR" +
                                                    "Xqr+s9qQzE2t6W4AoTaBvslgDmUzpTzMdVyBKnAHe1ANPIWJQ4r/s9U5Fhvn" +
                                                    "implw9Mz5M1LM6x1sKGNoqIooT2QkBCA5hnaZVvV4QQfTrQY0lj1naH3754X" +
                                                    "wfD2Ix5icnjiyKPA0Ql/WtO2JKtvSt8jGjeu5mzhk0fw88HzN3uYDWxCHNzV" +
                                                    "wUT3UJ9qHyyLpXDDTGpxER0/Xxz78qOxcX/CJ+sowzYT0+xS4xPPZQZoLjyl" +
                                                    "iQCV/usA+TlHP/vcwgdO2jtDqF5mQzdFeRAqvuOx6lWzyVp4yhPqledUbxrE" +
                                                    "gKSwbHUYPBn3aOVzIUWovWcGtfvZ0EdRsYypbeprlGl0j8M9yttNMCCtybrB" +
                                                    "iK5bvjBZUbxgsvd7fj6mOuMSaE8jwCOtytIrrtCySUTlipWIY8/if9FcOCh6" +
                                                    "HMruKOyFqxwR9IOgrZeeonz2l06mUzQrjQxcmnhLJwI0zAMi9rrXSqJyJT8/" +
                                                    "2LUkIHrwOMqAeSsT9NPPU1a2/HaYxK2YuB+G5YuTm7ftv//MaQ6CBXCvHB3l" +
                                                    "twm4HIkuIYV9DdNyS/Iq3NT8sPxSydJk1WT0Dx7dFuc+Ztt1i/KDcfTzBZ+t" +
                                                    "PzN5m5/z/wDJkbAKtg8AAA==");
}
