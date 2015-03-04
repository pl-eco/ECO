package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class QuickGrayShader implements Shader {
    public QuickGrayShader() { super(); }
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { if (state.getNormal() ==
                                                         null) { return state.
                                                                   getShader(
                                                                     ) !=
                                                                   this
                                                                   ? state.
                                                                   getShader(
                                                                     ).
                                                                   getRadiance(
                                                                     state)
                                                                   : Color.
                                                                       BLACK;
                                                   }
                                                   state.faceforward();
                                                   state.initLightSamples(
                                                           );
                                                   state.initCausticSamples(
                                                           );
                                                   return state.diffuse(Color.
                                                                          GRAY);
    }
    public void scatterPhoton(ShadingState state, Color power) { Color diffuse;
                                                                 if (Vector3.
                                                                       dot(
                                                                         state.
                                                                           getNormal(
                                                                             ),
                                                                         state.
                                                                           getRay(
                                                                             ).
                                                                           getDirection(
                                                                             )) >
                                                                       0.0) {
                                                                     state.
                                                                       getNormal(
                                                                         ).
                                                                       negate(
                                                                         );
                                                                     state.
                                                                       getGeoNormal(
                                                                         ).
                                                                       negate(
                                                                         );
                                                                 }
                                                                 diffuse =
                                                                   Color.
                                                                     GRAY;
                                                                 state.
                                                                   storePhoton(
                                                                     state.
                                                                       getRay(
                                                                         ).
                                                                       getDirection(
                                                                         ),
                                                                     power,
                                                                     diffuse);
                                                                 float avg =
                                                                   diffuse.
                                                                   getAverage(
                                                                     );
                                                                 double rnd =
                                                                   state.
                                                                   getRandom(
                                                                     0,
                                                                     0,
                                                                     1);
                                                                 if (rnd <
                                                                       avg) {
                                                                     power.
                                                                       mul(
                                                                         diffuse).
                                                                       mul(
                                                                         1.0F /
                                                                           avg);
                                                                     OrthoNormalBasis onb =
                                                                       state.
                                                                       getBasis(
                                                                         );
                                                                     double u =
                                                                       2 *
                                                                       Math.
                                                                         PI *
                                                                       rnd /
                                                                       avg;
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
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
