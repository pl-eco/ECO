package org.sunflow.core.filter;
import org.sunflow.core.Filter;
final public class FilterFactory {
    final public static Filter get(String filter) { if (filter.equals("box"))
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
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1169098762000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAJ1Xe2wURRif3qOlD9IH0JZnoRTkeUsTMUpJoNYWCguUthQo" +
       "YJnuzl0X9naW3dn2\nWoiBmAhCNBI10UQJMSQFFDFRgyZEMYgv/gETNSHBxP" +
       "iHJoqJMUGM/uE3M/fcu0JCk87uznyP+V6/\n77t37qCw66BZmhthozZxI209" +
       "Xdhxid5mYtftha0B7Vq4tGt8o0UDqEhFAUNnqFLVXEXHDCuGrnQ+\n1ZJw0F" +
       "KbmqMxk7IISbDIPnNlUt4GdWWewO2nLtUcORNqCKCwiiqxZVGGmUGtdpPEXY" +
       "aq1H14GCse\nM0xFNVzWoqLJxPLibdRyGbaYewA9g4IqKrY1LpOheWpKuQLK" +
       "FRs7OK4I9UqXUAsSpjiEYcMiemta\nHXAuy+WEayf5uvOpQcgkftgH5ogbgN" +
       "Vz01ZLa/NMtYNn+x47dPpcEFX2o0rD6uHCNLCEgb5+VBEn\n8UHiuK26TvR+" +
       "VG0RovcQx8CmMSa09qMa14hZmHkOcbuJS81hTljjejZxhM7UpooqNG6T42mM" +
       "Omkf\nRQ1i6qmvcNTEMTC7NmO2NLeD74OBZQZczIlijaRYQvsNCyLe4OdI29" +
       "i0EQiAtSRO2BBNqwpZGDZQ\njYylia2Y0sMcw4oBaZh6oIWhGRMK5b62sbYf" +
       "x8gAQ/V+ui55BFSlwhGchaFpfjIhCaI0wxelrPgs\nrb177Owbn6wVuR3SiW" +
       "by+5cB0xwfUzeJEodYGpGM97zIK507vVkBhIB4mo9Y0rQuuLRN/fXTBkkz\n" +
       "swDNlsF9RGMD2uaTDd0H11EU5NeYZFPX4MHPsVyUQ1fypCVhQ9XWpiXyw0jq" +
       "8Er3FzsPnye/BVBZ\nJyrWqOnFIY+qNRq3DZM464hFHMyI3olKiaW3ifNOVA" +
       "LvKqS83N0SjbqEdaKQKbaKqfgGF0VBBHdR\nKbwbVpSm3m3MhsR7wkYITYZ/" +
       "VAP/YST/xJOhxyOK61lRk44orqMp1ImlvzXqEAWkQ2YoHeLRgXkm\nj0Z4Bt" +
       "kM9SpDNE4UrGHLsKgSM6BmNbpcJ8P8+ZByE/zONSNFRRwE/cVsQh2sp6ZOnA" +
       "Ft/OdvDrVv\nfP6YTBSe3ElrGVoA6iJJdRGuLiLVRXLUoaIioWUqVysDBu7e" +
       "D4ULEFexuGfPhr3HGoOQKfZICHzF\nSRvBruRd2jXalqnuTgGEGqRY/Vu7jk" +
       "buja+RKaZMDMIFuctvXLh++q+KJQEUKIyQ3EbA6DIupovD\nahr5mvw1VUj+" +
       "H8c3vf/99duLMtXFUFNe0edz8qJt9EfDoRrRAQYz4s9MrwxuR30nAygESADo" +
       "J+4P\nwDLHryOneFtSQMhtKVFReZQ6cWzyoxR6lbEhh45kdkSaVPFlqswYHk" +
       "jfBQWG3nu2eMUPl8uvCYtT\ncFuZ1dB6CJPFW53Jg16HENi//VrXy6/eObpL" +
       "JEEyCxh0OW/QNLQEsCzMsEDVmoAcPEZN26w41Y2o\ngQdNwpPpv8oFzR/+/m" +
       "KV9LoJO6mgLXuwgMz+9CfR4etP/z1HiCnSeNfImJEhk9ZMyUhudRw8yu+R\n" +
       "OPLt7Ne/xG8CqAGQuMYYEdiAhGVI+DEi3LtYrMt9Zyv40giyl02Q1QV69IB2" +
       "6Hys0Tvw9cfi1uU4\nu9lnh2ETtltkUIXuKaB0HkouOZjFT6fZfK3lIajzV+" +
       "967A6BsEevbN5dZV75F9T2g1oNGqi7xQHk\nSOREOkkdLrn12dXavTeDKNCB" +
       "ykyKdQkSgL2QeMQdAtBJ2GvWimtUjUziq/ALEredkfRSIudLfMwX\n60KZPQ" +
       "EGKWhYWPS0RT5qB82eqC2Kln50x58Vz+HP90hkqcltNe0wjv0yepU8svqFnw" +
       "ogYimj9nKT\nDBMzSydHtNk5iLZJTAyZej5+7u1L7ObSVVLlkonBzM+4ZNXp" +
       "sYZVF088BI41+JzgF109PHNrcMj4\nKiCGGglhecNQLlNLtjtAKdzHcyzuWL" +
       "5TIdJubjrt6nk4muG/OJl2xf60k4CTF90gf18tQgso4YrB\nMuGrp6Kk5/l3" +
       "HUzWIhP5MBaRw5iQvek+RbiVL+sYCsYIS0mpy+t3stGJ82a+rJf3WPngDGZo" +
       "ck6X\n5FhSnzdUy0FQU28d3H1X/e4fgffpYa0cJqaoZ5pZLs92f7HtkKghjC" +
       "mXFW+LR38hQ2TjZnxsTlu0\nU9LvAff56RkK8Uc22V6GyrPIGCpJvmUTAZgG" +
       "gYi/6naB4MixMLdguWfm55SE+J2TSltP/tIZ0HZc\n2DU3caL3JVELYfiFND" +
       "YmRlqY0GWfS6f+vAmlpWTdQO9d7Lv87hOBZDbkdMA87GmWp/cJPZRb4Q7U\n" +
       "HreZ6BljH9V9sHr81I8B0QP/BzknJmeeDgAA");
}
