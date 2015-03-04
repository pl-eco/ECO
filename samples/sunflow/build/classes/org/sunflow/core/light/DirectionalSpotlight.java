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
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVYX2wcxRmfO/93HN/Zzh8THDtxHNTY9LZJS9XUCOoYmzgc" +
                                                    "tomdKHXUmPHu3N3GuzvL7Jx9MXUhkVCiSI1Qa9KAwA8oEZCGBKFGgCqkvLSA" +
                                                    "6AtV1aoPhaovRaV5yEMpKm3pN7O7t3d7f+LSh560s3Mz33x/Zr7v932zV26i" +
                                                    "OoehAZsaJ9IG5QmS44njxj0JfsImTuJA8p5JzByiDRvYcaZhbFbtfS326edP" +
                                                    "Z+JRVD+DOrBlUY65Ti3nIHGosUC0JIoFoyMGMR2O4snjeAErWa4bSlJ3+GAS" +
                                                    "rStYylFf0ldBARUUUEGRKihDARUsWk+srDksVmCLO4+hH6BIEtXbqlCPo+3F" +
                                                    "TGzMsOmxmZQWAIdG8f8wGCUX5xjalrfdtbnE4GcGlJWfHIu/XoNiMyimW1NC" +
                                                    "HRWU4CBkBrWYxJwjzBnSNKLNoDaLEG2KMB0b+pLUewa1O3rawjzLSH6TxGDW" +
                                                    "JkzKDHauRRW2sazKKcubl9KJofn/6lIGToOtmwJbXQtHxTgY2KyDYiyFVeIv" +
                                                    "qZ3XLY2jnvCKvI19DwEBLG0wCc/QvKhaC8MAanfPzsBWWpniTLfSQFpHsyCF" +
                                                    "oy0VmYq9trE6j9NklqPOMN2kOwVUTXIjxBKONobJJCc4pS2hUyo4n5vj9557" +
                                                    "3NpvRaXOGlENoX8jLOoOLTpIUoQRSyXuwpb+5Hm86e0zUYSAeGOI2KV54/u3" +
                                                    "vnN39413XZo7y9BMzB0nKp9VL861ftA1vGtvjVCj0aaOLg6/yHLp/pPezGDO" +
                                                    "hsjblOcoJhP+5I2Dv/zuk5fJJ1HUPIbqVWpkTfCjNpWatm4Q9iCxCMOcaGOo" +
                                                    "iVjasJwfQw3QT+oWcUcnUimH8DFUa8iheir/wxalgIXYogbo61aK+n0b84zs" +
                                                    "52yEUAM86FvwtCL3J98cMSVDTaJgFVu6RRXwXYKZmlGISmcZsakyMjyhzMEu" +
                                                    "Z0zM5h3FyVopgy7OqlmHU1NxmKpQlvaHFZUyohh6OsOVB3QGOwnWY2PKplwO" +
                                                    "JoTv2f8XqTmxF/HFSASOqSsMEgbE135qaITNqivZfSO3rs6+H80HjbeLHA2A" +
                                                    "0IQnNCGEJlz+5YSiSETK2iCEu+4AhzkPsACA2bJr6nsHHj3TWwN+aC/WwkkI" +
                                                    "0l4w39NoRKXDAXaMSYRUwYE7Xzx6OvHZS/e7DqxUBvqyq9GNC4snDz/xtSiK" +
                                                    "FiO2sBCGmsXySYGzeTztC0dqOb6x0x9/eu38Mg1itigFeFBSulJAQW/4LBhV" +
                                                    "iQbgGrDv34avz7693BdFtYAvgKkcQwwAXHWHZRRBwqAPr8KWOjA4RZmJDTHl" +
                                                    "Y2IzzzC6GIxIJ2mV/TY4lHUiRnrg2egFjXyL2Q5btBtcpxKnHLJCwvfoWzee" +
                                                    "vf7cwN5oIdLHCnLnFOEubrQFTjLNCIHxP1yY/PEzN08flR4CFDvKCegT7TCg" +
                                                    "CBwZbOtT7z72+48+vPibaOBVHNJpds7Q1RzwuCuQAhhjuC7r9B2yTKrpKR3P" +
                                                    "GUQ45z9jO3df/+u5uHuaBoz4znD37RkE43fsQ0++f+zv3ZJNRBU5LrA8IHM3" +
                                                    "oCPgPMQYPiH0yJ389dZn38EvAAQD7Dn6EpFIhqRlSG69Io+qX7aJ0Nxu0Wyz" +
                                                    "S+ZycqRT/qsH0bsqB9GoSNUFwfePCWPu1J8+kxaVhE+ZDBVaP6NceX7L8H2f" +
                                                    "yPWBH4vVPblSVIKyJli757L5t2hv/S+iqGEGxVWvZjqMjazwlhmoExy/kIK6" +
                                                    "qmi+OOe7CW4wH6dd4RgqEBuOoAANoS+oRb85FDQtYpc3wxPzgiYWDpoIkp29" +
                                                    "ckmvbHeK5iu+zzbYTF/AoiBDNYD1kmIjR5sL4deE9AYJFsqjr8sgdA/6G8Vq" +
                                                    "dMIT99SIV1BjSDSDIEnTmS+ps0TSYSKAq4qoHfC0eaLaKoga9UTVzWFHd3xh" +
                                                    "20uETTAo3MYlWu0TlNUNbPektleQ+pAnNcKqe/sk000oVxa8ekpZbv9o/vmP" +
                                                    "X3VTTdi1Q8TkzMrZLxLnVqIFFeqOkiKxcI1bpUrfWe/a9AX8IvD8WzzCFjHg" +
                                                    "Vintw16ptC1fK9m2gLTt1dSSIkb/fG355y8vn3bNaC8u0Ebg/vHqb//1q8SF" +
                                                    "P75XJt+D81PM/7fdP+LtfpTtEb1HKnO7E54Oj1tHBW5HPW6NDGu6CNGysaGb" +
                                                    "UIsLiKbMFSe3ua8A+CL+uq6SkiYpqpcpmmUqETu8tVKpLnf34qmVVW3i0u6o" +
                                                    "B7qHOGri1P6qQRaIEQLarUXlzcPychIA3NlXfvoG/2Dg2+459Vd20/DCd079" +
                                                    "Zcv0fZlH/4uipidkU5jlKw9fee/Bu9QfRVFNHidL7lvFiwaL0bGZEbggWtNF" +
                                                    "GNmdP295vHd4xYVfZJQWFsGpBSkuKvcz6p9fd8n5SVMJXOdEDvXJNhWSTbnv" +
                                                    "ockxKcaqkkSl889DFZG1NYg6STMumoNuGp0GsJ6j1CDYKs20ciCdN1rmgS54" +
                                                    "+j2j+9dsdKFKy1XmnhDNEkfr04SPZ80pbNoGccppXQOJY+0aJzyNE19K4zNV" +
                                                    "5s6K5ilX4yRd9DQWgydvq946X6U9nnp71qxepBgFtpZ40VQGAMZKi68jRLJ5" +
                                                    "uooRK6L5IUfNYESVPa9doLq2NqtOwJP0rEqu2aoGybGhnOx6jUIdLINxtbi5" +
                                                    "XzQPiOaY5PxCFUNfFM1zgHFg6GSGcmqJgfO3tanNj/cjnk1HvpQjvVxl7rJo" +
                                                    "LkFmEKrRRbesfKRM+cvRhnL3VVGFd5Z8PHM/+KhXV2ONm1cP/U7ewPIfZZqS" +
                                                    "qDGVNYzCqrCgX28zktKlck1ujejmoWshOAou0pBx5VsqfdWlfp2jeJgaXEm8" +
                                                    "Csmuc7SugAxgyesVEr0JcQ9EovuW7ft+XF49RG2ccGvjHCpIXOL2Vfiv6Com" +
                                                    "cpP8LOnnkaz7YXJWvbZ6YPzxW9+8JJNSnWrgpSXBpTGJGtxbaD4Xba/IzedV" +
                                                    "v3/X562vNe30c2yraNq9q2dIt57yN7QR0+byTrX05uaf3fvS6ofyivgfdbaT" +
                                                    "Iy8WAAA=");
}
