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
    private static final byte HALF = 1;
    private static final byte FLOAT = 2;
    private static final int HALF_SIZE = 2;
    private static final int FLOAT_SIZE = 4;
    private static final int OE_MAGIC = 20000630;
    private static final int OE_EXR_VERSION = 2;
    private static final int OE_TILED_FLAG = 512;
    private static final int NO_COMPRESSION = 0;
    private static final int RLE_COMPRESSION = 1;
    private static final int ZIP_COMPRESSION = 3;
    private static final int RLE_MIN_RUN = 3;
    private static final int RLE_MAX_RUN = 127;
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
        super(
          );
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
                  getMessage(
                    ));
            e.
              printStackTrace(
                );
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
                  getMessage(
                    ));
            e.
              printStackTrace(
                );
        }
    }
    public void imageFill(int x, int y, int w, int h,
                          Color c) {  }
    public void imageEnd() { try { writeTileOffsets(
                                     );
                                   file.close(); }
                             catch (IOException e) {
                                 UI.
                                   printError(
                                     Module.
                                       DISP,
                                     "EXR - %s",
                                     e.
                                       getMessage(
                                         ));
                                 e.
                                   printStackTrace(
                                     );
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
                                          getBytes(
                                            ));
                             file.write(0);
                             file.write("chlist".
                                          getBytes(
                                            ));
                             file.write(0);
                             file.write(ByteUtil.
                                          get4Bytes(
                                            55));
                             file.write("R".getBytes(
                                              ));
                             file.write(chanOut);
                             file.write("G".getBytes(
                                              ));
                             file.write(chanOut);
                             file.write("B".getBytes(
                                              ));
                             file.write(chanOut);
                             file.write(0);
                             file.write("compression".
                                          getBytes(
                                            ));
                             file.write(0);
                             file.write("compression".
                                          getBytes(
                                            ));
                             file.write(0);
                             file.write(1);
                             file.write(ByteUtil.
                                          get4BytesInv(
                                            compression));
                             file.write("dataWindow".
                                          getBytes(
                                            ));
                             file.write(0);
                             file.write("box2i".getBytes(
                                                  ));
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
                                          getBytes(
                                            ));
                             file.write(0);
                             file.write("box2i".getBytes(
                                                  ));
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
                                          getBytes(
                                            ));
                             file.write(0);
                             file.write("lineOrder".
                                          getBytes(
                                            ));
                             file.write(0);
                             file.write(1);
                             file.write(ByteUtil.
                                          get4BytesInv(
                                            2));
                             file.write("pixelAspectRatio".
                                          getBytes(
                                            ));
                             file.write(0);
                             file.write("float".getBytes(
                                                  ));
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
                                          getBytes(
                                            ));
                             file.write(0);
                             file.write("v2f".getBytes(
                                                ));
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
                                          getBytes(
                                            ));
                             file.write(0);
                             file.write("float".getBytes(
                                                  ));
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
                             file.write("tiles".getBytes(
                                                  ));
                             file.write(0);
                             file.write("tiledesc".
                                          getBytes(
                                            ));
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
                                 getFilePointer(
                                   );
                             writeTileOffsets(); }
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
            getFilePointer(
              );
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
                  getRGB(
                    );
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
    private static final int compress(int tp, byte[] in,
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
                  finish(
                    );
                ret =
                  def.
                    deflate(
                      out);
                return ret;
            case RLE_COMPRESSION:
                return rleCompress(
                         tmp,
                         inSize,
                         out);
            default:
                return -1;
        }
    }
    private static final int rleCompress(byte[] in,
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
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1425485134000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "jxY/t6Dbzn0XY9XisLE2Rwb0u/8AY+IcxNQkAAA=");
}
