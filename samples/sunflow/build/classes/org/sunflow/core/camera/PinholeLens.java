package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class PinholeLens implements CameraLens {
    private float au;
    private float av;
    private float aspect;
    private float fov;
    public PinholeLens() { super();
                           fov = 90;
                           aspect = 1;
                           update(); }
    public boolean update(ParameterList pl, SunflowAPI api) { fov = pl.getFloat(
                                                                         "fov",
                                                                         fov);
                                                              aspect = pl.
                                                                         getFloat(
                                                                           "aspect",
                                                                           aspect);
                                                              update(
                                                                );
                                                              return true;
    }
    private void update() { au = (float) Math.tan(Math.toRadians(
                                                         fov *
                                                           0.5F));
                            av = au / aspect; }
    public Ray getRay(float x, float y, int imageWidth, int imageHeight,
                      double lensX,
                      double lensY,
                      double time) { float du = -au + 2.0F *
                                       au *
                                       x /
                                       (imageWidth -
                                          1.0F);
                                     float dv = -av + 2.0F *
                                       av *
                                       y /
                                       (imageHeight -
                                          1.0F);
                                     return new Ray(0, 0,
                                                    0,
                                                    du,
                                                    dv,
                                                    -1); }
    public static final String jlc$CompilerVersion$jl7 = "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425482308000L;
    public static final String jlc$ClassType$jl7 = ("H4sIAAAAAAAAAL1YYWwcxRWeW9t3tmNyFydxggl24jioSehtU5VKqRE0OdnE" +
                                                    "6YFPcYiEEVzGu3O+jfd2NrNz9sWpKUSqkqIqVGBoqKh/VEEUGgiqGgGqkPKn" +
                                                    "BQR/QFWr/ihU/VNUmh/5UYpKW/pmZvd2b+/OFKnqSTs3N/Pem/fmvfe9t3fp" +
                                                    "GuryGNrrUvvUnE15ltR49oR9W5afcomXPZy/rYCZR8ycjT3vKKwVjZGX0x9/" +
                                                    "+oNyRkPJGbQROw7lmFvU8Y4Qj9oLxMyjdLg6bpOKx1EmfwIvYL3KLVvPWx4f" +
                                                    "y6N1EVaORvOBCjqooIMKulRBPxBSAdMNxKlWcoIDO9w7iR5CiTxKuoZQj6Md" +
                                                    "jUJczHDFF1OQFoCEbvH7GBglmWsMba/brmxuMvjJvfrKDx/M/LwDpWdQ2nKm" +
                                                    "hToGKMHhkBnUVyGVWcK8A6ZJzBm0wSHEnCbMwra1JPWeQf2eNedgXmWkfkli" +
                                                    "seoSJs8Mb67PELaxqsEpq5tXsohtBr+6SjaeA1sHQluVhRNiHQzstUAxVsIG" +
                                                    "CVg65y3H5Gg4zlG3cfRbQACsqQrhZVo/qtPBsID6le9s7Mzp05xZzhyQdtEq" +
                                                    "nMLRYFuh4q5dbMzjOVLkaGucrqC2gKpHXoRg4WhznExKAi8NxrwU8c+1e24/" +
                                                    "f9o55GhSZ5MYttC/G5iGYkxHSIkw4hhEMfbtyT+FB14/pyEExJtjxIrmlW9f" +
                                                    "/+atQ1ffVDQ3taCZmj1BDF40Ls6uf3dbbvf+DqFGt0s9Szi/wXIZ/gV/Z6zm" +
                                                    "QuYN1CWKzWywefXIr+97+AXykYZ6J1HSoHa1AnG0waAV17IJu4s4hGFOzEnU" +
                                                    "QxwzJ/cnUQrmecshanWqVPIIn0SdtlxKUvkbrqgEIsQVpWBuOSUazF3My3Je" +
                                                    "cxFCKXhQFp5upD7ym6MTeplWiI4N7FgO1SF2CWZGWScGLTLiUn08N6XPwi2X" +
                                                    "K5jNe7pXdUo2XSwaVY/Tiu4xQ6dsLljWDcqIbkCgMawXLKdMbZInjpcVMef+" +
                                                    "X0+rCdszi4kEuGVbHBRsyKdD1DYJKxor1YPj118qvq3Vk8S/NY52wmFZ/7Cs" +
                                                    "OCyrDstGDkOJhDxjkzhUuR2cNg/pD8DYt3v6gcPHz410QLy5i51w44J0BMz1" +
                                                    "NRk3aC7EiEmJhAYE6taf3H82+8lzd6pA1dsDektudPXC4iPHvvMVDWmNyCws" +
                                                    "g6VewV4QeFrHzdF4RraSmz774ceXn1qmYW42QL0PGc2cIuVH4j5g1CAmgGgo" +
                                                    "fs92fKX4+vKohjoBRwA7OYZYB1gaip/RkPpjAYwKW7rA4BJlFWyLrQD7enmZ" +
                                                    "0cVwRQbHejnfAE5ZJ3JhAJ4+Pznkt9jd6Ipxkwom4eWYFRKmJ167+vSVH+3d" +
                                                    "r0URPR2pkdOEK3zYEAbJUUYIrP/hQuGJJ6+dvV9GCFDsbHXAqBhzgBbgMrjW" +
                                                    "77558vcfvH/xN1oYVRzKZnXWtowayLglPAWwxAY8E74fvdepUNMqWXgWIheC" +
                                                    "85/pXfuu/PV8RnnThpUgGG79fAHh+o0H0cNvP/j3ISkmYYhaFloekqkL2BhK" +
                                                    "PsAYPiX0qD3y3s1Pv4F/DFAL8OZZS0QiFpKWIXn1unTVHjlmY3v7xLDdbdqr" +
                                                    "yZWt8lcnHL27fRJNiJIcSb5/TNmzZ/70ibSoKX1aVKIY/4x+6ZnB3B0fSf4w" +
                                                    "jgX3cK0ZjaB9CXm/+kLlb9pI8lcaSs2gjOH3RsewXRXRMgP9gBc0TNA/New3" +
                                                    "1nZVyMbqebotnkORY+MZFKIgzAW1mPfGkkbmyI3w9PhJ0xNPmgSSk/2SZUSO" +
                                                    "u8TwpSBmUy6zFrBovJCGq2v7qMCsChTTBb/a68v9H8w/8+GLCiDjDokRk3Mr" +
                                                    "j36WPb+iRfqnnU0tTJRH9VDS4huUxZ/BJwHPv8UjLBULqob25/xCvr1eyV1X" +
                                                    "JOKOtdSSR0z8+fLyL3+6fFaZ0d/YPoxDd/zib//1TvbCH99qUZ3AZRTLnM2o" +
                                                    "6P/aF/fNpBjGxPUviNnB9tKG4On1pfW2kZb3pSWx50Lw/Q8kTvkSO0o0qqB0" +
                                                    "zGgkwRNyvpmjm5pKdk6WbFGqhUtubtd5SndcPLOyak49u0/zsWWCox5O3S/b" +
                                                    "ZIHYkeM6hKSGKn637LXDPH70+Z+9wt/d+w3l2D3t4zrO+MaZvwwevaN8/AvU" +
                                                    "7uGYTXGRz9996a27bjEe11BHHQ6aXh8amcYaQaCXEXjfcY42QMFQ3Z0bg3Dr" +
                                                    "993Z37J+hk4LkVyT96kF7htqcp80lcDbiSgVAdlAlGxafR8oTMpjjq9RK0pi" +
                                                    "eAACtOqakKaS5k4x5FS1GAdMmqXQ2WGnuaDIhfuac2zQN3qwrdFja6hE19g7" +
                                                    "KQZ7TXU7F6hlfq6uabG4GZ5hX9fh/9pBKSkxVU++g6206IC3VDE93WozaVJo" +
                                                    "TWTgPCQHedjSGnafEcMicM4RfgSfCry+qSk4YLNF4edoXaRBF23H1qZ/BdSb" +
                                                    "rPHSarp7y+q9v5MtZ/1tswde+UpV246Wwcg86TJSsqSmPaooKkD6Hkdb2rwx" +
                                                    "gC1qIrU9p+i/z1EmTg8eFV9RssfAnggZhKg/ixI9Dk4AIjF9wg0uLCO7LdEO" +
                                                    "ZFU7UEMREBMNZ/RXQ/cpcEr+4xJgSlX951I0Lq8evuf09a8/KwGqy7Dx0pKQ" +
                                                    "0p1HKdV413FpR1tpgazkod2frn+5Z1eAt+vF0O932zHdhls3peMVl8s2cunV" +
                                                    "Lb+4/bnV92VX/B+XgetDChMAAA==");
}
