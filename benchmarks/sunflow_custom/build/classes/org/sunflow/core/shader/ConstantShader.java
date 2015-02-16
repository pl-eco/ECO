package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class ConstantShader implements Shader {
    private Color c;
    public ConstantShader() { super();
                              this.c = Color.WHITE; }
    public boolean update(ParameterList pl, SunflowAPI api) { c = pl.getColor(
                                                                       "color",
                                                                       c);
                                                              return true;
    }
    public Color getRadiance(ShadingState state) { return c; }
    public void scatterPhoton(ShadingState state, Color power) {  }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1160854688000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YW2wc1Rk+O77FjmNvnDgJIXES40QkgR1SiUrBKBAshzjd" +
                                                    "kFWcRGAg5uzMWe/EszOTmbP2xmAuQSgRD1FVDISK+qEK4hYIQkSAEFJeuAle" +
                                                    "QFWrPnBRX4pK85CHUlRK6f+fM/ddm/JSS3P2zDn/9fz//51/fP4yafFcst2x" +
                                                    "zRMTps1zrMZzx8wbc/yEw7zcvvyNBep6TB8yqecdgrVxrf+17m+//3U5q5DW" +
                                                    "MbKCWpbNKTdsyzvIPNucYnqedEerwyareJxk88foFFWr3DDVvOHxwTxZGmPl" +
                                                    "ZCAfmKCCCSqYoAoT1N0RFTAtY1a1MoQc1OLecfIgyeRJq6OheZxsSgpxqEsr" +
                                                    "vpiC8AAkLMH3I+CUYK65ZGPou/S5zuEnt6tzTx/Nvt5EusdIt2GNojkaGMFB" +
                                                    "yRjprLBKkbnebl1n+hhZbjGmjzLXoKYxI+weIz2eMWFRXnVZeEi4WHWYK3RG" +
                                                    "J9epoW9uVeO2G7pXMpipB28tJZNOgK+rIl+lh3twHRzsMMAwt0Q1FrA0TxqW" +
                                                    "zsmGNEfo48CvgABY2yqMl+1QVbNFYYH0yNiZ1JpQR7lrWBNA2mJXQQsnaxcU" +
                                                    "imftUG2STrBxTtak6QpyC6jaxUEgCye9aTIhCaK0NhWlWHwu33HzmfutvZYi" +
                                                    "bNaZZqL9S4CpL8V0kJWYyyyNScbObfmn6Kp3TyuEAHFviljSvPnAlVuv67v0" +
                                                    "oaS5ugHNgeIxpvFx7Vyx69N1Q1t3NqEZSxzbMzD4Cc9F+hf8ncGaA5W3KpSI" +
                                                    "m7lg89LB9+96+CX2jUI6RkirZpvVCuTRcs2uOIbJ3NuZxVzKmT5C2pmlD4n9" +
                                                    "EdIG87xhMbl6oFTyGB8hzaZYarXFOxxRCUTgEbXB3LBKdjB3KC+Lec0hhLTB" +
                                                    "Q66Hp53IP/HLCVMPe5DuKtWoZVi2CsnLqKuVVabZ40U43XKFupOeqlU9bldU" +
                                                    "r2qVTHta9VxNtd2J8F2zXaZ6ZaozVw0KelS85jDdnP+Xohp6nJ3OZCAY69JQ" +
                                                    "YEIV7bVNoB3X5qq3DV95dfxjJSwN/6w42QL6cr6+HOrLSX25pD6SyQg1K1Gv" +
                                                    "jDdEaxLqHhCxc+vovfvuO93fBInmTDfDUSNpP/jqGzOs2UMROIwIwRpk6Jrf" +
                                                    "330q993zt8gMVRdG8obc5NLZ6UeOPHSDQpQkJKNzsNSB7AUE0hAwB9Kl2Ehu" +
                                                    "96mvv73w1KwdFWUC432sqOfEWu9Ph8G1NaYDekbit22kF8ffnR1QSDMACIAm" +
                                                    "p5DkgEd9aR2Jmh8M8BN9aQGHS7ZboSZuBaDXwcuuPR2tiPzoEvPlEJSlWARX" +
                                                    "wdPpV4X4xd0VDo4rZT5hlFNeCHze8/alZy7+dvtOJQ7l3bHLcZRxCQzLoyQ5" +
                                                    "5DIG65+fLTzx5OVTd4sMAYprGikYwHEIYAJCBsf62IfH//zlF+f+oERZxeG+" +
                                                    "rBZNQ6uBjC2RFgARE4AMYz9w2KrYulEyaNFkmJz/7t684+Lfz2RlNE1YCZLh" +
                                                    "up8WEK1fdRt5+OOj/+wTYjIaXmKR5xGZPIAVkeTdrktPoB21Rz5b/8wH9HeA" +
                                                    "sYBrnjHDBFQR4RkRR6+KUG0TYy61twOHjU7dXk2srAmrbuvCRbQH7+JY8f3r" +
                                                    "gFk8+ZfvhEd15dPgCkrxj6nnn107tOsbwR/lMXJvqNUDEvQtEe8vXqr8Q+lv" +
                                                    "fU8hbWMkq/lQc4SaVcyWMWgEvAB/oHFK7CcvdXmDDYZ1ui5dQzG16QqKgBDm" +
                                                    "SI3zjlTRiBpZCU+HXzQd6aLJEDHZKVj6xbgZh2uDnG1zXGOKYscFiSP2ezlZ" +
                                                    "HcddowLtBCahLY4v6wgjBhKhbcQn8FoCNFbE+oUaDdEknTs5N68feG6HBNue" +
                                                    "5OU9DL3pK3/84ZPc2a8+anBLtHPbud5kU8yM2dSEKhMgv1/0YFGYH3/x5Tf5" +
                                                    "p9tvkiq3LZyaacYPTv5t7aFd5ft+BrRvSDmfFvni/vMf3b5F+41CmsJsqWsr" +
                                                    "k0yDyRzpcBn0wdahRKb0hZmyIoDXrJ8p2YbwGkU2KnRFnKcSxLivLsbCVQZd" +
                                                    "KyJJQLYqTjYqf3cXRoSawiJQcgSH/YClVUeHtFwcNAquUYG2bsrvO9XZni8n" +
                                                    "n/36FRnRNEKkiNnpucd/zJ2ZU2Kd/DV1zXScR3bzwspl8mB/hL8MPP/BB13A" +
                                                    "BdnN9Qz5LeXGsKd0HKyDTYuZJVTs+euF2XdemD2l+EcyDFVatG2TUaseYsXC" +
                                                    "SBhnfMg6eHr9OPf+z3HOJGt5fcNaho8U/ExjQgxbJI4GDkVOlk4wfhD4MGVx" +
                                                    "6ZafdEF0Arv8dA3S9melKr6WhDJBai9i5nEcoNiWeRrlkMKFss39Sr4Th3uk" +
                                                    "dUc5aZ6yDb3BFcdJV7IbxTt2Td23r/xe016d716yev7wn0R/FX5TtcOHTalq" +
                                                    "mnHMj81bHZeVDGFvu7wBJAafaIS4skOGApITYXBN0j/ASTZND47hT5zsIYha" +
                                                    "jAyyz5/FiU5y0gREOH3UCXImK1oLvPty8u6rkRgkY3cVf0u0Wljb4v8KAUJW" +
                                                    "5X8WxrUL8/vuuP/KL58TcNuimXRmRnyHwme17DJDlN20oLRAVuverd93vda+" +
                                                    "OSitLhx6/NYyZduGxh3YcMXhomeaeWv1Gzc/P/+FaAH/C/v7kaHwEQAA");
}
