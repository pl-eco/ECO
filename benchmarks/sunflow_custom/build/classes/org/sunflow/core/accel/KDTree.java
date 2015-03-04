package org.sunflow.core.accel;
import java.io.FileWriter;
import java.io.IOException;
import org.sunflow.core.AccelerationStructure;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Point3;
import org.sunflow.system.Memory;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
import org.sunflow.util.IntArray;
public class KDTree implements AccelerationStructure {
    private int[] tree;
    private int[] primitives;
    private PrimitiveList primitiveList;
    private BoundingBox bounds;
    private int maxPrims;
    private static final float INTERSECT_COST = 0.5F;
    private static final float TRAVERSAL_COST = 1;
    private static final float EMPTY_BONUS = 0.2F;
    private static final int MAX_DEPTH = 64;
    private static boolean dump = false;
    private static String dumpPrefix = "kdtree";
    public KDTree() { this(0); }
    public KDTree(int maxPrims) { super();
                                  this.maxPrims = maxPrims; }
    private static class BuildStats {
        private int numNodes;
        private int numLeaves;
        private int sumObjects;
        private int minObjects;
        private int maxObjects;
        private int sumDepth;
        private int minDepth;
        private int maxDepth;
        private int numLeaves0;
        private int numLeaves1;
        private int numLeaves2;
        private int numLeaves3;
        private int numLeaves4;
        private int numLeaves4p;
        BuildStats() { super();
                       numNodes = (numLeaves = 0);
                       sumObjects = 0;
                       minObjects = Integer.MAX_VALUE;
                       maxObjects = Integer.MIN_VALUE;
                       sumDepth = 0;
                       minDepth = Integer.MAX_VALUE;
                       maxDepth = Integer.MIN_VALUE;
                       numLeaves0 = 0;
                       numLeaves1 = 0;
                       numLeaves2 = 0;
                       numLeaves3 = 0;
                       numLeaves4 = 0;
                       numLeaves4p = 0; }
        void updateInner() { numNodes++; }
        void updateLeaf(int depth, int n) { numLeaves++;
                                            minDepth = Math.min(depth, minDepth);
                                            maxDepth = Math.max(depth, maxDepth);
                                            sumDepth += depth;
                                            minObjects = Math.min(n, minObjects);
                                            maxObjects = Math.max(n, maxObjects);
                                            sumObjects += n;
                                            switch (n) { case 0: numLeaves0++;
                                                                 break; case 1:
                                                             numLeaves1++;
                                                             break;
                                                         case 2:
                                                             numLeaves2++;
                                                             break;
                                                         case 3:
                                                             numLeaves3++;
                                                             break;
                                                         case 4:
                                                             numLeaves4++;
                                                             break;
                                                         default:
                                                             numLeaves4p++;
                                                             break; } }
        void printStats() { UI.printDetailed(Module.ACCEL, "KDTree stats:");
                            UI.printDetailed(Module.ACCEL, "  * Nodes:          %d",
                                             numNodes);
                            UI.printDetailed(Module.ACCEL, "  * Leaves:         %d",
                                             numLeaves);
                            UI.printDetailed(Module.ACCEL,
                                             "  * Objects: min    %d",
                                             minObjects);
                            UI.printDetailed(Module.ACCEL,
                                             "             avg    %.2f",
                                             (float)
                                               sumObjects /
                                               numLeaves);
                            UI.printDetailed(Module.ACCEL,
                                             "           avg(n>0) %.2f",
                                             (float)
                                               sumObjects /
                                               (numLeaves -
                                                  numLeaves0));
                            UI.printDetailed(Module.ACCEL,
                                             "             max    %d",
                                             maxObjects);
                            UI.printDetailed(Module.ACCEL,
                                             "  * Depth:   min    %d",
                                             minDepth);
                            UI.printDetailed(Module.ACCEL,
                                             "             avg    %.2f",
                                             (float)
                                               sumDepth /
                                               numLeaves);
                            UI.printDetailed(Module.ACCEL,
                                             "             max    %d",
                                             maxDepth);
                            UI.printDetailed(Module.ACCEL,
                                             "  * Leaves w/: N=0  %3d%%",
                                             100 *
                                               numLeaves0 /
                                               numLeaves);
                            UI.printDetailed(Module.ACCEL,
                                             "               N=1  %3d%%",
                                             100 *
                                               numLeaves1 /
                                               numLeaves);
                            UI.printDetailed(Module.ACCEL,
                                             "               N=2  %3d%%",
                                             100 *
                                               numLeaves2 /
                                               numLeaves);
                            UI.printDetailed(Module.ACCEL,
                                             "               N=3  %3d%%",
                                             100 *
                                               numLeaves3 /
                                               numLeaves);
                            UI.printDetailed(Module.ACCEL,
                                             "               N=4  %3d%%",
                                             100 *
                                               numLeaves4 /
                                               numLeaves);
                            UI.printDetailed(Module.ACCEL,
                                             "               N>4  %3d%%",
                                             100 *
                                               numLeaves4p /
                                               numLeaves);
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YXWxcxRUer//WJtgbhwTjJrbjOAjHyV47fyQ4SutsHOJ4" +
                                                        "iU1sUnURbMb3zto3vn/cO2tvnJqGSG1SKkVtMTRBYFVtEAUCQYiIVhVSXlpA" +
                                                        "9IWqatWHQtWXotI85KEUlbb0zM/u3b3r62z70JVmdnbmnG/OmfMzZ/bKDVTr" +
                                                        "uajXsY1TU4ZN4yRH4yeNXXF6yiFe/Ehy1xh2PaIlDOx5EzCXVrteb/708+9O" +
                                                        "xyKoLoXWYMuyKaa6bXnHiGcbs0RLomZ/dsggpkdRLHkSz2IlS3VDSeoeHUii" +
                                                        "24pYKepO5kVQQAQFRFC4CMqgTwVMtxMrayYYB7ao9xh6HFUlUZ2jMvEo2lgK" +
                                                        "4mAXmxJmjGsACFH2+zgoxZlzLuos6C50LlP46V5l8QePxt6oRs0p1Kxb40wc" +
                                                        "FYSgsEkKrTKJOUlcb1DTiJZCqy1CtHHi6tjQ57ncKdTi6VMWplmXFA6JTWYd" +
                                                        "4vI9/ZNbpTLd3KxKbbegXkYnhpb/VZsx8BTous7XVWh4iM2Dgo06COZmsEry" +
                                                        "LDUzuqVR1BHkKOjYPQIEwFpvEjptF7aqsTBMoBZhOwNbU8o4dXVrCkhr7Szs" +
                                                        "QlFbKCg7awerM3iKpClqDdKNiSWgauAHwVgoWhsk40hgpbaAlYrsc+Povgun" +
                                                        "rcNWhMusEdVg8keBqT3AdIxkiEsslQjGVVuSz+B1b5+PIATEawPEguatr9/8" +
                                                        "ytb26+8Kmi8tQzM6eZKoNK1enmz6YH2iZ281EyPq2J7OjF+iOXf/MbkykHMg" +
                                                        "8tYVENliPL94/dgvv3bmZfJJBDUOozrVNrIm+NFq1TYd3SDu/cQiLqZEG0YN" +
                                                        "xNISfH0Y1cM4qVtEzI5mMh6hw6jG4FN1Nv8NR5QBCHZE9TDWrYydHzuYTvNx" +
                                                        "zkEIrYGGWqH1IvHh3xRpyrRtEgWr2NItWwHfJdhVpxWi2mmXOLYylBhVJuGU" +
                                                        "p03szniKl7Uyhj2XVrMetU3Fc1XFdqfy04pquwxMJYYycnDCJSTOvM35P+2T" +
                                                        "Y/rG5qqqwBTrg4nAgBg6bBsacdPqYvbA0M3X0u9HCoEhT4qie2CbuNwmzraJ" +
                                                        "823iYpvuA1nd0FjO8FBVFd/oDrazsDdYawbiHjLiqp7xR46cON9VDY7mzNXA" +
                                                        "UTPSLtBWijOk2gk/OQzzFKiCh7b+6OFz8c9e/LLwUCU8ky/Lja5fnHvi+Df6" +
                                                        "IihSmpKZejDVyNjHWCItJMzuYCguh9t87uNPrz6zYPtBWZLjZa4o52Sx3hU0" +
                                                        "hGurRIPs6cNv6cTX0m8vdEdQDSQQSJoUg5NDPmoP7lES8wP5/Ml0qQWFM7Zr" +
                                                        "YoMt5ZNeI5127Tl/hntIEx+vBqNEWRC0QBuQUcG/2eoah/V3CI9iVg5owfPz" +
                                                        "oZ9dv3Tt2d69keJU3lx0OY4TKhLDat9JmBvB/B8ujj319I1zD3MPAYpNy23Q" +
                                                        "zfoEpAkwGRzrN9997PcffXj5N5GCV6EcsN7tg0PuMCB/MZN3P2SZtqZndDxp" +
                                                        "EOaT/2ze3H/trxdiwogGzOR9YOutAfz5uw6gM+8/+vd2DlOlsrvLV9gnE3qv" +
                                                        "8ZEHXRefYnLknvj1hkvv4OchtUI68/R5wjMUkgoxoeLcQj283xZY62Ndp1O2" +
                                                        "luMzrfxXE2zdEx47h9gVXBRz/xg1Js/+6TOuUVnULHPzBPhTypXn2hL7P+H8" +
                                                        "vvsy7o5ceSaCcsXn3f6y+bdIV90vIqg+hWKqrIWOYyPLnCQF97+XL5CgXipZ" +
                                                        "L73LxcU1UAjP9cHQKdo2GDh+BoQxo2bjxkCsxNgpb4C2VcbK1mCsVCE+2MNZ" +
                                                        "uni/mXX3iARIUb3j6rOYFVooCpXfUVsj3sqWGnN1E67QWXnHKwstH8089/Gr" +
                                                        "IjsGzRIgJucXn/wifmExUlQ1bSorXIp5ROXE9b5d6P0FfKqg/Zs1pi+bEDdn" +
                                                        "S0Je352F+9txWDhuXEksvsWhP19d+PlPFs4JNVpKi4YhOJlXf/uvX8Uv/vG9" +
                                                        "Ze6naigIeVISEbCj1D7t0LZJ+2wLsc9h1t1HUQNslCR4VqTGwXDQDmhxCRoP" +
                                                        "AR2RoI1e1hSuWAmqIlGVENSjeVRTtypH7ZOofSGoDxZQca4yVOb2/RK1PwR1" +
                                                        "QqJG4QQOEkcUX7fA3C4xt4dgfjWPCfpXirlDYu4IwUwVMHGuEkx2ojsl5s4Q" +
                                                        "zEfyJ1pwqb4KUHdJ1F0hqCfKUPsrQN0tUXeHoKplqNsrQL1Xot4bgpopQ91R" +
                                                        "AeoeibonBFUvQ915C9ROaHsl6t4QVEOi3uajOkWwueXzd4QN91FU5/EXc/F1" +
                                                        "y6uQDWFvOp7yLp9dXNJGX+iPyFt8CIDkU9vHqWYwJTXyA/wJ61+XT770ylv0" +
                                                        "g977RObcEn5xBBnfOfuXton90yf+i8q4I6BQEPKlB668d//d6vcjqLpw65a9" +
                                                        "ykuZBkrv2kaX0KxrTZTcuO2l1WkbtBFp0JGgQbnBWNe9QsH0+AprZ1h3Glwh" +
                                                        "62hwgw1bljTIftYdEEY+SFHNrK1r5TUXn5grFbgH2qgUeLRigSO+j3E3HOSk" +
                                                        "315B9O+w7lsQG0J0cGQeg2crk/IuaKaU0vyfjvV7K6w9xboLIBsUPBblz8UQ" +
                                                        "2aAUavTflHx5LUUbVnyEQpS0lv3NJf6aUV9bao7eufTQ7/hTqvD3SUMSRTNZ" +
                                                        "wyiu84rGdY5LMjqXvEFUfQ7/ehaK++UloaiWf3OBLwnq5ymKBanBc9hXMdkP" +
                                                        "wduKyKAqlKNioh9DoQNEbHjZyZ9KjD8mWLUbF9d2DpUkISeYkjaV5Af+B2I+" +
                                                        "lrPiL8S0enXpyNHTN3e/wBNDrWrg+XmGEk2ievGcLOSDjaFoeay6wz2fN73e" +
                                                        "sDmf5JpY1yLfkAHZOpZ/cw2ZDuWvpPmf3vnmvheXPuRvvf8APBImy9kVAAA=");
    }
    public static void setDumpMode(boolean dump,
                                   String prefix) {
        KDTree.
          dump =
          dump;
        KDTree.
          dumpPrefix =
          prefix;
    }
    public void build(PrimitiveList primitives) {
        UI.
          printDetailed(
            Module.
              ACCEL,
            "KDTree settings");
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Max Leaf Size:  %d",
            maxPrims);
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Max Depth:      %d",
            MAX_DEPTH);
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Traversal cost: %.2f",
            TRAVERSAL_COST);
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Intersect cost: %.2f",
            INTERSECT_COST);
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Empty bonus:    %.2f",
            EMPTY_BONUS);
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Dump leaves:    %s",
            dump
              ? "enabled"
              : "disabled");
        Timer total =
          new Timer(
          );
        total.
          start(
            );
        this.
          primitiveList =
          primitives;
        bounds =
          primitives.
            getWorldBounds(
              null);
        int nPrim =
          primitiveList.
          getNumPrimitives(
            );
        int nSplits =
          0;
        BuildTask task =
          new BuildTask(
          nPrim);
        Timer prepare =
          new Timer(
          );
        prepare.
          start(
            );
        for (int i =
               0;
             i <
               nPrim;
             i++) {
            for (int axis =
                   0;
                 axis <
                   3;
                 axis++) {
                float ls =
                  primitiveList.
                  getPrimitiveBound(
                    i,
                    2 *
                      axis +
                      0);
                float rs =
                  primitiveList.
                  getPrimitiveBound(
                    i,
                    2 *
                      axis +
                      1);
                if (ls ==
                      rs) {
                    task.
                      splits[nSplits] =
                      pack(
                        ls,
                        PLANAR,
                        axis,
                        i);
                    nSplits++;
                }
                else {
                    task.
                      splits[nSplits +
                               0] =
                      pack(
                        ls,
                        OPENED,
                        axis,
                        i);
                    task.
                      splits[nSplits +
                               1] =
                      pack(
                        rs,
                        CLOSED,
                        axis,
                        i);
                    nSplits +=
                      2;
                }
            }
        }
        task.
          n =
          nSplits;
        prepare.
          end(
            );
        Timer t =
          new Timer(
          );
        IntArray tempTree =
          new IntArray(
          );
        IntArray tempList =
          new IntArray(
          );
        tempTree.
          add(
            0);
        tempTree.
          add(
            1);
        t.
          start(
            );
        Timer sorting =
          new Timer(
          );
        sorting.
          start(
            );
        radix12(
          task.
            splits,
          task.
            n);
        sorting.
          end(
            );
        BuildStats stats =
          new BuildStats(
          );
        buildTree(
          bounds.
            getMinimum(
              ).
            x,
          bounds.
            getMaximum(
              ).
            x,
          bounds.
            getMinimum(
              ).
            y,
          bounds.
            getMaximum(
              ).
            y,
          bounds.
            getMinimum(
              ).
            z,
          bounds.
            getMaximum(
              ).
            z,
          task,
          1,
          tempTree,
          0,
          tempList,
          stats);
        t.
          end(
            );
        task =
          null;
        tree =
          tempTree.
            trim(
              );
        tempTree =
          null;
        this.
          primitives =
          tempList.
            trim(
              );
        tempList =
          null;
        total.
          end(
            );
        stats.
          printStats(
            );
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Node memory:    %s",
            Memory.
              sizeof(
                tree));
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Object memory:  %s",
            Memory.
              sizeof(
                this.
                  primitives));
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Prepare time:   %s",
            prepare);
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Sorting time:   %s",
            sorting);
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Tree creation:  %s",
            t);
        UI.
          printDetailed(
            Module.
              ACCEL,
            "  * Build time:     %s",
            total);
        if (dump) {
            try {
                UI.
                  printInfo(
                    Module.
                      ACCEL,
                    "Dumping mtls to %s.mtl ...",
                    dumpPrefix);
                FileWriter mtlFile =
                  new FileWriter(
                  dumpPrefix +
                  ".mtl");
                int maxN =
                  stats.
                    maxObjects;
                for (int n =
                       0;
                     n <=
                       maxN;
                     n++) {
                    float blend =
                      (float)
                        n /
                      (float)
                        maxN;
                    Color nc;
                    if (blend <
                          0.25)
                        nc =
                          Color.
                            blend(
                              Color.
                                BLUE,
                              Color.
                                GREEN,
                              blend /
                                0.25F);
                    else
                        if (blend <
                              0.5)
                            nc =
                              Color.
                                blend(
                                  Color.
                                    GREEN,
                                  Color.
                                    YELLOW,
                                  (blend -
                                     0.25F) /
                                    0.25F);
                        else
                            if (blend <
                                  0.75)
                                nc =
                                  Color.
                                    blend(
                                      Color.
                                        YELLOW,
                                      Color.
                                        RED,
                                      (blend -
                                         0.5F) /
                                        0.25F);
                            else
                                nc =
                                  Color.
                                    MAGENTA;
                    mtlFile.
                      write(
                        String.
                          format(
                            "newmtl mtl%d\n",
                            n));
                    float[] rgb =
                      nc.
                      getRGB(
                        );
                    mtlFile.
                      write(
                        "Ka 0.1 0.1 0.1\n");
                    mtlFile.
                      write(
                        String.
                          format(
                            "Kd %.12g %.12g %.12g\n",
                            rgb[0],
                            rgb[1],
                            rgb[2]));
                    mtlFile.
                      write(
                        "illum 1\n\n");
                }
                FileWriter objFile =
                  new FileWriter(
                  dumpPrefix +
                  ".obj");
                UI.
                  printInfo(
                    Module.
                      ACCEL,
                    "Dumping tree to %s.obj ...",
                    dumpPrefix);
                dumpObj(
                  0,
                  0,
                  maxN,
                  new BoundingBox(
                    bounds),
                  objFile,
                  mtlFile);
                objFile.
                  close(
                    );
                mtlFile.
                  close(
                    );
            }
            catch (IOException e) {
                e.
                  printStackTrace(
                    );
            }
        }
    }
    private int dumpObj(int offset, int vertOffset,
                        int maxN,
                        BoundingBox bounds,
                        FileWriter file,
                        FileWriter mtlFile)
          throws IOException { if (offset ==
                                     0) file.
                                          write(
                                            String.
                                              format(
                                                "mtllib %s.mtl\n",
                                                dumpPrefix));
                               int nextOffset =
                                 tree[offset];
                               if ((nextOffset &
                                      3 <<
                                      30) ==
                                     3 <<
                                     30) {
                                   int n =
                                     tree[offset +
                                            1];
                                   if (n >
                                         0) {
                                       Point3 min =
                                         bounds.
                                         getMinimum(
                                           );
                                       Point3 max =
                                         bounds.
                                         getMaximum(
                                           );
                                       file.
                                         write(
                                           String.
                                             format(
                                               "o node%d\n",
                                               offset));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "v %g %g %g\n",
                                               max.
                                                 x,
                                               max.
                                                 y,
                                               min.
                                                 z));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "v %g %g %g\n",
                                               max.
                                                 x,
                                               min.
                                                 y,
                                               min.
                                                 z));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "v %g %g %g\n",
                                               min.
                                                 x,
                                               min.
                                                 y,
                                               min.
                                                 z));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "v %g %g %g\n",
                                               min.
                                                 x,
                                               max.
                                                 y,
                                               min.
                                                 z));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "v %g %g %g\n",
                                               max.
                                                 x,
                                               max.
                                                 y,
                                               max.
                                                 z));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "v %g %g %g\n",
                                               max.
                                                 x,
                                               min.
                                                 y,
                                               max.
                                                 z));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "v %g %g %g\n",
                                               min.
                                                 x,
                                               min.
                                                 y,
                                               max.
                                                 z));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "v %g %g %g\n",
                                               min.
                                                 x,
                                               max.
                                                 y,
                                               max.
                                                 z));
                                       int v0 =
                                         vertOffset;
                                       file.
                                         write(
                                           String.
                                             format(
                                               "usemtl mtl%d\n",
                                               n));
                                       file.
                                         write(
                                           "s off\n");
                                       file.
                                         write(
                                           String.
                                             format(
                                               "f %d %d %d %d\n",
                                               v0 +
                                                 1,
                                               v0 +
                                                 2,
                                               v0 +
                                                 3,
                                               v0 +
                                                 4));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "f %d %d %d %d\n",
                                               v0 +
                                                 5,
                                               v0 +
                                                 8,
                                               v0 +
                                                 7,
                                               v0 +
                                                 6));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "f %d %d %d %d\n",
                                               v0 +
                                                 1,
                                               v0 +
                                                 5,
                                               v0 +
                                                 6,
                                               v0 +
                                                 2));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "f %d %d %d %d\n",
                                               v0 +
                                                 2,
                                               v0 +
                                                 6,
                                               v0 +
                                                 7,
                                               v0 +
                                                 3));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "f %d %d %d %d\n",
                                               v0 +
                                                 3,
                                               v0 +
                                                 7,
                                               v0 +
                                                 8,
                                               v0 +
                                                 4));
                                       file.
                                         write(
                                           String.
                                             format(
                                               "f %d %d %d %d\n",
                                               v0 +
                                                 5,
                                               v0 +
                                                 1,
                                               v0 +
                                                 4,
                                               v0 +
                                                 8));
                                       vertOffset +=
                                         8;
                                   }
                                   return vertOffset;
                               }
                               else {
                                   int axis =
                                     nextOffset &
                                     3 <<
                                     30;
                                   int v0;
                                   float split =
                                     Float.
                                     intBitsToFloat(
                                       tree[offset +
                                              1]);
                                   float min;
                                   float max;
                                   nextOffset &=
                                     ~(3 <<
                                         30);
                                   switch (axis) {
                                       case 0:
                                           max =
                                             bounds.
                                               getMaximum(
                                                 ).
                                               x;
                                           bounds.
                                             getMaximum(
                                               ).
                                             x =
                                             split;
                                           v0 =
                                             dumpObj(
                                               nextOffset,
                                               vertOffset,
                                               maxN,
                                               bounds,
                                               file,
                                               mtlFile);
                                           bounds.
                                             getMaximum(
                                               ).
                                             x =
                                             max;
                                           min =
                                             bounds.
                                               getMinimum(
                                                 ).
                                               x;
                                           bounds.
                                             getMinimum(
                                               ).
                                             x =
                                             split;
                                           v0 =
                                             dumpObj(
                                               nextOffset +
                                                 2,
                                               v0,
                                               maxN,
                                               bounds,
                                               file,
                                               mtlFile);
                                           bounds.
                                             getMinimum(
                                               ).
                                             x =
                                             min;
                                           break;
                                       case 1 <<
                                         30:
                                           max =
                                             bounds.
                                               getMaximum(
                                                 ).
                                               y;
                                           bounds.
                                             getMaximum(
                                               ).
                                             y =
                                             split;
                                           v0 =
                                             dumpObj(
                                               nextOffset,
                                               vertOffset,
                                               maxN,
                                               bounds,
                                               file,
                                               mtlFile);
                                           bounds.
                                             getMaximum(
                                               ).
                                             y =
                                             max;
                                           min =
                                             bounds.
                                               getMinimum(
                                                 ).
                                               y;
                                           bounds.
                                             getMinimum(
                                               ).
                                             y =
                                             split;
                                           v0 =
                                             dumpObj(
                                               nextOffset +
                                                 2,
                                               v0,
                                               maxN,
                                               bounds,
                                               file,
                                               mtlFile);
                                           bounds.
                                             getMinimum(
                                               ).
                                             y =
                                             min;
                                           break;
                                       case 2 <<
                                         30:
                                           max =
                                             bounds.
                                               getMaximum(
                                                 ).
                                               z;
                                           bounds.
                                             getMaximum(
                                               ).
                                             z =
                                             split;
                                           v0 =
                                             dumpObj(
                                               nextOffset,
                                               vertOffset,
                                               maxN,
                                               bounds,
                                               file,
                                               mtlFile);
                                           bounds.
                                             getMaximum(
                                               ).
                                             z =
                                             max;
                                           min =
                                             bounds.
                                               getMinimum(
                                                 ).
                                               z;
                                           bounds.
                                             getMinimum(
                                               ).
                                             z =
                                             split;
                                           v0 =
                                             dumpObj(
                                               nextOffset +
                                                 2,
                                               v0,
                                               maxN,
                                               bounds,
                                               file,
                                               mtlFile);
                                           bounds.
                                             getMinimum(
                                               ).
                                             z =
                                             min;
                                           break;
                                       default:
                                           v0 =
                                             vertOffset;
                                           break;
                                   }
                                   return v0;
                               } }
    private static final long CLOSED = 0L <<
      30;
    private static final long PLANAR = 1L <<
      30;
    private static final long OPENED = 2L <<
      30;
    private static final long TYPE_MASK =
      3L <<
      30;
    private static long pack(float split,
                             long type,
                             int axis,
                             int object) {
        int f =
          Float.
          floatToRawIntBits(
            split);
        int top =
          f ^
          (f >>
             31 |
             -2147483648);
        long p =
          ((long)
             top &
             4294967295L) <<
          32;
        p |=
          type;
        p |=
          (long)
            axis <<
            28;
        p |=
          object &
            268435455L;
        return p;
    }
    private static int unpackObject(long p) {
        return (int)
                 (p &
                    268435455L);
    }
    private static int unpackAxis(long p) {
        return (int)
                 (p >>>
                    28) &
          3;
    }
    private static long unpackSplitType(long p) {
        return p &
          TYPE_MASK;
    }
    private static float unpackSplit(long p) {
        int f =
          (int)
            (p >>>
               32 &
               4294967295L);
        int m =
          (f >>>
             31) -
          1 |
          -2147483648;
        return Float.
          intBitsToFloat(
            f ^
              m);
    }
    private static void radix12(long[] splits,
                                int n) { final int[] hist =
                                           new int[2048];
                                         final long[] sorted =
                                           new long[n];
                                         for (int i =
                                                0;
                                              i <
                                                n;
                                              i++) {
                                             long pi =
                                               splits[i];
                                             hist[0 +
                                                    ((int)
                                                       (pi >>>
                                                          28) &
                                                       511)]++;
                                             hist[512 +
                                                    ((int)
                                                       (pi >>>
                                                          37) &
                                                       511)]++;
                                             hist[1024 +
                                                    ((int)
                                                       (pi >>>
                                                          46) &
                                                       511)]++;
                                             hist[1536 +
                                                    (int)
                                                      (pi >>>
                                                         55)]++;
                                         }
                                         {
                                             int sum0 =
                                               0;
                                             int sum1 =
                                               0;
                                             int sum2 =
                                               0;
                                             int sum3 =
                                               0;
                                             int tsum;
                                             for (int i =
                                                    0;
                                                  i <
                                                    512;
                                                  i++) {
                                                 tsum =
                                                   hist[0 +
                                                          i] +
                                                     sum0;
                                                 hist[0 +
                                                        i] =
                                                   sum0 -
                                                     1;
                                                 sum0 =
                                                   tsum;
                                                 tsum =
                                                   hist[512 +
                                                          i] +
                                                     sum1;
                                                 hist[512 +
                                                        i] =
                                                   sum1 -
                                                     1;
                                                 sum1 =
                                                   tsum;
                                                 tsum =
                                                   hist[1024 +
                                                          i] +
                                                     sum2;
                                                 hist[1024 +
                                                        i] =
                                                   sum2 -
                                                     1;
                                                 sum2 =
                                                   tsum;
                                                 tsum =
                                                   hist[1536 +
                                                          i] +
                                                     sum3;
                                                 hist[1536 +
                                                        i] =
                                                   sum3 -
                                                     1;
                                                 sum3 =
                                                   tsum;
                                             }
                                         }
                                         for (int i =
                                                0;
                                              i <
                                                n;
                                              i++) {
                                             long pi =
                                               splits[i];
                                             int pos =
                                               (int)
                                                 (pi >>>
                                                    28) &
                                               511;
                                             sorted[++hist[0 +
                                                             pos]] =
                                               pi;
                                         }
                                         for (int i =
                                                0;
                                              i <
                                                n;
                                              i++) {
                                             long pi =
                                               sorted[i];
                                             int pos =
                                               (int)
                                                 (pi >>>
                                                    37) &
                                               511;
                                             splits[++hist[512 +
                                                             pos]] =
                                               pi;
                                         }
                                         for (int i =
                                                0;
                                              i <
                                                n;
                                              i++) {
                                             long pi =
                                               splits[i];
                                             int pos =
                                               (int)
                                                 (pi >>>
                                                    46) &
                                               511;
                                             sorted[++hist[1024 +
                                                             pos]] =
                                               pi;
                                         }
                                         for (int i =
                                                0;
                                              i <
                                                n;
                                              i++) {
                                             long pi =
                                               sorted[i];
                                             int pos =
                                               (int)
                                                 (pi >>>
                                                    55);
                                             splits[++hist[1536 +
                                                             pos]] =
                                               pi;
                                         }
    }
    private static class BuildTask {
        long[] splits;
        int numObjects;
        int n;
        byte[] leftRightTable;
        BuildTask(int numObjects) { super(
                                      );
                                    splits =
                                      (new long[6 *
                                                  numObjects]);
                                    this.
                                      numObjects =
                                      numObjects;
                                    n = 0;
                                    leftRightTable =
                                      (new byte[(numObjects +
                                                   3) /
                                                  4]);
        }
        BuildTask(int numObjects, BuildTask parent) {
            super(
              );
            splits =
              (new long[6 *
                          numObjects]);
            this.
              numObjects =
              numObjects;
            n =
              0;
            leftRightTable =
              parent.
                leftRightTable;
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "Tco7h5GP53y4+tTEdd7//AeZzXYA7BAAAA==");
    }
    private void buildTree(float minx, float maxx,
                           float miny,
                           float maxy,
                           float minz,
                           float maxz,
                           BuildTask task,
                           int depth,
                           IntArray tempTree,
                           int offset,
                           IntArray tempList,
                           BuildStats stats) {
        if (task.
              numObjects >
              maxPrims &&
              depth <
              MAX_DEPTH) {
            float dx =
              maxx -
              minx;
            float dy =
              maxy -
              miny;
            float dz =
              maxz -
              minz;
            float bestCost =
              INTERSECT_COST *
              task.
                numObjects;
            int bestAxis =
              -1;
            int bestOffsetStart =
              -1;
            int bestOffsetEnd =
              -1;
            float bestSplit =
              0;
            boolean bestPlanarLeft =
              false;
            int bnl =
              0;
            int bnr =
              0;
            float area =
              dx *
              dy +
              dy *
              dz +
              dz *
              dx;
            float ISECT_COST =
              INTERSECT_COST /
              area;
            int[] nl =
              { 0,
            0,
            0 };
            int[] nr =
              { task.
                  numObjects,
            task.
              numObjects,
            task.
              numObjects };
            float[] dp =
              { dy *
              dz,
            dz *
              dx,
            dx *
              dy };
            float[] ds =
              { dy +
              dz,
            dz +
              dx,
            dx +
              dy };
            float[] nodeMin =
              { minx,
            miny,
            minz };
            float[] nodeMax =
              { maxx,
            maxy,
            maxz };
            int nSplits =
              task.
                n;
            long[] splits =
              task.
                splits;
            byte[] lrtable =
              task.
                leftRightTable;
            for (int i =
                   0;
                 i <
                   nSplits;
                 ) {
                long ptr =
                  splits[i];
                float split =
                  unpackSplit(
                    ptr);
                int axis =
                  unpackAxis(
                    ptr);
                int currentOffset =
                  i;
                int pClosed =
                  0;
                int pPlanar =
                  0;
                int pOpened =
                  0;
                long ptrMasked =
                  ptr &
                  (~TYPE_MASK &
                     -268435456L);
                long ptrClosed =
                  ptrMasked |
                  CLOSED;
                long ptrPlanar =
                  ptrMasked |
                  PLANAR;
                long ptrOpened =
                  ptrMasked |
                  OPENED;
                while (i <
                         nSplits &&
                         (splits[i] &
                            -268435456L) ==
                         ptrClosed) {
                    int obj =
                      unpackObject(
                        splits[i]);
                    lrtable[obj >>>
                              2] =
                      0;
                    pClosed++;
                    i++;
                }
                while (i <
                         nSplits &&
                         (splits[i] &
                            -268435456L) ==
                         ptrPlanar) {
                    int obj =
                      unpackObject(
                        splits[i]);
                    lrtable[obj >>>
                              2] =
                      0;
                    pPlanar++;
                    i++;
                }
                while (i <
                         nSplits &&
                         (splits[i] &
                            -268435456L) ==
                         ptrOpened) {
                    int obj =
                      unpackObject(
                        splits[i]);
                    lrtable[obj >>>
                              2] =
                      0;
                    pOpened++;
                    i++;
                }
                nr[axis] -=
                  pPlanar +
                    pClosed;
                if (split >=
                      nodeMin[axis] &&
                      split <=
                      nodeMax[axis]) {
                    float dl =
                      split -
                      nodeMin[axis];
                    float dr =
                      nodeMax[axis] -
                      split;
                    float lp =
                      dp[axis] +
                      dl *
                      ds[axis];
                    float rp =
                      dp[axis] +
                      dr *
                      ds[axis];
                    boolean planarLeft =
                      dl <
                      dr;
                    int numLeft =
                      nl[axis] +
                      (planarLeft
                         ? pPlanar
                         : 0);
                    int numRight =
                      nr[axis] +
                      (planarLeft
                         ? 0
                         : pPlanar);
                    float eb =
                      numLeft ==
                      0 &&
                      dl >
                      0 ||
                      numRight ==
                      0 &&
                      dr >
                      0
                      ? EMPTY_BONUS
                      : 0;
                    float cost =
                      TRAVERSAL_COST +
                      ISECT_COST *
                      (1 -
                         eb) *
                      (lp *
                         numLeft +
                         rp *
                         numRight);
                    if (cost <
                          bestCost) {
                        bestCost =
                          cost;
                        bestAxis =
                          axis;
                        bestSplit =
                          split;
                        bestOffsetStart =
                          currentOffset;
                        bestOffsetEnd =
                          i;
                        bnl =
                          numLeft;
                        bnr =
                          numRight;
                        bestPlanarLeft =
                          planarLeft;
                    }
                }
                nl[axis] +=
                  pOpened +
                    pPlanar;
            }
            for (int axis =
                   0;
                 axis <
                   3;
                 axis++) {
                int numLeft =
                  nl[axis];
                int numRight =
                  nr[axis];
                if (numLeft !=
                      task.
                        numObjects ||
                      numRight !=
                      0)
                    UI.
                      printError(
                        Module.
                          ACCEL,
                        ("Didn\'t scan full range of objects @depth=%d. Left overs for" +
                         " axis %d: [L: %d] [R: %d]"),
                        depth,
                        axis,
                        numLeft,
                        numRight);
            }
            if (bestAxis !=
                  -1) {
                BuildTask taskL =
                  new BuildTask(
                  bnl,
                  task);
                BuildTask taskR =
                  new BuildTask(
                  bnr,
                  task);
                int lk =
                  0;
                int rk =
                  0;
                for (int i =
                       0;
                     i <
                       bestOffsetStart;
                     i++) {
                    long ptr =
                      splits[i];
                    if (unpackAxis(
                          ptr) ==
                          bestAxis) {
                        if (unpackSplitType(
                              ptr) !=
                              CLOSED) {
                            int obj =
                              unpackObject(
                                ptr);
                            lrtable[obj >>>
                                      2] |=
                              1 <<
                                ((obj &
                                    3) <<
                                   1);
                            lk++;
                        }
                    }
                }
                for (int i =
                       bestOffsetStart;
                     i <
                       bestOffsetEnd;
                     i++) {
                    long ptr =
                      splits[i];
                    assert unpackAxis(
                             ptr) ==
                      bestAxis;
                    if (unpackSplitType(
                          ptr) ==
                          PLANAR) {
                        if (bestPlanarLeft) {
                            int obj =
                              unpackObject(
                                ptr);
                            lrtable[obj >>>
                                      2] |=
                              1 <<
                                ((obj &
                                    3) <<
                                   1);
                            lk++;
                        }
                        else {
                            int obj =
                              unpackObject(
                                ptr);
                            lrtable[obj >>>
                                      2] |=
                              2 <<
                                ((obj &
                                    3) <<
                                   1);
                            rk++;
                        }
                    }
                }
                for (int i =
                       bestOffsetEnd;
                     i <
                       nSplits;
                     i++) {
                    long ptr =
                      splits[i];
                    if (unpackAxis(
                          ptr) ==
                          bestAxis) {
                        if (unpackSplitType(
                              ptr) !=
                              OPENED) {
                            int obj =
                              unpackObject(
                                ptr);
                            lrtable[obj >>>
                                      2] |=
                              2 <<
                                ((obj &
                                    3) <<
                                   1);
                            rk++;
                        }
                    }
                }
                long[] splitsL =
                  taskL.
                    splits;
                long[] splitsR =
                  taskR.
                    splits;
                int nsl =
                  0;
                int nsr =
                  0;
                for (int i =
                       0;
                     i <
                       nSplits;
                     i++) {
                    long ptr =
                      splits[i];
                    int obj =
                      unpackObject(
                        ptr);
                    int idx =
                      obj >>>
                      2;
                    int mask =
                      1 <<
                      ((obj &
                          3) <<
                         1);
                    if ((lrtable[idx] &
                           mask) !=
                          0) {
                        splitsL[nsl] =
                          ptr;
                        nsl++;
                    }
                    if ((lrtable[idx] &
                           mask <<
                           1) !=
                          0) {
                        splitsR[nsr] =
                          ptr;
                        nsr++;
                    }
                }
                taskL.
                  n =
                  nsl;
                taskR.
                  n =
                  nsr;
                task.
                  splits =
                  (splits =
                     (splitsL =
                        (splitsR =
                           null)));
                task =
                  null;
                int nextOffset =
                  tempTree.
                  getSize(
                    );
                tempTree.
                  add(
                    0);
                tempTree.
                  add(
                    0);
                tempTree.
                  add(
                    0);
                tempTree.
                  add(
                    0);
                tempTree.
                  set(
                    offset +
                      0,
                    bestAxis <<
                      30 |
                      nextOffset);
                tempTree.
                  set(
                    offset +
                      1,
                    Float.
                      floatToRawIntBits(
                        bestSplit));
                stats.
                  updateInner(
                    );
                switch (bestAxis) {
                    case 0:
                        buildTree(
                          minx,
                          bestSplit,
                          miny,
                          maxy,
                          minz,
                          maxz,
                          taskL,
                          depth +
                            1,
                          tempTree,
                          nextOffset,
                          tempList,
                          stats);
                        taskL =
                          null;
                        buildTree(
                          bestSplit,
                          maxx,
                          miny,
                          maxy,
                          minz,
                          maxz,
                          taskR,
                          depth +
                            1,
                          tempTree,
                          nextOffset +
                            2,
                          tempList,
                          stats);
                        taskR =
                          null;
                        return;
                    case 1:
                        buildTree(
                          minx,
                          maxx,
                          miny,
                          bestSplit,
                          minz,
                          maxz,
                          taskL,
                          depth +
                            1,
                          tempTree,
                          nextOffset,
                          tempList,
                          stats);
                        taskL =
                          null;
                        buildTree(
                          minx,
                          maxx,
                          bestSplit,
                          maxy,
                          minz,
                          maxz,
                          taskR,
                          depth +
                            1,
                          tempTree,
                          nextOffset +
                            2,
                          tempList,
                          stats);
                        taskR =
                          null;
                        return;
                    case 2:
                        buildTree(
                          minx,
                          maxx,
                          miny,
                          maxy,
                          minz,
                          bestSplit,
                          taskL,
                          depth +
                            1,
                          tempTree,
                          nextOffset,
                          tempList,
                          stats);
                        taskL =
                          null;
                        buildTree(
                          minx,
                          maxx,
                          miny,
                          maxy,
                          bestSplit,
                          maxz,
                          taskR,
                          depth +
                            1,
                          tempTree,
                          nextOffset +
                            2,
                          tempList,
                          stats);
                        taskR =
                          null;
                        return;
                    default:
                        assert false;
                }
            }
        }
        int listOffset =
          tempList.
          getSize(
            );
        int n =
          0;
        for (int i =
               0;
             i <
               task.
                 n;
             i++) {
            long ptr =
              task.
                splits[i];
            if (unpackAxis(
                  ptr) ==
                  0 &&
                  unpackSplitType(
                    ptr) !=
                  CLOSED) {
                tempList.
                  add(
                    unpackObject(
                      ptr));
                n++;
            }
        }
        stats.
          updateLeaf(
            depth,
            n);
        if (n !=
              task.
                numObjects)
            UI.
              printError(
                Module.
                  ACCEL,
                "Error creating leaf node - expecting %d found %d",
                task.
                  numObjects,
                n);
        tempTree.
          set(
            offset +
              0,
            3 <<
              30 |
              listOffset);
        tempTree.
          set(
            offset +
              1,
            task.
              numObjects);
        task.
          splits =
          null;
    }
    public void intersect(Ray r, IntersectionState state) {
        float intervalMin =
          r.
          getMin(
            );
        float intervalMax =
          r.
          getMax(
            );
        float orgX =
          r.
            ox;
        float dirX =
          r.
            dx;
        float invDirX =
          1 /
          dirX;
        float t1;
        float t2;
        t1 =
          (bounds.
             getMinimum(
               ).
             x -
             orgX) *
            invDirX;
        t2 =
          (bounds.
             getMaximum(
               ).
             x -
             orgX) *
            invDirX;
        if (invDirX >
              0) {
            if (t1 >
                  intervalMin)
                intervalMin =
                  t1;
            if (t2 <
                  intervalMax)
                intervalMax =
                  t2;
        }
        else {
            if (t2 >
                  intervalMin)
                intervalMin =
                  t2;
            if (t1 <
                  intervalMax)
                intervalMax =
                  t1;
        }
        if (intervalMin >
              intervalMax)
            return;
        float orgY =
          r.
            oy;
        float dirY =
          r.
            dy;
        float invDirY =
          1 /
          dirY;
        t1 =
          (bounds.
             getMinimum(
               ).
             y -
             orgY) *
            invDirY;
        t2 =
          (bounds.
             getMaximum(
               ).
             y -
             orgY) *
            invDirY;
        if (invDirY >
              0) {
            if (t1 >
                  intervalMin)
                intervalMin =
                  t1;
            if (t2 <
                  intervalMax)
                intervalMax =
                  t2;
        }
        else {
            if (t2 >
                  intervalMin)
                intervalMin =
                  t2;
            if (t1 <
                  intervalMax)
                intervalMax =
                  t1;
        }
        if (intervalMin >
              intervalMax)
            return;
        float orgZ =
          r.
            oz;
        float dirZ =
          r.
            dz;
        float invDirZ =
          1 /
          dirZ;
        t1 =
          (bounds.
             getMinimum(
               ).
             z -
             orgZ) *
            invDirZ;
        t2 =
          (bounds.
             getMaximum(
               ).
             z -
             orgZ) *
            invDirZ;
        if (invDirZ >
              0) {
            if (t1 >
                  intervalMin)
                intervalMin =
                  t1;
            if (t2 <
                  intervalMax)
                intervalMax =
                  t2;
        }
        else {
            if (t2 >
                  intervalMin)
                intervalMin =
                  t2;
            if (t1 <
                  intervalMax)
                intervalMax =
                  t1;
        }
        if (intervalMin >
              intervalMax)
            return;
        int offsetXFront =
          (Float.
             floatToRawIntBits(
               dirX) &
             1 <<
             31) >>>
          30;
        int offsetYFront =
          (Float.
             floatToRawIntBits(
               dirY) &
             1 <<
             31) >>>
          30;
        int offsetZFront =
          (Float.
             floatToRawIntBits(
               dirZ) &
             1 <<
             31) >>>
          30;
        int offsetXBack =
          offsetXFront ^
          2;
        int offsetYBack =
          offsetYFront ^
          2;
        int offsetZBack =
          offsetZFront ^
          2;
        IntersectionState.StackNode[] stack =
          state.
          getStack(
            );
        int stackTop =
          state.
          getStackTop(
            );
        int stackPos =
          stackTop;
        int node =
          0;
        while (true) {
            int tn =
              tree[node];
            int axis =
              tn &
              3 <<
              30;
            int offset =
              tn &
              ~(3 <<
                  30);
            switch (axis) {
                case 0:
                    {
                        float d =
                          (Float.
                             intBitsToFloat(
                               tree[node +
                                      1]) -
                             orgX) *
                          invDirX;
                        int back =
                          offset +
                          offsetXBack;
                        node =
                          back;
                        if (d <
                              intervalMin)
                            continue;
                        node =
                          offset +
                            offsetXFront;
                        if (d >
                              intervalMax)
                            continue;
                        stack[stackPos].
                          node =
                          back;
                        stack[stackPos].
                          near =
                          d >=
                            intervalMin
                            ? d
                            : intervalMin;
                        stack[stackPos].
                          far =
                          intervalMax;
                        stackPos++;
                        intervalMax =
                          d <=
                            intervalMax
                            ? d
                            : intervalMax;
                        continue;
                    }
                case 1 <<
                  30:
                    {
                        float d =
                          (Float.
                             intBitsToFloat(
                               tree[node +
                                      1]) -
                             orgY) *
                          invDirY;
                        int back =
                          offset +
                          offsetYBack;
                        node =
                          back;
                        if (d <
                              intervalMin)
                            continue;
                        node =
                          offset +
                            offsetYFront;
                        if (d >
                              intervalMax)
                            continue;
                        stack[stackPos].
                          node =
                          back;
                        stack[stackPos].
                          near =
                          d >=
                            intervalMin
                            ? d
                            : intervalMin;
                        stack[stackPos].
                          far =
                          intervalMax;
                        stackPos++;
                        intervalMax =
                          d <=
                            intervalMax
                            ? d
                            : intervalMax;
                        continue;
                    }
                case 2 <<
                  30:
                    {
                        float d =
                          (Float.
                             intBitsToFloat(
                               tree[node +
                                      1]) -
                             orgZ) *
                          invDirZ;
                        int back =
                          offset +
                          offsetZBack;
                        node =
                          back;
                        if (d <
                              intervalMin)
                            continue;
                        node =
                          offset +
                            offsetZFront;
                        if (d >
                              intervalMax)
                            continue;
                        stack[stackPos].
                          node =
                          back;
                        stack[stackPos].
                          near =
                          d >=
                            intervalMin
                            ? d
                            : intervalMin;
                        stack[stackPos].
                          far =
                          intervalMax;
                        stackPos++;
                        intervalMax =
                          d <=
                            intervalMax
                            ? d
                            : intervalMax;
                        continue;
                    }
                default:
                    {
                        int n =
                          tree[node +
                                 1];
                        while (n >
                                 0) {
                            primitiveList.
                              intersectPrimitive(
                                r,
                                primitives[offset],
                                state);
                            n--;
                            offset++;
                        }
                        if (r.
                              getMax(
                                ) <
                              intervalMax)
                            return;
                        do  {
                            if (stackPos ==
                                  stackTop)
                                return;
                            stackPos--;
                            intervalMin =
                              stack[stackPos].
                                near;
                            if (r.
                                  getMax(
                                    ) <
                                  intervalMin)
                                continue;
                            node =
                              stack[stackPos].
                                node;
                            intervalMax =
                              stack[stackPos].
                                far;
                            break;
                        }while(true); 
                    }
            }
        }
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "AAA=");
}
