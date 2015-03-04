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
          1425485134000L;
        public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YXWxUxxUer//WDmGNCcRxwTbGRDGkeyH8BLBjsI2JTTbg" +
                                                        "YEPFRsSM7521L9y99+beWXsxdRqIImhUofw4FCrqhwqUPwJRVZRWVSRe2iRK" +
                                                        "X1JVrfrQpOpLo6Y88NA0atqmZ3723t27e82Sh640s7Mz53xzzpyfObNXbqJq" +
                                                        "10HrbMs4PmFYNE6yNH7U2Bynx23ixvckNg9jxyVav4FddxTmxtT2d2JffPXi" +
                                                        "ZEME1STRUmyaFsVUt0x3P3EtY4poCRTzZwcMknYpakgcxVNYyVDdUBK6S7sS" +
                                                        "6K48Voo6EjkRFBBBAREULoLS61MB093EzKT7GQc2qfs0egZVJFCNrTLxKFpV" +
                                                        "CGJjB6clzDDXABCi7PdBUIozZx3U5ukudC5S+NV1ytwPn2r4aSWKJVFMN0eY" +
                                                        "OCoIQWGTJFqUJulx4ri9mka0JFpiEqKNEEfHhj7D5U6iRlefMDHNOMQ7JDaZ" +
                                                        "sYnD9/RPbpHKdHMyKrUcT72UTgwt96s6ZeAJ0HW5r6vQcDebBwXrdRDMSWGV" +
                                                        "5FiqjummRlFrkMPTseMxIADW2jShk5a3VZWJYQI1CtsZ2JxQRqijmxNAWm1l" +
                                                        "YBeKmkNB2VnbWD2GJ8gYRU1BumGxBFR1/CAYC0XLgmQcCazUHLBSnn1u7u0+" +
                                                        "e8IcNCNcZo2oBpM/CkwtAab9JEUcYqpEMC5amziHl793JoIQEC8LEAuad797" +
                                                        "a+eDLTc+EDTfKkGzb/woUemYeml88ccr+ju3VTIxorbl6sz4BZpz9x+WK11Z" +
                                                        "GyJvuYfIFuO5xRv7f33o2TfJ5xFUP4RqVMvIpMGPlqhW2tYN4jxKTOJgSrQh" +
                                                        "VEdMrZ+vD6FaGCd0k4jZfamUS+gQqjL4VI3Ff8MRpQCCHVEtjHUzZeXGNqaT" +
                                                        "fJy1EUJLoaEmaBuR+PBvijRl0koTBavY1E1LAd8l2FEnFaJaiovTtgFWczNm" +
                                                        "yrCmFddRFcuZ8H6rlsM4VWIofVbG1MCXhpi3TmFjUAeNAOd4nHmb/X/aJ8v0" +
                                                        "bZiuqABTrAgmAgNiaNAyNOKMqXOZvoFbV8c+iniBIU+Koi7YOS53jrOd43zn" +
                                                        "eOjOHX0Z3dBYGnFRRQXf+x4mjHABMOAxSAWQJBd1jhzec+RMeyX4nj1dBafP" +
                                                        "SNvhAKSEA6rV7+eLIZ4VVXDapp88eTr+5Ws7hNMq4cm9JDe6cX765MHvrY+g" +
                                                        "SGGWZhrDVD1jH2a51cuhHcHoLIUbO/3ZF9fOzVp+nBakfZk+ijlZ+LcHbeNY" +
                                                        "KtEgofrwa9vw9bH3ZjsiqApyCuRRisHvIUW1BPcoSANduZTKdKkGhVOWk8YG" +
                                                        "W8rlwXo66VjT/gx3msV8vASMEmVx0QhtlwwU/s1Wl9qsv0c4GbNyQAuesnf/" +
                                                        "4saF6z9aty2Sn91jefflCKEiVyzxnWTUIQTm/3R++JVXb55+knsIUKwutUEH" +
                                                        "6/shc4DJ4Fif/+DpP376yaXfRTyvQllgvd8Hh3RiQEpjJu84YKYtTU/peNwg" +
                                                        "zCf/HVuz4frfzzYIIxowk/OBB28P4M/f14ee/eipf7ZwmAqVXWe+wj6Z0Hup" +
                                                        "j9zrOPg4kyN78rcrL7yPfwzZFjKcq88QnrSQVIgJFecW6uT9twNr61nXZhet" +
                                                        "ZflME/8Vg607w2NnN7uV82LuX/uM8VN/+ZJrVBQ1JS6jAH9SuXKxub/nc87v" +
                                                        "uy/jbs0WJyeoYHzeh95M/yPSXvOrCKpNogZVlkcHsZFhTpKEksDN1UxQQhWs" +
                                                        "F17v4i7r8sJzRTB08rYNBo6fFGHMqNm4PhArDeyUV0LbJGNlUzBWKhAfbOUs" +
                                                        "7bxfw7oHRAKkqNZ29CnMai8UhWJwr6URd2FLDTt6Gm7VKXntK7ONnx67+Nnb" +
                                                        "IjsGzRIgJmfmXvg6fnYukldIrS6qZfJ5RDHF9b5b6P01fCqg/Zc1pi+bEJdp" +
                                                        "Y7+80du8K922WTiuWkgsvsXuv16b/eXrs6eFGo2FdcQAnMzbv//Pb+Ln//xh" +
                                                        "iSurEmpEnpREBGwstE8LtM3SPptD7DPIuu0U1cFGCYKnRGrsDQdthbZFgm4J" +
                                                        "AX1Mgta7mbRwxXJQH5aoD4eg7s2hpnWzfNStEnVrCOoTHirOlofK3H6bRN0W" +
                                                        "gjoqUaNwAruILeqx22Bul5jbQzC/k8ME/cvF7JKYXSGYSQ8TZ8vBZCfaLTG7" +
                                                        "QzAP507Uc6n1ZaA+IlEfCUE9UoS6oQzUHonaE4KqFqE+VAbqDom6IwQ1VYS6" +
                                                        "sQzUnRJ1ZwiqXoS66TaobdB6JWpvCKohUe/yUe3bwK6A1idh+0JgLQlbC7B9" +
                                                        "BwfzTzVb+kqIsGE3RTUuf5fn3+C8sFkZ9nLkWfTSqbl5bd/lDRFZGAwAkHzQ" +
                                                        "+zhVDKag7H6cP5T9G/iFN956l368brtIxmvD76Ig4/un/tY82jN55A6K7daA" +
                                                        "QkHINx6/8uGj96svR1Cld5EXvf0LmboKr+96h9CMY44WXOIthQVvM7QD0pgH" +
                                                        "gsbkBmNdxwI12MkF1p5j3TPgXRlbg0txyDSlQXpY1yeMvIuiqilL14rLOD4x" +
                                                        "UyjwfdAOSYEPfSOBf7DA2lnWfR+CTAicc93ny5OtE9phKdvhsmWL+P7PQ6SX" +
                                                        "k84tIOU51r3kSQlxm7oDKdkJnpBSnvhGJ3hxgbV51l0A2aC+Myl/HYfIBpVf" +
                                                        "vf+E5svLKFp/p89wCOqmov/+xP9V6tX5WPTe+QN/4I9J7z+lugSKpjKGkV/p" +
                                                        "5o1rbIekdK5Mnah7bf51GZ43pYWjqJp/cx0uCerXKWoIUoOjs698srcgOPLI" +
                                                        "IGPKUT7RVSj1gIgNr9m5g2rgzylW78dF4ZJFBTnTDmbQ1QXpjP+rmks9GfG/" +
                                                        "6ph6bX7P3hO3tlzmeaxaNfDMDEOJJlCteFB76WtVKFoOq2aw86vF79StyeXk" +
                                                        "xaxrlK/ogGytpV+dA2mb8nfizM/v/Vn3a/Of8Nfu/wAbzvti7hYAAA==");
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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "K3VdbsVrxKnF+RIR5I3Fyf8Avmg+I/wXAAA=");
}
