package org.sunflow.image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class Bitmap {
    private int[] pixels;
    private int width;
    private int height;
    private boolean isHDR;
    public Bitmap(String filename, boolean isLinear) throws IOException {
        super();
        if (filename.
              endsWith(
                ".hdr")) {
            isHDR =
              true;
            FileInputStream f =
              new FileInputStream(
              filename);
            boolean parseWidth =
              false;
            boolean parseHeight =
              false;
            width =
              (height =
                 0);
            int last =
              0;
            while (width ==
                     0 ||
                     height ==
                     0 ||
                     last !=
                     '\n') {
                int n =
                  f.
                  read();
                switch (n) {
                    case 'Y':
                        parseHeight =
                          last ==
                            '-';
                        parseWidth =
                          false;
                        break;
                    case 'X':
                        parseHeight =
                          false;
                        parseWidth =
                          last ==
                            '+';
                        break;
                    case ' ':
                        parseWidth &=
                          width ==
                            0;
                        parseHeight &=
                          height ==
                            0;
                        break;
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        if (parseHeight)
                            height =
                              10 *
                                height +
                                (n -
                                   '0');
                        else
                            if (parseWidth)
                                width =
                                  10 *
                                    width +
                                    (n -
                                       '0');
                        break;
                    default:
                        parseWidth =
                          (parseHeight =
                             false);
                        break;
                }
                last =
                  n;
            }
            pixels =
              (new int[width *
                         height]);
            if (width <
                  8 ||
                  width >
                  32767) {
                this.
                  readFlatRGBE(
                    f,
                    0,
                    width *
                      height);
                return;
            }
            int rasterPos =
              0;
            int numScanlines =
              height;
            int[] scanlineBuffer =
              new int[4 *
                        width];
            while (numScanlines >
                     0) {
                int r =
                  f.
                  read();
                int g =
                  f.
                  read();
                int b =
                  f.
                  read();
                int e =
                  f.
                  read();
                if (r !=
                      2 ||
                      g !=
                      2 ||
                      (b &
                         128) !=
                      0) {
                    pixels[rasterPos] =
                      r <<
                        24 |
                        g <<
                        16 |
                        b <<
                        8 |
                        e;
                    this.
                      readFlatRGBE(
                        f,
                        rasterPos +
                          1,
                        width *
                          numScanlines -
                          1);
                    return;
                }
                if ((b <<
                       8 |
                       e) !=
                      width) {
                    System.
                      out.
                      println(
                        "Invalid scanline width");
                    return;
                }
                int p =
                  0;
                for (int i =
                       0;
                     i <
                       4;
                     i++) {
                    if (p %
                          width !=
                          0)
                        System.
                          out.
                          println(
                            "Unaligned access to scanline data");
                    int end =
                      (i +
                         1) *
                      width;
                    while (p <
                             end) {
                        int b0 =
                          f.
                          read();
                        int b1 =
                          f.
                          read();
                        if (b0 >
                              128) {
                            int count =
                              b0 -
                              128;
                            if (count ==
                                  0 ||
                                  count >
                                  end -
                                  p) {
                                System.
                                  out.
                                  println(
                                    "Bad scanline data - invalid RLE run");
                                return;
                            }
                            while (count-- >
                                     0) {
                                scanlineBuffer[p] =
                                  b1;
                                p++;
                            }
                        }
                        else {
                            int count =
                              b0;
                            if (count ==
                                  0 ||
                                  count >
                                  end -
                                  p) {
                                System.
                                  out.
                                  println(
                                    "Bad scanline data - invalid count");
                                return;
                            }
                            scanlineBuffer[p] =
                              b1;
                            p++;
                            if (--count >
                                  0) {
                                for (int x =
                                       0;
                                     x <
                                       count;
                                     x++)
                                    scanlineBuffer[p +
                                                     x] =
                                      f.
                                        read();
                                p +=
                                  count;
                            }
                        }
                    }
                }
                for (int i =
                       0;
                     i <
                       width;
                     i++) {
                    r =
                      scanlineBuffer[i];
                    g =
                      scanlineBuffer[i +
                                       width];
                    b =
                      scanlineBuffer[i +
                                       2 *
                                       width];
                    e =
                      scanlineBuffer[i +
                                       3 *
                                       width];
                    pixels[rasterPos] =
                      r <<
                        24 |
                        g <<
                        16 |
                        b <<
                        8 |
                        e;
                    rasterPos++;
                }
                numScanlines--;
            }
            for (int y =
                   0,
                   i =
                     0,
                   ir =
                     (height -
                        1) *
                     width;
                 y <
                   height /
                   2;
                 y++,
                   ir -=
                     width) {
                for (int x =
                       0,
                       i2 =
                         ir;
                     x <
                       width;
                     x++,
                       i++,
                       i2++) {
                    int t =
                      pixels[i];
                    pixels[i] =
                      pixels[i2];
                    pixels[i2] =
                      t;
                }
            }
        }
        else
            if (filename.
                  endsWith(
                    ".tga")) {
                isHDR =
                  false;
                FileInputStream f =
                  new FileInputStream(
                  filename);
                int pix_ptr =
                  0;
                int pix =
                  0;
                int r;
                int j;
                byte[] read =
                  new byte[4];
                int idsize =
                  f.
                  read() &
                  255;
                f.
                  read();
                int datatype =
                  f.
                  read() &
                  255;
                f.
                  read();
                f.
                  read();
                f.
                  read();
                f.
                  read();
                f.
                  read();
                f.
                  read();
                f.
                  read();
                f.
                  read();
                f.
                  read();
                width =
                  f.
                    read() &
                    255;
                width |=
                  (f.
                     read() &
                     255) <<
                    8;
                height =
                  f.
                    read() &
                    255;
                height |=
                  (f.
                     read() &
                     255) <<
                    8;
                pixels =
                  (new int[width *
                             height]);
                int bpp =
                  (f.
                     read() &
                     255) /
                  8;
                int imgdscr =
                  f.
                  read() &
                  255;
                if (idsize !=
                      0)
                    f.
                      skip(
                        idsize);
                switch (datatype) {
                    case 10:
                        while (pix_ptr <
                                 width *
                                 height) {
                            r =
                              f.
                                read() &
                                255;
                            if ((r &
                                   128) ==
                                  128) {
                                r &=
                                  127;
                                f.
                                  read(
                                    read,
                                    0,
                                    bpp);
                                pix =
                                  (read[2] &
                                     255) <<
                                    16;
                                pix |=
                                  (read[1] &
                                     255) <<
                                    8;
                                pix |=
                                  read[0] &
                                    255;
                                pix =
                                  isLinear
                                    ? pix
                                    : RGBSpace.
                                        SRGB.
                                    rgbToLinear(
                                      pix);
                                for (j =
                                       0;
                                     j <=
                                       r;
                                     j++,
                                       pix_ptr++)
                                    pixels[pix_ptr] =
                                      pix;
                            }
                            else {
                                r &=
                                  127;
                                for (j =
                                       0;
                                     j <=
                                       r;
                                     j++,
                                       pix_ptr++) {
                                    f.
                                      read(
                                        read,
                                        0,
                                        bpp);
                                    pix =
                                      (read[2] &
                                         255) <<
                                        16;
                                    pix |=
                                      (read[1] &
                                         255) <<
                                        8;
                                    pix |=
                                      read[0] &
                                        255;
                                    pixels[pix_ptr] =
                                      isLinear
                                        ? pix
                                        : RGBSpace.
                                            SRGB.
                                        rgbToLinear(
                                          pix);
                                }
                            }
                        }
                        break;
                    case 2:
                        for (pix_ptr =
                               0;
                             pix_ptr <
                               width *
                               height;
                             pix_ptr++) {
                            f.
                              read(
                                read,
                                0,
                                bpp);
                            pix =
                              (read[2] &
                                 255) <<
                                16;
                            pix |=
                              (read[1] &
                                 255) <<
                                8;
                            pix |=
                              read[0] &
                                255;
                            pixels[pix_ptr] =
                              isLinear
                                ? pix
                                : RGBSpace.
                                    SRGB.
                                rgbToLinear(
                                  pix);
                        }
                        break;
                    default:
                        UI.
                          printWarning(
                            Module.
                              IMG,
                            "Unsupported TGA datatype: %s",
                            datatype);
                        break;
                }
                if ((imgdscr &
                       32) ==
                      32) {
                    pix_ptr =
                      0;
                    for (int y =
                           0;
                         y <
                           height /
                           2;
                         y++)
                        for (int x =
                               0;
                             x <
                               width;
                             x++) {
                            int t =
                              pixels[pix_ptr];
                            pixels[pix_ptr] =
                              pixels[(height -
                                        y -
                                        1) *
                                       width +
                                       x];
                            pixels[(height -
                                      y -
                                      1) *
                                     width +
                                     x] =
                              t;
                            pix_ptr++;
                        }
                }
                f.
                  close();
            }
            else {
                BufferedImage bi =
                  ImageIO.
                  read(
                    new File(
                      filename));
                width =
                  bi.
                    getWidth();
                height =
                  bi.
                    getHeight();
                isHDR =
                  false;
                pixels =
                  (new int[width *
                             height]);
                for (int y =
                       0,
                       index =
                         0;
                     y <
                       height;
                     y++) {
                    for (int x =
                           0;
                         x <
                           width;
                         x++,
                           index++) {
                        int rgb =
                          bi.
                          getRGB(
                            x,
                            height -
                              1 -
                              y);
                        pixels[index] =
                          isLinear
                            ? rgb
                            : RGBSpace.
                                SRGB.
                            rgbToLinear(
                              rgb);
                    }
                }
            }
    }
    public static void save(BufferedImage image, String filename) { Bitmap b =
                                                                      new Bitmap(
                                                                      image.
                                                                        getWidth(),
                                                                      image.
                                                                        getHeight(),
                                                                      false);
                                                                    for (int y =
                                                                           0;
                                                                         y <
                                                                           b.
                                                                             height;
                                                                         y++)
                                                                        for (int x =
                                                                               0;
                                                                             x <
                                                                               b.
                                                                                 width;
                                                                             x++)
                                                                            b.
                                                                              pixels[(b.
                                                                                        height -
                                                                                        1 -
                                                                                        y) *
                                                                                       b.
                                                                                         width +
                                                                                       x] =
                                                                              image.
                                                                                getRGB(
                                                                                  x,
                                                                                  y);
                                                                    if (filename.
                                                                          endsWith(
                                                                            ".tga"))
                                                                        b.
                                                                          saveTGA(
                                                                            filename);
                                                                    else
                                                                        b.
                                                                          savePNG(
                                                                            filename);
    }
    private void readFlatRGBE(FileInputStream f,
                              int rasterPos,
                              int numPixels)
          throws IOException { while (numPixels-- >
                                        0) {
                                   int r =
                                     f.
                                     read();
                                   int g =
                                     f.
                                     read();
                                   int b =
                                     f.
                                     read();
                                   int e =
                                     f.
                                     read();
                                   pixels[rasterPos] =
                                     r <<
                                       24 |
                                       g <<
                                       16 |
                                       b <<
                                       8 |
                                       e;
                                   rasterPos++;
                               } }
    public Bitmap(int w, int h, boolean isHDR) {
        super();
        width =
          w;
        height =
          h;
        this.
          isHDR =
          isHDR;
        pixels =
          (new int[w *
                     h]);
    }
    public void setPixel(int x, int y, Color c) {
        if (x >=
              0 &&
              x <
              width &&
              y >=
              0 &&
              y <
              height)
            pixels[y *
                     width +
                     x] =
              isHDR
                ? c.
                toRGBE()
                : c.
                copy().
                toNonLinear().
                toRGB();
    }
    public Color getPixel(int x, int y) {
        if (x >=
              0 &&
              x <
              width &&
              y >=
              0 &&
              y <
              height)
            return isHDR
              ? new Color(
              ).
              setRGBE(
                pixels[y *
                         width +
                         x])
              : new Color(
              pixels[y *
                       width +
                       x]);
        return Color.
                 BLACK;
    }
    public int getWidth() { return width;
    }
    public int getHeight() { return height;
    }
    public void save(String filename) { if (filename.
                                              endsWith(
                                                ".hdr"))
                                            this.
                                              saveHDR(
                                                filename);
                                        else
                                            if (filename.
                                                  endsWith(
                                                    ".png"))
                                                this.
                                                  savePNG(
                                                    filename);
                                            else
                                                if (filename.
                                                      endsWith(
                                                        ".tga"))
                                                    this.
                                                      saveTGA(
                                                        filename);
                                                else
                                                    this.
                                                      saveHDR(
                                                        filename +
                                                        ".hdr");
    }
    private void savePNG(String filename) {
        BufferedImage bi =
          new BufferedImage(
          width,
          height,
          BufferedImage.
            TYPE_INT_RGB);
        for (int y =
               0;
             y <
               height;
             y++)
            for (int x =
                   0;
                 x <
                   width;
                 x++)
                bi.
                  setRGB(
                    x,
                    height -
                      1 -
                      y,
                    isHDR
                      ? this.
                      getPixel(
                        x,
                        y).
                      toRGB()
                      : pixels[y *
                                 width +
                                 x]);
        try {
            ImageIO.
              write(
                bi,
                "png",
                new File(
                  filename));
        }
        catch (IOException e) {
            e.
              printStackTrace();
        }
    }
    private void saveHDR(String filename) {
        try {
            FileOutputStream f =
              new FileOutputStream(
              filename);
            f.
              write(
                "#?RGBE\n".
                  getBytes());
            f.
              write(
                "FORMAT=32-bit_rle_rgbe\n\n".
                  getBytes());
            f.
              write(
                ("-Y " +
                 height +
                 " +X " +
                 width +
                 "\n").
                  getBytes());
            for (int y =
                   height -
                   1;
                 y >=
                   0;
                 y--) {
                for (int x =
                       0;
                     x <
                       width;
                     x++) {
                    int rgbe =
                      isHDR
                      ? pixels[y *
                                 width +
                                 x]
                      : new Color(
                      pixels[y *
                               width +
                               x]).
                      toRGBE();
                    f.
                      write(
                        rgbe >>
                          24);
                    f.
                      write(
                        rgbe >>
                          16);
                    f.
                      write(
                        rgbe >>
                          8);
                    f.
                      write(
                        rgbe);
                }
            }
            f.
              close();
        }
        catch (FileNotFoundException e) {
            e.
              printStackTrace();
        }
        catch (IOException e) {
            e.
              printStackTrace();
        }
    }
    private void saveTGA(String filename) {
        try {
            FileOutputStream f =
              new FileOutputStream(
              filename);
            byte[] tgaHeader =
              { 0,
            0,
            2,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0 };
            f.
              write(
                tgaHeader);
            f.
              write(
                width &
                  255);
            f.
              write(
                width >>
                  8 &
                  255);
            f.
              write(
                height &
                  255);
            f.
              write(
                height >>
                  8 &
                  255);
            f.
              write(
                32);
            f.
              write(
                0);
            for (int y =
                   0;
                 y <
                   height;
                 y++) {
                for (int x =
                       0;
                     x <
                       width;
                     x++) {
                    int pix =
                      isHDR
                      ? this.
                      getPixel(
                        x,
                        y).
                      toRGB()
                      : pixels[y *
                                 width +
                                 x];
                    f.
                      write(
                        pix &
                          255);
                    f.
                      write(
                        pix >>
                          8 &
                          255);
                    f.
                      write(
                        pix >>
                          16 &
                          255);
                    f.
                      write(
                        255);
                }
            }
            f.
              close();
        }
        catch (IOException e) {
            e.
              printStackTrace();
        }
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1170389032000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAK1ZC2wUxxmeu/PbTvwC8whgMCaU1x2pCYSHRGz84MxhO7Z5" +
       "mVBnvTd3XtjbXXbn\nzodBaaKoQEBtgtqorVQIrZB4hCSoaUVaUQqCpDQoFU" +
       "FqqJBCW1GlVdu0SStRqkRV/5nZvd3be+Bg\nLHl2dnfm///vn/+5d/oTVGjo" +
       "aJpo+MkuDRv+NX09gm7g8BpZMIx+eDQovlNY2nN8naJ6kSeEvFKY\noMqQaA" +
       "TCAhECUjgQbF2Z1NECTZV3RWWV+HGS+LfLj5v0OkOPZxDcdORszfPHCuq9qD" +
       "CEKgVFUYlA\nJFVpk3HMIKgqtF1ICIE4keRASDLIyhB6CCvx2BpVMYigEGMn" +
       "ehb5QqhIEylNgmaFLOYBYB7QBF2I\nBRj7QA9jCxRqdUwEScHh5hQ72LkwfS" +
       "eIbe7rzVwNREroy40Ah0kAqGemUHO0GVA134mNS/ccPelD\nlQOoUlL6KDER" +
       "kBDgN4AqYjg2hHWjORzG4QFUrWAc7sO6JMjSKOM6gGoMKaoIJK5joxcbqpyg" +
       "C2uM\nuIZ1xtN6GEIVIsWkx0Wi6ikdRSQsh627wogsRAF2nQ2bw22nzwFgmQ" +
       "SC6RFBxNaWgh2SAide796R\nwti4DhbA1uIYJsNqilWBIsADVMPPUhaUaKCP" +
       "6JIShaWFahy4EDQ1J1Gqa00QdwhRPEjQZPe6Hv4K\nVpUyRdAtBE10L2OU4J" +
       "Smuk7JcT4L6u7sP/GD808y2y4IY1Gm8pfBphmuTb04gnWsiJhvvBv3fye4\n" +
       "JT7NixAsnuhazNc0zzm7IfSXX9bzNY9kWdM9tB2LZFDsOlTfu7tDRT4qRomm" +
       "GhI9/DTkzB16zDcr\nkxp4bV2KIn3pt15e6H13y3On8N+8qCyIikRVjsfAjq" +
       "pFNaZJMtY7sIJ1geBwEJViJbyGvQ+iYpiH\nwOT50+5IxMAkiApk9qhIZfeg" +
       "ogiQoCoqhbmkRFRrrglkmM2TGkKoGP5RBfxXIv7HrgQt8AeMuBKR\n1ZGAoY" +
       "sBVY+m7qUYnGigRSIxQfNTo9EI6ggMqzEcEERBkRQ1EJXATUV1URgn6HXspJ" +
       "JUspoRj4eG\nOrfLymDta1U5jPVB8fjt9/a0rXtxPzcHasImJjgK4OA3OfgZ" +
       "Bz/ngDweRngC5cRPAvS4AzwSYlfF\nvL5tnc/sb/CBCWgjBaAELyxtAOlN9m" +
       "2iusZ22yCLcCLYzuQfbd3nv3t8NbedQO7omnV3+bXXrx79\nd8V8L/JmD30U" +
       "FgTfMkqmh8bLVEhrdDtLNvr/OLD+rQ+vfvQV220Iaszw5syd1Bsb3AegqyIO" +
       "Q3yz\nyR+bUunbhDYe8qICcHEIa0x+iBgz3DzSvHKlFeEoluIQKo+oekyQ6S" +
       "srLJWRYV0dsZ8wy6hi81o4\nnHJqptXwP9G0W3albydqdKzjlkRP24WCRdC7" +
       "LxQtvnGu/B2mFivYVjrSWR8m3HWrbWPp1zGG5x99\nr+fbr3yybyuzFG4qHg" +
       "I5Lj4kS2IStjxqbwGflSFu0INs3KDE1LAUkYQhGVOL+6JyzmM//fu3qvjR\n" +
       "yPDEOtmF9yZgP5/Sgp67+rX/zGBkPCLNGTYMexlHU2tTbtZ1YReVI/n89enf" +
       "/5VwGEIahBFDGsUs\nMnhNJ6BCTYJcz3bS9ODn6QGoTXYWI7oUg6CWYOd7e2" +
       "/DL65seHUf94l5eSoO565B8eu//8MO30sX\nh/g+d2B3LT4049jHb93uncD1" +
       "x7Pf7IwE5NzDMyCzjEqNntSsfBzY6ssLZp1+tveWKVFNehxvg1rn\nz7su4b" +
       "mrvvnHLIGoeEhVZSwojGGAKXI+G/3UZLjhWOrlByOp/mB3W1LEGj0ytm8VHR" +
       "pA1oU5tJil\nABoU95yKNsR3/vpnTKpywVlJOa18vaBxdVTRYTZVySR3cFwr" +
       "GMOwbsmFrqer5AufA8UBoChC4WF0\n6xCLk2k+Yq4uLL558VLdMx/4kLcdlc" +
       "mqEG4XWHhBpeDX2BiGMJ7UVj/JXLdqpISOdJpETCFTmXJQ\nkt1My3B6z3Tu" +
       "8/ya4fR0eNSlbh+j6GP3X9U44WUE+aCMotNOOixl24OunciUhd530aGdvVpN" +
       "hw5O\nqOWeQJKOuwIjr0u00zrQjrCDQwtPhN7rPsxOMmeCyOItLjqj5zccuf" +
       "s+ucXo2JGa7p6VzEy2UDvb\ne5/4MFFddObVmBcVD6Aq0azuNwpynMbDAShG" +
       "Davkhw4g7X16YcmrqJWpTDTN7a4Otu4cYfsWzOlq\nOq9wpQVayaAp1iFYV6" +
       "eFeBCbbGZbGtk4NxXEizVdSgi04od4LiWxbNAE4ohyLGjS4HDy5dba3uVb\n" +
       "X2B5uxS6AMHosuWD3ovOPKDYOblPOkVsUJy77eynF8/jucy7SiQD9NCsR7NU" +
       "xI49nwmn8PobkSMs\n9xYMCQbXiLuVyOwU0hoApsCHNXbp1DQNaj8OZ3lT02" +
       "JAXwPoaQPpl8KmSbVebx86FVFOhb2MPGPW\nTHeY4EvZE4c2fKpm0ArZ0Yqa" +
       "lBq7NYNWGg85mARb95zprCj54cG9jL6pylJHtW3eFycEvcsZb8uZ\n3I8tW7" +
       "x4+RKCnnpAJemKpmVLFi5tWrR0OXDoXxvs83PLoGyHUnbyLBT5mZqi+ExDRD" +
       "Usgzxsp1Ga\nPZwvAUFBb1tzKy9cUjFmy71iTF+69deZhZFVIGWz/p10GCCo" +
       "cEQK826g08VV/5JcJ3EQFpisXBMm\n16JhLEWHSTa2I1+S7VT4rzXZ1uZgu9" +
       "sCKxlrW3tZoHdx3ZOHa9LOJqmspKPpuRpVVjTs2/xZxV7h\n8javmTaeAJ8i" +
       "qrZIxgnTcDipMkoprcNYzzzTjrwHTr52lnywYAUvP+bnjiXujfNXHB2tX/Hm" +
       "wfvo\nK+pd2NykqxOPPOUblq4w9zQDecZXh/RNK9PDdxnIE9eV/rQgPjN1ss" +
       "x+miEgF5tpvjhrms+M4F46\nnwcGZrCPN0lXJndVtNOYKwojxGoS4xHaooSD" +
       "9C5n2ct4v5SnRniFDi+CLxtCArvrjYKEKoVtwzsw\n1vqB3Xwj3fa7QDOmy3" +
       "kyXC5VCA3kKYQA3ySr7myXZOjrtDgBmFiIpZyzk5E6ep/V6zE6HCaoAmiG\n" +
       "ofMivR0tbfTZd20lHLlPJbBqMABSNJpKaMyphBzVoA0ypY7MbwfQQqk6I/VG" +
       "nlP/MR1eI6jEwKSH\nZgQXyNPjATkXwM0zQc4bM0iv7ROOk/x5HhDn6HAWQE" +
       "RNEPdSi43v7fHgg4LNEzDxBcaMzyn5u3ne\nXaHDJY5qUyrf2aJfHo/okIA8" +
       "TaboTfcl+rU8767T4X3IHiD6Wjtp2rL/ZjyyzwGZl5myLxuz7C7X\nzx4gb+" +
       "YBdYsON8wA6XKT340nIIJreFpMPC058bgD4pjwfJwHz1/pcBuaB4qnp6vDBe" +
       "lP44XUZULq\nerCQ/pUH0h06/NOEZBZNDkifjhcSNiHhBwvpf7kheVgc/NyE" +
       "1N/R7IL0xZg7eSgveHdAv6dNzvhZ\nif8UIoZu7n76Tui3/+XNmfVzRTl0MZ" +
       "G4LDtbWce8SNNxRGJQynljy/oyTylB1RkRmBa0VqXiKeEL\nK6BFcSykaPnM" +
       "uaiSQEemR+m0SsuiUN6kJ9NQU6yz06pP9tudVSHG+a93g+Lm17fOTB7sf5mV" +
       "nYWi\nLIyOUjJl0K3xT7ypKnNWTmoWrWvozJsbz72x3Kqi2TeqCY7aL83KVv" +
       "O3eU4RKtvs31XbYhphX0JH\n3570k1XHj9zysi+7/wcs4SPsch0AAA==");
}
