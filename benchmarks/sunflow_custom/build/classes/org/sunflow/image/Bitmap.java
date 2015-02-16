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
      1170389032000L;
    public static final String jlc$ClassType$jl7 =
      ("H4sIAAAAAAAAALVZfWwUxxWfOxt/YTh/8OG4fBo7qiG9zYdISp3S2o4B04vt" +
       "2oYIo8ZZ782dF/Z2l905+yClIUgpCFUoakhCosRSK1JK6kBaBdGojUqltgQl" +
       "akUUNcofgaZq1agUqfzRNGrapu/N7NftfWCQamnnZmfnvfm9eW9+7+169hqZ" +
       "Z1tknWloe9OaweI0x+K7tPVxttekdnxrYv2QbNk02avJtj0KY+NK26uxjz99" +
       "crIhSqrGSLOs6waTmWro9jC1DW2KJhMk5o/2aTRjM9KQ2CVPyVKWqZqUUG3W" +
       "lSDzA6KMtCdcCBJAkACCxCFI3f4sEFpA9WymFyVkndl7yLdIJEGqTAXhMbI6" +
       "X4kpW3LGUTPELQANNXi/HYziwjmLrPJsFzYXGPz0OunYsw83/KSCxMZITNVH" +
       "EI4CIBgsMkbqMzQzQS27O5mkyTHSqFOaHKGWKmvqPo57jDTZalqXWdai3ibh" +
       "YNakFl/T37l6BW2zsgozLM+8lEq1pHs3L6XJabB1iW+rsHATjoOBdSoAs1Ky" +
       "Ql2Ryt2qnmRkZVjCs7H9azABRKszlE0a3lKVugwDpEn4TpP1tDTCLFVPw9R5" +
       "RhZWYaS1pFLca1NWdstpOs5IS3jekHgEs2r5RqAII4vD07gm8FJryEsB/1wb" +
       "uP/oo/oWPcoxJ6miIf4aEFoREhqmKWpRXaFCsH5t4hl5yRuHo4TA5MWhyWLO" +
       "uW9e/+odK86/KeZ8rsicwYldVGHjyomJhZeW9XZuqEAYNaZhq+j8PMt5+A85" +
       "T7pyJpy8JZ5GfBh3H54f/s2OAy/Tq1FS10+qFEPLZiCOGhUjY6oatTZTnVoy" +
       "o8l+Ukv1ZC9/3k+qoZ9QdSpGB1Mpm7J+UqnxoSqD38MWpUAFblE19FU9Zbh9" +
       "U2aTvJ8zCSHVcJF6uGJE/PFfRh6SttkQ7pKsyLqqGxIEL5UtZVKiijE+Abs7" +
       "mZGt3bakZG1mZCQ7q6c0Y1qyLUUyrLR3r2bA+1KPyjKyGccAM/9/qnNoVcN0" +
       "JAIbvix83DU4KVsMLUmtceVYtqfv+unxt6Je+Dv7AW6EFeLOCnG+QlysQCIR" +
       "rngRriS8CD7YDacZeK6+c+QbWx853FYB4WNOV8IGRmFqG9jjLN+nGL3+ke/n" +
       "xKZA3LV8f+eh+CcnvyLiTirNz0Wlyfnj049vf+zOKInmEy2aA0N1KD6E9OjR" +
       "YHv4gBXTGzv00cdnntlv+Ectj7kdBiiUxBPcFt54y1BoEjjRV792lXx2/I39" +
       "7VFSCbQAVMhkCF1gmRXhNfJOcpfLimjLPDA4ZVgZWcNHLpXVsUnLmPZHeEQs" +
       "5P1GcMp8DG3sLHZinf/i02YT20UigtDLISs46256/fxzZ59ftyEaJOhYIOWN" +
       "UCaOe6MfJKMWpTD+wfGhp56+dmgnjxCYsabYAu3Y9sLhB5fBtj7x5p73r1w+" +
       "8W7Ui6oIgyyYndBUJQc6bvdXAWrQgJ7Q9+3b9IyRVFOqPKFRDM5/xzruOvu3" +
       "ow3CmxqMuMFwx40V+OO39ZADbz38zxVcTUTB1ORb7k8TG9Dsa+62LHkv4sg9" +
       "/s7y5y7ILwJzAlvZ6j7KCSjqnBcEtRhKCC6JWSgushBo6yxTt1hqBqh0yuF6" +
       "aX/Tld0vfPSKOE/hxBCaTA8fO/JZ/OixaCB7rilIYEEZkUF5lCwQUfUZ/EXg" +
       "+i9eGE04IBi0qdeh8VUej5sm+m11OVh8iU1/ObP/Zz/cf0iY0ZSfPPqgNnrl" +
       "9/95O378DxeLMFj1hGFoVNY5SomjXMvbOAaQCCN3s4WbVCPeP9iXU6iJDuRy" +
       "X8ZmlenJES5HcnygteBARZaL8yR+Cw4UNu0hJBVcYwW/vxube4XyLzJSAaUN" +
       "dvuwWc9VPBCSdvHgfT823YVYhb4WfldZPoo2YekVYOV/DWoTB//4Cd/dAl4t" +
       "Elgh+TFp9oXW3o1XubxPcCi9MleYm6BM9WXvfjnzj2hb1a+jpHqMNChODbxd" +
       "1rJII2NQ99luYQx1ct7z/BpOFCxdHoEvC0d2YNkwtfoRBX2cjf26EJti0UBu" +
       "g6vBYdOGsPMjhHe+zkXaeNuBzeddMqs2LXVKxgIbeE3NUc0GP3WU9hPnEnF4" +
       "Z36w5rePzaz5EPZ4jNSoNljTbaWLlJABmb/PXrn6zoLlp3niqZyQbWFXuPYu" +
       "LK3zKma+DfUm/+krerISpmv6zuKmR7HbyWDTVV3W0HqN6mlRlvGo32HmPM0h" +
       "gmz2CbJXM3SKLO0+W+SeZ+/NBB7mCjBaZHleafIgt86PwSOnfnSOXVr3JUE/" +
       "a0v7Iyx44eBfW0c3Tj5yEwXJypC7wipPPTh7cfPtynejpMIL5YJXnHyhrvwA" +
       "rrMovJPpo3lhvEL4b0cxagqSi1bmmY7NLvCign4QboO9XVk8q/ZlTMbz4L6f" +
       "Ln3t/pMzl3laz/ED0yDYK5F/tpY41YpbtRQ7W5xURgDDtJoMxE9xhUvhanIU" +
       "NpVQmHUUVk1SNT3JbqCxFa5mR2NzCY05F6Jqb3lgmHO6UJjzN7/FzS8QmqVe" +
       "AXlmPHHw2Exy8KW7oo4PNjBSywzzCxqdolpAVR3vpzy03NxuCP5qJ1NVFy/9" +
       "yh7XKpt/E8iFYiJ0QJdx/8vTzH1/yKawik324x3OuZOv9e0ykXUEmwMM0pY8" +
       "RYulyMopQ02WSM+pfBcNgLGO0yMFTvfS80iZ9Aw2LXWJZRMUNP26mWVQnFE5" +
       "44VHH1f1VPFyA2838gnPYvMkI/UgnIRqlw1v7uHy37mhMbzWkEBbu2NM+83W" +
       "Gj5Yz6zCtz04sobFVb1YxkHfw+Z5RmrgVXsIE9fcbGh0Umak07Ghc842RP1I" +
       "DGz4yTIYT2FzAjCmAxhnbogx5jBFRHIwSnPGGFz9TJlnP8ZmViB7yOOtuSFr" +
       "AUT3OMjuuSVk58o8ex2b14BTANkWnwDnFpwdAOk+B9p9c4YWOCOCGH5RBt8v" +
       "sfm5QwxzizlOAhBnkR4HWs+cSaAA2oUy0C5i8yuo6RDa0MDmm0Q34KAbuHV0" +
       "vyuD7hI2bzvonDR0E+iog47eOrr3yqB7H5t3HXSjm7tLoMNCUXyNwjqjpeCL" +
       "tvgKq5yeidUsndn2nihz3S+ltQlSk8pqWrC0D/SrTIumVI6mVhT6Ij9fZqSx" +
       "gCUxmeMvR/iBmPghI/MDE9EY0QtO+hO83cEk7P7ZLPLKL15aciSvKDDDJcKa" +
       "vIqUf/93q8es+A/AuHJmZuvAo9fvfYmXolChyfv2oZYaKObFdyOvAl1dUpur" +
       "q2pL56cLX63tcIuOhdg0BSqGloAv9/wPK+Z/UW0ZAAA=");
}
