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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
