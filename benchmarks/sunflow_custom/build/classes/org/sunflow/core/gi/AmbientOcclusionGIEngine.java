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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "1t4UPfG/AcDhXgEjEwAA");
}
