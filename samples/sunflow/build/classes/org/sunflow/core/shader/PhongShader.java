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
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYb2wcRxWfO/8523F8Fydx3JA4sWNXxCm3BFGk4CrBdZ3E" +
                                                    "6SU57DQIV8Qd787dbby3u9mdsy8OLk0QJC1ShFo3JCj1B5SqtKRNhYgKQpXy" +
                                                    "BdqqfClCID7QIr5QUfIhHygVBcp7M7u3e3t3bvsBSzM3O/PmzXvz3vu9N75+" +
                                                    "m7S4DtllW8bpvGHxNCvz9Enj3jQ/bTM3fShzb5Y6LtPGDOq6x2BuRh14Ofn+" +
                                                    "h98vpOKkdZqsp6Zpccp1y3QnmWsZ80zLkGQwO26wostJKnOSzlOlxHVDyegu" +
                                                    "H8mQNaGtnAxmfBEUEEEBERQhgjIaUMGmtcwsFcdwBzW5e4o8SmIZ0mqrKB4n" +
                                                    "/dVMbOrQoscmKzQADm34fRyUEpvLDtle0V3qXKPw07uU5R+cSP20iSSnSVI3" +
                                                    "p1AcFYTgcMg06Syy4ixz3FFNY9o0WWcypk0xR6eGvijknibdrp43KS85rHJJ" +
                                                    "OFmymSPODG6uU0XdnJLKLaeiXk5nhuZ/teQMmgddewJdpYb7cR4U7NBBMCdH" +
                                                    "VeZvaZ7TTY2TbdEdFR0HHwQC2JooMl6wKkc1mxQmSLe0nUHNvDLFHd3MA2mL" +
                                                    "VYJTONnckCnetU3VOZpnM5z0Rumycgmo2sVF4BZONkbJBCew0uaIlUL2uX3k" +
                                                    "votnzINmXMisMdVA+dtgU19k0yTLMYeZKpMbO4czl2jPqxfihADxxgixpHnl" +
                                                    "m3e+ck/frdclzWfq0BydPclUPqNem+16a8vYzj1NKEabbbk6Gr9Kc+H+WW9l" +
                                                    "pGxD5PVUOOJi2l+8Nfnrrz/2AnsvTjomSKtqGaUi+NE61SrausGcA8xkDuVM" +
                                                    "myDtzNTGxPoEScA4o5tMzh7N5VzGJ0izIaZaLfENV5QDFnhFCRjrZs7yxzbl" +
                                                    "BTEu24SQBDQyDK2TyD/xy8nXlIJVZApVqamblgK+y6ijFhSmWopLi7YBVnNL" +
                                                    "Zs6wFhTXURXLyVe+VcthilugGnOUbMEy81NinEYHs/9/rMuoVWohFoML3xIN" +
                                                    "dwMi5aBlAO2Muly6f/zOSzNvxivu790HJzvgsLR3WBoPS8vD0qHDSCwmztiA" +
                                                    "h0qDgjnmILAB8jp3Tn3j0CMXBprAk+yFZrhLJB0A3TxJxlVrLIj+CYFxKrhg" +
                                                    "748ePp/+4Ll90gWVxlBddze5dXnh7PFvfT5O4tWYi5rBVAduzyJSVhBxMBpr" +
                                                    "9fgmz7/7/o1LS1YQdVUg7oFB7U4M5oGoDRxLZRrAY8B+eDu9OfPq0mCcNANC" +
                                                    "ACpyCl4MgNMXPaMqqEd8gERdWkDhnOUUqYFLPqp18IJjLQQzwjm6xHgdGGUN" +
                                                    "enkPtG7P7cUvrq63sd8gnQmtHNFCAPD+X9y6cvOHu/bEw1idDGW/KcZl5K8L" +
                                                    "nOSYwxjM/+ly9qmnb59/WHgIUOyod8Ag9mOAA2AyuNbvvH7qj++8fe138cCr" +
                                                    "OCTE0qyhq2XgcXdwCqCEAUiFth98yCxamp7T6azB0Dn/nRzaffPvF1PSmgbM" +
                                                    "+M5wz8czCObvup889uaJf/YJNjEVs1SgeUAmL2B9wHnUcehplKN89rdbr7xG" +
                                                    "nwEQBeBy9UUmsIgIzYi4ekWYalj06cjabuy22zVrZTHTK76a4eidjYNoPybb" +
                                                    "UPD966gxe+4vHwiNasKnTo6J7J9Wrl/dPLb3PbE/8GPcva1ci0ZQmAR7v/BC" +
                                                    "8R/xgdZfxUlimqRUr+o5To0Sess0ZHrXL4WgMqpar87aMkWNVOJ0SzSGQsdG" +
                                                    "IyhAQRgjNY47IkEjUsMmaGu9oFkbDZoYEYM9YsuA6Iew+6zvswnb0ecpllQg" +
                                                    "pQ6pClc3crIpjLt6EUoG9ENL3GBKWvqLtXJ0eXJ0NZBjFLsROMq1mYrjfY35" +
                                                    "9UJLevySDfg94PFrsa0FqAVX9bCsoxchyc97VYiy1P3O3NV3X5TwHnWnCDG7" +
                                                    "sPzER+mLy/FQXbejprQK75G1nbDXWqnXR/AXg/ZfbKgPTsjc3j3mFRjbKxWG" +
                                                    "bSOM9K8mljhi/19vLP3yx0vnpRrd1WXNOFTtL/7+P79JX/7zG3VyKzicRfnq" +
                                                    "Fkh5Fkg1sMBXPQsk4KhJetoVHA5g96CM/sOcNEFpLE8R9zEYQoVYXX8TeV7m" +
                                                    "dryFrY2KUHED184tr2hHn90d98DoCCft3LI/Z7B5ZkQAaGtV2j8syu4g8J94" +
                                                    "/iev8Ld2fVne5XBjV4pufO3c3zYf21t45FMk+20RnaIsnz98/Y0Dd6tPxklT" +
                                                    "BT9qXhLVm0aqUaPDYfD0MY9VYUdfxcLr0aB3ec0f1ybcwGAB9MfFfcZ90/XV" +
                                                    "mE6oyuChgrnFJ+sJk03J39HshDiGrZJc5rCbhexasjWIjHoOlpi1LINRszYD" +
                                                    "iYkTFaU34GQftH5P6f76VUYjtGy3HYsDpjOtHJE4Vu3OW+u6M7zh8BXLxCkL" +
                                                    "q+h8BrtTnHTkGX8AULnkChvu+1gFsZEt0IY8BYc+sVU9DfCzLKjOriLgt7F7" +
                                                    "lJM1IOAkKIb+98kkFIXeXmiKJ6Hyaf1OSCgOE/3jq4j5Pey+ywGCVcrBH+HR" +
                                                    "wL2wjLhQ87yla3UqGFAx9NLA+qm35h8X8rGtvrSSbNu08tAfRO1ceRC3w6s0" +
                                                    "VzKMcD4PjVtth+V0IWy7zO4SJJ+qB4ny6QOhIAdC2icl/SVOUlF60Ap/wmRX" +
                                                    "QJ8QGYSONwoTXQXIBiIcPmP7Hp0SZSPWNWlZ15RJCFyxcg5/VZXRiJ/in0I+" +
                                                    "1pXkv4Vm1Bsrh46cufOlZwVwtqgGXVxELm0ZkpAviApe9jfk5vNqPbjzw66X" +
                                                    "24f8PNCFXXcooEOybatfXY8XbS7q4cWfb/rZfc+tvC3K+/8BFLU2FK0TAAA=");
}
