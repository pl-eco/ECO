package org.sunflow.core;
import org.sunflow.SunflowAPI;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
final class InstanceList implements PrimitiveList {
    private Instance[] instances;
    InstanceList() { super();
                     instances = (new Instance[0]); }
    InstanceList(Instance[] instances) { super();
                                         this.instances = instances; }
    public final float getPrimitiveBound(int primID, int i) { return instances[primID].
                                                                getBounds(
                                                                  ).
                                                                getBound(
                                                                  i);
    }
    public final BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                             new BoundingBox(
                                                             );
                                                           for (Instance i
                                                                 :
                                                                 instances)
                                                               bounds.
                                                                 include(
                                                                   i.
                                                                     getBounds(
                                                                       ));
                                                           return bounds;
    }
    public final void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        instances[primID].
          intersect(
            r,
            state);
    }
    public final int getNumPrimitives() { return instances.
                                                   length;
    }
    public final int getNumPrimitives(int primID) { return instances[primID].
                                                      getNumPrimitives(
                                                        );
    }
    public final void prepareShadingState(ShadingState state) {
        state.
          getInstance(
            ).
          prepareShadingState(
            state);
    }
    public boolean update(ParameterList pl, SunflowAPI api) {
        return true;
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1165455008000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVZe2wcRxmfO79dx680jmNiJ3Hsqs7jrokIanBpcUweDnZi" +
                                                    "4iSIK8Qd782dN9nb3e7O2ReH0DRSlagSEbRpSKFYAlJCS5oURGhRVcl/AGkp" +
                                                    "QrRCRRWiKSDRihCJ/EGpKFC+b2Zft7e+FJpa2tm5mfm+md/3nvW5q6TKtshq" +
                                                    "09AOZjWDJ1iBJ/ZrGxL8oMnsxPbhDaPUsll6UKO2vRvGxpXup5vefvcrk81x" +
                                                    "Up0iC6muG5xy1dDtXcw2tCmWHiZN/uhmjeVsTpqH99MpmsxzVUsOqzbvHyY3" +
                                                    "BUg56Rl2j5CEIyThCElxhOSAvwqIFjA9nxtECqpz+17yJRIbJtWmgsfjZEUx" +
                                                    "E5NaNOewGRUIgEMt/t4LoARxwSLLPewScwngR1YnT35tX/MPK0hTijSp+hge" +
                                                    "R4FDcNgkRRpyLDfBLHsgnWbpFGnRGUuPMUulmjojzp0irbaa1SnPW8wTEg7m" +
                                                    "TWaJPX3JNSiIzcor3LA8eBmVaWn3V1VGo1nA2uZjlQi34DgArFfhYFaGKswl" +
                                                    "qTyg6mlOloUpPIw9n4YFQFqTY3zS8Laq1CkMkFapO43q2eQYt1Q9C0urjDzs" +
                                                    "wknHvExR1iZVDtAsG+ekPbxuVE7BqjohCCThZFF4meAEWuoIaSmgn6s77jhx" +
                                                    "SN+mx8WZ00zR8Py1QNQVItrFMsxiusIkYcOq4VO07fnjcUJg8aLQYrnmmS9e" +
                                                    "++SarrkX5JqPRKzZObGfKXxcOTPR+PLSwb6NFXiMWtOwVVR+EXJh/qPOTH/B" +
                                                    "BM9r8zjiZMKdnNv1888deZJdiZP6IVKtGFo+B3bUohg5U9WYtZXpzKKcpYdI" +
                                                    "HdPTg2J+iNRAf1jVmRzdmcnYjA+RSk0MVRviN4goAyxQRDXQV/WM4fZNyidF" +
                                                    "v2ASQqrhIevgqSHyT7w5+Xxyjw3mnqQK1VXdSILxMmopk0mmGOMTIN3JHLUO" +
                                                    "2Eklb3Mjl7TzekYzppO2pSQNK+v9VgyLJYeEIysMQ0ICrcz8kPkXEF/zdCwG" +
                                                    "ol8adnwNfGaboaWZNa6czG/afO38+EtxzxEcyXDSCdsknG0SuE0iuA2JxQT3" +
                                                    "m3E7qVRQyQFwbphs6Bv7wvZ7jndXgDWZ05Ugzzgs7QZkzhk2K8agHwFcvuNK" +
                                                    "+7fvPpZ45+xd0gyT84frSGoyd3r6/r333RYn8eK4i5hgqB7JRzFaelGxJ+xv" +
                                                    "UXybjr319oVThw3f84oCuRMQSinRobvD0rcMhaUhRPrsVy2nF8efP9wTJ5UQ" +
                                                    "JSAycgqWDEGnK7xHkWP3u0ESsVQB4Ixh5aiGU25kq+eTljHtjwizaBT9FlAK" +
                                                    "KoY0w1PvmL544+xCE9ubpRmhlkMoRBDe8pO5Ry9+ffXGeDBeNwUy4Bjj0vtb" +
                                                    "fCPZbTEG478/PfrwI1eP3S0sBFasjNqgB9tBiAWgMhDrAy/c+9rl18/8Ju5Z" +
                                                    "FSkA6S0+cwgQGgQpVHnPHj1npNWMSic0YbD/aupdd/GvJ5qlEjUYcW1gzfUZ" +
                                                    "+ONLNpEjL+37R5dgE1MwQfmA/WUS90Kf84Bl0YN4jsL9r3Q+eol+E+InxCxb" +
                                                    "nWEiDBEHEB4qITTUJ9q1obnbsFlulsyJgY5S1TY6qm2MVC02PaHdYoIj+nbv" +
                                                    "/P4n4MjUMfvdlb+6b3blH0AiKVKr2lB0DFjZiFwWoPnbuctXXlnQeV6YfOUE" +
                                                    "tUXYqQ8XAaU5vih1CwwNHuYVCLEPnpyDOScj+Z4bGGnHFEhJbgj/cBhLVS7i" +
                                                    "ZMm8AThCV33z62oLijQQY/+5U5s4+sd3hAWXRMmIciJEn0qee6xj8M4rgt4P" +
                                                    "V0i9rFCabsAcfNr1T+b+Hu+u/lmc1KRIs+IUuHuplsegkAKF227VC0Vw0Xxx" +
                                                    "gSarkX4vHC8Nh8rAtuFA6ac56ONqYXsyNmKzsRAjwkk+JSi6RduLza0yl3Eo" +
                                                    "xfMTmgreX5VRdaoVYERjepZPltfEqKXmoO6Zcgqz5OHWywcee+spme3CYg8t" +
                                                    "ZsdPPvhe4sTJeKDUXVlSbQZpZLkrcC2QTvIe/MXg+Q8+6Bw4IJ2kddCpuZZ7" +
                                                    "RZdpYnhdUe5YYostb144/Nz3Dh+TMFqLK73NcJF56tV//zJx+o0XI4qMCvBw" +
                                                    "/HG7WfCMOu5UDI4byCCKWodq2NAZxmN3TlYfqpHwbiIwWYhwj86i2mNEBBHf" +
                                                    "LB984vvP8JdXf1wiWDW/AsOEl47+pWP3nZP3/A8Vx7KQPMMsnxg59+LWW5SH" +
                                                    "4qTCs+6SK00xUX+xTddbDO5g+u4iy+6Sli1kHW3WMexuLZTJPRNl5tLY7AOH" +
                                                    "UFBHUqUg92XRuXVzzuQiG848u/hHd5ydfV3k9IJktaHMNio260vTnzx2u6Nv" +
                                                    "7N/l5YUG4iSHWicv1IZzoePv2jyC4aTGtNQpihdhUqcGg/DtIo06xhst0m2l" +
                                                    "ZwPL7SoJ7p5jiSoBbXa+u6DwujNHT86mdz6+Lu5I5jNwNG6YazU2xbTAjrWi" +
                                                    "n/KksRjB3wpPqyON1uiiLzr0CRsJoOoLey3+HBGN4HOkjC6PYnOIk5Ys4x74" +
                                                    "TUZel+l9OzY75DajGGk1g/J5Ch8fXicOroanzYHXdmPgxYrV1x5UXw4ulIkR" +
                                                    "yi218FHB/stlUH8Vm+OcNALqzxqWlpaIXcZLSxiLeVXPbjIK10WPD9ntisF9" +
                                                    "f3D0FWJBhRd2S4x3Fz0olO6u6I6oXaCUs2UAwE9LTBzlG2Uk9S1sTkFqUl1S" +
                                                    "z0qiDKRyylDT15XQIhzsdatF9/3BJRQ8+Lkyc+exOctJM6h/Rz5XjGjk/R+/" +
                                                    "1zl+7w01b99xf1wGw7PY/OD/xiCM9BNE1urEfd9wFy39fjE2SdGRfOubKwPy" +
                                                    "p9g8B+WHaTG4gLIgMU5957o4F+LgEnjWOjjXRuLEZjw6mJZJFlhTMHALTBbu" +
                                                    "srbgsjH5HhgdEtv8ogzUX2NzCWrYvJkGdFHOVTNhGBqj+nVBI16yBp71Duj1" +
                                                    "7xt08EivlZn7HTavgmbA/DZRqISzxRaYjygPOGkIfr3C0qS95IO4/IirnJ9t" +
                                                    "ql08u+e38nLqfmitGya1mbymBS8PgX41mElGFeerk1cJU7zeADcJaw9iFb7E" +
                                                    "KS/LZX/i5KbAMhC40wsu+jPUy7AIu2+artKb/fJYXooKJJD9iZMz3F9Fn1qw" +
                                                    "vBX/PHBL0bz898G4cmF2+45D1z72uKhroaSjMzPIpRYu4PIrk1fOrpiXm8ur" +
                                                    "elvfu41P1/W6hYq4Y7UGfLw9oNvsfwFIdQF0qhkAAA==");
}
