package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Color;
public class TexturedWardShader extends AnisotropicWardShader {
    private Texture tex;
    public TexturedWardShader() { super();
                                  tex = null; }
    public boolean update(ParameterList pl, SunflowAPI api) { String filename =
                                                                pl.
                                                                getString(
                                                                  "texture",
                                                                  null);
                                                              if (filename !=
                                                                    null)
                                                                  tex =
                                                                    TextureCache.
                                                                      getTexture(
                                                                        api.
                                                                          resolveTextureFilename(
                                                                            filename),
                                                                        false);
                                                              return tex !=
                                                                null &&
                                                                super.
                                                                update(
                                                                  pl,
                                                                  api);
    }
    public Color getDiffuse(ShadingState state) {
        return tex.
          getPixel(
            state.
              getUV().
              x,
            state.
              getUV().
              y);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1414698348000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVYe2wcxRkf3/kRn438SOKYkNixY15OclsQqdqYKjHGAZML" +
       "Nj7HIU6oGe/OnTfZ\n21l2Z+2ziXhKJIB4RC0SlUqIUKSENxKggMQjiDdRJa" +
       "hUKiGRtorUVmpBQpVoEPzRb2Z2bx9356LW\n0u3OznyP+eb7vt/3jZ/7CtU5" +
       "NlqjOmm2YBEnPZQdw7ZDtCEDO84ETE2r79c1jp3YYdIEqsmghK4x\n1JJRHU" +
       "XDDCu6poxcO1C00QaLGgt5g7I0KbL0fmOzJ++GzOYygbuPnmq/+3htdwLVZV" +
       "ALNk3KMNOp\nOWyQgsNQa2Y/nsOKy3RDyegOG8igC4jpFoao6TBsMuc2dAdK" +
       "ZlC9pXKZDPVkfOUKKFcsbOOCItQr\nY0ItSFhuE4Z1k2iDJXXAuTHKCdv2+M" +
       "bLqUHIMr44CeaIHYDV60pWS2vLTLWSJyd/evDY00nUMoVa\ndDPLhalgCQN9" +
       "U6i5QAozxHYGNY1oU6jNJETLElvHhr4otE6hdkfPm5i5NnHGiUONOU7Y7rgW" +
       "sYVO\nfzKDmlVuk+2qjNqlM8rpxND8r7qcgfNgdkdgtjR3O58HA1M6bMzOYZ" +
       "X4LLUHdBM83h3nKNnYtwMI\ngLWhQNgsLamqNTFMoHbpSwObeSXLbN3MA2kd" +
       "dUELQ6urCuVnbWH1AM6TaYY643RjcgmoGsVBcBaG\nVsbJhCTw0uqYl0L+2d" +
       "Dx7eGTv31rm4jtWo2oBt9/Cpi6YkzjJEdsYqpEMp53078e2eOuSSAExCtj\n" +
       "xJJm8OJTuzJ/f7tb0lxUgWZ0Zj9R2bR645Hu8duvoyjJt7HMoo7OnR+xXKTD" +
       "mLcyULQgaztKEvli\n2l88Pf7BnrueIf9IoNQIqlep4RYgjtpUWrB0g9jXEZ" +
       "PYmBFtBDUSUxsS6yOoAcYZCHk5O5rLOYSN\noFpDTNVT8Q1HlAMR/IgaYayb" +
       "OeqPLcxmxbhoIYQa4Iea4ZdC8k+8GfpFWnFcM2fQecWxVYXa+dK3\nSm2iOL" +
       "NYI7YyAckBAa/txraWFVNpHkYWQ3uUWVogClaxqZtUyeuQuCrdpJE5/v5/hB" +
       "f57tvna2o4\nHMbT2oCMuJ4aQDutnjj3ycHhHfcfliHDw9yzm6F+0Jn2dKa5" +
       "zrTUmS7XiWpqhKoVXLf0H5z+Achj\nQLzmy7O33HDr4d4kBI41XwtHx0l7wU" +
       "JvQ8MqHQqSfUTgogoR1/nU3kPp8ye2yohTqmNyRe6mT58/\nc+xfzf0JlKgM" +
       "mNxQgOwUFzPGUbYEhH3xFKsk/+sHdr78+ZkvLwuSjaG+Mgwo5+Q53Bt3iU1V" +
       "osGZ\nBuKPX9iS3I0mjyRQLQADgKHYP+BMV1xHJJcHfFzktjRkUFOO2gVs8C" +
       "UfzFJs1qbzwYyIlVYxXg7O\naeLBvRZ+F3jRLt58daXFnx0ytri3Y1YI3D1/" +
       "b/1P/vhG0/viWHyIbgkVwSxhMuHbgmCZsAmB+S8f\nH/vVY18d2isixQsVBp" +
       "XRnTF0tQgslwQskOkGoA13ZN8us0A1PafjGYPwiPuh5eIrXv3nw63SNQbM\n" +
       "+J7d+N8FBPMXXoPuOvPLf3cJMTUqrzSBGQGZtGZ5IHnQtvEC30fx7t+v/c2H" +
       "+AkAQgAfR18kAk+Q\nsAyJc1TEufeLZzq2dgV/9ILsjVVCv0Jdn1YPPpPvdW" +
       "/7+HWx6yYcbhDCbtiJrQHpef5Yz093VTx7\nr8fOLNBddfrGfa3G6e9B4hRI" +
       "VKGeOqM2pH0x4kSPuq7hi3fe7bj1syRKbEcpg2JtOxbxjxoh8Igz\nC8hTtL" +
       "ZuE7HVOr+MP4XJSBzCau8AiqEvjheXV0//7bwrCDJnembjycwno0+IA6ia+B" +
       "WKYkzO4lu7\njp7/HTsr5AQZyLl7iuWwCp1UwPuzz+fa6l96spBADVOoVfV6" +
       "vUlsuDzOp6A1cfwGEPrByHq0zZA1\ndaCEMGvi2R9SG8/9AM5hzKn5uDmW7r" +
       "yuoU4/7/13ON1rkBhsFSx94nlpKTkbLFufw7z/Q0l4itVV\nUOrLaodXNCR2" +
       "8OeV/LFNcmyuFA5io5dEggLiYG219ke0bodu/qb5PvzeLbJktEdbimFou/+2" +
       "8C65\n9OqH/lKh3jUyam0yyBwxQjoTXGWkVO0UnWHg6geefvYU+2zDFqmyv3" +
       "qYxhn7txxb7N7y4oP/Q4Hq\njh1CXHTb3EU3JWf1jxKieZWRU9b0RpkGovGS" +
       "gv24tjkRiZp10SIxAL82L2raKhaJwIMBwiW8c/Ui\npassUoSpBHpqDqE+WU" +
       "eYLCvfg2MjQs3kEhi6jz+yUERcC654BLzZGb4d2noBusw5UTrP3df75ke7\n" +
       "njwkHbkE3kS4ptU7//TnA8lH3pmRfHFQiREf6Tr+15fPja+Q8SevI+vLbgRh" +
       "HnklEca0WDwDepbS\nIKjf29Dz3B3jZ8WOON8OyNMZSg2CzSD1JpZIvWjS8Y" +
       "/RqOsVDzR88Phxrq/x0Nzz6doy1/NuEm5V\n/F5JhBhjCdcKbTpDqTxh1+q5" +
       "nOsQX/KqsGS9ALcrXu69i6Swfv+PtR6Arb284eUVv7PsuiyveGrm\ni9v3fZ" +
       "v5w3eidStdw5rgLpRzDSMMyqFxvWWTnC4Ma5IQbYnXQsyaUCMOUS0HYsNFSX" +
       "+QodY4PUO1\n/BUmu5OhphAZBIg3ChPdA6AORHx4r+Wf7aZq14JBU3cos6ml" +
       "q8FBFSOHyY9tfSSxxL83fBRz5T84\nptWbn9+7rvjgxKMCGutUAy8uipssXM" +
       "xlP1tCwp6q0nxZn6KXXpx844Wf+8kg+p0VxaAQReL8Srm6\nRHAA+lZuIocL" +
       "FhNt3+Jrq165+sTRswnRxv4HrbvRIZUSAAA=");
}
