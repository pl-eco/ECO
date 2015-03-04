package org.sunflow.core;
public interface AccelerationStructure {
    public void build(PrimitiveList primitives);
    public void intersect(Ray r, IntersectionState istate);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425482308000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1Ze3BUVxk/u5t3QhISXtKWQgg4QLsrjnQG6FTCNinBhWRI" +
                                "wCFalrN3zyYX7t57ufdsstDSaTvjgIxFbFNLteYPBZVaSqcWa9U61KoFa1U6" +
                                "tYpaWh1nilUUZnyM0ha/75zdvXfvPkgCmpn9cvc8vsfvfK9z94nzpNK2yBLT" +
                                "0HYOagYPsjQPbtOWBflOk9nBtZFlvdSyWTysUdvuh7Go0vZU0z8vHRhq9pOq" +
                                "AdJKdd3glKuGbm9gtqENs3iENDmjnRpL2pw0R7bRYRpKcVULRVSbr4yQetdW" +
                                "TtojWRVCoEIIVAgJFUIdzirYNIXpqWQYd1Cd2zvIPcQXIVWmgupxMi+fiUkt" +
                                "msyw6RUWAIca/L4JjBKb0xaZm7Nd2lxg8MNLQqOPbGl+OkCaBkiTqvehOgoo" +
                                "wUHIAGlIsmSMWXZHPM7iA2Sqzli8j1kq1dRdQu8B0mKrgzrlKYvlQMLBlMks" +
                                "IdNBrkFB26yUwg0rZ15CZVo8+60yodFBsHWGY6u0sAvHwcA6FRSzElRh2S0V" +
                                "21U9zsmN3h05G9s/Bgtga3WS8SEjJ6pCpzBAWuTZaVQfDPVxS9UHYWmlkQIp" +
                                "nMwuyRSxNqmynQ6yKCezvOt65RSsqhVA4BZOpnuXCU5wSrM9p+Q6n/Prb91/" +
                                "l75G9wud40zRUP8a2DTHs2kDSzCL6QqTGxsWRz5PZzy/108ILJ7uWSzXPHv3" +
                                "xVU3zTlxUq65rsiantg2pvCocijWePr68KLlAVSjxjRsFQ8/z3Lh/r2ZmZVp" +
                                "EyJvRo4jTgazkyc2/HjzvY+zP/tJXTepUgwtlQQ/mqoYSVPVmHUH05lFOYt3" +
                                "k1qmx8NivptUw3NE1Zkc7UkkbMa7SYUmhqoM8R0gSgALhKganlU9YWSfTcqH" +
                                "xHPaJIRUw4f44NNK5F8ACSfJ0JCRZCGqUF3VjRD4LqOWMhRiihG1mGmEOsM9" +
                                "oRigPJSk1nY7ZKf0hGaMRJWUzY1kyLaUkGENZodDimGxUIeiMA0tAtP7hPtD" +
                                "rATR7cz/t8A0ItA84vPB4VzvTQ0aRNUaQ4szK6qMplZ3Xnwy+rI/FyoZ7DhZ" +
                                "CPKCGXlBlBcsKo/4fELMNJQrzx9ObzvkAciQDYv67ly7dW8boJ42RyoQ+7QI" +
                                "zFnZL7DRo59IAV3PnXj0+BeWLPe7s0WTK//2MS59b6ojt99iDMbfONj70MPn" +
                                "93xCCIUV84sJaEcaBk+E9App6lMnd5x58+yh1/w5RQMcUnIqpqkKJzU0BvmM" +
                                "KpyT2lxiKjDkhlLRLTLToftHx+I9h5fKGGzJj5hOKAhHX3/vp8GDb50qchS1" +
                                "3DBv1tgw01wy61EkuE9GWqdirBOJr1vUFQXCft+RbzzLTy9ZIUUuLl0evRtf" +
                                "uv+d2f23DW31E39+iUPpMFSHO3uxMOUK0I0e470sj6x74tQdC5UH/SSQyW9F" +
                                "cnn+ppVuGECoxcDfdAQUR+pAaJvXtS1DYXFwSkfu4rn0ePT53e1+UgFJGgoT" +
                                "p5BIIOfP8QrPy6srs16HoioBhIRhJamGU9nCUseHLGPEGREx1yiep+LxoFuE" +
                                "4FObyTziP862mkinyRgV668TdA6SeeJs/fjYhmQ+etZCx8chS2qQqfEw2jfq" +
                                "SSOuJlQa0xhG27tNC5Ye/8v+ZulBGoxkT+emKzNwxj+wmtz78pZ/zRFsfApW" +
                                "aSfunGUy/Fodzh2WRXeiHun7Xr3h0Zfol6CIQOK21V1M5GKfMM0nbJ0OB1CQ" +
                                "X3otNQmFY1gYI+BZIRYvE3Q54peJNvx+G5KlnFTGUip0FhZZVKb/y3KW9TC0" +
                                "u+XN7Y+dOyoDw1tgPYvZ3tF9l4P7R/2uLmR+QSPg3iM7EaHkFOkPl+HPB5/3" +
                                "8YMW4ICsRC3hTDmcm6uHpomHPq+cWkJE19vHdn/367v3+DOIfJCTimFDjZsF" +
                                "eImBJfm+uQI+LRnfbJmsb+Yfj18uyJ7wtIIT3kB3ZifbCia7MbPa0rmwPWVC" +
                                "i54yLrARydpsUsatOBAub7+AvvhZzHB6RNkQBUVPDudRBo+AgweUDOiQ4Yi8" +
                                "wLiVjpaZo1JRJJvTWaSaRYihVkGpVTk9IB4Sqk5F93in4LkFyVYkMahkg4yL" +
                                "umSXLQh9KSh4ru70AXXslZ/8o+k+GTD5gSYuKJmt3n1nfh34cD1v/6woJBUx" +
                                "aovUXQPJ1caVnMwtfdkRvGQU1V/x5KY7JyfE5w4uC2KTA6JYgMNGXpQVByGq" +
                                "dCejfcfP7LlFxH/TsAq9LYv3Z+5f+cnRaXxW5t3JisIUVc4de+DkvHc2tYpm" +
                                "O4uIu8lZR82CJmcNtYdgvLL6Ny+8OGPr6QDxd5E6zaDxLor3LeiaoS4xewga" +
                                "vLT50VUivBtGaoA2i+xrkQUlTM7YJKpBVLn7sfdf+dPus6cCpApqHRZsakGr" +
                                "DneBYKlbrptBez883Q67oJI2yt1w58pBDy7QkhvNlW1Obi7FWyRxT3XHeyJk" +
                                "D2atNlJ6HNl+yNMypEzTPSucqWHyznQPVNJxgJeznTjpFfyh0XFB7Pfck9Dj" +
                                "tYYjHX190f7NvZ3RTR0bujtWRzqFk5ow6evHRyVd5vQ+DkUwTK24DLzDl2vn" +
                                "r5p5aoUIvEKQJgnMLAEMHeEhLFqGznQnQ5pSw4KuuL2Evq5XIlHlwGsXpmy6" +
                                "8P2LIsa8DadsBRozSdEiM703jUxIfOTE+k82aycuAZMBYAKXFdvuseCaI/YP" +
                                "Z2KhORcLJE2KJmFxfRQJ9NNIPuPOyeOrAzL5lsnznysz95BXnnKlOizz/AEk" +
                                "DyIZhTw/BJiEjbjsTVchuV0q1MVJQM28dtrrKjT7iuIDjX3x1rEzaXLR7O36" +
                                "9sxnbv3a2Flxc0JGjyA5iOSLSMbGCV+ZtiLTOEoHQ/qVMvAdHid8gl2bWPFl" +
                                "JIeQfBUqONuRoppdDLfqmGFojOrjw04Yj+QIkseRHL1GgLjt/WaZueMTx+Jp" +
                                "JM8g+RZ4ETfku7IirYhrYvxQPIfkO0i+NxEoJhJaPygz98Nx4uHIG3Ti6wUk" +
                                "LyL5EfZ5BlcTO/FbeIIYnBSbkJz6X2Hw8zJzp68Gg58h+QWSV6Hrlhh0aNpk" +
                                "YPhlDobXryUM7kumJ3wrNEMfFLx/V2YT3kwdLxd3EitlQsvXmVaYiclPsPj9" +
                                "1cD4WyRvIfkjqDVCVT4ZBN/OIXhuIghe+f6GX99AclDw+WvpdPwHseDChCuW" +
                                "PIbzSP6G5KJjz6RB+Ps1AsEdLP+5kunvTtL0fyO5hOS9qzfdJ8fS0LkWfUWL" +
                                "N4hZBT8QyR81lCfHmmpmjm38lXhBlvvhoTZCahIpTXM10+7Gusq0WEIVltTK" +
                                "7kxckX0VUCK893rwcPyHBvkCclk1J/WuZVBZM0/uRXXQqMAifKyH/pLkNZam" +
                                "t82cX/KytS4lfzaLKsfG1q6/6+Ith0VTXAmN665dyAXuItXy9Z5giu/L5pXk" +
                                "luVVtWbRpcanahdk372I9rTF5XKzXG409l/LTYCgohwAAA==");
}
