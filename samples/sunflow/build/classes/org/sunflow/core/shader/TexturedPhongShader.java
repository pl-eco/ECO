package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Color;
public class TexturedPhongShader extends PhongShader {
    private Texture tex;
    public TexturedPhongShader() { super();
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
              getUV(
                ).
              x,
            state.
              getUV(
                ).
              y);
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAALVXW2wUVRg+O73RUtheoNQKBdpCLNUdMdEEa1RcCxQX2NBC" +
       "YomW05mzu0PnxszZdqnWC4mB+ECMVgWDfTAYvHAxRqLGkPCiQPBFYzQ+iMYX" +
       "icgDDyIRFf9z5rqz26oPbjIzZ8/5r+f//+/859gVVGVbqMc01D1Z1aAJUqCJ" +
       "XerdCbrHJHZiY+ruNLZsIidVbNuDMDcsdbwXv3bjhVyDgKqHUDPWdYNiqhi6" +
       "vZXYhjpG5BSKB7N9KtFsihpSu/AYFvNUUcWUYtPeFJobYqWoK+WZIIIJIpgg" +
       "chPEtQEVMM0jel5LMg6sU3s3egrFUqjalJh5FC0vFmJiC2uumDT3ACTMYf+3" +
       "g1OcuWChZb7vjs8lDr/cI069+njD+xUoPoTiij7AzJHACApKhlC9RrQRYtlr" +
       "ZZnIQ6hRJ0QeIJaCVWWC2z2Emmwlq2Oat4i/SWwybxKL6wx2rl5ivll5iRqW" +
       "715GIars/avKqDgLvrYEvjoermPz4GCdAoZZGSwRj6VyVNFlipZGOXwfux4B" +
       "AmCt0QjNGb6qSh3DBGpyYqdiPSsOUEvRs0BaZeRBC0VtMwple21iaRRnyTBF" +
       "rVG6tLMEVLV8IxgLRQujZFwSRKktEqVQfK5svu/AE/oGXeA2y0RSmf1zgKk9" +
       "wrSVZIhFdIk4jPWrUq/gltP7BYSAeGGE2KH58MmrD97efuacQ3NrGZotI7uI" +
       "RIelIyPzv1ic7F5TwcyYYxq2woJf5DlP/7S70lswofJafIlsMeEtntn62aPP" +
       "vEMuC6iuH1VLhprXII8aJUMzFZVY64lOLEyJ3I9qiS4n+Xo/qoFxStGJM7sl" +
       "k7EJ7UeVKp+qNvh/2KIMiGBbVANjRc8Y3tjENMfHBRMhVAMPqoenDjk//qVo" +
       "WMwZGhGxhHVFN0TIXYItKScSyRBtrJkqRM3O6xnVGBdtSxINK+v/lwyLiHYO" +
       "y8QSB6F6oCLkdM7QswN8LsESzfz/VRSYlw3jsRgEYHG0/FWonA2GCrTD0lT+" +
       "ob6rJ4YvCH45uPtDUQ8oTbhKE0xpwlGaKKMUxWJc1wKm3Ak0hGkUCh6gsL57" +
       "4LGNO/d3VECGmeOVsMeMtAN8dS3qk4xkgAr9HPskSM3WN3bsS1w/+oCTmuLM" +
       "EF6WG505OP7s9qfvFJBQjMXMQ5iqY+xphqA+UnZFa7Cc3Pi+S9dOvjJpBNVY" +
       "BO4uSJRysiLviMbCMiQiw2YG4lctw6eGT092CagSkAPQkmLIbgCi9qiOomLv" +
       "9YCT+VIFDmcMS8MqW/LQro7mLGM8mOFJMp+PGyEoc1n2t8Mzzy0H/mWrzSZ7" +
       "L3CSikU54gUH5nUfnzl06rWeNUIYw+OhU3GAUAcRGoMkGbQIgfnvDqZfevnK" +
       "vh08Q4Cis5yCLvZOAj5AyGBbnzu3+9vvLx75SgiyisJBmR9RFakAMlYGWgA9" +
       "VEAwFvuubbpmyEpGwSMqYcn5R3zF6lO/HGhwoqnCjJcMt/+zgGD+lofQMxce" +
       "/62di4lJ7PQKPA/InA1oDiSvtSy8h9lRePbLJYfO4tcBXAHQbGWCcIxC3DPE" +
       "t17koVrF34nI2mr2WmaWrBX4TKtfdd0zF9E6dgiHiu/3LerI3h+vc49KyqfM" +
       "2RPhHxKPHW5L3n+Z8wd5zLiXFkpRCRqWgPeud7RfhY7qTwVUM4QaJLcb2o7V" +
       "PMuWIegAbK9Fgo6paL34NHeOrl6/ThdHayikNlpBARrCmFGzcV2kaNjxgVq9" +
       "6vG+4aKJIT5Yw1k6+HsFe93m5WyNaSljmLVaqALenGIhnKol8OviLq9Ck9vR" +
       "FYouYkm/ZKYmgjdAR/ZOTctb3lzt4GlT8cHcB33n8a///Dxx8IfzZU6DWmqY" +
       "d6hkjKghnQJTWYTjm3h/FUTy+bff/ZB+0XOvo3LVzNkXZTy79+e2wftzO/8D" +
       "ei+NOB8V+famY+fXr5ReFFCFnxAlLWMxU29xGtRZBCKgDxYlQ7ufDM0sDLfA" +
       "0+gmQ2NZBA0iF9Sy4O6nG/z2kuBzVwl0pAwsPLKWMNmA812b7udqNs+CFoPs" +
       "9QjAZd6UIfNmx4W0pWjQso25PaU42fT96OFLx52IRkEgQkz2Tz1/M3FgSgh1" +
       "6Z0ljXKYx+nUuZXznI29Cb8YPH+xh7nAJpxOrSnptovL/H7RNFkdLJ/NLK5i" +
       "3U8nJz95a3Kf4G5JEgpxxDBUgvVSFOUT6/0487De6ha+BwD/Ls4xF4ndAC4p" +
       "iTPrp+ACwq5ghIuRZoljlr12UlSXJfRhJZPJ28STvCgsWdHgIsJOMcMqc0RQ" +
       "1FympWMHVWvJzdG57UgnpuNzFk1v+4Y3Kf6NpBauBZm8qoaBMzSuNi2SUbjh" +
       "tQ6MOiimR6wN9ZqQos6AW6059Lvhth2lp6iSfcJklKK5ITKIrzsKE8F0BRCx" +
       "YcH09q5zps43tD0FVIS9ZhSJO4sKil/UPVjKO1f1Yenk9MbNT1y9502OcVVw" +
       "xZ+Y4Bc7uKc63ZsPbctnlObJqt7QfWP+e7UrvHyez15NbssWsW1p+c6mTzMp" +
       "70UmPlr0wX1Hpy/y1upvqLqhmEERAAA=");
}
