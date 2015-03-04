package org.sunflow.image;
public class RegularSpectralCurve extends SpectralCurve {
    private final float[] spectrum;
    private final float lambdaMin;
    private final float lambdaMax;
    private final float delta;
    private final float invDelta;
    public RegularSpectralCurve(float[] spectrum, float lambdaMin, float lambdaMax) {
        super(
          );
        this.
          lambdaMin =
          lambdaMin;
        this.
          lambdaMax =
          lambdaMax;
        this.
          spectrum =
          spectrum;
        delta =
          (lambdaMax -
             lambdaMin) /
            (spectrum.
               length -
               1);
        invDelta =
          1 /
            delta;
    }
    public float sample(float lambda) { if (lambda < lambdaMin ||
                                              lambda >
                                              lambdaMax) return 0;
                                        float x = (lambda - lambdaMin) *
                                          invDelta;
                                        int b0 = (int) x;
                                        int b1 = Math.min(b0 + 1,
                                                          spectrum.
                                                            length -
                                                            1);
                                        float dx = x - b0;
                                        return (1 - dx) * spectrum[b0] +
                                          dx *
                                          spectrum[b1]; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YbWwcRxmeO9vnjzjx2WkSExI7sZ2oTtpbGtSg1FUgPezG" +
                                                    "4VKbOAnqFXKd25u723j2I7Nz9sWtSROpJFQQIXBLiop/oJTSkiYVIioIVcof" +
                                                    "aKsipFYIxA8a4A8VIRL5QakoUN6Z2du927szH0KctHNzM+/3+87zzt6lm6jN" +
                                                    "ZWinY9OTBWrzBCnzxHF6d4KfdIibOJC6exozl+SSFLvuYVjL6EMv9bz7/leK" +
                                                    "8SiKpdFabFk2x9ywLfcQcW06R3Ip1BOsjlNiuhzFU8fxHNZK3KBaynD5WAqt" +
                                                    "qmLlaCRVMUEDEzQwQZMmaPsCKmBaTaySmRQc2OLuCfR5FEmhmKML8zjaWivE" +
                                                    "wQybnphp6QFI6BC/j4JTkrnM0Bbfd+VzncNP7tSWvn4s/r0W1JNGPYY1I8zR" +
                                                    "wQgOStKo2yRmljB3Xy5HcmnUaxGSmyHMwNRYkHanUZ9rFCzMS4z4QRKLJYcw" +
                                                    "qTOIXLcufGMlndvMdy9vEJqr/GrLU1wAX9cHvioPJ8Q6ONhlgGEsj3VSYWmd" +
                                                    "NawcR4NhDt/HkU8BAbC2m4QXbV9Vq4VhAfWp3FFsFbQZzgyrAKRtdgm0cLSx" +
                                                    "qVARawfrs7hAMhz1h+mm1RZQdcpACBaO1oXJpCTI0sZQlqryc/OBe88/Yu23" +
                                                    "otLmHNGpsL8DmAZCTIdInjBi6UQxdu9IPYXXv3IuihAQrwsRK5qXH731iTsG" +
                                                    "rr2maD7cgGYqe5zoPKNfzK55c1NydE+LMKPDsV1DJL/Gc1n+097OWNmBk7fe" +
                                                    "lyg2E5XNa4d+8uBjL5AbUdQ1iWK6TUsm1FGvbpuOQQm7n1iEYU5yk6iTWLmk" +
                                                    "3J9E7TBPGRZRq1P5vEv4JGqlcilmy98QojyIECFqh7lh5e3K3MG8KOdlByHU" +
                                                    "Dg/qhqcFqY/85sjUirZJNKxjy7BsDWqXYKYXNaLbGUYcWxtPTmlZiHLRxGzW" +
                                                    "1dySlaf2fEYvudw2NZfpms0KlWXNMKEKQEqhRDGbcSCUDNNkic2RhCg75/+t" +
                                                    "sCwiEJ+PRCA5m8LQQOFU7bdpjrCMvlS6b/zW5cwbUf+oeLHjaDvoS3j6ElJf" +
                                                    "opE+FIlINbcJvSr/kL1ZwAFAyO7Rmc8dePjcEES97My3QugF6RA47RkzrtvJ" +
                                                    "ACwmJSTqULH933robOK95z6uKlZrjuwNudG1C/Onj576SBRFayFaOAdLXYJ9" +
                                                    "WgCrD6Aj4aPZSG7P2XfevfLUoh0c0hrM97CjnlOc/aFwGpitkxygaSB+xxZ8" +
                                                    "NfPK4kgUtQKgAIhyDEUP+DQQ1lGDAWMVPBW+tIHDeZuZmIqtCgh28SKz54MV" +
                                                    "WR9r5LwXkrJKHIpBeDq8UyK/xe5aR4y3qXoSWQ55IfF64ofXnr76jZ17otXQ" +
                                                    "3lPVLGcIV0DRGxTJYUYIrP/6wvTXnrx59iFZIUAx3EjBiBiTABuQMgjr46+d" +
                                                    "+NX1ty/+PBpUFYf+WcpSQy+DjO2BFgAVCsUqcj9yxDLtnJE3cJYSUZx/69l2" +
                                                    "19U/no+rbFJYqRTDHf9aQLD+ofvQY28c+8uAFBPRRVMLPA/IVADWBpL3MYZP" +
                                                    "CjvKp9/a/PSr+JuAuYBzrrFAJHS1SM9agGlb8/KXQhTIL397+Genlod/C3ak" +
                                                    "UYfhwvVgHys06DpVPH+6dP3GW6s3X5YV15rFrjz+XeF2Xd+Na5qsrI5uR4R+" +
                                                    "dIU7GDNMaAtzXt/SFvuuzz7zzovqhIebXIiYnFt64oPE+aVo1U1guK4ZV/Oo" +
                                                    "24C0bLWq8w/gE4HnH+IR9S0WVDfoS3otaYvfkxzpztaVzJIqJn5/ZfFH31k8" +
                                                    "q9zoq22E43DPe/EXf/9p4sJvXm+AsHBUbKwugAlRxD5ArhDFCZGXKpz86xTN" +
                                                    "nvnde1J6HdI1CGyIP61demZjcu8NyR9AjuAeLNf3DqipgHfXC+afo0OxH0dR" +
                                                    "exrFde8+exTTkjjYaagat3LJhTtvzX7tfUxdPsZ8SN0UzmyV2jDYBRGFuaCW" +
                                                    "BazwzSlHkMSvpKQekuM2MdwuQx0V01GRB8PCtAwYQolV4EVJ91ExfKws53s4" +
                                                    "aoEjIKa7nLKfsKiSIn+v497ZFh7Bxc62iICJyp7qjoad8C/VsFlukPrNNb3x" +
                                                    "oDxlQcifeP67L/M3d96jqm1H8zIJM7565g8bD+8tPvwfdMTBUO7DIp8/eOn1" +
                                                    "+7frX42iFj9zdbfzWqax2nx1MQKvE9bhmqwNOPJrlxhGpDE7/DAhGSYkCT6z" +
                                                    "wt6DYjgCidVFHlTaILaDjWF93HS4BOKFH2z4/r3PLb8t+0pZ9r/dcpBCtRUU" +
                                                    "HhPDFqduT2W4X/5qk/O9ftPtExTD8LR6Tbc13HS96sVNqpejdocZc4BWYnEC" +
                                                    "6rfDlTezkulHMO400nqnMMfT2tZEa2GFM+Mr7KTYzObwQcPyg/Xfa5RvPEYg" +
                                                    "VN1iVxB6OzwxT2isiVD733GjLUcox/8DbRIiTkAiDGvukyGZ5aCi+73yEOe9" +
                                                    "2Suh7C4Xzywt56aevSvq1dk9EB1uO3dSMkdolaiInH/aN1g8aAM8cc/geMML" +
                                                    "XaMz5kFRUPenVqj702J4FIDTxaaj3sd2NzgFgH+NXh3Emeyv++NCvWzrl5d7" +
                                                    "OjYsH/mluppUXog74a00X6K0GvWr5jGHkbwhTeus9ADx9QWOeuteZyDv8lva" +
                                                    "+7gi/CJHq6oI4Yh5s2qiL0E7ACIx/bJTQfjB+telGmfLqCb1TrgQhmvAXP7r" +
                                                    "UwHekvrfJ6NfWT7wwCO3dj8rURzADS8sCCkdcB9Td34fvLc2lVaRFds/+v6a" +
                                                    "lzq3VUprjRj6vIt+tW1i/tl/AsO9eV9jEwAA");
}
