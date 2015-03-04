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
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUYXWwcxXnu/O84vrOdHxMcO3Ec1Nj0tklL1dQI6hibOBz2" +
                                                    "kXOi1FFjxrtzdxvv7Sy7c/bF1IVEQokiNUKtSQMCP6BEQBoShBoBqpDy0gKi" +
                                                    "L1RVqz4Uqr4UleYhD6WotKXfzOze3u39xKXqSTs3O/P9zvc7e+UmanBsNGRR" +
                                                    "40TaoCxG8ix23Lgnxk5YxIkdiN+TwLZDtFEDO840rM2q/a9FPv386Uw0jBpn" +
                                                    "UBc2Tcow06npHCQONRaIFkcRf3XMIFmHoWj8OF7ASo7phhLXHTYcR+uKUBka" +
                                                    "iHsiKCCCAiIoQgRlxIcCpPXEzGVHOQY2mfMY+gEKxVGjpXLxGNpeSsTCNs66" +
                                                    "ZBJCA6DQzN8Pg1ICOW+jbQXdpc5lCj8zpKz85Fj09ToUmUER3UxycVQQggGT" +
                                                    "GdSWJdk5Yjsjmka0GdRhEqIlia1jQ18Scs+gTkdPm5jlbFI4JL6Ys4gtePon" +
                                                    "16Zy3eycyqhdUC+lE0Pz3hpSBk6Drpt8XaWG43wdFGzVQTA7hVXiodTP66bG" +
                                                    "UF8Qo6DjwEMAAKhNWcIytMCq3sSwgDql7QxsppUks3UzDaANNAdcGNpSlSg/" +
                                                    "awur8zhNZhnqDsIl5BZAtYiD4CgMbQyCCUpgpS0BKxXZ5+bkveceN/ebYSGz" +
                                                    "RlSDy98MSL0BpIMkRWxiqkQitg3Gz+NNb58JIwTAGwPAEuaN79/6zt29N96V" +
                                                    "MHdWgJmaO05UNqtenGv/oGd01946LkazRR2dG79Ec+H+CXdnOG9B5G0qUOSb" +
                                                    "MW/zxsFffvfJy+STMGqdQI0qNXJZ8KMOlWYt3SD2g8QkNmZEm0AtxNRGxf4E" +
                                                    "aoJ5XDeJXJ1KpRzCJlC9IZYaqXiHI0oBCX5ETTDXzRT15hZmGTHPWwihJnjQ" +
                                                    "t+BpR/In/hmaVTI0SxSsYlM3qQK+S7CtZhSiUsXBWcsAqzk5M2XQRcWxVYXa" +
                                                    "6cK7Sm2iGHo6w5QHdBuODVTFRtKiTCzGuKNZ/38Wea5ldDEUAgP0BMPfgMjZ" +
                                                    "Tw2N2LPqSm7f2K2rs++HC+Hgng9DQ8A05jKNcaYxSb8SUxQKCV4bOHNpaDDT" +
                                                    "PAQ8pMK2XcnvHXj0TH8deJi1WA9nzEH7QVdXojGVjvpZYULkPhVcs/vFo6dj" +
                                                    "n710v3RNpXoKr4iNblxYPHn4ia+FUbg0F3MNYamVoyd4Bi1kyoFgDFaiGzn9" +
                                                    "8afXzi9TPxpLkrubJMoxeZD3B21hU5VokDZ98oPb8PXZt5cHwqgeMgdkS4bB" +
                                                    "uyER9QZ5lAT7sJc4uS4NoHCK2lls8C0v27WyjE0X/RXhJO1i3gFGWce9vw+e" +
                                                    "jW44iH++22XxcYN0Km7lgBYiMY+/dePZ688N7Q0X5/BIUVVMEiYzQofvJNM2" +
                                                    "IbD+hwuJHz9z8/RR4SEAsaMSgwE+jkJ+AJPBsT717mO//+jDi78J+17FoFDm" +
                                                    "5gxdzQONu3wukD0M6bLOwCEzSzU9peM5g3Dn/Gdk5+7rfz0XldY0YMVzhrtv" +
                                                    "T8Bfv2MfevL9Y3/vFWRCKq9evuY+mDyALp/yiG3jE1yO/Mlfb332HfwCJFdI" +
                                                    "aI6+RESOQkIzJI5eEaYaFGMssLebD9ussr28WOkWb43Aelf1IBrnRbgo+P4x" +
                                                    "Zcyd+tNnQqOy8KlQewL4M8qV57eM3veJwPf9mGP35cuzEjQsPu6ey9m/hfsb" +
                                                    "fxFGTTMoqrrd0GFs5Li3zEAH4HgtEnRMJful1VyWruFCnPYEY6iIbTCC/GwI" +
                                                    "cw7N562BoGnjp7wZnogbNJFg0ISQmOwVKP1i3MmHr3g+22TZ+gLmrRaqg1wv" +
                                                    "IDYytLk4/WahcEHphMbn6yIIpaG/USpGNzxRV4xoFTFG+DAMnDTd9jh1l3E6" +
                                                    "THjiqsFqBzwdLquOKqzGXVYNc9jRHY/Z9jJmUza0ZJMiW+3jkLUV7HS5dlbh" +
                                                    "+pDLNWTX9vaErWehEVlwOyVlufOj+ec/flWWmqBrB4DJmZWzX8TOrYSLes8d" +
                                                    "Ze1fMY7sP4XvrJc6fQG/EDz/5g/XhS/I/qNz1G2CthW6IMviKW17LbEEi/E/" +
                                                    "X1v++cvLp6UanaWt1xjcLF797b9+Fbvwx/cq1HtwforZ/3b6R9zTD9t7+OyR" +
                                                    "6tTuhKfLpdZVhdpRl1qzjTWdh2jF2NCz0GXzFE1tyU4c80BR4gt5eD1lLU2c" +
                                                    "dy9JmrNVwk94a7UmXJzuxVMrq9rUpd1hN+keYqiFUeurBlkgRiDRbi1pbx4W" +
                                                    "1w4/wZ195advsA+Gvi3tNFjdTYOI75z6y5bp+zKP/hdNTV9ApyDJVx6+8t6D" +
                                                    "d6k/CqO6Qp4su0mVIg2XZsdWm8DVz5wuyZG9BXsL897hNhdek1HeWPhW80tc" +
                                                    "WJxn2LNfb5n9hKoELmq8hnpgm4rBkvJ/JDEh2Jg1iqhw/nnoInKWBlEnYCb5" +
                                                    "cFCW0WlI1nOUGgSb5ZVWLKQLSos60APPoKv04JqVLhZpucbeE3xYYmh9mrDJ" +
                                                    "XDYp7w2VpK6DwrF2iWOuxLEvJfGZGntn+fCUlDhOF12J+eLJ24q3zhNpjyve" +
                                                    "njWLFyrNAlvLvCiZgQRjpvl3DyLIPF1DiRU+/JChVlCixpnXL1BdW5tWJ+CJ" +
                                                    "u1rF16xVk6DYVIl3o0ahDxbBuFo63M+HB/hwTFB+oYaiL/LhOchxoGgiQxk1" +
                                                    "+cL52+rU4cX7EVenI1/KkV6usXeZD5egMnDR6KJsKx+p0P4ytKHSfZV34d1l" +
                                                    "n8Xkpxz16mqkefPqod+JG1jhc0tLHDWncoZR3BUWzRstm6R0IVyL7BFlHboW" +
                                                    "SEf+RRoqrvgXQl+V0K8zFA1Cgyvxv2Kw6wytKwKDtOTOioHehLgHID59y/J8" +
                                                    "PyquHrw3jsneOI+KChe/fRW/lVzFeG0SHxy9OpKTnxxn1WurByYfv/XNS6Io" +
                                                    "NagGXlriVJrjqEneQgu1aHtVah6txv27Pm9/rWWnV2Pb+dDpXj0DsvVVvqGN" +
                                                    "ZS0m7lRLb27+2b0vrX4oroj/Ab9LEigJFgAA");
}
