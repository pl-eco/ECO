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
                                         getBounds(
                                           ).
                                         getExtents(
                                           );
                                       root =
                                         new Node(
                                           scene.
                                             getBounds(
                                               ).
                                             getCenter(
                                               ),
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
                  getShader(
                    ) !=
                  null)
                return state.
                  getShader(
                    ).
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
                  getPoint(
                    ),
                state.
                  getNormal(
                    ));
    }
    public Color getIrradiance(ShadingState state,
                               Color diffuseReflectance) {
        if (samples <=
              0)
            return Color.
                     BLACK;
        if (state.
              getDiffuseDepth(
                ) >
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
              getBasis(
                );
            onb.
              transform(
                w);
            Ray r =
              new Ray(
              state.
                getPoint(
                  ),
              w);
            ShadingState temp =
              state.
              traceFinalGather(
                r,
                0);
            return temp !=
              null
              ? getGlobalRadiance(
                  temp).
              copy(
                ).
              mul(
                (float)
                  Math.
                    PI)
              : Color.
                  BLACK;
        }
        rwl.
          readLock(
            ).
          lock(
            );
        Color irr =
          getIrradiance(
            state.
              getPoint(
                ),
            state.
              getNormal(
                ));
        rwl.
          readLock(
            ).
          unlock(
            );
        if (irr ==
              null) {
            irr =
              Color.
                black(
                  );
            OrthoNormalBasis onb =
              state.
              getBasis(
                );
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
                    getPoint(
                      ),
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
                            getMax(
                              ),
                          minR);
                    invR +=
                      1.0F /
                        r.
                        getMax(
                          );
                    temp.
                      getInstance(
                        ).
                      prepareShadingState(
                        temp);
                    irr.
                      add(
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
              writeLock(
                ).
              lock(
                );
            insert(
              state.
                getPoint(
                  ),
              state.
                getNormal(
                  ),
              invR,
              irr);
            rwl.
              writeLock(
                ).
              unlock(
                );
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
    private final class Node {
        Node[] children;
        Sample first;
        Point3 center;
        float sideLength;
        float halfSideLength;
        float quadSideLength;
        Node(Point3 center, float sideLength) {
            super(
              );
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
                                                     copy(
                                                       ).
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
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "i4vvi6L+vzJNGFDmEwAA");
    }
    private static final class Sample {
        float pix;
        float piy;
        float piz;
        float nix;
        float niy;
        float niz;
        float invR0;
        Color irr;
        Sample next;
        Sample(Point3 p, Vector3 n) { super(
                                        );
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
                                        normalize(
                                          );
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
                              normalize(
                                );
                            nix = ni.x;
                            niy = ni.y;
                            niz = ni.z;
                            invR0 = 1.0F /
                                      r0;
                            this.irr = irr;
                            next = null; }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1425482308000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "WIdhM9FBjbw35521J8a/FX3gv4e6JqIbEgAA");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "Xn/g0sr74pr6X9jnepCzFgAA");
}
