package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class ViewGlobalPhotonsShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { state.faceforward();
                                                   return state.getGlobalRadiance(
                                                                  );
    }
    public void scatterPhoton(ShadingState state, Color power) { 
    }
    public ViewGlobalPhotonsShader() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1166301454000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XW2xURRie3d5oKd3SchOhQKloQfdAjCZYRGst0LrIpoUm" +
                                                    "lMAye87s7qFnzxzPmW2XYr0lBuIDMVoVjPbBQLyBGCNBY0x48hJ8wRiND4Jv" +
                                                    "GpUHXtTE6//P7PXstuqLm5zZc/75Z/77N/+cuUbqPJdscLh1OGlxEWZZET5k" +
                                                    "3REWhx3mhQcjd0Sp6zGjz6KetxtoMb3z7dDPvz2dag2S+lHSTm2bCypMbntD" +
                                                    "zOPWODMiJFSk9lss7QnSGjlEx6mWEaalRUxP9ETI/JKlgnRF8ipooIIGKmhS" +
                                                    "Ba23yAWLFjA7k+7DFdQW3kPkERKIkHpHR/UEWVO+iUNdms5tE5UWwA7z8HsE" +
                                                    "jJKLsy5ZXbBd2Vxh8HMbtOkXDrS+U0NCoyRk2sOojg5KCBAySprTLB1nrtdr" +
                                                    "GMwYJQttxoxh5prUMiel3qOkzTOTNhUZlxWchMSMw1wps+i5Zh1tczO64G7B" +
                                                    "vITJLCP/VZewaBJsXVK0VVm4DelgYJMJirkJqrP8ktox0zYEWeVfUbCx6wFg" +
                                                    "gKUNaSZSvCCq1qZAIG0qdha1k9qwcE07Cax1PANSBFk+66boa4fqYzTJYoIs" +
                                                    "8/NF1RRwNUpH4BJBFvvZ5E4QpeW+KJXE59qDW44fsXfYQamzwXQL9Z8Hizp8" +
                                                    "i4ZYgrnM1pla2Lw+8jxd8uGxICHAvNjHrHguPHz93ls7Ln6ieG6swrMrfojp" +
                                                    "IqafirdcXtHXvbkG1ZjncM/E4JdZLtM/mpvpyTpQeUsKO+JkOD95ceijvY+9" +
                                                    "wX4MkqYBUq9zK5OGPFqo87RjWszdzmzmUsGMAdLIbKNPzg+QBniPmDZT1F2J" +
                                                    "hMfEAKm1JKmey29wUQK2QBc1wLtpJ3j+3aEiJd+zDiGkAR5yJzyNRP3kvyC2" +
                                                    "tseDdNeoTm3T5hokL6OuntKYzmNx8G4qTd0xT9MznuBpzcvYCYtPaJ6ra9xN" +
                                                    "Fr517jLNS1GDudqIySa2WzxOrWiKC6iAYUkPY945/7vELPqgdSIQgPCs8IOD" +
                                                    "BXW1g1vAG9OnM/f1X38rdilYKJac9wRBweGc4DAKDivB4VkEk0BAyluECqhU" +
                                                    "gECOASQAWDZ3D+8fPHisswZy0JmohSggaydYn9OqX+d9RdwYkOioQ/Iue2Xf" +
                                                    "0fCvr96jklebHeSrriYXT0w8PvLoxiAJlqM1WgmkJlweRYwtYGmXv0qr7Rs6" +
                                                    "+v3P556f4sV6LYP/HIxUrkQY6PTHw+U6MwBYi9uvX03Pxz6c6gqSWsAWwFNB" +
                                                    "If8Bqjr8MsrgoCcPrWhLHRic4G6aWjiVx8MmkXL5RJEiE6UFhzaVMxhAn4IS" +
                                                    "lbe9f/Hk+Rc3bA6WAnio5EgcZkLBwcJi/He7jAH9mxPRZ5+7dnSfDD5wrK0m" +
                                                    "oAvHPgAHiAZ47MlPHvr66pVTXwSLCSPglMzELVPPwh7rilIAOiyALwxr1x47" +
                                                    "zQ0zYdK4xTDvfg/dtOn8T8dbVaAsoOTjfOs/b1Ck33AfeezSgV865DYBHY+u" +
                                                    "ouVFNuWA9uLOva5LD6Me2cc/X3nyY/oyICugmWdOMglQRFpGpOvDMiLdcrzN" +
                                                    "N7cRh9VOxVxWUpblvuTHGjl24XCz8hu+3lLKGZDviwVZWlHiqpTRwStnO63k" +
                                                    "SXvqiekZY9fpTaos28pPgH5ocM5++cdn4RPffloFWBoFd26z2DizSnSqQZFl" +
                                                    "cLBTHuTFonjq9TcviMsb7lIi18+OBP6FHz/xw/LdW1MH/wMIrPIZ79/y9Z1n" +
                                                    "Pt2+Tn8mSGoK9V/Rm5Qv6il1Awh1GTRTNjoUKU0yzB1SgYXgjnYM6A3wNOVO" +
                                                    "LvmPs+0OjotUteJwuy9vgtKfwXyMOypiLE1l0PpgYubZlpSyDav/3uiAFHP/" +
                                                    "HJk5iEMvlGbGMeA0hyh2z9GIu2YaeoPxXPOiTbVdHXvp+7Mqov5Ox8fMjk0/" +
                                                    "9Vf4+HSwpB1cW9GRla5RLaHUcoFy7F/wC8DzJz5oAhJUS9DWl+tLVhcaE8fB" +
                                                    "Olgzl1pSxLbvzk198NrU0WDOJZsFaYhzbjFqV1asJNxdiDM+ZAU8Lbk4t/zr" +
                                                    "OAfKa3ll1VqGThd7fSa32TtHHPfjMCLI/CQTQ7AOU7YqTJhpaHkRMrn7j9bN" +
                                                    "R+LWfDrn//9LFuPnKA4xycrmsCCJQ1xArHUqILtzzQlSIzhElXZDgtSOc9Oo" +
                                                    "AqZg6Sy9DcL6sopLlroY6G/NhOYtndnzlTytC817I3TQiYxlldR8af3XOy5L" +
                                                    "mFLxRnUIO/KPV0Nl1XhBkakXqbmt+IHa6ucHC/GvlC0DkS1hgwzNvZUygQNq" +
                                                    "gAlfDzv54LfK0wxvTmF1TciSskPHKT+CSk93rH95gc2jaEZdYWP6uZnBB49c" +
                                                    "v/O0hOQ6uPpOTsoLD9zfVM9SQOI1s+6W36t+R/dvLW833pQvv7JuxqfbquqH" +
                                                    "fn/aEfKYnnxv6btbXp25IruOvwFVdp0wWRAAAA==");
}
