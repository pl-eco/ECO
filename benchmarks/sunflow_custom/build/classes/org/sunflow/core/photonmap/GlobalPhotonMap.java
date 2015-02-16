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
          1163562676000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Ye2wUxxmfO7+NYx8mgOvyNCaKIb2tgxKVOqJ1jAHDAS42" +
                                                        "tHFEzHhvzrd4X9mdsw8nbgNSa5qqKEpNStrEilpQXhCiNog+ROU8eCQQVamq" +
                                                        "Vo3UEOWfRqT8wR8kUdM2/b7Zvd27vQemqnrSzs3OfM/55vvNN3v8KqmwLbLa" +
                                                        "NNR9w6rBoyzNo3vVu6J8n8ns6ObYXb3Uslm8S6W23Q9jg3LLyw0ff/ZYMhIm" +
                                                        "lQNkHtV1g1OuGLq9g9mGOsriMdLgj3arTLM5icT20lEqpbiiSjHF5h0xMieL" +
                                                        "lZPWWMYECUyQwARJmCB1+lTAdAvTU1oXclCd2w+Sb5NQjFSaMprHyfJcISa1" +
                                                        "qOaK6RUegIRqfN8FTgnmtEWWeb47Puc5fHi1NPXjByK/KCMNA6RB0fvQHBmM" +
                                                        "4KBkgNRpTBtilt0Zj7P4AJmrMxbvY5ZCVWVc2D1AGm1lWKc8ZTFvkXAwZTJL" +
                                                        "6PRXrk5G36yUzA3Lcy+hMDWeeatIqHQYfF3g++p4uAHHwcFaBQyzElRmGZby" +
                                                        "EUWPc7I0yOH52LoFCIC1SmM8aXiqynUKA6TRiZ1K9WGpj1uKPgykFUYKtHDS" +
                                                        "XFQorrVJ5RE6zAY5aQrS9TpTQFUjFgJZOJkfJBOSIErNgShlxefqtnsOPaRv" +
                                                        "0sPC5jiTVbS/GpiWBJh2sASzmC4zh7FuVewJuuDMwTAhQDw/QOzQnH742tfv" +
                                                        "WDJzwaH5YgGa7UN7mcwH5aND9e8s6mpbW4ZmVJuGrWDwczwX27/XnelIm5B5" +
                                                        "CzyJOBnNTM7sOHffIy+wj8KktodUyoaa0mAfzZUNzVRUZm1kOrMoZ/EeUsP0" +
                                                        "eJeY7yFV0I8pOnNGtycSNuM9pFwVQ5WGeIclSoAIXKIq6Ct6wsj0TcqTop82" +
                                                        "CSFz4CEReGaI8xP/nCjSThu2u0Rlqiu6IcHmZdSSkxKTjcEhWN2kRq0RW5JT" +
                                                        "Njc0yU7pCdUYk2xLlgxr2HuXDYtJZtLghq5RU9qoGkNU7RXvW6kZxS1n/j+V" +
                                                        "pdHzyFgoBEFZFIQEFbJpk6HGmTUoT6Xu7b720uDFsJci7ppx0g46o67OKOqM" +
                                                        "ejqjAZ2tTo+EQkLjrWiCswUggCMABQCSdW19uzfvOdhSBnvPHCuH1UfSFnDd" +
                                                        "tatbNrp8vOgRqCjDpm362f2T0U+f/ZqzaaXi4F6Qm8wcGdu/6ztfDpNwLkqj" +
                                                        "nzBUi+y9iK0ehrYGs7OQ3IbJDz8++cSE4edpDuy78JHPienfEoyIZcgsDoDq" +
                                                        "i1+1jJ4aPDPRGiblgCmAo5zCvgeIWhLUkQMDHRlIRV8qwOGEYWlUxakMDtby" +
                                                        "pGWM+SNiq9SL/lwISjXmRT08v3cTRfzj7DwT21udrYVRDnghIHvDr2eePPWT" +
                                                        "1WvD2ejekHVe9jHuYMVcf5P0W4zB+F+P9P7o8NXJ+8UOAYoVhRS0YtsFyAEh" +
                                                        "g2X97oUH/3L5vaN/DHu7iqSB9TZfOMCJCpCGIW/dqWtGXEkodEhluCf/2bCy" +
                                                        "/dTfD0WcIKowktkDd9xYgD/+hXvJIxcf+GSJEBOS8TjzHfbJHL/n+ZI7LYvu" +
                                                        "QzvS+/+w+Mnz9GlAW0A4WxlnArQqhEMVIjbzOVmYnZAawBsALByPazLzTXnz" +
                                                        "uxhuvTU4315QiqLBqYUeGhZOrxHBjQrSNtF+CVfUXVd8vxubZWbeXFqMNIm3" +
                                                        "OvCyrXiabsACICu9/7FdHTrwwadi8fIStMC5F+AfkI4/1dy17iPB72cKci9N" +
                                                        "56MfFEs+750vaNfDLZVnw6RqgERktxLbRdUU7scBqD7sTHkG1VrOfG4l4Ryb" +
                                                        "HR4SLApmaZbaYI76qAt9pMZ+baG0xPPrVTctXw2mZYiIzjpsWjls4tJR6LUU" +
                                                        "DQ7nUbd6kCYaL4889eEJB2SDSx4gZgenHv08emgqnFWPrcgribJ5nJpM+HSL" +
                                                        "49Pn8AvB82980BcccM7kxi63MFjmVQamie4sL2WWULHhbycnfvvcxKTjRmNu" +
                                                        "OdIN1faJP/3rUvTI+28WOO8gKAYVABBx9vfa/NV/zV3914qs/pbM6u/DzvrS" +
                                                        "wl53hb1eRNi2jLDxGwhrhOcNV9gbRYR9wxVWFlecWrwTm24nbzeC93bSsEp4" +
                                                        "Px+es66Os0V0fNPVUamLUwff+otLxM45V+K5IhIHXInlccppIbPLAABLL8x5" +
                                                        "V8X5Iir2uCoqTGPMgY3dpQVecAVeKCJQzgj0krywQFxRshKet1yBb+UJDHnn" +
                                                        "JKJMtAfuQsPMavzgmaOf7J/8ShhPi4pRRCPY7BGfblsK73DfO3548Zyp938g" +
                                                        "jsaM6IRQ3yLaldjcLiA7DEGzxU0Q7VZ0qqY5qerrjfX0D35rFi5cdF24WMAF" +
                                                        "7CRhOIQds4h+7GrY6L7i+2ah+JKr+FIJxUL46E0oHriB4tvhedtV/HYJxWXY" +
                                                        "eXg2imsdxVs7+7Zk6U4XZhULqWWfuKLmWVzsUimQ8eiBqen49mPtYfcg3wQR" +
                                                        "d+/6vpxyFJNTkW8Vd2j/xHz0+RdP83dWf9UB2FXFz5cg4/kDV5r71yX33EQd" +
                                                        "vjTgUFDk81uPv7nxNvnxMCnzDt68zwK5TB25x22txXjK0vtzDt0luSm/Ap53" +
                                                        "3WC/Gwy2iJPI90DNFPIjtVtQPVaiqHocmx9yKJwY7zNVhXemFbsQ2pWPGko8" +
                                                        "v/gSA9/3zMbqnSyA57Jr9uX/3uyfljD7aWyOcFI9zHiXYVhxfF8/e/OuuOZd" +
                                                        "mbV5ZUKiSKv1fiPof17C0GPYPOMYuh7K7fabNPS6a+j1/4WhL5Yw9AQ2z/mG" +
                                                        "3lnEUECMSufKnanqV83+sg453pT3ldD5siW/NN1QvXB655/FtdP7+lQTI9WJ" +
                                                        "lKpmF6pZ/UrTYglFOFDjlK2m+HuFk+biZnFS4/WFF790uE5zEglywc7Hv2yy" +
                                                        "33AyJ4sMsNvtZROdgQIBiLD7OzOzVFkHpVO2p0kOlJpBYF2Rg3LiO2wGkVLO" +
                                                        "l9hB+eT05m0PXbv7mIC3Clml46Joq46RKucK7qHa8qLSMrIqN7V9Vv9yzcoM" +
                                                        "VNdj0+jeuwO2LS18T+3WTC5uluO/WvjKPc9OvyeKgP8AOJ4aqCAXAAA=");
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
          1163562676000L;
        public static final String jlc$ClassType$jl7 =
          ("H4sIAAAAAAAAAL1Yb2wUxxWfW/93jG1MAMcFY2yDaqC3JS1tqaOkxjJgeoCF" +
           "gSoXNc7c7tx58d7OZnfOPjt1SagqUD4QAoZAlFgVIn8IDkRJUVpVkfiSf0oV" +
           "KVHUqh8a2n5pVIpUPjSNmrbpm5n9c7d35yZqVUuem51978178977vTe7cBPV" +
           "uA7aaFNzOmNSFid5Fj9kbomzaZu48V2JLSPYcYk+aGLX3Q9rY1r3Sy0ff3pi" +
           "vFVBtUm0DFsWZZgZ1HL3EZeak0RPoJZwdcgkWZeh1sQhPInVHDNMNWG4rD+B" +
           "bitgZag34augggoqqKAKFdSBkAqYlhArlx3kHNhi7oPohyiWQLW2xtVjaG2x" +
           "EBs7OOuJGREWgIR6/nwQjBLMeQd1BbZLm0sMPr1RnXvi/taXq1BLErUY1ihX" +
           "RwMlGGySRE1Zkk0Rxx3QdaIn0VKLEH2UOAY2jRmhdxK1uUbGwiznkOCQ+GLO" +
           "Jo7YMzy5Jo3b5uQ0Rp3AvLRBTN1/qkmbOAO2rghtlRZu5+tgYKMBijlprBGf" +
           "pXrCsHSG1kQ5Aht7vwsEwFqXJWycBltVWxgWUJv0nYmtjDrKHMPKAGkNzcEu" +
           "DHVUFMrP2sbaBM6QMYbao3Qj8hVQNYiD4CwMLY+SCUngpY6Ilwr8c3PPXccf" +
           "snZaitBZJ5rJ9a8Hps4I0z6SJg6xNCIZmzYkzuAVrx1TEALi5RFiSfPqD259" +
           "Z1PntbckzZfK0OxNHSIaG9MupJrfWzXYt7WKq1FvU9fgzi+yXIT/iPemP29D" +
           "5q0IJPKXcf/ltX1v3PvwC+SGghqHUa1GzVwW4mipRrO2YRJnB7GIgxnRh1ED" +
           "sfRB8X4Y1cE8YVhEru5Np13ChlG1KZZqqXiGI0qDCH5EdTA3rDT15zZm42Ke" +
           "txFCy+AftSMUO47En/xlyFAPuBDuKtawZVhUheAl2NHGVaLRsRSc7ngWOxOu" +
           "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");
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
      1163562676000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "bfvpoqcvfnTusPhy81+RZ/nbsyEAAA==");
}
