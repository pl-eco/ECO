package org.sunflow.core;
import org.sunflow.SunflowAPI;
import java.util.Map;
public final class Options extends ParameterList implements RenderObject {
    public boolean update(ParameterList pl, SunflowAPI api) { for (Map.Entry<String,Parameter> e
                                                                    :
                                                                    pl.
                                                                      list.
                                                                     entrySet(
                                                                       )) {
                                                                  list.
                                                                    put(
                                                                      e.
                                                                        getKey(
                                                                          ),
                                                                      e.
                                                                        getValue(
                                                                          ));
                                                                  e.
                                                                    getValue(
                                                                      ).
                                                                    check(
                                                                      );
                                                              }
                                                              return true;
    }
    public Options() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAK0XXWwURXju+l8K1x8oBaG0paClcAsPmECJWpqWFg/atEBi" +
                                                    "iZTp7ly7dG53mZ1rj2IVSEwJD8RoQTDaBwNBlL8YCRpD0ieB4AvGaHwQfJOo" +
                                                    "PPCCJqj4zeze7d3eFWPiJTs7O/P9/9+FB6jAZqjZMumBIWryMEnw8D66PswP" +
                                                    "WMQOb42s78HMJlobxba9A84G1IYroUeP3xouD6LCflSFDcPkmOumYfcS26Sj" +
                                                    "RIugkHfaTknM5qg8sg+PYiXOdapEdJu3RNCcNFSOGiNJERQQQQERFCmC0upB" +
                                                    "AdJcYsRjbQIDG9zej15HgQgqtFQhHkf1mUQszHDMJdMjNQAKxeJ7FyglkRMM" +
                                                    "1aV0d3TOUvhEszL17p7yT/NQqB+FdKNPiKOCEByY9KOyGIkNEma3ahrR+lGF" +
                                                    "QYjWR5iOqT4u5e5HlbY+ZGAeZyRlJHEYtwiTPD3LlalCNxZXuclS6kV1QrXk" +
                                                    "V0GU4iHQtdrT1dGwQ5yDgqU6CMaiWCVJlPwR3dA4WubHSOnY+DIAAGpRjPBh" +
                                                    "M8Uq38BwgCod31FsDCl9nOnGEIAWmHHgwtHiWYkKW1tYHcFDZICjGj9cj3MF" +
                                                    "UCXSEAKFowV+MEkJvLTY56U0/zzYvun4QaPTCEqZNaJSIX8xINX6kHpJlDBi" +
                                                    "qMRBLFsVOYmrrx8NIgTAC3zADsy11x6+tLp25qYD80wOmO7BfUTlA+qZwXl3" +
                                                    "lrQ1bcgTYhRbpq0L52doLsO/x71pSViQedUpiuIynLyc6f3qlUMfk1+DqLQL" +
                                                    "FaomjccgjipUM2bplLAtxCAMc6J1oRJiaG3yvgsVwT6iG8Q57Y5GbcK7UD6V" +
                                                    "R4Wm/AYTRYGEMFER7HUjaib3FubDcp+wEEJz4UGd8JQi5yffHEWUYTNGFKxi" +
                                                    "QzdMBWKXYKYOK0Q1FRvHLApes+NGlJpjis1UxWRDqW/VZETptmQ2h0VUWf8z" +
                                                    "vYSQv3wsEADTLvEnNoWc6DSpRtiAOhXf3P7w0sDtYCrQXc3BYcAh7HIICw5h" +
                                                    "lwMKBCTh+YKT4y+w9gjkLVS0sqa+V7fuPdqQB4FijeWDqQRoA2jhsm9XzTYv" +
                                                    "ubtkCVMhwmo+3D0Z/uPci06EKbNX4pzYaObU2OFdb6wNomBmSRXqwFGpQO8R" +
                                                    "hTBV8Br9qZSLbmjy/qPLJydML6kyarSb69mYIlcb/IZnpko0qH4e+VV1+OrA" +
                                                    "9YnGIMqHAgBFj2MIUqgntX4eGTnbkqx/QpcCUDhqshim4ipZtEr5MDPHvBMZ" +
                                                    "EfPEUukEh3CgT0BZOju+mDl99b3mDcH0KhtK61t9hDs5W+H5fwcjBM5/PNXz" +
                                                    "zokHk7ul8wFieS4GjWJtgwwGb4DF3ry5/4d7d898G/QChkMriw9SXU0AjZUe" +
                                                    "F8hvCjVGuLVxpxEzNT2q40FKRNz9GVqx7upvx8sdR1E4Sfp59b8T8M4XbUaH" +
                                                    "bu/5vVaSCaiiv3iae2COAao8yq2M4QNCjsThb5aevoE/gPIHJcfWx4msIkhq" +
                                                    "hqTpw9IjTXJd47tbK5Y6K+suIU9q3C/5US/XRrE8K8+DYvscB6fpBqbpGAG5" +
                                                    "X8DR0qyc7oUKSZhTvIW5l87WYGRzPHNkalrrPrvOSdLKzKLdDjPJxe/++jp8" +
                                                    "6qdbOepJCTetNZSMEpohGbDMKA7bZO/1UuTY+U+u8TvNGx2Wq2avC37EG0d+" +
                                                    "WbzjheG9/6EkLPMp7yd5ftuFW1tWqm8HUV6qGmSNE5lILelmAKaMwPxjCIOK" +
                                                    "k1Lp9FopQAWYo0q4dxE8c9xmI9/itsoS63w3d3P6PyD9n/BFV9CJjWQE1GZF" +
                                                    "gDQBgSlGhG8SrDodrM95t/Z0SfZbnhK/28SyGRI4bmnQmMG7TU+ZqZkegzY/" +
                                                    "6s4hykTlvZH37190PO0fWnzA5OjUsSfh41PBtMluedZwlY7jTHdSyrmOwZ/A" +
                                                    "LwDP3+IRKogDp7tXtrkjRl1qxrAskR/1TxNLsuj4+fLElx9NTAZdk2zkqGjQ" +
                                                    "NCnBRo68hku3rYqKUpM1hDuDo3ppOlS8cHrn97JRpIa7EpiwonFK0wIsPdgK" +
                                                    "LUaiuhSixKn/lnzthj8i/jDgKF+8pID9DtgejuakgYGk7i4dCHOUB0BiOyhD" +
                                                    "tDOBMmqVlVm50puCCAj55ySZbnHn78mAenl66/aDD58/K3O3AP7WjI/LYRZm" +
                                                    "c6fVpVK2flZqSVqFnU2P510pWZH0R0YT9Mm2LHevaI9ZXFb38c8Xfrbp3PRd" +
                                                    "2az+AVxCZXA1DgAA");
}
