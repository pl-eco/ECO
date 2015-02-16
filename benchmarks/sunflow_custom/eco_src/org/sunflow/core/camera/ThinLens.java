package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class ThinLens implements CameraLens {
    private float au;
    private float av;
    private float aspect;
    private float fov;
    private float focusDistance;
    private float lensRadius;
    private int lensSides;
    private float lensRotation;
    private float lensRotationRadians;
    public ThinLens() { super();
                        focusDistance = 1;
                        lensRadius = 0;
                        fov = 90;
                        aspect = 1;
                        lensSides = 0;
                        lensRotation = (lensRotationRadians = 0); }
    public boolean update(ParameterList pl, SunflowAPI api) { fov = pl.getFloat(
                                                                         "fov",
                                                                         fov);
                                                              aspect = pl.
                                                                         getFloat(
                                                                           "aspect",
                                                                           aspect);
                                                              focusDistance =
                                                                pl.
                                                                  getFloat(
                                                                    "focus.distance",
                                                                    focusDistance);
                                                              lensRadius =
                                                                pl.
                                                                  getFloat(
                                                                    "lens.radius",
                                                                    lensRadius);
                                                              lensSides =
                                                                pl.
                                                                  getInt(
                                                                    "lens.sides",
                                                                    lensSides);
                                                              lensRotation =
                                                                pl.
                                                                  getFloat(
                                                                    "lens.rotation",
                                                                    lensRotation);
                                                              this.
                                                                update();
                                                              return true;
    }
    private void update() { au = (float) Math.
                                   tan(
                                     Math.
                                       toRadians(
                                         fov *
                                           0.5F)) *
                                   focusDistance;
                            av = au / aspect;
                            lensRotationRadians =
                              (float)
                                Math.
                                toRadians(
                                  lensRotation);
    }
    public Ray getRay(float x, float y, int imageWidth,
                      int imageHeight,
                      double lensX,
                      double lensY,
                      double time) { float du =
                                       -au +
                                       2.0F *
                                       au *
                                       x /
                                       (imageWidth -
                                          1.0F);
                                     float dv =
                                       -av +
                                       2.0F *
                                       av *
                                       y /
                                       (imageHeight -
                                          1.0F);
                                     float eyeX;
                                     float eyeY;
                                     if (lensSides <
                                           3) {
                                         double angle;
                                         double r;
                                         double r1 =
                                           2 *
                                           lensX -
                                           1;
                                         double r2 =
                                           2 *
                                           lensY -
                                           1;
                                         if (r1 >
                                               -r2) {
                                             if (r1 >
                                                   r2) {
                                                 r =
                                                   r1;
                                                 angle =
                                                   0.25 *
                                                     Math.
                                                       PI *
                                                     r2 /
                                                     r1;
                                             }
                                             else {
                                                 r =
                                                   r2;
                                                 angle =
                                                   0.25 *
                                                     Math.
                                                       PI *
                                                     (2 -
                                                        r1 /
                                                        r2);
                                             }
                                         }
                                         else {
                                             if (r1 <
                                                   r2) {
                                                 r =
                                                   -r1;
                                                 angle =
                                                   0.25 *
                                                     Math.
                                                       PI *
                                                     (4 +
                                                        r2 /
                                                        r1);
                                             }
                                             else {
                                                 r =
                                                   -r2;
                                                 if (r2 !=
                                                       0)
                                                     angle =
                                                       0.25 *
                                                         Math.
                                                           PI *
                                                         (6 -
                                                            r1 /
                                                            r2);
                                                 else
                                                     angle =
                                                       0;
                                             }
                                         }
                                         r *=
                                           lensRadius;
                                         eyeX =
                                           (float)
                                             (Math.
                                                cos(
                                                  angle) *
                                                r);
                                         eyeY =
                                           (float)
                                             (Math.
                                                sin(
                                                  angle) *
                                                r);
                                     }
                                     else {
                                         lensY *=
                                           lensSides;
                                         float side =
                                           (int)
                                             lensY;
                                         float offs =
                                           (float)
                                             lensY -
                                           side;
                                         float dist =
                                           (float)
                                             Math.
                                             sqrt(
                                               lensX);
                                         float a0 =
                                           (float)
                                             (side *
                                                Math.
                                                  PI *
                                                2.0F /
                                                lensSides +
                                                lensRotationRadians);
                                         float a1 =
                                           (float)
                                             ((side +
                                                 1.0F) *
                                                Math.
                                                  PI *
                                                2.0F /
                                                lensSides +
                                                lensRotationRadians);
                                         eyeX =
                                           (float)
                                             ((Math.
                                                 cos(
                                                   a0) *
                                                 (1.0F -
                                                    offs) +
                                                 Math.
                                                 cos(
                                                   a1) *
                                                 offs) *
                                                dist);
                                         eyeY =
                                           (float)
                                             ((Math.
                                                 sin(
                                                   a0) *
                                                 (1.0F -
                                                    offs) +
                                                 Math.
                                                 sin(
                                                   a1) *
                                                 offs) *
                                                dist);
                                         eyeX *=
                                           lensRadius;
                                         eyeY *=
                                           lensRadius;
                                     }
                                     float eyeZ =
                                       0;
                                     float dirX =
                                       du;
                                     float dirY =
                                       dv;
                                     float dirZ =
                                       -focusDistance;
                                     return new Ray(
                                       eyeX,
                                       eyeY,
                                       eyeZ,
                                       dirX -
                                         eyeX,
                                       dirY -
                                         eyeY,
                                       dirZ -
                                         eyeZ);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169271630000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVYfWwcxRUf39k+++xwtpM43/6KMSU2t4U2VRsjJa5xwMkG" +
       "G38BDtQZ786dN9nb\n3ezOns8motBKcQC1NCqotIIQVakcaChIgEJVGoKAlh" +
       "JVgkqlEhJpq0htpZZKVSWaqv2jb2Z2b+/2\n7gxG6kk7Ozfz5v3mfc6bPfcR" +
       "qnFstFVxknTBIk5ycHwU2w5RB3XsOBMwNKO8VVM/urzfMCOoSkYR\nTaUoIS" +
       "uOpGKKJU2Vhm/pz9mo1zL1hbRu0iTJ0eRhfafHb5+8s4ThnafOtzx4pro9gm" +
       "pklMCGYVJM\nNdMY0knGoahJPoyzWHKppkuy5tB+Ga0hhpsZNA2HYoM6R9H9" +
       "KCqjWkthPCnqlH1wCcAlC9s4I3F4\naZTDAoe1NqFYM4g6kIeDlX3FK2Hb3r" +
       "qxUmpgUscmp0AcvgOQuiMvtZC2RFQrenbqS8dOPxNFiWmU\n0IxxxkwBSSjg" +
       "TaPGDMnMEtsZUFWiTqNmgxB1nNga1rVFjjqNWhwtbWDq2sQZI46pZxlhi+Na" +
       "xOaY\n/qCMGhUmk+0q1LTzOkppRFf9fzUpHadB7NZAbCHuXjYOAsY12Jidwg" +
       "rxl1Qf0QyweHt4RV7G7v1A\nAEtjGULnzDxUtYFhALUIW+rYSEvj1NaMNJDW" +
       "mC6gULS5IlOmawsrR3CazFC0MUw3KqaAqp4rgi2h\naH2YjHMCK20OWanAPr" +
       "2tH584++SFPdy3q1Wi6Gz/cVjUFlo0RlLEJoZCxMKrbvKx4bvdrRGEgHh9\n" +
       "iFjQDFx7flL+y2vtgmZLGZqR2cNEoTPK7Sfbx+671URRto06y3Q0ZvwiyXk4" +
       "jHoz/TkLorY1z5FN\nJv3Ji2O/uPuBZ8lfIyg+jGoVU3cz4EfNipmxNJ3Ytx" +
       "KD2JgSdRjVE0Md5PPDKAZ9GVxejI6kUg6h\nw6ha50O1Jv8PKkoBC6aieuhr" +
       "Rsr0+xamc7yfsxBCMXhQLzx1SPz4m6IvJCXHNVK6OS85tiKZdjr/\nXzFtIi" +
       "ngNDaWJuY0QyaGk2TOY1E0Is2ZGSJhBRuaYUppDcJVMW9QSZa9V88yx3baMl" +
       "9VxVJfOIR1\n8P7bTF0l9oyyfOWdY0P7Hzoh3IO5tCcjRR2AlPSQkgwpKZCS" +
       "PhKqquIA6xiisBDo9whEKuS0xuvH\n79136ERXFFzDmq8G5TDSLpDG28aQYg" +
       "4G4TzMM58CPrXxhweXkleXdwufkipn3bKrG9597tLpfzbu\niKBI+ZTIxIOk" +
       "HGdsRlkezae67nAQleP/94cPvPj+pQ8/F4QTRd0lUV66kkVpV9gQtqkQFfJe" +
       "wP7M\npkT0TjR1MoKqIfQh3fH9QyZpC2MURWu/n/mYLDEZNaRMO4N1NuWnqz" +
       "ids835YIR7SBPvrwXjNDD3\nZZ1mz5/5m82ut1jbKjyKWTskBc+sV79Z+/nf" +
       "vdrwFleLn4QTBcfcOKEipJsDZ5mwCYHxD58Y/e7j\nHy0d5J7iuQqFs8+d1T" +
       "UlB0t6giUQyzrkE2bI7kkjY6paSsOzOmEe99/EtTe+/LdvNwnT6DDiW7bv\n" +
       "kxkE45u+ih649LV/tXE2VQo7SwIxAjIhzdqA84Bt4wW2j9yDv9n2/V/ipyDV" +
       "QXpxtEXCMwbikiGu\nR4nrfQdvk6G5G1nTBbz7Krh+mZN7Rjn2bLrLPfqrn/" +
       "JdN+DCEqDQDAew1S8sz5rtTLsbwtF7G3bm\ngO6LF2+/p0m/+B/gOA0cFTgx" +
       "nREbkkauyIgedU3sg9ffaD30XhRF9qK4bmJ1L+b+j+rB8YgzB/km\nZ+3ew3" +
       "2raZ5lyyYuMuJK2OwpIFfwrx42d33l8N/Lzv0gcmZm+87K74w8xRVQMfDLHH" +
       "shPosXJk9d\n/TW9zPkEEchWd+ZKkynUSsHaL7+fba594elMBMWmUZPiVXNT" +
       "WHeZn09D8eH4JR5UfEXzxYWEODX7\n8xlmazj6C2DDsR8kcegzatZvDIV7I9" +
       "P2JqZkL9zrw+FehXhnN1/Szdvr8sEZs2wti1mFhyLYBTtt\nLCyvbS0Dx3SW" +
       "Z6Yrx7t+/vbk00sim69gzqJVM8rXf/+HI9FHX58V68I2CxGfbDvzpxevjK0T" +
       "kS/q\nue0lJVXhGlHTcaUkLBYFnSshcOo3ezvP3T922dtRS3FlMgTV+58X3i" +
       "DX3fytP5Y5SsFAJqYig7L2\nJtbsEc6+s2JQ7Fq9uUZZM8CskmW94RDkHauE" +
       "bIMn7kHGK0BOepC12LHAacvBTv0fYKc92GjKLCvq\nwVVidviHoP8ugznjYa" +
       "5JmYrr3KKJACyHfmiV6Nv8jv8ug6566HEdaq8xrGquUw6arBJ6MzxrPOg1\n" +
       "FaA1D7qeQY9rKhwFbGTIElj7wAxwrwpt5PAqN9IOzzXeRq6psJGj3kYauQ68" +
       "1F5OC/YqwXvgSXjg\niQrgWQ98bSE4MwQ2ylpifoU98NTTU3DaVfH+Boq2lJ" +
       "Tdg7zsZhU3y1TbKt31eJZauusfjcfxm/dG\nvGpiPxiNmtYNOskSvQAuyjgV" +
       "FeMH+O02OMwefubH5+l7vbtEvttROXOHF+7YdXqxfdfzj3yGErw9\nJFuYdX" +
       "N2yx3ROe3tCL+Ai7Ox5OJevKi/+ESMw35c25goOhc7isvgfi8q/OgoLYMD4w" +
       "U1XITrNeKb\nsa3EjFxUQonNikSfrLWQbFy8B0aHOcxDK1SJj7LmOGRd11Lh" +
       "IA6HY2zWNHXi+SV3xqVPCgi/BON/\nvlF69PR4GumpqJGBFfb7gxXmnmTN9y" +
       "rKUp01NTUQ5InPKIiX1BHq8wTp+9SmjXGOsXyM84Z/R9HD\nm61VTbi3cN86" +
       "yxvOdXkF6c+x5gysTBM6hhd8x1hX4j/+JNfCjz6tFqBEq/Mv7OzGsrHkg574" +
       "CKXI\nH9x3z8fyb//Nr575D0UNMqpLubpeWFQW9Gstm6Q0LkeDKDEt/nqZog" +
       "0VPh+ApKLDt/mSoH+FoqYw\nPVievQrJfkZRQwEZ+LnXKyS6AKcRELHua5av" +
       "ziZ+Y2HFdVIU17kiLTHNbC9Kcfwbq5+GXPGVdUa5\n67mDHblHJr7Dc1uNou" +
       "PFRcYmLqOYuHLnU1lnRW4+r3fRC89PvfqTr/ipml/J1uWCQ6TIkW8SsytY\n" +
       "HdJn+XvuUMai/Ga6+MqGl25ePnU5wm/a/wPj0DLZGhcAAA==");
}
