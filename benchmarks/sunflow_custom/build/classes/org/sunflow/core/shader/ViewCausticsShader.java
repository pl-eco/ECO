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
      1166307204000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "YuKEHv5i5uerz4zdEQ3Hv8wWHL5FEAAA");
}
