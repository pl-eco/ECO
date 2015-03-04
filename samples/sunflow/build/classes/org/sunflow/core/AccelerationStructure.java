package org.sunflow.core;
public interface AccelerationStructure {
    public void build(PrimitiveList primitives);
    public void intersect(Ray r, IntersectionState istate);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVZe3BUVxk/u5t3QhISXtKWQhJwgHZXHOkM0KmEbVKCC2RI" +
                                "wCFalpO7Z5MLd++93Hs22dDSaTvjgIxFbFNLteYPBZVaSqcWa9U61Kol1qp0" +
                                "ahW1tDrOFKsozPgYpS1+3zm7e+/efZAEmpn9cvc8vsfvfK9z94nzpNy2yFLT" +
                                "0EYGNIMHWYoHd2jLg3zEZHZwXWR5N7VsFgtr1LZ7YSyqtDzV8O9LBwcb/aSi" +
                                "jzRTXTc45aqh25uYbWhDLBYhDc5oh8YSNieNkR10iIaSXNVCEdXmqyKk1rWV" +
                                "k7ZIRoUQqBACFUJChVC7swo2TWN6MhHGHVTn9i5yD/FFSIWpoHqcLMhlYlKL" +
                                "JtJsuoUFwKEKv28Bo8TmlEXmZ22XNucZ/PDS0Ogj2xqfDpCGPtKg6j2ojgJK" +
                                "cBDSR+oSLNHPLLs9FmOxPjJdZyzWwyyVaupuoXcfabLVAZ3ypMWyIOFg0mSW" +
                                "kOkgV6egbVZS4YaVNS+uMi2W+VYe1+gA2DrLsVVa2InjYGCNCopZcaqwzJay" +
                                "naoe4+RG746sjW2fgAWwtTLB+KCRFVWmUxggTfLsNKoPhHq4peoDsLTcSIIU" +
                                "TuYWZYpYm1TZSQdYlJM53nXdcgpWVQsgcAsnM73LBCc4pbmeU3Kdz/kNtx64" +
                                "S1+r+4XOMaZoqH8VbJrn2bSJxZnFdIXJjXVLIl+ks57f5ycEFs/0LJZrnr37" +
                                "4uqb5p08JddcV2DNxv4dTOFR5XB//enrw4tXBFCNKtOwVTz8HMuF+3enZ1al" +
                                "TIi8WVmOOBnMTJ7c9NOt9z7O/uonNV2kQjG0ZAL8aLpiJExVY9YdTGcW5SzW" +
                                "RaqZHguL+S5SCc8RVWdydGM8bjPeRco0MVRhiO8AURxYIESV8KzqcSPzbFI+" +
                                "KJ5TJiGkEj7EB59mIv8CSDjpCw0aCRaiCtVV3QiB7zJqKYMhphghmyZMDU7N" +
                                "TupxzRgO2ZYSMqyB7HfFsFioXVGYhuqDnT3C1yEwguhj5gfKPYW2NQ77fAD7" +
                                "9d6g1yBe1hpajFlRZTS5puPik9GX/dkgSKPCySKQF0zLC6K8YEF5xOcTYmag" +
                                "XHmycC47IcIh99Ut7rlz3fZ9LYBnyhwuQ1RTIuTmZL7ARo9+Irg7nzv56Ikv" +
                                "LV3hd+eBBldm7WFcetV0R26vxRiMv3Go+6GHz+/9lBAKK1oLCWhDGgYfg8QJ" +
                                "Cegzp3adefPs4df8WUUDHJJtsl9TFU6qaD9kKqpwTqqzKSfPkBuKxa3IOYfv" +
                                "Hx2LbTyyTEZXU24sdECqP/b6ez8PHnprvMBRVHPDvFljQ0xzyaxFkeAraWkd" +
                                "irFepLQuUTEUCOj9R7/1LD+9dKUUuaR44fNufOn+d+b23ja43U/8ucULpcNQ" +
                                "De7sxpKTLS03eoz3sjy6/onxOxYpD/pJIJ25CmTp3E2r3DCAUIuBv+kIKI7U" +
                                "gNAWr2tbhsJi4JSO3CXz6Yno83va/KQM0i+UHE4hRUA2n+cVnpMxV2W8DkWV" +
                                "Awhxw0pQDacyJaOGD1rGsDMiYq5ePE/H40G3CMGnOp1TxH+cbTaRzpAxKtZf" +
                                "J+g8JAvE2frxsQVJK3rWIsfHIf9pkIPxMNo26wkjpsZV2q8xjLZ3GxYuO/G3" +
                                "A43SgzQYyZzOTVdm4Ix/aA259+Vt/5kn2PgUrL9O3DnLZPg1O5zbLYuOoB6p" +
                                "+1694dGX6FegPEBKttXdTGRZnzDNJ2ydCQeQl1+6LTUBJWFIGCPgWSkWLxd0" +
                                "BeKXjjb8fhuSZZyU9ydV6BkssrhEZ5fhLCtdaE/TmzsfO3dMBoa3dHoWs32j" +
                                "+y8HD4z6Xf1Fa16Jd++RPYZQcpr0h8vw54PP+/hBC3BA1pimcLrQzc9WOtPE" +
                                "Q19QSi0hovPt43u+/809e/1pRD7MSdmQocbMPLzEwNJc31wJn6a0bzZN1Tdz" +
                                "j8cvF2ROeEbeCW+iI5nJlrzJLsystnQubDyZ0GJjCRfYjGRdJinjVhwIl7Zf" +
                                "QF/4LGY53Z9sdYKi24bzKIFHwMEDSgb0vnBEXmDcSkdLzFGpKJKtqQxSjSLE" +
                                "UKug1KqUHhAPcVWnoi+8U/DchmQ7kn6oZAOMi7pklywIPUkoeK6+8wF17JWf" +
                                "/avhPhkwuYEmrh7prd59Z34b+Ggtb/u8KCRl/dQWqbsKkquNKzmZX/waI3jJ" +
                                "KKq94snNdE5OiM8eXAbEBgdEsQCHjZwoKwxCVOlKRHtOnNl7i4j/hiEVulYW" +
                                "603frHKTo9P4rMq5bRWEKaqcO/7AqQXvbGkWbXQGEXeTs56aeU3OWmoPwnh5" +
                                "5e9eeHHW9tMB4u8kNZpBY50Ub1LQD0NdYvYgNHgp8+OrRXjXDVcBbRTZ1yIL" +
                                "i5ictklUg6hy92Pvv/KXPWfHA6QCah0WbGpBEw5dfrDY/dXNoK0Xnm6HXVBJ" +
                                "6+VuuE1loQcXaMqOZss2JzcX4y2SuKe64w0Qsgez1hhJPYZsP+JpGZKm6Z4V" +
                                "zlQ3dWe6ByrpBMDL2k6c9Ar+UO+4IPZ77kno8ZrDkfaenmjv1u6O6Jb2TV3t" +
                                "ayIdwklNmPT14qOSKnF6n4QiGKZWTAbekcvVratnj68UgZcP0hSBmSOAocM8" +
                                "hEXL0JnuZEhTapjXFbcV0df1siOqHHztwrQtF354UcSYt+GUrUB9OilaZLb3" +
                                "ppEOiY+d3PDpRu3kJWDSB0zgsmLbGy245oj9Q+lYaMzGAkmRgklYXAxFAv0s" +
                                "ks+5c/LE6oBMviXy/BdKzD3kladcqQ7LPH8QyYNIRiHPDwImYSMme9PVSG6X" +
                                "CnVyElDTL5T2uQrN/oL4QGNfuHXsSJhcNHu7vzv7mVu/MXZW3JyQ0SNIDiH5" +
                                "MpKxCcJXoq1IN47SwZB+rQR8RyYIn2DXIlZ8FclhJF+HCs52JalmF8Ktst8w" +
                                "NEb1iWEnjEdyFMnjSI5dI0Dc9n67xNyJyWPxNJJnkHwHvIgb8i1YgVbENTFx" +
                                "KJ5D8j0kP5gMFJMJrR+VmPvxBPFw5A048fUCkheR/AT7PIOr8RH8Fp4kBqfE" +
                                "JiTjHxQGvywxd/pqMPgFkl8heRW6bolBu6ZNBYZfZ2F4/VrC4L5kesK3TDP0" +
                                "AcH7DyU24c3U8XJxJ7GSJrR8HSmFmZj8BIs/Xg2Mv0fyFpI/g1rDVOVTQfDt" +
                                "LILnJoPgle9v+PUNJIcEn78XT8d/EgsuTLpiyWM4j+QfSC469kwZhH9eIxDc" +
                                "wfK/K5n+7hRN/y+SS0jeu3rTfXIsBZ1rwVe0eIOYk/fTj/y5QnlyrKFq9tjm" +
                                "34gXZNmfFKojpCqe1DRXM+1urCtMi8VVYUm17M7EFdlXBiXCe68HD8d/aJAv" +
                                "IJdVclLrWgaVNf3kXlQDjQoswsda6C9JTmNpetvM1qKXrfVJ+YNYVDk+tm7D" +
                                "XRdvOSKa4nJoXHfvRi5wF6mUr/cEU3xftqAotwyvirWLL9U/Vb0w8+5FtKdN" +
                                "Lpeb43Kjsf8DdkARbHwcAAA=");
}
