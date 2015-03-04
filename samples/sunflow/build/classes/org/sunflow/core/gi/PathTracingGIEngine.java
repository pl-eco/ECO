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
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YXWwcVxW+O/53HK9/6sQJieO4TiFJ2aFAkYqrhnTlOA7b" +
                                                    "2rLdSHXUbu/O3N2deP5y5669cWtIIpCjVIoQuCVFxQ8oVWlJmwoRFYQq5QXa" +
                                                    "qrwUIRAPtIgXKkoe8kCpKFDOuTOzszu7dtMXLM3dO/eec+4595zznTO+coO0" +
                                                    "eJwcdB3zdMF0RIqVReqkeXdKnHaZlzqWuXuaco/paZN63hysZbWRV5IffPSd" +
                                                    "Yo9CWudJP7VtR1BhOLY3wzzHXGR6hiSj1XGTWZ4gPZmTdJGqJWGYasbwxFiG" +
                                                    "bKliFWQ0E6qgggoqqKBKFdTDERUwbWV2yUojB7WFd4p8gyQypNXVUD1B9tYK" +
                                                    "cSmnViBmWloAEtrx/TgYJZnLnAxXbPdtrjP4qYPq2vcf7flpE0nOk6Rhz6I6" +
                                                    "Gigh4JB50mUxK8e4d1jXmT5Pem3G9FnGDWoay1LvedLnGQWbihJnlUvCxZLL" +
                                                    "uDwzurkuDW3jJU04vGJe3mCmHr615E1aAFu3Rbb6Fh7BdTCw0wDFeJ5qLGRp" +
                                                    "XjBsXZA9cY6KjaNfBwJgbbOYKDqVo5ptCgukz/edSe2COiu4YReAtMUpwSmC" +
                                                    "7NxQKN61S7UFWmBZQQbjdNP+FlB1yItAFkEG4mRSEnhpZ8xLVf658eC9Fx+3" +
                                                    "j9qK1Flnmon6twPTUIxphuUZZ7bGfMauA5mn6bbXziuEAPFAjNinefWJm1+7" +
                                                    "c+j6Gz7NZxrQTOVOMk1ktcu57rd3pfff04RqtLuOZ6DzayyX4T8d7IyVXci8" +
                                                    "bRWJuJkKN6/P/PrhMy+y9xXSOUlaNccsWRBHvZpjuYbJ+ASzGaeC6ZOkg9l6" +
                                                    "Wu5PkjaYZwyb+atT+bzHxCRpNuVSqyPf4YryIAKvqA3mhp13wrlLRVHOyy4h" +
                                                    "pA0e8mV4uon/J38FsdWiYzGVatQ2bEeF2GWUa0WVaU6WM9dRx9NTag5uuWhR" +
                                                    "vuCpXsnOm85SVit5wrFUj2uqwwvhsqo5nKkFA+JBFOc41SC8JibH7QLonMK4" +
                                                    "c//vJ5bxDnqWEglwz644OJiQV0cdU2c8q62V7h+/+XL2LaWSLMHtCfJZODAV" +
                                                    "HJjCA1MFI9XgQJJIyHNuw4P9EAAHLgAUAEh27Z995Nhj50eaIPbcpWa4fSQd" +
                                                    "AbMDbcY1Jx3hxaRERQ2CdvBHJ1ZTHz5/yA9adWNwb8hNrl9aOnv8m19QiFKL" +
                                                    "0mgdLHUi+zRiawVDR+PZ2UhucvW9D64+veJEeVoD+wF81HNi+o/E/cAdjekA" +
                                                    "qJH4A8P0Wva1lVGFNAOmAI4KChcMEDUUP6MGBsZCSEVbWsDgvMMtauJWiIOd" +
                                                    "osidpWhFBki3nPeCU7ZgXgzB0xskivzF3X4Xx9v8gEIvx6yQkH3kF9efufaD" +
                                                    "g/co1eierKqXs0z4WNEbBckcZwzW/3Rp+ntP3Vg9ISMEKG5vdMAojmlADnAZ" +
                                                    "XOu33zj1x3ffufw7JYoqASW0lDMNrQwy7ohOAVwxAdvQ96MP2ZajG3mD5kyG" +
                                                    "wfnv5L67rv39Yo/vTRNWwmC485MFROs77idn3nr0n0NSTELDuhZZHpH5F9Af" +
                                                    "ST7MOT2NepTP/nb3M6/THwLsAtR5xjKT6JUI8gWVGgAQrsvHKVeqJX2jSrID" +
                                                    "ckyh8yQzkXtfwmHYrdsry5XBSlru3zjLjmD9rsrOf02ZuXN/+VCaXJdfDcpW" +
                                                    "jH9evfLszvR970v+KNCRe0+5HrKg14l4v/ii9Q9lpPVXCmmbJz1a0Egdp2YJ" +
                                                    "w2kemgcv7K6g2arZr20E/Ko3VknkXfEkqzo2nmIRVMIcqXHeGcuqLrzlQXiS" +
                                                    "QVYl41mVIHIyJllG5LgPh8+FQd3mcmORYpdG2jxquSbzNnfUNDcsKL+LQX+g" +
                                                    "rvS9u/Dsey/5MBr3SoyYnV+78HHq4ppS1XHdXtf0VPP4XZc0e6tv9sfwl4Dn" +
                                                    "v/igubjgV92+dFD6hyu133UxXfduppY84shfr6788scrq74ZfbUNxzj00y/9" +
                                                    "/j+/SV3685sN6lgTNJMSvVyp52hN0Ae5taMut8Lqhvrt3qhxk7pdPre2rk89" +
                                                    "d5cSZNtRQTqE437eZIvMrDqsGSXVFL4HZKsaRfaFF37yqnj74Fd9Kw9s7OQ4" +
                                                    "4+vn/rZz7r7iY5+i3O2J2RQX+cIDV96cuEP7rkKaKglS133XMo3VpkUnZ/C5" +
                                                    "YM/VJMdQJTn6MShG4BkIkmOgYcmJXNYY2x7eZO8EDsehEnF2qmTAl8t00RFw" +
                                                    "K5L4MA7jPgROQHblHMdk1K5HSbkwU6v3dnh2BHrvuGW9Y4C+rS7oZjVIDMmv" +
                                                    "b2KWbHCpwMbXkJH9yCcq3RvW9+FA6eFbVlqREpVQ6d31ShepDu0gflKykGp7" +
                                                    "NZVhwUcS1lGHy2PsTWyT9iwIsrUAbT/UR93AyMLFU7dmJEbUocDIQ5/WM/jq" +
                                                    "SKrlTXR8AoclQXpBxwnTyVFzZnM9Abz7GzTO2A4M1n25+1+b2svryfbt6w/9" +
                                                    "QbaClS/CDvgsy5dMs7r6VM1bXc7yhlSyw69FPuadBQUadPOCKAVDanvGp/uW" +
                                                    "ID1xOogz/KkmWxVkSxUZliZ/Vk10AZAXiHD6pBvGRY/sfrD6pvzqWyZVCIkN" +
                                                    "YPVbTTeIICj/GxICVsn/f0hWu7p+7MHHb37lOYl+LZpJl5dRSnuGtPmNcAX0" +
                                                    "9m4oLZTVenT/R92vdOwLwbwbh76g+43ptqdxkzhuuUK2dcs/3/6ze59ff0d2" +
                                                    "qf8Dr4kSD6YSAAA=");
}
