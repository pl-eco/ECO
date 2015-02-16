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
      1168808332000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
