package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.LightSample;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class AnisotropicWardShader implements Shader {
    private Color rhoD;
    private Color rhoS;
    private float alphaX;
    private float alphaY;
    private int numRays;
    public AnisotropicWardShader() { super();
                                     rhoD = Color.GRAY;
                                     rhoS = Color.GRAY;
                                     alphaX = 1;
                                     alphaY = 1;
                                     numRays = 4; }
    public boolean update(ParameterList pl, SunflowAPI api) { rhoD = pl.getColor(
                                                                          "diffuse",
                                                                          rhoD);
                                                              rhoS =
                                                                pl.
                                                                  getColor(
                                                                    "specular",
                                                                    rhoS);
                                                              alphaX =
                                                                pl.
                                                                  getFloat(
                                                                    "roughnessX",
                                                                    alphaX);
                                                              alphaY =
                                                                pl.
                                                                  getFloat(
                                                                    "roughnessY",
                                                                    alphaY);
                                                              numRays =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    numRays);
                                                              return true;
    }
    protected Color getDiffuse(ShadingState state) {
        return rhoD;
    }
    private float brdf(Vector3 i, Vector3 o,
                       OrthoNormalBasis basis) {
        float fr =
          4 *
          (float)
            Math.
              PI *
          alphaX *
          alphaY;
        fr *=
          (float)
            Math.
            sqrt(
              basis.
                untransformZ(
                  i) *
                basis.
                untransformZ(
                  o));
        Vector3 h =
          Vector3.
          add(
            i,
            o,
            new Vector3(
              ));
        basis.
          untransform(
            h);
        float hx =
          h.
            x /
          alphaX;
        hx *=
          hx;
        float hy =
          h.
            y /
          alphaY;
        hy *=
          hy;
        float hn =
          h.
            z *
          h.
            z;
        fr =
          (float)
            Math.
            exp(
              -(hx +
                  hy) /
                hn) /
            fr;
        return fr;
    }
    public Color getRadiance(ShadingState state) {
        state.
          faceforward();
        OrthoNormalBasis onb =
          state.
          getBasis();
        state.
          initLightSamples();
        state.
          initCausticSamples();
        Color lr =
          Color.
          black();
        if (state.
              includeSpecular()) {
            Vector3 in =
              state.
              getRay().
              getDirection().
              negate(
                new Vector3(
                  ));
            for (LightSample sample
                  :
                  state) {
                float cosNL =
                  sample.
                  dot(
                    state.
                      getNormal());
                float fr =
                  this.
                  brdf(
                    in,
                    sample.
                      getShadowRay().
                      getDirection(),
                    onb);
                lr.
                  madd(
                    cosNL *
                      fr,
                    sample.
                      getSpecularRadiance());
            }
            if (numRays >
                  0) {
                int n =
                  state.
                  getDepth() ==
                  0
                  ? numRays
                  : 1;
                for (int i =
                       0;
                     i <
                       n;
                     i++) {
                    double r1 =
                      state.
                      getRandom(
                        i,
                        0,
                        n);
                    double r2 =
                      state.
                      getRandom(
                        i,
                        1,
                        n);
                    float alphaRatio =
                      alphaY /
                      alphaX;
                    float phi =
                      0;
                    if (r1 <
                          0.25) {
                        double val =
                          4 *
                          r1;
                        phi =
                          (float)
                            Math.
                            atan(
                              alphaRatio *
                                Math.
                                tan(
                                  Math.
                                    PI /
                                    2 *
                                    val));
                    }
                    else
                        if (r1 <
                              0.5) {
                            double val =
                              1 -
                              4 *
                              (0.5 -
                                 r1);
                            phi =
                              (float)
                                Math.
                                atan(
                                  alphaRatio *
                                    Math.
                                    tan(
                                      Math.
                                        PI /
                                        2 *
                                        val));
                            phi =
                              (float)
                                Math.
                                  PI -
                                phi;
                        }
                        else
                            if (r1 <
                                  0.75) {
                                double val =
                                  4 *
                                  (r1 -
                                     0.5);
                                phi =
                                  (float)
                                    Math.
                                    atan(
                                      alphaRatio *
                                        Math.
                                        tan(
                                          Math.
                                            PI /
                                            2 *
                                            val));
                                phi +=
                                  Math.
                                    PI;
                            }
                            else {
                                double val =
                                  1 -
                                  4 *
                                  (1 -
                                     r1);
                                phi =
                                  (float)
                                    Math.
                                    atan(
                                      alphaRatio *
                                        Math.
                                        tan(
                                          Math.
                                            PI /
                                            2 *
                                            val));
                                phi =
                                  2 *
                                    (float)
                                      Math.
                                        PI -
                                    phi;
                            }
                    float cosPhi =
                      (float)
                        Math.
                        cos(
                          phi);
                    float sinPhi =
                      (float)
                        Math.
                        sin(
                          phi);
                    float denom =
                      cosPhi *
                      cosPhi /
                      (alphaX *
                         alphaX) +
                      sinPhi *
                      sinPhi /
                      (alphaY *
                         alphaY);
                    float theta =
                      (float)
                        Math.
                        atan(
                          Math.
                            sqrt(
                              -Math.
                                log(
                                  1 -
                                    r2) /
                                denom));
                    float sinTheta =
                      (float)
                        Math.
                        sin(
                          theta);
                    float cosTheta =
                      (float)
                        Math.
                        cos(
                          theta);
                    Vector3 h =
                      new Vector3(
                      );
                    h.
                      x =
                      sinTheta *
                        cosPhi;
                    h.
                      y =
                      sinTheta *
                        sinPhi;
                    h.
                      z =
                      cosTheta;
                    onb.
                      transform(
                        h);
                    Vector3 o =
                      new Vector3(
                      );
                    float ih =
                      Vector3.
                      dot(
                        h,
                        in);
                    o.
                      x =
                      2 *
                        ih *
                        h.
                          x -
                        in.
                          x;
                    o.
                      y =
                      2 *
                        ih *
                        h.
                          y -
                        in.
                          y;
                    o.
                      z =
                      2 *
                        ih *
                        h.
                          z -
                        in.
                          z;
                    float no =
                      onb.
                      untransformZ(
                        o);
                    float ni =
                      onb.
                      untransformZ(
                        in);
                    float w =
                      ih *
                      cosTheta *
                      cosTheta *
                      cosTheta *
                      (float)
                        Math.
                        sqrt(
                          Math.
                            abs(
                              no /
                                ni));
                    Ray r =
                      new Ray(
                      state.
                        getPoint(),
                      o);
                    lr.
                      madd(
                        w /
                          n,
                        state.
                          traceGlossy(
                            r,
                            i));
                }
            }
            lr.
              mul(
                rhoS);
        }
        lr.
          add(
            state.
              diffuse(
                this.
                  getDiffuse(
                    state)));
        return lr;
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        state.
          faceforward();
        Color d =
          this.
          getDiffuse(
            state);
        state.
          storePhoton(
            state.
              getRay().
              getDirection(),
            power,
            d);
        float avgD =
          d.
          getAverage();
        float avgS =
          rhoS.
          getAverage();
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              avgD) {
            power.
              mul(
                d).
              mul(
                1.0F /
                  avgD);
            OrthoNormalBasis onb =
              state.
              getBasis();
            double u =
              2 *
              Math.
                PI *
              rnd /
              avgD;
            double v =
              state.
              getRandom(
                0,
                1,
                1);
            float s =
              (float)
                Math.
                sqrt(
                  v);
            float s1 =
              (float)
                Math.
                sqrt(
                  1.0F -
                    v);
            Vector3 w =
              new Vector3(
              (float)
                Math.
                cos(
                  u) *
                s,
              (float)
                Math.
                sin(
                  u) *
                s,
              s1);
            w =
              onb.
                transform(
                  w,
                  new Vector3(
                    ));
            state.
              traceDiffusePhoton(
                new Ray(
                  state.
                    getPoint(),
                  w),
                power);
        }
        else
            if (rnd <
                  avgD +
                  avgS) {
                power.
                  mul(
                    rhoS).
                  mul(
                    1 /
                      avgS);
                OrthoNormalBasis basis =
                  state.
                  getBasis();
                Vector3 in =
                  state.
                  getRay().
                  getDirection().
                  negate(
                    new Vector3(
                      ));
                double r1 =
                  rnd /
                  avgS;
                double r2 =
                  state.
                  getRandom(
                    0,
                    1,
                    1);
                float alphaRatio =
                  alphaY /
                  alphaX;
                float phi =
                  0;
                if (r1 <
                      0.25) {
                    double val =
                      4 *
                      r1;
                    phi =
                      (float)
                        Math.
                        atan(
                          alphaRatio *
                            Math.
                            tan(
                              Math.
                                PI /
                                2 *
                                val));
                }
                else
                    if (r1 <
                          0.5) {
                        double val =
                          1 -
                          4 *
                          (0.5 -
                             r1);
                        phi =
                          (float)
                            Math.
                            atan(
                              alphaRatio *
                                Math.
                                tan(
                                  Math.
                                    PI /
                                    2 *
                                    val));
                        phi =
                          (float)
                            Math.
                              PI -
                            phi;
                    }
                    else
                        if (r1 <
                              0.75) {
                            double val =
                              4 *
                              (r1 -
                                 0.5);
                            phi =
                              (float)
                                Math.
                                atan(
                                  alphaRatio *
                                    Math.
                                    tan(
                                      Math.
                                        PI /
                                        2 *
                                        val));
                            phi +=
                              Math.
                                PI;
                        }
                        else {
                            double val =
                              1 -
                              4 *
                              (1 -
                                 r1);
                            phi =
                              (float)
                                Math.
                                atan(
                                  alphaRatio *
                                    Math.
                                    tan(
                                      Math.
                                        PI /
                                        2 *
                                        val));
                            phi =
                              2 *
                                (float)
                                  Math.
                                    PI -
                                phi;
                        }
                float cosPhi =
                  (float)
                    Math.
                    cos(
                      phi);
                float sinPhi =
                  (float)
                    Math.
                    sin(
                      phi);
                float denom =
                  cosPhi *
                  cosPhi /
                  (alphaX *
                     alphaX) +
                  sinPhi *
                  sinPhi /
                  (alphaY *
                     alphaY);
                float theta =
                  (float)
                    Math.
                    atan(
                      Math.
                        sqrt(
                          -Math.
                            log(
                              1 -
                                r2) /
                            denom));
                float sinTheta =
                  (float)
                    Math.
                    sin(
                      theta);
                float cosTheta =
                  (float)
                    Math.
                    cos(
                      theta);
                Vector3 h =
                  new Vector3(
                  );
                h.
                  x =
                  sinTheta *
                    cosPhi;
                h.
                  y =
                  sinTheta *
                    sinPhi;
                h.
                  z =
                  cosTheta;
                basis.
                  transform(
                    h);
                Vector3 o =
                  new Vector3(
                  );
                float ih =
                  Vector3.
                  dot(
                    h,
                    in);
                o.
                  x =
                  2 *
                    ih *
                    h.
                      x -
                    in.
                      x;
                o.
                  y =
                  2 *
                    ih *
                    h.
                      y -
                    in.
                      y;
                o.
                  z =
                  2 *
                    ih *
                    h.
                      z -
                    in.
                      z;
                Ray r =
                  new Ray(
                  state.
                    getPoint(),
                  o);
                state.
                  traceReflectionPhoton(
                    r,
                    power);
            }
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1168806208000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVYe2wUxxkf39k+/CBnG7ANMTY2Jimv24SGpsVRwTEmMRz4" +
       "4gMDhsQZ787dLezt\nbnbnzmcH5aFKMU2UNiitlKgNQS0SkEeJlLY0UpoQJW" +
       "lJaNUkVVMpUmgrpLZSm0pVpZSq/aPfzOze\n3u09AOeknd3b+eZ7f7/5Zl/6" +
       "DNXZFuqS7QidMYkdGYrHsGUTZUjDtr0bXk3K79Y1xE7t0I0Aqomi\ngKpQFI" +
       "7KtqRgiiVVkUa2DuQstNY0tJmkZtAIydHIIW2jw297dGMJw73Hz7U9erK2J4" +
       "DqoiiMdd2g\nmKqGPqyRtE1RS/QQzmIpQ1VNiqo2HYiihUTPpIcM3aZYp/YD" +
       "6CEUjKJ6U2Y8KeqNusIlEC6Z2MJp\niYuXYlwscFhkEYpVnSiDeXGwcl3xSl" +
       "DbWTdWSg1MFrDJcTCHawBWr8hbLawtMdUMnh7/ypETZ4Io\nPIHCqh5nzGSw" +
       "hIK8CdScJukpYtmDikKUCdSqE6LEiaViTZ3lUidQm60mdUwzFrHHiG1oWUbY" +
       "ZmdM\nYnGZ7ssoapaZTVZGpoaV91FCJZri/qtLaDgJZrd7Zgtzt7H3YGCjCo" +
       "pZCSwTd0ntYVWHiPf4V+Rt\n7N8BBLA0lCY0ZeRF1eoYXqA2EUsN60kpTi1V" +
       "TwJpnZEBKRQtq8iU+drE8mGcJJMUdfrpYmIKqBq4\nI9gSipb4yTgniNIyX5" +
       "QK4rO2/fOjp7//xhae27UKkTWmfyMs6vYtGiMJYhFdJmLhlUzkOyP7M10B\n" +
       "hIB4iY9Y0AyuOrcn+tc3ewTNjWVoRqcOEZlOyruO9Yw9eJeBgkyNBaZhqyz4" +
       "RZbzcog5MwM5E6q2\nPc+RTUbcyfNjv9j/yAvkbwHUOILqZUPLpCGPWmUjba" +
       "oase4iOrEwJcoIaiC6MsTnR1AInqOQ8uLt\naCJhEzqCajX+qt7g/8FFCWDB" +
       "XNQAz6qeMNxnE9MUf86ZCKEQXOg2uBYi8eN3irZEJDujJzRjWrIt\nWTKsZP" +
       "6/bFhEslNYIZY0qKu2QS3DVOW92FLi/G2EZZJJ0UEpZaSJhGWsq7ohJVWoXd" +
       "lYr5Asu39B\n/jlmQ9t0TQ0DRX9xa1AXdxsa0E7Kpy6/f2R4xzePisRhye5Y" +
       "T9F6EBtxxEaY2IgQGykrFtXUcGmL\nmXgRSAjDYShogL7m1fF7t99/tC8IGW" +
       "RO14IPGWkf2OnoNCwbQ17Vj3CAlCH1On9wYC5y5dRmkXpS\nZXAuu7rpg5cv" +
       "nvhX85oACpRHTmYrYHcjYxNjcJtHxH5/rZXj/4/Hd7768cVPv+RVHUX9JWBQ" +
       "upIV\nc58/KpYhEwXg0WN/cmk4uBeNHwugWkAIQEWuPwBOt19GUVEPuADJbA" +
       "lFUVPCsNJYY1MuqjXSlGVM\ne294urTw50UQnCaW5SvgWuykPb+z2SUmG9tF" +
       "erFo+6zgAHzlG/W3/P71pne5W1ysDhfshnFCReW3\nesmy2yIE3n/6TOzp73" +
       "42d4BnipMqFLbIzJSmyjlYcpO3BEpeA9hhgezfo6cNRU2oeEojLOP+F151\n" +
       "60/+/q0WERoN3riRXXd1Bt77pXeiRy7e9+9uzqZGZluOZ4ZHJqxZ5HEetCw8" +
       "w/TIPfrR8md/iZ8D\nRAQUstVZwoEFccsQ96PE/b6GjxHf3K1s6APe6yqkfp" +
       "kNflI+8kKyL/PAe69xrZtwYadQGIad2BwQ\nkWfDSubdDn/13o3tFNDddn7X" +
       "wRbt/H+B4wRwlGFjtUctKPtcURAd6rrQJ2+93X7/h0EU2IYaNQMr\n2zDPf9" +
       "QAiUfsFIBPzty8hedWy/QCNnKTEXfCMscBuYJ/rLtbXbn8t7H2wKucyal1p6" +
       "Pvjz7HHVCx\n8Mvsjj4+s2/sOX7l1/QS5+NVIFvdmytFVmipvLVf/TjbWv/K" +
       "8+kACk2gFtlp+saxlmF5PgE9iu12\ngtAYFs0X9xticx3II0yXv/oLxPpr30" +
       "N0eGbU7LnZV+7NzNsdcN3glPsN/nKvQfxhM1/Sz8eb88UZ\nMi01i1kjiGqt" +
       "lLGVT3dQ1FG4f6hpaHRYwRmWwA42bmDDFhHmjRXTYVOpomFH0XAFRUfYMCj0" +
       "iV+n\nPtuvU5+l7rR7L6NPzNGnHmtmCu+DXO4sPKlYaho6nixH78uP9f38wp" +
       "7n58SOVyXli1ZNyg//4Y+H\ng99+a0qs8+e1j/hY98k/v3p5bLFAR9Earyzp" +
       "TgvXiPaYJ07YZEjRW00Cp35nbe9LD41dcjRqK27y\nhuEg9JeZt8nNdzz5pz" +
       "K9BySxgakvMvfMIzKtTmRaK0TmvqLI7Gf/9vrETl6n2E642hyxbRXEyo7Y\n" +
       "ELhhDM/YnEPcFJL2URSEA4xPDaWKGjwuNxXAZU3ZpOdNnGjXWAiXVzpP8PDN" +
       "7ftn82P4nXsDzla0\nnwJ6G+Z6jWSJ5kPm5UWd3E5+gvKQ8PEzL56jH67dJB" +
       "JhTeWU9i9cs+nEbM+ms0/Mo3/r8dnmZ92a\nvfGeYEq9EOCHPAGsJYfD4kUD" +
       "xXDaCPpkLH13EaiuKO6hBuDqclKhq2wP5QXOawAC3K8BN4TdJSHk\nphI4e7" +
       "IOwyVrLySLi/tgbISLmanSYjzMhixUQMZUAMX9mRiaMgyNYN1LxOmr1YO7f/" +
       "M/Vt4jfFfZ\nANcqxyOrynqk7B7TYFoGhV2QOFXgmVJTnO/Ly+Y7nNfZFwvC" +
       "JTxZxRnH2DBHUWOS0K1qIpGxydW2\nD88xR+fpGI4aW+Fa7ThmdcVUGfTpHu" +
       "Qcg66OnYU6puEoGxknrPH68jUT9JYQjFpQBLv4EeJObKs2\nV+Z7VXz4QzY8" +
       "A5vvlKUkOKB6Lnp2ni7i1XQLXLc7Lrr9mqvp+hPkxSrGnWXDaYqaIEHGYB0D" +
       "hmvP\nkDNfxPyvwzXnmD83XzCpbv7VLOFiXqvinjfZ8FOKFtoypgBQsZRBHZ" +
       "wuwJTarKEqnlfOXatXoMNc\nUvbjAzt9dZZ8wxTf3eToJw8e/Dz6u//wY3T+" +
       "21hTFC1IZDStsEEueK43LZJQuU1Nol02+e29cluq\n+C4CECoeuM4XBP2vKG" +
       "rx04MD2K2Q7DeQUQVkALvOUyHRR9AXABF7/K3phqqFn77YQSEiDgq5Ipcx\n" +
       "z6ws2nH5Z2V3V8yID8uT8r6XD6zIPbH7Kb7V1skanp1lbBqjKCQ+H+R31t6K" +
       "3FxeH6BXzo6//qOv\nuZ0DP14uLkD3oizfIGarpADs5uXP7MNpk/JT9uzPOn" +
       "58x6njlwL8q8H/ATkUPTINGAAA");
}
