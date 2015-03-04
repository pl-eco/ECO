package org.sunflow.core;
import org.sunflow.image.Color;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public interface GlobalPhotonMapInterface extends PhotonStore {
    public Color getRadiance(Point3 p, Vector3 n);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVWTWxURRyf3X5uqd22UKhQCpRCUor7QggaLDFCU2hxoZsW" +
                                "SKyRZXbe7O6j89485s3SpahBEiPxQIyigok9QQyKSIwEPZBwEgheMEbjQfSs" +
                                "cuCiBzTxP/P27cdrG4822em8mf/3x+8/Vx6iBk+gQZezEznGZYIWZeIo25aQ" +
                                "J1zqJfYmt6Ww8Kg5zLDnHYCzNOm7Fv/z8dv59ihqnEJLseNwiaXFHW+Cepwd" +
                                "p2YSxSunI4zankTtyaP4ODYK0mJG0vLkUBItqWKVqD8ZmGCACQaYYGgTjJ0V" +
                                "KmB6gjoFe1hxYEd6x9BrKJJEjS5R5km0rlaIiwW2S2JS2gOQ0Ky+D4FTmrko" +
                                "0Nqy777P8xx+b9A498Hh9i/qUHwKxS1nUplDwAgJSqZQq03tDBXeTtOk5hTq" +
                                "cCg1J6mwMLNmtd1TqNOzcg6WBUHLQVKHBZcKrbMSuVaifBMFIrkou5e1KDOD" +
                                "r4YswznwdXnFV9/D3eocHGyxwDCRxYQGLPXTlmNKtCbMUfax/wUgANYmm8o8" +
                                "L6uqdzAcoE4/dww7OWNSCsvJAWkDL4AWiVYuKlTF2sVkGudoWqLuMF3KvwKq" +
                                "mA6EYpGoK0ymJUGWVoayVJWfh/t3nD3pjDpRbbNJCVP2NwNTb4hpgmapoA6h" +
                                "PmPrpuT7ePnNM1GEgLgrROzT3Hjl0fObe2/d8WlWLUAznjlKiUyTi5m2+z3D" +
                                "A9vrlBnNLvcslfwaz3X5p0o3Q0UXOm95WaK6TASXtya+efHUJ/T3KGoZQ42E" +
                                "s4INddRBuO1ajIo91KECS2qOoRh1zGF9P4aaYJ+0HOqfjmezHpVjqJ7po0au" +
                                "vyFEWRChQtQEe8vJ8mDvYpnX+6KLEGqCH4rArwv5f0vUIpFr5LlNDUywYznc" +
                                "gNqlWJC8QQlPC+pyY2R43MhAlPM2FtOe4RWcLOMzaVLwJLcNTxCDi1xwbBAu" +
                                "qLGH8QxmqTyX3NmH3bGgghOq8tz/QWdRxaF9JhKBFPWEAYIB1ShnJhVpcq6w" +
                                "a+TR1fS9aLlhShGUaABUJkoqE0plYjGVKBLRmpYp1X4hQBqnARAAKlsHJl/e" +
                                "e+RMXx1UoDtTr5JQ1B3aHXwAY8hEjQW7v7514fqHg9uj1bARrwLiSSr9Iuyo" +
                                "6D0gKIXzn8+n3n3v4ZsvaaVAsX4hBf1qHYaSBJwFvHrjzrGffnlw8fto2dA6" +
                                "CdhcyDCLSNSMMwBsmEiJYmWEqnYkovddEvXMi5sfsUnQQZW3qxfDAo1jF0+f" +
                                "mzPHL23xO7aztr9GYHx89sM/3ybO/3p3gZTFJHefYvQ4ZTWGgUqotJK2EcL3" +
                                "aZgc01OIAEi8dfnTG/L+4LO+yk2LD9Mw4+3Tv6088Fz+SBRFawei0g5HLYoz" +
                                "pcZYeVytCTkfFnl535W7ezaSd6KoroSGCyB/LdNQdRhAqaAwqhwVUHXSAkr7" +
                                "wi0gOKEmzLOK3k1r8fX0zVf7o6geIB3GmMQAOzAhesPKa1B4KChNpaoBgpDl" +
                                "wsZMXQVjqEXmBZ+pnOjebNP7DkiP+qEe+C0r4ZT+r26Xumpd5veypl+l1161" +
                                "rNO5japtn1rWq8raWGkEwFQGuK6S0X/QsblpZS2cYVS15N/xDVuu/3G23a8g" +
                                "BidBdjb/t4DK+ZO70Kl7h//q1WIiRM30SnNWyPweXVqRvFMIfELZUXz9u9UX" +
                                "buOPYOQAzHvWLNXIHfVdC/ppRXU/2YDxMGWgA7cG993z7g9R9f7YqgO3Q5M9" +
                                "o9chFdkS6qjvXWrZKtGSHJUT2LRUJSyo1bJh1KuIcOHOk1QEExZDRuV597wH" +
                                "mv+oIFfn4s0r5g7+qEuuPPhjMH2zBcaqKrq6uhtdQbOWNj/mV5Kr/43BIzUM" +
                                "PBLVq3/a4lGfLAneVpFJ1FTaVRONS1QHRGqbct0iqoFrNwze62vwQj9ag94u" +
                                "+M/WNPl8bu/+k4+evqSBogGeu7Oz+pEDbza/Ycr4sG5RaYGsxtGBx23XYhui" +
                                "pSy2qaWzqkuqbFuzcD2P2K7UFTj71Yovd3w890Bj/r92TX7YTQwAAA==");
}
