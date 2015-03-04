package org.sunflow.core.accel;
import org.sunflow.core.AccelerationStructure;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.math.BoundingBox;
import org.sunflow.system.Memory;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
import org.sunflow.util.IntArray;
public class BoundingIntervalHierarchy implements AccelerationStructure {
    private int[] tree;
    private int[] objects;
    private PrimitiveList primitives;
    private BoundingBox bounds;
    private int maxPrims;
    public BoundingIntervalHierarchy() { super();
                                         maxPrims = 2; }
    public void build(PrimitiveList primitives) { this.primitives = primitives;
                                                  int n = primitives.getNumPrimitives(
                                                                       );
                                                  UI.printDetailed(
                                                       Module.
                                                         ACCEL,
                                                       "Getting bounding box ...");
                                                  bounds = primitives.
                                                             getWorldBounds(
                                                               null);
                                                  objects = (new int[n]);
                                                  for (int i = 0;
                                                       i <
                                                         n;
                                                       i++) objects[i] =
                                                              i;
                                                  UI.printDetailed(
                                                       Module.
                                                         ACCEL,
                                                       "Creating tree ...");
                                                  int initialSize =
                                                    3 *
                                                    (2 *
                                                       6 *
                                                       n +
                                                       1);
                                                  IntArray tempTree =
                                                    new IntArray(
                                                    (initialSize +
                                                       3) /
                                                      4);
                                                  BuildStats stats =
                                                    new BuildStats(
                                                    );
                                                  Timer t = new Timer(
                                                    );
                                                  t.start();
                                                  buildHierarchy(
                                                    tempTree,
                                                    objects,
                                                    stats);
                                                  t.end();
                                                  UI.printDetailed(
                                                       Module.
                                                         ACCEL,
                                                       "Trimming tree ...");
                                                  tree = tempTree.
                                                           trim(
                                                             );
                                                  stats.printStats(
                                                          );
                                                  UI.printDetailed(
                                                       Module.
                                                         ACCEL,
                                                       "  * Creation time:  %s",
                                                       t);
                                                  UI.printDetailed(
                                                       Module.
                                                         ACCEL,
                                                       "  * Usage of init:  %3d%%",
                                                       100 *
                                                         tree.
                                                           length /
                                                         initialSize);
                                                  UI.printDetailed(
                                                       Module.
                                                         ACCEL,
                                                       "  * Tree memory:    %s",
                                                       Memory.
                                                         sizeof(
                                                           tree));
                                                  UI.printDetailed(
                                                       Module.
                                                         ACCEL,
                                                       "  * Indices memory: %s",
                                                       Memory.
                                                         sizeof(
                                                           objects));
    }
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
        private int numBVH2;
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
                       numLeaves4p = 0;
                       numBVH2 = 0; }
        void updateInner() { numNodes++; }
        void updateBVH2() { numBVH2++; }
        void updateLeaf(int depth, int n) { numLeaves++;
                                            minDepth = Math.
                                                         min(
                                                           depth,
                                                           minDepth);
                                            maxDepth = Math.
                                                         max(
                                                           depth,
                                                           maxDepth);
                                            sumDepth += depth;
                                            minObjects = Math.
                                                           min(
                                                             n,
                                                             minObjects);
                                            maxObjects = Math.
                                                           max(
                                                             n,
                                                             maxObjects);
                                            sumObjects +=
                                              n;
                                            switch (n) { case 0:
                                                             numLeaves0++;
                                                             break;
                                                         case 1:
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
                                                             break;
                                            } }
        void printStats() { UI.printDetailed(Module.ACCEL,
                                             "Tree stats:");
                            UI.printDetailed(Module.ACCEL,
                                             "  * Nodes:          %d",
                                             numNodes);
                            UI.printDetailed(Module.ACCEL,
                                             "  * Leaves:         %d",
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
                            UI.printDetailed(Module.ACCEL,
                                             "  * BVH2 nodes:     %d (%3d%%)",
                                             numBVH2,
                                             100 *
                                               numBVH2 /
                                               (numNodes +
                                                  numLeaves -
                                                  2 *
                                                  numBVH2));
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVYX2wUxxkfn/+dHcIdJhDHBdsYE8WQ3kL4E8COwT5MbHLB" +
                                                        "DjZUXESO8e6cvXhvd7M7Zx+mTgNRBI0qlD8OhYr6oQLlH4GoKkqrKhIvbRKl" +
                                                        "L6mqVn1oUvWlUVMeeGgaNW3Tb2Znb+/2bs2Rl540c3Mz3/eb75vvz3xzV26i" +
                                                        "WttCG0xDOz6hGTRGcjR2TNsao8dNYsf2JbaOYMsmSlzDtj0Gcym5453IF1+9" +
                                                        "OBkNobokWo513aCYqoZuHyC2oU0TJYEi3uyARjI2RdHEMTyNpSxVNSmh2rQ7" +
                                                        "ge4qYKWoM+GKIIEIEoggcRGkPo8KmO4mejYTZxxYp/bT6BlUlUB1pszEo2hN" +
                                                        "MYiJLZwRMCNcA0AIs9+HQCnOnLNQe153R+cShV/dIM3/8KnoT6tRJIkiqj7K" +
                                                        "xJFBCAqbJNGSDMmME8vuUxSiJNEynRBllFgq1tRZLncSNdnqhI5p1iL5Q2KT" +
                                                        "WZNYfE/v5JbITDcrK1PDyquXVommuL9q0xqeAF1Xero6Gu5l86BgowqCWWks" +
                                                        "E5elZkrVFYra/Bx5HTsfAwJgrc8QOmnkt6rRMUygJsd2GtYnpFFqqfoEkNYa" +
                                                        "WdiFopZAUHbWJpan8ARJUdTspxtxloCqgR8EY6FohZ+MI4GVWnxWKrDPzf09" +
                                                        "Z0/og3qIy6wQWWPyh4Gp1cd0gKSJRXSZOIxL1ifO4ZXvnQkhBMQrfMQOzbvf" +
                                                        "vbX7wdYbHzg03ypDMzx+jMg0JV8aX/rxqnjXjmomRtg0bJUZv0hz7v4jYqU7" +
                                                        "Z0LkrcwjssWYu3jjwK8PP/sm+TyEGodQnWxo2Qz40TLZyJiqRqxHiU4sTIky" +
                                                        "hBqIrsT5+hCqh3FC1YkzO5xO24QOoRqNT9UZ/DccURog2BHVw1jV04Y7NjGd" +
                                                        "5OOciRBaDg01Q9uMnA//pmhGmjQyRMIy1lXdkMB3CbbkSYnIRsoipiENxIel" +
                                                        "cTjlyQy2pmzJzuppzZhJyVmbGhnJtmTJsCbcaUk2LAYmE03qN7K6Au41xBx4" +
                                                        "GmuDKigJ0MdjzAHN/9/WOXYq0ZmqKjDYKn+60CDSBg1NIVZKns/2D9y6mvoo" +
                                                        "lA8fcZ4UdcPOMbFzjO0c4zvHAnfu7M+qmsKSjY2qqvje9zBhHEcBM09BwoBU" +
                                                        "uqRr9Mi+o2c6qsFDzZkasBEj7YAzERIOyEbcyypDPHfK4NrNP3nydOzL13Y5" +
                                                        "ri0FXwFludGN8zMnD31vYwiFinM50ximGhn7CMvA+Uzb6Y/hcriR0599ce3c" +
                                                        "nOFFc9HlIJJMKSdLEh1+21iGTBRIux78+nZ8PfXeXGcI1UDmgWxLMUQHJLJW" +
                                                        "/x5FyaLbTbxMl1pQOG1YGayxJTdbNtJJy5jxZrjTLOXjZWCUMIueJmh7RDjx" +
                                                        "b7a63GT9PY6TMSv7tOCJfe8vbly4/qMNO0KFd0Ck4FYdJdTJKMs8JxmzCIH5" +
                                                        "P50feeXVm6ef5B4CFGvLbdDJ+jjkFzAZHOvzHzz9x08/ufS7UN6rUA5Y7/fA" +
                                                        "IelokPiYyTsP6hlDUdMqHtcI88l/R9Ztuv73s1HHiBrMuD7w4O0BvPn7+tGz" +
                                                        "Hz31z1YOUyWzS89T2CNz9F7uIfdZFj7O5Mid/O3qC+/jH0NOhjxoq7OEpzYk" +
                                                        "FGJCxbiFunj/bd/aRta1myVrOT7TzH9FYOuu4NjZy+7ugpj717A2fuovX3KN" +
                                                        "SqKmzJXl409KVy62xHs/5/ye+zLutlxpcoI6x+N96M3MP0Iddb8Kofokisqi" +
                                                        "iDqEtSxzkiQUDrZbWUGhVbReXAQ4N153PjxX+UOnYFt/4HhJEcaMmo0bfbES" +
                                                        "Zae8GtoWEStb/LFShfhgO2fp4P061j3gJECK6k1LncasQkNhKBn3GwqxF7fU" +
                                                        "iKVm4O6dFsWBNNf06dTFz952sqPfLD5icmb+ha9jZ+dDBeXW2pKKp5DHKbm4" +
                                                        "3nc7en8Nnypo/2WN6csmnCu3KS7u/fb8xW+aLBzXLCYW32LvX6/N/fL1udOO" +
                                                        "Gk3F1cYAnMzbv//Pb2Ln//xhmSurGipJnpScCNhcbJ9WaFuFfbYG2GeQdTsp" +
                                                        "aoCNEgRPO6mxLxi0Ddo2AbotAPQxAdpoZzOOK1aC+rBAfTgAdb+LmlH1ylG3" +
                                                        "C9TtAahP5FFxrjJU5vY7BOqOANQxgRqGE9hDTKdquw3mToG5MwDzOy4m6F8p" +
                                                        "ZrfA7A7ATOYxca4STHaiPQKzJwDziHuieZfaWAHqIwL1kQDUoyWomypA7RWo" +
                                                        "vQGocgnqQxWg7hKouwJQ0yWomytA3S1QdwegqiWoW26D2g6tT6D2BaBqAvUu" +
                                                        "D9W8DewqaP0Ctj8A1hCw9QDbf2iw8FRz5a+EEBv2UFRn89d74Q3OC5vVQe9L" +
                                                        "nkUvnZpfUIYvbwqJwmAAgMSz38OpYTBFZffj/Dnt3cAvvPHWu/TjDTudZLw+" +
                                                        "+C7yM75/6m8tY72TR++g2G7zKeSHfOPxKx8+er/8cghV5y/ykn8Iipm6i6/v" +
                                                        "RovQrKWPFV3ircUFbwu0g8KYB/3G5AZjXeciNdjJRdaeY90z4F1ZU4FLcUjX" +
                                                        "hUF6WdfvGHkPRTXThqqUlnF8YrZY4PugHRYCH/5GAv9gkbWzrPs+BJkjsOu6" +
                                                        "z1cmWxe0I0K2IxXLFvL8n4dIHyedX0TKc6x7KS8lxG36DqRkJ3hCSHniG53g" +
                                                        "xUXWFlh3AWSD+k6n/HUcIBtUfo3eE5ovr6Bo450+wyGom0v+IXT+1ZKvLkTC" +
                                                        "9y4c/AN/TOb/eWpIoHA6q2mFlW7BuM60SFrlyjQ4da/Jvy7D86a8cBTV8m+u" +
                                                        "wyWH+nWKon5qcHT2VUj2FgRHARlkTDEqJLoKpR4QseE10z2oKH9OsXo/5hQu" +
                                                        "OVSUM01/Bl1blM74f69u6sk6/76m5GsL+/afuLXtMs9jtbKGZ2cZSjiB6p0H" +
                                                        "dT59rQlEc7HqBru+WvpOwzo3Jy9lXZN4Rftkayv/6hzImJS/E2d/fu/Pel5b" +
                                                        "+IS/dv8HiBpo5BQXAAA=");
    }
    private void buildHierarchy(IntArray tempTree,
                                int[] indices,
                                BuildStats stats) {
        tempTree.
          add(
            3 <<
              30);
        tempTree.
          add(
            0);
        tempTree.
          add(
            0);
        if (objects.
              length ==
              0)
            return;
        float[] gridBox =
          { bounds.
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
          z };
        float[] nodeBox =
          { bounds.
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
          z };
        subdivide(
          0,
          objects.
            length -
            1,
          tempTree,
          indices,
          gridBox,
          nodeBox,
          0,
          1,
          stats);
    }
    private void createNode(IntArray tempTree,
                            int nodeIndex,
                            int left,
                            int right) { tempTree.
                                           set(
                                             nodeIndex +
                                               0,
                                             3 <<
                                               30 |
                                               left);
                                         tempTree.
                                           set(
                                             nodeIndex +
                                               1,
                                             right -
                                               left +
                                               1);
    }
    private void subdivide(int left, int right,
                           IntArray tempTree,
                           int[] indices,
                           float[] gridBox,
                           float[] nodeBox,
                           int nodeIndex,
                           int depth,
                           BuildStats stats) {
        if (right -
              left +
              1 <=
              maxPrims ||
              depth >=
              64) {
            stats.
              updateLeaf(
                depth,
                right -
                  left +
                  1);
            createNode(
              tempTree,
              nodeIndex,
              left,
              right);
            return;
        }
        int axis =
          -1;
        int prevAxis;
        int rightOrig;
        float clipL =
          Float.
            NaN;
        float clipR =
          Float.
            NaN;
        float prevClip =
          Float.
            NaN;
        float split =
          Float.
            NaN;
        float prevSplit;
        boolean wasLeft =
          true;
        while (true) {
            prevAxis =
              axis;
            prevSplit =
              split;
            float[] d =
              { gridBox[1] -
              gridBox[0],
            gridBox[3] -
              gridBox[2],
            gridBox[5] -
              gridBox[4] };
            if (d[0] <
                  0 ||
                  d[1] <
                  0 ||
                  d[2] <
                  0)
                throw new IllegalStateException(
                  "negative node extents");
            for (int i =
                   0;
                 i <
                   3;
                 i++) {
                if (nodeBox[2 *
                              i +
                              1] <
                      gridBox[2 *
                                i] ||
                      nodeBox[2 *
                                i] >
                      gridBox[2 *
                                i +
                                1]) {
                    UI.
                      printError(
                        Module.
                          ACCEL,
                        ("Reached tree area in error - discarding node with: %d object" +
                         "s"),
                        right -
                          left +
                          1);
                    throw new IllegalStateException(
                      "invalid node overlap");
                }
            }
            if (d[0] >
                  d[1] &&
                  d[0] >
                  d[2])
                axis =
                  0;
            else
                if (d[1] >
                      d[2])
                    axis =
                      1;
                else
                    axis =
                      2;
            split =
              0.5F *
                (gridBox[2 *
                           axis] +
                   gridBox[2 *
                             axis +
                             1]);
            clipL =
              Float.
                NEGATIVE_INFINITY;
            clipR =
              Float.
                POSITIVE_INFINITY;
            rightOrig =
              right;
            float nodeL =
              Float.
                POSITIVE_INFINITY;
            float nodeR =
              Float.
                NEGATIVE_INFINITY;
            for (int i =
                   left;
                 i <=
                   right;
                 ) {
                int obj =
                  indices[i];
                float minb =
                  primitives.
                  getPrimitiveBound(
                    obj,
                    2 *
                      axis +
                      0);
                float maxb =
                  primitives.
                  getPrimitiveBound(
                    obj,
                    2 *
                      axis +
                      1);
                float center =
                  (minb +
                     maxb) *
                  0.5F;
                if (center <=
                      split) {
                    i++;
                    if (clipL <
                          maxb)
                        clipL =
                          maxb;
                }
                else {
                    int t =
                      indices[i];
                    indices[i] =
                      indices[right];
                    indices[right] =
                      t;
                    right--;
                    if (clipR >
                          minb)
                        clipR =
                          minb;
                }
                if (nodeL >
                      minb)
                    nodeL =
                      minb;
                if (nodeR <
                      maxb)
                    nodeR =
                      maxb;
            }
            if (nodeL >
                  nodeBox[2 *
                            axis +
                            0] &&
                  nodeR <
                  nodeBox[2 *
                            axis +
                            1]) {
                float nodeBoxW =
                  nodeBox[2 *
                            axis +
                            1] -
                  nodeBox[2 *
                            axis +
                            0];
                float nodeNewW =
                  nodeR -
                  nodeL;
                if (1.3F *
                      nodeNewW <
                      nodeBoxW) {
                    stats.
                      updateBVH2(
                        );
                    int nextIndex =
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
                    stats.
                      updateInner(
                        );
                    tempTree.
                      set(
                        nodeIndex +
                          0,
                        axis <<
                          30 |
                          1 <<
                          29 |
                          nextIndex);
                    tempTree.
                      set(
                        nodeIndex +
                          1,
                        Float.
                          floatToRawIntBits(
                            nodeL));
                    tempTree.
                      set(
                        nodeIndex +
                          2,
                        Float.
                          floatToRawIntBits(
                            nodeR));
                    nodeBox[2 *
                              axis +
                              0] =
                      nodeL;
                    nodeBox[2 *
                              axis +
                              1] =
                      nodeR;
                    subdivide(
                      left,
                      rightOrig,
                      tempTree,
                      indices,
                      gridBox,
                      nodeBox,
                      nextIndex,
                      depth +
                        1,
                      stats);
                    return;
                }
            }
            if (right ==
                  rightOrig) {
                if (clipL <=
                      split) {
                    gridBox[2 *
                              axis +
                              1] =
                      split;
                    prevClip =
                      clipL;
                    wasLeft =
                      true;
                    continue;
                }
                if (prevAxis ==
                      axis &&
                      prevSplit ==
                      split) {
                    stats.
                      updateLeaf(
                        depth,
                        right -
                          left +
                          1);
                    createNode(
                      tempTree,
                      nodeIndex,
                      left,
                      right);
                    return;
                }
                gridBox[2 *
                          axis +
                          1] =
                  split;
                prevClip =
                  Float.
                    NaN;
            }
            else
                if (left >
                      right) {
                    right =
                      rightOrig;
                    if (clipR >=
                          split) {
                        gridBox[2 *
                                  axis +
                                  0] =
                          split;
                        prevClip =
                          clipR;
                        wasLeft =
                          false;
                        continue;
                    }
                    if (prevAxis ==
                          axis &&
                          prevSplit ==
                          split) {
                        stats.
                          updateLeaf(
                            depth,
                            right -
                              left +
                              1);
                        createNode(
                          tempTree,
                          nodeIndex,
                          left,
                          right);
                        return;
                    }
                    gridBox[2 *
                              axis +
                              0] =
                      split;
                    prevClip =
                      Float.
                        NaN;
                }
                else {
                    if (prevAxis !=
                          -1 &&
                          !Float.
                          isNaN(
                            prevClip)) {
                        int nextIndex =
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
                        if (wasLeft) {
                            stats.
                              updateInner(
                                );
                            tempTree.
                              set(
                                nodeIndex +
                                  0,
                                prevAxis <<
                                  30 |
                                  nextIndex);
                            tempTree.
                              set(
                                nodeIndex +
                                  1,
                                Float.
                                  floatToRawIntBits(
                                    prevClip));
                            tempTree.
                              set(
                                nodeIndex +
                                  2,
                                Float.
                                  floatToRawIntBits(
                                    Float.
                                      POSITIVE_INFINITY));
                        }
                        else {
                            stats.
                              updateInner(
                                );
                            tempTree.
                              set(
                                nodeIndex +
                                  0,
                                prevAxis <<
                                  30 |
                                  nextIndex -
                                  3);
                            tempTree.
                              set(
                                nodeIndex +
                                  1,
                                Float.
                                  floatToRawIntBits(
                                    Float.
                                      NEGATIVE_INFINITY));
                            tempTree.
                              set(
                                nodeIndex +
                                  2,
                                Float.
                                  floatToRawIntBits(
                                    prevClip));
                        }
                        depth++;
                        stats.
                          updateLeaf(
                            depth,
                            0);
                        nodeIndex =
                          nextIndex;
                    }
                    break;
                }
        }
        int nextIndex =
          tempTree.
          getSize(
            );
        int nl =
          right -
          left +
          1;
        int nr =
          rightOrig -
          (right +
             1) +
          1;
        if (nl >
              0) {
            tempTree.
              add(
                0);
            tempTree.
              add(
                0);
            tempTree.
              add(
                0);
        }
        else
            nextIndex -=
              3;
        if (nr >
              0) {
            tempTree.
              add(
                0);
            tempTree.
              add(
                0);
            tempTree.
              add(
                0);
        }
        stats.
          updateInner(
            );
        tempTree.
          set(
            nodeIndex +
              0,
            axis <<
              30 |
              nextIndex);
        tempTree.
          set(
            nodeIndex +
              1,
            Float.
              floatToRawIntBits(
                clipL));
        tempTree.
          set(
            nodeIndex +
              2,
            Float.
              floatToRawIntBits(
                clipR));
        float[] gridBoxL =
          new float[6];
        float[] gridBoxR =
          new float[6];
        float[] nodeBoxL =
          new float[6];
        float[] nodeBoxR =
          new float[6];
        for (int i =
               0;
             i <
               6;
             i++) {
            gridBoxL[i] =
              (gridBoxR[i] =
                 gridBox[i]);
            nodeBoxL[i] =
              (nodeBoxR[i] =
                 nodeBox[i]);
        }
        gridBoxL[2 *
                   axis +
                   1] =
          (gridBoxR[2 *
                      axis] =
             split);
        nodeBoxL[2 *
                   axis +
                   1] =
          clipL;
        nodeBoxR[2 *
                   axis +
                   0] =
          clipR;
        gridBox =
          (nodeBox =
             null);
        if (nl >
              0)
            subdivide(
              left,
              right,
              tempTree,
              indices,
              gridBoxL,
              nodeBoxL,
              nextIndex,
              depth +
                1,
              stats);
        else
            stats.
              updateLeaf(
                depth +
                  1,
                0);
        if (nr >
              0)
            subdivide(
              right +
                1,
              rightOrig,
              tempTree,
              indices,
              gridBoxR,
              nodeBoxR,
              nextIndex +
                3,
              depth +
                1,
              stats);
        else
            stats.
              updateLeaf(
                depth +
                  1,
                0);
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
          Float.
          floatToRawIntBits(
            dirX) >>>
          31;
        int offsetYFront =
          Float.
          floatToRawIntBits(
            dirY) >>>
          31;
        int offsetZFront =
          Float.
          floatToRawIntBits(
            dirZ) >>>
          31;
        int offsetXBack =
          offsetXFront ^
          1;
        int offsetYBack =
          offsetYFront ^
          1;
        int offsetZBack =
          offsetZFront ^
          1;
        int offsetXFront3 =
          offsetXFront *
          3;
        int offsetYFront3 =
          offsetYFront *
          3;
        int offsetZFront3 =
          offsetZFront *
          3;
        int offsetXBack3 =
          offsetXBack *
          3;
        int offsetYBack3 =
          offsetYBack *
          3;
        int offsetZBack3 =
          offsetZBack *
          3;
        offsetXFront++;
        offsetYFront++;
        offsetZFront++;
        offsetXBack++;
        offsetYBack++;
        offsetZBack++;
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
            pushloop: while (true) {
                int tn =
                  tree[node];
                int axis =
                  tn &
                  7 <<
                  29;
                int offset =
                  tn &
                  ~(7 <<
                      29);
                switch (axis) {
                    case 0:
                        {
                            float tf =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetXFront]) -
                                 orgX) *
                              invDirX;
                            float tb =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetXBack]) -
                                 orgX) *
                              invDirX;
                            if (tf <
                                  intervalMin &&
                                  tb >
                                  intervalMax)
                                break pushloop;
                            int back =
                              offset +
                              offsetXBack3;
                            node =
                              back;
                            if (tf <
                                  intervalMin) {
                                intervalMin =
                                  tb >=
                                    intervalMin
                                    ? tb
                                    : intervalMin;
                                continue;
                            }
                            node =
                              offset +
                                offsetXFront3;
                            if (tb >
                                  intervalMax) {
                                intervalMax =
                                  tf <=
                                    intervalMax
                                    ? tf
                                    : intervalMax;
                                continue;
                            }
                            stack[stackPos].
                              node =
                              back;
                            stack[stackPos].
                              near =
                              tb >=
                                intervalMin
                                ? tb
                                : intervalMin;
                            stack[stackPos].
                              far =
                              intervalMax;
                            stackPos++;
                            intervalMax =
                              tf <=
                                intervalMax
                                ? tf
                                : intervalMax;
                            continue;
                        }
                    case 1 <<
                      30:
                        {
                            float tf =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetYFront]) -
                                 orgY) *
                              invDirY;
                            float tb =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetYBack]) -
                                 orgY) *
                              invDirY;
                            if (tf <
                                  intervalMin &&
                                  tb >
                                  intervalMax)
                                break pushloop;
                            int back =
                              offset +
                              offsetYBack3;
                            node =
                              back;
                            if (tf <
                                  intervalMin) {
                                intervalMin =
                                  tb >=
                                    intervalMin
                                    ? tb
                                    : intervalMin;
                                continue;
                            }
                            node =
                              offset +
                                offsetYFront3;
                            if (tb >
                                  intervalMax) {
                                intervalMax =
                                  tf <=
                                    intervalMax
                                    ? tf
                                    : intervalMax;
                                continue;
                            }
                            stack[stackPos].
                              node =
                              back;
                            stack[stackPos].
                              near =
                              tb >=
                                intervalMin
                                ? tb
                                : intervalMin;
                            stack[stackPos].
                              far =
                              intervalMax;
                            stackPos++;
                            intervalMax =
                              tf <=
                                intervalMax
                                ? tf
                                : intervalMax;
                            continue;
                        }
                    case 2 <<
                      30:
                        {
                            float tf =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetZFront]) -
                                 orgZ) *
                              invDirZ;
                            float tb =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetZBack]) -
                                 orgZ) *
                              invDirZ;
                            if (tf <
                                  intervalMin &&
                                  tb >
                                  intervalMax)
                                break pushloop;
                            int back =
                              offset +
                              offsetZBack3;
                            node =
                              back;
                            if (tf <
                                  intervalMin) {
                                intervalMin =
                                  tb >=
                                    intervalMin
                                    ? tb
                                    : intervalMin;
                                continue;
                            }
                            node =
                              offset +
                                offsetZFront3;
                            if (tb >
                                  intervalMax) {
                                intervalMax =
                                  tf <=
                                    intervalMax
                                    ? tf
                                    : intervalMax;
                                continue;
                            }
                            stack[stackPos].
                              node =
                              back;
                            stack[stackPos].
                              near =
                              tb >=
                                intervalMin
                                ? tb
                                : intervalMin;
                            stack[stackPos].
                              far =
                              intervalMax;
                            stackPos++;
                            intervalMax =
                              tf <=
                                intervalMax
                                ? tf
                                : intervalMax;
                            continue;
                        }
                    case 3 <<
                      30:
                        {
                            int n =
                              tree[node +
                                     1];
                            while (n >
                                     0) {
                                primitives.
                                  intersectPrimitive(
                                    r,
                                    objects[offset],
                                    state);
                                n--;
                                offset++;
                            }
                            break pushloop;
                        }
                    case 1 <<
                      29:
                        {
                            float tf =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetXFront]) -
                                 orgX) *
                              invDirX;
                            float tb =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetXBack]) -
                                 orgX) *
                              invDirX;
                            node =
                              offset;
                            intervalMin =
                              tf >=
                                intervalMin
                                ? tf
                                : intervalMin;
                            intervalMax =
                              tb <=
                                intervalMax
                                ? tb
                                : intervalMax;
                            if (intervalMin >
                                  intervalMax)
                                break pushloop;
                            continue;
                        }
                    case 3 <<
                      29:
                        {
                            float tf =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetYFront]) -
                                 orgY) *
                              invDirY;
                            float tb =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetYBack]) -
                                 orgY) *
                              invDirY;
                            node =
                              offset;
                            intervalMin =
                              tf >=
                                intervalMin
                                ? tf
                                : intervalMin;
                            intervalMax =
                              tb <=
                                intervalMax
                                ? tb
                                : intervalMax;
                            if (intervalMin >
                                  intervalMax)
                                break pushloop;
                            continue;
                        }
                    case 5 <<
                      29:
                        {
                            float tf =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetZFront]) -
                                 orgZ) *
                              invDirZ;
                            float tb =
                              (Float.
                                 intBitsToFloat(
                                   tree[node +
                                          offsetZBack]) -
                                 orgZ) *
                              invDirZ;
                            node =
                              offset;
                            intervalMin =
                              tf >=
                                intervalMin
                                ? tf
                                : intervalMin;
                            intervalMax =
                              tb <=
                                intervalMax
                                ? tb
                                : intervalMax;
                            if (intervalMin >
                                  intervalMax)
                                break pushloop;
                            continue;
                        }
                    default:
                        return;
                }
            }
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
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
