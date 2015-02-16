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
          start(
            );
        Thread[] renderThreads =
          new Thread[scene.
                       getThreads(
                         )];
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
              start(
                );
        }
        for (int i =
               0;
             i <
               renderThreads.
                 length;
             i++) {
            try {
                renderThreads[i].
                  join(
                    );
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
          end(
            );
        UI.
          printInfo(
            Module.
              BCKT,
            "Render time: %s",
            timer.
              toString(
                ));
        display.
          imageEnd(
            );
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
                                renderBucket(
                                  bx,
                                  by,
                                  istate);
                            } }
        public BucketThread() { super(); }
        public static final String jlc$CompilerVersion$jl7 =
          "2.6.1";
        public static final long jlc$SourceLastModified$jl7 =
          1163562070000L;
        public static final String jlc$ClassType$jl7 =
          ("H4sIAAAAAAAAAL1XX2wURRifXv+XwrXlX0EobTmQUtyFB1QsEeFSoHhA0xaM" +
           "JXBMd+fulu7tLrNz7VGsAokp8YEYLQhG+2AgiPIvRoLGkPRJIPiCMRofBN8k" +
           "Kg+8oAkqfjO7d3u3vVZ98ZKdnZ35vvn+/+a78/dRqU1Rq2XqB+K6ySSSZtI+" +
           "fbXEDljElrZEVndiahM1rGPb7oG1qNJ8Ofjw0ZuJmgAq60UzsWGYDDPNNOwu" +
           "Ypv6AFEjKOittuskaTNUE9mHB7CcYpouRzSbtUXQtBxWhkKRjAoyqCCDCrJQ" +
           "QV7vUQHTdGKkkmHOgQ1m70evoqIIKrMUrh5DTfmHWJjipHtMp7AATqjg3zvB" +
           "KMGcpqgxa7tj8wSDj7fKo+/sqfmkGAV7UVAzurk6CijBQEgvqk6SZB+h9npV" +
           "JWovqjUIUbsJ1bCuDQm9e1GdrcUNzFKUZJ3EF1MWoUKm57lqhdtGUwozada8" +
           "mEZ0NfNVGtNxHGyd49nqWLiRr4OBVRooRmNYIRmWkn7NUBla5OfI2hh6EQiA" +
           "tTxJWMLMiioxMCygOid2OjbicjejmhEH0lIzBVIYmj/podzXFlb6cZxEGar3" +
           "03U6W0BVKRzBWRia7ScTJ0GU5vuilBOf+9vWHjtobDYCQmeVKDrXvwKYGnxM" +
           "XSRGKDEU4jBWL4+cwHOuHQ0gBMSzfcQOzdVXHrywomH8hkPzRAGa7X37iMKi" +
           "yum+GbcXhFvWFHM1KizT1njw8ywX6d/p7rSlLai8OdkT+aaU2Rzv+vLlQx+R" +
           "XwKoqgOVKaaeSkIe1Spm0tJ0QjcRg1DMiNqBKomhhsV+ByqHeUQziLO6PRaz" +
           "CetAJbpYKjPFN7goBkdwF5XDXDNiZmZuYZYQ87SFEKqGB9XC8yxyfuLNUFze" +
           "YUO6y1jBhmaYMiQvwVRJyEQxo33g3UQS035bVlI2M5OynTJiujko21SRTRrP" +
           "fismJTIEQ4WIULlbS1o66XI/JZ5w1v8nKs2trhksKoKALPDDgQ6VtNnUgTqq" +
           "jKY2tD+4GL0VyJaH6y+GVoNEyZUocYlSRqKULzG0IaX0E9aToASrqKhISJ3F" +
           "1XBSAALYD1AAIFnd0r17y96jzcWQe9ZgCXifkzaD8a5u7YoZ9vCiQ6CiAklb" +
           "/8GuEen3s+ucpJUnB/eC3Gj85ODhna+tDKBAPkpzW2GpirN3cmzNYmjIX52F" +
           "zg2O3Ht46cSw6dVpHuy78DGRk5d/sz8q1FSICoDqHb+8EV+JXhsOBVAJYArg" +
           "KMOQ9wBRDX4ZeTDQloFUbkspGBwzaRLrfCuDg1UsQc1Bb0Wkyww+1DmZwwPo" +
           "U1Cg8cbPx09debd1TSAXuIM5V2E3YQ4M1Hrx76GEwPoPJzvfPn5/ZJcIPlAs" +
           "LiQgxMcwgAJEAzz2+o3939+9c/qbgJcwDJVbVBsArEjDIUs9MYAZOuAWj2to" +
           "h5E0VS2m4T6d8MT7I7hk1ZVfj9U4kdJhJRPoFf98gLc+bwM6dGvPbw3imCKF" +
           "31me6R6Z44GZ3snrKcUHuB7pw18vPHUdvw+QCjBma0NEIBMSpiHhe0mEpEWM" +
           "T/n2VvKh0ZqwlxYr9e6X+GgSY4gPTzqO49NlPkqKFk52DYkr9PSR0TF1+5lV" +
           "Tt3V5UN7O3QuF7798yvp5I83C+BHmdtGeAJ5sS/MK/at4nr2Uv6Ncx9fZbdb" +
           "n3PkLZ+8zv2M14/8PL/n+cTe/1Dii3yW+488t/X8zU1LlbcCqDhb3RM6jnym" +
           "tlwfgFBKoEUyuDf5SpWIYYNQgF9DQR6DefCsce8j8ea7My0+znJrsXA4wcFW" +
           "qk/XlPQUCdM+xd4mPqxjqJimDAhMyxQdM9WScIkPuF2GPFx3t/+9execIPlb" +
           "Eh8xOTr6xmPp2Gggp29bPKF1yuVxejeh4nTHV4/hVwTPX/zh+vMF5+6uC7sN" +
           "RGO2g7AsntdNU6klRGz86dLwFx8OjwRcfzzDUMmAqakFyouh6tw7ThDMZmjZ" +
           "v70hQZ/6CU2500gqF8eCFXPHdnwnUD7b7FVCxxVL6XpONuVmVplFSUwTalc6" +
           "4G2J10sMzZtUKYYqMlNhwU6Hpxf+zPh5wBX8lUu2m6FpOWQAw+4sl2gvpBMQ" +
           "8Sm2Mm6qEUDIu23J8V8a5aGQ5cekxXmpKP70ZGo05fztiSqXxrZsO/jg6TOi" +
           "4Evh79LQkGiSoed37rtsnTdNelrmrLLNLY9mXK5cksmEvJvQp9uiwvdFe9Ji" +
           "AuGHPpv76dqzY3fEjfU3CIMy/40OAAA=");
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
                    getResult(
                      )
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
    public static final String jlc$CompilerVersion$jl7 =
      "2.6.1";
    public static final long jlc$SourceLastModified$jl7 =
      1163562070000L;
    public static final String jlc$ClassType$jl7 =
      ("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" +
       "VLlf66Cs/d+CMOUFYZ0IwhjTs3NC4p7aYoegdXpiO2uI3e+LBft35dV5xt0H" +
       "vwCxD1aKfehzxG6D1uWJ7aoh9qAndn1ayExaeXwtfAGCD/kpHthbIrVQ49zj" +
       "cGspOkf8k7ip4iSOY9imKdZSDubbplpPApFr50+srGoTz90R9a6GCU5auGXf" +
       "ZrAFZpQorENJZfXVA+IRFMDw4y/+5HX+3vZ7ZNZuq31ow4xvnfhL774dcw//" +
       "F1VVf8insMgXH7jwzv03q09GSV0RzSvedeVMw+UY3uoweIia+8qQvK8YbxHe" +
       "XmjdXry7w/EWAcWOhi7ierGf9bWhdMIuer+jmBt7hMAja1zqeexMcUMwqHrk" +
       "xTCGXUpmzl5YS1uWwahZefWLicNF/9bh5C3Q+j3/+q/Zv0iQsklB9cgaRh/H" +
       "7hiUYvIVUM3m+gVL167N4FFo457B49dscJ1M8GCr/dAMVJ4tRAFXVmf4+wYT" +
       "Ur+7hoPfw+4xTtqkg/LI49yJKuUXJ+3ljyCs/3oqfniRPxaor6zGmq9f3f87" +
       "UdYXH/Qt8KrO5A2jtB4pGTdCdmR0YVqLrE5s8e9JTm6o+TTjpNkfCqOfkDxn" +
       "OImHeSBe+K+U7CygcwkZZKE3KiX6EVxdQITDc7YfgLgofbE2S8jarEBKIAmr" +
       "/9KvsqcAoo74YctHiLz8aWtWvbi6e++xq195TsBNg2rQpSWU0pwiTfKBU0SZ" +
       "LTWl+bIax4Y+bX+15SYfPcuePiHb+qu/EEZyNhc1/dIb17927/Or74snyn8A" +
       "Nyu083EUAAA=");
}
