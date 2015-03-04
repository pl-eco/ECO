package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.Instance;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.Solvers;
import org.sunflow.math.Vector3;
public class JuliaFractal implements PrimitiveList {
    private static float BOUNDING_RADIUS = (float) Math.sqrt(3);
    private static float BOUNDING_RADIUS2 = 3;
    private static float ESCAPE_THRESHOLD = 10.0F;
    private static float DELTA = 1.0E-4F;
    private float cx;
    private float cy;
    private float cz;
    private float cw;
    private int maxIterations;
    private float epsilon;
    public JuliaFractal() { super();
                            cw = -0.4F;
                            cx = 0.2F;
                            cy = 0.3F;
                            cz = -0.2F;
                            maxIterations = 15;
                            epsilon = 1.0E-5F; }
    public int getNumPrimitives() { return 1; }
    public float getPrimitiveBound(int primID, int i) { return (i & 1) ==
                                                          0
                                                          ? -BOUNDING_RADIUS
                                                          : BOUNDING_RADIUS;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { BoundingBox bounds =
                                                       new BoundingBox(
                                                       BOUNDING_RADIUS);
                                                     if (o2w != null) bounds =
                                                                        o2w.
                                                                          transform(
                                                                            bounds);
                                                     return bounds;
    }
    public void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        float qc =
          r.
            ox *
          r.
            ox +
          r.
            oy *
          r.
            oy +
          r.
            oz *
          r.
            oz -
          BOUNDING_RADIUS2;
        float qt =
          r.
          getMin();
        if (qc >
              0) {
            float qa =
              r.
                dx *
              r.
                dx +
              r.
                dy *
              r.
                dy +
              r.
                dz *
              r.
                dz;
            float qb =
              2 *
              (r.
                 dx *
                 r.
                   ox +
                 r.
                   dy *
                 r.
                   oy +
                 r.
                   dz *
                 r.
                   oz);
            double[] t =
              Solvers.
              solveQuadric(
                qa,
                qb,
                qc);
            if (t ==
                  null ||
                  t[0] >=
                  r.
                  getMax() ||
                  t[1] <=
                  r.
                  getMin())
                return;
            qt =
              (float)
                t[0];
        }
        float dist =
          Float.
            POSITIVE_INFINITY;
        float rox =
          r.
            ox +
          qt *
          r.
            dx;
        float roy =
          r.
            oy +
          qt *
          r.
            dy;
        float roz =
          r.
            oz +
          qt *
          r.
            dz;
        float invRayLength =
          (float)
            (1 /
               Math.
               sqrt(
                 r.
                   dx *
                   r.
                     dx +
                   r.
                     dy *
                   r.
                     dy +
                   r.
                     dz *
                   r.
                     dz));
        while (true) {
            float zw =
              rox;
            float zx =
              roy;
            float zy =
              roz;
            float zz =
              0;
            float zpw =
              1;
            float zpx =
              0;
            float zpy =
              0;
            float zpz =
              0;
            float dotz =
              0;
            for (int i =
                   0;
                 i <
                   maxIterations;
                 i++) {
                {
                    float nw =
                      zw *
                      zpw -
                      zx *
                      zpx -
                      zy *
                      zpy -
                      zz *
                      zpz;
                    float nx =
                      zw *
                      zpx +
                      zx *
                      zpw +
                      zy *
                      zpz -
                      zz *
                      zpy;
                    float ny =
                      zw *
                      zpy +
                      zy *
                      zpw +
                      zz *
                      zpx -
                      zx *
                      zpz;
                    zpz =
                      2 *
                        (zw *
                           zpz +
                           zz *
                           zpw +
                           zx *
                           zpy -
                           zy *
                           zpx);
                    zpw =
                      2 *
                        nw;
                    zpx =
                      2 *
                        nx;
                    zpy =
                      2 *
                        ny;
                }
                {
                    float nw =
                      zw *
                      zw -
                      zx *
                      zx -
                      zy *
                      zy -
                      zz *
                      zz +
                      cw;
                    zx =
                      2 *
                        zw *
                        zx +
                        cx;
                    zy =
                      2 *
                        zw *
                        zy +
                        cy;
                    zz =
                      2 *
                        zw *
                        zz +
                        cz;
                    zw =
                      nw;
                }
                dotz =
                  zw *
                    zw +
                    zx *
                    zx +
                    zy *
                    zy +
                    zz *
                    zz;
                if (dotz >
                      ESCAPE_THRESHOLD)
                    break;
            }
            float normZ =
              (float)
                Math.
                sqrt(
                  dotz);
            dist =
              0.5F *
                normZ *
                (float)
                  Math.
                  log(
                    normZ) /
                JuliaFractal.
                length(
                  zpw,
                  zpx,
                  zpy,
                  zpz);
            rox +=
              dist *
                r.
                  dx;
            roy +=
              dist *
                r.
                  dy;
            roz +=
              dist *
                r.
                  dz;
            qt +=
              dist;
            if (dist *
                  invRayLength <
                  epsilon)
                break;
            if (rox *
                  rox +
                  roy *
                  roy +
                  roz *
                  roz >
                  BOUNDING_RADIUS2)
                return;
        }
        if (!r.
              isInside(
                qt))
            return;
        if (dist *
              invRayLength <
              epsilon) {
            r.
              setMax(
                qt);
            state.
              setIntersection(
                0,
                0,
                0);
        }
    }
    public void prepareShadingState(ShadingState state) { state.init();
                                                          state.getRay().
                                                            getPoint(
                                                              state.
                                                                getPoint());
                                                          Instance parent =
                                                            state.
                                                            getInstance();
                                                          Point3 p =
                                                            parent.
                                                            transformWorldToObject(
                                                              state.
                                                                getPoint());
                                                          float gx1w =
                                                            p.
                                                              x -
                                                            DELTA;
                                                          float gx1x =
                                                            p.
                                                              y;
                                                          float gx1y =
                                                            p.
                                                              z;
                                                          float gx1z =
                                                            0;
                                                          float gx2w =
                                                            p.
                                                              x +
                                                            DELTA;
                                                          float gx2x =
                                                            p.
                                                              y;
                                                          float gx2y =
                                                            p.
                                                              z;
                                                          float gx2z =
                                                            0;
                                                          float gy1w =
                                                            p.
                                                              x;
                                                          float gy1x =
                                                            p.
                                                              y -
                                                            DELTA;
                                                          float gy1y =
                                                            p.
                                                              z;
                                                          float gy1z =
                                                            0;
                                                          float gy2w =
                                                            p.
                                                              x;
                                                          float gy2x =
                                                            p.
                                                              y +
                                                            DELTA;
                                                          float gy2y =
                                                            p.
                                                              z;
                                                          float gy2z =
                                                            0;
                                                          float gz1w =
                                                            p.
                                                              x;
                                                          float gz1x =
                                                            p.
                                                              y;
                                                          float gz1y =
                                                            p.
                                                              z -
                                                            DELTA;
                                                          float gz1z =
                                                            0;
                                                          float gz2w =
                                                            p.
                                                              x;
                                                          float gz2x =
                                                            p.
                                                              y;
                                                          float gz2y =
                                                            p.
                                                              z +
                                                            DELTA;
                                                          float gz2z =
                                                            0;
                                                          for (int i =
                                                                 0;
                                                               i <
                                                                 maxIterations;
                                                               i++) {
                                                              {
                                                                  float nw =
                                                                    gx1w *
                                                                    gx1w -
                                                                    gx1x *
                                                                    gx1x -
                                                                    gx1y *
                                                                    gx1y -
                                                                    gx1z *
                                                                    gx1z +
                                                                    cw;
                                                                  gx1x =
                                                                    2 *
                                                                      gx1w *
                                                                      gx1x +
                                                                      cx;
                                                                  gx1y =
                                                                    2 *
                                                                      gx1w *
                                                                      gx1y +
                                                                      cy;
                                                                  gx1z =
                                                                    2 *
                                                                      gx1w *
                                                                      gx1z +
                                                                      cz;
                                                                  gx1w =
                                                                    nw;
                                                              }
                                                              {
                                                                  float nw =
                                                                    gx2w *
                                                                    gx2w -
                                                                    gx2x *
                                                                    gx2x -
                                                                    gx2y *
                                                                    gx2y -
                                                                    gx2z *
                                                                    gx2z +
                                                                    cw;
                                                                  gx2x =
                                                                    2 *
                                                                      gx2w *
                                                                      gx2x +
                                                                      cx;
                                                                  gx2y =
                                                                    2 *
                                                                      gx2w *
                                                                      gx2y +
                                                                      cy;
                                                                  gx2z =
                                                                    2 *
                                                                      gx2w *
                                                                      gx2z +
                                                                      cz;
                                                                  gx2w =
                                                                    nw;
                                                              }
                                                              {
                                                                  float nw =
                                                                    gy1w *
                                                                    gy1w -
                                                                    gy1x *
                                                                    gy1x -
                                                                    gy1y *
                                                                    gy1y -
                                                                    gy1z *
                                                                    gy1z +
                                                                    cw;
                                                                  gy1x =
                                                                    2 *
                                                                      gy1w *
                                                                      gy1x +
                                                                      cx;
                                                                  gy1y =
                                                                    2 *
                                                                      gy1w *
                                                                      gy1y +
                                                                      cy;
                                                                  gy1z =
                                                                    2 *
                                                                      gy1w *
                                                                      gy1z +
                                                                      cz;
                                                                  gy1w =
                                                                    nw;
                                                              }
                                                              {
                                                                  float nw =
                                                                    gy2w *
                                                                    gy2w -
                                                                    gy2x *
                                                                    gy2x -
                                                                    gy2y *
                                                                    gy2y -
                                                                    gy2z *
                                                                    gy2z +
                                                                    cw;
                                                                  gy2x =
                                                                    2 *
                                                                      gy2w *
                                                                      gy2x +
                                                                      cx;
                                                                  gy2y =
                                                                    2 *
                                                                      gy2w *
                                                                      gy2y +
                                                                      cy;
                                                                  gy2z =
                                                                    2 *
                                                                      gy2w *
                                                                      gy2z +
                                                                      cz;
                                                                  gy2w =
                                                                    nw;
                                                              }
                                                              {
                                                                  float nw =
                                                                    gz1w *
                                                                    gz1w -
                                                                    gz1x *
                                                                    gz1x -
                                                                    gz1y *
                                                                    gz1y -
                                                                    gz1z *
                                                                    gz1z +
                                                                    cw;
                                                                  gz1x =
                                                                    2 *
                                                                      gz1w *
                                                                      gz1x +
                                                                      cx;
                                                                  gz1y =
                                                                    2 *
                                                                      gz1w *
                                                                      gz1y +
                                                                      cy;
                                                                  gz1z =
                                                                    2 *
                                                                      gz1w *
                                                                      gz1z +
                                                                      cz;
                                                                  gz1w =
                                                                    nw;
                                                              }
                                                              {
                                                                  float nw =
                                                                    gz2w *
                                                                    gz2w -
                                                                    gz2x *
                                                                    gz2x -
                                                                    gz2y *
                                                                    gz2y -
                                                                    gz2z *
                                                                    gz2z +
                                                                    cw;
                                                                  gz2x =
                                                                    2 *
                                                                      gz2w *
                                                                      gz2x +
                                                                      cx;
                                                                  gz2y =
                                                                    2 *
                                                                      gz2w *
                                                                      gz2y +
                                                                      cy;
                                                                  gz2z =
                                                                    2 *
                                                                      gz2w *
                                                                      gz2z +
                                                                      cz;
                                                                  gz2w =
                                                                    nw;
                                                              }
                                                          }
                                                          float gradX =
                                                            JuliaFractal.
                                                            length(
                                                              gx2w,
                                                              gx2x,
                                                              gx2y,
                                                              gx2z) -
                                                            JuliaFractal.
                                                            length(
                                                              gx1w,
                                                              gx1x,
                                                              gx1y,
                                                              gx1z);
                                                          float gradY =
                                                            JuliaFractal.
                                                            length(
                                                              gy2w,
                                                              gy2x,
                                                              gy2y,
                                                              gy2z) -
                                                            JuliaFractal.
                                                            length(
                                                              gy1w,
                                                              gy1x,
                                                              gy1y,
                                                              gy1z);
                                                          float gradZ =
                                                            JuliaFractal.
                                                            length(
                                                              gz2w,
                                                              gz2x,
                                                              gz2y,
                                                              gz2z) -
                                                            JuliaFractal.
                                                            length(
                                                              gz1w,
                                                              gz1x,
                                                              gz1y,
                                                              gz1z);
                                                          Vector3 n =
                                                            new Vector3(
                                                            (float)
                                                              gradX,
                                                            (float)
                                                              gradY,
                                                            (float)
                                                              gradZ);
                                                          state.
                                                            getNormal().
                                                            set(
                                                              parent.
                                                                transformNormalObjectToWorld(
                                                                  n));
                                                          state.
                                                            getNormal().
                                                            normalize();
                                                          state.
                                                            getGeoNormal().
                                                            set(
                                                              state.
                                                                getNormal());
                                                          state.
                                                            setBasis(
                                                              OrthoNormalBasis.
                                                                makeFromW(
                                                                  state.
                                                                    getNormal()));
                                                          state.
                                                            getPoint().
                                                            x +=
                                                            state.
                                                              getNormal().
                                                              x *
                                                              epsilon *
                                                              20;
                                                          state.
                                                            getPoint().
                                                            y +=
                                                            state.
                                                              getNormal().
                                                              y *
                                                              epsilon *
                                                              20;
                                                          state.
                                                            getPoint().
                                                            z +=
                                                            state.
                                                              getNormal().
                                                              z *
                                                              epsilon *
                                                              20;
                                                          state.
                                                            setShader(
                                                              parent.
                                                                getShader(
                                                                  0));
                                                          state.
                                                            setModifier(
                                                              parent.
                                                                getModifier(
                                                                  0));
    }
    private static float length(float w, float x,
                                float y,
                                float z) { return (float)
                                                    Math.
                                                    sqrt(
                                                      w *
                                                        w +
                                                        x *
                                                        x +
                                                        y *
                                                        y +
                                                        z *
                                                        z);
    }
    public boolean update(ParameterList pl, SunflowAPI api) {
        maxIterations =
          pl.
            getInt(
              "iterations",
              maxIterations);
        epsilon =
          pl.
            getFloat(
              "epsilon",
              epsilon);
        cw =
          pl.
            getFloat(
              "cw",
              cw);
        cx =
          pl.
            getFloat(
              "cx",
              cx);
        cy =
          pl.
            getFloat(
              "cy",
              cy);
        cz =
          pl.
            getFloat(
              "cz",
              cz);
        return true;
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169362820000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKUZC2wUx3Xu4w+2U38wxhiDsSGhMeSuoNAWHAmMP3Bw2I7P" +
       "NmCSHuO9ufPC3u5m\nd84+OzQkahtoojZBSdRGaoC2SEA+JVLa0khpAkrSUF" +
       "CrpJ+0QgpJi9RGKqmIoqY0/ahvZndv9/Y+\nGGNpZud23v83b8fPf4RKdA01" +
       "C3qATqlED3RFBrCmk1iXhHV9CF5FhTdL5gwc3yorXuQJI68Yo6g6\nLOjBGK" +
       "Y4KMaCoe6OtIZWqIo0lZAUGiBpGtgjrTHpbQmvySG4/fDpuoeO+Vu8qCSMqr" +
       "EsKxRTUZF7\nJJLUKaoJ78ETOJiiohQMizrtCKNbiJxKdimyTrFM9fvQA8gX" +
       "RqWqwGhS1Bq2mAeBeVDFGk4GOfvg\nAGcLFOZqhGJRJrHODDvAXJmNCWKbeI" +
       "O50ECknG2OgDpcAtB6SUZrQ9scVVXfiZEv7jt60oeqR1G1\nKEcYMQE0ocBv" +
       "FFUlSXKMaHpnLEZio6hWJiQWIZqIJXGacx1FdbqYkDFNaUQfJLoiTTDAOj2l" +
       "Eo3z\ntF6GUZXAdNJSAlW0jI3iIpFi1q+SuIQToHaDrbahbi97DwpWiCCYFs" +
       "cCsVD8e0UZPN7ixsjouGwr\nAABqWZLQcSXDyi9jeIHqDF9KWE4EI1QT5QSA" +
       "ligp4EJRU0GizNYqFvbiBIlS1OiGGzC2AGoONwRD\noWieG4xTAi81ubzk8M" +
       "+Khk8Pnvjeqxt4bPtjRJCY/BWAtNiFNEjiRCOyQAzEa6nAk6GdqWYvQgA8\n" +
       "zwVswHTeeno4/OFrLQbMwjww/WN7iECjQt+hlsH7NynIx8QoVxVdZM7P0pyn" +
       "w4C505FWIWsbMhTZ\nZsDaPDP4i50PPkv+5kUVIVQqKFIqCXFUKyhJVZSIto" +
       "nIRMOUxEJoDpFjXXw/hMpgHYaQN972x+M6\noSHkl/irUoX/BhPFgQQz0RxY" +
       "i3JcsdYqpuN8nVYRQmUw0GoYtcj440+K1gWCekqOS8pkUNeEoKIl\nMr8FRS" +
       "NBVROToMMECW5JSSLu1bBAsRRgMaRSNBIcV5IkiAUsi7ISTIiQtYJyR4xMsO" +
       "esKaeZ3HWT\nHg8rhO6EliAXNitSjGhR4fjl8/t6tn7zoBEsLMBNjSlaDgwD" +
       "JsMAYxjIMAw4GSKPh/OpZ4wNt4HR\n90L6QqGruj1y75bdB9t8EC/qpB8sxk" +
       "DbQDdTmh5B6bJzPMTLoQCB1viDXQcC146vNwItWLgU58Wu\nfPuFC0c/qWr3" +
       "Im/+Osm0hEpdwcgMsOKaqX/L3JmVj/7fH9n20rsX3vu8nWMULctJ/VxMlrpt" +
       "bn9o\nikBiUAxt8scWVPu2o5FDXuSHegA1kMsP5WWxm0dWCndY5ZDpUhZGlX" +
       "FFS2KJbVk1rIKOa8qk/YYH\nSg1fzwXnVLKYng+jxQxy/mS781Q2NxiBxbzt" +
       "0oKX22tfK/3CH16pfJObxarM1Y6zL0Kokee1drAM\naYTA+/e+O/DEUx8d2M" +
       "UjxQwVCgdiakwShTSg3GajQIJLUGSYI5cNy0klJsZFPCYRFnH/qb511U+u\n" +
       "fLvGcI0EbyzPrrw+Afv9go3owQtf+ediTsYjsAPGVsMGM7SZa1Pu1DQ8xeRI" +
       "P/SbRU+/hZ+B+gc1\nRxenCS8jiGuGuB2D3O7tfA649laxqQ1orywQ+nmO86" +
       "iw79lEW+q+X77Mpa7Ezr7A6YZtWO0wPM+m\npcy6893Zuxnr4wB355m+e2qk" +
       "M/8GiqNAUYBjVO/XoHaks5xoQpeUXTz7esPud3zI24sqJAXHejGP\nfzQHAo" +
       "/o41B20ur6DTy2aibL2cxVRtwITaYB0o5fFSDc7YXTv5c1A3bmRMdWngif73" +
       "+GG6Bg4uc5\nC110pl8dPnztV/QSp2NnIMNuTefWVGigbNwvvztRW/rikaQX" +
       "lY2iGsFs8UawlGJxPgodiW71fdAG\nZu1ndxfGUdqRqTDN7ux3sHXnvl3LYc" +
       "2g2brKle5sIOaMOjPd69zp7kF8sZ6jLOPzciM5vRSVwYEw\nAccupKnOm8A0" +
       "hNnG/uG+7lDfpuhgZ3doOALea3R24tYRwkrQ5Yfbfn5u+MgBo8YXcXIWVlTY" +
       "//4H\ne32PnR0z8NyedAEfWnzsLy9dHqw36oHR+i3N6b6cOEb7x01VrbLcaC" +
       "3GgUO/saL1+QcGL5kS1WU3\nMT3Q6P916nWy/K5v/SnPOQtuUzA16iqbV7Np" +
       "g5ECawqmyrpsJwashfXM48S78ziRrbvY1A2+q3H5\njkuyxSXZ4A1KtgpGvS" +
       "lZfQHJdlxXsp5IV+dAT3Ro82BPZHN/uDufZDtvULLlMOaZks0rIFn0epKV\n" +
       "dPeEhzrzibN75uJUWSI0muI0FhAnniuOh4sDkniFdD4xErMQY4EpxoICYiTZ" +
       "NM5YTuVjKc+CZZPJ\nsqkASy3DcjofS30WLBeaLBcWYDmZYTmZj2X6Blkuht" +
       "FssmwuwHKfyfKWJE6HKPumYWcWp9OrGvy2\nUuSDT1qXMF+9QWGYmReZwiwq" +
       "IMzXTWHKiKqLkiLnM8I3ivDl4Xib4yj38PV8aGNzPi0yJZW3TlBz\nFxX6wO" +
       "X19sCOj6sexm/c6zW7pTCFBkNR75DIBJEcHMsZpayPjW38k94+rB85+dxp+s" +
       "6KdUblbi98\nBrkR29cdnW5Zd+rRWXxitLh0c5OunVh4t29cPOfltw7G2Z9z" +
       "W5GN1JF94leAPClNHso695dkt/mt\nMNrNEGh3hwB3s+2//D3qd4rsPc2mJ6" +
       "FyJwjtSyUz/uXA++0Aeup6gWv1hPzH49kqrEHGyYes54xU\n8NplfD+fOOj3" +
       "iyjzQzYdpqgWlMloslFJyTGeE7Y2R25Gm9XmsNYz08aTnVqNztRKYjoe2Iap" +
       "Jqbv\n5BSeK6LkKTadoOhzoOR2RZNiXEHdItycQ5jvi3Jio5K2LXDyZizQB2" +
       "OtaYG1M7aAj1P0WYLW5xSX\nQTxlRx1AtOVAhNgdoW580rH7TMK5vVzEWq+x" +
       "6afQr4sWanaIO8q1f0IRY7aFTt+Mhb4E46BpoYOz\njZFFOfpHxjHzpK36+S" +
       "Kq/5pNb1E0V9WIijXiRGZbZ21dz81SV96fdcG4Yup6Ja+uxZszlwp+DuDP\n" +
       "nGKOiVP7YxGVL7Lpt/CdIxE5YdwKOrL+dzfj0Q4YV00tr87Yo2YNK3KgshOH" +
       "QGiyA9UCa3CCRYxn\n50CIs/lzEfU/ZNP7oH5KjYGT3QFeNqYoEsGybZEPbs" +
       "YijPtnpkU+m7FFnPJ+UmTvH2y6CtELZW4j\nhu/BhJ24M+xPbD0/nqme0J9X" +
       "OS9N2a1RY85/Woz/Dgjhi/ff82n49//i13+ZG/zKMCqPpyTJ+WHv\nWJdCNs" +
       "ZFrmKl8Zmv8sf/KGoqfJMLfZOaVbf+y7E8Hji53VhQydjDCeanqNIBBqFgrp" +
       "xAZdCxAhBb\nlquWiWv43RG75ggY1xzpLHsx+yzNasb4v8Cshill/BMsKux4" +
       "YdeS9KNDj/MurESQ8DT/PKgIozLj\n8jPTdLUWpGbRehu9eGrklR+ttZpKfj" +
       "lW76g0WUG72tgt4n9o9PLfOPYkVcrvCKd/Nv/Hdx0/fMnL\n7zz/Dz8Sqia5" +
       "HAAA");
}
