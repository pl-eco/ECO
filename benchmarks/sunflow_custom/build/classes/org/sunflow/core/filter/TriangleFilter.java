package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class TriangleFilter implements Filter {
    private float s;
    private float inv;
    public TriangleFilter(float size) { super();
                                        s = size;
                                        inv = 1.0F / (s * 0.5F); }
    public float getSize() { return s; }
    public float get(float x, float y) { return (1.0F - Math.abs(x * inv)) *
                                           (1.0F -
                                              Math.
                                              abs(
                                                y *
                                                  inv)); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1159026718000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWxURRSevW23P5S2lL+C0EIpRED2igQNlqCwaaG4QEML" +
                                                    "xvWnTO/Obi+9e+/l3tl2KVaBxEB4IEYLgsE+GAiC/MVI0BgSXhQIvmiMxgd/" +
                                                    "4otG5YEHkYiKZ2b2/uzdbZUXN5nZuTPnzDlnzjnfnDlzE5XZFlpsGtrOlGbQ" +
                                                    "CMnSyHZteYTuNIkdWR9b3oktmySiGrbtbpjrUZov1N6++2pfnYTCcTQZ67pB" +
                                                    "MVUN3d5MbEMbIIkYqvVm2zSStimqi23HA1jOUFWTY6pNW2Nogo+VopaYo4IM" +
                                                    "KsiggsxVkFd7VMA0keiZdJRxYJ3aO9BLKBRDYVNh6lE0N38TE1s4ndumk1sA" +
                                                    "O1Sw761gFGfOWmiOa7uwucDgQ4vlkTdeqHuvBNXGUa2qdzF1FFCCgpA4qk6T" +
                                                    "dC+x7NWJBEnE0SSdkEQXsVSsqUNc7ziqt9WUjmnGIu4hscmMSSwu0zu5aoXZ" +
                                                    "ZmUUaliueUmVaAnnqyyp4RTYOs2zVVjYzubBwCoVFLOSWCEOS2m/qicoagpy" +
                                                    "uDa2PAUEwFqeJrTPcEWV6hgmUL3wnYb1lNxFLVVPAWmZkQEpFM0cc1N21iZW" +
                                                    "+nGK9FDUEKTrFEtAVckPgrFQNDVIxncCL80MeMnnn5sbVx7cpa/TJa5zgiga" +
                                                    "078CmBoDTJtJklhEV4hgrF4UO4ynXd4vIQTEUwPEgubSi7eefKjxyjVB80AR" +
                                                    "mk2924lCe5TjvTWfzYouXFHC1KgwDVtlzs+znId/Z26lNWtC5k1zd2SLEWfx" +
                                                    "yuZPntl9mvwioaoOFFYMLZOGOJqkGGlT1Yi1lujEwpQkOlAl0RNRvt6BymEc" +
                                                    "U3UiZjclkzahHahU41Nhg3/DESVhC3ZE5TBW9aThjE1M+/g4ayKEyqGhJdDK" +
                                                    "kPjxf4qIvMWGcJexgnVVN2QIXoItpU8mitHTC6fbl8ZWvy0rGZsaadnO6EnN" +
                                                    "GJRtS5ENK+V+K4ZFZNAEokjuhmzRUxpp558RFm7m/yUoyyyuGwyFwBmzglCg" +
                                                    "QRatM7QEsXqUkcyatlvnem5IbmrkzoqiBSAvkpMXYfIiQl4kXx4KhbiYKUyu" +
                                                    "8Dd4qx/yHhCxemHX8+u37W8ugUAzB0vhqBlpM9iaU6ZNMaIeOHRwCFQgQhve" +
                                                    "fnZf5M7JJ0SEymMjeVFudOXI4J6tLz8sISkfkplxMFXF2DsZkLqA2RJMxWL7" +
                                                    "1u776fb5w8OGl5R5GJ/DikJOluvNQTdYhkISgJ7e9ovm4Is9l4dbJFQKAAKg" +
                                                    "STEEOeBRY1BGXs63OvjJbCkDg5OGlcYaW3JAr4r2WcagN8Pjo4aPJ4FTJrAk" +
                                                    "mAGtIpcV/J+tTjZZP0XEE/NywAqOz+0fXjl68c3FKyQ/lNf6LscuQgUwTPKC" +
                                                    "pNsiBOa/OdL5+qGb+57lEQIU84oJaGF9FGACXAbH+sq1HV9/9+3xLyQvqijc" +
                                                    "l5leTVWysMcCTwqAiAZAxnzfskVPGwk1qeJejbDg/LN2/tKLvx6sE97UYMYJ" +
                                                    "hof+fQNvfsYatPvGC7838m1CCrvEPMs9MnEAk72dV1sW3sn0yO75fPbRq/gt" +
                                                    "wFjANVsdIhyqQm6+LBynkLHUNGDrQA785eH67/qP/XRWpE3wpggQk/0jB+5F" +
                                                    "Do5Ivut0XsGN5ucRVyoPhokieO7BLwTtb9ZY0LAJAan10Ryuz3GB3TSZe+aO" +
                                                    "pxYX0f7j+eGP3hneJ8yoz79N2qBYOvvlX59Gjnx/vQhsQfwZmHIdZa7jIt5H" +
                                                    "mFL8RBFfa2XdHLNgLctnGviXNP7Zt7MKxgdZf2zSevf+cIfrVAA6RdwR4I/L" +
                                                    "Z47NjK76hfN72c+4m7KFMA7Vnsf7yOn0b1Jz+GMJlcdRnZIrJbdiLcNyLA7l" +
                                                    "k+3Ul1Bu5q3nl0Li3m910W1WMB58YoO44/kBxoyajasCUFPtQE04BzXhINSE" +
                                                    "EB9EOUsz7+ez7kEn08tNSx3ArE6F7GAryzg6CV+uun9JHaxro6hE1Qd823HF" +
                                                    "W3zhIIRNpWh6wc0orkIW3LPHKul4YB/fOzKa2HRiqZSLwscoqqSGuUQjA0QL" +
                                                    "RN7svFtyAy9iPY8fOPXuJfrZ4sdFiiwaO0qDjFf3/jyze1Xftvu4G5sCNgW3" +
                                                    "PLXhzPW1C5TXJFTiBk5BXZ7P1JofLlUWgYeE3p0XNI2uK1lDDdAm5lw5sej9" +
                                                    "5DmseM7Hx1l7jnVPQ3ClCO0CCOaBUAgPfKIrX7EpzsD5/0+KScLRbsAt46S9" +
                                                    "46iYYB28OkpAxTHUg5SoyS/P2KXTUPAYFA8Y5dxobcX00S1f8YLDfWRUQqWf" +
                                                    "zGiaP51947BpkaTKNaoUyS1SRS2WGKJkpOy5xwZc4T5BD0FSF6SnqJT9+ckM" +
                                                    "iib4yMBDuZGfCCSUABEb2qaTpnX8rmWwFhGwlkW+FGPlhv8rr/ZgWcQf2k7E" +
                                                    "Z8RTu0c5P7p+465bj57g6VMGT/ShIf4wg3emKLvcrJk75m7OXuF1C+/WXKic" +
                                                    "76BBDevqc7VWQLem4iVJW9qkvIgY+mD6+ytPjn7La6J/ANdQPbsBEQAA");
}
