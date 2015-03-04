package org.sunflow.image;
public class ConstantSpectralCurve extends SpectralCurve {
    final private float amp;
    public ConstantSpectralCurve(float amp) { super();
                                              this.amp = amp; }
    public float sample(float lambda) { return amp; }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1166416160000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Xe2wURRif3vVBrzV9AAUR6IMCyuNWEzDaGhFrgcIhtYUi" +
                                                   "RTymu3PXhX25O3s9\nCvERE0GID6ImmigSQwIiqAkaNEGEAIoSEzURExJRQ6" +
                                                   "ImPhJjghj9w29mbm9v9641sUl352a+x3zz\n/b7ffHvkV1Th2Gi67MTpNos4" +
                                                   "8a7+Xmw7ROnSsOOshamkfK6iuvfgKsOMoLIEiqgKRXUJ2ZEUTLGk\nKlLPPZ" +
                                                   "1ZG823TG1bWjNpnGRpfIu2OGdvZWJxkcH1+443PnagvDmCKhKoDhuGSTFVTa" +
                                                   "NbI7pDUX1i\nC85gyaWqJiVUh3Ym0HXEcPUu03AoNqjzEHoYRROo0pKZTYpa" +
                                                   "E55zCZxLFraxLnH3Ui93CxYm2oRi\n1SDK0rw70FwQ1IRt5/T6iqXByAS2OA" +
                                                   "Dh8B1A1C35qEW0RaFa0UMDt+7Y/3oU1Q2iOtXoZ8ZkiISC\nv0FUqxN9iNjO" +
                                                   "UkUhyiBqMAhR+omtYk0d5V4HUaOjpg1MXZs4fcQxtQwTbHRci9jcpzeZQLUy" +
                                                   "i8l2\nZWra+TNKqURTvF8VKQ2nIewmP2wR7jI2DwHGVNiYncIy8VTKt6oGZL" +
                                                   "w5rJGPsX0VCIBqlU7osJl3\nVW5gmECNIpcaNtJSP7VVIw2iFaYLXiiaNqZR" +
                                                   "dtYWlrfiNElSNDUs1yuWQKqaHwRToWhyWIxbgixN\nC2WpID/zm67uOvTyyb" +
                                                   "s4tssVImts/zFQmhlS6iMpYhNDJkLxmht/vmeDOz2CEAhPDgkLmaWzj69L\n" +
                                                   "/PRhs5C5oYTMmqEtRKZJ+d69zX3bl5soyrYxwTIdlSU/EDkvh97cSmfWgqpt" +
                                                   "yltki3Fv8VTfRxse\nPUx+jqBYD6qUTc3VAUcNsqlbqkbs5cQgNqZE6UHVxF" +
                                                   "C6+HoPqoJxAiAvZtekUg6hPahc41OVJv8N\nR5QCE+yIqmGsGinTG1uYDvNx" +
                                                   "1kIIVcE/qoX/aiT++JuijrjkuEZKM0ckx5Yl007nf6s6ZFTyarzf\ngnOxsd" +
                                                   "bl2hkSZxiyKBqQhk2dSFjGhmqYUlqFqpXNhQrJsPf/tpxl+24cKStjRBguaA" +
                                                   "1qYYWpKcRO\nygevfLqje9WTuwRYGMBzEVM0FxzGcw7j3GG8pENUVsb9TGKO" +
                                                   "Rdrg0LdC+QLR1d7Uv2nl5l1tUcCL\nNVIOJ8ZE2yC23G66ZbPLr/Eebl8GoE" +
                                                   "19bePO+LWDSwTQpLGpuKR2zedHL+z/o3ZeBEVK8ySLEpg6\nxsz0MnLN8197" +
                                                   "uLJK2f9t9+pjFy98c6NfYxS1F5V+sSYr3bZwPmxTJgqQoW/+wPV10fVoYG8E" +
                                                   "lQMf\nAAfy/QO9zAz7CJRwp0eHLJaqBKpJmbaONbbkcViMDtvmiD/DgVLPxx" +
                                                   "MhOTUM0y05oHuAR2x1ssWe\nTQJYLNuhKDjdXnu88uavT9Sc48fiMXNdwd3X" +
                                                   "T6io8wYfLGttQmD+mxd7n3vh150bOVJyUKFwIbpD\nmipnQWWOrwIFrgECWS" +
                                                   "Lb1xm6qagpFQ9phCHun7rZt7z7y9P1IjUazHiZXfDfBvz56+9Gj1548M+Z\n" +
                                                   "3EyZzC4YPwxfTEQz0be81LbxNraP7GNfznjpY/wK8B9wjqOOEk4jZfkimFrY" +
                                                   "oNiqDkSX4Wm88kTb\nB+fXvbpTQP+mcbqQQq2k/Mi3322NPnN6SOiFyT4kvH" +
                                                   "fmgR+OXembJI5J3Iizii6lQh1xK3IA1Fks\nIa3jeeDSZ+e3Hnm473JuR41B" +
                                                   "bu+G/ufHbWfI3Due+r4E/QB8TEy5O4njcx5/xhkg+RkivtbBHm2w\nmwVjnF" +
                                                   "OJticp7zicbnMf+uR97rcGF/ZPhXBdjS0RcD17zGJBTwmz3ArsDIPcolP3Pl" +
                                                   "CvnfobLA6C\nRRnaDWeNDRybDYA9J11Rden0mabNX0RRZBmKQaDKMsx5AlVD" +
                                                   "gRJnGOg5ay25i9dg/cgE9uQhI34I\n03IHkC34xSA1DlaWsabJZ5jk0IJDiU" +
                                                   "/XvMIPYEyCLAGjkJ3Rk+v2XfuMXuZ2fKZi2q3Z4rsHGk1f\n97aLmYbKt1/V" +
                                                   "I6hqENXLubtlAGsu44NB6Nwc78KBdjmwHuzCRMvRmWfi6WEcF7gNc6QPOhgz" +
                                                   "aTau\nDdFiIzvt6fAfy9FiLEyLZYgPVnCVdv6cK0gswqCsGhj2VWXZagazNh" +
                                                   "lFsc4VFgk+Zc9O9ugRKb2z\nVOr5puYEAAA5nzFWJ8grcOf9v9c+gc9uiuTK" +
                                                   "5VYKCDOthRrJEC2EnhmBW3k17339bO1+/Y3j9Iv5\nHaKW542NtLDivI79o8" +
                                                   "0db+35H3dxcyi2sOmGzA33RYfV8xHenovkF7X1QaXOYMpjsB/XNtYGEt8S\n" +
                                                   "vA9neynw3kX3oZ8Yn6RyRM+TzKU2jcNiSfYYhOvOAVyIbnSRD4qN44AiyAfw" +
                                                   "xVCyR2OX1NSiDzvx\nMSInLm1/4Griq794t5H/YKiBrj3lalphfRSMKy2bpF" +
                                                   "S++RpRLRZ/pShqKGocoQT4m2+WCEGVopoC\nQaiO3KhQCPIZBSE21C2+MAUy" +
                                                   "XNyYBoLNBs6EhT4rgFb+Me0hyhWf00n5/qMbW7J71j7LYVoBn+Gj\no/y7CT" +
                                                   "4DRRuVR2XrmNY8W5+jt98aOPHm7V7V8etjUtav9Xz95nM8abwcQyWU7l26dY" +
                                                   "vybmP0vSnv\n3HFw3+UI757+BSWrZGwDEQAA");
}
