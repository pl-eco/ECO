package org.sunflow.math;
public final class Solvers {
    public static final double[] solveQuadric(double a, double b, double c) {
        double disc =
          b *
          b -
          4 *
          a *
          c;
        if (disc <
              0)
            return null;
        disc =
          Math.
            sqrt(
              disc);
        double q =
          b <
          0
          ? -0.5 *
          (b -
             disc)
          : -0.5 *
          (b +
             disc);
        double t0 =
          q /
          a;
        double t1 =
          c /
          q;
        return t0 >
          t1
          ? (new double[] { t1,
             t0 })
          : (new double[] { t0,
             t1 });
    }
    public static double[] solveQuartic(double a, double b, double c, double d,
                                        double e) { double inva = 1 / a;
                                                    double c1 = b * inva;
                                                    double c2 = c * inva;
                                                    double c3 = d * inva;
                                                    double c4 = e * inva;
                                                    double c12 = c1 * c1;
                                                    double p = -0.375 * c12 +
                                                      c2;
                                                    double q = 0.125 * c12 *
                                                      c1 -
                                                      0.5 *
                                                      c1 *
                                                      c2 +
                                                      c3;
                                                    double r = -0.01171875 *
                                                      c12 *
                                                      c12 +
                                                      0.0625 *
                                                      c12 *
                                                      c2 -
                                                      0.25 *
                                                      c1 *
                                                      c3 +
                                                      c4;
                                                    double z = solveCubicForQuartic(
                                                                 -0.5 *
                                                                   p,
                                                                 -r,
                                                                 0.5 *
                                                                   r *
                                                                   p -
                                                                   0.125 *
                                                                   q *
                                                                   q);
                                                    double d1 = 2.0 *
                                                      z -
                                                      p;
                                                    if (d1 < 0) {
                                                        if (d1 >
                                                              1.0E-10)
                                                            d1 =
                                                              0;
                                                        else
                                                            return null;
                                                    }
                                                    double d2;
                                                    if (d1 < 1.0E-10) {
                                                        d2 =
                                                          z *
                                                            z -
                                                            r;
                                                        if (d2 <
                                                              0)
                                                            return null;
                                                        d2 =
                                                          Math.
                                                            sqrt(
                                                              d2);
                                                    }
                                                    else {
                                                        d1 =
                                                          Math.
                                                            sqrt(
                                                              d1);
                                                        d2 =
                                                          0.5 *
                                                            q /
                                                            d1;
                                                    }
                                                    double q1 = d1 *
                                                      d1;
                                                    double q2 = -0.25 *
                                                      c1;
                                                    double pm = q1 -
                                                      4 *
                                                      (z -
                                                         d2);
                                                    double pp = q1 -
                                                      4 *
                                                      (z +
                                                         d2);
                                                    if (pm >= 0 &&
                                                          pp >=
                                                          0) { pm =
                                                                 Math.
                                                                   sqrt(
                                                                     pm);
                                                               pp =
                                                                 Math.
                                                                   sqrt(
                                                                     pp);
                                                               double[] results =
                                                                 new double[4];
                                                               results[0] =
                                                                 -0.5 *
                                                                   (d1 +
                                                                      pm) +
                                                                   q2;
                                                               results[1] =
                                                                 -0.5 *
                                                                   (d1 -
                                                                      pm) +
                                                                   q2;
                                                               results[2] =
                                                                 0.5 *
                                                                   (d1 +
                                                                      pp) +
                                                                   q2;
                                                               results[3] =
                                                                 0.5 *
                                                                   (d1 -
                                                                      pp) +
                                                                   q2;
                                                               for (int i =
                                                                      1;
                                                                    i <
                                                                      4;
                                                                    i++) {
                                                                   for (int j =
                                                                          i;
                                                                        j >
                                                                          0 &&
                                                                          results[j -
                                                                                    1] >
                                                                          results[j];
                                                                        j--) {
                                                                       double t =
                                                                         results[j];
                                                                       results[j] =
                                                                         results[j -
                                                                                   1];
                                                                       results[j -
                                                                                 1] =
                                                                         t;
                                                                   }
                                                               }
                                                               return results;
                                                    }
                                                    else
                                                        if (pm >=
                                                              0) {
                                                            pm =
                                                              Math.
                                                                sqrt(
                                                                  pm);
                                                            double[] results =
                                                              new double[2];
                                                            results[0] =
                                                              -0.5 *
                                                                (d1 +
                                                                   pm) +
                                                                q2;
                                                            results[1] =
                                                              -0.5 *
                                                                (d1 -
                                                                   pm) +
                                                                q2;
                                                            return results;
                                                        }
                                                        else
                                                            if (pp >=
                                                                  0) {
                                                                pp =
                                                                  Math.
                                                                    sqrt(
                                                                      pp);
                                                                double[] results =
                                                                  new double[2];
                                                                results[0] =
                                                                  0.5 *
                                                                    (d1 -
                                                                       pp) +
                                                                    q2;
                                                                results[1] =
                                                                  0.5 *
                                                                    (d1 +
                                                                       pp) +
                                                                    q2;
                                                                return results;
                                                            }
                                                    return null;
    }
    private static final double solveCubicForQuartic(double p,
                                                     double q,
                                                     double r) {
        double A2 =
          p *
          p;
        double Q =
          (A2 -
             3.0 *
             q) /
          9.0;
        double R =
          (p *
             (A2 -
                4.5 *
                q) +
             13.5 *
             r) /
          27.0;
        double Q3 =
          Q *
          Q *
          Q;
        double R2 =
          R *
          R;
        double d =
          Q3 -
          R2;
        double an =
          p /
          3.0;
        if (d >=
              0) {
            d =
              R /
                Math.
                sqrt(
                  Q3);
            double theta =
              Math.
              acos(
                d) /
              3.0;
            double sQ =
              -2.0 *
              Math.
              sqrt(
                Q);
            return sQ *
              Math.
              cos(
                theta) -
              an;
        }
        else {
            double sQ =
              Math.
              pow(
                Math.
                  sqrt(
                    R2 -
                      Q3) +
                  Math.
                  abs(
                    R),
                1.0 /
                  3.0);
            if (R <
                  0)
                return sQ +
                  Q /
                  sQ -
                  an;
            else
                return -(sQ +
                           Q /
                           sQ) -
                  an;
        }
    }
    public Solvers() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "w85X2raVO17FYB+VDZ/5/wA3P7HB3hIAAA==");
}
