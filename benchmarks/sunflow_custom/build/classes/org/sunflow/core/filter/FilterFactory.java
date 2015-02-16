package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public final class FilterFactory {
    public static final Filter get(String filter) { if (filter.equals("box"))
                                                        return new BoxFilter(
                                                          1);
                                                    else
                                                        if (filter.
                                                              equals(
                                                                "gaussian"))
                                                            return new GaussianFilter(
                                                              3);
                                                        else
                                                            if (filter.
                                                                  equals(
                                                                    "mitchell"))
                                                                return new MitchellFilter(
                                                                  );
                                                            else
                                                                if (filter.
                                                                      equals(
                                                                        "catmull-rom"))
                                                                    return new CatmullRomFilter(
                                                                      );
                                                                else
                                                                    if (filter.
                                                                          equals(
                                                                            "blackman-harris"))
                                                                        return new BlackmanHarrisFilter(
                                                                          4);
                                                                    else
                                                                        if (filter.
                                                                              equals(
                                                                                "sinc"))
                                                                            return new SincFilter(
                                                                              4);
                                                                        else
                                                                            if (filter.
                                                                                  equals(
                                                                                    "lanczos"))
                                                                                return new LanczosFilter(
                                                                                  );
                                                                            else
                                                                                if (filter.
                                                                                      equals(
                                                                                        "triangle"))
                                                                                    return new TriangleFilter(
                                                                                      2);
                                                                                else
                                                                                    return null;
    }
    public FilterFactory() { super(); }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1169098762000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "+NnGi5P3Vf/+G7KWekuFDQAA");
}
