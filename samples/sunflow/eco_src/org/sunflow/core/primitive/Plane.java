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
import org.sunflow.math.Vector3;
public class Plane implements PrimitiveList {
    private Point3 center;
    private Vector3 normal;
    int k;
    private float bnu;
    private float bnv;
    private float bnd;
    private float cnu;
    private float cnv;
    private float cnd;
    public Plane() { super();
                     center = new Point3(0, 0, 0);
                     normal = new Vector3(0, 1, 0);
                     k = 3;
                     bnu = (bnv = (bnd = 0));
                     cnu = (cnv = (cnd = 0)); }
    public boolean update(ParameterList pl, SunflowAPI api) { center = pl.
                                                                         getPoint(
                                                                           "center",
                                                                           center);
                                                              Point3 b =
                                                                pl.
                                                                getPoint(
                                                                  "point1",
                                                                  null);
                                                              Point3 c =
                                                                pl.
                                                                getPoint(
                                                                  "point2",
                                                                  null);
                                                              if (b !=
                                                                    null &&
                                                                    c !=
                                                                    null) {
                                                                  Point3 v0 =
                                                                    center;
                                                                  Point3 v1 =
                                                                    b;
                                                                  Point3 v2 =
                                                                    c;
                                                                  Vector3 ng =
                                                                    normal =
                                                                    Vector3.
                                                                      cross(
                                                                        Point3.
                                                                          sub(
                                                                            v1,
                                                                            v0,
                                                                            new Vector3(
                                                                              )),
                                                                        Point3.
                                                                          sub(
                                                                            v2,
                                                                            v0,
                                                                            new Vector3(
                                                                              )),
                                                                        new Vector3(
                                                                          )).
                                                                      normalize();
                                                                  if (Math.
                                                                        abs(
                                                                          ng.
                                                                            x) >
                                                                        Math.
                                                                        abs(
                                                                          ng.
                                                                            y) &&
                                                                        Math.
                                                                        abs(
                                                                          ng.
                                                                            x) >
                                                                        Math.
                                                                        abs(
                                                                          ng.
                                                                            z))
                                                                      k =
                                                                        0;
                                                                  else
                                                                      if (Math.
                                                                            abs(
                                                                              ng.
                                                                                y) >
                                                                            Math.
                                                                            abs(
                                                                              ng.
                                                                                z))
                                                                          k =
                                                                            1;
                                                                      else
                                                                          k =
                                                                            2;
                                                                  float ax;
                                                                  float ay;
                                                                  float bx;
                                                                  float by;
                                                                  float cx;
                                                                  float cy;
                                                                  switch (k) {
                                                                      case 0:
                                                                          {
                                                                              ax =
                                                                                v0.
                                                                                  y;
                                                                              ay =
                                                                                v0.
                                                                                  z;
                                                                              bx =
                                                                                v2.
                                                                                  y -
                                                                                  ax;
                                                                              by =
                                                                                v2.
                                                                                  z -
                                                                                  ay;
                                                                              cx =
                                                                                v1.
                                                                                  y -
                                                                                  ax;
                                                                              cy =
                                                                                v1.
                                                                                  z -
                                                                                  ay;
                                                                              break;
                                                                          }
                                                                      case 1:
                                                                          {
                                                                              ax =
                                                                                v0.
                                                                                  z;
                                                                              ay =
                                                                                v0.
                                                                                  x;
                                                                              bx =
                                                                                v2.
                                                                                  z -
                                                                                  ax;
                                                                              by =
                                                                                v2.
                                                                                  x -
                                                                                  ay;
                                                                              cx =
                                                                                v1.
                                                                                  z -
                                                                                  ax;
                                                                              cy =
                                                                                v1.
                                                                                  x -
                                                                                  ay;
                                                                              break;
                                                                          }
                                                                      case 2:
                                                                      default:
                                                                          {
                                                                              ax =
                                                                                v0.
                                                                                  x;
                                                                              ay =
                                                                                v0.
                                                                                  y;
                                                                              bx =
                                                                                v2.
                                                                                  x -
                                                                                  ax;
                                                                              by =
                                                                                v2.
                                                                                  y -
                                                                                  ay;
                                                                              cx =
                                                                                v1.
                                                                                  x -
                                                                                  ax;
                                                                              cy =
                                                                                v1.
                                                                                  y -
                                                                                  ay;
                                                                          }
                                                                  }
                                                                  float det =
                                                                    bx *
                                                                    cy -
                                                                    by *
                                                                    cx;
                                                                  bnu =
                                                                    -by /
                                                                      det;
                                                                  bnv =
                                                                    bx /
                                                                      det;
                                                                  bnd =
                                                                    (by *
                                                                       ax -
                                                                       bx *
                                                                       ay) /
                                                                      det;
                                                                  cnu =
                                                                    cy /
                                                                      det;
                                                                  cnv =
                                                                    -cx /
                                                                      det;
                                                                  cnd =
                                                                    (cx *
                                                                       ay -
                                                                       cy *
                                                                       ax) /
                                                                      det;
                                                              }
                                                              else {
                                                                  normal =
                                                                    pl.
                                                                      getVector(
                                                                        "normal",
                                                                        normal);
                                                                  k =
                                                                    3;
                                                                  bnu =
                                                                    (bnv =
                                                                       (bnd =
                                                                          0));
                                                                  cnu =
                                                                    (cnv =
                                                                       (cnd =
                                                                          0));
                                                              }
                                                              return true;
    }
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
        Vector3 worldNormal =
          parent.
          transformNormalObjectToWorld(
            normal);
        state.
          getNormal().
          set(
            worldNormal);
        state.
          getGeoNormal().
          set(
            worldNormal);
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
        Point3 p =
          parent.
          transformWorldToObject(
            state.
              getPoint());
        float hu;
        float hv;
        switch (k) {
            case 0:
                {
                    hu =
                      p.
                        y;
                    hv =
                      p.
                        z;
                    break;
                }
            case 1:
                {
                    hu =
                      p.
                        z;
                    hv =
                      p.
                        x;
                    break;
                }
            case 2:
                {
                    hu =
                      p.
                        x;
                    hv =
                      p.
                        y;
                    break;
                }
            default:
                hu =
                  (hv =
                     0);
        }
        state.
          getUV().
          x =
          hu *
            bnu +
            hv *
            bnv +
            bnd;
        state.
          getUV().
          y =
          hu *
            cnu +
            hv *
            cnv +
            cnd;
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                normal));
    }
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
        float dn =
          normal.
            x *
          r.
            dx +
          normal.
            y *
          r.
            dy +
          normal.
            z *
          r.
            dz;
        if (dn ==
              0.0)
            return;
        float t =
          ((center.
              x -
              r.
                ox) *
             normal.
               x +
             (center.
                y -
                r.
                  oy) *
             normal.
               y +
             (center.
                z -
                r.
                  oz) *
             normal.
               z) /
          dn;
        if (r.
              isInside(
                t)) {
            r.
              setMax(
                t);
            state.
              setIntersection(
                0,
                0,
                0);
        }
    }
    public int getNumPrimitives() { return 1;
    }
    public float getPrimitiveBound(int primID,
                                   int i) {
        return 0;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) {
        return null;
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169362846000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALUZaWwU1/ntru3FR+v7iDl84CQNJrsNCT1wJDCuSRYWvLGx" +
       "CYbUPM+8XQ+enZnM\nvLHXDg2JIgVKGlJyqZUKQRUqJIUSKY3okYMo0Byoba" +
       "iUVIoKaYTUm6pVqpSq/dHvvZnZ2Z09YrCw\nNG9m5333Pc/HL6NSQ0eLBCNE" +
       "ZzRihPqGYlg3iNgnY8PYDK/GhLOl5bGjGxTVj3xR5JdEiqqjghEW\nMcVhSQ" +
       "xHvtaT0lG3psozCVmlIZKioZ3ySpve+ujKHIJbDp2qe/hISZsflUZRNVYUlW" +
       "IqqUq/TJIG\nRTXRnXgKh00qyeGoZNCeKPocUcxkn6oYFCvUuB89iAJRVKYJ" +
       "jCZFHVGHeRiYhzWs42SYsw/HOFug\nUK8TiiWFiL1pdoC5PBsTxLbxBnOhgc" +
       "gCtjkC6nAJQOv2tNaWtjmqaoFjI1/adfj5AKoeRdWSMsSI\nCaAJBX6jqCpJ" +
       "kuNEN3pFkYijqFYhRBwiuoRlaZZzHUV1hpRQMDV1YgwSQ5WnGGCdYWpE5zyd" +
       "l1FU\nJTCddFOgqp62UVwisuj8Ko3LOAFqN7lqW+quY+9BwQoJBNPjWCAOSs" +
       "mkpIDH27wYaR27NgAAoAaT\nhE6oaVYlCoYXqM7ypYyVRHiI6pKSANBS1QQu" +
       "FLUWJMpsrWFhEifIGEUtXriYtQVQ5dwQDIWiRi8Y\npwReavV4KcM/3U2f7j" +
       "32vdfW8NguEYkgM/krAGmJB2mQxIlOFIFYiFfM0NORreYiP0IA3OgBtmB6\n" +
       "bzw1HP3T620WzMI8MAPjO4lAx4RNB9oGH7hLRQEmxgJNNSTm/CzNeTrE7J2e" +
       "lAZZ25SmyDZDzubp\nwV9sfegF8lc/qoigMkGVzSTEUa2gJjVJJvpdRCE6pk" +
       "SMoHKiiH18P4KC8ByFkLfeDsTjBqERVCLz\nV2Uq/w0migMJZqJyeJaUuOo8" +
       "a5hO8OeUhhAKwoW64apB1h+/U3R7KGyYSlxWp8OGLoRVPZH+Lag6\nCWu6lA" +
       "Qdpkg4BiFDQix4NIoGwhNqkoSxgBVJUcMJCdJVUG8VyRS7Xz3JFJO0btrnY6" +
       "XPm8IyRP/d\nqiwSfUw4eundXf0bvrnXCg8W0raOFLUDp5DNKcQ4hdKcQpwT" +
       "8vk4gwbG0fIQ2HcSMhVqWtUtQ/et\n37G3MwChoU2XgHEYaCdoY4vRL6h9bj" +
       "pHeOUTIKZavr9tT+jK0dVWTIULV9282JXvnTh3+JOqZX7k\nz18SmXpQlCsY" +
       "mRiro+lS1+VNonz0/75v40sfnLvwBTedKOrKyfJcTJalnV5H6KpARKh7Lvkj" +
       "N1QH\ntqCRA35UAqkP5Y7LD5VkiZdHVrb2OJWP6RKMosq4qiexzLacclVBJ3" +
       "R12n3DI6SGP9eDcyqdGG62\n45nf2W6jxtYmK6KYtz1a8Mp65ZGyL/72lcqz" +
       "3CxOEa7OaHNDhFopXesGy2adEHh/4Tuxp565vGcb\njxQ7VCj0PnNcloQUoN" +
       "zkokAuy1BPmCO7hpWkKkpxCY/LhEXc/6pvvO3lv+2vsVwjwxvHs8s/m4D7\n" +
       "/oa16KFzX//3Ek7GJ7Be4qrhglna1LuUe3UdzzA5Ug//ZvF338IHodRBeTGk" +
       "WcIrBuKaIW7HMLf7\nMr6GPHu3saUTaC8vEPp5OveYsOuFRKd5/zs/5VJX4s" +
       "wRINMNG7HWY3meLUuZdZu92Xs3NiYA7o7T\nm7bXyKf/CxRHgaIAHdMY0KFo" +
       "pLKcaEOXBj98482mHecDyL8OVcgqFtdhHv+oHAKPGBNQb1La6jVW\nrZxe4F" +
       "TMFOJGaLUNkMr4VQ7C3VI4/dexvu9mztj48mPRdwcOcgMUTPw8bc9DZ/a14U" +
       "NXfkkvcjpu\nBjLsjlRuMYVZycX9ygdTtWUvPpf0o+AoqhHsaW4EyyaL81EY" +
       "PgxnxIOJL2s/e5CwumZPusIs8mZ/\nBltv7rtFHJ4ZNHuu8qR7FbN2K1y1dr" +
       "rXetPdh/jDao7Sxdeb08kZhE4whdmEB+2XsImKAzRT1JzZ\nNJLQMqFpw8h1" +
       "u1U92LqCLWssR68sGBCrskVdCFedLWpdAVEjbOkFiRRe/ByJWnIkGiEsMr0i" +
       "rZ+7\nSDxWK+Cqt0WqLyBSLNd6CGzmm4TQbsn8InEaK6vPlx7tfPXt4ef2WA" +
       "2wSAZkYY0Juz/6/WTgiTfG\nLTxvmHuADyw58oeXLg02WMXSGoGX5kyhmTjW" +
       "GMzjqFpjhaOjGAcOfaa74/iDgxdtieqyh7l++OD5\n48yb5OY7H/84z/QRgK" +
       "jxeOieqwyadrgabA81FPDQDjtoAuOKybGHNYvLKGVppWKvEPg6CDHhCjHF\n" +
       "HgUPT+k68Ey6PMV8PJVr4Nlo82wswFN3eAqKmY+ncR14Trs889o2dR147nJ5" +
       "5rXtN4rw5Al2U0Yb\n9DmFbEnOPJ7OOD52QEouLvQdyNNxz73/rHoUn7nPb0" +
       "8a2yg0Z1W7VSZTRM7gGGSUsgb1jfzL1210\n+57/4Sl6vnuVldjLCpcoL+Ky" +
       "VYdn21adfOwaxvM2j25e0rVTC+8JTEhv+/nHudU3cz7qs5F6srtl\nBchj6s" +
       "rmrJ7Znj0i98DVZru/zet+7mbXf+585+d29RfxJFOVQB9lnnTAmjLBhqx7by" +
       "zC2TxRZIJ8\nhi3fgkZoaiI0aW9ZC46rqkyw4sbj45+VA854xn/szbbIl+Ha" +
       "bltk+5wt4suO7cU5FhmawKKkJNhx\nEuFkDhfR+AhbDlJUr+lEwzrJRPaqXz" +
       "KlSqKr+6H56L4Jrt227rvnrHuAUww4ujfk6D6IZ9jeVgei\nMwciwmcu60vE" +
       "NdHJIiZ6mS3HYcyUHNR09WA7P3AtcmI+FumAa59tkX1ztkimoK8W2XudLT+j" +
       "qCZB\n6CYzmdbAcM3FVfj5fFRYCdd+W4X9V5viXBC+cNC3iijzDlvOUFQLyq" +
       "Q1Wauads9wtTk7H21WwPWk\nrc2T15qeuTP0Rkx1KXUHp3C+iJLvs+VXFH0e" +
       "lNyi6rLIFTQcwotyCPN9yN21asq1wK/nYwEm0rO2\nBZ69ppD8qMjex2z5HZ" +
       "QeUHAthkE64UblHDu3q+eFueoJHxGl/AyOHUK05JzRW+fKQvTDB7Z/Gn3/\n" +
       "P/w0KX32WxlFC+KmLGd+J2Y8l0ENjUtct0rrq1Hjt79Q1Fr4RBBGCS0rG/9s" +
       "YV2GZPViQQ1mt0yw\nf1BUmQEGTcp+ygT6BCYqAGKP/9Ic29bwowj21Ryyvp" +
       "pTWYZi9lmaNZ/wf544M4Rp/ftkTLj3xLb2\n1GObv80Hk1JBxrOzjExFFAXj" +
       "7uckm0M6ClJzaL2HXjw58sqPvurMWfyspcE+QMuJ1hXWbhHHw+yT\n/wCrP6" +
       "lRfuQ0+5PmH9959NBFPz9C+z+1iyT68xoAAA==");
}
