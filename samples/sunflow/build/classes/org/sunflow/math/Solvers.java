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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "7+evXH1n3bYLovg357ArAyc+D9eOu1VTrBC+yxG3qUCzRNB6G0TDBGcT6cD/" +
       "mjZyJ/78gXBwTVrVCZAYfkY9/9yW1D1XBX7YzDj29krtHAImCnE/9ZL5D2Wo" +
       "9RcKasugXs37/jmCjTLvKxkwgut/FME3UtV59fwuh9WxIIm3xiM0wjbeRqPp" +
       "3MyqErnbqSSQSNQvNSq+MjMh/AxiFVlJAO3ny/0yXQ8y1ATu5I9zTiXwliJJ" +
       "iPeNzOsrXB0o0rZFeIvyz+TQpdvJ4AsMDis1fufve2VQzNUXOCEEjleRaEaY" +
       "Dc7ESK1Dr9G4gFIfCLbt9XvtuOkw0R2XfnrLT/a+sPquaPYVVNvnpOBB1byV" +
       "b94G106vau68gaoZuuQLcTVbBEBLWB5iiyB7rIH+x/nCItWCenV57mN1GuCb" +
       "nxY1T/7YjXQChtocqi9AeRSKNWoF9XT6agOdvs6XxyDEhE6pck7XJmwa0W2q" +
       "zlwC8nifBdz/m2v+apCfx9qF1Z72W1bnfidrnf8J2wHfkYWyYUTzLvLc6lBS" +
       "0IVsHX4W8ts3GOqNf5ww1MxvQsDTEuwMQzdFwEBS7ykK9C3ISADij085fpL1" +
       "hgko60kFVU1TTny2Gq4qsuJvGX/QKMs/ZrLaxdWpB45f/8zzYmqBvMFLS5xK" +
       "O9RzOb4Hw8rgmtR8Wq0HRj/sfqVjh9/xqgb7qGz8mf4P+FT5AgQTAAA=");
}
