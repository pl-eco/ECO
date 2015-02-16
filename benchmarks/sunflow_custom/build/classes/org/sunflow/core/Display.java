package org.sunflow.core;
import org.sunflow.image.Color;
public interface Display {
    void imageBegin(int w, int h, int bucketSize);
    void imagePrepare(int x, int y, int w, int h, int id);
    void imageUpdate(int x, int y, int w, int h, Color[] data);
    void imageFill(int x, int y, int w, int h, Color c);
    void imageEnd();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1170608712000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0ZbXAV1fW+l+8PkpDwJV8SEnQg+l5hih2IgyQxgWCADAk4" +
                                "ptbHZve+l4V9u8vufflAsGCrMHREh2KVjs0vaGvlw2mltHVkaG0VtHQGSm1x" +
                                "Wqj9mKKWFjqj/YFKz7l339t9m5dHEqGZ2ZPde+8595xzz+d9By+TPNsidaah" +
                                "DcQ0g4VoPwtt0BaG2IBJ7dCKtoXtkmVTpUmTbLsTxiLy7JfLP772TE9FkOR3" +
                                "kSpJ1w0mMdXQ7TXUNrReqrSRcne0WaNxm5GKtg1SrxROMFULt6k2q28jJR5U" +
                                "RmrbkiyEgYUwsBDmLIQb3FWANI7qiXgTYkg6szeRx0igjeSbMrLHSHU6EVOy" +
                                "pLhDpp1LABQK8XsdCMWR+y0yKyW7kHmIwM/Whfc+90jFD3NIeRcpV/UOZEcG" +
                                "Jhhs0kVK4zTeTS27QVGo0kXG65QqHdRSJU3dzPnuIpW2GtMllrBoSkk4mDCp" +
                                "xfd0NVcqo2xWQmaGlRIvqlJNSX7lRTUpBrJOcmUVErbgOAhYrAJjVlSSaRIl" +
                                "d6OqK4zc7sdIyVj7ACwA1II4ZT1GaqtcXYIBUinOTpP0WLiDWaoeg6V5RgJ2" +
                                "YWTqsERR16Ykb5RiNMLIFP+6djEFq4q4IhCFkYn+ZZwSnNJU3yl5zufyqnt3" +
                                "P6ov14OcZ4XKGvJfCEgzfUhraJRaVJepQCyd1/YtadJrO4OEwOKJvsVizbEt" +
                                "V5feNfPESbFmWoY1q7s3UJlF5P3dZWemN81dlINsFJqGreLhp0nOzb/dmanv" +
                                "N8HzJqUo4mQoOXlizRsPbfsB/TBIiltJvmxoiTjY0XjZiJuqRq1lVKeWxKjS" +
                                "SoqorjTx+VZSAO9tqk7F6Opo1KasleRqfCjf4N+goiiQQBUVwLuqR43kuymx" +
                                "Hv7ebxJCCuAhAXhWEPFXiICRB8NrbTD3sCRLuqobYTBeKllyT5jKRqQbtNsT" +
                                "l6yNdlhO2MyIh+2EHtWMvrBtyWHDiqW+ZcOi4ftV29SkgRAamHnrSPejVBV9" +
                                "gQAofLrf3TXwlOWGplArIu9NNDZfPRx5O5gyf0cfcIywQ8jZIYQ7hJwdSCDA" +
                                "CU/AncQpwhlsBG+GOFc6t+MrK9bvnJ0D5mP25aIG+7l7TUl+AKKPI+7ILT89" +
                                "se/ot+sWBb0+X+6Joh2UCQsa7+7baVEK4396vv2bz17e8WW+KayoybRBLcIm" +
                                "sCcIkhBsnji56fzFC/vPBVOM5jAIrIluTZUZKZS6ISpJMmOkKBVehggyYzgf" +
                                "5fFl/+N7B5XVB+YLT6pMt/tmCOuH3vn016Hn/3wqg/KLmGHerdFeqnn2LMMt" +
                                "wTKc3ZplYyUPX608O8jgvLtefOkYO1O3WGw5b/gk50d88/EPpnYu6VkfJMH0" +
                                "RIW7w1AxYrZjekmlkdt9wvtJvrjy4Klld8h7giTHiVIZInI6Ur1XDbCpRSGF" +
                                "6KhQHCmGTWf7jdkyZKpAnnH3nTdLOhp5bWttkORCqIX0wiQIBxC5Z/o3T4uO" +
                                "9Umrw63yQAlRw4pLGk4l00Mx67GMPneEe1kZfx8Px8PNYj48VU784P9xtspE" +
                                "OEF4JV8/jcOZCKr52QbxdTaCGrSsO1wbh1inQbzFw6hdq8cNRY2qUrdG0ds+" +
                                "KZ8z/+g/d1cIC9JgJHk6d92YgDt+WyPZ9vYj/53JyQRkzLWu37nLhPtVuZQb" +
                                "LEsaQD76t5+dse9N6TuQCiD82upmyiNqjvArQJqbpd6y1DikgF4nR4W3Vl7c" +
                                "+MKlQ8KE/QnNt5ju3Lvremj33qAn69cMSbxeHJH5+UmMEyd3Hf4C8HyGD54Y" +
                                "DojIX9nkpJ9Zqfxjmng81dnY4lu0/OPI1le/v3UHioHb3MlIDgQSfF3CAR9d" +
                                "zFlYyOEi3NOJLfjdjGA+g/ImDmVDI42pwhvrEdwnYlEDI7m9hqqYQyjwgbp0" +
                                "28T5asc2q8dqm+kM5/EFea5YPsDJtmeRcg2CBxgp5VK2WxTCM/f2ZSOTaQk8" +
                                "CxyZFtxymeDo5wxvydwdhGEOfrfmN18drHkPDLOLFKo21N8NVixDWefBuXLw" +
                                "4odnx804zANXbrdki6jnr4eHlrtpVSwXujSlpGbiFDSnHSWdFqb98M2pPOwB" +
                                "m9F4uJUfnqRTLVnZ3FL64sQmMjLZW6NwC8JoZ1imaQrz6spiegqCtYyUcMS1" +
                                "pgL+PQrL+xI8jY5SG/8v3iTKOi2LSDqCGNYsKFKLqmmjEAjTVasjUOvNEcjL" +
                                "W2+WOQ42QdnF+W7WlRGwzcN05rg9ye3fRLMS4v0yxO4sYuS4YkAhCN0rhPNs" +
                                "8jyeZe7rglEEW1LGWsETJ3IVElxl44NBNaLqEj+/bZzmdgRfQ/AEKCpGGa82" +
                                "7axlXkcCylhP5/iUOnj6rY/Kt4vkmp6U+eWBg+rHO/+HnAUlrPZpXh6mQlMh" +
                                "lEw2rmRk1vAXEZyWyLglNzy5ie7J8e1TB5dUYrmrRL4Ah/ekZeTMSojIrfFI" +
                                "x9HzO+7htUJ5rwp9J1U6nbuR9JLHbWfq0+5LMqopIl868tTJ6g/WVfFGOKkR" +
                                "b+uyUjKHtC7LJbsHxvMK3v3565PWn8khwRZSrBmS0iLhXQh0tFBtUrsHGrV+" +
                                "876l3CsDfdiSBvEtSyZyZOI1XkTe8sJnp9/feuFUDsmH3IFlOCRYqLgZCQ13" +
                                "A+UlUNsJb/cDFtTHZQJb1WMp1YMJVKZGU8U4I3cPR5sXfL6aHe9wIIBTq9FI" +
                                "CNf/gq8RSJimd9ab48ZiTI9BfTwC5aVkd4IiqeRuU+aaIHZx3klIfFVNbQ0d" +
                                "HZHOh9qbI+sa1rQ2NLbxSm6PCZOBTnx9sj/L6T2oakqTZCnC8Q5cL6pZOvnU" +
                                "Yu54Q5U0RsVM4YqR+lgYC1xDp7obIU3B4ZBet3YYfj3XlRH5mXNXxq27cvwq" +
                                "9zF/G/mc6JecoGiRyf77A8clvnhi1cMV2olrvHYqkWQodOzVlkItjr/P8QXC" +
                                "faGCs0cyBuFvIHgawUEER7wxeWR5QATfLHH+lSxzP/bv9+SN0qeI8z9CcBTB" +
                                "MYjzPaCTJkPhnrCEo77kySqHMioDevPM3V9z3GS8X9v8k8mv3Pu9wQv88gMJ" +
                                "/SxVZLyK4PgIdZUl9Qf4goCwJoSvZ9HVr0aoK05uNl/xCwS/RPAGpGu6KSFp" +
                                "dqb+qKDbMDQq6SPTHRcewSkEbyE4fZMU4pX3t1nmfjd6XZxFcA7BO2AyzBCX" +
                                "1hnqDs/EyFVxHsG7CP44GlWMxo/+kmXubyPUh7vfLteZ3kPwVwR/x6LOYGp0" +
                                "AL+WjVIHlzgSgvdvlQ7+nWXu6ufRwb8QXEHwH+gMhA4anM5glGr4KKWGj2+m" +
                                "GpxYkcl9czVDj3Han2RBAiuf6Vp5KzbIVsKE+q65X6YmBj8kEQh8HjVeQwqo" +
                                "ikAusNUnqWwMGgwUJDUYKByNBrPElaC74FME/M4lUDZsOA4E+YKKUaenXRxv" +
                                "HIJyBONdecashAk3SQkeZwlMvZHo08co+m0IpiGYcRNEn8XH+iFDOb+rYIMw" +
                                "Zchvs+L3RPnwYHnh5MG1vxeXQ8nf/IraSGE0oWmeWtlbN+ebFo2qXMQiUXzx" +
                                "DjhQC0nB/+sO2DT+QxECNWLZnYyUeJYBp86bd9E8RnJgEb7WQflI0upG019F" +
                                "1gzbS61MiF+sI/KRwRWrHr16zwFe8+ZBXbp5M1KBVqNA3MlzonjJXT0stSSt" +
                                "/OVzr5W9XDQneQ3Lq89Kj5FN8UTZ4/8DwR6OwR0gAAA=");
}
