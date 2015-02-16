package org.sunflow.core.bucket;
import org.sunflow.core.BucketOrder;
public class DiagonalBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) { int[] coords = new int[2 *
                                                                                nbw *
                                                                                nbh];
                                                       int x =
                                                         0;
                                                       int y =
                                                         0;
                                                       int nx =
                                                         1;
                                                       int ny =
                                                         0;
                                                       for (int i =
                                                              0;
                                                            i <
                                                              nbw *
                                                              nbh;
                                                            i++) {
                                                           coords[2 *
                                                                    i +
                                                                    0] =
                                                             x;
                                                           coords[2 *
                                                                    i +
                                                                    1] =
                                                             y;
                                                           do  {
                                                               if (y ==
                                                                     ny) {
                                                                   y =
                                                                     0;
                                                                   x =
                                                                     nx;
                                                                   ny++;
                                                                   nx++;
                                                               }
                                                               else {
                                                                   x--;
                                                                   y++;
                                                               }
                                                           }while((y >=
                                                                     nbh ||
                                                                     x >=
                                                                     nbw) &&
                                                                    i !=
                                                                    nbw *
                                                                    nbh -
                                                                    1); 
                                                       }
                                                       return coords;
    }
    public DiagonalBucketOrder() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1159026716000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVXe2wcxRkf352fZ+pX4oQQ4sRJyMPJbkxJWmwEMbZDLrkk" +
                                                   "VzsJYJOa8ezceeO9\nnc3urH0xEQUhJSlRgQgqtVIJoYoUkkJBolVaidJEQE" +
                                                   "ubfwCpRUKCtooElVoqEBJNVf7gm5l77O2d\ng9QKS56bnfke871+883zH6Na" +
                                                   "z0VLiafxww71tMHRFHY9agxa2PP2wtIEeaO2MXV2p80iqCaJIqbB\nUUuSeL" +
                                                   "qBOdZNQ08M9edc1OMw63DGYlyjOa4dtDbn5e1Ibq4QePepC+0Pn4l1RVBtEr" +
                                                   "Vg22Ycc5PZ\nwxbNehy1Jg/iGaz73LT0pOnx/iS6jtp+dpDZHsc29w6hB1E0" +
                                                   "ieocImRytCJZUK6Dct3BLs7qUr2e\nkmpBQodLOTZtagwU1QHnhnJOOHaeb6" +
                                                   "SSGoQ0iM39YI48AVi9vGi1srbCVCf63P4tR06fi6KWMdRi\n2qNCGAFLOOgb" +
                                                   "Q81Zmp2krjdgGNQYQ202pcYodU1smXNS6xhq98yMjbnvUm+EesyaEYTtnu9Q" +
                                                   "V+os\nLCZRMxE2uT7hzC36KG1Syyh81aYtnAGzO0tmK3O3iXUwsMmEg7lpTG" +
                                                   "iBJTZt2hDxrjBH0cZVO4EA\nWOuzlE+xoqqYjWEBtatYWtjO6KPcNe0MkNYy" +
                                                   "H7RwtGReocLXDibTOEMnOFocpkupLaBqlI4QLBwt\nDJNJSRClJaEoBeLT0/" +
                                                   "n58ed+8upWmdsxgxJLnL8JmJaFmEZomrrUJlQxXvW1pxL3+ksjCAHxwhCx\n" +
                                                   "ohlYfWFf8u+/7VI0N1Sh2TN5kBI+QXaf7Bp54C6GouIYDQ7zTBH8MstlOaTy" +
                                                   "O/05B6q2syhRbGqF\nzYsjv7v3ofP0HxHUlEB1hFl+FvKojbCsY1rUvYva1M" +
                                                   "WcGgnUSG1jUO4nUD3Mk5DyanVPOu1RnkAx\nSy7VMfkNLkqDCOGiRpibdpoV" +
                                                   "5g7mU3KecxBC9fCPvgX/tUj9yV+Obtd0z7fTFpvVPZfozM0Uvwlz\nqT7pk2" +
                                                   "nK9SETZ5iNrTvl5x7XoK4m8sjhaEyfYlmqY4Jt02Z6xoTKJWyjQWfE7/8lPS" +
                                                   "fO3z5bUyMA\nMVzYFtTEdmYB7QQ5e+WPR4Z3fv+4ShqR6HnLOeoBpVpeqSaU" +
                                                   "akqpVkUpqqmRuhYI5SqEEIBpKGUA\nveZ1owd23H+8Owq548zGwHuCtBtszJ" +
                                                   "9omLDBUr0nJDQSSLrFPx0/pl09e4dKOn1+WK7KHX/rhcun\nP2teH0GR6pgp" +
                                                   "LAXUbhJiUgJoi1i4Klxl1eT/69FdL//58vtrS/XG0aoKGKjkFGXcHY6Jywg1" +
                                                   "ABhL\n4s9c3xK9G+0/GUExwAbAQ3l+gJplYR1l5dxfgEZhS30SxdPMzWJLbB" +
                                                   "XwrIlPuWy2tCKTpVUMC1Te\niECGDihR9eojdZvefSX+hrS4AMAtgStulHJV" +
                                                   "zm2lPNjrUgrr7/8o9eQPPz42LpMgnwUc7j1/0jJJ\nDlhuKrFAHVuAJSJGq/" +
                                                   "bZWWaYaRNPWlQk0xctq3t/+c/HWpXXLVgpBG3DVwsorV9/J3ro8nf/vUyK\n" +
                                                   "qSHiHimZUSJT1nSUJA+4Lj4szpF7+J0bf/x7/DTAHECLZ85RiRZIWoakHzXp" +
                                                   "3nVy3Bja2ySGbpC9\nYZ6srnJrT5Aj5zPd/qE//FqeOo6D138wDLuw06+CKn" +
                                                   "V3gNJvo/xQhmJid6Ejxk4RgkXh6t2OvSkQ\ndsvF3fe1Whf/C2rHQC2BK9WT" +
                                                   "ZZ8ri3Seurb+vUuvdd7/dhRFtqEmi2FjG5b5jxoh8ag3BdCTc+7Y\nKo/ROt" +
                                                   "sgRukXJE+7JO+lXNmX/Fgpx5vy2SPma4NUNXK+iKOlFcgVgCph6I3z3aKyAz" +
                                                   "h2z6fNR/Hr\nBxTstJffTMPQvX10+DW65rYf/K0KaDZy5my06Ay1yg4GKsvg" +
                                                   "bpdsMErF/ui5n13gb/f0KZXr50e6\nMOP6vtNzXX0vnvgfQK4r5ISw6LaZG7" +
                                                   "4TnTLfjMgeSOFbRe9UztQfdAcohfP4ri0cK1aaZU4uL+Zk\nXET2Fvivy+dk" +
                                                   "XTgnJRqJoS9UShHp1wj4dXGw3XfNLLQNMxIIrxzt/s2b+545ply67ho9fZBr" +
                                                   "gnzv\nL3+djj5+aVLxhVunEPHJZWc+fPnKyAKVCaq/XFnR4gV5VI8pLWtxRC" +
                                                   "6uuJYGSf16z4rnHxz5QJ5I\n8G3lKArNrZim5MLOawDNPjFs56gtQ7mqglF6" +
                                                   "yBe3hUD5gPMksgmd554Y6hi5dfwRmVKN0L5jb3cp\npvBoErMa8M3q+X1aFD" +
                                                   "ZB1hy48MmlV+kaCR4NpgdvjgE3U6WVDfB8is/TXe+mT8m7LzaJPZU/4TdA\n" +
                                                   "ZYtf1rlL+7/hyJ+U4zjQtMWlOb2bbt7yzU1gfzvYL95+mmloSUawlRh69lL8" +
                                                   "nZP+lh0q/NcFCBJD\nR17a0dzw7ImjsiLyjmgMNLn57/oZ7O4ugYL4Gedo8u" +
                                                   "vr9vq2bOjt3bj5Zsi9iiiv1TRt3RpveakU\npXN6xZBQ6Ln5q7GYo44qysXd" +
                                                   "uLji2aieOiT53gP3fZ78039UDAvPkTi4K+1bVgAlgohR57g0bcqI\nxdUNps" +
                                                   "KX5mjRPO0oNBFqIk9MFb0JT+8wPUcx8RMkA0SLB8g4qs/PgkQMyg2IxNRxCl" +
                                                   "dMq7z2xFtQ\nUw+f8utKeGZlWXnIl3wBaX31lp8g97wwvjx3Yu8TstZqiYXn" +
                                                   "5uSjDZJI9W1FtF4xr7SCrLfQSy/u\nf+XntxZgoqyjq7hLe9XuNRIAbojqHd" +
                                                   "Vw1uGyB5r71aJf3Hb21AcR2dN9CaSTqS+AEQAA");
}
