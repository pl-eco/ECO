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
                                                                normalize();
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
                                                           getGeoNormal()) <
                                                       0 &&
                                                       Vector3.
                                                       dot(
                                                         dir,
                                                         state.
                                                           getNormal()) <
                                                       0) {
                                                     float x =
                                                       state.
                                                         getPoint().
                                                         x -
                                                       src.
                                                         x;
                                                     float y =
                                                       state.
                                                         getPoint().
                                                         y -
                                                       src.
                                                         y;
                                                     float z =
                                                       state.
                                                         getPoint().
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
                                                                     getPoint(),
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
                                getLuminance(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1169184962000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALUYa2wUx3l8Z/v8Ss8PMA4PGxuSNDa5a2ipUhwVHAcSw4Id" +
                                                   "HxhiEsx4d+68sLez\n2Z2zD4fmoaqBJkoa2kRKHzxUUZk8aCKRllZKU1DeQZ" +
                                                   "VCpKRV2tBWSG2lNpWqSilV+6PfzOze3u09\nIKCetLN7M99879fMC5+gGsdG" +
                                                   "i1UnxvZaxIkNJkaw7RBt0MCOswWmJtQ3aupH5jaaNISqFBTSNYai\niurENc" +
                                                   "xwXNfiQ7f3Z23UZ1Fjb8qgLEayLLbbWOXi26CsKkK47fCp1oePVXeFUI2Cot" +
                                                   "g0KcNMp+Y6\ng6QdhpqV3XgaxzNMN+KK7rB+BV1DzEx6kJoOwyZz7kMPoLCC" +
                                                   "ai2V42SoW/GIx4F43MI2TscF+fiI\nIAsY2mzCsG4SbSBHDnauKNwJbLv7Ro" +
                                                   "uhAUkdXxwDcQQHIPXSnNRS2iJRrfDxsS/vO/psGEXHUVQ3\nExyZCpIwoDeO" +
                                                   "mtIkPUlsZ0DTiDaOWkxCtASxdWzos4LqOGp19JSJWcYmzihxqDHNAVudjEVs" +
                                                   "QdOb\nVFCTymWyMyqjdk5HSZ0YmvevJmngFIjd7ostxV3P50HABh0Ys5NYJd" +
                                                   "6W6j26CRbvCu7Iybh8IwDA\n1kiasCmaI1VtYphArdKWBjZT8QSzdTMFoDU0" +
                                                   "A1QYWlgWKde1hdU9OEUmGOoIwo3IJYCqF4rgWxia\nHwQTmMBKCwNWyrNPX/" +
                                                   "unB47/4NW1wrerNaIanP8G2NQZ2DRKksQmpkrkxouZ2FNDd2cWhxAC4PkB\n" +
                                                   "YAkzcN2prcpfftklYRaVgBme3E1UNqFuPtg1ev8dFIU5G3UWdXRu/ALJRTiM" +
                                                   "uCv9WQuitj2HkS/G\nvMXTo2/e/dBz5K8h1DCEalVqZNLgRy0qTVu6Qew7iE" +
                                                   "lszIg2hOqJqQ2K9SEUgW8FXF7ODieTDmFD\nqNoQU7VU/AcVJQEFV1E9fOtm" +
                                                   "knrfFmZT4jtrIYQi8KBb4Pkckj/xZuirsbiTMZMGnYk7thqndir3\nX6U2iR" +
                                                   "t6aorFb9dt0ApIgo2ERZmYjHE/shgaj0/RNIljFZu6SeMpHSJXpTdpZJq/rw" +
                                                   "p7lvPfOlNV\nxRNiMLANiIk7qaERe0Kdu/DuvnUbv3lAOg13dFdyhvqAaMwl" +
                                                   "GuNEYxJ/KaKoqkrQmseJSxOCAfZA\nKEPSa7oxce+GXQd6wuA71kw1aI+D9o" +
                                                   "CMLkfrVDrox/uQSI0qOF3HD3fsj12cWyOdLl4+LZfc3fje\nibNH/9nUG0Kh" +
                                                   "0jmTSwpZu4GjGeGJNpcLlwejrBT+vz+66eSHZz/+vB9vDC0vSgPFO3kY9wRt" +
                                                   "YlOV\naJAYffTHro2Gt6GxgyFUDbkB8qHgH1JNZ5BGQTj3e6mRyxJRUGOS2m" +
                                                   "ls8CUvnzWwKZvO+DPCWZrF\ndxsYp5H7dxc8812HF2++Ot/iY7t0Lm7tgBQi" +
                                                   "9V78eu0Xfv1K4xtCLV6WjubVwQRhMuZbfGfZYhMC\n8x8/M/Kdpz/Zv0N4iu" +
                                                   "sqDIpjZtLQ1Sxsud7fAsFuSD90lm8101TTkzqeNAj3uP9Gr7v5J397olma\n" +
                                                   "xoAZz7IrLo3An7/2NvTQ2Z3/6hRoqlRebHwxfDApTZuPecC28V7OR/bh95d8" +
                                                   "9y18CHIh5B9HnyUi\npSAhGRJ6jAu994oxFli7mQ89gHtFGdcvUdon1H3PpX" +
                                                   "oy973zc8F1I87vEfLNsAlb/dLyfFjGtbsg\nGL13YmcK4L50evM9zcbp/wDG" +
                                                   "ccCoQkl1hm3IH9kCI7rQNZGPzrzWvutcGIXWowaDYm09Fv6P6sHx\niDMFqS" +
                                                   "drrVkrfKt5po6PQmQklLDQVUA2718tMHdj+fBfzxsDP3ImJlccV94dPiQUUD" +
                                                   "bwS9TFAJ7Z\nV7cevvgrdl7g8SOQ7+7OFudVaKb8vbd8ON1S+9KRdAhFxlGz" +
                                                   "6rZ7Y9jIcD8fh+7E8XpAaAkL1gs7\nDVlW+3MZZnEw+vPIBmPfz+fwzaH5d1" +
                                                   "Mg3Ju4thfAE3XDPRoM9yokPtaILcvFeEMuOCOWrU9j3gKi\nMFQqsbqAoQX5" +
                                                   "xSMNBRVKOjRkX5Spg48r+bBWWnlVWW9YXchnh7fsvUvwOcSHAWBH022PnY4i" +
                                                   "dsYI\n98kgPxs+Iz/L4Glx+Wkpw8+Iy0/NJHZ0x+Oou4ijYRuazs0iW9/GIQ" +
                                                   "Os3XUFqmp1WWstw9o2l7Uq\nGwKsI//gZOtpaMCmRUm58EjPL97eemS/LMMV" +
                                                   "4rBg14T64O//sCf8rTOTcl8w2ALABzuP/enkhdF5\nMmXLTn1ZUbOcv0d268" +
                                                   "KboxZPX92VKAjo1/u6X3hg9LzLUWthz7kOzmV/3vsaueHWx/9Yoh2CyKKY\n" +
                                                   "BYyy/f9gFOIaJWQLEjsDJJOfkeQieNpckm1lSO52SdbZWNN5JikZxnoaDiq8" +
                                                   "bFI7wNOeCjwJA12f\nl8yrPOSLixpMhfeSCZqxVcINuqTcYUcYc//2fzQ9gl" +
                                                   "+/N+RWy10MCgy1bjLINDECxWNJQbO5SRzv\n/GT96LPPn2Ln+lZLt+gt7+DB" +
                                                   "jb2rj852rX7xsStoMbsCsgVRt0wvuis8pb8dEidQmfuLTq6Fm/oL\nM34D8J" +
                                                   "OxzS0FeX9pYZvXj2Srh7x3UZvnW8/vUUJCryHPjp1FdhSiEjgY8ybIA2vPB0" +
                                                   "vI98DIkCDz\nYIUu6Bt82AdtYMbSoNAImHFLehycqCOTlBoEm743fu1SEeK1" +
                                                   "GOJPtlAjnfD0uhrpvWyN5PP7ZIW1\nb/PhcYauSRG2OZNO4LRlECcoUlh3b4" +
                                                   "aEOE9crTgxV5zYFYlzqMLaET58T4qj0BlXHD75lM//96+G\nf05xpcv/ysvm" +
                                                   "v6ow0SwpctDEFCQ6M8WvsIhAM1dByuf5cIyhBpCyjMWqp6mu+SL/6GpE3guP" +
                                                   "4oqs\nXLbIEYExEmSsVqNweBIJ4JQ/XKpLu2TbdKn6IPj8aQWdnuHDScjYoN" +
                                                   "ORKcqoySdO+Cp8+WpUyGve\ndleF26/I69+psHaWD29CteS80xl5Itjps/7W" +
                                                   "5bIOPfO8Urcp/DjZUXQdK68QVeWj++/5VPng3+Je\nIHfN16igumTGMPI7/r" +
                                                   "zvWssmSV1w3yj7f0u83g+kZf+aBxoe8RYMn5PQHzDUHIQG3+evfLDfMNSY\n" +
                                                   "BwYp2v3KB/otpDkA4p+/szx3ahaHSX7uiclzT7ZAX1wvywqqs7gf9ypoRt6Q" +
                                                   "T6jbT+xYmn1sy5Oi\nLNeoBp6d5WgaFBSRtyG5KtxdFpuH6z300otjr/z4K1" +
                                                   "6XIU7L89wrkCI/XClXK9gfKn/pK4h1aYuJ\nS4PZny14+da5w+dD4hLkf7AZ" +
                                                   "WabWGAAA");
}
