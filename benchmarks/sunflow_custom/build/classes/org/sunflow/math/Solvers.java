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
      1159026718000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
