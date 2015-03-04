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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAALVYb2wcRxWfW/93HJ/tNInrxk7iOoE45ZYgihRcpbjGTpxe" +
       "kiNOU+GIuuPdOd8me7ub3Tn74mBIg6pErYgQOCWFYiSUqrSkTYWICkKV8gXa" +
       "qnwpQiA+0CK+UFHyIR8oFQXKezP77/buTIKEpZ2bnX3vzXvz3vu9N75ygzR5" +
       "Ltnp2OapOdPmGVbmmePmvRl+ymFeZn/23hx1PaaPmdTzjsDajDb4cvr9D79R" +
       "6FJI8zRZRy3L5pQbtuUdZp5tzjM9S9LR6rjJih4nXdnjdJ6qJW6Yatbw+EiW" +
       "rImxcjKUDVRQQQUVVFCFCupoRAVMa5lVKo4hB7W4d5J8haSypNnRUD1OtlYK" +
       "cahLi76YnLAAJLTi+1EwSjCXXbIltF3aXGXwxZ3q8rcf6fpxA0lPk7RhTaE6" +
       "GijBYZNp0lFkxVnmeqO6zvRp0m0xpk8x16CmsSj0niY9njFnUV5yWXhIuFhy" +
       "mCv2jE6uQ0Pb3JLGbTc0L28wUw/emvImnQNbN0S2SgsncB0MbDdAMTdPNRaw" +
       "NJ4wLJ2TzUmO0MahB4EAWFuKjBfscKtGi8IC6ZG+M6k1p05x17DmgLTJLsEu" +
       "nPTVFYpn7VDtBJ1jM5z0July8hNQtYmDQBZO1ifJhCTwUl/CSzH/3Dh434XT" +
       "1j5LETrrTDNR/1ZgGkgwHWZ55jJLY5KxYzj7FN3w6nmFECBenyCWNK98+ebn" +
       "7hm4/rqkuasGzaHZ40zjM9rl2c63No3t2N2AarQ6tmeg8yssF+Gf87+MlB3I" +
       "vA2hRPyYCT5eP/zLL555gb2nkPZJ0qzZZqkIcdSt2UXHMJm7l1nMpZzpk6SN" +
       "WfqY+D5JWmCeNSwmVw/l8x7jk6TRFEvNtniHI8qDCDyiFpgbVt4O5g7lBTEv" +
       "O4SQFnhIBp41RP6JX06OqQW7yFSqUcuwbBVil1FXK6hMs1WPFh0TvOaVrLxp" +
       "L6ieq6m2Oxe+a7bLVK9AdeaqDxsuy0OCsinxnsEgc/6/4stoXddCKgUHvymZ" +
       "9iZkzD7bBNoZbbn0wPjNl2beVMI08M+Fk4/Bhhl/wwxumJEbZhIbklRK7HMH" +
       "biydC645AUkO8NexY+pL+x89P9gAUeUsNMK5Iukg2OhrM67ZYxESTAq80yAc" +
       "e39w7Fzmg+ful+Go1oftmtzk+qWFx45+9ZMKUSrxF62DpXZkzyFqhug4lMy7" +
       "WnLT5959/+pTS3aUgRWA7gNDNScm9mDSD66tMR2gMhI/vIVem3l1aUghjYAW" +
       "gJCcQkQD+Awk96hI8JEALNGWJjA4b7tFauKnAOHaecG1F6IVESCdYt4dRHwf" +
       "PN1+Cohf/LrOwfEOGVDo5YQVAownfnb96Wvf2blbieN2OlYJpxiXKNAdBckR" +
       "lzFY/8Ol3Lcu3jh3TEQIUNxda4MhHMcAE8BlcKyPv37y9++8ffk3ShRVHIpj" +
       "adY0tDLI2B7tAohhAmqh74cesoq2buQNOmsyDM5/prftuvbXC13SmyasBMFw" +
       "z38XEK3f+QA58+Yjfx8QYlIaVqzI8ohMHsC6SPKo69JTqEf5sV/3P/0a/R4A" +
       "KoCYZywygUtEWEbE0avCVcNizCS+7cJhi1P1rSxWesVbI2y9o34STWDhjSXf" +
       "Pw6Zs2f/9IGwqCp9atSbBP+0euWZvrE97wn+KI6Re3O5GpGgSYl4P/VC8W/K" +
       "YPMvFNIyTbo0vwM6Ss0SRss0VH0vaIugS6r4XlnBZbkaCfN0UzKHYtsmMyhC" +
       "QpgjNc7bE0nTgae8KZgEv/GkSREx2S1YBsW4DYePBzHb4rjGPMX2irRh8YJo" +
       "8duh9ZxsjAOwUYQeIiO+i1SU7v50tTJrfWXW1lFmFIcR2A+KoxnKu7++0F54" +
       "On2hnXWEft4X2rRg6LyweqzlXKMIpX/e703UpZ53Tjzz7osS6JOBlSBm55ef" +
       "+ChzYVmJdXt3VzVccR7Z8QnPrZV2fQR/KXj+jQ/agwuy4veM+W3HlrDvcBwE" +
       "lK2rqSW2mPjz1aWf/3DpnDSjp7LZGYde/sXf/utXmUt/fKNGpYXQsymv74G7" +
       "4En7HkjX8cAXfA+0arb3MDoB3/dLmcL6oRgapGqGmKjxsqajzf31GlFh7+Wz" +
       "yyv6oWd3KT4IHYSI4rbzCZPNMzO2FV66+ivK/QHRekcJ/8TzP3qFv7Xzs/Lk" +
       "husHTpLxtbN/6Tuyp/DobRT5zQmbkiKfP3Dljb3btW8qpCHEjarbRCXTSCVa" +
       "tLsMrj/WkQrMGAj9uQ7dd6dfbIOiW11oI4dFkK+I81QC1w1UuU6YyuCygjUl" +
       "INsQJ5uSv6O5SbHN7CpFRcQPXGeaS44OeSBo9uLwoCwrBwC8Zm3bZNSqrjxi" +
       "4VhotGgm+uEZ9I0evGWjU5Xx2l8zXuGihldVJsScXMWoEg5wU+mYY3yiAv5u" +
       "3Ybtvg3bb9cGfHUF1elVVFzCoSxVzAYV4TZUxBIw7Ks4/L+r+LVVVHwchzOc" +
       "rAEVD8PpYxbcmoaizdwDz6Sv4eTtRr/QUGwmSJ9cRc2v43CeA+xrlENW5Ao2" +
       "98EhEciN87ah1+ifoIdN3HWwg+ut+jeKvPprL62kWzeuPPQ70b2H1/M2uCPn" +
       "S6YZ7yhi82YHNjCEwm2yv5BwfbEWOMsLGCSlnAiNlyX9JU66kvRgGf7Eyb4L" +
       "bouRQRL7szjRCicNQITT7ztB6nWJxhU7q4zsrMokBvPYu8ffKhp5RHLxL6oA" +
       "dUvyn1Qz2tWV/QdP3/zMswLCmzSTLi6ilNYsaZF3mBC5t9aVFshq3rfjw86X" +
       "27YFFakThx7/4pLQbXPt/n686HDRkS/+dONP7ntu5W1xwfgP60MgtjsUAAA=");
}
