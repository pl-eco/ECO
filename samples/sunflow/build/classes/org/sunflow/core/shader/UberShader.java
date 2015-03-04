package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Color;
import org.sunflow.math.MathUtils;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class UberShader implements Shader {
    private Color diff;
    private Color spec;
    private Texture diffmap;
    private Texture specmap;
    private float diffBlend;
    private float specBlend;
    private float glossyness;
    private int numSamples;
    public UberShader() { super();
                          diff = (spec = Color.GRAY);
                          diffmap = (specmap = null);
                          diffBlend = (specBlend = 1);
                          glossyness = 0;
                          numSamples = 4; }
    public boolean update(ParameterList pl, SunflowAPI api) { diff = pl.getColor(
                                                                          "diffuse",
                                                                          diff);
                                                              spec =
                                                                pl.
                                                                  getColor(
                                                                    "specular",
                                                                    spec);
                                                              String filename;
                                                              filename =
                                                                pl.
                                                                  getString(
                                                                    "diffuse.texture",
                                                                    null);
                                                              if (filename !=
                                                                    null)
                                                                  diffmap =
                                                                    TextureCache.
                                                                      getTexture(
                                                                        api.
                                                                          resolveTextureFilename(
                                                                            filename),
                                                                        false);
                                                              filename =
                                                                pl.
                                                                  getString(
                                                                    "specular.texture",
                                                                    null);
                                                              if (filename !=
                                                                    null)
                                                                  specmap =
                                                                    TextureCache.
                                                                      getTexture(
                                                                        api.
                                                                          resolveTextureFilename(
                                                                            filename),
                                                                        false);
                                                              diffBlend =
                                                                MathUtils.
                                                                  clamp(
                                                                    pl.
                                                                      getFloat(
                                                                        "diffuse.blend",
                                                                        diffBlend),
                                                                    0,
                                                                    1);
                                                              specBlend =
                                                                MathUtils.
                                                                  clamp(
                                                                    pl.
                                                                      getFloat(
                                                                        "specular.blend",
                                                                        diffBlend),
                                                                    0,
                                                                    1);
                                                              glossyness =
                                                                MathUtils.
                                                                  clamp(
                                                                    pl.
                                                                      getFloat(
                                                                        "glossyness",
                                                                        glossyness),
                                                                    0,
                                                                    1);
                                                              numSamples =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    numSamples);
                                                              return true;
    }
    public Color getDiffuse(ShadingState state) {
        return diffmap ==
          null
          ? diff
          : Color.
          blend(
            diff,
            diffmap.
              getPixel(
                state.
                  getUV(
                    ).
                  x,
                state.
                  getUV(
                    ).
                  y),
            diffBlend);
    }
    public Color getSpecular(ShadingState state) {
        return specmap ==
          null
          ? spec
          : Color.
          blend(
            spec,
            specmap.
              getPixel(
                state.
                  getUV(
                    ).
                  x,
                state.
                  getUV(
                    ).
                  y),
            specBlend);
    }
    public Color getRadiance(ShadingState state) {
        state.
          faceforward(
            );
        state.
          initLightSamples(
            );
        state.
          initCausticSamples(
            );
        Color d =
          getDiffuse(
            state);
        Color lr =
          state.
          diffuse(
            d);
        if (!state.
              includeSpecular(
                ))
            return lr;
        if (glossyness ==
              0) {
            float cos =
              state.
              getCosND(
                );
            float dn =
              2 *
              cos;
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
                  getDirection(
                    ).
                  x;
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
                  getDirection(
                    ).
                  y;
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
                  getDirection(
                    ).
                  z;
            Ray refRay =
              new Ray(
              state.
                getPoint(
                  ),
              refDir);
            cos =
              1 -
                cos;
            float cos2 =
              cos *
              cos;
            float cos5 =
              cos2 *
              cos2 *
              cos;
            Color ret =
              Color.
              white(
                );
            ret.
              sub(
                spec);
            ret.
              mul(
                cos5);
            ret.
              add(
                spec);
            return lr.
              add(
                ret.
                  mul(
                    state.
                      traceReflection(
                        refRay,
                        0)));
        }
        else
            return lr.
              add(
                state.
                  specularPhong(
                    getSpecular(
                      state),
                    2 /
                      glossyness,
                    numSamples));
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        Color diffuse;
        Color specular;
        state.
          faceforward(
            );
        diffuse =
          getDiffuse(
            state);
        specular =
          getSpecular(
            state);
        state.
          storePhoton(
            state.
              getRay(
                ).
              getDirection(
                ),
            power,
            diffuse);
        float d =
          diffuse.
          getAverage(
            );
        float r =
          specular.
          getAverage(
            );
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              d) {
            power.
              mul(
                diffuse).
              mul(
                1.0F /
                  d);
            OrthoNormalBasis onb =
              state.
              getBasis(
                );
            double u =
              2 *
              Math.
                PI *
              rnd /
              d;
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
                    getPoint(
                      ),
                  w),
                power);
        }
        else
            if (rnd <
                  d +
                  r) {
                if (glossyness ==
                      0) {
                    float cos =
                      -Vector3.
                      dot(
                        state.
                          getNormal(
                            ),
                        state.
                          getRay(
                            ).
                          getDirection(
                            ));
                    power.
                      mul(
                        diffuse).
                      mul(
                        1.0F /
                          d);
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
                else {
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
                          r);
                    OrthoNormalBasis onb =
                      state.
                      getBasis(
                        );
                    double u =
                      2 *
                      Math.
                        PI *
                      (rnd -
                         r) /
                      r;
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
                            (1.0F /
                               glossyness +
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
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "rqAVAAA=");
}
