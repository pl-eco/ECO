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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "5+vOjt4RI8e/6iIo9TcQAAA=");
}
