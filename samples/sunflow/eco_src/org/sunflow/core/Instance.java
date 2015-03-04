package org.sunflow.core;
import org.sunflow.SunflowAPI;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class Instance implements RenderObject {
    private Matrix4 o2w;
    private Matrix4 w2o;
    private BoundingBox bounds;
    private Geometry geometry;
    private Shader[] shaders;
    private Modifier[] modifiers;
    public boolean update(ParameterList pl, SunflowAPI api) { String geometryName =
                                                                pl.
                                                                getString(
                                                                  "geometry",
                                                                  null);
                                                              if (geometry ==
                                                                    null ||
                                                                    geometryName !=
                                                                    null) {
                                                                  if (geometryName ==
                                                                        null) {
                                                                      UI.
                                                                        printError(
                                                                          Module.
                                                                            GEOM,
                                                                          "geometry parameter missing - unable to create instance");
                                                                      return false;
                                                                  }
                                                                  geometry =
                                                                    api.
                                                                      lookupGeometry(
                                                                        geometryName);
                                                                  if (geometry ==
                                                                        null) {
                                                                      UI.
                                                                        printError(
                                                                          Module.
                                                                            GEOM,
                                                                          "Geometry \"%s\" was not declared yet - instance is invalid",
                                                                          geometryName);
                                                                      return false;
                                                                  }
                                                              }
                                                              String[] shaderNames =
                                                                pl.
                                                                getStringArray(
                                                                  "shaders",
                                                                  null);
                                                              if (shaderNames !=
                                                                    null) {
                                                                  shaders =
                                                                    (new Shader[shaderNames.
                                                                                  length]);
                                                                  for (int i =
                                                                         0;
                                                                       i <
                                                                         shaders.
                                                                           length;
                                                                       i++) {
                                                                      shaders[i] =
                                                                        api.
                                                                          lookupShader(
                                                                            shaderNames[i]);
                                                                      if (shaders[i] ==
                                                                            null)
                                                                          UI.
                                                                            printWarning(
                                                                              Module.
                                                                                GEOM,
                                                                              "Shader \"%s\" was not declared yet - ignoring",
                                                                              shaderNames[i]);
                                                                  }
                                                              }
                                                              else {
                                                                  
                                                              }
                                                              String[] modifierNames =
                                                                pl.
                                                                getStringArray(
                                                                  "modifiers",
                                                                  null);
                                                              if (modifierNames !=
                                                                    null) {
                                                                  modifiers =
                                                                    (new Modifier[modifierNames.
                                                                                    length]);
                                                                  for (int i =
                                                                         0;
                                                                       i <
                                                                         modifiers.
                                                                           length;
                                                                       i++) {
                                                                      modifiers[i] =
                                                                        api.
                                                                          lookupModifier(
                                                                            modifierNames[i]);
                                                                      if (modifiers[i] ==
                                                                            null)
                                                                          UI.
                                                                            printWarning(
                                                                              Module.
                                                                                GEOM,
                                                                              "Modifier \"%s\" was not declared yet - ignoring",
                                                                              modifierNames[i]);
                                                                  }
                                                              }
                                                              Matrix4 transform =
                                                                pl.
                                                                getMatrix(
                                                                  "transform",
                                                                  o2w);
                                                              if (transform !=
                                                                    o2w) {
                                                                  o2w =
                                                                    transform;
                                                                  if (o2w !=
                                                                        null) {
                                                                      w2o =
                                                                        o2w.
                                                                          inverse();
                                                                      if (w2o ==
                                                                            null) {
                                                                          UI.
                                                                            printError(
                                                                              Module.
                                                                                GEOM,
                                                                              "Unable to compute transform inverse - determinant is: %g",
                                                                              o2w.
                                                                                determinant());
                                                                          return false;
                                                                      }
                                                                  }
                                                                  else
                                                                      o2w =
                                                                        (w2o =
                                                                           null);
                                                              }
                                                              return true;
    }
    public void updateBounds() { bounds =
                                   geometry.
                                     getWorldBounds(
                                       o2w);
    }
    public boolean hasGeometry(Geometry g) {
        return geometry ==
          g;
    }
    public void removeShader(Shader s) { if (shaders !=
                                               null) {
                                             for (int i =
                                                    0;
                                                  i <
                                                    shaders.
                                                      length;
                                                  i++)
                                                 if (shaders[i] ==
                                                       s)
                                                     shaders[i] =
                                                       null;
                                         }
    }
    public void removeModifier(Modifier m) {
        if (modifiers !=
              null) {
            for (int i =
                   0;
                 i <
                   modifiers.
                     length;
                 i++)
                if (modifiers[i] ==
                      m)
                    modifiers[i] =
                      null;
        }
    }
    public BoundingBox getBounds() { return bounds;
    }
    int getNumPrimitives() { return geometry.
                               getNumPrimitives();
    }
    void intersect(Ray r, IntersectionState state) {
        Ray localRay =
          r.
          transform(
            w2o);
        state.
          current =
          this;
        geometry.
          intersect(
            localRay,
            state);
        r.
          setMax(
            localRay.
              getMax());
    }
    public void prepareShadingState(ShadingState state) {
        geometry.
          prepareShadingState(
            state);
        if (state.
              getNormal() !=
              null &&
              state.
              getGeoNormal() !=
              null)
            state.
              correctShadingNormal();
        if (state.
              getModifier() !=
              null)
            state.
              getModifier().
              modify(
                state);
    }
    public Shader getShader(int i) { if (shaders ==
                                           null ||
                                           i <
                                           0 ||
                                           i >=
                                           shaders.
                                             length)
                                         return null;
                                     return shaders[i];
    }
    public Modifier getModifier(int i) { if (modifiers ==
                                               null ||
                                               i <
                                               0 ||
                                               i >=
                                               modifiers.
                                                 length)
                                             return null;
                                         return modifiers[i];
    }
    public Point3 transformObjectToWorld(Point3 p) {
        return o2w ==
          null
          ? new Point3(
          p)
          : o2w.
          transformP(
            p);
    }
    public Point3 transformWorldToObject(Point3 p) {
        return o2w ==
          null
          ? new Point3(
          p)
          : w2o.
          transformP(
            p);
    }
    public Vector3 transformNormalObjectToWorld(Vector3 n) {
        return o2w ==
          null
          ? new Vector3(
          n)
          : w2o.
          transformTransposeV(
            n);
    }
    public Vector3 transformNormalWorldToObject(Vector3 n) {
        return o2w ==
          null
          ? new Vector3(
          n)
          : o2w.
          transformTransposeV(
            n);
    }
    public Vector3 transformVectorObjectToWorld(Vector3 v) {
        return o2w ==
          null
          ? new Vector3(
          v)
          : o2w.
          transformV(
            v);
    }
    public Vector3 transformVectorWorldToObject(Vector3 v) {
        return o2w ==
          null
          ? new Vector3(
          v)
          : w2o.
          transformV(
            v);
    }
    PrimitiveList getBakingPrimitives() {
        return geometry.
          getBakingPrimitives();
    }
    Geometry getGeometry() { return geometry;
    }
    public Instance() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170616172000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVaDXAU1R1/l+8vSQghIEIiAUH5uIMIUohTCd/BC8QEAgY1" +
       "bvbeJQt7u+vuXnJE\narFaoFCtWttpqyI6zvAhfsyogx0o1VGp1ToVO9Wpo9" +
       "aOU3Wm2qm2Q6n9mP7/7+3e7u3eLSEnmdmX\n3X3v/d//9/9+b+/o56TY0Mlk" +
       "0Qib2zVqhJd3dQi6QWPLZcEwNsCrXvGV4vKOg9cqagEJRUmBFDNJ\ndVQ0Ij" +
       "HBFCJSLNK2oiWlk9maKm/vl1UzTFNmeKu80KK3NrrQR3DT/mO1tz9W1FhAiq" +
       "OkWlAU1RRM\nSVVWyjRhmKQmulUYFCJJU5IjUckwW6LkIqokE8tVxTAFxTRu" +
       "IbeRwigp0USkaZKpUXvxCCwe0QRd\nSETY8pEOtixQGKdTU5AUGmtNLwcz52" +
       "TOBLateZ3+0UCkDDu7AQ7jAFBfmkbN0fqgaoWHuq/aceBw\nIanuIdWS0oXE" +
       "REBiwno9pCpBE31UN1pjMRrrIWMVSmNdVJcEWRpmq/aQWkPqVwQzqVOjkxqq" +
       "PIgD\na42kRnW2pv0ySqpExKQnRVPV0zKKS1SO2U/FcVnoB9j1DmwOdxW+B4" +
       "AVEjCmxwWR2lOKtkkKaLzR\nOyONcfq1MACmliaoOaCmlypSBHhBarkuZUHp" +
       "j3SZuqT0w9BiNQmrmGRSTqIoa00Qtwn9tNckE73j\nOngXjCpngsApJhnvHc" +
       "YogZYmebTk0s/s+jN7Dj14cimz7aIYFWXkvwImNXgmddI41akiUj7xbDJ8\n" +
       "f9v1yckFhMDg8Z7BfEzrZcc2Rj/9ZSMfc0mWMev7tlLR7BXX3dvYeetqlRQi" +
       "G2Waakio/AzkzB06\nrJ6WlAZeW5+miJ1hu/OFzlPX7zxC/1JAKtpIiajKyQ" +
       "TY0VhRTWiSTPXVVKG6YNJYGymnSmw5628j\npXAfBZPnb9fH4wY120iRzF6V" +
       "qOwZRBQHEiiicriXlLhq32uCOcDuUxohpBQuMheuGsL/2H/wt3DE\nSCpxWR" +
       "2KGLoYUfX+9LOo6jTSxjxcpGG0Gs0kayIDaoJGBFFQJEWN9Evgp6I6N0YH8f" +
       "950Eohb7VD\noRAGO6/TymDva1Q5RvVe8eBHr+1Yee339nCDQCO2UJnkYlgi" +
       "bC0RxiXC9hIkFGKU63AprgwQ5TZw\nSghfVVd03bj25j1NhWAF2lARyAGHNg" +
       "H/1vorRXW547k2zV5x4qNbdofPHryGm08kd4DNOrvyzSde\nP/D3qlkFpCB7" +
       "9ENcEH8rkEwHhsx0VJvu9Zds9P+6t/2Zt19//3LHc0wy3efQ/pnokE1eDeiq" +
       "SGMQ\n4hzyj11cXbiJdN9bQIrAyyGyMf4haDR418hwzBY7yCGW0iipjKt6Qp" +
       "Cxy45MFeaArg45b5hp1GBT\nx60EFelhkMXHs3eUzHvnROUrDLEdSqtdyaqL" +
       "mtwxxzp2sEGnFN6//5OOH/7o891bmBFYVmBCBkv2\nyZKYgikznCngkTJEBd" +
       "TR9I1KQo1JcUnokyka03+qL5v/3Gd313Cpy/DGVtqccxNw3l+8jOx8/aZ/\n" +
       "NjAyIREzggPDGcbRjHMot+q6sB35SN3+1pSf/kp4CAIWBAlDGqbM7wlDRpgc" +
       "w0y8V7B2rqdvHjZN\nQHtODqvOkn97xR1H+puSt/z654zrSsGdyN1qaBe0Fq" +
       "5UtvY4WDRMrCYjHmHveA3belTBBK/3rhGM\nASC24IV1N9TIL/wblu2BZUVI" +
       "jsZ6HaJFKkPT1uji0ndffKn+5tOFpGAVqZBVIbZKYPZPysHwqDEA\ngSalXb" +
       "OUszFUZjOTIozbSZaUUq6nEmDuitzuvwpTvOM5vX1zDkVfW/8Qk1JOx8+S4T" +
       "x0hk9u3H/2\nDfMDRsfxQJw9NeWPolAWOXO/8fbg2JKnH04UkNIeUiNahVu3" +
       "ICfRGXqgzjDsag6Ku4z+zJqBJ8iW\ndISZ7PV+17Je33eiN9zjaLyv8lhGFU" +
       "p7IlxjLcsY67WMEGE317Ap01g7I+3BpZouDQpYzJFCtXmI\n9U6AvO1OFQlI" +
       "jeF2Aaqf1AIeYLCdj81SruaFOc1hiZ/RWovR2hyMtmHTCvwMNavny8/a8+Sn" +
       "Ea5x\nFj/jcvDTYfFT0qcmlZhhszTZx9Iy7IcKcZma8rB13Xmy1QBXncVWXQ" +
       "62NllslfVTKDFMfbvNmD/N\nr7ZGeLjafJ5cTYFrvMXV+Bxc3WRxVWoMCBBh" +
       "DMxFrr0Zi7+Yug7fs2Jc5+Itd7DsXg7bBcFY51g7\nbNLwLgRuelnuuJEm1i" +
       "vOvPHY3148SWeyEFcmGeBVrXp/ltLZNecL4Qhtfye+n2Xooj7B4P7l3XP4\n" +
       "txQZOwWm6jGZgqoNEpStpgk+NXVxkdn5JmTVWkwT2FBbyJLflQtMiB2SIrDy" +
       "/3Lw5hKZKv3mAMh/\nontvrEsJqLEHWa3x0a6mX7y68eHdvD4LCNAZs3rFb/" +
       "/xw22FP3ixj8/zRmHP4HsbHvv4mY8663jC\n55uxab79kHsO35AxuVZrmNem" +
       "Bq3ARr88e+rR2zo/sDiqzdxWrISt9yfbX6Izr77rT1mq4kLQLz7E\ntFRa9A" +
       "VcpraqeAGB8Ry2XqpCsRax+3jJLKnh9LYXOlM+JepkSkbB3M5MyEk4ew8/fs" +
       "w8PXsJRzAr\nty68E2ctOTDcuOSpfaMokxs9cvWSHjt4yXWFA9KrBWw/zPOX" +
       "bx+dOaklM2tVAD9JXdmQkbsu5bbM\nZJ4lJ3EDDqi9vhvQtxub28EXRNQTVy" +
       "vIvjF7bbkyoZmsGhx+fsKzVx/c/wFKX0vBPq+CRaTmKxct\nbIbpteBBeFoU" +
       "lmJWkbHirVV9R+LKkRiTTQULGK04xQJezt64Ilqhqhm4HXadO1mUpq/XDNxT" +
       "XORa\npG3FjqfXVpU9sm8Xo2+Fw3LX1tp6Lh0U9HVua65kjM+ft/iqxc0m6f" +
       "y6tp9L5l+1YM78hXObgWjV\nhjVtXWHDCVb7nHB/G2zq/cJCiMTJ+gB2jONR" +
       "6J7uTgBR1LmydYUnWfWeZ7KaCle9RbY+R7L6mZWs\nyvkWA9MVvhEyKdUFUc" +
       "qddNttmjnj+X6bj0ezxHO8t4I5tglsWO36kD9O4SMbNewPPPj8Lb4am43N\n" +
       "dwI86HBA3+PYHMRmD+cE272Z/rKAzfy+yxIXzft6LXHRImaJC0wyhlliwi3o" +
       "o25t4osfe+zogQA7\nSjnCmeQSH6h3ik+9nVQBg+flPSapKbnO6FiC2r35i6" +
       "pdwss3FliSNIFNU9XmynSQyq4Fx3F9pQ2w\nEhlrIbxwJvZ/twEyeNmU6slh" +
       "DT4ELBlQqG0wAtrD6t3Duvj/1o42tszJANN4GZvjUHYkNXB+nh0V\ny1Ih8J" +
       "X2qapMBcXRw4lz+bO9kcwmEayP2y2JtI9YIm5+fxvQdxqb30Cg41iWOWW/C1" +
       "HRoCrFHDhv\n5ANnFlybLTibRwwnlGmiuct+RuK9AMAfYvMHcNkBwXDvFU45" +
       "+N7NB98MuAQLnzBafLnqZUbg0wB0\nn2HzZ1CnThPqIOWz8N3vHHgf56s+1Y" +
       "Knfn3qsxMII/GPAIBnsfkCwiEHaM/zQPwyH4hNcO2wIO4Y\njcOFSEAfS2D/" +
       "hZDYT81lI99kO9j+N0ps7OUkuHZa2HZmxeZPzySgQA2NCejDc9pQuUlqAOq6" +
       "ZCK9\nlWGD9TSiUEU+iJrhutNCdGcubYUqgxNGnT/lCekzhqYsnxIgkxi8ss" +
       "aPhKzAD00MEEUDNuNB65I9\nNdNiQ/X5WOwiuO6zZHDfaJ3Sn/YxeoD1OQhn" +
       "BCCchc002D9qOtUEnbone7BOz9c7H7GwPnK+WJnh\nMXabA6DgQVsowl3UdU" +
       "5xrnMMhm1evrnjSQvbk6PH9s0AbEuxaYH0B9gyqvZz1/UM39X54ENfPW7h\n" +
       "Oz5aO53gC5MdKnjVlQze2gDo67BZDWWfqUNxht+aeEW7Qd2k6tYZWAB9RwZr" +
       "8pXBKUsGpy6IDDYH\nyOAGbDa6ZcCwb1C5KEYug+58ZLAErtOWDE6PVgb+Y/" +
       "Juit9uuBDiAULYio0ICTcthHXsw2NWc8i5\njCOLWL6yeM+SxXsXRhZmgCxY" +
       "c4tfFlnNYgSy0POVxSeWLD65MLIIKtt2YfNttyz4zNHaxc58ZfGl\nJYsvL4" +
       "ws7gmQxf3Y3OWXxWjt4u58aryZcJ2xZHEmlyx8NZ4bzv6AvgPYPACVC1bkwj" +
       "YoWpxK1UaY\n5UTBHpM+UWA4H8y3Ov/KwvnVqHAeCeg7is1BnvpXj/g7mgPt" +
       "0EihpUxSZp9j4Yn0RN8P7/iPxcTo\nu7fecCb6+3/xr1L2D7oqo6QsnpRl9x" +
       "dh130JFJhxiYmjkn8f1hi852Cj4UVhkiL8h9yFnuXDngcB\nuIbhcS6/cw86" +
       "jl+I9X68PaHZMqpxznHtozA3akQ6LeNbBvtto/29Icl/3dgrbn5iy6WpfRvu" +
       "YR8x\nikVZGB5GMhVRUsp//8Ko4jeLqTmp2bTeJE8/1X3iycX2UVvGL2N81j" +
       "af9+bWInbs1f4PX6cvgmcq\nAAA=");
}
