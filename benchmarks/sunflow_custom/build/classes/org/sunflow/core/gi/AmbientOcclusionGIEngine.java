package org.sunflow.core.gi;
import org.sunflow.core.GIEngine;
import org.sunflow.core.Options;
import org.sunflow.core.Ray;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class AmbientOcclusionGIEngine implements GIEngine {
    private Color bright;
    private Color dark;
    private int samples;
    private float maxDist;
    public AmbientOcclusionGIEngine(Options options) { super();
                                                       bright = options.getColor(
                                                                          "gi.ambocc.bright",
                                                                          Color.
                                                                            WHITE);
                                                       dark =
                                                         options.
                                                           getColor(
                                                             "gi.ambocc.dark",
                                                             Color.
                                                               BLACK);
                                                       samples =
                                                         options.
                                                           getInt(
                                                             "gi.ambocc.samples",
                                                             32);
                                                       maxDist =
                                                         options.
                                                           getFloat(
                                                             "gi.ambocc.maxdist",
                                                             0);
                                                       maxDist =
                                                         maxDist <=
                                                           0
                                                           ? Float.
                                                               POSITIVE_INFINITY
                                                           : maxDist;
    }
    public Color getGlobalRadiance(ShadingState state) {
        return Color.
                 BLACK;
    }
    public boolean init(Scene scene) { return true;
    }
    public Color getIrradiance(ShadingState state,
                               Color diffuseReflectance) {
        OrthoNormalBasis onb =
          state.
          getBasis(
            );
        Vector3 w =
          new Vector3(
          );
        Color result =
          Color.
          black(
            );
        for (int i =
               0;
             i <
               samples;
             i++) {
            float xi =
              (float)
                state.
                getRandom(
                  i,
                  0,
                  samples);
            float xj =
              (float)
                state.
                getRandom(
                  i,
                  1,
                  samples);
            float phi =
              (float)
                (2 *
                   Math.
                     PI *
                   xi);
            float cosPhi =
              (float)
                Math.
                cos(
                  phi);
            float sinPhi =
              (float)
                Math.
                sin(
                  phi);
            float sinTheta =
              (float)
                Math.
                sqrt(
                  xj);
            float cosTheta =
              (float)
                Math.
                sqrt(
                  1.0F -
                    xj);
            w.
              x =
              cosPhi *
                sinTheta;
            w.
              y =
              sinPhi *
                sinTheta;
            w.
              z =
              cosTheta;
            onb.
              transform(
                w);
            Ray r =
              new Ray(
              state.
                getPoint(
                  ),
              w);
            r.
              setMax(
                maxDist);
            result.
              add(
                Color.
                  blend(
                    bright,
                    dark,
                    state.
                      traceShadow(
                        r)));
        }
        return result.
          mul(
            (float)
              Math.
                PI /
              samples);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1166302002000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "XFl++RfL51wz+mubpXG4Czz/9j9/n774pzcaVOIWaISbe2AInqTngWQTD8z4" +
       "HtBp5XsANkLCJA6H3Pyf4hjaJvX2EecxEkKGKgBtqgMgvwXAc9jSrLkVZ3Dp" +
       "7MpqfurpWyQPkqY56eSm9TWNLTAttFkLSqrpDg6Jdj5I/wee/eVL/K0933JP" +
       "c3fzYIoyvnb2o6Ejd5SO/xc9wdaITVGRzx66/Mb+m5UHJdJSRZG6G0ot02gt" +
       "dnTZDK5UxpEaBBmuehkfkvI87Xu8vi4HLgsKQKR6bKlz3kyJ5uEehDdBJsQU" +
       "1yghAgsgo/qKjO/XzBzVDgM3GiRAor62iIXjVUMGfAAa9gwZ/l8N2VBviAIZ" +
       "KfhPrmFBGQe4ysBtQW2YBPGcaWqMGl9qS5+vf8qzJfUf2yIJiRK+ilvKmCC9" +
       "dw21z+BwmpMeOPhJaBjWPHSoEoPN2nRsPgbr/k3gXm2VF1YTHRtXj74jGs/q" +
       "9bMT7oCFsqaFa11o3m7ZrKAKLTvdyueCx/2cDDS4QHAiFVWh8k9cuvOcJKN0" +
       "4B/8CZP9lJN1ITKsJe4sTHQBoBKIcPozyw+VpOi1sNan3VpfISGowXYz/FbT" +
       "eyKaiH+9+Jlfdv/5MqdcWT141+kbtz0tYKRN0ejSEkrpyJK423ZX0WN7U2m+" +
       "rPYDuz7vfbFzp4+KvTj0e712RLetjVvScd3ioolc+s3GX9/+zOr7oif+N8B1" +
       "GXETEwAA");
}
