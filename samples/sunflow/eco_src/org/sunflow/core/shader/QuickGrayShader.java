package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class QuickGrayShader implements Shader {
    public QuickGrayShader() { super(); }
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { if (state.getNormal() ==
                                                         null) { return state.
                                                                   getShader() !=
                                                                   this
                                                                   ? state.
                                                                   getShader().
                                                                   getRadiance(
                                                                     state)
                                                                   : Color.
                                                                       BLACK;
                                                   }
                                                   state.faceforward();
                                                   state.initLightSamples();
                                                   state.initCausticSamples();
                                                   return state.diffuse(
                                                                  Color.
                                                                    GRAY);
    }
    public void scatterPhoton(ShadingState state, Color power) { Color diffuse;
                                                                 if (Vector3.
                                                                       dot(
                                                                         state.
                                                                           getNormal(),
                                                                         state.
                                                                           getRay().
                                                                           getDirection()) >
                                                                       0.0) {
                                                                     state.
                                                                       getNormal().
                                                                       negate();
                                                                     state.
                                                                       getGeoNormal().
                                                                       negate();
                                                                 }
                                                                 diffuse =
                                                                   Color.
                                                                     GRAY;
                                                                 state.
                                                                   storePhoton(
                                                                     state.
                                                                       getRay().
                                                                       getDirection(),
                                                                     power,
                                                                     diffuse);
                                                                 float avg =
                                                                   diffuse.
                                                                   getAverage();
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
                                                                       getBasis();
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
                                                                             getPoint(),
                                                                           w),
                                                                         power);
                                                                 }
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1168808332000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVXe2wURRif3vVBH6SllIIoLZSXAt6qEROpUWstcHLA0ULV" +
       "Apbp7Nzd0r2ddXe2\nPSoqxoQixAdRE018EEOCL8QEDZqgYnzLP2qiJiSghk" +
       "RNfCTGRDH6h9/M3N5j71oMXnK7szPfY77X\nb755+RdU5TroEuJG+A6bupHu" +
       "vjh2XKp3m9h1N8LUIPmgqjZ+aI3FQqgihkKGzlFjjLiajjnWDF2L\n3tyZcd" +
       "BSm5k7kibjEZrhke3m8qy8W2LLSwTe+syx5vsOVraHUFUMNWLLYhxzg1k9Jk" +
       "27HDXFtuMR\nrHncMLWY4fLOGJpKLS/dzSyXY4u7d6J7UDiGqm0iZHI0L+Yr" +
       "10C5ZmMHpzWpXotLtSBhukM5Niyq\nd+XUAeeyYk7Ydpavt5QahEwRi/1gjt" +
       "wBWD03Z7WytsRUO/x8/zU7D7wQRo0DqNGw+oQwApZw0DeA\nGtI0PUQdt0vX" +
       "qT6AplmU6n3UMbBpjEmtA6jZNZIW5p5D3V7qMnNEEDa7nk0dqdOfjKEGImxy" +
       "PMKZ\nk/NRwqCm7n9VJUycBLNb82Yrc1eKeTCwzoCNOQlMqM9SOWxYEPH2IE" +
       "fOxgVrgABYa9KUp1hOVaWF\nYQI1q1ia2EpqfdwxrCSQVjEPtHA0e0Khwtc2" +
       "JsM4SQc5mhWki6sloKqVjhAsHM0IkklJEKXZgSgV\nxGdp6x97nn/q7Rtlbl" +
       "fqlJhi/3XA1BZg6qUJ6lCLUMV4zos8Fr3duySEEBDPCBArmq6FxzbFfnyn\n" +
       "XdFcXIZm/dB2SvggWbe/vfeuVQyFxTam2Mw1RPCLLJflEM+udGZsqNrWnESx" +
       "GPEXT/R+ePuuF+lP\nIVQXRdWEmV4a8mgaYWnbMKmzilrUwZzqUVRLLb1brk" +
       "dRDYxjkPJqdn0i4VIeRZWmnKpm8htclAAR\nwkW1MDasBPPHNuYpOc7YCKEa" +
       "+KMI/BuQ+sk3RysimutZCZONaq5DNOYkc9+EOVRzU1injrbBM8jw\nKgfv6J" +
       "PfEZFDNkf9WoqlqYYJtgyLaUkDqpawy3U6It4XLDkj9t08WlEhgDBY0CbUwm" +
       "pmAu0gOXT2\n0509ax7Yo5JFJHjWYo4Wg8JIVmFEKIwohZGAQlRRIfW0CMUq" +
       "bOD0YShfALqGy/q23rJtT0cY8sUe\nrQSPCdIOsC27mx7CuvM1HpVwSCDRZj" +
       "23eTxy7tANKtG0iaG4LHf9Z4dPHvi9YUkIhcrjpLASkLpO\niIkLcM3h34Jg" +
       "ZZWT/+vetUe/Onn60nyNcbSgpPRLOUXpdgTj4TBCdQDDvPiDFzWGb0X9+0Oo" +
       "EvAA\nMFDuH+ClLaijqIQ7fTgUttTEUH2COWlsiiUfw+p4ymGj+RmZKE1yPB" +
       "2CUy9yejb8p2aTXL7F6gxb\nPFtVYoloB6yQcHvu/uorvj5e/4F0i4/MjQVn" +
       "Xx/lqs6n5ZNlo0MpzJ9+Iv7o47+Mb5aZkk0VDgei\nN2QaJAMsi/IsUOAmgI" +
       "wI5IJNVprpRsLAQyYVGfdP48IrX//5oSYVGhNm/MguO7+A/PxFN6FdJ+/4\n" +
       "s02KqSDigMmbkSdT1kzPS+5yoDDEPjL3fTHnyY/w04B/gDmuMUYljCBpGZJ+" +
       "1KTfl8hnJLB2pXh0\ngOxlE6R+meN8kOx8Mdnh3fnJm3LX9biwLygMw1psd6" +
       "rIi8d84d2Zwepdjd0U0F19Yt2WJvPE3yBx\nACQSOEbd9Q6UfaYoiFnqqppT" +
       "777Xuu3zMAqtRHUmw/pKLPMf1ULiUTcFsJOxb7hR5lbT6BTxlCYj\n6YTZWQ" +
       "dkir7EeFHBTIUcz+RoZglCKUQS9syZ6ICUh/v4bb817Mbvb1Xo0lx86PRAY/" +
       "bDjvfo4use\n/K4MLtZyZl9u0hFqFuwpLFQWodpa2Tvka3rvCy8d458vXaFU" +
       "LpkY0IKMS1YcGGtfcWTfBWBZe8AJ\nQdHTRi7eEE4ZH4dke6NgrKQtKmbqLH" +
       "QHKIX9eI4lHCtmGmSw5hbjSacfZf9dgif5COeLIST9GvJj\n3VYSa2kqha5L" +
       "VJtP1lpI1qfeXfGoVBOdpNzWi8dKwBvPhksAhWjOKrw/OEYa+pARibJnd3e8" +
       "9fGm\nZ8dVIC+b5JJQyDVI7v3m2+Hww+8OKb5gLxYg3t928PujZ3tbVP6phn" +
       "V+Sc9YyKOaVmlMoy0qYN5k\nGiT1+0vnvXxP7xm5I8F3PUc1Q4yZFKukuko8" +
       "VqliXH7eopUfNxWH/gr4t2RD3/KfQ19RXOZzypY5\n9N3i5kGlmMFJQkvEYw" +
       "tH9UnKe4FPZHFZBDHS0ICLoyF715Dmb/0/5l8P/46s+R0XmvmTm38+S6Sa\n" +
       "9CTukZPbOZrqEsyhmuIpxrOgssFWVvVzVDnCDD3vleH/6pUMHDyBflEcmLNK" +
       "LpnqYkRip+7a8kfs\ny79k55O7vNTDDSLhmWYB8BSCULXt0IQhralXx5otX3" +
       "eXOyJUEwuVrgZytzsV/S64qAfpwXTxKiS7\nH3KpgAyKJjsqJNrNURiIxHDc" +
       "9oPUJA9McXOMqGtS8UEnPDO/CE/kvd8Hb0/d/AfJbYc3z83s2/iI\nPBGqiI" +
       "nHxuQVD26squPLHQDzJpTmy/oMvXqk//gr1/oYIDuClmybV5LfV6nVSYIPh0" +
       "75NqsnbXPZ\nGI29MfO16w49cyYkG71/AfPPEjyuEQAA");
}
