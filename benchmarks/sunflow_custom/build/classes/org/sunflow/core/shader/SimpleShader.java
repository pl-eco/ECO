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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "fYdpM3E3D34x5fOVZ0buiFbjX2RUkKQ9EAAA");
}
