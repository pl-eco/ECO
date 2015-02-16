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
      1166299774000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "3GLijB7+Yvrnq86M3REtx7+0dhhKTRAAAA==");
}
