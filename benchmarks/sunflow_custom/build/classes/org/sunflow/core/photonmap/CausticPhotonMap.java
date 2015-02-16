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
          1169871682000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "Q3IUajHXL1ahdi2YL6yrZBHRH4Dhmqif5G0bhanQPtDnq3m/vhQytcDzmQdN" +
           "n0WRKYZEZydverg9adbS+ctXBWZJk20vZHg3PNc9htfLMNztMVRsIVL/f8dt" +
           "T8Bt5n/AbTjgNlueW7NfCN3wuN0ow21EkHSJdgNvvigzNUM1tmNMYX5fQBUZ" +
           "WWiUPlWxVyc8N729bpbZ6yHeHATOacp2EWyXCsmacUpNgq2SO7X4O93ydrpV" +
           "ZqexslrV2Q5l4LZEB72qdIDirRC5G8pHroBsCY4LP+n+9ZGF7j9B1I2iWsMF" +
           "/x5w0iUK/Tyavy9euvzRsrXnRFqvHMeu9PToDan4AlRwrxGn3+A5Y4BDMa+o" +
           "Eodk+7obpXVXGNw5s+OmofE4MSxswgFUm8RKywJa2DZt5wLuiiTzc5HMYjzw" +
           "4R5DLcIToj8nyz+DxoM7JEzmiuR00NqC4m+P0DBEpqfO/vQN9mHfvRLiN5W3" +
           "SZTw3aN/bT9w/8Sjd1DyrYuYLMry7J7F93du1J5WUEUAcEWX0UKi/kJYq3cI" +
           "3J6tAwXg1iFtmC7jn7xr5pZIRXNLzB3hzQxYV+P2keaEM19XurAZzNhMlCKz" +
           "v1j98/teWvhYFFQ5VD7w1sNz2wu822UC73u8wSADZGzPA0jApcHH85Uel5VR" +
           "Lr5Dbb2TK4ns2bbtyZ4rEwC8+yD4vCu+heQnb1FBri13Wxflx5mj8wv6vhe3" +
           "KN5hfwMYeR9RQj4yWLKFmL4Z7Nog9ZX/+QoLiQWgRwyrhDKrIurF0pNLmP9Z" +
           "3jwNRw/3M1Ky3KmcooZeXMaUEnojCNvqCd36HwsdC734uFj14yXkfYE3z8Nd" +
           "QZsg2uSArntXSz58ukSxxVBj4eXTd5a+O3AWsHNb0bcz+b1HO7fQVLt64eDv" +
           "JFT732Tqkqg2lTXN/IIlr19tOyRlCIXqZPkiI/wsQ+3l5eJ5yO8LNV6WVIsM" +
           "NUepwG78L3/ZeYbuylsGWdPr5S96HfI2LOLdn9n+WTWHKC7LtxwqiAM7GhXd" +
           "BSgsvk76iJmV3yfHtPMLu/c+fvVrLwr4BfTBs6IyqYUkJm+jAequL8vN51W9" +
           "q/dG42t1G/w4a+RNi3cFzZeN94/+CxlpG3QLFgAA");
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
          1169871682000L;
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
      1169871682000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
