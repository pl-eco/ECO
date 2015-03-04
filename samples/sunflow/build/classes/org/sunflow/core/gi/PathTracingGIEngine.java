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
          black(
            );
        OrthoNormalBasis onb =
          state.
          getBasis(
            );
        Vector3 w =
          new Vector3(
          );
        int n =
          state.
          getDiffuseDepth(
            ) ==
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
                    getPoint(
                      ),
                  w),
                i);
            if (temp !=
                  null) {
                temp.
                  getInstance(
                    ).
                  prepareShadingState(
                    temp);
                if (temp.
                      getShader(
                        ) !=
                      null)
                    irr.
                      add(
                        temp.
                          getShader(
                            ).
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
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYXWwc1RW+O/53HK9/cOKExHEcB5qE7vAvgREhWI7jdMGW" +
                                                    "bSLhCMz1zN3die/OTGbu2huDaRIVOQpSVLWGhir1QxVEoYGgqhGtEFJeWkD0" +
                                                    "hapqxQNQ9QUEzUMeSlEpP+fcmdmZnV2b8ICluXvn3nPOPeeec75zxheukDrX" +
                                                    "IXtsix/LckukWFGkjvA7UuKYzdzUwfQdY9RxmT7IqetOwtq01vdq8rMvfppr" +
                                                    "U0j9FOmkpmkJKgzLdMeZa/E5pqdJMlwd4izvCtKWPkLnqFoQBlfThisG0mRd" +
                                                    "hFWQ/nSgggoqqKCCKlVQ94VUwLSemYX8IHJQU7hHyZMkkSb1tobqCbK9XIhN" +
                                                    "HZr3xYxJC0BCI74fAqMkc9EhvSXbPZsrDH5mj7r8i0fbfldDklMkaZgTqI4G" +
                                                    "Sgg4ZIq05Fl+hjnuPl1n+hRpNxnTJ5hjUG4sSL2nSIdrZE0qCg4rXRIuFmzm" +
                                                    "yDPDm2vR0DanoAnLKZmXMRjXg7e6DKdZsHVDaKtn4X5cBwObDVDMyVCNBSy1" +
                                                    "s4apC7ItzlGysf9HQACsDXkmclbpqFqTwgLp8HzHqZlVJ4RjmFkgrbMKcIog" +
                                                    "m1cVindtU22WZtm0IN1xujFvC6ia5EUgiyBdcTIpCby0OealiH+uPHjPmcfN" +
                                                    "A6YiddaZxlH/RmDqiTGNswxzmKkxj7Fld/pZuuGNUwohQNwVI/ZoXnvi6n03" +
                                                    "9Vx+y6O5vgrN6MwRpolp7fxM67tbBnfdVYNqNNqWa6DzyyyX4T/m7wwUbci8" +
                                                    "DSWJuJkKNi+P//nh4y+xTxXSPELqNYsX8hBH7ZqVtw3OnGFmMocKpo+QJmbq" +
                                                    "g3J/hDTAPG2YzFsdzWRcJkZILZdL9ZZ8hyvKgAi8ogaYG2bGCuY2FTk5L9qE" +
                                                    "kAZ4yO3wtBLvT/4KcljNWXmmUo2ahmmpELuMOlpOZZqlujRvc/CaWzAz3JpX" +
                                                    "XUdTLSdbetcsh6lZA5wvcpMO1SCWhkeGzCwomMIgs79f8UW0rm0+kYCL3xJP" +
                                                    "ew4Zc8DiOnOmteXC/UNXX5l+RymlgX8vgtwIB6b8A1N4YCprpKocSBIJec51" +
                                                    "eLDnXHDNLCQ5wF/LrolHDj52qq8Gosqer4V7RdI+sNHXZkizBkMkGJF4p0E4" +
                                                    "dv/68FLq8xf2euGorg7bVbnJ5bPzJw79+GaFKOX4i9bBUjOyjyFqltCxP553" +
                                                    "1eQmlz7+7OKzi1aYgWWA7gNDJScmdl/cD46lMR2gMhS/u5demn5jsV8htYAW" +
                                                    "gJCCwgUD+PTEzyhL8IEALNGWOjA4Yzl5ynErQLhmkXOs+XBFBkirnLeDU9Zh" +
                                                    "xPfA0+6ngPzF3U4bx+u8gEIvx6yQYLz/j5efu/TLPXcpUdxORirhBBMeCrSH" +
                                                    "QTLpMAbr758d+/kzV5YOywgBih3VDujHcRAwAVwG1/rUW0ff+/CD839TwqgS" +
                                                    "UBwLM9zQiiDjhvAUQAwOqIW+73/IzFu6kTHoDGcYnP9P7rzl0r/PtHne5LAS" +
                                                    "BMNN3y4gXN90Pzn+zqP/7ZFiEhpWrNDykMy7gM5Q8j7HocdQj+KJv2597k36" +
                                                    "KwBUADHXWGASlxJ+vqBSXQCvFfk4aku1pG9USbZbjil0nmQmcu82HHrtir2i" +
                                                    "XOkupeWu1bNsP1bmSHb+b5TPnPzX59LkivyqUpBi/FPqhXObB+/9VPKHgY7c" +
                                                    "24qVkAVdTMh760v5/yh99X9SSMMUadP8FukQ5QUMpyloC9ygb4I2qmy/vMR7" +
                                                    "9WyglMhb4kkWOTaeYiFUwhypcd4cy6oWvOVueJJ+ViXjWZUgcjIgWfrkuBOH" +
                                                    "HwRB3WA7xhzF/os0+EVhbUeNOUYeCuucX/nVxY4PZ899/LIHo3GvxIjZqeXT" +
                                                    "X6fOLCuRXmpHRTsT5fH6KWn2es/sr+EvAc9X+KC5uODV045Bv6j3lqq6bWO6" +
                                                    "bl9LLXnE/o8uLr7+m8Ulz4yO8lZiCDrll//+5V9SZ//5dpU6VgNtokQvW+rZ" +
                                                    "Xxb0fm5tqsitoLqhfltXa8mkbudPLq/oo8/fovjZdkCQJmHZP+RsjvHIYbUo" +
                                                    "qazwPSCb0DCyT7/429fEu3vu9qzcvbqT44xvnvxk8+S9uce+Q7nbFrMpLvLF" +
                                                    "By68PXyD9jOF1JQSpKKvLmcaKE+LZofBh4A5WZYcPaXk6MSg6IOny0+Orqol" +
                                                    "J3RZdWx7eI29wzgcgkrksKMFA75JxnKWgFuRxPtwGPIgcBiya8ayOKNmJUrK" +
                                                    "hfFyvTfCs8nXe9M16x0D9A0VQTehQWJIfn0Ns2TrSgW2tIaM7Ee+Ven2oL73" +
                                                    "+kr3XrPSipSoBEpvrVQ6R3VoB/FjkQVUG6NURh4+f7COWo48xlzDNmnPrCDr" +
                                                    "s9DQQ33UDYwsXDx6bUZiRO31jdz7XT2Dr5akWlhDxydwmBekHXQc5tYM5eNr" +
                                                    "6wng3VmlccZ2oLvim9z7jtReWUk2blx56B+yFSx96zXBB1emwHm0+kTm9bbD" +
                                                    "MoZUssmrRR7mnQAFqnTzgihZQ2p73KP7iSBtcTqIM/yJki0Jsi5ChqXJm0WJ" +
                                                    "TgPyAhFOn7aDuGiT3Q9W35RXfYskgpDYAEbfyrpBBEH5f44AsArefzqmtYsr" +
                                                    "Bx98/Oqdz0v0q9M4XVhAKY3wme81wiXQ276qtEBW/YFdX7S+2rQzAPNWHDr8" +
                                                    "7jem27bqTeJQ3hayrVv4w8bf3/PCygeyS/0GgPqr74ASAAA=");
}
