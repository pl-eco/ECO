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
          init(
            );
        state.
          getRay(
            ).
          getPoint(
            state.
              getPoint(
                ));
        Instance parent =
          state.
          getInstance(
            );
        Point3 p =
          parent.
          transformWorldToObject(
            state.
              getPoint(
                ));
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
          getNormal(
            ).
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
          getNormal(
            ).
          normalize(
            );
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
          getUV(
            ).
          x =
          (float)
            (theta /
               (2 *
                  Math.
                    PI));
        state.
          getUV(
            ).
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
              getNormal(
                ));
        state.
          getNormal(
            ).
          set(
            worldNormal);
        state.
          getNormal(
            ).
          normalize(
            );
        state.
          getGeoNormal(
            ).
          set(
            state.
              getNormal(
                ));
        state.
          setBasis(
            OrthoNormalBasis.
              makeFromW(
                state.
                  getNormal(
                    )));
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
                  getMax(
                    ) ||
                  t[t.
                      length -
                      1] <=
                  r.
                  getMin(
                    ))
                return;
            for (int i =
                   0;
                 i <
                   t.
                     length;
                 i++) {
                if (t[i] >
                      r.
                      getMin(
                        )) {
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
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYb2wcRxWfO/93XPv8J47rJrbjOhGJwy2JWlBw1RCf7Mbh" +
                                                    "EluxG1RXrT3enbM32dvd7s7ZFyeGNBEkaqUIFbekUCyBUoWU/CmoUYpQpXyB" +
                                                    "tipfihCID7SIL1SUfMgHSkWB8t7s39s7n9MILM14bua9N+8379/MXr5FqmyL" +
                                                    "9JuGdmxWM3iS5XnyiPZgkh8zmZ3cn35wjFo2U1Iate0JmJuSe19t+uiTb88l" +
                                                    "4qR6krRSXTc45aqh24eYbWjzTEmTpmB2SGNZm5NE+gidp1KOq5qUVm0+kCbr" +
                                                    "Qqyc9KU9FSRQQQIVJKGCtDegAqZ7mJ7LppCD6tx+inydxNKk2pRRPU42Fwox" +
                                                    "qUWzrpgxgQAk1OLvwwBKMOct0uNjdzAXAX6+X1r+7pOJn1WQpknSpOrjqI4M" +
                                                    "SnDYZJI0ZFl2hln2XkVhyiRp1hlTxpmlUk1dFHpPkhZbndUpz1nMPySczJnM" +
                                                    "EnsGJ9cgIzYrJ3PD8uFlVKYp3q+qjEZnAWt7gNVBOIzzALBeBcWsDJWZx1J5" +
                                                    "VNUVTrqjHD7Gvq8CAbDWZBmfM/ytKnUKE6TFsZ1G9VlpnFuqPgukVUYOduGk" +
                                                    "c1WheNYmlY/SWTbFSUeUbsxZAqo6cRDIwsn6KJmQBFbqjFgpZJ9bBx86d1zf" +
                                                    "p8eFzgqTNdS/Fpi6IkyHWIZZTJeZw9iwPf0CbX/jbJwQIF4fIXZobpy4/ZUd" +
                                                    "XTffcmjuK0EzOnOEyXxKvjDT+O7G1LbdFahGrWnYKhq/ALlw/zF3ZSBvQuS1" +
                                                    "+xJxMekt3jz0q8dOvsI+jJP6EVItG1ouC37ULBtZU9WY9QjTmUU5U0ZIHdOV" +
                                                    "lFgfITUwTqs6c2ZHMxmb8RFSqYmpakP8hiPKgAg8ohoYq3rG8MYm5XNinDcJ" +
                                                    "ITXQSD+0FuL8if+cTEhzRpZJVKa6qhsS+C6jljwnMdmQbJo1NbCandMzmrEg" +
                                                    "2ZYsGdas/1s2LCaZlpoFkPNMmjCsnJ1E7zL/T3LziCexEIvBUW+MBroGMbLP" +
                                                    "0BRmTcnLucGh21en3on7ju+eBCc9sFPS3SmJOyX9nZJiJxKLiQ3acEfHjmCF" +
                                                    "oxDPkOkato0/sX/6bG8FOJC5UAlHiKS9gMpVY0g2UkHQj4jUJoPndfzo8TPJ" +
                                                    "jy/ucTxPWj1Dl+QmN88vPH34G1+Ik3hhqkVYMFWP7GOYIP1E2BcNsVJym858" +
                                                    "8NG1F5aMINgKcrebA4o5MYZ7owawDJkpkBUD8dt76PWpN5b64qQSEgMkQ07B" +
                                                    "eSHPdEX3KIjlAS8vIpYqAJwxrCzVcMlLZvV8zjIWghnhGY1i3AxGWYfOnYDW" +
                                                    "7nq7+I+rrSb2bY4noZUjKETeHf75zRevf69/dzycoptCRW+ccSfgmwMnmbAY" +
                                                    "g/k/nh/7zvO3zjwuPAQo7i+1QR/2KQh/MBkc6zffeuoP77934bfxwKs41MHc" +
                                                    "jKbKeZCxNdgFkoMGCQpt3/eonjUUNaPSGY2hc/6racvO6387l3CsqcGM5ww7" +
                                                    "1hYQzN87SE6+8+Q/uoSYmIzFKUAekDkH0BpI3mtZ9BjqkX/6N5tefJP+AHIn" +
                                                    "5CtbXWQiBRGBjIijl4Sptos+GVnbiV2PWbSWFzMd4lclbL1t9SAaxhobCr5/" +
                                                    "jmozp/78sUBUFD4lSkuEf1K6/FJn6uEPBX/gx8jdnS9ORXAfCXh3vZL9e7y3" +
                                                    "+pdxUjNJErJ72TlMtRx6yyQUeNu7AcGFqGC9sFg7lWnAj9ON0RgKbRuNoCAF" +
                                                    "whipcVwfCZoGPOX7oLW6QdMaDZoYEYPdgqVX9Fuw+5znszWQS+cp3qRIhaXu" +
                                                    "Km+kMS/vOrVZWmp5/+hLH1xxMmTUIhFidnb5mU+T55bjoRvR/UWXkjCPcysS" +
                                                    "kO9xIH8KfzFo/8GGUHHCqYotKbc09/i12TQxEjeXU0tsMfyXa0u/+PHSGQdG" +
                                                    "S+GFYAjuu1d+9+9fJ8//6e0StQlsZlARtAnH/R/47MYZwW4Az9/YhcPB1cXd" +
                                                    "C63NFde2iri0Ky5uqf8DaaO+NCMkTRilLxTdMTFeD3WiqFj7By4SDVhk02pX" +
                                                    "SWGNC6eWV5TRl3fG3dwyzEkdN8zPa2yeaaEda1BSQRU/IC7PQRw/c+knN/i7" +
                                                    "/V927Lp9dbeOMr556q+dEw/PTX+G2t0dwRQVeenA5bcf2So/FycVfjooeg8U" +
                                                    "Mg0UJoF6i8EDRp8oSAVdvkFbPYN2uQbtKlk/A7sFmTwuzjNexoIIlcFzAy3o" +
                                                    "kbWHycad/3vHRsQ202VqRQa7J6BY5kwFolTQ7MEu5VSLIchJM4ahMaoXFxQx" +
                                                    "8ZgPegNOboG21QW99Y5BxwrdtiOMJgv38OQBCk+t/ANCglEGj5gEazbOMv41" +
                                                    "w9KUQSOnK7YneGORYLEOj7hBI78mQGykF1rSBZi8K6tGzrcCXqg4PCG4j5cB" +
                                                    "dxI7KA3NAM6PYwFAJIM19W/CyW5oe1z999yx/mE1vlVm7Sx2pzlJgIoHc1lf" +
                                                    "S0F8Yk0Nxb3zS9BSroapu3WhTUVxMz5H0dD4vYIJMefK4HgOu2c5bGsxuGSy" +
                                                    "MHMpI1bOG6pyZ/AOQpt24U3fMbwKIbHCg9dWBO8QPSbO2KPoLaIYwS8htnP5" +
                                                    "DE7h+2VO4YfYnYdyrnqsvkFxZXlNwJgCyQ5op13Ap+/K4y6WWbuE3QWwFHjc" +
                                                    "IIWbzGyh042XuAjDTUE8VfEC3lH0wcv5SCNfXWmq3bDy6O/F48v/kFKXJrWZ" +
                                                    "nKaFL4ShcTX4S0YVitU510OnOl/lpHP1hzMUVbNA6SsO108hkKJc4Gv4L0z2" +
                                                    "GifrQmSQst1RmOgGZBogwuHrpuckCfH6wOtx0rke50moqBM3JXq/Cl5jWLfF" +
                                                    "J0Wvxuacj4pT8rWV/QeP3/7iy6JgV8kaXVxEKbVpUuM8RP06vXlVaZ6s6n3b" +
                                                    "Pml8tW6Ld/9oxK7FfX1GdOsu/UgbyppcPKsWX9/w2kMXV94Tr8T/AkJAFZHr" +
                                                    "FQAA");
}
