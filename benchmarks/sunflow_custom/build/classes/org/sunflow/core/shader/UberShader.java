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
      1169407194000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "6++KS91/AVl424+2FQAA");
}
