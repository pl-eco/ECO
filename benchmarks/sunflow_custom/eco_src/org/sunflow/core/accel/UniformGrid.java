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
final public class UniformGrid implements AccelerationStructure {
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
                                                  int n = primitives.getNumPrimitives();
                                                  bounds = primitives.getWorldBounds(
                                                                        null);
                                                  bounds.enlargeUlps();
                                                  Vector3 w = bounds.
                                                    getExtents();
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
                                                      this.
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
                                                      this.
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
                                                                getSize() ==
                                                                0) {
                                                              numEmpty++;
                                                              cell =
                                                                null;
                                                          }
                                                          else {
                                                              cells[i] =
                                                                cell.
                                                                  trim();
                                                              numInFull +=
                                                                cell.
                                                                  getSize();
                                                          }
                                                      }
                                                      else
                                                          numEmpty++;
                                                      i++;
                                                  }
                                                  t.
                                                    end();
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
                                                        toString());
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
                  getMinimum().
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
                         getMinimum().
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
                         getMinimum().
                         x -
                       orgX) *
                    invDirX;
            }
        indxY =
          (int)
            ((orgY -
                bounds.
                  getMinimum().
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
                         getMinimum().
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
                         getMinimum().
                         y -
                       orgY) *
                    invDirY;
            }
        indxZ =
          (int)
            ((orgZ -
                bounds.
                  getMinimum().
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
                         getMinimum().
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
                         getMinimum().
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
                          hit() &&
                          (r.
                             getMax() <
                             tnextX &&
                             r.
                             getMax() <
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
                              hit() &&
                              (r.
                                 getMax() <
                                 tnextY &&
                                 r.
                                 getMax() <
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
                              hit() &&
                              (r.
                                 getMax() <
                                 tnextZ &&
                                 r.
                                 getMax() <
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
                                                       getMinimum().
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
                                                       getMinimum().
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
                                                       getMinimum().
                                                       z) *
                                                    invVoxelwz),
                                               0,
                                               nz -
                                                 1);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1165002530000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVZC2wUxxmee9jGD/ATQ3gZGwMFzB0vA8aRwNgGDg4wNnYS" +
       "O9RZ786dF/Z2l929\n89mhNFEkIKFNQUmlVgqPVFSAmwQkUpFIKQXlHRQ1QQ" +
       "qpIoW2okojtYkatU2pUrX9Z2b3dm/vzsSo\nPmlnZ3dm/vf/zzd7L3yB8nQN" +
       "zeL1gDGsYj3Q2tXBaToWWiVO13fDq37+zbzCjrPbZMWLPGHkFQUD\nlYZ5PS" +
       "hwBhcUhWCorTmpoSWqIg1HJcUI4KQR2Cs1mvS2hhszCD5w8nLF42f8NV6UF0" +
       "alnCwrBmeI\nitwu4ZhuoLLwXi7BBeOGKAXDom40h9FkLMdjrYqsG5xs6PvR" +
       "QeQLo3yVJzQNVBu2mAeBeVDlNC4W\npOyDHZQtUKjUsMGJMhZaUuxgZUP6Sh" +
       "DbXNeZORuITCKDPaAOlQC0npvSmmmboarqO9ez+sDp8z5U\n2otKRbmLEONB" +
       "EwP49aKSGI4NYE1vEQQs9KJyGWOhC2siJ4kjlGsvqtDFqMwZcQ3rnVhXpASZ" +
       "WKHH\nVaxRntbLMCrhiU5anDcULWWjiIglwXrKi0hcFNSuttVm6m4i70HBIh" +
       "EE0yIcj60l/n2iDB6vca9I\n6Vi/DSbA0oIYNgaVFCu/zMELVMF8KXFyNNhl" +
       "aKIchal5Shy4GGhGTqLE1irH7+OiuN9A093zOtgQ\nzCqkhiBLDDTVPY1SAi" +
       "/NcHnJ4Z8l1V8fOffclQ00tv0C5iUifxEsmuNa1IkjWMMyj9nCO/HAs6GH\n" +
       "4rO8CMHkqa7JbE7L/Mvd4c9/XcPmzMwyZ+fAXswb/fyO4zWdj25WkI+IMUlV" +
       "dJE4P01zmg4d5khz\nUoWsrU5RJIMBa/Bq51sPPTaK/+xFRSGUzytSPAZxVM" +
       "4rMVWUsLYZy1jjDCyEUCGWhVY6HkIF0A9D\nyLO3OyMRHRsh5Jfoq3yFPoOJ" +
       "IkCCmKgQ+qIcUay+yhmDtJ9UEUKT4ULr4SpF7EfvBmoMBPW4HJGU\noaCu8U" +
       "FFi6aeeUXDQY7nsRTslsWIosU2a6IQIOGjGmhXcFCJkXFOFmUlGBUhYXllqY" +
       "AT5H4vRJNE\n2oohj4eUP3caS5ABWxRJwFo/f/b2ewfatz15hIUICWtTTwPV" +
       "Aa+AyStAeAUor4CDF/J4KIsqwpP5\nCay8D/IVKlvJoq49Wx85UueDAFGH/G" +
       "AiMrUONDIFaeeVVjupQ7T+8RBZ03/Wdzhw5+x6FlnB3LU3\n6+riD168fvpv" +
       "JYu9yJu9MBIFoTQXETIdpJqmCl69O5Wy0f/yqe2Xbl7/9Dt2UhmoPiPXM1eS" +
       "XK1z\nu0JTeCxA9bPJn7mv1PcA6jnuRX4oAFD0qPxQT+a4eaTlbLNV/4guBW" +
       "FUTFzESWTIKlpFxqCmDNlv\naIyU0X4lOKeYBHE1XNPMqKZ3MjpVJW01iyni" +
       "bZcWtL7eeSJ/2cevFb9JzWKV4lLHZteFDZbY5Xaw\n7NYwhvef/qTjmR9/cb" +
       "iPRooZKgbsgPEBSeSTsGSBvQQyWoKqQhxZ3y3HFEGMiNyAhEnE/bt0/vJf\n" +
       "/uXpMuYaCd5Ynm24OwH7/X0b0WPXv/vPOZSMhyc7iq2GPY1pU2lTbtE0bpjI" +
       "kXz8xuyfvs2dgIIH\nRUYXRzCtG4hqhqgdg9Tui2kbcI0tJ00d0G7IEfpZ9u" +
       "9+/sBotC6+/91XqdTFnBMION2wnVObmedJ\nM49Yd5o7e7dw+iDMW3V1x8Nl" +
       "0tVvgGIvUITk1/WdGpSNZJoTzdl5BZ9ce736kQ99yLsJFUkKJ2zi\naPyjQg" +
       "g8rA9CxUmq6zfQ2CobmkRaqjKiRphhGiDpeCoB4RblTv9NZPe3M6d/oOFc+L" +
       "2dJ6gBciZ+\nls3PRWfkSvfJO+8btygdOwPJ6tpkZjkFxGSvXXszUZ5/8VTM" +
       "iwp6URlvYroeToqTOO8FCKJbQA9w\nX9p4Opxge2dzqsLMcme/g6079+0yDn" +
       "0ym/RLXOleQqw903KCdXemuwfRznq6pJ62C1PJWaBqYoIj\nOA95ZRJE050g" +
       "WxNjsFknaGW6fajuV+90nzrMqvkY7kxb1c9//3e/3+f70bUBts7tM9fk43PO" +
       "fHbp\ndmcVy3yG6uZlACvnGobsqFFKVaJA7Vgc6Ow3ltS+cLDzlilRRTo+aQ" +
       "cM/6fh1/HC+3/4hyybqQ+w\nJ6ufpF1Bmg0s1BtzpsS68TurgzQtxCfDpBdy" +
       "sdw1ASy7UyxHsrHsGSfL+XCVmyzLc7DsNVkWqZaP\ndEpkGuySGaAl5cewuR" +
       "84hOsbp3A1cFWYwlXkEI4zhcsfUOKykBJsllOwGODJwEYyDmeGjUrSJdbA\n" +
       "OMWaAVelKVZlDrEGTbHyAMJJOtnFHQlLdy4S5+ePtVV2NvU9QcFTIRzUOH2H" +
       "XUzgeEx6Hoj++bnz\nOEWsn1+45/Jfr13BC+kOMknUoWi1aNEshxbHmq+4Ub" +
       "z948hJCoD8A5zOypf7tJd5mEs7o1GTTlGZ\nIfep9DGkqirA9GKq1vKmNY1N" +
       "TWCHCrADOe0HADqznaDtxqaB0Yg8KngpI8q2hawxzVBI3zjs4lNU\nnRxnHN" +
       "8NTEr1O1WdAL/JDiahtgMXt5ZMev7oIUrfNGqh42hkPhckOG2HXUTIDSpJ3/" +
       "/9sLBuxZoV\nDStXLl21ykBTdm8JdQVojAT6knugcEFLOA8dhCNZpqmIgsjO" +
       "CFB1CkUGZBOjNdE5CNL7O9tb2lKo\nx2MeC2hMq1aofi9zx/FC4EZEmaMH2E" +
       "Ww6eRLWI6yQxmtOLLpakLVy5ZYmVdpC9QqKTImYM8aYwcX\nUQmkvkvAYDJD" +
       "Pg3NTju2bKeRZm/7T53/xWXjwyXr2M6wOHduuBcuXnd6pGbdhaP3cFipcaWQ" +
       "m3R5\nYuYu36D4Dg0yE0VkfOhIX9Scjh2KQJ64Ju9OQxBzWS7RGF0wBoh9eo" +
       "yxY6T5AalFxB/MfWDjmuwg\nvT2mGhRWj7wy7eX7z568RaysJp2ZvHrZMspo" +
       "v/VuxZpVjauaJjJZVq6F/cdOFsL9Wau6kocRV00X\nx1nTG+CqMnOnKkdNf8" +
       "6s6QUJJYmlIcah3cyErSRlAIa797wTEyDImXRBKPY45eL78wngez6d70g2\n" +
       "vqPj5NsI11ST79QcfF+yAIgoJ3qY7bOxvjABrC9lsM5q7ZcngPUrGayzGvzV" +
       "MVgns5R20n+M1XXH\n2c9jlegFGYiuhSQm+coH5aGLfmWJa5hA+Nm5PoVS+H" +
       "74wa9KDnFv7PGaVWibASdTRV0q4QSWHJx9\ntH8k/cNIEDH0h6y70z7UANkK" +
       "oiddlbuAU0rn3THq5vukeQvyeiAumkjIke3+hCIKthvevlsEWCfu\nbOo2wR" +
       "Ux1Y18a3VdG29Vhrqd3LA1mPl1MUQAnc6qPvknAVNGvx3DHrdI8xG4UbSWkh" +
       "e/sW1w8x5t\nQFOiBTxXy0zA7llt0OKSz08p+lOJwZqxYOjqZStdMDSs8JwU" +
       "anv+WvGN4/HVW62ApUQ+n5hNbW1T\nQ+OapasBAXpEyuyzMez+JWluG6gkig" +
       "1CIyQLzFkO0//x25oewFyxQx4CBKZn/PHE/izhw588+vDX\n4Y/+xc4G1h8a" +
       "xQCdI3FJcn72cPTzVQ1HmFLF7CMI88M/DFSd/RM3ZBi9Uzn/zmbfMVCZezbk" +
       "HLk5\np30D2jimwdZk9pyT/gOwGiaR7n9VKyHKbJjKPv4k08xE7DIvDVjSfw" +
       "It8Bdn/wX28w++2Dc3eXT3\nMYooAWBxI7RIF8Fxgn0STgHI2pzULFofoIsX" +
       "el57qckKQPrJsMpxWk1LnxVsNLfbycAz6v8AL7iM\nFJUdAAA=");
}
