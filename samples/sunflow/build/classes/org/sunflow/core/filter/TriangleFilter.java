package org.sunflow.core.filter;
import org.sunflow.core.Filter;
public class TriangleFilter implements Filter {
    private float s;
    private float inv;
    public TriangleFilter(float size) { super();
                                        s = size;
                                        inv = 1.0F / (s * 0.5F); }
    public float getSize() { return s; }
    public float get(float x, float y) { return (1.0F - Math.abs(x * inv)) *
                                           (1.0F -
                                              Math.
                                              abs(
                                                y *
                                                  inv)); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 = 1425485134000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAALVXX2wURRif27bXP7S9Uv7VCm0phdgWbkWiRmtQuLRSPKWh" +
                                                    "FeOhlune3HXp3u66O9ce1aqQmBIeiNGCaLQPBqMoCjESNMakL/6LvmCMxgfF" +
                                                    "+KJReeBBJKLiNzO3f27vWvXBS3Zvdub7O9/3/eabkxdQhW2hbtPQ9qU1g0ZJ" +
                                                    "jkb3ajdG6T6T2NHt8RsHsGWTZEzDtj0Ec8NK++nIpStPjTZIKJxAS7CuGxRT" +
                                                    "1dDtncQ2tHGSjKOIN9urkYxNUUN8Lx7HcpaqmhxXbdoTR4t8rBR1xB0TZDBB" +
                                                    "BhNkboK8xaMCpjqiZzMxxoF1aj+MHkOhOAqbCjOPotWFQkxs4UxezAD3ACRU" +
                                                    "se9d4BRnzlmozfVd+Fzk8JFueebZhxreKkORBIqo+iAzRwEjKChJoNoMyYwQ" +
                                                    "y96STJJkAi3WCUkOEkvFmjrJ7U6gRltN65hmLeJuEpvMmsTiOr2dq1WYb1ZW" +
                                                    "oYblupdSiZZ0vipSGk6Dr8s9X4WHfWweHKxRwTArhRXisJSPqXqSotYgh+tj" +
                                                    "x11AAKyVGUJHDVdVuY5hAjWK2GlYT8uD1FL1NJBWGFnQQlHzvELZXptYGcNp" +
                                                    "MkxRU5BuQCwBVTXfCMZC0bIgGZcEUWoORMkXnwv33Hb4EX2bLnGbk0TRmP1V" +
                                                    "wNQSYNpJUsQiukIEY21X/Che/v5BCSEgXhYgFjRnH714x/qWuY8FzbUlaHaM" +
                                                    "7CUKHVaOj9SfWxnrvKWMmVFlGrbKgl/gOU//gfxKT86EylvuSmSLUWdxbueH" +
                                                    "9z/xGvlZQjX9KKwYWjYDebRYMTKmqhHrTqITC1OS7EfVRE/G+Ho/qoRxXNWJ" +
                                                    "mN2RStmE9qNyjU+FDf4NW5QCEWyLKmGs6inDGZuYjvJxzkQIVcKDNsBTgcSP" +
                                                    "/1OUkEeNDJGxgnVVN2TIXYItZVQmiiHbOGNqEDU7q6c0Y0K2LUU2rLT7rRgW" +
                                                    "kUE9pI48BCWipzXSxz+jLMfM/1V6jvnWMBEKwbavDBa9BvWyzdCSxBpWZrJb" +
                                                    "ey++Ofyp5BZBflcoWgf6onl9UaYvKvRFC/WhUIirWcr0ishCXMagwgH7ajsH" +
                                                    "H9y+52B7GaSUOVEOm8pI28HDvDG9ihHzYKCfg50Cudj00u7p6OVXbhe5KM+P" +
                                                    "2SW50dyxif27Hr9eQlIh+DLnYKqGsQ8wyHShsSNYdKXkRqZ/vHTq6JThlV8B" +
                                                    "mudRoZiTVXV7MAyWoZAk4KQnvqsNnxl+f6pDQuUAFQCPFEM6A/K0BHUUVHeP" +
                                                    "g5TMlwpwOGVYGayxJQfeauioZUx4Mzw/6vl4MQRlEUv3a+Cpyuc//2erS0z2" +
                                                    "XiryiUU54AVH4r53554783z3LZIftCO+Y3CQUAEBi70kGbIIgflvjg08c+TC" +
                                                    "9G6eIUCxppSCDvaOASBAyGBbn/z44a/Pf3v8C8nLKgonY3ZEU5UcyFjnaQG4" +
                                                    "0ACyWOw77tUzRlJNqXhEIyw5/4is3Xjml8MNIpoazDjJsP6fBXjz12xFT3z6" +
                                                    "0G8tXExIYceV57lHJjZgiSd5i2XhfcyO3P7PVz33EX4R0BQQzFYnCQelkFsv" +
                                                    "nQu0LJaaARQdz8O8PNV4fuyFH98QZRM8EwLE5ODMoavRwzOS7+BcU3R2+XnE" +
                                                    "4cmToU4kz1X4heD5iz0sadiEAM/GWB7B21wIN00WntULmcVV9P1wauq9V6em" +
                                                    "hRuNhedGL7RFb3z552fRY999UgK2IP8MTLmNMrexi7+jzCi+o4iv9bBXm1m0" +
                                                    "luMzTfxLWnjv+1iv4oOs33doIwe+v8xtKgKdEuEI8Cfkky80xzb/zPm96mfc" +
                                                    "rbliGIe+zuO94bXMr1J7+AMJVSZQg5JvGndhLctqLAGNku10ktBYFqwXNj3i" +
                                                    "hO9x0W1lMB98aoO448UBxoyajWsCUFPrQE04DzXhINSEEB/EOEs7f69lr+uc" +
                                                    "Sq80LXUcs44UqoOtbOLoJGK5+b9r6mevXorKVH3cJ44b3uFLB6FsGUUrik5G" +
                                                    "cRSy5F41X/PGE/v4gZnZ5I6XN0r5LLyZompqmBs0Mk60QOatKjgl7+btqhfx" +
                                                    "QydeP0vPdd8qSqRr/iwNMn504Kfmoc2je/7D2dga8Cko8sTdJz+5c53ytITK" +
                                                    "3MQp6sALmXoK06XGInBl0IcKkqbFDSV7UBM8dflQ1pU8n7yAla75xAJrD7DX" +
                                                    "fZBcaUIHAYJ5IhTDA58YLDRsqTNw/v+VYZIItJtwmzjpyAImJtkL7hdlYOI8" +
                                                    "5kFJ1Be2Z+zQaSq69omrivLmbKRqxey9X/GGw71OVENPn8pqmr+cfeOwaZGU" +
                                                    "yi2qFsUtSkUtVRiiZaTsYscG3OBRQQ9J0hCkp6ic/fnJDIoW+cggQvmRnwg0" +
                                                    "lAERG9qmU6YN/KxlsBYVsJZDvhJj7Yb/q6D3YFXEr9ROxmfFpXpYOTW7/Z5H" +
                                                    "Lt70Mi+fCriMT07yKxjcKEXb5VbN6nmlObLC2zqv1J+uXuugQT17NeZ7rYBt" +
                                                    "raVbkt6MSXkTMfnOirdve2X2W94T/Q0e6YEl6xAAAA==");
}
