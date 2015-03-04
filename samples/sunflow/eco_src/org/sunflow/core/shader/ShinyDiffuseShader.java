package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class ShinyDiffuseShader implements Shader {
    private Color diff;
    private float refl;
    public ShinyDiffuseShader() { super();
                                  diff = Color.GRAY;
                                  refl = 0.5F; }
    public boolean update(ParameterList pl, SunflowAPI api) { diff = pl.getColor(
                                                                          "diffuse",
                                                                          diff);
                                                              refl =
                                                                pl.
                                                                  getFloat(
                                                                    "shiny",
                                                                    refl);
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
                                                     x = dn *
                                                           state.
                                                             getNormal().
                                                             x +
                                                           state.
                                                             getRay().
                                                             getDirection().
                                                             x;
                                                   refDir.
                                                     y = dn *
                                                           state.
                                                             getNormal().
                                                             y +
                                                           state.
                                                             getRay().
                                                             getDirection().
                                                             y;
                                                   refDir.
                                                     z = dn *
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
                                                   cos = 1 -
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
                                                   Color r =
                                                     d.
                                                     copy().
                                                     mul(
                                                       refl);
                                                   ret.sub(
                                                         r);
                                                   ret.mul(
                                                         cos5);
                                                   ret.add(
                                                         r);
                                                   return lr.
                                                     add(
                                                       ret.
                                                         mul(
                                                           state.
                                                             traceReflection(
                                                               refRay,
                                                               0)));
    }
    public void scatterPhoton(ShadingState state, Color power) {
        Color diffuse;
        state.
          faceforward();
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
        float d =
          diffuse.
          getAverage();
        float r =
          d *
          refl;
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
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1170988822000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALUYa2wcxXnuYTs+O/iR2A552LFjXk64Jaip2pg0McaBSy7k" +
                                                   "uEsMcQAz3p2723hv\nd9mdPZ9NRAmtSAC1kD4kKpWQolQJEB4SrdJKlAYBLS" +
                                                   "WqBJVKJSTSVpHaSi2Vqko0Vfuj38zs3t7t\n3TkRUU/a2bmZ7zHf+5s9/Slq" +
                                                   "si20WrbjdN4kdnw8k8KWTZRxDdv2Hlialt9tak2d3KkbYRRKorCq\nUNSRlG" +
                                                   "1JwRRLqiIlbhstWWi9aWjzOc2gcVKi8QPaJpfejuSmGoJ3HzvTfehEdCCMmp" +
                                                   "KoA+u6QTFV\nDX1CIwWbos7kAVzEkkNVTUqqNh1NoqVEdwrjhm5TrFP7QfQw" +
                                                   "iiRRsykzmhQNJj3mEjCXTGzhgsTZ\nSynOFigsswjFqk6UsTI7wNxQjQnHdv" +
                                                   "HStdBAZAnbnARx+AlA6rVlqYW0NaKakVOTXzx4/IUI6phC\nHaqeYcRkkIQC" +
                                                   "vynUXiCFGWLZY4pClCnUpROiZIilYk1d4FynULet5nRMHYvYaWIbWpEBdtuO" +
                                                   "SSzO\n01tMonaZyWQ5MjWsso6yKtEU719TVsM5ELvXF1uIu52tg4AxFQ5mZb" +
                                                   "FMPJTorKqDxQeCGGUZh3cC\nAKC2FAjNG2VWUR3DAuoWttSwnpMy1FL1HIA2" +
                                                   "GQ5woWhlQ6JM1yaWZ3GOTFO0IgiXElsA1coVwVAo\n6gmCcUpgpZUBK1XYZ3" +
                                                   "3vZ0dOff/Nbdy3owqRNXb+GCD1B5DSJEssostEIF504t9J7HNWhxEC4J4A\n" +
                                                   "sIAZu+bM3uRffj4gYFbVgdk9c4DIdFq+8+hA+qHbDRRhx1hiGrbKjF8lOQ+H" +
                                                   "lLszWjIhanvLFNlm\n3Ns8m/7FvkdeJH8No1gCNcuG5hTAj7pko2CqGrFuJz" +
                                                   "qxMCVKArUSXRnn+wnUAvMkuLxY3Z3N2oQm\nUFTjS80G/w8qygIJpqJWmKt6" +
                                                   "1vDmJqZ5Pi+ZCKEWeNBGeNqR+PE3RVviku3oWc2Yk2xLlgwrV/4v\nGxaR7D" +
                                                   "xWiCVl8qo+f5uazTo2yfClOHMjk6J9Ut4oEAnLWFd1Q8qpELiycaNCiux9Jc" +
                                                   "RL7PTdc6EQ\nS4fBsNYgIu4wNICdlk9eeP/gxM7HjwiXYW7uyk3RCPCMuzzj" +
                                                   "jGdc8IzX8kShEGe1nPEW9gPtz0Ic\nQ8ZrvyFz344HjgxFwHHMuSiojoEOgY" +
                                                   "TugSZkY9wP9gTPizJ43Irn9x+OXzy5VXic1Dgn18Vu++Dl\nc8f/2T4SRuH6" +
                                                   "CZMJCik7xsikWJYtJ8LhYIjVo//3J3a9/tG5T673g42i4ZocUIvJYngoaBLL" +
                                                   "kIkC\nWdEnf+LqjsjdaPJoGEUhMUAy5OeHPNMf5FEVy6NeXmSytCRRW9awCl" +
                                                   "hjW14yi9G8Zcz5K9xXOvl8\nGRinjTn3Gng6XW/nb7bbY7KxV/gWs3ZACp53" +
                                                   "L36t+abfvdH2LleLl6I7KopghlAR8F2+s+yxCIH1\nT55Jffu7nx7ezz3FdR" +
                                                   "UKldGZ0VS5BCjX+igQ6RpkG2bI4b16wVDUrIpnNMI87r8d12z88d++2SlM\n" +
                                                   "o8GKZ9kNlybgr199K3rk3P3/6udkQjKrNL4YPpiQZplPecyy8Dw7R+nQb9Z8" +
                                                   "75f4WUiEkHxsdYHw\nfIK4ZIjrUeJ6H+FjPLC3kQ1DQHtDA9evU9en5YMv5o" +
                                                   "acB3/1U37qNlzZIFSaYRc2R4Xl2bCOabcv\nGL13YDsPcF84e+e9ndrZ/wDF" +
                                                   "KaAoQz21d1sQ9qUqI7rQTS0fv/V27wMfRlB4O4ppBla2Y+7/qBUc\nj9h5yD" +
                                                   "wlc+s24VtzSzwPKyGuhJWuAkoV/8JwuBsah/921hX4kTM9s+FU8v3dz3IFNA" +
                                                   "z8OkUxQGfh\nzb3HLv6anud0/Ahk2IOl2rQKnZSP+6WPil3Nrz1XCKOWKdQp" +
                                                   "u73eJNYc5udT0JrYXgMI/WDVfnWb\nIWrqaDnDrA5GfwXbYOz76RzmDJrN2w" +
                                                   "PhzmtZHzxL3XBfGgz3EOKTrRxlmI/XlYOzxbTUImb9H5wQ\nSgLf7qOor7J4" +
                                                   "qAXob1jAGZbIHWy8mQ3bhJk3NXSHzbUHvco96FUNDppgwxicxyJZDTxnReV1" +
                                                   "wFIL\n0FYUea688NjQz97b+9xhUV8WcbAqrGn5q7//w2zkqbdmBF7QiwLAR/" +
                                                   "tP/On1C+nlIheJ/nNdTQtY\niSN6UG6mDpPF5eBiHDj0O+sHTz+cPu+eqLu6" +
                                                   "k5qA28af598m193yjT/WKfPgMgamAbvsWMQu/GTX\nVoRnqK7Reccg2gMmxJ" +
                                                   "pGbSsX4PA9/2h/DL9zX9hNfWkK2cIwb9RIkWgVrKKMUlXnsIs36n7kPfHC\n" +
                                                   "S2foh+s3C1WMNDZqEHFk8/GFgc2vPvk5+oWBgGxB0l3FVXdF8up7YX6XEIFc" +
                                                   "cwepRhqtDt8YnMex\n9D1VQby2umaPwtPjxkZP3ZrtG84vOGE3w7om7K8xIR" +
                                                   "eVwBWHVTQPrLcSLCPeY6kEZ0MWKWmzbJiB\nmu6YcOMWkElTONtdkE1mDEMj" +
                                                   "WPcdUb5UgvDqBf9zf7VGJHhWuRpZddkaCVU79Zq6Tg13P3b7JZxM\ncRGJF9" +
                                                   "gAtTeWI9TtmS+VI33p6ZVIfxM8A670A/9H6b++iPRH2HCIojaQPg14zLUvX/" +
                                                   "xHr0T8r8Cz\nxRV/y+cNh8XFv5QknM23FlHPM2x4iqKltowphFgqb1A301RE" +
                                                   "RbRoqIqvlacvVytQk7tr72qsWV1R\n86VHfJ2Qkx8/dO9nyd/+m986yl8Q2u" +
                                                   "Aan3U0rbKfqJg3m1BpVS5Qm+guTP56vl5FEHdIyABiwg/8\nAwH/Q4o6g/Ag" +
                                                   "PXtVgp0Cd6oAg6zhziqBXqIoAkBsetr07NTJm1XWV8VFX1Wq0hfTzLqqgsE/" +
                                                   "vnlJ\n3RGf36ble17ev7b05J6neaVokjW8wIM8lkQt4rZVLgyDDal5tD5Ar7" +
                                                   "06+cYrX/YKH+/Gl5f8clzl\n4jeL3UXsD8Wo/hVnomBSfilZ+Enfj245eex8" +
                                                   "mF+y/gdee2ceMxUAAA==");
}
