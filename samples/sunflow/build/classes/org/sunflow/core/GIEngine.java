package org.sunflow.core;
import org.sunflow.image.Color;
public interface GIEngine {
    public Color getGlobalRadiance(ShadingState state);
    public boolean init(Scene scene);
    public Color getIrradiance(ShadingState state, Color diffuseReflectance);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVZf3AU1R1/d/kdQn5CgggRQrACelc6xRmIYwkxQOgBaRJp" +
                                "TavHy+673MLe7rL7LjlQGHSmA+OM1NFo1WJmdGDEll/Tylhr6cRxpoLoVFqn" +
                                "FduC/7RalI50prUtLfT7fXu3u7e5XH5AM3Pf7L0f3x+f9/319g5fIkWWSZYY" +
                                "urq9X9V5iKV4aIu6LMS3G8wKrYss66SmxeQ2lVpWD4xFpabjVf+48ni8OkiK" +
                                "e0kd1TSdU67omtXFLF0dYHKEVLmj7SpLWJxUR7bQARpOckUNRxSLt0TINM9W" +
                                "TpojGRXCoEIYVAgLFcKt7irYNJ1pyUQb7qAat7aRXSQQIcWGhOpxMj+biUFN" +
                                "mkiz6RQWAIdS/L4JjBKbUyaZ59hu2zzK4KeWhIe+/0D1jwtIVS+pUrRuVEcC" +
                                "JTgI6SUVCZboY6bVKstM7iU1GmNyNzMVqio7hN69pNZS+jXKkyZzQMLBpMFM" +
                                "IdNFrkJC28ykxHXTMS+mMFXOfCuKqbQfbK13bbUtXI3jYGC5AoqZMSqxzJbC" +
                                "rYomc3KLf4djY/PXYQFsLUkwHtcdUYUahQFSa5+dSrX+cDc3Fa0flhbpSZDC" +
                                "yewxmSLWBpW20n4W5WSWf12nPQWrygQQuIWTmf5lghOc0mzfKXnO59KGu/Y9" +
                                "qK3VgkJnmUkq6l8Kmxp9m7pYjJlMk5i9sWJx5Glaf3JvkBBYPNO32F7z6kOX" +
                                "V97eOHLKXnNzjjUb+7YwiUelA32VZ+e0LVpegGqUGrql4OFnWS7cvzM905Iy" +
                                "IPLqHY44GcpMjnT98r7dP2SfBkl5BymWdDWZAD+qkfSEoajMXMM0ZlLO5A5S" +
                                "xjS5Tcx3kBJ4jigas0c3xmIW4x2kUBVDxbr4DhDFgAVCVALPihbTM88G5XHx" +
                                "nDIIISXwIQH4LCL2XzkSTtaH43qChalENUXTw+C7jJpSPMwkPWzRhKHCqVlJ" +
                                "Labqg2HLlMK62e98l3SThdd0tGv9oFII3cq40QxTaEH1YCAA4M7xh7YKUbFW" +
                                "V2VmRqWh5Kr2y0ejZ4KOq6dt5+QmEBFKiwihiFBGBAkEBOcZKMo+MgB8K4Qu" +
                                "JLWKRd33r9u8t6kAfMUYLES4UiKWZmW+wEafSiJqV7828uyJ55YsD3oDvMqT" +
                                "MrsZt92lxpXbYzIG4398pvPJpy7t+bYQCisW5BLQjLQNnAcyImSW757adu7C" +
                                "+QPvBx1FCzhk0WSfqkiclNI+SEFU4pyUOblklCFzxwpIkUwOPDI0LG88uNQO" +
                                "m9psJ2+HHH7kt/99J/TMR6dzoF/GdeMOlQ0w1SOzAkWCR6SltUv6epGrOkQp" +
                                "kCBSH335R6/ys0tW2CIXj13R/BvfeuTi7J6745uDJJhdlVA6DJXjzk6sJU7N" +
                                "uMVnvJ/ly+sPn15zq/REkBSkU1KO9Ju9qcULAwg1GdQLDQHFkXIQ2uT3ZlOX" +
                                "mAxFxZW7eB49ET25szlICiGvQi3hFJwW0nSjX3hWKmzJeB2KKgIQYrqZoCpO" +
                                "ZWpBOY+b+qA7IsKsUjzXwPHghzTBZ0Y6WYj/OFtnIJ1hh6VYf7OgjUjmi7MN" +
                                "4mMTkgXoWbe6Pg6JTYXkiofRfK+W0GUlptA+lWG0/adq4dITn+2rtj1IhZHM" +
                                "6dw+PgN3/KZVZPeZB75oFGwCEhZWN+7cZXb41bmcW02Tbkc9Ug//eu6zb9Hn" +
                                "Ie9DrrWUHUykz4AwLSBsncnJ3FEppTtOZail2E0wgc4KsXaZoMsRvnSw4fe7" +
                                "kSzlpKaf8TWq3kfVLtiNh54R0OAVoCSgtKLxummM4icGljhHV4eDDfCZkz66" +
                                "OVM9umz1fQDUjwZAgiommK/NY3kESRvHSqVwOIJFeVpVU0lA9RxIl/fwztoL" +
                                "W/d/csROCP5ewLeY7R169Fpo31DQ0zAtGNWzePfYTZPQcboN5jX4C8DnKn7Q" +
                                "ABywi2ZtW7pyz3NKt2Ggs8/Pp5YQsfrjYztfP7RzTzANyJc4KenTdZVRbdyz" +
                                "FWHZCJ/b0md7240526C7oAXJSsHn/jzHGEXyLU6mgwN3QOjYziv25rdCYJgb" +
                                "1Hq3L7WbsJC4BwCweawqcK2CmgddOWDtN8+ruJJnbqutKBIplXH0apEjUKuQ" +
                                "rVU+PTgkXkWjomPtFzxFB7YFCdSMUkBLFFYrb0XrTkLF9nTEjynD777996qH" +
                                "bc/PjhhxKUpv9e8790HBV6bx5u+JSljYRy1xQKVQHSxcycm8sS9YgpcdDtPG" +
                                "PbmZ7skJ8c7BZUCsckEUC3B4e1a45AYhKnUkot0nzu25UwRy1YAC/TSTe9J3" +
                                "vuzs7nZuLVn3wJwwRaVPjj12av7FTXWiwc8g4u3S1lNjVJe2llpxGC8q+fCN" +
                                "N+s3ny0gwdWkXNWpvJriHQ86dSiszIpDU5oyvrZSBGnFYCnQapE9TbJwDJPT" +
                                "NolyFpUe2n/13b/sPH+6gBRDscaOg5pwPYD7R2ism7WXQXMPPN0Du6AVqLR3" +
                                "Q21yoAcXqHVGnb6DkzvG4i2ysa89wbspJH9mrtKTmoxsv+zreZKG4Z0VzlQx" +
                                "dWfaBa3ABMBzbE/nSFIrwqbSdUFsWL2T0KTWtUVau7ujPfd1tkc3tXZ1tK6K" +
                                "tAsnNWAy0IOPiVSe0/umospt1JTtwDt4rWzByobTK0TgjQZpisDMEsDQQR7G" +
                                "6qNrTHMzpGFrOKqtbx5DX89rmKj0+PufT9/0+S8uixjzd8y77NYwnRRN0uC/" +
                                "KqVD4qsjG75TrY5cASa9wESCVsbaaMLVTOzfnY6FaicWSIrkTMImkgEkTyJ5" +
                                "2puTJ1YH7OSbJ8//IM/c8355ifGqqZ3nn0OyH8kw5Pk4YNKmy3Y3twFJl61Q" +
                                "DycFSvpV1xOeQjOUEx+4meTufdsTBhfd6o6fNrxy10vD58XVDxm9gORFJAeR" +
                                "HJogfOM3fraDIT2SB75jE4RPsGsSKw4jOYrkOFRwti1J7YviNyYGkrASyU/E" +
                                "JiSv3CDLvYb9LM/czydv9GtIXkdyEtyF6/aLuBw9h2di4lCMIHkDyZuTgWIy" +
                                "MXQ6z9yZCeLhytvmBtIpJG8jeQcbOp0rse25wqhwQFfkSeLyKyTvIfnN/wuX" +
                                "c3nmfn89uHyA5EMkf+CkzMalVRWd5nuThOGCA8NHNxIG7w3Rf1iqrtlm/Dn/" +
                                "tbLR9fwOfFdlJg3o99pTEjMw8wkWn14PjH9CchHJX0GtQarwqSB42UHwb5NB" +
                                "cGJXsI+RvCj4/HPsXPyZWPDvSZerbWLZF0j+heSKa8+UQbh6g0DwBEugcBzT" +
                                "A8VTMz2AbhEoQlJy/aYHysVYCpJ45p0y3hhmjfoRyv7hRDo6XFXaMHzv78Qb" +
                                "PefHjbIIKY0lVdXTPHsb6WLDZDFF2Fhmd2PiShyohErhfw0DTo3/0IbAdHtZ" +
                                "DSfTPMs4KUk/eRfNgMYEFuHjTOgnSVYjafjbygVjXq7WJ+2f5qLSseF1Gx68" +
                                "fOdB0QQXQaO6YwdygbtHif0+UjDFF3zzx+SW4VW8dtGVyuNlCzMvTUQ7Wuvx" +
                                "slmeNHvof3BRPEAGHQAA");
}
