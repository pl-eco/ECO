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
public class Sphere implements PrimitiveList {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       1);
                                                     if (o2w != null) bounds =
                                                                        o2w.
                                                                          transform(
                                                                            bounds);
                                                     return bounds;
    }
    public float getPrimitiveBound(int primID, int i) { return (i &
                                                                  1) ==
                                                          0
                                                          ? -1
                                                          : 1; }
    public int getNumPrimitives() { return 1; }
    public void prepareShadingState(ShadingState state) { state.init();
                                                          state.getRay().
                                                            getPoint(
                                                              state.
                                                                getPoint());
                                                          Instance parent =
                                                            state.
                                                            getInstance();
                                                          Point3 localPoint =
                                                            parent.
                                                            transformWorldToObject(
                                                              state.
                                                                getPoint());
                                                          state.
                                                            getNormal().
                                                            set(
                                                              localPoint.
                                                                x,
                                                              localPoint.
                                                                y,
                                                              localPoint.
                                                                z);
                                                          state.
                                                            getNormal().
                                                            normalize();
                                                          float phi =
                                                            (float)
                                                              Math.
                                                              atan2(
                                                                state.
                                                                  getNormal().
                                                                  y,
                                                                state.
                                                                  getNormal().
                                                                  x);
                                                          if (phi <
                                                                0)
                                                              phi +=
                                                                2 *
                                                                  Math.
                                                                    PI;
                                                          float theta =
                                                            (float)
                                                              Math.
                                                              acos(
                                                                state.
                                                                  getNormal().
                                                                  z);
                                                          state.
                                                            getUV().
                                                            y =
                                                            theta /
                                                              (float)
                                                                Math.
                                                                  PI;
                                                          state.
                                                            getUV().
                                                            x =
                                                            phi /
                                                              (float)
                                                                (2 *
                                                                   Math.
                                                                     PI);
                                                          Vector3 v =
                                                            new Vector3(
                                                            );
                                                          v.
                                                            x =
                                                            -2 *
                                                              (float)
                                                                Math.
                                                                  PI *
                                                              state.
                                                                getNormal().
                                                                y;
                                                          v.
                                                            y =
                                                            2 *
                                                              (float)
                                                                Math.
                                                                  PI *
                                                              state.
                                                                getNormal().
                                                                x;
                                                          v.
                                                            z =
                                                            0;
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
                                                                getNormal());
                                                          v =
                                                            parent.
                                                              transformVectorObjectToWorld(
                                                                v);
                                                          state.
                                                            getNormal().
                                                            set(
                                                              worldNormal);
                                                          state.
                                                            getNormal().
                                                            normalize();
                                                          state.
                                                            getGeoNormal().
                                                            set(
                                                              state.
                                                                getNormal());
                                                          state.
                                                            setBasis(
                                                              OrthoNormalBasis.
                                                                makeFromWV(
                                                                  state.
                                                                    getNormal(),
                                                                  v));
    }
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
        float qa =
          r.
            dx *
          r.
            dx +
          r.
            dy *
          r.
            dy +
          r.
            dz *
          r.
            dz;
        float qb =
          2 *
          (r.
             dx *
             r.
               ox +
             r.
               dy *
             r.
               oy +
             r.
               dz *
             r.
               oz);
        float qc =
          r.
            ox *
          r.
            ox +
          r.
            oy *
          r.
            oy +
          r.
            oz *
          r.
            oz -
          1;
        double[] t =
          Solvers.
          solveQuadric(
            qa,
            qb,
            qc);
        if (t !=
              null) {
            if (t[0] >=
                  r.
                  getMax() ||
                  t[1] <=
                  r.
                  getMin())
                return;
            if (t[0] >
                  r.
                  getMin())
                r.
                  setMax(
                    (float)
                      t[0]);
            else
                r.
                  setMax(
                    (float)
                      t[1]);
            state.
              setIntersection(
                0,
                0,
                0);
        }
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    public Sphere() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169362916000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVYe2wcxRkf3/kRP1o/kjgmxHHsJEBet6RKWjVGIo7jkCOX" +
       "5PAlTnAAM96du9tk\nb2fZnbXPJgWiSnEAURq1IJDaNCoRCRQKCKr0QdsgoK" +
       "XNP1CprYQEbRWprdRSqapEU7V/9JuZ3du9\nPfuw7JN2bnfne8zve803+9In" +
       "qM6x0SrVSbApiziJwUwa2w7RBg3sOIfg1Zj6bl1j+uI+k8ZQTQrF\ndI2h1p" +
       "TqKBpmWNE1Jbm7v2ijTRY1pnIGZQlSZInjxnZP3p2p7RUCj5y73HHqQm1PDN" +
       "WlUCs2Tcow\n06k5ZJCCw1Bb6jiewIrLdENJ6Q7rT6HPEdMtDFLTYdhkzgPo" +
       "IRRPoXpL5TIZ6k35yhVQrljYxgVF\nqFfSQi1IWGoThnWTaAMldcC5uZwTlu" +
       "3xDVdSg5AlfHIE4IgVAOo1JdQSbQVUK35p5Isnz78QR62j\nqFU3M1yYCkgY" +
       "6BtFLQVSGCe2M6BpRBtF7SYhWobYOjb0aaF1FHU4es7EzLWJM0wcakxwwg7H" +
       "tYgt\ndPovU6hF5ZhsV2XULtkoqxND85/qsgbOAezOALaEu4e/B4BNOizMzm" +
       "KV+Cy1J3QTPN4T5ShhXLcP\nCIC1oUBYnpZU1ZoYXqAO6UsDmzklw2zdzAFp" +
       "HXVBC0Mr5xTKbW1h9QTOkTGGuqJ0aTkFVI3CEJyF\noeVRMiEJvLQy4qWQfz" +
       "Z1fnrm0rd+tlPEdq1GVIOvvwmYVkeYhkmW2MRUiWS87ia+mbzbXRVDCIiX\n" +
       "R4glzcD6y4dTf/15j6S5cRaag+PHicrG1ANne4YfvIOiOF/GEos6Ond+GXKR" +
       "Dmlvpr9oQdZ2liTy\nyYQ/eWX4F3c/8iL5Www1JVG9Sg23AHHUrtKCpRvEvo" +
       "OYxMaMaEnUSExtUMwnUQPcpyDk5duD2axD\nWBLVGuJVPRXPYKIsiOAmaoR7" +
       "3cxS/97CLC/uixZCqAEutBmudiR/4p+hbQnFcc2sQScVx1YVaudK\nzyq1iW" +
       "LZegEwTBAlY+XB3AkePRZDaSVPC0TBKjZ1kyo5HfJVpVs0MsH/FyCzyNfaMV" +
       "lTw4tfNIkN\niP+91NCIPaZevPbrk0P7Hj0jA4QHtYcSyg6oSniqElxVoqQq" +
       "IVWhmhqhYRlXKZ0EJj4ByQplrWVD\n5t477z/TF4fosCZrwT6ctA/weOsYUu" +
       "lgkNFJUfxUCKuu7x6bSVy/eLsMK2Xuwjsrd/P7L189/6+W\njTEUm70qcnxQ" +
       "l5u4mDQvpaVqty6aR7PJ/8dj+1//3dWPbgkyiqF1FYleyckTtS/qCZuqRIPS" +
       "F4i/\ncENr/AgaORtDtZD9UPHE+qGYrI7qKEvYfr/4cSwNKdScpXYBG3zKr1" +
       "hNLG/TyeCNCJE2PiyT0cId\nGVmgqJvXv1p/6+/fbH5XIPZLbGtoE8sQJhO2" +
       "PYiDQzYh8P6jZ9LfeOqTmWMiCLwoYLCzueOGrhaB\n5aaABTLVgGrBfbTusF" +
       "mgmp7V8bhBeDD9r3X91h/8/Wtt0uoGvPGdtvmzBQTvb9iFHrl6379XCzE1\n" +
       "Kt8pAhgBmUSzNJA8YNt4iq+jeOo33c/+En8bChkUD0efJqIeIIEMCTsmhHk3" +
       "iHFLZO5WPvSB7M1z\nRPUs+/KYevLFXJ/7wK9+JFbdjMMbfNgN+7HVL50qdC" +
       "8FpUL/lmid4rPLLT52chesiGbvXuzkQdi2\nKwfuaTOu/BfUjoJaFTZN56AN" +
       "VaNY5mmPuq7hw7fe7rz/gziK7UFNBsXaHiziHzVC4BEnDwWnaN2+\nUyyjbX" +
       "IJH4VdkFjtSs9KxbIn8bBWjDd50cPvbwlT1Yj7FZAhFfUq7dcr4TqA2j3XTi" +
       "l2+Zmj/2w5\njd+5VxaejvLdZwg6tL9MvU1uvu2JP81SLBsZtbYYZIIYoaU1" +
       "cJVlBW+/aCKCdH/she9dZh9s2iFV\nbpy71kUZN+44P92z45XHF1DmeiJGiI" +
       "pun7jxrnhefy8m+hxZ4Sr6o3Km/rA5QCmsx7VNblj+pkVE\n5ZpSVDZz3/bD" +
       "1eFFZUc0KkU94sOOSDLFhF1jVVzOoRJov7jLfbLOMFlG/g+kk0LNvirpehcf" +
       "9kK9\nci04DRDwZlf4IOFHF8d57XTfT987/J0Z6cgNVU4LYa4x9eE//PFE/M" +
       "m3xiVftCmLEJ9dfeHPr18b\nXibjT3auayuaxzCP7F4FmFaLZ0BvNQ2C+p1N" +
       "vS89NPyxWBHn28lQwzilBsEyqLbyISkzcPtn5rN4\n2F3u+i/A1em5vnPerq" +
       "8pz/ausE8L0J0l9mNowIvbhARcxasiJu9j6PM5wo5Q29B2UdfUHF/wqgrB\n" +
       "Yh5a+120GFhgbDEW2A5Xt2eB7gUFf8aSgo8yFIdDDb+1BCetglyQQTa3A/KS" +
       "4yX6iEzY5ilmAdrC\nYtD2epd/Pz+04YU/XGXuFB9OwrEaQB1wCyVcgtgKIH" +
       "xlMRC+BNd6D8L6hYZsd0W1yuQxDyx+aiZC\nzKNVgD7Bh9MMLbVsYmGbhJmj" +
       "/qudoLoWYJ9ZDPYDcO32sO+eN/a4kBj3sS+rwD6MpwIPAUVfBUWS\nH9Yd2Z" +
       "IFJnqmionO8eEpOJbrPmspHPjMk4FFnl6MRRJwjXgWGVlQQD9fZe4SH54DP0" +
       "NA78JQ5XNB\nTM+z1wlwXpgvziJsc/JUx3vfrooPP/JjhZr68MF7Pk399j/i" +
       "fFL6oNAMp/qsaxihHiDcD9RDxGZ1\nAa5ZdqiyVr3G0Mq5D5nQVFllyfyq5H" +
       "oDcj3KBRHP/8JklxlqDpHBLubdhYl+DOUTiPjtT0qR2Caa\nW/5NJyE/YJQ3" +
       "pdw+a8s2ePFFzu+mXPlNbkw9+vKxNcXHD31dtGh1qoGnp7mYphRqkKezUkfW" +
       "O6c0\nX9b76NVXRt78/pf9Tbns3FYRrlvlbBXPQxc4+7lpqGAxcdKZ/uGKN2" +
       "67eO7jmDi5/R9utY0eSBUA\nAA==");
}
