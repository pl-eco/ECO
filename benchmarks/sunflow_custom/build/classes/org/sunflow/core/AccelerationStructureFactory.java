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
      1169992200000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWwURRyfXr9L4dryVRFKvwBL6a0kYoIlarm00HpAQ4GE" +
                                                    "Ejimu3Pt0rndZXauvRarQGIgPBCjBcFoHwwEUb5iJGgMSZ8Egi8Yo/FB8E2i" +
                                                    "8sALmqDif2b3bu+216ovXnJzuzP/74/f/O/CA1RoM9RsmXSkn5o8RJI8tI+u" +
                                                    "CfERi9ihrsiabsxsooUptu1tsBdV668EHz1+c6AigIp60VxsGCbHXDcNeyux" +
                                                    "TTpEtAgKervtlMRtjioi+/AQVhJcp0pEt3lrBM3KYOWoMZIyQQETFDBBkSYo" +
                                                    "bR4VMM0mRiIeFhzY4PZ+9BrKi6AiSxXmcVSXLcTCDMddMd3SA5BQIt53gFOS" +
                                                    "OclQbdp3x+cpDp9oVsbf2VPxST4K9qKgbvQIc1QwgoOSXlQeJ/E+wuw2TSNa" +
                                                    "L6o0CNF6CNMx1Uel3b2oytb7DcwTjKSDJDYTFmFSpxe5clX4xhIqN1navZhO" +
                                                    "qJZ6K4xR3A++LvB8dTzsEPvgYJkOhrEYVkmKpWBQNzSOlvo50j42vgIEwFoc" +
                                                    "J3zATKsqMDBsoCondxQb/UoPZ7rRD6SFZgK0cLRoWqEi1hZWB3E/iXJU7afr" +
                                                    "do6AqlQGQrBwNN9PJiVBlhb5spSRnweb1x0/YGw0AtJmjahU2F8CTDU+pq0k" +
                                                    "RhgxVOIwlq+MnMQLrh8NIATE833EDs21Vx++vKpm8qZD83QOmi19+4jKo+qZ" +
                                                    "vjl3Foeb1uYLM0os09ZF8rM8l+Xf7Z60Ji3ovAVpieIwlDqc3PrlzoMfkV8C" +
                                                    "qKwTFakmTcShjipVM27plLANxCAMc6J1olJiaGF53omK4TmiG8TZ3RKL2YR3" +
                                                    "ogIqt4pM+Q4hioEIEaJieNaNmJl6tjAfkM9JC7mfQviWuc/ylyOqbLeh3BWs" +
                                                    "YkM3TAWKl2CmDihENaN9EN2BOGaDtqImbG7GFTthxKg5rNhMVUzWn35XTUaU" +
                                                    "NlUlVHgCLvfIsoce6cCi+kdCouqs/1lfUvhfMZyXB6lZ7AcGCj210aQaYVF1" +
                                                    "PLG+/eGl6O1AulHcyHHUAmpDrtqQUBuaSS3Ky5Pa5gn1ThFACgcBDAAmy5t6" +
                                                    "dnftPVqfD9VnDRdA/AVpPXju2tSummEPMTolLqpQttUf7DoS+v3cS07ZKtPD" +
                                                    "e05uNHlq+NCO158NoEA2TgsfYatMsHcLdE2jaKO/P3PJDR65/+jyyTHT69Qs" +
                                                    "4HcBZCqnAIB6fzaYqRIN4uiJX1mLr0avjzUGUAGgCiApx1D5AFI1fh1ZQNCa" +
                                                    "AlXhSyE4HDNZHFNxlELCMj7AzGFvR5bJHLFUORUjEugzUOJxx+eTp6++27w2" +
                                                    "kAndwYzLsIdwBwgqvfxvY4TA/g+nut8+8eDILpl8oGjIpaBRrGGABcgGROyN" +
                                                    "m/u/v3f3zDeBdMGgJLAu94QDVlDAK5HNxu1G3NT0mI77KBHl9kdw2eqrvx6v" +
                                                    "cPJDYSeV3lX/LMDbf2o9Onh7z281UkyeKu4qz2GPzPF7rie5jTE8IuxIHvp6" +
                                                    "yekb+H2AUoAvWx8lDiK5DgmjWmQinpHrKt+ZIpZaa8pZUu5UZ1A2ZO2ANUum" +
                                                    "u2bkFXnm8PiEtuXsaqerqrKhux0mk4vf/vlV6NSPt3KgQik3rRZKhgjN0Cm6" +
                                                    "eUlWN2+SN7BX08fOf3yN32l+wVG5cvpG9jPeOPzzom0vDuz9Dz281Oe8X+T5" +
                                                    "TRdubViuvhVA+en2nTJUZDO1ZoYBlDIC0GeIgIqdMpmEGmlAJYRjhUhDC3xn" +
                                                    "uVeO/BWncy2xznObTax1cm0UywoZywBHRbYczTh0mm5gmvTVSL6ky5fv82Em" +
                                                    "lZUnxpqQM9ZAMppmGISZHoe7ecgdHpSxqnuD792/6CTGP2n4iMnR8WNPQsfH" +
                                                    "AxnjWMOUiSiTxxnJpL+znfg8gU8efP8SXxEXseFcyVVhdy6oTQ8GliXKuW4m" +
                                                    "s6SKjp8uj33x4diRgNsRz3GUr7ujdYdYupwoRjgq7jNNSrAh6cIz9F+PWNZB" +
                                                    "OlRGwJZUvJf/u3sxR99ytHimK1TASPWUKd6ZPNVLE8GShRPbv5OXQno6LIUR" +
                                                    "LZagNKM2M+u0yGIkpktnSh2st+RPL1SN3wuOCsSPtHqnQ7abo1kZZBA69ymT" +
                                                    "aC9EGojEI7Zy1KQzVyZRFkRZfsBqyCpY+Y8n1b0J5z9PVL080bX5wMPnz0oo" +
                                                    "KIT/SqOjckKGgd+56tIIUDettJSsoo1Nj+dcKV2WqpesS9Bn29Lcl0Z73OIS" +
                                                    "5kc/W/jpunMTd+Vl9TcMJB9Qig4AAA==");
}
