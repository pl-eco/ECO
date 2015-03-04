package org.sunflow.core;
import org.sunflow.SunflowAPI;
import java.util.Map;
public final class Options extends ParameterList implements RenderObject {
    public boolean update(ParameterList pl, SunflowAPI api) { for (Map.Entry<String,Parameter> e
                                                                    :
                                                                    pl.
                                                                      list.
                                                                     entrySet(
                                                                       )) {
                                                                  list.
                                                                    put(
                                                                      e.
                                                                        getKey(
                                                                          ),
                                                                      e.
                                                                        getValue(
                                                                          ));
                                                                  e.
                                                                    getValue(
                                                                      ).
                                                                    check(
                                                                      );
                                                              }
                                                              return true;
    }
    public Options() { super(); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXXWwURRyfu36XQr+gFITSloKWwi08YAIlarkUWjxo0wKJ" +
                                                    "JXLM7c71lu7tLLNz7VGsAomB8ECMFgSjfTAQRPmKkaAxJH0SCL5gjMYHwTeJ" +
                                                    "ygMvaIKK/5m9u73bu2J88JL9mvl/f/zmfxceoBKboQ6LGvuHDcoDJMkDe421" +
                                                    "Ab7fInZgS2htP2Y20YIGtu3tsBZWW69UP3r8VqzGj0qHUD02Tcox16lpDxCb" +
                                                    "GqNEC6Fqd7XbIHGbo5rQXjyKlQTXDSWk27wzhGZlsXLUFkqboIAJCpigSBOU" +
                                                    "LpcKmGYTMxEPCg5scnsfeh35QqjUUoV5HLXkCrEww/GUmH7pAUgoF987wSnJ" +
                                                    "nGSoOeO743Oewyc6lMl3d9d8WoSqh1C1bg4Kc1QwgoOSIVQVJ/EIYXaXphFt" +
                                                    "CNWahGiDhOnY0Mel3UOoztaHTcwTjGSCJBYTFmFSpxu5KlX4xhIqpyzjXlQn" +
                                                    "hpb+KokaeBh8bXB9dTzcJNbBwUodDGNRrJI0S/GIbmocLfFyZHxsexkIgLUs" +
                                                    "TniMZlQVmxgWUJ2TOwObw8ogZ7o5DKQlNAFaOFo4o1ARawurI3iYhDlq9NL1" +
                                                    "O1tAVSEDIVg4muclk5IgSws9WcrKz4NtG44fMHtMv7RZI6oh7C8HpiYP0wCJ" +
                                                    "EkZMlTiMVStCJ3HD9aN+hIB4nofYobn22sOXVjZN33RonilA0xfZS1QeVs9E" +
                                                    "5txZFGxfVyTMKLeorYvk53guy78/tdOZtKDzGjISxWYgvTk98NUrBz8mv/pR" +
                                                    "ZS8qVamRiEMd1ao0bukGYZuJSRjmROtFFcTUgnK/F5XBe0g3ibPaF43ahPei" +
                                                    "YkMulVL5DSGKgggRojJ4180oTb9bmMfke9JCCM2GC/XAVYmcn3xyFFZiNE4U" +
                                                    "rGJTN6kCtUswU2MKUWmYEYsq3cE+JQJRjsUxG7EVO2FGDToWVhM2p3HFZqpC" +
                                                    "2XB6WVEpI0qfJRs8IArN+v9VJIWXNWM+HyRgkbf9DeicHmpohIXVycTG7oeX" +
                                                    "wrf9mXZIxQfSChoCKQ0BoSGQ0oB8Pil4rtDkZBVyMgLdDbhX1T746pY9R1uL" +
                                                    "oJyssWIIqCBtBcdS6rtVGnQhoFcCnQp12PjhriOBP8696NShMjNeF+RG06fG" +
                                                    "Du18Y7Uf+XOBV7gDS5WCvV/AZQYW27wNV0hu9ZH7jy6fnKBu6+UgeQoR8jlF" +
                                                    "R7d6A8+oSjTASFf8imZ8NXx9os2PigEmABo5hlIG1Gny6sjp7M40SgpfSsDh" +
                                                    "KGVxbIitNLRV8hijY+6KrIg54lbnFIdIoMdACbCbvpg+ffW9jnX+bCyuzjrd" +
                                                    "Bgl3OrvWzf92Rgis/3iq/50TD47skskHiqWFFLSJexD6HLIBEXvz5r4f7t09" +
                                                    "863fLRgOB14iYuhqEmQsd7UAChiARCKtbTvMONX0qI4jBhF192f1sjVXfzte" +
                                                    "4yTKgJV0nlf+uwB3fcFGdPD27t+bpBifKk4h13OXzAlAvSu5izG8X9iRPPTN" +
                                                    "4tM38AcAkgBMtj5OJNYg6RmSoQ/IjLTL+yrP3mpxa7by9pJypTH1JT9a5L1N" +
                                                    "3J6V637x+hyHpOkmNrI5fPJ9HkeL83p6AHCUMAfiRbgXz3QMySP0zOHJKa3v" +
                                                    "7BqnSetyob0bJpeL3/31deDUT7cK4EkFp9Yqg4wSI8cyUJkDDlvlCe22yLHz" +
                                                    "n1zjdzrWOypXzIwLXsYbh39ZuP2F2J7/AAlLPM57RZ7feuHW5uXq235UlEGD" +
                                                    "vKEjl6kzOwyglBGYkkwRULFSKZPeJA2ohXDUi/QugGtW6kiST7Fbb4n73FTv" +
                                                    "Fsy/T+Y/6akuv1Mb6QpoyqsAGQICs44o3zRZQzbZoPPs6u+V6jc/pX63ittG" +
                                                    "aOCEpcHxDdltf8rkzfQ4DAOjqWlFmai7N/L+/YtOpr2jjYeYHJ089iRwfNKf" +
                                                    "Nf8tzRvBsnmcGVBaOdsJ+BP4+eD6W1zCBbHgzAB1wdQg0pyZRCxL9EfL08yS" +
                                                    "Kjb9fHniy48mjvhTIVnPUVmEUoNgs0Bfw2bqWBWI0pg3qjvjpXppqrp8/tSO" +
                                                    "7+VBkRkBK2AOiyYMI6vAsout1GIkqksjKhz8t+RjF/xd8ZYBR8XiIQ0ccsh2" +
                                                    "czQriwwsTb1lE2GOioBIvEZkifYkUQ5WWbnIlX0oiIKQf2HS7ZZw/sSE1ctT" +
                                                    "W7YdePj8Wdm7JfDnZ3xcjrwwwTtHXaZlW2aUlpZV2tP+eM6VimXpfOQcgh7b" +
                                                    "lhQ+K7rjFpfoPv75/M82nJu6Kw+rfwCbL56rWw4AAA==");
}
