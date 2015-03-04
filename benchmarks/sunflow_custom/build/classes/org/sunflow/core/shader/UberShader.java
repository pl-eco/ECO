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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "0PLPNv70/pfW3heXuv8CgK3MC8YVAAA=");
}
