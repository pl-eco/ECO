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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
