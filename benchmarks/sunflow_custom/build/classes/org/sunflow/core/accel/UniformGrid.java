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
      1165002530000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
