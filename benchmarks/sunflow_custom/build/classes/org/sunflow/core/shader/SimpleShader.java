package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class SimpleShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { return new Color(Math.
                                                                      abs(
                                                                        state.
                                                                          getRay(
                                                                            ).
                                                                          dot(
                                                                            state.
                                                                              getNormal(
                                                                                ))));
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        
    }
    public SimpleShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1163966490000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "v5jx+aozY3dEq/Evo6DMUy0QAAA=");
}
