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
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XW2xURRie3d5L6Y1bRShtKWgp7IEYTLB4KWsLhYU2LWAs" +
                                                    "keX0nNnd054bc2bbpVgFElPCAzFaEIz2wUAQ5RYjQWNI+iQQfMEYjQ+CbxKV" +
                                                    "B17QBBX/mdnds3t2W/XFTc7sOTP//f/nm3/O3UNFDkGttqXvi+oWDeAEDQzq" +
                                                    "awN0n42dwObQ2h6ZOFgN6rLjbIe5sNJ0qerBwzdj1X5U3I/myKZpUZlqlun0" +
                                                    "YsfSh7EaQlXubIeODYei6tCgPCxLcarpUkhzaFsIzcpgpag5lDJBAhMkMEHi" +
                                                    "JkjtLhUwzcZm3AgyDtmkzl70GvKFULGtMPMoaswWYstENpJiergHIKGUfe8E" +
                                                    "pzhzgqCGtO/C5xyHj7VKE+/srv6kAFX1oyrN7GPmKGAEBSX9qMLAxgAmTruq" +
                                                    "YrUf1ZgYq32YaLKujXK7+1Gto0VNmcYJTgeJTcZtTLhON3IVCvONxBVqkbR7" +
                                                    "EQ3rauqrKKLLUfB1vuur8LCTzYOD5RoYRiKyglMshUOaqVK0xMuR9rF5CxAA" +
                                                    "a4mBacxKqyo0ZZhAtSJ3umxGpT5KNDMKpEVWHLRQtHBaoSzWtqwMyVEcpqjO" +
                                                    "S9cjloCqjAeCsVA0z0vGJUGWFnqylJGfe9vWH91vbjL93GYVKzqzvxSY6j1M" +
                                                    "vTiCCTYVLBgrVoSOy/OvHvYjBMTzPMSC5sqr919YWT91XdA8noeme2AQKzSs" +
                                                    "nBqovLUo2LKugJlRaluOxpKf5Tkv/57kSlvChp03Py2RLQZSi1O9X7584CP8" +
                                                    "ix+Vd6FixdLjBtRRjWIZtqZjshGbmMgUq12oDJtqkK93oRJ4D2kmFrPdkYiD" +
                                                    "aRcq1PlUscW/IUQREMFCVALvmhmxUu+2TGP8PWEjhErgQSvhKULix/8p0qWY" +
                                                    "ZWBJVmRTMy0JahfLRIlJWLHCBNuW1BHslgYgyjFDJkOO5MTNiG6NhJW4Qy1D" +
                                                    "cogiWSSampYUi2AJLIJqkkKyqYxaTif/CrCqs/9nfQnmf/WIzwepWeQFBh32" +
                                                    "1CZLVzEJKxPxDR33L4Rv+tMbJRk5ipaBukBSXYCpCwh1gSx1yOfjWuYytSL5" +
                                                    "kLohAAGAx4qWvlc27zncVABVZ48UQtwZaRO4nLSlQ7GCLlJ0cTxUoFzrPtg1" +
                                                    "Hvj9zPOiXKXpYT0vN5o6MXJw5+ur/cifjc/MN5gqZ+w9DFXT6Nns3Zf55FaN" +
                                                    "331w8fiY5e7QLMBPAkcuJ9v4Td4sEEvBKkCpK35Fg3w5fHWs2Y8KAU0AQakM" +
                                                    "FQ/gVO/VkQUAbSkwZb4UgcMRixiyzpZSCFhOY8QacWd4eVSyoVZUCkugx0CO" +
                                                    "w52fT528/G7rOn8mZFdlHIJ9mAoAqHHzv51gDPM/nOh5+9i98V08+UCxNJ+C" +
                                                    "ZjYGAQ4gGxCxN67v/f7O7VPf+N2CoXAuxgd0TUmAjOWuFgALHQCLpbV5h2lY" +
                                                    "qhbR5AEds7r7o2rZmsu/Hq0WidJhJpXnlf8swJ1/bAM6cHP3b/VcjE9hh5Xr" +
                                                    "uUsmAjDHldxOiLyP2ZE4+PXik9fk9wFLAb8cbRRzSELcM8RDH+AZaeHjKs/a" +
                                                    "ajY02DlrCT5Tl/ziH418bGbDEyJu7PXJTEoff59H0YKcjS22Mgvw4unOJ362" +
                                                    "njo0Mal2n14jtmVtNuZ3QEtz/ts/vwqc+PFGHjgpo5a9SsfDWM+wqYCpzIKD" +
                                                    "rfzodjfFkbMfX6G3Wp8RKldMjwRexmuHfl64/bnYnv8AAks8zntFnt167sbG" +
                                                    "5cpbflSQ3v853Ug2U1tmGEApwdA+mSygbKacp7meG1AD4WAPqoOnOHlW8X+2" +
                                                    "Osdm41yxW9nw1Ax18+IMa51saKeoJIppHxQkJKBlhq6ZaAYc5MPJTkMaq70z" +
                                                    "9N7d8yIZ3rbEQ4wPTxx5FDg64c/o3ZbmtE+ZPKJ/42bOFjF5BD8fPH+xh/nA" +
                                                    "JsT5XRtMNhEN6S7CtlkJN85kFlfR+dPFsS8+HBv3J2OyjjJss2Sau9X4xLPZ" +
                                                    "CZoLT3kyQeX/OkF+LtHPPrfwgZPumCFVL7Ghl6ICSBXn+EfzalP1U5k0rzKv" +
                                                    "edMgBhSFTbRhiGTCY5XPhRRh9p4ZzB5gQz/AtqOZyhp1GssTFM3OaiQYhtbl" +
                                                    "3GFE361cmKwqXTC54zt+NKZ74zJoUCNxXc/YYJmbrdgmOKJxm8rEiWfzv2g+" +
                                                    "CBS9DWW3FPbC7Y0I+kG493npKSpkf5lkBkWzMsggmsm3TCIAwgIgYq977RQg" +
                                                    "V/Ojg11MAqILT6AshLez8T7zKGU7lt8PU5AVFzfEsHJxcvO2/fefPs3xrwhu" +
                                                    "lqOj/D4B1yPRIKRhr3FaaSlZxZtaHlZeKluW2jBZrYPHtiX5T9gOw6b8TBz9" +
                                                    "bMGn689M3uZH/N9U89tjuA8AAA==");
}
