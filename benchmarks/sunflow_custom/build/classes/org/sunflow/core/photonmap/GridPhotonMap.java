package org.sunflow.core.photonmap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.sunflow.core.GlobalPhotonMapInterface;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.MathUtils;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class GridPhotonMap implements GlobalPhotonMapInterface {
    private int numGather;
    private float gatherRadius;
    private int numStoredPhotons;
    private int nx;
    private int ny;
    private int nz;
    private BoundingBox bounds;
    private PhotonGroup[] cellHash;
    private int hashSize;
    private int hashPrime;
    private ReentrantReadWriteLock rwl;
    private int numEmit;
    private static final float NORMAL_THRESHOLD = (float) Math.cos(10.0 *
                                                                     Math.
                                                                       PI /
                                                                     180.0);
    private static final int[] PRIMES = { 11, 19, 37, 109, 163, 251, 367,
    557,
    823,
    1237,
    1861,
    2777,
    4177,
    6247,
    9371,
    21089,
    31627,
    47431,
    71143,
    106721,
    160073,
    240101,
    360163,
    540217,
    810343,
    1215497,
    1823231,
    2734867,
    4102283,
    6153409,
    9230113,
    13845163 };
    public GridPhotonMap(int numEmit, int numGather, float gatherRadius) {
        super(
          );
        this.
          numEmit =
          numEmit;
        this.
          numGather =
          numGather;
        this.
          gatherRadius =
          gatherRadius;
        numStoredPhotons =
          0;
        hashSize =
          0;
        rwl =
          new ReentrantReadWriteLock(
            );
        numEmit =
          100000;
    }
    public void prepare(BoundingBox sceneBounds) { bounds = new BoundingBox(
                                                              sceneBounds);
                                                   bounds.enlargeUlps();
                                                   Vector3 w = bounds.getExtents(
                                                                        );
                                                   nx = (int) Math.
                                                          max(
                                                            w.
                                                              x /
                                                              gatherRadius +
                                                              0.5F,
                                                            1);
                                                   ny = (int) Math.
                                                          max(
                                                            w.
                                                              y /
                                                              gatherRadius +
                                                              0.5F,
                                                            1);
                                                   nz = (int) Math.
                                                          max(
                                                            w.
                                                              z /
                                                              gatherRadius +
                                                              0.5F,
                                                            1);
                                                   int numCells =
                                                     nx *
                                                     ny *
                                                     nz;
                                                   UI.printInfo(Module.
                                                                  LIGHT,
                                                                "Initializing grid photon map:");
                                                   UI.printInfo(Module.
                                                                  LIGHT,
                                                                "  * Resolution:  %dx%dx%d",
                                                                nx,
                                                                ny,
                                                                nz);
                                                   UI.printInfo(
                                                        Module.
                                                          LIGHT,
                                                        "  * Total cells: %d",
                                                        numCells);
                                                   for (hashPrime =
                                                          0;
                                                        hashPrime <
                                                          PRIMES.
                                                            length;
                                                        hashPrime++)
                                                       if (PRIMES[hashPrime] >
                                                             numCells /
                                                             5)
                                                           break;
                                                   cellHash =
                                                     (new PhotonGroup[PRIMES[hashPrime]]);
                                                   UI.printInfo(
                                                        Module.
                                                          LIGHT,
                                                        "  * Initial hash size: %d",
                                                        cellHash.
                                                          length);
    }
    public int size() { return numStoredPhotons; }
    public void store(ShadingState state, Vector3 dir,
                      Color power,
                      Color diffuse) { if (Vector3.
                                             dot(
                                               state.
                                                 getNormal(
                                                   ),
                                               dir) >
                                             0) return;
                                       Point3 pt =
                                         state.
                                         getPoint(
                                           );
                                       if (!bounds.
                                             contains(
                                               pt))
                                           return;
                                       Vector3 ext =
                                         bounds.
                                         getExtents(
                                           );
                                       int ix = (int)
                                                  ((pt.
                                                      x -
                                                      bounds.
                                                        getMinimum(
                                                          ).
                                                        x) *
                                                     nx /
                                                     ext.
                                                       x);
                                       int iy = (int)
                                                  ((pt.
                                                      y -
                                                      bounds.
                                                        getMinimum(
                                                          ).
                                                        y) *
                                                     ny /
                                                     ext.
                                                       y);
                                       int iz = (int)
                                                  ((pt.
                                                      z -
                                                      bounds.
                                                        getMinimum(
                                                          ).
                                                        z) *
                                                     nz /
                                                     ext.
                                                       z);
                                       ix = MathUtils.
                                              clamp(
                                                ix,
                                                0,
                                                nx -
                                                  1);
                                       iy = MathUtils.
                                              clamp(
                                                iy,
                                                0,
                                                ny -
                                                  1);
                                       iz = MathUtils.
                                              clamp(
                                                iz,
                                                0,
                                                nz -
                                                  1);
                                       int id = ix +
                                         iy *
                                         nx +
                                         iz *
                                         nx *
                                         ny;
                                       synchronized (this)  {
                                           int hid =
                                             id %
                                             cellHash.
                                               length;
                                           PhotonGroup g =
                                             cellHash[hid];
                                           PhotonGroup last =
                                             null;
                                           boolean hasID =
                                             false;
                                           while (g !=
                                                    null) {
                                               if (g.
                                                     id ==
                                                     id) {
                                                   hasID =
                                                     true;
                                                   if (Vector3.
                                                         dot(
                                                           state.
                                                             getNormal(
                                                               ),
                                                           g.
                                                             normal) >
                                                         NORMAL_THRESHOLD)
                                                       break;
                                               }
                                               last =
                                                 g;
                                               g =
                                                 g.
                                                   next;
                                           }
                                           if (g ==
                                                 null) {
                                               g =
                                                 new PhotonGroup(
                                                   id,
                                                   state.
                                                     getNormal(
                                                       ));
                                               if (last ==
                                                     null)
                                                   cellHash[hid] =
                                                     g;
                                               else
                                                   last.
                                                     next =
                                                     g;
                                               if (!hasID) {
                                                   hashSize++;
                                                   if (hashSize >
                                                         cellHash.
                                                           length)
                                                       growPhotonHash(
                                                         );
                                               }
                                           }
                                           g.
                                             count++;
                                           g.
                                             flux.
                                             add(
                                               power);
                                           g.
                                             diffuse.
                                             add(
                                               diffuse);
                                           numStoredPhotons++;
                                       } }
    public void init() { UI.printInfo(Module.LIGHT,
                                      "Initializing photon grid ...");
                         UI.printInfo(Module.LIGHT,
                                      "  * Photon hits:      %d",
                                      numStoredPhotons);
                         UI.printInfo(Module.LIGHT,
                                      "  * Final hash size:  %d",
                                      cellHash.
                                        length);
                         int cells = 0;
                         for (int i = 0; i < cellHash.
                                               length;
                              i++) { for (PhotonGroup g =
                                            cellHash[i];
                                          g !=
                                            null;
                                          g =
                                            g.
                                              next) {
                                         g.
                                           diffuse.
                                           mul(
                                             1.0F /
                                               g.
                                                 count);
                                         cells++;
                                     } }
                         UI.printInfo(Module.LIGHT,
                                      "  * Num photon cells: %d",
                                      cells); }
    public void precomputeRadiance(boolean includeDirect,
                                   boolean includeCaustics) {
        
    }
    private void growPhotonHash() { if (hashPrime >=
                                          PRIMES.
                                            length -
                                          1) return;
                                    PhotonGroup[] temp =
                                      new PhotonGroup[PRIMES[++hashPrime]];
                                    for (int i = 0;
                                         i <
                                           cellHash.
                                             length;
                                         i++) { PhotonGroup g =
                                                  cellHash[i];
                                                while (g !=
                                                         null) {
                                                    int hid =
                                                      g.
                                                        id %
                                                      temp.
                                                        length;
                                                    PhotonGroup last =
                                                      null;
                                                    for (PhotonGroup gn =
                                                           temp[hid];
                                                         gn !=
                                                           null;
                                                         gn =
                                                           gn.
                                                             next)
                                                        last =
                                                          gn;
                                                    if (last ==
                                                          null)
                                                        temp[hid] =
                                                          g;
                                                    else
                                                        last.
                                                          next =
                                                          g;
                                                    PhotonGroup next =
                                                      g.
                                                        next;
                                                    g.
                                                      next =
                                                      null;
                                                    g =
                                                      next;
                                                }
                                    }
                                    cellHash = temp;
    }
    public synchronized Color getRadiance(Point3 p,
                                          Vector3 n) {
        if (!bounds.
              contains(
                p))
            return Color.
                     BLACK;
        Vector3 ext =
          bounds.
          getExtents(
            );
        int ix =
          (int)
            ((p.
                x -
                bounds.
                  getMinimum(
                    ).
                  x) *
               nx /
               ext.
                 x);
        int iy =
          (int)
            ((p.
                y -
                bounds.
                  getMinimum(
                    ).
                  y) *
               ny /
               ext.
                 y);
        int iz =
          (int)
            ((p.
                z -
                bounds.
                  getMinimum(
                    ).
                  z) *
               nz /
               ext.
                 z);
        ix =
          MathUtils.
            clamp(
              ix,
              0,
              nx -
                1);
        iy =
          MathUtils.
            clamp(
              iy,
              0,
              ny -
                1);
        iz =
          MathUtils.
            clamp(
              iz,
              0,
              nz -
                1);
        int id =
          ix +
          iy *
          nx +
          iz *
          nx *
          ny;
        rwl.
          readLock(
            ).
          lock(
            );
        PhotonGroup center =
          null;
        for (PhotonGroup g =
               get(
                 ix,
                 iy,
                 iz);
             g !=
               null;
             g =
               g.
                 next) {
            if (g.
                  id ==
                  id &&
                  Vector3.
                  dot(
                    n,
                    g.
                      normal) >
                  NORMAL_THRESHOLD) {
                if (g.
                      radiance ==
                      null) {
                    center =
                      g;
                    break;
                }
                Color r =
                  g.
                    radiance.
                  copy(
                    );
                rwl.
                  readLock(
                    ).
                  unlock(
                    );
                return r;
            }
        }
        int vol =
          1;
        while (true) {
            int numPhotons =
              0;
            int ndiff =
              0;
            Color irr =
              Color.
              black(
                );
            Color diff =
              center ==
              null
              ? Color.
              black(
                )
              : null;
            for (int z =
                   iz -
                   (vol -
                      1);
                 z <=
                   iz +
                   (vol -
                      1);
                 z++) {
                for (int y =
                       iy -
                       (vol -
                          1);
                     y <=
                       iy +
                       (vol -
                          1);
                     y++) {
                    for (int x =
                           ix -
                           (vol -
                              1);
                         x <=
                           ix +
                           (vol -
                              1);
                         x++) {
                        int vid =
                          x +
                          y *
                          nx +
                          z *
                          nx *
                          ny;
                        for (PhotonGroup g =
                               get(
                                 x,
                                 y,
                                 z);
                             g !=
                               null;
                             g =
                               g.
                                 next) {
                            if (g.
                                  id ==
                                  vid &&
                                  Vector3.
                                  dot(
                                    n,
                                    g.
                                      normal) >
                                  NORMAL_THRESHOLD) {
                                numPhotons +=
                                  g.
                                    count;
                                irr.
                                  add(
                                    g.
                                      flux);
                                if (diff !=
                                      null) {
                                    diff.
                                      add(
                                        g.
                                          diffuse);
                                    ndiff++;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (numPhotons >=
                  numGather ||
                  vol >=
                  3) {
                float area =
                  (2 *
                     vol -
                     1) /
                  3.0F *
                  (ext.
                     x /
                     nx +
                     ext.
                       y /
                     ny +
                     ext.
                       z /
                     nz);
                area *=
                  area;
                area *=
                  Math.
                    PI;
                irr.
                  mul(
                    1.0F /
                      area);
                rwl.
                  readLock(
                    ).
                  unlock(
                    );
                rwl.
                  writeLock(
                    ).
                  lock(
                    );
                if (center ==
                      null) {
                    if (ndiff >
                          0)
                        diff.
                          mul(
                            1.0F /
                              ndiff);
                    center =
                      new PhotonGroup(
                        id,
                        n);
                    center.
                      diffuse.
                      set(
                        diff);
                    center.
                      next =
                      cellHash[id %
                                 cellHash.
                                   length];
                    cellHash[id %
                               cellHash.
                                 length] =
                      center;
                }
                irr.
                  mul(
                    center.
                      diffuse);
                center.
                  radiance =
                  irr.
                    copy(
                      );
                rwl.
                  writeLock(
                    ).
                  unlock(
                    );
                return irr;
            }
            vol++;
        }
    }
    private PhotonGroup get(int x, int y, int z) {
        if (x <
              0 ||
              x >=
              nx)
            return null;
        if (y <
              0 ||
              y >=
              ny)
            return null;
        if (z <
              0 ||
              z >=
              nz)
            return null;
        return cellHash[(x +
                           y *
                           nx +
                           z *
                           nx *
                           ny) %
                          cellHash.
                            length];
    }
    private class PhotonGroup {
        int id;
        int count;
        Vector3 normal;
        Color flux;
        Color radiance;
        Color diffuse;
        PhotonGroup next;
        PhotonGroup(int id, Vector3 n) { super();
                                         normal =
                                           new Vector3(
                                             n);
                                         flux = Color.
                                                  black(
                                                    );
                                         diffuse =
                                           Color.
                                             black(
                                               );
                                         radiance =
                                           null;
                                         count = 0;
                                         this.id =
                                           id;
                                         next = null;
        }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1163966492000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "dEV0Lv8CkzT7RbkQAAA=");
    }
    public boolean allowDiffuseBounced() { return true;
    }
    public boolean allowReflectionBounced() { return true;
    }
    public boolean allowRefractionBounced() { return true;
    }
    public int numEmit() { return numEmit; }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1163966492000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "RU7yWI1NvQezGz2AtPd/vcuqlgogAAA=");
}
