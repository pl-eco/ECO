package org.sunflow.core.primitive;
import org.sunflow.SunflowAPI;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.ParameterList;
import org.sunflow.core.PrimitiveList;
import org.sunflow.core.Ray;
import org.sunflow.core.ShadingState;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
public class Background implements PrimitiveList {
    public Background() { super(); }
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public void prepareShadingState(ShadingState state) { if (state.getDepth(
                                                                      ) ==
                                                                0) state.
                                                                     setShader(
                                                                       state.
                                                                         getInstance(
                                                                           ).
                                                                         getShader(
                                                                           0));
    }
    public int getNumPrimitives() { return 1; }
    public float getPrimitiveBound(int primID, int i) { return 0;
    }
    public BoundingBox getWorldBounds(Matrix4 o2w) { return null;
    }
    public void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        if (r.
              getMax(
                ) ==
              Float.
                POSITIVE_INFINITY)
            state.
              setIntersection(
                0,
                0,
                0);
    }
    public PrimitiveList getBakingPrimitives() { return null;
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1165455048000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Yb2wcxRWfO/93HJ/tOIlJYyc2DlKc9JZITVuaqCW2HOJw" +
                                                    "Sdw4gdaUmPHu3Hnj3Z1ld86+OHX5o1aOkBqh4tAEFQvRIEoLBFWNKKqQ8qmA" +
                                                    "6BcQouID0G9FbfMhX2gl2tL3Zv/e3vkc+NCTZnZ35r0378177zdv7sXrpMF1" +
                                                    "yC6bG2cKBhdZVhLZ08berDhjMzd7OLd3nDou00YM6ronYGxKHXgl8+lnj890" +
                                                    "pEnjJNlALYsLKnRuuceZy405puVIJhodNZjpCtKRO03nqFIUuqHkdFfsy5F1" +
                                                    "MVZBBnOBCgqooIAKilRBORBRAdN6ZhXNEeSglnAfJD8iqRxptFVUT5D+ciE2" +
                                                    "dajpixmXFoCEZvy+B4ySzCWHbA9t92yuMPjCLmX556c6fltHMpMko1sTqI4K" +
                                                    "SghYZJK0mcycZo57QNOYNkk6Lca0Cebo1NAXpN6TpMvVCxYVRYeFm4SDRZs5" +
                                                    "cs1o59pUtM0pqoI7oXl5nRla8NWQN2gBbN0U2epZeBDHwcBWHRRz8lRlAUv9" +
                                                    "rG5pgmxLcoQ2Dt4NBMDaZDIxw8Ol6i0KA6TL851BrYIyIRzdKgBpAy/CKoJs" +
                                                    "WVUo7rVN1VlaYFOC9CTpxr0poGqRG4EsgmxMkklJ4KUtCS/F/HP96P7zZ61D" +
                                                    "VlrqrDHVQP2bgakvwXSc5ZnDLJV5jG1DuSfpptfPpQkB4o0JYo/m1R/euHN3" +
                                                    "37U3PZqvVKE5Nn2aqWJKvTzd/s7WkZ131KEazTZ3dXR+meUy/Mf9mX0lGzJv" +
                                                    "UygRJ7PB5LXjf/z+w79mf0+T1jHSqHKjaEIcdarctHWDOXcxizlUMG2MtDBL" +
                                                    "G5HzY6QJ3nO6xbzRY/m8y8QYqTfkUCOX37BFeRCBW9QE77qV58G7TcWMfC/Z" +
                                                    "hJAmaOR2aG3E+8mnIJpy0oVwV6hKLd3iCgQvo446ozCVT03D7s6Y1Jl1FbXo" +
                                                    "Cm4qbtHKG3xecR1V4U4h/Fa5wxTb0U2wd44pwxAOBYcXLS2L0Wb/n9Ypob0d" +
                                                    "86kUuGJrEggMyKFD3NCYM6UuF4dHb7w89XY6TAx/pwTZActl/eWyuFw2XC4b" +
                                                    "LUdSKblKNy7rORtcNQtJD3DYtnPi/sMPnBuogyiz5+thn5F0ACz1dRlV+UiE" +
                                                    "DGMS/1QIz55n71vK/uv573jhqawO41W5ybWL84/c89DtaZIux2O0DYZakX0c" +
                                                    "UTREy8FkHlaTm1n65NMrTy7yKCPLAN4HikpOTPSBpBccrjINoDMSP7SdXp16" +
                                                    "fXEwTeoBPQAxBYUIBzDqS65RlvD7AvBEWxrA4Dx3TGrgVIB4rWLG4fPRiAyP" +
                                                    "dvneCU5ZhxmwEdp6PyXkE2c32Nh3e+GEXk5YIcH54GvXLl19atcd6TiOZ2In" +
                                                    "4wQTHip0RkFywmEMxj+8OP7EhetL98kIAYpbqy0wiP0IYAS4DLb1J28++MHH" +
                                                    "H11+Lx1FlYDDsjht6GoJZNwWrQIIYgCKoe8HT1om1/S8TqcNhsH578yOPVf/" +
                                                    "cb7D86YBI0Ew7F5bQDR+yzB5+O1T/+yTYlIqnmCR5RGZtwEbIskHHIeeQT1K" +
                                                    "j7zbe+kN+jQALICaqy8wiVNEWkbk1ivSVUOyzybm9mC33a6YK8mRnhjlYGwk" +
                                                    "Jd83QmxVZPl4kOVSOVC6d7UzSp6vlx9dXtGOPbfHS9WuctwfhbLmpff/86fs" +
                                                    "xb+8VQViWgS3v2qwOWbEVGvCJcsg4og8vqNEeeyF37wq3tn1LW/JodXRIcn4" +
                                                    "xqN/23Li2zMPfAFg2JYwPinyhSMvvnXXberP0qQuxISKiqScaV98G2BRh0EJ" +
                                                    "ZeGG4kir9FVfmJwb0H+3QOvwk7OjanJGDo7CJC33M13D1Wgqg4IHXR2QbYqT" +
                                                    "TXjPA+NjcpmRGoE4ht2dkIlFW4MzHLy4s0b5HQSZV44oi10fz/7ik5c8jybr" +
                                                    "mwQxO7f82OfZ88vpWBF4a0UdFufxCkGp5XpvYz+HXwraf7GhCTjgFQJdI341" +
                                                    "sj0sR2wb86C/llpyiYN/vbL4h18tLqX9LfmmIE3TnBuMWpUJKgf2l4PwN6B1" +
                                                    "+37uvmk/p8pTurfCzxMzVIP6Fit8JsV8r4Yff4DdSQHLOgwQl8WZJcPd2B3z" +
                                                    "9P+uIPVzXNfWNC+Dg9v8UA5C+ubMiyvHaswVsJuGS1mBiaNFM3SPW03rOrhO" +
                                                    "rKk0NjIArddXuveL5h5+6rKTpHYN9R3sTEE6Qf1Q92GssarpD+csp2tbsBkH" +
                                                    "d0Dr9y3o/7JR1ROPKhNK6uwRCrem0tekhLM1DHsIu5Ig7WDYvdwxNM+oQPDW" +
                                                    "CsFyHkJumJduLm2O+kYGxt6cgXVSYl2gR3dF2hynZ6TrAoqBCooxvJO63gkf" +
                                                    "ZddSjc34KXY/BpTRA9bQ1zhzak2DZd7shjbkGzz0pRLpiRpzF7B7HBAAHDZM" +
                                                    "AWALUS7h1N4q1YYgrdGlAEudnor/H7w7s/rySqZ588rJP8syN7zXtsDlMl80" +
                                                    "jNjBGD8kGwGM8l4OtXjVqy0fT8FVffV7ClQYdpnmlzyupwEkklwAZPiIkz0j" +
                                                    "yLoYGYC5/xYn+iVgCRDh62U7iJQOWefhXwtZ7x5dImXlmF32VVb34lEp/+EJ" +
                                                    "Co6i9x/PlHpl5fDRsze+/pysXhpUgy4soJTmHGnySv6waOlfVVogq/HQzs/a" +
                                                    "X2nZEZxU7dh1+XV+Qrdt1cvhUdMWsoBd+P3m3+1/fuUjWY//D08WYzx6EwAA");
}
