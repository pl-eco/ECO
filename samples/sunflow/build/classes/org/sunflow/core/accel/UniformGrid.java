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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
