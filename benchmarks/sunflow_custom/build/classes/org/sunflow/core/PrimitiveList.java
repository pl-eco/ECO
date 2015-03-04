package org.sunflow.core;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
public interface PrimitiveList extends RenderObject {
    public BoundingBox getWorldBounds(Matrix4 o2w);
    public int getNumPrimitives();
    public float getPrimitiveBound(int primID, int i);
    public void intersectPrimitive(Ray r, int primID, IntersectionState state);
    public void prepareShadingState(ShadingState state);
    public PrimitiveList getBakingPrimitives();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0XaWwUVfjt9i7QixtLC6VoCrgTUDRSYqSlSHEptcWrRtfX" +
                                "mbe7Q2fmDW/elqWIB4kBTSQexSvaXxjvI0aixpjwyyP6R2M0/vD4p1H5wR/9" +
                                "4YHf92Znd3Z6GUPc5L2d+d73vvuaV8+RKk+QjS63DmcsLhMsLxMHrK0Jedhl" +
                                "XmJPcusgFR4zei3qefsBltI73mz87Y9Hsk1xUj1CFlPH4ZJKkzveEPO4Nc6M" +
                                "JGksQfssZnuSNCUP0HGq5aRpaUnTk91JsiB0VZLOZCCCBiJoIIKmRNB2lLDg" +
                                "0iLm5OxevEEd6R0k95BYklS7OoonydpyIi4V1C6QGVQaAIVafL8ZlFKX84Ks" +
                                "Keru6zxN4VMbtckn72x6q4I0jpBG0xlGcXQQQgKTEbLQZvYoE94Ow2DGCGl2" +
                                "GDOGmTCpZU4ouUdIi2dmHCpzghWNhMCcy4TiWbLcQh11EzldclFUL20yywje" +
                                "qtIWzYCuy0q6+hruQjgoWG+CYCJNdRZcqRwzHUOS9uiNoo6dNwACXK2xmczy" +
                                "IqtKhwKAtPi+s6iT0YalMJ0MoFbxHHCRZNWsRNHWLtXHaIalJFkRxRv0jwCr" +
                                "ThkCr0iyNIqmKIGXVkW8FPLPuYHtJ484u524ktlguoXy18KltsilIZZmgjk6" +
                                "8y8u3JB8gi774EScEEBeGkH2cd65+/x1m9rOfuzjXDIDzr7RA0yXKf30aMPn" +
                                "rb1d11SgGLUu90x0fpnmKvwHCyfdeRcyb1mRIh4mgsOzQx/edt/L7Jc4qe8n" +
                                "1Tq3cjbEUbPObde0mLieOUxQyYx+Uscco1ed95MaeE6aDvOh+9Jpj8l+Umkp" +
                                "UDVX72CiNJBAE9XAs+mkefDsUplVz3mXEFIDi8Rg9RH/V4ebJEzLcptpVKeO" +
                                "6XANYpdRoWc1pvOUYC7X+nr3aaNg5axNxZineTknbfFDKT3nSW5rntA1LjIB" +
                                "WNO5YNqgMG3Qe5xhbUhguLn/F6M8atx0KBYDZ7RGS4EFWbSbWwYTKX0y19N3" +
                                "/vXUp/FiahRsJUkb8EkU+CSQT6KMD4nFFPklyM/3M3hpDPIdDhd2Dd+x564T" +
                                "HRUQYO6hSrRxXiXgiuAFLkbkUqm+672zT595ZuM18XBVaAzV2WEm/RhrLvHd" +
                                "LxgD+LdPDT5+6tzx2xVTwFg3E4NO3Hsh4qCMQjl64OOD33z/3ekv40VBKySU" +
                                "3tyoZeqS1NJRqFtUl5LUFQtQWJGYel4qyeppxhqCYGXCzyNUd/Vsua7q1Olj" +
                                "k1PGvuc3+xnZUp4/fdAeXvvqr88ST/3wyQyOqpPcvdxi48wKSVaNLCGoCtz6" +
                                "dL5XlcF+1WV0KAIPvfTKO/Lzjdt8lhtmb5bRix8d+3nV/muzd8VJvLzhIXcA" +
                                "1ePNQWxTxXbUHlE+SvKlva9+cv2l+mNxUlGodjNU9vJL3WEzAFPBoBU5aFCE" +
                                "1APTjmjgC64zA/pVie+GNfRM6oOjnXFSCSUb2pSkUFagA7RFmZdV2e4gNpFV" +
                                "FRghzYVNLTwK2ky9zAp+qARRGdmgnpvBPcsxC9bDWlqoQ+ofTxe7uC/xM1jh" +
                                "X6L2NtzWKt/G8bEDt3UYWZeWMgFqpgXxhs7ovMmxuWGmTTpqqYT9s3H95jO/" +
                                "nmzyI8gCSOCdTfMTKMFX9pD7Pr3z9zZFJqZjzy5lZwnNT9LFJco7hKCHUY78" +
                                "/V+sfvoj+hy0FCjjnjnBVGWOlSfUinBC2VDDE3sptOn8lcow2xXa1WrvRssV" +
                                "ygq+X4fbFZI0ZJi8hQvL6OE5x/ACwq3TCKtzGAB6OMgcpagAlxf91ojAdlit" +
                                "Bb+1/le/za7AnjnOkrj1wegJyg3k7GJF9sDYXXPMuwGe3/+1oy3fjz3702t+" +
                                "6kcHiggyOzH50IXEycl4aOpaN23wCd/xJy8l7yLfchfgF4P1Ny5UBgF+523p" +
                                "LbT/NcX+77oY1mvnEkux2PXjG0fff/Ho8XjBOF2SVECZnteJuEgHrMsKTrzs" +
                                "4jgxXkK4UW2Kzh1zuDOF262SNIM7i/r58YonA7gN+Wz2S6w5nM6v3gIEDsDa" +
                                "UlBvy8VRr8Lvj0EiLZne8uhhpXaA0TENox+bqOdXCPziYEqUA3NYSEmM87oZ" +
                                "XC3F/AwmqhznpvHvLHQ1rG0FC227OBaKzTcUDGcpVpqS6kfmUP1e3MYlyASD" +
                                "IRUsfBmPxLx6rkTgJlg7C3ruvPjV6vgcZw/idgw0gPDuoVA6MuXOa5quQV6S" +
                                "RWVzJraRFdO+Zv0vMP31qcba5VM3fa36d/ErqQ4+VdI5ywqNB+FRoRrsmTaV" +
                                "gHV+W3bV30koq1GPQUThnxLzYR/tUUkWhNAkqSk8hZEmoRQBEj6egnJGyoZf" +
                                "NzoKryur3OoLPxiUcv43fkp/Y2rPwJHzVz2vpq4q3aITE0ilFj5w/emjOGyt" +
                                "nZVaQKt6d9cfDW/WrQ8KZwNuLaFgCMnWPvNw0Ge7UrXziXeXv739hanv1AT9" +
                                "D69d7OZ6EQAA");
}
