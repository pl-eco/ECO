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
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "8swf+XDe+xuOjZ+Vncu/7mq578kQAAA=");
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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
