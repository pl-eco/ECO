package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;
public class NormalShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { Vector3 n = state.getNormal(
                                                                       );
                                                   if (n == null) return Color.
                                                                           BLACK;
                                                   float r = (n.x + 1) * 0.5F;
                                                   float g = (n.y + 1) * 0.5F;
                                                   float b = (n.z + 1) * 0.5F;
                                                   return new Color(r, g,
                                                                    b); }
    public void scatterPhoton(ShadingState state, Color power) {  }
    public NormalShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUXXWwURXju+kdL4Ur5KwilhYKWwi3EYIJFtNYCxQMuLRAp" +
                                                    "gWO6O3e3sLez7M6112IVSEwJD8RoQTDaBwNBlL8YCRpDwpNA8AVjND4IvklU" +
                                                    "HnhBE1T8ZuZ+9vauRR68ZOdmZ77//z17D5U5NmqxqDEQMygLkhQL7jZWBNmA" +
                                                    "RZzg+tCKMLYdorUb2HE2w1lEnX8x8ODh2/EaPyrvQVOxaVKGmU5Np4s41Ogj" +
                                                    "WggFcqcdBkk4DNWEduM+rCSZbigh3WGtITTRhcpQUygjggIiKCCCIkRQ2nJQ" +
                                                    "gDSJmMlEO8fAJnP2ojeQL4TKLZWLx1BjPhEL2ziRJhMWGgCFCfx9KyglkFM2" +
                                                    "asjqLnUuUPhoizLy3s6az0pQoAcFdLObi6OCEAyY9KDqBEn0Ettp0zSi9aAp" +
                                                    "JiFaN7F1bOiDQu4eVOvoMROzpE2yRuKHSYvYgmfOctUq181OqozaWfWiOjG0" +
                                                    "zFtZ1MAx0HVGTlep4Rp+DgpW6SCYHcUqyaCU7tFNjaF5Xoysjk2vAgCgViQI" +
                                                    "i9Msq1ITwwGqlb4zsBlTupmtmzEALaNJ4MLQ7DGJcltbWN2DYyTCUJ0XLiyv" +
                                                    "AKpSGIKjMDTdCyYogZdme7zk8s+9jauO7DPXmX4hs0ZUg8s/AZDqPUhdJEps" +
                                                    "YqpEIlYvDh3DM64c8iMEwNM9wBLm8uv3X1pSf/W6hHmqCMym3t1EZRH1ZO/k" +
                                                    "W3Pam1eWcDEmWNTRufPzNBfhH07ftKYsyLwZWYr8Mpi5vNr19bb9n5Df/Kiq" +
                                                    "E5Wr1EgmII6mqDRh6Qax1xKT2JgRrRNVElNrF/edqAL2Id0k8nRTNOoQ1olK" +
                                                    "DXFUTsU7mCgKJLiJKmCvm1Ga2VuYxcU+ZSGEKuBBLfBUIfkT/wy9psRpgihY" +
                                                    "xaZuUgVil2BbjStEpYqDE5YBXnOSZtSg/Ypjqwq1Y9l3ldpEceJYI7aykdoJ" +
                                                    "bHSLlyCPMOt/pJ3ietX0+3xg8jnehDcgV9ZRA2Aj6kjy5Y775yM3/dkESFsE" +
                                                    "ShRwC6a5BTm3oOQWdHNDPp9gMo1zlT4Fj+yB3IaqV93cvWP9rkPzSyCYrP5S" +
                                                    "MCcHnQ/apUXpUGl7rgB0ijKnQhTWfbR9OPjn6RdlFCpjV+ui2Ojq8f4DW99c" +
                                                    "5kf+/LLLVYOjKo4e5sUyWxSbvOlWjG5g+O6DC8eGaC7x8up4uh4UYvJ8nu91" +
                                                    "gk1VokGFzJFf3IAvRa4MNflRKRQJKIwMQyBDzan38sjL69ZMjeS6lIHCUeEe" +
                                                    "fpUpbFUsbtP+3ImIjsl8qZWBwh3oEVCU1zVfXj1x6f2WlX53JQ64els3YTKv" +
                                                    "p+T8v9kmBM5/Oh5+9+i94e3C+QCxoBiDJr62Q5aDN8Bib13f++Od2ye/8+cC" +
                                                    "hkG7S/YaupoCGotyXKAGGFCHuFubtpgJqulRHfcahMfdX4GFyy/9fqRGOsqA" +
                                                    "k4yflzyeQO581sto/82df9QLMj6V96Cc5jkwaYCpOcptto0HuBypA9/OPXEN" +
                                                    "fwglEsqSow8SUWmQ0AwJ0weFR5rFutRzt4wvDVbBXUqc1KXfxEujWJv48rS0" +
                                                    "G98+44b0if10hmYW5LVMZW7guWO1HdEyTx4cGdU2nVou07I2v5R3wKRy7vu/" +
                                                    "vwke//lGkWpSyai11CB9xHDJVMJZ5pWDDaIj55Li8JlPL7NbLc9LlovHrgRe" +
                                                    "xGsHf529eXV81xMUgXke5b0kz2w4e2PtIvUdPyrJ5n/BkJGP1Oo2AzC1CUxF" +
                                                    "JjcoP6kSbq4XAkwBc0zlDp0Fz8R0CxL//HaqxddpMlv58qwnbvzCnv6Mj+sL" +
                                                    "fCxUJTDD8MDMgM1wg3XL/7Zwp2DzyjiRuZ4vbZCaSUuDtgxebB5norb1BDT5" +
                                                    "vvQUogzV3tnzwd1z0qPekcUDTA6NHH4UPDLid811CwpGKzeOnO2ElJOkYR/B" +
                                                    "zwfPP/zhKvAD2dtr29MDRkN2wrAsngeN44klWKz55cLQVx8PDfvTJlnJUEUv" +
                                                    "pQbBZmHGioMXsn7mD5oDTyDt58B/9rMvP5fnFs1lGFn50E4EmW3j+HEHX7Yy" +
                                                    "NDFGWBfg8ZAtWib0BMyuvGRS+7HaiaBdnY7kTEQ/URTz1x6+RAQoGUeDGF96" +
                                                    "GfhaxQyiOxynLJ3kIb6EpXRdDJX2UV0rUkwZqnYPNLyW1xV8IsmxXj0/Gpgw" +
                                                    "c3TLD6JFZ0fvSph/o0nDcCW6O+nLLZtEdSFtpey8lvijxUqxHLEgs+RGiGtK" +
                                                    "eDit8cKDWvzPDZYEd7rAICzTOzcQaF0CQHw7YGU8XiNaGP/uCcohP4XyOo2V" +
                                                    "33fcLZ0nvfj8zJTOpPwAjagXRtdv3Hf/uVOiDpfBh+vgoPhcga8vOahky2/j" +
                                                    "mNQytMrXNT+cfLFyYSbn8kYYj2zzinf6joTFRG8e/GLm56tOj94Wo8a/mZkd" +
                                                    "+xcQAAA=");
}
