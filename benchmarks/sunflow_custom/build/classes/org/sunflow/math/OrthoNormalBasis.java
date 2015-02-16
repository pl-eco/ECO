package org.sunflow.math;
public final class OrthoNormalBasis {
    private Vector3 u;
    private Vector3 v;
    private Vector3 w;
    private OrthoNormalBasis() { super();
                                 u = new Vector3();
                                 v = new Vector3();
                                 w = new Vector3(); }
    public void flipU() { u.negate(); }
    public void flipV() { v.negate(); }
    public void flipW() { w.negate(); }
    public void swapUV() { Vector3 t = u;
                           u = v;
                           v = t; }
    public void swapVW() { Vector3 t = v;
                           v = w;
                           w = t; }
    public void swapWU() { Vector3 t = w;
                           w = u;
                           u = t; }
    public Vector3 transform(Vector3 a, Vector3 dest) { dest.x = a.x * u.
                                                                         x +
                                                                   a.
                                                                     y *
                                                                   v.
                                                                     x +
                                                                   a.
                                                                     z *
                                                                   w.
                                                                     x;
                                                        dest.y = a.x * u.
                                                                         y +
                                                                   a.
                                                                     y *
                                                                   v.
                                                                     y +
                                                                   a.
                                                                     z *
                                                                   w.
                                                                     y;
                                                        dest.z = a.x * u.
                                                                         z +
                                                                   a.
                                                                     y *
                                                                   v.
                                                                     z +
                                                                   a.
                                                                     z *
                                                                   w.
                                                                     z;
                                                        return dest; }
    public Vector3 transform(Vector3 a) { float x = a.x * u.x + a.y * v.x +
                                            a.
                                              z *
                                            w.
                                              x;
                                          float y = a.x * u.y + a.y * v.y +
                                            a.
                                              z *
                                            w.
                                              y;
                                          float z = a.x * u.z + a.y * v.z +
                                            a.
                                              z *
                                            w.
                                              z;
                                          return a.set(x, y, z); }
    public Vector3 untransform(Vector3 a, Vector3 dest) { dest.x = Vector3.
                                                                     dot(
                                                                       a,
                                                                       u);
                                                          dest.y = Vector3.
                                                                     dot(
                                                                       a,
                                                                       v);
                                                          dest.z = Vector3.
                                                                     dot(
                                                                       a,
                                                                       w);
                                                          return dest; }
    public Vector3 untransform(Vector3 a) { float x = Vector3.dot(a, u);
                                            float y = Vector3.dot(a, v);
                                            float z = Vector3.dot(a, w);
                                            return a.set(x, y, z); }
    public float untransformX(Vector3 a) { return Vector3.dot(a, u); }
    public float untransformY(Vector3 a) { return Vector3.dot(a, v); }
    public float untransformZ(Vector3 a) { return Vector3.dot(a, w); }
    public static final OrthoNormalBasis makeFromW(Vector3 w) { OrthoNormalBasis onb =
                                                                  new OrthoNormalBasis(
                                                                  );
                                                                w.
                                                                  normalize(
                                                                    onb.
                                                                      w);
                                                                if (Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          x) <
                                                                      Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          y) &&
                                                                      Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          x) <
                                                                      Math.
                                                                      abs(
                                                                        onb.
                                                                          w.
                                                                          z)) {
                                                                    onb.
                                                                      v.
                                                                      x =
                                                                      0;
                                                                    onb.
                                                                      v.
                                                                      y =
                                                                      onb.
                                                                        w.
                                                                        z;
                                                                    onb.
                                                                      v.
                                                                      z =
                                                                      -onb.
                                                                         w.
                                                                         y;
                                                                }
                                                                else
                                                                    if (Math.
                                                                          abs(
                                                                            onb.
                                                                              w.
                                                                              y) <
                                                                          Math.
                                                                          abs(
                                                                            onb.
                                                                              w.
                                                                              z)) {
                                                                        onb.
                                                                          v.
                                                                          x =
                                                                          onb.
                                                                            w.
                                                                            z;
                                                                        onb.
                                                                          v.
                                                                          y =
                                                                          0;
                                                                        onb.
                                                                          v.
                                                                          z =
                                                                          -onb.
                                                                             w.
                                                                             x;
                                                                    }
                                                                    else {
                                                                        onb.
                                                                          v.
                                                                          x =
                                                                          onb.
                                                                            w.
                                                                            y;
                                                                        onb.
                                                                          v.
                                                                          y =
                                                                          -onb.
                                                                             w.
                                                                             x;
                                                                        onb.
                                                                          v.
                                                                          z =
                                                                          0;
                                                                    }
                                                                Vector3.
                                                                  cross(
                                                                    onb.
                                                                      v.
                                                                      normalize(
                                                                        ),
                                                                    onb.
                                                                      w,
                                                                    onb.
                                                                      u);
                                                                return onb;
    }
    public static final OrthoNormalBasis makeFromWV(Vector3 w,
                                                    Vector3 v) {
        OrthoNormalBasis onb =
          new OrthoNormalBasis(
          );
        w.
          normalize(
            onb.
              w);
        Vector3.
          cross(
            v,
            onb.
              w,
            onb.
              u).
          normalize(
            );
        Vector3.
          cross(
            onb.
              w,
            onb.
              u,
            onb.
              v);
        return onb;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1159026718000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1YfWwUxxWfO38bgw/z5VIwYExUm/S2Tpoq1AgKlg12L7bl" +
       "LxqjYsZ7c/bi/crunH04dUuQIhBSUJSalESJVUWgJBQCioKSKkKyVKUhSlQ1" +
       "bdUvqVBFahs15Q/+aBo1bdP3Zvdu7/bOW/si9aR5Nzszb97vzXvz5s1cvkPK" +
       "bIvsNA31+Lhq8ChL8egx9YEoP24yO9ode6CPWjaLt6vUtgehbVRuvFb78adP" +
       "TkTCpHyErKG6bnDKFUO3+5ltqFMsHiO1XmuHyjSbk0jsGJ2iUpIrqhRTbN4W" +
       "IyuyWDlpiqUhSABBAgiSgCDt80YB00qmJ7V25KA6tx8h3yWhGCk3ZYTHybbc" +
       "SUxqUc2dpk9oADNU4vcwKCWYUxbZmtHd0TlP4XM7pbkfHIm8WkJqR0itog8g" +
       "HBlAcBAyQmo0po0xy94Xj7P4CFmtMxYfYJZCVWVG4B4hdbYyrlOetFhmkbAx" +
       "aTJLyPRWrkZG3aykzA0ro15CYWo8/VWWUOk46Lre09XRsBPbQcFqBYBZCSqz" +
       "NEvppKLHOdni58jo2PRNGACsFRrjE0ZGVKlOoYHUObZTqT4uDXBL0cdhaJmR" +
       "BCmcbFx0Ulxrk8qTdJyNclLvH9fndMGoKrEQyMLJOv8wMRNYaaPPSln2udOz" +
       "++yj+kE9LDDHmawi/kpgavAx9bMEs5guM4expiX2NF1/43SYEBi8zjfYGfP6" +
       "d+5+496GhZvOmC8WGNM7dozJfFS+MLbq/U3tzbtKEEaladgKGj9Hc+H+fW5P" +
       "W8qEnbc+MyN2RtOdC/0/ffjEJfZRmFR3kXLZUJMa+NFq2dBMRWXWAaYzi3IW" +
       "7yJVTI+3i/4uUgH1mKIzp7U3kbAZ7yKlqmgqN8Q3LFECpsAlqoC6oieMdN2k" +
       "fELUUyYhZCUUUgelhDg/8c/JUWnIBneXqEx1RTckcF5GLXlCYrIxOgarO6FR" +
       "a9KW5KTNDU2yk3pCNaYl25IlwxrPfGsgTOq1wN96DEuj6n5qK3YUPc38P8hI" +
       "oZ6R6VAITLDJHwBU2DsHDTXOrFF5Lrm/4+4ro++GMxvCXSGINiAq6oqKoqio" +
       "XxQJhYSEtSjSMTCYZxI2OoTAmuaBb3cfPd0Iy5oyp0thbXFoI2jo4uiQjXYv" +
       "GnSJmCeDS9a/cPhU9JMX9zouKS0eugtyk4Xz048Nf+8rYRLOjcGoFzRVI3sf" +
       "Rs5MhGzy771C89ae+vDjq0/PGt4uzAnqbnDI58TN3ei3gGXILA7h0pu+ZSu9" +
       "PnpjtilMSiFiQJTkFLwaAlCDX0bOJm9LB0zUpQwUTgjrYFc6ylXzCcuY9lqE" +
       "a6wS9dVglBr0+k1Qyt1tIP6xd42JdK3jSmhlnxYiIHf+eOGZ68/u3BXOjt21" +
       "WafhAONOJFjtOcmgxRi0/+F83/fP3Tl1WHgIjNheSEAT0naIC2AyWNbHbz7y" +
       "u9u3Lvwq7HkVJxWmpUxBuEjBJPd4YiBsqBC60PhNQ7pmxJWEQsdUht75r9od" +
       "rdf/djbimFOFlrQ33Pu/J/Dav7CfnHj3yD8axDQhGY8tT3VvmLMCa7yZ91kW" +
       "PY44Uo/9YvMzb9PnIapCJLOVGSaCExGqEbH2krBVi6BRX18rkq1mXl9KtNSL" +
       "rxIQ3bz4LurE0zdr9/2zVx07+cEnQqO8/VPg0PHxj0iXn9vYvucjwe85MnJv" +
       "SeUHI8hUPN77Lml/DzeWvxUmFSMkIrtp0DBVk+guI3D02+ncCFKlnP7cY9w5" +
       "s9oyG3WTfxNlifVvIS8IQh1HY716sV1T6u6aUv+uCRFR2YWkiZNQUvCug/My" +
       "L6wOMwwY94t95pjyq8sXtCctaAoruz/fZPvSk01nTeb4VKOgO5B8SbhXmEOC" +
       "mhxTFfD+soSiUzXb+Qjuyc2LJTciMbtwcm4+3nux1Yn3dbkJQwfkw1d+/e/3" +
       "ouf/+E6BM6qKG+aXVTbF1CyZtSgy55x5SOR9nqOdeflHr/P3d37dEdmy+Obw" +
       "M7598q8bB/dMHF3G6bLFp7x/ypcfuvzOgXvkp8KkJOOvealsLlNbrpdWWwxy" +
       "b30wx1cbMlZfgWbYkDZ/+j8/whe0bgirnamAEHQooO9hJP3oF6piDgXHoT5L" +
       "0SA3nHKTV2m27vbkcx9ecUzkDzq+wez03JnPomfnwlnXge15GXk2j3MlECBX" +
       "Oiv1GfxCUP6DBTXABiclrGt389KtmcTUNNGxtwXBEiI6/3J19s2XZk+F3RXp" +
       "5qR0ylDi+SFbNPTkWy3iWi1S0GpIBgMsMBHQdwwJc60zjB9Hlg5rrQtrbVGw" +
       "zIA+cVZoLqxDy4BV75Z0ffmwgvx8BkkSgp09Tc2h5SwXYmlwcTUUhetEQN9J" +
       "JLMuruHlrleTi6upKFynA/rOIHncxXVoaGm41mAjnlAtLq6WJeMKO6dR5sDa" +
       "LYY+GYDwKSRP4CFiUd3GzFnwLR1kqwuydckgQ15QdfCdD8D3LJJzReJDR3vQ" +
       "xffg51zEHwaAfAHJ85ysSOrFwtzrwtxb/DK+FIDwEpKLxSDEQjZD6XQRdhaP" +
       "8FoAwleRXOGkJgvht8TIw0hGHVxUhESD8qXj7nZxdxeP+80A3DeQvJGLWxz5" +
       "ry0dYo8Lsad4iD8JgPgWkoVciCNLg9iMjfdB6Xch9heEWDhvKsFqJ0Y/8ZqK" +
       "Xwf8p0ueIj8LUOTnSG5CMNDoJOu0DE1E+sjStLgfSsLVIrFsLZC8V1CDwuHi" +
       "twFq/B7JLzmpzqgxvIgeKU4i/gcnvEXX571nO2+w8ivztZUb5od+I55QMu+k" +
       "VTFSmUiqavatLqteblosoQhoVc4dzxR/t0G6/6IGqRv+CaS3nGEfQFjJGsZJ" +
       "hVvLHvQnTkpgEFb/bKavgRHxGIC31ahzW02RnGuT6b9Ebc9JncXbf/pGkXRe" +
       "/0flq/PdPY/e/dpFcT0pk1U6I7KWyhipcB6GMreSbYvOlp6r/GDzp6uuVe1I" +
       "Z66rkNRleUsWti2F30w6NJOLV46ZNza8tvvF+Vvi1ea/Eelku5QZAAA=");
}
