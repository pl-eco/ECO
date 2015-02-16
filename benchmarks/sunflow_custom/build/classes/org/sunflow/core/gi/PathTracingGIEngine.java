package org.sunflow.core.gi;
import org.sunflow.core.GIEngine;
import org.sunflow.core.Options;
import org.sunflow.core.Ray;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class PathTracingGIEngine implements GIEngine {
    private int samples;
    public PathTracingGIEngine(Options options) { super();
                                                  samples = options.getInt(
                                                                      "gi.path.samples",
                                                                      16);
    }
    public boolean requiresPhotons() { return false; }
    public boolean init(Scene scene) { samples = Math.max(0, samples);
                                       UI.printInfo(Module.LIGHT,
                                                    "Path tracer settings:");
                                       UI.printInfo(Module.LIGHT,
                                                    "  * Samples: %d",
                                                    samples);
                                       return true; }
    public Color getIrradiance(ShadingState state, Color diffuseReflectance) {
        if (samples <=
              0)
            return Color.
                     BLACK;
        Color irr =
          Color.
          black(
            );
        OrthoNormalBasis onb =
          state.
          getBasis(
            );
        Vector3 w =
          new Vector3(
          );
        int n =
          state.
          getDiffuseDepth(
            ) ==
          0
          ? samples
          : 1;
        for (int i =
               0;
             i <
               n;
             i++) {
            float xi =
              (float)
                state.
                getRandom(
                  i,
                  0,
                  n);
            float xj =
              (float)
                state.
                getRandom(
                  i,
                  1,
                  n);
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
            ShadingState temp =
              state.
              traceFinalGather(
                new Ray(
                  state.
                    getPoint(
                      ),
                  w),
                i);
            if (temp !=
                  null) {
                temp.
                  getInstance(
                    ).
                  prepareShadingState(
                    temp);
                if (temp.
                      getShader(
                        ) !=
                      null)
                    irr.
                      add(
                        temp.
                          getShader(
                            ).
                          getRadiance(
                            temp));
            }
        }
        irr.
          mul(
            (float)
              Math.
                PI /
              n);
        return irr;
    }
    public Color getGlobalRadiance(ShadingState state) { return Color.
                                                                  BLACK;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1166303834000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfW/93HJ//4MRJE8cxDiUJ3Ja2VKJGpMFyHKcH" +
                                                    "tmwnEo7AzO3O3W08t7vZnbMvBrdJ1MpRkKKqNTQg8IcqiEIDQYiIVhVSvrSA" +
                                                    "4AtVBeIDUPULCMiHfChFpS19b3b3dm/vbMKXWtq52Zn33rw3773fe+uLV0mD" +
                                                    "65C9tsVP5LglUqwkUsf47SlxwmZu6lD69gnquEwf5tR1p2FtVht4Mfn5l7/I" +
                                                    "dyikcYZ0U9O0BBWGZbqTzLX4PNPTJBmujnBWcAXpSB+j81QtCoOracMVQ2my" +
                                                    "IcIqyGA6UEEFFVRQQZUqqPtDKmDayMxiYRg5qCnc4+QnJJEmjbaG6gmys1KI" +
                                                    "TR1a8MVMSAtAQjO+HwGjJHPJIf1l2z2bqwx+dK+68usHOl6qI8kZkjTMKVRH" +
                                                    "AyUEHDJD2gqskGGOu1/XmT5DOk3G9CnmGJQbi1LvGdLlGjmTiqLDypeEi0Wb" +
                                                    "OfLM8ObaNLTNKWrCcsrmZQ3G9eCtIctpDmzdFNrqWXgA18HAVgMUc7JUYwFL" +
                                                    "/Zxh6oLsiHOUbRz8MRAAa1OBibxVPqrepLBAujzfcWrm1CnhGGYOSBusIpwi" +
                                                    "yNY1heJd21Sbozk2K0hvnG7C2wKqFnkRyCJIT5xMSgIvbY15KeKfq/feee4h" +
                                                    "86CpSJ11pnHUvxmY+mJMkyzLHGZqzGNs25N+jG569YxCCBD3xIg9mlcevvaj" +
                                                    "W/quvO7RfKsGzXjmGNPErHYh0/72tuHdd9ShGs225Rro/ArLZfhP+DtDJRsy" +
                                                    "b1NZIm6mgs0rk3++7+Rz7FOFtI6RRs3ixQLEUadmFWyDM2eUmcyhguljpIWZ" +
                                                    "+rDcHyNNME8bJvNWx7NZl4kxUs/lUqMl3+GKsiACr6gJ5oaZtYK5TUVezks2" +
                                                    "IaQJHvJ9eNqJ9yd/Bcmqh10Id5Vq1DRMS4XgZdTR8irTrNkM3G6+QJ05V9WK" +
                                                    "rrAKqls0s9xaUF1HUy0nV37XLIepOQPiQOSnHapBWI2OjZg50DWF8Wb/304q" +
                                                    "oc0dC4kEuGNbHAw45NFBi+vMmdVWinePXHth9k2lnBz+bQnybTgw5R+YwgNT" +
                                                    "OSNV40CSSMhzbsCDPZeDw+Yg9QEU23ZP3X/owTMDdRBr9kI93DaSDoC1vjYj" +
                                                    "mjUc4sOYREENgrT3N0eXU188s88LUnVtMK/JTa6cXzh15KffUYhSicpoHSy1" +
                                                    "IvsEYmkZMwfj2VhLbnL5488vPbZkhXlZAfM+XFRzYroPxP3gWBrTAUBD8Xv6" +
                                                    "6eXZV5cGFVIPGAK4KShcMEBSX/yMirQfCiAUbWkAg7OWU6ActwLcaxV5x1oI" +
                                                    "V2SAtMt5JzhlA+ZBHzydfmLIX9zttnG8wQso9HLMCgnRB/5w5fHLT+y9Q4mi" +
                                                    "eTJSH6eY8LChMwySaYcxWH///MSvHr26fFRGCFDcWOuAQRyHASnAZXCtP3/9" +
                                                    "+HsffnDhr0oYVQJKZjHDDa0EMm4KTwEc4YBl6PvBw2bB0o2sQTOcYXD+O7nr" +
                                                    "tsufnevwvMlhJQiGW75eQLi+5W5y8s0H/tknxSQ0rGOh5SGZdwHdoeT9jkNP" +
                                                    "oB6lU3/Z/vhr9CmAWYA211hkEq0Sfr6gUj0AulX5OG5LtaRvVEm2R44pdJ5k" +
                                                    "JnLvezj021V7JbnSW07L3Wtn2QGs15Hs/Nc4z5z++xfS5Kr8qlGmYvwz6sUn" +
                                                    "tw7f9ankDwMduXeUqiELepuQ97vPFf6hDDT+SSFNM6RD8xunI5QXMZxmoFlw" +
                                                    "g24KmquK/crC71W5oXIib4snWeTYeIqFUAlzpMZ5ayyr2vCWe+FJ+lmVjGdV" +
                                                    "gsjJkGQZkOMuHG4OgrrJdox5il0ZaXJpwebMXd9RE45RgHI77/cD6lLXh3NP" +
                                                    "fvy8B6Nxr8SI2ZmVs1+lzq0okQ7rxqomJ8rjdVnS7I2e2V/BXwKe/+KD5uKC" +
                                                    "V2W7hv1S31+u9baN6bpzPbXkEQc+urT0x98uLXtmdFU2GCPQPz//zn/eSp3/" +
                                                    "2xs16lgdNI8SvWyp52BF0Pu5taUqt4LqhvptX6tRk7pdOL2yqo8/fZviZ9tB" +
                                                    "QVqEZd/K2TzjkcPqUVJF4btHtqZhZJ999neviLf3/tCzcs/aTo4zvnb6k63T" +
                                                    "d+Uf/AblbkfMprjIZ++5+MboTdovFVJXTpCqbruSaagyLVodBp8H5nRFcvSV" +
                                                    "k6Mbg2IAnh4/OXpqlpzQZbWx7b519o7icAQqkcOOFw34UpnIWwJuRRLvx2HE" +
                                                    "g8BRyK6MZXFGzWqUlAuTlXpvhmeLr/eW69Y7BuibqoJuSoPEkPz6OmbJhpYK" +
                                                    "bHQNGdn3f63SnUF97/eV7r9upRUpUQmU3l6tdJ7q0A7iJyQLqDZHqYwCfBRh" +
                                                    "HbUceYy5jm3SnjlBNuagzYf6qBsYWbh4/PqMxIja5xu575t6Bl8tSbW4jo4P" +
                                                    "47AgSCfoOMqtDOWT6+sJ4N1do3HGdqC36kvd+7rUXlhNNm9ePfyubAXLX4At" +
                                                    "8BmWLXIerT6ReaPtsKwhlWzxapGHeadAgRrdvCBKzpDanvTofiZIR5wO4gx/" +
                                                    "omTLgmyIkGFp8mZRorOAvECE00fsIC46ZPeD1TflVd8SiSAkNoDRt4puEEFQ" +
                                                    "/vcjAKyi9/+PWe3S6qF7H7r2g6cl+jVonC4uopRm+Pj3GuEy6O1cU1ogq/Hg" +
                                                    "7i/bX2zZFYB5Ow5dfvcb021H7SZxpGAL2dYt/n7zy3c+s/qB7FL/B06FF4GW" +
                                                    "EgAA");
}
