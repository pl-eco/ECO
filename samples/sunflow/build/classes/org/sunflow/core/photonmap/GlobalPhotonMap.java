package org.sunflow.core.photonmap;
import java.util.ArrayList;
import org.sunflow.core.GlobalPhotonMapInterface;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public final class GlobalPhotonMap implements GlobalPhotonMapInterface {
    private ArrayList<Photon> photonList;
    private Photon[] photons;
    private int storedPhotons;
    private int halfStoredPhotons;
    private int log2n;
    private int numGather;
    private float gatherRadius;
    private BoundingBox bounds;
    private boolean hasRadiance;
    private float maxPower;
    private float maxRadius;
    private int numEmit;
    public GlobalPhotonMap(int numEmit, int numGather, float gatherRadius) {
        super(
          );
        this.
          numEmit =
          numEmit;
        this.
          numGather =
          numGather;
        this.
          gatherRadius =
          gatherRadius;
        bounds =
          new BoundingBox(
            );
        hasRadiance =
          false;
        maxPower =
          0;
        maxRadius =
          0;
    }
    public void prepare(BoundingBox sceneBounds) { photonList = new ArrayList<Photon>(
                                                                  );
                                                   photonList.add(
                                                                null);
                                                   photons = null;
                                                   storedPhotons =
                                                     (halfStoredPhotons =
                                                        0); }
    public void store(ShadingState state, Vector3 dir, Color power,
                      Color diffuse) { Photon p = new Photon(state.
                                                               getPoint(
                                                                 ),
                                                             state.
                                                               getNormal(
                                                                 ),
                                                             dir,
                                                             power,
                                                             diffuse);
                                       synchronized (this)  { storedPhotons++;
                                                              photonList.
                                                                add(
                                                                  p);
                                                              bounds.
                                                                include(
                                                                  new Point3(
                                                                    p.
                                                                      x,
                                                                    p.
                                                                      y,
                                                                    p.
                                                                      z));
                                                              maxPower =
                                                                Math.
                                                                  max(
                                                                    maxPower,
                                                                    power.
                                                                      getMax(
                                                                        ));
                                       } }
    private void locatePhotons(NearestPhotons np) { float[] dist1d2 =
                                                      new float[log2n];
                                                    int[] chosen =
                                                      new int[log2n];
                                                    int i = 1;
                                                    int level = 0;
                                                    int cameFrom;
                                                    while (true) {
                                                        while (i <
                                                                 halfStoredPhotons) {
                                                            float dist1d =
                                                              photons[i].
                                                              getDist1(
                                                                np.
                                                                  px,
                                                                np.
                                                                  py,
                                                                np.
                                                                  pz);
                                                            dist1d2[level] =
                                                              dist1d *
                                                                dist1d;
                                                            i +=
                                                              i;
                                                            if (dist1d >
                                                                  0.0F)
                                                                i++;
                                                            chosen[level++] =
                                                              i;
                                                        }
                                                        np.
                                                          checkAddNearest(
                                                            photons[i]);
                                                        do  {
                                                            cameFrom =
                                                              i;
                                                            i >>=
                                                              1;
                                                            level--;
                                                            if (i ==
                                                                  0)
                                                                return;
                                                        }while(dist1d2[level] >=
                                                                 np.
                                                                   dist2[0] ||
                                                                 cameFrom !=
                                                                 chosen[level]); 
                                                        np.
                                                          checkAddNearest(
                                                            photons[i]);
                                                        i =
                                                          chosen[level++] ^
                                                            1;
                                                    } }
    private void balance() { if (storedPhotons == 0) return;
                             photons = photonList.toArray(
                                                    new Photon[photonList.
                                                                 size(
                                                                   )]);
                             photonList = null;
                             Photon[] temp = new Photon[storedPhotons +
                                                          1];
                             balanceSegment(temp, 1, 1, storedPhotons);
                             photons = temp;
                             halfStoredPhotons = storedPhotons /
                                                   2;
                             log2n = (int) Math.ceil(Math.
                                                       log(
                                                         storedPhotons) /
                                                       Math.
                                                       log(
                                                         2.0));
    }
    private void balanceSegment(Photon[] temp, int index,
                                int start,
                                int end) { int median = 1;
                                           while (4 * median <=
                                                    end -
                                                    start +
                                                    1) median +=
                                                         median;
                                           if (3 * median <=
                                                 end -
                                                 start +
                                                 1) { median +=
                                                        median;
                                                      median +=
                                                        start -
                                                          1;
                                           }
                                           else
                                               median =
                                                 end -
                                                   median +
                                                   1;
                                           int axis = Photon.
                                                        SPLIT_Z;
                                           Vector3 extents =
                                             bounds.
                                             getExtents(
                                               );
                                           if (extents.x >
                                                 extents.
                                                   y &&
                                                 extents.
                                                   x >
                                                 extents.
                                                   z) axis =
                                                        Photon.
                                                          SPLIT_X;
                                           else
                                               if (extents.
                                                     y >
                                                     extents.
                                                       z)
                                                   axis =
                                                     Photon.
                                                       SPLIT_Y;
                                           int left = start;
                                           int right = end;
                                           while (right >
                                                    left) {
                                               double v =
                                                 photons[right].
                                                 getCoord(
                                                   axis);
                                               int i =
                                                 left -
                                                 1;
                                               int j =
                                                 right;
                                               while (true) {
                                                   while (photons[++i].
                                                            getCoord(
                                                              axis) <
                                                            v) {
                                                       
                                                   }
                                                   while (photons[--j].
                                                            getCoord(
                                                              axis) >
                                                            v &&
                                                            j >
                                                            left) {
                                                       
                                                   }
                                                   if (i >=
                                                         j)
                                                       break;
                                                   swap(
                                                     i,
                                                     j);
                                               }
                                               swap(
                                                 i,
                                                 right);
                                               if (i >=
                                                     median)
                                                   right =
                                                     i -
                                                       1;
                                               if (i <=
                                                     median)
                                                   left =
                                                     i +
                                                       1;
                                           }
                                           temp[index] = photons[median];
                                           temp[index].setSplitAxis(
                                                         axis);
                                           if (median > start) {
                                               if (start <
                                                     median -
                                                     1) {
                                                   float tmp;
                                                   switch (axis) {
                                                       case Photon.
                                                              SPLIT_X:
                                                           tmp =
                                                             bounds.
                                                               getMaximum(
                                                                 ).
                                                               x;
                                                           bounds.
                                                             getMaximum(
                                                               ).
                                                             x =
                                                             temp[index].
                                                               x;
                                                           balanceSegment(
                                                             temp,
                                                             2 *
                                                               index,
                                                             start,
                                                             median -
                                                               1);
                                                           bounds.
                                                             getMaximum(
                                                               ).
                                                             x =
                                                             tmp;
                                                           break;
                                                       case Photon.
                                                              SPLIT_Y:
                                                           tmp =
                                                             bounds.
                                                               getMaximum(
                                                                 ).
                                                               y;
                                                           bounds.
                                                             getMaximum(
                                                               ).
                                                             y =
                                                             temp[index].
                                                               y;
                                                           balanceSegment(
                                                             temp,
                                                             2 *
                                                               index,
                                                             start,
                                                             median -
                                                               1);
                                                           bounds.
                                                             getMaximum(
                                                               ).
                                                             y =
                                                             tmp;
                                                           break;
                                                       default:
                                                           tmp =
                                                             bounds.
                                                               getMaximum(
                                                                 ).
                                                               z;
                                                           bounds.
                                                             getMaximum(
                                                               ).
                                                             z =
                                                             temp[index].
                                                               z;
                                                           balanceSegment(
                                                             temp,
                                                             2 *
                                                               index,
                                                             start,
                                                             median -
                                                               1);
                                                           bounds.
                                                             getMaximum(
                                                               ).
                                                             z =
                                                             tmp;
                                                   }
                                               }
                                               else
                                                   temp[2 *
                                                          index] =
                                                     photons[start];
                                           }
                                           if (median < end) {
                                               if (median +
                                                     1 <
                                                     end) {
                                                   float tmp;
                                                   switch (axis) {
                                                       case Photon.
                                                              SPLIT_X:
                                                           tmp =
                                                             bounds.
                                                               getMinimum(
                                                                 ).
                                                               x;
                                                           bounds.
                                                             getMinimum(
                                                               ).
                                                             x =
                                                             temp[index].
                                                               x;
                                                           balanceSegment(
                                                             temp,
                                                             2 *
                                                               index +
                                                               1,
                                                             median +
                                                               1,
                                                             end);
                                                           bounds.
                                                             getMinimum(
                                                               ).
                                                             x =
                                                             tmp;
                                                           break;
                                                       case Photon.
                                                              SPLIT_Y:
                                                           tmp =
                                                             bounds.
                                                               getMinimum(
                                                                 ).
                                                               y;
                                                           bounds.
                                                             getMinimum(
                                                               ).
                                                             y =
                                                             temp[index].
                                                               y;
                                                           balanceSegment(
                                                             temp,
                                                             2 *
                                                               index +
                                                               1,
                                                             median +
                                                               1,
                                                             end);
                                                           bounds.
                                                             getMinimum(
                                                               ).
                                                             y =
                                                             tmp;
                                                           break;
                                                       default:
                                                           tmp =
                                                             bounds.
                                                               getMinimum(
                                                                 ).
                                                               z;
                                                           bounds.
                                                             getMinimum(
                                                               ).
                                                             z =
                                                             temp[index].
                                                               z;
                                                           balanceSegment(
                                                             temp,
                                                             2 *
                                                               index +
                                                               1,
                                                             median +
                                                               1,
                                                             end);
                                                           bounds.
                                                             getMinimum(
                                                               ).
                                                             z =
                                                             tmp;
                                                   }
                                               }
                                               else
                                                   temp[2 *
                                                          index +
                                                          1] =
                                                     photons[end];
                                           } }
    private void swap(int i, int j) { Photon tmp = photons[i];
                                      photons[i] = photons[j];
                                      photons[j] = tmp; }
    static class Photon {
        float x;
        float y;
        float z;
        short dir;
        short normal;
        int data;
        int power;
        int flags;
        static final int SPLIT_X = 0;
        static final int SPLIT_Y = 1;
        static final int SPLIT_Z = 2;
        static final int SPLIT_MASK = 3;
        Photon(Point3 p, Vector3 n, Vector3 dir, Color power,
               Color diffuse) { super();
                                x = p.x;
                                y = p.y;
                                z = p.z;
                                this.dir = dir.encode();
                                this.power = power.toRGBE(
                                                     );
                                flags = 0;
                                normal = n.encode();
                                data = diffuse.toRGB(); }
        void setSplitAxis(int axis) { flags &= ~SPLIT_MASK;
                                      flags |= axis; }
        float getCoord(int axis) { switch (axis) { case SPLIT_X:
                                                       return x;
                                                   case SPLIT_Y:
                                                       return y;
                                                   default:
                                                       return z;
                                   } }
        float getDist1(float px, float py, float pz) { switch (flags &
                                                                 SPLIT_MASK) {
                                                           case SPLIT_X:
                                                               return px -
                                                                 x;
                                                           case SPLIT_Y:
                                                               return py -
                                                                 y;
                                                           default:
                                                               return pz -
                                                                 z;
                                                       } }
        float getDist2(float px, float py, float pz) { float dx =
                                                         x -
                                                         px;
                                                       float dy =
                                                         y -
                                                         py;
                                                       float dz =
                                                         z -
                                                         pz;
                                                       return dx *
                                                         dx +
                                                         dy *
                                                         dy +
                                                         dz *
                                                         dz;
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVYe2wUxxmfO7+NYx8mgOuCAWOiGNLbOihRqSNa5zBgOLCL" +
                                                        "HdpcRI713pxv8b7YnbMPJ24DUmuaqihKTUraxIpaUF4QojaIPkTlPHgkEFWp" +
                                                        "qlaN1BDln0ak/MEfJFHTNv2+2d3bu70HpqrUk3ZuduZ7zjffb77Z41dJlWWS" +
                                                        "NYau7BtRdBamGRbeo9wVZvsMaoW3RO8aEE2LJiKKaFlDMBaX2l9u+vizx1Kh" +
                                                        "IKmOkQWipulMZLKuWTuopStjNBElTd5or0JVi5FQdI84JgppJitCVLZYd5TM" +
                                                        "y2FlpCPqmiCACQKYIHAThB6PCphuoVpajSCHqDFrL/k2CURJtSGheYysyBdi" +
                                                        "iKaoOmIGuAcgoRbfd4JTnDljkuVZ322fCxw+vEaY/vGDoV9UkKYYaZK1QTRH" +
                                                        "AiMYKImRBpWqw9S0ehIJmoiR+RqliUFqyqIiT3C7Y6TZkkc0kaVNml0kHEwb" +
                                                        "1OQ6vZVrkNA3My0x3cy6l5SpknDfqpKKOAK+LvJ8tT3ciOPgYL0MhplJUaIu" +
                                                        "S+WorCUYWebnyPrYsRUIgLVGpSylZ1VVaiIMkGY7doqojQiDzJS1ESCt0tOg" +
                                                        "hZHWkkJxrQ1RGhVHaJyRFj/dgD0FVHV8IZCFkYV+Mi4JotTqi1JOfK5uv+fQ" +
                                                        "Q9pmLchtTlBJQftrganNx7SDJqlJNYnajA2ro0+Ii84cDBICxAt9xDbN6Yev" +
                                                        "ff2OttkLNs0Xi9D0D++hEotLR4cb31kS6VxXgWbUGrolY/DzPOfbf8CZ6c4Y" +
                                                        "kHmLshJxMuxOzu44d/8jL9CPgqS+j1RLupJWYR/Nl3TVkBVqbqIaNUVGE32k" +
                                                        "jmqJCJ/vIzXQj8oatUf7k0mLsj5SqfChap2/wxIlQQQuUQ30ZS2pu31DZCne" +
                                                        "zxiEkHnwkBA8s8T+8X9G9gopXaWCKImarOkC7F0qmlJKoJIeN6mhC72RfmEY" +
                                                        "VjmliuaoJVhpLano43EpbTFdFSxTEnRzxB0WJN2kgpHSma6poiFsUvRhURng" +
                                                        "79tEI4xbz/h/KM3gSoTGAwEI0hI/RCiQXZt1JUHNuDSdvrf32kvxi8Fsyjhr" +
                                                        "yEgX6Aw7OsOoM5zVGfbp7LB7JBDgGm9FE+wtAQEdBWgA0GzoHNy1ZffB9grY" +
                                                        "i8Z4JUQDSdthDRy7eiU94uFHH0dJCTZxy88emAp/+uzX7E0slAb7otxk9sj4" +
                                                        "/p3f+XKQBPNRG/2EoXpkH0CszWJqhz9bi8ltmvrw45NPTOpe3uYdAw6cFHIi" +
                                                        "HLT7I2LqEk0AwHriVy8XT8XPTHYESSVgDOAqEyEPALLa/DryYKHbhVj0pQoc" +
                                                        "TuqmKio45eJiPUuZ+rg3wrdKI+/Ph6DUYp40wvN7J3H4P84uMLC91d5aGGWf" +
                                                        "FxzCN/569slTP1mzLpiL9k055+cgZTZ2zPc2yZBJKYz/9cjAjw5fnXqA7xCg" +
                                                        "WFlMQQe2EUASCBks63cv7P3L5feO/jGY3VUkA6y3ecIBXhSAOAx5x32aqifk" +
                                                        "pCwOKxT35D+bVnWd+vuhkB1EBUbcPXDHjQV441+4lzxy8cFP2riYgITHm+ew" +
                                                        "R2b7vcCT3GOa4j60I7P/D0ufPC8+DegLiGfJE5SDWBV3qIrHZiEji3MTUgW4" +
                                                        "A8CF43KtO99SML+T4tZbi/NdRaXIKpxi6KFu4vRaHtwwJ+3k7ZdwRZ11xfe7" +
                                                        "sVluFMxl+EgLf2sALztLp+lGLAhy0vsf/crwgQ8+5YtXkKBFzkEff0w4/lRr" +
                                                        "ZP1HnN/LFORelilEPyiePN47X1CvB9urzwZJTYyEJKcy2ykqadyPMahGLLdc" +
                                                        "g+otbz6/srCP0e4sEizxZ2mOWn+OeqgLfaTGfn2xtMTz7FUnLV/1p2WA8M56" +
                                                        "bDoYbOLyURgwZRUO6zGnmhAmmy+PPvXhCRtk/UvuI6YHpx/9PHxoOphTn60s" +
                                                        "KJFyeewajft0i+3T5/ALwPNvfNAXHLDP6OaIUygsz1YKhoHurChnFlex8W8n" +
                                                        "J3/73OSU7UZzfnnSC9X3iT/961L4yPtvFjnvICi6yAEgZO/vdYWr/5qz+q+V" +
                                                        "WP2t7urvw86G8sJed4S9XkLYdlfYxA2ENcPzhiPsjRLCvuEIq0jIdm3eg02v" +
                                                        "nbebwHsrpZtlvF8Iz1lHx9kSOr7p6KjW+KmDb0OlJWLnnCPxXAmJMUdiZUJk" +
                                                        "YjGzKwAAyy/MeUfF+RIqdjsqqgx93IaNXeUFXnAEXighUHIFZpO8uEBcUbIK" +
                                                        "nrccgW8VCAxkz0lEmXAf3I1GqNn8wTNHP9k/9ZUgnhZVY4hGsNlDHt32NN7p" +
                                                        "vnf88NJ50+//gB+NrugkV9/O21XY3M4hOwhBs/jNEO2WNVHJMFIzOBDtG4p/" +
                                                        "aw4uXHRcuFjEBeykYDiAHaOEfuyq2Gie4vvnoPiSo/hSGcVc+NhNKI7dQPHt" +
                                                        "8LztKH67jOIK7Dw8F8X1tuJtPYNbc3RnirPyhVRzT1xe8ywtdcnkyHj0wPRM" +
                                                        "ov9YV9A5yDdDxJ27vyenEsXkVeTb+J3aOzEfff7F0+ydNV+1AXZ16fPFz3j+" +
                                                        "wJXWofWp3TdRhy/zOeQX+fy2429uuk16PEgqsgdvwWeCfKbu/OO23qQsbWpD" +
                                                        "eYduW37Kr4TnXSfY7/qDzePE891XMwW8SO3iVI+VKaoex+aHDAonygYNRWY9" +
                                                        "GdkqhnaVY7qcKCy++MD3s2Zj9U4WwXPZMfvyf2/2T8uY/TQ2RxipHaEsoutm" +
                                                        "At83zN28K455V+ZsXgWXyNNqg9dw+p+XMfQYNs/Yhm6AcrvrJg297hh6/X9h" +
                                                        "6ItlDD2BzXOeoXeWMBQQo9q+crtV/eq5X9Yhx1sKvhraX7qkl2aaahfP3Pdn" +
                                                        "fu3Mfo2qi5LaZFpRcgvVnH61YdKkzB2os8tWg/+9wkhrabMYqcv2uRe/tLlO" +
                                                        "MxLyc8HOx79cst8wMi+HDLDb6eUSnYECAYiw+zvDXaqcg9Iu2zMkD0oNP7Cu" +
                                                        "zEM5/l3WRaS0/WU2Lp2c2bL9oWt3H+PwViUp4gQv2mqjpMa+gmdRbUVJaa6s" +
                                                        "6s2dnzW+XLfKhepGbJqde7fPtmXF76m9qsH4zXLiV4tfuefZmfd4EfAfjNB1" +
                                                        "oDAXAAA=");
    }
    public void init() { UI.printInfo(Module.
                                        LIGHT,
                                      "Balancing global photon map ...");
                         UI.taskStart("Balancing global photon map",
                                      0,
                                      1);
                         Timer t = new Timer(
                           );
                         t.start();
                         balance();
                         t.end();
                         UI.taskStop();
                         UI.printInfo(Module.
                                        LIGHT,
                                      "Global photon map:");
                         UI.printInfo(Module.
                                        LIGHT,
                                      "  * Photons stored:   %d",
                                      storedPhotons);
                         UI.printInfo(Module.
                                        LIGHT,
                                      "  * Photons/estimate: %d",
                                      numGather);
                         UI.printInfo(Module.
                                        LIGHT,
                                      "  * Estimate radius:  %.3f",
                                      gatherRadius);
                         maxRadius = 1.4F *
                                       (float)
                                         Math.
                                         sqrt(
                                           maxPower *
                                             numGather);
                         UI.printInfo(Module.
                                        LIGHT,
                                      "  * Maximum radius:   %.3f",
                                      maxRadius);
                         UI.printInfo(Module.
                                        LIGHT,
                                      "  * Balancing time:   %s",
                                      t.
                                        toString(
                                          ));
                         if (gatherRadius >
                               maxRadius)
                             gatherRadius =
                               maxRadius;
                         t.start();
                         precomputeRadiance(
                           );
                         t.end();
                         UI.printInfo(Module.
                                        LIGHT,
                                      "  * Precompute time:  %s",
                                      t.
                                        toString(
                                          ));
                         UI.printInfo(Module.
                                        LIGHT,
                                      "  * Radiance photons: %d",
                                      storedPhotons);
                         UI.printInfo(Module.
                                        LIGHT,
                                      "  * Search radius:    %.3f",
                                      gatherRadius);
    }
    public void precomputeRadiance() { if (storedPhotons ==
                                             0)
                                           return;
                                       int quadStoredPhotons =
                                         halfStoredPhotons /
                                         2;
                                       Point3 p =
                                         new Point3(
                                         );
                                       Vector3 n =
                                         new Vector3(
                                         );
                                       Point3 ppos =
                                         new Point3(
                                         );
                                       Vector3 pdir =
                                         new Vector3(
                                         );
                                       Vector3 pvec =
                                         new Vector3(
                                         );
                                       Color irr =
                                         new Color(
                                         );
                                       Color pow =
                                         new Color(
                                         );
                                       float maxDist2 =
                                         gatherRadius *
                                         gatherRadius;
                                       NearestPhotons np =
                                         new NearestPhotons(
                                         p,
                                         numGather,
                                         maxDist2);
                                       Photon[] temp =
                                         new Photon[quadStoredPhotons +
                                                      1];
                                       UI.
                                         taskStart(
                                           "Precomputing radiance",
                                           1,
                                           quadStoredPhotons);
                                       for (int i =
                                              1;
                                            i <=
                                              quadStoredPhotons;
                                            i++) {
                                           UI.
                                             taskUpdate(
                                               i);
                                           Photon curr =
                                             photons[i];
                                           p.
                                             set(
                                               curr.
                                                 x,
                                               curr.
                                                 y,
                                               curr.
                                                 z);
                                           Vector3.
                                             decode(
                                               curr.
                                                 normal,
                                               n);
                                           irr.
                                             set(
                                               Color.
                                                 BLACK);
                                           np.
                                             reset(
                                               p,
                                               maxDist2);
                                           locatePhotons(
                                             np);
                                           if (np.
                                                 found <
                                                 8) {
                                               curr.
                                                 data =
                                                 0;
                                               temp[i] =
                                                 curr;
                                               continue;
                                           }
                                           float invArea =
                                             1.0F /
                                             ((float)
                                                Math.
                                                  PI *
                                                np.
                                                  dist2[0]);
                                           float maxNDist =
                                             np.
                                               dist2[0] *
                                             0.05F;
                                           for (int j =
                                                  1;
                                                j <=
                                                  np.
                                                    found;
                                                j++) {
                                               Photon phot =
                                                 np.
                                                   index[j];
                                               Vector3.
                                                 decode(
                                                   phot.
                                                     dir,
                                                   pdir);
                                               float cos =
                                                 -Vector3.
                                                 dot(
                                                   pdir,
                                                   n);
                                               if (cos >
                                                     0.01F) {
                                                   ppos.
                                                     set(
                                                       phot.
                                                         x,
                                                       phot.
                                                         y,
                                                       phot.
                                                         z);
                                                   Point3.
                                                     sub(
                                                       ppos,
                                                       p,
                                                       pvec);
                                                   float pcos =
                                                     Vector3.
                                                     dot(
                                                       pvec,
                                                       n);
                                                   if (pcos <
                                                         maxNDist &&
                                                         pcos >
                                                         -maxNDist)
                                                       irr.
                                                         add(
                                                           pow.
                                                             setRGBE(
                                                               phot.
                                                                 power));
                                               }
                                           }
                                           irr.
                                             mul(
                                               invArea);
                                           irr.
                                             mul(
                                               new Color(
                                                 curr.
                                                   data)).
                                             mul(
                                               1.0F /
                                                 (float)
                                                   Math.
                                                     PI);
                                           curr.
                                             data =
                                             irr.
                                               toRGBE(
                                                 );
                                           temp[i] =
                                             curr;
                                       }
                                       UI.
                                         taskStop(
                                           );
                                       numGather /=
                                         4;
                                       maxRadius =
                                         1.4F *
                                           (float)
                                             Math.
                                             sqrt(
                                               maxPower *
                                                 numGather);
                                       if (gatherRadius >
                                             maxRadius)
                                           gatherRadius =
                                             maxRadius;
                                       storedPhotons =
                                         quadStoredPhotons;
                                       halfStoredPhotons =
                                         storedPhotons /
                                           2;
                                       log2n =
                                         (int)
                                           Math.
                                           ceil(
                                             Math.
                                               log(
                                                 storedPhotons) /
                                               Math.
                                               log(
                                                 2.0));
                                       photons =
                                         temp;
                                       hasRadiance =
                                         true;
    }
    public Color getRadiance(Point3 p, Vector3 n) {
        if (!hasRadiance ||
              storedPhotons ==
              0)
            return Color.
                     BLACK;
        float px =
          p.
            x;
        float py =
          p.
            y;
        float pz =
          p.
            z;
        int i =
          1;
        int level =
          0;
        int cameFrom;
        float dist2;
        float maxDist2 =
          gatherRadius *
          gatherRadius;
        Photon nearest =
          null;
        Photon curr;
        Vector3 photN =
          new Vector3(
          );
        float[] dist1d2 =
          new float[log2n];
        int[] chosen =
          new int[log2n];
        while (true) {
            while (i <
                     halfStoredPhotons) {
                float dist1d =
                  photons[i].
                  getDist1(
                    px,
                    py,
                    pz);
                dist1d2[level] =
                  dist1d *
                    dist1d;
                i +=
                  i;
                if (dist1d >
                      0)
                    i++;
                chosen[level++] =
                  i;
            }
            curr =
              photons[i];
            dist2 =
              curr.
                getDist2(
                  px,
                  py,
                  pz);
            if (dist2 <
                  maxDist2) {
                Vector3.
                  decode(
                    curr.
                      normal,
                    photN);
                float currentDotN =
                  Vector3.
                  dot(
                    photN,
                    n);
                if (currentDotN >
                      0.9F) {
                    nearest =
                      curr;
                    maxDist2 =
                      dist2;
                }
            }
            do  {
                cameFrom =
                  i;
                i >>=
                  1;
                level--;
                if (i ==
                      0)
                    return nearest ==
                      null
                      ? Color.
                          BLACK
                      : new Color(
                      ).
                      setRGBE(
                        nearest.
                          data);
            }while(dist1d2[level] >=
                     maxDist2 ||
                     cameFrom !=
                     chosen[level]); 
            curr =
              photons[i];
            dist2 =
              curr.
                getDist2(
                  px,
                  py,
                  pz);
            if (dist2 <
                  maxDist2) {
                Vector3.
                  decode(
                    curr.
                      normal,
                    photN);
                float currentDotN =
                  Vector3.
                  dot(
                    photN,
                    n);
                if (currentDotN >
                      0.9F) {
                    nearest =
                      curr;
                    maxDist2 =
                      dist2;
                }
            }
            i =
              chosen[level++] ^
                1;
        }
    }
    private static class NearestPhotons {
        int found;
        float px;
        float py;
        float pz;
        private int max;
        private boolean gotHeap;
        protected float[] dist2;
        protected Photon[] index;
        NearestPhotons(Point3 p, int n, float maxDist2) {
            super(
              );
            max =
              n;
            found =
              0;
            gotHeap =
              false;
            px =
              p.
                x;
            py =
              p.
                y;
            pz =
              p.
                z;
            dist2 =
              (new float[n +
                           1]);
            index =
              (new Photon[n +
                            1]);
            dist2[0] =
              maxDist2;
        }
        void reset(Point3 p, float maxDist2) {
            found =
              0;
            gotHeap =
              false;
            px =
              p.
                x;
            py =
              p.
                y;
            pz =
              p.
                z;
            dist2[0] =
              maxDist2;
        }
        void checkAddNearest(Photon p) { float fdist2 =
                                           p.
                                           getDist2(
                                             px,
                                             py,
                                             pz);
                                         if (fdist2 <
                                               dist2[0]) {
                                             if (found <
                                                   max) {
                                                 found++;
                                                 dist2[found] =
                                                   fdist2;
                                                 index[found] =
                                                   p;
                                             }
                                             else {
                                                 int j;
                                                 int parent;
                                                 if (!gotHeap) {
                                                     float dst2;
                                                     Photon phot;
                                                     int halfFound =
                                                       found >>
                                                       1;
                                                     for (int k =
                                                            halfFound;
                                                          k >=
                                                            1;
                                                          k--) {
                                                         parent =
                                                           k;
                                                         phot =
                                                           index[k];
                                                         dst2 =
                                                           dist2[k];
                                                         while (parent <=
                                                                  halfFound) {
                                                             j =
                                                               parent +
                                                                 parent;
                                                             if (j <
                                                                   found &&
                                                                   dist2[j] <
                                                                   dist2[j +
                                                                           1])
                                                                 j++;
                                                             if (dst2 >=
                                                                   dist2[j])
                                                                 break;
                                                             dist2[parent] =
                                                               dist2[j];
                                                             index[parent] =
                                                               index[j];
                                                             parent =
                                                               j;
                                                         }
                                                         dist2[parent] =
                                                           dst2;
                                                         index[parent] =
                                                           phot;
                                                     }
                                                     gotHeap =
                                                       true;
                                                 }
                                                 parent =
                                                   1;
                                                 j =
                                                   2;
                                                 while (j <=
                                                          found) {
                                                     if (j <
                                                           found &&
                                                           dist2[j] <
                                                           dist2[j +
                                                                   1])
                                                         j++;
                                                     if (fdist2 >
                                                           dist2[j])
                                                         break;
                                                     dist2[parent] =
                                                       dist2[j];
                                                     index[parent] =
                                                       index[j];
                                                     parent =
                                                       j;
                                                     j +=
                                                       j;
                                                 }
                                                 dist2[parent] =
                                                   fdist2;
                                                 index[parent] =
                                                   p;
                                                 dist2[0] =
                                                   dist2[1];
                                             }
                                         }
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public boolean allowDiffuseBounced() {
        return true;
    }
    public boolean allowReflectionBounced() {
        return true;
    }
    public boolean allowRefractionBounced() {
        return true;
    }
    public int numEmit() { return numEmit;
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "8xO+fNv7XeOXJLecxMl/uU8nP8vHN+0+sOF05Z4gqcpWvgVvJPOJ1vk2ZNjj" +
       "05Y+nlf1Lss/fZ8N1xkyAM4ohr6t2PT6Di0Bx5n4+FUx65Eyp5rHsPmhKJEZ" +
       "1GVFU6p6xlDjhQcf0fH9fIlH4QpLicMVS1wtOFa74be0IPzGkhRBBN9VM3dW" +
       "ZwHWTDAs+851JyzyTlBTdJrhwd0QqfyMEObHZQzzHDZ7IE3EroQPT5zQBCJr" +
       "18J1gTTBBSVNsLG401zZLzyJ6uVKhn7jcmsTS/yijGIvYvNz2G41QwFzenbE" +
       "ChVEfBuXCo5XrKBXhANlxl7F5iXEcaph2pyEYJvg2iIF21KxYDL48NHMoqfT" +
       "CKKDZYT9LTavc9IihR1j0ynmvGqoUOaVcD0vZX6+YpmDOST3SHqojKR/wuZN" +
       "yGV7lpqVyScSGguL41K+4xUntHfld8qM/RWbwxxf3KsVWk1I1QupIkufQEHp" +
       "U5FU75UZO4rN3zhpB0zEbxRpztyKozIZRaUMJUFgQso4UbGM0rNFMUyA3IgB" +
       "Vbxw+tOCy7EyinyEzT+gZppm3KvBMyfUQJTUp4Pkb0sN3j4lK39aZuw4Np/A" +
       "SY9qoNzlaiKRthmWi3CyxaFvVCZkHwj3rhTy3VMRMlBVZqwGGzgHLxRCjrKE" +
       "fHF7anIekXIeOSU555UZa8am3iOnRU9ezpBM+MAHUs4PTknOjjJjC7Bp9ZWr" +
       "Rd6pchLybXP47qez4OO280FW2TMXql80d9XvnQOw+9G0IUrqE2lN875l9NzX" +
       "QnonVKFTg/PO0RQydnHSVXr7hfI1e49yBxY7VEs5afVTAbDhj3daN2SjZxpY" +
       "Qt55J63kpAom4W2vsHwiQzylMn4U8T7lfSHxv2DblHb+WSCm7J274sobj53/" +
       "sCiDaxSNbtuGXOqjpM75OJStfleU5Obyqt3Y92nLUw2r3Kq+BZt2+UXIJ9vy" +
       "4h9OBlMmF++Ttv100dMXPzp3WHy5+S/H3IuBwyEAAA==");
}
