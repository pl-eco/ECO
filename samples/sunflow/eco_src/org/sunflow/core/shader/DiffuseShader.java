package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class DiffuseShader implements Shader {
    private Color diff;
    public DiffuseShader() { super();
                             diff = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { diff = pl.getColor(
                                                                          "diffuse",
                                                                          diff);
                                                              return true;
    }
    public Color getDiffuse(ShadingState state) { return diff;
    }
    public Color getRadiance(ShadingState state) { state.
                                                     faceforward();
                                                   state.
                                                     initLightSamples();
                                                   state.
                                                     initCausticSamples();
                                                   return state.
                                                     diffuse(
                                                       this.
                                                         getDiffuse(
                                                           state));
    }
    public void scatterPhoton(ShadingState state, Color power) {
        Color diffuse;
        if (Vector3.
              dot(
                state.
                  getNormal(),
                state.
                  getRay().
                  getDirection()) >
              0.0) {
            state.
              getNormal().
              negate();
            state.
              getGeoNormal().
              negate();
        }
        diffuse =
          this.
            getDiffuse(
              state);
        state.
          storePhoton(
            state.
              getRay().
              getDirection(),
            power,
            diffuse);
        float avg =
          diffuse.
          getAverage();
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              avg) {
            power.
              mul(
                diffuse).
              mul(
                1.0F /
                  avg);
            OrthoNormalBasis onb =
              state.
              getBasis();
            double u =
              2 *
              Math.
                PI *
              rnd /
              avg;
            double v =
              state.
              getRandom(
                0,
                1,
                1);
            float s =
              (float)
                Math.
                sqrt(
                  v);
            float s1 =
              (float)
                Math.
                sqrt(
                  1.0 -
                    v);
            Vector3 w =
              new Vector3(
              (float)
                Math.
                cos(
                  u) *
                s,
              (float)
                Math.
                sin(
                  u) *
                s,
              s1);
            w =
              onb.
                transform(
                  w,
                  new Vector3(
                    ));
            state.
              traceDiffusePhoton(
                new Ray(
                  state.
                    getPoint(),
                  w),
                power);
        }
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1160859148000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALUYa2wcxXl8d7bjs63zI7FNcOzYsXkk5hZQU9EYNXGNAyYX" +
                                                   "cvhFcABnvDt3t/He\n7rI7a59NRIMq4TSoLVFbqVRNiNpICW8kqNJKlAYBLS" +
                                                   "WqBJVKJSTSVpHaSi2Vqko0Vfuj38zs3t7u\nnS9RUE/a2dmZ7zHf+5t78VNU" +
                                                   "a1uoW7aTdNkkdnJ0Mo0tmyijGrbtKViak9+tbUif2aMbEVSTQhFV\noSiRkm" +
                                                   "1JwRRLqiKN3zVcsNA209CWs5pBk6RAk4e07S69e1Pbywg+cPJc2xOnY70RVJ" +
                                                   "tCCazrBsVU\nNfQxjeRtilpSh/AilhyqalJKtelwCjUT3cmPGrpNsU7tR9Hj" +
                                                   "KJpCdabMaFLUl/KYS8BcMrGF8xJn\nL6U5W6DQbhGKVZ0oI0V2gDkUxIRju3" +
                                                   "gT5dBAZB3bnAFx+AlA6s1FqYW0ZaKa0bMzXzx86rkoSsyi\nhKpPMmIySEKB" +
                                                   "3yxqypP8PLHsEUUhyixq1QlRJomlYk1d4VxnUZutZnVMHYvYE8Q2tEUG2GY7" +
                                                   "JrE4\nT28xhZpkJpPlyNSwijrKqERTvK/ajIazIHaHL7YQdzdbBwHjKhzMym" +
                                                   "CZeCixBVUHi/eGMYoyDuwB\nAECtzxOaM4qsYjqGBdQmbKlhPStNUkvVswBa" +
                                                   "azjAhaKNaxJlujaxvICzZI6irjBcWmwBVANXBEOh\naEMYjFMCK20MWanEPt" +
                                                   "s6Pjt69gdv7uK+HVOIrLHzxwGpJ4Q0QTLEIrpMBOJlJ/md8Qed7ghCALwh\n" +
                                                   "BCxgRgbPTaf+8vNeAXN9BZh984eITOfk+473Tjx2t4Gi7BjrTMNWmfEDkvNw" +
                                                   "SLs7wwUTorajSJFt\nJr3N8xO/ePDI8+SvERQfR3WyoTl58KNW2cibqkasu4" +
                                                   "lOLEyJMo4aiK6M8v1xVA/zFLi8WN2XydiE\njqOYxpfqDP4NKsoACaaiBpir" +
                                                   "esbw5iamOT4vmAihenjQEDxNSPz4m6I7kpLt6BnNWJJsS5YMK1v8\nlg2LSH" +
                                                   "YOK8SS7lIzGccmk/wryTzIpGhKyhl5ImEZ66puSFkVYlY2blHIIntfI90CO3" +
                                                   "PbUk0NS4Lh\nYNYgDu4xNICdk89cev/w2J6vHxWOwpzblZaiQWCXdNklGbuk" +
                                                   "YJcMsEM1NZzLesZWGAzUvQCBCymu\n6ebJh+89eLQ/Cp5iLsVAVwy0H+Ryzz" +
                                                   "ImG6N+dI/zRCiDi3X98MBq8vKZncLFpLWTcEXsxg9eunDq\nn01bIyhSOUMy" +
                                                   "GSFHxxmZNEurxcw3EI6pSvT/fmzvax9d+OQmP7ooGigL+nJMFrT9YWtYhkwU" +
                                                   "SIM+\n+dPXJaIPoJnjERSDTADZj58fEktPmEcgeIe9RMhkqU+hxoxh5bHGtr" +
                                                   "zsFac5y1jyV7ibtPB5Oxin\nkXlzFzwJ1735m+1uMNnYIdyKWTskBU+0l79W" +
                                                   "d+vv3mh8l6vFy8mJkqo3SaiI8FbfWaYsQmD9k++l\nv/3dT1cPcE9xXYVCKX" +
                                                   "TmNVUuAMoNPgqEtgbphRlyYFrPG4qaUfG8RpjH/TcxeNuP//bNFmEaDVY8\n" +
                                                   "yw5dmYC/ft1X0JELj/yrh5OpkVlp8cXwwYQ07T7lEcvCy+wchSd+s+mZX+IT" +
                                                   "kPkg29jqCuEJBHHJ\nENejxPW+lY/J0N5tbOgH2kNruH6FQj4nH34+2+88+q" +
                                                   "uf8lM34tKOoNQMe7E5LCzPhi1Mu53h6L0H\n2zmA+8L5+x5q0c7/ByjOAkUZ" +
                                                   "Cqi9z4KwLwSM6ELX1n/81tsdBz+MoshuFNcMrOzG3P9RAzgesXOQ\ndArmzl" +
                                                   "3ct1qW1rGRi4y4Eja6CiiUfLF8cfPa4b+btQF+5MzND51Nvb/vBFfAmoFfoQ" +
                                                   "qG6Ky8OX3y\n8q/pRU7Hj0CG3Vcoz6jQOvm4d3y02Fr36rP5CKqfRS2y29zN" +
                                                   "YM1hfj4LvYjtdXzQAAb2g32FKKLD\nxQzTHY7+Erbh2PczOcwZNJs3hcKdF6" +
                                                   "9OeJrdcG8Oh3sN4pOdHGWAjzcWg7PetNRFzBo+OCGUBL7d\nSVFnad1Q89DQ" +
                                                   "sIAzLJE72Hg7G3YJM2+v5A78oDcE3KAScV6URBliTrxprX6I93Kr+//R9CR+" +
                                                   "52FR\nUtqCPcYY9OF/Xn6b3HjnN/5YoRQ2UMO8RSOLRCs5U4yxDJSyvbxV9F" +
                                                   "3h2HMvnKMfbtshWG5d243D\niFt3nFrp3fHKU9dQwHpDSgiTbl28/v5oTn0v" +
                                                   "wrtZ4VllXXAQaTjoT3E4j2PpUwGv2hwsIsPwtLte\n1V6xiPgW9jNghOs14t" +
                                                   "m6p8zWXFQCTTZLsR5YRynYpHiPpMc5m/1VcuwjbJiGIuOYcOcjYM2u0uui\n" +
                                                   "peah7VzkpfXSk/0/e2/62VVhyCr5KIA1J3/1939YiH7rrXmBF046IeDjPaf/" +
                                                   "9NqlifXC/8T9ZEvZ\nFaEUR9xRuDAJk0VAXzUOHPqdbX0vPj5xkZ+I4e2FOJ" +
                                                   "43DI1g3Q/NmSqhGcjU/OP+oOklt4fweomr\nM31NMMw3VQxzuGaxiybhZPQq" +
                                                   "puW5ZoGieJZQt1u9Unbypdc+j/S3wtPtSt/9f5T+cBXpj7BhmaJG\nkH4C8F" +
                                                   "gMX734K59H/C/DM+iKP3itcV9d/CtJwtkcq6Kep9mwSlGzLWMKuSSdM6ibUg" +
                                                   "+aQiqoxLFF\nQ1V8rRy9Wq1ANWwOXJBYh9hV9n+K+A9ATn382EOfpX77b97q" +
                                                   "F+/pjXBZzjiaVlrES+Z1pkUyKpel\nUZR0k7++X6k8ijsbZDkx4Wd9RsCfoK" +
                                                   "glDA+Cs1cp2CnwpBIwSBjurBToRxRFAYhNT5ueiVp4h8ia\nmaRoZgoBVTHN" +
                                                   "bAnkUv4Xl1e4HPEn15y8/6UDmwtPTT3Nq2GtrOGVFUYmnkL14opTLH59a1Lz" +
                                                   "aH2A\nXn1l5o2Xv+TlP94Cry/4vUnAu28Xu1VMDwW38r1iLG9SfhNY+Unn63" +
                                                   "eeOXkxwm82/wPwm+FLmRQA\nAA==");
}
