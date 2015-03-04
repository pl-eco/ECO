package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.LightSample;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class ViewCausticsShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { state.faceforward();
                                                   state.initCausticSamples();
                                                   Color lr = Color.black();
                                                   for (LightSample sample
                                                         :
                                                         state) lr.madd(sample.
                                                                          dot(
                                                                            state.
                                                                              getNormal()),
                                                                        sample.
                                                                          getDiffuseRadiance());
                                                   return lr.
                                                     mul(
                                                       1.0F /
                                                         (float)
                                                           Math.
                                                             PI);
    }
    public void scatterPhoton(ShadingState state, Color power) {
        
    }
    public ViewCausticsShader() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1166307204000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVYe2wURRif3vVBH+ZKgYJVWiig8roFIyZSX7UWPTjkvEKF" +
                                                   "Ipbp7tzdwt7Oujvb\nHpUoxgQQ44OoiSaKxJDgCzFRgyY+IOCTf8RETUx8hU" +
                                                   "RNfCTGRDH6h9/M7N7t7V2r0SY7uzfzPeZ7\n/eabvvATqnNsdKHqxNkOizjx" +
                                                   "voEUth2i9RnYcdbD1LD6Tl1j6vAak0ZQTRJFdI2hWFJ1FA0zrOia\nkri+p2" +
                                                   "CjxRY1dmQNyuKkwOLbjBWevNXJFRUCbzlwrO3uQ7VdEVSXRDFsmpRhplOz3y" +
                                                   "B5h6HW5DY8\nihWX6YaS1B3Wk0TnEdPN91HTYdhkzu3oThRNonpL5TIZmpv0" +
                                                   "lSugXLGwjfOKUK+khFqQMM0mDOsm\n0XqL6oBzSTknbNvjS1dSg5ApfHEQzB" +
                                                   "E7AKvnFK2W1laYakWfGbx858Fnoyg2hGK6OcCFqWAJA31D\nqCVP8iPEdno1" +
                                                   "jWhDaKpJiDZAbB0b+rjQOoTaHD1rYubaxEkThxqjnLDNcS1iC53+ZBK1qNwm" +
                                                   "21UZ\ntYs+yujE0PxfdRkDZ8Hs9pLZ0txVfB4MbNJhY3YGq8Rnqd2umxDxrj" +
                                                   "BH0cb5a4AAWBvyhOVoUVWt\niWECtclYGtjMKgPM1s0skNZRF7Qw1DGhUO5r" +
                                                   "C6vbcZYMMzQrTJeSS0DVKBzBWRiaESYTkiBKHaEo\nBeKzuP23vc888da1Ir" +
                                                   "drNaIafP9NwNQZYkqTDLGJqRLJeM6NP5LY5F4YQQiIZ4SIJU3vgmMbkt+/\n" +
                                                   "3SVpLqhCs25kG1HZsHrT/q70HTdQFOXbmGJRR+fBL7NclEPKW+kpWFC17UWJ" +
                                                   "fDHuLx5Pv7tp13Pk\nhwhqSqB6lRpuHvJoqkrzlm4Q+wZiEhszoiVQIzG1Pr" +
                                                   "GeQA3wnYSUl7PrMhmHsASqNcRUPRW/wUUZ\nEMFd1Ajfupmh/reFWU58FyyE" +
                                                   "UAM8aDk8TUj+iTdDV8UVxzUzBh1THFtVqJ0t/lapTRQnhzViK4M6\nGevDrg" +
                                                   "Ol4gyIqThPI4uhTUqO5omCVWzqJlWyOhSuSpdqZJS//4/wAt9921hNDYfDcF" +
                                                   "kbUBE3UgNo\nh9XDZz/c2b/m3r0yZXiae3YztAh0xj2dca4zLnXGK3Wimhqh" +
                                                   "ajrXLeMH3t8OdQyI17JwYMvqrXu7\no5A41lgtuI6TdoOF3ob6VdpXKvaEwE" +
                                                   "UVMm7W05v3xM8dvkZmnDIxJlflbv7oyOmDv7YsiqBIdcDk\nhgJkN3ExKY6y" +
                                                   "RSCcHy6xavJ/3rf25U9Pf3FJqdgYml+BAZWcvIa7wyGxqUo0QMWS+EPnx6K3" +
                                                   "oMH9\nEVQLwABgKPYPONMZ1lFWyz0+LnJbGpKoOUPtPDb4kg9mTSxn07HSjM" +
                                                   "iVVj5Ml2nDAxnaoIDUc/fU\nL/vsjeZ3hMU++sYC59sAYbKWp5byYL1NCMx/" +
                                                   "8Vjq4Ud/2rNZJIGXBQwOPXfE0NUCsFxUYoEiNgBI\neIzmbzDzVNMzOh4xCE" +
                                                   "+mv2ILlr/64wOt0usGzPhBW/LPAkrz51+Hdp2+7fdOIaZG5YdIyYwSmbRm\n" +
                                                   "Wklyr23jHXwfhbs/nv34e/hJwDjAFUcfJwIqkLAMCT/GhXsXinFpaG0ZH7pB" +
                                                   "9pIJsrrKkT2s7nwu\n2+3e/sHrYtfNOHj2B8OwFls9MqhC9zRQeinyhjII46" +
                                                   "szLD628xDMDFfvjdjJgbDLjt90a6tx/E9Q\nOwRqVThPnXU2lH2hLNIedV3D" +
                                                   "5ydOtm89E0WRVajJoFhbhUX+o0ZIPOLkAHkK1jXXim20jk3ho/AL\ngi10VQ" +
                                                   "9if95iwu3jr8185crDB77kkGAVhHkdnlvFj3livMhLMP59SZCqRnzPZGhmBb" +
                                                   "ZJIONumD3R\nASuagz0bf2nZjU9tkaDUVn5o9UNj992Ok+TiK+//pgqiNjJq" +
                                                   "LTXIKDECe4pylWVguFb0HiUo2Pfs\n88fYmcUrpcpFE+NgmHHRyoPjXSuP3v" +
                                                   "cfILAr5ISw6KmjF9wczenvR0R7JNGvoq0qZ+oJugOUwn5c\n2+SO5TMtImPn" +
                                                   "FDO2mQe1x//w38GMFVjFh55QoUWEXyN+rDsrYi1MJdC18ZTyydqDZAPy3ZtK" +
                                                   "CDXJ\nSUo5zYcEYJlrwSWCQDRnBe8ftp6HPmZUgPPZ3d1vvr/hqT0ykAsnuW" +
                                                   "QEuYbVu776env0wRMjki/c\ny4WI93ce+vbls+npMv9kwzuvoucM8simVxgT" +
                                                   "s3gFzJ1Mg6A+tXjuC3emvxQ74ny9DDWMUGoQLJNq\nOR9Wy9JbUbXWK0q3vz" +
                                                   "z0y+CJeaGP/evQ15SX+eyqZQ59O7+5ECFmZJLQZvgALXtzlrA08PEsroog\n" +
                                                   "eh4aeI5Y3l1FmL/1/5h/NTwdnvkd/zXzJzf/nywRaqxJ3DPKhzxD5zkqZlBN" +
                                                   "qRxlHqis98B5E0O1\no1TXSl4x/61XCnDfquw0+Xk8q+KeKu9WavLzO279Lf" +
                                                   "nJH6JnKt5/muESknENI4A9QRyqt2yS0YVB\nzfLUtMRrV7VTQnbAUOzyQ2z4" +
                                                   "Lkl/D9z1w/RgPX8FyXZDOgXIoG68ryDRvQxFgYh/7rP8OLWKs5Ff\nPuPypl" +
                                                   "V+/nHPzCuDFPGvAx+/XfnPg2F145HNcwr3rX9IHAp1qoHHx8UtES69slcsng" +
                                                   "FzJ5Tmy/oI\nvXR08I0Xr/BhoKyLrEjx5XJ14vjzhSusvwGBjou0xhEAAA==");
}
