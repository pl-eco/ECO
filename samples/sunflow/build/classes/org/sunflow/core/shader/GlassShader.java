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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "EwAA");
}
