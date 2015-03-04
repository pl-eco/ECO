package org.sunflow.core.photonmap;
import java.util.ArrayList;
import org.sunflow.core.CausticPhotonMapInterface;
import org.sunflow.core.LightSample;
import org.sunflow.core.Options;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public final class CausticPhotonMap implements CausticPhotonMapInterface {
    private ArrayList<Photon> photonList;
    private Photon[] photons;
    private int storedPhotons;
    private int halfStoredPhotons;
    private int log2n;
    private int gatherNum;
    private float gatherRadius;
    private BoundingBox bounds;
    private float filterValue;
    private float maxPower;
    private float maxRadius;
    private int numEmit;
    public CausticPhotonMap(Options options) { super();
                                               numEmit = options.getInt("caustics.emit",
                                                                        10000);
                                               gatherNum = options.
                                                             getInt(
                                                               "caustics.gather",
                                                               50);
                                               gatherRadius =
                                                 options.
                                                   getFloat(
                                                     "caustics.radius",
                                                     0.5F);
                                               filterValue =
                                                 options.
                                                   getFloat(
                                                     "caustics.filter",
                                                     1.1F);
                                               bounds = new BoundingBox(
                                                          );
                                               maxPower =
                                                 0;
                                               maxRadius =
                                                 0; }
    public void prepare(BoundingBox sceneBounds) { photonList =
                                                     new ArrayList<Photon>(
                                                       );
                                                   photonList.
                                                     add(
                                                       null);
                                                   photons =
                                                     null;
                                                   storedPhotons =
                                                     (halfStoredPhotons =
                                                        0);
    }
    private void locatePhotons(NearestPhotons np) { float[] dist1d2 =
                                                      new float[log2n];
                                                    int[] chosen =
                                                      new int[log2n];
                                                    int i =
                                                      1;
                                                    int level =
                                                      0;
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
                                                    }
    }
    private void balance() { if (storedPhotons ==
                                   0) return;
                             photons = photonList.
                                         toArray(
                                           new Photon[photonList.
                                                        size(
                                                          )]);
                             photonList = null;
                             Photon[] temp = new Photon[storedPhotons +
                                                          1];
                             balanceSegment(temp,
                                            1,
                                            1,
                                            storedPhotons);
                             photons = temp;
                             halfStoredPhotons = storedPhotons /
                                                   2;
                             log2n = (int) Math.ceil(
                                                  Math.
                                                    log(
                                                      storedPhotons) /
                                                    Math.
                                                    log(
                                                      2.0));
    }
    private void balanceSegment(Photon[] temp, int index,
                                int start,
                                int end) { int median =
                                             1;
                                           while (4 *
                                                    median <=
                                                    end -
                                                    start +
                                                    1)
                                               median +=
                                                 median;
                                           if (3 *
                                                 median <=
                                                 end -
                                                 start +
                                                 1) {
                                               median +=
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
                                           int axis =
                                             Photon.
                                               SPLIT_Z;
                                           Vector3 extents =
                                             bounds.
                                             getExtents(
                                               );
                                           if (extents.
                                                 x >
                                                 extents.
                                                   y &&
                                                 extents.
                                                   x >
                                                 extents.
                                                   z)
                                               axis =
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
                                           int left =
                                             start;
                                           int right =
                                             end;
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
                                           temp[index] =
                                             photons[median];
                                           temp[index].
                                             setSplitAxis(
                                               axis);
                                           if (median >
                                                 start) {
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
                                           if (median <
                                                 end) {
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
    private void swap(int i, int j) { Photon tmp =
                                        photons[i];
                                      photons[i] =
                                        photons[j];
                                      photons[j] =
                                        tmp; }
    public void store(ShadingState state, Vector3 dir,
                      Color power,
                      Color diffuse) { if (state.
                                             getDiffuseDepth(
                                               ) ==
                                             0 &&
                                             (state.
                                                getReflectionDepth(
                                                  ) >
                                                0 ||
                                                state.
                                                getRefractionDepth(
                                                  ) >
                                                0)) {
                                           Photon p =
                                             new Photon(
                                             state.
                                               getPoint(
                                                 ),
                                             dir,
                                             power);
                                           synchronized (this)  {
                                               storedPhotons++;
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
                                           }
                                       } }
    public void init() { UI.printInfo(Module.LIGHT,
                                      "Balancing caustics photon map ...");
                         Timer t = new Timer();
                         t.start();
                         balance();
                         t.end();
                         UI.printInfo(Module.LIGHT,
                                      "Caustic photon map:");
                         UI.printInfo(Module.LIGHT,
                                      "  * Photons stored:   %d",
                                      storedPhotons);
                         UI.printInfo(Module.LIGHT,
                                      "  * Photons/estimate: %d",
                                      gatherNum);
                         maxRadius = 1.4F * (float)
                                              Math.
                                              sqrt(
                                                maxPower *
                                                  gatherNum);
                         UI.printInfo(Module.LIGHT,
                                      "  * Estimate radius:  %.3f",
                                      gatherRadius);
                         UI.printInfo(Module.LIGHT,
                                      "  * Maximum radius:   %.3f",
                                      maxRadius);
                         UI.printInfo(Module.LIGHT,
                                      "  * Balancing time:   %s",
                                      t.
                                        toString(
                                          ));
                         if (gatherRadius > maxRadius)
                             gatherRadius =
                               maxRadius; }
    public void getSamples(ShadingState state) { if (storedPhotons ==
                                                       0)
                                                     return;
                                                 NearestPhotons np =
                                                   new NearestPhotons(
                                                   state.
                                                     getPoint(
                                                       ),
                                                   gatherNum,
                                                   gatherRadius *
                                                     gatherRadius);
                                                 locatePhotons(
                                                   np);
                                                 if (np.
                                                       found <
                                                       8)
                                                     return;
                                                 Point3 ppos =
                                                   new Point3(
                                                   );
                                                 Vector3 pdir =
                                                   new Vector3(
                                                   );
                                                 Vector3 pvec =
                                                   new Vector3(
                                                   );
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
                                                 float f2r2 =
                                                   1.0F /
                                                   (filterValue *
                                                      filterValue *
                                                      np.
                                                        dist2[0]);
                                                 float fInv =
                                                   1.0F /
                                                   (1.0F -
                                                      2.0F /
                                                      (3.0F *
                                                         filterValue));
                                                 for (int i =
                                                        1;
                                                      i <=
                                                        np.
                                                          found;
                                                      i++) {
                                                     Photon phot =
                                                       np.
                                                         index[i];
                                                     Vector3.
                                                       decode(
                                                         phot.
                                                           dir,
                                                         pdir);
                                                     float cos =
                                                       -Vector3.
                                                       dot(
                                                         pdir,
                                                         state.
                                                           getNormal(
                                                             ));
                                                     if (cos >
                                                           0.001) {
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
                                                             state.
                                                               getPoint(
                                                                 ),
                                                             pvec);
                                                         float pcos =
                                                           Vector3.
                                                           dot(
                                                             pvec,
                                                             state.
                                                               getNormal(
                                                                 ));
                                                         if (pcos <
                                                               maxNDist &&
                                                               pcos >
                                                               -maxNDist) {
                                                             LightSample sample =
                                                               new LightSample(
                                                               );
                                                             sample.
                                                               setShadowRay(
                                                                 new Ray(
                                                                   state.
                                                                     getPoint(
                                                                       ),
                                                                   pdir.
                                                                     negate(
                                                                       )));
                                                             sample.
                                                               setRadiance(
                                                                 new Color(
                                                                   ).
                                                                   setRGBE(
                                                                     np.
                                                                       index[i].
                                                                       power).
                                                                   mul(
                                                                     invArea /
                                                                       cos),
                                                                 Color.
                                                                   BLACK);
                                                             sample.
                                                               getDiffuseRadiance(
                                                                 ).
                                                               mul(
                                                                 (1.0F -
                                                                    (float)
                                                                      Math.
                                                                      sqrt(
                                                                        np.
                                                                          dist2[i] *
                                                                          f2r2)) *
                                                                   fInv);
                                                             state.
                                                               addSample(
                                                                 sample);
                                                         }
                                                     }
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
           "1sCbZu8Kmq8b7x/9F8LVG4f1FQAA");
    }
    private static class Photon {
        float x;
        float y;
        float z;
        short dir;
        int power;
        int flags;
        static final int SPLIT_X = 0;
        static final int SPLIT_Y = 1;
        static final int SPLIT_Z = 2;
        static final int SPLIT_MASK = 3;
        Photon(Point3 p, Vector3 dir, Color power) {
            super(
              );
            x =
              p.
                x;
            y =
              p.
                y;
            z =
              p.
                z;
            this.
              dir =
              dir.
                encode(
                  );
            this.
              power =
              power.
                toRGBE(
                  );
            flags =
              SPLIT_X;
        }
        void setSplitAxis(int axis) { flags &=
                                        ~SPLIT_MASK;
                                      flags |=
                                        axis;
        }
        float getCoord(int axis) { switch (axis) {
                                       case SPLIT_X:
                                           return x;
                                       case SPLIT_Y:
                                           return y;
                                       default:
                                           return z;
                                   } }
        float getDist1(float px, float py,
                       float pz) { switch (flags &
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
        float getDist2(float px, float py,
                       float pz) { float dx =
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
                                     dz; }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
    }
    public boolean allowDiffuseBounced() {
        return false;
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
       "Kd3iV1q7f7zw2asPzLzFP4X8F5moIV7xIAAA");
}
