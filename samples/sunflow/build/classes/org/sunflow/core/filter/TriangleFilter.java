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
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWxURRSevW23P5S2lL+CUKAUIj/u9SdotAaFTQvFhTa0" +
                                                    "YFyVdXp3dvfS+8e9s+1SrQqJgfBAjFYFg30wEAT5MUaixpDwokDwRWM0PgjG" +
                                                    "F4nIAw/+RFQ8M7N379272yovbjKzc2fOmXPOnHO+OXPiOqpybLTCMrWdac2k" +
                                                    "EZKjke3aqgjdaREnsiG2qhfbDklGNew4/TCXUNrea/z15suZJgmF42g6NgyT" +
                                                    "YqqahrOZOKY2RJIx1OjNdmpEdyhqim3HQ1jOUlWTY6pDO2Joio+VovaYq4IM" +
                                                    "KsiggsxVkNd4VMA0lRhZPco4sEGdHeh5FIqhsKUw9ShaVLyJhW2s57fp5RbA" +
                                                    "DjXseysYxZlzNlpYsF3YXGLwayvksTe2Nb1fgRrjqFE1+pg6CihBQUgc1etE" +
                                                    "HyC2syaZJMk4mmYQkuwjtoo1dYTrHUfNjpo2MM3apHBIbDJrEZvL9E6uXmG2" +
                                                    "2VmFmnbBvJRKtKT7VZXScBpsneXZKizsYvNgYJ0KitkprBCXpXJQNZIULQhy" +
                                                    "FGxsfwwIgLVaJzRjFkRVGhgmULPwnYaNtNxHbdVIA2mVmQUpFM2dcFN21hZW" +
                                                    "BnGaJChqCdL1iiWgquUHwVgomhkk4zuBl+YGvOTzz/VND+9/1lhvSFznJFE0" +
                                                    "pn8NMLUGmDaTFLGJoRDBWL889jqedXavhBAQzwwQC5oPn7vx6MrWcxcEzR1l" +
                                                    "aHoGthOFJpTDAw1fzIsue7CCqVFjmY7KnF9kOQ//3vxKR86CzJtV2JEtRtzF" +
                                                    "c5s/e+LF4+SahOq6UVgxtawOcTRNMXVL1Yi9jhjExpQku1EtMZJRvt6NqmEc" +
                                                    "Uw0iZntSKYfQblSp8amwyb/hiFKwBTuiahirRsp0xxamGT7OWQihamjoLmhV" +
                                                    "SPz4P0W6nDF1ImMFG6phyhC7BNtKRiaKmbCJZcqd0R55AE45o2N70JGdrJHS" +
                                                    "zOGEknWoqcuOrcimnXanZcW0iQwaQTTJ/ZA1RlojXfwzwsLO+r8F5tgJNA2H" +
                                                    "QuCceUFo0CCr1ptaktgJZSy7tvPGqcQlqZAq+bOjaCnIi+TlRZi8iJAXKZaH" +
                                                    "QiEuZgaTK/wP3hsEHACErF/W9/SGZ/a2VUDgWcOVcPSMtA2MzivTqZhRDyy6" +
                                                    "OSQqELEtbz+5J/L70UdExMoTI3tZbnTuwPCurS/cLSGpGKKZcTBVx9h7GbAW" +
                                                    "ALQ9mJrl9m3cc/XX06+Pml6SFmF+HjtKOVnutwXdYJsKSQKaetsvX4jPJM6O" +
                                                    "tkuoEgAFQJRiCHrAp9agjCIM6HDxlNlSBQanTFvHGltyQbCOZmxz2Jvh8dHA" +
                                                    "x9PAKVNYUsyBVpPPEv7PVqdbrJ8h4ol5OWAFx+uuj88dPPPmigclP7Q3+i7L" +
                                                    "PkIFUEzzgqTfJgTmvzvQ++pr1/c8ySMEKBaXE9DO+ijABrgMjvWlCzu+vXL5" +
                                                    "8FeSF1UU7s/sgKYqOdhjqScFQEUDYGO+b99i6GZSTal4QCMsOP9sXHLPmZ/3" +
                                                    "NwlvajDjBsPKf9/Am5+zFr14adtvrXybkMIuNc9yj0wcwHRv5zW2jXcyPXK7" +
                                                    "vpx/8Dx+CzAXcM5RRwiHrlAhX5ZNUtjYqg5YO5S/DOTR5iuDh66eFGkTvDkC" +
                                                    "xGTv2L5bkf1jku96XVxyw/l5xBXLg2GqCJ5b8AtB+5s1FjRsQkBsczSP8wsL" +
                                                    "QG9ZzD2LJlOLi+j68fToJ++M7hFmNBffLp1QPJ38+q/PIwe+v1gGtiD+TEy5" +
                                                    "jjLXcTnvI0wpfqKIr3WwbqFVspbjMy38S5r87LtYReODrD96tIHdP/zOdSoB" +
                                                    "nTLuCPDH5ROH5kZXX+P8XvYz7gW5UhiH6s/jvfe4/ovUFv5UQtVx1KTkS8ut" +
                                                    "WMuyHItDOeW49SaUn0XrxaWRqAM6Cug2LxgPPrFB3PH8AGNGzcZ1Aaipd6Em" +
                                                    "nIeacBBqQogPopyljfdLWHenm+nVlq0OYVa3Qnawlfs4Oglfrr59Sd2s66So" +
                                                    "QjWGfNtxxdt94SCEzaRodsnNKK5CFtzzJyrxeGAf3j02nuw5co+Uj8IHKKql" +
                                                    "pnWXRoaIFoi8+UW35EZe1Hoe33fs3Q/pFyseEimyfOIoDTKe3/3T3P7VmWdu" +
                                                    "425cELApuOWxjScurluqvCKhikLglNTpxUwdxeFSZxN4WBj9RUHTWnAla6gF" +
                                                    "2tS8K6eWvZ88h5XP+fgka0+x7nEIrjShfQDBPBBK4YFP9BUrNsMduP//STFJ" +
                                                    "OLoQcPdx0oFJVEyyDl4hFaDiBOpBSjQUl2fs0mkpeRyKB41yaryxZvb4lm94" +
                                                    "wVF4dNRC5Z/Kapo/nX3jsGWTlMo1qhXJLVJFLZcYomSk7PnHBlzhjKCHIGkK" +
                                                    "0lNUyf78ZCZFU3xk4KH8yE8EEiqAiA0dy03TJn7XMliLCFjLIV+KsXLD/1VU" +
                                                    "e7As4g9vN+Kz4umdUE6Pb9j07I37j/D0qYIn+8gIf6jBu1OUXYWsWTThbu5e" +
                                                    "4fXLbja8V7vERYMG1jXna62AbgvKlySdukV5ETHy0ewPHj46fpnXRP8A96L8" +
                                                    "XBERAAA=");
}
