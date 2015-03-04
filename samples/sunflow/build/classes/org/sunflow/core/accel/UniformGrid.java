package org.sunflow.core.accel;
import org.sunflow.core.AccelerationStructure;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.MathUtils;
import org.sunflow.math.Vector3;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
import org.sunflow.util.IntArray;
public final class UniformGrid implements AccelerationStructure {
    private int nx;
    private int ny;
    private int nz;
    private PrimitiveList primitives;
    private BoundingBox bounds;
    private int[][] cells;
    private float voxelwx;
    private float voxelwy;
    private float voxelwz;
    private float invVoxelwx;
    private float invVoxelwy;
    private float invVoxelwz;
    public UniformGrid() { super();
                           nx = (ny = (nz = 0));
                           bounds = null;
                           cells = null;
                           voxelwx = (voxelwy = (voxelwz = 0));
                           invVoxelwx = (invVoxelwy = (invVoxelwz = 0)); }
    public void build(PrimitiveList primitives) { Timer t = new Timer();
                                                  t.start();
                                                  this.primitives = primitives;
                                                  int n = primitives.getNumPrimitives(
                                                                       );
                                                  bounds = primitives.
                                                             getWorldBounds(
                                                               null);
                                                  bounds.enlargeUlps(
                                                           );
                                                  Vector3 w = bounds.
                                                    getExtents(
                                                      );
                                                  double s = Math.
                                                    pow(
                                                      w.
                                                        x *
                                                        w.
                                                          y *
                                                        w.
                                                          z /
                                                        n,
                                                      1 /
                                                        3.0);
                                                  nx = MathUtils.
                                                         clamp(
                                                           (int)
                                                             (w.
                                                                x /
                                                                s +
                                                                0.5),
                                                           1,
                                                           128);
                                                  ny = MathUtils.
                                                         clamp(
                                                           (int)
                                                             (w.
                                                                y /
                                                                s +
                                                                0.5),
                                                           1,
                                                           128);
                                                  nz = MathUtils.
                                                         clamp(
                                                           (int)
                                                             (w.
                                                                z /
                                                                s +
                                                                0.5),
                                                           1,
                                                           128);
                                                  voxelwx = w.x /
                                                              nx;
                                                  voxelwy = w.y /
                                                              ny;
                                                  voxelwz = w.z /
                                                              nz;
                                                  invVoxelwx = 1 /
                                                                 voxelwx;
                                                  invVoxelwy = 1 /
                                                                 voxelwy;
                                                  invVoxelwz = 1 /
                                                                 voxelwz;
                                                  UI.printDetailed(
                                                       Module.
                                                         ACCEL,
                                                       "Creating grid: %dx%dx%d ...",
                                                       nx,
                                                       ny,
                                                       nz);
                                                  IntArray[] buildCells =
                                                    new IntArray[nx *
                                                                   ny *
                                                                   nz];
                                                  int[] imin =
                                                    new int[3];
                                                  int[] imax =
                                                    new int[3];
                                                  int numCellsPerObject =
                                                    0;
                                                  for (int i =
                                                         0;
                                                       i <
                                                         n;
                                                       i++) {
                                                      getGridIndex(
                                                        primitives.
                                                          getPrimitiveBound(
                                                            i,
                                                            0),
                                                        primitives.
                                                          getPrimitiveBound(
                                                            i,
                                                            2),
                                                        primitives.
                                                          getPrimitiveBound(
                                                            i,
                                                            4),
                                                        imin);
                                                      getGridIndex(
                                                        primitives.
                                                          getPrimitiveBound(
                                                            i,
                                                            1),
                                                        primitives.
                                                          getPrimitiveBound(
                                                            i,
                                                            3),
                                                        primitives.
                                                          getPrimitiveBound(
                                                            i,
                                                            5),
                                                        imax);
                                                      for (int ix =
                                                             imin[0];
                                                           ix <=
                                                             imax[0];
                                                           ix++) {
                                                          for (int iy =
                                                                 imin[1];
                                                               iy <=
                                                                 imax[1];
                                                               iy++) {
                                                              for (int iz =
                                                                     imin[2];
                                                                   iz <=
                                                                     imax[2];
                                                                   iz++) {
                                                                  int idx =
                                                                    ix +
                                                                    nx *
                                                                    iy +
                                                                    nx *
                                                                    ny *
                                                                    iz;
                                                                  if (buildCells[idx] ==
                                                                        null)
                                                                      buildCells[idx] =
                                                                        new IntArray(
                                                                          );
                                                                  buildCells[idx].
                                                                    add(
                                                                      i);
                                                                  numCellsPerObject++;
                                                              }
                                                          }
                                                      }
                                                  }
                                                  UI.
                                                    printDetailed(
                                                      Module.
                                                        ACCEL,
                                                      "Building cells ...");
                                                  int numEmpty =
                                                    0;
                                                  int numInFull =
                                                    0;
                                                  cells =
                                                    (new int[nx *
                                                               ny *
                                                               nz][]);
                                                  int i =
                                                    0;
                                                  for (IntArray cell
                                                        :
                                                        buildCells) {
                                                      if (cell !=
                                                            null) {
                                                          if (cell.
                                                                getSize(
                                                                  ) ==
                                                                0) {
                                                              numEmpty++;
                                                              cell =
                                                                null;
                                                          }
                                                          else {
                                                              cells[i] =
                                                                cell.
                                                                  trim(
                                                                    );
                                                              numInFull +=
                                                                cell.
                                                                  getSize(
                                                                    );
                                                          }
                                                      }
                                                      else
                                                          numEmpty++;
                                                      i++;
                                                  }
                                                  t.
                                                    end(
                                                      );
                                                  UI.
                                                    printDetailed(
                                                      Module.
                                                        ACCEL,
                                                      "Uniform grid statistics:");
                                                  UI.
                                                    printDetailed(
                                                      Module.
                                                        ACCEL,
                                                      "  * Grid cells:          %d",
                                                      cells.
                                                        length);
                                                  UI.
                                                    printDetailed(
                                                      Module.
                                                        ACCEL,
                                                      "  * Used cells:          %d",
                                                      cells.
                                                        length -
                                                        numEmpty);
                                                  UI.
                                                    printDetailed(
                                                      Module.
                                                        ACCEL,
                                                      "  * Empty cells:         %d",
                                                      numEmpty);
                                                  UI.
                                                    printDetailed(
                                                      Module.
                                                        ACCEL,
                                                      "  * Occupancy:           %.2f%%",
                                                      100.0 *
                                                        (cells.
                                                           length -
                                                           numEmpty) /
                                                        cells.
                                                          length);
                                                  UI.
                                                    printDetailed(
                                                      Module.
                                                        ACCEL,
                                                      "  * Objects/Cell:        %.2f",
                                                      (double)
                                                        numInFull /
                                                        (double)
                                                          cells.
                                                            length);
                                                  UI.
                                                    printDetailed(
                                                      Module.
                                                        ACCEL,
                                                      "  * Objects/Used Cell:   %.2f",
                                                      (double)
                                                        numInFull /
                                                        (double)
                                                          (cells.
                                                             length -
                                                             numEmpty));
                                                  UI.
                                                    printDetailed(
                                                      Module.
                                                        ACCEL,
                                                      "  * Cells/Object:        %.2f",
                                                      (double)
                                                        numCellsPerObject /
                                                        (double)
                                                          n);
                                                  UI.
                                                    printDetailed(
                                                      Module.
                                                        ACCEL,
                                                      "  * Build time:          %s",
                                                      t.
                                                        toString(
                                                          ));
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
        orgX +=
          intervalMin *
            dirX;
        orgY +=
          intervalMin *
            dirY;
        orgZ +=
          intervalMin *
            dirZ;
        int indxX;
        int indxY;
        int indxZ;
        int stepX;
        int stepY;
        int stepZ;
        int stopX;
        int stopY;
        int stopZ;
        float deltaX;
        float deltaY;
        float deltaZ;
        float tnextX;
        float tnextY;
        float tnextZ;
        indxX =
          (int)
            ((orgX -
                bounds.
                  getMinimum(
                    ).
                  x) *
               invVoxelwx);
        if (indxX <
              0)
            indxX =
              0;
        else
            if (indxX >=
                  nx)
                indxX =
                  nx -
                    1;
        if (Math.
              abs(
                dirX) <
              1.0E-6F) {
            stepX =
              0;
            stopX =
              indxX;
            deltaX =
              0;
            tnextX =
              Float.
                POSITIVE_INFINITY;
        }
        else
            if (dirX >
                  0) {
                stepX =
                  1;
                stopX =
                  nx;
                deltaX =
                  voxelwx *
                    invDirX;
                tnextX =
                  intervalMin +
                    ((indxX +
                        1) *
                       voxelwx +
                       bounds.
                         getMinimum(
                           ).
                         x -
                       orgX) *
                    invDirX;
            }
            else {
                stepX =
                  -1;
                stopX =
                  -1;
                deltaX =
                  -voxelwx *
                    invDirX;
                tnextX =
                  intervalMin +
                    (indxX *
                       voxelwx +
                       bounds.
                         getMinimum(
                           ).
                         x -
                       orgX) *
                    invDirX;
            }
        indxY =
          (int)
            ((orgY -
                bounds.
                  getMinimum(
                    ).
                  y) *
               invVoxelwy);
        if (indxY <
              0)
            indxY =
              0;
        else
            if (indxY >=
                  ny)
                indxY =
                  ny -
                    1;
        if (Math.
              abs(
                dirY) <
              1.0E-6F) {
            stepY =
              0;
            stopY =
              indxY;
            deltaY =
              0;
            tnextY =
              Float.
                POSITIVE_INFINITY;
        }
        else
            if (dirY >
                  0) {
                stepY =
                  1;
                stopY =
                  ny;
                deltaY =
                  voxelwy *
                    invDirY;
                tnextY =
                  intervalMin +
                    ((indxY +
                        1) *
                       voxelwy +
                       bounds.
                         getMinimum(
                           ).
                         y -
                       orgY) *
                    invDirY;
            }
            else {
                stepY =
                  -1;
                stopY =
                  -1;
                deltaY =
                  -voxelwy *
                    invDirY;
                tnextY =
                  intervalMin +
                    (indxY *
                       voxelwy +
                       bounds.
                         getMinimum(
                           ).
                         y -
                       orgY) *
                    invDirY;
            }
        indxZ =
          (int)
            ((orgZ -
                bounds.
                  getMinimum(
                    ).
                  z) *
               invVoxelwz);
        if (indxZ <
              0)
            indxZ =
              0;
        else
            if (indxZ >=
                  nz)
                indxZ =
                  nz -
                    1;
        if (Math.
              abs(
                dirZ) <
              1.0E-6F) {
            stepZ =
              0;
            stopZ =
              indxZ;
            deltaZ =
              0;
            tnextZ =
              Float.
                POSITIVE_INFINITY;
        }
        else
            if (dirZ >
                  0) {
                stepZ =
                  1;
                stopZ =
                  nz;
                deltaZ =
                  voxelwz *
                    invDirZ;
                tnextZ =
                  intervalMin +
                    ((indxZ +
                        1) *
                       voxelwz +
                       bounds.
                         getMinimum(
                           ).
                         z -
                       orgZ) *
                    invDirZ;
            }
            else {
                stepZ =
                  -1;
                stopZ =
                  -1;
                deltaZ =
                  -voxelwz *
                    invDirZ;
                tnextZ =
                  intervalMin +
                    (indxZ *
                       voxelwz +
                       bounds.
                         getMinimum(
                           ).
                         z -
                       orgZ) *
                    invDirZ;
            }
        int cellstepX =
          stepX;
        int cellstepY =
          stepY *
          nx;
        int cellstepZ =
          stepZ *
          ny *
          nx;
        int cell =
          indxX +
          indxY *
          nx +
          indxZ *
          ny *
          nx;
        for (;
             ;
             ) {
            if (tnextX <
                  tnextY &&
                  tnextX <
                  tnextZ) {
                if (cells[cell] !=
                      null) {
                    for (int i
                          :
                          cells[cell])
                        primitives.
                          intersectPrimitive(
                            r,
                            i,
                            state);
                    if (state.
                          hit(
                            ) &&
                          (r.
                             getMax(
                               ) <
                             tnextX &&
                             r.
                             getMax(
                               ) <
                             intervalMax))
                        return;
                }
                intervalMin =
                  tnextX;
                if (intervalMin >
                      intervalMax)
                    return;
                indxX +=
                  stepX;
                if (indxX ==
                      stopX)
                    return;
                tnextX +=
                  deltaX;
                cell +=
                  cellstepX;
            }
            else
                if (tnextY <
                      tnextZ) {
                    if (cells[cell] !=
                          null) {
                        for (int i
                              :
                              cells[cell])
                            primitives.
                              intersectPrimitive(
                                r,
                                i,
                                state);
                        if (state.
                              hit(
                                ) &&
                              (r.
                                 getMax(
                                   ) <
                                 tnextY &&
                                 r.
                                 getMax(
                                   ) <
                                 intervalMax))
                            return;
                    }
                    intervalMin =
                      tnextY;
                    if (intervalMin >
                          intervalMax)
                        return;
                    indxY +=
                      stepY;
                    if (indxY ==
                          stopY)
                        return;
                    tnextY +=
                      deltaY;
                    cell +=
                      cellstepY;
                }
                else {
                    if (cells[cell] !=
                          null) {
                        for (int i
                              :
                              cells[cell])
                            primitives.
                              intersectPrimitive(
                                r,
                                i,
                                state);
                        if (state.
                              hit(
                                ) &&
                              (r.
                                 getMax(
                                   ) <
                                 tnextZ &&
                                 r.
                                 getMax(
                                   ) <
                                 intervalMax))
                            return;
                    }
                    intervalMin =
                      tnextZ;
                    if (intervalMin >
                          intervalMax)
                        return;
                    indxZ +=
                      stepZ;
                    if (indxZ ==
                          stopZ)
                        return;
                    tnextZ +=
                      deltaZ;
                    cell +=
                      cellstepZ;
                }
        }
    }
    private void getGridIndex(float x, float y,
                              float z,
                              int[] i) { i[0] =
                                           MathUtils.
                                             clamp(
                                               (int)
                                                 ((x -
                                                     bounds.
                                                       getMinimum(
                                                         ).
                                                       x) *
                                                    invVoxelwx),
                                               0,
                                               nx -
                                                 1);
                                         i[1] =
                                           MathUtils.
                                             clamp(
                                               (int)
                                                 ((y -
                                                     bounds.
                                                       getMinimum(
                                                         ).
                                                       y) *
                                                    invVoxelwy),
                                               0,
                                               ny -
                                                 1);
                                         i[2] =
                                           MathUtils.
                                             clamp(
                                               (int)
                                                 ((z -
                                                     bounds.
                                                       getMinimum(
                                                         ).
                                                       z) *
                                                    invVoxelwz),
                                               0,
                                               nz -
                                                 1);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "dRgAAA==");
}
