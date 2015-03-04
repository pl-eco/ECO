package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Instance;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.MathUtils;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Solvers;
import org.sunflow.math.Vector3;
public class Torus implements PrimitiveList {
    private float ri2;
    private float ro2;
    private float ri;
    private float ro;
    public Torus() { super();
                     ri = 0.25F;
                     ro = 1;
                     ri2 = ri * ri;
                     ro2 = ro * ro; }
    public boolean update(ParameterList pl, SunflowAPI api) { ri = pl.getFloat(
                                                                        "radiusInner",
                                                                        ri);
                                                              ro =
                                                                pl.
                                                                  getFloat(
                                                                    "radiusOuter",
                                                                    ro);
                                                              ri2 =
                                                                ri *
                                                                  ri;
                                                              ro2 =
                                                                ro *
                                                                  ro;
                                                              return true;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       -ro -
                                                         ri,
                                                       -ro -
                                                         ri,
                                                       -ri);
                                                     bounds.
                                                       include(
                                                         ro +
                                                           ri,
                                                         ro +
                                                           ri,
                                                         ri);
                                                     if (o2w !=
                                                           null)
                                                         bounds =
                                                           o2w.
                                                             transform(
                                                               bounds);
                                                     return bounds;
    }
    public float getPrimitiveBound(int primID, int i) { switch (i) {
                                                            case 0:
                                                            case 2:
                                                                return -ro -
                                                                  ri;
                                                            case 1:
                                                            case 3:
                                                                return ro +
                                                                  ri;
                                                            case 4:
                                                                return -ri;
                                                            case 5:
                                                                return ri;
                                                            default:
                                                                return 0;
                                                        }
    }
    public int getNumPrimitives() { return 1; }
    public void prepareShadingState(ShadingState state) {
        state.
          init(
            );
        state.
          getRay(
            ).
          getPoint(
            state.
              getPoint(
                ));
        Instance parent =
          state.
          getInstance(
            );
        Point3 p =
          parent.
          transformWorldToObject(
            state.
              getPoint(
                ));
        float deriv =
          p.
            x *
          p.
            x +
          p.
            y *
          p.
            y +
          p.
            z *
          p.
            z -
          ri2 -
          ro2;
        state.
          getNormal(
            ).
          set(
            p.
              x *
              deriv,
            p.
              y *
              deriv,
            p.
              z *
              deriv +
              2 *
              ro2 *
              p.
                z);
        state.
          getNormal(
            ).
          normalize(
            );
        double phi =
          Math.
          asin(
            MathUtils.
              clamp(
                p.
                  z /
                  ri,
                -1,
                1));
        double theta =
          Math.
          atan2(
            p.
              y,
            p.
              x);
        if (theta <
              0)
            theta +=
              2 *
                Math.
                  PI;
        state.
          getUV(
            ).
          x =
          (float)
            (theta /
               (2 *
                  Math.
                    PI));
        state.
          getUV(
            ).
          y =
          (float)
            ((phi +
                Math.
                  PI /
                2) /
               Math.
                 PI);
        state.
          setShader(
            parent.
              getShader(
                0));
        state.
          setModifier(
            parent.
              getModifier(
                0));
        Vector3 worldNormal =
          parent.
          transformNormalObjectToWorld(
            state.
              getNormal(
                ));
        state.
          getNormal(
            ).
          set(
            worldNormal);
        state.
          getNormal(
            ).
          normalize(
            );
        state.
          getGeoNormal(
            ).
          set(
            state.
              getNormal(
                ));
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                state.
                  getNormal(
                    )));
    }
    public void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        float rd2x =
          r.
            dx *
          r.
            dx;
        float rd2y =
          r.
            dy *
          r.
            dy;
        float rd2z =
          r.
            dz *
          r.
            dz;
        float ro2x =
          r.
            ox *
          r.
            ox;
        float ro2y =
          r.
            oy *
          r.
            oy;
        float ro2z =
          r.
            oz *
          r.
            oz;
        double alpha =
          rd2x +
          rd2y +
          rd2z;
        double beta =
          2 *
          (r.
             ox *
             r.
               dx +
             r.
               oy *
             r.
               dy +
             r.
               oz *
             r.
               dz);
        double gamma =
          ro2x +
          ro2y +
          ro2z -
          ri2 -
          ro2;
        double A =
          alpha *
          alpha;
        double B =
          2 *
          alpha *
          beta;
        double C =
          beta *
          beta +
          2 *
          alpha *
          gamma +
          4 *
          ro2 *
          rd2z;
        double D =
          2 *
          beta *
          gamma +
          8 *
          ro2 *
          r.
            oz *
          r.
            dz;
        double E =
          gamma *
          gamma +
          4 *
          ro2 *
          ro2z -
          4 *
          ro2 *
          ri2;
        double[] t =
          Solvers.
          solveQuartic(
            A,
            B,
            C,
            D,
            E);
        if (t !=
              null) {
            if (t[0] >=
                  r.
                  getMax(
                    ) ||
                  t[t.
                      length -
                      1] <=
                  r.
                  getMin(
                    ))
                return;
            for (int i =
                   0;
                 i <
                   t.
                     length;
                 i++) {
                if (t[i] >
                      r.
                      getMin(
                        )) {
                    r.
                      setMax(
                        (float)
                          t[i]);
                    state.
                      setIntersection(
                        0,
                        0,
                        0);
                    return;
                }
            }
        }
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YXWxcxRWeXf87xrv+iWNMYjvGiUic7m0iaJUakcYrmzjd" +
                                                    "xFZsUmEE6/G9s+sb371zuXfW3jhxGxK1iUCKKmpoaKmlVkFpID+AiEJVIeWl" +
                                                    "BURfqKpWfShUfSkqzUMeSlFpS8/M/d2763WIqlqa8ezMOWfON+dv5l66iWos" +
                                                    "Ew0YVDua1ShLkAJLHNEeSLCjBrES+1MPjGPTIkpSw5Y1CXNpue/V2CeffW82" +
                                                    "HkW1U6gN6zplmKlUtw4Ri2rzREmhmD87rJGcxVA8dQTPYynPVE1KqRYbTKF1" +
                                                    "AVaG+lOuChKoIIEKklBB2utTAdNdRM/nkpwD68x6En0LRVKo1pC5egxtLhZi" +
                                                    "YBPnHDHjAgFIqOe/DwMowVwwUa+H3cZcAvi5AWn5B0/EX69CsSkUU/UJro4M" +
                                                    "SjDYZAo15UhuhpjWXkUhyhRq0QlRJoipYk1dFHpPoVZLzeqY5U3iHRKfzBvE" +
                                                    "FHv6J9ckc2xmXmbU9OBlVKIp7q+ajIazgLXDx2ojHOHzALBRBcXMDJaJy1I9" +
                                                    "p+oKQz1hDg9j/zeAAFjrcoTNUm+rah3DBGq1badhPStNMFPVs0BaQ/OwC0Nd" +
                                                    "qwrlZ21geQ5nSZqhzjDduL0EVA3iIDgLQ+vDZEISWKkrZKWAfW4efPDsMX2f" +
                                                    "HhU6K0TWuP71wNQdYjpEMsQkukxsxqbtqedxx1tnoggB8foQsU1z/fitr+/o" +
                                                    "vvGOTXNPGZqxmSNEZmn5/Ezz+xuT23ZXcTXqDWqp3PhFyIX7jzsrgwUDIq/D" +
                                                    "k8gXE+7ijUO/evTEy+TjKGocRbUy1fI58KMWmeYMVSPmw0QnJmZEGUUNRFeS" +
                                                    "Yn0U1cE4perEnh3LZCzCRlG1JqZqqfgNR5QBEfyI6mCs6hnqjg3MZsW4YCCE" +
                                                    "6qChAWityP4T/xnKSrM0RyQsY13VqQS+S7Apz0pEpmmTGFQaTo5JM3DKszls" +
                                                    "zlmSldczGl1Iy3mL0ZxkmbJEzaw7LcnUJJJhqjnAPU+kSWrmrQR3OOP/t1WB" +
                                                    "o44vRCJgkI3hdKBBJO2jmkLMtLycHxq+dSX9XtQLD+e8GOqFnRLOTgm+U8Lb" +
                                                    "KSF2QpGI2KCd72hbG2w1B1EP+bBp28Tj+6fP9FWBmxkL1XDQnLQPgDpqDMs0" +
                                                    "6aeGUZEAZfDPzp8+djrx6YU9tn9Kq+fxstzoxrmFpw5/+8tRFC1OyBwWTDVy" +
                                                    "9nGeRr102R8OxHJyY6c/+uTq80vUD8miDO9kilJOHul9YQOYVCYK5E5f/PZe" +
                                                    "fC391lJ/FFVD+oCUyTC4OGSj7vAeRRE/6GZPjqUGAGeomcMaX3JTXiObNemC" +
                                                    "PyM8o1mMW8Ao63gIxKF1ODEh/vPVNoP37bYncSuHUIjsPPLzGy9c++HA7mgw" +
                                                    "kccCpXGCMDsttPhOMmkSAvN/PDf+/edunn5MeAhQ3Ftug37eJyFJgMngWL/z" +
                                                    "zpN/+PCD87+N+l7FoFrmZzRVLoCMrf4ukEI0SGPc9v2P6DmqqBkVz2iEO+e/" +
                                                    "Ylt2Xvvb2bhtTQ1mXGfYsbYAf/7uIXTivSf+0S3ERGRewnzkPpl9AG2+5L2m" +
                                                    "iY9yPQpP/WbTC2/jH0OGhaxmqYtEJCokkCFx9JIw1XbRJ0JrO3nXa5SsFcRM" +
                                                    "p/hVDVtvWz2IRnglDgTfP8e0mZN//lQgKgmfMgUoxD8lXXqxK/nQx4Lf92PO" +
                                                    "3VMoTUVwa/F5d72c+3u0r/aXUVQ3heKycyU6jLU895YpuAZY7j0Jrk1F68Ul" +
                                                    "3a5fg16cbgzHUGDbcAT5KRDGnJqPG0NB08RP+R5obU7QtIWDJoLEYLdg6RP9" +
                                                    "Ft7d5/psHeTSeczvW6jKVHdVNtK4m3ftCi4ttX449+JHl+0MGbZIiJicWX76" +
                                                    "88TZ5Wjg3nRvydUlyGPfnQTku2zIn8NfBNp/eONQ+YRdO1uTTgHv9Sq4YfBI" +
                                                    "3FxJLbHFyF+uLv3iZ0unbRitxdeGYbgVX/7dv3+dOPend8vUJrAZxSJo47b7" +
                                                    "3//FjTPKu0F+/nQXHw6tLu5uaO2OuPZVxKUccVFT/R9IG/Ok0YA0YZT+QHRH" +
                                                    "xHg91ImSYu0duEg0YJFNq104hTXOn1xeUcZe2hl1cssIQw2MGl/SyDzRAjvW" +
                                                    "cUlFVfyAuGL7cfz0xVeus/cHvmbbdfvqbh1mfPvkX7smH5qd/gK1uyeEKSzy" +
                                                    "4oFL7z68VX42iqq8dFDyaihmGixOAo0mgWeOPlmUCro9g7a5Bu12DNpdtn76" +
                                                    "dvMzeVScZ7SCBTlUAo8SbkGXrCNINmH/3zs+KraZrlArMrx7HIpl3lAgSgXN" +
                                                    "Ht4l7WoxDDlphlKNYL20oIiJRz3QG/jkFmhbHdBbbxt0pNhtO4NocnBbTxzA" +
                                                    "8CAr3C8k0Ap4xCRYszlL2DepqSlDNK8rlit4Y4lgsQ5PvSFaWBMgb6gPWsIB" +
                                                    "mLgjq4bOtwresXx4XHAfqwDuBO+gNLQAOC+OBQCRDNbUP8Yne6DtcfTfc9v6" +
                                                    "B9X4boW1M7w7xVAcVDyYz3laCuLja2oo7p1fhZZ0NEzeqQttKombiVnMDc2/" +
                                                    "ahAh5mwFHM/y7hkG28L7C5skyFzOiNXzVFVuD95BaNMOvOnbhlclJFa58NpL" +
                                                    "4B3CR8UZuxR9JRSj/HuJZV8+/VP4UYVT+AnvzkE5V11Wz6B8ZXlNwDwFoh3Q" +
                                                    "TjmAT92Rx12osHaRd+fBUuBxQxhuMtlip5socxGGm4J4qvILeGfJZzH7U458" +
                                                    "ZSVWv2Hlkd+Lx5f3uaUhheozeU0LXggD41rwl4wqFGuwr4d2db7CUNfqD2co" +
                                                    "qkaR0pdtrtcgkMJc4Gv8X5DsDYbWBcggZTujINF1yDRAxIdvGq6TxMXrg1+P" +
                                                    "E/b1uIACRR05KdH9VfQa43VbfHh0a2ze/vSYlq+u7D947NZXXhIFu0bW8OIi" +
                                                    "l1KfQnX2Q9Sr05tXlebKqt237bPmVxu2uPePZt61Oq/PkG495R9pwzmDiWfV" +
                                                    "4psb3njwwsoH4pX4X2jSR34RFgAA");
}
