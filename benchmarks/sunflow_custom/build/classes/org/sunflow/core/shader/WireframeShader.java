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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "ct9zq2+LC8Z/AKYa3DVhFAAA");
}
