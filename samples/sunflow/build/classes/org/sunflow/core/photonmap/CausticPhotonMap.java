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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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");
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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("H4sIAAAAAAAAAMVYW2xcxRme3bXXlxh745DEdRMncRyEY7qnCQI1NUprFidx" +
           "vLa3XifAIlgfn53dPfG5MWfW3jh1S6K2jkBEqDhpuNRCbSiXhgQhIkAVUl5a" +
           "QPSFqmrVhwLipag0D3kooNKW/jPntnv2EqeqVEuenZ35r/P//zf/7PkrqNEk" +
           "aMDQlaM5RadRXKTRI8ptUXrUwGb0YPy2hEhMnIkpomlOwVpa6n2549MvHstH" +
           "giicQutETdOpSGVdMyexqStzOBNHHd7qsIJVk6JI/Ig4JwoFKitCXDbpYByt" +
           "KWGlqC/umCCACQKYIHAThCGPCphuwFpBjTEOUaPmg+h7KBBHYUNi5lG0rVyI" +
           "IRJRtcUkuAcgoZl9PwxOceYiQVtd3y2fKxw+PSAs/+SByCsh1JFCHbKWZOZI" +
           "YAQFJSnUpmJ1BhNzKJPBmRRaq2GcSWIii4q8wO1OoU5TzmkiLRDsHhJbLBiY" +
           "cJ3eybVJzDdSkKhOXPeyMlYyzrfGrCLmwNcNnq+Wh/vYOjjYKoNhJCtK2GFp" +
           "mJW1DEVb/Byuj32jQACsTSqmed1V1aCJsIA6rdgpopYTkpTIWg5IG/UCaKGo" +
           "u6ZQdtaGKM2KOZymqMtPl7C2gKqFHwRjoWi9n4xLgih1+6JUEp8r43ecOqYd" +
           "0ILc5gyWFGZ/MzD1+JgmcRYTrEnYYmzbGT8jbnjzZBAhIF7vI7ZoXvvu1W/f" +
           "0nP5bYvmq1VoJmaOYImmpXMz7e9tivXvCTEzmg3dlFnwyzzn6Z+wdwaLBlTe" +
           "Blci24w6m5cnf3PvQy/iT4KodQSFJV0pqJBHayVdNWQFk/1Yw0SkODOCWrCW" +
           "ifH9EdQE87isYWt1Ips1MR1BDQpfCuv8OxxRFkSwI2qCuaxldWduiDTP50UD" +
           "IbQO/lEXQoGDiP9ZnxQRIa+rWBAlUZM1XYDcxSKR8gKW9DTBhi4MxyaEGTjl" +
           "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" +
           "oH6SFgAA");
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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
