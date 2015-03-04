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
      1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYa2wcxR2fWzt+xe+QxE0TO/Ejwgm9LamgCkZpw8kmTg/i" +
                                                    "xkkQB8SM9+bOm8ztbmbn7ItTA4lUJeJDhMBAQNQfUHiHBFWNKEJI+cJLICQQ" +
                                                    "AvGhBPqliBCJfICiUqD/mdnX7Z1d9UMt7dzszP8x/9fvP+szl9Eyl6HNjk0P" +
                                                    "56nNk6TEkwfodUl+2CFucmf6ujHMXJJNUey6e2Btwuh9qe3b7x+YatdQXQat" +
                                                    "wJZlc8xN23J3E9em0ySbRm3h6jAlBZej9vQBPI31IjepnjZdPpRGyyOsHPWn" +
                                                    "/SPocAQdjqDLI+jbQypgaiFWsZASHNji7iF0D0qkUZ1jiONxtKFciIMZLnhi" +
                                                    "xqQFIKFBvO8DoyRziaH1ge3K5gqDH96szz+6v/3PNagtg9pMa1wcx4BDcFCS" +
                                                    "Qc0FUpgkzN2ezZJsBnVYhGTHCTMxNWfluTOo0zXzFuZFRgInicWiQ5jUGXqu" +
                                                    "2RC2saLBbRaYlzMJzfpvy3IU58HWVaGtysIRsQ4GNplwMJbDBvFZag+aVpaj" +
                                                    "njhHYGP/74AAWOsLhE/ZgapaC8MC6lSxo9jK6+OcmVYeSJfZRdDC0ZpFhQpf" +
                                                    "O9g4iPNkgqOuON2Y2gKqRukIwcLRyjiZlARRWhOLUiQ+l2+98eQRa4elyTNn" +
                                                    "iUHF+RuAqTvGtJvkCCOWQRRj86b0I3jVayc0hIB4ZYxY0bz8hyu/vab7wluK" +
                                                    "5udVaHZNHiAGnzBOT7a+vzY1uLVGHKPBsV1TBL/Mcpn+Y97OUMmBylsVSBSb" +
                                                    "SX/zwu43br/veXJJQ02jqM6wabEAedRh2AXHpITdTCzCMCfZUdRIrGxK7o+i" +
                                                    "epinTYuo1V25nEv4KKqlcqnOlu/gohyIEC6qh7lp5Wx/7mA+JeclByFUDw9q" +
                                                    "9h5/jji6U5+yC0THBrZMy9YhdwlmxpRODFt3ccGhEDW3aOWoPaO7zNBtlg/e" +
                                                    "zQKEXB9ljOSLFLNxBzzHME0V2TRJiixz/s/yS8K+9plEAly/Nl74FGpmh02z" +
                                                    "hE0Y88Wbhq+cnXhHCwrB8wxHV4PGpKcxKTUmq2tEiYRUdJXQrOIL0TkIdQ4I" +
                                                    "2Dw4ftfOu0/01kBiOTO14FpB2gtWescZNuxUCAajEvIMyMiuJ+84nvzumd+o" +
                                                    "jNQXR+6q3OjCqZmj++79pYa0cggW5sFSk2AfE8AZAGR/vPSqyW07/sW35x6Z" +
                                                    "s8MiLMN0DxsqOUVt98YDwWyDZAEtQ/Gb1uPzE6/N9WuoFgADQJJjSGrAn+64" +
                                                    "jrIaH/LxUtiyDAzO2ayAqdjyQa6JTzF7JlyRGdIq5x0QlOUi6TfA0+VVgfwV" +
                                                    "uyscMV6lMkpEOWaFxOORVy48dv7xzVu1KHS3RZrhOOEKCDrCJNnDCIH1v50a" +
                                                    "e+jhy8fvkBkCFH3VFPSLMQWwACEDt/7xrUOfXPz09IdamFUc+mNxkppGCWRs" +
                                                    "DLUAaFBIVhH7/r1Wwc6aORNPUiKS899tA9ee/+pku4omhRU/Ga757wLC9Z/d" +
                                                    "hO57Z/8/u6WYhCGaVmh5SKYcsCKUvJ0xfFico3T0g3WPvYn/BJgKOOaas0RC" +
                                                    "kyYt04BpYPH0l0IUiC883ffevQt9n8M5MqjBdKH9b2f5Kl0lwvP1mYuXPmhZ" +
                                                    "d1ZmXO0kdiUANMXbcWW3LWuiMjuaHeH6wSXuWMwsAOxPe31Jn+u8ePCJL15U" +
                                                    "FR5vYjFicmL+/p+SJ+e1SKfvq2i2UR7V7eXJWlSe/wR/CXh+FI/Ib7Gg0L4z" +
                                                    "5bWc9UHPcaQ5G5Y6llQx8o9zc68+O3dcmdFZ3uiG4R734kc/vJs89dnbVTAW" +
                                                    "SsXG6oKXFEkcAOQSXhwRcYng5L920cljf/9OSq9AuiqOjfFn9DNPrEltuyT5" +
                                                    "Q8gR3D2lyu4BORXybnm+8I3WW/e6huozqN3w7qv7MC2Kws5A1rj+JRbutGX7" +
                                                    "5fctdbkYCiB1bTyyEbVxsAs9CnNBLRNY4ZtTSiCJXylJ3SvHATFcrWpLTAdF" +
                                                    "HEwL0xJgCCVWnk9Jul+J4dclOd/KUQ2UgJhucUpBwPwKFe8ruVfbwiK4uNkW" +
                                                    "ETDh76nuaNrJ4NIMm6UqoV9X1htvkVUWuvz+5154mb+/+QaVbZsWT5M445vH" +
                                                    "vlyzZ9vU3f9DR+yJxT4u8rlbzrx980bjQQ3VBJGruH2XMw2Vx6uJEfhcsPaU" +
                                                    "Ra3bkT9bxNAvD7MpcBOSbkKS4LYl9m4Xw14IrCHioMIGvu2pDuvDBYdLIJ79" +
                                                    "6+q/3PjMwqeyr5Rk/9si5elL6NovhvVOxZ4KblckRbYF/bZTUGyEp8Xrty3x" +
                                                    "fuslLl4kcTmqd5g5DUAlFkcgdZfP4GkSSV/pv3anmuIBeFo9xa2LKM4vUTGB" +
                                                    "ziZxUTV5MUuiKkth5Lo8X4i8XuzTRqLo6WPzC9ldT12reU69gaNGbju/oASM" +
                                                    "iohKyPnvA3vEg1bD0+vZ01v14lItl7ySE6/XSyq+RJCnxXAIAELdzSVTlZBD" +
                                                    "769+SRbZ11XxCa4+G42zC20Nqxf2fqyasP9p1wjfV7kipVF8i8zrHEZypjxc" +
                                                    "o4924ucIRx0VV3eoBPkrTzyrCO+BnIkQQkZ5syjRUQA+IBLTY46PZT2VnwZl" +
                                                    "xpZQWfCdeCr0lcGW/P+FDzFF9R+MCePcws5bj1y5/imJV1DGeHZWfu/CzUPd" +
                                                    "bgOY2rCoNF9W3Y7B71tfahzwk6tVDJ3elTZ6NjG/8z9HcyymLRIAAA==");
}
