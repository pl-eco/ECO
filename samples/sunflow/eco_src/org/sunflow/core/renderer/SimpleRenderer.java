package org.sunflow.core.renderer;
import org.sunflow.core.Display;
import org.sunflow.core.ImageSampler;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.Options;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;
public class SimpleRenderer implements ImageSampler {
    private Scene scene;
    private Display display;
    private int imageWidth;
    private int imageHeight;
    private int numBucketsX;
    private int numBucketsY;
    private int bucketCounter;
    private int numBuckets;
    public boolean prepare(Options options, Scene scene, int w, int h) { this.
                                                                           scene =
                                                                           scene;
                                                                         imageWidth =
                                                                           w;
                                                                         imageHeight =
                                                                           h;
                                                                         numBucketsX =
                                                                           imageWidth +
                                                                             31 >>>
                                                                             5;
                                                                         numBucketsY =
                                                                           imageHeight +
                                                                             31 >>>
                                                                             5;
                                                                         numBuckets =
                                                                           numBucketsX *
                                                                             numBucketsY;
                                                                         return true;
    }
    public void render(Display display) {
        this.
          display =
          display;
        display.
          imageBegin(
            imageWidth,
            imageHeight,
            32);
        bucketCounter =
          0;
        Timer timer =
          new Timer(
          );
        timer.
          start();
        Thread[] renderThreads =
          new Thread[scene.
                       getThreads()];
        for (int i =
               0;
             i <
               renderThreads.
                 length;
             i++) {
            renderThreads[i] =
              new BucketThread(
                );
            renderThreads[i].
              start();
        }
        for (int i =
               0;
             i <
               renderThreads.
                 length;
             i++) {
            try {
                renderThreads[i].
                  join();
            }
            catch (InterruptedException e) {
                UI.
                  printError(
                    Module.
                      BCKT,
                    "Bucket processing thread %d of %d was interrupted",
                    i +
                      1,
                    renderThreads.
                      length);
            }
        }
        timer.
          end();
        UI.
          printInfo(
            Module.
              BCKT,
            "Render time: %s",
            timer.
              toString());
        display.
          imageEnd();
    }
    private class BucketThread extends Thread {
        public void run() { IntersectionState istate =
                              new IntersectionState(
                              );
                            while (true) {
                                int bx;
                                int by;
                                synchronized (SimpleRenderer.this)  {
                                    if (bucketCounter >=
                                          numBuckets)
                                        return;
                                    by =
                                      bucketCounter /
                                        numBucketsX;
                                    bx =
                                      bucketCounter %
                                        numBucketsX;
                                    bucketCounter++;
                                }
                                SimpleRenderer.this.
                                  renderBucket(
                                    bx,
                                    by,
                                    istate);
                            } }
        public BucketThread() { super(); }
        final public static String jlc$CompilerVersion$jl =
          "2.5.0";
        final public static long jlc$SourceLastModified$jl =
          1163562070000L;
        final public static String jlc$ClassType$jl =
          ("H4sIAAAAAAAAAJ1Xe2wURRif3vVBH+baAuXd0lKQ5y0kYoQSodQWCgfUlmd5" +
           "lOnu3N3Svd1ldrY9\nCkGIiSDEB1ETTRSJIeEhiAkaNEGFAIryD5ioCQmoIV" +
           "ETxcSYIEb/8JuZe+61xdCkc7sz3/vxm29P\n3UUFDkXjVSfIdtjECTZ1tGHq" +
           "EK3JwI6zGra61CsFxW3HlpuWD+WFkE/XGAqEVEfRMMOKrimtTzXE\nKZphW8" +
           "aOiGGxIImz4DZjbkLestDcHIHrDp+r3Hs0v8aHCkIogE3TYpjpltlskJjDUH" +
           "loG+7Fist0\nQwnpDmsIoUeI6caaLNNh2GTOdrQb+UOo0Fa5TIZqQ0nlCihX" +
           "bExxTBHqlTahFiQMp4Rh3SRaY0od\ncM7M5gSzE3ztudQgZBg/XAvuCAvA64" +
           "kpr6W3Oa7a/uNrH9915IQfBTpRQDc7uDAVPGGgrxOVxUis\nm1CnUdOI1okq" +
           "TEK0DkJ1bOj9QmsnqnT0iImZS4nTThzL6OWElY5rEyp0JjdDqEzlPlFXZRZN" +
           "xSis\nE0NLvhWEDRwBt6vSbkt3W/g+OFiig2E0jFWSZMnv0U3IeI2XI+Vj/X" +
           "IgANaiGGFRK6Uq38SwgSpl\nLg1sRpQORnUzAqQFlgtaGBo7qFAeaxurPThC" +
           "uhga7aVrk0dAVSwCwVkYGuklE5IgS2M9WcrIz4yq\ne/uPv/npIlHb+RpRDW" +
           "5/CTBVe5jaSZhQYqpEMt53g6+2bnDH+xAC4pEeYknTOPncmtAvn9VImnED\n" +
           "0Kzq3kZU1qWuPFTTvnOJhfzcjGG25eg8+Vmei3ZoS5w0xG3o2qqURH4YTB5e" +
           "aP98w56T5FcfKmlF\nhapluDGoowrVitm6QegSYhKKGdFaUTExtSZx3oqK4D" +
           "kEJS93V4XDDmGtKN8QW4WWeIcQhUEED1Ex\nPOtm2Eo+25hFxXPcRgiVwT+q" +
           "gP8nkPwTvww1BBXHNcOG1ac4VFUsGkm9qxYlCgRYgyhTpUOP2QZp\nT7wGeR" +
           "HZDK1TolaMKFjFpm5aSkSHtlWtWRrp5b8PLzrOLa/sy8vjUOhtaQO6YallAH" +
           "WXeuzOV7ua\nlz+/X5YLL/GEzwzNBY3BhMYg1xhMagxma6xf7Ko9hK2OUoI1" +
           "lJcntI7gZsg0QhJ6oJ0B+MqmdWxe\ntnV/nR/qx+7Lhwhy0jpwNWFbs2o1pX" +
           "u+VcCjCoU3+p2N+4L3jy2UhacMDs0DcpdeP33tyJ9l033I\nNzBucp8BuUu4" +
           "mDYOtik8rPd22kDyfz+w4uy3125NTfccQ/U5UJDLyVu5zpsdaqlEA3BMiz86" +
           "JuBf\nh9Ye8qF8wAfARGE/wE21V0dWSzck4ZH7UhRCpWGLxrDBj5KYVsKi1O" +
           "pL74iyKefLCFlBPJEeAwWy\n3n+2cPZ350uvCI+TIBzIuOY6CJMtXZGug9WU" +
           "ENi/9XrbK6/d3bdRFEGiChgqsqneC00cB54paR5o\nZgMAhSepfo0ZszQ9rO" +
           "Nug/Bq+jcwec6Hv71YLsNuwE4yazMfLCC9P2Yx2nNty1/VQkyeyi+TtB9p\n" +
           "MunO8LTkRkrxDm5HfO/XE974Ar8FWAf44uj9REAGEq4hEcigiO80sc7ynM3m" +
           "Sx3InjlIWQ9wdXep\nu05G6tztX34srC7FmTNAZh5WYLtBZlXoHg5KgyixZE" +
           "EZPx1p87WKp2CUt32XYicKwh67sHJTuXHh\nH1DbCWpVuFedVRRgIJ6V6gR1" +
           "QdHNi5eqtt7wI18LKjEsrLVg0QCoGCqPOFFAobi9cJEwo7xvGF9F\nXJCwdm" +
           "wiSvGsN/EySaxTEuXDn6d6qCiaMNgtKW74fev/KHsOX94sIaUy++Zphuns5x" +
           "2XyKMLXvhx\nAGgsTEw5aYUcxyZk4dgKMT2ku/jAiXfPsRsz5kt90weHMC/j" +
           "9PlH+mvmnzn4EOhV44mAV3RF77in\n/VH9qk8MOBK4cgajbKaGzFiAUrDHpS" +
           "aPKt8pE7U2MVVrAZ6LMfA/L1Fr87y1JmEmN6UQZNvtNnQ1\nPkTrhIY4W8mX" +
           "Fob81DUhOaMzPwioHoPBolfA5J3n6j65uubtfTIv04aY+jO5utRnvv+hx//S" +
           "xW7J\n5x2uPMSHqo/+dPZO+whZS3ICnZQzBGbyyClUeBKweTXXDqVBUF+eUX" +
           "tqd/ttYRHne5Kh/F5LlxPs\nHL4skcGc++COY6gs81YXh6MYmvp/ZwIe8ZxP" +
           "CTn+qqGbOzfdC33zt7jPUiNqKcyJYdcwMoors9AK\nbUrCuvCrVAKaLX5gmh" +
           "4zqFEMDUs+Cg+2SJ5u+CTz8kCs+E8mGeyVZpDBHZV4yiSKQoUBEX/U7WSY\n" +
           "ygUS8m+EoIxfNjbxi2RSVqGJL7xkk7ryG69LXX9648T4wdUvi84vgG/D/n4x" +
           "zMO3ibzLU41eO6i0\npKzr6P0za8+/Ny9ZHFm3fA68zpGnQ1QLgMvAl2xzzG" +
           "biWuz/aNQHC44dvu0T9/x/lZoWn5gPAAA=");
    }
    public void renderBucket(int bx, int by,
                             IntersectionState istate) {
        int x0 =
          bx *
          32;
        int y0 =
          by *
          32;
        int bw =
          Math.
          min(
            32,
            imageWidth -
              x0);
        int bh =
          Math.
          min(
            32,
            imageHeight -
              y0);
        Color[] bucketRGB =
          new Color[bw *
                      bh];
        for (int y =
               0,
               i =
                 0;
             y <
               bh;
             y++) {
            for (int x =
                   0;
                 x <
                   bw;
                 x++,
                   i++) {
                ShadingState state =
                  scene.
                  getRadiance(
                    istate,
                    x0 +
                      x,
                    imageHeight -
                      1 -
                      (y0 +
                         y),
                    0.0,
                    0.0,
                    0.0,
                    0);
                bucketRGB[i] =
                  state !=
                    null
                    ? state.
                    getResult()
                    : Color.
                        BLACK;
            }
        }
        display.
          imageUpdate(
            x0,
            y0,
            bw,
            bh,
            bucketRGB);
    }
    public SimpleRenderer() { super(); }
    final public static String jlc$CompilerVersion$jl =
      "2.5.0";
    final public static long jlc$SourceLastModified$jl =
      1163562070000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAALUYa2wcxXnu4bfh/IgdJyRx7ATI85aopGpjpMQYm1xywcaX" +
       "2IkDmPHu3N3Ge7vL\n7px9NhENaotTEG2jlopKEKIqlcOr0FIUqqY0CGgpUS" +
       "WoVCohkbaK1FZqqVRVoqnaH/1mZvf2du/O\nJFQ5aedmZ77nfM/ZFz5GNbaF" +
       "Vsl2nM6ZxI4PpEawZRNlQMO2vR+WJuW3axpGFvfqRhiFkiisKhTF\nkrItKZ" +
       "hiSVWkxB19BQttNg1tLqMZNE4KNH5E2+7Q25PcXkZw/OTZtodPR7vDqCaJYl" +
       "jXDYqpauiD\nGsnZFLUkj+AZLOWpqklJ1aZ9SXQd0fO5AUO3Kdap/QB6CEWS" +
       "qNaUGU2KepIucwmYSya2cE7i7KUR\nzhYotFuEYlUnSn+RHWBu8WOC2A7eaD" +
       "k0EKlnm2OgDpcAtF5b1FpoW6aqGTkz9vmjp56NoNgEiql6\nihGTQRMK/CZQ" +
       "c47kpohl9ysKUSZQq06IkiKWijV1nnOdQG22mtExzVvEHiW2oc0wwDY7bxKL" +
       "83QX\nk6hZZjpZeZkaVvGM0irRFPetJq3hDKjd6akt1B1i66BgowqCWWksEx" +
       "clOq3qYPHuIEZRx/V7AQBQ\n63KEZo0iq6iOYQG1CVtqWM9IKWqpegZAa4w8" +
       "cKFoZVWi7KxNLE/jDJmkqCsINyK2AKqBHwRDoagj\nCMYpgZVWBqxUYp/NnZ" +
       "8cP/PU67u4b0cVImtM/kZAWhNAGiVpYhFdJgLxcj7+7cSh/KowQgDcEQAW\n" +
       "MP03nj2Q/MvPuwXMDRVghqeOEJlOyned6B598E4DRZgY9aZhq8z4Ps15OIw4" +
       "O30FE6K2s0iRbcbd\nzfOjvzh07Dny1zBqTKBa2dDyOfCjVtnImapGrDuJTi" +
       "xMiZJADURXBvh+AtXBPAkuL1aH02mb0ASK\nanyp1uDvcERpIMGOqAHmqp42" +
       "3LmJaZbPCyZCqA4e9Dl4rkfix/8p6otLdl5Pa8asZFuyZFiZ4rts\nWESCA1" +
       "bglC0ppeZMjYw6r3HmRCZF41LWyBEJy1hXdUPKqBC2srFVITPs/7OTLjDJ22" +
       "ZDIZYKgyGt\nQTTsNjSAnpQXL717dHDv144Ld2Eu7uhM0QbgGHc4xhnHuMsx" +
       "7ueIQiHOaBnjLCwH5z4NEQy5rnlj\n6t499x/vjYDLmLNRODQG2gvaOeIMys" +
       "aAF+YJnhFl8LWu7x1eiF9e3Cl8TaqejStiN7334oVT/2ze\nFEbhyqmSqQnJ" +
       "upGRGWH5tZgC1weDqxL9vz+675UPLny0wQszitaXRX85Jove3qBBLEMmCuRD" +
       "j/zp\nFbHIOBo7EUZRSAmQBrn8kGHWBHn4orjPzYhMl7okakobVg5rbMtNY4" +
       "00axmz3gr3lBY2LBNOwwwZ\nEJAn08tfrr3ld+ea3uYau3k3VlLZUoSKKG71" +
       "/GC/RQisf/TkyLee+HjhMHcCxwsolLv8lKbKBUC5\nyUOB8NUghTAbrT+g5w" +
       "xFTat4SiPMmf4bu3Hbq3/7eos4dQ1WXKNt+XQC3vqK29GxC/f9aw0nE5JZ\n" +
       "+fDU8MCENu0e5X7LwnNMjsLDv1n93V/ipyG7QUax1XnCkwTimiF+jnF+vBv5" +
       "uDWwdwsbeoH2lipe\nXaFYT8pHn8v05h/41U+41E24tOqXmmEfNvuEUTnvdm" +
       "B6K3IGX/Jiux0mGzuZCZYHo3c3trNA7Nbz\nd93Top3/D7CdALYyVFJ72ILA" +
       "L/gs7UDX1H34xpud978fQeEh1KgZWBnC3P9RAzgesbOQdwrmzl1c\njJbZej" +
       "byc0Fc2pXOKRVK3upBuI3Vw3+I9QNe5ExObTmTfHf4aX5KVQO/QjkM0Jl//c" +
       "DJy7+mFzkd\nLwIZdk+hPKlCD+XhfuGDmdbal5/JhVHdBGqRnS5vDGt5FgwT" +
       "0JTYbusHnaBv399giGraV8wwq4LR\nX8I2GPteMoc5g2bz5oBnNLPT7oIn5n" +
       "hGLOgZIcQnOznKOj7eVIzgOtNSZzDr/KDplqEM8/3lEFBl\ntSPFtkWCYeM2" +
       "NuwSZt5e1R12+AVd5W67/xUETbChH0RTVNvU8JwrUVeZRHcIgIBMe65Spg3w" +
       "tDoy\ntVaRacSRqVHNQZM3DheOLPh0V+kVxVJz0OrM8Cx+6ZHen71z4JkFUf" +
       "mWcH0f1qT8pd//YTryjTem\nBF7QvwPAJ9ac/tMrl0aXiVQqeuJ1ZW1pKY7o" +
       "i7kDxUyWMXqW4sCh39rc88JDoxcdidr83d0g3ID+\nPPcmufm2x/9Yof2IQO" +
       "cesM3d18A29zm2aeK22U3UTJazHQ/wnrxK3hvhaXN4t1XhLbu84SRuz8vT\n" +
       "hNoHK/FWrgHvbDnvQ5V4q1fJexM87Q7v9iq8cw7v66Y44wEjzy5plbjr14C7" +
       "5Uajp3kl1vYSrAsV\nsiGbbyitWyE39awuSz0J5mspzJpoi0XS6mr3OR5FCw" +
       "f/0fwIfuvesNM+HKJQTA1zq0ZmiFbCMMIo\n+RrrffwG6xWmR599/ix9f/MO" +
       "EY+bqmeWIOKmHafmu3e89NhnaKe7A7oFSbfO3HB3JKu+E+aXbFHn\nyi7nfq" +
       "Q+f3VrBHnylr7fV+PWFh2jidlsCJ4OxzE6go7BLc+G2UDTFuXnGq1eQ4ZNrv" +
       "0VlD3uXuOc\n1cISreHjbPgKr6zExJYoqClT+NVBWJ8yDI1g3fPTr35aiLg9" +
       "FX855j+Wm+Hpdo6l+4qPJeT376ql\nlVN4cgltn2LDE3ATEDfLoLLRGUNVPE" +
       "2/8/9oyhwg4WiauGJNIyKwPAO6OveWxzTLYba4ObCPYoRT\nXVxC++fZcJqi" +
       "ZqG9yEVs7aSn8/evVGdowa73X8vZ5aWr7HOe+AQlJz988J5Pkr/9N79gFj8T" +
       "NSVR\nfTqvaaWtY8m8FpwyrXLZm0QjafK/H1G0ourHAorq3SkX+IcC51WKWo" +
       "I4YHH2Vwr2GlSoEjAIAGdW\nCvRT6BYAiE3Pma6FWvjdhLXRcdFGF3znxU5n" +
       "nS8B8q+sbpLKi++sk/LBFw+vLTy2/5s889XIGp6f\nZ2Qak6hOXK6Lia6nKj" +
       "WX1nvo5ZfGzv3gi24i9127yxx4m9hdwv6QXCtfewdzJuUX1fnXlv/4tsWT\n" +
       "F8P84v0/2uP7ihwXAAA=");
}
