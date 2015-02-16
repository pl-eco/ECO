package org.sunflow.system;
import org.sunflow.system.UI.Module;
import org.sunflow.system.UI.PrintLevel;
public interface UserInterface {
    void print(Module m, PrintLevel level, String s);
    void taskStart(String s, int min, int max);
    void taskUpdate(int current);
    void taskStop();
    String jlc$CompilerVersion$jl = "2.5.0";
    long jlc$SourceLastModified$jl = 1163560750000L;
    String jlc$ClassType$jl = ("H4sIAAAAAAAAAKVXa2wVRRSe++i7pKVAefdFkUDLXVEwgZLIq8CFhda+gAKW" +
                               "6e7c26V7d5fd2fa2\nIEJUQIkPoiaaKPKDhIcoJmrQBBUCKOofMTEmJKKGRE" +
                               "2UH/5BjP7wzMx9bm+rQpM7nZ05z5lzvnPm\nzC2U59hohuKE6JBFnNDK9lZs" +
                               "O0RdqWPH6YClHuVKXlHrifWG6Uc+Gfk1laIyWXEkFVMsaaoUXtUU\nt1GDZe" +
                               "pDUd2kIRKnoZ36ooS8dfKiEQI3HT1Xsf94sNqP8mRUhg3DpJhqptGsk5hDUb" +
                               "m8Ew9gyaWa\nLsmaQ5tkNI4YbmylaTgUG9TZhfaigIzyLYXJpKhWTiqXQLlk" +
                               "YRvHJK5eauVqQcIEm1CsGURdnlIH\nnI3ZnGB2gq9tJDUIKWSbXeAOtwC8rk" +
                               "l5Lbwd4aoVONn10J5jpwKorBuVaUY7E6aAJxT0daPSGIn1\nEttZrqpE7Ubj" +
                               "DULUdmJrWNeGudZuVOFoUQNT1yZOG3FMfYARVjiuRWyuM7koo1KF+WS7CjXt" +
                               "1BlF\nNKKrya+8iI6j4HZl2m3h7mq2Dg4Wa2CYHcEKSbIE+zUDbrzay5HysX" +
                               "49EABrQYzQPjOlKmhgWEAV\n4i51bESldmprRhRI80wXtFA0bVSh7KwtrPTj" +
                               "KOmhaIqXrlVsAVURPwjGQtEkLxmXBLc0zXNLGffT\nUHn70MnXPl7GYzuoEk" +
                               "Vn9hcDU5WHqY1EiE0MhQjGO27opfAWd4YfISCe5CEWNMtnn+uUf/mkWtBM\n" +
                               "z0HT0ruTKLRH2Xikum33GhMFmBmFlulo7PKzPOfp0JrYaYpbkLWVKYlsM5Tc" +
                               "vND26ZZ9p8mvflQc\nRvmKqbsxiKPxihmzNJ3Ya4hBbEyJGkZFxFBX8v0wKo" +
                               "C5DCEvVlsiEYfQMArqfCnf5N9wRBEQwY6o\nCOaaETGTcwvTPj6PWwihAvgh" +
                               "H/zmI/GXzwaKHgxJjmtEdHNQcmxFMu1o+nvIoSQmdTrEDieDMMSC\nx6KoRe" +
                               "ozY0TCCjY0w5SiGqSrYs5XyQD7//9FxpmlFYM+H4M+bwrrQLXW1FVi9ygnbn" +
                               "6xp3n904dE\neLCQTvhIUQ1oCiU0hYSmUJYm5PNxBROZRnFDcL79kKmAaaVz" +
                               "27ev23GoLgChYQ0G2enEeepMS34A\no8cynqR3nsi//9vzJVe4Rcl8LstAzH" +
                               "ZCRXSMT+vtsAmB9e9eaX3x5VsHt3KlQmuAokLcC6iBFUpR\nUSr9KaCr26tr" +
                               "ygirZo6WTBwIDm7+vfQAvrxdhHxFdoA2A4j/PHSJzFn67I85TrSImtZ8nQwQ" +
                               "PUNn\nkKmES05oa1bMDRxnwrwWKJBlz5x68xy91rBEqJw3eh3yMs5bcmy4es" +
                               "nZw37kz10fmBVQoYqZhFZW\nVFK4X+05BK/o8QPTHwn0aVf9HAoZrOSA0Gym" +
                               "pszjAKVgj2sb7GDZSikorfNGqm0qRIXCkNZ7fGpZ\nYBPqOuJHQcBGqAfcJ4" +
                               "DaKq/yLDhrSoYSU1Ugo5KIacewzraSeF5M+2xzML3CU6iczyewa2LhsRB+\n" +
                               "FYmE5//Z7iSLjZUi5Tj9TD7WiAD0s3kdG+pZdN2XDloAJh3AkV1EfacRM1Ut" +
                               "ouFenbD0+bts9oL3\nf3uuXESRDivJm2n8dwHp9akr0L4vH/2jiovxKawwph" +
                               "MpTSbyaUJa8nLbxkPMjvj+r2e++hl+HXAb\nsNLRhgmHv4DILe7nZIpm5IKK" +
                               "cP0GU3V1kiTKiSfh+laomVROJQUQlnM7WEUNiYrKj3YJ317Ex8Xs\n7BMZy7" +
                               "5XsGEBRXkWEwauTMls92wtBmVjgAfCzQN1H13tfOOgyKW5Y/R0mVw9yuPf/9" +
                               "AfeP5ir+Dz\nlk4P8ZGq4z+9e7Ntorg80V/MGlHiM3lEj8F9KbNYmNSOpYFT" +
                               "X26oPbO37Qa3iPHNoSg4YGoqMDeO\n4laOnq9H2XM6Wufu+vxDbmwJzmweM1" +
                               "F3A7aEfeVseIDZONmL+2ux0wd0Cy9s3FauX/gLJHaDRAV6\nLafFhnITz8Ls" +
                               "BHVewfWLlyp3XAsg/2pUrJtYXY1ZgwdlGjKSOH1QqeLWw8t40pUPFrKRXzzK" +
                               "Bm72\n0ZCdriH4VSXStepu0jU74jxBPyJM+cYqS7CFKQpALLLpVq5r8xjx28" +
                               "OGdlYgsNMPHbTN+dZyWpkN\nHUJoy10dQw385iSOYc69H4OPE/jSnmljeNbP" +
                               "Bgj/YuZZpwVPKuJxjdyLa2zSmHCt8d5dy7TcHWNv\nkA1Q0AvFfZmWx6dd/9" +
                               "WnOEXjsnoqhsNTRry5xDtBka/v3nZb/uZPXvxSvXwJNNQRV9czamtmnc23\n" +
                               "bBLRuNklIoEt/m8vvF1GIjK0RWLCzXxMkO6nqCSDlKKCxCyT6EkIeCBi06es" +
                               "HDkiXgLZ3RbzdlYW\nWPGnbbLncMXjtkfZ/NbWmvjhjhd4I5MHj+LhYf6KgU" +
                               "eZKOSpvqV2VGlJWV+hd852nX97cRI3OZ5N\nzAiWrHiTxe4Y1wm9Uu6K3Byz" +
                               "KK+hwx9Mfm/piaM3/Lwr/Qckg9c2kRAAAA==");
}
