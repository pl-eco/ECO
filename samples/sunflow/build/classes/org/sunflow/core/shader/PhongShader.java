package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class PhongShader implements Shader {
    private Color diff;
    private Color spec;
    private float power;
    private int numRays;
    public PhongShader() { super();
                           diff = Color.GRAY;
                           spec = Color.GRAY;
                           power = 20;
                           numRays = 4; }
    public boolean update(ParameterList pl, SunflowAPI api) { diff = pl.getColor(
                                                                          "diffuse",
                                                                          diff);
                                                              spec =
                                                                pl.
                                                                  getColor(
                                                                    "specular",
                                                                    spec);
                                                              power =
                                                                pl.
                                                                  getFloat(
                                                                    "power",
                                                                    power);
                                                              numRays =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    numRays);
                                                              return true;
    }
    protected Color getDiffuse(ShadingState state) { return diff;
    }
    public Color getRadiance(ShadingState state) { state.
                                                     faceforward(
                                                       );
                                                   state.
                                                     initLightSamples(
                                                       );
                                                   state.
                                                     initCausticSamples(
                                                       );
                                                   return state.
                                                     diffuse(
                                                       getDiffuse(
                                                         state)).
                                                     add(
                                                       state.
                                                         specularPhong(
                                                           spec,
                                                           power,
                                                           numRays));
    }
    public void scatterPhoton(ShadingState state, Color power) {
        state.
          faceforward(
            );
        Color d =
          getDiffuse(
            state);
        state.
          storePhoton(
            state.
              getRay(
                ).
              getDirection(
                ),
            power,
            d);
        float avgD =
          d.
          getAverage(
            );
        float avgS =
          spec.
          getAverage(
            );
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              avgD) {
            power.
              mul(
                d).
              mul(
                1.0F /
                  avgD);
            OrthoNormalBasis onb =
              state.
              getBasis(
                );
            double u =
              2 *
              Math.
                PI *
              rnd /
              avgD;
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
                  1.0F -
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
                    getPoint(
                      ),
                  w),
                power);
        }
        else
            if (rnd <
                  avgD +
                  avgS) {
                float dn =
                  2.0F *
                  state.
                  getCosND(
                    );
                Vector3 refDir =
                  new Vector3(
                  );
                refDir.
                  x =
                  dn *
                    state.
                      getNormal(
                        ).
                      x +
                    state.
                      getRay(
                        ).
                      dx;
                refDir.
                  y =
                  dn *
                    state.
                      getNormal(
                        ).
                      y +
                    state.
                      getRay(
                        ).
                      dy;
                refDir.
                  z =
                  dn *
                    state.
                      getNormal(
                        ).
                      z +
                    state.
                      getRay(
                        ).
                      dz;
                power.
                  mul(
                    spec).
                  mul(
                    1.0F /
                      avgS);
                OrthoNormalBasis onb =
                  state.
                  getBasis(
                    );
                double u =
                  2 *
                  Math.
                    PI *
                  (rnd -
                     avgD) /
                  avgS;
                double v =
                  state.
                  getRandom(
                    0,
                    1,
                    1);
                float s =
                  (float)
                    Math.
                    pow(
                      v,
                      1 /
                        (this.
                           power +
                           1));
                float s1 =
                  (float)
                    Math.
                    sqrt(
                      1 -
                        s *
                        s);
                Vector3 w =
                  new Vector3(
                  (float)
                    Math.
                    cos(
                      u) *
                    s1,
                  (float)
                    Math.
                    sin(
                      u) *
                    s1,
                  s);
                w =
                  onb.
                    transform(
                      w,
                      new Vector3(
                        ));
                state.
                  traceReflectionPhoton(
                    new Ray(
                      state.
                        getPoint(
                          ),
                      w),
                    power);
            }
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YX2wUxxmfO/852xjfYcA4FAw2dlRMeluqphJ1BHUuBkwO" +
                                                    "fLUJUh2Vy9zu3N3ivZ1ld84+TJ0GqhaaSqhKHAoV8UNFlH8kRFVRWlWReGmT" +
                                                    "KH1JVbXqQ5MqL4mS8MBD06hpm34zs3u7t3fnJC+1NHOzM998f+b7vt9842u3" +
                                                    "UJtjo10WNU4VDMqSpMKSJ4y7k+yURZzkofTdGWw7REsZ2HGOwlxWHXop/tEn" +
                                                    "Py0moqh9Fq3HpkkZZjo1nWniUGOeaGkU92cnDFJyGEqkT+B5rJSZbihp3WFj" +
                                                    "abQmsJWh4bSnggIqKKCCIlRQxn0q2LSWmOVSiu/AJnNOoodRJI3aLZWrx9Bg" +
                                                    "LRML27jksskIC4BDB/8+BkaJzRUbba/aLm2uM/iJXcryz44nftmC4rMorpsz" +
                                                    "XB0VlGAgZBZ1l0gpR2xnXNOINovWmYRoM8TWsaEvCr1nUa+jF0zMyjapHhKf" +
                                                    "LFvEFjL9k+tWuW12WWXUrpqX14mheV9teQMXwNY+31Zp4X4+DwZ26aCYnccq" +
                                                    "8ba0zummxtC28I6qjcP3AwFsjZUIK9KqqFYTwwTqlb4zsFlQZpitmwUgbaNl" +
                                                    "kMLQ5qZM+VlbWJ3DBZJlqD9Ml5FLQNUpDoJvYWhjmExwAi9tDnkp4J9bR+65" +
                                                    "cNo8aEaFzhpRDa5/B2waCG2aJnliE1MlcmP3aPoi7nvlfBQhIN4YIpY0L3/v" +
                                                    "9rfuGrj5mqT5UgOaqdwJorKsejXX8+aW1M49LVyNDos6Ond+jeUi/DPuyljF" +
                                                    "gszrq3Lki0lv8eb077/zyHPkgyjqmkTtKjXKJYijdSotWbpB7APEJDZmRJtE" +
                                                    "ncTUUmJ9EsVgnNZNImen8nmHsEnUaoipdiq+4YjywIIfUQzGupmn3tjCrCjG" +
                                                    "FQshFIOGRqF1I/knfhk6oRRpiShYxaZuUgVil2BbLSpEpVmbWFSZSE0pOTjl" +
                                                    "Ygnbc47ilM28QReyatlhtKQ4tqpQu+BNKyq1ieIUsUZsJVOkZmFGjJM85qz/" +
                                                    "q7QKtz2xEImAW7aEQcGAfDpIDaDNqsvleyduv5h9I1pNEvfUGNoBwpKusCQX" +
                                                    "lpTCkgFhKBIRMjZwodLt4LQ5SH8Axu6dM9899ND5oRaIN2uhFU6ckw6Bua4m" +
                                                    "EypN+RgxKZBQhUDt/8WD55IfP71PBqrSHNAb7kY3Ly2cOfb9r0ZRtBaZuWUw" +
                                                    "1cW3ZzieVnFzOJyRjfjGz7330fWLS9TPzRqodyGjfidP+aGwD2yqEg1A1Gc/" +
                                                    "uh3fyL6yNBxFrYAjgJ0MQ6wDLA2EZdSk/pgHo9yWNjA4T+0SNviSh31drGjT" +
                                                    "BX9GBEePGK8Dp6zhudAHrddNDvHLV9dbvN8gg4l7OWSFgOn9v7l5+cbPd+2J" +
                                                    "BhE9HrgjZwiT+LDOD5KjNiEw/7dLmcefuHXuQREhQLGjkYBh3qcALcBlcKw/" +
                                                    "fO3kX99+6+qfon5UMbg2yzlDVyvA405fCmCJAXjGfT/8gFmimp7Xcc4gPDj/" +
                                                    "HR/ZfePDCwnpTQNmvGC467MZ+PN33IseeeP4PwcEm4jK7zLfcp9MHsB6n/O4" +
                                                    "beNTXI/KmT9uvfwqfhKgFuDN0ReJQCwkLEPi6BXhqlHRJ0Nru3m33apbq4iZ" +
                                                    "fvHVCqJ3Nk+i/fxKDiTfv6aM3Nl3PhYW1aVPg5sotH9WuXZlc2rvB2K/H8d8" +
                                                    "97ZKPRpB+eLv/dpzpX9Eh9p/F0WxWZRQ3droGDbKPFpmoR5wvIIJ6qea9dq7" +
                                                    "XV5kY9U83RLOoYDYcAb5KAhjTs3HXaGkERfIJmhr3aRZG06aCBKDPWLLkOhH" +
                                                    "ePdlL2Zjlq3PY154gZY6XGh8dSNDm4K4q5egsOBxSMUJJqSnv16vR4+rR08T" +
                                                    "PcZ5NwaiHIuofLyvOb9+aHGXX7wJv/tcfm0WXYCKcdUIy9h6CUqBebdWUZZ6" +
                                                    "35678t4LEt7D4RQiJueXH/00eWE5Gqj+dtQVYME9sgIU/lor7foU/iLQ/ssb" +
                                                    "t4dPyAqgN+WWIdurdYhlcRgZXE0tIWL/u9eXfvvM0jlpRm9t8TMBtf0Lf/7P" +
                                                    "H5KX/v56g7sVAo5itroHEq4HEk088G3XAzEQNY1POYLDAd7dL7P/MEMtUEBL" +
                                                    "KeI8hgOoEGkYb+Kel3c7P4WtzUpVcQJXzy6vaFNP7Y66YHSEoU5Gra8YZJ4Y" +
                                                    "IQDaWnPtHxbFuZ/4jz77/MvszV3flGc52jyUwhtfPfv+5qN7iw99gct+W8im" +
                                                    "MMtnD197/cCd6mNR1FLFj7r3Ru2msVrU6LIJPJDMozXYMVD18Hru0Dvc5o3r" +
                                                    "L1zfYT70R8V5Rj3XDdS5TphK4DnD7xaPrC9INiN/xzOTQgxZ5XKZ410Obtey" +
                                                    "pUFmNAqwWI5Sg2Cz/gYSE8erRm/gkwPQBl2jBxtXGc3QstOyKQNMJ1olpHGk" +
                                                    "Npy3NgxneOnxty4RUhZWsfk0704y1FUg7D5A5bIjfLjvMw3kDW2BNuIaOPK5" +
                                                    "vepawD8rgurMKgr+gHcPM7QGFJwGw3j8fT4NRaG3F5riaqh80bgTGgphov/x" +
                                                    "Kmr+hHc/YgDBKmYQj/BoYG5ahkKodZ7qWoMKBkwMvDR4/dRf9+8N+SRXX1yJ" +
                                                    "d2xaeeAvonauPps74e2aLxtG8D4PjNstm+R1oWynvN0lSD7eCBLl0wdSQQ6E" +
                                                    "to9J+osMJcL0YBX/CZJdBnsCZJA67ihIdAUgG4j48EnLi+iEKBt5XZOUdU0F" +
                                                    "BcCVV87Br5oymuOn+NeRh3Vl+c+jrHp95dCR07e/8ZQAzjbVwIuLnEtHGsXk" +
                                                    "C6KKl4NNuXm82g/u/KTnpc4R7x7o4V1vIKEDum1rXF1PlCwm6uHFX2/61T1P" +
                                                    "r7wlyvv/AbpoSbrTEwAA");
}
