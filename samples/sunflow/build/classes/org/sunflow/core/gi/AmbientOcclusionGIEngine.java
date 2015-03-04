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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
