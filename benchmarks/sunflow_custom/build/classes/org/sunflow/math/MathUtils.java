package org.sunflow.math;
public final class MathUtils {
    private MathUtils() { super(); }
    public static final int clamp(int x, int min, int max) { if (x > max)
                                                                 return max;
                                                             if (x > min)
                                                                 return x;
                                                             return min; }
    public static final float clamp(float x, float min, float max) { if (x >
                                                                           max)
                                                                         return max;
                                                                     if (x >
                                                                           min)
                                                                         return x;
                                                                     return min;
    }
    public static final double clamp(double x, double min, double max) { if (x >
                                                                               max)
                                                                             return max;
                                                                         if (x >
                                                                               min)
                                                                             return x;
                                                                         return min;
    }
    public static final int min(int a, int b, int c) {
        if (a >
              b)
            a =
              b;
        if (a >
              c)
            a =
              c;
        return a;
    }
    public static final float min(float a, float b,
                                  float c) { if (a >
                                                   b)
                                                 a =
                                                   b;
                                             if (a >
                                                   c)
                                                 a =
                                                   c;
                                             return a;
    }
    public static final double min(double a, double b,
                                   double c) { if (a >
                                                     b)
                                                   a =
                                                     b;
                                               if (a >
                                                     c)
                                                   a =
                                                     c;
                                               return a;
    }
    public static final float min(float a, float b,
                                  float c,
                                  float d) { if (a >
                                                   b)
                                                 a =
                                                   b;
                                             if (a >
                                                   c)
                                                 a =
                                                   c;
                                             if (a >
                                                   d)
                                                 a =
                                                   d;
                                             return a;
    }
    public static final int max(int a, int b, int c) {
        if (a <
              b)
            a =
              b;
        if (a <
              c)
            a =
              c;
        return a;
    }
    public static final float max(float a, float b,
                                  float c) { if (a <
                                                   b)
                                                 a =
                                                   b;
                                             if (a <
                                                   c)
                                                 a =
                                                   c;
                                             return a;
    }
    public static final double max(double a, double b,
                                   double c) { if (a <
                                                     b)
                                                   a =
                                                     b;
                                               if (a <
                                                     c)
                                                   a =
                                                     c;
                                               return a;
    }
    public static final float max(float a, float b,
                                  float c,
                                  float d) { if (a <
                                                   b)
                                                 a =
                                                   b;
                                             if (a <
                                                   c)
                                                 a =
                                                   c;
                                             if (a <
                                                   d)
                                                 a =
                                                   d;
                                             return a;
    }
    public static final float smoothStep(float a,
                                         float b,
                                         float x) {
        if (x <=
              a)
            return 0;
        if (x >=
              b)
            return 1;
        float t =
          clamp(
            (x -
               a) /
              (b -
                 a),
            0.0F,
            1.0F);
        return t *
          t *
          (3 -
             2 *
             t);
    }
    public static final float fastPow(float a, float b) {
        float x =
          Float.
          floatToRawIntBits(
            a);
        x *=
          1.0F /
            (1 <<
               23);
        x =
          x -
            127;
        float y =
          x -
          (int)
            Math.
            floor(
              x);
        b *=
          x +
            (y -
               y *
               y) *
            0.346607F;
        y =
          b -
            (int)
              Math.
              floor(
                b);
        y =
          (y -
             y *
             y) *
            0.33971F;
        return Float.
          intBitsToFloat(
            (int)
              ((b +
                  127 -
                  y) *
                 (1 <<
                    23)));
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
