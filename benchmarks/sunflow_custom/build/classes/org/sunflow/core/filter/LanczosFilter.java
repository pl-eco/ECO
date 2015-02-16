package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class LanczosFilter implements Filter {
    public float getSize() { return 4.0F; }
    public float get(float x, float y) { return sinc1d(x * 0.5F) * sinc1d(
                                                                     y *
                                                                       0.5F);
    }
    private float sinc1d(float x) { x = Math.abs(x);
                                    if (x < 1.0E-5F) return 1;
                                    if (x > 1.0F) return 0;
                                    x *= Math.PI;
                                    float sinc = (float) Math.sin(3 * x) /
                                      (3 *
                                         x);
                                    float lanczos = (float) Math.sin(x) /
                                      x;
                                    return sinc * lanczos; }
    public LanczosFilter() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1159026718000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWwURRyfu36X0i++KkJpS0FL4RZiMMHiR6ktFA5oWoqx" +
                                                    "RI7p7tx1273dZXauPYpVIDElPBCjBcFoHwwEUb5iJGgMSZ8Egi8Yo/FB8E2i" +
                                                    "8sALmqDif2bubu/2rlVfvGTndmf+//l//+Y/5+6hAoeiZtsy9kUMiwVInAUG" +
                                                    "jbUBts8mTmBzcG0Xpg7R2gzsODtgLqQ2XKp48PDNgUo/KuxDc7BpWgwz3TKd" +
                                                    "buJYxjDRgqjCnW03SNRhqDI4iIexEmO6oQR1h7UE0aw0VoYag0kVFFBBARUU" +
                                                    "oYLS6lIB02xixqJtnAObzNmLXkO+ICq0Va4eQ/WZm9iY4mhimy5hAexQzL93" +
                                                    "glGCOU5RXcp2aXOWwcealYl3dld+kocq+lCFbvZwdVRQgoGQPlQWJdF+Qp1W" +
                                                    "TSNaH6oyCdF6CNWxoY8KvftQtaNHTMxilKScxCdjNqFCpuu5MpXbRmMqs2jK" +
                                                    "vLBODC35VRA2cARsne/aKi3s4PNgYKkOitEwVkmSJX9INzWGlng5UjY2bgEC" +
                                                    "YC2KEjZgpUTlmxgmULWMnYHNiNLDqG5GgLTAioEUhhZOuyn3tY3VIRwhIYZq" +
                                                    "vHRdcgmoSoQjOAtD87xkYieI0kJPlNLic2/b+qP7zU2mX+isEdXg+hcDU62H" +
                                                    "qZuECSWmSiRj2YrgcTz/6mE/QkA8z0Msaa68ev+FlbVT1yXN4zlotvcPEpWF" +
                                                    "1FP95bcWtTWty+NqFNuWo/PgZ1gu0r8rsdISt6Hy5qd25IuB5OJU95cvH/iI" +
                                                    "/OJHpZ2oULWMWBTyqEq1orZuELqRmIRiRrROVEJMrU2sd6IieA/qJpGz28Nh" +
                                                    "h7BOlG+IqUJLfIOLwrAFd1ERvOtm2Eq+25gNiPe4jRAqggethKcAyZ/4Z0hT" +
                                                    "eh1IdwWr2NRNS4HkJZiqAwpRrVA/eHcgiumQo6gxh1lRxYmZYcMaURyqKhaN" +
                                                    "pL5VixIFNIEsUoLYVEctp0N8BXi22f+TnDi3t3LE54NQLPICgQE1tMkyNEJD" +
                                                    "6kRsQ/v9C6Gb/lRhJDzF0DIQF0iIC3BxASkukCEO+XxCylwuVgYbQjUERQ9w" +
                                                    "WNbU88rmPYcb8iDL7JF88DMnbQBLE7q0q1abiwydAv9USM+aD3aNB34/87xM" +
                                                    "T2V6GM/JjaZOjBzc+fpqP/Jn4jG3DaZKOXsXR9EUWjZ66zDXvhXjdx9cPD5m" +
                                                    "uRWZAfAJoMjm5IXe4I0CtVSiAXS626+ow5dDV8ca/Sgf0AMQk2HIcACjWq+M" +
                                                    "jIJvSYInt6UADA5bNIoNvpREvFI2QK0Rd0akRzkfqmWm8AB6FBS42/H51MnL" +
                                                    "7zav86dDdEXaoddDmCz4Kjf+OyghMP/Dia63j90b3yWCDxRLcwlo5GMblD9E" +
                                                    "Azz2xvW939+5feobv5swDM7BWL+hq3HYY7krBcDBAIDiYW3sNaOWpod13G8Q" +
                                                    "nnd/VCxbc/nXo5UyUAbMJOO88p83cOcf24AO3Nz9W63Yxqfyw8m13CWTDpjj" +
                                                    "7txKKd7H9Ygf/HrxyWv4fcBOwCtHHyUCgpCwDAnXB0REmsS4yrO2mg91dtZa" +
                                                    "XMzUJL7ER70YG/nwhPQbf30yndIn3ucxtCCrsGUpcwcvnu48EmfpqUMTk9r2" +
                                                    "02tkWVZnYnw7tDDnv/3zq8CJH2/kgJMSZtmrDDJMjDSd8rjIDDjYKo5qtyiO" +
                                                    "nP34CrvV/IwUuWJ6JPAyXjv088Idzw3s+Q8gsMRjvHfLs1vP3di4XH3Lj/JS" +
                                                    "9Z/VfWQytaS7AYRSAu2SyR3KZ0pFmGuFAlXgDv6gGngKE2eT+Oerc2w+zpXV" +
                                                    "yoenZsibF2dY6+BDK0NFEcJ6ICEhAE0zdMlUj8LBPZzoLJSx6jtD7909L4Ph" +
                                                    "bUM8xOTwxJFHgaMT/rRebWlWu5TOI/s1oeZs6ZNH8PPB8xd/uA18Qp7X1W2J" +
                                                    "pqEu1TXYNk/h+pnUEiI6fro49sWHY+P+hE/WMY5tFmbZpSYmns0M0Fx4ShMB" +
                                                    "Kv3XAfKLHf38c4sYBGnvDKF6iQ/dDOVBqATHP6pXncyf8oR65TnVmwYxICls" +
                                                    "qg+DJ+MerXwupEi198ygdj8f+gC2Hd1U12jTaB5naHZGI8ExtCbrziL7bPXC" +
                                                    "ZEXxgsne78TRmOqFS6AhDccMI63A0out0KYkrAudSuSJZ4u/SC4IlL0N47cS" +
                                                    "/iL0DUv6QbjneekZyud/6WRRhmalkYE3E2/pRACEeUDEX/faSUCuFEcHv4gE" +
                                                    "ZNcdRxkIb2fiffpRyitW3AeTkBWTN8KQenFy87b9958+LfCvAG6So6Pi/gDX" +
                                                    "IdkgpGCvftrdknsVbmp6WH6pZFmyYDJaB49uS3KfsO1Rm4kzcfSzBZ+uPzN5" +
                                                    "WxzxfwMAA5yRqA8AAA==");
}
