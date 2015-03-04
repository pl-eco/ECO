package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class ViewIrradianceShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { state.faceforward();
                                                   return new Color().set(
                                                                        state.
                                                                          getIrradiance(
                                                                            Color.
                                                                              WHITE)).
                                                     mul(
                                                       1.0F /
                                                         (float)
                                                           Math.
                                                             PI);
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {  }
    public ViewIrradianceShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
