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
      1169407152000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1aC2wUxxmeOz/OGIMfEDBgG2wMKY/elYakJY5obccE0wMs" +
       "DK7ipJj13tx5YW932Z2zD1KaBKWCRhWqKOHREiutSCEpCahtlD6UiEptkyhN" +
       "1aRvVQlpqyioBLVIbUKbtPT/Z3Zv9/bulgO1tTRzuzP/zHz//M+Z9elLpMoy" +
       "yTJDV3elVJ1FaZZFt6u3Rtkug1rRdfFbByTTooleVbKszdA2InecrX/3/S+N" +
       "NYRJ9TCZIWmaziSm6Jq1iVq6Ok4TcVLvtvapNG0x0hDfLo1LsQxT1FhcsVhX" +
       "nEz1DGWkM+5AiAGEGECIcQixbpcKBk2jWibdiyMkjVk7yedIKE6qDRnhMdKe" +
       "P4khmVLanmaAcwAz1OD7EDDFB2dNsiDHu+C5gOFHlsUOHdna8K0KUj9M6hVt" +
       "EOHIAILBIsOkLk3To9S0uhMJmhgmjRqliUFqKpKq7Oa4h0mTpaQ0iWVMmtsk" +
       "bMwY1ORrujtXJyNvZkZmupljL6lQNeG8VSVVKQW8znJ5FRyuwXZgsFYBYGZS" +
       "kqkzpHKHoiUYme8fkeOx81NAAEMjacrG9NxSlZoEDaRJyE6VtFRskJmKlgLS" +
       "Kj0DqzAyt+SkuNeGJO+QUnSEkWY/3YDoAqopfCNwCCM3+cn4TCCluT4peeRz" +
       "acMdB+7T1mphjjlBZRXx18CgNt+gTTRJTarJVAysWxo/LM16bn+YECC+yUcs" +
       "aJ797OVPLm8796KgmVeEZuPodiqzEfnE6PRXW3qXrKpAGDWGbiko/DzOufoP" +
       "2D1dWQMsb1ZuRuyMOp3nNv3k7geepBfDpLafVMu6mkmDHjXKetpQVGreRTVq" +
       "Sowm+skUqiV6eX8/icBzXNGoaN2YTFqU9ZNKlTdV6/wdtigJU+AWReBZ0ZK6" +
       "82xIbIw/Zw1CSAQKWQdlNhF//JcRGttigbrHJFnSFE2PgfJSyZTHYlTWR0Zh" +
       "d8fSkrnDiskZi+npmJXRkqo+EbNMOaabqdy7rJs0piqpMRbrT4Mi9EhgdXF8" +
       "j6K6Gf+vhbLIccNEKATCaPG7AhWsaK2uJqg5Ih/K9PRdfnrk5XDONOy9YmQx" +
       "rBe114vielG+XtS3HgmF+DIzcV0hb5DWDuy1WN2Swc+s27a/owIUzZiohK1G" +
       "0g7g1QbTJ+u9rnPo5y5QBg1t/vo9+6JXTn5CaGistCcvOpqcOzrx4ND9HwmT" +
       "cL5LRuagqRaHD6AjzTnMTr8pFpu3ft+Fd88c3qO7Rpnn421fUTgSbb3DLwZT" +
       "l2kCvKc7/dIF0jMjz+3pDJNKcCDgNJkESg7+qM2/Rp7Ndzn+E3mpAoaTupmW" +
       "VOxynF4tGzP1CbeF68d0/twIQpmKRjAXSrttFfwXe2cYWM8U+oRS9nHB/fOa" +
       "75079sxXlq0Ke115vSc4DlImHEOjqySbTUqh/fWjA19+5NK+e7iGAMXCYgt0" +
       "Yt0LbgJEBtv6+Rd3/u78Gyd+GXa1ikG8zIyqipyFORa7q4ATUcGRoew7t2hp" +
       "PaEkFWlUpaicH9QvWvHMOwcahDRVaHGUYfm1J3Db5/SQB17e+l4bnyYkYxBz" +
       "OXfJxAbMcGfuNk1pF+LIPvha67EXpEfBx4Jfs5TdlLsqwjkjfOtjXFRLeR31" +
       "9a3AaoFR0JflLc38rQaWXlLaiNZgLPYY3z83qqN7/3iFc1RgPkVCkG/8cOz0" +
       "8bm9qy/y8a4e4+j52UKHBHmLO/ajT6b/Hu6o/nGYRIZJg2wnRUOSmkFtGYZE" +
       "wHIyJUic8vrzg7qIYF05O23x25BnWb8FuY4QnpEan2t9RlOHu9wCpdk2mma/" +
       "0YQIf1jFh3TwehFWH3J0NmKYyriEGReJYI25FBLcBAG2wPtuFgTcEoW0V+Zj" +
       "WQhljo1lTgks3Vh1MVI1KlmK5SzW7l0sDQEzutGEnGkD9yM9SFl61Xm263Bc" +
       "SLFV19ir1kKeOyilDZVawQo5YCppSBnG7Zwmtqfp/I7jF54S0cCvfT5iuv/Q" +
       "w1ejBw6FPVniwoJEzTtGZIpcvNMEc1fhLwTl31iQKWwQmUJTr52uLMjlK4aB" +
       "Xqc9CBZfYs3bZ/b84NSefYKNpvwkqQ/25qlf/+un0aNvvlQkGldAAhwshHm2" +
       "EOaVEMKgLYSa7ZKsjyqSMOd+rNYLX7GRoRnoUsBC7bbOO7pfbKG77YUg7VfX" +
       "gn/TUxhoTbKotLy5LxTim/zGwp/dP7nwD7AHw6RGscBMu81UkWTZM+avp89f" +
       "fG1a69M8cFaCaguD9Z8yCg8ReWcDrgB1Bv8ZyrnTkJ228M0wHB63FbfpMD4u" +
       "wW1UNEkFs65WqZYSCWgcq61GNjdzWAxxjFCEBvRdcA7QNYpRxukTyZWiR3Nn" +
       "MOjMFmA0SWtearWec+c614ef+Oaz7NVltwsFXFpaHv6BL+z989zNq8e2XUdC" +
       "Nd8nLv+UT6w//dJdi+WDYVKR89EFh7n8QV35nrnWpOAQtc15/rlNyG8rVp0B" +
       "UXNnQB9v1EGKMspBiA32dn7xrKAvbTAex3d/d/Z37jg5+QZPS7KktBEtgtJq" +
       "G1FrCSOasI1ouoLptmtG2HyvzaNhGAGrtNnFeS62yn32KhHL9sp8+vx5ZgTN" +
       "UzRc8QgyRDEDviUYI/qq+fbc80tg3GtjxAOjbvoh9uEoPNC9Yk/zinDT9/53" +
       "zlnWLovRtDhjDUgaVZ1z3P90/ty2zvZuK9cEVD3ddHc16yq6SPUqnLFtBRlE" +
       "LiKhtjpkLQVk/Fg3qGdMmRbFwYkGxyQ4QGLcay11icFj3om9hyYTGx9fEbZt" +
       "a4CRKUw3PqzScap6YDfy5x356vFxKDfbcr3Zrx6cf64bPlMOu674AV5x0mMB" +
       "Bv9VrA4zMjVjJCCg9+RyI190rBzXlURhpu2DjgbDE7CYDT1WErrfQ/lCQhER" +
       "oo+lEMW8IpzlJRsUv90D/XyZEwFsP4nV18CyBNvFOI6M6rpKJe2aTPMj5Aoo" +
       "D9lMP3SjTDe4cVBczGH7ST782wHMPIvVWYaXPwpPYB4tD/PHoBy0MR8sG7M3" +
       "KwDMrUXtA7DjtSrl0zwXgP2HWH0fUgDDpHDGpd7B18HKBihHbFaOlM1KRb7b" +
       "mFnAyiZpF/bFHYqOAop+zKssERBdjl8I4PgVrH4E2bTiDM05p/IYrndCxnGb" +
       "4eNlM+zF8YuAvl9h9XNQyBRlGzLpHEBOHL8mQiykA8pjNsLHrtci+Dq84qS/" +
       "D8D6Ola/ZaQRsOaA9ugZLYEdQ9cEy69BMS85YYM9caOmUJgJrJfAjLMr+Qx/" +
       "CuDhbazehJQHePi0bqoJjj93SG0pmJj3g5306NlrMsgPxMuhnLIZPHVD+vKX" +
       "gL7LWL0DVgz4eyQ4eKbyVeaL5Sk1HqtO2yBP3xDI9wL6/oHV3xiZJpTaPo2X" +
       "p9HcyWD/WRve2etVEnx9nlNdLY0xxMk+YKQWMHoAlukFd0F5ywb4VtkAI3zG" +
       "SLEYWJ3QM6P840Koxq2KpkZcLQd0cGq3YDdPP77AeYoE8Dsdq0pIjNB4x3Sm" +
       "a+Wx2+ioywWb3Qs3LI/QzAB8s7BqhPQI8G2CyIRnMc7ZNRE2YSPeCl2yEV4q" +
       "idCfxXkQ8gQu1BKAsA2rOYzUAEKeIJcHbyY2LoByxYZ3pWx4Hhc9xCuOI8Aq" +
       "Q4uxamekDjDeqZgiWnL2ytPr1bAblQKm+C1L0B6cz7vauDwAZxSrJeAhLFli" +
       "EJyvWyPB1YYiNtBI2UC9EFYG9N2G1Qoh6gF9QlwrFwlwWUbqfZ+q8BDfXPBh" +
       "XHzMlZ+erK+ZPbnlN+IOyfngOiVOapIZVfVeCHueqyFhSyqcqSniepgfyEJd" +
       "vqTc/XzGSBX/Rbyh2wX1asgx/NSQyOKPl6wbLNBDhqd28eQlupORCiDCxz6j" +
       "SEotrsWz4o6j2d4rI+8t7ysMXg3xfzlwrnEy4p8ORuQzk+s23Hf5tsf5nVCV" +
       "rEq7d+MsNXESER+g+KR4FdRecjZnruq1S96ffnbKIueUiB6RNNlfnbzY8Hn8" +
       "P9TrJy/gIQAA");
}
