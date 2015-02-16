package org.sunflow.image;
public class BlackbodySpectrum extends SpectralCurve {
    private float temp;
    public BlackbodySpectrum(float temp) { super();
                                           this.temp = temp; }
    public float sample(float lambda) { double wavelength = lambda * 1.0E-9;
                                        return (float) (3.74183E-16 * Math.
                                                          pow(
                                                            wavelength,
                                                            -5.0) /
                                                          (Math.
                                                             exp(
                                                               0.014388 /
                                                                 (wavelength *
                                                                    temp)) -
                                                             1.0)); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1166394228000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWxURRSe3bbbdilsW/5qLW1pC7Et7hUJGixBYdNCcaFN" +
                                                    "fzAuyjJ7d7a99P5x72y7LVaRxJTwQIwWBIN9MBAE+YuRoDEkfVEg+IIxGh8E" +
                                                    "44tE5IEHkYiKZ+bu7t29u0V9scm9nZ05Z845c77zzbmnbqMi00CtuiaPDsga" +
                                                    "9ZME9e+QV/rpqE5M/8bgym5smCQakLFp9sFcWGw457t7/83BcjfyhNBcrKoa" +
                                                    "xVTSVLOHmJo8TKJB5LNn22WimBSVB3fgYSzEqSQLQcmkbUE0K0OVoqZgygUB" +
                                                    "XBDABYG7IKy1pUBpNlHjSoBpYJWaO9GryBVEHl1k7lG0OHsTHRtYSW7TzSOA" +
                                                    "HUrY7y0QFFdOGKg+HbsVc07AB1qFyXe2lX9UgHwh5JPUXuaOCE5QMBJCZQpR" +
                                                    "IsQw10ajJBpCFSoh0V5iSFiWxrjfIVRpSgMqpnGDpA+JTcZ1YnCb9smViSw2" +
                                                    "Iy5SzUiHF5OIHE39KorJeABiXWDHakXYweYhQK8EjhkxLJKUSuGQpEYpqnNq" +
                                                    "pGNseh4EQLVYIXRQS5sqVDFMoEordzJWB4ReakjqAIgWaXGwQlH1jJuys9ax" +
                                                    "OIQHSJiiKqdct7UEUqX8IJgKRfOdYnwnyFK1I0sZ+bm9efX+XeoG1c19jhJR" +
                                                    "Zv6XgFKtQ6mHxIhBVJFYimUtwYN4wcW9boRAeL5D2JK58Mqd55bVTl+2ZB7N" +
                                                    "I9MV2UFEGhaPRuZcqwk0rypgbpTomimx5GdFzuHfnVxpS+hQeQvSO7JFf2px" +
                                                    "uueLF3efJLfcyNuJPKImxxXAUYWoKbokE2M9UYmBKYl2olKiRgN8vRMVwzgo" +
                                                    "qcSa7YrFTEI7UaHMpzwa/w1HFIMt2BEVw1hSY1pqrGM6yMcJHSFUDA8qg6cA" +
                                                    "WX/8P0URod8EuAtYxKqkagKAl2BDHBSIqIUjcLqDCjaGTEGMm1RTBDOuxmRt" +
                                                    "RDANUdCMgfRvSYHsC+tkgEFEi4726nCIRlzxM6zp/4uVBIu1fMTlgjTUOEkA" +
                                                    "NMgGTY4SIyxOxte13zkTvupOF0XylChqBGP+pDE/N+bPMYZcLm5jHjNqpRmS" +
                                                    "NATlDkRY1tz78sbtexvgcBP6SCGcMBNtgCiTnrSLWsDmhE7OfCIAs+r9rRP+" +
                                                    "e8eftYApzEzgebXR9KGR17e89oQbubOZmEUGU16m3s34M82TTc4KzLevb+Lm" +
                                                    "3bMHxzW7FrOoPUkRuZqsxBucOTA0kUSBNO3tW+rx+fDF8SY3KgTeAK6kGLAN" +
                                                    "NFTrtJFV6m0p2mSxFEHAMc1QsMyWUlznpYOGNmLPcHDM4eMKSMoshv0aeDzJ" +
                                                    "YuD/2epcnb3nWWBiWXZEwWm549Ppw+ffbV3lzmRwX8ad2EuoxQcVNkj6DEJg" +
                                                    "/vtD3W8fuD2xlSMEJBrzGWhi7wCwA6QMjvWNyzu/u3H96NduG1UUrsl4RJbE" +
                                                    "BOyx1LYC3CEDTlnum/pVRYtKMQlHZMLA+YdvyfLzv+wvt7Ipw0wKDMv+eQN7" +
                                                    "/pF1aPfVbb/V8m1cIru77MhtMesA5to7rzUMPMr8SLz+1aLDl/B7QK1AZ6Y0" +
                                                    "RjhDudL10vyQ/sWQFKDU4STnC+OVN4aO3DxtlY3zgnAIk72T+x7490+6M27R" +
                                                    "xpyLLFPHukk5GGZb4HkAfy54/mIPAw2bsJi0MpCk8/o0n+s6S8/ih7nFTXT8" +
                                                    "dHb8sw/GJ6wwKrMvkXbokU5/8+eX/kM/XMnDWYA/DVPuo8B9bOFvP3OKnyji" +
                                                    "a23sVa/nrCX4TNW/OfsO1rhkUNbvXXJkz4/3uE85pJMnHQ79kHDqSHVgzS2u" +
                                                    "b1c/065L5HI4NHm27pMnlV/dDZ7P3ag4hMrFZAe5BctxVmMh6JrMVFsJXWbW" +
                                                    "enYHZF33bWl2q3HiIcOsk3fsPMCYSbOx10E17MpFC+EpTFJNoZNqXIgPAlyl" +
                                                    "gb+XsNdjqUov1g1pGLP2FMwQhUuv4ASlc2NNGSlEDHCLZuquONiO7pmcinYd" +
                                                    "W+5OIuNpikqppj8uk2EiO9CwKOvm2sT7STsL+058eIFea33Ggm3LzMhxKl7a" +
                                                    "83N135rB7f/hvqpzxOTc8sSmU1fWLxXfcqOCdDJzWuRspbbsFHoNAj292peV" +
                                                    "yNp0IitSifQmE+nNe2fYCbHrMMlsPG9cqv8hhfoCe/UAv5tY0a3ObkWesqWo" +
                                                    "Iqc1YYRblfP9Y/Xs4pkpX8nCqf5v+WWb7qtLobmNxWU5E8oZY49ukJjE/Sq1" +
                                                    "gG1B7iUwn9MrARfx/9zZrZYgfCvMyhAEMCdHmUIRigpAiA1FK9T5kLzcXsyK" +
                                                    "E8uBuDFMEigL9rqzCBqz4Mg/HlPQiVufj2Hx7NTGzbvuPHWM47AIPjvHxvjH" +
                                                    "Bnw7WT1FGn6LZ9wttZdnQ/P9OedKl6TKag57VSYbCYdvdfnv23ZFp/yGHPtk" +
                                                    "4cerj09d5xf+3wJ/3dnVDwAA");
}
