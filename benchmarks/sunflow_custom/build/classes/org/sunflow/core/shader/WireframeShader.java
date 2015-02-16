package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
public class WireframeShader implements Shader {
    private Color lineColor;
    private Color fillColor;
    private float width;
    private float cosWidth;
    public WireframeShader() { super();
                               lineColor = Color.BLACK;
                               fillColor = Color.WHITE;
                               width = (float) (Math.PI * 0.5 / 4096);
                               cosWidth = (float) Math.cos(width); }
    public boolean update(ParameterList pl, SunflowAPI api) { lineColor =
                                                                pl.
                                                                  getColor(
                                                                    "line",
                                                                    lineColor);
                                                              fillColor =
                                                                pl.
                                                                  getColor(
                                                                    "fill",
                                                                    fillColor);
                                                              width = pl.
                                                                        getFloat(
                                                                          "width",
                                                                          width);
                                                              cosWidth =
                                                                (float)
                                                                  Math.
                                                                  cos(
                                                                    width);
                                                              return true;
    }
    public Color getFillColor(ShadingState state) { return fillColor;
    }
    public Color getLineColor(ShadingState state) { return lineColor;
    }
    public Color getRadiance(ShadingState state) { Point3[] p =
                                                     new Point3[3];
                                                   if (!state.
                                                         getTrianglePoints(
                                                           p))
                                                       return getFillColor(
                                                                state);
                                                   Point3 center =
                                                     state.
                                                     getPoint(
                                                       );
                                                   Matrix4 w2c =
                                                     state.
                                                     getWorldToCamera(
                                                       );
                                                   center =
                                                     w2c.
                                                       transformP(
                                                         center);
                                                   for (int i =
                                                          0;
                                                        i <
                                                          3;
                                                        i++)
                                                       p[i] =
                                                         w2c.
                                                           transformP(
                                                             state.
                                                               getInstance(
                                                                 ).
                                                               transformObjectToWorld(
                                                                 p[i]));
                                                   float cn =
                                                     1.0F /
                                                     (float)
                                                       Math.
                                                       sqrt(
                                                         center.
                                                           x *
                                                           center.
                                                             x +
                                                           center.
                                                             y *
                                                           center.
                                                             y +
                                                           center.
                                                             z *
                                                           center.
                                                             z);
                                                   for (int i =
                                                          0,
                                                          i2 =
                                                            2;
                                                        i <
                                                          3;
                                                        i2 =
                                                          i,
                                                          i++) {
                                                       float t =
                                                         (center.
                                                            x -
                                                            p[i].
                                                              x) *
                                                         (p[i2].
                                                            x -
                                                            p[i].
                                                              x);
                                                       t +=
                                                         (center.
                                                            y -
                                                            p[i].
                                                              y) *
                                                           (p[i2].
                                                              y -
                                                              p[i].
                                                                y);
                                                       t +=
                                                         (center.
                                                            z -
                                                            p[i].
                                                              z) *
                                                           (p[i2].
                                                              z -
                                                              p[i].
                                                                z);
                                                       t /=
                                                         p[i].
                                                           distanceToSquared(
                                                             p[i2]);
                                                       float projx =
                                                         (1 -
                                                            t) *
                                                         p[i].
                                                           x +
                                                         t *
                                                         p[i2].
                                                           x;
                                                       float projy =
                                                         (1 -
                                                            t) *
                                                         p[i].
                                                           y +
                                                         t *
                                                         p[i2].
                                                           y;
                                                       float projz =
                                                         (1 -
                                                            t) *
                                                         p[i].
                                                           z +
                                                         t *
                                                         p[i2].
                                                           z;
                                                       float n =
                                                         1.0F /
                                                         (float)
                                                           Math.
                                                           sqrt(
                                                             projx *
                                                               projx +
                                                               projy *
                                                               projy +
                                                               projz *
                                                               projz);
                                                       float dot =
                                                         projx *
                                                         center.
                                                           x +
                                                         projy *
                                                         center.
                                                           y +
                                                         projz *
                                                         center.
                                                           z;
                                                       if (dot *
                                                             n *
                                                             cn >=
                                                             cosWidth)
                                                           return getLineColor(
                                                                    state);
                                                   }
                                                   return getFillColor(
                                                            state);
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {  }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169098062000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/gO28gmkURQAAA==");
}
