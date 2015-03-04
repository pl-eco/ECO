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
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAMVXW2wUVRg+u73RAr0hV6FcrGhBdyAGEkTRWgsUV7ppgYQa" +
                                                    "WM/OnN0dOjtnnDnbLtUqkBgID8RoUTTaBwPBG5cYiRpDwpNo8EVjND54edOo" +
                                                    "PPCiJqj4/+fs7GV2W/TJTebM7H/+c/77d/7zzlVS57lktcOt/SmLiwjLicg+" +
                                                    "a11E7HeYF9kWXRejrseMHot63g6gxfUV51t+u/5cujVM6ofIHGrbXFBhctsb" +
                                                    "YB63RpgRJS1Faq/FMp4grdF9dIRqWWFaWtT0xMYomVmyVJDOqK+CBipooIIm" +
                                                    "VdC6i1ywaDazs5keXEFt4T1BniahKKl3dFRPkOXlmzjUpZn8NjFpAewwA//v" +
                                                    "AqPk4pxLlhVsVzZXGHx8tTbx0t7Wd2tIyxBpMe1BVEcHJQQIGSKzMiyTYK7X" +
                                                    "bRjMGCJtNmPGIHNNapljUu8h0u6ZKZuKrMsKTkJi1mGulFn03CwdbXOzuuBu" +
                                                    "wbykySzD/1eXtGgKbJ1XtFVZuBnpYGCTCYq5Saozf0ntsGkbgiwNrijY2PkI" +
                                                    "MMDShgwTaV4QVWtTIJB2FTuL2iltULimnQLWOp4FKYIsmnJT9LVD9WGaYnFB" +
                                                    "FgT5YmoKuBqlI3CJIHODbHIniNKiQJRK4nN1+33HnrS32mGps8F0C/WfAYs6" +
                                                    "AosGWJK5zNaZWjhrVfRFOu/ikTAhwDw3wKx43n/q2oN3dVz6RPHcWoWnP7GP" +
                                                    "6SKun0w0f764p2tDDaoxw+GeicEvs1ymfyw/szHnQOXNK+yIkxF/8tLAx7sP" +
                                                    "vMV+CZOmPlKvcyubgTxq03nGMS3mbmE2c6lgRh9pZLbRI+f7SAN8R02bKWp/" +
                                                    "Mukx0UdqLUmq5/I/uCgJW6CLGuDbtJPc/3aoSMvvnEMIaYCHrIenkaiffAsy" +
                                                    "oqV5hmlUp7Zpcw1yl1FXT2tM53GXOVzr7enXEuDldIa6w57mZe2kxUfjetYT" +
                                                    "PKN5rq5xN+WTNZ27TPPS1GCutstko1ssnqBWLM0FVMKgpEcw/5z/TXIOfdI6" +
                                                    "GgpBuBYHwcKCOtvKLeCN6xPZh3qvnY1fCReKJ+9NQVBwJC84goIjSnBkCsEk" +
                                                    "FJLybkEFVGpAYIcBIgA8Z3UN7tn2+JEVNZCTzmgtRAVZV4Ab8lr16ryniCN9" +
                                                    "Ei11SOYFrz92OPLH6QdUMmtTg37V1eTSidGDu55ZEybhcvRGK4HUhMtjiLkF" +
                                                    "bO0MVm21fVsO//TbuRfHebF+y46DPKxUrkRYWBGMh8t1ZgDQFrdftYxeiF8c" +
                                                    "7wyTWsAawFdBoR4AujqCMsrgYaMPtWhLHRic5G6GWjjl42OTSLt8tEiRidKM" +
                                                    "Q7vKGQxgQEGJ0ps/vPTyhVdWbwiXAnpLyRE5yISCh7Zi/He4jAH92xOxF45f" +
                                                    "PfyYDD5w3FZNQCeOPQAWEA3w2LOfPPHN99+d/DJcTBgBp2Y2YZl6DvZYWZQC" +
                                                    "UGIBnGFYO3faGW6YSZMmLIZ592fL7Wsv/HqsVQXKAoof57tuvkGRvvAhcuDK" +
                                                    "3t875DYhHY+youVFNuWAOcWdu12X7kc9cge/WPLyZfoaIC2gm2eOMQlYRFpG" +
                                                    "pOsjMiJdcrw7MLcGh2VOxVxOUhbk/8k/y+XYicMdym/4eWcpZ0h+zxVkfkWJ" +
                                                    "q1JGBy+Z6vSSJ+/JQxOTRv+ptaos28tPhF5oeM589ddnkRM/fFoFWBoFd+62" +
                                                    "2AizSnSqQZFlcPCoPNiLRXH0zbffF5+vvleJXDU1EgQXXj7086Idm9KP/wcQ" +
                                                    "WBowPrjlm4++8+mWlfrzYVJTqP+KXqV80cZSN4BQl0FzZaNDkdIkw9whFWgD" +
                                                    "d8zBgC6Epyl/ksk3zs5xcLxFVSsO9wTyJiz9GfZj3FERY2kqg1YIE9Nnm1fK" +
                                                    "Nqje3bE+KebhaTJzGw7dUJpZx4DTHaLYNU1j7poZ6BVG8s2MNt7+/fCrP51R" +
                                                    "EQ12PgFmdmTi6I3IsYlwSXt4W0WHVrpGtYhSy9nKsTfgF4Lnb3zQBCSoFqG9" +
                                                    "J9+nLCs0Ko6DdbB8OrWkiM0/nhv/6I3xw+G8SzYI0pDg3GLUrqxYSbi/EGd8" +
                                                    "yGJ4mvNxbv7XcQ6V1/KSqrUMnS/2/kxus3uaOO7BYZcgM1NMDMA6TNmqMGFm" +
                                                    "oAVGyOTuTa2bicRNfjr77/+Sxfh3CIe4ZGXTWJDCISEg1joVkN355gSpURxi" +
                                                    "SrsBQWpHuGlUAVOwdIreBmF9QcWlS10U9LOTLTPmT+78Wp7WhWa+ETrqZNay" +
                                                    "Smq+tP7rHZclTal4ozqEHfni1VBZNV5QZOpDam4rfqC2BvnBQnyVsmUhsiVs" +
                                                    "kKH5r1ImcEANMOHnfscPfqs8zfAmFVHXhhwpO3Sc8iOo9HTH+pcXWh9Fs+pK" +
                                                    "G9fPTW7b/uS19ackJNfBVXhsTF6A4D6nepYCEi+fcjd/r/qtXdebzzfe7pdf" +
                                                    "WTcT0G1p9UO/N+MIeUyPfTD/vftOT34nu45/AK8Q8Y1pEAAA");
}
