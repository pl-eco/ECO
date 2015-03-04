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
      1425482308000L;
    public static final String jlc$ClassType$jl7 =
      ("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");
}
