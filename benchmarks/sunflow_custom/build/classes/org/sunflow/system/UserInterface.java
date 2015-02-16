package org.sunflow.system;
import org.sunflow.system.UI.Module;
import org.sunflow.system.UI.PrintLevel;
public interface UserInterface {
    void print(Module m, PrintLevel level, String s);
    void taskStart(String s, int min, int max);
    void taskUpdate(int current);
    void taskStop();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1163560750000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALUZC2wUx3Xuzn9sbOxgCCEOHxPE765UIRIQFYxjwPQACxva" +
                                "OG2O9e6cvXhvd9mds89OoEmqFpQKGrWkTarUbVVQQj4QoVKSVkRElQIkpRIt" +
                                "TUqqkvQjhaalDf2kamlD33u7d7t3Ph+241ja59mZN+837zd7z11hxbbFFpmG" +
                                "NtCtGSLMUyK8Q1sWFgMmt8MbosvaJMvmSrMm2XYHzMXkOS9Uf3Dt0Z6aICvp" +
                                "ZHWSrhtCEqqh21u4bWh9XImyam+2ReMJW7Ca6A6pT4okhapFoqotVkbZJN9W" +
                                "wRqjaREiIEIERIiQCJEmDws2VXE9mWjGHZIu7J1sNwtEWYkpo3iCzc4mYkqW" +
                                "lHDJtJEGQKEM37eBUrQ5ZbFZGd0dnYcp/NiiyIFv3ldzLMSqO1m1qrejODII" +
                                "IYBJJ6tM8EQXt+wmReFKJ5uic660c0uVNHWQ5O5ktbbarUsiafGMkXAyaXKL" +
                                "eHqWq5RRNyspC8PKqBdXuaak34rjmtQNutZ7ujoarsV5ULBCBcGsuCTz9Jai" +
                                "XlVXBLstd0dGx8ZPAwJsLU1w0WNkWBXpEkywWufsNEnvjrQLS9W7AbXYSAIX" +
                                "wWaMSBRtbUpyr9TNY4JNz8Vrc5YAq5wMgVsEm5qLRpTglGbknJLvfK5sumv/" +
                                "/fp6PUgyK1zWUP4y2NSQs2kLj3OL6zJ3NlYujH5Dqj+5N8gYIE/NQXZwTjxw" +
                                "dfXihlNnHJxb8uBs7trBZRGTD3ZNPj+zecHyEIpRZhq2ioefpTm5f5u7sjJl" +
                                "QuTVZyjiYji9eGrLq/c8+Az/U5BVtLIS2dCSCfCjKbKRMFWNW+u4zi1JcKWV" +
                                "lXNdaab1VlYK46iqc2d2czxuc9HKijSaKjHoHUwUBxJoolIYq3rcSI9NSfTQ" +
                                "OGUyxkrhYQF4ljDnrwSBYLHIVhvcPSLJkq7qRgScl0uW3BPhshHrAuv2JCSr" +
                                "147ISVsYiYid1OOa0R+xLTliWN3e+4AteIJotaYdNoyOZn78LFKoZU1/IAAH" +
                                "MDM3/DXAWm9oCrdi8oHkmparR2KvBzPh4NpHsFnAKexyCjucwlmcWCBADG5C" +
                                "js7pwtn0QpRD/qtc0P75Ddv3zgmBW5n9RWjZFIXd9PQLbMyRjAJ87Uunnjj+" +
                                "rUXLg/5cUO3Lru1cOJ41xePbYXEO8795vO3rj13Zcy8xBYy5+Rg0ImwGP4Pk" +
                                "CUnoS2d2Xnz70sELwYygIQEJN9mlqbJgZVIXZCtJFoKVZ9LOMEVuHSl2Ke8c" +
                                "fPjAkLL50FInwmqz46EF0v3zb/zvp+HH3zmb5xDKhWEu0Xgf13w8q5AleIrL" +
                                "rUU2NlJaa6WqIUNQP3L42RPi/KIVDsuFIxe/3I2nH35vRsenerYHWTC7gCF3" +
                                "mKrAnW1YdjLl5bYc5XNJHt743Nl1t8tfC7KQm73yZOrsTSv9ZgCmFofSoqNB" +
                                "caYCmM7JdWrLkLkC9cfju3CWdDx2cldjkBVBCoayIyRIE5DRG3KZZ2XNlWmv" +
                                "Q1bFYIS4YSUkDZfSZaNC9FhGvzdD0TaZxlPgeMgt7oCn1s0r9B9X60yENznR" +
                                "Sfi3EGxAMJvONojDOQjmomfd7vk45EAN8jAeRuNWPWEoalyVujSO0fbf6nlL" +
                                "j/95f43jQRrMpE9n8Y0JePM3r2EPvn7fvxqITEDGGuzFnYfmhF+dR7nJsqQB" +
                                "lCP10M9vfeK09G0oEZCWbXWQU6YNOXFFuk4VbGa+zNLauNFQkhpPI+VNP62N" +
                                "bVCeRTQTEIBYQ3Jg8Q47xZvMu4KWlxFcjvZ3oxXf1yBYKlixicRAlQUFukNL" +
                                "TUDB6nMramRX7du9T15+3gms3PKbg8z3Hnjkenj/gaCvR5k7rE3w73H6FBKy" +
                                "yvGn6/AXgOdDfFADnHDqVG2zWyxnZaqlaaLTzC4kFrFY++7RXT9+eteeoGuR" +
                                "+YIV9RmqYg6zF00syvbtMDwNrm83jNe3s4/H9RB8XUWTdyNY76BtECwEB4XD" +
                                "DqLfXuBwP4tgE2ZOye6FTtaifetGp9kseOa7ms2fGM0ChBDwhN9eQPguBPcK" +
                                "yDAg/FZTgTMdg/R18Cx2pV88MdL7hdtRYI06UmhvyxyrG+YoxCZPzu/a9V5D" +
                                "7nSfYboAgXsXUCPkqQEVHK4j4PGF9OkrsOYKisBM5ck0jlSF5ID0Eld1iQwj" +
                                "iGYSQT+CATBUNxfUJtgF63N7EvoP31Vgnzp07rV/Vj/k5J/svEW3QXdr7r6L" +
                                "vwp9cpJo/CrV9aIuySa/KoNaZyMmpNuRb5ZEy0lKk254clO9kyP2mYNLG7Ha" +
                                "MyIh4PSXs5JWfiPE5NZErP34xT13Ujqt7lPhIsGVDveym12rvD50ZdYFOK+Z" +
                                "YvLlo/vOzH5vWx3dbNIW8fecGyVzWM+5XrJ7YL649K1XflK//XyIBdeyCs2Q" +
                                "lLUSXm7higJtArd7oNNOmatWU1QG+ssw3nAExOaNoLKrExXnmPzAkx+e++Ou" +
                                "S2dDrARaD+yfJAvuRXDxCo/0ScFPoLEDRnfDLmhsJju7oUZmTA8uUJuZzXRR" +
                                "gi0ZiTbVxJxmCy/lUKa5tcZI6gqS/UROB5c0Tf8qOVPl+J1pNzQ2ozBeRnfm" +
                                "dWLgD5M9F8T2278ILXddc7SpvT3WcU9bS2xb05bWpjXRFnJSExYDHTgcTBU4" +
                                "vc+omtIsWYoTeIeul89dPe3sCgq84UYap2Gmk2GkfhHBHsDQue5lSNORcNgl" +
                                "pXEEeX3fn2Lyoxfer9r2/stXKcZy+/+vOI2umxQtNi334ueGxB2nNn2uRjt1" +
                                "DYh0AhFZ5ra92YL7Ju3f58YCo1ioIfFY3iT8BQRfRPBdBN/35+TR1QEn+RbI" +
                                "808XWHsml9/gjcqnk+efQnAYwbOQ53vAJs2GQpHQQVu/46sq38trDLhU5W/b" +
                                "WxKmoEZ78MVpP7jrqaFLdGtFQkeIPoKjCI6N0laja1wGicSJArZ6aZS2InJz" +
                                "COOHCF5E8CMo13xnUtLsfK1faZdhaFzSR2c7Uh7BSQQvI3hlggzi1/d0gbWz" +
                                "Y7fFqwjOIHgNeyjDucjg+6oxqn2ONiH42VjUHkvMXCiw9sYodff47fYC5xcI" +
                                "fongTWzgDKHGB/Bt3Rht8BZtQvDrj8sGvy2w9vuPYoN3EPwOwR/gDuPYoEnT" +
                                "xmOGdzNmuDyRZnDzQr5QLdIM9/r9lwKboAls8CowfU60kib0ci0pmZuY6IjE" +
                                "3z+KGa8g+BuCD0CsfkkV47HgvzMW/M9YLFgghwQ9hL8ioCthgI2cev9BCMEx" +
                                "l6LdhHYdNyOdQMjTZ7xGCJRMkBF8wRKovJHqk8enemASgioE1ROgei3NpQSr" +
                                "yvoEjleC6cN+XnN+EpKPDFWXTRva+iZ9gMz8bFMeZWXxpKb5umN/p1xiWjyu" +
                                "kqLlTrtFWgfqBasd/kUM0qQzQGUCUx3UmwWb5EOFCuqO/EgzBQsBEg5vhaaR" +
                                "ZXWLZm7vOHfEG9TGpPPDY0w+OrRh0/1X7zxEnW4xdKODg0gFLhilzidUIorf" +
                                "JGePSC1Nq2T9gmuTXyifl/4+RT1nrc/dpvvy7bH/A0d9nUbkHQAA");
}
