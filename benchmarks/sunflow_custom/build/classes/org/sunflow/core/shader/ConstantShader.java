package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class ConstantShader implements Shader {
    private Color c;
    public ConstantShader() { super();
                              this.c = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { c = pl.getColor(
                                                                       "color",
                                                                       c);
                                                              return true;
    }
    public Color getRadiance(ShadingState state) { return c; }
    public void scatterPhoton(ShadingState state, Color power) {  }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YX2wcxRmfW/+LHce+OHES0sRJjBORP9w2SCClRqHh5CRO" +
                                                    "L/gUJxGYNpe53Tnfxrs7m905+2JwgSCUiIcIgYFQgR9QEIXmnxARIISUlxYQ" +
                                                    "faGqingoVLyAmuYhDwRU2tJvZnZv/9zZlJda2rnZme/vfN/3m299/jpq8Vy0" +
                                                    "1aHmiXGTsgypsswx884MO+EQL7Mvd2ceux7Rsyb2vIOwVtD6L3ff/O6pclpB" +
                                                    "rWNoGbZtyjAzqO0dIB41J4meQ93h6pBJLI+hdO4YnsRqhRmmmjM8NphDiyOs" +
                                                    "DA3kAhNUMEEFE1RhgrorpAKmJcSuWFnOgW3mHUe/RqkcanU0bh5DG+JCHOxi" +
                                                    "yxeTFx6AhEX8/TA4JZirLlpf8136XOfws1vV2eePpN9oQt1jqNuwR7k5GhjB" +
                                                    "QMkY6rSIVSSut0vXiT6GltqE6KPENbBpTAu7x1CPZ4zbmFVcUjskvlhxiCt0" +
                                                    "hifXqXHf3IrGqFtzr2QQUw/eWkomHgdfV4S+Sg9383VwsMMAw9wS1kjA0jxh" +
                                                    "2DpD65IcNR8HfgEEwNpmEVamNVXNNoYF1CNjZ2J7XB1lrmGPA2kLrYAWhlbP" +
                                                    "K5SftYO1CTxOCgytStLl5RZQtYuD4CwM9SbJhCSI0upElCLxuX7f3Wcesvfa" +
                                                    "irBZJ5rJ7V8ETH0JpgOkRFxia0Qydm7JPYdXvHdaQQiIexPEkuath2/8fFvf" +
                                                    "1Q8kzU8a0IwUjxGNFbRzxa6P12Q372jiZixyqGfw4Mc8F+mf93cGqw5U3oqa" +
                                                    "RL6ZCTavHvjDA4++Tq4pqGMYtWrUrFiQR0s1ajmGSdw9xCYuZkQfRu3E1rNi" +
                                                    "fxi1wTxn2ESujpRKHmHDqNkUS61UvMMRlUAEP6I2mBt2iQZzB7OymFcdhFAb" +
                                                    "POh2eNqR/BO/DFlqmVpExRq2DZuqkLsEu1pZJRotuMSh6lB2RC3CKZct7E54" +
                                                    "qlexSyadKmgVj1FL9VxNpe54sKxq1CWqV8Y6cdWgsEfFa4annfP/VljlJ5Ce" +
                                                    "SqUgOGuS0GBCVe2lJtAWtNnKvUM3LhY+Umql4p8dQ5tAX8bXl+H6MlJfJq4P" +
                                                    "pVJCzXKuV8YfojcBOAAI2bl59Ff7jp7ub4LEc6aa4eg5aT847RszpNFsCBbD" +
                                                    "QrAGGbvq5QdPZb599R6Zser8yN6QG109O/XY4Ud+qiAlDtHcOVjq4Ox5Dqw1" +
                                                    "AB1IlmYjud2nvrp56bkZGhZpDPN97Kjn5LXfnwyDSzWiA5qG4resx1cK780M" +
                                                    "KKgZAAVAlGFIesCnvqSOGAYMBnjKfWkBh0vUtbDJtwIQ7GBll06FKyI/usR8" +
                                                    "KQRlMS+KW+Dp9KtE/PLdZQ4fl8t84lFOeCHwevc7V1+48putO5QotHdHLstR" +
                                                    "wiRQLA2T5KBLCKz/9Wz+mWevn3pQZAhQ3NpIwQAfswAbEDI41ic+OP7p55+d" +
                                                    "+7MSZhWD+7NSNA2tCjI2hVoAVEwANh77gUO2RXWjZOCiSXhy/qt74/Yr/ziT" +
                                                    "ltE0YSVIhm0/LCBcv+Ve9OhHR77pE2JSGr/UQs9DMnkAy0LJu1wXn+B2VB/7" +
                                                    "09oX3scvAeYCznnGNBHQhYRnSBy9KkK1RYyZxN52Pqx36vaqYmVVreo2z19E" +
                                                    "u/ndHCm+f46YxZNffCs8qiufBldSgn9MPf/i6uzOa4I/zGPOva5aD0jQx4S8" +
                                                    "d7xufa30t/5eQW1jKK35UHMYmxWeLWPQGHgB/kAjFduPX/LyRhus1emaZA1F" +
                                                    "1CYrKARCmHNqPu9IFI2okeXwdPhF05EsmhQSkx2CpV+MG/lwW5CzbY5rTGLe" +
                                                    "gUHiiP1ehlZGcdewoL3gSUjF8aUdYcRALLSN+AReS4DmFbF2vsZDNE3nTs7O" +
                                                    "6SOvbJdg2xO/zIegV73wl3//MXP2bx82uCXaGXVuN8kkMSM2NXGVMZDfL3qy" +
                                                    "MMxPvva7t9jHW38mVW6ZPzWTjO+f/PvqgzvLR38EtK9LOJ8U+dr+8x/u2aQ9" +
                                                    "raCmWrbUtZlxpsF4jnS4BPpi+2AsU/pqmbIsgNe0nynphvAaRjYsdEWcpxLE" +
                                                    "uK8uxsJVAl0sR5KAbEWUbFT+7soPCzX5BaDkMB/2A5ZWHB3ScmHQyLuGBW3e" +
                                                    "pN+HqjM9n0+8+NUFGdEkQiSIyenZJ7/PnJlVIp39rXXNdZRHdvfCyiXyYL+H" +
                                                    "vxQ8/+EPd4EvyO6uJ+u3mOtrPabj8DrYsJBZQsXuLy/NvPvbmVOKfyRDUKVF" +
                                                    "Sk2C7XqIFQvDtTjzB62Bp9ePc+//HOdUvJbXNqxl+Gjhn21EiCELxNHgQ5Gh" +
                                                    "xeOEHQA+nrJ86Z4fdEF0Ajv9dA3S9kelKn8tCWWClC5g5nE+QLEt8TTMIIXz" +
                                                    "Zcr8Sr6fD7+U1h1hqHmSGnqDK46hrng3yu/YVXXfwvL7Tbs4171o5dyhT0R/" +
                                                    "VfvGaocPnVLFNKOYH5m3Oi4pGcLednkDSAw+0QhxZYcMBSQnwuCqpH+YoXSS" +
                                                    "HhzjP1GyRyBqETLIPn8WJTrJUBMQ8enjTpAzadFa8LsvI+++KopAMu+uom+x" +
                                                    "VovXtvg/Q4CQFfmfhoJ2aW7ffQ/duOsVAbctmomnp8V3KXxmyy6zhrIb5pUW" +
                                                    "yGrdu/m7rsvtG4PS6uJDj99aJmxb17gDG7IcJnqm6bdXvnn3q3OfiRbwv9S9" +
                                                    "EWIAEgAA");
}
