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
          1425485134000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYa2xcxRUer19rE2zHIcG4ie04DsJxstfOiwRHaR3HIY6X" +
                                                        "2MQmqIvAGd87a9/kvrh31t44NQ2RaFIqRQUMTRBYVRvEKxBUNaIVQsqfFhD9" +
                                                        "Q1W16o9C1T9FpfmRH6WotKVnHnvv7t29zrZSV5rZ2ZlzvjlnzmPO7OXrqNpz" +
                                                        "UY9jGyenDZsmSJYmjhs7EvSkQ7zEoeSOMex6RBs0sOdNwNyk2vlW4+dffn+m" +
                                                        "KYZqUmgVtiybYqrblneEeLYxS7QkagxmhwxiehQ1JY/jWaxkqG4oSd2j/Ul0" +
                                                        "Sx4rRV3JnAgKiKCACAoXQRkIqIDpVmJlzEHGgS3qPYoeQxVJVOOoTDyK1heC" +
                                                        "ONjFpoQZ4xoAQpz9PgpKceasizp83YXORQo/26Ms/uCRpp9UosYUatStcSaO" +
                                                        "CkJQ2CSFVpjEnCKuN6BpREuhlRYh2jhxdWzo81zuFGr29GkL04xL/ENikxmH" +
                                                        "uHzP4ORWqEw3N6NS2/XVS+vE0HK/qtMGngZd1wS6Cg0PsHlQsF4Hwdw0VkmO" +
                                                        "peqEbmkUtYc5fB27RoAAWGtNQmdsf6sqC8MEaha2M7A1rYxTV7emgbTazsAu" +
                                                        "FLVGgrKzdrB6Ak+TSYpawnRjYgmo6vhBMBaKVofJOBJYqTVkpTz7XD+85/wp" +
                                                        "66AV4zJrRDWY/HFgagsxHSFp4hJLJYJxxabkc3jNu+diCAHx6hCxoHn7Wze+" +
                                                        "sbnt2vuC5mslaEanjhOVTqqXpho+WjvYvbuSiRF3bE9nxi/QnLv/mFzpzzoQ" +
                                                        "eWt8RLaYyC1eO/LLb55+jXwWQ/XDqEa1jYwJfrRStU1HN4h7L7GIiynRhlEd" +
                                                        "sbRBvj6MamGc1C0iZkfTaY/QYVRl8Kkam/+GI0oDBDuiWhjrVtrOjR1MZ/g4" +
                                                        "6yCEVkFDLdB6kPjwb4ruV2ZskyhYxZZu2Qr4LsGuOqMQ1VY8bDoGWM3LWGnD" +
                                                        "nlM8V1Vsd9r/rdou41SJoYzsn3AJSTDXcv4foFmmSdNcRQUc8tpwiBsQHQdt" +
                                                        "QyPupLqY2Td0483JD2O+y8szoOgu2CYht0mwbRJ8m4TYpmtfRjc0lg08VFHB" +
                                                        "N7qN7SwsCXY4ARENuW5F9/jDh46d66wEF3LmquAQGWknqCbFGVLtwSDsh3ly" +
                                                        "U8H3Wn700NnEFy9/XfieEp2jS3KjaxfmHj/67d4YihUmW6YeTNUz9jGWIv1U" +
                                                        "2BUOslK4jWc//fzKcwt2EG4F2VtmgWJOFsWdYUO4tko0yIsB/KYOfHXy3YWu" +
                                                        "GKqC1ADpkGJwX8g0beE9CqK5P5cZmS7VoHDadk1ssKVcOqunM649F8xwD2ng" +
                                                        "45VglDhz72Zo/dLf+TdbXeWw/jbhUczKIS145j3w82sXrz7fszuWn6Qb8669" +
                                                        "cUJFyK8MnIS5Ecz/4cLYM89eP/sQ9xCg2FBqgy7WD0ICAJPBsT7x/qO//+Tj" +
                                                        "S7+J+V6FssB6ZwAOWcGAzMRM3vWAZdqantbxlEGYT/6zcWPf1b+ebxJGNGAm" +
                                                        "5wObbw4QzN+xD53+8JG/t3GYCpXdSoHCAZnQe1WAPOC6+CSTI/v4r9ddfA+/" +
                                                        "CEkTEpWnzxOee5BUiAmV4Bbq5v2W0Fov6zqcorUsn2nhvxpg6+7o2DnALte8" +
                                                        "mPvHqDF15k9fcI2KoqbEnRLiTymXX2gd3PsZ5w/cl3G3Z4szERQiAe/W18y/" +
                                                        "xTprfhFDtSnUpMoq5yg2MsxJUnCze7nSByqhgvXCW1pcSf1+eK4Nh07etuHA" +
                                                        "CTIgjBk1G9eHYqWJnfI6aJtlrGwOx0oF4oNdnKWT9xtZd5dIgBTVOq4+i1kJ" +
                                                        "heJQ0x22NeItb6kxVzfhcpyVt7ey0PzJiRc+fUNkx7BZQsTk3OKTXyXOL8by" +
                                                        "6qENRSVJPo+oibjetwq9v4JPBbR/s8b0ZRPiTmwelBdzh38zOw4Lx/XLicW3" +
                                                        "OPDnKwvvvLJwVqjRXFgODMHJvPHbf/0qceGPH5S4nyqh1ONJSUTAtkL7tEHb" +
                                                        "Iu2zJcI+B1l3D0V1sFGS4FmRGgeiQduhJSRoIgJ0RILWexlTuGI5qIpEVSJQ" +
                                                        "D+dQTd0qH7VXovZGoN7vo+JseajM7fskal8E6oREjcMJ7CeOKKtugrlVYm6N" +
                                                        "wHwwhwn6l4u5TWJui8BM+Zg4Ww4mO9HtEnN7BObDuRP1Xaq3DNQdEnVHBOqx" +
                                                        "ItS+MlB3StSdEahqEerWMlDvlqh3R6Cmi1C3lYG6S6LuikDVi1C33wS1A9pu" +
                                                        "ibo7AtWQqLcEqE4ebLZ0/o6x4R6Kajz+Fs6/bnkVsi7qtcZT3qUzi0va6Et9" +
                                                        "MXmLDwGQfEQHOJUMpqBGvo8/ToPr8slXX3+bftRzj8icm6IvjjDje2f+0jqx" +
                                                        "d+bYf1EZt4cUCkO+et/lD+69U306hir9W7fovV3I1F9419a7hGZca6Lgxm0r" +
                                                        "rE5boY1Ig46EDcoNxrquZQqmx5ZZO826U+AKGUeDG2zYsqRB9rJunzDyfoqq" +
                                                        "Zm1dK665+MRcocDd0EalwKNlCxwLfIy74QAn/e4yon+Pdd+B2BCigyPzGDxT" +
                                                        "npR3QDOllOb/dKxPLbP2DOvOg2xQ8FiUPxcjZINSqD54U/Ll1RStW/YRClHS" +
                                                        "UvQHlvjTRX1zqTF++9IDv+NPKf+PkbokiqczhpFf5+WNaxyXpHUueZ2o+hz+" +
                                                        "9TwU96Uloaiaf3OBLwrqFylqClOD57CvfLIfgrflkUFVKEf5RD+GQgeI2PCS" +
                                                        "kzuVJv6YYNVuQlzbWVSQhJxwStpQkB/4X4O5WM6IPwcn1StLhw6furHzJZ4Y" +
                                                        "qlUDz88zlHgS1YrnpJ8P1kei5bBqDnZ/2fBW3cZckmtgXbN8Q4Zkay/95hoy" +
                                                        "HcpfSfM/u/2ne15e+pi/9f4DXrE6irMVAAA=");
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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "8x/v/EAQxhAAAA==");
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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "zzk+a4Wd6s7BosHlgxa7wubO/wOYEteGKiIAAA==");
}
