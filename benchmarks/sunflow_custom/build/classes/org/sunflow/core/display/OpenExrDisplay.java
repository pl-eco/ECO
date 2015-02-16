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
      1163966486000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
