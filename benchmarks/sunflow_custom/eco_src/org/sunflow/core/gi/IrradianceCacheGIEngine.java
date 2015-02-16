package org.sunflow.core.gi;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.sunflow.core.GIEngine;
import org.sunflow.core.GlobalPhotonMapInterface;
import org.sunflow.core.Options;
import org.sunflow.core.Ray;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.core.photonmap.GlobalPhotonMap;
import org.sunflow.core.photonmap.GridPhotonMap;
import org.sunflow.image.Color;
import org.sunflow.math.MathUtils;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class IrradianceCacheGIEngine implements GIEngine {
    private int samples;
    private float tolerance;
    private float invTolerance;
    private float minSpacing;
    private float maxSpacing;
    private Node root;
    private ReentrantReadWriteLock rwl;
    private GlobalPhotonMapInterface globalPhotonMap;
    public IrradianceCacheGIEngine(Options options) { super();
                                                      samples = options.getInt(
                                                                          "gi.irr-cache.samples",
                                                                          256);
                                                      tolerance =
                                                        options.
                                                          getFloat(
                                                            "gi.irr-cache.tolerance",
                                                            0.05F);
                                                      invTolerance =
                                                        1.0F /
                                                          tolerance;
                                                      minSpacing =
                                                        options.
                                                          getFloat(
                                                            "gi.irr-cache.min_spacing",
                                                            0.05F);
                                                      maxSpacing =
                                                        options.
                                                          getFloat(
                                                            "gi.irr-cache.max_spacing",
                                                            5.0F);
                                                      root =
                                                        null;
                                                      rwl =
                                                        new ReentrantReadWriteLock(
                                                          );
                                                      globalPhotonMap =
                                                        null;
                                                      String gmap =
                                                        options.
                                                        getString(
                                                          "gi.irr-cache.gmap",
                                                          null);
                                                      if (gmap ==
                                                            null ||
                                                            gmap.
                                                            equals(
                                                              "none"))
                                                          return;
                                                      int numEmit =
                                                        options.
                                                        getInt(
                                                          "gi.irr-cache.gmap.emit",
                                                          100000);
                                                      int gather =
                                                        options.
                                                        getInt(
                                                          "gi.irr-cache.gmap.gather",
                                                          50);
                                                      float radius =
                                                        options.
                                                        getFloat(
                                                          "gi.irr-cache.gmap.radius",
                                                          0.5F);
                                                      if (gmap.
                                                            equals(
                                                              "kd"))
                                                          globalPhotonMap =
                                                            new GlobalPhotonMap(
                                                              numEmit,
                                                              gather,
                                                              radius);
                                                      else
                                                          if (gmap.
                                                                equals(
                                                                  "grid"))
                                                              globalPhotonMap =
                                                                new GridPhotonMap(
                                                                  numEmit,
                                                                  gather,
                                                                  radius);
                                                          else
                                                              UI.
                                                                printWarning(
                                                                  Module.
                                                                    LIGHT,
                                                                  "Unrecognized global photon map type \"%s\" - ignoring",
                                                                  gmap);
    }
    public boolean init(Scene scene) { samples =
                                         Math.
                                           max(
                                             0,
                                             samples);
                                       minSpacing =
                                         Math.
                                           max(
                                             0.001F,
                                             minSpacing);
                                       maxSpacing =
                                         Math.
                                           max(
                                             0.001F,
                                             maxSpacing);
                                       UI.
                                         printInfo(
                                           Module.
                                             LIGHT,
                                           "Irradiance cache settings:");
                                       UI.
                                         printInfo(
                                           Module.
                                             LIGHT,
                                           "  * Samples: %d",
                                           samples);
                                       if (tolerance <=
                                             0)
                                           UI.
                                             printInfo(
                                               Module.
                                                 LIGHT,
                                               "  * Tolerance: off");
                                       else
                                           UI.
                                             printInfo(
                                               Module.
                                                 LIGHT,
                                               "  * Tolerance: %.3f",
                                               tolerance);
                                       UI.
                                         printInfo(
                                           Module.
                                             LIGHT,
                                           "  * Spacing: %.3f to %.3f",
                                           minSpacing,
                                           maxSpacing);
                                       Vector3 ext =
                                         scene.
                                         getBounds().
                                         getExtents();
                                       root =
                                         new Node(
                                           scene.
                                             getBounds().
                                             getCenter(),
                                           1.0001F *
                                             MathUtils.
                                             max(
                                               ext.
                                                 x,
                                               ext.
                                                 y,
                                               ext.
                                                 z));
                                       return globalPhotonMap !=
                                         null
                                         ? scene.
                                         calculatePhotons(
                                           globalPhotonMap,
                                           "global",
                                           0)
                                         : true;
    }
    public Color getGlobalRadiance(ShadingState state) {
        if (globalPhotonMap ==
              null) {
            if (state.
                  getShader() !=
                  null)
                return state.
                  getShader().
                  getRadiance(
                    state);
            else
                return Color.
                         BLACK;
        }
        else
            return globalPhotonMap.
              getRadiance(
                state.
                  getPoint(),
                state.
                  getNormal());
    }
    public Color getIrradiance(ShadingState state,
                               Color diffuseReflectance) {
        if (samples <=
              0)
            return Color.
                     BLACK;
        if (state.
              getDiffuseDepth() >
              0) {
            float xi =
              (float)
                state.
                getRandom(
                  0,
                  0,
                  1);
            float xj =
              (float)
                state.
                getRandom(
                  0,
                  1,
                  1);
            float phi =
              (float)
                (xi *
                   2 *
                   Math.
                     PI);
            float cosPhi =
              (float)
                Math.
                cos(
                  phi);
            float sinPhi =
              (float)
                Math.
                sin(
                  phi);
            float sinTheta =
              (float)
                Math.
                sqrt(
                  xj);
            float cosTheta =
              (float)
                Math.
                sqrt(
                  1.0F -
                    xj);
            Vector3 w =
              new Vector3(
              );
            w.
              x =
              cosPhi *
                sinTheta;
            w.
              y =
              sinPhi *
                sinTheta;
            w.
              z =
              cosTheta;
            OrthoNormalBasis onb =
              state.
              getBasis();
            onb.
              transform(
                w);
            Ray r =
              new Ray(
              state.
                getPoint(),
              w);
            ShadingState temp =
              state.
              traceFinalGather(
                r,
                0);
            return temp !=
              null
              ? this.
              getGlobalRadiance(
                temp).
              copy().
              mul(
                (float)
                  Math.
                    PI)
              : Color.
                  BLACK;
        }
        rwl.
          readLock().
          lock();
        Color irr =
          this.
          getIrradiance(
            state.
              getPoint(),
            state.
              getNormal());
        rwl.
          readLock().
          unlock();
        if (irr ==
              null) {
            irr =
              Color.
                black();
            OrthoNormalBasis onb =
              state.
              getBasis();
            float invR =
              0;
            float minR =
              Float.
                POSITIVE_INFINITY;
            Vector3 w =
              new Vector3(
              );
            for (int i =
                   0;
                 i <
                   samples;
                 i++) {
                float xi =
                  (float)
                    state.
                    getRandom(
                      i,
                      0,
                      samples);
                float xj =
                  (float)
                    state.
                    getRandom(
                      i,
                      1,
                      samples);
                float phi =
                  (float)
                    (xi *
                       2 *
                       Math.
                         PI);
                float cosPhi =
                  (float)
                    Math.
                    cos(
                      phi);
                float sinPhi =
                  (float)
                    Math.
                    sin(
                      phi);
                float sinTheta =
                  (float)
                    Math.
                    sqrt(
                      xj);
                float cosTheta =
                  (float)
                    Math.
                    sqrt(
                      1.0F -
                        xj);
                w.
                  x =
                  cosPhi *
                    sinTheta;
                w.
                  y =
                  sinPhi *
                    sinTheta;
                w.
                  z =
                  cosTheta;
                onb.
                  transform(
                    w);
                Ray r =
                  new Ray(
                  state.
                    getPoint(),
                  w);
                ShadingState temp =
                  state.
                  traceFinalGather(
                    r,
                    i);
                if (temp !=
                      null) {
                    minR =
                      Math.
                        min(
                          r.
                            getMax(),
                          minR);
                    invR +=
                      1.0F /
                        r.
                        getMax();
                    temp.
                      getInstance().
                      prepareShadingState(
                        temp);
                    irr.
                      add(
                        this.
                          getGlobalRadiance(
                            temp));
                }
            }
            irr.
              mul(
                (float)
                  Math.
                    PI /
                  samples);
            invR =
              samples /
                invR;
            rwl.
              writeLock().
              lock();
            this.
              insert(
                state.
                  getPoint(),
                state.
                  getNormal(),
                invR,
                irr);
            rwl.
              writeLock().
              unlock();
        }
        return irr;
    }
    private void insert(Point3 p, Vector3 n,
                        float r0,
                        Color irr) { if (tolerance <=
                                           0)
                                         return;
                                     Node node =
                                       root;
                                     r0 =
                                       MathUtils.
                                         clamp(
                                           r0 *
                                             tolerance,
                                           minSpacing,
                                           maxSpacing) *
                                         invTolerance;
                                     if (root.
                                           isInside(
                                             p)) {
                                         while (node.
                                                  sideLength >=
                                                  4.0 *
                                                  r0 *
                                                  tolerance) {
                                             int k =
                                               0;
                                             k |=
                                               p.
                                                 x >
                                                 node.
                                                   center.
                                                   x
                                                 ? 1
                                                 : 0;
                                             k |=
                                               p.
                                                 y >
                                                 node.
                                                   center.
                                                   y
                                                 ? 2
                                                 : 0;
                                             k |=
                                               p.
                                                 z >
                                                 node.
                                                   center.
                                                   z
                                                 ? 4
                                                 : 0;
                                             if (node.
                                                   children[k] ==
                                                   null) {
                                                 Point3 c =
                                                   new Point3(
                                                   node.
                                                     center);
                                                 c.
                                                   x +=
                                                   (k &
                                                      1) ==
                                                     0
                                                     ? -node.
                                                          quadSideLength
                                                     : node.
                                                         quadSideLength;
                                                 c.
                                                   y +=
                                                   (k &
                                                      2) ==
                                                     0
                                                     ? -node.
                                                          quadSideLength
                                                     : node.
                                                         quadSideLength;
                                                 c.
                                                   z +=
                                                   (k &
                                                      4) ==
                                                     0
                                                     ? -node.
                                                          quadSideLength
                                                     : node.
                                                         quadSideLength;
                                                 node.
                                                   children[k] =
                                                   new Node(
                                                     c,
                                                     node.
                                                       halfSideLength);
                                             }
                                             node =
                                               node.
                                                 children[k];
                                         }
                                     }
                                     Sample s =
                                       new Sample(
                                       p,
                                       n,
                                       r0,
                                       irr);
                                     s.next =
                                       node.
                                         first;
                                     node.
                                       first =
                                       s;
    }
    private Color getIrradiance(Point3 p,
                                Vector3 n) {
        if (tolerance <=
              0)
            return null;
        Sample x =
          new Sample(
          p,
          n);
        float w =
          root.
          find(
            x);
        return x.
                 irr ==
          null
          ? null
          : x.
              irr.
          mul(
            1.0F /
              w);
    }
    final private class Node {
        Node[] children;
        Sample first;
        Point3 center;
        float sideLength;
        float halfSideLength;
        float quadSideLength;
        Node(Point3 center, float sideLength) {
            super();
            children =
              (new Node[8]);
            for (int i =
                   0;
                 i <
                   8;
                 i++)
                children[i] =
                  null;
            this.
              center =
              new Point3(
                center);
            this.
              sideLength =
              sideLength;
            halfSideLength =
              0.5F *
                sideLength;
            quadSideLength =
              0.5F *
                halfSideLength;
            first =
              null;
        }
        final boolean isInside(Point3 p) {
            return Math.
              abs(
                p.
                  x -
                  center.
                    x) <
              halfSideLength &&
              Math.
              abs(
                p.
                  y -
                  center.
                    y) <
              halfSideLength &&
              Math.
              abs(
                p.
                  z -
                  center.
                    z) <
              halfSideLength;
        }
        final float find(Sample x) { float weight =
                                       0;
                                     for (Sample s =
                                            first;
                                          s !=
                                            null;
                                          s =
                                            s.
                                              next) {
                                         float c2 =
                                           1.0F -
                                           (x.
                                              nix *
                                              s.
                                                nix +
                                              x.
                                                niy *
                                              s.
                                                niy +
                                              x.
                                                niz *
                                              s.
                                                niz);
                                         float d2 =
                                           (x.
                                              pix -
                                              s.
                                                pix) *
                                           (x.
                                              pix -
                                              s.
                                                pix) +
                                           (x.
                                              piy -
                                              s.
                                                piy) *
                                           (x.
                                              piy -
                                              s.
                                                piy) +
                                           (x.
                                              piz -
                                              s.
                                                piz) *
                                           (x.
                                              piz -
                                              s.
                                                piz);
                                         if (c2 >
                                               tolerance *
                                               tolerance ||
                                               d2 >
                                               maxSpacing *
                                               maxSpacing)
                                             continue;
                                         float invWi =
                                           (float)
                                             (Math.
                                                sqrt(
                                                  d2) *
                                                s.
                                                  invR0 +
                                                Math.
                                                sqrt(
                                                  Math.
                                                    max(
                                                      c2,
                                                      0)));
                                         if (invWi <
                                               tolerance ||
                                               d2 <
                                               minSpacing *
                                               minSpacing) {
                                             float wi =
                                               Math.
                                               min(
                                                 1.0E10F,
                                                 1.0F /
                                                   invWi);
                                             if (x.
                                                   irr !=
                                                   null)
                                                 x.
                                                   irr.
                                                   madd(
                                                     wi,
                                                     s.
                                                       irr);
                                             else
                                                 x.
                                                   irr =
                                                   s.
                                                     irr.
                                                     copy().
                                                     mul(
                                                       wi);
                                             weight +=
                                               wi;
                                         }
                                     }
                                     for (int i =
                                            0;
                                          i <
                                            8;
                                          i++)
                                         if (children[i] !=
                                               null &&
                                               Math.
                                               abs(
                                                 children[i].
                                                   center.
                                                   x -
                                                   x.
                                                     pix) <=
                                               halfSideLength &&
                                               Math.
                                               abs(
                                                 children[i].
                                                   center.
                                                   y -
                                                   x.
                                                     piy) <=
                                               halfSideLength &&
                                               Math.
                                               abs(
                                                 children[i].
                                                   center.
                                                   z -
                                                   x.
                                                     piz) <=
                                               halfSideLength)
                                             weight +=
                                               children[i].
                                                 find(
                                                   x);
                                     return weight;
        }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1166303790000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAALVYe2wUxxkfn98P8GHAPAIYjAkBwx3hGTAqcW0DB4dxbR6J" +
           "CXXGu3N3C3u7y+6c\nOTuUJIoKNLQNtI3USA0hERKPJg1V0ppXKSiPhlAkEq" +
           "mJhBSaCCmp1KZqVCmlav/oNzO7t3t7d6ZQ\n9aTdm9355nvN9/2+b+fVL1Gp" +
           "ZaIpkhWigwaxQm09Xdi0iNymYsvaBK/6pHdKK7uOr9f0ACqKooAi\nU1Qbla" +
           "ywjCkOK3I40t6SNlGzoauDcVWnIZKmoR3qEpvfuuiSHIZbjwzXPX2spCGASq" +
           "OoFmuaTjFV\ndK1DJUmLomB0Bx7A4RRV1HBUsWhLFI0iWirZpmsWxRq1dqG9" +
           "qDiKygyJ8aRoRtQRHgbhYQObOBnm\n4sNdXCxwGGsSihWNyK0ZcbByXvZKUN" +
           "te151LDUwq2OQWMIdrAFZPz1gtrM0x1Sg+sWXpnqMni1Ft\nL6pVtB7GTAJL" +
           "KMjrRTVJkuwnptUqy0TuRWM0QuQeYipYVYa41F5UZylxDdOUSaxuYunqACOs" +
           "s1IG\nMblM52UU1UjMJjMlUd3M+CimEFV2nkpjKo6D2fWu2cLc1ew9GFilgG" +
           "JmDEvEWVKyU9Fgxxv8KzI2\nNq0HAlhaniQ0oWdElWgYXqA6sZcq1uLhHmoq" +
           "WhxIS/UUSKFockGmzNcGlnbiOOmjaKKfrktMAVUl\ndwRbQtF4PxnnBLs02b" +
           "dLnv1prv/6wImfXXyYx3aJTCSV6V8Fi6b5FnWTGDGJJhGx8HYq9JPIo6kp\n" +
           "AYSAeLyPWNC0zhreHP3TbxsEzX15aDb27yAS7ZM6Dzd0P7FGR8VMjQpDtxS2" +
           "+VmW83Tosmda0gZk\nbX2GI5sMOZOXut999KlT5M8BVBVBZZKuppIQR2MkPW" +
           "koKjHXEI2YmBI5giqJJrfx+Qgqh3EUQl68\n3RiLWYRGUInKX5Xp/BlcFAMW" +
           "zEWVMFa0mO6MDUwTfJw2EEJ1cKEJcP0aiR//p+gbobCV0mKqvjts\nmVJYN+" +
           "OZZ0k3STiuhCOmiWUFg6PbsJQgayIdWhx0CLE4MijqDSf0JAljCWuKpsMCyF" +
           "xJny+TAfb/\nP3FPM/3rdhcVMUD0J7YKObFWV2Vi9knHb72/p2P99w6IoGGB" +
           "bltO0QIQGrKFhpjQUFwJFRDa1KnL\nBBUVcYHjmAZiH2EXdkI+A/LVzOnZvu" +
           "7xA43FEEDG7hJwISNtBENttTokvc1N+gjHRwkib+Ir2/aH\nbh9fJSIvXBib" +
           "866uvv7a1aN/r5kbQIH8wMnMBeiuYmy6GNpmALHJn2r5+P/12Q1vfHT1kwfc" +
           "pKOo\nKQcLcleyXG70b4ypS0QGdHTZH5tUW7wVbTkcQCUAEACKXH/Am2l+GV" +
           "k53eLgI7OlPIqqY7qZxCqb\nckCtiiZMfbf7hkdMkI/HwuZUsCCvgeuiHfX8" +
           "n82ON9i9XkQY222fFRx/bz9TtuDjC9XvcLc4UF3r\nKYY9hIrEH+MGyyaTEH" +
           "j/yU+7fvz8l/u38UgRoYLSQHm/SwmJrgLYsP1r2qwldVmJKbhfJSzQ/l07\n" +
           "68Ff/eWHQbEjKrxxNnTenRm47yd9Ez119dv/mMbZFEms0Ljau2TCiLEu51ZI" +
           "kEGmR/rpD6e+8Dv8\nIuAgYI+lDBEOJwFuUIA7egJFE7xJlgTgAeiDwrUImE" +
           "70NjKmkgRAHOC7e2tf42/e2/zSfpERc0bo\nVryr+qQn//jpzuLnLveLdf6i" +
           "4CM+PO3Y52/c6h4n3Cgq58yc4uVdI6onj4tag23YjJEkcOq3m2e8\nurf7pq" +
           "1RXXYN6IA+6YvBt8jslT/4LA88QVTpmHJxIe7NOfw+n4WLHTTsuYXdGkGbeQ" +
           "X8lKc96pP2\nnIo3pnZdOcvlVmNvn+WN4g3YEAYH2W0mM3qCH/zWYisBdIsv" +
           "dT4WVC/9Czj2AkcJ2hJrowkYnM7K\nAZu6tPzG5bfqH/+gGAVWoyowVF6NOX" +
           "ygSshbYiUAvtPGqod5agZ3s2wNcpMRd8Jk2wFpz1OZNWKs\nrGbNlQs8ff3z" +
           "TkTf3/gid0BB3MwTRj4+Qxc3H7l9jd7kfFwAY6tnpHNrEzSk7tqHPhoYU3b6" +
           "pWQA\nlfeioGS3zFuwmmIw0QsdnuX00dBWZ81nd2uiNWnJAPQUfxx7xPqh0w" +
           "06GDNqNq7Jh5YT4Rq20XLY\nj5ZFiA8i7HY/RRVSQlFlwGsGoJ485+jB0uPk" +
           "ofax3cu3PcPrViX00NjqdBWBLxc2KgIPziq8pRlm\nfdLs7cN/u3yRzObRV6" +
           "FYYHCrGc/TT3rWfIVPkQ0fx47w2lPSjy1hur8Rz+2zs9pn7qnRuXWloKes\n" +
           "77DeAE3yeKVT13inp0hMs7STZGxvQyaJMShmxTM9+NnsG9N/H2y7KkA7QdEs" +
           "TxTYlOGINqBLPIjX\nYk2GLlJg+JS8Area2ICu/Pqnn29/rvmLdwO8JhkG9H" +
           "/VfFMWLl2+eMFyWF4Hy9lnZEiR7Rxo/3B1\n/6mYdkoOcDdxp7WyNfYmVvI3" +
           "nl0t1g2L9cmeD1KbU9NGw2IdwyiPkEj7ntPraipePriP87dDotLT\nc9vP5Q" +
           "PY7PQip635suXLFj5Ekfz/60JXLFy4eN6iBfMXgZhRm9ZGekJO2DNdtnrSYC" +
           "98AeQ6kJlt\nhwlrw8EBo92NZ+XBOwmGlXR3tLaLvoTdV7LbOoGBqwpiZXt2" +
           "bI6H64zN9kyBLI7ZWVwaU0yLOqV8\n4d30yz04aYivD4+u8bvUtR6us7auZw" +
           "vomrR1LZMIy9M79R0+jbS71GgSXOdsjc4V0IjaGlVZikyi\nRIuLr60lPtGp" +
           "uxQ9Fa7ztujzBUQP2qJHJ7Aa6xlR/NA9iL9gi79QQPxeR/yuFJZHFv/kCOLF" +
           "VBO/\nzxYNcoAHo4ahuJUbpjIA38Te2s/756mFjg54K7b/ka9q9uG3twfsvm" +
           "kZhIx9ouPyCTA2WZ9sGzjU\nuzX72ZM/H6YfNK8QHd3cwsXJv3DuiqNDDSte" +
           "P3gPH2oNPsP8rMcM3Pet4oTyHsdJuwXIOQTKXtSS\nXfirQJ+UqW3KKv/TMw" +
           "Ewmrn4Abiu2AFwxR8A7u5mbVsRG3837Wtfi+zP4zukKmf6/Ait7wvsdoiy\n" +
           "eg92QbhxqgcNIW8phEq/rqsEa27YHb5T1GfiiT18P9sDjXBdsz1wLa8H2O1H" +
           "I1t7DyjKeR8bwREn\n2O1lyk58NF4ll7gWv/LfWpyG9eyUw1G0+S4UZV9zOc" +
           "er4khQit544rGvo3/4p2iznGO7aqjjsZSq\nertPz7jMgG5G4cZVi17U4H+/" +
           "pGhsHr0oCsQVrvhpQfcmRUE/HdjH/rxkw9AseMggYuyRl+gchc7F\njLPhec" +
           "PxTtAt1KL7zoYj1nDNzAIHftLtJHBKnHX3SY+8tm16+uCmQxwVSiUVDw0xNl" +
           "XQ1YgjjQwI\nzCjIzeF1HZ1+fcuFXyx3EI5/s43zpGZWbK8UsyMEBwBP/gOF" +
           "jqRB+RHA0JkJb648fuSm6Br/A89l\npGagGAAA");
    }
    final private static class Sample {
        float pix;
        float piy;
        float piz;
        float nix;
        float niy;
        float niz;
        float invR0;
        Color irr;
        Sample next;
        Sample(Point3 p, Vector3 n) { super();
                                      pix =
                                        p.
                                          x;
                                      piy =
                                        p.
                                          y;
                                      piz =
                                        p.
                                          z;
                                      Vector3 ni =
                                        new Vector3(
                                        n).
                                        normalize();
                                      nix =
                                        ni.
                                          x;
                                      niy =
                                        ni.
                                          y;
                                      niz =
                                        ni.
                                          z;
                                      irr =
                                        null;
                                      next =
                                        null;
        }
        Sample(Point3 p, Vector3 n, float r0,
               Color irr) { super();
                            pix = p.x;
                            piy = p.y;
                            piz = p.z;
                            Vector3 ni = new Vector3(
                              n).
                              normalize();
                            nix = ni.x;
                            niy = ni.y;
                            niz = ni.z;
                            invR0 = 1.0F /
                                      r0;
                            this.irr = irr;
                            next = null; }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1166303790000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAALUYa2wcR3l8d7bjB/jiJM4D144dpyE4vS20QUUOpME4ySWX" +
           "xLJjp700XMa7c+dN\n9tXdOfvspqFVoQkgKBEggdSkEUQ4VC1UKqgglZKqD6" +
           "ABKSBRpEgNrSKStLRICKkYwQ++mdm93dvb\nc5RWOWnnZme++d6v2afeQ/WO" +
           "jTplJ0VnLOKkBkeHse0QZVDDjrMXlnLyK/VNw3M7DTOG6jIopioU\ntWVkR1" +
           "IwxZKqSOkvDJRs1G+Z2kxBM2mKlGjqkLbRxbcjs7EK4b5Tz7U/fCbRHUP1Gd" +
           "SGDcOkmKqm\nMaQR3aEomTmEp7BUpKomZVSHDmTQR4hR1AdNw6HYoM796CiK" +
           "Z1CDJTOcFPVkPOISEJcsbGNd4uSl\nYU4WMCyxCcWqQZQtZXJwckPlSWDbPT" +
           "dSDQ1IFrHNcRCHcwBSry5LLaStEtWKnx3/9JHTP46jtixq\nU41RhkwGSSjQ" +
           "y6JWnegTxHa2KApRsmixQYgySmwVa+osp5pF7Y5aMDAt2sQZIY6pTTHAdqdo" +
           "EZvT\n9BYzqFVmMtlFmZp2WUd5lWiK91af13ABxO7wxRbibmXrIGCzCozZeS" +
           "wT70jisGqAxbvDJ8oy9u0E\nADjaqBM6aZZJJQwMC6hd2FLDRkEapbZqFAC0" +
           "3iwCFYpW1UTKdG1h+TAukBxFK8Jww2ILoJq4ItgR\nipaFwTgmsNKqkJUC9u" +
           "nveP/42cdfuJv7dkIhssb4b4ZDXaFDIyRPbGLIRBycL6a+k7632BlDCICX\n" +
           "hYAFzJa1z41lrv26W8B8LAJmz8QhItOcvPtE98gD20wUZ2wsskxHZcavkJyH" +
           "w7C7M1CyIGo7yhjZ\nZsrbPDfy6r0PPUn+HkPNadQgm1pRBz9aLJu6pWrE3k" +
           "YMYmNKlDRqIoYyyPfTqBHmGXB5sbonn3cI\nTaOExpcaTP4OKsoDCqaiJpir" +
           "Rt705hamk3xeshBCK+FBXfD8DYkf/6focynJKRp5zZyWHFuWTLtQ\nfpdNm0" +
           "gFVUrbNlZUDIoexPIk2ZYeMgrAQ4r5kUVRVpo0dSJhGRuqYcIBiFzZvE0hU+" +
           "z/Q2EvMf7b\np+vqWEIMB7YGMbHd1BRi5+S5y68dGdr51ePCaZiju5JT9Ckg" +
           "mnKJphjRVEFN1SDaN4p1SyOoro6T\nXMp4EJYEOxyGiIbc17p+9MCOg8d74+" +
           "BC1nQClBgD0F4Q1WVsSDYH/bBP8wwpg++t+MH+Y6n5uc3C\n96Ta2TnydMuF" +
           "p8+f/lfrJ2IoFp06mcCQvJsZmmGWb8spsS8cbFH4//G1Xc++fv6Nj/thR1Ff" +
           "VTao\nPsmiuTdsGtuUiQL50Ud/ZmVbfB8aPxFDCUgRkBY5/5BxusI0KqJ6wM" +
           "uQTJbGDGrJm7aONbblpbVm\nOmmb0/4K95kkny8B4yxibv5ReN51/Z7/s91l" +
           "Fhs7hI8xa4ek4Bl4/pGG2//yfMsrXC1esm4LlMNR\nQkXoL/adZa9NCKy/8b" +
           "3hb3/3vWP7uacIV0ElgLzVh4RQ1yDdMPv1jRm6qah5FU9ohDna/9rWfvLn\n" +
           "734zKSyiwYpn0A3XR+Cvr/w8euj8F//dxdHUyazU+Nz7YEKIJT7mLRAiM4yP" +
           "0sN/uuX7v8EnIRNC\n9nHUWcITSsz1fcbUcoqWB8NMh9QDyQ9K1x3e/oqq/X" +
           "HC/OgOrv8UB1vPx9uYnlxtsfc72dALzG2o\nETIRnUFOPvJkobd4/+9+ycVu" +
           "wcEWI2i+XdgaEB7DhjXMPMvDUb8dO5MAd+e53fcltXP/BYxZwChD\nRXb22J" +
           "B+ShXGd6HrGy+++FLHwT/GUWwratZMrGzFPG5QEzgscSYhc5WszXdzn0xOMz" +
           "dNcpERV8Iq\nz13YS2e1N8+73jwf6c1suDWk0gTHmPjQ9gINrQj2praqQ42b" +
           "4uF6+dHeX/127IljIsWtX6ABDZ7K\nyV/665uH44+9OCHOhet8CPhE15krz1" +
           "4eWSriQjRDa6r6keAZ0RBx1bRZzMQ9C1Hg0C/39zx1dOSS\ny1F7ZVkfgtb3" +
           "6sxLZN2mb7wVUXEgTZiYRipa1aFJYkFr2pydzQv4/TAbNvGtjWz4rHCGu67r" +
           "M6XA\nW5OzoB22sl7Uz9K5iQ1nM6/tOcmFqllkIkwUwjP7wtip+T/QSxyPn+" +
           "3Z6Z5SdSmH/t0/e9frU4sb\nnnlCj6HGLErK7g1jHGtFllOz0BA73rUDbiEV" +
           "+5XNrejkBsrVrDPsIwGy4TrjGxTmDJrNW6NKSyc8\nV9xgvBIOxjrEJ1kejx" +
           "TFLZWj2C5KTtmy+69n2bEbp5nzac5E0Tx4E2gqPs3ZKJrkA9C86tK8WoOm\n" +
           "6tE0onV76CbQNHyakbo1bwJNx6cZqVt6gzSXwnPNpXmtBs2SS7NeNaZGbo+i" +
           "OnODVNvhedul+nYN\nqg96kqq2fb0kGmLn6AdQwjsuO+/UYOfLLjsJA7Kn8y" +
           "AA1aGVgQK42zT4/U2VWfEoef0DS0Epm+RZ\ne8Ua4tLMW+surv59cvC8aMQm" +
           "KVobSFYupJQ2pkyZ59rt2FDgbij6ss5IgvtsbMFd+8KbVw481n/1\nVVarrJ" +
           "BGvrKARsRWHx/Xif40zqqXamBIl42WrU7BpZSiBod/IgmWFd7H3lLrEs8r6L" +
           "F7/tn6KH75\nQMwtZzsAkfttJYgHVsWlyzN1/w3c11gvUvW9R3yjkDMXH7jv" +
           "/cyf/8NvHOXvCC1wmc8XNS2Y3wPz\nBgsMIRJJi8j2Fv87SdGSCL4oihVUzv" +
           "jjAu40RckwHLgO+wuC/ZCilgAYKNudBYF+BCEAQGw6Z3na\nSfrOJepbpVWY" +
           "r6ypKPf805urm11F8fEtJ9/z9P7Vpa/v/Ra/TdbLGp7lSaU5gxrFDat8eeyp" +
           "ic3D\ndQE989Px53/yGc/QvJNeWvIdsaKX3Sh2F4hTG3VH32+GdIvyG8nsL5" +
           "b/bNPcqUvc4a3/A7gkTmYx\nFQAA");
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1166303790000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVZe2wUxxkf3519fkV+AH6BMTZOKJjcJQrQFKMmjmucgwW7" +
       "5weJSWrGu3N3i/d2\nl905+3BQmqhKIKSPoFIpURtAFaohTSBS2tJIaQpK0i" +
       "bQSkmkphJVaCP6ktpUqiqlVO0f/WZ29/Zu\n74FtLO3s3u433/v7zTfjlz9F" +
       "5aaBVolmiB7UiRnqHxnGhkmkfgWb5ii8mhTfKa8ant+paj5UJiCf\nLFFUJ4" +
       "hmWMIUh2UpHPlSb9pAPbqmHIwrGg2RNA3tVzbb/HYIm/MY7jlxofHJ04EOHy" +
       "oXUB1WVY1i\nKmvqgEKSJkX1wn48g8MpKithQTZpr4BuI2oq2a+pJsUqNQ+g" +
       "x5FfQBW6yHhS1Ck4wsMgPKxjAyfD\nXHx4mIsFDssMQrGsEqkvIw5mbsydCW" +
       "rb86L51MCkkn0cB3O4BmD1mozVlrV5pur+M+NbDp0660d1\nE6hOVkcYMxEs" +
       "oSBvAtUmSXKKGGafJBFpAjWohEgjxJCxIs9xqROo0ZTjKqYpg5hRYmrKDCNs" +
       "NFM6\nMbhM56WAakVmk5ESqWZkfBSTiSI5v8pjCo6D2U2u2Za529l7MLBaBs" +
       "WMGBaJMyUwLasQ8Q7vjIyN\n3TuBAKYGk4QmtIyogIrhBWq0YqlgNR4eoYas" +
       "xoG0XEuBFIraijJlvtaxOI3jZJKiFi/dsPUJqKq4\nI9gUilZ4yTgniFKbJ0" +
       "pZ8elp+uzIme+9eT/P7YBERIXpXw2TVnsmRUmMGEQViTXxRip0PPJwapUP\n" +
       "ISBe4SG2aPpuvzAm/PXnHRbNygI0Q1P7iUgnxd3HOqKPDWrIz9So1DVTZsHP" +
       "sZyXw7D9pTetQ9U2\nZTiyjyHn48XoLx5+4iXyNx+qjqAKUVNSScijBlFL6r" +
       "JCjEGiEgNTIkVQFVGlfv49goLwLEDKW2+H\nYjGT0AgKKPxVhcZ/g4tiwIK5" +
       "qAqeZTWmOc86pgn+nNYRQkG40L1wrUDWH79T9MVQ2EypMUWbDZuG\nGNaMeO" +
       "a3qBkkHJfDEcPAkozB0f1YTJDByIAaBx1CLI90iibCCS1JwljEqqxqMAEqV9" +
       "TulMgMu98S\n9zTTv3G2rIwBorewFaiJBzVFIsakOH/98qGBnc8csZKGJbpt" +
       "OUU9IDRkCw0xoaG4HCoiFJWVcVnL\nmXArhBCAaShlAL3a9SOP7th3pMsPua" +
       "PPBsB7jLQLbLQ1GhC1frfeIxwaRUi6lu/vPRy6MX+flXTh\n4rBccHbN+69c" +
       "OfWv2g0+5CuMmcxSQO1qxmaYAW0GC7u9VVaI/z+O7nrtoysff86tN4q682Ag" +
       "fyYr\n4y5vTAxNJBIAo8v+dGudfw8aP+ZDAcAGwEOuP0DNaq+MnHLudaCR2R" +
       "IUUE1MM5JYYZ8cPKumCUOb\ndd/wZKnnz8sgODUsv7vg6rATnt/Z1xU6G5us" +
       "5GLR9ljBoffG1yru+u0bNe9wtzgoXZe1Do4QatV8\ng5ssowYh8P7j54e//Z" +
       "1PD+/lmWKnCoXFMTWlyGIaptzhToFiVwBwWCC7x9SkJskxGU8phGXc/+pu\n" +
       "v/vHf/9mvRUaBd44kd14cwbu+9YH0BNXvvLv1ZxNmcgWG9cMl8yyZpnLuQ+K" +
       "5CDTI/3kh+0v/BK/\nCFgI+GPKc4RDSpldBEypZkDGvEIb0rla3NFhTraBjy" +
       "EWCT4Z8W/3sKELhG8sUhsF1v5J8dBL8a7U\ngfde52bV4OwmIjtOu7Dea6UG" +
       "G9Yy9zd7y/tBbCaAbtPF3Y/UKxf/CxwngKMIa645ZADApHOibFOX\nB69eeq" +
       "tp3wd+5NuOqhUNS9sxLxBUBZlJzARgU1q/736efPWzlWzkJiPuhDbbAemsX5" +
       "Wg3Pri+LCd\ndQ5uaU1ObTwjXB56kTugKDIUWDg9fObeHDtx49f0Gufjliib" +
       "3ZnOB17otty5934001Dx6smkDwUn\nUL1o94PjWEmxQpiA9sV0mkToGXO+57" +
       "Yi1rrbm4GgVV54yBLrBQcX8OGZUbPnWg8e1DJvt8DVZONB\nkxcPyhB/6ONT" +
       "uvm4LlO9Qd2QZzDrEVHQxEldgdwwUEt2l27ISVjtZzh+XX+662fvjp08bGF+" +
       "iZjm\nzJoUv/r7P0z7v3VpyprnDZyH+Njq039+7Xp0uYUPVlu4Nq8zy55jtY" +
       "bcM3U6K4XOUhI49ds9nS8/\nHr1ma9SY2+AMwCbgLwffIuu2feOTAmuvH5pX" +
       "C2XZuIkND1j5/vmidbEtN2Kr4Gq2I9ZcJGJRNvRT\nqDsNuimWIZzHoG7JEi" +
       "hLFw17VRlZpCoddgI5iVRIlQlblVpZnRl1tGHvxjzC9y5SeDtcrbbw1iLC\n" +
       "J23h1UnY1kCzDr19IdH7liC6zRbdVkS0lBGN0yVEk0WKZjW60ha9soho2RYd" +
       "MDSNOovRXYvo+rp3\naxLxKLp/kYqus1PVSdlCiuq2on5jVnH0vNtdVgAcxZ" +
       "QBHRANKZo4zVY9eIYEolGCpT2GTIkA7z2K\nHlikopvtgDqBLaTorK1oHRT6" +
       "FFaGExrVVFjxHKXX5zl3MJcw4uxZPcqmSyjLQemOrMUw01i05ouz\nI8cQrL" +
       "3YVpKj1+GH/ln7NH77UZ/dauziKKHfqZAZomQJY0cu7Tmt/C6+eXZXuqNnf3" +
       "iBftCz1cLB\nDcUR3Ttxw9ZTcx1bzz+7hAa+w2Obl3XDzMov+xPyuz6+v7cW" +
       "zrxzgdxJvbnLZTXokzLU0ZxFc01u\nE90N1xY7ZbZ4U4ZH2A2d2+B5usOmvC" +
       "COiLDt5fO/XqI3fI4Nz1C2tZWpF9aDUxpgLFbdDDt6s3Jw\n2i7+46lcQ5lx" +
       "O2xDdyzV0PZ8QxOAOmqcHTRZ9n63hL0n2fA8RQ1xQq2qitqY5QhozhYgJ3Gc" +
       "sD2A\nfcDEnfDCrThBgGvUdsLogp3g4xx9C3PCzSzhYs6WcNJ5NvyAotvASS" +
       "6oL9xB80t0EEfQPriO2w46\nXtRB/R71A5xjoKCOSUwToWENmqV7Cm6m+Pdx" +
       "wjYXfKs0tiAHvl7CgZfY8BPYk8qqCZtxb10FZjRZ\nct114VbctQmuc7a7zi" +
       "3YXZ58WrK7uIRflfDEh2x4b+mpdHmhvoGNQ3ORBoTtu1vyzq2ts1ZRuPrY\n" +
       "I58Jv/kPP0DJnIfWCKgyllKU7J1P1nOFbpCYzA2ssfZBOr/9jqJlBTojinxx" +
       "mWt71aK7RlG9lw7S\ngt2yyT6hqCaLjG2NrKdsoj9CxwNE7PFPmRainvc9bO" +
       "cXsnZ+6RxnMY+szVli+b8QnGUwZf0TYVJ8\n6JW9a9LPjj7H19ZyUcFzc4xN" +
       "tYCC1oFRZintLMrN4fU+evX8+BvnvuC0Cvy8YLl9SpSX5ZusryWC\nD8t34V" +
       "OagaRO+bnK3E+bf7Rt/sQ1Hz8n+j8AA6MQ+RkAAA==");
}
