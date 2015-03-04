package org.sunflow.core;
public interface Filter {
    public float getSize();
    public float get(float x, float y);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVZe3BUVxk/u3mHkITwCrRNeYQ6QLsrjnSGx1RCmpTQBTJs" +
                                "SiUtXU7uPZtcuHvv5d6zyUJLp61aGBSsCEodGp0RplZ5jRarU6koMy1I0aF2" +
                                "tHSmoDM6Vlqc8oetii1+3zm7e+/e7C5JipnZL3fP43v8zvc6dw9dIWWOTeZa" +
                                "pr65Vzd5iKV4aIM+P8Q3W8wJLY/M76S2w9RWnTpOF4zFlBnH6j689kxffZCU" +
                                "d5Px1DBMTrlmGs5q5ph6P1MjpM4dbdNZwuGkPrKB9tNwkmt6OKI5fFGEjPFs" +
                                "5aQ5klEhDCqEQYWwUCHc4q6CTWOZkUy04g5qcGcTeZwEIqTcUlA9TqbnMrGo" +
                                "TRNpNp3CAuBQid/XgFFic8om07K2S5uHGLx3bnjPtx+p/3EJqesmdZoRRXUU" +
                                "UIKDkG5Sk2CJHmY7LarK1G4yzmBMjTJbo7q2RejdTRocrdegPGmzLEg4mLSY" +
                                "LWS6yNUoaJudVLhpZ82La0xXM9/K4jrtBVsnubZKC9txHAys1kAxO04VltlS" +
                                "ulEzVE5u9+/I2th8PyyArRUJxvvMrKhSg8IAaZBnp1OjNxzltmb0wtIyMwlS" +
                                "OJlakClibVFlI+1lMU4a/es65RSsqhJA4BZOJvqXCU5wSlN9p+Q5nysrF+96" +
                                "1FhmBIXOKlN01L8SNjX5Nq1mcWYzQ2FyY82cyLfopBPbg4TA4om+xXLNS49d" +
                                "XXJn08nTcs0tedas6tnAFB5TDvTUnr+1dfaCElSj0jIdDQ8/x3Lh/p3pmUUp" +
                                "CyJvUpYjToYykydXv7r2iR+y94KkuoOUK6aeTIAfjVPMhKXpzL6PGcymnKkd" +
                                "pIoZaquY7yAV8BzRDCZHV8XjDuMdpFQXQ+Wm+A4QxYEFQlQBz5oRNzPPFuV9" +
                                "4jllEUIq4EMC8JlK5F85Ek7uD/eZCRamCjU0wwyD7zJqK31hpphhhyYsHU7N" +
                                "SRpx3RwIO7YSNu3e7HfFtFm4XdPBdULoVNbNZZdC7esHAgEA9lZ/WOsQEctM" +
                                "XWV2TNmTXNp29UjsbDDr5mm7OZkMAkJpASEUEJICSCAg+E5AQfKwAOqNELSQ" +
                                "zmpmR9ctX799Rgl4iTVQikClRBQ1Zr7ARp9CIl7bf37y2ePfmbsg6A3tOk+y" +
                                "jDIuHWWcK7fLZgzG39nX+c29V7Y9JITCipn5BDQjbQW3gVwIOeUrpzdduHTx" +
                                "wJvBrKIlHPJnskfXFE4qaQ8kH6pwTqqyWWSIIbcVCkWRRg48tWdQXXVwngyY" +
                                "hlz3boPsffgPH78e2venM3mwr+KmdZfO+pnukTkGRYI3pKW1KeYKkaU6RBFQ" +
                                "IEZ3vPCjl/j5uQulyDmFa5l/42tPXZ7adU/f+iAJ5tYjlA5D1bizE6tItlrc" +
                                "7jPez/KFFYfO3HeHsjtIStLJKE/izd20yAsDCLUZVAoDAcWRahA6w+/Ltqkw" +
                                "FcqJK3fONHo8dmJrc5CUQkaFKsIpRD0k6Ca/8JwkuCjjdSiqDECIm3aC6jiV" +
                                "qQLVvM82B9wREWS14nkcHA9+SCN8atNpQvzH2fEW0gkyKMX6WwRtQjJdnG0Q" +
                                "H2cgmYmedYfr45DSdEireBjNDxgJU9XiGu3RGUbbf+tmzTv+/q566UE6jGRO" +
                                "584bM3DHpywlT5x95KMmwSagYEl1485dJsNvvMu5xbbpZtQj9eQbtz37Gn0O" +
                                "Mj5kWUfbwkTiJOlQQaUWCovnC7rAN7cYyTxOKnoZj8JmkDK7SB9mawkoDf3p" +
                                "2hXe2nBp4/53D0uf9xc632K2fc+O66Fde4KebmDmkILs3SM7AqHmWHnU1+Ev" +
                                "AJ9P8IM24ICsCA2t6bI0LVuXLAvPc3oxtYSI9r8d3fryD7ZuC6Yx+QxHfzQp" +
                                "t4ZAJgbm5vrdBPhMSfvdlNH6Xe4JBd0FLYIIPiuKnOMqJMs4KYFzFDuK6y5w" +
                                "yw/kJLfRkl1FSDS2AGYRW0pcWyCVQ5sJ+PqN8qq7tsjcQ1JRJFHJYyL07sL1" +
                                "UauQ1KqYHnh+mkFFC/ag4PlFJN1IHoYKAxiJeuEUTdTRJBQiT4u3Uxs895t/" +
                                "1j0pvT03SkSXn97q33fhrZLPjeHNXxcJvrSHOiKlVkLSc3AlJ9MK3xgELxkC" +
                                "Y254chPdkxPisweXAbHOBVEswGEtJ0TygxBTOhKx6PEL2+4WwVvXr0GDyNSu" +
                                "9CUmN2m5DcminItNXphiyrtHd56efnnNeNGxZhDxNh8rqDWk+VhGnT4YL6t4" +
                                "+1enJq0/X0KC7aQaIlZtp3hpgdYT6gVz+qDTSllfWCJCs2agEmg99pPAbFYB" +
                                "k9M2iSwdUx7b/8m5v2+9eKaElEMNwkJKbeh3oaEOFboqehk0d8HTvbALKlyt" +
                                "3A0Xlyz04AIN2dFsOeXkrkK8RQb2VV28bEGXyOylZtJQke1nfaU8aVneWeFM" +
                                "NaN3psehwg0DvKzt6cxIGkTY1LouiH2YdxJ6r/GtkZZoNNa1trMttqZldUfL" +
                                "0kibcFILJgNd+LguVeT0HtR0tZXaqgy8g9erZi6ZfGahCLyhII0SmEYBDB3g" +
                                "Yaw4psEMN0NaUsMh3WpzAX097xViyjNvfjB2zQevXBUx5m8EDdnxpJOiTSb7" +
                                "bwDpkPj8yZUP1+snrwGTbmCiwAXcWWXDfUPsz8RCfTYWSIrkTcI9SOJIvoTk" +
                                "aW9OHl4dkMm3SJ7/apG5nX55625UQ2We34Hka0h2QZ7vA0xaTVX2jPcgWSoV" +
                                "uhcKpZZ+d/OUp9B8OS8+0HDnb+naEhYXTdiWn01+cfHzgxfFjQYZfQPJbiR7" +
                                "kewbJnxFWoKAWBCQDob0uSLwfXeY8Al2M8SK/UgGkXwPKjjblKS6kw+3ih7T" +
                                "1Bk1hoedMB7J95EcQPL8TQLEa+/hInNHR47FISRHkBwDL+KmfOGUpxXxTAwf" +
                                "ip8geRHJT0cCxUhC6xdF5l4ZJh6uPOrG18tITiD5JfZ5Jtfim/N5SWm/qakj" +
                                "xOXXSE4hefX/hcvrReZ++2lwOYvkHJLfcVIlcWnRRQN6aoQwvJGF4fc3E4Z0" +
                                "/sh7WLpp9AreF4psAs9vcj2/A9/M2EkL2sC2lMIsTIiCxcVPA+NbSN5B8mdQ" +
                                "a4BqfDQI/iWL4F9HguDw7mNvI9kt+LxXOEVfEguujLiKybeHl5G8j+Qfrj2j" +
                                "BuHqTQLBGywf3cj0f4/S9A+R/AvJf26C6R+LsRSkKvn+FK8RjUN+apE/DyhH" +
                                "BusqJw8+8Efx9ir7Cr8qQirjSV33dNTe7rrcsllcE6pXyRZN3JMDAagT/pe4" +
                                "4NL4Dy0IELmslJMxnmVQXtNP3kUV0K3AInyshCaT5HSXlr/XnFnwxrUiKX+A" +
                                "iilHB5evfPTq3QdFZ1wG3euWLcgFLiQV8t2bYIovs6YX5JbhVb5s9rXaY1Wz" +
                                "Mm9PRI/a4PGxRo/f7Psf2crmF+wbAAA=");
}
