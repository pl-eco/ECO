package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class DiffuseShader implements Shader {
    private Color diff;
    public DiffuseShader() { super();
                             diff = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { diff = pl.getColor(
                                                                          "diffuse",
                                                                          diff);
                                                              return true;
    }
    public Color getDiffuse(ShadingState state) { return diff;
    }
    public Color getRadiance(ShadingState state) { state.
                                                     faceforward(
                                                       );
                                                   state.
                                                     initLightSamples(
                                                       );
                                                   state.
                                                     initCausticSamples(
                                                       );
                                                   return state.
                                                     diffuse(
                                                       getDiffuse(
                                                         state));
    }
    public void scatterPhoton(ShadingState state, Color power) {
        Color diffuse;
        if (Vector3.
              dot(
                state.
                  getNormal(
                    ),
                state.
                  getRay(
                    ).
                  getDirection(
                    )) >
              0.0) {
            state.
              getNormal(
                ).
              negate(
                );
            state.
              getGeoNormal(
                ).
              negate(
                );
        }
        diffuse =
          getDiffuse(
            state);
        state.
          storePhoton(
            state.
              getRay(
                ).
              getDirection(
                ),
            power,
            diffuse);
        float avg =
          diffuse.
          getAverage(
            );
        double rnd =
          state.
          getRandom(
            0,
            0,
            1);
        if (rnd <
              avg) {
            power.
              mul(
                diffuse).
              mul(
                1.0F /
                  avg);
            OrthoNormalBasis onb =
              state.
              getBasis(
                );
            double u =
              2 *
              Math.
                PI *
              rnd /
              avg;
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
                  1.0 -
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
                    getPoint(
                      ),
                  w),
                power);
        }
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYXWwc1RW+O/53nOzGIYkJiZMYGzU/3WkqUSk1Cg3GIU43" +
                                                    "ZBWHCEwbcz1z1zvJ7Mxk5q69MXVJIpCjIEUIDISK+qEK4i8QVDWiFULKSwuI" +
                                                    "vlBVrfpQqPpSVJqHPPCj0gLn3Dv/u3aTh1qau3fuPeeec+455ztnfPEqafFc" +
                                                    "ss2xzROTps3zrMbzR83b8/yEw7z8vsLtRep6TB8yqecdgrVxre+N7GdfPlHO" +
                                                    "KaR1jKyilmVzyg3b8g4yzzanmF4g2Wh12GQVj5Nc4SidomqVG6ZaMDw+WCDL" +
                                                    "Yqyc9BcCFVRQQQUVVKGCujuiAqblzKpWhpCDWtw7Tn5KMgXS6mioHiebk4c4" +
                                                    "1KUV/5iisABOaMf3w2CUYK65ZFNou7S5zuCnt6nzzx7J/bKJZMdI1rBGUR0N" +
                                                    "lOAgZIx0VVhlgrnebl1n+hhZaTGmjzLXoKYxI/QeI92eMWlRXnVZeEm4WHWY" +
                                                    "K2RGN9eloW1uVeO2G5pXMpipB28tJZNOgq1rIlulhXtwHQzsNEAxt0Q1FrA0" +
                                                    "HzMsnZONaY7Qxv4fAgGwtlUYL9uhqGaLwgLplr4zqTWpjnLXsCaBtMWughRO" +
                                                    "1i16KN61Q7VjdJKNc9KTpivKLaDqEBeBLJysTpOJk8BL61Jeivnn6r13nHvY" +
                                                    "2mspQmedaSbq3w5MvSmmg6zEXGZpTDJ2bS08Q9e8fUYhBIhXp4glzZs/ufaD" +
                                                    "7b1X3pU0tzSgOTBxlGl8XLswseKD9UNbdjahGu2O7Rno/ITlIvyL/s5gzYHM" +
                                                    "WxOeiJv5YPPKwd89cPIV9olCOkdIq2ab1QrE0UrNrjiGydx7mMVcypk+QjqY" +
                                                    "pQ+J/RHSBvOCYTG5eqBU8hgfIc2mWGq1xTtcUQmOwCtqg7lhlexg7lBeFvOa" +
                                                    "Qwhpg4dsh6eLyD/xy8kDatmuMJVq1DIsW4XYZdTVyirTbNWjFccEr3lVq2Ta" +
                                                    "06rnaqrtTobvmu0y1StTnbnq3UapVPXYqHjLY4g5/8/Da2hZbjqTgUtfn055" +
                                                    "E7Jlr20C7bg2X71r+Nrr4+8rYQr4d8LJAIjL++LyKC4vxeUT4kgmI6TchGKl" +
                                                    "W8EpxyC9Afi6toz+eN9DZ/qaIJ6c6Wa4USTtA/t8XYY1eyjCgBGBdBoEYs8v" +
                                                    "HpzLf/HinTIQ1cUBuyE3uXJ++tThR76jECWJvGgbLHUiexHxMsTF/nTGNTo3" +
                                                    "O/fxZ5eembWj3EtAuQ8J9ZyY0n1pL7i2xnQAyej4rZvo5fG3Z/sV0gw4AdjI" +
                                                    "KcQywE5vWkYitQcDmERbWsDgku1WqIlbAbZ18rJrT0crIjxWiPlKcMoyjPUe" +
                                                    "eLJ+8Itf3F3l4HiTDCf0csoKAcN7fnPlucs/27ZTiSN2NlYDRxmX+b8yCpJD" +
                                                    "LmOw/tfzxaeevjr3oIgQoLi1kYB+HIcADcBlcK2PvXv8Lx99eOGPShRVHMpi" +
                                                    "dcI0tBqccVskBbDCBLxC3/ffZ1Vs3SgZdMJkGJz/yQ7suPyvcznpTRNWgmDY" +
                                                    "/r8PiNZvvoucfP/I573imIyGtSqyPCKTF7AqOnm369ITqEft1B82PPcO/TlA" +
                                                    "KcCXZ8wwgUhEWEbE1avCVVvFmE/t7cBhk1O3VxMrPWHWbVk8ifZgyY0l378P" +
                                                    "mBOn//6FsKgufRpUmhT/mHrx+XVDuz4R/FEcI/fGWj0eQXsS8X73lcqnSl/r" +
                                                    "bxXSNkZymt/7HKZmFaNlDOq9FzRE0B8l9pO1WxaqwTBP16dzKCY2nUERDsIc" +
                                                    "qXHemUoaUSDWwrPcT5rl6aTJEDHZKVj6xDiAw7eCmG1zXGOKYmMFWgKoCpLV" +
                                                    "nKyNI69RgcYB49AWN5hzhB79Ce824hOILTEak2LDYi2FaIcunJ5f0A+8sEPi" +
                                                    "bXeyTA9DF/ran/77+/z5v73XoE50cNv5tsmmmBnTqRlFJnB+v+i2Ik+fffnV" +
                                                    "N/kH274vRW5dPDrTjO+c/ue6Q7vKD90Aum9MGZ8+8uX9F9+75zbtSYU0hQFT" +
                                                    "10AmmQaTYdLpMuh4rUOJYOkNg2UVxsbNwST4rUPYyLNRriviPpXAx711Pham" +
                                                    "MuhPEUwCsjVxslH5u7s4IsQUl0CTwzjsBzitOjpE5tK4UXSNCjRwU36Hqc52" +
                                                    "f3Ts+Y9fkx5Ng0SKmJ2ZP/t1/ty8EuvZb61rm+M8sm8XWi6XF/s1/GXg+Qof" +
                                                    "NAEXZN/WPeQ3j5vC7tFxMA82L6WWELHnH5dm33ppdk7xr2QYEnXCtk1GrXqU" +
                                                    "FQsjoZ/xIbf41TSoqtfn50wylzc0zGX4HMEPMiaOYUv40cBhgpPOScb9fg1X" +
                                                    "7rw+C9b7TzC/IQvwtSSo7CUUPI4DpNkyUPAgGIY5dX0aim5lFzwDvoYDN5pL" +
                                                    "QkMhTJBOL6HmCRw4h4jTKIccK5Zt7kPN/Tj8SGp3BBB8yjb0BmUYeBMNM7YB" +
                                                    "PXVf4fLLUXt9Idu+duG+P4sWMPy664BPrFLVNONlKTZvdVxWMoS6HbJIyRpx" +
                                                    "qlFFkD08JLicCH1PSvpHOcml6cEu/ImTzYHTYmSQHf4sTnSWkyYgwunjThDT" +
                                                    "OdH9YHnOy/JcI7GSgQ1g/C3RDSL2iP9wBAhelf/jGNcuLey79+Fr33tBlIMW" +
                                                    "zaQzM3hKO3zgy0Y4rAKbFz0tOKt175YvV7zRMRCk/gocuv3uN6XbxsZN4nDF" +
                                                    "4aKtm/n12l/d8eLCh6JL/QY4G0e1ehIAAA==");
}
