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
final public class CausticPhotonMap implements CausticPhotonMapInterface {
    private java.util.ArrayList<Photon> photonList;
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
                                                     new java.util.ArrayList<Photon>(
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
    private void locatePhotons(NearestPhotons np) {
        float[] dist1d2 =
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
                                                        size()]);
                             photonList = null;
                             Photon[] temp = new Photon[storedPhotons +
                                                          1];
                             this.balanceSegment(
                                    temp,
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
                                             getExtents();
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
                                                   this.
                                                     swap(
                                                       i,
                                                       j);
                                               }
                                               this.
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
                                                               getMaximum().
                                                               x;
                                                           bounds.
                                                             getMaximum().
                                                             x =
                                                             temp[index].
                                                               x;
                                                           this.
                                                             balanceSegment(
                                                               temp,
                                                               2 *
                                                                 index,
                                                               start,
                                                               median -
                                                                 1);
                                                           bounds.
                                                             getMaximum().
                                                             x =
                                                             tmp;
                                                           break;
                                                       case Photon.
                                                              SPLIT_Y:
                                                           tmp =
                                                             bounds.
                                                               getMaximum().
                                                               y;
                                                           bounds.
                                                             getMaximum().
                                                             y =
                                                             temp[index].
                                                               y;
                                                           this.
                                                             balanceSegment(
                                                               temp,
                                                               2 *
                                                                 index,
                                                               start,
                                                               median -
                                                                 1);
                                                           bounds.
                                                             getMaximum().
                                                             y =
                                                             tmp;
                                                           break;
                                                       default:
                                                           tmp =
                                                             bounds.
                                                               getMaximum().
                                                               z;
                                                           bounds.
                                                             getMaximum().
                                                             z =
                                                             temp[index].
                                                               z;
                                                           this.
                                                             balanceSegment(
                                                               temp,
                                                               2 *
                                                                 index,
                                                               start,
                                                               median -
                                                                 1);
                                                           bounds.
                                                             getMaximum().
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
                                                               getMinimum().
                                                               x;
                                                           bounds.
                                                             getMinimum().
                                                             x =
                                                             temp[index].
                                                               x;
                                                           this.
                                                             balanceSegment(
                                                               temp,
                                                               2 *
                                                                 index +
                                                                 1,
                                                               median +
                                                                 1,
                                                               end);
                                                           bounds.
                                                             getMinimum().
                                                             x =
                                                             tmp;
                                                           break;
                                                       case Photon.
                                                              SPLIT_Y:
                                                           tmp =
                                                             bounds.
                                                               getMinimum().
                                                               y;
                                                           bounds.
                                                             getMinimum().
                                                             y =
                                                             temp[index].
                                                               y;
                                                           this.
                                                             balanceSegment(
                                                               temp,
                                                               2 *
                                                                 index +
                                                                 1,
                                                               median +
                                                                 1,
                                                               end);
                                                           bounds.
                                                             getMinimum().
                                                             y =
                                                             tmp;
                                                           break;
                                                       default:
                                                           tmp =
                                                             bounds.
                                                               getMinimum().
                                                               z;
                                                           bounds.
                                                             getMinimum().
                                                             z =
                                                             temp[index].
                                                               z;
                                                           this.
                                                             balanceSegment(
                                                               temp,
                                                               2 *
                                                                 index +
                                                                 1,
                                                               median +
                                                                 1,
                                                               end);
                                                           bounds.
                                                             getMinimum().
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
                                             getDiffuseDepth() ==
                                             0 &&
                                             (state.
                                                getReflectionDepth() >
                                                0 ||
                                                state.
                                                getRefractionDepth() >
                                                0)) {
                                           Photon p =
                                             new Photon(
                                             state.
                                               getPoint(),
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
                                                       getMax());
                                           }
                                       } }
    public void init() { UI.printInfo(Module.LIGHT,
                                      "Balancing caustics photon map ...");
                         Timer t = new Timer();
                         t.start();
                         this.balance();
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
                                        toString());
                         if (gatherRadius > maxRadius)
                             gatherRadius =
                               maxRadius; }
    public void getSamples(ShadingState state) { if (storedPhotons ==
                                                       0)
                                                     return;
                                                 NearestPhotons np =
                                                   new NearestPhotons(
                                                   state.
                                                     getPoint(),
                                                   gatherNum,
                                                   gatherRadius *
                                                     gatherRadius);
                                                 this.
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
                                                           getNormal());
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
                                                               getPoint(),
                                                             pvec);
                                                         float pcos =
                                                           Vector3.
                                                           dot(
                                                             pvec,
                                                             state.
                                                               getNormal());
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
                                                                     getPoint(),
                                                                   pdir.
                                                                     negate()));
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
                                                               getDiffuseRadiance().
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
            super();
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
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1169871682000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAALVZC2wUxxke3/ltBx8GzCMEY2OggLkDO4TajkIc24DhAMcG" +
           "kxioGe/OnRf2dpfd\nOXMYRBNFAQJqA0ortVJDaEXFI6FBIi1tlVIoSZqERk" +
           "oiNZEihbZCaiu1qRpVSiGQqP/M7N7u7d05\nkApLtzs7j//9f/PP+KVPUJFl" +
           "oumSFaa7DGKFO/p6sGkRuUPFlrUeugal14vKek6s1vQAKoiigCJT\nVBWVrI" +
           "iMKY4ocqS7sy1logWGru6KqzoNkxQNb1OX2PRWRZdkEdx49Hz1k8cLawOoKI" +
           "qqsKbpFFNF\n17pUkrAoCkW34REcSVJFjUQVi7ZF0T1ESyY6dM2iWKPWDrQX" +
           "BaOo2JAYTYrqog7zCDCPGNjEiQhn\nH+nhbIHCBJNQrGhEbk+zg5WNmStBbH" +
           "tdb/ZsIFLKBvtBHS4BaD0zrbXQNktVI3iy/4E9x04FUdUA\nqlK0PkZMAk0o" +
           "8BtAlQmSGCKm1S7LRB5A4zVC5D5iKlhVRjnXAVRtKXEN06RJrF5i6eoIm1ht" +
           "JQ1i\ncp5OZxRVSkwnMylR3UzbKKYQVXa+imIqjoPaNa7aQt3lrB8ULFdAMD" +
           "OGJeIsKdyuaODxWv+KtI4N\nq2ECLC1JEDqsp1kVahg6ULXwpYq1eKSPmooW" +
           "h6lFehK4UDQtL1FmawNL23GcDFI0xT+vRwzBrDJu\nCLaEokn+aZwSeGmaz0" +
           "se/yyo+ezAyR9deJjHdqFMJJXJXw6LZvgW9ZIYMYkmEbHwejL8ve7Hk9MD\n" +
           "CMHkSb7JYk777PMbon//ba2Yc2+OOeuGthGJDkprj9T27l6hoyATo9TQLYU5" +
           "P0Nzng499khbyoCs\nrUlTZINhZ/Bi7xuPP3Ga/COAyrtRsaSryQTE0XhJTx" +
           "iKSswVRCMmpkTuRmVEkzv4eDcqgXYUQl70\nrovFLEK7UaHKu4p1/g0migEJ" +
           "ZqIyaCtaTHfaBqbDvJ0yEEIT4IemwO86En/8TdFD4YiV1GKqvjNi\nmVJEN+" +
           "Ppb0k3ScQY1qmuJbAR6cBJCzKlh3eswUaYxZFB0UBkWE+QCJawpmh6JK5A5k" +
           "r6QpmMsPf/\nRT3F5K/eWVDAANGf2CrkxEpdlYk5KJ249vaertXPHBBBwwLd" +
           "1pyiVmAatpmGGdNwmmnYz7RhLcGQ\n1VR0WKiggLOeyGQRHgV/bIfMBgysnN" +
           "e3ZdXWA/VBCCVjZyEYk02tB5VtAbskvcNN/26OlBLE4JSf\nbNofvn5imYjB" +
           "SH6Uzrm64t0zV479p3J+AAVyQyhTHEC8nJHpYbibhsYGf9Llov+vg2vOfXDl" +
           "42+4\n6UdRQxYqZK9kWV3vd5GpS0QGnHTJH59aFdyI+o8EUCFABcAjlx+QZ4" +
           "afR0Z2tzlIyXQpiaKKmG4m\nsMqGHHgrp8OmvtPt4bET4m0W+6Us3GvASXb4" +
           "izcbnWSwZ42INeZtnxYcia8/Vbzow1crXudmcUC7\nyrMt9hEqIGC8GyzrTU" +
           "Kg/+Mf9Dz3/U/2b+KRIkIFpWDmHHcmpLwKsMP817BBS+iyElPwkEpYoN2q\n" +
           "mr345//8bkh4RIUex6GNX03A7Z/6CHriyrf+O4OTKZDYluNK704TSkxwKbeb" +
           "Jt7F5Eg9+f59P/w9\nfh4QEVDIUkYJB5YgVyjIDT2ZosnedEsABAEIwhbWDE" +
           "SneEsaU0kANI5w717bV/+bNze8sF9kxLwx\n6hbvqkHp23/68/bgs5eGxDr/" +
           "9uCbfGTG8b+eu9Y7UZhR7KGzsrYx7xqxj/K4qDKYw+rG4sBnv7ag\n7qW9vV" +
           "dtiaozd4MuqJj+tusymfvgd/6SA6iCil1ILQZm7P0AZZGmY8pFCPO+efy5kI" +
           "WQHUjs+yH2\nqAcJG/PYLkfxNCjtOR2vT+5461dclgrsrcK8kQ24KIwQYo9Z" +
           "zBCT/YC4ElvDMO/+i2s3h9SLN4Hi\nAFCUoGix1pmA0KmMvLBnF5V8dOlyzd" +
           "b3giiwHJWDovJyzCEFlUEuE2sYwD1lLHuYZ2poJ8vgEFcZ\ncSNMsw2Q8nyV" +
           "WmPGz3JWerlgNDjUeDL69rrnuQHyYmmO0PLRGb2w4ej1d+hVTscFNba6LpW9" +
           "c0G5\n6q795gcj44vPvpAIoJIBFJLsgrofq0kGHQNQ/1lOlQ1Fd8Z4Zi0nCp" +
           "e2NGhP98e2h60fTt1AhDab\nzdqVuRC0Gn43bAi94UfQAsQbUfaYw4JXT2oy" +
           "+1gisJU9l7HHGuGzR/L6dkUm16nw+9zm+nkero/a\nXAMGl7vVx7L3LrDsT7" +
           "PclYvlxrvAclOa5Wgulptvn2XIKQhv2ixv5mG5lS9p4M+5dqVDUYlhKiOY\n" +
           "nZlQMCEKNb+X8R0KMxN+t2xhbuURhle0Q8A+rtOVBBt+wCwZ0nWVYM0ninL7" +
           "olQ7onxhi/JFHlGM\nnHYpM0ydQiISGSxTJMO+2cRqCs/WxzdUtmOcOtw5ob" +
           "dl01O8lCuDAya21rp5CMd61ioAAJmdH9HS\nxAaluVvO//vSBTKXg2+pYkG+" +
           "t5vxHIctz5pP8Wmy5sPYUV6OFQ5hS2S+/5SafQjNOFty74+z884w\nDDhWlH" +
           "N1WlpaFi0F9atBfXY5EVZkGzs7318+dDqmnZYDnD7n1s6W2NqX8R6POYK6Yb" +
           "HTl+eaw6bU\nsM6wWPV5j4dJd+ees6sqS398aB+nb9uyzHOSs79LRrC51rsL" +
           "V3DBm5YublraQpF89842rc1N9zc2\nL17YDFFbvn5ld1+YBwsTZNSJnL1wos" +
           "w2HVPYjk0WraD6OL63sm2AFxneQVCpsLervdOXEDvuMCHq\n4PelTfbLPAnx" +
           "DHtAKV8EhRAR9EcyqUwZi4pTQTbdyYFNtETMCdctXrSoqXkpJ7Y7w51L77Y7" +
           "mxub\nFi1sWuK4k1uBiXHYMQn7eNrniINjOCKVjTAB1obKtdji11be2oefKe" +
           "7Ld7HCy9P9j31auQ+/tiVg\n141gkmL7vsulE2BkMo6xa3iuuzXLwVMvnqfv" +
           "LWgVVe78/OjkXzi/9dhobevLh77G4bXWp5if9PiR\nex8NDitv8ny3S6CsK7" +
           "LMRW2ZhU85yJM0tfUZ5c/MzC26ETC+UsSveHsDmHuW78++Uj1g29UO8HxH\n" +
           "JA6fnMqJMWr9F9njOIQUVMgk68BQOKIrshtdP/2qNE+HDvs4lqnsHFBykq3s" +
           "pNtWtsC+C/n62cxp\n/2IME/yaPc7BKUUaJtL2dlm2r21Y9xlX+VduV3nYqc" +
           "dl3vw40i+4A+nZCTfr8llcmErRj3Zv/iz6\nxxtin3UuNStgP4olVdVbfXva" +
           "xYZJYgrXuELU4mKLvUzRtPxysRLEaXM1fidWvUFRyL8K4oW9vNPe\nAsz0TI" +
           "OKym55J/0Bij6YxJrvGI6tQu4mJM4imeDE7DMrAyr4fwWcdE6K/wsMSo+d2T" +
           "QzdWj9YY4R\nRZKKR3mlWw57tbj0SUNCXV5qDq130dmX+1/9WYuDd/wEOzHl" +
           "QnBG+C8To2MEDcBQ7iuXroRB+SXJ\n6C8nv/LgiaNXA/yu539d6CRyzBkAAA" +
           "==");
    }
    private static class Photon {
        float x;
        float y;
        float z;
        short dir;
        int power;
        int flags;
        final static int SPLIT_X = 0;
        final static int SPLIT_Y = 1;
        final static int SPLIT_Z = 2;
        final static int SPLIT_MASK = 3;
        Photon(Point3 p, Vector3 dir, Color power) {
            super();
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
                encode();
            this.
              power =
              power.
                toRGBE();
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
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1169871682000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAK1YfWwUxxWfu/Pn2a0/AOMQwNiYJGBy13ynOCpxjSHGa/vq" +
           "sw0YqFnvzp0X9naX\n3Tn7bCgNioppUNKgJFIrFUIjq5CUhFS0IlGTlJTQpE" +
           "FVSatSFSk0LVJbqU2lqlJC1f7RNzO7t3d7\nH4CpJe/N7rx57/fmvfnNmzn5" +
           "CSq1TLRYskJkysBWqDMaEU0Ly52qaFmD8GlUOl9aGTneo+l+5BOQ\nX5EJqh" +
           "EkKyyLRAwrcrh7XXvKRG2Grk7FVZ2EcIqEdqoP2Po2Cg/kKNx09Ez9/tmSJj" +
           "8qFVCNqGk6\nEYmia10qTlgE1Qo7xQkxnCSKGhYUi7QL6HNYSyY6dc0iokas" +
           "3WgfCgiozJCoToKaBcd4GIyHDdEU\nE2FmPhxhZkHDPBMTUdGw3JE2ByNXZ4" +
           "8E2Pa4gVxpUFJBO4fBHYYAvF6W9pp7m+OqETgx/ODeYy8F\nUM0IqlG0KFUm" +
           "gScE7I2g6gROjGHT6pBlLI+gOg1jOYpNRVSVaWZ1BNVbSlwTSdLE1gC2dHWC" +
           "CtZb\nSQObzKbzUUDVEvXJTEpEN9NzFFOwKjtvpTFVjIPbDa7b3N319Ds4GF" +
           "QAmBkTJewMKdmlaBDxJu+I\ntI+tPSAAQ8sTmIzraVMlmggfUD2PpSpq8XCU" +
           "mIoWB9FSPQlWCFpUUCmda0OUdolxPEpQo1cuwrtA\nqpJNBB1C0AKvGNMEUV" +
           "rkiVJGfNoaPj144rtvP8pyu0TGkkrxB2HQUs+gARzDJtYkzAdeS4ae696S\n" +
           "XOxHCIQXeIS5TMeKM0PCX3/axGVuzyPTP7YTS2RU6jvcNLBng44CFEaFoVsK" +
           "DX6W52w5ROye9pQB\nq7YhrZF2hpzOswM/3/L4y/hvfhTsRmWSriYTkEd1kp" +
           "4wFBWbG7CGTZFguRtVYk3uZP3dqBzaAqQ8\n/9ofi1mYdKMSlX0q09k7TFEM" +
           "VNApqoS2osV0p22IZJy1UwZCaB78o0aEfBsR++O/BH0pFLaSWkzV\nJ8OWKY" +
           "V1M55+l3QTh41xnehaQjTCnWLSgpUSYR96RSNE88ggaCQ8ridwWJRETdH0cF" +
           "yBlSvpd8t4\ngv7ekvYUxV8/6fNRQvQubBXWxGO6KmNzVDp+9YO9XT3fPMiT" +
           "hia67TlB94LRkG00RI2G0kZDXqOt\nvIV8PmZyPsXAIwlx2AUrGrivemV0+8" +
           "YdB1sCkELGZAmdShBtAVdtYF2S3uku+27GkBLkXuOLW2dC\n146v5bkXLszO" +
           "eUdXXXzlwrF/Va/yI39+6qQOA3kHqZoI5ds0JbZ6F1s+/f94svf0pQsf3eUu" +
           "O4Ja\nc9ggdyRdzS3e0Ji6hGXgR1f97G01gU1o+LAflQBFAC0y/MA4S702sl" +
           "Z1u8OQ1JdyAVXFdDMhqrTL\nobUgGTf1SfcLy5la1qY5X0HT/PMQpE123rNf" +
           "2rvAoM8GnmM02h4vGANfe6LsC797s+o8mxaHrGsy\ntsMoJnzp17nJMmhiDN" +
           "8/+nbk2ec/mdnKMoWnCkqB5B2uJCx1FeiGxq91SEvoshJTxDEV00T7b82K\n" +
           "e37896dreURU+OIEdPX1Fbjfb/syevzCVz9bytT4JLrVuOhdMe7EPFdzh2mK" +
           "UxRHav+vl3znPfEI\nMCGwj6VMY0YoAeZQgE30QoIWZi6zBFAPkB9sXfc5/Y" +
           "05/cOY5tF9eRUoCdhMqHO6yeITYlIr2fNu\nOo/2bNL3B+mjBcCvLrCk8lQO" +
           "o9Lel+Mtyd2/eINNS5WYWYJkhhdIoZ1nFH0sp+Fb6GWFx0RrHOTu\nP9u3rV" +
           "Y9+x/QOAIaJdixrX4T6CmVlRy2dGn55XfONez4MID861FQ1UV5vcjWFaqEhM" +
           "bWODBbylj7\nKEva2kmaxrXMZcQmYZE9AamMtyCAW1mYVtbTusNdkaNjq08I" +
           "H/QfYRNQkFDybLsePdNvDx299kty\nhelxVzYd3ZzKpW2o1dyxD1+aqCt77Y" +
           "WEH5WPoFrJriaHRTVJ188IFD+WU2JCxZnVn13I8F27Pc1c\ni72skmHWyynu" +
           "dgFtKk3b1floBALg67FppMdLIz7EGuvo4w6CfDRVGjOLeFNJQDEwwXjt6oGW" +
           "t94f\nemGG7wVFgpY1alT6+h8+3hX41jtjfJw3Mh7hw0tn/3z66sB8TiC8al" +
           "yeU7hljuGVI3O9xqAONBez\nwKTfbWs+uW/gio2oPrv+6YIzwl+mzuE7H3nq" +
           "j3m2ZgiDLhLOv/T5EH108ZReUzD11+YGRbCDIhQI\nyoATlCna6PFYjM7BYq" +
           "9tsbeAxU2Oxel8FjffpMV6sNRnW+wrYHGbbTEgK/ysscHgVgSYaKAT0zvR\n" +
           "2+cAot8G0V8AhGyDKDX0SZwDIwAbggcEngOIiA0iUgCE6oBIr/K4x2rixq0u" +
           "oF9XgLUB2+pAjlVf\nevenXBTqhgNbHJv1fzo2+9n+mYf9dNssnaCcBQuk1p" +
           "XrS9KD5oGTzy+peu7jQ3T9GI7q3cx8K3ve\nySsHP/VH0UQgtzKLHVlTBJVH" +
           "I0L34OjmfD6ac/AxavsYzeMjbVi0kza+lgcgbTPqmHKRbcmHbN8c\nkA3ayA" +
           "aLIGMIvnGDyEbyITtwk8juAkRDNrKhIsgCtPHU9ZAFObLejmhPPnBPFwGXyp" +
           "sw5YapTMC5\nkhvIqhsgZZcUOoMzXp/Z/M/qA+K72/12ldULeWdfjbh6Sqia" +
           "rJNPL7tycHf4J1/6wRnyYdsavj2s\nKrzReQeuWnNsumnNqUNzOO80eRzzqq" +
           "6buP0rgXHlfT+7FeEFQ85tSvag9uwyIQh4kqY2mFUsLMtm\nquWQEzvs3Njh" +
           "zQ0WWkZTnsLWx0+ULP5MarZI5ft9+vgeQdUWJlFDVUhHSrG8pFsyoSuym0Uv" +
           "Xi/F\n0xlCX45k+7QMoGHbJzx3n35YxKfT9PEqQRVxTDp13WQ7So+L/9St4L" +
           "8fYBg2fuOG8dsnHQbEfTD5\nnxTx5C36eJ17sg7OUfd4PHnjVj3ZY3uy5//h" +
           "yfkinrxHHz9zPbnX48m5G/UESK6MX7E4p762m7id\noQV1zu0uv5GUhMt7tn" +
           "0q/Pbf7H4hfWtYJaCKWFJVMyv8jHaZYeKYwlys4vW+wX4uErSoMC6CKtNt\n" +
           "5sav+KjfEFTrHQXLj/5kil0iqCpDDFjabmUK/R6KJRCizcuGM1cZtQM/72RT" +
           "Op2f5VkEy67dHRJM\n8ov3UWnzK1uXpQ4NPsOYtVRSxWlWowYFVM5vV9JE2l" +
           "xQm6PrInrt1PCbr37R2SXYKXl+yt25snL4\nId5bJFmAvPPfbXQlDMJuI6Zf" +
           "X/ijR44fvcLqJeN/+N2E1i0ZAAA=");
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169871682000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVaDXAU1R1/d/kiH5AQIAmBEAhRJIS7JHwFYpWQBAlcIE1C" +
       "kADGl713l4W93XX3\nXTiQWqwtUPGL0TrSKfhROyhqtdKW2loLg1qr/VDbwg" +
       "xTsS39sLXaOs5YHDu2//f23d3e3geE1JvZ\nd3v73vt//3/v/97ek++hHNNA" +
       "0yXTQ3foxPS09XZjwyT+NgWbZh88GpReysnvPrJG1dzI5UNu2U9R\nsU8yvX" +
       "5MsVf2ezvbWyIGmqdryo6golEPiVDPVmWRoLfatyiJ4IbDx0tvfTS72o1yfK" +
       "gYq6pGMZU1\ntUMhIZOiEt9WPIK9YSorXp9s0hYfGk/UcKhNU02KVWrehG5B" +
       "WT6Uq0uMJkWzfFHmXmDu1bGBQ17O\n3tvN2QKFSQahWFaJvzXGDmbWJ84Esc" +
       "W8nuTRQGQc6+wHdbgEoPXMmNaWtkmq6lmP9S/e9dDjWah4\nABXLai8jJoEm" +
       "FPgNoKIQCQ0Rw2z1+4l/AE1UCfH3EkPGiryTcx1ApaYcVDENG8TsIaamjLCB" +
       "pWZY\nJwbnGX3oQ0US08kIS1QzYjYKyETxR3/lBBQcBLXL4mpb6q5kz0HBAh" +
       "kEMwJYItEp2dtkFTxe7ZwR\n07F2DQyAqXkhQoe1GKtsFcMDVGr5UsFq0NtL" +
       "DVkNwtAcLQxcKKpMS5TZWsfSNhwkgxRVOMd1W10w\nKp8bgk2haIpzGKcEXq" +
       "p0eMnmn3llH+177BsvLOexne0nksLkL4BJMxyTekiAGESViDXxQthzX+fG\n" +
       "8HQ3QjB4imOwNab1iuPrfe/8pNoaMy3FmHVDW4lEB6W1B6p7br5OQ1lMjHG6" +
       "ZsrM+Qma83ToFj0t\nER2ytixGkXV6op0nel7euPsoedeNCjpRrqQp4RDE0U" +
       "RJC+myQozriEoMTIm/E+UT1d/G+ztRHtz7\nIOStp+sCAZPQTpSt8Ee5Gv8N" +
       "JgoACWaifLiX1YAWvdcxHeb3ER0hNB4utAquUmR9+DdF13i8ZlgN\nKNp2r2" +
       "lIXs0Ixn5LmkG8+rBGNTWEdW8bDpuQKd38QRfWPSyOdIoGvMNaiHixhFVZ1b" +
       "xBGTJX0ub7\nyQj7HhP1CJO/dLvLxQDRmdgK5MQqTfETY1A6cv7VXR1rvrrP" +
       "ChoW6EJziuYBU49g6mFMPTGmHidT\n5HJxXpMZc8uF4IBtkMoAekVze7esvn" +
       "FfTRbEjr49G6zHhtaAjkKiDklri+d7J4dGCYKu4pFNez0X\njlxrBZ03PSyn" +
       "nF34+lOvPfRhUZ0buVNjJtMUULuAkelmQBvDwlpnlqWi//7tXcdOv/bWVfF8" +
       "o6g2\nCQaSZ7I0rnH6xNAk4gdgjJN/dGpx1gbUf8CNsgEbAA+5/AA1M5w8Et" +
       "K5JQqNTJc8HyoMaEYIK6wr\nimcFdNjQtsef8GAp4feTwDmFLL6nwTVTBDz/" +
       "Zr1TdNaWWcHFvO3QgkPvhdtyG848X/gSN0sUpYtt\n62AvoVbOT4wHS59BCD" +
       "x/64Hue7/23t5NPFJEqFBYHMNDiixFYMqV8SmQ7AoADnNk7Xo1pPnlgIyH\n" +
       "FMIi7j/FVzR+7x93lViuUeBJ1LP1FycQfz51Bdr92g3/nsHJuCS22MTViA+z" +
       "tJkUp9xqGHgHkyNy\n65tVB3+KDwEWAv6Y8k7CIcUlkoAJVQ7ImJRo63QuFj" +
       "e0lw+r462HeYJPRrxvAWtqgHl9mtxIsfYP\nSruOBmvCN/3sOa5WIbYXEXY/" +
       "QVq3WKHBmtnM/OXO9F6FzWEYt/DE2s0lyolPgOIAUJRgzTXXGQAw\nkQQvi9" +
       "E5eWdPniq78Y0s5F6JChQN+1diniAoHyKTmMOATRH92uU8+Eq2j2MtVxlxI1" +
       "QKA0Rsv4pA\nuLnp8WElqxziqTU4VP+Y79V1h7gB0iJDioXTQWfnC+sPX/gF" +
       "PcfpxFOUzZ4VSQZeqLbic5tPj0zM\nfebBkBvlDaASSdSD/VgJs0QYgPLFjB" +
       "aJUDMm9CeWIta62xKDoOlOeLCxdYJDHPDhno1m90UOPChi\n1p4L1ySBB5Oc" +
       "eOBC/KaVT6nl7ZxY9ubphjyCWY2ICqwFhGUG+GuarVDvDQ+Z1FbLLFq+pP7Z" +
       "VZtP\ncezOhxISm2vjokLhzu5cYOO69E530jRO9b1Ezk+531pNEqOF19liqn" +
       "Pe/XPfHZnX8KVDXJbsIWxy\nMQrAlCYbSdHM9DU7p2Vl0XjLoP+FD4LrU3Yx" +
       "Q/IHvKKYGs+9GIJ4+L5A1yNRsEgFM6xrLVi01GnR\nQal8s76+H92x2VJ61k" +
       "WsNShV/vyHpzec7dd5SBePyFCGEX+f2CokAmB8gW9J2D6ktOeg9KeaLee+\n" +
       "nv32QV4XWqZjci8C8GTfzSLfi2L5zoqEcptGQgqO0X//5cHJXzb2adwnOTw+" +
       "kmPjijTa2gkNSne9\ns+LT3b5tc9wsIQpYDmEDCkqoWD3pdlR2ArV9cNcOsy" +
       "C3JlizYWfAw0eESWnsaWyVp2h+Otpsk+ks\nBpxpC5i5nRgrtLDKdZ2VmMgF" +
       "YV239/LomzC26NPZhyJXRzQMS3gYMgDyWAAE2c3d0NTUvKRRjw5r\nGkUNWW" +
       "vdJeA6AtQo5HQbGxqaFjaLIGf7dY/sF3jc/ubKoaMB9ajfzasbvk1stYVEPn" +
       "9iw48sTTfZ\nhsS28xeUatfpJovm8TYmne27nlldNO7h/Xs4fRFg+bbNjfid" +
       "N4KNtfYK2pK8aUnD0qUNFPk/u3J/\nWePSxvrGxfObFsMS3reqs9cTR1omjT" +
       "8Be2+B7VayEZnqKL7FASNMiPu4Qw2H7J2gXHZPR2u7VQmy\ndiFrVli+W5J2" +
       "7b46cVWpgmuyIDs5zarCZ7SxdYQrYLK604YKPFZZQj5+T/uknqWbbrvokpEO" +
       "FiDz\nYsQGpTlbjv/r5AtkDi9pxskmZHmrEUyxA7fN+QAfJV1nAod5yR5bKY" +
       "qcRxfJJxMJBw72fI0aqjST\nocaQbJDVyJ5hC5o4MWKP3SVLKZI+y9htbqpv" +
       "nt+4iKIiW+hyOb4Qdzv7qTnCjY4y3GbANUVYcUqa\ncPuKCLfxJlSkxN8dC7" +
       "oK+7miIYcg80f4juv8npofv7L+wb2p6orEw0P7rEHpi2//flvW3SeHrHnO\n" +
       "UtMx+MCMR/9y7HzPZGtHYx1kzU46S7LPsQ6zeDAV66x4n5WJAx/94rxZT97S" +
       "c05IVJp4JMMg4K87\nTpE5V9/5hxSnBVkQ1A737Bmle2rgKhPuKUvjnnuFey" +
       "YOYyXQa3cR67jDIcF9o5SAcS0XEpSnkeAB\nIUGOogWb1FRcD46SayVcFYJr" +
       "RRquhwTX/CCmw8RYGw6l4nx4lJyr4ZoqOE9Nw/kRwbnI4tyD/XLY\nqm32ia" +
       "r0TsrKEw07/f/Ny5CmUkhTmUaao0Ka3CFW35hR6Jtuh74QCOrh9Q/UXCu0iE" +
       "OsJy4DNaYJ\nsaalEes7QqzCgKwAtvNtGnv0LQfvZ0fJm/GbLnhPT8P7+4L3" +
       "uBCOdLPCMBXj46NkzJhVCcZVaRj/\nKBqTwNgKi1Scnx8l5wph8qjpU3E+Ea" +
       "0JAJM6AMRS5cLJDHwjyTtVNwtiWcX80HyuvQaNndPUJa2v\nzgWtM7qwM7yt" +
       "SndUz7F27/UfFO3BL25xi6Ocu8COVNPnK2SEKI6TjaqEo9IuXivETxJuf/yJ" +
       "4/SN\necss1M6wIXZOrFv20M7qZU/vv4wD0mqHbk7SE0emfT5rWH6FV8ziYC" +
       "LpvUvipBbHLgbkCRtqX8Kh\nxMzEQ8oGuOpEqNQ5Q4WHA2uudBygOU7fMkIH" +
       "p3I6wwncWdb8mp9yENj0EicwZo9osj8elL+5WDJE\ntz38x68SM6NRXNH71O" +
       "q2ZVZ32WiKxLWEqUTFCst5/DmDMf7Gmj9C7aRoEqbEtjD/Lm6C82MxAVsW\n" +
       "uoQJui7ZBHYZP8jQ9yFr3gdnDmGFhaRD8n+ORXIm7UYh+cZLljybU8zmv0di" +
       "BGey+VdmIvj/3BIs\ncmy6feBcpbP94ZOFbx4IL14dBbAgNC702W4VmpfWL2" +
       "iavxC2CtmUhPQY7FsNF+OT9P515bKHFyia\nIPzbS4IhYpWtNjd/PBY3z4br" +
       "mPDKsUt2s9taghJVcU3IoAo7k3cVgh3M7VhPVMBVdJkKcEztges5\nocBzaR" +
       "VwYqo9TiHsqpLCrncYM0hl/xkg0VEVScjbT9h7gAXRAeX2AXIIBwl7aSP+EZ" +
       "Chn1uoIoP1\nalgzBVZ8vslzmK9sLOZjO4iXhflevmTz2WWry9BXz5o5lL2u" +
       "lh1h67pqLGIzRmeE2GcuWWzH0pLZ\n61yBhRmUa2ZNI0UFQUJ7cUhXiGPpcD" +
       "WNRcWrQNLbLA2t71F7ZnmGvhWs+RxFk7AC2rfLgUDYJKyQ\nkIjfWRPkDWma" +
       "QrAaV+yasSg2DxTaKxTbe1mKdWXoW8ea1RSVccV6SEC89BS6sd72uCJrxqrI" +
       "fqHI\n/stSZEOGvo2s6bMpYuAMiqwfiyKwhXHdLRS5+7IUwRn6JNbc4Nj8xC" +
       "UfvFTJIxSVOJdZ9iK7IumP\nYNaflyTf2Zs3f+T77cfW8Wb0D0aFPjQuEFYU" +
       "+6tE230uFMYBmWtdaL1Y1LkWWymqTF+cwGYods+E\ndsnWrBDI7JwFcMi+7M" +
       "PY2bttGNhK3NkHmRRlwSB2S2MvLJLeayS+jWD2mZ32TVdX2PqP3qB0/VOb\n" +
       "Zkb2991jvZ+SFLxzJyNT4EN51v8xONWspFdydmpRWq+jZ57uf/7bS6OFFn8d" +
       "P9l2qpEQigut3gwx\nALu31H+C6AjplL/22fmD8u9efeTwOTf/G8b/AH8Lct" +
       "FYKQAA");
}
