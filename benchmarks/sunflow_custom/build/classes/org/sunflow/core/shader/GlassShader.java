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
      1169410512000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "aLf/C03JgfdTEwAA");
}
