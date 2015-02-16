package org.sunflow.core.light;
import org.sunflow.SunflowAPI;
import org.sunflow.core.LightSample;
import org.sunflow.core.LightSource;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public class PointLight implements LightSource {
    private Point3 lightPoint;
    private Color power;
    public PointLight() { super();
                          lightPoint = new Point3(0, 0, 0);
                          power = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { lightPoint =
                                                                pl.
                                                                  getPoint(
                                                                    "center",
                                                                    lightPoint);
                                                              power = pl.
                                                                        getColor(
                                                                          "power",
                                                                          power);
                                                              return true;
    }
    public int getNumSamples() { return 1; }
    public void getSamples(ShadingState state) { Vector3 d =
                                                   Point3.
                                                   sub(
                                                     lightPoint,
                                                     state.
                                                       getPoint(
                                                         ),
                                                     new Vector3(
                                                       ));
                                                 if (Vector3.
                                                       dot(
                                                         d,
                                                         state.
                                                           getNormal(
                                                             )) >
                                                       0 &&
                                                       Vector3.
                                                       dot(
                                                         d,
                                                         state.
                                                           getGeoNormal(
                                                             )) >
                                                       0) {
                                                     LightSample dest =
                                                       new LightSample(
                                                       );
                                                     dest.
                                                       setShadowRay(
                                                         new Ray(
                                                           state.
                                                             getPoint(
                                                               ),
                                                           lightPoint));
                                                     float scale =
                                                       1.0F /
                                                       (float)
                                                         (4 *
                                                            Math.
                                                              PI *
                                                            lightPoint.
                                                            distanceToSquared(
                                                              state.
                                                                getPoint(
                                                                  )));
                                                     dest.
                                                       setRadiance(
                                                         power,
                                                         power);
                                                     dest.
                                                       getDiffuseRadiance(
                                                         ).
                                                       mul(
                                                         scale);
                                                     dest.
                                                       getSpecularRadiance(
                                                         ).
                                                       mul(
                                                         scale);
                                                     dest.
                                                       traceShadow(
                                                         state);
                                                     state.
                                                       addSample(
                                                         dest);
                                                 } }
    public void getPhoton(double randX1, double randY1, double randX2,
                          double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power) { p.set(lightPoint);
                                         float phi = (float)
                                                       (2 *
                                                          Math.
                                                            PI *
                                                          randX1);
                                         float s = (float)
                                                     Math.
                                                     sqrt(
                                                       randY1 *
                                                         (1.0F -
                                                            randY1));
                                         dir.x = (float) Math.
                                                   cos(
                                                     phi) *
                                                   s;
                                         dir.y = (float) Math.
                                                   sin(
                                                     phi) *
                                                   s;
                                         dir.z = (float) (1 -
                                                            2 *
                                                            randY1);
                                         power.set(this.power);
    }
    public float getPower() { return power.getLuminance();
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169179364000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcRxWfO/8523F8ttM4bnAcx3UqkpRbAhTJuGpxjdM4" +
                                                    "XJNTLomEo9YZ787dbby7s92ds88Opm0QSqhQ+OeWBAUjlVSlJW0qRFQQqpQv" +
                                                    "0FblSxEC8YEW8YWKkg/5QKkoUN6b3b3d2zs7gQ9Y2rnZ2ffmvTfvvd9748vX" +
                                                    "SYvrkD02NxaLBhcZVhGZk8bdGbFoMzdzIHt3jjou0yYM6rpHYG1GHX4p/d4H" +
                                                    "3yh1J0nrNNlELYsLKnRuuYeZy415pmVJOlydNJjpCtKdPUnnqVIWuqFkdVeM" +
                                                    "ZcmGCKsgI9lABQVUUEAFRaqgjIdUwLSRWWVzAjmoJdxHyJdIIktabRXVE2RH" +
                                                    "7SY2dajpb5OTFsAObfh+DIySzBWHDFVt92yuM/jJPcrKdx7u/nETSU+TtG7l" +
                                                    "UR0VlBAgZJp0msycZY47rmlMmyY9FmNanjk6NfQlqfc06XX1okVF2WHVQ8LF" +
                                                    "ss0cKTM8uU4VbXPKquBO1byCzgwteGspGLQItvaFtnoW7sN1MLBDB8WcAlVZ" +
                                                    "wNI8p1uaINvjHFUbRz4PBMCaMpko8aqoZovCAun1fGdQq6jkhaNbRSBt4WWQ" +
                                                    "IsjWNTfFs7apOkeLbEaQ/jhdzvsEVO3yIJBFkM1xMrkTeGlrzEsR/1w/eM+5" +
                                                    "U9Z+Kyl11phqoP5twDQYYzrMCsxhlso8xs7d2ado3ytnk4QA8eYYsUfz8hdv" +
                                                    "fPauwWuveTQfaUBzaPYkU8WMemm2682BiV2jTahGm81dHZ1fY7kM/5z/Zaxi" +
                                                    "Q+b1VXfEj5ng47XDv/zCY8+zd5OkY4q0qtwomxBHPSo3bd1gzgPMYg4VTJsi" +
                                                    "7czSJuT3KZKCeVa3mLd6qFBwmZgizYZcauXyHY6oAFvgEaVgrlsFHsxtKkpy" +
                                                    "XrEJISl4SAaejcT7k7+CnFCOuhDuClWppVtcgeBl1FFLClP5zCycbsmkzpyr" +
                                                    "qGVXcFNxy1bB4AuK66gKd4rVd5U7TDH0YknAiUDIZnGawUiz/w8yKmhn90Ii" +
                                                    "AS4YiAOAAbmznxsac2bUlfL9kzdenHkjWU0I/4QAbUBUxheVQVEZKSoTiiKJ" +
                                                    "hJRwG4r0HAzumYNEBwjs3JV/6MCJs8NNEFn2QjOcLZIOg4W+HpMqnwjRYEpi" +
                                                    "ngoh2f/08TOZ95+9zwtJZW3obshNrp1fePzYox9PkmQtBqNdsNSB7DlEzipC" +
                                                    "jsRzr9G+6TPvvHflqWUeZmENqPvgUM+JyT0c94DDVaYBXIbb7x6iV2deWR5J" +
                                                    "kmZADEBJQSGqAYAG4zJqknwsAEy0pQUMLnDHpAZ+ClCuQ5QcvhCuyNDokvMe" +
                                                    "cMoGjPrN8PT4aSB/8esmG8fbvFBCL8eskIC872fXLlz97p7RZBS705FqmGfC" +
                                                    "Q4KeMEiOOIzB+h/O57795PUzx2WEAMUdjQSM4DgBuAAug2P9ymuP/P7tty79" +
                                                    "JhlGlYACWZ41dLUCe9wZSgHUMAC50PcjRy2Ta3pBp7MGw+D8Z3rn3qt/Pdft" +
                                                    "edOAlSAY7rr5BuH67feTx954+O+DcpuEilUrtDwk8w5gU7jzuOPQRdSj8viv" +
                                                    "t114lX4PQBWAzNWXmMQmIi0j8ugV6ardcszEvu3FYciu+1aRK/3yLQmid62d" +
                                                    "RPuw+EaS7x+HjNnTf3pfWlSXPg1qTox/Wrl8cevEve9K/jCOkXt7pR6LoFEJ" +
                                                    "eT/xvPm35HDrL5IkNU26Vb8LOkaNMkbLNFR+N2iNoFOq+V5bxb2SNVbN04F4" +
                                                    "DkXExjMoxECYIzXOO2JJ04mnPAhPl580XfGkSRA5GZUsw3LcicNHg5hN2Y4+" +
                                                    "T7HFIh0SVyWsSsLNgmyJYq8JdctD3U/KXPT8/alabfrhSfvapNfQZhyHMUFa" +
                                                    "bL7AnIaydBOaFox87niypOUjkXhKBHwDdfVBFoU8Lzsqw1zctlZPI/uxS6dX" +
                                                    "VrVDz+z1YL63tk+YhDb4hd/+61eZ8398vUFpahfc/pjB5pkRUQwb/W015eVB" +
                                                    "2e6FAfbEcz96Wby55zOeyN1rJ0Wc8dXTf9l65N7Sif+iqGyPGR/f8rkHL7/+" +
                                                    "wJ3qt5KkqRqndR1sLdNYbXR2OAxabutITYwOVqNiEwbB7fD0+VHR1xDYQ/eG" +
                                                    "EJP0gcN39GCdo6WpDBpkxLCArC9Klvd+x3NTUszRdUDsOA6HAcXLtgYJsT5c" +
                                                    "5RzdhA5y3m9xleXet+cuvvOC59E4NsWI2dmVJz7MnFtJRi4Nd9T17VEe7+Ig" +
                                                    "tdzoHeyH8JeA59/4oAm44DWOvRN+9zpUbV9tG/Ngx3pqSRH7/nxl+ec/XD6T" +
                                                    "9I/kAODDLOcGo1Y9uMuFg1U/y2Qf8J9gfmt+jnrh5Drf5IWjKMjGIhMHy2ae" +
                                                    "mrbBXEn6EA7U0wnqXxOA1E013hB03kO+xkO3rHGiFoK21UVmvkQ1uMHhHZbJ" +
                                                    "bcrr2LWIA/RYHWDXOkY1z3NduzWrFuFRfKuUW7YqJXdMNZLdqnHobWSCf7l2" +
                                                    "uC84hP66WnGMYQcqi8XnpNTT6xzCV3F4FDAVDiFX4oJbuHDqpvb2BPgy6ts7" +
                                                    "+j/F3dfX+fZNHL4mSBuqVi1ZsROC6s1pg6DDwhpeVbAB66/7T4h3e1dfXE23" +
                                                    "bVk9+jvZfFdv2O1wzS2UDSPaEETmrbbDCrrUs91rD7xaeSGGhOHNCXSVv1LV" +
                                                    "8x71RUG649QQcfgTJfu+IBsiZAAP/ixK9DTkHxDh9Ad2EB3dsuvEtijjtUUV" +
                                                    "EqmZ2HhH32q6cARf+T+moISVvf8yzahXVg8cPHXj08/IetiiGnRpCXdpy5KU" +
                                                    "dwGplsEda+4W7NW6f9cHXS+17wywrwuHXv/WEdNte+PmfNK0hWynl3665Sf3" +
                                                    "PLv6lrwd/AcW4jbg/BMAAA==");
}
