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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAALVaC2wcRxmeOz/OcZ34kZeT+BE7Tkoe3BH6gNZVwHadxuGS" +
       "mDg1qgtx13tz5032dje7c/Y5JbSNihIqVKGSJimkVkEuTUOaREBVHkoVJKCt" +
       "ShEtb6E+AFWNaCOIBG2ghfL/M7u3e3t360sASzO3O/PPzPfP/5xZn7xAqiyT" +
       "rDV0dSql6ixKsyy6S70uyqYMakU3x68blEyLJvpUybJ2QNuo3Hmm/u13vzTe" +
       "ECbVI2S+pGk6k5iia9Z2aunqBE3ESb3b2q/StMVIQ3yXNCHFMkxRY3HFYt1x" +
       "cpVnKCNdcQdCDCDEAEKMQ4j1uFQwaC7VMuk+HCFpzNpDPkdCcVJtyAiPkY78" +
       "SQzJlNL2NIOcA5ihBt+HgSk+OGuS5TneBc8FDD+4NnboyM6Gb1WQ+hFSr2hD" +
       "CEcGEAwWGSF1aZoeo6bVk0jQxAhp1ChNDFFTkVRlL8c9QposJaVJLGPS3CZh" +
       "Y8agJl/T3bk6GXkzMzLTzRx7SYWqCeetKqlKKeB1kcur4HAjtgODtQoAM5OS" +
       "TJ0hlbsVLcFIu39EjseuTwABDI2kKRvXc0tVahI0kCYhO1XSUrEhZipaCkir" +
       "9AyswsjSkpPiXhuSvFtK0VFGmv10g6ILqObwjcAhjCz0k/GZQEpLfVLyyOfC" +
       "1pvuv1PbpIU55gSVVcRfA4PafIO20yQ1qSZTMbBuTfywtOjswTAhQLzQRyxo" +
       "nvrsxY+vazv3rKBZVoRm29guKrNReWZs3ostfatvqEAYNYZuKSj8PM65+g/a" +
       "Pd1ZAyxvUW5G7Iw6nee2/+S2u0/QN8OkdoBUy7qaSYMeNcp62lBUat5CNWpK" +
       "jCYGyByqJfp4/wCJwHNc0aho3ZZMWpQNkEqVN1Xr/B22KAlT4BZF4FnRkrrz" +
       "bEhsnD9nDUJIBArZDGUxEX/8l5GR2LiepjFJljRF02Ogu1Qy5fEYlfWYJaUN" +
       "FaRmZbSkqk/GLFOO6WYq9y7rJo2pSmqcxQbSIP1eCUwtju9R1DHj/zp7Fnlr" +
       "mAyFYNtb/Eavgr1s0tUENUflQ5ne/ounRp8P54zA3hVGVsF6UXu9KK4X5etF" +
       "feuRUIgvswDXFZIFuezGXovVrR76zOY7DnZWgEoZk5WwqUjaCRzaYPplvc91" +
       "AwPc2cmgi81fv/1A9NJjHxO6GCvts4uOJueOTt4zfNeHwiSc73yROWiqxeGD" +
       "6DJzrrHLb3TF5q0/cP7t04f36a755Xlz2ysUjkSr7vSLwdRlmgA/6U6/Zrn0" +
       "5OjZfV1hUgmuAtwjk0CdwfO0+dfIs+5ux1MiL1XAcFI305KKXY57q2Xjpj7p" +
       "tnD9mMefG0EoV6G6L4XSYes//8Xe+QbWC4Q+oZR9XHBPvPF75x568itrbwh7" +
       "nXa9JwwOUSZcQKOrJDtMSqH95aODX37wwoHbuYYAxYpiC3Rh3QcOAUQG2/r5" +
       "Z/f87tVXZn4ZdrWKQWTMjKmKnIU5VrmrgLtQwWWh7Ltu1dJ6Qkkq0phKUTnf" +
       "q1+5/sm37m8Q0lShxVGGdbNP4LYv6SV3P7/znTY+TUjGcOVy7pKJDZjvztxj" +
       "mtIU4sje81LrQ89ID4M3BQ9mKXspd0qEc0b41se4qNbwOurrW4/VcqOgL8tb" +
       "mvlbDSy9urQRbcSo6zG+f25Tx/b/8RLnqMB8igQb3/iR2MljS/s2vMnHu3qM" +
       "o9uzhQ4JMhR37IdPpP8e7qz+cZhERkiDbKc/w5KaQW0ZgZBvOTkRpEh5/fnh" +
       "W8Sq7pydtvhtyLOs34JcRwjPSI3PtT6jqcNdboHSbBtNs99oQoQ/3MCHdPJ6" +
       "JVYfcHQ2YpjKhIS5FYlgjVkTEiyEUFrgfXcIAm6JQtrX5mNZAWWJjWVJCSw9" +
       "WHUzUjUmWYrlLNbhXSwNoTG6zYTsaCv3I71IWXrVZbbrcFxIsVU32qvWQkY7" +
       "JCJbsEIOmkoakoMJO3uJ7Wt6dfex80+IaODXPh8xPXjovvej9x8Ke/LBFQUp" +
       "mXeMyAm5eOcK5t6HvxCUf2NBprBB5ARNfXZisjyXmRgGep2OIFh8iY1vnN73" +
       "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");
}
