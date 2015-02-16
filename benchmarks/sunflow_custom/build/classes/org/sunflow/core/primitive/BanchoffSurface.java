package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Instance;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Solvers;
import org.sunflow.math.Vector3;
public class BanchoffSurface implements PrimitiveList {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       1.5F);
                                                     if (o2w != null) bounds =
                                                                        o2w.
                                                                          transform(
                                                                            bounds);
                                                     return bounds;
    }
    public float getPrimitiveBound(int primID, int i) { return (i &
                                                                  1) ==
                                                          0
                                                          ? -1.5F
                                                          : 1.5F;
    }
    public int getNumPrimitives() { return 1; }
    public void prepareShadingState(ShadingState state) { state.init(
                                                                  );
                                                          state.getRay(
                                                                  ).
                                                            getPoint(
                                                              state.
                                                                getPoint(
                                                                  ));
                                                          Instance parent =
                                                            state.
                                                            getInstance(
                                                              );
                                                          Point3 n =
                                                            parent.
                                                            transformWorldToObject(
                                                              state.
                                                                getPoint(
                                                                  ));
                                                          state.
                                                            getNormal(
                                                              ).
                                                            set(
                                                              n.
                                                                x *
                                                                (2 *
                                                                   n.
                                                                     x *
                                                                   n.
                                                                     x -
                                                                   1),
                                                              n.
                                                                y *
                                                                (2 *
                                                                   n.
                                                                     y *
                                                                   n.
                                                                     y -
                                                                   1),
                                                              n.
                                                                z *
                                                                (2 *
                                                                   n.
                                                                     z *
                                                                   n.
                                                                     z -
                                                                   1));
                                                          state.
                                                            getNormal(
                                                              ).
                                                            normalize(
                                                              );
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
    public void intersectPrimitive(Ray r, int primID,
                                   IntersectionState state) {
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
        double A =
          rd2y *
          rd2y +
          rd2z *
          rd2z +
          rd2x *
          rd2x;
        double B =
          4 *
          (r.
             oy *
             rd2y *
             r.
               dy +
             r.
               oz *
             r.
               dz *
             rd2z +
             r.
               ox *
             r.
               dx *
             rd2x);
        double C =
          -rd2x -
          rd2y -
          rd2z +
          6 *
          (ro2y *
             rd2y +
             ro2z *
             rd2z +
             ro2x *
             rd2x);
        double D =
          2 *
          (2 *
             ro2z *
             r.
               oz *
             r.
               dz -
             r.
               oz *
             r.
               dz +
             2 *
             ro2x *
             r.
               ox *
             r.
               dx +
             2 *
             ro2y *
             r.
               oy *
             r.
               dy -
             r.
               ox *
             r.
               dx -
             r.
               oy *
             r.
               dy);
        double E =
          3.0F /
          8.0F +
          (-ro2z +
             ro2z *
             ro2z -
             ro2y +
             ro2y *
             ro2y -
             ro2x +
             ro2x *
             ro2x);
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
    public BanchoffSurface() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169362746000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1YXWwcVxW+u/530qztJI4bYid2nYKTdKcVpCik/NjGaZxu" +
       "kpWdH9VV617P3F1PPDsznbljbxxMm0pVoj5E/LghLWCJKlF/aJsKERWEKuWJ" +
       "tiovRQjEAy1vVEAe8lKQCpRz7p2/nV2vQx5Yae7M3Hv+vnPOPffMvnaDNLkO" +
       "2W1bxumiYfEsK/PsKWNvlp+2mZs9lNubp47LtFGDuu4xmJtWB97MfPLpd2Y7" +
       "0qR5imykpmlxynXLdCeYaxnzTMuRTDQ7ZrCSy0lH7hSdp4rHdUPJ6S7fnyPr" +
       "YqycDOYCExQwQQETFGGCMhxRAdMdzPRKo8hBTe4+Qb5NUjnSbKtoHif9lUJs" +
       "6tCSLyYvEICEVnw/AaAEc9khO0LsEnMV4Od2K8s/eKzjZw0kM0UyujmJ5qhg" +
       "BAclU2R9iZVmmOMOaxrTpkinyZg2yRydGvqisHuKdLl60aTcc1joJJz0bOYI" +
       "nZHn1quIzfFUbjkhvILODC14ayoYtAhYuyOsEuEBnAeA7ToY5hSoygKWxjnd" +
       "1DjZnuQIMQ4+BATA2lJifNYKVTWaFCZIl4ydQc2iMskd3SwCaZPlgRZOtq4q" +
       "FH1tU3WOFtk0Jz1JurxcAqo24Qhk4WRzkkxIgihtTUQpFp8bRx64cMY8aKaF" +
       "zRpTDbS/FZj6EkwTrMAcZqpMMq7flbtIu98+nyYEiDcniCXNW9+6+Y09fdff" +
       "lTSfq0FzdOYUU/m0enlmwwfbRof2NaAZrbbl6hj8CuQi/fP+yv6yDTuvO5SI" +
       "i9lg8frErx9+6lX2tzRpHyfNqmV4JcijTtUq2brBnAeZyRzKmTZO2pipjYr1" +
       "cdICzzndZHL2aKHgMj5OGg0x1WyJd3BRAUSgi1rgWTcLVvBsUz4rnss2IaQF" +
       "LrIXrk4if+LOia4cdyHdFapSUzctBZKXUUedVZhqTc+Ad2dL1JlzFdVzuVVS" +
       "XM8sGNaC4jqqYjnF8F21HKbYjl4CvPNMGaHACBZOeiJ1s5hy9v9TWRmRdyyk" +
       "UhCUbcmSYADVQcvQmDOtLnsjYzffmH4/HW4R32ec7AKdWV9nFnVmQ53ZhE6S" +
       "SglVm1C3jD1Ebg5qAFTH9UOTjx56/PxAAySdvdAIbkfSAcDsGzSmWqNRoRgX" +
       "5VCFbO158ZFz2X++9HWZrcrqVb0mN7l+aeHsiSfvTZN0ZXlGgDDVjux5LKph" +
       "8RxMbstacjPnPv7k6sUlK9qgFfXerxvVnLjvB5KhcCyVaVBJI/G7dtBr028v" +
       "DaZJIxQTKKCcQsJDbepL6qjY//uDWopYmgBwwXJK1MCloAC281nHWohmRI5s" +
       "wKFLpgsGMGGgKMMHfnn9+Wsv7N6XjlfsTOwMnGRc7v/OKP7HHMZg/k+X8t9/" +
       "7sa5R0TwgeKuWgoGcRyFagDRAI898+4Tf/zow8u/S0cJw+FY9GYMXS2DjLsj" +
       "LVArDKhXGNbB42bJ0vSCTmcMhnn3r8zO+679/UKHDJQBM0Gc96wtIJq/c4Q8" +
       "9f5j/+gTYlIqnlUR8ohMOmBjJHnYcehptKN89re9z79DfwylFMqXqy8yUZGI" +
       "QEaE67MiIkNivCexdi8OO+yqtbKY6fHfxEu/GAdx+Lz0Gz5+IU6ZEs+bIZ2q" +
       "dnc+2N3CaADTu9opJU7Yy08vr2hHr9wnd2dXZeUfg8bm9d//+zfZS39+r0Zp" +
       "aeOWfY/B5pkRM60FVVZUhcPiAI/2xrOv/PQt/sHur0iVu1YvCEnGd57+69Zj" +
       "X5t9/H+oBdsT4JMiXzn82nsP3q1+L00awjJQ1ZNUMu2PuwGUOgyaKBMdijPt" +
       "Itp9wgA8nDZiXO+Eq8s/scQdVzfaOG6SmxaHLybSJy38ma4TaoTKoOXBUAdk" +
       "3XGySXkfzo8LNd+sk6CHcBiGHerZGpziEMWhOg14kGSyIVGWuj6a+9HHr8uI" +
       "JjucBDE7v/zsZ9kLy+lYG3hXVScW55GtoLDyDunYz+CXgus/eCEEnJCtQNeo" +
       "34/sCBsS28Z90F/PLKHiwF+uLv3q5aVzad8l+zhpmbEsg1GzeuOKia+Gcd6C" +
       "kzvh6vbj3H3LcU5VbumeeABL0P5kD1PocMtfEhIerhPCR3E4wcmGIuMnLcfQ" +
       "RizP1NxA8LYqwWIdeucRq7wmQNFpDcDV6wPsva1EzuGQl8InOGmADwN8LAhu" +
       "VgfcKRxmOOkEcGHoJMAacuF8syhfE1QGJ7fD1e+D6r9lUHHb3DprHg5QLDvA" +
       "7iNeKTRdEBfWtHAdTn7Zz60gx24rr3qr6sfkLMXo47cjE2LO1MHxJA5lDmod" +
       "Bic8izPXikDjvKVrtwbviA8xgHpr8BqExIYA3qYqeBP0tPBxQDFQRTGOX6Wu" +
       "PPkjL5yv44ULODwDVUYPWMOA4srZNQHjUUD2wHXSB3zytjJuuc7aRRy+C5GC" +
       "jBuhUGCLlUl3f40uBDrBxMcA9kE9VX9DyE9n9Y2VTOuWleN/EO1t+HnbBt+Y" +
       "Bc8wYqdj/KRshswp6MLENtm12uL2Q/hiX/0jBdoMu8L8FyTXCmypJBdkHd7i" +
       "ZD/hZF2MDCq6/xQnugyFCIjw8YodpEuHaALxH4as/Jwuk4peza7s3OJNMZ6X" +
       "4o+eoOvw5F890+rVlUNHzty8/4poYZpUgy4uopTWHGmRrX7YufSvKi2Q1Xxw" +
       "6NMNb7btDI6rio+AhG3ba/fKYyWbi+528Rdbfv7ASysfimb9v/AhEZ2BEwAA");
}
