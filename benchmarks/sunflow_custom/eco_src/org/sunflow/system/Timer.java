package org.sunflow.system;
public class Timer {
    private long startTime;
    private long endTime;
    public Timer() { super();
                     startTime = (endTime = 0); }
    public void start() { startTime = (endTime = System.nanoTime()); }
    public void end() { endTime = System.nanoTime(); }
    public long nanos() { return endTime - startTime; }
    public double seconds() { return (endTime - startTime) * 1.0E-9; }
    public static String toString(long nanos) { Timer t = new Timer();
                                                t.endTime = nanos;
                                                return t.toString(); }
    public static String toString(double seconds) { Timer t = new Timer();
                                                    t.endTime = (long) (seconds *
                                                                          1.0E9);
                                                    return t.toString();
    }
    public String toString() { long millis = this.nanos() / (1000 *
                                                               1000);
                               if (millis < 10000) return String.
                                                     format(
                                                       "%dms",
                                                       millis);
                               long hours = millis / (60 * 60 * 1000);
                               millis -= hours * 60 * 60 * 1000;
                               long minutes = millis / (60 * 1000);
                               millis -= minutes * 60 * 1000;
                               long seconds = millis / 1000;
                               millis -= seconds * 1000;
                               return String.format("%d:%02d:%02d.%1d",
                                                    hours,
                                                    minutes,
                                                    seconds,
                                                    millis /
                                                      100); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1414698727000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAKUYbWwcxXV8d/bZZxd/JHGCE+KPmNDEyW2DmqqNkRLXdcIl" +
                                                   "G2Jsx4ADHOPdufPG\ne7vL7tz5bCIILUpSKC1pqdQKCBGKlA/ChwQ0bQVpEN" +
                                                   "BSokpQqVRCIm0Vqa3UUqmqRFO1P/pmZu/2\ndu8D4pw0c7M777153+/Nnv0E" +
                                                   "1Ts2WqU4cTpvESc+PD6KbYeowzp2nAl4lVTeqW8aPbnLMEOoTkYh\nTaWoVV" +
                                                   "YcScUUS5oqJb4xmLfRgGXq82ndpHGSp/H9+maX3k55cxnBO46d63j4RKQ7hO" +
                                                   "pl1IoNw6SY\naqYxopOMQ1GbvB/nsJSlmi7JmkMHZfQFYmQzw6bhUGxQ5370" +
                                                   "IArLqMFSGE2KeuXC4RIcLlnYxhmJ\nHy+N8mOBwhKbUKwZRB0qHgeYG/yYwL" +
                                                   "aLN1YODUQa2eYkiMM5AKl7ilILactEtcKnJr9y4PjpMGqd\nQq2aMc6IKSAJ" +
                                                   "hfOmUEuGZKaJ7QypKlGnULtBiDpObA3r2gI/dQp1OFrawDRrE2eMOKaeY4Ad" +
                                                   "TtYi\nNj+z8FJGLQqTyc4q1LSLOkppRFcLT/UpHadB7E5PbCHudvYeBIxpwJ" +
                                                   "idwgopoERmNQMs3h3EKMrY\nvwsAADWaIXTGLB4VMTC8QB3Cljo20tI4tTUj" +
                                                   "DaD1ZhZOoairKlGmawsrszhNkhStCMKNii2AauKK\nYCgULQuCcUpgpa6AlU" +
                                                   "rsM9D56ZFTT5/fxn07ohJFZ/zHAGl1AGmMpIhNDIUIxCvZ+JOJu7KrQggB\n" +
                                                   "8LIAsIAZuvHcXvmvv+gWMCsrwOyZ3k8UmlRuO9o99sAOE4UZG42W6WjM+D7J" +
                                                   "eTiMujuDeQuitrNI\nkW3GC5sXxn5518Ez5G8hFEugBsXUsxnwo3bFzFiaTu" +
                                                   "wdxCA2pkRNoCZiqMN8P4GisJbB5cXbPamU\nQ2gCRXT+qsHkz6CiFJBgKmqC" +
                                                   "tWakzMLawnSGr/MWQigKA7XACCPx4/8UDcQlJ2ukdHNOcmxFMu20\n9zzvUJ" +
                                                   "KRJrQMsePMaSyKdkgzZoZIWMGGZphSWoMwVcyNKsmx/89PKs8465irq2OpLh" +
                                                   "iyOnj7raau\nEjupnLz83oGRXd8+ItyBubArE5gCToi7J8TFCXF+Aqqr44SX" +
                                                   "spOEJUCPsxCRkLta1o3fs/O+I30g\nf96ai4ASGGgfcO8eP6KYw17YJniGU8" +
                                                   "B3Vjy373D8ysmtwnek6tm1Inbz+y9cPP6vlvUhFKqc+phY\nkHxjjMwoy5fF" +
                                                   "lNYfDJZK9P/x6O5XPrz48Re9sKGovyyayzFZNPYFDWCbClEhv3nkT1zfGr4D" +
                                                   "TR4N\noQiEOKQ1zj9kjNXBM3xROVjIcEyWqIyaU6adwTrbKqSlGJ2xzTnvDf" +
                                                   "eMNr5eAsZpZm7aBqPB9Vv+\nz3aXWWzuFJ7ErB2QgmfQK99q+NLvX29+h6ul" +
                                                   "kGxbS8rZOKEidNs9Z5mwCYH3H/9o9Ac//OTwPu4p\nrqtQqHHZaV1T8oCy1k" +
                                                   "OBmNUhbzBD9u81MqaqpTQ8rRPmcf9rvXHTa3//bpswjQ5vCpbd8NkEvPfX\n" +
                                                   "fx0dvHjvv1dzMnUKqxmeGB6YkGaJR3nItvE84yP/8G9v+PGv8DOQ0iCNONoC" +
                                                   "4ZkBcckQ16PE9b6e\nz/HA3iY29QHtDVVcv0KFTioHzqT7svf/+mec62ZcWu" +
                                                   "pLzbAbW4PC8mxaw7S7PBi9t2JnBuC+fOG2\nu9v0C/8FilNAUYHK6OyxIVnk" +
                                                   "fUZ0oeujH735Vud9H4RRaDuK6SZWt2Pu/6gJHI84M5Bn8tbWbdy3\n2uYaXW" +
                                                   "9DecSV0OUqIF/yFALm1lUP/+2svnuRk5zecEp+b88zXAFVA79CeQvQWTi/99" +
                                                   "iV39BLnI4X\ngQy7N1+eRKEn8nC/+mGuveHlZzMhFJ1CbYrbtU1iPcv8fAqa" +
                                                   "DKfQykFn59v3NwyiOg4WM8yqYPSX\nHBuMfS95w5pBs3VLINxZhUL9MCJuuE" +
                                                   "eC4V6H+GIrR+nn803F4IxatpbDrJODPoRim7J6AOZaUdpN\n21oGqnKOJ6jL" +
                                                   "h/reeHfvs4dFUq9hVR9WUnnoD3+cDX/vzWmBFzRdAPjo6hN/fuXy2FKRAET7" +
                                                   "tqas\ngyrFES0c102rxYKht9YJHPrtgd6zD45dcjnq8DciI9Cs/2X+LXLTLY" +
                                                   "//qUIljeimkRZ5lM03s2mb\ncPnNVUNjy9UbbZRNQ5R3Nswy7DEROPf2Gudy" +
                                                   "haz1BSbo5oZqzSTXy+E7/9lyCL99T8hNY7vANahp\nbdRJjuglpKKMkq8L2M" +
                                                   "3bZy+KHj39/Dn6wcAWoeH11X0liLh+y/GF7i0vPbaI2t8dkC1Iuj238vbw\n" +
                                                   "jPZuiHf4IijLbgZ+pEF/KMaAn6xtTPgCssdff5fDiLm2jVWsv55hKhcPXGNP" +
                                                   "YdO9FK69LGI5yIgl\nvGAn+GbO1FTPPZKf5ZZFx2APU345lsG4zpXjukXJYd" +
                                                   "TY43RmKQqDd7Ml8bjWr4Vrpv0Ol+uORXGd\nr7G3wKYsaN/AhskhEh7fuWvh" +
                                                   "eyWMTpfvzkXx/c0ae4+w6SHIJQ4EraE6Qb9pUE1o1EpscHCRsnCV\nb4TR5c" +
                                                   "rSVVGW8noUYut1wInDPzEEjVAnihbXOCfx/RrSPsmm71DUSE1xaedQyylq4x" +
                                                   "0PK87xkg0u\n8OPXIvAmGD2uwD1XJTCbnqgh7CGOfryGsM+x6amrE/bpa/HU" +
                                                   "VTDWusKuXZSnnq2x9yKbTl+dOGc+\nrzjQ6dTzSy/r+leUffwSH2wU+aMH7v" +
                                                   "5U/t1/+PWt+FGlWUaNqayulzZmJesGyyYpjYvQLNo0i/+d\ng56w/OrN/Jwv" +
                                                   "OHs/EaA/p6i5BJRFq1iVAr0BKROA2PK8VUE3ounM+8Rm0q7xVWD+jbFQJbPi" +
                                                   "K2NS\nufOFfT35xyae4KW3XtHxAs93MRlFxVW0WGl7q1Ir0HofvfzS5Osvfq" +
                                                   "3QSfCrytKSYPC52M1it4YZ\nobpXvv+NZCzKb2wLP13+6i0nj10K8Rvo/wF9" +
                                                   "xi2vGhYAAA==");
}
