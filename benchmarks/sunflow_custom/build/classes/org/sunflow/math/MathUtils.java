package org.sunflow.math;
public final class MathUtils {
    private MathUtils() { super(); }
    public static final int clamp(int x, int min, int max) { if (x > max)
                                                                 return max;
                                                             if (x > min)
                                                                 return x;
                                                             return min; }
    public static final float clamp(float x, float min, float max) { if (x >
                                                                           max)
                                                                         return max;
                                                                     if (x >
                                                                           min)
                                                                         return x;
                                                                     return min;
    }
    public static final double clamp(double x, double min, double max) { if (x >
                                                                               max)
                                                                             return max;
                                                                         if (x >
                                                                               min)
                                                                             return x;
                                                                         return min;
    }
    public static final int min(int a, int b, int c) {
        if (a >
              b)
            a =
              b;
        if (a >
              c)
            a =
              c;
        return a;
    }
    public static final float min(float a, float b,
                                  float c) { if (a >
                                                   b)
                                                 a =
                                                   b;
                                             if (a >
                                                   c)
                                                 a =
                                                   c;
                                             return a;
    }
    public static final double min(double a, double b,
                                   double c) { if (a >
                                                     b)
                                                   a =
                                                     b;
                                               if (a >
                                                     c)
                                                   a =
                                                     c;
                                               return a;
    }
    public static final float min(float a, float b,
                                  float c,
                                  float d) { if (a >
                                                   b)
                                                 a =
                                                   b;
                                             if (a >
                                                   c)
                                                 a =
                                                   c;
                                             if (a >
                                                   d)
                                                 a =
                                                   d;
                                             return a;
    }
    public static final int max(int a, int b, int c) {
        if (a <
              b)
            a =
              b;
        if (a <
              c)
            a =
              c;
        return a;
    }
    public static final float max(float a, float b,
                                  float c) { if (a <
                                                   b)
                                                 a =
                                                   b;
                                             if (a <
                                                   c)
                                                 a =
                                                   c;
                                             return a;
    }
    public static final double max(double a, double b,
                                   double c) { if (a <
                                                     b)
                                                   a =
                                                     b;
                                               if (a <
                                                     c)
                                                   a =
                                                     c;
                                               return a;
    }
    public static final float max(float a, float b,
                                  float c,
                                  float d) { if (a <
                                                   b)
                                                 a =
                                                   b;
                                             if (a <
                                                   c)
                                                 a =
                                                   c;
                                             if (a <
                                                   d)
                                                 a =
                                                   d;
                                             return a;
    }
    public static final float smoothStep(float a,
                                         float b,
                                         float x) {
        if (x <=
              a)
            return 0;
        if (x >=
              b)
            return 1;
        float t =
          clamp(
            (x -
               a) /
              (b -
                 a),
            0.0F,
            1.0F);
        return t *
          t *
          (3 -
             2 *
             t);
    }
    public static final float fastPow(float a, float b) {
        float x =
          Float.
          floatToRawIntBits(
            a);
        x *=
          1.0F /
            (1 <<
               23);
        x =
          x -
            127;
        float y =
          x -
          (int)
            Math.
            floor(
              x);
        b *=
          x +
            (y -
               y *
               y) *
            0.346607F;
        y =
          b -
            (int)
              Math.
              floor(
                b);
        y =
          (y -
             y *
             y) *
            0.33971F;
        return Float.
          intBitsToFloat(
            (int)
              ((b +
                  127 -
                  y) *
                 (1 <<
                    23)));
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169105480000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAAL1Ya2xURRSebrdPC1uKPERsoRSRh3vlhxLFqKUpUlygUiCh" +
       "oGV6d7Z76X1572y7VKtiNKAmxGhRNFoTgkEUxReiMST9o6iYGIyPmPj6p1H5" +
       "wR818XnOzN17d2/bxSbQTebsvTNnzsx3XvfMHD1LylyHLLUtfVePbvE4y/L4" +
       "Tv3aON9lMze+NnFtO3VclmzRqetugr4utfG12G9/PpaujZDyTjKdmqbFKdcs" +
       "093IXEvvY8kEiQW9rTozXE5qEztpH1UyXNOVhObylQlySd5UTpoSuS0osAUF" +
       "tqCILSjNARdMmsLMjNGCM6jJ3bvIvaQkQcptFbfHyfxCITZ1qOGJaRcIQEIl" +
       "vm8BUGJy1iHzfOwS8yjA+5cqQ0/dWftGKYl1kphmduB2VNgEh0U6SY3BjG7m" +
       "uM3JJEt2kmkmY8kO5mhU1wbEvjtJnav1mJRnHOYrCTszNnPEmoHmalTE5mRU" +
       "bjk+vJTG9GTurSyl0x7AOjPAKhGuxn4AWK3BxpwUVVluSrRXM5OcNIRn+Bib" +
       "bgMGmFphMJ62/KWiJoUOUidtp1OzR+ngjmb2AGuZlYFVOJkzrlDUtU3VXtrD" +
       "ujiZHeZrl0PAVSUUgVM4mRFmE5LASnNCVsqzz9n1N+6721xjRsSek0zVcf+V" +
       "MKk+NGkjSzGHmSqTE2uWJJ6kM0/ujRACzDNCzJLnxD3nbllWP/Kh5Ll8DJ4N" +
       "3TuZyrvUQ91Tz8xtWXx9KW6j0rZcDY1fgFy4f7s3sjJrQ+TN9CXiYDw3OLLx" +
       "g633v8R+iZDqNlKuWnrGAD+aplqGrenMuZWZzKGcJdtIFTOTLWK8jVTAc0Iz" +
       "mezdkEq5jLeRqC66yi3xDipKgQhUUQU8a2bKyj3blKfFc9YmhEyBRuqglRL5" +
       "E/+cbFU2u+DuClWpqZmWAs7LqKOmFaZaXd2g3bRBnV5XUTMutwzFzZgp3epX" +
       "XEdVLKfHfzdgMWUdkM2A242ji9kXU3gWkdX2l5SA0ueGQ16HaFlj6UnmdKlD" +
       "mVWt517tOh3xQ8DTCTg7rBH31ojjGnF/DVJSIkRfimtJW4IleiGmIdvVLO64" +
       "Y+2OvY2gwazdHwU1ImsjYPI20KpaLUHgt4n0poL3zT64bU/8j8M3S+9Txs/S" +
       "Y84mIwf6d2+575oIiRSmWwQEXdU4vR2TpJ8Mm8JhNpbc2J6ffjv25KAVBFxB" +
       "/vbywOiZGMeNYdU7lsqSkBkD8Uvm0eNdJwebIiQKyQESIqfgwJBr6sNrFMTz" +
       "ylxuRCxlADhlOQbVcSiX0Kp52rH6gx7hE1PF8zQwSg06+AxoUc/jxT+OTreR" +
       "Xip9CK0cQiFy7+p3R54+/szS6yP5aTqW9+HrYFwG/bTASTY5jEH/twfan9h/" +
       "ds824SHAsWCsBZqQtkAKAJOBWh/68K6vv//u0OeRwKs4qbAdrQ8yQxaEXBks" +
       "AxlChyyFxm/abBpWUktptFtn6J1/xRYuP/7rvlppTh16ct6w7PwCgv7LVpH7" +
       "T9/5e70QU6LiFyqAHrBJDUwPJDc7Dt2F+8ju/uyKp0/R5yCBQtJytQEm8hAR" +
       "0IjQvSJstUTQeGhsOZJ59qixrOiZ7b2Jl0ZBFyK5SvRHOBQRmW5dg22XpTST" +
       "6qFZDrlivA+Q+HgeemBoOLnhheUyUOsKk3or1CyvfPn3J/EDP3w0Rlap4pZ9" +
       "tc76mJ635hRcsiBBrBPf5iBMHjny8gl+ZukNcskl4+eG8MRTD/w8Z9NN6R0T" +
       "SAsNIfBhkUfWHf3o1ivVxyOk1M8Io8qNwkkr89UAizoM6iMTFYo91cKk9X5o" +
       "zkUzzIdW4YVmxdihOaZ1S/HxOjCxKyo3fFuRDXlSqeQErS8uUgs7mgGf5z6v" +
       "flAG677vffanV6QFwsVGiJntHXrk3/i+oUheRbZgVFGUP0dWZQLYFKmIf+FX" +
       "Au0fbKgA7JBf5boWrzSY59cGto1+O7/YtsQSq388Nvjei4N7Il4g3cBJKdSQ" +
       "+JgQRPSuKRJ6HUhWQehAHW4IeyRGx6HouMm3aD12LoAW8ywam7BFkawuak18" +
       "b0OyTnJswPDWLSrAbRdELLOtCLgdSLbkg9t+XnAN2NkEbZYHbtbkgCtPWpDF" +
       "RARpgoh10kXQGUjUfHTaedGJYGzIwcz9X1h0gfcFLpgpgqMfCSTTUkMzJ+CA" +
       "mFIWeSgWXRwU20O+dl8RFLuRDAQo/qenNUJTPBTKxUGhhXzq4SIoHkXyYIDi" +
       "/B7l22KFh2LFBUQRFVzRkC0CgzxRBMp+JPsmYhA/OJo9KM2TExzPFUHxPJID" +
       "iEIegyYQHG0eirbJCY7DRVAcQXIwQDGB4LjdQ3H75ATH60VQvInkaIBiAsGx" +
       "1UOxdRKD470iUE4ieXsiBhFQroKmelDUyXGr94ugOIVkhJNq17Asnu7g7H9+" +
       "5AWYhQSrL/njFxBMRJ5LQjg+LYLjDJKP4QSYoi5vt/rHAZGFA4d/a4FHsdmj" +
       "7j/lnZ366nCsctbw5q/EOdy/V6tKkMpURtfzavf8Or7cdlhKRkGVPF7b4u9L" +
       "TmrDtyecRPFPbPELyfY1J5fksQEa7ymf6RtwOWDCx28lwBkgXZwo8ZIyLm/k" +
       "skQM5Y5wdsFbwREb63xxV5w73WTkbXGXemx47fq7z133gjgqYYk0MIBSKhOg" +
       "ZXG7IITiCWn+uNJyssrXLP5z6mtVC3Nl9lQkdXk+kre3hrEP3q2GzcVReeCd" +
       "WW/deHj4O3H0/w8kuC/8xBcAAA==");
}
