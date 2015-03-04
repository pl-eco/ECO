package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class AmbientOcclusionShader implements Shader {
    private Color bright;
    private Color dark;
    private int samples;
    private float maxDist;
    public AmbientOcclusionShader() { super();
                                      bright = Color.WHITE;
                                      dark = Color.BLACK;
                                      samples = 32;
                                      maxDist = Float.POSITIVE_INFINITY; }
    public AmbientOcclusionShader(Color c, float d) { this();
                                                      bright = c;
                                                      maxDist = d; }
    public boolean update(ParameterList pl, SunflowAPI api) { bright = pl.
                                                                         getColor(
                                                                           "bright",
                                                                           bright);
                                                              dark =
                                                                pl.
                                                                  getColor(
                                                                    "dark",
                                                                    dark);
                                                              samples =
                                                                pl.
                                                                  getInt(
                                                                    "samples",
                                                                    samples);
                                                              maxDist =
                                                                pl.
                                                                  getFloat(
                                                                    "maxdist",
                                                                    maxDist);
                                                              if (maxDist <=
                                                                    0)
                                                                  maxDist =
                                                                    Float.
                                                                      POSITIVE_INFINITY;
                                                              return true;
    }
    public Color getBrightColor(ShadingState state) { return bright;
    }
    public Color getRadiance(ShadingState state) { return state.
                                                     occlusion(
                                                       samples,
                                                       maxDist,
                                                       getBrightColor(
                                                         state),
                                                       dark);
    }
    public void scatterPhoton(ShadingState state, Color power) {
        
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVYb2wcRxWfO/8523F8jtM4rnHsxHUi4pRbAkpRcEjrXJ3E" +
                                                    "4RpbdhIJR/Q6tzd3t/H+6+6cfXFq2kaqHEVqQOCmadVagFKVlrSpEFFBqFK+" +
                                                    "QFuVL0UIxAdaxBcqSj7kA6WiQHlvZvf2bu/sJnzB0s7Nzr55f+a993tvfOUG" +
                                                    "aXIdssu29NN53eIJVuKJU/qeBD9tMzdxJLVnkjouyyZ16rrHYC2tDr4W/+iT" +
                                                    "7xQ6o6R5hmykpmlxyjXLdKeYa+lzLJsi8WB1TGeGy0ln6hSdo0qRa7qS0lw+" +
                                                    "kiLrKrZyMpTyVVBABQVUUIQKymhABZvWM7NoJHEHNbn7MPkWiaRIs62iepxs" +
                                                    "q2ZiU4caHptJYQFwaMH3E2CU2FxyyNay7dLmGoOf2qUsP/1g508aSHyGxDVz" +
                                                    "GtVRQQkOQmZIu8GMDHPc0WyWZWfIBpOx7DRzNKprC0LvGdLlanmT8qLDyoeE" +
                                                    "i0WbOUJmcHLtKtrmFFVuOWXzchrTs/5bU06nebC1O7BVWngQ18HANg0Uc3JU" +
                                                    "Zf6WxlnNzHIyEN5RtnHo60AAW2MG4wWrLKrRpLBAuqTvdGrmlWnuaGYeSJus" +
                                                    "IkjhpHdVpnjWNlVnaZ6lOekJ003KT0DVKg4Ct3CyKUwmOIGXekNeqvDPjaP7" +
                                                    "LpwxD5tRoXOWqTrq3wKb+kObpliOOcxUmdzYPpy6SLvfOBclBIg3hYglzeuP" +
                                                    "3Lzv7v7rb0maz9WhmcicYipPq5czHe/2JXfubUA1WmzL1dD5VZaL8J/0voyU" +
                                                    "bMi87jJH/JjwP16f+tU3HnuZfRglbeOkWbX0ogFxtEG1DFvTmXOImcyhnGXH" +
                                                    "SSszs0nxfZzEYJ7STCZXJ3I5l/Fx0qiLpWZLvMMR5YAFHlEM5pqZs/y5TXlB" +
                                                    "zEs2ISQGD9kDTyuRf+KXk6JSsAymUJWammkpELuMOmpBYaqVdphtKWPJCSUD" +
                                                    "p1wwqDPrKm7RzOnWfFotutwyFNdRFcvJ+8uKajlMcQs0yxxl1MhozOQTqqoX" +
                                                    "XTiFabGcwPCz/1+CS3ginfORCDirLwwVOmTZYUsH2rS6XDwwdvPV9DvRcup4" +
                                                    "Z8lJAuQmPLkJlJuQchP15ZJIRIi7A+XLuACvzgI+AHK275z+5pGHzg02QEDa" +
                                                    "843gkiiQDsIheEqNqVYyAJFxAZUqRHLPD08uJT5+8V4ZycrqiF93N7l+af7x" +
                                                    "E49+MUqi1dCNRsJSG26fRMAtA+tQOGXr8Y0vffDR1YuLVpC8VbXAw5TanYgJ" +
                                                    "g2F3OJbKsoCyAfvhrfRa+o3FoShpBKABcOUUkgFwqz8sowobRnycRVuawOCc" +
                                                    "5RhUx08+OLbxgmPNBysiTjrEfAM4ZR0myzZ44l72iF/8utHG8Q4ZV+jlkBUC" +
                                                    "xw/+/Poz157dtTdaCfnxiiI6zbgEkA1BkBxzGIP1P16a/N5TN5ZOiggBirvq" +
                                                    "CRjCMQlwAi6DY33irYf/8P57l38bLUdVhENdLWZ0TS0Bjx2BFAAbHQAPfT90" +
                                                    "3DSsrJbTaEZnGJz/im/ffe1vFzqlN3VY8YPh7s9mEKzfeYA89s6D/+gXbCIq" +
                                                    "FrvA8oBMHsDGgPOo49DTqEfp8d9seeZN+jxgMeCfqy0wAWlEWEbE0SvCVcNi" +
                                                    "TIS+7cZhq13zTSz01vq42/Nxd10f4zAUkhb1MhffN3GyuRIgNAPqIp4StAAO" +
                                                    "2blGa+ZoBlSLOa+cKYtd788+98ErMsHDtS9EzM4tn/80cWE5WtEg3FVToyv3" +
                                                    "yCZBmLReHsGn8BeB5z/4oOm4IItEV9KrVFvLpcq2MZC2raWWEHHwL1cXf/Gj" +
                                                    "xSVpRld1fRyD9u+V3/3714lLf3q7DtBCpliUCx3vWcO/9+Pw5Vr/Sgf3iLfG" +
                                                    "tc/+IPZkFeD6zwk9c/bPHwudauCxjjtC+2eUK8/1Jvd/KPYHOIW7B0q1hQf6" +
                                                    "12Dvl142/h4dbP5llMRmSKfqNccnqF5ENJiBhtD1O2ZooKu+Vzd3spMZKeNw" +
                                                    "XzgeKsSGETLwA8yRGudtIVBsx1O+E542L2HawgkTIWKSElsGxbgdh8/7mBSz" +
                                                    "HW2OYudNmjOOli8IhPmKSDTp0EPV4jb7ier/1hE3hcNRDnZD4/AZ/Hr8if9b" +
                                                    "h99xj1/MpYatM1dw2IvD12SI3ctJA7Tpq0vphWe9J2X9KlJO+lIMWrrfg9p9" +
                                                    "kmUpwBwZzZG6SCNaEdlzYG5uWa3HFnl5+ezySnbihd1RL4nu46SVW/YXdDbH" +
                                                    "9FDibKlqRx4Qt4ogYM+/9OPX+bu7viozfHj1JAtvfPPsX3uP7S88dBtNyEDI" +
                                                    "pjDLlx648vahHep3o6ShHPc1F6XqTSPV0d7mMLjZmceqYr6/7M6Nfsz3ee7s" +
                                                    "+1+LRH+N64SpDO5hWPN8su5Ksmn5Ozo5LsToa4CiABsN0qpoZyHB6sVsLGNZ" +
                                                    "OqPmKpWRlY3GhwzAs8MzesctGx2pjtctdeMV7qB4C2eCzZk1jHoUB8CKjjzj" +
                                                    "BwRciLoqcvzWrEB3DXtWDN+uFfj6iKB6Yg0ll3A4y8k6UHIKjMMguzUNBaDt" +
                                                    "hyfhaZi43eASGgphgvTJNdT8Ng7nOVR/lXIIusmCxb3cC8VJ45ylZeuUVwjO" +
                                                    "+jce7ON6av4PI/93oL66Em/ZvHL896KHL9/vW+GSnSvqemXdqZg32w7LaULv" +
                                                    "VlmFJCg+XQ8C5W0MQl9OhOIXJf2znHSG6cFA/Kkkex68V0GG0C9nlUTfB9QH" +
                                                    "Ipz+wPYDvFO0r1h/E7L+lkgFmGIHX/lW1c4jXor/cfnYVpT/5UqrV1eOHD1z" +
                                                    "854XBFA2qTpdWEAuLSkSkzeZMj5uW5Wbz6v58M5POl5r3e7jfgcOXd71JaTb" +
                                                    "QP0uf8ywuejLF362+af7Xlx5T1wz/gszgOE7fBQAAA==");
}
