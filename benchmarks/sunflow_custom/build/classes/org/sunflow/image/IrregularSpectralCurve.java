package org.sunflow.image;
public class IrregularSpectralCurve extends SpectralCurve {
    private final float[] wavelengths;
    private final float[] amplitudes;
    public IrregularSpectralCurve(float[] wavelengths, float[] amplitudes) {
        super(
          );
        this.
          wavelengths =
          wavelengths;
        this.
          amplitudes =
          amplitudes;
        if (wavelengths.
              length !=
              amplitudes.
                length)
            throw new RuntimeException(
              String.
                format(
                  ("Error creating irregular spectral curve: %d wavelengths and " +
                   "%d amplitudes"),
                  wavelengths.
                    length,
                  amplitudes.
                    length));
        for (int i =
               1;
             i <
               wavelengths.
                 length;
             i++)
            if (wavelengths[i -
                              1] >=
                  wavelengths[i])
                throw new RuntimeException(
                  String.
                    format(
                      ("Error creating irregular spectral curve: values are not sort" +
                       "ed - error at index %d"),
                      i));
    }
    public float sample(float lambda) { if (wavelengths.length ==
                                              0) return 0;
                                        if (wavelengths.length ==
                                              1 ||
                                              lambda <=
                                              wavelengths[0])
                                            return amplitudes[0];
                                        if (lambda >= wavelengths[wavelengths.
                                                                    length -
                                                                    1])
                                            return amplitudes[wavelengths.
                                                                length -
                                                                1];
                                        for (int i = 1; i <
                                                          wavelengths.
                                                            length;
                                             i++) { if (lambda <
                                                          wavelengths[i]) {
                                                        float dx =
                                                          (lambda -
                                                             wavelengths[i -
                                                                           1]) /
                                                          (wavelengths[i] -
                                                             wavelengths[i -
                                                                           1]);
                                                        return (1 -
                                                                  dx) *
                                                          amplitudes[i -
                                                                       1] +
                                                          dx *
                                                          amplitudes[i];
                                                    } }
                                        return amplitudes[wavelengths.
                                                            length -
                                                            1];
    }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1170479716000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YW2xcxRmePXZ8i+8hiUkTO/ElwgndU4IICkZpw8omDgtx" +
                                                    "4yQVC40Zn51dn/jcMmeOvXFqIJGqRDxEVWtoqKgfUCiFhgRVjWiFkPJCAYGQ" +
                                                    "QKiIh5KWFxAhEnmAIu7/zJzbnl276kstndk5M/8///375/jcVbTCpWirYxtH" +
                                                    "i4bN0qTE0oeNW9LsqEPc9J7sLWOYuiSfMbDr7oe1Ca33+bbPv/rVVLuC6nJo" +
                                                    "FbYsm2Gm25a7j7i2MUPyWdQWrQ4bxHQZas8exjNY9ZhuqFndZUNZtDLGylB/" +
                                                    "NlBBBRVUUEEVKqi7IipgaiGWZ2Y4B7aYewQ9iFJZVOdoXD2GNpUf4mCKTf+Y" +
                                                    "MWEBnNDA3w+CUYK5RNHG0HZpc4XBj25VF357qP3PNagth9p0a5yro4ESDITk" +
                                                    "ULNJzElC3V35PMnnUIdFSH6cUB0b+pzQO4c6Xb1oYeZREjqJL3oOoUJm5Llm" +
                                                    "jdtGPY3ZNDSvoBMjH7ytKBi4CLauiWyVFo7wdTCwSQfFaAFrJGCpndatPEM9" +
                                                    "SY7Qxv67gABY603CpuxQVK2FYQF1ytgZ2Cqq44zqVhFIV9geSGFo3ZKHcl87" +
                                                    "WJvGRTLBUFeSbkxuAVWjcARnYWh1kkycBFFal4hSLD5X77n99DFrt6UInfNE" +
                                                    "M7j+DcDUnWDaRwqEEksjkrF5S/YxvOalUwpCQLw6QSxpXvjFtZ/c2H3pVUnz" +
                                                    "gyo0eycPE41NaGcnW99anxncUcPVaHBsV+fBL7NcpP+YvzNUcqDy1oQn8s10" +
                                                    "sHlp39/vffhZckVBTaOoTrMNz4Q86tBs09ENQu8kFqGYkfwoaiRWPiP2R1E9" +
                                                    "zLO6ReTq3kLBJWwU1Rpiqc4W7+CiAhzBXVQPc90q2MHcwWxKzEsOQqgeHtTs" +
                                                    "P8EcMVRUD7iQ7irWsKVbtgrJSzDVplSi2ROT4N0pE9NpV9U8l9mm6npWwbBn" +
                                                    "VZdqqk2L4btuQvTVUUpJ0TMwHXfAiRQbGY/OkDRPOOf/J6rErW6fTaUgIOuT" +
                                                    "cGBAJe22jTyhE9qCd8fwtfMTrythefj+YugGkJj2JaaFxHR1iSiVEoKu45Jl" +
                                                    "1CFm01D9gIvNg+M/3/PAqd4aSDdnthYczkl7wV5fnWHNzkQQMSqAUIM87Xry" +
                                                    "vpPpL57+scxTdWk8r8qNLp2ZPX7woR8pSCkHZm4eLDVx9jEOpyFs9icLstq5" +
                                                    "bSc/+vzCY/N2VJplSO8jRiUnr/jeZCCorZE8YGh0/JaN+OLES/P9CqoFGAHo" +
                                                    "ZBhSHVCpOymjrPKHAhTltqwAgws2NbHBtwLoa2JT1J6NVkSGtIp5BwRlJS+F" +
                                                    "TfB0+bUhfvnuKoeP18mM4lFOWCFQeuRvlx6/+LutO5Q4oLfFWuQ4YRIeOqIk" +
                                                    "2U8JgfV/nhn7zaNXT94nMgQo+qoJ6OdjBsACQgZu/eWrR967/P7Zd5Qoqxh0" +
                                                    "TW/S0LUSnLE5kgJQYkCy8tj3H7BMO68XdDxpEJ6cX7cN3HTxk9PtMpoGrATJ" +
                                                    "cON/PyBav/4O9PDrh/7TLY5JabyVRZZHZNIBq6KTd1GKj3I9Ssff3vD4K/j3" +
                                                    "gLSAbq4+RwRgKcIyBZgGlk5/cYiE9sU/9L350GLfv0GPHGrQXbgU7KLFKr0m" +
                                                    "xvPpuctX3m7ZcF5kXO0kdgUANCWbdGUPLmutIjuaHe76wWVuXlQ3oRnM+N1K" +
                                                    "ne+8PP3ER8/JCk+2tgQxObXwyHfp0wtKrP/3VbTgOI+8AwjNWmSefwd/KXi+" +
                                                    "5Q/Pb74ge0Bnxm9EG8NO5AhzNi2nlhAx8uGF+Rf/OH9SmtFZ3v6G4Xb33D++" +
                                                    "eSN95l+vVcFYKBUby2tfmidxCJDLeHGExyWGk1/uNSZPfPCFOL0C6ao4NsGf" +
                                                    "U889sS6z84rgjyCHc/eUKrsH5FTEu+1Z8zOlt+5lBdXnULvm32IPYsPjhZ2D" +
                                                    "rHGDqy3cdMv2y29h8soxFELq+mRkY2KTYBd5FOacWiSwxDenlEICvzKCuleM" +
                                                    "A3y4QdYWnw7yOOgWNkqAIQaximxK0N3Mh1tLYr6DoRooAT7d5pTCgAUVyt9X" +
                                                    "M7+2uUVwnbMtwmEi2JPdUbfT4VUaNktVQr+hrDfeLaoscvkjz/zpBfbW1ttk" +
                                                    "tm1ZOk2SjK+c+Hjd/p1TD/wPHbEnEfvkkc/cfe61Ozdrv1ZQTRi5ijt5OdNQ" +
                                                    "ebyaKIGPCGt/WdS6HfGzjQ/9QpktoZuQcBMSBD9bZu9ePhyAwGo8DjJs4Nue" +
                                                    "6rA+bDpMAPHcX9f+5fanF98XfaUk+t82cZ66jKxDfNjoVOzJ4HbFUmRn2G87" +
                                                    "OcVmeFr8ftuS7Ld+4uIlEpeheofqMwBUfHEEUnflLJ4hsfQV/mt3qgkegKfV" +
                                                    "F9y6hODiMhUTymzCpmPozMuTuMhSFLku3xc8r5f64BEoevbEwmJ+71M3Kb5T" +
                                                    "b2OokdnODw0CRsWOSon5T0N7+IPWwtPr29Nb9eJSLZf8kuOv2wUVWybIM3w4" +
                                                    "AgDhcpOFA7ZXCTn0/uqXZJ59XRUf5vJjUju/2NawdvHAu7IJBx98jfDVVfAM" +
                                                    "I45vsXmdQ0lBF8o1BmjHf44x1FFxdYdKEL9C4zlJ+CDkTIwQMsqfxYmOA/AB" +
                                                    "EZ+ecAIs66n8NCgztoTKgu8kU6GvDLbEfzUCiPHk/zUmtAuLe+45dm37UwKv" +
                                                    "oIzx3Jz4Coabh7zdhjC1acnTgrPqdg9+1fp840CQXK186PSvtHHd+Pz+7wGT" +
                                                    "WpulQxIAAA==");
}
