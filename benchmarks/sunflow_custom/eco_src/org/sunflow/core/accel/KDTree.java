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
    final private static float INTERSECT_COST = 0.5F;
    final private static float TRAVERSAL_COST = 1;
    final private static float EMPTY_BONUS = 0.2F;
    final private static int MAX_DEPTH = 64;
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
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1163561096000L;
        final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVYe2wUxxkf3/ltt34A5hHA2DgPsLm1wRDAUXkYE4wX7PqV" +
                                                       "YJI44905e2Fvd7M7\nZx8OTUMrYRraJqitlErh0RaJR8lDIi2plKagkCYFVU" +
                                                       "oqNZUihbZCaiu1qVRVSqnaP/rNzN7t3d7t\nJVxPmtm5mW++33zzPeabufQJ" +
                                                       "KnFstFRxIvSQRZxI99AAth2iduvYcYaha1x5p6Ri4FyfYYZQkYxC\nmkpRja" +
                                                       "w4koopljRV6t3RlbBRq2XqhyZ1k0ZIgkYO6Otdfrvl9VkMHzl1pf7I2eLGEC" +
                                                       "qRUQ02DJNi\nqplGj05iDkW18gE8jaU41XRJ1hzaJaMvECMe6zYNh2KDOk+h" +
                                                       "Z1BYRqWWwnhS1CQnwSUAlyxs45jE\n4aUBDgsc5tmEYs0g6rYUHMxsy5wJy3" +
                                                       "bnDWZTA5NyNjgK4vAVgNQrUlILabNEtcLnRzccPnMhjGrG\nUI1mDDFmCkhC" +
                                                       "AW8MVcdIbILYzjZVJeoYqjMIUYeIrWFdm+WoY6je0SYNTOM2cQaJY+rTjLDe" +
                                                       "iVvE\n5pjJThlVK0wmO65Q007tUVQjupr8VxLV8SSI3eCJLcTdyfpBwEoNFm" +
                                                       "ZHsUKSU4oPagZovNE/IyVj\nSx8QwNSyGKFTZgqq2MDQgeqFLnVsTEpD1NaM" +
                                                       "SSAtMeOAQtGSQKZsry2sHMSTZJyiRX66ATEEVBV8\nI9gUihb4yTgn0NISn5" +
                                                       "bS9NPa8Omx8y+9tZXbdrFKFJ2tvxImLfdNGiRRYhNDIWLinXjku7374ktD\n" +
                                                       "CAHxAh+xoNl275UR+S+/aBQ09+Sg6Z84QBQ6ruw90Tj49MMmCrNllFumozHl" +
                                                       "Z0jO3WHAHelKWOC1\nDSmObDCSHLw6+Mt9z14kfw2hyl5Uqph6PAZ2VKeYMU" +
                                                       "vTif0wMYiNKVF7UQUx1G4+3ovKoC2DyYve\n/mjUIbQXFeu8q9Tk/2GLosCC" +
                                                       "bVEFtDUjaibbFqZTvJ2wEELzoKBFUFqR+PEvRe0RyYkbUd2ckRxb\nkUx7Mv" +
                                                       "VfMW0iYUUhutS3Y9gmJMIsx6JIlqbMGBvChmaY0qQGvqqYa1Qyzb53yS/B1l" +
                                                       "g/U1TEgp7f\neXWw+12mrhJ7XDl3+8bhnr5vHBOGwYzZlY6iBwAm4sJEGEyE" +
                                                       "w0QETMv2uKarzM8dVFTEgeYzZKEj\n2OGD4KsQ1apXDT2++8ljzWEwDmumGL" +
                                                       "aHkTaDSO5yehSz23PoXh77FLCqRT/cPxe5c26LsCopOO7m\nnF31/ss3z/yz" +
                                                       "enUIhXIHRSYmhOVKxmaARdJUsGvxu1Eu/n9/bs/lD29+/IDnUBS1ZPl59kzm" +
                                                       "p81+\nhdimQlSIfB77s4trwo+g0RMhVAzODwGPrx9iyXI/Roa/diVjH5OlTE" +
                                                       "ZVUdOOYZ0NJQNWJZ2yzRmv\nh1tKLW8zay5nBlwPpcu1aP5lowssVjcIy2La" +
                                                       "9knBY+udr5e2/+7Nqnf4tiTDcE3aQTdEqHDqOs9Y\nmDlB/8cvDnzne5/M7e" +
                                                       "eWIkwFJYDyPo8SnFiHQML01zJixExVi2p4QifM0P5bc2/HT/727VqhER16\n" +
                                                       "kgpt+2wGXv/i7ejZm0/8azlnU6SwQ8RbvUcmhJjncd5m2/gQW0fiyG+Wff9d" +
                                                       "fBJiHMQVR5slPFQg\nVyC2qAjf7lW8XuMba2dVM/BuC7D4HEf2uHL44mRz/K" +
                                                       "lf/Yyvugqnn/3pu78HW11C4axayXZ3od9p\nd2FnCug6r+59rFa/+h/gOAYc" +
                                                       "wfMdp9+GmJHI0J1LXVL20bW3G578IIxCO1GlbmJ1J+ZmjyrA3ogz\nBeEmYW" +
                                                       "3Zyk2qdoZZWS0XGfFNWOJuQCLt3xdhcauCvX4nO/A9hxmfaDsv3+g/yTcg0N" +
                                                       "9znHc+PrNv\njZy682t6i/PxHI/Nbkpkx1JIkry5Gz+crit97XQshMrGUK3i" +
                                                       "pnGjWI8z8x6DrMNJ5naQ6mWMZ2YQ\n4rjsSgWWpX6nT4P1u7wXw6HNqFm72u" +
                                                       "flfO+XQWlzvbzN7+VFiDe+xKe08Pp+N3xTVGbZ2jRmqR0q\nh4R1r6mCcdho" +
                                                       "UXp2bWsxOKWneVi6fbT55++NnJ4ToTyPUjNmjStf/f0fDoafvzYh5vk15yM+" +
                                                       "sfzs\nny7fHpwv/F+kcyuzMqr0OSKl41tTYzFfaMqHwKmvtzZdembwlrui+s" +
                                                       "zEpAf24s+H3ib3P/StP+Y4\nT8OQdIrgyeoOVm0RBt8Z6BibMlW2HMoaV2Vr" +
                                                       "AlTWz6qtFFXAamSCp0Wc3+VDHrhL5EYoERc5EoA8\n7CJXOvGYMOGc0CMFQE" +
                                                       "sutBQAvS8JHdOMPNBjBUC3u9DtAdBPpKBxIg/0+F1CM+/scKE7AqAVF7oc\n" +
                                                       "NnwHsURm6gdWCwBe6wKvDQCeSgLDdgcCawUAr3OB1wUAx1LAOBEIbBSg5U4X" +
                                                       "uDMA2E5qOeVV7bmg\nnQKg17vQ6wOgZ7KgO3JBJwqA3uBCbwiAPpwFvTYX9F" +
                                                       "cKgH7QhX4wAPpIFvS6XNBfKwB6owu9MQD6\naBZ0Zy7oubuEXgFlkwu9KQD6" +
                                                       "uAtd5UFbubC/mQc7kX1mh1h7O0WlDn+XSU+zeIq9LOjlgB96c4/+\no/oovv" +
                                                       "54yE1RdwMj90HH4xNmbDJudXv4Q4mXHj134cdX6Aetm8XZuTo4C/BPXL35zG" +
                                                       "zj5lePF3CX\na/QJ5mddN33Pl8NT2nsh/pYjsq2sN6DMSV2ZOVYlrCduG8MZ" +
                                                       "mdaKzPvUEih9rub7/JrnmmXVfXlu\nBafzjP2AVS+BzcQtFZKyXsNwFbPDEo" +
                                                       "rupah42tRUz3xOfpbppmyD/XkxU5pVUPpdafo/tzQhzxC5\nMe/ipJfyyPUK" +
                                                       "qy6AGwq5wB2irOdHnhgX/x8xFkOJuWLEClLKT/OMvcGqy7B4SJUNyp9JfIt/" +
                                                       "/fMu\nHrLsSu+xhQ8tpGhZ3tcZlo1nvdmKd0ZF/ujpxz6Vf/tv/raQeguskl" +
                                                       "F5NK7r6deHtHapZZOoxkWr\nEpcJi3+uwQU590ooKuFfvuCrgvo6RbV+ajBO" +
                                                       "9kknexeMOY0MLhxuK53oBmTUQMSaN63krtTymym7\nREVELpYZ6tidfWVG4O" +
                                                       "GP6MngEBfP6OPKoy/vX5E4PvwCjzglio5nZxmbShmViReVVIBpCuSW5PU+\n" +
                                                       "eu3V0Tdf2ZSMnvzqPT/hBfQMI+0Qo3lMA4Ja7veMnphF+QvE7BsLX3/o3Klb" +
                                                       "If6Q8j/XITCN+xgA\nAA==");
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
          start();
        this.
          primitiveList =
          primitives;
        bounds =
          primitives.
            getWorldBounds(
              null);
        int nPrim =
          primitiveList.
          getNumPrimitives();
        int nSplits =
          0;
        BuildTask task =
          new BuildTask(
          nPrim);
        Timer prepare =
          new Timer(
          );
        prepare.
          start();
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
                      KDTree.
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
                      KDTree.
                        pack(
                          ls,
                          OPENED,
                          axis,
                          i);
                    task.
                      splits[nSplits +
                               1] =
                      KDTree.
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
          end();
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
          start();
        Timer sorting =
          new Timer(
          );
        sorting.
          start();
        KDTree.
          radix12(
            task.
              splits,
            task.
              n);
        sorting.
          end();
        BuildStats stats =
          new BuildStats(
          );
        this.
          buildTree(
            bounds.
              getMinimum().
              x,
            bounds.
              getMaximum().
              x,
            bounds.
              getMinimum().
              y,
            bounds.
              getMaximum().
              y,
            bounds.
              getMinimum().
              z,
            bounds.
              getMaximum().
              z,
            task,
            1,
            tempTree,
            0,
            tempList,
            stats);
        t.
          end();
        task =
          null;
        tree =
          tempTree.
            trim();
        tempTree =
          null;
        this.
          primitives =
          tempList.
            trim();
        tempList =
          null;
        total.
          end();
        stats.
          printStats();
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
                      getRGB();
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
                this.
                  dumpObj(
                    0,
                    0,
                    maxN,
                    new BoundingBox(
                      bounds),
                    objFile,
                    mtlFile);
                objFile.
                  close();
                mtlFile.
                  close();
            }
            catch (IOException e) {
                e.
                  printStackTrace();
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
                                         getMinimum();
                                       Point3 max =
                                         bounds.
                                         getMaximum();
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
                                               getMaximum().
                                               x;
                                           bounds.
                                             getMaximum().
                                             x =
                                             split;
                                           v0 =
                                             this.
                                               dumpObj(
                                                 nextOffset,
                                                 vertOffset,
                                                 maxN,
                                                 bounds,
                                                 file,
                                                 mtlFile);
                                           bounds.
                                             getMaximum().
                                             x =
                                             max;
                                           min =
                                             bounds.
                                               getMinimum().
                                               x;
                                           bounds.
                                             getMinimum().
                                             x =
                                             split;
                                           v0 =
                                             this.
                                               dumpObj(
                                                 nextOffset +
                                                   2,
                                                 v0,
                                                 maxN,
                                                 bounds,
                                                 file,
                                                 mtlFile);
                                           bounds.
                                             getMinimum().
                                             x =
                                             min;
                                           break;
                                       case 1 <<
                                         30:
                                           max =
                                             bounds.
                                               getMaximum().
                                               y;
                                           bounds.
                                             getMaximum().
                                             y =
                                             split;
                                           v0 =
                                             this.
                                               dumpObj(
                                                 nextOffset,
                                                 vertOffset,
                                                 maxN,
                                                 bounds,
                                                 file,
                                                 mtlFile);
                                           bounds.
                                             getMaximum().
                                             y =
                                             max;
                                           min =
                                             bounds.
                                               getMinimum().
                                               y;
                                           bounds.
                                             getMinimum().
                                             y =
                                             split;
                                           v0 =
                                             this.
                                               dumpObj(
                                                 nextOffset +
                                                   2,
                                                 v0,
                                                 maxN,
                                                 bounds,
                                                 file,
                                                 mtlFile);
                                           bounds.
                                             getMinimum().
                                             y =
                                             min;
                                           break;
                                       case 2 <<
                                         30:
                                           max =
                                             bounds.
                                               getMaximum().
                                               z;
                                           bounds.
                                             getMaximum().
                                             z =
                                             split;
                                           v0 =
                                             this.
                                               dumpObj(
                                                 nextOffset,
                                                 vertOffset,
                                                 maxN,
                                                 bounds,
                                                 file,
                                                 mtlFile);
                                           bounds.
                                             getMaximum().
                                             z =
                                             max;
                                           min =
                                             bounds.
                                               getMinimum().
                                               z;
                                           bounds.
                                             getMinimum().
                                             z =
                                             split;
                                           v0 =
                                             this.
                                               dumpObj(
                                                 nextOffset +
                                                   2,
                                                 v0,
                                                 maxN,
                                                 bounds,
                                                 file,
                                                 mtlFile);
                                           bounds.
                                             getMinimum().
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
    final private static long CLOSED = 0L <<
      30;
    final private static long PLANAR = 1L <<
      30;
    final private static long OPENED = 2L <<
      30;
    final private static long TYPE_MASK =
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
        BuildTask(int numObjects) { super();
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
            super();
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
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1163561096000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAK1Ye2wUxxmfO78fwWcbzKPGxsbEpZg7k9gU41SJ8SMYDts9" +
           "v4IJNePdufPivd3N\n7px9digligS0UZugpOGhQEhlCWIlLVJa0UopBeXRNr" +
           "QSjdREQgpNhJRUalM1qpRStX/0m5m9u729\nsxFSLHlvduab7zW/7zH72uco" +
           "zzJRtWT56axBLH/n4AA2LSJ3qtiyhmBqXHonr2jgwm5N9yJPEHkV\nmaKyoG" +
           "QFZExxQJEDvV3tcRNtMnR1NqLq1E/i1H9QbbX57Qq2ZjAcPXe54qn53Fovyg" +
           "uiMqxpOsVU\n0bVulUQtinzBg3gaB2JUUQNBxaLtQXQf0WLRTl2zKNao9QQ6" +
           "jHKCKN+QGE+K6oIJ4QEQHjCwiaMB\nLj4wwMUCh0qTUKxoRO5IioOdTek7QW" +
           "17XyiTGpgUssURMIdrAFavS1otrM0w1ci5OLL10PlXc1DZ\nGCpTtEHGTAJL" +
           "KMgbQ6VREp0gptUhy0QeQ+UaIfIgMRWsKnNc6hiqsJSIhmnMJFaIWLo6zQgr" +
           "rJhB\nTC4zMRlEpRKzyYxJVDeTPgorRJUTb3lhFUfA7KqU2cLcHjYPBhYroJ" +
           "gZxhJJbMmdUjQ48Vr3jqSN\nDbuBALYWRAmd1JOicjUME6hCnKWKtUhgkJqK" +
           "FgHSPD0GUihasyhT5msDS1M4QsYpWuWmGxBLQFXE\nHcG2ULTCTcY5wSmtcZ" +
           "2S43w2VX15/OJLVx7h2M6ViaQy/YthU41rU4iEiUk0iYiNd2L+F3r3xqq9\n" +
           "CAHxChexoOnYcHk4+Nff1Aqar2Wh6Z84SCQ6LvWdqA09+aiOcpgahYZuKezw" +
           "0yzn4TBgr7THDYja\nqiRHtuhPLF4Nvbv3yAL5mxcV96J8SVdjUcBRuaRHDU" +
           "Ul5qNEIyamRO5FRUSTO/l6LyqAcRAgL2b7\nw2GL0F6Uq/KpfJ2/g4vCwIK5" +
           "qAjGihbWE2MD00k+jhsIoUr4R6sQ8ryI+J/4pajZH7BiWljVZwKW\nKQV0M5" +
           "J8l3STBLAkETWwu2vIJMTPkGNQFAxM6lG2hDVF0wMRBWJV0jfLZJr93iO/ON" +
           "OxYsbjYUnP\nHbwq4H6nrsrEHJcu3H7vUPfu7x8XwGBgtq2jqBHE+G0xfibG" +
           "z8X4hZiGHTFFlYewNYU8Hi5nORMs\njggcPAWhCkmtdOPg/l0HjtfnADaMmV" +
           "zwjhdI68EiW5tuSe9MxXMvT30SgGrVT/Yd89+58LAAVWDx\ntJt1d8mN16+f" +
           "/1fpN7zImz0nMishKxczNgMskSZzXYM7irLx/8cP9rzxwfWPvp6KJ4oaMsI8" +
           "cycL\n03r3eZi6RGRIfCn286vLckbRyAkvyoXYh3zH9YdUUuOWkRau7YnUx2" +
           "wpCKKSsG5GscqWEvmqmE6a\n+kxqhgPFx8cMzIUMv+UA5JdsQPNftrrCYM8q" +
           "ASx22i4reGq983R+84dvlrzD3ZLIwmWOOjdIqIjp\n8hRYGJpg/qNTA8//+P" +
           "Nj+zhSBFRQHCjvT1FCDKuQR9j5NQxrUV1WwgqeUAkD2v/KNmz5xd9/5BMn\n" +
           "osJM4kCb7s4gNb96Bzpy/Tv/ruFsPBKrISntU2TCiMoU5w7TxLNMj/hT7689" +
           "/Vt8FlIcpBVLmSM8\nU3i4QSxMVjl7EFOJQi6b5qd3+2j9r383/PIxgfiNSz" +
           "Qazl3j0vf+8vFUzrPXJsQ+dz53EZ+omf/0\njduh5cJNouitz6g7zj2i8PFz" +
           "LzPYgdQtJYFTv72p7rXDoVu2RhXp6bsbWpzPZt8ijQ/98JMsWScH\nSjMX5u" +
           "eg3MifmxkYbEiw9zb2qAddmhbxUpa+Zlw6tBCpjz3x+19xqSXY2SA5MboHG8" +
           "JcH3usZyav\ndKe2ndiaBLqWq32P+9Sr/wWOY8AR0qNl9ZuQWONpCLep8wpu" +
           "Xnur6sCfcpC3BxWrOpZ7ME8OqAii\nkliTkJPjxsOP8MjzzbBY9HGTEXfCmk" +
           "RMsJfqzJCdt0N2PmvIssf9Lpd6RUJmrw9a32V70WoHOvt0\njRdRRWInG0+4" +
           "gTU5fpOEWSiw5BWf/aTx5ro/+Dqvi6CZpGiDox2yKQO92rQucX/vxJoMBVrE" +
           "UHVW\ngaMmNqDhufHxp/uf3fTZuwxIBrejawlUBNmjgy9tZ48dwlXfuqtH44" +
           "63XGvJ2OthfWYqUY9PNF0M\nvtd/llu+aJ3JEpYuPnNXhs/d+SO9xfmkEj7b" +
           "XRfPLOHQm6f2bvtgujz/0stRLyoYQz7Jvj2MYDXG\n0uoYNLtW4koBN4y09f" +
           "TGVXRp7cmCVu3OCw6x7lKTCmIYM2o2Ls1WXaoAZidtqJ50Q9WD+GCEo5Wi\n" +
           "fMtQFWqxcuOACc+1DJKvPtdVGWrb9zSv8kVwmcBWX0oNuMKxkQf8t2HxA00y" +
           "G5ca91/+57UrpJFH\nc6FigbkdZiRLY+3Y8wVeIHs+DJ/jlTp3AlvCcPeNJP" +
           "PCkXaP4H5aZggcBuzfFgp1TNcihmFAS1nA\nzWttAV9UgC/YrdSvyDaOut7v" +
           "mVgIawuylwvjojsYve2KIj7j8E2Oblis7Xbcb21ODf2GxbqU+xxC\nersOXd" +
           "pVWvjKM0c5f9uxRY4W3n4vmMZmnzObl3Ctt7Q1NzdvoWj4q2xxt29taW7atn" +
           "lLG0ih0Ib6\nBVaY6ANJ5ByG20Omt5iNyP6r4DVqWSqzsfrkXAQrCkZDvUND" +
           "3X2i90nml9G75ZdvpyN/JSD+lI38\nU4sgX7eRXwxaiHjkPB50iTbuUfQyEH" +
           "naFn16EdHUFu3RskmM3aPEGpB0xpZ4ZhGJs7bEZSoJ05AS\nmaRDrBvjjPZm" +
           "iYaJWUrSoqGVL+Ekzra1ffMBIBz5SnHW0rq16YG2zS3bKKrkQEvXlmlwJMOG" +
           "w2xa\nAZ1D3R1dLlfOLeFKsdTAn432hQnMNUxlGu6yDNf8y4qzYvEuee1id3" +
           "/ekB177IvSo/jt/V67UrYC\nI/uTjJMPRUXJWx1fWEnR2iVvgayfzfg2JL5n" +
           "SMGbTz7+ZfDP/xGpMfHNoQSyRjimqs564RjnG9Av\nKFzJElE9RN0/CZ14dk" +
           "0oyuO/XOEXBfUZinxuajgL9uMkOwvAcZCBn+2Rk+g89KRAxIavGAmv+FL5\n" +
           "QkRp+oGwxmZ9Ws3hH+tsD+2Jic9149Jjr+9bF39m6DlewPIkFc/NMTbFkEnF" +
           "1S15K61blFuC1w10\n6Wcjb/60LXHGvHtdHk9hL61/3C5Wl4hpE9Vmvzh1Rw" +
           "3Krzpzv1z584cunLvFuzPj/8KmoYdjFQAA\n");
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
                  KDTree.
                  unpackSplit(
                    ptr);
                int axis =
                  KDTree.
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
                      KDTree.
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
                      KDTree.
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
                      KDTree.
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
                    if (KDTree.
                          unpackAxis(
                            ptr) ==
                          bestAxis) {
                        if (KDTree.
                              unpackSplitType(
                                ptr) !=
                              CLOSED) {
                            int obj =
                              KDTree.
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
                    assert KDTree.
                      unpackAxis(
                        ptr) ==
                      bestAxis;
                    if (KDTree.
                          unpackSplitType(
                            ptr) ==
                          PLANAR) {
                        if (bestPlanarLeft) {
                            int obj =
                              KDTree.
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
                              KDTree.
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
                    if (KDTree.
                          unpackAxis(
                            ptr) ==
                          bestAxis) {
                        if (KDTree.
                              unpackSplitType(
                                ptr) !=
                              OPENED) {
                            int obj =
                              KDTree.
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
                      KDTree.
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
                  getSize();
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
                  updateInner();
                switch (bestAxis) {
                    case 0:
                        this.
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
                        this.
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
                        this.
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
                        this.
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
                        this.
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
                        this.
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
          getSize();
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
            if (KDTree.
                  unpackAxis(
                    ptr) ==
                  0 &&
                  KDTree.
                  unpackSplitType(
                    ptr) !=
                  CLOSED) {
                tempList.
                  add(
                    KDTree.
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
          getMin();
        float intervalMax =
          r.
          getMax();
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
             getMinimum().
             x -
             orgX) *
            invDirX;
        t2 =
          (bounds.
             getMaximum().
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
             getMinimum().
             y -
             orgY) *
            invDirY;
        t2 =
          (bounds.
             getMaximum().
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
             getMinimum().
             z -
             orgZ) *
            invDirZ;
        t2 =
          (bounds.
             getMaximum().
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
          getStack();
        int stackTop =
          state.
          getStackTop();
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
                              getMax() <
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
                                  getMax() <
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1163561096000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVaC5AUxRnu3XtxD70HcIA8Dg7kBI5dXodwRwLL3R4c7D3c" +
       "PQ4B8Zyb7T0GZmeG\nmdljQYISIqgkEaJWaaKIFlUIwWhiEpKKMVA+oqJVal" +
       "U0MeUrphKroomWKSUVK5X/75nZmZ19cMfp\nVk1vz3T33//3v/rvnjnzMSnS" +
       "VDKF13z6boVqvtZID6dqNNoqcprWC4/6+eeKSntOrpdkL/GEiFeI\n6qQyxG" +
       "v+KKdzfiHq72hrSapkniKLuwdFWffRpO7bLjaZ9NaFmjIIbjx2tmb/icI6Ly" +
       "kKkUpOkmSd\n0wVZCoo0rumkKrSdG+L8CV0Q/SFB01tC5AoqJeKtsqTpnKRr" +
       "O8k+UhAixQqPNHUyI2RN7ofJ/Qqn\ncnE/m97fw6YFCmNVqnOCRKOB1HQwsj" +
       "F9JLBtjgtn9gYiY7CxD+AwDgD19BRqA20GVKXg0b6le4+f\nKiCVm0mlIEWQ" +
       "GA9IdJhvM6mI0/gAVbVANEqjm0m1RGk0QlWBE4U9bNbNpEYTBiVOT6hUC1NN" +
       "Foew\nY42WUKjK5rQehkgFj5jUBK/LakpGMYGKUeuuKCZygwC71oZtwG3H5w" +
       "CwTADG1BjHU2tI4Q5BAo3X\nuUekMM5aDx1gaEmc6tvk1FSFEgcPSI2hS5GT" +
       "Bv0RXRWkQehaJCdgFp1clZMoylrh+B3cIO3XyUR3\nvx6jCXqVMkHgEJ2Md3" +
       "djlEBLV7m05NDPvNrPb3/0gadXMdsujFJeRP7LYNA016AwjVGVSjw1Bl5M\n" +
       "+O7p2JSY4iUEOo93dTb6BK4+uyH04e/qjD6Ts/TpHthOeb2f7zpaF755jUwK" +
       "kI0xiqwJqPw05Mwd\nesyWlqQCXlubooiNPqvxXPj5Tbeepv/wkrIOUszLYi" +
       "IOdlTNy3FFEKm6hkpU5XQa7SClVIq2svYO\nUgL1EJi88bQ7FtOo3kEKRfao" +
       "WGb3IKIYkEARlUJdkGKyVVc4fRurJxVCSAlcZAlcY4nxY/86WeDz\nawkpJs" +
       "q7/JrK+2V1MHXPyyr1czxPRf/6tl6VUh9ajqKTkH+bHMcmThIk2T8ogK/y8v" +
       "woHcL/EdJL\nIo81uzweDHpu5xXB7tfKYpSq/fzJD17aG1x/x+2GYaAxm+h0" +
       "MhWm8ZnT+HAaH5vGZ0xDPB5GfRxO\nZygGxLoDHBRCWcWcyNZ1N91eXwAWoe" +
       "wqBJl4oWs94DB5CPJyq+3FHSzg8WBKEx/Zcsh38eRKw5T8\nuYNt1tHlrz52" +
       "4fhnFXO9xJs9EiI2iMVlSKYHw2cqws1y+042+v+8s/PJNy68fY3tRTqZleHc" +
       "mSPR\nOevdWlBlnkYh3NnkT0yqLNhI+o56SSF4PEQ5xj8EkGnuOdKctMUKeI" +
       "ilJETKY7Ia50RssqJUmb5N\nlXfZT5h5VLE6Wmw5Wm01XDNNM2b/2DpewbLW" +
       "MCfUtgsFC6gXDxQvePOp8ueYWKzYW+lY3SJUNzy5\n2jYWtCF4/vZ9PXff+/" +
       "GhLcxSDFPx6LDkJQZEgU/CkNn2EHBhEcIIKnLWBikuR4WYwA2IFC3uy8qr\n" +
       "F/7io+9XGaoR4Yml2cZLE7CfT1pNbr1w4xfTGBkPj0uIDcPuZqAZa1MOqCq3" +
       "G/lI7n996v2/5x6E\nCAdRRRP2UBYoCENGmBz9TO5zWelztS3Eoh5oN+Yw/S" +
       "wLdj+/9/RgfWLni79mXJdzzpXfqYZOTmkx\nNI/FTJTuBLf3ruW0bdBvybmu" +
       "G6rEc/8FipuBIvi9pnWrEDGSaUo0exeVvHX+mdqbXisg3nZSJspc\ntJ1j9k" +
       "9KwfCotg2CTVJZuYrZVtWuMVgyyIQJ4SpTAOxmSqZVNphW2ZDVKrGY7RKpxz" +
       "AkADjRmfGp\nQhxWjiHmNR8crP/tCxseOmREmjl50jrnqH7+lnff21Fw1/kB" +
       "Y5x79XR1PjrtxN+e/CA8zrBKI8WY\nmbHKO8cYaQZDVqmghmbkm4H1fnbejD" +
       "P7wu+YHNWkL5ZBSCj/vvsZ2rDie+9nifEFkAixyVbkMco1\nWCxnTYuwaDY0" +
       "1XRJhSYdd5VaXim3Y/5mh8L+gcZHQy91P8hYzhnJsyjARWfP0xuOXXxFf4fR" +
       "sUMq\njp6RzFwaIee1xy57Y6i6+ImH4l5SsplU8WZW3seJCQxcmyGJ1KxUHT" +
       "L3tPb0hNDIflpSS8YUtwU4\npnUHc1tdUMfeWK9wxe8KlPYEuMaZnjLO7Ske" +
       "wirXsSGzWNmQirYliioMcZipwxQQljHOO9yGxTa0\ntlNH2saGl285wJbXUs" +
       "jdOa3L5g52TFjzgFivzq3nFLF+vmHr2U/OP00bWIwZI2gghYA6mCWPdYz5\n" +
       "lDtNO9+MHWNLZOEApxnycG8AMvP7tLSdie9Khf0FFEWBjK2EwVmyDLDXAHbc" +
       "9PmEqGlOba+3D5yO\nSaejXkacTRXA/ib0UvbEIYsCWdEwq3VsH01Ks7oVDd" +
       "OBKxyTdLTtfWJdxZiHDx9k9E1BljoyZPO+\nZIhTu5zuW864Xrh8wcIly3XS" +
       "91VmkM3XXru0cdGy+YsX6aS0d21HxIemgTNvMc1kH2TmmaJCgKYV\nkhoWka" +
       "5k6wV6AotGzkaAUBgOBtqM9CIVYMKXCjChdNOfCtd4k+z4HKa/HYuITsoUK4" +
       "BqjMjGbGZg\nBLsbnAJuavrqBbyscemC+dcugFWaCdjmDGeX0njFJzGXmHaM" +
       "UEzXwFVriqk2h5iGTDFdkZoasxpG\nZwIkohlbgh5nNxd/u0bIX50Zxaxolo" +
       "2/b5n8FQ/ICSmqWYxNcTIWhz2abzW2wz58tZx0sbVvhGxN\nMi+rno2t75hs" +
       "jYlzSRQJoxBwTXzb8Cdm8zTBNdmceHLGxJjdVNqu1Q5pl/7Wxy8+8ucVn3wE" +
       "IbWd\nFA3hUgQOWGX36krgOczBM/dOLb/nvcOYMSgrTcJ3ZK4NBTosRoLEsc" +
       "OCDSB0jR3rwDpxZUdXbzAc\nCbb29rd2R3rZoG8oBr7VOAq5ccG/c4TwF8E1" +
       "xYQ/JSv8ahtYBwT8QarW/OX4iS/2H1rmxfTbFAAO\n+C6udFi5LwtKrB9hEL" +
       "E4ivB6w4E+wBcIMXj4+G4XmvsvA81UE83ULGiwcviboZdfwcrDw+KyPNjZ\n" +
       "07upf3V314ZINhYfGSGLjXBNM1mcloPFH8LjVVg5NSwWSzsD1/e3BXt612Zz" +
       "iNPDZ5AdrGAAm24y\nOD2HJ/40ky9vGkuF0URccRtsyYAsi5STXAz+bIQMLo" +
       "BrhsngjBwM/uZSDJYhgz0qjQlJK7w5PNg4\nXXTx+dQINQ18epYbbBr/Ga7l" +
       "WLRDsjTYcvivR16+a+a74Ffr3H5FLALPDsskiltD3ZFgm1sDhaKc\nAeu5y4" +
       "DVbMJqzmHAz+PzVWbbq8PjuCcU6AqE8faCi8PXLoPDFpPDlnwc3mq2/XF4HH" +
       "b3BLuCbdk4\n/NMIOVwMnK0wOVyRj8NzZtt7wwsEvZt6gv2dgcj6bEy+n4dJ" +
       "ZmezHTtJj+UVszOykQAmWXjqCzlz\nhB3CJVSKu+ipuY7G2Q760PWfVhzknt" +
       "3qNTe8rcCvLivzRTpERcfM5Ugp7RCzk+0q7D3jnad+fFZ/\nbV6zsRefm3sf" +
       "5B44t/n4nrrmxw9fxtFlnQubm3T10OTrCrYJL7DNhbkFzXjPkT6oJX3jWQb8" +
       "JFSp\nN237OT1lNzWoKEwj9pvBYL/bbmx1Z8a9OYaJuM4fvHaHX+aMgozuxT" +
       "wnF19i8RmslBrV2yCqdspR\nmhF4hmQhapviv4d7uMFu/pUSAjut8sN10BTC" +
       "waxCsI0587TKhHmJJBvpeEpyg/aUY1EISdhAQhDZ\nVvJ/KXieosuExzLhIb" +
       "g+NOF9mBNexMVbMaNYjLcBuxhW5m51qmGaF2TYZIp0oyrA5j5PE5PC2PxC\n" +
       "HmsN6+gOJnmqoI+xcZOxqIKMAFfh7oHtNrNMftWXKT+WHqwDBlaasXXlSHxk" +
       "Q1YfKWQdCvH27lRU\nNaTLoDTkMRL0Ok+9ji+0+B1ssI1x5mgwziOYIhgY14" +
       "0eo6k2xiFjfHEeUE1Y+HRSkZAQlnHu5lKg\nfzTg5gArXSa4rq8B3Mo84FCx" +
       "nmZIDw1wgaSguaC1jAYahC5P2IQW/hqgrc8DrROLdp1UGtAiiijo\n1mLjsM" +
       "s1o8EHmxtPn4mv72vAtzEPvk1YhGEZcuBjXmtji4wGWxNwstXEtnX02My1l9" +
       "2bx2MXnMdj\nTQtdp6QhmefEjraHz5e/fjSxdJ2VRt2IwOhXfWy2eMnyxsWL" +
       "5i9pwvMIlKRmRzw+jxIwynluBAwq\nFxWSC9li71gY+0ezMJ4HwZ8xFXAmqw" +
       "KwcC+MFYxixU4rfmctrAWrIe/b+Fmrca3v5bQddkyAQZOc\ng9j7oA5JZ8fp" +
       "w+xm9bhmGLPjNz8aE7WWRw14gOdRIMFm2UmveabsUMTO0SRgsJn1HjAUYfxn" +
       "VYQ7\nAXMaPIAdlwE2bEuiPqMRj55UzXgxjDJgO2PPbXlkcBiLb4MMBGuoSw" +
       "YHhv2GDZzA0AG+kJ6Y8ZmW\n8WkRH3rr5hs+D/3hP8ZrE+vzn/IQGRNLiKLz" +
       "FZOjXqywMwjW03jhxGKB5wc6qc1uDpBzsn9k0XPU\n6H0v5O3u3pB24J+z23" +
       "0QHR3dwE3NmrPTj3RSAJ2w+oBiKcSxKzAW/GSahFAuM9P2Yey7OWuvlDC+\n" +
       "nOvnr39sy/Tk4d4jbANWxIvcnj1IpixESozvKRhV3G/NyEnNovUqeeLxvqd+" +
       "stwKhOx9+zhHJE4z\n4UVGax6Nwx4v+0cMwbiis88O9vxqws9XnDz2DjvRVf" +
       "4P/Jx+sO4oAAA=");
}
