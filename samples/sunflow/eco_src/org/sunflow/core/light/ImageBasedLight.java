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
                               this.updateBasis(new Vector3(0, 0, -1), new Vector3(
                                                  0,
                                                  1,
                                                  0));
                               numSamples = 64; }
    private void updateBasis(Vector3 center, Vector3 up) { if (center !=
                                                                 null &&
                                                                 up !=
                                                                 null) {
                                                               basis =
                                                                 OrthoNormalBasis.
                                                                   makeFromWV(
                                                                     center,
                                                                     up);
                                                               basis.
                                                                 swapWU();
                                                               basis.
                                                                 flipV();
                                                           }
    }
    public boolean update(ParameterList pl, SunflowAPI api) {
        this.
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
          getBitmap();
        if (b ==
              null)
            return false;
        if (filename !=
              null) {
            imageHistogram =
              (new float[b.
                           getWidth()][b.
                                         getHeight()]);
            colHistogram =
              (new float[b.
                           getWidth()]);
            float du =
              1.0F /
              b.
              getWidth();
            float dv =
              1.0F /
              b.
              getHeight();
            for (int x =
                   0;
                 x <
                   b.
                   getWidth();
                 x++) {
                for (int y =
                       0;
                     y <
                       b.
                       getHeight();
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
                        getLuminance() *
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
                                      getHeight() -
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
                       getHeight();
                     y++)
                    imageHistogram[x][y] /=
                      imageHistogram[x][b.
                                          getHeight() -
                                          1];
            }
            for (int x =
                   0;
                 x <
                   b.
                   getWidth();
                 x++)
                colHistogram[x] /=
                  colHistogram[b.
                                 getWidth() -
                                 1];
            jacobian =
              (float)
                (2 *
                   Math.
                     PI *
                   Math.
                     PI) /
                (b.
                   getWidth() *
                   b.
                   getHeight());
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
                  this.
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
    public void init(String name, SunflowAPI api) { api.geometry(
                                                          name,
                                                          this);
                                                    if (api.
                                                          lookupGeometry(
                                                            name) ==
                                                          null) {
                                                        return;
                                                    }
                                                    api.shader(
                                                          name +
                                                          ".shader",
                                                          this);
                                                    api.parameter(
                                                          "shaders",
                                                          name +
                                                          ".shader");
                                                    api.instance(
                                                          name +
                                                          ".instance",
                                                          name);
                                                    api.light(
                                                          name +
                                                          ".light",
                                                          this);
    }
    public void prepareShadingState(ShadingState state) {
        if (state.
              includeLights())
            state.
              setShader(
                this);
    }
    public void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        if (r.
              getMax() ==
              Float.
                POSITIVE_INFINITY)
            state.
              setIntersection(
                0,
                0,
                0);
    }
    public int getNumPrimitives() { return 1; }
    public float getPrimitiveBound(int primID, int i) { return 0;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { return null;
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public int getNumSamples() { return numSamples; }
    public void getSamples(ShadingState state) { if (samples ==
                                                       null) {
                                                     int n =
                                                       state.
                                                       getDiffuseDepth() >
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
                                                           this.
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
                                                                   getGeoNormal()) >
                                                               0) {
                                                             LightSample dest =
                                                               new LightSample(
                                                               );
                                                             dest.
                                                               setShadowRay(
                                                                 new Ray(
                                                                   state.
                                                                     getPoint(),
                                                                   dir));
                                                             dest.
                                                               getShadowRay().
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
                                                               getDiffuseRadiance().
                                                               mul(
                                                                 invP);
                                                             dest.
                                                               getSpecularRadiance().
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
                                                                   getGeoNormal()) >
                                                               0 &&
                                                               Vector3.
                                                               dot(
                                                                 samples[i],
                                                                 state.
                                                                   getNormal()) >
                                                               0) {
                                                             LightSample dest =
                                                               new LightSample(
                                                               );
                                                             dest.
                                                               setShadowRay(
                                                                 new Ray(
                                                                   state.
                                                                     getPoint(),
                                                                   samples[i]));
                                                             dest.
                                                               getShadowRay().
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
          includeLights()
          ? this.
          getColor(
            basis.
              untransform(
                state.
                  getRay().
                  getDirection(),
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169407152000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVaCWwc1Rl+uz7WF3HshNyJcQ4CibMbO87hGEGcizhsEmPn" +
       "IiGY8ezb9SSzM8PM\nrLMJaSACEo5SCAUBKoRQReQglLSUhqopBQEtBVUFpE" +
       "KFBAVFtFRtqtJWND2k9v/fe7MzO3vEsRVL\n83a87/q/7z/e/8/syXOkzDLJ" +
       "JNkK27sMaoWX9XRJpkVjy1TJstbDV73yW2WVXUdv0PQgCURJUInZ\npDYqW5" +
       "GYZEsRJRbpXN6eNslsQ1d3JVTdDtO0Hd6uzhfrrY7Oz1lw06HT9fuOlDYESV" +
       "mU1EqaptuS\nrejaCpUmLZuMjG6XBqRIylbUSFSx7PYouYxqqeQyXbNsSbOt" +
       "28heUhIl5YaMa9qkMepsHoHNI4Zk\nSskI2z7SxbaFFUaZ1JYUjcY6MtvBzK" +
       "bsmSC2mNedOxoWqcDOjQCHSQCor8ig5mhzoBolxzYu2HP4\neAmp3UJqFa0H" +
       "F5MBiQ37bSE1SZrso6bVEYvR2BZSp1Ea66GmIqnKbrbrFlJvKQlNslMmtbqp" +
       "pasD\nOLDeShnUZHs6X0ZJjYyYzJRs62aGo7hC1ZjzX1lclRIAe4wLm8Ndid" +
       "8DwCoFBDPjkkydKaU7FA00\n3uCfkcE4/QYYAFNDSWr365mtSjUJviD1XJeq" +
       "pCUiPbapaAkYWqanYBebTCi4KHJtSPIOKUF7bTLO\nP66Ld8GoSkYETrHJ5f" +
       "5hbCXQ0gSfljz6mT3m63uPPfXqEmbbpTEqqyh/FUya4pvUTePUpJpM+cTz\n" +
       "qfCjnTelJgUJgcGX+wbzMR0zTm+IfvmzBj5mYp4x6/q2U9nuldcebOi+/Xqd" +
       "lKAYFYZuKaj8LOTM\nHbpET3vaAK8dk1kRO8NO52vdP7/pzhP0T0FS1UnKZV" +
       "1NJcGO6mQ9aSgqNa+nGjUlm8Y6SSXVYstY\nfycJwX0UTJ5/uy4et6jdSUpV" +
       "9lW5zv4HiuKwBFJUCfeKFtede0Oy+9l92iCEhOAiq+EaS/gf+7RJ\nWzhipb" +
       "S4qu+MWKYc0c1E5n9ZN2lEVRL9dqQzCcpdKoEnRfH/MJqQYZMNkX49SSOSLG" +
       "mKpkcSCjit\nrM+J0QH8HOrCaZS6fmcggGHQ784qeMIqXY1Rs1c+evadPStu" +
       "uO9ebipo3gKvTa6E/cJivzDuF2b7\nhX37kUCAbTMa9+U6A8Z3YK9l11zds2" +
       "31rfdOLQFjMXaWAl04dCogE8KskPVlroN3slgog5WN++7W\nA+HzR6/jVhYp" +
       "HIfzzq5+74V3D/+9ZlaQBPMHSQQJYboKl+nCyJoJftP9bpVv/b/cv+alD9/9" +
       "5CrX\nwWwyPcfvc2ei3071q8PUZRqDSOguf2R8bckmsvFgkJRCMIAAyOSH2D" +
       "LFv0eW/7Y7sRCxhKKkOq6b\nSUnFLieAVdn9pr7T/YbZyUh2PwqUU40GPQGu" +
       "RmHh7BN7LzewHcPtCrXtQ8Fi7fm7yud+dKb6LUaL\nE5ZrPQdfD7W5k9e5xr" +
       "LepBS+/+SJrm8/du7AVmYpwlRsOA1Tfaoip2HKle4U8G4VIgwqcvoGLanH\n" +
       "lLgi9akULe6/tTOaX/7zt0Zy1ajwjaPZpgsv4H4/fim5891b/jmFLROQ8XRx" +
       "YbjDOJpR7sodpint\nQjnS+z6Y/OQvpKch+EHAsZTdlMUQwpARxmOE8T6LtW" +
       "FfXzM2U2HtpgKmn+cs75X3nEhMTd32yx8z\nqaslb1LgVcMayWjnmsdmGrI7" +
       "1u+9qySrH8a1vrb25pHqa/+BFbfAijKcodY6E0JHOkuJYnRZ6OPX\n3xhz6/" +
       "slJLiSVKm6FFspMfsnlWB41OqHqJM2rlvCbGvkzgpsGWTCSJggCEh7/qsA4a" +
       "4u7P4rMRNw\nPae3r+lY9J11TzMCCjp+noPQt87uVzccOv8r+1O2juuBOLsx" +
       "nRtSIXty5y76cKCu/NQzySAJbSEj\nZZHfbZTUFNr5FkhHLCfpgxwwqz87te" +
       "DnaHsmwkzye79nW7/vu6Ec7nE03tf43L0G2Z4E1zjh7uP8\n7h4g7OY6NmU6" +
       "a2dmnDNkmMqAhDkfCWGL2RyOGAtHfM7ZsZ4P4PED2xZslnBVzy9oEouzhZ0G" +
       "13gh\n7PgCwnZi02GTsj7JUixHokavREk418PrTEjt1rIQuRRH+kRbfZGiTR" +
       "Sh0wmh+UTrEqJVQeLfIyUN\nFbzJJOO8dYqpJCHfGWAB/ez+qT99e8MzB/gh" +
       "WMQLsmb1ynf87rMdJQ+93sfn+U3dN/jglCO/f+ls\n92geMHliPC0nN/XO4c" +
       "kxs6VaA4NHY7Ed2Og3Zzee3Nv9qZCoPjvFWwFs/GHXG3TmNQ9+nicPKYH0\n" +
       "3aebG4egm4lCNxML6OYWoZuK7ZKs9ykSjxU9Bt9qs43+pUt+SXovUpJG4XGO" +
       "5+WTJC4kgdJHXQVH\niZ7ABAXOXI+dsHMG6T3+8PJR3W1b72KpTiWUWJK11n" +
       "V9KGzxLgCkzyhsPpnFeuWZ207/9fVX6UwW\n7ysUC0JMh5nIU2545nwlnaBr" +
       "PoofYulKKXgdDzb+Oi23DMuqrpg9jTDYh5Q5EwMiaWRUGg5DWm48\nCqKCFE" +
       "1ipc7VEJLKVaolePq+CRtVaBJXDfIpTmzg5zfGXKiidI1iKuD08bRW0cOZCh" +
       "Y60znymWRy\nVlK7hiFzD4X7jz9/2n5/9mLuALMK68I/cdbiw7sbFr/4wBBS" +
       "2QafyvxL1w1MvLGkX3k7yEpbfsbk\nlMTZk9qzT5YqkCdlauuzzpcruA6ZIq" +
       "4skuLcUaRvHzbfAJXKqA+uPuC4IX8KtyJp2Czp2v3K2B9e\nc/TQp8iykYbS" +
       "rIo5REtL24L5ML0eHAgf8ISVmDjwl3+wsu9EXDsRYxxUMXvtwCkCYCX7xuNQ" +
       "Jbph\nYQXreVQkVpq+zrAwv7/Ms0nn8j2nVtdUPPvAfra+8MZKTzUs/g8NSO" +
       "Zab9CrZoI3ty1YOK/NJr2X\npEhc3Ny2qGnewjmtsEPd+lWdPWFvwEFR7vEF" +
       "ob1QnudyiMhFSCP1zL5HuA6Fwd3bCdhKu1d0LPfF\n0cRFxtEZcE0Wy04uEE" +
       "cfFXF0hILg3UiKXysiGigi4BiGkWUuC1jvfmyevHT8L1rYNK9tzvwFNhnL\n" +
       "+M8WNLw1vQ2OP2hRjKexedgv50Iup8dgFrRdQoMRAs+DmJlHYBTlUA7fTG6f" +
       "uh+7SHVPEZdzn0/d\nzwl1hyyRWTH9Zq8zqtg6eZNXlipupFjFzOPkC6bntb" +
       "QtbMlhf9HceZeM/ZbmtqYFrXMWNoNXMvYF\nUJThBRd3Pr6PDiFhahA8NRTg" +
       "+2XBNz6M0828dNcVW8ahe6yXbmY6GNx1M4ft1ly2m1svHdstzU2t\ni+bMhx" +
       "2qneAoHkGfyYDOx/WPinCddg9FXt6WOCxMyamYMlk0nmzOsEk5w5jIPXrKlG" +
       "leRtmgnn4J\na3ZMUwo9NmZ5+oHNX9Xsl97cFhTn8E02VO66MUelA1T1iF3H" +
       "7ndla3sRXDOFtmf6tc1IYhbjO/Z9\nuVhB57vgALbFB0WSit9i82tQZ8qAE4" +
       "wuzZSHniS/dEBXYq4u37uQ3ziPLHx8sAdp7XBFBB+Rgnz4\nUyQfH3nsApM8" +
       "Cqm01y7GeIf18M+Ork62zdkinPwRm8/AnjknfjpCfbquUlELMUY+Hw4jzXDd" +
       "LRi5\ne6iMjHSTC/7yZTAc/KMIB//G5isbH/0rrL772IX7t+HAXQjXIwLuI4" +
       "OG6y15ANXkvM4MsPGtG8Vl\nAsHC4ALl+OX/4Lw2TGpIJvVOzsYaIMPBuhau" +
       "xwXWxweNtSQ7CI7Owdot7cK+Tc6IqTkjOrGqtHgp\n4FIyogglo7Gptkm94k" +
       "zNhFofIzXDYQRL/acEI08NmhGvoJOK9E3BZjx4Q4Laa1PJDALLpYtBmDAc\n" +
       "CPPhOiwgHL5Yf2WCsIYJXAzoVdhMgyoEwGSQLNVTGquXJBfN9OGgaYHriEBz" +
       "ZKjumHv8rJEgCqVb\nGZC5RUC2YjMH0mMAuUk31RgDmHlAOSlnYdYPvrpUT7" +
       "sMhIfDAIp0TDBwbEgmeW2RviXYtEOoAYBL\npR0guWuVF5PmMJzXDAcnpvYn" +
       "Bc6TQ8K5ukhfFJvrbXIZd70eN+32+N2q4erplJD/1FAtdRAHx/oi\nKDdjcy" +
       "PUmoDSA9ETHbuHA3EXXF8IiF8MGmKIrRjypynlMT3Vx17hByS3yZsOM8/q0i" +
       "H0XzijzLuA\np0JhPN1ahMMd2EANX4lxrV+3+fMeD4W3DIfCuXB9KSj88hJa" +
       "iVUE4U5s8HkVIOyGefiQ8EK0ufBv\nGyJ8Vm5giXFOwD9XEL6/3LhgOPdUE4" +
       "F9RZDfg81em1QAchfWoGDfMRzYeIqdF7DPDxq250yWWMMw\nPFQE30FsvmmT" +
       "GsC3XDHFs9ZBuQwD+eBwTPtaUFEpx8g/B2XavkKhuGkPyrm/U4SgZ7F5Ao4B" +
       "S5Zs\nSCTzOviTw2FhIqAPCRZCg2bBK+PxIn3PY/McN+AufSd/u+3JtS74zC" +
       "jzut4mtb5HKPiwflzOzwj5\nT9/k6Me33/x19Df/4u+LnJ+nVUdJRTylqt4X" +
       "1577cqhf4gpDXc1fY7Mnt4Ef+Oo+94dKNiljnyhr\n4Pt89MuQMPtHQ+GHH9" +
       "5hr0BA8wzDZ2v8zjvoJzYpgUF4e8ZwrMlTnPLX9+ksqpCXaVkvf9jvOp0X\n" +
       "NCn+y85eefMLW69IP7D+YfbWp0xWpd27cZmqKAnxH/WwVfElT2PB1Zy13iOn" +
       "Xtx45nttzjMd9qOP\n0eKXPDkm2MJ7C6seO+42/g/80UbFYysAAA==");
}
