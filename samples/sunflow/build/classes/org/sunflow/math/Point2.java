package org.sunflow.math;
public final class Point2 {
    public float x;
    public float y;
    public Point2() { super(); }
    public Point2(float x, float y) { super();
                                      this.x = x;
                                      this.y = y; }
    public Point2(Point2 p) { super();
                              x = p.x;
                              y = p.y; }
    public final Point2 set(float x, float y) { this.x = x;
                                                this.y = y;
                                                return this; }
    public final Point2 set(Point2 p) { x = p.x;
                                        y = p.y;
                                        return this; }
    public final String toString() { return String.format("(%.2f, %.2f)",
                                                          x,
                                                          y); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAK1Yb2wURRSf2/4v0JaWP6VCW0oxFvBWMJBIDQq1QOGASgsJ" +
                                                    "NVKme3Pt0v3H7hw9KkUgGggaYrQgGOwHA0EQxRgJGkPCFwWDXzBG4wfB+EWj" +
                                                    "8oEPIhEV38zs7e7tXSskXLKzszPvvXlv3nu/eXNnbqACx0ZzLVPb0auZNEpS" +
                                                    "NLpVWxilOyziRFfFFrZj2yHxFg07TieMdSsNH5bfuvNaX4WECrtQFTYMk2Kq" +
                                                    "moaznjimtp3EY6jcH23ViO5QVBHbirdjOUlVTY6pDm2OoXEBVooaY2kVZFBB" +
                                                    "BhVkroK81KcCpgnESOotjAMb1NmGdqFIDBVaClOPopmZQixsY90V084tAAnF" +
                                                    "7HsjGMWZUzaq92wXNmcZfGiuPPzm5oqP8lB5FypXjQ6mjgJKUFikC43Xid5D" +
                                                    "bGdpPE7iXWiiQUi8g9gq1tRBrncXqnTUXgPTpE28TWKDSYvYfE1/58YrzDY7" +
                                                    "qVDT9sxLqESLp78KEhruBVun+LYKC5ezcTCwVAXF7ARWSJolv1814hTVhTk8" +
                                                    "GxtXAwGwFumE9pneUvkGhgFUKXynYaNX7qC2avQCaYGZhFUoqhlVKNtrCyv9" +
                                                    "uJd0U1QdpmsXU0BVwjeCsVA0OUzGJYGXakJeCvjnxtonD75grDQkrnOcKBrT" +
                                                    "vxiYakNM60mC2MRQiGAcPyd2GE+5sF9CCIgnh4gFzfmdN5+eV3vxsqB5KAfN" +
                                                    "up6tRKHdyvGesqvTW5qeyGNqFFumozLnZ1jOw7/dnWlOWZB5UzyJbDKanry4" +
                                                    "/otNu0+T3yRU2oYKFVNL6hBHExVTt1SN2CuIQWxMSbwNlRAj3sLn21AR9GOq" +
                                                    "QcToukTCIbQN5Wt8qNDk37BFCRDBtqgI+qqRMNN9C9M+3k9ZCKEJ8KBKePKQ" +
                                                    "+PE3RavlPlMnMlawoRqmDLFLsK30yUQxZQfrlgZec5JGQjMHZMdWZNPu9b51" +
                                                    "WAE2ACJ0QZQFlfVgxaWY9hUDkQhs7PRwWmuQEStNLU7sbmU4uaz15gfdVyQv" +
                                                    "zF27KZoKC0TdBaJsgahYAEUiXO4ktpBwFmx1PyQtwNn4po7nV23Z3wBblLIG" +
                                                    "8tlmAWkD2OCu3qqYLX5mt3H8UiC8qt95bl/09smnRHjJo8NwTm508cjAno0v" +
                                                    "PiYhKRNPmTUwVMrY2xkKemjXGM6jXHLL9/1y6+zhIdPPqAyAdhM9m5MlakN4" +
                                                    "321TIXGAPl/8nHp8rvvCUKOE8iH7AfEohggFMKkNr5GRsM1p8GO2FIDBCdPW" +
                                                    "scam0ohVSvtsc8Af4QFRxvsTwSnjWASzTqEb0vzNZqss1k4SAcS8HLKCg+vy" +
                                                    "Ty8ePffW3CekIA6XB062DkJFVk/0g6TTJgTGfzjS/sahG/ue4xECFLNyLdDI" +
                                                    "2hbIcXAZbOvLl7d9f/3a8W8kL6oiFA67ZI+mKimQ8bC/CiCABijEfN+4wdDN" +
                                                    "uJpQcY9GWHD+XT57/rnfD1YIb2owkg6Gef8vwB+ftgztvrL5z1ouJqKwE8i3" +
                                                    "3CcTG1DlS15q23gH0yO15+sZRy/htwEgAZQcdZBwnEHcMsS3XuaumsPbaGhu" +
                                                    "Pmvqraw5PlCT7eMS18clOX3MmsbQahKXKIH6TWPUQ7aqA0Rvd88Qeajyev+x" +
                                                    "X94XCRw+cELEZP/wgbvRg8NS4FSelXUwBnnEycxVniBMvAu/CDz/soeZxgYE" +
                                                    "Mle2uMdDvXc+WBYLlJljqcWXWP7z2aHP3h3aJ8yozDyUWqHmev/bf76KHvnx" +
                                                    "yxyICZlgYh5Ti7mii8Zw4jLWPH7vTixznVh2z06MiETh85xqxRjqtLHmmWx1" +
                                                    "hD7V9xIPy1lxFgD0v9ZpPXt/us33KQuSc4RIiL9LPnOspmXJb5zfx0bGXZfK" +
                                                    "PtWgkPV5F5zW/5AaCj+XUFEXqlDcKnkj1pIMgbqgMnTSpTNU0hnzmVWeKGma" +
                                                    "PeyfHo7RwLJhVPZjA/qMmvVLcwHxFHjyXf/mh/0bQbzzLHcxdZF8MfeqcFfs" +
                                                    "/oV1poXtCAgTrm7g7WzWPCK8zrpNLLhVA2vBeEAso2aMVo7ybDq+d3gkvu7E" +
                                                    "fMkNsyUUlVDTelQj24kWEMWKhBkZRcIaXoD7Lj1w6r3z9OrcxSIv54wehmHG" +
                                                    "S3t/relc0rflPkqDupBNYZGn1pz5csXDyusSyvMiI+tOkcnUnBkPpTaBS5DR" +
                                                    "mREVtZ4jq9nuPgRPlevIqtzH8xgOY82mVG5c95wuYKpvDFzYyho44vKgYA7E" +
                                                    "XBZidWfrPs3VfdqD0T0Lzuwx1Oa66vetdj08da7adQ9G7aBWO8eY28WaAQq3" +
                                                    "clNcLTnVZIoqeAHB0CgqJnKANBRDojZnFUd11jVeXD2VD0bKi6eObPiOV5ve" +
                                                    "9bAE7miJpKYF0SrQL7RsklC5iiUCuyz+ehk0C18QKMpnL67fS4JsP0XjAmQU" +
                                                    "Fbm9INEr4CggYt1XrRx2CxROoQzsscJINCsDE/hfHun8TYo/PbqVsyOr1r5w" +
                                                    "c9EJDgYFioYHB5mUYrjxixraw4CZo0pLyypc2XSn7MOS2WlsK2NNZSBMArrV" +
                                                    "5a4vW3WL8opw8JOpHz95cuQaL3D/A66zH+eLEgAA");
}
