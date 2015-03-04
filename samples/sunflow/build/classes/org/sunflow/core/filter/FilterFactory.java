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
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "Gwgcq/RvDQAA");
}
