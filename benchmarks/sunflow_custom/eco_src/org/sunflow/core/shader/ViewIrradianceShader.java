package org.sunflow.core.shader;
import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
public class ViewIrradianceShader implements Shader {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Color getRadiance(ShadingState state) { state.faceforward();
                                                   return new Color().set(
                                                                        state.
                                                                          getIrradiance(
                                                                            Color.
                                                                              WHITE)).
                                                     mul(
                                                       1.0F /
                                                         (float)
                                                           Math.
                                                             PI);
    }
    public void scatterPhoton(ShadingState state,
                              Color power) {  }
    public ViewIrradianceShader() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1166299774000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVYe2wURRif3vVBH9LSQkGUlhZQnrcQwURqhFoKnD3k6EGF" +
       "gpbp7tzdwt7Oujvb\nHpXw0EhR4oOoiSY+iCHBB6gJGjTxAVF88Y+YqImJr5" +
       "CoiY/EmCBG//Cbmb3X3rUYbXKzuzPfY77X\nb77p8V9QhWOjq1UnxHZZxAl1" +
       "xaLYdojWZWDH2QhTA+rZiurosR6TBlBZBAV0jaH6iOooGmZY0TUl\nvKojba" +
       "MFFjV2JQzKQiTNQjuMZZ68WyLLigTe9vSpxv1Hy1sDqCKC6rFpUoaZTs1ug6" +
       "QchhoiO/AQ\nVlymG0pEd1hHBF1BTDfVRU2HYZM5d6I9KBhBlZbKZTLUFsko" +
       "V0C5YmEbpxShXokKtSChySYM6ybR\nOrPqgHNhISds2+PrLaYGIRP4Yh+YI3" +
       "YAVs/MWi2tLTLVCj7Xd/3uI88HUX0/qtfNGBemgiUM9PWj\nuhRJDRLb6dQ0" +
       "ovWjSSYhWozYOjb0EaG1HzU6esLEzLWJ00scagxxwkbHtYgtdGYmI6hO5TbZ" +
       "rsqo\nnfVRXCeGlvmqiBs4AWY358yW5q7m82BgjQ4bs+NYJRmW8p26CRFv9X" +
       "NkbZzdAwTAWpUiLEmzqspN\nDBOoUcbSwGZCiTFbNxNAWkFd0MLQ9DGFcl9b" +
       "WN2JE2SAoWl+uqhcAqpq4QjOwtAUP5mQBFGa7otS\nXnwWNF88+NyTb68UuV" +
       "2uEdXg+68BphYfUy+JE5uYKpGMl9zQo+Et7tUBhIB4io9Y0nTOObUp8uM7\n" +
       "rZLmqhI06wd3EJUNqLcebu29aw1FQb6NCRZ1dB78AstFOUS9lY60BVXbnJXI" +
       "F0OZxdO972/Z9wL5\nKYBqwqhSpYabgjyapNKUpRvEXkNMYmNGtDCqJqbWJd" +
       "bDqAreI5DycnZ9PO4QFkblhpiqpOIbXBQH\nEdxF1fCum3GaebcwS4r3tIUQ" +
       "qoIfug5+1Uj+iSdDK0KK45pxgw4rjq0q1E5kv1VqE8VJYo3YSp9O\nhsO2jT" +
       "Udg8NjYjLEE8liaKuSpCmiYBWbukmVhA6lq9JFGhniz/8nPs0taBwuK+OQ6C" +
       "9tA6piLTWA\ndkA9duHj3d099x2UacNT3bMdIAW0hjytIa41JLWGSmlFZWVC" +
       "2WSuXUYRYrATqhlwr25e7PZbth9s\nD0L6WMPl4EBO2g5WelvqVmlXruTDAh" +
       "1VyLtpz24dDV06tkLmnTI2Mpfkrv3kxLkjv9fND6BAadjk\npgJw13AxUY61" +
       "WTic7S+0UvJ/vX/dyc/PfTU3V3IMzS5CgmJOXsnt/qDYVCUaYGNO/NEr64O3" +
       "ob7D\nAVQO8ACQKPYPaNPi11FQ0R0ZdOS2VEVQbZzaKWzwpQyk1bCkTYdzMy" +
       "JbGvgwWSYOD6RvgwJYL91T\nufiLN2vPCoszGFyfd8rFCJMVPSmXBxttQmD+" +
       "q8ejjzz2y+hWkQReFjA4+txBQ1fTwHJNjgVK2QA4\n4TGavclMUU2P63jQID" +
       "yZ/q6fs+S1nx9skF43YCYTtIWXF5Cbv/JmtO/cHX+0CDFlKj9KcmbkyKQ1\n" +
       "TTnJnZD4u/g+0vs/nfHEB/gpQDpAF0cfIQIwkLAMCT+GhHvniXGRb20xH9pB" +
       "9sIxsrrEwT2g7n4h\n0e7e+dEbYte1OL8DyA/DOmx1yKAK3U2gdCnyhgIg46" +
       "tTLD428xBM9VfvWuwkQdjS07duazBO/wVq\n+0GtCqeqs96Gsk8XRNqjrqj6" +
       "8sy7zdvPB1FgNaoxKNZWY5H/qBoSjzhJwJ60tWKl2EbD8AQ+Cr8g\nsdvpnp" +
       "fSBV/iY5YYr/Gyh7/PzacqE+9TGZpaBF0SpbiNM8Y6Q8X5P7r5t7oD+L3bJe" +
       "I0Fp5L3dC7\n/bDrXXLtjQ98VwIwqxm1FhlkiBh5ewpylQVIt060F7k6v//5" +
       "F0+x8wuWS5XzxwY5P+P85UdGWpe/\nfOg/4Furzwl+0ZOGrtoQTOofBkQHJK" +
       "GtqHMqZOrIdwcohf24tskdy2fqRDrOzKZjLQ9qB/xqvHSs\n8aejACI+LPdV" +
       "UUD4NZCJdUtRrIWpBBozXqYZsuZ8sph8dkbDQk3POHW6gQ9rAahcC+4JBKI5" +
       "Lf+K\nYespaFWGBPJeOND+1oebnhmVgZw3zj0in2tA3fvNtzuDD50ZlHz+ds" +
       "1HfLjl6PcnL/ROlvkne9pZ\nRW1lPo/sa4Ux9RavgLbxNAjq9xa0Hd/T+7XY" +
       "EedbyVDVIKUGwTKplvAhLEtv2WULWXysKgz9YvhN\n9EI/8V+HvqywzGeULH" +
       "NozfnlhAgxeJzQisS8g6HaBGG9XjdTEkH0FPTo/EzxriPC/IH/Y/5N8Gvy\n" +
       "zG/6r5k/vvmXs0SooeO4x+UD1P0VjooZVFM0SZkHKjFLWrWZofIhqms5r6T+" +
       "rVfSDE0u1Ujy43Za\n0WVUXqDUyJd3bbsY+exP0RJlLzm1cNOIu4aRhz75SF" +
       "Rp2SSuC5Nq5aFoicfeUueEbHGh3OWL2PIe\nSX83XOj99GA/f+ST3QsJlUcG" +
       "leO95RMdZCgIRPz1PisTqQZxkvIbZkhepwpPQO6ZWQWgIv4/kEFw\nV/6HYE" +
       "DdfGLrzPShjQ+LY6FCNfDIiLgKws1WtoLZU6BtTGkZWZ+gV17ue/OlGzJAUN" +
       "AkFiX5Erk6\nTgbAyVO6SetOWUy0VSOvT331xmNPfx0QbeI/rLtyytYRAAA=");
}
