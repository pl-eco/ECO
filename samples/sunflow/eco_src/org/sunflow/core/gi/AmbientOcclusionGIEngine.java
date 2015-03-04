package org.sunflow.core.gi;
import org.sunflow.core.GIEngine;
import org.sunflow.core.Options;
import org.sunflow.core.Ray;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;
public class AmbientOcclusionGIEngine implements GIEngine {
    private Color bright;
    private Color dark;
    private int samples;
    private float maxDist;
    public AmbientOcclusionGIEngine(Options options) { super();
                                                       bright = options.getColor(
                                                                          "gi.ambocc.bright",
                                                                          Color.
                                                                            WHITE);
                                                       dark =
                                                         options.
                                                           getColor(
                                                             "gi.ambocc.dark",
                                                             Color.
                                                               BLACK);
                                                       samples =
                                                         options.
                                                           getInt(
                                                             "gi.ambocc.samples",
                                                             32);
                                                       maxDist =
                                                         options.
                                                           getFloat(
                                                             "gi.ambocc.maxdist",
                                                             0);
                                                       maxDist =
                                                         maxDist <=
                                                           0
                                                           ? Float.
                                                               POSITIVE_INFINITY
                                                           : maxDist;
    }
    public Color getGlobalRadiance(ShadingState state) {
        return Color.
                 BLACK;
    }
    public boolean init(Scene scene) { return true;
    }
    public Color getIrradiance(ShadingState state,
                               Color diffuseReflectance) {
        OrthoNormalBasis onb =
          state.
          getBasis();
        Vector3 w =
          new Vector3(
          );
        Color result =
          Color.
          black();
        for (int i =
               0;
             i <
               samples;
             i++) {
            float xi =
              (float)
                state.
                getRandom(
                  i,
                  0,
                  samples);
            float xj =
              (float)
                state.
                getRandom(
                  i,
                  1,
                  samples);
            float phi =
              (float)
                (2 *
                   Math.
                     PI *
                   xi);
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
            Ray r =
              new Ray(
              state.
                getPoint(),
              w);
            r.
              setMax(
                maxDist);
            result.
              add(
                Color.
                  blend(
                    bright,
                    dark,
                    state.
                      traceShadow(
                        r)));
        }
        return result.
          mul(
            (float)
              Math.
                PI /
              samples);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1166302002000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAKVYfWwcxRUf39m++COc7SS2ExI7dgyUJNy2aqAljhSMccKR" +
       "DXbPsQEb6sztzt1t\nvLuz7M7ZFxNR0lYkBbUlopWKVEKKUiVAKEi0SitRGg" +
       "S0lKgSVCqVkEhbRWortVSqKtFU7R99M7t7\ne7t35yRw0s7u7bz35n3+5s2e" +
       "+Qg1OTZarzgpdtAiTmp0cgLbDlFHdew4++DVnPJWU8vEqT0mjaEG\nGcU0la" +
       "GkrDiSihmWNFVK3zFcstEWi+oH8zplKVJiqQP6zZ68u+SbqwTec/xs1+GTjf" +
       "0x1CSjJDZN\nyjDTqDmmE8NhqEM+gBewVGSaLsmaw4ZltJKYRWOUmg7DJnMe" +
       "RA+juIyaLYXLZGhA9heXYHHJwjY2\nJLG8NCGWBQmrbMKwZhJ1pLwccG4Nc4" +
       "LaHl+mmhqErOCT02CO0ACs3li22rW2ylQrfnr6lkMnnouj\n5AxKauYkF6aA" +
       "JQzWm0HtBjGyxHZGVJWoM6jTJESdJLaGdW1JrDqDuhwtb2JWtImTIQ7VFzhh" +
       "l1O0\niC3W9F/KqF3hNtlFhVG77KOcRnTV/9eU03EezO4OzHbN3cXfg4GtGi" +
       "hm57BCfJbGec2EiPdHOco2\nDu0BAmBNGIQVaHmpRhPDC9TlxlLHZl6aZLZm" +
       "5oG0iRZhFYbW1RXKfW1hZR7nyRxDvVG6CXcKqFqE\nIzgLQ2uiZEISRGldJE" +
       "oV8dnS/fHR099/7TaR240qUXSufysw9UWYMiRHbGIqxGW8VEx9J31fcX0M\n" +
       "ISBeEyF2aUauOzsl//UX/S7NtTVoxrMHiMLmlLuP9Wce2k1RnKuxwqKOxoMf" +
       "slyUw4Q3M1yyoGq7\nyxL5ZMqfPJf55X2PPE/+FkOtadSsUL1oQB51KtSwNJ" +
       "3Yu4lJbMyImkYtxFRHxXwaJeBZhpR3347n\ncg5hadSoi1fNVPwHF+VABHdR" +
       "CzxrZo76zxZmBfFcshBCCbjQrXC1I/cn7gztTElO0czpdFFybEWi\ndr78X6" +
       "E2kfKaNGJkNWKycUXRiw5Yszs9ZuZBiRRPJIuhWalADSJhBZuaSYEDSlehN6" +
       "lkgd8/nfgS\nt6BrsaGBQ2K0tHWoijuprhJ7Tjl18Z1DY3u+cdRNG57qnu0A" +
       "KbBqyls1xVdN5bVUvVVRQ4NYbDVf\n3Y0ixGAeqhlwr/3GyQfu2n90MA7pYy" +
       "02ggM56SBY6ak0ptDRoOTTAh0VyLveZ2ePpC6d2unmnVQf\nmWtyt7374vkT" +
       "/2rfHEOx2rDJTQXgbuViJjjWluFwKFpoteT/47G9r7x//sPPBCXH0FAVElRz" +
       "8koe\njAbFpgpRARsD8SfXJuP3oOljMdQI8ACQKPQHtOmLrhGq6GEfHbktCR" +
       "m15ahtYJ1P+ZDWygo2XQze\niGzpEM+rIDhtPMU3wdXl5by489k1Fh+73ezi" +
       "0Y5YIdD30teaP/v7V9veEm7xgTpZsRVOEuaWfWeQ\nLPtsQuD9h9+bePK7Hx" +
       "2ZFZnipQqD/bGY1TWlBCzXByxQ7zpgDg/k0JRpUFXLaTirE55x/0te97mf\n" +
       "/P1bHW5odHjjR3br5QUE79fejh45/+V/9wkxDQrfbwIzAjLXmlWB5BHbxge5" +
       "HqXDv93w1K/w0wCH\nAEGOtkQEqjR4RcCV6gFwrKq0cUuoJRwtCbLNYkzxSA" +
       "hmJOY+z4dBWHxrndqosf3PKYeezw8WH/z1\nz4RZbbiyj6iM015sDbupwYdN" +
       "3P090fK+EzsFoNt27u77O/Rz/wWJMyBRgW3XGbcBYUqhKHvUTYkP\nXn+je/" +
       "97cRTbhVp1itVdWBQIaoHMJE4BwKlk7bxNJF/H4go+CpORcMI6zwGlin+NoN" +
       "yN9fFhF28e\ngtKay249Lb8z/rRwQF1kqLF3RuQsvTZ1/NJv2AUhJyhRzj1Q" +
       "qkZeaLgC3i++v9DZ/PIzRgwlZlCH\n4rWE01gv8kKYgQ7G8ftEaBtD8+FuxN" +
       "16h8sQtD4KDxXLRsEhQHx45tT8uT2CB2LLWwvXSg8PVkbx\noAGJhxHBMiTG" +
       "G8rVm7BsbQHzNhE1Z20tX2B+3vdU5r1mQCPEa5LaLrzwcRsfbncD/YW6CbEj" +
       "rGoP\nXNd4ql5TR9U9fBhl4DNsz1+lPvJV6tMLV9LTJ1lHn4ynT8LBhqVD9d" +
       "iot/IoY2sGtEQLAuEvPjr4\n87ennjni7orLZH2Ia075yh/+OB//9utZly+a" +
       "2hHiY30n//zKxcxqF0Hd3nlTVftayeP2zyJ3khYH\ni4HlVhDUb24ZOPNw5o" +
       "KnUVe4CxyDk9JfDr5BbtjxzT/VaE/i0OFHAjN5lYFZ50/79xqB2e8HxsCl\n" +
       "OwDRhYQpy11phvFyojiqCF5GEeGg6yugq7wNrK3aBvzuintzQ73eX3jyyL3/" +
       "bH8Uv/lAzNsYZhlg\nKbVu0skC0SsWi3NJocZrrzjtBLj02HMvnGXvbdnuxm" +
       "Rz/eyKMm7efmKpf/tLj3+Cdqs/YltUdOfC\ntV+KF7S3Y+JA5sJc1UEuzDQc" +
       "BrdW0Kdom/tCELcx3PLc4qWEnxrVLU8QumA7juzlG6qCOFnAKhwX\n+YGZCD" +
       "ELy2zoS3yAfbgzT9hunWaxngFubtDlQCrIPna5MvA3UPGHhp0wBFef54S+T+" +
       "qE7monKHBY\nE/xfX8b6o3w4zPiBTKuqtESWUp1gMzD0q5/GUBmuQc/QwSs2" +
       "NCYkxq4s2pcLmVjmyWX88RQfnmBo\nJWRDGnrKq82EY1fqINibe+sd7Xhv21" +
       "v1ecj9pKHIHzx0/8fy7/4jDinlzw5tcPbPFXW9sruoeG62\nbJLThIVtbq9h" +
       "iduzDK2qcehkKJbXhLo/cOl+yFBHlA6Sht8qyU4z1FZBxjdX96mS6AXYSYCI" +
       "P56x\nfM92iJaVd1cpt7sqhbzFPbIpBIziS50PXkX3W92ccu+LsxtLj+97Qi" +
       "Bik6LjJVHerTJKuIeyMgAO\n1JXmy3oXvfzS9Ks/utUHeNGTry4FG08o17e5" +
       "s8tEH0C39klozLCYOLss/bTnxztOHb8QE2ex/wMu\ntWtVYBUAAA==");
}
