package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class IDShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { Vector3 n = state.getNormal(
                                                                       );
                                                   float f = n == null ? 1.0F
                                                     : Math.
                                                     abs(
                                                       state.
                                                         getRay(
                                                           ).
                                                         dot(
                                                           n));
                                                   return new Color(state.
                                                                      getInstance(
                                                                        ).
                                                                      hashCode(
                                                                        )).
                                                     mul(
                                                       f); }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public IDShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0XW2wUVfTu9l0eW1rKSygtFLA8diAGEwTRUgoUF9i0QEIJ" +
                                                    "LLczd3eHzs4dZu62S7EKJAbCBzFaEIz2w0AQ5RUjQWNI+iUQ/MEYjR+CfxKV" +
                                                    "D37QBBXPvXdnH7PbIj9uMnfvnHvOPe/HXHiAyhwbLbKosT9mUBYkKRbcaywP" +
                                                    "sv0WcYIbQ8vD2HaI1mZgx9kKsIg650rg0eO34zV+VN6N6rBpUoaZTk2nkzjU" +
                                                    "6CNaCAWy0HaDJByGakJ7cR9Wkkw3lJDusJUhNC6HlKHmkCuCAiIoIIIiRFBa" +
                                                    "s1hANIGYyUQbp8Amc/ahN5AvhMotlYvHUFP+JRa2cSJ9TVhoADdU8vftoJQg" +
                                                    "TtmoMaO71LlA4ROLlKH3dtd8VoIC3Sigm11cHBWEYMCkG41PkEQPsZ1WTSNa" +
                                                    "N5pkEqJ1EVvHhj4g5O5GtY4eMzFL2iRjJA5MWsQWPLOWG69y3eykyqidUS+q" +
                                                    "E0Nz38qiBo6BrlOyukoN13E4KFitg2B2FKvEJSnt1U2NodleioyOza8BApBW" +
                                                    "JAiL0wyrUhMDANVK3xnYjCldzNbNGKCW0SRwYWjGqJdyW1tY7cUxEmFomhcv" +
                                                    "LI8Aq0oYgpMwVO9FEzeBl2Z4vJTjnwebVx0/YG4w/UJmjagGl78SiBo8RJ0k" +
                                                    "SmxiqkQSjl8YOomnXD/qRwiQ6z3IEufa6w9fXdwwclPiPFcEZ0vPXqKyiHqm" +
                                                    "Z+KdmW0tK0q4GJUWdXTu/DzNRfiH0ycrUxZk3pTMjfww6B6OdH694+An5Dc/" +
                                                    "qu5A5So1kgmIo0kqTVi6Qez1xCQ2ZkTrQFXE1NrEeQeqgH1IN4mEbolGHcI6" +
                                                    "UKkhQOVUvIOJonAFN1EF7HUzSt29hVlc7FMWQqgCHrQAnmokf+KfoZgSpwmi" +
                                                    "YBWbukkViF2CbTWuEJVGbGJRpb1ti9IDVo4nsN3rKE7SjBq0P6ImHUYTimOr" +
                                                    "CrVjLlhRqU0UJ441Yisda7vEJsgDzvr/WKW41jX9Ph84ZKa3HBiQSRuoAbgR" +
                                                    "dSi5pv3hpchtfyY90vZiqBE4BdOcgpxTUHIKupyQzycYTOYcpbfBV72Q9VAP" +
                                                    "x7d07dq45+icEggzq78UDM1R54CiaTHaVdqWLQ0dogCqEJ/TPtp5JPjnuVdk" +
                                                    "fCqj1/Gi1GjkVP+h7W8u9SN/fkHmagGompOHeRnNlMtmbyIWuzdw5P6jyycH" +
                                                    "aTYl8yp8ulIUUvJMn+N1gE1VokHtzF6/sBFfjVwfbPajUigfUDIZhhCHatTg" +
                                                    "5ZGX8Svd6sl1KQOFo9ROYIMfuSWvmsVt2p+FiMiYyJdaGSTcgR4BReFd9+XI" +
                                                    "6avvL1rhz63RgZyu10WYzPhJWf9vtQkB+E+nwu+eeHBkp3A+YMwtxqCZr22Q" +
                                                    "/+ANsNhbN/f9eO/ume/82YBh0AiTPYaupuCO+VkuUB0MqFDcrc3bzATV9KiO" +
                                                    "ewzC4+6vwLxlV38/XiMdZQDE9fPip1+QhU9fgw7e3v1Hg7jGp/LulNU8iyYN" +
                                                    "UJe9udW28X4uR+rQt7NO38AfQvGEguXoA0TUICQ0Q8L0QeGRFrEu8Zwt5Uuj" +
                                                    "VXCWEpBp6Tfx0iTWZr4skHbj2+dzMX1iX8/Q1IKclqnMDTxrtIYkmumZw0PD" +
                                                    "2pazy2Ra1uYX+XaYYS5+//c3wVM/3ypSSaoYtZYYpI8YOTKVcJZ55WCT6NXZ" +
                                                    "pDh2/tNr7M6ilyTLhaNXAi/hjcO/zti6Or7nGYrAbI/y3ivPb7pwa/189R0/" +
                                                    "Ksnkf8H4kU+0MtcMwNQmMC+Z3KAcUi3c3CAEmATmqOMOnQ7PuHRzEv/8tM7i" +
                                                    "62SZrXx5wRM3fmFPv+vjhgIfC1UJTDc8MF20KbloXfK/Ndwh2KwdIzI38qUV" +
                                                    "UjNpadCwwYstY8zatp6A9t+Xnk+Uwdp7vR/cvyg96h1mPMjk6NCxJ8HjQ/6c" +
                                                    "iW9uwdCVSyOnPiHlBGnYJ/DzwfMPf7gKHCC7fm1bevRozMwelsXzoGkssQSL" +
                                                    "db9cHvzq48Ej/rRJVjBU0UOpQbBZmLEC8HLGz/xBM+EJpP0c+M9+9uXn8qyi" +
                                                    "uQzDLB/nibhmxxh+3MWX7QyNixHWCXQ8ZIuWCT0BUy0vmdR+qnYiaFfDU5/W" +
                                                    "rv5Zo5i/dvMlIlDJGBrE+NLDwNcqZhDd4Thl6SQP8SUspetkqLSP6lqRYspQ" +
                                                    "pTvM8Do+reDDSQ776qXhQOXU4W0/iPacGcirYCqOJg0jJ8lzE77csklUF5JW" +
                                                    "ya5riT9arAzL0QqySm6EqKbEB2iNFx9U4n+5aElwZQ4ahGR6l4sEGpcAEt/u" +
                                                    "t1xv14j2xb+GgnL0T6G8LmPl95zcds4TXnyUumUzKT9LI+rl4Y2bDzx88ayo" +
                                                    "wWXwOTswID5i4JtMDimZ0ts06m3uXeUbWh5PvFI1z823vPHFI9vs4l2+PWEx" +
                                                    "0ZcHvpj6+apzw3fFmPEvUDAjaC0QAAA=");
}
