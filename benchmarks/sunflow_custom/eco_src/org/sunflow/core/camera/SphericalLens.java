package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class SphericalLens implements CameraLens {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time) { double theta = 2 * Math.PI *
                                       x /
                                       imageWidth +
                                       Math.
                                         PI /
                                       2;
                                     double phi = Math.PI * (imageHeight -
                                                               1 -
                                                               y) /
                                       imageHeight;
                                     return new Ray(0, 0, 0, (float)
                                                               (Math.
                                                                  cos(
                                                                    theta) *
                                                                  Math.
                                                                  sin(
                                                                    phi)),
                                                    (float)
                                                      Math.
                                                      cos(
                                                        phi),
                                                    (float)
                                                      (Math.
                                                         sin(
                                                           theta) *
                                                         Math.
                                                         sin(
                                                           phi)));
    }
    public SphericalLens() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1163484256000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Xe2wURRif3vVBH6SlQEHAlhZQnreQiFFKIrUWOTmk9gCx" +
                                                   "KHW6O3e3sLez7M62\n10JQYyKI8UHURBMfxJDgWxM1aIKK8S3/iImamPgKiZ" +
                                                   "r4SIyJYvQPv/nm9h57bTVecruzM99jvtdv\nvnn2Z1LjuWSB7sXEmMO8WG+y" +
                                                   "n7oeM3ot6nnbYGpIf7emvv/EZptHSFWCRExDkOaE7mkGFVQzDS1+\nVXfOJS" +
                                                   "scbo2lLS5iLCdie6y1eXnXJNZWCLz+sZOttx2v7oiQmgRpprbNBRUmt/sslv" +
                                                   "UEaUnsoSNU\n84VpaQnTE90JMp3ZfraX256gtvD2kYMkmiC1ji5lCtKZCJRr" +
                                                   "oFxzqEuzGqrX+lEtSJjpMkFNmxk9\nBXXAubKcE7ad5xuopAYh0+TiDjAHdw" +
                                                   "BWLyxYraytMNWJPrnj0gPHnoqS5kHSbNpJKUwHSwToGyRN\nWZYdZq7XYxjM" +
                                                   "GCQzbMaMJHNNapnjqHWQtHpm2qbCd5k3wDxujUjCVs93mIs6g8kEadKlTa6v" +
                                                   "C+4W\nfJQymWUEXzUpi6bB7Lai2crcjXIeDGwwYWNuiuosYKnea9oQ8Y4wR8" +
                                                   "HGxZuBAFjrskxkeEFVtU1h\ngrSqWFrUTmtJ4Zp2GkhruA9aBJk3qVDpa4fq" +
                                                   "e2maDQkyN0zXr5aAqh4dIVkEmR0mQ0kQpXmhKJXE\nZ0Xb74effOSNDZjb1Q" +
                                                   "bTLbn/BmBqDzENsBRzma0zxXjejz0Qv8FfECEEiGeHiBVNz5KT2xM/vNmh\n" +
                                                   "aOZPQLN1eA/TxZB+7dGOgf1XcxKV25jmcM+UwS+zHMuhP7/SnXOgatsKEuVi" +
                                                   "LFg8PfDeDbc+zX6M\nkIY4qdW55Wchj2boPOuYFnOvZjZzqWBGnNQz2+jF9T" +
                                                   "ipg3ECUl7Nbk2lPCbipNrCqVqO3+CiFIiQ\nLqqHsWmneDB2qMjgOOcQQurg" +
                                                   "T1bDfxpRP3wLcllM83w7ZfFRzXN1jbvpwrfOXabpkDQu1ZJOBopA\np1aC2V" +
                                                   "5MZpAjyDYtw7NMozq1TZtraRNqVuerDDYi3/9Tbk7uuXW0qkqCYLiYLaiDTd" +
                                                   "wymDuknzj3\n0YG+zXceVokikztvrSBLQF0sry4m1cWUuliZOlJVhVpmSbUq" +
                                                   "YODuvVC4AHFNy5I3XXPz4a4oZIoz\nWg2+kqRdYFd+L3067y1WdxyBUIcUm/" +
                                                   "vErkOx8yeuUCmmTQ7CE3I3fvzcmWO/NS2PkMjECCltBIxu\nkGL6JawWkG9x" +
                                                   "uKYmkv/LkS0vfXbmy6XF6hJkcUXRV3LKou0KR8PlOjMABovij1/QHL2e7Dga" +
                                                   "IdWA\nBIB+uH8AlvawjrLi7Q6AUNpSlyCNKe5mqSWXAvRqEBmXjxZnME1a5G" +
                                                   "OWyhgZyNAGEUPP3167+vNT\nje+ixQHcNpccaEkmVPHOKObBNpcxmP/yof77" +
                                                   "H/z50C5MgnwWCDjl/GHL1HPAclGRBarWAuSQMVq8\n3c5yw0yZdNhiMpn+bl" +
                                                   "6y5pWf7mlRXrdgJgjayn8XUJy/4Epy65ndf7SjmCpdnhpFM4pkypqZRck9\n" +
                                                   "rkvH5D5yt31y4cPv00cB1ABIPHOcITYQtIygH2Po3mX4XBVaWy0fXSB75SRZ" +
                                                   "PcEZPaQfeDrd5e/7\n8DXcdSMtPexLw7CFOt0qqKh7JihdQ/KPMsySq7Md+W" +
                                                   "yTIZgTrt5N1MuAsEtOX3tji3X6L1A7CGp1\nOEC9rS4gR64s0nnqmrov3nq7" +
                                                   "7eazURLZSBosTo2NFPOf1EPiMS8DoJNzrtiA22gZlRtpQb8Q3O28\nvJdyZV" +
                                                   "/4sQifF+WzR46XllJV4XiOIPMrMKsXMUsilbTzwsmOTDzuD+38tekO+s5NCn" +
                                                   "Vay4+hPmjV\nvh97m128/u5vJ0DLesGdVRYbYVbJviJSZRnabcFuoljrR556" +
                                                   "5qQ4u2KdUrl8cqALMy5fd2y8Y90L\nd/0PjOsIOSEsesbI/OuiGfODCDY8Ct" +
                                                   "4qGqVypu5Sd4BS2I/v2tKxcqYJU3JhISUbZWC74V+fT8n6\ncEoiGMnHulAl" +
                                                   "RfJ+zce7vSLeaCqDPkyWakDWVkqWVO+e/jiq2TxFrV4nH5sArHwHrgUMojm3" +
                                                   "9Ebh\nmlnoTEYQfc/d0fX6B9sfP6QCuWyKa0Mp15B+y9ff7I3e+9aw4gt3Zy" +
                                                   "Hio+3Hv3vp3MAslX+qhV1U\n0UWW8qg2Fo1pdmQFdE6lAanfWdH57MGBr3BH" +
                                                   "km+DIHXDnFuMqqRaIx9xVX5r/7WY8eOq8tDvg//0\nfOin/+fQ16HEOvxOOk" +
                                                   "rwTiFPI07xKBgOL0Wh7ZdDFl6oNTicPpib2N1lUCudIhOwf94NnGkmBuhY\n" +
                                                   "kFizKvIvWEQvDf1XL+UEmV7WVsnDZ27FLUzdHPTEF/tv/D3x6Z/YIBS6+0Zo" +
                                                   "sVO+ZZXUYWlN1jou\nS5loTKM6Ihx8+YLMmaTTA3PVAPcqFD1stSVML0i1fJ" +
                                                   "WS7ReksYQMcig/KiU6CDECIjm8xQl82oLn\nirxaxdQ9ovw8kJ5ZVFZeeDEO" +
                                                   "sMxXV+Mhfedzuxbm7tp2HwJkDVypx8fxDgRXOtUYFfCwc1JpgayP\nyYsv7D" +
                                                   "j1/OVBSZS1TBXZvkatThF6wOCJW5a+rCOwyRh/dc7L60889lUEm6Z/AEGDSH" +
                                                   "PPEAAA");
}
