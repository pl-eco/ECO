package org.sunflow.core.display;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.zip.Deflater;
import org.sunflow.core.Display;
import org.sunflow.image.Color;
import org.sunflow.system.ByteUtil;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class OpenExrDisplay implements Display {
    final private static byte HALF = 1;
    final private static byte FLOAT = 2;
    final private static int HALF_SIZE = 2;
    final private static int FLOAT_SIZE = 4;
    final private static int OE_MAGIC = 20000630;
    final private static int OE_EXR_VERSION = 2;
    final private static int OE_TILED_FLAG = 512;
    final private static int NO_COMPRESSION = 0;
    final private static int RLE_COMPRESSION = 1;
    final private static int ZIP_COMPRESSION = 3;
    final private static int RLE_MIN_RUN = 3;
    final private static int RLE_MAX_RUN = 127;
    private String filename;
    private RandomAccessFile file;
    private long[][] tileOffsets;
    private long tileOffsetsPosition;
    private int tilesX;
    private int tilesY;
    private int tileSize;
    private int compression;
    private byte channelType;
    private int channelSize;
    private byte[] tmpbuf;
    private byte[] comprbuf;
    public OpenExrDisplay(String filename, String compression, String channelType) {
        super();
        this.
          filename =
          filename ==
            null
            ? "output.exr"
            : filename;
        if (compression ==
              null ||
              compression.
              equals(
                "none"))
            this.
              compression =
              NO_COMPRESSION;
        else
            if (compression.
                  equals(
                    "rle"))
                this.
                  compression =
                  RLE_COMPRESSION;
            else
                if (compression.
                      equals(
                        "zip"))
                    this.
                      compression =
                      ZIP_COMPRESSION;
                else {
                    UI.
                      printWarning(
                        Module.
                          DISP,
                        ("EXR - Compression type was not recognized - defaulting to zi" +
                         "p"));
                    this.
                      compression =
                      ZIP_COMPRESSION;
                }
        if (channelType !=
              null &&
              channelType.
              equals(
                "float")) {
            this.
              channelType =
              FLOAT;
            this.
              channelSize =
              FLOAT_SIZE;
        }
        else
            if (channelType !=
                  null &&
                  channelType.
                  equals(
                    "half")) {
                this.
                  channelType =
                  HALF;
                this.
                  channelSize =
                  HALF_SIZE;
            }
            else {
                UI.
                  printWarning(
                    Module.
                      DISP,
                    "EXR - Channel type was not recognized - defaulting to float");
                this.
                  channelType =
                  FLOAT;
                this.
                  channelSize =
                  FLOAT_SIZE;
            }
    }
    public void setGamma(float gamma) { UI.printWarning(Module.
                                                          DISP,
                                                        "EXR - Gamma correction unsupported - ignoring");
    }
    public void imageBegin(int w, int h, int bucketSize) {
        try {
            file =
              new RandomAccessFile(
                filename,
                "rw");
            file.
              setLength(
                0);
            if (bucketSize <=
                  0)
                throw new Exception(
                  "Can\'t use OpenEXR display without buckets.");
            this.
              writeRGBHeader(
                w,
                h,
                bucketSize);
        }
        catch (Exception e) {
            UI.
              printError(
                Module.
                  DISP,
                "EXR - %s",
                e.
                  getMessage());
            e.
              printStackTrace();
        }
    }
    public void imagePrepare(int x, int y, int w,
                             int h,
                             int id) {  }
    public synchronized void imageUpdate(int x, int y,
                                         int w,
                                         int h,
                                         Color[] data) {
        try {
            int tx =
              x /
              tileSize;
            int ty =
              y /
              tileSize;
            this.
              writeTile(
                tx,
                ty,
                w,
                h,
                data);
        }
        catch (IOException e) {
            UI.
              printError(
                Module.
                  DISP,
                "EXR - %s",
                e.
                  getMessage());
            e.
              printStackTrace();
        }
    }
    public void imageFill(int x, int y, int w, int h,
                          Color c) {  }
    public void imageEnd() { try { this.writeTileOffsets();
                                   file.close(); }
                             catch (IOException e) {
                                 UI.
                                   printError(
                                     Module.
                                       DISP,
                                     "EXR - %s",
                                     e.
                                       getMessage());
                                 e.
                                   printStackTrace();
                             } }
    public void writeRGBHeader(int w, int h, int tileSize)
          throws Exception { byte[] chanOut = { 0,
                             channelType,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             1,
                             0,
                             0,
                             0,
                             1,
                             0,
                             0,
                             0 };
                             file.write(ByteUtil.
                                          get4Bytes(
                                            OE_MAGIC));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            OE_EXR_VERSION |
                                              OE_TILED_FLAG));
                             file.write("channels".
                                          getBytes());
                             file.write(0);
                             file.write("chlist".
                                          getBytes());
                             file.write(0);
                             file.write(ByteUtil.
                                          get4Bytes(
                                            55));
                             file.write("R".getBytes());
                             file.write(chanOut);
                             file.write("G".getBytes());
                             file.write(chanOut);
                             file.write("B".getBytes());
                             file.write(chanOut);
                             file.write(0);
                             file.write("compression".
                                          getBytes());
                             file.write(0);
                             file.write("compression".
                                          getBytes());
                             file.write(0);
                             file.write(1);
                             file.write(ByteUtil.
                                          get4BytesInv(
                                            compression));
                             file.write("dataWindow".
                                          getBytes());
                             file.write(0);
                             file.write("box2i".getBytes());
                             file.write(0);
                             file.write(ByteUtil.
                                          get4Bytes(
                                            16));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            0));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            0));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            w -
                                              1));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            h -
                                              1));
                             file.write("displayWindow".
                                          getBytes());
                             file.write(0);
                             file.write("box2i".getBytes());
                             file.write(0);
                             file.write(ByteUtil.
                                          get4Bytes(
                                            16));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            0));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            0));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            w -
                                              1));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            h -
                                              1));
                             file.write("lineOrder".
                                          getBytes());
                             file.write(0);
                             file.write("lineOrder".
                                          getBytes());
                             file.write(0);
                             file.write(1);
                             file.write(ByteUtil.
                                          get4BytesInv(
                                            2));
                             file.write("pixelAspectRatio".
                                          getBytes());
                             file.write(0);
                             file.write("float".getBytes());
                             file.write(0);
                             file.write(ByteUtil.
                                          get4Bytes(
                                            4));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            Float.
                                              floatToIntBits(
                                                1)));
                             file.write("screenWindowCenter".
                                          getBytes());
                             file.write(0);
                             file.write("v2f".getBytes());
                             file.write(0);
                             file.write(ByteUtil.
                                          get4Bytes(
                                            8));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            Float.
                                              floatToIntBits(
                                                0)));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            Float.
                                              floatToIntBits(
                                                0)));
                             file.write("screenWindowWidth".
                                          getBytes());
                             file.write(0);
                             file.write("float".getBytes());
                             file.write(0);
                             file.write(ByteUtil.
                                          get4Bytes(
                                            4));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            (int)
                                              Float.
                                              floatToIntBits(
                                                1)));
                             this.tileSize = tileSize;
                             tilesX = (int) ((w +
                                                tileSize -
                                                1) /
                                               tileSize);
                             tilesY = (int) ((h +
                                                tileSize -
                                                1) /
                                               tileSize);
                             tmpbuf = (new byte[tileSize *
                                                  tileSize *
                                                  channelSize *
                                                  3]);
                             comprbuf = (new byte[tileSize *
                                                    tileSize *
                                                    channelSize *
                                                    3 *
                                                    2]);
                             tileOffsets = (new long[tilesX][tilesY]);
                             file.write("tiles".getBytes());
                             file.write(0);
                             file.write("tiledesc".
                                          getBytes());
                             file.write(0);
                             file.write(ByteUtil.
                                          get4Bytes(
                                            9));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            tileSize));
                             file.write(ByteUtil.
                                          get4Bytes(
                                            tileSize));
                             file.write(0);
                             file.write(0);
                             tileOffsetsPosition =
                               file.
                                 getFilePointer();
                             this.writeTileOffsets();
    }
    public void writeTileOffsets() throws IOException {
        file.
          seek(
            tileOffsetsPosition);
        for (int ty =
               0;
             ty <
               tilesY;
             ty++)
            for (int tx =
                   0;
                 tx <
                   tilesX;
                 tx++)
                file.
                  write(
                    ByteUtil.
                      get8Bytes(
                        tileOffsets[tx][ty]));
    }
    private void writeTile(int tileX, int tileY, int w,
                           int h,
                           Color[] tile) throws IOException {
        byte[] rgb =
          new byte[4];
        int pixptr =
          0;
        int writeSize =
          0;
        int comprSize =
          Integer.
            MAX_VALUE;
        int tileRangeX =
          tileSize <
          w
          ? tileSize
          : w;
        int tileRangeY =
          tileSize <
          h
          ? tileSize
          : h;
        int channelBase =
          tileRangeX *
          channelSize;
        if (tileSize !=
              tileRangeX &&
              tileX ==
              0)
            System.
              out.
              print(
                " bad X alignment ");
        if (tileSize !=
              tileRangeY &&
              tileY ==
              0)
            System.
              out.
              print(
                " bad Y alignment ");
        tileOffsets[tileX][tileY] =
          file.
            getFilePointer();
        file.
          write(
            ByteUtil.
              get4Bytes(
                tileX));
        file.
          write(
            ByteUtil.
              get4Bytes(
                tileY));
        file.
          write(
            ByteUtil.
              get4Bytes(
                0));
        file.
          write(
            ByteUtil.
              get4Bytes(
                0));
        Arrays.
          fill(
            tmpbuf,
            (byte)
              0);
        for (int ty =
               0;
             ty <
               tileRangeY;
             ty++) {
            for (int tx =
                   0;
                 tx <
                   tileRangeX;
                 tx++) {
                float[] rgbf =
                  tile[tx +
                         ty *
                         tileRangeX].
                  getRGB();
                for (int component =
                       0;
                     component <
                       3;
                     component++) {
                    if (channelType ==
                          FLOAT) {
                        rgb =
                          ByteUtil.
                            get4Bytes(
                              Float.
                                floatToRawIntBits(
                                  rgbf[2 -
                                         component]));
                        tmpbuf[channelBase *
                                 component +
                                 pixptr +
                                 0] =
                          rgb[0];
                        tmpbuf[channelBase *
                                 component +
                                 pixptr +
                                 1] =
                          rgb[1];
                        tmpbuf[channelBase *
                                 component +
                                 pixptr +
                                 2] =
                          rgb[2];
                        tmpbuf[channelBase *
                                 component +
                                 pixptr +
                                 3] =
                          rgb[3];
                    }
                    else
                        if (channelType ==
                              HALF) {
                            rgb =
                              ByteUtil.
                                get2Bytes(
                                  ByteUtil.
                                    floatToHalf(
                                      rgbf[2 -
                                             component]));
                            tmpbuf[channelBase *
                                     component +
                                     pixptr +
                                     0] =
                              rgb[0];
                            tmpbuf[channelBase *
                                     component +
                                     pixptr +
                                     1] =
                              rgb[1];
                        }
                }
                pixptr +=
                  channelSize;
            }
            pixptr +=
              tileRangeX *
                channelSize *
                2;
        }
        writeSize =
          tileRangeX *
            tileRangeY *
            channelSize *
            3;
        if (compression !=
              NO_COMPRESSION)
            comprSize =
              OpenExrDisplay.
                compress(
                  compression,
                  tmpbuf,
                  writeSize,
                  comprbuf);
        if (comprSize <
              writeSize) {
            file.
              write(
                ByteUtil.
                  get4Bytes(
                    comprSize));
            file.
              write(
                comprbuf,
                0,
                comprSize);
        }
        else {
            file.
              write(
                ByteUtil.
                  get4Bytes(
                    writeSize));
            file.
              write(
                tmpbuf,
                0,
                writeSize);
        }
    }
    final private static int compress(int tp, byte[] in,
                                      int inSize,
                                      byte[] out) {
        if (inSize ==
              0)
            return 0;
        int t1 =
          0;
        int t2 =
          (inSize +
             1) /
          2;
        int inPtr =
          0;
        int ret;
        byte[] tmp =
          new byte[inSize];
        if (tp ==
              ZIP_COMPRESSION ||
              tp ==
              RLE_COMPRESSION) {
            while (true) {
                if (inPtr <
                      inSize)
                    tmp[t1++] =
                      in[inPtr++];
                else
                    break;
                if (inPtr <
                      inSize)
                    tmp[t2++] =
                      in[inPtr++];
                else
                    break;
            }
            t1 =
              1;
            int p =
              tmp[t1 -
                    1];
            while (t1 <
                     inSize) {
                int d =
                  (int)
                    tmp[t1] -
                  p +
                  (128 +
                     256);
                p =
                  (int)
                    tmp[t1];
                tmp[t1] =
                  (byte)
                    d;
                t1++;
            }
        }
        switch (tp) {
            case ZIP_COMPRESSION:
                Deflater def =
                  new Deflater(
                  Deflater.
                    DEFAULT_COMPRESSION,
                  false);
                def.
                  setInput(
                    tmp,
                    0,
                    inSize);
                def.
                  finish();
                ret =
                  def.
                    deflate(
                      out);
                return ret;
            case RLE_COMPRESSION:
                return OpenExrDisplay.
                  rleCompress(
                    tmp,
                    inSize,
                    out);
            default:
                return -1;
        }
    }
    final private static int rleCompress(byte[] in,
                                         int inLen,
                                         byte[] out) {
        int runStart =
          0;
        int runEnd =
          1;
        int outWrite =
          0;
        while (runStart <
                 inLen) {
            while (runEnd <
                     inLen &&
                     in[runStart] ==
                     in[runEnd] &&
                     runEnd -
                     runStart -
                     1 <
                     RLE_MAX_RUN)
                runEnd++;
            if (runEnd -
                  runStart >=
                  RLE_MIN_RUN) {
                out[outWrite++] =
                  (byte)
                    (runEnd -
                       runStart -
                       1);
                out[outWrite++] =
                  in[runStart];
                runStart =
                  runEnd;
            }
            else {
                while (runEnd <
                         inLen &&
                         (runEnd +
                            1 >=
                            inLen ||
                            in[runEnd] !=
                            in[runEnd +
                                 1] ||
                            (runEnd +
                               2 >=
                               inLen ||
                               in[runEnd +
                                    1] !=
                               in[runEnd +
                                    2])) &&
                         runEnd -
                         runStart <
                         RLE_MAX_RUN)
                    runEnd++;
                out[outWrite++] =
                  (byte)
                    (runStart -
                       runEnd);
                while (runStart <
                         runEnd)
                    out[outWrite++] =
                      in[runStart++];
            }
            runEnd++;
        }
        return outWrite;
    }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1163966486000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALVaCWwU1xl+u2vjMzHYBhzOYG5s7/rAxuAowcFrWFgfXR8h" +
       "BrqMZ2ftCbMzk5lZ\nsziIJIoENLRJUI62akNIhQqh5JDSFiolBJo7UasE5V" +
       "KikLRR06pp2qC2CWmrtv//ZnZ3dvbwrolX\nmjdvZ957//cf73//++ed+pzk" +
       "qwqZx6pObY/Mqc4Nfb2MonKBDQKjqv3wyM++mF/Ue3yLKNmJzUvs\nfEAjZV" +
       "5WdQUYjXHxAZenoy2ikBpZEvaMCJLm5CKa8xah2Rhvs7c5acCbjpwuv/NY3k" +
       "I7yfeSMkYU\nJY3ReEl0C1xI1ch07y3MGOMKa7zg8vKq1uYlV3FiOLRBElWN" +
       "ETX1VrKPOLxkmszimBpZ5I0SdwFx\nl8woTMhFybt6KVkYoULhNIYXuUB7jB" +
       "z0rE3sCbCNfr7k1jBIIb4cBHYoAuD62hjXOrdJrMqOE4Mt\ne48+5iBlQ6SM" +
       "F/twMBY40YDeECkNcaFhTlHbAwEuMERmiBwX6OMUnhH4cUp1iJSr/IjIaGGF" +
       "U32c\nKglj2LBcDcucQmlGH3pJKYs8KWFWk5SYjII8JwSi//KDAjMCbM+Ks6" +
       "2z24nPgcFiHoApQYblol3y\ndvEiaHyhtUeMxyVboAF0LQhx2qgUI5UnMvCA" +
       "lOu6FBhxxNWnKbw4Ak3zpTBQ0cictIOirGWG3cWM\ncH6NVFnb9eqvoFURFQ" +
       "R20chMazM6EmhpjkVLJv3UzPry4Ikfn11PbTsvwLEC4i+GTgssnXxckFM4\n" +
       "keX0jpfDzgc8N4fn2QmBxjMtjfU27UtPD3j/9NxCvc3cFG16hm/hWM3Pdh9e" +
       "6Ltto0QcCKNQllQe\nlZ/AOZ0OvcabtogMs3ZWbER86Yy+POd76eY7TnKf2U" +
       "mxh0xjJSEcAjuawUohmRc4ZSMncgqjcQEP\nKeLEwAb63kMKoO4Fk9ef9gSD" +
       "Kqd5SJ5AH02T6H8QURCGQBEVQZ0Xg1K0LjPaKK1HZEJIAVzECdcM\nov/oXS" +
       "PrnC41LAYFabdLVViXpIzE/rOSwrkCvCoLzB5Xj8yJ7ojSof91og3JGhl0jU" +
       "ohzsWwjMiL\nkmuEh1nLSnUBbgzvkx45grjLd9ts6AitE1qAubBJEgKc4meP" +
       "f/LaXveW7xzUjQUN3OBYI8uBoNMg\n6ESCToOgM5EgsdkonUokrKsNhL4Lpi" +
       "84utKVfTs27zxY7QB7kXfngcSwaTXwZqBxs9KG+Bz3UHfI\ngqFV/WTbAefl" +
       "4zfohuZK74pT9i554/HXj/69dJWd2FP7SeQSPHUxDtOLzjXm/5ZYZ1aq8f96" +
       "d9fT\n77z+4Yr4HNPIkqSpn9wTp261VR+KxHIBcIbx4Y9dU+a4iQwetpM88A" +
       "fgAyl+cC8LrDQSpnBb1B0i\nLwVeUhKUlBAj4KuoDyvWRhVpd/wJNZTptF4B" +
       "yilBm74GrkbDyOkd386UsZylGxZq28IFdbeX75pW\n/+4zJS9SsUQ9c5lp7e" +
       "vjNH2ez4gbS7/CcfD8wx/03v/g5we2UUsxTEWDBTE8LPBsBLosi3eBCS6A\n" +
       "k0FFLhkQQ1KAD/LMsMChxf2nbGnDL/5yz3RdNQI8iWq2duIB4s+vuZHc8fq3" +
       "v1pAh7GxuMDE2Yg3\n07mpiI/crijMHsQRufPC/B++zDwM/g98jsqPc9SNOC" +
       "hnDirx2RAY0J64ljj1tST3F1QlLvp6FS3R\nSRFKhtB3a7CoBpi1aWZRisjA" +
       "z+49OVIdvvXVX1EBlDDmEMOs0S5GbtONCIvFqKjZVkewiVFHod3q\nc93bpw" +
       "vn/g0jDsGILKzIao8CbiiSYA9G6/yC988/P2vnmw5i7yTFgsQEOhk6lUgR2D" +
       "CnjoIHi8g3\nrKdmOn13IZaUZUKFMMcQQMT0rwrArUzvSToxrohPQv9w7Qnv" +
       "az0PUwGk9SEpllXLOONnB45c/o12\nkY4Tn8zYe1Ek2T1DLBbv2/rO2IxpTz" +
       "0SspOCITKdNaLFQUYI45QZguBGjYaQEFEmvE8MVPRVuS3m\nrOZZHYmJrNWN" +
       "xJcFqGNrrJdaPAc6DbIcrnLDc5RbPQddJGbErdcDcdkIp5T//uixr+480GrH" +
       "eZI/\nhtBBKiYr7w5jPLn/1IPzSx74+BCuBziPbDiom5JfQsvlus9waACeFx" +
       "ngsUBW+DGICsCLqDRGjWgk\nb1O7txNwVJl3BwofgihjjPrQT/ZXP/vKwCMH" +
       "9HUng7Uk9PKzt3/08S7HveeH9X5Wk7A0Przg2KdP\nf+Kr1H2UHo4uTooIzX" +
       "30kJTKvEzGSbYoEwXa+oWaRaf2+S4aiMoTAys3bD7+uOd5bvl13/tdirU/\n" +
       "b3iPxumuHstWLDr1qdSWdsqtTzSGFXBVGMZQkcIYsLIRHtuxsi2FJrHuxaIL" +
       "i27QXn6nt6e9H//1\nW8BtzxFcDVyVBrjKicCxWYErQtPy93mGdKvslXVIAx" +
       "pxwBbEgjeQI95auGYaeGdmwJuHFTErvMVU\nmBQwPuItCKUcETbANdtAODsd" +
       "QluDawwrY1khLOxx+7vaN3o2pMK3O0d89bgAGPiqJtL4vqzwXQ34\n3Ft9/k" +
       "G3r8/T050K5e05omyNVqL31Cjt9Pn+rFBeBSj7PV53h7/T274xFcgDkxDlXA" +
       "Pk3AyipM/v\nyU6U3T3+DT1dvT53XzpR3jsJg5xnoJyXASVdSR7KCmWZz+ue" +
       "COb3JwFzgQFzQQaYFM6R7GAOeXon\ngvlIjjDr4Ko2YFZPBPOnWcEsQWl2eb" +
       "r9voGUEI/nCNEF12ID4uIMEG/HyhM5QGzfmg7ik9lDLI3a\n4VID4tIkiIRW" +
       "fpmMjJpoF3pEzFXgSp12N2ABeDpHgKjaZQbAZWkAPofFGU1PnESBVFEgvOT0" +
       "MWJA\nCrXT6L7TyKyYAJ3LEdAiooeVJHpPAeglA1AJRDacnuZRcYtqivHotg" +
       "xDo8fu66jwrd12F80MFDEC\nz6jd8fDWzgewZoOAaWn60C82mJ9dvuP0F+fP" +
       "csvpnqaQVyGMbldGUiToTH0uMSe5rneDR+juPm+Y\nUfWA2prZTE5cJuQjaS" +
       "x4tRFkvGrcTUFHniCJI7IsEwiEKZtrGkEm5SATzHI7+YCxT+m40Dl8Miie\n" +
       "DNgpUQqhHdsbIimiT0wyckiyimk8U77cGGlJj6xihuMqExFPx96nNpcWPnpo" +
       "Px3fEHCRKSVo/C8Y\nY5TueAyKtzc14p+a/Ni6xpbm2ta6xhaNVPZv8vQ5TZ" +
       "bj3BbZATEblIjhvX0KmZUsNGTVsErc6QDT\nV8dnIgbX5pcYUPvc7R0J2mii" +
       "Ar4AVkv/N6xpam5qmmqGG9aCy7AyjDg+TJw9+OgDy8x9OceZi/5t\nhSGFFW" +
       "lm7h+MmVthot1rsovfWjB8miMGDERXGhhWpsHwZwPDNMSgbk3l5j+bBNlVBt" +
       "lVacj+LYHs\nzanIfpEjWVwFawyyNWnI/sMgW4hk+/hxLhXhf05iWas1CNem" +
       "Ifx11Elj0l6BxcFQsZX2v3KkPZ/o\nwQmJ3lPQ/m+M9igjihzNiOIj6y7yf5" +
       "Pg22nQdqambXNYaKeRuS0vR9pziB7xkOg9Be3CmI2F5OFw\nMLpUUNYTnNHq" +
       "JGfU0lqvkZ1T5IyaGuprG5vqGsEblejeiOJDyKUxtCl8kK1oEgqqN4RUn0ZI" +
       "ldEZ\nQQ0zo5iak8XU0jKFYqpfg2JqaoBNHBVTFCHCrjIhTiWqmRlERVlbRt" +
       "/PMT7KYB3DuKSPPgYizDnN\nT/fRkeabDmy9VLqfeWGH3Ug7D2qkSJPkOoEb" +
       "4wQTrRIcKeEDUBcNa+JZz7sf+9lp7c2adXrmalX6\nQMzacdW6o+ML1z15aB" +
       "KffRZaeLMOPWNs7rcco/wrNIoxkqhJX5ATO7Ulpk6LAU9YEfsTEqjXxuyV\n" +
       "fnrBNXOLYa9brPZKFRzXXDzZbzPp0BQB5oMSGQ272ZyWHqbPA7YmLGrAmmDx" +
       "3ciEQkxSJDkm8YG4\nYdVONAejqXf8Y1ueyCDmN3oMBnuyZtAR35/x8YKivz" +
       "4DZ+uxaNMgkg4xI9yN3AiPK46tOc7LdVfC\nC/bZbvCyPWte8umI+RZeLFxt" +
       "ysDVZizcGimlXPUqnMwonIWvCXO1afiiiTEfXH6DL39KvpJ3qDRr\nthJAqX" +
       "tEdlSRRFjljBxnVqzrPjeGw41kN2fCEXVYs80Oi0oEP+5Jiu63i3U/3dzY0m" +
       "rZ/HgllhE8\nHY+eL7lwONyyOeq03kJ5DE1dGL62ubZlbV0rePQ8REIVOpBB" +
       "2Tux6IEFh7I2IEMnq657r8SGr4dr\nlyHjXd+EDU+kF8rVaAaO8bSKjYO1g3" +
       "bq5AXBwm/wSvjFqEk0+BWz5teMT8vwDnPbtlvBlVLsbjFg\nga5cCfR1tJn+" +
       "i2QNPYPr3JdhGQENVpg2tBGWk3EBpf3uwOI2jVy9W+E1zrfxxk0cE6AfNM28" +
       "7r0S\nXjENdcng9dKk1HR3NszxktPTk8jcd7E4ALtkylx/wpbYzN7BSbJHw1" +
       "IvXJcN9i6nZe/MpN3nDThm\nRyYiObpPPVth24bFj6Yu4G1c3Vrb0lS3phnc" +
       "I+5PqUYemqQmj2JxPziSmCYtKnxgkiqkyeZegLBO\nF65+T1Jh8iKZlGC2cJ" +
       "ZHW+WZNJpqE7I2roonpnKLtrp2dUtdM6jCzouZIbXWxyE9PbWQYPFcA7tG\n" +
       "hxTWA9tTGVzAGSyOR7dJnKrGuaD6P3El+ofgxGYk+fT7N6B/89mglHJujMv5" +
       "hSmUc0tz7eqGuub6\nbFRvcgyvTi0kdAxNJtX/OoPqMW9oexbCJgXPdKXU/t" +
       "lstU8/9CZgwiNfVUnHpPWjvaz3/du2f+l9\n+2s9ux89flviJYXBsCCYj9KY" +
       "6tMAYZCuyqREP1hDZW17O9WO3BAUqMCoURW8pfd4D5Yuaw/wpngz\nN/sAZG" +
       "NqBmMZNXOjiyhtBT8p2T6So/7W9MVJP1QUSZAWymZxwo6dnl2P7qrD+ul1P7" +
       "v18W3XRg71\n30e36vmswIyP4zDFXlKgn1qko+LOfFHa0aJjvUGeenLwmSfW" +
       "RoN4ehSt0jQLzRNZz/RVZtK+Qham\nPiroDskaPdw3fmb2z687fuQiPY0k/x" +
       "8VzjpWcjAAAA==");
}
