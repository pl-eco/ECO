package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.LightSample;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class ViewCausticsShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { state.faceforward();
                                                   state.initCausticSamples(
                                                           );
                                                   Color lr = Color.black(
                                                                      );
                                                   for (LightSample sample
                                                         :
                                                         state) lr.madd(sample.
                                                                          dot(
                                                                            state.
                                                                              getNormal(
                                                                                )),
                                                                        sample.
                                                                          getDiffuseRadiance(
                                                                            ));
                                                   return lr.
                                                     mul(
                                                       1.0F /
                                                         (float)
                                                           Math.
                                                             PI);
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        
    }
    public ViewCausticsShader() { super();
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/I4YOP4FZFySey8QAAA=");
}
