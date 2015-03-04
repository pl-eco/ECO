package org.sunflow.core.gi;
import org.sunflow.core.GIEngine;
import org.sunflow.core.Options;
import org.sunflow.core.Ray;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class PathTracingGIEngine implements GIEngine {
    private int samples;
    public PathTracingGIEngine(Options options) { super();
                                                  samples = options.getInt(
                                                                      "gi.path.samples",
                                                                      16);
    }
    public boolean requiresPhotons() { return false; }
    public boolean init(Scene scene) { samples = Math.max(0, samples);
                                       UI.printInfo(Module.LIGHT,
                                                    "Path tracer settings:");
                                       UI.printInfo(Module.LIGHT,
                                                    "  * Samples: %d",
                                                    samples);
                                       return true; }
    public Color getIrradiance(ShadingState state, Color diffuseReflectance) {
        if (samples <=
              0)
            return Color.
                     BLACK;
        Color irr =
          Color.
          black();
        OrthoNormalBasis onb =
          state.
          getBasis();
        Vector3 w =
          new Vector3(
          );
        int n =
          state.
          getDiffuseDepth() ==
          0
          ? samples
          : 1;
        for (int i =
               0;
             i <
               n;
             i++) {
            float xi =
              (float)
                state.
                getRandom(
                  i,
                  0,
                  n);
            float xj =
              (float)
                state.
                getRandom(
                  i,
                  1,
                  n);
            float phi =
              (float)
                (xi *
                   2 *
                   Math.
                     PI);
            float cosPhi =
              (float)
                Math.
                cos(
                  phi);
            float sinPhi =
              (float)
                Math.
                sin(
                  phi);
            float sinTheta =
              (float)
                Math.
                sqrt(
                  xj);
            float cosTheta =
              (float)
                Math.
                sqrt(
                  1.0F -
                    xj);
            w.
              x =
              cosPhi *
                sinTheta;
            w.
              y =
              sinPhi *
                sinTheta;
            w.
              z =
              cosTheta;
            onb.
              transform(
                w);
            ShadingState temp =
              state.
              traceFinalGather(
                new Ray(
                  state.
                    getPoint(),
                  w),
                i);
            if (temp !=
                  null) {
                temp.
                  getInstance().
                  prepareShadingState(
                    temp);
                if (temp.
                      getShader() !=
                      null)
                    irr.
                      add(
                        temp.
                          getShader().
                          getRadiance(
                            temp));
            }
        }
        irr.
          mul(
            (float)
              Math.
                PI /
              n);
        return irr;
    }
    public Color getGlobalRadiance(ShadingState state) { return Color.
                                                                  BLACK;
    }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1166303834000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVYfWwcxRUf39m++IOe7cSOHRJ/xZCShNtWDbSNkYIxjjHZ" +
                                                   "YNd2DHEIztzu3N3G\ne7ub3bnz2UR8qFIcQECjAioSJFEVKYFCQaJVWonSIK" +
                                                   "AFIiRAAiQkQqtIbaWWSlUlmqr9o29mdm9v\n985HGizt7NzOe2/mff3eG7/w" +
                                                   "BapzbLRecRJ00SJOYnhqAtsOUYd17DjT8GlOeauuYeL0LsOMoBoZ\nRTSVor" +
                                                   "isOJKKKZY0VRq7dbBgoy2WqS+mdZMmSIEmDuo3uPJul28oE3jn8bNtD56q7Y" +
                                                   "mgOhnFsWGY\nFFPNNEZ0knUoapEP4jyWclTTJVlz6KCMriJGLjtsGg7FBnUO" +
                                                   "oftQVEb1lsJkUtQne5tLsLlkYRtn\nJb69NMG3BQmrbUKxZhB1qLgdcG4Ncs" +
                                                   "KxXb7JcmoQsootzoA6/ASgdW9Ra6FtmapW9MzMjYdPPhdF\n8VkU14wpJkwB" +
                                                   "TSjsN4uasySbJLYzpKpEnUWtBiHqFLE1rGtLfNdZ1OZoaQPTnE2cSeKYep4R" +
                                                   "tjk5\ni9h8T++jjJoVppOdU6hpF22U0oiuer/qUjpOg9odvtpC3Z3sOyjYqM" +
                                                   "HB7BRWiMdSO68Z4PGeMEdR\nx4FdQACssSyhGbO4Va2B4QNqE77UsZGWpqit" +
                                                   "GWkgrTNzsAtF61YUymxtYWUep8kcRZ1hugmxBFQN\n3BCMhaL2MBmXBF5aF/" +
                                                   "JSiX+2dHx59Mwzr93MY7tWJYrOzt8ITN0hpkmSIjYxFCIYL+UST4ztza2P\n" +
                                                   "IATE7SFiQTN0zdk98l9+2yNorq5AM548SBQ6p9xxrGfy3lETRdkxVlmmozHn" +
                                                   "BzTn6TDhrgwWLMja\njqJEtpjwFs9N/m7vA8+Tv0ZQ4xiqV0w9l4U4alXMrK" +
                                                   "XpxB4lBrExJeoYaiCGOszXx1AM5jKEvPg6\nnko5hI6hWp1/qjf5bzBRCkQw" +
                                                   "EzXAXDNSpje3MM3wecFCCMXgQdvg+QYSf/xN0faE5OSMlG4uSI6t\nSKadLv" +
                                                   "5WTJtIaQ18SzPTNlYgVEbHRow07J9gMWRRNCNlzCyRsIINzTCBGLJWMa9XSZ" +
                                                   "69r1hygZ27\nbaGmhgFhOKF1yIXbTF0l9pxy+uK7h0d2PXRUBAsLcFdjijbB" +
                                                   "hgl3wwTbMJHWEhU2RDU1fJ81bGPh\nNjD6PKQvAF3zdVP7bz9wtD8K8WIt1I" +
                                                   "LFGGk/6OaeZkQxh/0cH+NwqECgdf5033Li0ukdItCklaG4\nInfT+y+eP/nP" +
                                                   "5s0RFKmMk0xLQOpGJmaCgWsR/wbCmVVJ/t8f3v3Kx+c/+6afYxQNlKV+OSdL" +
                                                   "3f6w\nP2xTISqAoS/+VFc8eieaORZBtYAHgIH8/AAv3eE9Aik86MEh0yUmo6" +
                                                   "aUaWexzpY8DGukGdtc8L/w\nQGnh89XgnCYW093wtLpBzt9std1iY4cILObt" +
                                                   "kBYcbi/9sP5bn7za9BY3i4fM8ZLaN0WoyPNWP1im\nbULg+2c/mfjxk18s7+" +
                                                   "OR4oYKhYKYS+qaUgCWa30WSHAdQIY5cmCPkTVVLaXhpE5YxP03fs23f/m3\n" +
                                                   "x1qEa3T44nl261cL8L933YIeOH/Pv7q5mBqFFRhfDZ9MaLPalzxk23iRnaPw" +
                                                   "4Icbnv49fhbwDzDH\n0ZYIh5EaNwnYodYCGpYl2bjFj8UNLXGyzXxMME9wZs" +
                                                   "TXvsOGfth86wq5UaHezymHn0/35w6982uu\nVhMubRxK/bQbW4MiNNiwkZl/" +
                                                   "bTi9b8NOBui2nbvj7hb93H9A4ixIVKDOOuM2gEsh4GWXui726etv\ndBz4II" +
                                                   "oiO1GjbmJ1J+YJghogMomTAVwqWDtu5sHXsrCKjVxlxI2wzjVAoeQXA5TrVs" +
                                                   "aHnaxb8FNr\nLrn1jPzu+LPcACsiQ4ViGZKz9Nqe45feoxe4HD9FGXdfoRx0" +
                                                   "ocPyeb/3cb61/uUT2QiKzaIWxe0B\nZ7CeY4kwCy2L4zWG0CcG1oPth6i1g0" +
                                                   "UIWh+Gh5Jtw+Dggz3MGTWbN4fwoJlZuxOeuIsH8TAe1CA+\nGeIsA3zcVMze" +
                                                   "mGVrecz6QhRzcNbSITZs1FnamdtaFip8nuPXxSP9v3l7z4llgflVfBrgmlPu" +
                                                   "//wP\n89HHX08KvrDjQsTHuk/96ZWLk2sEPohWcGNZN1bKI9pBbpm4xVKhr9" +
                                                   "oOnPrNLX0v3Dd5wT1RW7Cp\nGYHG/8+Lb5BNNz36xwp1NwoNq0BZNm5jwy0i" +
                                                   "3r9bKS/4ua4N5IOLLV1l2OKVbKbEhpU6SK7A8l3/\naD6C39wfcdFmN4UENa" +
                                                   "3rdZIneslmtUxSoJrv5j2zH+wPP/ezs/SDLduFKTav7NQw4+btJ5d6tr/0\n" +
                                                   "yBXU8J6QbmHRrfmrfxDNaG9HeFsvcqfsOhBkGgxmTCOcJ2cb04G86Q3W0QF4" +
                                                   "2t28aa9YR33XVcb4\nA1XWkmzYD7Btk0M5DaBzImNSsAonHrVExMiQeEnT1A" +
                                                   "k2/Gi6p0o0BVCW/9hbrlSXq1TXZSsVqnod\nZZE5pUALz/mzVXQ+xIaDlLXp" +
                                                   "Gs8R1ddq/utoJcPT62rVe9laRbjEiKfVhnKtMliFTpndkIlHtbaU\nSsvCnY" +
                                                   "91I6bNt1msovz9bMhTdFUabjHQZagai8uvEusbaOHrGOhGeHa4BtpxpW6vbi" +
                                                   "Au5qEqBniU\nDUcoagUDjOpmEuuT/7cRli/XCFCyVle47LBur7PsPyTiVq/I" +
                                                   "n95795fyR//mbXvx5t0E199UTtdL\n623JvN6ySUrjCjaJ6mvx11NwgAo3MI" +
                                                   "oiaY2f9ElB9zRFLWE6yBD2KiV7hqKmEjJWkMWslOgEVB8g\nYtOTlmfUFt7E" +
                                                   "sX4jIfqNQsBQzCIbA6jO/1nlIW9O/LtqTrnrxX29hUemf8ThvE7R8dISE9Mo" +
                                                   "o5i4\nphTRu29FaZ6s99HLL828+vPve9WJd6lrCn7VDITyNrFaxfFQMSrfDU" +
                                                   "ayFuXd/NKv1v7iptPHL0T4\n7eR/8s8Gr2MUAAA=");
}
