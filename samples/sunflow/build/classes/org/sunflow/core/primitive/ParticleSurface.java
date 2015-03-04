package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.core.ParameterList.FloatParameter;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Solvers;
import org.sunflow.math.Vector3;
public class ParticleSurface implements PrimitiveList {
    private float[] particles;
    private float r;
    private float r2;
    private int n;
    public ParticleSurface() { super();
                               particles = null;
                               r = (r2 = 1);
                               n = 0; }
    public int getNumPrimitives() { return n; }
    public float getPrimitiveBound(int primID, int i) { float c = particles[primID *
                                                                              3 +
                                                                              (i >>>
                                                                                 1)];
                                                        return (i &
                                                                  1) ==
                                                          0
                                                          ? c -
                                                          r
                                                          : c +
                                                          r;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) {
        BoundingBox bounds =
          new BoundingBox(
          );
        for (int i =
               0,
               i3 =
                 0;
             i <
               n;
             i++,
               i3 +=
                 3)
            bounds.
              include(
                particles[i3],
                particles[i3 +
                            1],
                particles[i3 +
                            2]);
        bounds.
          include(
            bounds.
              getMinimum(
                ).
              x -
              r,
            bounds.
              getMinimum(
                ).
              y -
              r,
            bounds.
              getMinimum(
                ).
              z -
              r);
        bounds.
          include(
            bounds.
              getMaximum(
                ).
              x +
              r,
            bounds.
              getMaximum(
                ).
              y +
              r,
            bounds.
              getMaximum(
                ).
              z +
              r);
        return o2w ==
          null
          ? bounds
          : o2w.
          transform(
            bounds);
    }
    public void intersectPrimitive(Ray r, int primID,
                                   IntersectionState state) {
        int i3 =
          primID *
          3;
        float ocx =
          r.
            ox -
          particles[i3 +
                      0];
        float ocy =
          r.
            oy -
          particles[i3 +
                      1];
        float ocz =
          r.
            oz -
          particles[i3 +
                      2];
        float qa =
          r.
            dx *
          r.
            dx +
          r.
            dy *
          r.
            dy +
          r.
            dz *
          r.
            dz;
        float qb =
          2 *
          (r.
             dx *
             ocx +
             r.
               dy *
             ocy +
             r.
               dz *
             ocz);
        float qc =
          ocx *
          ocx +
          ocy *
          ocy +
          ocz *
          ocz -
          r2;
        double[] t =
          Solvers.
          solveQuadric(
            qa,
            qb,
            qc);
        if (t !=
              null) {
            if (t[0] >=
                  r.
                  getMax(
                    ) ||
                  t[1] <=
                  r.
                  getMin(
                    ))
                return;
            if (t[0] >
                  r.
                  getMin(
                    ))
                r.
                  setMax(
                    (float)
                      t[0]);
            else
                r.
                  setMax(
                    (float)
                      t[1]);
            state.
              setIntersection(
                primID,
                0,
                0);
        }
    }
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
        Point3 localPoint =
          state.
          getInstance(
            ).
          transformWorldToObject(
            state.
              getPoint(
                ));
        localPoint.
          x -=
          particles[3 *
                      state.
                      getPrimitiveID(
                        ) +
                      0];
        localPoint.
          y -=
          particles[3 *
                      state.
                      getPrimitiveID(
                        ) +
                      1];
        localPoint.
          z -=
          particles[3 *
                      state.
                      getPrimitiveID(
                        ) +
                      2];
        state.
          getNormal(
            ).
          set(
            localPoint.
              x,
            localPoint.
              y,
            localPoint.
              z);
        state.
          getNormal(
            ).
          normalize(
            );
        state.
          setShader(
            state.
              getInstance(
                ).
              getShader(
                0));
        state.
          setModifier(
            state.
              getInstance(
                ).
              getModifier(
                0));
        Vector3 worldNormal =
          state.
          getInstance(
            ).
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
    public boolean update(ParameterList pl, SunflowAPI api) {
        FloatParameter p =
          pl.
          getPointArray(
            "particles");
        if (p !=
              null)
            particles =
              p.
                data;
        r =
          pl.
            getFloat(
              "radius",
              r);
        r2 =
          r *
            r;
        n =
          pl.
            getInt(
              "num",
              n);
        return particles !=
          null &&
          n <=
          particles.
            length /
          3;
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAALUYbWwUx3XubPyF8ScYxzXGGDsqht6VCFpRR2ltywQTA5ZN" +
       "qDAqznhv7rywt7vsztmHE5cEtTLiB6oah5KK+EcETUkdiKqitIpS+UfTEFGl" +
       "SlS16o+Gtn8alSKVH02jpm363sx+3d757LTqSTM3O/Pem/fmfc4s3iNrbIts" +
       "Nw3tdEozeIxleeyEtjvGT5vMju0f2j1MLZsl+jVq24dhblzpeLX2w4+/NVkX" +
       "JWVjpJHqusEpVw3dHmG2oU2xxBCp9WcHNJa2OakbOkGnaDzDVS0+pNq8Z4is" +
       "DaBy0jnkshAHFuLAQlywEO/1oQBpHdMz6X7EoDq3T5Gvk8gQKTMVZI+TLblE" +
       "TGrRtENmWEgAFCrw+wgIJZCzFmn3ZJcy5wn83Pb4/HeO1/2whNSOkVpVH0V2" +
       "FGCCwyZjpDrN0hPMsnsTCZYYI/U6Y4lRZqlUU2cE32OkwVZTOuUZi3mHhJMZ" +
       "k1liT//kqhWUzcoo3LA88ZIq0xLu15qkRlMga5Mvq5RwL86DgFUqMGYlqcJc" +
       "lNKTqp7gZHMYw5Ox8zEAANTyNOOThrdVqU5hgjRI3WlUT8VHuaXqKQBdY2Rg" +
       "F05aliWKZ21S5SRNsXFOmsNww3IJoCrFQSAKJxvCYIISaKklpKWAfu4dfPjC" +
       "k/o+PSp4TjBFQ/4rAKkthDTCksxiusIkYnX30EXa9Ma5KCEAvCEELGFee+r+" +
       "V3a0Ld2SMJ8pAHNo4gRT+LhyZaLm3db+bXtKkI0K07BVVH6O5ML8h52VnqwJ" +
       "ntfkUcTFmLu4NPLzo0+/zO5GSdUgKVMMLZMGO6pXjLSpasx6lOnMopwlBkkl" +
       "0xP9Yn2QlMN4SNWZnD2UTNqMD5JSTUyVGeIbjigJJPCIymGs6knDHZuUT4px" +
       "1iSElEMju6HVE/kT/5wcj08aaRanCtVV3YiD7TJqKZNxphhxm6ZNDbRmZ/Sk" +
       "ZkzHbUuJG1bK+1YMi8VNS02DkFMMbMACT9LYaEbYawztzPy/75BFGeumIxE4" +
       "/taw82sAtc/QEswaV+YzfQP3r4/fjnrO4JwOJ92wZ8zZM4Z7xrw9Y6E9SSQi" +
       "tlqPe0stg45OgrdDHKzeNvq1/U+c6ygB8zKnS+GAEbQDJHUYGlCMfj8kDIrA" +
       "p4BdNr94bC720UtflnYZXz5+F8QmS5emnzly5vNREs0NxCggTFUh+jCGTy9M" +
       "doYdsBDd2rkPPrxxcdbwXTEnsjsRIh8TPbwjrArLUFgCYqZPvrud3hx/Y7Yz" +
       "SkohbECo5BRMG6JQW3iPHE/vcaMmyrIGBE4aVppquOSGuio+aRnT/oywkRox" +
       "RrNfi6bfAq3J8QXxj6uNJvbrpU2hlkNSiKi89ydLz9/87vY90WAArw2kxFHG" +
       "ZTio943ksMUYzP/u0vCzz92bOyYsBCC2FtqgE/t+CA6gMjjWb9469ds771/5" +
       "VdS3Kg5ZMjOhqUoWaDzo7wKhQ4PwhbrvfFxPGwk1qdIJjaFx/rO2a+fNv1yo" +
       "k9rUYMY1hh0rE/DnH+gjT98+/vc2QSaiYOryJffB5AE0+pR7LYueRj6yz7y3" +
       "6fm36AsQWSGa2eoMEwGKCMmIOPq4UFW36GOhtZ3YtZt5a1kx0yy+SmHrbcs7" +
       "0V7MwAHn+8chbeLsHz8SEuW5T4HEE8Ifiy9ebul/5K7A9+0YsTdn84MSVCs+" +
       "7kMvp/8W7Sh7M0rKx0id4pRCR6iWQWsZg/Rvu/URlEs567mpXOatHs9PW8M+" +
       "FNg27EF+MIQxQuO4KuQ01XjKbdAaHKdpCDtNhIjBHoHSIfou7D7r2mw5RNUp" +
       "inUWqTSdsGqDqrqWV5WwGpm7F7639Z0zC1v/AMc8RipUGwTqtVIFiokAzl8X" +
       "79x9b92m6yLElE5QW4oWrsLyi6yc2kmcRLWZLW5Vw27KkFvHZxvunLz8wSsy" +
       "pIdNKATMzs2f/yR2YT4aKPC25tVYQRxZ5AnO1kkdfQK/CLR/Y0Pd4IRM8g39" +
       "TqXR7pUaphBnSzG2xBZ7/3Rj9vXvz85JMRpy65sBKN9f+fW/fhG79Pu3C6RV" +
       "MDKDcs9TI05GxO9dpmsuBwqbSxSH25CGqlMNLKZMY3qKTwq4Puz2So/fx0kJ" +
       "qA+HvWbW2ywqqYjvDdwJROgpUGsaOsOY5q7JVK4aMa/Oh8VsHtsW2ZSTyA8I" +
       "C/Fd+fy1H7zG393+JXlS3csbShjxrbN/bjn8yOQTnyJ9bw7pLUzy2oHFtx99" +
       "UPl2lJR4ESHvwpCL1JMbB6osBjcc/XBONGgzxV8vdp1FYjQtsqZgB1eINQrq" +
       "QaoNznZz4Rw0kDa5yBozP974o4dfWnhfJMGsiDt1Mgnsyg1RzdAanRDVuEyI" +
       "SmHXw0lEBOmB/43YCYdY1HpoBWoYMdc71NYvQy3tsqbjYEQSy/oH3hxwIrDd" +
       "trzK1XNhkWvRape7awn/vnJ2fiFx6OrOqKOeQYjN3DA/p7EppgV2LBfjY55A" +
       "tcj/ZmitjkCtBWuolSxlpsjaU9hNc1KXYvxgJu0JJoBH8iuAEIfigtMBrd3h" +
       "sH3VHEb9ECQ0MCJAzxbh9RvYneGkHnj1GO0zMnpC2MSKzG7EyS6nuePVMRvJ" +
       "tYjmoEWk4Q4YO0Dhmp/dJSicLyLDBezmOKkBGb5qWFpC8G+7hFvzCIt1VU/1" +
       "GdkVBRQ190FoMUfA2KoFLBEUS7xwnWfyI/S00JIL0ZEHMYj53ZZBBR98mNjt" +
       "YpHDuIzds5A6VRc11/5CKah0ylATqzuEL0Lrdw6h/7/V8qY8EUcnKerCl+5q" +
       "EemuYfcipEXTYlCJsSAyLi2sKIqIhg9AO+qIcvTTeleREIa5jsGxYwhzwZqC" +
       "YKPyv3d4UGxzo4ioN7FbhPohYyZAukLKK58wDI1RfUWhUV6yA5riCK2sWugg" +
       "Sz8tsraE3eugGXDCPgqVYCrX7k4VuPnA7TP0SoEZtTnvJVS+3inXF2orNi48" +
       "/htZFLsvbJVDpCKZ0bTgXSAwLgNLSaqCxUp5M5BZ6U1OWpZ/PcFCP4f9n0ms" +
       "WxDVw1jgRPgXBLvNydoAGGjKGQWB3oECEIBw+EvTtZY6v96TN6MsCSQz4sQr" +
       "9yvnIo71mnhrdmurjHxtHlduLOw/+OT9L1wVhRrUL3RmBqlUwHVBvkF49dmW" +
       "Zam5tMr2bfu45tXKLjfv1mDX4Dw8BHnDcfI/yWiq+NkXAAA=");
}
