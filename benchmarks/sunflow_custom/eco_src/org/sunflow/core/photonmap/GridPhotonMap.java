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
    final private static float NORMAL_THRESHOLD = (float) Math.cos(10.0 *
                                                                     Math.
                                                                       PI /
                                                                     180.0);
    final private static int[] PRIMES = { 11, 19, 37, 109, 163, 251, 367,
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
        super();
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
                                                   Vector3 w = bounds.getExtents();
                                                   nx = (int) Math.max(w.
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
                                                 getNormal(),
                                               dir) >
                                             0) return;
                                       Point3 pt =
                                         state.
                                         getPoint();
                                       if (!bounds.
                                             contains(
                                               pt))
                                           return;
                                       Vector3 ext =
                                         bounds.
                                         getExtents();
                                       int ix = (int)
                                                  ((pt.
                                                      x -
                                                      bounds.
                                                        getMinimum().
                                                        x) *
                                                     nx /
                                                     ext.
                                                       x);
                                       int iy = (int)
                                                  ((pt.
                                                      y -
                                                      bounds.
                                                        getMinimum().
                                                        y) *
                                                     ny /
                                                     ext.
                                                       y);
                                       int iz = (int)
                                                  ((pt.
                                                      z -
                                                      bounds.
                                                        getMinimum().
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
                                                             getNormal(),
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
                                                     getNormal());
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
                                                       this.
                                                         growPhotonHash();
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
          getExtents();
        int ix =
          (int)
            ((p.
                x -
                bounds.
                  getMinimum().
                  x) *
               nx /
               ext.
                 x);
        int iy =
          (int)
            ((p.
                y -
                bounds.
                  getMinimum().
                  y) *
               ny /
               ext.
                 y);
        int iz =
          (int)
            ((p.
                z -
                bounds.
                  getMinimum().
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
          readLock().
          lock();
        PhotonGroup center =
          null;
        for (PhotonGroup g =
               this.
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
                  copy();
                rwl.
                  readLock().
                  unlock();
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
              black();
            Color diff =
              center ==
              null
              ? Color.
              black()
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
                               this.
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
                  readLock().
                  unlock();
                rwl.
                  writeLock().
                  lock();
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
                    copy();
                rwl.
                  writeLock().
                  unlock();
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
                                                  black();
                                         diffuse =
                                           Color.
                                             black();
                                         radiance =
                                           null;
                                         count = 0;
                                         this.id =
                                           id;
                                         next = null;
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1163966492000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAJ1YC2wUxxke3/mBH9WdDdhACcbGhFDIbauEqpWRCHUMHBzY" +
           "ssEkJtQZ787dLezt\nbGZn7bND00RVgfRFUBOplRoSVUjQKGkjpVVaKU2J8m" +
           "gbWolWaiohhSZCSiq1qVS1ARNaqf/M7N3u\nre+cOpZ2b3bmn//9f/OPn/0A" +
           "NbgMrdbdFJ9xiJsaGB3GzCXGgIVddz9MTeivNzQPn9tj0xiqy6CY\naXCUyO" +
           "iuZmCONdPQ0nf3Fxna5FBrJmdRniJFnjpibfH57c5smcfw4JkXOx45W98dQw" +
           "0ZlMC2TTnm\nJrUHLVJwOUpmjuAprHnctLSM6fL+DPoUsb3CALVdjm3uPoAe" +
           "QvEManR0wZOjnkxJuAbCNQczXNCk\neG1YigUOSxnh2LSJsb0sDnZurtwJav" +
           "v7RuZTA5MlYnEMzJEagNVry1Yra+eZ6sTPj33+2NM/iqPE\nOEqY9qhgpoMl" +
           "HOSNo7YCKUwS5m43DGKMo3abEGOUMBNb5qyUOo46XDNnY+4x4o4Ql1pTgrDD" +
           "9RzC\npMzSZAa16cIm5umcsrKPsiaxjNJXQ9bCOTC7MzBbmbtDzIOBLSYoxr" +
           "JYJ6Ut9UdNGyLeHd1RtrFv\nDxDA1qYC4XlaFlVvY5hAHSqWFrZz2ihnpp0D" +
           "0gbqgRSOVtVkKnztYP0ozpEJjlZE6YbVElA1S0eI\nLRwtj5JJThClVZEohe" +
           "KzqfPayfM/ePkumdv1BtEtoX8LbFoT2TRCsoQRWydq45yXejx9r7c6hhAQ\n" +
           "L48QK5rt6188kPnrr7oVzaer0AxNHiE6n9D3ne4eeXAnRXGhxhKHuqYIfoXl" +
           "shyG/ZX+ogNV21nm\nKBZTpcULI2/c+/Az5G8x1JJGjTq1vALkUbtOC45pEb" +
           "aT2IRhTow0aia2MSDX06gJxhlIeTU7lM26\nhKdRvSWnGqn8BhdlgYVwUTOM" +
           "TTtLS2MH87wcFx2EUBs8qB2efyP1J3856k9prmdnLTqtuUzXKMuV\nv3XKiO" +
           "bkKad2ATvaTmYaw/JrL3ZSIokcjg5qeVogGtaxbdpUy5lQtjq93SBT4veTsy" +
           "4KzTum6+oE\nFEZL2oJq2EUtg7AJ/dzVN48N7nn0pEoXkeK+zRzdCRJTvsSU" +
           "kJgqS0xVSOxTo52Meg6qq5NClwkt\nVBQhBkehmgH32jaOHt59/8neOKSPM1" +
           "0PDhSkvWCpr9qgTgeCkk9LdNQh71b88NCJ1Ny5bSrvtNrI\nXHV366XnLj79" +
           "r7bPxFCsOmwKkwG4WwSbYYG1ZTjsixZaNf7/+MbeF966+PZtQclx1DcPCebv" +
           "FJXc\nGw0OozoxABsD9mdXJuIH0djpGKoHeABIlPoD2qyJyqio6P4SOgpbmj" +
           "KoNUtZAVtiqQRpLTzP6HQw\nI7MmKcdLIThLRIqLwX/9nJe/YnW5I96dKstE" +
           "tCNWSPSd+1rjZ//8Uuvr0i0loE6EjsJRwlXZtwfJ\nsp8RAvNvf2/4u098cO" +
           "KQzBSVKqgIlLcGlFDmFkCNiF/fAbtADTNr4kmLiET7T2L953729+8kVUQs\n" +
           "mCkFdPPHMwjmV34JPXzxy9fXSDZ1ujhmAu0DMmXE0oDzdsbwjNCj+Mgfb/n+" +
           "r/GTgIKAPK45SySY\nxKRBMdi0ItymMLMAcDclo3f1eO8vf3PgqRMq4zcu0I" +
           "uEd03oX/3LO0fjp16ZVPuikB8hPr3m7Hsv\nXB1ZptykzsV1846m8B51Nsq4" +
           "JxwRkJ6FJEjq1zb1PPvQyBVfo45KhB+ELuj9mVfJhq3ffrcKBMVN\nvznqgp" +
           "MjDEcFAOfUGBHVdIfUJiXJNsr37SJb/JwR3/3i1QvKbq7hxiq90YR+7Jlcr/" +
           "fAb38h1WrF\n4SYrnMSAf8ofSfFaJ3zSFcW+XdjNA92dF/bdl7Qu3ASO48BR" +
           "h57EHWIAw8WKEvCpG5ouv/Jq5/1/\niKPYDtRiUWzswBI9UDOULXHzgOBFZ9" +
           "tdsjKT06JYk9JkJJ2wyndAMfTV5C6YSjtEZxXgzsTk5vOZ\nN4eelA6oCZtV" +
           "sizCZ/blA2fmfs+vSD4BfondPcX5xxN0o8HeL7w11d74/FOFGGoaR0nd75fH" +
           "sOUJ\nlBiH9s4tNdHQU1esV7Zqqi/pL+Pz6miah8RGkTPISRgLajFuqwaWCX" +
           "g+9MHywyhY1iE5SIvXrVzc\nO8ToDoWh4r1VvHargG2rGdi7K0V2wHPNF3mt" +
           "hsh9vsgGnXo2ryZ1aJFSu+C57ku9XkPqqC+10ZYH\nz8fWcUSl/YtUSQzmfJ" +
           "Xmaqg07qtUn7W8YkmhrrBCZgHacHE0UBbR59AncNENX58bNfTBvj5LGDZM\n" +
           "kXqL1GlykTp1wvORr9NHNXTK+zo1wXmY9dzFqmQuUqWV8Nz0VbpZQyVaCpsN" +
           "mOV+BYjq0MrQybkP\nmk9xBzB1ceoUSwgsCj/FSFYc06KxKs68u+Hy2t8lBy" +
           "6qAz3P0foQRPiUWtqeorpEuF3YNuB+oc73\n1VUFHmTYgfvapXfeO3xq0/tv" +
           "iEPOiXjEWcAjaqlPvjf4LTG43mHmFFxowtgt259bat375El74p5/\nth3Hrx" +
           "2O+efeFqg9/zoe5sNRa6hhL4X3tv+32xddy7x/EqiLrZ65/OB91zJ/uiFb1f" +
           "LlsxVugFnP\nssIwGho3OuB5U2rcqkDVkT/H4T5dWymOmstjacPX1a5HOUpG" +
           "d0HmiJ8w2bfACyEy8Lk/ChOdgg4E\niMTwsbKjkkFuqUOlMkrCP+sqzlj53x" +
           "vfU3s99f+bCf2e5w6tLX5z/2PyUtKgW3h2VrBpyaCmbICX\nomXtqcmtxOsS" +
           "ev4nYy/9+IulwMtWZFkxyMNyCpWTctlCZcpQd/U2ebDgcNnYzv6866dbz525" +
           "IvPd\n+R+paNPudBMAAA==");
    }
    public boolean allowDiffuseBounced() { return true;
    }
    public boolean allowReflectionBounced() { return true;
    }
    public boolean allowRefractionBounced() { return true;
    }
    public int numEmit() { return numEmit; }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1163966492000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVaCWwU1xl+u2t8g40B45jDHCaEaxdiQzlyGWzAYY3dNVdM" +
       "iDOeebueMDszmZm1\nF4dSaCLIoTShTapWKoRWVASaS0pTeqQ0aZLmUqskVR" +
       "MpUtID9YjSVE1bpVSJqv7/ezM7s7NHfAhL\n8+Z4x39////e+rGPyCTTILNF" +
       "M2wd0KkZ3tTbIxgmlTYpgmnugE/94kuTKnrObFO1IAlESVCWLFIT\nFc2IJF" +
       "hCRJYine0b0gZZpmvKgYSiWWGatsK3Kavt9W6Mrs5ZcPfJ83VHTpc0BcmkKK" +
       "kRVFWzBEvW\n1A6FJk2L1EZvE4aESMqSlUhUNq0NUTKZqqnkJk01LUG1zNvJ" +
       "IRKKklJdxDUtMj/qEI8A8YguGEIy\nwshHehhZWGGaQS1BVqnUliEHM5dnzw" +
       "S27Xmx3NGwSDl27gJxGAcg9byM1FzaHFH10KO71hw8dTZE\navpIjaz24mIi" +
       "SGIBvT5SnaTJAWqYbZJEpT4yVaVU6qWGLCjyCKPaR+pMOaEKVsqgZoyamjKE" +
       "A+vM\nlE4NRtP5GCXVIspkpERLMzI6istUkZy3SXFFSIDY9a7YXNzN+B0ErJ" +
       "SBMSMuiNSZUrJfVsHiTf4Z\nGRmbt8EAmFqWpNagliFVogrwgdRxWyqCmoj0" +
       "WoasJmDoJC0FVCzSWHBR1LUuiPuFBO23SIN/XA/v\nglEVTBE4xSIz/MPYSm" +
       "ClRp+VPPZZVv/J3Y9++8INzLdLJCoqyH8lTJrrmxSjcWpQVaR84qVU+KHO\n" +
       "m1Kzg4TA4Bm+wXxM26LzO6N//XkTHzMrz5jugduoaPWL2483xe7YopEQslGu" +
       "a6aMxs+SnIVDj92z\nIa1D1NZnVsTOsNP5XOyXNx0+Rz8MkspOUipqSioJfj" +
       "RV1JK6rFBjC1WpIVhU6iQVVJU2sf5OUgbP\nUXB5/rU7Hjep1UlKFPapVGPv" +
       "oKI4LIEqqoBnWY1rzrMuWIPsOa0TQsrgItfBVUP4H7tbZEM4YqbU\nuKINR0" +
       "xDjGhGIvMuagaN6IOapalJQY9sMWSph711CXoYnUi3yO7IoJakEUEUVFnVIg" +
       "kZwlbUVkh0\nCO/jXzqNnNcNBwIIhf6QViAatmqKRI1+8czF1w52bLvnbu4u" +
       "6OK2zBa5CiiGbYphpBjOUAxnUSSB\nACM0HSlzy4He90MEA9ZVL+ndd+Otdy" +
       "8IgcvowyWgNBy6AKSz2ekQtU1umHcyRBTB1xq+u/dY+NKZ\n67mvRQqjcd7Z" +
       "VW88/vqpf1UvDZJgfqhEMQGsK3GZHsTXDAQ2+4Mr3/p/v7fr6bdff+8qN8ws" +
       "0pwT\n/bkzMXoX+A1iaCKVAA/d5U9fURPaTXYdD5ISgASAQcY/IMxcP42sKN" +
       "7gICLKUhYlVXHNSAoKdjkw\nVmkNGtqw+4V5Si17ngbGqUK3boBrvu3n7I69" +
       "M3Rs67lnobV9UjDEvXRn6cp3nq16ianFAecaT/rr\npRYP9amus+wwKIXv73" +
       "2z5+sPf3RsL/MU21UsyImpAUUW0zDlSncKxLgCOIOGbN6pJjVJjsvCgELR\n" +
       "4z6rWbTqmb99tZabRoEvjmWXf/4C7vcrNpLDr9/yn7lsmYCIOcYVwx3GpZnm" +
       "rtxmGMIB5CN95K05\n33pZOAEQCLBjyiOUIUmISRaCSQ3eGsWQk4B1Q8yMF4" +
       "8u+NkrOx85xl1/SZFCxDurX/zy736/P/TA\n8wN8nh/vfYOPzz3956cvxqZz" +
       "NfGkuDAnL3nn8MTIHKBGR4PML0aBjX5x2fzHDsXetzmqy4b3DiiB\n/nLgBb" +
       "r4mvv/kAd/QpC68aWVOedKoIj31Rb6lSZYjI8I+7aUtWH0VKZcwvquxWYBsL" +
       "m8gALzlET9\n4sFziQWp21/9MWOoSvDWVl4/BtjjmqjFZiFqY6Yf/rYK5iCM" +
       "a31u+821ynOfwop9sKIIpYjZbQD6\nprOiwB49qezd51+ov/XNEAluJpUgqL" +
       "RZYABCKiByqTkIwJ3Wr7+BBWftcDm2TGTClNBoKyDteZti\nFnWizVhQudDT" +
       "P7D80ehr3SeYAgoiZx7/8q0zcmHnyUu/st5n67gQhrPnp3OzEhSh7ty1bw9N" +
       "LX3q\nkWSQlPWRWtEuk3cJSgqBog+qOtOpnaGUzurPrtB4ObIhA9Gz/Q7uIe" +
       "sHT9cb4RlH43O1Dy+rUduN\njhGcuxcvA4Q9bGNTmlm7OINuZbohDwlYOpMK" +
       "iIYtUHNwFbVymMX2Omyi3KBtBQ2/OZulJrim2ixN\nLcBSLzZdFqlOMLIxQZ" +
       "JTbJV1PuI7xkgcc0adTbyuAPE9NvFaELsX/Jva9YSZT/qbxsjALLim2QxM\n" +
       "K8DAPpuBoJrOR/KWy0ByIEPyQD6S4mUgmciQHMlHcnAcbjXdJjm9AEnFJlk6" +
       "oKVUyWQLzITQ85aT\nSfC48Ebshw3URi3tYys5RrYWwjXDZmtGAbZMm61ykS" +
       "oKgi3WMJ4MzPI2Jq6zD7ZPi63beycrHStg\n4yqY210kCMoSPgUAwhYVxtTM" +
       "Yv3i4n3n//H8BbqYwX+5bALitBmJPJs4z5yPhXO06534SVb+lQwI\nJsce/+" +
       "43d3ObtWdlWp2SramZxTTlmKp1tJV/M3/aYmgpPZODA3aVz+yEzYhjgkO5GB" +
       "jEdC6rAnOa\nJQCDpQpVE3znxZz1gJ33ceUgn+KwyYsuxHnYAGsqxfrN6eN7" +
       "EVkLZw4foDOdw6NB5mTtRLqY+txE\ndO/Z75+33ly2ntcvSwsb3D9x6fpTI0" +
       "3rn7xvHPuPJp9f+JeeOjTri6FB+ZUgO5XgeS3nNCN70obs\nbFYJ/KQMdUdW" +
       "TpvHTcV0js2VRUqrB4r0HcfmfjCriDbhJgQ9N+WvvTuSusWq5ZEfzfzBNWdO" +
       "vo+a\n1tOws65ikXf1F9aua1kL8+sgVPGALixLdqXR/tbmgXNx9ZzEFFHJIq" +
       "MN59hSVrAvntANabqJJxCe\noz57peZu3cSd2WQPkc72g0/dWF3+nfuOsvXt" +
       "uK/wnGbY72VDgrHdW7janK9taWlZZRHhMm3x11/d\numr56pYVa4DG5B1bO3" +
       "vDDrAhIw97gO6QQepztYcyEzdJg/RT3HjC0tzbCVKVxDra2n04bY0Rp6+A\n" +
       "q95etr4ATp92cHoQWO+FXVO+vPW9MRJutKHPgcB8hM/ahCuQMG5l8lI+N0bK" +
       "iwnfTxPnnofyEzbl\nkDGsOAC2yo0XqG/FlAGbfCusaOJ+3LjAswEVb4wK0m" +
       "5DtmgUvvsYfXKMjDbY9nHslI/RZ2xGy8A9\nOmCrl09BPxw9XUZmL+GVDHHu" +
       "eej+JDdxhPD5CDbdkDRMdgoN6aN2e3esqy3av2NrrKN3a3eUeay/\nnv3pGF" +
       "lsJ7zywb/ZBVj8RVEWsbmA2a0n1tnV0ctGDem88MyfOHVn4VfzJM3MwkvYEl" +
       "/J2OHl3GyJ\nr3dhczQ3/eH7PZyNlz8P898o0vcWNr/G5mucC2wf8qD4mnWt" +
       "V7ewqd/w4OPK1S2XER9XtaxZ3rp6\nxWrAxxtGW9CEuX0Wm/MswUhQa579AT" +
       "n/TcZ8+HbK51MvFPGptKvbRo/mIcqX5DC2RdEGBCXDUKdT\n4eEJw5xCx/7s" +
       "rOXYno+rjwov7gvaRlkDUGZp+gqFDlHFQ7yamz37yG8lXMtsF1/md3Emaj7/" +
       "CGRL\nU7TEZ6v8qYgTfYDNH9memOqCQf3HPiVDmiy5Cr/4eUHsHIXkExdL32" +
       "ttca8dtbhebv9dpO8TbD62\n+KGfG+OM7X9OhO0YXG02222jZruErVjiWGlO" +
       "js/1DgpoJPwpL1NAN+TYchfFc6gWZ8BM7wA5KSQo\nFnb2D3VF+hmf/yusvU" +
       "A5fvwUCkgTjwXw5UNXfZ9NRH1Ycjg1jzkeqwdqi/Th5MBkC39F4pnRZTsw\n" +
       "ZaJWP2KzfWTUbHs3S54oKhvQNIUKWLwGZjG+G4vI1IQNGLMOYhJ/bktZFI+J" +
       "cE/hk7BhnBJmDozu\nsiW8q6CEXUUYXVykbwk2zRaZkjC0YY6sTpnsEWDROA" +
       "VgRR3+MnjCFuBEXgEKZPElFuDxAVUcNDQV\nwEJKF7GiP6hYVPZosmplgrJg" +
       "1DJFtBZR0npswpCcIes5Jh5VqDPlRSZifcw9H9jK+2DU1g+5NVar\n2zBZ2o" +
       "vIiScSgTaotUFOR77xn3cw4TdOJLivgvxZzmXn9zFjUk+Rvhg2ULBPExSQrV" +
       "2Ox1MmxaQs\nUokhgCvF9olIASVDoNqWonpcUvQV6bsZm90WqWdSxGjcPj7I" +
       "L8ieiQpiH+UHco7yRyWIVKQvjo3g\nEcQQiggyMBFBAJYC9kltIOekdlSCqE" +
       "X6cJ3Aft9G0OVcGS3nsCWanBVheFTUkPM/SPz/ZsTou3fc\n/En0t//lx6LO" +
       "/7ZURUl5PKUo3p9rPM+lkLriMhO5iv94wzY8gWGLNBaOe6idM8/IcYDv1gIj" +
       "sMX0\nz4KUjzfvsC8BkHqGgaLsJ++gw4BCMAgfj+gOFtW6hzD8x6t0lsJQPw" +
       "uzjiHZP4c5R4Up/u9h/eKe\nx/fOS9+340F2/jhJVIQRdv5fGSVl/H8C2Kp4" +
       "3Di/4GrOWm+Qp57c9ewT65yNBfvJc7onrWX54XW8\nt7ADYMdD+v8BZsxWP6" +
       "gnAAA=");
}
