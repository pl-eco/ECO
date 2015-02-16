package org.sunflow.core;
import org.sunflow.SunflowAPI;
import org.sunflow.core.accel.NullAccelerator;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class Geometry implements RenderObject {
    private Tesselatable tesselatable;
    private PrimitiveList primitives;
    private AccelerationStructure accel;
    private int builtAccel;
    private int builtTess;
    private String acceltype;
    public Geometry(Tesselatable tesselatable) { super();
                                                 this.tesselatable = tesselatable;
                                                 primitives = null;
                                                 accel = null;
                                                 builtAccel = 0;
                                                 builtTess = 0;
                                                 acceltype = null; }
    public Geometry(PrimitiveList primitives) { super();
                                                tesselatable = null;
                                                this.primitives = primitives;
                                                accel = null;
                                                builtAccel = 0;
                                                builtTess = 1; }
    public boolean update(ParameterList pl, SunflowAPI api) { acceltype =
                                                                pl.
                                                                  getString(
                                                                    "accel",
                                                                    acceltype);
                                                              if (tesselatable !=
                                                                    null) {
                                                                  primitives =
                                                                    null;
                                                                  builtTess =
                                                                    0;
                                                              }
                                                              accel =
                                                                null;
                                                              builtAccel =
                                                                0;
                                                              if (tesselatable !=
                                                                    null)
                                                                  return tesselatable.
                                                                    update(
                                                                      pl,
                                                                      api);
                                                              return primitives.
                                                                update(
                                                                  pl,
                                                                  api);
    }
    int getNumPrimitives() { return primitives ==
                               null
                               ? 0
                               : primitives.
                               getNumPrimitives();
    }
    BoundingBox getWorldBounds(Matrix4 o2w) { if (primitives ==
                                                    null) {
                                                  BoundingBox b =
                                                    tesselatable.
                                                    getWorldBounds(
                                                      o2w);
                                                  if (b !=
                                                        null)
                                                      return b;
                                                  if (builtTess ==
                                                        0)
                                                      this.
                                                        tesselate();
                                                  if (primitives ==
                                                        null)
                                                      return null;
                                              }
                                              return primitives.
                                                getWorldBounds(
                                                  o2w);
    }
    void intersect(Ray r, IntersectionState state) {
        if (builtTess ==
              0)
            this.
              tesselate();
        if (builtAccel ==
              0)
            this.
              build();
        accel.
          intersect(
            r,
            state);
    }
    private synchronized void tesselate() { if (builtTess !=
                                                  0)
                                                return;
                                            if (tesselatable !=
                                                  null &&
                                                  primitives ==
                                                  null) {
                                                UI.
                                                  printInfo(
                                                    Module.
                                                      GEOM,
                                                    "Tesselating geometry ...");
                                                primitives =
                                                  tesselatable.
                                                    tesselate();
                                                if (primitives ==
                                                      null)
                                                    UI.
                                                      printError(
                                                        Module.
                                                          GEOM,
                                                        "Tesselation failed - geometry will be discarded");
                                                else
                                                    UI.
                                                      printDetailed(
                                                        Module.
                                                          GEOM,
                                                        "Tesselation produced %d primitives",
                                                        primitives.
                                                          getNumPrimitives());
                                            }
                                            builtTess =
                                              1;
    }
    private synchronized void build() { if (builtAccel !=
                                              0)
                                            return;
                                        if (primitives !=
                                              null) {
                                            int n =
                                              primitives.
                                              getNumPrimitives();
                                            if (n >=
                                                  1000)
                                                UI.
                                                  printInfo(
                                                    Module.
                                                      GEOM,
                                                    "Building acceleration structure for %d primitives ...",
                                                    n);
                                            accel =
                                              AccelerationStructureFactory.
                                                create(
                                                  acceltype,
                                                  n,
                                                  true);
                                            accel.
                                              build(
                                                primitives);
                                        }
                                        else {
                                            accel =
                                              new NullAccelerator(
                                                );
                                        }
                                        builtAccel =
                                          1;
    }
    void prepareShadingState(ShadingState state) {
        primitives.
          prepareShadingState(
            state);
    }
    PrimitiveList getBakingPrimitives() {
        if (builtTess ==
              0)
            this.
              tesselate();
        if (primitives ==
              null)
            return null;
        return primitives.
          getBakingPrimitives();
    }
    PrimitiveList getPrimitiveList() { return primitives;
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170609110000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVZe2wUxxmfu/Pbbv3ANmDABmMgYHyXttAHRk0MNXDhgKtt" +
       "DDEhZrw7d168t7vZ\nnbPPDkqDKgIhpS19SH0kQCtUIA1NpCSilfIgSkiToE" +
       "hJqiZqpJBGqA81pVVViRClf/Sbmd3bu70H\nNrY0s3M733zz/b7XfLN+4hoq" +
       "tUy0ULKCdNIgVnBjfxSbFpE3qtiyBuDVsHSptDJ6Zqum+5EvgvyK\nTFFtRL" +
       "JCMqY4pMih8De6UybqNHR1Mq7qNEhSNLhfXWvzuyuyNofhrhMXGg6eLmnzo9" +
       "IIqsWaplNM\nFV3rVUnCoqgush+P41CSKmoooli0O4I+R7RkYqOuWRRr1LoP" +
       "PYACEVRmSIwnRUsizuYh2DxkYBMn\nQnz7UJRvCxzmmIRiRSNyT3o7WLk6ey" +
       "WIba/ry6UGJhVschDgcAkA9eI0aoE2B6oRODv45QOnzgVQ\n7RCqVbR+xkwC" +
       "JBT2G0I1CZIYIabVI8tEHkL1GiFyPzEVrCpTfNch1GApcQ3TpEmsPmLp6jgj" +
       "bLCS\nBjH5ns7LCKqRGCYzKVHdTOsophBVdn6VxlQcB9jNLmwBdxN7DwCrFB" +
       "DMjGGJOEtKxhQNLN7mXZHG\n2LEVCGBpeYLQUT29VYmG4QVqELZUsRYP9VNT" +
       "0eJAWqonYReKWgoyZbo2sDSG42SYonleuqiYAqpK\nrgi2hKImLxnnBFZq8V" +
       "gpwz6dzdePnH30hTu5b5fIRFKZ/FWwqNWzqI/EiEk0iYiFN5LBH4XvTi70\n" +
       "IwTETR5iQdOz7MLOyN9fbBM0C/LQ7BjZTyQ6LG0/3tZ3/2YdBZgYFYZuKcz4" +
       "Wch5OETtme6UAVHb\nnObIJoPO5MW+V+9+8HHysR9VhVGZpKvJBPhRvaQnDE" +
       "Ul5maiERNTIodRJdHkjXw+jMphHAGXF293\nxGIWoWFUovJXZTr/DSqKAQum" +
       "okoYK1pMd8YGpqN8nDIQQuXQUBe0OiT++BPiLRiyklpM1SdClimF\ndDOe/i" +
       "3pJgltJjp4jTkZZF5jULQlNAovQljCmqLpobgCcSrpXTIZZ88Z8Eox2RomfD" +
       "6W7LxBq4K/\nb9FVmZjD0pmrbxzo3frwEeEQzIltVBTNhy2C9hZBtkXQ2QL5" +
       "fJxzI9tKGANUOQZBCemrZmX/3rv2\nHWkPgBcYEyWgBz+QtoP89v69kr7Rjd" +
       "wwT3ISuM+8X+45HLxx5g7hPqHCCTbv6uq3zl8+9d+aVX7k\nz5/9GC7Iv1WM" +
       "TZSlzHRW6/DGSz7+/zq67el3L39wmxs5FHXkBHTuShaQ7V4LmLpEZEhxLvvT" +
       "82sD\nu9DgcT8qgSiHzMblh6TR6t0jKzC7nSTHsJRHUHVMNxNYZVNOZqqio6" +
       "Y+4b7hrlHHx3PAONXMU9mg\n1XZd/mSzTQbrm4UrMWt7UPAkeuPbZbe/91z1" +
       "Ja4WJ9/WZpxo/YSK6K13nWXAJATef/CT6A9/fO3w\nHu4pwlV8FI655IiqSC" +
       "lYstxdAmGrQupghuzYqSV0WYkpeEQlzOP+V7vsC8/+87t1wjQqvHEsu/rm\n" +
       "DNz38zegBy/f+0krZ+OT2LHhwnDJBJo5Luce08STTI7UwXcW/fT3+DHIapBJ" +
       "LGWK8OTgE8i4xudS\ntCgnrAaIZREVygsQh2s7xGlX8T7IzME5ID73Jda1gw" +
       "SrCwRInqN8WDrweLw9ed/rv+PYqnFmTZBp\nrG3Y6Bb+wbqlzAZzvTG+BVuj" +
       "QLfm4vZ76tSLnwHHIeAowRFq7TAhp6SyTG1Tl5a//9LLzfveDiD/\nJlSl6l" +
       "jehHmUoEpwT2KNQjpKGXfcKZLnRIWTQlOIK6HFVgD/sTDXd7ts3+3K67usW+" +
       "5RqccorTlG\niZpKAo6Xce5hnM/Xi1hlA+u6+dQa1q0Xon7lpohSGb/KQN0r" +
       "C6e9Tay0cTPG8Mjqs5E3djzGTVow\n4eU52T18pl7YeeLGm/QK5+NmHrZ6SS" +
       "r39IBy0F371XfH68ueOpnwo/IhVCfZBesgVpMsvoegvrKc\nKhaK2qz57FpJ" +
       "FAbd6cy60Jv1Mrb15jz31IIxo2bjGk+aq2HaXg6t3naVeq+r+BAfRPiSDt6v" +
       "SCel\ncsNUxjErYlENzQjX6QW1yKBp59h2M+fYnC32MmgNttgNBcQeZN12iq" +
       "oMx2+tafq2R7hdMxTuNjsE\nnVDMJ9xeW7hSliZUR67lOXL1sGlWrIH79vNz" +
       "FM5Ij3z3zlC+BdAabfkaC8gnO8obSSoq5VJAKM7L\nvCQ6GmPn6NWH2p9/be" +
       "fJw6JQKRKxWauGpW99+OexwPdeGhHrvGHpIT7eevqvT1/taxSHmriVLM25\n" +
       "GGSuETcT7ve1BkvdS4rtwKlf6VzyxAN9V2yJGrLr6164g/5t8mWyYv2xj/KU" +
       "hwG4O3lsQ2ZomxZo\nTbZtmgrYxrRtU8ltM2C79ZhnZ2uGOy+C1mzv3Fxg5w" +
       "lnZ+611CmgwHPr+PnGEldQXPI80qSKSJNy\nT6OWmxUGfXAzIabIjcygiwpd" +
       "7LgxD+/+T81D+JW9fvtMSoDoVDe6VDJuB53YsJJxyirHt/GrrJvW\nj5779Q" +
       "X6duc64RarCju4d+Gqdaem2tY9+cgtFOFtHmxe1vXjC74ZGFVe8/Pbtjglcm" +
       "7p2Yu6s8+G\nKpAnaWoDWSfE4uxiohvaWtsv1nr9omAx4bevOYUTLoNKKDFZ" +
       "wnXImjPJ+sWzJxrm23ynSK3xA9Y9\nDIVy0pDhSOI0cUO4HCilfETXVYI11x" +
       "2PTrcY4T8OpTVS4sRor62R3rwayT0wbU75xT9ZZO4XrPsZ\nBFic0O3JRDph" +
       "iZh3Ef18Nog6oYVtROGCNn60eME4L9N4CUxHg9swpILUGs7hXBGM51n3K4o+" +
       "Dxh3\n6aYqb9CTmpw+rRfmMObzkGQ26ClXA2dmo4EvQttta2D3tDXg8fLG3H" +
       "SFJ53J9pzJMPvSZokrFPsq\nKG46F4oo6nnWPQNJTHGWel29ZFxXZFcnz96i" +
       "Tuazlyuh7bN1sm+afu5n4yiUhNakJsEtW4Mrn1zM\n9y8XmXuTdZdYzrYrR5" +
       "6lXnThvTobeKyG1G14+ozgse71YqDeKzL3J9a9A5UfO71lD6A/zMaHb4d2\n" +
       "0AZ08FajOPfI7R/FLNZcD/2oCLq/sO4KRXMMkxjYJJmLPVg/nA3WFdAO2VgP" +
       "TRtrpqTXisz9m3X/\nABSQkDZgqDTj0ZneIVycH88GZwe0YzbOY7eE89Mic5" +
       "+x7ro4XHKlnwnIT6Z9u6eowvloyr4azcv5\n/4n45i9F3r//nuuRP37KP/+l" +
       "v8tXR1BFLKmqmRfcjHEZuF1M4diqxXXXYA9fCWD0QoFkyR5MOl9A\nkJVTVJ" +
       "1BBpWDPcokqoJCH4jYsNrIU/86xWkmaoZ0aVbRyP9F5RR2SfFPqmFp9/k9i1" +
       "OPDHyfV4ul\nkoqnphibqggqF58x08XhkoLcHF5voaeeHHzuN19zil/+Aasx" +
       "I7Nl+d0aMVvEilCQ5v922JswKP/a\nN/Xbuc+sP3Piip9/vfw/AQnpaVkcAA" +
       "A=");
}
