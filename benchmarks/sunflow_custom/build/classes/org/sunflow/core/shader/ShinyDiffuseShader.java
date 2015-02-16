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
                                                     x = dn *
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
                                                     y = dn *
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
                                                     z = dn *
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
                                                     white(
                                                       );
                                                   Color r =
                                                     d.
                                                     copy(
                                                       ).
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
          faceforward(
            );
        diffuse =
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
            diffuse);
        float d =
          diffuse.
          getAverage(
            );
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
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1170988822000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfW/93HPvi4CSExE6MgxqH3jZVqZQaQo1xiMNB" +
                                                    "TnESCdNi5nbnfBvP7S67s/bF1AUitY4iNaJgaKioP1RBFBoIQkS0qpDypQVE" +
                                                    "v1BVrfqhUPVLUWk+5EMpKm3pezN7t3t7Zzf5UkszNzv73rz35r33e2994Qpp" +
                                                    "8T2yx3X4yRnuiAwri8wJfltGnHSZnzmUvS1HPZ+ZY5z6/lHYmzYGX+v55LMn" +
                                                    "i2mNtE6RjdS2HUGF5dj+EeY7fI6ZWdIT7Y5zVvIFSWdP0DmqB8LietbyxUiW" +
                                                    "rIuxCjKUraiggwo6qKBLFfTRiAqY1jM7KI0hB7WF/wj5NkllSatroHqC7Kw9" +
                                                    "xKUeLYXH5KQFcEI7Ph8HoyRz2SM7qrYrm+sMfmaPvvyDh9KvN5GeKdJj2ZOo" +
                                                    "jgFKCBAyRbpKrJRnnj9qmsycIhtsxsxJ5lmUWwtS7ynS61szNhWBx6qXhJuB" +
                                                    "yzwpM7q5LgNt8wJDOF7VvILFuFl5ailwOgO2bopsVRYewH0wsNMCxbwCNViF" +
                                                    "pXnWsk1BBpIcVRuH7gUCYG0rMVF0qqKabQobpFf5jlN7Rp8UnmXPAGmLE4AU" +
                                                    "QbaueijetUuNWTrDpgXZkqTLqVdA1SEvAlkE6UuSyZPAS1sTXor558r9t599" +
                                                    "1D5oa1Jnkxkc9W8Hpv4E0xFWYB6zDaYYu4azz9JNb53WCAHivgSxonnzW1e/" +
                                                    "fmv/5XcUzU0NaA7nTzBDTBvn893vbxvbva8J1Wh3Hd9C59dYLsM/F74ZKbuQ" +
                                                    "eZuqJ+LLTOXl5SO/euDxl9nHGumcIK2Gw4MSxNEGwym5FmfePcxmHhXMnCAd" +
                                                    "zDbH5PsJ0gbrrGUztXu4UPCZmCDNXG61OvIZrqgAR+AVtcHasgtOZe1SUZTr" +
                                                    "sksIaYNB9sLoIupP/gpi6cd8CHedGtS2bEeH4GXUM4o6M5zpPNxusUS9WV83" +
                                                    "Al84Jd0P7AJ35nXfM3THm6k+G47HdL9ITebpk0XLPnm3VSgEPpuUWxkMOff/" +
                                                    "KayMlqfnUylwyrYkJHDIpoMOB9ppYzm4a/zqq9PvadUUCe9MkGGQmQllZlBm" +
                                                    "RsnM1MskqZQUdQPKVr4Hz80CBgA6du2e/Oahh08PNkHQufPNcO1IOgg2hwqN" +
                                                    "G85YBBQTEg4NiNYtP35wKfPpi3eqaNVXR/WG3OTyufknjj/2JY1otfCMBsJW" +
                                                    "J7LnEFSr4DmUTMtG5/YsffTJxWcXnShBa/A+xI16Tsz7waQrPMdgJiBpdPzw" +
                                                    "Dnpp+q3FIY00A5gAgAoKAQ/Y1J+UUZP/IxUsRVtawOCC45Uox1cVAOwURc+Z" +
                                                    "j3ZkjHTL9QZwyjpMiO0w0mGGyF98u9HF+QYVU+jlhBUSqw/8/PJzl364Z58W" +
                                                    "h/WeWKGcZEKBxIYoSI56jMH+H8/lnn7mytKDMkKA4uZGAoZwHgPIAJfBtX7n" +
                                                    "nUf+8OEH53+rRVEloHYGeW4ZZTjjlkgKAAoHUEPfDx2zS45pFSya5wyD8189" +
                                                    "u/Ze+tvZtPImh51KMNz6vw+I9m+8izz+3kP/6JfHpAwsaJHlEZm6gI3RyaOe" +
                                                    "R0+iHuUnfrP9ubfpjwBvAeN8a4FJ2CLSMiKvXpeuGpZzJvFuL0473Lp3Zbmz" +
                                                    "RT5pIHr36kl0AOtyLPn+eZjnT/35U2lRXfo0KEcJ/in9wvNbx/Z/LPmjOEbu" +
                                                    "gXI9KEEPE/F++eXS37XB1l9qpG2KpI2wQTpOeYDRMgVNgV/pmqCJqnlfW+BV" +
                                                    "NRup5um2ZA7FxCYzKAJDWCM1rjsTSSOryGYY68OkWZ9MmhSRi32SZVDOu3D6" +
                                                    "QiVm21zPmqPYfYGWAKqSpE+QzXH4tUrQXWAcOvIG08rTX6nXozvUo3sVPUZx" +
                                                    "GgFRHivwtQMi51klKN9zYX+hL/Z+OPv8R68oNE56P0HMTi+f+TxzdlmLdWw3" +
                                                    "1zVNcR7VtcnrXa/M+hz+UjD+gwPNwQ1VtXvHwtZhR7V3cF3M+p1rqSVFHPjL" +
                                                    "xcVf/GRxSZnRW9uwjEM//srv/v3rzLk/vdugIkJ8OFQoB0hNh2LplWroOFk3" +
                                                    "VZFE/bav1vhJ3c6fWl4xD7+wVwuz+l5BOoTjfpGzOcZjoprxpJr6eZ9sdaMM" +
                                                    "OvPST98U7+/5mrJyeHUnJxnfPvXXrUf3Fx++jqo5kLApeeRL9114955bjKc0" +
                                                    "0lRNxLruvZZppDb9Oj0Gnxv20Zok7K8G/0YMihth9IXB39ewckUOizBUC5Ex" +
                                                    "dF1/neukqQw+DhCkK2Sb4mST6nc0NyHFfGMNlM7j9ACUqcA1IWYlzd04HVQ4" +
                                                    "fQjQIO84nFG7HsrlxrGq0TjITeGorK/N6FRtvG5vGK/wYYSfhkweM7uGUbLh" +
                                                    "LgrSOcNE2BTizp3XZsE2GAOhBQPXawE+ckkVrKHgPE7QQK0DBY+AYRhg16ah" +
                                                    "bIn2w7gj1PCO6w0sqaEUJkkX11DzMZwWBKCfQQUEXK7oiDDvEjHSPOdYZoNa" +
                                                    "D7hY35pjw7Gl7p8C6kPWeHWlp33zyrHfy2az+rHZAV98hYDzeAGMrVtdqB2W" +
                                                    "1LlDlUMFhkuNoE99MkDIq4VU+ruK/owg6SQ9GIc/cbLvgediZJAi4SpO9KQg" +
                                                    "TUCEy++7lcBOyz4LG4GMagTKJAai2GrGn2r6TsRJ+Q+XCqYF6l8u08bFlUP3" +
                                                    "P3r1qy9IgGwxOF1YwFPas6RNtdxVXNy56mmVs1oP7v6s+7WOXRW878apN+yz" +
                                                    "E7oNNG5Hx0uukA3kws82v3H7iysfyH74v8iQzHYJEwAA");
}
