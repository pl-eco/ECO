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
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYb2wcRxWfO/8523F8ttM4bnAcx3UqkpRbAhTJuGpxjdM4" +
                                                    "XJNTLomEo9Yd787dbbK7s92ds88Opm0QSqhQgNYtCQpGKqlKS9pUiKggVClf" +
                                                    "oK3KlyIE4kP/qF9aUfIhHygVBcp7s7u3e3tnJyBhaedmZ9+b9968937vjS9e" +
                                                    "JS2uQ3bZ3FgoGlxkWEVkjhm3Z8SCzdzMvuztOeq4TJswqOsegrUZdfjF9Icf" +
                                                    "f6/UnSSt02QDtSwuqNC55R5kLjfmmJYl6XB10mCmK0h39hido0pZ6IaS1V0x" +
                                                    "liXrIqyCjGQDFRRQQQEVFKmCMh5SAdN6ZpXNCeSglnAfJN8giSxptVVUT5Bt" +
                                                    "tZvY1KGmv01OWgA7tOH7ETBKMlccMlS13bO5zuAndinLP7i/++dNJD1N0rqV" +
                                                    "R3VUUEKAkGnSaTJzljnuuKYxbZr0WIxpeebo1NAXpd7TpNfVixYVZYdVDwkX" +
                                                    "yzZzpMzw5DpVtM0pq4I7VfMKOjO04K2lYNAi2NoX2upZuAfXwcAOHRRzClRl" +
                                                    "AUvzcd3SBNka56jaOPJVIADWlMlEiVdFNVsUFkiv5zuDWkUlLxzdKgJpCy+D" +
                                                    "FEE2r7opnrVN1eO0yGYE6Y/T5bxPQNUuDwJZBNkYJ5M7gZc2x7wU8c/V/Xec" +
                                                    "OWHttZJSZ42pBurfBkyDMaaDrMAcZqnMY+zcmX2S9r18OkkIEG+MEXs0L339" +
                                                    "2pdvG7zyqkfzqQY0B2aPMVXMqBdmu94YmNgx2oRqtNnc1dH5NZbL8M/5X8Yq" +
                                                    "NmReX3VH/JgJPl45+NuvPfwc+yBJOqZIq8qNsglx1KNy09YN5tzDLOZQwbQp" +
                                                    "0s4sbUJ+nyIpmGd1i3mrBwoFl4kp0mzIpVYu3+GICrAFHlEK5rpV4MHcpqIk" +
                                                    "5xWbEJKCh2TgWU+8P/kryGGlxE2mUJVausUViF1GHbWkMJUrLjVtA7zmlq2C" +
                                                    "wecV11EV7hSr7yp3mGLoxZKAY4A4zeI0g+Fl/782rqBF3fOJBBz2QDzVDciS" +
                                                    "vdzQmDOjLpfvnrz2wszryWro+2cBuAKiMr6oDIrKSFGZUBRJJKSEm1Ck50pw" +
                                                    "xHFIaQC7zh35+/Y9cHq4CWLInm+GU0TSYbDL12NS5RNh3k9JdFMh+PqfOnoq" +
                                                    "89Ezd3nBp6wO0g25yZWz848ceeizSZKsRVu0C5Y6kD2HGFnFwpF4ljXaN33q" +
                                                    "/Q8vPbnEw3yrgW8fBuo5MY2H4x5wuMo0AMZw+51D9PLMy0sjSdIM2AB4KCjE" +
                                                    "L0DNYFxGTTqPBdCItrSAwQXumNTATwGedYiSw+fDFRkaXXLeA05Zh/G9EZ4e" +
                                                    "P+DlL37dYON4kxdK6OWYFRJ69/zqyrnLP9w1moyidDpS9/JMeDnfEwbJIYcx" +
                                                    "WH/zbO7xJ66eOiojBChuaSRgBMcJQABwGRzrt1598M9vv3XhD8kwqgSUwvKs" +
                                                    "oasV2OPWUArggwEYhb4fOWyZXNMLOp01GAbnP9Pbd1/+65luz5sGrATBcNv1" +
                                                    "NwjXb76bPPz6/X8flNskVKxPoeUhmXcAG8Kdxx2HLqAelUd+v+XcK/RHAJ8A" +
                                                    "Wa6+yCQKEWkZkUevSFftlGMm9m03DkN23beKXOmXb0kQvWP1JNqDZTaSfP84" +
                                                    "YMyefPcjaVFd+jSoLjH+aeXi+c0Td34g+cM4Ru6tlXosgpYk5P3cc+bfksOt" +
                                                    "v0mS1DTpVv1+5wg1yhgt01Dj3aAJgp6o5nttvfaK01g1TwfiORQRG8+gEANh" +
                                                    "jtQ474glTSee8iA8XX7SdMWTJkHkZFSyDMtxOw6fDmI2ZTv6HMVminRIXJWw" +
                                                    "Kgk3CrIpir0mVCgPdT8vc9Hz9xdqtemHJ+1rk15Fm3EcxgRpsfk8cxrK0k1o" +
                                                    "TzDyuePJkpaPROIpEfAN1NUHWRTyvOyoDHNxy2rdi+y8LpxcXtEOPL3bg/ne" +
                                                    "2o5gEhre5//4r99lzr7zWoPS1C64/RmDzTEjohi29Ftqysu9srELA+zRZ3/2" +
                                                    "knhj15c8kTtXT4o44ysn/7L50J2lB/6LorI1Znx8y2fvvfjaPbeqjyVJUzVO" +
                                                    "63rVWqax2ujscBg019ahmhgdrEbFBgyCm+Hp86OiryGwh+4NISbpA4fv6ME6" +
                                                    "R0tTGbTCiGEBWV+ULO/9juempJjDa4DYURwOAoqXbQ0SYm24yjm6Cb3inN/M" +
                                                    "Kku9bx8///7znkfj2BQjZqeXH/0kc2Y5Gbke3FLXoUd5vCuC1HK9d7CfwF8C" +
                                                    "nn/jgybggtci9k74fepQtVG1bcyDbWupJUXsee/S0q9/unQq6R/JPsCHWc4N" +
                                                    "Rq16cJcL+6t+lsk+4D/B/Mb8HPXCsTW+yatFUZD1RSb2l82815ZK0vtwoJ5O" +
                                                    "UP+aAKSuq/G6oMce8jUeumGNE7UQtKUuMvMlqsFdDW+rTG5TXsOuBRygx+oA" +
                                                    "u9YwqnmO69qNWbUAj+JbpdywVSm5Y6qR7FaNQ28jE/ybtcNdwSH019WKIww7" +
                                                    "UFksviKlnlzjEL6Nw0OAqXAIuRIX3MKFE9e1tyfAl1Hf3tH/Ke6+u8a37+Pw" +
                                                    "HUHaULVqyYqdEFRvThsEHRbW8KqCDVh/3f88vHu6+sJKum3TyuE/yea7epdu" +
                                                    "hwttoWwY0YYgMm+1HVbQpZ7tXnvg1cpzMSQMb06gq/yVqp71qM8L0h2nhojD" +
                                                    "nyjZjwVZFyEDePBnUaKnIP+ACKc/sYPo6JZdJ7ZFGa8tqpBIzcTGO/pW04Uj" +
                                                    "+Mr/JgUlrOz9P2lGvbSyb/+Ja198WtbDFtWgi4u4S1uWpLwLSLUMblt1t2Cv" +
                                                    "1r07Pu56sX17gH1dOPT6t46YblsbN+eTpi1kO734y02/uOOZlbfk7eA/RF/u" +
                                                    "N+YTAAA=");
}
