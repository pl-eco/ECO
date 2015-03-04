package org.sunflow.image;
public class ConstantSpectralCurve extends SpectralCurve {
    private final float amp;
    public ConstantSpectralCurve(float amp) { super();
                                              this.amp = amp; }
    public float sample(float lambda) { return amp; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXXWwUVRS+u/0vpbstf7WWtpSCtsUd0aDBEhQ2LRQX2tDS" +
                                                    "6CKU25m77dDZmWHmbrsUq0BiIDwQowXBYB8MBEH+YiRoDElfFAi+YIzGB8H4" +
                                                    "IhF54EEkouK5d2Zndme3qA82mende8+595x7vvOdM6duowLTQC26pmwfUDQa" +
                                                    "Ikka2qosCdHtOjFDayJLurBhEimsYNPsgbk+seFc4O79NweDflQYRTOwqmoU" +
                                                    "U1lTzfXE1JRhIkVQwJ1tU0jcpCgY2YqHsZCgsiJEZJO2RtC0NFWKGiMpEwQw" +
                                                    "QQATBG6CsMKVAqXpRE3Ew0wDq9Tchl5Dvggq1EVmHkXzMjfRsYHj9jZd3APY" +
                                                    "oZj97gWnuHLSQPWO75bPWQ4faBHG39kc/CgPBaIoIKvdzBwRjKBwSBSVxUm8" +
                                                    "nxjmCkkiUhRVqIRI3cSQsSKPcrujqNKUB1RMEwZxLolNJnRi8DPdmysTmW9G" +
                                                    "QqSa4bgXk4kipX4VxBQ8AL7Odn21PGxn8+BgqQyGGTEskpRK/pCsShTVeTUc" +
                                                    "HxtfBAFQLYoTOqg5R+WrGCZQpRU7BasDQjc1ZHUARAu0BJxCUfWUm7K71rE4" +
                                                    "hAdIH0VVXrkuawmkSvhFMBWKZnnF+E4QpWpPlNLic3vdsv071NWqn9ssEVFh" +
                                                    "9heDUq1HaT2JEYOoIrEUy5ojB/Hsi3v9CIHwLI+wJXPh1TsvLKqdvGzJPJpD" +
                                                    "prN/KxFpn3i0v/xaTbhpaR4zo1jXTJkFP8NzDv8ue6U1qUPmzXZ2ZIuh1OLk" +
                                                    "+i9e3nmS3PKj0g5UKGpKIg44qhC1uC4rxFhFVGJgSqQOVEJUKczXO1ARjCOy" +
                                                    "SqzZzljMJLQD5St8qlDjv+GKYrAFu6IiGMtqTEuNdUwH+TipI4SK4EFl8JQg" +
                                                    "64//p2ijMKjFiYBFrMqqJgB2CTbEQYGImmDiuK5A1MyEGlO0EcE0REEzBpzf" +
                                                    "chxCLqTyt1uHizOwEk4YwyTEQKb/v9snmXfBEZ8PLr7Gm/YKZMxqTZGI0SeO" +
                                                    "J1a23TnTd9XvpIF9LxQ9BgeG7AND/MBQzgORz8fPmckOtoILoRmCJAf6K2vq" +
                                                    "3rRmy96GPECVPpIP98pEG8BH25o2UQu7TNDB9xcBjlXvb9wTunf8eQuOwtS0" +
                                                    "nVMbTR4a2dX7+pN+5M/kX+YdTJUy9S7Gmg47NnrzLte+gT037549OKa5GZhB" +
                                                    "6DYxZGuyxG7wxsHQRCIBVbrbN9fj830Xxxr9KB/YAhiSYkA0kE+t94yMBG9N" +
                                                    "kSXzpQAcjmlGHCtsKcVwpXTQ0EbcGQ6Qcj6ugKBMY4ivt9MglQ6Irc7Q2Xum" +
                                                    "BSgWZY8XnIzbP508fP7dlqX+dN4OpFXCbkItFqhwQdJjEALz3x/qevvA7T0b" +
                                                    "OUJAYn6uAxrZOwycACGDa33j8rbvblw/+rXfRRWF4pjoV2QxCXssdE8BxlAA" +
                                                    "rCz2jRvUuCbJMRn3K4SB84/AgsXnf9kftKKpwEwKDIv+eQN3/pGVaOfVzb/V" +
                                                    "8m18IqtYrueumHUBM9ydVxgG3s7sSO76au7hS/g9IFQgMVMeJZyXfE6+ND2k" +
                                                    "azHkOBDpsM30wljljaEjN09baeMtCx5hsnd834PQ/nF/Wu2cn1W+0nWs+snB" +
                                                    "MN0CzwP488HzF3sYaNiExZ+VYZvE6x0W13UWnnkPM4sf0f7T2bHPPhjbY7lR" +
                                                    "mVk62qAzOv3Nn1+GDv1wJQdvAf40TLmNArexmb9DzCh+o4ivtbJXvZ61luQz" +
                                                    "Vf/m7ttZu5JGWb93Kv27f7zHbcoinRzh8OhHhVNHqsPLb3F9N/uZdl0ym8eh" +
                                                    "tXN1nzoZ/9XfUPi5HxVFUVC0eboXKwmWY1HolcwUeUNvmbGe2fdYRb7VYbca" +
                                                    "Lx7SjvXyjhsHGDNpNi71UE0lu+UaeEptqin1Uo0P8UGYqzTw9wL2epzHxE9R" +
                                                    "kW7IwwAnFmtZxUqSojwomEzmac5TOj+zMS2SiOFu7lStFcfc0d3jE1LnscV+" +
                                                    "GyDPUlRCNf0JhQwTxQOKuRkFbC1vJt1g7Dvx4QV6reU5C73NUwPIq3hp98/V" +
                                                    "PcsHt/yHslXn8cm75Ym1p66sWii+5Ud5Tkyz+uNMpdbMSJYaBBp6tScjnrVO" +
                                                    "PNmD5sATtOMZzFk63IC46WgTHI8bl+p9SL6+xF7dQPNWb8SVcmQvdNY5uxTG" +
                                                    "vVVZH0BW0y6emQgUz5nY8C2vu05jXQLdbSyhKOmoThsX6gaJydy2EgvjFuw2" +
                                                    "UVSR1ToBVPl/bvArluAWiqalCQKu7VG6EJSTPBBiQ8lydxYEMLs1y3A2iTKg" +
                                                    "r3sTYX4GJPnXYwo+Cev7sU88O7Fm3Y47zxzjWCyA787RUf61AR9PVnvhQHDe" +
                                                    "lLul9ipc3XS//FzJglRqlbNXpd1TeGyry1162+I65cVy9JM5Hy87PnGd1/6/" +
                                                    "ARFsFV/WDwAA");
}
