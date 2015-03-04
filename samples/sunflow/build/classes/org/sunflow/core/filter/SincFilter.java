package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class SincFilter implements Filter {
    private float s;
    public SincFilter(float size) { super();
                                    s = size; }
    public float getSize() { return s; }
    public float get(float x, float y) { return sinc1d(x) * sinc1d(y); }
    private float sinc1d(float x) { x = Math.abs(x);
                                    if (x < 1.0E-4F) return 1.0F;
                                    x *= Math.PI;
                                    return (float) Math.sin(x) / x; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWwUVRS+O223P1S6lAIVsUApxgLuiEaN1qi4aaG40IZW" +
                                                    "jPVnvZ29ux06f8zcbZdq/SExEB+I0YpotA8GgiB/MRI1xoQXFaIvGqPxQTS+" +
                                                    "SEQeeBCJ/+fcmdnZnd1WfHGTmbl77zn3nHPPOd8598gFUuPYZLVlajuymsnj" +
                                                    "LM/j27Rb4nyHxZz4xuQt/dR2WDqhUccZhLmU0n6i6dLvz4/EJBIdIvOpYZic" +
                                                    "ctU0nC3MMbUxlk6SpmC2W2O6w0ksuY2OUTnHVU1Oqg7vSpI5RaycdCR9FWRQ" +
                                                    "QQYVZKGCvC6gAqarmJHTE8hBDe5sJ0+SSJJELQXV42R56SYWtanubdMvLIAd" +
                                                    "6vD/VjBKMOdtsqxgu2tzmcEvrZanXn409nYVaRoiTaoxgOoooAQHIUOkUWf6" +
                                                    "MLOddek0Sw+ReQZj6QFmq1RTJ4TeQ6TZUbMG5TmbFQ4JJ3MWs4XM4OQaFbTN" +
                                                    "zinctAvmZVSmpf1/NRmNZsHWhYGtroU9OA8GNqigmJ2hCvNZqkdVI83J0jBH" +
                                                    "wcaO+4AAWGt1xkfMgqhqg8IEaXZ9p1EjKw9wWzWyQFpj5kAKJ4tn3BTP2qLK" +
                                                    "KM2yFCetYbp+dwmo6sVBIAsnC8JkYifw0uKQl4r8c2HznXseNzYYktA5zRQN" +
                                                    "9a8DprYQ0xaWYTYzFOYyNq5K7qULP9wtEQLEC0LELs27T1y8Z03bqdMuzTUV" +
                                                    "aPqGtzGFp5T9w3M/X5LovL0K1aizTEdF55dYLsK/31vpyluQeQsLO+Ji3F88" +
                                                    "teXjB58+zM5LpKGXRBVTy+kQR/MUU7dUjdnrmcFsylm6l9QzI50Q672kFsZJ" +
                                                    "1WDubF8m4zDeS6o1MRU1xX84ogxsgUdUC2PVyJj+2KJ8RIzzFiGkFh7SCU8N" +
                                                    "cX/iy4kqj5g6k6lCDdUwZYhdRm1lRGaKmbKZZcrdiT55GE55RKf2qCM7OSOj" +
                                                    "meMpJedwU5cdW5FNO+tPy4ppMxk0gmiSB1RD6RHDOIac9X8Ky6PlsfFIBJyy" +
                                                    "JAwJGmTTBlNLMzulTOXu7b54LPWpVEgR78w4aQdZcU9WHGXFXVnxQBaJRISI" +
                                                    "FpTp+hw8Ngq5D6jY2DnwyMbHdrdXQbBZ49Vw3EjaDsZ6inQrZiIAiF4BgwpE" +
                                                    "aesbD+2KXz54txul8sxoXpGbnNo3/szWp26UiFQKy2gYTDUgez+CaQE0O8Lp" +
                                                    "WGnfpl3nLh3fO2kGiVmC8x5elHNivreHXWCbCksDggbbr1pGT6Y+nOyQSDWA" +
                                                    "CAAnpxDogEltYRkled/lYyjaUgMGZ0xbpxou+cDXwEdsczyYEbExV4zngVPm" +
                                                    "YCIsgKfOywzxxdX5Fr5b3FhCL4esEBjd8/6pV06+uvp2qRjOm4oK5ADjLjjM" +
                                                    "C4Jk0GYM5r/d1//iSxd2PSQiBChWVBLQge8EQAW4DI712dPbv/nu7P4vpSCq" +
                                                    "ONTM3LCmKnnY47pACgCJBmCGvu+439DNtJpR6bDGMDj/aFq59uTPe2KuNzWY" +
                                                    "8YNhzb9vEMxffS95+tNHf20T20QULGSB5QGZewDzg53X2TbdgXrkn/ni2lc+" +
                                                    "oa8DzgK2OeoEE3AVKeRL5yzNjK3qgK9jXgGQJ5u/G33t3FE3bcLVIkTMdk89" +
                                                    "93d8z5RUVFJXlFW1Yh63rIpguMoNnr/hF4HnL3wwaHDChdXmhIftywrgblno" +
                                                    "nuWzqSVE9Px4fPKDNyd3uWY0l1aUbmiYjn7152fxfd+fqQBZEH8m5UJHWei4" +
                                                    "SrzjqJQ4USLWuvC1zCpby4uZ1is5+x7sYoog67c+bXjnD5eFTmWgU8EdIf4h" +
                                                    "+chrixN3nRf8QfYj99J8OYRDxxfw3nRY/0Vqj34kkdohElO8dnIr1XKYY0PQ" +
                                                    "Qjl+jwktZ8l6aTvk1v6uArotCcdDkdgw7gR+gDFS47ghBDWNeMot8EQ9qImG" +
                                                    "oSZCxCAhWNrFeyW+rvczvday1TGKvSp4CFduFuhkCUkdJf7D8QJOFpWVMbd2" +
                                                    "YTReO1MfJiJx/86p6XTfgbWSFza3cVLPTesGjY0xrUhUFe5UUtY2ic4zcNFz" +
                                                    "h956l3+++g43plfNHFZhxk92/rR48K6Rx/5DMVsasim85aFNR86sv055QSJV" +
                                                    "BU+XNdOlTF2l/m2wGXT/xmCJl9sKXsaHtPru9r9lBSVwWOUkfWCWtQfxNQjR" +
                                                    "kGV8ADBTBEJ5PouJ/lLFMPxinmKxK1ZMEjtKhYC7WZCmZlGR4uthTqpAxStT" +
                                                    "r9k/txZPvZYZ1esOCfbKRaBZdhbNVHwB3Ecd6OPWpmdQDhKsIWj0sHy1ll0t" +
                                                    "3euQcmy6qW7R9P1fi9alcGWph3tDJqdpxcBQNI5aNsuoQqF6FybcHDYqZazb" +
                                                    "eHK8POJAKKu79NvhOh6m56QaP8VknJM5RWQQOt6omAimq4AIh3nLx4+YqNoI" +
                                                    "kHEXIPOkKPd9Qv9fSReD6S2u7X4q5tyLe0o5Pr1x8+MXbz0g8roGLvwTE+Ka" +
                                                    "B7dWt4ErpPPyGXfz94pu6Px97on6lT5MzcVXs9e1hXRbWrm56dYtLtqRifcW" +
                                                    "vXPnwemzorv6B0xPYVhPEQAA");
}
