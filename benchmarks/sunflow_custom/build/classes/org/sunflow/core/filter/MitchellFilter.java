package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class MitchellFilter implements Filter {
    public float getSize() { return 4.0F; }
    public float get(float x, float y) { return mitchell(x) * mitchell(y);
    }
    private float mitchell(float x) { final float B = 1 / 3.0F;
                                      final float C = 1 / 3.0F;
                                      final float SIXTH = 1 / 6.0F;
                                      x = Math.abs(x);
                                      float x2 = x * x;
                                      if (x > 1.0F) return ((-B - 6 * C) *
                                                              x *
                                                              x2 +
                                                              (6 *
                                                                 B +
                                                                 30 *
                                                                 C) *
                                                              x2 +
                                                              (-12 *
                                                                 B -
                                                                 48 *
                                                                 C) *
                                                              x +
                                                              (8 *
                                                                 B +
                                                                 24 *
                                                                 C)) * SIXTH;
                                      return ((12 - 9 * B - 6 * C) * x * x2 +
                                                (-18 +
                                                   12 *
                                                   B +
                                                   6 *
                                                   C) *
                                                x2 +
                                                (6 -
                                                   2 *
                                                   B)) * SIXTH; }
    public MitchellFilter() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XW2xURRie3d5L6bblViuUthS0FPZADCZYvJS1hcJCmxYw" +
                                                    "lshyenZ297TnxpzZdilWgcSU8ECIFgSjfTAQRLnFSNAYkj4JBF8wRuOD4JtE" +
                                                    "5YEXNEHFf2Z29+ye3RZ9cZOZnfPP/89/m/nmn3P3UJFNUKtlanujmkn9OEH9" +
                                                    "g9oaP91rYdu/KbimRyY2Dgc02ba3AS2kNF3yPXh4NFblRcX9aI5sGCaVqWoa" +
                                                    "di+2TW0Yh4PI51A7NKzbFFUFB+VhWYpTVZOCqk3bgmhWhihFzcGUCRKYIIEJ" +
                                                    "EjdBane4QGg2NuJ6gEnIBrX3oDeQJ4iKLYWZR1Fj9iKWTGQ9uUwP9wBWKGXf" +
                                                    "O8ApLpwgqCHtu/A5x+FjrdLEu7uqPi1Avn7kU40+Zo4CRlBQ0o8qdKwPYGK3" +
                                                    "h8M43I+qDYzDfZiosqaOcrv7UY2tRg2ZxglOB4kR4xYmXKcTuQqF+UbiCjVJ" +
                                                    "2r2IirVw6qsooslR8HW+46vwsJPRwcFyFQwjEVnBKZHCIdUIU7TYLZH2sXkz" +
                                                    "MIBoiY5pzEyrKjRkIKAakTtNNqJSHyWqEQXWIjMOWiiqm3ZRFmtLVobkKA5R" +
                                                    "VOvm6xFTwFXGA8FEKJrnZuMrQZbqXFnKyM+9reuO7DM2Gl5ucxgrGrO/FITq" +
                                                    "XUK9OIIJNhQsBCuWB4/L868e8iIEzPNczILnyuv3X1pRP3Vd8DyZh6d7YBAr" +
                                                    "NKScGqi8tTDQsraAmVFqmbbKkp/lOd/+PcmZtoQFJ29+ekU26U9NTvV+9er+" +
                                                    "j/GvXlTehYoVU4vrsI+qFVO3VA2TDdjARKY43IXKsBEO8PkuVALjoGpgQe2O" +
                                                    "RGxMu1ChxknFJv+GEEVgCRaiEhirRsRMjS2Zxvg4YSGESqChldCKkPjxf4p0" +
                                                    "KWbqWJIV2VANU4K9i2WixCSsmCGCLVPqCHRLAxDlmC6TIVuy40ZEM0dCStym" +
                                                    "pi7ZRJFMEk2RJcUkWAKLYDdJW1SqxLCmdfJPP9t21v+tMMEiUDXi8UByFrqh" +
                                                    "QYNTtdHUwpiElIn4+o77F0I3vemjkowdRctAnz+pz8/0+YU+f7Y+5PFwNXOZ" +
                                                    "XpF/yN4Q4AAgZEVL32ubdh9qKoCNZ40UQugZaxM4nTSmQzEDDlh0cUhUYMfW" +
                                                    "frhz3P/HmRfFjpWmR/a80mjqxMiBHW+u8iJvNkQz54BUzsR7GLCmAbTZfTTz" +
                                                    "resbv/vg4vEx0zmkWZifxI5cSXb2m9xpIKaCw4CmzvLLG+TLoatjzV5UCIAC" +
                                                    "IEpl2PSAT/VuHVkY0JbCU+ZLETgcMYkua2wqBYLlNEbMEYfC90cl62rEVmEJ" +
                                                    "dBnIobjzi6mTl99rXevNRG1fxj3Yh6nAgGon/9sIxkD/8UTPO8fuje/kyQeO" +
                                                    "JfkUNLM+AIgA2YCIvXV9zw93bp/61utsGApXY3xAU5UErLHM0QJ4oQFmsbQ2" +
                                                    "bzd0M6xGVHlAw2zf/elbuvryb0eqRKI0oKTyvOLxCzj0J9aj/Td3/V7Pl/Eo" +
                                                    "7L5yPHfYRADmOCu3EyLvZXYkDnyz6OQ1+QOAU4AwWx3FHJUQ9wzx0Pt5Rlp4" +
                                                    "v9I1t4p1DVbOXIJTapNf/KOR982se0rEjQ2fzuT08PE8ihbknGxxlFmAF013" +
                                                    "RfHr9dTBiclw9+nV4ljWZMN+B1Q157/762v/iZ9u5MGTMmpaKzU8jLUMmwqY" +
                                                    "yiw42MJvb+dQHD77yRV6q/U5oXL59EjgFrx28Je6bS/Edv8HEFjsct695Nkt" +
                                                    "525sWKa87UUF6fOfU5BkC7VlhgGUEgwVlMECyijlPM313IBqCAdrqBZacfK6" +
                                                    "4v9sdo7F+rnitLLumRn2zcszzHWyrp2ikiimfbAhIQEtMxTORNXhLh9OFhvS" +
                                                    "WM2doffvnhfJcFcmLmZ8aOLwI/+RCW9G+bYkp4LKlBElHDdztojJI/h5oP3N" +
                                                    "GvOBEcQVXhNI1hEN6ULCstgWbpzJLK6i8+eLY19+NDbuTcZkLWXYZso096hx" +
                                                    "wvPZCZoLrTyZoPJ/nSAvX9HLPjfzjrNunyFVr7Cul6ICSBWXeKx5NYxYB60y" +
                                                    "aV5lXvOmQQzYFBZRhyGSCZdVHgdShNm7ZzB7gHX9FJXqyUJhGtsTFFVm1xIM" +
                                                    "RmtzXjKi+lYuTPpKF0xu/57fjukKuQzK1Ehc0zLOWOZ5K7YIjqjcrDJx6Vn8" +
                                                    "L5oPBUV9Q9lbhQ24wRHBPwivPzc/RYXsL5NNp2hWBhsENDnKZAIsLAAmNtxj" +
                                                    "pTC5it8e7HniF7V4AmWBvJUN+Zm3KTu0/JWYQq24eCeGlIuTm7buu//saQ6B" +
                                                    "RfC+HB3lrwp4JIkaIY18jdOullqreGPLw8pLZUtTZyarenDZtjj/JduhW5Rf" +
                                                    "i6OfL/hs3ZnJ2/yW/wdIA0E+vg8AAA==");
}
