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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("H4sIAAAAAAAAALVYfWwcxRWfW9vnDxzbcb5MmjiJ7aA6hltCm7bUETRcncTh" +
           "SKw4ROIQHOPdubuN93Y3u3P2xakLiYQSAYoQGBoQ+A8UvkOCEGkSAij/8BHx" +
           "IYEQqH+UQP8pahqJ/MGHoC19M7N7u7d36yp/1NLMzc689+a9ee/95o2PXkQN" +
           "jo0GLFPfk9NNmiAlmtilr0vQPRZxEltS60aw7RA1qWPH2QFzGaXn5fZvf3ww" +
           "3yGheBotwIZhUkw103C2E8fUJ4iaQu3+7JBOCg5FHaldeALLRarpckpz6GAK" +
           "XRFgpagv5akggwoyqCBzFeQNPhUwzSNGsZBkHNigzm70RxRLobilMPUoWlUp" +
           "xMI2LrhiRrgFIKGJfe8EozhzyUYry7YLm6sMfmRAnvnTnR2v1KH2NGrXjFGm" +
           "jgJKUNgkjVoLpDBGbGeDqhI1jeYbhKijxNawrk1xvdOo09FyBqZFm5QPiU0W" +
           "LWLzPf2Ta1WYbXZRoaZdNi+rEV31vhqyOs6BrYt9W4WFG9k8GNiigWJ2FivE" +
           "Y6kf1wyVohVhjrKNfTcDAbA2FgjNm+Wt6g0ME6hT+E7HRk4epbZm5IC0wSzC" +
           "LhQtjRTKztrCyjjOkQxFXWG6EbEEVM38IBgLRYvCZFwSeGlpyEsB/1zcuv7Q" +
           "XmOzIXGdVaLoTP8mYOoOMW0nWWITQyGCsXVN6lG8+I2DEkJAvChELGhO/uHS" +
           "767uPvuuoPlZDZptY7uIQjPKkbG2j5Yl+6+vY2o0WaajMedXWM7Df8RdGSxZ" +
           "kHmLyxLZYsJbPLv97dvueYFckFDLMIorpl4sQBzNV8yCpenE3kQMYmNK1GHU" +
           "TAw1ydeHUSOMU5pBxOy2bNYhdBjV63wqbvJvOKIsiGBH1Ahjzcia3tjCNM/H" +
           "JQsh1AkNLYH2ZyT++C9FGTlvFoiMFWxohilD7BJsK3mZKKbs4IKlg9ecopHV" +
           "zUnZsRXZtHPlb8W0iZzT5GHbxqqGwRNJrOTJpuEhIwdKJligWf//LUrMyo7J" +
           "WAwcsCyc/jpkzmZTV4mdUWaKNw1dOpZ5Tyqng3s+FF0LmybcTRNs00ROS0Rs" +
           "2rfVVAmKxfiGC5kGwtvgq3HIesDD1v7RO7bcdbCnDsLMmqyHg2akPWCwq9aQ" +
           "YiZ9aBjmAKhAfHY9dfuBxPfP3ijiU47G8Zrc6OzhyX07775WQlIlIDMzYaqF" +
           "sY8wGC3DZV84EWvJbT/w1bfHH502/ZSsQHgXKao5Wab3hB1imwpRATt98WtW" +
           "4hOZN6b7JFQP8AGQSTGcMqBRd3iPiowf9NCT2dIABmdNu4B1tuRBXgvN2+ak" +
           "P8MjpY2P54NTmlgKtEJ7080J/stWF1isXygii3k5ZAVH542nzz524vGB66Ug" +
           "kLcHrsZRQgUszPeDZIdNCMz/9fDIw49cPHA7jxCg6K21QR/rkwAS4DI41nvf" +
           "3f2X858f+UQqRxUqAetVvnBADh3Qi7m871ajYKpaVsNjOmEx+a/21WtP/PNQ" +
           "h3CiDjNeDFz9vwX481fehO55787vurmYmMJuLt9gn0zYvcCXvAFyaQ/To7Tv" +
           "4+WPvYOfBGAFMHO0KcLxSeIGSdw3iyhaEszHAiAZYCnchL8Aof1zVDe2VgDA" +
           "nXBvBHm68/z4E1+9JLIpfH2EiMnBmft+ShyakQJ3bG/VNRfkEfcsj5F5IqZ+" +
           "gr8YtP+wxvzDJgTOdiZdsF9ZRnvLYu5bNZdafIuNfz8+fea56QPCjM7KK2YI" +
           "KqiXPv33+4nDX5yrgWsQliamXMcE17Gf99cwpdwQYt/rWbfSqlor8Zku/hWf" +
           "++w3srImgGQ/bNPH9v/te65TFRbVcEeIPy0ffWJp8oYLnN8HBca9olSN81AC" +
           "+rzXvVD4RuqJvyWhxjTqUNz6cifWiyz10lBTOV7RCTVoxXplfSSKgcEy6C0L" +
           "x0Ng2zAc+X6AMaNm45ZaCNQF7aSLQCfDCBRDfPB71vVR1KTkNV0FDARnrI52" +
           "Bs82Edezz/R+ePds75dwkGnUpDmg8gY7V6MGC/B8ffT8hY/nLT/GEbl+DDtC" +
           "+XDxWl2bVpSc3NbWarSNtJWjrWVZSAw4542V/IugnXL5T0Wc1c3uWTVkNduh" +
           "HqJcdzk3/CgvS6LVWAzttKvG6Qg1Rlw14gphx8S+5GiJV0J7zZX4WoTEUVdi" +
           "i6OpJEWMnCjwfhktdTm0M67UMxFSd7pS2/JYz45ejuTXXcmvR0i+zZO8u4jV" +
           "mpIFvvTwfjXrfi6uAYoaLVubAJTkbjSwHkQifu0tj3pCcMw8sn9mVt329FrJ" +
           "BbjfgB/cl50vR2JiKoqyW3j4+khy3/MvnqQfDfxWQO+a6IQLM76z/x9Ld9yQ" +
           "v+sySrEVIYPCIp+/5ei5TVcpD0morgxIVY/ASqbBShhqsQm8Wo0dFWDUXfYr" +
           "C2rUDe2c69dztcuhmi6LsWGmFLpjYv6azLmNOS4hvolGGUiBARAunGot69YJ" +
           "wb+GuBgzTZ1go/qu4hOkbM1CD1o/cK35oKY1rBuP1norp9ozh9Z7WTdB2TvM" +
           "UHlo17hGYZk9GjwsGrgMLIIY7ar6n4Z4hyvHZtublsze+pnAae+t3AwP1mxR" +
           "14MXUGAct2yS1bjuzeI6svjPfooW1NCLIimnccX3Cbp7KeoI04F97CdIdpCi" +
           "KwJk4Dl3FCS6n6I6IGLDByzvdDp41cgu4IS4gEuoIvmtMBT0VuQl/z+Rl0NF" +
           "8Z+ijHJ8dsvWvZd+9TRPyAZFx1NTTEoT3Fni3VDOw1WR0jxZ8c39P7a93Lza" +
           "A5c21nUGsiOg24raxfVQwaK8HJ46teTV9c/Ofs6L+v8CpBcqk8ATAAA=");
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
          1425485134000L;
        public static final String jlc$ClassType$jl7 =
          ("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" +
           "gf8CrcYBOvURAAA=");
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
