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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "/1/s4npnrhcAAA==");
}
