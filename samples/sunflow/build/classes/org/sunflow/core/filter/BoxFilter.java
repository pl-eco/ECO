package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class BoxFilter implements Filter {
    private float s;
    public BoxFilter(float size) { super();
                                   s = size; }
    public float getSize() { return s; }
    public float get(float x, float y) { return 1.0F; }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1XXWwUVRS+O223PxS6lL+KUKAUwp87IkGDJShsWigutKEF" +
                                                    "Q1GW29m73aGzM8PM3XYpVoHEQHggRguCwT4YCIL8xUjQGBJeFAi+YIzGB8H4" +
                                                    "IhF54EEkouK59+7M7M5uEV/cZGbu3nvOPefcc853zj11B5XZFppvGtr2Hs2g" +
                                                    "YZKh4a3a4jDdbhI7vDq6uB1bNolHNGzbnTAXUxrO1dx78FYyJKFgFxqHdd2g" +
                                                    "mKqGbq8jtqH1kXgU1XizzRpJ2RSFoltxH5bTVNXkqGrTpigalcNKUWPUUUEG" +
                                                    "FWRQQeYqyMs9KmAaTfR0KsI4sE7tbeh1FIiioKkw9Siakb+JiS2cym7Tzi2A" +
                                                    "HSrY/w1gFGfOWGi6a7uwucDgA/PloXc3hz4uQTVdqEbVO5g6CihBQUgXqk6R" +
                                                    "VDex7OXxOIl3obE6IfEOYqlYUwe43l2o1lZ7dEzTFnEPiU2mTWJxmd7JVSvM" +
                                                    "NiutUMNyzUuoRIs7/8oSGu4BWyd6tgoLW9g8GFilgmJWAivEYSntVfU4RdP8" +
                                                    "HK6NjS8BAbCWpwhNGq6oUh3DBKoVvtOw3iN3UEvVe4C0zEiDFIomj7gpO2sT" +
                                                    "K724h8QoqvPTtYsloKrkB8FYKJrgJ+M7gZcm+7yU4587a5fu36Gv0iWuc5wo" +
                                                    "GtO/ApjqfUzrSIJYRFeIYKyeFz2IJ17cKyEExBN8xILmwmt3X1xQf+mKoHmy" +
                                                    "CE1b91ai0JhytHvM9SmRuUtKmBoVpmGrzPl5lvPwb8+uNGVMyLyJ7o5sMews" +
                                                    "Xlr35cadJ8ltCVW1oqBiaOkUxNFYxUiZqkaslUQnFqYk3ooqiR6P8PVWVA7j" +
                                                    "qKoTMduWSNiEtqJSjU8FDf4fjigBW7AjKoexqicMZ2ximuTjjIkQKocHzYGn" +
                                                    "DIkf/1KUlJNGishYwbqqGzLELsGWkpSJYsQsYhpyc6RN7oZTTqaw1WvLdlpP" +
                                                    "aEZ/TEnb1EjJtqXIhtXjTMuKYREZNIJoklcYmRY+CrOIM/9HWRlmd6g/EACX" +
                                                    "TPEDgga5tMrQ4sSKKUPpFc13z8SuSW6CZE8M0AdEhbOiwkxUWIgKu6JQIMAl" +
                                                    "jGcihcPBXb2Q+ACJ1XM7Xl29ZW9DCUSa2V8KZ81IG8DUrB7NihHx0KGVY6AC" +
                                                    "IVr3waY94fvHXxAhKo8M5UW50aVD/bs2vPG0hKR8TGZ2wVQVY29nSOoiZqM/" +
                                                    "F4vtW7Pn1r2zBwcNLyvzQD4LFoWcLNkb/B6wDIXEAT697edNx+djFwcbJVQK" +
                                                    "CAKoSTFEOQBSvV9GXtI3OQDKbCkDgxOGlcIaW3JQr4omLaPfm+GhMYaPx4JT" +
                                                    "RrEsGA9PRTYt+JetjjPZe7wIJeZlnxUcoFs+u3T4/Hvzl0i5WF6TUx07CBXI" +
                                                    "MNYLkk6LEJj/4VD7Owfu7NnEIwQoZhYT0MjeEcAJcBkc65tXtn1/88bRbyQv" +
                                                    "qigUzHS3pioZ2GO2JwVQRAMkY75vXK+njLiaUHG3Rlhw/lkza+H5X/eHhDc1" +
                                                    "mHGCYcG/b+DNP7EC7by2+fd6vk1AYVXMs9wjEwcwztt5uWXh7UyPzK6vpx6+" +
                                                    "jN8HkAVgs9UBwrEq4ObL3Ed0MpaaAnDty6K/PFh7s/fIrdMibfylwkdM9g7t" +
                                                    "exjePyTl1NOZBSUtl0fUVB4Mo0XwPIRfAJ6/2cOChk0ITK2NZIF9uovspsnc" +
                                                    "M+NRanERLT+fHfz8w8E9woza/HLSDN3S6W//+ip86MerRRAL4s/AlOsocx3n" +
                                                    "8XeYKcVPFPG1JvaabhasZfhM3eOcfQtrYXIg6482rXv3T/e5TgWgU8QdPv4u" +
                                                    "+dSRyZFltzm/l/2Me1qmEMGh3fN4nzmZ+k1qCH4hofIuFFKyveQGrKVZjnVB" +
                                                    "/2Q7DSb0m3nr+b2QKPxNLrpN8cdDjlg/7nh+gDGjZuMqH9RUO1ATzEJN0A81" +
                                                    "AcQHEc7SwN+z2GuOk+nlpqX2YdaogofYyiKOTiaX1JjnPzaeQNGkgiomaheL" +
                                                    "xqkjNWE8Eo/uHhqOtx1bKGXD5jmKKqlhPqWRPqLliJLYTnllbQ1vOz0X7Tvx" +
                                                    "0QV6ff7zIqbnjRxWfsbLu3+Z3LksueU/FLNpPpv8W55Yc+rqytnK2xIqcT1d" +
                                                    "0EnnMzXl+7fKItD66515Xq53vcweVOe42/kWFBTPYcWT9OVHrG1kr06Ihh5C" +
                                                    "OwAzeSAU5jOfaM9XjIVfKKtY6LEVk4Sj3YBbxEljj1ARs9crFJWAiiOoBzFc" +
                                                    "6bZSrEDUFdzcxG1DOTNcUzFpeP13vDlwbwSV0JYn0pqWm3o546BpkYTKlakU" +
                                                    "iSiyJFEsJ0RnR9ndjA24rkTQq3Db9dNTVMo+uWQQRqNyyMA52VEukQEHAkRs" +
                                                    "aJpOhoZ4XWQQFBYQlEE52cVag9x/eX0CSyB+K3aCPS3uxTHl7PDqtTvuPnuM" +
                                                    "Z04Z3KcHBvgtCi6FokVyE2bGiLs5ewVXzX0w5lzlLAcIxrBXbbYv8uk2rXj7" +
                                                    "0JwyKS/4A59O+mTp8eEbvH/5B1oHx/+uEAAA");
}
