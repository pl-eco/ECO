package org.sunflow.math;
final public class Solvers {
    final public static double[] solveQuadric(double a, double b, double c) {
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
                                                    double z = Solvers.solveCubicForQuartic(
                                                                         -0.5 *
                                                                           p,
                                                                         -r,
                                                                         0.5 *
                                                                           r *
                                                                           p -
                                                                           0.125 *
                                                                           q *
                                                                           q);
                                                    double d1 =
                                                      2.0 *
                                                      z -
                                                      p;
                                                    if (d1 <
                                                          0) {
                                                        if (d1 >
                                                              1.0E-10)
                                                            d1 =
                                                              0;
                                                        else
                                                            return null;
                                                    }
                                                    double d2;
                                                    if (d1 <
                                                          1.0E-10) {
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
                                                    double q1 =
                                                      d1 *
                                                      d1;
                                                    double q2 =
                                                      -0.25 *
                                                      c1;
                                                    double pm =
                                                      q1 -
                                                      4 *
                                                      (z -
                                                         d2);
                                                    double pp =
                                                      q1 -
                                                      4 *
                                                      (z +
                                                         d2);
                                                    if (pm >=
                                                          0 &&
                                                          pp >=
                                                          0) {
                                                        pm =
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
    final private static double solveCubicForQuartic(double p,
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1159026718000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAK1Ye2wUxxkf3/ltg40BYyDBYN7Y3sMJpoCRiGsMGJ/BOfMI" +
       "JsQZ786dF+/tbmbn\n7MOheSgKkNC0QUmlVmopqZAINGkipRWtlKagJG1a/k" +
       "kitZEiJW2F1FZqUzWqlFK1f/Sbb+6xt2c7\nNMpJN7c78z3me/3mm3vpE1Lm" +
       "cXKH7mnihMs8rWdokHKPGT0W9bwDMDWiv11WNXip33ZCpCRKQqYh\nSF1U9y" +
       "IGFTRiGpG+nV1pTlpdxzqRsByhsbTQjludGXl7o51FAg+fv9rw+MXS5hApi5" +
       "I6atuOoMJ0\n7F6LJT1B6qPH6QSNpIRpRaKmJ7qiZA6zU8kex/YEtYX3EHmE" +
       "hKOk3NWlTEFWRLPKI6A84lJOkxFU\nHxlEtSBhPmeCmjYzunPqgLOtkBO2ne" +
       "GLFVODkEq5eAjMwR2A1ctzVitri0x1wy8e2nzywuUwqRsm\ndaY9JIXpYIkA" +
       "fcOkNsmSo4x73YbBjGEyz2bMGGLcpJY5hVqHSYNnJmwqUpx5MeY51oQkbPBS" +
       "LuOo\nMzsZJbW6tImndOHwnI/iJrOM7FtZ3KIJMLsxb7Yyd5ecBwOrTdgYj1" +
       "OdZVlKx00bIt4c5MjZuKof\nCIC1IsnEmJNTVWpTmCANKpYWtRORIcFNOwGk" +
       "ZU4KtAiyZEah0tcu1cdpgo0I0hSkG1RLQFWFjpAs\ngiwMkqEkiNKSQJR88W" +
       "lt/OzMi9994x7M7VKD6ZbcfzUwLQswxViccWbrTDHeSmnP9x1J3REiBIgX\n" +
       "BogVTffqqwejf/lFs6JZOg3N/tHjTBcj+r5zzbGHdzskLLdR6TqeKYNfYDmW" +
       "w2BmpSvtQtU25iTK\nRS27eC32yyOPXWF/DZHqPlKuO1YqCXk0T3eSrmkxvp" +
       "vZjFPBjD5SxWyjB9f7SAU8RyHl1ez+eNxj\noo+UWjhV7uA7uCgOIqSLquDZ" +
       "tONO9tmlYgyf0y4hZA58SQN8w0R98FeQVi3ipey45UxGPK5HHJ7I\nvSdBQG" +
       "RIpjL3NJk0riC7I2NOkkWoTm3TdiIJE8pUd9oNNiF/b19UWu6sYbKkREJdsG" +
       "QtyPY9jmUw\nPqJfuvmbk739T51R6SBTOGMThAI0aBkNmtSgZTSQkhIUvEBq" +
       "UpEAP45DRQJ21a4fOrb3wTMtYH/a\nnSwFJ0jSFth9Rn2v7vTky7YPEU6H3G" +
       "n6wdHT2q1LO1TuRGZG12m5a959+caFf9ZuCJHQ9NAnzQLw\nrZZiBiVe5iBt" +
       "VbBYppP/96cHXvvdjY/W5ctGkFVF1VzMKauxJRgA7ujMAHzLi7+4uC58mBw6" +
       "FyKl\nUOIAa7h/QIxlQR0FVdmVRThpS0WU1MQdnqSWXMrCUrUY485kfgYzo1" +
       "4OC1SSyEAGNojgeOuJ8o0f\nvF7zNlqcxdE630k1xISqynn5PDjAGYP5j749" +
       "+Ny3Pjl9FJMgkwUCjq/UqGXqaWBZk2eBcrQAEmSM\nVh20k45hxk06ajGZTP" +
       "+tW93xk799o1553YKZbNDaPl9Afn7xV8ljNx741zIUU6LL4yBvRp5MWTM/\n" +
       "L7mbc3pC7iP9+Pt3fudX9HuAVoAQnjnFsOgJWkbQjxq6dz2O7YG1jXJoAdlt" +
       "M2T1NIfviH7ySqIl\n9dCvf4a7rqH+U9wfhgHqdqmgou75oHQpyQwFYCRXF7" +
       "pybJQhWBSs3j3UGwNhm67tu7/euvYfUDsM\nanU4Gb39HMAiXRDpDHVZxYfX" +
       "32x88L0wCe0i1ZZDjV0U859UQeIxbwxwJu3uuAe3UT9ZKUf0C8Hd\nLsl4KV" +
       "3whi8rcVyjsickIAVNm+JhtS5AzcmdM513eFafvu/T2lP0rWMKWRoKz5Be6L" +
       "P+fOJNtnb7\nM3+cBgSrhOO2W2yCWT6dYamyANEGsBXI1/PTl394VbzXuk2p" +
       "3DAzmAUZN2y7MNW87ZWzXwDHmgNO\nCIqeN7H03vCY+U4IuxUFYUVdTiFTl9" +
       "8doBT2k+K2dKycqcW0W55LuyYZjn74zs2k3dxg2inAKYpu\nWD5vx9ACSnjY" +
       "MaYD9RTOeb7J37pzMwktwASi4c1TLT9/5+D3Tyunr5+lP/dzjeiP/v4P4+Fv" +
       "Xh9V\nfME2KEB8btnFP712M7ZA5YrqFVcWtWt+HtUvou11rszWFbNpQOq3Wl" +
       "e89EjsY9yR5NsBbjEcQE/0\newwHXBiYBXUOy2G3ILXYLt+bogY3dYn2Pv8h" +
       "wkm1l5/dOT+29egTmHdV0I1Tb18+8HAHkk8l4J7V\nM7s1J2xEX3vs6j+uv8" +
       "HWIohUmh5cIbp5YprO1MfzKb3CBj6In8czsHSUeirJgi19ccde0Iij6XNd\n" +
       "/Im5rgs9WA2a07Hx7rs6OsH+BrBfXuU009Cijk6tvp0vXK95/1xq816VAXN8" +
       "BH07T766t7byhbOn\nsGwyjqjy9ayZ94oJyvflkUP+HBNk8Evq57Z1bGq7a1" +
       "P7VzqhI/CHc52maevXesvzhYle6JDDHlVC\nnZ8LvvjSkytj2cWSI/Bdkynj" +
       "NbdZxiGsYDn0B6u3DAnK8tkbGFDk+CzJnJRD3JfMHDACKY/MFOvN\nuPyAHN" +
       "wvMRJ3b2nr2NK+ZasvEnIvs0Ui8QUjsVhOxvAeoT7i/wJUQSpcbk7AzWfamI" +
       "TzpMFIfG2W\nSDwqh0lBFqD1PalRU9/l8IwXUETe8PTtGp6GvWa8LHuwpqJ/" +
       "GdTNWI9++PD9n0V/+2+FEdnbaw2U\nYzxlWb6jyn9slbucxU3cfI3qlFTKnB" +
       "KkPnjHEaRU/uDmnlRkT0FW+chgp5knP9HXBQkDkXx8xsWF\nRSAd2yX5V4Cm" +
       "7r2FjYu0dGUBnOIfOdnjO6X+yhnR73v56PL02QPPIjaX6RadmpJiqgF0VL+f" +
       "awFW\nzCgtK+td8uorh17/0dbsyVJwEyhKxQ61OksQoe2YvhPvTboCe+epny" +
       "768fZL5z8O4V3gf9uP8Dd/\nEwAA");
}
