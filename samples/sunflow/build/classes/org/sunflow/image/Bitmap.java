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
        super(
          );
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
                  read(
                    );
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
                  read(
                    );
                int g =
                  f.
                  read(
                    );
                int b =
                  f.
                  read(
                    );
                int e =
                  f.
                  read(
                    );
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
                          read(
                            );
                        int b1 =
                          f.
                          read(
                            );
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
                                        read(
                                          );
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
                  read(
                    ) &
                  255;
                f.
                  read(
                    );
                int datatype =
                  f.
                  read(
                    ) &
                  255;
                f.
                  read(
                    );
                f.
                  read(
                    );
                f.
                  read(
                    );
                f.
                  read(
                    );
                f.
                  read(
                    );
                f.
                  read(
                    );
                f.
                  read(
                    );
                f.
                  read(
                    );
                f.
                  read(
                    );
                width =
                  f.
                    read(
                      ) &
                    255;
                width |=
                  (f.
                     read(
                       ) &
                     255) <<
                    8;
                height =
                  f.
                    read(
                      ) &
                    255;
                height |=
                  (f.
                     read(
                       ) &
                     255) <<
                    8;
                pixels =
                  (new int[width *
                             height]);
                int bpp =
                  (f.
                     read(
                       ) &
                     255) /
                  8;
                int imgdscr =
                  f.
                  read(
                    ) &
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
                                read(
                                  ) &
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
                  close(
                    );
            }
            else {
                BufferedImage bi =
                  ImageIO.
                  read(
                    new File(
                      filename));
                width =
                  bi.
                    getWidth(
                      );
                height =
                  bi.
                    getHeight(
                      );
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
                                                                        getWidth(
                                                                          ),
                                                                      image.
                                                                        getHeight(
                                                                          ),
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
                                     read(
                                       );
                                   int g =
                                     f.
                                     read(
                                       );
                                   int b =
                                     f.
                                     read(
                                       );
                                   int e =
                                     f.
                                     read(
                                       );
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
        super(
          );
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
                toRGBE(
                  )
                : c.
                copy(
                  ).
                toNonLinear(
                  ).
                toRGB(
                  );
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
                                            saveHDR(
                                              filename);
                                        else
                                            if (filename.
                                                  endsWith(
                                                    ".png"))
                                                savePNG(
                                                  filename);
                                            else
                                                if (filename.
                                                      endsWith(
                                                        ".tga"))
                                                    saveTGA(
                                                      filename);
                                                else
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
                      ? getPixel(
                          x,
                          y).
                      toRGB(
                        )
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
              printStackTrace(
                );
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
                  getBytes(
                    ));
            f.
              write(
                "FORMAT=32-bit_rle_rgbe\n\n".
                  getBytes(
                    ));
            f.
              write(
                ("-Y " +
                 height +
                 " +X " +
                 width +
                 "\n").
                  getBytes(
                    ));
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
                      toRGBE(
                        );
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
              close(
                );
        }
        catch (FileNotFoundException e) {
            e.
              printStackTrace(
                );
        }
        catch (IOException e) {
            e.
              printStackTrace(
                );
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
                      ? getPixel(
                          x,
                          y).
                      toRGB(
                        )
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
              close(
                );
        }
        catch (IOException e) {
            e.
              printStackTrace(
                );
        }
    }
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "lmLTGKgYmgO+3P8/FqXOJlcZAAA=");
}
