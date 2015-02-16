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
final public class GlobalPhotonMap implements GlobalPhotonMapInterface {
    private java.util.ArrayList<Photon> photonList;
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
        super();
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
    public void prepare(BoundingBox sceneBounds) { photonList = new java.util.ArrayList<Photon>(
                                                                  );
                                                   photonList.add(
                                                                null);
                                                   photons = null;
                                                   storedPhotons =
                                                     (halfStoredPhotons =
                                                        0); }
    public void store(ShadingState state, Vector3 dir, Color power,
                      Color diffuse) { Photon p = new Photon(state.
                                                               getPoint(),
                                                             state.
                                                               getNormal(),
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
                                                                      getMax());
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
                                                                 size()]);
                             photonList = null;
                             Photon[] temp = new Photon[storedPhotons +
                                                          1];
                             this.balanceSegment(temp, 1,
                                                 1,
                                                 storedPhotons);
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
                                             getExtents();
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
        final static int SPLIT_X = 0;
        final static int SPLIT_Y = 1;
        final static int SPLIT_Z = 2;
        final static int SPLIT_MASK = 3;
        Photon(Point3 p, Vector3 n, Vector3 dir, Color power,
               Color diffuse) { super();
                                x = p.x;
                                y = p.y;
                                z = p.z;
                                this.dir = dir.encode();
                                this.power = power.toRGBE();
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
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1163562676000L;
        final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAK1Ze2wUxxmfu/P53frBMwQwNiYJj9yVNqlSnDZxHQOOD+zY" +
                                                       "YMCQmvHu3Hlhb3ez\nO2cfBiWgSJiGNikKkVo1IahCglASUtGKVqUUwss1QS" +
                                                       "FRm0qoPFqkpEqbSlWVhKr9o9/M7N7e7T0w\nppZmbnbmm+8x3ze/+WZ85FMU" +
                                                       "tEw0U7JCdItBrFBLdyc2LSK3qNiyVkFXn3QuWNZ5sF3T/cgXQX5F\npqgqIl" +
                                                       "lhGVMcVuRw2xNNSRMtNHR1S0zVaYgkaWiT+rDN78nIw1kM1+w7XrvjQFGdHw" +
                                                       "UjqAprmk4x\nVXStVSVxi6LqyCY8iMMJqqjhiGLRpgj6EtES8RZdsyjWqPUM" +
                                                       "ehYFIqjYkBhPiuojjvAwCA8b2MTx\nMBcf7uRigcMkk1CsaERuTomDmYsyZ4" +
                                                       "La9ryubGpgUsoGe8AcrgFYPSdltbA2y1QjcKjn69v2vxFA\nVb2oStG6GTMJ" +
                                                       "LKEgrxdVxkm8n5hWsywTuRfVaITI3cRUsKoMc6m9qNZSYhqmCZNYXcTS1UFG" +
                                                       "WGsl\nDGJymU5nBFVKzCYzIVHdTK1RVCGq7HwFoyqOgdlTXbOFuUtZPxhYro" +
                                                       "BiZhRLxJlStFnRwON13hkp\nGxvbgQCmlsQJHdBTooo0DB2oVvhSxVos3E1N" +
                                                       "RYsBaVBPgBSKZuRlytbawNJmHCN9FE330nWKIaAq\n4wvBplA0xUvGOYGXZn" +
                                                       "i8lOafhVM/33Xo1ZOP89gukomkMv3LYdJsz6QuEiUm0SQiJt5KhPa2rUvM\n" +
                                                       "9CMExFM8xIKmed7x1ZG//rZO0Nybg6ajfxORaJ+0ck9d19ZlOgowNUoN3VKY" +
                                                       "8zMs59uh0x5pShqw\na6emOLLBkDN4quv8uu2Hyd/8qLwNFUu6mohDHNVIet" +
                                                       "xQVGIuIxoxMSVyGyojmtzCx9tQCbQjEPKi\ntyMatQhtQ0Uq7yrW+TcsURRY" +
                                                       "sCUqg7aiRXWnbWA6wNtJAyFUAQVVQzmFxB//peibobCV0KKqPhS2\nTCmsm7" +
                                                       "HUt6SbJGwM6FTX4tgIL1P1fqx28u8V2AixMDIoWhce0OMkjCWsKZoejimwcS" +
                                                       "X9QZkMst+7\nYZ5k2tcO+XwMDr3bWoUdsVxXZWL2SQdvjm1rbf/uLhEyLMxt" +
                                                       "uylaDDJDtswQkxlKyQx5ZDaKFvL5\nuMTJTAXhRnDCZtjOAHyV87uffnLjro" +
                                                       "YAxI8xVAQryEgbwFBbr1ZJb3H3fBuHRwkCb/pP1o+Ebh18\nTAReOD8055xd" +
                                                       "cfnNi/v/VbnAj/y5cZPZC8hdzth0MrBN4WGjd6fl4v+PF1Yc+/Di1QfcPUdR" +
                                                       "YxYU\nZM9kW7nB6xlTl4gM4OiyP3BPVWAN6tnjR0WAD4CJXH+Am9leGRlbus" +
                                                       "mBR2ZLSQRVRHUzjlU25GBa\nOR0w9SG3h4dMNW9PAueUshj/MpT37KDnv2x0" +
                                                       "isHqqSLEmLc9VnD4vfV88Vf+eKLiHF8WB6mr0s7C\nbkLFvq9xg2WVSQj0X/" +
                                                       "1h58uvfDqynkeKCBWUBMr7XErY5ypgDfNf42otrstKVMH9KmGB9t+qeYt/\n" +
                                                       "8fcXq4VHVOhxHLro9gzc/nu+jbZf/M4Xszkbn8TOGVd7l0wYMcnl3GyaeAvT" +
                                                       "I7njg1k/uoBfAxgE\n6LGUYcLRJMgNCvKFnkbRtPRdFgfcAeSDc+trzvj0rP" +
                                                       "EewuJo/AQZEpQ4HDXMevtULTDOHRziVPN5\n/SBzhO0O9r2EVQ1g/aI8ezJH" +
                                                       "3tEnbTsca0g887tf8XWtwOkJTHp8AKo0iZBk1Vzm/2leWFmOrQGg\ne+jUyg" +
                                                       "3V6qn/AMde4CjBeW91mABvyYzosqmDJVdOn5m68f0A8i9F5aqO5aWYb0xUBj" +
                                                       "uCWAOAjEnj\nscd50FcPldrYj5KIL8IMewGSaV+VoNz8/Li0lGUt7pbu6190" +
                                                       "KDLW8RpfgLyIlOPQ9vAZPrl6361L\n9Brn40IDm12fzIZ9yPTcuY98OFhT/P" +
                                                       "brcT8q6UXVkp2L9mA1wTZgL6ROlpOgQr6aMZ6ZBokzvykF\nfTO9sJQm1gtK" +
                                                       "7nEDbUbN2pW5cIg54LSNQ6e9OORDvLGcVfdR5GOhMj39CmAqcUglBjkw3tzZ" +
                                                       "8JvR\n1a+PiMOkgNMyZvVJz12/sTnw0ul+Mc/rGQ/xntkHPjp2s2uyQCCRc8" +
                                                       "7NSvvS54i8k5teZTAD6gtJ\n4NRnF9Yfebbrmq1RbWb21Ao3jI+3nCH3P/r9" +
                                                       "P+c42sENOqYCwFndxKo2EdLfyhv6LdlOecd2yjt5\nnNLjOGULa3R4JK6ZgM" +
                                                       "QztsQzeSSudyQO55K44Q4l1kI5a0s8m0fiRltiQFYEprYbQkonLDTAield\n" +
                                                       "aHyHSkyBcs5W4lweJQZsJYo1ftCzL8kjVrlDsTVQzttiz+cRG7fFFrH7s9f4" +
                                                       "AJxjHh20Caz/BVuH\nC3l0SNg6BA19SMCf4ZE6OAGpo7bU0TxShx2pKUTzSt" +
                                                       "06fqnMw2gelDFb6liWVF8qVWK4G2qDq22M\nmLV/2X/gix0jj/hZjhEcZPgM" +
                                                       "YFDt0q1MsCv5ziOvzKrYe2M3wwrDYf0cF9/I6/tFmuVn9igaBiAv\ntvjlPk" +
                                                       "lRSXdnpG1V39pcNm6fgI0XbRsv5rCRNXZAt481dudQkLV3smrE1WxdLs2+Nw" +
                                                       "HN3rU1e7eA\nZlyDl8epWW8uzfbeoWYPQLlka3apgGYB1vjx7TQrF5qtaO5u" +
                                                       "z6XcqwWUS2bz9gm2GZkRBOqsfG8U\n/OQaWfvPyp347NN+O498CqLNfjpy+R" +
                                                       "QxNhmXwxX8ScbNYV5446fH6fsLl4gDcEH+o9w7ccGS/cN1\nS47unsCVsM5j" +
                                                       "mJd1zeC9TwUGlFE/fzUSKVHWa1PmpKbMRKgc9EmY2qqMdGhOJj7NhXLFjogr" +
                                                       "3ojg\nDuXg5Endfa7HDE71swK5/TFWvUUhtSW021AV2pxULC/AFw3qiuzGzt" +
                                                       "HbBXYqQtjH4Uyb5kC5btt0\nfeI2nSxg0ylW/Zqi0hihLbpuyuy7w9X/xN3o" +
                                                       "/xCUT2z9Pxm3/gHOke/cDrfi9KMFLBlj1TlhyRNw\n1VzsseT83VrymW3JZ/" +
                                                       "8PSz4oYMnvWfWea8lXPZZcHq8lAG3F4hXKudcuGP/7FbsxZD1+iwdbKXJl\n" +
                                                       "64bPI3/4N3+BST2qVkRQaTShqulXmLR2sWGSqMItrBAXGhGeVymakV8tispS" +
                                                       "bW7Fn8SsGxRVe2fB\n7mM/6WQ3KapII4NTyG6lE30EeRkQsebHhrNUaQmDuN" +
                                                       "BlIjpbn7kZ+Mr/K+FgYEL8X6JPWvvm+jnJ\n3at+wIE1KKl4mGdJ5RFUIt6f" +
                                                       "Ujhan5ebw+syevtoz4m3vuEcEvwZYHLSPa4yQrhJjBaIFcDu3K8/\nrXGD8v" +
                                                       "ea4V9O+/mjB/dd40mS8T9XX2U6TBoAAA==");
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
                         this.balance();
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
                                        toString());
                         if (gatherRadius >
                               maxRadius)
                             gatherRadius =
                               maxRadius;
                         t.start();
                         this.precomputeRadiance();
                         t.end();
                         UI.printInfo(Module.
                                        LIGHT,
                                      "  * Precompute time:  %s",
                                      t.
                                        toString());
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
                                           this.
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
                                               toRGBE();
                                           temp[i] =
                                             curr;
                                       }
                                       UI.
                                         taskStop();
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
          1163562676000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAALVZe2wUxxkf3/ltBx8GjCEEY2OggLnDxubltMQBA4YDHBtM" +
           "sKFmvDt3XtjbXXbn\n7MMgmigKEFADBBqlUkMoouIVGiTS0lYphZKkSWikJF" +
           "ITKVJoK6S2UpuqUaWUqv2j38zu3j7uzoFU\nWGJvdvab7/395pvh1c9RgaGj" +
           "KYIRprs1YoSXd3di3SDichkbxkaY6hfeKijpPLtWUQMoL4oCkkhR\nRVQwIi" +
           "KmOCKJkY4VrSkdzdVUeXdcVmmYpGh4h9xi8VsTbclguPnk1cqnz+TXBFBBFF" +
           "VgRVEpppKq\ntMskYVAUiu7AQziSpJIciUoGbY2ih4iSTCxXFYNihRq70D4U" +
           "jKJCTWA8KaqN2sIjIDyiYR0nIlx8\npJOLBQ7jdEKxpBCxLS0OVjZ4V4La1r" +
           "quTGpgUsw+9oA5XAOwelraatPaDFO14LmehXtPnQ+iil5U\nISndjJkAllCQ" +
           "14vKEyQxQHSjTRSJ2IvGKoSI3USXsCyNcKm9qNKQ4gqmSZ0YXcRQ5SFGWGkk" +
           "NaJz\nmfZkFJULzCY9KVBVT/soJhFZtN8KYjKOg9lVjtmmuSvZPBhYKoFieg" +
           "wLxF6Sv1NSIOI1/hVpG+vX\nAgEsLUoQOqimReUrGCZQpRlLGSvxSDfVJSUO" +
           "pAVqEqRQNDknU+ZrDQs7cZz0U1Ttp+s0PwFVCXcE\nW0LRBD8Z5wRRmuyLki" +
           "s+c6u+PHjuB9ce47mdLxJBZvqXwqKpvkVdJEZ0ogjEXHg3GT7RsSU5JYAQ\n" +
           "EE/wEZs0bTOubor+5Vc1Js3DWWg2DOwgAu0X1h+r6dqzSkVBpkaxphoSC77H" +
           "cl4OndaX1pQGVVuV\n5sg+hu2P17ve3vLUBfLXACrtQIWCKicTkEdjBTWhST" +
           "LRVxGF6JgSsQOVEEVczr93oCIYRyHlzdkN\nsZhBaAfKl/lUocrfwUUxYMFc" +
           "VAJjSYmp9ljDdJCPUxpCaBz8Q9UI5T2P+J/5S9E3wxEjqcRkdThi\n6EJE1e" +
           "Ppd0HVSUQbVKmqJLAWWSWrA1ju5O/rsBZmaaRRtCUyqCZIBAtYkRQ1EpegcA" +
           "V1nkiG2O//\nwzzFtK8czstjcOgvaxkqYrUqi0TvF87eeW9v+9rnDpopw9Lc" +
           "spuiJSAzbMkMM5nhtMywT2b9eoKh\npKk5YaC8PC55PFPFDCcEYyeUNQBg+e" +
           "zubWu2H6wLQh5pw/nMn0BaBwZb+rUL6nKn9js4TAqQgNWn\n+w6E755dZiZg" +
           "JDdEZ11d9sGlW6f+WT4ngALZ8ZPZDQheyth0MtBN42K9v+Ky8f/7oXVXPr71" +
           "2Tec\n2qOoPgMSMleykq7zR0hXBSICSDrsz0yqCG5GPccCKB9wArCR6w+wM9" +
           "Uvw1ParTZMMluKoqgspuoJ\nLLNPNraV0kFdHXZmeOqE+JglfjHL9SoI0otW" +
           "8vNf9nWCxp5VZqqxaPus4DB895nC+Z+8UfYWd4uN\n2BWuPbGbULP+xzrJsl" +
           "EnBOY/e6nz+Pc+P9DHM8VMFZQCypkOJdS7DJjD4le/SUmoohST8IBMWKL9\n" +
           "t2JG40/+9nzIjIgMM3ZAG76agTM/6XH01K1v/2sqZ5MnsP3G0d4hM40Y53Bu" +
           "03W8m+mRevqjR77/\nG/wywCFAkCGNEI4qQW5QkDt6IkUT3dWWAPwBBIT9aw" +
           "EwrXb3M7qUAFwc4tG9s7/ul+9seuWAWRGz\nR2la3Kv6he/8/g87g0duDJjr" +
           "/HuDj/jY1DN/unKna7zpRnMDnZ6xh7nXmJsoz4sKjQWsdjQJnPrN\nubWv7u" +
           "u6bWlU6d0K2qFd+vPum2TWo9/9YxacCkpWF9UIwtjvQsoyTcWUqxDmc7P5cx" +
           "5LISuR2Pu3\n2KMONGzI4bssnVO/sPdCvC65692fc13KsLsFc2c24KLphBB7" +
           "TGeOmOgHxNXYGAS65uvrt4bk6/8B\njr3AUYCOxdigA0CnPHVhURcUfXrjZt" +
           "X2D4MosBKVgqHiSswhBZVALRNjELA9pS17jNdraJhVcIib\njLgTJlsOSLne" +
           "io1R82cl67scMOofaDgXfW/Dy9wBObE0S2r5+Ixc23Ty7vv0NufjgBpbXZvK" +
           "3Lig\nV3XWLv54aGzh5VcSAVTUi0KC1U33YDnJoKMXmj/DbrGh4/Z89zZyZt" +
           "fSmgbtKf7cdon1w6mTiDBm\n1Gxcng1BKwE5j1gIesSPoHmID6LsMZMlr5pU" +
           "RPbSYmIrey5jj3VmzB7PGdtVXqmTQNpRS+rRHFKf\nsKQGNK73Up/Irgcgsi" +
           "ctcnc2kZsfgMi+tMiRbCK33rvIkN0NHrNEHsshcjtfUs+fs6xOh6IiTZeG\n" +
           "MDswoWDC7NP8Ucb3qcw0YPyCpcwLOZTh7ewAiI+rdDXBmh8wiwZUVSZY8aki" +
           "3bsqlbYqxy1VjudQ\nRcvqlxJNVykUIhHBMwUi7JtNrKdwbX18Q2U7xvmjK8" +
           "Z1Lel7hrdyJXC6xMZ6pw7hTM9GeQAgM3Ij\nWppZvzBr29V/3LhGZnHwLZYM" +
           "qPc2PZ7lpOVa8wW+QNZ9EjvJ27H8AWyYle8/omaeQD0HSx79MVbd\naZoGZ4" +
           "oybk7TwiXNjYvA/kqwn11NhCXRAs8VH60cuBBTLogBLoCLa2NrLPNL+IzLH0" +
           "FVM9jZy3XJ\nYXGq36AZrP18yCWkY8Xey2vKi394eD/nbzmzxHWOs96LhrC+" +
           "3r0NW5ovblzQMp8i4YEdbZY2L2pu\nWNA4bwFkbenG1R3dYZ4sTI8RO3P2wX" +
           "Ey03PMXmT9VfJGYwzfW9k2wJsM90ewKL+rvW2FryB23WdB\n1EJyn7AK4kSO" +
           "gniOPaCVL4BGiJj8h7xcqkfjYneQjfdxXjNHnpRbtHhJUwvntccdzObFDziY" +
           "Cxqa\n5s9rarGDyX3AtDhqO4S9POsLw6FRwpDKxJcAG0PfWmjwGyt358NPFI" +
           "/kulPhzemBJ78o34/f3Baw\nusZFwMi66nL4BBgbzyF2Ha90p2M5dP7iVfrh" +
           "3KVmjzsnNzb5F85ZemqkZulrh7/G0bXGZ5if9dih\nh58IDkrv8GK3GqCM2z" +
           "HvolZv21MK+iR1ZaOn+Znm3aAbIG1PW+l72p++PLJ8d/Y16gHLr1Z65zog\n" +
           "cfDkXM6O0ulfZI8zkFLQH5OM40L+kCqJTnb96KuKPJ067OWU19iZYORFy9iL" +
           "92xsnnUT8rVrmbP+\n6Sge+AV7XIEjijBIhJ1tomjd2bDpS47tr9+r7bBNj/" +
           "Fe+9jKz7l35dnpNuPW2bwpFaKf7tn6ZfR3\n/zb3WPs2swy2olhSlt2dt2tc" +
           "qOkkJnGDy8w+3Nxeb1I0ObdarP2wx9yKX5ur3qYo5F8F2cJ+3GTv\nAmC6yK" +
           "CbskZuot9CwwdEbPi+Zrsq5GxA5jnEC03MP9M9QMH/O8Au5qT5HwL9wpOX+q" +
           "alDm88yhGi\nQJDxCO9yS2GbNi980oBQm5ObzesDdPm1njd+vMRGO356HZ9y" +
           "ANiT/MvMr6PkDIBQ9uuW9oRG+QXJ\nyM8mvv7o2ZO3A/ye538k9ytMxRkAAA" +
           "==");
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1163562676000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVaC5AUxRnu3T3uDffgcccbjkMEjl0ODzjAiAd3wMEenHfH" +
       "8c7RN9u7NzI7M870\n3i1ICGpKiCZG8jAxUcSUCYoajZoiUgal0MRoklKqgh" +
       "WrRFMYQ0VNxaRKyUOTv3t6dmdnH9wjbtX0\nzk53/6/+/+//u2cf+xCNMg00" +
       "RTL9dK9OTP/qznZsmCS0WsGm2QWPeqSXRhW1H9+gal7kCSKvHKKo\nLCiZgR" +
       "CmOCCHAq3NK+IGmq9ryt6IolE/iVP/jcpiQW99cHEawS1HT1be8lDedC8aFU" +
       "RlWFU1iqms\nqS0KiZoUlQdvxP04EKOyEgjKJl0RRKOJGouu1lSTYpWaN6ED" +
       "yBdE+brEaFI0M2gzDwDzgI4NHA1w\n9oF2zhYojDUIxbJKQk0JdjCzLnUmiC" +
       "3mdaSPBiKFrLMb1OESgNYzElpb2qapqvse7l6y/9gjPlS2\nHZXJaicjJoEm" +
       "FPhtR6VREu0lhtkUCpHQdlShEhLqJIaMFXkf57odVZpyRMU0ZhCzg5ia0s8G" +
       "Vpox\nnRicp/0wiEolppMRk6hmJGwUlokSsn+NCis4AmpPSKptqbuGPQcFi2" +
       "UQzAhjidhT8vbIKqz4dPeM\nhI61G2AATC2IEtqnJVjlqRgeoEprLRWsRgKd" +
       "1JDVCAwdpcWAC0WTshJlttaxtAdHSA9F1e5x7VYX\njCrihmBTKBrvHsYpwS" +
       "pNcq2SY33mT/j48MP3nb6e+3ZeiEgKk78YJk1zTeogYWIQVSLWxMsx/7db\n" +
       "t8WmeBGCweNdg60xTbNPbg5een66NWZyhjGbem8kEu2RNh6Z3nHzWg35mBiF" +
       "umbKbPFTNOfh0C56\nVsR1iNoJCYqs0293vtDxy20HT5D3vai4FeVLmhKLgh" +
       "9VSFpUlxVirCUqMTAloVZURNTQat7figrg\nPggubz3dFA6bhLaiPIU/ytf4" +
       "bzBRGEgwExXBvayGNftex7SP38d1hNBouNAauMqQ9eHfFH3BHzBj\naljRBg" +
       "KmIQU0I5L4LWkGCeh9GtXUKNYDaxWtFyvt/Hcb1v3MjXSKtgX6tCgJYAmrsq" +
       "oFIjIErqQt\nCJF+9j0S4nEmfeWAx8Pg0B3WCkTEOk0JEaNHOn7xlf0tG756" +
       "2HIZ5uZCb4rmAU+/4OlnPP0Jnn4X\nT+TxcFbjGG9r/cD6eyCOAfFK53buWr" +
       "/7cI0PHEcfyAPTsaE1oKEQqEXSVieDvZXjogQeV/3DHYf8\nl4+vtDwukB2T" +
       "M84uee3xV4/9o3SeF3kzAyZTFCC7mJFpZyibAMJad4hlov/XO9qePv/qW1cn" +
       "g42i\n2jQMSJ/JYrjGvSSGJpEQoGKS/EMTy3xbUPcRL8oDYAAw5PIDzkxz80" +
       "iJ5RU2LjJdCoKoJKwZUayw\nLhvMimmfoQ0kn3BfKef3Y2FxSphzT4JrqvB2" +
       "/s16x+usnWD5FlttlxYcdy/flr/wjedKXuJmsSG6\nzJEEOwm1Ar4i6SxdBi" +
       "Hw/K3vtX/rOx8e2sE9RbgKhcwY61VkKQ5TrkpOgUhXAG3YQtZuVqNaSA7L\n" +
       "uFchzOP+Uza7/mcf3FVuLY0CT+yVrbsygeTziavQwVe/+Mk0TsYjsUyTVCM5" +
       "zNJmbJJyk2HgvUyO\n+C3npt77K3w/ACGAjynvIxxPfFwzH0yqdlYqhhwFxO" +
       "vny3jx9ppfvLz5gUOW68/NUY44Z/VIX377\nnT2+b5zptea5Ud81+Mi0h957" +
       "+mLHOMtMVmqclZadnHOs9MgdoExnCzIzFwc++sX5Mx870HFBSFSZ\nCvItUA" +
       "j9ee9ZMufar/8xAwL5IIGzHw3cORcCR/a9mDK/0jDlcgT4s3m89TNP5cZFvO" +
       "8LrKkBMeuy\nGDBDYdQj7T8RqYnd9OtnuUAl2FlhOf0YYM+yRDlrZjFrVLnh" +
       "bx02+2Bcwwsbd5YrL/wbKG4HihIU\nJOYmA/A3nhIFYvSogjfPnJ2w+3Uf8q" +
       "5BxaBoaA3mAIKKIHKJ2QfQHddXXs+Ds3ygkLVcZcSNMEkY\nIO74VWrmdKI1" +
       "rKxKQk9Pb93DwVc23c8NkBU5M/iXi86+05uPXv4tvcDpJCGMzZ4ZT89LUIom" +
       "5zae\n76/If/KBqBcVbEflkiiWu7ESY0CxHWo7066goaBO6U+t06yiZEUCoq" +
       "e4HdzB1g2eSW+Eezaa3Ze6\n8LKUWXuuvQj2txMvPYjfbOBTank7J4FuBboh" +
       "92NWQKNiK78y5ID1muzAhs5Yr0kdhd7i65fWPbVu\n51me24qgvsbmxqSosK" +
       "thdx6w8bzsi+6maZzteolcHH9PJsjhmxAx1T3vnrnv989feOv9XJa8Xmxy\n" +
       "MYrBlCYbSdGM7BsaTsuKotGWQf8LHwTXZ+xihuQPeLk1MRl7CYT1802TLpCh" +
       "imaEYda1FSxa6bZo\nj1S1U9/cjb6201J65hWs1SNN+s2p81ve7Na5S5f1y1" +
       "CjklCX2EelJohkAbQiZW+V0Z490rs1uy58\nP+/te3nRbJmOyb0Skgv7XiXi" +
       "vTQR76yIqnJoJKTgOewvv7t33FeMwxpfk1HcP9J9Y3YWbZ2EeqS7\nLq367G" +
       "BwzxwvC4hiFkPYgGobynl/tu2mk0BtF9w1wyyIrTHWbNg2cfcRblKZeJqogi" +
       "hakI02T3mu\nYskdtoCZA8RYpcVUruvM1EAujum6s5d735iReZ/OPhR5Wmw3" +
       "LOduyADIbwEQRDdfhkWLGpfW6/aw\n+sGX2LXWXQqsIwCNEovs0sZlCxuEj7" +
       "OzDL8cEnDcfG5N74mweiLk5cUf30I3OTyiiD9xwIdP0022\nWXOcighKtZt0" +
       "kznzaAeT1ub9T64vLXzwzts5feFfRY6Nn/hd0I+Njc7sLiRvXLi0oZ4i/Lnt" +
       "hZY3\nNtY1LqhvhPTdta61059EWSaKmoK7B2Afmm5BprcAd1TJkWJMcn1ZDe" +
       "PsBM0KtnS0dnW1bLQKZdZe\nx5qgtXZNWVP3mtSkwgrvCkG5IktS+RJr2lga" +
       "4TqYrCx3gAJ3VRaPj9zdPLZj2Y7brpgxsqECBF6C\nWI80Z9fJv505Tebwiq" +
       "ZQNiHIm4xIhtMJx5yP8AnS9kb4KN/RJBJFqftYJ/3UJuUwxhmutqEqcxlq\n" +
       "+LEGMY1SAmwJp6UlXbd+UQO4lfT5ue41jQ119csWLFpCUanDebkch5KrfoD9" +
       "HqAor6OlqdnldQeG\n6HXThEFtw2byuiPC60abUJeSUHtSqAYX928OkXsNXG" +
       "MF97FZuN8juFf0YSXceSUJvjtECSbANU5I\nMC6LBD8QEoxStMgiNRPX+4bI" +
       "lW24xwuu47NwPSa4FgHirMW0zyql3ZwfHCLn6UJnW/dMnH8kOJdG\nONsOHJ" +
       "JjnMoyF/MfD4N5lWBelYX5CcE8v5flbNOO5ynOeI6CXH6e06GOWKXFXWI9Ok" +
       "SxZsBVLcSq\nziLWT4VYJX3YZAZhlYh7n1rQq2kKwapLnKeGKM5kuCYKcSZm" +
       "EedZIU5hFMfbWf2TaXlODZHxFOGZ\ntodmYnza9kpgnN0xnh8i52qhtq1+Js" +
       "5n7dwH8dASlWmmaHgxB994+obMy84WZBXzg/O5zlrLY/vd\n3LQ84kLuVjt/" +
       "sSOBqdlO6/nhyKGtH5Xejl/c5RUHFgDzRVTTFyiknygO5qMZpZQD0zaeEpP7" +
       "5Tse\nefQkfX3+cmsjk2Pb5544b/mxfdOXP3HnMI5Jp7t0c5Ou6J98g69Pfp" +
       "kXhmL7nfbqJXXSCletDvLE\nDLUrZes9I/WociFcc4SnzHF7CvcG1lzlOiby" +
       "pC5qTjDhVP6Q45zpLdac53t5Alu7NBjI69fkUNIn\n37hSLNjVPf9xLlXdDr" +
       "j8Ql3/oNXN4xTzbHWnpvlwZx9m2rI3esQeVZ1mlG7CDqKusQdUOQfIURwh\n" +
       "7FRVvK/L0c/lvJTDoH9nzZ8gFnl9wX68nTTfe8M0H8eVergahfkas5qvLbe3" +
       "LBtCKbmRMIegokDh\nLP6VQ/PPWPMJVFaKJsFSOOoahwUuj8QCDMe7hAW6Bm" +
       "0Bh4ye/Bx9hazxscyHFRbQqZJ78kYieRtc\nu4XkuwctudP1DyYIzmDzr8pF" +
       "8P+3b6hf7NqYB2FtldbmB8+UnDsSW7LeRv+bmIXGU9T7+e0nli2p\nu2bRgg" +
       "aGSZRE9UTGtBq+guU5Vncya0ZTNEasbieJRIl1Ru9Y5DEjWeRZcJ0Sa3Jq0I" +
       "vstbK3S5VZ\nOVSZzZoZYAdzAOsuBWaOBKBZDf2pUODTrAq4Adop2oIcfQHW" +
       "zKPsdbXstvv8kYhdC/gmtgGetG3A\noMRemqOPlYOeBooqIUOyV/cxSuya2a" +
       "XE4pEoAeju6RZKdA9aCeE8GbMWT3vtmqzSRNbLmha5pk05\nrLCWNdcBNEQI" +
       "TdkyXCmXcsusHIllrgaLvCMs886wlveGHH2drNlI0VisgPjNcjgcMwmroCTC" +
       "D5ae\nSWqxaSRazAfp3xVavDssLXbk6NvFmq0UTeBadJCweMGbWZFtI1Xkkl" +
       "Dk0rAUITn6IqzpdShi4ByK\nSCNRBPZpng+EIh8MSxEtRx/PiIprh5eUPDpY" +
       "yeMUlbnyIXtnX532hzfrT1pS8M2bd34c/P0/raNK\n+49UJUFUGI4pivOtoO" +
       "M+H7AtLHOlS6x3hDpXAphPyl5DwI4vcc9k9gxYs26mqNw9C2CffTmHHQA0\n" +
       "cQwDU4k756BbKPLBIHZ7a+LdQ9oritQ3C8w+s7K+tGqLWf9F7JG2Pr5jRvzO" +
       "rrutV02SgvftY2SK\ng6jA+usJp+pLe7vmpGbTeg09+UT3cz9ZZhdE/M36OM" +
       "dhToonXmf15nAB2KJm/r9HS1Sn/A3Ovp9X\nPXPt8aMXvPwfJ/8DFZ3t9kAq" +
       "AAA=");
}
