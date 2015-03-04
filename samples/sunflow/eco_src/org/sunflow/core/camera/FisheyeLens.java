package org.sunflow.core.camera;
import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;
public class FisheyeLens implements CameraLens {
    public boolean update(ParameterList pl, SunflowAPI api) { return true;
    }
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX,
                      double lensY,
                      double time) { float cx = 2.0F * x / imageWidth -
                                       1.0F;
                                     float cy = 2.0F * y / imageHeight -
                                       1.0F;
                                     float r2 = cx * cx + cy * cy;
                                     if (r2 > 1) return null;
                                     return new Ray(0, 0, 0, cx, cy,
                                                    (float)
                                                      -Math.
                                                      sqrt(
                                                        1 -
                                                          r2)); }
    public FisheyeLens() { super(); }
    final public static String jlc$CompilerVersion$jl = "2.5.0";
    final public static long jlc$SourceLastModified$jl = 1163483360000L;
    final public static String jlc$ClassType$jl = ("H4sIAAAAAAAAAJ1Xe2wURRif3vVBH6SlQEHQFgqivG4lEROpidRa5OSQegXE" +
                                                   "otbp7tzdwt7Osjvb\nXgtBjYmgxgdRE018EEOCL8QEDZr4wPiWf9RETUx8hU" +
                                                   "RNfCTGRDH6h998c3u3t9dW4yW3OzvzPeZ7\n/eab534mdZ5LztW9hBh3mJfo" +
                                                   "GxygrseMPot63laYGtbfqWscOLrJ5jFSkyIx0xCkNaV7mkEF1UxD\nS17ZU3" +
                                                   "DJSodb41mLiwQriMQua21R3tWptVUCr3v8ZPttR2q7YqQuRVqpbXNBhcntfo" +
                                                   "vlPUHaUrvo\nKNV8YVpayvRET4rMZLaf7+O2J6gtvD1kP4mnSL2jS5mCLE4F" +
                                                   "yjVQrjnUpXkN1WsDqBYkzHaZoKbN\njN6SOuBcVckJ2y7ypaupQcgMubgdzM" +
                                                   "EdgNWLSlYra6tMdeJPbb9k3+Gn46R1iLSa9qAUpoMlAvQN\nkZY8y48w1+s1" +
                                                   "DGYMkVk2Y8Ygc01qmROodYi0e2bWpsJ3mZdmHrdGJWG75zvMRZ3BZIq06NIm" +
                                                   "19cF\nd0s+ypjMMoKvuoxFs2B2R9lsZe4GOQ8GNpmwMTdDdRaw1O42bYh4V5" +
                                                   "SjZOPSTUAArA15JnK8pKrW\npjBB2lUsLWpntUHhmnYWSOu4D1oEWTClUOlr" +
                                                   "h+q7aZYNCzI/SjegloCqER0hWQSZGyVDSRClBZEo\nheKzsuP3g089+vp6zO" +
                                                   "1ag+mW3H8TMHVGmNIsw1xm60wxnvUTDyav98+NEQLEcyPEiqb3/JPbUj+8\n" +
                                                   "0aVoFk5Cs2VkF9PFsH7Noa703qs4icttzHC4Z8rgV1iO5TBQXOkpOFC1HSWJ" +
                                                   "cjERLJ5Kv3v9rc+w\nH2OkKUnqdW75ecijWTrPO6bF3KuYzVwqmJEkjcw2+n" +
                                                   "A9SRpgnIKUV7NbMhmPiSSptXCqnuM3uCgD\nIqSLGmFs2hkejB0qcjguOISQ" +
                                                   "BviTBPxnEPXDtyCXJDTPtzMWH9M8V9e4my1969xlmg5J41Jtg+nl\n2DhLMd" +
                                                   "tLyPxxBElrOZ5nGtWpbdpcy5pQsTpfbbBR+f5fUgtyv+1jNTUSAKOFbEENbO" +
                                                   "SWwdxh/eiZ\nD/f1b7rzoEoSmdhFSwVZAsoSRWUJqSyhlCVCykhNDeqYI5Wq" +
                                                   "UIGjd0PJAri1LB+88eqbD3bHIUec\nsVrwkiTtBpuKO+nXeV+5rpMIgTok1/" +
                                                   "wndx5InD16uUoubWr4nZS7+aNjpw//1rIiRmKTY6O0ENC5\nSYoZkIBawryl" +
                                                   "0WqaTP4vd20+8dnpLy8s15UgS6vKvZpTlmt3NBYu15kBAFgWf+Sc1vh1ZPuh" +
                                                   "GKkF\nDADcw/0DpHRGdVSUbU8AgdKWhhRpznA3Ty25FOBWk8i5fKw8g0nSJh" +
                                                   "9zVL7IQEY2iOh59vb6iz5/\ntfkdtDgA2tbQUTbIhCrbWeU82OoyBvNfPjzw" +
                                                   "wEM/H9iJSVDMAgHnmz9imXoBWJaVWaBeLcAMGaOl\n2+w8N8yMSUcsJpPp79" +
                                                   "bz17z0071tyusWzARBW/XvAsrz51xBbj190x+dKKZGl+dF2YwymbJmdlly\n" +
                                                   "r+vScbmPwm2fnPfIe/QxgDOAEM+cYIgKBC0j6McEunc5PldH1i6Sj26QvWqK" +
                                                   "rJ7kdB7W9z2T7fb3\nfPAK7rqZho/5cBg2U6dHBRV1zwalGik+KtBKrs515L" +
                                                   "NDhmBetHo3Ui8Hwi4+dc0Nbdapv0DtEKjV\n4ej0triAG4WKSBep6xq+ePOt" +
                                                   "jps/jpPYBtJkcWpsoJj/pBESj3k5gJyCc/l63EbbmNxIG/qF4G4X\nFL1UqP" +
                                                   "jCjyX4XFbMHjm+MExVg+N5giysQqw+RCyJVNLO86Y6LPGgP7Dj15Y76Ns3Kt" +
                                                   "RprzyA+qFJ\n+378LXbBZfd8OwlWNgrurLbYKLNC+4pJlRVotxn7iHKt3/X0" +
                                                   "syfFxyvXKZUrpga6KOOKdYcnutYd\nv/t/YFxXxAlR0bNGF14bz5nvx7DVUf" +
                                                   "BW1SJVMvWE3QFKYT++a0vHypkWTMlFpZRsloHtgX9jMSUb\noymJYCQf6yKV" +
                                                   "FCv6tRjvzqp4o6kMOjBZqgFZR5hsUL17B5KoZtM0tXqtfGwEsPIduBAwiOb8" +
                                                   "8F3C\nNfPQk4wi+p65o/u197c9cUAFcvk0F4Yw17B+y9ff7I7f9+aI4ov2ZR" +
                                                   "HiQ51HvjtxJj1H5Z9qXpdU\n9Y9hHtXAojGtjqyAxdNpQOq3Vy5+bn/6K9yR" +
                                                   "5FsvSMMI5xajKqnWyEdSld/afy1m/LiyMvR74D+z\nGPqZ/zn0DSixAb8HHS" +
                                                   "V4h5CnEad4FIxEl+LQ8Mshiy7UGxxOH8xN7OtyqJVOkwnYOd8EnFkm0nQ8\n" +
                                                   "SKw5VfkXLKKXhv+rlwqCNIeaKnn0zK+6fakbg576Yu8Nv6c+/RPbg1JX3wyt" +
                                                   "dca3rFAVhiuy3nFZ\nxkRTmtUB4eDLF2TeFF0eGKsGuFOh6GGjbVF6QWrlK0" +
                                                   "y2F+wJkUEGFUdhov0QISCSw1ucwKNteKrI\nK1VC3R8qTwPpmSUVxYUX4gDJ" +
                                                   "fHUlHtZ3HNu5qHD31vsRHuvgKj0xgXcfuMqptqiEhounlBbI+oi8\ncHz7q8" +
                                                   "9fGhRERcNUletr1Oo0gQcEnrxh6c87AluMiZfnvXjZ0ce/imHL9A+71csVxx" +
                                                   "AAAA==");
}
