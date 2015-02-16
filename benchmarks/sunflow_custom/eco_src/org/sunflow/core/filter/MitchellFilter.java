package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class MitchellFilter implements Filter {
    public float getSize() { return 4.0F; }
    public float get(float x, float y) { return this.mitchell(x) * this.mitchell(
                                                                          y);
    }
    private float mitchell(float x) { final float B = 1 / 3.0F;
                                      final float C = 1 / 3.0F;
                                      final float SIXTH = 1 / 6.0F;
                                      x = Math.abs(x);
                                      float x2 = x * x;
                                      if (x > 1.0F) return ((-B -
                                                               6 *
                                                               C) *
                                                              x *
                                                              x2 +
                                                              (6 *
                                                                 B +
                                                                 30 *
                                                                 C) *
                                                              x2 +
                                                              (-12 *
                                                                 B -
                                                                 48 *
                                                                 C) *
                                                              x +
                                                              (8 *
                                                                 B +
                                                                 24 *
                                                                 C)) *
                                                      SIXTH;
                                      return ((12 - 9 * B - 6 * C) *
                                                x *
                                                x2 +
                                                (-18 +
                                                   12 *
                                                   B +
                                                   6 *
                                                   C) *
                                                x2 +
                                                (6 -
                                                   2 *
                                                   B)) * SIXTH; }
    public MitchellFilter() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026718000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1XfWwURRSf3vW7lSsF2lKghVIqUHoLiZjQkgCWFgpXqC3l" +
                                                   "o4Blujt33XZvd9md\na68FEWJCEeIHURNNFIkhKSCICRo0QYUAivIPmKgJCa" +
                                                   "ghURPFxJggRv/wzcx97l0L4ZKd2515n/Pe\n+82bU3dRlm2h6bLtpUMmsb2N" +
                                                   "HW3YsonSqGHb3gBT3fKVrLy20bW64UIZPuRSFYo8PtmWFEyxpCpS\ny8qGsI" +
                                                   "VqTUMbCmgG9ZIw9fZpiyPy1vgWpwjcdORc8b5jmZUulOVDHqzrBsVUNfQmjQ" +
                                                   "Rtiop8fXgA\nSyGqapJPtWmDDz1G9FCw0dBtinVq70R7kNuHsk2ZyaRoli+q" +
                                                   "XALlkoktHJS4eqmNqwUJkyxCsaoT\nZUVMHXAuSOYEsyN87anUICSXLW4Ed7" +
                                                   "gF4PXMmNfC2xRXTffxjU/uPnrCjTxdyKPqHUyYDJ5Q0NeF\nCoMk2EMse4Wi" +
                                                   "EKULTdQJUTqIpWJNHeZau1CxrQZ0TEMWsduJbWgDjLDYDpnE4jqjkz5UKDOf" +
                                                   "rJBM\nDSu2R36VaEr0K8uv4QC4XRJ3W7jbzObBwXwVDLP8WCZRlsx+VYeIVz" +
                                                   "o5Yj5WrwUCYM0JEtprxFRl\n6hgmULGIpYb1gNRBLVUPAGmWEQItFJWPKZTt" +
                                                   "tYnlfhwg3RSVOenaxBJQ5fGNYCwUTXGScUkQpXJH\nlBLiU1ty78Dxtz5bzn" +
                                                   "M7UyGyxuzPB6YKB1M78ROL6DIRjPdD3tdatoSmuxAC4ikOYkGzYs65Tt+v\n" +
                                                   "n1cKmmlpaNb39BGZdsvrDle271plIDczI9c0bJUFP8lzXg5tkZWGsAlVWxKT" +
                                                   "yBa90cUL7V9s2XuS\n/OZC+S0oWza0UBDyaKJsBE1VI9YqohMLU6K0oDyiK4" +
                                                   "18vQXlwLsPUl7Mrvf7bUJbUKbGp7IN/g1b\n5AcRbIvy4F3V/Ub03cS0l7+H" +
                                                   "TYRQDjyoDp4sJH78n6IlXskO6X7NGJRsS5YMKxD7lg2LSCAdMkNq\nVancSz" +
                                                   "StmX96WQqZFHVKvUaQSFjGuqobUkCFopWNOoUMsP9HFRxmVhcPZmQwGHSWsw" +
                                                   "aVsNrQFGJ1\ny6N3vt7dtPaFAyJVWHpH/KWoBvR5I/q8TJ9X6PMm60MZGVzN" +
                                                   "ZKZXxAx2vB9qF1CucF7H9jU7DlS5\nIVnMwUzYLkZaBZ5FjGmSjcZ4gbdwLJ" +
                                                   "Qhy8re3TrivT+6TGSZNDYOp+UuuH762tG/Cue7kCs9SDIn\nAabzmZg2hqwx" +
                                                   "8Kt2llU6+X8cbD373bVbc+MFRlF1St2ncrK6rXKGwzJkogASxsUfm+pxb0Ib" +
                                                   "D7tQ\nJoABACC3H7ClwqkjqX4boljIfMnxoQK/YQWxxpaiAJZPey1jMD7D86" +
                                                   "SIDZNFyrBAOgzkMHr/+eyF\n358vuMI9jiKuJ+FM6yBU1O/EeB5ssAiB+Vtv" +
                                                   "tL36+t2RrTwJIllA4aAL9WiqHAaWmjgLFK4G4MFi\nVN2pBw1F9au4RyMsmf" +
                                                   "7zzFn00e8vFYld12AmGrQFDxYQn5/6FNp77Zm/K7iYDJkdHHE34mTCm0lx\n" +
                                                   "ySssCw8xO8L7vpnx5pf4bcA1wBJbHSYcHhD3DPF99PLtncfHOsfaQjZUgewF" +
                                                   "Y2R1mmO6W959MlAV\n2vnVJ9zqApx43ieGoRWbDSKoXPckUOpFkSEJttjqFJ" +
                                                   "ONJSwEpc7qXY3tXhD2xIV124q0C/+C2i5Q\nK8MZaq+3ADrCSZGOUGfl3Lx4" +
                                                   "qWTHDTdyNaN8zcBKM+b5j/Ig8YjdC6gTNpct52YUDeayke8L4taW\nR3YpnP" +
                                                   "TFP2bzsSaSPex9biJVBn8vpag0BbQESjEfZ4x1YvLTfmTzn4X78eXtAnGKk0" +
                                                   "+hJujUfhm6\nRB5f+uJPaaAyjxpmnUYGiJZgk5upTEK6Vt5MxOv84In3ztEb" +
                                                   "tfVC5fyxQc7JOL/+6HBl/ZlDj4Bv\nlY5NcIqeODDtaXevetXF+x0BbSl9Uj" +
                                                   "JTQ+J2gFKwJ2TpbGPZTCFPx5mxdCxgQS2HJzuSjtnOdORA\nxIb6capo7Thr" +
                                                   "rWxYTVFOgNAOKE8IRFniXcBSg9BTDHDQvLO/6tOrne+MiBjMG6fhT+Tqlp/7" +
                                                   "4cd+\n98sXewSfs69yEB+uOPbz2Tvtk0XqiOZzdkr/l8gjGlDujcdkyTtrPA" +
                                                   "2c+nLtrFN72m9zixjfcsqA\n2sDiarGIDS2iZhY/sAL5x8rkmNXAkx+JWf5D" +
                                                   "x8zFJbrYZwcfOOn2caLXzYYuitwQPc4Rt3/rI9pf\nyCZnwzMhYv+EtPanQR" +
                                                   "pIItNSB6CzDDtMzohDkfCpbxyfeAMOQc8NRnonh2PkYR0LUzQhuf9ip1RZ\n" +
                                                   "yo1N3DJk381d2+75vv2HdxKxm0ABtOP+kKYlFG1iAWebFvGr3O4CcZaY/M9O" +
                                                   "B6+iJ6TsTsZeuLGW\noB+AW6+TnqJM9pdINkRRQQIZ7HjkLZFoN+QDELHXZ8" +
                                                   "0o2BfxA4hdw7zizpF8cLCdmZ1U0PwSHQW+\nkLhGd8ubT2+dGT604RWOpllw" +
                                                   "/R4e5vcluP6JDioGnrPGlBaVdR19cGbj+feXRIswqbdKydBFYnWc\n2ANgp+" +
                                                   "9tmoIm5d3I8MelHy4dPXLbxbur/wGCcovZ+xAAAA==");
}
