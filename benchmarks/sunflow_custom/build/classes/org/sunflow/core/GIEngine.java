package org.sunflow.core;
import org.sunflow.image.Color;
public interface GIEngine {
    public Color getGlobalRadiance(ShadingState state);
    public boolean init(Scene scene);
    public Color getIrradiance(ShadingState state, Color diffuseReflectance);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1170609328000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVZf3AU1R1/d/kdQn5CgggRQrACelc6xRmIYwkxQOgBaRKo" +
                                "ptXjZfddbsne7rL7LjlQGHSmA+OM1NFo1WJmdGDEll/Tylhr6cRxpoLoVFqn" +
                                "FduC/7RalI50prUtLfT7fXt3u7e5u/wAM3Pf7L0f3x+f9/319g5fIkWWSZYY" +
                                "urq9X9V5gCV4YKu6LMC3G8wKrAst66SmxeQ2lVpWD4yFpabjVf+88ni02k+K" +
                                "e0kd1TSdU67omtXFLF0dZHKIVDmj7SqLWZxUh7bSQRqMc0UNhhSLt4TINNdW" +
                                "TppDKRWCoEIQVAgKFYKtzirYNJ1p8Vgb7qAat7aRXcQXIsWGhOpxMj+TiUFN" +
                                "Gkuy6RQWAIdS/L4ZjBKbEyaZl7bdtnmMwU8tCQ7/4IHqnxSQql5SpWjdqI4E" +
                                "SnAQ0ksqYizWx0yrVZaZ3EtqNMbkbmYqVFV2CL17Sa2l9GuUx02WBgkH4wYz" +
                                "hUwHuQoJbTPjEtfNtHkRhaly6ltRRKX9YGu9Y6tt4WocBwPLFVDMjFCJpbYU" +
                                "DiiazMkt3h1pG5u/CQtga0mM8aieFlWoURggtfbZqVTrD3ZzU9H6YWmRHgcp" +
                                "nMzOyRSxNqg0QPtZmJNZ3nWd9hSsKhNA4BZOZnqXCU5wSrM9p+Q6n0sb7tr3" +
                                "oLZW8wudZSapqH8pbGr0bOpiEWYyTWL2xorFoadp/cm9fkJg8UzPYnvNqw9d" +
                                "Xnl74+gpe83NWdZs7NvKJB6WDvRVnp3Ttmh5AapRauiWgoefYblw/87kTEvC" +
                                "gMirT3PEyUBqcrTrV/ft/hH71E/KO0ixpKvxGPhRjaTHDEVl5hqmMZNyJneQ" +
                                "MqbJbWK+g5TAc0jRmD26MRKxGO8ghaoYKtbFd4AoAiwQohJ4VrSInno2KI+K" +
                                "54RBCCmBD/HBZxGx/8qRcHJvcJMF7h6kEtUUTQ+C8zJqStEgk/RwH6AbjVFz" +
                                "wApKcYvrsaAV1yKqPhS0TCmom/3p75JusuCajnatH7QLoIcZXyLvBNpVPeTz" +
                                "AeRzvAGvQqys1VWZmWFpOL6q/fLR8Bl/OgCSiHByE4gIJEUEUEQgJYL4fILz" +
                                "DBRlHyQcwwAENKS6ikXd96/bsrepADzIGCpEEBMiwmalvsBGj0oille/Nvrs" +
                                "ieeWLPe7w77KlUi7GbedqMaR22MyBuN/eqbzyacu7fmOEAorFmQT0Iy0DVwK" +
                                "8iTkm++d2nbuwvkD7/vTihZwyK3xPlWROCmlfZCYqMQ5KUtnmDGGzM0VpiLF" +
                                "HHhkeETeeHCpHUy1ma7fDpn9yO/+907gmY9OZ0G/jOvGHSobZKpLZgWKBN9I" +
                                "SmuX9PUig3WIAiFB/D768o9f5WeXrLBFLs5d57wb33rk4uyeu6Nb/MSfWatQ" +
                                "OgyV485OrDDpSnKLx3gvy5fXHz695lbpCT8pSCaqLEk5c1OLGwYQajKoIhoC" +
                                "iiPlILTJ682mLjEZSo0jd/E8eiJ8cmeznxRCtoUKwyk4LSTvRq/wjATZkvI6" +
                                "FFUEIER0M0ZVnEpViHIeNfUhZ0SEWaV4roHjwQ9pgs+MZAoR/3G2zkA6ww5L" +
                                "sf5mQRuRzBdn68fHJiQL0LNudXwc0p0KKRcPo3mTFtNlJaLQPpVhtP23auHS" +
                                "E5/tq7Y9SIWR1OncPj4DZ/ymVWT3mQe+aBRsfBKWWyfunGV2+NU5nFtNk25H" +
                                "PRIP/2bus2/R56EaQAa2lB1MJFWfMM0nbJ3JydwxKaU7SmWosNhjMIHOCrF2" +
                                "maDLEb5ksOH3u5Es5aSmn/E1qt5H1S7YjYeeEtDgFqDEoOCi8bppjOEnBpak" +
                                "j64OBxvgMyd5dHOmenSZ6nsAqB8LgAS1TTBfm8fyEJI2jvVL4XAEi/I0sKYS" +
                                "g5o6mCz6wZ21Fwb2f3LETgjeDsGzmO0dfvRaYN+w39VGLRjTybj32K2U0HG6" +
                                "DeY1+PPB5yp+0AAcsEtpbVuyns9LF3TDQGefn08tIWL1x8d2vn5o5x5/EpCv" +
                                "cFLSp+sqo9q4ZyvCshE+tyXP9rYbc7Z+Z0ELkpWCz/15jjGM5F5OpoMDd0Do" +
                                "2M4r9ua3QmCYHdR6p1u1W7OAuB0AsHmsKnCsgpoHvTpg7TXPrbiSZ27AVhSJ" +
                                "lEg5erXIEahVwNYqnx4cEq+iUdHH9gueoi/bigRqRimgJQqrlbeidcehYrv6" +
                                "5MeUkXff/kfVw7bnZ0aMuColt3r3nfug4GvTePP3RSUs7KOWOKBSqA4WruRk" +
                                "Xu5rl+Blh8O0cU9upnNyQnz64FIgVjkgigU4vD0jXLKDEJY6YuHuE+f23CkC" +
                                "uWpQgS6byT3Jm2Bmdnc6t5aM22FWmMLSJ8ceOzX/4uY60fanEHF3aeupMaZL" +
                                "W0utKIwXlXz4xpv1W84WEP9qUq7qVF5N8eYH/TsUVmZFoSlNGN9YKYLUN1SK" +
                                "0SWyp0kW5jA5aZMoZ2Hpof1X3/3rzvOnC0gxFGvsOKgJlwa4lQRy3bfdDJp7" +
                                "4Oke2AWtQKW9G2pTGnpwgdr0aLrv4OSOXLxFNva0J3hjheTPzFV6XJOR7Vc9" +
                                "PU/cMNyzwpkqpu5Mu6AVmAB4aduTOZLUirCpdFwQG1b3JDSpdW2h1u7ucM99" +
                                "ne3hza1dHa2rQu3CSQ2Y9PXgYyyR5/S+rahyGzVlO/AOXitbsLLh9AoReGNB" +
                                "miIwswQwdIgHsfroGtOcDGnYGo5p65tz6Ot6OROWHn//8+mbP//lZRFj3o55" +
                                "l90aJpOiSRq8V6VkSHx9dMN3q9XRK8CkF5hI0MpYG024mon9u5OxQEQsVAv1" +
                                "SNYkbCIZRPIkkqfdOXlidcBOvnny/A/zzD3vlRcbr5raef45JPuRjECejwIm" +
                                "bbpsd3MbkHTZCvVwUqAkX4A94So0w1nxgZtJ9t63PWZw0a3u+FnDK3e9NHJe" +
                                "XP2Q0QtIXkRyEMmhCcI3fuNnOxjSI3ngOzZB+AS7JrHiMJKjSI5DBWfb4tS+" +
                                "KH5rYiAJK5H8VGxC8soNstxt2M/zzP1i8ka/huR1JCfBXbhuv57L0nO4JiYO" +
                                "xSiSN5C8ORkoJhNDp/PMnZkgHo68bU4gnULyNpJ3sKHTuRLZni2MCgd1RZ4k" +
                                "Lr9G8h6S335ZuJzLM/eH68HlAyQfIvkjJ2U2Lq2q6DTfmyQMF9IwfHQjYXDf" +
                                "EL2HpeqabcZf8l8rGx3P78B3VWbcgH6vPSExAzOfYPHp9cD4ZyQXkfwN1Bqi" +
                                "Cp8KgpfTCP59MghO7Ar2MZIXBZ9/5c7Fn4kF/5l0udomln2B5N9Irjj2TBmE" +
                                "qzcIBFew+ArHMd1XPDXTfegWviIkJddvuq9cjCUgiafeKeONYdaYn6bsn1Ok" +
                                "oyNVpQ0jm34v3uilf/IoC5HSSFxVXc2zu5EuNkwWUYSNZXY3Jq7EvkqoFN7X" +
                                "MODU+A9t8E23l9VwMs21jJOS5JN70QxoTGARPs6EfpJkNJKGt61ckPNytT5u" +
                                "/2AXlo6NrNvw4OU7D4omuAga1R07kAvcPUrs95GCKb7gm5+TW4pX8dpFVyqP" +
                                "ly1MvTQR7Wity8tmudLsof8D83Vs7xwdAAA=");
}
