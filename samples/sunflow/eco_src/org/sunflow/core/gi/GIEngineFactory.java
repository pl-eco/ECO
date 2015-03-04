package org.sunflow.core.gi;
import org.sunflow.core.GIEngine;
import org.sunflow.core.Options;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
final public class GIEngineFactory {
    final public static GIEngine create(Options options) { String type = options.
                                                             getString(
                                                               "gi.engine",
                                                               null);
                                                           if (type ==
                                                                 null ||
                                                                 type.
                                                                 equals(
                                                                   "null") ||
                                                                 type.
                                                                 equals(
                                                                   "none"))
                                                               return null;
                                                           else
                                                               if (type.
                                                                     equals(
                                                                       "ambocc"))
                                                                   return new AmbientOcclusionGIEngine(
                                                                     options);
                                                               else
                                                                   if (type.
                                                                         equals(
                                                                           "fake"))
                                                                       return new FakeGIEngine(
                                                                         options);
                                                                   else
                                                                       if (type.
                                                                             equals(
                                                                               "igi"))
                                                                           return new InstantGI(
                                                                             options);
                                                                       else
                                                                           if (type.
                                                                                 equals(
                                                                                   "irr-cache"))
                                                                               return new IrradianceCacheGIEngine(
                                                                                 options);
                                                                           else
                                                                               if (type.
                                                                                     equals(
                                                                                       "path"))
                                                                                   return new PathTracingGIEngine(
                                                                                     options);
                                                                               else {
                                                                                   UI.
                                                                                     printWarning(
                                                                                       Module.
                                                                                         LIGHT,
                                                                                       "Unrecognized GI engine type \"%s\" - ignoring",
                                                                                       type);
                                                                                   return null;
                                                                               }
    }
    public GIEngineFactory() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1166659882000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAJ1Xa2wURRyf3l1b+iB9QSnPQluQ5y0kQpSSANZSDra0tFCg" +
       "gGW6O3dd2NtdZufa\nayEGYiII0UjURBMlxJAUUMREDZoQxSC++AImakKCif" +
       "GDJoqJMUGMfvA/M7f32LtiwiW3j5n/+/H7\nz759FxW7FM3S3DAbdYgbbuvt" +
       "xtQlepuJXXcbLA1o14vLusc3W3YAFakoYOgMVamaq+iYYcXQlciT\nrUmKlj" +
       "i2ORozbRYmSRbeb65MydukrswTuOP05dqjZ0ONAVSsoipsWTbDzLCtdpPEXY" +
       "aq1f14GCsJ\nZpiKarisVUWTiZWIt9mWy7DF3IPoaRRUUYmjcZkMzVM95Qoo" +
       "VxxMcVwR6pVuoRYk1FHCsGERfX1a\nHXAuzeUEs1N8PfnUIGQS3+wDd4QF4P" +
       "XctNfS2zxXneC5vlWHz5wPoqp+VGVYvVyYBp4w0NePKuMk\nPkiou17Xid6P" +
       "aixC9F5CDWwaY0JrP6p1jZiFWYISt4e4tjnMCWvdhEOo0OktqqhS4z7RhMZs" +
       "mo5R\n1CCm7r0VR00cA7frM25LdzfwdXCw3ADDaBRrxGMJHTAsyHijnyPtY8" +
       "tmIADW0jhhQ3ZaVcjCsIBq\nZS5NbMWUXkYNKwakxXYCtDA0Y0KhPNYO1g7g" +
       "GBlgqMFP1y23gKpMBIKzMDTVTyYkQZZm+LKUlZ8l\n9feOn3v943WitkM60U" +
       "xufzkwzfEx9ZAoocTSiGS8nwi/HNmVmBVACIin+oglzfr5l7erv3zSKGlm\n" +
       "FqDpGtxPNDagbTnV2HOow0ZBbsYkx3YNnvwcz0U7dKd2WpMOdG19WiLfDHub" +
       "V3s+33XkAvk1gMoj\nqESzzUQc6qhGs+OOYRLaQSxCMSN6BJURS28T+xFUCs" +
       "8qlLxc7YpGXcIiKGSKpRJbvEOIoiCCh6gM\nng0ranvPDmZD4jnpIIQmwx/V" +
       "wn8Skj9xZ2hVWHETVtS0RxSXaopNY+l3zaZEiRlKR6TdioHSDZjX\n8WiY14" +
       "/DUI8yZMeJgjVsGZYNhNCxmr1MJ8P8/lBSk9ze2pGiIg6A/kY2oQc22qZO6I" +
       "A2/tPXh9s3\nP3dcFgkv7JSnDDWDsnBKWZgrC8eMsE8ZKioSOqZwpTJVEOgD" +
       "0LIAbpWLevdu2ne8KQg14oyEIEqc\ntAl8SlnSrtltmb6OCAjUoLga3tx9LH" +
       "x/fK0sLmVi+C3IXXHz4o0zf1YuDqBAYWzkHgI6l3Mx3RxQ\n05jX4u+mQvJ/" +
       "P9H53nc37izM9BVDLXntns/J27XJnwtqa0QHAMyIPzu9KrgD9Z0KoBBgAOCe" +
       "sB8g\nZY5fR07btnoQyH0pVVFF1KZxbPItD7fK2RC1RzIrokiq+WWKrBeeSJ" +
       "+BAj3vP1Oy/PsrFdeFxx7Q\nVmWNsl7CZNvWZOpgGyUE1u+82v3SK3eP7RZF" +
       "kKoCBvMtMWgaWhJYFmRYoF9NwAyeo5btVtzWjaiB\nB03Ci+nfqvkrPvjthW" +
       "oZdRNWvKQt/X8BmfXpT6AjN576a44QU6TxeZFxI0MmvanLSF5PKR7ldiSP\n" +
       "fjP7tS/wGwBnACGuMUYEKiDhGRJxDIvwLhLXZb695fzSBLKXTlDVBabzgHb4" +
       "QqwpcfCrj4TVFTh7\nzGenoRM7rTKpQncdKG1GqUsOWvHdqQ6/1vMUTPN370" +
       "bsDoGwR69u2VNtXv0H1PaDWg1Gp9tFATeS\nOZlOUReX3v70Wv2+W0EU2IDK" +
       "TRvrEiQAdaHwiDsEkJN01q4TZlSPcEOqRVyQsHZGKkrJnDfx0iyu\nC2T1BB" +
       "iUoGFhMc0W+qgpmj3RQBTD/NjOPyqfxZ/tlchSmztk2uEg9vPoNfLImud/LI" +
       "CHZcx2lplk\nmJhZOjmizc5BtE5xVsj084nzb11mt5aslioXTwxmfsbFq8+M" +
       "Na6+dPIhcKzRFwS/6JrhmVuDQ8aX\nAXGckRCWdwzKZWrNDgcoBXsS1OKB5S" +
       "uVouzmpsuugafjMfiXpcquzF92EnDyshvkz2tEagElXHGk\nTPr6qSgVef4+" +
       "DQ4SeXOqyxHxETo6H9CMW/mlA/RolMCpwRM4PU+gN/UExQp+2SiNWvn/5Qwt" +
       "6hua\nHFoa8k7X8kSoqbcP7bmnfvu3gP/0qa0Cjk7RhGlmZSA7GyUOJVFD+F" +
       "QhAcARt36G6gpMcYYCMUNY\nuUvS7YUvEz8dQyF+yybbx1BFFhlDpamnbCLA" +
       "1CAQ8Ufd8UJaLdCCH5XD8lyY27c8Is05nSE+dLzq\nTchPnQFt58Xdc5Mnt7" +
       "0oWqIYPpHGxsSZFo7octylO2DehNI8WTfRu5f6rrzzeCBVDDmDMA+CVsjd\n" +
       "ByQduq7wIGqPO0yMjrEPp72/Zvz0DwExCv8DBkvX558OAAA=");
}
