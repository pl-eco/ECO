package org.sunflow.core;
public interface CameraLens extends RenderObject {
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time);
    String jlc$CompilerVersion$jl = "2.5.0";
    long jlc$SourceLastModified$jl = 1170608524000L;
    String jlc$ClassType$jl = ("H4sIAAAAAAAAAJVXW2wUVRg+e+m9ZttSCrS0pQU0UNjRCBopRkotUBhg3UKB" +
                               "ApbTmbPbobMzw8zZ\ndlsIQkwAJV6ImmiiyAMJF1FM1KAJKgRQ1BcxMSYkoo" +
                               "ZETZQHXxCjD/7nnJ3d2WmLcZOdPXPOfzn/\n7fv/PXMLFTk2mqk4UTpqESfa" +
                               "2RPDtkPUTh07zgbY6leuFJXFTqwxzCAKyCioqRRFZMWRVEyxpKlS\n9+PtGR" +
                               "u1WaY+mtRNGiUZGt2pL87KWy0vHidw09FzNfuPh5uDqEhGEWwYJsVUM40una" +
                               "QciqrknXgY\nS2mq6ZKsObRdRvcQI53qNA2HYoM6u9BeFJJRsaUwmRS1yK5y" +
                               "CZRLFrZxSuLqpRhXCxKm2IRizSBq\nR04dcC4o5IRrZ/ni46lBSCk77AVz+A" +
                               "3A6lk5q4W140y1Qid7H9pz7FQIRfpQRDN6mDAFLKGgrw9V\npkhqgNhOh6oS" +
                               "tQ9VG4SoPcTWsK6Nca19qMbRkgamaZs4ceKY+jAjrHHSFrG5TndTRpUKs8lO" +
                               "K9S0\ncz5KaERX3beihI6TYHZd3mxh7gq2DwaWa3AxO4EV4rKEhzQDIt7s58" +
                               "jZOGcNEABrSYrQQTOnKmxg\n2EA1IpY6NpJSD7U1IwmkRWYatFBUP6lQ5msL" +
                               "K0M4Sfopmu6ni4kjoCrjjmAsFE31k3FJEKV6X5Q8\n8Wmru33o5OufLOO5HV" +
                               "aJorP7lwNTk48pThLEJoZCBOOddPTl7i3pmUGEgHiqj1jQdMw9t1H+9dNm\n" +
                               "QdMwAc36gZ1Eof3KuiPN8d0rTRRi1yi1TEdjwS+wnJdDLHvSnrGgautyEtlh" +
                               "1D28EP9sy77T5Lcg\nKu9GxYqpp1OQR9WKmbI0ndgriUFsTInajcqIoXby82" +
                               "5UAmsZUl7srk8kHEK7UVjnW8UmfwcXJUAE\nc1EZrDUjYbprC9NBvs5YCKES" +
                               "+KIAfGci8eEbFEWjkpM2Ero5Ijm2Ipl2MveumDaROiFpbCwTw4my\nvLEoWi" +
                               "0NmikiYQUbmmFKSQ0qVTEXqmSY/f4vaRl2v5qRQIABnr9wdcj5VaauErtfOX" +
                               "Hzyz1da545\nJJKCJXLWMooaQEk0qyTKlETzSlAgwGXXMmUiJODQIShNALHK" +
                               "eT3bV+841BqCXLBGwswdGV4r9e4L\nMPouxavyztPF9393vuIKv4xbwBEPRP" +
                               "YQKtKhOq93g00I7H//auylV24d3MqVCq0hikrxAMAEVihF\nZbl6pwCn6QFd" +
                               "U7y3CvD1NIoax5kdhyQhtshfdvfGyWqM48PBzX9UHsCXt4tKqCnM2y7A9l9G" +
                               "L5H7\nlj730wQuL6OmtVAnw0QvuBmohATIautSzLUcfrp5i1Cg+J499dY5eq" +
                               "1tiVA5f/L25Gecv+TYWPOS\ns4eDKDhx22C3gMZVziTEWK/JtYNmnxP8oquH" +
                               "G54IDWpXgxwhGdpMgKyFTO1ed4BSuE/aNphj2U4l\nKG31p7JtKkSFfpHXe3" +
                               "xGJLQJ9R4JojBAJrQJbhMgcJNfeQHKtbsJx1SVyKgiYdoprLMjF+bL6aBt\n" +
                               "juR3eI1V8fUUCFMFS+1d8G3I4kCD2NgLiMmedaImOX0jf84SaRpk61b2mMOy" +
                               "6958agNe6ZBzLBBz\nNhopU9USGh7QCSuyfyJzH/jg9+erRBbpsONGZsF/C8" +
                               "jvz1iO9n315J9NXExAYf0yX255MlF1U/KS\nO2wbj7J7ZPZ/0/ja5/gNgHOA" +
                               "UEcbIwIVuWklwDTdO2/ZWgpwe5i7/OaB1o+vbnzzoMjaeXcZqrxc\n/cpTP/" +
                               "w4FHrh4oDg8/cuH/GRpuM/v3czXivcJBr87HE91ssjmjwPVsRiAWm5mwZOfb" +
                               "mt5cze+A1+\nI8Y3j7JkMjEPSQeP9aOWAJvlFIUAiNiyy39QrJoASzzZV/EH" +
                               "F7aUHz/Mn+0spbIgyt5l9ngQOJOE\nxvGoC2G14yEMDiEzJvHxBBNgv7LndL" +
                               "I1veuLj7jnKrB3lPRC8lpsCWdVscci5rBp/qawCjuDQLfo\nwrptVfqFv0Fi" +
                               "H0hUYPJy1tsArZkCQM9SF5Vcv3ipbse1EAquQOXgTXUFZuMeNG0oROIMQgfL" +
                               "WI8t\n47VWNVLKntwxqKDXZCgqz/ctlsXTxw2yYvhS5Ou7t92Wv/2LQ0duQK" +
                               "qAKSWR1nUPMnlRqtiySULj\nHqgQfrD4zxYY7v1hoCjMfvj9NguybRRVeMgo" +
                               "lIxYeYlgLgwBEVvusNwgV3GPsWkz6rYmr93M0tkF\n8eb/FVy0Tot/C/3K5r" +
                               "e3zsoc3vAibwFF8C9jbIyPhTDlCgjMIX7LpNJcWV+jd8/2nn/nEbcOeErU\n" +
                               "enCv3jsNxMQpX/dMGEfoMhNjWVfKohx9xj6c9v7SE0dvBHnX/xcUEFY64g0A" +
                               "AA==");
}
