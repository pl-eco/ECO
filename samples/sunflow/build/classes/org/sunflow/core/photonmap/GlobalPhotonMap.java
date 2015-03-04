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
          1425485134000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYe2wUxxmfO7+NYx8mgOuCAWOiGNLbOihRqSNaxxgwHOBi" +
                                                        "hzYXgRnvzdkL+8runH04cRuQWtNURVFqUtImVtSC8oIQtUH0ISrnwSOBqEpV" +
                                                        "tWqkhij/NCLlD/4giZq26ffN7u3e7T0wVXvSzs3OfM/55vvNN3v8KqmwLbLK" +
                                                        "NNR9w6rBoyzNo3vUu6J8n8ns6KbYXX3UslmiW6W2PQBjg3Lryw0ff/bYSCRM" +
                                                        "KuNkHtV1g1OuGLq9ndmGOsoSMdLgj/aoTLM5icT20FEqpbiiSjHF5p0xMieL" +
                                                        "lZO2WMYECUyQwARJmCB1+VTAdAvTU1o3clCd2w+Sb5NQjFSaMprHybJcISa1" +
                                                        "qOaK6RMegIRqfN8BTgnmtEWWer47Puc5fHiVNPXjXZFflJGGOGlQ9H40RwYj" +
                                                        "OCiJkzqNaUPMsrsSCZaIk7k6Y4l+ZilUVcaF3XHSaCvDOuUpi3mLhIMpk1lC" +
                                                        "p79ydTL6ZqVkbliee0mFqYnMW0VSpcPg6wLfV8fD9TgODtYqYJiVpDLLsJTv" +
                                                        "VfQEJ0uCHJ6PbZuBAFirNMZHDE9VuU5hgDQ6sVOpPiz1c0vRh4G0wkiBFk6a" +
                                                        "iwrFtTapvJcOs0FOmoJ0fc4UUNWIhUAWTuYHyYQkiFJzIEpZ8bm69Z5DD+kb" +
                                                        "9bCwOcFkFe2vBqaWANN2lmQW02XmMNatjD1BF5w5GCYEiOcHiB2a0w9f+/od" +
                                                        "LTMXHJovFqDZNrSHyXxQPjpU/86i7vY1ZWhGtWnYCgY/x3Ox/fvcmc60CZm3" +
                                                        "wJOIk9HM5Mz2c/c/8gL7KExqe0mlbKgpDfbRXNnQTEVl1gamM4tyluglNUxP" +
                                                        "dIv5XlIF/ZiiM2d0WzJpM95LylUxVGmId1iiJIjAJaqCvqInjUzfpHxE9NMm" +
                                                        "IWQOPCQCzwxxfuKfk13SiKExicpUV3RDgr3LqCWPSEw2JJtqpgpRs1N6UjXG" +
                                                        "JNuSJcMa9t5lw2KSOWJwQ9eoKW1QjSGq9on3LdSM4j4z/+8a0uhjZCwUguVf" +
                                                        "FEx+FfJmo6EmmDUoT6Xu7bn20uDFsJcM7upw0gE6o67OKOqMejqjAZ1tTo+E" +
                                                        "QkLjrWiCE2wI1V5IeoDDuvb+nZt2H2wtg11mjpXDOiNpKzjs2tUjG90+MvQK" +
                                                        "/JNhezb97IHJ6KfPfs3ZnlJxGC/ITWaOjO3f8Z0vh0k4F4/RTxiqRfY+RFEP" +
                                                        "LduCeVhIbsPkhx+ffGLC8DMyB+BdoMjnxERvDUbEMmSWAOj0xa9cSk8Nnplo" +
                                                        "C5NyQA9ATE5hhwMYtQR15CR8ZwY80ZcKcDhpWBpVcSqDeLV8xDLG/BGxVepF" +
                                                        "fy4EpRozoB6e37spIf5xdp6J7a3O1sIoB7wQ4Lz+1zNPnvrJqjXhbBxvyDoZ" +
                                                        "+xl3UGGuv0kGLMZg/K9H+n50+OrkA2KHAMXyQgrasO0GjICQwbJ+98KDf7n8" +
                                                        "3tE/hr1dRdLAepsvHIBDBfDCkLfdp2tGQkkqdEhluCf/2bCi49TfD0WcIKow" +
                                                        "ktkDd9xYgD/+hXvJIxd3fdIixIRkPLh8h30yx+95vuQuy6L70I70/j8sfvI8" +
                                                        "fRpwFbDMVsaZgKcK4VCFiM18ThZmJ6QGQAZQCgfh6sx8U978DoZbbzXOdxSU" +
                                                        "omhwPqGHhoXTq0Vwo4K0XbRfwhV11xXf78ZmqZk3lxYjTeKtDrxsL56m6/Go" +
                                                        "z0rvf2xThw588KlYvLwELXDCBfjj0vGnmrvXfiT4/UxB7iXpfPSDssjnvfMF" +
                                                        "7Xq4tfJsmFTFSUR2a64dVE3hfoxDnWFnCjGoy3Lmc2sG54Ds9JBgUTBLs9QG" +
                                                        "c9RHXegjNfZrC6UlnlSvumn5ajAtQ0R01mLTxmETl45Cn6VocAyPunWCNNF4" +
                                                        "ee9TH55wQDa45AFidnDq0c+jh6bCWZXX8rziJ5vHqb6ET7c4Pn0OvxA8/8YH" +
                                                        "fcEB5/Rt7HZLgKVeDWCa6M6yUmYJFev/dnLit89NTDpuNOYWHj1QV5/4078u" +
                                                        "RY+8/2aB8w6CYlABABFnf6/JX/3X3NV/rcjqb86s/j7srCst7HVX2OtFhG3N" +
                                                        "CBu/gbBGeN5whb1RRNg3XGFlCcWpuruw6XHydgN4b48YVgnv58Nz1tVxtoiO" +
                                                        "b7o6KnVx6uDbQHGJ2DnnSjxXRGLclVieoJwWMrsMALD0wpx3VZwvomK3q6LC" +
                                                        "NMYc2NhZWuAFV+CFIgLljEAvyQsLxBUlK+B5yxX4Vp7AkHdOIspEe+HWM8ys" +
                                                        "xg+eOfrJ/smvhPG0qBhFNILNHvHptqbwtva944cXz5l6/wfiaMyITgr1raJd" +
                                                        "gc3tArLDEDRb3PnQbkWnapqTqv6+WO/A4Ldm4cJF14WLBVzAzggMh7BjFtGP" +
                                                        "XQ0b3Vd8/ywUX3IVXyqhWAgfvQnF8Rsovh2et13Fb5dQXIadh2ejuNZRvKWr" +
                                                        "f3OW7nRhVrGQWvaJK2qexcWujwIZjx6Ymk5sO9YRdg/yjRBx91bvyylHMTkV" +
                                                        "+RZxW/ZPzEeff/E0f2fVVx2AXVn8fAkynj9wpXlg7cjum6jDlwQcCop8fsvx" +
                                                        "NzfcJj8eJmXewZv3ASCXqTP3uK21GE9Z+kDOoduSm/LL4XnXDfa7wWCLOIl8" +
                                                        "D9RMIT9SOwXVYyWKqsex+SGHwonxflNVeFdasQuhXfmooSTyiy8x8H3PbKze" +
                                                        "yQJ4LrtmX/7vzf5pCbOfxuYIJ9XDjHcbhpXA93WzN++Ka96VWZtXJiSKtFrn" +
                                                        "N4L+5yUMPYbNM46h66Dc7rhJQ6+7hl7/Xxj6YglDT2DznG/onUUMBcSodK7c" +
                                                        "map+5ewv65DjTXnfA51vWPJL0w3VC6fv+7O4dnrfmWpipDqZUtXsQjWrX2la" +
                                                        "LKkIB2qcstUUf69w0lzcLE5qvL7w4pcO12lOIkEu2Pn4l032G07mZJEBdru9" +
                                                        "bKIzUCAAEXZ/Z2aWKuugdMr2NMmBUjMIrMtzUE58cc0gUsr55joon5zetPWh" +
                                                        "a3cfE/BWIat0XBRt1TFS5VzBPVRbVlRaRlblxvbP6l+uWZGB6npsGt17d8C2" +
                                                        "JYXvqT2aycXNcvxXC1+559np90QR8B/QxDqeChcAAA==");
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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "k0KLWvS+uH2SvUJfAIaronFSsG0UpkL/wJxT83lDOWRqBUR6wkOmJ6LIFENi" +
           "soMPPdyfNGfp/OErArOky7YVC7wLBJ30BJ6sIHCXJ1CxhUp9/5203YG06f+B" +
           "tOFA2kxlaS1+v/OkJ+3JCtJGBEuXGNfx4YuyUjNUZzvGJObXAlSVlX1G+VMV" +
           "e3UCzylvr1MV9nqADwdAcoaynQTb5VKyboxSk2Cr7E6t/k6nvZ1OV9hptKJV" +
           "9bZDGYQt0cGuGh2g+B7I3HWVM1dAtgTH+R91//rIfPfvIetSKG64EN/9TqZM" +
           "P1/A85eFGzffWbL6iijr1WPYlZEevQiV3nOKri/i9Bu9YAxwKOY1VeKQbN92" +
           "o7ztCoOrZW7MNDSeJ4aFTTiAWpNYGdknC99m7HwgXZFsfi2SVYwnPlxXqEV4" +
           "QfTfyfbPoIngqggv8yV6Omh1UfO3W1gYItPjly6/yt7e+A0J8Rsq+yTK+PrR" +
           "P7Xvv2/8oc/R8q2JuCwq8tLuhTd3rNdOKagqALiSO2cxU18xrDU4BC7J1v4i" +
           "cOuQPsxUiE8+NfOLlKLZRd4d4cM0eFfj/pHuhDNfU76xGczaTLQiMz9d+ZN7" +
           "n59/XzRUeVQ58daCgnNe4s1VSLzv8wGDDlCxvQgggZRGD8/FA/J/C6X4AbX5" +
           "c9xI5My2bU/1fIX459NvQ8i74otHYe0WDeTqSndy0X1cPDo3r+99brPinfXX" +
           "QZD3qSSUI3MlVwzpm+C0LnindiFqr9BY4HnEr0qosyqSXpCeWcT7T/HhFJw8" +
           "XM9I2W6nepIaemkXU07p9aDsZU/py59Z6VgYxMcF1Q8X0fcCH56Fq4I2TrSJ" +
           "fl33bpZ8+XyZXouhpuK7px8rGz57rICb20o+kMmPOtqV+eb4yvkDv5VA7X94" +
           "qU+ieDpnmoXtSsG81nZI2hD21MvmReb3JYbaK6vFq5A/F1a8ILkWGGqJcoHb" +
           "+E8h2VWG7iggg5rpzQqJXoGqDUR8+mPbP6qWEMNl85ZHRWlgR5OiuwiDxSdI" +
           "Hy9z8iPkqHZ1fteeR25/9TkBvoA9eEb0JXEoYfIuGmDu2orSfFm1O3s/aXq5" +
           "fp2fZk18aPUuoIW68fnRfwN+A6Zy8BUAAA==");
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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "iy83/wV3bonEnSEAAA==");
}
