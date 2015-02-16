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
          1166303790000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "Ewhv1hMAAA==");
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
          1166303790000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "oIY+nfHx6uOjl3gd+C+ugmRSCxIAAA==");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1166303790000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1YXWwcVxW+Xv87TvzX2I6T2InrFByXnYYoEcVtwbUcx+4m" +
       "NraTClfUvZ65uzvx/HXmrr1xMDQRVaJIjarilASKH1CqkJI4BTVKEaqUF2ir" +
       "8lKEQDzQIl6oKHnIA6WiQDnnzt/u7K7j8oClub5z55xzz7nnnO+cu1dvk0rH" +
       "Jn2WqR1PaSaPsyyPH9P2xflxiznx0cS+cWo7TBnUqONMwdqM3P1qw0efPJ9u" +
       "jJGqadJCDcPklKum4Uwwx9TmmZIgDeHqkMZ0h5PGxDE6T6UMVzUpoTq8P0E2" +
       "5LBy0pPwVZBABQlUkIQK0kBIBUwbmZHRB5GDGtx5mnyLlCVIlSWjepzszBdi" +
       "UZvqnphxYQFIqMH3o2CUYM7aZEdgu2tzgcHn+6Tl7z3Z+LNy0jBNGlRjEtWR" +
       "QQkOm0yTep3ps8x2BhSFKdOkyWBMmWS2SjV1Ueg9TZodNWVQnrFZcEi4mLGY" +
       "LfYMT65eRtvsjMxNOzAvqTJN8d8qkxpNga2toa2uhQdwHQysU0ExO0ll5rNU" +
       "zKmGwklXlCOwsecxIADWap3xtBlsVWFQWCDNru80aqSkSW6rRgpIK80M7MJJ" +
       "R0mheNYWledois1w0h6lG3c/AVWtOAhk4WRzlExIAi91RLyU45/bhx86d8I4" +
       "aMSEzgqTNdS/Bpg6I0wTLMlsZsjMZazfnXiRtr5xJkYIEG+OELs0N79556v3" +
       "d956y6XZWoRmbPYYk/mMfGl207vbBnsfLEc1aizTUdH5eZaL8B/3vvRnLci8" +
       "1kAifoz7H29N/Orrz7zCPoyRuhFSJZtaRoc4apJN3VI1Zg8zg9mUM2WE1DJD" +
       "GRTfR0g1zBOqwdzVsWTSYXyEVGhiqcoU73BESRCBR1QNc9VImv7cojwt5lmL" +
       "EFIND/kSPJuJ+yf+c3JMOuJAuEtUpoZqmBIEL6O2nJaYbM7MwummdWrPOZKc" +
       "cbipS07GSGrmguTYsmTaqeBdNm0mpVRpxLapolJwyiCV02x4ZMhIgb5xjDnr" +
       "/7pbFm1vXCgrA7dsi4KCBvl00NQUZs/Iy5lHh+6szrwTC5LEOzVO+mDTuLdp" +
       "HDeNp9R4iU1JWZnY6x7c3HU/OG8OYAAAsr538hujT53pLoe4sxYq4OSRtBus" +
       "9jQaks3BECtGBCLKELDtP3ridPzjy19xA1YqDexFucmtCwsnj377gRiJ5SM0" +
       "WghLdcg+jrga4GdPNDOLyW04/cFH119cMsMczYN8DzoKOTH1u6O+sE2ZKQCm" +
       "ofjdO+iNmTeWemKkAvAEMJRTOGCAp87oHnkQ0O/DKdpSCQYnTVunGn7yMbCO" +
       "p21zIVwRQbJJzJvAKRswJ7rh6fKSRPzHry0Wjve4QYVejlgh4PrAz29dvPH9" +
       "vgdjucjekFMrJxl3caIpDJIpmzFY/+OF8e+ev336CREhQHFvsQ16cBwE1ACX" +
       "wbE++9bTf3j/vUu/jYVRxaF8ZmY1Vc6CjPvCXQBTNMA19H3PEUM3FTWp0lmN" +
       "YXD+q2HXnht/O9foelODFT8Y7r+7gHB9y6PkmXee/EenEFMmY00LLQ/J3ANo" +
       "CSUPQD4dRz2yJ3+z/eKb9IcAuQBzjrrIBHKVefmCSm0GAC7IyTFLqCV8Iwmy" +
       "3WKMo/MEMxHf9uKwwyr4lhUr7eKtBnTrLZ1lB7B252TnP8e02VN//liYXJBf" +
       "RUpWhH9auvpSx+AjHwr+MNCRuytbCFvQ54S8X3xF/3usu+qXMVI9TRplr4k6" +
       "SrUMhtM0NA6O31lBo5X3Pb8JcCtef5DI26JJlrNtNMVCuIQ5UuO8LpJV9XjK" +
       "7fC0elnVGs2qMiIm/YKlW4y7cPi8H9TVlq3OU+zQSLVDdUtjztqOGrdVHUrv" +
       "vNcbSEvN78+99ME1F0ajXokQszPLZz+Nn1uO5XRb9xY0PLk8bsclzN7omv0p" +
       "/JXB8x980FxccCtu86BX9ncEdd+yMF13rqWW2OLAX64v/eLHS6ddM5rzm40h" +
       "6KWv/e7fv45f+NPbRWpZOTSSAr3cBNif755t8LR57mkr4Z7HcHiYk1pugvYY" +
       "DkLGAA5DbhINc4wPk66xU5cXDH5QFNvpa95O9aoxP+VvhmuHS8vdDs8WT+6W" +
       "EnKnPLl1OvT90M1C87sOqR2e1I4SUh8PpNLs+qRiAmz1pG4tIXXak1phmyb3" +
       "we+Bz9CQ9Bw2FVZah895Xve9X0yHGU+HcntB81XYEyI3gIqcsaH+8rhmynPY" +
       "kcMcnMUnGFUet1XOErBeWod93gn7J11MB8XToQEifZZq42mTm8Yhavn69BYc" +
       "yXA+4Yh/gXL1EFnakwP5QWXZUijJO0rMzu2lriwiMy+dWl5Rxl7eE/NqzUGR" +
       "JtYXNDbPtJzN8Hq+Pa/tOyQuaSGun73yk5v83b4vuzm+uzTERRnfPPXXjqlH" +
       "0k99hmavK2JTVOSVQ1ffHr5PfiFGyoPyUHDvzGfqzy8KdTaDi7IxlVcaOoMo" +
       "aPHxZr8XBfuLNlyhy8LKHmkLWgucNykDvAr+hTWaghM4cI5XJ5UXw7PqWRMQ" +
       "iBqFfYNYsAJb8BHN46hny+j/asv2QlvSkOFGCn+rcE36zhomncHhJCdNKcbd" +
       "XJjw8MHfoC13A1WHizv2d6a9Phs74ZnybJxat40xITGGr8/icFaQPr+GHS/g" +
       "8BwnG8GOEOME710VFfAyAM+yp+hySUUfjqhQISRWFD0rHe7UcKuHUrq3aD8q" +
       "vh9leOfZG9QA19ILa1i6gsN56N1VA67HRcOwYt5Ulbua3YyLWGFXPbNX1212" +
       "jn8u4vADQXp5Da2v4HBpnf6Bzq2tRKHC+0B7wc927k9N8upKQ03bypHfi7tg" +
       "8HNQbYLUJDOaltt+5syrLJslVaFkrduMurC/yklLkQrKSSylCo2vuXQ/5aQx" +
       "SgcuwH+5ZK9xsiGHDHtTd5ZLdBPKJxDh9PWgaDWKIortd9xtv7Mkp0jgDTD3" +
       "Le86iHVA/BTqY3bG/TF0Rr6+Mnr4xJ39L4sCUClrdHERpdQkSLV7Ew5wf2dJ" +
       "ab6sqoO9n2x6tXaXX8824dDsXX8junUVvyUO6RYX97rF19tee+jyynvimvpf" +
       "x1jryqMWAAA=");
}
