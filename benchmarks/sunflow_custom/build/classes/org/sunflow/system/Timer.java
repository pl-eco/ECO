package org.sunflow.system;
public class Timer {
    private long startTime;
    private long endTime;
    public Timer() { super();
                     startTime = (endTime = 0); }
    public void start() { startTime = (endTime = System.nanoTime()); }
    public void end() { endTime = System.nanoTime(); }
    public long nanos() { return endTime - startTime; }
    public double seconds() { return (endTime - startTime) * 1.0E-9; }
    public static String toString(long nanos) { Timer t = new Timer();
                                                t.endTime = nanos;
                                                return t.toString(); }
    public static String toString(double seconds) { Timer t = new Timer();
                                                    t.endTime = (long) (seconds *
                                                                          1.0E9);
                                                    return t.toString(
                                                               );
    }
    public String toString() { long millis = nanos() / (1000 * 1000);
                               if (millis < 10000) return String.
                                                     format(
                                                       "%dms",
                                                       millis);
                               long hours = millis / (60 * 60 * 1000);
                               millis -= hours * 60 * 60 * 1000;
                               long minutes = millis / (60 * 1000);
                               millis -= minutes * 60 * 1000;
                               long seconds = millis / 1000;
                               millis -= seconds * 1000;
                               return String.format("%d:%02d:%02d.%1d",
                                                    hours,
                                                    minutes,
                                                    seconds,
                                                    millis /
                                                      100); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1414698727000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYbWwcxXVubd/ZjrEvDnGCGxzHcSKcwG1TlUqpETQ52cTh" +
                                                    "IG7spO1Bcca7c/bG+8XunH1x6kJSoURIpBU4ENpgJBrEVyAINaJVhZQ/5UP0" +
                                                    "D1XVih+Fqn+KSvMjP0pRaUvfm9m7vds7n0mkWprx7Mx7b973e3PnL5Mm3yPb" +
                                                    "Xcc8MmU6PMUKPHXYvDXFj7jMT+3N3DpKPZ/paZP6/jjsTWh9r3V8+vlPppMK" +
                                                    "iWfJGmrbDqfccGx/P/Mdc5bpGdIR7g6ZzPI5SWYO01mq5rlhqhnD54MZsqoM" +
                                                    "lZP+TJEFFVhQgQVVsKDuCqEA6Tpm5600YlCb+w+QH5JYhsRdDdnjZFMlEZd6" +
                                                    "1ArIjAoJgEIzfh8EoQRywSO9JdmlzFUCn96uLj55f/L1BtKRJR2GPYbsaMAE" +
                                                    "h0uypM1i1iTz/F26zvQsWW0zpo8xz6CmMS/4zpJO35iyKc97rKQk3My7zBN3" +
                                                    "hppr01A2L69xxyuJlzOYqRe/mnImnQJZu0JZpYTDuA8CthrAmJejGiuiNM4Y" +
                                                    "ts7JxihGScb+uwAAUBMW49NO6apGm8IG6ZS2M6k9pY5xz7CnALTJycMtnHQv" +
                                                    "SxR17VJthk6xCU7WR+FG5RFAtQhFIAona6NgghJYqTtipTL7XL7ntlNH7T22" +
                                                    "InjWmWYi/82A1BNB2s9yzGO2xiRi27bME7TrzZMKIQC8NgIsYd74wZVv3dxz" +
                                                    "6R0J85UaMPsmDzONT2jnJtvf35Ae2NmAbDS7jm+g8SskF+4/GpwMFlyIvK4S" +
                                                    "RTxMFQ8v7X/rew+9xD5RSOsIiWuOmbfAj1ZrjuUaJvPuZDbzKGf6CGlhtp4W" +
                                                    "5yMkAeuMYTO5uy+X8xkfIY2m2Io74htUlAMSqKIErA075xTXLuXTYl1wCSEJ" +
                                                    "GKQNRgORf+I/J99RD/jg7irVqG3YjgrOy6inTatMcyYmQbvTFvVmfFXL+9yx" +
                                                    "VD9v50xnTvU9TXW8qfD7iM+ZpY4bFvNS6GDu/490AaVKzsVioPAN0XA3IVL2" +
                                                    "OKbOvAltMb976MqrE+8pJfcP9AFmhBtSwQ0peUNK3EBiMUH4erxJWhFsMAPR" +
                                                    "DHmubWDs+3sPnewD3RXcuUZQIIL2gTzB9UOakw5DfkQkNg38bv2z955Iffb8" +
                                                    "HdLv1OXzc01scunM3LGDD35VIUplokVxYKsV0UcxPZbSYH80wGrR7Tjx8acX" +
                                                    "nlhwwlCryNxBBqjGxAjuiyreczSmQ04MyW/rpRcn3lzoV0gjpAVIhZyC60KW" +
                                                    "6YneURHJg8WsiLI0gcA5x7OoiUfFVNbKpz1nLtwRHtEu1qvBKKvQtZMw4oGv" +
                                                    "i/94usbF+XrpQWjliBQi6w7/6tJTF3+6fadSnqA7ykreGOMy3FeHTjLuMQb7" +
                                                    "fzoz+vjpyyfuFR4CEJtrXdCPcxqCH0wGan34nQc++OjDc79XQq/iUAXzk6ah" +
                                                    "FYDG1vAWSA0mpCe0ff8B23J0I2fQSZOhc/67Y8uOi38/lZTWNGGn6Aw3r0wg" +
                                                    "3L9hN3novfv/2SPIxDQsTaHkIZhUwJqQ8i7Po0eQj8Kx39341Nv0acickK18" +
                                                    "Y56JBESEZESoXhWm2ibmVORsB069btVZQeysF18KXD2wfBANY4UtC75/7TMn" +
                                                    "j//lMyFRVfjUKCwR/Kx6/mx3+vZPBH7ox4i9sVCdgqAbCXG/9pL1D6Uv/huF" +
                                                    "JLIkqQWtzkFq5tFbslDe/WL/A+1QxXllqZZ1abAUpxuiMVR2bTSCwtQHa4TG" +
                                                    "dWskaLA2kH4YjUHQNEaDJkbEYqdA6RPzFpxuKvpswvWMWYp9FHQBnHocM2p9" +
                                                    "U416hgUlcjao4epC50czZz9+RebJqF0iwOzk4iNfpE4tKmVd0eaqxqQcR3ZG" +
                                                    "QvDrpOBfwF8Mxn9xoMC4IStjZzooz72l+uy6GI+b6rElrhj+64WFX7+wcEKK" +
                                                    "0VnZFAxBz/vKH/7z29SZP79bozI1mo49JfKTjIGvX72FRnAa5KKBQBPg525J" +
                                                    "UsjeXxZKBCW6cbl2TEhz7vjikr7vuR1KEKHDYF7uuLeYbJaZZaQSSKmiFt4t" +
                                                    "GtAwGh558eU3+Pvbvyn1sm15t4givn38b93jt08fuooKuDEiU5Tki3eff/fO" +
                                                    "rdpjCmkoBVVVT12JNFgZSq0eg0eAPV4RUD2VVWgdjNbAXK01q1BokNr58Lt1" +
                                                    "zrI4HeDw6MNoEyB34JSWyXIIvGnWMfTqZCo2vl3J6loY7QGr7dfEqlbnTKjo" +
                                                    "ECcN4JO4vG9FppJFpjoDpjqviamZOmcWTtOgP5vajoDYvSJbgosbYHQFbHVd" +
                                                    "E1t+nbM8TpAKEj5Ek637tQwb1x1oENiK7CKnZBOM7oDd7prs1s7pCi4H4DJf" +
                                                    "PJILEZ5jMvELvQkyx+oI9SOcjnJ4sTvy2Smg1nKSFD0EFrmUPPjyMvUGMvVe" +
                                                    "tUw4PVhHnjlB4tE68vwYp5Nl8uD3w1/OfdAGWwPet16T+5yuc/YkTo+tzBmU" +
                                                    "6Sbx5sFObn3V7ybyra+9utTRvG7pwB9FF196j7fAoziXN83yzqJsHXc9ljME" +
                                                    "Ny2yz5CV5yyU1eqXFzqYWAgWfyZBn+FkVRkoRoNclQM9CwkFgHD5c7eGQ8mu" +
                                                    "qUAqCp4bLX+bKwqR+J2pWDTy8pemCe3C0t57jl75xnOiAjVpJp2fRyrNGZKQ" +
                                                    "75NS4dm0LLUirfiegc/bX2vZUiyo7Th1ljltGW8ba/fuQ5bLRbc9/8t1v7jt" +
                                                    "+aUPxePhf/YNhYYAFAAA");
}
