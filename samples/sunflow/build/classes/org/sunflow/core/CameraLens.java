package org.sunflow.core;
public interface CameraLens extends RenderObject {
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time);
    String jlc$CompilerVersion$jl7 = "2.6.1";
    long jlc$SourceLastModified$jl7 = 1425485134000L;
    String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAK1WW2xURRie3bbbbindtlBACi29QFKKe0IMGiwayqaF4kKb" +
                                "tmgskWV6zuz20HNjzmy7LdYgiYHwQIwWBaN9gijKLUaCxpDwJBB8wRiND4Jv" +
                                "GpUHXvQBFf+Z2cvZ0xZf3OTMzuW/zv//3z/n76Myl6IOxzYmU4bNoiTDogeM" +
                                "TVE26RA3ujO+qR9Tl2gxA7vuEOwl1JbLkT8evjlaE0ShYbQEW5bNMNNtyx0g" +
                                "rm2MEy2OIoXdboOYLkM18QN4HCtpphtKXHdZZxwt8rAy1BbPmaCACQqYoAgT" +
                                "lK4CFTAtJlbajHEObDH3IHoNBeIo5KjcPIaai4U4mGIzK6ZfeAASKvj6RXBK" +
                                "MGcoWpP3Xfo8x+GTHcrMu/tqPi1BkWEU0a1Bbo4KRjBQMoyqTGKOEOp2aRrR" +
                                "hlGtRYg2SKiODX1K2D2M6lw9ZWGWpiR/SXwz7RAqdBZurkrlvtG0ymyady+p" +
                                "E0PLrcqSBk6Br8sKvkoPe/g+OFipg2E0iVWSYykd0y2NoSY/R97HtheAAFjL" +
                                "TcJG7byqUgvDBqqTsTOwlVIGGdWtFJCW2WnQwtDKBYXyu3awOoZTJMHQCj9d" +
                                "vzwCqrC4CM7CUL2fTEiCKK30RckTn/u7t5w4ZO2wgsJmjagGt78CmBp9TAMk" +
                                "SSixVCIZq9bH38HLrh0LIgTE9T5iSXP11QdbNzRevylpGuah6Rs5QFSWUM+M" +
                                "VN9ZFWvfXMLNqHBsV+fBL/JcpH9/9qQz40DlLctL5IfR3OH1ga9ePvwx+S2I" +
                                "KntRSLWNtAl5VKvapqMbhG4nFqGYEa0XhYmlxcR5LyqHeVy3iNztSyZdwnpR" +
                                "qSG2QrZYwxUlQQS/onKY61bSzs0dzEbFPOMghMrhQwH4ViH5ExsM9SmjtkkU" +
                                "rGJLt2wFcpdgqo4qRLUVF5uOAVFz01bSsCcUl6qKTVP5tWpTosQgqyiOE8uN" +
                                "8sRy/n+RGe5FzUQgABe8yl/eBlTGDtvQCE2oM+lt3Q8uJm4H8+me9Z+hBlAS" +
                                "zSqJciXRghIUCAjZS7kyGTi49jEoYIC2qvbBV3buP9ZSAhnjTJTyS8uIilqR" +
                                "WwCjzyhRuz1fXD995b2OzUFvmUc8wDlImEya2oLeIUoI7P94qv/tk/eP7hVK" +
                                "gaJ1PgVtfIxBCgEuAr68cfPgD/funvk2mDe0hAGWpkcMXWWoAo8AEGGVMRTO" +
                                "I4rXkYCY1zO0es5NDUD2ESoLg7u7eqHiFcBz5sjMrNZ3dqMssbrigugGvL/w" +
                                "3d9fR0/9dGueKIWZ7TxpkHFiFFkGKiF3stq6VXuXwLVe0TZUqOrj5z65yu50" +
                                "PCtVrl+4+/kZbxz5deXQ86P7gyhY3MG4dtiq5Jz9vO/k+0uTz3m/yHO7zt/a" +
                                "vk59K4hKsvA1D1QXM3V6rwGUUgK9xeIXyncqQWmLP+uprRINGlBB7/o1+Eri" +
                                "2nRbEJUCBkPfYRhwAiC90a+8CDY7c7nJVZXBJSRtamKDH+X6RiUbpfZEYUeU" +
                                "Y7WY10J4IrwK6uFryAKL+OenSxw+LpXlK+gbxNjIh2YR2yCftvChlWfWukIl" +
                                "AAgakG88GG17LNPW9KSORwzCa/KvyNqNV34/USMzyICdXHQ2/LeAwv4T29Dh" +
                                "2/v+bBRiAipvwoXqLJDJIl1SkNxFKZ7kdmRe/2b16Rv4A+gRgMuuPkUk1ArX" +
                                "yoGp/TEPMaqb0BvGs81Lma67N/b+LxdkCvs7nY+YHJs5/ih6YiboeQ60zunI" +
                                "Xh75JBCRWCwj9wh+Afj+4R+PGN+QLaEulu1La/KNyXF4eJofZ5ZQ0fPzpekv" +
                                "P5o+yt3gatoZTy8biwBtFZqf40OXhJ4YQyWARnzaM99hSLMBv0QZ9IpBCN0i" +
                                "jp8RYyc3OYvEfL2LD08BZ4qwATyZA7Wlc0ENTzpzJGQYqiz0BR72FXOek/IJ" +
                                "pF6cjVQsn93zvai3/DMlDG+FZNowPOXsLe2QQ0lSF4aGZRk54m8IntR+Cxkq" +
                                "5X/CxkFJ9hJDizxkDHJMzrxEw3CnQMSneyFsqKhZOf7W1VqUoeKJnQO2tHxk" +
                                "J9RLszt3H3rw9FmBkmXwOJ+aEk8yeGFKtMiDY/OC0nKyQjvaH1ZfDq/NJUg1" +
                                "H+o8EOGxrWn+Yu42HSbKb+rz5Z9t+XD2ruh4/wK6ta6E+wwAAA==");
}
