package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class GlassShader implements Shader {
    private float eta;
    private float f0;
    private Color color;
    private float absorbtionDistance;
    private Color absorbtionColor;
    public GlassShader() { super();
                           eta = 1.3F;
                           color = Color.WHITE;
                           absorbtionDistance = 0;
                           absorbtionColor = Color.GRAY; }
    public boolean update(ParameterList pl, SunflowAPI api) { color = pl.
                                                                        getColor(
                                                                          "color",
                                                                          color);
                                                              eta =
                                                                pl.
                                                                  getFloat(
                                                                    "eta",
                                                                    eta);
                                                              f0 =
                                                                (1 -
                                                                   eta) /
                                                                  (1 +
                                                                     eta);
                                                              f0 =
                                                                f0 *
                                                                  f0;
                                                              absorbtionDistance =
                                                                pl.
                                                                  getFloat(
                                                                    "absorbtion.distance",
                                                                    absorbtionDistance);
                                                              absorbtionColor =
                                                                pl.
                                                                  getColor(
                                                                    "absorbtion.color",
                                                                    absorbtionColor);
                                                              return true;
    }
    public Color getRadiance(ShadingState state) {
        if (!state.
              includeSpecular(
                ))
            return Color.
                     BLACK;
        Vector3 reflDir =
          new Vector3(
          );
        Vector3 refrDir =
          new Vector3(
          );
        state.
          faceforward(
            );
        float cos =
          state.
          getCosND(
            );
        boolean inside =
          state.
          isBehind(
            );
        float neta =
          inside
          ? eta
          : 1.0F /
          eta;
        float dn =
          2 *
          cos;
        reflDir.
          x =
          dn *
            state.
              getNormal(
                ).
              x +
            state.
              getRay(
                ).
              getDirection(
                ).
              x;
        reflDir.
          y =
          dn *
            state.
              getNormal(
                ).
              y +
            state.
              getRay(
                ).
              getDirection(
                ).
              y;
        reflDir.
          z =
          dn *
            state.
              getNormal(
                ).
              z +
            state.
              getRay(
                ).
              getDirection(
                ).
              z;
        float arg =
          1 -
          neta *
          neta *
          (1 -
             cos *
             cos);
        boolean tir =
          arg <
          0;
        if (tir)
            refrDir.
              x =
              (refrDir.
                 y =
                 (refrDir.
                    z =
                    0));
        else {
            float nK =
              neta *
              cos -
              (float)
                Math.
                sqrt(
                  arg);
            refrDir.
              x =
              neta *
                state.
                  getRay(
                    ).
                  dx +
                nK *
                state.
                  getNormal(
                    ).
                  x;
            refrDir.
              y =
              neta *
                state.
                  getRay(
                    ).
                  dy +
                nK *
                state.
                  getNormal(
                    ).
                  y;
            refrDir.
              z =
              neta *
                state.
                  getRay(
                    ).
                  dz +
                nK *
                state.
                  getNormal(
                    ).
                  z;
        }
        float cosTheta1 =
          Vector3.
          dot(
            state.
              getNormal(
                ),
            reflDir);
        float cosTheta2 =
          -Vector3.
          dot(
            state.
              getNormal(
                ),
            refrDir);
        float pPara =
          (cosTheta1 -
             eta *
             cosTheta2) /
          (cosTheta1 +
             eta *
             cosTheta2);
        float pPerp =
          (eta *
             cosTheta1 -
             cosTheta2) /
          (eta *
             cosTheta1 +
             cosTheta2);
        float kr =
          0.5F *
          (pPara *
             pPara +
             pPerp *
             pPerp);
        float kt =
          1 -
          kr;
        Color absorbtion =
          null;
        if (inside &&
              absorbtionDistance >
              0) {
            absorbtion =
              Color.
                mul(
                  -state.
                    getRay(
                      ).
                    getMax(
                      ) /
                    absorbtionDistance,
                  absorbtionColor.
                    copy(
                      ).
                    opposite(
                      )).
                exp(
                  );
            if (absorbtion.
                  isBlack(
                    ))
                return Color.
                         BLACK;
        }
        Color ret =
          Color.
          black(
            );
        if (!tir) {
            ret.
              madd(
                kt,
                state.
                  traceRefraction(
                    new Ray(
                      state.
                        getPoint(
                          ),
                      refrDir),
                    0)).
              mul(
                color);
        }
        if (!inside ||
              tir)
            ret.
              add(
                Color.
                  mul(
                    kr,
                    state.
                      traceReflection(
                        new Ray(
                          state.
                            getPoint(
                              ),
                          reflDir),
                        0)).
                  mul(
                    color));
        return absorbtion !=
          null
          ? ret.
          mul(
            absorbtion)
          : ret;
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        Color refr =
          Color.
          mul(
            1 -
              f0,
            color);
        Color refl =
          Color.
          mul(
            f0,
            color);
        float avgR =
          refl.
          getAverage(
            );
        float avgT =
          refr.
          getAverage(
            );
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              avgR) {
            state.
              faceforward(
                );
            if (state.
                  isBehind(
                    ))
                return;
            float cos =
              state.
              getCosND(
                );
            power.
              mul(
                refl).
              mul(
                1.0F /
                  avgR);
            float dn =
              2 *
              cos;
            Vector3 dir =
              new Vector3(
              );
            dir.
              x =
              dn *
                state.
                  getNormal(
                    ).
                  x +
                state.
                  getRay(
                    ).
                  getDirection(
                    ).
                  x;
            dir.
              y =
              dn *
                state.
                  getNormal(
                    ).
                  y +
                state.
                  getRay(
                    ).
                  getDirection(
                    ).
                  y;
            dir.
              z =
              dn *
                state.
                  getNormal(
                    ).
                  z +
                state.
                  getRay(
                    ).
                  getDirection(
                    ).
                  z;
            state.
              traceReflectionPhoton(
                new Ray(
                  state.
                    getPoint(
                      ),
                  dir),
                power);
        }
        else
            if (rnd <
                  avgR +
                  avgT) {
                state.
                  faceforward(
                    );
                float cos =
                  state.
                  getCosND(
                    );
                float neta =
                  state.
                  isBehind(
                    )
                  ? eta
                  : 1.0F /
                  eta;
                power.
                  mul(
                    refr).
                  mul(
                    1.0F /
                      avgT);
                float wK =
                  -neta;
                float arg =
                  1 -
                  neta *
                  neta *
                  (1 -
                     cos *
                     cos);
                Vector3 dir =
                  new Vector3(
                  );
                if (state.
                      isBehind(
                        ) &&
                      absorbtionDistance >
                      0) {
                    power.
                      mul(
                        Color.
                          mul(
                            -state.
                              getRay(
                                ).
                              getMax(
                                ) /
                              absorbtionDistance,
                            absorbtionColor.
                              copy(
                                ).
                              opposite(
                                )).
                          exp(
                            ));
                }
                if (arg <
                      0) {
                    float dn =
                      2 *
                      cos;
                    dir.
                      x =
                      dn *
                        state.
                          getNormal(
                            ).
                          x +
                        state.
                          getRay(
                            ).
                          getDirection(
                            ).
                          x;
                    dir.
                      y =
                      dn *
                        state.
                          getNormal(
                            ).
                          y +
                        state.
                          getRay(
                            ).
                          getDirection(
                            ).
                          y;
                    dir.
                      z =
                      dn *
                        state.
                          getNormal(
                            ).
                          z +
                        state.
                          getRay(
                            ).
                          getDirection(
                            ).
                          z;
                    state.
                      traceReflectionPhoton(
                        new Ray(
                          state.
                            getPoint(
                              ),
                          dir),
                        power);
                }
                else {
                    float nK =
                      neta *
                      cos -
                      (float)
                        Math.
                        sqrt(
                          arg);
                    dir.
                      x =
                      -wK *
                        state.
                          getRay(
                            ).
                          dx +
                        nK *
                        state.
                          getNormal(
                            ).
                          x;
                    dir.
                      y =
                      -wK *
                        state.
                          getRay(
                            ).
                          dy +
                        nK *
                        state.
                          getNormal(
                            ).
                          y;
                    dir.
                      z =
                      -wK *
                        state.
                          getRay(
                            ).
                          dz +
                        nK *
                        state.
                          getNormal(
                            ).
                          z;
                    state.
                      traceRefractionPhoton(
                        new Ray(
                          state.
                            getPoint(
                              ),
                          dir),
                        power);
                }
            }
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "60/ve3HtPdFu/xc8EIg1YxMAAA==");
}
