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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "cpYooxAAAA==");
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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "0QVIe/4H/IvmCfQfAAA=");
}
