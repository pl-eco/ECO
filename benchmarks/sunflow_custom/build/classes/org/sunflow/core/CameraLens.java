package org.sunflow.core;
public interface CameraLens extends RenderObject {
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1WXWwUVRS+u2233VK6baGAFFr6A0kp7oQYNFg0lE0LxYU2" +
                                "bdFYIsvdmbu7Q2fmDnfutttiDZIYCA/EaFEw2ieIovzFSNAYEp4Egi8Yo/FB" +
                                "8E2j8sCLPqDiuXf2d9rim5vM3Ttnzu8953znnr+PKhyGumxqTCYNysMkw8MH" +
                                "jE1hPmkTJ7wzumkQM4doEQM7zgjQYmrb5dAfD99M1flRYBQtwZZFOeY6tZwh" +
                                "4lBjnGhRFCpQew1iOhzVRQ/gcaykuW4oUd3h3VG0qEiUo45ozgUFXFDABUW6" +
                                "oPQUuEBoMbHSZkRIYIs7B9FryBdFAVsV7nHUWqrExgybWTWDMgLQUCXeX4Sg" +
                                "pHCGoTX52N2Y5wR8skuZeXdf3adlKDSKQro1LNxRwQkORkZRjUnMOGFOj6YR" +
                                "bRTVW4Row4Tp2NCnpN+jqMHRkxbmaUbyhySIaZswabNwcjWqiI2lVU5ZPryE" +
                                "Tgwt91aRMHASYl1WiNWNsE/QIcBqHRxjCaySnEj5mG5pHLV4JfIxdrwADCBa" +
                                "aRKeonlT5RYGAmpwc2dgK6kMc6ZbSWCtoGmwwtHKBZWKs7axOoaTJMbRCi/f" +
                                "oPsJuILyIIQIR41eNqkJsrTSk6Wi/NzfveXEIWuH5Zc+a0Q1hP9VINTsERoi" +
                                "CcKIpRJXsGZ99B287NoxP0LA3Ohhdnmuvvpg64bm6zddnqZ5eAbiB4jKY+qZ" +
                                "eO2dVZHOzWXCjSqbOrpIfknksvwHs1+6MzZ03rK8RvExnPt4feirlw9/TH7z" +
                                "o+p+FFCpkTahjupVatq6Qdh2YhGGOdH6UZBYWkR+70eVsI/qFnGpA4mEQ3g/" +
                                "KjckKUDlOxxRAlSII6qEvW4laG5vY56S+4yNEKqEB/ngWYXcnyRwFFdS1CQK" +
                                "VrGlW1SB2iWYqSmFqDTGiE2V3siAEodTTpmYjTmKk7YSBp2IqWmHU1NxmKpQ" +
                                "lsyRFZUyokSg0BiOEssJi1qz/xcrGRFr3YTPB2lY5QUBA/pnBzU0wmLqTHpb" +
                                "74OLsdv+fFNkT4mjJjASzhoJCyPhghHk80ndS4UxN72QnDFocwDAms7hV3bu" +
                                "P9ZWBnVlT5SLo83IvluRewFBj1Oyw/u+uH76yntdm/3FYBAqgtdhwt3Sqi/Y" +
                                "HWGEAP3HU4Nvn7x/dK80Chzt8xnoEGsECg3QE1DojZsHf7h398y3/ryjZRwQ" +
                                "Nx03dJWjKhwHuMIq5yiYx53iQHxy38jR6jknNQQ1SpjbPiLc1Qu1uISnM0dm" +
                                "ZrWBsxvdRmwobZtemAoXvvv76/Cpn27Nk6Ugp/aTBhknRolnYBLKKWutV6W7" +
                                "JPr1y+GiQu8fP/fJVX6n61nX5PqFZ6RX8MaRX1eOPJ/a70f+0jknrAOpWkgO" +
                                "iumUn0ItnuC9Ks/tOn9r+zr1LT8qy4LcPIBeKtRdfAxglBGYQJY4UEGpBqNt" +
                                "3qpnVCUajKmC3fVr8JXYtekOPyoHpIbpxDGgCQB/s9d4Cbh252pTmKqAQ0hQ" +
                                "ZmJDfMpNl2qeYnSiQJHtWCv39ZCekOiCRniasvAj/8XXJbZYl7rtK/mb5Nos" +
                                "llaZW7/YtomlXVTWukInAFQaUG8iGR17LJNqekLHcYOInvwrtHbjld9P1LkV" +
                                "ZAAll50N/62gQH9iGzp8e9+fzVKNTxWjutCdBTa3SZcUNPcwhieFH5nXv1l9" +
                                "+gb+ACYJoLejTxEXkGVolSDU+ZjrGtNNmCDj2RGnTDfcG3v/lwtuCXvnoYeZ" +
                                "HJs5/ih8YsZfdGlonzO3i2Xci4PMxGI3c4/g54PnH/GIjAmCOzgaItnptSY/" +
                                "vmxbpKf1cW5JE30/X5r+8qPpoyIMYaaTi/KiWCZoq7T8nFh6XOiJcFQGaCS2" +
                                "ffN9DGgU8Eu2Qb9cpNIt8vMzcu0WLmeRWLzvEstTIJkkfAhP5kBt6VxQw5P2" +
                                "HA0ZjqoLc0GkfcWcS6d7UVIvzoaqls/u+V72W/4yE4QbRSJtGEXtXNzaAZuR" +
                                "hC4dDbptZMu/Ebh4ez3kqFz8SR+HXbaXOFpUxMahxtxdMdMonCkwie1eSBsq" +
                                "GVa2d3S1l1SovIjngC3tXsVj6qXZnbsPPXj6rETJCrjCT03JixvcQ120yINj" +
                                "64LacroCOzof1l4Ors0VSK1YGoogosi3lvmbude0uWy/qc+Xf7blw9m7cuL9" +
                                "C2iK8pwhDQAA");
}
