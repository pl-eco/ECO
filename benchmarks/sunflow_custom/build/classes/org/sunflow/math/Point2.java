package org.sunflow.math;
public final class Point2 {
    public float x;
    public float y;
    public Point2() { super(); }
    public Point2(float x, float y) { super();
                                      this.x = x;
                                      this.y = y; }
    public Point2(Point2 p) { super();
                              x = p.x;
                              y = p.y; }
    public final Point2 set(float x, float y) { this.x = x;
                                                this.y = y;
                                                return this; }
    public final Point2 set(Point2 p) { x = p.x;
                                        y = p.y;
                                        return this; }
    public final String toString() { return String.format("(%.2f, %.2f)",
                                                          x,
                                                          y); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1414698736000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVYW2wUVRg+O70XaEsrUCq0pRRjQXdEo4nUoLC2UFyk0tKE" +
                                                    "El1PZ8+2087NmbPttlovRANBQ4wWBIN9MBBvKMZI1BiTvigQfNEYjQ+K8UUi" +
                                                    "8sCDaLz/55zZmdnZbYVEN5mzZ8/5///81+/8s8cvohLHRmssUxsf1EwaJRka" +
                                                    "HdZujdJxizjRLfFbu7HtkGRMw47TC2sJpeXt6su/PztUI6HSflSHDcOkmKqm" +
                                                    "4WwnjqmNkmQcVfurHRrRHYpq4sN4FMtpqmpyXHVoexzNC7BS1BrPqiCDCjKo" +
                                                    "IHMV5A0+FTAtIEZajzEObFDnIfQoisRRqaUw9ShakSvEwjbWXTHd3AKQUM5+" +
                                                    "94FRnDljo2bPdmFznsEH1shTLzxQ804Rqu5H1arRw9RRQAkKh/Sj+TrRB4jt" +
                                                    "bEgmSbIfLTQISfYQW8WaOsH17ke1jjpoYJq2iecktpi2iM3P9D03X2G22WmF" +
                                                    "mrZnXkolWjL7qySl4UGwdbFvq7Cwk62DgZUqKGansEKyLMUjqpGkqCnM4dnY" +
                                                    "eg8QAGuZTuiQ6R1VbGBYQLUidho2BuUeaqvGIJCWmGk4haKGWYUyX1tYGcGD" +
                                                    "JEFRfZiuW2wBVQV3BGOhaFGYjEuCKDWEohSIz8V779j/sLHZkLjOSaJoTP9y" +
                                                    "YGoMMW0nKWITQyGCcf7q+EG8+KO9EkJAvChELGjee+TSXTc0zpwWNNcWoNk2" +
                                                    "MEwUmlCODlR9tizWdnsRU6PcMh2VBT/Hcp7+3e5Oe8aCylvsSWSb0ezmzPZP" +
                                                    "dj7+OrkgocouVKqYWlqHPFqomLqlasTeRAxiY0qSXaiCGMkY3+9CZTCPqwYR" +
                                                    "q9tSKYfQLlSs8aVSk/8GF6VABHNRGcxVI2Vm5xamQ3yesRBCC+BBtfAUIfHh" +
                                                    "3xT1yTscSHcZK9hQDVOG5CXYVoZkopiJAfDukI7tEUdW0g41ddlJGynNHJMd" +
                                                    "W5FNe9D7rcNh4AtI1pujLL+s/01yhtlUMxaJgLuXhYtdgzrZbGpJYieUqfTG" +
                                                    "jktvJc5KXvK73qBoCRwQdQ+IsgOi4gAUiXC517CDRAghACNQygBy89t67t/y" +
                                                    "4N4WcFzGGitmLgTSFrDGPb1DMWN+vXdxVFMg6epf3rUn+usrd4qkk2cH54Lc" +
                                                    "aObQ2BN9j90kISkXZZk1sFTJ2LsZNnoY2BqurkJyq/ecv3zi4KTp11kObLvl" +
                                                    "n8/Jyrcl7HfbVEgSANEXv7oZn0x8NNkqoWLABMBBiiFvAWIaw2fklHF7FhKZ" +
                                                    "LSVgcMq0dayxrSyOVdIh2xzzV3hCVPH5QgjKPJbXbFLqJjr/Zrt1FhuvEQnE" +
                                                    "ohyygkNu5wczh0++uOZ2KYjO1YH7rodQUesL/STptQmB9W8OdT9/4OKeXTxD" +
                                                    "gGJloQNa2RiDyoeQgVufOv3Q1+e+PfqF5GVVhMIVmB7QVCUDMq7zTwFc0ACb" +
                                                    "WOxbdxi6mVRTKh7QCEvOP6pXrT350/4aEU0NVrLJcMO/C/DXl25Ej5994JdG" +
                                                    "LiaisHvJt9wnEw6o8yVvsG08zvTIPPH58sOn8EsAmwBVjjpBOPogbhnirpd5" +
                                                    "qFbzMRraW8uGZitvjy805Me4wo1xRcEYs6E1dJrEJUqgftscXZKt6gDco+7N" +
                                                    "Ik/Wnhs5cv5NUcDhayhETPZO7fs7un9KCtzVK/OuyyCPuK+5yguEiX/DJwLP" +
                                                    "X+xhprEFgde1MffSaPZuDctiibJiLrX4EZ0/nJj88NXJPcKM2tyrqgM6sTe/" +
                                                    "/PPT6KHvzhRATKgEE/OcWscVvW2OIG5kwy1XHsQqN4hVVxzEiCgUvs+pNs2h" +
                                                    "Thcb7s5XR+hTfyX50MlatgCg/7ZNG9j9/a/cT3mQXCBFQvz98vEjDbH1Fzi/" +
                                                    "j42MuymTf6tBe+vz3vy6/rPUUvqxhMr6UY3i9s59WEszBOqHftHJNtTQX+fs" +
                                                    "5/Z+otFp97B/WThHA8eGUdnPDZgzajavLATEi+EpduNbHI5vBPHJfTzE1EXy" +
                                                    "dTyqIlzxqxfWmxU2HhAmQt3Cx1VsuF5EnU3bWHKrBtaC+YBYRS2frUnl1XR0" +
                                                    "99R0ctuxtZKbZuspqqCmdaNGRokWEMWahOU5TcJW3pb7Id332hvv0c/WrBN1" +
                                                    "uXr2NAwzntr9Y0Pv+qEHr6I1aArZFBb52tbjZzZdpzwnoSIvM/LeNHKZ2nPz" +
                                                    "odIm8Gpk9OZkRaMXyHrm3WvhqXMDWVf4ep4jYGzYmSmM617QBUwNzYELw2yA" +
                                                    "K64I2uhAzuUhViJf96Wu7kv/G93z4MyeQ22uq37VajfD0+Sq3fTfqB3U6pE5" +
                                                    "9h5lwxiFd3VTvHByqkUU1fAGgqFRVGwUAGlohkRvzjqO+ryXe/FCqrw1XV2+" +
                                                    "ZHrHV7zb9F4aK+DNLZXWtCBaBeallk1SKlexQmCXxb+eAs3CLwgUFbMvrt+T" +
                                                    "gmwvRfMCZBSVubMg0dMQKCBi02esAnYLFM6gHOyxwki0MgcT+B8h2fpNi79C" +
                                                    "EsqJ6S33PnzptmMcDEoUDU9MMCnlcVQmemgPA1bMKi0rq3Rz2+9Vb1esymJb" +
                                                    "FRtqA2kS0K2pcH/ZoVuUd4QT7y95945Xpr/lDe4/VxwDg6ESAAA=");
}
