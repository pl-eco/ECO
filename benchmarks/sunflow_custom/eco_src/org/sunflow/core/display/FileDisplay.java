package org.sunflow.core.display;
import org.sunflow.core.Display;
import org.sunflow.image.Bitmap;
import org.sunflow.image.Color;
public class FileDisplay implements Display {
    private Bitmap bitmap;
    private String filename;
    public FileDisplay(boolean saveImage) { super();
                                            bitmap = null;
                                            filename = saveImage ? "output.png"
                                                         : null; }
    public FileDisplay(String filename) { super();
                                          bitmap = null;
                                          this.filename = filename == null
                                                            ? "output.png"
                                                            : filename; }
    public void imageBegin(int w, int h, int bucketSize) { if (bitmap == null ||
                                                                 bitmap.
                                                                 getWidth() !=
                                                                 w ||
                                                                 bitmap.
                                                                 getHeight() !=
                                                                 h) bitmap =
                                                                      new Bitmap(
                                                                        w,
                                                                        h,
                                                                        filename ==
                                                                          null ||
                                                                          filename.
                                                                          endsWith(
                                                                            ".hdr"));
    }
    public void imagePrepare(int x, int y,
                             int w,
                             int h,
                             int id) {  }
    public void imageUpdate(int x, int y,
                            int w,
                            int h,
                            Color[] data) {
        for (int j =
               0,
               index =
                 0;
             j <
               h;
             j++)
            for (int i =
                   0;
                 i <
                   w;
                 i++,
                   index++)
                bitmap.
                  setPixel(
                    x +
                      i,
                    bitmap.
                      getHeight() -
                      1 -
                      (y +
                         j),
                    data[index]);
    }
    public void imageFill(int x, int y, int w,
                          int h,
                          Color c) { Color cg =
                                       c;
                                     for (int j =
                                            0;
                                          j <
                                            h;
                                          j++)
                                         for (int i =
                                                0;
                                              i <
                                                w;
                                              i++)
                                             bitmap.
                                               setPixel(
                                                 x +
                                                   i,
                                                 bitmap.
                                                   getHeight() -
                                                   1 -
                                                   (y +
                                                      j),
                                                 cg);
    }
    public void imageEnd() { if (filename !=
                                   null) bitmap.
                                           save(
                                             filename);
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1159026718000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVYfWwUxxWfu/O3nfoDsB0CGIwdGjC3IS0uwWkCGJMcHODa" +
       "mCQG4ox3584Le7ub\n3bnz2UEJSSog0I+gplKrNgRaJD4KhSqtSNWUQpO0aV" +
       "ClJFITKVJoK6S2UpuqUaWUqv2jb2Z2b/f2\nPkAh/WNnZ3fee/Pem99782ZO" +
       "f4gqbQvNke0onTKJHe0fHsSWTZR+Ddv2Fvg1Jr9eWTt4fINuhFEo\njsKqQl" +
       "FjXLYlBVMsqYoUW9uXtdAS09CmkppBoyRLozu15Y689fHlBQIfPHy+5aljFR" +
       "1hVBlHjVjX\nDYqpaugDGknZFDXFd+IMltJU1aS4atO+OLqF6OlUv6HbFOvU" +
       "fgw9gSJxVGXKTCZFC+Lu5BJMLpnY\nwimJTy8N8mlBwgyLUKzqRFmdmw44e/" +
       "I5QW2Hb6iQGoTUsMGtYA7XAKyen7NaWFtgqhk5sbV395GT\nEdQ4ihpVfZgJ" +
       "k8ESCvONooYUSY0Ty16tKEQZRc06IcowsVSsqdN81lHUYqtJHdO0RewhYhta" +
       "hhG2\n2GmTWHxO92ccNcjMJistU8PK+SihEk1xvyoTGk6C2a2e2cLcdew/GF" +
       "ingmJWAsvEZanYpeqw4h1B\njpyNXRuAAFirU4ROGLmpKnQMP1CLWEsN60lp" +
       "mFqqngTSSiMNs1A0u6RQ5msTy7twkoxR1B6kGxRD\nQFXLHcFYKJoVJOOSYJ" +
       "VmB1bJtz5LWj/ef+K7F1ZxbFcoRNaY/nXANC/ANEQSxCK6TATjtXT0+djD\n" +
       "6TlhhIB4VoBY0KzuPj8S/8svOgTNbUVoNo/vJDIdkzcd6hh6/H4DRZgaNaZh" +
       "q2zx8yzn4TDojPRl\nTYja1pxENhh1By8O/erhPafIX8OoLoaqZENLpwBHzb" +
       "KRMlWNWPcTnViYEiWGaomu9PPxGKqGfhwg\nL/5uTiRsQmOoQuO/qgz+DS5K" +
       "gAjmolroq3rCcPsmphO8nzURQtXwoCVux31T9IWoZKf1hGZMSrYl\nS4aVzH" +
       "3LhkUkRbVNDU9J62CWtaIfZQAyKRqWJowUkbCMdVU3pKQKISsbSxWSYe9PJj" +
       "bLNG6ZDIVY\nCgyGsgZR8IChKcQak49ffXP3wIZn9wuYMGg7tlLUBbNFndmi" +
       "bLaoM1vUNxsKhfgkM9msYrXA17sg\naiG/NdwxvGP9o/s7IwATc7ICHBUG0k" +
       "6wylFlQDb6vdCO8SwoA77av7dtX/Ta8fsEvqTSGbgod/1b\nZy4f+WfD4jAK" +
       "F0+PzERI0HVMzCDLqbm01xUMqGLy/35g40vvXv7gs15ogbMKIr6Qk0VsZ3Ax" +
       "LEMm\nCuRAT/yxWxsjD6Kth8KoAtIApD6uP2SVecE58iK3z82CzJbqOKpPGF" +
       "YKa2zITV11dMIyJr0/HCVN\nvD8DFqeeQbnV7bhvNjrLZG2rQBVb7YAVPMte" +
       "e6bqzvdeqX+du8VNyI2+LW+YUBHezR5YtliEwP8P\nvjX4jW9+uG8bR4qASo" +
       "jCPpge11Q5Cyy3eywQ1xrkFraQXSN6ylDUhIrHNcIQ99/G7mU/+dvXmsTS\n" +
       "aPDHXdme6wvw/t+6Bu25/Mi/5nExIZntK54ZHpmwZoYnebVl4SmmR/apd+Z+" +
       "+9f4BUh7kGpsdZrw\n7BESlgFTu78usdQU5LcMX8arezt//sbIi/sE9O8oU3" +
       "z4ucbkJ3//h12Rr18aF3zBHB8gPjTv2J9e\nujo0U7hJbIQLC/YiP4/YDDkA" +
       "Gk22IAvKzcCpX1uy4PQTQ1ccjVryU/oAlD1/nnqVLLrnq38sknuq\nxw1DI1" +
       "jnE0ocoYt5G2WQ5F5EfGwlazpBn54SnipS74zJu08lO9OP/eanfOZ67C+c/I" +
       "DdiE1hchNr\nFjKz24J57gFsTwDd5y9u2t6kXfwPSBwFiTLUGfZmC1JsNg/u" +
       "DnVl9fuXXm199O0ICq9DdZqBlXWY\nZwpUCyFK7AnIzlnzvlU8Cpsma1jLTU" +
       "bcCbMdB/CPOYXx2+LEb0vR+GXN7QGXusBk321QqHKNWW0T\nFbUN5xwosw4x" +
       "1qzhQ32s6RfK3XtdG7K+L7Y7lEH8OlbxeXlybLznRPzNzS/wRSyZ5osEQ0DO" +
       "9IWR\nw9d+S69wOV6+ZdwLsoXbJ1TJHu+KdzPNVedeTIVR9Shqkp06fivW0i" +
       "yrjULZabvFPdT6eeP5JaSo\nl/py+8mcYDT6pg1mei90oM+oWb8hkNwbmLdn" +
       "w1PjgKMmCI4Q4p1hztLF20W5VFxtWmoGs9oeVY2r\nNIVNFyvt/jJBTUH1Gl" +
       "3DCcRmkUPElushYlO+rnPgqXV0rS2h6w7WjFBUw4o35oWSAA4o80gZZbJe\n" +
       "gMzOD4v2gorIqYJYZphbqhbnyXDfQx817MWv7Qg78dJLIdQNc6lGMkTzzcXO" +
       "rHPzCqSN/PThQe7A\nyR+cp28vWSnS6uLS4RJkXLzyyHTHyrMHP0FZ1BGwLS" +
       "i6OXPblyIT6hthfkASCC44WOUz9eXjtg70\nSVv6ljz0zs9PbSvgaXMQ0XbD" +
       "qS3C/Rrh33eaYt2XUxSBAyHr8m+bs1tl8tskawwKAc0QvoYkVT0o\nsSJjqI" +
       "oHMfNGMyD/2JVv6ionANxAuDFTKwWEPLMCDed8uoyVX2bNkxQ1cCsHLWJii6" +
       "/HlGfXnpux\nawCeDseujk/DLlaM+kopXoAxEJ18bu2Mobu3PcPBXos1Fdub" +
       "PLSFVYX1QgDt7tIBlBM2Ji/acf4f\nly6QRXx7r1FtyMarrWSRE7iP5yN8im" +
       "x8L3GY1/EV49gWyA5eXRTeTORdOHCvfCbnRRYD6IvlvOgm\nq7bCvAyVq2GZ" +
       "pgnn1TrukmXL71pxF7iwBVzIbr2iqhKNGzLWYmuPXqp/51C6d71IM7f4CGJr" +
       "d59b\n31Bz9OBeHu6OL2t9x3vnuzqDrU1eVcdez1O0/f9w3F35uWU9y3uX9q" +
       "6AKGRqcqd9pQzQv8OaZymq\n544ZMYEpiPMDN4Pze+Hpdlao+1PB+XVWlQv9" +
       "fhmLj7PmCOw6nAkcqAXsPXoz9rLCosext+eG7fXr\nd7bM2I9Ycxq2ea77gK" +
       "4EVD9zw8UmrLgPO+z01l5w0Sku5+T4+49v/zj+u3+L8HUv0OoB5om0pvlL\n" +
       "Ll+/yrRIQuVK14sCzOSvl4sVDw6aobhyelzX84LjZ1DEBDkA3ezlJ7sAFvnI" +
       "QJbT8xNdgu0OiFj3\nl7m6zVciieIzm+cq5puFebmR3z67BUBa3D+PyQ+d2T" +
       "Y/e3DLczzRVsoanp5mYuog/MUFRK6IWFBS\nmivrLXTu7NZXfni3WyTxY9dM" +
       "59ahAIJ9YrTM0kPhUvzUP5AyKT+nT7/c9uN7jh++Eub3Dv8DEvu2\n9DQYAA" +
       "A=");
}
