package org.sunflow.core;
import org.sunflow.image.Color;
public interface Display {
    void imageBegin(int w, int h, int bucketSize);
    void imagePrepare(int x, int y, int w, int h, int id);
    void imageUpdate(int x, int y, int w, int h, Color[] data);
    void imageFill(int x, int y, int w, int h, Color c);
    void imageEnd();
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL0ZbXAV1fW+l+8PkhA+5SMSEnQAfa8wxQ7gIElMIPiAlAQ6" +
                                "pq2Pze59Lwv7dpfd+/KBYMGqMHREh2KVjs0vaGvlw2mltHVkaG0VtHQGSm1x" +
                                "Wqj9mKKWFjqj/YFKz7l339t9m5dHArGZ2ZPde+8595xzz+d9By+TAtsic01D" +
                                "649rBgvRPhbaoC0IsX6T2qEVkQVtkmVTpUmTbLsDxqLyzJcqP7r2dHdVkBR2" +
                                "knGSrhtMYqqh22uobWg9VImQSne0WaMJm5GqyAapRwonmaqFI6rNFkdImQeV" +
                                "kfpIioUwsBAGFsKchXCDuwqQxlA9mWhCDEln9ibyCAlESKEpI3uM1GYSMSVL" +
                                "Sjhk2rgEQKEYv9eBUBy5zyIz0rILmQcJ/Mzc8N5nH6r6YR6p7CSVqt6O7MjA" +
                                "BINNOkl5gia6qGU3KApVOslYnVKlnVqqpKmbOd+dpNpW47rEkhZNKwkHkya1" +
                                "+J6u5spllM1Kysyw0uLFVKopqa+CmCbFQdaJrqxCwhYcBwFLVWDMikkyTaHk" +
                                "b1R1hZHb/RhpGesfgAWAWpSgrNtIb5WvSzBAqsXZaZIeD7czS9XjsLTASMIu" +
                                "jEwZkijq2pTkjVKcRhmZ7F/XJqZgVQlXBKIwMsG/jFOCU5riOyXP+Vxede/u" +
                                "h/XlepDzrFBZQ/6LAanGh7SGxqhFdZkKxPI5kW9JE1/dGSQEFk/wLRZrjm25" +
                                "uvSumhMnxZqpWdas7tpAZRaV93dVnJnWNHthHrJRbBq2ioefITk3/zZnZnGf" +
                                "CZ43MU0RJ0OpyRNrXn9w2w/oB0FS2koKZUNLJsCOxspGwlQ1ai2jOrUkRpVW" +
                                "UkJ1pYnPt5IieI+oOhWjq2Mxm7JWkq/xoUKDf4OKYkACVVQE76oeM1LvpsS6" +
                                "+XufSQgpgocE4FlBxF8xAkYi4W4jQcOSLOmqboTBdqlkyd1hKhthW0qYGpya" +
                                "ndRjmtEbti05bFjx9LdsWDR8v2qbmtQfQqsyR5leH/Jf1RsIgGqn+R1bA59Y" +
                                "bmgKtaLy3mRj89XD0beCaUN3JIcDgx1Czg4h3CHk7EACAU54PO4kzgu0vRH8" +
                                "FiJa+ez2r65Yv3NmHhiK2ZuPuurjjjQ59QGIPo64y7b89MS+o9+euzDo9e5K" +
                                "T7xsp0zYylh33w6LUhj/03Nt33zm8o4v801hRV22DeoRNoHlQDiEsPL4yU3n" +
                                "L17Yfy6YZjSPQQhNdmmqzEix1AXxR5IZIyXpQDJIkOlDeSOPJPsf3TugrD4w" +
                                "T/hMdaaFN0MAP/T2J78OPffnU1mUX8IM826N9lDNs2cFbgn24OzWLBsreaBq" +
                                "5XlABjfd9cKLx9iZuYvElnOGTmd+xDcefX9Kx5Lu9UESzExJuDsMlSJmGyaS" +
                                "dMK43Se8n+QLKw+eWnaHvCdI8px4lCX2ZiIt9qoBNrUoJAsdFYojpbDpTL8x" +
                                "W4ZMFcgo7r5zZkhHo69urQ+SfAiqkEiYBI4PMbrGv3lGHFycsjrcqgCUEDOs" +
                                "hKThVCoRlLJuy+h1R7iXVfD3sXA83CzmwTPOiRT8P86OMxGOF17J10/lsAZB" +
                                "LT/bIL7ORFCHlnWHa+MQ1TSIrHgY9Wv1hKGoMVXq0ih628eVs+Yd/efuKmFB" +
                                "GoykTueuGxNwx29rJNveeui/NZxMQMas6vqdu0y43ziXcoNlSf3IR9/2s9P3" +
                                "vSF9B4I+BFpb3Ux57MwTfgVIs3NUVpaagGDf42Sj8Nbqixufv3RImLA/dfkW" +
                                "0517d10P7d4b9OT3ukEp1osjcjw/iTHi5K7DXwCeT/HBE8MBEeOrm5xEMyOd" +
                                "aUwTj6c2F1t8i5Z/HNn6yve37kAxcJs7GcmDQIKvSzjgo4s4Cws4XIh7OrEF" +
                                "v5sRzGNQyCSgQGikcVV442IE94lY1MBIfo+hKuYgCnxgbqZt4nytY5u1N2ub" +
                                "mQwX8AUFrlg+wMm25ZByDYIHGCnnUrZZFMIz9/Zlw5NpCTzzHZnmf+YywdHP" +
                                "GtqSuTsIwxz4bt1vvjZQ9y4YZicpVm2otBuseJYCzoNz5eDFD86OmX6YB678" +
                                "LskWUc9f+Q4ubDPqVS50eVpJzcQpXU47SjotTPuLt1Bu2P02o4lwKz8xSada" +
                                "qoYZfaLibCYwMslbjXBbwbhmWKZpCkPqzGFkCoK1jJRxxLWmAp48Ahv7AjyN" +
                                "jvoa/y9+Iwo4LYdIOoI4VicoUouqaSMQCBNTqyNQ6+gI5OWtJ8ccB5ugwOJ8" +
                                "N+vKMNjmATl7hJ7o9mSiAQnxHhiidA4x8lwxoOSDjhQCdy55Hs0x95hgFMGW" +
                                "tLFW8RSJXIUEV7n4YFB3qLrEz28bp7kdwdcRPA6KilPG60o7Z0HXnoSC1dMN" +
                                "PqkOnH7zw8rtIo1mpl9+IeCg+vHO/yFvfhmrf4oXgukgVAzFkY0rGZkx9OUC" +
                                "pyVya9kNT26Ce3J8+/TBpZRY6SqRL8DhPRm5N7sSonJrItp+9PyOe3hVUNmj" +
                                "Qi9JlQ7nviOzuHEbl8UZdyBZ1RSVLx158mTt++vG8eY2pRFvk7JSMgc1Kcsl" +
                                "uxvGC4re+flrE9efySPBFlKqGZLSIuH9BnSpUFdSuxtasj7zvqXcK8t7sc2s" +
                                "gieQI+c4MvFqLipvef7T0+9tvXAqjxRClsCCG1Ip1NaMhIa6VfISqO+At/sB" +
                                "CyrhCoGt6vG06sEEqtOj6bKbkbuHos1LO191jvcyEMCp1Wgkhet/zlfyJ03T" +
                                "O+vNZjdjTI9AJTwM5aVld4IiqeZuU+GaIPZr3knIduOaIg3t7dGOB9uao+sa" +
                                "1rQ2NEZ4zbbHhMlAB74+0Zfj9L6kakqTZCnC8Q5cL6lbOunUIu54g5V0k4qZ" +
                                "zBUj9bIwlrKGTnU3QpqCw0Fdbf0Q/HquIKPy0+eujFl35fhV7mP+hvFZ0Rk5" +
                                "QdEik/w3BY5LfP7Eqq9UaSeu8SqpTJKhpLFXWwq1OP4+xxeq0r5A+kjWIPwN" +
                                "BE8hOIjgiDcmDy8PiOCbI86/nGPux/79nrhR+hRx/kcIjiI4BnG+G3TSZCjc" +
                                "E5Zw1Bc9WeVQVmVAF569z2tOmIx3Zpt/Munle783cIFfcyChn6WLjFcQHB+m" +
                                "rnKk/gBfEBDWhPC1HLr61TB1xcnN5Ct+geCXCF6HdE03JSXNztYJFXUZhkYl" +
                                "fXi648IjOIXgTQSnR0khXnl/m2PudyPXxVkE5xC8DSbDDHERnaXu8EwMXxXn" +
                                "EbyD4I8jUcVI/OgvOeb+Nkx9uPvtcp3pXQR/RfB3LOoMpsb68WvZCHVwiSMh" +
                                "eO+z0sG/c8xdvRUd/AvBFQT/gc5A6KDB6QxGqIYP02r4aDTV4MSKbO6brxl6" +
                                "nNP+OAcSWHmNa+Wt2ApbSRPqu+Y+mZoY/JBEIHAraryGFFAVgXxgq1dS2U1o" +
                                "MFCU0mCgeCQazBFXgu6CTxDw25VAxZDhOBDkC6pGnJ52cbwxCCoRjHXluWkl" +
                                "jB8lJXicJTDlRqJPu0nRb0MwFcH0URB9Bh/rgwzl/IKCDcLkQb+3it8I5cMD" +
                                "lcWTBtb+XlwDpX7HK4mQ4lhS0zy1srduLjQtGlO5iCWi+OIdcKAekoL/dxyw" +
                                "afyHIgTqxLI7GSnzLANOnTfvojmM5MEifJ0L5SPJqBtNfxVZN2QvtTIpfoWO" +
                                "ykcGVqx6+Oo9B3jNWwB16ebNSAVajSJx+86J4nV27ZDUUrQKl8++VvFSyazU" +
                                "hSuvPqs9RjbZE2WP/w8IOIKk8R8AAA==");
}
