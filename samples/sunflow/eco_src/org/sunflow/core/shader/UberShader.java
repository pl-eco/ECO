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
                  getUV().
                  x,
                state.
                  getUV().
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
                  getUV().
                  x,
                state.
                  getUV().
                  y),
            specBlend);
    }
    public Color getRadiance(ShadingState state) {
        state.
          faceforward();
        state.
          initLightSamples();
        state.
          initCausticSamples();
        Color d =
          this.
          getDiffuse(
            state);
        Color lr =
          state.
          diffuse(
            d);
        if (!state.
              includeSpecular())
            return lr;
        if (glossyness ==
              0) {
            float cos =
              state.
              getCosND();
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
                  getNormal().
                  x +
                state.
                  getRay().
                  getDirection().
                  x;
            refDir.
              y =
              dn *
                state.
                  getNormal().
                  y +
                state.
                  getRay().
                  getDirection().
                  y;
            refDir.
              z =
              dn *
                state.
                  getNormal().
                  z +
                state.
                  getRay().
                  getDirection().
                  z;
            Ray refRay =
              new Ray(
              state.
                getPoint(),
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
              white();
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
                    this.
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
          faceforward();
        diffuse =
          this.
            getDiffuse(
              state);
        specular =
          this.
            getSpecular(
              state);
        state.
          storePhoton(
            state.
              getRay().
              getDirection(),
            power,
            diffuse);
        float d =
          diffuse.
          getAverage();
        float r =
          specular.
          getAverage();
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
              getBasis();
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
                    getPoint(),
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
                          getNormal(),
                        state.
                          getRay().
                          getDirection());
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
                          getNormal().
                          x +
                        state.
                          getRay().
                          getDirection().
                          x;
                    dir.
                      y =
                      dn *
                        state.
                          getNormal().
                          y +
                        state.
                          getRay().
                          getDirection().
                          y;
                    dir.
                      z =
                      dn *
                        state.
                          getNormal().
                          z +
                        state.
                          getRay().
                          getDirection().
                          z;
                    state.
                      traceReflectionPhoton(
                        new Ray(
                          state.
                            getPoint(),
                          dir),
                        power);
                }
                else {
                    float dn =
                      2.0F *
                      state.
                      getCosND();
                    Vector3 refDir =
                      new Vector3(
                      );
                    refDir.
                      x =
                      dn *
                        state.
                          getNormal().
                          x +
                        state.
                          getRay().
                          dx;
                    refDir.
                      y =
                      dn *
                        state.
                          getNormal().
                          y +
                        state.
                          getRay().
                          dy;
                    refDir.
                      z =
                      dn *
                        state.
                          getNormal().
                          z +
                        state.
                          getRay().
                          dz;
                    power.
                      mul(
                        spec).
                      mul(
                        1.0F /
                          r);
                    OrthoNormalBasis onb =
                      state.
                      getBasis();
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
                            getPoint(),
                          w),
                        power);
                }
            }
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169407194000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALUYa2wUx3l8Z/vssyO/HzxsY+Mk5ZHbBBXU2lHBcUxiOMLh" +
       "sw0xSZzx3tx5YW93\n2Z07nx2UJqoaCOmLPqS+ILRCAvIokdKWRkpSokCTBk" +
       "VKKpVISNBESG2lNpWqSilV+6PfzOze3u09\nAKOctLNzO99879fMS5+iKstE" +
       "y2UrROcNYoWGoxFsWiQ2rGLLGodP0/L5qtrIia2a7kMVYeRTYhQ1\nhGVLim" +
       "GKJSUmjd4/mDHRGkNX5xOqTkMkQ0N71PU2vi3h9QUIdx490/z08coeH6oKow" +
       "asaTrFVNG1\nEZUkLYoaw3twGkspqqhSWLHoYBjdRrRUcljXLIo1au1DTyJ/" +
       "GFUbMsNJUW/YIS4BccnAJk5KnLwU\n4WQBQ4tJKFY0EhvKkoOda/N3Atv2vr" +
       "FCaEBSwxYnQRzOAUi9Iiu1kLZAVMN/cnLD/mOn/KhhCjUo\nWpQhk0ESCvSm" +
       "UH2SJGeIaQ3FYiQ2hZo0QmJRYipYVRY41SnUbCkJDdOUSawxYulqmgE2WymD" +
       "mJym\n8zGM6mUmk5mSqW5mdRRXiBpz/lXFVZwAsdtdsYW4m9l3EDCoAGNmHM" +
       "vE2VK5V9HA4j3eHVkZ+7cC\nAGwNJAmd1bOkKjUMH1CzsKWKtYQUpaaiJQC0" +
       "Sk8BFYqWlkTKdG1geS9OkGmKOr1wEbEEULVcEWwL\nRW1eMI4JrLTUY6Uc+6" +
       "xp/+zgyZ++uYn7dmWMyCrjPwibuj2bxkicmESTidh4LRX6/ujDqeU+hAC4\n" +
       "zQMsYIZuPzMR/utvewTMsiIw22f2EJlOyw8d7hl74gEd+RkbNYZuKcz4eZLz" +
       "cIjYK4MZA6K2PYuR\nLYacxbNjv3v4qRfI33woOIqqZV1NJcGPmmQ9aSgqMR" +
       "8gGjExJbFRVEu02DBfH0UBmIfB5cXX7fG4\nRegoqlT5p2qd/wcVxQEFU1Et" +
       "zBUtrjtzA9NZPs8YCKEAPGgVPA1I/PibovUhyUppcVWfkyxTlnQz\nkf0v6y" +
       "aRrFkcI6Y0ATER5dMQcx+Doh3SrJ4kEpaxpmi6lFAgYGX9rhhJs/dikGYYt8" +
       "1zFRUs/XnD\nWIUIeFBXAXZaPnH1vf0jW589KFyEubUtJ0V9QCtk0woxWiFB" +
       "K+TSQhUVnEQroynsBFreC/EKma1+\nVfTRLY8f7PODgxhzlaAiBtoHEtmMjM" +
       "j6sBvUozz/yeBZnT/ffSB07cRG4VlS6dxbdHfdBy9fOPav\n+tU+5CueGJmA" +
       "kJqDDE2EZdNswuv3hlIx/P84tO3Vixcuf8ENKor6C2K9cCeL1T6vKUxdJjHI" +
       "fi76\n40sa/DvR5GEfqoQEAEmP8w/5pNtLIy9mB538x2QJhFFdXDeTWGVLTt" +
       "IK0llTn3O/cB9p5PMWME4d\nc+I2eJbYXs3fbLXNYGO78ClmbY8UPL9e+1r1" +
       "3R+9Xneeq8VJxQ05xS5KqAjsJtdZxk1C4PvlH0a+\n94NPD+zmnmK7CoUKmJ" +
       "pRFTkDW+5wt0BEq5BVmCH7J7SkHlPiCp5RCfO4/zXcfs+v/v6tRmEaFb44\n" +
       "ll17fQTu9yX3oacuPPbvbo6mQmYVxRXDBRPStLiYh0wTzzM+Mk//oetH7+Aj" +
       "kPAgyVjKAuF5A3HJ\nENejxPW+mo8hz9o9bOgD3GtLuH6R+j0t738h0Zfa9/" +
       "vXONd1OLcRyDXDNmwMCsuzYSXTboc3eh/E\n1izAffHsQ480qmf/CxinAKMM" +
       "ddPabkLYZ/KMaENXBS699Xb74x/6kW8zCqo6jm3G3P9RLTgesWYh\n42SMjZ" +
       "u4bzXO1bCRi4y4EpbaCsjk/KsB5laVDv/NrPq7kTM9s/Zk+L3tR7gCSgZ+ke" +
       "LnwbPw5sTR\na+/TKxyPG4Fsd2+mMJ1Cx+Tu/dLFdFP1K88nfSgwhRplu6eb" +
       "xGqK+fkUtCCW0+hB35e3nt9OiNo5\nmM0wy73Rn0PWG/tuGoc5g2bzek+41z" +
       "NtdzhGcN654V6B+GQj39LPxzuzwRkwTCWNWZ8HHCpQP9ly\nB0UduUVDSUIf" +
       "wwJON0XuYOM6NmwSZl5f0h0GChltshltKsHoKBuGgB/LIPJN8rPlJvlZDk+z" +
       "zU9z\nCX4iNj8Bpp8kNhyWOgvq6jioEYqAh6cdi+CpxeappQRPOx2emI4Wwd" +
       "OuRfDUavPUWoKnx2yeapme\n7lOhP4Ow78w9s5lKEnq/NC90V5/pe+PdiecP" +
       "iOagTHbI2zUtf/VPH+/1f/utGbHPmwI8wIe7j//5\n1atjraKQiEPCyoI+PX" +
       "ePOCjwGGswWFLtLUeBQ59b0/vSk2NXbI6a89vdETgS/mX+bXLnvd/8pEhv\n" +
       "BvGuY+oxzvQijNNmG6ethHFUxzjMYbhx2AevWyRvknIXPO025fYSlPfZlIOg" +
       "FMua16D4FCNt3iTp\nZXY2cbJKMdJphzQYIYqThmrTxoYgFqfID2dJDydzZT" +
       "jhjnFHTmmrKJqgeOSJ1pr5UFepox33nwO7\n/ln/DD73qM9uGxJgJaobd6kk" +
       "TdQcUuwKpCuv697GD7Nu1Tp06sUz9MM1A8ITV5eOKe/G1QPHFnoG\nTj+3iF" +
       "67xyObF3VTetkO/6zyro+ft0URLDin528azC99QeAnZWrjeQVwRX6/OwhPr+" +
       "0NvV5v4PZ1\nDec2az6uV59jwu4CE3JRCSUm6wYdsPZcsKh4D0VGOZlvlGkH" +
       "v8uGZ6EfThkxqLheTwzM6LpKsOY6\n4qHrhYTTa/E/X8/XiATPOlsj625YIx" +
       "X5Tt1V1KkVLcFuiAhHc6SMxD9jw49Z6BN6PxSFlEWuV89d\n6X9yK9LfDc8G" +
       "W/oNn6P0L5aR/jQbTlJUB9JHIeumVPtO7obEP3Wr4g/Y4g98juK/Vkb8N9jw" +
       "ayH+\nGOxjkX3j4p+5FfG/As+ULf7UYrNBefGvJwkn804Z9bzPhnMU3WbJmE" +
       "KGiczq1E60OUmhMq0rMVcr\n529UK9DOB93rHXa+7Sy4BBYXl3L40hOPfBb+" +
       "43/4RUX2crEujGriKVXNPYLkzKsNk8QVLkidOJAY\n/PVRsUIorpsg8YkJZ/" +
       "SigL9EUaMXHqRmr1ywy+BGOWCs7xWzXKCPoZoDEJt+km2IG/n5lh3FQuIo\n" +
       "lsnTE9PMyrw6ye/lnVqWEjfz0/Kul3evyDw3/h1eIKtkFS8sMDTBMAqIC5ps" +
       "Pewtic3B9QF65fTk\n67/4slPv+QG+1b6VKXDtdWK1jN2hBhe/FRlJGpTfYy" +
       "z8puOX9544esXH72X+D9uQuDtOGQAA");
}
