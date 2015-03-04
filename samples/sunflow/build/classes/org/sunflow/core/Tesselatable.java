package org.sunflow.core;
import org.sunflow.core.primitive.TriangleMesh;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
public interface Tesselatable extends RenderObject {
    public PrimitiveList tesselate();
    public BoundingBox getWorldBounds(Matrix4 o2w);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1WXWxURRSe3f6X2j/+yl+BtpBAYW+IosESIjSFFhfatAVD" +
                                "jSzTubO7l869c5k72y5FDZIYiQ/EKCiY2CeIQRGJkaAPJDwJBF8wRuOD6LPK" +
                                "Ay/6gCaembt39+5tq4kPbrKzs2fO75xzvjNXHqIqT6Bul7PjGcZlguZl4ijb" +
                                "mpDHXeol9ia3DmHhUbOXYc8bBVqKdFxr+v3xW9nmOKoeQwux43CJpcUdb5h6" +
                                "nE1SM4maStQ+Rm1PoubkUTyJjZy0mJG0PNmTRAtCohJ1JQMXDHDBABcM7YKx" +
                                "s8QFQk9QJ2f3KgnsSO8YehXFkqjaJco9idaWK3GxwHZBzZCOADTUqv8HISgt" +
                                "nBdoTTF2P+ZZAZ/rNs6+d7j5swrUNIaaLGdEuUPACQlGxlCDTe1xKrydpknN" +
                                "MdTiUGqOUGFhZk1rv8dQq2dlHCxzghYvSRFzLhXaZunmGoiKTeSI5KIYXtqi" +
                                "zAz+VaUZzkCsS0qx+hHuVnQIsN4Cx0QaExqIVE5YjinR6qhEMcau54EBRGts" +
                                "KrO8aKrSwUBArX7uGHYyxogUlpMB1iqeAysSLZ9XqbprF5MJnKEpidqifEP+" +
                                "EXDV6YtQIhItjrJpTZCl5ZEshfLzcP/2MyecfieufTYpYcr/WhBqjwgN0zQV" +
                                "1CHUF2zYmHwXL7l5Oo4QMC+OMPs8N15+9Nym9lt3fJ4Vc/AMjh+lRKbIxfHG" +
                                "+yt7N2yrUG7UutyzVPLLItflP1Q46cm70HlLihrVYSI4vDX81aGTH9Ff46h+" +
                                "AFUTznI21FEL4bZrMSr2UIcKLKk5gOqoY/bq8wFUA/uk5VCfOphOe1QOoEqm" +
                                "SdVc/4crSoMKdUU1sLecNA/2LpZZvc+7CKEa+KIYfDuR/1mgFolMI8ttamCC" +
                                "HcvhBtQuxYJkDUp4SlCXG329g8Y43HLWxmLCM7yck2Z8KkVynuS24QlicJEJ" +
                                "yAbhghqj1PMowxKPM5pQ1eb+T3byKt7mqVgMUrEyCgQMeqifM5OKFDmb29X3" +
                                "6GrqXrzYGIWbkmgVmEkUzCSUmUTYDIrFtPZFypyfZEjRBDQ7wGDDhpGX9h45" +
                                "3VEB1eVOVaoLzuvuawv+gGDELd3nu7+8deH6+93b4mFIaAqB7AiVfoG1lOyO" +
                                "CkqB/uP5oXfOPXzjRW0UODrnMtCl1l4oN8BQwKLX7xz74acHF7+NFx2tkIC7" +
                                "uXFmEYlq8TiAFiZSoroi+oQDien94rnuahgqlQq/iVS4q+ZrdA1SF0+dnTEH" +
                                "L23x27G1vHn6YDZ88t1fXyfO/3x3jjzVSe5uZnSSspBncWUSSqpgrY/wfRoD" +
                                "B/SIIYAAb17++Ia83/2sb3Lj/JMyKnj71C/LR3dkj8RRvHzaKetAqleSQ2pG" +
                                "FWfR6kjwUZWX9125u2c9eTuOKgpQNweslwv1hK8BjAoKc8hRF6oo9WC0I1r3" +
                                "ghNqwrAq2d24Bl9P3XylK44qAa9hRkkMmALw3x41XgaxPUFtKlNVcAlpLmzM" +
                                "1FEwY+plVvCpEkU3ZKPet0B6lqku6IBvawGE9K86XeiqdZHfwJp/hV7b1bLW" +
                                "z63adqilU1XW+lInAGAyqDeVjK4Djs1NK22pZlU9+WfTui3XfzvT7FcQA0qQ" +
                                "nU3/rqBEX7YLnbx3+I92rSZG1MAudWeJzW/ShSXNO4XAx5Uf+de+WXXhNv4A" +
                                "5glguGdNUw3LqIAMyqntOuJn9NoTOduhlidV1RfAiAY92D6rB4eEZcPYmdTx" +
                                "u7PUacLmYk6WKuK6Ql6C/PynnJR7HyuHirawmzaMpsQ+DK+P/FNaff8/hJ5U" +
                                "S69EjRkqX+CCmbt4zjG9QPHKWYr1ObxrdvH87OjzEjWEAV3lq23Wm9F/55Cr" +
                                "M021S2cOfK8bpfgWqYMHQTrHWKgPwz1Z7QqatrTrdX79u/pnBN7N0VxJVKl+" +
                                "tJfDPttBiRaE2CSqKezCTIckqgAmtR1z3TwqmzJudOZ0lqGcfkcHiJTzX9Ip" +
                                "8unM3v0nHj19ScNbFbzAp6f1uwuekX6bF1Ft7bzaAl3V/RseN16rWxcvZLBR" +
                                "La2hOgr5tnruLuyzXan7ZvqLpZ9v/3DmgR5VfwNEfv904AwAAA==");
}
