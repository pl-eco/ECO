package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class PhongShader implements Shader {
    private Color diff;
    private Color spec;
    private float power;
    private int numRays;
    public PhongShader() { super();
                           diff = Color.GRAY;
                           spec = Color.GRAY;
                           power = 20;
                           numRays = 4; }
    public boolean update(ParameterList pl, SunflowAPI api) { diff = pl.getColor(
                                                                          "diffuse",
                                                                          diff);
                                                              spec =
                                                                pl.
                                                                  getColor(
                                                                    "specular",
                                                                    spec);
                                                              power =
                                                                pl.
                                                                  getFloat(
                                                                    "power",
                                                                    power);
                                                              numRays =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    numRays);
                                                              return true;
    }
    protected Color getDiffuse(ShadingState state) { return diff;
    }
    public Color getRadiance(ShadingState state) { state.
                                                     faceforward();
                                                   state.
                                                     initLightSamples();
                                                   state.
                                                     initCausticSamples();
                                                   return state.
                                                     diffuse(
                                                       this.
                                                         getDiffuse(
                                                           state)).
                                                     add(
                                                       state.
                                                         specularPhong(
                                                           spec,
                                                           power,
                                                           numRays));
    }
    public void scatterPhoton(ShadingState state, Color power) {
        state.
          faceforward();
        Color d =
          this.
          getDiffuse(
            state);
        state.
          storePhoton(
            state.
              getRay().
              getDirection(),
            power,
            d);
        float avgD =
          d.
          getAverage();
        float avgS =
          spec.
          getAverage();
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              avgD) {
            power.
              mul(
                d).
              mul(
                1.0F /
                  avgD);
            OrthoNormalBasis onb =
              state.
              getBasis();
            double u =
              2 *
              Math.
                PI *
              rnd /
              avgD;
            double v =
              state.
              getRandom(
                0,
                1,
                1);
            float s =
              (float)
                Math.
                sqrt(
                  v);
            float s1 =
              (float)
                Math.
                sqrt(
                  1.0F -
                    v);
            Vector3 w =
              new Vector3(
              (float)
                Math.
                cos(
                  u) *
                s,
              (float)
                Math.
                sin(
                  u) *
                s,
              s1);
            w =
              onb.
                transform(
                  w,
                  new Vector3(
                    ));
            state.
              traceDiffusePhoton(
                new Ray(
                  state.
                    getPoint(),
                  w),
                power);
        }
        else
            if (rnd <
                  avgD +
                  avgS) {
                float dn =
                  2.0F *
                  state.
                  getCosND();
                Vector3 refDir =
                  new Vector3(
                  );
                refDir.
                  x =
                  dn *
                    state.
                      getNormal().
                      x +
                    state.
                      getRay().
                      dx;
                refDir.
                  y =
                  dn *
                    state.
                      getNormal().
                      y +
                    state.
                      getRay().
                      dy;
                refDir.
                  z =
                  dn *
                    state.
                      getNormal().
                      z +
                    state.
                      getRay().
                      dz;
                power.
                  mul(
                    spec).
                  mul(
                    1.0F /
                      avgS);
                OrthoNormalBasis onb =
                  state.
                  getBasis();
                double u =
                  2 *
                  Math.
                    PI *
                  (rnd -
                     avgD) /
                  avgS;
                double v =
                  state.
                  getRandom(
                    0,
                    1,
                    1);
                float s =
                  (float)
                    Math.
                    pow(
                      v,
                      1 /
                        (this.
                           power +
                           1));
                float s1 =
                  (float)
                    Math.
                    sqrt(
                      1 -
                        s *
                        s);
                Vector3 w =
                  new Vector3(
                  (float)
                    Math.
                    cos(
                      u) *
                    s1,
                  (float)
                    Math.
                    sin(
                      u) *
                    s1,
                  s);
                w =
                  onb.
                    transform(
                      w,
                      new Vector3(
                        ));
                state.
                  traceReflectionPhoton(
                    new Ray(
                      state.
                        getPoint(),
                      w),
                    power);
            }
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1168806208000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKUYfWwT1/3FTmLyQe0ESAKFhITQroH6WjSqjVSDLA2twRTX" +
                                                   "gRRC2/Tl7tk5ON87\n7p4TJ0Vdu0qFtftC7aZtaimtkKD0U6IT29QyqrZbVz" +
                                                   "SpnbROqlS2CWmbtHXSNKlj2v7Y77139p3P\ndoDM0r17vvt9f797+TPU4Nho" +
                                                   "perE2axFnPjwaArbDtGGDew4u+DRhPpeQ1Pq5HaThlBdEoV0jaFo\nUnUUDT" +
                                                   "Os6JqSuGOwYKN1FjVmswZlcVJg8f3GRpfetuTGCoL3Hjvb/uiJ+p4QakiiKD" +
                                                   "ZNyjDTqTli\nkJzDUCy5H09jJc90Q0nqDhtMosXEzOeGqekwbDLnIHoYhZOo" +
                                                   "0VI5TYZ6k0XmCjBXLGzjnCLYKynB\nFigssQnDukm0oRI7wFxfjgliu3jpSm" +
                                                   "ggsoi/HAN1hASg9eqS1lLbClWt8Kmx2w4dfzGMouMoqpuj\nnJgKmjDgN45a" +
                                                   "cyQ3SWxnSNOINo7aTEK0UWLr2NDnBNdx1O7oWROzvE2cNHGoMc0B2528RWzB" +
                                                   "s/gw\niVpVrpOdVxm1SzbK6MTQiv8aMgbOgtodntpS3a38OSjYrINgdgarpI" +
                                                   "hSf0A3weM9QYySjv3bAQBQ\nIznCpmiJVb2J4QFql740sJlVRpmtm1kAbaB5" +
                                                   "4MLQippEua0trB7AWTLBUFcQLiVfAVSTMARHYWhZ\nEExQAi+tCHjJ5591HZ" +
                                                   "8fOfXMuS0itus1ohpc/mZA6g4gpUmG2MRUiUS8nI8/ndibXxlCCICXBYAl\n" +
                                                   "zNDas7uTf/l5j4S5vgrMzsn9RGUT6t1He9IP3UlRmIuxyKKOzp1fprlIh5T7" +
                                                   "ZrBgQdZ2lCjyl/Hi\ny/PpX+x95DT5awg1J1CjSo18DuKoTaU5SzeIfScxiY" +
                                                   "0Z0RKoiZjasHifQBHYJyHk5dOdmYxDWALV\nG+JRIxX/wUQZIMFN1AR73czQ" +
                                                   "4t7CbErsCxZCKAIXGoCrFcmfuDN0W1xx8mbGoDOKY6sKtbOl/yq1\nieJMYY" +
                                                   "3YSmqKmtlRsY/z+LEYSitTNEcUrGJTN6mS1SFjVXqzRqb5fUFUC1ze9pm6Ol" +
                                                   "4Ag4lsQA7c\nRQ2AnVBPXvrg0Mj2bxyRQcID29WUoTXALO4yi3Nmccks7mOG" +
                                                   "6uoEj6WcqXQVGPoApCwUt9abRu/f\n9uCRvjDEiDVTD1bioH2gkyvJiEqHvb" +
                                                   "xOiBKoQnB1vbDvcPzyyc0yuJTa5bcqdsuHr1w4/s/WgRAK\nVa+NXEOozs2c" +
                                                   "TIoX1FLN6w9mUzX6f39ix5mPL3z6BS+vGOqvSPdKTJ6ufUFf2FQlGhRAj/yJ" +
                                                   "5dHw\nvWjsaAjVQw2Auifkh5LSHeRRlraDxRLIdYkkUUuG2jls8FfFutXMpm" +
                                                   "w64z0RQRIT+yXgnBYexx1w\ntbuBLe787TKLrx0yqLi3A1qIEnv5scZbfvdm" +
                                                   "y3vCLMVqHPX1u1HCZG63ecGyyyYEnn/6g9RT3/vs\n8D4RKW6oMGiC+UlDVw" +
                                                   "uAcoOHAkltQGHhjuzfbeaopmd0PGkQHnH/ja699cd/+3ZMusaAJ0XPrr8y\n" +
                                                   "Ae/58q+iRy488K9uQaZO5U3FU8MDk9os8SgP2Tae5XIUHv3Nqh/+Ej8LNQ/q" +
                                                   "jKPPEVE6kNAMCTsq\nwu4DYo0H3t3Klz6gvb5G6Fdp4RPqodPZvvzBX/1USN" +
                                                   "2C/bOA3w07sDUoPc+XNdy6ncHsvQs7UwD3\nxfN33xczzv8HKI4DRRVap7PT" +
                                                   "hrQvlDnRhW6IfPL2Ox0PfhRGoa2o2aBY24pF/KMmCDziTEHJKVib\nt4jYis" +
                                                   "0s4qtQGQkjrHANUPD9qwfhbqqd/lv5AOBlzsTk+lPJD3Y+KwxQM/Gr9L8Anb" +
                                                   "lzu49d/jW7\nKOh4GcixewuV9RSGJg/3Sx9PtzW+/lwuhCLjKKa6Y90YNvI8" +
                                                   "zsdhCnGKsx6MfmXvyycK2T4HSxVm\nZTD7fWyDue/VcdhzaL5vDaS7aFudcC" +
                                                   "12031xMN3rkNhsFij9Yr2xlJwRy9anMR/1QEIdWih/3clQ\np79r6DkYZXjC" +
                                                   "UVvWDr5u4MsW6eaNNcNhU6Wg17mCXldD0ARfhkAexyLqNcqz7Rrl6YIr6soT" +
                                                   "rSFP\nypWnwaIzMAjbqMt/FLH1HIw006J4X3q87633dz93WDa8eSK+DGtC/d" +
                                                   "rv/3Ag/J23JyVeMKwDwEe7\nT/zpzKX0Ulkc5ey7pmL89OPI+VfETdTihaJ3" +
                                                   "Pg4C+t11vS8/nL7oStRePsWNwEnnz7PvkBtv/9Yf\nqwwcEMMUs4Bj7lmAY2" +
                                                   "KuY2I1HPOA65gIyJPGs46gMGpJTnsYCsNRISDGxDxiCAPd4CtbdVWDT4xQ\n" +
                                                   "cmzitlxVa3IXdjy85x+tj+N37w+5LWEvgypKrZsNMk2MQIVcVTZR7RBnFa8i" +
                                                   "PfHiS2fZR+s2SY8M\n1I6tIOLApuNzPZtee3IBc1RPQLcg6bbp6+8JT+nvh8" +
                                                   "RxSha4imNYOdJgeVlrBnnytrmrrLitLp9l\nBuFa7obC8qqzjOc4rxGHhF1D" +
                                                   "RRd2V7hQqErglMc7fRGsww82Ku9DqYRgw+Zp9XN8OQizTt7SoJoG\nIzEySa" +
                                                   "lBsOkFon2lfCj2UfEnV7KIKJob4Op1LdJb1SJVa32TZVMG3YhohYAqdeXxvq" +
                                                   "pqvMPJmH8b\nIILDkXmM8U2+fJ2h5ixhd0BPyTvkSmXcM8xjCzSMCJVb4Frr" +
                                                   "GmbtVYfKtWv//Xm0/xFfnmKoBbRP\nAx6P+qtX/+n/R/2vwKW46isLzZT51b" +
                                                   "+SJoLNiXnMc5ovzzO02FExg+yDUyhzi5AvYeqnqa55Vnnh\naq0CY0yL71zL" +
                                                   "B/uuig9g8qONmvzkofs+T/723+KEVvqw0pJEizJ5w/DPXr59o2WTjC40aZGT" +
                                                   "mCVu\nb1TrEvKgDVVBboSkZyT8WYZiQXhQm9/8YD8DfXxgUEncnR/oLWh1AM" +
                                                   "S356yig2JisOczaFzOoIUy\nQ3HLrClrIuKbZLHQ5+VXyQl1zyv7Vhee3PVd" +
                                                   "0T0aVAPPiVLXnEQReTItNYvemtSKtD5Er7829uar\nXy42Q3FyWeorWGWxvU" +
                                                   "G+ncfx0KCqHwdHchYTB7i5n3S+cfvJYxdD4kD6PwUoICFKFgAA");
}
