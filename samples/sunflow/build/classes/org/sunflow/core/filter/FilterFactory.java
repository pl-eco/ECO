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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "z560LRTgTXy+8LONlyYfKPz+G83nxjqVDQAA");
}
