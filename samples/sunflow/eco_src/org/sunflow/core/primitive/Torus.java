package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Instance;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.MathUtils;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Solvers;
import org.sunflow.math.Vector3;
public class Torus implements PrimitiveList {
    private float ri2;
    private float ro2;
    private float ri;
    private float ro;
    public Torus() { super();
                     ri = 0.25F;
                     ro = 1;
                     ri2 = ri * ri;
                     ro2 = ro * ro; }
    public boolean update(ParameterList pl, SunflowAPI api) { ri = pl.getFloat(
                                                                        "radiusInner",
                                                                        ri);
                                                              ro =
                                                                pl.
                                                                  getFloat(
                                                                    "radiusOuter",
                                                                    ro);
                                                              ri2 =
                                                                ri *
                                                                  ri;
                                                              ro2 =
                                                                ro *
                                                                  ro;
                                                              return true;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       -ro -
                                                         ri,
                                                       -ro -
                                                         ri,
                                                       -ri);
                                                     bounds.
                                                       include(
                                                         ro +
                                                           ri,
                                                         ro +
                                                           ri,
                                                         ri);
                                                     if (o2w !=
                                                           null)
                                                         bounds =
                                                           o2w.
                                                             transform(
                                                               bounds);
                                                     return bounds;
    }
    public float getPrimitiveBound(int primID, int i) { switch (i) {
                                                            case 0:
                                                            case 2:
                                                                return -ro -
                                                                  ri;
                                                            case 1:
                                                            case 3:
                                                                return ro +
                                                                  ri;
                                                            case 4:
                                                                return -ri;
                                                            case 5:
                                                                return ri;
                                                            default:
                                                                return 0;
                                                        }
    }
    public int getNumPrimitives() { return 1; }
    public void prepareShadingState(ShadingState state) {
        state.
          init();
        state.
          getRay().
          getPoint(
            state.
              getPoint());
        Instance parent =
          state.
          getInstance();
        Point3 p =
          parent.
          transformWorldToObject(
            state.
              getPoint());
        float deriv =
          p.
            x *
          p.
            x +
          p.
            y *
          p.
            y +
          p.
            z *
          p.
            z -
          ri2 -
          ro2;
        state.
          getNormal().
          set(
            p.
              x *
              deriv,
            p.
              y *
              deriv,
            p.
              z *
              deriv +
              2 *
              ro2 *
              p.
                z);
        state.
          getNormal().
          normalize();
        double phi =
          Math.
          asin(
            MathUtils.
              clamp(
                p.
                  z /
                  ri,
                -1,
                1));
        double theta =
          Math.
          atan2(
            p.
              y,
            p.
              x);
        if (theta <
              0)
            theta +=
              2 *
                Math.
                  PI;
        state.
          getUV().
          x =
          (float)
            (theta /
               (2 *
                  Math.
                    PI));
        state.
          getUV().
          y =
          (float)
            ((phi +
                Math.
                  PI /
                2) /
               Math.
                 PI);
        state.
          setShader(
            parent.
              getShader(
                0));
        state.
          setModifier(
            parent.
              getModifier(
                0));
        Vector3 worldNormal =
          parent.
          transformNormalObjectToWorld(
            state.
              getNormal());
        state.
          getNormal().
          set(
            worldNormal);
        state.
          getNormal().
          normalize();
        state.
          getGeoNormal().
          set(
            state.
              getNormal());
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                state.
                  getNormal()));
    }
    public void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        float rd2x =
          r.
            dx *
          r.
            dx;
        float rd2y =
          r.
            dy *
          r.
            dy;
        float rd2z =
          r.
            dz *
          r.
            dz;
        float ro2x =
          r.
            ox *
          r.
            ox;
        float ro2y =
          r.
            oy *
          r.
            oy;
        float ro2z =
          r.
            oz *
          r.
            oz;
        double alpha =
          rd2x +
          rd2y +
          rd2z;
        double beta =
          2 *
          (r.
             ox *
             r.
               dx +
             r.
               oy *
             r.
               dy +
             r.
               oz *
             r.
               dz);
        double gamma =
          ro2x +
          ro2y +
          ro2z -
          ri2 -
          ro2;
        double A =
          alpha *
          alpha;
        double B =
          2 *
          alpha *
          beta;
        double C =
          beta *
          beta +
          2 *
          alpha *
          gamma +
          4 *
          ro2 *
          rd2z;
        double D =
          2 *
          beta *
          gamma +
          8 *
          ro2 *
          r.
            oz *
          r.
            dz;
        double E =
          gamma *
          gamma +
          4 *
          ro2 *
          ro2z -
          4 *
          ro2 *
          ri2;
        double[] t =
          Solvers.
          solveQuartic(
            A,
            B,
            C,
            D,
            E);
        if (t !=
              null) {
            if (t[0] >=
                  r.
                  getMax() ||
                  t[t.
                      length -
                      1] <=
                  r.
                  getMin())
                return;
            for (int i =
                   0;
                 i <
                   t.
                     length;
                 i++) {
                if (t[i] >
                      r.
                      getMin()) {
                    r.
                      setMax(
                        (float)
                          t[i]);
                    state.
                      setIntersection(
                        0,
                        0,
                        0);
                    return;
                }
            }
        }
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1169362904000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVYe2wcRxkf3/kRP8DPOM7Ljh03pXF6R0PDI66UOMZpL7nE" +
                                                   "rl9pnRZnvDt33nhv\nZ7s7a5+dKLSN1KQpASKeFTQNEMlJaUlEQSlSG1LaQk" +
                                                   "kEahAtUqUGUCRAgiIhpBIEf/DN7O7t3t6j\nTixO2tm9ne8xv+813+zz76My" +
                                                   "00CrJDPCZnViRnqHBrBhErlXxaY5DK/GpTfKKgfmd2o0hEriKKTI\nDNXGJT" +
                                                   "MqY4ajihyNfbY7baAunaqzSZWyCEmzyH51kyNvR3xTjsA9Jy80PHa6tC2Eyu" +
                                                   "KoFmsaZZgp\nVOtTScpkqC6+H0/jqMUUNRpXTNYdRx8hmpXqpZrJsMbMR9Ah" +
                                                   "FI6jcl3iMhlqj7vKo6A8qmMDp6JC\nfXRAqAUJjQZhWNGI3JNRB5wbsjlh2Q" +
                                                   "7fYC41CFnCJ0cBjlgBoF6TQW2jzYGqh8+MfvLgqbNhVDuG\nahVtiAuTAAkD" +
                                                   "fWOoJkVSE8Qwe2SZyGOoXiNEHiKGglVlTmgdQw2mktQwswxiDhKTqtOcsMG0" +
                                                   "dGII\nne7LOKqROCbDkhg1MjZKKESV3X9lCRUnAXazB9uGu52/B4BVCizMSG" +
                                                   "CJuCylU4oGHm8LcmQwdu4E\nAmCtSBE2STOqSjUML1CD7UsVa8noEDMULQmk" +
                                                   "ZdQCLQytKCiU21rH0hROknGGWoJ0A/YUUFUKQ3AW\nhpYGyYQk8NKKgJd8/u" +
                                                   "lq/uDomW9f3Cpiu1QmksrXXwVMrQGmQZIgBtEkYjPesCJfjT1orQohBMRL\n" +
                                                   "A8Q2Tc9tF0bif/lpm02zMg9N/8R+IrFxafeJtsED91IU5stYolNT4c7PQi7S" +
                                                   "YcCZ6U7rkLXNGYl8\nMuJOXhr8+YOPPkf+GkJVMVQuUdVKQRzVSzSlKyox7i" +
                                                   "UaMTAjcgxVEk3uFfMxVAHPcQh5+21/ImES\nFkOlqnhVTsV/MFECRHATVcKz" +
                                                   "oiWo+6xjNime0zpCqAIu1AVXA7J/4s7QJyJR09ISKp2JmoYUpUYy\n81+iBo" +
                                                   "nqhpICDNMkOkwNy4zw4NEZ6o9O0hSJYglrikajSQXSVaJ3ymSa329eZJqvtG" +
                                                   "GmpISXvmAK\nqxD991FVJsa4NH/98sG+nU8etcODh7SDkaE1oCniaIpwTZGM" +
                                                   "pojQhEpKhIImrtH2ENh3CjIValrN\nHUMP79h3tCMMoaHPlIJxOGkHoHGW0S" +
                                                   "fRXi+dY6LySRBTLd/deyRyY36LHVPRwlU3L3f1Wy9cOfXP\nmvUhFMpfEjk8" +
                                                   "KMpVXMwAr6OZUtcZTKJ88v9+bNeL71x572NeOjHUmZPluZw8SzuCjjCoRGSo" +
                                                   "e574\n08trw3vQ6IkQKoXUh3In1g+VpDWoIytbu93Kx7FUxFF1ghoprPIpt1" +
                                                   "xVsUmDznhvRITUiedGcE41\nD986uJqdeBZ3PrtU52OzHVHc2wEUorLeOFz+" +
                                                   "8d+9XP2GMItbhGt929wQYXZK13vBMmwQAu/f++bA\nV772/pG9IlKcUGGw91" +
                                                   "kTqiKlgWWdxwK5rEI94Y7sHNFSVFYSCp5QCY+4/9bedteP//bFOts1Krxx\n" +
                                                   "PbvhwwV475dvQ49e+dy/WoWYEonvJR4Mj8xG0+hJ7jEMPMvXkX7sN6uf/gV+" +
                                                   "BkodlBdTmSOiYiCB\nDAk7RoXd14sxEpi7iw8dIHtDgdDPs3OPSwefS3ZYj/" +
                                                   "zyJ2LV1djfAvjdsAvr3bbn+bCWW3dZMHvv\nw+Yk0N19afdDdeql/4DEMZAo" +
                                                   "wY5p9htQNNJZTnSoyyreffW15n1Xwyi0HVWpFMvbsYh/VAmBR8xJ\nqDdpfc" +
                                                   "tWEVt1M0ucaENpJIywwjFA2vevFBZ3R+H03873fS9zxic2nIlf7n9GGKBg4u" +
                                                   "fZ9gJy5i6O\nnLzxK3ZNyPEykHO3p3OLKfRKHu+n35muLz//bCqEKsZQneR0" +
                                                   "c6NYtXicj0HzYbotHnR8WfPZjYS9\na3ZnKsyqYPb71AZz3yvi8Myp+XNNIN" +
                                                   "1ruLVXwtXopHtjMN1LkHjYIlg6xXh7JjkrYCeYxrzDQ2FD\n2QiOavH31+42" +
                                                   "wavN9Sc6Xnlz5Nkjdjkv4s8srnHp87//w1T4S69O2HxBpwWIT7Se/tOL1web" +
                                                   "7NS3\nG7q1OT2Vn8du6oRVanWeBu3FNAjq17vanz80eM1ZUUN2a9IH7fufZ1" +
                                                   "8jt99z/I959lLwEMXMLqF8\n3MiHrXa0byqYFZtv3l8DfOjhbqFCRyyg8/6b" +
                                                   "1LkcriZHZ1MBnSOOzpCh5FM5+n9QOZZRSfOp3FtE\npfD5Ol+dKRHPy2CPzW" +
                                                   "l4MkEg6jpEyepCjbaIkCMP/KPmCfz6wyGnlO9kUP2ofqdKponq01jBJWV1\n" +
                                                   "QrvE0cKrJMfOfv8Cu9q12Y619YWzJsi4fvOpubbN5566hf6nLYAtKLp+euX9" +
                                                   "4UnlzZA4/diFKefU\nlM3UnV2OqmA9lqENZxWlNdk9SDdcrY73W/P2IJ7/vA" +
                                                   "00JOwaKuJJDpXAoYx70iVr9pMN2feegZhQ\nkyqyRYuX+6FHsXQZqqD426fb" +
                                                   "MbcDquMEpSrBmhePUx+WAu7+J/4ksi2yEa51jkXWLdgiJdmx3eKH\nmoKjTG" +
                                                   "QXhtNq+m4h4VARsI/z4QBDH00StocaqryNWppsuoJX5QgW83AO3kbTngUOLs" +
                                                   "YCm+CKOBaI\n3FJM+LwTVjRRiI8Lzi8UQX6CD08yVA/IM5VAoBM1xwN3bDHg" +
                                                   "2uHa4oDbsmBw/nU+XWTuW3z4OkN1\ngGG3lcrAEMTHPQjfWAyET8HV60Dovd" +
                                                   "UIXZ2Ts0OTmMcR/6JEhJjvFQE6z4dTDDXqBtGxQfzMwRAo\nnaaK7GH/zmKw" +
                                                   "74Zrn4N934Kxh4XEsIu9KQf7IJ71PAQUHTkUMf4hy7QPI56JfljERC/x4Rx0" +
                                                   "morL\nmgkHPnPWs8j5xViEqz3sWOTwLQX0pSJzP+PDK+BnCOhtGFq9pBfTC9" +
                                                   "zIPZwXF4oTGt0y8c2DH/pa\ncr6J2t/xpPi7Bx76IP72v8XpPfOtrTqOliQs" +
                                                   "VfX35b7ncgjYhCKwVdtdui5uVxhaUfgLDHQWelYu\nX7a5fg2pHuSCgOc3P9" +
                                                   "lVhqp9ZLBnOU9+ot9CsQQi/vi27tq2Thz9+CklYp9S0lmG4vZZm9WuiI/V\n" +
                                                   "bkth2Z+rx6UHXti7Jv3U8JdFn1ImqXhujoupiqMK+9tFpi1pLyjNlfUWOn9u" +
                                                   "9OUffMZtu8TZtsn5\nYJETrRvt2SKOh1Yo/weDvpTOxBF/7qVlP7pn/uS1kP" +
                                                   "hk8T+VQePkYxgAAA==");
}
