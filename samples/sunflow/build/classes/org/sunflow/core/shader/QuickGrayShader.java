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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/XzV2ck7vKv4F7W35zEwEAAA");
}
