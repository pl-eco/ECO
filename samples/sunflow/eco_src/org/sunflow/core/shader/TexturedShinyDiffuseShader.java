package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.core.Texture;
import org.sunflow.core.TextureCache;
import org.sunflow.image.Color;
public class TexturedShinyDiffuseShader extends ShinyDiffuseShader {
    private Texture tex;
    public TexturedShinyDiffuseShader() { super();
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
      1169407182000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAK1Ye2wcxRkf3/kRn438SOKYkNix40Bxwm2JSFVipMQ1Dphc" +
       "sPE5BpyAGe/O3W0y\nt7vsztpnE/EoEklBfURtpVaCEKFICa+CRKu0Eo8gni" +
       "WqBEiAhESgitRWKiAhJJqK/tFvZnZvH3dn\nEMLSzc7ufI/55vu+3/eNn/oM" +
       "NTg2Wqc6abZoESc9kp3AtkO0EYodZwo+zaqvNTRPnNxtmAlUl0EJ\nXWOoLa" +
       "M6ioYZVnRNGbt2qGSjzZZJF/PUZGlSYukDdJsn74bMtgqBNx873XnfifreBG" +
       "rIoDZsGCbD\nTDeNUUqKDkPtmQN4Hisu06mS0R02lEEXEcMtjpiGw7DBnDvR" +
       "3SiZQY2WymUy1JfxlSugXLGwjYuK\nUK9MCLUgYaVNGNYNog2X1QHnlignbN" +
       "vjm6ykBiEr+OI0mCN2AFZvKFstra0w1Uqemv7RoeOPJ1Hb\nDGrTjSwXpoIl" +
       "DPTNoNYiKc4R2xnWNKLNoA6DEC1LbB1TfUlonUGdjp43MHNt4kwSx6TznLDT" +
       "cS1i\nC53+xwxqVblNtqsy0y6fUU4nVPPfGnIU58HsrsBsae4u/h0MTOmwMT" +
       "uHVeKz1B/UDfB4b5yjbOPA\nbiAA1qYiYQWzrKrewPABdUpfUmzklSyzdSMP" +
       "pA2mC1oYWltTKD9rC6sHcZ7MMtQdp5uQS0DVLA6C\nszC0Ok4mJIGX1sa8FP" +
       "LP5q6vjpx6+MWdIrbrNaJSvv8UMPXEmCZJjtjEUIlkvOCmfzN2q7sugRAQ\n" +
       "r44RS5rhTaf3Zv71Uq+kuaQKzfjcAaKyWfXGo72Td11noiTfxgrLdHTu/Ijl" +
       "Ih0mvJWhkgVZ21WW\nyBfT/uKZyddvvfcJ8u8ESo2hRtWkbhHiqEM1i5ZOiX" +
       "0dMYiNGdHGUDMxtBGxPoaaYJ6BkJdfx3M5\nh7AxVE/Fp0ZTvMMR5UAEP6Jm" +
       "mOtGzvTnFmYFMS9ZCKEm+KFW+KWQ/BNPhkbTiuMaOWouKI6tKqad\nL7+rpk" +
       "0Up4A1YitTkBwQ8Fq2oBuL1+q5nOuQrFhK83CyGMJKwSwSBavY0A1TyeuQwK" +
       "p5hUbm+fP7\nUFLi1nQu1NVxeIynOYUMud6kQDurnjz/1qHR3T87IkOIh713" +
       "DgxtBd1pT3ea605L3enaulFdnVC5\niu9B+hW8chDyG5Cw9fLsbTfccaQ/CQ" +
       "FlLdTDkXLSfrDY29ioao4EIDAm8FKFSOx+bN/h9IWTO2Qk\nKrWxuip3y9tP" +
       "nz3+ZetgAiWqAyk3GKA8xcVMcPQtA+RAPPWqyf/8wT3PvX/2ox8EScjQQAU2" +
       "VHLy\n3O6Pu8Y2VaLB2QbiT1zclrwZTR9NoHoADABJsX/An564jkiOD/l4yW" +
       "1pyqCWnGkXMeVLPsilWME2\nF4IvImbaxXwlOKeFB/0m+F3kZYF48tXVFh+7" +
       "ZIxxb8esEHh84f7GH37wfMtr4lh86G4LFccsYRII\nOoJgmbIJge8f/W7i17" +
       "/97PA+ESleqDComO4c1dUSsFwasAACUEAh7siBvUbR1PScjuco4RH3v7ZN\n" +
       "V/7p01+0S9dQ+OJ7dss3Cwi+X/wTdO/Z2//TI8TUqbwCBWYEZNKalYHkYdvG" +
       "i3wfpfveXf/7N/Aj\nAJAASo6+RATOIGEZEueoiHMfFGM6tnYlH/pB9pYaoV" +
       "+l3s+qh57I97t3/vUvYtctONw4hN2wB1tD\n0vN82MhPd008e6/HTgHorjpz" +
       "4/52euZrkDgDElWos864DWlfijjRo25o+vDlV7rueCeJErtQippY\n24VF/K" +
       "NmCDziFACBStaOnSK22hdW8FGYjMQhrPUOoBR643hxee3038W7hSBzZue2nM" +
       "q8Nf6IOICa\niV+lWMbkLL2499iFv7FzQk6QgZy7r1QJr9BhBbw/fn++o/HZ" +
       "R4sJ1DSD2lWvB5zG1OVxPgMti+M3\nhtAnRtaj7YestUNlhFkXz/6Q2njuB7" +
       "AOc07N562xdOf1DnX7ee8/w+leh8Rkh2AZEONl5eRssmx9\nHvO+ECVhFKtr" +
       "oAWoqCFe8ZDYwcetfNgpObZVCwex0UsjQQFxsL5WWyRausO3fNH6AH71Nlky" +
       "OqOt\nxii04/9cfIVcds3P/16l7jUz07qCknlCQzoTXGWkVO0RHWPg6gcff/" +
       "I0e2fzdqlysHaYxhkHtx9f\n6t3+zEPfoUD1xg4hLrpj/pKbkgX9zYRoamXk" +
       "VDTDUaahaLykYD+ubUxFomZDtEgMwa/Di5qOqkUi\n8GCAcAnvXL1I6amIFG" +
       "EqgV6bQ6hP1hUmy8rn8MSYUDO9DIbu50MWiohrwdWPgDe7w7dGWy9C9zkv\n" +
       "Suf5B/pfeHPvo4elI5fBmwjXrHrPx58cTP7y5TnJFweVGPHRnhP/eO785CoZ" +
       "f/KasrHiphDmkVcV\nYUybxTOgbzkNgvrVzX1P3T15TuyI8+2GPJ0zTUqwEa" +
       "Te1DKpF006/jIedb3igYYPHt/O9XUemns+\nXV/het5Nwm2L3zeJEEOXca3Q" +
       "pjOUyhPmdaO+5DVhyXoRbl283HsXTGH9gW9rPQDb2tqNL6/83RXX\naXkFVD" +
       "Mf3rX/q8x7/xUtXPma1gJ3pZxLaRicQ/NGyyY5XRjYIqHaEo/FmFWhxhyiW0" +
       "7ExkuS/hBD\n7XF6hur5I0x2D0MtITIIFG8WJvopgDsQ8en9ln/Gg7WuCZWn" +
       "VIqcKD+zjZHsEv/78KHMlf/9mFVv\neXrfhtJDU78S+NigUry0JK65cGuXTW" +
       "0ZDvtqSvNlvY2efWb6+T9c7WeEaHpWlYJqFAn2rXJ1mQgB\nCK7eSY4WLSZ6" +
       "v6U/r/njNSePnUuIXvb/aJIz37ISAAA=");
}
