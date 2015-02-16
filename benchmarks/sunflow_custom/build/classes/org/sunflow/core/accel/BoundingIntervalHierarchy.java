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
          1170053892000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YX2wUxxkfn/+dHcIdJhDHBdsYE8WQ3kL4E8COwTYmNrmA" +
                                                        "g20qLiJmvDtnL97b3ezO2oep00AUQaMK5Y9DoaJ+qED5RyCqitKqisRLm0Tp" +
                                                        "S6qqVR+aVH1p1JQHHppGTdv0m9m53bu9W3PkoSfN3NzM9/3m++b7M9/clZuo" +
                                                        "2rbQBtPQjk9oBk2QLE0c07Ym6HGT2Il9ya1D2LKJ0qdh2x6BuTG57Z3YF1+9" +
                                                        "OBmPoJoUWo513aCYqoZuHyS2oU0TJYli/my/RjI2RfHkMTyNJYeqmpRUbdqZ" +
                                                        "RHflsVLUnsyJIIEIEoggcRGkHp8KmO4mupPpYxxYp/bT6BlUkUQ1pszEo2hN" +
                                                        "IYiJLZwRMENcA0CIst+HQCnOnLVQq6e7q3ORwq9ukOZ/+FT8p5UolkIxVR9m" +
                                                        "4sggBIVNUmhJhmTGiWX3KApRUmiZTogyTCwVa+oslzuFGmx1QsfUsYh3SGzS" +
                                                        "MYnF9/RPbonMdLMcmRqWp15aJZqS+1Wd1vAE6LrS19XVcC+bBwXrVRDMSmOZ" +
                                                        "5FiqplRdoaglyOHp2P4YEABrbYbQScPbqkrHMIEaXNtpWJ+Qhqml6hNAWm04" +
                                                        "sAtFTaGg7KxNLE/hCTJGUWOQbshdAqo6fhCMhaIVQTKOBFZqClgpzz4393ed" +
                                                        "PaEP6BEus0JkjckfBabmANNBkiYW0WXiMi5ZnzyHV753JoIQEK8IELs07373" +
                                                        "1u4Hm2984NJ8qwTNgfFjRKZj8qXxpR+v6uvYUcnEiJqGrTLjF2jO3X9IrHRm" +
                                                        "TYi8lR4iW0zkFm8c/PXhZ98kn0dQ/SCqkQ3NyYAfLZONjKlqxHqU6MTClCiD" +
                                                        "qI7oSh9fH0S1ME6qOnFnD6TTNqGDqErjUzUG/w1HlAYIdkS1MFb1tJEbm5hO" +
                                                        "8nHWRAgth4YaoW1G7od/U2RIoza4u4RlrKu6IYHzEmzJkxKRjbFxON3JDLam" +
                                                        "bEl2bGpkJNvR05oxI9mWLBnWhPdbNiwCGDLRpF7D0RVwq0HmuNNYG1BBOUA8" +
                                                        "nmCOZ/7/t8yyU4jPVFSAgVYF04MGkTVgaAqxxuR5p7f/1tWxjyJeuIjzo6gT" +
                                                        "dk6InRNs5wTfORG6c3uvo2oKSy42qqjge9/DhHEdA8w6BQkCUueSjuEj+46e" +
                                                        "aasEjzRnqsAmjLQNjkJI2C8bfX4WGeS5UgZXbvzJk6cTX762y3VlKTzll+RG" +
                                                        "N87PnDz0vY0RFCnM3UxjmKpn7EMs43qZtT0Ys6VwY6c/++LauTnDj96Cy0Ak" +
                                                        "lWJOlhTagraxDJkokGZ9+PWt+PrYe3PtEVQFmQayK8UQDZC4moN7FCSHzlyi" +
                                                        "ZbpUg8Jpw8pgjS3lsmM9nbSMGX+GO81SPl4GRomyaGmAtkeED/9mq8tN1t/j" +
                                                        "OhmzckALnsj3/uLGhes/2rAjkp/zY3m36DChbgZZ5jvJiEUIzP/p/NArr948" +
                                                        "/ST3EKBYW2qDdtb3QT4Bk8GxPv/B03/89JNLv4t4XoWywHq/Dw5JRoNEx0ze" +
                                                        "PqpnDEVNq3hcI8wn/x1bt+n638/GXSNqMJPzgQdvD+DP39eLnv3oqX82c5gK" +
                                                        "mV1yvsI+mav3ch+5x7LwcSZH9uRvV194H/8YcjDkPVudJTyVIaEQEyrBLdTB" +
                                                        "+28H1jayrtUsWsvymUb+KwZbd4THzl52V+fF3L8OaOOn/vIl16goakpcUQH+" +
                                                        "lHTlYlNf9+ec33dfxt2SLU5OUNf4vA+9mflHpK3mVxFUm0JxWRRNh7DmMCdJ" +
                                                        "QaFg5yopKKwK1gsvffeG6/TCc1UwdPK2DQaOnxRhzKjZuD4QK3F2yquhbRGx" +
                                                        "siUYKxWID7Zzljber2PdA24CpKjWtNRpzCoyFIUScb+hEHtxSw1Zagbu2mlR" +
                                                        "DEhzDZ9OXfzsbTc7Bs0SICZn5l/4OnF2PpJXXq0tqnDyedwSi+t9t6v31/Cp" +
                                                        "gPZf1pi+bMK9Yhv6xD3f6l30psnCcc1iYvEt9v712twvX5877arRUFhd9MPJ" +
                                                        "vP37//wmcf7PH5a4siqhcuRJyY2AzYX2aYa2Vdhna4h9Bli3k6I62ChJ8LSb" +
                                                        "GnvCQVugbROg20JAHxOg9baTcV2xHNSHBerDIaj7c6gZVS8fdbtA3R6C+oSH" +
                                                        "irPloTK33yFQd4SgjgjUKJzAHmK6VdptMHcKzJ0hmN/JYYL+5WJ2CszOEMyU" +
                                                        "h4mz5WCyE+0SmF0hmEdyJ+q51MYyUB8RqI+EoB4tQt1UBmq3QO0OQZWLUB8q" +
                                                        "A3WXQN0VgpouQt1cBupugbo7BFUtQt1yG9RWaD0CtScEVROod/mo5m1gV0Hr" +
                                                        "FbC9IbCGgK0F2N5DA/mnmi19JUTYsIuiGpu/1vNvcF7YrA57T/IseunU/IJy" +
                                                        "4PKmiCgM+gFIPPN9nCoGU1B2P86fz/4N/MIbb71LP96w003G68PvoiDj+6f+" +
                                                        "1jTSPXn0DortloBCQcg3Hr/y4aP3yy9HUKV3kRf9I1DI1Fl4fddbhDqWPlJw" +
                                                        "iTcXFrxN0EaFMUeDxuQGY137IjXYyUXWnmPdM+BdjqnApTio68Ig3azrdY28" +
                                                        "h6KqaUNViss4PjFbKPB90A4LgQ9/I4F/sMjaWdZ9H4LMFTjnus+XJ1sHtCNC" +
                                                        "tiNlyxbx/Z+HSA8nnV9EynOse8mTEuI2fQdSshM8IaQ88Y1O8OIiawusuwCy" +
                                                        "QX2nU/46DpENKr96/wnNl1dQtPFOn+EQ1I1F/wi6/2LJVxdi0XsXRv/AH5Pe" +
                                                        "P011SRRNO5qWX+nmjWtMi6RVrkydW/ea/OsyPG9KC0dRNf/mOlxyqV+nKB6k" +
                                                        "BkdnX/lkb0Fw5JFBxhSjfKKrUOoBERteM3MHFefPKVbvJ9zCJYsKcqYZzKBr" +
                                                        "C9IZ/681l3oc99/WMfnawr79J25tu8zzWLWs4dlZhhJNolr3Qe2lrzWhaDms" +
                                                        "moGOr5a+U7cul5OXsq5BvKIDsrWUfnX2Z0zK34mzP7/3Z12vLXzCX7v/A52v" +
                                                        "5EYEFwAA");
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
      1170053892000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
