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
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YW2xcRxmePXZ8i+9pEhMSO/ElqpOyhwY1KHUVSFd247Ct" +
                                                    "3TgJ6hayHZ+d3T3xuWXOHHvj4LaJhBL1IULglhQVP6CU0jZNqqpRQahSXqCt" +
                                                    "ipBaIRAPNMALFSESeaBUFCj/zJzbnl0b8YKlMztn5r///3z/HF++hda5FO12" +
                                                    "bONUybBZmlRY+oRxT5qdcoibPpS9ZxpTlxQyBnbdI7CW1wZf7frok2+VuxXU" +
                                                    "lEMbsGXZDDPdttzDxLWNeVLIoq5oddwgpstQd/YEnseqx3RDzeouG8ui9TFW" +
                                                    "hoazgQkqmKCCCaowQT0QUQFTB7E8M8M5sMXck+hxlMqiJkfj5jG0o1qIgyk2" +
                                                    "fTHTwgOQ0MLfj4FTgrlC0fbQd+lzjcNP71aXv3u8+7UG1JVDXbo1w83RwAgG" +
                                                    "SnKo3STmLKHugUKBFHKoxyKkMEOojg19UdidQ72uXrIw8ygJg8QXPYdQoTOK" +
                                                    "XLvGfaOexmwaulfUiVEI3tYVDVwCXzdFvkoPJ/g6ONimg2G0iDUSsDTO6VaB" +
                                                    "oYEkR+jj8FeAAFibTcLKdqiq0cKwgHpl7gxsldQZRnWrBKTrbA+0MLRlVaE8" +
                                                    "1g7W5nCJ5BnqS9JNyy2gahWB4CwMbUySCUmQpS2JLMXyc+uh+y6ctg5airC5" +
                                                    "QDSD298CTP0JpsOkSCixNCIZ23dln8Gb3jyvIATEGxPEkuaNb9z+8l3919+W" +
                                                    "NJ+tQzM1e4JoLK9dmu18b2tmdF8DN6PFsV2dJ7/Kc1H+0/7OWMWBk7cplMg3" +
                                                    "08Hm9cM/f+TJl8hNBbVNoibNNjwT6qhHs01HNwh9gFiEYkYKk6iVWIWM2J9E" +
                                                    "zTDP6haRq1PFokvYJGo0xFKTLd4hREUQwUPUDHPdKtrB3MGsLOYVByHUDA9q" +
                                                    "959gjhiy1bJtEhVr2NItW4XaJZhqZZVodp4Sx1bHM1PqLES5bGI656quZxUN" +
                                                    "eyGveS6zTdWlmmrTUrCs6iZUgTpJKSl5BqYzDgSTYiPj0XmS5oXn/P9VVngU" +
                                                    "uhdSKUjQ1iQ8GHCyDtpGgdC8tuzdP377Sv5dJTwufvwYuhM0pn2NaaExXV8j" +
                                                    "SqWEoju4ZlkFkMM5QAPAyfbRma8feuz8YAOUn7PQCAngpIPguG/OuGZnIsiY" +
                                                    "FMCoQd32/eDRc+mPX/iSrFt1dXyvy42uX1w4c+yJzytIqQZq7h4stXH2aQ6v" +
                                                    "IYwOJw9oPbld5z786OozS3Z0VKuQ30eQWk6OAIPJRFBbIwXA1Ej8ru34Wv7N" +
                                                    "pWEFNQKsAJQyDKUPKNWf1FGFBGMBqnJf1oHDRZua2OBbARS2sTK1F6IVUSGd" +
                                                    "Yt4DSVnPj8YOePr8syJ++e4Gh493yIriWU54IVB74ifXn732vd37lDjAd8Va" +
                                                    "5gxhEi56oiI5QgmB9d9dnP7O07fOPSoqBCiG6ikY5mMGwANSBmH95tsnf3vj" +
                                                    "g0u/UqKqYtBFvVlD1yogY2ekBaDFgGLluR8+apl2QS/qeNYgvDj/2TVy97W/" +
                                                    "XOiW2TRgJSiGu/67gGj9M/ejJ989/vd+ISal8dYWeR6RyQBsiCQfoBSf4nZU" +
                                                    "zry/7dm38PcBeQHtXH2RCABThGcKMI2sXv5CiIT6lR8O/fKJlaE/gB051KK7" +
                                                    "cEk4QEt1ek+M56+Xb9x8v2PbFVFxjbPYFQDQlmzatT25qtWK6mh3eOhH17iJ" +
                                                    "Ud2E5jDvdy91qffG3HMfviJPeLLVJYjJ+eWnPk1fWFZi94GhmpYc55F3AmFZ" +
                                                    "h6zzT+EvBc+/+cPrmy/IntCb8RvT9rAzOcKdHWuZJVRM/Onq0k9/tHROutFb" +
                                                    "3Q7H4bb3yq//9Yv0xd+/Uwdj4ajYWF4D07yIQ4BcI4oTPC8xnPzHlDF79o8f" +
                                                    "C+k1SFcnsAn+nHr5uS2Z/TcFfwQ5nHugUts9oKYi3j0vmX9TBpt+pqDmHOrW" +
                                                    "/FvtMWx4/GDnoGrc4KoLN9+q/epbmbyCjIWQujWZ2ZjaJNhFEYU5pxYFLPHN" +
                                                    "qaSQwK+MoB4U4wgf7pRni09HeR50CxsVwBCDWCVWFnRf4MMXK2K+j6EGOAJ8" +
                                                    "usephAkLTih/38j8s809guudbREOE8Ge7I66nQ6v1rBZqZP6bVW98UFxyqKQ" +
                                                    "P/Xiy2+w93bfK6tt1+plkmR86+yftxzZX37sf+iIA4ncJ0W++ODldx7YqX1b" +
                                                    "QQ1h5mru6NVMY9X5aqMEPiqsI1VZ63fEzx4+DAtjdoVhQiJMSBB8dY29R/hw" +
                                                    "FBKr8TzItEFsB+rD+rjpMAHEiz/e/Pp9L6x8IPpKRfS/PUKeuoau43zY7tTs" +
                                                    "yeT2xUpkf9hveznFTng6/H7bkey3fuHiVQqXoWaH6vMAVHxxAkp3/QKeJ7Hy" +
                                                    "FfHrduopHoGn01fcuYri0honJtTZhk3H0JlXIHGVlShzfX4seF2v9gEkUPTS" +
                                                    "2eWVwtTzdyt+UO9lqJXZzucMAk7FRKXE/OHQH/6gzfAM+v4M1r241Ksl/8jx" +
                                                    "172Ciq2R5Hk+nASAcLnLIgB766Qcen/9SzKvvr6aD3X5caldWelq2bxy9Dey" +
                                                    "CQcfgK3wFVb0DCOOb7F5k0NJURfGtQZox39OM9RTc3WHkyB+hcWLkvBxqJkY" +
                                                    "IVSUP4sTnQHgAyI+PesEWDZQ+2lQ5WwFVSXfSZbCUBVsif9yBBDjyf9z5LWr" +
                                                    "K4ceOn177/MCr+AY48VF8VUMNw95uw1haseq0gJZTQdHP+l8tXUkKK5OPvT6" +
                                                    "V9q4bXz+tf8AmR7mt1MSAAA=");
}
