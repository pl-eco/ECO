package org.sunflow.core;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
public interface Tesselatable extends RenderObject {
    public PrimitiveList tesselate();
    public BoundingBox getWorldBounds(Matrix4 o2w);
    String jlc$CompilerVersion$jl = "2.5.0";
    long jlc$SourceLastModified$jl = 1170615938000L;
    String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1XW2wVRRiec+m92gtQrm2hFI1czqKCRkpUWgscWODYlgLl" +
                               "Uqa7c04X9uwss7Pt\naSEGYiIo0UjURBMlPJBw8YIPGjQhikG8voiJMSHRxP" +
                               "igifLgC2L0wX9mz56zZ9tC9CQ7Ozvzz3+Z\n//u/mfPWDVTmMDRPcxJ8zCZO" +
                               "oqs3hZlD9C4TO04fDA1qV8uqUmc2WjSKIiqKGjpHdarmKDrmWDF0\nJflER4" +
                               "6hJTY1xzIm5QmS44l95sq8vg3qygkKt5282HjkdLw1ispUVIcti3LMDWp1my" +
                               "TrcFSv7sMj\nWHG5YSqq4fAOFd1FLDfbRS2HY4s7B9BTKKaiclsTOjlaoPrG" +
                               "FTCu2JjhrCLNKylpFjRMY4RjwyL6\nmoI5WLm0dCW4nV/XM1EalFSKyX4IR3" +
                               "oAUc8vRO1FOyFUO3a2/6FDp87FUN0AqjOsXqFMg0g42BtA\ntVmSHSLMWaPr" +
                               "RB9ADRYhei9hBjaNcWl1ADU6RsbC3GXE6SEONUeEYKPj2oRJm/6gimo1ERNz" +
                               "NU5Z\nYY/SBjF1/6ssbeIMhN1UDNsLd60YhwCrDXCMpbFG/CXx/YYFGW8Nry" +
                               "jE2L4RBGBpRZbwYVowFbcw\nDKBGL5cmtjJKL2eGlQHRMuqCFY7mTKlU7LWN" +
                               "tf04QwY5mhWWS3lTIFUlN0Is4WhGWExqgizNCWUp\nkJ8lTTePnX39o8cltu" +
                               "M60UzhfzUsagkt6iFpwoilEW/hLTfxcnKHOy+KEAjPCAl7MmsWXdyq/vpx\n" +
                               "qyczdxKZLUP7iMYHtc0nWnsOrqMoJtyotKljiOSXRC7LIZWf6cjZULVNBY1i" +
                               "MuFPXu75bMfh8+S3\nKKpOonKNmm4WcNSg0axtmIStIxZhmBM9iaqIpXfJ+S" +
                               "SqgL4KkPdGt6TTDuFJFDflUDmV37BFaVAh\ntqgK+oaVpn7fxnxY9nM2QqgC" +
                               "HhSBZyHyfjWi4Wh5QnFcK23SUcVhmkJZpvCtUUaUPuI4xARiGTJJ\nQiDH5k" +
                               "hVhmmWKFjDlmFRJWNArWp0mU5GxPs/6ssJHxtHIxFBeuHiNQH366mpEzaonf" +
                               "n5q0PdG589\n5gFDgDkfHUfNYCaRN5MQZhJBMygSkdqnC3NeYmBb90OBApXV" +
                               "3te7e8PeY20xQIQ9GhebkpMVM8f/\ngIUht2Rt3nq6fPn3l2quSnf8Mq4LEG" +
                               "Uv4R4oGop2+xghMP7Dq6mXXrlxdKc06lmNcVSJh4AssMY5\nqipUPQdSdYdM" +
                               "Qwt6FZH9mZMF3gNQIcxDsfC9eapKkyxxdPsftc/gT3d79dBYit5uYPhfxq6Q" +
                               "e1c/\n/9Mkm17Fqb3MJCPEDHgWFSYBBHlr3RrdJEkoKQ8KDUrwuXNvXuTXlq" +
                               "zyTC6e+pAKL1y86tR466oL\nx6MoOvnhIbyA46taaEiJE6dwKLSGNiGsumFk" +
                               "7pOxYeOLqORJwTmT8Gvpoo7gdoBR8MdllthYMVIL\nRtvCYGZUIzqcGkW7p2" +
                               "fXxbah/hNRFAfihMNCxgQ83BI2XsJ1HT7ghKkKFdWkKctiU0z5ZF/Nhxkd\n" +
                               "LY7IKquX/Wl+8bfD05hnA/kWszNs0TZ5VSnlm2U734NpVPTbRNMu0HVPEdrA" +
                               "WiZgTiSifauVpbqR\nNkT1iSL7p27R/e///kK9hyITRvzMLL2zguL47E50+O" +
                               "s9f7ZINRFNnJrFciuKeVU3rah5DWN4TPiR\nO/Jt82uf4zeA1IFIHWOcSG5E" +
                               "+VIXTq2W0T4s247Q3KOieVAgP88uxK/Dlgl1mGJGFrh/RMYP7iyd\nAuiTXG" +
                               "sGtUPnM23ugS8/lIHW4OD9KMgwm7Dd4SVWNCtEQmaGOW49doZBbsXlzbvqzc" +
                               "t/g8YB0KjB\ndcLZwoApciX8lJcuq7j+yZWmvddiKLoWVZsU62uxuMPASQS4" +
                               "Is4wUHLOfuxxCZ360UrRyl1CpdQp\nPpaVgu4BeNryoGv7P6ArTU+klA9nBf" +
                               "OQhQMwsQnDHSe3QqrefJvc9ogmydHdGcK3UWbqndS1dMdX\nPG+CYjkPt6dO" +
                               "6vnUKZoNXr/7jluT46g2eEYJxM6acHX1rluaev3grpvqd39JmihciWrgXpJ2" +
                               "TTPA\nQkFGKrcZSRsythoPJLZ87YTrfBitHMXFS3o44Int4agmIMZRRb4XFM" +
                               "IcxUBIdIdsf6vqJZzE/TLh\nH0PByEWkC0uKQf478JnZ9f4fDGrb3945P3e8" +
                               "70VJ92Xwv2J8XF4E4V7r0V2B3RdMqc3X9Q1690L/\npXceieaTLetlegBuJY" +
                               "jt9GZvk0k4USbnre6szSXTjH8w873VZ07+GJUn/L/oZWRE1A0AAA==");
}
