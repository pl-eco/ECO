package org.sunflow.image;
public class RegularSpectralCurve extends SpectralCurve {
    final private float[] spectrum;
    final private float lambdaMin;
    final private float lambdaMax;
    final private float delta;
    final private float invDelta;
    public RegularSpectralCurve(float[] spectrum, float lambdaMin, float lambdaMax) {
        super();
        this.
          lambdaMin =
          lambdaMin;
        this.
          lambdaMax =
          lambdaMax;
        this.
          spectrum =
          spectrum;
        delta =
          (lambdaMax -
             lambdaMin) /
            (spectrum.
               length -
               1);
        invDelta =
          1 /
            delta;
    }
    public float sample(float lambda) { if (lambda < lambdaMin ||
                                              lambda >
                                              lambdaMax) return 0;
                                        float x = (lambda - lambdaMin) *
                                          invDelta;
                                        int b0 = (int) x;
                                        int b1 = Math.min(b0 + 1,
                                                          spectrum.
                                                            length -
                                                            1);
                                        float dx = x - b0;
                                        return (1 - dx) * spectrum[b0] +
                                          dx *
                                          spectrum[b1]; }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1166422616000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAALVYe2wUxxkf353fTvzgGUIwGAMBzB0PQ20cNXH9AMMBjo0h" +
                                                   "GIgz3p07L+ztLruz\nx+EgkigSkKA8UBOpaRsCFRLghIKUpjQSoaAkbRpUKU" +
                                                   "FqIkUKbYXUVmpTNaqUUrV/9JuZ3du9vTuj\nRuKkm53d+eb75nv9vm/3ra9Q" +
                                                   "qWWi2ZIVpQcMYkW7BvuxaRG5S8WWtRUejUgfllb2n9mo6SFUEkch\nRaaoNi" +
                                                   "5ZMRlTHFPkWF93R8ZESw1dPZBUdRolGRrdo652+G2Ir85juP3EpYZnTkcaQ6" +
                                                   "g0jmqxpukU\nU0XXelSSsiiqi+/BaRyzqaLG4opFO+LoHqLZqS5dsyjWqLUP" +
                                                   "HULhOCozJMaTonlxV3gMhMcMbOJU\njIuP9XOxwGGKSShWNCJ3ZsXBzpbcnX" +
                                                   "BsZ99APjUwqWCL20AdfgLQem5Wa6FtnqpG+Oy2NQdPnguj\n2mFUq2iDjJkE" +
                                                   "mlCQN4xqUiQ1SkyrU5aJPIzqNULkQWIqWFXGudRh1GApSQ1T2yTWALF0Nc0I" +
                                                   "Gyzb\nICaX6T6MoxqJ6WTaEtXNrI0SClFl9640oeIkqD3dU1uo28ueg4JVCh" +
                                                   "zMTGCJuFsiexUNPN4Y3JHV\nsXkjEMDW8hShY3pWVETD8AA1CF+qWEvGBqmp" +
                                                   "aEkgLdVtkELRrKJMma0NLO3FSTJC0cwgXb9YAqpK\nbgi2haJpQTLOCbw0K+" +
                                                   "Aln3+WTv/m6NkfX3mEx3ZEJpLKzl8Fm+YENg2QBDGJJhGx8bYdfaVvhz07\n" +
                                                   "hBAQTwsQC5rOBZeG4n/5ZaOgub8AzZbRPUSiI9Lm440DT67TUZgdo8LQLYU5" +
                                                   "P0dzng79zkpHxoCs\nnZ7lyBaj7uLVgV/teHqC/DWEqvpQmaSrdgriqF7SU4" +
                                                   "aiEnMd0YiJKZH7UCXR5C6+3ofKYR6HkBdP\ntyQSFqF9KKLyR2U6vwcTJYAF" +
                                                   "M1ElzBUtobtzA9MxPs8YCKFy+KMa+IeR+PErRe3RmGVrCVXfH7NM\nKaabye" +
                                                   "y9kgKPQt4lbRWbgwaYxcRql22mSZSFkEHRUGxMT5EYlrCmaHosqUDSSvoyma" +
                                                   "TZ9dsyzrBT\nN+wvKWEwGExnFTJhva7KxByRztz6+GDPxueOilBh4e3oS9FC" +
                                                   "kBd15EW5vGgheaikhIuZyuQKn4HF\n90LuAsrVLB7cveGJo01gqYyxPwLmYq" +
                                                   "RNoJlzmB5J7/ISvI9joQRRNvMnO49Eb595WERZrDgOF9xd\n/cn56yf/WbMk" +
                                                   "hEKFQZIpCTBdxdj0M2TNgl9zMK0K8f/785ve/uz6lw96CUZRc17e5+9kedsU" +
                                                   "dIep\nS0QGJPTYn76vNrwdbTseQhEAAwBAfn7AljlBGTn52+FiIdOlPI6qE7" +
                                                   "qZwipbcgGsio6Z+n7vCY+T\nOj6fAs6pZgHdCP8KJ8L5la1OM9g4XcQV83ZA" +
                                                   "C461t58tW/755eoPuVlcWK71Fb5BQkWS13vBstUk\nBJ5/+YP+77/61ZGdPF" +
                                                   "KcUKFQDe1RVZEysGWhtwWyW4UIZI5sHtJSuqwkFDyqEhZx/61dsOKdv71Y\n" +
                                                   "J1yjwhPXsy13ZuA9v+976Onrj/9rDmdTIrHq4qnhkQltpnicO00TH2DnyDxz" +
                                                   "44HXfo1fB/ADwLGU\nccIxJMw1CzML+roTvot55dzL3VMG2nc+ywO3Egomtj" +
                                                   "a7OcnbFDYrAWUWFM+ILLMRadHuS/+4doUs\nAi2GUYViQZXvNJMFiodvz9d4" +
                                                   "gmz6PHGCB19kFFtcek2w6uYX1ZxayQPlXoM5bqa/DTOVFMB5msfr\nrcNN73" +
                                                   "009MYRkeOLJ+m1/LtGpKd+/4e94ZeujYp9wZIWID4+5/Sf3r41MFXEg6j78/" +
                                                   "NKr3+PqP1c\ngVquwLzJJHDqD5bOe+vQwE3nRA25FawHurw/H3ifLHrohT8W" +
                                                   "gFnIEx2L9i/Kgj6LkpMYpJd5wsOL\nkdGWs/GPt7zOuReFuwK2CvAZvzJ04v" +
                                                   "Zv6U3Ox8MdtnteJr+QQDR5e9s+S9eXXXwjFULlw6hOcrra\nbVi1WXYPQ7xY" +
                                                   "bqsLnW/Oem5DJbqHjiyuzg46yyc2iHieZWHOqHnoCpAzMiWIg1gXp27m4yKB" +
                                                   "NCHm\nBkXDvE9anAHcUYmWpGOcphWCgF3bKQpD0LPpKucRc1hIcOD3M6iDBU" +
                                                   "wT6Mh0jTBYcddEiVT0aLYb\nhsVMAdc/kFMgN/G88kz9/Lk3L9FPl64V0bak" +
                                                   "eJgENy5Ze3K8ce2FY9+iLDYGfB9kXZ++/9HwmPJR\niLfJwnN57XXupo5cf1" +
                                                   "XBeWxT25rjtbkGv6xiw0J+mCVZcyFuLsQJtk+ytoMNQ+BhiflDuA9s3Fi4\n" +
                                                   "HPSkDMoBfPwXM3720JkTN5mVjQy0edUcjlesWPmd1uWwvwFgjb0tRhU5Gtcl" +
                                                   "rPZ1n7pWfeO4vWaD\ncMw9PoK+7oMXN9RUnDp2mBvIAfJKX1vs3JensbnZgw" +
                                                   "d22U3R7rvSJK5ta1m1cllrG0UVFl+0U0xe\nGx+46WKTmJVr0ASGaCkSgAXe" +
                                                   "NkekgxPJJnvfb97lEFON/a+t/kZhEzYEAtexYT5D4RnB/nI9tsaA\nrvXq5l" +
                                                   "116tX/8BpXjSUoSNYWE5rbTE6b4VCXln9x7f3pT3waRqFeVAXIK/di3qGhSm" +
                                                   "iNiDUGfXHG\nePgR3v3U7Wf9Tx1XGXEjzHIMkPHdlfL5d7NNVAOjmA//iNNE" +
                                                   "RYJNlANEqQJAxOa9FJUbppLG7HXa\n8w2nWy4Soi0PNPgZshhnF2MtAI6N69" +
                                                   "iwng1WPpqx2zgbNufDE7t/VBzDulNeHpxk7RAbxtkwLE7B\nxl25mbaqPZBp" +
                                                   "omJ13+gdnUhoEzLPpiremHSyPU4WVfInvs4prBsWe/H0feFxODVvMSwRaiOu" +
                                                   "4JVr\n2lcsh6x4/O5k3crVLa1rlq1upeierev7BqP+3HvK5+9D8C6crzk7rx" +
                                                   "NXLNIgyO/1Kg5rNfyL0F5E\nBno6u0XXzkZuEE04daxomAeCeRmLcodtaZFg" +
                                                   "fmGyiNsHcVyp4tSojDcpWhZkfEd68S4c6RU2vORJ\nFm/FQcmv/p+SH4R/mS" +
                                                   "O5rIjk1+5kjFKZqBQXOs4P78JxTrLhRxBaipbuLib41CSCM16euwjIupRi\n" +
                                                   "X6B4T3zksa9rDuMPdoecdF8LjqC6sUwlaaL6WDmAkvsOusCV7V79WvGDF0Ie" +
                                                   "B6G8+nV+Evi5wIYJ\naPUsnDLE5582zxpv3skN2UoAbV2hXGctxsy8D6ni45" +
                                                   "8U/+LJXd/Ef/dv8Y7lfqCrhnYgYauqv4n1\nzcsMkyQUfvZqt6Vll59TVJ/3" +
                                                   "qQZCjF/5Wd8RhO8CwPkIoco4Mz/RZehygYhN3zPcxrUx/1NQjrKZ\nHJMw1e" +
                                                   "fnNAX847XbOdri8/WI9Nj5nXMzx7a+zNtR6M7wOK8GVdAFiS8X2e5zXlFuLq" +
                                                   "9P0MUL2y7/\ntN2NNt43TM14MZ6N26yLp07iYl6KjP8BrOp88UgYAAA=");
}
