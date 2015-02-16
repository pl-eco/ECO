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
          1163561096000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYXWxcRxUer//d1N44TeqaxHYcp6rjZK+dvyZ1FHAcp3G8" +
                                                        "jU3sBLGldcb3zto3vn+9d9beOLikkUpCkSKgbkmq1kKQqrSkTYWICkKV8gJt" +
                                                        "VV6KEIgHWsQLFSUPeaBUFChnfvbe3bt7nQWJlWZ2duacb86Z8zNn9upNVO25" +
                                                        "qMexjdPThk0TJEsTp4xdCXraIV7iSHLXGHY9og0a2PMmYG5S7Xy96eNPvzUT" +
                                                        "j6GaFFqDLcummOq25R0jnm3MES2JmoLZIYOYHkXx5Ck8h5UM1Q0lqXu0P4nu" +
                                                        "yGOlqCuZE0EBERQQQeEiKAMBFTDdSayMOcg4sEW9x9DjqCKJahyViUfRxkIQ" +
                                                        "B7vYlDBjXANAqGO/T4BSnDnrog5fd6FzkcLP9ChL3300/uNK1JRCTbo1zsRR" +
                                                        "QQgKm6TQKpOYU8T1BjSNaCm02iJEGyeujg19gcudQs2ePm1hmnGJf0hsMuMQ" +
                                                        "l+8ZnNwqlenmZlRqu756aZ0YWu5XddrA06DrukBXoeEhNg8KNuggmJvGKsmx" +
                                                        "VM3qlkZRe5jD17FrBAiAtdYkdMb2t6qyMEygZmE7A1vTyjh1dWsaSKvtDOxC" +
                                                        "UWskKDtrB6uzeJpMUtQSphsTS0BVzw+CsVC0NkzGkcBKrSEr5dnn5tF9F89Y" +
                                                        "h60Yl1kjqsHkrwOmthDTMZImLrFUIhhXbUk+i9e9eSGGEBCvDRELmje+eusL" +
                                                        "W9tuvC1oPleCZnTqFFHppHplqvG99YPdeyuZGHWO7enM+AWac/cfkyv9WQci" +
                                                        "b52PyBYTucUbx3755bOvkI9iqGEY1ai2kTHBj1artunoBnEfJBZxMSXaMKon" +
                                                        "ljbI14dRLYyTukXE7Gg67RE6jKoMPlVj899wRGmAYEdUC2PdStu5sYPpDB9n" +
                                                        "HYTQGmioBVoPEh/+TdFXlOMeuLuCVWzplq2A8xLsqjMKUe3JKTjdGRO7s56i" +
                                                        "Zjxqm4qXsdKGPa94rqrY7rT/W7VdAhgqMZSRgxMuIQnmZc7/GT/L9IvPV1TA" +
                                                        "0a8PB74BMXPYNjTiTqpLmQNDt16bfDfmB4I8GYrug20ScpsE2ybBt0mIbboO" +
                                                        "ZHRDYznCQxUVfKO72M7CvmCdWYhzyICruscfOXLyQmclOJYzXwVHy0g7QUkp" +
                                                        "zpBqDwbJYJinPBU8suX7D59PfPLS54VHKtGZuyQ3unFp/okTX+uNoVhhCmbq" +
                                                        "wVQDYx9jidNPkF3h0CuF23T+w4+vPbtoB0FYkNNlbijmZLHdGTaEa6tEg2wZ" +
                                                        "wG/pwNcn31zsiqEqSBiQJCkGp4b80xbeoyDG+3P5kulSDQqnbdfEBlvKJbkG" +
                                                        "OuPa88EM95BGPl4NRqljTt8MrV9GAf9mq2sc1t8lPIpZOaQFz8eHfnbj8vXn" +
                                                        "evbG8lN3U95lOE6oSASrAydhbgTzf7g09vQzN88/zD0EKDaV2qCL9YOQFsBk" +
                                                        "cKxPvv3Y7z94/8pvYr5XoSyw3huAQ64wIF8xk3cdt0xb09M6njII88l/Nm3u" +
                                                        "u/7Xi3FhRANmcj6w9fYAwfw9B9DZdx/9exuHqVDZXRUoHJAJvdcEyAOui08z" +
                                                        "ObJP/HrD5bfwC5BKIX15+gLhGQlJhZhQCW6hbt5vC631sq7DKVrL8pkW/qsR" +
                                                        "tu6Ojp1D7MrNi7l/jBpT5/70CdeoKGpK3DQh/pRy9fnWwf0fcf7AfRl3e7Y4" +
                                                        "E0F5EvBuf8X8W6yz5hcxVJtCcVXWPiewkWFOkoL73ssVRFAfFawX3t3iour3" +
                                                        "w3N9OHTytg0HTpABYcyo2bghFCtxdsoboG2VsbI1HCsViA/2cJZO3m9m3X0i" +
                                                        "AVJU67j6HGaFFaqDSu+orRFvZUuNuboJV+acvNOVxeYPZp//8FWRHcNmCRGT" +
                                                        "C0tPfZa4uBTLq5I2FRUq+TyiUuJ63yn0/gw+FdD+zRrTl02Im7J5UF7XHf59" +
                                                        "7TgsHDeuJBbf4tCfry3+/IeL54UazYVFwhCczKu//devEpf++E6J+6kSCkCe" +
                                                        "lEQE7Ci0Txu0bdI+2yLsc5h1D1BUDxslCZ4TqXEgGrQdWkKCJiJARyRog5cx" +
                                                        "hSuWg6pIVCUC9WgO1dSt8lF7JWpvBOoXfVScLQ+VuX2fRO2LQJ2QqHVwAgeJ" +
                                                        "I4qt22Bul5jbIzC/lMME/cvF3CExd0RgpnxMnC0Hk53oTom5MwLzkdyJ+i7V" +
                                                        "WwbqLom6KwL1ZBFqXxmouyXq7ghUtQh1exmo90vU+yNQ00WoO8pA3SNR90Sg" +
                                                        "6kWoO2+D2gFtr0TdG4FqSNQ7AlQnDzZbOn/H2HAfRTUefyHnX7e8CtkQ9Ybj" +
                                                        "Ke/KuaVlbfTFvpi8xYcASD6tA5xKBlNQIz/En6zBdfnUyz96g77X84DInFui" +
                                                        "L44w41vn/tI6sX/m5H9RGbeHFApDvvzQ1XcevFf9TgxV+rdu0Su8kKm/8K5t" +
                                                        "cAnNuNZEwY3bVlidtkIbkQYdCRuUG4x1XSsUTI+vsHaWdWfAFTKOBjfYsGVJ" +
                                                        "g+xn3QFh5IMUVc3ZulZcc/GJ+UKBu6GNSoFHyxY4FvgYd8MBTvqNFUT/Juu+" +
                                                        "DrEhRAdH5jF4rjwp74FmSinN/+lYv73C2tOsuwiyQcFjUf5cjJANSqGG4E3J" +
                                                        "l9dStGHFRyhESUvR31rirxj1teWmuruXj/+OP6X8v0vqk6gunTGM/Dovb1zj" +
                                                        "uCStc8nrRdXn8K/noLgvLQlF1fybC3xZUL9AUTxMDZ7DvvLJvgfelkcGVaEc" +
                                                        "5RP9AAodIGLDK07uVOL8McGq3YS4trOoIAk54ZS0qSA/8D8Mc7GcEX8ZTqrX" +
                                                        "lo8cPXNr94s8MVSrBl5YYCh1SVQrnpN+PtgYiZbDqjnc/Wnj6/Wbc0mukXXN" +
                                                        "8g0Zkq299JtryHQofyUt/PTun+x7afl9/tb7D/dxTATJFQAA");
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
          1163561096000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "nvvhmtMT13n/8x9mOwHC3BAAAA==");
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
      1163561096000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
