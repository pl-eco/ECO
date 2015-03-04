package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Instance;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Solvers;
import org.sunflow.math.Vector3;
public class BanchoffSurface implements PrimitiveList {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       1.5F);
                                                     if (o2w != null) bounds =
                                                                        o2w.
                                                                          transform(
                                                                            bounds);
                                                     return bounds;
    }
    public float getPrimitiveBound(int primID, int i) { return (i &
                                                                  1) ==
                                                          0
                                                          ? -1.5F
                                                          : 1.5F;
    }
    public int getNumPrimitives() { return 1; }
    public void prepareShadingState(ShadingState state) { state.init();
                                                          state.getRay().
                                                            getPoint(
                                                              state.
                                                                getPoint());
                                                          Instance parent =
                                                            state.
                                                            getInstance();
                                                          Point3 n =
                                                            parent.
                                                            transformWorldToObject(
                                                              state.
                                                                getPoint());
                                                          state.
                                                            getNormal().
                                                            set(
                                                              n.
                                                                x *
                                                                (2 *
                                                                   n.
                                                                     x *
                                                                   n.
                                                                     x -
                                                                   1),
                                                              n.
                                                                y *
                                                                (2 *
                                                                   n.
                                                                     y *
                                                                   n.
                                                                     y -
                                                                   1),
                                                              n.
                                                                z *
                                                                (2 *
                                                                   n.
                                                                     z *
                                                                   n.
                                                                     z -
                                                                   1));
                                                          state.
                                                            getNormal().
                                                            normalize();
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
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
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
        double A =
          rd2y *
          rd2y +
          rd2z *
          rd2z +
          rd2x *
          rd2x;
        double B =
          4 *
          (r.
             oy *
             rd2y *
             r.
               dy +
             r.
               oz *
             r.
               dz *
             rd2z +
             r.
               ox *
             r.
               dx *
             rd2x);
        double C =
          -rd2x -
          rd2y -
          rd2z +
          6 *
          (ro2y *
             rd2y +
             ro2z *
             rd2z +
             ro2x *
             rd2x);
        double D =
          2 *
          (2 *
             ro2z *
             r.
               oz *
             r.
               dz -
             r.
               oz *
             r.
               dz +
             2 *
             ro2x *
             r.
               ox *
             r.
               dx +
             2 *
             ro2y *
             r.
               oy *
             r.
               dy -
             r.
               ox *
             r.
               dx -
             r.
               oy *
             r.
               dy);
        double E =
          3.0F /
          8.0F +
          (-ro2z +
             ro2z *
             ro2z -
             ro2y +
             ro2y *
             ro2y -
             ro2x +
             ro2x *
             ro2x);
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
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    public BanchoffSurface() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169362746000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVYe2wcxRkf3/kRP1o/kjgmxHHsJJS8bklFqBpTEds45Mgl" +
       "OXxJnDiAGe/O3W2y\nt7PsztpnkwJRpTiAKI0KiEptGkFEwqtQQZU+aBsEtL" +
       "T5Byq1lZCgrSK1lVoqVZVoqvaPfjOze7u3\nZx9WfNLO7e58j/l9r/lmX/4E" +
       "1Tk2WqU6CTZtEScxlElj2yHakIEdZz+8GlffrWtMn99t0hiqSaGY\nrjHUml" +
       "IdRcMMK7qmJG/vL9pok0WN6ZxBWYIUWeKosc2Td2dqW4XA0TMXO06cq+2Job" +
       "oUasWmSRlm\nOjWHDVJwGGpLHcWTWHGZbigp3WH9KfQ5YrqFIWo6DJvMuR89" +
       "iOIpVG+pXCZDvSlfuQLKFQvbuKAI\n9UpaqAUJS23CsG4SbaCkDjg3l3PCsj" +
       "2+kUpqELKETx4EOGIFgHpNCbVEWwHVil84eMvxsy/EUesY\natXNDBemAhIG" +
       "+sZQS4EUJojtDGga0cZQu0mIliG2jg19RmgdQx2OnjMxc23ijBCHGpOcsMNx" +
       "LWIL\nnf7LFGpROSbbVRm1SzbK6sTQ/Ke6rIFzALszgC3h7uTvAWCTDguzs1" +
       "glPkvtMd0Ej/dEOUoY1+0G\nAmBtKBCWpyVVtSaGF6hD+tLAZk7JMFs3c0Ba" +
       "R13QwtDKeYVyW1tYPYZzZJyhrihdWk4BVaMwBGdh\naHmUTEgCL62MeCnkn0" +
       "2dn5668O2f7RCxXasR1eDrbwKm1RGmEZIlNjFVIhmvuoknk4fdVTGEgHh5\n" +
       "hFjSDKy/eCD115/3SJrr56DZN3GUqGxc3Xu6Z+SBOyiK82Ussaijc+eXIRfp" +
       "kPZm+osWZG1nSSKf\nTPiTl0Z+cfjhF8nfYqgpiepVargFiKN2lRYs3SD2Hc" +
       "QkNmZES6JGYmpDYj6JGuA+BSEv3+7LZh3C\nkqjWEK/qqXgGE2VBBDdRI9zr" +
       "Zpb69xZmeXFftBBCDXChbXC1I/kT/wx9JaE4rpk16JTi2KpC7Vzp\nWaU2US" +
       "xbLwCGSaIMYlPNg9aMK8IxwcPIYuiwkqcFomAVm7pJlZwOiavSLRqZ5P+LEV" +
       "7kq++Yqqnh\n5TCa1gZQ7aKGRuxx9fyVXx8f3v3IKRkyPMw93AxtBJ0JT2eC" +
       "60yUdCYiOlFNjVC1jOuW/gPrH4M8\nhorXsiFzz533neqLQ+BYU7VgOk7aBw" +
       "i9BQ2rdChI9qSoiypEXNezR2YTV8/fJiNOmb8mz8nd/P4r\nl8/+q2VjDMXm" +
       "LpgcKJTsJi4mzatsqRCui6bYXPL/8eie1393+aMbg2RjaF1FDajk5DncF3WJ" +
       "TVWi\nQVUMxJ+7rjU+ig6ejqFaKAxQDMX6oc6sjuooy+V+vy5yLA0p1Jyldg" +
       "EbfMovZk0sb9Op4I2IlTY+\nLJNhwx0ZWaAoqVe/Vn/T799sflcg9qtva2h/" +
       "yxAmc7k9iIP9NiHw/qNn0t986pPZIyIIvChgsOm5\nE4auFoHlhoAFktiAQs" +
       "J9tO6AWaCantXxhEF4MP2vdf3WH/z9623S6ga88Z22+bMFBO+vG0QPX773\n" +
       "36uFmBqVbyIBjIBMolkaSB6wbTzN11E88Zvub/0SfwdqHNQVR58holQggQwJ" +
       "OyaEeTeIcUtk7iY+\n9IHszfNE9Rxb9rh6/MVcn3v/r34kVt2Mw3t/2A17sN" +
       "UvnSp0LwWltyBvKCthfHa5xcdO7oIV0ezd\nhZ08CLv50t6724xL/wW1Y6BW" +
       "hf3U2WdD+SiWedqjrmv48K23O+/7II5iO1GTQbG2E4v4R40QeMTJ\nQ+UpWr" +
       "ftEMtom1rCR2EXJFa70rNSsexJPKwV4w1e9PD7G8NUNeJ+BWRIReFK+4VLuA" +
       "6gds+3iYoG\nYPbQP1tO4nfukYWno3xjGobm7S/Tb5Mv3Pr4n+aomo2MWlsM" +
       "MkmM0NIauMqygrdH9BdBuj/6wksX\n2QebtkuVG+evdVHGjdvPzvRsf/Wxay" +
       "hzPREjREW3T15/VzyvvxcTLZCscBWtUzlTf9gcoBTW49om\nNyx/0yKick0p" +
       "Kpu5b/vh6vCisiMalaIe8WF7JJliwq6xKi7nUAl0ZtzlPllnmCwj/wfSSaFm" +
       "d5V0\nvYsPu6BeuRYcFAh4syt8xvCji+O8crLvp+8d+O6sdOSGKgeJMNe4+t" +
       "Af/ngs/sRbE5Iv2q9FiE+v\nPvfn16+MLJPxJ5vatRV9ZZhHNrYCTKvFM6C3" +
       "mgZB/c6m3pcfHPlYrIjz7WCoYYJSg2AZVFv5kJQZ\nuO0z81k83F7u+i/C1e" +
       "m5vnPBrq8pz/ausE8L0Lgl9mDozYs3Cwm4ildFTN7L0OdzhI1S29AGqWtq\n" +
       "ji94VYVgMQ9d/yAtBhYYX4wFeFfZ7Vmg+5qCP2NJwYcYisN5h99agpNWQS7I" +
       "IJvbAXnJ8RJ9RCZs\n8xSzAG1hMWh7vcu/Xxja8MIfqjJ3gg/H4cQNoPa6hR" +
       "IuQWwFEL66GAhfgmu9B2H9tYZsd0W1yuQx\nDyx+oCZCzCNVgD7Oh5MMLbVs" +
       "YmGbhJmj/qudpLoWYJ9dDPa9Hn7fDgvDHhcS4z72ZRXYR/B04CGg\n6KugSP" +
       "JzvCNbssBEz1Qx0Rk+PAUndt1nLYUDn3kisMjTi7FIAq5RzyKj1xTQz1eZu8" +
       "CH58DPENCD\nGKp8LojpBfY6Ac5zC8VZhGYycrzjTXBXxcch+UFDTX34wN2f" +
       "pn77H3FQKX10aIaTf9Y1jFAzEG4M\n6iF0s7pA2SxbVVm0vs/QyvmPndBdWW" +
       "VZ/ZrkegOSPsoFoc//wmQXGWoOkcF25t2FiX4MdRSI+O1P\nSiHZJrpc/t0n" +
       "IT9ylHen3D5ry3Z68dXOb6tc+d1uXD30ypE1xcf2f0P0anWqgWdmuJimFGqQ" +
       "x7RS\na9Y7rzRf1vvotVcPvvm9L/u7c9kBriJut8rZKiEA7eDcB6jhgsXEkW" +
       "fmhyveuPX8mY9j4gj3f7P7\nN6lsFQAA");
}
