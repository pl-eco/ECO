package org.sunflow.system;
import org.sunflow.system.UI.Module;
import org.sunflow.system.UI.PrintLevel;
public interface UserInterface {
    void print(Module m, PrintLevel level, String s);
    void taskStart(String s, int min, int max);
    void taskUpdate(int current);
    void taskStop();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0ZC2wUx3Xuzn9sn7GDIQQcPiaI312pQiQgKhjHgOkBFja0" +
                                "cdoc49053+K93WV3zj47gSapWlAqaNSQNKlSt1VBCflAhEpJWhERVQqQlEq0" +
                                "NCmpStKPFJqWNvSTqqUNfW9273bvfHfYjltL+zw78+b95v1m7/krpNQyySJD" +
                                "Vwd7VZ2HWIqHdqjLQnzQYFZoQ2RZBzUtJreq1LK6YC4qzXkx+OG1R+J1flLW" +
                                "TRqopumcckXXrC3M0tV+JkdI0J1tU1nC4qQusoP203CSK2o4olh8ZYRM8mzl" +
                                "pDmSFiEMIoRBhLAQIdziYsGmGqYlE624g2rc2kl2E1+ElBkSisfJ7GwiBjVp" +
                                "wiHTITQAChX4vg2UEptTJpmV0d3WeYTCjy0KH/j6vXXHAiTYTYKK1oniSCAE" +
                                "BybdpDrBEj3MtFpkmcndZLLGmNzJTIWqypCQu5vUW0qvRnnSZBkj4WTSYKbg" +
                                "6VquWkLdzKTEdTOjXkxhqpx+K42ptBd0bXR1tTVci/OgYJUCgpkxKrH0lpI+" +
                                "RZM5uTV3R0bH5k8DAmwtTzAe1zOsSjQKE6TePjuVar3hTm4qWi+glupJ4MLJ" +
                                "9IJE0dYGlfpoL4tyMi0Xr8NeAqxKYQjcwsmUXDRBCU5pes4pec7nyqY799+n" +
                                "rdf8QmaZSSrKXwGbmnI2bWExZjJNYvbG6oWRx2njyb1+QgB5Sg6yjXPi/qur" +
                                "FzedOmPj3JIHZ3PPDibxqHSwp/b8jNYFywMoRoWhWwoefpbmwv07nJWVKQMi" +
                                "rzFDERdD6cVTW167+4Fn2R/8pKqdlEm6mkyAH02W9IShqMxcxzRmUs7kdlLJ" +
                                "NLlVrLeTchhHFI3Zs5tjMYvxdlKiiqkyXbyDiWJAAk1UDmNFi+npsUF5XIxT" +
                                "BiGkHB7ig2cJsf/KEHDSG47rCRamEtUUTQ+D7zJqSvEwk/SoyQw93Na6OdwD" +
                                "Vo4nqNlnha2kFlP1gaiUtLieCFumFNbN3vR02Bq0OEuEt1rMbE87bggdzvj/" +
                                "sUqh1nUDPh8cyIzcdKAC1npdlZkZlQ4k17RdPRJ9w58JD8denMwCTiGHU8jm" +
                                "FMriRHw+weAm5GifNpxVH0Q95MPqBZ2f37B975wAuJkxUIKWTokwnJZ+gY05" +
                                "komAX/vyqSePf2PRcr83NwQ92baTcdvTJrt8u0zGYP5XT3Q8+tiVPfcIpoAx" +
                                "Nx+DZoSt4HeQTCEpfenMzovvXDp4wZ8RNMAhASd7VEXipIL2QPaiEuekMpOG" +
                                "Rigys1Asizx08KEDw/LmQ0vtiKvPjo82SP8vvPmfH4eeePdsnkOo5LqxRGX9" +
                                "TPXwrEGW4DIOtzZJ3yjSXLuoIhIE+cOHnzvBzy9aYbNcWLgY5m48/dD707s+" +
                                "Fd/uJ/7sgobcYaoKd3ZgGcqUm1tzlM8leXjj82fX3SZ9zU8CTjbLk7mzN630" +
                                "mgGYmgxKjYYGxZkqYDon16lNXWIy1COX78JZ9Hj05K5mPymBlAxliFNIG5Dh" +
                                "m3KZZ2XRlWmvQ1alYISYbiaoikvpMlLF46Y+4M6IaKsV48lwPMItboen3skz" +
                                "4j+uNhgIb7KjU+DfImATgtnibP04nINgLnrWba6PQ05UIS/jYTRv1RK6rMQU" +
                                "2qMyjLZ/B+ctPf7H/XW2B6kwkz6dxTcm4M7fvIY88Ma9/2gSZHwS1mQ37lw0" +
                                "O/waXMotpkkHUY7Ugz+d+eRp+k0oGZCmLWWIicwbsONK6DqFkxn5Mkt780Zd" +
                                "TqosjZQ3/bQ3d0C55pFMQABinZADi3nILubCvCvE8jIBl6P9nWjF9zUIlnJS" +
                                "aiAxUGVBkW7RVBJQwPqdChveVf9O31OXX7ADK7cc5yCzvQcevh7af8Dv6Vnm" +
                                "jmgbvHvsvkUIWWP703X488HzET6oAU7Ydau+1SmeszLV0zDQaWYXE0uwWPve" +
                                "0V0/fGbXHr9jkfmclPTrimyMsJeYWJTt2yF4mhzfbhqvb2cfj+Mh+LpKTN6F" +
                                "YL2NtoGTABwUDrsE/c4ih/tZBJswc1KrDzpbU+xbNzrNZsEz39Fs/sRo5hMI" +
                                "Plf47UWE70FwD4cMA8JvNWQ40zFI3wDPYkf6xRMjvVe4HUXWRIcK7W6FbXXd" +
                                "GIXYwpPzu3aj26Db3WhIXIjAvYuoEXDVgAoO1xPw+GL69BdZcwRFYKTyZBpb" +
                                "qmJyQHqJKRoVhuGCZhLBAIJBMFQv46JNsIrW584k9B+eq8E+Zfjc638PPmjn" +
                                "n+y8JW6HztbcfRd/EfjkJN78VVHXS3qoJfyqAmqdhZiQbgvfNAUtOylNuuHJ" +
                                "TXFPTrDPHFzaiEHXiAIBp7+clbTyGyEqtSeinccv7rlDpNNgvwIXCyZ3OZff" +
                                "7Frl9qErsy7Eec0UlS4f3Xdm9vvbGsRNJ20Rb8+5kRojes711IrDfGn526/+" +
                                "qHH7+QDxryVVqk7ltRQvu3BlgTaBWXHotFPGqtUiKqsHKgDWYUIAYvMKqOzo" +
                                "JIpzVLr/qY/O/X7XpbMBUgatB/ZP1IR7ElzEQoU+MXgJNHfB6C7YBY1Nrb0b" +
                                "amTG9OAC9ZnZTBfFyZJCtEVNzGm28JIOZZqZa/SkJiPZT+R0cEnD8K4KZ6oe" +
                                "vzPthsZmFMbL6E7cTgz8odZ1QWy/vYvQcje0Rlo6O6Ndd3e0Rbe1bGlvWRNp" +
                                "E05qwKKvC4dDqSKn9xlFlVupKduBd+h65dzVU8+uEIE30kjjNMw0YRg6wMPY" +
                                "A+ga09wMadgSjrikNBeQ1/M9Kio9cuGDmm0fvHJVxFhu//8Vu9F1kqJJpuZe" +
                                "/JyQuP3Ups/VqaeuAZFuICJJzLI2m3DfFPv3ObFQl4kFkiJ5k/AXEHwRwbcR" +
                                "fNebk0dXB+zkWyTPP1Nk7dlcfkM3Kp92nn8awWEEz0Gej4NNWnVZREKX2Pot" +
                                "T1X5Tl5jwKUqf9veljC4aLSHXpr6vTufHr4kbq1I6Iigj+AogmOjtNXoGpch" +
                                "QeJEEVu9PEpbCXJzBMb3EbyE4AdQrtnOJFWtfK1feY+uq4xqo7OdUB7BSQSv" +
                                "IHh1ggzi1fd0kbWzY7fFawjOIHgdeyjdvsjg+6oxqn1ObELwk7GoPZaYuVBk" +
                                "7c1R6u7y2+0Gzs8Q/BzBW9jA6VyJDeLbujHa4G2xCcEv/1c2+HWRtd9+HBu8" +
                                "i+A3CH4HdxjbBi2qOh4zvJcxw+WJNIOTF/KFaomqO9fvPxXZBE1gk1uBxedE" +
                                "M2lAL9eWkpiBiU6Q+OvHMeMVBH9B8CGINUAVPh4L/jNjwX+NxYJFcojfRfgz" +
                                "AnEl9JHCqfdvAsE/5lK0W6Bdx81Ixxdw9RmvEXxlE2QET7D4qm+keu34VPdN" +
                                "QlCDIDgBqteLuRQnNVmfwPFKMG3Ez232T0TSkeFgxdThrW+JD5CZn3EqI6Qi" +
                                "llRVT3fs7ZTLDJPFFKFopd1uCa19jZzUj/wiBmnSHqAyvik26s2cTPKgQgV1" +
                                "Rl6kGZwEAAmHM6FpJFndopHbO84teIPamLR/iIxKR4c3bLrv6h2HRKdbCt3o" +
                                "0BBSgQtGuf0JVRDFb5KzC1JL0ypbv+Ba7YuV89Lfp0TPWe9xt2mefHvsv+H9" +
                                "QgH0HQAA");
}
