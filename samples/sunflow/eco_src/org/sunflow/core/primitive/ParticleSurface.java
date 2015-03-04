package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.core.ParameterList.FloatParameter;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Solvers;
import org.sunflow.math.Vector3;
public class ParticleSurface implements PrimitiveList {
    private float[] particles;
    private float r;
    private float r2;
    private int n;
    public ParticleSurface() { super();
                               particles = null;
                               r = (r2 = 1);
                               n = 0; }
    public int getNumPrimitives() { return n; }
    public float getPrimitiveBound(int primID, int i) { float c = particles[primID *
                                                                              3 +
                                                                              (i >>>
                                                                                 1)];
                                                        return (i &
                                                                  1) ==
                                                          0
                                                          ? c -
                                                          r
                                                          : c +
                                                          r;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) {
        BoundingBox bounds =
          new BoundingBox(
          );
        for (int i =
               0,
               i3 =
                 0;
             i <
               n;
             i++,
               i3 +=
                 3)
            bounds.
              include(
                particles[i3],
                particles[i3 +
                            1],
                particles[i3 +
                            2]);
        bounds.
          include(
            bounds.
              getMinimum().
              x -
              r,
            bounds.
              getMinimum().
              y -
              r,
            bounds.
              getMinimum().
              z -
              r);
        bounds.
          include(
            bounds.
              getMaximum().
              x +
              r,
            bounds.
              getMaximum().
              y +
              r,
            bounds.
              getMaximum().
              z +
              r);
        return o2w ==
          null
          ? bounds
          : o2w.
          transform(
            bounds);
    }
    public void intersectPrimitive(Ray r, int primID,
                                   IntersectionState state) {
        int i3 =
          primID *
          3;
        float ocx =
          r.
            ox -
          particles[i3 +
                      0];
        float ocy =
          r.
            oy -
          particles[i3 +
                      1];
        float ocz =
          r.
            oz -
          particles[i3 +
                      2];
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
             ocx +
             r.
               dy *
             ocy +
             r.
               dz *
             ocz);
        float qc =
          ocx *
          ocx +
          ocy *
          ocy +
          ocz *
          ocz -
          r2;
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
                primID,
                0,
                0);
        }
    }
    public void prepareShadingState(ShadingState state) {
        state.
          init();
        state.
          getRay().
          getPoint(
            state.
              getPoint());
        Point3 localPoint =
          state.
          getInstance().
          transformWorldToObject(
            state.
              getPoint());
        localPoint.
          x -=
          particles[3 *
                      state.
                      getPrimitiveID() +
                      0];
        localPoint.
          y -=
          particles[3 *
                      state.
                      getPrimitiveID() +
                      1];
        localPoint.
          z -=
          particles[3 *
                      state.
                      getPrimitiveID() +
                      2];
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
        state.
          setShader(
            state.
              getInstance().
              getShader(
                0));
        state.
          setModifier(
            state.
              getInstance().
              getModifier(
                0));
        Vector3 worldNormal =
          state.
          getInstance().
          transformNormalObjectToWorld(
            state.
              getNormal());
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
              makeFromW(
                state.
                  getNormal()));
    }
    public boolean update(ParameterList pl, SunflowAPI api) {
        FloatParameter p =
          pl.
          getPointArray(
            "particles");
        if (p !=
              null)
            particles =
              p.
                data;
        r =
          pl.
            getFloat(
              "radius",
              r);
        r2 =
          r *
            r;
        n =
          pl.
            getInt(
              "num",
              n);
        return particles !=
          null &&
          n <=
          particles.
            length /
          3;
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169362834000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVZeWwU1xl/3vWBj+ALjOMAxsaEcu0WC6exnQqMMcGwPmpz" +
       "mlDnefbtemB2ZjLz\n1iwOpaGRgAa1DU2jtlI4UqFyNDRISUQrJQSUkNLQSg" +
       "G1iYQUeqA2ldq0iSJR0vaPft+b2Z3ZWe/i\nBMXSvJ2Z9x3v913ve+MXPiQF" +
       "pkFmSmaA79KZGegc7KeGycKdCjXN9fBqWLpUUNx/Yp2q+UheiPjk\nMCflIc" +
       "kMhimnQTkc7F7VnjDIIl1TdkUVjQdYgge2Ky22vLWhlgyBm46cq9p7PL/eRw" +
       "pCpJyqqsYp\nlzW1S2Exk5OK0HY6RoNxLivBkGzy9hC5h6nxWKemmpyq3HyM" +
       "7CH+ECnUJZTJSUMoqTwIyoM6NWgs\nKNQH+4VakFBtME5llYU7UuqAc3E6Jy" +
       "zb5hvIpAYhU3ByI8ARKwDUc1KoLbQZUHX/yY0P7D52yk/K\nh0i5rA6iMAmQ" +
       "cNA3RMpiLDbCDLMjHGbhIVKpMhYeZIZMFXlcaB0iVaYcVSmPG8wcYKamjCFh" +
       "lRnX\nmSF0Jl+GSJmEmIy4xDUjZaOIzJRw8qkgotAowK5xYFtwV+N7AFgiw8" +
       "KMCJVYkiV/h6yCx+u9HCmM\nTeuAAFiLYoyPailV+SqFF6TK8qVC1WhwkBuy" +
       "GgXSAi0OWjipyyoUba1TaQeNsmFOar10/dYUUBUL\nQyALJ9O9ZEISeKnO4y" +
       "WXfxbV3Dpw8rnzK0Rs54eZpOD6S4BptodpgEWYwVSJWYy344EfdG+Jz/QR\n" +
       "AsTTPcQWTce8cxtCf3u93qK5bwKavpHtTOLDUu+h+oHHH9aIH5cxRddMGZ2f" +
       "hlykQ789057QIWtr\nUhJxMpCcvDDw1pYnTrO/+0hJNymUNCUegziqlLSYLi" +
       "vMeJipzKCchbtJMVPDnWK+mxTBfQhC3nrb\nF4mYjHeTfEW8KtTEM5goAiLQ" +
       "RMVwL6sRLXmvUz4q7hM6IaQILtICVyWx/sQvJ18NBM24GlG0nUHT\nkIKaEU" +
       "09S5rBgrohxwDDGAMXG5AoChuMi3AMYBjpnGwJjmoxFqQSVWVVC0ZlSFxJWx" +
       "JmY/h7N8IT\nuPqqnXl5WA69aa0A1RpNCTNjWDpx8+3dXeu+fcAKGQxzGzcn" +
       "C0FnwNYZQJ2BlM6ARyfJyxOqpqFu\ny39g/R2Qx1DxyhYMblv76IFGPwSOvj" +
       "MfTIekjYDQXlCXpHU6yd4t6qIEEVf7k637A7dPLLciLpi9\nJk/IXfrOmSvH" +
       "Pilb6CO+iQsmAoWSXYJi+rHKpgphkzfFJpL/z6d6Xnr3yvtfcpKNk6aMGpDJ" +
       "iTnc\n6HWJoUksDFXREX/83nL/JrLxkI/kQ2GAYijWD3VmtldHWi63J+siYi" +
       "kKkdKIZsSoglPJYlbCRw1t\np/NGxEqFuK8G55RicNfBVWNHu/jF2ek6jjVW" +
       "bKG3PShE3b39ZOGX33u19JIwS7JEl7s2wUHGrYSv\ndIJlvcEYvH//R/3PPP" +
       "vh/q0iUuxQ4bAzxkcUWUoAy/0OC2S6AtUGHdm0QY1pYTki0xGFYcT9r3ze\n" +
       "0lf+8d0KyzUKvEl6dvGdBTjv711Jnrjy9X/PFmLyJNxpHBgOmYWm2pHcYRh0" +
       "F64jsffarB//ih6G\nQgjFx5THmagnRCAjwo5BYfeFYgx45pbi0AiyF2cJ/Q" +
       "n29WFp9+loY/yxX/9SrLqUuhsEtxt6qN5u\neR6HuWjdGd7sXUPNUaBbdqH3" +
       "kQrlwn9B4hBIlGA/NfsMKB+JNCfa1AVF1y++UfPoVT/xrSYlikbD\nq6mIf1" +
       "IMgcfMUag8CX35ChFbFTun4CggE2GEOtsACddTPixuQfb0X41dgZM5wyOLT4" +
       "be7jssDJA1\n8SfYFD1yxs9vOHL7t/yGkONkIHI3JDLLKnRSDu+D745VFp49" +
       "GvORoiFSIdm93kaqxDHOh6A1MZMN\nIPSDafPpbYa1p7anKsxMb/a71Hpz3y" +
       "nncI/UeF/mSfcytPZsuKrsdK/ypnseETfLBUuTGOenkrMI\n9oQxiv0fKdbt" +
       "TcHE2uDqwUU+YL059fSq6oHWrU+KklwMbSE1e50lQjOOd3lg23nZnZ0SNizN" +
       "33bu\no4vn2XwRl1NkE0zRYUQnaJFcPB/T06znvcgRUVbzR6hpGcXbW2a2jm" +
       "kdobDhVB2TptZ92Ejuj6jr\n5r7G1y5vOLrf2r1yhG8a17D0zT/8cYf/exdH" +
       "LD5vjHqID80+/teXbg5Msyqd1d3OzWgw3TxWhysA\nlAsADbk0COo3FzW8sG" +
       "fghr2iqvQ+rQvOMh/seoPNf+g7f5qgiYCA1ChPlbc8e//H5zY9GVqDmaHl\n" +
       "Q1ZZpaKDXQDRVagwNcpHBU23blWHXk784Ci8XWO/QiU+S4J4nsHtyozZBL2y" +
       "pjIs8sk5q2GRtUDq\nnAKTiYzlGmRWWrvSI2LBSfenTv3sHL+6qM2y0MLsvv" +
       "YyLmw7Nl7f9uLBz9Gk1Hv85hVdOXbf1/yj\n8mWfOMBY1SPj4JPO1J5eM0pg" +
       "PXFDXZ9WOebo4mcNDvfn2LyiOeZkHKDQFUjoD8t9YOP6iTfnrpjO\nxXY6/o" +
       "sZLz904sgNtLKegAa8RJSQ5ubW1mZgr4JMxGN8QA7bpXzVtdUjpyPq6bCwQY" +
       "nI8A5ksQEW\nizeuEuTXdBPPKa4PArakpj7dxM7tHpeS7lW7z64tm/L8wX1C" +
       "vl2/il1nHvu5aIwave6sKBULX9ra\n0tryICcjX9gxoK31gcXLmpe0LOVk6v" +
       "o13YOBVInGpcTcNXsPnMAyDYiwibMxgAGmOtmEqe+eBGD5\nA10dq6z+EMdm" +
       "HFZY+dSSdctvS9+MauGqtsVWZ9mMduPQwUme2I57PBq/8QVo3Gtr9BnNE6n8" +
       "1mdU\nibvsNFvltCwq9yVBijja7NG4P4fGhJOdda6KCwVvdsahLlXvRceKpS" +
       "7bBwaxGezf/HHZPvrmNp+d\ny30QRFzTlyhsjCkujUXinqYfKxrgmmmjnulF" +
       "LfDdqaw8m2Puhzh8n5OKKOO98VgKmCDe7FjumTv5\nKtmDTgQBvwPMsSHMmT" +
       "QEe0tK+XGzID2aA8zzODzHSSWASSFZqcVVUWR6HDSH7wZNM1zzbDTzJo0m\n" +
       "Lz2mat0xFaN8NNBDuSEnlgkJp3KAPIPDT6E4AchNmqGEBUAzKXhmhmAxL6vR" +
       "lVrCscCJu7FAL1wB\n2wKBSVvALyT6U21ERlYN0F1O1AFFYwZFN3aYprXJ4Y" +
       "dUJrSdy2Gt13B4Gc4HcpI1PcRdbVH+mCaH\nHQu9cjcW+gpcnbaFOj9vjMzK" +
       "wD84StGTDvTLOaD/BodL0MvpBoMti7mZcep1B+tbd4O1Ha4tNtYt\nnzW7c9" +
       "RY7NwYOA1rbJKsxk02aP129HcLNb/LYYvrOFyFjjiuw26d4fqiEU1TGFUdi1" +
       "y7G4ugdsm2\niDRpi7jX+5cccx/g8GfwKxSAlRTOL1EnpCe5ZTk4b04WJxwn" +
       "yj3tEvaftRn//7C+2Uuh648/civ0\n+0+tQ2Pyu3opNHqRuKK4T9mu+0II1Y" +
       "gsUJZaZ26rc/6Ik7rsX1axJUtL6n9ZXJ/AtublgjTHHzfZ\nLegtXWQQDfad" +
       "m+hTODYBEd7+R09aucLp66xvDok0k6F95qada8Q/ppJnj7j1r6lhafOZrXMS" +
       "B9c/\nLQ400N/T8XEUUwJNsPUlMnV+acgqLSnrHXL2xY2v/rw12WqIL1XT7M" +
       "+PGXHbbM1mDwGcUPT/AwTa\nXdskHAAA");
}
