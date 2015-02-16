package org.sunflow.core.light;
import org.sunflow.SunflowAPI;
import org.sunflow.core.LightSample;
import org.sunflow.core.LightSource;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
public class DirectionalSpotlight implements LightSource {
    private Point3 src;
    private Vector3 dir;
    private OrthoNormalBasis basis;
    private float r;
    private float r2;
    private Color radiance;
    public DirectionalSpotlight() { super();
                                    src = new Point3(0, 0, 0);
                                    dir = new Vector3(0, 0, -1);
                                    dir.normalize();
                                    basis = OrthoNormalBasis.makeFromW(dir);
                                    r = 1;
                                    r2 = r * r;
                                    radiance = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { src = pl.getPoint(
                                                                         "source",
                                                                         src);
                                                              dir =
                                                                pl.
                                                                  getVector(
                                                                    "dir",
                                                                    dir);
                                                              dir.
                                                                normalize(
                                                                  );
                                                              r =
                                                                pl.
                                                                  getFloat(
                                                                    "radius",
                                                                    r);
                                                              basis =
                                                                OrthoNormalBasis.
                                                                  makeFromW(
                                                                    dir);
                                                              r2 =
                                                                r *
                                                                  r;
                                                              radiance =
                                                                pl.
                                                                  getColor(
                                                                    "radiance",
                                                                    radiance);
                                                              return true;
    }
    public int getNumSamples() { return 1; }
    public int getLowSamples() { return 1; }
    public void getSamples(ShadingState state) { if (Vector3.
                                                       dot(
                                                         dir,
                                                         state.
                                                           getGeoNormal(
                                                             )) <
                                                       0 &&
                                                       Vector3.
                                                       dot(
                                                         dir,
                                                         state.
                                                           getNormal(
                                                             )) <
                                                       0) {
                                                     float x =
                                                       state.
                                                         getPoint(
                                                           ).
                                                         x -
                                                       src.
                                                         x;
                                                     float y =
                                                       state.
                                                         getPoint(
                                                           ).
                                                         y -
                                                       src.
                                                         y;
                                                     float z =
                                                       state.
                                                         getPoint(
                                                           ).
                                                         z -
                                                       src.
                                                         z;
                                                     float t =
                                                       x *
                                                       dir.
                                                         x +
                                                       y *
                                                       dir.
                                                         y +
                                                       z *
                                                       dir.
                                                         z;
                                                     if (t >=
                                                           0.0) {
                                                         x -=
                                                           t *
                                                             dir.
                                                               x;
                                                         y -=
                                                           t *
                                                             dir.
                                                               y;
                                                         z -=
                                                           t *
                                                             dir.
                                                               z;
                                                         if (x *
                                                               x +
                                                               y *
                                                               y +
                                                               z *
                                                               z <=
                                                               r2) {
                                                             Point3 p =
                                                               new Point3(
                                                               );
                                                             p.
                                                               x =
                                                               src.
                                                                 x +
                                                                 x;
                                                             p.
                                                               y =
                                                               src.
                                                                 y +
                                                                 y;
                                                             p.
                                                               z =
                                                               src.
                                                                 z +
                                                                 z;
                                                             LightSample dest =
                                                               new LightSample(
                                                               );
                                                             dest.
                                                               setShadowRay(
                                                                 new Ray(
                                                                   state.
                                                                     getPoint(
                                                                       ),
                                                                   p));
                                                             dest.
                                                               setRadiance(
                                                                 radiance,
                                                                 radiance);
                                                             dest.
                                                               traceShadow(
                                                                 state);
                                                             state.
                                                               addSample(
                                                                 dest);
                                                         }
                                                     }
                                                 } }
    public void getPhoton(double randX1, double randY1, double randX2,
                          double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power) { float phi = (float)
                                                       (2 *
                                                          Math.
                                                            PI *
                                                          randX1);
                                         float s = (float)
                                                     Math.
                                                     sqrt(
                                                       1.0F -
                                                         randY1);
                                         dir.x = r * (float)
                                                       Math.
                                                       cos(
                                                         phi) *
                                                   s;
                                         dir.y = r * (float)
                                                       Math.
                                                       sin(
                                                         phi) *
                                                   s;
                                         dir.z = 0;
                                         basis.transform(
                                                 dir);
                                         Point3.add(src, dir,
                                                    p);
                                         dir.set(this.dir);
                                         power.set(radiance).
                                           mul(
                                             (float)
                                               Math.
                                                 PI *
                                               r2); }
    public float getPower() { return radiance.copy().mul(
                                                       (float)
                                                         Math.
                                                           PI *
                                                         r2).
                                getLuminance(
                                  ); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169184962000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YX2wcxRmfO/93HN/Zzh8THDtxHNTY9LZJS9XUCOoYmzgc" +
                                                    "tsk5UeqoMXO7c3cb7+0ss3P2xdSFREKJIjVCrUkDAj+gREAaEoQaAaqQ8tIC" +
                                                    "oi9UVas+FKq+FJXmIQ+lqLSl38zu3t7t/YlLpZ60c7Mz33x/Zr7v932zV26i" +
                                                    "BpuhIYsaJ9IG5TGS57Hjxj0xfsIiduxA/J5pzGyijRrYtmdgbE7tfy3y6edP" +
                                                    "Z6Jh1DiLurBpUo65Tk37ILGpsUC0OIr4o2MGydocRePH8QJWclw3lLhu8+E4" +
                                                    "Wle0lKOBuKeCAioooIIiVVBGfCpYtJ6YueyoWIFNbj+GfoBCcdRoqUI9jraX" +
                                                    "MrEww1mXzbS0ADg0i/fDYJRcnGdoW8F2x+Yyg58ZUlZ+ciz6eh2KzKKIbiaE" +
                                                    "OioowUHILGrLkmySMHtE04g2izpMQrQEYTo29CWp9yzqtPW0iXmOkcImicGc" +
                                                    "RZiU6e9cmypsYzmVU1YwL6UTQ/PeGlIGToOtm3xbHQvHxTgY2KqDYiyFVeIt" +
                                                    "qZ/XTY2jvuCKgo0DDwEBLG3KEp6hBVH1JoYB1OmcnYHNtJLgTDfTQNpAcyCF" +
                                                    "oy1VmYq9trA6j9NkjqPuIN20MwVULXIjxBKONgbJJCc4pS2BUyo6n5uT9557" +
                                                    "3NxvhqXOGlENoX8zLOoNLDpIUoQRUyXOwrbB+Hm86e0zYYSAeGOA2KF54/u3" +
                                                    "vnN37413HZo7K9BMJY8Tlc+pF5PtH/SM7tpbJ9Rotqiti8MvsVy6/7Q7M5y3" +
                                                    "IPI2FTiKyZg3eePgL7/75GXySRi1TqBGlRq5LPhRh0qzlm4Q9iAxCcOcaBOo" +
                                                    "hZjaqJyfQE3Qj+smcUanUimb8AlUb8ihRirfYYtSwEJsURP0dTNFvb6FeUb2" +
                                                    "8xZCqAke9C142pHzk/8cHVcO2eDuClaxqZtUAeclmKkZhah0Lgm7m8liNm8r" +
                                                    "as7mNKvYOTNl0EXFZqpCWbrwrlJGFENPZ7jygM5gB8FqbCQsyuVgTPic9X+V" +
                                                    "lhe2RxdDITiWniAoGBBP+6mhETanruT2jd26Ovd+uBAk7q5xNARCY67QmBAa" +
                                                    "c/hXEopCISlrgxDuHD8c3jzAAABk267E9w48eqa/DvzOWqyHnRek/WC1q9GY" +
                                                    "Skd9rJiQiKiCw3a/ePR07LOX7nccVqkO7BVXoxsXFk8efuJrYRQuRWhhIQy1" +
                                                    "iuXTAlcL+DkQjMxKfCOnP/702vll6sdoCeS70FG+UoR+f/AsGFWJBmDqsx/c" +
                                                    "hq/Pvb08EEb1gCeAoRyDzwM89QZllEDAsAenwpYGMDhFWRYbYsrDwFaeYXTR" +
                                                    "H5FO0i77HXAo60RM9MGz0Q0S+S9muyzRbnCcSpxywAoJ1+Nv3Xj2+nNDe8PF" +
                                                    "yB4pypUJwh2c6PCdZIYRAuN/uDD942dunj4qPQQodlQSMCDaUUANODLY1qfe" +
                                                    "fez3H3148Tdh36s4pM9c0tDVPPC4y5cCmGI4LmsPHDKzVNNTOk4aRDjnPyM7" +
                                                    "d1//67moc5oGjHjOcPftGfjjd+xDT75/7O+9kk1IFTnNt9wnczagy+c8whg+" +
                                                    "IfTIn/z11mffwS8A5ALM2foSkciFpGVIbr0ij2pQtrHA3G7RbLPK5vJypFu+" +
                                                    "NYLoXdWDaFyk5qLg+8eUkTz1p8+kRWXhUyEjBdbPKlee3zJ63ydyve/HYnVf" +
                                                    "vhyVoIzx1+65nP1buL/xF2HUNIuiqlsjHcZGTnjLLNQFtlc4QR1VMl+a452E" +
                                                    "NlyI055gDBWJDUaQj4bQF9Si3xoImjaxy5vhibhBEwkGTQjJzl65pF+2O0Xz" +
                                                    "Fc9nmyymL2BRgKE6wHpJsZGjzcXwm4V0BgkVyqGvyyB0DvobpWp0wxN11YhW" +
                                                    "UWNENMMgSdOZJ6m7TNJhIoCrhqgd8HS4ojqqiBp3RTUksa3bnrDtZcKmGBRq" +
                                                    "kxKt9gnK2gZ2ulI7q0h9yJUaYrW9fZrpWShPFtz6SVnu/Gj++Y9fdVJN0LUD" +
                                                    "xOTMytkvYudWwkUV6Y6yorB4jVOVSt9Z79j0BfxC8PxbPMIWMeBUJZ2jbmm0" +
                                                    "rVAbWZaAtO211JIixv98bfnnLy+fdszoLC3IxuC+8epv//Wr2IU/vlch34Pz" +
                                                    "U8z/t90/4u5+mO0RvUeqc7sTni6XW1cVbkddbs0Ma7oI0YqxoWeh9hYQTZkj" +
                                                    "Tm7zQBHwhbx1PWUlTVxULwmaYyoRO7y1Wmkud/fiqZVVberS7rALuoc4auHU" +
                                                    "+qpBFogRANqtJeXNw/Iy4gPc2Vd++gb/YOjbzjkNVnfT4MJ3Tv1ly8x9mUf/" +
                                                    "i6KmL2BTkOUrD19578G71B+FUV0BJ8vuV6WLhkvRsZURuBCaMyUY2Vs4b3m8" +
                                                    "d7jFhVdklBcW/qn5KS4s9zPsnV9v2flJUwlc30QO9cg2FZMlnP+R6QkpxqyR" +
                                                    "RKXzz0MVkbM0iDpJMymag04anQGwTlJqEGyWZ1o5kC4YLfNADzyDrtGDaza6" +
                                                    "WKXlGnNPiGaJo/Vpwidz2QTOWgaxK2ldB4lj7RrHXI1jX0rjMzXmzormKUfj" +
                                                    "OF10NRaDJ2+r3jpPpT2uenvWrF6oFAW2lnlRIgMAY6bF1xAi2Txdw4gV0fyQ" +
                                                    "o1Ywosae1y9QXVubVSfgibtWxddsVZPk2FRJdqNGoQ6Wwbha2twvmgdEc0xy" +
                                                    "fqGGoS+K5jnAODB0OkM5NcXA+dva1OHF+xHXpiNfypFerjF3WTSXIDMI1eii" +
                                                    "U1Y+UqH85WhDpfuqqMK7yz6WOR941KurkebNq4d+J29ghY8wLXHUnMoZRnFV" +
                                                    "WNRvtBhJ6VK5FqdGdPLQtQAc+RdpyLjyXyp91aF+naNokBpcSfwVk13naF0R" +
                                                    "GcCS2ysmehPiHohE9y3L8/2ovHqI2jjm1MZ5VJS4xO2r+K3kKiZyk/wM6eWR" +
                                                    "nPMhck69tnpg8vFb37wkk1KDauClJcGlOY6anFtoIRdtr8rN49W4f9fn7a+1" +
                                                    "7PRybLtoOt2rZ0C3vso3tLGsxeWdaunNzT+796XVD+UV8T/SUJKhHxYAAA==");
}
