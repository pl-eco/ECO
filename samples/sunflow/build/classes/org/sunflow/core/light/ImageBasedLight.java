package org.sunflow.core.light;
import org.sunflow.SunflowAPI;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.LightSample;
import org.sunflow.core.LightSource;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Bitmap;
import org.sunflow.image.Color;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Point3;
import org.sunflow.math.QMC;
import org.sunflow.math.Vector3;
public class ImageBasedLight implements PrimitiveList, LightSource, Shader {
    private Texture texture;
    private OrthoNormalBasis basis;
    private int numSamples;
    private float jacobian;
    private float[] colHistogram;
    private float[][] imageHistogram;
    private Vector3[] samples;
    private Color[] colors;
    public ImageBasedLight() { super();
                               texture = null;
                               updateBasis(new Vector3(0, 0, -1), new Vector3(
                                             0,
                                             1,
                                             0));
                               numSamples = 64; }
    private void updateBasis(Vector3 center, Vector3 up) { if (center != null &&
                                                                 up !=
                                                                 null) { basis =
                                                                           OrthoNormalBasis.
                                                                             makeFromWV(
                                                                               center,
                                                                               up);
                                                                         basis.
                                                                           swapWU(
                                                                             );
                                                                         basis.
                                                                           flipV(
                                                                             );
                                                           }
    }
    public boolean update(ParameterList pl,
                          SunflowAPI api) {
        updateBasis(
          pl.
            getVector(
              "center",
              null),
          pl.
            getVector(
              "up",
              null));
        numSamples =
          pl.
            getInt(
              "samples",
              numSamples);
        String filename =
          pl.
          getString(
            "texture",
            null);
        if (filename !=
              null)
            texture =
              TextureCache.
                getTexture(
                  api.
                    resolveTextureFilename(
                      filename),
                  true);
        if (texture ==
              null)
            return false;
        Bitmap b =
          texture.
          getBitmap(
            );
        if (b ==
              null)
            return false;
        if (filename !=
              null) {
            imageHistogram =
              (new float[b.
                           getWidth(
                             )][b.
                                  getHeight(
                                    )]);
            colHistogram =
              (new float[b.
                           getWidth(
                             )]);
            float du =
              1.0F /
              b.
              getWidth(
                );
            float dv =
              1.0F /
              b.
              getHeight(
                );
            for (int x =
                   0;
                 x <
                   b.
                   getWidth(
                     );
                 x++) {
                for (int y =
                       0;
                     y <
                       b.
                       getHeight(
                         );
                     y++) {
                    float u =
                      (x +
                         0.5F) *
                      du;
                    float v =
                      (y +
                         0.5F) *
                      dv;
                    Color c =
                      texture.
                      getPixel(
                        u,
                        v);
                    imageHistogram[x][y] =
                      c.
                        getLuminance(
                          ) *
                        (float)
                          Math.
                          sin(
                            Math.
                              PI *
                              v);
                    if (y >
                          0)
                        imageHistogram[x][y] +=
                          imageHistogram[x][y -
                                              1];
                }
                colHistogram[x] =
                  imageHistogram[x][b.
                                      getHeight(
                                        ) -
                                      1];
                if (x >
                      0)
                    colHistogram[x] +=
                      colHistogram[x -
                                     1];
                for (int y =
                       0;
                     y <
                       b.
                       getHeight(
                         );
                     y++)
                    imageHistogram[x][y] /=
                      imageHistogram[x][b.
                                          getHeight(
                                            ) -
                                          1];
            }
            for (int x =
                   0;
                 x <
                   b.
                   getWidth(
                     );
                 x++)
                colHistogram[x] /=
                  colHistogram[b.
                                 getWidth(
                                   ) -
                                 1];
            jacobian =
              (float)
                (2 *
                   Math.
                     PI *
                   Math.
                     PI) /
                (b.
                   getWidth(
                     ) *
                   b.
                   getHeight(
                     ));
        }
        if (pl.
              getBoolean(
                "fixed",
                samples !=
                  null)) {
            samples =
              (new Vector3[numSamples]);
            colors =
              (new Color[numSamples]);
            for (int i =
                   0;
                 i <
                   numSamples;
                 i++) {
                double randX =
                  (double)
                    i /
                  (double)
                    numSamples;
                double randY =
                  QMC.
                  halton(
                    0,
                    i);
                int x =
                  0;
                while (randX >=
                         colHistogram[x] &&
                         x <
                         colHistogram.
                           length -
                         1)
                    x++;
                float[] rowHistogram =
                  imageHistogram[x];
                int y =
                  0;
                while (randY >=
                         rowHistogram[y] &&
                         y <
                         rowHistogram.
                           length -
                         1)
                    y++;
                float u =
                  (float)
                    (x ==
                       0
                       ? randX /
                       colHistogram[0]
                       : (randX -
                            colHistogram[x -
                                           1]) /
                       (colHistogram[x] -
                          colHistogram[x -
                                         1]));
                float v =
                  (float)
                    (y ==
                       0
                       ? randY /
                       rowHistogram[0]
                       : (randY -
                            rowHistogram[y -
                                           1]) /
                       (rowHistogram[y] -
                          rowHistogram[y -
                                         1]));
                float px =
                  x ==
                  0
                  ? colHistogram[0]
                  : colHistogram[x] -
                  colHistogram[x -
                                 1];
                float py =
                  y ==
                  0
                  ? rowHistogram[0]
                  : rowHistogram[y] -
                  rowHistogram[y -
                                 1];
                float su =
                  (x +
                     u) /
                  colHistogram.
                    length;
                float sv =
                  (y +
                     v) /
                  rowHistogram.
                    length;
                float invP =
                  (float)
                    Math.
                    sin(
                      sv *
                        Math.
                          PI) *
                  jacobian /
                  (numSamples *
                     px *
                     py);
                samples[i] =
                  getDirection(
                    su,
                    sv);
                basis.
                  transform(
                    samples[i]);
                colors[i] =
                  texture.
                    getPixel(
                      su,
                      sv).
                    mul(
                      invP);
            }
        }
        else {
            samples =
              null;
            colors =
              null;
        }
        return true;
    }
    public void init(String name, SunflowAPI api) {
        api.
          geometry(
            name,
            this);
        if (api.
              lookupGeometry(
                name) ==
              null) {
            return;
        }
        api.
          shader(
            name +
            ".shader",
            this);
        api.
          parameter(
            "shaders",
            name +
            ".shader");
        api.
          instance(
            name +
            ".instance",
            name);
        api.
          light(
            name +
            ".light",
            this);
    }
    public void prepareShadingState(ShadingState state) {
        if (state.
              includeLights(
                ))
            state.
              setShader(
                this);
    }
    public void intersectPrimitive(Ray r,
                                   int primID,
                                   IntersectionState state) {
        if (r.
              getMax(
                ) ==
              Float.
                POSITIVE_INFINITY)
            state.
              setIntersection(
                0,
                0,
                0);
    }
    public int getNumPrimitives() { return 1;
    }
    public float getPrimitiveBound(int primID,
                                   int i) {
        return 0;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) {
        return null;
    }
    public PrimitiveList getBakingPrimitives() {
        return null;
    }
    public int getNumSamples() { return numSamples;
    }
    public void getSamples(ShadingState state) {
        if (samples ==
              null) {
            int n =
              state.
              getDiffuseDepth(
                ) >
              0
              ? 1
              : numSamples;
            for (int i =
                   0;
                 i <
                   n;
                 i++) {
                double randX =
                  state.
                  getRandom(
                    i,
                    0,
                    n);
                double randY =
                  state.
                  getRandom(
                    i,
                    1,
                    n);
                int x =
                  0;
                while (randX >=
                         colHistogram[x] &&
                         x <
                         colHistogram.
                           length -
                         1)
                    x++;
                float[] rowHistogram =
                  imageHistogram[x];
                int y =
                  0;
                while (randY >=
                         rowHistogram[y] &&
                         y <
                         rowHistogram.
                           length -
                         1)
                    y++;
                float u =
                  (float)
                    (x ==
                       0
                       ? randX /
                       colHistogram[0]
                       : (randX -
                            colHistogram[x -
                                           1]) /
                       (colHistogram[x] -
                          colHistogram[x -
                                         1]));
                float v =
                  (float)
                    (y ==
                       0
                       ? randY /
                       rowHistogram[0]
                       : (randY -
                            rowHistogram[y -
                                           1]) /
                       (rowHistogram[y] -
                          rowHistogram[y -
                                         1]));
                float px =
                  x ==
                  0
                  ? colHistogram[0]
                  : colHistogram[x] -
                  colHistogram[x -
                                 1];
                float py =
                  y ==
                  0
                  ? rowHistogram[0]
                  : rowHistogram[y] -
                  rowHistogram[y -
                                 1];
                float su =
                  (x +
                     u) /
                  colHistogram.
                    length;
                float sv =
                  (y +
                     v) /
                  rowHistogram.
                    length;
                float invP =
                  (float)
                    Math.
                    sin(
                      sv *
                        Math.
                          PI) *
                  jacobian /
                  (n *
                     px *
                     py);
                Vector3 dir =
                  getDirection(
                    su,
                    sv);
                basis.
                  transform(
                    dir);
                if (Vector3.
                      dot(
                        dir,
                        state.
                          getGeoNormal(
                            )) >
                      0) {
                    LightSample dest =
                      new LightSample(
                      );
                    dest.
                      setShadowRay(
                        new Ray(
                          state.
                            getPoint(
                              ),
                          dir));
                    dest.
                      getShadowRay(
                        ).
                      setMax(
                        Float.
                          MAX_VALUE);
                    Color radiance =
                      texture.
                      getPixel(
                        su,
                        sv);
                    dest.
                      setRadiance(
                        radiance,
                        radiance);
                    dest.
                      getDiffuseRadiance(
                        ).
                      mul(
                        invP);
                    dest.
                      getSpecularRadiance(
                        ).
                      mul(
                        invP);
                    dest.
                      traceShadow(
                        state);
                    state.
                      addSample(
                        dest);
                }
            }
        }
        else {
            for (int i =
                   0;
                 i <
                   numSamples;
                 i++) {
                if (Vector3.
                      dot(
                        samples[i],
                        state.
                          getGeoNormal(
                            )) >
                      0 &&
                      Vector3.
                      dot(
                        samples[i],
                        state.
                          getNormal(
                            )) >
                      0) {
                    LightSample dest =
                      new LightSample(
                      );
                    dest.
                      setShadowRay(
                        new Ray(
                          state.
                            getPoint(
                              ),
                          samples[i]));
                    dest.
                      getShadowRay(
                        ).
                      setMax(
                        Float.
                          MAX_VALUE);
                    dest.
                      setRadiance(
                        colors[i],
                        colors[i]);
                    dest.
                      traceShadow(
                        state);
                    state.
                      addSample(
                        dest);
                }
            }
        }
    }
    public void getPhoton(double randX1, double randY1,
                          double randX2,
                          double randY2,
                          Point3 p,
                          Vector3 dir,
                          Color power) { 
    }
    public Color getRadiance(ShadingState state) {
        return state.
          includeLights(
            )
          ? getColor(
              basis.
                untransform(
                  state.
                    getRay(
                      ).
                    getDirection(
                      ),
                  new Vector3(
                    )))
          : Color.
              BLACK;
    }
    private Color getColor(Vector3 dir) {
        float u;
        float v;
        double phi =
          0;
        double theta =
          0;
        phi =
          Math.
            acos(
              dir.
                y);
        theta =
          Math.
            atan2(
              dir.
                z,
              dir.
                x);
        u =
          (float)
            (0.5 -
               0.5 *
               theta /
               Math.
                 PI);
        v =
          (float)
            (phi /
               Math.
                 PI);
        return texture.
          getPixel(
            u,
            v);
    }
    private Vector3 getDirection(float u,
                                 float v) {
        Vector3 dest =
          new Vector3(
          );
        double phi =
          0;
        double theta =
          0;
        theta =
          u *
            2 *
            Math.
              PI;
        phi =
          v *
            Math.
              PI;
        double sin_phi =
          Math.
          sin(
            phi);
        dest.
          x =
          (float)
            (-sin_phi *
               Math.
               cos(
                 theta));
        dest.
          y =
          (float)
            Math.
            cos(
              phi);
        dest.
          z =
          (float)
            (sin_phi *
               Math.
               sin(
                 theta));
        return dest;
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {
        
    }
    public float getPower() { return 0; }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "cb5C+bHh+9T/ABsWHc8AIgAA");
}
