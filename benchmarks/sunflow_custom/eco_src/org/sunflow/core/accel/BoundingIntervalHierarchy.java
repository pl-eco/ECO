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
                                                  int n = primitives.getNumPrimitives();
                                                  UI.printDetailed(Module.
                                                                     ACCEL,
                                                                   "Getting bounding box ...");
                                                  bounds = primitives.getWorldBounds(
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
                                                  this.buildHierarchy(
                                                         tempTree,
                                                         objects,
                                                         stats);
                                                  t.end();
                                                  UI.printDetailed(
                                                       Module.
                                                         ACCEL,
                                                       "Trimming tree ...");
                                                  tree = tempTree.
                                                           trim();
                                                  stats.printStats();
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
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1170053892000L;
        final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVZe2wUxxkf3/l5duUHYB4BGxsnKY/cQWMI+Aj4gYkPDuz6" +
                                                       "ldiEXMa7c/bC3u5m\nd+58NjQNqYRp6COordRKhdAIFZImIRWtSNQkJSU0aV" +
                                                       "BV0qpURQpthdRWalOpqpRStX/0m9m927u9\n20t8WJrZuZ1vvt/3zffYb8Yv" +
                                                       "fYTKDB0tFww/ndGI4e8ZGsC6QcQeGRvGMLyKCFfKqgbO7lZUDyoJ\nI48kUl" +
                                                       "QbFoyAiCkOSGIgtCOY1NFaTZVnJmWV+kmS+g/IGy1+u8Ibcxg+fOpiw5Ezpc" +
                                                       "0eVBZGtVhR\nVIqppCq9MokZFNWFD+AEDsSpJAfCkkGDYfQZosRjPapiUKxQ" +
                                                       "4wn0JPKGUbkmMJ4UtYRT4AEAD2hY\nx7EAhw8McFjgsEAnFEsKEbvScLByXf" +
                                                       "ZKENtaN5hLDUwq2eQoqMMlAK1XprU2tc1RVfOeG910+PQL\nXlQ7jmolZYgx" +
                                                       "E0ATCnjjqCZGYhNEN7pEkYjjqF4hRBwiuoRlaZajjqMGQ5pUMI3rxBgkhion" +
                                                       "GGGD\nEdeIzjFTL8OoRmA66XGBqnp6j6ISkcXUr7KojCdB7UZbbVPdnew9KO" +
                                                       "iTQDA9igWSWlJ6UFLA4s3O\nFWkd23YDASytiBE6paahShUML1CDaUsZK5OB" +
                                                       "IapLyiSQlqlxQKFomStTttcaFg7iSRKhaImTbsCc\nAqoqvhFsCUWLnGScE1" +
                                                       "hpmcNKGfZZ2/jxsXPffauT+3apSASZye+DRU2ORYMkSnSiCMRceDvu/2Zo\n" +
                                                       "LL7cgxAQL3IQmzRdd18cCf/1p80mzV15aPonDhCBRoS9J5oHDz2kIi8To1JT" +
                                                       "DYkZP0tzHg4D1kww\nqUHUNqY5skl/avLS4M/HnnqR/M2DfCFULqhyPAZ+VC" +
                                                       "+oMU2Sif4QUYiOKRFDqIooYg+fD6EKGIfB\n5c23/dGoQWgIlcr8VbnKf8MW" +
                                                       "RYEF26IqGEtKVE2NNUyn+DipIYQWQENLoN2PzD/+pKjHHzDiSlRW\npwOGLg" +
                                                       "RUfTL9W1B1EsCCQORAtxpXRHCVEHPGBJb7JBBYF6Zm/MyZNIoigSk1xqixIi" +
                                                       "lqYFKC8BXU\n+0SSYM87h0gyTRqmS0pYanSGuAzR0afKItEjwtlb7x/u3f3l" +
                                                       "Y6b7MJe39oCiICD7LWQ/Q/ZzZL8r\nclt3XJJFliAMVFLCsRcyYUzjgmkOQp" +
                                                       "BDOqxZPbR/1+PHWr3gVdp0KewrI20FxS0JewW1x84EIZ40\nBXDHJc/vm/Pf" +
                                                       "PrvddMeAe8LOu7r62stXT/+rZo0HefJnU6Y55HMfYzPAUnA6S7Y54y8f/388" +
                                                       "s+fC\n9asfftaORIrachJE7koW4K1OG+mqQERImTb7M0trvQ+j0RMeVApZAz" +
                                                       "Illx+SUJMTIyvQg6mkyXSp\nCKPqqKrHsMymUpnOR6d0ddp+w52njo9ZGFQy" +
                                                       "z2+AtsMKBf5ks4s01jeazsas7dCCJ+XbXypf/7s3\nqq/wbUnl79qML+QQoW" +
                                                       "Y2qLedZVgnBN5/+O2Bb3zro7l93FNMV0FJoLzHpoTolyEDMfu1jSgxVZSi\n" +
                                                       "Ep6QCXO0/9XeveHHf/9anWkRGd6kDLrukxnY75d2o6euPvbvJs6mRGBfH1t6" +
                                                       "m8xUYoHNuUvX8QyT\nI3nk1yu+8y4+CckREpIhzRKeY5ClEBPKz7d7Ne/vc8" +
                                                       "ytZ10r8F7n4vF5vvUR4fCLk63xJ37xOpe6\nGmcWDZm7vwdrQdPgrFvFdnex" +
                                                       "M2j7sDEFdO2X9j5aJ1/6L3AcB46QDAyjX4c0ksyynUVdVnHj7cuN\nj3/gRZ" +
                                                       "6dyCerWNyJudujKvA3YkxBBkpq2zu5S9VNMy+r4yojvgnLrA1IZvyqBeFWu0" +
                                                       "f9TlYp2AET\nmVh3Lvx+/0m+Aa7xnudD6eAz+9bIqdu/pDc5Hzvw2OqWZG56" +
                                                       "herKXrv5eqK+/NXnYh5UMY7qBKv+\nG8VynLn3OJQrRqoohBoxaz679DC/s8" +
                                                       "F0YlnuDPoMWGfI22kdxoyajWscUc73fgW0divK251RXoL4\nYBtf0sb7e630" +
                                                       "TVGFpksJzGpCVAmV7l5VBOfQ0ZLMslyXYvB5T/C0dOto65vvjTw3Z6byAkbN" +
                                                       "WhUR\nvviHPx70fv3tCXOd03IO4hNNZ/584dbgQjP+zTpwVU4plrnGrAX51t" +
                                                       "RqLBZaCiFw6nfWtrz05OBN\nS6KG7IqmF/biLzOXyb1bv/qnPJ9YL1SrZvJk" +
                                                       "/QbWbTcdvt01MLZkm6wJ2kbLZBtdTNbPuk6KqkCa\nMMEJM8/3OZAH5oncDG" +
                                                       "2ThbzJBXnYQvYZ8ZjpwnmhR4qAfsCCfsAFeiwFHZOUAtDjRUBvtqA3u0A/\n" +
                                                       "lobGyQLQkXlCs+jcYkFvcYEWLOhK2PAdRDNLWiewWARwhwXc4QI8lQKG7XYF" +
                                                       "looADlrAQRfgWBoY\nJ12BlSKsvNUC3uoCrKesnI6q9fmgjSKgH7SgH3SBns" +
                                                       "6B3pAPOlkE9DYLepsL9OEc6M/lg/5CEdDb\nLejtLtBHcqDvzwf9dBHQnRZ0" +
                                                       "pwv00Rzo9nzQc/OEXgmty4LucoE+bkFX29BaPuyvzBN7ObRuC7vb\nBftZC7" +
                                                       "sCsLtH+/Ja+kQB3GRureBh426Kyg1+kZRZ3vHSfoXbVQf/2M498s+ao/id/R" +
                                                       "6rNN4FjKwb\nKJtPKWOTdZrcw2927LLsmRd+cJF+sLbD/Gavca8+nAvXdJye" +
                                                       "be44f7yIM2SzQzEn6/rEXZ/3Tknv\nefjlk1nl5VxaZS8KZtd2PpAnrivDWR" +
                                                       "Xeyuxz3DJoI5bVR5xW55Zl3T0FTiNnCsx9n3XfA1+NayIU\ngyFFsQyzQzMN" +
                                                       "HaKoNKFKou0+z3+S26Z9g/04ma3NUmhjljZjRWnzwwJzF1j3CgS9qU0qAM7Z" +
                                                       "wp+/\nE+FXQ9tvCb//UwvvsaOIR2IfJ/1JATXeZN1raTUgh0Qdarx+pzY4ZK" +
                                                       "lxqCgbXCkw9y7rfgbCw/lC\nofxuySH85U8rPBxNfPYNFZ9aTNH6+d5ysVNN" +
                                                       "zqW5edErhG8cevTj8G//w+9o0pex1WFUGY3LcuYx\nLGNcrukkKnFtq81Dmc" +
                                                       "Yf1yhqzC8cRWX8yXX4lUn9G4rqnNQQbOyRSXYdgjODDFK7Ncok+j2cTICI\n" +
                                                       "DW9oqY2q4yd8dhj1mzVtdupmdx+rshIp/y9GKtnFzf9jRIRHXt63Mnl8+Fme" +
                                                       "QcsEGc/OMja+MKow\nb6bSCbPFlVuK1zX06vnRN17Zkvoa8CuMhUn7A5Xltx" +
                                                       "vM2QLeAkk6/71Qb0yj/CZn9rXFP9p69tRN\nD7+Q+j99vDjZfBoAAA==");
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
          z };
        float[] nodeBox =
          { bounds.
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
          z };
        this.
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
            this.
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
                      updateBVH2();
                    int nextIndex =
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
                    stats.
                      updateInner();
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
                    this.
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
                    this.
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
                        this.
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
                        if (wasLeft) {
                            stats.
                              updateInner();
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
                              updateInner();
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
          getSize();
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
          updateInner();
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
            this.
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
            this.
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
          getStack();
        int stackTop =
          state.
          getStackTop();
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170053892000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAL1Zf4wU1R1/u3vcb7lf/BY4OQ6pcLfLIfeLM8EDDjhY4HoH" +
       "qId4zs283RuYnRln\n3h7LSaiGVKikLaRtapMKSExA1GpjG2pjLURtrcRGTa" +
       "upqdbGtDVpbWraWGz9o9/vezM7s7O7B0jK\nJvPmx3vv+74/P+/7ffvUx2SK" +
       "bZG5sh1l+0xqR9cMDUiWTZU1mmTb2+DTiPzKlIqB05t0I0xCcRJW\nFUZq4r" +
       "IdUyQmxVQl1r+2J2ORpaah7UtqBovSDIvu1todehvj7XkE7zh+rv7Bx0saw2" +
       "RKnNRIum4w\niamG3qfRlM1IbXy3NC7F0kzVYnHVZj1xcgPV06k1hm4zSWf2" +
       "feQAicRJqSkjTUYWxN3FY7B4zJQs\nKRXjy8cG+LJAocGiTFJ1qvRml4OZLb" +
       "kzgW1n3mD+aCBSjp07QBzOAUh9U1ZqIW2eqGbkzI6O/Sef\niJCaYVKj6kNI" +
       "TAZJGKw3TKpTNDVKLbtXUagyTOp0SpUhaqmSpk7wVYdJva0mdYmlLWoPUtvQ" +
       "xnFg\nvZ02qcXXdD/GSbWMMllpmRlWVkcJlWqK+zYloUlJEHuGJ7YQdx1+Bw" +
       "ErVWDMSkgydaeU7FF1sHhj\ncEZWxuZNMACmlqUoGzOyS5XoEnwg9cKWmqQn" +
       "Y0PMUvUkDJ1ipGEVRuYUJYq6NiV5j5SkI4zMCo4b\nEF0wqoIrAqcwMj04jF" +
       "MCK80JWMlnn6UzPj185vsv3s59u0Shsob8V8Kk+YFJgzRBLarLVEy8lI5+\n" +
       "u/+u9NwwITB4emCwGNO76Nz2+Ec/bxRjbiwwZuvobiqzEXnLscbB+9cbJIJs" +
       "lJuGraLxcyTn4TDg\n9PRkTIjaGVmK2Bl1O88P/uKuB87Sv4ZJZT8plQ0tnQ" +
       "I/qpONlKlq1FpPdWpJjCr9pILqyhre30/K\n4DkOLi++bk0kbMr6SYnGP5Ua" +
       "/B1UlAASqKIKeFb1hOE+mxIb488ZkxBSBhdZD9dUIn78zsiaaMxO\n6wnN2B" +
       "uzLTlmWMnsu2xYNCbJMtViq420roCr9KMzjkvaBhUYtuSxfVF0JpORkdiYkc" +
       "LRkq7qRiyp\nQvjKRqtCx/F+7UtkUJL6vaEQQmMwxDWIjg2GplBrRD794Wv7" +
       "+zZ97bBwH3R5RweMLIOVo87KUVw5\nyleOFl2ZhEJ8wWnIgbAo2GMPRDZgYP" +
       "UtQ7s23nu4KQKuZO4tAWXi0CaQ1mGrTzbWeOHfz5FSBh+c\ndWrnoeil06uE" +
       "D8aKo3TB2VVvPH3x5D+rl4RJuDCEorgA4pVIZgBxNwuNzcGgK0T/7w9vfu7t" +
       "i+99\nyQs/RprzUCF/JkZ1U9AwliFTBXDSI//47JrIHWTHsTApAagAeOT8A/" +
       "LMD66RE909LlKiLGVxUpUw\nrJSkYZcLb5VszDL2el+4x9Ty5wYwThW6ezNc" +
       "0x3/53fsnW5iO0N4GFo7IAVH4ksHS5e980LVK1wt\nLmjX+LbFIcoEBNR5zr" +
       "LNohS+v/fIwLe+8/GhndxTHFdhsFemRzVVzsCUm70pEPsa4A8asnm7njIU\n" +
       "NaFKoxpFj/u8ZlHbj//2jVphGg2+uJZtuTwB7/vs1eSBi/f8ez4nE5Jx7/HE" +
       "8IYJaRo8yr2WJe1D\nPjIPvjXve7+UHgVoBDiy1QnKEYZwyQjXY4zrfQlvo4" +
       "G+NmyagHZLEdcvsNOPyPvPJpvS9/3qec51\nleRPGfxm2CyZPcLy2CxE7c4M" +
       "Ru8GyR6DcSvOb7m7Vjv/X6A4DBQBCmx7qwUgkskxojN6Stm7F16a\nce+bER" +
       "JeRyo1Q1LWSdz/SQU4HrXHAH8y5qrbuW/V7i3HlotMuBLmOArI+N4wzbuleP" +
       "ivwzzBi5yR\n0ZYz8de2PsoVUDTwC2yTAToTL24/ful19j6n40Ugzl6QyQdX" +
       "yK28uV1vj9eVPnsiFSZlw6RWdrK/\nHZKWRj8fhmTFdlNCyBBz+nMTD7HL9m" +
       "QRZm4w+n3LBmPfA3V4xtH4XB0I92rU9ky4apxwrwmGe4jw\nh1V8SjNvF2eD" +
       "s8y01HEJM0JYAqIYYcGXkPNQQKh54ujahsHunQc5GldAjijZWzzuIDPHpxCo" +
       "dVFx\nO2eJjciLd537x4UX6WLukuWqDVrotZIF8iXfnE+ks3TzO4njHFFLRi" +
       "Vb6COYaObnkTnpIVffVBPj\nZZa/8rDUFGQw4xyEP3yo6Wevbj9xSGxck3hu" +
       "zqwR+St/+GBP5JsXRsW8oHsGBh+b//ifn/twcJoA\nOZHqLszLNv1zRLrLBa" +
       "jhAiyYbAU++uWlC546MPi+w1F9btLWB4XNX/a9RBff9vU/FsgiIqpT7yCu\n" +
       "hZyNH99Xmq5PDeX7VJiBE6u6xJPZW8CtSjWqJ0WCthmbDWYmSzUspvD3mczB" +
       "YIwbyJMNnSKcu30i\nNVGNaLZGgc5MHn8WmZeTmGzmpvcC++EnnjzH3ly6Ui" +
       "hkSXHTBicuWXlyonHlM0e+QDrSGDBTkHTd\n+I1fjoypr4Z58SJwIq/oyZ3U" +
       "k4sOlcBP2tK35WDETSa/bcDm5km2KWWSvgQ2o2BSGe0hzAc6biy8\nDfelTM" +
       "Y3zomfzPzRbaePv49aNjOQfFdxxGjr7mzvaof59RB5WMNHVcVB7bVvrRs9m9" +
       "DPKlwJlTyi\ne3GOI2EF/+KDnIhh2lik+E4DHErNW00bk7QbfIv0r93/7Mbq" +
       "8seOPMTpO3hV4St4nPeyccna4o8C\nwfnyzo5bu9sYUf/P2f/K9q7OlvbO1g" +
       "5Yq2Lbhv6hKIIyMrPbAegDUHvlaw9ldvCf1HPXnurFEsa5\nvxOkKhns610r" +
       "8kBsl2Nzu4im9qJb+8rcTWeO2+3eC2w6+7DphW3G4PugzSn0CcfcfBlwOVAA" +
       "XPB5\nh0AWbO/Iosr+fFTB17uw2ZkPE/h+j2Bj/+Xi46uT9B3C5iA2ScEFtm" +
       "rA4zv41D1+X7q1+3r4Unt3\nS0dba0cXI9XclxwjIDeHPZvgazrgCxNX6QuL" +
       "4KpzfKGuiC8cdXyh0nS3KdsF9/l5BWt2K4s72b+P\nuWNXyVyj8HvX/wsy91" +
       "2HudJR1GmWsbl+xlISG8vW0KuNTICtR66SrdlwNThsNRRh67jDVnlKyqBK\n" +
       "7KzD+xY+McnCGc+75/h8H0S7OU/nvehUeD4DcDjEq16oaDHPmFfsEIvnGIfu" +
       "/KT6IenlXWEnJrYC\ndDHDbNXoONVyqgAedbmFasxRhKuQ/EK1UHiGckW5jP" +
       "twOk9PEsU/xOYs7HKjadVJJPsdPNkCWDlu\nqIqn7ScvZ2a3AgqIy20+BNdR" +
       "R9yjRcXtDfAa4RQjrriz/eLyTRgAgCfKOfBqmmYuDnUGdt64IUta\n/9rHLl" +
       "S9dSzdsdG1Xwqb89cBn9pWrGhpB3xaAVgEfqTKHiD0XO0JVvNqNB0ecdtciB" +
       "cmMfer2DzP\nyFRu7iwJ/PqcZ+efXoudB+E65dj51BXbuYRTLLm8nbMoIBpO" +
       "8Y1JJP4NNq8D9MoWhWJvi6HQgLS/\nvhZpT8B1xpH2zBVLW8EpVnhiXKN/dy" +
       "/j3Rew+eB6uG9HV0tHZ2vXsoD79jnQ4YMQKOsNieUx3OYx\n/NF1YrhreWt3" +
       "JzCctFQFtjC/Qv+Ux99yj7+PGdl9Pfjr7m5ta0NA0MFHnT021zuuGRp+P0mg" +
       "/Aub\n3zH8b2dUUcfVvDh59wvGCd/sumHPSoswEfcr2uwCRfK0PPkHncCAzq" +
       "a8Tq4OW1RoqAPKF/q8uA5C\nPHH+DHSgulMDOvjPleoAqv/ZRc2CdeSsvP8w" +
       "xf9ucvzd++/+NP7bz8RZj/vfWBXUa4m0pvnPxXzP\npaZFEyqXr0qcknHHDl" +
       "UwMqOw00Bk8jtyHSoXo6sZqQ2OhiQAb/5hNRApvmHgsc6Tf1A9gzrVwtIg\n" +
       "1GC6Nqr1KjNxOpjJURrqZWHOuQT/U9k9O0iLv5VH5Duf3nlT5si2o/xAAupz" +
       "aWICyVRCDSv+M8ie\nPywoSs2l9QZ59pkdL/yg200B+JnyNF+Cm+PVy0VvcS" +
       "fADtX8H9rLCXjgHwAA");
}
