package org.sunflow.core.display;
import java.io.IOException;
import javax.swing.JPanel;
import org.sunflow.core.Display;
import org.sunflow.image.Color;
@SuppressWarnings("serial") 
public class ImgPipeDisplay extends JPanel implements Display {
    private int ih;
    public ImgPipeDisplay() { super(); }
    public synchronized void imageBegin(int w, int h, int bucketSize) { ih =
                                                                          h;
                                                                        this.
                                                                          outputPacket(
                                                                            5,
                                                                            w,
                                                                            h,
                                                                            Float.
                                                                              floatToRawIntBits(
                                                                                1.0F),
                                                                            0);
                                                                        System.
                                                                          out.
                                                                          flush();
    }
    public synchronized void imagePrepare(int x,
                                          int y,
                                          int w,
                                          int h,
                                          int id) {
        
    }
    public synchronized void imageUpdate(int x,
                                         int y,
                                         int w,
                                         int h,
                                         Color[] data) {
        int xl =
          x;
        int xh =
          x +
          w -
          1;
        int yl =
          ih -
          1 -
          (y +
             h -
             1);
        int yh =
          ih -
          1 -
          y;
        this.
          outputPacket(
            2,
            xl,
            xh,
            yl,
            yh);
        byte[] rgba =
          new byte[4 *
                     (yh -
                        yl +
                        1) *
                     (xh -
                        xl +
                        1)];
        for (int j =
               0,
               idx =
                 0;
             j <
               h;
             j++) {
            for (int i =
                   0;
                 i <
                   w;
                 i++,
                   idx +=
                     4) {
                int rgb =
                  data[(h -
                          j -
                          1) *
                         w +
                         i].
                  toNonLinear().
                  toRGB();
                int cr =
                  rgb >>
                  16 &
                  255;
                int cg =
                  rgb >>
                  8 &
                  255;
                int cb =
                  rgb &
                  255;
                rgba[idx +
                       0] =
                  (byte)
                    (cr &
                       255);
                rgba[idx +
                       1] =
                  (byte)
                    (cg &
                       255);
                rgba[idx +
                       2] =
                  (byte)
                    (cb &
                       255);
                rgba[idx +
                       3] =
                  (byte)
                    255;
            }
        }
        try {
            System.
              out.
              write(
                rgba);
        }
        catch (IOException e) {
            e.
              printStackTrace();
        }
    }
    public synchronized void imageFill(int x,
                                       int y,
                                       int w,
                                       int h,
                                       Color c) {
        int xl =
          x;
        int xh =
          x +
          w -
          1;
        int yl =
          ih -
          1 -
          (y +
             h -
             1);
        int yh =
          ih -
          1 -
          y;
        this.
          outputPacket(
            2,
            xl,
            xh,
            yl,
            yh);
        int rgb =
          c.
          toNonLinear().
          toRGB();
        int cr =
          rgb >>
          16 &
          255;
        int cg =
          rgb >>
          8 &
          255;
        int cb =
          rgb &
          255;
        byte[] rgba =
          new byte[4 *
                     (yh -
                        yl +
                        1) *
                     (xh -
                        xl +
                        1)];
        for (int j =
               0,
               idx =
                 0;
             j <
               h;
             j++) {
            for (int i =
                   0;
                 i <
                   w;
                 i++,
                   idx +=
                     4) {
                rgba[idx +
                       0] =
                  (byte)
                    (cr &
                       255);
                rgba[idx +
                       1] =
                  (byte)
                    (cg &
                       255);
                rgba[idx +
                       2] =
                  (byte)
                    (cb &
                       255);
                rgba[idx +
                       3] =
                  (byte)
                    255;
            }
        }
        try {
            System.
              out.
              write(
                rgba);
        }
        catch (IOException e) {
            e.
              printStackTrace();
        }
    }
    public synchronized void imageEnd() {
        this.
          outputPacket(
            4,
            0,
            0,
            0,
            0);
        System.
          out.
          flush();
    }
    private void outputPacket(int type, int d0,
                              int d1,
                              int d2,
                              int d3) { this.
                                          outputInt32(
                                            type);
                                        this.
                                          outputInt32(
                                            d0);
                                        this.
                                          outputInt32(
                                            d1);
                                        this.
                                          outputInt32(
                                            d2);
                                        this.
                                          outputInt32(
                                            d3);
    }
    private void outputInt32(int i) { System.
                                        out.
                                        write(
                                          i >>
                                            24 &
                                            255);
                                      System.
                                        out.
                                        write(
                                          i >>
                                            16 &
                                            255);
                                      System.
                                        out.
                                        write(
                                          i >>
                                            8 &
                                            255);
                                      System.
                                        out.
                                        write(
                                          i &
                                            255);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1166161668000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVZe2wUxxkf3/l5duIXb4gNBuryugukEMCJwDGGnDnAtcEO" +
       "BnIZ786dF/Z2N7tz\n57ODaKJIQIPShiZVW7UhUFEZKBSqtHWjpBSa0JLQSk" +
       "mkJlKk0FZIbaU2VdNKKVX7R7+Z3b193CMp\nCEs7N7vzfd/M73vNN+OzH6EK" +
       "Q0dzBCNMxzRihLv6e7FuELFLxoaxHT7FhSsVNb0TmxU1gMpiKCCJ\nFNXHBC" +
       "MiYoojkhiJbujI6miJpspjSVmlYZKl4b3ySkteT2xlnsDBY5NNT50sbw2gih" +
       "iqx4qiUkwl\nVemWScqgqCG2F2dwJE0lORKTDNoRQ3cRJZ3qUhWDYoUaj6MD" +
       "KBhDlZrAZFI0L2ZPHoHJIxrWcSrC\np4/08mlBQrNOKJYUInbmpgPOpV5OWL" +
       "bF15dPDUKq2eAAwOErANRzc6hNtHlQteCpgVX7j58Oovoh\nVC8p/UyYAEgo" +
       "zDeE6lIkNUx0o1MUiTiEGhVCxH6iS1iWxvmsQ6jJkJIKpmmdGH3EUOUMI2wy" +
       "0hrR\n+Zz2xxiqExgmPS1QVc/pKCERWbTfKhIyTgLsaQ5sE+5G9h0AhiRYmJ" +
       "7AArFZyvdJCli81c+Rw7hg\nMxAAa1WK0BE1N1W5guEDajJtKWMlGemnuqQk" +
       "gbRCTcMsFM0qKpTpWsPCPpwkcYpm+Ol6zSGgquGK\nYCwUTfWTcUlgpVk+K7" +
       "nss2TaJ4dPfefieu7b5SIRZLb+EDC1+Jj6SILoRBGIyXgzHX4hujM9J4AQ\n" +
       "EE/1EZs0nQsnd8T+/PNWk2Z2AZptw3uJQOPC1qOtfU9sUlGQLaNaUw2JGd+D" +
       "nIdDrzXSkdUgaqfl\nJLLBsD14qe+XO588Q/4SQKEoqhRUOZ0CP2oU1JQmyU" +
       "TfRBSiY0rEKKohitjFx6OoCvoxcHnz67ZE\nwiA0ispl/qlS5e+gogSIYCqq" +
       "gb6kJFS7r2E6wvtZDSFUBQ96EJ5aZP7xX4rWhiNGWknI6mjE0IWI\nqidz74" +
       "Kqk4goGZqMxyLRVLJX0sgG8zXMfEijaCAyoqZIBAtYkRQ1kpQgagV1mUgy7P" +
       "eWJWfZuptG\ny8pYIvQHtAyx8LAqi0SPCxM33trfvfnLh01nYQ5uIaaoHSYM" +
       "WxOG2YRha8Kwd0JUVsbnmcImNs0G\nSt8H4QuJrm5R/56exw63BcFftNFy0B" +
       "gjbQNs1mq6BbXLifEoT4cCONqM7+46FL45sc50tEjxVFyQ\nu/btc9eO/7Nu" +
       "cQAFCudJhhIydYiJ6WXJNZf/Fvgjq5D8vz2z5eX3rn34eSfGKFqQF/r5nCx0" +
       "2/z2\n0FWBiJAMHfEnZ9YHB9HA0QAqh3wAOZCvH9JLi38OTwh32OmQYamKod" +
       "qEqqewzIbsHBaiI7o66nzh\njtLA+822T8+Ep8lycv7LRqdqrJ1mOhaztg8F" +
       "T7c3n6689/3Xaq9wtdiZud619/UTasZ5o+Ms23VC\n4PuH3+x9/usfHdrFPc" +
       "VyFQobYnpYloQssHzOYYEAlyHJMEMu2KGkVFFKSHhYJszj/lu/cPmP//qV\n" +
       "BtM0MnyxLbv00wU432c+hJ689ui/WriYMoFtMA4Mh8xE0+xI7tR1PMbWkX3q" +
       "3Xu+9Sv8IuQ/yDmG\nNE54GkEcGeJ6jHC9L+Zt2De2nDVtIHtpEdcvsJ3Hhf" +
       "1nkm3px998ha+6FrvrArcZtmCtw7Q8a+Yz\n7U73R+/D2BgBui9c2rq7Qb70" +
       "H5A4BBIF2EaNbTrkjqzHiBZ1RdUHl1+f9tg7QRTYiEKyisWNmPs/\nqgHHI8" +
       "YIpJ2stm49962G0WrWcsiIK2GWpYCs643li0XFw38jKwacyIkPLz0Ve2vbi1" +
       "wBRQO/wF7o\nkzN+ccexm7+h17kcJwIZ97xsfk6FAsrhXf1eprHywkupAKoa" +
       "Qg2CVeINYDnN/HwIKhLDrvugDPSM\ne6sLcyvtyGWYOf7od03rj30nl0OfUb" +
       "N+nS/c65i2cx371x3uZYh31nGWBbxtzwVnlaZLGczKPqih\nR8BOM9w1ty6l" +
       "YO/O8Mx042Dbz67ueOmQmc1LmNPDFRe+9Lvf7wt+9fKwyee3mY/4aMvJP758" +
       "o2+K\nGflmkTc/r85y85iFHldKvcaiYF6pGTj1G0vmnT3Qd91aUZO3XOmGkv" +
       "5PY6+T9gee/UOBHTUIpaiZ\nP1m7gjXrTVdfWSgksgWUzvqLvOHB+tOhrsrb" +
       "rq39meG6p1i5yDEdeuTjuoP4jT0BK/dsphCuqrZM\nJhkiu+aqYpI8W/cWXi" +
       "A7rv/M6e9P0neWrDW1s7i4nf2Mi9ceH29de/7ILWzYrT5sftGNmdlfDI5I\n" +
       "VwO8hjcjKa/29zJ1eOMnBOtJ68p2TxTNzUXRDGaubnimWlE0tfCmmWfNALcm" +
       "RXXGmCLAvqzAJiFm\nfZtCkJMG2WvUabhIocT+wVe6h0KyScGx4iGSlEwddm" +
       "vmBD0UlWdUSXRc8dESrujJzvxlpxf+Fnhm\nW/Bn/1/wWYP9oCs4QYUPtA++" +
       "XgI+j7MUqJbD79UJnKC5SpIOYOV2APfB02IBbrmjgFmx5cqrvMBg\nrnj6uQ" +
       "3NfWt2Pc1DpgaO19jY6vhsQBJZrwwCZGHxMMwJiwvteyb/fvkiaecbfbVkwN" +
       "7SqScLHDVd\nPB/jM2TL+4ljvE4tH8aGGR/+M3r+EdxzsubqujunXhZJqKeU" +
       "eu2UN92d8rihWWGn6pqmwcEsxFWy\nfOWK+1eBCptAhex6JyyJ4ZgqYDm64c" +
       "Tl2nePplf1mMnqLhdBdMP+Cz111SeOHORJw9Jljesca71X\nZbC+1Unx7Ocg" +
       "RfE7c6hbe9+9S1etWbZ6OYQuWynX24ESQfAsa8YoquW62aEBkz8Gxm8nBjbD" +
       "s9oy\n0uo7GwOfYnE+2zdKqOLbrPka7GucaaMkyz5FPH87imi3EoKdGG5TEe" +
       "6Ff6/E2ARrjlOIVgaqWxF9\nmE7cIiZeBW6CZ9DCNFgQE2s6byVxny+B6Yes" +
       "OQuJW01TLU3ZtRihPlznbgfXfHjiFq74Z8ZV5pRf\nJoZXSmB4lTU/gbgzMU" +
       "QVet8KH4TJzwoBauu7vbmAHThn5F3SmheLQuyDJ3Z/Evvtv82MbF/+1ULm\n" +
       "SqRl2X0mcPUrNZ0kJL70WvOEoPGfXxSqKq0EBdW/1ePLvWxyXKGowc8B2Yr9" +
       "uMmuMt04ZCDL6rmJ\nrkHBDESs+2vNTgBN/HYrbIxKSjLc04sVImc9+mLame" +
       "/Z8PjduV0bps3b87jwyLldc7NHtj/Hd88K\nQcbj40xMCHK6eWuSqy/nFZVm" +
       "y3obXTg/8NoP1tj1Mz9VT3EFvscfV5ijHvvX5eyfq+dn8cM1OweG\n+9MaWM" +
       "gwBrEOG0rSKMZKUUWGnSJBBfcX2fM7Pf+ZINax0z6OxoUXdr855R+Nza9ytV" +
       "TzsylQsPke\nLHGO5LdEs+AJWYEV8gcWRZUG/yeADa/BBY9fomeZ50M1X/iS" +
       "pjulUX6tMv7T6T96YOLY9QC/Jvof\nSkcot+wZAAA=");
}
